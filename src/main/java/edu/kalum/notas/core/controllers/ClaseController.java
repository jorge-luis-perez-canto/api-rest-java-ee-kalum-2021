package edu.kalum.notas.core.controllers;


import edu.kalum.notas.core.controllers.AlumnoController;
import edu.kalum.notas.core.models.entities.Clase;
import edu.kalum.notas.core.models.services.IClaseServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/kalum-notas/v1"})
public class ClaseController {
    private Logger logger = LoggerFactory.getLogger(AlumnoController.class);

    @Autowired
    private IClaseServices claseService;

    @GetMapping({"/clases"})
    public ResponseEntity<?> listarClases() {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("Iniaciando el proceso de la consulta de clases en la base de datos.");
        try {
            this.logger.debug("Iniciando la consulta de base de datos.");
            List<Clase> listaClases = this.claseService.findAll();
            if (listaClases == null || listaClases.size() == 0) {
                this.logger.warn("No existen registros en la tabla de clases.");
                response.put("Mensaje", "No existen registros en la tabla clases.");
                return new ResponseEntity(response, HttpStatus.NO_CONTENT);
            }
            this.logger.info("Obteniendo listado de la informacion de alumnos.");
            return new ResponseEntity(listaClases, HttpStatus.OK);
        } catch (CannotCreateTransactionException e) {
            this.logger.error("Error al momento de conectarse a la base de datos.");
            response.put("Mensaje", "No error al momento de conectarse a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            this.logger.error("Error al momento de conectarse a la base de Datos.");
            response.put("Mensaje", "Error al momento de consultar la informacion a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @GetMapping({"/clases/{uuid}"})
    public ResponseEntity<?> showClase(@PathVariable String uuid) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("Iniaciando el proceso de la consulta de clases en la base de datos.");
        try {
            this.logger.debug("Iniciando la consulta de base de datos por cde clase: ".concat(uuid));
            Clase clase = this.claseService.findById(uuid);
            if (clase == null) {
                this.logger.warn("No existe en la tabla de clases con cde clase: ".concat(uuid));
                response.put("Mensaje", "Error al conectarse a la base de datos.");
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            this.logger.info("Obteniendo informacia la base de datos.".concat(uuid));
            return new ResponseEntity(clase, HttpStatus.OK);
        } catch (CannotCreateTransactionException e) {
            this.logger.error("Error al momento de conectarse a la base de datos.");
            response.put("Mensaje", "No error al momento de conectarse a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            this.logger.error("Error al momento de conectarse a la base de Datos.");
            response.put("Mensaje", "Error al momento de consultar la informacion a la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}