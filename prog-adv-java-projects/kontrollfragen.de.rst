.. meta::
   :lang: de
   :author: Michael Eichberg
   :keywords: "Programmierung", "Java", "Projekte"
   :description lang=de: Kontrollfragen zum Bauen von Java Projekten
   :id: lecture-prog-java-projekte-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!
    
.. include:: ../docutils.defs



Java Projekte
===========================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1


.. class:: new-section transition-scale

Bauen von Java Projekten
-------------------------------------


.. class:: exercises

Kontrollfragen 
-------------------------------------


.. deck::

   .. card:: 
   
      .. exercise:: Nennen Sie Schritte beim Bauen eines Java Projekts.

         .. solution:: 
            :pwd: GanzVieleSindWichtig

            1. Kompilieren der Java-Quelltexte
            2. automatische Code-Formatierung
            3. Ausführen der Tests
            4. Bestimmung der Testabdeckung im Rahmen der Qualitätssicherung
            5. Ausführen von statischen Code-Analysen
            6. Erzeugen des JAR-Files/Paketieren
            7. Erzeugen der Dokumentation
            8. Erzeugen der Webseite
            9. Erzeugen der Installationsdateien
            10. Veröffentlichen des Projekts

   .. card::
   
      .. exercise:: Nennen Sie mind. 2 etablierte Build-Tools für Java Projekte.

         .. solution::
            :pwd: 3-Phasen

            - Apache Ant
            - Apache Maven
            - Gradle


   .. card::

      .. exercise:: Wie sieht die empfohlene Projektstruktur für Java Projekte im Allgemeinen aus?

         .. solution::
            :pwd: /src/main/java

            :Quellcode: src/main/java
            :Testcode: src/test/java
            :Ressourcen: src/main/resources
            :Testressourcen: src/test/resources
            :Konfigurationen und andere Ressourcen: src/main/resources
            :gebaute Artefakte: target 

   .. card::

      .. exercise:: Welches ist ein wesentliches Ziel von Build-management software?

         .. solution::
            :pwd: DasZielIST...

            Ein wesentliches Ziel von Build-Management-Software ist es, die Erstellung und Verwaltung von Projekten zu vereinfachen und **stabile und reproduzierbare Builds zu ermöglichen**.



.. class:: new-section transition-scale

Maven
-------------------------------------


.. class:: exercises
   
Kontrollfragen
-------------------------------------

.. deck:: numbered

   .. card:: 
   
      .. exercise:: Was ist Apache Maven?

         .. solution:: 
            :pwd: MaVen

            Apache Maven ist ein Build-Management-Tool, das auf Konventionen basiert und die Erstellung und Verwaltung von Java-Projekten vereinfacht.

   .. card::

      .. exercise:: Was ist eine Phase und welche Phasen gibt es?

         .. solution::
            :pwd: PhaPhasen

            Eine Phase ist ein Schritt im Lebenszyklus (3 Standardlebenszyklen: clean, default und site) eines Maven-Builds. 

            Beispiele für Phasen sind:
            - validate
            - compile
            - test
            - package
            - deploy

   

