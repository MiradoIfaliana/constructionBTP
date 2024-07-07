package com.projet.eval.models;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Tariftravaux extends Motherobj<Tariftravaux>  {
    @Id
    int id_tariftravaux ; 
    double tarifunitaire ; 
    int id_travaudetail ; 
    Date datetarif ; 

    public Tariftravaux(){ }
    public Tariftravaux(String id_tariftravaux,String tarifunitaire,String id_travaudetail,String datetarif)throws Exception{
        setId_tariftravaux(id_tariftravaux);
        setTarifunitaire(tarifunitaire);
        setId_travaudetail(id_travaudetail);
        setDatetarif(datetarif);
    }
    
    public int getId_tariftravaux(){
        return this.id_tariftravaux;
    }
    public void setId_tariftravaux(int id_tariftravaux){
        this.id_tariftravaux=id_tariftravaux;
    }
    public void setId_tariftravaux(String id_tariftravaux)throws Exception{
        id_tariftravaux=id_tariftravaux.trim();
        try{
            Integer.valueOf(id_tariftravaux);
        }catch(Exception e){
            throw new MyException("valeur de id_tariftravaux:"+id_tariftravaux+" invalide pour type int");
        }
        setId_tariftravaux(Integer.valueOf(id_tariftravaux));
    }
    public double getTarifunitaire(){
        return this.tarifunitaire;
    }
    public void setTarifunitaire(double tarifunitaire)throws Exception{
        if(tarifunitaire<=0){ throw new MyException("tarif unitaire doit etre superieur a 0"); }
        this.tarifunitaire=tarifunitaire;
    }
    public void setTarifunitaire(String tarifunitaire)throws Exception{
        tarifunitaire=tarifunitaire.trim();
        try{
            Double.valueOf(tarifunitaire);
        }catch(Exception e){
            throw new MyException("valeur de tarifunitaire:"+tarifunitaire+" invalide pour type double");
        }
        setTarifunitaire(Double.valueOf(tarifunitaire));
    }
    public int getId_travaudetail(){
        return this.id_travaudetail;
    }
    public void setId_travaudetail(int id_travaudetail){
        this.id_travaudetail=id_travaudetail;
    }
    public void setId_travaudetail(String id_travaudetail)throws Exception{
        id_travaudetail=id_travaudetail.trim();
        try{
            Integer.valueOf(id_travaudetail);
        }catch(Exception e){
            throw new MyException("valeur de id_travaudetail:"+id_travaudetail+" invalide pour type int");
        }
        setId_travaudetail(Integer.valueOf(id_travaudetail));
    }
    public Date getDatetarif(){
        return this.datetarif;
    }
    public void setDatetarif(Date datetarif){
        this.datetarif=datetarif;
    }
    public void setDatetarif(String datetarif)throws Exception{
        datetarif=datetarif.trim();
        try{
            Date.valueOf(datetarif);
        }catch(Exception e){
            throw new MyException("valeur de datetarif:"+datetarif+" invalide pour type Date");
        }
        setDatetarif(Date.valueOf(datetarif));
    }
    public void save(Connection connection)throws Exception{
        this.datetarif=Date.valueOf(LocalDate.now());
        Traveaudetail traveaudetail=new Traveaudetail();
        traveaudetail.setId_travaudetail(this.id_travaudetail);
        traveaudetail=traveaudetail.readById(connection);
        if(traveaudetail==null){ throw new MyException("travaux detail inconnu"); }
        this.create(connection);
    }
    public void insertBydatacsv(Connection connection)throws Exception{
        Csv_tariftravaux[]  csv_tariftravauxs=new Csv_tariftravaux().read(connection);
        if(csv_tariftravauxs==null){ throw new MyException("donnee csv_tariftravaux null"); }
        Tariftravaux tariftravauxtemp=null;
        for(Csv_tariftravaux csv_tariftravaux:csv_tariftravauxs){
            tariftravauxtemp=new Tariftravaux("0", csv_tariftravaux.getPrix_unitaire(), csv_tariftravaux.getId_travaudetail()+"", Date.valueOf(LocalDate.now()).toString());
            tariftravauxtemp.create(connection);
        }
    }

}
