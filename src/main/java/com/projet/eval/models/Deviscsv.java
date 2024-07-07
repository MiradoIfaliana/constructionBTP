package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Deviscsv extends Motherobj<Deviscsv>  {
    @Id
    int id_deviscsv ; 
    String client ; 
    String ref_devis ; 
    String type_maison ; 
    String finition ; 
    String taux_finition ; 
    String date_devis ; 
    String date_debut ; 
    String lieu ; 

    public Deviscsv(){ }
    public Deviscsv(String id_deviscsv,String client,String ref_devis,String type_maison,String finition,String taux_finition,String date_devis,String date_debut,String lieu)throws Exception{
        setId_deviscsv(id_deviscsv);
        setClient(client);
        setRef_devis(ref_devis);
        setType_maison(type_maison);
        setFinition(finition);
        setTaux_finition(taux_finition);
        setDate_devis(date_devis);
        setDate_debut(date_debut);
        setLieu(lieu);
    }
    
    public int getId_deviscsv(){
        return this.id_deviscsv;
    }
    public void setId_deviscsv(int id_deviscsv){
        this.id_deviscsv=id_deviscsv;
    }
    public void setId_deviscsv(String id_deviscsv)throws Exception{
        id_deviscsv=id_deviscsv.trim();
        try{
            Integer.valueOf(id_deviscsv);
        }catch(Exception e){
            throw new MyException("valeur de id_deviscsv:"+id_deviscsv+" invalide pour type int");
        }
        setId_deviscsv(Integer.valueOf(id_deviscsv));
    }
    public String getClient(){
        return this.client;
    }
    public void setClient(String client){
        client=client.trim();
        this.client=client;
    }
    public String getRef_devis(){
        return this.ref_devis;
    }
    public void setRef_devis(String ref_devis){
        ref_devis=ref_devis.trim();
        this.ref_devis=ref_devis;
    }
    public String getType_maison(){
        return this.type_maison;
    }
    public void setType_maison(String type_maison){
        type_maison=type_maison.trim();
        this.type_maison=type_maison;
    }
    public String getFinition(){
        return this.finition;
    }
    public void setFinition(String finition){
        finition=finition.trim();
        this.finition=finition;
    }
    public String getTaux_finition(){
        return this.taux_finition;
    }
    public void setTaux_finition(String taux_finition){
        taux_finition=taux_finition.trim().replaceAll("%","").replaceAll(",",".");
        this.taux_finition=taux_finition;
    }
    public String getDate_devis(){
        return this.date_devis;
    }
    public void setDate_devis(String date_devis){
        date_devis=date_devis.trim().replaceAll("/", "-");
        String [] jmy=date_devis.split("-");
        this.date_devis=jmy[2]+"-"+jmy[1]+"-"+jmy[0];
    }
    public String getDate_debut(){
        return this.date_debut;
    }
    public void setDate_debut(String date_debut){
        date_debut=date_debut.trim().replaceAll("/", "-");
        String [] jmy=date_debut.split("-");
        this.date_debut=jmy[2]+"-"+jmy[1]+"-"+jmy[0];
    }
    public String getLieu(){
        return this.lieu;
    }
    public void setLieu(String lieu){
        lieu=lieu.trim();
        this.lieu=lieu;
    }

}
