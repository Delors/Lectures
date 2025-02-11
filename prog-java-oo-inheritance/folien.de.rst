.. meta::
    :version: genesis
    :lang: de
    :author: Michael Eichberg
    :keywords: "Programmierung", "Java", "Objektorientierung", "Vererbung", "Polymorphie"
    :description lang=de: Objekt-orientierte Programmierung mit Java - Vererbung und Polymorphie
    :id: lecture-prog-oo-inheritance
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!
    
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
.. role:: dhbw-red    
.. role:: smaller
.. role:: far-smaller
.. role:: monospaced
.. role:: java(code)
   :language: java



.. class:: animated-symbol

Objekt-orientierte Programmierung - Vererbung und Polymorphie
==============================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0.1

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

Objektorientierte Programmierung mit Java - Vererbung und Polymorphie
------------------------------------------------------------------------



Generalisierung und Spezialisierung
------------------------------------------

.. container:: scrollable

    .. rubric:: Implementierung einer Raumverwaltung

    .. container:: two-columns smaller

        .. container:: column

            .. rubric:: Ein Vorlesungsraum an der DHBW

            .. code:: java
                :class: far-far-smaller copy-to-clipboard

                public class Vorlesungsraum {
                    private int zustand; // 0: nutzbar, 
                                         // 1: zu renovieren
                    private String gebaeude;
                    private int ebene;
                    private int raumNummer;
                    private int kapazitaet;
                    private boolean klimatisiert;
                    private Ausstattung[] ausstattung;  

                    public void setzeZustand(int zustand)...    
                    public void setzeEbene(int ebene)...
                    public void setzeRaum(int raumNr)...
                    public void generiereBeschreibung()...

                    public void reserviere()...
                }

        .. container:: column incremental

            .. rubric:: Eine Teeküche an der DHBW

            .. code:: java
                :class: far-far-smaller copy-to-clipboard

                public class Teekueche {
                    private int zustand; // 0: nutzbar, 
                                         // 1: zu renovieren
                    private String gebaeude;
                    private int ebene;
                    private int raumNummer;
                    private Kuechgeraete[] geraete;



                    public void setzeZustand(int zustand)...    
                    public void setzeEbene(int ebene)...
                    public void setzeRaum(int raumNr)...
                    public void generiereBeschreibung()...

                    public void setzeSchliessberechtigung()...
                }

    .. container:: incremental margin-top-1em

        .. rubric:: Identifikation der Gemeinsamkeiten und Modellierung einer allgemeinen Klasse

        .. image:: images/raumverwaltung.svg
            :alt: Raumverwaltung
            :width: 80%
            :align: center
            :class: margin-top-1em

    .. container:: incremental margin-top-1em

        Klassen können durch eine **Vererbungshierachie** in *Oberklassen* (*Superklassen*) (hier: :java:`Raum`) und *Unterklassen* (Subklassen) (hier: :java:`Vorlesungsraum`, :java:`Buero`, :java:`Teekueche`, ...) eingeteilt werden.


.. supplemental::

    Unterklassen *spezialisieren* eine Oberklasse: Die Oberklasse definiert gemeinsame Attribute und Methoden. Eine Unterklasse kann neue Attribute und Methoden hinzufügen bzw. überschreiben. Dabei ist darauf zu achten, dass die Unterklasse sich verhaltenskonform zur Oberklasse verhält.



Vererbung (:eng:`Inheritance`)
--------------------------------------------------

.. stack::

    .. layer:: 

        :Definition: Erlaubt es, eine Klasse von einer anderen abzuleiten und deren Eigenschaften und Methoden zu erben.
        :Vorteile:
            - Wiederverwendbarkeit des Codes
            - Hierarchische Strukturierung
        
    .. layer:: incremental

        Klassen werden in Vererbungshierachien eingeteilt.
        
        :Syntax:

            .. code:: java
                :class: far-smaller 

                public class <Subklassenname> 
                        extends <Superklassenname> { ...
                }

        .. class:: incremental

        :Beispiel:

            .. code:: java
                :class: far-smaller copy-to-clipboard

                class Auto {                            // Basisklasse
                    String marke;
                    void fahren() { System.out.println("Das Auto fährt."); }
                }

                class Elektroauto extends Auto {        // Abgeleitete Klasse
                    int batteriestand;
                    void aufladen() {
                        System.out.println("Das Elektroauto wird aufgeladen.");
                }   }

    .. layer:: incremental

        - Eine Unter- bzw. Subklasse erbt alle Attribute und Methoden der Super- bzw. Oberklasse. 
      
        .. class:: incremental

        - Auf :java:`public` und :java:`protected` Attribute und Methoden der Superklassen kann direkt zugegriffen werden.
        - Auf :java:`private` Attribute und Methoden kann nicht zugegriffen werden (zBei Attributen ggf. nur über entsprechende :java:`get`- und :java:`set`-Methoden)
        - Zyklen in der Vererbungshierarchie sind nicht erlaubt
  
    .. layer:: incremental

        .. rubric:: Zugriff auf Methoden und Attribute von Superklassen

        Mittels :java:`super` ist der direkte Zugriff auf die Attribute und Methoden der Superklasse (wenn diese :java:`protected` oder :java:`public` sind) möglich.

        - Dies ist notwendig, wenn die Vaterklasse Attribute bzw. Methoden mit gleichem Namen enthält (ansonsten kann man :java:`super` auch weglassen).
        
    .. layer:: incremental

        .. rubric:: Verwendung von :java:`super` für Aufruf der Methode der Superklasse

        .. include:: code/super/Main.java
            :code: java
            :class: far-smaller copy-to-clipboard
            :number-lines:
            :end-before: void main() {


    .. layer:: incremental

        .. rubric:: Initialisierung von Superklassen
        
        - Wird ein Objekt erzeugt (mittels :java:`new`), so wird automatisch auch Speicher für die Attribute der Superklasse reserviert und initialisiert.

        - Mittels eines :java:`super(...)` Aufrufs ist es möglich einen bestimmten Konstruktor der Superklasse (innerhalb des Konstruktors der Subklasse) aufzurufen.
        
        - Ruft der Konstruktor nicht explizit einen Konstruktor mit :java:`super(...)` auf, dann wird der parameterlose Konstruktor :java:`super()` implizit aufgerufen, wenn keiner explizit definiert wurde.

        - Die Initialisierung startet immer bei der Superklasse und arbeitet sich dann rekursiv durch die Vererbungshierarchie nach unten.

    .. layer:: incremental

        .. rubric:: Verwendung von :java:`super` während der Initialisierung

        .. include:: code/Super.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard
            :end-before: void main()


    .. layer:: incremental

        .. rubric:: :java:`java.lang.Object`

        - Jede Klasse in Java erbt von der Klasse :java:`java.lang.Object`.
        - Die Klasse :java:`java.lang.Object` definiert allgemein relevante Methoden wie :java:`toString()`, :java:`equals()` und :java:`hashCode()`.
        - Die Methode :java:`toString()` gibt eine String-Repräsentation des Objekts zurück und wird aufgerufen, wenn ein Objekt in einem String-Kontext verwendet wird.

          .. include:: code/Super.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard
            :start-after: }   }
        - Die Methode :java:`getClass()` erlaubt den Zugriff auf die Klasse eines Objekts und ermöglicht :java:`Reflection`. :minor:`Thema für spätere Vorlesung(en).`

    .. layer:: incremental

        .. rubric:: Methoden überschreiben

        - Eine Methode in einer Subklasse kann eine Methode in der Superklasse überschreiben.
        - :dhbw-red:`Eine Methode, die eine Methode in der Superklasse überschreibt hat den Kontrakt der Superklasse immer einzuhalten!`
          
          D. h. Vorbedingungen können in der Subklassen entspannt und Nachbedingungen verschärft werden, aber nie umgekehrt.

    .. layer:: incremental

        .. rubric:: Einfach- vs. Mehrfachvererbung

        :Einfachvererbung: Jede Klasse kann nur eine Superklasse in der Vererbungshierachie besitzen

        :Mehrfachvererbung: Jede Klasse kann mehrere Superklassen in der Vererbungshierachie besitzen 

        .. container:: box-shadow margin-top-1em rounded-corners padding-1em 

            Java unterstützt nur Einfachvererbung bei Klassen (und Mehrfachvererbung bei Schnittstellen).



Vererbung und Typkonvertierungen/-kompatibilität
--------------------------------------------------

.. stack::

    .. layer::

        Im Folgenden gehen wir von der folgenden Vererbungshierarchie aus: 

        .. image:: images/konten.svg
            :alt: Konten
            :height: 750px
            :align: center
            
        Alle Attribute und Klassen sein :java:`public`. 

    .. layer:: incremental

        .. rubric:: Statischer und Dynamischer Typ

        - Eine Referenzvariable (für ein Objekt) hat einen statischen und einen dynamischen Typ.
        - Der statische Typ ist durch die Deklaration der Referenzvariablen gegeben.
          
          Beispiel: :java:`Konto k; //Statischer Typ "Konto"`

        - Der dynamische Typ hängt vom konkreten Objekt ab; es ist der Typ der Klasse, von der das Objekt instanziiert wurde mittels :java:`new`.
        
          Beispiel: :java:`k = new Sparkonto(...); // Dynamischer Typ "Sparkonto“`

    .. layer:: incremental

        - Der dynamische Typ muss von einer (nicht echten) Unterklasse des statischen Typs sein (z. B. „Sparkonto“ als dynamischer und „Konto“ als statischer Typ.)
        - Über die Referenzvariable sind nur die sichtbaren Attribute und Methoden des statischen Typs ansprechbar.

           Im Fall von :java:`Konto k = new Sparkonto(...);` kann nicht auf :java:`sparzins` zugegriffen werden.

        .. attention::
            :class: incremental

            Der dynamische Typ bestimmt die Methode, die ausgeführt wird. 
            
            .. container:: incremental far-smaller
            
                D. h. eine Methode, die in der Subklasse überschrieben wurde, wird auch dann ausgeführt, wenn die Referenzvariable den statischen Typ der Oberklasse hat.

    .. layer:: incremental

        .. rubric:: Implizite Typkonvertierung 

        .. class:: list-with-explanations

        - Eine implizite Typkonvertierung (ohne cast-Operator) ist in der Vererbungshierarchie aufwärts möglich (Upcast).
        
          Beispiel: Ein Tagesgeldkonto kann immer in ein Sparkonto konvertiert werden. Nach der Konvertierung sind über die Referenzvariable nur noch Attribute und Methoden des statischen Typs Sparkonto „sichtbar“. 
          
        - Das Objekt selbst wird bei einer impliziten Konvertierung nicht geändert, nur die sichtbaren Attribute und Methoden unterscheiden sich.

        - Die implizite Typkonvertierung ist sicher; es kann kein Fehler bei der Typkonvertierung entstehen.

    .. layer:: incremental

        .. rubric:: Explizite Typkonvertierung 

        - Typkonvertierung in der Vererbungshierarchie abwärts (Downcast) ist nur durch explizite Typkonvertierung (mit cast-Operator) möglich 
        
          Beispiel - ein Konto kann „möglicherweise“ in ein Sparkonto konvertiert werden: 
          
          :java:`Sparkonto sk = (Sparkonto) konto`;
        - Nach der Konvertierung sind über die Referenzvariable die Attribute und Methoden des statischen Typs Sparkonto „sichtbar“.
        - Das Objekt selbst wird bei einer expliziten Konvertierung nicht verändert!
        - Die Typkonvertierung ist nicht sicher; es kann ein Fehler bei der Typkonvertierung entstehen. Eine sogenannte *Typecast Exception* ist dann die Folge.

    .. layer:: incremental

        .. rubric:: Typkonvertierung - Details

        - Eine explizite Konvertierung eines Objektes ist nur dann möglich wenn der dynamische Typ des Objektes gleich der Ziel-Klasse ist bzw. der dynamische Typ des Objektes eine Subklasse der Ziel-Klasse ist.
        
          Beispiele: 
          
          - Ein Objekt wird als Festgeldkonto angelegt und implizit in ein Konto konvertiert (d. h. der dynamische Typ ist Festgeldkonto). Eine explizite Konvertierung in ein Sparkonto ist möglich. 
          - Wird allerdings ein Objekt als Sparkonto angelegt, dann kann es nicht explizit in ein Tagesgeldkonto konvertiert werden.

    .. layer:: incremental

        .. rubric:: Typtest mit :java:`instanceof`

        - Der :java:`instanceof`-Operator testet ob ein Objekt kompatibel zu einer Klasse ist (d. h. ob das Objekt in die Klasse konvertierbar ist). Der Operator gibt :java:`true` oder :java:`false` zurück:
        
          :Syntax: :java:`<Objekt> instanceof <Klasse>`

          :Beispiel: :java:`k instanceof Sparkonto` testet ob das Objekt :java:`k` in ein Sparkonto explizit konvertiert werden kann. Hier nur möglich, wenn :java:`k` den dynamischen Typ Sparkonto, Festgeldkonto oder Tagesgeldkonto hat.

          Sollte :java:`k` null sein, dann ist das Ergebnis immer :java:`false`.

    .. layer:: incremental

        .. rubric:: Beispiele

        .. code:: java
            :class: far-smaller copy-to-clipboard

                Konto k1 = new Festgeld (1, "Matt", 100, 2.5, 36);
                
                // Test der Typkompatibilität mit instanceof Festgeld
                if(k1 instanceof Festgeld){    
                    // Explizite Konvertierung ist jetzt sicher:
                    Festgeld k2 = (Festgeld)k1;
                    System.out.println(k2);
                }

.. supplemental::

    Bzgl. des Zugriffs auf Methoden mit *Default* Sichtbarkeit gelten die Standardregeln.

    Neben der klassischen Einfach- und Mehrfachvererbung gibt es noch viele weitere Konstrukte (z. B. traits, mixins, ...), die in anderen Programmiersprachen verwendet werden und ähnliche Konzepte ermöglichen.

    .. warning::

        Die Klasse :java:`java.lang.Object` definiert eine Reihe von Methoden, die als veraltet markiert sind. Diese sollten *nicht verwendet werden* und wir gehen hier auch nicht weiter auf diese ein!



Polymorphie (Polymorphism)
--------------------------------------------------

.. stack:: 

    .. layer::

        :Definition: Eine Referenzvariable mit einem statischen Typ kann auf Objekte mit unterschiedlichem dynamischen Typ verweisen.
        :Verwendung:

          - Überschreiben von Methoden (:eng:`Runtime Polymorphism``)
          - Parameter und Rückgabewerte: Methoden können als Parameter Objekte einer beliebigen Subklasse übergeben bekommen bzw. zurückgeben.
          - ein Array kann Objekte jeder Subklasse enthalten (z. B. ein Array mit dem Datentyp :java:`Konto[]` kann alle Subklassen enthalten.)
        
    .. layer:: incremental

        .. code:: java
            :class: far-smaller copy-to-clipboard

            Festgeld k1 = new Festgeld(1, "Matt", 100, 2.5, 36);
            Sparkonto k2 = new Sparkonto(1, "Michael", 100, 3);
            
            // Objekte mit unterschiedlichem dynamischen Typ in einem Array
            Konto[] konten = {k1, k2};
            for(int i=0; i<konten.length; ++i){
                println(konten[i]);
            }
        
    .. layer:: incremental

        **Beispiel**: Methode `fahren` wird in verschiedenen Klassen unterschiedlich implementiert.

        .. code:: java
            :class: far-smaller copy-to-clipboard

            class Auto {
                void fahren() {
                    System.out.println("Das Auto fährt.");
                }
            }

            class Elektroauto extends Auto {
                void fahren() { // Überschreiben der Methode
                    System.out.println("Das Elektroauto fährt leise.");
                }
            }
        
        Wir sprechen hier vom überschreiben (:eng:`overriding`) von Methoden.

    .. layer:: incremental

        Methoden überschreiben: 

        .. class:: list-with-explanations

        - Deklaration einer Methode mit der gleichen Schnittstelle (Name, Rückgabetyp, Parameter) aber ggf. mit neuem Methodenrumpf. 
        - Eine Methode kann in einer Subklasse eine erhöhte Sichtbarkeit haben, aber keine verringerte!
        - Methoden die :java:`final` sind können in Subklassen nicht überschrieben werden. 
        - Methoden die :java:`private` sind, sind in Subklassen nicht sichtbar und können daher nicht überschrieben werden. 
        
          Wenn die Subklasse eine Methode mit dem gleichen Namen und den gleichen Parametern definiert, dann handelt es sich um eine neue Methode und nicht um eine Überschreibung. Ob diese neue Methode auch (wieder) :java:`private` ist, ist nicht weiter von belang!


.. supplemental::

    overriding und Overloading sind zwei verschiedene Konzepte. Bei Overloading wird eine Methode mit dem gleichen Namen aber unterschiedlichen Parametertypen definiert. Bei Overriding wird eine Methode mit dem gleichen Namen und den gleichen Parametertypen in einer Subklasse neu definiert.



Zusammenfassung und Vorteile von Objekt-orientierter Programmierung\ [#]_
--------------------------------------------------------------------------

:Kapselung: Schützt die Daten und kontrolliert den Zugriff.
:Abstraktion: Vereinfacht die Komplexität des Codes.
:Vererbung: Ermöglicht Code-Wiederverwendung und Hierarchien.
:Polymorphie: Erlaubt flexiblen Code durch unterschiedliche Implementierungen.

.. [#] Diese Vorteile gelten im Wesentlichen für alle objektorientierten Programmiersprachen.



.. class:: integrated-exercise transition-move-to-top

Übung
--------------------------------------------------

.. exercise:: Meine Erste Klassenhierarchie

    Erstelle eine einfache :java:`Tier`-Klasse mit einem Attribut :java:`decibel` vom Typ :java:`float` und einer Methode :java:`lautGeben()`, die den Laut des Tieres auf der Konsole ausgibt und einer Methode :java:`decibel`, die die Lautstärke als :java:`String` zurückgibt. Erstelle dann die Klassen :java:`Hund` und :java:`Katze`, die :java:`Tier` erweitern bzw. von :java:`Tier` erben. Überschreibe die Methode `lautGeben()` mit unterschiedlichen Ausgaben.

    .. solution::
        :pwd: DerAnfangIstGemacht

        .. include:: code/Tiere.java
            :code: java
            :number-lines:
            :class: far-smaller



Fehlerbehandlung (:eng:`Exceptions`, :ger:`Ausnahmen`) 
--------------------------------------------------------

.. stack::

    .. layer::

        - Die Fehlerbehandlung in Java erfolgt mittels Exceptions.
        - Exceptions sind Objekte, die eine Fehlermeldung und den *Stacktrace* enthalten und erben direkt oder indirekt von :java:`Throwable`.
        - Exceptions können geworfen (mit :java:`throw`) und gefangen (mit :java:`try` und :java:`catch`) werden.
        - Exceptions können *checked* oder *unchecked* sein:

          .. container:: smaller

            - *Checked Exceptions* (Klassen, die von :java:`Throwable` erben aber nicht von :java:`RuntimeException` oder :java:`Error` ) müssen gefangen oder deklariert werden.
            - *Unchecked Exceptions* (Exceptions, die von :java:`java.lang.RuntimeException` erben) können im Code ignoriert werden; d. h. müssen nicht explizit beachtet werden. Sollten/müssen aber nicht.


    .. layer:: incremental

        .. rubric:: Exceptions Typhierearchie  

        .. image:: images/exceptions.svg
            :alt: Exceptions
            :height: 750px
            :align: center

    .. layer:: incremental

        .. rubric:: Einige ausgewählte typische Exceptions

        **Unchecked Exceptions**:

        - :java:`ArithmeticException`: Division durch ``0``.
        - :java:`NullPointerException`: Ein Objekt wird verwendet, obwohl es :java:`null` ist.
        - :java:`ArrayIndexOutOfBoundsException`: Ein ungültiger Index wird verwendet.
        - :java:`IllegalArgumentException`: Ein ungültiges Argument wird übergeben.
        
        **Checked Exceptions**:
        
        - :java:`IOException`: Fehler beim Lesen oder Schreiben von Dateien.
        - :java:`FileNotFoundException`: Datei nicht gefunden.
        - :java:`ParseException`: Fehler beim Parsen von Strings.


    .. layer:: incremental

        .. rubric:: Handling von *Unchecked Exceptions* (:java:`try ... catch (E e)`)

        .. include:: code/exceptions/Division.java 
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

    .. layer:: incremental

        .. rubric:: Handling von *Checked Exceptions* (:java:`try ... catch (E e)`)

        .. include:: code/exceptions/Date.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

    .. layer:: incremental

        .. rubric:: Identische Behandlung von mehreren Exceptions (:java:`... catch (A | B e`))

        .. include:: code/exceptions/DivisionV2.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

        .. container:: minor incremental far-smaller

            Es wäre auch möglich gewesen die gemeinsame Superklasse zu nehmen (:java:`RuntimeException`). Dies würde jedoch dazu führen, dass man Ausnahmen fängt, die man gar nicht fangen will!

    .. layer:: incremental

        .. rubric:: Deklaration, dass eine *Checked Exceptions* geworfen werden könnte (:java:`throws`)

        .. include:: code/exceptions/DateV2.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

    .. layer:: incremental

        .. rubric:: *Try-with-Resources* (:java:`try(var i = <Ressource>) { ... }`)
        
        Stellt sicher, dass eine Ressource (z. B. eine Datei) immer geschlossen wird, auch wenn eine Exception auftritt.

        .. include:: code/exceptions/Cat.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

        Der explizite Exceptionhandler wird nach dem Schließen der Ressource aufgerufen.

.. supplemental::

    Exceptions können selbstverständlich auch selbst definiert werden. Im Allgemeinen empfiehlt es sich aber, die Standard-Exceptions zu verwenden, da diese von anderen Entwicklern erkannt und verstanden werden.

    :java:`Errors` sind Exceptions, die nicht gefangen werden sollten. Sie sind für den Programmierer nicht vorhersehbar und können im ganz Allgemeinen nicht sinnvoll behandelt werden.  Sie signalisieren zum Beispiel Fehlerzustände der virtuellen Maschine. Ein Beispiel ist der :java:`OutOfMemoryError`.



.. class:: integrated-exercise transition-move-to-top

Übung
--------------------------------------------------

.. exercise:: Einfache Fehlerbehandlung

    Erweiteren Sie Ihre Methoden zum Berechnen der Kubikwurzel und zur Berechnung der Fibonacci-Zahlen um Fehlerbehandlung. D. h. testen Sie die Parameter auf Gültigkeit und werfen Sie ggf. eine :java:`IllegalArgumentException`. 
    
    Deklarieren Sie in der Methodensignatur, dass eine :java:`IllegalArgumentException` geworfen werden könnte.

    Bedenken Sie bei der Berechnung der Methode für die Kubikwurzel, dass Double Werte auch Spezialwerte wie :java:`Double.POSITIVE_INFINITY` und :java:`Double.NaN` haben können!

    Ändern Sie Ihre :java:`main` Methode so, dass sie die Exceptions fängt und eine entsprechende Fehlermeldung ausgibt und dann sauber das Program beendet.

    .. solution::
        :pwd: ExceptionsRule

        .. include:: code/math_with_exceptions/math/Functions.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

        .. include:: code/math_with_exceptions/Main.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard



.. class:: integrated-exercise transition-move-to-top

Übung
--------------------------------------------------

.. exercise:: Nicht-leere Zeilen zählen

    Schreiben Sie eine Methode (:java:`countNonEmptyLines`), die die Anzahl der nicht-leeren Zeilen in einem Datenstrom zählt und zurückgibt. Eine Zeile wird als leer angesehen, wenn diese keine Zeichen oder nur Leerzeichen enthält. Verwenden Sie dazu die Klasse :java:`BufferedReader` und die Methode :java:`readLine()` (siehe Beispiel in den Folien). Die Methode soll sich nicht um  Fehlerbehandlung kümmern.
    
    Schreiben Sie eine :java:`main` Methode, die die Methode verwendet und sich um jegliche Fehlerbehandlung kümmert. D. h. die main Methode soll bei allen Fehlern eine *passende Fehlermeldung* ausgeben und das Programm sauber beenden. Verwenden Sie ggf. ein :java:`try-with-ressource` Statement.

    .. hint::
        :class: far-smaller

        Studieren Sie die Dokumentation der Klasse :java:`String` in Hinblick auf Methoden, die es Ihnen einfacher machen zu erkennen ob eine Zeile gemäß obiger Definition leer ist.    

    .. solution::
        :pwd: ExceptionsGanzEinfach

        .. include:: code/exceptions/WC.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard



Abstrakte Klassen
--------------------------------------------------

.. stack::

    .. layer:: 

        - Abstrakte Klassen deklarieren ein Grundgerüst einer Klasse von der keine Objekte erzeugt werden können.

        - Abstrakte Klassen können abstrakte Methoden enthalten, die nur die Schnittstelle einer Methode definieren, aber auch implementierte Methoden.

        - Abstrakte Klassen und abstrakte Methoden werden durch den Modifizierer :java:`abstract` gekennzeichnet.

        - Nicht abstrakte Subklassen einer abstrakten Klasse müssen ALLE abstrakte Methoden der Vaterklasse implementieren.

    .. layer:: incremental

        :Beispiel: Eine `Form`-Klasse, die über verschiedene Unterklassen wie `Kreis`, `Quadrat` und `Dreieck` abstrahiert. Alle Formen bieten eine Möglichkeit zur Berechnung der Fläche.

        .. code:: java
            :class: far-smaller copy-to-clipboard incremental

            public abstract class EinfacheForm {
                protected double hoehe;
                abstract double berechneFlaeche();
                double berechneVolumen() {
                    return berechneFlaeche() * hoehe;
            }   }

        .. container:: two-columns incremental margin-top-1em

            .. container:: column no-separator

                .. code:: java
                    :class: far-smaller copy-to-clipboard

                    class Kreis extends EinfacheForm {
                        double r = 0.0;
                        double berechneFlaeche() {
                            return Math.PI * r * r;
                    }   }

            .. container:: column no-separator 

                .. code:: java
                    :class: far-smaller copy-to-clipboard

                    class Quadrat extends EinfacheForm {
                        double seite = 0.0;
                        double berechneFlaeche() {
                            return seite * seite;
                    }   }

    .. layer:: incremental

        .. rubric:: Abstrakte Methoden

        - Abstrakte Methoden, dürfen nicht :java:`private`, :java:`final` oder :java:`static` sein.
        - Abstrakte Methoden können von nicht-abstrakten Methoden aufgerufen werden.
        - Abstrakte Klassen können von anderen (auch nicht-abstrakten) Klassen erben.
        - Konkrete Subklassen müssen alle abstrakten Methoden implementieren.
            
    .. layer:: incremental

        .. rubric:: Statischer Typ

        - Abstrakte Klassen können als statischer Typ von Referenzvariablen verwendet werden.
        - Klassen, die von einer abstrakten Klasse erben, sind typkonform zu der abstrakten Klasse und können implizit in diese konvertiert werden.
        - Referenzvariablen (Abstrakte Klassen) können an den gewohnten Stellen verwendet werden.



Finale Klassen und Methoden (der :java:`final` Modifikator)
--------------------------------------------------------------

.. stack::

    .. layer::

      - Durch den Modifikator :java:`final` kann das Überschreiben von Methoden bzw. ganzen Klassen verhindert werden.
      - Methoden, die durch den Modifikator :java:`final` gekennzeichnet sind, können in Subklassen nicht überschrieben werden.
      - Von Klassen, die durch den Modifikator :java:`final` gekennzeichnet sind, können keine Subklassen abgeleitet werden

    .. layer:: incremental

        .. rubric:: Konto.java 

        .. code:: java
            :class: far-smaller copy-to-clipboard

            
            public class Konto {
                private String name;
                protected double saldo;
                        
                public final double getSaldo(){
                    return saldo;
                }
                
                public final void setSaldo(double saldo){
                    this.saldo = saldo;
                }
            }

    .. layer:: incremental

        .. rubric:: Festgeldkonto.java 

        .. code:: java
            :class: far-smaller copy-to-clipboard
            
            public final class Festgeldkonto extends Konto {
	            private int laufzeit;
                  //...
            }

.. supplemental::

    Attributen, die als :java:`final` markiert sind, kann nur einmal einen Wert zuweisen. Dies hat mit Vererbung nichts zu tun.




.. class:: integrated-exercise transition-move-to-top

Übung
--------------------------------------------------

.. exercise:: Vererbung, Exceptions und Abstrakte Klassen
    :class: far-far-smaller

    Wir möchten mathematische Ausdrücke repräsentieren, um darauf verschiedene Operationen auszuführen.

    Erstellen Sie eine abstrakte Klasse :java:`Term`, die eine Methode :java:`int evaluate()` deklariert. Die Methode :java:`evaluate` soll eine *Checked Exception* vom neu anzulegenden Typ :java:`MathException` werfen, wenn die Auswertung nicht möglich ist. Die abstrakte Klasse :java:`Term` hat ein privates Attribut mit der Priorität des Terms (als int Wert), welcher bei der Initialisierung gesetzt wird. Implementieren Sie eine passende finale Methode :java:`int getPriority()` in der abstrakten Klasse. Die Priorität eines Terms ist relevant, wenn man einen Ausdruck ausgeben möchte und die Klammern minimieren möchte.
    
    Erstellen Sie dann die Klassen :java:`Number`, :java:`Plus` und :java:`Division`, die von :java:`Term` erben und ggf. Referenzen auf weitere Terme halten. :java:`Number` repräsentiert eine Zahl, :java:`Plus` eine Addition und :java:`Division` eine Division. Implementieren Sie die Methode :java:`int evaluate()` in den Subklassen. Legen Sie für jede Klasse einen passenden Konstruktor an. Werfen Sie ggf. eine :java:`MathException`, wenn die Auswertung nicht möglich ist.

    Implementieren Sie für jede konkrete Klasse eine Methode :java:`public String toString()`, die den Term als String zurückgibt und Klammerung durchführt *wenn notwendig*. Die Methode :java:`toString()` soll die Klammern so setzen, dass der Ausdruck korrekt ist. D. h. :java:`(1 + 2) * 3` soll als :java:`(1 + 2) * 3` und nicht als :java:`1 + 2 * 3` ausgegeben werden. Weiterhin soll eine Ausdruck wie :java:`1 + 2 + 3` als :java:`1 + 2 + 3` und nicht als :java:`1 + (2 + 3)` oder :java:`(1 + 2) + 3` ausgegeben werden.

    Schreiben Sie eine :java:`main` Methode und testen Sie mit verschiedenen Termen die Auswertung und die Ausgabe.

    Achten Sie darauf, dass im Falle einer Exception eine passende Fehlermeldung ausgegeben wird.

    .. solution::
        :pwd: AbstraktUndKonkreteKlassen

        .. include:: code/terms/Main.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard


.. supplemental::

    Beispiele für die Verwendung:

    .. code:: java
        :class: far-smaller copy-to-clipboard

        System.out.println(
            new Division(
                new Number(1), 
                new Plus(
                    new Plus(new Number(1),new Number(2)), 
                    new Number(1))));
    
    Ausgabe:

    ::

        1 / (1 + 2 + 1)


    .. code:: java
        :class: far-smaller copy-to-clipboard

        System.out.println(
            new Plus(
                new Number(1), 
                new Division(new Number(2), new Number(1))));
    
    Ausgabe:

    ::

        1 + 2 / 1 



Schnittstellen (Java :java:`interface`\ s)
--------------------------------------------------------------

.. stack::

    .. layer::

        Schnittstellen (Interfaces) werden ähnlich wie Klassen deklariert, spezifizieren aber nur Methoden-Schnittstellen (abstrakte Methoden und :java:`default` Methoden) und öffentliche statische finale Attribute. 

        .. class:: incremental

        :Syntax:
            .. code:: java
        
                <public>? interface <Schnittstellenname>{
                    // statische, finale Attribute 
                    // Methodendeklarationen und "default" Methoden
                }

    .. layer:: incremental

        :Beispiele:

            .. rubric:: Saeugetier.java

            .. include:: code/interfaces/Saeugetier.java
                :code: java
                :number-lines:
                :class: far-smaller copy-to-clipboard

            .. rubric:: Haustier.java

            .. include:: code/interfaces/Haustier.java
                :code: java
                :number-lines:
                :class: far-smaller copy-to-clipboard
    
    .. layer:: incremental

        :Details:

            - Von Schnittstellen können keine Objekte erzeugt werden.
            - Schnittstellen können aber als statischer Typ eines Objektes verwendet werden.
            - Die Angabe von :java:`public abstract` bei Methoden ist optional.
            - Die Angabe von :java:`public final static` bei Attributen ist optional.
  
    .. layer:: incremental

        .. rubric:: Implementierung von Schnittstellen

        Eine Klasse kann mehrere Schnittstellen implementieren. Die Methoden der Schnittstellen müssen in der Klasse implementiert werden.

        .. rubric:: Katze.java

        .. include:: code/interfaces/Katze.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

    .. layer:: incremental

        .. rubric:: Vererbung von Schnittstellen

        Eine Schnittstelle kann von einer oder mehrerer Schnittstelle erben.

        :Syntax:

            .. code:: java

                interface <Schnittstelle> 
                    extends <Schnittstelle> (, <Schnittstelle>)* {
                    //...
                }

        :Beispiel: Schnittstellenvererbung

            .. include:: code/interfaces/Carnivora.java
                :code: java
                :number-lines:
                :class: far-smaller copy-to-clipboard

    .. layer:: incremental

        **Statischer Typ**

        - Schnittstellen können (wie Klassen) als statischer Typ von Objekten(Referenzvariablen) verwendet werden.
        - Klassen, die eine Schnittstelle implementieren, sind typkonform zu der Schnittstelle und können implizit in diese konvertiert werden.
        - Referenzvariablen (mit den statischen Datentyp einer Schnittstellen) können an den gewohnten Stellen verwendet werden.

.. supplemental::

    Es ist nicht möglich Interfaces mit Methoden mit inkompatiblen Signaturen zu implementieren. Es ist aber möglich, dass eine Klasse mehrere Interfaces implementiert, die Methoden mit gleichen Signaturen haben. In diesem Fall muss die Klasse die Methode nur einmal implementieren.

    :Beispiel:

        .. include:: code/conflicting_interfaces/Main.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard



.. class:: integrated-exercise transition-move-to-top

Übung
--------------------------------------------------

.. exercise:: Ausdrücke vergleichen (Schnittstellen, instanceof, Type Casts)

    Erweitern Sie die Lösung der vorhergehenden Übung wie folgt.

    Definieren Sie eine Schnittstelle :java:`Comparable`, die eine Methode :java:`boolean equal(Term t)` deklariert. Implementierungen der Methode sollen den aktuellen Term vergleichen mit dem Übergebenen und :java:`true` zurückgeben, wenn der aktuelle Term (:java:`this`) identisch zum übergebenen Term (:java:`t`) ist. Beachten Sie die das Kommutativgesetz beim Vergleich; d. h. :java:`a + b` ist gleich :java:`b + a`.

    Die abstrakten Klasse :java:`Term` soll die Schnittstelle implementieren. Die Implementierungen der Methoden müssen natürlich in den Subklassen erfolgen.

    .. solution::
        :pwd: ThatsIt

        .. include:: code/terms/Comparable.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard

.. supplemental::

    .. rubric:: Beispiel

    .. code:: java
        :class: far-smaller copy-to-clipboard

        System.out.println(
                new Plus(new Number(1), new Number(2))
            .equal(
                null
            ));
        System.out.println(
                new Plus(new Number(1), new Number(2))
            .equal(
                new Plus(new Number(1), new Number(2)))
        );
        System.out.println(
                new Plus(new Number(2), new Number(1))
            .equal(
                new Plus(new Number(1), new Number(2)))
        );

    Ausgabe:

    :: 

        false
        true
        true
