.. meta::
    :lang: de
    :author: Michael Eichberg
    :keywords: "Java", "Streams", "Funktional Programmierung"
    :description lang=de: Java Streams
    :id: lecture-prog-java-streams
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!
    :theme: colored

.. include:: ../docutils.defs



Java - Streams
===========================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 2.0 [Themed]

.. class:: block-footer

    Teile der Folien sind inspiriert von: `Th. Letschert - Funktionale Programmierung in Java. <https://homepages.thm.de/~hg51/Veranstaltungen/A_D/Folien/java-8-kurzeinfuehrung.pdf>`__

.. supplemental::

    :Folien:

        |html-source|

        |pdf-source|

    :Kontrollfragen:

        .. source:: kontrollfragen.de.rst
            :path: relative
            :prefix: https://delors.github.io/
            :suffix: .html

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: Glossary

Funktionale Programmierung in Java
--------------------------------------------





(Aktuelle) Herausforderungen
------------------------------------------------

.. deck::

    .. card::

        **Verarbeitung von**:

        .. class:: list-with-sublists incremental-list

        - großen Datenmengen

          .. class:: incremental-list

          - die nicht (ohne weiteres) in den Speicher passen oder
          - bei denen es keinen Sinn macht diese vorab vollständig in den Speicher zu laden, da immer nur ein kleiner Abschnitt analysiert werden muss
        - Daten, die kontinuierlich verarbeitet werden müssen

    .. card::

        .. example::
            :class: incremental

            .. rubric:: Konkrete Szenarien

            - HTTP Request/Response Handling (Audio/Video Streaming)
            - Verarbeitung von AI Streaming Responses
            - Ver-/Entschlüsselung von großen Datenmengen
            - Verarbeitung große Logdateien
            - Kontinuierliche Verarbeitung von Daten
            - Verarbeitung von Ereignissen (Event)
            - Echtzeitdatenverarbeitung von kontinuierlichen Daten von (IoT) Sensoren, Finanzdaten/-transaktionen
            - Aufbereitung großer Wörterbücher (z.B. für Passwortwiederherstellung)

    .. card::

        - Parallele Verarbeitung und effiziente Nutzung von modernen CPU-Architekturen

        .. supplemental::

            Insbesondere die korrekte Nutzung ist häufig schwierig und hier ist die Verwendung von transparent parallelisierten Streams sehr hilfreich.
            (Z. B. mit Java Parallel Streams oder :scala:`.par` in Scala ist eine transparente Parallelisierung möglich.)

    .. card::

        - Entwicklung von Software nahe am Domänenmodell, um die korrekte Implementierung zu unterstützen. Das Ziel sollte eine möglichst geringe Repräsentationslücke (:eng:`Low representational gap`) zwischen Code und Domänenmodell sein.



.. class:: no-title center-content

Java I/O Streams vs. Java Stream API
-------------------------------------

.. attention::

    Im Folgenden betrachten wir die Java Stream API und nicht Java I/O Streams.

    .. list-table::
        :header-rows: 1
        :stub-columns: 1
        :width: 100%
        :widths: 20 40 40
        :class: incremental

        * -
          - Java I/O Streams
          - Java Stream API
        * - Package
          - :java:`java.io.*` / :java:`java.nio.*`
          - :java:`java.util.stream.*`
        * - Fokus
          - Byte-/Zeichentransport
          - Datenverarbeitung
        * - Paradigma
          - Imperativ
          - Funktional/Deklarativ


.. supplemental::

    :Imperativ Programmierung:

        Das Vorgehen wird detailliert durch konkrete Anweisungen beschrieben, die genau vorgeben welche einzelnen Schritte von dem Computer ausgeführt werden sollen, um das Ziel zu erreichen.

        Viele gängige *general-purpose* Programmiersprachen (C, C++, Rust, Java, Go, JavaScript, Python) sind im Kern imperative Programmiersprachen.

    :Deklarative Programmierung:

        Das Ziel ist es auszudrücken *was* erreicht werden soll, ohne das *wie* genau anzugeben.

        (Prominentes Beispiel für eine deklarative Programmiersprache: SQL als Datenbankabfragesprache.)

    .. remark::

        - Auch für gängige Programmiersprachen, die im Kern imperativ sind, ist zu beobachten, dass mehr und mehr Ideen und Konzepte aus der deklarativen Programmierung Einzug in diese Sprachen - insbesondere auch über APIs - finden.

        - Es ist in der Zwischenzeit so, dass die Grenzen zwischen (klassischen) prozeduralen (d. h. primär imperativen) Programmiersprachen und funktionalen sowie deklarativen Programmiersprachen verschwimmen.

        - Scala - als ein Beispiel für eine moderne Programmiersprache - ist von Grund auf als objektorientiert-funktionale Sprache entworfen wurde.




Streams - Einführung
------------------------------------------------

.. class:: incremental-list

:Pragmatische Sicht: Streams\ [#]_ sind umgeformte Sammlungen\ :incremental:`, die durch die Umformung für funktional-orientiere Massen-Operationen geeignet sind.`
:Konzeptionelle Sicht: Streams erlauben die „korrekte, lesbare (domänennahe)“ Verarbeitung von Daten mit Hilfe von Konzepten und Ideen aus der funktionalen und deklarativen Programmierung.

.. example::
    :class: incremental

    .. code:: java
        :number-lines:
        :class: copy-to-clipboard

        import java.util.Arrays;
        import java.util.List;
        import java.util.stream.Collectors;

        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> ll = l
            .stream()                       // list → stream
            .filter(x -> x % 2 == 0)        // filter list with predicate
            .map(x -> 10 * x)               // map each element to a new one
            .collect(Collectors.toList());  // back to a list
        ll.forEach(x -> System.out.println(x));

    .. container:: incremental

        *Ausgabe:* :console:`20 40 60 80`

.. [#] `Th. Letschert <https://homepages.thm.de/~hg51/Veranstaltungen/A_D/Folien/java-8-kurzeinfuehrung.pdf>`__








Streams API - kurze Historie
------------------------------

- die Streams API wurde mit Java 1.8 eingeführt und wird seit dem ständig `weiterentwickelt <https://javapro.io/2025/11/13/java-streams-evolution-from-java-8-to-today/>`__.



.. story::

    .. raw:: html
        :class: center-content

        <div    style="width: 48ch; height: 128ch;">
        <svg    viewBox="0 0 24 128"
                font-size="2"
                version="1.1" xmlns="http://www.w3.org/2000/svg">
            <!-- the previous style is reused!-->
            <g class="graph">
                <g>
                <circle cx="8" cy="1" r="0.5" />
                <text x="7.5" y="3.25">A</text>
                </g>

                <g class="incremental">
                <circle cx="1" cy="6" r="0.5" />
                <line x1="1.35" y1="5.65" x2="7.65" y2="1.35" />
                <text x="0.5" y="8.25">B</text>
                </g>

                <g class="incremental">
                <circle cx="15" cy="6" r="0.5" />
                <line x1="14.65" y1="5.65" x2="8.35" y2="1.35"/>
                <text x="15.75" y="6.75">C</text>
                </g>

                <g class="incremental">
                <circle cx="8" cy="11" r="0.5" />
                <line x1="8.35" y1="10.65" x2="14.5" y2="6" />
                <path d="M 8 10.5 C 5 6.5, 2.5 12, 7.5 11" />
                <text x="7.5" y="13.25">D</text>
                </g>

                <g class="incremental">
                <circle cx="22" cy="11" r="0.5" />
                <line x1="21.5" y1="11" x2="8.5" y2="11" />
                <path d="M 22 10.5 C 22 5.5, 16 1, 8.5 1" />
                <text x="21.5" y="13.25">E</text>
                </g>

                <g class="incremental" transform="translate(0,20)">
                <circle cx="22" cy="11" r="0.5" />
                <line x1="21.5" y1="11" x2="8.5" y2="11" />
                <path d="M 22 10.5 C 22 5.5, 16 1, 8.5 1" />
                <text x="21.5" y="13.25">E</text>
                </g>
                <g class="incremental" transform="translate(0,40)">
                <circle cx="22" cy="11" r="0.5" />
                <line x1="21.5" y1="11" x2="8.5" y2="11" />
                <path d="M 22 10.5 C 22 5.5, 16 1, 8.5 1" />
                <text x="21.5" y="13.25">E</text>
                </g>
                <g class="incremental" transform="translate(0,60)">
                <circle cx="22" cy="11" r="0.5" />
                <line x1="21.5" y1="11" x2="8.5" y2="11" />
                <path d="M 22 10.5 C 22 5.5, 16 1, 8.5 1" />
                <text x="21.5" y="13.25">E</text>
                </g>
                <g class="incremental" transform="translate(0,80)">
                <circle cx="22" cy="11" r="0.5" />
                <line x1="21.5" y1="11" x2="8.5" y2="11" />
                <path d="M 22 10.5 C 22 5.5, 16 1, 8.5 1" />
                <text x="21.5" y="13.25">E</text>
                </g>
                <g class="incremental" transform="translate(0,100)">
                <circle cx="22" cy="11" r="0.5" />
                <line x1="21.5" y1="11" x2="8.5" y2="11" />
                <path d="M 22 10.5 C 22 5.5, 16 1, 8.5 1" />
                <text x="21.5" y="13.25">E</text>
                </g>
            </g>
        </svg>
        </div>





Streams mit primitiven Daten und Objekten
------------------------------------------------

- :java:`Stream<T>` ist der Typ der Streams mit Objekten vom Typ :java:`T`
- Streams mit primitiven Daten:

  - :java:`IntStream`
  - :java:`LongStream`
  - :java:`DoubleStream`

  Dies Streams mit primitiven Daten arbeiten in vielen Fällen effizienter jedoch sind manche Operationen nur auf :java:`Object`\ -Streams erlaubt. „Primitive“ Streams können mit der Methode :java:`boxed` in :java:`Object`\ -Streams umgewandelt werden.

.. example::
    :class: incremental

    .. code:: java
        :number-lines:
        :class: copy-to-clipboard

        IntStream isPrim = IntStream.range(1, 10);
        Stream<Integer> isObj = isPrim.boxed();



Erzeugung von Streams
------------------------------------------------

.. deck::

    .. card::

        .. rubric:: Statische Methoden in :java:`Arrays`

        - Die Klasse :java:`java.util.Arrays` hat mehrere überladene statische stream-Methoden, mit denen Arrays in Ströme umgewandelt werden können.
        - Die Streams können Objekte oder primitive Daten enthalten.

        .. example::
            :class: incremental

            .. code:: java
                :number-lines:
                :class: copy-to-clipboard

                // Stream of primitive data:
                IntStream isP = Arrays.stream(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 });
                // Stream of objects:
                Stream<Integer> isO = Arrays.stream(
                    new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 }
                );

    .. card::

        .. rubric:: Statische Methoden in :java:`Stream`

        - Das Interface :java:`java.util.stream.Stream` enthält mehrere statische Methoden mit denen Streams erzeugt werden können.
        - Für die Klassen der Streams mit primitiven Werten (z.B. :java:`java.util.stream.IntStream`) gibt es äquivalente Methoden.

        .. deck::

            .. card::


                - Mit :java:`of` werden die übergebenen Wert in einen Stream gepackt.
                - Mit :java:`iterate` und :java:`generate` hat man eine einfache Möglichkeit unendliche Ströme zu erzeugen.

            .. card::

                .. example::
                    :class: incremental

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard

                        // Object-Stream 1, 2 ... 9, 0:
                        Stream<Integer> is1a = Stream.of(1,2,3,4,5,6,7,8,9,0);

                    .. code:: java
                        :number-lines: 2
                        :class: copy-to-clipboard incremental

                        // int-Stream 1, 2, ... 9, 0
                        IntStream is1b = IntStream.of(1,2,3,4,5,6,7,8,9,0);

                    .. code:: java
                        :number-lines: 3
                        :class: copy-to-clipboard incremental

                        // (infinite) Stream 1, 2, ...
                        Stream<Integer> is2 = Stream.iterate(1, ((x) -> x+1));

                    .. code:: java
                        :number-lines: 4
                        :class: copy-to-clipboard incremental

                        int[] z = new int[]{1};
                        Stream<Integer> is3 = Stream.generate((() -> z[0]++)); // (infinite) Stream 1, 2, ...

    .. card::

        .. rubric:: Statische range-Methoden in :java:`IntStream` und :java:`LongStream`

        Die Interfaces :java:`java.util.stream.IntStream` und :java:`java.util.stream.LongStream` enthalten jeweils zwei statische :java:`range`-Methoden mit denen Streams erzeugt werden können.

        .. example::
            :class: incremental

            .. code:: java
                :number-lines:
                :class: copy-to-clipboard

                IntStream isPrimA = IntStream.range(1, 10); // 1,2, .. 9
                IntStream isPrimA = IntStream.rangeClosed(1, 10); // 1,2, .. 9, 10

    .. card::

        .. rubric:: Nicht-statische Methoden der Collection-API

        Das Interface :java:`java.util.Collection` enthält die Methode stream mit der die jeweilige Kollektion in einen Stream umgewandelt werden kann.

        .. example::
            :class: incremental

            .. code:: java
                :number-lines:
                :class: copy-to-clipboard

                Stream<Integer> is = Arrays.asList(1,2,3,4,5,6,7,8,9,0).stream();



Verwendung von Streams
------------------------------------------------

.. deck::

    .. card::

        Streams werden typischerweise in einer Pipeline-artigen Struktur genutzt:

        .. class:: incremental-list

        1. Erzeugung
        2. Folge von Verarbeitungs-/Transformationsschritten
        3. Abschluss mit einer terminalen Operation

    .. card::

        .. rubric:: Verarbeitungsoperationen

        Verarbeitungs-Operationen transformieren die Elemente eines Streams. Man unterscheidet:

        .. class:: incremental-list list-with-explanations

        - zustandslose Operationen

          Transformieren die Elemente jeweils völlig unabhängig von allen anderen.
        - zustandsbehaftete Operationen

          Transformieren die Elemente abhängig von anderen.

    .. card::

        .. rubric:: Zustandslose Verarbeitungsoperationen

        .. deck::

            .. card::


                .. class:: incremental-list

                - :java:`map(Function<? super T,? extends R> mapper)`: Transformiert jedes Element in ein anderes.
                - :java:`filter(Predicate<? super T> predicate)`: Filtert Elemente heraus.
                - :java:`flatMap(Function<? super T,? extends Stream<? extends R>> mapper)`: Transformiert jedes Element in einen Stream und fügt die Streams zusammen.

            .. card::

                .. example::

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard

                        import java.util.List;
                        import java.util.stream.Collectors;
                        import java.util.stream.IntStream;

                        List<Integer> is = IntStream.range(1, 10)
                            .filter(i -> i % 2 != 0)
                            .peek(i -> System.out.print(i+ " "))
                            .map(i -> 10 * i)
                            .boxed()
                            .collect(Collectors.toList());
                        System.out.println(is);

                    .. container:: incremental

                        *Ausgabe:* :console:`1 3 5 7 9 [10, 30, 50, 70, 90]`

            .. card::

                .. example::

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard


                        import java.util.List;
                        import java.util.stream.Collectors;
                        import java.util.stream.IntStream;
                        import java.util.stream.Stream;

                        static Stream<Integer> range(int from, int to) {
                            return IntStream.range(from, to).boxed();
                        }

                        List<Integer> is = Stream.of(0, 1, 2)
                            .flatMap(i -> range(10 * i, 10 * i + 10))
                            .collect(Collectors.toList());


                    .. container:: incremental

                        *Ausgabe:* :console:`is ==> [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29]`

    .. card::

        .. rubric:: Zustandsbehaftete Verarbeitungsoperationen

        .. deck::

            .. card::

                .. class:: incremental-list

                - :java:`distinct()`: Entfernt Duplikate.
                - :java:`sorted()`: Sortiert die Elemente.
                - :java:`sorted(Comparator<? super T> comparator)`: Sortiert die Elemente mit einem gegebenen Comparator.
                - :java:`limit(long maxSize)`: Begrenzt die Anzahl der Elemente.
                - :java:`skip(long n)`: Überspringt die ersten n Elemente.

            .. card::

                .. example::

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard

                            import java.util.List;
                            import java.util.stream.Collectors;
                            import java.util.stream.Stream;

                            List<Integer> lst = Stream.of(9, 0, 3, 1, 7, 3, 4, 7, 2, 8, 5, 0, 6, 2)
                                .distinct()
                                .sorted((i, j) -> i - j)
                                .skip(1)
                                .limit(3)
                                .collect(Collectors.toList());

                    .. container:: incremental

                        *Ausgabe:* :console:`is ==> [1, 2, 3]`

    .. card::

        .. rubric:: Verarbeitungsoperationen

        Eine terminale Operation hat im Gegensatz zu den Verarbeitungsoperationen *keinen Stream* als
        Ergebnis.

        .. deck:: incremental

            .. card::

                .. rubric:: Terminale Operationen ohne Ergebnis

                .. class:: incremental-list list-with-explanations

                - :java:`forEach(Consumer<? super T> action)`

                  Wendet die übergebene Aktion auf alle Elemente des Streams an.

            .. card::

                .. rubric:: Terminale Operationen mit Ergebnis

                .. class:: incremental-list list-with-explanations

                - Operationen mit Array-Ergebnis: :java:`Stream => Array`

                  Operationen die den Stream in ein äquivalentes Array umwandeln.

                - Operationen mit Kollektions-Ergebnis: :java:`Stream => Kollektion`

                  Operationen die den Stream in eine äquivalente Kollektion umwandeln.

                - Operationen mit Einzel-Ergebnis: Aggregierende Operationen

                  Operationen die den Stream zu einem einzigen Wert verarbeiten.

            .. card::

                .. example::

                    .. rubric:: :java:`forEach`

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard

                        Stream.of(9, 0, 3, 1, 7, 3, 4, 7, 2, 8, 5, 0, 6, 2)
                            .distinct()
                            .sorted( (i,j) -> i-j )
                            .limit(3)
                            .forEach( System.out::println );

            .. card::

                .. example::

                    .. rubric:: :java:`toArray`

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard

                        int[] a = IntStream.range(1, 3).toArray();

                        Object[] a = Stream.of("1", "2", "3").map( Integer::parseInt )
                                .toArray();

                        Integer[] a = (Integer[]) Stream.of(1, 2, 3)
                                .toArray();

                        String[] a = Stream.of(1, 2, 3).map( (i) -> i.toString() )
                                .toArray( String[]::new ); // using generator

            .. card::

                .. supplemental::

                    .. rubric:: Terminale Operationen mit Kollektions-Ergebnis

                    - Die Methode :java:`collect` erzeugt eine Kollektion aus den Elementen des Streams.
                    - :java:`IntStream` und andere Streams mit primitiven Daten haben keine entsprechende Operation.
                    - Das Argument von :java:`collect` ist ein :java:`java.util.stream.Collector`. Die Erzeugung einer Kollektion ist damit Sonderfall einer aggregierenden Operation.
                    - Für die Erzeugung einer Kollektion verwendet man typischerweise einen vordefinierten :java:`Collector` aus der Klasse :java:`java.util.stream.Collectors`.
                    - Einfache Kollektionserzeuger in Collectors sind:

                        - :java:`toList()`
                        - :java:`toSet()`
                        - :java:`toCollection(Supplier<C> collectionFactory)`

                .. example::

                    .. rubric:: :java:`collect`

                    .. deck::

                        .. card::

                            .. code:: java
                                :number-lines:
                                :class: copy-to-clipboard

                                List<Integer> l1 = Stream.of(1, 2, 3).collect( Collectors.toList() );

                                List<Integer> l2 = IntStream.range(1, 4).boxed()
                                        .collect( Collectors.toList() );

                                Set<String> s1 = (Set<String>) Stream.of("1", "2", "3")
                                        .collect( Collectors.toSet());

                                Set<String> s2 = (Set<String>) Stream.of("1", "2", "3")
                                        .collect( Collectors.toCollection( HashSet::new) );

                        .. card::

                            .. code:: java
                                :number-lines:
                                :class: copy-to-clipboard

                                // Generating a map from a stream of strings

                                Map<String, Integer> m = Stream.of("1", "2", "3")
                                        .collect(
                                            Collectors.toMap(
                                                (s) -> s,
                                                Integer::parseInt
                                            )
                                        );

            .. card::

                In :java:`Collectors` finden sich **Kollektoren mit denen Maps erzeugt werden können**, die eine Gruppierung bzw. eine Partitionierung der Stream-Elemente darstellen:

                - :java:`static <T,K> Collector<T,?,Map<K,List<T>>> groupingBy(Function<? super T,? extends K> classifier)`

                  Gruppiert die Elemente entsprechend einer Klassifizierungsfunktion.

                - :java:`static <T> Collector<T,?,Map<Boolean,List<T>>> partitioningBy(Predicate<? super T> predicate)`

                  Partitioniert die Elemente entsprechend einem Prädikat.

            .. card::

                .. example::

                    .. rubric:: :java:`collect(groupingBy)`

                    .. supplemental::

                        .. rubric:: Hilfreiche Methoden

                        .. code:: java
                            :number-lines:
                            :class: copy-to-clipboard

                            import static java.util.stream.Collectors.groupingBy;
                            import static java.util.stream.Collectors.partitioningBy;
                            import static java.util.stream.Collectors.counting;

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard

                        Map<Integer, List<Integer>> groupedByMod3 = Stream.of(1, 2, 3, 4, 5, 6 ,7 ,8, 9)
                                .collect( groupingBy( (x) -> x%3 ) );

                    .. container:: incremental

                        *Ausgabe:* :console:`groupedByMod3 = {0=[3, 6, 9], 1=[1, 4, 7], 2=[2, 5,8]}`

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard incremental

                        Map<Integer, List<String>> groupedByLength = Stream.of(
                                    "one", "two", "three", "four", "five", "six", "seven", "eight")
                                .collect( groupingBy( (s) -> s.length() ) );

                    .. container:: incremental

                        *Ausgabe:* :console:`groupedByLength ==> {3=[one, two, six], 4=[four, five], 5=[three, seven, eight]}`

            .. card::

                Das Interface :java:`Stream` bzw. die Interfaces für Ströme primitiver Daten (:java:`IntStream`, etc.) bieten einige **einfache aggregierende Funktionen für Standardoperationen** auf allen Elementen des Stroms.

                .. example::
                    :class: incremental

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard

                        long count = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).count();

                        long sum = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).sum();

                        OptionalDouble av = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).average();

            .. card::

                Das Interface Stream bieten einige einfache **aggregierende Funktionen für den Test aller Elemente des Stroms** mit einem übergebenen Prädikat.

                .. example::
                    :class: incremental

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard

                        boolean anyEven = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                                .anyMatch( (x) -> x%2 == 0 );

                        boolean allEven = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                                .allMatch( (x) -> x%2 == 0 );

                        boolean noneEven = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                                .noneMatch( (x) -> x%2 == 0 );

            .. card::

                Das Interface :java:`Stream` bietet die Funktionen :java:`findFirst` und :java:`findAny` für die „Suche“ nach dem ersten bzw. irgendeinem Element in einem Stream.

                .. attention::
                    :class: incremental

                    Diese Methoden haben kein Prädikat als Parameter. Es empfiehlt sich darum den :java:`Stream` vorher mit dem entsprechenden Prädikat zu filtern.

                .. example::
                    :class: incremental

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard

                        Optional<Integer> firstEven = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                                .filter( (x) -> x%2 == 0 )
                                .findFirst();

                    .. container:: incremental

                        *Ausgabe:* :console:`firstEven ==> Optional[2]`

            .. card::

                Das Interface Stream bietet die Funktion

                    :java:`Optional<T> reduce(BinaryOperator<T> accumulator)`

                mit der eine Funktion auf jedes Element und das bisherige Zwischenergebnis  angewendet werden kann.

                Falls der erste Wert nicht der Startwert sein soll, verwendet man:

                    :java:`Optional<T> reduce(T identity, BinaryOperator<T> accumulator)`

            .. card::

                .. example::

                    .. rubric:: :java:`reduce`

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard

                        Optional<Integer> sumOfAll = Stream.of(1, 2, 3, 4, 5).reduce( (a, x) -> a+x );

                    .. container:: incremental

                        *Ausgabe:* :console:`sumOfAll ==> Optional[15]`

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard incremental

                        Optional<Integer> subOfAll = Stream.of(1, 2, 3, 4, 5).reduce( (a, x) -> a-x );

                    .. container:: incremental

                        *Ausgabe:* :console:`subOfAll ==> Optional[-13]`

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard incremental

                        int sumOfAllPlus100 = Stream.of(1, 2, 3, 4, 5)
                                .reduce(100, (a, x) -> a+x );

                    .. container:: incremental

                        *Ausgabe:* :console:`sumOfAllPlus100 ==> 115`

            .. card::

                Es gibt einen Kollektor mit dem String-Elemente zu einem String konkateniert werden können:

                    :java:`static Collector<CharSequence,?,String> joining(CharSequence delimiter)`

                .. example::
                    :class: incremental

                    .. rubric:: :java:`reduce`

                    .. code:: java
                        :number-lines:
                        :class: copy-to-clipboard

                        String concat = Stream.of("one", "two", "three")
                                .collect( joining("+") );

                    .. container:: incremental

                        *Ausgabe:* :console:`concat = one+two+three`



Streams - fortgeschrittene Konzepte
------------------------------------------------

.. deck::

    .. card::

        .. rubric:: Ausgewählte Eigenschaften des *Basisinterface* aller Streams

        Parallele und sequentielle Streams.

        .. code:: java
            :number-lines:

            package java.util.stream;

            public interface BaseStream<T, S extends BaseStream<T,S>> {
                /** Closes the stream, releasing any resources associated with it. */
                void close();

                /** Returns an equivalent stream that is parallel. */
                S parallel();
                /** Returns an equivalent stream that is sequential. */
                S sequential();
            }

        .. code:: java
            :number-lines: 13
            :class: incremental

            public interface Stream<T> extends BaseStream<T, Stream<T>> {
                // ...
            }

        .. supplemental::

            Die Interfacedefintion (:java:`BaseStream<T, S extends BaseStream<T,S>>`) ist eine Anwendung des CRTP; d. h. des *Curiously Recurring Template Pattern*\s. Bei diesem Idiom haben wir eine Klasse :java:`X`, die von einer  generischen Klasse oder einem generischen Interface :java:`S` abgeleitet wird, wobei die ableitende Klasse :java:`X` sich selber als Typparameter verwendet. Dies erlaubt die Definition einer Fluent-API, bei der Methoden, die in der Basisklasse definiert sind, den abgeleiteten Typ zurückgeben.

    .. card::

        .. rubric:: Erzeugen von eigenen Streams mittels `StreamSupport <https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/stream/StreamSupport.html>`__

        Die Implementierung des Interfaces :java:`Stream<T>` ist ggf. sehr aufwändig. Alternativ kann die Klasse :java:`StreamSupport` verwendet werden, um auf einem :java:`Spliterator` basierende Streams zu erzeugen.

        .. code:: java
            :number-lines:

            package java.util.stream;

            public final class StreamSupport {

                /** Creates a new sequential or parallel Stream from a Spliterator. */
                static <T> Stream<T> stream(Spliterator<T> spliterator, boolean parallel);

                // ...
            }




Java :java:`Optional`\ s
------------------------------------------------

Instanzen der Klasse :java:`java.util.Optional<T>` (bzw. :java:`java.util.OptionalInt` etc.) **repräsentieren Werte die vorhanden sind oder
auch nicht**.

Insbesondere :java:`java.util.Optional<T>` kann/sollte anstelle von :java:`null` verwendet werden, in Fällen in denen unter bestimmten Umständen kein sinnvoller Wert angegeben werden kann.

.. deck::

    .. card::

        .. remark::

            Es gibt moderne Programmiersprachen, die auf :java:`null` komplett verzichten und stattdessen immer auf ``Optionals`` oder ähnliche Konstrukte setzen.

    .. card::

        .. example::

            .. code:: java
                :number-lines:
                :class: copy-to-clipboard

                static Optional<Integer> min(int[] a ) {
                    if(a == null || a.length == 0)
                        return Optional.empty();

                    int min = a[0];
                    for(int x: a) { if (x < min) { min = x; } }
                    return Optional.of(min);
                }



.. class:: exercises

Übung - Streams Grundlagen
----------------------------

.. exercise:: Streams und Lambda Ausdrücke

    .. code:: java
        :number-lines:
        :class: copy-to-clipboard

        void main() {
            List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana", "Eve");
            List<Integer> grades = Arrays.asList(85, 42, 91, 67, 55);

            // TODO 1: Using a lambda, filter the grades list to only keep grades >= 60
            // and print the passing grades.
            // Hint: Use stream(), filter(), and forEach().


            // TODO 2: Create a Predicate<Integer> lambda called `isExcellent`
            // that returns true if a grade is 90 or above. Then print all excellent grades.


            // TODO 3: Create a Function<Integer, String> lambda called `toLetterGrade`
            // that converts a numeric grade to a letter:
            //   90+  → "A"; 75+  → "B" ;  60+  → "C"; below 60 → "F"
            // Then print each grade alongside its letter grade.


            // TODO 4: Using a lambda, calculate and print the average of all grades.
            // Hint: Use stream() and mapToInt().
        }


.. class:: exercises

Übung
--------

.. exercise:: Java Streams

    .. remark::

        Verwenden Sie ausschließlich Streams und Lambda-Ausdrücke.

    1. Schreiben Sie eine Methode :java:`int sumOfSquares(int[] a)` die die Elemente des Arrays quadriert und dann die Summe berechnet.

    2. Schreiben Sie eine Methode :java:`int sumOfSquaresEven(int[] a)` die die Elemente des Arrays quadriert, und dann die Summe berechnet für alle Elemente die gerade sind.

    3. Schreiben Sie eine Methode, die eine Liste von Strings (:java:`List<String>`) in eine flache Liste von Zeichen (:java:`List<Integer>`) umwandelt.

    4. Schreiben Sie eine Methode, die die Zahlen von :java:`1` bis :java:`Integer.MAX_VALUE` addiert. Nutzen Sie :java:`IntStream.range()` um die Zahlen zu iterieren. Messen Sie die Ausführungsdauer für die *sequentielle* und *parallele* Ausführung (siehe Anhang für eine entsprechende Methode zur Zeitmessung.)


    .. supplemental::

        Um die Ausführungsdauer Ihrer Methode zu messen, können Sie folgenden Methode verwenden:

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            void time(Runnable r) {
                final var startTime = System.nanoTime();
                r.run();
                final var endTime = System.nanoTime();
                System.out.println("elapsed time: "+(endTime - startTime));
            }

        Ein Aufruf der Methode :java:`time` könnte dann so aussehen:

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            time(() -> System.out.println(sumOfSquares(new int[]{1,2,3,4,5,6,7,8,9,0})));

    .. solution::
        :pwd: QuadrierteStreams

        1. Lösung

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            var s = Arrays.stream(new int[]{1,2,3})
            s.map(x -> x * x).reduce(0, (x,y) -> x + y)
            // s.map(x -> x * x).sum()

        2. Lösung

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            var s = Arrays.stream(new int[]{1,2,3,4})
            s
                .map(x -> x * x)
                .filter(x -> x % 2 == 0 )
                .reduce(0, (x,y) -> x + y)

        3. Lösung

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            //var s = Stream.of("Hello", "World")
            var s = List.of("Hello", "World").stream()
            //Stream<Integer> sc =  s.flatMap((String x) -> x.chars().boxed())
            var l = s.flatMap((String x) -> x.chars().boxed()).collect(Collectors.toList())

        4. Lösung

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            // sequential
            time (() -> IntStream.range(1,Integer.MAX_VALUE).map(x -> x -1).reduce(0, (x,y) -> x + y));

            // parallel
            time (() -> IntStream.range(1,Integer.MAX_VALUE).parallel().map(x -> x -1).reduce(0, (x,y) -> x + y));

            Depending on the number of cores in your machine, the parallel version should be faster than the sequential version.




Herausforderungen gelöst?
-------------------------

- Streams unterstützen die korrekte Verarbeitung von (Massen-)Daten durch die Anwendung von funktionalen und deklarativen Konzepten mit dem Ziel einer möglichst geringe Repräsentationslücke (:eng:`Low representational gap`).








Vergleich von Java's Stream-API mit Scala's Stream-API
------------------------------------------------------
