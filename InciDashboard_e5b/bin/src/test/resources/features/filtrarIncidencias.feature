Feature: filtrar incidencias mediante el filtro
 
	  Scenario: solo pasan el filtro las incidencias que contengan el tag fuego 
			    Given una lista de incidencias con tags:
			      | name    | description 	| tag1	| tag2 	| 
			      | inc1    | descr1   	 	| fuego	| calor	|
			      | inc2    | descr2    		| frio	| agua	|
			      | inc3    | descr3    		| calor	| miedo	|
			    	And el tag con valor "fuego"
			    		
			    When el filtro se configura para solo dejar pasar las incidencias que contengan el tag
			    And se aplica el filtro sobre la lista de incidencias con tags
			    
			    Then solamente la incidencia inc1 pasa el filtro
	    
	  Scenario: solo pasan el filtro las incidencias que tengan una propiedad temperatura con valor superior a 10
			    Given una lista de incidencias con properties:
			      | name    | description 	| propertyName	| propertyValue 	| 
			      | inc1    | descr1   	 	| temperatura	| 20				|
			      | inc2    | descr2    		| temperatura	| 5				|
			      | inc3    | descr3    		| aire			| 100			|
			    	And la property con nombre "temperatura" cuyo valor debe ser mayor de 10
			    		
			    When el filtro se configura para solo dejar pasar las incidencias cuya temperatura sea superior a 10
			    And se aplica el filtro sobre la lista de incidencias con properties
			    
			    Then solamente la incidencia con nombre inc1 pasa el filtro