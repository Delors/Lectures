.. meta::
   :lang: de
   :author: Michael Eichberg
   :keywords: "Programmierung", "Java", "Domänenmodellierung", "Kontrollfragen"
   :description lang=de: Kontrollfragen zum Thema Domänenmodellierung in Java
   :id: lecture-prog-java-projekte-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!
    
.. include:: ../docutils.defs



Domänenmodellierung mit Java
===========================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1.1



.. class:: new-section transition-scale

Domänenmodellierung
-------------------------------------



.. class:: exercises

Kontrollfragen 
-------------------------------------


.. deck::

   .. card:: 
   
      .. exercise:: Welchem Zweck dient die Domänenmodellierung?

         .. solution:: 
            :pwd: DieDomäne 

            - Kommunikationsgrundlage (ifizieren)
            - vertieftem Verständnis der Domäne
            - Identifikation der relevanten KonzepteVokabular ident
            - Strukturierung der Konzepte
            - Definition der Beziehungen zwischen den Konzepten
            - Definition der Attribute der Konzepte
            
   .. card::

      .. exercise:: Was soll man als Attribut und was als Klasse modellieren?

         .. solution:: 
            :pwd: AttributOderKlasse

            Die Attribute in einem Domänenmodell sollten vorzugsweise „primitive“ Datentypen in Bezug auf die Domäne sein. Sehr häufige Datentypen sind: Booleans, Datum, Zahl, Zeichen, String, Adresse, Farbe, Telefonnummer,..

   .. card::

      .. exercise:: Warum sollte man Mengen als Klassen modellieren?

         .. solution:: 
            :pwd: EinheitenSindAlles

            Um den Werten Einheiten zuordnen zu können.



.. class:: new-section transition-scale

Java :java:`record`\ s und :java:`enum`\ s
---------------------------------------------



.. class:: exercises

Kontrollfragen 
-------------------------------------

.. deck::

   .. card:: 
   
      .. exercise:: Was ist ein record?
         :formatted-title: Was ist ein :java:`record`?

         .. solution:: 
            :pwd: Rekord

            Ein :java:`record` ist eine spezielle Klasse, die unveränderliche Daten speichert.

      .. exercise:: Von welcher Klassen erben alle Records?
         
         .. solution:: 
            :pwd: RekordErben

            Alle Records erben von :java:`java.lang.Record`.

      .. exercise:: Ist es möglich von Java Records zu erben?

         .. solution:: 
            :pwd: RekordErben

            Nein, es ist nicht möglich von Java Records zu erben. Records sind final.

   .. card::

      .. exercise:: Konstructoren und records
         :formatted-title: Konstruktoren und :java:`record`\ s        

         1. Können records Konstruktoren haben?
         2. Können records mehrere Konstruktoren haben?
         3. Können records Konstruktoren mit Parametern haben?
         4. Können records Konstruktoren ohne Parameter haben?
         5. Was ist der kanonische Konstruktor?

         .. solution:: 
            :pwd: GehtAlles

            .. code:: java
               :number-lines:
               :class: copy-to-clipboard

               // compact canonical constructor:
               record Point(int x, int y) { 
                  public Point(){
                     this(0,0);
                  } 
                  
                  public Point {       
                     if(x < 0) throw new IllegalArgumentException();
                  }
               }

            Der kanonische Konstruktor ist der Konstruktor, der die Parameter der Klasse als Parameter hat und die Werte initialisiert.



   .. card::

      .. exercise:: Wie definiere ich eine Aufzählung?

         .. solution::
            :pwd: Enum

            .. code:: java
               :number-lines:
               :class: copy-to-clipboard

               enum Color {
                  RED, GREEN, BLUE
               }

   .. card::

      .. exercise:: Welchen Vorteil bietet die explizite Definition einer Aufzählung in Java?

         .. solution::
            :pwd: SwitchOverEnum

            In einem :java:`switch` Statement wird ein Compilerfehler erzeugt, wenn nicht alle Fälle abgedeckt sind.
      
   .. card::

      .. exercise:: Können Aufzählungen Methoden und eigene Eigenschaften haben?

         .. solution::
            :pwd: EnumMethoden

            Ja, Aufzählungen können Methoden haben. Insbesondere können Konstanten auch eigene Klassenbodies haben.
