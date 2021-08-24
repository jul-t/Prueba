package ar.edu.teclab.prueba.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import ar.edu.teclab.prueba.entity.Carrera;
import ar.edu.teclab.prueba.model.CarreraRepository;


@Service
public class CarreraService {

    @Autowired
    CarreraRepository carreraRepository;

    public List<Carrera> getAllCarreras() {
        List<Carrera> carreras = new ArrayList<Carrera>();
        carreraRepository.findAll().forEach(Carrera -> carreras.add(Carrera));
        return carreras;
    }

    public Carrera getCarreraById(int id) {
        return carreraRepository.findOne(id);
    }
    
    
    public void save(Carrera carrera) {
        carreraRepository.save(carrera);
    }

    public void delete(int id) {
        carreraRepository.delete(id);
    }

	public void updateCarrera(Carrera carreraT) {
		 carreraRepository.save(carreraT);
		
	}
}