package edu.kalum.notas.core.controllers;


import edu.kalum.notas.core.controllers.AsignacionAlumnosController;
import edu.kalum.notas.core.models.entities.DetalleActividad;
import edu.kalum.notas.core.models.entities.Seminario;
import edu.kalum.notas.core.models.services.IDetalleActividadServices;
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
public class DetalleActividadController {
    @Value("2")
    private Integer registros;

    private Logger logger = LoggerFactory.getLogger(AsignacionAlumnosController.class);

    @Autowired
    private IDetalleActividadServices detalleActividadServices;

    @Autowired
    private ISeminarioServices seminarioServices;

    @GetMapping({"/detalleActividades"})
    public ResponseEntity<?> listaDetalleActividad() {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("Iniciando el proceso de la consulta de Detalle Actividad en la base de datos.");
        try {
            this.logger.debug("Iniciando el proceso de la consulta a la base de datos");
            List<DetalleActividad> listaDetalleActividad = this.detalleActividadServices.findAll();
            if (listaDetalleActividad == null || listaDetalleActividad.size() == 0) {
                this.logger.warn("No existen registros en la tabla Detalle Actividad.");
                response.put("Mensajes", "No existen registros en la tabla Detalle Actividad.");
                return new ResponseEntity(response, HttpStatus.NO_CONTENT);
            }
            this.logger.info("Obteniendo lista de Detalle Actividad.");
            return new ResponseEntity(listaDetalleActividad, HttpStatus.OK);
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

    @GetMapping({"/detalleActividades/page/{page}"})
    public ResponseEntity<?> index(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        PageRequest pageRequest = PageRequest.of(page.intValue(), 5);
        try {
            Page<DetalleActividad> detalleActividades = this.detalleActividadServices.findAll((Pageable) pageRequest);
            if (detalleActividades == null || detalleActividades.getSize() == 0)
                return new ResponseEntity(response, HttpStatus.NO_CONTENT);
            return new ResponseEntity(detalleActividades, HttpStatus.OK);
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

    @GetMapping({"/detalleActividades/{id}"})
    public ResponseEntity<?> show(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            DetalleActividad detalleActividad = this.detalleActividadServices.findById(id);
            if (detalleActividad == null) {
                response.put("Mensaje", "No existe el Detalle Actividad con el Id: ".concat(id));
                return new ResponseEntity(response, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(detalleActividad, HttpStatus.OK);
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

    @PostMapping({"/detalleActividades"})
    public ResponseEntity<?> create(@Valid @RequestBody DetalleActividad registro, BindingResult result) {
        DetalleActividad detalleActividad = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errores = (List<String>) result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores: ", errores);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        try {
            Seminario seminario = this.seminarioServices.findById(registro.getSeminario().getSeminarioId());
            if (seminario == null) {
                response.put("Mensaje", "No existe el seminario con el Id: ".concat(registro.getSeminario().getSeminarioId()));
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            registro.setDetalleActividadId(UUID.randomUUID().toString());
            detalleActividad = this.detalleActividadServices.save(registro);
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
        response.put("Mensaje", "El detalle actividad fue creado con exito.");
        response.put("Detalle Actividad: ", detalleActividad);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping({"/detalleActividades/{id}"})
    public ResponseEntity<?> update(@Valid @RequestBody DetalleActividad update, BindingResult result, @PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errores = (List<String>) result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores: ", errores);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        DetalleActividad detalleActividad = this.detalleActividadServices.findById(id);
        if (detalleActividad == null) {
            response.put("Mensaje", "No existe el detalle Actividad con el Id: ".concat(id));
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        try {
            Seminario seminario = this.seminarioServices.findById(update.getSeminario().getSeminarioId());
            if (seminario == null) {
                response.put("Mensaje", "No existe el seminario con el Id: ".concat(update.getSeminario().getSeminarioId()));
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            detalleActividad.setEstado(update.getEstado());
            detalleActividad.setFechaCreacion(update.getFechaCreacion());
            detalleActividad.setFechaEntrega(update.getFechaEntrega());
            detalleActividad.setFechaPostergacion(update.getFechaPostergacion());
            detalleActividad.setNombreActividad(update.getNombreActividad());
            detalleActividad.setNotaActividad(update.getNotaActividad());
            this.detalleActividadServices.save(detalleActividad);
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
        response.put("Mensaje", "El Detalle Actividad ha sido modificado correctamente. ");
        response.put("Detalle Actividad: ", detalleActividad);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping({"/detalleActividades/{id}"})
    public ResponseEntity<?> delete(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        DetalleActividad detalleActividad = null;
        try {
            detalleActividad = this.detalleActividadServices.findById(id);
            if (detalleActividad == null) {
                response.put("Mensaje", "No existe ningun detalle actividad con el id: ".concat(id));
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            this.detalleActividadServices.delete(detalleActividad);
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
        response.put("Mensaje", "El detalle actividad ha sido eliminada correctamente. ");
        response.put("Detalle Actividad: ", detalleActividad);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}