package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Csv_tauxtariffinition extends Motherobj<Csv_tauxtariffinition>  {
    int id_typefinition ; 
    String taux_finition ; 

    public Csv_tauxtariffinition(){ }
    public Csv_tauxtariffinition(String id_typefinition,String taux_finition)throws Exception{
        setId_typefinition(id_typefinition);
        setTaux_finition(taux_finition);
    }
    
    public int getId_typefinition(){
        return this.id_typefinition;
    }
    public void setId_typefinition(int id_typefinition){
        this.id_typefinition=id_typefinition;
    }
    public void setId_typefinition(String id_typefinition)throws Exception{
        id_typefinition=id_typefinition.trim();
        try{
            Integer.valueOf(id_typefinition);
        }catch(Exception e){
            throw new MyException("valeur de id_typefinition:"+id_typefinition+" invalide pour type int");
        }
        setId_typefinition(Integer.valueOf(id_typefinition));
    }
    public String getTaux_finition(){
        return this.taux_finition;
    }
    public void setTaux_finition(String taux_finition){
        taux_finition=taux_finition.trim();
        this.taux_finition=taux_finition;
    }

}
