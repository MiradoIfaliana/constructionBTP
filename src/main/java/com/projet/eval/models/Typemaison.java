package com.projet.eval.models;
import com.projet.eval.gno.*;

import java.sql.Connection;

import com.projet.eval.exception.*;


public class Typemaison extends Motherobj<Typemaison>  {
    @Id
    int id_typemaison ; 
    String nomtypemaison ; 
    String descriptions ; 
    double duree_j ; 
    double surface ; 

    public Typemaison(){ }
    public Typemaison(String id_typemaison,String nomtypemaison,String descriptions,String duree_j,String surface)throws Exception{
        setId_typemaison(id_typemaison);
        setNomtypemaison(nomtypemaison);
        setDescriptions(descriptions);
        setDuree_j(duree_j);
        setSurface(surface);
    }
    
    public int getId_typemaison(){
        return this.id_typemaison;
    }
    public void setId_typemaison(int id_typemaison){
        this.id_typemaison=id_typemaison;
    }
    public void setId_typemaison(String id_typemaison)throws Exception{
        id_typemaison=id_typemaison.trim();
        try{
            Integer.valueOf(id_typemaison);
        }catch(Exception e){
            throw new MyException("valeur de id_typemaison:"+id_typemaison+" invalide pour type int");
        }
        setId_typemaison(Integer.valueOf(id_typemaison));
    }
    public String getNomtypemaison(){
        return this.nomtypemaison;
    }
    public void setNomtypemaison(String nomtypemaison){
        nomtypemaison=nomtypemaison.trim();
        this.nomtypemaison=nomtypemaison;
    }
    public String getDescriptions(){
        return this.descriptions;
    }
    public void setDescriptions(String descriptions){
        this.descriptions=descriptions;
    }
    public double getDuree_j(){
        return this.duree_j;
    }
    public void setDuree_j(double duree_j){
        this.duree_j=duree_j;
    }
    public void setDuree_j(String duree_j)throws Exception{
        duree_j=duree_j.trim();
        try{
            Double.valueOf(duree_j);
        }catch(Exception e){
            throw new MyException("valeur de duree_j:"+duree_j+" invalide pour type double");
        }
        setDuree_j(Double.valueOf(duree_j));
    }
    public double getSurface(){
        return this.surface;
    }
    public void setSurface(double surface){
        this.surface=surface;
    }
    public void setSurface(String surface)throws Exception{
        surface=surface.trim();
        try{
            Double.valueOf(surface);
        }catch(Exception e){
            throw new MyException("valeur de surface:"+surface+" invalide pour type double");
        }
        setSurface(Double.valueOf(surface));
    }

    public void insertBydatacsv(Connection connection)throws Exception{
        Csv_typemaison[]  csv_typemaisons=new Csv_typemaison().read(connection);
        if(csv_typemaisons==null){ throw new MyException("donnee csv_typemaison null"); }
        Typemaison typemaisontemp=null;
        for(Csv_typemaison csv_typemaison:csv_typemaisons){
            typemaisontemp=new Typemaison("0", csv_typemaison.getType_maison(), csv_typemaison.getDescriptions(), csv_typemaison.getDuree_travaux(), csv_typemaison.getSurface());
            typemaisontemp.create(connection);
        }

    }

}
