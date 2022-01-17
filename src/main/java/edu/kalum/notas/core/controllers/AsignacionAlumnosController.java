package edu.kalum.notas.core.controllers;

import edu.kalum.notas.core.models.entities.Alumno;
import edu.kalum.notas.core.models.entities.AsignacionAlumno;
import edu.kalum.notas.core.models.entities.Clase;
import edu.kalum.notas.core.models.services.IAlumnoServices;
import edu.kalum.notas.core.models.services.IAsignacionAlumnoServices;
import edu.kalum.notas.core.models.services.IClaseServices;

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
public class AsignacionAlumnosController {
    @Value("${edu.kalum.configuration.page.registros}")
    private Integer registros;

    private Logger logger = LoggerFactory.getLogger(edu.kalum.notas.core.controllers.AsignacionAlumnosController.class);

    @Autowired
    private IAsignacionAlumnoServices asignacionAlumnoServices;

    @Autowired
    private IAlumnoServices alumnoService;

    @Autowired
    private IClaseServices claseServices;

    @GetMapping({"/asignaciones/page/{page}"})
    public ResponseEntity<?> index(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        PageRequest pageRequest = PageRequest.of(page.intValue(), 5);
        try {
            Page<AsignacionAlumno> asignaciones = this.asignacionAlumnoServices.findAll((Pageable) pageRequest);
            if (asignaciones == null || asignaciones.getSize() == 0)
                return new ResponseEntity(response, HttpStatus.NO_CONTENT);
            return new ResponseEntity(asignaciones, HttpStatus.OK);
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

    @GetMapping({"/asignaciones/{id}"})
    public ResponseEntity<?> show(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            AsignacionAlumno asignacion = this.asignacionAlumnoServices.findById(id);
            if (asignacion == null) {
                response.put("Mensaje", "No existe la asiganacion con el Id: ".concat(id));
                return new ResponseEntity(response, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(asignacion, HttpStatus.OK);
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

    @GetMapping({"/asignaciones"})
    public ResponseEntity<?> listaAsignaciones() {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("Iniciando el proceso de la consulta de las asignaciones en la base de datos.");
        try {
            this.logger.debug("Iniciando el proceso de la consulta a la base de datos");
            List<AsignacionAlumno> listaAsignaciones = this.asignacionAlumnoServices.findAll();
            if (listaAsignaciones == null || listaAsignaciones.size() == 0) {
                this.logger.warn("No existen registros en la tabla Asignaciones.");
                response.put("Mensajes", "No existen registros en la tabla Asignaciones.");
                return new ResponseEntity(response, HttpStatus.NO_CONTENT);
            }
            this.logger.info("Obteniendo lista de Asignaciones.");
            return new ResponseEntity(listaAsignaciones, HttpStatus.OK);
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

    @PostMapping({"/asignaciones"})
    public ResponseEntity<?> create(@Valid @RequestBody AsignacionAlumno registro, BindingResult result) {
        AsignacionAlumno asignacionAlumno = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errores = (List<String>) result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores: ", errores);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        try {
            Alumno alumno = this.alumnoService.findByCarne(registro.getAlumno().getCarne());
            if (alumno == null) {
                response.put("Mensaje", "No existe la asiganacion con el Id: ".concat(registro.getAlumno().getCarne()));
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            Clase clase = this.claseServices.findById(registro.getClase().getClaseId());
            if (clase == null) {
                response.put("Mensaje", "No existe la asiganacion con el Id: ".concat(registro.getAlumno().getCarne()));
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            registro.setAsignacionId(UUID.randomUUID().toString());
            asignacionAlumno = this.asignacionAlumnoServices.save(registro);
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
        response.put("Mensaje", "La asignacifue creada con exito.");
        response.put("Asiganaci", asignacionAlumno);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping({"/asignaciones/{id}"})
    public ResponseEntity<?> update(@Valid @RequestBody AsignacionAlumno update, BindingResult result, @PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errores = (List<String>) result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores: ", errores);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        AsignacionAlumno asignacion = this.asignacionAlumnoServices.findById(id);
        if (asignacion == null) {
            response.put("Mensaje", "No existe la asiganacion con el Id: ".concat(id));
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        try {
            Alumno alumno = this.alumnoService.findByCarne(update.getAlumno().getCarne());
            if (alumno == null) {
                response.put("Mensaje", "No existe la asignacion con el Id: ".concat(update.getAlumno().getCarne()));
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            Clase clase = this.claseServices.findById(update.getClase().getClaseId());
            if (clase == null) {
                response.put("Mensaje", "No existe la asignacion con el Id: ".concat(update.getAlumno().getCarne()));
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            asignacion.setFechaAsignacion(update.getFechaAsignacion());
            asignacion.setAlumno(alumno);
            asignacion.setClase(clase);
            this.asignacionAlumnoServices.save(asignacion);
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
        response.put("Mensaje", "La asignaciha sido modificada correctamente. ");
        response.put("Asignacion", asignacion);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping({"/asignaciones/{id}"})
    public ResponseEntity<?> delete(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        AsignacionAlumno asignacion = null;
        try {
            asignacion = this.asignacionAlumnoServices.findById(id);
            if (asignacion == null) {
                response.put("Mensaje", "No existe ninguna asignacion con el id: ".concat(id));
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            this.asignacionAlumnoServices.delete(asignacion);
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
        response.put("Mensaje", "La asignaciha sido eliminada correctamente. ");
        response.put("Asignacion", asignacion);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}