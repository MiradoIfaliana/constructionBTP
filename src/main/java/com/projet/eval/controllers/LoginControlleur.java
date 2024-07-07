package com.projet.eval.controllers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletContext;

import java.sql.Connection;
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
@RequestMapping("/login")
public class LoginControlleur extends MotherControlleur {

    @Autowired
    private ServletContext servletContext;
    @Value("${pathupload}")
    private String pathupload;

    @PostMapping("/authentificationclient")
    public String authentificationclient(
        @RequestParam(name = "erreur", defaultValue = "") String erreur,
        HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page=null;
        try {
            connection=new Connect().getConnectionPsql();
            Client client=new Client();
            this.setFieldByRequest(client, request);
            client=client.getOrCreateIfNotExistclient(connection);
            session.removeAttribute("id_admin");
            session.setAttribute("numero", client.getNumero());
            page="acceuilclient";
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginclient";
        }catch(MyException me){
            erreur=me.getMessage();
            page="loginclient";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="impossible de se connecter";
            page="loginclient";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }//deconnection
    @GetMapping("/deconnectionclient")
    public String deconnection(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        session.removeAttribute("numero");
        String page="loginclient";
        return page;
    }
    @GetMapping("/acceuilclient")
    public String acceuil(Model model,@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="acceuilclient";
        try {
            connection=new Connect().getConnectionPsql();
            throwIfNoClient(connection,session,"");
            page="acceuilclient";
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginclient";
        }catch(MyException me){
            erreur=me.getMessage();
            page="loginclient";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="impossible de se connecter";
            page="loginclient";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }
    @GetMapping("/loginclient")
    public String loginclient(Model model,@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        request.setAttribute("erreur", erreur);
        return "loginclient";
    }
    @GetMapping("/loginadmin")
    public String loginadmin(Model model,@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        request.setAttribute("erreur", erreur);
        return "loginadmin";
    }
    @PostMapping("/authentificationadmin")
    public String authentificationadmin(
        @RequestParam(name = "erreur", defaultValue = "") String erreur,
        HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="acceuiladmin";
        try {
            connection=new Connect().getConnectionPsql();
            Admin admin=new Admin();
            this.setFieldByRequest(admin, request);
            admin=admin.getByMailPwd(connection);
            if(admin==null){ throw new MyException("admin inconnu, verifiez votre login ou password"); }
            session.removeAttribute("numero");
            session.setAttribute("id_admin", admin.getId_admin()+"");
            page="acceuiladmin";
        }catch(MyException me){
            erreur=me.getMessage();
            page="loginadmin";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="impossible de se connecter";
            page="loginadmin";
        }finally{
            try {   
                if(connection!=null){ connection.close(); }
            }catch(Exception e){e.printStackTrace();
            }
        }
        request.setAttribute("erreur", erreur);
        return page;
    }
    @GetMapping("/deconnectionadmin")
    public String deconnectionadmin(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        session.removeAttribute("id_admin");
        String page="loginadmin";
        return page;
    }
    @GetMapping("/acceuiladmin")
    public String acceuiladmin(Model model,@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="acceuiladmin";
        try {
            connection=new Connect().getConnectionPsql();
            throwIfNotAdmin(connection,session);
            page="acceuiladmin";
        }catch(AuthException ae){
            erreur=ae.getMessage();
            page="loginadmin";
        }catch(MyException me){
            erreur=me.getMessage();
            page="loginadmin";
        }
        catch(Exception ex){
            ex.printStackTrace();
            erreur="impossible de se connecter";
            page="loginadmin";
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


