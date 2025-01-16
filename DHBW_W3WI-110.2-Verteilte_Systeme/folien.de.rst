.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "Verteilte Systeme"
    :description lang=de: Verteilte Systeme
    :id: lecture-w3wi_110.2-verteilte_systeme_wirtschaftsinformatik
    :first-slide: last-viewed

.. |at| unicode:: 0x40

.. role:: incremental   
.. role:: eng
.. role:: ger
.. role:: red
.. role:: green
.. role:: the-blue
.. role:: minor
.. role:: obsolete
.. role:: line-above
.. role:: huge
.. role:: xxl

.. role:: raw-html(raw)
   :format: html



W3WI_110.2 - Verteilte Systeme
================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0



Kerninhalte gem. MHB
---------------------------

- Terminologie, Konzepte, Architekturen, Anforderungsprofile und Architekturmodelle für verteilte Systeme
- Entwurfs- und Implementierungsansätze
- Vergleich unterschiedlicher Middleware-Konzepte
- Synchrone und asynchrone Kommunikation, entfernter Methodenaufruf 
- Asynchrone Kommunikation und Messaging-Systeme
- Sicherheitsaspekte in verteilten Systemen


Prüfungsleistung - Portfolio
------------------------------------------

.. admonition:: Hintergrund

    - das Modul hat 55 VL
    - Verteilte Systeme hat 22VL
    - Die verteilte Systeme geht mit **50** von 120 Modulpunkten ein

- 2 Bestandteile:

  1. Vorträge - max. 15 Punkte
  2. Programmieraufgabe - max. 35 Punkte


Vorträge - Rahmenbedingungen
------------------------------------------

.. class:: list-with-explanations

- Die Präsentationen sollen sich insbesondere mit den Kerninhalten der Vorlesung beschäftigen und insbesondere konzeptioneller Natur sein.  

  D. h. nach der Darstellung des Anwendungszweckes gilt es die Architektur darzustellen, wie mit Fehlern umgegangen wird, welche Services angeboten werden, welche Garantien/Sicherheitsaspekte umgesetzt werden, wie wird die Skalierbarkeit erreicht, etc. 
  
  Keine Werbevorträge!
- Die Präsentationen sind am Abend vor dem ausgemachten Termin hochzuladen in Moodle.


Vorträge - Themen
------------------------------------------


.. rubric:: Middleware Lösungen

.. list-table::
  :width: 100%
  :widths: 50 50
  :class: borderless 
  :header-rows: 0

  * - Apache Kafka
    - Apache Zookeeper
  * - (Scala) Akka
    - Rabbit MQ
  * - (Twitter/X) Finagle
    - Apache Hive
  * - Apache Cassandra
    - Apache Spark
  * - `Apache Pulsar <https://pulsar.apache.org>`__ 
    - Hadoop HDFS
  * - Hadoop Yarn/MapReduce
    - Apache Mahout
  * - `Apache Camel <https://camel.apache.org>`__
    - `NATS <https://docs.nats.io>`__
  * - Spring Framework 
    - `Eclipse Glassfish und Jakarta EE <https://glassfish.org>`__
  * - `Tokio <https://tokio.rs>`__
    - `Google Spanner <https://dl.acm.org/doi/10.1145/3035918.3056103>`__

.. Nicht mehr vergeben:
   `Zeebe <https://github.com/camunda/zeebe>`__



.. Vorträge - Themen (inkl. Einstiegslinks)
  ------------------------------------------
  - `Pxos <https://en.wikipedia.org/wiki/Paxos_(computer_science)>`_
  - `Raft Consensus Algorithm <https://raft.github.io>`_ 
  - `Gossip Protokoll <https://highscalability.com/gossip-protocol-explained/>`_
  - `gRPC <https://grpc.io>`_
  - `AMQP <https://en.wikipedia.org/wiki/Advanced_Message_Queuing_Protocol>`_
  - `GraphQL <https://graphql.org>`_
  - `Django <https://www.djangoproject.com>`_
  - `HTTP/3 und QUIC bzw. HTTP over QUIC <https://en.wikipedia.org/wiki/HTTP/3>`_