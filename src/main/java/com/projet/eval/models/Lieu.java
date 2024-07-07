package com.projet.eval.models;
import com.projet.eval.gno.*;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

import com.projet.eval.exception.*;


public class Lieu extends Motherobj<Lieu>  {
    @Id
    int id_lieu ; 
    String lieu ; 

    public Lieu(){ }
    public Lieu(String id_lieu,String lieu)throws Exception{
        setId_lieu(id_lieu);
        setLieu(lieu);
    }
    
    public int getId_lieu(){
        return this.id_lieu;
    }
    public void setId_lieu(int id_lieu){
        this.id_lieu=id_lieu;
    }
    public void setId_lieu(String id_lieu)throws Exception{
        id_lieu=id_lieu.trim();
        try{
            Integer.valueOf(id_lieu);
        }catch(Exception e){
            throw new MyException("valeur de id_lieu:"+id_lieu+" invalide pour type int");
        }
        setId_lieu(Integer.valueOf(id_lieu));
    }
    public String getLieu(){
        return this.lieu;
    }
    public void setLieu(String lieu){
        lieu=lieu.trim();
        this.lieu=lieu;
    }
    public void insertBydatacsv(Connection connection)throws Exception{
        Csv_lieu[]  csv_lieus=new Csv_lieu().read(connection);
        if(csv_lieus==null){ throw new MyException("donnee csv_lieu null"); }
        Lieu lieutemp=null;
        for(Csv_lieu csv_lieu:csv_lieus){
            lieutemp=new Lieu("0", csv_lieu.getLieu());
            lieutemp.create(connection);
        }
    }

}
