package br.com.mural.criando.futuro.controller;
/*
import br.com.mural.criando.futuro.model.ContatoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/contato")
public class ContatoController {

    private static final Logger logger = LoggerFactory.getLogger(ContatoController.class);
    private final JavaMailSender mailSender;

    public ContatoController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @PostMapping
    public String enviarEmail(@RequestBody ContatoDTO contato) {
        try {
            logger.info("Tentando enviar e-mail de contato de: {}", contato.getNome());
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("destinatario@dominio.com");
            mailMessage.setSubject("Novo contato: " + contato.getAssunto());
            mailMessage.setText(String.format("Nome: %s\nE-mail: %s\nTelefone: %s\n\nMensagem:\n%s", contato.getNome(), contato.getEmail(), contato.getTelefone() != null ? contato.getTelefone() : "Não informado", contato.getMensagem()));
            mailSender.send(mailMessage);
            logger.info("E-mail enviado com sucesso.");
            return "Mensagem enviada com sucesso!";
        } catch (Exception e) {
            logger.error("Erro ao enviar e-mail: ", e);
            return "Erro ao enviar mensagem.";
        }
    }
}

 */