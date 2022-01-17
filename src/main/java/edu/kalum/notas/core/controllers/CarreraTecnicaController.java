package edu.kalum.notas.core.controllers;

import edu.kalum.notas.core.models.entities.CarreraTecnica;
import edu.kalum.notas.core.models.services.ICarreraTecnicaServices;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/kalum-notas/v1"})
@CrossOrigin(origins = {"*"})
public class CarreraTecnicaController {
    private Logger logger = LoggerFactory.getLogger(edu.kalum.notas.core.controllers.CarreraTecnicaController.class);

    @Autowired
    private ICarreraTecnicaServices carreraTecnicaServices;

    @GetMapping({"/carreras"})
    public ResponseEntity<?> listarCarreras() {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("Iniaciando el proceso de la consulta de carreras en la base de datos.");
        try {
            this.logger.debug("Iniciando la consulta de base de datos.");
            List<CarreraTecnica> listaCarreras = this.carreraTecnicaServices.findAll();
            if (listaCarreras == null || listaCarreras.size() == 0) {
                this.logger.warn("No existen registros en la tabla de carreras.");
                response.put("Mensaje", "No existen registros en la tabla carreras.");
                return new ResponseEntity(response, HttpStatus.NO_CONTENT);
            }
            this.logger.info("Obteniendo listado de la informacion de carreras.");
            return new ResponseEntity(listaCarreras, HttpStatus.OK);
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

    @GetMapping({"/carreras/{codigo_carrera}"})
    public ResponseEntity<?> showCarreras(@PathVariable String codigo_carrera) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("Iniciando el proceso de la consulta de carreras en la base de datos.");
        try {
            this.logger.debug("Iniciando la consulta de base de datos por numero de carne: ".concat(codigo_carrera));
            CarreraTecnica carrera = this.carreraTecnicaServices.findByCodigoCarrera(codigo_carrera);
            if (carrera == null) {
                this.logger.warn("No existe en la tabla de carreras con el codigo de carrera.: ".concat(codigo_carrera));
                response.put("Mensaje", "Error al conectarse a la base de datos.");
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            this.logger.info("Obteniendo informacia la base de datos.".concat(codigo_carrera));
            return new ResponseEntity(carrera, HttpStatus.OK);
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