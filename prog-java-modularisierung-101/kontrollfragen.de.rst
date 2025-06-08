.. meta::
   :version: genesis
   :lang: de
   :author: Michael Eichberg
   :keywords: "Programmierung", "Java", "Arrays"
   :description lang=de: Kontrollfragen zu Java Arrays
   :id: lecture-prog-java-arrays-kontrollfragen
   :first-slide: last-viewed
   :exercises-master-password: WirklichSchwierig!
    
.. include:: ../docutils.defs



Kontrollfragen zu Klassen, Packages und Imports
=================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0



Kontrollfragen
----------------

.. container:: scrollable

   .. class:: incremental long-list

   1. \
   
      .. exercise:: Welche Ziele werden bei der Modularisierung von Code verfolgt?

         .. solution::
            :pwd: 4 Ziele

            Wiederverwendbarkeit, Kollaborative Entwicklung, Erweiterbarkeit, Wartbarkeit/Testbarkeit.

   2. 

      .. exercise:: Sichtbarkeiten

         Welche Sichtbarkeiten gibt es in Java und wie sind diese angeordnet?  

         .. solution::
            :pwd: 4 Sichtbarkeiten

            :java:`public`, :java:`protected`, *<package-private>*, :java:`private`


   3. 

      .. exercise:: Finden Sie den/die Fehler:
      
         .. code:: java
            :class: far-far-smaller

            package math.dhbw.de;
            public class arrays {
               int median(int... numbers ) { ... }
               double average(int... numbers) { ... }
            }

            // in einer anderen Datei in einem anderen Verzeichnis
            void main() {
               println(math.dhbw.de.Arrays.median(1,2,3,4,5));
            }

         .. solution::
            :pwd: 3 Ebenen

            - Die Methoden sind nicht statisch und nicht öffentlich. 
            - Die Klasse `Arrays` sollte groß geschrieben werden.
            - der Packaqename ist nicht in umgekehrter Reihenfolge des Domainnamens.

   4. 

      .. exercise:: Imports 
      
         .. code:: java
            :class: far-far-smaller

            package programmierung;

            public class TwoDimensionalArrays {
               public static void clean(int[][] a) { ... }
            }
         
         Welche Möglichkeiten kennen Sie, um die Methode `clean` in einer anderen Klasse zu verwenden?

         .. solution::
            :pwd: 3 Möglichkeiten

            1. `import programmierung.TwoDimensionalArrays;` // dann `TwoDimensionalArrays.clean(a);`
            2. `import programmierung.*;` // dann `TwoDimensionalArrays.clean(a);`
            3. `import static programmierung.TwoDimensionalArrays.clean;` // dann `clean(a);`
            4. `import static programmierung.TwoDimensionalArrays.*;` // dann `clean(a);`
            5. `programmierung.TwoDimensionalArrays.clean(a);`
   
   

   5.

      .. exercise:: Identifizieren Sie Fehler in der folgenden Projektkonfiguration:

         .. image:: images/fehlerhafte_projekt_konfiguration.png
            :height: 750px
            :alt: Fehlerhafte Projektkonfiguration
            :align: center
         
         .. container:: text-align-center far-far-smaller minor

            ZED-Editor


         .. solution::
            :pwd: mehrere

            1. Packagename und Pfad von Math stimmen nicht überein.
            2. Die Klasse `Math` ist nicht öffentlich.