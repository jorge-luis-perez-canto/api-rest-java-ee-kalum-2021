package edu.kalum.notas.core.controllers;


import edu.kalum.notas.core.controllers.AsignacionAlumnosController;
import edu.kalum.notas.core.models.entities.CarreraTecnica;
import edu.kalum.notas.core.models.entities.Modulo;
import edu.kalum.notas.core.models.services.ICarreraTecnicaServices;
import edu.kalum.notas.core.models.services.IModuloServices;
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
public class ModuloController {
    @Value("2")
    private Integer registros;

    private Logger logger = LoggerFactory.getLogger(AsignacionAlumnosController.class);

    @Autowired
    private ICarreraTecnicaServices carreraTecnicaServices;

    @Autowired
    private IModuloServices moduloServices;

    @Autowired
    private ICarreraTecnicaServices carreraServices;

    @GetMapping({"/modulos"})
    public ResponseEntity<?> listaModulos() {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("Iniciando el proceso de la consulta de los modulo en la base de datos.");
        try {
            this.logger.debug("Iniciando el proceso de la consulta a la base de datos");
            List<Modulo> listaModulos = this.moduloServices.findAll();
            if (listaModulos == null || listaModulos.size() == 0) {
                this.logger.warn("No existen registros en la tabla Modulos.");
                response.put("Mensajes", "No existen registros en la tabla Modulos.");
                return new ResponseEntity(response, HttpStatus.NO_CONTENT);
            }
            this.logger.info("Obteniendo lista de Modulos.");
            return new ResponseEntity(listaModulos, HttpStatus.OK);
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

    @GetMapping({"/modulos/{id}"})
    public ResponseEntity<?> show(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Modulo modulo = this.moduloServices.findById(id);
            if (modulo == null) {
                response.put("Mensaje", "No existe el modulo con el Id: ".concat(id));
                return new ResponseEntity(response, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(modulo, HttpStatus.OK);
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

    @GetMapping({"/modulos/page/{page}"})
    public ResponseEntity<?> index(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        PageRequest pageRequest = PageRequest.of(page.intValue(), 5);
        try {
            Page<Modulo> modulos = this.moduloServices.findAll((Pageable)pageRequest);
            if (modulos == null || modulos.getSize() == 0)
                return new ResponseEntity(response, HttpStatus.NO_CONTENT);
            return new ResponseEntity(modulos, HttpStatus.OK);
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

    @PostMapping({"/modulos"})
    public ResponseEntity<?> create(@Valid @RequestBody Modulo registro, BindingResult result) {
        Modulo modulo = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errores = (List<String>)result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores: ", errores);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        try {
            CarreraTecnica carreraTecnica = this.carreraServices.findByCodigoCarrera(registro.getCarreraTecnica().getCodigoCarrera());
            if (carreraTecnica == null) {
                response.put("Mensaje", "No existe la carrera con el Id: ".concat(registro.getCarreraTecnica().getCodigoCarrera()));
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            registro.setModulo_id(UUID.randomUUID().toString());
            modulo = this.moduloServices.save(registro);
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
        response.put("Mensaje", "El modulo fue creado con exito.");
        response.put("Modulo: ", modulo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping({"/modulos/{id}"})
    public ResponseEntity<?> update(@Valid @RequestBody Modulo update, BindingResult result, @PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errores = (List<String>)result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores: ", errores);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        Modulo modulo = this.moduloServices.findById(id);
        if (modulo == null) {
            response.put("Mensaje", "No existe el modulo con el Id: ".concat(id));
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        try {
            CarreraTecnica carreraTecnica = this.carreraServices.findByCodigoCarrera(update.getCarreraTecnica().getCodigoCarrera());
            if (carreraTecnica == null) {
                response.put("Mensaje", "No existe la carrera con el Id: ".concat(update.getCarreraTecnica().getCodigoCarrera()));
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            modulo.setNombre_modulo(update.getNombre_modulo());
            modulo.setNumero_seminarios(update.getNumero_seminarios());
            modulo.setCarreraTecnica(carreraTecnica);
            this.moduloServices.save(modulo);
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
        response.put("Mensaje", "El modulo ha sido modificado correctamente. ");
        response.put("Modulo: ", modulo);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping({"/modulos/{id}"})
    public ResponseEntity<?> delete(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        Modulo modulo = null;
        try {
            modulo = this.moduloServices.findById(id);
            if (modulo == null) {
                response.put("Mensaje", "No existe ningun modulo con el id: ".concat(id));
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            this.moduloServices.delete(modulo);
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
        response.put("Mensaje", "El modulo ha sido eliminada correctamente. ");
        response.put("Modulo: ", modulo);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}