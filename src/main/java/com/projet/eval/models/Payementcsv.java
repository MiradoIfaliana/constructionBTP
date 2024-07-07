package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Payementcsv extends Motherobj<Payementcsv>  {
    @Id
    int id_payementcsv ; 
    String ref_devis ; 
    String ref_paiement ; 
    String date_paiement ; 
    String montant ; 

    public Payementcsv(){ }
    public Payementcsv(String id_payementcsv,String ref_devis,String ref_paiement,String date_paiement,String montant)throws Exception{
        setId_payementcsv(id_payementcsv);
        setRef_devis(ref_devis);
        setRef_paiement(ref_paiement);
        setDate_paiement(date_paiement);
        setMontant(montant);
    }
    
    public int getId_payementcsv(){
        return this.id_payementcsv;
    }
    public void setId_payementcsv(int id_payementcsv){
        this.id_payementcsv=id_payementcsv;
    }
    public void setId_payementcsv(String id_payementcsv)throws Exception{
        id_payementcsv=id_payementcsv.trim();
        try{
            Integer.valueOf(id_payementcsv);
        }catch(Exception e){
            throw new MyException("valeur de id_payementcsv:"+id_payementcsv+" invalide pour type int");
        }
        setId_payementcsv(Integer.valueOf(id_payementcsv));
    }
    public String getRef_devis(){
        return this.ref_devis;
    }
    public void setRef_devis(String ref_devis){
        ref_devis=ref_devis.trim();
        this.ref_devis=ref_devis;
    }
    public String getRef_paiement(){
        return this.ref_paiement;
    }
    public void setRef_paiement(String ref_paiement){
        ref_paiement=ref_paiement.trim();
        this.ref_paiement=ref_paiement;

    }
    public String getDate_paiement(){
        return this.date_paiement;
    }
    public void setDate_paiement(String date_paiement){
        date_paiement=date_paiement.trim().replaceAll("/", "-");
        String [] jmy=date_paiement.split("-");
        this.date_paiement=jmy[2]+"-"+jmy[1]+"-"+jmy[0];
    }
    public String getMontant(){
        return this.montant;
    }
    public void setMontant(String montant){
        montant=montant.trim().replaceAll(",", ".");
        this.montant=montant;
    }
    

}
