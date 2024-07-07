package com.projet.eval.utilitaire;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.lang.reflect.Array;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.projet.eval.exception.MyException;

import jakarta.servlet.ServletContext;

public class Csvutil<T> {

    private String to_setAttribu(String nomAttribu){
          String getAttribMaj="set";
          String attrib=nomAttribu.substring(0,1).toUpperCase(); //rendre en majuscul la premiere lettre du nomAtribu
          String restattrib=nomAttribu.substring(1,nomAttribu.length());  //prendre les lettres a partir du 2e lettre=
          getAttribMaj=getAttribMaj.concat(attrib+restattrib); //fusionner pour avoir le nom de fonction "getAtribu"
          return getAttribMaj;
    }
    public T getObjectByData(String[] headers,String[] data,T objT)throws Exception{
        Field[] fields=objT.getClass().getDeclaredFields();
        Map<String, Method> methodMap = new HashMap<>();
        Method methodtemp=null;
        for (Field field : fields) {
            //verifier si l'objet a un setters string , puis mettre cette methode dans l'HashMap
            try{
                methodtemp=objT.getClass().getDeclaredMethod(to_setAttribu(field.getName()), String.class);
                methodMap.put(field.getName().toLowerCase(), objT.getClass().getDeclaredMethod( to_setAttribu(field.getName()),  String.class));
            }catch(Exception ex){ System.out.println(ex.getMessage()); }
        }
        String headtemp="";
        T newT=(T)objT.getClass().getDeclaredConstructor().newInstance();
        for (int i = 0; i < headers.length; i++) {
            headtemp=headers[i].toLowerCase();
            methodtemp=methodMap.get(headtemp);//raha tsy misy ao @ le headtemp
            if(methodtemp!=null){
                try{
                    methodtemp.invoke(newT,data[i]);
                }catch(InvocationTargetException ie){
                    throw new MyException(ie.getCause().getMessage());
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
        return newT;
    }
    public T getObjectByDataSimple(String[] data,T objT,boolean exclu1erfield)throws Exception{
        Field[] fields=objT.getClass().getDeclaredFields();
        Method methodtemp=null;
        T newT=(T)objT.getClass().getDeclaredConstructor().newInstance();
        for (int i = 0; i < data.length; i++) {
            methodtemp=null;
            try{
                if(exclu1erfield==true){
                    methodtemp=objT.getClass().getDeclaredMethod(to_setAttribu(fields[i+1].getName()), String.class);
                }else{
                    methodtemp=objT.getClass().getDeclaredMethod(to_setAttribu(fields[i].getName()), String.class);
                }
            }catch(Exception ex){ System.out.println(ex.getMessage()); }
            if(methodtemp!=null){
                try{
                    methodtemp.invoke(newT,data[i]);
                }catch(InvocationTargetException ie){
                    throw new MyException(ie.getCause().getMessage());
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
        return newT;
    }
    public T[] getObjectsByData(String[] headers,String[][] data,T objT)throws Exception{
        T[] objs=(T[]) Array.newInstance(objT.getClass(), data.length);
        for(int i=0;i<data.length;i++){
            objs[i]=getObjectByData(headers, data[i],objT);
        }
        return objs;
    }
    public T[] getObjectsByDataSimple(String[][] data,T objT,boolean exclu1erfield)throws Exception{
        T[] objs=(T[]) Array.newInstance(objT.getClass(), data.length);
        for(int i=0;i<data.length;i++){
            objs[i]=getObjectByDataSimple( data[i],objT,exclu1erfield);
        }
        return objs;
    }
    public T[] getObjectsByData(String[] headers,List<String[]> ldata,T objT)throws Exception{
        T[] objs=(T[]) Array.newInstance(objT.getClass(), ldata.size());
        for(int i=0;i<ldata.size();i++){
            objs[i]=getObjectByData(headers, ldata.get(i),objT);
        }
        return objs;
    }
    public T[] getObjectsByDataSimple(List<String[]> ldata,T objT,boolean exclu1erfield)throws Exception{
        T[] objs=(T[]) Array.newInstance(objT.getClass(), ldata.size());
        int line=0;
            for(int i=0;i<ldata.size();i++){
                line=i+1;
                objs[i]=getObjectByDataSimple(ldata.get(i),objT,exclu1erfield);
            }
        return objs;
    }
    public List<String[]> csvToListStringTab(String csvFilePath)throws Exception{
        FileReader fileReader = new FileReader(csvFilePath);
        CSVReader csvReader = new CSVReader(fileReader);
        List<String[]> csvData = csvReader.readAll();// Utilisez la méthode readAll() pour lire toutes les lignes du fichier CSV dans une liste
        csvReader.close(); 
        return csvData;
    }

    //le file azo , pathupload:asina an'le file csv,classT le andraisana an'le objet

    public T[] csvToTheObjects(MultipartFile file,ServletContext servletContext,String pathupload,Class<T> classT,boolean isByMethodSimple,boolean exclu1erfield)throws Exception{   
        String path=servletContext.getRealPath("");
        path=Paths.get(path).getParent().toString()+pathupload.replace("/", "\\")+file.getOriginalFilename();
        List<String[]> listTab=csvToListStringTab(path);
        String[] headers=listTab.remove(0);
        T[] tabT=null;
        if(isByMethodSimple==true){
            tabT= getObjectsByDataSimple(listTab,classT.getConstructor().newInstance(),exclu1erfield);
        }else{
            tabT= getObjectsByData(headers,listTab,classT.getConstructor().newInstance());
        }
        return tabT;
    }

}

    // import java.lang.reflect.Field;
    // import java.util.Map;
    // import java.util.HashMap;
    
    // public class CSVMapper {
    //     public static void mapToClass(String[] headers, String[] data, Class<?> clazz) throws Exception {
    //         Map<String, Field> fieldMap = new HashMap<>();
    //         for (Field field : clazz.getDeclaredFields()) {
    //             fieldMap.put(field.getName().toLowerCase(), field);
    //         }
    
    //         for (int i = 0; i < headers.length; i++) {
    //             String header = headers[i].toLowerCase();
    //             if (fieldMap.containsKey(header)) {
    //                 Field field = fieldMap.get(header);
    //                 field.setAccessible(true);
    //                 String value = data[i];
    //                 if (field.getType() == int.class) {
    //                     field.setInt(clazz, Integer.parseInt(value));
    //                 } else if (field.getType() == double.class) {
    //                     field.setDouble(clazz, Double.parseDouble(value));
    //                 } else {
    //                     field.set(clazz, value);
    //                 }
    //             }
    //         }
    //     }
    // }