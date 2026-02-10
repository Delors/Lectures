.. meta::
    :lang: de
    :author: Michael Eichberg
    :keywords: "Hashing", "Hashmaps", "Algorithmen", "Java", "Python"
    :description lang=de: Hashing und Hashmaps
    :id: lecture-theo-algo-hashing-and-applications
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Hashing und Hashmaps
======================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 2.0.1

.. container:: peripheral

    :Quelle:
        Die Folien sind teilweise inspiriert von oder basierend auf Robert Sedgewick und Kevin Wayne, "Algorithms", Addison-Wesley, 4th Edition, 2011 sowie auf Lehrmaterial von Prof. Dr. Ritterbusch

.. supplemental::

    :Folien:

        |html-source|

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-move-to-top

Einführung
--------------------------------------------------------



Suchen in einer Liste
--------------------------------------------------------

.. container:: s-font-size-70 highlight-cell-on-hover

    .. class:: incremental-table-rows

    +-----------------------------------------+----------+----------+---------+-------------------------+-----------+---------+---------------------------------+
    | Implementation                          | Garantie                      | Durchschnittlicher Fall                       | Operationen auf den Schlüsseln  |
    +                                         +----------+----------+---------+-------------------------+-----------+---------+                                 +
    |                                         | Suchen   | Einfügen | Löschen | Suchen                  | Einfügen  | Löschen |                                 |
    +=========================================+==========+==========+=========+=========================+===========+=========+=================================+
    | sequentielle Suche                      | N        | N        | N       | ½ N                     | N         | ½ N     | equals()                        |
    |                                         |          |          |         |                         |           |         |                                 |
    | (unsortierte Liste)                     |          |          |         |                         |           |         |                                 |
    +-----------------------------------------+----------+----------+---------+-------------------------+-----------+---------+---------------------------------+
    | binäre Suche (geordnetes Array)         | lg N     | N        | N       | lg N                    | ½ N       | ½ N     | compareTo()                     |
    +-----------------------------------------+----------+----------+---------+-------------------------+-----------+---------+---------------------------------+
    | BST [#]_                                | N        | N        | N       | 1.39 lg N               | 1.39 lg N | √ N     | compareTo()                     |
    +-----------------------------------------+----------+----------+---------+-------------------------+-----------+---------+---------------------------------+


.. question::
    :class: incremental

    Können wir effizienter suchen?

.. [#] Binary Search Tree




Hashing - Grundidee
--------------------------------------------------------

.. image:: images/Hashkollision.svg
    :class: opaque
    :alt: Hashmap
    :align: right

.. deck::

    .. card:: d-no-clear

        .. class:: incremental list-with-explanations

        - Die Elemente werden über den Schlüssel indexiert in einer Tabelle gespeichert.

          Der Index ist eine Funktion des Schlüssels.
        - Hash-Funktion:  Methode zur Berechnung des Array-Index aus dem Schlüssel.

    .. card:: d-no-clear

        .. rubric:: Herausforderungen

        1. Berechnung der Hash-Funktion.
        2. Gleichheitstest: Methode zur Überprüfung, ob zwei Schlüssel gleich sind.
        3. Kollisionsauflösung: Algorithmus und Datenstruktur zur Behandlung von zwei Schlüsseln, die auf denselben Array-Index hindeuten.

    .. card::

        .. hint::

            .. rubric:: Klassischer Kompromiss zwischen Raum und Zeit!

            - Keine Platzbeschränkung: triviale Hash-Funktion mit Schlüssel als Index.
            - Keine Zeitbeschränkung: triviale Kollisionsauflösung mit sequentieller Suche.
            - Raum- und Zeitbeschränkung: Hashing (die reale Welt).

.. supplemental::

    In dem Beispiel ist der Schlüssel das Wort ``it``.



Berechnung der Hash-Funktion
--------------------------------------------------------

:Idealistisches Ziel:

    Die Schlüssel gleichmäßig verwürfeln, um einen Tabellenindex zu erzeugen.

    - Effizient berechenbar.
    - Jeder Tabellenindex ist für jeden Schlüssel gleich wahrscheinlich.

    .. supplemental::

        Die Frage, wie man gute Schlüssel berechnet, ist ein gründlich erforschtes Problem, dass in der Praxis immer noch problematisch ist.

.. container:: example incremental

    .. rubric:: Beispiel 1.  Telefonnummern.

    :Schlecht: die ersten drei bis fünf Ziffern.
    :Besser: die letzten vier Ziffern.

.. container:: example incremental

    .. rubric:: Beispiel 2.  Sozialversicherungsnummer

    :Schlecht: die ersten beiden Ziffern.
    :Besser: die letzten Ziffern.

    .. supplemental::

        Die ersten beiden Stellen bei der Sozialversicherungsnummer identifizieren den Rentenversicherungsträger.

.. container:: challenge incremental

    Praktische Herausforderung: für jeden Schlüsseltyp ist ein anderer Ansatz erforderlich.



Hashfunktionen
--------------------------------------------------------

.. deck::

    .. card::

        .. definition::

            Eine Hashfunktion :math:`h : M →\mathbb{Z}_n` bildet eine Menge :math:`M` mit :math:`|M|≥|\mathbb{Z}_n|` auf die Zahlen :math:`0,...,n−1` ab.

            Eine Hashfunktion ist *surjektiv* \ [#]_: für jedes :math:`y ∈ Z_n` gibt es ein :math:`x ∈ M` mit :math:`h(x) = y`.

            .. presenter-note::

                Surjektiv bedeutet, dass jeder Wert aus dem Wertebereich mindestens einmal von der Funktion berechnet wird.

                Sollte die Funktion nicht-surjektiv sein, dann führt das ggf. zu unnötigen Kollisionen und verschlechtert die Effizienz der Hashtabelle.

            Eine Hashfunktion ist *gleichverteilt*, wenn zwei Bilder :math:`y1,y2 ∈ \mathbb{Z}_n` immer ungefähr gleich viele Urbilder haben :math:`|h^{−1}({y1})|≈|h^{−1}({y2})|`.

        .. [#] In machen Fällen ist der Nachweis nicht möglich, aber es wird vermutet.

    .. card::

        .. rubric:: Hashes für unterschiedliche Anwendungen

        .. class:: incremental

        - **Hashes für Datenstrukturen** *müssen sehr effizient* sein.
        - Für **Hashes, welche verwendet werden im Rahmen von Verschlüsselung und Signaturen,**
          muss es schwer sein:

          - ein Urbild zu finden (d. h. von Y auf X zu schließen)
          - zwei kollidierende Werte zu finden.

          .. supplemental::

             MD5 ist seit 2008 und SHA1 seit 2017 „geknackt“.

          - kryptographische Hashes *sollten effizient berechenbar* sein.
        - **Hashes für Passwortspeicherung** müssen die selben Anf. erfüllen wie Hashes für Signaturen und Verschlüsselungszwecke, dürfen aber *nicht effizient berechenbar* sein.

        .. important::
            :class: incremental

            Im Folgenden konzentrieren wir uns auf Hashes für Datenstrukturen.

    .. card::

        Wenn das Ziel ist, Hash-Werte mit einer bestimmten Länge (zum Beispiel 32Bit) zu berechnen, dann wären folgende Hashfunktionen denkbar:

        .. rubric:: Exemplarische Hashfunktionen

        .. deck::

            .. card::

                **Ganze Zahlen**

                .. code:: rust
                    :number-lines:

                    hash(x: u32) : u32 { return x; } // u32 == 32-Bit unsigned integer

            .. card::

                **Gleitkommazahlen**

                .. code:: rust
                    :number-lines:

                    hash(x: f64) : u32 { // f64 == 64-Bit (IEEE) floating point number
                        bits : u64 = f64ToBits(x); // u64 = 64-Bit (signed) integer
                        return (u32) (bits ^ (bits >>> 32));
                    }

                .. supplemental::

                    ``>>>`` ist der *unsigned right shift* Operator.

            .. card::

                **Zeichenketten**

                .. note::

                    .. csv-table::
                        :header: char, unicode

                        'a', 97
                        'b', 98
                        'c', 99
                        ︙, ︙
                        'l', 108


                Horners Methode für Zeichenketten der Länge L:

                :math:`h = s[0] · 31^{L–1} + …  + s[L – 2] · 31^1  +  s[L – 1] · 31^0`.

                .. code:: rust
                    :number-lines:

                    fn hash(s: [char,4]) : u32 {
                        hash: u32 = 0
                        for i in 0..4 { hash = s[i] + hash * 31; }
                        return hash;
                    }
                    // hash(['c','a','l','l']) = // ≘ hash("call")
                    //    99 · 31·31·31 + 97 · 31·31 + 108 · 31 + 108 =
                    //    108 + 31 · ( 108 + 31 · (97 + 31 · (99)))




.. class:: new-section transition-move-to-top

Hashing in Python
--------------------------------------------------------



Verwendung von Hashes in Python
--------------------------------------------------------

.. deck::

    .. card::

        .. class:: incremental-list

        - Bei der Speicherung von Objekten in Sets und Dictionaries verwendet Python Hashes.
        - Sobald ein Objekt in einem Set oder Dictionary gespeichert wird, darf der Objektzustand (zumindest im Hinblick auf die Hashfunktion) nicht mehr verändert werden!
        - Der Hashwert eines (nicht veränderlichen) Objekts kann mit der Funktion ``hash()`` berechnet werden.
        - Eigene Objekte in Sets und Dictionaries speichern:

          .. class:: incremental-list

          - Um benutzerdefinierte Objekte in einer Hashmap zu speichern, müssen wir die Methoden :python:`__hash__` und :python:`__eq__` implementieren.

          - Zu beachten:

            - Hashwerte *müssen für gleiche Objekte gleich sein*.
            - Hashwerte *für unterschiedliche Objekte sollten unterschiedlich sein*.

    .. card::

        .. rubric:: Beispielklasse :java:`Person`

        .. code:: python
            :class: copy-to-clipboard
            :number-lines:

            class Person:
                def __init__(self, name, age):
                    self.name = name
                    self.age = age

                def __eq__(self, other):
                    if isinstance(other, Person):
                        return  self.name == other.name and \
                                self.age == other.age
                    return False

                def __hash__(self):
                    return hash((self.name, self.age))

    .. card::

        .. rubric:: Verwendung der Klasse :java:`Person`

        .. code:: python
            :class: copy-to-clipboard
            :number-lines:

            person1 = Person("Alice", 30)
            person2 = Person("Bob", 25)
            person3 = Person("Alice", 30) # gleiche Werte wie "person1"

        **Beispielausgabe**

        .. code:: python
            :class: incremental

            >>> person1
            <__main__.Person object at 0x101474c20>
            >>> person2
            <__main__.Person object at 0x1013daad0>
            >>> person3
            <__main__.Person object at 0x1013db110>

    .. card::

        .. rubric:: Speicherung von :java:`Person`-Objekten in einem Set

        .. code:: python
            :class: copy-to-clipboard
            :number-lines:

            people = {person1, person2, person3}

        .. container:: incremental

            .. rubric:: Ausgabe des Sets

            .. code:: python
                :class: copy-to-clipboard
                :number-lines:

                for p in people: print(p.name)

            **Ausgabe**

            .. code:: python
                :class: incremental

                Bob
                Alice

    .. card::

        .. rubric:: Verwendung der :java:`hash`-Funktion

        .. code:: python
            :class: copy-to-clipboard
            :number-lines:

            print(hash(person1))
            print(hash(person2))
            print(hash(person3))

        **Beispielausgabe**

        .. code:: python
            :class: incremental

            3529483511948588452
            -9048922068811934735
            3529483511948588452

        .. supplemental::

            In Python ist die Ausgabe der Funktion ``hash()`` nach jedem Neustart der Pythonumgebung unterschiedlich, da die Hashfunktion einen Zufallswert enthält, der bei jedem Neustart neu generiert wird.

    .. card::

        .. rubric:: Beispielklasse :java:`Person` mit konstantem Hashwert

        .. code:: python
            :class: copy-to-clipboard
            :number-lines:

            class PersonWithBadHash:
                def __init__(self, name, age):
                    self.name = name
                    self.age = age

                def __eq__(self, other):
                    if isinstance(other, Person):
                        return  self.name == other.name and \
                                self.age == other.age
                    return False

                def __hash__(self):
                    return 1 # immer der gleiche Hashwert

        .. supplemental::

            Die Verwendung des Alters der Person als Hashwert wäre in den allermeisten Fällen auch keine gute Idee, da es (vermutlich) viele Hashkollisionen geben würde.

    .. card::

        .. rubric:: Verwendung einer Klasse mit einer konstanten Hashfunktion

        .. code:: python

            person1 = Person("Alice", 30)
            person2 = Person("Bob", 25)
            person3 = Person("Alice", 30)
            people = {person1, person2, person3}
            print(hash(person1))
            print(hash(person2))
            print(hash(person3))
            print(" ".join(map(lambda p: p.name, people)))

        **Beispielausgabe**

        .. code:: python

            1
            1
            1
            Alice Bob

        .. supplemental::

            .. warning::

                Die Verwendung einer konstanten Hashfunktion ist in der Regel keine gute Idee, da sie die Effizienz von Hashmaps ganz erheblich beeinträchtigen kann.


.. class:: exercises transition-fade

Übung
--------------------------------------------------------

.. exercise:: Eine Klasse zur Repräsentation von Studierenden.

    Die Klasse :java:`Student` soll:

    - die Attribute/Properties ``name`` und ``matriculation_number`` haben.

    - die Methoden ``__eq__`` und ``__hash__`` sinnvoll/korrekt definieren

    Aufgaben:

    1) Erzeugen Sie drei :java:`Student`-Objekte und speichern Sie diese in einem Set.

    2) *Fragen Sie sich wie sie effizient den Hashwert berechnen können.*

    3) Geben Sie die Namen der Studierenden aus.
    4) Was passiert, wenn Sie — *nachdem Sie ein Student Objekt dem Dictionary hinzugefügt haben* — den Namen des Studenten ändern?

       Schreiben Sie entsprechenden Code, um Ihre Annahme zu überprüfen!

    .. supplemental::

        .. rubric:: Rumpfimplementierung

        .. code:: python
            :class: copy-to-clipboard
            :number-lines:

            class Student:
                def __init__(self, ... ):
                    raise NotImplementedError("TODO")

                def __eq__(self, other):
                    raise NotImplementedError("TODO")

                def __hash__(self):
                    raise NotImplementedError("TODO")

    .. solution::
        :pwd: DieMatrikelnummerIstDerHash

        .. rubric:: Lösung

        .. include:: code/Student.py
            :number-lines:
            :code: python
            :class: copy-to-clipboard





.. class:: new-section transition-move-to-top

Hashing in Java
--------------------------------------------------------



Verwendung von Hashes in Java
--------------------------------------------------------

.. deck::

    .. card::

        .. class:: incremental-list

        - Bei der Speicherung von Objekten in Sets und :java:`HashMap`\ s/:java:`Hashtable`\ s verwendet Java Hashes.
        - Sobald ein Objekt in einem Set oder einer Map gespeichert wird, darf der Objektzustand (zumindest im Hinblick auf die Hashfunktion) nicht mehr verändert werden!
        - Der Hashwert eines (nicht veränderlichen) Objekts kann mit der Funktion :java:`hashCode()` berechnet werden.
        - Eigene Objekte in Sets und Maps speichern:

          .. class:: incremental-list

          - Um benutzerdefinierte Objekte in einer :java:`HashMap` zu speichern, müssen wir die Methoden :java:`boolean equals(Object o)` und :python:`int hashCode()` implementieren.

          - Zu beachten:

            - Hashwerte *müssen für gleiche Objekte gleich sein*.
            - Hashwerte *für unterschiedliche Objekte sollten unterschiedlich sein*.

    .. card::

        .. rubric:: Beispielklasse :java:`Person`

        .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            class Person {
                private String name;
                private int age;
                Person(String name, int age) { this.name = name; this.age = age; }

                public boolean equals(Object o) {
                    if (o instanceof Person) { // Alt. compare class objects
                        Person other = (Person) o;
                        return this.name.equals(other.name) && this.age == other.age;
                    }
                    return false;
                }

                public int hashCode() { return java.util.Objects.hash(name, age); }
            }

    .. card::

        .. rubric:: Verwendung der Klasse :java:`Person`

        .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            var person1 = new Person("Alice", 30);
            var person2 = new Person("Bob", 25);
            var person3 = new Person("Alice", 30); // gleiche Werte wie "person1"

        **Beispielausgabe**

        .. code:: console
            :class: incremental

            person1 ==> Person@750e297f // the addresses are memory addresses
            person2 ==> Person@1fb0e5
            person3 ==> Person@650e893b

    .. card::

        .. rubric:: Speicherung von :java:`Person`-Objekten in einem Set

        .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            var set = new HashSet<Person>();
            set.add(person1);
            set.add(person2);
            set.add(person3);

        .. code:: java
            :class: copy-to-clipboard incremental
            :number-lines:

            // throws IllegalArgumentException:
            var people = Set.of(person1, person2, person3)

        .. container:: incremental

            .. rubric:: Ausgabe des Sets

            .. code:: java
                :class: copy-to-clipboard
                :number-lines:

                for (var p : people) System.out.println(p.name);

        .. container:: incremental

            **Ausgabe**

            .. code:: java

                Bob
                Alice

    .. card::

        .. rubric:: Verwendung der :java:`hashCode`-Funktion

        .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            System.out.println(person1.hashCode());
            System.out.println(person2.hashCode());
            System.out.println(person3.hashCode());

        **Beispielausgabe**

        .. code:: python
            :class: incremental

            1963862399
            2076901
            1963862399


    .. card::

        .. rubric:: Beispielklasse :java:`Person` mit konstantem Hashwert

        .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            class PersonWithBadHash {
                String name;
                int age;
                PersonWithBadHash(String name, int age) { this.name = name; this.age = age; }

                public boolean equals(Object o) {
                    if (o instanceof PersonWithBadHash) {
                        PersonWithBadHash other = (PersonWithBadHash) o;
                        return this.name.equals(other.name) && this.age == other.age;
                    }
                    return false;
                }

                public int hashCode() { return 1; /* immer der gleiche Hashwert */ }
            }

        .. supplemental::

            Die Verwendung „nur“ des Alters der Person als Hashwert wäre in den allermeisten Fällen auch keine gute Idee, da es (vermutlich) viele Hashkollisionen geben würde.

    .. card::

        .. rubric:: Verwendung einer Klasse mit einer konstanten Hashfunktion

        .. code:: java

            var person1 = new PersonWithBadHash("Alice", 30);
            var person2 = new PersonWithBadHash("Bob", 25);
            System.out.println(person1.hashCode());
            System.out.println(person2.hashCode());
            var people = Set.of(person1, person2);
            people.forEach(p -> System.out.println(p.name));

        **Beispielausgabe**

        .. code:: java

            1
            1
            1
            Alice Bob

        .. supplemental::

            .. warning::

                Die Verwendung einer konstanten Hashfunktion ist in der Regel keine gute Idee, da sie die Effizienz von Hashmaps ganz erheblich beeinträchtigen kann.


.. class:: exercises transition-fade

Übung
--------------------------------------------------------

.. exercise:: Eine Klasse zur Repräsentation von Studierenden.

    Die Klasse :java:`Student` (Nutzen Sie hier kein :java:`record`) soll:

    - die Attribute/Properties ``name`` und ``matriculationNumber`` haben.

    - die Methoden ``equals`` und ``hashCode`` sinnvoll/korrekt definieren

    Aufgaben:

    1) Erzeugen Sie drei :java:`Student`-Objekte und speichern Sie diese in einem Set.

    2) *Fragen Sie sich wie sie effizient den Hashwert berechnen können.*

    3) Geben Sie die Namen der Studierenden aus.
    4) Was passiert, wenn Sie — *nachdem Sie ein Student Objekt einem (Hash)Set hinzugefügt haben* — den Namen des Studenten ändern?

       Schreiben Sie entsprechenden Code, um Ihre Annahme zu überprüfen!

    .. supplemental::

        .. rubric:: Rumpfimplementierung

        .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            import java.util.HashSet;

            class Student {
                private String name;
                private int matriculationNumber;

                public Student(String name, int matriculationNumber) {
                    this.name = name;
                    this.matriculationNumber = matriculationNumber;
                }

                public String getName() { return name; }
                public void setName(String name) { this.name = name; }
                public int getMatriculationNumber(){ return matriculationNumber; }
                public void setMatriculationNumber(int matriculationNumber) {
                    this.matriculationNumber = matriculationNumber;
                }

                @Override public boolean equals(Object o) {
                    throw new UnsupportedOperationException("TODO");
                }

                @Override public int hashCode() {
                    throw new UnsupportedOperationException("TODO");
                }
            }

    .. solution::
        :pwd: DieMatrikelnummerIstDerHash

        .. rubric:: Lösung

        .. include:: code/Student.java
            :number-lines:
            :code: java
            :class: copy-to-clipboard





.. class:: new-section transition-move-to-top

Hashfunktionen
--------------------------------------------------------



Gängige Ansätze für Hashfunktionen
--------------------------------------------------------

.. deck::

    .. card::

        :Modulo-Hashfunktion:

            Sei :math:`n` möglichst eine Primzahl:

            :math:`h^{mod}_n(x) = x\; mod\; n`

        **Bewertung**

        - einfach zu berechnen/sehr effizient
        - surjektiv
        - gleichverteilt
        - wenn :math:`n` keine Primzahl ist, dann kann es (leicht) passieren, dass bestimmte (Teil-)daten weniger oder keinen Einfluss auf den Hashwert haben:

          .. class:: incremental

          - :math:`x \cdot 10^3 \mod 40 = 0`
          - :math:`x \cdot 10^3 \mod 42 \in \{0,2,4,...,40\}` Anm.: :math:`ggt(42,1000) = 2`
          - :math:`x \cdot 10^3 \mod 41 \in \{0,1,2,3,...,40\}` Anm.: :math:`ggt(41,1000) = 1`

    .. card::

        :Multiplikations-Hashfunktion:

            Sei :math:`c` fest, oft :math:`c = {\sqrt{5}−1 \over 2} \approx 0,6180339887498949`:

            .. presenter-note::

                c ist eine irrationale Zahl.

            :math:`h^{mul}_n (x) = ⌊n·(c·x −⌊c·x⌋)⌋`

        **Bewertung**

        .. class:: list-with-explanations

        - nicht beweisbar surjektiv
        - nur asymptotisch gleichverteilt
        - Das verwendete :math:`c` sollte eine gute Durchmischung der Key-Bits fördern.

          Andere irrationale Zahlen sind ggf. auch sinnvoll/möglich.

        - Berechnung benötigt eine effiziente Fließkomma-Verarbeitung



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Hashwerte berechnen I

    Berechnen Sie:

    1. :math:`h^{mod}_{257}(1 000)`
    2. :math:`h^{mul}_{257}(1 000)`

    .. solution::
        :pwd: zweiWerte

        .. rubric:: Lösung

        **Python-Implementierung**

        .. code:: python
            :class: copy-to-clipboard

            import math

            c = ((5 ** 0.5) - 1) / 2
            def h(x,n) :
                return  math.floor(n * (c * x - math.floor(c*x)))

        **Als mathematischer Ausdruck**

        1. :math:`h^{mod}_{257}(1 000) = 1 000 \mod 257 = 229`
        2. :math:`h^{mul}_{257}(1 000) = ⌊257·(0,6180339887498949·1000 −⌊0,6180339887498949·1 000⌋)⌋ = 8`

.. exercise:: Hashwerte berechnen II

    Berechnen Sie:

    1. :math:`h^{mod}_{263}(10 000)`
    2. :math:`h^{mul}_{263}(10 000)`

    .. solution::
        :pwd: WiederZweiWerte+

        .. rubric:: Lösung

        **Als mathematischer Ausdruck**

        1. :math:`h^{mod}_{263}(10 000) = 10 000 \mod 263 = 6`
        2. :math:`h^{mul}_{263}(10 000) = ⌊263·(0,6180339887498949·10 000 −⌊0,6180339887498949·10 000⌋)⌋ = 89`




.. class:: new-section transition-move-to-top

Hashtabellen (:eng:`Hashmaps` oder :eng:`Dictionaries`)
-------------------------------------------------------



Grundlagen von Hashtabellen
-------------------------------

Das Grundprinzip von Hashtabellen ist einfach:

.. class:: incremental list-with-explanations

- Im Vorfeld wird ein Array :java:`A` einer Größe :java:`n` angelegt,

  Die Größe des Arrays übersteigt die erwartete Belegung deutlich.
- Daten mit einem Schlüssel :java:`k` werden dann an der Position :java:`A[h(k)]` gespeichert - oder an einer Ersatzposition.
- Sollte die Belegung zu groß werden, wird das Array vergrößert und die Elemente werden (ggf.) neu bzw. wieder gehasht.
- Sollten zwei Schlüssel den gleichen Hash haben (d. h. :math:`h(x_1) = h(x_2)`), dann wird eine Kollisionsauflösung benötigt.



Belegung von Hashtabellen
-------------------------------

Die Belegung von Hashtabellen ist für die Effizienz entscheidend.

.. definition:: 

    Ein Array :math:`A` der Kapazität :math:`n` mit einer Hashfunktion :math:`h_n` wird :math:`Hashtabelle(A,h_n)` genannt.

    Sind zu einem Zeitpunkt :math:`m` (erste) Felder belegt, so hat die :math:`Hashtabelle(A,h_n)` eine Belegung von :math:`α = \frac{m}{n}`.


Verkettete Hashtabellen
-------------------------------

.. deck::

    .. card::

        .. rubric:: Direkte Verkettung

        .. image:: images/hashtables/direkte_verkettung.svg
                    :alt: Hashtabelle mit direkter Verkettung
                    :align: center

        .. supplemental::

            Die *direkte Verkettung* von Überläufern verwendet eine :math:`Hashtabelle(A,h_n)`, mit einem Array :math:`A`, das aus Zeigern auf einfach verkettete Listen besteht, dessen Schlüssel der Einträge alle den gleichen Hashwert besitzen, oder die ``nil`` sind, wenn kein Eintrag bisher mit dem jeweiligen Hashwert vorhanden ist.

    .. card::

        .. rubric:: Separate Verkettung

        .. image:: images/hashtables/separate_verkettung.svg
                    :alt: Hashtabelle mit separater Verkettung
                    :align: center

        .. supplemental::


            Die *separate Verkettung* von Überläufern verwendet eine :math:`Hashtabelle(A,h_n)`, bei der das Array :math:`A` aus Knoten einer einfach verketteten Liste besteht, dessen Wert :math:`nil` ist, wenn unter dem Hashwert noch nichts gespeichert wurde.

            Ein Eintrag mit Schlüssel :math:`k` wird der verketteten Liste zugeordnet, die in :math:`A[h_n(k)]` verlinkt ist oder startet, und kann entsprechend hinzugefügt, gelöscht und gefunden werden.



Offene Adressierung
-------------------------------

.. definition:: 

    Soll der :math:`Hashtabelle(A,h_n)` mit einem Array :math:`A` ein Datensatz mit Schlüssel :math:`k` hinzugefügt werden soll, so erfolgt dies in :math:`A[h_n(k)]`, wenn dieser Eintrag noch nicht belegt ist. Ansonsten werden  :math:`i= 1,...,n−1` weitere Positionen :math:`A[g_n(k,i)]` geprüft.

.. rubric:: Strategien

.. deck::

    .. card::

        :Lineares-Sondieren:

            Das Array wird linear durchsucht.

            :math:`g^{lin}_n(k,i) = (h_n(k) + i)\; mod\; n`

    .. card::

        :Quadratisches-Sondieren:

            Das Array wird quadratisch steigend durchsucht.

            :math:`g^{quad}_n(k,i) = (h_n(k) + i^2)\; mod\; n`

    .. card::

        :Doppeltes-Hashing:

            Das Array wird mit Hilfe einer zweiten Hashfunktion:

            :math:`h^{'}_n(k) = (k\; mod\; (n-2)) +1`

            durchsucht.

            :math:`g^{doppel}_n(k,i) = (h_n(k) + i \cdot h^{'}_n(k))\; mod\; n`



Beispiel Offene Adressierung (Hashing: :math:`x\; mod\; 7`)
------------------------------------------------------------

.. deck::

    .. card::

        .. image:: images/open_addressing/linear_probing.svg
                    :alt: Offene Adressierung mit linearem Sondieren
                    :align: right

        **Lineare Sondierung**

        Hinzufügen von (17, 5, 3, 21, 9, 10, 12)

    .. card::

        .. image:: images/open_addressing/double_hashing.svg
                    :alt: Offene Adressierung mit doppeltem Hashing
                    :align: right

        **Doppeltes-Hashing**

        Hinzufügen von (17, 5, 3, 21, 9, 10, 12)

    .. card::

        .. image:: images/open_addressing/quadratic_probing.svg
                    :alt: Offene Adressierung mit quadratischem Sondieren
                    :align: right

        **Quadratische Sondierung**

        Hinzufügen von (17, 5, 3, 21, 9, 10, 12)

        .. container:: incremental

            Für den Wert 10 wird kein Platz gefunden!

            :math:`(10\; mod\; 7 = 3)`

            1. :math:`3 + 0^2\; mod\; 7 = 3`
            2. :math:`3 + 1^2\; mod\; 7 = 4`
            3. :math:`3 + 2^2\; mod\; 7 = 0`
            4. :math:`3 + 3^2\; mod\; 7 = 5`
            5. :math:`3 + 4^2\; mod\; 7 = 5`
            6. :math:`3 + 5^2\; mod\; 7 = 0`
            7. :math:`3 + 6^2\; mod\; 7 = 4`


.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Werte in kleine Hashtabelle einfügen

    Belegen Sie eine Hashtabelle mit :math:`n = 5` Feldern mit den Werten 37, 18, 32 und 24 auf Basis von :math:`h^{mod}_5(x)` mit linearer Sondierung, quadratischer Sondierung und doppeltem Hashing mit :math:`h^{'}_5(x) = (x\; mod\; 3) + 1`.

    .. solution:: Lösung
        :pwd: O(1)?

        Bei linearer Sondierung ergibt sich:

        .. csv-table::
            :header: 0, 1, 2, 3, 4

            24, , 37, 18, 32

        Bei quadratischer Sondierung ergibt sich:

        .. csv-table::
            :header: 0, 1, 2, 3, 4

            , 32, 37, 18, 24


        Dei doppeltem Hashing ergibt sich:

        .. csv-table::
            :header: 0, 1, 2, 3, 4

            32, , 37, 18, 24

.. exercise:: Werte in größere Hashtabelle einfügen

    Belegen Sie eine Hashtabelle mit :math:`n = 11` Feldern mit den Werten 37, 49, 26 und 39 auf Basis von :math:`h^{mod}_{11}(x)` mit linearer Sondierung, quadratischer Sondierung und doppeltem Hashing mit :math:`h^{'}_{11}(x) = (x\; mod\; 9) + 1`.

    .. solution:: Lösung
        :pwd: O(1)???

        Bei linearer Sondierung ergibt sich:

        .. csv-table::
            :header: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

            , , , , 37, 49, 26, 39, , ,

        Bei quadratischer Sondierung ergibt sich:

        .. csv-table::
            :header: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

            ,,,,37,49,39,,26,,


        Dei doppeltem Hashing ergibt sich:

        .. csv-table::
            :header: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

            ,,26,,37,49,39,,,,



Angriffe auf algorithmische Komplexität
--------------------------------------------------------

.. epigraph::

    Julian Wälde and Alexander Klink reported that the :java:`String.hashCode()` hash function is not sufficiently collision resistant.

    :java:`hashCode()` value is used in the implementations of [Java 6] HashMap and Hashtable classes. A specially-crafted set of keys could trigger hash function collisions, which can degrade performance of HashMap or Hashtable by changing hash table operations complexity from an expected/average O(1) to the worst case O(n).
    Reporters were able to find colliding strings efficiently using equivalent substrings and meet in the middle techniques.
    This problem can be used to start a denial of service attack against  applications that use untrusted inputs as HashMap or Hashtable keys. An example is a web application server that may fill hash tables with data from HTTP request. A remote attack could use that to make JVM use excessive amount of CPU time by sending a POST request with large amount of parameters which hash to the same value.

    -- [Abbreviated Version] Jan Lieskovsky 2011-11-01


.. TODO: Discuss https://en.wikipedia.org/wiki/MurmurHash
