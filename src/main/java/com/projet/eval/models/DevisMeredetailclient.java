package com.projet.eval.models;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class DevisMeredetailclient {
    int id_clienttravaux ; 
    int id_client ; 
    String numero ; 
    int id_typemaison ; 
    String nomtypemaison ; 
    int id_travaux ; 
    String codetravau ; 
    String designation ; 
    Date datedebut ; 
    Timestamp datehfin ; 
    Date datecreation ; 
    double tauxaugment ; 
    String nomfinition ; 
    List<Devisdetailclient> devisdetailclients;

    public DevisMeredetailclient(){ }
    public DevisMeredetailclient(String id_clienttravaux,String id_client,String numero,String id_typemaison,String nomtypemaison,String id_travaux,String codetravau,String designation,String datedebut,String datehfin,String datecreation,String tauxaugment, String nomfinition)throws Exception{
        setId_clienttravaux(id_clienttravaux);
        setId_client(id_client);
        setNumero(numero);
        setId_typemaison(id_typemaison);
        setNomtypemaison(nomtypemaison);
        setId_travaux(id_travaux);
        setCodetravau(codetravau);
        setDesignation(designation);
        setDatedebut(datedebut);
        setDatehfin(datehfin);
        setDatecreation(datecreation);
        setTauxaugment(tauxaugment);
        setNomfinition(nomfinition);
    }
    
    public DevisMeredetailclient(int id_clienttravaux, int id_client, String numero, int id_typemaison,
            String nomtypemaison, int id_travaux, String codetravau, String designation, Date datedebut,
            Timestamp datehfin, Date datecreation,double tauxaugment, String nomfinition ) {
        this.id_clienttravaux = id_clienttravaux;
        this.id_client = id_client;
        this.numero = numero;
        this.id_typemaison = id_typemaison;
        this.nomtypemaison = nomtypemaison;
        this.id_travaux = id_travaux;
        this.codetravau = codetravau;
        this.designation = designation;
        this.datedebut = datedebut;
        this.datehfin = datehfin;
        this.datecreation = datecreation;
        this.tauxaugment=tauxaugment;
        this.nomfinition = nomfinition;
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
    public int getId_travaux(){
        return this.id_travaux;
    }
    public void setId_travaux(int id_travaux){
        this.id_travaux=id_travaux;
    }
    public void setId_travaux(String id_travaux)throws Exception{
        id_travaux=id_travaux.trim();
        try{
            Integer.valueOf(id_travaux);
        }catch(Exception e){
            throw new MyException("valeur de id_travaux:"+id_travaux+" invalide pour type int");
        }
        setId_travaux(Integer.valueOf(id_travaux));
    }
    public String getCodetravau(){
        return this.codetravau;
    }
    public void setCodetravau(String codetravau){
        codetravau=codetravau.trim();
        this.codetravau=codetravau;
    }
    public String getDesignation(){
        return this.designation;
    }
    public void setDesignation(String designation){
        designation=designation.trim();
        this.designation=designation;
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
    public List<Devisdetailclient> getDevisdetailclients() {
        return devisdetailclients;
    }
    public void setDevisdetailclients(List<Devisdetailclient> devisdetailclients) {
        this.devisdetailclients = devisdetailclients;
    }
    public void setTauxaugment(String tauxaugment)throws Exception{
        tauxaugment=tauxaugment.trim();
        try{
            Double.valueOf(tauxaugment);
        }catch(Exception e){
            throw new MyException("valeur de tauxaugment:"+tauxaugment+" invalide pour type double");
        }
        setTauxaugment(Double.valueOf(tauxaugment));
    }
    public void addDevisdetailclient(Devisdetailclient de)throws Exception{
        if(devisdetailclients==null){ devisdetailclients=new ArrayList<Devisdetailclient>(); }
        devisdetailclients.add(de);
    }
    public double getTauxaugment(){
        return this.tauxaugment;
    }
    public void setTauxaugment(double tauxaugment){
        this.tauxaugment=tauxaugment;
    }
    public String getNomfinition(){
        return this.nomfinition;
    }
    public void setNomfinition(String nomfinition){
        nomfinition=nomfinition.trim();
        this.nomfinition=nomfinition;
    }
    public DevisMeredetailclient[] getCreerByOneId_clienttravaux(Devisdetailclient_v[] devisdetailclient_vs)throws Exception{
        Devisdetailclient_v[] devdc_vs=devisdetailclient_vs;
        int idnow=0;
        DevisMeredetailclient devisMTemp=null;
        Devisdetailclient devisdtCtemp=null;
        List<DevisMeredetailclient> lstdevM=new ArrayList<DevisMeredetailclient>();
        if(devisdetailclient_vs!=null){
            for(int i=0;i<devdc_vs.length;i++){
                if(i==0){ 
                    idnow=devdc_vs[i].getId_travaux();
                    devisMTemp=new DevisMeredetailclient(devdc_vs[i].getId_clienttravaux(),devdc_vs[i].getId_client(),devdc_vs[i].getNumero(),devdc_vs[i].getId_typemaison(),devdc_vs[i].getNomtypemaison(),devdc_vs[i].getId_travaux(),devdc_vs[i].getCodetravau(),devdc_vs[i].getDesignation(),devdc_vs[i].getDatedebut(),devdc_vs[i].getDatehfin(),devdc_vs[i].getDatecreation(),devdc_vs[i].getTauxaugment(),devdc_vs[i].getNomfinition());
                    devisMTemp.setDevisdetailclients(new ArrayList<Devisdetailclient>());
                }
                if(devdc_vs[i].getId_travaux()==idnow){
                    devisMTemp.addDevisdetailclient(new Devisdetailclient(devdc_vs[i].getId_travaudetail(),devdc_vs[i].getCodetravaud(),devdc_vs[i].getDesignationd(),devdc_vs[i].getUnite(),devdc_vs[i].getQuantite(),devdc_vs[i].getPu()));
                }else{
                    lstdevM.add(devisMTemp);
                    devisMTemp=new DevisMeredetailclient(devdc_vs[i].getId_clienttravaux(),devdc_vs[i].getId_client(),devdc_vs[i].getNumero(),devdc_vs[i].getId_typemaison(),devdc_vs[i].getNomtypemaison(),devdc_vs[i].getId_travaux(),devdc_vs[i].getCodetravau(),devdc_vs[i].getDesignation(),devdc_vs[i].getDatedebut(),devdc_vs[i].getDatehfin(),devdc_vs[i].getDatecreation(),devdc_vs[i].getTauxaugment(),devdc_vs[i].getNomfinition());
                    devisMTemp.setDevisdetailclients(new ArrayList<Devisdetailclient>());
                    devisMTemp.addDevisdetailclient(new Devisdetailclient(devdc_vs[i].getId_travaudetail(),devdc_vs[i].getCodetravaud(),devdc_vs[i].getDesignationd(),devdc_vs[i].getUnite(),devdc_vs[i].getQuantite(),devdc_vs[i].getPu()));
                    idnow=devdc_vs[i].getId_travaux();
                }
            }
            if(devisMTemp!=null){
                lstdevM.add(devisMTemp);
            }
        }
        if(lstdevM.size()==0){ return null; }
        return lstdevM.toArray(new DevisMeredetailclient[lstdevM.size()] );
    }
    public DevisMeredetailclient[] getById_clienttravaux(Connection connection)throws Exception{
        Devisdetailclient_v devisdetailclient_v=new Devisdetailclient_v();
        devisdetailclient_v.setId_clienttravaux(this.id_clienttravaux);
        Devisdetailclient_v[] devisdetailclient_vs=devisdetailclient_v.getById_clienttravaux(connection);
        return getCreerByOneId_clienttravaux(devisdetailclient_vs);
    }
    public double getTotal(){
        double total=0;
        for(int i=0;i<this.devisdetailclients.size();i++){
            total+=devisdetailclients.get(i).getTariftotal(); 
        }
        
        return (total);
    }
    public String getTotal_s(){
        return formatterNb(this.getTotal());
    }
    public double getTotalfinition(){
        double total=getTotal();
        return ((this.tauxaugment*total)+total);
    }
    public String getTotalfinition_s(){
        return formatterNb(this.getTotalfinition());
    }
    public String formatterNb(double d){
        DecimalFormat df= new DecimalFormat("#,###.#####");
        return df.format(Double.valueOf(String.format("%.2f",d).replaceAll(",",".")));
    }
//id_travaudetail | codetravaud |designationd | unite | quantite | pu |nomfinition | tauxaugment | datedebut  | datehfin|datecreation

}
