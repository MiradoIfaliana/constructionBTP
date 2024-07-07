package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Payeannee_v extends Motherobj<Payeannee_v>  {
    double montant ; 
    int annee ; 

    public Payeannee_v(){ }
    public Payeannee_v(String montant,String annee)throws Exception{
        setMontant(montant);
        setAnnee(annee);
    }
    
    public double getMontant(){
        return this.montant;
    }
    public void setMontant(double montant){
        this.montant=montant;
    }
    public void setMontant(String montant)throws Exception{
        montant=montant.trim();
        try{
            Double.valueOf(montant);
        }catch(Exception e){
            throw new MyException("valeur de montant:"+montant+" invalide pour type double");
        }
        setMontant(Double.valueOf(montant));
    }
    public int getAnnee(){
        return this.annee;
    }
    public void setAnnee(int annee){
        this.annee=annee;
    }
    public void setAnnee(String annee)throws Exception{
        annee=annee.trim();
        try{
            Integer.valueOf(annee);
        }catch(Exception e){
            throw new MyException("valeur de annee:"+annee+" invalide pour type int");
        }
        setAnnee(Integer.valueOf(annee));
    }

}
