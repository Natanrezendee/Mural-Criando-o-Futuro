package br.com.mural.criando.futuro.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception e, Model model) {
        logger.error("An error occurred: {}", e.toString()); // Aqui você pode personalizar ainda mais a mensagem.
        model.addAttribute("errorMessage", e.getMessage());
        return "redirect:/";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException e, Model model) {
        logger.error("Page not found: {}", e.toString()); // Aqui você pode personalizar ainda mais a mensagem.
        model.addAttribute("errorMessage", "Page not found");
        return "redirect:/";
    }
}

