.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "Testen", "Abdeckung", Metriken
    :description lang=de: Software Engineering - Testen und Metriken 
    :id: lecture-se-testen-und-metriken
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. |html-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html
.. |pdf-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html.pdf
.. |at| unicode:: 0x40

.. role:: incremental   
.. role:: eng
.. role:: ger
.. role:: red
.. role:: peripheral
.. role:: obsolete    
.. role:: java(code)
    :language: java
.. role:: bash(code)
    :language: bash
.. role:: sh(code)
    :language: sh
.. role:: console(code)
    :language: console

.. role:: raw-html(raw)
   :format: html


Software Engineering - Projekte bauen, Testen und Bewerten
===================================================================

Eine allererste Einführung 

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.0

.. container:: footer-left tiny
    
    Die Folien basieren in Teilen auf Folien von Dr. Helm und Prof. Dr. Hermann.
    
    Alle Fehler sind meine eigenen.

.. supplemental::

  :Folien: 
      [HTML] |html-source|

      [PDF] |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues


.. class:: new-section transition-scale

Buildsysteme
------------  


Buildsysteme (hier: sbt)
--------------------------- 


.. class:: incremental-list list-with-explanations

- Buildsysteme automatisieren repetitive Aufgaben
- Sie haben eingebaute Unterstützung oder Plugins für häufige Aufgaben:

  .. class:: incremental-list 

  • Code zur einer ausführbaren Datei kompilieren: :console:`sbt compile`
  • Tests ausführen: :console:`sbt test`
  • Kompilierte Dateien entfernen: :console:`sbt clean`
  • Start eines interaktiven Interpreters: :console:`sbt console`
  • Code-Dokumentation als HTML oder PDF ausgeben: :console:`sbt doc`
  • Code formatieren, Code-Stil prüfen: :console:`sbt scalafmt`, :console:`sbt scalastyle`
  • ...
  • Ausführbare Datei (auf Server) publizieren: :console:`sbt publish`

- Buildsysteme werden mit Buildskripten konfiguriert
- Buildsysteme verarbeiten meist nur geänderte und davon abhängige Dateien 

  (Inkrementalität)


.. supplemental::

    Heutzutage bringen fast alle Programmiersprachen eigene Buildsysteme mit oder es gibt etablierte Buildsysteme.

    :C/C++: **Make**, **CMake**
    :Java: **Maven**, Gradle, :obsolete:`Ant`, sbt
    :Scala: **sbt**
    :Rust: **Cargo**
    :...: ...



Minimale Anforderungen an ein Buildskript für Softwareprojekte
----------------------------------------------------------------

.. class:: incremental-list

1. **Kompilieren** des Codes nach einem frischen Update (:red:`bei Fehler Abbruch`)
2. **Testen** des Codes:

   .. class:: incremental-list

   1. **Unit-Tests** (:red:`bei Fehler Abbruch`)
   2. **Integrationstests** (:red:`bei Fehler Abbruch`)
   3. Systemtests/Abnahmetests (:red:`bei Fehler Abbruch`)
3. **Packaging** des Projekts (:red:`bei Fehler Abbruch`)
4. **Deployment** (typischerweise in einer Testumgebung)



Buildskripte für größere Softwareprojekte
----------------------------------------------------------------

Insbesondere bei größeren Projekten kommen häufig noch viele weitere Schritte hinzu:

- Code-Dokumentation erzeugen und veröffentlichen
- verschiedene statische Analysen durchführen, um Fehler zu finden
- zahlreiche Skripte um zum Beispiel Datenbanken zu aktualisieren, Docker-Container zu bauen und zu starten...
- ...

