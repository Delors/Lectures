.. meta:: 
    :version: renaissance
    :lang: de
    :author: Michael Eichberg
    :keywords: "Hashing", "Hashmaps", "Algorithmen", "Datenstrukturen"
    :description lang=de: Hashing und Hashmaps
    :id: lecture-theo-algo-hashing-and-applications
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
.. role:: obsolete
.. role:: line-above
.. role:: smaller
.. role:: far-smaller
.. role:: monospaced
.. role:: copy-to-clipboard
.. role:: kbd
.. role:: java(code)
   :language: java



.. class .. :: animated-symbol 

Hashing und Hashmaps
======================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0

.. container:: minor

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

.. container:: sl-font-size-70

    +-----------------------------------------+----------+----------+---------+-------------------------+-----------+---------+---------------------------------+
    | Implementation                          | Garantie                      | Durchschnittlicher Fall                       | Operationen auf den Schlüsseln  |
    +                                         +----------+----------+---------+-------------------------+-----------+---------+                                 +
    |                                         | Suchen   | Einfügen | Löschen | Suchen                  | Einfügen  | Löschen |                                 |
    +=========================================+==========+==========+=========+=========================+===========+=========+=================================+
    | sequentielle Suche (unsortierte Liste)  | N        | N        | N       | ½ N                     | N         | ½ N     | equals()                        |
    +-----------------------------------------+----------+----------+---------+-------------------------+-----------+---------+---------------------------------+
    | binäre Suche (geordnetes Array)         | lg N     | N        | N       | lg N                    | ½ N       | ½ N     | compareTo()                     |
    +-----------------------------------------+----------+----------+---------+-------------------------+-----------+---------+---------------------------------+
    | BST [#]_                                | N        | N        | N       | 1.39 lg N               | 1.39 lg N | √ N     | compareTo()                     |
    +-----------------------------------------+----------+----------+---------+-------------------------+-----------+---------+---------------------------------+


.. container:: question incremental

    Können wir effizienter suchen?

.. [#] Binary Search Tree

.. container.. block-footer text-align-center dhbw-light-gray-background dhbw-dark-gray
    Wiederholung


Hashing - Grundidee
--------------------------------------------------------

.. stack:: invisible

    .. layer::

        .. image:: images/Hashkollision.svg
            :alt: Hashmap
            :align: right
            
        .. class:: incremental list-with-explanations

        - Die Elemente werden über den Schlüssel indexiert in einer Tabelle gespeichert.
        
          Der Index ist eine Funktion des Schlüssels.
        - Hash-Funktion:  Methode zur Berechnung des Array-Index aus dem Schlüssel.

    .. layer:: incremental

        .. container:: challenges
            
            .. rubric:: Herausforderungen
            
            1. Berechnung der Hash-Funktion.
            2. Gleichheitstest: Methode zur Überprüfung, ob zwei Schlüssel gleich sind.
            3. Kollisionsauflösung: Algorithmus und Datenstruktur zur Behandlung von zwei Schlüsseln, die auf denselben Array-Index hindeuten.

    .. layer:: incremental

        .. hint:: 

            .. rubric:: Klassischer Kompromiss zwischen Raum und Zeit!

            - Keine Platzbeschränkung: triviale Hash-Funktion mit Schlüssel als Index.
            - Keine Zeitbeschränkung: triviale Kollisionsauflösung mit sequentieller Suche.
            - Raum- und Zeitbeschränkung: Hashing (die reale Welt).


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
    :Besser: die letzten drei Ziffern.

.. container:: example incremental

    .. rubric:: Beispiel 2.  Sozialversicherungsnummer

    :Schlecht: die ersten beiden Ziffern.
    :Besser: die letzten Ziffern.

    .. supplemental::

        Die ersten beiden Stellen bei der Sozialversicherungsnummer identifizieren den Rentenversicherungsträger. 

.. container:: challenge incremental

    Praktische Herausforderung: für jeden Schlüsseltyp ist ein anderer Ansatz erforderlich.


