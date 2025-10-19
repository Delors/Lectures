.. meta::
   :lang: de
   :author: Michael Eichberg
   :keywords: "Programmierung"
   :description lang=de: Kontrollfragen zu Einführung in die Programmierung
   :id: lecture-prog-einfuehrung-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Kontrollfragen: Einführung in die Programmierung
===========================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0



.. class:: new-section
   
Grundlagen
------------



.. class:: exercises

Kontrollfragen - Grundlagen
----------------------------

.. deck:: 

   .. card::

      .. exercise:: Was ist ein Algorithmus?

         .. solution::
            :pwd: ... Vorschrift

            Eine eindeutige, ausführbare Handlungsvorschrift zur Lösung eines Problems in endlich vielen Schritten.

   .. card::

      .. exercise:: Was ist die Komplexität eines Algorithmus?

         .. solution::
            :pwd: kompleXitaet

            Die Komplexität eines Algorithmus beschreibt den Ressourcenverbrauch (insbesondere Zeit und Speicher) in Abhängigkeit von der Eingabe.

   .. card::

      .. exercise:: Nennen Sie Probleme von natürlichen Sprachen, die formale Sprachen nicht haben?

         .. solution::
            :pwd: da_gibt_es_mehrere

            Natürliche Sprachen haben z. B. folgende Probleme:
            
            - Kontextabhängigkeit

              .. example:: 
               
                  „Die Bank ist am Fluss.“

                  (Ist hier eine Geld- oder eine Uferbank gemeint?)

            - Mehrdeutigkeit 
             
              .. example:: 
               
                  „Ich sehe den Mann mit dem Fernglas.“

                  (Wer sieht hier wen; d. h. wer hat das Fernglas?)
            - Vagheit

              .. example:: 
               
                  „Der Mann sieht gut aus.“
            
                  (Was bedeutet hier „gut aus“?)

            - Unvollständigkeit

               .. example:: 
                
                     „Kommst du?“
   
                     (*Kommst Du* jetzt mit mir oder *kommst Du* auch zu der Party?)
            
   .. card::

      .. exercise:: Was zeichnet ein Skript aus?

         .. solution::
            :pwd: skript

            Ein Skript ist ein Programm, das in einer Skriptsprache geschrieben ist. Skripte werden typischerweise interpretiert und nicht kompiliert. Sie sind oft für die Automatisierung von Aufgaben gedacht und können schnell geschrieben und geändert werden.

      .. exercise:: Nennen Sie zwei Beispiele für Skriptsprachen. Kann Java als Skriptsprache verwendet werden?

         .. solution::
            :pwd: drei_skriptsprachen

            Beispiele für Skriptsprachen sind:

            - Python
            - JavaScript
            - Ruby
            - Perl
            - Bash

            Java kann seit einiger Zeit auch als Skriptsprache verwendet werden (JEP 330: Launch Single-File Source-Code Programs und JEP 458: Launch Multi-File Source-Code Programs).



.. class:: new-section
   
Extended Backus-Naur Form (EBNF)
---------------------------------



.. class:: exercises

Kontrollfragen - EBNF
----------------------------

.. deck:: 

   .. card::

      .. exercise:: EBNF verwenden

         .. code:: ebnf
            :number-lines:

            Datei          = { Eintrag } ;
            Eintrag        = SchluesselWert ;
            SchluesselWert = Schlüssel , ":" , Wert , ";" ;
            Schlüssel      = Buchstaben , { Buchstaben | Ziffern | "_" } ;
            Wert           = Zahl | String ;
            Zahl           = Ziffern , { Ziffern } ;
            String         = '"' , { Zeichen } , '"' ;
            Buchstaben     = "a" | "b" | "c" | "d" | "e" | "f" | "g" | "h" | "i" | "j" | "k" | "l" |
                             "m" | "n" | "o" | "p" | "q" | "r" | "s" | "t" | "u" | "v" | "w" | "x" |
                             "y" | "z" | "A" | "B" | "C" | "D" | "E" | "F" | "G" | "H" | "I" | "J" |
                             "K" | "L" | "M" | "N" | "O" | "P" | "Q" | "R" | "S" | "T" | "U" | "V" |
                             "W" | "X" | "Y" | "Z" ;
            Ziffern        = "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" ;
            Zeichen        = Buchstaben | Ziffern | " " | "-" | "_" | "." ;

         Bilden Sie konkrete Beispiele für eine Datei, die dieser EBNF-Spezifikation entspricht.
         Ist der folgende Eintrag gültig: ``name: Java-Kurs; version: 1.1;`` ?

         .. solution::
            :pwd: ebnf_beispiel

            Beispiel für eine Datei, die der obigen EBNF-Spezifikation entspricht:

            .. code:: yaml
            
               name: "BeispielDatei";
               version: 1;

            Nein, der Eintrag ``name: Java-Kurs; version: 1.1;`` ist nicht gültig, da der Wert für "name" kein String ist (fehlende Anführungszeichen) und der Wert für "version" eine Dezimalzahl ist, die in der EBNF-Spezifikation nicht definiert ist (nur ganze Zahlen sind erlaubt).