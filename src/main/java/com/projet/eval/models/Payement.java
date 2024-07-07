package com.projet.eval.models;
import java.sql.Connection;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;

import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Payement extends Motherobj<Payement>  {
    @Id
    int id_payement ; 
    double montant ; 
    Date datepaye ; 
    int id_clienttravaux ; 
    String ref_paiement ; 

    public Payement(){ }
    public Payement(String id_payement,String montant,String datepaye,String id_clienttravaux,String ref_paiement)throws Exception{
        setId_payement(id_payement);
        setMontant(montant);
        setDatepaye(datepaye);
        setId_clienttravaux(id_clienttravaux);
        setRef_paiement(ref_paiement);
    }
    
    public int getId_payement(){
        return this.id_payement;
    }
    public void setId_payement(int id_payement){
        this.id_payement=id_payement;
    }
    public void setId_payement(String id_payement)throws Exception{
        id_payement=id_payement.trim();
        try{
            Integer.valueOf(id_payement);
        }catch(Exception e){
            throw new MyException("valeur de id_payement:"+id_payement+" invalide pour type int");
        }
        setId_payement(Integer.valueOf(id_payement));
    }
    public double getMontant(){
        return this.montant;
    }
    public void setMontant(double montant)throws Exception{
        if(montant<=0){ throw new MyException("montant doit etre superieur a 0"); }
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
    public Date getDatepaye(){
        return this.datepaye;
    }
    public void setDatepaye(Date datepaye){
        this.datepaye=datepaye;
    }
    public void setDatepaye(String datepaye)throws Exception{
        datepaye=datepaye.trim();
        try{
            Date.valueOf(datepaye);
        }catch(Exception e){
            throw new MyException("valeur de datepaye:"+datepaye+" invalide pour type Date");
        }
        setDatepaye(Date.valueOf(datepaye));
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
    public void save(Connection connection)throws Exception{
        if(datepaye==null){
            this.datepaye=Date.valueOf(LocalDate.now());
        }
        Payementclient_v payementclient_v=new Payementclient_v();
        payementclient_v.setId_clienttravaux(this.id_clienttravaux);
        payementclient_v=payementclient_v.getById_clienttravaux(connection);
        if(payementclient_v==null){
            throw new MyException("impossible de faire la paye, devis inconnu");
        }
        double restepaye=payementclient_v.getRestepaye();
        if(restepaye==0){ throw new MyException("deja tous paye"); }
        else if(restepaye<this.montant){
            throw new MyException("montant("+formatterNb(this.montant)+") depasse le reste a payer("+formatterNb(restepaye)+") pour ref_paiement="+this.getRef_paiement());
        }
        this.create(connection);
    }
    public String getMontant_s(){
        return formatterNb(getMontant());
    }
    public String formatterNb(double d){
        DecimalFormat df= new DecimalFormat("#,###.#####");
        return df.format(Double.valueOf(String.format("%.2f",d).replaceAll(",",".")));
    }
    public void insertBydatacsv(Connection connection)throws Exception{
        Csv_payement[]  csv_payements=new Csv_payement().read(connection);
        if(csv_payements==null){ throw new MyException("donnee csv_payement null"); }
        Payement payementtemp=null;
        Payement payementtem2=null;
        for(Csv_payement csv_payement:csv_payements){
            payementtemp=new Payement("0", csv_payement.getMontant(), csv_payement.getDate_paiement(), csv_payement.getId_clienttravaux()+"", csv_payement.getRef_paiement());
            //verification du ref_paiement existant
            payementtem2=new Payement();
            payementtem2.setRef_paiement(payementtemp.getRef_paiement());
            payementtem2=payementtem2.getByRef_payement(connection); 
            //---------
            if(payementtem2==null){ // raha mbola tsy nisy
                payementtemp.save(connection); //save sans commit le io
            }else{
                System.out.println("existant="+payementtemp.getRef_paiement());
            }
            
        }
    }

    public Payement[] getById_clienttravaux(Connection connection)throws Exception{
        return this.readByQueryConvenable(connection, "select * from payement where id_clienttravaux= ? order by datepaye ASC",new Object[]{ this.getId_clienttravaux() });
    }
    public Payement getByRef_payement(Connection connection)throws Exception{
        return readOneByQueryConvenable(connection, "select * from payement where ref_paiement= ? " , new Object[]{this.getRef_paiement()});
    }
}
