.. meta::
   :version: renaissance
   :lang: de
   :author: Michael Eichberg
   :keywords: "Kontrollfragen", "Java", "funktionale Programmierung"
   :description lang=de: Kontrollfragen bzgl. funktionaler Programmierung (in Java)
   :id: lecture-prog-java-funktionale-programmierung-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!
    
.. include:: ../docutils.defs



Funktionale Programmierung (mit Java)
===========================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1


.. class:: new-section transition-scale

Funktionale Programmierung
-------------------------------------


.. class:: exercises

Kontrollfragen 
-------------------------------------

.. deck:: numbered

   .. card:: 

      .. exercise:: Was sind Funktionen höherer Ordnung?

         .. solution::
            :pwd: HighUpToTheSky

            Funktionen höherer Ordnung sind Funktionen, die andere Funktionen als Parameter annehmen oder zurückgeben.

      .. exercise:: Was sind Lambda-Ausdrücke?

         .. solution::
            :pwd: ADBMAL

            Lambda-Ausdrücke sind anonyme Funktionen, die häufig als Parameter an andere Funktionen übergeben werden.

   .. card::

      .. exercise:: Was steht bei der funktionalen Programmierung im Vordergrund?

         .. solution::
            :pwd: Funktionsaufrufe

            Bei der funktionalen Programmierung stehen Funktionsaufrufe im Vordergrund, nicht die Veränderung von Zuständen oder Variablen.



.. class:: new-section transition-move-to-top

Funktionale Programmierung in Java
-------------------------------------


.. class:: exercises

Kontrollfragen 
-------------------------------------

.. deck:: numbered

   .. card:: 

      .. exercise:: Eigenschaften von Functional Interfaces
         :formatted-title: Eigenschaften von *Functional Interfaces*
         
         Nennen Sie eine wesentliche Eigenschaft von *Functional Interfaces*. Geben Sie ein Beispiel für ein funktionales Interface in Java an.
         
         .. solution::
            :pwd: FunctionalInterfaces

            Functional Interfaces sind Interfaces, die genau eine abstrakte Methode enthalten. Sie können mit Lambda-Ausdrücken implementiert werden.

            Ein Beispiel für ein funktionales Interface in Java ist:
            
            - :java:`java.util.function.Function<T, R>` oder auch 
            - :java:`java.util.function.Consumer<T>` oder 
            - :java:`java.util.function.Supplier<T>` oder
            - ...

   .. card:: 

      .. exercise:: Nennen Sie mind. drei Möglichkeiten ein funktionales Interface zu implementieren.
         
         .. solution::
            :pwd: FunctionalInterfaces

            1. Anonyme Klasse
            2. eine reguläre (benannte) Klasse
            3. Lambda-Ausdruck
            4. Method-Referenz

   .. card::

      .. exercise:: Was ist eine Methoden-Referenz? Worauf kann eine Methoden-Referenz verweisen? Beschreiben Sie das allgemeine Muster.
         
         .. solution::
            :pwd: MethodReference

            Eine Method-Referenz ist eine verkürzte Form eines Lambda-Ausdrucks, die auf eine Methode verweist. Sie wird verwendet, um den Code lesbarer zu machen.
            Sie hat die Form :java:`ClassName::methodName` bei statischen Methoden, :java:`ClassName::new` bei Konstrukturen oder :java:`instance::methodName` bei Instanzmethoden und kann anstelle eines Lambda-Ausdrucks verwendet werden, wenn der Lambda-Ausdruck nur eine Methode aufruft.
            Beispiel: 

            .. code:: java
            
               List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
               names.forEach(System.out::println);
            
   

.. class:: exercises

Lambda-Ausdrücke
-------------------------------------

.. exercise:: Typkompatibilität von Lambda-Ausdrücken
   
   Die Methode :java:`forEach` hat die folgende Signatur:

   .. code:: java
   
      void forEach(Consumer<? super T> action);

   Weiterhin sei gegeben:

   .. code:: java
   
      List<String> names = Arrays.asList("Alice", "Bob", "Charlie");


   .. deck:: numbered

      .. card::
   
         Welche der folgenden Lambda-Ausdrücke kann ich an :java:`forEach` übergeben?

         1. :java:`names.forEach(name -> System.out.println(name));`
         2. :java:`names.forEach(name -> name.toUpperCase());`
         3. :java:`names.forEach(name -> { System.out.println(name); return name.toUpperCase(); });`
         4. :java:`names.forEach(name -> { System.out.println(name); return; });`

      .. card::

         Welche der folgenden Objekte kann ich an :java:`forEach` übergeben?

         5. :java:`Consumer<String> cs = name -> System.out.println(name);`
         6. :java:`Consumer<Object> co = name -> System.out.println(name);`
         7. :java:`Consumer<CharSequence> ccs = name -> System.out.println(name); // Hint String extends CharSequence`
         

   .. solution:: 
      :pwd: LambdaTypen

      1. Ja, der Lambda-Ausdruck ist kompatibel mit :java:`Consumer<String>`.
      2. Ja, der Lambda-Ausdruck ist kompatibel.
      3. Nein, der Lambda-Ausdruck hat den Typ :java:`Function<String, String>`, was nicht mit dem Typ des Parameters von :java:`forEach` übereinstimmt.
      4. Ja, der Lambda-Ausdruck hat den Typ :java:`Consumer<String>`, was mit dem Typ des Parameters von :java:`forEach` übereinstimmt.

      5. Ja, der Lambda-Ausdruck hat den Typ :java:`Consumer<String>`, was mit dem Typ des Parameters von :java:`forEach` übereinstimmt.
      6. Ja, der Lambda-Ausdruck hat den Typ :java:`Consumer<Object>`, was mit dem Typ des Parameters (:java:`<? super T>`) von :java:`forEach` übereinstimmt.
      7. Ja - siehe 6.



.. class:: exercises

Streams
-------------------------------------
      
.. exercise:: Was ist ein Stream?

   .. solution::
      :pwd: Stream

      Ein Stream ist eine Abstraktion, die eine Sequenz von Elementen beschreibt, die verarbeitet werden können. Streams unterstützen eine Vielzahl von Operationen wie Filterung, Mapping und Reduzierung.

.. exercise:: Welche Typen von Streams gibt es?
   :class: incremental

   .. solution::
      :pwd: StreamTypen

      - :java:`IntStream`
      - :java:`LongStream`
      - :java:`DoubleStream`
      - :java:`Stream<T>`
      
.. exercise:: Wie sieht die grundlegende Verwendung von Streams aus?
   :class: incremental

   .. solution::
      :pwd: StreamVerwendung

      1. Erstellen eines Streams
      2. Anwenden von Zwischenoperationen (Verarbeitungs-/Transformationsschritte) (z.B. :java:`filter`, :java:`map`)
      3. Anwenden einer Terminaloperation (z.B. :java:`collect`, :java:`forEach`)



.. class:: new-section transition-move-to-top

Datenstrukturen
-------------------------------------



.. class:: exercises

Optional
---------------

.. exercise:: Welchem Zweck dient der Datentyp Optional?
   :formatted-title: Welchem Zweck dient der Datentyp :java:`Optional`?

   .. solution::
      :pwd: OptIonal

      Der Datentyp :java:`Optional` dient dazu, den Umgang mit Nullwerten zu erleichtern und die Möglichkeit darzustellen, dass ein Wert vorhanden sein kann oder nicht. Er hilft, :java:`NullPointerException`\ s zu vermeiden.



.. class:: exercises

Queue / Warteschlange
-------------------------------------

.. deck::

   .. card::
      
      .. exercise:: Warteschlangen verstehen

         1. Benennen Sie die essentiellen Eigenschaften einer Warteschlange.
         2. Welche Implementierungsstrategien gibt es für Warteschlangen? Erklären Sie diese kurz.

         .. solution::
            :pwd: Queue
         
            Eigenschaften:

            - FIFO (First In First Out)
            - Elemente werden in der Reihenfolge verarbeitet, in der sie hinzugefügt wurden.
            - Es gibt zwei Hauptoperationen: :java:`enqueue` (zum Hinzufügen eines Elements) und :java:`dequeue` (zum Entfernen des ältesten Elements).

            Als Implementierungsstrategie gibt es:

            - Array-basierte Implementierung (in der *Regel* ineffizient)
            - Verkettete Liste (in der *Regel* effizient(er))

   .. card::

      .. exercise:: Erstellen Sie ein UML Klassendiagramm für verkettete Listen

         .. solution::
            :pwd: UMLChainedList
         
            .. image:: images/chainedlist.svg
               :alt: UML Klassendiagramm
               :align: center

