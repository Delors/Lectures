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
:Version: WWI-23SEB



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
    - ...



.. class:: transition-move-to-top

Anforderungen bzw. Herausforderungen
----------------------------------------

.. story::

    .. rubric:: Funktionale Anforderungen an den AppStore

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

    .. class:: incremental

    .. rubric:: Nicht-funktionale Anforderungen

    .. class:: incremental-list list-with-explanations

        - Der App-Store ist eine eigenständige Webanwendung, der die öffentlichen Schnittstellen von OpenStack verwendet

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

        Die oben genannten Anforderungen stellen nur den groben Rahmen dar.



.. class:: transition-move-to-top

Möglicherweise relevante Technologien
----------------------------------------

Sowohl für die Entwicklung des App Stores, als auch der Anwendungen (ggf. als Inspiration):

.. note::

    Abgesehen von OpenStack als Deploymentziel sind Sie frei in der Wahl der Technologien.

.. story::

    .. class:: incremental-list

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

- **Projektkonzeption**

  **Kleingruppenpräsentationen (und Dokumentation) mit insgesamt 50 Punkten.**

  Aus jeder Gruppe muss jeder in etwa gleich lang präsentieren.

.. class:: incremental

- **Projektrealisierung**

  Gegen Ende dieses Semesters Zwischenpräsentation (20% der Projektnote).

  Am Ende des nächsten Semesters ist das Produkt als solches vorzuführen und abzugeben (60% der Projektnote). Weiterhin ist die Dokumentation (10%) abzugeben und eine Webseite und ein Produktvideo (10% der Projektnote) zu erstellen.



.. class:: new-section

Projektkonzeption
--------------------------



1. Projektkonzeption - 19.11.2025
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

1. Projektkonzeption - 24.11.2025 [Optional - Online]
-------------------------------------------------------------------------------------------------

:BBB: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd

Je Gruppe:

- Vertraut machen mit dem Technologiestack
- **Prototyping / Entwicklung eines Spike**
- Ausarbeitung der initialen Präsentation\ [#]_

.. [#] `(WIP) Vorträge: Hinweise und Bewertungskriterien <https://delors.github.io/allg-vortraege/folien.de.rst.html?ld-slide-no=1>`__



.. class:: fade-out

3. Projektkonzeption - 01.12.2025
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



4. Projektkonzeption - 08.12.2025 [Optional - Online]
-------------------------------------------------------------------------------------------------

:BBB: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd

Je Gruppe:

- Schätzung der User Stories
- Ausarbeitung von Akzeptanzkriterien für die User Stories
- Verfeinerung der initialen Architektur
- Ausarbeitung der Abschlusspräsentation



5. Projektkonzeption - 15.12.2025
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



.. class:: new-section transition-fade

Projektrealisierung
-----------------------


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

:BBB: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd

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

:BBB: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd

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

  Online: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd

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
