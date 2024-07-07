package com.projet.eval.models;
import com.projet.eval.gno.*;

import java.sql.Connection;

import com.projet.eval.exception.*;


public class Quantitetariftravaux_v extends Motherobj<Quantitetariftravaux_v>  {
    int id_typemaison ; 
    int id_travaudetail ; 
    double quantite ; 
    double tarifunitaire ; 

    public Quantitetariftravaux_v(){ }
    public Quantitetariftravaux_v(String id_typemaison,String id_travaudetail,String quantite,String tarifunitaire)throws Exception{
        setId_typemaison(id_typemaison);
        setId_travaudetail(id_travaudetail);
        setQuantite(quantite);
        setTarifunitaire(tarifunitaire);
    }
    
    public Quantitetariftravaux_v(int id_typemaison, int id_travaudetail, double quantite, double tarifunitaire) {
        this.id_typemaison = id_typemaison;
        this.id_travaudetail = id_travaudetail;
        this.quantite = quantite;
        this.tarifunitaire = tarifunitaire;
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
    public double getQuantite(){
        return this.quantite;
    }
    public void setQuantite(double quantite){
        this.quantite=quantite;
    }
    public void setQuantite(String quantite)throws Exception{
        quantite=quantite.trim();
        try{
            Double.valueOf(quantite);
        }catch(Exception e){
            throw new MyException("valeur de quantite:"+quantite+" invalide pour type double");
        }
        setQuantite(Double.valueOf(quantite));
    }
    public double getTarifunitaire(){
        return this.tarifunitaire;
    }
    public void setTarifunitaire(double tarifunitaire){
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
    public Quantitetariftravaux_v[] getAllById_typemaison(Connection connection)throws Exception{
        return readByQueryConvenable(connection, "select * from quantitetariftravaux_v where id_typemaison= ? ", new Object[]{ this.id_typemaison});
    }

}
