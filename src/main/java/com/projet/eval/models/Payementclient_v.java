package com.projet.eval.models;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;

import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Payementclient_v extends Motherobj<Payementclient_v>  {
    int id_clienttravaux ; 
    int id_client ; 
    String numero ; 
    int id_typemaison ; 
    String nomtypemaison ; 
    String nomfinition ; 
    Date datedebut ; 
    Timestamp datehfin ; 
    Date datecreation ; 
    double totalapaye ; 
    double payetotal ; 
    double restepaye ; 

    public Payementclient_v(){ }
    public Payementclient_v(String id_clienttravaux,String id_client,String numero,String id_typemaison,String nomtypemaison,String nomfinition,String datedebut,String datehfin,String datecreation,String totalapaye,String payetotal,String restepaye)throws Exception{
        setId_clienttravaux(id_clienttravaux);
        setId_client(id_client);
        setNumero(numero);
        setId_typemaison(id_typemaison);
        setNomtypemaison(nomtypemaison);
        setNomfinition(nomfinition);
        setDatedebut(datedebut);
        setDatehfin(datehfin);
        setDatecreation(datecreation);
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
    public int getId_client(){
        return this.id_client;
    }
    public void setId_client(int id_client){
        this.id_client=id_client;
    }
    public void setId_client(String id_client)throws Exception{
        id_client=id_client.trim();
        try{
            Integer.valueOf(id_client);
        }catch(Exception e){
            throw new MyException("valeur de id_client:"+id_client+" invalide pour type int");
        }
        setId_client(Integer.valueOf(id_client));
    }
    public String getNumero(){
        return this.numero;
    }
    public void setNumero(String numero){
        numero=numero.trim();
        this.numero=numero;
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
    public String getNomtypemaison(){
        return this.nomtypemaison;
    }
    public void setNomtypemaison(String nomtypemaison){
        nomtypemaison=nomtypemaison.trim();
        this.nomtypemaison=nomtypemaison;
    }
    public String getNomfinition(){
        return this.nomfinition;
    }
    public void setNomfinition(String nomfinition){
        nomfinition=nomfinition.trim();
        this.nomfinition=nomfinition;
    }
    public Date getDatedebut(){
        return this.datedebut;
    }
    public void setDatedebut(Date datedebut){
        this.datedebut=datedebut;
    }
    public void setDatedebut(String datedebut)throws Exception{
        datedebut=datedebut.trim();
        try{
            Date.valueOf(datedebut);
        }catch(Exception e){
            throw new MyException("valeur de datedebut:"+datedebut+" invalide pour type Date");
        }
        setDatedebut(Date.valueOf(datedebut));
    }
    public Timestamp getDatehfin(){
        return this.datehfin;
    }
    public void setDatehfin(Timestamp datehfin){
        this.datehfin=datehfin;
    }
    public void setDatehfin(String datehfin)throws Exception{
        datehfin=datehfin.trim().replaceAll("T"," ")+":00";
        datehfin=datehfin.substring(0, 19);
        try{
            Timestamp.valueOf(datehfin);
        }catch(Exception e){
            throw new MyException("valeur de datehfin:"+datehfin+" invalide pour type Timestamp");
        }
        setDatehfin(Timestamp.valueOf(datehfin));
    }
    public Date getDatecreation(){
        return this.datecreation;
    }
    public void setDatecreation(Date datecreation){
        this.datecreation=datecreation;
    }
    public void setDatecreation(String datecreation)throws Exception{
        datecreation=datecreation.trim();
        try{
            Date.valueOf(datecreation);
        }catch(Exception e){
            throw new MyException("valeur de datecreation:"+datecreation+" invalide pour type Date");
        }
        setDatecreation(Date.valueOf(datecreation));
    }
    public double getTotalapaye(){
        return this.totalapaye;
    }
    public String getTotalapaye_s(){
        return formatterNb(getTotalapaye());
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
    public String getPayetotal_s(){
        return formatterNb(getPayetotal());
    }
    public double getRestepaye(){
        return this.restepaye;
    }
    public String getRestepaye_s(){
        return formatterNb(getRestepaye());
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
    public Payementclient_v getById_clienttravaux(Connection connection)throws Exception{
        return readOneByQueryConvenable(connection, "select * from payementclient_v where id_clienttravaux= ?  ", new Object[]{this.id_clienttravaux});
    }
    public String formatterNb(double d){
        DecimalFormat df= new DecimalFormat("#,###.#####");
        return df.format(Double.valueOf(String.format("%.2f",d).replaceAll(",",".")));
    }

}
