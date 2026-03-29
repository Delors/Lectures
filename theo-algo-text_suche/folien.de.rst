.. meta::
    :lang: de
    :author: Michael Eichberg
    :keywords: "Textsuche", "Arrays", "Algorithmen"
    :description lang=de: Suchen auf Arrays
    :id: lecture-theo-algo-text_suche_auf_arrays
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Textsuche
======================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1.5 [Themed]

.. container:: peripheral

    :Quelle:
        Die Folien sind teilweise inspiriert von oder basierend auf Lehrmaterial von Prof. Dr. Ritterbusch


.. supplemental::

    :Folien:

        |html-source|

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: center-content

Arrays und Textsuche
--------------------------------------------------------

Texte können als unsortierte Arrays von Zeichen verstanden werden. Eine typische
Frage ist hier das Finden von Textsequenzen im Text.




Einfache Textsuche
------------------------------------------------

.. To generate strike-through unicode letters: https://yaytext.com/strike/

.. deck::

    .. card::

        .. note::
            :class: width-40 incremental

            Die Laufzeit der einfachen Textsuche kann asymptotisch durch :math:`O(n·m)` abgeschätzt werden.

        .. code:: pascal
            :number-lines:
            :class: copy-to-clipboard

            function NaiveTextSearch(text,needle)
                n := length(text)
                m := length(needle)
                for i := 1,...,n-m + 1 do
                    j := 0
                    while text[i + j] == needle[j + 1] do
                        j := j + 1
                        if j == m then
                            return i // Found at i
                return nil

    .. card::

        .. rubric:: Beispiel bei einfacher Suche nach ``aaab`` in ``aaaaaaaab``:

        .. container::  monospaced

            ::

                a a a a a a a a b
                ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
                a a a b̶
                  a a a b̶
                    a a a b̶
                      a a a b̶
                        a a a b̶
                          a a a b

        .. container:: incremental

            Sind so viele Vergleiche notwendig?



Knuth-Morris-Pratt Verfahren - Grundlagen
------------------------------------------------

.. Use the jshell to generate combined characters: (https://www.compart.com/en/unicode/combining/220)
   Example: \u0305 = Combining Overline
            \u0332 = Combining Low Line
            a̲̅ = "a\u0305\u0332"
            a̅ = "a\u0305"
            a̲ = "a\u0332"
            n̲ = "n\u0332"
            n̅ = "n\u0305"

.. deck::

    .. card::

        Das Verfahren von Knuth-Morris-Pratt vermeidet unnötige Vergleiche, da es zunächst die Suchwortteile auf den größten Rand, also das größte Prefix, das auch Postfix ist, untersucht.

        .. definition:: : Präfix, Postfix und Rand
            :class: incremental

            Für ein Wort :math:`w = (w_1,...,w_n)` sind die Präfixe :math:`p^{(k)} = (w_1,...,w_k )` und die Postfixe :math:`q^{(k)} = (w_{n−k+1},...,w_{n})` für :math:`0 ≤k ≤n`.

            Ist :math:`p^{(k)} = q^{(k)} = r^{(k)}` für ein :math:`0 ≤k <n`, so ist :math:`r(k)` ein Rand von :math:`w`.

            Für :math:`k <n` werden :math:`p^{(k)}` und :math:`q^{(k)}` auch echte Prä- und Postfixe genannt.

    .. card::

        .. rubric:: Beispiel/Idee

        ::

            Text              010110101
            Gesucht/Muster    010101
            Übereinstimmung   ✓✓✓✓✗

        .. container:: incremental margin-top-1em

            **Beobachtungen:**

            1. Wir haben an Stelle 5 ein Mismatch.
            2. Wenn wir im Text das Muster um eine Stelle nach rechts verschoben suchen, so haben wir garantiert wieder ein Mismatch.

            .. admonition:: Frage
                :class: question far-smaller incremental

                Wie weit kann man also das Muster im Allgemeinen verschoben werden ohne ein Vorkommen zu übersehen?


    .. card::

        .. rubric:: Beispiel/Idee

        ::

                                1.                    2.
            Text                01101100              0102111
            Gesucht/Muster      01100                 010201
            Übereinstimmungen   ✓✓✓✓✗                 ✓✓✓✓✗

        .. container:: incremental margin-top-1em

            **Beobachtungen bzgl.:**

            1. Beim Mismatch an Stelle 5 kann das Muster „nur“ um 3 Stellen nach rechts verschoben werden.
            2. Beim Mismatch an Stelle 5 kann das Muster um 4 Stellen nach rechts verschoben werden.

            .. container::

                Wie weit wir das Muster verschieben können, hängt also vom Rand des Teils des Musters ab, der bereits übereinstimmt.

    .. card::

            .. rubric:: Beispiel

            Das Wort :math:`aufkauf` hat die *echten* Präfixe und Postfixe:

                :math:`\{p^{(k)} : 0 ≤k <n\}=\{ε,a,au,auf,aufk,aufka,aufkau\}`

                :math:`\{q^{(k)} : 0 ≤k <n\}=\{ε,f,uf,auf,kauf,fkauf,ufkauf\}`

            und die Ränder:

                :math:`\{r^{(k)} : 0 ≤k <n\}= \{ε,auf\}`.

            Das bedeutet, dass wenn :math:`aufkauf` erkannt wurde, die letzten drei Buchstaben schon den nächsten Treffer einleiten können, wie beispielsweise in :math:`aufkaufkauf`.

    .. card::

        Das KMP-Verfahren fängt nicht immer von vorne an, sondern prüft, ob ein Rand eines Präfixes :math:`- ε` ausgenutzt werden kann. Dazu werden die entsprechenden größten Ränder bestimmt.

        .. grid::

            .. cell:: width-50

                .. rubric:: Beispiel: ananas

                .. csv-table::
                    :header: "Präfixe :math:`\setminus \{ε\}`", "Größter Rand", "Länge des Randes"
                    :class: highlight-row-on-hover incremental-table-rows
                    :width: 100%

                    a, ε, 0
                    an, ε, 0
                    a̲na̅, a, 1
                    a̲n̲a̅n̅, an, 2
                    a̲n̲a̲̅n̅a̅, ana, 3
                    ananas, ε , 0


            .. cell:: width-50 incremental

                .. rubric:: Beispiel: axaaxax

                .. csv-table::
                    :header: "Präfixe :math:`\setminus \{ε\}`", "Größter Rand", "Länge des Randes"
                    :class: highlight-row-on-hover incremental-table-rows
                    :width: 100%

                    a, ε, 0
                    ax, ε, 0
                    a̲xa̅, a, 1
                    a̲xaa̅, a, 1
                    a̲x̲aa̅x̅, ax, 2
                    a̲x̲a̲a̅x̅a̅, axa, 3
                    a̲x̲aaxa̅x̅, ax , 2


.. supplemental::

    Die Idee ist also, dass wir beim Musterabgleich nach einem Mismatch, wenn der übereinstimmende
    Teil einen Rand hat, beim Abgleich des Musters an einer späteren Stelle - basierend auf der Größe des Randes - weitermachen können. Wir müssen also nicht immer das ganze Muster von vorne anfangen zu vergleichen.



.. class:: exercises transition-fade

Übung
--------

.. exercise:: Ränder und Randlängen bestimmen

    Bestimmen Sie die Ränder und die Längen der :math:`Präfixe - ε` für die Worte:

    1. :math:`tultatul`
    2. :math:`eikleike`
    3. :math:`okokorok`
    4. :math:`trattrad`

    .. solution::
        :pwd: raender_+_randlaengen

        .. rubric:: Beispiel: tultatul

        .. csv-table::
            :header: ":math:`Präfixe \\setminus \\{ε\\}`", "Größter Rand", "Länge des Randes"

            t, ε, 0
            tu, ε, 0
            tul, ε, 0
            tult, t, 1
            tulta, ε, 0
            tultat, t, 1
            tultatu, tu, 2
            tultatul, tul, 3

        .. rubric:: Beispiel: eikleike

        .. csv-table::
            :header: ":math:`Präfixe \\setminus \\{ε\\}`", "Größter Rand", "Länge des Randes"

            e, ε, 0
            ei, ε, 0
            eik, ε, 0
            eikl, ε, 0
            eikle, e, 1
            eiklei, ei, 2
            eikleik, eik, 3
            eikleike, e, 1

        .. rubric:: Beispiel: okokorok

        .. csv-table::
            :header: ":math:`Präfixe \\setminus \\{ε\\}`", "Größter Rand", "Länge des Randes"

            o, ε, 0
            ok, ε, 0
            oko, o, 1
            okok, ok, 2
            okoko, oko, 3
            okokor, ε, 0
            okokoro, o, 1
            okokorok, ok, 2

        .. rubric:: Beispiel: trattrad

        .. csv-table::
            :header: ":math:`Präfixe \\setminus \\{ε\\}`", "Größter Rand", "Länge des Randes"

            t, ε, 0
            tr, ε, 0
            tra, ε, 0
            trat, t, 1
            tratt, t, 1
            trattr, tr, 2
            trattra, tra, 3
            trattrad, ε, 0



Knuth-Morris-Pratt Verfahren
------------------------------------------------

.. deck::

    .. card::

        .. code:: pascal
            :number-lines:
            :class: copy-to-clipboard

            function ComputePrefixFunction(needle)
                m := length(needle)
                sei B[1...m] ein Array // Array für die Längen der Ränder der Teilworte
                B[1] := 0
                j := 0 // j ist die Länge des Randess
                for i := 2,...,m do
                    j := j + 1
                    while j > 0 and needle[j] ≠ needle[i] do
                        if j > 1 then
                            j := B[j-1] + 1
                        else
                            j := 0
                    B[i] := j
                return B

        Komplexität: :math:`O(m)`

    .. card::

        .. code:: pascal
            :number-lines:

            function KMP(text,needle)
                n := length(text), m := length(needle)
                B := ComputePrefixFunction(needle)
                q := 0               // Anzahl der übereinstimmenden Zeichen
                R := []              // Ergebnisliste der Indizes der Übereinstimmungen
                for i := 1,...,n do
                    while q > 0 and needle[q + 1] ≠ text[i] do
                        q := B[q]    // ... die nächsten Zeichen stimmen nicht überein
                    if needle[q + 1] == text[i] then
                        q := q + 1   // Übereinstimmung
                    if q == m then
                        R append (i - m + 1)
                        q := B[q]    // Suche nach nächster Übereinstimmung
                return R

        Komplexität: :math:`O(n+m)`

.. supplemental::

    **Details ComputePrefixFunction**

    Die Funktion :math:`ComputePrefixFunction` berechnet die größten Werte der Präfixe für das Suchwort :math:`needle` der Länge :math:`m` und gibt diese als Array (:math:`B`) zurück.
    Das Array :math:`B` enthält somit die größten Ränder der Präfixe :math:`needle[1,...,i]`.
    (Der Wert von :math:`B[1]` ist immer 0, da es keinen Rand gibt.)

    .. ? Die grundlegende Idee ist, dass der Rand des Präfixes :math:`needle[1,...,i]` der Rand des Präfixes :math:`needle[1,...,i-1]` ist, wenn :math:`needle[i] = needle[j]` ist.



Beispiel für eine KMP-Textsuche
------------------------------------------------------------

Gesucht wird ``ananas`` in ``saansanananas``

.. container:: far-smaller

    ::

           s a a n s a n a n a n a s
        i  ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
        1  a̶
        ...
        3    a n̶
        ...
        5      a n a̶
        ...
        11           a n a n a s̶       Beim Auftreten des Mismatch (ln 7) ist
        ...                            q=5 und wird auf p[5]=3 (ln 8) gesetzt
        13               a n a n a s

.. remark::

    Dargestellt sind die Fälle, in denen ein Mismatch auftritt. ``i`` ist der Index des aktuellen Zeichen im Text, das mit dem Muster verglichen wird.



.. class:: exercises transition-fade

Übung
--------

.. exercise:: KMP-Algorithmus

    Bestimmen Sie die Randlängen der Muster und stellen Sie die Teilschritte bei der Durchführung des KMP-Algorithmus zur Suche des Wortes/Muster im Text  dar.

    Stellen Sie insbesondere die Fälle dar in denen ein Mismatch auftritt.

    .. csv-table::
        :header: "Muster", "Text"
        :align: left

        ``aaab``, ``aaaaaaaab``
        ``barbara``, ``abbabarabarbarbara``

    .. solution::
        :pwd: Barbarasrababerbar

        .. rubric:: Lösung bzgl. ``aaab`` in ``aaaaaaaab``

        .. csv-table::
            :header: Präfixe, größter Rand, Länge des Randes

            a, ε, 0
            aa, a, 1
            aaa, aa, 2
            aaab, ε, 0

        **Durchführung des KMP-Algorithmus**

        ::

          a a a a a a a a b
          ___________________
          a a a b̶             Beim Mismatch bei i == 4 ist q == 3 und wird auf q=p[3] == 2
                              (längster Rand) gesetzt und direkt wieder um 1
                              erhöht, da das nächste Zeichen (a == a) übereinstimmt.
            a a a b̶
              a a a b̶
                a a a b̶
                  a a a b̶
                    a a a b

        .. rubric:: Lösung bzgl. ``barbara`` in ``abbabarabarbarbara``

        .. csv-table::
            :header: Präfixe, größter Rand, Länge des Randes

                Präfixe, größter Rand, Länge des Randes
                b, ε, 0
                ba, ε, 0
                bar, ε, 0
                barb, b, 1
                barba, ba, 2
                barbar, bar, 3
                barbara, ε, 0

        **Durchführung des KMP-Algorithmus**

        ::

            a b b a b a r a b a r b a r b a r a
            ___________________________________
            b̶
              b a̶
                b a r̶
                    b a r b̶
                            b a r b a r a̶
                                  b a r b a r a



Boyer-Moore-Algorithmus (vereinfacht)
------------------------------------------------


.. deck::

    .. card::

        .. observation::

            Häufig ist das Alphabet des Textes größer als das des Musters.

        .. container:: incremental list-with-explanations

            - Der Algorithmus vergleicht das Muster (Pattern) von rechts nach links mit dem Text.

              Viele andere Algorithmen führen die Vergleiche von links nach rechts durch.
            - Der Boyer-Moore-Algorithmus nutzt dies aus, indem er die Verschiebung des Musters anhand des letzten Zeichens des Musters und des Textes vornimmt.

    .. card::

        Wird beispielsweise das Wort :java:`Banane` im Text :java:`Orangen,␣Ananas␣und␣Bananen` gesucht, so wird zunächst die Sprungtabelle für das verwendete Alphabet in Bezug auf das Suchwort (:java:`Banane` mit Länge 6) bestimmt:

        .. csv-table::
            :class: fake-header-column

            Zeichen im Text,  ␣, ",", A, B, O, a, d, e, g, n, r, s, u
            Sprung, 6, 6, 6, 5, 6, 2, 6, 0, 6, 1, 6, 6, 6

    .. card::

        .. combinbing strike through: \u0336; combining underline: \u0332

        ::

            O r a n g̲ e̲ n , ␣ A n̲ a̲ n a̲ s ␣̲ u n d ␣ B̲ a̲ n̲ a̲ n̲ e̲ n̲
            B a n a n̶̲ e̶̲
                      B a n a n e̶̲
                        B a n a n e̶̲
                            B a n a n e̶̲
                                B a n a n e̶̲
                                            B a n a n e̶̲
                                                B a n a n e̶̲
                                                    B̲ a̲ n̲ a̲ n̲ e̲
                                                      B a n a n e̶̲

        .. remark::

            Unterschrichen sind die durchgeführten Vergleiche. Die Verschiebung des Musters erfolgt anhand des letzten Zeichens des Musters und des Textes, dass nicht übereinstimmt. Dabei ist die Verschiebung durch das Zeichen des Textes gegeben, das nicht mit dem Muster übereinstimmt.

    .. card::

        .. rubric:: Komplexität

        Im guten und häufigen Fall erreicht das Verfahren :math:`O(\frac{n}{m})`, aber in speziellen Fällen ist auch :math:`O(n·m)` möglich.

.. supplemental::

    Bei der Sprungtabelle handelt es sich um eine Tabelle, die *für jedes Zeichen des Alphabets des Textes* die Verschiebung des Musters angibt, wenn das Zeichen im Text mit dem Muster nicht übereinstimmt. Die Zeichen :java:`A`, :java:`O`, :java:`d`, :java:`g`, :java:`r`, :java:`s`, :java:`u`, :java:`,` und das Leerzeichen haben die größte Verschiebung, da sie nicht im Muster vorkommen. Das Zeichen :java:`e` hat die kleinste Verschiebung, da es das letzte Zeichen des Musters ist. Das Zeichen :java:`n` hat eine Verschiebung von 1, da es im Muster  als vorletztes Zeichen vorkommt, das Zeichen :java:`a` hat eine Verschiebung von 2, da das späteste Vorkommen an drittletzer Stelle ist, und das Zeichen :java:`B` hat eine Verschiebung von 5, da es nur einmal vorkommt und das erste Zeichen des Musters ist.



.. class:: exercises transition-fade

Übung - Boyer-Moore-Algorithmus
------------------------------------------

.. exercise:: „belli“

    Suchen Sie das Wort

    ::

        belli

    im Text

    ::

        It is a dark time for the Rebellion.


    .. solution::
        :pwd: Rebellion_!

        .. rubric:: Lösung

        .. container:: smaller

            ::

                I t   i s   a   d a r k   t i m e   f o r   t h e   R e b e l l i o n .
                b e l l i̲
                          b e l l i̲
                                    b e l l̲ i̲
                                            b e l l i̲
                                                      b e l l i̲
                                                                b e l l i̲
                                                                        b̲ e̲ l̲ l̲ i̲
                                                                          b e l l i̲


.. exercise:: "barbara"

    Suchen Sie das Wort

    ::

        barbara

    im Text

    ::

        abbabarabarbarbara


    .. solution::
        :pwd: #Barbarasrababerbar+

        .. rubric:: Lösung

        .. container:: smaller

            ::

                a b b a̲ b̲ a̲ r̲ a̲ b a r b̲ a̲ r̲ b̲ a̲ r̲ a̲
                b a r b a r a̲
                  b a r̲ b̲ a̲ r̲ a̲
                    b a r b a r a̲
                          b a r b a r a̲
                                b a r b a r a̲
                                      b̲ a̲ r̲ b̲ a̲ r̲ a̲
