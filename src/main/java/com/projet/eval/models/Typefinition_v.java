package com.projet.eval.models;
import java.sql.Connection;
import java.sql.Date;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Typefinition_v extends Motherobj<Typefinition_v>  {
    int id_typefinition ; 
    String nomfinition ; 
    String descriptions ; 
    double tauxaugment ; 
    Date datemodif ; 

    public Typefinition_v(){ }
    public Typefinition_v(String id_typefinition,String nomfinition,String descriptions,String tauxaugment,String datemodif)throws Exception{
        setId_typefinition(id_typefinition);
        setNomfinition(nomfinition);
        setDescriptions(descriptions);
        setTauxaugment(tauxaugment);
        setDatemodif(datemodif);
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
    public double getTauxaugment(){
        return this.tauxaugment;
    }
    public void setTauxaugment(double tauxaugment){
        this.tauxaugment=tauxaugment;
    }
    public void setTauxaugment(String tauxaugment)throws Exception{
        tauxaugment=tauxaugment.trim();
        try{
            Double.valueOf(tauxaugment);
        }catch(Exception e){
            throw new MyException("valeur de tauxaugment:"+tauxaugment+" invalide pour type double");
        }
        setTauxaugment(Double.valueOf(tauxaugment));
    }
    public Date getDatemodif(){
        return this.datemodif;
    }
    public void setDatemodif(Date datemodif){
        this.datemodif=datemodif;
    }
    public void setDatemodif(String datemodif)throws Exception{
        datemodif=datemodif.trim();
        try{
            Date.valueOf(datemodif);
        }catch(Exception e){
            throw new MyException("valeur de datemodif:"+datemodif+" invalide pour type Date");
        }
        setDatemodif(Date.valueOf(datemodif));
    }
    public Typefinition_v getById_typefinition(Connection connection)throws Exception{
        return this.readOneByQueryConvenable(connection, " select * from typefinition_v where id_typefinition= ? ", new Object[]{this.id_typefinition});
    }

}
