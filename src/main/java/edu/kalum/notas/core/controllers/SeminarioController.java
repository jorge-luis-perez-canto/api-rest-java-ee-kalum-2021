package edu.kalum.notas.core.controllers;


import edu.kalum.notas.core.controllers.AsignacionAlumnosController;
import edu.kalum.notas.core.models.entities.Modulo;
import edu.kalum.notas.core.models.entities.Seminario;
import edu.kalum.notas.core.models.services.IModuloServices;
import edu.kalum.notas.core.models.services.ISeminarioServices;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/kalum-notas/v1"})
public class SeminarioController {
    @Value("2")
    private Integer registros;

    private Logger logger = LoggerFactory.getLogger(AsignacionAlumnosController.class);

    @Autowired
    private ISeminarioServices seminarioServices;

    @Autowired
    private IModuloServices moduloServices;

    @GetMapping({"/seminarios"})
    public ResponseEntity<?> listaSeminarios() {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("Iniciando el proceso de la consulta de los seminarios en la base de datos.");
        try {
            this.logger.debug("Iniciando el proceso de la consulta a la base de datos");
            List<Seminario> listaSeminarios = this.seminarioServices.findAll();
            if (listaSeminarios == null || listaSeminarios.size() == 0) {
                this.logger.warn("No existen registros en la tabla seminarios.");
                response.put("Mensajes", "No existen registros en la tabla seminarios");
                return new ResponseEntity(response, HttpStatus.NO_CONTENT);
            }
            this.logger.info("Obteniendo lista de seminarios.");
            return new ResponseEntity(listaSeminarios, HttpStatus.OK);
        } catch (CannotCreateTransactionException e) {
            this.logger.error("Error al momento de conectarse a la base de datos.");
            response.put("Mensaje", "Error al momento de conectase a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            this.logger.error("Error al momento de consultar a la base de datos.");
            response.put("Mensaje", "Error al momento de conectase a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @GetMapping({"/seminarios/page/{page}"})
    public ResponseEntity<?> index(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        PageRequest pageRequest = PageRequest.of(page.intValue(), 5);
        try {
            Page<Seminario> seminarios = this.seminarioServices.findAll((Pageable)pageRequest);
            if (seminarios == null || seminarios.getSize() == 0)
                return new ResponseEntity(response, HttpStatus.NO_CONTENT);
            return new ResponseEntity(seminarios, HttpStatus.OK);
        } catch (CannotCreateTransactionException e) {
            this.logger.error("Error al momento de conectarse a la base de datos.");
            response.put("Mensaje", "Error al momento de conectase a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            this.logger.error("Error al momento de consultar a la base de datos.");
            response.put("Mensaje", "Error al momento de conectase a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @GetMapping({"/seminarios/{id}"})
    public ResponseEntity<?> show(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Seminario seminarios = this.seminarioServices.findById(id);
            if (seminarios == null) {
                response.put("Mensaje", "No existe el seminario con el Id: ".concat(id));
                return new ResponseEntity(response, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(seminarios, HttpStatus.OK);
        } catch (CannotCreateTransactionException e) {
            this.logger.error("Error al momento de conectarse a la base de datos.");
            response.put("Mensaje", "Error al momento de conectase a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            this.logger.error("Error al momento de consultar a la base de datos.");
            response.put("Mensaje", "Error al momento de conectase a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PostMapping({"/seminarios"})
    public ResponseEntity<?> create(@Valid @RequestBody Seminario registro, BindingResult result) {
        Seminario seminario = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errores = (List<String>)result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores: ", errores);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        try {
            Modulo modulos = this.moduloServices.findById(registro.getModulo().getModulo_id());
            if (modulos == null) {
                response.put("Mensaje", "No existe el modulo con el Id: ".concat(registro.getModulo().getModulo_id()));
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            registro.setSeminarioId(UUID.randomUUID().toString());
            seminario = this.seminarioServices.save(registro);
        } catch (CannotCreateTransactionException e) {
            this.logger.error("Error al momento de conectarse a la base de datos.");
            response.put("Mensaje", "Error al momento de conectase a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            this.logger.error("Error al momento de consultar a la base de datos.");
            response.put("Mensaje", "Error al momento de conectase a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
        response.put("Mensaje", "El seminario fue creado con exito.");
        response.put("Modulo: ", seminario);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping({"/seminarios/{id}"})
    public ResponseEntity<?> update(@Valid @RequestBody Seminario update, BindingResult result, @PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errores = (List<String>)result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores: ", errores);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        Seminario seminario = this.seminarioServices.findById(id);
        if (seminario == null) {
            response.put("Mensaje", "No existe el seminario con el Id: ".concat(id));
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        try {
            Modulo modulos = this.moduloServices.findById(update.getModulo().getModulo_id());
            if (modulos == null) {
                response.put("Mensaje", "No existe el modulo con el Id: ".concat(update.getModulo().getModulo_id()));
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            seminario.setFechaFin(update.getFechaFin());
            seminario.setFechaInicio(update.getFechaInicio());
            seminario.setNombreSeminario(update.getNombreSeminario());
            seminario.setModulo(modulos);
            this.seminarioServices.save(seminario);
        } catch (CannotCreateTransactionException e) {
            this.logger.error("Error al momento de conectarse a la base de datos.");
            response.put("Mensaje", "Error al momento de conectase a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            this.logger.error("Error al momento de consultar a la base de datos.");
            response.put("Mensaje", "Error al momento de conectase a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
        response.put("Mensaje", "El seminario ha sido modificado correctamente. ");
        response.put("Seminario: ", seminario);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping({"/seminarios/{id}"})
    public ResponseEntity<?> delete(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        Seminario seminario = null;
        try {
            seminario = this.seminarioServices.findById(id);
            if (seminario == null) {
                response.put("Mensaje", "No existe ningun seminario con el id: ".concat(id));
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            this.seminarioServices.delete(seminario);
        } catch (CannotCreateTransactionException e) {
            this.logger.error("Error al momento de conectarse a la base de datos.");
            response.put("Mensaje", "Error al momento de conectase a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            this.logger.error("Error al momento de consultar a la base de datos.");
            response.put("Mensaje", "Error al momento de conectase a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
        response.put("Mensaje", "El seminario ha sido eliminada correctamente. ");
        response.put("Seminario: ", seminario);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}