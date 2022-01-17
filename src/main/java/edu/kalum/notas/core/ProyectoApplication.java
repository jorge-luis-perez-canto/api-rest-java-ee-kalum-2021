package edu.kalum.notas.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner {
    @Autowired
    private BCryptPasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(edu.kalum.notas.core.ProyectoApplication.class, args);
    }

    public void run(String... args) throws Exception {
        System.out.println(this.encoder.encode("Kalum.2021"));
    }
}
