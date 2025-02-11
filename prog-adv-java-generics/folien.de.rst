.. meta::
    :version: renaissance
    :lang: de
    :author: Michael Eichberg
    :keywords: "Programmierung", "Java", "Generics", "Software Development"
    :description lang=de: Java Generics 
    :id: lecture-prog-java-generics
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
.. role:: smaller
.. role:: far-smaller
.. role:: monospaced
.. role:: java(code)
   :language: java


Java Generics
===========================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0

.. supplemental::

    :Folien: 
        
        |html-source| 

        |pdf-source|

.. 
    :Kontrollfragen:

        .. source:: kontrollfragen.de.rst 
            :path: relative
            :prefix: https://delors.github.io/
            :suffix: .html

    :Klausurvorbereitung:

        .. source:: klausurvorbereitung.de.rst 
            :path: relative
            :prefix: https://delors.github.io/
            :suffix: .html

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: exercises 

Übung 
------------------------------------------------

.. exercise:: Eine einfache Datenstruktur

    1. Implementieren Sie die Datenstruktur ``Pair`` die zwei Werte speichern kann. Die Klasse soll folgende Methoden bereitstellen:

       - :java:`Pair(..., ...)`: Konstruktor
       - :java:`getFirst()` und :java:`getSecond()`: Liefert den ersten/zweiten Wert zurück
       - :java:`void setFirst(...)` und :java:`void setSecond(...)`: Setzt den ersten/zweiten Wert
       - :java:`toString()`: Liefert eine String-Repräsentation des Paares

    2. Erzeugen Sie ein ``Pair``-Objekt mit zwei Integer-Werten (z. B. 1 und 2).
    3. Nutzen Sie die Methoden der Klasse, um die Werte abzufragen und zu addieren.
    4. Speichern Sie das Ergebnis an zweiter Stelle im ``Pair``-Objekt als String.
    5. Geben Sie den zweiten Wert auf der Konsole aus.

    .. solution:: 
        :pwd: EinfachAberUnschoen

        .. include:: code/Pair.java
            :code: java
            :class: copy-to-clipboard
            :number-lines: