package com.projet.eval.models;
import java.sql.Connection;
import java.sql.Date;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Lasttauxtariffinition_v extends Motherobj<Lasttauxtariffinition_v>  {
    int id_tauxtariffinition ; 
    double tauxaugment ; 
    int id_typefinition ; 
    Date datemodif ; 

    public Lasttauxtariffinition_v(){ }
    public Lasttauxtariffinition_v(String id_tauxtariffinition,String tauxaugment,String id_typefinition,String datemodif)throws Exception{
        setId_tauxtariffinition(id_tauxtariffinition);
        setTauxaugment(tauxaugment);
        setId_typefinition(id_typefinition);
        setDatemodif(datemodif);
    }
    
    public int getId_tauxtariffinition(){
        return this.id_tauxtariffinition;
    }
    public void setId_tauxtariffinition(int id_tauxtariffinition){
        this.id_tauxtariffinition=id_tauxtariffinition;
    }
    public void setId_tauxtariffinition(String id_tauxtariffinition)throws Exception{
        id_tauxtariffinition=id_tauxtariffinition.trim();
        try{
            Integer.valueOf(id_tauxtariffinition);
        }catch(Exception e){
            throw new MyException("valeur de id_tauxtariffinition:"+id_tauxtariffinition+" invalide pour type int");
        }
        setId_tauxtariffinition(Integer.valueOf(id_tauxtariffinition));
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
    public Lasttauxtariffinition_v getById_typefinition(Connection connection)throws Exception{
        return  this.readOneByQueryConvenable(connection,"select * from lasttauxtariffinition_v where id_typefinition = ?  ", new Object[]{this.id_typefinition});
    }

}
