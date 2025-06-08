.. meta::
    :version: renaissance 
    :author: Michael Eichberg
    :keywords: "Fortgeschrittene Systementwicklung", "verteilte Systeme"
    :description lang=de: Einführung in die Entwicklung verteilter Systeme
    :id: lecture-w3wi_am302-fortgeschrittene_systementwicklung
    :first-slide: last-viewed

.. include:: ../docutils.defs
  


W3WI_AM302 - Fortgeschrittene Systementwicklung
================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 23AMA (Rev. 1)



.. class:: fade-out

Inhalte 1. Semester
---------------------------------
  
- Cybersecurity
- klassische Sicherheitsprinzipien
- Schwachstellen: von CVSS bis CWE

.. container:: peripheral

  - voraussichtlich im 6. Semester: 
    
    IT-Security Grundlagen: Verschlüsselung, Hashfunktionen, Signaturen, Zertifikate, etc.



Inhalte 2. Semester
---------------------------------

**Ziel: Stärkung und Weiterentwicklung Ihrer Fähigkeiten in der Softwareentwicklung.**  

0. Versionsverwaltung
1. Buildprozesse und Continuous Integration / Continuous Deployment
2. Crashkurs CSS (Responsive Design)
3. Crashkrus JavaScript (Wiederholung)
4. Authentifizierung in Webanwendungen (JSON Web Token, Cookies, Sessions)
5. automatisiertes Finden von Schwachstellen in der Softwareentwicklung.
6. Virtualisierung und Containerisierung (optional)



Prüfungsleistung 
------------------------------------------

Prüfungsleistung: Portfolio (insgesamt 120 Punkte)

:1. Semester: Kurztest mit 20 Minuten mit 20 Punkten
:2. Semester: Vorträge (pro Person 15 Minuten), Ausarbeitungen/Projekt mit 100 Punkten



Ablauf
--------

.. admonition::  Hintergrund

    - Modul: 55 VL
    - Modul 5 ECTS

.. deck::

  .. card:: 
    :class: fade-out

    1. Semester 12 VL:

    - \14. Oct 2024: 13:45 bis 17:00 
    - \23. Oct 2024: 13:45 bis 17:00 
    - \28. Oct 2024: 13:45 bis 17:00 
    
    Davon 20Minuten Kurztest von 16:40 bis 17:00 Uhr; davor 10 Minuten Pause.

  .. card:: 
  
    2. Semester: 43 VL

    - .. class:: fade-out
      
      \19. Feb 2025 von 13:45 bis 17:00
    - \25. Feb 2025 von 09:30 bis 13:00
    - \7. Mar 2025 von 13:30 bis 17:00
    - \18. Mar 2025 von 09:30 bis 13:00
    - \1. Apr 2025 von 09:45 bis 13:00
    - \14. Apr 2025 von 13:45 bis 17:00
    - \16. Apr 2025 von 09:30 bis 13:00
    - \22. Apr 2025 von 09:00 bis 12:15



Aufgabenstellung
---------------------------------

Entwicklung eines browser-basierten Multiplayer Spiels.

.. class:: incremental-list

- 3. Spiele stehen zur Auswahl:

  - „*Snake-Variante*“ als Teamspiel 
  - „*Scrabble-Variante*“ als Teamspiel
  - „*Kahoot-Variante*“ als Teamspiel

  Es handelt sich hierbei nur um Vorschläge. Jedem Team steht es frei die Originalkonzepte weitgehend zu modifizieren und dies ist auch gewünscht! Wenn Sie ein ganz anderes Spiel entwickeln wollen, dann sprechen Sie mich bitte bis Ende der Woche an.

- Die Spiele sollen durch 3 Teams entwickelt werden, die sich jeweils auf ein Spiel festlegen und jeweils in ein Frontend und Backend Team möglichst gleicher Größe aufgliedern. Ein Wechsel zwischen den (Sub-)Teams ist nicht vorgesehen. 

- Der empfohlene Technologie-Stack ist: 

  - Frontend: JavaScript/Typescript
  - Backend: Node.js/Express.js oder Java/Spring Boot

  Eine Abweichung ist möglich.


  

Detailplanung
---------------------------------

.. story::

  .. compound:: 
    
    .. rubric:: 19. Feb 2025 von 13:45 bis 17:00
    
    - Gruppenbildung
    - Vorstellung der Anforderungen an das Projekt (Benotungsgrundlage)
    - Besprechung der Themen: 
    
      1. Versionsverwaltung
      2. Bauen, Testen und Bewerten von Software

  .. compound:: 
    :class: incremental

    .. rubric:: 25. Feb 2025 von 09:30 bis 13:00

    (insg. 6 Vortragende)

    - Besprechung der Themen: 
    
      1. RESTful Web Services
      2. Crashkurs JavaScript - Teil 1

    - **Je Gruppe** 

      1 Person vom Frontend-Team stellt das Spielkonzept vor und zeigt Mockups (Wireframes, handgezeichnet, ...) der UI - dies bezieht sich auch auf den Login-Prozess und ggf. Admin Funktionen.

      1 Person aus dem Backend-Team stellt die Iterationsplanung vor und wie die Teams auf sozialer und technischer Ebene zusammenarbeiten wollen. 
      
      Die konzeptionelle Interaktion zwischen Frontend und Backend soll dabei mittels UML (Sequenzdiagrammen) oder einem anderen geeigneten Modell dargestellt werden.

  .. compound:: 
    :class: incremental

    .. rubric:: 7. Mar 2025 von 13:30 bis 17:00 

    (insg. 6 Vortragende)

    - Besprechung der Themen: 
    
      1. Crashkurs JavaScript - Teil 2
      2. CSS

    - **Je Teilgruppe**
    
    Vorstellung des Projektsetups in Hinblick auf die Sicherstellung der Codequalität und der Kollaboration. D. h. Buildskripte und Testskripte sind zu präsentieren und vorzuführen.

  .. compound:: 
    :class: incremental
    
    .. rubric:: 18. Mar 2025 von 09:30 bis 13:00

    (insg. 6 Vortragende)

    **Je Teilgruppe**
    
    Vorstellung der Projektstruktur und durchführen eines Codewalkthroughs. D. h. wie ist der Code strukturiert und wie ist der Code modularisiert.

    Darstellung des Mockclients bzw. Mockservers. Insbesondere Darstellung der Grenzen des Mockservers/-clients.

    **Je Gruppe**
    Demonstration des Projektes in Relation zur Iterationsplanung.

  .. compound:: 
    :class: incremental

    .. rubric:: 1. Apr 2025 von 09:45 bis 13:00

    (insg. 6 Vortragende)    

    **Je Gruppe**

    1 Person Frontend Darstellung wie ein Responsive Design umgesetzt wurde.

    1 Person Backend Darstellung wie Informationen zwischen den Clients ausgetauscht werden; d. h. wann werden welche Daten wohin geschickt?

    Demonstration des Projektes in Relation zur Iterationsplanung.

  .. compound:: 
    :class: incremental

    .. rubric:: 14. Apr 2025 von 13:45 bis 17:00

    (insg. 6 Vortragende)

    **Je Gruppe**

    **Je Teilgruppe**

    1 Person Umsetzung des Sicherheitskonzepts der Webanwendung; d. h. wie werden die Nutzer authentifiziert; wie erfolgt die Umsetzung der Autorisierung in der Anwendung.

    **Je Gruppe**
    Demonstration des Projektes in Relation zur Iterationsplanung.


  .. compound:: 
    :class: incremental

    .. rubric:: 16. Apr 2025 von 09:30 bis 13:00

    Online: https://bbb.dhbw.de/mannheim/eic-uue-f52-6xb

    (Im Wesentlichen dient der Zeitslot der Fertigstellung der Software und der Vorbereitung auf die Abschlusspräsentation. Sollten Fragen auftauchen, dann bin ich Online erreichbar.)

  .. compound:: 
    :class: incremental

    .. rubric:: 22. Apr 2025 von 09:00 bis 12:15 

    (insg. 3 Vortragende)

    **Je Gruppe**

    - Präsentation der Projekte/des finalen Spieldesigns 
    - Anleiten der Spiele

Abgaben
---------------------------------

- das Video (bitte nicht über Moodle!)
- das GIT Repository (--depth 1)
- ein Dokument aus dem hervorgeht welches Gruppenmitglied an welchen Abgaben wie mitgearbeitet hat. 

  (Z. B. „Lara hat den Message-Broker implementiert“; „Tim hat das Testkonzept und die Testumgebung aufgesetzt“.)

- eine von allen Gruppenmitgliedern persönlich unterschriebene ehrenwörtliche Erklärung mit folgendem Wortlaut ist ebenfalls mit einzureichen:

  .. epigraph::
  
    Hiermit erklären wir ehrenwörtlich, dass wir die vorliegende Portfolio-Arbeit zur Vorlesung Fortgeschrittene Systementwicklung bestehend aus Ausarbeitung, Programmcode und Video selbstständig verfasst und keine anderen als die angegebenen Quellen und Hilfsmittel benutzt haben.

    -- Datum, Unterschriften

Benotungsgrundlage
---------------------------------

.. scrollable::
    
  - [**max. 20 Punkte**] Qualität des Vortrags (50% Individualnote und 50% gehen anteilig auf das Teamkonto.)

    D. h. Alle Punkte, die auf dem Teamkonto für Voträge landen, werden dann addiert und durch die Anzahl der Teammitglieder geteilt. Dabei wird zwischem dem Frontend- und Backend-Team unterschieden. 

    .. example::
      
      Es gab zwei Vorträge mit :math:`18` und :math:`14` Punkten. Dann hat das Team :math:`9+7` Punkte für die Vorträge erhalten. Der erste Vortragende hat dann auf den Vortragsteil :math:`9+ \frac{(9+7)}{2 (\text{\# Vorträge})} = 17` Punkte erhalten. Der zweite Vortragende hat :math:`7+ \frac{(9+7)}{ 2 (\text{\# Vorträge})} = 15` Punkte erhalten.  

  - [**max. 10 Punkte**] Ist das Spiel lauffähig?

    Lauffähig bedeutet hier, dass das Spiel auf einem Server läuft und von mehreren Spielern gespielt werden kann. Weiterhin muss es möglich sein ohne Serverneustart mehrere Runden hintereinander zu spielen.

  - [**max. 10 Punkte**] Qualität des Projektsetups (Buildskripte, Testskripte, Versionsverwaltung, Mockserver/-client) 

    Die Dokumentation, die beschreibt wie das Projekt kompiliert und deployed wird, ist ein wichtiger Bestandteil dieser Bewertung.

  - [**max. 10 Punkte**] für den Mockclients bzw. Mockservers 

  - [**max. 10 Punkte**] für die Umsetzung des Sicherheitskonzepts

    Hierbei geht es insbesondere um die Umsetzung der Authentifizierung und Autorisierung.

  - [**max. 25 Punkte**] Qualität der Sourcecodes

    In Hinblick auf: keine Redundanzen/Duplikationen, Modularisierung, Wartbarkeit, kompakt, *gut* dokumentiert, keine unnötigen Abhängigkeiten, keine unnötigen Dateien, keine unnötigen Kommentare.

  - [**max. 15 Punkte**] Ein Video (ca. 10-15 Minuten), das über den Code geht und die Projektstruktur sowie die wichtigsten Codebestandteile erklärt. Das Video sollte max. 15 Minuten lang sein und auf einem Notebook abspielbar bzw. ansehbar sein.

    max. 10 Punkte für den Inhalt - max. 5 Punkte für die "optische Gestaltung" des Videos.



Allgemeine Anforderungen
---------------------------------

- Jede Präsentation wird auch als Teamleistung betrachtet und für jede Präsentation muss es einen Backup-Präsentierenden geben, der Backup soll eine Person sein, die bisher noch nicht vorgetragen hat. (Außer beim letzten Vortrag...)

.. attention::

  Um sicherzustellen, dass alle Teams das Projekt durchführen können, entwickelt jedes Teilteam ein Mockserver bzw. Mockclient, der die Interaktion mit dem anderen Team simuliert. 
  
  Dieser Mockserver bzw. Mockclient ist Teil der Abgabe und wird im Bereich Qualität des Projektsetups benotet.



Zur Verfügung gestellt wird
---------------------------------

- Zugriff auf einem Server, der aus dem UNI Netz (ggf. mittels VPN) erreichbar ist.

  :Server: 141.72.12.132
  :Nutzer: wwi23ama1, wwi23ama2, wwi23ama3
  :Passwort: wird im Kurs bekannt gegeben 



