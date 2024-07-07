package com.projet.eval.models;
import com.projet.eval.gno.*;

import java.sql.Connection;
import java.text.DecimalFormat;

import com.projet.eval.exception.*;


public class Devisparannee_v extends Motherobj<Devisparannee_v>  {
    int annee ; 
    double montanttotal ; 
    public Devisparannee_v(){ }
    public Devisparannee_v(String annee,String montanttotal)throws Exception{
        setAnnee(annee);
        setMontanttotal(montanttotal);
    }
    
    public Devisparannee_v( int annee, double montanttotal) {
        this.annee = annee;
        this.montanttotal = montanttotal;
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
    public String getMontanttotal_s(){
        return formatterNb(getMontanttotal());
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
    public Devisparannee_v getAllByAnnee(Connection connection)throws Exception{
        Devisparannee_v devisparannee_v=readOneByQueryConvenable(connection, "select sum(montanttotal) as montanttotal from devisparannee_v ", new Object[]{this.annee});
        if(devisparannee_v==null){
            devisparannee_v=new Devisparannee_v(this.annee, 0);
        }
        return devisparannee_v;
    }
    public String formatterNb(double d){
        DecimalFormat df= new DecimalFormat("#,###.#####");
        return df.format(Double.valueOf(String.format("%.2f",d).replaceAll(",",".")));
    }

}
