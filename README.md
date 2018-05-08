# Inci_e5b
[![Build Status](https://travis-ci.org/Arquisoft/Inci_e5b.svg?branch=master)](https://travis-ci.org/Arquisoft/Inci_e5b)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b9f2956baa714f48b3e0a642d24886b5)](https://www.codacy.com/app/CarlosSanabriaM/Inci_e5b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Inci_e5b&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/Arquisoft/Inci_e5b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/Inci_e5b)

## Distintos módulos

| Proyecto | Build status | Codacy | Code Coverture | Deployment |
|----------|--------------|--------|----------------|------------|
[Loader_e5b](https://github.com/Arquisoft/Loader_e5b) | [![Build Status](https://travis-ci.org/Arquisoft/Loader_e5b.svg?branch=master)](https://travis-ci.org/Arquisoft/Loader_e5b) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/97d6326cbcbb4c638d59879facacaf32)](https://www.codacy.com/app/jelabra/Loader_e5b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Loader_e5b&amp;utm_campaign=Badge_Grade) | [![codecov](https://codecov.io/gh/Arquisoft/Loader_e5b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/Loader_e5b) | 
[Agents_e5b](https://github.com/Arquisoft/Agents_e5b) | [![Build Status](https://travis-ci.org/Arquisoft/Agents_e5b.svg?branch=master)](https://travis-ci.org/Arquisoft/Agents_e5b) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/2d1976960db9415892b85d741bb4a336)](https://www.codacy.com/app/jelabra/Agents_e5b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Agents_e5b&amp;utm_campaign=Badge_Grade) | [![codecov](https://codecov.io/gh/Arquisoft/Agents_e5b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/Agents_e5b) | [Agents Web](http://54.201.168.73:8080/)
[InciManager_e5b](https://github.com/Arquisoft/InciManager_e5b) | [![Build Status](https://travis-ci.org/Arquisoft/InciManager_e5b.svg?branch=master)](https://travis-ci.org/Arquisoft/InciManager_e5b) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/97d6326cbcbb4c638d59879facacaf32)](https://www.codacy.com/app/jelabra/InciManager_e5b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/InciManager_e5b&amp;utm_campaign=Badge_Grade) | [![codecov](https://codecov.io/gh/Arquisoft/InciManager_e5b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciManager_e5b) | [Manager Web](http://54.201.168.73:9010/)
[InciDashboard_e5b](https://github.com/Arquisoft/InciDashboard_e5b) | [![Build Status](https://travis-ci.org/Arquisoft/InciDashboard_e5b.svg?branch=master)](https://travis-ci.org/Arquisoft/InciDashboard_e5b) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/2d1976960db9415892b85d741bb4a336)](https://www.codacy.com/app/jelabra/InciDashboard_e5b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/InciDashboard_e5b&amp;utm_campaign=Badge_Grade) | [![codecov](https://codecov.io/gh/Arquisoft/InciDashboard_e5b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciDashboard_e5b) | [Dashboard Web](http://54.201.168.73:8090/)

## Autores
- Carlos Sanabria Miranda ([@CarlosSanabriaM](https://github.com/CarlosSanabriaM))
- Manuel Fernández Antuña ([@uo2999](https://github.com/uo2999))
- Adrián Pérez Carou ([@adrycarou896](https://github.com/adrycarou896))
- Mª Rosa Valdés Pire ([@RosaValdesPire](https://github.com/RosaValdesPire))
- Mateo Juarez Verdugo ([@UO251123](https://github.com/UO251123))
- Manuel Mori Álvarez ([@ManuelMori](https://github.com/ManuelMori))

# Funcionamiento:

1. Arrancar HSQLDB
   * Para ello, acceder a la carpeta hsqldb/bin y lanzar el runServer.bat (o runServer.sh en caso de Linux/Mac).
2. Es necesario tener instalado Apache Kafka. 
   * Las instrucciones para su instalación y despliegue se encuentran en https://kafka.apache.org/quickstart.
2. Arrancar Apache Zookeeper
   * Para lanzarlo en Mac/Linux: ``bin/zookeeper-server-start.sh config/zookeeper.properties``
   * Para lanzarlo en Windows: ``bin\windows\zookeeper-server-start.bat config\zookeeper.properties``
3. Arrancar Apache Kafka
   * Para lanzarlo en Mac/Linux: ``bin/kafka-server-start.sh config/server.properties``
   * Para lanzarlo en Windows: ``bin\windows\kafka-server-start.bat config\server.properties``
4. Arrancar InciDashboard
   * Ejecutar el siguiente comando, estando situado en la carpeta InciDashboard_e5b: ``mvn spring-boot:run``
5. Arrancar Agents
   * Ejecutar el siguiente comando, estando situado en la carpeta Agents_e5b: ``mvn spring-boot:run``
6. Arrancar Manager
   * Ejecutar el siguiente comando, estando situado en la carpeta InciManager_e5b: ``mvn spring-boot:run``

## Probar Dashboard
  1. Escribir en el navegador: http://localhost:8090/ para acceder a la parte de Dashboard
  2. En el menu hacer click en identificarse.
  3. Deberá introducir su identifier y password.
    * Usuario de pruebas: ``IDENTIFIER: 99999999A PASSWORD: 123456``.
  4. Una vez identificado, aparecerán en el menu 3 botones inicio/incidencias/filtro.
  5. ``Inicio`` es la pagina principal.
  6. ``Incidencias`` se abre un submenu en el que podrá elegir 3 opciones Actuales/Asignadas a mí/Por categorías.
  7. ``Incidencias/Actuales`` muestra las incidencias ocurridas en tiempo real por todos los operarios.
  8. ``Incidencias/Asignadas a mí`` muestra las incidencias asignadas a el operario que ha iniciado sesión.
  9. ``Incidencias/Por categorías`` puede ver las incidencias según una determinada categoría.
  10. ``Filtro`` se puede actualizar el filtro, para modificar las incidencias que se muestran en la vista de incidencias actuales o para marcar determinadas incidencias como peligrosas.

Para más información acerca del módulo Dashboard, consultar la siguiente guía: https://github.com/Arquisoft/InciDashboard_e5b/blob/master/README.md
  
## Probar Manager
  1. Escribir en el navegador: http://localhost:9010/ para acceder a la parte de Manager
  2. En el menu hacer click en ``Insertar incidencia``.
  3. Deberá introducir un valor para todos los campos.
      * Datos del agente de pruebas: ``Usuario: 31668313G, Password: 1234, Tipo agente: Person``.
  4. La localización ha de seguir un formato como el de este ejemplo: ``+15.36,-10.11``.
  5. Las etiquetas han de seguir un formato como el de este ejemplo: ``urgente;incendio;domestico`` (cada etiqueta separada por punto y coma).
  6. Las propiedades han de seguir un formato como el de este ejemplo: ``temperatura:50;humedad:70`` (cada propiedad se indica así ``propiedad:valor``, y cada propiedad va separada por punto y coma).
  7. Una vez rellenados todos los campos, se clicka el botón de Enviar.
      * Si los datos del agente son válidos, se le indicará mediante un mensaje.
      * Si no son válidos, se le mostrará un mensaje de error.
  8. También es posible mandar incidencias a través de un servicio web REST, mediante la URI ``/api/incidence`` y el método POST.
      * Las incidencias se enviarán en formato JSON, con la siguiente estructura:
   
   ```json
   {
     "login": "31668313G",
     "password": "1234",
     "kind": "Person",
  	 "name": "Fuego en Llamaquique",
     "description" : "Se ha producido un leve fuego en el Edificio de Ciencias",
  	 "tags" : "calor;fuego;chamusquina",
	 "properties" : "calor:50;aire:10"
   }
   ```
   
  9. Si los datos del agente son válidos, se creará la incidencia y se devolverá un código 200 OK. Si no, no se creará 
  y se devolverá un código 404 Not Found.
 
Para más información acerca del módulo Manager, consultar la siguiente guía: https://github.com/Arquisoft/InciManager_e5b/blob/master/README.md
  
## Probar Agents
  1. Escribir en el navegador: http://localhost:8080/
  2. Proporcionar los datos de login para los 3 agentes disponibles (uno de cada tipo):
     * Login: 31668313G  Password: 1234  Kind: Person
     * Login: A58818501  Password: 1234  Kind: Entity
     * Login: 525695S    Password: 1234  Kind: Sensor
  3. Aparecerá la pantalla que muestra los datos del agente
     * Se puede ir a la pantalla de cambio de datos
     * Se puede ir a la pantalla de inicio
  4. Cambio de datos:
     * Se puede cambiar la contraseña
       * Escribir la contraseña antigua
       * Escribir la contraseña nueva
     * Se puede cambiar el email del agente, siempre que sea una dirección de email válida 
     * Se puede cambiar el nombre del agente, siempre que no esté en blanco
     * Se puede cambiar la localización del agente u omitirla
     * Se puede ir a la pantalla de vista de datos del agente
     * Se puede ir a la pantalla de inicio

Para más información acerca del módulo Agents y de cómo enviar peticiones a su API Rest para obtener los datos de un agente, consultar la siguiente guía, donde se explica todo más en detalle: https://github.com/Arquisoft/Agents_e5b/blob/master/README.md 
