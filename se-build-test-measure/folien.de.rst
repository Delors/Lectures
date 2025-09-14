.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "Testen", "Abdeckung", Metriken
    :description lang=de: Software Engineering - Testen und Metriken
    :id: lecture-se-testen-und-metriken
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!
    :svg-style:
        rect {
            fill: white;
            stroke: blue;
            stroke-width: 0.2;
        }
        text {
            fill: black;
            font-family: var(--theme-code-font-family);
        }
    :svg-defs:
        <marker
        id="arrow"
        viewBox="0 0 10 10"
        refX="10"
        refY="5"
        markerWidth="4"
        markerHeight="4"
        orient="auto-start-reverse">
            <path class="arrow-head" d="M 0 0 L 10 5 L 0 10 z" />
        </marker>


.. include:: ../docutils.defs


Software Engineering - Projekte bauen, Testen und Bewerten\ [#]_
===================================================================

Eine allererste Einführung

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.1.1

.. [#]

    Die Folien basieren in Teilen auf Folien von Dr. Helm und Prof. Dr. Hermann.

    Alle Fehler sind meine eigenen.

.. supplemental::

  :Folien:
      [HTML] |html-source|

      [PDF] |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-scale

Buildsysteme
------------


Buildsysteme (hier: sbt)
---------------------------


.. class:: incremental-list list-with-explanations

- Buildsysteme automatisieren repetitive Aufgaben
- Sie haben eingebaute Unterstützung oder Plugins für häufige Aufgaben:

  .. class:: incremental-list

  • Code zur einer ausführbaren Datei kompilieren: :console:`sbt compile`
  • Tests ausführen: :console:`sbt test`
  • Kompilierte Dateien entfernen: :console:`sbt clean`
  • Start eines interaktiven Interpreters: :console:`sbt console`
  • Code-Dokumentation als HTML oder PDF ausgeben: :console:`sbt doc`
  • Code formatieren, Code-Stil prüfen: :console:`sbt scalafmt`, :console:`sbt scalastyle`
  • ...
  • Ausführbare Datei (auf Server) publizieren: :console:`sbt publish`

- Buildsysteme werden mit Buildskripten konfiguriert
- Buildsysteme verarbeiten meist nur geänderte und davon abhängige Dateien

  (Inkrementalität)


.. supplemental::

    Heutzutage bringen fast alle Programmiersprachen eigene Buildsysteme mit oder es gibt etablierte Buildsysteme.

    :C/C++: **Make**, **CMake**
    :Java: **Maven**, Gradle, :obsolete:`Ant`, sbt
    :Scala: **sbt**
    :Rust: **Cargo**
    :...: ...



Minimale Anforderungen an ein Buildskript für Softwareprojekte
----------------------------------------------------------------

.. class:: incremental-list

1. **Kompilieren** des Codes nach einem frischen Update (:red:`bei Fehler Abbruch`)
2. **Testen** des Codes:

   .. class:: incremental-list

   1. **Unit-Tests** (:red:`bei Fehler Abbruch`)
   2. **Integrationstests** (:red:`bei Fehler Abbruch`)
   3. Systemtests/Abnahmetests (:red:`bei Fehler Abbruch`)
3. **Packaging** des Projekts (:red:`bei Fehler Abbruch`)
4. **Deployment** (typischerweise in einer Testumgebung)



Buildskripte für größere Softwareprojekte
----------------------------------------------------------------

Insbesondere bei größeren Projekten kommen häufig noch viele weitere Schritte hinzu:

- Code-Dokumentation erzeugen und veröffentlichen
- verschiedene statische Analysen durchführen, um Fehler zu finden
- zahlreiche Skripte um zum Beispiel Datenbanken zu aktualisieren, Docker-Container zu bauen und zu starten...
- ...



.. class:: new-section transition-fade

Testen von Software
-------------------



Validierung vs. Verifikation (V&V)
-----------------------------------

.. deck::

    .. card::

        :Validierung: „Bauen wir das richtige Produkt?“

        :Verifikation: „Bauen wir das Produkt korrekt?“

    .. card::

        .. image:: images/software_inspektionen.svg
                :align: right

        .. rubric:: Zwei komplementäre Ansätze die (V&V) unterscheiden:

        .. class:: list-with-explanations

        1. Software-Inspektionen oder Peer-Reviews (statische Technik)

           Software-Inspektionen können in allen Phasen des Prozesses durchgeführt werden.
        2. Software-Tests (dynamische Technik)


Software-Inspektionen überprüfen die Übereinstimmung zwischen einem Programm und seiner Spezifikation.
------------------------------------------------------------------------------------------------------------------------------

Ausgewählte Ansätze:

.. story::

    .. compound::

        .. rubric:: Programminspektionen

        Ziel ist es, Programmfehler, Verstöße gegen Standards und mangelhaften Code zu finden, und nicht, allgemeinere Designfragen zu berücksichtigen; sie werden in der Regel von einem Team durchgeführt, dessen Mitglieder den Code systematisch analysieren. Eine Inspektion wird in der Regel anhand von Checklisten durchgeführt.

        .. supplemental::

            Studien haben gezeigt, dass eine Inspektion von etwa 100LoC etwa einen Personentag an Aufwand erfordert.

    .. compound::
        :class: incremental

        .. rubric:: automatisierte Quellcodeanalyse

        Diese umfasst u. a. Kontrollflussanalysen, Datenverwendungs-/flussanalyse, Informationsflussanalyse und Pfadanalyse.

        Statische Analysen lenken die Aufmerksamkeit auf Anomalien.

    .. compound::
        :class: incremental

        .. rubric:: Formale Verifikation

        Die formale Verifizierung kann das Nichtvorhandensein bestimmter Fehler garantieren. So kann z. B. garantiert werden, dass ein Programm keine Deadlocks, Race Conditions oder Pufferüberläufe enthält.

    .. summary::
        :class: incremental

        Software-Inspektionen zeigen nicht, dass die Software nützlich ist.


ausgewählte Testziele
----------------------

- Test der Funktionalität
- Test auf Robustheit
- Test der Effizienz/Performance
- :peripheral:`Test auf Wartbarkeit`
- Test auf Nutzbarkeit

.. class:: no-title

Testarten
----------

.. grid:: height-100

    .. cell:: width-50 height-100 black-background white padding-1em

        **Black Box Testen**

        Wir wollen die Korrektheit zeigen.

        Testdaten werden durch die Untersuchung der Domäne gewonnen. Was sind gültige und was sind ungültige Eingabewerte in der Domäne?

        Der Test kann (und sollte!) ohne Betrachtung der konkreten Implementierung entwickelt werden.

    .. cell:: width-50 height-100 white-background black padding-1em

        **White Box Testen**

        Wie auch beim Black-Box Test wollen wir die Korrektheit zeigen.

        Testdaten werden durch die Inspektion des Programms gewonnen.

        Das heißt im Umkehrschluss, dass wir den Quellcode des Programms benötigen.



Der Umfang eines Tests ist die Sammlung der zu prüfenden Softwarekomponenten.
--------------------------------------------------------------------------------

.. class:: incremental-list

:Unit Tests (Modultest): Umfasst eine relativ kleine ausführbare Datei; z.B. ein einzelnes Objekt.
:Integrationstest: Komplettes (Teil-)System. Schnittstellen zwischen den Einheiten werden getestet, um zu zeigen, dass die Einheiten gemeinsam funktionsfähig sind.
:Systemtest: Eine vollständige integrierte Anwendung. Kategorisiert nach der Art der Konformität, die festgestellt werden soll: funktional, Leistung, Stress oder Belastung
:Abnahmetests: Tests (durch den Kunden), um zu zeigen, dass das System die Anforderungen erfüllt.



Testpläne
------------

Testpläne beschreiben, wie die Software getestet wird.

.. observation::
    :class: incremental

    Da die Anzahl der Tests praktisch unendlich ist, müssen wir (für praktische Zwecke) eine Annahme darüber treffen, wo Fehler wahrscheinlich zu finden sind; d. h. die Tests müssen auf einem Fehlermodell beruhen.

.. container:: incremental

    Es gibt zwei allgemeine Fehlermodelle und entsprechende Prüfstrategien:

    1. konformitätsorientiertes Testen
    2. fehlerorientiertes Testen



Entwicklung eines Testplans - Beispiel
---------------------------------------

.. deck::

    .. card::

        Entwickeln Sie einen Testplan für ein Programm, das ...

        1. drei ganzzahlige Werte liest,
        2. diese dann als die Länge der Seiten eines Dreiecks interpretiert
        3. danach ausgibt ob das Dreieck...

           - gleichschenklig,
           - schief oder
           - gleichseitig ist.

        .. hint::
            :class: incremental

            Ein gültiges Dreieck muss zwei Bedingungen erfüllen:

            - Keine Seite darf eine Länge von Null haben
            - Jede Seite muss kürzer sein als die Summe aller Seiten geteilt durch 2

    .. card::

        .. csv-table::
            :header: "Beschreibung", A, B, C, Erwartetes Ergebnis
            :widths: 20, 3, 3, 3, 8
            :class: incremental-table-rows

            Gültiges schiefes Dreieck, 5, 3, 4, Schief
            Gültiges gleichschenkliges Dreieck, 3, 3, 4, Gleichschenklig
            Gültiges gleichseitiges Dreieck, 3, 3, 3, Gleichseitig
            Erste Permutation von zwei gleichen Seiten, 50, 50, 25, Gleichschenklig
            (Permutationen des vorherigen Testfalls), ..., ..., ..., Gleichschenklig
            Eine Seite ist Null, 1000, 1000, 0, Ungültig
            Erste Permutation von zwei gleichen Seiten, 10, 5, 5, Ungültig
            Zweite Permutation von zwei gleichen Seiten, 5, 10, 5, Ungültig
            Dritte Permutation von zwei gleichen Seiten, 5, 5, 10, Ungültig
            "Drei Seiten größer als Null, Summe der zwei Kleinsten ist kleiner als die Größte", 8, 5, 2, Ungültig
            Drei Seiten mit maximaler Länge, MAX, MAX, MAX, Gleichseitig
            Zwei Seiten mit maximaler Länge, MAX, MAX, 1, Gleichschenklig
            Eine Seite mit maximaler Länge, 1, 1, MAX, Ungültig

        .. supplemental::

            Die Testfälle sind noch nicht vollständig (zum Beispiel wenn A, B und C alle 0 sind; oder wenn eine Seite die Länge der beiden anderen Seiten addiert hat). Tests zum Beispiel in Hinblick auf objektorientierte Struktur, Fehlerbehandlung, etc. ... fehlen.



(Test-)Überdeckung (eng.  (Test-)Coverage)
-------------------------------------------

.. question::

    Wie wissen wir aber wie “gut” unsere Tests sind?

.. definition::
    :class: incremental

    Testüberdeckung beschreibt, wie viel des Programms durch die Tests geprüft wird.

.. container:: incremental

    Hierbei gibt es verschiedene Überdeckungskriterien, die verschiedene Metriken für die Beschreibung der Testgüte nutzen.



Anweisungsüberdeckung (:eng:`Statement Coverage`)
---------------------------------------------------

Alternativ auch Zeilenüberdeckung oder :eng:`Line Coverage` genannt.

.. grid::

    .. cell::

        Zu testende Software:

        .. code:: java
            :number-lines:

            public double compute (boolean includeTax) {
                double result = 1.3;
                if (includeTax) {
                    result *= 1.19;
                }
                return result;
            }

    .. cell:: incremental

        Testfälle:

        .. code:: java
            :number-lines:

            compute(false);
            compute(true);


        .. question::
            :class: incremental

            Wie hoch ist die Anweisungsüberdeckung? Sind beide Testfälle notwendig?

            .. presenter-note::

                Die Anweisungsüberdeckung beträgt 100%; der erste Testfall ist nicht notwendig.

.. definition::
    :class: margin-top-1em incremental

    .. math::

        \text{Anweisungsüberdeckung} = \frac{\text{\# ausgeführte Anweisungen}}{\text{\# aller Anweisungen}} \cdot 100\%



Zweigüberdeckung (:eng:`Branch Coverage`)
---------------------------------------------------

.. grid::

    .. cell::

        Zu testende Software:

        .. code:: java
            :number-lines:

            public double compute (boolean includeTax) {
                double result = 1.3;
                if (includeTax) {
                    result *= 1.19;
                }
                return result;
            }

    .. cell:: incremental

        Kontrollflussgraph:

        .. raw:: html

            <div style="width: 30ch; height: 7lh; container-type:size;">
            <svg viewBox="0 0 30 13" font-size="1.75" version="1.1" xmlns="http://www.w3.org/2000/svg">

                <rect width="21" height="4.25" x="1" y="0.15" rx="1" ry="1" />
                <text class="code" x="2.5" y="1.75">
                    <tspan class="keyword type">double</tspan>
                    <tspan class="name">result</tspan>
                    <tspan class="operator">=</tspan>
                    <tspan class="literal number">1.3</tspan>
                </text>
                <text class="code" x="2.5" y="3.75">
                    <tspan class="keyword">if</tspan>
                    <tspan class="whitespace"> </tspan>
                    <tspan class="punctuation">(</tspan>
                    <tspan class="name">includeTax</tspan>
                    <tspan class="punctuation">)</tspan>
                </text>

                <g class="incremental">
                <line x1="14" y1="4.4" x2="14" y2="6.15" style="stroke:blue;stroke-width:0.2" marker-end="url(#arrow)"/>
                <rect width="16.5" height="2" x="6" y="6.15" rx="1" ry="1" />
                <text class="code" x="7.5" y="7.75">
                    <tspan class="name">result</tspan>
                    <tspan class="whitespace"> </tspan>
                    <tspan class="operator">*=</tspan>
                    <tspan class="whitespace">
                    </tspan>
                    <tspan class="literal number float">1.19</tspan>
                </text>
                </g>

                <g class="incremental">
                <line x1="4" y1="4.4" x2="4" y2="10.15" style="stroke:blue;stroke-width:0.2" marker-end="url(#arrow)"/>
                <line x1="14" y1="8.15" x2="14" y2="10.15" style="stroke:blue;stroke-width:0.2" marker-end="url(#arrow)"/>
                <rect width="15" height="2" x="1" y="10.15" rx="1" ry="1" />
                <text class="code" x="2.5" y="11.75">
                    <tspan class="keyword">return</tspan>
                    <tspan class="whitespace"> </tspan>
                    <tspan class="name">result</tspan>
                </text>
                </g>
            </svg>
            </div>

.. deck:: incremental

    .. card::

        .. question::

            Wie hoch ist die Anweisungsüberdeckung? Sind beide Testfälle notwendig?

            .. presenter-note::

                Die Anweisungsüberdeckung beträgt 100%; der erste Testfall ist nicht notwendig.

    .. card::

        .. definition::

            .. math::

                \text{Zweigüberdeckung} = \frac{\text{\# ausgeführte Zweige}}{\text{\# aller Zweige}} \cdot 100\%



Pfadüberdeckung (:eng:`Path Coverage`)
---------------------------------------------------

.. deck::

    .. card::

        .. definition::

            .. math::

                \text{Pfadüberdeckung} = \frac{\text{\# ausgeführten Pfade}}{\text{\# aller (möglichen) Pfade}} \cdot 100\%

    .. card::

        .. grid::

            .. cell::

                Zu testende Software:

                .. code:: java
                    :number-lines:

                    public double compute (boolean includeTax,
                                           boolean reducedTax,
                                           double discount) {
                      double result = 1.3;
                      if (includeTax) {
                        if (reducedTax) {
                          result *= 1.07;
                        else {
                          result *= 1.19;
                      } }
                      if (discount > 0.0) {
                        result *= (1.0 - discount);
                      }
                      return result;
                    }


            .. cell:: incremental

                Testfälle:

                .. code:: java
                    :number-lines:

                    compute(false, false, 0.0);
                    compute(true, false, 0.0);
                    compute(true, true, 0.0);
                    compute(false, false, 0.1);
                    compute(true, false, 0.1);
                    compute(true, true, 0.1);

                .. question::
                    :class: incremental

                    Wie hoch ist die Pfadüberdeckung?

                    .. presenter-note::

                        Die Überdeckung beträgt 100%.

    .. card:: center-content

        .. attention::

            Die Pfadüberdeckung ist in der Praxis oft nicht realisierbar, da die Anzahl der möglichen Pfade exponentiell mit der Anzahl der Verzweigungen wächst.

            In der Praxis wird daher oft die Zweigüberdeckung als Kompromiss genutzt.



Weitere Überdeckungskriterien
------------------------------

.. class:: incremental-list list-with-explanations

- (einfache) Bedingungsüberdeckung (:eng:`(Simple) Condition Coverage`)
- Eingangs-/Ausgangsüberdeckung (:eng:`Entry/Exit Coverage`)
- Schleifenüberdeckung (:eng:`Loop Coverage`)
- Zustandsüberdeckung (:eng:`State Coverage`)

  (Erfodert ggf. das ein endlicher Automat modelliert wird.)
- Datenflussüberdeckung (:eng:`Data Flow Coverage`)



Überdeckungsziele
------------------

.. deck::

    .. card::

        :IEEE 29119 “Software Testing”:
            100% Anweisungsabdeckung

            100% Zweigabdeckung für kritische Module
        :DO-178B “Software Considerations in Airborne Systems and Equipment Certification”:

            Abhängig von der Auswirkung von Systemfehlern.
            Beispiel: 100% Anweisungsabdeckung bei Verletzungsgefahr von Passagieren.
        :IEC 61508 “Functional Safety of Electrical/Electronic/Programmable Electronic Safety-Related Systems”:
            100% Anweisungs-/Zweig-/Bedingungsabdeckung je nach Sicherheitsanforderung
        :ISO 26262 “Road vehicles - Functional safety”:
            Abhängig von der Kritikalität der Komponente


    .. card:: s-overlay center-content height-100

        .. summary::
            :class: backdrop-blur

            Das Erreichen eines Überdeckungsziels erfordert Aufwand, der durch die Kritikalität möglicher Fehler motiviert sein muss.

            In vielen Anwendersystemen ist eine hundertprozentige Testabdeckung nicht notwendig. Testabdeckung ist dennoch zu messen.



.. class:: transition-flip no-title center-content

Grenzen des Testens
-------------------

.. epigraph::

    Tests können nur das Vorhandensein von Fehlern zeigen, nicht deren Abwesenheit.

    -- E. Dijkstra



JUnit Test Case - Beispiel
--------------------------

.. code:: java
    :number-lines:

    import org.junit.Test;
    import static org.junit.Assert.assertEquals;
    import static org.junit.Assert.fail;

    import java.util.Arrays;

    public class SimpleCalculatorTest {

      @Test                                                // <= JUnit Test Annotation
      public void testProcess() {

        String[] term = new String[] {
          "4", "5", "+", "7", "*"
        };
        long result = SimpleCalculator.process(term);
          assertEquals(Arrays.toString(term), 63, result); // <= JUnit Assertion
      }
    }



TestNG - Beispiel
-----------------

.. code:: java
    :number-lines:

    // This method will provide data to any test method
    // that declares that its Data Provider is named "provider1".
    @DataProvider(name = "provider1")
    public Object[][] createData1() {
        return new Object[][] {
            { "Cedric", new Integer(36) },
            { "Anne", new Integer(37) }
        };
    }

    // This test method declares that its data should be
    // supplied by the Data Provider named "provider1".
    @Test(dataProvider = "provider1")
    public void verifyData1(String n1, Integer n2) {
        System.out.println(n1 + " " + n2);
    }


Behavior-Driven Development
-------------------------------

Das Ziel ist, dass die Entwickler die Verhaltensabsichten des Systems, das sie entwickeln, definieren.\ [#]_ Hier mit Hilfe von ScalaTest.

.. code:: scala
    :number-lines:

    import org.specs.runner._
    import org.specs._

    object SimpleCalculatorSpec extends Specification {

        "The Simple Calculator" should {
            "return the value 36 for the input {“6”,“6”,“*”}" in {
                SimpleCalculator.process(Array("6","6","*")) must_== 36
            }
        }
    }


.. [#] http://behaviour-driven.org/



The Last Word
--------------

.. epigraph::

    **A Tester’s Courage**

    The Director of a software company proudly announced that a flight software developed by the company was installed in an airplane and the airline was offering free first flights to the members of the company. “Who are interested?” the Director asked. Nobody came forward. Finally, one person volunteered. The brave Software Tester stated, ‘I will do it. I know that the airplane will not be able to take off.’

    -- Unknown Author @ http://www.softwaretestingfundamentals.com


.. class:: exercises

Übung
-------

.. scrollable::

    .. exercise::

        Entwickeln Sie einen Testplan für das folgende Programm:

        .. include:: code/RPN.java
            :code: java
            :number-lines:
            :class: copy-to-clipboard

        .. solution::
            :pwd: Uf!

            Ihr Testplan sollte alle Standardtestfälle abdecken. Zum Beispiel:
            - einfache und komplexe Ausdrücke mit allen möglichen Operatoren
            - Ausdrücke mit unterschiedlichen Operanden (negative Zahlen, Dezimalzahlen, sehr große Zahlen)
            - Ausdrücke mit Sonderfällen (z. B. Division durch Null)
            - Ausdrücke mit :java:`NaN` als Wert.

            Ihr Testplan sollte weiterhin alle möglichen Fehlerfälle abdecken. Zum Beispiel:

            - :java:`main` ohne Parameter
            - wir haben zu viele Operanden bzw. zu wenige Operatoren
            - wir haben zu wenige Operanden bzw. wir haben zu viele Operatoren
            - wir haben einen unbekannten Operator
            - wir haben sehr große Operanden (d. h. die Berechnung führt zu einem Überlauf)
            - ...



.. class:: transition-move-to-top new-section

Metriken
----------



Qualitätsmetriken
-------------------------

.. deck::

    .. card::

        .. rubric:: Warum?

        .. class:: incremental-list

        - Neue Features oder Code-Qualität erhöhen?
        - Kann ich Komponente C austauschen? Bzw. wie viel Aufwand ist das?
        - Haben die letzten Änderungen das System negativ beeinflusst?
        - Welche Systemteile sind besonders sicherheitskritisch?

    .. card::

        .. rubric:: Ziele

        .. class:: incremental-list

        - Mögliche Bugs frühzeitig erkennen
        - Systeme quantitativ miteinander vergleichen
        - Wartbarkeit einschätzen
        - Refactoring planen
        - Änderungen bewerten
        - Evolution des Systems überwachen bzw. verstehen

    .. card::

        .. rubric:: Verwendung

        .. class:: incremental-list

        - Als *Quality Gates* in Buildsystemen

          z. B. Zeilen pro Methode < 50
        - Zur Bewertung von Software

    .. card::

        .. rubric:: Einfache Metriken

        .. class:: incremental-list

        :Lines of Code (LOC): Alle Zeilen zählen so, wie sie im Quellcode stehen
        :Source Lines of Code (SLOC): Alle Zeilen ohne Leerzeile oder Kommentare
        :Comment Lines of Code (CLOC): Es zählen nur Zeilen mit Kommentaren; dies können ganze Zeilen sein, oder einfach nur Inline Kommentare; auskommentierter Code zählt ggf. auch
        :Non-Comment Lines of Code (NCLOC) / Effective Lines of Code (ELOC): Codezeilen ohne Kommentarzeile oder Zeilen, die nur Klammern enthalten, reine Import Statements oder Methodendeklarationen
        :Logical Lines of Code (LLOC): Zählt nur Anweisungen

    .. card::

        .. rubric:: Fortgeschrittene Metriken

        .. class:: incremental-list

        :Zyklomatische Komplexität (McCabe): Anzahl der linear unabhängigen Pfade durch den Code
        :Kopplung: Anzahl der Abhängigkeiten zwischen Komponenten
        :...: ...



.. class:: no-title center-content

Es gibt keine fixen Werten
----------------------------

.. warning::

    Umfangreiche Forschung hat gezeigt, dass es keine fixen Werte gibt, die für alle Projekte gelten. Es hat sich weiterhin gezeigt, dass es keine einzige Metrik gibt, die alleine zur Bewertung der Qualität eines Systems ausreicht. Welche Metriken die Qualität des Systems am besten beschreiben, lässt sich immer nur posthum beantworten.

    Metriken sind immer kontextabhängig zu bewerten.

    Metriken eignen sich insbesondere, um Veränderungen zu bewerten und um die Entwicklung von Software zu überwachen.



.. class:: transition-fade new-section

Softwarequalitätssicherung
----------------------------

.. container:: section-subtitle

    Konstruktiv vs. Analytisch



Mechanismen der konstruktiven Softwarequalitätssicherung
------------------------------------------------------------

.. class:: incremental-list

- Programmiersprachen (mit Typsystemen)
- Softwareentwicklungsprozesse
- Domain Specific Languages (DSLs)



Ansätze der analytischen Softwarequalitätssicherung
------------------------------------------------------------

.. csv-table::
    :header: " ", "Wiederverwend-
	barkeit", Wartbarkeit, Korrektheit, Aufwand
    :class: incremental-table-rows

    leichtgewichtige statische Analysen, , ✓, ✓ , ↓-○
    Semiformale Methoden, , , ✓, ↓
    formale Methoden, , , :green:`✓`, ↑
    Strukturanalysen, ✓, ✓, , ↓
    "Stilüberprüfungen

    (:eng:`Linting`)", , :green:`✓` , , ↓

.. supplemental::

    Leichtgewichtige statische Analysen können zum Beispiel Code-Clone erkennen (Maintenance), oder Verletzungen von empfohlenen Vorgehensweisen identifizieren und auf gängige Fehlermuster (:eng:`Bug Patterns`) hinweisen.



.. class:: exercises

Übung
---------

.. story::

    .. attention::
        :class: incremental

        Um einen Lernerfolg zu erhalten, sollten Sie die Schritte unten selber durchführen und nicht die Aufgabenstellung als solches direkt an den KI Assistenten Ihrer Wahl übergeben!

    .. remark::
        :class: incremental

        Für die folgenden Aufgabe sind keine Kenntnisse von HTML, JavaScript oder CSS notwendig. Wenn es Probleme gibt, dann beschreiben Sie das Problem der KI und lassen sich helfen!

    .. hint::
        :class: incremental

        Es kann sehr hilfreich sein, den Code häufig in ein Repository einzuchecken, da sie bei Problemen somit immer auf einen vorherigen Stand zurückkehren können.


    .. exercise:: Eine eigene Webanwendung bauen mittels Vibe-Coding

        .. container:: incremental

            Entwickeln Sie mittels Vibe-Coding eine einfache, aber vollständige Webanwendung, die nur Standard JavaScript, CSS und HTML verwendet. Es sollen keine Frameworks oder externe Bibliotheken für die Ausführung der Anwendung notwendig sein.

        .. supplemental::

            Ein Beispiel für eine solche Anwendung wäre TicTacToe oder Stein-Schere-Papier, welches Sie gegen den Computer spielen. Sie können selbstverständlich auch ein anderes kleines Spiel wählen, dass keine komplexe Logik benötigt.


        .. class:: incremental-list

        1. Erstellen Sie mit Hilfe eines *eigenem* Prompts die Anwendung. Dieser Prompt sollte die Anwendung beschreiben und die zu verwendenden Technologien. Fordern Sie ggf. explizit, dass der Code und das Styling in getrennten Dateien erfolgen soll, falls nur eine Datei (``index.html``?) zu finden ist.

        2. Lassen Sie sich ein **NPM Build-Skript** generieren, dass die nachfolgenden Schritte durchführt. D.h. es soll eine ``package.json`` Datei erzeugt werden, die *alle* notwendigen Abhängigkeiten und Skripte definiert. Es muss insbesondere möglich sein: ``npm run build`` und ``npm run clean`` auszuführen.

           1. Formatierung aller Dateien (.js, .html, .css)
           2. Linting aller Dateien (.js, .html, .css)

           Validieren Sie, dass Ihr Build-Skript auch die neuesten Versionen der eingebundenen Tools verwendet. *Dies erfordert in der Regel eine Google-Suche nach den aktuellen Versionen.*

           .. supplemental::

              Wenn Sie NodeJs installieren, dann steht Ihnen auch NPM zur Verfügung.

              .. hint::

                KI Codegeneratoren referenzieren üblicherweise veraltete Tools und Bibliotheken. Ggf. müssen Sie danach auch die Konfigurationsskripte anpassen lassen.

        3. Führen Sie Ihr Build-Skript aus und beheben Sie alle gefundenen Probleme.
        4. Finden Sie heraus, wie Sie Ihre Anwendung für den Einsatz optimieren könn(t)en und erweitern Sie das Build-Skript entsprechend.

           Ein Ziel könnte die Minimierung des Ressourcenverbrauchs sein.
        5. Lassen Sie sich Testfälle generieren und führen Sie diese als Teil des Builds aus.

           Beheben Sie ggf. gefundene Probleme.
        6. Hinterfragen Sie den generierten, lauffähigen Code. Lassen Sie sich ggf. von der KI beim Verständnis helfen und stellen Sie kritische Fragen.

        7. [Bonus] Lassen Sie sich die Testabdeckung (Coverage) des JavaScript Codes ermitteln und versuchen Sie eine möglichst hohe Abdeckung zu erreichen.

        .. solution::
            :pwd: TicTacToe!

            .. rubric:: Exemplarisches Vorgehen

            Im Folgenden wird kurz beschrieben wie vorgegangen werden könnte, um ein vollständiges TicTacToe-Projekt zu erstellen. *In diesem Fall wurde Copilot und GPT-4.1 verwendet, um den Code zu generieren. (August 2025)*

            .. rubric:: Prompts für die Webanwendung

            .. rubric:: 1. Prompt

            ::

                Please create a small webpage to play TicTacToe against a computer. You should only use plain HTML, CSS and JavaScript.

            .. rubric:: 2. Prompt

            ::

                I need an npm build script which performs code formatting and linting for all files. The file should define all dependencies and scripts.

            .. rubric:: 3. Prompt

            ::

                Please, create some example configuration files for ESLint and Prettier and HtmlHint with sensible defaults.

            .. rubric:: 4. Prompt

            Das Ausführen des Skripts mittels ``npm run build`` deckte einige Probleme auf, die zu beheben waren.

            ::

                When I run the build script, it detects invalid HTML in the index.html: "Opening div not terminated". Please, fix it.

            .. rubric:: 5. Prompt

            ::

                ESLint is complaining that I should use an ``eslint.config`` file instead of an ``.eslintrc`` file. Please, update the file.

            .. rubric:: 6. Prompt

            An dieser Stelle zeigte sich, dass der Code Inkonsistenzen aufwies.

            ::

                OK - I want to use ECMAScript Modules and not CommonJS. Please, make the necessary changes?

            .. rubric:: 7. Prompt

            Danach gab es noch Probleme mit dem CSS Linting.

            ::

                ``stylelint`` complains that it misses the ``stylelint-config-standard`` file. Please, fix this bug and don't forget to update my build script.

            .. rubric:: 8. Prompt

            Eine detailliertere Analyse des Code ergab, dass ein paar Styles nicht verwendet wurden. Diesbezüglich erstellte Prompts haben diese Probleme gelöst.

            Ein Prompt bzgl. der Optimierung des Codes ergab, dass der JavaScript und CSS Code kompaktiert werden kann und dies mittels des Tools **vite** möglich ist.

            ::

                Please, update my build script to use ``vite`` to bundle and minify my JavaScript and CSS code.

            .. rubric:: 9. Prompt

            Ein Prompt bzgl. Tools für **End-to-End-Tests** ergab, dass Playwright eine geeignete Wahl ist (Cypress wäre eine Alternative, ist aber ggf. kommerziell).

            ::

                I need some end-to-end tests for my application. Please, create them using Playwright. Also update the build script to run all tests when the application is build.
