.. meta::
    :author: Michael Eichberg
    :keywords: "Virtualisierung", "Container", "Docker", "Deployment"
    :description lang=de: Distributed Systems - Virtualisierung und Container
    :id: lecture-ds-containers
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!
    :svg-defs:
        <marker
            id="arrow"
            viewBox="0 0 10 10"
            refX="8"
            refY="5"
            markerWidth="6"
            markerHeight="6"
            orient="auto-start-reverse">
            <path d="M 0 0 L 10 5 L 0 10 z" fill="#111"/>
        </marker>
        <marker
            id="arrow-blue"
            viewBox="0 0 10 10"
            refX="8"
            refY="5"
            markerWidth="6"
            markerHeight="6"
            orient="auto-start-reverse">
            <path d="M 0 0 L 10 5 L 0 10 z" fill="#0066cc"/>
        </marker>
    :svg-style:
        svg {
            font-family: "Noto Sans Display";
        }
        .container-box { fill: #e6f3ff; stroke: #0066cc; stroke-width: 0.1; }
        .vm-box { fill: #fff2e6; stroke: #ff6600; stroke-width: 0.1; }
        .host-box { fill: #f0f0f0; stroke: #333; stroke-width: 0.1; }
        .app-box { fill: #e6ffe6; stroke: #00cc66; stroke-width: 0.1; }
        .text-label {  fill: #333; }
        .title-label { font-weight: bold; fill: #333; }

.. include:: ../docutils.defs



Verteilte Systeme - Virtualisierung und Container
====================================================

Eine Einführung in moderne Deployment-Technologien

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.0

.. supplemental::

    :Folien:
        [HTML] |html-source|

        [PDF] |pdf-source|
    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues




.. class:: new-section transition-fade

Einführung in Virtualisierung und Container
--------------------------------------------


Virtualisierung - allg. Definition
------------------------------------

.. epigraph::

    Virtualisierung bezeichnet in der Informatik die **Nachbildung eines Hard- oder Software-Objekts durch ein ähnliches Objekt vom selben Typ mit Hilfe einer Abstraktionsschicht**. 
    
    :incremental:`Dadurch lassen sich virtuelle (d. h. nicht-physische) Geräte oder Dienste wie emulierte Hardware, Betriebssysteme, Datenspeicher oder Netzwerkressourcen erzeugen.` 
    
    :incremental:`Dies erlaubt es etwa, Computer-Ressourcen (insbesondere im Server-Bereich) transparent zusammenzufassen oder aufzuteilen, oder ein Betriebssystem innerhalb eines anderen auszuführen. Dadurch können u. a. mehrere Betriebssysteme auf einem physischen Server oder „Host“ ausgeführt werden.`

    -- `Stand 5. Oktober 2025 - Wikipedia <https://de.wikipedia.org/wiki/Virtualisierung_(Informatik)>`__



Motivation - Warum Virtualisierung?
-----------------------------------

.. observation::

    Moderne Softwareentwicklung steht vor verschiedenen Herausforderungen:

    .. class:: incremental-list

    - **Heterogene Umgebungen**: Software muss auf verschiedenen Betriebssystemen und Hardware-Konfigurationen laufen.
    - **Skalierbarkeit**: (Server-)Anwendungen müssen flexibel skalieren können.
    - **Isolation**: Verschiedene (Server-)Anwendungen sollen sich nicht gegenseitig beeinträchtigen.
    - **Portabilität**: Code soll einfach zwischen verschiedenen Umgebungen übertragbar sein.
    - **Ressourceneffizienz**: Hardware soll optimal genutzt werden.

    .. container:: framed incremental

        Virtualisierung und Containerisierung bieten Lösungen für diese Herausforderungen.



Was ist Virtualisierung?
-------------------------

.. definition:: 

    **Virtualisierung**\ [#]_ ermöglicht es mehrere virtuelle Maschinen (VMs) oder Container auf einer physischen Hardware auszuführen.

.. deck:: incremental

    .. card::

        .. rubric:: Ziel der Virtualisierung

        .. class:: incremental-list

        - Abstraktion der Hardware
        - Isolation zwischen Anwendungen
        - Optimale Ressourcennutzung
        - Flexibilität bei der Bereitstellung

    .. card::

        .. rubric:: Arten der Virtualisierung

        .. class:: incremental-list

        - **Bare-Metal Virtualisierung**: Virtualisierung direkt auf der Hardware
        - **Hosted Virtualisierung**: Virtualisierung auf einem Host-Betriebssystem
        - **Containervirtualisierung**: Virtualisierung von Systemressourcen eines Betriebssystems, teilt sich jedoch den Kernel mit dem Host-Betriebssytem.
        - **Anwendungsvirtualisierung**: Ausführung einer Anwendung in einer exclusiven, teilweise oder vollständig isolierten virtuellen Umgebung.


.. [#] Im Folgenden konzentrieren wir uns auf Virtualisierung von serverseitigen Anwendungen und von Softwareentwicklung.



Traditionelle vs. Virtualisierte Architektur
----------------------------------------------

.. raw:: html
    :class: align-center

    <div style="width: 80ch; height: 25ch; container-type:size;">
    <svg viewBox="0 0 60 20" font-size="1" version="1.1" xmlns="http://www.w3.org/2000/svg">
        
        <!-- Traditional Architecture -->
        <g class="incremental">
            <text class="title-label" x="8" y="2">Traditionelle Architektur</text>
            
            <!-- Host OS -->
            <rect class="host-box" width="12" height="3" x="2" y="4" rx="1"/>
            <text class="text-label" x="5" y="5.8">Host OS</text>
            
            <!-- Apps -->
            <rect class="app-box" width="3" height="2" x="3" y="8" rx="0.5"/>
            <text class="text-label" x="3.5" y="9.3" >App 1</text>
            
            <rect class="app-box" width="3" height="2" x="7" y="8" rx="0.5"/>
            <text class="text-label" x="7.5" y="9.3" >App 2</text>
            
            <rect class="app-box" width="3" height="2" x="11" y="8" rx="0.5"/>
            <text class="text-label" x="11.5" y="9.3" >App 3</text>
        </g>
        
        <!-- Virtualized Architecture -->
        <g class="incremental">
            <text class="title-label" x="38" y="2">Virtualisierte Architektur</text>
            
            <!-- Hypervisor -->
            <rect class="host-box" width="12" height="3" x="32" y="4" rx="1"/>
            <text class="text-label" x="35" y="5.8">Hypervisor</text>
            
            <!-- VMs -->
            <rect class="vm-box" width="3.5" height="4" x="33" y="8" rx="0.5"/>
            <text class="text-label" x="33.5" y="9.5" >VM 1</text>
            <rect class="app-box" width="2" height="1.5" x="33.5" y="10.5" rx="0.3"/>
            <text class="text-label" x="33.8" y="11.2" >OS</text>
            
            <rect class="vm-box" width="3.5" height="4" x="37.5" y="8" rx="0.5"/>
            <text class="text-label" x="38" y="9.5" >VM 2</text>
            <rect class="app-box" width="2" height="1.5" x="38" y="10.5" rx="0.3"/>
            <text class="text-label" x="38.3" y="11.2" >OS</text>
            
            <rect class="vm-box" width="3.5" height="4" x="42" y="8" rx="0.5"/>
            <text class="text-label" x="42.5" y="9.5" >VM 3</text>
            <rect class="app-box" width="2" height="1.5" x="42.5" y="10.5" rx="0.3"/>
            <text class="text-label" x="42.8" y="11.2" >OS</text>
        </g>
        
        <!-- Hardware -->
        <rect class="host-box" width="56" height="2" x="2" y="16" rx="1"/>
        <text class="text-label" x="28" y="17.3">Physische Hardware</text>
        
        <!-- Arrows -->
        <g class="incremental">
            <line x1="30" y1="10" x2="32" y2="10" style="stroke: #666; stroke-width: 0.1" marker-end="url(#arrow)"/>
            <text class="text-label" x="30" y="9.5" >Virtualisierung</text>
        </g>
    </svg>
    </div>



Bare-Metal Virtualisierung
---------------------------

.. deck::

    .. card::

        Bei der **Bare-Metal Virtualisierung** (auch Type-1 Hypervisor) läuft die Virtualisierungsschicht direkt auf der Hardware, ohne ein Host-Betriebssystem.

        .. deck::

            .. card::

                .. rubric:: Vorteile

                .. class:: incremental-list

                - **Bessere Performance**: Kein Overhead durch Host-OS
                - **Höhere Sicherheit**: Weniger Angriffsfläche
                - **Direkter Hardware-Zugriff**: Optimale Ressourcennutzung
                - **Enterprise-fähig**: Für produktive Umgebungen geeignet

            .. card::

                .. rubric:: Nachteile

                .. class:: incremental-list

                - **Komplexität**: Schwerer zu installieren und zu verwalten
                - **Hardware-Abhängigkeit**: Spezielle Hardware-Unterstützung erforderlich
                - **Kosten**: Lizenzkosten für Hypervisor-Software

    .. card::

    
        **Beispiele für Bare-Metal Hypervisoren:**
        
        - VMware vSphere/ESXi
        - Microsoft Hyper-V (Server)
        - Citrix XenServer
        - Red Hat RHEV



Hosted Virtualisierung
-----------------------

.. deck::

    .. card::

        Bei der **Hosted Virtualisierung** (auch Type-2 Hypervisor) läuft die Virtualisierungsschicht als Anwendung auf einem Host-Betriebssystem.

        .. deck::

            .. card::

                .. rubric:: Vorteile

                .. class:: incremental-list

                - **Einfache Installation**: Wie normale Software
                - **Flexibilität**: Verschiedene Host-Betriebssysteme möglich
                - **Entwicklungsumgebung**: Ideal für Tests und Entwicklung
                - **Benutzerfreundlich**: Grafische Oberflächen verfügbar

            .. card::

                .. rubric:: Nachteile

                .. class:: incremental-list

                - **Performance-Overhead**: Host-OS verbraucht Ressourcen
                - **Sicherheitsrisiken**: Host-OS als zusätzliche Angriffsfläche
                - **Begrenzte Skalierbarkeit**: Weniger VMs pro Host möglich

    .. card::

        **Beispiele für Hosted Hypervisoren:**
        
        - VMware Workstation
        - Oracle VirtualBox
        - Parallels Desktop
        - Microsoft Hyper-V (Desktop)



.. class:: new-section transition-fade

Container-Technologien
-----------------------



Was sind Container?
--------------------

.. definition::

    **Container** sind leichtgewichtige, portable Einheiten, die Anwendungen mit allen notwendigen Abhängigkeiten (Code, Runtime, Bibliotheken) verpacken.

.. observation::

    Container teilen sich das Betriebssystem des Hosts und nutzen Kernel-Features wie:

    .. class:: incremental-list

    - **Namespaces**: Isolation von Prozessen, Netzwerk, Dateisystem
    - **Control Groups (cgroups)**: Ressourcenbeschränkung
    - **Union File Systems**: Effiziente Speicherung von Layern



Container vs. Virtual Machines
-------------------------------

.. raw:: html
    :class: align-center

    <div style="width: 80ch; height: 30ch; container-type:size;">
    <svg viewBox="0 0 60 24" font-size="1" version="1.1" xmlns="http://www.w3.org/2000/svg">
        
        <!-- Virtual Machine Architecture -->
        <g class="incremental">
            <text class="title-label" x="8" y="2">Virtual Machine</text>
            
            <!-- Apps -->
            <rect class="app-box" width="2.5" height="1.5" x="2" y="4" rx="0.3"/>
            <text class="text-label" x="2.2" y="4.8" style="font-size: 8">App</text>
            
            <rect class="app-box" width="2.5" height="1.5" x="5" y="4" rx="0.3"/>
            <text class="text-label" x="5.2" y="4.8" style="font-size: 8">App</text>
            
            <!-- Guest OS -->
            <rect class="vm-box" width="12" height="2" x="2" y="6" rx="0.5"/>
            <text class="text-label" x="6" y="7.3">Guest OS</text>
            
            <!-- Hypervisor -->
            <rect class="host-box" width="12" height="2" x="2" y="9" rx="0.5"/>
            <text class="text-label" x="6" y="10.3">Hypervisor</text>
            
            <!-- Host OS -->
            <rect class="host-box" width="12" height="2" x="2" y="12" rx="0.5"/>
            <text class="text-label" x="6" y="13.3">Host OS</text>
        </g>
        
        <!-- Container Architecture -->
        <g class="incremental">
            <text class="title-label" x="38" y="2">Container</text>
            
            <!-- Apps -->
            <rect class="app-box" width="2.5" height="1.5" x="32" y="4" rx="0.3"/>
            <text class="text-label" x="32.2" y="4.8" style="font-size: 8">App</text>
            
            <rect class="app-box" width="2.5" height="1.5" x="35" y="4" rx="0.3"/>
            <text class="text-label" x="35.2" y="4.8" style="font-size: 8">App</text>
            
            <rect class="app-box" width="2.5" height="1.5" x="38" y="4" rx="0.3"/>
            <text class="text-label" x="38.2" y="4.8" style="font-size: 8">App</text>
            
            <rect class="app-box" width="2.5" height="1.5" x="41" y="4" rx="0.3"/>
            <text class="text-label" x="41.2" y="4.8" style="font-size: 8">App</text>
            
            <!-- Container Runtime -->
            <rect class="container-box" width="12" height="3" x="32" y="6" rx="0.5"/>
            <text class="text-label" x="35" y="7.8">Container Runtime</text>
            <text class="text-label" x="35" y="8.5" >(Docker, containerd)</text>
            
            <!-- Host OS -->
            <rect class="host-box" width="12" height="2" x="32" y="10" rx="0.5"/>
            <text class="text-label" x="36" y="11.3">Host OS</text>
        </g>
        
        <!-- Hardware -->
        <rect class="host-box" width="56" height="2" x="2" y="18" rx="1"/>
        <text class="text-label" x="28" y="19.3">Physische Hardware</text>
        
        <!-- Resource Usage -->
        <g class="incremental">
            <text class="title-label" x="2" y="22">Ressourcennutzung</text>
            <text class="text-label" x="2" y="23.2" >VM: Vollständiges OS pro Instanz</text>
            <text class="text-label" x="32" y="23.2" >Container: Geteiltes Host-OS</text>
        </g>
    </svg>
    </div>



Vergleich: Container vs. VMs
-----------------------------

.. grid::

    .. cell::

        .. rubric:: Virtual Machines

        .. class:: incremental-list

        :Vorteile:
            - Vollständige Isolation
            - Verschiedene Betriebssysteme möglich
            - Bewährte Technologie
            - Hohe Sicherheit

        :Nachteile:
            - Hoher Ressourcenverbrauch
            - Langsame Startzeiten
            - Komplexe Verwaltung
            - Weniger portabel

    .. cell:: incremental

        .. rubric:: Container

        .. class:: incremental-list

        :Vorteile:
            - Schneller Start
            - Geringer Ressourcenverbrauch
            - Hohe Portabilität
            - Einfache Skalierung

        :Nachteile:
            - Weniger Isolation
            - Gleiches Betriebssystem erforderlich
            - Sicherheitsrisiken bei Root-Zugriff
            - Abhängigkeit vom Host-OS



Docker - Container-Infrastruktur
---------------------------------

**Docker** ist eine Open-Source-Plattform zur Entwicklung, Bereitstellung und Verwaltung von containerisierten Anwendungen.

.. deck::

    .. card::

        .. rubric:: Docker-Komponenten

        .. class:: incremental-list

        - **Docker Engine**: Runtime für Container
        - **Docker Images**: Vorlagen für Container
        - **Docker Containers**: Laufende Instanzen
        - **Docker Registry**: Repository für Images
        - **Docker Compose**: Orchestrierung mehrerer Container

    .. card::

        .. rubric:: Docker-Architektur

        .. class:: incremental-list

        - **Docker Daemon**: Hintergrundprozess
        - **Docker Client**: Kommandozeilen-Tool
        - **Docker Registry**: Speicher für Images
        - **Docker Hub**: Öffentliche Registry



Docker-Architektur
-------------------

.. raw:: html
    :class: align-center

    <div style="width: 70ch; height: 20ch; container-type:size;">
    <svg viewBox="0 0 50 16" font-size="1" version="1.1" xmlns="http://www.w3.org/2000/svg">
        
        <!-- Docker Client -->
        <g class="incremental">
            <rect class="app-box" width="8" height="2" x="2" y="2" rx="0.5"/>
            <text class="text-label" x="4.5" y="3.3">Docker Client</text>
            <text class="text-label" x="4" y="3.8" >(CLI, API)</text>
        </g>
        
        <!-- Docker Daemon -->
        <g class="incremental">
            <rect class="container-box" width="12" height="4" x="12" y="2" rx="0.5"/>
            <text class="text-label" x="15" y="3.5">Docker Daemon</text>
            <text class="text-label" x="14" y="4.2" >(Docker Engine)</text>
            <text class="text-label" x="15" y="4.8" >- Images</text>
            <text class="text-label" x="15" y="5.3" >- Containers</text>
        </g>
        
        <!-- Docker Registry -->
        <g class="incremental">
            <rect class="vm-box" width="8" height="2" x="26" y="2" rx="0.5"/>
            <text class="text-label" x="28.5" y="3.3">Docker Registry</text>
        </g>
        
        <!-- Host OS -->
        <rect class="host-box" width="46" height="2" x="2" y="8" rx="0.5"/>
        <text class="text-label" x="23" y="9.3">Host Operating System</text>
        
        <!-- Hardware -->
        <rect class="host-box" width="46" height="2" x="2" y="11" rx="0.5"/>
        <text class="text-label" x="23" y="12.3">Hardware</text>
        
        <!-- Containers -->
        <g class="incremental">
            <rect class="app-box" width="4" height="2" x="12" y="6" rx="0.3"/>
            <text class="text-label" x="13" y="7.2" >Container 1</text>
            
            <rect class="app-box" width="4" height="2" x="17" y="6" rx="0.3"/>
            <text class="text-label" x="18" y="7.2" >Container 2</text>
            
            <rect class="app-box" width="4" height="2" x="22" y="6" rx="0.3"/>
            <text class="text-label" x="23" y="7.2" >Container 3</text>
        </g>
        
        <!-- Arrows -->
        <g class="incremental">
            <line x1="10" y1="3" x2="12" y2="3" style="stroke: #666; stroke-width: 2" marker-end="url(#arrow-blue)"/>
            <line x1="24" y1="3" x2="26" y2="3" style="stroke: #666; stroke-width: 2" marker-end="url(#arrow-blue)"/>
            <line x1="26" y1="3" x2="24" y2="3" style="stroke: #666; stroke-width: 2" marker-end="url(#arrow-blue)"/>
        </g>
    </svg>
    </div>



Docker-Images und Container
----------------------------

Ein **Docker-Image** ist eine unveränderliche Vorlage, die alle notwendigen Komponenten für eine Anwendung enthält.

.. deck::

    .. card::

        .. rubric:: Image-Eigenschaften

        .. class:: incremental-list

        - **Layered Architecture**: Images bestehen aus mehreren Layern
        - **Immutable**: Images können nicht verändert werden
        - **Portable**: Funktionieren auf verschiedenen Systemen
        - **Versioned**: Verschiedene Versionen eines Images möglich

    .. card::

        .. rubric:: Container-Lifecycle

        .. class:: incremental-list

        - **Create**: Container aus Image erstellen
        - **Start**: Container starten
        - **Run**: Container ausführen
        - **Stop**: Container stoppen
        - **Remove**: Container löschen



Docker-Befehle
---------------

.. class:: incremental-list

:Image-Verwaltung:
    - :console:`docker pull <image>` - Image herunterladen
    - :console:`docker images` - Alle Images anzeigen
    - :console:`docker build -t <name> .` - Image erstellen
    - :console:`docker rmi <image>` - Image löschen

:Container-Verwaltung:
    - :console:`docker run <image>` - Container starten
    - :console:`docker ps` - Laufende Container anzeigen
    - :console:`docker stop <container>` - Container stoppen
    - :console:`docker rm <container>` - Container löschen

:Informationen:
    - :console:`docker logs <container>` - Container-Logs anzeigen
    - :console:`docker exec -it <container> /bin/bash` - In Container einloggen



.. class:: new-section transition-fade

Container Orchestrierung
-------------------------

Was ist Container Orchestrierung?
----------------------------------

**Container Orchestrierung** ist die Automatisierung der Bereitstellung, Verwaltung, Skalierung und Vernetzung von Container-Anwendungen.

.. observation::

    Bei der Orchestrierung geht es um:

    .. class:: incremental-list

    - **Deployment**: Automatische Bereitstellung von Containern
    - **Scaling**: Dynamische Skalierung basierend auf Last
    - **Load Balancing**: Verteilung der Anfragen
    - **Service Discovery**: Auffinden von Services
    - **Health Monitoring**: Überwachung der Container-Gesundheit
    - **Rolling Updates**: Updates ohne Downtime



Container Orchestrierungs-Tools
--------------------------------

.. grid::

    .. cell::

        .. rubric:: Kubernetes

        .. class:: incremental-list

        - **Open Source** von Google
        - **De-facto Standard** für Container Orchestrierung
        - **Umfangreiche Features**
        - **Große Community**
        - **Komplexe Einrichtung**

    .. cell:: incremental

        .. rubric:: Docker Swarm

        .. class:: incremental-list

        - **Native Docker-Lösung**
        - **Einfache Einrichtung**
        - **Weniger Features** als Kubernetes
        - **Gut für kleinere Projekte**
        - **Einfache Verwaltung**

    .. cell:: incremental

        .. rubric:: Apache Mesos

        .. class:: incremental-list

        - **Distributed Systems Kernel**
        - **Unterstützt verschiedene Frameworks**
        - **Hochskalierbar**
        - **Komplexe Architektur**
        - **Weniger verbreitet**



.. class:: exercises transition-fade

Übung: Web-Anwendung mit Docker deployen
------------------------------------------

.. exercise:: Docker-basiertes Deployment einer Web-Anwendung

    In dieser Übung werden Sie eine einfache Web-Anwendung mit Docker containerisieren und deployen.

    .. class:: incremental-list

    1. **Docker-Installation prüfen**
    2. **Einfache Web-Anwendung erstellen**
    3. **Dockerfile erstellen**
    4. **Docker-Image bauen**
    5. **Container starten und testen**
    6. **Docker Compose für Multi-Container-Setup**



Schritt 1: Docker-Installation prüfen
--------------------------------------

.. code:: bash
    :number-lines:

    # Docker-Version prüfen
    docker --version
    
    # Docker-Status prüfen
    docker info
    
    # Ersten Container testen
    docker run hello-world



Schritt 2: Web-Anwendung erstellen
-----------------------------------

.. code:: html

    <!DOCTYPE html>
    <html>
    <head>
        <title>Docker Demo App</title>
        <style>
            body { font-family: Arial, sans-serif; margin: 40px; }
            .container { max-width: 800px; margin: 0 auto; }
            .header { background: #0066cc; color: white; padding: 20px; }
            .content { padding: 20px; }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <h1>Docker Demo Application</h1>
            </div>
            <div class="content">
                <h2>Willkommen zur Docker-Demo!</h2>
                <p>Diese Anwendung läuft in einem Docker-Container.</p>
                <p>Server-Zeit: <span id="time"></span></p>
                <script>
                    document.getElementById('time').textContent = new Date().toLocaleString();
                </script>
            </div>
        </div>
    </body>
    </html>



Schritt 3: Dockerfile erstellen
--------------------------------

.. code:: dockerfile

    # Basis-Image verwenden
    FROM nginx:alpine
    
    # Arbeitsverzeichnis setzen
    WORKDIR /usr/share/nginx/html
    
    # HTML-Datei kopieren
    COPY index.html .
    
    # Port 80 freigeben
    EXPOSE 80
    
    # Nginx starten
    CMD ["nginx", "-g", "daemon off;"]



Schritt 4: Docker-Image bauen
------------------------------

.. code:: bash
    :number-lines:

    # Image bauen
    docker build -t webapp-demo .
    
    # Images anzeigen
    docker images
    
    # Image-Details anzeigen
    docker inspect webapp-demo



Schritt 5: Container starten und testen
----------------------------------------

.. code:: bash
    :number-lines:

    # Container starten
    docker run -d -p 8080:80 --name webapp-container webapp-demo
    
    # Container-Status prüfen
    docker ps
    
    # In Browser testen: http://localhost:8080
    
    # Container-Logs anzeigen
    docker logs webapp-container
    
    # Container stoppen
    docker stop webapp-container
    
    # Container löschen
    docker rm webapp-container



Schritt 6: Docker Compose für Multi-Container
----------------------------------------------

.. code:: yaml

    version: '3.8'
    
    services:
      web:
        build: .
        ports:
          - "8080:80"
        depends_on:
          - db
        environment:
          - DB_HOST=db
      
      db:
        image: postgres:13
        environment:
          - POSTGRES_DB=demo
          - POSTGRES_USER=demo
          - POSTGRES_PASSWORD=demo123
        volumes:
          - postgres_data:/var/lib/postgresql/data
    
    volumes:
      postgres_data:

.. code:: bash
    :number-lines:

    # Multi-Container-Setup starten
    docker-compose up -d
    
    # Status prüfen
    docker-compose ps
    
    # Logs anzeigen
    docker-compose logs
    
    # Setup stoppen
    docker-compose down



Erweiterte Übung: Database-Integration
---------------------------------------

.. story::

    .. exercise:: Web-App mit Datenbank

        Erweitern Sie die Web-Anwendung um eine Datenbank-Integration:

        .. class:: incremental-list

        1. **PostgreSQL-Container hinzufügen**
        2. **Backend-API erstellen** (Python/Node.js)
        3. **Datenbankverbindung konfigurieren**
        4. **Multi-Container-Setup mit Docker Compose**
        5. **Persistente Datenspeicherung**

Backend-API (Python/Flask)
---------------------------

.. code:: python

    from flask import Flask, jsonify, request
    import psycopg2
    import os
    
    app = Flask(__name__)
    
    # Datenbankverbindung
    def get_db_connection():
        conn = psycopg2.connect(
            host=os.environ.get('DB_HOST', 'localhost'),
            database=os.environ.get('POSTGRES_DB', 'demo'),
            user=os.environ.get('POSTGRES_USER', 'demo'),
            password=os.environ.get('POSTGRES_PASSWORD', 'demo123')
        )
        return conn
    
    @app.route('/api/data', methods=['GET'])
    def get_data():
        conn = get_db_connection()
        cur = conn.cursor()
        cur.execute('SELECT * FROM items;')
        items = cur.fetchall()
        cur.close()
        conn.close()
        return jsonify(items)
    
    @app.route('/api/data', methods=['POST'])
    def add_data():
        data = request.get_json()
        conn = get_db_connection()
        cur = conn.cursor()
        cur.execute('INSERT INTO items (name, description) VALUES (%s, %s);',
                   (data['name'], data['description']))
        conn.commit()
        cur.close()
        conn.close()
        return jsonify({'status': 'success'})
    
    if __name__ == '__main__':
        app.run(host='0.0.0.0', port=5000)

Dockerfile für Backend
-----------------------

.. code:: dockerfile

    FROM python:3.9-slim
    
    WORKDIR /app
    
    COPY requirements.txt .
    RUN pip install -r requirements.txt
    
    COPY app.py .
    
    EXPOSE 5000
    
    CMD ["python", "app.py"]

Requirements-Datei
------------------

.. code:: text

    Flask==2.3.3
    psycopg2-binary==2.9.7

Erweiterte Docker Compose
--------------------------

.. code:: yaml

    version: '3.8'
    
    services:
      web:
        build: .
        ports:
          - "8080:80"
        depends_on:
          - api
      
      api:
        build:
          context: .
          dockerfile: Dockerfile.backend
        ports:
          - "5000:5000"
        depends_on:
          - db
        environment:
          - DB_HOST=db
          - POSTGRES_DB=demo
          - POSTGRES_USER=demo
          - POSTGRES_PASSWORD=demo123
      
      db:
        image: postgres:13
        environment:
          - POSTGRES_DB=demo
          - POSTGRES_USER=demo
          - POSTGRES_PASSWORD=demo123
        volumes:
          - postgres_data:/var/lib/postgresql/data
          - ./init.sql:/docker-entrypoint-initdb.d/init.sql
    
    volumes:
      postgres_data:

Datenbank-Initialisierung
--------------------------

.. code:: sql

    CREATE TABLE IF NOT EXISTS items (
        id SERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        description TEXT,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    
    INSERT INTO items (name, description) VALUES
        ('Docker', 'Container-Platform'),
        ('Kubernetes', 'Container-Orchestrierung'),
        ('PostgreSQL', 'Datenbank-System');



.. class:: new-section

Zusammenfassung
----------------

.. summary::

    .. class:: incremental-list

    - **Virtualisierung** ermöglicht effiziente Nutzung von Hardware-Ressourcen
    - **Container** bieten leichtgewichtige Alternative zu VMs
    - **Docker** ist der Standard für Container-Plattformen
    - **Orchestrierung** automatisiert das Management von Container-Anwendungen
    - **Multi-Container-Setups** ermöglichen komplexe Anwendungsarchitekturen



Best Practices für Docker
--------------------------

.. deck::

    .. card::

        .. rubric:: Image-Erstellung

        .. class:: incremental-list

        - **Multi-Stage Builds** für kleinere Images
        - **Non-Root User** für Sicherheit
        - **Layer-Caching** optimieren
        - **Security Updates** regelmäßig durchführen

    .. card::

        .. rubric:: Container-Management

        .. class:: incremental-list

        - **Health Checks** implementieren
        - **Resource Limits** setzen
        - **Logging** konfigurieren
        - **Backup-Strategien** für Volumes

    .. card::

        .. rubric:: Sicherheit

        .. class:: incremental-list

        - **Minimal Images** verwenden
        - **Secrets** nicht im Image speichern
        - **Network Policies** definieren
        - **Regular Updates** durchführen



Ausblick
---------

.. observation::

    Die Container-Technologie entwickelt sich weiter:

    .. class:: incremental-list

    - **Serverless Container** (AWS Fargate, Azure Container Instances)
    - **WebAssembly (WASM)** als Alternative zu Container
    - **Edge Computing** mit Container-Technologie
    - **GitOps** für automatisiertes Deployment
    - **Service Mesh** für Microservice-Kommunikation

.. conclusion::

    Container und Virtualisierung sind fundamentale Technologien für moderne Softwareentwicklung und -deployment. Die praktische Anwendung mit Docker bietet eine solide Grundlage für weiterführende Themen wie Kubernetes und Cloud-Native-Entwicklung.



.. class:: exercises

Übung
------

.. story::

    .. exercise:: Erweiterte Docker-Praxis

        Erstellen Sie eine vollständige Anwendung mit Docker:

        .. class:: incremental-list

        1. **Frontend** (React/Vue.js) containerisieren
        2. **Backend-API** (Node.js/Python) erstellen
        3. **Datenbank** (PostgreSQL/MongoDB) integrieren
        4. **Reverse Proxy** (Nginx) konfigurieren
        5. **Docker Compose** für gesamtes Setup
        6. **Environment-spezifische Konfiguration**

        .. solution::

            Eine mögliche Lösung finden Sie in der separaten Übungsdatei `docker-exercise-solution/`.