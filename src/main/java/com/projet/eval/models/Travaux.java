package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Travaux extends Motherobj<Travaux>  {
    @Id
    int id_travaux ; 
    String codetravau ; 
    String designation ; 

    public Travaux(){ }
    public Travaux(String id_travaux,String codetravau,String designation)throws Exception{
        setId_travaux(id_travaux);
        setCodetravau(codetravau);
        setDesignation(designation);
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

}
