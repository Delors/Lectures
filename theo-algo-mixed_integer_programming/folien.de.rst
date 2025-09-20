.. meta::
    :lang: de
    :author: Michael Eichberg
    :keywords: "mixed-integer programming", "python"
    :description lang=de: Lineare und Mixed-Integer-Programmierung
    :id: lecture-theo-mixed_integer_programming
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs

.. |group_assignment_template.py| source:: code/group_assignment_template.py
            :path: relative
            :prefix: https://delors.github.io/

.. |send_more_money.py| source:: code/send_more_money.py
    :path: relative
    :prefix: https://delors.github.io/



Lineare und Mixed-Integer Programmierung\ [#]_
======================================================

.. rubric:: Eine allererste Einführung

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0.5

.. [#] Introduction to Algorithms, 3rd Edition, Cormen, Leiserson, Rivest, Stein, MIT Press, 2009

.. supplemental::

    :Folien:

        |html-source|

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-move-to-top

Einführung in lineare Programmierung
--------------------------------------------------------



Beispielszenario: Kostenoptimierung
------------------------------------

.. rubric:: Optimierung der Kosten für die Nahrungsmittelzusammensetzung

.. container::

    Seien :math:`x_1` und :math:`x_2` die Menge an Nahrungsmitteln 1 und 2, die wir kaufen. :incremental:`Die Kosten für Nahrungsmittel 1 und 2 betragen 1 und 2 Euro pro Einheit.` :incremental:`Die täglichen Ernährungsbedürfnisse sind 10 Einheiten Protein und 20 Einheiten Fett.` :incremental:`Nahrungsmittel 1 enthält 2 Einheiten Protein und 3 Einheiten Fett pro Einheit. Nahrungsmittel 2 enthält 1 Einheit Protein und 4 Einheiten Fett pro Einheit.`

    .. container:: incremental

        .. rubric:: Zielfunktion (:eng:`objective function` oder einfach nur :eng:`objective`)

        .. math::

            \text{minimiere }x_1 \cdot 1\text{€} + x_2 \cdot 2\text{€}

        .. rubric:: (unter den) Nebenbedingungen (:eng:`constraints`/:eng:`subject to (s.t.)`)

        .. math::

            \begin{array}{rcll}
            2 \cdot x_1 + 1 \cdot x_2 & \geq & 10 & \text{Nebenbedingung bzgl. Protein}\\
            3 \cdot x_1 + 4 \cdot x_2 & \geq & 20 & \text{Nebenbedingung bzgl. Fett}\\
            x_1, x_2 & \geq & 0 &\\
            \end{array}



Lineare Programmierung
------------------------

.. definition::

    Lineare Programmierung: Optimierung von linearen Funktionen unter linearen Nebenbedingungen.

.. container::

    Das Ziel ist die Optimierung (Maximierung/Minimierung) einer linearen Funktion :math:`f`:

    .. math::

        f(x_1,\ldots,x_n) = a_1 \cdot x_1 + a_2 \cdot x_2 + \ldots + a_n \cdot x_n = \sum_{i=1}^{n} a_i \cdot x_i

    Unter einer Menge von linearen Nebenbedingungen. Sei :math:`b \in \mathbb{R}`, dann ist ...

    - eine *lineare Ungleichung* der Form: :math:`f(x_1,\ldots,x_n) \leq b`
    - eine *lineare Gleichung* der Form: :math:`f(x_1,\ldots,x_n) = b`
    - lineare Ungleichungen und Gleichungen beschreiben die *linearen Nebenbedingungen*.



Lösen von linearen Optimierungsproblemen
------------------------------------------

.. deck::

    .. card::

        .. grid::

            .. cell::

                .. rubric:: Standardform - „nur“ Verwendung von linearen Ungleichungen

                **Zielfunktion (Maximiere)**

                .. math::

                        x_1 + x_2

                **Nebenbedingungen**

                .. math::

                        \begin{array}{rrcr}
                        4 x_1 & - &   x_2 & \leq & 8 \\
                        2 x_1 & + &   x_2 & \leq & 10 \\
                        5 x_1 & - & 2 x_2 & \geq  & -2 \\
                              &   &   x_1 & \geq & 0 \\
                              &   &   x_2 & \geq & 0
                        \end{array}

            .. cell:: incremental

                .. deck::

                    .. card::

                        .. image:: images/lp-constraints.svg
                            :align: center
                            :height: 925px

                    .. card:: overlay

                        .. image:: images/lp-solution.svg
                            :align: center
                            :height: 925px

    .. card::

        .. rubric:: Schlupfform (:eng:`Slack Form`) - „nur“ Verwendung von linearen Gleichungen

        **Zielfunktion (Maximiere)**

        .. math::

                x_1 + x_2

        .. container:: incremental

            **unter den Nebenbedingungen**

            .. math::
                :class: text-align-left left align-left

                    \begin{array}{rcrcrcr}
                    x_3 & = &  8 & - & 4x_1 & + & x_2  \\
                    x_4 & = & 10 & - & 2x_1 & - & x_2  \\
                    x_5 & = &  2 & + & 5x_1 & - & 2x_2 \\
                    0 & \leq & x_1, & x_2, & x_3, & x_4 , & x_5 \\
                    \end{array}

        .. container:: incremental

            Die Variablen :math:`x_3`, :math:`x_4` und :math:`x_5` sind die Schlupfvariablen. Sie messen die Differenz zwischen der linken und der rechten Seite der Ungleichungen und sind nicht Teil der Zielfunktion.



.. supplemental::

    **Beobachtungen (am Beispiel orientiert)**

    - der Bereich der zulässigen Lösungen enthält (im Allgemeinen) unendlich viele Punkte
    - der Bereich der zulässigen Lösungen ist beschränkt/ist (hier) ein konvexes Polygon (im Allgemeinen ein konvexes Polyeder)
    - Die konvexe Hülle einer endlichen Anzahl von affin unabhängigen Punkten in einem n-dimensionalen Raum bezeichnen wir als Simplex
    - in diesem (2-Dimensionalen) Fall können wir die Lösung grafisch darstellen
    - nicht jedes lineare Optimierungsproblem hat eine (bzw. eine optimale) Lösung
    - Auch in der Schlupfform, werden die Anforderungen an die nicht-Negativität der Variablen als Ungleichungen beschrieben.

    .. container:: peripheral

        :Affine Unabhängigkeit:

            Zwei Punkte sind affin unabhängig, wenn die Differenz der beiden Punkte nicht durch einen Skalarfaktor auf den anderen Punkt abgebildet werden kann. (Im 2-D Raum: Die beiden Punkte liegen nicht auf einer Geraden, wenn die beiden Punkte als entsprechende Vektoren aufgefasst werden.)

    Die Schlupfform ist für den Simplex-Algorithmus relevant.



.. class:: exercises transition-scale

Übung\ [#]_
--------------

.. exercise:: Formulierung eines linearen Programms

    In einem Betrieb mit mehrschichtiger Arbeitszeit besteht folgender Mindestbedarf an Personal:

    ::

        von 0 bis 4 Uhr: 3 Personen
        von 4 bis 8 Uhr: 8 Personen
        von 8 bis 12 Uhr: 10 Personen
        von 12 bis 16 Uhr: 8 Personen
        von 16 bis 20 Uhr: 14 Personen
        von 20 bis 24 Uhr: 5 Personen

    Der Arbeitsbeginn ist jeweils um 0, 4, 8, 12, 16 bzw. 20 Uhr. Die Arbeitszeit beträgt stets 8 Stunden hintereinander. Formulieren Sie ein lineares Program, um einen Einsatzplan mit minimalem Gesamtpersonalbedarf aufzustellen.

    .. solution::
        :pwd: alt_aber_GUT

        **Lösung**

        Assoziiere jeden Zeitslot mit einer Variable :math:`x_i`, die die Anzahl der arbeitenden Personen beschreibt (:math:`x_1` ist die Anzahl der um 0 Uhr arbeitenden, :math:`x_1` ist die Anzahl der um 4 Uhr arbeitenden etc.) und formuliere die Nebenbedingungen:

        .. math::

            \begin{array}{rrcl}
            \text{minimiere} & x_1 &  + & x_2 & + & x_3 & + & x_4 & +&  x_5 & + & x_6 \\
            \text{unter den Nebenbedingungen} & x_1 & + & x_6 & \geq & 3 \\
            & x_1 & + & x_2 & \geq & 8 \\
            & x_2 & + & x_3 &  \geq & 10 \\
            & x_3 & + & x_4 &  \geq & 8 \\
            & x_4 & + & x_5 & \geq & 14 \\
            & x_5 & + & x_6 &  \geq & 5 \\
            & x_1, & x_2, & x_3, & x_4, & x_5, & x_6 & \geq & 0 & & & \text
            {und ganzzahlig}
            \end{array}

.. [#] Aus: Übungsbuch Operations Research; Domschke, Drexl, Schildt, Scholl, Voß; Springer Verlag 1997




.. class:: exercises transition-scale

Übung
------

.. exercise:: Berechnung des maximalen Fluss (Maximum-Flow-Problem)

    Formulieren Sie ein lineares Programm zur Bestimmung des maximalen Flusses von einer Quelle :math:`s` zu einer Senke :math:`t` in einem Netzwerk mit :math:`V` Knoten. (Die Funktion) :math:`f_{uv}` sei der Fluss zwischen zwei Knoten :math:`u` und :math:`v`. Nebenbedingungen:

    .. container:: slightly-more-smaller

        :Kapazitätsbeschränkung: Der Fluss :math:`f_{uv}` auf einer Kante darf die Kapazität (:math:`c(u,v)`) der Kante nicht überschreiten.
        :Flusserhaltung: Für jeden Knoten (außer Quelle und Senke) gilt, dass der zufließende Fluss gleich dem abfließenden Fluss ist.
        :Richtungsabhängigkeit: Der Fluss ist gerichtet (von einem Knoten zum anderen).

        .. container:: smaller incremental

            Sie können die vereinfachende Annahme machen, dass die Summe der Zuflüsse  zur Quelle :math:`0`  ist (:math:`\sum_{v\in V} f_{vs} = 0`); dass die Quelle keine eingehenden Kanten hat. Weiterhin sei die Kapazität zwischen zwei nicht-verbundenen Knoten :math:`0`.

    .. solution::
        :pwd: G(V,E)

        **Lösung**

        .. math::

            \begin{array}{rrcl}
            \text{maximiere} & \sum_{v \in V} f_{sv} \\
            \text{unter den Nebenbedingungen} & f_{uv} & \leq & c(u,v) & \text{für } u,v \in V \\
            & \sum_{v \in V} f_{uv} & = & \sum_{v \in V} f_{vu} & \text{für } u \in V \setminus \{s,t\} \\
            & f_{uv} & \geq & 0 & \text{für } u,v \in V
            \end{array}

.. supplemental::

    **Beispiel**

    Netzwerk mit Kapazitäten:

    .. image:: images/max-flow/capacities.svg
        :align: center
        :width: 800px

    Eine (nicht notwendigerweise optimale) Lösung, die die Nebenbedingungen erfüllt:

    .. image:: images/max-flow/flows.svg
        :align: center
        :width: 800px

    **Im  Allgemeinen gilt**

    Das Netzwerk ist modelliert als gerichteter Graph :math:`G = (V,E`) ohne Eigenschleifen und ohne antiparallele Kanten (d. h. :math:`(v,u) \in E \Rightarrow (u,v) \notin E`). Jeder Kante :math:`(u,v) \in E` ist eine nicht-negative Kapazität :math:`c(u,v) \geq 0` zugeordnet.

    Sei :math:`(u,v) \notin E`, dann ist :math:`c(u,v) = 0`.


    **Empfohlene Vorgehensweise**

    1. Bestimmen Sie die Zielfunktion in Hinblick auf den Fluss bzw. der Variablen, die den Fluss repräsentieren.
    2. Formulieren Sie die Nebenbedingungen:

       1. in Hinblick darauf, dass der Fluss über eine Kante nie negativ sein darf
       2. in Bezug auf die Kanten und die Kapazitäten
       3. in Bezug auf die Kapazitätserhaltung



.. class:: new-section transition-fade

Simplex Algorithmus
---------------------------------


Simplex Algorithmus - Einführung
---------------------------------

.. class:: incremental-list list-with-explanations

- Der Simplex-Algorithmus ist ein Algorithmus zur Lösung von linearen Optimierungsproblemen.
- Der Algorithmus wurde von George Dantzig entwickelt und 1947 veröffentlicht.
- Der Simplex-Algorithmus ist ein iteratives Verfahren, das in der Regel sehr effizient ist

  Im Regelfall polynomielle Laufzeit, im Worst-case jedoch exponentiell.
- Der Simplex-Algorithmus ist ein Beispiel für einen Algorithmus, der auf einem Netzwerk von Kanten operiert.

  Der Simplex-Algorithmus bewegt sich systematisch entlang der Ecken (:eng:`Vertices`) des Bereichs, der die zulässigen Lösungen des linearen Programms beschreibt, um die optimale Lösung zu finden. Er terminiert, wenn er das lokale Optimum erreicht hat. :incremental:`Aufgrund der konvexen Natur des Problems ist das lokale Optimum gleichzeitig das globale Optimum.`

.. supplemental::

    Es gibt Algorithmen, die eine garantierte polynomielle Laufzeit haben, wie zum Beispiel der Ellipsoid-Algorithmus. Der Simplex-Algorithmus ist jedoch in der Praxis oft schneller.



Standardform
------------------------

.. story::

    Gegeben sein :math:`n` reelle Zahlen :math:`(c_1,...,c_n`); :math:`m` reelle Zahlen (:math:`b_1,...,b_m`); und eine :math:`m \times n` Matrix :math:`A = (a_{ij})` für :math:`i = 1,2,...m` und :math:`j = 1,2,...n`.

    Wir möchten nun :math:`n` reelle Zahlen :math:`(x_1,...,x_n)` finden, die die folgenden Bedingungen erfüllen:

    **Zielfunktion** (:eng:`objective function`)

    .. math::

        \text{maximiere} \sum_{j=1}^{n} c_j \cdot x_j


    **(unter den) Nebenbedingungen** (:eng:`subject to/constraints`)

    .. math::

        \begin{array}{rcll}
        \sum_{j=1}^{n} a_{ij} \cdot x_j & \leq & b_i & \text{für } i = 1,2,...,m \\
        x_j & \geq & 0 & \text{für } j = 1,2,...,n
        \end{array}

    .. container:: incremental

        .. rubric:: Kompakte Darstellung

        Gegeben Matrix :math:`A = (a_{ij})`, :math:`m`\ -Vektor :math:`b = (b_i)`, :math:`n`\ -Vektor :math:`c = (c_j)`, und :math:`n`\ -Vektor :math:`x = (x_j)`. Dann ist das lineare Programm:

        .. math::

            \begin{array}{rcl}
            \text{maximiere} & c^T x & \\
            \text{unter den Nebenbedingungen} & A \cdot x & \leq & b \\
            & x & \geq & 0
            \end{array}

    .. container:: incremental

        .. rubric:: Terminologie

        :zulässige Lösung/`feasible`:eng:: Eine Belegung der Variablen :math:`\bar{x}`, die die Nebenbedingungen erfüllt.
        :optimale Lösung: Eine zulässige Lösung, die die Zielfunktion maximiert.
        :unbeschränkt: Ein lineares Programm, dass Lösungen hat, die die Zielfunktion nicht beschränken.
        :unzulässig/`infeasible`:eng:: Ein lineares Programm, dass keine zulässige Lösung hat.

    .. container:: incremental

        .. rubric::  Konvertierung von beliebigen linearen Programmen in die Standardform

        Ein lineares Programm kann aus folgenden vier Gründen nicht in  Standardform sein:

        - Die Zielfunktion ist zu minimieren
        - Es gibt Variablen ohne Nichtnegativitätsbedingung
        - Es gibt Gleichungen (:math:`=`)
        - Es gibt Ungleichungen mit :math:`\geq` statt :math:`\leq`

    .. container:: incremental

        *Regeln*

        .. class:: list-with-explanations

        1. Minimierungsprobleme können durch Multiplikation der Zielfunktion mit :math:`-1` in ein Maximierungsproblem umgewandelt werden.
        2. Variablen ohne Nichtnegativitätsbedingung können durch die Einführung von Differenzvariablen in Nichtnegativitätsbedingungen umgewandelt werden.

           D. h. wir ersetzen die Vorkommen der Variablen :math:`c\cdot x` durch :math:`c\cdot x^+ - c\cdot x^-` wobei :math:`x^+` und :math:`x^-` nicht-negativ sind.
        3. Gleichungen können in zwei Ungleichungen umgewandelt werden.
        4. Ungleichungen mit :math:`\geq` können durch Multiplikation mit :math:`-1` in Ungleichungen mit :math:`\leq` umgewandelt werden.

.. supplemental::

    - :math:`c^Tx` ist das innere Produkt.
    - :math:`x \geq 0` bedeutet, dass jede Komponente von :math:`x` nicht negativ sein darf.



.. class:: exercises transition-move-to-top

Übung
-------

.. exercise:: Überführen Sie das lineare Programm in Standardform.

    .. math::

        \begin{array}{rrcrcl}
        \text{minimiere} & -2x_1 & +& 3x_2 & \\
        \text{unter den Nebenbedingungen} & x_1 & + & x_2 & = & 7 \\
        & x_1 &-& 2x_2 & \leq & 4 \\
        & x_1 & & & \geq & 0
        \end{array}

    .. solution::
        :pwd: QuickFix

        :math:`x_2` wurde durch :math:`x_2` und :math:`x_3` nach Regel 2 ersetzt.

        .. math::

                \begin{array}{rrcrcrcr}
                \text{maximiere} & 2x_1 & - & 3x_2 & + & 3x_3 & \\
                \text{unter den Nebenbedingungen} \\
                & x_1 & + & x_2 & - & x_3 & \leq & 7 \\
                & -x_1 & - & x_2 & + & x_3 &  \leq & -7 \\
                & x_1 &-& 2x_2 & + & 2x_3 & \leq & 4 \\
                & x_1 & & & & & \geq & 0 \\
                & x_2 & & & & & \geq & 0 \\
                & x_3 & & & & & \geq & 0
                \end{array}


.. exercise:: Zeigen Sie, dass das folgende lineare Programm unzulässig ist.

    .. math::

        \begin{array}{rrcrcr}
        \text{maximiere}                    & 3x_1  & - & 2x_2 & \\
        \text{unter den Nebenbedingungen}   & x_1   & + & x_2  & \leq & 2 \\
                                            & -2x_1 & - & 2x_2 & \leq & -10 \\
                                            &       &   & x_1  & \geq & 0 \\
                                            &       &   & x_2  & \geq & 0
        \end{array}

    .. solution::
        :pwd: WiDeRspruch

        Nach Umformulierung der 2. Nebenbedingung (geteilt durch -2), erhalten wir die Nebenbedingungen:

        .. math::

            \begin{array}{rrcrcl}
            & x_1 & + & x_2 & \leq & 2 \\
            & x_1 & + & x_2 & \geq & 5 \\
            \end{array}

        Und somit unmittelbar einen Widerspruch (Alternativ: Ungleichungen in einander einsetzen führt zum Widerspruch).



Schlupfform (:eng:`Slack Form`)
----------------------------------

.. story::

    - Zum effizienten Lösung von linearen Programmen wird die Schlupfform verwendet.
    - Bei der Schlupfform werden alle Nebenbedingungen in Gleichungen umgewandelt - abgesehen von den Nichtnegativitätsbedingungen.

      .. container:: accentuate

        .. rubric:: Vorgehen

        Gegeben sei *eine* Ungleichung:

        .. container:: math

            \\[
            \\begin{align}
            \\textstyle\\sum_{j=1}^{n} a_{ij} \\cdot x_j \\leq b_i \\tag{1}
            \\end{align}
            \\]

        Wir führen dann eine Schlupfvariable (:eng:`slack variable`) :math:`x_{n+i}` ein und erhalten:

        .. container:: math

            \\[
            \\begin{equation}
            \\textstyle x_{n+1} = b_i - \\sum_{j=1}^{n} a_{ij} \\cdot x_j  \\tag{2}
            \\end{equation}
            \\]

            \\[
            \\begin{equation}
            x_{n+1} \\geq 0 \\tag{3}
            \\end{equation}
            \\]

    .. class:: incremental-list

    - Somit stehen die Variablen :math:`x_1,...,x_n` für die ursprünglichen Variablen und die Variablen :math:`x_{n+1},...,x_{n+m}` für die Schlupfvariablen.
    - Auf der rechten Seite der Gleichung :math:`(2)` stehen die ursprünglichen Variablen. Nur diese Variablen sind (initial) Teil der Zielfunktion.

      :Basisvariablen: Die Variablen auf der linken Seite der Gleichung :math:`(2)`.
      :Nichtbasisvariablen: Die Variablen auf der rechten Seite der Gleichung :math:`(2)`.
    - Für den Wert der Zielfunktion verwenden wir die Variable :math:`z`.
    - Im Folgenden gilt:

      :`N`:math:: die Menge der Indizes der Nichtbasisvariablen
      :`B`:math:: die Menge der Indizes der Basisvariablen
      :`v`:math:: ein optionaler konstanter Term in der Zielfunktion

      :math:`|N| = n`, :math:`|B| = m` und :math:`N \cup B = {1, ..., n + m }`

      Somit ist die kompakte Darstellung des linearen Programms in Schlupfform:

      .. math::

        \begin{array}{rcrcl}
            z     & = &   v & + & \sum_{j \in N} c_j \cdot x_j \\
            x_{i} & = & b_i & - & \sum_{j \in N} a_{ij} \cdot x_j \quad \text{für } i \in B\\
        \end{array}

.. supplemental::

    Diese Schlupfvariable (:math:`x_{n+1}`) misst die Differenz zwischen der linken und der rechten Seite der Ungleichung (1).



Schlupfform - Beispiel
------------------------

.. container::

    .. container::

        .. rubric::  Gegebenes lineares Programm

        .. math::

            \begin{array}{rrcrcl}
            \text{maximiere}         & 3x_1 & + &  x_2 & + & 2x_3 \\
            \text{unter den Nebenbedingungen}        &  x_1 & + &  x_2 & + & 3x_3 & \leq & 30 \\
                                & 2x_1 & + & 2x_2 & + & 5x_3 & \leq & 24 \\
                                & 4x_1 & + &  x_2 & + & 2x_3 & \leq & 36 \\
                                &  x_1 & , &  x_2 & , &  x_3 & \geq & 0
            \end{array}

    .. container:: incremental

        .. rubric:: Gegebenes lineares Programm in Schlupfform

        Wir führen die Schlupfvariablen :math:`x_4`, :math:`x_5` und :math:`x_6` ein mit der Nebenbedingung: :math:`x_4, x_5, x_6 \geq 0`.

        .. math::

                \begin{array}{rrcrcl}
                \text{maximiere}     &  z & = &    & & 3x_1 & + &  x_2 & + & 2x_3 \\
                \text{unter den Nebenbedingungen}    &x_4 & = & 30 & - &  x_1 & - &  x_2 & - & 3x_3 \\
                                &x_5 & = & 24 & - & 2x_1 & - & 2x_2 & - & 5x_3 \\
                                &x_6 & = & 36 & - & 4x_1 & - &  x_2 & - & 2x_3 \\
                \end{array}



.. class:: exercises transition-move-to-top

Übung
-------

.. exercise:: Überführen eines linearen Programms in Schlupfform

    Überführen Sie das 1. lineare Programm aus der vorhergehenden Übung in Schlupfform.

    .. container:: peripheral

        Bauen Sie ggf. auf den Ergebnissen der vorhergehenden Aufgabe auf.

    .. solution::
        :pwd: Even_Quicker_FIX

        .. math::

                \begin{array}{rrcrcrcrcr}
                \text{maximiere} & 2x_1 & - & 3x_2 & + & 3x_3 & \\
                \text{unter den Nebenbedingungen} \\
                & x_4  & = &  7 & - & x_1 & - &  x_2 & + &  x_3 \\
                & x_5  & = & -7 & + & x_1 & + &  x_2 & - &  x_3 \\
                & x_6  & = &  4 & - & x_1 & + & 2x_2 & - & 2x_3 \\
                & x_1  & \geq & 0 \\
                & x_2  & \geq & 0 \\
                & x_3  & \geq & 0 \\
                & x_4  & \geq & 0 \\
                & x_5  & \geq & 0 \\
                & x_6  & \geq & 0 \\
                \end{array}

.. exercise:: Zeigen Sie (grafisch), das das folgende lineare Programm unbeschränkt ist.

    .. math::

        \begin{array}{rrcrcl}
        \text{maximiere}                    & x_1 & - & x_2 & \\
        \text{unter den Nebenbedingungen}   & -2x_1 & +   &  x_2 & \leq & -1 \\
                                            &  -x_1 & -   & 2x_2 & \leq & -2 \\
                                            &  x_1, & x_2 &      & \geq & 0
        \end{array}

    .. solution::
        :pwd: Grenzen waren gestern

        Durch

        .. image:: images/lp-exercise-solution-unbounded.svg
            :align: center
            :width: 500px





(Primaler) Simplex
------------------------

.. story::

    .. admonition:: Grundlegende Idee

        Wir lösen unser Optimierungsproblem durch gezielte algebraische Operationen, die die Zielfunktion maximieren.

    .. class:: incremental-list list-with-explanations

    1. Wir wählen immer eine Variable, die in der Zielfunktion vorkommt und einen positiven Koeffizienten hat.

       (D. h. wir wählen eine Variable deren Erhöhung die Zielfunktion maximiert.)
    2. Dann bestimmen wir die Ungleichung, die die Maximierung der gewählten Variable am stärksten einschränkt.
    3. Wir „tauschen“ die Variable mit der Schlupfvariablen, die in dieser Ungleichung vorkommt und lösen die Gleichung nach der gewählten Variable auf.
    4. Wir setzen dann die umgestellte Gleichung in alle anderen Gleichungen (inkl. Zielfunktion) ein, um die Werte der anderen Variablen zu bestimmen.

    .. container:: incremental

        Wir nennen diesen Prozess (1-4) „Pivot Operation“.

    .. container:: incremental

        Wir wiederholen diesen Prozess, bis wir keine Variable mehr finden, die die Zielfunktion maximiert. An dieser Stelle können wir dann das Optimum und die Werte für die Variablen (:math:`x_i,...,x_{n+m}`) ablesen.

.. supplemental::

    Es ist in Hinblick auf die Korrektheit gleichgültig welche Variable wir im ersten Schritt wählen. Es kommt jedoch ggf. zu einer unterschiedlichen Anzahl an Schritten, bis wir die optimale Lösung finden.



Simplex anwenden
------------------------

.. story::

    .. rubric:: Gegebenes lineares Programm in Schlupfform

    Wir führen die Schlupfvariablen :math:`x_4`, :math:`x_5` und :math:`x_6` ein mit der Nebenbedingung: :math:`x_4, x_5, x_6 \geq 0`.

    .. math::

            \begin{array}{rrcrcr}
            \text{maximiere}     &  z & = &    & & 3x_1 & + &  x_2 & + & 2x_3 \\
            \text{unter den Nebenbedingungen}    &x_4 & = & 30 & - &  x_1 & - &  x_2 & - & 3x_3 \\
                            &x_5 & = & 24 & - & 2x_1 & - & 2x_2 & - & 5x_3 \\
                            &x_6 & = & 36 & - & 4x_1 & - &  x_2 & - & 2x_3 \\
            \end{array}

    .. class:: incremental-list

    - Wir können die Zielfunktion maximieren, indem wir die Variable der Zielfunktion mit dem größten positiven Koeffizienten wählen: :math:`x_1`.
    - Wir prüfen welche Nebenbed. die Maximierung von :math:`x_1` am stärksten einschränkt:

      1. Nebenbed.: :math:`x_1\leq 30`, 2. Nebenbed.: :math:`x_1\leq 12` und 3. Nebenbed.: :math:`x_1\leq 9`
    - Die (nicht-Basis)Variable :math:`x_1` wird somit durch die Schlupfvariable/Basisvariable :math:`x_6` ersetzt:

      .. math::

        4x_1 = 36 - x_6 - x_2 - 2x_3  \Rightarrow x_1 = 9 - \frac{1}{4}x_6 - \frac{1}{4}x_2 - \frac{1}{2}x_3

    - Wir setzen :math:`x_1` in die Zielfunktion und die anderen Nebenbedingungen ein und erhalten:

      :math:`x_4 = 30 - (9 - \frac{1}{4}x_6 - \frac{1}{4}x_2 - \frac{1}{2}x_3) - x_2 - 3x_3`

      :math:`x_4 = 21 + \frac{1}{4}x_6 - \frac{3}{4}x_2 - \frac{5}{2}x_3`

    - Ergebnis

      .. math::

            \begin{array}{rrrcrcrcr}
            z & = & 27 & - & \frac{3}{4}x_6 & + & \frac{1}{4}x_2 & + & \frac{1}{2}x_3 \\
            x_1 & = & 9 & - & \frac{1}{4}x_6 & - & \frac{1}{4}x_2 & - & \frac{1}{2}x_3 \\
            x_4 & = & 21 & + & \frac{1}{4}x_6 & - & \frac{3}{4}x_2 & - & \frac{5}{2}x_3 \\
            x_5 & = & 6 & + & \frac{1}{2}x_6 & - & \frac{3}{2}x_2 & - & 4x_3 \\
            \end{array}

      :incremental:`Diese Operation wird als Pivot Operation bezeichnet.`
    - Im nächsten Schritt könnten wir :math:`x_3` wählen, da es den größten positiven Koeffizienten hat. Da die dritte Nebenbedingung die Maximierung von :math:`x_3` am stärksten einschränkt, würden wir :math:`x_3` durch die Schlupfvariable :math:`x_5` ersetzen.

    - Ergebnis

      .. math::

            \begin{array}{rrrcrcrcr}
            z   & = & \frac{111}{4} & + & \frac{1}{16}x_2 & - & \frac{1}{8}x_5 & - & \frac{11}{16}x_6 \\
            x_1 & = & \frac{33}{4} & - & \frac{1}{16}x_2 & + & \frac{1}{8}x_5 & - & \frac{5}{16}x_6 \\
            x_3 & = & \frac{3}{2} & - &  \frac{3}{8}x_2 & - & \frac{1}{4}x_5 & + & \frac{1}{8}x_6 \\
            x_4 & = & \frac{69}{4} & + & \frac{3}{16}x_2 & + & \frac{5}{8}x_5 & - & \frac{1}{16}x_6 \\
            \end{array}

    - Die Basislösung ist: :math:`(33/4,0,3/2,69/4,0,0)` und der Wert der Zielfunktion ist :math:`111/4`.
    - Im letzten Schritte würden wir :math:`x_2` wählen. Da die zweite Nebenbedingung die Maximierung von :math:`x_2` am stärksten einschränkt, würden wir :math:`x_2` durch die Schlupfvariable :math:`x_3` ersetzen.
    - Ergebnis

      .. math::

            \begin{array}{rrrcrcrcr}
            z   & = & 28 & - & \frac{1}{6}x_3 & - & \frac{1}{6}x_5 & - & \frac{2}{3}x_6 \\
            x_1 & = &  8 & + & \frac{1}{6}x_3 & + & \frac{1}{6}x_5 & - & \frac{1}{3}x_6 \\
            x_2 & = &  4 & - & \frac{8}{3}x_3 & - & \frac{2}{3}x_5 & + & \frac{1}{3}x_6 \\
            x_4 & = & 18 & + & \frac{1}{2}x_3 & + & \frac{1}{2}x_5 \\
            \end{array}

    - Die Basislösung ist: :math:`(8,4,0,18,0,0)` und der Wert der Zielfunktion ist :math:`28`.

    - Eine weitere Verbesserung der Zielfunktion ist nicht möglich. Die Basislösung ist somit unsere optimale Lösung.

.. supplemental::

    Beobachtungen:

    - Beim Start: jede Belegung der Variablen :math:`x_1,...,x_3` definiert Werte für die Variablen :math:`x_4,...,x_6` und ist somit eine Lösung.
    - eine Lösung ist (jedoch nur) dann zulässig wenn alle Variablen nicht-negativ sind.
    - Die Basislösung ist die Lösung, bei der die nicht-Basisvariablen (im ersten Schritt also :math:`x_1, x_2` und :math:`x_3`) den Wert :math:`0` haben. Im ersten Schritt ergibt sich somit die Basislösung (:math:`\bar{x_1},...,\bar{x_6}`) :math:`(0,0,0,30,24,36)`; der Wert der Zielfunktion ist :math:`0`.




Simplex Algorithmus
------------------------

.. code:: pascal
    :number-lines:

    Algorithm Simplex(A,b,c):
        (N,B,A,b,c,v) := InitialisiereSimplex(A,b,c)
        sei Δ ein Vektor der Länge m
        while ∃ j ∈ N mit c_j > 0 do
            wähle Index e ∈ N mit c_e > 0   { e für "entering variable" }
            for Index i ∈ B
                Δ_i := b_i / A_ie falls A_ie > 0, sonst ∞
            wähle l ∈ B mit Δ_l := min(Δ_1,...,Δ_m)
            if Δ_l = ∞ then return "unbeschränkt"
            (N,B,A,b,c,v) := Pivot(N,B,A,b,c,v,l,e)
        for i := 1 to n                     { Gib die Lösung zurück }
            if i ∈ B then
                x_i := b_i
            else
                x_i := 0
        return (x_1,...,x_n)

.. supplemental::

    .. rubric:: InitialisiereSimplex(A,b,c)

    Falls das LP lösbar ist, dann gib das LP in Schlupfform zurück, in der die initiale Basislösung zulässig ist.

    Wir werden uns im Rahmen dieses Kurses nicht weiter mit der Implementierung des Simplex-Algorithmus beschäftigen. Es ist jedoch wichtig, dass Sie die Funktionsweise des Algorithmus verstehen.



.. class:: exercises transition-move-to-top

Übung
--------

.. exercise::  Anwenden des Simplex-Algorithmus

    Berechnen Sie die optimale Lösung für das folgende lineare Programm:

    .. math::

        \begin{array}{rrcrcl}
        \text{maximiere}                    & 40x_1 & + & 30x_2 & \\
        \text{unter den Nebenbedingungen}   & x_1   & + & x_2   & \leq & 8 \\
                                            & 2x_1  & + & x_2   & \leq & 12 \\
                                            & 2x_1  & + & 3x_2  & \leq & 18 \\
                                            &  x_1, & x_2 &     & \geq & 0
        \end{array}

    .. solution::
        :pwd: 270_ist_der_Wert

        Überführung in Schlupfform

        .. math::

            \begin{array}{rcrcrcr}
            z   & = &       &   & 40x_1 & + & 30x_2 \\
            x_3 & = &  8    & - &  x_1  & - &  x_2  \\
            x_4 & = & 12    & - & 2x_1  & - &  x_2  \\
            x_5 & = & 18    & - & 2x_1  & - & 3x_2  \\
            \end{array}

        Wir können jetzt :math:`x_1` oder :math:`x_2` wählen, da beide in der Zielfunktion vorkommen und positive Koeffizienten haben. Wir wählen :math:`x_2` und versuchen :math:`x_2` zu maximieren. Da der maximale Wert, den :math:`x_2` annehmen kann, für die dritte Ungleichung (:math:`6`) am geringsten ist (in den anderen Fällen wäre es der Wert :math:`8` bzw. :math:`12`), tauschen wir :math:`x_2` und :math:`x_5`.


        .. math::

            \begin{array}{rcrcrcr}
            3x_2 & = & 18    & - & 2x_1  & - & x_5  \\
            x_2 & = & 6    & - & \frac{2}{3}x_1  & - & \frac{1}{3}x_5  \\
            \end{array}

        Nach dem Einsetzen von :math:`x_2` in die Zielfunktion und die anderen Nebenbedingungen erhalten wir:

        .. math::

            \begin{array}{rcrcrcr}
            z   & = & 40x_1 & + & 30(6 & - & \frac{2}{3}x_1 & - & \frac{1}{3}x_5) \\
            z   & = & 40x_1 & + & 180 & - & 20x_1 & - & 10x_5 \\
            z   & = & 20x_1 & - & 10x_5 & + & 180 \\
            x_3 & = &     8 & - & x_1 & - & 6 & + & \frac{2}{3}x_1 & + & \frac{1}{3}x_5 \\
            x_3 & = &     2 & - & \frac{1}{3}x_1 & + & \frac{1}{3}x_5 \\
            x_4 & = &    12 & - & 2x_1 & - & 6 & + & \frac{2}{3}x_1 & + & \frac{1}{3}x_5  \\
            x_4 & = &     6 & - & \frac{4}{3}x_1 & + & \frac{1}{3}x_5  \\
            x_2 & = &     6 & - & \frac{2}{3}x_1  & - & \frac{1}{3}x_5  \\
            \end{array}

        Wir können nun :math:`x_1` maximieren, da der Koeffizient von :math:`x_1` in der Zielfunktion positiv ist. Wir tauschen :math:`x_1` und :math:`x_4`:

        .. math::

            \begin{array}{rcrcrcr}
            \frac{4}{3}x_1 & = &           6 & - &            x_4 & + & \frac{1}{3}x_5 \\
                       x_1 & = & \frac{9}{2} & - & \frac{3}{4}x_4 & + & \frac{1}{4}x_5 \\
            \end{array}

        Nach dem Einsetzen von :math:`x_1` in die Zielfunktion und die anderen Nebenbedingungen erhalten wir:

        .. math::

            \begin{array}{rcrcrcr}
            z   & = & 20(\frac{9}{2} & - & \frac{3}{4}x_4 & + & \frac{1}{4}x_5) & - & 10x_5 & + & 180 \\
            z   & = &             90 & - &          15x_4 & + &            5x_5 & - & 10x_5 & + & 180 \\
            z   & = &             90 & - &          15x_4 & - &            5x_5 &   &       & + & 180 \\
            z   & = &            270 & - &          15x_4 & - &            5x_5 \\
            x_3 & = &              2 & - & \frac{1}{3}(\frac{9}{2} & - & \frac{3}{4}x_4  & + & \frac{1}{4}x_5) & + & \frac{1}{3}x_5 \\
            x_3 & = &              2 & - &             \frac{3}{2} & + & \frac{1}{4}x_4  & - & \frac{1}{12}x_5 & + & \frac{1}{3}x_5 \\
            x_3 & = &    \frac{1}{2} & + &          \frac{1}{4}x_4 & + & \frac{1}{4}x_5  \\
            x_2 & = &              6 & - & \frac{2}{3}(\frac{9}{2} & - & \frac{3}{4}x_4  & + & \frac{1}{4}x_5) & - & \frac{1}{3}x_5  \\
            x_2 & = &              6 & - &                       3 & + & \frac{1}{2}x_4  & - &  \frac{1}{6}x_5 & - & \frac{1}{3}x_5  \\
            x_2 & = &              3 & + &          \frac{1}{2}x_4 & - & \frac{1}{2} x_5 \\
            x_1 & = &    \frac{9}{2} & - &          \frac{3}{4}x_4 & + & \frac{1}{4}x_5  \\
            \end{array}

        Somit ist die Lösung (eine Maximierung der Zielfunktion ist nicht mehr möglich!): :math:`x_1 = \frac{9}{2}`, :math:`x_2 = 3`, :math:`x_3 = \frac{1}{2}`, :math:`x_4 = 0`, :math:`x_5 = 0` und :math:`z = 270`.

        .. container:: peripheral

            (Ein Einsetzen der Wert für :math:`x_1` und :math:`x_2` in die Originalzielfunktion bestätigt das Ergebnis (:math:`270`) und das Einsetzen bestätigt auch das Einhalten der Nebenbedingungen.)




.. class:: new-section transition-move-to-top

Mixed-Integer-Programmierung (MIP)
--------------------------------------------------------



MIP: einige (oder alle) Variablen sind ganzzahlig
---------------------------------------------------------------------------------------------------

.. story::

    .. grid::

        .. cell::

            **Zielfunktion (Maximiere)**

            .. math::

                    x_1 + x_2

            **Nebenbedingungen**

            .. math::

                    \begin{array}{rcrcl}
                    4 x_1 & - &   x_2 & \leq & 8 \\
                    2 x_1 & + &   x_2 & \leq & 10 \\
                    5 x_1 & - & 2 x_2 & \geq & -2 \\
                        &   &   x_1 & \geq & 0 \quad \text{und ganzzahlig} \\
                        &   &   x_2 & \geq & 0 \quad \text{und ganzzahlig} \\
                    \end{array}


        .. cell:: incremental

            .. image:: images/lp_optimum_is_integer_solution.svg
                :align: center
                :height: 900px

    .. grid:: incremental

        .. cell::

            **Zielfunktion (Maximiere)**

            .. math::

                    x_1 + x_2

            **Nebenbedingungen**

            .. math::

                    \begin{array}{rcrcrl}
                    4 x_1 & - &   x_2 & \leq & 8 \\
                    2 x_1 & + &   x_2 & \leq & 10,5 \\
                    5 x_1 & - & 2 x_2 & \geq & -2 \\
                          &   &   x_1 & \geq & 0 & \text{und ganzzahlig} \\
                          &   &   x_2 & \geq & 0 & \text{und ganzzahlig} \\
                    \end{array}

        .. cell:: incremental

            .. image:: images/lp_optimum_is_no_integer_solution.svg
                :align: center
                :height: 900px

    .. hint::
        :class: incremental

        Durch die Einschränkung, dass die Variablen ganzzahlig sein müssen, wird das Problem schwieriger zu lösen und ist NP-schwer, während das lineare Programm in polynomieller Zeit gelöst werden kann.

    .. container:: incremental rounded-corners box-shadow padding-1em dhbw-dark-gray-background white

        Zur Lösung von MIPs gibt es verschiedene Ansätze, wie z. B. den Branch-and-Bound-Algorithmus, bzw. Branch-and-Cut-Algorithmus. Häufig werden in der Praxis auch Kombinationen von Algorithmen eingesetzt, die auf dem Simplex-Algorithmus basieren.


.. supplemental::

    Wenn alle Variablen ganzzahlig sind, sprechen wir von einem reinen ganzzahligen Programm (:eng:`Integer Programming`). Wenn nur einige Variablen ganzzahlig sind, sprechen wir von einem gemischt ganzzahligem Programm.




.. class:: no-title center-content

Fokussierung auf Lösungsstrategien
--------------------------------------------


.. remark::

    Wir konzentrieren uns im Folgenden darauf für konkrete Probleme, ganzzahlige Programme zu entwickeln. Wir betrachten die zugrunde liegenden Algorithmen nicht.



Binärvariablen oder ganzzahlige Variablen?
---------------------------------------------

.. rubric:: Sudokus lösen

.. grid::

   .. cell::

        .. image:: images/sudoku.svg
            :align: center
            :height: 400px

   .. cell::

        **Naiver Ansatz**

        Wir verwenden :math:`81` Integer Variablen :math:`1 ≤ y_i ≤ 9`.

        .. container:: incremental

            Und jetzt?

.. container:: incremental

    .. rubric:: Faustregel

    • Verwenden Sie allgemeine Ganzzahlen (Integers), wenn sie tatsächliche Mengen darstellen und die Reihenfolge wichtig ist!
    • Verwenden Sie Binärzahlen (:math:`\{0,1\}` für jeden möglichen Wert einer Ganzzahl), wenn die Ganzzahlen konzeptuell nur „einige verschiedene Werte“ darstellen!



.. class:: new-subsection transition-move-to-top

Beispiel: SEND + MORE = MONEY mittels Integer Programmierung
--------------------------------------------------------------



Problembeschreibung: SEND+MORE=MONEY\ [#]_
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

.. [#] Mit Hilfe von (Mixed-)Integer-Programmierung lässt sich dieses Problem schnell lösen.



Lösung des Rätsels: SEND+MORE=MONEY
----------------------------------------------------------

.. deck::

    .. card::

        :Initiale Idee:

            - Jeder Buchstabe wird durch eine Variable repräsentiert, die auf den Wertebereich 0 bis 9 beschränkt ist.
            - Die Gleichung (zum Optimieren) wird dann wie folgt dargestellt:

              .. math::

                \begin{array}{rrrrr}
                    & 1000 \cdot S + & 100 \cdot E + & 10 \cdot N + & D &+  \\
                    & 1000 \cdot M + & 100 \cdot O + & 10 \cdot R + & E & = \\
                    10000 \cdot M + & 1000 \cdot O + & 100 \cdot N + & 10 \cdot E + & Y &
                \end{array}

        .. class:: incremental

        :Ergebnis: Alle Variablen bekommen den Wert „0“ zugewiesen.


    .. card::

        :Herausforderung:
            Wir müssen die Nebenbedingungen formulieren, die sicherstellen, dass die Variablen die Werte 0 bis 9 annehmen und dass keine Ziffer doppelt vorkommt.

        .. class:: incremental

        :Problem: Es ist nicht direkt möglich eine mathematische Formulierung zu finden, die die Nebenbedingungen beschreibt.

        .. class:: incremental

        :Lösungsansatz (häufiger Ansatz bei „\ *Set-Partitioning-Problems*\ “):
            - Jeder Variablen ([S, E, N, D, M, O, R, Y]) werden jeweils 10 binäre Variablen zugewiesen, die den Wert 0 oder 1 annehmen, wenn die Variable den entsprechenden Wert hat.

    .. card::

        .. rubric:: Nebenbedingungen

        - Jede Variable hat genau einen Wert
        - Keine Ziffer darf doppelt vorkommen

        .. math::
            :class: incremental

            \begin{array}{cccccccl}
                S_0 & + & S_1 & + & \ldots & + & S_9 & = & 1 \\
                + &  & + &  & \ldots &  & + & &  \\
                E_0 & + & E_1 & + & \ldots & + & E_9 & = & 1 \\
                \vdots & & \vdots & & \vdots & & \vdots & & \\
                + &  & + &  & \ldots &  & + & &  \\
                Y_0 & + & Y_1 & + & \ldots & + & Y_9 & = & 1 \\
                \shortparallel &   & \shortparallel & & & &  \shortparallel & \\
                1 &  & 1 &  & \ldots &  & 1 & \\
            \end{array}


    .. card::

        .. rubric:: „Optimierungsziel“

        .. math::
            :class: incremental font-size-80

            \begin{array}{r}
                \displaystyle\sum_{i=0}^{9} i \cdot S_i  \times 1000 + \sum_{i=0}^{9} i \cdot E_i  \times 100 + \sum_{i=0}^{9} i \cdot N_i  \times 10 + \sum_{i=0}^{9} i \cdot D_i  \times 1 & + \\
                \displaystyle\sum_{i=0}^{9} i \cdot M_i  \times 1000 + \sum_{i=0}^{9} i \cdot O_i  \times 100 + \sum_{i=0}^{9} i \cdot R_i  \times 10 + \sum_{i=0}^{9} i \cdot E_i  \times 1 & = \\
                \displaystyle\sum_{i=0}^{9} i \cdot M_i  \times 10000 + \sum_{i=0}^{9} i \cdot O_i  \times 1000 + \sum_{i=0}^{9} i \cdot N_i  \times 100 + \sum_{i=0}^{9} i \cdot E_i  \times 10 + \sum_{i=0}^{9} i \cdot Y_i  \times 1 &
            \end{array}



    .. card::

        Umsetzung in Python mit Hilfe von `PuLP <https://coin-or.github.io/pulp/>`

        .. rubric:: Imports

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: copy-to-clipboard
            :end-before: VALS

        .. rubric:: Variablen

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: copy-to-clipboard
            :start-after: )
            :end-before: # Nebenbedingungen

    .. card::

        .. rubric:: Nebenbedingungen

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: copy-to-clipboard
            :start-after: # Nebenbedingungen
            :end-before: # Ziel

    .. card::

        .. rubric:: „hauptsächliche Nebenbedingung“

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: copy-to-clipboard
            :start-after: # Ziel
            :end-before: # Lösung

    .. card::

        .. rubric:: Lösung berechnen lassen

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: copy-to-clipboard
            :start-after: # Lösung berechnen

        .. rubric:: Ausgabe

        ::

            S=8; E=3; N=2; D=4; M=0; O=9; R=1; Y=7

        .. container::  incremental box-shadow padding-1em far-smaller rounded-corners margin-top-1em

            Code: |send_more_money.py|

.. supplemental::

    Eine Formulierung wie :math:`28 \leq S + E + N + D + M + O + R + Y \leq 44`, um sicherzustellen, dass (zumindest einige) Variablen nicht :math:`0` sind stellt leider nicht die gewünschte Nebenbedingung sicher, dass jeder Wert nur einmal vergeben wird (:math:`0 + 1+ 2 + 3 + 4 + 5 + 6 + 7 = 28` und :math:`0 + 1+ 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 45`).

    Eine Lösung mit obiger Nebenbedingung wäre zum Beispiel:

    ::

            S=0 E=8 N=9 D=0
            M=0 O=0 R=9 E=8
        M=0 O=0 N=9 E=8 Y=8

    .. rubric:: PuLP (Details)

    Die Datenstruktur :python:`choices` ist ein Dictionary mit folgenden Aufbau:

    .. code:: python

        choices =
            {'S': {0: Choice_S_0, 1: Choice_S_1,..., 9: Choice_S_9},
             ...
             'Y': {0: Choice_Y_0, 1: Choice_Y_1,..., 9: Choice_Y_9}}
        # choices['S'][0].name == 'Choice_S_0'



.. class:: exercises transition-move-to-top

Übung
--------------------------------------------------------

.. exercise:: Maximaler Fluss

    Berechnen Sie für folgenden Graphen den maximalen Fluss mit Hilfe von Pulp. Der Graph ist in der Vorlage definiert und kann als Grundlage für das Lösen des Problems verwendet werden. Orientieren Sie sich an dem Programm, dass sie im Vorfeld für das *Maximum-Flow-Problem* erstellt haben.

    .. image:: images/max-flow/capacities.svg
        :height: 400px
        :align: center

    .. solution::
        :pwd: Straightforward

        .. include:: code/max_flow.py
            :code: python
            :class: copy-to-clipboard far-smaller
            :start-after: # Solution

.. supplemental::

    Vorlage

    .. include:: code/max_flow.py
        :code: python
        :class: copy-to-clipboard far-smaller
        :end-before: # Solution



.. class:: exercises transition-move-to-top

Übung
--------------------------------------------------------

.. exercise:: Gruppenzuteilung

    Finden Sie eine sehr gute Aufteilung von Personen (Studierenden) auf eine feste Anzahl an Gruppen, basierend auf den Präferenzen der Personen. Nutzen Sie dazu Mixed-Integer-Programmierung. Im Template ist eine initiale Aufgabenstellung hinterlegt, die es zu lösen gilt: Verteilung von 16 Studierenden auf 4 Gruppen inkl. Bewertungsmatrix :peripheral:`(jeder Studierende hat jeden anderen mit Werten von 1 bis 10 bewertet)`. Ggf. ist die Funktion `pulp.allcombinations` beim Modellieren hilfreich.

    .. container:: slightly-more-smaller rounded-corners box-shadow padding-1em

        |group_assignment_template.py|


    .. solution::
        :pwd: ALLE Kombinationen bewerten

        *Ein Lösungsvorschlag*

        .. include:: code/group_assignment.py
            :code: python
            :number-lines:

.. exercise:: Alle Gruppen gleich glücklich machen

    Fragen Sie sich was Sie tun müssten, wenn Sie zusätzlich sicherstellen wollen, dass alle Gruppen in etwa die gleiche Glücklichkeit haben sollen. (Hier geht es nur um ein Gedankenexperiment.)

    .. solution::
        :pwd: nicht-lineares Optimierungsproblem

        An dieser Stelle müssten wir gleichzeitig den Zielwert maximieren, während wir die Varianz zwischen den Gruppenglücklichkeiten minimieren. Dies kann man nicht mehr (realistisch) als lineares Optimierungsproblem modellieren. Hierzu sind andere Techniken (zum Beispiel aus dem Bereich Constraint Integer Programming bzw. nicht-lineare Programmierung) notwendig.



.. class:: transition-move-to-top

Klausurvorbereitung
--------------------------------------------------------

- Finden Sie eine Formulierung für das Lösen von Sudokus mittels Mixed-Integer-Programmierung.



.. class:: transition-flip

Nächste Schritte
-------------------

- Studiere Mathematische Programmiersprachen (AMPL, `ZIMPL  <https://webdoc.sub.gwdg.de/ebook/serien/ah/ZIB/ZR-12-27.pd>`__ .)
- Studiere verfügbare Bibliotheken zum Lösen entsprechender Probleme (GLPK, SCIP, `Gurobi <https://www.gurobi.com/>`__, `CPLEX <https://www.ibm.com/analytics/cplex-optimizer>`__, ...)

.. hint::

    PuLP ist ein einfaches, aber mächtiges Werkzeug, um lineare und gemischt-ganzzahlige Programme zu beschreiben. PuLP nutzt im Hintergrund verschiedene Solver!
