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
@RequestMapping("/admin")
public class AdminController extends MotherControlleur {

    @Autowired
    private ServletContext servletContext;
    @Value("${pathupload}")
    private String pathupload;
//throwIfNotAdmin(Connection connection,HttpSession session)
    @GetMapping("/listedevis")
    public String listedevis(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="listedevis";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNotAdmin(connection,session);
            Listedevisclient_v listedevisclient_v=new Listedevisclient_v();
            Listedevisclient_v[] listedevisclient_vs=listedevisclient_v.readAllEncoure(connection);
            request.setAttribute("listedevisclient_vs", listedevisclient_vs);
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginadmin";
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
            page="listedevis";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
            page="listedevis";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }//detaildevis
    @PostMapping("/detaildevis")
    public String detaildevis(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="detaildevisadmin";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNotAdmin(connection,session);
            DevisMeredetailclient devisMeredetailclient=new DevisMeredetailclient();
            this.setFieldByRequest(devisMeredetailclient, request);
            DevisMeredetailclient[] devisMeredetailclients=devisMeredetailclient.getById_clienttravaux(connection);
            request.setAttribute("devisMeredetailclients", devisMeredetailclients);
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginadmin";
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
            page="detaildevisadmin";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
            page="detaildevisadmin";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }
    //histogrammedevis
    @GetMapping("/histogrammedevis")
    public String histogrammedevis(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="histogrammedevis";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNotAdmin(connection,session);
            System.out.println(request.getParameter("mois_annee"));
            //Devisparannee_v devisparannee_v=
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginadmin";
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
            page="histogrammedevis";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
            page="histogrammedevis";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }
    @GetMapping("/listetypefinition")
    public String listetypefinition(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="listetypefinition";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNotAdmin(connection,session);
            Typefinition_v[] typefinition_vs=new Typefinition_v().read(connection);
            request.setAttribute("typefinition_vs", typefinition_vs);
            //Devisparannee_v devisparannee_v=
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginadmin";
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
            page="listetypefinition";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
            page="listetypefinition";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }//modiftauxfinition
    @PostMapping("/tomodiftauxfinition")
    public String tomodiftauxfinition(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="modiftauxfinition";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNotAdmin(connection,session);//getById_typefinition(Connection connection)
            Typefinition_v typefinition_v=new Typefinition_v();
            this.setFieldByRequest(typefinition_v, request);
            typefinition_v=typefinition_v.getById_typefinition(connection);
            request.setAttribute("typefinition_v", typefinition_v);
            //Devisparannee_v devisparannee_v=
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginadmin";
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
            page="modiftauxfinition";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
            page="modiftauxfinition";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }
    @PostMapping("/modiftauxfinition")
    public String modiftauxfinition(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="modiftauxfinition";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNotAdmin(connection,session);//getById_typefinition(Connection connection)
            Typefinition_v typefinition_v=new Typefinition_v();
            this.setFieldByRequest(typefinition_v, request);
            typefinition_v=typefinition_v.getById_typefinition(connection);
            request.setAttribute("typefinition_v", typefinition_v);

            Tauxtariffinition tauxtariffinition=new Tauxtariffinition();
            this.setFieldByRequest(tauxtariffinition, request);
            tauxtariffinition.save(connection);

            typefinition_v=typefinition_v.getById_typefinition(connection);
            request.setAttribute("typefinition_v", typefinition_v);

            request.getAttribute("modification effectue");
            //Devisparannee_v devisparannee_v=
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginadmin";
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
            page="modiftauxfinition";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
            page="modiftauxfinition";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }

    @GetMapping("/listetraveaudetail")
    public String listetraveaudetail(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="listetraveaudetail";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNotAdmin(connection,session);
            Traveaudetailtarif_v[] traveaudetailtarif_vs=new Traveaudetailtarif_v().read(connection);
            request.setAttribute("traveaudetailtarif_vs", traveaudetailtarif_vs);
            //Devisparannee_v devisparannee_v=
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginadmin";
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
            page="listetraveaudetail";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
            page="listetraveaudetail";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }//modiftauxfinition
    @PostMapping("/tomodifetravauxdetail")
    public String tomodifetravauxdetail(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="modifetravauxdetail";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNotAdmin(connection,session);//getById_traveaudetail(Connection connection)
            Traveaudetailtarif_v traveaudetailtarif_v=new Traveaudetailtarif_v();
            this.setFieldByRequest(traveaudetailtarif_v, request);
            traveaudetailtarif_v=traveaudetailtarif_v.readById(connection);
            request.setAttribute("traveaudetailtarif_v", traveaudetailtarif_v);
            //Devisparannee_v devisparannee_v=
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginadmin";
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
            page="modifetravauxdetail";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
            page="modifetravauxdetail";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }
    @PostMapping("/modifetravauxdetail")
    public String modifetravauxdetail(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="modifetravauxdetail";
        try {
            connection=new Connect().getConnectionPsql();
            this.throwIfNotAdmin(connection,session);//getById_traveaudetail(Connection connection)
            Traveaudetailtarif_v traveaudetailtarif_v=new Traveaudetailtarif_v();
            this.setFieldByRequest(traveaudetailtarif_v, request);
            traveaudetailtarif_v=traveaudetailtarif_v.readById(connection);
            request.setAttribute("traveaudetailtarif_v", traveaudetailtarif_v);

            Tariftravaux tariftravaux=new Tariftravaux();
            this.setFieldByRequest(tariftravaux, request);
            tariftravaux.save(connection);

            traveaudetailtarif_v=traveaudetailtarif_v.readById(connection);
            request.setAttribute("traveaudetailtarif_v", traveaudetailtarif_v);

            request.getAttribute("modification effectue");
            //Devisparannee_v devisparannee_v=
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginadmin";
        }catch(MyException me){
            me.printStackTrace();
            erreur=me.getMessage();
            page="modifetravauxdetail";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="error";
            page="modifetravauxdetail";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }
    ///admin/traveaudetail
}

