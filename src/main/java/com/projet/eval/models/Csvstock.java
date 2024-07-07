package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Csvstock extends Motherobj<Csvstock>  {
    @Id
    int idcsvstock ; 
    String id ; 
    String daty ; 
    String datyheure ; 
    String nom ; 
    String nb ; 
    String temp ; 

    public Csvstock(){ }
    public Csvstock(String idcsvstock,String id,String daty,String datyheure,String nom,String nb,String temp)throws Exception{
        setIdcsvstock(idcsvstock);
        setId(id);
        setDaty(daty);
        setDatyheure(datyheure);
        setNom(nom);
        setNb(nb);
        setTemp(temp);
    }
    
    public int getIdcsvstock(){
        return this.idcsvstock;
    }
    public void setIdcsvstock(int idcsvstock){
        this.idcsvstock=idcsvstock;
    }
    public void setIdcsvstock(String idcsvstock)throws Exception{
        idcsvstock=idcsvstock.trim();
        try{
            Integer.valueOf(idcsvstock);
        }catch(Exception e){
            throw new MyException("valeur de idcsvstock:"+idcsvstock+" invalide pour type int");
        }
        setIdcsvstock(Integer.valueOf(idcsvstock));
    }
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        id=id.trim();
        this.id=id;
    }
    public String getDaty(){
        return this.daty;
    }
    public void setDaty(String daty){
        daty=daty.trim();
        this.daty=daty;
    }
    public String getDatyheure(){
        return this.datyheure;
    }
    public void setDatyheure(String datyheure){
        datyheure=datyheure.trim();
        this.datyheure=datyheure;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        nom=nom.trim();
        this.nom=nom;
    }
    public String getNb(){
        return this.nb;
    }
    public void setNb(String nb){
        nb=nb.trim();
        this.nb=nb;
    }
    public String getTemp(){
        return this.temp;
    }
    public void setTemp(String temp){
        temp=temp.trim();
        this.temp=temp;
    }

}
