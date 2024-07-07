package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Csv_typemaison extends Motherobj<Csv_typemaison>  {
    String type_maison ; 
    String descriptions ; 
    String surface ; 
    String duree_travaux ; 

    public Csv_typemaison(){ }
    public Csv_typemaison(String type_maison,String descriptions,String surface,String duree_travaux)throws Exception{
        setType_maison(type_maison);
        setDescriptions(descriptions);
        setSurface(surface);
        setDuree_travaux(duree_travaux);
    }
    
    public String getType_maison(){
        return this.type_maison;
    }
    public void setType_maison(String type_maison){
        type_maison=type_maison.trim();
        this.type_maison=type_maison;
    }
    public String getDescriptions(){
        return this.descriptions;
    }
    public void setDescriptions(String descriptions){
        descriptions=descriptions.trim();
        this.descriptions=descriptions;
    }
    public String getSurface(){
        return this.surface;
    }
    public void setSurface(String surface){
        surface=surface.trim();
        this.surface=surface;
    }
    public String getDuree_travaux(){
        return this.duree_travaux;
    }
    public void setDuree_travaux(String duree_travaux){
        duree_travaux=duree_travaux.trim();
        this.duree_travaux=duree_travaux;
    }

}
