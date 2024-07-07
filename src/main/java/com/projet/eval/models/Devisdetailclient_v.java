package com.projet.eval.models;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Devisdetailclient_v extends Motherobj<Devisdetailclient_v>  {
    int id_clienttravaux ; 
    int id_client ; 
    String numero ; 
    int id_typemaison ; 
    String nomtypemaison ; 
    int id_travaux ; 
    String codetravau ; 
    String designation ; 
    int id_travaudetail ; 
    String codetravaud ; 
    String designationd ; 
    String unite ; 
    double quantite ; 
    double pu ; 
    String nomfinition ; 
    double tauxaugment ; 
    Date datedebut ; 
    Timestamp datehfin ; 
    Date datecreation ; 


    public Devisdetailclient_v(){ }
    public Devisdetailclient_v(String id_clienttravaux,String id_client,String numero,String id_typemaison,String nomtypemaison,String id_travaux,String codetravau,String designation,String id_travaudetail,String codetravaud,String designationd,String unite,String quantite,String pu,String nomfinition,String tauxaugment,String datedebut,String datehfin,String datecreation)throws Exception{
        setId_clienttravaux(id_clienttravaux);
        setId_client(id_client);
        setNumero(numero);
        setId_typemaison(id_typemaison);
        setNomtypemaison(nomtypemaison);
        setId_travaux(id_travaux);
        setCodetravau(codetravau);
        setDesignation(designation);
        setId_travaudetail(id_travaudetail);
        setCodetravaud(codetravaud);
        setDesignationd(designationd);
        setUnite(unite);
        setQuantite(quantite);
        setPu(pu);
        setNomfinition(nomfinition);
        setTauxaugment(tauxaugment);
        setDatedebut(datedebut);
        setDatehfin(datehfin);
        setDatecreation(datecreation);
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
    public int getId_travaudetail(){
        return this.id_travaudetail;
    }
    public void setId_travaudetail(int id_travaudetail){
        this.id_travaudetail=id_travaudetail;
    }
    public void setId_travaudetail(String id_travaudetail)throws Exception{
        id_travaudetail=id_travaudetail.trim();
        try{
            Integer.valueOf(id_travaudetail);
        }catch(Exception e){
            throw new MyException("valeur de id_travaudetail:"+id_travaudetail+" invalide pour type int");
        }
        setId_travaudetail(Integer.valueOf(id_travaudetail));
    }
    public String getCodetravaud(){
        return this.codetravaud;
    }
    public void setCodetravaud(String codetravaud){
        codetravaud=codetravaud.trim();
        this.codetravaud=codetravaud;
    }
    public String getDesignationd(){
        return this.designationd;
    }
    public void setDesignationd(String designationd){
        designationd=designationd.trim();
        this.designationd=designationd;
    }
    public String getUnite(){
        return this.unite;
    }
    public void setUnite(String unite){
        unite=unite.trim();
        this.unite=unite;
    }
    public double getQuantite(){
        return this.quantite;
    }
    public void setQuantite(double quantite){
        this.quantite=quantite;
    }
    public void setQuantite(String quantite)throws Exception{
        quantite=quantite.trim();
        try{
            Double.valueOf(quantite);
        }catch(Exception e){
            throw new MyException("valeur de quantite:"+quantite+" invalide pour type double");
        }
        setQuantite(Double.valueOf(quantite));
    }
    public double getPu(){
        return this.pu;
    }
    public void setPu(double pu){
        this.pu=pu;
    }
    public void setPu(String pu)throws Exception{
        pu=pu.trim();
        try{
            Double.valueOf(pu);
        }catch(Exception e){
            throw new MyException("valeur de pu:"+pu+" invalide pour type double");
        }
        setPu(Double.valueOf(pu));
    }
    public String getNomfinition(){
        return this.nomfinition;
    }
    public void setNomfinition(String nomfinition){
        nomfinition=nomfinition.trim();
        this.nomfinition=nomfinition;
    }
    public double getTauxaugment(){
        return this.tauxaugment;
    }
    public void setTauxaugment(double tauxaugment){
        this.tauxaugment=tauxaugment;
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

    public Devisdetailclient_v[] getById_clienttravaux(Connection connection)throws Exception{
        String query="select * from devisdetailclient_v where id_clienttravaux= ? order by id_clienttravaux,id_typemaison,codetravau,codetravaud";
        return readByQueryConvenable(connection, query, new Object[]{this.id_clienttravaux});
    }
//id_travaudetail | codetravaud |designationd | unite | quantite | pu |nomfinition | tauxaugment | datedebut  | datehfin|datecreation
}
