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
.. role:: obsolete
.. role:: monospaced
.. role:: copy-to-clipboard
.. role:: kbd
.. role:: java(code)
   :language: java



Formale Sprachen
======================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0

.. container:: minor

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
  


.. class:: wiederholung 

Kartesisches Produkt
--------------------------------------------------------

.. definition::

    Ein kartesisches Produkt wie :math:`A × B` oder :math:`A^n` für :math:`n ∈ \mathbb{N}` von Mengen oder Alphabeten bezeichnet die Menge der Tupel :math:`(a,b)` oder :math:`(a_1,...,a_n)` von Elementen der Mengen:

    .. math::

        \begin{array}{rccll}
            A × B & := & & & \{(a,b) | a ∈ A, b ∈ B\} \\
            A^n & := & \underbrace{A × ... × A}_{n\; \text{Faktoren}} & = & \{(a1,...,an) |a1,...,an ∈ A\}
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
                    M^0 & = & {ε} \\
                    M^1 & = & {01,2} \\
                    M^2 & = & {0101,012,201,22} \\
                    M^3 & = & {010101,01012,01201,0122,20101,2012,2201,222} \\
                    & \ldots & \\
                    M^+ & = & M^1 ∪ M^2 ∪ \ldots = {01,2,0101,012,201,22,010101,01012,...} \\
                    M^* & = & M^0 ∪ M^+ = {ε,01,2,0101,012,201,22,010101,01012,...}
                \end{array}
        
            .. observation::

                Die Wortlänge :math:`|ω|` für ein :math:`ω ∈ L^*` hängt von der Definition des Alphabets ab. So ist in diesem Beispiel :math:`|222| = 3` während :math:`|0101| = 2` ist.



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

    Sei :math:`Σ = {a,e,n,r}`, sowie :math:`ω = \text{na} ∈Σ^∗` und :math:`𝜐 = \text{er} ∈ Σ^∗`.  
    
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

    Gegeben sei das Alphabet :math:`Σ = {e,en,in,r,t,u}`. Welche der folgenden Worte liegen in :math:`Σ^5`?

    :math:`ω_1` = reiner, :math:`ω_2` = teurer, :math:`ω_3` = treuer, :math:`ω_4` = teuren, :math:`ω_5` = retten, :math:`ω_6` = teuer

    .. solution::
        :pwd: reiner ist teurer

        .. rubric:: Lösung

        .. math:: 

            ω_2, ω_3 \notin Σ^4 \\ 
            ω_1, ω_4, ω_5, ω_6 \in Σ^4



.. class:: exercises

Übung 
--------------------------------------------------------

.. exercise:: Alphabet Σ = {e,g,in,l,s,ter}

    Gegeben sei das Alphabet :math:`Σ = {e,g,in,l,s,ter}`. Welche der folgenden Worte liegen in :math:`Σ^*`?

    :math:`ω_1` = tester, :math:`ω_2` = seile, :math:`ω_3` = lines, :math:`ω_4` = segel, :math:`ω_5` = seinen, :math:`ω_6` = erster

    .. solution::
        :pwd: erster am seile

        .. rubric:: Lösung

        .. math:: 

            ω_1, ω_2, ω_5, ω_6 \notin Σ^4 \\ 
            ω_3, ω_4 \in Σ^4



Formale Sprachen
--------------------------------------------------------

.. definition::

    Jede Teilmenge :math:`L ⊆ Σ^*` ist eine formale Sprache über dem Alphabet :math:`Σ`.

.. example::

    Sei :math:`Σ = \{0,1,2\}`, dann ist :math:`Σ^*` die Menge oder Sprache von Wörtern aus den Ziffern :math:`0`, :math:`1` oder :math:`2` beliebiger Länge wie :math:`101` oder auch :math:`0001`. 
    
    .. deck:: numbered incremental

        .. card:: 
        
            Die Menge :math:`M ⊂ Σ^*` der binären Zahlen ohne führende Nullen:
         
            :math:`M = \{0\}∪\{1\}×\{0,1\}^* = \{0,1,10,11,100,101,110,111,1000,...\}`

        .. card::

            Die Menge :math:`M ⊂ Σ^*` von einer gleichen Anzahl von 0 und 1 in dieser Reihenfolge:

            :math:`M = \{0^n1^n | n ∈ \mathbb{N}\} = \{01,0011,000111,00001111,0000011111,...\}`

        .. card::

            Die Wörter :math:`M ⊂ Σ^*` mit gleicher Anzahl von 0, 1 und 2 in dieser Reihenfolge:

            :math:`M = \{0^n1^n2^n |n ∈ \mathbb{N}\}= \{012,001122,000111222,000011112222,...\}`

        .. card::

            Die Menge :math:`M ⊂ Σ^*` mit Wörtern der Länge von Zweierpotenzen:

            :math:`M = \{w ∈Σ^*| |w| = 2^n, n ∈ \mathbb{N}\}= \{0,1,2,00,01,\ldots,21,22,0000,...\}`



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

                :math:`M_{k-1}` ist die Menge, die noch *ein Element mehr enthält* als :math:`M_k`. D. h. die Kardinalität der Menge :math:`M_l` mit :math:`l > k` ist kleiner als die Kardinalität von :math:`M_k`.

            Die Bijektion lautet dann :math:`f : N → M` mit :math:`f(k) = m_k` mit :math:`N = \{1,...,n\}`.

    .. card::

        .. theorem::

            Jede Teilmenge :math:`M ⊆ N` einer abzählbaren Menge :math:`N= \{n_1,n_2,...\}` ist abzählbar.

        .. proof::

            Sei :math:`f(k) = n_k` die Abzählung der Menge N. Sei :math:`R = \{k ∈ \mathbb{N} | n_k ∈ M \}`; d. h. die Menge der Indizes der Elemente aus :math:`N`, die in :math:`M` sind. Dann ist die Einschränkung :math:`f_{|R} : R → M` von :math:`f` genau die Abzählung, die die Abzählbarkeit von :math:`M` beweist.

            .. presenter-note::
                
                :math:`f_{|R}` ist die Einschränkung von :math:`f`, die nur auf der Teilmenge :math:`R` des Definitionsbereichs von f betrachtet wird.

    .. card::

        .. example:: 

            Eine abzählbar unendliche Menge sind — zum Beispiel: 
            
            .. class:: incremental

            - die geraden Zahlen :math:`\{2n |n ∈\mathbb{N}\}` 
            - die Quadratzahlen :math:`\{n^2 |n ∈\mathbb{N}\}`
            - die Menge der Fakultäten :math:`\{n! |n ∈\mathbb{N}\}`
            - die ganzen Zahlen :math:`\mathbb{Z}` mit der Funktion:
              
              .. math::
    
                    f(n) = \left\{ \begin{array}{ll}
                        n/2 & \text{für}\; n\; \text{gerade} \\
                        -(n+1)/2 & \text{für}\; n\; \text{ungerade}
                    \end{array} \right.
              
              .. container:: peripheral s-font-size-90

                    :math:`f(1) = 0,\; f(2) = 1,\; f(3) = -1,\; f(4) = 2,\; f(5) = -2,\; ...`

    .. card::

        .. example:: 


            Die rationalen Zahlen :math:`\mathbb{Q}` sind abzählbar unendlich.

            .. image:: images/cantor.svg
                :alt: Diagonalisierungsverfahren von Cantor
                :align: center
              
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

            .. incremental:: 

                Unbesetzt bleibt, wo eine 0 in der Stellenwertdarstellung vorliegt. Zum Beispiel ist :math:`f(6) = 1 + 1 \cdot 5^1 + 0 \cdot 5^0 = 1_5 + 10_5`.

    .. card::

        .. theorem::

            Jede formale Sprache is abzählbar.

        .. proof::

            Da jede formale Sprache :math:`L` über einem endlichen Alphabet :math:`Σ` definiert ist, ist das eine direkte Folge aus vorherigem Satz, dass :math:`Σ^*` abzählbar ist, und wie zuvor gezeigt damit auch die Teilmenge :math:`L ⊆ Σ^*` abzählbar ist.        

    .. card::

        .. rubric:: Abzählen mit Hilfe von Gödelnummern

        .. supplemental::

            Gödelnummern unterstützen abzählbarer un-/endliche Mengen. Letzeres (abzähbar unendlich) ist mit einem einfachen Stellenwertsystem zur Basis der Anzahl der Elemente und des somit (zwangsweise) endlichen Alphabets nicht möglich.

        .. deck:: 

            .. card::
        
                .. definition:: 

                    Sei (:math:`p_n`) die Folge der Primzahlen:

                    :math:`p_1 = 2, p_2 = 3, p_3 = 5, p_4 = 7, p_5 = 11, p_6 = 13, ...`

                    Für eine abzählbare Menge :math:`M= {m_1,m_2,...}` ist die Gödelnummer :math:`c_M : M^* → \mathbb{N}` des Tupels :math:`w = (m_{k_1} ,m_{k_2} ,...,m_{k_l} )` gegeben durch 
                    
                    .. math:: 

                        c_M (w) = p^{k_1}_1 · p^{k_2}_2 · ... · p^{k_l}_l = \prod_{i=1}^{l} p^{k_i}_i

                    .. presenter-note::

                        *Fundamentalsatz der Arithmetik*: Jede natürliche Zahl :math:`n > 1` kann eindeutig als ein Produkt von Primzahlen geschrieben werden, wobei die Reihenfolge der Primfaktoren ignoriert wird. :math:`\Leftrightarrow` Die Gödelnummer :math:`c_M(w)` ist eindeutig für jedes Wort :math:`w ∈ M^*`.
            
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

                        \Z. B. ist die Primzahlzerlegung von :math:`10 = 2^1 \cdot 3^0 \cdot 5^1`. Somite gäbe es an der zweiten Stelle *kein Zeichen* was unsinnig ist.

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
            r_1 & = & 0,x_{11}x_{13}x_{13}x_{14}x_{15}... \\
            r_2 & = & 0,x_{21}x_{23}x_{23}x_{24}x_{25}... \\
            r_3 & = & 0,x_{31}x_{33}x_{33}x_{34}x_{35}... \\
            r_4 & = & 0,x_{41}x_{43}x_{43}x_{44}x_{45}... \\
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

        .. incremental::

            So sind dies verschwindend wenige lösbare Probleme verglichen schon mit der Reichhaltigkeit der reellen Zahlen im Intervall :math:`(0,1)`.

        .. conclusion::
            :class: incremental

            Soweit davon auszugehen ist, dass die Teilmenge der in der Realität tatsächlich relevanten reellen Zahlen tatsächlich auch überabzählbar ist, wird es nie möglich sein, für alle Fragestellungen über solche Zahlen Lösungen in der Form von Programmen über einer gegebenen formalen Sprache zu formulieren.


    .. card:: 

        .. conclusion::
        
            Gleichzeitig ist aber auch die Anzahl der formalen Sprachen sehr groß.

        .. proof:: 

            Für jede reelle Zahl :math:`x ∈R` mit Nachkommastellen :math:`r1r2...` gibt es eine formale Sprache :math:`L_x` über :math:`\Sigma_{\text{Zahl}}`: 

            :math:`L_x = \{r_1r_2...r_n ∈ Σ^*_{\text{Zahl}} |x \text{ hat die ersten } n \text{ Nachkommastellen } r_1...r_n\}`

            Beispielsweise ist :math:`L_π = {1,14,141,1415,14159,141592,1415926,...}`. Damit ist die Anzahl der formalen Sprachen mindestens so groß, wie die Anzahl reeller Zahlen im Intervall :math:`(0,1)`, also aller möglichen Nachkommastellen in :math:`\mathbb{R}`, zuzüglich der 0, und damit nach vorherigem Satz überabzählbar unendlich.



.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Stellenwerte I

    Gegeben sei das Alphabet :math:`Σ = {a,gen,i,re}` mit Aufzählung in dieser Reihenfolge. Bestimmen Sie die Zahlen :math:`n` nach Stellenwert mit Bild :math:`f (n)` der Wörter :math:`regen`, :math:`aare` und die Worte mit Stellenwert :math:`15`, :math:`118`.


    .. solution::
        :pwd: Im Regen 

        .. rubric:: Lösung

        :math:`regen= f (1+ 4·5^1 + 2) = f (23)`, :math:`f (15) = f (1+ 2·5^1 + 4) = genre`, :math:`aare = f (1+ 1·5^2 + 1·5^1 + 4) = f (35)`, :math:`f (118) = f (1+ 4·5^2 + 3·5^1 + 2) = reigen`



.. exercise:: Stellenwerte II

    Gegeben sei das Alphabet :math:`Σ = {e,h,r,ste}` mit Aufzählung in dieser Reihenfolge. Bestimmen Sie die Zahlen n nach Stellenwert mit Bild :math:`f (n)` der Wörter :math:`steh`, :math:`rehe` und die Worte mit Stellenwert :math:`45`, :math:`1417`.


    .. solution:: 
        :pwd: steh steher

        .. rubric:: Lösung

        :math:`steh= f (1+ 4·5^1 + 2) = f (23)`, :math:`rehe= f (1+ 3·5^3 + 1·5^2 + 2·5^1 + 1) = f (412)`, :math:`f (45) = f (1+1·5^2+3·5^1+4) = erste`, :math:`f (1417) = f (1+2·5^4+1·5^3+1·5^2+3·5^1+1) = heere`


.. class:: exercises

Übung
--------------------------------------------------------

.. exercise:: Gödelnummern I

    Gegeben sei das Alphabet :math:`Σ = {e,l,ste,te}` mit Aufzählung in dieser Reihenfolge. Bestimmen Sie die Gödelnummer :math:`c(w)` der Wörter :math:`este`, :math:`elle` und die Worte mit Gödelnummer :math:`720`, :math:`12600`.

    .. solution:: 
        :pwd: tele&stelle

        .. rubric:: Lösung

        :math:`c(este) = 2^1 \cdot 3^3 = 54`

        :math:`c(elle) = 2^1 \cdot 3^2 \cdot 5^2 \cdot 7^1 = 3150`

        :math:`720 = 2^4 \cdot 3^2 \cdot 5^1 = 720 = c(tele)`
        
        :math:`12600 = 2^3 \cdot 3^2 \cdot 5^2 \cdot 7^1 = c(stelle)`

.. exercise:: Gödelnummern II

    Gegeben sei das Alphabet :math:`Σ = {h,he,re,ste}` mit Aufzählung in dieser Reihenfolge. Bestimmen Sie die Gödelnummer :math:`c(w )` der Wörter :math:`steh`, :math:`reste` und die Worte mit Gödelnummer :math:`144`, :math:`1500`.

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
        
            math:`L_k`: Alle Wörter, die mit dem Symbol :math:`a` beginnen.

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

            Die Folge erreicht für :math:`n = 6` also den Wert :math:`1` nach :math:`8` Schritten.

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

            L_{\text{Collatz}} = \{n ∈N |∃k ∈\mathbb{N}_0 : f^k(n) = 1\}

        .. incremental::

            Das Wortproblem auf dieser Sprache ist damit — hier nach Definition des Problems statt einer Aufzählung — ebenso mindestens semi-entscheidbar.

        .. incremental::

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



Englische Grammatik (etwas vereinfacht)
--------------------------------------------------------


