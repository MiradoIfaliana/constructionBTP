package com.projet.eval.models;
import com.projet.eval.gno.*;

import java.sql.Connection;
import java.text.DecimalFormat;

import com.projet.eval.exception.*;


public class Detailpaye_v extends Motherobj<Detailpaye_v>  {
    int id_clienttravaux ; 
    double totalapaye ; 
    double payetotal ; 
    double restepaye ; 

    public Detailpaye_v(){ }
    public Detailpaye_v(String id_clienttravaux,String totalapaye,String payetotal,String restepaye)throws Exception{
        setId_clienttravaux(id_clienttravaux);
        setTotalapaye(totalapaye);
        setPayetotal(payetotal);
        setRestepaye(restepaye);
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
    public double getTotalapaye(){
        return this.totalapaye;
    }
    public void setTotalapaye(double totalapaye){
        this.totalapaye=totalapaye;
    }
    public void setTotalapaye(String totalapaye)throws Exception{
        totalapaye=totalapaye.trim();
        try{
            Double.valueOf(totalapaye);
        }catch(Exception e){
            throw new MyException("valeur de totalapaye:"+totalapaye+" invalide pour type double");
        }
        setTotalapaye(Double.valueOf(totalapaye));
    }
    public double getPayetotal(){
        return this.payetotal;
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
    public double getRestepaye(){
        return this.restepaye;
    }
    public void setRestepaye(double restepaye){
        this.restepaye=restepaye;
    }
    public void setRestepaye(String restepaye)throws Exception{
        restepaye=restepaye.trim();
        try{
            Double.valueOf(restepaye);
        }catch(Exception e){
            throw new MyException("valeur de restepaye:"+restepaye+" invalide pour type double");
        }
        setRestepaye(Double.valueOf(restepaye));
    }
    public Detailpaye_v getById_clienttravaux(Connection connection)throws Exception{
        return this.readOneByQueryConvenable(connection, "select * from detailpaye_v where id_clienttravaux=?",new Object[]{ this.getId_clienttravaux() });
    }
    public String formatterNb(double d){
        DecimalFormat df= new DecimalFormat("#,###.#####");
        return df.format(Double.valueOf(String.format("%.2f",d).replaceAll(",",".")));
    }

}
