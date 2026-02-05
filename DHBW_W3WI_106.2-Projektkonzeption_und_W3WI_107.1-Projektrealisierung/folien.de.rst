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
:Version: 23SEB - Rev. 30.01.2026

.. supplemental::

  .. rubric:: Errata

  :27.01.2026: Im Rahmen der Zwischenpräsentation gibt es nur 17 Punkte statt 18 Punkte für die Endnote. (Die Bewertungskriterien bleiben unverändert; die Bewertung der Teilleistungen ändert sich nicht; es handelt sich *nur* um eine Korrektur der Summe.)
  :30.01.2026: Anpassungen für das 2. Semester vorgenommen.


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



.. class:: new-section transition-scale fade-out

Projektkonzeption
--------------------------


.. class:: fade-out

1. Projektkonzeption - 19.11.2025
----------------------------------------------------------

.. rubric:: Teil 1 - Einführung

.. rubric:: Teil 2 - Requirements Workshop zur Erhebung der User Stories

**Je Gruppe**: ca. 60 Minuten, um User Stories zu erfassen.

.. rubric:: Teil 3 - User Story Diskussion

**Je Gruppe**: Darstellung und Diskussion der User Stories

.. attention::

  Es wird erwartet, dass alle Gruppen basierend auf den Erkenntnissen ggf. Ihre User Stories anpassen/erweitern.



.. class:: fade-out

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



.. class:: fade-out

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


.. class:: fade-out

4. Projektkonzeption - 08.12.2025 [Online]
-------------------------------------------------------------------------------------------------

Gruppenindividuelle Unterstützung und Beantwortung von Rückfragen.

:BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa/ (Optional)

Je Gruppe:

- Verfeinerung der initialen Architektur
- Fortsetzen des Prototypings 
- Ausarbeitung der Abschlusspräsentation und der Endabgabe



.. class:: fade-out

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



.. class:: new-section transition-scale fade-out

Projektrealisierung - Teil 1 (1. Semester)
---------------------------------------------



.. class:: fade-out

1. Projektrealisierung - 18.12.2025 (Online)
---------------------------------------------

:BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa/

- Jede Gruppe:
  
  - Kurze Vorstellung des Standes (erledigte User Stories, nächste User Stories)
  - kurze Vorführung des Entwicklungsstandes

- Ggf. (gruppenindividuelle) Unterstützung bei der Projektrealisierung und Beantwortung von Rückfragen.


.. class:: fade-out

2. Projektrealisierung - 12.01.2026 (Online: 13:15 Uhr bis 15:15 Uhr und 16:45 Uhr bis 18:45 Uhr)
---------------------------------------------------------------------------------------------------

:BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa/

- Jede Gruppe: 

  - kurze Vorführung des Entwicklungsstandes
  - Kurze Vorstellung des Standes (erledigte User Stories, nächste User Stories, Build-Prozess, QS-Stand)

- Ggf. (gruppenindividuelle) Unterstützung bei der Projektrealisierung und Beantwortung von Rückfragen.


.. class:: fade-out

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


.. class:: fade-out

4. Projektrealisierung - 19.01.2026 (Online)
-------------------------------------------------------------------------------------------------

:BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa/

- Jede Gruppe: 

  - kurze Vorführung des Entwicklungsstandes
  - Kurze Vorstellung des Standes (erledigte User Stories, nächste User Stories, Build-Prozess, QS-Stand)

- Anforderungen an die Zwischenpräsentation klären.
- Ggf. (gruppenindividuelle) Unterstützung bei der Projektrealisierung und Beantwortung von Rückfragen.



.. class:: fade-out

5. Projektrealisierung - 28.01.2026
--------------------------------------------

.. scrollable::

  .. rubric:: Abschluss der ersten Projektrealisierungsphase

  - Zwischenpräsentation je Gruppe ca. 45 Minuten inkl. Diskussion/Rückfragen. 
  - 17 von 100 Punkten der Endnote ergeben sich aus der Zwischenpräsentation in Verbindung mit den abzugebenden Dokumenten. Bitte denken Sie daran alle relevanten Links in die Dokumentation aufzunehmen. Bitte denken Sie auch daran, dass Dokument mit Seitenzahlen zu versehen und ein Inhaltsverzeichnis zu erstellen. In diesem Rahmen sollten Sie auch sicherstellen, dass alle Abschnitte nummeriert sind.

  **Vorführung**

  - [**2P**] Aktueller Prototyp des App Stores
  - [**2P**] Prototypen zweier Apps inkl. Deployment auf OpenStack; d.h. es sollen zwei unterschiedliche Anwendungen deployed werden können. Welche Anwendungen dies sind, können Sie selbst entscheiden. Es reichen für diesen Punkt auch einfache Anwendungen wie zum Beispiel ein Ubuntu mit einem festen Nutzeraccount und/oder ein Windows mit einem festen Nutzeraccount. 

  **Präsentation**

  .. class:: list-with-explanations

  - [**2P**] Aktueller Stand der statischen und dynamischen Architektur des App-Stores 

    Dies sollte im Wesentlichen über Abbildungen erfolgen und kann ggf. auch die Darstellung eines Datenbankschemas beinhalten. 
    
    (Die Darstellungen sollten im Wesentlichen auf der Architektur aus der Konzeptionsphase aufbauen und diese weiter verfeinern.)
  - [**1P**] aktualisierter Technologiestack des App-Stores und der Apps. Im Rahmen der Präsentation ist es ggf. ausreichend zu sagen, dass sich nichts geändert hat oder auf Änderungen hinzuweisen. Die begleitende Dokumentation sollte ggf. einfach angepasst werden. D. h. Sie können im Wesentlichen die Dokumentation aus der Konzeptionsphase übernehmen und ggf. anpassen. 
  - [**1P**] Build-Prozess
  
    Es sollen alle Schritte dargelegt werden; inkl. Konfiguration und Zielen. Es muss klar ersichtlich sein, welche Schritte manuell und welche automatisiert durchgeführt werden. Für manuelle Schritte ist ggf. zu begründen warum diese nicht automatisiert wurden bzw. werden können.

    Benennen Sie ggf. die genauen Standards, die sie durchsetzen. Ggf. können Sie einen Anhang erstellen aus dem die genauen Konfigurationen hervorgehen.
  - [**1P**] Stand der QS
  
    Stellen Sie die von Ihnen als relevant identifizierten QS Ziele dar. Stellen Sie *für das wichtigste QS Ziel* dar, wie dieses erreicht werden soll. Für dieses ist insbesondere der vollständige Prozess darzustellen. Sie können diesbezüglich auf den Ergebnissen der Konzeptionsphase aufbauen. Vergessen Sie jedoch nicht den Prozess klar und vollständig darzustellen. Es muss ersichtlich sein, dass der Prozess realistisch durchführbar ist und dann auch ausreichend ist um das Qualitätsziel zu erreichen. 

    .. remark::
    
      Den Prozess für dieses QS Ziel müssen Sie leben und am Ende des Projekts auch nachweisen. 
      
      Ich empfehle dringend, dass Sie mich bzgl. der Nachweise frühzeitig im nächsten Semester kontaktieren, damit Sie wissen was ich als Nachweis akzeptiere.
  - [**8P**] Backlog
  
    D. h. erledigte User Stories, noch offene User Stories, neue User Stories, Schätzung der Story Points, Entwicklung der Velocity während des Projekts. Im wesentliche eine Fortschreibung der User Stories aus der Konzeptionsphase.

  **Abgaben** (:red:`zum 26.01.2026, 12:00Uhr über Moodle`)

  - Aktualisierte Projektdokumentation (PDF)

    Dies umfasst ggf. auch die Teile, die im Rahmen der Projektkonzeption abgegeben wurden. Insbesondere sind folgende Punkte zu aktualisieren:

    - Dokumentation für die relevanten QS-Ziele und QS Maßnahmen (d.h. wer macht was wann und warum und wie kann die Erreichung des Qualitätsziel sichergestellt werden?)
      
      .. attention::

        Für alles was Sie hier Versprechen müssen Sie am Projektende auch entsprechende Nachweise vorlegen können (z. B. Testberichte, Code-Analysen, Reviews, ...).
    - Architektur
    - Technologiestack
    - (vorläufige) Installations- und Konfigurationsanleitung
    
    - aktualisiertes Backlog mit erledigten und offenen User Stories. inkl. Akzeptanzkriterien.  Geben Sie auch die Story Points an und die Entwicklung der Velocity bzw. (alternativ) wieviel Zeit Sie für die Umsetzung geschätzt hatten und dann effektiv benötigt haben.

            
  **Abgaben** (:red:`zum 28.01.2026, 7:00Uhr über Moodle`)

  - Präsentation (PDF)
  - dokumentierte Aufwände (PDF)






.. class:: new-section transition-scale 

Projektrealisierung - Teil 2 (2. Semester)
--------------------------------------------



Geänderte Bewertungskriterien
-------------------------------

.. attention:: 

  Folgendes gilt für alle Abgaben (Präsentationen, Dokumentationen):

  - Jedes Aussage, die faktisch falsch ist, führt unmittelbar zu Punktabzug am Ende (und wird nicht ignoriert werden).
  - Jede Aussage, die in-sich blödsinnig ist, führt zu Punktabzug.
  - Jedes Aussage, die überflüssig ist ("Blah Blah") und nicht dem Thema der Aufgabe/Präsentation dient, führt zu Punktabzug.
  - Qualifizierende Attribute („schneller“, „besser“, „effizienter“, „leichter“, „schwerer“ ...) müssen durch konkrete Metriken belegt bzw. definiert werden, ansonsten führen sie zu Punktabzug.



6. Projektrealisierung - 13.05.2026
--------------------------------------------

- Noten Zwischenpräsentation
- Überblick über den weiteren Ablauf
- nächste Schritte / User Stories auswählen

.. container:: accentuate

  Start der ersten Iteration



7. Projektrealisierung - 18.05.2026
--------------------------------------------

.. container:: exclamation-mark

  Online: https://bbb.dhbw.de/rooms/eic-uue-f52-6xb/join

- Diskussion von Rückfragen




8. Projektrealisierung - 29.05.2026
--------------------------------------------
.. container:: accentuate

  Ende der ersten Iteration

- Präsentation des aktuellen Projektfortschritts

  - Stand bzgl. User Stories
  - Herausforderungen
  - Live Demo

- Iterationsplanung (Wahl der nächsten User Stories)

.. container:: accentuate

  Start der zweiten Iteration



9. Projektrealisierung - 01.06.2026
--------------------------------------------

.. container:: exclamation-mark

  Online: https://bbb.dhbw.de/rooms/eic-uue-f52-6xb/join

- Diskussion von Rückfragen



10. Projektrealisierung - 10.06.2026
--------------------------------------------

.. container:: accentuate

  Ende der zweiten Iteration

- Präsentation des aktuellen Projektfortschritts

  - Stand bzgl. User Stories
  - Herausforderungen
  - ggf. Live Demo

- Iterationsplanung (Wahl der nächsten User Stories)

.. container:: accentuate

  Start der dritten Iteration




11. Projektrealisierung - 15.06.2026
--------------------------------------------

.. container:: exclamation-mark

  Online: https://bbb.dhbw.de/rooms/eic-uue-f52-6xb/join

- Diskussion von Rückfragen
- Besprechung der Anforderungen an die Abschlusspräsentation
- Besprechung der Endabgaben und der Bewertungskriterien für die Abschlusspräsentation




12. Projektrealisierung - 30.06.2026
--------------------------------------------

.. container:: accentuate

  Ende der dritten Iteration

- Abschlusspräsentation: je Gruppe ca. 45 Minuten inkl. Diskussion/Rückfragen. 

  **Vorführung**

  .. attention::

    Es wird erwartet, dass der App Store und die Apps Release Candidate Status haben. Im Rahmen der Vorführung gefundene kleinere Fehler/Ungereimtheiten können aber in der letzten Iteration noch behoben werden und führen dann auch nicht zu Punktabzug. Gravierende Fehler, die die Nutzung des App Stores oder der Apps verhindern, führen jedoch zu Punktabzug. Auch sollte die Menge der Apps und die Ausgestaltung der Apps Release Candidate Status haben.  

  - [**12P**] Aktueller Stand des App Stores:

    - Deployment von Apps auf OpenStack
    - Registrierung und Aktualisierung von Apps 
    - Verwaltung von Apps durch den Administrator

  - [**12P**] Alle App Templates inkl. Deployment auf OpenStack


- Festlegen der abzuschließenden User Stories
- Besprechung der Endabgaben


.. container:: accentuate incremental

    Start der vierten Iteration



Projektabschluss - 25.07.2026 (Ereignis)
-----------------------------------------

- finale Einreichung aller Dokumente über Moodle 


.. container:: accentuate 

    Damit endet das Projekt.
  


Einzureichende Dokumente - pro Team
------------------------------------

.. story::

  .. class:: incremental-list

    - [**20P**] Sourcecode
    - [**2P**] Build-Prozess und CI/CD Pipeline
    - [**8P**] Dokumentation der Architektur

      (Achtung: die Dokumentation **muss explizit gegengezeichnet werden**. D.h. es muss klar ersichtlich sein wer die Dokumentation geschrieben hat und wer sie gegengezeichnet hat (Review); die Person dürfen sich nicht überschneiden.)
    - [**3P**] Dokumentation: Installation und Konfiguration	

      (Achtung: die Dokumentation **muss explizit gegengezeichnet werden**. D.h. es muss klar ersichtlich sein wer die Dokumentation geschrieben hat und wer sie gegengezeichnet hat (Review); die Person darf nicht aus dem Backend Team sein.)
    - [**5P**] Dokumentation: Entwicklung eines neuen App Templates	

      (Achtung: die Dokumentation **muss explizit gegengezeichnet werden**. D.h. es muss klar ersichtlich sein wer die Dokumentation geschrieben hat und wer sie gegengezeichnet hat (Review); die Person darf nicht aus dem entsprechenden Subteam sein.)  
    - [**8P**] QS Maßnahmen - Beleg der Durchführung
    - [**8P**] Video mit Code Walkthrough
    
      :peripheral:`Die Auflösung sollte dabei (1600-1920) x (1080-1200) auf keinen Fall überschreiten. Kodieren Sie das Video in einem gängigen Format (z.B. MP4 mit H.264 Codec) und achten Sie auf eine gute Video- und Audioqualität.`

      - Video: Inhalt	*6P*
      - Video: (technische) Qualität	*2P*


Einzureichende Dokumente - pro Person
--------------------------------------

- [**5P**] Reflexionsbericht: Ausgestaltung analog zur Projektkonzeption; jedoch muss ein ganz klarer Bezug zum Projekt, ihrer Rolle bzw. ihren Rollen im Team und Ihren persönlichen Erkenntnissen für zukünftige Projekte hergestellt werden. Generischer Text (z.B. "Ich habe gelernt, dass es wichtig ist effektiv zu kommunizieren.") führt unmittelbar zu einer Abwertung. Es ist erforderlich konkrete Beispiele aus dem Projekt zu nennen und zu erläutern und ggf. auch zu belegen.