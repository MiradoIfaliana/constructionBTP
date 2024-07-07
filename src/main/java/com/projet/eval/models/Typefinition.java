package com.projet.eval.models;
import com.projet.eval.gno.*;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

import com.projet.eval.exception.*;


public class Typefinition extends Motherobj<Typefinition>  {
    @Id
    int id_typefinition ; 
    String nomfinition ; 
    String descriptions ; 

    public Typefinition(){ }
    public Typefinition(String id_typefinition,String nomfinition,String descriptions)throws Exception{
        setId_typefinition(id_typefinition);
        setNomfinition(nomfinition);
        setDescriptions(descriptions);
    }
    
    public int getId_typefinition(){
        return this.id_typefinition;
    }
    public void setId_typefinition(int id_typefinition){
        this.id_typefinition=id_typefinition;
    }
    public void setId_typefinition(String id_typefinition)throws Exception{
        id_typefinition=id_typefinition.trim();
        try{
            Integer.valueOf(id_typefinition);
        }catch(Exception e){
            throw new MyException("valeur de id_typefinition:"+id_typefinition+" invalide pour type int");
        }
        setId_typefinition(Integer.valueOf(id_typefinition));
    }
    public String getNomfinition(){
        return this.nomfinition;
    }
    public void setNomfinition(String nomfinition){
        nomfinition=nomfinition.trim();
        this.nomfinition=nomfinition;
    }
    public String getDescriptions(){
        return this.descriptions;
    }
    public void setDescriptions(String descriptions){
        this.descriptions=descriptions;
    }
    public void insertBydatacsv(Connection connection)throws Exception{
        Csv_typefinition[]  csv_typefinitions=new Csv_typefinition().read(connection);
        if(csv_typefinitions==null){ throw new MyException("donnee csv_typefinition null"); }
        Typefinition typefinitiontemp=null;
        for(Csv_typefinition csv_typefinition:csv_typefinitions){
            typefinitiontemp=new Typefinition("0", csv_typefinition.getFinition(), "finition "+csv_typefinition.getFinition());
            typefinitiontemp.create(connection);
        }
    }
}
