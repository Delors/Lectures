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

            Die Menge :math:`M ⊂ Σ^∗` von einer gleichen Anzahl von 0 und 1 in dieser Reihenfolge:

            :math:`M = \{0^n1^n |n ∈ \mathbb{N}\}= \{01,0011,000111,00001111,0000011111,...\}`

        .. card::

            Die Wörter :math:`M ⊂ Σ^*` mit gleicher Anzahl von 0, 1 und 2 in dieser Reihenfolge:

            :math:`M = \{0^n1^n2^n |n ∈ \mathbb{N}\}= \{012,001122,000111222,000011112222,...\}`

        .. card::

            Die Menge :math:`M ⊂ Σ^∗` mit Wörtern der Länge von Zweierpotenzen:

            :math:`M = \{w ∈Σ^∗| |w| = 2^n, n ∈ \mathbb{N}\}= \{0,1,2,00,01,\ldots,21,22,0000,...\}`



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


