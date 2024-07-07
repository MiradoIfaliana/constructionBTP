package com.projet.eval.controllers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletContext;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projet.eval.exception.AuthException;
import com.projet.eval.exception.MyException;
import com.projet.eval.models.*;
public class MotherControlleur {
    public void uploadFile(MultipartFile file,ServletContext servletContext,String pathupload){
        if(file.isEmpty()){
            System.out.println("file null");
        }
        try {
            //its maximum permitted size of 1048576 bytes.
            String path=servletContext.getRealPath("");
            path=Paths.get(path).getParent().toString()+pathupload.replace("/", "\\");
            System.out.println("---->"+path+"<----");
            System.out.println(file.getOriginalFilename());
            File destination=new File(path+""+file.getOriginalFilename());
            file.transferTo(destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String toUpperCaseFirst(String str){ return str.toUpperCase().substring(0,1)+str.substring(1,str.length()); }

    public void setFieldByRequest(Object object,HttpServletRequest request)throws Exception{
        String d=null;
        Field[] fields=object.getClass().getDeclaredFields();
        Method method=null;
        for(int i=0;i<fields.length;i++){
          d=request.getParameter(fields[i].getName());
          //System.out.println(fields[i].getName()+"="+d);
          if(d!=null){
            try{
                method=object.getClass().getDeclaredMethod("set"+toUpperCaseFirst(fields[i].getName()),String.class);
                method.invoke(object, d);
            }catch(Exception e){
                if(e.getCause()!=null){
                    if(e.getCause() instanceof MyException){
                        throw (MyException)e.getCause();
                    }else{
                    throw (Exception)e.getCause();
                    }
                    
                }else{
                    throw e;
                }
            }
            //System.out.println(fields[i].getName()+"="+d);
          }
        }    
    }
//---------------------non generaliser
    // public Users_v getUsersOfSession(HttpSession session,Connection connection)throws Exception{
    //     Users_v users=null;
    //     Object id_users=session.getAttribute("id_users");
    //     Object id_profil=session.getAttribute("id_profil");
    //     if(id_users!=null && id_profil!=null){
    //         users=new Users_v();
    //         users.setId_users( (int)id_users );
    //         users.setId_profil( (int)id_profil );
    //         users=users.getByIdAndIdprofil(connection);
    //     }
    //     return users;
    // }

    // public boolean IsUserOnSessionHaveAutorisation(int[] codeprofil,HttpSession session,Connection connection)throws Exception{
    //     Users_v users=getUsersOfSession(session, connection);
    //     if(users==null){ return false;}
    //     for(int i=0;i<codeprofil.length;i++){
    //         if(users.getCode()==codeprofil[i]){ return true; }
    //     }
    //     return false;
    // }

    public void throwIfNoClient(Connection connection,HttpSession session,String complementMsg)throws Exception{
        Client client=new Client();
        if(session.getAttribute("numero")==null){
            throw new AuthException("veuillez vous idntifier en tant que client");
        }else{
            client.setNumero((String)session.getAttribute("numero"));
        }
        client=client.getByNumero(connection);
        if(client==null){
            throw new AuthException("veuillez vous idntifier en tant que client");
        }
    }
    public Client getClient(Connection connection,HttpSession session)throws Exception{
        Client client=new Client();
        if(session.getAttribute("numero")==null){
            throw new AuthException("veuillez vous idntifier en tant que client");
        }else{
            client.setNumero((String)session.getAttribute("numero"));
        }
        client=client.getByNumero(connection);
        if(client==null){
            throw new AuthException("veuillez vous idntifier en tant que client");
        }
        return client;
    }
    public Admin getAdmin(Connection connection,HttpSession session)throws Exception{
        Admin admin=new Admin();
        if(session.getAttribute("id_admin")==null){
            throw new AuthException("veuillez vous idntifier en tant que admin");
        }else{
            admin.setId_admin((String)session.getAttribute("id_admin"));
        }
        admin=admin.readById(connection);
        if(admin==null){
            throw new AuthException("veuillez vous idntifier en tant qu'admin");
        }
        return admin;
    }
    public Admin throwIfNotAdmin(Connection connection,HttpSession session)throws Exception{
        Admin admin=new Admin();
        if(session.getAttribute("id_admin")==null){
            throw new AuthException("veuillez vous idntifier en tant que admin");
        }else{
            admin.setId_admin((String)session.getAttribute("id_admin"));
        }
        admin=admin.readById(connection);
        if(admin==null){
            throw new AuthException("veuillez vous idntifier en tant qu'admin");
        }
        return admin;
    }
//---------------------------------------
    public String[] getTabString_0_n(String nameparameter_n,HttpServletRequest request,int tailleparcour)throws Exception{
        List<String> lst=new ArrayList<>();
        String temp="";
        for(int i=0;i<tailleparcour;i++){
            temp=request.getParameter(nameparameter_n+""+i);
            System.out.println(temp);
            if(temp!=null){
                lst.add(temp+"");
            }
        }
        return lst.toArray(new String[lst.size()]);
    }

    // @GetMapping("/redirect")
    // public String redirection(@RequestParam(name = "page", defaultValue = "") String page,@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
    //     return page;
    // }//
    @PostMapping("/allchamp")
    public String allchamp(@RequestParam(name = "page", defaultValue = "") String page,@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        String[] var={"price","month","time","week","color","newsletter1","newsletter2","newsletter3","prefix","message"};
        for(int i=0;i<var.length;i++){
            System.out.println(var[i]+" : "+request.getParameter(var[i]));
        }
        String[] var1=request.getParameterValues("newsletter1");
        String[] var2=request.getParameterValues("newsletter2");
        String[] var3=request.getParameterValues("newsletter3");
        if(var1!=null){
            for(int i=0;i<var1.length;i++){
                System.out.println(var1[i]+" "+i+" newsletter1");
            }
        }
        if(var2!=null){
            for(int i=0;i<var2.length;i++){
                System.out.println(var2[i]+" "+i+" newsletter2");
            }
        }
        if(var3!=null){
            for(int i=0;i<var3.length;i++){
                System.out.println(var3[i]+" "+i+" newsletter3");
            }
        }
        return page;
    }
}
