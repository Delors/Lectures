.. meta::
   :version: genesis
   :lang: de
   :author: Michael Eichberg
   :keywords: "Programmierung", "Java", "Objektorientierung"
   :description lang=de: Kontrollfragen zu Objektorientierter Programmierung in Java
   :id: lecture-prog-java-oo-kontrollfragen
   :first-slide: last-viewed
   :exercises-master-password: WirklichSchwierig!
   
.. include:: ../docutils.defs

 

Kontrollfragen: Objekt-orientierte Programmierung - Vererbung und Polymorphie
================================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0



Kontrollfragen
----------------

.. container:: scrollable

   .. class:: incremental long-list

   1. \
   
      .. exercise:: Ober- und Unterklassen
         
         1. :java:`class X extends Y { ... }` - Welches ist die Oberklasse und welches die Unterklasse?
         2. :java:`class Z extends Y { ... }` - Ist Z eine Unterklasse von X?

         .. solution::
            :pwd: Super, Unter

            1. X ist die Unterklasse und Y die Oberklasse.
            2. Nein, Z ist keine Unterklasse von X.


   2. 

       .. exercise:: Statischer und dynamischer Typ
         
         .. container:: two-columns

            .. container:: column no-separator

               Gegeben sei folgender Code:

            .. container:: column

               .. code:: java
                  :class: smaller

                  class X extends Y { ... }
                  class Z extends Y { ... }
                  Y y = new X();

         .. class:: incremental

         1. Welches ist der statische und welches der dynamische Typ von :java:`y`?
         2. Kann ich :java:`y` auch mit einem Objekt vom Typ Z initialisieren?
         3. Kann ich einer Referenzvariablen :java:`Z z;` ein Objekt vom Typ :java:`X` zuweisen?
         4. Kann ich einer Referenzvariablen :java:`Z z;` ein Objekt vom Typ :java:`Y` zuweisen?
         5. Wie teste ich, wenn ich eine Referenzvariable :java:`Y y;` habe, ob das Objekt, auf das :java:`y` zeigt, vom Typ :java:`X` ist?
         6. Was passiert wenn meine Referenzvariable vom Typ :java:`Y y` mit :java:`null` initialisiert ist, und ich einen Typtest auf :java:`X` durchführe?

         .. solution::
            :pwd: 6-Fragen-6A

            1. Der statische Typ ist Y und der dynamische Typ ist X.
            2. Ja, das ist möglich, da Z eine Subklasse von Y ist.
            3. Nein - die Klassen stehen in keiner Vererbungsbeziehung.
            4. Nein - Z ist eine Subklasse von Y und nicht umgekehrt.
            5. Mit :java:`if (y instanceof X) { ... }`
            6. Es wird `false` von `instanceof` zurückgegeben.

   3. 

       .. exercise:: Methoden

         .. class:: incremental

         1. Wann kann ich Methoden in einer Subklasse überschreiben?
         2. Was ist der Unterschied zwischen *Method Overloading* und *Method Overriding*?
         3. Was ist der Unterschied zwischen einem Konstruktor und einer Methode?
         4. Wie kann ich gezielt eine Methode der Superklasse in einer Subklasse aufrufen?
         5. Wie kann ich gezielt einen anderen Konstruktor der selben Klasse aufrufen?
         6. Welche Methoden hat jede Klasse und warum?

         .. solution::
            :pwd: dannUNDwann

            1. Eine Methode kann in einer Subklasse überschrieben werden, wenn sie in der Oberklasse als :java:`public` oder :java:`protected` deklariert ist und nicht :java:`final` ist.
            2. *Method Overloading* bedeutet, dass eine Klasse mehrere Methoden mit dem gleichen Namen aber unterschiedlichen Parametern hat. *Method Overriding* bedeutet, dass eine Methode in einer Subklasse eine Methode in der Oberklasse überschreibt.
            3. Ein Konstruktor hat den Namen der Klasse und hat keinen Rückgabewert. Er wird aufgerufen, wenn ein Objekt erzeugt werden soll mittels :java:`new` Operator. Alles andere ist eine Methode.
            4. Mit :java:`super.methodName()`.
            5. Jede Klasse erbt von der Klasse :java:`Object` insbesondere die Methode :java:`toString()`, :java:`equals(Object o)` und :java:`hashCode()`. Diese Methoden können in jeder Klasse überschrieben werden.

   4. 

       .. exercise:: Überschriebene Methoden
         
         .. container:: two-columns

            .. container:: no-separator

               Gegeben sei folgender Code:

            .. container:: column width-75

               .. code:: java
                  :class: smaller

                  class Y { void p(){println("Y.p");} }
                  class X extends Y { void p(){println("X.p");} }
                  class Z extends Y { 
                     void p(){println("Z.p");} 
                     void m(){println("Z.m");} }
                  Y x = new X(); Y z = new Z();
                  
         .. class:: incremental

         1. Was wird ausgegeben bei :java:`x.p();`?
         2. Was gibt :java:`x.p();` aus, wenn die Methode :java:`p` in der Klasse :java:`X` nicht überschrieben worden wäre?
         3. Wie kann ich die Methode :java:`m` von :java:`Z` auf der Variable :java:`z` aufrufen?
         4. Was müsste ich tun - und was würde dann passieren - wenn ich versuchen wollte die Methode :java:`m` auf der Variable :java:`x` aufzurufen?

         .. solution::
            :pwd: Methoden-Überschreibung

            1. Es wird `X.p` ausgegeben, da die Methode `p` in der Klasse `X` überschrieben wurde und beim Aufruf der Methode der dynamische Typ des Objekts berücksichtigt wird.
            2. Es würde `Y.p` ausgegeben werden, da die Methode `p` in der Klasse `X` nicht überschrieben wurde und daher die Methode der Oberklasse aufgerufen wird.
            3. Das ist direkt nicht möglich, da die Methode `m` in der Klasse `Z` definiert ist und nicht in der Klasse `Y`. Es ist also ein *Typecast* notwendig: `((Z) z).m()`.
            4. Es wird zur Laufzeit eine `ClassCastException` geworfen, da der dynamische Typ der Referenzvariable :java:`x` :java:`X` ist und die Methode :java:`m` nicht in der Klasse :java:`X` definiert ist.


   5. 

       .. exercise:: Ausnahmen 

         (:eng:`Exceptions`)

         .. class:: incremental
            
         1. Welches ist die Superklasse aller Ausnahmen? 
         2. Was ist der Unterschied zwischen *checked* und *unchecked* Ausnahmen?
         3. Wie fange ich eine Ausnahme?
         4. Was muss ich machen, wenn ich eine *checked Exception* nicht fangen will?
         5. Was ist ein :java:`catch` Block.
         6. Warum sollte ich :java:`Error`\ s nicht fangen?`
         
         .. solution::
            :pwd: Fehler-hier-und-Fehler-da

            1. :java:`Throwable`.
            2. *Checked Exceptions* müssen entweder gefangen oder deklariert werden. *Unchecked Exceptions* müssen nicht gefangen oder deklariert werden.
            3. Mit einem :java:`try`-:java:`catch`-Block.
            4. Mittels :java:`throws`-Klausel deklarieren, dass die Methode die Aussnahme ggf. weiterreicht.
            5. Ein :java:`catch`-Block fängt eine Ausnahme ab und behandelt sie.
            6. :java:`Error`\ s sind schwerwiegende Fehler, die nicht behandelt werden sollten, da es normalerweise „nichts zu retten“ gibt.