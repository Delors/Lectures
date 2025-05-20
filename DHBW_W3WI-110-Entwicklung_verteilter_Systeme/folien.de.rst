.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "Web-Engineering", "Verteilte Systeme"
    :description lang=de: Web Programmierung
    :id: lecture-w3wi_110-entwicklung_verteilter_systeme
    :first-slide: last-viewed

.. include:: ../docutils.defs



W3WI_110 - Entwicklung verteilter Systeme
================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 23SEB (SoSe 2025 - 1. Rev.)

.. supplemental : :
  :Folien:
      [HTML] |html-source|

      [PDF] |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues



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

- das Modul hat 55 VL
- Verteilte Systeme hat 22VL, Web-Programmierung hat 33 VL

Zwei Bestandteile:

.. class:: list-with-explanations

1. Vorträge
2. Projekt bzw. Programmieraufgabe in Teams von 4 Personen

   1. Projekt (Funktionsumfang, Code, Dokumentation, etc.)
   2. Abschlusspräsentation



Projekt/Programmieraufgabe
------------------------------------------

Entwicklung von Kollaborationstools als wiederverwendbare Webkomponenten.

.. story::

    .. compound::
        :class: incremental

        Beispiele:

        - ein einfacher Chat
        - ein einfaches interaktives Whiteboard
        - ein Tool für ad-hoc Abstimmungen (zum Beispiel: wer hat die Hausaufgaben gemacht?)
        - ein Tool für Umfragen
        - ein Tool für Brainstorming/Mindmaps
        - ein Tool für einfache Teameinteilungen
        - ein Tool für die Erstellung von WordClouds
        - ...

    .. compound::
        :class: incremental

        Idee ist immer, dass die Tools ohne explizite Anmeldung funktionieren. Es gibt keine Benutzerverwaltung. Die Anmeldung erfolgt über einen Link, der den Teilnehmern - zum Beispiel im Rahmen einer Vorlesung - zur Verfügung gestellt wird.

    .. compound::
        :class: incremental

        Es gibt eine *Adminoberfläche*, die es Admins - für diese Nutzer gibt es eine rudimentäre Verwaltung - ermöglicht neue Räume zu erzeugen und die Teilnehmer zu verwalten. Der Admin kann IP Adressen (temporär) sperren.



Bewertungskriterien für das Projekt
------------------------------------------------

- Funktionsumfang
- Dokumentation (Entwickler und Benutzer)
- Qualität des Codes (HTML, CSS und JavaScript)
- Qualität des Buildprozesses



Ablauf - W3WI-110 - Entwicklung verteilter Systeme 23SEB
---------------------------------------------------------

.. story::

    .. class:: incremental-list list-with-explanations

    - Scheduled: 16. May 2025 at 13:15 to 17:30, CEST
    - Scheduled: 19. May 2025 at 13:15 to 17:30, CEST
    - Scheduled: 22. May 2025 at 13:15 to 17:30, CEST
    - Scheduled: 6. Jun 2025 at 13:15 to 17:30, CEST

      Kurzpräsentation der Projekte (kein Code - Powerpoint ist ausreichend; 5 Minuten pro Team). Kein unmittelbare Bewertung - dient "lediglich" zur Steuerung.

    - Scheduled: 13. Jun 2025 at 13:15 to 17:30, CEST
    - Scheduled: 16. Jun 2025 at 13:15 to 17:30, CEST
    - Scheduled: 24. Jun 2025 at 13:15 to 17:30, CEST
    - Scheduled: 30. Jun 2025 at 13:15 to 17:30, CEST
    - Scheduled: 4. Jul 2025 at 13:15 to 17:30, CEST

      Gegenseitige Code Reviews und Präsentation der Ergebnisse der Code-Reviews.

      (Jedes Team führt ein Review durch und wird einmal reviewed. Danach erstellen alle Teams eine Präsentation, die sie in der Gruppe vorstellen. Die Präsentation ist 10 Minuten lang und wird bewertet. Danach erstellt jedes Team einen Bericht, der die Ergebnisse des Reviews zusammenfasst. Der Bericht wird bewertet.)

    - Scheduled: 7. Jul 2025 at 13:15 to 17:30, CEST

      Gruppenindividuelle Betreuung bei Fragen und Problemen bzgl. des Projekts.

    - Scheduled: 11. Jul 2025 at 13:15 to 17:30, CEST

      Abschlusspräsentationen und Vorführung der Projekte.

      Die Projektabgabe ist am 9.7.2025 um 23:59 Uhr.
