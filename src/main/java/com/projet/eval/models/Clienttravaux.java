package com.projet.eval.models;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Clienttravaux extends Motherobj<Clienttravaux>  {
    @Id
    int id_clienttravaux ; 
    String nomtraveaux ; 
    Date datedebut ; 
    Timestamp datehfin ; 
    Date datecreation ; 
    int id_client ; 
    int id_typemaison ; 
    int id_lieu ; 
    int id_tauxtariffinition ; 
    String ref_devis ; 

    public Clienttravaux(){ }
    public Clienttravaux(String id_clienttravaux,String nomtraveaux,String datedebut,String datehfin,String datecreation,String id_client,String id_typemaison,String id_lieu,String id_tauxtariffinition,String ref_devis)throws Exception{
        setId_clienttravaux(id_clienttravaux);
        setNomtraveaux(nomtraveaux);
        setDatedebut(datedebut);
        setDatehfin(datehfin);
        setDatecreation(datecreation);
        setId_client(id_client);
        setId_typemaison(id_typemaison);
        setId_lieu(id_lieu);
        setId_tauxtariffinition(id_tauxtariffinition);
        setRef_devis(ref_devis);
    }
    public Clienttravaux(String id_clienttravaux,String nomtraveaux,String datedebut,String datecreation,String id_client,String id_typemaison,String id_lieu,String id_tauxtariffinition,String ref_devis)throws Exception{
        setId_clienttravaux(id_clienttravaux);
        setNomtraveaux(nomtraveaux);
        setDatedebut(datedebut);
        setDatecreation(datecreation);
        setId_client(id_client);
        setId_typemaison(id_typemaison);
        setId_lieu(id_lieu);
        setId_tauxtariffinition(id_tauxtariffinition);
        setRef_devis(ref_devis);
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
    public String getNomtraveaux(){
        return this.nomtraveaux;
    }
    public void setNomtraveaux(String nomtraveaux){
        nomtraveaux=nomtraveaux.trim();
        this.nomtraveaux=nomtraveaux;
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
    public int getId_tauxtariffinition(){
        return this.id_tauxtariffinition;
    }
    public void setId_tauxtariffinition(int id_tauxtariffinition){
        this.id_tauxtariffinition=id_tauxtariffinition;
    }
    public void setId_tauxtariffinition(String id_tauxtariffinition)throws Exception{
        id_tauxtariffinition=id_tauxtariffinition.trim();
        try{
            Integer.valueOf(id_tauxtariffinition);
        }catch(Exception e){
            throw new MyException("valeur de id_tauxtariffinition:"+id_tauxtariffinition+" invalide pour type int");
        }
        setId_tauxtariffinition(Integer.valueOf(id_tauxtariffinition));
    }
    public String getRef_devis(){
        return this.ref_devis;
    }
    public void setRef_devis(String ref_devis){
        ref_devis=ref_devis.trim();
        this.ref_devis=ref_devis;
    }


            // clienttravaux;
            // id_clienttravaux |   nomtraveaux   | datedebut  |      datehfin       | datecreation | id_client | id_typemaison | id_lieu | id_tauxtariffinition
            //detaidevisclient;
            //id_detaidevisclient | quantite | tarifunitaire | id_travaudetail | id_clienttravaux

    public void save(Connection connection,Client client)throws Exception{
        if(datedebut.before(Date.valueOf(LocalDate.now()))){
            throw new MyException("date de debut doit etre superieur a aujourd'hui");
        }
        if(client==null){ throw new AuthException("veuillez vous identifier en tant que client"); }
        Typemaison typemaison=new Typemaison();
        typemaison.setId_typemaison(this.id_typemaison);
        typemaison=typemaison.readById(connection);
        if(typemaison==null){ throw new MyException("type maison invalide"); }
        this.id_client=client.getId_client();
        //Timestamp t=datedebut.toLocalDate().plusDays((long)typemaison.getDuree_j());
        if(datecreation==null){
            this.datecreation=Date.valueOf(LocalDate.now());
        }
        if(datehfin==null){
            this.datehfin=Timestamp.valueOf(LocalDateTime.of(datedebut.toLocalDate().plusDays((long)typemaison.getDuree_j()),LocalTime.of(0, 0, 0)));
        }//Detaidevisclient detaidevisclient =new detaidevisclient
        Quantitetariftravaux_v quantitetariftravaux_v=new Quantitetariftravaux_v();
        System.out.println("----->"+id_typemaison);
        quantitetariftravaux_v.setId_typemaison(this.id_typemaison);
        Quantitetariftravaux_v[] quantitetariftravaux_vs=quantitetariftravaux_v.getAllById_typemaison(connection);
        Detaidevisclient detaidevisclient=null;
        if(quantitetariftravaux_vs==null){ throw new MyException(" donnee travaux du type maison vide"); }
        try{
            connection.setAutoCommit(false);
            Clienttravaux ct=this.create(connection);
            for(int i=0;i<quantitetariftravaux_vs.length;i++){
                detaidevisclient=new Detaidevisclient(0, quantitetariftravaux_vs[i].getQuantite(), quantitetariftravaux_vs[i].getTarifunitaire(), quantitetariftravaux_vs[i].getId_travaudetail(), ct.getId_clienttravaux());
                detaidevisclient.create(connection);
            }
            connection.commit();

        }catch(MyException me){
            connection.rollback();
            throw me;
        }catch(Exception e){
            connection.rollback();
            throw e;
        }
        finally{
            connection.setAutoCommit(true);
        }
        
    }
    //SAVE sans commit
    public void save(Connection connection)throws Exception{
        // if(datedebut.before(Date.valueOf(LocalDate.now()))){
        //     throw new MyException("date de debut doit etre superieur a aujourd'hui");
        // }
        Typemaison typemaison=new Typemaison();
        typemaison.setId_typemaison(this.id_typemaison);
        typemaison=typemaison.readById(connection);
        if(typemaison==null){ throw new MyException("type maison invalide"); }
        //Timestamp t=datedebut.toLocalDate().plusDays((long)typemaison.getDuree_j());
        if(datecreation==null){
            this.datecreation=Date.valueOf(LocalDate.now());
        }
        if(datehfin==null){
            this.datehfin=Timestamp.valueOf(LocalDateTime.of(datedebut.toLocalDate().plusDays((long)typemaison.getDuree_j()),LocalTime.of(0, 0, 0)));
        }//Detaidevisclient detaidevisclient =new detaidevisclient
        Quantitetariftravaux_v quantitetariftravaux_v=new Quantitetariftravaux_v();
        System.out.println("----->"+id_typemaison);
        quantitetariftravaux_v.setId_typemaison(this.id_typemaison);
        Quantitetariftravaux_v[] quantitetariftravaux_vs=quantitetariftravaux_v.getAllById_typemaison(connection);
        Detaidevisclient detaidevisclient=null;
        if(quantitetariftravaux_vs==null){ throw new MyException(" donnee travaux du type maison vide"); }
            Clienttravaux ct=this.create(connection);
            for(int i=0;i<quantitetariftravaux_vs.length;i++){
                detaidevisclient=new Detaidevisclient(0, quantitetariftravaux_vs[i].getQuantite(), quantitetariftravaux_vs[i].getTarifunitaire(), quantitetariftravaux_vs[i].getId_travaudetail(), ct.getId_clienttravaux());
                detaidevisclient.create(connection);
            }        
    }
    public void insertBydatacsv(Connection connection)throws Exception{
        Csv_clienttravaux[]  csv_clienttravauxs=new Csv_clienttravaux().read(connection);
        if(csv_clienttravauxs==null){ throw new MyException("donnee csv_clienttravaux null"); }
        Clienttravaux clienttravauxtemp=null;
        for(Csv_clienttravaux csv_clienttravaux:csv_clienttravauxs){
            clienttravauxtemp=new Clienttravaux("0","my travaux",csv_clienttravaux.getDate_debut(), csv_clienttravaux.getDate_devis(), csv_clienttravaux.getId_client()+"", csv_clienttravaux.getId_typemaison()+"", csv_clienttravaux.getId_lieu()+"", csv_clienttravaux.getId_tauxtariffinition()+"", csv_clienttravaux.getRef_devis());
            clienttravauxtemp.save(connection); //save sans commit le io
        }
    }

}
