package edu.kalum.notas.core.controllers;


import edu.kalum.notas.core.models.entities.Role;
import edu.kalum.notas.core.models.entities.Usuario;
import edu.kalum.notas.core.models.services.IRoleServices;
import edu.kalum.notas.core.models.services.UsuarioServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/kalum-notas/v1"})
public class UsuarioController {
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private IRoleServices roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @PostMapping({"/usuarios"})
    public ResponseEntity<?> create(@Valid @RequestBody Usuario value, BindingResult result) {
        Usuario usuario = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errores = (List<String>)result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            response.put("errores", response);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        try {
            if (this.usuarioServiceImpl.findByEmail(value.getEmail()) != null) {
                this.logger.error("Error el correo electronico ya existe");
                response.put("Mensaje", "Correo electronico existente");
                response.put("Error", "Correo electronico existente");
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            if (this.usuarioServiceImpl.findByUsername(value.getUsername()) != null) {
                this.logger.error("Error el username ya existe");
                response.put("Mensaje", "Username existente");
                response.put("Error", "Username existente");
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            List<Role> roles = new ArrayList<>();
            Role role = this.roleService.findById(Long.valueOf(2L));
            roles.add(role);
            value.setEnabled(Boolean.valueOf(true));
            value.setPassword(this.passwordEncoder.encode(value.getPassword()));
            value.setRoles(roles);
            usuario = this.usuarioServiceImpl.save(value);
        } catch (CannotCreateTransactionException e) {
            this.logger.error("Error al momento de conectarse a la base de datos");
            response.put("Mensaje", "Error al momento de conectarse a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            this.logger.error("Error al realizar el insert a la base de datos");
            response.put("Mensaje", "Error al realizar el insert a la base de datos");
            response.put("Error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        this.logger.debug("Finalizando preceso de insert del usuario a la base de datos");
        response.put("Mensaje", "El usuario ha sido creado con éxito");
                response.put("Usuario", usuario);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}