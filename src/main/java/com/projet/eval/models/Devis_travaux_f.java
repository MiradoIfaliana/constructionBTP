package com.projet.eval.models;
import java.sql.Date;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Devis_travaux_f extends Motherobj<Devis_travaux>  {
    @Id
    int id_devis_travaux ; 
    double quantite ; 
    int id_travaudetail ; 
    int id_typemaison ; 
    Date datequantite ; 

    public Devis_travaux_f(){ }
    public Devis_travaux_f(String id_devis_travaux,String quantite,String id_travaudetail,String id_typemaison,String datequantite)throws Exception{
        setId_devis_travaux(id_devis_travaux);
        setQuantite(quantite);
        setId_travaudetail(id_travaudetail);
        setId_typemaison(id_typemaison);
        setDatequantite(datequantite);
    }
    
    public int getId_devis_travaux(){
        return this.id_devis_travaux;
    }
    public void setId_devis_travaux(int id_devis_travaux){
        this.id_devis_travaux=id_devis_travaux;
    }
    public void setId_devis_travaux(String id_devis_travaux)throws Exception{
        id_devis_travaux=id_devis_travaux.trim();
        try{
            Integer.valueOf(id_devis_travaux);
        }catch(Exception e){
            throw new MyException("valeur de id_devis_travaux:"+id_devis_travaux+" invalide pour type int");
        }
        setId_devis_travaux(Integer.valueOf(id_devis_travaux));
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
    public Date getDatequantite(){
        return this.datequantite;
    }
    public void setDatequantite(Date datequantite){
        this.datequantite=datequantite;
    }
    public void setDatequantite(String datequantite)throws Exception{
        datequantite=datequantite.trim();
        try{
            Date.valueOf(datequantite);
        }catch(Exception e){
            throw new MyException("valeur de datequantite:"+datequantite+" invalide pour type Date");
        }
        setDatequantite(Date.valueOf(datequantite));
    }
    

}
