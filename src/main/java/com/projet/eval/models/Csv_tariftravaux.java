package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Csv_tariftravaux extends Motherobj<Csv_tariftravaux>  {
    int id_travaudetail ; 
    String prix_unitaire ; 

    public Csv_tariftravaux(){ }
    public Csv_tariftravaux(String id_travaudetail,String prix_unitaire)throws Exception{
        setId_travaudetail(id_travaudetail);
        setPrix_unitaire(prix_unitaire);
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
    public String getPrix_unitaire(){
        return this.prix_unitaire;
    }
    public void setPrix_unitaire(String prix_unitaire){
        prix_unitaire=prix_unitaire.trim();
        this.prix_unitaire=prix_unitaire;
    }

}
