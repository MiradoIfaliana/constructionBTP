package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Csv_payement extends Motherobj<Csv_payement>  {
    int id_clienttravaux ; 
    String ref_paiement ; 
    String date_paiement ; 
    String montant ; 

    public Csv_payement(){ }
    public Csv_payement(String id_clienttravaux,String ref_paiement,String date_paiement,String montant)throws Exception{
        setId_clienttravaux(id_clienttravaux);
        setRef_paiement(ref_paiement);
        setDate_paiement(date_paiement);
        setMontant(montant);
    }
    
    public int getId_clienttravaux(){
        return this.id_clienttravaux;
    }
    public void setId_clienttravaux(int id_clienttravaux){
        this.id_clienttravaux=id_clienttravaux;
    }
    public void setId_clienttravaux(String id_clienttravaux)throws Exception{
        id_clienttravaux=id_clienttravaux.trim();
        try{
            Integer.valueOf(id_clienttravaux);
        }catch(Exception e){
            throw new MyException("valeur de id_clienttravaux:"+id_clienttravaux+" invalide pour type int");
        }
        setId_clienttravaux(Integer.valueOf(id_clienttravaux));
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
        date_paiement=date_paiement.trim();
        this.date_paiement=date_paiement;
    }
    public String getMontant(){
        return this.montant;
    }
    public void setMontant(String montant){
        montant=montant.trim();
        this.montant=montant;
    }

}
