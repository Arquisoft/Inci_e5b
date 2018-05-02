Feature: añadir comentario incidencia
 
  Scenario: operario añade un comentario a una de sus incidencias asignadas
    Given el operario con el identificador "99999999A"
    	And la primera de sus incidencias asignadas
    		
    When el operario añade a la incidencia el comentario "Incidencia solucionada correctamente"
    And la incidencia se actualiza en base de datos
    And la incidencia se recupera de la base de datos
    
    Then la incidencia tiene el comentario "Incidencia solucionada correctamente"