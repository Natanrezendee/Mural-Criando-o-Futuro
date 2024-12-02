package br.com.mural.criando.futuro.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;
/*
@Controller
public class GlobalExceptionHandler implements ErrorController {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @RequestMapping("/error")
    public String handleError(Exception e, Model model) {
        logger.info(e.getMessage());
        return "redirect:/";  // Redireciona para a página principal
    }
}
*/