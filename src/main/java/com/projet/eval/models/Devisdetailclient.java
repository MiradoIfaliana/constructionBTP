package com.projet.eval.models;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;

import com.projet.eval.exception.*;


public class Devisdetailclient  {
    int id_travaudetail ; 
    String codetravaud ; 
    String designationd ; 
    String unite ; 
    double quantite ; 
    double pu ; 
    
    

    public Devisdetailclient(){ }
    public Devisdetailclient(String id_travaudetail,String codetravaud,String designationd,String unite,String quantite,String pu)throws Exception{
        setId_travaudetail(id_travaudetail);
        setCodetravaud(codetravaud);
        setDesignationd(designationd);
        setUnite(unite);
        setQuantite(quantite);
        setPu(pu);
        
    }

    public Devisdetailclient(int id_travaudetail, String codetravaud, String designationd, String unite,
            double quantite, double pu) {
        this.id_travaudetail = id_travaudetail;
        this.codetravaud = codetravaud;
        this.designationd = designationd;
        this.unite = unite;
        this.quantite = quantite;
        this.pu = pu;
        
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
    public String getCodetravaud(){
        return this.codetravaud;
    }
    public void setCodetravaud(String codetravaud){
        codetravaud=codetravaud.trim();
        this.codetravaud=codetravaud;
    }
    public String getDesignationd(){
        return this.designationd;
    }
    public void setDesignationd(String designationd){
        designationd=designationd.trim();
        this.designationd=designationd;
    }
    public String getUnite(){
        return this.unite;
    }
    public void setUnite(String unite){
        unite=unite.trim();
        this.unite=unite;
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
    public double getPu(){
        return this.pu;
    }
    public String getPu_s(){
        return formatterNb(this.pu);
    }
    public void setPu(double pu){
        this.pu=pu;
    }
    public void setPu(String pu)throws Exception{
        pu=pu.trim();
        try{
            Double.valueOf(pu);
        }catch(Exception e){
            throw new MyException("valeur de pu:"+pu+" invalide pour type double");
        }
        setPu(Double.valueOf(pu));
    }


    public double getTariftotal(){
        return (this.quantite*this.pu);
    }
    public String getTariftotal_s(){
        return formatterNb(this.getTariftotal());
    }
    public String formatterNb(double d){
        DecimalFormat df= new DecimalFormat("#,###.#####");
        return df.format(Double.valueOf(String.format("%.2f",d).replaceAll(",",".")));
    }
//id_travaudetail | codetravaud |designationd | unite | quantite | pu |nomfinition | tauxaugment | datedebut  | datehfin|datecreation
}
