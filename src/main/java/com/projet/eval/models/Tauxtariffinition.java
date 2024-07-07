package com.projet.eval.models;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Tauxtariffinition extends Motherobj<Tauxtariffinition>  {
    @Id
    int id_tauxtariffinition ; 
    double tauxaugment ; 
    Date datemodif ; 
    int id_typefinition ; 

    public Tauxtariffinition(){ }
    public Tauxtariffinition(String id_tauxtariffinition,String tauxaugment,String datemodif,String id_typefinition)throws Exception{
        setId_tauxtariffinition(id_tauxtariffinition);
        setTauxaugment(tauxaugment);
        setDatemodif(datemodif);
        setId_typefinition(id_typefinition);
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
    public void setTauxaugment(double tauxaugment)throws Exception{
        if(tauxaugment<0){ throw new MyException("taux d'augmentation doit etre superieur ou egal a 0"); }
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
    public void save(Connection connection)throws Exception{
        this.datemodif=Date.valueOf(LocalDate.now());
        this.tauxaugment=(tauxaugment/100.0); //rehefa le mapiditra tanana % no eo
        Typefinition typefinition=new Typefinition();
        typefinition.setId_typefinition(this.id_typefinition);
        typefinition=typefinition.readById(connection);
        if(typefinition==null){ throw new MyException("type finition inconnu"); }
        this.create(connection);
    }
    public void insertBydatacsv(Connection connection)throws Exception{
        Csv_tauxtariffinition[]  csv_tauxtariffinitions=new Csv_tauxtariffinition().read(connection);
        if(csv_tauxtariffinitions==null){ throw new MyException("donnee csv_tauxtariffinition null"); }
        Tauxtariffinition tauxtariffinitiontemp=null;
        for(Csv_tauxtariffinition csv_tauxtariffinition:csv_tauxtariffinitions){
            tauxtariffinitiontemp=new Tauxtariffinition("0", csv_tauxtariffinition.getTaux_finition(), Date.valueOf(LocalDate.now()).toString(), csv_tauxtariffinition.getId_typefinition()+"");
            tauxtariffinitiontemp.setTauxaugment(tauxtariffinitiontemp.getTauxaugment()/100.0) ;
            tauxtariffinitiontemp.create(connection);
        }
    }
}
