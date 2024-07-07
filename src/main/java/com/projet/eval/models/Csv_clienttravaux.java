package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Csv_clienttravaux extends Motherobj<Csv_clienttravaux>  {
    int id_client ; 
    String ref_devis ; 
    int id_typemaison ; 
    int id_tauxtariffinition ; 
    String date_devis ; 
    String date_debut ; 
    int id_lieu ; 

    public Csv_clienttravaux(){ }
    public Csv_clienttravaux(String id_client,String ref_devis,String id_typemaison,String id_tauxtariffinition,String date_devis,String date_debut,String id_lieu)throws Exception{
        setId_client(id_client);
        setRef_devis(ref_devis);
        setId_typemaison(id_typemaison);
        setId_tauxtariffinition(id_tauxtariffinition);
        setDate_devis(date_devis);
        setDate_debut(date_debut);
        setId_lieu(id_lieu);
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
    public String getRef_devis(){
        return this.ref_devis;
    }
    public void setRef_devis(String ref_devis){
        ref_devis=ref_devis.trim();
        this.ref_devis=ref_devis;
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
    public String getDate_devis(){
        return this.date_devis;
    }
    public void setDate_devis(String date_devis){
        date_devis=date_devis.trim();
        this.date_devis=date_devis;
    }
    public String getDate_debut(){
        return this.date_debut;
    }
    public void setDate_debut(String date_debut){
        date_debut=date_debut.trim();
        this.date_debut=date_debut;
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

}
