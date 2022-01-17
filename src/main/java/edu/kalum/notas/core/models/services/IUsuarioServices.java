package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.entities.Usuario;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public interface IUsuarioServices {
    Usuario findByUsername(String paramString);

    Usuario findByEmail(String paramString);

    Usuario save(Usuario paramUsuario);
}
