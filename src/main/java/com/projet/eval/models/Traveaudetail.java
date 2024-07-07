package com.projet.eval.models;
import com.projet.eval.gno.*;

import java.sql.Connection;

import com.projet.eval.exception.*;


public class Traveaudetail extends Motherobj<Traveaudetail>  {
    @Id
    int id_travaudetail ; 
    String codetravaud ; 
    int rang ; 
    int id_unite ; 
    int id_travaux ; 
    String designationd ; 

    public Traveaudetail(){ }
    public Traveaudetail(String id_travaudetail,String codetravaud,String rang,String id_unite,String id_travaux,String designationd)throws Exception{
        setId_travaudetail(id_travaudetail);
        setCodetravaud(codetravaud);
        setRang(rang);
        setId_unite(id_unite);
        setId_travaux(id_travaux);
        setDesignationd(designationd);
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
    public String getCodetravaud(){
        return this.codetravaud;
    }
    public void setCodetravaud(String codetravaud){
        codetravaud=codetravaud.trim();
        this.codetravaud=codetravaud;
    }
    public int getRang(){
        return this.rang;
    }
    public void setRang(int rang){
        this.rang=rang;
    }
    public void setRang(String rang)throws Exception{
        rang=rang.trim();
        try{
            Integer.valueOf(rang);
        }catch(Exception e){
            throw new MyException("valeur de rang:"+rang+" invalide pour type int");
        }
        setRang(Integer.valueOf(rang));
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
    public String getDesignationd(){
        return this.designationd;
    }
    public void setDesignationd(String designationd){
        designationd=designationd.trim();
        this.designationd=designationd;
    }
    public void insertBydatacsv(Connection connection)throws Exception{
        Csv_traveaudetail[]  csv_traveaudetails=new Csv_traveaudetail().read(connection);
        if(csv_traveaudetails==null){ throw new MyException("donnee csv_traveaudetail null"); }
        Traveaudetail traveaudetailtemp=null;
        for(Csv_traveaudetail csv_traveaudetail:csv_traveaudetails){
            traveaudetailtemp=new Traveaudetail("0", csv_traveaudetail.getCode_travaux(), "1", csv_traveaudetail.getId_unite()+"",this.id_travaux+"",csv_traveaudetail.getType_travaux());
            traveaudetailtemp.create(connection);
        }
    }

}
