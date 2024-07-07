package com.projet.eval.controllers;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.eval.connect.Connect;
import com.projet.eval.exception.AuthException;
import com.projet.eval.exception.MyException;
import com.projet.eval.models.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api_1")
public class AjaxRController extends MotherControlleur {
    @SuppressWarnings("rawtypes")
    @PostMapping("/detaildevis")
    public Map<String,Object> detaildevis(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpServletRequest request
    ) {
        Map<String,Object> map=new HashMap<>();
        Connection connection=null;
        try {
            connection=new Connect().getConnectionPsql();
            DevisMeredetailclient devisMeredetailclient=new DevisMeredetailclient();
            this.setFieldByRequest(devisMeredetailclient, request);
            int id_clienttravaux= devisMeredetailclient.getId_clienttravaux();

            Payement payement=new Payement();
            payement.setId_clienttravaux(id_clienttravaux);
            Payement[] payements=payement.getById_clienttravaux(connection);
            if(payements==null){
                map.put("payements", 0);
            }else{
                map.put("payements", payements);
            }
            
            Detailpaye_v detailpaye_v=new Detailpaye_v();
            detailpaye_v.setId_clienttravaux(id_clienttravaux);
            detailpaye_v=detailpaye_v.getById_clienttravaux(connection);
            if(detailpaye_v==null){
                map.put("payetotal",0 );
            }else{
                map.put("payetotal",detailpaye_v.formatterNb(detailpaye_v.getPayetotal()) );
            }
            DevisMeredetailclient[] devisMeredetailclients=devisMeredetailclient.getById_clienttravaux(connection);
            map.put("devisMeredetailclients", devisMeredetailclients);
        }catch(AuthException ae){
            erreur=ae.getMessage();
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        map.put("erreur", erreur);
        return map;
    } 

    @SuppressWarnings("rawtypes")
    @GetMapping("/histogrammedevis")
    public Map<String,Object> lieux(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpServletRequest request,
    @RequestParam(name = "annee", defaultValue = "2024") int annee
    ) {
        Map<String,Object> map=new HashMap<>();
        Connection connection=null;
        try {
            connection=new Connect().getConnectionPsql();
            //this.throwIfNoAutoriser(auth0,session,connection," enregitrement paye");
            Devis_paye_totalall_v devis_paye_totalall_v=new Devis_paye_totalall_v();
            devis_paye_totalall_v=devis_paye_totalall_v.getOne(connection);

            Devisparmoisparannee_v devisparmoisparannee_v=new Devisparmoisparannee_v();
            devisparmoisparannee_v.setAnnee(annee);
            Devisparmoisparannee_v[] devisparmoisparannee_vs=devisparmoisparannee_v.getAllByAnnee(connection);
            // devis_paye_totalall_v;
                //devistotal | payetotal
            double[] datas=new double[devisparmoisparannee_vs.length];
            String[] labels=new String[devisparmoisparannee_vs.length];
            for(int i=0;i<devisparmoisparannee_vs.length;i++){
                labels[i]=devisparmoisparannee_vs[i].getMois_s();
                datas[i]=devisparmoisparannee_vs[i].getMontanttotal();
                System.out.println("histogramme "+labels[i]+"  "+datas[i]);
            }
            map.put("labels", labels);
            map.put("datas", datas);
            map.put("devistotal", devis_paye_totalall_v.getDevistotal_s());
            map.put("payetotal", devis_paye_totalall_v.getPayetotal_s());

        }catch(MyException me){
            erreur=me.getMessage();       
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        map.put("erreur", erreur);
        return map;
    }
    @PostMapping("/payer")
    public Map<String,Object> payer(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="payer";
        Map<String,Object> map=new HashMap<>();
        String msg="";
        try {
                // int id_clienttravaux ; 
                // int id_client ; 
                // String numero ; 
                // int id_typemaison ; 
                // String nomtypemaison ; 
                // String nomfinition ; 
                // Date datedebut ; 
                // Timestamp datehfin ; 
                // Date datecreation ; 
                // double totalapaye ; 
                // double payetotal ; 
                // double restepaye ;

            connection=new Connect().getConnectionPsql();
            this.throwIfNoClient(connection, session, "");
            Payementclient_v payementclient_v=new Payementclient_v();
            this.setFieldByRequest(payementclient_v, request); //id_clienttravaux
            payementclient_v=payementclient_v.getById_clienttravaux(connection);
            map.put("totalapaye", payementclient_v.getTotalapaye_s());
            map.put("payetotal", payementclient_v.getPayetotal_s());
            map.put("restepaye", payementclient_v.getRestepaye_s());
            
            Payement payement=new Payement();
            this.setFieldByRequest(payement, request);// montant/ datepaye /id_clienttravaux  
            payement.save(connection);
            msg="payement effectue";
            
            payementclient_v=payementclient_v.getById_clienttravaux(connection);
            map.put("payementclient_v", payementclient_v);
            map.put("totalapaye", payementclient_v.getTotalapaye_s());
            map.put("payetotal", payementclient_v.getPayetotal_s());
            map.put("restepaye", payementclient_v.getRestepaye_s());
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginclient";
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
            page="payer";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
            page="payer";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        map.put("msg",msg);
        map.put("erreur", erreur);
        return map;
    }
}
