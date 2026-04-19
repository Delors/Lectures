.. meta::
    :lang: de
    :author: Michael Eichberg
    :keywords: "Java", "Streams", "Funktional Programmierung"
    :description lang=de: Java Streams
    :id: lecture-prog-java-streams
    :first-slide: last-viewed
    :master-password: SehrSchwierig?
    :theme: colored
    :svg-defs:
        <marker id="pipe-arrow" viewBox="0 0 6 6" refX="5.5" refY="3"
                markerWidth="5" markerHeight="5" orient="auto">
            <path d="M0,0.5 L5,3 L0,5.5" fill="none"
                stroke="currentColor" stroke-width="1.2"/>
        </marker>
        <marker id="light-pipe-arrow" viewBox="0 0 6 6" refX="5.5" refY="3"
                markerWidth="5" markerHeight="5" orient="auto">
            <path d="M0,0.5 L5,3 L0,5.5" fill="none"
                style="stroke:oklch(from currentColor 0.8 c h);" stroke-width="1.1"/>
        </marker>
        <marker id="impl-arrow" viewBox="0 0 6 6" refX="5.5" refY="3"
                markerWidth="5" markerHeight="5" orient="auto">
            <path d="M0,0.5 L5,3 L0,5.5" fill="none"
                stroke="currentColor" stroke-width="1.2"/>
        </marker>

.. include:: ../docutils.defs



Java Stream API
===========================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0

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

    :Quellen:

        #. `Dokumentation des JDK <https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/stream/package-summary.html>`__ und `JEP 485 <https://openjdk.org/jeps/485>`__
        #. Dokumentation der Scala (2.13 bzw. 3) Standardbibliothek;

           `Aniefiok Akpan; Streams in Scala, July 2023 <https://blog.lunatech.com/posts/2023-07-28-streams-in-scala--an-introductory-guide>`__
        #. `Das Rust Buch <https://doc.rust-lang.org/book/ch13-02-iterators.html>`__
        #.  `Th. Letschert <https://homepages.thm.de/~hg51/Veranstaltungen/A_D/Folien/java-8-kurzeinfuehrung.pdf>`__  bzgl. der konkreten *Java Streams API (Deepdive)*

    :Externe Links:

        Die folgenden Youtube Videos besprechen Stream Gatherers sehr tiefgehend.

        - `Better Java Streams with Gatherers - JEP Cafe #23 <https://www.youtube.com/watch?v=jqUhObgDd5Q>`__
        - `Deep Dive into Gatherers - JEP Cafe #24 <https://www.youtube.com/watch?v=fgQQIV3B-uo>`__

    :KI Verwendung:

        Bei der Erstellung der Folien wurden KI Assistenten (insbesondere Claude Opus 4.6/4.7) unterstützend eingesetzt. Dies erfolgte insbesondere, um effizient die Grafiken (d. h. die SVG Dateien)  zu generieren, oder um sich Übersichtstabellen generieren zu lassen. Weiterhin wurde KI zur allgemeinen Qualitätssicherung eingesetzt. Inhalte, die ggf. von der KI vorgeschlagen wurden, wurden im Falle der Übernahme explizit validiert.

        *KI wurde nicht verwendet* für den Aufbau, die Struktur und die Auswahl der grundlegenden Inhalte.

.. global-information:: Funktionale Programmierung in Java
      :symbol: Funktionale Prog. in Java

      .. list-table::
        :header-rows: 1
        :widths: 20 35 40
        :width: 100%

        *   -   Konzept
            -   Beschreibung
            -   Beispiel
        *   -   Lambda-Ausdruck
            -   Eine anonyme Funktion, die als Wert übergeben werden kann. Syntax:

                -   :java:`(Parameter) -> Ausdruck`

                -   :java:`(Parameter) -> { Anweisungen; }`
            -   :java:`(x, y) -> x + y`

                :java:`x -> x * x`

                :java:`() -> 42`

                :java:`(a, b) -> { int s = a + b; return s * 2; }`
        *   -   Methoden-Referenz
            -   Kurzschreibweise für Lambdas, die eine bestehende Methode aufrufen. Varianten:

                - statisch: :java:`Klasse::methode`
                - auf Instanz: :java:`objekt::methode`
                - auf Typ: :java:`Klasse::instanzMethode`
                - Konstruktor: :java:`Klasse::new`
            -   :java:`Integer::parseInt`

                :java:`System.out::println`

                :java:`String::length`

                :java:`ArrayList::new`
        *   -   Funktionales Interface
            -   Ein Interface mit genau *einer* abstrakten Methode; ermöglicht die Verwendung von Lambda-Ausdrücken.
            -   .. code:: java

                    @FunctionalInterface
                    interface MyFunc {
                        int apply(int a, int b);
                    }
        *   -   :java:`Predicate<T>`
            -   Funktionales Interface: Prüft eine Bedingung auf einem Wert und gibt :java:`boolean` zurück.
            -   :java:`Predicate<Integer> isEven = x -> x % 2 == 0;`
        *   -   :java:`Function<T,R>`
            -   Funktionales Interface: Bildet einen Wert vom Typ :java:`T` auf einen Wert vom Typ :java:`R` ab.
            -   :java:`Function<String,Integer> len = String::length;`
        *   -   :java:`Consumer<T>`
            -   Funktionales Interface: Konsumiert einen Wert, gibt nichts zurück (:java:`void`).
            -   :java:`Consumer<String> printer = System.out::println;`
        *   -   :java:`Supplier<T>`
            -   Funktionales Interface: Liefert einen Wert ohne Eingabe.
            -   :java:`Supplier<Double> random = Math::random;`
        *   -   :java:`BinaryOperator<T>`
            -   Funktionales Interface: Verknüpft zwei Werte desselben Typs zu einem Ergebnis des entsprechenden Typs.
            -   :java:`BinaryOperator<String> wrap = (a, b) -> b + a + b;`
        *   -   :java:`Comparator<T>`
            -   Funktionales Interface für den Vergleich von zwei Objekten; gibt einen :java:`int`-Wert zurück.
            -   .. code:: java

                    Comparator<String> byLen =
                        (a, b) -> a.length() - b.length();
                    Comparator.comparing(String::length)

        *   -   Funktionen höherer Ordnung
            -   Funktionen, die andere Funktionen als Parameter nehmen oder als Ergebnis zurückgeben. Grundlage aller Stream-Operationen.
            -   :java:`stream.filter(x -> x > 0)` :java:`.map(x -> x * 2)`
        *   -   Closure
            -   Ein Lambda-Ausdruck, der auf Variablen aus dem umgebenden Kontext zugreift. Diese Variablen müssen :java:`final` oder *effektiv final* sein.
            -   .. code:: java

                    final int factor = 10;
                    Stream.of(3,4,3).
                        map(x -> x * factor).
                        toList();
        *   -   :java:`Optional<T>`
            -   Ein Container, der einen Wert enthalten kann oder leer ist. Ersetzt die Verwendung von :java:`null`.
            -   :java:`Optional.of(42)`

                :java:`Optional.empty()`



.. class:: new-section transition-fade

Motivation
-----------------------------------------------



:peripheral:`(Aktuelle)` Herausforderungen bei der Datenverarbeitung
----------------------------------------------------------------------

.. deck::

    .. card::

        - Große Datenmengen,

          - die nicht (ohne weiteres) in den Speicher passen oder
          - bei denen es keinen Sinn macht diese vorab vollständig in den Speicher zu laden, da immer nur ein kleiner Abschnitt verarbeitet werden muss.
        - Daten, die kontinuierlich verarbeitet werden müssen.

    .. card::

        .. example:: Konkrete Szenarien der Datenverarbeitung

            - HTTP Request/Response Handling (Audio/Video Streaming)
            - Ver-/Entschlüsselung von großen Datenmengen
            - kontinuierliche Verarbeitung von allg. Daten (z. B. Finanzdaten/-transaktionen) oder von (IoT) Sensoren
            - Verarbeitung von Ereignissen (Event)
            - Aufbereitung großer Wörterbücher (z. B. für Passwortwiederherstellung)
            - Verarbeitung von AI Streaming Responses
            - Verarbeitung großer Logdateien

    .. card::

        - Parallele Verarbeitung und effiziente Nutzung von modernen CPU-Architekturen

        .. deck:: center-content

            .. card::

                .. raw:: html

                    <div style="width:71.5ch; height: 38.4ch">
                    <svg viewBox="2 0 110 58"
                            font-size="1.5"
                            version="1.1" xmlns="http://www.w3.org/2000/svg">

                    <g>
                        <rect x="2" y="0" rx="1" width="108" height="58" fill="white" />
                        <a href="https://www.amd.com/en/products/specifications/server-processor.html" target="_blank"><text x="60.0" y="3.5" text-anchor="middle"
                                font-size="2" font-weight="bold" fill="#333">AMD EPYC Server CPUs — Entwicklung 2017–2026</text></a>
                        <rect x="18" y="5" width="84" height="43"
                                fill="#FAFAFA" stroke="#ccc" stroke-width="0.12"/>

                        <line x1="18" y1="48.00" x2="102" y2="48.00" stroke="#e0e0e0" stroke-width="0.08" stroke-dasharray="0.3,0.6"/>
                        <line x1="18" y1="40.83" x2="102" y2="40.83" stroke="#e0e0e0" stroke-width="0.08" stroke-dasharray="0.3,0.6"/>
                        <line x1="18" y1="33.67" x2="102" y2="33.67" stroke="#e0e0e0" stroke-width="0.08" stroke-dasharray="0.3,0.6"/>
                        <line x1="18" y1="26.50" x2="102" y2="26.50" stroke="#e0e0e0" stroke-width="0.08" stroke-dasharray="0.3,0.6"/>
                        <line x1="18" y1="19.33" x2="102" y2="19.33" stroke="#e0e0e0" stroke-width="0.08" stroke-dasharray="0.3,0.6"/>
                        <line x1="18" y1="12.17" x2="102" y2="12.17" stroke="#e0e0e0" stroke-width="0.08" stroke-dasharray="0.3,0.6"/>
                        <line x1="18" y1="5.00" x2="102" y2="5.00" stroke="#e0e0e0" stroke-width="0.08" stroke-dasharray="0.3,0.6"/>

                        <line x1="21.53" y1="5" x2="21.53" y2="48" stroke="lightgray" stroke-width="0.12" stroke-dasharray="0.5,0.5"/>
                        <line x1="21.53" y1="48" x2="21.53" y2="48.8" stroke="gray" stroke-width="0.12"/>
                        <line x1="39.88" y1="5" x2="39.88" y2="48" stroke="lightgray" stroke-width="0.12" stroke-dasharray="0.5,0.5"/>
                        <line x1="39.88" y1="48" x2="39.88" y2="48.8" stroke="gray" stroke-width="0.12"/>
                        <line x1="53.29" y1="5" x2="53.29" y2="48" stroke="lightgray" stroke-width="0.12" stroke-dasharray="0.5,0.5"/>
                        <line x1="53.29" y1="48" x2="53.29" y2="48.8" stroke="gray" stroke-width="0.12"/>
                        <line x1="67.41" y1="5" x2="67.41" y2="48" stroke="lightgray" stroke-width="0.12" stroke-dasharray="0.5,0.5"/>
                        <line x1="67.41" y1="48" x2="67.41" y2="48.8" stroke="gray" stroke-width="0.12"/>
                        <line x1="72.35" y1="5" x2="72.35" y2="48" stroke="lightgray" stroke-width="0.12" stroke-dasharray="0.5,0.5"/>
                        <line x1="72.35" y1="48" x2="72.35" y2="48.8" stroke="gray" stroke-width="0.12"/>
                        <line x1="83.65" y1="5" x2="83.65" y2="48" stroke="lightgray" stroke-width="0.12" stroke-dasharray="0.5,0.5"/>
                        <line x1="83.65" y1="48" x2="83.65" y2="48.8" stroke="gray" stroke-width="0.12"/>
                        <line x1="83.65" y1="5" x2="83.65" y2="48" stroke="lightgray" stroke-width="0.12" stroke-dasharray="0.5,0.5"/>
                        <line x1="83.65" y1="48" x2="83.65" y2="48.8" stroke="gray" stroke-width="0.12"/>
                        <line x1="100.59" y1="5" x2="100.59" y2="48" stroke="lightgray" stroke-width="0.12" stroke-dasharray="0.5,0.5"/>
                        <line x1="100.59" y1="48" x2="100.59" y2="48.8" stroke="gray" stroke-width="0.12"/>
                        <line x1="83.65" y1="48.8" x2="81.65" y2="49.7" stroke="gray" stroke-width="0.08"/>
                        <line x1="83.65" y1="48.8" x2="85.65" y2="49.7" stroke="gray" stroke-width="0.08"/>
                        <text x="21.53" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="black">Naples</text>
                        <text x="21.53" y="52.6" text-anchor="middle" font-size="1.2" fill="black">7601</text>
                        <text x="21.53" y="54.2" text-anchor="middle" font-size="1.2" fill="gray">06.2017</text>
                        <text x="39.88" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="black">Rome</text>
                        <text x="39.88" y="52.6" text-anchor="middle" font-size="1.2" fill="black">7742</text>
                        <text x="39.88" y="54.2" text-anchor="middle" font-size="1.2" fill="gray">08.2019</text>
                        <text x="53.29" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="black">Milan</text>
                        <text x="53.29" y="52.6" text-anchor="middle" font-size="1.2" fill="black">7763</text>
                        <text x="53.29" y="54.2" text-anchor="middle" font-size="1.2" fill="gray">03.2021</text>
                        <text x="67.41" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="black">Genoa</text>
                        <text x="67.41" y="52.6" text-anchor="middle" font-size="1.2" fill="black">9654</text>
                        <text x="67.41" y="54.2" text-anchor="middle" font-size="1.2" fill="gray">11.2022</text>
                        <g transform="translate(2,0)">
                        <text x="72.35" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="#666666">Bergamo</text>
                        <text x="72.35" y="52.6" text-anchor="middle" font-size="1.2" fill="black">9754</text>
                        <text x="72.35" y="54.2" text-anchor="middle" font-size="1.2" fill="gray">06.2023</text>
                        </g>
                        <g transform="translate(2,0)">
                            <text x="79.15" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="black">Turin</text>
                            <text x="79.15" y="52.6" text-anchor="middle" font-size="1.2" fill="black">9755</text>
                            <text x="79.15" y="54.2" text-anchor="middle" font-size="1.2" fill="gray">10.2024</text>
                        </g>
                        <g>
                        <text x="88.15" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="#666666">Turin Dense</text>
                        <text x="88.15" y="52.6" text-anchor="middle" font-size="1.2" fill="#666666">9965</text>
                        <text x="88.15" y="54.2" text-anchor="middle" font-size="1.2" fill="gray">10.2024</text>
                        </g>
                        <text x="100.59" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="#AD7733">Venice</text>
                        <text x="100.59" y="52.6" text-anchor="middle" font-size="1.2" fill="gray">Voraussichtlich</text>
                        <text x="100.59" y="54.2" text-anchor="middle" font-size="1.2" fill="gray">10.2026</text>

                        <text x="20" y="3.8" text-anchor="end" font-size="1.4" font-weight="bold" fill="#0066CC">Perf/Core</text>
                        <line x1="17.4" y1="48.00" x2="18" y2="48.00" stroke="#0066CC" stroke-width="0.12"/>
                        <text x="17" y="48.45" text-anchor="end" font-size="1.2" fill="#0066CC">0</text>
                        <line x1="17.4" y1="40.83" x2="18" y2="40.83" stroke="#0066CC" stroke-width="0.12"/>
                        <text x="17" y="41.28" text-anchor="end" font-size="1.2" fill="#0066CC">2</text>
                        <line x1="17.4" y1="33.67" x2="18" y2="33.67" stroke="#0066CC" stroke-width="0.12"/>
                        <text x="17" y="34.12" text-anchor="end" font-size="1.2" fill="#0066CC">4</text>
                        <line x1="17.4" y1="26.50" x2="18" y2="26.50" stroke="#0066CC" stroke-width="0.12"/>
                        <text x="17" y="26.95" text-anchor="end" font-size="1.2" fill="#0066CC">6</text>
                        <line x1="17.4" y1="19.33" x2="18" y2="19.33" stroke="#0066CC" stroke-width="0.12"/>
                        <text x="17" y="19.78" text-anchor="end" font-size="1.2" fill="#0066CC">8</text>
                        <line x1="17.4" y1="12.17" x2="18" y2="12.17" stroke="#0066CC" stroke-width="0.12"/>
                        <text x="17" y="12.62" text-anchor="end" font-size="1.2" fill="#0066CC">10</text>
                        <line x1="17.4" y1="5.00" x2="18" y2="5.00" stroke="#0066CC" stroke-width="0.12"/>
                        <text x="17" y="5.45" text-anchor="end" font-size="1.2" fill="#0066CC">12</text>

                        <!-- Perf/Core: gestrichelte Linie -->
                        <polyline points="21.53,32.10 39.88,28.91 53.29,23.59 67.41,14.48 83.65,8.53"
                                    fill="none" stroke="#0066CC" stroke-width="0.35"
                                    stroke-dasharray="1.5,0.8" stroke-linecap="round"/>
                        <polyline points="72.35,20.54 83.65,17.58"
                                    fill="none" stroke="#0088FF" stroke-width="0.35"
                                    stroke-dasharray="1.5,0.8" stroke-linecap="round"/>
                        <circle cx="21.53" cy="32.10" r="0.55" fill="#0066CC"/>
                        <text x="21.53" y="31.00" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#0066CC">4.4</text>
                        <circle cx="39.88" cy="28.91" r="0.55" fill="#0066CC"/>
                        <text x="39.88" y="27.81" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#0066CC">5.3</text>
                        <circle cx="53.29" cy="23.59" r="0.55" fill="#0066CC"/>
                        <text x="53.29" y="22.49" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#0066CC">6.8</text>
                        <circle cx="67.41" cy="14.48" r="0.55" fill="#0066CC"/>
                        <text x="67.41" y="13.38" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#0066CC">9.4</text>
                        <circle cx="72.35" cy="20.54" r="0.55" fill="#0088FF"/>
                        <text x="72.35" y="19.44" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#0088FF">7.7</text>
                        <circle cx="83.65" cy="8.53" r="0.55" fill="#0066CC"/>
                        <text x="83.65" y="7.43" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#0066CC">11.0</text>
                        <circle cx="83.65" cy="17.58" r="0.55" fill="#0088FF"/>
                        <text x="85.45" y="18.08" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#0088FF">8.5</text>

                        <!-- Legende -->
                        <g transform="translate(0,-8)">
                            <line x1="20" y1="64.5" x2="26" y2="64.5"
                                    stroke="#0066CC" stroke-width="0.35" stroke-dasharray="1.5,0.8"/>
                            <circle cx="23" cy="64.5" r="0.45" fill="#0066CC"/>
                            <text x="27" y="65" font-size="1.2" fill="#0066CC">SPECint 2017 (Baseline)/#Cores</text>
                        </g>
                    </g>
                    <g class="incremental">
                        <text x="13" y="3.8" text-anchor="end" font-size="1.4" font-weight="bold" fill="#CC3300">SPECint Rate</text>
                        <line x1="17.4" y1="48.00" x2="18" y2="48.00" stroke="#CC3300" stroke-width="0.12"/>
                        <text x="10.2" y="48.45" text-anchor="end" font-size="1.2" fill="#CC3300">0</text>
                        <line x1="17.4" y1="40.83" x2="18" y2="40.83" stroke="#CC3300" stroke-width="0.12"/>
                        <text x="10.2" y="41.28" text-anchor="end" font-size="1.2" fill="#CC3300">300</text>
                        <line x1="17.4" y1="33.67" x2="18" y2="33.67" stroke="#CC3300" stroke-width="0.12"/>
                        <text x="10.2" y="34.12" text-anchor="end" font-size="1.2" fill="#CC3300">600</text>
                        <line x1="17.4" y1="26.50" x2="18" y2="26.50" stroke="#CC3300" stroke-width="0.12"/>
                        <text x="10.2" y="26.95" text-anchor="end" font-size="1.2" fill="#CC3300">900</text>
                        <line x1="17.4" y1="19.33" x2="18" y2="19.33" stroke="#CC3300" stroke-width="0.12"/>
                        <text x="10.2" y="19.78" text-anchor="end" font-size="1.2" fill="#CC3300">1200</text>
                        <line x1="17.4" y1="12.17" x2="18" y2="12.17" stroke="#CC3300" stroke-width="0.12"/>
                        <text x="10.2" y="12.62" text-anchor="end" font-size="1.2" fill="#CC3300">1500</text>
                        <line x1="17.4" y1="5.00" x2="18" y2="5.00" stroke="#CC3300" stroke-width="0.12"/>
                        <text x="10.2" y="5.45" text-anchor="end" font-size="1.2" fill="#CC3300">1800</text>

                        <!-- SPECint Rate: gepunktete Linie -->
                        <polyline points="21.53,44.61 39.88,39.85 53.29,37.58 67.41,26.55 83.65,14.32"
                                    fill="none" stroke="#CC3300" stroke-width="0.35"
                                    stroke-dasharray="0.3,0.6" stroke-linecap="round"/>
                        <polyline points="72.35,24.56 83.65,9.06"
                                    fill="none" stroke="#FF4400" stroke-width="0.35"
                                    stroke-dasharray="0.3,0.6" stroke-linecap="round"/>
                        <circle cx="21.53" cy="44.61" r="0.55" fill="#CC3300"/>
                        <text x="21.53" y="46.51" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#CC3300">142</text>
                        <circle cx="39.88" cy="39.85" r="0.55" fill="#CC3300"/>
                        <text x="39.88" y="38.75" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#CC3300">341</text>
                        <circle cx="53.29" cy="37.58" r="0.55" fill="#CC3300"/>
                        <text x="53.29" y="39.48" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#CC3300">436</text>
                        <circle cx="67.41" cy="26.55" r="0.55" fill="#CC3300"/>
                        <text x="67.41" y="25.45" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#CC3300">898</text>
                        <circle cx="72.35" cy="24.56" r="0.55" fill="#FF4400"/>
                        <text x="74.35" y="24.9" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#FF4400">981</text>
                        <circle cx="83.65" cy="14.32" r="0.55" fill="#CC3300"/>
                        <text x="81.5" y="14.58" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#CC3300">1410</text>
                        <circle cx="83.65" cy="9.06" r="0.55" fill="#FF4400"/>
                        <text x="85.75" y="9.41" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#FF4400">1630</text>

                        <!-- Legende -->
                        <g transform="translate(30,-10)">
                            <line x1="20" y1="66.5" x2="26" y2="66.5"
                                    stroke="#CC3300" stroke-width="0.35" stroke-dasharray="0.3,0.6"/>
                            <circle cx="23" cy="66.5" r="0.45" fill="#CC3300"/>
                            <text x="27" y="67" font-size="1.2" fill="#CC3300">SPECint 2017 (Baseline)</text>
                        </g>
                    </g>

                    <g class="incremental">
                        <text x="101" y="3.8" text-anchor="start" font-size="1.4" font-weight="bold" fill="#9900CC">#Cores</text>
                        <line x1="102" y1="48.00" x2="102.6" y2="48.00" stroke="#9900CC" stroke-width="0.12"/>
                        <text x="103" y="48.45" text-anchor="start" font-size="1.2" fill="#9900CC">0</text>
                        <line x1="102" y1="42.62" x2="102.6" y2="42.62" stroke="#9900CC" stroke-width="0.12"/>
                        <text x="103" y="43.08" text-anchor="start" font-size="1.2" fill="#9900CC">32</text>
                        <line x1="102" y1="37.25" x2="102.6" y2="37.25" stroke="#9900CC" stroke-width="0.12"/>
                        <text x="103" y="37.70" text-anchor="start" font-size="1.2" fill="#9900CC">64</text>
                        <line x1="102" y1="31.88" x2="102.6" y2="31.88" stroke="#9900CC" stroke-width="0.12"/>
                        <text x="103" y="32.33" text-anchor="start" font-size="1.2" fill="#9900CC">96</text>
                        <line x1="102" y1="26.50" x2="102.6" y2="26.50" stroke="#9900CC" stroke-width="0.12"/>
                        <text x="103" y="26.95" text-anchor="start" font-size="1.2" fill="#9900CC">128</text>
                        <line x1="102" y1="21.12" x2="102.6" y2="21.12" stroke="#9900CC" stroke-width="0.12"/>
                        <text x="103" y="21.57" text-anchor="start" font-size="1.2" fill="#9900CC">160</text>
                        <line x1="102" y1="15.75" x2="102.6" y2="15.75" stroke="#9900CC" stroke-width="0.12"/>
                        <text x="103" y="16.20" text-anchor="start" font-size="1.2" fill="#9900CC">192</text>
                        <line x1="102" y1="10.38" x2="102.6" y2="10.38" stroke="#9900CC" stroke-width="0.12"/>
                        <text x="103" y="10.82" text-anchor="start" font-size="1.2" fill="#9900CC">224</text>
                        <line x1="102" y1="5.00" x2="102.6" y2="5.00" stroke="#9900CC" stroke-width="0.12"/>
                        <text x="103" y="5.45" text-anchor="start" font-size="1.2" fill="#9900CC">256</text>

                        <!-- #Cores: Strich-Punkt-Linie -->
                        <polyline points="21.53,42.62 39.88,37.25 53.29,37.25 67.41,31.88 83.65,26.50"
                                    fill="none" stroke="#9900CC" stroke-width="0.35"
                                    stroke-dasharray="2,0.4,0.3,0.4" stroke-linecap="round"/>
                        <polyline points="72.35,26.50 83.65,15.75 100.59,5.00"
                                    fill="none" stroke="#CC44FF" stroke-width="0.35"
                                    stroke-dasharray="2,0.4,0.3,0.4" stroke-linecap="round"/>
                        <circle cx="21.53" cy="42.62" r="0.55" fill="#9900CC"/>
                        <text x="21.53" y="41.52" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#9900CC">32</text>
                        <circle cx="39.88" cy="37.25" r="0.55" fill="#9900CC"/>
                        <text x="39.88" y="36.15" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#9900CC">64</text>
                        <circle cx="53.29" cy="37.25" r="0.55" fill="#9900CC"/>
                        <text x="53.29" y="36.15" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#9900CC">64</text>
                        <circle cx="67.41" cy="31.88" r="0.55" fill="#9900CC"/>
                        <text x="67.41" y="30.77" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#9900CC">96</text>
                        <circle cx="72.35" cy="26.50" r="0.55" fill="#CC44FF"/>
                        <text x="72.35" y="28.40" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#CC44FF">128</text>
                        <circle cx="83.65" cy="26.50" r="0.55" fill="#9900CC"/>
                        <text x="83.65" y="28.40" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#9900CC">128</text>
                        <circle cx="83.65" cy="15.75" r="0.55" fill="#CC44FF"/>
                        <text x="85.5" y="16.2" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#CC44FF">192</text>
                        <circle cx="100.59" cy="5.00" r="0.55" fill="#9900CC"/>

                        <!-- Legende -->
                        <g transform="translate(20,-8)">
                            <line x1="55" y1="64.5" x2="61" y2="64.5"
                                    stroke="#9900CC" stroke-width="0.35" stroke-dasharray="2,0.4,0.3,0.4"/>
                            <circle cx="58" cy="64.5" r="0.45" fill="#9900CC"/>
                            <text x="62" y="65" font-size="1.2" fill="#9900CC">#Cores</text>
                        </g>
                    </g>
                    </svg>
                    </div>

        .. supplemental::

            Wenn man sich die Performance anschaut, dann hat sich die Leistung pro Core (für die Hauptlinie der EPYC CPUs) von 2017 bis 2024 um etwa den Faktor 2.5 verbessert, während die Rechenleistung pro CPU insgesamt um den Faktor 10 gestiegen ist. Der Gesamtanstieg ist somit wesentlich auf die Steigerung der Anzahl der Cores (Faktor 4) zurückzuführen.

            Insbesondere die korrekte und effiziente Nutzung mehrere Threads (d. h. von nebenläufiger Programmierung) ist im Allgemeinen schwierig. Die Verwendung von (soweit möglich) „transparenter“ Parallelisierung ist dabei sehr hilfreich.
            (Z. B. mit Java Parallel Streams oder :scala:`.par` in Scala ist eine weitgehend transparente Parallelisierung möglich.)

    .. card::

        - Entwicklung von Software nahe am Domänenmodell, um eine korrekte, leicht wartbare und erweiterbare Implementierung zu unterstützen.

          .. deck:: incremental

            .. card::

                .. remark::

                    Das Ziel sollte (immer) eine möglichst geringe Repräsentationslücke (:eng:`Low representational gap`) zwischen Code und Domänenmodell sein.

            .. card::

                .. scrollable::
                    :height: 14lh

                    .. example:: Finden der besten Studierenden

                        .. global-information:: Studierende Auswählen - Beispiel
                            :symbol: Beispiel: Studierendenauswahl
                            :type: slide
                            :embed:

                            .. class:: dd-margin-left-6em

                            :Eingabe: (Flache) Liste von allen Studierenden
                            :Ausgabe: (Flache) sortierte Liste von förderungswürdigen Studierenden nach Förderwürdigkeit
                            :Logik:

                                #. Filtere aus allen Studierenden diejenigen heraus, die bereits ein Stipendium haben,
                                #. gruppiere die verbleibenden nach Studiengang,
                                #. wähle pro Studiengang den mit der besten Durchschnittsnote, und
                                #. erstelle daraus eine nach Note sortierte Empfehlungsliste.

                        .. container:: incremental

                            .. rubric:: Setup

                            .. code:: java
                                :number-lines:
                                :line-number-digits: 2

                                record Student(boolean hatStipendium, String studiengang, double schnitt){};
                                List<Student> alleStudierenden = List.of(new Student(false, "Inf.", 1.3), …);

                            .. rubric:: Klassische Implementierung der Geschäftslogik

                            .. code:: java
                                :number-lines: 3
                                :line-number-digits: 2

                                // Schritt 1: Studierende ohne Stipendium sammeln
                                List<Student> ohneStipendium = new ArrayList<>();
                                for (Student s : alleStudierenden) {
                                    if (!s.hatStipendium()) { ohneStipendium.add(s); }
                                }
                                // Schritt 2: Nach Studiengang gruppieren
                                Map<String, List<Student>> nachStudiengang = new HashMap<>();
                                for (Student s : ohneStipendium) {
                                    nachStudiengang
                                        .computeIfAbsent(s.studiengang(), k -> new ArrayList<>())
                                        .add(s);
                                }
                                // Schritt 3: Pro Gruppe den Besten finden
                                List<Student> empfehlungen = new ArrayList<>();
                                for (var eintrag : nachStudiengang.entrySet()) {
                                    Student bester = null;
                                    for (Student s : eintrag.getValue()) {
                                        if (bester == null || s.schnitt() < bester.schnitt()) { bester = s; }
                                    }
                                    if (bester != null) {
                                        empfehlungen.add(bester);
                                    }
                                }
                                // Schritt 4: Empfehlungen nach Note sortieren
                                empfehlungen.sort(Comparator.comparingDouble(Student::schnitt));



.. class:: new-section transition-scale

Zentrale Konzepte von (Java) Streams
--------------------------------------------------



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

        * -
          - Java Stream API
          - Java I/O Streams
        * - Package
          - :java:`java.util.stream.*`
          - :java:`java.io.*` / :java:`java.nio.*`
        * - Fokus
          - Datenverarbeitung
          - Byte-/Zeichentransport
        * - Paradigma
          - Funktional/Deklarativ
          - Imperativ

.. supplemental:: dd-margin-left-4em

    Im Folgenden betrachten wir auch nicht `Reactive Streams <https://www.reactive-streams.org>`__, die seit Java 9 über die :java:`java.util.concurrent.Flow` `API <https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.html>`__ unterstützt werden.

    :Imperativ Programmierung:

        Das Vorgehen wird detailliert durch konkrete Anweisungen beschrieben, die genau vorgeben welche einzelnen Schritte von dem Computer ausgeführt werden sollen, um das Ziel zu erreichen.

        Viele gängige *general-purpose* Programmiersprachen (C, C++, Rust, Java, Go, JavaScript, Python) sind im Kern imperative Programmiersprachen bzw. erlauben einen imperativen Programmierstil.

    :Deklarative Programmierung:

        Das Ziel ist es auszudrücken *was* erreicht werden soll, ohne das *wie* genau anzugeben.

        (Ein sehr prominentes Beispiel für eine deklarative Programmiersprache ist die Datenbankabfragesprache SQL.)

    .. remark::

        - Auch für gängige Programmiersprachen, die im Kern imperativ sind, ist zu beobachten, dass mehr und mehr Ideen und Konzepte aus der deklarativen Programmierung Einzug in diese Sprachen - insbesondere auch über APIs - finden.

        - Es ist in der Zwischenzeit so, dass die Grenzen zwischen (klassischen) prozeduralen (d. h. primär imperativen) Programmiersprachen und funktionalen sowie deklarativen Programmiersprachen verschwimmen.

        - Scala – als ein Beispiel für eine moderne Programmiersprache – ist von Grund auf als objektorientiert-funktionale Sprache entworfen worden.



Streams - Einführung am Beispiel
------------------------------------------------

.. deck::

    .. card::

        .. class:: incremental-list dd-margin-left-4em

        :Programmiertechnische Sicht:
            Streams erlauben auf Sammlungen (:eng:`Collections`) die Ausführung von funktional-orientierten Massen-Operationen.\ [#]_
        :Konzeptionelle Sicht: Streams erlauben die *korrekte, effiziente, lesbare und domänennahe* Verarbeitung von Daten mit Hilfe von Konzepten und Ideen aus der funktionalen und deklarativen Programmierung.

    .. card::

        .. example::

            .. supplemental::
                :embed-in-document-flow:

                *Benötigte Imports (nicht in der JShell)*

                .. code:: java
                    :class: copy-to-clipboard
                    :number-lines:
                    :line-number-digits: 1

                    import java.util.Arrays;
                    import java.util.List;

            .. code:: java
                :number-lines:
                :line-number-digits: 1
                :class: copy-to-clipboard

                List<Integer> li = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
                List<Integer> lr = li
                    .stream()                   // Erzeugt einen Stream basierend auf der Liste
                    .filter(x -> x % 2 == 0)    // Filterung mit Hilfe eines Prädikats
                    .map(x -> 10 * x)           // Bilde jedes Element auf das Zehnfache ab
                    .toList();                  // Aufsammeln der Ergebnisse
                lr.forEach(x -> IO.print(x + " "));

            .. container:: incremental

                *Ausgabe:* :console:`20 40 60 80`

.. [#] `Th. Letschert <https://www.thm.de/mni/thomas-k-letschert>`__
.. Ursprünglich: https://homepages.thm.de/~hg51/Veranstaltungen/A_D/Folien/java-8-kurzeinfuehrung.pdf>`__



Streams - Grundlegendes Konzept
---------------------------------

.. raw:: html
    :class: center-content height-100

    <div style="width:71ch; height: 35.5ch">
    <svg viewBox="0 3 66 33"
        xmlns="http://www.w3.org/2000/svg">

    <!-- ═══════════════════════════════════════════════════
        Schritt 1: Quelle  (sofort sichtbar)
        ═══════════════════════════════════════════════════ -->
    <g>
        <!-- Source-Box -->
        <rect x="1" y="11" width="13" height="8" rx="0.8"
            fill="light-dark(#f3f4f6, #374151)"
            stroke="light-dark(#6b7280, #9ca3af)" stroke-width="0.2"/>
        <text x="7.5" y="13.4" text-anchor="middle"
            font-size="1.6" font-weight="bold" fill="currentColor">Quelle</text>
        <text x="7.5" y="14.75" text-anchor="middle"
            font-size="0.95"  fill="light-dark(#fc6a03, #dfa172)">(read-only)</text>
        <text x="7.5" y="16.6" text-anchor="middle"
            font-size="1.05" fill="light-dark(#6b7280, #9ca3af)">
            <tspan x="7.5" dy="0">&lt;Collection&gt;.stream(),</tspan>
            <tspan x="7.5" dy="1.4">Stream.of(…), …</tspan>
        </text>

        <!-- .stream() Pfeil -->
        <line x1="14" y1="15" x2="19.5" y2="15"
            stroke="currentColor" stroke-width="0.2"
            marker-end="url(#pipe-arrow)"/>
        <text x="16.75" y="14" text-anchor="middle"
            font-size="0.95"  fill="light-dark(#fc6a03, #dfa172)">(read-once)</text>
    </g>

    <!-- ═══════════════════════════════════════════════════
        Schritt 2: Intermediate Operations
        ═══════════════════════════════════════════════════ -->
    <g class="incremental">
        <!-- Hintergrund-Klammer für die Kette -->
        <rect x="19.5" y="6" width="16" height="17.25" rx="0.6"
            fill="light-dark(#eff6ff, #1e3a5f)"
            stroke="light-dark(#2563eb, #60a5fa)" stroke-width="0.12"
            stroke-dasharray="0.5,0.3" opacity="0.5"/>
        <text x="27.5" y="7.5" text-anchor="middle"
            font-size="1" fill="light-dark(#2563eb, #60a5fa)"
            opacity="0.7">0..n Intermediate Operations (lazy)</text>

        <!-- Op 1: filter -->
        <g transform="translate(2.5,-3.75)">
            <rect x="21" y="12" width="8.5" height="6" rx="0.6"
                fill="light-dark(#dbeafe, #1e40af)"
                stroke="light-dark(#2563eb, #60a5fa)" stroke-width="0.18"/>
            <text x="25.25" y="14.8" text-anchor="middle"
                font-size="1.2" font-weight="bold" font-style="italic"
                fill="light-dark(#1e40af, #93c5fd)">Built-in Op</text>
            <text x="25.25" y="16.6" text-anchor="middle"
                font-size="0.85"
                fill="light-dark(#3b82f6, #93c5fd)">map(…), sorted(), …</text>
        </g>

        <!-- Verbindungspfeil -->
        <line x1="23.5" y1="15" x2="32" y2="15"
            stroke="light-dark(#2563eb, #60a5fa)" stroke-width="0.18"
            marker-end="url(#pipe-arrow)"/>

        <!-- Op 2: gather (custom!) -->
        <g transform="translate(2.5,3.75)">
            <rect x="21" y="12" width="8.5" height="6" rx="0.6"
                fill="light-dark(#dcfce7, #14532d)"
                stroke="light-dark(#15803d, #4ade80)" stroke-width="0.18"/>
            <text x="25.25" y="14.8" text-anchor="middle"
                font-size="1.2" font-weight="bold" font-style="italic"
                fill="light-dark(#15803d, #4ade80)">Custom Op</text>
            <text x="25.25" y="13.25" text-anchor="middle"
                font-size="0.9"
                fill="light-dark(#15803d, #4ade80)">≥Java 24</text>
            <text x="25.25" y="16.6" text-anchor="middle"
                font-size="0.85"
                fill="light-dark(#16a34a, #86efac)">gather</text>
        </g>

        <!-- „..." -->
        <text x="21.5" y="16" text-anchor="middle"
            font-size="2.5" fill="light-dark(#93c5fd, #60a5fa)"
            font-weight="bold">···</text>
        <text x="33.5" y="16" text-anchor="middle"
            font-size="2.5" fill="light-dark(#93c5fd, #60a5fa)"
            font-weight="bold">···</text>

        <path d="M 33.5 15.5 L 33.5 22.5 L 21.5 22.5 L 21.5 15.5"
            fill="none"
            stroke="light-dark(#2563eb, #60a5fa)" stroke-width="0.2"
            stroke-dasharray="0.4,0.3"
            marker-end="url(#pipe-arrow)"/>
    </g>

    <!-- ═══════════════════════════════════════════════════
        Schritt 3: Terminal Operation
        ═══════════════════════════════════════════════════ -->
    <g class="incremental">
        <!-- Pfeil zur Terminal Op -->
        <line x1="35.5" y1="15" x2="41" y2="15"
            stroke="currentColor" stroke-width="0.2"
            marker-end="url(#pipe-arrow)"/>

        <!-- Terminal-Box -->
        <rect x="41" y="11" width="13" height="8" rx="0.8"
            fill="light-dark(#fef2f2, #4c1d1d)"
            stroke="light-dark(#b91c1c, #f87171)" stroke-width="0.22"/>
        <text x="47.5" y="14.2" text-anchor="middle"
            font-size="1.4" font-weight="bold" font-style="italic"
            fill="light-dark(#b91c1c, #f87171)">Terminal Op</text>
        <text x="47.5" y="16.6" text-anchor="middle"
            font-size="1.0" fill="light-dark(#ef4444, #fca5a5)">
        <tspan x="47.5" dy="0">collect(…), toList(),</tspan>
        <tspan x="47.5" dy="1.4">forEach(…), reduce(…), …</tspan>
        </text>
    </g>

    <!-- ═══════════════════════════════════════════════════
        Schritt 4: UpStream-Downstream
        ═══════════════════════════════════════════════════ -->

    <g class="incremental">
        <line x1="28.5" y1="5.5" x2="48.5" y2="5.5"
            style="stroke:oklch(from currentColor 0.8 c h);"
            stroke-width="0.15" stroke-dasharray="0.3,0.3"
            marker-end="url(#light-pipe-arrow)"/>
        <line x1="26.5" y1="5.5" x2="6.5" y2="5.5"
            style="stroke:oklch(from currentColor 0.8 c h);"
            stroke-width="0.15"
            stroke-dasharray="0.3,0.3"
            marker-end="url(#light-pipe-arrow)"/>
        <text x="28.5" y="5" text-anchor="start"
            font-size="0.95" fill="currentColor"
            font-style="italic">Downstream</text>
        <text x="26.5" y="5" text-anchor="end"
            font-size="0.95" fill="currentColor"
            font-style="italic">Upstream</text>

    </g>


    <!-- ═══════════════════════════════════════════════════
        Schritt 5: Ergebnis
        ═══════════════════════════════════════════════════ -->
    <g class="incremental">
        <!-- Pfeil zum Ergebnis -->
        <line x1="54" y1="15" x2="59.5" y2="15"
            stroke="currentColor" stroke-width="0.2"
            marker-end="url(#pipe-arrow)"/>
        <!-- Ergebnis -->
        <rect x="59.5" y="12" width="5.5" height="6" rx="0.6"
            fill="light-dark(#f5f3ff, #3b0764)"
            stroke="light-dark(#7c3aed, #a78bfa)" stroke-width="0.18"/>
        <text x="62.25" y="14.35" text-anchor="middle"
            font-size="1.2" font-weight="bold"
            fill="light-dark(#7c3aed, #a78bfa)">Ergebnis</text>
        <text x="62.25" y="16.35" text-anchor="middle"
            font-size="1.2" font-weight="bold"
            fill="light-dark(#7c3aed, #a78bfa)">/ Effekt</text>
    </g>

    <!-- ═══════════════════════════════════════════════════
        Schritt 6: Erklärung
        ═══════════════════════════════════════════════════ -->
    <g class="incremental">
        <!-- Annotationen unterhalb -->
        <g font-size="1.5">
        <text y="27.5" text-anchor="middle"
            fill="light-dark(#6b7280, #9ca3af)">
            <tspan x="7.5" font-weight="bold" fill="currentColor">Erzeugen</tspan>
            <tspan x="7.5" dy="2">Erstellt einen Stream</tspan>
            <tspan x="7.5" dy="1.75">aus einer Datenquelle.</tspan>
            <tspan x="7.5" dy="1.75">Keine Daten fließen</tspan>
            <tspan x="7.5" dy="1.75">bis zur Terminal Op.</tspan>
        </text>
        <text y="27.5" text-anchor="middle"
            fill="light-dark(#6b7280, #9ca3af)">
            <tspan x="27.5" font-weight="bold" fill="currentColor">Transformieren</tspan>
            <tspan x="27.5" dy="2">Verkettete Operationen;</tspan>
            <tspan x="27.5" dy="1.75">jede liefert neuen Stream.</tspan>
            <tspan x="27.5" dy="1.75">Lazy: erst bei Terminal</tspan>
            <tspan x="27.5" dy="1.75">Op ausgewertet.</tspan>
        </text>
        <text y="27.5" text-anchor="middle"
            fill="light-dark(#6b7280, #9ca3af)">
            <tspan x="47.5" font-weight="bold" fill="currentColor">Konsumieren</tspan>
            <tspan x="47.5" dy="2">Löst die Auswertung</tspan>
            <tspan x="47.5" dy="1.75">der gesamten Pipeline</tspan>
            <tspan x="47.5" dy="1.75">aus und produziert</tspan>
            <tspan x="47.5" dy="1.75">das Ergebnis/den Effekt.</tspan>
        </text>
        </g>
    </g>

    </svg>
    </div>

.. supplemental::
    :embed-in-document-flow:

    Wir sagen auch, dass die Daten *Downstream gepusht* werden. Dies bedeutet nichts anderes, als dass die Daten :ger:`stromabwärts` weitergereicht werden.

    *Upstream* (:ger:`stromaufwärts`) bezeichnet die entgegengesetzte Richtung: eine Operation X liegt *upstream* von Y, wenn X zeitlich vor Y ausgeführt wird und somit näher an der Quelle liegt.

    Es ist möglich als terminale Operation einen :java:`Iterator` bzw. :java:`Spliterator` zu erzeugen. In diesen beiden Fällen erfolgt die Evaluation der Pipeline nicht unmittelbar durch die terminale Operation (d. h. nicht *eager* sondern *lazy*). Das hat zur Folge, dass die Elemente des Streams erst dann verarbeitet werden, wenn sie tatsächlich über den :java:`Iterator`\ /\ :java:`Spliterator` angefordert werden. Die Verwendung dieser beiden Methoden (:java:`<BaseStream>.iterator()`/:java:`<BaseStream>.spliterator()`) führt zu einem Bruch des deklarativen Pipeline-Modells, der in den allermeisten Fällen vermieden werden kann. Häufig sind die Methoden nur noch im Zusammenhang mit Legacy-APIs relevant, die mit :java:`Iterator`\ /\ :java:`Spliterator` arbeiten.



Java Stream API - Evolution
--------------------------------

.. story::

    .. raw:: html
        :class: center-content

        <div style="width:71ch; height: 90.47ch">
        <svg viewBox="6 0 62 79"
            xmlns="http://www.w3.org/2000/svg">

        <!-- ═══ Timeline backbone ═══ -->
        <line x1="40" y1="2" x2="40" y2="6"
                stroke="light-dark(#bbb, #555)" stroke-width="0.25" stroke-dasharray="0.5 0.25"/>
        <line x1="40" y1="6" x2="40" y2="69"
                stroke="light-dark(#bbb, #555)" stroke-width="0.25" />
        <line x1="40" y1="69" x2="40" y2="78"
                stroke="light-dark(#bbb, #555)" stroke-width="0.25" stroke-dasharray="0.5 0.25"/>

        <!-- ═══════════════════════════════════════════════════
            Java 8 — März 2014  (LEFT)
            ═══════════════════════════════════════════════════ -->
        <g>
            <circle cx="40" cy="6" r="1"
                    fill="light-dark(#b91c1c, #f87171)" />
            <line x1="39" y1="6" x2="32" y2="6"
                stroke="light-dark(#b91c1c, #f87171)" stroke-width="0.2" />
            <text x="31" y="5" text-anchor="end"
                font-size="2.2" font-weight="bold"
                fill="light-dark(#b91c1c, #f87171)">Java 8</text>
            <text x="31" y="7" text-anchor="end"
                font-size="1.1"
                fill="light-dark(#888, #999)">März 2014 (LTS bis 2030)</text>
            <text x="31" y="9.2" text-anchor="end"
                font-size="1.1" fill="currentColor">
                <tspan x="31" dy="0" font-weight="600">Initiale Stream API (java.util.stream)</tspan>
                <tspan x="31" dy="1.6" font-weight="500" font-style="italic">Intermediate Ops: </tspan><tspan>filter, map, flatMap, sorted, …</tspan>
                <tspan x="31" dy="1.6"  font-weight="500" font-style="italic">Terminal Ops: </tspan><tspan>reduce, forEach, count, …, </tspan>
                <tspan x="31" dy="1.6" fill="light-dark(#791010, #E8A1A1)">collect(</tspan><tspan font-style="italic" fill="light-dark(#791010, #E8A1A1)">Collector</tspan><tspan fill="light-dark(#791010, #E8A1A1)">)</tspan>
                <tspan x="31" dy="1.6" font-weight="500" font-style="italic">Parallele Streams: </tspan><tspan>parallelStream()</tspan>
            </text>
        </g>


        <!-- ═══════════════════════════════════════════════════
            Java 9 — Sep. 2017  (RIGHT)
            ═══════════════════════════════════════════════════ -->
        <g class="incremental" transform="translate(0, -4)">

            <!-- Java 9 -->
            <circle cx="40" cy="22" r="0.6"
                    fill="light-dark(#2563eb, #60a5fa)" />
            <line x1="41" y1="22" x2="48" y2="22"
                stroke="light-dark(#2563eb, #60a5fa)" stroke-width="0.2" />
            <text x="49" y="21" text-anchor="start"
                font-size="2.2" font-weight="bold"
                fill="light-dark(#2563eb, #60a5fa)">Java 9</text>
            <text x="49" y="23" text-anchor="start"
                font-size="1.1"
                fill="light-dark(#888, #999)">Sep. 2017</text>
            <text x="49" y="25.2" text-anchor="start"
                font-size="1.1" fill="currentColor">
            <tspan x="49" dy="0">Stream.iterate(seed, hasNext, next)</tspan>
            <tspan x="49" dy="1.6">Stream.ofNullable(…), &lt;Optional&gt;.stream()</tspan>
            <tspan x="49" dy="1.6">takeWhile, dropWhile</tspan>
            <tspan x="49" dy="1.6">Collectors.{filtering, flatMapping}</tspan>
            </text>
        </g>

        <!-- ═══════════════════════════════════════════════════
            Java 10 — März 2018  (LEFT)
            ═══════════════════════════════════════════════════ -->
        <g class="incremental" transform="translate(0, -10)">
            <circle cx="40" cy="36" r="0.6"
                    fill="light-dark(#2563eb, #60a5fa)" />
            <line x1="39.4" y1="36" x2="32" y2="36"
                stroke="light-dark(#2563eb, #60a5fa)" stroke-width="0.15" />
            <text x="31" y="35" text-anchor="end"
                font-size="2" font-weight="bold"
                fill="light-dark(#2563eb, #60a5fa)">Java 10</text>
            <text x="31" y="37" text-anchor="end"
                font-size="1.1"
                fill="light-dark(#888, #999)">März 2018</text>
            <text x="31" y="39" text-anchor="end"
                font-size="1.1" fill="currentColor">
            <tspan x="31" dy="0">Collectors.toUnmodifiable{List, Set, Map}</tspan>
            </text>
        </g>

        <!-- ═══════════════════════════════════════════════════
            Java 12 — März 2019  (RIGHT)
            ═══════════════════════════════════════════════════ -->
        <g class="incremental" transform="translate(0,-10)">
            <circle cx="40" cy="44" r="0.6"
                    fill="light-dark(#2563eb, #60a5fa)" />
            <line x1="40.6" y1="44" x2="48" y2="44"
                stroke="light-dark(#2563eb, #60a5fa)" stroke-width="0.15" />
            <text x="49" y="43" text-anchor="start"
                font-size="2" font-weight="bold"
                fill="light-dark(#2563eb, #60a5fa)">Java 12</text>
            <text x="49" y="45" text-anchor="start"
                font-size="1.1"
                fill="light-dark(#888, #999)">März 2019</text>
            <text x="49" y="47" text-anchor="start"
                font-size="1.1" fill="currentColor">
            <tspan x="49" dy="0">Collectors.teeing (zwei Collector parallel)</tspan>
            </text>
        </g>

        <!-- ═══════════════════════════════════════════════════
            Java 16 — März 2021  (LEFT)
            ═══════════════════════════════════════════════════ -->
        <g class="incremental" transform="translate(0,-12)">
            <circle cx="40" cy="53" r="0.6"
                    fill="light-dark(#2563eb, #60a5fa)" />
            <line x1="39.3" y1="53" x2="32" y2="53"
                stroke="light-dark(#2563eb, #60a5fa)" stroke-width="0.15" />
            <text x="31" y="52" text-anchor="end"
                font-size="2" font-weight="bold"
                fill="light-dark(#2563eb, #60a5fa)">Java 16</text>
            <text x="31" y="54" text-anchor="end"
                font-size="1.1"
                fill="light-dark(#888, #999)">März 2021</text>
            <text x="31" y="56" text-anchor="end"
                font-size="1.1" fill="currentColor">
            <tspan x="31" dy="0">toList() (Ergänzend zu collect(Collectors.toList()))</tspan>
            <tspan x="31" dy="1.6">mapMulti (als Alternative zu flatMap)</tspan>
            </text>

            <!-- Epoche: Inkrementelle Verbesserungen -->
            <line x1="38" y1="30" x2="38" y2="53"
                stroke="light-dark(#2563eb, #60a5fa)" stroke-width="0.1"
                stroke-dasharray="0.4,0.3" opacity="0.5" />
            <text x="37" y="41.5" text-anchor="middle"
                font-size="1" fill="light-dark(#2563eb, #60a5fa)"
                transform="rotate(-90, 37, 41.5)" opacity="0.6">Inkrementelle Ergänzungen</text>
        </g>

        <!-- ═══════════════════════════════════════════════════
            Java 22 — März 2024  (RIGHT)  — Gatherers Era
            ═══════════════════════════════════════════════════ -->
        <g class="incremental" transform="translate(0,-14)">

            <!-- Java 22 -->
            <circle cx="40" cy="65" r="0.6"
                    fill="light-dark(#15803d, #4ade80)" />
            <line x1="40.7" y1="65" x2="48" y2="65"
                stroke="light-dark(#15803d, #4ade80)" stroke-width="0.15" />
            <text x="49" y="64" text-anchor="start"
                font-size="2" font-weight="bold"
                fill="light-dark(#15803d, #4ade80)">Java 22</text>
            <text x="49" y="66" text-anchor="start"
                font-size="1.1"
                fill="light-dark(#888, #999)">März 2024</text>
            <text x="49" y="68" text-anchor="start"
                font-size="1.1" fill="currentColor">
            <tspan x="49" dy="0">JEP 461: Stream Gatherers (Preview)</tspan>
            <tspan x="49" dy="1.6" font-style="italic" font-weight="500">Custom Intermediate Ops: </tspan><tspan>via gather</tspan>
            </text>
        </g>

        <!-- ═══════════════════════════════════════════════════
            Java 23 — Sep. 2024  (LEFT)
            ═══════════════════════════════════════════════════ -->
        <g class="incremental" transform="translate(0,-16)">
            <circle cx="40" cy="76" r="0.6"
                    fill="light-dark(#15803d, #4ade80)" />
            <line x1="39.4" y1="76" x2="32" y2="76"
                stroke="light-dark(#15803d, #4ade80)" stroke-width="0.15" />
            <text x="31" y="75" text-anchor="end"
                font-size="2" font-weight="bold"
                fill="light-dark(#15803d, #4ade80)">Java 23</text>
            <text x="31" y="77" text-anchor="end"
                font-size="1.1"
                fill="light-dark(#888, #999)">Sep. 2024</text>
            <text x="31" y="79" text-anchor="end"
                font-size="1.1" fill="currentColor">
            <tspan x="31" dy="0">JEP 473: Gatherers (2nd Preview, ohne Änd.)</tspan>
            </text>
        </g>

        <!-- ═══════════════════════════════════════════════════
            Java 24 — März 2025  (RIGHT)  — Finale
            ═══════════════════════════════════════════════════ -->
        <g class="incremental" transform="translate(0,-18)">
            <circle cx="40" cy="87" r="1"
                    fill="light-dark(#15803d, #4ade80)" />
            <line x1="41" y1="87" x2="48" y2="87"
                stroke="light-dark(#15803d, #4ade80)" stroke-width="0.2" />
            <text x="49" y="86" text-anchor="start"
                font-size="2.2" font-weight="bold"
                fill="light-dark(#15803d, #4ade80)">Java 24</text>
            <text x="49" y="88" text-anchor="start"
                font-size="1.1"
                fill="light-dark(#888, #999)">März 2025</text>
            <text x="49" y="90.2" text-anchor="start"
                font-size="1.1" fill="currentColor">
            <tspan x="49" dy="0" font-weight="bold">JEP 485: Gatherers</tspan>
            <tspan x="49" dy="1.6">Gatherer&lt;T,A,R&gt; + Stream.gather(…)</tspan>
            <tspan x="49" dy="1.6">Built-in: fold, scan, windowFixed,</tspan>
            <tspan x="49" dy="1.6">    windowSliding, mapConcurrent</tspan>
            </text>

            <!-- Epoche: Gatherers -->
            <line x1="42" y1="69" x2="42" y2="87"
                stroke="light-dark(#15803d, #4ade80)" stroke-width="0.1"
                stroke-dasharray="0.4,0.3" opacity="0.5" />
            <text x="43" y="76" text-anchor="start"
                font-size="1" fill="light-dark(#15803d, #4ade80)"
                writing-mode="tb" opacity="0.6">Gatherers-Ära</text>

        </g>
        </svg>
        </div>

    .. supplemental::

        :java:`<Stream>.flatMap(…)` bildet jedes Element auf einen Stream ab und ebnet (engl. flattens) diese zu einem einzigen Stream ein.

        :java:`<Stream>.mapMulti(…)` ist eine imperative Variante von :java:`<Stream>.flatMap(…)` und ggf. auch schneller, wenn die Zielliste immer/meistens sehr klein ist [`JavaDoc <https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/stream/Stream.html#mapMulti(java.util.function.BiConsumer)>`__].



Eigenschaften der *Intermediate Operations* von Java 8 bis 26
-------------------------------------------------------------------

.. story::

  .. raw:: html
    :class: center-content

    <div style="width:71ch; height: 74.55ch">
    <svg viewBox="0 4 40 42"
     xmlns="http://www.w3.org/2000/svg">

        <!-- ═══════════════════════════════════════════════════
            Schritt 1: Stateless vs. Stateful
            ═══════════════════════════════════════════════════ -->
        <g>
            <text x="22" y="2.5" text-anchor="middle"
                font-size="1.5" font-weight="bold"
                fill="currentColor"></text>

            <!-- Stateless -->
            <rect x="1" y="5" width="16" height="5" rx="0.6"
                fill="light-dark(#dbeafe, #1e3a5f)"
                stroke="light-dark(#2563eb, #60a5fa)" stroke-width="0.2"/>
            <text x="9" y="7.2" text-anchor="middle"
                font-size="1.4" font-weight="bold"
                fill="light-dark(#1e40af, #93c5fd)">Stateless</text>

            <!-- Stateful -->
            <rect x="23" y="5" width="16" height="5" rx="0.6"
                fill="light-dark(#fef2f2, #4c1d1d)"
                stroke="light-dark(#b91c1c, #f87171)" stroke-width="0.2"/>
            <text x="31" y="7.2" text-anchor="middle"
                font-size="1.4" font-weight="bold"
                fill="light-dark(#b91c1c, #f87171)">Stateful</text>

            <!-- vs. -->
            <line x1="17" y1="7.5" x2="23" y2="7.5"
                stroke="light-dark(#d1d5db, #4b5563)" stroke-width="0.1"
                stroke-dasharray="0.3,0.2"/>
            <text x="20" y="6.8" text-anchor="middle"
                font-size="0.9" fill="light-dark(#9ca3af, #6b7280)"
                font-style="italic">vs.</text>

            <!-- Beispiele -->
            <text x="9" y="9" text-anchor="middle"
                font-size="0.85" fill="light-dark(#6b7280, #9ca3af)">z. B. filter</text>
            <text x="31" y="9" text-anchor="middle"
                font-size="0.85" fill="light-dark(#6b7280, #9ca3af)">z. B. sorted</text>
        </g>

        <!-- ═══════════════════════════════════════════════════
            Schritt 2: Konsequenzen Stateless
            ═══════════════════════════════════════════════════ -->
        <g class="incremental">
            <line x1="9" y1="10" x2="9" y2="12"
                stroke="light-dark(#2563eb, #60a5fa)" stroke-width="0.18"
                marker-end="url(#impl-arrow)"/>
            <text x="10" y="11.15" text-anchor="start"
                font-size="0.8" fill="light-dark(#93c5fd, #60a5fa)"
                font-style="italic">⇒ impliziert</text>

            <rect x="1" y="12" width="16" height="4" rx="0.5"
                fill="light-dark(#eff6ff, #172554)"
                stroke="light-dark(#93c5fd, #1e40af)" stroke-width="0.12"/>
            <text x="9" y="13.5" text-anchor="middle"
                font-size="1.0" fill="light-dark(#1e40af, #93c5fd)">Konstanter
                <tspan x="9" dy="1.75">Speicherbedarf</tspan>
                </text>

            <line x1="9" y1="16" x2="9" y2="18"
                stroke="light-dark(#2563eb, #60a5fa)" stroke-width="0.18"
                marker-end="url(#impl-arrow)"/>

            <rect x="1" y="18" width="16" height="4" rx="0.5"
                fill="light-dark(#eff6ff, #172554)"
                stroke="light-dark(#93c5fd, #1e40af)" stroke-width="0.12"/>
            <text x="9" y="19.5" text-anchor="middle"
                font-size="1.0" fill="light-dark(#1e40af, #93c5fd)">Trivial
                <tspan x="9" dy="1.75">parallelisierbar</tspan></text>
        </g>

        <!-- ═══════════════════════════════════════════════════
            Schritt 3: Konsequenzen Stateful
            ═══════════════════════════════════════════════════ -->
        <g class="incremental">
            <line x1="31" y1="10" x2="31" y2="12"
                stroke="light-dark(#b91c1c, #f87171)" stroke-width="0.18"
                marker-end="url(#impl-arrow)"/>
            <text x="30" y="11.15" text-anchor="end"
                font-size="0.8" fill="light-dark(#fca5a5, #f87171)"
                font-style="italic">⇒ möglich</text>

            <rect x="23" y="12" width="16" height="4" rx="0.5"
                fill="light-dark(#fef2f2, #371717)"
                stroke="light-dark(#fca5a5, #991b1b)" stroke-width="0.12"/>
            <text x="31" y="13.5" text-anchor="middle"
                font-size="1.0" fill="light-dark(#991b1b, #fca5a5)">Speicher bis O(n)</text>
            <text x="31" y="15" text-anchor="middle"
                font-size="0.8" fill="light-dark(#6b7280, #9ca3af)">(aber nicht zwingend)</text>

            <line x1="31" y1="16" x2="31" y2="18"
                stroke="light-dark(#b91c1c, #f87171)" stroke-width="0.18"
                marker-end="url(#impl-arrow)"/>

            <rect x="23" y="18" width="16" height="4" rx="0.5"
                fill="light-dark(#fef2f2, #371717)"
                stroke="light-dark(#fca5a5, #991b1b)" stroke-width="0.12"/>
            <text x="31" y="19.5" text-anchor="middle"
                font-size="1.0" fill="light-dark(#991b1b, #fca5a5)">Parallelisierung
                <tspan x="31" dy="1.75">eingeschränkt</tspan></text>
        </g>

        <g transform="translate(0,-7)">
        <!-- ═══════════════════════════════════════════════════
            Schritt 4: Short-circuiting
            ═══════════════════════════════════════════════════ -->
        <g class="incremental">
            <line x1="3" y1="31" x2="37" y2="31"
                stroke="light-dark(#e5e7eb, #374151)" stroke-width="0.08"/>
            <text x="20" y="33" text-anchor="middle"
                font-size="0.8" fill="light-dark(#9ca3af, #6b7280)"
                font-style="italic">Weitere Dimensionen</text>

            <rect x="1" y="35" width="12" height="5" rx="0.6"
                fill="light-dark(#fefce8, #422006)"
                stroke="light-dark(#ca8a04, #facc15)" stroke-width="0.18"/>
            <text x="7" y="37" text-anchor="middle"
                font-size="1.1" font-weight="bold"
                fill="light-dark(#854d0e, #facc15)">Short-circuiting</text>
            <text x="7" y="39.2" text-anchor="middle"
                font-size="0.8" fill="light-dark(#a16207, #fde047)">(vorzeitig abbrechend)</text>

            <line x1="13" y1="37.5" x2="27" y2="37.5"
                stroke="light-dark(#ca8a04, #facc15)" stroke-width="0.18"
                marker-end="url(#impl-arrow)"/>

            <rect x="27" y="35" width="12" height="5" rx="0.5"
                fill="light-dark(#fefce8, #422006)"
                stroke="light-dark(#fde68a, #92400e)" stroke-width="0.12"/>
            <text x="33" y="37" text-anchor="middle"
                font-size="0.95" fill="light-dark(#854d0e, #facc15)">kann unendliche</text>
            <text x="33" y="38.5" text-anchor="middle"
                font-size="0.95" fill="light-dark(#854d0e, #facc15)">Streams beenden</text>

            <!-- Beispiele -->
            <text x="20" y="34.75" text-anchor="middle"
                font-size="0.85" fill="light-dark(#6b7280, #9ca3af)">
                Beispiele:
                <tspan x="20" dy="2.25" font-weight="bold" fill="light-dark(#16a34a, #4ade80)">Ja: </tspan>takeWhile
                <tspan x="20" dy="1.5" font-weight="bold" fill="light-dark(#dc2626, #f87171)">Nein: </tspan>sorted
            </text>
        </g>

        <!-- ═══════════════════════════════════════════════════
            Schritt 5: Encounter Order
            ═══════════════════════════════════════════════════ -->
        <g class="incremental" transform="translate(0,-4)">
            <rect x="1" y="47" width="12" height="5" rx="0.6"
                fill="light-dark(#f0fdf4, #052e16)"
                stroke="light-dark(#15803d, #4ade80)" stroke-width="0.18"/>
            <text x="7" y="49" text-anchor="middle"
                font-size="1.1" font-weight="bold"
                fill="light-dark(#15803d, #4ade80)">Encounter Order</text>
            <text x="7" y="51.2" text-anchor="middle"
                font-size="0.8" fill="light-dark(#16a34a, #86efac)">(Elementreihenfolge)</text>

            <line x1="13" y1="49.5" x2="27" y2="49.5"
                stroke="light-dark(#15803d, #4ade80)" stroke-width="0.18"
                marker-end="url(#impl-arrow)"/>

            <rect x="27" y="47" width="12" height="5" rx="0.5"
                fill="light-dark(#f0fdf4, #052e16)"
                stroke="light-dark(#86efac, #14532d)" stroke-width="0.12"/>
            <text x="33" y="49.25" text-anchor="middle"
                font-size="0.95" fill="light-dark(#15803d, #4ade80)">Beeinflusst Overhead</text>
            <text x="33" y="50.75" text-anchor="middle"
                font-size="0.95" fill="light-dark(#15803d, #4ade80)">bei Parallelisierung</text>

            <!-- Beispiele -->
            <g transform="translate(11,-6.65)">
            <text x="9" y="54.2" text-anchor="middle"
                font-size="0.85" fill="light-dark(#6b7280, #9ca3af)">
            <tspan font-weight="bold" fill="light-dark(#2563eb, #60a5fa)">Erhält:</tspan>
            filter</text>
            <text x="9" y="55.7" text-anchor="middle"
                font-size="0.85" fill="light-dark(#6b7280, #9ca3af)">
            <tspan font-weight="bold" fill="light-dark(#ca8a04, #facc15)">Stellt her:</tspan>
            sorted</text>
            <text x="9" y="57.2" text-anchor="middle"
                font-size="0.85" fill="light-dark(#6b7280, #9ca3af)">
            <tspan font-weight="bold" fill="light-dark(#dc2626, #f87171)">Entfernt:</tspan>
            unordered</text>
            </g>
        </g>
        </g>

        </svg>
        </div>

.. supplemental:: dd-margin-left-4em

    :Stateless Ops(`zustandslose Operationen`:ger:): Transformieren die Elemente jeweils völlig unabhängig von allen anderen.
    :Stateful Ops(`zustandsbehaftete Operationen`:ger:): Transformieren die Elemente abhängig von anderen.
    :Encounter-Order:

        Die *Encounter Order* hängt typischerweise an der Quelle. Zum Beispiel ist ein Stream über die Elemente einer Liste *Ordered* während er für Sets *unordered* ist. Sollte ein Stream über die Elemente eines Sets allerdings sortiert werden (:java:`sorted()`), dann ist dieser ab diesem Zeitpunkt ordered.

        .. hint::

            Es kann interessant sein, einen Stream explizit als :java:`unordered()` zu markieren, da dann ggf. weitere Optimierungen bei der Auswertung möglich sind.




Stream-Operationen mit Seiteneffekten
---------------------------------------------

.. container:: height-90 center-content

    .. warning::

        Seiteneffekte in Funktionen, die an Stream-Operationen übergeben werden, sind grundsätzlich zu vermeiden. Wird ein Stream parallelisiert und eine übergebene Funktion (z. B. an die :java:`peek` Methode) hat dennoch Seiteneffekte, so muss diese Thread-sicher sein.


.. supplemental::

    `Java Stream API <https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/stream/package-summary.html>`__



Überblick über wichtige Operationen und ihre Eigenschaften
--------------------------------------------------------------------

.. deck::

    .. card::

        .. csv-table::
            :header: "Operation", "Stateless / Stateful", "Speicherbedarf", "Parallelisierbar", "Short-circuiting"
            :widths: 14, 14, 16, 14, 14
            :stub-columns: 1
            :width: 100%
            :class: dhbw highlight-row-on-hover

            "``filter``", "Stateless", "Konstant", "Trivial", "Nein"
            "``map``", "Stateless", "Konstant", "Trivial", "Nein"
            "``flatMap``", "Stateless", "Konstant", "Trivial", "Nein"
            "``mapMulti``", "Stateless", "Konstant", "Trivial", "Nein"
            "``peek``", "Stateless", "Konstant", "Trivial", "Nein"
            "``sorted``", "Stateful", "O(n)", "Eingeschränkt", "Nein"
            "``distinct``", "Stateful", "O(n)", "Eingeschränkt", "Nein"
            "``limit``", "Stateful", "Konstant", "Eingeschränkt", "Ja"
            "``skip``", "Stateful", "Konstant", "Eingeschränkt", "Nein"
            "``takeWhile``", "Stateful", "Konstant", "Eingeschränkt", "Ja"
            "``dropWhile``", "Stateful", "Konstant", "Eingeschränkt", "Nein"
            "``gather``", :orange-themed:`Op. abhängig`, ":orange-themed:`Op. abhängig`", ":orange-themed:`Op. abhängig`", ":orange-themed:`Op. abhängig`"

.. supplemental::
    :embed-in-document-flow:

    Die Eigenschaften von :java:`gather` hängen von der übergebenen Gatherer-Implementierung ab. Javas Gatherer-Implementierung für :java:`windowFixed(k)` ist zum Beispiel eine stateful Operation mit O(k) Speicherbedarf, die nicht short-circuiting ist, aber dennoch gut parallelisierbar — insbesondere, wenn die Fenstergröße klein ist im Vergleich zur Anzahl an Elementen. Darüber hinaus ist es durchaus möglich Gatherer zu implementieren, die einen Speicherbedarf jenseits von O(n) haben. Zum Beispiel ist es möglich einen Gatherer zu implementieren, der alle Permutationen der Elemente berechnet. Dieser hätte einen Speicherbedarf von O(n!) und könnte somit nur auf sehr kleinen Streams sinnvoll eingesetzt werden.



.. class:: new-section transition-fade

Java Streams API - Deep Dive
---------------------------------------

`Bewertung der Fähigkeiten der Standardoperationen von Java Streams`_


Streams mit primitiven Daten und Objekten
------------------------------------------------

- :java:`Stream<T>` ist der Typ der Streams mit Objekten vom Typ :java:`T`
- Streams mit primitiven Daten:

  - :java:`IntStream`
  - :java:`LongStream`
  - :java:`DoubleStream`

  .. supplemental::
    :embed-in-document-flow:

    Diese Streams mit primitiven Daten arbeiten in vielen Fällen effizienter -  jedoch sind manche Operationen nur auf :java:`Object`\ -Streams erlaubt. „Primitive“ Streams können mit der Methode :java:`boxed()` in :java:`Object`\ -Streams des entsprechenden Wrapper-Typs umgewandelt werden.

.. example::
    :class: incremental

    .. code:: java
        :line-number-digits: 1
        :class: copy-to-clipboard

        IntStream isPrim = IntStream.range(1, 10);
        Stream<Integer> isObj = isPrim.boxed();     // Umwandlung in Boxed Stream



Erzeugung von Streams
------------------------------------------------

.. deck::

    .. card::

        .. rubric:: Statische Methoden in :java:`Arrays`

        - Die Klasse :java:`java.util.Arrays` hat mehrere überladene statische :java:`stream(…)`-Methoden, mit denen Arrays in Ströme umgewandelt werden können.
        - Die Streams können Objekte oder primitive Daten enthalten.

          .. example::
            :class: incremental

            .. code:: java
                :number-lines:
                :line-number-digits: 1
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

                - Mit :java:`of(…)` werden die übergebenen Wert in einen Stream gepackt.
                - Mit :java:`iterate(…)` und :java:`generate(…)` hat man eine einfache Möglichkeit unendliche, sequentielle Ströme zu erzeugen.

            .. card::

                .. example::
                    :class: incremental

                    .. code:: java
                        :number-lines:
                        :line-number-digits: 1
                        :class: copy-to-clipboard

                        // Object-Stream 1, 2 ... 9, 0:
                        Stream<Integer> is1a = Stream.of(1,2,3,4,5,6,7,8,9,0);

                    .. code:: java
                        :number-lines: 3
                        :line-number-digits: 1
                        :class: copy-to-clipboard incremental

                        // int-Stream 1, 2, ... 9, 0
                        IntStream is1b = IntStream.of(1,2,3,4,5,6,7,8,9,0);

                    .. code:: java
                        :number-lines: 5
                        :line-number-digits: 1
                        :class: copy-to-clipboard incremental

                        // (infinite) Stream 1, 2, ...
                        Stream<Integer> is2 = Stream.iterate(1, (x) -> x+1);

                    .. code:: java
                        :number-lines: 7
                        :line-number-digits: 1
                        :class: copy-to-clipboard incremental

                        int[] z = new int[]{1};
                        Stream<Integer> is3 = Stream.generate(() -> z[0]++); // (infinite) Stream 1,2,...

    .. card::

        .. rubric:: Statische range-Methoden in :java:`IntStream` und :java:`LongStream`

        Die Interfaces :java:`java.util.stream.IntStream` und :java:`java.util.stream.LongStream` enthalten jeweils :java:`range(…)` und :java:`rangeClose(…)`-Methoden mit denen Streams erzeugt werden können.

        .. example::
            :class: incremental

            .. code:: java
                :number-lines:
                :line-number-digits: 1
                :class: copy-to-clipboard

                IntStream isPrimA = IntStream.range(1, 10); // 1,2, .. 9
                IntStream isPrimA = IntStream.rangeClosed(1, 10); // 1,2, .. 9, 10

    .. card::

        .. rubric:: Nicht-statische Methoden der Collection-API

        Das Interface :java:`java.util.Collection` enthält die Methode :java:`stream()` mit der die jeweilige Kollektion in einen Stream umgewandelt werden kann.

        .. example::
            :class: incremental

            .. code:: java
                :number-lines:
                :line-number-digits: 1
                :class: copy-to-clipboard

                Stream<Integer> is = Arrays.asList(1,2,3,4,5,6,7,8,9,0).stream();



Verwendung von Streams
------------------------------------------------

.. deck::

    .. card::

        .. rubric:: Zustandslose Verarbeitungsoperationen

        .. deck::

            .. card::


                .. class:: incremental-list

                - :java:`<R> map(Function<? super T,? extends R> mapper)`:

                  Transformiert jedes Element in ein anderes.
                - :java:`filter(Predicate<? super T> predicate)`:

                  Filtert Elemente heraus.
                - :java:`<R> flatMap(Function<? super T,? extends Stream<? extends R>> mapper)`:

                  Transformiert jedes Element in einen Stream und fügt die Streams zusammen.
                - :java:`<R> mapMulti(BiConsumer<? super T,? super Consumer<? super R>> mapper)`:

                  Imperative Variante von :java:`flatMap`.
                - :java:`peek(Consumer<? super T> action)`:

                  Führt eine Aktion für jedes Element aus, ohne den Stream zu verändern.

            .. card::

                .. example::

                    .. code:: java
                        :number-lines:
                        :line-number-digits: 2
                        :class: copy-to-clipboard

                        import java.util.List;
                        import java.util.stream.Collectors;
                        import java.util.stream.IntStream;

                        List<Integer> is = IntStream.range(1, 10)
                            .filter(i -> i % 2 != 0)
                            .peek(i -> System.out.print(i+ " "))
                            .map(i -> 10 * i)
                            .boxed()
                            .toList();
                        System.out.println(is);

                    .. container:: incremental

                        *Ausgabe:* :console:`1 3 5 7 9 [10, 30, 50, 70, 90]`

            .. card::

                .. example::

                    .. code:: java
                        :number-lines:
                        :line-number-digits: 2
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
                            .toList();


                    .. container:: incremental

                        *Ausgabe:* :console:`is ==> [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29]`

    .. card::

        .. rubric:: Zustandsbehaftete Verarbeitungsoperationen

        .. deck::

            .. card::

                .. class:: incremental-list

                - :java:`distinct()`: Entfernt Duplikate.
                - :java:`sorted()`: Sortiert die Elemente gemäß ihrer natürlichen Ordnung (:java:`T extends Comparable<T>`).
                - :java:`sorted(Comparator<? super T> comparator)`: Sortiert die Elemente mit einem gegebenen Comparator.
                - :java:`limit(long maxSize)`: Begrenzt die Anzahl der Elemente.
                - :java:`skip(long n)`: Überspringt die ersten n Elemente.

            .. card::

                .. example::

                    .. code:: java
                        :number-lines:
                        :line-number-digits: 2
                        :class: copy-to-clipboard

                        import java.util.List;
                        import java.util.stream.Collectors;
                        import java.util.stream.Stream;

                        List<Integer> lst = Stream.of(9, 0, 3, 1, 7, 3, 4, 7, 2, 8, 5, 0, 6, 2)
                            .distinct()
                            .sorted((i, j) -> i - j)
                            .skip(1)
                            .limit(3)
                            .toList();

                    .. container:: incremental

                        *Ausgabe:* :console:`is ==> [1, 2, 3]`

    .. card::

        .. rubric:: Terminale Operationen

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

                .. example:: :java:`forEach`

                    .. code:: java
                        :line-number-digits: 1
                        :number-lines:
                        :class: copy-to-clipboard

                        Stream.of(9, 0, 3, 1, 7, 3, 4, 7, 2, 8, 5, 0, 6, 2)
                            .distinct()
                            .sorted((i,j) -> i-j)
                            .limit(3)
                            .forEach(e -> IO.print(e + " "));

                    .. container:: incremental

                        *Ausgabe:* :console:`0 1 2`

            .. card::

                .. example:: :java:`toArray`

                    .. code:: java
                        :number-lines:
                        :line-number-digits: 1
                        :class: copy-to-clipboard

                        int[] a = IntStream.range(1, 3).toArray();

                        Object[] a = Stream.of("1", "2", "3").map( Integer::parseInt ).toArray();

                        Integer[] a = (Integer[]) Stream.of(1, 2, 3).toArray();

                        String[] a = Stream.of(1, 2, 3).map( (i) -> i.toString() )
                                            .toArray( String[]::new ); // using generator

            .. card::

                .. supplemental::

                    .. rubric:: Terminale Operationen mit Kollektions-Ergebnis

                    -   Die Methode :java:`collect` erzeugt eine Kollektion aus den Elementen des Streams.
                    -   :java:`IntStream` und andere Streams mit primitiven Daten haben keine entsprechende Operation.
                    -   Das Argument von :java:`collect` ist ein :java:`java.util.stream.Collector`. Die Erzeugung einer Kollektion ist damit Sonderfall einer aggregierenden Operation.
                    -   Für die Erzeugung einer Kollektion verwendet man typischerweise einen vordefinierten :java:`Collector` aus der Klasse :java:`java.util.stream.Collectors`.
                    -   Einfache Kollektionserzeuger in Collectors sind:

                        - :java:`toList()`
                        - :java:`toSet()`
                        - :java:`toCollection(Supplier<C> collectionFactory)`

                .. example:: :java:`collect`

                    .. deck::

                        .. card::

                            .. code:: java
                                :number-lines:
                                :line-number-digits: 2
                                :class: copy-to-clipboard

                                List<Integer> l1 = Stream.of(1, 2, 3).collect( Collectors.toList() );

                                List<Integer> l2 = IntStream.range(1, 4).boxed()
                                        .collect( Collectors.toList() );

                                Set<String> s1 = (Set<String>) Stream.of("1", "2", "3")
                                        .collect( Collectors.toSet() );

                                Set<String> s2 = (Set<String>) Stream.of("1", "2", "3")
                                        .collect( Collectors.toCollection( HashSet::new ) );

                        .. card::

                            .. code:: java
                                :number-lines:
                                :line-number-digits: 1
                                :class: copy-to-clipboard

                                // Generating a map from a stream of strings

                                Map<String, Integer> m = Stream.of("1", "2", "3")
                                        .collect(
                                            Collectors.toMap(
                                                Function.identity(), // (s) -> s
                                                Integer::parseInt
                                            )
                                        );

                            .. container:: incremental

                                *Resultat:* :console:`m ==> {1=1, 2=2, 3=3}`

            .. card::

                In :java:`Collectors` finden sich **Kollektoren mit denen Maps erzeugt werden können**, die eine Gruppierung bzw. eine Partitionierung der Stream-Elemente darstellen:

                - :java:`static <T,K> Collector<T,?,Map<K,List<T>>> groupingBy(Function<? super T,? extends K> classifier)`

                  Gruppiert die Elemente entsprechend einer Klassifizierungsfunktion.

                - :java:`static <T> Collector<T,?,Map<Boolean,List<T>>> partitioningBy(Predicate<? super T> predicate)`

                  Partitioniert die Elemente entsprechend einem Prädikat.

            .. card::

                .. example:: :java:`collect(groupingBy)`

                    .. supplemental::
                        :embed-in-document-flow:

                        .. rubric:: Import hilfreicher Methoden

                        .. code:: java
                            :number-lines:
                            :line-number-digits: 1
                            :class: copy-to-clipboard

                            import static java.util.stream.Collectors.groupingBy;
                            import static java.util.stream.Collectors.partitioningBy;
                            import static java.util.stream.Collectors.counting;

                    .. code:: java
                        :number-lines:
                        :line-number-digits: 1
                        :class: copy-to-clipboard

                        Map<Integer, List<Integer>> groupedByMod3 = Stream.of(1, 2, 3, 4, 5, 6 ,7 ,8, 9)
                                .collect( groupingBy( (x) -> x%3 ) );

                    .. container:: incremental

                        *Resultat:* :console:`groupedByMod3 ==> {0=[3, 6, 9], 1=[1, 4, 7], 2=[2, 5, 8]}`

                    .. code:: java
                        :number-lines:
                        :line-number-digits: 1
                        :class: copy-to-clipboard incremental

                        Map<Integer, List<String>> groupedByLength =
                            Stream.of("one", "two", "three", "four", "five", "six", "seven", "eight")
                                .collect( groupingBy( (s) -> s.length() ) );

                    .. container:: incremental

                        *Resultat:* :console:`groupedByLength ==> {3=[one, two, six], 4=[four, five], 5=[three, seven, eight]}`

            .. card::

                Das Interface :java:`Stream` bzw. die Interfaces für Ströme primitiver Daten (:java:`IntStream`, etc.) bieten einige **aggregierende Funktionen für Standardoperationen** auf allen Elementen des Stroms.

                .. example::
                    :class: incremental

                    .. code:: java
                        :number-lines:
                        :line-number-digits: 1
                        :class: copy-to-clipboard

                        long count = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).count();

                        long sum = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).sum();

                        OptionalDouble av = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).average();

            .. card::

                Das Interface :java:`Stream` bieten einige **aggregierende Funktionen für den Test aller Elemente des Stroms** mit einem übergebenen Prädikat.

                .. example::
                    :class: incremental

                    .. code:: java
                        :number-lines:
                        :line-number-digits: 1
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
                        :line-number-digits: 1
                        :class: copy-to-clipboard

                        Optional<Integer> firstEven = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                                .filter( (x) -> x%2 == 0 )
                                .findFirst();

                    .. container:: incremental

                        *Ausgabe:* :console:`firstEven ==> Optional[2]`

            .. card::

                Das Interface :java:`Stream` bietet die Funktion

                    :java:`Optional<T> reduce(BinaryOperator<T> accumulator)`

                mit der eine Funktion auf jedes Element und das bisherige Zwischenergebnis  angewendet werden kann.

                Falls der erste Wert nicht der Startwert sein soll, verwendet man:

                    :java:`Optional<T> reduce(T identity, BinaryOperator<T> accumulator)`

            .. card::

                .. example:: :java:`reduce`

                    .. code:: java
                        :number-lines:
                        :line-number-digits: 1
                        :class: copy-to-clipboard

                        Optional<Integer> sumOfAll = Stream.of(1, 2, 3, 4, 5).reduce( (a, x) -> a+x );

                    .. container:: incremental

                        *Resultat:* :console:`sumOfAll ==> Optional[15]`

                    .. code:: java
                        :number-lines:
                        :line-number-digits: 1
                        :class: copy-to-clipboard incremental

                        Optional<Integer> subOfAll = Stream.of(1, 2, 3, 4, 5).reduce( (a, x) -> a-x );

                    .. container:: incremental

                        *Resultat:* :console:`subOfAll ==> Optional[-13]`

                    .. code:: java
                        :number-lines:
                        :line-number-digits: 1
                        :class: copy-to-clipboard incremental

                        int sumOfAllPlus100 = Stream.of(1, 2, 3, 4, 5).reduce(100, (a, x) -> a+x );

                    .. container:: incremental

                        *Resultat:* :console:`sumOfAllPlus100 ==> 115`

                    .. compound::
                        :class: fade-out

                        In den Beispielen verwenden wir :java:`a` als Variablenname für den ersten Parameter, da dies der Akkumulator ist, der das Zwischenergebnis enthält.

            .. card::

                Es gibt einen Kollektor mit dem String-Elemente zu einem String konkateniert werden können:

                    :java:`static Collector<CharSequence,?,String> joining(CharSequence delimiter)`

                .. example:: :java:`collect(joining(…))`
                    :class: incremental

                    .. code:: java
                        :number-lines:
                        :line-number-digits: 1
                        :class: copy-to-clipboard

                        String concat = Stream.of("one", "two", "three")
                                .collect( joining("+") );

                    .. container:: incremental

                        *Resultat:* :console:`concat = "one+two+three"`



Streams - fortgeschrittenere Konzepte
------------------------------------------------

.. deck::

    .. card::

        .. rubric:: Ausgewählte Eigenschaften des *Basisinterface* aller Streams

        Parallele und sequentielle Streams.

        .. code:: java
            :line-number-digits: 2
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
            :line-number-digits: 2
            :number-lines: 13
            :class: incremental

            public interface Stream<T> extends BaseStream<T, Stream<T>> {
                // ...
            }

        .. supplemental::

            Die Interfacedefintion (:java:`BaseStream<T, S extends BaseStream<T,S>>`) ist eine Anwendung des *Curiously Recurring Template Pattern*\s (*CRTP*). Bei diesem Idiom haben wir eine Klasse :java:`X`, die von einer  generischen Klasse oder einem generischen Interface :java:`S` abgeleitet wird, wobei die ableitende Klasse :java:`X` sich selber als Typparameter verwendet. Dies erlaubt die Definition einer *Fluent-API*, bei der Methoden, die in der Basisklasse definiert sind, den abgeleiteten Typ zurückgeben.

    .. card::

        .. rubric:: Erzeugen von eigenen Streams mittels `StreamSupport <https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/StreamSupport.html>`__

        Die Implementierung des Interfaces :java:`Stream<T>` ist ggf. sehr aufwändig. Alternativ kann die Klasse :java:`StreamSupport` verwendet werden, um auf einem :java:`Spliterator` basierende Streams zu erzeugen.

        .. code:: java
            :number-lines:
            :line-number-digits: 1

            package java.util.stream;

            public final class StreamSupport {

                /** Creates a new sequential or parallel Stream from a Spliterator. */
                static <T> Stream<T> stream(Spliterator<T> spliterator, boolean parallel);

                // ...
            }



.. class:: exercises

Übung - Streams - erste Schritte
--------------------------------

.. exercise:: Streams und Lambda Ausdrücke

    .. code:: java
        :number-lines:
        :class: copy-to-clipboard

        List<Integer> punkte = Arrays.asList(85, 42, 91, 67, 55);

    .. class:: list-with-explanations

    #.  Filtere mit einem Lambda die Liste :java:`punkte`, sodass nur Noten >= 60 übrig bleiben, und gib die bestandenen Noten aus.

        Hinweis: Verwende :java:`stream()`, :java:`filter()` und :java:`forEach()`.
    #.  Erstelle ein :java:`Predicate<Integer>`-Lambda namens :java:`isExcellent`, das :java:`true` zurückgibt bei 90 oder mehr Punkten. Gib dann alle ausgezeichneten Noten aus.
    #.  Erstelle ein :java:`Function<Integer, String>`-Lambda namens :java:`toLetterGrade`, das die Punkte in einen Buchstaben umwandelt: 90+ → "A"; 80-89 → "B"; 70-79 → "C"; 60-69 → "D"; unter 60 → "F"; gib dann die Punkte zusammen mit der Note aus.
    #.  Berechne den Durchschnitt aller Noten und gib diese aus.

        Hinweis: Verwende :java:`stream()` und :java:`mapToInt()` und schaue Dir die `API <https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/stream/IntStream.html>`__ von :java:`IntStream` an.

    .. solution::
        :pwd: that_was_easy...

        .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            List<Integer> punkte = Arrays.asList(85, 42, 91, 67, 55);

            // 1.
            punkte.stream().filter(x -> x>= 60).forEach(IO::println);

            // 2.
            Predicate<Integer> isExcellent = (i) -> i >= 90;
            punkte.stream().filter(isExcellent).forEach(IO::println);

            // 3.
            Function<Integer,String> toLetterGrade =
                (i) -> switch(i/10) {
                            case 9  -> "A";
                            case 8  -> "B";
                            case 7  -> "C";
                            case 6  -> "D";
                            default -> "F"; };
            var punkteZuNote = punkte.stream().collect(Collectors.toUnmodifiableMap(Function.identity(), toLetterGrade));
            IO.println(punkteZuNote);
            // Resultat: {91=A, 67=D, 55=F, 85=B, 42=F}

            punkte.stream().mapToInt(Integer::intValue).average().ifPresent(IO::println);



.. class:: exercises

Übung - Streams
-----------------------------------

.. exercise:: Java Streams

    1.  Schreiben Sie eine Methode :java:`int sumOfSquares(int[] a)` die die Elemente des Arrays quadriert und dann die Summe berechnet.

    2.  Schreiben Sie eine Methode :java:`int sumOfSquaresEven(int[] a)` die die Summe der quadrierten Elemente bildet, für die das Ergebnis der Quadrierung gerade ist.

    3.  Schreiben Sie eine Methode, die eine Liste von Strings (:java:`List<String>`) in eine flache Liste von Zeichen-Codepoints (:java:`List<Integer>`) umwandelt.

        .. remark::

            :java:`String.chars()` liefert einen :java:`IntStream` von Codepoints.

    .. important::

        Verwenden Sie ausschließlich Streams und Lambda-Ausdrücke.

    .. solution::
        :pwd: QuadrierteStreams

        1.  Lösung

            .. code:: java
                :number-lines:
                :class: copy-to-clipboard

                // Alternative Lösungen:
                Arrays.stream(new int[]{1,2,3}).map(x -> x * x).sum();
                Arrays.stream(new int[]{1,2,3}).map(x -> x * x).reduce(0, (x,y) -> x + y);
                Arrays.stream(new int[]{1,2,3}).reduce(0,(x,y) -> x + y*y);


        2.  Lösung

            .. code:: java
                :number-lines:
                :class: copy-to-clipboard

                Arrays.stream(new int[]{1,2,3,4})
                    .map(x -> x * x)
                    .filter(x -> x % 2 == 0 )
                    .sum()

                // Alternativ mit reduce
                Arrays.stream(new int[]{1,2,3,4})
                    .map(x -> x * x)
                    .filter(x -> x % 2 == 0 )
                    .reduce(0, (x,y) -> x + y);

        3.  Lösung

            .. code:: java
                :number-lines:
                :class: copy-to-clipboard

                var l = List.of("Hello", "World");

                // Variante mit explizitem Lambda, das auch unmittelbar das Boxing via boxed durchführt:
                var r1 = l.stream().flatMap((String x) -> x.chars().boxed()).collect(Collectors.toList());
                // Variante mit flatMapToInt, *Method Reference* und nachgelagertem Boxing via boxed()
                var r2 = l.stream().flatMapToInt(String::chars).boxed().toList();



.. class:: new-section transition-move-to-top

Bewertung der Fähigkeiten der Standardoperationen von Java Streams
--------------------------------------------------------------------



Herausforderung *Low-representational Gap* gelöst?
---------------------------------------------------

.. rubric:: Lösung mit standard *Stream* Operationen (Java 8 - 26)

.. supplemental:: d-only
    :embed-in-document-flow:

    *Benötigte Imports*

    .. code:: java
        :line-number-digits: 1
        :number-lines:
        :class: copy-to-clipboard

        import static java.util.Comparator.comparingDouble;
        import static java.util.function.Predicate.not;
        import static java.util.stream.Collectors.groupingBy;
        import static java.util.stream.Collectors.minBy;

*Geschäftslogik*

.. code:: java
    :number-lines:
    :line-number-digits: 1
    :class: copy-to-clipboard

    List<Student> empfehlungenS =
        alleStudierenden.stream()
            .filter(not(Student::hatStipendium))
            .collect(groupingBy(Student::studiengang,
                                minBy(comparingDouble(Student::schnitt))))
            .values().stream().flatMap(Optional::stream)
            .sorted(comparingDouble(Student::schnitt))
            .toList();

.. supplemental::
    :embed-in-document-flow:

    .. assessment::

        Wir sind näher an der Geschäftslogik, aber technische Artefakte scheinen noch durch!

        - Um die gewünschte Gruppierung zu erhalten, werden die besten Studierenden in einer Zwischendatenstruktur (*Map*) aufgesammelt (:java:`collect(…)`). Diese muss - um eine Stream-orientierte Weiterverarbeitung zu ermöglichen - wieder in einen Stream verwandelt werden, der über den *Values* der *Map* operiert (:java:`values().stream()`).

        - Da wir in eine *Map* aufgesammelt hatten, haben wir einen *Stream of Optionals*; :java:`minBy(…)` liefert ein :java:`Optional`. Somit müssen wir die :java:`Optional`\ s im :java:`Stream<Optional<Student>>` über :java:`flatMap(Optional::stream)` entpacken.



.. class:: new-section transition-fade

Benutzerdefinierte Intermediate Operations: Stream Gatherers
------------------------------------------------------------------------

.. supplemental::

    Im Folgenden verwenden wir die Begriffe *Custom Intermediate Operations* und *Stream Gatherers* faktisch synonym.



Welches Problem wollen wir lösen?
------------------------------------

.. code:: java
    :number-lines:
    :line-number-digits: 1
    :class: head fade-out-strong no-margin

    List<Student> empfehlungenS = alleStudierenden.stream()
        .filter(not(Student::hatStipendium))

.. code:: java
    :line-number-digits: 1
    :number-lines: 3
    :class: snippet no-margin

        .collect(groupingBy(Student::studiengang,
                            minBy(comparingDouble(Student::schnitt))))
        .values().stream().flatMap(Optional::stream)

.. code:: java
    :line-number-digits: 1
    :number-lines: 6
    :class: tail fade-out-strong

        .sorted(comparingDouble(Student::schnitt))
        .toList();

.. compound::
    :class: accentuate incremental

    Um (hier) den Bruch in der Pipeline (Stream → List → Stream) zu vermeiden, benötigen wir eine passende *Intermediate Operation*, die von der Standardbibliothek nicht zur Verfügung gestellt wird.



`Stream Gatherers (JDK 24+) <https://docs.oracle.com/en/java/javase/26/core/stream-gatherers.html>`__
------------------------------------------------------------------------------------------------------------

Gatherers erlauben die Definition von benutzerdefinierten *Intermediate Operations*.

:java:`Gatherer<T, A, R>` bestehen aus vier Komponenten:

.. class:: incremental-list

:`initializer`:java:: Erzeugt den privaten, Gatherer-internen, ggf. veränderlichen Zustand (Typ :java:`A`).
:`integrator`:java:: Verarbeitet jedes Eingabeelement (:java:`T`) und kann beliebig viele Ausgabeelemente (:java:`R`) *downstream* senden.
:`combiner`:java:: Vereinigt Zustände bei paralleler Ausführung *(optional)*.
:`finisher`:java:: Wird nach dem letzten Element aufgerufen; kann finale Elemente *downstream* senden *(optional)*.

.. class:: incremental-list

- Damit lassen sich *zustandsbehaftete*, *short-circuiting* und *m:n*-Transformationen als wiederverwendbare, komponierbare, parallelisierbare Bausteine definieren.

- Eingebunden über: :java:`stream.gather(myGatherer)`


.. container:: s-only

    → `Benutzerdefinierter Stream Gatherer: reducePerGroup(…)`_



Ausgewählte Standard Stream Gatherers
---------------------------------------

.. deck::

    .. card::

        .. rubric:: :java:`Gatherers.scan(…)`

        Der aktuelle Zustand wird basierend auf dem aktuellen Element aktualisiert. Der initiale Zustand kann explizit angegeben werden.

        .. code:: java
            :number-lines:
            :line-number-digits: 1
            :class: copy-to-clipboard

            Stream<Integer> numbers = Stream.of(1, 2, 3, 4);
            List<Integer> resultList =
                    numbers.gather(Gatherers.scan(() -> 0, Integer::sum)).toList();
            // resultList => List.of(1, 3, 6, 10)

    .. card::

        .. rubric:: :java:`Gatherers.windowFixed(…)`

        Der Stream wird in Blöcke fester Größe aufgeteilt und diese werden dann *downstream* weitergereicht.

        .. code:: java
            :number-lines:
            :line-number-digits: 1
            :class: copy-to-clipboard

            Map<String,String> nameToPoints =
                Stream.of("A","90+","B","80+")
                    .gather(Gatherers.windowFixed(2))
                    .collect(Collectors.toMap((l) -> l.get(0),(l) -> l.get(1)));
            // nameToPoints => {A=90+, B=80+}



Benutzerdefinierter Stream Gatherer: reducePerGroup(…)
----------------------------------------------------------------

.. code:: java
    :line-number-digits: 2
    :number-lines:
    :class: head no-margin incremental-code

    static <T, K> Gatherer<T, ?, T> reducePerGroup(
                Function<T, K> grouping, BinaryOperator<T> reducer) {

        return Gatherer.ofSequential(
            /*initializer:*/ HashMap<K, T>::new,
            /*integrator: */ (map, element, downstream) -> {
                map.merge(grouping.apply(element), element, reducer);
                return true; // <= we will consume more elements
            },

.. code:: java
    :line-number-digits: 2
    :number-lines: 10
    :class: tail incremental

            /*finisher:   */ (map, downstream) -> map.values().forEach(downstream::push)
        );
    }

.. supplemental::
    :embed-in-document-flow:

    Aufgrund der Verwendung von :java:`Gatherer.ofSequential(…)` müssen wir keinen :java:`combiner` angeben; Parallelisierung wird aber auch nicht unterstützt.


.. supplemental::
    :embed-in-document-flow:

    .. assessment::
        :class: incremental

        Obwohl es sich um einen spezialisierten *Custom Gatherer* handelt, der die Gruppierung nur intern durchführt und diese nicht explizit downstream verfügbar macht, ist es dennoch sehr gut vorstellbar, dass dieser wiederverwendet werden kann.





.. class:: new-section transition-move-to-top

Bewertung der Java Stream API
--------------------------------------------------------------------



Herausforderungen gelöst?
-------------------------

.. rubric:: Lösung mit :java:`reducePerGroup` *Gatherer*

.. supplemental:: d-only
    :embed-in-document-flow:

    .. code:: java
        :number-lines:
        :line-number-digits: 1
        :class: copy-to-clipboard

        import static java.util.Comparator.comparingDouble;
        import static java.util.function.Predicate.not;
        import static java.util.stream.Collectors.groupingBy;
        import static java.util.function.BinaryOperator.minBy;

.. code:: java
    :number-lines:
    :line-number-digits: 1
    :class: copy-to-clipboard

    List<Student> empfehlungenG = alleStudierenden.stream()
        .filter(not(Student::hatStipendium))
        .gather(reducePerGroup(
                    Student::studiengang,
                    minBy(comparingDouble(Student::schnitt))))
        .sorted(comparingDouble(Student::schnitt))
        .toList();


.. supplemental::
    :embed-in-document-flow:

    .. assessment::

        Die Umsetzung in Java ist noch einmal näher an der Geschäftslogik, aber der Code ist noch nicht parallelisiert und enthält noch immer einiges an syntaktischem Rauschen.



.. class:: exercises

Übung
----------

.. exercise:: Fakultät für 1 bis 20

    Berechnen Sie die Fakultät für n = 1 bis 20 und speichern Sie die Ergebnisse in einer Liste. Verwenden Sie Streams und einen passenden :java:`Gatherer`. Bedenken sie, dass ``20!`` bereits sehr groß ist!

    Bis zu welchem Wert können Sie bei der Verwendung eines passenden primitiven Datentyps ohne Präzisionsverlust die Fakultät von n (n!) berechnen.

    .. hint::

        Auf primitiven Streams ist :java:`.gather(…)` nicht verfügbar.

    .. solution::
        :pwd: LongStreamGathererscan

        Der Datentyp Long erlaubt maximal die Berechnung von 20!.

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            LongStream.rangeClosed(1,20).boxed().gather(Gatherers.scan(() -> 1l, (x,y) -> x * y)).toList();



.. class:: exercises

Übung
----------

.. exercise:: Fakultät mit BigInteger
    :formatted-title: Fakultät mit :java:`BigInteger`

    Berechnen Sie die Fakultät für n = 1 bis 100 und speichern Sie die Ergebnisse in einer Liste. Verwenden Sie Streams und einen passenden :java:`Gatherer`. Verwenden Sie :java:`BigInteger`, um Präzisionsverlust zu vermeiden.

    .. solution::
        :pwd: BigIntegerScanGatherer

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            List<BigInteger> factorials = LongStream.rangeClosed(1, 100)
                    .boxed()
                    .gather(Gatherers.scan(() -> BigInteger.ONE, (x,y) -> x.multiply(BigInteger.valueOf(y))))
                    .toList();



.. class:: new-section transition-fade

Parallelisierung von Streams
------------------------------


Effizienz von Parallelisierung
-------------------------------

.. epigraph::

    Als allgemeine Faustregel gilt, dass Geschwindigkeitssteigerungen in der Regel dann spürbar sind, wenn die Sammlung groß ist, typischerweise mehrere tausend Elemente umfasst.\ [#]_

    -- `Aleksandar Prokopec, Heather Miller - Parallel Collections <https://docs.scala-lang.org/overviews/parallel-collections/overview.html>`__

.. [#] Übersetzung aus dem Englischen von DeepL.



Korrektheit bei Parallelisierung
----------------------------------

.. deck::

    .. card::


        .. question::

            Welches Ergebnis erwarten wir:

            .. code:: java
                :class: copy-to-clipboard

                Arrays.stream(new int[]{1,2,3}).reduce(0,(x,y) -> x + y*y);
                                                         // x ist das aktuelle Zwischenergebnis und
                                                         // y ist der nächste Wert aus der Liste

        .. answer::
            :class: incremental

            14

    .. card::

        .. question::

            Welches Ergebnis erwarten wir:

            .. code:: java
                :class: copy-to-clipboard

                Arrays.stream(new int[]{1,2,3}).parallel().reduce(0,(x,y) -> x + y*y);

        .. answer::
            :class: incremental

            7226?\ [#]_

        .. [#] Das Ergebnis hängt von einigen Faktoren ab und kann variieren, ist aber wahrscheinlich nicht 14.

    .. card::

        .. rubric:: Ausgangscode

        .. code:: java
            :class: copy-to-clipboard
            :number-lines:
            :line-number-digits: 1

            IntBinaryOperator f = (x,y) -> x + y*y;
            Arrays.stream(new int[]{1,2,3}).parallel().reduce(0,f);

        .. deck::

            .. card::

                .. rubric:: Erklärung

                .. class:: incremental-list

                1. Die Parallelisierung\ [#]_ hat (hier) dazu geführt, dass die Liste in drei Teillisten aufgespalten wurde, um die Berechnung dafür zu parallelisieren. Danach wurde für jeden Wert der Teillisten zuerst eine Reduktion mit dem Basiswert :java:`0` durchgeführt; d. h. es wurde erst: :java:`f(0,1)=1`, :java:`f(0,2)=4` und :java:`f(0,3)=9` berechnet.
                2. Danach wurden die Zwischenergebnisse verrechnet. D. h. es wurde (*in diesem Falle*) :java:`f(f(0,2),f(0,3))=f(4,9)=85` berechnet und dann dieses Zwischenergebnis mit dem von :java:`f(0,1)` verrechnet: :java:`f(f(0,1),f(f(0,2),f(0,3)))=f(1,85)=7226`.

                .. [#] Im ersten Schritt wurde der Stream aufgeteilt, um die Berechnungen für jeden Teilstream parallel ausführen zu können.

            .. card:: s-overlay center-content backdrop-blur

                .. warning::

                    Die übergebene Reduktionsfunktion ``f`` verletzt die von der Stream API gestellten Bedingungen (Assoziativität).

            .. card::

                **1. Lösung** *mit Mutable Reduction* (:java:`collect(…)`)

                .. code:: java
                    :class: copy-to-clipboard

                    Arrays.stream(new int[]{1,2,3})
                            .parallel()
                            .collect(
                                    () -> new AtomicInteger(),
                                    (a,y) -> a.addAndGet(y*y),
                                    (a,v) -> a.addAndGet(v.get()));

            .. card::

                **2. Lösung** effizienter mit :java:`map(…)` und Reduktion über :java:`sum()`

                .. code:: java
                    :class: copy-to-clipboard

                    Arrays.stream(new int[]{1,2,3})
                            .parallel()
                            .map(x -> x * x)
                            .sum();



.. class:: exercises

Übung - Streams - Parallelisierung
-----------------------------------

.. exercise:: Java Streams

    .. remark::

        Verwenden Sie ausschließlich Streams und Lambda-Ausdrücke.

    Schreiben Sie eine Methode, die die Zahlen von :java:`1` bis :java:`Integer.MAX_VALUE` addiert. Nutzen Sie :java:`IntStream.rangeClosed(…)`, um die Zahlen zu iterieren. Messen Sie die Ausführungsdauer für die *sequentielle* und *parallele* Ausführung (siehe Anhang für eine entsprechende Methode zur Zeitmessung.)

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

            time(() -> IO.println(sumOfSquares(new int[]{1,2,3,4,5,6,7,8,9,0})));

    .. solution::
        :pwd: in+Parallel

        **Lösung**

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            // sequential
            time (() -> IntStream.rangeClosed(1,Integer.MAX_VALUE).reduce(0, (x,y) -> x + y));
            time (() -> IntStream.rangeClosed(1,Integer.MAX_VALUE).sum());
            time (() -> IntStream.rangeClosed(1,100).sum());

            // parallel
            time (() -> IntStream.rangeClosed(1,Integer.MAX_VALUE).parallel().reduce(0, (x,y) -> x + y));
            time (() -> IntStream.rangeClosed(1,Integer.MAX_VALUE).parallel().sum());
            time (() -> IntStream.rangeClosed(1,100).parallel().sum());

        In Abhängigkeit von Ihrer CPU (d. h. der Anzahl an echten Kernen) sollte eine Beschleunigung bei der Parallelisierung zu beobachten sein.



.. class:: new-section transition-fade

Java Streams - abschließende Betrachtung
-----------------------------------------


Grundlegender Aufbau und Bewertung
--------------------------------------

.. compound::
    :class: accentuate

    Die drei erweiterbaren Bausteine der Java Stream API:

    .. class:: center-content

    ::

        Stream pipeline = Spliterator + Gatherer? + Collector

- (Java) Streams unterstützen die Verarbeitung von (Massen-)Daten durch die Anwendung von funktionalen und deklarativen Konzepten.

- Dies ermöglicht eine Umsetzung von fachlichen Konzepten in Java Code mit einer geringeren Repräsentationslücke (:eng:`Low representational gap`).

- Eine effiziente - weitgehend - transparente Parallelisierung ist möglich.



.. class:: new-section transition-flip

Vergleich von Javas Stream-API mit vergleichbaren APIs anderer Sprachen
------------------------------------------------------------------------



Java Streams vs. Scala Collections — Codebeispiel
----------------------------------------------------

.. grid::

    .. cell:: width-50

        .. rubric:: |java-icon| Java (mit Gatherer)

        .. code:: java
            :number-lines:
            :line-number-digits: 2

            alleStudierenden.stream()
              .filter(not(Student::hatStipendium))
              .gather(reducePerGroup(
                  Student::studiengang,
                  minBy(
                    comparingDouble(
                      Student::schnitt))))
              .sorted(comparingDouble(
                  Student::schnitt))
              .toList();

    .. cell:: width-50

        .. rubric:: |scala-icon| Scala

        .. code:: scala
            :number-lines:
            :line-number-digits: 2

            alleStudierenden
              .filterNot(_.hatStipendium)
              .groupBy(_.studiengang)
              .values
              .map(_.minBy(

                  _.schnitt)) //=>Iterable[Student]
              .toList
              .sortBy(_.schnitt)

.. supplemental::
    :embed-in-document-flow:

    .. assessment::
        :class: dd-margin-left-3em

        :Syntaktisches Rauschen: Java erfordert :java:`.stream()` / :java:`.toList()` als Rahmen, Typnamen in Method References (:java:`Student::schnitt`) und Wrapper wie ``comparingDouble``. Scalas ``_``-Platzhalter und die direkte Arbeit auf Collections eliminieren diesen Overhead.

        :Gruppierung: In Scala ist :scala:`groupBy` eine normale Collection-Methode. Allerdings erzeugt :scala:`groupBy` eine Map, die anschließend über :scala:`.values` wieder zu einem Iterable heruntergebrochen werden muss — ein milderer Bruch als Javas traditioneller :java:`collect().values().stream()`\-Ansatz über einen Collector, aber strukturell verwandt. Seit Java 24 kann die Gatherer-API verwendet werden, wodurch in Java dieser Bruch ganz vermieden werden kann.

        :Vergleich und Reduktion: :scala:`_.minBy(_.schnitt)` in Scala vs. :java:`minBy(comparingDouble(Student::schnitt))` in Java — die explizite Typmaschinerie des Java-Typsystems ist hier deutlich sichtbar.

        :Lazy vs. Eager: Scalas Code wird sofort ausgewertet — :scala:`groupBy` erzeugt *sofort* eine :scala:`Map`. Javas Pipeline hingegen ist vollständig lazy; erst :java:`.toList()` löst die Berechnung aus. Für große Datenmengen kann das ein relevanter Unterschied sein.

.. supplemental::

    .. rubric:: Vollständiger Scala Code

    Der folgende Code kann direkt in der Scala REPL (:console:`scala-cli`)  ausgeführt werden.

    .. include:: code/Students.scala
        :code: scala
        :number-lines:
        :class: copy-to-clipboard



Java Streams vs. Scala Collections — Konzeptioneller Vergleich
----------------------------------------------------------------

.. csv-table::
    :header: "", "Java Streams", "Scala Collections"
    :stub-columns: 1
    :widths: 12, 20, 20
    :class: dhbw incremental-table-rows sticky-header

    "Pipeline-Modell", "deklarativ, verkettet", "deklarativ, verkettet"
    "Funktionen als Parameter", "Lambdas, Method References", "Funktionsliterale, Platzhalter ``_``"
    "Quelle unverändert", "Ja", "Ja"
    "Streams = Collections?","Nein — separates Konzept, explizites :java:`.stream()`/:java:`.toList()`", "Ja — Operationen direkt auf Collections"
    "Auswertung", "Lazy (erst bei Terminal Op)", "Eager (sofort); :scala:`LazyList` als Opt-in"
    "Erweiterbarkeit", "via Collectors und Gatherers", "umfangreiche Standardbibliothek; ggf. *Extension Methods*"
    "Parallelisierung", "Tief integriert (:java:`.parallelStream()`)", "Separate Bibliothek (:scala:`.par`) seit Scala 2.13/3"

.. supplemental::

    Scala verfolgt einen anderen Designansatz: Collections *sind* die Pipeline. Es gibt keine Trennung zwischen Datenstruktur und Transformations-API. Das macht den Einstieg einfacher und den Code kürzer, bedeutet aber auch, dass *Laziness* explizit gewählt werden muss (:scala:`<Collection>.to(LazyList)`).

    Für Scala gibt es auch noch weitere 3rd Party Stream APIs wie `Akka Streams <https://doc.akka.io/docs/akka/current/stream/index.html>`__ oder `fs2 <https://fs2.io/>`__, die verschiedene Trade-offs zwischen Einfachheit, Leistungsfähigkeit und Funktionalität bieten. Hier haben wir jedoch „nur“ solche APIs betrachtet, die direkt in der Standardbibliothek enthalten sind.

    Javas Trennung in eine Collection API und eine Stream API führt zu syntaktisch komplexerem Code (:eng:`verbose`), ermöglicht dafür aber *Lazy Evaluation* als Standard und eine tief integrierte Parallelisierung.

    .. remark::

        Zwischen Scala 2.x und Scala 3 hat sich die Art wie man *Extension Methods* implementiert verändert. In Scala 2 erfolgte dies über Implizite Klassen (:scala:`implicit class`); in Scala 3 gibt es dafür den entsprechenden Sprachmechanismus (:scala:`extension`).


Java Streams vs. JavaScript Arrays — Codebeispiel
----------------------------------------------------

.. grid::

    .. cell:: width-50

        .. rubric:: |java-icon| Java (mit Gatherer)

        .. code:: java
            :number-lines:
            :line-number-digits: 2

            alleStudierenden.stream()
              .filter(not(Student::hatStipendium))
              .gather(reducePerGroup(
                  Student::studiengang,
                  minBy(
                    comparingDouble(
                      Student::schnitt))))

              .sorted(comparingDouble(
                  Student::schnitt))
              .toList();

    .. cell:: width-50

        .. rubric:: |javascript-icon| JavaScript

        .. code:: javascript
            :number-lines:
            :line-number-digits: 2

            Object.values(
                Object.groupBy(
                  alleStudierenden.
                    filter(s => !s.hatStipendium),
                  s => s.studiengang) ).
              map(gruppe =>
                gruppe.reduce((a, b) =>
                  a.schnitt < b.schnitt ? a : b)).
              toSorted((a, b) =>
                a.schnitt - b.schnitt);

.. supplemental::
    :embed-in-document-flow:

    .. assessment::
        :class: dd-margin-left-3em

        :*Pipeline-Bruch* durch `Object.groupBy`:js:: Die Gruppierung ist in JavaScript eine statische Funktion, keine Methode auf Arrays. Der Datenfluss muss *um* das Array herumgeschrieben werden statt linear verkettet zu bleiben. In Java vor Gatherers bestand dasselbe Problem; seit Java 24 ist die Pipeline durchgängig.

        :JavaScript hat kein ``minBy``: Die Vergleichslogik muss manuell im Reducer formuliert werden (:js:`a.schnitt < b.schnitt ? a : b`). Das ist weniger deklarativ als Javas :java:`minBy(comparingDouble(…))`.

        :Arrow Functions vs. Method References: :js:`s => !s.hatStipendium` vs. :java:`not(Student::hatStipendium)` — JavaScript kürzer, aber ohne Typsicherheit.

        :Eager Evaluation: Jede JavaScript-Operation erzeugt ein vollständiges Zwischenarray. :js:`filter` kopiert alle passenden Elemente in ein neues Array, bevor :js:`Object.groupBy` überhaupt beginnt. Javas Pipeline verarbeitet jedes Element einmal durch alle Stufen — ohne Zwischenspeicherung.

.. supplemental::

    .. rubric:: Vollständiger JavaScript Code

    Der folgende Code kann direkt in einer aktuellen Version von Node.js ausgeführt werden.

    .. include:: code/Students.js
        :code: javascript
        :number-lines:
        :class: copy-to-clipboard



Java Streams vs. JavaScript Arrays — Konzeptioneller Vergleich
----------------------------------------------------------------

.. story::

    .. csv-table::
        :header: "", "Java Streams", "JavaScript Arrays"
        :stub-columns: 1
        :widths: 12, 20, 20
        :class: dhbw sticky-header incremental-table-rows

        "Pipeline-Modell", "deklarativ, verkettet", "deklarativ, verkettet"
        "Funktionen als Parameter", "Lambdas, Method References", "Arrow Functions"
        "Quelle unverändert", "Ja", "Ja (Achtung: :js:`sort()` mutiert!)"
        "Streams = Collections?", "Nein — separates Konzept", "Ja — Operationen direkt auf :js:`Array`"
        "Auswertung", "Lazy (erst bei Terminal Op)", "Eager (Zwischenarrays bei jedem Schritt)"
        "Erweiterbarkeit", "via Collectors und Gatherers", "*„Keine“*\ [#]_ — fester API-Satz"
        "Parallelisierung", "integriert (:java:`.parallelStream()`)", "Nein -  Single-threaded"
        "Unendliche Sequenzen", "Ja (z. B. :java:`Stream.iterate`)", "Nein - Generatoren nicht in Array-API integriert"
        "Typsicherheit", "Compile-time", "Keine — Fehler erst zur Laufzeit"

.. supplemental::

    JavaScripts Array-Methoden wie :js:`filter`, :js:`map` und :js:`reduce` sind dem Stream-Modell oberflächlich sehr ähnlich. Der entscheidende Unterschied liegt in der Auswertungsstrategie: jede Array-Operation erzeugt sofort ein vollständiges Zwischenarray. Bei :js:`array.filter(…).map(…)` werden während der Auswertung *zwei* neue Arrays erzeugt. Javas Lazy-Pipeline *fusioniert* die Schritte und erzeugt keine Zwischenergebnisse.

    Ein weiterer fundamentaler Unterschied: :js:`Array.prototype.sort()` mutiert das Array in-place — ein Bruch mit dem ansonsten funktionalen Charakter der Array-API. Erst seit ES2023 gibt es mit :js:`toSorted()` eine nicht-mutierende Alternative.

.. [#] Eine Erweiterung über :js:`Array.prototype` ist möglich aber nicht empfehlenswert.



Java Streams vs. Rust Iteratoren — Codebeispiel
--------------------------------------------------

.. grid::

    .. cell:: width-50

        .. rubric:: |java-icon| Java (mit Gatherer)

        .. code:: java
            :number-lines:
            :line-number-digits: 2

            alleStudierenden.stream()
              .filter(not(Student::hatStipendium))
              .gather(reducePerGroup(
                  Student::studiengang,


                  minBy(
                    comparingDouble(
                      Student::schnitt))))
              .sorted(comparingDouble(
                  Student::schnitt))
              .toList();

    .. cell:: width-50

        .. rubric:: |rust-icon| Rust (mit itertools)

        .. code:: rust
            :number-lines:
            :line-number-digits: 2

            alle_studierenden.iter()
              .filter(|s| !s.hat_stipendium)
              .into_group_map_by(
                  |s| &s.studiengang)
              .into_values()
              .map(|g| g.into_iter()
                .min_by(|a, b|
                  a.schnitt.total_cmp(&b.schnitt))
                .unwrap())
              .sorted_by(|a, b|
                a.schnitt.total_cmp(&b.schnitt))
              .collect::<Vec<_>>();

        .. supplemental::

            In Rust ist :rust:`&` der Referenzoperator. D. h. in Zeile 2 wird eine Referenz auf den String für den Studiengang übergeben. Die Zielmethode kann diese Referenz lesen, aber nicht verändern.

.. supplemental::
    :embed-in-document-flow:

    .. assessment::
        :class: dd-margin-left-3em

        :Zero-Costs: Rusts Pipeline kompiliert zu praktisch dem selben Maschinencode wie eine handgeschriebene :rust:`for`-Schleife — keine Heap-Allokationen, keine virtuellen Dispatches. Javas Pipeline erzeugt Overhead, der vom JIT-Compiler erst zur Laufzeit *teilweise* optimiert wird.

        :Expliziter Float-Vergleich: Rust erzwingt :rust:`total_cmp` statt eines einfachen :rust:`<`-Vergleichs, weil Gleitkommazahlen :rust:`NaN` enthalten können. Javas :java:`comparingDouble(…)` behandelt :java:`NaN` stillschweigend; ein potenziell versteckter Bug.

        :Ownership vs. Runtime-Schutz: :rust:`into_iter()` in Rust *konsumiert* die Gruppe — ein erneuter Zugriff ist ein Compile-Fehler. Javas Stream wirft erst zur Laufzeit eine :java:`IllegalStateException` bei Mehrfachnutzung.

        :Erweiterbarkeit: Rust benötigt kein Gatherer-Konzept. Einen neuen Iterator zu bauen erfordert nur die Implementierung von :rust:`next()` — alle ~75 Adapter-Methoden (:rust:`filter`, :rust:`map`, :rust:`take_while`, …) stehen dann automatisch zur Verfügung. Das ``itertools``-Crate erweitert dies nahtlos über Rusts Trait-System.

        :Parallelisierung:

            In Java genügt :java:`.parallelStream()`; in Rust ersetzt man :rust:`iter()` durch :rust:`par_iter()` (via ``rayon``-Crate). Der entscheidende Unterschied: Rusts Ownership-System garantiert *zur Compile-Zeit*, dass keine Data Races entstehen. Javas Fork/Join-Pool kann das nicht.

            Es gilt zu beachten, dass der Fehler bei der nicht-assoziativen reduce-Funktion (`Korrektheit bei Parallelisierung`_) auch in Rust möglich wäre, da er auf einem logischen Fehler (Vertragsverletzung) beruht, nicht auf einer Race Condition."


.. supplemental::

    .. rubric:: Vollständiges Beispiel in Rust

    .. include:: code/Students.rs
        :code: rust
        :class: copy-to-clipboard

    Zum Ausführen des Codes kann `Play Rust-Lang.org <https://play.rust-lang.org/?version=stable&mode=debug&edition=2024&gist=9c8cc9e32e4c4473dd84728ee3e86ff3>`__ verwendet werden.




Java Streams vs. Rust Iteratoren — Konzeptioneller Vergleich
----------------------------------------------------------------

.. story::

    .. csv-table::
        :header: "", "Java Streams", "Rust Iteratoren"
        :stub-columns: 1
        :widths: 12, 20, 20
        :class: dhbw sticky-header incremental-table-rows

        "Pipeline-Modell", "deklarativ, verkettet", "deklarativ, verkettet"
        "Funktionen als Parameter", "Lambdas, Method References", "Closures (``|x| …``)"
        "Quelle unverändert", "Ja", "Ja"
        "Verhalten bei Stream Mehrfachnutzung", ":java:`IllegalStateException` zur Laufzeit", "Compile-Fehler bei Mehrfachnutzung (Ownership)"
        "Streams = Collections?", "Nein — separates Konzept", "Nein — ``iter()`` erzeugt Iterator über Collection"
        "Auswertung", "Lazy (erst bei Terminal Op)", "Lazy (erst bei ``collect``, ``sum``, ``for_each``, …)"
        "Performance-Overhead", "JVM + GC + JIT (zur Laufzeit)", "Null — Zero-Cost Abstractions (zur Compile-Zeit)"
        "Erweiterbarkeit", "Collectors und Gatherers", "Nur ``next()`` implementieren — ~75 Methoden gratis"
        "Parallelisierung", "Tief integriert (``.parallelStream()``)", "Über ``rayon``-Crate (``.par_iter()``)"
        "Fehlerbehandlung", "``Optional`` (umgehbar via ``get()``)", "``Option`` / ``Result`` (vom Compiler erzwungen)"
        "Typsicherheit", "Compile-time (Generics mit Type Erasure)", "Compile-time (Generics, Monomorphisierung)"

.. supplemental::

    Rusts Iterator-Modell ist Javas Streams oberflächlich sehr ähnlich: beide sind lazy, beide unterscheiden zwischen Intermediate- und Terminal-Operationen, beide unterstützen ``filter``, ``map``, ``flat_map``, ``collect`` etc. Der fundamentale Unterschied liegt tiefer:

    :Zero-Cost Abstractions: Rusts Compiler (via LLVM) optimiert Iterator-Pipelines zu praktisch demselben Maschinencode wie handgeschriebene ``for``-Schleifen — keine Zwischenstrukturen, keine Heap-Allokationen, keine virtuellen Methodenaufrufe. In Java erzeugt die Stream-Pipeline dagegen Overhead durch Objekt-Allokationen, virtuelle Dispatches und GC-Zyklen, der erst durch den JIT-Compiler *teilweise* kompensiert wird.

    :Ownership: Javas Schutz gegen Mehrfachnutzung eines Streams greift erst zur Laufzeit (``IllegalStateException``). Rusts Ownership-System verhindert denselben Fehler bereits zur Compile-Zeit — der Code kompiliert schlicht nicht.

    :Parallelisierung: Java bietet mit ``.parallelStream()`` / ``.parallel()`` eine in die Standardbibliothek integrierte Lösung. Rust lagert dies in das ``rayon``-Crate aus — dort genügt es, ``iter()`` durch ``par_iter()`` zu ersetzen. Beide Ansätze sind ergonomisch ähnlich, aber Rusts Ownership-System garantiert zur Compile-Zeit, dass keine Data Races entstehen können. Javas Fork/Join-Pool bietet diese Garantie nicht.



.. class:: new-section transition-flip

Fragen?
------------------

.. class:: section-subtitle

    Java Stream API und Stream(-like) APIs anderer Sprachen!
