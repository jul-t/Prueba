package ar.edu.teclab.prueba.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//Getter y Setters configurados por annotation para Lombok
@Builder
@Getter
@Setter
public class Comment {
	private Body comment;

}
