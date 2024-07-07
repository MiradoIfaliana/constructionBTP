package com.projet.eval.models;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Listedevisclient_v extends Motherobj<Listedevisclient_v>  {
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
    String ref_devis ; 
    int id_lieu ; 
    String lieu ; 
    public Listedevisclient_v(){ }
    public Listedevisclient_v(String id_clienttravaux,String id_client,String numero,String id_typemaison,String nomtypemaison,String nomfinition,String datedebut,String datehfin,String datecreation,String totalapaye,String payetotal,String restepaye,String ref_devis,String id_lieu,String lieu)throws Exception{
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
        setRef_devis(ref_devis);
        setId_lieu(id_lieu);
        setLieu(lieu);
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
    public String getRef_devis(){
        return this.ref_devis;
    }
    public void setRef_devis(String ref_devis){
        ref_devis=ref_devis.trim();
        this.ref_devis=ref_devis;
    }
    public int getId_lieu(){
        return this.id_lieu;
    }
    public void setId_lieu(int id_lieu){
        this.id_lieu=id_lieu;
    }
    public void setId_lieu(String id_lieu)throws Exception{
        id_lieu=id_lieu.trim();
        try{
            Integer.valueOf(id_lieu);
        }catch(Exception e){
            throw new MyException("valeur de id_lieu:"+id_lieu+" invalide pour type int");
        }
        setId_lieu(Integer.valueOf(id_lieu));
    }
    public String getLieu(){
        return this.lieu;
    }
    public void setLieu(String lieu){
        lieu=lieu.trim();
        this.lieu=lieu;
    }

    public Listedevisclient_v[] readAllByNumero(Connection connection)throws Exception{
        return readByQueryConvenable( connection,"select * from listedevisclient_v where numero = ? ",new Object[]{this.numero});
    }
    public Listedevisclient_v[] readAllEncoure(Connection connection)throws Exception{
        return readByQueryConvenable( connection,"select * from listedevisclient_v  order by datecreation ASC",null);
    }
    public String getTotalapaye_s(){
        return formatterNb(getTotalapaye());
    }
    public String getPayetotal_s(){
        return formatterNb(getPayetotal());
    }
    public String getRestepaye_s(){
        return formatterNb(getRestepaye());
    }
    public String formatterNb(double d){
        DecimalFormat df= new DecimalFormat("#,###.#####");
        return df.format(Double.valueOf(String.format("%.2f",d).replaceAll(",",".")));
    }
    public double getpourcentagePaye(){
        return ((this.payetotal*100)/this.totalapaye);
    }
    public String getpourcentagePaye_s(){
        return formatterNb(getpourcentagePaye());
    }
    public String getColor(){
        if(this.getpourcentagePaye()<50){
            return "#f55e5e";
        }else if(this.getpourcentagePaye()>50){
            return "#46e946";
        }else{
            return "white";
        }
    }
}
