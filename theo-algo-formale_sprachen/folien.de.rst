.. include:: ../docutils.defs

.. meta::
    :version: renaissance
    :lang: de
    :author: Michael Eichberg
    :keywords: Formale Sprachen, Gödelnummern, Chomsky Hierarchie, Alphabete, Sprachen, Abzählbarkeit
    :description lang=de: Formale Sprachen
    :id: vorlesung-theo-algo-formale_sprachen
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. |SomeText.txt| source:: code/SomeText.txt
    :path: relative
    :prefix: https://delors.github.io/

.. |SomeText.html| source:: code/SomeText.html
    :path: relative
    :prefix: https://delors.github.io/



Formale Sprachen
======================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1.1

.. container:: peripheral

    :Quelle:
        Die Folien sind teilweise inspiriert von oder basierend auf Lehrmaterial von Prof. Dr. Ritterbusch und Theoretische Informatik - kurzgefasst von Prof. Dr. Uwe Schöning.

.. supplemental::

    :Folien:

        |html-source|

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-move-to-top

Einführung
--------------------------------------------------------



Alphabete und Sprachen
--------------------------------------------------------

Formale Sprachen sind ein zentraler Aspekt der theoretischen Informatik.

.. deck::

 .. card::

    - Nutzungsinterface zwischen Computer und Mensch
    - Grundlage für Programmiersprachen

 .. card::

    Es gibt unterschiedliche Klassen und Modelle formaler Sprachen:

    - Erkennbarkeit und Ausdruckskraft
    - Anforderungen an Computermodelle zur Erkennbarkeit
    - Komplexität von Verfahren zur Erkennung



Alphabete
--------------------------------------------------------

.. definition::

    Ein Alphabet :math:`Σ = \{α_1,α_2,...,α_n\}` ist eine endliche Menge von Zeichen / Symbolen.

.. example:: Abzählbare Mengen
    :class: incremental

    - :math:`Σ_{lat} = \{a,b,c,...,z\}`
    - :math:`Σ_{ziffer} = \{0,1,2,3,4,5,6,7,8,9\}`
    - :math:`Σ_{unicode} = \{x |x\; \text{ist ein Unicode-Zeichen}\}`
    - :math:`Σ_{logik} = \{0,1,(,),∧,∨,¬,(,)\}∪ Σ_{lat}`



.. class:: repetition

Kartesisches Produkt
--------------------------------------------------------

.. definition::

    Ein kartesisches Produkt wie :math:`A × B` oder :math:`A^n` für :math:`n ∈ \mathbb{N}` von Mengen oder Alphabeten bezeichnet die Menge der Tupel :math:`(a,b)` oder :math:`(a_1,...,a_n)` von Elementen der Mengen:

    .. math::

        \begin{array}{rccll}
            A × B & := & & & \{(a,b) | a ∈ A, b ∈ B\} \\
            A^n & := & \underbrace{A × ... × A}_{n\; \text{Faktoren}} & = & \{(a_1,...,a_n) |a_1,...,a_n ∈ A\}
        \end{array}

.. example::
    :class: incremental

    - :math:`Σ_{lat} × Σ_{lat} = \{(a,a),(a,b),...,(z,z)\}`

    .. class:: incremental

    - :math:`Σ_{lat}^3 = \{(a,a,a),(a,a,b),...,(z,z,z)\}`



Kleene-Abschluss
--------------------------------------------------------

.. deck::

    .. card::

        .. definition::

            Ein Wort :math:`ω` ist ein endliches — ggf. leeres — Tupel :math:`(w_1,w_2,...,w_n) ∈ Σ^n` von Zeichen :math:`w_k ∈ Σ` eines Alphabets mit Länge :math:`|ω| = n` der Anzahl der Zeichen.

            .. deck::

                .. card::

                    - Wörter werden meist ohne Klammern geschrieben; d. h. :math:`ω = w_1w_2...w_n`.
                    - Das leere Wort (das Wort ohne Zeichen) wird mit :math:`ε` bezeichnet.
                    - Besondere Wortmengen:

                    .. class:: incremental

                        - :math:`Σ^0 = \{ε\}`
                        - :math:`Σ^* = \bigcup_{n=0}^∞ Σ^n`
                        - :math:`Σ^+ = \bigcup_{n=1}^∞ Σ^n`

                .. card::

                        Die Operationen :math:`M^∗` und :math:`M^+` auf einer Menge :math:`M` werden als

                        - Kleene-:math:`*`-Abschluss oder
                        - Kleene-:math:`+`-Abschluss bezeichnet.

    .. card::

        .. example::

            - :math:`Σ_{lat}^* = \{ε,a,b,...,z,aa,ab,...,zz,aaa,...\}`
            - :math:`Σ_{lat}^+ = \{a,b,...,z,aa,ab,...,zz,aaa,...\}`

    .. card::

        .. example::

            Sei :math:`M = \{01, 2\}`, so ergeben sich u.a. diese Wortmengen:

            .. math::
                :class: s-font-size-90

                \begin{array}{lcl}
                    M^0 & = & \{ε\} \\
                    M^1 & = & \{01,2\} \\
                    M^2 & = & \{0101,012,201,22\} \\
                    M^3 & = & \{010101,01012,01201,0122,20101,2012,2201,222\} \\
                    & \ldots & \\
                    M^+ & = & M^1 ∪ M^2 ∪ \ldots = \{01,2,0101,012,201,22,010101,01012,...\} \\
                    M^* & = & M^0 ∪ M^+ = \{ε,01,2,0101,012,201,22,010101,01012,...\}
                \end{array}

            .. observation::

                Die Wortlänge :math:`|ω|` für ein :math:`ω ∈ M^*` hängt von der Definition des Alphabets ab. So ist in diesem Beispiel :math:`|222| = 3` während :math:`|0101| = 2` ist.



Produkt und Konkatenation
--------------------------------------------------------

.. definition::

    Die Konkatenation von zwei Wörtern :math:`ω = (ω_{1},...,ω_{n})` und :math:`𝜐 = (𝜐_{1},...,𝜐_{m})` ist definiert als das Wort, das durch ein aneinanderreihen der beiden Wörter entsteht:

    .. math::

        ω \cdot 𝜐 =  ω𝜐 = (ω_1,...,ω_n)\cdot (𝜐_{1},...,𝜐_{m}) = w_1...w_n𝜐_{1}...𝜐_{m}

    Das leere Wort ist :math:`ω^0 = ε` und die n-te Potenz von :math:`ω` ist:

    .. math::

        ω^n = \underbrace{ω \cdot ... \cdot ω}_{n\; \text{Faktoren}}\; \text{für}\; n > 0

.. example::
    :class: incremental

    Sei :math:`Σ = \{a,e,n,r\}`, sowie :math:`ω = \text{na} ∈Σ^∗` und :math:`𝜐 = \text{er} ∈ Σ^∗`.

    :math:`ω^2 = \text{nana}`, :math:`𝜐ω = \text{erna}` und :math:`𝜐ω^2𝜐 = \text{ernanaer}`



Abschluss-Eigenschaften
--------------------------------------------------------

.. deck::

    .. card::

        .. remark::

            Der Begriff *Abschluss in obiger Definition* bedeutet:

            Auf einer Menge mit einer Verknüpfung liefert jede Anwendung der Operation mit Elementen wieder ein Element aus der Menge.


        .. example::
            :class: incremental

            - die Subtraktion ist auf den natürlichen Zahlen nicht abgeschlossen,

            .. class:: incremental

            - der Abschluss der natürlichen Zahlen bezüglich der Subtraktion sind die ganzen Zahlen.

    .. card::

        Die Kleene-Abschlüsse und Multiplikationen werden später in regulären Ausdrücken auf Wörtern verwendet, damit ist dann der Abschluss oder das kartesische Produkt der Menge mit genau diesem Wort gemeint.

        .. example::

            .. math::

                \begin{array}{rclcl}
                    (ab)^+ & = & \{ab\}^+ & = & \{ab, abab, ababab, ...\} \\
                    cd^*e  & = & \{c\}×\{d\}^*×\{e\} & = & \{ce, cde, cdde, cddde, ...\}
                \end{array}



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Alphabet Σ = {a,el,en,g,l,ste}

    Gegeben sei das Alphabet :math:`Σ = \{a,el,en,g,l,ste\}`. Welche der folgenden Worte liegen in :math:`Σ^4`?

    :math:`ω_1` = galgen, :math:`ω_2` = stelle, :math:`ω_3` = sagen, :math:`ω_4` = lagen, :math:`ω_5` = allen, :math:`ω_6` = aalen

    .. solution::
        :pwd: galgen tut weh

        .. rubric:: Lösung

        .. math::

            ω_1, ω_2, ω_3 \notin Σ^4 \\
            ω_4, ω_5, ω_6 \in Σ^4

.. exercise:: Alphabet Σ = {e,en,in,r,t,u}

    Gegeben sei das Alphabet :math:`Σ = \{e,en,in,r,t,u\}`. Welche der folgenden Worte liegen in :math:`Σ^5`?

    :math:`ω_1` = reiner, :math:`ω_2` = teurer, :math:`ω_3` = treuer, :math:`ω_4` = teuren, :math:`ω_5` = retten, :math:`ω_6` = teuer

    .. solution::
        :pwd: reiner ist teurer

        .. rubric:: Lösung

        .. math::

            ω_2, ω_3 \notin Σ^5 \\
            ω_1, ω_4, ω_5, ω_6 \in Σ^5



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Alphabet Σ = {e,g,in,l,s,ter}

    Gegeben sei das Alphabet :math:`Σ = \{e,g,in,l,s,ter\}`. Welche der folgenden Worte liegen in :math:`Σ^*`?

    :math:`ω_1` = tester, :math:`ω_2` = seile, :math:`ω_3` = lines, :math:`ω_4` = segel, :math:`ω_5` = seinen, :math:`ω_6` = erster

    .. solution::
        :pwd: erster am seile

        .. rubric:: Lösung

        .. math::

            ω_1, ω_2, ω_5, ω_6 \notin Σ^* \\
            ω_3, ω_4 \in Σ^*



Formale Sprachen
--------------------------------------------------------

.. definition::

    Jede Teilmenge :math:`L ⊆ Σ^*` ist eine formale Sprache über dem Alphabet :math:`Σ`.

.. example::

    Sei :math:`Σ = \{0,1,2\}`, dann ist :math:`Σ^*` die Menge oder Sprache von Wörtern aus den Ziffern :math:`0`, :math:`1` oder :math:`2` beliebiger Länge wie :math:`101` oder auch :math:`0001`.

    .. deck:: numbered incremental

        .. card::

            Die Menge :math:`M_3 ⊂ Σ^*` der binären Zahlen ohne führende Nullen:

            :math:`M_3 = \{0\}∪\{1\}×\{0,1\}^* = \{0,1,10,11,100,101,110,111,1000,...\}`

        .. card::

            Die Menge :math:`M_2 ⊂ Σ^*` von einer gleichen Anzahl von 0 und 1 in dieser Reihenfolge:

            :math:`M_2 = \{0^n1^n | n ∈ \mathbb{N}\} = \{01,0011,000111,00001111,0000011111,...\}`

        .. card::

            Die Wörter :math:`M_1 ⊂ Σ^*` mit gleicher Anzahl von 0, 1 und 2 in dieser Reihenfolge:

            :math:`M_1 = \{0^n1^n2^n |n ∈ \mathbb{N}\}= \{012,001122,000111222,000011112222,...\}`

        .. card::

            Die Menge :math:`M_0 ⊂ Σ^*` mit Wörtern der Länge von Zweierpotenzen:

            :math:`M_0 = \{w ∈Σ^*| |w| = 2^n, n ∈ \mathbb{N}_0\}= \{0,1,2,00,01,\ldots,21,22,0000,...\}`



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Wörter bestimmen

    Bestimmen Sie die Wörter der folgenden Sprache:

    :math:`L= \{acx^m (zq)^n | n ∈ \{0,1\}, m ∈ \{1,2\}\}`

    .. solution::
        :pwd: nicht ganz so schlimm

        .. rubric:: Lösung

        .. math::

            L= \{acxxzq,acxx,acx,acxzq\}


.. exercise:: Wörter bestimmen

    Bestimmen Sie die Wörter der folgenden Sprache:

    :math:`L = \{ (b^ma)^lza | m ∈\{0,1\}, l ∈ \{1,2,3\}\}`

    .. solution::
        :pwd: wird schon

        .. rubric:: Lösung

        .. math::

            L= \{aza,bababaza,aaza,aaaza,baza,babaza\}



.. class:: new-section transition-move-to-top

Abzählbarkeit und Gödelnummern
--------------------------------------------------------



Abzählbar (unendlich)
--------------------------------------------------------

.. deck::

    .. card::

        .. observation::

            Selbst mit endlichen Alphabeten können formale Sprachen unendlich groß sein.

        .. definition::

            Eine Menge M ist *abzählbar*, wenn die einzelnen Elemente abzählbar sind, es also eine bijektive Funktion :math:`f : N →M` von den natürlichen Zahlen :math:`N = \mathbb{N}` oder einer Teilmenge der natürlichen Zahlen :math:`N ⊂ \mathbb{N}` auf :math:`M` gibt.

            Wenn es keine solche Funktion geben kann, so ist die Menge *überabzählbar unendlich*.

    .. card::

        .. theorem::

            Jede endliche Menge ist abzählbar.

        .. proof::

            Eine endliche Menge :math:`M` hat eine endliche Anzahl :math:`n = |M|` von Elementen.

            .. presenter-note::

                :math:`|M|` ist die Kardinalität der Menge :math:`M` oder auch die Anzahl der Elemente in :math:`M`.

            Wird nun beginnend von :math:`M_0 = M` und :math:`k= 1` in :math:`n` Schritten jeweils ein Element :math:`m_k` der Menge :math:`M_{k-1}` entnommen mit :math:`M_k = M_{k-1}\setminus\{m_k\}`, so ist induktiv :math:`|M_k| = |M_{k-1}| - 1 = n-k` und es ist :math:`M_n = ∅`.

            .. presenter-note::

                :Induktiv: wir schließen vom Spezifischem auf das Allgemeine.

                :math:`M_{k-1}` ist die Menge, die noch *ein Element mehr enthält* als :math:`M_k`. D. h. die Kardinalität der Menge :math:`M_l` mit :math:`l > k` ist kleiner als die Kardinalität von :math:`M_k`.

            Die Bijektion lautet dann :math:`f : N → M` mit :math:`f(k) = m_k` mit :math:`N = \{1,...,n\}`.

    .. card::

        .. theorem::

            Jede Teilmenge :math:`M ⊆ N` einer abzählbaren Menge :math:`N= \{n_1,n_2,...\}` ist abzählbar.

        .. proof::

            Sei :math:`f(k) = n_k` die Abzählung der Menge :math:`N`. Sei :math:`R = \{k ∈ \mathbb{N} | n_k ∈ M \}`; d. h. die Menge der Indizes der Elemente aus :math:`N`, die in :math:`M` sind. Dann ist die Einschränkung :math:`f_{|R} : R → M` von :math:`f` genau die Abzählung, die die Abzählbarkeit von :math:`M` beweist.

            .. presenter-note::

                :math:`f_{|R}` ist die Einschränkung von :math:`f`, die nur auf der Teilmenge :math:`R` des Definitionsbereichs von f betrachtet wird.

    .. card::

        .. example::

            Abzählbar unendliche Mengen sind — zum Beispiel:

            .. class:: incremental

            - die geraden Zahlen :math:`\{2n |n ∈\mathbb{N}\}`
            - die Quadratzahlen :math:`\{n^2 |n ∈\mathbb{N}\}`
            - die Menge der Fakultäten :math:`\{n! |n ∈\mathbb{N}\}`
            - die ganzen Zahlen :math:`\mathbb{Z}` mit der Funktion:

              .. math::

                    f(n) = \left\{ \begin{array}{ll}
                        n/2 & \text{für}\; n\; \text{gerade} \\
                        -(n-1)/2 & \text{für}\; n\; \text{ungerade}
                    \end{array} \right.

              .. container:: peripheral s-font-size-90

                    :math:`f(1) = 0,\; f(2) = 1,\; f(3) = -1,\; f(4) = 2,\; f(5) = -2,\; ...`

    .. card::

        .. example::


            Die rationalen Zahlen :math:`\mathbb{Q}` sind abzählbar unendlich.

            .. image:: images/cantor.svg
                :alt: Diagonalisierungsverfahren von Cantor
                :align: center

            .. presenter-note::

                Auf der Diagonalen ist die Summe aus Zähler und Nenner immer konstant und  jede Diagonale zählt alle Zerlegungen dieser Konstanten auf.

            .. supplemental::

                Rationale Zahlen können als Brüche dargestellt werden und mit Hilfe des Diagonalisierungsverfahren von Cantor (auch: Cantors erstes Diagonalargument) in eine Bijektion zu den natürlichen Zahlen gebracht werden.

                Die 0 und alle negativen Brüche können wie zuvor eingeschoben werden. Auch alle rationalen Vektoren :math:`\mathbb{Q}^n` in beliebiger Dimension :math:`n ∈ \mathbb{N}` sind so abzählbar.

    .. card::

        .. theorem::

            Für jede endliche Menge oder Alphabet :math:`Σ` ist deren Kleene-Abschluss :math:`Σ^*` abzählbar.

        .. proof::

            Ist das Alphabet :math:`Σ` leer, so ist auch :math:`Σ^*` leer, und damit für :math:`N = ∅` trivial abzählbar.

            Ist :math:`Σ` nicht leer, dann besitzt :math:`Σ` mit Größe :math:`n = |Σ|` eine Aufzählung :math:`m_k` mit :math:`k= 1,...,n`.

            .. deck::

                .. card::

                    Jedes Wort :math:`w = m_{k_1} m_{k_2}...m_{k_l}` kann dann im Stellenwertsystem zur Basis :math:`n + 1` dargestellt werden:

                    .. math::

                        1 + k_1·(n + 1)^{l-1} + k_2(n + 1)^{l-2} +...+ k_l(n + 1)^0

                    und somit der Zahl :math:`1 + (k_1k_2...k_l)_{(n+1)}`\ [#]_ zugeordnet werden.

                    .. [#] Die Darstellung :math:`(k_1k_2...k_l)_{(n+1)}` ist die Stellenwertdarstellung zur Basis :math:`n + 1` des Wortes :math:`w`.

                .. card::

                    .. example::

                        .. image:: images/kleene-abschluss-abz.svg
                            :alt: Kleene-Abschluss abzählbar
                            :align: center

                .. card::

                    Die Abbildung :math:`f : N →Σ^*` mit :math:`N ⊆\mathbb{N}` ergibt sich für :math:`f(x)` aus der Stellenwertdarstellung von :math:`x- 1 >0` zur Basis :math:`n + 1` beginnend mit der höchstwertigen Ziffer :math:`k_1` bis zur letzten Stelle :math:`k_l`.

                    .. supplemental::

                        .. repetition::

                            Umrechnung einer Dezimalzahl in eine Zahl zur Basis ``n``, erfolgt durch Division mit Rest durch ``n`` und die Reihenfolge der Reste ist dann die Stellenwertdarstellung, beginnend mit dem letzten Rest. D. h. der erste Rest ist die letzte Ziffer der Stellenwertdarstellung.

                            .. example::

                                Umrechnung von 5 = 5\ :sub:`10` zur Basis 5:

                                1. 5 / 5 =  1 Rest 0 (letzte Ziffer/niederwertigste Stelle)
                                2. 1 / 5 =  0 Rest 1 (erste Ziffer/höchstwertige Stelle)

                                Die Stellenwertdarstellung ist dann 10\ :sub:`5`.

                                Gegenprobe: 1 · 5\ :sup:`1` + 0 · 5\ :sup:`0` = 5.

                            .. example::

                                Umrechnung von 7 = 7\ :sub:`10` zur Basis 3:

                                1. 7 / 3 = 2 Rest 1
                                2. 2 / 3 = 0 Rest 2

                                Die Stellenwertdarstellung ist dann 21\ :sub:`3`.

                                Gegenprobe: 2 · 3\ :sup:`1` + 1 · 3\ :sup:`0` = 7

                        .. hint::

                            Wenn an einer Zahl keine spezifische Basis angegeben ist, oder aus dem Kontext unmittelbar eine andere Basis anzunehmen ist (z. B. 2 oder 16), so ist die Basis 10 anzunehmen. D. h. die Dezimaldarstellung ist die Standarddarstellung und 34 wäre zum Beispiel Äquivalent zu 34\ :sub:`10`.

                    Das Bild :math:`f(x)` ist dann das Wort :math:`m_{k_1} m_{k_2}...m_{k_l}`.

                    Das leere Wort :math:`ε` wird von :math:`1` abgebildet und entsprechend ist :math:`f(1) = ε`.

    .. card::

        .. example::

            Sei :math:`Σ = \{e,i,rn,st\}` mit Aufzählung :math:`m_1 = e`, :math:`m_2 = i`, :math:`m_3 = rn`, und :math:`m_4 = st`, dann haben die folgenden Wörter diese Abzählung nach Stellenwert:

            .. deck::

                .. card::

                    .. list-table::
                        :class: s-font-size-70 align-content-in-data-cells-top
                        :stub-columns: 1
                        :header-rows: 1
                        :width: 100%
                        :widths: 10 24 24 24 24 24

                        * - :math:`x`
                          - :math:`1`
                          - :math:`2`
                          - :math:`3`
                          - :math:`4`
                          - :math:`5`
                        * -
                          - .. math::

                                1
                          - .. math::

                                \begin{array}{lcl}
                                    1 & + & 1\\
                                    1_5 & + & 1_5
                                \end{array}

                            [#]_
                          - .. math::

                                \begin{array}{lcl}
                                    1 & + & 2\\
                                    1_5 & + & 2_5
                                \end{array}
                          - .. math::

                                \begin{array}{lcl}
                                    1 & + & 3\\
                                    1_5 & + & 3_5
                                \end{array}
                          - .. math::

                                \begin{array}{lcl}
                                    1 & + & 4\\
                                    1_5 & + & 4_5
                                \end{array}
                        * - Wort
                          - :math:`\epsilon`
                          - e
                          - i
                          - rn
                          - st
                        * - :math:`f(x)`
                          - :math:`f(1) = \epsilon`
                          - :math:`f(2) = e`
                          - :math:`f(3) = i`
                          - :math:`f(4) = rn`
                          - :math:`f(5) = st`

                            *(Anm.: k ist 4 für st)*

                    .. [#] Wir haben immer :math:`1 + ...`, da wir noch das leere Wort :math:`ε` haben.

                .. card::

                    .. list-table::
                        :class: s-font-size-70
                        :stub-columns: 1
                        :header-rows: 1
                        :width: 100%
                        :widths: 10 10 30 30 10 30 10

                        * - :math:`x`
                          - ...
                          - :math:`7 = 1 + 6`

                            :math:`12_5 = 1_5 + 11_5`
                          - :math:`8 = 1 + 7`

                            :math:`13_5 = 1_5 + 12_5`
                          - ...
                          - :math:`45 = 1 + 44`

                            :math:`140_5 = 1_5 + 134_5`
                          - ...
                        * -
                          - ...
                          - .. math::

                                \begin{array}{lcl}
                                    1 & + & 1 \cdot 5 + 1\\
                                    1 & + & 11_5
                                \end{array}
                          - .. math::


                                \begin{array}{lcl}
                                    1 & + & 1\cdot 5 + 2\\
                                    1 & + & 12_5
                                \end{array}
                          - ...
                          - .. math::

                                \begin{array}{lcl}
                                    1 & + & 1 \cdot 25 + 3 \cdot 5 + 4\\
                                    1 & + & 134_5
                                \end{array}
                          - ...
                        * - Wort
                          - ...
                          - :math:`ee`
                          - :math:`ei`
                          - ...
                          - :math:`e\, rn\, st`
                          - ...

            .. container:: incremental

                Unbesetzt bleibt, wo eine 0 in der Stellenwertdarstellung vorliegt. Zum Beispiel ist :math:`f(6) = 1 + 1 \cdot 5^1 + 0 \cdot 5^0 = 1_5 + 10_5`.

    .. card::

        .. theorem::

            Jede formale Sprache is abzählbar.

        .. proof::

            Da jede formale Sprache :math:`L` über einem endlichen Alphabet :math:`Σ` definiert ist, ist das eine direkte Folge aus vorherigem Satz, dass :math:`Σ^*` abzählbar ist, und wie zuvor gezeigt damit auch die Teilmenge :math:`L ⊆ Σ^*` abzählbar ist.

    .. card::

        .. rubric:: Abzählen mit Hilfe von Gödelnummern

        .. supplemental::

            Gödelnummern unterstützen abzählbare un-/endliche Mengen. Letzteres (abzählbar unendlich) ist mit einem einfachen Stellenwertsystem zur Basis der Anzahl der Elemente und des somit (zwangsweise) unendlichen Alphabets nicht möglich.

        .. deck::

            .. card::

                .. definition::

                    Sei (:math:`p_n`) die Folge der Primzahlen:

                    :math:`p_1 = 2, p_2 = 3, p_3 = 5, p_4 = 7, p_5 = 11, p_6 = 13, ...`

                    Für eine abzählbare Menge :math:`M= {m_1,m_2,...}` ist die Gödelnummer :math:`c_M : M^* → \mathbb{N}` des Tupels :math:`w = (m_{k_1} ,m_{k_2} ,...,m_{k_l} )` gegeben durch

                    .. math::

                        c_M (w) = p^{k_1}_1 · p^{k_2}_2 · ... · p^{k_l}_l = \prod_{i=1}^{l} p^{k_i}_i

                    .. supplemental::

                        .. repetition::

                            *Fundamentalsatz der Arithmetik*: Jede natürliche Zahl :math:`n > 1` kann eindeutig als ein Produkt von Primzahlen geschrieben werden, wobei die Reihenfolge der Primfaktoren ignoriert wird. D. h. die Gödelnummer :math:`c_M(w)` ist eineindeutig für jedes Wort :math:`w ∈ M^*`.

                        .. remark::

                            Die Primfaktorzerlegung einer Zahl ``x`` kann wie folgt erfolgen, wenn wir die Liste der Primzahlen ``p = [2,3,5,7,11,13,17,...]`` haben:

                            .. code:: python
                                :number-lines:
                                :class: copy-to-clipboard

                                # Um die Primzahlen zu erzeugen, kann z. B. das Sieb des Eratosthenes
                                # verwendet werden. Die Bestimmung der Primzahlen ist hier
                                # jedoch nicht Gegenstand.
                                p = [2, 3, 5, 7, 11, 13, 17, 19]

                                def primfaktorzerlegung(x,i = 0):
                                    c = 0 # Häufigkeit des Primfaktors
                                    while x % p[i] == 0:
                                        c += 1
                                        x = x // p[i]
                                    factor = str(p[i]) + "^" + str(c)
                                    if x == 1:
                                        return factor
                                    else:
                                        return factor + " * " + primfaktorzerlegung(x,i+1)

                            .. example::

                                Primfaktorzerlegung von 10:

                                - X = 10, p\ :sub:`1` = 2

                                    - 10 / 2 = 5 Rest 0 ⇒ **2 ist ein Primfaktor**
                                    - 5 / 2 = 2 Rest 1 ⇒ 2 ist kein weiterer Primfaktor; um die nächsten Primfaktoren zu bestimmen setzen wir X = 5
                                - X = 5, p\ :sub:`2` = 3

                                    - 5 / 3 = 1 Rest 2 ⇒ 3 ist *kein* Primfaktor, da wir einen Rest haben; X hat unverändert den Wert 5
                                - X = 5, p\ :sub:`3` = 5

                                    - 5 / 5 = 1 Rest 0 ⇒ **5 ist ein Primfaktor**

                                10 = 2\ :sup:`1` · 5\ :sup:`1`

                            .. example::

                                Primfaktorzerlegung von 12:

                                - X = 12, p\ :sub:`1` = 2

                                    - 12 / 2 = 6 Rest 0 ⇒ **2 ist ein Primfaktor**
                                    - 6 / 2 = 3 Rest 0 ⇒ **2 ist noch einmal ein Primfaktor**
                                    - 3 / 2 = 1 Rest 1 ⇒ X' = 3

                                - X = 3, p\ :sub:`2` = 3

                                    - 3 / 3 = 1 Rest 0 ⇒ **3 ist ein Primfaktor**

                                12 = 2\ :sup:`2` · 3\ :sup:`1`

            .. card::

                .. example::

                    Sei :math:`Σ = \{e,i,rn,st\}` mit Aufzählung :math:`m_1 = e`, :math:`m_2 = i`, :math:`m_3 = rn` und :math:`m_4 = st`, dann haben die folgenden Wörter diese Gödelnummern:

                    .. list-table::
                        :class: s-font-size-70 align-content-in-data-cells-top
                        :stub-columns: 1
                        :header-rows: 1
                        :width: 100%
                        :widths: 10 24 24 24 24 24 24

                        * - Wort
                          - :math:`\epsilon`
                          - :math:`e`
                          - :math:`i`
                          - :math:`rn`
                          - :math:`st`
                          - :math:`e\, rn\, st`
                        * - :math:`c_M(w)`
                          - :math:`2^0 = 1`
                          - :math:`2^1`

                            :math:`p_1^{k_1=1}`
                          - :math:`2^2`

                            :math:`p_1^{k_2=2}`
                          - :math:`2^3`
                          - :math:`2^4`
                          - :math:`2^1 \cdot 3^3 \cdot 5^4 = 33750`

                            .. presenter-note::

                                :math:`2`, :math:`3` und :math:`5` sind die ersten drei Primzahlen. Die Potenzen ergeben sich aus der Aufzählung der Wörter.

                .. observation::

                        Unbesetzt bleibt, wo bis zum höchsten Primzahlfaktor davor eine Primzahlpotenz 0 ist.

                .. supplemental::

                        \Z. B. ist die Primzahlzerlegung von 10 = 2\ :sup:`1` · 3\ :sup:`0` · 5\ :sup:`1`. Somit gäbe es an der zweiten Stelle *kein Zeichen* was unsinnig ist.

    .. card::

        .. theorem::

            Die Menge von endlichen Folgen :math:`P= \{p = (w_1,w_2,...,w_n) |w_k ∈ L, n ∈ \mathbb{N}\}` aus Wörtern einer formalen Sprache :math:`L ⊆ Σ^*` (also Programmen) über einem Alphabet :math:`Σ` ist abzählbar.

        .. proof::
            :class: incremental

            Jede formale Sprache :math:`L ⊆Σ^*` ist abzählbar. Damit kann nach Definition für jede Folge :math:`p ∈ P` injektiv eine Gödelnummer :math:`c_L(p)` über :math:`L` bestimmt werden.

            .. presenter-note::

                Die Abbildung ist injektiv, da die Gödelnummer für ein Programm eindeutig ist, aber nicht jede Gödelnummer eine Gödelnummer eines Programms sein muss.

            Auf der Menge :math:`N= \{x = c_L(p) |p ∈ P\}` kann die Umkehrung :math:`f : N →P` von :math:`c_L` auf :math:`P` eingeschränkten bijektiven Funktion :math:`c_{L|P} : P →N` bestimmt werden, und damit ist :math:`P` abzählbar.



Überabzählbar unendlich
--------------------------------------------------------

.. theorem::

    Die Menge der reellen Zahlen :math:`r ∈(0,1) ⊂ \mathbb{R}` ist überabzählbar unendlich.

.. proof::

    .. rubric:: Cantor's (zweites) Diagonalargument

    .. presenter-note::

        Die Benennung ist hier nicht eindeutig und der Begriff *Diagonalargument* wird auch für Cantors erstes Diagonalargument verwendet bzw. bei der Verwendung des Begriffs Diagonalisierungsverfahren wird auch Cantors zweites Diagonalargument gemeint.

    Angenommen die reellen Zahlen sind als Binärbrüche wie folgt abzählbar:

    .. math::
        :class: s-font-size-80

        \begin{array}{rcl}
            r_1 & = & 0,x_{11}x_{12}x_{13}x_{14}x_{15}... \\
            r_2 & = & 0,x_{21}x_{22}x_{23}x_{24}x_{25}... \\
            r_3 & = & 0,x_{31}x_{32}x_{33}x_{34}x_{35}... \\
            r_4 & = & 0,x_{41}x_{42}x_{43}x_{44}x_{45}... \\
            \vdots & & \vdots
        \end{array}

    Sei jetzt :math:`r = 0,\overline{x_{11}}\,\overline{x_{22}}\,\overline{x_{33}}\,\overline{x_{44}}\,\overline{x_{55}}... \in (0,1)`, dann ist :math:`r` nicht in der Abzählung und es liegt ein Widerspruch zur Annahme vor. :math:`\mathbb{R}` ist also überabzählbar unendlich.

    .. supplemental::

        :math:`\bar{x}` ist das einfache Komplement von x. Das bedeutet, dass 0 durch 1 und 1 durch 0 ersetzt wird.

        Beachte, dass :math:`r` über die gesamte (unendliche) Diagonale definiert ist und dadurch zu jeder bestehenden Zahl unterschiedlich sein muss; d. h. :math:`r` ist nicht gleich zu :math:`r_1` in der ersten Stelle, nicht gleich zu :math:`r_2` in der zweiten Stelle, nicht gleich zu :math:`r_3` in der dritten Stelle, ... und nicht gleich :math:`r_n` in der n-ten Stelle.

        Die Kardinalität (bereits) der Menge der reellen Zahlen im Bereich :math:`(0,1)` ist also größer als die der natürlichen Zahlen.



Schlussfolgerungen aus der Überabzählbarkeit
--------------------------------------------------------

.. deck:: numbered

    .. card::

        Angenommen:

        - jedes in einer formalen Sprache geschriebenes Programm löst ein Problem
        - wir interpretieren dies als Berechnung einer Lösung

        .. container:: incremental

            So sind dies verschwindend wenige lösbare Probleme verglichen schon mit der Reichhaltigkeit der reellen Zahlen im Intervall :math:`(0,1)`.

        .. conclusion::
            :class: incremental

            Soweit davon auszugehen ist, dass die Teilmenge der in der Realität tatsächlich relevanten reellen Zahlen tatsächlich auch überabzählbar ist, wird es nie möglich sein, für alle Fragestellungen über solche Zahlen Lösungen in der Form von Programmen über einer gegebenen formalen Sprache zu formulieren.


    .. card::

        .. conclusion::

            Gleichzeitig ist aber auch die Anzahl der formalen Sprachen sehr groß.

        .. proof::

            Für jede reelle Zahl :math:`x ∈ R` mit Nachkommastellen :math:`r_1r_2...` gibt es eine formale Sprache :math:`L_x` über :math:`\Sigma_{\text{Zahl}}`:

            :math:`L_x = \{r_1r_2...r_n ∈ Σ^*_{\text{Zahl}} |x \text{ hat die ersten } n \text{ Nachkommastellen } r_1...r_n\}`

            Beispielsweise ist :math:`L_π = \{1,14,141,1415,14159,141592,1415926,...\}`. Damit ist die Anzahl der formalen Sprachen mindestens so groß, wie die Anzahl reeller Zahlen im Intervall :math:`(0,1)`, also aller möglichen Nachkommastellen in :math:`\mathbb{R}`, zuzüglich der 0, und damit nach vorherigem Satz überabzählbar unendlich.



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Stellenwerte I

    Gegeben sei das Alphabet :math:`Σ = \{a,gen,i,re\}` mit Aufzählung in dieser Reihenfolge. Bestimmen Sie die Zahlen :math:`n` nach Stellenwert mit Bild :math:`f (n)` der Wörter :math:`regen`, :math:`aare` und die Worte mit Stellenwert :math:`15`, :math:`118`.


    .. solution::
        :pwd: Im Regen

        .. rubric:: Lösung

        :math:`regen= f (1+ 4·5^1 + 2) = f (23)`, :math:`f (15) = f (1+ 2·5^1 + 4) = genre`, :math:`aare = f (1+ 1·5^2 + 1·5^1 + 4) = f (35)`, :math:`f (118) = f (1+ 4·5^2 + 3·5^1 + 2) = reigen`



.. exercise:: Stellenwerte II

    Gegeben sei das Alphabet :math:`Σ = \{e,h,r,ste\}` mit Aufzählung in dieser Reihenfolge. Bestimmen Sie die Zahlen n nach Stellenwert mit Bild :math:`f (n)` der Wörter :math:`steh`, :math:`rehe` und die Worte mit Stellenwert :math:`45`, :math:`1417`.


    .. solution::
        :pwd: steh steher

        .. rubric:: Lösung

        :math:`steh= f (1+ 4·5^1 + 2) = f (23)`, :math:`rehe= f (1+ 3·5^3 + 1·5^2 + 2·5^1 + 1) = f (412)`, :math:`f (45) = f (1+1·5^2+3·5^1+4) = erste`, :math:`f (1417) = f (1+2·5^4+1·5^3+1·5^2+3·5^1+1) = heere`


.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Gödelnummern I

    Gegeben sei das Alphabet :math:`Σ = \{e,l,ste,te\}` mit Aufzählung in dieser Reihenfolge. Bestimmen Sie die Gödelnummer :math:`c(w)` der Wörter :math:`este`, :math:`elle` und die Worte mit Gödelnummer :math:`720`, :math:`12600`.

    .. solution::
        :pwd: tele&stelle

        .. rubric:: Lösung

        :math:`c(este) = 2^1 \cdot 3^3 = 54`

        :math:`c(elle) = 2^1 \cdot 3^2 \cdot 5^2 \cdot 7^1 = 3150`

        :math:`720 = 2^4 \cdot 3^2 \cdot 5^1 = 720 = c(tele)`

        :math:`12600 = 2^3 \cdot 3^2 \cdot 5^2 \cdot 7^1 = c(stelle)`

.. exercise:: Gödelnummern II

    Gegeben sei das Alphabet :math:`Σ = \{h,he,re,ste\}` mit Aufzählung in dieser Reihenfolge. Bestimmen Sie die Gödelnummer :math:`c(w )` der Wörter :math:`steh`, :math:`reste` und die Worte mit Gödelnummer :math:`144`, :math:`1500`.

    .. solution::
        :pwd: hehre_stehe

        .. rubric:: Lösung

        :math:`c(steh) = 2^4 · 3^1 = 48`

        :math:`c(reste) = 2^3 · 3^4 = 648`

        :math:`144 = 2^4 · 3^2 = c(stehe)`

        :math:`1500 = 2^2 · 3^1 · 5^3  = c(hehre)`


.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Gödelnummern und ChatGPT

    Eine Befragung von ChatGPT zum Thema Gödelnummern ergab, dass ChatGPT vorgeschlagen hat allen Zeichen :math:`a \in \Sigma` eine Primzahl zuzuordnen und dann für das Vorkommen eines Zeichens :math:`a` an Stelle :math:`i` den aktuellen Wert mit der Primzahl des Zeichens hoch :math:`i` zu multiplizieren.

    .. example::

        Sei :math:`\Sigma = \{a,b,c,d\}`

        Zuweisung von Primzahlen an Symbole: :math:`a \rightarrow 2`, :math:`b \rightarrow 3`, :math:`c \rightarrow 5`, :math:`d \rightarrow 7`

        Für das Wort: :math:`abac` wäre nach dem von ChatGPT vorgeschlagenen Verfahren die Gödelnummer :math:`c(abac) = 2^1 \cdot 3^2 \cdot 2^3 \cdot 5^4 = 90\,000`.

    Bewerten Sie diesen Vorschlag.

    .. solution::
        :pwd: Bloedsinn

        .. rubric:: Lösung

        Die Primzahlzerlegung von :math:`90\,000` ist :math:`2^{4} \cdot 3^2 \cdot 5^4`. Dies lässt keinen Rückschluss auf das Wort :math:`abac` zu - wir wüssten zwar, dass die vorkommenden Buchstaben nur :math:`a`, :math:`b` und :math:`c` sind, aber die Positionsinformation ist nicht mehr eindeutig und das Wort :math:`cbca` hätte die gleich Nummer.

        Der Vorschlag ist also nicht sinnvoll, da diese Nummer nicht eindeutig ist und nicht auf das Wort schließen lässt!

        Das hier in der Vorlesung vorgeschlagene Verfahren würde (bei gleichbleibender Zuordnung!) die Berechnung wie folgt durchführen: :math:`c(abac) = 2^2 \cdot 3^3 \cdot 5^2 \cdot 7^5 = 45\,378\,900`.



.. class:: new-section transition-scale

Verknüpfungen und Entscheidbarkeit
--------------------------------------------------------


Verknüpfungen von formalen Sprachen
--------------------------------------------------------

.. deck::

    .. card::

        .. theorem::

            Sind :math:`L_1` und :math:`L_2` zwei formale Sprachen über den Alphabeten :math:`Σ_1` und :math:`Σ_2`, so gilt:

            1. Die Vereinigung :math:`L_∪= L_1 ∪ L_2` ist eine formale Sprache über dem Alphabet :math:`Σ_1 ∪ Σ_2`.
            2. Der Schnitt :math:`L_∩= L_1 ∩ L_2` ist eine formale Sprache über dem Alphabet :math:`Σ_1 ∪ Σ_2`.

               .. container:: peripheral

                    Die Wörter aus :math:`L_∩` sind aber natürlich in :math:`(\Sigma_1 \cap \Sigma_2)^*` enthalten.

        .. proof::
            :class: incremental

            Die Vereinigung der Alphabete :math:`Σ = Σ_1 ∪ Σ_2`, also zweier endlicher Mengen, ist wieder eine endliche Menge und damit ein Alphabet. Da sowohl :math:`Σ_k ⊆Σ` für :math:`k= 1,2`, sind :math:`L_1` und :math:`L_2` auch Sprachen über :math:`Σ` und es gilt :math:`L_k ⊆Σ^*_k ⊆Σ^*`, da die Teilmengenbeziehung in jeder Mengenpotenz und damit auch in deren Vereinigung gilt.
            Damit sind auch :math:`L_1 ∪L_2 ⊆Σ^*` und :math:`L_1 ∩L_2 ⊆Σ^*` und damit Sprachen über :math:`Σ = Σ_1 ∪Σ_2`.

    .. card::

        .. theorem::

            Sind :math:`L_1` und :math:`L_2` zwei formale Sprachen über den Alphabeten :math:`Σ_1` und :math:`Σ_2`, so gilt:


            3. Das Komplement :math:`\overline{L_k} = Σ^∗_k \setminus L_k , k= 1,2` ist formale Sprache über Alphabet :math:`Σ_k`.

               .. presenter-note::

                    .. repetition::

                        Eine formale Sprache besteht in der Regel nicht aus alle möglichen Wörtern, sondern nur aus einer Teilmenge davon.

        .. proof::

            Nach Definition der Mengendifferenz gilt :math:`Σ^*_k \setminus L_k ⊆ Σ^*_k`. Somit ist :math:`\overline{L_k} ⊆ Σ^*_k` und somit eine Sprache über :math:`Σ_k`.


    .. card::

        .. theorem::

            Sind :math:`L_1` und :math:`L_2` zwei formale Sprachen über den Alphabeten :math:`Σ_1` und :math:`Σ_2`, so gilt:

            4. Das Produkt :math:`L_1L_2 = \{w_1w_2 |w_1 ∈L_1,w_2 ∈L_2\}` ist eine formale Sprache über :math:`Σ_1 ∪ Σ_2`.

        .. proof::

            Für :math:`L= L_1 ∪ L_2` ist :math:`L_1 ⊆L` und :math:`L_2 ⊆L`.

            :math:`L` ist somit eine Sprache über :math:`Σ = Σ_1 ∪ Σ_2` nach
            Satz 1. Damit ist ist jedes Wort :math:`w ∈L ⊆Σ^*` in einem :math:`w ∈ Σ^k` für ein bestimmtes :math:`k` enthalten. Ebenso ist damit :math:`w_1w_2 ∈Σ^{k_1} Σ^{k_2} = Σ^{k_1+k_2} ⊆ Σ^*`. Damit ist :math:`LL ⊆Σ^*` und  damit :math:`L_1L_2 ⊆ LL ⊆ Σ^*` Sprache über :math:`Σ`.

            .. presenter-note::

                In diesem Fall bezeichnet :math:`\Sigma^k` die Menge aller Wörter der Länge :math:`k` über dem Alphabet :math:`\Sigma`; Mengentheoretisch betrachtet ist es das :math:`k`-malige kartesische Produkt von :math:`\Sigma`  mit sich selbst.

    .. card::

        .. theorem::

            Sind :math:`L_1` und :math:`L_2` zwei formale Sprachen über den Alphabeten :math:`Σ_1` und :math:`Σ_2`, so gilt:

            5. Kleensche Abschlüsse :math:`L^*_k` und :math:`L^+_k` , :math:`k= 1,2` sind formale Sprachen über :math:`Σ_k`.

               .. presenter-note::

                    Wir führen jetzt die Kleene Abschlüsse über Sprachen durch und nicht über Alphabete.

        .. proof::

            .. observation::

                Zunächst ist :math:`ε ∈ Σ^*_k`, somit reicht es für :math:`L^+_k` zu argumentieren.

            - Jedes Wort :math:`w ∈ L^+_k` ist in :math:`w ∈ L^n_k` für ein bestimmtes :math:`n`.
            - Damit gibt es Teilworte :math:`m_1m_2...m_n = w` mit :math:`m_i ∈L_k`
            - Da :math:`L_k ⊆Σ^*_k` gibt es :math:`p_i` , so dass :math:`m_i ∈Σ^{p_i}_k`
            - Damit ergibt sich, dass :math:`m_1...m_n ∈ Σ^{\Sigma p_i}_k` liegt und damit in :math:`Σ^{\Sigma p_i}_k ⊆ Σ^*_k`
            - Damit dies für alle Worte in :math:`L^+_k` gilt, ist :math:`L^+_k ⊆ Σ^*_k` und damit eine Sprache über :math:`Σ_k`

.. supplemental::

    .. example::

        .. rubric:: Komplement einer Sprache

        **Gegeben**

        :Alphabet: :math:`\Sigma_k = \{a, b\}`
        :Sprache:

            :math:`L_k` Alle Wörter, die mit dem Symbol :math:`a` beginnen.

            .. math::

               L_k = \{a, aa, ab, aaa, aab, \dots\}

        :Komplement der Sprache:

            Das Komplement :math:`\overline{L_k}` enthält alle Wörter aus :math:`\Sigma_k^*`, die *nicht* mit :math:`a` beginnen. Das bedeutet:

            .. math::

                \overline{L_k} = \{\epsilon, b, bb, ba, bba, bbb, \dots\}


Existenz der Abzählbarkeit
--------------------------------------------------------

.. deck::

    .. card::

        .. repetition::

            Sind :math:`L_1` und :math:`L_2` abzählbar, so sind mit entsprechenden Anpassungen auch

            - Vereinigung,
            - Schnitt und
            - Produkt

            abzählbar.

        .. observation::

            Die Abzählbarkeit des Komplements kann nicht so einfach beantwortet werden!

            Dies ist jedoch kein Problem, da jede formale Sprache abzählbar ist und damit auch ihr Komplement.

    .. card::

        .. question::

            Kann mit dem Wissen der Existenz auch die tatsächliche Abzählung angegeben werden?

            .. presenter-note::

                Nein, die Existenz der Abzählbarkeit bedeutet nur, dass es eine Abzählung gibt, aber nicht, dass diese bekannt ist.

                Eine reine Existenzaussage ist leider nicht konstruktiv!

    .. card::

        .. summary::

            Wir unterscheiden deswegen die einfache und nicht konstruktivistische Erkenntnis einer Abzählbarkeit von einer konstruktiven und praktischen Aufzählbarkeit.

    .. card::

        .. definition::

            Eine Menge oder Sprache :math:`M` ist **aufzählbar** oder **rekursiv aufzählbar**, wenn eine surjektive Abbildung :math:`f : N →M` bekannt ist, die nach endlichen Schritten für jedes :math:`n ∈N` die Berechnung von :math:`f(n)` ermöglicht, falls :math:`M ≠ ∅`. Daraus ergibt sich eine Aufzählung von :math:`M` durch die Folge :math:`(f (1),f (2),...)`.

        .. remark::
            :class: incremental

            Die Bedeutung der „Berechenbarkeit“ wird später im Sinne eines „Programms“ erklärt.

        .. supplemental::

            :„aufzählbar“: bezieht sich auf die Existenz der Aufzählung als „berechenbare Funktion“,
            :„rekursiv aufzählbar“: bezieht sich auf die Existenz eines „Programms“, was aber hier äquivalent ist.


Aufzählbarkeit
--------------------------------------------------------

.. theorem::

    Sei :math:`Σ` ein Alphabet, dann ist :math:`Σ^*` aufzählbar.

.. proof::

    Die Konstruktion aus dem früheren Satz zur Abzählbarkeit von :math:`Σ^*` ist schon eine konstruktive Aufzählung von :math:`Σ^*`.

    Die nicht zugeordneten natürlichen Zahlen werden beispielsweise auf das jeweils zuletzt zugeordneten Wort abgebildet.


.. class:: no-title s-center-child-elements

Auf- und Abzählbarkeit
--------------------------------------------------------

.. summary::

    Zwischen den Bezeichnungen „aufzählbar“ zu „abzählbar“ ist der relevante Unterschied in der konstruktiven Kenntnis der Aufzählbarkeit im Gegensatz von der nicht konstruktiven Gewissheit der Abzählbarkeit.

    .. attention::
        :class: incremental

        Es ist aber kein Verfahren bekannt, wie aus einer allgemeinen Aufzählung einer Sprache konstruktiv eine Aufzählung des Komplements abgeleitet werden kann. Das Gleiche gilt bei zwei aufgezählten Sprachen für deren Schnitt.

.. supplemental::

    Die Übertragung der Eigenschaft der Aufzählbarkeit muss mit Angabe eines ausführbaren Algorithmus erfolgen.

    So kann - wie bei der Aufzählung von :math:`\mathbb{Z}` - bei der Vereinigung abwechselnd die eine oder die andere Aufzählung verwendet werden. Die Aufzählung der rationalen Zahlen kann  nach dem vorgestellten Verfahren von Cantor erfolgen. Die gilt ggf. auch für das Produkt.



Entscheidungsproblem
--------------------------------------------------------

.. definition::

    Das *Entscheidungsproblem* bezeichnet die Frage, ob für ein Problem ein ausführbares Verfahren angegeben werden kann, mit dem in endlich vielen Schritten eine Entscheidung für das Problem bestimmt wird.

    .. rubric:: Ein Problem ist ...

    :entscheidbar: wenn ein solches Verfahren existiert
    :nicht-entscheidbar: wenn es ein solches Verfahren nicht geben kann
    :semi-entscheidbar: wenn ein Verfahren existiert, das nach endlich vielen Schritten die Entscheidung für eine Klasse von möglichen Antworten bestimmt


Wortproblem
--------------

.. deck::

    .. card::

        .. container:: peripheral

            (Ein Beispiel für ein Entscheidbarkeitsproblem.)

        .. definition::

            Sei :math:`L` eine Sprache über :math:`Σ` und :math:`w ∈ Σ^*`. Das Wortproblem bezeichnet die Frage, ob :math:`w` Teil der Sprache ist, also entweder :math:`w ∈ L` oder :math:`w \notin L` gilt.

    .. card::

        .. theorem::

            Sind :math:`L` und :math:`\bar{L}` aufzählbare Sprachen über dem Alphabet :math:`Σ`, so ist das Wortproblem :math:`w \stackrel{?}{∈} L` für ein :math:`w ∈Σ^*` entscheidbar.

            Dann werden :math:`L` und :math:`\bar{L}` als *entscheidbare Sprachen* oder *rekursive Sprachen* bezeichnet.

        .. proof::
            :class: incremental

            Es seien :math:`f_L : \mathbb{N} →L` und :math:`f_{\bar{L}} : \mathbb{N} → \bar{L}` die Aufzählungen von :math:`L` und :math:`\bar{L}`.

            Abwechselnd wird aufsteigend — beginnend bei :math:`k = 1` — das Wort :math:`w` mit :math:`f_L(k)` und :math:`f_{\bar{L}}(k)` verglichen. Nach endlicher Anzahl von Schritten ist :math:`f_L(k) = w` , dann ist :math:`w ∈ L`, oder :math:`f_{\bar{L}}(k) = w`, dann ist :math:`w \notin L`.

        .. supplemental::

            Es ist wichtig, dass der Vergleich von :math:`w` abwechselnd mit :math:`L` und :math:`\bar{L}` (aufsteigend) erfolgt, da wir sonst nicht nach einer endlichen Anzahl von Schritten garantiert zu einem Ergebnis kommen.


    .. card::

        Ist :math:`L` aufzählbar, doch :math:`\bar{L}` nicht, so endet das Verfahren, genau dann wenn :math:`w ∈ L` ist. Daher ist Wortproblem aufzählbarer Sprachen semi-entscheidbar.

        .. theorem::

            Jede entscheidbare Sprache ist aufzählbar.

        .. proof::

            Jede formale Sprache :math:`L` basiert auf einem Alphabet :math:`Σ_L`. Damit ist der Abschluss :math:`Σ^* _L` mit :math:`f_{Σ^*}` aufzählbar und :math:`L ⊆Σ^*_L`. Entweder ist die Sprache :math:`L` leer, oder es gibt ein Wort :math:`w_0 ∈ L`.

            Wenn :math:`L` entscheidbar ist, so kann für jedes :math:`n ∈ \mathbb{N}` in endlichen Schritten bestimmt werden, ob :math:`f_{Σ^∗_L}(n) ∈L` ist. Wenn ja, so ist :math:`f_L(n) = f_{Σ^*_L}(n)`, und sonst :math:`f_L(n) = w_0`.

            .. presenter-note::

                Unbesetzte :math:`n` werden durch :math:`f_L(n)` mit :math:`w_0` aufgefüllt.

    .. card::

        Damit gilt:

        rekursive bzw. entscheidbare Sprache ⇒ rekursiv aufzählbare Sprache

        semi-entscheidbare Sprache ⇐ rekursiv aufzählbare Sprache

        .. observation::
            :class: incremental

            Eine rekursive Aufzählung kann die Sprache völlig durcheinander aufzählen.

            .. container:: incremental

                Es ist nie sicher, ob frühe Lücken zur Stellenwertaufzählung später aufgefüllt werden.



Das Collatz-Problem
--------------------------------------------------------

.. deck::

    .. card::

        .. definition::

            Die Collatz-Funktion :math:`f : \mathbb{N} → \mathbb{N}` ist definiert als:

            .. math::

                f(n) = \begin{cases}
                    n/2 & \text{für gerade } n \\
                    3n+1 & \text{für ungerade } n
                \end{cases}

            Das Collatz-Problem besteht darin, für ein gegebenes :math:`n` die Folge :math:`f(n), f(f(n)), f(f(f(n))), ...` zu betrachten und zu entscheiden, ob die Folge irgendwann den Wert 1 erreicht.

        .. example::
            :class: incremental

            .. math::

                f(6) = 3, f(3) = 10, f(10) = 5, f(5) = 16,

                f(16) = 8, f(8) = 4, f(4) = 2, f(2) = 1, ...

            Die Folge erreicht für :math:`n = 6` den Wert :math:`1` nach :math:`8` Schritten.

    .. card::

        .. theorem::

            Das Collatz-Problem ist semi-entscheidbar.

        .. proof::

            Die Collatz-Folge kann für ein gegebenes :math:`n` in endlich vielen Schritten berechnet werden.

            Wenn die Folge den Wert :math:`1` erreicht, so ist das Problem entschieden.

            Wenn die Folge nicht den Wert :math:`1` erreicht, so ist das Problem nicht entschieden, aber es ist auch nicht sicher, ob die Folge den Wert :math:`1` nicht doch noch erreicht.

            .. presenter-note::

                Auch wenn es bedeutende Fortschritte bei der Beantwortung der Frage gab - sowohl theoretische als auch praktische - und Computer den Nachweis für sehr große Zahlen erbringen konnten, ist das Problem noch nicht entschieden.

    .. card::


        Das Collatz-Problem kann direkt in eine Collatz-Sprache über :math:`Σ_{\text{Zahl}}`  übertragen werden:

        .. math::

            L_{\text{Collatz}} = \{n ∈ \mathbb{N} |∃k ∈\mathbb{N}_0 : f^k(n) = 1\}

        .. container:: incremental

            Das Wortproblem auf dieser Sprache ist damit — hier nach Definition des Problems statt einer Aufzählung — ebenso mindestens semi-entscheidbar.

        .. container:: incremental

            Ob das Problem auch entscheidbar ist, konnte bisher niemand beantworten. Die naive Methode des Ausprobierens, ob es überhaupt ein :math:`w ∈ N` mit :math:`w \notin L_{\text{Collatz}}` gibt, hat trotz intensiver Suche bisher nicht geendet.


Das Halteproblem
--------------------------------------------------------

.. definition::

    Das Halteproblem ist die Fragestellung, ob die Ausführung eines Programms :math:`p` bei gegebenen Eingabedaten :math:`x` nach endlichen Schritten terminiert.

    Das Halteproblem ist die verallgemeinerte Fragestellung zum Collatz-Problem. Entsprechend ist die äquivalente Sprache:

    :math:`L_{\text{Halteproblem}} =`

    :math:`\quad \{(p,x) \in \Sigma^*_{\text{Unicode}} \times \Sigma^*_{\text{Unicode}} | p(x) \text{ terminiert nach endlichen Schritten }\}`

    nur semi-entscheidbar, da durch Ausführung des Programms nur :math:`(p,x) \in L_{\text{Halteproblem}}` gezeigt werden kann.

.. remark::
    :class: incremental

    Alan Turing konnte beweisen, dass es keinen Algorithmus gibt, der die Entscheidung :math:`(p,x)\notin L_{\text{Halteproblem}}` für beliebige :math:`p` und :math:`x` in endlicher Zeit beantwortenkann.


.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Collatz-Funktion

    Die parametrisierte Collatz-Funktion :math:`f_{α,β}(n) : \mathbb{N} \rightarrow \mathbb{N}` laute für :math:`α,β ∈\mathbb{N}`:

    .. math::

        f_{α,β}(n) = \begin{cases}
            n/2 & \text{für } n  \text{ gerade } \\
            α \cdot n+ β & \text{sonst}
        \end{cases}

    1. Bestimmen Sie mit einem Programm das kleinste :math:`k ∈ \mathbb{N}` für das :math:`f^k_{3,1}(27) = 1`  ist.
    2. Sei die Sprache :math:`L_{\text{Collatz}_{3,7}} = \{n ∈ \mathbb{N} | ∃k ∈ \mathbb{N}_0 : f^k_{3,7}(n) = 1\}`.

       Bestimmen Sie mit einem Programm die Menge :math:`M = \bar{L}_{3,7} \cap [1,20]`.

    .. solution::
        :pwd: Parametrisierte Collatz-Funktion

        .. include:: code/collatz.py
            :code: python
            :class: copy-to-clipboard
            :number-lines:



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Rekursive Sprachen

    Es seien :math:`L_1` und :math:`L_2` rekursive Sprachen über dem Alphabet :math:`Σ`. Sei :math:`L= L_1 \setminus L_2`.

    Zeigen oder widerlegen Sie, dass :math:`L` eine rekursive Sprache über :math:`Σ` sei.

    .. solution::
        :pwd: RekursivE*Sprachen

        - :math:`L_1` und :math:`L_2` rekursiv bedeutet, dass beide Sprachen entscheidbar sind und sowohl :math:`L_1` und :math:`\bar{L}_1` sowie :math:`L_2` und :math:`\bar{L}_2` auch aufzählbar sind. D.h. es gilt, dass

          - :math:`w ∈L_1` oder :math:`w \notin L_1` bzw.
          - :math:`w ∈L_2` oder :math:`w \notin L_2`

        in endlichen Schritten entschieden werden kann.

        - :math:`L` ist rekursiv, wenn :math:`w ∈L` oder :math:`w  \notin L` in endlichen Schritten entschieden wird.

          Prüfe dazu in endlichen Schritten ob :math:`w \stackrel{?}{∈} L` oder :math:`w \stackrel{?}{\notin} L`

          - Ist :math:`w \in L_1` und :math:`w \notin L_2`, so ist :math:`w ∈ L`.
          - Sonst ist :math:`w \notin L`.

          Diese Entscheidung wurde in endlichen Schritten gefällt, also ist :math:`L` rekursiv.



.. class:: new-section transition-scale

Grammatiken
--------------------------------------------------------



Englische Grammatik (Beispielhaft)
--------------------------------------------------------

.. deck::

    .. card::

        .. math::

            \underbrace{ %
            \underbrace{ %
            \underbrace{The}_{\textstyle \text{ Bestimmungswort }} \underbrace{professor}_{\textstyle \text{ Substantiv }} %
            }_{\textstyle \text{ Substantivphrase }} %
            \underbrace{ %
            \underbrace{should}_{\textstyle \text{ Modalverb }} \underbrace{teach}_{\textstyle \text{ Hauptwort }}
            }_{\textstyle \text{ Verbphrase }} %
            }_{\textstyle \text{Satz}} .

        .. math::
            :class: incremental

            \underbrace{ %
            \underbrace{ %
            \underbrace{One}_{\textstyle \text{ Bestimmungswort }} \underbrace{student}_{\textstyle \text{ Substantiv }} %
            }_{\textstyle \text{ Substantivphrase }} %
            \underbrace{ %
            \underbrace{might}_{\textstyle \text{ Modalverb }} \underbrace{listen}_{\textstyle \text{ Hauptwort }}
            }_{\textstyle \text{ Verbphrase }} %
            }_{\textstyle \text{Satz}} .

    .. card::

        Ein Satz ``S`` wird mit diesen Regeln ``R`` gebildet:

        - Ein Satz besteht aus einer Substantivphrase und einer Verbphrase.
        - Eine Substantivphrase hat ein optionales Bestimmungswort und ein Substantiv.
        - Eine Verbphrase besteht aus optionalem Modalverb und einem Hauptverb.
        - Ein Bestimmungswort ist :eng:`The` oder :eng:`One`.
        - Ein Substantiv ist :eng:`student` oder :eng:`professor`.
        - Ein Modalverb ist :eng:`should` oder :eng:`might`.
        - Ein Hauptverb ist :eng:`listen` oder :eng:`teach`.

        .. container:: incremental dd-margin-left-4em

            Darin wurden diese Variablen ``V`` und Symbole ``T`` verwendet:

            :``V``: \{Satz, Substantivphrase, Verbphrase, Bestimmungswort, Substantiv, Modalverb, Hauptverb\}

            :``T``: \{The, One, student, professor, should, might, listen, teach\}.



Grammatiken
--------------------------------------------------------

.. definition::


    Eine Grammatik ist ein Tupel :math:`G = (V ,T ,R,S)`, wo

    :`V`:math:: das Alphabet der Variablen,
    :`T`:math:: das Alphabet der Terminalen Symbole mit :math:`V ∩T= ∅`,
    :`R={r_1,...,r_n}`:math:: die endliche Menge der Regeln

         :math:`r_k`: :math:`(V ∪T )^*\setminus T ^* → (V ∪ T)^*`

         .. presenter-note::

            Eine Regel muss auf der linken Seiten mindestens ein Nichtterminal enthalten.

    :`S ∈V`:math:: das Startsymbol ist.

.. container:: incremental

    Die Regeln von Grammatiken werden auch Produktionen genannt


Ableitungen
------------------------

.. deck::

    .. card::

        .. definition::

            Sei :math:`G = (V ,T ,R,S)` eine Grammatik. Eine :emph:`Ableitung` ist die Anwendung einer Regel :math:`r ∈R` mit :math:`a ↦ b` auf das Wort :math:`w_1 ∈(V ∪ T )^*` zum Wort :math:`w_2 ∈(V ∪ T )^*`, geschrieben  :math:`w_1 \overset{r}{⇒} w_2`, wenn es :math:`x,y ∈(V ∪ T )^*` gibt, so dass:

            .. presenter-note::

                Aussprachehinweise:

                :`a ↦ b`:math:: Eine :emph:`Ableitung` ist die Anwendung einer Regel r aus R mit *a wird abgeleitet nach b*.
                :`w_1 \overset{r}{\Rightarrow} w_2`:math:: *w1 wird mit der Regel r abgeleitet nach w2*.

            .. math::

                \begin{array}{ccccc}
                    w_1 & = & x & a   & y \\
                    ⇓_r &   &   & ↧_r &   \\
                    w_2 & = & x & b   & y \\
                \end{array}

    .. card::

        .. definition::

            Eine :emph:`transitive Ableitung` :math:`w_1\overset{*}{⇒}w_n` ist die Anwendung keiner oder beliebig vieler Regeln :math:`r ∈R`, um von :math:`w_1` auf :math:`w_n` zu schließen. Die Sprache einer Grammatik :math:`L(G )` ist die Menge aller möglichen Wörter, die durch die Regeln der Grammatik transitiv aus dem Startsymbol :math:`S` abgeleitet werden können:

            .. math::

                L(G ) := \{w ∈T^*|S\overset{*}{⇒}w \}


.. summary::
    :class: incremental

    Ableitungen aus einer Grammatik definieren eine Sprache.



Eine Grammatik für boolsche Ausdrücke
--------------------------------------------------------

Eine Grammatik für boolesche Terme ist :math:`G_{\text{Logik}} = (V ,T ,R,S)` mit

.. deck::

    .. card::

        .. math::

            \begin{array}{rcl}
            V & = & \{\text{Term},\text{Literal},\text{Variable}\} \\
            T & = & Σ_\text{Logik}= \{∨,∧,¬,(,),0,1,a,...,z\} \\
            R & = & \{r_1,r_2,r_3,r_4\}, wo \\
                &   & r_1 : \text{Term} ↦ \text{Literal} |\text{Variable} |¬ \text{Term} |( \text{Term} ) \\
                &   & r_2 : \text{Term} ↦ \text{Term} ∨ \text{Term} |\text{Term} ∧ \text{Term}\\
                &   & r_3 : \text{Literal} ↦ 0|1\\
                &   & r_4 : \text{Variable} ↦ a|... |z\\
            S & = & \text{Term}
            \end{array}

        .. supplemental::

            .. remark::

                :math:`r2 : \text{Term} ↦ \text{Term} ∨ \text{Term} |\text{Term} ∧ \text{Term}` ist zu interpretieren als:

                :math:`\qquad\begin{cases}r2.1 : \text{Term} ↦ \text{Term} ∨ \text{Term}\quad  \\ r2.2 : \text{Term} ↦ \text{Term} ∧ \text{Term}\end{cases}`

    .. card::

        Eine Ableitung des Terms :math:`S \overset{*}{⇒} {\color{red}(a ∧ b) ∨ c} ∈ L(G_\text{Logik})` kann dann so ablaufen:

        .. csv-table::
            :class: incremental-table-rows borderless text-align-center
            :align: center
            :header: "Regel", " ", " ", " ", " ", " "

                        ,   ,  , , S = Term ,
            r2.1        ,   ,   ,   ,  ↧    ,
                        ,   , Term , , ∨ ,  Term
            "r1.4,r1.2",   , ↧ ,    ,      , ↧
                        ,   , (Term) , , ∨ ,  Variable
            "r2.2,r4",   , ↧ ,     ,     , ↧
                        , ( Term , ∧ , Term ), ∨, c
            "r1.2,r1.2", ↧ ,          , ↧ ,
                        , ( Variable , ∧ , Variable ), ∨, c
            "r4,r4",  ↧ ,          , ↧
                         , ( a , ∧ , b ), ∨, c



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Sprache bestimmen: ersw

    Bestimmen Sie die Sprache :math:`L(G)` für :math:`G = (V ,T ,R,S)`:

    .. math::

        \begin{array}{rcl}
        V & = & \{\text{A},\text{B},\text{C}\} \\
        T & = & \{e,r,s,w\} \\
        R & = & \{r_1,r_2,r_3\}, \\
          &   & r_1 : \text{A} ↦ \text{B}w | ws\text{C} \\
          &   & r_2 : \text{B} ↦ \text{C}r \\
          &   & r_3 : \text{C} ↦ e|s \\
        S & = & \text{A}
        \end{array}

    .. solution::
        :pwd: UndEsSprach

            :math:`L(G ) = \{wse,wss,srw,erw\}`



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Sprache bestimmen: kot

    Bestimmen Sie die Sprache :math:`L(G)` für :math:`G = (V ,T ,R,S)`:

    .. math::

        \begin{array}{rcl}
        V & = & \{\text{A},\text{B},\text{C}\} \\
        T & = & \{k,o,t\} \\
        R & = & \{r_1,r_2,r_3,r_4\}, \\
          &   & r_1 : \text{A} ↦ \text{B}t | \text{C}o\\
          &   & r_2 : \text{B} ↦ \text{C}t \\
          &   & r_3 : \text{C} ↦ k|o \\
          &   & r_4 : \text{C}tt ↦ o|ok \\
        S & = & \text{A}
        \end{array}

    .. supplemental::

        Wenn auf der linken Seite einer Regel ein komplexer Ausdruck steht, dann erfolgt die Ersetzung für den Ausdruck als Ganzes.

        D. h. Sei das aktuelle Wort :math:`w = \text{C}tt`, dann wird :math:`w \overset{r_4}{⇒} \text{o|ok}`.

    .. solution::
        :pwd: DaIstWasZuTun

        :math:`L(G ) = \{oo,ott,ko,ok,o,ktt\}`



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Ableitung finden: ewtiewet

    Wie wird das Wort :math:`ewtiewet` aus der Grammatik :math:`G = (V ,T ,R,S)` abgeleitet?

    .. math::

        \begin{array}{rcl}
        V & = & \{\text{P},\text{Q},\text{R},\text{S}\} \\
        T & = & \{e,i,t,w\} \\
        R & = & \{r_1,r_2,r_3,r_4,r_5\}, \\
          &   & r_1 : \text{P} ↦ i | w\text{Q} \\
          &   & r_2 : \text{Q} ↦ et | we | wit \\
          &   & r_3 : \text{R} ↦ \text{Q} wt| tie \text{P} \\
          &   & r_4 : \text{S} ↦ \text{P} e| ew \text{R} | i | w \text{Q} we \\
          &   & r_5 : wtie\text{P} ↦ wtietie \\
        S & = & \text{S}
        \end{array}

    .. solution::
        :pwd: AbleitungGefunden

        :math:`S \overset{r4.2}{⇒} ewR \overset{r3.2}{⇒} ewtieP \overset{r1.2}{⇒} ewtiewQ \overset{r2.1}{⇒} ewtiewet`



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Ableitung finden: etrrtse

    Wie wird das Wort :math:`etrrtse` aus der Grammatik :math:`G = (V ,T ,R,S)` abgeleitet?

    .. math::

        \begin{array}{rcl}
        V & = & \{\text{X},\text{Y},\text{Z}\} \\
        T & = & \{e,r,s,t\} \\
        R & = & \{r_1,r_2,r_3\}, \\
          &   & r_1 : \text{X} ↦ rts \\
          &   & r_2 : \text{Y} ↦ et \text{Z} | re \text{X} \\
          &   & r_3 : \text{Z} ↦ r \text{X} e| srt | tse \\
        S & = & \text{Y}
        \end{array}

    .. solution::
        :pwd: AbleitungGefunden

        :math:`Y \overset{r2.1}{⇒} etZ \overset{r3.1}{⇒} etrXe \overset{r1}{⇒} etrrtse`


Grammatiken für die vorhergehenden Beispiele
--------------------------------------------------------

.. deck::

    .. card::

        :math:`M_3 = \{0\}∪\{1\}×\{0,1\}^* = \{0,1,10,11,100,101,110,111,...\}= L(G )`:

        .. math::
            :class: incremental

            \begin{array}{rcl}
            G & = & (V ,T ,R,S) \\
            V & = & \{\text{Start},\text{A}\} \\
            T & = & \{0,1\} \\
            R & = & \{r_1,r_2\}, \\
              &   & r_1 : \text{Start} ↦ 0 | 1 | 1\text{A}\\
              &   & r_2 : \text{A} ↦ 0 | 1 | 0\text{A} | 1\text{A}\\
            S & = & \text{Start}
            \end{array}

    .. card::

        :math:`M_2 = \{0^n1^n |n ∈ \mathbb{N}\}= \{01,0011,000111,...\}= L(G )`:

        .. math::
            :class: incremental

            \begin{array}{rcl}
            G & = & (V ,T ,R,S) \\
            V & = & \{\text{S}\} \\
            T & = & \{0,1\} \\
            R & = & \{r_1\}, \\
              &   & r_1 : \text{S} ↦ 0\,\text{S}\,1 | 01\\
            S & = & \text{S}
            \end{array}

    .. card::

        :math:`M_1 = \{0^n1^n2^n |n ∈ \mathbb{N}\}= \{012,001122,000111222,...\}= L(G )`:

        .. math::
            :class: incremental

            \begin{array}{rcl}
            G & = & (V ,T ,R,S) \\
            V & = & \{\text{S},\text{B},\text{C}\} \\
            T & = & \{0,1,2\} \\
            R = \{r_1,r_2,r_3,r_4,r_5,r_6\} & , & r_1 : \text{S} ↦ 0\text{SBC} | 0 \text{BC}\\
              &   & r_2 : \text{CB} ↦ \text{BC} \\
              &   & r_3 : 0\text{B} ↦ 01 \\
              &   & r_3 : 1\text{B} ↦ 11 \\
              &   & r_3 : 1\text{C} ↦ 12 \\
              &   & r_3 : 2\text{C} ↦ 22 \\
            S & = & \text{S}
            \end{array}



.. class:: new-section transition-flip

Chomsky-Hierarchie
--------------------------------------------------------


Aufbau der Chomsky-Hierarchie
--------------------------------------------------------


.. definition::

    Unterteilung der formalen Grammatiken :math:`G = (V ,T ,R,S)` in vier Klassen:

    .. deck:: numbered from-zero

        .. card:: dd-margin-left-4em

            :**Typ-0**: In einer allgemeinen Chomsky-Grammatik oder Typ-0 Grammatik sind alle Regeln zugelassen.

                :math:`r_k : (V ∪T )^*\setminus T^* ↦ (V ∪T )^*`

        .. card:: dd-margin-left-4em

            :**Typ-1**: In einer :emph:`kontextsensitiven Grammatik` oder Typ-1 Grammatik müssen die Regeln Prefix und Postfix vor und nach der Ersetzung erhalten, und die Länge des Wortes erhalten oder wachsen lassen, also

                :math:`r_k : u A v ↦ u w v` mit :math:`u,v ∈(V ∪T )^*`, :math:`A ∈V` und :math:`w ∈(V ∪T )^+`.

                .. container:: peripheral

                    Einmalig ist die Regel :math:`S ↦ε` erlaubt, dann darf aber :math:`S` auf keiner rechten Seite einer anderen Regel auftreten.

        .. card:: dd-margin-left-4em

            :**Typ-2**: In einer :emph:`kontextfreien Grammatik` oder Typ-2 Grammatik dürfen Regeln links nur aus einer Variablen bestehen, also

                :math:`rk : A ↦ w` mit :math:`A ∈V` und :math:`w ∈(V ∪T )^+`.

                .. container:: peripheral

                    Einmalig ist die Regel :math:`S ↦ ε` erlaubt, dann darf aber :math:`S` auf keiner rechten Seite einer anderen Regel auftreten.

        .. card:: dd-margin-left-4em

            :**Typ-3**: In einer :emph:`regulären Grammatik` oder Typ-3 Grammatik dürfen Regeln links nur aus einer Variablen bestehen, und auf der rechten Seite aus einem terminalen Symbol und optional einer Variable, die bei allen Regeln nur links für *links-lineare Grammatiken* oder nur rechts für *rechts-lineare Grammatiken* stehen darf:

                :math:`rk : A ↦aB` (rechts-linear) oder :math:`A ↦Ba` (links-linear) oder :math:`A ↦a` mit :math:`A,B ∈V` , :math:`a ∈T`.

                .. container:: peripheral

                    Einmalig ist die Regel :math:`S ↦ ε` erlaubt, dann darf aber :math:`S` auf keiner rechten Seite einer anderen Regel auftreten.



Chomsky-Typ einer Sprache
--------------------------------------------------------

.. observation::

    Regeln von Grammatiken mit höherem Typ erfüllen immer auch „tiefere“ Bedingungen.

Eine relevante Frage ist: Welches ist der höchste Grammatik-Typ einer erzeugten Sprache?


.. definition::
    :class: incremental

    Eine formale Sprache :math:`L` ist von einem bestimmten *Chomsky-Typ* und entsprechend kontextsensitiv, kontextfrei oder regulär, wenn es eine Grammatik G gibt, die die Sprache :math:`L= L(G )` erzeugt.

.. summary::
    :class: incremental

    Da Sprachen höheren Typs auch die Kriterien tieferen Typs erfüllen, sind somit reguläre Sprachen auch kontextfrei, sowie kontextfreie Sprachen auch kontextsensitiv.



Einordnung von Grammatiken in die Chomsky-Hierarchie
--------------------------------------------------------

.. deck::

    .. card::

        .. question::

            Welchen Typ hat die folgende Grammatik :math:`G =(V ,T ,R,S)`?

            .. math::

                \begin{array}{rcl}
                V & = & \{\text{Start},\text{A}\} \\
                T & = & \{0,1\} \\
                R & = & \{r_1,r_2\}, \\
                &   & r_1 : \text{Start} ↦ 0 | 1 | 1\text{A}\\
                &   & r_2 : \text{A} ↦ 0 | 1 | 0\text{A} | 1\text{A}\\
                S & = & \text{Start}
                \end{array}

            .. presenter-note::

                Es ist eine reguläre Grammatik/Typ 3 Grammatik.

    .. card::

        .. question::

            Welchen Typ hat die folgende Grammatik :math:`G =(V ,T ,R,S)`?

            .. math::

                \begin{array}{rcl}
                V & = & \{\text{S}\} \\
                T & = & \{0,1\} \\
                R & = & \{r_1\}, \\
                &   & r_1 : \text{S} ↦ 0\,\text{S}\,1 | 01\\
                S & = & \text{S}
                \end{array}

            .. presenter-note::

                Es ist eine kontextfreie Grammatik/Typ 2 Grammatik.

    .. card::

        .. question::

            Welchen Typ hat die folgende Grammatik :math:`G =(V ,T ,R,S)`?

            .. math::

                \begin{array}{rcl}
                V  =  \{\text{S},\text{B},\text{C}\}\quad,\quad S =  \text{S}&,& T  =  \{0,1,2\} \\
                R = \{r_1,r_2,r_3,r_4,r_5,r_6\} & , & r_1 : \text{S} ↦ 0\text{SBC} | 0 \text{BC}\\
                &   & r_2 : \text{CB} ↦ \text{BC} \\
                &   & r_3 : 0\text{B} ↦ 01 \\
                &   & r_3 : 1\text{B} ↦ 11 \\
                &   & r_3 : 1\text{C} ↦ 12 \\
                &   & r_3 : 2\text{C} ↦ 22 \\
                \end{array}

            .. presenter-note::

                Es ist eine allgemeine Grammatik/Typ 0 Grammatik aufgrund der Regel
                :math:`r_2 : \text{CB} ↦ \text{BC}`, die nicht in einer kontextsensitiven Grammatik erlaubt ist.

                :math:`CB \rightarrow BC`  ist keine kontextsensitive Regel, da sie mehrere Symbole gleichzeitig ersetzt, ohne einen expliziten Kontext zu definieren; d. h. es wird formal keine Umgebung ( :math:`u` oder :math:`v` ) spezifiziert, die für die Anwendung der Regel erforderlich wäre.

            .. container:: incremental

                Können wir die Grammatik umformulieren, damit dies eine Type 1 Grammatik wird?

    .. card::

            Umformulierung einer allgemeinen Regel zur Vertauschung von zwei Variablen in kontextsensitive Regeln (der Kontext ist hierbei nicht explizit definiert kann aber natürlich ergänzt werden):

            Gegeben sei die Regel :math:`r_2 : \text{CB} ↦ \text{BC}`.

            Umformulierung in kontextsensitive Regeln:

            .. math::

                \begin{array}{rcl}
                    r_{2'.1} & : & CB ↦ CX \\
                    r_{2'.2} & : & CX ↦ YX \\
                    r_{2'.3} & : & YX ↦ YC \\
                    r_{2'.4} & : & YC ↦ BC \\
                \end{array}

            .. container:: incremental

                In jeder Regel wird nur eine Variable ersetzt!

    .. card::

        .. question::

            Welchen Typ hat die folgende Grammatik :math:`G =(V ,T ,R,S)`?

            .. math::

                \begin{array}{rcl}
                    V  =  \{Start, o, >, <, \#, *\}  & , & T = \{0, 1, 2\} \quad , \quad S  =  Start \\
                    R = \{r_1, r_2, r_3, r_4, r_5, r_6, r_7\} & , &
                    \begin{array}{rrcl}
                        r_1: &  Start & ↦ & \#<o\# \\
                        r_2: & \#< & ↦ & \#> |* \\
                        r_3: & >o & ↦ & oo> \\
                        r_4: &  >\# & ↦ & <\# \\
                        r_5: & o< & ↦ & <o \\
                        r_6: & *o & ↦ & 0*\,|\,1*\,|\,2* \\
                        r_7: & *\# & ↦ & ε \\
                    \end{array} \\
                \end{array}

            .. supplemental::

                Die Grammatik erzeugt die Sprache:

                .. math::

                    \begin{array}{rcl}
                        M_0  & = & \{w ∈ Σ^* | |w| = 2^n , n ∈ \mathbb{N}\}\\
                        & = & \{0, 1, 2, 00, 01, ... , 21, 22, 0000, 0001, ...\} \\
                        & = & L(G )
                    \end{array}

            .. presenter-note::

                Es handelt sich um einen Typ 0 Grammatik.



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Chomsky-Typ: ikos

    Bestimmen Sie den Chomsky-Typ der Grammatik :math:`G = (V ,T ,R,S)` und geben Sie eine Ableitung für das Wort :math:`okoik` an.

    .. math::

        \begin{array}{rcl}
            V&= & \{X,Y,Z\} \\
            T&= & \{i,k,o,s\} \\
            R = \{r_1,r_2,r_3,r_4,r_5\} & , & r_1 : X ↦ io |isk |ok \\
            & & r_2 : Xo ↦ ikso |ko |okio |oso \\
            & & r_3 : Y ↦ Xoik |k |o |s \\
            & & r_4 : Z ↦ oY \\
            & & r_5 : oXo ↦ oko |osioo \\
            S & = & Z
        \end{array}

    .. solution::
        :pwd: r4r31r22

        Die Grammatik ist vom Chomsky-Typ 1. Ableitung: :math:`Z \Rightarrow oY \Rightarrow oXoik \Rightarrow okoik`



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Chomsky-Typ: ru

    Bestimmen Sie den Chomsky-Typ von :math:`G = (V ,T ,R,S)` und die Sprache :math:`L(G)`:

    .. math::

        \begin{array}{rcl}
            V & = & \{A,B,C\} \\
            T & = & \{r,u\} \\
            R & = & \{r_1,r_2,r_3,r_4,r_5\} \\
            & & r_1 : A ↦ uB \\
            & & r_2 : B ↦ r \\
            & & r_3 : Bir ↦ ru |u |ur \\
            & & r_4 : C ↦ AiB |r |rB |u \\
            & & r_5 : riB ↦ u \\
            S & = & C
        \end{array}

    .. solution::
        :pwd: uur_rr-u...

        Die Grammatik ist vom Chomsky-Typ 0. Die Sprache :math:`L(G)` ist :math:`\{uur,rr,urir,u,r,uu,uru\}`



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Chomsky-Typ: iosu

    Bestimmen Sie den Chomsky-Typ von :math:`G = (V ,T ,R,S)` und die Sprache :math:`L(G)`:

    .. math::

        \begin{array}{rcl}
            V & = & \{A,B,C,D\} \\
            T & = & \{i,o,s,u\} \\
            R & = & \{r_1,r_2,r_3,r_4\} \\
            & & r_1 : A ↦ Co| o \\
            & & r_2 : B ↦ iCu | iDu | uA \\
            & & r_3 : C ↦ is \\
            & & r_4 : D ↦ usoA \\
            S & = & B
        \end{array}

    .. solution::
        :pwd: uur_rr-u...

        Die Grammatik ist vom Chomsky-Typ 2. Die Sprache :math:`L(G)` ist :math:`\{uiso,uo,iisu,iusoisou,iusoou\}`



.. class:: new-section transition-move-left

Typ-0 und Typ-1 Grammatiken
--------------------------------------------------------


Allgemeine Chomsky Typ-0 Grammatiken
--------------------------------------------------------


Zur Erinnerung: Entscheidbare Sprachen sind aufzählbar.

.. theorem::

    Die Sprache einer allgemeinen, also Typ-0, Grammatik ist (rekursiv) aufzählbar.

.. deck::

    .. card::

        .. proof::

            Sei :math:`r`  die Anzahl Regeln, :math:`m` die maximale Verlängerung durch dir Anwendung einer Regel und :math:`k` die Anzahl Ableitungen.

            Die k-te Anwendung einer Regel :math:`ϱ ≤ r` an Stelle :math:`µ ≤ 1+ (k-1)·m` wird kodiert als:

            :math:`ν_k = ϱ + µ·(r + 1)`

            .. supplemental::

                Durch die Konstruktion von :math:`ν_k` wird sichergestellt, dass jede Ableitung eindeutig kodiert ist. Aus :math:`ν_k` lässt sich die angewandte Regel und die Stelle der Anwendung durch einfache  Division durch :math:`r+1` ablesen. Der ganzzahlige Anteil ist die Position und der Rest die angewandte Regel.

                - ϱ ist der griechische Buchstabe Rho,
                - µ ist der griechische Buchstabe My,
                - ν ist der griechische Buchstabe Ny.

            Die Gödelnummer eines Wortes nach :math:`s` Ableitungen ist mit (:math:`p_k`) Primzahlfolge:

            :math:`n = \prod_{k=1}^{s} p_k^{ν_k}`

    .. card::

            .. example::

                .. class:: columns

                - Gegeben sei :math:`G = (V ,T ,R,S)`:

                  .. math::

                    \begin{array}{rcl}
                        V & = & \{T\} \\
                        T & = & \{0,1,+\} \\
                        R & = & \{r_1,r_2,r_3\} \\
                        & & r_1 : T ↦ T + T \\
                        & & r_2 : T ↦ 0 \\
                        & & r_3 : T ↦ 1 \\
                        S & = & T
                    \end{array}
                - Ableitung von :math:`0 + 0`:

                  1. :math:`T \overset{r_1, \mu = 1}{⇒} T + T \overset{r_2, \mu = 1}{⇒} 0 + T \overset{r_2, \mu = 3}{⇒} 0 + 0`

                     :math:`n = 2^{ν_1} \cdot 3^{ν_2} \cdot 5^{ν_3}`

                     :math:`ν_1 = 1 + 1 \cdot 4 = 5`

                     :math:`ν_2 = 2 + 1 \cdot 4 = 6`

                     :math:`ν_3 = 2 + 3 \cdot 4 = 14\quad (\frac{14}{4} = 3 \text{ Rest } 2)`

                  .. class:: incremental margin-top-1em

                  2. :math:`T \overset{r_1, \mu = 1}{↦} T + T \overset{r_2, \mu = 3}{↦} T + 0 \overset{r_2, \mu = 1}{↦} 0 + 0`



Chomsky Typ-0 Grammatiken - Schlussfolgerungen und Beobachtungen
-----------------------------------------------------------------

- Ist eine formale Sprache rekursiv aufzählbar, so wird sich daraus auch eine Typ-0 Grammatik erzeugen lassen.

.. class:: incremental

- (Aber) nicht jede Typ-0 Grammatik ist entscheidbar (d.h. rekursiv)!

    - Für eine Typ-0 Sprache des Halteproblems ist nur das positive entscheidbar.
    - Eine Endlosschleife endet - per Definition - nie...

- Es muss auch sehr viele formale Sprachen geben, die nicht Typ-0 sind:

  - Typ-0 Sprachen sind durch Turingmaschinen erzeugbar, also aufzählbar.
  - Die Menge der formalen Sprachen ist überabzählbar...





Chomsky Typ-1 - kontextsensitive Grammatiken
--------------------------------------------------------

.. theorem::

    Die Sprache einer kontextsensitiven, also Typ-1, Grammatik ist entscheidbar.

.. deck::

    .. card::

        .. proof::

            Erzeugte Wörter aus Produktionen sind in der Länge monoton wachsend!

            Sei :math:`G = (V ,T ,R,S)` und :math:`w ∈T^*` mit :math:`n = |w|` und :math:`M` Produkte, die auf Worte der Länge :math:`n` abgebildet werden können:

            :math:`M= \{(V ∪T )^m |0 <m ≤n\}` ist durch :math:`|M| = \sum^n_{m=1}(|V |+ |T |)^m`  beschränkt! Nach spätestens :math:`|M|` Ableitungen sind alle möglichen Quellen, bzw. maximal :math:`n ·|M|` Stellen für Ableitungen, durchsucht.

            Damit ist bei einer Suche unter allen Worten bis Länge :math:`n` nach endlicher Suche durch und kann :math:`w ∈L(G )` oder :math:`w \notin L(G )` entschieden werden.

    .. card::

        .. attention::

            Die Umkehrung gilt nicht: Nicht jede entscheidbare Sprache ist kontextsensitiv! Es kann eine entscheidbare Typ-0 Sprache konstruiert werden.

        .. container:: incremental

            Sind entscheidbare Sprache damit eine gute Wahl für Programmiersprachen?

            - Entscheidbarkeit sagt nichts über die Komplexität der Entscheidung aus.
            - Der Aufwand zur Analyse von Typ-1 Sprachen ist bereits sehr hoch.
            - Trotzdem haben viele Programmiersprachen Anteile, die kontextsensitiv sind:

              - der wesentliche Teil (insbesondere die Syntaxanalyse) ist jedoch kontextfrei
              - Sonderfälle (zum Beispiel Typprüfungen) werden gesondert verarbeitet


Die Sprachhiarchie und die Chomsky-Typen
--------------------------------------------------------

.. theorem::

    Seien

    - :math:`L` die Menge der formalen Sprachen,
    - :math:`L_k` die Menge der Sprachen vom Chomsky-Typ :math:`k`,
    - :math:`L_\text{aufzählbar}` die Menge der aufzählbaren formalen Sprachen und
    - :math:`L_\text{entscheidbar}` die Menge der entscheidbaren formalen Sprachen,

    dann gilt:

    :math:`\underbrace{L_3}_{\text{\small regulär}} ⊂ \underbrace{L_2}_{\text{\small kontextfrei}} ⊂ \underbrace{L_1}_{\text{\small kontextsensitiv}} ⊂ \underbrace{L_\text{\small entscheidbar}}_{\text{\small rekursiv}} ⊂ \underbrace{L_0 = L_\text{\small aufzählbar}}_{\text{\small allg. Chomsky-Grammatik}} ⊂ \underbrace{L}_{\text{\small formale Sprache}}`



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Aufzählung einer Sprache

    .. class:: columns

    - Gegeben sei :math:`G = (V ,T ,R,S)`:

      .. math::

            \begin{array}{rcl}
                V & = & \{T\} \\
                T & = & \{0,1,\cdot,(,)\} \\
                R & = & \{r_1,r_2,r_3\} \\
                & & r_1 : T ↦ 1 \\
                & & r_2 : T ↦ ( T ) \\
                & & r_3 : T ↦ T \cdot T \\
                S & = & T
            \end{array}
    - 1. Gegeben Sei folgende Ableitung:

         :math:`T ↦ T \cdot T ↦ ( T ) \cdot T ↦ ( 1 ) \cdot T`

         Bestimmen Sie die Gödelnummer.

      2. Bestimmen Sie die Ableitung/das Wort für die Gödelnummer :math:`n=37\,968\,750\,000\,000`.

    .. solution::
        :pwd: und1und2und3

        .. rubric:: 1. Lösung

        :math:`T \overset{r_3,\mu=1}{↦} T \cdot T \overset{r_2,\mu=1}{↦} ( T ) \cdot T \overset{r_1,\mu=2}{↦} ( 1 ) \cdot T`

        Daraus ergibt sich:

        :math:`n = 2^{ν_1} \cdot 3^{ν_2} \cdot 5^{ν_3}`

        :math:`ν_1 = 3 + 1 \cdot 4 = 7`

        :math:`ν_2 = 2 + 1 \cdot 4 = 6`

        :math:`ν_3 = 1 + 2 \cdot 4 = 9`

        :math:`\Rightarrow n =  182\,250\,000\,000`.

        .. rubric:: 2. Lösung

        :math:`n = 37\,968\,750\,000\,000 = 2^7 \cdot 3^5 \cdot 5^{13}`.

        :math:`n = 2^{ν_1} \cdot 3^{ν_2}\cdot 5^{ν_3}`

        :math:`ν_1 = 3 + 1 \cdot 4 = 7\qquad (7/4 = 1 \text{ Rest } 3)`

        :math:`ν_2 = 1 + 1 \cdot 4 = 5\qquad (5/4 = 1 \text{ Rest } 1)`

        :math:`ν_3 = 1 + 3 \cdot 4 = 5\qquad (13/4 = 3 \text{ Rest } 1)`

        :math:`T \overset{r_3,\mu=1}{↦} T \cdot T \overset{r_1,\mu=1}{↦} 1 \cdot T \overset{r_1,\mu=3}{↦} 1 \cdot 1`




.. class:: new-section transition-move-to-top

Grammatiken kontextfreier Sprachen
--------------------------------------------------------


Chomsky Typ-2: Kontextfreie Grammatiken
--------------------------------------------------------

Grammatiken für die wichtige Klasse der kontextfreien Sprachen sind nicht eindeutig:

Zwei Grammatiken für Terme wie :math:`{\color{red}1+ 2 * 3} ∈ L(G1) = L(G2)`:

.. story::

    .. class:: columns evenly-spaced

    - .. math::

            \begin{array}{rcl}
                G_1 & = & (V_1 ,T_1 ,R_1, S_1) \\
                V_1 & = & \{Term\} \\
                T_1 & = & \{0,1,...,9,+,*\} \\
                R_1 & = & \{r_1,r_2,r_3\} \\
                & & r_1 : Term ↦ Term + Term \\
                & & r_2 : Term ↦ Term * Term \\
                & & r_3 : Term ↦ 0|1|...|9 \\
                S_1 & = & T
            \end{array}

      .. math::
        :class: incremental

        \underbrace{%
            \underbrace{\underbrace{1}_{Term}+\underbrace{2}_{Term}}_{Term} * \underbrace{3}_{Term}}_{Term}
    - .. math::

            \begin{array}{rcl}
                G_2 & = & (V_2 ,T_2, R_2, S_2) \\
                V_2 & = & \{Sum, Prod\} \\
                T_2 & = & \{0,1,...,9,+,*\} \\
                R_2 & = & \{r_1,r_2,r_3,r_4\} \\
                & & r_1 : Sum ↦ Sum + Prod \\
                & & r_2 : Sum ↦ Prod \\
                & & r_3 : Prod ↦ Prod * Prod \\
                & & r_4 : Prod ↦ 0|1|...|9  \\
                S_2 & = & Sum
            \end{array}

      .. math::
        :class: incremental

        \underbrace{%
            \underbrace{\underbrace{1}_{Prod}}_{Sum}+\underbrace{\underbrace{2}_{Prod} * \underbrace{3}_{Prod}}_{Prod}}_{Sum}

    .. question::
        :class: incremental

        In welcher Weise unterscheiden sich die beiden Grammatiken?

        Bedenken Sie insbesondere die Rechenregeln für die Auswertung von Termen.

        .. presenter-note::

            Rechenregel :math:`G_2` bildet die Rechenregel Punkt-vor-Strich ab, während :math:`G_1` die Rechenregel links nach rechts abbildet.



Formate zur Beschreibung kontextfreier Grammatiken
--------------------------------------------------------

.. deck::

    .. card::

        .. rubric:: (E)BNF (Klassisch)

        \(a) Backus-Naur-Form (BNF) bzw. (b) erweiterte Backus-Naur-Form (EBNF), die die BNF um praktische Elemente erweitert.

        .. example::

            :math:`G_2` in (E)BNF:

            ::

                <sum>  ::= <sum> "+" <prod> | <prod>
                <prod> ::= <prod> "*" <prod> | "0" | "1" | ... | "8" | "9"

    .. card::

        .. rubric:: PEG (Modern)

        Parsing Expression Grammar (PEG)

        .. example::

            :math:`G_2` in PEG:

            ::

                start: sum
                sum:   sum "+" prod | prod
                prod:  prod "*" prod | "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7"  | "8" | "9"

        .. container:: incremental

            Eine PEG definiert eine Reihenfolge zur Auflösung des Syntaxbaums. D. h. ein Umstellen der Regeln führt zu einer anderen Sprache. D. h. würde die Regel ``sum: sum "+" prod | prod`` in ``sum: prod | sum "+" prod`` geändert, würde sich die Sprache ändern bzw. manche Ausdrücke nicht mehr erkannt werden.



Domänenspezifische Sprachen
--------------------------------------------------------

- Nur selten werden erfolgreich neue allgemeine Programmiersprachen entwickelt.
- Häufig(er) werden Domänenspezifische Sprachen (DSLs) entwickelt:

.. deck:: padding-left-1em

    .. card::

        .. class:: incremental

        - DSLs sind oft kontextfrei oder regulär.
        - DSLs befähigen Personen mit Domänenwissen, Programme in Ihrer Sprache zu entwickeln.
        - DSLs sind oft einfacher zu verstehen und zu verwenden als allgemeine Programmiersprachen.
        - DSLs haben oft große Einschränkungen sind dafür aber verständlicher
        - DSLs können oft einfacher optimiert werden, da sie weniger allgemein sind

    .. card:: dd-margin-left-2em

        Wir unterscheiden externe und interne DSLs.

        .. class:: incremental

        :Externer DSLs:
            Externe DSLs sind eigenständige Sprachen unabhängig von anderen Sprachen.

            - Zahlreiche Beispiele: SQL, Reguläre Ausdrücke, CSS, ...
            - Volle Kontrolle über Grammatik und Mächtigkeit
            - (Sehr viel) mehr Entwicklungsaufwand

        .. class:: incremental

        :Interne DSLs:
            Interne DSLs sind in einer anderen Sprache eingebettet und nutzen deren Syntax.

            - Prominents Beispiel: JSON
            - Es gibt Programmiersprachen, die gut (z. B. Scala) und solche die schlecht (z. B. Java) für die Entwicklung von internen DSLs geeignet sind.



Entwicklung von DSLs mit LARK
--------------------------------------------------------

.. deck::

    .. card::


        Lark ist ein Python-Parser-Generator für kontextfreie Grammatiken.

        - LARK basiert auf EBNF
        - LARK unterstützt das Erstellen von Parse-Trees basierend auf der Grammatik.

    .. card::




        .. example::

            .. class:: columns left-aligned

            - .. rubric:: "+" Ausdrücke

              .. code:: python
                :number-lines:
                :class: copy-to-clipboard

                from lark import Lark

                GRAMMAR = """
                    s: term
                    term: term "+" term -> add
                        | NUMBER -> no

                    %import common.NUMBER
                    """

                l = Lark(GRAMMAR, start="s")
                print(l.parse("1+2"))
            - .. rubric:: Resultierender Parse Tree

              ::

                Tree(
                 Token("RULE","start"),
                 [
                  Tree(
                   "add",
                   [
                    Tree("no",[Token("NUMBER","1")]),
                    Tree("no",[Token("NUMBER","2")]),
                   ],
                  )
                 ],
                )



.. class:: new-section transition-move-left

Grammatiken regulärer Sprachen
--------------------------------------------------------


Chomsky Typ-3: Reguläre Grammatiken
--------------------------------------------------------

Ein regulärer Ausdruck ist eine effiziente Darstellung von Sprachen.

.. deck::

    .. card::

        .. definition::

            Die Menge :math:`\mathcal{R}(Σ)` umfasst alle regulären Ausdrücke über einem Alphabet :math:`Σ`. Es sind :math:`∅,ε ∈ \mathcal{R}(Σ)` und :math:`Σ ⊂ \mathcal{R}(Σ)`. Sind :math:`α,β ∈R(Σ)`, so sind auch :math:`αβ`, :math:`(α|β)`, :math:`(α)^* ∈ \mathcal{R}(Σ)`.

            Die Sprache :math:`L(α)` eines regulären Ausdrucks :math:`α ∈ \mathcal{R}(Σ)` sei definiert durch :math:`L(∅) = ∅`, :math:`L(ε) = {ε}` und :math:`L(a) = {a}` für ein :math:`a ∈ Σ`, sowie

            - :math:`L(αβ) = L(α)L(β)`,
            - :math:`L(α|β) = L(α) ∪L(β)`,
            - :math:`L(α^*) = (L(α))^*`

            für reguläre Ausdrücke :math:`α,β ∈R(Σ)`.

        .. presenter-note::

            L ist immer die Menge der Worte der Sprache L. Hier definieren wir die Menge der Worte, die durch einen regulären Ausdruck beschrieben werden basierend auf den Zeichen des Alphabets, dass für die Beschreibung des regulären Ausdrucks verwendet wird.


    .. card::

        .. example::

            Auf dem Alphabet :math:`Σ = \{a,b\}` ist :math:`α= a(ab)^*(a|bb) ∈ R(Σ)` ein regulärer Ausdruck, der diese Sprache beschreibt:

            :math:`L(α) = \{aa, abb, aaba, aabbb, aababa, aababbb, aabababa, aabababbb,...\}`


    .. card::

        .. theorem::

            Die Menge der Sprachen regulärer Ausdrücke beschreibt genau die Menge der regulären Sprachen auf einem Alphabet.

        ..
            add proof

    .. card::

        .. example::

            Gegeben sei:

            :math:`M_3 = \{0\}∪\{1\}×\{0,1\}^* = \{0,1,10,11,100,101,110,111,1000,...\}`

            mit Grammatik:

            .. math::

                \begin{array}{rcl}
                G & = & (\{\text{Start},\text{A}\} , \{0,1\} ,R,Start) \\
                R & = & \{r_1,r_2\}, \\
                &   & r_1 : \text{Start} ↦ 0 | 1 | 1\text{A}\\
                &   & r_2 : \text{A} ↦ 0 | 1 | 0\text{A} | 1\text{A}\\
                \end{array}

            .. container:: incremental

                Der regulären Ausdruck :math:`α = 0|1(0|1)^*` beschreibt die Sprache.

            .. container:: incremental

                Die Darstellung ist aber nicht eindeutig: :math:`\beta = 0|1|1(0|1)^*` ist eine äquivalenter Ausdruck.

    .. card::

        .. theorem::

            Alle endlichen formalen Sprachen sind reguläre Sprachen.

        .. proof::

            Eine endliche formale Sprache :math:`L` besteht aus endlich vielen Worten :math:`L= \{w_1,...,w_n\}`. Diese Sprache wird auch durch den regulären Ausdruck :math:`w_1 |... |w_n` erzeugt und daher ist die Sprache regulär.

        .. presenter-note::

            Dies ist in der Praxis nicht sonderlich relevant.



Grammatiken regulärer Sprachen
--------------------------------------------------------

.. deck::

    .. card::

        .. rubric:: Abfolgen und Alternativen

        .. csv-table::
            :class: borderless
            :align: center
            :widths: 30 70
            :width: 50%

            XY, X nach Y
            X|Y, X oder Y
            "\(X\)", Klammerung und Abfrage

    .. card::

        .. rubric:: Zeichen

        .. csv-table::
            :class: borderless
            :align: center
            :widths: 30 70
            :width: 50%

            x, Das Zeichen x
            "\\.", Der Punkt .
            "\\t", Tabulator
            "\ \\\\\\\", Backslash \\


    .. card::

        .. rubric:: Positionen

        .. csv-table::
            :class: borderless
            :align: center
            :widths: 30 70
            :width: 50%

            ˆ, Anfang der Zeile
            $, Ende der Zeile
            \\b, Wortgrenze
            \\B, Nicht-Wortgrenze

    .. card::

        .. rubric:: Mengen

        .. csv-table::
            :class: borderless
            :align: center
            :widths: 30 70
            :width: 50%

            ., Beliebiges Zeichen
            [abc], Ein Zeichen aus Liste
            [ˆabc], Ein Zeichen außer aus Liste
            [a−r], Ein Zeichen aus Bereich
            \\d, Eine Ziffer [0−9]
            \\D, Keine Ziffer [ˆ0−9]
            \\w, Wortzeichen [a−zA−Z0−9_]

    .. card::

        .. rubric:: Anzahl

        .. csv-table::
            :class: borderless
            :align: center
            :widths: 30 70
            :width: 50%

            X?, einmal oder kein mal
            X*, kein mal oder beliebig oft
            X+, einmal oder beliebig oft
            X{n}, exakt n mal
            "X{n,}", mindestens n mal
            "X{n,m}", von n bis m mal
            X*?, mit ? nicht gierig


    .. card::

        -  :eng:`Regular Expressions` werden in fast allen Programmiersprachen und IDEs zur Textanalyse und Transformation verwendet.
        - Es gibt leicht unterschiedliche Syntaxvarianten.
        - Fast alle Implementierungen bieten Erweiterungen, die über die klassischen regulären Sprachen hinausgehen. (z. B. Lookahead, Lookbehind, Charakterklassen...)



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Bestimmung eines regulären Ausdrucks

    Bestimmen Sie einen möglichst kurzen regulären Ausdruck :math:`α ∈ \mathcal{R}(T)`, für den
    :math:`L(α) = L(G)` für die Grammatik :math:`G = (V ,T ,R,S)` gilt:

    .. math::

        \begin{array}{rcl}
        V & = & \{A,B,C\} \\
        T & = & \{o,r,s\} \\
        R & = & \{r_1,r_2,r_3\} \\
        & & r_1 : A → rC |sB \\
        & & r_2 : B → rC |oB \\
        & & r_3 : C → o |s \\
        S & = & A
        \end{array}

    .. solution::
        :pwd: RegExps

        Die Grammatik erzeugt die Sprache ``L(G) = {ro,rs,sro,srs,soro,sors,sooro,soors,...}``, wobei die ``oo`` in den letzten beiden Worten mit :math:`r_{2,2}` beliebig durch ``o`` verlängert werden.

        Ein äquivalenter regulärer Ausdruck ist damit: :math:`α = ro|rs|s(ro|rs|oo^*r(o|s))` bzw. :math:`α = r(o|s)|so*r(o|s)`.



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Rechts-lineare Grammatiken

    Bestimmen Sie eine rechts-lineare Typ-3 Grammatik :math:`G = (V ,T ,R,S)` für das Alphabet :math:`T = (e,r,s,t)`, für die :math:`L(G) = L(α)` mit :math:`α = r((s|t)^*|e)^*` gilt.

    .. solution::
        :pwd: RegExpsRechtsLinear

        .. rubric:: Lösung: Rechts-lineare Grammatik

        Die rechts-lineare Grammatik ist:

        .. math::

            \begin{array}{rcl}
                V & = & \{A,B\} \\
                T & = & \{e,r,s,t\} \\
                R & = & \{r_1,r_2\} \\
                & & r_1 : A → rB | r \\
                & & r_2 : B → eB | sB | tB | e | s | t \\
                S & = & A
            \end{array}



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Links-lineare Grammatiken

    Bestimmen Sie eine links-lineare Typ-3 Grammatik :math:`G = (V ,T ,R,S)` für das Alphabet :math:`T = (a,b,k,n)`, für die :math:`L(G) = L(α)` mit :math:`α = b^*an(k|a)^*` gilt.


    .. solution::
        :pwd: LinksLinear+RegExps

        Der reguläre Ausdruck endet optional mit beliebiger Anzahl von k oder a, davor sind die
        Symbole an, die in links-linearer Grammatik umgekehrt erzeugt werden müssen. Davor
        ist optional eine beliebige Anzahl von b:

        .. math::

            \begin{array}{rcl}
            V & = & \{A,B,C\} \\
            T & = & \{a,b,k,n\} \\
            R & = & \{r_1,r_2,r_3\} \\
              &   & r_1 : A → Ak | Aa | Bn \\
              &   & r_2 : B → Ca | a \\
              &   & r_3 : C → Cb | b \\
            S & = & A
            \end{array}



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Regulären Ausdruck Vereinfachen

    Vereinfachen Sie den regulären Ausdruck :math:`α= (a^*b^*a^*|aba)^*(a|ac^*|aba)` zu einem äquivalenten kürzeren Ausdruck β mit :math:`L(α) = L(β)`.


    .. solution::
        :pwd: VereinfachenVonRegExps

        .. rubric:: Ableitung des vereinfachten regulären Ausdrucks

        - Der Ausdruck :math:`a^*b^*a^*` kann sowohl :math:`a` also auch :math:`b` erzeugen. Schon diese beiden Möglichkeiten lassen (a^*b^*a^*|aba)^* zu :math:`(a|b)^*` vereinfachen.
        - Jeder Term der zweiten Klammer beginnt mit :math:`a`, damit kann dieses Symbol ausgeklammert werden.
        - Die leere Option ist schon in :math:`c^*` enthalten. Damit ist :math:`(a|ac^*|aba)` äquivalent zu :math:`a(c^*|ba)`.

        Ein vereinfachter Ausdruck wäre damit :math:`β = (a|b)^*a(c^*|ba)`.



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Konvertierung einer einfachen Markup Sprache mittels RegExps

    Konvertieren Sie den verlinkten Text (|SomeText.txt|) mittels mehrere regulärer Ausdrücke in HTML. Das HTML soll dann dem verlinkten Ergebnis entsprechen (|SomeText.html|).

    Beachten Sie, dass ggf. die Reihenfolge in der Sie die regulären Ausdrücke auswerten relevant sein kann.

    Nutzen Sie ein Diff Tool Ihrer Wahl (zum Beispiel VS Code oder einfach ``diff``), um zu überprüfen ob Ihr Ergebnis den Erwartungen entspricht.

    Nutzen Sie entweder ``sed`` zur Auswertung Ihrer regulären Ausdrücke oder VS Code.

    .. supplemental::

        .. rubric:: SED 101

        - ``sed`` ist ein Stream-Editor, der einzelne Textzeilen bearbeiten kann.
        - Um reguläre Ausdrücke zu verwenden, muss ``sed`` mit dem Flag ``-E`` gestartet werden. Z. B.: ``sed -E -f SomeTextToHTML.sed SomeText.txt > SomeText.html``.
        - ``\s`` steht für alle "whitespace characters" (funktioniert aber ggf. nur unter bestimmten Versionen; z. B. nicht auf dem  Mac); ``[ ]`` oder ``[[:space:]]`` sind eine Alternative.
        - Ein Ausdruck in sed hat die Form:

          ::

             s/regexp/replacement/flags

          - Dabei wird der durch den regexp erkannte Text durch replacement ersetzt. Flags sind optional. Das g Flag (global) ermöglicht es alle Überstimmungen zu ersetzen und nicht nur die Erste.
          - Ein & im ``replacement`` bezieht sich auf den gesamten gefundenen Text.
          - Ein & im ``replacement`` kann durch ein Backslash escaped werden: \\&.
          - \\1, \\2, \\3 bezieht sich auf die gefundenen Gruppen (in Klammern) im regexp.

        - Ein ``sed`` Script ist eine Liste von ``sed`` Befehlen, die in einer Datei gespeichert werden und dann Zeile für Zeile auf den Input angewendet werden.
        - ``sed`` ist immer greedy und versucht längst-mögliche Übereinstimmungen zu finden. Ggf. ist es notwendig eine Formulierung zu finden, die verhindert, dass zu viel Text erfasst wird.

        .. hint::

            Es kann notwendig sein Hilfstransformationen durchzuführen, um die eigentlich gewünschte Transformation zu erreichen.

        **Beispiele**

        .. code:: bash
            :number-lines:

            $ echo "START aa B aa C aa ENDE START aa D aa" | sed -E 's/aa[^E]*aa/MATCH/'
            START MATCH ENDE START aa D aa

            $ echo "START aa B aa C aa ENDE START aa D aa" | sed -E 's/aa[^E]*aa/MATCH/g'
            START MATCH ENDE START MATCH


    .. solution::
        :pwd: RegExpsForDSL

        .. include:: code/SomeTextToHTML.sed
            :code: sed
            :number-lines:



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: DSL entwerfen mit LARK

    Sie wollen eine DSL für Ihre eigene Markupsprache entwickeln. Ihr Ziel ist es Texte folgender Art zu parsen, um diese danach weiterzuverarbeiten.

      ::

        + Wer bin ich?
        Ich bin *Prof.* an der DHBW [link: www.dhbw-mannheim.de].
        Meine Homepage finden sie hier: [link: www.michael-eichberg.de].

    Ein „\ ``+``“ am Anfang einer Zeile kennzeichnet eine Überschrift. Text in „\ ``*``“ soll fett dargestellt werden. URLs stehen in Blöcken, die mit „\ ``[link:``“ anfangen und mit „\ ``]``“ enden.

    Definieren Sie eine Grammatik in LARK. Wenn Sie reguläre Ausdrücke verwenden wollen – zum Beispiel zum Parsen von URLs – können Sie dies in der Grammatik direkt angeben (siehe ``WORD`` Regel). Der angehängte Code dient als Grundlage.

    .. supplemental::

        .. code:: python
            :class: copy-to-clipboard
            :number-lines:

            from lark import Lark
            GRAMMAR = r"""
                s: ...

                WORD: /[a-zA-Z]+/
                %import common.WS_INLINE
                %ignore WS_INLINE
            """

            l = Lark(GRAMMAR, start="s")
            print(l.parse("""+ Wer bin ich?"""))


    .. solution::
        :pwd: MeineErsteDSL

        .. rubric:: Lösung: LARK Grammatik

        .. code:: peg
            :class: copy-to-clipboard
            :number-lines:

            s: block+
            block: "+" WORD+ BLOCK_END? NL? -> heading
                | "*" block "*"             -> bold
                | "[link:" URL "]"          -> link
                | WORD                      -> word
                | BLOCK_END                 -> line_end

            NL:         /\r?\n/
            WORD:       /[a-zA-Z]+/
            BLOCK_END:  /[.:!?]/
            URL:        /[a-zA-Z.-]+/
            %import common.WS_INLINE
            %ignore WS_INLINE
