.. meta:: 
    :lang: de
    :author: Michael Eichberg
    :keywords: "Programmierung", "Java", "Variablen", "Methoden"
    :description lang=de: Einführung in die Programmierung mit Java
    :id: lecture-prog-java-basics
    :first-slide: last-viewed
    :exercises-master-password: WirklichSchwierig!
    
.. |html-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html
.. |pdf-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html.pdf
.. |at| unicode:: 0x40
.. |qm| unicode:: 0x22 

.. role:: incremental
.. role:: appear
.. role:: eng
.. role:: ger
.. role:: dhbw-red
.. role:: green
.. role:: the-blue
.. role:: minor
.. role:: ger-quote
.. role:: obsolete
.. role:: line-above
.. role:: smaller
.. role:: far-smaller
.. role:: monospaced
.. role:: copy-to-clipboard
.. role:: kbd
.. role:: java(code)
   :language: java



.. class:: animated-symbol 

Einführung in die Programmierung mit Java
====================================================

.. container:: line-above

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
    :Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
    :Version: 1.0

    .. container:: minor

        :Quelle: 
            Die Folien sind teilweise inspiriert von oder basierend auf Lehrmaterial von Prof. Dr. M. Matt bzw. Prof. C. Binning.

.. Im Wesentlichen Foliensätze von Michael Matt: 03_Grundlagen_Teil1 und Teil2 und Formatierung.key

.. supplemental::

    :Folien: 
        
        |html-source| 

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-move-to-top

Einführung
----------------



"Hello World" - das erste Java-Programm
------------------------------------------------

``HelloWorld.java``\ [#]_

.. include:: code/HelloWorld.java
    :code: java


.. [#]  Die Datei ``HelloWorld.java`` kann `hier <https://delors.github.io/prog-java-basics/code/HelloWorld.java>`__ heruntergeladen werden und mit ``java --enable-preview HelloWorld.java`` ausgeführt werden.

.. supplemental::

    Die Datei enthält ein einfaches Java-Programm, das den Text ``Hello World!`` auf der Konsole ausgibt. 
    
    In der ersten Zeile wird die Methode :java:`main` definiert. Diese ist die Einstiegsmethode in das Programm. Der Text ``Hello World!`` wird mit der *Methode* :java:`println` auf der Konsole ausgegeben. Die Methoden :java:`print`, und :java:`println` sind in Java Skripten immer verfügbar (bei Verwendung von ``--enable-preview`` (Java 23)) und geben den übergebenen Text auf der Konsole aus. Die Methode :java:`print` tut dies ohne und die Methode :java:`println`  mit Zeilenumbruch (``\\n``) am Ende.



Von der Konsole lesen
--------------------------------------------

``HelloYou.java``\ [#]_

.. include:: code/HelloYou.java
    :code: java

.. supplemental::

    Mit Hilfe von ``readln`` können Sie von der Konsole lesen. In Java Skripten ist ``readln`` immer verfügbar. Das Programm gibt den Text ``Hello`` gefolgt von dem eingegebenen Text aus. Die Methode ``readln`` gibt erst den übergebenen String aus und liest dann eine Zeile von der Konsole ein. Der eingelesene Text wird dann an das Wort "Hello " angehängt (mittels des "+" Operators) und als ganzes zurückgegeben.


.. [#] `HelloYou.java <https://delors.github.io/prog-java-basics/code/HelloYou.java>`__     



.. class:: integrated-exercise

Übung - mein erstes Programm
--------------------------------

.. exercise:: Lesen von und Schreiben auf die Konsole

    Schreiben Sie ein Java-Programm (``GutenMorgen.java``), das erst nach dem Namen des Nutzers ``X`` fragt und dann ``Guten Morgen X!`` auf der Konsole ausgibt. Beachten Sie dabei, dass der Text ``X`` durch den eingegebenen Namen ersetzt wird und am Ende ein Ausrufezeichen steht.

    Als zweites soll das selbe Programm dann nach dem Wohnort ``Y`` des Nutzers fragen und dann ``Y ist wirklich schön!`` auf der Konsole ausgeben. 

    Schreiben Sie das Programm und führen Sie es aus!

    .. solution::
        :pwd: ProgrammierenGanzEinfach!

        .. include:: code/GutenMorgen.java
            :code: java

.. supplemental::

    .. hint:: 
        :class: far-smaller

        Vorgehensweise:

        0) :minor:`Stellen Sie sicher, dass Java korrekt installiert ist. Öffnen Sie dazu die Konsole und geben Sie java --version ein.`
        1) Öffnen Sie einen Texteditor (z. B. Visual Studio Code oder ZED oder ...)
        2) Schreiben Sie den Rumpf des Programms: :java:`void main() { <IHR CODE> }`
        3) Ersetzen Sie ``<IHR CODE>`` durch den Code, der den Nutzer nach seinem Namen X fragt und dann "Guten Morgen X!" ausgibt.
        4) Führen Sie den Code aus in dem Sie die Konsole/ein Terminal öffnen und dort: :code:`java --enable-preview GutenMorgen.java` ausführen.


.. class:: new-section transition-move-to-top

Einfache Prozedurale Programmierung mit Variablen, Konstanten, Literalen und Ausdrücken
----------------------------------------------------------------------------------------


Prozedurale Elemente
---------------------------

.. container:: scrollable
        
    .. class:: incremental

    :Kommentare: Dienen der Codedokumentation und werden vom Compiler ignoriert.

    .. class:: incremental

    :primitive Datentypen: ganze Zahlen (byte, int, long), Fließkommazahlen (float, double), Zeichen (char, :minor:`byte`), Wahrheitswerte (boolean)

    .. class:: incremental

    :Literale: Konstante Wert (z.B. 42, 3.14, 'A', "Hello World!")

    .. class:: incremental

    :Zuweisungen: Speichern eines Wertes in einer Variable 

        (*Eine benannten Stelle im Speicher*)

    .. class:: incremental

    :Ausdrücke: dienen der Berechnung von Werten mit Hilfe von Variablen, Literalen und Operatoren

    .. class:: incremental

    :Kontrollstrukturen: dienen der Ablaufsteuerung mit Hilfe von Schleifen (:java:`while`, :java:`do-while`, :java:`for`) und Verzweigungen (:java:`if`-:java:`else`, :java:`switch`-:java:`case`)

    .. class:: incremental

    :Unterprogramme: Methoden (*Prozeduren* und *Funktionen*), die eine bestimmte Funktionalität wiederverwendbar bereitstellen


Kommentare
---------------------------

- Kommentare dienen der Dokumentation des Codes und helfen anderen Entwicklern den Code zu verstehen. 

.. stack:: incremental

    .. layer:: 

      - In Java unterscheiden wir folgende Arten von Kommentaren:

        .. class:: incremental list-with-explanations

        - Einzeilige Kommentare, die mit ``//`` beginnen und bis zum Ende der Zeile gehen.
        - Mehrzeilige Kommentare, die mit ``/*`` beginnen und mit ``*/`` enden.
        
          Kommentare, die mit ``/**`` beginnen und mit ``*/`` enden, sind so genannte JavaDoc Kommentare und dienen der Erzeugung von Dokumentation.
        - [ab Java 23] Mehrzeilige Kommentare, bei der jede Zeile mit ``///`` beginnt, werden als Markdown basierte JavaDoc Kommentare interpretiert. 

    .. layer:: incremental far-smaller

        Beispiel (ab Java 1.0 - spezifische Tags und HTML)

        .. code:: java

            /**
             * Berechnet die Fakultät von n.
             * 
             * @param n die Zahl, von der die Fakultät berechnet werden soll; (0 <= n <= 20).
             * @return die Fakultät von n. 
             */
            long fak(long n){ // TODO mögliche Fehlerfälle abfangen
                /* Die Verwendung von long als Datentyp limitiert uns auf n <= 20;
                   durch den wechsel von long auf double könnten wir bis n <= 170 rechnen; 
                   sind aber unpräziser. */
                if (n == 0) return 1;
                else return n * fak(n-1);
            }

    .. layer:: incremental far-smaller

        Beispiel (ab Java 23 - spezifische Tags und Markdown)

        .. code:: java

            /// Berechnet die Fakultät von n.
            ///
            /// @param n die Zahl, von der die Fakultät berechnet werden soll;/
            ///          (*0 <= n <= 20*).
            /// @return _die Fakultät von n_.
            long fak(long n){ // TODO mögliche Fehlerfälle abfangen
                /* Die Verwendung von long als Datentyp limitiert uns auf n <= 20;
                   durch den wechsel von long auf double könnten wir bis n <= 170 rechnen; 
                   sind aber unpräziser. */
                if (n == 0) return 1;
                else return n * fak(n-1);
            }

    .. layer:: incremental far-smaller

        Erzeugte Dokumentation (mit Java 23)

        .. image:: images/Fak_java23_javadoc.png
            :alt: Generierte Dokumentation für die Funktion fak(long n) 
            :height: 600px
            :align: center
            :class: box-shadow

.. supplemental::

    .. rubric:: JavaDoc tags

    :@param <name descr>: Dokumentiert einen Parameter einer Methode.
    :@return <descr>: Dokumentiert den Rückgabewert einer Methode.



Java Shell
------------------------------------------------

.. stack::

    .. layer::

        - Die Java Shell (``jshell``) ist ein interaktives Werkzeug, das es ermöglicht Java-Code (insbesondere kurze Snippets) direkt auszuführen.
        - Starten Sie die Java Shell mit dem Befehl ``jshell --enable-preview`` in der Konsole.
        - Den gültigen Java-Code können Sie direkt in der Java Shell eingeben oder über ``/edit`` als Ganzes bearbeiten.
        - Sie beenden die Java Shell mit dem Befehl ``/exit``.
        - Die Java Shell eignet sich insbesondere für das Ausprobieren von Code-Schnipseln und das Testen von Methoden.

    .. layer:: incremental  

        .. code:: Java
            :class: far-smaller

            # jshell --enable-preview

            |  Welcome to JShell -- Version 23
            |  For an introduction type: /help intro

            jshell> var x = "X";
            x ==> "X"

            jshell> x + "Y"
            $2 ==> "XY"

            jshell> $2.length()
            $3 ==> 2



.. class:: integrated-exercise

Übung - Java als Taschenrechner
--------------------------------

.. exercise:: Rechnen auf der Konsole

    Verwenden Sie die JShell als Taschenrechner und lösen Sie die folgenden Aufgaben in der angegebenen Reihenfolge jeweils mit Hilfe von *einer* Formel:

    1. Berechnen Sie, wie viele Sekunden ein Schaltjahr hat.
    2. Sie nehmen einen Kredit über 47865 € auf und zahlen monatlich 3,6% Zinsen. Wie viele Zinsen haben Sie nach 5 Jahren bezahlt?
    3. Ein Bauer hat 120 Äpfel. Er möchte die Äpfel gleichmäßig auf 4 Körbe verteilen. Nachdem er die Äpfel aufgeteilt hat, isst er 5 Äpfel aus jedem Korb. Wie viele Äpfel hat er noch?
    4.  Nehmen Sie an, dass weltweit jeden Tag 1 500 000 000 Plastikflaschen produziert werden. Wie viele Flaschen werden in einem Jahr produziert, wenn das Jahr 365 Tage hat, aber an den Wochenenden nicht produziert werden würde (gehen Sie von 52 Wochenenden aus)?

    .. solution::
        :pwd: JShellAlsTaschenrechner

        - :java:`366 * 24 * 60 * 60` ⇒ 31622400
        - :java:`47865 * 0.036 * 12 * 5` ⇒ 103388.4
        - :java:`(120 / 4 - 5 ) * 4` ⇒ 100
        - :java:`1_500_000_000*(365-52*2)` ⇒ 657 976 064 ⚠️ (Dieses Ergebnis kann offensichtlich nicht stimmen! Wie dieses Ergebnis zu Stand kam, klären wir als nächstes. Das richtige Ergebnis würden Sie mit folgender Formel erhalten :java:`1_500_000_000l*(365-52*2)`.)

.. supplemental::

    Zum Starten der JShell müssen Sie die Konsole (ein Terminal) öffnen und ``jshell`` eingeben. 

    .. hint:: 
        :class: smaller
      
        - In Programmiersprachen wird generell die englische Schreibweise für Zahlen verwendet. D. h. Sie müssen das Dezimalkomma durch einen Punkt ersetzen.)

        - Die Division wird in (den meisten) Programmiersprachen mit dem Operator ``/`` durchgeführt.

        - Die Multiplikation wird in (den meisten) Programmiersprachen mit dem Operator ``*`` durchgeführt.

        - Sie können Klammern (``(`` und ``)``) so verwenden, wie Sie es von der Mathematik gewohnt sind.

        - Sie können große Zahlen mit einem Unterstrich (``_``) formatieren, um die Lesbarkeit zu erhöhen: z. B. :java:`1_500_000_000`.



.. class:: new-section transition-scale

Primitive Datentypen
---------------------------



Verwendung von Datentypen
---------------------------

- Um die erlaubten Werte von Parametern, Variablen und Rückgabewerten genauer spezifizieren zu können, werden Datentypen verwendet.


.. class:: incremental

- Java stellt hierzu primitive Datentypen, Aufzählungen (:java:`enum`), Klassen und Interfaces zur Verfügung

.. stack:: incremental

    .. layer::

        Ein primitiver Datentyp ist z. B. :java:`int` (d. h. :eng:`integer` bzw. :ger:`Ganzzahl`)

        Dieser Datentyp legt fest, dass ein Wert eine Ganzzahl mit dem Wertebereich: :math:`[-2147483648, 2147483647]`


    .. layer:: incremental

        .. csv-table::
            :header: "Art", "Datentyp", "Beispiel"
            :align: center
            :class: highlight-cell-on-hover

            Ganzzahlen, ":java:`byte`, :java:`short`, :java:`int`, :java:`long`", :java:`123`
            Fließkommazahlen, ":java:`float`, :java:`double`", :java:`1.23` oder :java:`3.141d`
            Zeichen, :java:`char`, :java:`'a'`
            Wahrheitswerte, :java:`boolean`, ":java:`true`"

.. supplemental::

    Bitte beachten Sie, dass in Code für Zahlen immer die Englische Schreibweise verwendet wird. D. h. das Dezimalkomma wird durch einen Punkt ersetzt.

    Java kennt neben den primitiven Datentypen auch noch Arrays, Aufzählungen (:java:`enum`) sowie Klassen und Interfaces. Diese werden wir später behandeln.


Ganzzahlige Datentypen - Hintergrund
--------------------------------------------

- Ganzzahlige Werte werden im Speicher als Binärzahlen gespeichert; d. h. als Folge von Nullen und Einsen.
- Um verschieden große Werte zu speichern, stellen Programmiersprachen ganzzahlige Werte mit einer unterschiedlichen Zahl von Bits dar.

.. stack::

    .. layer::

        Zahlen werden immer mit 8 Bit (1 Byte), 16 Bit (2 Byte), 32 (4 Byte) oder 64 Bit (8 Byte) gespeichert. 

        .. hint:: 
          :class: far-smaller
          
          In Java werden Zahlen immer vorzeichenbehaftet gespeichert. D. h. ein Bit wird für das Vorzeichen verwendet; auch wenn es nicht immer benötigt wird.

    .. layer:: incremental

        Umrechnung Binär-Dezimal

        .. csv-table::
            :header: "Binär", "Dezimal"
            :align: center
            :class: highlight-line-on-hover far-smaller

            :java:`0000 0000`, +0
            :java:`0000 0001`, +1
            ..., ...
            :java:`0111 1111`, +127
            :java:`1000 0000`, -128
            ..., ...
            :java:`1111 1111`, -1

    .. layer:: incremental

        .. csv-table::
            :header: Datentyp, "Genauigkeit (in Bit)", Wertebereich, Anzahl Werte
            :align: center
            :class: highlight-cell-on-hover

            :java:`byte`, 8, -128 bis 127, :math:`2^8`
            :java:`short`, 16, -32768 bis 32767, :math:`2^{16}`
            :java:`int`, 32, -2147483648 bis 2147483647, :math:`2^{32}`
            :java:`long`, 64, -922337022036854775808 bis 922337022036854775807, :math:`2^{64}`

.. supplemental::

    Die Größenwahl für ``long`` und ``int`` ist teilweise historisch bedingt. Auf gängigen Prozessoren sind jedoch 64 Bit und 32 Bit die natürlichen Größen für Ganzzahlen und können effizient verarbeitet werden. 


Gleitkommatypen - Hintergrund (Konzeptionell)
----------------------------------------------

.. stack::

    .. layer::

        Gleitkommazahlen werden in `Java nach Norm IEEE 754 (Seit Java 15 Version 2019) <https://docs.oracle.com/javase/specs/jls/se23/html/jls-4.html#jls-4.2.3>`__ durch die Mantisse :math:`m` und den Exponent :math:`e` dargestellt: :math:`z = m \times 2^e`.

        Für das Vorzeichen wird das erste Bit verwendet, für Mantisse und Exponent werden zusammen 31- (bei :java:`float`) bzw. 63-Bit (bei :java:`double`)  verwendet.
        
        Die Mantisse und der Exponent sind vorzeichenbehaftete Ganzzahlen.

            Beispiel (vereinfacht)

            .. class:: incremental

            :math:`7 \times 2^{-1} = { 7 \over 2 } = 3.5`

            .. class:: incremental
                
            :math:`-7 \times 2^{-1} = { -7 \over 2 } = -3.5`

            .. class:: incremental
                
            :math:`7 \times 2^{-3} = { 7 \over 8 } = 1.125`

            .. class:: incremental
                
            :math:`7 \times 2^{0} = { 7 \over 1 } = 7`

    .. layer:: incremental

        .. csv-table::
            :header: Datentyp, Genauigkeit, Mantisse, Exponent, Wertebereich
            :align: center
            :class: highlight-cell-on-hover smaller
        
            :java:`float`, 32, 23, 8, ca. :math:`-3.4*10^{38}\; \text{bis}\; 3.4 \times 10^{38}`
            :java:`double`, 64, 52, 11, ca. :math:`-1.8*10^{308}\; \text{bis}\; 1.8 \times 10^{308}`

.. supplemental::

    Ganzzahlen :math:`< 2^{24}` können bei Verwendung des Datentyps :java:`float` exakt dargestellt werden; bei :java:`double` sind es Ganzzahlen :math:`< 2^{53}`.

    In beiden Fällen gibt es noch die Möglichkeit +/- Unendlich und NaN (Not a Number) zu repräsentieren.



Gleitkommatypen - Verwendung
--------------------------------------------

.. warning::

    Bei Berechnungen mit Gleitkommazahlen treten Rundungsfehler auf, da nicht alle Werte in beliebiger Genauigkeit dargestellt werden können

    Beispiel: Der Wert ``0.123456789f`` (``float``) wird durch die Darstellung mit Mantisse und Exponent (:math:`m \times 2^e`) zu ``0.12345679``.

    .. container:: incremental

        **Gleitkommazahlen sind somit nicht für betriebswirtschaftliche Anwendungen geeignet.**

    .. container:: incremental margin-top-1em

        Gleitkommazahlen sind z. B. für wissenschaftliche Anwendungen geeignet.

.. supplemental::

    Für betriebswirtschaftliche Anwendungen gibt es den Datentyp :java:`BigDecimal`. Dieser ist aber kein primitiver Datentyp und wird später behandelt. 



Zeichen - Hintergrund
--------------------------------------------

- einzelne Zeichen (z. B. ':java:`a`\ ') werden in Java mit dem Datentyp :java:`char` dargestellt
- ein :java:`char` ist (intern) eine vorzeichenlose Ganzzahl mit 16 Bit (d. h. eine Zahl im Bereich :math:`[0,65536]`), die den Unicode-Wert des Zeichens repräsentiert

  Alle gängigen (westeuropäischen) Zeichen können mit einem :java:`char` dargestellt werden.
  
  .. warning::
    :class: smaller

    Seit Java eingeführt wurde, wurde der Unicode Standard mehrfach weiterentwickelt und heute gibt es Zeichen, die bis zu 32 Bit benötigen. Diese können mit nur einem :java:`char` nicht dargestellt werden und benötigen ggf. zwei :java:`char`\ s.

- Für Zeichenketten (z. B. ``"Hello World"``) existiert ein nicht-primitiver Datentyp :java:`String`.

.. supplemental::

    .. rubric:: Unicode Zeichen und :java:`char`\ s

    Hinweise:
    - 0x1F60E ist der Unicode Codepoint von 😎 und :java:`Character.toChars(<Wert>)` rechnet den Wert um. 
    - In Java ist die Länge (:java:`<String>.length()`) einer Zeichenkette (:eng:`String`) die Anzahl der benötigten :java:`char`\ s und entspricht somit nicht notwendigerweise der Anzahl der (sichtbaren) Zeichen.

    .. code:: java
        :class: far-far-smaller
        :number-lines:

        jshell> var smiley = Character.toChars(0x1F60E)
        smiley ==> char[2] { '?', '?' }

        jshell> var s = new String(smiley)
        s ==> "😎"

        jshell> s.length()
        $1 ==> 2

        jshell> s.getBytes(StandardCharsets.UTF_8)
        $2 ==> byte[4] { -16, -97, -104, -114 }

        jshell> s.codePointCount(0,s.length())
        $3 ==> 1



Wahrheitswerte (Boolesche) - Hintergrund
--------------------------------------------

.. class:: incremental list-with-explanations

- die Wahrheitswerte wahr (:java:`true`) und falsch (:java:`false`) werden in Java mit dem Datentyp :java:`boolean` dargestellt
- häufigste (explizite) Verwendung ist das Speichern des Ergebnisses einer Bedingungsüberprüfung

  (Wahrheitswerte sind zentral für Bedingungsüberprüfungen und Schleifen, werden dort aber selten explizit gespeichert; z. B. beim Test von ``n`` auf 0 im Algorithmus für die Berechnung der Fakultät.)


Konvertierung von Datentypen
--------------------------------------------

.. container:: scrollable

    .. class:: incremental

    - Die (meist verlustfreie,) implizite Konvertierung von Datentypen ist nur in eine Richtung möglich:

        :incremental:`( (byte → short) | char )` :incremental:`→ int` :incremental:`→ long`  :incremental:`→ float` :incremental:`→ double`

    - Konvertierungen in die andere Richtung sind immer explizit anzugeben, da es zu Informationsverlust kommen kann

      Beispiel: :java:`int` zu :java:`byte` (Wertebereich :math:`[-128,127]`)

      .. container:: incremental minor

        Bei der Konvertierung von :java:`int` zu :java:`byte` werden die höherwertigen Bits (9 bis 32) einfach abgeschnitten.

        :incremental:`(byte) 128` :incremental:`⇒ -128`
        
        :incremental:`(byte) 255` :incremental:`⇒ -1`

        :incremental:`(byte) 256` :incremental:`⇒ 0`


.. supplemental::

    - Beispiel für die verlustbehaftete implizite Konvertierung 

      .. code:: java
        :class: far-far-smaller

        jshell> long l = Long.MAX_VALUE - 1;
        l ==> 9223372036854775806

        jshell> float f = l
        f ==> 9.223372E18

        jshell> f == l
        $1 ==> true                     // Warum ?
        
        jshell> ((long) f) == l
        $2 ==> false

        jshell> ((long) f)
        $3 ==> 9223372036854775807      // == Long.MAX_VALUE

    - Wahrheitswerte können nicht konvertiert werden.



.. class:: new-section transition-scale

Literale
---------------------------


Literale - Übersicht
--------------------------------------------

Literale stellen konstante Werte eines bestimmten Datentyps dar:

.. csv-table::
    :header: Datentyp, Literal (Beispiele)
    :align: left
    :class: highlight-cell-on-hover smaller incremental

    :java:`int`, :minor:`Dezimal:` 127 :minor:`; Hexadezimal:` 0xcafebabe\ [#]_ :minor:`; Oktal:` 010 :minor:`; Binär:` 0b1010
    :java:`long`, 123_456_789l oder 123456789L  :minor:`("_" dient nur der besseren Lesbarkeit)`
    :java:`float`, 0.123456789f oder 0.123456789F
    :java:`double`, "0.123456789 oder 0.123456789d oder 0.123456789D"
    :java:`char`, "'a' (Zeichen-Darstellung) oder 97 (Zahlen-Darstellung) oder 
    '\\u0061' (Unicode-Darstellung) oder Sonderzeichen (siehe nächste Folie)"
    :java:`String`, "|qm|\ Hallo\ |qm| oder 

    |qm|\ |qm|\ |qm|\  
    
    Text-block\ |qm|\ |qm|\ |qm|\ "

    :java:`boolean`, true oder false

.. [#] 0xcafebabe ist der Header aller kompilierten Java-Klassen-Dateien.

.. supplemental::

    Textblöcke werde seit Java 15 unterstützt.

    Mittels: :code:`-Xlint:text-blocks` können Sie sich warnen lassen, wenn die Textblöcke potentiell nicht korrekt formatiert sind.



Literale - Sonderzeichen ("\\" ist das Escape-Zeichen)
-------------------------------------------------------

.. csv-table::
    :align: center
    :class: highlight-cell-on-hover
    :header: Datentyp, Literal (Beispiele)

    \\\', Einfaches Hochkomma
    \\\", Doppeltes Hochkomma
    \\ \ \\, Backslash
    \\b, Rückschrittaste (backspace)
    \\f, Seitenvorschub (form feed)
    \\n, Zeilenschaltung (line feed)
    \\t, Tabulator
    \\r, Wagenrücklauf


.. class:: new-section transition-scale

Variablen und Konstanten
---------------------------


Variablen - Übersicht
--------------------------------------------

.. stack:: 

    .. layer::

        - Variablen stellen einen logischen Bezeichner für einen Wert eines bestimmten Datentyps dar.
        - Variablen müssen erst deklariert werden. Danach können sie weiter initialisiert werden, wenn der Standardwert nicht ausreicht.
        
            .. class:: incremental

            :Deklaration: Variablennamen und Datentyp werden festgelegt

            .. class:: incremental

            :Initialisierung (optional): Variablen werden mit einem bestimmten Wert versehen
        
        .. class:: incremental

        - der Wert einer Variablen kann jederzeit geändert werden

    .. layer:: incremental

        *Beispieldeklaration und -initialisierung*

        .. include:: code/Variables.java
            :code: java
            :class: far-smaller



Konstanten - Übersicht
--------------------------------------------


.. stack:: 

    .. layer::

      - Konstanten sind Variablen, die nach der Initialisierung nicht mehr verändert werden können
      - Konstanten werden in Java mit dem Schlüsselwort :java:`final` deklariert
      - Es wird überprüft, dass keine weitere Zuweisung erfolgt
      - Konvention: Konstanten werden in Großbuchstaben geschrieben

    .. layer:: incremental

      *Beispieldeklaration und -initialisierung*

      .. include:: code/Constants.java
          :code: java
          :class: far-smaller


Bezeichner (:eng:`Identifier`) - Übersicht
--------------------------------------------

.. class:: incremental

- Bezeichner sind Namen für Variablen, Konstanten, Methoden, Klassen, Interfaces, Enums, etc.
- Erstes Zeichen: Buchstabe, Unterstrich (_) oder Dollarzeichen ($); 
- Folgende Zeichen: Buchstaben, Ziffern, Unterstrich oder Dollarzeichen
- Groß- und Kleinschreibung wird unterschieden
- Schlüsselworte (z. B. :java:`var`, :java:`int`, etc.) dürfen nicht als Bezeichner verwendet werden
- Konvention: 
  
    .. class:: incremental smaller

    - Variablen (z. B. :java:`aktuellerHerzschlag`) und Methoden (z. B. :java:`println`) verwenden *lowerCamelCase* 
    
    - Konstanten verwenden *UPPER_CASE* und Unterstriche (z. B. :java:`GEWICHT_BEI_GEBURT`)
    
    - Klassen, Interfaces und Enums verwenden *UpperCamelCase* (z. B. :java:`BigDecimal`)


.. supplemental:: 
    
    In Java ist es unüblich, das Dollarzeichen ($) in eigenem Code zu verwenden und es wird in der Regel nur von der JVM (der Java Virtual Machine; d. h. der Ausführungsumgebung) verwendet.
    
    Ein Unterstrich am Anfang des Bezeichners sollte ebenfalls vermieden werden. Ganz insbesondere ist darauf zu verzichten den Unterstrich als alleinigen Variablennamen zu verwenden, da der *reine* Unterstrich seit `Java 22 für unbenannte Variablen verwendet wird <https://openjdk.org/jeps/456>`__ und dies die Migration von altem Code erschwert.



.. class:: integrated-exercise

Übung - Bezeichner
--------------------------------

Welche der folgenden Bezeichner sind (a) ungültig, (b) gültig aber sollten dennoch nicht verwendet werden oder (c) gültig und entsprechen den Konventionen?


.. exercise:: Bezeichner

    .. container:: three-columns far-smaller

        .. container:: column


            .. code:: Java
                :number-lines:
            
                var 1a = ...
                var 1_a = ...
                var _1a = ...
                var a1 = ...

        .. container:: column

            .. code:: Java
                :number-lines: 5

                int i;
                int _i;
                float $$f;
                final float E = ...;

        .. container:: column

            .. code:: Java
                :number-lines: 9

                String Wohnort;
                String ortDerGeburt;
                void BucheFlug(){...}
                class FlugBuchungen{...}

    .. solution::
        :pwd: Bezeichner_Sind-Wichtig

        :Ungültig: 1. und 2.
        :Gültig aber nicht verwenden: (3.), (6.) , 7., 9., 11.
        :Gültig und entsprechen den Konventionen: (3.), 4., 5., (6.), 8., 10., 12.



.. class:: integrated-exercise

Übung - Variablen und Konstanten
--------------------------------

.. hint::
    :class: far-far-smaller

    Für diese Aufgabe können Sie sowohl die Java Shell verwenden als auch Ihren Code in eine Datei schreiben. Denken Sie in diesem Fall daran, dass der Code in einer Methode :java:`main` stehen muss (:java:`void main(){ <IHRE CODE> }`).

.. exercise:: Grundlegende Datentypen


  - Deklarieren und initialisieren Sie eine Variable x mit dem Ganzzahlwert 42. 
  
    - Welche Datentypen können Sie verwenden, wenn eine präzise Darstellung des Wertes notwendig ist? 
    - Welcher Datentyp wird verwendet, wenn Sie keinen Typ angeben (d. h. wenn Sie :java:`var` schreiben bzw. anders ausgedrückt welchen Typ hat das Literal ``42``)? 

  - Weisen Sie den Wert der Variable ``x`` einer Variable ``f`` vom Typ :java:`float` zu. 
  - Ändern Sie den Wert der Variablen ``x``. Welche Auswirkungen hat das auf die Variable ``f`` vom Typ :java:`float`?
  - Deklarieren und initialisieren Sie die Konstante π (Wert 3.14159265359).

  .. solution::
        :pwd: DatentypenKonstantenUndVariablen

        Der Wert 42 kann von allen primitiven Datentypen präzise dargestellt werden. Wenn Sie 
        keinen Typ angeben, wird der Typ :java:`int` verwendet.

        .. code:: Java
            :class: far-smaller
 
            jshell> int i = 42
            i ==> 42

            jshell> byte b = 42
            b ==> 42

            jshell> char c = 42
            c ==> '*'

            jshell> short s = 42
            s ==> 42

            jshell> long l = 42
            l ==> 42

            jshell> float f = 42
            f ==> 42.0

            jshell> double d = 42
            d ==> 42.0

            jshell> var v = 42
            v ==> 42

            jshell> float vf = v
            vf ==> 42.0

            jshell> v = 43 // Änderung von v
            v ==> 43

            jshell> vf // hat keine Auswirkung auf vf, da vf eine Kopie von v ist
            vf ==> 42.0

            jshell> double PI = 3.14159265359d // π wäre auch ein gültiger, aber ungewöhnlicher Bezeichner 
            d ==> 3.14159265359




.. class:: new-section transition-scale

Ausdrücke und Operatoren
---------------------------


Ausdrücke und Operatoren - Übersicht
--------------------------------------------

- Berechnungen erfolgen über Ausdrücke, die sich aus Variablen, Konstanten, Literalen, Methodenaufrufen und Operatoren zusammensetzen.

.. class:: incremental list-with-explanations 
    
- Jeder Ausdruck hat ein Ergebnis (d. h. Rückgabewert).

  Beispiel: (``age + 1``) addiert zwei Werte und liefert das Ergebnis der Addition zurück.
  
- Einfache Ausdrücke sind Variablen, Konstanten, Literale und Methodenaufrufe.
- Komplexe Ausdrücke werden aus einfachen Ausdrücken und Operatoren (z. B. +, -, \*, /, %, >, <, >=, \<=) zusammengesetzt
- Ergebnisse von Ausdrücken können insbesondere Variablen zugewiesen werden (z.B. :java:`int newAge = age + 1` oder :java:`var isAdult = age >= 18`) 
- Ausdrücke, die einen Wahrheitswerte ergeben können zusätzlich in Bedingungen (z. B. :java:`if(age + 5 >= 18) ...`) verwendet werden.



Ausdrücke und Operatoren - Beispiele
--------------------------------------------

.. include:: code/Expressions.java
    :code: java
    :class: far-smaller


Operatoren und Operanden in der Mathematik
--------------------------------------------

.. container:: smaller

    **Binäre/Zweistellige Operatoren** (:eng:`Binary Operators`)

    *Addition*

    .. math::

        \begin{matrix}
        \text{1. Operand} & \text{Operator} & \text{2. Operand}  \\
        1 & + & 2  \\
        \end{matrix}

.. container:: incremental smaller

    **Unäre/Einstellige Operatoren** (:eng:`Unary Operators`)

    *Negation*

    .. math::

        \begin{matrix}
        \text{Operator} & \text{Operand}  \\
        - & ( 2 )  \\
        \end{matrix}


    *Fakultät*

    .. math::

        \begin{matrix}
        \text{Operator} & \text{Operand}  \\
        2 & !  \\
        \end{matrix}


Operatoren
--------------------------------------------

- Operatoren sind spezielle Zeichen, die auf Variablen, Konstanten und Literale angewendet werden, um Ausdrücke zu bilden.

.. class:: incremental list-with-explanations

- Die Auswertungsreihenfolge wird durch die Priorität der Operatoren bestimmt. 
  
  (Wie aus der Schulmathematik bekannt gilt auch in Java: ``*`` oder ``/`` vor ``+`` und ``-``.)

- Runde Klammern können verwendet werden, um eine bestimmt Auswertungsreihenfolge zu erzwingen bzw. dienen zur Strukturierung

- Es gibt Operatoren, die auf eine, zwei oder drei Operanden angewendet werden: diese nennt man dann ein-, zwei- oder dreistellige Operatoren. 
- Für einstellige Operatoren wird die Präfix- oder Postfix-Notation (z.B. :java:`++a` oder :java:`a++`) verwendet, 
- Für mehrstellige Operatoren wird die Infix-Notation (z.B. :java:`a + b`) verwendet



Klassifikation der Operatoren
--------------------------------------------

.. class:: incremental list-with-explanations

- Arithmetische Operatoren (auf numerische Datentypen)
- Vergleichsoperatoren 
- Logische Operatoren (auf boolean Datentypen)
- Bedingungsoperatoren
- Bitoperatoren (auf ganzzahligen Datentypen)
- Zuweisungs- und Verbundoperatoren (auf alle Datentypen)
- Konkatenationsoperator (String)
- Explizite Typkonvertierung 

.. supplemental::

    Einige Operatoren sind nur auf bestimmten Datentypen anwendbar. So sind Vergleichsoperatoren wie ``<=`` oder ``>=`` nur auf numerische Datentypen anwendbar, aber ``==`` und ``!=`` auf allen Typen. Es gilt immer, dass die linke und die rechte Seite Typkompatibel sein müssen; mit anderen Worten wir können nur Dinge vergleichen, die den gleichen Typ haben oder für die eine automatische Typumwandlung möglich ist. Ein Vergleich von einem String und einer Zahl ist z. B. nicht möglich.

    *Beispiel für unzulässigen Vergleich:*

    .. code:: bash

        jshell> "s" == 1
        |  Error:
        |  bad operand types for binary operator '=='
        |    first type:  java.lang.String
        |    second type: int
        |  "s" == 1


.. class:: no-title center-child-elements transition-move-left

Verwendung der JShell
--------------------------------------------

.. hint:: 

    Wenn Sie die folgenden Codeschnipsel (:eng:`Snippets`) in der Java Shell (``jshell``) ausführen möchten, dann müssen sie noch die Methoden :java:`println` und :java:`readln` definieren: :java:`void println(Object o) { System.out.println(o); }` und :java:`String readln(String s) { return System.console().readln(s); }`. 

    .. container:: incremental smaller margin-top-1em

        Alternativ können Sie den unten verlinkten Code direkt in die JShell laden:

        .. code:: Shell

            jshell --enable-preview <DATEINAME>

    .. container:: incremental smaller margin-top-1em
         
        Alternative können Sie ein Java Script schreiben (inkl. ``main`` Methode). In diesem Fall sind die beiden Methoden direkt verfügbar und müssen nicht extra deklariert werden.

    .. container:: incremental smaller margin-top-1em

        Ich empfehle Ihnen, die Beispiele händisch einzugeben, dann lernen Sie mehr!

.. class:: transition-fade

Zweistellige Arithmetische Operatoren 
----------------------------------------------------------

.. stack::

    .. layer:: 

        .. csv-table::
            :header: Operator, Anwendung, Bedeutung
            :class: highlight-line-on-hover

            ``+``, x + y, Summe von x und y (Additions-Operator)
            ``-``, x - y, Differenz von x und y (Subtraktions-Operator)
            ``*``, x * y, Produkt von x und y (Multiplikations-Operator)
            ``/``, x / y, Quotient von x und y (Divisions-Operator)
            ``%``, x % y, Rest der ganzzahligen Division von x und y (Modulo-Operator)

    .. layer:: incremental

        JShell-Beispiel: `ArithmetischeOperatoren.jshell.java <./code/ArithmetischeOperatoren.jshell.java>`__

        .. include:: code/ArithmetischeOperatoren.jshell.java
            :code: java
            :class: far-smaller margin-bottom-1em
            :number-lines:
            :start-line: 2
            :end-before: // Einstellige Operatoren


        .. exercise:: Zweistellige Operatoren - welche Werte werden ausgegeben?
            
            .. solution::
                :pwd: WieZuErwarten

                8, 15, 1 (Ganzzahlarithmetik!), 2


.. supplemental::

    Andere Sprachen (z. B. JavaScript oder Python) haben häufig noch ``**`` für die Potenzierung. Dies ist in Java über ``Math.pow`` möglich.



Einstellige Arithmetische Operatoren 
----------------------------------------------------------

.. stack::

    .. layer::

        .. csv-table::
            :header: Operator, Anwendung, Bedeutung
            :class: highlight-line-on-hover

            ``+``, +x, Positiver Wert von x
            ``-``, -x, Negativer Wert von x
            (Präfix) ``++``, ++x, Prä-inkrement: Gleichbedeutend mit :math:`\{ x_{neu}=x_{alt}+1; x_{neu} \}`
            ``++`` (Postfix), x++, Post-inkrement: Gleichbedeutend mit :math:`\{ x_{neu}=x_{alt}+1; x_{alt} \}`
            (Präfix) ``--``, --x, Prä-dekrement: Gleichbedeutend mit :math:`\{ x_{neu}=x_{alt}-1; x_{neu} \}`
            ``--`` (Postfix), x--, Post-dekrement: Gleichbedeutend mit :math:`\{ x_{neu}=x_{alt}-1; x_{alt} \}`

    .. layer:: incremental

        JShell-Beispiel: `ArithmetischeOperatoren.jshell.java <./code/ArithmetischeOperatoren.jshell.java>`__

        .. include:: code/ArithmetischeOperatoren.jshell.java
            :code: java
            :class: far-smaller margin-bottom-1em
            :number-lines:
            :start-line: 12

        .. exercise:: Einstellige Operatoren - welche Werte werden ausgegeben?

            .. solution::
                :pwd: Achtung!

                6, 6, -7


Zweistellige Vergleichsoperatoren
----------------------------------------------------------

.. stack::

    .. layer::

        .. csv-table::
            :header: Operator, Anwendung, Bedeutung
            :width: 100%
            :class: highlight-line-on-hover

            ``==``, x == y, "Überprüft, ob die Werte von x und y **gleich** sind"
            ``!=``, x != y, "Überprüft, ob der Werte von x und y **ungleich** sind"
            ``<``, x < y, "Überprüft, ob der Wert von x **kleiner** dem Wert von y ist"
            ``<=``, x <= y, "Überprüft, ob der Wert von x **kleiner oder gleich** dem Wert von y ist"
            ``>``, x > y, "Überprüft, ob der Wert von x **größer** dem Wert von y ist"
            ``>=``, x >= y, "Überprüft, ob der Wert von x **größer oder gleich** dem Wert von y ist"

    .. layer:: incremental

        JShell-Beispiel: `Vergleichsoperatoren.jshell.java <./code/Vergleichsoperatoren.jshell.java>`__

        .. include:: code/Vergleichsoperatoren.jshell.java
            :code: java
            :class: far-smaller margin-bottom-1em
            :number-lines:
            :start-line: 2

        .. exercise:: Einstellige Operatoren - welche Werte werden ausgegeben?

            .. solution::
                :pwd: Achtung-

                Strings:

                    true, false, true

                Numerische Werte:

                    true, true, false


Ein- und zweistellige logische Operatoren
----------------------------------------------------------

.. stack:: 

    .. layer:: 

        .. csv-table::
            :header: Operator, Anwendung, Bedeutung
            :width: 100%
            :class: highlight-line-on-hover

            ``!``, !x, Negation (Aus true wird false und umgekehrt)
            ``&``, x & y, Logisches UND (AND)
            ``&&``, x && y, Bedingtes logisches UND (AND Short-circuit Evaluation)
            ``|``, x | y, Logisches ODER (OR)
            ``||``, x || y, Bedingtes logisches ODER (OR Short-circuit Evaluation)
            ``^``, x ^ y, Logisches ENTWEDER-ODER (XOR :eng:`exclusive OR`)

    .. layer:: incremental

        .. rubric:: Wahrheitstabelle

        .. csv-table::
            :header: 
            :width: 100%
            :class: highlight-line-on-hover

            x, y, !x, "x & y oder x && y", "x | y oder x || y", x ^ y
            true, true, false, true, true, false
            true, false, false, false, true, true
            false, true, true, false, true, true
            false, false, true, false, false, false

    .. layer:: incremental

        JShell-Beispiel: `LogischeOperatoren.jshell.java <./code/LogischeOperatoren.jshell.java>`__

        .. include:: code/LogischeOperatoren.jshell.java
            :code: java
            :class: far-smaller margin-bottom-1em
            :number-lines:
            :start-line: 2

        .. exercise:: Logische Operatoren - welche Werte werden ausgegeben?

            .. solution::
                :pwd: &&FehlerVoraus!

                ``&`` und ``&&`` Vergleiche:

                    true, false, ⚡️ (ArithmeticException), true, true

                ``||`` basierte Vergleiche: 

                    true, true, ⚡️ (ArithmeticException)

.. supplemental::

    - Der Unterschied zwischen ``&`` und ``&&`` ist, dass ``&&`` nur den rechten Operanden auswertet, wenn der linke Operand ``true`` ist. 
    - Der Unterschied zwischen ``|`` und ``||`` ist, dass ``||`` nur den rechten Operanden auswertet, wenn der linke Operand ``false`` ist. 
    
    Mit anderen Worten bei ``&&`` und ``||`` wird der Ausdruck nur so weit ausgewertet, wie nötig ist, um das Ergebnis des Ausdrucks als Ganzes zu bestimmen. 


.. class:: integrated-exercise transition-scale

Übung
--------------------------------

.. exercise:: Vergleichsoperatoren

    Lesen Sie zwei Zahlen von der Console ein (siehe `Von der Konsole lesen`_) und vergleichen Sie diese auf Gleichheit. Speichern Sie das Ergebnis in einer Variable und geben Sie das Ergebnis danach auf der Konsole aus.

    Zum  Konvertieren der eingelesenen Zeichenketten in Zahlen verwenden Sie die Methode :java:`Integer.parseInt(<EINGABE>)`. Sie können hier den eingelesen String direkt an die Methode übergeben oder vorher in einer Variable speichern.
    
    .. container:: smaller

        Denken Sie daran, dass Ihr Code in die ``main`` Methode gehört:

        .. code:: java
            :class: far-smaller

            void main() {
                // Ihr Code
            }

        Schreiben Sie ein vollständiges Java Script, dass Sie mit dem Java Interpreter (``java --enable-preview <JAVA-DATEI>``) ausführen können.

    .. solution::
        :pwd: NamenEinlesenUndVergleichen

        .. include:: code/NamenEinlesenUndVergleichen.java
            :code: java
            :class: far-smaller
            :number-lines:


.. class:: transition-fade

Bedingungsoperator
----------------------------------------------------------

.. stack::

    .. layer::

        Der Bedingungsoperator:

            |    <Bedingungsausdruck :math:`c`> ``?`` 
            |        <auszuwertender Ausdruck :math:`a_{(c\,wahr)}` falls :math:`c` wahr > 
            |        : 
            |        <auszuwertender Ausdruck :math:`a_{(c\,falsch)}` falls :math:`c` falsch/unwahr>

        liefert in Abhängigkeit eines Ausdrucks ``c`` (der einen Wahrheitswert liefert) das Ergebnis des ersten Ausdrucks oder des zweiten Ausdrucks  zurück.

    .. layer:: incremental

        :math:`c\; ?\; a_{(c\,wahr)}\; :\; a_{(c\,falsch)}` 

        Beide Ausdrücke :math:`a_{(c\,wahr)}` und :math:`a_{(c\,falsch)}` müssen entweder numerische Werte oder boolean Werte oder Instanzen einer Klasse zurück liefern (d. h. Werte die implizit ineinander konvertiert werden dürfen)

        Von den beiden Ausdrücken wird *nur ein Ausdruck ausgewertet*.

    .. layer:: incremental

        **Beispiele**

        .. code:: java
            :class: far-smaller 

            int n = 0;
            n == 0 ? 1 : 2 

        .. container:: incremental

            Verschachtelung ist möglich aber *nicht*   empfehlenswert:

            .. code:: java
                :class: far-smaller 

                int alter = Integer.parseInt(readln("Wie alt sind Sie?"));
                alter < 18 ? 
                    "jugendlicher" : 
                    alter < 65 ? 
                        "erwachsener" : 
                        "senior"; 


Bitoperatoren
----------------------------------------------------------


.. stack::

    .. layer::

        Bitoperatoren (``>>``, ``<<``, ...) arbeiten auf der binären Darstellung der numerischen, primitiven Datentypen für Ganzzahlen.

        Bitoperationen werden häufig für spezielle Algorithmen verwendet, um die gleiche Operation auf mehreren Daten (den Bits) gleichzeitig anzuwenden (1 CPU Zyklus). Ein Beispiel ist das Ver-/Entschlüsseln von Daten (insbesondere mit ``XOR``).

        Bestimmte mathematische Operationen (z. B. Division durch :math:`2^x`) können durch Bitoperationen ersetzt werden, die effizienter sind (z. B. :java:`16 / 4 == 16 >> 2`).

    .. layer:: incremental

        .. csv-table::
            :header: Operator, Anwendung, Bedeutung
            :width: 100%
            :class: highlight-line-on-hover

            ``~``, ~x, Bitweise-Negation
            ``&``, x & y, Bitweise UND
            ``|``, x | y, Bitweise ODER
            ``^``, x ^ y, Bitweise ENTWEDER-ODER
            ``<<``, x << y, Bits von x werden um y Positionen nach links verschoben und von rechts mit 0 aufgefüllt
            ``>>``, x >> y, Bits von x werden um y Positionen nach rechts verschoben und von links mit dem höchsten Bit aufgefüllt
            ``>>>``, x >>> y, Bits von x werden um y Positionen nach rechts verschoben und von links mit 0 aufgefüllt

    .. layer:: incremental

        Bits verschieben (:eng:`shiften`) um eine bestimmte Anzahl von Positionen:

        .. code:: java
            :class: far-smaller

            jshell> Integer.toBinaryString(Integer.MIN_VALUE)
            $1 ==> "10000000000000000000000000000000"

            jshell> Integer.toBinaryString(Integer.MIN_VALUE >> 31)
            $2 ==> "11111111111111111111111111111111"

            jshell> Integer.toBinaryString(Integer.MIN_VALUE >>> 31)
            $3 ==>                                "1"

    .. layer:: incremental

        Verschlüsselung mit XOR (`EncryptionWithXOR.jshell.java <./code/EncryptionWithXOR.jshell.java>`__):

        .. code:: java
            :class: far-smaller

            final var key = new java.util.Random().nextInt();   
            Integer.toBinaryString(key);             // ==> "1001101011000011100110101001110"
            
            final var income = 13423;
            Integer.toBinaryString(income);          // ==>                  "11010001101111"
            
            // Verschlüsselung von "income" mit "key" mit Hilfe von XOR:
            final var encryptedIncome = income ^ key;
            Integer.toBinaryString(encryptedIncome); // ==> "1001101011000011111100100100001"

        .. warning::
            :class: far-smaller incremental

            Die dargestellte Verschlüsselung mit XOR ist die Grundlage aller modernen Verschlüsselungsalgorithmen, aber es gibt sehr viel zu beachten, um eine sichere Verschlüsselung zu gewährleisten. 



Zuweisungs- und Verbundoperatoren
----------------------------------------------------------


.. stack::

    .. layer::

        Zuweisungs- und Verbundoperatoren weisen einer Variablen einen neuen Wert zu (z. B. :java:`int newAge = age + 1;`).

        Die Variable steht auf der linken Seite des Operators.

        Der Ausdruck zur Berechnung des neuen Wertes ist durch den Operator selbst und den Ausdruck auf der rechten Seite festgelegt.

        Das Ergebnis des kompletten Ausdruckes ist der zugewiesene Wert mit dem entsprechenden Datentyp. 

    .. layer:: incremental

        Standardbeispiele:

        .. code:: java
            :class: far-smaller

            jshell> int age = 1;
            age ==> 1

            jshell> age = age + 1;
            age ==> 2

            jshell> age += 1;
            age ==> 3


        .. container:: incremental

            Folgendes wäre auch erlaubt, aber *nicht* empfehlenswert, da schwer(er) zu lesen:

            .. code:: java
                :class: far-smaller

                jshell> var newAge = age = age + 1;
                newAge ==> 4

                jshell> var newAge = age += 1;
                newAge ==> 5


    .. layer:: incremental

        .. csv-table::
            :header: Operator, Bedeutung
            :width: 100%
            :class: highlight-line-on-hover

            x = y, **Zuweisung des Wertes** von y an x
            x <Operator>= y, **Zuweisung des Wertes** von x <Operator> y an x

        Operatoren: ``+``, ``-``, ``*``, ``/``, ``%``, ``&``, ``|``, ``^``, ``<<``, ``>>``, ``>>>``

        Zum Beispiel: :java:`x <<= y` ist gleichbedeutend mit :java:`x = x << y`.



String Konkatenation (Verbinden von Zeichenketten)
----------------------------------------------------------


.. stack::

    .. layer::

        Literale, Variablen, Konstanten vom Datentyp String werden durch den Konkatenationsoperator + zu einem neuen String-Wert verkettet.

    .. layer::

        .. code:: java
            :class: far-smaller

            jshell> final String name = "Max";
            name ==> "Max"

            jshell> String greeting = "Hallo " + name + "!";
            greeting ==> "Hallo Max!"


Implizite Typkonvertierung
----------------------------------------------------------

Bei Zuweisungen und arithmetischen Operationen werden die Datentypen von Operanden unter bestimmten Umständen implizit konvertiert.

.. stack::

    .. layer::

        .. class:: incremental list-with-explanations

        - Bei arithmetischen Operationen erfolgt eine Konvertierung in den nächst größeren Datentyp der beteiligten Operanden bzgl. :java:`int`, :java:`long`, :java:`float`, :java:`double`.
        - Bei Operationen auf primitiven, ganzzahligen Datentypen wandelt der Compiler die beteiligten Operanden mindestens in :java:`int` um.
        - Bei Zuweisungen wird das Ergebnis des Ausdruckes auf der rechten Seite in den Datentyp der Variablen auf der linken Seite konvertiert gemäß der Regeln (`Konvertierung von Datentypen`_). 
  
          ⚠️ Die Typkonvertierung erfolgt unabhängig von den konkreten Werten der Operanden.

    .. layer:: incremental
        
        .. code:: java
            :class: far-smaller

            jshell> byte b = 13;
                    short s = Short.MAX_VALUE;
                    float f = b + s;

            b ==> 13
            s ==> 32767
            f ==> 32780.0

    .. layer:: incremental
        
        .. code:: java
            :class: far-smaller

            jshell> int r = Integer.MAX_VALUE + Integer.MAX_VALUE;
            r ==> -2

        .. warning::
            :class: far-smaller incremental

            Hier erfolgt keine Überlaufprüfung und demzufolge auch keine (implizite) Konvertierung (z. B. in Long).

        .. hint::
            :class: far-smaller incremental

            Bei der Addition von :java:`Integer.MAX_VALUE` und :java:`Integer.MAX_VALUE` wird der Wert :java:`-2` zurückgegeben, da der Wert :java:`Integer.MAX_VALUE + 1` den Wert :java:`Integer.MIN_VALUE` ergibt (wir haben einen Überlauf (:eng:`Overflow`)). 

            :java:`Integer.MAX_VALUE + Integer.MAX_VALUE` entspricht also :java:`Integer.MIN_VALUE + (Integer.MAX_VALUE - 1)`.

    .. layer:: incremental

        .. code:: java
            :class: far-smaller

            jshell> short s = Short.MAX_VALUE + Short.MAX_VALUE;
            |  Error:
            |  incompatible types: possible lossy conversion from int to short
            |  short s = Short.MAX_VALUE + Short.MAX_VALUE;



Explizite Typkonvertierung
----------------------------------------------------------

Das Ergebnis eines Ausdruckes kann durch explizite Typkonvertierung in einen anderen primitiven Datentyp umgewandelt werden.


.. stack::

    .. layer::

        .. class:: incremental

        - Bei primitiven Datentypen erlaubt für numerische Datentypen.
        - Wird ein ganzzahliges Ergebnis in einen kleineren ganzzahligen Datentyp konvertiert, dann werden die führenden Bits abgeschnitten.
        - Nachkommastellen gehen bei der Konvertierung von Gleitkommazahlen in Ganzzahlen verloren
        - Bei Konvertierung von :java:`double` in :java:`float` kommt es ebenfalls zu einem Genauigkeitsverlust in der Darstellung (durch Abschneiden der Bits in Mantisse und Exponent)

    .. layer:: incremental smaller

        .. rubric:: Standardfälle

        .. code:: java
            :class: far-smaller

            jshell> int i = 42;
            i ==> 42

            jshell> byte b = (byte) i;
            b ==> 42

        .. container:: incremental

            .. rubric:: Sonderfälle

            .. code:: java
                :class: far-smaller

                jshell> (byte) 128 ;
                $1 ==> -128

                jshell> (byte) 256 ; // Integer.numberOfTrailingZeros(256) == 8
                $2 ==> 0


Überlauf und Unterlauf
----------------------------------------------------------

Unter-/Überschreitet das Ergebnis eines Ausdruckes den minimalen/maximalen Wert des resultierenden Datentyps, erfolgt ein Unter-/Überlauf. (:eng:`Overflow`/:eng:`Underflow`)

.. class:: incremental

- Bei einem Unterlauf bzw. Überlauf werden bei Ganzzahlen die nicht mehr darstellbaren höheren Bits abgeschnitten.
- Bei Fließkommazahlen werden die Konstanten: :java:`Float.NEGATIVE_INFINITY` und :java:`Float.POSITIVE_INFINITY` bzw. :java:`Double.NEGATIVE_INFINITY` und :java:`Double.POSITIVE_INFINITY` verwendet.

.. code:: java
    :class: far-smaller incremental

    Integer.toBinaryString(Integer.MIN_VALUE) //    "10000000000000000000000000000000"
    Integer.toBinaryString(Integer.MIN_VALUE - 1) //"01111111111111111111111111111111"
    Long.toBinaryString(Integer.MIN_VALUE -1l)
                 // "1111111111111111111111111111111101111111111111111111111111111111"

.. supplemental::

    In der Praxis wird häufig der Begriff Overflow verwendet, wenn bei einer Berechnung der Wertebereich eines Datentyps nicht ausreicht, um das Ergebnis zu speichern. D. h. die Unterscheidung zwischen Über- und Unterlauf ist nicht immer eindeutig.

    Bei Double erfolgt der Überlauf erst, wenn man eine Zahl auf :java:`Double.MAX_VALUE` addiert, die mehr als 292 Stellen vor dem Komma hat.

    .. code:: java
        :class: far-smaller
    
        jshell> Double.MAX_VALUE + 1
        $0 ==> 1.7976931348623157E308

        jshell> Double.MAX_VALUE + 1999999999999999999999999
        9999999999999999999999999999999999999999999999999999
        9999999999999999999999999999999999999999999999999999
        9999999999999999999999999999999999999999999999999999
        9999999999999999999999999999999999999999999999999999
        9999999999999999999999999999999999999999999999999999
        9999999d // Eins gefolgt von 291 Neunen(!)
        $1 ==> 1.7976931348623157E308 // 16 Nachkommastellen

        jshell> Double.MAX_VALUE + 1000000000000000000000000
        0000000000000000000000000000000000000000000000000000
        0000000000000000000000000000000000000000000000000000
        0000000000000000000000000000000000000000000000000000
        0000000000000000000000000000000000000000000000000000
        0000000000000000000000000000000000000000000000000000
        00000000d // 1 gefolgt von 292 Nullen(!)
        $2 ==> Infinity



Auswertungsreihenfolge
----------------------------------------------------------

Die Auswertungsreihenfolge von komplexen Ausdrücken mit mehreren Operatoren wird durch die Priorität der Operatoren bestimmt.\ [#]_

.. class:: incremental list-with-explanations

- Kommen in einem Ausdruck mehrere Operatoren mit gleicher Priorität vor, dann wird der Ausdruck von links nach rechts ausgewertet.
- Ausnahmen sind die Verbund- und Zuweisungsoperatoren die von rechts nach links bewertet werden.
- Klammern haben die höchste Priorität und erzwingen die Auswertung des Ausdrucks in den Klammern zuerst. Klammern dienen aber (insbesondere) auch der Strukturierung von Ausdrücken.


.. [#] Die Regeln sind vergleichbar mit der Schulmathematik: Punkt-vor-Strich-Rechnung.


Priorität der Operatoren
----------------------------------------------------------

.. container:: scrollable

    .. csv-table::
        :header: Operatoren, Beschreibung, Priorität
        :class: highlight-line-on-hover incremental far-smaller
        :width: 100%
        
        "=, +=, -=, ...", Zuweisungs- und Verbundoperatoren, 1 (niedrigste)
        "?:", Bedingungsoperator, 2
        "||", Bedingt logisches ODER, 3
        "&&", Bedingt logisches UND, 4
        "\|", Logisches/Bitweises ODER, 5
        "^", Logisches/Bitweises  ENTWEDER-ODER, 6
        "&", Logisches/Bitweises  UND, 7
        "==, !=", "Vergleich: Gleich, Ungleich", 8
        "<, <=, >, >=", "Vergleich: Kleiner (oder Gleich), Größer (oder Gleich)", 9
        "<<, >>, >>>", Bitweise Schiebeoperatoren, 10
        "+, -", "Addition, Subtraktion, String-Konkatentation", 11
        "\*, /, %", "Multiplikation, Division, Rest", 12
        "++, --, +, - (Vorzeichen), ~, !, (cast)", Einstellige Operatoren, 13 (höchste)


Beispiele zur Auswertungsreihenfolge
----------------------------------------------------------


.. exercise:: Auswertung von Ausdrücken
    
    .. container:: far-smaller

        Sind die folgenden Ausdrücke (a) gültig und wie ist (b) ggf. das Ergebnis der folgenden Ausdrücke und (c) welchen Wert haben die Variablen nach der Auswertung (der neue Wert wird dann für den nachfolgenden Ausdruck verwendet)?

        *Initiale* Belegung der Variablen: :java:`int x = 4, y = 2, z = 3;`.

    .. code:: java
        :class: far-smaller incremental
        :number-lines: 1

        x + y * z / x 

    .. code:: java
        :class: far-smaller incremental 
        :number-lines: 2

        ( x + - (float) y * 2 ) / x  ==  ( x + ( ( (float) -y ) * 2 ) )/ x 

    .. code:: java
        :class: far-smaller incremental 
        :number-lines: 3

        x + ++y * z++ % x

    .. code:: java
        :class: far-smaller incremental 
        :number-lines: 4

        x < 5 && --y <= 1 || z == 3

    .. code:: java
        :class: far-smaller incremental
        :number-lines: 5

        x << 2 * y >> 1

    .. code:: java
        :class: far-smaller  incremental
        :number-lines: 6

        z & 1 % 2 == 0

    .. code:: java
        :class: far-smaller  incremental
        :number-lines: 7

        (z & 1) % 2 == 0

    .. solution::
        :pwd: WasIstDasErgebnis

        1. 5
        2. true
        3. 5 (nach Ausführung ist y = 3, z = 4)
        4. false (nach Ausführung ist y = 2)
        5. 32 (2*y = 4, 4 << 4 = 64, 64 >> 1 = 32)
        6. <ungültig> (== hat eine höhere Priorität als & und somit würde ein boolean Wert mit einer Zahl verglichen werden)
        7. true



.. class:: integrated-exercise transition-scale

Übung
--------    

.. exercise:: BMI berechnen

    Schreiben Sie ein Java Script, dass den Body-Mass-Index (BMI) berechnet. Lesen Sie das Gewicht in Kilogramm und die Größe in Metern von der Konsole ein und geben Sie den BMI auf der Konsole aus. Geben Sie auch aus, ob die Person Untergewicht, Normalgewicht oder Übergewicht hat. Falls die Person nicht das Normalgewicht hat, geben Sie auch an, wie viel Gewicht sie bis zum Normalgewicht zunehmen oder abnehmen muss.

    Der BMI wird nach folgender Formel berechnet: :math:`BMI = \frac{Gewicht}{Größe^2}`.

    Beispielinteraktion:

    .. code:: text
        :class: far-smaller
    
        Bitte geben Sie Ihr Gewicht in Kilogramm ein: 80
        Bitte geben Sie Ihre Größe in Metern ein: 1.80
        Ihr BMI beträgt 24.69
        Untergewicht: nein
        Normalgewicht: nein
        Übergewicht: 5.897499999999994 kg bis Normalgewicht

    .. solution::
        :pwd: BmiBerechnen

        .. include:: code/BmiBerechnen.java
            :code: java
            :class: far-smaller
            :number-lines:


.. supplemental::

    Denken Sie daran, dass Ihr Code in die ``main`` Methode gehört:

    .. code:: java
        :class: far-smaller

        void main() {
            // Ihr Code
        }

    Denken Sie daran, dass Sie einen Zeichenkette (``String``) in eine Zahl umwandeln können, indem Sie die Methode :java:`Double.parseDouble(<String>)` für Fließkommazahlen verwenden oder :java:`Integer.parseInt(<String>)` für Ganzzahlen.

    Schreiben Sie ein vollständiges Java Script, dass Sie mit dem Java Interpreter (``java --enable-preview <JAVA-DATEI>``) ausführen können.



.. class:: integrated-exercise transition-scale

Übung
--------    

.. exercise:: Umrechnung von Sekunden

    Schreiben Sie ein Java Script, dass die Anzahl von Sekunden in Stunden, Minuten und Sekunden umrechnet. Lesen Sie die Anzahl von Sekunden von der Konsole ein und geben Sie die Umrechnung auf der Konsole aus.

    Beispielinteraktion:

    .. code:: text
        :class: far-smaller
    
        Bitte geben Sie die Sekunden ein: 3455
        0 Stunde(n), 57 Minute(n) und 35 Sekunde(n)

    .. solution::
        :pwd: SekundenUmrechnen

        .. include:: code/SekundenUmrechnen.java
            :code: java
            :class: far-smaller
            :number-lines:



.. class:: transition-fade

Von Variablen, Konstanten, Literalen und Ausdrücken
-------------------------------------------------------

.. class:: incremental

- Variablen sind Speicherorte, die einen Wert enthalten.
- Konstanten sind unveränderliche Werte, die an einem Speicherort gespeichert sind.
- Literale sind konstante Werte, die direkt im Code stehen.
- Operatoren haben eine Priorität und bestimmen die Auswertungsreihenfolge von Ausdrücken.
- Ausdrücke sind Kombinationen von Variablen, Konstanten und Operatoren, die einen Wert ergeben.
- Implizite Typkonvertierung erfolgen automatisch und führen meist zu keinem Verlust von Genauigkeit.


.. class:: new-section transition-move-to-top

(Bedingte) Anweisungen, Schleifen und Blöcke
-------------------------------------------------------



Anweisungen
----------------------------------------------------------

Eine Anweisung in einem Java-Programm stellt eine einzelne Vorschrift dar, die während der Abarbeitung des Programms auszuführen ist.

.. class:: incremental

- In Java-Programmen werden einzelne Anweisungen durch einen Semikolon :java:`;` voneinander getrennt.

  .. code:: java
    :class: far-far-smaller

    void main() { 
        int a = 1; // Variablendeklaration und Initialisierung
        println("a = " + a); // Methodenaufruf (hier: println)
    }

- Programme setzen sich aus einer Abfolge von Anweisungen zusammen.
- Die einfachste Anweisung ist die leere Anweisung: :java:`;`.
- Weitere Beispiele für Anweisungen sind Variablendeklarationen und Initialisierungen, Zuweisungsausdrücke, Schleifen, Methoden-Aufrufe.


Blöcke
----------------------------------------------------------

Ein Block in einem Java-Programm ist eine Folge von Anweisungen, die durch geschweifte Klammern { ... } zusammengefasst werden.

.. class:: incremental

- Blöcke werden **nicht** durch einen Semikolon beendet.
  
  .. code:: java
    :class: far-far-smaller

    void main() {
        {   // Block von Anweisungen
            int a = 1;
            println("a = "+a);
        } 
    }

- Ein Block kann dort verwendet werden, wo auch eine Anweisung erlaubt ist.

- Ein Block stellt ein Gültigkeitsbereich (:eng:`scope`) für Variablendeklarationen dar. Auf die entsprechenden Variablen kann nur von innerhalb des Blocks zugegriffen werden.

- Leere Blöcke :java:`{}` sind erlaubt und Blöcke können verschachtelt werden.



Anweisungen und Blöcke - Beispiele
----------------------------------------------------------

.. code:: java
    :class: far-far-smaller

    // Deklaration und Initialisierung von Variablen
    int age = 18 + 1;
    char gender = 'm';

    ; // Leere Anweisung
    
    // Block
    {
        boolean vegi = true;
        gender = 'f';
        System.out.println("vegi=" + vegi);
        {} // leerer Block
    }
    
    // Methodenaufruf
    println("age=" + age);
    println("gender=" + gender);
    /* println("vegi=" + vegi); =>  Error: cannot find symbol: variable vegi */


Bedingte Anweisungen und Ausdrücke
----------------------------------------------------------

Bedingte Anweisungen und Ausdrücke in einem Java-Programm dienen dazu Anweisungen bzw. Blöcke nur dann auszuführen wenn eine logische Bedingung eintrifft.

.. class:: incremental

- Bedingte Anweisungen und Ausdrücke zählen zu den Befehlen zur Ablaufsteuerung.

- Bedingte Anweisungen und Ausdrücke können in Java-Programmen mittels :java:`if`-Anweisungen, :java:`if`-/:java:`else`-Anweisung und :java:`switch`-Anweisungen/-Ausdrücken umgesetzt werden.

- Der Bedingungs-Operator (:java:`<Ausdruck> ? <Ausdruck> : <Ausdruck>`) stellt in bestimmten Fällen eine Alternative zu den bedingten Anweisungen dar.


:java:`if`-Anweisung
----------------------------------------------------------

Die :java:`if`-Anweisung setzt sich zusammen aus dem Schlüsselwort :java:`if`, einem Prüf-Ausdruck in runden Klammern und einer Anweisung bzw. einem Block.

:Syntax: :java:`if(<Ausdruck>) <Anweisung> bzw. <Block>`

.. stack:: 

    .. layer:: 

        .. include:: code/SimpleIf.java
            :code: java
            :class: far-far-smaller
            :number-lines:

    .. layer:: incremental

        .. include:: code/NestedIf.java
            :code: java
            :class: far-far-smaller
            :number-lines:

.. supplemental::

    Der :java:`<Ausdruck>` muss einen Wert vom Datentyp :java:`boolean` zurückliefern

    Die :java:`<Anweisung>` bzw. der :java:`<Block>` wird ausgeführt, wenn der Ausdruck :java:`true` zurück liefert 

    Ansonsten wird die nächste Anweisung nach der :java:`if`-Anweisung ausgeführt

    :java:`if`-Anweisungen können verschachtelt werden (in der Anweisung bzw. im Block).



:java:`if`-:java:`else`-Anweisung
----------------------------------------------------------

.. stack::

    .. layer:: 

        .. include:: code/IfElse.java
            :code: java
            :class: far-smaller
            :number-lines:

    .. layer:: incremental

        .. include:: code/IfElseReformatted.java
            :code: java
            :class: far-smaller
            :number-lines:

.. supplemental::

    Die :java:`if`-Anweisung kann um einen :java:`else`-Zweig erweitert werden, der aus dem Schlüsselwort :java:`else` und einer Anweisung bzw. einem Block besteht.

    :Syntax: :java:`if(<Ausdruck>) <Anweisung bzw. Block> else <Anweisung bzw. Block>`

    Die :java:`<Anweisung>` bzw. der :java:`<Block>` im else-Zweig wird ausgeführt, wenn der Ausdruck in der :java:`if`-Anweisung :java:`false` zurück liefert.

    Im :java:`else`-Zweig kann wieder eine weitere :java:`if`-Anweisung verwendet werden (:java:`if` / :java:`else-if` Kaskade).

    Bei verschachtelten :java:`if`-Anweisungen gehört der :java:`else`-Zweig zur direkt vorhergehenden :java:`if`-Anweisung ohne :java:`else`-Zweig.



:java:`switch`-Anweisung/-Ausdruck (Grundlagen)
----------------------------------------------------------

.. stack::

    .. layer:: 

        Die :java:`switch`-Anweisung bzw. der :java:`switch`-Ausdruck setzt sich aus dem Schlüsselwort :java:`switch`, einem Prüf-Ausdruck in runden Klammern und einem oder mehreren :java:`case`-Blöcken zusammen.

        :Syntax: :java:`switch(<Ausdruck>) <case-Block>* [<default-Block>]`

        Im Gegensatz zur :java:`if`-\ :java:`else` Anweisung wird hier nur ein <Ausdruck> ausgewertet für den mehrere Alternativen (:java:`case`-Blöcke) angegeben werden können.

        .. container:: margin-top-1em

            Der :java:`default`-Zweig stellt eine Möglichkeit dar, die immer dann ausgeführt wird, wenn kein anderer :java:`case`-Block zutrifft 

            :Syntax: :java:`default: <Anweisungen>`

    .. layer:: incremental

        .. rubric::  :java:`case L :`

        :Syntax: :java:`case <Literal>: <Anweisungen>`.

        Ein :java:`case`\ -Block setzt sich zusammen aus dem Schlüsselwort :java:`case`, einem oder mehreren :java:`Literal`\ en (konstanter Ergebniswert) und einer Abfolge von Anweisungen.

        Die Anweisung in einem :java:`case :`-Block werden bis zur folgenden :java:`break`-Anweisung ausgeführt (:eng:`fall-through`).

        Gibt es keine :java:`break`-Anweisung in einem :java:`case`-Block werden alle Anweisungen bis zum Ende der :java:`switch`-Anweisung ausgeführt.

    .. layer:: incremental

        .. include:: code/Switch.java
            :code: java
            :class: far-smaller
            :number-lines:

    .. layer:: incremental

        .. include:: code/SwitchMultipleLabels.java
            :code: java
            :class: far-smaller
            :number-lines:
            
    .. layer:: incremental

        .. include:: code/SwitchYieldExpression.java
            :code: java
            :class: far-smaller
            :number-lines:


    .. layer:: incremental

        .. rubric:: :java:`case L ->`

        :Syntax: :java:`case <Literal> -> <Ausdruck oder Block>`.

        Auf der rechten Seite ist nur ein Ausdruck oder ein Block erlaubt - keine Anweisung. 

        Bei dieser Variante gibt es kein *durchfallen* :eng:`Fall-Through-Effekt`, d. h. ein :java:`break` ist nicht zur Beendigung eines :java:`case`-Blocks zu verwenden!


    .. layer:: incremental
    
        .. include:: code/SwitchArrowMultipleLabels.java
            :code: java
            :class: far-smaller
            :number-lines:

    .. layer:: incremental

        .. include:: code/SwitchArrowExpression.java
            :code: java
            :class: far-smaller
            :number-lines:




.. supplemental::

    Als Wert im :java:`case`-Block können Literale vom Datentyp :java:`int` und ab Java 7 auch :java:`String` und Aufzählungen (:java:`enum` Klassen) verwendet werden; ab Java 21 wird der Musterabgleich (:eng:`pattern matching`) unterstützt es können auch beliebige (sogenannte) Referenztypen (nicht nur :java:`String`) verwendet werden. Wir werden dies später bei der Diskussion von Referenztypen detailliert behandeln.

    :java:`case L ->` wird erst seit `Java 14 <https://openjdk.org/jeps/361>`__ unterstützt. Ein Mischen ist nicht möglich.

    switch-Anweisung ≘ :eng:`switch-statement`

    switch-Ausdruck ≘ :eng:`switch-expression`



:java:`switch`-Anweisung/-Ausdruck mit Musterabgleich und :java:`when` Bedingungen (seit Java 21)
---------------------------------------------------------------------------------------------------

.. stack::

    .. layer::

        Seit `Java 21 <https://openjdk.org/jeps/441>`__  werden auch :java:`case`-Label unterstützt, die Muster abgleichen (:eng:`match a pattern`), und die mit :java:`when`-Bedingungen kombiniert werden können.

        :Syntax: :java:`case <Pattern> when <Bedingung> -> <Ausdruck oder Block>`.

    .. layer:: incremental

        .. include:: code/SwitchAndWhen.java
            :code: java
            :class: far-smaller
            :number-lines:

        .. exercise:: Erfolgreicher Musterabgleich?
            :class: far-smaller

            Bei welchem Name wäre ein erfolgreicher Musterabgleich in mehreren Fällen möglich? 

            .. solution:: 
                :pwd: ErfolgreicherMusterabgleich

                Bei "Eva-Maria". 


.. supplemental::

    Wir werden Pattern Matching später detailliert behandeln.

        
Effizienz von bedingten Anweisungen
------------------------------------------

.. class:: incremental

- Bei :java:`if`-/\ :java:`else`-Anweisungen werden die Prüf-Ausdrücke sequentiell (in der angegebenen Reihenfolge) ausgewertet (ein Ausdruck pro Alternative).

- Bei :java:`switch`-Anweisungen/-Ausdrücken wird nur ein einziger Prüf-Ausdruck ausgewertet und die entsprechende(n) Alternative(n) direkt oder zumindest sehr effizient ausgeführt.

- Daher benötigt die Auswertung einer :java:`switch`-Anweisung i. d. R. weniger Rechenschritte als eine äquivalente :java:`if`-/\ :java:`else`-Anweisung.

        

.. class:: transition-fade integrated-exercise

Übung
--------

.. exercise:: Wochentag benennen

    Berechnen Sie den Wochentag für ein gegebenes Datum. 
    Lesen Sie (a) den Tag des Monats, (b) den Monat, (c) ob das Jahr ein Schaltjahr ist und (d) den Wochentag des 1. Januars des Jahres von der Konsole ein. Benutzen Sie die :java:`switch` und/oder :java:`if`-Anweisungen und geben Sie den Wochentag des gegebenen Datums auf der Konsole aus.

    Beispielinteraktion:

    .. code:: text
        :class: far-far-smaller
        
        # java --enable-preview Wochentag.java
        Welchen Monat haben wir (1-12)? 12
        Welchen Tag des Monats haben wir (1-28/29/30/31)? 24
        Welcher Wochentag war der 1. Januar (0=Montag, 1=Dienstag, ..., 6=Sonntag)? 0
        Ist das Jahr ein Schaltjahr (j/n)? j
        > Tag im Jahr: 359
        > Tag in der Woche: 2
        > Der 24.12. ist ein Dienstag

    .. solution::
        :pwd: Am241224IstDienstag

        .. include:: code/Wochentag.java
            :code: java
            :class: far-smaller
            :number-lines:

    .. container:: bonus-task far-far-smaller margin-top-1em

        Heimaufgabe: Erlauben Sie statt der Eingabe einer Zahl für den Wochentag auch die Eingabe des Wochentages als Text (z. B. "Montag", "Dienstag", ...).


.. class:: new-section transition-move-to-top

Schleifen
----------------------------------------------------------



Schleifen
----------------------------------------------------------

.. class:: incremental

- Schleifen dienen dazu gleiche Anweisungen bzw. Blöcke mehrfach auszuführen
- Schleifen zählen wie auch bedingte Anweisungen zu den Befehlen der Kontrollflußsteuerung
- Schleifen können in Java-Programmen mittels for-Anweisungen, while-Anweisung und do-while-Anweisungen umgesetzt werden
- Es muss darauf geachtet werden, dass keine Endlosschleifen entstehen



:java:`for`-Schleife
----------------------------------------------------------

.. stack::

    .. layer::

        .. code:: java
            :class: far-smaller
            :number-lines: 1

            int sum = 0;

            // for(<Init>; <Ausdruck>; <Update>) <Anweisung>
            for(int i = 0; i < 10 ; ++i ){
                sum += i;
                System.out.println("sum="+sum);
            }

    .. layer:: incremental

        Die :java:`for`-Schleife setzt sich zusammen aus einer Initialisierungsliste (<Init>), einer Abbruchbedingung <Ausdruck>, einer Änderungsliste (<Update>) und einen Schleifenrumpf (<Anweisung> bzw. <Block>). Alle drei Teile sind optional.

        :Syntax: ``for(<Init>;<Ausdruck>;<Update>) <Anweisung> bzw. <Block>``

        :Initialisierungsliste: wird vor dem ersten evtl. Schleifendurchlauf ausgeführt
        :Abbruchbedingung: wird vor jedem Schleifendurchlauf geprüft
        :Änderungsliste: wird nach einem Schleifendurchlauf ausgeführt

    .. layer:: incremental

        Sowohl die Initialisierungsliste als auch die Änderungsliste können mehrere Ausdrücke enthalten, die durch Kommas getrennt sind.
        
        Beispiel:

        .. container:: two-columns

            .. container:: column

                .. code:: java
                    :class: far-far-smaller
                    :number-lines: 1

                    int sum=0;


                    for(int i = 0, j = 2; i < 10; ++i, j+=2 ){
                        sum +=j;
                        System.out.println("sum="+sum);
                    }

            .. container:: column

                .. code:: java
                        :class: far-far-smaller
                        :number-lines: 1

                        int sum=0;
                        int i=0;
                        int j=2;
                        for(i++, j--; i < 10 ; ++i, j+=2 ){
                            sum +=j;
                            System.out.println("sum="+sum);
                        }


.. supplemental::

    Gültiger Code:

    .. code:: java
        :class: far-smaller

        for(;;) { System.out.println("forever"); }



:java:`while`-Schleife
----------------------------------------------------------

.. stack::

    .. layer::

        .. code:: java
            :class: far-smaller
            :number-lines: 1

            int sum = 0;
            int i = 0;
            
            // while(<Ausdruck>) <Anweisung oder Block>
            while(i < 10){
                sum += i;
                System.out.println("sum=" + sum);
                ++i;
            }

    .. layer:: incremental

        Die :java:`while`-Anweisung setzt sich zusammen aus einem <Ausdruck> als Abbruchbedingung und einen Schleifenrumpf (<Anweisung> bzw. <Block>).

        Die Abbruchbedingung wird vor jedem Schleifendurchlauf geprüft.

        :Syntax: while(<Ausdruck>) <Anweisung> bzw. <Block>


:java:`do`-:java:`while`-Schleife
----------------------------------------------------------

.. stack::

    .. layer:: 

        .. code:: java

            int sum=0;
            int i=0;
            
            // do <Anweisung> while (<Ausdruck>);
            do {
                sum += i++;
                System.out.println("sum="+sum);
            } while(i < 10);

    .. layer:: incremental

        .. class:: list-with-explanations 

        - Die :java:`do`-:java:`while`-Schleife setzt sich zusammen aus einem Schleifenrumpf (<Anweisung> bzw. <Block>) und einem <Ausdruck> als Abbruchbedingung.
    
        - Die Abbruchbedingung wird nach jedem Schleifendurchlauf geprüft.
    
          Im Gegensatz zur :java:`while`-Schleife wird der Schleifenrumpf mindestens einmal ausgeführt, bevor die Abbruchbedingung geprüft wird.



Kontrolle des Schleifenablaufs
----------------------------------------------------------

.. stack::

    .. layer:: 

        .. code:: java
            :class: far-smaller
            :number-lines: 1

            int sum = 0;

            for (int i = 0; i < 10; ++i) {
                if ((i + 1) % 5 == 0)
                    break;

                sum += i;
                System.out.println("i=" + i);
            }
            System.out.println("sum=" + sum);

    .. layer:: incremental

        .. code:: java
            :class: far-smaller
            :number-lines: 1

            int sum = 0;

            for (int i = 0; i < 10; ++i) {
                if ((i + 1) % 5 == 0)
                    continue;

                sum += i;
                System.out.println("i=" + i);
            }
            System.out.println("sum=" + sum);

    .. layer:: incremental

        .. code:: java
            :class: far-smaller
            :number-lines: 1
                
            int sum = 0;
             
            outer: for (int i = 0; i < 10; ++i) {
                System.out.println("i=" + i);

                for (int j = 0; j < i; ++j) {
                    System.out.println("j=" + j);
                    if ((j + 1) % 5 == 0)
                        break outer;
                    sum += j;
                }

                sum += i;
            }
            System.out.println("sum=" + sum);


    .. layer:: incremental

        - Mit den Anweisungen :java:`break`, :java:`break <Marke>`, :java:`continue` und :java:`continue <Marke>` kann die Abarbeitung einer Schleife beeinflusst werden.

        - Bei :java:`break` wird die Ausführung des aktuellen Schleifendurchlaufs abgebrochen und mit der Anweisung direkt nach dem Schleifenrumpf fortgefahren.

        - Bei :java:`continue` wird die Ausführung des aktuellen Schleifendurchlaufs abgebrochen und zum nächsten Schleifendurchlauf gesprungen.
      
        - :java:`break <Marke>` bricht auch die Ausführung des aktuellen Schleifendurchlaufs ab und es wird zur Anweisung nach einem Schleifenrumpf der Schleife mit der gegebenen Marke gesprungen.
        - Eine Marke setzt sich zusammen aus einem Java-Bezeichner und einem „:“ und kann vor einer Schleife bzw. einem Block stehen 

.. supplemental:: 

    *Corner Cases*

    .. code:: java

        jshell> farOuter: for (int j = 0 ; j < 5 ; j++) 
                    outer: for(int i = 0; i < 5; i++) { 
                        System.out.println(j+" "+i); break farOuter;
                    }
        0 0

        jshell> farOuter: for (int j = 0 ; j < 5 ; j++) 
                    outer: for(int i = 0; i < 5 ; i++) { 
                        System.out.println(j+" "+i); continue farOuter;
                    }
        0 0
        1 0
        2 0
        3 0
        4 0


.. class:: integrated-exercise transition-scale

Übung
--------

.. exercise:: Einfacher Primzahltest

    Verwenden Sie eine Schleife, um festzustellen ob eine Zahl eine Primzahl ist. Lesen Sie die Zahl von der Konsole ein. Geben Sie am Ende aus, ob die Zahl eine Primzahl ist oder nicht; geben Sie ggf. auch den kleinsten Teiler der Zahl aus.
    
    .. container:: minor smaller

        - Schreiben Sie den Code für den Java Interpreter.
        - Es ist nicht erforderlich, dass der Algorithmus effizient ist.

    Beispielinteraktion:

    .. code:: bash
        :class: far-smaller

        # java --enable-preview Primzahltest.java
        Geben Sie eine ganze positive Zahl ein? 97
        97 ist eine Primzahl
        # java --enable-preview Primzahltest.java
        Geben Sie eine ganze positive Zahl ein? 123
        3 ist ein Teiler von 123

    .. solution:: 
        :pwd: NaivUndIneffizient

        .. include:: code/Primzahltest.java
            :code: Java
            :class: smaller


.. class:: integrated-exercise transition-scale

Übung
--------

.. exercise:: Berechnung der Kubikwurzel mit Newton-Raphson

    Berechnen Sie die Kubikwurzel :math:`x` einer Zahl :math:`n` mit Hilfe einer Schleife. Nutzen Sie dazu das schnell konvergierende, iterative Verfahren von Newton-Raphson. 
    
    .. math:: 
        :class: smaller
        
        x_{n+1} = x_n - \frac{x_n^3 - n}{3 \times x_n^2}\qquad\qquad\text{ein mgl. Startwert:}\; x_0 = 1

    .. stack:: 

        .. layer::

            Beispielinteraktion:

            .. code:: bash
                :class: far-far-smaller

                # java --enable-preview KubikwurzelMitSchleife.java  10:43:30
                Geben Sie eine Zahl n ein deren Kubikwurzel w Sie berechnen wollen
                (d.h. n = w*w*w): 1000000
                Wie viele Schritte wollen Sie machen? 50
                ...
                Das Ergebnis ist: 100.0


        .. layer:: incremental

            .. container:: minor far-smaller

                Stellen Sie sich die folgenden Fragen:

                .. class:: list-with-explanations

                - Welchen Datentyp sollten Sie für die Kubikwurzeln verwenden?
                - Macht es Sinn die Anzahl der Iterationen zu begrenzen? 
            
                  (D. h. wie schnell konvergiert das Verfahren?)

                - Können Sie die Kubikwurzel von 2.251.748.274.470.911 (:java:`2251748274470911`) berechnen?

                - Wie kann man feststellen ob eine gute Näherung an die Kubikwurzel vorliegt?

    .. solution::
        :pwd: KubikwurzelnMitNewtonRaphson

        .. include:: code/KubikwurzelMitSchleife.java
            :code: Java
            :class: far-smaller

        Bei iterativen Algorithmen ist es fast immer möglich ein Abbruchkriterium über die Änderung zwischen zwei Schritten zu bestimmen. Unterschreitet die Änderung einen bestimmten Wert (z. B. :math:`0,000000000001`) , so kann bzw. könnte die Schleife je nach Anwendungszweck abgebrochen werden.

.. supplemental::  

    Um zu verstehen wie schnell der Algorithmus konvergiert können sie sich den aktuellen Wert :math:`x_n` ausgeben lassen. 

    Hausübung: Implementieren Sie den Algorithmus auch mit einem anderen Typ von Schleife.



.. class:: transition-fade new-section

Methoden
----------------------------------------------------------


Methoden (in Java Scripts)
----------------------------------------------------------

.. stack::

    .. layer::

        .. class:: incremental list-with-explanations

        - Methoden in Java-Programmen dienen dazu die Anwendungslogik zu strukturieren und in wiederverwendbare *Unterprogramme* zu zerlegen.

        - Methoden können von einer anderen Methode aufgerufen werden.

        - Eine Methode hat einen Namen, eine Parameterliste und einen Rückgabetyp.

          Methoden können bzw. müssen weiterhin deklarieren welche Ausnahmen auftreten können. Dies werden wir aber erst später behandeln.

        - Der Methodenrumpf ist eine Abfolge von Anweisungen bzw. Blöcken.
        
        .. class:: incremental

        :Syntax: 
            .. code:: java
        
                <Rückgabetyp> <Methodenname> (<Parameterliste>){
                    <Methoden-Rumpf>
                }

        .. class:: incremental

    .. layer:: incremental

        - Wir haben bereits Methoden wie :java:`println(String)` und :java:`double Double.parseDouble(String)` kennengelernt.
        - Wenn wir :java:`void main() { ... }` verwenden, dann definieren wir eine Methode, die von der Java-Laufzeitumgebung beim Start aufgerufen wird. 
   
           Per Konvention ist festgelegt, dass diese Methode :java:`main` heisst.

    .. layer:: incremental

        **Beispiel**
        
        Deklaration einer Methode zum Berechnen des größten gemeinsamen Teilers (:java:`ggt`) zweier Zahlen.

        .. code:: java
            :class: far-smaller
            :number-lines: 1

            int ggt(int z1, int z2) { // Algorithmus von Euklid
                z1 = Math.abs(z1);
                z2 = Math.abs(z2);

                int rest = 0;
                while (z2 != 0) {
                    rest = z1 % z2;
                    z1 = z2;
                    z2 = rest;
                }
                return z1; // return bestimmt welcher Wert zurückgegeben wird
            }

.. supplemental::

    Im :java:`ggtEuklid`-Beispiel werden die Parameter als normale Variablen behandelt. Dies wurde hier aus Kompaktheitsgründen so gewählt. Im Allgemeinen sollten die Parameter als Konstanten betrachtet werden, d. h. sie sollten nicht verändert werden.



Methodenparameter und Rückgabewerte
----------------------------------------------------------

- Die Parameterliste definiert über eine komma-separierte Liste die optionalen formalen Parameter der Methode:
  
  :Syntax: :java:`<Typ> <Bezeichner> (, <Typ> <Bezeichner>)*`

- Rückgabewerte werden im Methodenrumpf mit der return Anweisung zurückgegeben:
  
  :Syntax: :java:`return <Ausdruck>`

- Bei *Methoden ohne Rückgabewert* (:java:`void`) dient die leere :java:`return` Anweisung (:java:`return ;`) zum - ggf. vorzeitigem - Beenden der Methode. Am Ende der Methode ist in diesem Fall die :java:`return` Anweisung optional.



Methodenaufruf
----------------------------------------------------------

.. class:: incremental

- Der Aufruf erfolgt durch die Angabe des Klassennamens, des Methodennamens und der aktuellen Parameterwerte.

  :Syntax: <Methode>(<Parameterwerte>)

- Als aktuelle Parameterwerte können Variablen, Ausdrücke oder Literale übergeben werden.

- Der Datentyp des übergebenen Wertes muss in den Datentyp des formalen Parameters implizit konvertierbar sein. Andernfalls muss explizit konvertiert werden.

.. class:: incremental list-with-explanations   

- Von allen übergebenen Werten wird eine (ggf. flache) Kopie übergeben. 

  D. h. Änderungen an den Parametern innerhalb der Methode haben keine Auswirkungen auf die Werte der Argumente (Fachbegriff: :eng:`call-by-value`).

- Methoden dürfen sich selber aufrufen (**Rekursion**).


Rekursive Methoden - Beispiel
----------------------------------------------------------

.. container:: two-columns

    .. container:: column minor

        Schleifen basierte Implementierung des Algorithmus von Euklid:

        .. code:: java
            :class: smaller
            :number-lines: 1

            int ggt(int z1, int z2) { 
                int rest = 0;
                while (z2 != 0) {
                    rest = z1 % z2;
                    z1 = z2;
                    z2 = rest;
                }
                return z1; 
            }

    .. container:: column 

        Elegante rekursive Implementierung des Algorithmus von Euklid:

        .. code:: java
            :class: smaller
            
            int ggt(int z1, int z2) {
                if (z2 == 0)
                    return z1;
                else
                    return ggt(z2, z1 % z2);
            }


Überladen von Methoden (:eng:`Overloading`)
----------------------------------------------------------

.. stack::

    .. layer::

        .. class:: incremental            

        - Eine überladene Methode ist eine Methode mit dem gleichen Namen wie eine andere Methode, aber mit einer unterschiedlichen Parameterliste. Folgende Unterschiede sind möglich:

        - Eine Methode definiert eine unterschiedliche Anzahl von Parametern
        - Eine Methode hat unterschiedliche Datentypen für ihre formalen Parameter
        - :dhbw-red:`Unterschiedliche Rückgabetypen sind in Java nicht ausreichend.`
        - Zum Beispiel gibt es in Java die Methode :java:`int Math.max(int, int)` und :java:`double Math.max(double, double)`.
        
        .. exercise::  max(long, long)?
            :class: smaller incremental

            Warum definiert Java auch noch die Methode :java:`long Math.max(long, long)` bzw. warum reicht es nicht aus nur :java:`long Math.max(long, long)` zu definieren und auf :java:`int Math.max(int, int)` zu verzichten?

            .. solution::
                :pwd: WarumMaxLong?
                
                Würden wir nur :java:`long Math.max(long, long)` definieren, dann würde bei einem Aufruf mit zwei :java:`int` Werten eine implizite Konvertierung erfolgen. Der Rückgabewert wäre dann ein :java:`long` Wert. In den meisten Fällen ist dies jedoch nicht der gewünschte Typ und es müsste explizit in :java:`int` zurück konvertiert werden. Dies würde den Code unnötig komplizieren und auch (geringfügige) Performanceeinbußen verursachen. Daher ist es sinnvoll die Methode :java:`int Math.max(int, int)` zu definieren.

    .. layer:: incremental  

        .. code:: java
            :class: far-smaller

            void print(int i) {
                println("int: " + i);
            }

            void print(double d) {
                println("double: " + d);
            }

            void main() {
                int i = 1;
                print(i);
                float f = 1.0f;
                print(f);
            }



Aufruf von Methoden aus anderen Klassen
----------------------------------------------------------

.. class:: incremental

- *Für den Moment* ist eine Klasse für uns eine Sammlung von Methoden und Konstanten, die inhaltlich in einem logischen Zusammenhang stehen.

- Der Aufruf einer Methode einer Klasse erfolgt durch die Angabe des Klassennamens, des Methodennamens und der aktuellen Parameterwerte.
  
  :Syntax: <Klasse>.<Methode>(<Parameterwerte>)

- Wir haben bereits entsprechende Beispiele gesehen, z. B. :java:`Double.parseDouble(String)` oder :java:`Integer.parseInt(String)`.

.. hint::
    :class: incremental far-smaller margin-top-2em

    Auf diese Weise können nur statische Methoden aufgerufen werden. Die Details werden wir später beim Thema Klassen und Objekte behandeln.


.. class:: transition-fade integrated-exercise

Übung
--------

.. exercise:: Methoden definieren

    .. class:: list-with-explanations

    1) Nehmen Sie die Ergebnisse der letzten Übung und definieren Sie jeweils eine Methode für die Berechnung der :java:`Kubikwurzel` und für den Primzahltest. Die Methode :java:`isPrime` soll dabei den Rückgabetyp boolean haben. 

       Auf die Ausgabe des kleinsten Teilers beim Primzahltest soll verzichtet werden.

    2) Rufen Sie die Methoden aus Ihrer :java:`main`-Methode auf. Die :java:`main`-Methode soll dabei nur die Eingabe und die Ausgabe übernehmen.

    3) Wandeln Sie die Methode für die Berechnung der Kubikwurzel in eine rekursive Methode um.

      .. solution::
          :pwd: MethodenDefinieren

          **Primzahltest**

          .. include:: code/PrimzahltestInMethode.java
              :code: Java
              :class: far-smaller

          **Kubikwurzel**

          .. include:: code/KubikwurzelInRekursiverMethode.java
              :code: Java
              :class: far-smaller


.. class:: transition-move-left integrated-exercise

Übung
--------

.. exercise:: Fakultät berechnen

    Schreiben Sie eine nicht-rekursive Methode zur Berechnung der Fakultät einer Zahl. Lesen Sie die Zahl von der Konsole ein und geben Sie die Fakultät auf der Konsole aus.

    .. solution::
        :pwd: FakultaetBerechnen

        Verwendung von :java:`long` ermöglicht es die Fakultät von Zahlen bis einschließlich 20 zu berechnen.

        .. code:: java
            :class: far-smaller

            long fakultaet(long n) {
                long fak = 1;
                for (int i = 1; i <= n; ++i)
                    fak *= i;
                return fak;
            }

        Verwendung von :java:`double` ermöglicht es die Fakultät von Zahlen bis einschließlich 170 zu berechnen. Darüber hinaus wird :java:`Infinity` zurückgegeben.

        .. code:: java
            :class: far-smaller
  
            double fakultaet(int n) {
                double fak = 1;
                for (int i = 1; i <= n; ++i)
                    fak *= i;
                return fak;
            }

        

.. class:: transition-fade new-section

Von Codekonventionen und Lesbarkeit
----------------------------------------------------------


Lesbarer Java-Code
----------------------------------------------------------

.. class:: incremental list-with-explanations

- Halten Sie sich an die `Java-Konventionen <https://www.oracle.com/java/technologies/javase/codeconventions-contents.html>`__.
  
  (Die Konventionen haben sich - aus guten Gründen - seit Jahrzehnten nicht geändert.)

- Formatieren Sie Ihren Code konsistent; d. h. stellen Sie konsistente Einrückungen sicher! 
- Verwenden Sie inhaltsorientierte, sprechende Namen für Variablen, Konstanten, Methoden etc.

.. hint::
    :class: incremental 
    
    .. container:: center-child-elements

        Manuelles formatieren ist nicht sinnvoll. 
        
        **Verwenden Sie einen automatische Code-Formatter!**


Einrückungen und Blöcke
----------------------------------------------------------

- Rücken Sie zusammenhängende Blöcke um die gleiche Anzahl von Leerzeichen ein. 
  
  Gängig ist ein Vielfaches von :minor:`2 oder` 4 Leerzeichen.

- Verwenden Sie keine Tabulatoren (``\\t``) für Einrückungen.

- Beginnt ein neuer Block innerhalb eines äußeren Blockes, so werden die zugehörigen Anweisungen tiefer eingerückt als der äußere Block.
- Pro Zeile sollte nur ein Block oder eine Anweisung stehen.



Einrückungen und Blöcke - Beispiele
----------------------------------------------------------

.. stack:: 

    .. layer:: 

        .. rubric:: Falsche Einrückung

        .. code:: java
            :class: far-smaller
            
            int ggtNaiv(int z1, int z2){
                    int min = (z1>z2)?z2:z1; println("current min="+min);
                for(int ggt=min; ggt>1; --ggt){
                        if(z1%ggt==0 && z2%ggt==0)
                        return ggt;
                }     
            return 1;
                }
            }

    .. layer:: incremental

        .. rubric:: Korrekte Einrückung

        .. code:: java
            :class: far-smaller
            
            int ggtNaiv(int z1, int z2) {
                int min = (z1 > z2) ? z2 : z1;
                println("current min=" + min);
                for (int ggt = min; ggt > 1; --ggt) {
                    if (z1 % ggt == 0 && z2 % ggt == 0)
                        return ggt;
                }
                return 1;
            }



Klammern 
----------------------------------------------------------

.. class:: list-with-explanations incremental

- Verwenden Sie Klammern um Blöcke, auch wenn sie nur eine Anweisung enthalten.
  
  (Insbesondere bei verschachtelten Blöcken bzw. :java:`If`-Anweisungen ist dies wichtig.)
- Bei bedingten Anweisungen und  Schleifen steht die öffnende geschweifte Klammer am Ende der 1. Zeile. Die schließende geschweifte Klammer steht in einer eigenen Zeile am Ende. Sie hat die gleiche Einrückung wie die Anweisung.



.. class:: no-title center-child-elements

Zeilenlängen
----------------------------------------------------------

.. warning:: 
    :class: incremental

    Zeilen, die länger als 80 oder max. 100 Zeichen erfordern beim Lesen horizontales Scrollen und sind unter allen Umständen zu vermeiden!

    .. class:: incremental

        Konfigurieren Sie Ihren Editor so, dass Sie unmittelbar sehen, wenn eine Zeile zu lang wird.



Methoden und Kommentare
----------------------------------------------------------

.. container:: scrollable 
        
    .. class:: incremental list-with-explanations

    - Dokumentieren Sie Ihre Methoden und Klassen mit Javadoc-Kommentaren.
    - Dokumentieren Sie insbesondere die Vor- und Nachbedingungen von Methoden.
    - Dokumentieren Sie die Anforderungen an die Parameter. 
      
      Zum Beispiel: :java:`@param n die Zahl für die die Fakultät berechnet wird; n>=0 und n < 13`
    - Dokumentieren Sie die Rückgabewerte. 
  
      Zum Beispiel: :java:`@return die Fakultät von n`
    - Der erste Satz eines Javadoc-Kommentar sollte eine kurze, vollständige Beschreibung der Methode enthalten. Dieser wird in der Übersicht verwendet.
   
      Zum Beispiel: :java:`Berechnet die Fakultät einer Zahl n`
    - Dokumentieren Sie keine Trivialitäten 
     
      Zum Beispiel: :java:`i++; // erhöhe i um 1`
    - Wenn Sie einen Bedarf sehen, innerhalb einer Methode Kommentare zu schreiben, dann ist dies häufig ein Hinweis darauf, dass der Code refaktorisiert (:eng:`refactored`) werden sollte. 

      Zum Beispiel könnten die Methode in kleinere Methoden aufgeteilt werden.

    - Dokumentieren Sie insbesondere das, was nicht im Code steht und was nicht offensichtlich ist.
  
      KI Tools (zum Beispiel GitHub Copilot) sind bereits jetzt in der Lage gute *initiale* Kommentare zu generieren. Aber häufig fehlt die Dokumentation der (impliziten/globalen) Anforderungen sowie der Vor- und Nachbedingungen. 

.. supplemental::

    Woher könnte die Anforderung :java:`n < 13` für die Fakultät kommen?

    .. danach kommt es bei der Verwendung des :java:`int`-Datentyps zu einem Überlauf.


Team und Projektspezifische Konventionen
----------------------------------------------------------

- die Java-Konventionen sind allgemein gültig und sollten eingehalten werden, decken aber nicht alle Teile des Codes ab. 

.. class:: incremental

- In einem Team oder Projekt können spezifische Konventionen festgelegt werden, die über die allgemeinen Konventionen hinausgehen. Diese sollten dann **automatisiert überprüft und ggf. automatisch formatiert werden**.

- Beispiele sind:

  - Einrückungen der Parameter bei Methoden mit :ger-quote:`vielen` Parametern.
  - Sprache in der Variablen benannt werden. (z. B. Fachsprache in Englisch oder Deutsch)
  - Maximale Einrückungstiefe von Schleifen und Bedingungen.
  - ...


.. class:: integrated-exercise transition-scale

Übung
--------

.. class:: list-with-explanations

1) Überprüfen Sie den von Ihnen geschrieben Code auf korrekte Formatierung. 

2) Installieren Sie für VS Code das Java Extension Pack und verwenden Sie den eingebauten Code Formatter über die entsprechende Tastenkombination.
   
   (Auf Mac mit Standardeinstellungen zum Beispiel:  :kbd:`Shift` + :kbd:`Alt` + :kbd:`F`.)

3) Schreiben Sie für die Methoden passende Kommentare im Javadoc-Stil.

Am Ende diskutieren wir Ihren Code/Ihre Kommentare.


.. TODO schreiben Sie ein Programm, dass berechnet wie viele Tage ein Mensch bereits auf der Welt ist. Als Eingaben sollen das Geburtsdatum und das aktuelle Datum eingegeben werden. D.h. Sie fragen erst den Tag, dann den Monat und dann das Jahr der Geburt ab. Anschließend fragen Sie den aktuellen Tag, Monat und das Jahr ab. Geben Sie dann die Anzahl der Tage aus. Verwenden Sie Methoden für sinnvolle Teilaufgaben. (Z. B. Tage eines Jahres. Tag im Jahr (siehe vorhergehende Übung)).


.. class:: center-child-elements

Von Codekonventionen und Lesbarkeit - Zusammenfassung
----------------------------------------------------------


Auf dem Weg zu einem professionellen Programmierer (egal welcher Sprache) ist es wichtig, neben den Sprachkonstrukten auch die geltenden Konventionen zu erlernen und einzuhalten. Diese sind je nach Sprache meist leicht unterschiedlich, aber in der Regel sehr ähnlich.

Das Einhalten fördert die Zusammenarbeit mit anderen Programmieren - *insbesondere auch Ihrem zukünftigen Ich* - und erhöht die Lesbarkeit des Codes.