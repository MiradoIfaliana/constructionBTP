package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Detaidevisclient extends Motherobj<Detaidevisclient>  {
    @Id
    int id_detaidevisclient ; 
    double quantite ; 
    double tarifunitaire ; 
    int id_travaudetail ; 
    int id_clienttravaux ; 

    public Detaidevisclient(){ }
    public Detaidevisclient(String id_detaidevisclient,String quantite,String tarifunitaire,String id_travaudetail,String id_clienttravaux)throws Exception{
        setId_detaidevisclient(id_detaidevisclient);
        setQuantite(quantite);
        setTarifunitaire(tarifunitaire);
        setId_travaudetail(id_travaudetail);
        setId_clienttravaux(id_clienttravaux);
    }
    
    public Detaidevisclient(int id_detaidevisclient, double quantite, double tarifunitaire, int id_travaudetail,
            int id_clienttravaux) {
        this.id_detaidevisclient = id_detaidevisclient;
        this.quantite = quantite;
        this.tarifunitaire = tarifunitaire;
        this.id_travaudetail = id_travaudetail;
        this.id_clienttravaux = id_clienttravaux;
    }
    public int getId_detaidevisclient(){
        return this.id_detaidevisclient;
    }
    public void setId_detaidevisclient(int id_detaidevisclient){
        this.id_detaidevisclient=id_detaidevisclient;
    }
    public void setId_detaidevisclient(String id_detaidevisclient)throws Exception{
        id_detaidevisclient=id_detaidevisclient.trim();
        try{
            Integer.valueOf(id_detaidevisclient);
        }catch(Exception e){
            throw new MyException("valeur de id_detaidevisclient:"+id_detaidevisclient+" invalide pour type int");
        }
        setId_detaidevisclient(Integer.valueOf(id_detaidevisclient));
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
    public double getTarifunitaire(){
        return this.tarifunitaire;
    }
    public void setTarifunitaire(double tarifunitaire){
        this.tarifunitaire=tarifunitaire;
    }
    public void setTarifunitaire(String tarifunitaire)throws Exception{
        tarifunitaire=tarifunitaire.trim();
        try{
            Double.valueOf(tarifunitaire);
        }catch(Exception e){
            throw new MyException("valeur de tarifunitaire:"+tarifunitaire+" invalide pour type double");
        }
        setTarifunitaire(Double.valueOf(tarifunitaire));
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

}
