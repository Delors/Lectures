.. meta:: 
    :lang: de
    :author: Michael Eichberg
    :keywords: "Programmierung", "Java", "Objektorientierung", "Software Development"
    :description lang=de: Einführung in die Objekt-orientierte Programmierung mit Java
    :id: lecture-prog-oo
    :first-slide: last-viewed
    :exercises-master-password: WirklichSchwierig!
    
.. |html-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html
.. |pdf-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html.pdf
.. |at| unicode:: 0x40

.. role:: incremental
.. role:: appear
.. role:: eng
.. role:: ger
.. role:: red
.. role:: green
.. role:: the-blue
.. role:: minor
.. role:: obsolete
.. role:: line-above
.. role:: smaller
.. role:: far-smaller
.. role:: monospaced
.. role:: java(code)
   :language: java


.. class:: animated-symbol

Einführung in die Objekt-orientierte Programmierung
===========================================================

.. container:: line-above tiny

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
    :Kontakt: michael.eichberg@dhbw.de, Raum 149B
    :Version: 1.1

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


.. class:: new-section transition-move-to-top

Objekt-orientierte Programmierung mit Java
------------------------------------------------



Was ist Objektorientierte Programmierung?
------------------------------------------

:Definition: Objektorientierte Programmierung (OOP) ist ein Programmierparadigma, das auf den Konzepten von Klassen und **Objekten** basiert, die Daten und Funktionen kapseln.

.. class:: incremental

:Hauptziele:
  Code **erweiterbar**, **strukturierter** und wiederverwendbar gestalten.

.. class:: incremental 

:Hauptprinzipien:
  - **Kapselung** (:eng:`Encapsulation`)
  - **Abstraktion** (:eng:`Abstraction`)
  - **Vererbung** (:eng:`Inheritance`) :minor:`(nächster Foliensatz)`
  - **Polymorphie** (:eng:`Polymorphism`) („vielerlei Gestalt“) :minor:`(nächster Foliensatz)`

.. supplemental::

    Während es in der Anfangszeit Programmiersprachen gab, die neben der prozeduralen Programmierung insbesondere auch die objektorientierte Programmierung unterstützten, unterstützen heute fast alle Programmiersprachen auch weitere Paradigmen. Insbesondere die funktionale Programmierung.



Klassen
--------------------

.. container:: scrollable

    :Klasse: Ein Bauplan für Objekte, der beschreibt, welche Daten bzw. Attribute und Methoden ein Objekt haben kann.
    
    :Syntax: 
            .. code:: java
                
                <Modifikator>* class <Klassenname> { 
                  (<Attributdeklarationen>|<Methodendeklarationen>)*
                }

    .. class:: incremental

    **Beispiele:**
    
    .. container:: incremental

        .. rubric:: :java:`Auto` ist eine Klasse.

        .. code:: java
            :class: far-smaller copy-to-clipboard

            class Auto {
                // Attribute (gel. auch Felder (bzw. Fields) genannt)
                private String marke;
                private int geschwindigkeit; // _aktuelle_ Geschwindigkeit

                // Methoden
                void beschleunigen(int wert) {
                    geschwindigkeit += wert; // Zugriff auf das Attribut des Objektes
                }
            }

    .. container:: incremental margin-top-1em

        .. rubric:: :java:`Button` (bei der Modellierung grafischer Benutzeroberflächen) ist eine Klasse.

        .. code:: java
            :class: far-smaller copy-to-clipboard

            class Button {
                private String text;
                private int state; // 0: normal, 1: pressed, 2: disabled

                void registerListener() { ... }
            }

    .. container:: incremental margin-top-1em

        .. rubric:: :java:`BigDecimal` (zur Repräsentation von Dezimalzahlen mit „beliebiger“ Präzision) ist eine Klasse.

        .. code:: java
            :class: far-smaller copy-to-clipboard

            class BigDecimal {
                private int scale;
                private int precision;
                void add(BigDecimal b) { ... }
            }


    .. container:: incremental margin-top-1em

        .. rubric:: :java:`File` (zum Zugriff auf Dateien) ist eine Klasse.

        .. code:: java
            :class: far-smaller copy-to-clipboard

            class File {
                private String name;
                private long size;
                void read() { ... }
            }

    .. container:: incremental margin-top-1em box-shadow rounded-corners padding-1em margin-1em

        Klassen ermöglichen es uns über konkrete Objekte zu **abstrahieren**: Klassen sind eine Beschreibung vieler Objekte mit gleichen Eigenschaften und Verhalten.   

    .. container:: incremental margin-top-1em box-shadow rounded-corners padding-1em margin-1em 

        Durch die Verwendung von Sichtbarkeiten (insbesondere :java:`private` und ggf. :java:`protected`) ist der Zugriff auf die Attribute und Methoden einer Klasse von außen kontrollierbar. Wir sprechen hier von **Kapselung**. 
        
        :minor:`Die privaten Daten eines Objekts (und ggf. einiger Methoden) sind also geschützt und können nur über die Methoden der Klasse manipuliert werden. Dabei können alle Objekte einer Klasse auf die Attribute eines anderen Objektes der selben Klasse zugreifen.`
 

.. supplemental::

    Pro Java Datei (Datei mit der Endung .java), darf nur eine Klasse mit dem Modifikator :java:`public` enthalten sein. Die Datei muss den Namen der Klasse haben.



Objekte
--------------------------------------------------

Sichtweisen:

- Objekte sind Instanzen von Klassen, die durch den Bauplan der Klasse definiert sind und durch spezifische Werte der Attribute charakterisiert werden.
- Objekte haben eine Identität und einen konkreten, eigenen Zustand.



Objekterzeugung/Instanziierung einer Java Klasse
--------------------------------------------------

.. container:: scrollable

    Um ein Objekt zu erzeugen bzw. eine Klasse zu instanziiern, wird der :java:`new` Operator verwendet. Der :java:`new` Operator ...

    .. class:: incremental

    - reserviert den benötigten Speicher für die Attribute, und stellt sicher, dass alle Attribute mit dem Defaultwert initialisiert sind.
    
    .. class:: incremental

    - ruft dann den *Konstruktor* der Klasse auf. 

      Der Konstruktor ist eine spezielle Methode, die einmalig beim Erzeugen eines Objekts aufgerufen wird und der Initialisierung des Objekts dient.
    

    .. class:: incremental

    :Syntax: :java:`new <Klassenname>(<Parameter>)`

    .. class:: incremental

    :Beispiel: 

        `meinAuto` referenziert ein Objekt der Klasse `Auto`. 

        .. code:: java
            :class: far-smaller faded-to-white copy-to-clipboard

            class Auto {
                String marke;           // der Standardwert ist null
                int geschwindigkeit;    // der Standardwert ist 0

                void beschleunigen(int wert) { ... }
            }

        .. code:: java
            :class: far-smaller copy-to-clipboard

            var meinAuto = new Auto(); // Aufruf des impliziten Konstruktors
        

.. supplemental::

    Der Konstruktor ist eine spezielle Methode, die nur beim Erzeugen eines Objekts aufgerufen wird. Wird kein Konstruktor explizit definiert, wird ein (impliziter) Standardkonstruktor verwendet.

    Der implizite Konstruktor ist ein Konstruktor, der automatisch vom Java compiler generiert wird, wenn kein Konstruktor explizit definiert wurde. Der implizite Konstruktor hat keine Parameter und initialisiert die Attribute mit Standardwerten.



Variablen, die auf Objekte verweisen
--------------------------------------------------

.. stack::

    .. layer::

         :Beispiel:

            Deklaration und Initialisierung einer Objektvariablen.

            .. code:: java
                :class: far-smaller faded-to-white copy-to-clipboard

                class Rectangle {
                    int width;  
                    int height; 
                    int x;
                    int y;
                }

            .. code:: java
                :class: far-smaller copy-to-clipboard

                Rectangle a = new Rectangle(); 

            bzw. 

            .. code:: java
                :class: far-smaller copy-to-clipboard

                var b = new Rectangle();  

    .. layer:: incremental

        - Objektvariablen sind *Referenzvariablen* und werden durch den Klassennamen gefolgt von einem Variablennamen deklariert.
        
          .. class:: incremental

          :Syntax: :java:`<Klassenname> <Variablenname>`

        .. class:: incremental

        - Die Referenzvariable speichert eine Referenz (wie bei Feldern/Arrays) auf ein Objekt der Klasse.
        - Bei Objekten gelten die gleichen Regeln beim Kopieren und Vergleichen wie bei Arrays (z. B. bzgl. des :java:`==` Operators).
        - Objektvariablen können überall dort deklariert werden, wo auch andere Variablen (für primitive Datentypen) deklariert werden können.
        - Wie bei Arrays ist der Standardwert für Objektvariablen :java:`null` und bedeutet, dass diese Variable auf *kein* Objekt verweist. 
       

    .. layer:: incremental

        - Der Typ der Variablen ist durch die Klasse bestimmt. 
        - Der Name des Typs ist somit der Klassenname.
        - Überall, wo ein primitiver Typ verwendet werden kann, kann auch der Typ eines Objektes verwendet werden.
  
        .. class:: incremental
        
        :Beispiel:
        
            .. code:: java
                :class: far-smaller copy-to-clipboard

                boolean compare(Rectangle a, Rectangle b) { } // Parameter
                Person copy(Person a) { }                     // Rückgabetyp
                double sum(Rectangle[] rectangles) { }        // Arrays
                double intersect(Circle... circles) { }       // Varags
                class Student { private String s; }           // Attribute

                Car c = new Car(); 
                Rectangle[] rs = new Rectangle[10];
                Rectangle[] rs = new Rectangle[]{new Rectangle(), new Rectangle()};


    .. layer:: incremental

        .. rubric:: Exemplarische Speicherbelegung

        .. code:: java
            :class: far-smaller copy-to-clipboard

            Rectangle a = new Rectangle(); 
            Rectangle b = a;

        .. image:: images/objekte.svg
            :alt: Objekte im Speicher
            :width: 100%
            :align: center
            :class: margin-top-1em



Bereinigung von Objekten
-------------------------

Verweist keine Referenzvariable mehr auf ein Objekt im Speicher, dann wird es automatisch vom *Garbage Collector* aus dem Speicher entfernt, d. h. der Entwickler muss sich nicht explizit um die Speicherbereinigung kümmern. 

Es ist insbesondere nicht notwendig, Referenzvariablen auf :java:`null` zu setzen.

.. container:: incremental bold box-shadow rounded-corners padding-1em margin-1em dhbw-red-background white

    Java unterstützt automatische *Garbage Collection*.



Objekte und die Selbstreferenz `this`
------------------------------------------

.. container:: scrollable

    :Objekt: Eine Instanz einer Klasse.

    :Definition: :java:`this` ist eine Referenz auf das aktuelle Objekt. Es wird verwendet, um auf die Attribute und Methoden des aktuellen Objekts zuzugreifen. 
    
    .. class:: incremental

    :Beispiel: 

        .. include:: code/Auto.java
            :code: java
            :class: far-smaller copy-to-clipboard
            :end-before: void main() {

.. supplemental::

    Wenn es keine Zweideutigkeit gibt, dann kann auf die Angabe von :java:`this` verzichtet werden.
    (Lokale Parameter und Variable überschatten ggf. Attribute der Klasse mit dem gleichen Namen.)



Explizite Konstruktoren
---------------------------------------------------------

.. stack::

    .. layer::

        Ein Konstruktor hat immer den Namen der Klasse und kann Parameter enthalten. Ein Konstruktor hat keinen Rückgabewert. 

        :Syntax: :java:`<Klassenname>(<Parameter>) { ... }`

    .. layer:: incremental

        :Beispiel: 

            .. code:: java
                :class: far-smaller copy-to-clipboard

                class Auto {
                    String marke;           // der Standardwert ist null
                    int geschwindigkeit;    // der Standardwert ist 0

                    Auto(String marke, int geschwindigkeit) {
                        // ⚠️ "this." ist notwendig, 
                        //   zur Unterscheidung von Parameter und Attribut
                        this.marke = marke;                     
                        this.geschwindigkeit = geschwindigkeit;notwendig!
                    }
                }

            .. code:: java
                :class: far-smaller incremental copy-to-clipboard

                var meinAuto = new Auto("BMW",0); // Aufruf des Konstruktors

    .. layer:: incremental

        Ein Konstruktor kann auch andere Konstruktoren der Klasse aufrufen.  Der „Methodenname“ der anderen Konstruktoren ist in diesem Fall :java:`this`.

        :Beispiel: 

            .. code:: java
                :class: far-smaller copy-to-clipboard

                class Auto {
                    String marke;           
                    int geschwindigkeit;    

                    Auto(String marke) { this.marke = marke; }

                    Auto(String marke, int geschwindigkeit) {
                        this(marke); // Aufruf des anderen Konstruktors

                        this.geschwindigkeit = geschwindigkeit;
                    }
                }

            .. code:: java
                :class: far-smaller incremental copy-to-clipboard

                void main() {  new Auto("VW", 0); }

    .. layer:: incremental

        .. attention::

            Die Anzahl der Dinge, die vor dem Aufruf eines Konstruktors gemacht werden können, ist sehr begrenzt, damit eine konsistente Initialisierung gewährleistet ist.

            Es ist zum Beispiel nicht möglich andere Methoden des Objekts aufzurufen (d. h. Aufrufe auf :java:`this` sind nicht möglich).

        .. hint:: 
            :class: incremental

            Erst seit Java 22 ist es überhaupt möglich, dass vor dem Aufruf eines anderen Konstruktors Code ausgeführt werden darf.

    .. layer:: incremental

        :Beispiel (seit Java 22):

            .. code:: java
                :class: far-smaller copy-to-clipboard

                class Auto {
                    String marke; int geschwindigkeit;    

                    Auto(String marke) { this.marke = marke; }

                    Auto(String marke, int geschwindigkeit) {
                        if (geschwindigkeit < 0) 
                            throw new IllegalArgumentException("Geschw. < 0");
                        this.geschwindigkeit = geschwindigkeit;
                        this(marke); // Aufruf des anderen Konstruktors
                    }
                }


       


    .. layer:: incremental

        .. rubric:: Initialisierungsfolge

        1. Initialisierung der Attribute mit Standardwerten
      
        .. class:: incremental list-with-explanations

        2. Aufruf des Konstruktors des expliziten Konstruktors, wenn angegeben sonst des impliziten Konstruktors.
     
           (Dies führt ggf. zu weiteren Konstruktoraufrufen.)   





Verwendung eines Objektes
--------------------------------------------------

Auf sichtbare Attribute und Methoden eines beliebigen Objektes kann über den **Punktoperator** zugegriffen werden.

:Syntax: :java:`<Objektinstanz>.<Attribut/Methode>`

:Beispiel: 

    `meinAuto` referenziert ein Objekt der Klasse `Auto`. 


    .. code:: java
        :class: far-smaller faded-to-white

        class Auto {
            String marke;
            int geschwindigkeit;
            void beschleunigen(int wert) { ... }
        }

    .. code:: java
        :class: far-smaller

        var meinAuto = new Auto();
        meinAuto.marke = "BMW";
        meinAuto.beschleunigen(10);



Kapselung (:eng:`Encapsulation`)\ [#]_
--------------------------------------------------

:Ziel: 
   Daten eines Objekts vor direktem Zugriff von außen schützen.

   Best Practice: Zugriff auf Daten erfolgt über öffentliche **Getter** (Methoden, die mit :java:`get` anfangen) und **Setter** (Methoden, die mit :java:`set` anfangen). Alle Attribute (außer Konstanten) sollten **privat** sein.

.. class:: incremental

:Vorteile:

    - Schutz der Datenintegrität
    - Kontrollierter Zugriff auf die Daten; fördert die Wartbarkeit

    .. code:: java 
        :class: far-smaller copy-to-clipboard

        class Auto {
            private int geschwindigkeit;
            public int getGeschwindigkeit() { return geschwindigkeit; }
            public void setGeschwindigkeit(int geschwindigkeit) {
                if (geschwindigkeit >= 0) {
                    this.geschwindigkeit = geschwindigkeit;
        }   }   }

.. [#] Kapselung dient vor allem dem Programming-in-the-Large. 


.. supplemental::

    .. attention::

        Die Benennung von Gettern und Setter - wie dargestellt - ist umbedingt einzuhalten. Dies ist eine so etablierte Konvention, dass sie in den meisten modernen IDEs und vielen Tools automatisch unterstützt wird und auch von erweiterten Sprachkonstrukten genutzt wird.

    Die Unterstützung von Sichtbarkeitskonzepten variieren von Programmiersprache zu Programmiersprache sehr stark. Sprachen wie zum Beispiel Python bieten diesbezüglich zum Beispiel deutlich weniger oder gar keine Konzepte. Obwohl fast alle Sprachen zumindest grundlegende Mechanismen für die Unterscheidung zwischen privaten und öffentlichen Daten und Methoden bieten. Sprachen wie Scala bieten jedoch noch weit ausgefeiltere Konzepte.



.. class:: integrated-exercise transition-move-to-top

Übung
--------------------------------------------------

.. exercise:: Bibliothek - Grundgerüst

    Entwickeln Sie die Klassen :java:`Buch`, :java:`Exemplar`, :java:`Benutzer`, :java:`Bibliothek`.

    .. container:: dd-margin-left-8em

        Die Klassen haben die folgenden Attribute:

        :Bücher: Titel, ISBN, Jahr, Autoren
        :Exemplare: Exemplar-Nummer, Regal, Position
        :Benutzer: Benutzer-Nummer, Vorname, Nachname
        :Bibliotheken: Name der zugehörigen Institution, Standort 

        Es soll weiterhin gelten:

        - Ein Buch hat max. 10 Exemplare.
        - Ein Exemplar kann durch max. einen Benutzer ausgeliehen sein.
        - Eine Bibliothek hat max. 100 Bücher und max. 20 Benutzer.
  
    .. solution::
        :pwd: Bibliothek_v1

        .. rubric:: Benutzer.java

        .. include:: code/bibliothek_v1/Benutzer.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

        .. rubric:: Bibliothek.java

        .. include:: code/bibliothek_v1/Bibliothek.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

        .. rubric:: Buch.java

        .. include:: code/bibliothek_v1/Buch.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

        .. rubric:: Exemplar.java

        .. include:: code/bibliothek_v1/Exemplar.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard



.. class:: integrated-exercise transition-move-to-top

Übung
--------------------------------------------------

.. exercise:: Bibliothek 

    - Entwickeln Sie Konstruktoren und folgende Methoden für die jeweiligen Klassen: 

      .. class:: dd-margin-left-8em

      :Buch: :java:`addExemplar(Exemplar ex, int nr)`, um ein Exemplar hinzuzufügen
      :Exemplar: :java:`verleihe(Benutzer b)`, um ein Buch auszuleihen
      :Biblitohek: :java:`addBenutzer(Benutzer b)`, :java:`addBuch(Buch b)`
      :Alle Klassen: :java:`print()`, um alle Attribute auf der Kommandozeile auszugeben

    - Entwickeln Sie eine :java:`main()`-Methode, die eine Bibliothek der DHBW Mannheim erzeugt mit dem Standort Coblitzallee. Der Bibliothek sollen mindestens zwei Bücher und zwei Benutzer und jedem Buch mindestens ein Exemplar zugeordnet werden. Jeweils ein Exemplar ist an einen Benutzer verliehen.

      Abschließend soll die :java:`main()`-Methode alle Informationen der Bibliothek über die Kommandozeile ausgeben.

    .. solution::
        :pwd: BIBILIOTHEK_v2

        .. rubric:: Benutzer.java

        .. include:: code/bibliothek_v2/Benutzer.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

        .. rubric:: Bibliothek.java

        .. include:: code/bibliothek_v2/Bibliothek.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

        .. rubric:: Buch.java

        .. include:: code/bibliothek_v2/Buch.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

        .. rubric:: Exemplar.java

        .. include:: code/bibliothek_v2/Exemplar.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

.. supplemental::

    Fehlerbehandlung und Validierung der Eingaben sind *noch nicht* notwendig.


.. class:: integrated-exercise transition-move-to-top

Übung
--------------------------------------------------

.. exercise:: Patientenakte - die Klasse Patient

    - Die Klasse Patient hat folgende Attribute das Geburtsdatum (:java:`String geburtsdatum`), einen :java:`Namen (String name)`, ein Gewicht in Kilogramm (:java:`double gewicht`) und eine Größe in Zentimetern (:java:`int groesse`). 
    - Definieren Sie einen Konstruktor, der es ermöglicht einen Patienten wie folgt zu erzeugen: :java:`new Patient("24.12.2024", "Max Müller", 180, 80d)`

    - In einer (externen) :java:`main` Methode:
    
      - Legen Sie mehrere Patienten an und speichern Sie diese in einem Array :java:`patienten`.
      - Schreiben Sie eine Methode, die die Durchschnittsgröße aller Patienten berechnet. Rufen Sie die Methode auf und geben Sie das Ergebnis aus. 

    .. solution::
        :pwd: Patientenakte

        .. rubric:: Patient.java

        .. include:: code/patientenakte_v1/Patient.java
            :code: java
            :number-lines:
            :class: far-smaller

        .. rubric:: Main.java

        .. include:: code/patientenakte_v1/Main.java
            :code: java
            :number-lines:
            :class: far-smaller      

.. supplemental::

    Achten Sie ggf. auf die Datentypen bei den Berechnungen.



.. class:: integrated-exercise transition-move-to-top

Übung
--------------------------------------------------

.. exercise:: Patient mit Getter und Setter Methoden

    .. class:: list-with-explanations

    - Machen Sie alle Attribute der Klasse ``Patient`` :java:`private`.
    - Implementieren Sie für jedes Attribut eine Getter Methode. 
  
      D. h. eine Methode, die einfach den Wert des Attributs direkt zurückgibt. 
    - Passen Sie Ihre Main-Methode entsprechend an.

    .. solution::
        :pwd: es_wird_besser

        .. rubric:: Patient.java

        .. include:: code/patientenakte_v2/Patient.java
            :code: java
            :number-lines:
            :class: far-smaller

        .. rubric:: Main.java

        .. include:: code/patientenakte_v2/Main.java
            :code: java
            :number-lines:
            :class: far-smaller 

.. supplemental::

    **Zur Erinnerung**

    Ein Getter fängt immer mit :java:`get` an und hat den Namen des Attributs als Suffix (z. B. :java:`getGeburtsdatum`).



.. class:: integrated-exercise transition-move-to-top

Übung
--------------------------------------------------

.. exercise:: Patient und Arzt

    .. class:: list-with-explanations

    - Legen Sie eine Klasse Arzt an, die ein privates Attribut vom Typ Array („Feld“) von Patienten hat.
    - Fügen Sie der Klasse Arzt eine Methode :java:`addPatient` hinzu, die ein neues Array erzeugt, das alle bisherigen Patienten des Arztes und den neuen Patienten enthält. Stellen Sie sicher, dass der Patient nur einmal hinzugefügt wird. Sollte der Patient schon in der Liste sein, dann passiert nichts. 
    
      (Gehen Sie für diese Aufgabe davon aus, dass jeder echte Patient immer nur durch genau ein Objekt repräsentiert wird.)
    - Verschieben Sie die Methode zur Berechnung der Durschnittsgröße in die Klasse Arzt, um die Durschnittsgröße aller Patienten des Arztes zu berechnen.

    .. solution::
        :pwd: es*wird+#besser

        .. rubric:: Patient.java

        **Unverändert gegenüber vorheriger Lösung.**

        .. rubric:: Arzt.java

        .. include:: code/patientenakte_v3/Arzt.java
            :code: java
            :number-lines:
            :class: far-smaller

        .. rubric:: Main.java

        .. include:: code/patientenakte_v3/Main.java
            :code: java
            :number-lines:
            :class: far-smaller 

.. supplemental::

    Sie können ggf. zum Vergrößern des Arrays die Methode :java:`java.util.Arrays.copyOf` verwenden.



