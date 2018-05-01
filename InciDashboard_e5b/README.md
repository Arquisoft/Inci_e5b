

# InciDashboard_e5b

# Authors
## Autores
- Carlos Sanabria Miranda (@CarlosSanabriaM)
- Manuel Fernández Antuña (@uo2999)
- Adrián Pérez Carou (@adrycarou896)
- Mª Rosa Valdés Pire (@RosaValdesPire)


# Funcionamiento:

1. Arrancar HSQLDB
   * Para ello, acceder a la carpeta data/hsqldb/bin y lanzar el runServer.bat (o runServer.sh en caso de Linux/Mac).
2. Es necesario tener instalado Apache Kafka. 
   * Las instrucciones para su instalación y despliegue se encuentran en https://kafka.apache.org/quickstart.
2. Arrancar Apache Zookeeper
   * Para lanzarlo en Mac/Linux: ``bin/zookeeper-server-start.sh config/zookeeper.properties``
   * Para lanzarlo en Windows: ``bin\windows\zookeeper-server-start.bat config\zookeeper.properties``
3. Arrancar Apache Kafka
   * Para lanzarlo en Mac/Linux: ``bin/kafka-server-start.sh config/server.properties``
   * Para lanzarlo en Windows: ``bin\windows\kafka-server-start.bat config\server.properties``

Para arrancarlo todo y que funcione, se debe ejecutar el siguiente comando, estando situado en la carpeta InciDashboard_e5b:
``mvn spring-boot:run``

   
## Interfaz HTML
  1. Escribir en el navegador: http://localhost:8090/ 
  2. En el menu escoger entre identificarse o registrarse y hacer click.
  3. Al hacer click en el boton identificarse y si usted es operario deberá introducir su identifier y password.
    * Usuario de pruebas: ``IDENTIFIER: 99999999A PASSWORD: 123456``.
  4. Si selecciona registrarse deberá introducir sus datos.
  5. Una vez identificado, aparecerán en el menu 3 botones inicio/incidencias/filtro.
  6. ``Inicio`` es la pagina principal.
  7. ``Incidencias`` se abre un submenu en el que podrá elegir 3 opciones Actuales/Asignadas a mí/Por categorías.
  8. ``Actuales`` muestra las incidencias ocurridas en tiempo real por todos los operarios.
  9. ``Asignadas a mí`` muestra las incidencias asignadas a el operario que ha iniciado sesión.
  10. ``Por categorías`` puede ver las incidencias según una determinada categoría.
  11. ``Filtro`` se puede actualizar el filtro seleccionando e introduciendo una serie de campos.
  
  
