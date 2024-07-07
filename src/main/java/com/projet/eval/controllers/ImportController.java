package com.projet.eval.controllers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletContext;

import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.projet.eval.connect.Connect;
import com.projet.eval.exception.MyException;
import com.projet.eval.models.*;
import com.projet.eval.utilitaire.Csvutil;


@Controller
@RequestMapping("/import")
public class ImportController extends MotherControlleur {

    @Autowired
    private ServletContext servletContext;
    @Value("${pathupload}")
    private String pathupload;

    @GetMapping("/toimporter")
    public String toimporter(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="import";
        return page;
    }
    @PostMapping("/importer")
    public String importer(@RequestParam("file") MultipartFile file,@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="import";
        try {
            connection=new Connect().getConnectionPsql();
            this.uploadFile(file,servletContext,pathupload);
            //REGLE : LE ID 
            Csvutil<Csvstock> csvutil=new Csvutil<Csvstock>();
            Csvstock[] csvstocks=csvutil.csvToTheObjects(file, servletContext, pathupload, Csvstock.class, true,true);
            for(int i=0;i<csvstocks.length;i++){
                csvstocks[i].systemoutfield();
            }
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
        request.setAttribute("erreur", erreur);
        return page;
    }
    // <li><a href="/import/toimporttravauxdevis">M_Travaux et Devis</a></li>
    // <li><a href="/import/toimportpaiment">Paimennt</a></li>
    @GetMapping("/toimporttravauxdevis")
    public String toimporttravauxdevis(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="importtravauxdevis";
        return page;
    }
    @PostMapping("/importtravauxdevis")
    public String importtravauxdevis(@RequestParam("maison_travaux") MultipartFile maison_travaux,@RequestParam("devis") MultipartFile devis,@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="importtravauxdevis";
        try {
            connection=new Connect().getConnectionPsql();
            this.uploadFile(maison_travaux,servletContext,pathupload);
            this.uploadFile(devis,servletContext,pathupload);
            //Maisontravauxcsv,Deviscsv,Payementcsv
            try{
            //-----------------------------------------------------------------
                connection.setAutoCommit(false);
                Csvutil<Maisontravauxcsv> csvutilM=new Csvutil<Maisontravauxcsv>();
                Csvutil<Deviscsv> csvutilD=new Csvutil<Deviscsv>();
                Maisontravauxcsv[] maisontravauxcsvs=csvutilM.csvToTheObjects(maison_travaux, servletContext, pathupload, Maisontravauxcsv.class, true,true);
                Deviscsv[] deviscsvs=csvutilD.csvToTheObjects(devis, servletContext, pathupload, Deviscsv.class, true,true);
                
                request.setAttribute("maisontravauxcsvs", maisontravauxcsvs);
                request.setAttribute("deviscsvs", deviscsvs);
                for(int i=0;i<maisontravauxcsvs.length;i++){
                    maisontravauxcsvs[i].create(connection);
                    //maisontravauxcsvs[i].systemoutfield();
                }
                for(int i=0;i<deviscsvs.length;i++){
                    deviscsvs[i].create(connection);
                    // deviscsvs[i].systemoutfield();
                }
                connection.commit();
            }catch(MyException ex){
                connection.rollback();
                throw ex;
            }catch(Exception e){
                connection.rollback();
                throw e;
            }finally{
                connection.setAutoCommit(true);
            }
            //-----------------------------------------------------------------
            try{
                connection.setAutoCommit(false);
                Typemaison typemaison=new Typemaison();
                typemaison.insertBydatacsv(connection);
                Unite unite=new Unite();
                unite.insertBydatacsv(connection);
                Traveaudetail traveaudetail=new Traveaudetail();
                
                Travaux travaux=new Travaux();
                travaux=travaux.readOneByQueryConvenable(connection, "select * from travaux", null);
                if(travaux==null){
                    travaux=new Travaux("0", "Code", "Travaux");
                    travaux=travaux.create(connection);
                }
                traveaudetail.setId_travaux(travaux.getId_travaux());
                traveaudetail.insertBydatacsv(connection);
                Tariftravaux tariftravaux=new Tariftravaux();
                tariftravaux.insertBydatacsv(connection);
                Devis_travaux devis_travaux=new Devis_travaux();
                devis_travaux.insertBydatacsv(connection);
                Lieu lieu=new Lieu();
                lieu.insertBydatacsv(connection);
                Typefinition typefinition=new Typefinition();
                typefinition.insertBydatacsv(connection);
                Tauxtariffinition tauxtariffinition=new Tauxtariffinition();
                tauxtariffinition.insertBydatacsv(connection);
                Client client=new Client();
                client.insertBydatacsv(connection);
                Clienttravaux clienttravaux=new Clienttravaux();
                clienttravaux.insertBydatacsv(connection);
                connection.commit();
            }catch(MyException ex){
                connection.rollback();
                throw ex;
            }catch(Exception e){
                connection.rollback();
                throw e;
            }finally{
                connection.setAutoCommit(true);
            }
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur=ex.getMessage();
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }
    @GetMapping("/toimportpaiment")
    public String toimportpaiment(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        String page="importpaiment";
        return page;
    }
    @PostMapping("/importpaiment")
    public String importpaiment(@RequestParam("paiement") MultipartFile paiement,@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="importpaiment";
        try {
            connection=new Connect().getConnectionPsql();
            this.uploadFile(paiement,servletContext,pathupload);
            try{
                connection.setAutoCommit(false);
                Csvutil<Payementcsv> csvutilM=new Csvutil<Payementcsv>();
                Payementcsv[] payementcsvs=csvutilM.csvToTheObjects(paiement, servletContext, pathupload, Payementcsv.class, true,true);
                Payement payementTemp=new Payement();
                String message="";
                for(int i=0;i<payementcsvs.length;i++){
                    //payementcsvs[i].systemoutfield();
                    payementTemp=new Payement();
                    payementTemp.setRef_paiement(payementcsvs[i].getRef_paiement());
                    payementTemp=payementTemp.getByRef_payement(connection);
                    if(payementTemp!=null){ //raha efa nisy dia tsy apidirina
                        message+=payementTemp.getRef_paiement()+" / ";
                    }else{ //raha mbola tsy misy
                        payementcsvs[i].create(connection);
                    }
                    //if(payementcsvs[i].getRef_devis())
                }
                if(message.equals("")==false){
                    message="Ref_paiement existant : "+message;
                }
                request.setAttribute("message", message);
                request.setAttribute("payementcsvs", payementcsvs);
                connection.commit();
            }catch(MyException ex){
                connection.rollback();
                throw ex;
            }catch(Exception e){
                connection.rollback();
                throw e;
            }finally{
                connection.setAutoCommit(true);
            }
            try{
                connection.setAutoCommit(false);
                Payement payement=new Payement();
                payement.insertBydatacsv(connection);
                connection.commit();
            }catch(MyException ex){
                connection.rollback();
                throw ex;
            }catch(Exception e){
                connection.rollback();
                throw e;
            }finally{
                connection.setAutoCommit(true);
            }
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur=ex.getMessage();
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }

}

