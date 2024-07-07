package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Traveaudetailtarif_v extends Motherobj<Traveaudetailtarif_v>  {
    @Id
    int id_travaudetail ; 
    String codetravaud ; 
    int rang ; 
    String designationd ; 
    int id_tariftravaux ; 
    double tarifunitaire ; 

    public Traveaudetailtarif_v(){ }
    public Traveaudetailtarif_v(String id_travaudetail,String codetravaud,String rang,String designationd,String id_tariftravaux,String tarifunitaire)throws Exception{
        setId_travaudetail(id_travaudetail);
        setCodetravaud(codetravaud);
        setRang(rang);
        setDesignationd(designationd);
        setId_tariftravaux(id_tariftravaux);
        setTarifunitaire(tarifunitaire);
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
    public String getDesignationd(){
        return this.designationd;
    }
    public void setDesignationd(String designationd){
        designationd=designationd.trim();
        this.designationd=designationd;
    }
    public int getId_tariftravaux(){
        return this.id_tariftravaux;
    }
    public void setId_tariftravaux(int id_tariftravaux){
        this.id_tariftravaux=id_tariftravaux;
    }
    public void setId_tariftravaux(String id_tariftravaux)throws Exception{
        id_tariftravaux=id_tariftravaux.trim();
        try{
            Integer.valueOf(id_tariftravaux);
        }catch(Exception e){
            throw new MyException("valeur de id_tariftravaux:"+id_tariftravaux+" invalide pour type int");
        }
        setId_tariftravaux(Integer.valueOf(id_tariftravaux));
    }
    public double getTarifunitaire(){
        return this.tarifunitaire;
    }
    public void setTarifunitaire(double tarifunitaire){
        this.tarifunitaire=tarifunitaire;
    }
    public void setTarifunitaire(String tarifunitaire)throws Exception{
        tarifunitaire=tarifunitaire.trim();
        try{
            Double.valueOf(tarifunitaire);
        }catch(Exception e){
            throw new MyException("valeur de tarifunitaire:"+tarifunitaire+" invalide pour type double");
        }
        setTarifunitaire(Double.valueOf(tarifunitaire));
    }

}
