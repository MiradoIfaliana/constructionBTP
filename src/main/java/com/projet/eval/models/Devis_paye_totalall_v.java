package com.projet.eval.models;
import com.projet.eval.gno.*;

import java.sql.Connection;
import java.text.DecimalFormat;

import com.projet.eval.exception.*;


public class Devis_paye_totalall_v extends Motherobj<Devis_paye_totalall_v>  {
    double devistotal ; 
    double payetotal ; 

    public Devis_paye_totalall_v(){ }
    public Devis_paye_totalall_v(String devistotal,String payetotal)throws Exception{
        setDevistotal(devistotal);
        setPayetotal(payetotal);
    }
    
    public double getDevistotal(){
        return this.devistotal;
    }
    public String getDevistotal_s(){
        return formatterNb(getDevistotal());
    }
    public void setDevistotal(double devistotal){
        this.devistotal=devistotal;
    }
    public void setDevistotal(String devistotal)throws Exception{
        devistotal=devistotal.trim();
        try{
            Double.valueOf(devistotal);
        }catch(Exception e){
            throw new MyException("valeur de devistotal:"+devistotal+" invalide pour type double");
        }
        setDevistotal(Double.valueOf(devistotal));
    }
    public double getPayetotal(){
        return this.payetotal;
    }
    public String getPayetotal_s(){
        return formatterNb(getPayetotal());
    }
    public void setPayetotal(double payetotal){
        this.payetotal=payetotal;
    }
    public void setPayetotal(String payetotal)throws Exception{
        payetotal=payetotal.trim();
        try{
            Double.valueOf(payetotal);
        }catch(Exception e){
            throw new MyException("valeur de payetotal:"+payetotal+" invalide pour type double");
        }
        setPayetotal(Double.valueOf(payetotal));
    }
    public Devis_paye_totalall_v getOne(Connection connection)throws Exception{
        Devis_paye_totalall_v devis_paye_totalall_v=readOneByQueryConvenable(connection, "select * from devis_paye_totalall_v");
        if(devis_paye_totalall_v==null){
            devis_paye_totalall_v=new Devis_paye_totalall_v("0", "0");
        }
        return devis_paye_totalall_v;
    }
    public String formatterNb(double d){
        DecimalFormat df= new DecimalFormat("#,###.#####");
        return df.format(Double.valueOf(String.format("%.2f",d).replaceAll(",",".")));
    }

}
