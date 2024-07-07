package com.projet.eval.controllers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletContext;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
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
import com.projet.eval.exception.AuthException;
import com.projet.eval.exception.MyException;
import com.projet.eval.models.*;
import com.projet.eval.utilitaire.Csvutil;


@Controller
@RequestMapping("/maisonc")
public class ClientMaisonController extends MotherControlleur {

    @Autowired
    private ServletContext servletContext;
    @Value("${pathupload}")
    private String pathupload;

    @GetMapping("/listetypemaison")
    public String listetypemaison(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="listetypemaison";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNoClient(connection, session, "");
            Typemaison[] typemaisons=new Typemaison().read(connection);
            request.setAttribute("typemaisons", typemaisons);
            
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginclient";
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
    }///maisonc/choixfinition
    @PostMapping("/choixfinition")
    public String choixfinition(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="choixfinition";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNoClient(connection, session, "");
            Typemaison[] typemaisons=new Typemaison().read(connection);
            request.setAttribute("typemaisons", typemaisons);
            Typemaison typemaison=new Typemaison();
            setFieldByRequest(typemaison, request);
            typemaison=typemaison.readById(connection);
            if(typemaison==null){ throw new MyException("veuillez choisir le type de maison");}
            Typefinition[] typefinitions=new Typefinition().read(connection);
            request.setAttribute("id_typemaison", typemaison.getId_typemaison()+"");
            request.setAttribute("typefinitions", typefinitions);

            Lieu[] lieus=new Lieu().read(connection);
            request.setAttribute("lieus",lieus);
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginclient";
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
            page="listetypemaison";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
            page="listetypemaison";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }
    //creerdevis
    @PostMapping("/creerdevis")
    public String creerdevis(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="listetypemaison";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNoClient(connection, session, "");
            Typemaison[] typemaisons=new Typemaison().read(connection);
            request.setAttribute("typemaisons", typemaisons);
            Clienttravaux clienttravaux=new Clienttravaux();
            // clienttravaux;
            // id_clienttravaux |   nomtraveaux   | datedebut  |      datehfin       | datecreation | id_client | id_typemaison | id_lieu | id_tauxtariffinition
            //detaidevisclient;
            //id_detaidevisclient | quantite | tarifunitaire | id_travaudetail | id_clienttravaux
            setFieldByRequest(clienttravaux, request);
            Lasttauxtariffinition_v lasttauxtariffinition_v=new Lasttauxtariffinition_v();
            setFieldByRequest(lasttauxtariffinition_v, request);
            lasttauxtariffinition_v=lasttauxtariffinition_v.getById_typefinition(connection);
            clienttravaux.setId_tauxtariffinition(lasttauxtariffinition_v.getId_tauxtariffinition());
            Client client=getClient(connection, session);
            clienttravaux.save(connection,client);

            request.setAttribute("msg","devis travaux creer!");
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginclient";
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
            page="listetypemaison";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
            page="listetypemaison";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }
    @GetMapping("/mylistedevis")
    public String mylistedevis(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="mylistedevis";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNoClient(connection, session, "");
            Listedevisclient_v listedevisclient_v=new Listedevisclient_v();
            listedevisclient_v.setNumero((String)session.getAttribute("numero"));
            Listedevisclient_v[] listedevisclient_vs=listedevisclient_v.readAllByNumero(connection);
            request.setAttribute("listedevisclient_vs", listedevisclient_vs);
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginclient";
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
            page="mylistedevis";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
            page="mylistedevis";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }//detaildevis id_clienttravaux detaildevis
    @PostMapping("/detaildevis")
    public String detaildevis(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="detaildevis";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNoClient(connection, session, "");
            DevisMeredetailclient devisMeredetailclient=new DevisMeredetailclient();
            this.setFieldByRequest(devisMeredetailclient, request);
            DevisMeredetailclient[] devisMeredetailclients=devisMeredetailclient.getById_clienttravaux(connection);
            request.setAttribute("devisMeredetailclients", devisMeredetailclients);
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginclient";
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
            page="detaildevis";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
            page="detaildevis";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    } 
    @PostMapping("/topayer")
    public String topayer(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="payer";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNoClient(connection, session, "");
            Payementclient_v payementclient_v=new Payementclient_v();
            this.setFieldByRequest(payementclient_v, request);
            payementclient_v=payementclient_v.getById_clienttravaux(connection);
            request.setAttribute("payementclient_v", payementclient_v);
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
        request.setAttribute("erreur", erreur);
        return page;
    }
    @PostMapping("/payer")
    public String payer(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="payer";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNoClient(connection, session, "");
            Payementclient_v payementclient_v=new Payementclient_v();
            this.setFieldByRequest(payementclient_v, request);
            payementclient_v=payementclient_v.getById_clienttravaux(connection);
            request.setAttribute("payementclient_v", payementclient_v);
            
            Payement payement=new Payement();
            this.setFieldByRequest(payement, request);
            payement.save(connection);
            request.setAttribute("msg", "payement effectue");
            payementclient_v=payementclient_v.getById_clienttravaux(connection);
            request.setAttribute("payementclient_v", payementclient_v);
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
        request.setAttribute("erreur", erreur);
        return page;
    } 

}

