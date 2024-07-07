package com.projet.eval.models;
import java.sql.Connection;
import java.sql.Date;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Admin extends Motherobj<Admin>  {
    @Id
    int id_admin ; 
    String nom ; 
    Date nee ; 
    String pwd ; 
    String mail ; 

    public Admin(){ }
    public Admin(String id_admin,String nom,String nee,String pwd,String mail)throws Exception{
        setId_admin(id_admin);
        setNom(nom);
        setNee(nee);
        setPwd(pwd);
        setMail(mail);
    }
    
    public int getId_admin(){
        return this.id_admin;
    }
    public void setId_admin(int id_admin){
        this.id_admin=id_admin;
    }
    public void setId_admin(String id_admin)throws Exception{
        id_admin=id_admin.trim();
        try{
            Integer.valueOf(id_admin);
        }catch(Exception e){
            throw new MyException("valeur de id_admin:"+id_admin+" invalide pour type int");
        }
        setId_admin(Integer.valueOf(id_admin));
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        nom=nom.trim();
        this.nom=nom;
    }
    public Date getNee(){
        return this.nee;
    }
    public void setNee(Date nee){
        this.nee=nee;
    }
    public void setNee(String nee)throws Exception{
        nee=nee.trim();
        try{
            Date.valueOf(nee);
        }catch(Exception e){
            throw new MyException("valeur de nee:"+nee+" invalide pour type Date");
        }
        setNee(Date.valueOf(nee));
    }
    public String getPwd(){
        return this.pwd;
    }
    public void setPwd(String pwd){
        pwd=pwd.trim();
        this.pwd=pwd;
    }
    public String getMail(){
        return this.mail;
    }
    public void setMail(String mail){
        mail=mail.trim();
        this.mail=mail;
    }
    public Admin getByMailPwd(Connection connection)throws Exception{
        return readOneByQueryConvenable(connection, "select * from admin where mail = ? and pwd= ? ", new Object[]{this.mail,this.pwd});
    }

}
