.. meta::
    :author: Michael Eichberg
    :keywords: "Projektkonzeption" und "Projektrealisierung"
    :description lang=de: Projektkonzeption und Projektrealisierung
    :id: project-w3wi_106.2-107.1-projektkonzeption_und_projektrealisierung
    :first-slide: last-viewed

.. include:: ../docutils.defs



Projektkonzeption und -realisierung
==========================================

*W3WI_106.2 und W3WI_107*

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
:Version: 23SEB



.. class:: transition-fade

Einführung
----------------------------------------

.. class:: incremental-list

1. Allgemeines

   .. class:: incremental-list

   1. Teaser des Projekts
   2. Vorstellung des Ablaufs der Projektkonzeption und -realisierung
   3. Zusammenfinden in 3 Teams
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



Erster Prototyp der Gruppe Deploo
----------------------------------

.. deck::

    .. card:: center-content
  
        .. image:: screenshots-deploo/Dashboard.webp
            :class: screenshot 
            :height: 980px
        
    .. card:: center-content
  
        .. image:: screenshots-deploo/Courses.webp
            :class: screenshot 
            :height: 980px
  
    .. card:: center-content
  
        .. image:: screenshots-deploo/Templates.webp
            :class: screenshot 
            :height: 980px
          
    .. card:: center-content
  
        .. image:: screenshots-deploo/OpenstackConfig.webp
            :class: screenshot 
            :height: 980px



.. HINWEIS: Gruppenbenotung -> Einzelbenotung nur auf explizitem Wunsch hin


Beispielanwendungen
-----------------------

.. story::

    .. class:: incremental-list list-with-explanations

    - Eine einfache VM mit NodeJS für Studierende bzw. Gruppen von Studierenden
      (ggf. inkl. Quotas und Porteinschränkungen)
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
    - Eine vorkonfigurierte Entwicklungsumgebung für die Programmierung
      (z.B. in Java mit Eclipse Theia)
    - Projektmanagementsoftware (z. B. OpenProject)
    - Ein Datenbankserver mit passenden Accounts (z. B. MySQL, PostgreSQL)
    - ...



.. class:: transition-move-to-top

Anforderungen bzw. Herausforderungen
----------------------------------------

.. story::

    .. rubric:: Funktionale Anforderungen an den AppStore

    .. class:: incremental-list

        - Es soll sowohl sowohl vorgefertigte Anwendungen (bzw. Anwendungstemplates)  geben, die einfach deployt werden können, als auch möglich sein dem App Store eigene (private) Anwendungen hinzuzufügen.
        - Beim Deployment von Anwendungen werden die notwendigen Konfigurationsschritte (möglichst)automatisiert durchgeführt:

          - Anlegen der entsprechenden Anzahl an VMS
          - Anlegen von Nutzeraccounts bzw. Gruppenaccounts (ggf. basierend auf CSV Dateien, manuell oder von einem LDAP Server); alternativ Konfiguration von SSH Keys
          - Einrichten von Netzwerkkonfigurationen (ggf. inkl. Erzeugung von Zertifikaten)
          - Konfiguration von DNS.
          - ...

        - Es ist dem Dozenten ggf. möglich (einzelne) Anwendungen neu zu starten bzw. neu zu konfigurieren (z. B. wenn Anwendungen nicht mehr reagieren weil zum Beispiel im Rahmen des Pentesting die ZielVMs abgestürzt sind).
        - Anwendungen können in unterschiedlichen Versionen vorliegen.
        - Anwendungen können einfach aktualisiert werden (z. B. können die Anwendungen in einem GIT liegen und ein Dozent bzw. Administrator kann darüber Aktualisierungen durchführen).
        - Jedem Dozenten sind nur begrenzte Ressourcen zur Verfügung gestellt (z. B. nur 10 VMs). Es kann ggf. sinnvoll sein, mehrere Instanzen einer Anwendung in einer VM zu deployen.

    .. class:: incremental

    .. rubric:: Nicht-funktionale Anforderungen

    .. class:: incremental-list list-with-explanations

        - Der App-Store ist eine eigenständige Webanwendung, der die öffentlichen Schnittstellen von OpenStack verwendet.

          (D.h. er ist kein Plug-in.)
        - Der Technologiestack sollte überschaubar sein, damit das Projekt ggf. später weiterentwickelt/übernommen werden kann.

          (Dies ist Lizenz und Ergebnisabhängig.)

          .. supplemental::

            Jedes Team ist frei in der Wahl der Softwarelizenz. Sie können sich auch entscheiden Ihr Projekt unter keine explizite Lizenz zu stellen.

            Eine weitere Übernahme des Projekts ist natürlich nur dann möglich, wenn Sie eine entsprechende Open-Source Lizenz wählen (Informationen finden Sie z.B. auf: https://www.linux-magazin.de/ausgaben/2025/04/software-lizenzen-teil-1/ oder auf https://www.computerweekly.com/de/definition/Softwarelizenz bzw. auf https://opensource.org/).

            **Die Benotung des Projekts ist unabhängig von der Lizenzfrage.**

        - Wartbarkeit und Qualität sind primäre Ziele.

    .. hint::
        :class: incremental

        Die genauen Features bzw. Anforderungen legen wir gemeinsam in den folgenden Wochen im Rahmen von *Requirements Workshops* fest.

        Die oben genannten Anforderungen stellen nur den initialen, groben Rahmen dar.



.. class:: transition-move-to-top

Möglicherweise relevante Technologien
----------------------------------------

.. note:: 
  :class: width-55

  Abgesehen von OpenStack als Deploymentziel sind Sie frei in der Wahl der Technologien.

Sowohl für die Entwicklung des App Stores, als auch der Anwendungen (ggf. als Inspiration):


.. story::

    .. class:: incremental-list

    - Docker
    - Kubernetes
    - FluxCD

      .. epigraph::

        Flux is a tool for keeping Kubernetes clusters in sync with sources of configuration, and automating updates to configuration when there is new code to deploy.

    - HELM Charts

      .. epigraph::

        Charts describe even the most complex apps, provide repeatable application installation, and serve as a single point of authority.

    - Open Component Model

      .. epigraph::

        The Open Component Model (OCM) is your one-stop open-source Software Bill of Delivery (SBoD) for packaging, signing, transporting and deploying your artifacts – preserving end-to-end security, integrity and provenance.



.. class:: transition-scale

Benotungsgrundlage
--------------------

.. deck::

  .. card::
  
    .. rubric:: Projektkonzeption

    Abgaben:
    
    - Präsentationen [#]_
    - Dokumentation mit insgesamt 50 Punkten.

    .. [#] `Vorträge: Hinweise und Bewertungskriterien <https://delors.github.io/allg-vortraege/folien.de.rst.html?ld-slide-no=1>`__

  .. card::

    .. rubric:: Projektrealisierung

    Abgaben:
    
    - gegen Ende dieses Semesters Zwischenpräsentation (15% der Projektnote).
    - am Ende des nächsten Semesters/des Projekts ist das Produkt als solches vorzuführen und abzugeben (60% der Projektnote). 
    - die qualitätsgesicherte Nutzer- (4%) und Entwicklerdokumentation (8%) abzugeben
    - ein kurzes Tutorialvideo (3%)
    - ein Video für Entwickler, dass die Struktur des Projekts und den Code erklärt und insbesondere auch als Grundlage für die Bewertung der Softwarequalität dient (5%).
    - ein selbstbezogener Reflexionsbericht (5%)

  .. card::

    .. important::
      
      Jedes Team bzw. Teammitglied muss genau protokollieren wer an welchem Teil des Projekts wie viel Arbeit investiert hat. Dies ist notwendig, um am Ende des Projekts eine individuelle Benotung zu ermöglichen. Hierfür werden die Protokolle herangezogen. Stellen Sie sicher, dass jeder im Team in etwa gleich viel Arbeit an entsprechend bewerteten Teilen des Projekts investiert hat.
      
      Sollten Sie als Team bewertet werden wollen, so ist dies auch möglich, dann brauche ich jedoch von jedem Teammitglied eine entsprechende individuelle EMail (kein CC!), dass die Benotung auf Teamebene gewünscht ist. In diesem Fall entfällt die individuelle Protokollierung der Arbeitszeiten. 
      
      **Der Reflexionsbericht ist jedoch immer individuell zu verfassen und wird immer individuell benotet.**


.. class:: new-section

Projektkonzeption
--------------------------



1. Projektkonzeption - 19.11.2025
----------------------------------------------------------

.. rubric:: Teil 1 - Einführung

- `Kurze Wiederholung von Aspekten des Softwareprojektmanagements <https://delors.github.io/se-softwareprojektmanagement/folien.de.rst.html?ld-slide-no=1>`__

- `Aspekte der Projektdurchführung <https://delors.github.io/lab-aspekte-der-projektdurchfuehrung/folien.de.rst.html?ld-slide-no=1>`__

.. rubric:: Teil 2 - Requirements Workshop zur Erhebung der User Stories

**Je Gruppe**: ca. 60 Minuten, um User Stories zu erfassen.

.. rubric:: Teil 3 - User Story Diskussion

**Je Gruppe**: Darstellung und Diskussion der User Stories

.. attention::

  Es wird erwartet, dass alle Gruppen basierend auf den Erkenntnissen ggf. Ihre User Stories anpassen/erweitern.



2. Projektkonzeption - 24.11.2025 [Online]
-------------------------------------------------------------------------------------------------

Gruppenindividuelle Unterstützung bei der Ausarbeitung der initialen Architektur und des Technologiestacks. Beantwortung von Rückfragen.

:BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa/ (Optional)

Je Gruppe:

- Vertraut machen mit dem Technologiestack
- Prototyping / Entwicklung eines Spike
- Ausarbeitung der initialen Präsentation und der Abgabe.



3. Projektkonzeption - 01.12.2025
----------------------------------

.. scrollable::

  .. rubric:: Teil 1 - Präsentation und Vorführung

  (*Je Gruppe max. 45 Minuten*)

  **Vorführung**

  - lokale OpenStack Umgebung

  **Präsentation**

  .. class:: dd-margin-left-3em

  :Bewertete Bestandteile:

    - [**3 Punkte**] Projektorganisation: Teamname, Teamaufteilung, Zuständigkeiten, Kommunikationswege, Meetingstruktur, Stakeholdermanagement, Hardware-Ressourcen, ...
    - [**2 Punkte**] gewählte Lizenz(en): für das Endprodukt und die Apps gewählte Lizenz(en) mit Begründung
    - [**4 Punkte**] Risikomanagement: d. h. identifizierte Risiken und Maßnahmen zur Risikominimierung/-vermeidung

  :Unbewertet Bestandteile:

    - initiale Architektur inkl. Technologiestack für Apps und App Store
    - UI Konzept für den App Store
    - Build-Prozess und CI/CD-Pipeline (für App Store und Anwendungen)
    - Erfasste (nicht-)funktionale Anforderungen
    - Geplante Qualitätsmaßnahmen

  **Abgaben**

    Es ist ein Dokument (PDF) abzugeben, dass die obigen bewerteten Punkte abdeckt. Ein Abgabe nur der Präsentation ist nicht ausreichend. Geben Sie die Präsentation und das Dokument bis zum 1.12.2025, 7:00Uhr über Moodle ab.

    Aus dem Dokument muss der Teamname und der Hauptansprechpartner für die Projektleitung/Administratoren klar ersichtlich sein. Diese Person muss garantieren, dass sie - sobald Sie die offizielle OpenStack Umgebung nutzen im zweiten Teil des Projekts - auch für Rückfragen zur Verfügung steht. Die Reaktionszeit auf Mails ist max. 24 Stunden an Werktagen.

  .. rubric:: Teil 2 - gemeinsame Verfeinerung der User Stories



4. Projektkonzeption - 08.12.2025 [Online]
-------------------------------------------------------------------------------------------------

Gruppenindividuelle Unterstützung und Beantwortung von Rückfragen.

:BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa/ (Optional)

Je Gruppe:

- Verfeinerung der initialen Architektur
- Fortsetzen des Prototypings 
- Ausarbeitung der Abschlusspräsentation und der Endabgabe.



5. Projektkonzeption - 15.12.2025
--------------------------------------------

.. rubric:: Teil 1 - Abschluss der Konzeptionsphase

(Je Gruppe max. 45 Minuten inkl. Diskussion/Rückfragen.)

    
*(Sie können die Reihenfolge der folgenden Punkte bei der Präsentation anpassen.)*

- [4P] Präsentation der Technologien des (aktuellen) Technologiestacks
- [6P] Architektur des App-Stores mit Hilfe von Standardkonformen UML Diagrammen
- [4P] UI Konzept (z. B. Wireframes oder Mockups)

- [2P] Build-Prozess und CI/CD-Pipeline für App Store
- [2P] Build-Prozess und CI/CD-Pipeline für Apps

- [8P] Qualitätsmaßnahmen: Welche sind wann und warum geplant. Wie sieht die konkrete Ausgestaltung aus.

- [3P] nicht-funktionale Anforderungen
- [8P] funktionale Anforderungen (vollständige User Stories inkl. Akzeptanzkriterien)

Abgaben

- Präsentation (PDF)
- Vollständige Dokumentation (PDF)
- Dokumentierte Zeitaufwände
- [2P] Pro Person: Reflexionsbericht (PDF)


.. rubric:: Teil 2 - Übergang zum Projekt

**Je Gruppe**: Planung und Präsentation der nächsten Schritte




.. class:: new-section transition-fade

Projektrealisierung
-----------------------

.. attention::

  Alle folgenden Informationen sind vorläufig und können sich noch ändern.


1. Projektrealisierung - 23.12.2025
---------------------------------------------

.. rubric:: Teil 1

**Je Gruppe**:

- Präsentation des aktuellen Projektfortschritts (Umgesetzte User Stories; geplanter und tatsächlicher Aufwand; Entwicklung der Story Points)
- ggf. Präsentation von unerwarteten Problemen und deren Lösung
- Präsentation der Ressourcenplanung (zur Verfügung stehende Arbeitszeit pro Person) für nächste Iteration

.. rubric:: Teil 2

**Gesamt**: Iterationsplanung (d. h. Wahl der nächsten User Stories)

.. container:: fade-out

    .. rubric:: Teil 3

    **Je Gruppe (für sich/im Nachgang)**: Aktualisierung der Schätzung der User Stories




2. Projektrealisierung - 12.01.2026 [Optional - Online: 13:15Uhr bis 15:15Uhr und 16:45Uhr bis 18:45Uhr]
----------------------------------------------------------------------------------------------------------

:BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa/

**Je Gruppe**:

- Entwicklung der Anwendung (d. h. der gewählten User Stories)
- Durchführung der QS



3. Projektrealisierung - 14.01.2026
-------------------------------------------

.. rubric:: Teil 1

**Je Gruppe**:

- Präsentation des aktuellen Projektfortschritts (Umgesetzte User Stories; geplanter und tatsächlicher Aufwand; Entwicklung der Story Points)
- ggf. Präsentation von unerwarteten Problemen und deren Lösung
- Präsentation der Ressourcenplanung (zur Verfügung stehende Arbeitszeit pro Person) für nächste Iteration

.. rubric:: Teil 2

**Gesamt**: Iterationsplanung (d. h. Wahl der nächsten User Stories)

.. container:: fade-out

    .. rubric:: Teil 3

    **Je Gruppe (für sich/im Nachgang)**: Aktualisierung der Schätzung der User Stories




4. Projektrealisierung - 19.01.2026 [Optional - Online]
-------------------------------------------------------------------------------------------------

:BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa/

**Je Gruppe**:

- Entwicklung der Anwendung (d. h. der gewählten User Stories)
- Durchführung der QS
- Vorbereitung der Zwischenpräsentation



.. class:: fade-out

5. Projektrealisierung - 28.01.2026
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



.. class:: new-section transition-scale fade-out

Projektrealisierung Teil 2
--------------------------------------------



.. class:: fade-out

6. Projektrealisierung - DD. MMMM 2026
--------------------------------------------

- Noten Zwischenpräsentation
- Überblick über den weiteren Ablauf
- nächste Schritte / User Stories auswählen

.. container:: accentuate

  Start der ersten Iteration



.. class:: fade-out

7. Projektrealisierung - DD. MMMM 2026
--------------------------------------------

.. container:: exclamation-mark

  Online: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa/

- Diskussion von Rückfragen



.. class:: fade-out

8. Projektrealisierung - DD. MMMM 2026
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



.. class:: fade-out

9. Projektrealisierung - DD. MMMM 2026
--------------------------------------------

.. container:: exclamation-mark

  Online: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd

- Diskussion von Rückfragen



.. class:: fade-out

10. Projektrealisierung - DD. MMMM 2026
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



.. class:: fade-out

11. Projektrealisierung - DD. MMMM 2026
--------------------------------------------

.. container:: exclamation-mark

  Online: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd



.. class:: fade-out

12. Projektrealisierung - DD. MMMM 2026
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



.. class:: fade-out

Projektabschluss - DD. MMMM 2026
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
