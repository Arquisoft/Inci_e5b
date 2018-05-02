Feature: marcar incidencias como peligrosas
 
  Scenario: las incidencias que contengan el tag fuego son marcadas como peligrosas
    Given una lista de incidencias:
      | name    | description 	| tag1	| tag2 	| 
      | inc1    | descr1   	 	| fuego	| calor	|
      | inc2    | descr2    		| frio	| agua	|
      | inc3    | descr3    		| calor	| miedo	|
    	And el tag "fuego"
    		
    When el filtro se configura para marcar como peligrosas las incidencias que contengan el tag
    And se aplica el filtro sobre la lista de incidencias
    
    Then solamente la incidencia inc1 es marcada como peligrosa