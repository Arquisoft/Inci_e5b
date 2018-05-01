$(document).ready(function() {
	var tabla = $("#tablaIncidenciasBody");

	var socket = new SockJS('/stomp');

	var stompClient = Stomp.over(socket);
	
	var arrayIncidences = [];

	stompClient.connect({}, function(frame) {
		stompClient.subscribe("/topic/incidences", function(data) {
			var incidencia = JSON.parse(data.body);
			var encontrado = false;

			$(".nameIncidencia").each(function(i) {
				if (this.innerHTML === incidencia.contents) {
					encontrado = true;
					return false;
				}
			});

			if (!encontrado) {
				
				// Cambiamos el true o false de dangerous por si o no
				var textoDangerous;
				if(incidencia.dangerous) textoDangerous = "Sí";
				else textoDangerous = "No";
				
				// Si algun campo es null, lo cambiamos por -
				if(incidencia.expiration == null) incidencia.expiration = "-";
				if(incidencia.tags == null || incidencia.tags.length == 0) incidencia.tags = "-";
				
				// Sacamos los valores de las properties
				var propertiesString;
				if(incidencia.properties == null || incidencia.properties.length == 0){
					propertiesString = "-";
				}else{
					propertiesString = [];
					// Iteramos por las propiadedes para sacar sus valores como texto
					for(var i = 0; i<incidencia.properties.length; i++){
						var key = incidencia.properties[i].name;
						var value = incidencia.properties[i].value;
						propertiesString.push(key+"="+value+" ");
					}
				}
				
				// Si la localizacion es null, no mostramos el mapa
				var mapa;
				if(incidencia.location == null){
					mapa = "Localización no disponible";
				}else{
					mapa = "<iframe width='300' height='300' frameborder='0' style='border:0' src='https://www.google.com/maps/embed/v1/view?key=AIzaSyAnjyWNjAWTI8Cr80Uqv0thhdpLUpm3cNk&center=" + incidencia.location + "&zoom=18&maptype=satellite' allowfullscreen></iframe>";
				}
				
				
				var htmlstring = "<tr>" +
				        "<td class='nameIncidencia'>" + incidencia.name + "</td>" +
				        "<td>" + incidencia.description + "</td>" +
				        "<td>" + incidencia.expiration + "</td>" +
				        "<td>" + incidencia.tags + "</td>" +
				        "<td>" + propertiesString + "</td>" +
				        "<td id='dangerous"+ incidencia.identifier +"'>" + textoDangerous + "</td>" +
				        "<td>" + mapa + "</td>" +
			        "</tr>";
				
				tabla.prepend(htmlstring);
				
				
				// Si la incidencia es peligrosa, incrementamos el numero de incidencias peligrosas
				if(incidencia.dangerous){
					nTrue++;
					$("#dangerous"+incidencia.identifier).css("background-color", "rgb(255, 97, 90)");
				}
				else{
					nFalse++;
					$("#dangerous"+incidencia.identifier).css("background-color", "rgb(157, 255, 251)");
				}
				
				
				//Actualizamos el grafico
				drawChart();
				
			}
		});
	});
});