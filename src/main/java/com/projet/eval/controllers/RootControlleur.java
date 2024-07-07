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
import com.projet.eval.exception.MyException;
import com.projet.eval.utilitaire.Csvutil;


@Controller
@RequestMapping("/")
public class RootControlleur extends MotherControlleur {

    @Autowired
    private ServletContext servletContext;
    @Value("${pathupload}")
    private String pathupload;

    @GetMapping("/")
    public String root(Model model,@RequestParam(name = "erreur", defaultValue = "") String erreur,HttpSession session,HttpServletRequest request) {
        request.setAttribute("erreur", erreur);
        return "loginclient";
    }


}

