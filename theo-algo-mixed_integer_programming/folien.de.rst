.. meta:: 
    :lang: de
    :author: Michael Eichberg
    :keywords: "mixed-integer programming", "python"
    :description lang=de: Lineare und Mixed-Integer-Programmierung
    :id: lecture-theo-mixed_integer_programming
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
.. role:: python(code)
   :language: python


.. |group_assignment_template.py| source:: code/group_assignment_template.py
            :path: relative
            :prefix: https://delors.github.io/


.. class:: animated-symbol 

Lineare und Mixed-Integer Programmierung
======================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0

.. container:: minor

    :Quelle: 
       
       TBD


.. supplemental::

    :Folien: 
        
        |html-source| 

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-move-to-top

Einführung
--------------------------------------------------------


.. class:: new-section transition-move-to-top

Beispiel: SEND + MORE = MONEY
--------------------------------------------------------



SEND+MORE=MONEY\ [#]_
----------------------------------------------------------

- Klassisches Problem der Kryptographie
- Jeder Buchstabe repräsentiert eine Ziffer von 0 bis 9
- Keine Ziffer darf doppelt vorkommen
  
  :: 
    
      S E N D
    + M O R E
    ---------
    M O N E Y

- Welcher Buchstabe steht für welchen Wert?

.. [#] Wir verwenden hier das Beispiel lediglich zur Demonstration der Technik. 



SEND+MORE=MONEY - Umsetzung
----------------------------------------------------------



.. class:: integrated-exercise transition-move-to-top

Übung
--------------------------------------------------------

.. exercise:: Gruppenzuteilung

    Finden Sie eine sehr gute Aufteilung von Personen (Studierenden) auf eine feste Anzahl an Gruppen, basierend auf den Präferenzen der Personen. Nutzen Sie dazu Mixed-Integer-Programmierung.

    Im Template ist eine initiale Aufgabenstellung hinterlegt, die es zu lösen gilt: Verteilung von 16 Studierenden auf 4 Gruppen inkl. Bewertungsmatrix :minor:`(jeder Studierende hat jeden anderen mit Werten von 1 bis 10 bewertet)`.

    .. container:: slightly-more-smaller rounded-corners box-shadow padding-1em

        |group_assignment_template.py|


    .. solution::
        :pwd: ALLE Kombinationen bewerten

        *Ein Lösungsvorschlag*

        .. include:: code/group_assignment.py
            :code: python
            :number-lines:
            :class: smaller

