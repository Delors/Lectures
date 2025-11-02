.. meta::
    :lang: de
    :author: Michael Eichberg
    :keywords: "Programmierung", "Java", "Grundlagen"
    :description lang=de: Fragen und Aufgaben zur Klausurvorbereitung bzgl. Java Grundlagen
    :id: lecture-prog-java-grundlagen-klausurvorbereitung
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Java Grundlagen - Optionale Aufgaben
===================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0.1


.. class:: new-section

Algorithmisches Denken
-----------------------


.. class:: exercises

Aufgabe
--------

.. exercise:: Berechnung der Quadratwurzel 
    
    Bearbeitungszeit: ca. 10 Minuten für erste Lösung; ca. 15 Minuten für beide Lösungen.

    Implementieren Sie eine Methode :java:`sqrt(double x, double epsilon)`, die die Quadratwurzel von :java:`x` mit einer Genauigkeit von :java:`epsilon` berechnet. Verwenden Sie dazu das Newton-Raphson-Verfahren: :math:`y_{n+1} = \frac{1}{2}\left(y_n + \frac{x}{y_n}\right)`. Implementieren Sie die Methode einmal rekursiv und einmal iterativ.
    Der Abbruch soll erfolgen, wenn :math:`|y_{n+1} - y_n| < \epsilon`.

    .. supplemental::

        .. hint::

            Für die rekursive Variante kann es sinnvoll sein neben der Hauptmethode eine zweite Hilfsmethode zu implementieren.

    .. example:: 

        .. code:: bash

            # java SQRT.java                  
            Enter number to compute SQRT for: 9
            Enter epsilon: 0.0001
            9.0 => 5.0
            ...
            3.00009155413138 => 3.000000001396984
            The SQRT of 9.0 is 3.000000001396984

    .. solution::
        :pwd: SoGehtEs...

        .. include:: code/SQRT.java
            :code: java
            :number-lines:



.. class:: exercises

Aufgabe
--------

.. exercise:: Rekursive Berechnung von E

    Berechnen Sie den Wert von :math:`e` auf eine vom Nutzer festgelegte Anzahl Stellen. Nutzen Sie dafür die Formel: :math:`e = \sum_{i=0}^{\infty} \frac{1}{i!} = \frac{1}{0!} + \frac{1}{1!} + \frac{1}{2!} + \ldots`. Implementieren Sie die Funktion rekursiv. Die Signatur der Hauptfunktion muss wie folgt sein: :java:`double e(double precision)`.

    .. hint::

        Sie brauchen ggf. Hilfsfunktionen.

    .. example::

        .. code:: bash

            # java E.java                  
            Enter number of decimal places of accuracy (> 0):10
            E = 2.71828182845823

    .. solution::
        :pwd: SoGehtDieBerechnungVonE

        .. include:: code/E.java
            :code: java
            :number-lines:



