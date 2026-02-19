.. meta::
    :author: Michael Eichberg
    :keywords: "Web-Engineering", "Verteilte Systeme"
    :description lang=de: Entwicklung verteilter Systeme
    :id: lecture-w3wi_110-entwicklung_verteilter_systeme
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



W3WI_110 - Entwicklung verteilter Systeme
================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 24SEA - Februar 2026



Ausgewählte Inhalte gem. MHB - Web-Programmierung
--------------------------------------------------------

.. rubric:: Kerninhalte

- HTML, CSS, JavaScript als clientseitige Web-Technologien und aktuelle APIs (z.B. HTML5 und verwandte Technologien)
- Übertragungsprotokolle und APIs zwischen Client und Server (z.B. HTTP, HTTPS, WebSockets, XMLHttpRequest, Fetch API)
- Kommunikation zwischen einzelnen Komponenten Web-basierter Anwendungen
- Optimierung von Webseiten für verschiedene Zielsysteme

.. rubric:: Zusatzinhalte

- Vertiefung von Frameworks
- Dynamische serverseitige Erzeugung von Webseiten



Ausgewählte Inhalte gem. MHB - Verteilte Systeme
-------------------------------------------------------

- Terminologie, Konzepte, Architekturen, Anforderungsprofile und Architekturmodelle für verteilte Systeme
- Entwurfs- und Implementierungsansätze
- Vergleich unterschiedlicher Middleware-Konzepte
- Synchrone und asynchrone Kommunikation, entfernter Methodenaufruf
- Asynchrone Kommunikation und Messaging-Systeme
- Sicherheitsaspekte in verteilten Systemen



Prüfungsleistung - Portfolio
------------------------------------------

- das Modul hat 55 VL:
  
  - Verteilte Systeme hat 22VL
  - Web-Programmierung hat 33 VL

.. compound::
  :class: incremental

  - Mehrere Bestandteile:

    - Projekt
    - Code Reviews
    - Vorträge



Vorträge - Themen
------------------------------------------

.. deck:: font-size-90

  .. card::

    .. important::

        Studierende, die Präsentationen innerhalb desselben Blocks halten, müssen sich untereinander abstimmen, um Überschneidungen zu vermeiden und auch sicherstellen, dass der/die vorherige Person die Themen, die für die Präsentation vorausgesetzt werden, auch ausreichend behandelt werden.
    
    .. important:: 
        :class: incremental

        Wenn Sie bestimmtes Wissen voraussetzen, das von einer anderen Studentin oder einem anderen Studenten behandelt werden soll(te), Sie sich aber nicht sicher sind, ob es ausreichend präsentiert wurde, erstellen Sie ggf. eine Backup-Folie, die dieses Thema ebenfalls abdeckt. Kennzeichnen Sie die Folie explizit als Backup-Folie mit einem Hinweis in welchem inhaltlichen Block das Thema hätte behandelt werden sollen. Diese Backup-Folie wird dann nicht auf das Zeitlimit angerechnet.

  .. card:: 

        .. rubric:: *Virtualization* und *Virtualization Platforms*

        .. class:: dhbw list-with-sublists show-list-item-content-on-hover 

        1. Introduction to Virtualization & Use Cases

           - Was ist Virtualization? Historischer Kontext und Motivation
           - Unterschiedliche Typen/Ebenen der Virtualization
           - Zentrale Use Cases: Server Consolidation, Cloud Computing, Development/Testing, Isolation
           - Vorteile und Trade-offs

        2. Hypervisors – Architecture & Types

           - Was ist ein Hypervisor?
           - Type 1 (bare-metal) vs. Type 2 (hosted) – architektonische Unterschiede
           - Full Virtualization vs. Paravirtualization
           - Beispiele und wann welcher Typ eingesetzt wird

        3. Virtual Machines – Implementation & Management

           - VM-Struktur und Komponenten (virtuelle Hardware, Guest OS)
           - VM-Lifecycle: Erstellung, Running, Pause/Resume, Snapshots
           - VM-Migration (Konzepte der Live Migration)
           - Resource Allocation und Isolation

        4. Containers & OS-level Virtualization

           - Container-Konzept und Unterschiede zu VMs
           - Namespaces und cgroups (konzeptionell)
           - Container Images und Layering
           - Use Cases und Vergleich mit VMs

        5. Memory Virtualization

           - Das Address-Translation-Problem (guest virtual → guest physical → host physical)
           - Shadow Page Tables
           - Hardware-assisted Virtualization (EPT/NPT)
           - Memory-Management-Techniken (Overcommitment, Ballooning)

        6. Network & I/O Virtualization

           - Herausforderungen bei der Virtualisierung von Network- und I/O-Geräten
           - Emulated vs. Paravirtualized Devices
           - SR-IOV (Single Root I/O Virtualization) und Device Passthrough
           - Virtuelle NICs und Network Bridges
           - Virtuelle Switches und Network Isolation

  .. card:: 

        .. rubric:: Network Protocols

        .. class:: dhbw 

        7. QUIC :peripheral:`(nur verfügbar, wenn wir ≥ 21 Studierende haben)`
        8. HTTP/3
        9. BitTorrent Protocol :peripheral:`(nur verfügbar, wenn wir ≥ 25 Studierende haben)`

  .. card:: 

        .. rubric:: Modern RPC

        .. class:: dhbw 

        10.  Protobuf
        11.  Google RPC

  .. card:: 

        .. rubric:: Web-App Security

        .. class:: dhbw 

        12.  SOP (Same-Origin Policy), CORS (Cross-Origin Resource Sharing) (Grundlagen)
        13.  CORP / COOP / COEP (Cross-Origin Resource/Opener/Embedder Policies) :peripheral:`(nur verfügbar, wenn wir ≥ 23 Studierende haben)`
        14.  CSP (Content Security Policy) und SRI (Subresource Integrity)

        Einführung und konkrete Beispiele, wie diese Mechanismen eingesetzt/spezifiziert werden und dabei helfen, Angriffe zu verhindern.

  .. card:: 

        .. rubric:: Monitoring & Debugging Distributed Systems

        .. class:: dhbw

        15.  Log Aggregation mit besonderem Fokus auf der Korrelation von Log-Einträgen

  .. card:: 

        .. rubric:: Leader Election

        .. class:: dhbw

        16.  Bully Algorithm und/oder Ring Algorithm

  .. card::

        .. rubric:: Quorum Systems
        
        .. class:: dhbw

        17.  Majority Voting (d. h. quorum-basiertes verteiltes Rechnen)        

  .. card::

        .. rubric:: Consensus Algorithms and Fault Tolerance

        .. class:: dhbw list-with-sublists show-list-item-content-on-hover

        18. Consensus Fundamentals & Problem Definition

            - Was ist Consensus und warum ist er in verteilten Systemen schwierig?
            - Das FLP-Impossibility-Result (konzeptionelles Verständnis)
            - Fehlermodelle: Crash Faults vs. Byzantine Faults
            - Safety- vs. Liveness-Eigenschaften
            - Motivation aus der Praxis: Replicated State Machines, Distributed Databases

        19. (Practical) Byzantine Fault Tolerance

            - Wann benötigen wir BFT?
            - Moderne Entwicklungen
          
              .. presenter-note::
                
                Tendermint, HotStuff

            - Einsatz in realen Systemen

        20. Paxos Family

            - Grundlegender Paxos-Algorithmus (konzeptioneller Überblick, Rollen: Proposers, Acceptors, Learners)
            - Warum Paxos korrekt, aber komplex ist
            - Multi-Paxos für praktische Systeme
            - Einsatz in realen Systemen
          
              .. presenter-note::
          
                [VERIFY!:] Google Chubby, Apache ZooKeeper Foundations

        21. Raft – Understandable Consensus

            - Motivation
            - Leader Election, Log Replication, Safety
            - Unterschiede zwischen Raft und Paxos (Design-Philosophie)
            - Einsatz in realen Systemen
          
              .. presenter-note::
                
                etcd, Consul, CockroachDB
  
  .. card:: 

        .. rubric:: Eventual Consistency
    
        .. class:: dhbw 

        22.  Eventual Consistency und Gossip Protocol
        23.  CRDTs (Conflict-free Replicated Data Types) :peripheral:`(nur verfügbar, wenn wir ≥ 22 Studierende haben)`

  .. card:: 

        .. rubric:: Distributed File Systems

        .. class:: dhbw

        24.  Ceph
        25.  HDFS :peripheral:`(nur verfügbar, wenn wir ≥ 24 Studierende haben)`




  .. card::

    .. hint::

        Students giving presentations belonging to the same block have to coordinate with each other to avoid any overlap. If you need a specific topic to be covered by another student but are not sure whether it will be presented sufficiently, create a backup slide for your presentation that covers this topic as well and mark it as a backup slide. This backup slide will not be counted towards the time limit.

  .. card:: 

        .. rubric:: Virtualization and Virtualization Platforms

        .. class:: dhbw list-with-sublists show-list-item-content-on-hover font-size-90

        1. Introduction to Virtualization & Use Cases

           - What is virtualization? Historical context and motivation
           - Different types/levels of virtualization
           - Key use cases: server consolidation, cloud computing, development/testing, isolation
           - Benefits and trade-offs

        2. Hypervisors - Architecture & Types

           - What is a hypervisor?
           - Type 1 (bare-metal) vs Type 2 (hosted) - architectural differences
           - Full virtualization vs paravirtualization approaches
           - Examples and when to use each type

        3. Virtual Machines - Implementation & Management

           - VM structure and components (virtual hardware, guest OS)
           - VM lifecycle: creation, running, pause/resume, snapshots
           - VM migration (live migration concepts)
           - Resource allocation and isolation

        4. Containers & OS-level Virtualization

           - Container concept and how it differs from VMs
           - Namespaces and cgroups (conceptual)
           - Container images and layering
           - Use cases and comparison with VMs

        5. Memory Virtualization

           - The address translation problem (guest virtual → guest physical → host physical)
           - Shadow page tables approach
           - Hardware-assisted virtualization (EPT/NPT)
           - Memory management techniques (overcommitment, ballooning)

        6. Network & I/O Virtualization

           - Challenges of virtualizing network and I/O devices
           - Emulated vs paravirtualized devices
           - SR-IOV (Single Root I/O Virtualization) and device passthrough
           - Virtual NICs and network bridges
           - Virtual switches and network isolation

  .. card:: 

        .. rubric:: Network Protocols

        .. class:: dhbw font-size-90

        1. QUIC :peripheral:`(only available when we have ≥ 21 students)`
        2. HTTP/3
        3. BitTorrent Protocol :peripheral:`(only available when we have ≥ 25 students)`

  .. card:: 

        .. rubric:: Modern RPC

        .. class:: dhbw font-size-90

        1.  Protobuf
        2.  Google RPC

  .. card:: 

        .. rubric:: Web-App Security

        .. class:: dhbw font-size-90

        1.  SOP (Same-Origin Policy), CORS (Cross-Origin Resource Sharing) (Foundations)
        2.  CORP / COOP / COEP (Cross-Origin Resource/Opener/Embedder Policies) :peripheral:`(only available when we have ≥ 23 students)`
        3.  CSP (Content Security Policy) and SRI (Subresource Integrity)

        Introduction and concrete examples how they are used/specified and help prevent attacks.

  .. card:: 

        .. rubric:: Monitoring & Debugging Distributed Systems

        .. class:: dhbw font-size-90

        1.  Log Aggregation with a particular focus on correlation of log entries

  .. card:: 

        .. rubric:: Leader Election

        .. class:: dhbw font-size-90

        1.  Bully Algorithm and/or Ring Algorithm

  .. card::

        .. rubric:: Quorum Systems
        
        .. class:: dhbw font-size-90       

        1.  Majority voting (i. e., quorum-distributed computing)        

  .. card::

        .. rubric:: Consensus Algorithms and Fault Tolerance

        .. class:: dhbw list-with-sublists show-list-item-content-on-hover font-size-90       

        1.  Consensus Fundamentals & Problem Definition

            - What is consensus and why is it hard in distributed systems?
            - The FLP impossibility result (conceptual understanding)
            - Fault models: crash faults vs Byzantine faults
            - Safety vs liveness properties
            - Real-world motivation: replicated state machines, distributed databases

        2.  (Practical) Byzantine Fault Tolerance

            - When do we need BFT?
            - Modern developments
          
              .. presenter-note::
                
                Tendermint, HotStuff

            - Real-world usage

        3.  Paxos Family

            - Basic Paxos algorithm (conceptual overview, roles: proposers, acceptors, learners)
            - Why Paxos is correct but complex
            - Multi-Paxos for practical systems
            - Real-world usage
          
              .. presenter-note::
          
                [VERIFY!:] Google Chubby, Apache ZooKeeper foundations

        4.  Raft - Understandable Consensus

            - Motivation
            - Leader election, log replication, safety
            - How Raft differs from Paxos (design philosophy)
            - Real-world usage
          
              .. presenter-note::
                
                etcd, Consul, CockroachDB
  
  .. card:: 

        .. rubric:: Eventual Consistency
    
        .. class:: dhbw font-size-90       

        1.  Eventual Consistency and Gossip Protocol
        2.  CRDTs (Conflict-free Replicated Data Types) :peripheral:`(only available when we have ≥ 22 students)`

  .. card:: 

        .. rubric:: Distributed File Systems

        .. class:: dhbw font-size-90       

        1.  Ceph
        2.  HDFS :peripheral:`(only available when we have ≥ 24 students)`



Gruppennote für Projekt bei Wunsch
-----------------------------------

.. container:: accentuate

    Das Projekt ist als Gruppenarbeit ausgelegt und alle Gruppenmitglieder können die gleiche Punktzahl erhalten, wenn dies gewünscht ist. 

    .. important:: 
      
      Es ist in jedem Fall zu Protokollieren, wer an welchen Teilen wie mitgewirkt hat. Dieses Protokoll muss am Ende eingereicht werden. Ohne dieses Protokoll erfolgt keine Bewertung!

      Das Protokoll wird jedoch nur zur Wertung herangezogen, wenn keine Gruppenbenotung gewünscht ist oder es Unstimmigkeiten gibt.



Projekt/Programmieraufgabe
------------------------------------------

Entwickeln Sie ein webbasiertes, responsives Familien-Dashboard, das als zentrale Informationsplattform für Familienmitglieder dient. Die Anwendung soll verschiedene konfigurierbare Widgets bereitstellen und durch ein Rollenkonzept unterschiedliche Zugriffsrechte ermöglichen - ggf. auf Widget-Level.



Anwendungsbeispiele
---------------------------------

.. deck::


  .. card::

    .. class:: incremental-list

    - Sie möchten - z. B. in der Küche - ein Dashboard auf einem Tablet anzeigen lassen, das den Familienkalender, die Schulpläne der Kinder, den Wochenplan für das Au Pair, das Wetter und eine To-Do-Liste anzeigt.
    - Sie möchten, dass alle Familienmitglieder gegenseitig lesenden Zugriff auf den/die Kalender der Familienmitglieder haben; die Kalender werden ggf. in externen Diensten (Google Calendar, Apple Calendar etc.) geführt.
    - Sie möchten den Schulplan der Kinder als Widget auf dem Dashboard anzeigen lassen
    - Ein Au-Pair soll lesenden Zugriff auf ihren/seinen Wochenplan haben, aber keine administrativen Rechte besitzen.
    - Die To-Do-Liste kann von allen Familienmitgliedern bearbeitet werden.
    - ...

  .. card::

        .. raw:: html

            <style>
                #example-dashboard {
                    overflow: hidden;    
                    box-shadow: var(--trbl-shadow);
                    width: fit-content;
                    border-radius: var(--small-border-radius);
                    margin: var(--default-box-margin);
                    iframe {
                        border: none;
                        
                        
                    }
                }
            </style>
            <div id="example-dashboard">
                <iframe src="dashboard-prototype.html" width="600px" height="800px" frameborder="0"></iframe>
            </div>

        .. container:: caption

          Beispiel eines Familien-Dashboards erzeugt mit Claude Code.



Anforderungen
---------------------------------

.. scrollable::

  .. rubric:: Funktionale Anforderungen

  **Widget-System**

  - Implementierung eines modularen Widget-Systems mit mindestens 3 der folgenden Widgets:

    - Stundenplan (für Kinder und Au-Pairs) (MUSS)
    - Gemeinsamer Terminkalender (ggf. als Integration externer Kalenderdienste)
    - Wetteranzeige (mit Standortauswahl)
    - To-Do-Liste
    - Notizen/Pinnwand

  - Widgets sollen hinzugefügt, entfernt und auf dem Dashboard positioniert werden können
  - Jedes Widget muss individuell konfigurierbar sein (z.B. Datenquelle, Darstellungsoptionen)

  **Rollenkonzept**

  :Familien-Administrator-Rolle: Volle Konfigurations- und Verwaltungsrechte

    - Widgets hinzufügen/entfernen/konfigurieren
    - Benutzer verwalten und Rollen zuweisen

      (D. h. Registrierung und Authentifizierung von Nutzern durchführen und Nutzer zu Familiengruppen zuweisen.)
    - Widget-Berechtigungen festlegen

  :Nutzer-Rolle: Eingeschränkte Rechte

    - Dashboard ansehen und sein persönliches Layout anpassen
    - Zugriff nur auf freigegebene Widgets
    - Interaktion mit Widget-Inhalten (z.B. Termine einsehen, nicht aber Dashboard umgestalten)

  :System-Administrator-Rolle: Verwaltung der Anwendung als solches

    - Benutzer- und Familiengruppenverwaltung
    - Systemweite Einstellungen und Wartung

  .. rubric:: Nicht-funktionale Anforderungen

  - Responsive Design

    - Optimierte Darstellung für Desktop, Tablet und Smartphone
    - Touch-optimierte Bedienung für mobile Endgeräte

  - Verteilte Architektur

    - Klare Trennung von Frontend und Backend
    - RESTful API für die Kommunikation
    - Zustandsverwaltung (State Management) im Frontend
    - Datenpersistenz im Backend

  - Erweiterbarkeit

    - Modulare Architektur ermöglicht einfaches Hinzufügen neuer Widgets
    - Klar definierte Schnittstellen zwischen Komponenten
    - Plugin-Architektur für Widget-Entwicklung wäre wünschenswert

  - Technische Anforderungen

    Entwickeln Sie eine verteilte Anwendung mit folgender Architektur:

    - Frontend als Single Page Application (SPA) ggf. unter Einsatz von responsive UI-Frameworks (z.B. Bootstrap, Tailwind CSS)
    - Backend mit RESTful API; der Technologiestack ist frei wählbar (z.B. Node.js/Express, Python/Flask, Java/Spring Boot)
    - Datenbank zur Persistierung (z.B. PostgreSQL, MongoDB, MySQL)

  - Architektur-Dokumentation

    - Erstellen Sie Architekturdiagramme (z.B. mit C4-Modell)
    - Dokumentieren Sie Design-Entscheidungen
    - Begründen Sie die Wahl von Technologien und Architekturmustern

  - Datenintegration

    - Integration mindestens einer externen API (z.B. Wetter-API)
    - Synchronisation von Kalenderdaten
    - Optional: Import/Export von Daten

  .. remark:: 
    
    .. rubric:: Einsatz von KI-Tools

    Nutzung von KI-Assistenten (z.B. GitHub Copilot, ChatGPT, Claude Code oder OpenCode) zur Code-Generierung ist erlaubt. 

    KI kann zur Unterstützung der folgenden Aufgaben eingesetzt werden:

    - Boilerplate-Code generieren
    - Code-Optimierung und Refactoring
    - Debugging und Fehleranalyse
    - Erstellung von Tests
    - Dokumentation

    *Pflichten bei KI-Nutzung*

    - Jedes Teammitglied muss jeden Teil der Anwendung erklären können
    - Die Architektur und Design-Entscheidungen müssen vom Team begründet werden
    - Der Technologiestack muss vom Team begründet werden
    - Im Projektbericht: Dokumentation, wo und wie, welche KI eingesetzt wurde
    - Reflexion über Vor- und Nachteile des KI-Einsatzes im Projekt



Bewertungskriterien für das Projekt
------------------------------------------------

.. csv-table::
    :width: 100%
    :widths: 80 20
    :stub-columns: 1
    :class: table-data-align-right table-header-align-left
    :header: Kategorie, max. 90 Punkte

    Code Reviews, max. 10
    Abschlusspräsentation, max. 05
    Vorführung, max. 20
    Funktionsumfang, max. 15
    Dokumentation (Entwickler und Benutzer), max. 9
    Technische Qualität des Videos, max. 1    
    "Qualität des Codes und der Tests (HTML, CSS und JavaScript)\ [#]_", max. 25
    Qualität des Buildprozesses\ [#]_ , max. 05

Es ist ein Dokument einzureichen aus dem hervorgeht:

1. welche KI Tools wofür eingesetzt wurden. (*Fehlanzeige erforderlich!*)
2. wer an welchem Teil mitgewirkt hat. (*Ohne dieses Dokument erfolgt keine Bewertung.*)

.. [#] Es ist neben dem Code auch ein kurzes Video 10 bis max. 15 Minuten einzureichen, dass in die Struktur und die Codebasis einführt. Dieses Video geht in die Benotung ein! Bitte nur im Notfall über Moodle bereitstellen.
.. [#] Werden Tests ausgeführt und wird am Ende ein Container gebaut?



Ablauf - W3WI-110 - Entwicklung verteilter Systeme 23SEB
---------------------------------------------------------

.. story::

    .. rubric:: 1. Block

    .. class:: incremental-list list-with-sublists

    - 20. Feb 2026 at 13:00 to 17:00
    - 27. Feb 2026 at 13:00 to 17:00
    - 6. Mar 2026 at 13:00 to 17:00
    - 13. Mar 2026 at 13:00 to 17:00
    - 20. Mar 2026 at 13:00 to 17:00
    - 27. Mar 2026 at 13:00 to 17:00
   

    .. class:: incremental 

    .. rubric:: 2. Block  

    .. class:: incremental-list list-with-sublists

    - 10. Apr 2026 at 13:00 to 17:00
    - 14. Apr 2026 at 13:00 to 17:00
    - 17. Apr 2026 at 13:00 to 17:00
    - 20. Apr 2026 at 13:30 to 17:30
    - 24. Apr 2026 at 13:00 to 17:00




      - **Grundlagen der Virtualisierung** (Terminologie: z.B. Bare Metal Virtualisierung, Hypervisor Level..; Sicherheitsmodelle ggf. von CPU an.)- 2 Stud.
      - **Virtualization Platforms** (Proxmox und Openstack) - 2 Stud.
      - **Container Technologies** (Docker, Firecracker, Linux Containers (LXC)) - 2 Stud.
      - **Container Orchestrators** (Kubernetes, Docker Swarm) - 2 Stud.

    - Scheduled: 24. Jun 2025 at 13:15 to 17:30, CEST

      .. class:: list-with-explanations

      - **Web- and Distributed Application Testing** (Diskussion und Präsentation von Werkzeugen für das Frontend- und Backend Testing) - 4 Stud.

        (Jeder Studierende soll sich sein eigenes Thema suchen! D. h. es werden danch im Prinzip vier Einzelvorträge gehalten.)
      - **gRPC** `🔗 <https://grpc.io>`__ und **gRPC-web** `🔗 <https://github.com/grpc/grpc-web>`__
        - 2 Stud.
      - **Apache Thrift** `🔗 <https://thrift.apache.org/>`__ - 2 Stud.

    - Scheduled: 30. Jun 2025 at 13:15 to 17:30, CEST

      - **Grundlagen von outdoor/indoor Positionierungssystemen** (GPS) - 1 Stud.
      - **Distributed Hash Tables** - 1 Stud.
      - **Paxos** `🔗 <https://en.wikipedia.org/wiki/Paxos_(computer_science)>`__
        - 2 Stud.
      - **Raft Consensus Algorithm** `🔗 <https://raft.github.io>`__
        - 2 Stud.
      - **Gossip Protokoll** `🔗 <https://highscalability.com/gossip-protocol-explained/>`__
        - 2 Stud.

    - Scheduled: 4. Jul 2025 at 13:15 to 17:30, CEST

      Gegenseitige Code Reviews und Präsentation der Ergebnisse der Code-Reviews.

      (Jedes Team führt ein Review durch (45 Minuten) und wird auch einmal reviewed. Danach erstellen alle Teams einen Bericht über das Projekt, dass sie reviewed haben. Dafür stehen ca. 45 Minuten zur Verfügung. Die Berichte werden danach präsentiert (ca. 10 Minuten). Die Präsentation und die Berichte werden als Teil der Gruppenleistung bewertet.)

    - Scheduled: 7. Jul 2025 at 13:15 to 17:30, CEST

      Gruppenindividuelle Betreuung bei Fragen und Problemen bzgl. des Projekts.

    - Scheduled: 11. Jul 2025 at 13:15 to 17:30, CEST

      Abschlusspräsentationen (Vorstellung des Tools und Vorstellung wie die Komponenten genutzt werden kann - d. h. Code zeige) und Vorführung der Projekte.

      .. TODO Anforderungen präzisieren!

      Die Projektabgabe ist am 9.7.2025 um 23:59 Uhr.



Code Reviews - 4.7.2025
----------------------------

1. Durchführung eines Code Reviews (Frontend, Backend, Buildscripte, Projektstruktur, Dokumentation,...) eines anderen Projektes (2 * 45 Minuten)

   Die Code Reviews erfolgen in zwei Runden, damit jede Gruppe ein Review bekommmt; die Gruppen sollten sich aufteilen, damit alle Teile reviewt werden!


   .. presenter-note::

        Zuteilung in der ersten Runde:

        .. csv-table::
            :header: "Gruppe", "Review von"

            1, 5
            2, 6
            3, 4

        Zuteilung in der zweiten Runde:

        .. csv-table::
            :header: "Gruppe", "Review von"

            4, 1
            5, 2
            6, 3

3. Erstellung eines Reports, der konstruktive Vorschläge enthält (30-45 Minuten).

4. Präsentation der Reports

.. supplemental::

    - Gruppe 1:	Planning Poker
        Nico Wrede,
        Max Meinel,
        Okan Sönmez,
        Johannes Kling
    - Gruppe 2:	CoCreate
        Ramona Korten,
        Monika Pjano,
        Paulina Klaus,
        Lisa Molter
    - Gruppe 3:	Chat App
        Jonas Stammer,
        Felix Erhard,
        Luca Bäck,
        Raphael Plett
    - Gruppe 4:	Chat App
        Iven Stahl,
        Christian Zweigert,
        Ibrahim Tikce,
        Nils Teschke
    - Gruppe 5:	Tool für Umfragen
        Jonathan Wieder,
        Mika Jun,
        Leon Priemer,
        Sergio Meli
    - Gruppe 6:	Planning Poker
        Tom Weber,
        Tarnbir Singh,
        Jan Müller,
        Dilmand Sado
