package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Csv_lieu extends Motherobj<Csv_lieu>  {
    String lieu ; 

    public Csv_lieu(){ }
    public Csv_lieu(String lieu)throws Exception{
        setLieu(lieu);
    }
    
    public String getLieu(){
        return this.lieu;
    }
    public void setLieu(String lieu){
        lieu=lieu.trim();
        this.lieu=lieu;
    }

}
