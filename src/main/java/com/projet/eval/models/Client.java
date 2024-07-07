package com.projet.eval.models;
import com.projet.eval.gno.*;

import jakarta.security.auth.message.AuthException;

import java.sql.Connection;

import com.projet.eval.connect.Connect;
import com.projet.eval.exception.*;


public class Client extends Motherobj<Client>  {
    @Id
    int id_client ; 
    String numero ; 

    public Client(){ }
    public Client(String id_client,String numero)throws Exception{
        setId_client(id_client);
        setNumero(numero);
    }
    
    public int getId_client(){
        return this.id_client;
    }
    public void setId_client(int id_client){
        this.id_client=id_client;
    }
    public void setId_client(String id_client)throws Exception{
        id_client=id_client.trim();
        try{
            Integer.valueOf(id_client);
        }catch(Exception e){
            throw new MyException("valeur de id_client:"+id_client+" invalide pour type int");
        }
        setId_client(Integer.valueOf(id_client));
    }
    public String getNumero(){
        return this.numero;
    }
    public void setNumero(String numero){
        numero=numero.trim();
        this.numero=numero;
    }
    public Client getByNumero(Connection connection)throws Exception{
       return readOneByQueryConvenable(connection, "select * from client where numero= ? ", new Object[]{this.numero});
    }
    public Client getOrCreateIfNotExistclient(Connection connection)throws Exception{
        if(numero==null){ throw new AuthException("veuillez entrer un numero"); }
        else if(numero.replaceAll(" ","").equals("")){ throw new AuthException("veuillez entrer un numero");}
        Client client=getByNumero(connection);
        if(client==null){
            client=this.create(connection);
        }
        return client;
    }
    public void insertBydatacsv(Connection connection)throws Exception{
        Csv_client[]  csv_clients=new Csv_client().read(connection);
        if(csv_clients==null){ throw new MyException("donnee csv_client null"); }
        Client clienttemp=null;
        for(Csv_client csv_client:csv_clients){
            clienttemp=new Client("0", csv_client.getClient());
            clienttemp.create(connection);
        }
    }

}
