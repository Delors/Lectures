.. meta:: 
    :lang: de
    :author: Michael Eichberg
    :keywords: "genetic programming", "python"
    :description lang=de: Genetische Programmierung
    :id: lecture-theo-genetic_programming
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


.. |chromosone.py| source:: code/lib/chromosome.py
            :path: relative
            :prefix: https://delors.github.io/

.. |genetic_algorithm.py| source:: code/lib/genetic_algorithm.py
            :path: relative
            :prefix: https://delors.github.io/

.. |group_assignment_template.py| source:: code/group_assignment_template.py
            :path: relative
            :prefix: https://delors.github.io/



.. class:: animated-symbol 

Genetische Programmierung
======================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0

.. container:: minor

    :Quelle: 
       
       Davic Kopec, Algorithmen in Python, Rheinwerk Computing


.. supplemental::

    :Folien: 
        
        |html-source| 

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-move-to-top

Einführung
--------------------------------------------------------

Genetische Algorithmen
--------------------------------------------------------

.. class:: incremental list-with-explanations

- werden eingesetzt, wenn traditionelle Algorithmen nicht geeignet sind oder keine offensichtlichen Algorithmen existieren

  Z. B. im Bereich des Wirkstoffdesigns oder der Optimierung von Produktionsprozessen.
- benötigen im Prinzip nur eine Definition des Aufgabenziels
- :minor:`(Implementierungen)` sind in der Regel hochgradig spezialisiert; hier diskutieren wir eine allgemeine/generische Implementierung


(Biologische) Grundlagen
--------------------------------------------------------

.. class:: incremental 

- Genetische Algorithmen basieren auf der Evolutionstheorie:

  .. class:: dd-margin-left-10em

  :Selektion: die besten Individuen überleben
  :Mutation: zufällige Veränderungen
  :Rekombination: Kreuzung von Individuen

- Terminologie:

  .. class:: dd-margin-left-10em

  :Population: Menge von Individuen, die auch Chromosomen genannt werden und Lösungskandidaten repräsentieren
  :Chromosomen: Lösungskandidaten oder auch Individuen, die aus Genen bestehen
  :Gene: Eigenschaften eines Lösungskandidaten
  :Fitness(-Funktion): Bewertung eines Chromosoms ( Lösungskandidaten)



.. supplemental::

   D. h. auf der natürlichen Auslese und der Vererbung von Eigenschaften. Ein Individuum, das besser an die Umwelt angepasst ist, hat eine höhere Wahrscheinlichkeit, sich fortzupflanzen und seine Gene weiterzugeben. Schlechter angepasste Individuen sterben aus.

   Klassisches Beispiel ist die Entwicklung von Resistenzen gegen Antibiotika bei Bakterien.


Grundidee
--------------------------------------------------------

.. class:: incremental 

- ein genetischer Algorithmus durchläuft Generationen
- In jeder Generation werden die Individuen/Chromosomen bewertet und die besser angepassten Individuen (welche *fitter* sind) mit einer höheren Wahrscheinlichkeit zum Überleben und zur Fortpflanzung ausgewählt
- in jeder Generation werden die Individuen mit einer gewissen Wahrscheinlichkeit: 

  1. rekombiniert (:java:`crossover` von zwei Chromosomen) und 
  2. mutiert (:java:`mutate`)


.. warning:: 
    :class: incremental

    Genetische Algorithmen sind somit stochastische Algorithmen, d. h. sie liefern nicht immer das gleiche Ergebnis in der gleichen Zeitspanne und es gibt keine Garantie, dass sie das beste Ergebnis finden.

.. supplemental::

    Genetische Algorithmen sind eine gute Wahl, wenn es keinen schnellen deterministischen Algorithmus gibt.


.. class:: new-section transition-move-to-top

Grundlegendes Framework in Python
--------------------------------------------------------



Basisklasse für Chromosomen    
--------------------------------------------------------

.. include:: code/lib/chromosome.py
    :code: python
    :number-lines:
    :class: far-smaller
    :start-after: # Base class for all chromosomes

.. supplemental::

    Die Basisklasse für Chromosomen (|chromosone.py|) definiert die Methoden, die jedes Chromosom implementieren muss: Erzeugen einer zufälligen Instanz (:java:`random_instance`), eine Methode zum Mutieren einer Instanz (:java:`mutate`) und eine Methode zum Kreuzen einer Instanz mit einer anderen (:java:`crossover`) und schließlich eine Methode zur Bewertung eines Chromosoms (:java:`fitness`).

    :`mutate`:python:: führt eine kleine zufällige Änderung durch
    :`crossover`:python:: kombiniert zwei Chromosomen zu einem neuen Chromosom; d. h. mischt zwei Lösungen
    :`fitness`:python:: bewertet die eigene Fitness
    :`random_instance`:python:: erzeugt eine zufällige Instanz; wir nur am Anfang benötigt, um die Population zu initialisieren



Ablauf von genetischen Algorithmen
--------------------------------------------------------

1. Erzeugen einer zufälligen Population

.. class:: incremental

2. Miss die Fitness der Individuen, wenn einer den Zielwert erreicht, beende den Algorithmus und gib das Individuum zurück
3. Wähle einige Individuen aus, die sich fortpflanzen - bevorzuge die Fitteren mit einer höheren Wahrscheinlichkeit
4. Kombiniere die ausgewählten Individuen, um neue Individuen zu erzeugen
5. Mutiere einige Individuen, um die neue Generation zu vervollständigen
6. Wiederhole die Schritte 2-5 für eine bestimmte Anzahl von Generationen


Parameter von genetischen Algorithmen
--------------------------------------------------------

.. class:: incremental

- Größe der Population
- Design der ersten Population (rein zufällig oder basierend auf einer Heuristik)
- Wahl des Schwellenwertes, der angibt, wann der Algorithmus beendet wird
- Auswahl der Chromosomen, die sich fortpflanzen
- Wie und mit welcher Wahrscheinlichkeit die Rekombination erfolgt (:java:`crossover`)
- Wie und mit welcher Wahrscheinlichkeit eine Mutation erfolgt (:java:`mutate`)
- Wie viele Generationen max. durchlaufen werden

.. supplemental:: 

    Es gibt zwei typische Strategien zur Auswahl der Chromosomen, die sich fortpflanzen: die *Tournament Selection* und die *Roulette Wheel Selection*.


Selektionsstrategien von Chromosomen
--------------------------------------------------------

.. rubric:: Bestimmung der Chromosomen, die überleben und sich ggf. fortpflanzen.

.. container:: scrollable

    .. rubric:: Auswahlstrategien

    .. class:: incremental

    :*Tournament Selection*: 
        
        Wähle zufällig einige Chromosomen aus und wähle das beste(/die besten) Chromosom(en) aus dieser Gruppe:

        .. include:: code/lib/genetic_algorithm.py
            :code: python
            :number-lines:
            :class: smaller
            :start-after: # Choose num_participants at random and take the best 2
            :end-before: # type: ignore

    .. class:: incremental

    :*Roulette Wheel Selection*:

        Jedes Chromosom wird mit einer Wahrscheinlichkeit ausgewählt, die proportional zu seiner Fitness ist (die Fitness wird in eine Wahrscheinlichkeit umgerechnet).

        .. include:: code/lib/genetic_algorithm.py
            :code: python
            :number-lines:
            :class: smaller
            :start-after: # Note: will not work with negative fitness results
            :end-before: # Choose num_participants at random and take the best 2

    .. container:: incremental

        .. rubric:: Vorgehen

        Für jedes gewählte Chromosomenpaar bestimme, ob diese rekombiniert werden sollen. Wenn ja, führe die Rekombination durch. Wenn nicht, kopiere die Chromosomen einfach in die neue Generation.

        Wiederhole die Selektion und ggf. Rekombination, bis die gewünschte Anzahl von Chromosomen ausgewählt wurde.



Initialisierung des genetischen Algorithmus
--------------------------------------------------------

.. include:: code/lib/genetic_algorithm.py
    :code: python
    :number-lines:
    :class: far-smaller
    :start-after: from lib.chromosome import Chromosome
    :end-before:  -> None:



.. class::  no-title center-child-elements

Herausforderungen bei der Entwicklung genetischer Algorithmen
---------------------------------------------------------------

.. container:: box-shadow rounded-corners padding-1em

    Die Herausforderung bei der Entwicklung von genetischen Algorithmen ist zweigeteilt:

    .. class:: incremental

    1. eine passende Formulierung für das Problem zu entwickeln und 
    2. danach die Parameter so zu wählen, dass der Algorithmus in einer akzeptablen Zeit eine gute Lösung findet.



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

.. [#] Wir verwenden hier das Beispiel lediglich zur Demonstration der Technik. Das Problem kann auch anders gelöst werden!



SEND+MORE=MONEY - Umsetzung
----------------------------------------------------------

.. stack::

    .. layer:: 

        .. rubric:: Chromosomen ermöglichen Zuordnung von Buchstaben zu Ziffern

        **Idee**: Verwendung von Listenindizes zur Repräsentation der Ziffern: [0,...,9]. Die Werte in der Liste entsprechen den Buchstaben; Beispiel:

        *Zuordnung von Ziffern zu Buchstaben:*

        .. csv-table::
            :header: "Index: ", "0", "1", "2", "3", "4", "5" , "6", "7" ,"8", "9" , " "
            :widths: 25, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10
            :width: 100%
            :class: no-table-borders no-inner-borders

            "letters = \[", "O,", "M,", "Y,", "␣,", "␣,", "E,", "N,", "D,", "R,", "S", "]"

        .. container:: minor

            *Für die Lösung würde sich ergeben:*
            
            .. csv-table::
                    :header: "S", "E", "N", "D", "M", "O", "R", "Y" ,"␣ ", "␣"
                    :widths: 10, 10, 10, 10, 10, 10, 10, 10, 10, 10
                    :class: no-table-borders
        
                    9, 5, 6, 7, 1, 0, 8, 2, 3 ,4

        .. container:: incremental

            Somit kann durch die Verschiebung der Buchstaben in der Liste der assoziierte Wert geändert werden.

    .. layer:: incremental

        .. rubric:: Klasse für Chromosomen

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: smaller
            :start-after: # class SendMoreMoney(Chromosome)
            :end-before: # The fitness function


    .. layer:: incremental

        .. rubric:: Erzeugen von Individuen bzw. Chromosomen

        **Idee**: beliebige Verwürfelung der Buchstaben in der Liste.
        
        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: smaller
            :start-after: # random_instance()
            :end-before: # crossover()


    .. layer:: incremental

        .. rubric:: Mutation eines Chromosoms

        **Idee**: zufälliger paarweiser Austausch von zwei Buchstaben in der Liste.

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: smaller
            :start-after: # mutate()
            :end-before: # __str__()

    .. layer:: incremental

        .. rubric:: Vererbung

        **Idee**: pro Nachkommen sicherstellen, dass einige Buchstaben Indizes (d. h. Wertzuordnungen) von einem Elternteil und einige vom anderen Elternteil stammen.

        Beispiel (verkürzt):

        .. container:: slightly-more-smaller

            ::

                Elternteil 1 = [a,b,c,d,e]  # d.h. ein Chromosom
                Elternteil 2 = [c,a,b,e,d]  # d.h. ein Chromosom

        gewählte Indizes seien: 0 und 2 

        .. container:: slightly-more-smaller

            ::    
                    
                Nachkomme 1 = [a,c,b,d,e] # im Wesentlichen Elternteil 1, 
                                            aber Position von "b" vom 2ten übernommen
                                            (dadurch Anpassung der Pos. von "c" notw.)
                Nachkomme 2 = [a,c,b,e,d] # im Wesentlichen Elternteil 2, 
                                            aber Position von "a" vom 1ten übernommen
                                            (dadurch Anpassung der Pos. von "c" notw.)

    .. layer:: incremental

        .. rubric:: Vererbung - Implementierung

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: smaller
            :start-after: # crossover()
            :end-before: # mutate()

    .. layer:: incremental

        .. rubric:: Fitness-Funktion

        **Idee**: Eine Lösung ist besser, je näher die Summe der Ziffern von SEND und MORE an MONEY ist. D. h.: 

        .. container:: incremental

            .. math:: 

                \text{minimiere}(|money - (send + more)|)


        .. container:: incremental
        
            **Feststellung**: Das Ziel des generischen genetischen Algorithmus ist es, die Fitness-Funktion zu maximieren. Wir müssen somit unser Minimierungsproblem in ein Maximierungsproblem umwandeln.

            .. math:: 

                \frac{1} { |money - (send + more)| + 1 }\\

        .. container:: incremental far-smaller

            Beispiel: Sei die Differenz :math:`1`, dann ist die Fitness :math:`1/2`; bei einer Differenz von :math:`0` ist die Fitness :math:`1` und somit maximal.

    .. layer:: incremental

        .. rubric:: Fitness-Funktion - Implementierung

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: smaller
            :start-after: # fitness()
            :end-before: @classmethod

    .. layer:: incremental

        .. rubric:: Initialisierung

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: smaller
            :start-after: if __name__ == "__main__":

    .. layer:: incremental

        .. rubric:: Exemplarische Ausführungen zeigt stochastische Natur

        .. container:: far-far-smaller

            *1. Lauf (237 Generationen)*

            ::

                Generation 0 Best 0.03333333333333333 Avg 0.0001322689088530895
                ︙                
                Generation 236 Best 0.5 Avg 0.26552306542712417
                9567 + 1085 = 10652 Difference: 0

            *2. Lauf (2 Generationen)*

            ::

                Generation 0 Best 0.025 Avg 0.0001658511923601707
                Generation 1 Best 0.3333333333333333 Avg 0.003446183841665406
                7429 + 814 = 8243 Difference: 0

            *3. Lauf (4 Generationen)*

            ::

                Generation 0 Best 0.006896551724137931 Avg 0.00012684576568285674
                ︙
                Generation 3 Best 0.5 Avg 0.01426133638316889
                5731 + 647 = 6378 Difference: 0



.. class:: integrated-exercise transition-move-to-top

Übung
--------------------------------------------------------

.. exercise:: Gruppenzuteilung

    Entwickeln Sie einen Algorithmus, der eine sehr gute Aufteilung von Personen auf eine feste Anzahl an Gruppen findet. Nutzen Sie das Framework für genetische Algorithmen, als Grundlage.

    Im Template ist eine initiale Aufgabenstellung hinterlegt, die es zu lösen gilt: Verteilung von 16 Studierenden auf 4 Gruppen inkl. Bewertungsmatrix :minor:`(jeder Studierende hat jeden anderen mit Werten von 1 bis 10 bewertet)`.

    .. container:: slightly-more-smaller rounded-corners box-shadow padding-1em

        .. rubric:: Basis (Achten Sie auf die Verzeichnisse)

        |chromosone.py|

        |genetic_algorithm.py|

        |group_assignment_template.py|


    .. solution::
        :pwd: NUR_ein_VORschlag

        *Ein Lösungsvorschlag*

        .. include:: code/group_assignment.py
            :code: python
            :number-lines:
            :class: smaller

.. supplemental::

    Denken Sie daran, dass Sie die Parameter bzgl. :java:`mutation_chance`, :java:`crossover_chance`, :java:`selection_type`, :java:`initial_population` und :java:`max_generations` anpassen können. 
    
    Sie können für die Darstellung der Mitglieder einer Gruppe auch eine andere Darstellung als eine Liste von Listen verwenden. Sie müssen dann nur ggf. auch die :java:`__str__` Methode anpassen.

    Sieger ist, wer in einem akzeptablen Zeitrahmen (3 Minuten) die beste Lösung findet. 
