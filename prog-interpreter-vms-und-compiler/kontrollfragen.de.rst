.. meta::
   :version: renaissance
   :lang: de
   :author: Michael Eichberg
   :keywords: "Programmierung", "Java", "VM", "Compiler", "Bytecode"
   :description lang=de: Kontrollfragen zu VMs und Compiler
   :id: lecture-prog-interpreter-vms-und-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!
    
.. |html-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html
.. |pdf-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html.pdf
.. |at| unicode:: 0x40
.. |qm| unicode:: 0x22 

.. role:: incremental
.. role:: eng
.. role:: ger
.. role:: dhbw-red
.. role:: green
.. role:: the-blue
.. role:: obsolete
.. role:: monospaced
.. role:: copy-to-clipboard
.. role:: kbd
.. role:: java(code)
   :language: java



Interpreter, VMs und Compiler - Wiederholung
===========================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1


.. class:: new-section transition-scale

VMs, Interpreter und Compiler
-------------------------------------


.. class:: exercises

Kontrollfragen 
-------------------------------------


.. deck:: numbered

   .. card:: 
   
      .. exercise:: Was ist ein Interpreter? Welche Vor-/Nachteile haben diese gegenüber anderen Formen der Programmausführung?

         .. solution::
            :pwd: interpreter

            Ein Interpreter ist ein Programm, das Quellcode direkt ausführt. Vorteile: Einfachheit, Fehlermeldungen sind oft besser. Nachteile: Langsam, da der Code nicht optimiert wird.

   .. card::

      .. exercise:: Was ist ein Compiler und welche Phasen umfasst die Übersetzung eines Programms?

         .. solution::
            :pwd: 4-Phasen

            1. Lexikalische Analyse
            2. Syntaxanalyse
            3. Semantische Analyse
            4. Codegenerierung
   

      
   .. card::

      .. exercise:: Was ist ein VM und wie unterscheidet sich eine VM von einem Interpreter?

         .. solution::
            :pwd: vmsVSInterpreter

            Eine VM ist eine virtuelle Maschine (simuliert einen Prozessor), die Bytecode (kein Sourcecode!) ausführt. Ein Interpreter führt den Bytecode direkt aus, während eine VM den Bytecode in Maschinenbefehle übersetzt und dann ausführt. VMs optimieren den Bytecode oft, was die Ausführung beschleunigt und teilweise auf das Niveau von nativem Code bringt.

   .. card::

      .. exercise:: Nennen Sie einen Vorteil einer VM in Hinblick auf die Unterstützung von mehreren Programmiersprachen.
         
         .. solution::
            :pwd: EinfachWenigerAufwand

            Es ist nicht erforderlich für jede Programmiersprache einen eigenen Compiler pro Zielplattform zu schreiben.



.. class:: new-section transition-scale

Übersetzen von Java-Quellcode
-------------------------------------



.. class:: exercises

Kontrollfragen 
-------------------------------------

.. deck:: numbered

   .. card::

      .. exercise:: Was ist javac?
         :formatted-title: Was ist ``javac``? 

         .. solution:: 
            :pwd: JaVaCCCC

            ``javac`` ist der Java-Compiler, der Java-Quellcode in Bytecode übersetzt.

   .. card::

      .. exercise:: Was sind .class Dateien?
         :formatted-title: Was sind ``.class`` Dateien?

         .. solution:: 
            :pwd: JaVaCErg

            Eine ``.class`` Datei enthält den Bytecode einer Java-Klasse. Class-Dateien sind das Ergebnis der Kompilierung von Java Sourcecode mit Hilfe von ``javac``. Dieser Bytecode wird von der JVM ausgeführt.

   .. card::

      .. exercise:: Wie viele öffentliche (public) Klassen können in eine Java-Datei (.java) geschrieben werden?
         :formatted-title: Wie viele öffentliche (:java:`public`) Klassen können in einer Java-Datei (:java:`.java`) definiert sein? 

         .. solution:: 
            :pwd: JaVaCCCC

            Eine Sourcedatei darf maximal eine öffentlichen Klasse enthalten. Die Datei muss den Namen der öffentlichen Klasse tragen.