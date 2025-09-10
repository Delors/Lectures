.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "LLMs", "Prompting"
    :description lang=de: Software Engineering - Code Generierung mit LLMs
    :id: lecture-se-using-llms
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!
    :svg-defs:
        <marker id="arrow" viewBox="0 0 10 10" refX="8" refY="5" markerWidth="6" markerHeight="6" orient="auto-start-reverse">
        <path d="M 0 0 L 10 5 L 0 10 z" fill="#111"/>
        </marker>
    :svg-style:
        .label { font-family: "Noto Sans Display"; font-weight: 400; fill: #111; }
        .arrow { fill: none; stroke: darkgray; stroke-width: 6;  }

.. include:: ../docutils.defs



Software Engineering - Code Generierung mit LLMs\ [#]_
===================================================================

Eine allererste Einführung

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 0.1

.. supplemental::

  :Folien:
      [HTML] |html-source|

      [PDF] |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues

.. [#] Erstellt unter Zuhilfenahme von ChatGPT



LLMs und Code-Generierung
---------------------------------------------------

.. grid::

    .. cell:: width-30

        **Normales LLM**

        .. class:: incremental-list

        - Antwortet direkt: Eingabe → Ausgabe
        - Schnell, ausreichend für einfache Aufgaben
        - Risiko: unvollständiger oder fehlerhafter Code bei komplexeren Problemen
        - Beispiel: „Schreibe eine Python-Funktion für Fibonacci-Zahlen“ → sofortiger Code

    .. cell:: width-70

        **Thinking-LLM**

        .. raw:: html

                <div style="width: 15ch; height: 15ch;">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="75 75 450 450" font-size="30">

                    <!-- Circle center (300,300), radius 200 -->
                    <!-- Labels -->
                    <text class="label" x="300" y="140" text-anchor="middle">PERCEIVE</text>
                    <text class="label incremental#1" x="420" y="310" text-anchor="middle">REASON</text>
                    <text class="label incremental#2" x="300" y="480" text-anchor="middle">ACT</text>
                    <text class="label incremental#3" x="180" y="310" text-anchor="middle">OBSERVE</text>


                    <!-- Arcs with arrows (clockwise) -->
                    <g transform="rotate(2.5,300,300)">
                        <!-- PERCEIVE -> REASON -->
                        <path class="arrow incremental#1" d="M 300 100 A 200 200 0 0 1 500 300" marker-end="url(#arrow)" />
                        <!-- REASON -> ACT -->
                        <path class="arrow incremental#2" d="M 500 300 A 200 200 0 0 1 300 500" marker-end="url(#arrow)" />
                        <!-- ACT -> OBSERVE -->
                        <path class="arrow incremental#3" d="M 300 500 A 200 200 0 0 1 100 300" marker-end="url(#arrow)" />
                        <!-- OBSERVE -> PERCEIVE -->
                        <path class="arrow incremental#4" d="M 100 300 A 200 200 0 0 1 300 100" marker-end="url(#arrow)"/>
                    </g>
                </svg>
                </div>

        .. class:: incremental-list

        - Führt interne „Denk“-Schritte vor der Antwort aus
        - Reflektiert, prüft und verbessert den Code, bevor er ausgegeben wird
        - Langsamer, aber zuverlässiger bei komplexen Aufgaben
        - Beispiel: Überlegt Algorithmus-Varianten, Edge-Cases und Tests, bevor Code zurückkommt

.. supplemental::

    .. summary::
        :class: incremental

        Normale LLMs sind schnell für Routinetätigkeiten – Thinking-LLMs liefern bessere Ergebnisse bei schwierigen Programmieraufgaben.



.. class:: transition-scale

Prompting-Strategien für Code-Generierung mit LLMs
--------------------------------------------------

.. story::

    .. class:: incremental-list

    :Klare Aufgabenstellung:
        Programmiersprache und gewünschte Bibliotheken benennen.

        .. example::

            Schreibe eine Funktion in *Python*, die … mit *NumPy* …

    :Struktur vorgeben:

        Erwünschte Signatur oder Interfaces spezifizieren und dabei möglichst präzise sein.

        .. example::

            Implementiere :python:`def sort_list(xs: list[int]) -> list[int]:`

    :Schrittweise Anleitungen:

        Erst Algorithmus erklären lassen, dann den Code generieren.

        Hilft bei komplexen Problemen (Divide & Conquer im Prompt).

    :Beispiele nutzen:

        „Few-Shot Prompting“ mit kleinen Code-Snippets als Vorlage.

        Fördert Einhaltung von Stil, Syntax und Konventionen.

    :Fehlervermeidung:

        Auf Testfälle im Prompt hinweisen: „Der Code muss diese Tests bestehen …“

    :Iterative Verfeinerung:

        Erst groben Entwurf generieren, dann gezielt Verbesserungen anfordern

        .. example::

            „Optimiere die Laufzeit“ oder „Füge Typannotationen hinzu“.

    .. auf Widersprüche hinweisen...



Typische Stolperfallen beim Code-Prompting
-------------------------------------------


.. story::

    .. class:: incremental-list

    :Unpräzise Sprache:
        - Keine Programmiersprache/Technologien angegeben → Modell wählt zufällig.
        - Lösung: Sprache und Version im Prompt festlegen

    :Fehlende Constraints:
        - Ohne Funktionssignatur oder Interfaces generiert LLM „freie“ Lösungen
        - Lösung: Signaturen, Klassen oder API-Vorgaben angeben

    :Vermischte Anforderungen:

        - Prompt enthält Code + Prosa → Antwort enthält unnötige Erklärungen
        - Lösung: explizit nur *Codeblock* anfordern

    :Zu komplex in einem Schritt:
        - Lange Prompts mit vielen Bedingungen führen zu Fehlern
        - Lösung: Problem in mehrere Teilaufgaben aufsplitten, dabei jedoch alle für einen Schritt relevanten Anforderungen benennen, um sich (viel) Nacharbeit zu sparen.

        .. supplemental::

            Modernere LLMs können immer besser mit komplexen Prommpts umgehen und hier sind auch weitere Verbesserungen zu erwarten.

    :Keine Tests spezifiziert:
        - Modell weiß nicht, wann Lösung „korrekt“ ist
        - Lösung: Testfälle im Prompt beilegen oder explizit anfordern

    :Übermäßiges Vertrauen:

        .. class:: negative-list

        - Generierter Code wird ohne Überprüfung übernommen!
        - Lösung: Immer Review, Linting und Tests durchführen. Insbesondere Versionen von externen Bibliotheken überprüfen, diese sind häufig nicht aktuell oder veraltet.

        .. attention::

           Beim Einbiden von Tools oder Bibliotheken ist die Zielbibliothek immer zu prüfen! Am Besten einen Dependency-Manager verwenden.






Mensch vs. Maschine
-----------------------

.. epigraph::

    [...] Hoher Komplexitätsgrad als Stolperstein

    Viele KI-Tools sind mittlerweile so leistungsstark geworden, dass sie Developern einen echten Mehrwert bringen. Komplexe Coding-Aufgaben können sich für Kollege KI jedoch laut der Studie als Stolperstein herausstellen. Darunter fallen etwa Aspekte wie das Kontextverständnis für sehr umfangreiche Codebasen, höhere logische Komplexitätsgrade sowie die Fähigkeit, Codestrukturen so zu planen und umzusetzen, dass ihre Qualität auch langfristig auf dem gleichen Niveau bleibt. [...]

    -- `2.9.2025 - Heise Online - Das machen Menschen besser: Studie zeigt Grenzen von Coding-Agenten <https://www.heise.de/news/Das-machen-Menschen-besser-Studie-zeigt-Grenzen-von-Coding-Agenten-10628483.html>`__

.. supplemental::

    Es ist davon auszugehen, dass sich diese Situation zeitnahe noch stärker verschieben wird, dadurch, dass die Modelle bei Unsicherheit nachfragen werden.


.. class:: new-section transition-fade

Exemplarische Verwendung von LLMs für die Codegenerierung
-----------------------------------------------------------



.. class:: new-subsection transition-move-to-top

Beispielhafte Verwendung von GPT-4.1
-------------------------------------



Beispielhafte Verwendung von GPT-4.1
-------------------------------------

Das Ziel ist die Generierung einer Kommandozeilenanwendung zur Berechnung der Quadratwurzel einer Zahl mit vorgegebener, aber beliebiger Genauigkeit.

.. attention::

        Der Chat bzw. die Ausgaben wurden teilweise reformatiert und gekürzt, um die Lesbarkeit zu verbessern. Der grundzätzliche Ablauf ist zu 100% erhalten, um die Herausforderungen zu verdeutlichen.



Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: 1. Schritt: Grundlegende Methode implementieren lassen.

    .. compound::
        :class: llm-chat-user

        Bitte schreibe mir eine Methode in Java, die die Newton-Raphson-Methode
        anwendet zur Berechnung der Quadratwurzel.

    .. compound::
        :class: llm-chat-agent incremental

        Hier ist eine Java-Methode, die mit Newtons Verfahren die Quadratwurzel einer Zahl berechnet:

        .. code:: java
            :number-lines:

             class NewtonSqrt {
                /**
                * Berechnet die Quadratwurzel einer Zahl mit Newtons Verfahren.
                * @param n Die Zahl, deren Quadratwurzel berechnet werden soll (n >= 0)
                * @param tolerance Die gewünschte Genauigkeit (z.B. 1e-10)
                * @return Die berechnete Quadratwurzel von n */
                public static double sqrt(double n, double tolerance) {
                    if (n < 0) { throw new IllegalArgumentException("n muss >= 0 sein"); }
                    if (n == 0) return 0;
                    double x = n;
                    while (Math.abs(x * x - n) > tolerance) {
                        x = 0.5 * (x + n / x);
                    }
                    return x;
                }
            }

        *Verwendung:*

        .. code:: java
            :number-lines:

             public class ExampleUsage {
                public static void main(String[] args) {
                    double wurzel = NewtonSqrt.sqrt(25, 1e-10);
                    System.out.println("Quadratwurzel von 25: " + wurzel);
                }
            }



Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable::

    .. rubric:: 2. Schritt: Ermöglichen einer konfigurierbaren Anzahl Stellen.

    .. compound::
        :class: llm-chat-user

        Die Methode soll in der Lage sein eine - ggf. sehr große - Anzahl an
        Nachkommastellen zu liefern.

    .. compound::
        :class: llm-chat-agent incremental

        Um eine sehr große Anzahl an Nachkommastellen zu berechnen, solltest du statt ``double`` die Klasse ``BigDecimal`` verwenden. Damit kannst du beliebige Genauigkeit erreichen, solange der Speicher reicht. Newtons Verfahren lässt sich auch mit ``BigDecimal`` umsetzen.

        Hier ist eine Methode, die die Quadratwurzel einer Zahl mit Newtons Verfahren und beliebiger Genauigkeit berechnet:

        .. code:: java
            :number-lines:

             import java.math.BigDecimal;
            import java.math.MathContext;
            import java.math.RoundingMode;

            public class BigDecimalNewtonSqrt {
                /**
                 * Berechnet die Quadratwurzel einer Zahl mit Newtons Verfahren und hoher Genauigkeit.
                 * @param n Die Zahl, deren Quadratwurzel berechnet werden soll (n >= 0)
                 * @param scale Anzahl der gewünschten Nachkommastellen
                 * @return Die berechnete Quadratwurzel von n als BigDecimal
                 */
                public static BigDecimal sqrt(BigDecimal n, int scale) {
                    if (n.compareTo(BigDecimal.ZERO) < 0) {
                        throw new IllegalArgumentException("n muss >= 0 sein");
                    }
                    if (n.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;

                    BigDecimal two = BigDecimal.valueOf(2);
                    MathContext mc = new MathContext(scale + 5, RoundingMode.HALF_UP); // Extra Stellen für Genauigkeit
                    BigDecimal x = n;
                    BigDecimal lastX;
                    int maxIterations = 1000;
                    int count = 0;

                    do {
                        lastX = x;
                        x = n.divide(x, mc).add(x).divide(two, mc);
                        count++;
                    } while (x.subtract(lastX).abs().compareTo(BigDecimal.ONE.scaleByPowerOfTen(-scale)) > 0 && count < maxIterations);

                    return x.setScale(scale, RoundingMode.HALF_UP);
                }
            }

        *Beispiel für die Verwendung:*

        .. code:: java
            :number-lines:

             import java.math.BigDecimal;

            public class BigDecimalNewtonSqrtTest {
                public static void main(String[] args) {
                    BigDecimal n = new BigDecimal("2");
                    int scale = 50; // 50 Nachkommastellen
                    BigDecimal wurzel = BigDecimalNewtonSqrt.sqrt(n, scale);
                    System.out.println("Quadratwurzel von 2 mit 50 Nachkommastellen:");
                    System.out.println(wurzel.toPlainString());
                }
            }

        Mit dieser Methode kannst du die Quadratwurzel mit beliebig vielen Nachkommastellen berechnen. Passe einfach den Wert von ``scale`` an deine Anforderungen an.



Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable::

    .. rubric:: 3. Schritt: Umwandlung in ein Kommandozeilenprogramm

    .. compound::
        :class: llm-chat-user

        Bitte stecke alle Methoden in eine Klasse und erweitere die Main Methode
        so, dass ich die Parameter auf der Kommandozeile eingeben kann. Erzeuge
        auch eine entsprechende Datei.

    .. compound::
        :class: llm-chat-agent incremental

        Hier ist die vollständige Java-Klasse ``BigDecimalNewtonSqrt`` mit  Newtons Verfahren für die Quadratwurzel und einem Kommandozeilen-Interface. Die Datei wurde unter ``...src/BigDecimalNewtonSqrt.java`` erstellt.

        .. code:: java
            :number-lines:

             import java.math.BigDecimal;
            import java.math.MathContext;
            import java.math.RoundingMode;

            public class BigDecimalNewtonSqrt {

                /**
                 * Berechnet die Quadratwurzel einer Zahl mit Newtons Verfahren und hoher Genauigkeit.
                 * @param n Die Zahl, deren Quadratwurzel berechnet werden soll (n >= 0)
                 * @param scale Anzahl der gewünschten Nachkommastellen
                 * @return Die berechnete Quadratwurzel von n als BigDecimal
                 */
                public static BigDecimal sqrt(BigDecimal n, int scale) {
                    if (n.compareTo(BigDecimal.ZERO) < 0) {
                        throw new IllegalArgumentException("n muss >= 0 sein");
                    }
                    if (n.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;

                    BigDecimal two = BigDecimal.valueOf(2);
                    MathContext mc = new MathContext(scale + 5, RoundingMode.HALF_UP); // Extra Stellen für Genauigkeit
                    BigDecimal x = n;
                    BigDecimal lastX;
                    int maxIterations = 1000;
                    int count = 0;

                    do {
                        lastX = x;
                        x = n.divide(x, mc).add(x).divide(two, mc);
                        count++;
                    } while (x.subtract(lastX).abs().compareTo(BigDecimal.ONE.scaleByPowerOfTen(-scale)) > 0 && count < maxIterations);

                    return x.setScale(scale, RoundingMode.HALF_UP);
                }

                /**
                 * Kommandozeilen-Interface:
                 * Parameter:
                 *   args[0] - Zahl (z.B. "2")
                 *   args[1] - Anzahl der Nachkommastellen (z.B. "50")
                 */
                public static void main(String[] args) {
                    if (args.length < 2) {
                        System.out.println("Verwendung: java BigDecimalNewtonSqrt <Zahl> <Nachkommastellen>");
                        System.out.println("Beispiel: java BigDecimalNewtonSqrt 2 50");
                        return;
                    }
                    try {
                        BigDecimal n = new BigDecimal(args[0]);
                        int scale = Integer.parseInt(args[1]);
                        BigDecimal wurzel = sqrt(n, scale);
                        System.out.println("Quadratwurzel von " + n.toPlainString() + " mit " + scale + " Nachkommastellen:");
                        System.out.println(wurzel.toPlainString());
                    } catch (Exception e) {
                        System.out.println("Fehler: " + e.getMessage());
                    }
                }
            }

        Du kannst die Klasse wie folgt kompilieren und ausführen:

        .. code:: sh

            javac src/BigDecimalNewtonSqrt.java
            java -cp src BigDecimalNewtonSqrt 2 50

        Das berechnet die Quadratwurzel von 2 mit 50 Nachkommastellen. Passe die
        Parameter nach Bedarf an.



Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable::

    .. rubric:: 4. Schritt: Kritisches Hinterfragen des Codes

    .. compound::
        :class: llm-chat-user

        In der Methode sqrt ist die Anzahl an Iterationen auf Max 1000 gesetzt.
        Sollte dies nicht abhängig sein von der Anzahl der geforderten
        Nachkommastellen. Wenn ich zum Beispiel 10000 Nachkommastellen
        anfordere, dann erwarte ich auch, dass diese Präzise sind und dies
        dürfte bei 1000 Iterationen nicht der Fall sein, oder?

    .. compound::
        :class: llm-chat-agent incremental

        Das ist ein sehr guter und berechtigter Hinweis! Die maximale Anzahl der Iterationen sollte tatsächlich von der  gewünschten Genauigkeit (``scale``) abhängen. Je mehr Nachkommastellen   du verlangst, desto mehr Iterationen sind in der Regel nötig, damit  Newtons Verfahren konvergiert und die gewünschte Präzision erreicht  wird.

        **Warum?**

        Newtons Verfahren verdoppelt grob die Anzahl der korrekten Stellen pro
        Iteration, aber bei sehr hoher Präzision kann es passieren, dass die
        Differenz zwischen zwei Iterationen so klein wird, dass sie erst nach
        sehr vielen Durchläufen unter die gewünschte Schwelle fällt.

        **Empfehlung:**

        Setze ``maxIterations`` proportional zur gewünschten
        Nachkommastellenzahl, z.B.
        ``maxIterations = Math.max(100, scale * 2)`` oder sogar ``scale * 5``
        für sehr hohe Sicherheit.

        Hier ist die angepasste Methode:

        .. code:: java
            :number-lines:

             public static BigDecimal sqrt(BigDecimal n, int scale) {
                if (n.compareTo(BigDecimal.ZERO) < 0) {
                    throw new IllegalArgumentException("n muss >= 0 sein");
                }
                if (n.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;

                BigDecimal two = BigDecimal.valueOf(2);
                MathContext mc = new MathContext(scale + 5, RoundingMode.HALF_UP); // Extra Stellen für Genauigkeit
                BigDecimal x = n;
                BigDecimal lastX;
                int maxIterations = Math.max(100, scale * 5); // Dynamisch abhängig von scale
                int count = 0;

                do {
                    lastX = x;
                    x = n.divide(x, mc).add(x).divide(two, mc);
                    count++;
                } while (x.subtract(lastX).abs().compareTo(BigDecimal.ONE.scaleByPowerOfTen(-scale)) > 0 && count < maxIterations);

                if (count >= maxIterations) {
                    throw new ArithmeticException("Maximale Iterationszahl (" + maxIterations + ") erreicht, Ergebnis möglicherweise ungenau.");
                }

                return x.setScale(scale, RoundingMode.HALF_UP);
            }

        **Was ist neu?** - ``maxIterations`` ist jetzt abhängig von ``scale``. -
        Es wird eine Exception geworfen, falls die Iterationszahl überschritten
        wird (damit du weißt, dass das Ergebnis evtl. nicht präzise genug ist).

        **Fazit:**
         Mit dieser Anpassung kannst du auch sehr viele Nachkommastellen zuverlässig berechnen. Bei extrem hohen Anforderungen kann es trotzdem  sinnvoll sein, die Konvergenz zu überwachen und ggf. die Startwerte  oder die MathContext-Präzision weiter zu erhöhen.




Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable::

    .. rubric:: 5. Schritt: Dokumentation verbessern lassen

    .. compound::
        :class: llm-chat-user

        Bitte aktualisiere die Dokumentation der Methode in Hinblick darauf, dass eine ArithmeticException geworfen werden kann.

    .. compound::
        :class: llm-chat-agent incremental

        Die Dokumentation der Methode ``sqrt`` wurde aktualisiert und enthält jetzt den Hinweis, dass eine ``ArithmeticException`` geworfen werden kann, falls die maximale Iterationszahl überschritten wird und das Ergebnis möglicherweise ungenau ist.

        Hier ist der relevante Ausschnitt:

        .. code:: java
            :number-lines:

             /**
             * Berechnet die Quadratwurzel einer Zahl mit Newtons Verfahren und hoher Genauigkeit.
             * @param n Die Zahl, deren Quadratwurzel berechnet werden soll (n >= 0)
             * @param scale Anzahl der gewünschten Nachkommastellen
             * @return Die berechnete Quadratwurzel von n als BigDecimal
             * @throws ArithmeticException falls die maximale Iterationszahl überschritten wird und das Ergebnis möglicherweise ungenau ist
             */
            public static BigDecimal sqrt(BigDecimal n, int scale) {
                ...
            }

        Damit ist die Methode und ihre Dokumentation jetzt korrekt und robust für sehr hohe Präzision.




Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable::

    .. rubric:: 6. Schritt: Dokumentation weiter verbessern lassen

    .. compound::
        :class: llm-chat-user

        Bitte dokumentiere bis zu wieviel Stellen die Methode aufgrund der Beschränkung des Counters auf den Datentyp Int maximal präzise sein kann.

    .. compound::
        :class: llm-chat-agent incremental

        Sehr gerne!

        Da der Iterationszähler ``count`` vom Typ ``int`` ist, kann die Methode maximal ``Integer.MAX_VALUE`` Iterationen durchführen. Da die maximale Iterationszahl als ``maxIterations = Math.max(100, scale * 5)`` gesetzt wird, ist die maximale Anzahl an Nachkommastellen, die du zuverlässig berechnen kannst, durch die Größe des ``int``-Datentyps begrenzt.

        *Berechnung:*

        - ``Integer.MAX_VALUE`` = 2 147 483 647
        - Die Formel für die Iterationen: ``scale * 5 ≤ Integer.MAX_VALUE``
        - Daraus folgt: ``scale ≤ Integer.MAX_VALUE / 5 ≈ 429 496 729``

        *Dokumentationsvorschlag für die Methode:*

        .. code:: java
            :number-lines:

             /**
             * Berechnet die Quadratwurzel einer Zahl mit Newtons Verfahren und hoher Genauigkeit.
             * <p>
             * Hinweis: Die maximale Anzahl an Nachkommastellen ist durch den Datentyp {@code int} für den Iterationszähler begrenzt.
             * Bei der aktuellen Implementierung mit {@code maxIterations = Math.max(100, scale * 5)} können maximal ca. 429.496.729 Nachkommastellen
             * zuverlässig berechnet werden. Wird ein höherer Wert für {@code scale} angegeben, kann eine {@link ArithmeticException} auftreten.
             * </p>
             * @param n Die Zahl, deren Quadratwurzel berechnet werden soll (n >= 0)
             * @param scale Anzahl der gewünschten Nachkommastellen (maximal ca. 429.496.729)
             * @return Die berechnete Quadratwurzel von n als BigDecimal
             * @throws ArithmeticException falls die maximale Iterationszahl überschritten wird und das Ergebnis möglicherweise ungenau ist
             */

        *Fazit:*

        Die Methode ist für bis zu ca. 429 Millionen Nachkommastellen präzise, sofern genügend Speicher und Rechenzeit vorhanden sind. Für noch höhere Präzision müsste der Iterationszähler auf ``long`` oder einen größeren Datentyp umgestellt werden.



Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable::

    .. rubric:: 7. Schritt: Kritisches Hinterfragen des Codes

    .. compound::
        :class: llm-chat-user

        Wenn ich in die Methode Int.MAX_VALUE reingebe, dann ist die Anzahl der Iterationen nicht wie erwartet. Passe den Code so an, dass bei einem zu großen Wert von scale eine Exception geworfen wird und passe auch die Dokumentation entsprechend an.

    .. compound::
        :class: llm-chat-agent incremental

        Die Methode wurde angepasst, sodass bei einem zu großen Wert für ``scale`` eine ``IllegalArgumentException`` geworfen wird. Die Dokumentation wurde ebenfalls entsprechend erweitert.

        Hier ist der aktualisierte Ausschnitt:

        .. code:: java
            :number-lines:

             /**
             * Berechnet die Quadratwurzel einer Zahl mit Newtons Verfahren und hoher Genauigkeit.
             * <p>
             * Hinweis: Die maximale Anzahl an Nachkommastellen ist durch den Datentyp {@code int} für den Iterationszähler begrenzt.
             * Bei der aktuellen Implementierung mit {@code maxIterations = Math.max(100, scale * 5)} können maximal ca. 429496729 Nachkommastellen
             * zuverlässig berechnet werden. Wird ein höherer Wert für {@code scale} angegeben, wird eine {@link IllegalArgumentException} geworfen.
             * </p>
             * @param n Die Zahl, deren Quadratwurzel berechnet werden soll (n >= 0)
             * @param scale Anzahl der gewünschten Nachkommastellen (maximal ca. 429496729)
             * @return Die berechnete Quadratwurzel von n als BigDecimal
             * @throws IllegalArgumentException falls {@code scale} zu groß ist oder {@code n} negativ ist
             * @throws ArithmeticException falls die maximale Iterationszahl überschritten wird und das Ergebnis möglicherweise ungenau ist
             */
            public static BigDecimal sqrt(BigDecimal n, int scale) {
                if (n.compareTo(BigDecimal.ZERO) < 0) {
                    throw new IllegalArgumentException("n muss >= 0 sein");
                }
                if (n.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;

                // Begrenzung für scale, damit maxIterations nicht über Integer.MAX_VALUE hinausgeht
                int maxScale = Integer.MAX_VALUE / 5;
                if (scale > maxScale) {
                    throw new IllegalArgumentException(
                        "scale ist zu groß. Maximal erlaubt: " + maxScale
                    );
                }

                BigDecimal two = BigDecimal.valueOf(2);
                MathContext mc = new MathContext(scale + 5, RoundingMode.HALF_UP); // Extra Stellen für Genauigkeit
                BigDecimal x = n;
                BigDecimal lastX;
                int maxIterations = Math.max(100, scale * 5); // Dynamisch abhängig von scale
                int count = 0;

                do {
                    lastX = x;
                    x = n.divide(x, mc).add(x).divide(two, mc);
                    count++;
                } while (
                    x.subtract(lastX).abs().compareTo(BigDecimal.ONE.scaleByPowerOfTen(-scale)) >
                        0 &&
                    count < maxIterations
                );

                if (count >= maxIterations) {
                    throw new ArithmeticException(
                        "Maximale Iterationszahl (" +
                        maxIterations +
                        ") erreicht, Ergebnis möglicherweise ungenau."
                    );
                }

                return x.setScale(scale, RoundingMode.HALF_UP);
            }

        Damit ist die Methode jetzt robust gegen zu große Werte für ``scale`` und die Dokumentation informiert klar über die Begrenzung.


Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: 8. Schritt: Etwas Fordern, dass nicht bzw. nur begrenzt sinnvoll ist

    .. compound::
        :class: llm-chat-user

        Erzeuge mir ein einfaches npm Build-Script, dass Prettier verwendet, um den Code zu formatieren. Der Code soll eine maximale Breite von 70 Zeichen haben.

    .. compound::
        :class: llm-chat-agent incremental

        Hier ist ein einfaches npm Build-Script, das Prettier verwendet, um den Code zu formatieren. Die maximale Zeilenbreite ist auf 70 Zeichen gesetzt.


        *1. package.json*

        Das Format-Script und die Prettier-Dependency sind enthalten:

        .. code:: json

            {
                "name": "sqrt-jshell-vibecoded",
                "version": "1.0.0",
                "description": "Build-Script für Prettier-Codeformatierung mit max. 70 Zeichen Breite",
                "scripts": {
                    "format": "prettier --write ."
                },
                "devDependencies": {
                    "prettier": "^3.0.0"
                },
                "prettier": {
                    "printWidth": 70
                }
            }

        *2. Prettier-Konfiguration (.prettierrc)*

        Die maximale Breite ist explizit gesetzt:

        .. code:: json

            {
                "printWidth": 70
            }

        *Verwendung:*

        1. Installiere die Dependencies: ``sh    npm install``
        2. Formatiere den Code: ``sh    npm run format``

        Damit wird dein Code mit Prettier und einer Zeilenbreite von 70 Zeichen formatiert.


    .. warning::

        Im Kontext von Java ist die Verwendung von Maven oder Gradle zum Bauen von Projekten der Standard. Es gab hier keinen Hinweis darauf, dass dies zumindest ungewöhnlich ist.

        Weitere Prompts, um das Build-Skript zu vervollständigen in Hinblick auf Testausführung und Berechnung der Codeabdeckung haben erst viele Nachbesserungen erfordert und waren am Ende auch nicht vollständig erfolgreich.

        Davon unabhängig ist auch die obige Konfiguration schon fragwürdig, da die Einstellung der maximalen Zeilenlänge an zwei Stellen erfolgt ist und diese Konfiguration so nicht funktionsfähig ist, da die Java Dateien, die zu formatieren sind, nicht angegeben wurden.

        Weiterhin ist Prettier ungeeignet, da es keine Dokumentation umbrechen kann.



Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: 9. Schritt: Tests generieren lassen

    .. compound::
        :class: llm-chat-user

        Bitte generiere mir Tests für sqrt Methode.

    .. compound::
        :class: llm-chat-agent incremental

        Hier findest du eine vollständige JUnit-Testklasse für die``sqrt``-Methode. Die Datei liegt unter ``./test/BigDecimalNewtonSqrtTest.java``.

        Die Tests prüfen:

        - Standardfälle (0, 1, 4, 9, 2 mit hoher Präzision)
        - Negative Eingabe
        - Zu große Präzision (``scale``)
        - Sehr große und sehr kleine Zahlen

        .. code:: java
            :number-lines:

             import org.junit.jupiter.api.Test;
            import org.junit.jupiter.api.DisplayName;
            import org.junit.jupiter.api.function.Executable;

            import java.math.BigDecimal;

            import static org.junit.jupiter.api.Assertions.*;

            class BigDecimalNewtonSqrtTest {

                @Test
                @DisplayName("Quadratwurzel von 0 ist 0")
                void testSqrtZero() {
                    BigDecimal result = BigDecimalNewtonSqrt.sqrt(BigDecimal.ZERO, 10);
                    assertEquals(BigDecimal.ZERO.setScale(10), result);
                }

                @Test
                @DisplayName("Quadratwurzel von 1 ist 1")
                void testSqrtOne() {
                    BigDecimal result = BigDecimalNewtonSqrt.sqrt(BigDecimal.ONE, 10);
                    assertEquals(BigDecimal.ONE.setScale(10), result);
                }

                @Test
                @DisplayName("Quadratwurzel von 4 ist 2")
                void testSqrtFour() {
                    BigDecimal result = BigDecimalNewtonSqrt.sqrt(new BigDecimal("4"), 10);
                    assertEquals(new BigDecimal("2.0000000000"), result);
                }

                @Test
                @DisplayName("Quadratwurzel von 2 mit 20 Nachkommastellen")
                void testSqrtTwoHighPrecision() {
                    BigDecimal expected = new BigDecimal("1.41421356237309504880");
                    BigDecimal result = BigDecimalNewtonSqrt.sqrt(new BigDecimal("2"), 20);
                    // Vergleiche die ersten 20 Nachkommastellen
                    assertEquals(expected, result);
                }

                @Test
                @DisplayName("Quadratwurzel von 9 ist 3")
                void testSqrtNine() {
                    BigDecimal result = BigDecimalNewtonSqrt.sqrt(new BigDecimal("9"), 8);
                    assertEquals(new BigDecimal("3.00000000"), result);
                }

                @Test
                @DisplayName("Negative Eingabe wirft IllegalArgumentException")
                void testNegativeInputThrows() {
                    assertThrows(IllegalArgumentException.class, () ->
                        BigDecimalNewtonSqrt.sqrt(new BigDecimal("-1"), 10)
                    );
                }

                @Test
                @DisplayName("Zu großer scale wirft IllegalArgumentException")
                void testTooLargeScaleThrows() {
                    int tooLargeScale = Integer.MAX_VALUE / 5 + 1;
                    assertThrows(IllegalArgumentException.class, () ->
                        BigDecimalNewtonSqrt.sqrt(BigDecimal.ONE, tooLargeScale)
                    );
                }

                @Test
                @DisplayName("Quadratwurzel von sehr großer Zahl")
                void testSqrtLargeNumber() {
                    BigDecimal large = new BigDecimal("1000000000000");
                    BigDecimal result = BigDecimalNewtonSqrt.sqrt(large, 5);
                    assertEquals(new BigDecimal("1000000.00000"), result);
                }

                @Test
                @DisplayName("Quadratwurzel von kleiner Zahl")
                void testSqrtSmallNumber() {
                    BigDecimal small = new BigDecimal("0.0004");
                    BigDecimal result = BigDecimalNewtonSqrt.sqrt(small, 6);
                    assertEquals(new BigDecimal("0.020000"), result);
                }
            }

        Du kannst die Tests mit JUnit 5 ausführen. Falls du Unterstützung für das Test-Setup brauchst, sag einfach Bescheid!



Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: 10. Schritt: Umstieg auf Maven als Build-Tool

    .. compound::
        :class: llm-chat-user

        Bitte erstelle mir eine Maven Konfiguration. Wenn möglich soll die Maven Konfiguration auch die Code Formatierung übernehmen und auf ein Plug-in setzten, dass auch die Dokumentation formatieren kann.

    .. compound::
        :class: llm-chat-agent incremental

        Hier ist eine Maven-Konfiguration (``pom.xml``), die folgende Features
        bietet:

        - **JUnit 5** für Tests
        - **Spotless Maven Plugin** für die automatische Formatierung von Java-Code und JavaDoc-Kommentaren
        - **Zeilenlänge** und **JavaDoc-Umbruch** auf 70 Zeichen

        .. code:: xml

            <?xml version="1.0" encoding="UTF-8"?>
            <project xmlns="http://maven.apache.org/POM/4.0.0"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                                            https://maven.apache.org/xsd/maven-4.0.0.xsd">
                <modelVersion>4.0.0</modelVersion>

                <groupId>de.michael.sqrt</groupId>
                <artifactId>sqrt-jshell-vibecoded</artifactId>
                <version>1.0.0</version>
                <packaging>jar</packaging>

                <name>SQRT-JShell-VibeCoded</name>
                <description>Berechnung der Quadratwurzel mit Newtons Verfahren und Formatierung/Tests</description>

                <properties>
                    <maven.compiler.source>17</maven.compiler.source>
                    <maven.compiler.target>17</maven.compiler.target>
                    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                    <junit.version>5.10.2</junit.version>
                    <formatter.version>2.23.0</formatter.version>
                </properties>

                <dependencies>
                    <!-- JUnit 5 Jupiter API & Engine -->
                    <dependency>
                        <groupId>org.junit.jupiter</groupId>
                        <artifactId>junit-jupiter</artifactId>
                        <version>${junit.version}</version>
                        <scope>test</scope>
                    </dependency>
                </dependencies>

                <build>
                    <plugins>
                        <!-- Maven Compiler Plugin -->
                        <plugin>
                            <artifactId>maven-compiler-plugin</artifactId>
                            <version>3.11.0</version>
                            <configuration>
                                <source>${maven.compiler.source}</source>
                                <target>${maven.compiler.target}</target>
                            </configuration>
                        </plugin>

                        <!-- Maven Surefire Plugin for running tests -->
                        <plugin>
                            <artifactId>maven-surefire-plugin</artifactId>
                            <version>3.2.5</version>
                            <configuration>
                                <useSystemClassLoader>false</useSystemClassLoader>
                            </configuration>
                        </plugin>

                        <!-- Spotless Plugin for code and JavaDoc formatting -->
                        <plugin>
                            <groupId>com.diffplug.spotless</groupId>
                            <artifactId>spotless-maven-plugin</artifactId>
                            <version>2.23.0</version>
                            <executions>
                                <execution>
                                    <goals>
                                        <goal>apply</goal>
                                    </goals>
                                </execution>
                            </executions>
                            <configuration>
                                <java>
                                    <googleJavaFormat>
                                        <version>1.17.0</version>
                                        <style>AOSP</style>
                                    </googleJavaFormat>
                                    <target>src/**/*.java</target>
                                    <removeUnusedImports />
                                    <formatAnnotations />
                                    <importOrder>
                                        <order>java,javax,org,com</order>
                                    </importOrder>
                                    <licenseHeader>
                                        <!-- Optional: Add a license header if desired -->
                                    </licenseHeader>
                                    <endWithNewline>true</endWithNewline>
                                    <indent>
                                        <spaces>4</spaces>
                                    </indent>
                                    <trimTrailingWhitespace>true</trimTrailingWhitespace>
                                    <maxLineLength>70</maxLineLength>
                                    <javadocFormatter>
                                        <enabled>true</enabled>
                                        <wrapLength>70</wrapLength>
                                    </javadocFormatter>
                                </java>
                            </configuration>
                        </plugin>
                    </plugins>
                </build>
            </project>

        *Wichtige Hinweise:*

        - Die Formatierung und das JavaDoc-Wrapping übernimmt das Spotless-Plugin mit Google Java Format und aktiviertem       JavaDoc-Formatter.
        - Du kannst formatieren mit:

          ``sh   mvn spotless:apply``
        - Tests laufen wie gewohnt mit:

          ``sh   mvn test``

        Falls du weitere Anpassungen oder eine andere Formatierungsstrategie
        möchtest, gib einfach Bescheid!


    .. remark::

        Die Konfiguration war in der Form nicht korrekt konnte aber automatisch korrigiert werden.

        .. compound::
            :class: llm-chat-user

            Ich bekomme folgende Fehlermeldung: [ERROR] Failed to execute goal com.diffplug.spotless:spotless-maven-plugin:2.23.0:apply (default-cli) on project sqrt-jshell-vibecoded: Unable to parse configuration of mojocom.diffplug.spotless:spotless-maven-plugin:2.23.0:apply for parameter target: Cannot find ‘target’ in class com.diffplug.spotless.maven.java.Java




Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: 10. Schritt: Verwendung aktueller Tools einfordern

    .. compound::
        :class: llm-chat-user

        Bitte aktualisiere die Versionen der Plug-ins auf die neuesten Versionen.

    .. compound::
        :class: llm-chat-agent incremental

        Alle Versionen in deiner ``pom.xml`` wurden auf die neuesten stabilen Versionen aktualisiert und als Properties hinterlegt, damit du sie zentral pflegen kannst. Hier sind die wichtigsten Änderungen:

        - Java-Version: 21
        - Spotless Maven Plugin: 2.45.0
        - Google Java Format: 1.17.0
        - Maven Compiler Plugin: 3.11.0
        - Maven Surefire Plugin: 3.2.5
        - JUnit Jupiter: 5.10.2


    .. attention::
        :class: incremental

        Keine einzige Version war halbwegs aktuell. Ein manuelles Eingreifen war erforderlich.

        Aktuell sind/bzw waren zum Zeitpunkt der Erstellung der Folien die folgenden Versionen:

        - Java-Version: 24
        - Spotless Maven Plugin: 2.46.1
        - Maven Compiler Plugin: 3.14.0
        - Maven Surefire Plugin: 3.5.3
        - JUnit Jupiter: 5.13.4


    .. remark::
        :class: incremental

        Ein expliziter Prompt nach neuen Versionen im Internet zu suchen führte zu folgendem Fehler:

        .. compound::
            :class: llm-chat-agent

            Failed to connect to API: 400 Bad Request {"error":{"message":"prompt token count of 154533 exceeds the limit of 128000","code":"model_max_prompt_tokens_exceeded"}}

        [D.h. das Ergebnisse der Webabfrage durch den Agenten ist zu groß und konnte von diesem nicht verarbeitet werden.]



Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: 11. Schritt: Build-Skript korrigieren

    .. compound::
        :class: llm-chat-user

        Wenn ich mvn test ausführe, dann bekomme ich: “No tests to run.” Bitte behebe das Problem.

    .. compound::
        :class: llm-chat-agent incremental

        Das Problem lag daran, dass sich deine Testklasse nicht im
        Standard-Maven-Testverzeichnis (``src/test/java``) befand.
        Ich habe die Datei jetzt nach ``src/test/java/BigDecimalNewtonSqrtTest.java`` verschoben.

        *Jetzt erkennt Maven die Tests automatisch.*

        Du kannst die Tests wie gewohnt mit

        .. code:: sh

            mvn test

        ausführen.

        Falls du weitere Testklassen hast, sollten diese ebenfalls im         Verzeichnis ``src/test/java`` liegen und das Package-Statement (falls
        verwendet) korrekt gesetzt sein.  Wenn du noch ein Package hinzufügen möchtest, kann ich das gerne für dich erledigen!

    .. attention::
        :class: incremental

        An diese Stelle wurde zwar der Test richtig verschoben, aber der eigentliche Code nicht. Es waren in der Folge mehrere Prompts, mit den entsprechenden Fehlermeldungen notwendig, damit die Projektstruktur angepasst wurde. Hier wäre eine manuelle Korrektur schneller gewesen. Erst danach konnten die Tests ausgeführt werden.



Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: 12. Schritt: Fehler korrigieren

    .. compound::
        :class: llm-chat-user

        Ein Test schlägt fehl: [ERROR] BigDecimalNewtonSqrtTest.testSqrtZero – Time elapsed: 0.003 s <<< FAILURE! org.opentest4j.AssertionFailedError: expected: <0E-10> but was: <0>

    .. compound::
        :class: llm-chat-agent incremental

        Das Problem entsteht, weil ``BigDecimal.ZERO.setScale(10)`` ergibt ``0E-10``, während das Ergebnis der Methode ``sqrt`` einfach ``0`` ist (Skalierung 0). Obwohl beide Werte mathematisch gleich sind, unterscheidet ``BigDecimal.equals()`` zwischen der Skala.

        ...

    .. attention::
        :class: incremental

        Auch hier hat es mehrere Runden gedauert bis die Tests einwandfrei funktioniert haben. Der dabei entstandene Testcode ist jedoch klar Verbesserungsfähig, da er auf Stringvergleich (zum Beispiel: :java:` assertEquals("0.0000000000", result.toPlainString());`) gesetzt hat, was nicht notwendig gewesen wäre.  Fehler im Code gab es keine.



Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: 13. Schritt: Code verbessern

    .. compound::
        :class: llm-chat-user

        Bitte ergänze den Code so, dass negative scale Werte sinnvoll behandelt werden.

    .. compound::
        :class: llm-chat-agent incremental

        Die Methode behandelt jetzt negative Werte für ``scale`` sinnvoll:

        .. code:: java
            :number-lines:

             if (scale < 0) {
                throw new IllegalArgumentException("scale muss >= 0 sein");
            }

        Das bedeutet:

        Wenn ein negativer Wert für ``scale`` übergeben wird, wird eine aussagekräftige ``IllegalArgumentException`` geworfen. Damit ist die Eingabeprüfung für die Präzision jetzt vollständig und robust.

    .. attention::

        Der Testcode wurde nicht mit bedacht!


Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: 14. Schritt: Tests nachziehen

    .. compound::
        :class: llm-chat-user

        Bitte ergänze auch die Tests.

    .. compound::
        :class: llm-chat-agent incremental


        Die Tests wurden ergänzt:

        Es gibt jetzt einen Test, der prüft, dass ein negativer Wert für
        ``scale`` eine ``IllegalArgumentException`` auslöst:

        .. code:: java
            :number-lines:

             @Test
            @DisplayName("Negative scale wirft IllegalArgumentException")
            void testNegativeScaleThrows() {
                assertThrows(IllegalArgumentException.class, () ->
                    BigDecimalNewtonSqrt.sqrt(BigDecimal.ONE, -1)
                );
            }

        Damit ist die Eingabeprüfung für negative ``scale``-Werte auch durch die
        Tests abgedeckt.



Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: 15. Schritt: Build-Skript vervollständigen

    .. compound::
        :class: llm-chat-user

        Bitte ergänze das Build-Skript so, dass die Testabdeckung berechnet wird.

    .. compound::
        :class: llm-chat-agent incremental


        Um die Testabdeckung in einem Maven-Projekt zu berechnen, wird üblicherweise das Plugin **JaCoCo** verwendet. Dieses Plugin erstellt Berichte über die Testabdeckung (Coverage) und lässt sich einfach in die ``pom.xml`` integrieren.

        Hier ist die Ergänzung für dein Build-Skript:

        .. code:: xml

            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.11</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>test</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

    .. attention::

        Auch hier war eine manuelle Anpassung der Version erforderlich. Die angegebene Version war mit dem Projekt nicht kompatibel.


Beispielhafte Verwendung von GPT-4.1
-----------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: 16. Schritt: Basierend auf Codeabdeckung weitere Tests fordern

    .. compound::
        :class: llm-chat-user

        Die Testabeckung sagt, dass die Zeile 62 noch nicht getestet wird. Kannst Du dafür einen Testfall generieren?

    .. compound::
        :class: llm-chat-agent incremental

        Die Zeile 62 ist:

        .. code:: java
            :number-lines:

            if (count >= maxIterations) {
                throw new ArithmeticException(
                    "Maximale Iterationszahl (" +
                    maxIterations +
                    ") erreicht, Ergebnis möglicherweise ungenau."
                );
            }

        Das bedeutet:

        Du brauchst einen Test, der absichtlich dafür sorgt, dass die maximale
        Iterationszahl überschritten wird.

        *Wie erreichst du das?*

        - Wähle einen Wert für ``n``, der schwer konvergiert (z.B. eine sehr große Zahl mit sehr hoher Präzision).
        - Setze ``scale`` auf einen Wert > 0, aber so hoch, dass die Konvergenz nicht innerhalb von ``maxIterations`` erreicht wird.
        - Oder: Manipuliere die Methode für den Test, sodass sie nie konvergiert (das ist aber unsauber).

        *Praktischer Testfall:*

        Setze ``scale`` auf einen sehr hohen Wert (z.B. ``Integer.MAX_VALUE / 5``), aber das wirft schon vorher eine ``IllegalArgumentException``.  Deshalb: Wähle einen Wert, bei dem die Methode zwar startet, aber   nicht konvergiert.  Ein Ansatz ist, eine Zahl zu wählen, die sehr klein ist und eine sehr hohe Präzision verlangt.

    .. attention::

        Hier war auch nach mehreren Versuchen es nicht möglich einen Testfall zu finden, der die maximale Iterationszahl erreicht.


Beispielhafte Verwendung von GPT-4.1
-------------------------------------

.. story::

    .. rubric:: Zusammenfassung

    .. class:: incremental-list

    - Gesamtaufwand ca. 2 Stunden
    - An mehreren Stellen war manuelles Eingreifen erforderlich
    - Man kann Dinge einfordern, die wenig Sinn machen, ohne dass man (unmittelbar) darauf aufmerksam gemacht wird (z. B. Verwendung von npm)
    - An vielen Stellen war das einfache Kopieren von Fehlermeldungen in den Chat ausreichend, um die Fehler korrigiert zu bekommen.
    - Wenn der Prompt nicht präzise genug ist, dann muss man (ggf. mehrfach) prompten.
    - Kritisches Hinterfragen des Codes ist dringend notwendig. Nur dann erhält man Code, der auch höhere Qualitätsstandards erreicht.
    - Häufig wird die nicht die Codebasis als Ganzes betrachtet. Zum Beispiel brauchen ggf. Anpassungen von Dokumenten, Skripts und Tests nachfolgende Prompts.



.. class:: new-subsection transition-move-to-top

Beispielhafte Verwendung von Claude Sonnet 4 Thinking
---------------------------------------------------------


Beispielhafte Verwendung von Claude Sonnet 4 Thinking
-------------------------------------------------------

Das Ziel ist (auch hier) die Generierung einer Kommandozeilenanwendung zur Berechnung der Quadratwurzel einer Zahl mit vorgegebener, aber beliebiger Genauigkeit.

Der Unterschied ist jedoch, dass wir versuchen das ganze Projekt in einem Zug generieren zu lassen, in dem wir einen Prompt erstellen, der „alle“ Anforderungen des Projekts beschreibt.



Beispielhafte Verwendung von Claude Sonnet 4 Thinking
-------------------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: Initialer Prompt

    .. compound::
        :class: llm-chat-user

        Bitte generiere mir ein Java-Kommandozeilenprogramm zur Berechung der Quadratwurzel unter Verwendung der Newtown-Raphson-Methode. Die Anforderungen sind:

        - Es soll möglich sein die gewünschte Anzahl an Nachkommastellen anzugeben.
        - Die Methode zur Berechnung der Quadratwurzel soll auch aus anderem Code heraus aufrufbar sein.
        - Die Methode soll robust sein bei fehlerhaften Eingaben und ggf. aussagekräftige Ausnahmen werfen.
        - Die Methode zur Berechnung der Quadratwurzel soll über eine umfassende Dokumentation verfügen, die auch alle Sonderfälle und Ausnahmen dokumentiert.
        - Es soll nach Möglichkeit nur eine Datei generiert werden, um eine optionale Verwendung als Skript einfach zu ermöglichen.
        - Das Programm soll die neueste Version von Java verwenden.
        - Alle verwendeten Bibliotheken und Tools sollen auf dem neuesten Stand sein.
        - Es soll ein Build-Skript geben, dass den Code formatiert und testet. Bei der Codeformatierung soll insbesondere eine maximale Zeilenlänge von 70 Zeichen eingehalten werden. Die Tests sollen die Methode, die die Berechnung durchführt, umfassend testen; inkl. Sonderfälle. Es soll auch eine Codeabdeckung berechnet werden und nach Möglichkeit 100% Branch-Coverage erreicht werden.



Beispielhafte Verwendung von Claude Sonnet 4 Thinking
-------------------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: (:eng:`Thought Process`)

    .. compound::
        :class: llm-chat-agent

        :xml:`<think>`

        Das ist eine umfassende Anforderung für ein Java-Kommandozeilenprogramm. Lass mich die Anforderungen durchgehen:

        1. Java-Kommandozeilenprogramm zur Berechnung der Quadratwurzel mit Newton-Raphson-Methode
        2. Anzahl der Nachkommastellen soll konfigurierbar sein
        3. Methode soll aus anderem Code aufrufbar sein
        4. Robust bei fehlerhaften Eingaben mit aussagekräftigen Ausnahmen
        5. Umfassende Dokumentation inkl. Sonderfälle
        6. Nur eine Datei wenn möglich (für Skript-Verwendung)
        7. Neueste Java-Version
        8. Neueste Bibliotheken
        9. Build-Skript mit Formatierung (max 70 Zeichen Zeilenlänge) und Tests
        10. Umfassende Tests inkl. Sonderfälle
        11. 100% Branch-Coverage angestrebt

        Ich sollte zuerst schauen, was im aktuellen Projektverzeichnis ist, und dann eine Struktur aufbauen.

        Newton-Raphson für Quadratwurzel:
        :math:`x_{n+1} = (x_n + a/x_n) / 2`
        wobei a die Zahl ist, deren Quadratwurzel gesucht wird.

        Ich werde:

        1. Eine Java-Klasse erstellen mit der Newton-Raphson Implementierung
        2. Kommandozeilenhandling hinzufügen
        3. Robuste Fehlerbehandlung
        4. Umfassende Dokumentation
        5. Ein Build-Skript (build.gradle oder Maven) für Formatierung und Tests
        6. JUnit-Tests mit hoher Abdeckung

        Für maximale Flexibilität werde ich eine einzelne Java-Datei entwickeln, die sowohl als Skript als auch als importierbare Bibliothek funktioniert. Das bedeutet, ich integriere Kommandozeilen-Logik direkt in die Hauptklasse, sodass sie sowohl standalone als auch als Komponente in anderen Projekten nutzbar ist.

        :xml:`</think>`


    .. remark::

        Danach erfolgt ein langwieriger Prozess in dem das Model die einzelnen Schritte abarbeitet und dabei immer wieder überprüft ob das erwartete Ergebnis erreicht wurde. Stellt es dabei Probleme fest, zum Beispiel beim Ausführen des Build-Skriptes oder der Tests, dann versucht es selber diese zu korrigieren. Dies ist im Chat genau nachlesbar.



Beispielhafte Verwendung von Claude Sonnet 4 Thinking
-------------------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: Generierter Code

    .. compound::
        :class: llm-chat-agent


        .. code:: java
            :number-lines:

             import java.math.BigDecimal;
            import java.math.MathContext;
            import java.math.RoundingMode;
            import java.util.Scanner;

            /**
            * Ein hochpräziser Quadratwurzel-Rechner basierend auf der
            * Newton-Raphson-Methode.
            *
            * Diese Klasse bietet sowohl eine Kommandozeilenschnittstelle als auch
            * eine programmierbare API zur Berechnung von Quadratwurzeln mit
            * beliebiger Genauigkeit.
            *
            * @author Generated Code
            * @version 1.0
            * @since Java 21
            */
            public class SquareRootCalculator {

                /**
                * Standard-Präzision für Berechnungen (50 Dezimalstellen).
                */
                private static final int DEFAULT_PRECISION = 50;

                /**
                * Maximale erlaubte Präzision zur Vermeidung von
                * Performance-Problemen.
                */
                private static final int MAX_PRECISION = 1000;

                /**
                * Maximale Anzahl von Iterationen für die Newton-Raphson-Methode.
                */
                private static final int MAX_ITERATIONS = 10000;

                /**
                * Custom Exception für ungültige Eingaben.
                */
                public static class InvalidInputException extends Exception {
                    public InvalidInputException(String message) {
                        super(message);
                    }
                }

                /**
                * Custom Exception für Berechnungsfehler.
                */
                public static class CalculationException extends Exception {
                    public CalculationException(String message) {
                        super(message);
                    }
                }

                /**
                * Berechnet die Quadratwurzel einer Zahl mit der
                * Newton-Raphson-Methode.
                *
                * Die Newton-Raphson-Methode für Quadratwurzeln verwendet die
                * Iterationsformel:
                * x_{n+1} = (x_n + a/x_n) / 2
                *
                * wobei 'a' die Eingabezahl und x_0 ein Startwert ist.
                *
                * @param number Die Zahl, deren Quadratwurzel berechnet werden soll.
                *               Muss größer oder gleich 0 sein.
                * @param decimalPlaces Die Anzahl der gewünschten Nachkommastellen.
                *                      Muss zwischen 0 und 1000 liegen.
                * @return Die Quadratwurzel als BigDecimal mit der gewünschten
                *         Präzision.
                *
                * @throws InvalidInputException wenn die Eingabe ungültig ist:
                *         - number ist null
                *         - number ist negativ
                *         - decimalPlaces ist negativ oder größer als 1000
                * @throws CalculationException wenn die Berechnung fehlschlägt:
                *         - Konvergenz nicht erreicht nach MAX_ITERATIONS
                *         - Numerischer Overflow/Underflow
                *
                * Sonderfälle:
                * - sqrt(0) = 0
                * - sqrt(1) = 1
                * - Für sehr kleine Zahlen (< 1e-100) wird eine spezielle
                *   Behandlung angewendet
                * - Für sehr große Zahlen wird die Berechnung in Segmente
                *   aufgeteilt
                *
                * Beispiele:
                * <pre>
                * {@code
                * // Einfache Berechnung
                * BigDecimal result = calculateSquareRoot(
                *     new BigDecimal("2"), 5);
                * // Ergebnis: 1.41421
                *
                * // Hochpräzise Berechnung
                * BigDecimal precise = calculateSquareRoot(
                *     new BigDecimal("2"), 50);
                *
                * // Große Zahlen
                * BigDecimal large = calculateSquareRoot(
                *     new BigDecimal("1234567890123456789"), 10);
                * }
                * </pre>
                */
                public static BigDecimal calculateSquareRoot(BigDecimal number,
                                                        int decimalPlaces)
                        throws InvalidInputException, CalculationException {

                    // Eingabevalidierung
                    if (number == null) {
                        throw new InvalidInputException(
                            "Die Eingabezahl darf nicht null sein.");
                    }

                    if (number.compareTo(BigDecimal.ZERO) < 0) {
                        throw new InvalidInputException(
                            "Die Quadratwurzel aus negativen Zahlen ist nicht " +
                            "im Bereich der reellen Zahlen definiert. " +
                            "Eingabe: " + number);
                    }

                    if (decimalPlaces < 0) {
                        throw new InvalidInputException(
                            "Die Anzahl der Nachkommastellen muss nicht-negativ " +
                            "sein. Eingabe: " + decimalPlaces);
                    }

                    if (decimalPlaces > MAX_PRECISION) {
                        throw new InvalidInputException(
                            "Die maximale Anzahl von Nachkommastellen beträgt " +
                            MAX_PRECISION + ". Eingabe: " + decimalPlaces);
                    }

                    // Sonderfall: sqrt(0) = 0
                    if (number.compareTo(BigDecimal.ZERO) == 0) {
                        return BigDecimal.ZERO.setScale(decimalPlaces,
                                                    RoundingMode.HALF_UP);
                    }

                    // Sonderfall: sqrt(1) = 1
                    if (number.compareTo(BigDecimal.ONE) == 0) {
                        return BigDecimal.ONE.setScale(decimalPlaces,
                                                    RoundingMode.HALF_UP);
                    }

                    // Präzision für interne Berechnungen (höher als gewünscht)
                    int internalPrecision = Math.max(decimalPlaces + 10,
                                                DEFAULT_PRECISION);
                    MathContext mc = new MathContext(internalPrecision,
                                                RoundingMode.HALF_UP);

                    try {
                        // Startwert bestimmen (heuristische Schätzung)
                        BigDecimal x = getInitialGuess(number, mc);
                        BigDecimal previousX;

                        // Newton-Raphson-Iteration
                        for (int i = 0; i < MAX_ITERATIONS; i++) {
                            previousX = x;

                            // x_{n+1} = (x_n + number/x_n) / 2
                            BigDecimal quotient = number.divide(x, mc);
                            x = x.add(quotient).divide(BigDecimal.valueOf(2), mc);

                            // Konvergenzprüfung
                            if (hasConverged(x, previousX, decimalPlaces + 5)) {
                                return x.setScale(decimalPlaces,
                                                RoundingMode.HALF_UP);
                            }
                        }

                        throw new CalculationException(
                            "Die Newton-Raphson-Methode konvergierte nicht nach " +
                            MAX_ITERATIONS + " Iterationen. Möglicherweise ist " +
                            "die gewünschte Präzision zu hoch oder die Eingabe " +
                            "numerisch instabil.");

                    } catch (ArithmeticException e) {
                        throw new CalculationException(
                            "Numerischer Fehler während der Berechnung: " +
                            e.getMessage() + ". Dies kann bei sehr großen oder " +
                            "sehr kleinen Zahlen auftreten.");
                    }
                }

                /**
                * Bestimmt einen geeigneten Startwert für die Newton-Raphson-Iteration.
                *
                * @param number Die Zahl, deren Quadratwurzel berechnet werden soll
                * @param mc Der MathContext für die Berechnung
                * @return Ein geeigneter Startwert
                */
                private static BigDecimal getInitialGuess(BigDecimal number,
                                                        MathContext mc) {
                    // Für Zahlen nahe 1: verwende die Zahl selbst
                    if (number.compareTo(BigDecimal.valueOf(0.1)) >= 0 &&
                        number.compareTo(BigDecimal.valueOf(10)) <= 0) {
                        return number;
                    }

                    // Für sehr kleine Zahlen: verwende 1
                    if (number.compareTo(BigDecimal.valueOf(0.1)) < 0) {
                        return BigDecimal.ONE;
                    }

                    // Für große Zahlen: verwende number/2
                    return number.divide(BigDecimal.valueOf(2), mc);
                }

                /**
                * Prüft, ob die Newton-Raphson-Iteration konvergiert ist.
                *
                * @param current Der aktuelle Wert
                * @param previous Der vorherige Wert
                * @param requiredPrecision Die erforderliche Präzision
                * @return true, wenn konvergiert
                */
                private static boolean hasConverged(BigDecimal current,
                                                BigDecimal previous,
                                                int requiredPrecision) {
                    if (current.equals(previous)) {
                        return true;
                    }

                    BigDecimal difference = current.subtract(previous).abs();
                    BigDecimal tolerance = BigDecimal.ONE.divide(
                        BigDecimal.TEN.pow(requiredPrecision),
                        new MathContext(requiredPrecision + 10));

                    return difference.compareTo(tolerance) <= 0;
                }

                /**
                * Überladene Methode mit Standard-Präzision von 10 Nachkommastellen.
                *
                * @param number Die Zahl, deren Quadratwurzel berechnet werden soll
                * @return Die Quadratwurzel mit 10 Nachkommastellen
                * @throws InvalidInputException bei ungültiger Eingabe
                * @throws CalculationException bei Berechnungsfehlern
                */
                public static BigDecimal calculateSquareRoot(BigDecimal number)
                        throws InvalidInputException, CalculationException {
                    return calculateSquareRoot(number, 10);
                }

                /**
                * Convenience-Methode für double-Werte.
                *
                * @param number Die Zahl als double
                * @param decimalPlaces Anzahl Nachkommastellen
                * @return Die Quadratwurzel
                * @throws InvalidInputException bei ungültiger Eingabe
                * @throws CalculationException bei Berechnungsfehlern
                */
                public static BigDecimal calculateSquareRoot(double number,
                                                        int decimalPlaces)
                        throws InvalidInputException, CalculationException {
                    if (Double.isNaN(number) || Double.isInfinite(number)) {
                        throw new InvalidInputException(
                            "Die Eingabe darf nicht NaN oder unendlich sein.");
                    }
                    return calculateSquareRoot(BigDecimal.valueOf(number),
                                            decimalPlaces);
                }

                /**
                * Hauptmethode für die Kommandozeilenschnittstelle.
                *
                * Verwendung:
                * - Ohne Argumente: Interaktiver Modus
                * - Mit einem Argument: Berechnet sqrt(arg) mit 10 Nachkommastellen
                * - Mit zwei Argumenten: Berechnet sqrt(arg1) mit arg2
                *   Nachkommastellen
                *
                * @param args Kommandozeilenargumente
                */
                public static void main(String[] args) {
                    try {
                        if (args.length == 0) {
                            runInteractiveMode();
                        } else if (args.length == 1) {
                            calculateAndPrint(args[0], "10");
                        } else if (args.length == 2) {
                            calculateAndPrint(args[0], args[1]);
                        } else {
                            printUsage();
                            System.exit(1);
                        }
                    } catch (Exception e) {
                        System.err.println("Fehler: " + e.getMessage());
                        System.exit(1);
                    }
                }

                /**
                * Startet den interaktiven Modus.
                */
                private static void runInteractiveMode() {
                    Scanner scanner = new Scanner(System.in);

                    System.out.println("=================================");
                    System.out.println("  Quadratwurzel-Rechner v1.0");
                    System.out.println("  Newton-Raphson-Methode");
                    System.out.println("=================================");
                    System.out.println();

                    while (true) {
                        try {
                            System.out.print("Zahl (oder 'quit' zum Beenden): ");
                            String numberInput = scanner.nextLine().trim();

                            if ("quit".equalsIgnoreCase(numberInput) ||
                                "exit".equalsIgnoreCase(numberInput)) {
                                System.out.println("Auf Wiedersehen!");
                                break;
                            }

                            System.out.print("Nachkommastellen (Standard: 10): ");
                            String precisionInput = scanner.nextLine().trim();

                            if (precisionInput.isEmpty()) {
                                precisionInput = "10";
                            }

                            calculateAndPrint(numberInput, precisionInput);
                            System.out.println();

                        } catch (Exception e) {
                            System.err.println("Fehler: " + e.getMessage());
                            System.out.println();
                        }
                    }

                    scanner.close();
                }

                /**
                * Berechnet und gibt das Ergebnis aus.
                */
                private static void calculateAndPrint(String numberStr,
                                                    String precisionStr)
                        throws InvalidInputException, CalculationException {

                    BigDecimal number;
                    int precision;

                    try {
                        number = new BigDecimal(numberStr);
                    } catch (NumberFormatException e) {
                        throw new InvalidInputException(
                            "'" + numberStr + "' ist keine gültige Zahl.");
                    }

                    try {
                        precision = Integer.parseInt(precisionStr);
                    } catch (NumberFormatException e) {
                        throw new InvalidInputException(
                            "'" + precisionStr +
                            "' ist keine gültige Anzahl von Nachkommastellen.");
                    }

                    long startTime = System.nanoTime();
                    BigDecimal result = calculateSquareRoot(number, precision);
                    long endTime = System.nanoTime();

                    System.out.println("Eingabe: " + number);
                    System.out.println("Nachkommastellen: " + precision);
                    System.out.println("Ergebnis: " + result);
                    System.out.printf("Berechnungszeit: %.2f ms%n",
                                    (endTime - startTime) / 1_000_000.0);

                    // Verifikation (Quadrat des Ergebnisses)
                    if (precision <= 20) { // Nur für kleinere Präzisionen
                        BigDecimal verification = result.multiply(result);
                        System.out.println("Verifikation (Quadrat): " +
                                        verification.setScale(precision,
                                                            RoundingMode.HALF_UP));
                    }
                }

                /**
                * Gibt die Verwendungsinformationen aus.
                */
                private static void printUsage() {
                    System.out.println("Quadratwurzel-Rechner - Newton-Raphson-Methode");
                    System.out.println();
                    System.out.println("Verwendung:");
                    System.out.println("  java SquareRootCalculator");
                    System.out.println("    Startet den interaktiven Modus");
                    System.out.println();
                    System.out.println("  java SquareRootCalculator <zahl>");
                    System.out.println("    Berechnet sqrt(<zahl>) mit 10 " +
                                    "Nachkommastellen");
                    System.out.println();
                    System.out.println("  java SquareRootCalculator <zahl> " +
                                    "<nachkommastellen>");
                    System.out.println("    Berechnet sqrt(<zahl>) mit der " +
                                    "angegebenen Präzision");
                    System.out.println();
                    System.out.println("Beispiele:");
                    System.out.println("  java SquareRootCalculator 2");
                    System.out.println("  java SquareRootCalculator 2 20");
                    System.out.println("  java SquareRootCalculator 1234567890.123456");
                }
            }



Beispielhafte Verwendung von Claude Sonnet 4 Thinking
-------------------------------------------------------

.. scrollable:: llm-chat

    .. rubric:: Generierte Tests

    .. compound::
        :class: llm-chat-agent


        .. code:: java
            :number-lines:

            import static org.assertj.core.api.Assertions.*;
            import static org.junit.jupiter.api.Assertions.*;

            import java.io.ByteArrayInputStream;
            import java.io.ByteArrayOutputStream;
            import java.io.PrintStream;
            import java.math.BigDecimal;
            import java.math.RoundingMode;
            import java.util.stream.Stream;
            import org.junit.jupiter.api.AfterEach;
            import org.junit.jupiter.api.BeforeEach;
            import org.junit.jupiter.api.DisplayName;
            import org.junit.jupiter.api.Test;
            import org.junit.jupiter.api.TestInstance;
            import org.junit.jupiter.params.ParameterizedTest;
            import org.junit.jupiter.params.provider.Arguments;
            import org.junit.jupiter.params.provider.MethodSource;
            import org.junit.jupiter.params.provider.ValueSource;

            @TestInstance(TestInstance.Lifecycle.PER_CLASS)
            @DisplayName("SquareRootCalculator Tests")
            class SquareRootCalculatorTest {

                private final ByteArrayOutputStream outputStreamCaptor =
                        new ByteArrayOutputStream();
                private final ByteArrayOutputStream errorStreamCaptor =
                        new ByteArrayOutputStream();
                private final PrintStream standardOut = System.out;
                private final PrintStream standardErr = System.err;

                @BeforeEach
                void setUp() {
                    System.setOut(new PrintStream(outputStreamCaptor));
                    System.setErr(new PrintStream(errorStreamCaptor));
                }

                @AfterEach
                void tearDown() {
                    System.setOut(standardOut);
                    System.setErr(standardErr);
                }

                @Test
                @DisplayName("Should calculate square root of 2 with default precision")
                void testSquareRootOfTwoDefaultPrecision() throws Exception {
                    BigDecimal result = SquareRootCalculator.calculateSquareRoot(
                            new BigDecimal("2"));

                    assertThat(result).isNotNull();
                    assertThat(result.scale()).isEqualTo(10);

                    // Verify precision (should be close to 1.4142135623)
                    BigDecimal expected = new BigDecimal("1.4142135624");
                    assertThat(result.subtract(expected).abs())
                            .isLessThan(new BigDecimal("0.0000000001"));
                }

                @Test
                @DisplayName("Should calculate square root with specified precision")
                void testSquareRootWithSpecifiedPrecision() throws Exception {
                    BigDecimal result = SquareRootCalculator.calculateSquareRoot(
                            new BigDecimal("2"), 5);

                    assertThat(result.scale()).isEqualTo(5);
                    assertThat(result.toString()).isEqualTo("1.41421");
                }

                @Test
                @DisplayName("Should return 0 for square root of 0")
                void testSquareRootOfZero() throws Exception {
                    BigDecimal result = SquareRootCalculator.calculateSquareRoot(
                            BigDecimal.ZERO, 10);

                    assertThat(result).isEqualTo(
                            BigDecimal.ZERO.setScale(10, RoundingMode.HALF_UP));
                }

                @Test
                @DisplayName("Should return 1 for square root of 1")
                void testSquareRootOfOne() throws Exception {
                    BigDecimal result = SquareRootCalculator.calculateSquareRoot(
                            BigDecimal.ONE, 10);

                    assertThat(result).isEqualTo(
                            BigDecimal.ONE.setScale(10, RoundingMode.HALF_UP));
                }

                @ParameterizedTest
                @MethodSource("validSquareRootTestCases")
                @DisplayName("Should calculate square roots for various valid inputs")
                void testValidSquareRoots(BigDecimal input, int precision,
                                        BigDecimal expectedApprox) throws Exception {
                    BigDecimal result = SquareRootCalculator.calculateSquareRoot(
                            input, precision);

                    assertThat(result).isNotNull();
                    assertThat(result.scale()).isEqualTo(precision);

                    // Verify by squaring the result
                    BigDecimal squared = result.multiply(result);
                    BigDecimal tolerance = BigDecimal.ONE.divide(
                            BigDecimal.TEN.pow(Math.min(precision, 10)));

                    assertThat(squared.subtract(input).abs())
                            .isLessThanOrEqualTo(tolerance);
                }

                private static Stream<Arguments> validSquareRootTestCases() {
                    return Stream.of(
                            Arguments.of(new BigDecimal("4"), 5, new BigDecimal("2")),
                            Arguments.of(new BigDecimal("9"), 3, new BigDecimal("3")),
                            Arguments.of(new BigDecimal("16"), 2, new BigDecimal("4")),
                            Arguments.of(new BigDecimal("0.25"), 8, new BigDecimal("0.5")),
                            Arguments.of(new BigDecimal("0.01"), 10,
                                    new BigDecimal("0.1")),
                            Arguments.of(new BigDecimal("100"), 1, new BigDecimal("10")),
                            Arguments.of(new BigDecimal("1000000"), 15,
                                    new BigDecimal("1000")),
                            Arguments.of(new BigDecimal("0.000001"), 20,
                                    new BigDecimal("0.001"))
                    );
                }

                @Test
                @DisplayName("Should throw InvalidInputException for null input")
                void testNullInput() {
                    SquareRootCalculator.InvalidInputException exception =
                            assertThrows(SquareRootCalculator.InvalidInputException.class,
                                    () -> SquareRootCalculator.calculateSquareRoot(
                                            null, 10));

                    assertThat(exception.getMessage())
                            .contains("darf nicht null sein");
                }

                @Test
                @DisplayName("Should throw InvalidInputException for negative input")
                void testNegativeInput() {
                    SquareRootCalculator.InvalidInputException exception =
                            assertThrows(SquareRootCalculator.InvalidInputException.class,
                                    () -> SquareRootCalculator.calculateSquareRoot(
                                            new BigDecimal("-1"), 10));

                    assertThat(exception.getMessage())
                            .contains("negativen Zahlen")
                            .contains("-1");
                }

                @Test
                @DisplayName("Should throw InvalidInputException for negative decimal places")
                void testNegativeDecimalPlaces() {
                    SquareRootCalculator.InvalidInputException exception =
                            assertThrows(SquareRootCalculator.InvalidInputException.class,
                                    () -> SquareRootCalculator.calculateSquareRoot(
                                            new BigDecimal("2"), -1));

                    assertThat(exception.getMessage())
                            .contains("nicht-negativ")
                            .contains("-1");
                }

                @Test
                @DisplayName("Should throw InvalidInputException for too high precision")
                void testTooHighPrecision() {
                    SquareRootCalculator.InvalidInputException exception =
                            assertThrows(SquareRootCalculator.InvalidInputException.class,
                                    () -> SquareRootCalculator.calculateSquareRoot(
                                            new BigDecimal("2"), 1001));

                    assertThat(exception.getMessage())
                            .contains("maximale Anzahl")
                            .contains("1000")
                            .contains("1001");
                }

                @Test
                @DisplayName("Should handle maximum allowed precision")
                void testMaximumPrecision() throws Exception {
                    BigDecimal result = SquareRootCalculator.calculateSquareRoot(
                            new BigDecimal("2"), 1000);

                    assertThat(result).isNotNull();
                    assertThat(result.scale()).isEqualTo(1000);
                }

                @Test
                @DisplayName("Should calculate square root for double input")
                void testDoubleInput() throws Exception {
                    BigDecimal result = SquareRootCalculator.calculateSquareRoot(
                            4.0, 5);

                    assertThat(result.toString()).isEqualTo("2.00000");
                }

                @Test
                @DisplayName("Should throw InvalidInputException for NaN double input")
                void testNaNDoubleInput() {
                    SquareRootCalculator.InvalidInputException exception =
                            assertThrows(SquareRootCalculator.InvalidInputException.class,
                                    () -> SquareRootCalculator.calculateSquareRoot(
                                            Double.NaN, 5));

                    assertThat(exception.getMessage())
                            .contains("NaN oder unendlich");
                }

                @Test
                @DisplayName("Should throw InvalidInputException for infinite double input")
                void testInfiniteDoubleInput() {
                    SquareRootCalculator.InvalidInputException exception =
                            assertThrows(SquareRootCalculator.InvalidInputException.class,
                                    () -> SquareRootCalculator.calculateSquareRoot(
                                            Double.POSITIVE_INFINITY, 5));

                    assertThat(exception.getMessage())
                            .contains("NaN oder unendlich");
                }

                @Test
                @DisplayName("Should handle very small numbers")
                void testVerySmallNumbers() throws Exception {
                    BigDecimal result = SquareRootCalculator.calculateSquareRoot(
                            new BigDecimal("1e-50"), 10);

                    assertThat(result).isNotNull();
                    assertThat(result.scale()).isEqualTo(10);
                }

                @Test
                @DisplayName("Should handle very large numbers")
                void testVeryLargeNumbers() throws Exception {
                    BigDecimal result = SquareRootCalculator.calculateSquareRoot(
                            new BigDecimal("1e50"), 10);

                    assertThat(result).isNotNull();
                    assertThat(result.scale()).isEqualTo(10);
                }

                @Test
                @DisplayName("Should handle numbers close to 1")
                void testNumbersCloseToOne() throws Exception {
                    BigDecimal result = SquareRootCalculator.calculateSquareRoot(
                            new BigDecimal("1.000001"), 15);

                    assertThat(result).isNotNull();
                    assertThat(result.scale()).isEqualTo(15);
                }

                @Test
                @DisplayName("Should run main with no arguments (interactive mode mock)")
                void testMainWithNoArguments() throws Exception {
                    // Mock interactive input
                    String input = "4\n5\nquit\n";
                    System.setIn(new ByteArrayInputStream(input.getBytes()));

                    SquareRootCalculator.main(new String[]{});

                    String output = outputStreamCaptor.toString();
                    assertThat(output).contains("Quadratwurzel-Rechner v1.0");
                    assertThat(output).contains("Eingabe: 4");
                    assertThat(output).contains("Ergebnis: 2.00000");
                    assertThat(output).contains("Auf Wiedersehen!");
                }

                @Test
                @DisplayName("Should run main with one argument")
                void testMainWithOneArgument() throws Exception {
                    SquareRootCalculator.main(new String[]{"9"});

                    String output = outputStreamCaptor.toString();
                    assertThat(output).contains("Eingabe: 9");
                    assertThat(output).contains("Nachkommastellen: 10");
                    assertThat(output).contains("Ergebnis: 3.0000000000");
                }

                @Test
                @DisplayName("Should run main with two arguments")
                void testMainWithTwoArguments() throws Exception {
                    SquareRootCalculator.main(new String[]{"16", "3"});

                    String output = outputStreamCaptor.toString();
                    assertThat(output).contains("Eingabe: 16");
                    assertThat(output).contains("Nachkommastellen: 3");
                    assertThat(output).contains("Ergebnis: 4.000");
                }

                @Test
                @DisplayName("Should show usage for too many arguments")
                void testMainWithTooManyArguments() {
                    assertThrows(SystemExit.class, () -> {
                        try {
                            SquareRootCalculator.main(new String[]{"1", "2", "3", "4"});
                        } catch (Exception e) {
                            // Convert to our custom exception for testing
                            throw new SystemExit();
                        }
                    });

                    String output = outputStreamCaptor.toString();
                    assertThat(output).contains("Verwendung:");
                }

                @Test
                @DisplayName("Should handle invalid number in main")
                void testMainWithInvalidNumber() {
                    assertThrows(SystemExit.class, () -> {
                        try {
                            SquareRootCalculator.main(new String[]{"not_a_number"});
                        } catch (Exception e) {
                            throw new SystemExit();
                        }
                    });

                    String errorOutput = errorStreamCaptor.toString();
                    assertThat(errorOutput).contains("Fehler:");
                }

                @Test
                @DisplayName("Should handle invalid precision in main")
                void testMainWithInvalidPrecision() {
                    assertThrows(SystemExit.class, () -> {
                        try {
                            SquareRootCalculator.main(new String[]{"4", "not_a_number"});
                        } catch (Exception e) {
                            throw new SystemExit();
                        }
                    });

                    String errorOutput = errorStreamCaptor.toString();
                    assertThat(errorOutput).contains("Fehler:");
                }

                @Test
                @DisplayName("Should handle interactive mode with empty precision")
                void testInteractiveModeEmptyPrecision() throws Exception {
                    String input = "25\n\nquit\n";
                    System.setIn(new ByteArrayInputStream(input.getBytes()));

                    SquareRootCalculator.main(new String[]{});

                    String output = outputStreamCaptor.toString();
                    assertThat(output).contains("Eingabe: 25");
                    assertThat(output).contains("Nachkommastellen: 10");
                    assertThat(output).contains("Ergebnis: 5.0000000000");
                }

                @Test
                @DisplayName("Should handle interactive mode with exit command")
                void testInteractiveModeWithExit() throws Exception {
                    String input = "exit\n";
                    System.setIn(new ByteArrayInputStream(input.getBytes()));

                    SquareRootCalculator.main(new String[]{});

                    String output = outputStreamCaptor.toString();
                    assertThat(output).contains("Auf Wiedersehen!");
                }

                @Test
                @DisplayName("Should handle interactive mode with invalid input")
                void testInteractiveModeInvalidInput() throws Exception {
                    String input = "invalid\n5\nquit\n";
                    System.setIn(new ByteArrayInputStream(input.getBytes()));

                    SquareRootCalculator.main(new String[]{});

                    String output = outputStreamCaptor.toString();
                    String errorOutput = errorStreamCaptor.toString();

                    assertThat(errorOutput).contains("Fehler:");
                    assertThat(output).contains("Auf Wiedersehen!");
                }

                @Test
                @DisplayName("Should show verification for small precision")
                void testVerificationOutput() throws Exception {
                    SquareRootCalculator.main(new String[]{"4", "5"});

                    String output = outputStreamCaptor.toString();
                    assertThat(output).contains("Verifikation (Quadrat):");
                }

                @Test
                @DisplayName("Should not show verification for high precision")
                void testNoVerificationForHighPrecision() throws Exception {
                    SquareRootCalculator.main(new String[]{"4", "25"});

                    String output = outputStreamCaptor.toString();
                    assertThat(output).doesNotContain("Verifikation (Quadrat):");
                }

                @ParameterizedTest
                @ValueSource(doubles = {0.05, 0.5, 5.0, 50.0})
                @DisplayName("Should handle different number ranges for initial guess")
                void testDifferentInitialGuessRanges(double input) throws Exception {
                    BigDecimal result = SquareRootCalculator.calculateSquareRoot(
                            BigDecimal.valueOf(input), 10);

                    assertThat(result).isNotNull();

                    // Verify accuracy by squaring
                    BigDecimal squared = result.multiply(result);
                    BigDecimal tolerance = new BigDecimal("0.0000000001");

                    assertThat(squared.subtract(BigDecimal.valueOf(input)).abs())
                            .isLessThanOrEqualTo(tolerance);
                }

                @Test
                @DisplayName("Should test exception constructors")
                void testExceptionConstructors() {
                    SquareRootCalculator.InvalidInputException invalidException =
                            new SquareRootCalculator.InvalidInputException(
                                    "Test message");
                    assertThat(invalidException.getMessage()).isEqualTo("Test message");

                    SquareRootCalculator.CalculationException calcException =
                            new SquareRootCalculator.CalculationException(
                                    "Calc error");
                    assertThat(calcException.getMessage()).isEqualTo("Calc error");
                }

                @Test
                @DisplayName("Should handle convergence edge cases")
                void testConvergenceEdgeCases() throws Exception {
                    // Test with a number that requires more iterations
                    BigDecimal result = SquareRootCalculator.calculateSquareRoot(
                            new BigDecimal("123456789012345678901234567890"), 20);

                    assertThat(result).isNotNull();
                    assertThat(result.scale()).isEqualTo(20);
                }

                @Test
                @DisplayName("Should handle precision boundary values")
                void testPrecisionBoundaryValues() throws Exception {
                    // Test with 0 decimal places
                    BigDecimal result = SquareRootCalculator.calculateSquareRoot(
                            new BigDecimal("4"), 0);
                    assertThat(result.scale()).isEqualTo(0);
                    assertThat(result.toString()).isEqualTo("2");

                    // Test with maximum precision
                    result = SquareRootCalculator.calculateSquareRoot(
                            new BigDecimal("4"), 1000);
                    assertThat(result.scale()).isEqualTo(1000);
                }

                // Helper class to simulate System.exit calls in tests
                private static class SystemExit extends RuntimeException {
                }
            }



Beispielhafte Verwendung von Claude Sonnet 4 Thinking
-------------------------------------------------------

.. story::

    .. rubric:: Zusammenfassung

    .. class:: incremental-list

    - Aufwand: ca. 30 Minuten (ca. 15 Minuten für den Prompt und ca. 15 Minuten für Claude Sonnet).
    - Die Generierung musste explizit terminiert werden, da der Agent beim Ausführen der Anwendung nicht in der Lage war diese korrekt zu bedienen und in einer Endlos-Schleife hing.

    - Der Code verwendet eigene Exception für Standarfälle. Dies ist ungeschickt.
    - Projektstruktur und Tests entsprechen den Erwartungen.
    - Die verwendeten Libraries waren auf den Ständen, die bei GPT nach der Aktualisierung verwendet wurden. D. h. nicht aktuell.
    - Es wurden noch einige weitere Dateien generiert, die nicht gefordert wurden: README.md zum Beispiel mit Lizenzinformationen...
    - Anwendung funktioniert und (viele) Tests auch - wenn man diese manuell ausführt.

      Achtung aufgrund des Testens der main Methode welche :java:`System.exit` Aufrufe hat kommt es zu Fehlern!

      Durch einen weiteren Prompt mit dem Fehler konnte dies behoben werden. Dabei wurde auch gleich ein Test fachlich korrigiert (Thinking Model). Leider wurde auch das Build-Skript abgeändert und (wieder) veraltete Versionen der Tools eingetragen.
    - Die Codeabdeckung ist gut, aber die meisten Sonderfälle werden nicht abgedeckt.
    - Das Build-Skript funktionierte erst nicht, da die Versionen nicht korrekt ermittelt wurden!
    - Der Code ist besser modularisiert in mehrere Methoden wodurch das Verständnis gefördert wird.
    - Es wurden Hilfsmethoden für anzunehmende gängige Fälle implementiert.
