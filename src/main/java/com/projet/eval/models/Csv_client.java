package com.projet.eval.models;
import com.projet.eval.gno.*;
import com.projet.eval.exception.*;


public class Csv_client extends Motherobj<Csv_client>  {
    String client ; 

    public Csv_client(){ }
    public Csv_client(String client)throws Exception{
        setClient(client);
    }
    
    public String getClient(){
        return this.client;
    }
    public void setClient(String client){
        client=client.trim();
        this.client=client;
    }

}
