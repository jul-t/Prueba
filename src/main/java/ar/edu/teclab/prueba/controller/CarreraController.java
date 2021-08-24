package ar.edu.teclab.prueba.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.teclab.prueba.entity.Carrera;
import ar.edu.teclab.prueba.service.CarreraService;

import java.util.List;

@RestController
@RequestMapping("/abm")
public class CarreraController {

    @Autowired
    CarreraService carreraService;

    @GetMapping("/carreras")
    private List<Carrera> getAllCarreras() {
        return carreraService.getAllCarreras();
    }

    @GetMapping("/carreras/{id}")
    private Carrera getCarrera(@PathVariable("id") int id) {
        return carreraService.getCarreraById(id);
    }

    @DeleteMapping("/carreras/{id}")
    private void deleteCarrera(@PathVariable("id") int id) {
        carreraService.delete(id);
    }

    @PostMapping("/carreras")
    private int saveCarrera(@RequestBody Carrera carrera) {
        carreraService.save(carrera);
        return carrera.getId();
    }
    
    @PutMapping("/carreras/{id}")
    private int updateCarrera(@PathVariable("id") int id, @RequestBody Carrera carrera) {
    	
    	//Detecto si el Json que estoy recibiendo tiene todos los parametros, solo actualizo los parametros que recibo
    	Carrera carreraT = carreraService.getCarreraById(id);
    	if (carrera.getNombre() != null){
    		carreraT.setNombre(carrera.getNombre());
    	}
    	if (carrera.getDuracion() != null){
    		carreraT.setDuracion(carrera.getDuracion());
    	}
    	carreraService.updateCarrera(carreraT);
        return carrera.getId();
    }
}