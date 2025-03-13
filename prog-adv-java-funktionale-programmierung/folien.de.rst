.. meta::
    :version: renaissance
    :lang: de
    :author: Michael Eichberg
    :keywords: "Programmierung", "Java", "Funktional Programmierung"
    :description lang=de: Java Funktionale Programmierung
    :id: lecture-prog-java-functional-programming
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!
    :theme: colored

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
.. role:: green
.. role:: obsolete
.. role:: peripheral
.. role:: monospaced
.. role:: java(code)
   :language: java
.. role:: console(code)
   :language: console



Java - Funktionale Programmierung
===========================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0

.. class:: block-footer

    Teile der Folien basieren auf: Th. Letschert - Funktionale Programmierung in Java.


.. supplemental::

    :Folien:

        |html-source|

        |pdf-source|

    ..
        :Kontrollfragen:

            .. source:: kontrollfragen.de.rst
                :path: relative
                :prefix: https://delors.github.io/
                :suffix: .html

        :Klausurvorbereitung:

            .. source:: klausurvorbereitung.de.rst
                :path: relative
                :prefix: https://delors.github.io/
                :suffix: .html

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section

Einführung in die Funktionale Programmierung
------------------------------------------------



Grundlagen funktionaler Programmierung
-----------------------------------------------------------------------

.. deck::

    .. card::

        .. class:: incremental-list

        - Programmierparadigma, bei dem Funktionen im Mittelpunkt stehen
        - Vermeidet veränderliche Zustände (:eng:`Mutable State`)
        - Fördert deklarativen Code statt imperativem Code

    .. card::

        .. question::
            :class: incremental

            Wie unterscheidet sich dieses Paradigma von der objektorientierten Programmierung?

        .. answer::
            :class: incremental margin-top-1em

            - Methoden ohne Seiteneffekte
            - Daten sind standardmäßig unveränderlich
            - Fokus auf Funktionsanwendungen und -komposition

    .. card::

        .. rubric:: Wichtige Konzepte

        .. class:: incremental-list

        - Funktionen Höherer Ordnung
        - Lambda-Ausdrücke
        - Funktionskomposition
        - Currying und Partielle Anwendung



.. class:: new-section

Funktionale Programmierung in Java
------------------------------------------------



Lambdas
------------------------------------------------

.. deck::

    .. card::

        :Lambda (auch Closure):

            Ein Ausdruck, dessen Wert eine Funktion ist.

            Solche Ausdrücke sind sehr nützlich, mussten in Java bisher aber mit anonymen inneren Klassen emuliert werden.

    .. card::

        .. rubric:: Ein einfache Personenklasse

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            class Person {
                private String name;
                private int age;

                public Person(String name, int age) {
                    this.name = name;
                    this.age = age;
                }
                public String getName() { return name; }
                public int getAge() { return age; }
                public String toString() { return "Person[" + name + ", " + age + "]"; }
            }

    .. card::

        .. rubric:: Sortieren von Personen nach Alter

        Angenommen wir haben eine Klasse Person und eine Liste von Personen, die nach Alter sortiert werden soll. Dazu muss eine Vergleichsfunktion übergeben werden. In Java <8 kommt dazu nur ein Objekt in Frage.

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            List<Person> persons = Arrays.asList(
                new Person("Hugo", 55),
                new Person("Amalie", 15),
                new Person("Anelise", 32) );

        .. deck::

            .. card::

                .. rubric:: Traditionelle Lösung

                .. code:: java
                    :number-lines:
                    :class: copy-to-clipboard

                    Collections.sort(persons, new Comparator<Person>() {
                        public int compare(Person p1, Person p2) {
                            return p1.getAge() - p2.getAge();
                        }
                    });


            .. card::

                .. rubric:: Lösung ab Java 8

                .. code:: java
                    :number-lines:
                    :class: copy-to-clipboard

                    Collections.sort(
                        persons,
                        (p1, p2) -> { return p1.getAge() - p2.getAge(); }
                    );

            .. card::

                .. rubric:: Lösung ab Java 8 (kürzer)

                .. code:: java
                    :number-lines:
                    :class: copy-to-clipboard

                    Collections.sort(persons, (p1, p2) -> p1.getAge() - p2.getAge());


    .. card::

        .. attention::


            Bis Java 7 ist :java:`java.lang.Object` der Basistyp aller Referenztypen.
            Der Typ eines Lambdas ist jedoch der Typ eines funktionalen Interfaces, das nur eine Methode hat und dieser Typ muss explizit angegeben werden.

        .. deck::

            .. card::

                **Instanzen von inneren Klassen können immer Object zugewiesen werden:**

                .. code:: java
                    :number-lines:
                    :class: copy-to-clipboard

                    Object actionListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(text);
                        }
                    };

            .. card::

                **Illegale Zuweisung:**

                .. code:: java
                    :number-lines:
                    :class: copy-to-clipboard

                    Object actionListener = (e) -> System.out.println(text);

                    // Error: The target type of this expression must be a functional interface

            .. card::


                **Zuweisung an ein funktionales Interface:**

                .. code:: java
                    :number-lines:
                    :class: copy-to-clipboard

                    ActionListener actionListener = (e) -> System.out.println(text);

    .. card::

        .. rubric:: Funktionale Interfaces

        :Functional Interface / SAM-Interface (Single Abstract Method Interface):
            Ein Functional Interface ist ein Interface das genau eine Methode enthält (die natürlich abstrakt ist) optional kann die Annotation :java:`@FunctionalInterface` hinzugefügt werden.


        .. example::
            :class: incremental

            .. code:: java
                :number-lines:
                :class: copy-to-clipboard

                @FunctionalInterface
                interface MyActionListener extends java.awt.event.ActionListener {
                    /*final static*/ int MAGIC_NUMBER = 42;
                }

                MyActionListener actionListener = (e) -> System.out.println(text + MyActionListener.MAGIC_NUMBER);


    .. card::

        .. rubric:: Vordefinierte Funktionsinterfaces

        :java:`java.util.function` enthält viele vordefinierte Funktionsinterfaces, die in der funktionalen Programmierung häufig verwendet werden.

        *Beispiele sind:*

        .. class:: incremental-list

        - :java:`Function<T,R>`: Eine Funktion, die ein Argument vom Typ :java:`T` entgegennimmt und ein Ergebnis vom Typ :java:`R` zurückgibt.
        - :java:`Predicate<T>`: Eine Funktion, die ein Argument vom Typ :java:`T` entgegennimmt und ein Ergebnis vom Typ :java:`boolean` zurückgibt.
        - :java:`Consumer<T>`: Eine Funktion, die ein Argument vom Typ :java:`T` entgegennimmt und kein Ergebnis zurückgibt.
        - :java:`Supplier<T>`: Eine Funktion, die kein Argument entgegennimmt und ein Ergebnis vom Typ :java:`T` zurückgibt.

    .. card::

        .. example::

            :java:`Predicate<T>`

            .. code:: java
                :number-lines:
                :class: copy-to-clipboard

                static <T> List<T> filterList(List<T> l, Predicate<T> pred) {
                    List<T> res = new LinkedList<>();
                    for (T x : l) {
                        if (pred.test(x)) { res.add(x); }
                    }
                    return res;
                }

                List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
                System.out.println(filterList(l, x -> x % 2 == 0));

            .. container:: incremental

                *Ausgabe:* :console:`[2, 4, 6, 8]`

    .. card::

        .. example::

            :java:`Consumer<T>`

            .. code:: java
                :number-lines:
                :class: copy-to-clipboard

                class WorkerOnList<T> implements Consumer<List<T>> {
                    private Consumer<T> action;
                    public WorkerOnList(Consumer<T> action) { this.action = action; }

                    @Override public void accept(List<T> l) {
                        for (T x : l) action.accept(x);
                }   }

                WorkerOnList<Integer> worker = new WorkerOnList<>( (i) -> System.out.println(i*10) );
                worker.accept(Arrays.asList(1,2,3,4));

            *Ausgabe:* :console:`10 20 30 40`



Lambdas -Method References
------------------------------------------------

.. deck::

    .. card:: Referenzen auf Methoden

        Als Implementierung eines funktionalen Interfaces (als „Lambda“) können auch Methoden verwendet werden.

    .. card::

        .. deck::

            .. card::

                .. example::

                    .. rubric:: Referenz auf statische Methode

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard

                        class ListMethods {
                            static <T> List<T> filterList(List<T> l, Predicate<T> pred) {
                                List<T> res = new LinkedList<>();
                                for (T x : l) if (pred.test(x)) { res.add(x); }
                                return res;
                            }
                            static boolean isEven(int x) { return x % 2 == 0; }
                        }

                        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
                        System.out.println(filterList(l, ListMethods::isEven));


                    *Ausgabe:* :console:`[2, 4, 6, 8]`

            .. card::

                .. example::

                    .. rubric:: Referenz auf Instanzmethode

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard

                        class Tester {
                            private int magicNumber;
                            public Tester(int magicNumber) { this.magicNumber = magicNumber; }
                            boolean isMagic(int x) { return x == magicNumber; }
                        }
                        class ListMethods {
                            static <T> List<T> filterList(List<T> l, Predicate<T> pred) {
                                List<T> res = new LinkedList<>();
                                for (T x : l) if (pred.test(x)) res.add(x);
                                return res;
                        }   }

                        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
                        System.out.println(filterList(l, new Tester(5)::isMagic));


            .. card::

                .. example::

                    .. rubric:: Referenz auf Constructor

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard

                        class Tester {
                            private int magicNumber;
                            public Tester(int magicNumber) { this.magicNumber = magicNumber; }
                            boolean isMagic(int x) { return x == magicNumber; }
                        }

                        Function<Integer,Tester> create = Tester::new;
                        create.apply(5).isMagic(5);

                    *Ausgabe:* :console:`true`



Erweiterungen der Collection API
------------------------------------------------

.. deck::

    .. card::

        .. rubric:: Neue Methoden in der Collection API

        - :java:`forEach(Consumer<? super T> action)`
        - :java:`removeIf(Predicate<? super T> filter)`
        - :java:`replaceAll(UnaryOperator<T> operator)`
        - :java:`sort(Comparator<? super T> c)`

    .. card::

        .. example::

            .. code:: java
                :number-lines:
                :class: copy-to-clipboard

                List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
                l.replaceAll(x -> x * 10);
                l.forEach(System.out::println);

            *Ausgabe:* :console:`10 20 30 40 50 60 70 80 90`



.. class:: exercises

Übung
--------

.. exercise::

    Schreiben Sie eine Klasse :java:`Tuple2<T>`; d. h. eine Variante von :java:`Pair` bei der beide Werte vom gleichen Typ :java:`T` sein müssen. Die Klasse soll Methoden haben, um die beiden Werte zu setzen und zu lesen und weiterhin um folgende Methoden ergänzt werden:

    - :java:`void forEach(Consumer<...> action)`: Führt die Aktion für jedes Element in der :java:`Queue` aus.
    - :java:`void replaceAll(UnaryOperator<...> operator)`: Ersetzt alle Elemente in der :java:`Queue` durch das Ergebnis der Anwendung des Operators auf das Element.

    Schreiben Sie Tests für die neuen Methoden. Stellen Sie 100% *Statementcoverage* sicher.
    
    .. hint:: 

        Sorgen Sie ggf. vorher dafür, dass Sie eine angemessene Projektstruktur haben.

        Passen Sie ggf. die ``pom.xml`` von ihren anderen Projekten an.


    .. solution::
        :pwd: Pair%Funktional

        .. rubric:: Tuple2.java

        .. include:: code/src/main/java/ds/generic/Tuple2.java
            :code: java
            :class: copy-to-clipboard
            :number-lines:

        .. rubric:: JUnit Tests

        .. include:: code/src/test/java/ds/generic/Tuple2Test.java
            :code: java
            :class: copy-to-clipboard
            :number-lines:

        .. rubric:: Maven Build File

        .. include:: code/pom.xml
            :code: xml
            :class: copy-to-clipboard
            :number-lines:



.. class:: exercises

Übung
--------

.. exercise:: Implementierung einer Warteschlange mittels verketteter Liste

    Implementieren Sie eine Warteschlange (:java:`Queue<T>`) basierend auf einer verketteten Liste.  Die Klasse :java:`Queue<T>` soll folgendes Interface implementieren.

    .. code:: java
        :number-lines:
        :class: copy-to-clipboard
        
        public interface Queue<T> {
            void enqueue(T item);
            T dequeue();
            boolean isEmpty();
            int size();

            void replaceAll(UnaryOperator<T> operator);
            void forEach(Consumer<T> operator);
            /** Erzeugt eine neue Queue<X> bei der die Elemente der neuen Queue das Ergebnis der Anwendung der Funktion mapper sind. */
            <X> Queue<X> map(Function<T, X> mapper);

            /** Erzeugt eine leere Queue. */
            static <T> Queue<T> empty() { TODO }
        }
        
    Schreiben Sie Testfälle, um die Implementierung zu überprüfen. Zielen Sie auf mind. 100% *Statementcoverage* ab.

    
    .. solution::
        :pwd: FuncF

        .. rubric:: Queue.java

        .. include:: code/src/main/java/ds/generic/Queue.java
            :code: java
            :class: copy-to-clipboard
            :number-lines:

        .. rubric:: QueueTest.java

        .. include:: code/src/test/java/ds/generic/QueueTest.java
            :code: java
            :class: copy-to-clipboard
            :number-lines:

        .. rubric:: Pom.xml

        .. include:: code/pom.xml
            :code: xml
            :class: copy-to-clipboard
            :number-lines:




.. class: : new-section
    Streams
    ------------------------------------------------
    https://homepages.thm.de/~hg51/Veranstaltungen/A_D/Folien/java-8-kurzeinfuehrung.pdf
