package edu.kalum.notas.core.auth;

import edu.kalum.notas.core.models.entities.Alumno;
import edu.kalum.notas.core.models.entities.Usuario;
import edu.kalum.notas.core.models.services.IAlumnoServices;
import edu.kalum.notas.core.models.services.IUsuarioServices;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

//@SpringBootApplication
@Component
public class InfoAdicionalToken implements TokenEnhancer {
    @Autowired
    private IUsuarioServices usuarioServices;

    @Autowired
    private IAlumnoServices alumnoServices;

    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Usuario usuario = this.usuarioServices.findByUsername(oAuth2Authentication.getName());
        Alumno alumno = this.alumnoServices.findByEmail(usuario.getEmail());
        Map<String, Object> info = new HashMap<>();
        info.put("carne", alumno.getCarne());
        info.put("apellidos", alumno.getApellidos());
        info.put("nombres", alumno.getNombres());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
