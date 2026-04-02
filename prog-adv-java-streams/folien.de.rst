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



.. class:: global-information

[λ] Glossar - Funktionale Programmierung in Java
--------------------------------------------------

.. scrollable::

  .. list-table::
    :header-rows: 1
    :widths: 25 35 40
    :width: 100%

    * - Konzept
      - Beschreibung
      - Beispiel
    * - Lambda-Ausdruck
      - Eine anonyme Funktion, die als Wert übergeben werden kann. Syntax:

        - :java:`(Parameter) -> Ausdruck`

        - :java:`(Parameter) -> { Anweisungen; }`
      - :java:`(x, y) -> x + y`

        :java:`x -> x * x`

        :java:`() -> 42`

         :java:`(a, b) -> { int sum = a + b; return sum * 2; }`
    * - Methoden-Referenz
      - Kurzschreibweise für Lambdas, die lediglich eine bestehende Methode aufrufen. Varianten:

        - statisch: :java:`Klasse::methode`
        - auf Instanz: :java:`objekt::methode`
        - auf Typ: :java:`Klasse::instanzMethode`
        - Konstruktor: :java:`Klasse::new`
      - :java:`Integer::parseInt`

        :java:`System.out::println`

        :java:`String::length`

        :java:`ArrayList::new`
    * - Funktionales Interface
      - Ein Interface mit genau **einer** abstrakten Methode (SAM — *Single Abstract Method*). Kann mit :java:`@FunctionalInterface` annotiert werden und ermöglicht die Verwendung von Lambda-Ausdrücken.
      - .. code:: java

            @FunctionalInterface
            interface MyFunc {
                int apply(int a, int b);
            }
    * - :java:`Predicate<T>`
      - Funktionales Interface: Prüft eine Bedingung auf einem Wert und gibt :java:`boolean` zurück.
      - :java:`Predicate<Integer> isEven = x -> x % 2 == 0;`
    * - :java:`Function<T,R>`
      - Funktionales Interface: Bildet einen Wert vom Typ :java:`T` auf einen Wert vom Typ :java:`R` ab.
      - :java:`Function<String,Integer> len = String::length;`
    * - :java:`Consumer<T>`
      - Funktionales Interface: Konsumiert einen Wert, gibt nichts zurück (:java:`void`).
      - :java:`Consumer<String> printer = System.out::println;`
    * - :java:`Supplier<T>`
      - Funktionales Interface: Liefert einen Wert ohne Eingabe.
      - :java:`Supplier<Double> random = Math::random;`
    * - :java:`BinaryOperator<T>`
      - Funktionales Interface: Verknüpft zwei Werte desselben Typs zu einem Ergebnis.
      - :java:`BinaryOperator<String> wrap = (a, b) -> b + a + b;`
    * - :java:`Comparator<T>`
      - Funktionales Interface für den Vergleich von zwei Objekten; gibt einen :java:`int`-Wert zurück.
      - :java:`Comparator<String> byLen = (a, b) -> a.length() - b.length();`

        :java:`Comparator.comparing(String::length)`
    * - Funktionen höherer Ordnung
      - Funktionen, die andere Funktionen als Parameter nehmen oder als Ergebnis zurückgeben. Grundlage aller Stream-Operationen.
      - :java:`stream.filter(x -> x > 0)` :java:`.map(x -> x * 2)`
    * - Closure
      - Ein Lambda-Ausdruck, der auf Variablen aus dem umgebenden Kontext zugreift. Diese Variablen müssen :java:`final` oder *effektiv final* sein.
      - .. code:: java

            final int factor = 10;
            Stream.of(3,4,3).
                map(x -> x * factor).
                toList();
    * - :java:`Optional<T>`
      - Ein Container, der einen Wert enthalten kann oder leer ist. Ersetzt die Verwendung von :java:`null`.
      - :java:`Optional.of(42)`

        :java:`Optional.empty()`







(Aktuelle) Herausforderungen
------------------------------------------------

.. deck::

    .. card::

        **Bei der Datenverarbeitung von**:

        .. class:: list-with-sublists incremental-list

        - großen Datenmengen,

          .. class:: incremental-list

          - die nicht (ohne weiteres) in den Speicher passen oder
          - bei denen es keinen Sinn macht diese vorab vollständig in den Speicher zu laden, da immer nur ein kleiner Abschnitt analysiert werden muss
        - Daten, die kontinuierlich verarbeitet werden müssen

    .. card::

        .. example:: Konkrete Szenarien der Datenverarbeitung

            - HTTP Request/Response Handling (Audio/Video Streaming)
            - Ver-/Entschlüsselung von großen Datenmengen
            - kontinuierliche/echtzeit Verarbeitung von allg. Daten (z. B. Finanzdaten/-transaktionen) oder (IoT) Sensoren
            - Verarbeitung von Ereignissen (Event)
            - Aufbereitung großer Wörterbücher (z.B. für Passwortwiederherstellung)
            - Verarbeitung von AI Streaming Responses
            - Verarbeitung große Logdateien

    .. card::

        - Parallele Verarbeitung und effiziente Nutzung von modernen CPU-Architekturen

        .. deck:: center-content

            .. card::

                .. raw:: html

                        <div style="width:71.5ch; height: 38.4ch">
                        <svg viewBox="2 0 110 58"
                            font-size="1.5"
                            version="1.1" xmlns="http://www.w3.org/2000/svg">
                        <rect x="2" y="0" rx="1" width="108" height="58" fill="white" />
                        <text x="60.0" y="3.5" text-anchor="middle"
                                font-size="2" font-weight="bold" fill="#333">AMD EPYC Server CPUs — Entwicklung 2017–2026</text>
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
                        <text x="21.53" y="54.2" text-anchor="middle" font-size="1.1" fill="gray">06.2017</text>
                        <text x="39.88" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="black">Rome</text>
                        <text x="39.88" y="52.6" text-anchor="middle" font-size="1.2" fill="black">7742</text>
                        <text x="39.88" y="54.2" text-anchor="middle" font-size="1.1" fill="gray">08.2019</text>
                        <text x="53.29" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="black">Milan</text>
                        <text x="53.29" y="52.6" text-anchor="middle" font-size="1.2" fill="black">7763</text>
                        <text x="53.29" y="54.2" text-anchor="middle" font-size="1.1" fill="gray">03.2021</text>
                        <text x="67.41" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="black">Genoa</text>
                        <text x="67.41" y="52.6" text-anchor="middle" font-size="1.2" fill="black">9654</text>
                        <text x="67.41" y="54.2" text-anchor="middle" font-size="1.1" fill="gray">11.2022</text>
                        <g transform="translate(2,0)">
                        <text x="72.35" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="#666666">Bergamo</text>
                        <text x="72.35" y="52.6" text-anchor="middle" font-size="1.2" fill="black">9754</text>
                        <text x="72.35" y="54.2" text-anchor="middle" font-size="1.1" fill="gray">06.2023</text>
                        </g>
                        <g transform="translate(2,0)">
                            <text x="79.15" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="black">Turin</text>
                            <text x="79.15" y="52.6" text-anchor="middle" font-size="1.2" fill="black">9755</text>
                            <text x="79.15" y="54.2" text-anchor="middle" font-size="1.1" fill="gray">10.2024</text>
                        </g>
                        <g>
                        <text x="88.15" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="#666666">Turin Dense</text>
                        <text x="88.15" y="52.6" text-anchor="middle" font-size="1.2" fill="#666666">9965</text>
                        <text x="88.15" y="54.2" text-anchor="middle" font-size="1.1" fill="gray">10.2024</text>
                        </g>
                        <text x="100.59" y="50.8" text-anchor="middle" font-size="1.5" font-weight="bold" fill="black">Venice</text>
                        <text x="100.59" y="54.2" text-anchor="middle" font-size="1.1" fill="gray">10.2026</text>

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
                        <text x="27" y="65" font-size="1.2" fill="#0066CC">SPECint 2017/Cores</text>
                        </g>
                        </svg>
                        </div>


            .. card:: overlay

                .. raw:: html

                        <div style="width:71.5ch; height: 38.4ch">
                        <svg viewBox="2 0 110 58"
                            font-size="1.5"
                            version="1.1" xmlns="http://www.w3.org/2000/svg">

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
                        <g transform="translate(25,-10)">
                        <line x1="20" y1="66.5" x2="26" y2="66.5"
                                stroke="#CC3300" stroke-width="0.35" stroke-dasharray="0.3,0.6"/>
                        <circle cx="23" cy="66.5" r="0.45" fill="#CC3300"/>
                        <text x="27" y="67" font-size="1.2" fill="#CC3300">SPECint 2017 Rate Baseline</text>
                        </g>
                        </svg>
                        </div>

            .. card:: overlay

                .. raw:: html

                        <div style="width:71.5ch; height: 38.4ch">
                        <svg viewBox="2 0 110 58"
                            font-size="1.5"
                            version="1.1" xmlns="http://www.w3.org/2000/svg">

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
                        <polyline points="21.53,42.62 39.88,37.25 53.29,37.25 67.41,31.88 72.35,26.50 83.65,26.50 83.65,15.75 100.59,5.00"
                                    fill="none" stroke="#9900CC" stroke-width="0.35"
                                    stroke-dasharray="2,0.4,0.3,0.4" stroke-linecap="round"/>
                        <circle cx="21.53" cy="42.62" r="0.55" fill="#9900CC"/>
                        <text x="21.53" y="41.52" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#9900CC">32</text>
                        <circle cx="39.88" cy="37.25" r="0.55" fill="#9900CC"/>
                        <text x="39.88" y="36.15" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#9900CC">64</text>
                        <circle cx="53.29" cy="37.25" r="0.55" fill="#9900CC"/>
                        <text x="53.29" y="36.15" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#9900CC">64</text>
                        <circle cx="67.41" cy="31.88" r="0.55" fill="#9900CC"/>
                        <text x="67.41" y="30.77" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#9900CC">96</text>
                        <circle cx="72.35" cy="26.50" r="0.55" fill="#9900CC"/>
                        <text x="72.35" y="28.40" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#9900CC">128</text>
                        <circle cx="83.65" cy="26.50" r="0.55" fill="#9900CC"/>
                        <text x="83.65" y="28.40" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#9900CC">128</text>
                        <circle cx="83.65" cy="15.75" r="0.55" fill="#9900CC"/>
                        <text x="85.5" y="16.2" text-anchor="middle" font-size="1.1" font-weight="bold" fill="#9900CC">192</text>
                        <circle cx="100.59" cy="5.00" r="0.55" fill="#9900CC"/>

                        <!-- Legende -->
                        <g transform="translate(20,-8)">
                        <line x1="55" y1="64.5" x2="61" y2="64.5"
                                stroke="#9900CC" stroke-width="0.35" stroke-dasharray="2,0.4,0.3,0.4"/>
                        <circle cx="58" cy="64.5" r="0.45" fill="#9900CC"/>
                        <text x="62" y="65" font-size="1.2" fill="#9900CC">#Cores</text>
                        </svg>
                        </g>
                        </div>




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
