package com.projet.eval.models;
import com.projet.eval.gno.*;

import java.sql.Connection;
import java.text.DecimalFormat;

import com.projet.eval.exception.*;


public class Devisparmoisparannee_v extends Motherobj<Devisparmoisparannee_v>  {
    int mois ; 
    int annee ; 
    double montanttotal ; 

    public Devisparmoisparannee_v(){ }
    public Devisparmoisparannee_v(String mois,String annee,String montanttotal)throws Exception{
        setMois(mois);
        setAnnee(annee);
        setMontanttotal(montanttotal);
    }
    
    public Devisparmoisparannee_v(int mois, int annee, double montanttotal) {
        this.mois = mois;
        this.annee = annee;
        this.montanttotal = montanttotal;
    }
    public int getMois(){
        return this.mois;
    }
    public void setMois(int mois){
        this.mois=mois;
    }
    public String getMois_s(){
        String[] mois_s={"Janv","Fev","Mars","Avr","Mais","Juin","Jul","Aout","Sept","Oct","Nov","Dec"};
        return mois_s[this.mois-1];
    }
    public void setMois(String mois)throws Exception{
        mois=mois.trim();
        try{
            Integer.valueOf(mois);
        }catch(Exception e){
            throw new MyException("valeur de mois:"+mois+" invalide pour type int");
        }
        setMois(Integer.valueOf(mois));
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
    public double getMontanttotal(){
        return this.montanttotal;
    }
    public void setMontanttotal(double montanttotal){
        this.montanttotal=montanttotal;
    }
    public void setMontanttotal(String montanttotal)throws Exception{
        montanttotal=montanttotal.trim();
        try{
            Double.valueOf(montanttotal);
        }catch(Exception e){
            throw new MyException("valeur de montanttotal:"+montanttotal+" invalide pour type double");
        }
        setMontanttotal(Double.valueOf(montanttotal));
    }
    public Devisparmoisparannee_v[] getAllByAnnee(Connection connection)throws Exception{
        Devisparmoisparannee_v[] devisparmoisparannee_vs=readByQueryConvenable(connection, "select * from devisparmoisparannee_v where annee = ? ", new Object[]{this.annee});
        if(devisparmoisparannee_vs==null){
            devisparmoisparannee_vs=new Devisparmoisparannee_v[12];
            for(int i=0;i<12;i++){
                devisparmoisparannee_vs[i]=new Devisparmoisparannee_v(i+1,this.annee, 0);
            }
        }
        return devisparmoisparannee_vs;
    }
    public String getMontanttotal_s(){
        return formatterNb(getMontanttotal());
    }
    public String formatterNb(double d){
        DecimalFormat df= new DecimalFormat("#,###.#####");
        return df.format(Double.valueOf(String.format("%.2f",d).replaceAll(",",".")));
    }
}
