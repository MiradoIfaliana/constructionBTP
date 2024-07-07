package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Csv_typefinition extends Motherobj<Csv_typefinition>  {
    String finition ; 

    public Csv_typefinition(){ }
    public Csv_typefinition(String finition)throws Exception{
        setFinition(finition);
    }
    
    public String getFinition(){
        return this.finition;
    }
    public void setFinition(String finition){
        finition=finition.trim();
        this.finition=finition;
    }

}
