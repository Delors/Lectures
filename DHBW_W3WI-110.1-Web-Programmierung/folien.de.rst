.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "JavaScript", "CSS", "HTML"
    :description lang=de: Web Programmierung
    :id: lecture-w3wi_110.1-web_programmierung
    :first-slide: last-viewed

.. include:: ../docutils.defs



W3WI_110.2 - Web-Entwicklung
================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 23EG/EH

.. supplemental : :
  :Folien:
      [HTML] |html-source|

      [PDF] |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues



Ausgewählte Inhalte gem. MHB
---------------------------------

.. rubric:: Kerninhalte

- HTML, CSS, JavaScript als clientseitige Web-Technologien und aktuelle APIs (z.B. HTML5 und verwandte Technologien)
- Übertragungsprotokolle und APIs zwischen Client und Server (z.B. HTTP, HTTPS, WebSockets, XMLHttpRequest, Fetch API)
- Kommunikation zwischen einzelnen Komponenten Web-basierter Anwendungen
- Optimierung von Webseiten für verschiedene Zielsysteme


.. rubric:: Zusatzinhalte

- Vertiefung von Frameworks
- Dynamische serverseitige Erzeugung von Webseiten


Prüfungsleistung - Portfolio
------------------------------------------

.. admonition::  Hintergrund

    - das Modul hat 55 VL
    - Web-Programmierung hat 33VL und geht mit **70** von 120 Modulpunkten ein


Zwei Bestandteile:

.. class:: list-with-explanations

1. Vorträge - max. 20 Punkte
2. Projekt bzw. Programmieraufgabe - max. 50 Punkte; aufgeteilt:

   1. Projekt (Funktionsumfang, Code, Dokumentation, etc.) - max. 40 Punkte
   2. Abschlusspräsentation - max. 10 Punkte


Ablauf
------------------------------------------

:4.9. bis 2.10.: Vorlesungen
:9.10. und 16.10.: Vorträge
:30.10.: Präsentation der Programmieraufgabe
:15.11.: Abgabe der Programmieraufgabe

Alle Abgaben über Moodle. Bitte beachten Sie die Anforderungen an die Abgaben.


Vorträge - Rahmenbedingungen
------------------------------------------

.. class:: incremental

- Die Präsentationen sollen insbesondere die Kerninhalte der Vorlesung aufgreifen und entsprechend vertiefen bzw. einbetten.
- Die Präsentationen sind am Abend vor dem ausgemachten Termin hochzuladen in Moodle.
- Vortragsdauer pro Person: 10 Minuten.
- Jeder hält genau einen Vortrag - ggf. im Team.


Themen
---------

.. deck:: dd-margin-left-4em

  .. card::

    :Barrierefreiheit und HTML:

      (max. 2 Personen)

      - Allgemeine Einführung in das Thema Barrierefreiheit (:eng:`Accessibilty`)
      - Barrierefreie HTML Webseiten mit HTML5 Gestalten
      - Erste Quellen:

        .. class:: far-smaller

        - https://developer.mozilla.org/en-US/docs/Learn/Accessibility
        - https://developer.mozilla.org/en-US/docs/Web/Accessibility/ARIA
        - https://web.dev/learn/accessibility
        - https://www.w3.org/TR/wai-aria/

  .. card::

    :HTML Forms:

      (max. 2 Personen)

      - Formulare mit HTML; insbesondere Input Typen und Validierung von Daten
      - Erste Quellen:

        .. class:: far-smaller

        - https://developer.mozilla.org/en-US/docs/Learn/Forms
        - https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form
        - https://web.dev/learn/forms

      *CSS und JavaScript soll nicht behandelt werden*.


  .. card::

    :SVG - Vektorgrafik auf Webseiten:

      (max. 1 Person)


      - Einführung in SVG und Vektorgrafiken
      - HTML5 und eingebettete SVGs
      - Erste Quellen:

        .. class:: far-smaller

        - https://developer.mozilla.org/en-US/docs/Web/SVG
        - https://svg-tutorial.com
        - https://www.w3schools.com/graphics/svg_intro.asp

  .. card::

    :Saas:

      (max. 1 Person)

      - CSS Erweiterung (CSS Preprocessor)
      - Erste Quellen:

        .. class:: far-smaller

        - https://sass-lang.com/


  .. card::

    :Less:

      (max. 1 Person)

      - CSS Preprocessor
      - Erste Quellen:

        .. class:: far-smaller

        - https://lesscss.org/

  .. card::

    :Bootstrap:

      (max. 2 Personen)

      - Entwicklung von *responsive* Webseiten
      - Erste Quellen:

        .. class:: far-smaller

        - https://getbootstrap.com/

      Achtung: Sass (und Less) werden getrennt behandelt werden.

  .. card::

    :Express:

      (4 Personen)

      - Einführung in Express
      - *Programmierung von Endpunkten*

      **Hinweise**

      - Grundlagen des RESTful Designs brauchen nicht behandelt werden; konzentrieren Sie sich darauf, wie man Express verwendet (\ *Hands-on*\ )
      - Die Programmierung mit WebSockets nur in soweit behandeln, wie es nicht bereits in einem anderen Vortrag/der Vorlesung behandelt wird.
      - Erste Quellen:

        .. class:: far-smaller

        - https://expressjs.com/
        - https://developer.mozilla.org/en-US/docs/Web/API/WebSockets_API
        - https://www.npmjs.com/package/express-ws?activeTab=readme

  .. card::

    :Vue.js:

      (max. 3 Personen)


      - JavaScript Framework für Web User Interfaces
      - Erste Quellen:

        .. class:: far-smaller

        - https://vuejs.org/

  .. card::

    :Typescript:

      (max. 2 Personen)

      - JavaScript with types

        (Der Vortrag sollte sich auf die Vorteile von statischer Typisierung konzentrieren; insbesondere für das :eng:`Programming in the large`.)
      - Erste Quellen:

        .. class:: far-smaller

        - https://www.typescriptlang.org/

  .. card::

    :Angular:

      (max. 3 Personen)

      - Web-Application Framework
      - Erste Quellen:

        .. class:: far-smaller

        - https://angular.io/


  .. card::

    :Svelte: (max. 3 Personen)


      - Javascript Web Framework
      - Erste Quellen:

        .. class:: far-smaller

        - https://svelte.dev/

  .. card::

    :React:

      (max. 4 Personen)

      - Frontend orientierte Bibliothek für die Entwicklung von Benutzeroberflächen
      - Erste Quellen:

        .. class:: far-smaller

        - https://react.dev/
