#Api Zendesk
#USO:

GET a http://localhost:8093/zendesk/getcomments?ticket_id={ticketid} devuelve un JSON con todos los comentarios del ticket indicado

POST a http://localhost:8093/zendesk/postcomment?ticket_id={ticketid} con texto plano sin formatear en el body del POST, postea dicho texto como comentario del ticket con la id indicada en el parametro






#ABM de Carrera
#USO:

GET a http://localhost:8093/abm/carreras/ trae un JSON con un detalle de todas las carreras

GET a http://localhost:8093/abm/carreras/{id} trae un JSON con el detalle de la carrera correspondiente al Id pasado por parametro

POST a http://localhost:8093/abm/carreras con JSON en el body formateado como ejemplo:

{
    "nombre":"matematica",
    "duracion":"3 años"
}

Agrega una carrera a la base de datos H2 en memoria, con una Id auto incremental


DELETE a http://localhost:8093/abm/carreras/{id} pasando por parametro el id de la carrera, elimina la carrera de la base


PUT a http://localhost:8093/abm/carreras/{id} con JSON en el body formateado como ejemplo y el id de la carrera a actualizar:

{
    "nombre":"Medicina"
}

actualiza  el valor del parametro que le pasamos para la carrera indicada






##Consideraciones:

*Se eliminaron credenciales de acceso a Zendesk por ser un repositorio de acceso publico

*Se utilizo Eclipse como IDE, con el modulo Lombok instalado para las notaciones de Getter y Setters

*Se utilizo base de datos temporal en memoria H2
