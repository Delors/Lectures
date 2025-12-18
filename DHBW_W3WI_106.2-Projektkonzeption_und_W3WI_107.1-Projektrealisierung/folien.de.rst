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
:Version: 23SEB - Rev. 18.12.2025



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

            **Die Benotung des Projekts ist unabhängig von der gewählten Lizenz.**

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
    
    - [**1P**] Produktvorführung 
    - [**47P**] Dokumentationen und Präsentationen [#]_
    - [**2P** je Person] Selbstbezogener Reflexionsbericht

    .. [#] `Vorträge: Hinweise und Bewertungskriterien <https://delors.github.io/allg-vortraege/folien.de.rst.html?ld-slide-no=1>`__

  .. card::

    .. rubric:: Projektrealisierung

    Abgaben:
    
    - [**18P**] Zwischenpräsentation und aktualisierte Dokumentation (am Ende des ersten Semesters)
    - [**78P**] das Produkt (am Ende des nächsten Semesters/des Projekts ist das Produkt als solches vorzuführen und abzugeben)
    - [**4P**] die qualitätsgesicherte Nutzerdokumentation
    - [**7P**] die qualitätsgesicherte Entwicklerdokumentation
    - [**4P**] ein Tutorialvideo
    - [**5P**] Entwicklervideo (Erklärung der Struktur des Projekts und des Code und insbesondere auch als Grundlage für die Bewertung der Softwarequalität dient)
    - [**4P** je Person] Selbstbezogener Reflexionsbericht

  .. card::

    .. warning::

      Nicht realistische Planungen bzw. Aussagen, zum Beispiel in Hinblick auf die Qualitätsmaßnahmen oder die Risiken, führen zu Abzügen bei der Benotung.

      Ihre Planung muss die Rahmenbedingungen des Projekts berücksichtigen (z. B. verfügbare Zeit, verfügbare Ressourcen, Vorkenntnisse der Teammitglieder, ...) und realistisch durchführbar sein.

  .. card::

    .. important::
      
      Jedes Team bzw. Teammitglied muss protokollieren *an welchem Teil des Projekts wer wie viel zum Ergebnis beigetragen hat*. Dies ist notwendig, um am Ende des Projekts eine individuelle Benotung zu ermöglichen, wenn dies erforderlich ist. Hierfür werden die Protokolle herangezogen. Stellen Sie sicher, dass jeder im Team in etwa gleich viel Arbeit an entsprechend bewerteten Teilen des Projekts investiert hat bzw. investieren konnte.
      
      Sollten Sie als Team bewertet werden wollen, so ist dies auch möglich, dann brauche ich jedoch von jedem Teammitglied eine entsprechende individuelle Email (kein CC!), dass die Benotung auf Teamebene gewünscht ist. Ich brauche dann pro Veranstaltung (Projektkonzeption und Projektrealisierung) eine Mail. Die individuelle Protokollierung der Arbeitszeiten ist jedoch weiterhin erforderlich.
      
      *Der Reflexionsbericht ist jedoch immer individuell zu verfassen und wird immer individuell benotet.*



Teamaufteilung
---------------------

3 Gruppen mit je 8 Studierenden mit folgender Aufteilung:

- 2 Entwickler für Apps
- 3 Entwickler für den App Store (Frontend)
- 3 Entwickler für den App Store (Backend)

:red:`Diese Aufteilung muss dokumentiert und gelebt werden. Ein Rollenwechsel ist nur in Ausnahmefällen und mit Absprache möglich.`

Darüber hinaus sind im Verlauf des Projekts mind. folgende Aufgaben frei zu verteilen:

- Projektmanagement
- Infrastrukturmanagement
- Qualitätsmanagement: Buildautomation, Testmanagement, Testautomatisierung, Codequalität, UX 
- Dokumentation: `Architektur <https://www.youtube.com/watch?v=3Gc5pN9sGzw>`__, Entwicklerdokumentation, Nutzerdokumentation



.. class:: new-section transition-scale

Projektkonzeption
--------------------------



1. Projektkonzeption - 19.11.2025
----------------------------------------------------------

.. rubric:: Teil 1 - Einführung

.. rubric:: Teil 2 - Requirements Workshop zur Erhebung der User Stories

**Je Gruppe**: ca. 60 Minuten, um User Stories zu erfassen.

.. rubric:: Teil 3 - User Story Diskussion

**Je Gruppe**: Darstellung und Diskussion der User Stories

.. attention::

  Es wird erwartet, dass alle Gruppen basierend auf den Erkenntnissen ggf. Ihre User Stories anpassen/erweitern.



2. Projektkonzeption - 24.11.2025 [Online]
-------------------------------------------------------------------------------------------------

:BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa/ 

- `Kurze Wiederholung von Aspekten des Softwareprojektmanagements <https://delors.github.io/se-softwareprojektmanagement/folien.de.rst.html?ld-slide-no=1>`__

- `Aspekte der Projektdurchführung <https://delors.github.io/lab-aspekte-der-projektdurchfuehrung/folien.de.rst.html?ld-slide-no=1>`__

Gruppenindividuelle Unterstützung bei der Ausarbeitung der initialen Architektur und des Technologiestacks. Beantwortung von Rückfragen.

Je Gruppe:

- Vertraut machen mit dem Technologiestack
- Prototyping / Entwicklung eines Spike
- Ausarbeitung der initialen Präsentation und der Abgabe

.. supplemental::

  Ein gute Video zum Thema Architekturdiagramme ist hier zu finden:
  
  `Visualising software architecture with the C4 model <https://www.youtube.com/watch?v=3Gc5pN9sGzw>`__



3. Projektkonzeption - 01.12.2025
----------------------------------

.. scrollable::

  .. rubric:: Teil 1 - Präsentation und Vorführung

  (*Je Gruppe max. 45 Minuten*)

  **Vorführung**

  - Lokale OpenStack Umgebung

  **Präsentation**

  .. class:: dd-margin-left-3em

  :Bewertete Bestandteile:

    - [**3P**] Projektorganisation: Teamname, Teamaufteilung, Zuständigkeiten, Kommunikationswege, Meetingstruktur, Stakeholdermanagement, Hardware-Ressourcen, ...
    - [**2P**] gewählte Lizenz(en): für das Endprodukt und die Apps gewählte Lizenz(en) mit Begründung
    - [**4P**] Risikomanagement: d. h. identifizierte Risiken und Maßnahmen zur Risikominimierung/-vermeidung

  :Unbewertet Bestandteile:

    - initiale Architektur inkl. Technologiestack für Apps und App Store
    - UI Konzept für den App Store
    - Build-Prozess und CI/CD-Pipeline (für App Store und Anwendungen)
    - Erfasste (nicht-)funktionale Anforderungen
    - Geplante Qualitätsmaßnahmen

  **Abgaben** (zum 1.12.2025, 7:00Uhr über Moodle)

  .. class:: list-with-explanations

  - Es ist ein Dokument (PDF) abzugeben, dass *die obigen bewerteten Punkte abdeckt*. 

    Aus dem Dokument muss der Teamname und der Hauptansprechpartner für die Projektleitung/Administratoren klar ersichtlich sein. Diese Person muss garantieren, dass sie - sobald Sie die offizielle OpenStack Umgebung nutzen im zweiten Teil des Projekts - auch für Rückfragen zur Verfügung steht. Die Reaktionszeit auf Mails sollte max. 24 Stunden an Werktagen betragen.
  - Die Präsentation (PDF) 

  Die Anzahl der erreichten Punkte ergibt sich aus der Präsentation und dem Dokument zusammen.

  .. rubric:: Teil 2 - gemeinsame Verfeinerung der User Stories



4. Projektkonzeption - 08.12.2025 [Online]
-------------------------------------------------------------------------------------------------

Gruppenindividuelle Unterstützung und Beantwortung von Rückfragen.

:BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa/ (Optional)

Je Gruppe:

- Verfeinerung der initialen Architektur
- Fortsetzen des Prototypings 
- Ausarbeitung der Abschlusspräsentation und der Endabgabe



5. Projektkonzeption - 15.12.2025
--------------------------------------------

.. scrollable::

  .. rubric:: Teil 1 - Abschluss der Konzeptionsphase

  (Je Gruppe max. 45 Minuten zzgl. Diskussion/Rückfragen.)

  **Vorführung**

  - [**1P**] Aktueller Prototyp des App Stores

  **Präsentation**

  *(Sie können die Reihenfolge der folgenden Punkte bei der Präsentation anpassen.)*

  - [**4P**] Präsentation der Technologien des (aktuellen) Technologiestacks inkl. Begründung der Notwendigkeit/Sinnhaftigkeit
  - [**4P**] Architektur des App-Stores (insbesondere mit Hilfe von Standardkonformen UML Diagrammen)
  - [**4P**] UI Konzept (z. B. Wireframes oder Mockups)
  - [**2P**] Entwicklungsmodell für die Apps

  - [**2P**] Build-Prozess und CI/CD-Pipeline für App Store
  - [**2P**] Build-Prozess und CI/CD-Pipeline für Apps

  - [**8P**] Qualitätsmaßnahmen: Welche sind wann und warum geplant. Wie sieht die konkrete Ausgestaltung aus.

  - [**4P**] nicht-funktionale Anforderungen
  - [**8P**] funktionale Anforderungen (vollständige User Stories inkl. Akzeptanzkriterien)

  **Abgaben** (:red:`zum 15.12.2025, 7:00Uhr über Moodle`)

  - Präsentation (PDF)
  - Vollständige Dokumentation (PDF)
  - Dokumentierte Zeitaufwände (PDF)
  - [**2P** je Person]: Reflexionsbericht mit max. 2 Seiten (PDF): Was lieft gut, was lief schlecht, was sollte im weiteren Verlauf beachtet werden, was habe ich persönlich gelernt, wie werde ich zukünftig versuchen den Projekterfolg sicherzustellen. Geben Sie auf dem Reflexionsbericht bitte auch den Namen der Gruppe und Ihre wesentliche Rolle(n) im Projekt an. 

  .. rubric:: Teil 2 - Übergang zum Projekt

  **Je Gruppe**: Planung und Präsentation der nächsten Schritte



.. class:: new-section transition-scale

Projektrealisierung - Teil 1 (1. Semester)
---------------------------------------------



1. Projektrealisierung - 18.12.2025 (Online)
---------------------------------------------

:BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa/

- Jede Gruppe:
  
  - Kurze Vorstellung des Standes (erledigte User Stories, nächste User Stories)
  - kurze Vorführung des Entwicklungsstandes

- Ggf. (gruppenindividuelle) Unterstützung bei der Projektrealisierung und Beantwortung von Rückfragen.



2. Projektrealisierung - 12.01.2026 (Online: 13:15 Uhr bis 15:15 Uhr und 16:45 Uhr bis 18:45 Uhr)
---------------------------------------------------------------------------------------------------

:BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa/

- Jede Gruppe: 

  - kurze Vorführung des Entwicklungsstandes
  - Kurze Vorstellung des Standes (erledigte User Stories, nächste User Stories, Build-Prozess, QS-Stand)

- Ggf. (gruppenindividuelle) Unterstützung bei der Projektrealisierung und Beantwortung von Rückfragen.



3. Projektrealisierung - 14.01.2026
-------------------------------------------

- Jede Gruppe:

  .. class:: list-with-explanations

  - Präsentation des aktuellen Projektfortschritts 
  
    (Umgesetzte User Stories; geplanter und tatsächlicher Aufwand; Entwicklung der Story Points)
  - ggf. Präsentation von unerwarteten Problemen und deren Lösung

  - Präsentation der Ressourcenplanung (zur Verfügung stehende Arbeitszeit pro Person) für nächste Iteration

- Besprechen der nächsten Anforderungen/User Stories.

- Jede Gruppe:

  - Aktualisierung und Schätzung des Backlogs
  - Mitteilung der Ziele für die nächste Iteration




4. Projektrealisierung - 19.01.2026 (Online)
-------------------------------------------------------------------------------------------------

:BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa/

- Jede Gruppe: 

  - kurze Vorführung des Entwicklungsstandes
  - Kurze Vorstellung des Standes (erledigte User Stories, nächste User Stories, Build-Prozess, QS-Stand)

- Anforderungen an die Zwischenpräsentation klären.
- Ggf. (gruppenindividuelle) Unterstützung bei der Projektrealisierung und Beantwortung von Rückfragen.



5. Projektrealisierung - 28.01.2026
--------------------------------------------

.. scrollable::

  .. rubric:: Abschluss der ersten Projektrealisierungsphase

  Zwischenpräsentation je Gruppe ca. 45 Minuten inkl. Diskussion/Rückfragen. 18 von 120 Punkten der Endnote ergeben sich aus der Zwischenpräsentation in Verbindung mit den abzugebenden Dokumenten.

  **Vorführung**

  - [**2P**] Aktueller Prototyp des App Stores
  - [**2P**] Prototypen zweier Apps inkl. Deployment auf OpenStack

  **Präsentation**

  .. class:: list-with-explanations

  - [**2P**] Aktueller Stand der statischen und dynamischen Architektur des App-Stores 

    Dies sollte im Wesentlichen über Abbildungen erfolgen und kann ggf. auch die Darstellung eines Datenbankschemas beinhalten. (Dies sollte im Wesentlichen auf die Architektur aus der Konzeptionsphase aufbauen und diese weiter verfeinern.
  - [**2P**] Technologiestack des App-Stores und der Apps.
  - [**1P**] Build-Prozess
  
    Es sollen alle Schritte dargelegt werden; inkl. Konfiguration und Zielen. Es muss klar ersichtlich sein, welche Schritte manuell und welche automatisiert durchgeführt werden. Für manuelle Schritte ist ggf. zu begründen warum diese nicht automatisiert wurden bzw. werden können.
  - [**1P**] Stand der QS
  
    Es ist insbesondere der Prozess darzustellen, wie auf Probleme (zum Beispiel beim Merge, Tests, ...) reagiert wird.
  - [**8P**] Backlog
  
    D. h. erledigte User Stories, noch offene User Stories, Schätzung der Story Points, Entwicklung der Velocity während des Projekts.

  **Abgaben** (:red:`zum 26.01.2026, 12:00Uhr über Moodle`)

  - Aktualisierte Projektdokumentation (PDF)

    Dies umfasst ggf. auch die Teile, die im Rahmen der Projektkonzeption abgegeben wurden. Weiterhin sind folgende Punkte abzudecken:

    - QS Maßnahmen (d.h. wer macht was wann und warum?)
    - Architektur
    - Technologiestack
    - (vorläufige) Installations- und Konfigurationsanleitung
    
  - Backlog: mit erledigten und offenen User Stories inkl. Akzeptanzkriterien, Story Points und Entwicklung der Velocity

            
  **Abgaben** (:red:`zum 28.01.2026, 7:00Uhr über Moodle`)

  - Präsentation (PDF)
  - dokumentierte Aufwände (PDF)



.. class:: new-section transition-scale fade-out

Projektrealisierung - Teil 2 (2. Semester)
--------------------------------------------

.. attention::

  Die folgenden Informationen werden zum Beginn des 2. Semester aktualisiert. Sie sind aktuell als Platzhalter zu verstehen.



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
    - Dokumentation: Architektur	**10** 

      (Achtung: die Dokumentation **muss explizit gegengezeichnet werden**. D.h. es muss klar ersichtlich sein wer die Dokumentation geschrieben hat und wer sie gegengezeichnet hat (Review); die Person dürfen sich nicht überschneiden.)
    - Dokumentation: Installation und Konfiguration	**5** 

      (Achtung: die Dokumentation **muss explizit gegengezeichnet werden**. D.h. es muss klar ersichtlich sein wer die Dokumentation geschrieben hat und wer sie gegengezeichnet hat (Review); die Person dürfen sich nicht überschneiden.)
    - Reflexionsbericht	**5** (*pro Person*) (Ausgestaltung analog zur Projektkonzeption)


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
