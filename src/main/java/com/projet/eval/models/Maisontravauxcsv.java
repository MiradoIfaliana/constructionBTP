package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Maisontravauxcsv extends Motherobj<Maisontravauxcsv>  {
    @Id
    int id_maisontravauxcsv ; 
    String type_maison ; 
    String descriptions ; 
    String surface ; 
    String code_travaux ; 
    String type_travaux ; 
    String unite ; 
    String prix_unitaire ; 
    String quantite ; 
    String duree_travaux ; 

    public Maisontravauxcsv(){ }
    public Maisontravauxcsv(String id_maisontravauxcsv,String type_maison,String descriptions,String surface,String code_travaux,String type_travaux,String unite,String prix_unitaire,String quantite,String duree_travaux)throws Exception{
        setId_maisontravauxcsv(id_maisontravauxcsv);
        setType_maison(type_maison);
        setDescriptions(descriptions);
        setSurface(surface);
        setCode_travaux(code_travaux);
        setType_travaux(type_travaux);
        setUnite(unite);
        setPrix_unitaire(prix_unitaire);
        setQuantite(quantite);
        setDuree_travaux(duree_travaux);
    }
    
    public int getId_maisontravauxcsv(){
        return this.id_maisontravauxcsv;
    }
    public void setId_maisontravauxcsv(int id_maisontravauxcsv){
        this.id_maisontravauxcsv=id_maisontravauxcsv;
    }
    public void setId_maisontravauxcsv(String id_maisontravauxcsv)throws Exception{
        id_maisontravauxcsv=id_maisontravauxcsv.trim();
        try{
            Integer.valueOf(id_maisontravauxcsv);
        }catch(Exception e){
            throw new MyException("valeur de id_maisontravauxcsv:"+id_maisontravauxcsv+" invalide pour type int");
        }
        setId_maisontravauxcsv(Integer.valueOf(id_maisontravauxcsv));
    }
    public String getType_maison(){
        return this.type_maison;
    }
    public void setType_maison(String type_maison){
        type_maison=type_maison.trim();
        this.type_maison=type_maison;
    }
    public String getDescriptions(){
        return this.descriptions;
    }
    public void setDescriptions(String descriptions){
        descriptions=descriptions.trim();
        this.descriptions=descriptions;
    }
    public String getSurface(){
        return this.surface;
    }
    public void setSurface(String surface){
        surface=surface.trim().replaceAll(",", ".");
        this.surface=surface;
    }
    public String getCode_travaux(){
        return this.code_travaux;
    }
    public void setCode_travaux(String code_travaux){
        code_travaux=code_travaux.trim();
        this.code_travaux=code_travaux;
    }
    public String getType_travaux(){
        return this.type_travaux;
    }
    public void setType_travaux(String type_travaux){
        type_travaux=type_travaux.trim();
        this.type_travaux=type_travaux;
    }
    public String getUnite(){
        return this.unite;
    }
    public void setUnite(String unite){
        unite=unite.trim();
        this.unite=unite;
    }
    public String getPrix_unitaire(){
        return this.prix_unitaire;
    }
    public void setPrix_unitaire(String prix_unitaire){
        prix_unitaire=prix_unitaire.trim().replaceAll(",", ".");
        this.prix_unitaire=prix_unitaire;
    }
    public String getQuantite(){
        return this.quantite;
    }
    public void setQuantite(String quantite){
        quantite=quantite.trim().replaceAll(",", ".");
        this.quantite=quantite;
    }
    public String getDuree_travaux(){
        return this.duree_travaux;
    }
    public void setDuree_travaux(String duree_travaux){
        duree_travaux=duree_travaux.trim().replaceAll(",", ".");
        this.duree_travaux=duree_travaux;
    }

}
