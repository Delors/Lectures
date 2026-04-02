.. meta::
    :lang: de
    :author: Michael Eichberg
    :keywords: "Suche", "Arrays", "Algorithmen", "Datenstrukturen"
    :description lang=de: Suchen auf Arrays
    :id: lecture-theo-algo-suchen_auf_arrays
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Suchen auf Arrays
======================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1.6 [Themed]

.. container:: peripheral

    :Quelle:
        Die Folien sind teilweise inspiriert von oder basierend auf Lehrmaterial von Prof. Dr. Ritterbusch


.. supplemental::

    :Folien:

        |html-source|

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-move-to-top

Einführung
--------------------------------------------------------



Skalierung von Daten
--------------------------------------------------------

Welche Skalierung haben gesuchte Daten sind im Array?

:nominal: Nur Vergleich auf Gleichheit, keine natürliche Ordnung oder Zahlbegriff.
:ordinal: Es gibt Größenvergleiche und damit eine Sortierung, aber kein Zahlbegriff.
:kardinal: Es gibt Größenvergleiche und Zahlbegriff.

- *Unsortiert oder nominal* führt (zunächst) zur linearen Suche.
- *Ordinale und kardinale Werte* können sortiert werden für binäre Suche.
- *Kardinale Größen* können modelliert werden für interpolierende Suche.

.. hint::

    Für unsere Betrachtung gehen wir im Folgenden davon aus, dass die Daten sortiert sind. Beim Vergleich der Algorithmen beschränken wir uns auf eine Betrachtung der Anzahl der Elementzugriffe.

.. supplemental::

    - Ein Beispiel für eine *nominal* skalierte Datenmenge wäre die Menge der Farben. Es gibt keine natürliche Ordnung der Farben, und es gibt auch keinen natürlichen Zahlenbegriff, der die Farben beschreibt. Ein weiteres Beispiel ist eine Liste von Wohnorten.
    - Ein Beispiel für eine *ordinale* skalierte Datenmenge wäre die Menge der Kleidergrößen (S,M,L,XL,...). Es gibt eine natürliche Ordnung der Kleidergrößen, aber es gibt keinen natürlichen Zahlenbegriff, der die Kleidergrößen beschreibt. Ein weiteres Beispiel ist die Bewertung von Filmen auf einer Skala von 1 bis 5 Sternen.



Lineare Suche
--------------------------------------------------------

.. code:: pascal
    :number-lines:

    // A        1-indiziertes Array, das durchsucht wird
    // n        Größe des Arrays
    // needle   der zu suchende Wert
    function LinearSearch(A,n,needle)
        for i := 1,...,n do
            if A[i] == needle then
                return i
        return nil

Laufzeit und Elementzugriffe kann asymptotisch durch :math:`O(n)` abgeschätzt werden.





Binäre Suche
--------------------------------------------------------

.. code:: pascal
    :number-lines:

    // A        aufsteigend sortiertes Array, das durchsucht wird
    // l        untere Grenze (Index im Array)
    // u        obere Grenze  (Index im Array: untere Grenze <= obere Grenze)
    // needle   der zu suchende Wert
    function BinarySearch(A,l,u,needle)
        upper := u
        lower := l
        repeat
            pos     := floor((upper + lower) / 2)
            value   := A[pos]
            if      value == needle then return pos
            else if value > needle  then
                    upper := pos − 1
            else    lower := pos + 1
        until upper < lower
        return nil

Laufzeit ist :math:`O(\log(n))`, genauer im Schnitt :math:`\log_2(n)−1` Zugriffe.




Effizientere Suche bei linearer Verteilung
----------------------------------------------------------

.. deck::

    .. card::

        .. image:: images/lagrange/lin-1.svg
            :width: 1700px
            :align: center
            :class: light-image

    .. card:: overlay

        .. image:: images/lagrange/lin-2.svg
            :width: 1700px
            :align: center
            :class: light-image


.. supplemental::

    In diesem Beispiel gehen wir davon aus, dass die Werte *im Wesentlichen* linear verteilt sind. Das bedeutet, dass die Differenz zwischen zwei aufeinanderfolgenden Werten immer gleich ist.

    Sei beispielsweise ein Array ``a`` mit folgenden Werten geben (Auszug):

    .. csv-table::
        :header: "Index i", "Wert"
        :widths: 10, 10

        i = 10, :peripheral:`a[i = 10] =`  20.0
        ..., ...
        i = 30,49.5
        ..., ...
        i = 50,87.2
        ..., ...
        i = 70,151.3
        ..., ...
        i = 90,169.9
        ..., ...
        i = 110,220.0
        ..., ...
        i = 130,251.2

    Wenn man jetzt exemplarisch die Paare: :math:`(i = 10, a[i] = 20.0)`, und :math:`(i = 110, a[i] = 220)` betrachtet, dann kann man zu dem Schluss kommen, dass die Funktion :math:`f(x) = 2.0\cdot x` eine Approximation der Verteilung der Werte ist. Würde man also nach dem Wert :math:`a[i] = y = 170` suchen wollen, dann wäre es gut als erstes den Wert von :math-r:`a[85]` zu überprüfen, :math:`170 = 2\cdot x \rightarrow \frac{170} {2} = 85 = i`.




Effizientere Suche bei exponentieller Verteilung
--------------------------------------------------------

.. deck::

    .. card::

        .. image:: images/lagrange/expo-1.svg
            :width: 1700px
            :align: center
            :class: light-image

    .. card::  overlay

        .. image:: images/lagrange/expo-2.svg
            :width: 1700px
            :align: center
            :class: light-image

    .. card::  overlay

        .. image:: images/lagrange/expo-3.svg
            :width: 1700px
            :align: center
            :class: light-image

    .. card::  overlay

        .. image:: images/lagrange/expo-4-quadratic-approx.svg
            :width: 1700px
            :align: center
            :class: light-image


.. supplemental::

    In diesem Beispiel gehen wir davon aus, dass die Werte *im Wesentlichen* exponentiell verteilt sind. Das bedeutet, dass die Differenz zwischen zwei aufeinanderfolgenden Werten immer größer wird.

    Sei beispielsweise ein Array ``a`` mit folgenden Werten geben (Auszug):

    .. csv-table::
        :header: "index i", "a[i]"
        :widths: 10, 10

        0, 0
        ..., ...
        20, 5
        ..., ...
        50, 25
        ..., ...
        70, 75.7
        ..., ...
        90, 110
        ..., ...
        110, 380
        ..., ...
        125, 579.5
        ..., ...
        130, 794


    Wenn man jetzt exemplarisch die Paare: :math:`(i = 20, a[i] = 5.0)`, und :math:`(i = 130, a[i] = 794)` betrachtet, und eine lineare Approximation durchführt, dann könnte man zu dem Schluss kommen, dass die Funktion :math:`f(x) = 6.1\cdot x` eine gute Approximation ist.

    Würde man eine quadratische Approximation mit Hilfe von Lagrange durchführen, zum Beispiel mit den Werten :math:`(i = 20, a[i] = 5.0)`, :math:`(i = 90, a[i] = 110)` , und :math:`(i = 130, a[i] = 794)`. Dann wäre der Fehler zwischen der realen Verteilung und der angenommen deutlich geringer, da die quadratische Funktion die Werte besser approximiert.

    In diesem Fall Fall wäre die Funktion: :math:`p(x) = \frac{39}{275}x^2 - \frac{141}{10}x + \frac{2533}{11}`.
    In diesem Fall können wir die Position des Wertes 650 im Array besser abschätzen (durch die Aufstellung der Umkehrfunktion und dann einsetzen von 650): :math:`\approx 123`.

    .. warning::

        Eine vernünftige Interpolation ist nur dann möglich, wenn die Verteilung der Werte im Wesentlichen bekannt ist.




Approximation der Verteilung
--------------------------------------------------------

.. important::

    Wenn wir die Verteilung der Werte kennen, können wir effizientere Algorithmen entwickeln.

.. example::
    :class: incremental

    Wenn wir wissen, dass die Werte quadratisch verteilt sind (``Array[10] a = { 1, 4, 9,16, ..., 100 }``), und wir zum Beispiel wissen, dass der kleinste Wert im Array :math:`1` und der größte Wert :math:`100` (an Stelle/mit Index :math:`10`) ist, den wir im Array gespeichert haben, dann macht es „keinen“ Sinn den Wert :math:`85` oder :math:`5` in der Mitte zu suchen! (:math:`85` findet sich vermutlich an Stelle :math:`9 = \lfloor\sqrt{85}\rfloor`).




Interpolation mit Lagrange-Polynomen
--------------------------------------------------------

.. deck::

    .. card::

        Speichert unser Array kardinal skalierte Daten, so können diese modelliert werden. Das einfachste Prinzip ist die Polynominterpolation mittels Lagrange-Polynomen.

        Das Ziel ist es, ein Polynom :math:`p(x)` zu finden, das eine Funktion :math:`f(x)` an einer gegebenen Menge von Punkten :math:`(x_1, y_1), \dots, (x_n, y_n)` *exakt* interpoliert. Das heißt:

        .. math::

            p(x_i) = y_i \quad \text{für alle } i = 1, \dots, n

    .. card::

        .. theorem::

            Das Lagrange-Interpolationspolynom :math:`p(x)` wird als Summe von Lagrange-Basispolynomen :math:`l_i(x)` aufgebaut:

            .. math::

                p(x) = \sum_{i=1}^n \left( y_i \cdot l_i(x) \right)

            wobei :math:`l_i(x)`, das :math:`i`-te Lagrange-Basispolynom, gegeben ist durch:

            .. math::

                l_i(x) = \prod_{\substack{j= 1 \\ j \neq i}}^{n} \frac{x - x_j}{x_i - x_j}

        .. supplemental::

            .. remark::

                .. rubric:: Warum ist diese Konstruktion korrekt? (Intuition)

                Man kann beobachten, dass für einen gegebenen Punkt :math:`(x_i,y_i)` nur das Lagrange-Basispolynom :math:`L_i`  an der Stelle :math:`x_i` nicht zu 0 wird, da :math:`x_i` kein Faktor im Produkt von :math:`L_i` ist; oder anders ausgedrückt: einer der Faktoren :math:`(x_i−x_k)` im Produkt der anderen Basispolynome wird garantiert zu :math-r:`0`, d. h. es gilt :math:`L_j(x_i)=0`  für alle :math:`j≠i`.

                Das Lagrange-Basispolynom :math:`L_i` für den Punkt :math:`(x_i,y_i)` ist weiterhin so konstruiert, dass es zu eins evaluiert, womit die Multiplikation mit :math:`y_i` notwendigerweise den gewünschten Zielwert ergibt. Alle anderen Summanden sind :math-r:`0`.


    .. card::

        Sind :math:`n` Tupel :math:`(x_n ,y_n ) ∈ \mathbb{R}^2` reeller Zahlen gegeben mit :math:`x_l \neq x_m` für :math:`l  \neq m`.

        Das Lagrange-Interpolationspolynom hat dann höchstens den Grad :math:`n-1` und es gilt :math:`p(x_i ) = y_i` für alle :math:`i= 1,...,n`.

    .. card::

        .. example::

            Gegeben sein die zwei Punkte: :math:`(x_1, y_1) = (1, 2)` und :math:`(x_2, y_2) = (3, 4)`.

            Das Lagrange-Polynom :math:`p(x)` ist dann:

            1. .. math::

                l_1(x) = \frac{x - x_2}{x_1 - x_2} = \frac{x - 3}{1 - 3} = \frac{3 - x}{2}

            2. .. math::

                l_2(x) = \frac{x - x_1}{x_2 - x_1} = \frac{x - 1}{3 - 1} = \frac{x - 1}{2}

            3. .. math::

                p(x) = y_1 \cdot l_1(x) + y_2 \cdot l_2(x) = 2 \cdot \frac{3 - x}{2} + 4 \cdot \frac{x - 1}{2} = x + 1


            Nach Ausmultiplizieren und Zusammenfassen ergibt das ein Polynom, das durch beide Punkte verläuft.

    .. card::

        Wenn zwei Punkte gegeben sind, ist das Lagrange Polynom somit:

        .. math::

            p(x) = y_1 \cdot \frac{x - x_2}{x_1 - x_2} + y_2 \cdot \frac{x - x_1}{x_2 - x_1}

        .. container:: incremental

            Bei drei Punkten ist das Lagrange Polynom somit:

            .. math::

                \begin{array}{rl}
                p(x) = & y_1 \cdot \frac{(x - x_2)(x - x_3)}{(x_1 - x_2)(x_1 - x_3)} + \\
                       & y_2 \cdot \frac{(x - x_1)(x - x_3)}{(x_2 - x_1)(x_2 - x_3)} + \\
                       & y_3 \cdot \frac{(x - x_1)(x - x_2)}{(x_3 - x_1)(x_3 - x_2)}
                \end{array}


.. supplemental::

    Der Grad unseres Lagrange-Polynoms ist immer um 1 kleiner als die Anzahl der gegebenen Punkte (die Terme des Basispolynom sind nur für :math:`j \neq i` definiert). Das bedeutet, dass wir für zwei Punkte ein lineares Polynom erhalten, für drei Punkte ein quadratisches Polynom, für vier Punkte ein kubisches Polynom, und so weiter. Weiterhin stellt die Konstruktion sicher, dass wir durch alle gegebenen Punkte gehen.



.. class:: exercises

Übung
--------

.. exercise:: Bestimme p(2)

    Bestimmen Sie direkt :math:`p(2)` für das quadratische Polynom mit den Eigenschaften:

    .. math::

        p(-10) = 3, p(-8) = 1, p(-4) =-1

    .. solution::
        :pwd: korrektes Arbeiten ist wichtig

        .. math::

            \text{d. h.}\qquad (x_1 = -10, y_1 = 3), (x_2 = -8, y_2 = 1), (x_3 = -4, y_3 = -1)

            p(x) = 3 \cdot \frac{(x + 8)(x + 4)}{12} + 1 \cdot \left(-\frac{(x + 10)(x + 4)}{8}\right) + (-1) \cdot \frac{(x + 10)(x + 8)}{24}

            p(2) = 3 \cdot \frac{(2 + 8)(2 + 4)}{12} + 1 \cdot \left(-\frac{(2 + 10)(2 + 4)}{8}\right) + (-1) \cdot \frac{(2 + 10)(2 + 8)}{24} = 1


        Aufstellen des Lagrangepolynoms (hier nicht gefordert!):

        .. math::

            p(x) = \frac{6(x^2 + 12x + 32)}{24} - \frac{3(x^2 + 14x + 40)}{24} - \frac{x^2 + 18x + 80}{24}.

            p(x) = \frac{x^2 + 6x - 4}{12}.

.. exercise:: Bestimme p(-1)

    Für die gegebenen Punkte, bestimmen Sie erst das Lagrange Polynom :math:`p(x)` im Allgemeinen und rechnen Sie dann den Wert für :math:`p(-1)` aus.

    .. math::

        p(2) = 4, p(4) = 6, p(7) = 3

    .. solution::
        :pwd: doch - ist immer wichtig

        .. math::

              \text{d. h.}\qquad  (x_1 = 2, y_1 = 4), (x_2 = 4, y_2 = 6), (x_3 = 7, y_3 = 3)

        1. Basispolynom :math:`l_1(x)`:

           .. math::

                l_1(x) = \frac{x - x_2}{x_1 - x_2} \cdot \frac{x - x_3}{x_1 - x_3}

           Setzen der Werte:

           .. math::

                l_1(x) = \frac{x - 4}{2 - 4} \cdot \frac{x - 7}{2 - 7} = \frac{x - 4}{-2} \cdot \frac{x - 7}{-5}

           Vereinfachen:

           .. math::

                l_1(x) = \frac{(x - 4)(x - 7)}{10}


        2. Basispolynom :math:`l_2(x)`:

           .. math::
                l_2(x) = \frac{x - x_1}{x_2 - x_1} \cdot \frac{x - x_3}{x_2 - x_3}

           Setzen der Werte:

           .. math::
                l_2(x) = \frac{x - 2}{4 - 2} \cdot \frac{x - 7}{4 - 7} = \frac{x - 2}{2} \cdot \frac{x - 7}{-3}

           Vereinfachen:

           .. math::
                l_2(x) = -\frac{(x - 2)(x - 7)}{6}

        3. Basispolynom :math:`l_3(x)`:

           .. math::
                  l_3(x) = \frac{x - x_1}{x_3 - x_1} \cdot \frac{x - x_2}{x_3 - x_2}

           Setzen der Werte:

           .. math::
                l_3(x) = \frac{x - 2}{7 - 2} \cdot \frac{x - 4}{7 - 4} = \frac{x - 2}{5} \cdot \frac{x - 4}{3}

           Vereinfachen:

           .. math::
                l_3(x) = \frac{(x - 2)(x - 4)}{15}

        Multiplizieren und zusammenfassen:

        .. math::
            p(x) = \frac{4(x - 4)(x - 7)}{10} - \frac{6(x - 2)(x - 7)}{6} + \frac{3(x - 2)(x - 4)}{15}

            p(x) = \frac{4x^2 - 44x + 112}{10} - x^2 + 9x - 14 + \frac{3x^2 - 18x + 24}{15}.


            p(x) = -\frac{2x^2}{5} + \frac{17x}{5} - \frac{6}{5}.

            p(-1) = -\frac{2(-1)^2}{5} + \frac{17(-1)}{5} - \frac{6}{5} = - \frac{2}{5} - \frac{17}{5} - \frac{6}{5} = -\frac{25}{5} = -5



Interpolierende Suche - lineare Approximation
--------------------------------------------------------

.. example::
    :class: dd-margin-left-4em

    :Gegeben: Vom Array :java:`a` sei bekannt: :java:`a[1] = 0`, :java:`a[20] = 30` und :java:`a[40] = 120`.

    :Frage: Ist der Wert ``50`` im Array enthalten?

    .. container:: incremental

        .. note::
            :class: width-30 s-font-size-80

            Wir möchten die Position des Wertes :math-r:`50` im Array abschätzen! Deswegen sind im linearen Modell die Paare :math:`(x_1,y1) = (30,20)` und :math:`(x_2,y_2) = (120,40)` zu wählen. D. h. die Indizes sind unsere y-Werte.

        :Lösung: Das Lagrangepolynom :math:`p(x)` mit :math:`p(30) = 20` und :math:`p(120) = 40` lautet:

            .. math::

                p(x) = 20· \frac{x−120}{30−120} + 40· \frac{x−30}{120−30}

            .. container:: incremental

                Für den gesuchten Wert :math-r:`50` ergibt sich als zu untersuchende Position:

                .. math::

                    p(50) = 20· \frac{50−120}{30−120} + 40· \frac{50−30}{120−30} = \frac{220}{9} \approx 24

.. supplemental::

    Eine binäre Suche würde in diesem Fall mit der Position :math:`{40+20 \over 2} = 30` beginnen.

    .. hint::

        Das Lagrangepolynom kann per Konstruktion die Position der Werte :math:`30` und :math:`120` perfekt bestimmen:

        .. math::

                p(30) = 20· \frac{30−120}{30−120} + 40· \frac{30−30}{120−30} = 20

                p(120) = 20· \frac{120−120}{30−120} + 40· \frac{120−30}{120−30} = 40



Interpolierende Suche - quadratische Approximation
--------------------------------------------------------

.. example::
    :class: dd-margin-left-4em

    :Gegeben: Vom Array :java:`a` sei bekannt: :java:`a[1] = 0`, :java:`a[20] = 30` und :java:`a[40] = 120`.

    :Frage: Ist der Wert :math-r:`50` im Array enthalten?

    .. container:: incremental

        :Lösung: :math-r:`p(x)` mit :math-r:`p(0) = 1`,  :math-r:`p(30) = 20` und :math-r:`p(120) = 40` lautet:

        .. math::

            \begin{array}{rl}
            p(x) = & 1 \cdot \frac{(x - 30)(x - 120)}{(0-30)(0-120))} +\\
                  & 20 \cdot \frac{(x - 0)(x - 120)}{(30-0)(30-120)} + \\
                 & 40 \cdot \frac{(x - 0)(x - 30)}{(120-0)(120-30)}
            \end{array}

        .. container:: incremental

            Für den gesuchten Wert :math-r:`50` ergibt sich als zu untersuchende Position:

            .. math::

                p(50) \approx 29



Interpolierende Suche - Vergleich
--------------------------------------------------------

.. deck::

    .. card::

        .. image:: images/lagrange/comparison.svg
            :width: 1700px
            :class: light-image
            :align: center

    .. card::

        - Auf gleichverteilten Daten hat die lineare Interpolationssuche O(log log n).
        - Auf anderen Verteilungen ist lineare Interpolation oft schlechter als binäre Suche.
        - Quadratische Interpolation hat ein erweitertes Modell und schlägt binäre Suche häufig.


Lineare interpolierende Suche
--------------------------------------------------------

.. code:: pascal
    :number-lines:
    :class: copy-to-clipboard

    // A        durchsuchtes, 1-indiziertes, aufsteigend sortiertes Array
    // needle   der Wert, der gesucht wird
    function linearInterpolatingSearch(A,needle)
        lower   := 1               // index auf das kleinste Element
        upper   := length(A)       // index auf das größte Element
        vL      := A[lower]
        if vL == needle then return lower
        vU      := A[upper]
        if vU == needle then return upper
        while upper > lower do
            pos     := round(lower·(needle−vU)/(vL−vU) +
                             upper·(needle−vL)/(vU−vL))
            pos     := max(lower + 1, min(upper - 1, pos))
            value   := A[pos]
            if value == needle then return pos
            else if value < needle then
                    lower := max(pos, lower+1), vL := A[lower]
            else    upper := min(pos, upper-1), vU := A[upper]
        return nil


.. supplemental::

    Die Korrektur von :java:`pos` in Zeile 11 (:java:`pos := max(lower + 1, min(upper - 1, pos))`) stellt sicher, dass :java:`pos` immer zwischen :java:`lower` und :java:`upper` liegt. Dies ist insbesondere deswegen notwendig, weil die Interpolation nicht immer exakt ist. Stellen Sie sich zum Beispiel vor, dass die Daten polynomiell skaliert sind und sie (in Unkenntnis der echten Verteilung) die lineare Interpolationssuche verwenden. In diesem Fall kann es zu folgender Situation kommen:

    Die Werte im Array seien: [0, 4, 16, 36, 64, 100, 144, 196] (zu Grunde liegt die Funktion :math:`4x^2`) und Sie suchen nach dem Wert 194.

    Im ersten Schritt würde die lineare Interpolationssuche den Wert 194 auf Position 7 schätzen, was nutzlos wäre, aber erst einmal kein Problem verursachen würde. Da der Wert 194 aber nicht im Array enthalten ist, würde die Suche den Wert für die obere Grenze um eins korrigieren. Jetzt würde die lineare Interpolation aber mit den Werten des Arrays an Stelle 0 und 6 erfolgen (A[0]  = 0 und A[6] = 144). Das Ergebnis wäre die 2. Funktion (blau) und der Wert 194 würde auf Position 8 geschätzt, was außerhalb des Arrays liegt.

    .. image:: images/lagrange/expo-index_error_case.svg
        :width: 1500px
        :class: light-image


    Folgen die Werte im Array einer logarithmisch Verteilung, dann würde die umgekehrte Situation eintreten, d. h. es könnte am unteren Ende des Arrays zu einem ähnlichen Problem kommen, da dann die Werte oberhalb der geraden liegen würden.

    Wenn der berechnete Index außerhalb des Bereichs ist, dann kann der Algorithmus auch einfach ``nil`` zurückgeben, da der Wert dann nicht im Array enthalten ist.



Exponentielle Suche im sortierten (unbeschränkten) *Array*
-----------------------------------------------------------

.. code:: pascal
    :class: copy-to-clipboard
    :number-lines:

    // A        durchsuchtes, 1-indiziertes, aufsteigend sortiertes Array
    // needle   der Wert, der gesucht wird
    function ExponentialSearch(A,needle)
        i := 1
        while i <= length(A) and A[i] < needle do
            i := i * 2
        return BinarySearch(A, floor(i/2) + 1, min(i, length(A)), needle)


.. supplemental::

    Die Idee ist erst mit einer exponentiellen Schrittweite zu springen, um dann mit einer binären Suche den Wert zu finden. Die Laufzeit ist :math:`O(\log(i))` wobei :math-r:`i` die Position des gesuchten Wertes ist. Die Laufzeit ist also :math:`O(\log(n))`.



.. class:: exercises

Übung
--------

.. exercise:: Wer sucht, der findet 5?

    Folgende Werte sind vom Array A bekannt:

    :math:`A[1] = -27,\; A[15] = 13,\; A[29] = 29`

    Gesucht wird der potentielle Index des Wertes :math-r:`5`. Welcher Index :math-r:`i` sollte als nächstes untersucht werden bei binärer, linearer oder quadratisch interpolierender Suche?

    .. solution::
        :pwd: wer_sucht_der_sucht

        :binäre Suche:

            Da 5 zwischen :math:`-27 = A[1]` und :math:`13 = A[15]` liegt, sollte bei binärer Suche als nächstes der Index :math:`i = (1 + 15) / 2 = 8` untersucht werden.

        :linear interpolierende Suche:

            Für die linear interpolierende Suche wird :math:`p_{lin}(5)` berechnet unter den Bedingungen, dass :math:`p_{lin}(-27) = 1` und :math:`p_{lin}(13) = 15` ist. Es ergibt sich:

            :math:`p_{lin}(5) = 1·{5-13 \over -27-13} + 15·{5+27 \over 13+27} = \frac{1}{5} + 12 ≈12`

        :quadratisch interpolierende Suche:

            Für die quadratisch interpolierende Suche wird :math:`p_{quad}(5)` berechnet unter den Bedingungen, dass :math:`p_{quad}(-27) = 1`, :math:`p_{quad}(13) = 15` und :math:`p_{quad}(29) = 29` ist. Es ergibt sich:

            :math:`p_{quad}(5) = \frac{49}{5} ≈ 10`

.. exercise:: Wer sucht, der findet -1?

    Folgende Werte sind vom Array A bekannt:

    :math:`A[1] = -13,\; A[7] = -4,\; A[13] = 11`

    Gesucht wird der potentielle Index des Wertes :math-r:`-1`. Welcher Index :math-r:`i` sollte als nächstes untersucht werden bei binärer, linearer oder quadratisch interpolierender Suche?

    .. solution::
        :pwd: wer_sucht_der_findet_vielleicht

        :binäre Suche:

            Da :math:`-1` zwischen :math:`-4 = A[7]` und :math:`11 = A[13]` liegt, sollte bei binärer Suche als nächstes der Index :math:`i= (7+13)/ 2 = 10` untersucht werden.

        :linear interpolierende Suche:

            Für die linear interpolierende Suche wird :math:`p_{lin}(-1)` berechnet unter den Bedingungen, dass :math:`p_{lin}(-4) = 7` und :math:`p_{lin}(11) = 13` ist. Es ergibt sich:

            :math:`p_{lin}(-1) = 7·{-1-11 \over -4-11} + 13·{-1+4 \over 11+4} = \frac{41}{5} ≈ 8`

        :quadratisch interpolierende Suche:

            Für die quadratisch interpolierende Suche wird :math:`p_{quad}(-1)` berechnet unter den Bedingungen, dass :math:`p_{quad}(-13) = 1`, :math:`p_{quad}(-4) = 7` und :math:`p_{quad}(11) = 13` ist. Es ergibt sich:

            :math:`p_{quad}(-1) = \frac{43}{5} ≈ 9`



.. class:: exercises

Übung
--------

.. exercise:: Lineare Interpolierende Suche

    Setzen Sie den Algorithmus für die lineare interpolierende Suche in einer Programmiersprache Ihrer Wahl um.

    Testen Sie den Algorithmus mit folgenden Arrays:

    .. code:: python
        :class: copy-to-clipboard

        A = [1, 3, 5, 7, 9, 11, 13, 15]        # linear verteilt (2x-1)

        B = [0, 7, 13, 22, 27, 32, 44, 49]     # approx. 7x linear verteilt

        C = [0, 2, 16, 54, 128, 250, 432, 686] # quadratisch verteilt (4x^2)

    Wie viele Schritte (im Sinne von Schleifendurchläufen) sind maximal notwendig, um festzustellen ob ein Wert im Array enthalten ist oder nicht?

    .. solution::
        :pwd: naja...

        Für Array A sind maximal 3 Schritte notwendig, für Array B sind auch maximal 3 Schritte und für Array C maximal 5 Schritte.


        .. rubric:: |java-icon| Lösung

        .. include:: code/LineareInterpolierendeSuche.java
                :code: java
                :number-lines:
                :class: copy-to-clipboard

        .. rubric:: |python-icon| Lösung

        .. include:: code/linear_interpolating_search.py
                :code: python
                :number-lines:
                :class: copy-to-clipboard


.. class:: exercises

Übung
-------------------

.. exercise:: Exponentiell Interpolierende Suche

    #. Implementieren Sie den Algorithmus für die exponentiell interpolierende Suche in einer Programmiersprache Ihrer Wahl. (Ggf. müssen Sie noch die passende binäre Suche implementieren). Implementieren Sie die Suche basierend auf einer Funktion und nicht über einem Array. Die Funktion kann zum Beispiel eine mathematische Funktion sein, oder eine einfache Funktion die auf einem Array operiert.

    #. Gegeben sei die folgende Funktion, die als Generator fungiert. (:math:`x` sei eine natürliche Zahl).

       .. math::

            f(x) = \text{round}\left(\frac{1}{1+e^{-x}} \cdot 100\,000\,000 \right)

       Testen Sie ob :math-r:`99999996` ein Wert der Funktion ist und geben Sie den Index (:math:`x`) zurück.

    #. Wann macht es Sinn die exponentiell interpolierende Suche zu verwenden?

    .. supplemental::

        .. rubric:: |java-icon| Hinweise zur Implementierung

        In Java kann die zu übergebene Funktion den Typ :java:`java.util.function.DoubleUnaryOperator` haben. Beim Aufruf kann man dann zum Beispiel eine entsprechende Lambda-Funktion angeben werden, die für einen double Wert einen anderen double Wert zurückgibt.

        Es kann hilfreich sein alle statischen Methoden und Konstanten der :java:`Math`-Klasse über einen static import zu verwenden.

        Die exemplarische Verwendung ist wie folgt:

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            void main() {
                try {
                    IO.println(
                        "Wert 64 hat Index: " + binarySearch((double x) -> 4 * x + 3, 0, 100, 64)+ "."
                    );
                } catch (NoSuchElementException e) {
                    IO.println("Wert 64 nicht gefunden.");
                }
                IO.println(binarySearch((double x) -> (4 * x + 3), 0, 100, 43));

                try {
                    IO.println(
                        "Wert 99999996 hat Index: " +
                            exponentialSearch((double x) -> TODO, 99999996 )
                    );
                } catch (NoSuchElementException e) {
                    IO.println("Wert 99999996  nicht gefunden.");
                }
            }

    .. solution::
        :pwd: sonst_ist_er_nicht_wirklich besser

        Der Index (:math:`x`) ist :math:`17`.

        1. Wenn es keine (echte) obere Grenze gibt, da dann kein oberster Wert für die binäre Suche bestimmt werden kann.

        2. Insbesondere dann, wenn die gesuchten Werte am unteren Rande sind.

           Zum Beispiel ist die Suche nach einem Wert nahe am unteren Rand in einem Array mit zehntausenden von Werten schneller als eine reine binäre Suche!

        .. rubric:: |java-icon| Lösung

        .. rubric:: |python-icon| Lösung

        .. include:: code/exponential_search.py
            :code: python
            :number-lines:
            :start-after: # binary_search(F, l, u, needle) -> int
            :end-before: # exponential_search(F, needle) -> int

        .. include:: code/exponential_search.py
            :code: python
            :number-lines:
            :start-after: # exponential_search(F, needle) -> int
            :end-before: # eval

        .. include:: code/exponential_search.py
            :code: python
            :number-lines:
            :start-after: # run_exponential_search



.. class:: new-section transition-move-to-top

Selbstanordnende Arrays
--------------------------------------------------------


Suchen auf Arrays mit spezieller Ordnung
--------------------------------------------------------

- Sind die Daten nominal skaliert, oder sagt die Ordnung der Werte im Array nichts über die Zugriffshäufigkeit aus, so können Arrays auf Basis der Zugriffe sortiert werden.
- Erfordert prinzipiell eine lineare Suche, die es gilt soweit möglich zu beschleunigen.

.. class:: incremental

- Anwendung(-sgebiete):

  - Cache-Zugriffe, Verwaltung von virtuellem Speicher
  - Wenn Werte häufiger verlangt werden als andere, so besitzen die Anfragen eine Wahrscheinlichkeitsverteilung.
  - Die Verteilung wird durch Abzählen angenähert, da sie nicht bekannt ist. Darauf basierend werden die Werte entsprechend sortiert.



Strategien zur Anordnung
--------------------------------------------------------

.. deck::

    .. card::

        .. definition:: FC-Regel

            Ein Array A ist gemäß *frequency count* (FC-Regel) sortiert, wenn für alle Werte gilt, dass :math:`c(A[k]) \geq c(A[j])` wenn :math:`k <j` und :math:`c(x)` die realisierte Häufigkeit des Wertes :math:`x` darstellt.

        .. hint::
            :class: incremental

            Es wird typischerweise lokal getauscht, um die Ordnung herzustellen.

    .. card::

        .. definition:: MF-Regel

            Ein Array A ist gemäß *move to front* (MF-Regel) sortiert, wenn bei Auftritt eines Wertes :math:`A[k]` in der Folge mit der ersten Position :math:`A[1]` oder :math:`A[0]` vertauscht wird, sollte der Wert noch nicht an der ersten Stelle stehen.

    .. card::

        .. definition:: T-Regel

            Ein Array A ist gemäß *transpose* (T-Regel) sortiert, wenn bei Auftritt eines Wertes :math:`A[k]` in der Folge mit der Position davor :math:`A[k-1]` vertauscht wird, sollte der Wert noch nicht an der ersten Stelle stehen.



Strategien zur Anordnung - Diskussion
--------------------------------------------------------

- Die FC-Regel erfordert das Mitführen der Häufigkeit der Werte. Die MF-Regel und die T-Regel sind einfacher zu implementieren, da sie nur die Reihenfolge der Werte im Array verändern.
- Für MF-Regel und T-Regel gibt es worst-case Aufrufsequenzen, die immer zu den schlechtesten Laufzeiten führen.
- Die MF-Regel nimmt eher starke Änderungen vor und reagiert schnell.
- Die T-Regel nimmt eher schwache Änderungen vor und ist stabiler.

.. summary::
    :class: incremental

    Die Bewertung sollte an Hand der tatsächlichen Daten erfolgen:

    - Liegen Häufigkeitsinformationen vor, so ist die FC-Regel sinnvoll.
    - Die MF-Regel ist für sich ändernde Verteilungen sinnvoller, die T-Regel für stabilere Situationen.


.. class:: exercises transition-flip

Übung
--------

.. exercise::  A = [1,2,3,4,5] selbstanordnend sortieren

    Das Array :python:`A = [1,2,3,4,5]` soll selbstanordnend sortiert werden. Die gesuchten Werte sind: :python:`1,2,3,2,3,2,1,5`. Bestimmen Sie die Anordnung des Arrays nach jedem Zugriff für die Sortierungen nach MF-Regel, T-Regel und FC-Regel. Füllen Sie die nachfolgende Tabelle aus:

    .. csv-table::
        :header: x, MF-Regel, T-Regel, FC-Regel, "Häufigkeiten pro Wert"
        :align: center
        :width: 90%

        1
        2
        3
        2
        3
        2
        1
        5

    .. solution::
        :pwd: das_ist_nicht_so_schwer

        .. csv-table::
            :header: x, MF-Regel, T-Regel, FC-Regel, Häufigkeiten pro Wert
            :align: center
            :width: 90%

            1, "[1,2,3,4,5]", "[1,2,3,4,5]", "[1,2,3,4,5]", "[1,0,0,0,0]"
            2, "[2,1,3,4,5]", "[2,1,3,4,5]", "[1,2,3,4,5]", "[1,1,0,0,0]"
            3, "[3,1,2,4,5]", "[2,3,1,4,5]", "[1,2,3,4,5]", "[1,1,1,0,0]"
            2, "[2,1,3,4,5]", "[2,3,1,4,5]", "[2,1,3,4,5]", "[1,2,1,0,0]"
            3, "[3,1,2,4,5]", "[3,2,1,4,5]", "[2,3,1,4,5]", "[1,2,2,0,0]"
            2, "[2,1,3,4,5]", "[2,3,1,4,5]", "[2,3,1,4,5]", "[1,3,2,0,0]"
            1, "[1,2,3,4,5]", "[2,1,3,4,5]", "[2,3,1,4,5]", "[2,3,2,0,0]"
            5, "[5,2,3,4,1]", "[2,1,3,5,4]", "[2,3,1,5,4]", "[2,3,2,0,1]"



.. class:: exercises

Übung
--------

.. exercise::  A = [1,2,3,4,5,6] selbstanordnend sortieren

    Das Array :python:`A = [1,2,3,4,5,6]` soll selbstanordnend sortiert werden. Danach werden die folgenden Werte in der angegebenen Reihenfolge gesucht: :python:`5,1,6,2,3,6,5`. Bestimmen Sie die Anordnung des Arrays nach jedem Zugriff für die Sortierungen nach MF-Regel, T-Regel und
    FC-Regel. Füllen Sie die nachfolgende Tabelle aus:

    .. csv-table::
        :header: x, MF-Regel, T-Regel, FC-Regel, "Häufigkeiten"
        :align: center
        :width: 90%

        5
        1
        6
        2
        3
        6
        5

    .. solution::
        :pwd: das_ist_noch_immer_nicht_so_schwer

        .. rubric:: Lösung

        .. csv-table::
            :header: x, MF-Regel, T-Regel, FC-Regel, Häufigkeiten
            :align: center
            :width: 90%

            5, "[5,2,3,4,1,6]", "[1,2,3,5,4,6]", "[5,1,2,3,4,6]", "[0,0,0,0,1,0]"
            1, "[1,2,3,4,5,6]", "[1,2,3,5,4,6]", "[5,1,2,3,4,6]", "[1,0,0,0,1,0]"
            6, "[6,2,3,4,5,1]", "[1,2,3,5,6,4]", "[5,1,6,2,3,4]", "[1,0,0,0,1,1]"
            2, "[2,6,3,4,5,1]", "[2,1,3,5,6,4]", "[5,1,6,2,3,4]", "[1,1,0,0,1,1]"
            3, "[3,6,2,4,5,1]", "[2,3,1,5,6,4]", "[5,1,6,2,3,4]", "[1,1,1,0,1,1]"
            6, "[6,3,2,4,5,1]", "[2,3,1,6,5,4]", "[6,5,1,2,3,4]", "[1,1,1,0,1,2]"
            5, "[5,3,2,4,6,1]", "[2,3,1,5,6,4]", "[6,5,1,2,3,4]", "[1,1,1,0,2,2]"



.. class:: new-section transition-move-to-top

Suche nach dem n-ten Element
---------------------------------------------


Suche nach dem n-ten Element - Einführung
---------------------------------------------

.. class:: incremental

- Ist das Array sortiert, so ist die Suche nach dem n-ten Element trivial und hat eine Laufzeit von :math:`O(1)`.

- Ist das Array nicht sortiert, so ist die Suche nach dem n-ten Element nicht trivial.

  Wir unterscheiden:

  .. class:: incremental

  1. wird das Array (im Folgenden) auch noch sortiert gebraucht, so ist es am effizientesten dieses erst zu sortieren, um dann das n-te Element auszulesen. Die Laufzeit beträgt dann - mit der Wahl eines geeigneten Sortierverfahrens - :math:`O(n \log n)`.
  2. Ist eine Sortierung nicht erforderlich/gewünscht, so können wir mit Hilfe von Teile-und-Herrsche-Verfahren das n-te Element auch effizienter bestimmen.



Suche nach dem k-ten Element mittels Quickselect
---------------------------------------------------------------------

.. code:: pascal
    :number-lines:

    // A    ein 0-indiziertes Array
    // k    das k-größte Element (0-indiziert)
    function Quickselect(A,k)
        if length(A) == 1 then return A[0]
        pivot   := A[length(A)-1] // ein bel. Element als Pivot (hier das letzte)
        lows    := []             // Elemente kleiner als Pivot
        highs   := []             // Elemente größer als Pivot
        pivotsCount := 0          // Anzahl der Pivot-Elemente
        for x in A do             // Partitionierung ...
            if x < pivot then lows.append(x)
            else if x > pivot then highs.append(x)
            else pivotsCount := pivotsCount + 1

        if k < length(lows) then
            return Quickselect(lows, k)
        else if k < length(lows) + pivotsCount then
            return pivot          // das k-te Element ist ein Pivot-Element
        else
            return Quickselect(highs, k - length(lows) -  pivotsCount)

.. supplemental::

    .. hint::

        In einer realen Implementierung sollte das Pivot-Element zufällig gewählt werden, um - für den Fall, dass das Array sortiert ist - die Laufzeit zu verbessern.

    .. hint::

        Der Quickselect Algorithmus kann auch *in-place* implementiert werden, d. h. ohne zusätzlichen Speicherbedarf. Dies setzt voraus, das die ursprüngliche Reihenfolge der Elemente nicht erhalten bleiben muss.



Beispielanwendung: Bestimmung des Medians
----------------------------------------------------

.. code:: pascal
    :number-lines:
    :class: copy-to-clipboard

    // A    ein nicht sortiertes, 0-indiziertes Array
    function FindMedian(A)
        n := length(A)
        if n % 2 == 1 then // d. h. wir haben eine ungerade Anzahl von Elementen in A
            return Quickselect(A, floor(n / 2))
        else // gerade Anzahl von Elementen in A
            left    := Quickselect(A, floor(n / 2) - 1)
            right   := Quickselect(A, floor(n / 2))
            return (left + right) / 2.0



.. class:: exercises

Übung
--------

.. exercise:: n-te Element bestimmen

    1. Bestimmen Sie den Median für das Array ``A = [23,335,2,24,566,3,233,54,42,6,667,7,5,7,7]``. Wenden Sie dazu den Algorithmus ``FindMedian`` (inkl. ``Quickselect-Algorithmus``) an.

    2. Geben Sie weiterhin nach jeder Partitionierung im Quickselect Algorithmus den aktuellen Zustand an (d. h. nach Zeile 11 in Quickselect).

       .. csv-table::
            :header: "Array A", "k", "Lows", "Pivot", "Pivots Count", "Highs"
            :align: center

            "[...]", <K>,  "[...]", <P>, "<#P>", "[...]"

    .. solution::
        :pwd: mal_schnell_mal_langsam

        .. csv-table::
            :header: "Array A", "length(A)", "k", "Pivot", "lows", "highs", "pivotsCount"
            :align: center

            "[23, 335, 2, 24, 566, 3, 233, 54, 42, 6, 667, 7, 5, 7, 7]", 15 , 7 , 7 , "[2, 3, 6, 5]", "[23, 335, 24, 566, 233, 54, 42, 667]", 3
            "[23, 335, 24, 566, 233, 54, 42, 667]", 8 , 0 , 667 , "[23, 335, 24, 566, 233, 54, 42]", "[]", 1
            "[23, 335, 24, 566, 233, 54, 42]", 7 , 0 , 42 , "[23, 24]", "[335, 566, 233, 54]", 1
            "[23, 24]", 2 , 0 , 24 , "[23]", "[]", 1

        Median: 23



.. class:: exercises

Übung
--------

.. exercise:: Komplexität von Quickselect

    Bestimmen Sie die Komplexität des Quickselect-Algorithmus im schlechtesten Fall, im Durchschnittsfall und im besten Fall.

    .. solution::
        :pwd: Analyse

        **Schlechtester Fall:**

        Beispiel: die Suche nach dem kleinsten Element in einem (zufällig) aufsteigend sortierten Array, bei dem immer das größte Element als Pivot Element gewählt wird.

        Im schlechtesten Fall ist die Partitionierung somit ineffektiv und wir benötigen ``length(A)`` Aufrufe von Quickselect (d. h. ``length(A)-1`` rekursive Aufrufe). Die Anzahl der Schritte für die Partitionierung nimmt pro rekursivem Aufruf um eins ab (d. h. ``length(A), length(A)-1, ... 2`` Schritte für das Partitionieren).

        Sei :math:`n =` ``length(A)`` die Länge des Arrays, dann haben wir im schlechtesten Fall :math:`n + (n-1) + ... + 2 = \frac{n(n+1)}{2}-1` Schritte.

        Die Komplexität beträgt also :math:`O(n^2)`.


        **Durchschnittsfall:**

        Im Durchschnittsfall ist die Partitionierung effektiv und halbiert das Array bei jeder Durchführung. Die Anzahl der Schritte für die Partitionierung nimmt pro rekursivem Aufruf somit um die Hälfte ab (d. h. ``length(A), length(A)/2, length(A)/4, ... 2`` Schritte für das Partitionieren).

        Sei :math:`n =` ``length(A)`` die Länge des Arrays, dann haben wir im durchschnittlichen Fall :math:`n + \frac{n}{2} + \frac{n}{4} + \frac{n}{8} + \ldots = 2n` Schritte durchzuführen.

        (Anwendung der Summenformel für eine geometrische Reihe: :math:`a = n, r = \frac{1}{2}` für :math:`n \rightarrow \infty` gilt hier: :math:`S = a \cdot \frac{1}{1-r} = n \cdot \frac{1}{1-\frac{1}{2}} = 2n`).

        Die Komplexität beträgt also :math:`O(n)`.

        **Bester Fall:**

        Der Aufwand ist :math:`O(n)` und tritt ein, wenn das Pivot-Element das Median-Element ist. In diesem Fall wird das Array nur einmal durchsucht und partitioniert.

.. supplemental::

    .. rubric:: Komplexitätsanalyse

    Zur Bestimmung der Komplexität kann man entweder das Master Theorem anwenden oder die Anzahl der Schritte für die Partitionierung bestimmen und die Summe der Schritte aufstellen.

    .. rubric:: Geometrische Reihen

    Die Summenformel für eine geometrische Reihe (:math:`S_n = a + ar + ar^2 + \dots + ar^{n-1}`) lautet:

    .. math::

        S_n = a \cdot \frac{1-r^n}{1-r}\quad \text{für}\quad r \neq 1

    Mit:

        :`S_n`:math:: Summe der ersten :math:`n` Glieder der geometrischen Reihe.
        :`a`:math:: Das erste Glied der Reihe.
        :`r`:math:: Der Quotient (Verhältnis aufeinanderfolgender Glieder).
        :`n`:math:: Die Anzahl der Glieder.

    Für :math:`n` gegen unendlich und :math:`|r| < 1` gilt somit:

    .. math::

            S = \frac{a}{1-r} \quad \text{für}\quad |r| < 1
