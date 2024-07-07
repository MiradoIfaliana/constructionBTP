package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Csv_devis_travaux extends Motherobj<Csv_devis_travaux>  {
    int id_typemaison ; 
    int id_travaudetail ; 
    String quantite ; 

    public Csv_devis_travaux(){ }
    public Csv_devis_travaux(String id_typemaison,String id_travaudetail,String quantite)throws Exception{
        setId_typemaison(id_typemaison);
        setId_travaudetail(id_travaudetail);
        setQuantite(quantite);
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
    public String getQuantite(){
        return this.quantite;
    }
    public void setQuantite(String quantite){
        quantite=quantite.trim();
        this.quantite=quantite;
    }

}
