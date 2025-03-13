.. meta:: 
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "Projektkonzeption" und "Projektrealisierung"
    :description lang=de: Projektkonzeption und Projektrealisierung
    :id: project-w3wi_106.2-107.1-projektkonzeption_und_projektrealisierung
    :first-slide: last-viewed

.. |date| date::

.. role:: ger
.. role:: peripheral
.. role:: obsolete



Projektkonzeption und -realisierung
==========================================

*W3WI_106.2 und W3WI_107*

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
:Version: WWI-22SEA



.. class:: transition-fade

Einführung
----------------------------------------

.. class:: incremental-list

1. Allgemeines

   .. class:: incremental-list

   1. Teaser des Projekts
   2. Vorstellung des Ablaufs der Projektkonzeption und -realisierung
   3. Zusammenfinden in 2 Teams
   4. Kurze Wiederholung von relevanten Themen

2. Start der Projektkonzeption
    


.. class:: transition-fade

Das Projekt
-------------

Entwicklung eines AppStores für Apps zum Deployment auf OpenStack. 

.. admonition:: Ziel

    Dozenten können - ohne tiefergehende Kenntnisse von OpenStack etc. - Anwendungen auf der OpenStack Platform deployen, die dann durch Studierende im Rahmen von Lehrveranstaltungen genutzt werden können. 
    
    Den Dozenten soll eine einfache Administrierbarkeit der Anwendungen ermöglicht werden.

    Den Administratoren ist es stets möglich Dozenten zu unterstützen.



.. class:: transition-move-to-top

Beispielanwendungen
-----------------------

.. class:: incremental-list

    - VMs für Pentesting Zwecke:
       - VMs die als Angriffsziele dienen
       - VMs mit Angriffswerkzeugen
       - ggf. in einem zugangsgeschützten virtuellen Netzwerk
    - Ein Gitlabserver für das Verwalten von Projekten eines bestimmten Kurses
    - Ein Jenkins Server für das Durchführen von Continuous Integration
    - Ein Jupyter Notebook Server für die Durchführung von Übungen
    - Eine vollständige Netzwerkinfrastruktur
    - Eine OpenStack Umgebung für Lehrveranstaltungen im Bereich Cloud Computing
    - Ein Kubernetes Cluster für das Deployment von Microservices
    - Ein Kubernetes Cluster für KI Anwendungen
    - Eine vorkonfigurierte Entwicklungsumgebung für die Programmierung in Java (z. B. Eclipse Theia)
    - Projektmanagementsoftware (z. B. OpenProject)...


.. class:: transition-move-to-top

Anforderungen bzw. Herausforderungen
----------------------------------------

.. story::

    .. class:: incremental-list

    - Es soll sowohl sowohl vorgefertigte Anwendungen (bzw. Anwendungstemplates)  geben, die einfach deployt werden können, als auch möglich sein dem App Store eigene (private) Anwendungen hinzuzufügen.
    - beim Deployment von Anwendungen werden die notwendigen Konfigurationsschritte möglichst automatisiert durchgeführt:
    
      - Anlegen der entsprechenden Anzahl an VMS
      - Anlegen von Nutzeraccounts bzw. Gruppenaccounts (ggf. basierend auf CSV Dateien, manuell oder von einem LDAP Server); alternativ Konfiguration von SSH Keys
      - Einrichten von Netzwerkkonfigurationen (ggf. inkl. Erzeugung von Zertifikaten)
      - Konfiguration von DNS...
      
    - Es ist dem Dozenten ggf. möglich (einzelne) Anwendungen neu zu starten bzw. neu zu konfigurieren (z. B. wenn Anwendungen nicht mehr reagieren weil zum Beispiel im Rahmen des Pentesting die ZielVMs abgestürzt sind). 
    - Anwendungen können in unterschiedlichen Versionen vorliegen.
    - Anwendungen können einfach aktualisiert werden. (Z. B. können die Anwendungen in einem GIT liegen und ein Dozent bzw. Administrator kann darüber Aktualisierungen durchführen.)
    - jedem Dozenten sind nur begrenzte Ressourcen zur Verfügung gestellt (z. B. nur 10 VMs). Es kann ggf. sinnvoll sein, mehrere Instanzen einer Anwendung in einer VM zu deployen.

    .. hint:: 
        :class: incremental 

        Die genauen Features bzw. Anforderungen legen wir gemeinsam in den folgenden Wochen im Rahmen von *Requirements Workshops* fest. Die oben genannten Anforderungen dienen nur als Leitfaden.



.. class:: transition-move-to-top fade-out

Möglicherweise relevante Technologien
----------------------------------------

Sowohl für die Entwicklung des App Stores, als auch der Anwendungen:

.. note::
    :class: width-50

    Abgesehen von OpenStack sind Sie frei in der Wahl der Technologien.

- Kubernetes
- FluxCD (GitLab)
- HELM Charts
- Open Component Model



.. class:: transition-scale

Benotungsgrundlage
--------------------

- **Projektkonzeption**

  **Kleingruppenpräsentationen (und Dokumentation) mit insgesamt 50 Punkten.**

  Aus jeder Gruppe muss jeder in etwa gleich lang präsentieren. 

.. class:: incremental

- **Projektrealisierung**

  Gegen Ende dieses Semesters Zwischenpräsentation (20% der Projektnote). Die Präsentation kann stellvertretend gehalten werden. D. h. nicht jeder muss präsentieren.

  Am Ende des nächsten Semesters ist das Produkt als solches vorzuführen und abzugeben (50% der Projektnote). Weiterhin ist die Dokumentation der QS Maßnahmen (10% der Projektnote) abzugeben und eine Webseite und ein Produktvideo (20% der Projektnote) zu erstellen.




.. class:: fade-out

1. Projektkonzeption - 26.08.2024
----------------------------------------------------------

.. rubric:: Teil 1

- Einführung

- `Kurze Wiederholung von Aspekten des Softwareprojektmanagements <https://delors.github.io/se-softwareprojektmanagement/folien.de.rst.html?ld-slide-no=1>`__

- `Aspekte der Projektdurchführung <https://delors.github.io/lab-aspekte-der-projektdurchfuehrung/folien.de.rst.html?ld-slide-no=1>`__

    
.. rubric:: Teil 2 - *Requirements Workshop* zur Erhebung der User Stories 
    
Geteiltest `Kanboard <http://141.72.12.83/kanboard-1.2.39>`__

.. Nutzer: admin
   Passwort: Kanboard!Admin

**Je Gruppe**: ca. 60 Minuten, um User Stories zu erfassen 

.. rubric:: Teil 3

**Gesamt**: zusammenführen, verfeinern und priorisieren der User Stories



.. class:: fade-out

1. Projektkonzeption - 02.09.2024 [Optional - Online] 
-------------------------------------------------------------------------------------------------

:BBB: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd

Je Gruppe:

- Vertraut machen mit dem Technologiestack
- **Prototyping / Entwicklung eines Spike** 
- Ausarbeitung der initialen Präsentation\ [#]_  

.. [#] `(WIP) Vorträge: Hinweise und Bewertungskriterien <https://delors.github.io/allg-vortraege/folien.de.rst.html?ld-slide-no=1>`__



.. class:: fade-out

3. Projektkonzeption - 09.09.2024
----------------------------------
   
.. rubric:: Teil 1 - „informelle“ Präsentation und Vorführung

(*Je Gruppe max. 45 Minuten inkl. Diskussion/Rückfragen*)

- [Vorführung] einer "lokalen" OpenStack Umgebung
- [Präsentation] Architektur
- [Präsentation] UI Konzept für den App Store
- [Präsentation] Build-Prozess und CI/CD-Pipeline (für App Store und Anwendungen)
- [Präsentation] Erfasste nicht-funktionale Anforderungen
- [Präsentation] Geplante Qualitätsmaßnahmen

.. rubric:: Teil 2 - Verfeinerung der User Stories

**Je Gruppe**: Verfeinerung der User Stories

**Gesamt**: Zusammenführung der User Stories



.. class:: fade-out

4. Projektkonzeption - 16.09.2024 [Optional - Online] 
-------------------------------------------------------------------------------------------------

:BBB: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd

Je Gruppe:

- Schätzung der User Stories
- Ausarbeitung von Akzeptanzkriterien für die User Stories
- Verfeinerung der initialen Architektur
- Ausarbeitung der Abschlusspräsentation



.. class:: fade-out

5. Projektkonzeption - 23.09.2024
--------------------------------------------

.. rubric:: Teil 1 - Abschluss der Konzeptionsphase

**Je Gruppe**: Abschlusspräsentation (max. 60 Minuten inkl. Diskussion/Rückfragen)

.. rubric:: Teil 2 - Übergang zum Projekt

**Je Gruppe**: Ressourcenplanung für nächste Iteration

**Gesamt**: Iterationsplanung: Wahl der ersten User Stories


.. supplemental::

    .. admonition:: Notenrelevante Präsentationen (je Gruppe max 50 Punkte) 

        - [1P] Projektorganisation (Projektname, Zuständigkeiten)
        - [1P] gewählte Lizenz(en)
    
        *(Sie können die Reihenfolge der folgenden Punkte bei der Präsentation anpassen.)*

        - [4P] identifizierte Risiken und Maßnahmen zur Risikominimierung/-vermeidung    
        - [6P] Präsentation der Technologien des (aktuellen) Technologiestacks 
        - [8P] Architektur mit Hilfe von UML Diagrammen 
        - [4P] UI Konzept (z. B. Wireframes oder Mockups)
        - [3P] nicht-funktionale Anforderungen

        - [3P] Build-Prozess und CI/CD-Pipeline

        - [10P] Qualitätsmaßnahmen: Welche sind wann und warum geplant. Wie sieht die konkrete Ausgestaltung aus.

        - [8P] Akzeptanzkriterien für die User Stories
        - [2P] Schätzung der User Stories 



.. class:: transition-fade fade-out

1. Projektrealisierung - 30.09.2024
---------------------------------------------

.. rubric:: Teil 1

**Je Gruppe**: 

- Präsentation des aktuellen Projektfortschritts (Umgesetzte User Stories; geplanter und tatsächlicher Aufwand; Entwicklung der Story Points)
- ggf. Präsentation von unerwarteten Problemen und deren Lösung 
- Präsentation der Ressourcenplanung (zur Verfügung stehende Arbeitszeit pro Person) für nächste Iteration

.. rubric:: Teil 2

**Gesamt**: Iterationsplanung (d. h. Wahl der nächsten User Stories)

.. container:: faded-to-white

    .. rubric:: Teil 3

    **Je Gruppe (für sich/im Nachgang)**: Aktualisierung der Schätzung der User Stories



.. class:: fade-out

2. Projektrealisierung - 07.10.2024 [Optional - Online] 
-------------------------------------------------------------------------------------------------

:BBB: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd

**Je Gruppe**: 

- Entwicklung der Anwendung (d. h. der gewählten User Stories)
- Durchführung der QS



.. class:: fade-out

3. Projektrealisierung - 14.10.2024
-------------------------------------------

.. rubric:: Teil 1

**Je Gruppe**: 

- Präsentation des aktuellen Projektfortschritts (Umgesetzte User Stories; geplanter und tatsächlicher Aufwand; Entwicklung der Story Points)
- ggf. Präsentation von unerwarteten Problemen und deren Lösung 
- Präsentation der Ressourcenplanung (zur Verfügung stehende Arbeitszeit pro Person) für nächste Iteration

.. rubric:: Teil 2

**Gesamt**: Iterationsplanung (d. h. Wahl der nächsten User Stories)

.. container:: faded-to-white

    .. rubric:: Teil 3

    **Je Gruppe (für sich/im Nachgang)**: Aktualisierung der Schätzung der User Stories



.. class:: fade-out

4. Projektrealisierung - 21.10.2024 [Optional - Online] 
-------------------------------------------------------------------------------------------------

:BBB: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd

**Je Gruppe**: 

- Entwicklung der Anwendung (d. h. der gewählten User Stories)
- Durchführung der QS
- Vorbereitung der Zwischenpräsentation



.. class:: fade-out

5. Projektrealisierung - 28.10.2024
--------------------------------------------
    
**Je Gruppe**: Zwischenpräsentation (ca. 45 - 60 Minuten je Gruppe inkl. Diskussion/Rückfragen)

.. supplemental::

    .. admonition:: Notenrelevante Bestandteile (20% der endgültigen Projektnote) 

        - [8P] [Vorführung] aktueller Projektstand
  
          Hier ist eine Livedemo des aktuellen Projektstandes notwendig. Es sollte klar ersichtlich sein, welche Funktionalitäten bereits umgesetzt wurden und welche noch nicht.

        - [5P] [Präsentation] aktuell implementierte Architektur
  
          Eine Darstellung der *Architektur* unter Verwendung der UML ist notwendig.  Ggf. ist auch eine Darstellung des Datenbankschemas notwendig.

        - [4P] [Präsentation] Stand der QS
          
          Eine präzise Darstellung der bisher durchgeführten QS-Maßnahmen und der Ergebnisse ist notwendig. 
        - [3P] [Präsentation] Build-Prozess (und CI/CD-Pipeline)
     
          Es sollen alle Schritte genau dargelegt werden. Inkl. Konfiguration und Zielen. Sollten Standardkonfigurationen (zum Beispiel für Codeformatierung) verwendet werden, so ist ein Verweis darauf ausreichend. Es muss klar ersichtlich sein, welche Schritte manuell und welche automatisiert durchgeführt werden. Für manuelle Schritte ist ggf. zu begründen warum diese nicht automatisiert werden können. Insbesondere ist der Prozess darzustellen, wie auf Probleme (zum Beispiel beim Merge, Tests, ...) reagiert wird.



.. class:: new-section transition-scale

Projektrealisierung Teil 2
--------------------------------------------



.. class:: transition-fade

6. Projektrealisierung - 18. Feb. 2025
--------------------------------------------

- Noten Zwischenpräsentation
- Überblick über den weiteren Ablauf
- nächste Schritte / User Stories auswählen 

  (Kanboard: http://141.72.12.83/kanboard-1.2.39/)

.. container:: accentuate

  Start der ersten Iteration


7. Projektrealisierung - 24. Feb 2025 
--------------------------------------------

.. container:: exclamation-mark

  Online: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd

- Diskussion von Rückfragen 



8. Projektrealisierung - 11. März 2025
--------------------------------------------

- Präsentation des aktuellen Projektfortschritts (ca. 8 Minuten pro Gruppe)

  - Stand bzgl. User Stories
  - Stand bzgl. QS
  - Stand bzgl. Build-Prozess
  - Herausforderungen
  - ggf. Live Demo

- Iterationsplanung (Wahl der nächsten User Stories)

.. container:: accentuate

  Start der zweiten Iteration


9. Projektrealisierung - 17. März 2025
--------------------------------------------

.. container:: exclamation-mark

  Online: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd

- Diskussion von Rückfragen



10. Projektrealisierung - 25. März 2025
--------------------------------------------

- Präsentation des aktuellen Projektfortschritts (ca. 8 Minuten pro Gruppe)

  - Stand bzgl. User Stories
  - Stand bzgl. QS
  - Stand bzgl. Build-Prozess
  - Herausforderungen
  - ggf. Live Demo

- Iterationsplanung (Wahl der nächsten User Stories)

.. container:: accentuate

  Start der dritten Iteration



11. Projektrealisierung - 31. März 2025 
--------------------------------------------

.. container:: exclamation-mark

  Online: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd



12. Projektrealisierung - 8. Apr 2025 
-------------------------------------------- 

- Festlegen der abzuschließenden User Stories
- Besprechung der Endabgaben und der Bewertungskriterien
- Diskussion der Anforderungen an die Abschlusspräsentation

.. story:: 

  .. rubric:: Notenrelevante Bestandteile 

  .. class:: incremental-list

  :`Zwischenpräsentation`:obsolete::	
      ∑ 20 (erledigt)

  :Projekt: 
    **∑ 50**

    - Sourcecode	**25**
    - Build-Prozess	**5**
    - Dokumentation: Architektur	**10** (*4 Punkte* davon ergeben sich aus der Abschlusspräsentation.)
    - Documentation: Installation und Konfiguration	**5** (*2 Punkte* davon ergeben sich aus der Abschlusspräsentation.)
    - Reflexionsbericht	**5** (*pro Person*)


  :QS:
    **∑ 10**

    - QS Maßnahmen - Beschreibung	**5**
    - QS Maßnahmen - Durchführung	**5**

  :Produktvideo:
    **∑ 20**

    - Produktvideo - Inhalt	**15**
    - Produktvideo - (technische) Qualität	**5**


  .. container:: accentuate incremental

    Start der vierten Iteration



Projektabschluss - 22. Apr 2025 
-----------------------------------------

.. class:: list-with-explanations

- Abschlusspräsentationen (Pro Gruppe 30 Minuten + Fragen)

  - ca. 15 Minuten Präsentation der Architektur und der Installation und Konfiguration
  - ca. 15 Minuten *Live Demo* des Produkts
- Diskussion des Standes dieser Iteration und ggf. Anpassung des Scopes.

.. container:: accentuate incremental margin-bottom-1em

  Die finale Einreichung aller Dokumente etc. erfolgt über Moodle am Ende des Semesters (d. h. am Sonntag nach der letzten Klausur.)

  :peripheral:`Videos sind per Downloadlink zur Verfügung zu stellen. Die Auflösung sollte dabei (1600-1920) x (1080-1200) auf keinen Fall überschreiten.`

.. attention::
  :class: incremental 

  Dies markiert das Endes der vierten Iteration!


