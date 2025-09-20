.. meta::
    :lang: de
    :author: Michael Eichberg
    :keywords: "Programmierung", "Java", "Domain Modeling", "Records"
    :description lang=de: Java Generics
    :id: lecture-prog-java-domain-modeling-and-records
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Domain Modeling sowie Java Records und Enums
===========================================================

Eine kurze Einführung, um das Entwickeln von kleinen Projekten zu erleichtern.

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0.1

.. supplemental::

    :Folien:

        |html-source|

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues

    :Kontrollfragen:

        .. source:: kontrollfragen.de.rst
            :path: relative
            :prefix: https://delors.github.io/
            :suffix: .html



.. class:: new-section

Implementierung einer einfachen Domänenklasse
------------------------------------------------

.. container:: peripheral

    Wiederholung etablierter Konzepte



.. class:: exercises

Übung
------------------------------------------------

.. scrollable::

    .. exercise:: Implementieren einer Klasse für einfache Telefonbucheinträge

        1. Entwickeln Sie eine Klasse :java:`TelefonbuchEintrag` mit den Attributen:

        1. :java:`Integer telefonnummer`
        2. :java:`String vorname`
        3. :java:`String nachname`

        sowie geeigneten Konstruktoren und allen passenden get- und set-Methoden. Setzen Sie *Encapsulation* um.

        2. Implementieren Sie weiterhin eine Methode :java:`public String toString()`, die den Eintrag in der Form "Vorname Nachname, Telefonnummer" zurückgibt.

        3. Implementieren Sie eine Methode :java:`public boolean equals(Object obj)`, die zwei Einträge (d. h. zwei Objekte mit dem dynamischen Typ :java:`TelefonbuchEintrag`) als gleich betrachtet, wenn die Telefonnummern gleich sind.

           Prüfen Sie - durch das Studium der Dokumentation der Methode :java:`java.lang.Object.equals()` - ob Sie die Methode korrekt implementiert haben.

        4. Implementieren Sie eine Methode :java:`public int hashCode()`, die einen :java:`int` Wert zurückgibt und einen Eintrag halbwegs sinnvoll repräsentiert. Prüfen Sie ob Ihre Implementierung dem Kontrakt der Methode :java:`java.lang.Object.hashCode()` entspricht.

        5. Schreiben Sie ein Methode, die drei :java:`TelefonbuchEintrag`-Objekte erzeugt. Zwei davon sollen die gleichen Inhalte haben. Prüfen Sie dann, ob die Methoden :java:`equals()` und :java:`hashCode()` korrekt implementiert sind.

        .. solution::
            :pwd: VielZuTun!

            .. include:: code/TelefonbuchEintrag.java
                :code: java
                :number-lines:



.. class:: new-section transition-fade

(Domain) Modeling [Larman2001]_
------------------------------------------------


Was ist Domain Modeling?
------------------------------------------------

:Warum: Die Domänenmodellierung hilft uns, die relevanten Konzepte und Ideen einer Domäne zu identifizieren.
:Wann: Immer dann, wenn wir die (weiteren) Konzepte in einem Bereich verstehen müssen.
:Beteiligte: Entwickler, Domänenexperten(:peripheral:`, Anwender`)
:Leitlinie: Erstellen Sie nur für die anstehenden Aufgaben ein Domänenmodell.

.. supplemental::

    Domain Model ≘ :ger:`Analysemodell oder  auch Konzeptmodell`



.. class:: no-title center-content

Curtis' Gesetz
------------------------------------------------

.. epigraph::

    Curtis’law: […] Good designs require deep application knowledge.\ [#]_

    -- [EndresRombach2003]_

.. [#] Sinngemäß: “Ein guter Entwurf benötigt ein tiefgreifendes Verständnis des Einsatzgebiets der zu entwickelnden Software.”



Das Domänenmodell
------------------------------------------------

.. class:: incremental-list list-with-explanations

- Das Domänenmodell wird erstellt, um die Domäne in Konzepte oder Objekte in der realen Welt aufzuschlüsseln.
- Das Modell sollte die Menge der konzeptionellen Klassen identifizieren.

  (Das Domänenmodell wird iterativ vervollständigt.)
- *Es ist die Grundlage für den Entwurf der Software*.



Erstellen eines Domänenmodells
------------------------------------------------

.. question::

    Was sind die relevanten Konzepte/Objekte der Domäne?


.. container:: incremental

    .. rubric:: Vorgehen

    .. class:: incremental-list list-with-explanations

    1. Identifizieren Sie die relevanten Konzepte/Objekte.

       (Dies kann zum Beispiel durch das Studieren von existierenden Modellen passieren\ [Fowler1997]_ oder durch die Analyse von fachlichen Dokumenten, die die Domäne beschreiben.)
    2. Identifizieren Sie die Attribute der Konzepte/Objekte.
    3. Identifizieren Sie die Beziehungen zwischen den Konzepten/Objekten.
    4. Erstellen Sie ein Klassendiagramm.


.. important::
    :class: incremental

    Verwenden Sie das Vokabular der Domäne; z. B. sollte ein Modell für eine Bibliothek Namen wie „Ausleiher“ anstelle von „Kunde“ verwenden.


Modellierungsaspekte
------------------------------------------------

.. story::

    .. class:: dhbw incremental-list

    1. \

       .. question::

            Wann sollte ich etwas als Attribut oder als Klasse modellieren?

       .. answer::
            :class: incremental

            *Faustregel*: Wenn wir uns ein Konzept X in der realen Welt nicht als Zahl, Datum oder Text vorstellen können, dann sollte X wahrscheinlich mit Hilfe einer Klasse modelliert werden und ist kein Attribut.

    2. \

       Die Attribute in einem Domänenmodell sollten vorzugsweise „primitive“ Datentypen in Bezug auf die Domäne sein.

       Sehr häufige Datentypen sind: Booleans, Datum, Zahl, Zeichen, String, Adresse, Farbe, Telefonnummer,...

    3. \

       Erwägen Sie die Modellierung von Mengen als Klassen, um Einheiten zuordnen zu können.

       .. example::

          Der Datentyp des Attributs „Betrag“ einer Zahlung sollte auch die Währung angeben.



.. class:: exercises

Übung
------------------------------------------------

.. exercise:: Domänenmodell für ein Kassensystem

    Erstellen Sie ein Domänenmodell (d. h. ein UML Klassendiagramm) für ein einfaches Kassensystem basierend auf der folgenden Beschreibung und Ihrem Domänenwissen:

        Verkauf abwickeln: Ein Kunde kommt an der Kasse an und möchte einen Artikel kaufen. Der Kassierer verwendet das Kassensystem, um jeden Artikel zu erfassen. Das System zeigt eine laufende Summe und Details zu den einzelnen Positionen an. Der Kunde gibt die Zahlungsinformationen ein, die das System prüft und aufzeichnet. Das System aktualisiert den Warenbestand. Der Kunde erhält eine Quittung vom System und verlässt dann das Geschäft mit den Artikeln.

    .. hint::

        Denken Sie daran, dass wir im Domänenmodell nur die relevanten Konzepte modellieren sollten; d. h. Klassen und deren Attribute und Beziehungen. Methoden sind hier nicht relevant.

    .. solution::
        :pwd: UMLhier-UMLda:

        Eine mögliche Modellierung eines Kassensystems könnte wie folgt aussehen:

        .. image:: images/kassensystem.svg
            :alt: Kassensystem




.. class:: new-section

Java records\ [JEP395]_
------------------------------------------------


Java Records - Überblick
------------------------------------------------

- Java Records sind eine spezielle Form von Klassen, die dazu dienen, unveränderliche Daten zu modellieren.
- Java Records sind häufig hervorragend geeignet, um Klassen aus Domänenmodellen, die insbesondere der Datenhaltung dienen, zu modellieren.
- Java Records sind seit Java 16 verfügbar.



Beispiel: Implementierung einer Klasse 2DPoint
------------------------------------------------

.. deck::

    .. card::

        .. rubric:: Traditioneller Ansatz

        .. grid::

            .. cell:: width-50

                .. code:: java
                    :number-lines:
                    :class: copy-to-clipboard

                    class Point {
                        private final int x;
                        private final int y;

                        Point(int x, int y) {
                            this.x = x; this.y = y;
                        }

                        int x() { return x; }
                        int y() { return y; }

                        public boolean equals(Object o) {
                            if (!(o instanceof Point)) return false;
                            Point other = (Point) o;
                            return other.x == x && other.y == y;
                        }

            .. cell:: width-50 incremental

                .. code:: java
                    :number-lines: 17
                    :class: copy-to-clipboard

                        public int hashCode() {
                            return Objects.hash(x, y);
                        }

                        public String toString() {
                            return String.format(
                                "Point[x=%d, y=%d]", x, y
                            );
                        }
                    }

    .. card::

        .. rubric:: Implementation mit Java :java:`record`

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            record Point(int x, int y) {}



Verwendung von Java Records
------------------------------------------------

.. code:: java
    :number-lines:

    jshell> record Point(int x, int y) {}
    |  created record Point

    jshell> var p = new Point(1,2);
    p ==> Point[x=1, y=2]

.. deck::

    .. card::

        .. rubric:: Deklaration und Initialisierung

        .. code:: java
            :number-lines:

            jshell> var x = p.x();var y = p.y()
            x ==> 1
            y ==> 2

            jshell> System.out.println(p.toString() + " #" + p.hashCode() )
            Point[x=1, y=2] #33

    .. card::

        .. rubric:: Verwendung

        .. code:: java
            :number-lines:

            jshell> new Point(1,2).hashCode();
            33

            jshell> new Point(1,2) == p
            false

            jshell> new Point(1,2).equals(p);
            true

.. supplemental::

    Die Getter und Setter heissen bei Records *Component Methods*. Ein direkter Zugriff auf die Attribute ist nicht möglich:

    .. code:: java
        :number-lines:

        jshell> p.x
        |  Error:
        |  x has private access in Point
        |  p.x
        |  ^-^



Java Records - Technische Besonderheiten
------------------------------------------------

- Java Records erben immer von :java:`java.lang.Record`.
- Die Klasse ist (implizit) :java:`final` (und notwendigerweise nicht abstrakt).
- Die Felder, die die Komponenten eines Records sind, sind :java:`final` und :java:`private`.
- Ein Record kann keinen weiteren (veränderlichen) Zustand haben.
- Verschachtelte/Lokale Records sind möglich sind jedoch immer :java:`static`.
- Records sind *serializable*.

- Sie haben eine *equals()*- und *hashCode()*-Methode, die auf allen Attributen basiert.
- Sie haben eine *toString()*-Methode, die alle Attribute ausgibt.
- Sie haben *getter*-Methoden für alle Attribute.
- Sie haben einen Konstruktor, der alle Attribute initialisiert.
- Sie können *static* und *non-static* Methoden haben.
- Sie können *interfaces* implementieren.
- Sie können *annotations* haben.


Konstruktoren von Record Klassen
------------------------------------------------

.. story::

    .. class:: list-with-explanations

    - Ein Record hat immer einen kanonischen Konstruktor, der alle Attribute initialisiert.

    (Ein Record hat nie einen parameterlosen Standardkonstruktor.)

    .. class:: incremental-list

    - Ein Record kann weitere Konstruktoren haben, die jedoch den kanonischen Konstruktor aufrufen müssen.

      .. example::
        :class: margin-top-1em

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard


            record Point(int x, int y) {
                public Point(int x) { this(x,x); }
            }


    - Es ist möglich einen kompakten kanonischen Konstruktor zu definieren. Der Code zur Initialisierung der Attribute (z. B. :java:`this.x = x;`) wird dann implizit am Ende generiert.

      .. example::
        :class: margin-top-1em

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            record Point(int x, int y) {
                public Point {
                    if (x < 0 || y < 0)
                        throw new IllegalArgumentException(
                            "Negative Koordinaten sind nicht erlaubt.");
                }
            }

    - \

      .. container:: accentuate

        Der primäre Zweck von zusätzlichen Konstruktoren ist es, die Validierung oder Normalisierung der Attribute zu ermöglichen.



.. class:: exercises

Übung
------------------------------------------------


.. exercise:: Ein einfacher TelefonbuchEintrag mit Java Records

    Entwickeln Sie eine Klasse TelefonbuchEintrag mit den Attributen:

       1. :java:`int telefonnummer`
       2. :java:`String vorname`
       3. :java:`String nachname`

    verwenden Sie dazu ein Java Record. Führen Sie ggf. eine Normalisierung der Attribute durch (löschen von Leerzeichen am Anfang und Ende). Validieren Sie die übergebenen Werte (Telefonummer muss (hier) größer 0 sein und die Namen müssen mind. einen Buchstaben enthalten. Instanziieren Sie drei Objekte und prüfen Sie, ob die Validierung korrekt funktioniert und die Vergleichbarkeit der Objekte korrekt implementiert ist.

    .. solution::
        :pwd: NixVielZuTun!

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            public record TelefonbuchEintrag(int telefonnummer, String vorname, String nachname) {
                public TelefonbuchEintrag {
                    if (telefonnummer <= 0)
                        throw new IllegalArgumentException("Telefonnummer muss größer 0 sein.");
                    if (vorname == null || nachname == null)
                        throw new IllegalArgumentException("Telefonnummer, Vorname und Nachname müssen gesetzt sein.");

                    vorname = vorname.trim();
                    nachname = nachname.trim();

                    if (vorname.isEmpty() || nachname.isEmpty())
                        throw new IllegalArgumentException("Vorname und Nachname müssen mind. einen Buchstaben enthalten.");
                }
            }


.. class:: new-section transition-fade


Aufzählungen
------------------------------------------------


Modellierung von Aufzählungen mit Java Enums
----------------------------------------------------------

.. deck::

    .. card::

        Eine Enum-Deklaration spezifiziert eine neue Enum-Klasse, eine eingeschränkte Art von Klasse, die eine kleine Menge von benannten Klasseninstanzen definiert.

    .. card::

        .. rubric:: Beispieldeklaration

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            public enum Arbeitstag { MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG }

        .. rubric:: Beispielverwendung (:java:`Arbeitstag w = Arbeitstag.FREITAG`)

        .. code:: java
            :number-lines:

            jshell> switch(w) {
                      case FREITAG -> System.out.println("gleich ist Wochenende");
                      default -> System.out.println("noch viel zu tun");
                    }
            gleich ist Wochenende

            jshell> w.values();
            ==> Arbeitstag[5] { MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG }

            jshell> Arbeitstag.valueOf("FREITAG").ordinal()
            ==> 4



Java Enum Konstanten können eigene Eigenschaften haben
-------------------------------------------------------

.. example::

    .. code:: java
        :number-lines:
        :class: copy-to-clipboard

        public enum Arbeitstag {
            MONTAG(1), DIENSTAG(2), MITTWOCH(3), DONNERSTAG(4), FREITAG(5);

            private final int tag;

            Arbeitstag( int tag ) {
                if (tag < 1 || tag > 5)
                    throw new IllegalArgumentException("Ungültiger Tag: " + tag);
                this.tag = tag;
            }
        }



Enum Konstanten können eigene Klassenbodies deklarieren
-------------------------------------------------------

.. deck::

    .. card::

        .. rubric:: Deklaration

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            enum Operation {
                PLUS {
                    double eval(double x, double y) { return x + y; }
                },
                DIVIDED_BY {
                    double eval(double x, double y) { return x / y; }
                };

                abstract double eval(double x, double y);
            }

    .. card::

        .. rubric:: Verwendung

        .. code:: java
            :number-lines:

            jshell> double x = 2;
            jshell> for(var op : Operation.values()) {
                        System.out.println(op.name() + " " +op.eval(x,x));
                    }
            PLUS 4.0
            DIVIDED_BY 1.0



Java :java:`enum`\ s - Technische Besonderheiten
------------------------------------------------

.. class:: incremental-list list-with-explanations

- Enums sind :java:`final` oder :java:`sealed` (falls es innere Klassen gibt)
- geschachtelte Enums sind (implizit) :java:`static`
- der Supertyp alle Enums ist :java:`java.lang.Enum`

  (:java:`extends` wird für Enums nicht unterstützt.)
- Klonen von Enums (:java:`clone()`) ist nicht möglich.
- Es können keine Instanzen der Enum Klasse (z. B. der Klassen Wochentag erzeugt werden.)



.. class:: exercises

Übung - Java Enums
------------------------------------------------

.. exercise:: Enum für Währungen

    Deklarieren Sie eine Enum (:java:`Currency`) für Währungen (Euro, Pfund etc.).

    Es soll möglich sein für eine Währung, das Währungssymbol zu erhalten.

    Für jede Währung soll es weiterhin möglich sein, die verfügbaren Stückelungen (:eng:`denominations`) zu erhalten.

    Schreiben Sie eine kleine :java:`main()`-Methode, um Ihr Enum zu testen.

    .. remark::
        :class: margin-top-1em

        Falls Sie die Stückelungen in einem Array zwischenspeichern sollten, dann stellen Sie sicher, dass das Array nicht verändert werden kann, wenn der Nutzer die verfügbaren Stückelungen abfragt.

    .. solution::
        :pwd: GanzVielEnums

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            public enum Currency {

                EURO("€",1,2,5,10,20,50,100,200),
                PFUND("£",1,2,5,10,20,50),
                DOLLAR("$",1,5,10,20,50,100);

                private final String symbol;
                private final int[] denominations;

                Currency(String symbol, int... denominations) {
                    this.denominations = denominations;
                    this.symbol = symbol;
                }

                public int[] getDenominations() {
                    return denominations.clone();
                }

                public String getSymbol() {
                    return symbol;
                }
            }



Bibliography
------------------------------------------------

.. [Larman2001] Craig Larman; Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and the Unified Process; Prentice Hall, 2001

.. [EndresRombach2003] Albert Endres and Dieter Rombach; A Handbook of Software and Systems Engineering; Addison Wesley, 2003

.. [Fowler1997] Martin Fowler; Analysis Patterns: Reusable Object Models; Addison-Wesley, 1997

.. [JEP395] JEP 395: Records; https://openjdk.java.net/jeps/395; zuletzt aktualisiert am 3.2.2024
