package br.com.mural.criando.futuro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // Habilita o agendamento de tarefas
public class MuralCriandoOFuturoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuralCriandoOFuturoApplication.class, args);
	}

}
