.. meta::
    :version: renaissance
    :lang: de
    :author: Michael Eichberg
    :keywords: genetische Algorithmen, Python
    :description lang=de: Implementierung von genetischen Algorithmen in Python
    :id: vorlesung-genetische_algorithmen
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs

.. |chromosone.py| source:: code/lib/chromosome.py
            :path: relative
            :prefix: https://delors.github.io/

.. |genetic_algorithm.py| source:: code/lib/genetic_algorithm.py
            :path: relative
            :prefix: https://delors.github.io/

.. |group_assignment_template.py| source:: code/group_assignment_template.py
            :path: relative
            :prefix: https://delors.github.io/



.. class:: animated-logo

Genetische Algorithmen
======================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0
:Quelle:  Davic Kopec, Algorithmen in Python, Rheinwerk Computing, 2023 (korrigierte Auflage)

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

.. class:: incremental-list list-with-explanations

- werden eingesetzt, wenn traditionelle Algorithmen nicht geeignet sind oder keine offensichtlichen Algorithmen existieren

  Z. B. im Bereich des Wirkstoffdesigns oder der Optimierung von Produktionsprozessen.
- benötigen im Prinzip nur eine Definition des Aufgabenziels
- :peripheral:`(Implementierungen)` sind in der Regel hochgradig spezialisiert; hier diskutieren wir eine allgemeine/generische Implementierung



(Biologische) Grundlagen
--------------------------------------------------------

.. class:: incremental-list

- Genetische Algorithmen basieren auf der Evolutionstheorie:

  .. class:: dd-margin-left-10em

  :Selektion: die besten Individuen überleben
  :Mutation: zufällige Veränderungen
  :Rekombination: Kreuzung von Individuen

- Terminologie:

  .. class:: dd-margin-left-10em

  :Population: Menge von Individuen, die auch Chromosomen genannt werden und Lösungskandidaten repräsentieren
  :Chromosomen: Lösungskandidaten oder auch Individuen, die aus Genen bestehen
  :`Gene`:peripheral:: :peripheral:`Eigenschaften eines Lösungskandidaten`
  :Fitness(-Funktion): Bewertung eines Chromosoms ( Lösungskandidaten)

.. supplemental::

   D. h. auf der natürlichen Auslese und der Vererbung von Eigenschaften. Ein Individuum, das besser an die Umwelt angepasst ist, hat eine höhere Wahrscheinlichkeit, sich fortzupflanzen und seine Gene weiterzugeben. Schlechter angepasste Individuen sterben aus.

   Klassisches Beispiel ist die Entwicklung von Resistenzen gegen Antibiotika bei Bakterien.



Grundidee
--------------------------------------------------------

.. class:: incremental-list

- ein genetischer Algorithmus durchläuft Generationen
- In jeder Generation werden die Individuen/Chromosomen bewertet und die besser angepassten Individuen (solche die *fitter* sind) mit einer höheren Wahrscheinlichkeit zum Überleben und zur Fortpflanzung ausgewählt
- in jeder Generation werden die Individuen mit gewissen Wahrscheinlichkeiten:

  1. rekombiniert (:java:`crossover` von zwei Chromosomen) und
  2. mutiert (:java:`mutate`)

.. warning::
    :class: incremental margin-top-1em

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
    :start-after: # Base class for all chromosomes

.. supplemental::

    Die Basisklasse für Chromosomen (|chromosone.py|) definiert die Methoden, die jedes Chromosom implementieren muss: Eine Klassenmethode zum Erzeugen einer zufälligen Instanz (:python:`random_instance`), und Instanzmethoden zum Mutieren einer Instanz (:python:`mutate`), zum Kreuzen einer Instanz mit einer anderen (:python:`crossover`) und schließlich zur Bewertung eines Chromosoms (:python:`fitness`).

    :`mutate`:python:: führt eine kleine zufällige Änderung (an den Genen) durch
    :`crossover`:python:: kombiniert zwei Chromosomen zu einem neuen Chromosom; d. h. mischt zwei Lösungen
    :`fitness`:python:: bewertet die eigene Fitness
    :`random_instance`:python:: erzeugt eine zufällige Instanz; wird nur einmal am Anfang benötigt, um die Population zu initialisieren



Ablauf von genetischen Algorithmen
--------------------------------------------------------

1. Erzeugen einer zufälligen Population

.. class:: incremental-list 

2. Miss die *Fitness* der Individuen, wenn einer den Zielwert erreicht, beende den Algorithmus und gib das Individuum zurück
3. Wähle einige Individuen aus, die sich fortpflanzen - bevorzuge die Fitteren mit einer höheren Wahrscheinlichkeit
4. Kombiniere die ausgewählten Individuen, um neue Individuen zu erzeugen
5. Mutiere einige Individuen, um die neue Generation zu vervollständigen

.. class:: incremental-list 

6. Wiederhole die Schritte 2-5 für eine bestimmte Anzahl von Generationen


Parameter von genetischen Algorithmen
--------------------------------------------------------

.. class:: incremental-list

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

.. story:: 

    .. rubric:: Auswahlstrategien

    .. class:: incremental-list

    :*Tournament Selection*:

        Wähle zufällig einige Chromosomen aus und wähle das beste(/die besten) Chromosom(en) aus dieser Gruppe:

        .. include:: code/lib/genetic_algorithm.py
            :code: python
            :number-lines:
            :start-after: # Choose num_participants at random and take the best 2
            :end-before: # type: ignore

    :*Roulette Wheel Selection*:

        Jedes Chromosom wird mit einer Wahrscheinlichkeit ausgewählt, die proportional zu seiner Fitness ist (die Fitness wird in eine Wahrscheinlichkeit umgerechnet).

        .. include:: code/lib/genetic_algorithm.py
            :code: python
            :number-lines:
            :start-after: # Note: will not work with negative fitness results
            :end-before: # Choose num_participants at random and take the best 2

    .. container:: incremental

        .. rubric:: Vorgehen

        1. Für jedes gewählte Chromosomenpaar bestimme, ob diese rekombiniert werden sollen. Wenn ja, führe die Rekombination durch. Wenn nicht, kopiere die Chromosomen einfach in die neue Generation.

        2. Wiederhole die Selektion und ggf. Rekombination, bis die gewünschte Anzahl von Chromosomen ausgewählt wurde.



Basisklasse des genetischen Algorithmus
--------------------------------------------------------

.. include:: code/lib/genetic_algorithm.py
    :code: python
    :number-lines:
    :start-after: from lib.chromosome import Chromosome
    :end-before:  -> None:



Hauptmethode des genetischen Algorithmus
--------------------------------------------------------

.. include:: code/lib/genetic_algorithm.py
    :code: python
    :number-lines:
    :start-after: # and return the best individual found

.. supplemental::

    :java:`fitness_key` ist eine Referenz auf die Methode, die die Fitness eines Chromosoms berechnet.



Durchführung der Mutationen
--------------------------------------------------------

.. include:: code/lib/genetic_algorithm.py
    :code: python
    :number-lines: 94
    :start-after: # With _mutation_chance probability mutate each individual
    :end-before: # Run the genetic algorithm for max_generations iterations



Durchführung der Selektion und Vererbung
--------------------------------------------------------

.. deck::

    .. card::

        .. include:: code/lib/genetic_algorithm.py
            :code: python
            :number-lines: 69
            :start-after: # Replace the population with a new generation of individuals
            :end-before: if self._selection_type == \

        .. include:: code/lib/genetic_algorithm.py
            :code: python
            :number-lines: 82
            :start-after: self._pick_tournament(len(self._population) // 2)
            :end-before: # With _mutation_chance probability mutate each individual

    .. card::

        .. include:: code/lib/genetic_algorithm.py
            :code: python
            :number-lines: 73
            :start-after: while len(new_population) < len(self._population):
            :end-before: # Kreuze ggf. die Eltern



Herausforderungen bei der Entwicklung genetischer Algorithmen
---------------------------------------------------------------

.. class:: incremental-list dhbw

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

.. deck::

    .. card::

        .. rubric:: Chromosomen ermöglichen Zuordnung von Buchstaben zu Ziffern

        **Idee**: Verwendung von Listenindizes zur Repräsentation der Ziffern: [0,...,9]. Die Werte in der Liste entsprechen den Buchstaben; Beispiel:

        *Zuordnung von Ziffern zu Buchstaben:*

        .. csv-table::
            :header: "Index: ", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, " "
            :widths: 25, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10
            :width: 100%
            :align: center

            "letters = \[", "O", "M", "Y", "␣", "␣", "E", "N", "D", "R", "S", "]"

        .. container:: minor smaller line-above

            *Für die Lösung würde sich ergeben:*

            .. csv-table::
                :header: S, E, N, D, M, O, R, Y, "␣ ", "␣"
                :widths: 10, 10, 10, 10, 10, 10, 10, 10, 10, 10
                :class: no-table-borders

                9, 5, 6, 7, 1, 0, 8, 2, 3 ,4

        .. container:: incremental

            Somit kann durch die Verschiebung der Buchstaben in der Liste der assoziierte Wert geändert werden.

    .. card:: 

        .. rubric:: Klasse für Chromosomen

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :start-after: # class SendMoreMoney(Chromosome)
            :end-before: # The fitness function


    .. card:: 

        .. rubric:: Erzeugen von Individuen bzw. Chromosomen

        **Idee**: beliebige Verwürfelung der Buchstaben in der Liste.

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :start-after: # random_instance()
            :end-before: # crossover()


    .. card::

        .. rubric:: Mutation eines Chromosoms

        **Idee**: zufälliger paarweiser Austausch von zwei Buchstaben in der Liste.

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :start-after: # mutate()
            :end-before: # __str__()

    .. card::

        .. rubric:: Vererbung

        **Idee**: pro Nachkommen sicherstellen, dass einige Buchstaben Indizes (d. h. Wertzuordnungen) von einem Elternteil und einige vom anderen Elternteil stammen.

        .. container:: 

            **Beispiel** (verkürzt):

            ::

                Elternteil 1 = [a,b,c,d,e]  # d.h. ein Chromosom
                Elternteil 2 = [c,a,b,e,d]  # d.h. ein Chromosom

            gewählte Indizes seien: 0 und 2

            .. container::

                ::

                    Nachkomme 1 = [a,c,b,d,e]  # im Wesentlichen Elternteil 1,
                                                aber Position von "b" vom 2ten übernommen
                                                (dadurch Anpassung der Pos. von "c" notw.)
                    Nachkomme 2 = [a,c,b,e,d]  # im Wesentlichen Elternteil 2,
                                                aber Position von "a" vom 1ten übernommen
                                                (dadurch Anpassung der Pos. von "c" notw.)

    .. card::

        .. rubric:: Vererbung - Implementierung

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :start-after: # crossover()
            :end-before: # mutate()

    .. card::

        .. rubric:: Fitness-Funktion

        **Idee**: Eine Lösung ist besser, je näher die Summe der Ziffern von SEND und MORE an MONEY ist. D. h.:

        .. container:: incremental

            .. math::

                \text{minimiere}(|money - (send + more)|)


        .. container:: incremental

            **Feststellung**: Das Ziel des generischen genetischen Algorithmus ist es, die Fitness-Funktion zu maximieren. Wir müssen somit unser Minimierungsproblem in ein Maximierungsproblem umwandeln:

            .. math::

                \text{maximiere}\left(\frac{1} { |money - (send + more)| + 1 }\right)\\

        .. container:: incremental 

            Beispiel: Sei die Differenz :math:`1`, dann ist die Fitness :math:`1/2`; bei einer Differenz von :math:`0` ist die Fitness :math:`1` und somit maximal.

    .. card::

        .. rubric:: Fitness-Funktion - Implementierung

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :start-after: # fitness()
            :end-before: @classmethod

    .. card::

        .. rubric:: Initialisierung

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :start-after: if __name__ == "__main__":

    .. card::

        .. rubric:: Exemplarische Ausführungen zeigt stochastische Natur

        .. container:: 

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



.. class:: exercises transition-move-to-top

Übung
--------------------------------------------------------

.. exercise:: Gruppenzuteilung

    Entwickeln Sie einen genetischen Algorithmus, der eine sehr gute Aufteilung von Personen (Studierenden) auf eine feste Anzahl an Gruppen findet, basierend auf den Präferenzen der Personen.

    Im Template ist eine initiale Aufgabenstellung hinterlegt, die es zu lösen gilt: Verteilung von 16 Studierenden auf 4 Gruppen inkl. Bewertungsmatrix :peripheral:`(jeder Studierende hat jeden anderen mit Werten von 1 bis 10 bewertet)`.

    .. container::

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

.. supplemental::

    Denken Sie daran, dass Sie die Parameter bzgl. :java:`mutation_chance`, :java:`crossover_chance`, :java:`selection_type`, :java:`initial_population` und :java:`max_generations` anpassen können.

    Sie können für die Darstellung der Mitglieder einer Gruppe auch eine andere Darstellung als eine Liste von Listen verwenden. Sie müssen dann nur ggf. auch die :java:`__str__` Methode anpassen. Es steht Ihnen natürlich auch frei Konzepte wie Memoization zu verwenden, um die Fitness-Funktion zu beschleunigen.

    Sieger ist, wer in einem akzeptablen Zeitrahmen (3 Minuten) die beste Lösung findet.



.. class:: exercises transition-move-to-top

Übung
--------------------------------------------------------

.. exercise:: Ausgeglichene Gruppenzuteilung

    Passen Sie Ihren genetischen Algorithmus aus der vorherigen Übung so an, dass die Gruppen alle in etwa die gleiche Glücklichkeit aufweisen. D. h. eine Zuteilung, bei der die  Gruppenglücklichkeiten z. B. 92 + 91 + 73 + 89 = 345 sind, die aber größere Unterschiede zwischen den Gruppen hat, sollte vermieden werden. Eine Verteilung z. B. mit den Gruppenglücklichkeiten: 80, 84, 80, 80 = 324 sollte bevorzugt werden.

    .. hint::

        Sie können den bisher errechneten Wert zum Beispiel dadurch anpassen, dass Sie von dem Wert die Summe der absoluten Abweichungen vom Durchschnitt abziehen. Ggf. ist es erforderlich den Wert auch noch zu skalieren, damit steigende Abweichungen stärker ins Gewicht fallen.

        Bedenken sie, dass sie ggf. den Threshold anpassen müssen.

    .. solution::
        :pwd: ist_das_glücklicher?

        *Ein Lösungsvorschlag*

        .. rubric:: Angepasste Fitness-Funktion

        .. include:: code/group_assignment_balanced.py
            :code: python
            :number-lines:
            :start-after: # compute_fitness()
            :end-before: def fitness(self)

        .. rubric:: Angepasste Main Methode (insbesondere der Threshold)

        .. include:: code/group_assignment_balanced.py
            :code: python
            :number-lines:
            :start-after: if __name__ == "__main__":
