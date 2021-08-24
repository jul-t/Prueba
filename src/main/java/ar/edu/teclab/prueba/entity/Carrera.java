package ar.edu.teclab.prueba.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

//Getter y Setters configurados por annotation para Lombok
@Getter
@Setter
@Entity
public class Carrera {
	
	//Defino el Id como primary key con annotation de persistence y con auto-generacion
	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	private String duracion;
	private int materias;
}