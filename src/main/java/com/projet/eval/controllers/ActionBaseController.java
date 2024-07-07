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
import com.projet.eval.gno.UtilBase;
import com.projet.eval.models.*;
import com.projet.eval.utilitaire.Csvutil;


@Controller
@RequestMapping("/base")
public class ActionBaseController extends MotherControlleur {

    @Autowired
    private ServletContext servletContext;
    @Value("${pathupload}")
    private String pathupload;

    @GetMapping("/toresetbase")
    public String toresetbase(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="resetbase";
        UtilBase utilBase=new UtilBase();
        try{
            connection=new Connect().getConnectionPsql();
            String[] tablenames=utilBase.getTableNameAll(connection, "btp", new String[]{"TABLE"});
            request.setAttribute("tablenames", tablenames);
        }catch(Exception e){
            e.printStackTrace();
            erreur=e.getMessage();
        }
        request.setAttribute("error", erreur);
        return page;
    }
    @PostMapping("/resetbase")
    public String resetbase(@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        Connection connection=null;
        String page="resetbase";
        UtilBase utilBase=new UtilBase();
        try{
            connection=new Connect().getConnectionPsql();
            String[] tablenames=utilBase.getTableNameAll(connection, "btp", new String[]{"TABLE"});
            String[] tablenamescheked=this.getTabString_0_n("tablename", request, tablenames.length);
            request.setAttribute("tablenames", tablenames);
            utilBase.resetData(connection, tablenamescheked);
        }catch(Exception e){
            e.printStackTrace();
            erreur=e.getMessage();
        }
        request.setAttribute("error", erreur);
        return page;
    }
}

