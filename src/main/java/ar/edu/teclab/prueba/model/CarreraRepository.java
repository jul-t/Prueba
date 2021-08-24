package ar.edu.teclab.prueba.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.teclab.prueba.entity.Carrera;

//Extiendo el CrudRepository y le defino el tipo Carrera como dato, integer como indice
@Repository
public interface CarreraRepository extends CrudRepository<Carrera, Integer> {

}
