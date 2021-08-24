package ar.edu.teclab.prueba.controller;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import ar.edu.teclab.prueba.service.*;
import ar.edu.teclab.prueba.dto.*;

@RestController
@RequestMapping("/zendesk")
@CrossOrigin(origins = "*")
public class ZendeskController {

	private static final Log LOG = LogFactory.getLog(ZendeskController.class);
	
	//Se definen parametros de servidor remoto Zendesk
	private static final String urlZendeskApi = "https://zentestapi.zendesk.com/";
	private static final String mailZendeskApi = "";
	private static final String passwordZendeskApi = "";

	@Autowired
	protected ZendeskService pruebaService;

	@GetMapping("/getcomments")
	public ResponseEntity<String> getJsonTickets(@RequestParam(value = "ticket_id") String ticket_id) {
		try {

			final String params = "/api/v2/tickets/" + ticket_id + "/comments.json";

			RestTemplate restTemplate = new RestTemplate();

			//Agrego basic-auth al header para autenticar la api
			restTemplate.getInterceptors()
					.add(new BasicAuthorizationInterceptor(mailZendeskApi, passwordZendeskApi));

			//Hago la llamada a la api remota
			ResponseEntity<String> response = restTemplate.exchange(urlZendeskApi + params, HttpMethod.GET, null, String.class);

			return response;
		} catch (Exception e) {
			LOG.error("Error", e);
		}
		return null;

	}

	@PutMapping("/postcomment")
	public ResponseEntity<String> postComment(@RequestParam(value = "ticket_id") String ticket_id,
			@RequestBody String message) {
		try {

			final String params = "/api/v2/tickets/" + ticket_id;

			RestTemplate restTemplate = new RestTemplate();
			
			//Agrego basic-auth al header para autenticar la api
			restTemplate.getInterceptors()
					.add(new BasicAuthorizationInterceptor(mailZendeskApi, passwordZendeskApi));
			

			//Creo un objeto Ticket anidado con un objeto Comment y otro objeto Body dentro, para generar las anidaciones de JSON Automaticamente para el PUT
			//Se utiliza Lombok para codificacion rapida
			
			Body commentBody = Body.builder().body(message).build();
			Comment comment = Comment.builder().comment(commentBody).build();
			Ticket ticket = Ticket.builder().ticket(comment).build();

			//Utilizo Gson para parsear el objeto Ticket a un string formateado en JSON
			Gson gson = new Gson();
			String commentJson = gson.toJson(ticket);

			//Cambio el content-type del header a JSON para que Zendesk identifique el tipo de contenido
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);


			HttpEntity requestEntity = new HttpEntity(commentJson, headers);

			//Hago la llamada a la api remota
			ResponseEntity<String> response = restTemplate.exchange(urlZendeskApi + params, HttpMethod.PUT, requestEntity,
					String.class);

			return response;
		} catch (Exception e) {
			LOG.error("Error", e);
		}
		return null;

	}

}
