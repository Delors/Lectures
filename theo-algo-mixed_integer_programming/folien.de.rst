.. meta:: 
    :lang: de
    :author: Michael Eichberg
    :keywords: "mixed-integer programming", "python"
    :description lang=de: Lineare und Mixed-Integer-Programmierung
    :id: lecture-theo-mixed_integer_programming
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


.. |group_assignment_template.py| source:: code/group_assignment_template.py
            :path: relative
            :prefix: https://delors.github.io/

.. |send_more_money.py| source:: code/send_more_money.py
    :path: relative
    :prefix: https://delors.github.io/


.. class:: animated-symbol 

Lineare und Mixed-Integer Programmierung
======================================================

.. rubric:: Eine allererste Einführung

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0

.. container:: minor

    :Quelle: 
       
       Introduction to Algorithms, 3rd Edition, Cormen, Leiserson, Rivest, Stein, MIT Press, 2009

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

.. container:: smaller

    Seien :math:`x_1` und :math:`x_2` die Menge an Nahrungsmitteln 1 und 2, die wir kaufen. Die Kosten für Nahrungsmittel 1 und 2 betragen 1 und 2 Euro pro Einheit. Die täglichen Ernährungsbedürfnisse sind 10 Einheiten Protein und 20 Einheiten Fett. Nahrungsmittel 1 enthält 2 Einheiten Protein und 3 Einheiten Fett pro Einheit. Nahrungsmittel 2 enthält 1 Einheiten Protein und 4 Einheiten Fett pro Einheit.

    .. incremental:: 

        .. rubric:: Zielfunktion (:eng:`objective function` oder einfach nur :eng:`objective`)

        .. math::

            \min(x_1 \cdot 1\text{€} + x_2 \cdot 2\text{€})
            
        .. rubric:: Nebenbedingungen (:eng:`constraints`, oft auch mit :eng:`subject to (s.t.)` eingeleitet)

        .. math::
            
            \begin{array}{rl}
            2 \cdot x_1 + 1 \cdot x_2 \geq 10 &  \text{Nebenbedingung bzgl. Protein}\\
            3 \cdot x_1 + 4 \cdot x_2 \geq 20 & \text{Nebenbedingung bzgl. Fett}\\
            x_1 \geq 0 &\\
            x_2 \geq 0&
            \end{array}



Lineare Programmierung
------------------------

.. admonition:: Definition

    Lineare Programmierung: Optimierung von linearen Funktionen unter linearen Nebenbedingungen.

.. container:: smaller

    Das Ziel ist die Optimierung (Maximierung/Minimierung) einer linearen Funktion :math:`f`:

    .. math::

        f(x_1,\ldots,x_n) = a_1 \cdot x_1 + a_2 \cdot x_2 + \ldots + a_n \cdot x_n = \sum_{i=1}^{n} a_i \cdot x_i

    Unter einer Menge von linearen Nebenbedingungen. Sei :math:`b \in \mathbb{R}`, dann ist ...
    
    - eine *lineare Ungleichung* der Form: :math:`f(x_1,\ldots,x_n) \leq b`
    - eine *lineare Gleichung* der Form: :math:`f(x_1,\ldots,x_n) = b`
    - lineare Ungleichungen und Gleichungen beschreiben die *linearen Nebenbedingungen*.



Lösen von linearen Optimierungsproblemen
------------------------------------------

.. stack:: invisible 
   
    .. layer:: 

        .. container:: two-columns

            .. container:: column no-separator

                .. rubric:: Standardform - „nur“ Verwendung von linearen Ungleichungen

                **Zielfunktion (Maximiere)**

                .. math::

                        x_1 + x_2 

                **Nebenbedingungen**

                .. math::
                
                        \begin{array}{rrcr}
                        4  x_1 & - & x_2 & \leq & 8 \\
                        2  x_1 & + & x_2  & \leq & 10 \\
                        5 x_1 & - & 2  x_2 & \geq  & -2 \\
                        & & x_1 &\geq & 0 \\
                        & & x_2 &\geq & 0
                        \end{array}

            .. container:: column incremental

                .. stack:: invisible

                    .. layer:: 

                        .. image:: images/lp-constraints.svg
                            :align: center
                            :height: 925px

                    .. layer:: incremental overlay

                        .. image:: images/lp-solution.svg
                            :align: center
                            :height: 925px

    .. layer:: incremental 

        .. rubric:: Schlupfform (:eng:`Slack Form`) - Verwendung von linearen Gleichungen

        **Zielfunktion (Maximiere)**

        .. math::

                x_1 + x_2 

        **unter den Nebenbedingungen**

        .. math::
            :class: text-align-left left align-left
        
                \begin{array}{rcrcrcr}
                x_3 & = &  8 & - & 4x_1 & + & x_2  \\
                x_4 & = & 10 & - & 2x_1 & - & x_2  \\
                x_5 & = &  2 & + & 5x_1 & - & 2x_2 \\
                  0 & \leq & x_1, & x_2, & x_3, & x_4 , & x_5 \\
                \end{array}

        Bei der Beschreibung des Simplex-Algorithmus wird diese Form relevant sein.

.. supplemental::

    **Beobachtungen (am Beispiel orientiert)**

    - der Bereich der zulässigen Lösungen enthält (im Allgemeinen) unendlich viele Punkte
    - der Bereich der zulässigen Lösungen ist beschränkt/ist (hier) ein konvexes Polygon (im Allgemeinen ein konvexes Polyeder)
    - Die konvexe Hülle einer endlichen Anzahl von affin unabhängigen Punkten in einem n-dimensionalen Raum bezeichnen wir als Simplex
    - in diesem (2-Dimensionalen) Fall können wir die Lösung grafisch darstellen
    - nicht jedes lineare Optimierungsproblem hat eine (bzw. eine optimale) Lösung
    - Auch in der Schlupfform, werden die Anforderungen an die nicht-Negativität der Variablen als Ungleichungen beschrieben.

    .. container:: minor smaller

        :Affine Unabhängigkeit: 

            Zwei Punkte sind affin unabhängig, wenn die Differenz der beiden Punkte nicht durch einen Skalarfaktor auf den anderen Punkt abgebildet werden kann. (Im 2-D Raum: Die beiden Punkte liegen nicht auf einer Geraden, wenn die beiden Punkte als entsprechende Vektoren aufgefasst werden.)



Simplex Algorithmus
------------------------

.. class:: incremental list-with-explanations

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

.. container:: scrollable

    Geben sein :math:`n` reelle Zahlen :math:`(c_1,...,c_n`); :math:`m` reelle Zahlen (:math:`b_1,...,b_m`); und eine :math:`m \times n` Matrix :math:`A = (a_{ij})` für :math:`i = 1,2,...m` und :math:`j = 1,2,...n`. 

    Wir möchten nun n reelle Zahlen :math:`(x_1,...,x_n)` finden, die die folgenden Bedingungen erfüllen:

    **Zielfunktion** (:eng:`objective function`)

    .. math::

        \text{maximimiere} \sum_{j=1}^{n} c_j \cdot x_j


    **(unter den) Nebenbedingungen** (:eng:`subject to/constraints`)

    .. math::

        \begin{array}{rcll}
        \sum_{j=1}^{n} a_{ij} \cdot x_j & \leq & b_i & \text{für } i = 1,2,...,m \\
        x_j & \geq & 0 & \text{für } j = 1,2,...,n
        \end{array}

    .. incremental::

        .. rubric:: Kompakte Darstellung

        Sei :math:`A = (a_{ij})`, m-Vektor :math:`b = (b_i)`, n-Vektor :math:`c = (c_j)`, und n-Vektor :math:`x = (x_j)`. Dann ist das lineare Programm:

        .. math::

            \begin{array}{rcl}
            \text{maximimiere} & c^T x & \\
            \text{unter den Nebenbedingungen} & A \cdot x & \leq & b \\
            & x & \geq & 0
            \end{array}

    .. incremental::

        .. rubric:: Terminologie

        :zulässige Lösung/`feasible`:eng:: Eine Belegung der Variablen :math:`\bar{x}`, die die Nebenbedingungen erfüllt.
        :optimale Lösung: Eine zulässige Lösung, die die Zielfunktion maximiert.
        :unbeschränkt: Ein lineares Programm, dass Lösungen hat, die die Zielfunktion nicht beschränken.
        :unzulässig/`infeasible`:eng:: Ein lineares Programm, dass keine zulässige Lösung hat.

    .. incremental::

        .. rubric::  Konvertierung von beliebigen linearen Programmen in die Standardform

        Ein lineares Programm kann aus folgenden vier Gründen nicht in  Standardform sein:

        - Die Zielfunktion ist zu minimieren
        - Es gibt Variablen ohne Nichtnegativitätsbedingung
        - Es gibt Gleichungen (:math:`=`)
        - Es gibt Ungleichungen mit :math:`\geq` statt :math:`\leq`
        
    .. incremental:: 

        .. rubric:: Regeln

        1. Minimierungsprobleme können durch Multiplikation der Zielfunktion mit :math:`-1` in ein Maximierungsproblem umgewandelt werden.
        2. Variablen ohne Nichtnegativitätsbedingung können durch die Einführung von Differenzvariablen in Nichtnegativitätsbedingungen umgewandelt werden.

           D. h. wir ersetzen die Vorkommen der Variable :math:`c\cdot x` durch :math:`c\cdot x^+ - c\cdot x^-` wobei :math:`x^+` und :math:`x^-` nicht-negativ sind.
        3. Gleichungen können in zwei Ungleichungen umgewandelt werden.
        4. Ungleichungen mit :math:`\geq` können durch Multiplikation mit :math:`-1` in Ungleichungen mit :math:`\leq` umgewandelt werden.

.. supplemental::

    - :math:`c^Tx` ist das innere Produkt.
    - :math:`x & \geq & 0` bedeutet, dass jede Komponente von :math:`x` nicht negativ sein darf.


.. class:: integrated-exercise transition-move-to-top

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
        
        :math:`x2` wurde durch :math:`x_2` und :math:`x_3` nach Regel 2 ersetzt.

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


.. exercise:: Zeigen Sie, das das folgende lineare Programm unzulässig ist.

    .. math::

        \begin{array}{rrcrcl}
        \text{maximiere} & 3x_1 & - & 2x_2 & \\
        \text{unter den Nebenbedingungen} \\
        & x_1 & + & x_2 & \leq & 2 \\
        & -2 x_1 &-& 2x_2 & \leq & -10 \\
        & x_1, & x_2 & & \geq & 0
        \end{array}

    .. solution::
        :pwd: WiDeRspruch

        Nach Umformulierung der 2. Nebenbedingung (geteilt durch -2), erhalten wir die Nebenbedingungen:

        .. math::

            \begin{array}{rrcrcl}
            & x_1 & + & x_2 & \leq & 2 \\
            & x_1 & + & x_2 & \geq & 5 \\   
            \end{array}
        
        Und somit unmittelbar einen Widerspruch.


Schlupfform (:eng:`Slack Form`)
----------------------------------

.. container:: scrollable
    
    - Zum effizienten Lösung von linearen Programmen wird die Schlupfform verwendet. 
    - Bei der Schlupfform werden alle Nebenbedingungen in Gleichungen umgewandelt - abgesehen von den Nichtnegativitätsbedingungen.

      .. container:: box-shadow padding-0-5em rounded-corners smaller
        
        .. rubric:: Vorgehen

        Gegeben sei *eine* Ungleichung (1):

        .. math::
            
            \textstyle \sum_{j=1}^{n} a_{ij} \cdot x_j \leq b_i \\
        
        Wir führen dann eine Schlupfvariable (:eng:`slack variable`) :math:`x_{n+i}` ein und erhalten (2):

        .. math::
            
            \textstyle x_{n+1} = b_i - \sum_{j=1}^{n} a_{ij} \cdot x_j \qquad \text{und }\qquad x_{n+1} \geq 0
            
    .. class:: incremental

    - Somit stehen die Variablen :math:`x_1,...,x_n` für die ursprünglichen Variablen und die Variablen :math:`x_{n+1},...,x_{n+m}` für die Schlupfvariablen. 
    - Auf der rechten Seite der Gleichung (2) stehen die ursprünglichen Variablen. Nur diese Variablen sind Teil der Zielfunktion.
      
      :Basisvariablen: die Variablen auf der linken Seite der Gleichung (2) 
      :Nichtbasisvariablen: die Variablen auf der rechten Seite der Gleichung (2) 
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


.. class:: integrated-exercise transition-move-to-top

Übung
------- 

.. exercise:: Überführen eines linearen Programms in Schlupfform

    Überführen Sie das 1. lineare Programm aus der vorhergehenden Übung in Schlupfform.

    .. container:: minor

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

.. exercise:: Zeigen Sie, das das folgende lineare Programm unbeschränkt ist.

    .. math::

        \begin{array}{rrcrcl}
        \text{maximiere} & x_1 & - & x_2 & \\
        \text{unter den Nebenbedingungen} \\
        & -2x_1 & +   &  x_2 & \leq & -1 \\
        &  -x_1 & -   & 2x_2 & \leq & -2 \\
        &  x_1, & x_2 &      & \geq & 0
        \end{array}

    .. solution::
        :pwd: Grenzen waren gestern

        Durch 

        .. image:: images/lp-exercise-solution-unbounded.svg
            :align: center
            :width: 500px









.. class:: new-section transition-move-to-top

Mixed-Integer-Programmierung
--------------------------------------------------------


Lösen von linearen Optimierungsproblemen bei denen einige (oder alle) Variablen ganzzahlig sind
---------------------------------------------------------------------------------------------------------

.. image:: images/lp-integer-programming.svg
    :align: center
    :height: 900px


.. supplemental::

    Wenn alle Variablen ganzzahlig sind, sprechen wir von einem reinen Ganzzahligen-Programm (:eng:`Integer Programming`). Wenn nur einige Variablen ganzzahlig sind, sprechen wir von einem gemischten Ganzzahligen-Programm.



Binärvariablen oder ganzzahlige Variablen?
---------------------------------------------

.. rubric:: Sudokus lösen

.. container:: two-columns

   .. container:: column no-separator

        .. image:: images/sudoku.svg
            :align: center
            :height: 400px

   .. container:: column

        **Naiver Ansatz**

        Wir verwenden :math:`81` Integer Variablen :math:`1 ≤ y_i ≤ 9`.

        .. incremental::

            Und jetzt? 

.. container:: incremental

    .. rubric:: Faustregel

    • Verwenden Sie allgemeine Ganzzahlen (Integers), wenn sie tatsächliche Mengen darstellen und die Reihenfolge wichtig ist!
    • Verwenden Sie Binärzahlen (0,1), wenn die Ganzzahlen konzeptuell nur „einige verschiedene Werte“ darstellen!



.. class:: new-section transition-move-to-top

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

.. stack::

    .. layer:: 

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
            

    .. layer:: incremental

        :Herausforderung:
            Wir müssen die Nebenbedingungen formulieren, die sicherstellen, dass die Variablen die Werte 0 bis 9 annehmen und dass keine Ziffer doppelt vorkommt. 
        
        .. class:: incremental
            
        :Problem: Es ist nicht direkt möglich eine mathematische Formulierung zu finden, die die Nebenbedingungen beschreibt. 

        .. class:: incremental            

        :Lösungsansatz (häufiger Ansatz bei „\ *Set-Partitioning-Problems*\ “):
            - Jeder Variablen ([S, E, N, D, M, O, R, Y]) werden jeweils 10 binäre Variablen zugewiesen, die den Wert 0 oder 1 annehmen, wenn die Variable den entsprechenden Wert hat.

    .. layer:: incremental

        .. rubric:: Nebenbedingungen
        
        - Jede Variable hat genau einen Wert
        - Keine Ziffer darf doppelt vorkommen
        
        .. math::  
            :class: smaller incremental

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


    .. layer:: incremental

        .. rubric:: „Optimierungsziel“

        .. math::
            :class: far-smaller incremental

            \begin{array}{r}
                \displaystyle\sum_{i=0}^{9} i \cdot S_i  \times 1000 + \sum_{i=0}^{9} i \cdot E_i  \times 100 + \sum_{i=0}^{9} i \cdot N_i  \times 10 + \sum_{i=0}^{9} i \cdot D_i  \times 1 & + \\
                \displaystyle\sum_{i=0}^{9} i \cdot M_i  \times 1000 + \sum_{i=0}^{9} i \cdot O_i  \times 100 + \sum_{i=0}^{9} i \cdot R_i  \times 10 + \sum_{i=0}^{9} i \cdot E_i  \times 1 & = \\
                \displaystyle\sum_{i=0}^{9} i \cdot M_i  \times 10000 + \sum_{i=0}^{9} i \cdot O_i  \times 1000 + \sum_{i=0}^{9} i \cdot N_i  \times 100 + \sum_{i=0}^{9} i \cdot E_i  \times 10 + \sum_{i=0}^{9} i \cdot Y_i  \times 1 & 
            \end{array}
            
        

    .. layer:: incremental

        Umsetzung in Python mit Hilfe von `PuLP <https://coin-or.github.io/pulp/>`

        .. rubric:: Imports 

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: far-smaller copy-to-clipboard
            :end-before: VALS

        .. rubric:: Variablen 

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: far-smaller copy-to-clipboard
            :start-after: )
            :end-before: # Nebenbedingungen

    .. layer:: incremental

        .. rubric:: Nebenbedingungen

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: far-smaller copy-to-clipboard
            :start-after: # Nebenbedingungen
            :end-before: # Ziel

    .. layer:: incremental

        .. rubric:: „Optimierungsziel“ 

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: far-smaller copy-to-clipboard
            :start-after: # Ziel
            :end-before: # Lösung

    .. layer:: incremental

        .. rubric:: Lösung berechnen lassen

        .. include:: code/send_more_money.py
            :code: python
            :number-lines:
            :class: far-smaller copy-to-clipboard
            :start-after: # Lösung berechnen

        .. rubric:: Ausgabe

        ::

            S=8; E=3; N=2; D=4; M=0; O=9; R=1; Y=7

        .. container::  incremental box-shadow padding-1em far-smaller rounded-corners margin-top-1em

            Code: |send_more_money.py| 

.. supplemental::
 
    Eine Formulierung wie :math:`28 \leq S + E + N + D + M + O + R + Y \leq 45`, um sicherzustellen, dass die Variablen nicht :math:`0` sind stellt leider nicht die gewünschte Nebenbedingung sicher, dass jeder Wert nur einmal vergeben wird (:math:`0 + 1+ 2 + 3 + 4 + 5 + 6 + 7 = 28` und :math:`0 + 1+ 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 45`).

    Eine Lösung mit obiger Nebenbedingung wäre zum Beispiel:

    ::

            S=0 E=8 N=9 D=0
            M=0 O=0 R=9 E=8
        M=0 O=0 N=9 E=8 Y=8
    



.. class:: integrated-exercise transition-move-to-top

Übung
--------------------------------------------------------

.. exercise:: Gruppenzuteilung

    Finden Sie eine sehr gute Aufteilung von Personen (Studierenden) auf eine feste Anzahl an Gruppen, basierend auf den Präferenzen der Personen. Nutzen Sie dazu Mixed-Integer-Programmierung.Im Template ist eine initiale Aufgabenstellung hinterlegt, die es zu lösen gilt: Verteilung von 16 Studierenden auf 4 Gruppen inkl. Bewertungsmatrix :minor:`(jeder Studierende hat jeden anderen mit Werten von 1 bis 10 bewertet)`. Ggf. ist die Funktion `pulp.allcombinations` beim Modellieren hilfreich.

    .. container:: slightly-more-smaller rounded-corners box-shadow padding-1em

        |group_assignment_template.py|


    .. solution::
        :pwd: ALLE Kombinationen bewerten

        *Ein Lösungsvorschlag*

        .. include:: code/group_assignment.py
            :code: python
            :number-lines:
            :class: smaller

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
- Studiere verfügbare Bibliotheken zum Lösen entsprechender Probleme (PuLP, `Gurobi <https://www.gurobi.com/>`__, `CPLEX <https://www.ibm.com/analytics/cplex-optimizer>`__, ...)