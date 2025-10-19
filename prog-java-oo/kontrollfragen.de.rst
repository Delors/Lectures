.. meta::
   :lang: de
   :author: Michael Eichberg
   :keywords: "Programmierung", "Java", "Objektorientierung"
   :description lang=de: Kontrollfragen zu einfacher objektorientierter Programmierung
   :id: lecture-prog-java-simple-oo-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!
    
.. include:: ../docutils.defs



Kontrollfragen zu einfacher objektorientierter Programmierung
==============================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0



.. class:: new-section

Grundlagen
------------



Kontrollfragen
----------------

.. story::
   
      .. exercise:: Ist diese Aussage korrekt? 
         :class: incremental
         
         In einer Java Datei können mehrere öffentlichen Klassen definiert werden. 

         .. solution::
            :pwd: NEIn

            Nein - es kann nur eine öffentliche Klasse pro Datei geben. Es können aber mehrere nicht öffentliche Klassen definiert werden.

      .. exercise:: Ist die Aussage korrekt?
         :class: incremental
         
         Der :java:`new` Operator initialisiert die Attribute eines Objekts mit den Standardwerten.

         .. solution::
            :pwd: Ja..so_ist_es

            Ja - die Attribute werden mit den Standardwerten initialisiert.

      .. exercise:: Welche Aussage ist korrekt?
         :class: incremental

         :java:`var cs = new Circle[10]`;
         
         1. Die Anweisung definiert eine Referenzvariable vom Typ :java:`Circle`.
         2. Die Anweisung erzeugt ein Array mit 10 Elementen vom Typ :java:`Circle`.
         3. Der Typ von :java:`cs` ist :java:`Circle`.

         .. solution::
            :pwd: nur-2-stimmt

            - nur die zweite Aussage ist korrekt.

      .. exercise:: Was passiert?
         :class: incremental
      
         .. rubric:: Circle.java

         .. include:: code/circle/Circle.java
            :code: java
            :class: copy-to-clipboard

         .. rubric:: Main.java

         .. include:: code/circle/Main.java
            :code: java
            :class: copy-to-clipboard

         .. solution::
            :pwd: Alles-gut

            Es wird über die Methode :java:`create` ein neues Objekt vom Typ Circle erzeugt.

      .. exercise:: Was wird ausgegeben?
         :class: incremental

         .. code:: java
            :number-lines:

            var c1 = new Triangle();
            var c2 = new Triangle();
            var c3 = c2; 
            println(c1 == c2); 
            println(c2 == c3);

         .. solution::
            :pwd: false-true

            Es wird `false` und `true` ausgegeben.

      .. exercise:: Was passiert/wie ist die Ausgabe?
         :class: incremental
      
         .. rubric:: Circle.java

         .. include:: code/circle_memoization/Circle.java
            :code: java
            :class: copy-to-clipboard

         .. rubric:: Main.java

         .. include:: code/circle_memoization/Main.java
            :code: java
            :class: copy-to-clipboard

         .. solution::
            :pwd: Alles-gut

            Es wird über die Methode :java:`create` ein neues Objekt vom Typ Circle erzeugt, es sei denn es handelt sich um den Speziellen Kreis mit Radius 1. Deswegen ist die Ausgabe hier auch ``true``.

      .. exercise:: Stimmt die folgende Aussage?
         :class: incremental

         Ein Objekt wird dann aus dem Speicher entfernt, wenn kein Zeiger mehr auf das Objekt zeigt?

         .. solution::
            :pwd: Ja-Korrekt

            Ja, das ist korrekt.


.. class:: new-section


Die Selbstreferenz :java:`this`
--------------------------------


Kontrollfragen
----------------

.. story::            

      .. exercise:: Welche Aussagen sind korrekt?
         :class: incremental

         1. :java:`this` wird benötigt, um einen anderen Konstruktor aufzurufen.
         2. :java:`this` ist eine Referenz auf das aktuelle Objekt. 
         3. :java:`this` ist in statischen Methoden verfügbar.

         .. solution::
            :pwd: 1-und2

            In statischen Methoden ist :java:`this` nicht verfügbar - es gibt kein Objekt. 
    
      .. exercise:: Welche der folgenden Verwendungen von this sind (ggf. in Konstruktoren und bei dem Vorhandensein entsprechender Attribute) korrekt?
         :class: incremental

         1. :java:`this = new Circle();`
         2. :java:`this.radius = 10;`
         3. :java:`this();`

         .. solution::
            :pwd: 2-und3

            Die erste Aussage ist nicht korrekt, da wir `this` nichts zuweisen können.
   
            
