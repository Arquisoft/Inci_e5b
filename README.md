[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2d1976960db9415892b85d741bb4a336)](https://www.codacy.com/app/jelabra/Inci_e5b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Inci_e5b&amp;utm_campaign=Badge_Grade)

La codecov de Inci_e5b no está bien, solo tiene en cuenta Agents.

## Inci_e5b:
[![Build Status](https://travis-ci.org/Arquisoft/Inci_e5b.svg?branch=master)](https://travis-ci.org/Arquisoft/Inci_e5b)
[![codecov](https://codecov.io/gh/Arquisoft/Inci_e5b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/Inci_e5b)

## Agents_e5b:
[![Build Status](https://travis-ci.org/Arquisoft/Agents_e5b.svg?branch=master)](https://travis-ci.org/Arquisoft/Agents_e5b)
[![codecov](https://codecov.io/gh/Arquisoft/Agents_e5b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/Agents_e5b)

## InciDashboard_e5b:
[![Build Status](https://travis-ci.org/Arquisoft/InciDashboard_e5b.svg?branch=master)](https://travis-ci.org/Arquisoft/InciDashboard_e5b)
[![codecov](https://codecov.io/gh/Arquisoft/InciDashboard_e5b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciDashboard_e5b)

# Inci_e5b
## Autores
- Carlos Sanabria Miranda (@CarlosSanabriaM)
- Manuel Fernández Antuña (@uo2999)
- Adrián Pérez Carou (@adrycarou896)
- Mª Rosa Valdés Pire (@RosaValdesPire)

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
4. Arrancar IndiDashboard
   * Ejecutar el siguiente comando, estando situado en la carpeta InciDashboard_e5b: ``mvn spring-boot:run``
5. Arrancar Agents
   * Ejecutar el siguiente comando, estando situado en la carpeta Agents_e5b: ``mvn spring-boot:run``

## Interfaz HTML
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
  
