package com.projet.eval.models;
import com.projet.eval.gno.*;

import java.sql.Connection;

import com.projet.eval.exception.*;


public class Unite extends Motherobj<Unite>  {
    @Id
    int id_unite ; 
    String unite ; 

    public Unite(){ }
    public Unite(String id_unite,String unite)throws Exception{
        setId_unite(id_unite);
        setUnite(unite);
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
    public String getUnite(){
        return this.unite;
    }
    public void setUnite(String unite){
        unite=unite.trim();
        this.unite=unite;
    }
    public void insertBydatacsv(Connection connection)throws Exception{
        Csv_unite[]  csv_unites=new Csv_unite().read(connection);
        if(csv_unites==null){ throw new MyException("donnee csv_unite null"); }
        Unite unitetemp=null;
        for(Csv_unite csv_unite:csv_unites){
            unitetemp=new Unite("0",csv_unite.getUnite());
            unitetemp.create(connection);
        }
    }

}
