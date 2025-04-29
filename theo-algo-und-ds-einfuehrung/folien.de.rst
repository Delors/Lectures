.. meta::
    :version: renaissance
    :lang: de
    :author: Michael Eichberg
    :keywords: "Datenstrukturen", "Algorithmen"
    :description lang=de: Algorithmen und Datenstrukturen - Wiederholung und Einführung
    :id: lecture-theo-algo-ds-einfuehrung
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!
    
.. include:: ../docutils.defs



Algorithmen und Datenstrukturen - eine Einführung
====================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1

.. container:: peripheral footer-left

    :Quelle: 
        Die Folien sind teilweise inspiriert von oder basierend auf:
        
        Lehrmaterial von Prof. Dr. Scherer und Prof. Dr. Baumgart 

.. supplemental::

    :Folien: 
        
        |html-source| 

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-move-to-top

Einführung 
--------------------------------------------------------



.. class:: repetition

Was ist ein Algorithmus?
--------------------------

.. definition::

    Ein Algorithmus ist eine endliche, wohl-definierte Abfolge von Anweisungen, die zur Lösung eines Problems führen.

.. deck::

    .. card:: 

        .. example::
            :class: incremental

            .. class:: incremental-list 

            - Berechnen / Arithmetik
            - Suchen
            - Sortieren
            - Planen
            - Simulieren
            - Visualisieren und Interagieren

    .. card:: 

        Die Spezifikation eines Algorithmus kann erfolgen...

        .. class:: incremental-list

        - in einer Programmiersprache (z. B. Java, Python)
        - in einer formalen Sprache (z. B. Z3, Alloy)
        - in einer natürlichen Sprache (z. B. Deutsch, Englisch)
        - in einer graphischen Sprache (Diagramme, z. B. UML)



.. class:: repetition

Was ist eine Datenstruktur?
-----------------------------

.. definition::

    Eine Datenstruktur beschreibt eine Möglichkeit...

    .. class: incremental-list 

    - Daten zu organisieren, 
    - Daten zu verwalten und 
    - Daten zu speichern, 
    - um sie effizient zu nutzen.

.. example::
    :class: incremental

    .. class:: incremental-list 

    - *Primitive* 
    - Arrays
    - Listen
    - Bäume
    - Graphen
    - ...


.. class:: new-section transition-move-to-top

Algorithmen
--------------------------------------------------------

Algorithmus: Beispiel
--------------------------------------------------------

.. admonition:: Aufgabe

    Gegeben ein nicht-sortiertes Array X mit N Integer Elementen. Was ist das größte Element?

.. deck:: incremental
    
    .. card:: 

        .. rubric:: Algorithmus - natürlich sprachlich

        .. class:: incremental-list

        1. Nehme an, das erste Element ist das Maximum
        2. Vergleiche das nächste Element des Arrays mit dem bisherigen Maximum.
        3. Wenn es größer ist, aktualisiere das Maximum auf dieses Element
        4.  Wiederhole 2 und 3 bis wir am Ende des Arrays angelangt sind

    .. card::

        .. rubric:: |java-icon| Algorithmus 

        .. code:: java
            :number-lines: 
            :class: copy-to-clipboard

            public int max(int[] x) {
                int max = x[0];
                for (int i = 1; i < x.length; i++) {
                    if (x[i] > max) {
                        max = x[i];
                    }
                }
                return max;
            }

    .. card::  

        .. rubric:: |python-icon| Algorithmus 

        .. code:: python
            :number-lines: 
            :class: copy-to-clipboard

            def max(x):
                max = x[0]
                for i in range(1, len(x)):
                    if x[i] > max:
                        max = x[i]
                return max 

    .. card::
        
        .. rubric:: Analyse (aller drei Implementierungen)

        .. class:: incremental-list dd-margin-left-12em

        :Zeitkomplexität: :incremental:`hängt direkt von der Anzahl der Elemente ab`
        :Speicherkomplexität: :incremental:`eine Variable für das Maximum`
        :deterministisch: :incremental:`ja`
        :endliche Ausführung: :incremental:`ja`



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Mehrheitselement finden

    1. Schreiben Sie einen Algorithmus, um das *Mehrheitselement* in einem unsortierten Array von Integer Werten zu finden. Das *Mehrheitselement* ist das Element, das **mehr als** :math:`⌊n/2⌋` mal vorkommt. Sie können davon ausgehen, dass das Mehrheitselement immer vorhanden ist.

    2. Wie schnell ist Ihr Algorithmus? Wovon hängt dies maßgeblich ab? (Best- und Worst-Case)
    3. Wie viel Speicherplatz benötigt Ihr Algorithmus?

    .. solution::
        :pwd: SchnellSchneller

        .. rubric:: (nicht-optimierter) Naiver Algorithmus

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            public int findeMehrheitsElement(int[] a) {
                final var minOccurences = a.length / 2 + 1;
                for (var v : a) {
                    var occurences = 0;
                    for (var i : a) {
                        if (i == v) {
                            occurences += 1;
                        }
                    }
                    if (occurences >= minOccurences) {
                        return v;
                    }
                }
                throw new IllegalArgumentException();
            }


        .. rubric::  Boyer-Moore-Algorithmus 

        .. code:: java 
            :number-lines:
            :class: copy-to-clipboard

            public int majorityElement(int[] nums) {
                if (nums == null || nums.length == 0) {
                    throw new IllegalArgumentException("Array is empty");
                }
                int count = 0;
                int candidate = 0; // this value will be replaced immediately

                for (int num : nums) {
                    if (count == 0) {
                        candidate = num;
                    }
                    count += (num == candidate) ? 1 : -1;
                }

                return candidate;
            }

        .. rubric::  Zeitkomplexität
        
        Linear abhängig von der Anzahl der Elemente.

        .. rubric::  Speicherkomplexität
            
        Es werden nur zwei Variablen benötigt, um den Zähler und den Kandidaten zu speichern. Daher ist der Speicherplatz konstant.



.. class:: new-section transition-fade

Datenstrukturen
--------------------------------------------------------


.. class:: repetition

Array
--------------------------------------------------------

.. definition::

    Ein Array ist eine Datenstruktur, die eine
    Sammlung von Elementen gleicher Größe in einem
    :incremental:`kontinuierlichen,` 
    :incremental:`d. h. zusammenhängenden Speicherbereich speichert.`

.. example::
    :class: incremental

    .. code:: java
        :number-lines:
        :class: copy-to-clipboard incremental

        var myIntArray = new int[5] {1,2,3,9,-5};

    .. code:: java
        :number-lines:
        :class: copy-to-clipboard incremental

        var myBooleanArray = new boolean[2] {true,false};

    .. code:: java
        :number-lines:
        :class: copy-to-clipboard incremental

        var myDoubleArray = new double[2] {1.0,-3};    

.. question::
    :class: incremental

    .. class:: incremental-list

    - Wie groß ist der Speicherplatz, den ein Array mit N Elementen benötigt?
    - Wie schnell ist der Zugriff auf ein Element (n)?
    



Dynamische Arrays
--------------------------------------------------------

.. definition::

    Ein dynamisches Array (Liste) ist eine Datenstruktur, die eine
    Sammlung von Elementen gleicher Größe in einem kontinuierlichen,
    d. h. zusammenhängenden Speicherbereich speichert :incremental:`und erweiterbar ist.`

    .. class:: incremental-list list-with-explanations

    - Ein dynamisches Array ist ein Array, das die Größe anpassen kann. 
    
      Typischwerweise wird ein neues Array mit doppelter Größe erstellt, wenn der Speicherplatz erschöpft ist. Die Elemente des alten Arrays werden dann in das neue Array kopiert.
    - dynamische Arrays werden basierend auf (statischen) Arrays implementiert

.. question::
    :class: incremental

    .. class:: incremental-list

    - Wie schnell ist das Einfügen eines neuen Elements?
    - Wie schnell ist das Löschen eines Elements?

    

Verkettete Listen
--------------------------------------------------------


.. definition::

    Eine verkettete Liste ist eine Datenstruktur, die eine
    Sammlung von Elementen in Knoten speichert. 

    .. class:: incremental-list list-with-explanations

    - Jeder Knoten enthält einen Zeiger auf das nächste Element und speichert den aktuellen Wert.
    - Im Falle einer doppelt verketteten Liste enthält jeder Knoten auch einen Zeiger auf das vorherige Element.
    - Verkettete Listen sind dynamisch und können beliebig wachsen.
    - Verkettete Listen sind nicht auf einen festen Speicherplatz angewiesen.



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Doppelt verkettete Liste

    .. class:: list-with-explanations

    1. Implementieren Sie eine generische, doppelt verkettete Liste in Java.

       Orientieren Sie sich ggf. an der Implementierung für eine einfach verkettete Liste.
    
    .. class:: list-with-sublists

    2. Implementieren Sie die folgenden Methoden.  Bestimmen Sie das *worst-case* verhalten der Operationen (Laufzeit) in Abhängigkeit von der Anzahl der Elemente N, die bereits in der Liste gespeichert sind. 

       - Einfügen eines neuen Wertes
       - Löschen eines Wertes
       - Überprüfen ob ein Wert vorhanden ist
       - eine Funktion (forEachReverse), um von hinten nach vorne durch die Liste zu iterieren

    .. solution::
        :pwd: DoppeltHaeltBesser

        .. code:: Java
            :number-lines:
            :class: copy-to-clipboard

            class DoublyLinkedList<T> {
                private Node head;
                private Node tail;

                private class Node {
                    T data;
                    Node next;
                    Node prev;

                    public Node(T data) {
                        this.data = data;
                    }
                }

                // Aufwand: Konstant
                public void insert(T data) {
                    Node newNode = new Node(data);
                    if (head == null) {
                        head = newNode;
                        tail = newNode;
                    } else {
                        tail.next = newNode;
                        newNode.prev = tail;
                        tail = newNode;
                    }
                }

                // Aufwand: Linear
                public void delete(T data) {
                    Node current = head;
                    while (current != null) {
                        if (current.data.equals(data)) {
                            if (current.prev != null) {
                                current.prev.next = current.next;
                            } else {
                                head = current.next;
                            }
                            if (current.next != null) {
                                current.next.prev = current.prev;
                            } else {
                                tail = current.prev;
                            }
                            break;
                        }
                        current = current.next;
                    }
                }

                // Aufwand: Linear
                public boolean contains(T data) {
                    Node current = head;
                    while (current != null) {
                        if (current.data.equals(data)) {
                            return true;
                        }
                        current = current.next;
                    }
                    return false;
                }

                // Aufwand: Linear
                public void forEachReverse(java.util.function.Consumer<T> action) {
                    Node current = tail;
                    while (current != null) {
                        action.accept(current.data);
                        current = current.prev;
                    }
                }
            }
                
