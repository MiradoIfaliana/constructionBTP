package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Csv_traveaudetail extends Motherobj<Csv_traveaudetail>  {
    String code_travaux ; 
    String type_travaux ; 
    String unite ; 
    int id_unite ; 

    public Csv_traveaudetail(){ }
    public Csv_traveaudetail(String code_travaux,String type_travaux,String unite,String id_unite)throws Exception{
        setCode_travaux(code_travaux);
        setType_travaux(type_travaux);
        setUnite(unite);
        setId_unite(id_unite);
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
    public int getId_unite(){
        return this.id_unite;
    }
    public void setId_unite(int id_unite){
        this.id_unite=id_unite;
    }
    public void setId_unite(String id_unite)throws Exception{
        id_unite=id_unite.trim();
        try{
            Integer.valueOf(id_unite);
        }catch(Exception e){
            throw new MyException("valeur de id_unite:"+id_unite+" invalide pour type int");
        }
        setId_unite(Integer.valueOf(id_unite));
    }

}
