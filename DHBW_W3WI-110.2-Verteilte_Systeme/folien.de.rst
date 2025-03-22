.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "Verteilte Systeme"
    :description lang=de: Verteilte Systeme
    :id: lecture-w3wi_110.2-verteilte_systeme_wirtschaftsinformatik
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. |at| unicode:: 0x40

.. role:: incremental   
.. role:: eng
.. role:: ger
.. role:: red
.. role:: green
.. role:: obsolete
.. role:: peripheral  

.. role:: raw-html(raw)
   :format: html



W3WI_110.2 - Verteilte Systeme
================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0 (23EG/EH)



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

  .. class:: dhbw 

  1. Vorträge - max. 15 Punkte - 20 Minuten pro Person
  2. Programmieraufgabe - max. 35 Punkte

.. attention::

  Personen, die gemeinsam einen Vortrag halten, sollten bei der Programmieraufgabe nicht im selben Team sein.



Vorträge - Rahmenbedingungen
------------------------------------------

.. class:: list-with-explanations

- Die Präsentationen sollen sich insbesondere mit den Kerninhalten der Vorlesung beschäftigen und insbesondere konzeptioneller Natur sein.  

  D. h. nach der Darstellung des Anwendungszweckes gilt es die Architektur darzustellen, wie mit Fehlern umgegangen wird, welche Services angeboten werden, welche Garantien/Sicherheitsaspekte umgesetzt werden, wie wird die Skalierbarkeit erreicht, etc. 
  
  Keine Werbevorträge!
- Die Präsentationen sind am Abend vor dem ausgemachten Termin hochzuladen in Moodle.
- Die Vortragenden sollten während des Vortrags nicht mehrfach rotieren. 

  D. h. gefordert ist, dass erst der erste Vortragende seinen Teil vollständig vorträgt, dann der zweite, usw.. Dies ist für die Notenfindung erforderlich.


Vorträge - Themen
------------------------------------------

.. presenter-note:: 

  *Backend subsetting* und *connection churn* befasst sich mit den Herausforderungen, die sich ergeben, wenn ein Backend-Dienst in mehrere Dienste aufgeteilt wird, und mit der Frage, wie der Verbindungsabbruch verwaltet werden kann.

.. story:: 
    
  .. rubric:: Themen, die zu besetzen sind

  .. list-table:: 
    :width: 100%
    :widths: 70 30
    :class: booktabs incremental-table-rows
    :header-rows: 1
    
    * - Thema
      - Anzahl Stud.
    * - **Byzantine faults** `🔗 <https://en.wikipedia.org/wiki/Byzantine_fault>`__ 
      - 1 Stud.
    * - **LDAP** `🔗 <https://www.rfc-editor.org/rfc/rfc4511.txt>`__ 
      - 2 - 3 Stud.
    * - **Backend Subsetting and connection churn** `🔗 <https://queue.acm.org/detail.cfm?id=3570937>`__ 
      - 1 Stud.
    * - **Virtualization**: Ziel dieser Präsentation ist es, einen Überblick über die verschiedenen Virtualisierungstechnologien zu geben und die Gemeinsamkeiten und Unterschiede zwischen ihnen zu erläutern. Es ist auch möglich, einige der Unterschiede anhand konkreter Produkte zu demonstrieren. 

        :peripheral:`ausgewählte Schlüsselworte: Containers (e. g., Docker, Linux), Firecracker, Hypervisors (KVM, Xen, Hyper-V)`
      - 3 - 5 Stud.
    * - **Real time system monitoring with eBPF** `🔗 <https://queue.acm.org/detail.cfm?id=3178371>`__
      - 2 Stud. (mit Interesse an Linux)
    * - **HTTP/3 and QUIC or HTTP over QUIC** `🔗 <https://en.wikipedia.org/wiki/HTTP/3>`_
      - 2 Stud.

  .. class:: incremental

  .. rubric:: Themen, die vorgetragen werden können

  .. list-table:: 
    :width: 100%
    :widths: 70 30
    :class: booktabs incremental-table-rows
    :header-rows: 0

    * - **Paxos** `🔗 <https://en.wikipedia.org/wiki/Paxos_(computer_science)>`__
      - 2 Stud.
    * - **Raft Consensus Algorithm** `🔗 <https://raft.github.io>`__ 
      - 2 Stud.
    * - **Gossip Protokoll** `🔗 <https://highscalability.com/gossip-protocol-explained/>`__
      - 2 Stud.
    * - **gRPC** `🔗 <https://grpc.io>`__
      - 2 Stud.
    * - **Web and Distributed Application Testing**

        Client und serverseitiges Testen sollen diskutiert werden.
      - 4 Stud. 
    * - **Neo4J** `🔗 <https://neo4j.com>`__
      - 2 Stud.

.. supplemental::

  :eng:`connection churn` ≘ :ger:`Verbindungsabbruch`


.. Nicht mehr vergeben:
   `Zeebe <https://github.com/camunda/zeebe>`__
  OFFEN:
.. In Reserve:  
  - `AMQP <https://en.wikipedia.org/wiki/Advanced_Message_Queuing_Protocol>`_
  - `GraphQL <https://graphql.org>`_
  - `Django <https://www.djangoproject.com>`_



Vorträge - Datum
------------------------------------------

Pro Termin werden Vorträge von insgesamt 100 Minuten Länge gehalten.

.. rubric:: VORLÄUFIGE PLANUNG

.. list-table:: 
  :width: 100%
  :widths: 20 80 
  :header-rows: 1

  * - Date
    - Topic
  * - \ 9. April
    - \ 1. (Byzantine faults), 4. (Virtualization), 2. (LDAP), 5. (Monitoring)
  * - \ 16. April 
    - \ 7. (Paxos), 11. (Testing), 12. (Neo4j), 13. (Docker Swarm vs. Kubernetes) 
  * - \ 23. April 
    - \ 8. (Raft), 9. (Gossip), 3. (Connection Churn), 6. (HTTP/3), 10. (gRPC)

