.. meta::
   :version: renaissance
   :lang: de
   :author: Michael Eichberg
   :keywords: "Programmierung", "Java", "Generics", "Kontrollfragen"
   :description lang=de: Kontrollfragen zu Java Generics
   :id: lecture-prog-java-generics-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Java Generics - Wiederholung
===========================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1


.. class:: new-section

Datenstrukturen
-------------------------------------


.. class:: exercises

Kontrollfragen
-------------------------------------

.. deck:: numbered

   .. card::

      .. exercise:: LiFo oder FiFo?

         1. Stack
         2. Queue
         3. List

         .. solution::
            :pwd: OfFiLi

            1. LiFo
            2. FiFo
            3. -

   .. card::

      .. exercise:: Welches sind die wesentlichen Methoden eines Stacks?

         .. solution::
            :pwd: CoLlEc-TiOn

            Die wesentlichen Methoden eines Stacks sind:

            - :java:`push(E e)`
            - :java:`pop()`
            - :java:`peek()`
            - :java:`empty()`



.. class:: new-section

Generics
-------------------------------------



.. class:: exercises

Kontrollfragen
-------------------------------------

.. deck:: numbered

   .. card::

      .. exercise:: Welchem Zweck dienen Generics?

         .. solution::
            :pwd: GeNeRIcs

            Generics ermöglichen es, Typen zu parametrisieren. Dadurch kann der gleiche Code für unterschiedliche Typen typsicher wiederverwendet werden. D. h. es kann zu Laufzeit keine ClassCastException geben.

   .. card::

      .. exercise:: Was ist der Diamond Operator?

         .. solution::
            :pwd: DiAmOnD

            Der Diamond Operator ``<>`` wird verwendet, um den Typ eines Generics zu inferieren. D. h. es ist nicht notwendig, den Typ explizit anzugeben, wenn dieser für den Compiler aus dem Kontext ersichtlich ist.


   .. card::

      .. exercise:: Wie sieht der Code aus, wenn Sie eine Liste von Ganzzahlen erstellen wollen? D.  h. welche Initialisierungen sind korrekt?

         .. code-block:: java
            :number-lines:

            ArrayList<Integer>   list = new ArrayList<int>();
            ArrayList<Integer>   list = new ArrayList<>();
            var                  list = new ArrayList<int>();
            var                  list = new ArrayList<Integer>();
            ArrayList<int>       list = new ArrayList<>();
            List<int>            list = new ArrayList<>();
            List<Integer>        list = new ArrayList<>();

         .. solution::
            :pwd: LiStInT

            :java:`var list = new ArrayList<Integer>();`

            :java:`List<Integer> list = new ArrayList<>();`

   .. card::

      .. exercise:: Welche Zeilen sind korrekt bzw. falsch?

         .. code-block:: java
            :number-lines:

            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add("2");

         .. solution::
            :pwd: CoRrFaLs

            Die Zeile ``list.add("2");`` ist falsch, da die Liste nur Ganzzahlen aufnehmen kann.


   .. card::

      .. exercise:: Was sind Raw-Types?

         .. solution::
            :pwd: RaWtYpEs

            Raw-Types sind Generics ohne Typangabe. Sie sollten vermieden werden, da sie zu Laufzeit :java:`ClassCastException`\ s verursachen können.


   .. card::

      .. exercise:: Was ist der Unterschied zwischen List&lt;?&gt; und List&lt;Object&gt;?
         :formatted-title: Was ist der Unterschied zwischen :java:`List<?>` und :java:`List<Object>`?

         .. solution::
            :pwd: LiStQuEs

            :java:`List<?>` ist eine Liste von unbekannten Typen, während :java:`List<Object>` eine Liste von Objekten ist. D. h. in einer :java:`List<?>` können keine Werte gespeichert werden, während in einer :java:`List<Object>` beliebige Objekte gespeichert werden können.

   .. card::

      .. exercise:: Was ist der Unterschied zwischen List&lt;? extends Number&gt; und List&lt;Number&gt;?
         :formatted-title: Was ist der Unterschied zwischen :java:`List<? extends Number>` und :java:`List<Number>`?

         .. solution::
            :pwd: LiStExNu

            Die Liste :java:`List<? extends Number>` ist eine Liste von Objekten, die von der Klasse :java:`Number` erben, während die Liste :java:`List<Number>` eine Liste von Objekten der Klasse :java:`Number` ist. In letzterem Falle ist es somit garantiert möglich :java:`Number` Objekte in der Liste zu speichern. D. h. :java:`List<? extends Number>` kann auch eine :java:`List<Integer>` sein und deswegen ist das Speichern von :java:`Number` Objekten in dieser Liste nicht möglich, um die Typsicherheit zu gewährleisten.

   .. card::

      .. exercise:: Welche Zuweisungen sind gültig?

         .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            List<? extends Number>  list1 = new ArrayList<Integer>();
            List<? extends Number>  list2 = new ArrayList<Double>();
            List<? extends Number>  list3 = new ArrayList<String>();
            ArrayList<Object>       list4 = new ArrayList<Integer>();
            List<Object>            list5 = new ArrayList<Object>();
            ArrayList<Integer>      list6 = new ArrayList<Object>();
            List<Integer>           list7 = new ArrayList<Object>();


   .. card::

      .. exercise:: Was ist damit gemeint, dass Generics invariant sind?
         :formatted-title: Was ist damit gemeint, dass Generics *invariant* sind?

         .. solution::
            :pwd: InVaRiAnT

            Generics sind invariant, d. h. :java:`List<A>` ist nicht kompatibel mit :java:`List<B>`, auch wenn :java:`A` und :java:`B` in einer Vererbungshierarchie stehen. Das bedeutet, dass :java:`List<Integer>` nicht kompatibel ist mit :java:`List<Number>`, auch wenn :java:`Integer` von :java:`Number` erbt.


   .. card::

      .. exercise:: Was ist Auto-Boxing?

         .. solution::
            :pwd: AutoAutoAutoBoxing

            Auto-Boxing ist die automatische Konvertierung von primitiven Datentypen in ihre Wrapper-Klassen und umgekehrt. D. h. es ist nicht notwendig, explizit zwischen primitiven Datentypen und ihren Wrapper-Klassen zu konvertieren.

   .. card::

      .. exercise:: Sie haben ein Set<Integer>, warum ist es unproblematisch, dass die Methode contains die Signatur boolean contains(Object o) hat?
         :formatted-title: Sie haben ein :java:`Set<Integer>`, warum ist es unproblematisch, dass die Methode :java:`contains` die Signatur :java:`boolean contains(Object o)` hat (und nicht :java:`boolean contains(T t)` wenn :java:`T` der generische Typparameter von :java:`Set` sei)?

         .. solution::
            :pwd: CoInSe

            :java:`Object` ist der Root-Typ aller Referenztypen in Java. Weiterhin braucht die Methode :java:`contains` nur die Methoden :java:`hashCode` und :java:`equals`, die ebenfalls von :java:`Object` definiert werden (und in Subklassen ggf. überschrieben werden). Somit kann die Methode :java:`contains` jedes Objekt mit jedem anderen Objekt vergleichen, ohne dass es zu Laufzeitfehlern kommt.


   .. card::

      .. exercise:: Erklären Sie die wesentlichen Elemente des Iterator Patterns.

         .. solution::
            :pwd: ItErAtOoOoOr

            Das Iterator Pattern ermöglicht es, über die Elemente einer Collection zu iterieren, ohne die interne Struktur der Collection zu kennen. Wesentlich ist, dass es eine Möglichkeit gibt um zu entscheiden ob noch Element vorhanden sind oder ob bereits über alle Elemente iteriert wurde.

            Ein Java-Iterator bietet die Methoden :java:`hasNext` und :java:`next`, um über die Elemente der Collection zu iterieren. Die Collection selbst bietet die Methode :java:`iterator`, um einen Iterator zu erstellen. Der Iterator speichert den aktuellen Zustand der Iteration und ermöglicht es, über die Elemente der Collection zu iterieren, ohne die interne Struktur der Collection zu kennen.

   .. card::

      .. exercise:: Wie ist der Zusammenhang zwischen den Interfaces Iterable und Iterator?
         :formatted-title: Wie ist der Zusammenhang zwischen den Interfaces :java:`Iterable` und :java:`Iterator`?

         .. solution::
            :pwd: IterableIteratorOderSo

            Das Interface :java:`Iterable` definiert eine Methode :java:`iterator`, die einen Iterator zurückgibt. Über eine :java:`Iterable` Collection kann mittels einer for-each Loop iteriert werden.

   .. card::

      .. exercise:: Erklären Sie die folgenden Methodensignaturen

         1. :java:`public static <T> void swap(List<T> list, int i, int j)`
         2. :java:`public static <T extends Comparable<T>> void sort(List<T> list)`
         3. :java:`public static void sort(List<? extends Comparable> list)`

            Ist diese Methode sinnvoll bzw. ist dies Methode vergleichbar mit der vorherigen Methode?

         4. :java:`public static <T> void copy(List<? super T> destination, List<? extends T> source)`

         .. solution::
            :pwd: AdvGenericMethodsT

            1. Die Methode :java:`swap` vertauscht die Elemente an den Positionen :java:`i` und :java:`j` in der Liste :java:`list`.
            2. Die Methode :java:`sort` sortiert eine Liste :java:`list` von Objekten, die das Interface :java:`Comparable` implementieren. Damit wir in der Implementierung der Methode auf die Elemente der Liste zugreifen können und diese miteinander vergleichen können, ist es notwendig, dass die Elemente der Liste das Interface :java:`Comparable` implementieren.
            3. Die Methode :java:`sort` sortiert eine Liste :java:`list` von Objekten, die das Interface :java:`Comparable` implementieren. Da der konkrete Typ der Elemente der Liste nicht bekannt ist, ist es jedoch nicht möglich, die Elemente aus der Liste zu nehmen und wieder in der Liste zu speichern ohne auf Typsicherheit zu verzichten.
            4. Die Methode :java:`copy` kopiert die Elemente der Liste :java:`source` in die Liste :java:`destination`. Da die Liste :java:`destination` Elemente vom Typ :java:`T` oder von einem Supertyp von :java:`T` enthalten kann, ist es möglich, die Elemente der Liste :java:`source` in die Liste :java:`destination` zu kopieren.
