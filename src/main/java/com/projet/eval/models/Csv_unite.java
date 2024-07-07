package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Csv_unite extends Motherobj<Csv_unite>  {
    String unite ; 

    public Csv_unite(){ }
    public Csv_unite(String unite)throws Exception{
        setUnite(unite);
    }
    
    public String getUnite(){
        return this.unite;
    }
    public void setUnite(String unite){
        unite=unite.trim();
        this.unite=unite;
    }

}
