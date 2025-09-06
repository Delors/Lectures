.. meta::
    :version: renaissance
    :lang: de
    :author: Michael Eichberg
    :keywords: "Komplexität", "Algorithmen"
    :description lang=de: Theoretische Informatik - Komplexität und Algorithmen
    :id: lecture-theo-algo-komplexitaet
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs

.. |group_assignment_template.py| source:: code/group_assignment_template.py
            :path: relative
            :prefix: https://delors.github.io/

.. |GroupAssignmentTemplate.java| source:: code/GroupAssignmentTemplate.java
            :path: relative
            :prefix: https://delors.github.io/



Komplexität und Algorithmen
====================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 2.0.3

.. container:: peripheral

    :Quelle:
        Die Folien sind teilweise inspiriert von oder basierend auf Lehrmaterial von Prof. Dr. Ritterbusch, Prof. Dr. Baumgart oder Prof. Dr. Albers.

.. supplemental::

    :Folien:

        |html-source|

        |pdf-source|


    :Kontrollfragen:

        .. source:: kontrollfragen.de.rst
            :path: relative
            :prefix: https://delors.github.io/
            :suffix: .html

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-move-to-top

Einführung - Landau’sche O-Notation
--------------------------------------------------------


Berechnungskomplexität
----------------------

.. deck::

    .. card::

        Analyse des Aufwands zur Berechnung von Ergebnissen ist wichtig ...

        .. class:: incremental-list

        - im Design,
        - in der Auswahl
        - und der Verwendung von Algorithmen.

    .. card::

        Für relevante Algorithmen und Eingangsdaten können Vorhersagen getroffen werden:

        .. class:: incremental-list list-with-explanations

        - Um Zusammenhänge sind zwischen Eingangsdaten und Aufwand zu finden.
        - Aufwand kann Rechenzeit, Speicherbedarf oder auch Komponentennutzung sein.

          **Der Rechenaufwand ist häufig zentral** und wird hier betrachtet, die Verfahren sind aber auch für weitere Ressourcen anwendbar.

    .. card::

        .. rubric:: Die Vorhersagen erfolgen über asymptotische Schätzungen

        - mit Hilfe der Infinitesimalrechnung,
        - durch Kategorisierung im Sinne des Wachstumsverhaltens,
        - damit ist oft keine exakte Vorhersage möglich.

    .. card::

        .. rubric:: Unterschiedliche Systeme sind unterschiedlich schnell, relativ dazu wird es interessant.

        Im Folgenden geht es um:

        - die Beschreibung des asymptotischen Wachstumsverhaltens
        - die Analyse von iterativen Algorithmen
        - die Analyse von rekursiv teilenden Algorithmen

.. supplemental::

    Die Infinitesimalrechnung bezeichnet die Differenzial- und Integralrechnung. Es wird mit unendlich kleinen Größen gerechnet.


.. class:: new-subsection

Entwurf von Algorithmen: Dynamische Programmierung
--------------------------------------------------------

.. supplemental::

    Der folgende Abschnitt behandelt die dynamische Programmierung, um ein Problem effizient zu lösen. Er zeigt gleichzeitig wie die Wahl des Algorithmus und der Implementierung die Laufzeit dramatisch beeinflussen kann.



.. class:: exercises

Übung
------------------------------------------

.. exercise::  Berechnung der Fibonacci-Zahlen

    Implementieren Sie eine **rekursive Funktion**, die die :math:`n`-te Fibonacci-Zahl berechnet!

    .. hint::

        Die Fibonacci-Zahlen sind definiert durch die Rekursionsformel:

        :math:`F(n) = F(n-1) + F(n-2)`

        mit den Anfangswerten:

        :math:`F(0) = 0` und :math:`F(1) = 1`.

    Bis zu welchem :math:`n` können Sie die Fibonacci-Zahlen in vernünftiger Zeit berechnen (d. h. < 10 Sekunden) ?

    .. solution::
        :pwd: das ist einfach gewesen

        Je nach Rechner und Laufzeitumgebung dürfte zwischen fib(35) und fib(45) die Grenze liegen, wenn man innerhalb von 10 Sekunden bleiben möchte.

        .. rubric:: |python-icon| Lösung in Python:

        .. code:: Python
            :class: copy-to-clipboard
            :number-lines:

            def fib(n):
                 if n == 0:
                     return 0
                 elif n == 1:
                     return 1
                 else :
                     return fib(n-1) + fib(n-2)

        .. rubric:: |java-icon| Lösung in Java >23

        (Ggf. mit --enable-preview zu starten!)

        .. code:: Java
            :number-lines:
            :class: copy-to-clipboard

            int fib(int n) {
                return switch(n){
                    case 0 -> 0;
                    case 1 -> 1;
                    default -> fib(n-1) + fib(n-2);
                };
            }



Technik der dynamischen Programmierung
---------------------------------------

:Rekursiver Ansatz: Lösen eines Problems durch Lösen mehrerer kleinerer Teilprobleme, aus denen sich die Lösung für das Ausgangsproblem zusammensetzt.
:Phänomen: Mehrfachberechnungen von Lösungen
:Methode: Speichern einmal berechneter Lösungen (in einer Tabelle) für spätere Zugriffe.



Beispiel: Berechnung der Fibonacci-Zahlen (rekursiv)
--------------------------------------------------------------------------------

.. definition::

    :math:`F(0) = 0\qquad\qquad F(1) = 1 \qquad\qquad F(n) = F(n-1) + F(n-2)`

.. deck:: incremental

    .. card::

        .. warning::

            Die Berechnung der Fibonacci-Zahlen mit Hilfe einer naiven rekursiven Funktion ist sehr ineffizient.

    .. card::

        :math:`F(n)` als stehende Formel:

        .. math::

            F(n) = \left[{ 1 \over \sqrt{5} } (1.618 \ldots)^n  \right]


    .. card::

        .. rubric:: Aufrufbaum

        .. image:: images/fib.svg
            :align: center




Vorgehen beim dynamischen Programmieren
----------------------------------------

1. Rekursive Beschreibung des Problems P

.. class:: incremental-list

2. Bestimmung einer Menge :math:`T`, die alle Teilprobleme von :math:`P` enthält, auf die bei der Lösung von :math:`P` – auch in tieferen Rekursionsstufen – zurückgegriffen wird.
3. Bestimmung einer Reihenfolge :math:`T_0 , \ldots, T_k` der Probleme in :math:`T`, so dass bei der Lösung von :math:`T_i` nur auf Probleme :math:`T_j`  mit :math:`j < i` zurückgegriffen wird.
4. Sukzessive Berechnung und Speicherung von Lösungen für :math:`T_0 ,...,T_k`.



Beispiel: Berechnung der Fibonacci-Zahlen mit dynamischer Programmierung
--------------------------------------------------------------------------------

.. deck::

    .. card::

        Rekursive Definition der Fibonacci-Zahlen nach gegebener Gleichung:

        .. class:: incremental-list

        1. :math:`T = { f(0),..., f(n-1)}`
        2. :math:`T_i = f(i), i = 0,...,n – 1`
        3. Berechnung von :math:`fib(i)` benötigt von den früheren Problemen nur die zwei letzten Teillösungen :math:`fib(i – 1)` und :math:`fib(i – 2)` für :math:`i ≥ 2`.

    .. card::

        .. rubric:: Lösung mit linearer Laufzeit und konstantem Speicherbedarf

        .. code:: pascal
            :number-lines:
            :class: copy-to-clipboard

            procedure fib (n : integer) : integer
                f_n_m2 := 0
                f_n_m1 := 1
                for k := 2 to n do
                    f_n := f_n_m1 + f_n_m2
                    f_n_m2 := f_n_m1
                    f_n_m1 := f_n
                if n ≤ 1 then return n
                else          return f_n

    .. card::

        .. rubric:: Lösung mit Memoisierung (:eng:`Memoization`)

        Berechne jeden Wert genau einmal, speichere ihn in einem Array F[0...n]:

        .. code:: pascal
            :number-lines:
            :class: copy-to-clipboard

            procedure fib (n : integer) : integer
                F[0] := 0
                F[1] := 1
                for i := 2 to n do
                    F[i] := ∞ // Initialisierung
                return lookupfib(n)

            procedure lookupfib (n : integer) : integer
                if F[n] = ∞ then
                    F[n] := lookupfib(n-1) + lookupfib(n-2)
                return F[n]



.. class:: exercises

Übung
------------------------------------------

.. exercise:: Fibonacci-Zahl effizient berechnen

    Implementieren Sie den Pseudocode der ersten Lösung zur Berechnung der Fibonacci-Zahlen. Verwenden Sie :java:`BigInteger`\ s.

    Bis zur welcher Fibonacci-Zahl können Sie die Berechnung nun durchführen?

    .. solution::
        :pwd: das ist schnell

        .. rubric:: |java-icon| Lösung

        Mit den Standarddatentypen ist in Java keine vernünftige Berechnung der Fibonacci-Zahlen möglich. Wir verwenden deswegen :java:`BigInteger`\ s.

        .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            import java.math.BigInteger;

            BigInteger fib(int n) {
                BigInteger f_n = null;
                var f_n_m2 = BigInteger.ZERO;
                var f_n_m1 = BigInteger.ONE;
                for(int i = 2; i < n+1; i++) {
                    f_n = f_n_m2.add(f_n_m1);
                    f_n_m2 = f_n_m1;
                    f_n_m1 = f_n;
                }
                return n <= 1 ? BigInteger.valueOf(n) : f_n;
            }

        .. rubric:: |python-icon| Lösung

        In Python kann die Berechnung (Python 3.13 - Standardinstallation) bis :java:`fib(20577)` durchgeführt werden, wenn das Ergebnis direkt angezeigt werden soll und keine weiteren Einstellungen verändert werden sollen.

        .. code:: python
            :class: copy-to-clipboard
            :number-lines:

            def fib (n) :
                 f_n_m2 = 0
                 f_n_m1 = 1
                 for k in range( 2, n+1):
                     f_n = f_n_m1 + f_n_m2
                     f_n_m2 = f_n_m1
                     f_n_m1 = f_n
                 if n <= 1:
                     return n
                 else:
                     return f_n



.. class:: new-subsection

Laufzeiten von Algorithmen
--------------------------------------------------------


Folgen
------

Im Allgemeinen werden Laufzeiten oder Aufwände in Abhängigkeit von einer Eingangsgröße als Folge beschrieben:

.. definition::

    Eine Folge (:math:`a_n`) ist eine Abbildung, die jedem :math:`n \in \mathbb{N}` ein :math:`a_n` zuweist.

.. class:: incremental-list

- .. class:: columns

    * Definition über Folgenglieder
    * .. example::

            (:math:`a_n`) : :math:`a_1 = 2, a_2 = 3, a_3 = 7, a_4 = 11, ...`

- .. class:: columns

    - Rekursive Definition
    - .. example::

        (:math:`c_n`) : :math:`c_1 = 1, c_2 = 1, c_{n+2} = c_n + c_{n+1}` für :math:`n \in \mathbb(N)`

- .. class:: columns

    - Explizite Definition
    - .. example::

        (:math:`b_n`) : :math:`b_n = n^2` für :math:`n \in \mathbb{N}`

.. supplemental::

    Eine rekursive Definition ist eine Definition, die sich auf sich selbst bezieht. Eine solche Definition ist häufiger schwieriger zu analysieren.

    Die explizite Definition ist eine direkte Zuweisung und meist die beste Wahl.



Folgen und Laufzeiten
----------------------

- Die explizite Definition von Laufzeiten ist zur Auswertung vorzuziehen.
- Die rekursive Definition tritt oft bei rekursiven Verfahren auf, und sollte dann in eine explizite Definition umgerechnet werden.

.. container:: incremental

    .. rubric:: Berechnung der Anzahl der Schritte zum Lösen der Türme von Hanoi.

    .. image:: images/hanoi.svg
        :align: center

    .. container:: text-align-center peripheral

        Türme von Hanoi mit 3 Scheiben.

.. supplemental::

    .. rubric:: Die Türme von Hanoi (ChatGPT)

    Die Türme von Hanoi sind ein klassisches mathematisches Puzzle. Es besteht aus drei Stäben und einer bestimmten Anzahl von unterschiedlich großen Scheiben, die anfangs alle in absteigender Reihenfolge auf einem Stab gestapelt sind – der größte unten und der kleinste oben.

    Das Ziel des Spiels ist es, alle Scheiben auf einen anderen Stab zu bewegen, wobei folgende Regeln gelten:

    - Es darf immer nur eine Scheibe auf einmal bewegt werden.
    - Eine größere Scheibe darf nie auf einer kleineren liegen.
    - Alle Scheiben müssen auf den dritten Stab bewegt werden, indem sie über den mittleren Stab verschoben werden.



Laufzeit der Lösung der Türme von Hanoi
----------------------------------------

.. deck::

    .. card::

        Für die Lösung sind für jeden Ring :math:`n` die folgenden :math:`a_n` Schritte erforderlich:

        .. class:: incremental-list

        1. Alle :math:`n−1` kleineren Ringe über Ring :math:`n` müssen mit :math:`a_{n−1}` Schritten auf den Hilfsstab.
        2. Der Ring :math:`n` kommt auf den Zielstab mit einem Schritt.
        3. Alle :math:`n−1` Ringe vom Hilfsstab müssen mit :math:`a_{n−1}` Schritten auf den Zielstab.

        .. container:: incremental

            Bei nur einem Ring ist :math:`a_1 = 1` und sonst :math:`a_n = a_{n−1} + 1+ a_{n−1} = 2a_{n−1} + 1`.

        .. container:: incremental

            Also:
            :math:`a_1 = 1`, :math:`a_2 = 2·1+ 1= 3`, :math:`a_3 = 2·3+ 1= 7`, :math:`a_4 = 2·7+ 1= 15`, ...

        .. container:: incremental

            Damit liegt nahe, dass der Aufwand (:math:`1,3,7,15,...`) dem Zusammenhang :math:`a_n = 2^n−1` entspricht.

    .. card::

        .. proof::

            .. rubric:: Beweis durch vollständige Induktion

            - Induktionsanfang :math:`n = 1`: :math:`a_1 = 2^n -1 =  2^1−1 = 1`
            - Induktionsvoraussetzung: :math:`a_{n-1} = 2^{n-1}−1` und :math:`a_{n} = 2a_{n-1} + 1`
            - Induktionsschritt (:math:`n-1 \rightarrow n`):

              :math:`a_{n} = 2·(2^{n-1}−1)+1`

              .. container:: incremental

                :math:`\quad\, = 2^{n}−2+1`

              .. container:: incremental

                :math:`\quad\, = 2^{n}−1`

              .. container:: incremental

                Damit ist die Vermutung bestätigt.


.. class:: new-subsection repetition

Konvergenz von Folgen
--------------------------------------------------------




Eigenschaften von Folgen - Konvergenz
----------------------------------------

.. admonition:: Definition

    - Eine Folge (:math:`a_n`) ist konvergent zum Grenzwert :math:`a`, wenn es zu jeder Zahl :math:`ε > 0` ein :math:`N \in \mathbb{N}` gibt, so dass :math:`|a_n−a|<ε` für alle :math:`n > N` gilt.

      Dies wird dann:

      .. math::

        a_n \xrightarrow{n→∞} a , a_n \rightarrow a\; \text{oder}\; \lim_{n → ∞} a_n = a

      geschrieben.

    - Eine Folge ist divergent, wenn es keinen Grenzwert gibt.



Eigenschaften von Folgen - Beispiel für Konvergenz
--------------------------------------------------

.. example::

    Betrachten wir die Folge (:math:`a_n`) mit :math:`a_n = {(−1)^n \over n} + 2`, :math:`n \in \mathbb{N}`:

    .. deck::

        .. card::

            .. rubric:: Entwicklung der Folge

            :math:`a_1 = -1 + 2 = 1`

            :math:`a_2 = 0.5 + 2 = 2.5`

            :math:`a_3 = -0.33.. + 2 \approx 1.67`

            :math:`a_4 = 0.25 + 2 = 2.25`

            ...


        .. card::

            Die Folge konvergiert zu 2, da für ein gegebenes :math:`ε > 0` ein :math:`N` existiert so dass :math:`|a_n−a|<ε`:

            .. math::

                |a_n−a|= |{ (−1)^n \over n} + 2 − 2| = |{(−1)^n \over n}| = {1 \over n} < ε

            wenn :math:`n > {1 \over ε}` ist.

            D. h. :math:`a_n \rightarrow 2` oder :math:`lim_{n→∞} a_n = 2`



Konvergenz von Folgen - Rechenregeln
-------------------------------------

.. admonition:: Satz

    Die beiden Folgen (:math:`a_n`) und (:math:`b_n`) seien konvergent :math:`a_n →a`, :math:`b_n →b` und :math:`λ\in\mathbb{C}`, sowie :math:`p,q \in \mathbb{N}` . Dann gilt:

    .. math::

        \begin{array}{rcl}
            lim_{n→∞} λa_n & = & λa \\
            lim_{n→∞}(a_n ± b_n) & = & a ± b \\
            lim_{n→∞}(a_n·b_n) & = & a·b \\
            lim_{n→∞} {a_n \over b_n} & = & {a \over b},\; \text{für}\; b ≠ 0, b_n ≠ 0 \\
            lim_{n→∞} a^{p/q}_n & = & a^{p/q} , \text{wenn}\; a^{p/q}\; \text{existiert} \\
        \end{array}


Konvergenz von Folgen - wichtige Grenzwerte
--------------------------------------------

.. math::

    \begin{array}{rcll}
        \lim_{{n \to \infty}} q^n & = & 0 & \text{wenn} \ |q| < 1 \\
        \lim_{{n \to \infty}} q^n & = & \infty & \text{wenn} \ q > 1 \\
        \lim_{{n \to \infty}} {q^n \over n!} & = & 0 & \text{für} \ q \in \mathbb{C} \\
        \lim_{{n \to \infty}} \sqrt[n]{a} & = &1 & \text{wenn} \ a > 0 \\
        \lim_{{n \to \infty}} \sqrt[n]{n} & = & 1 \\
        \lim_{{n \to \infty}} \sqrt[n]{n!} & = & \infty \\
    \end{array}



Konvergenz von Folgen - Beispiel
-----------------------------------------------------------------


Die Folge :math:`a_n = {n^2 + 1 \over n^3}` konvergiert gegen :incremental:`0`, da:

.. container:: incremental

    .. math::

        \lim_{{n \to \infty}} {n^2 + 1 \over n^3} = \lim_{{n \to \infty}} {n^3( 1/n + 1/n^3) \over n^3} = \lim_{{n \to \infty}} {( 1/n + 1/n^3) \over 1} = 0

.. class:: incremental

    Die Folge konvergiert gegen 0, da der Zähler gegen 0 strebt (:math:`\lim_{{n \to \infty}} {( 1/n)} = 0` und :math:`\lim_{{n \to \infty}} {( 1/n^3)} = 0`) und der Nenner konstant ist.

.. supplemental::

    Die allgemeine Vorgehensweise ist es, die größte Potenz im Zähler und Nenner zu finden und dann diese auszuklammern. Im zweiten Schritt kürzen wir dann. In diesem Fall ist es :math:`n^3`.

    D. h. das Ziel ist es den Ausdruck so umzuformen, dass der Grenzwert direkt abgelesen werden kann. Dies ist inbesondere dann der Fall, wenn :math:`n` nur noch im Nenner oder Zähler steht.



Analyse des asymptotischen Verhaltens
----------------------------------------

Wir möchten :math:`f(x) = \frac{\ln(x)}{x^{2/3}}` für :math:`x \to \infty` untersuchen.

.. deck::
    :class: incremental

    .. card::

        .. observation::

            1. Der Zähler, :math:`\ln(x)`, wächst gegen unendlich, aber sehr langsam im Vergleich zur Potenzfunktionen.
            2. Der Nenner, :math:`x^{2/3}`, wächst viel schneller als :math:`\ln(x)` für große :math:`x`.

            .. container:: incremental

                Es liegt somit ein unbestimmter Ausdruck vom Typ :math:`\frac{\infty}{\infty}` vor. Wir verwenden nun die Regel von L'Hôpital.

    .. card::

        .. rubric:: Anwendung von L'Hôpital

        .. container:: incremental

            .. math::

                \lim_{x \to \infty} \frac{\ln(x)}{x^{2/3}} = \lim_{x \to \infty} \frac{\frac{d}{dx}(\ln(x))}{\frac{d}{dx}(x^{2/3})} = \lim_{x \to \infty} \frac{\frac{1}{x}}{\frac{2}{3}x^{-1/3}}

        .. container:: incremental

            Das vereinfacht sich zu:

            .. math::

                = \lim_{x \to \infty} \frac{1}{x} \cdot \frac{3}{2}x^{1/3} = \lim_{x \to \infty} \frac{3}{2} \cdot \frac{1}{x^{2/3}} = 0

.. supplemental::

    Die **Regel von L'Hôpital** ermöglicht es Grenzwerte von Ausdrücken des Typs :math:`\frac{0}{0}` oder :math:`\frac{\infty}{\infty}` zu berechnen. In diesem Fall nehmen wir die Ableitungen des Zählers und des Nenners.

    Die Regel besagt:

    Falls :math:`\lim_{x \to a} \frac{f(x)}{g(x)}` den unbestimmten Ausdruck :math:`\frac{0}{0}` oder :math:`\frac{\infty}{\infty}` ergibt, dann gilt:

    .. math::

        \lim_{x \to a} \frac{f(x)}{g(x)} = \lim_{x \to a} \frac{f'(x)}{g'(x)},


    sofern der Grenzwert auf der rechten Seite existiert oder unendlich ist.



.. class:: exercises

Übung - Konvergenz von einfachen Folgen
------------------------------------------

.. exercise:: Erste Folge - zum Aufwärmen

    Zeigen Sie, dass die Folge :math:`a_n = {n^2 \over n^2 + 1}` konvergiert und bestimmen Sie den Grenzwert.

    .. solution::
        :pwd: das ist wirklich so

        Der Grenzwert der Folge :math:`a_n` ist 1, da:

        .. math::

            \lim_{{n \to \infty}} {n^2 \over n^2 + 1} = \lim_{{n \to \infty}} {1 \over 1 + {1 \over n^2}} = 1

.. exercise:: Zweite Folge

    Bestimmen Sie den Grenzwert der Folge, wenn er denn existiert: :math:`b_n =  {1 − n + n^2 \over n(n+1)}`.

    .. solution::
        :pwd: so und nur so

        Nach Kürzen der höchsten Potenz kann der Grenzwert für die einzelnen Terme bestimmt werden:

        .. math::

            \begin{array}{rl}
                \lim_{n→∞} b_n = & \lim_{n→∞} {1−n + n^2 \over n(n+1)} \\
                = & \lim_{n→∞} {n^2 - n + 1 \over n^2 + n} \\
                = & \lim_{n→∞} {n^2 (1 - 1/n + 1/n^2) \over n^2( 1 + 1/n)} \\
                = & \lim_{n→∞} {1 - 1/n + 1/n^2 \over 1 + 1/n} \\
                = & 1
            \end{array}



.. class:: exercises

Übung - Konvergenz von Folgen
------------------------------------------

.. hint::

    Die Binomischen Formeln sind ggf. hilfreich.


.. exercise:: Folge mit Wurzel

    Bestimmen Sie den Grenzwert :math:`\lim_{n→∞} \sqrt{n^2 + n} - n`.

    .. class:: minor

    Hier könnte die dritte binomische Formel (:math:`(a−b)(a + b) = a^2 −b^2`) hilfreich sein.


    .. solution::
        :pwd: da sind sie wieder

        Um die Wurzel loszuwerden betrachten wir :math:`\sqrt{n^2 + n}` als :math:`a` und :math:`n` als :math:`b` bzgl. der 3. binomischen Formel. Wir verwenden jetzt den entsprechenden Term: :math:`\sqrt{n^2 + n} + n` (*Achtung: Vorzeichen beachten, damit wir die dritte binomische Formel anwenden können*). Diesen wenden wir auf den Zähler  und Nenner (hier implizit :math:`1`) an:

        .. math::

            \lim_{n→∞} {(\sqrt{n^2 + n} - n) \cdot (\sqrt{n^2 + n} + n) \over \sqrt{n^2 + n} + n }

        Anwendung der dritten Binomischen Formel auf den Zähler:

        .. math::

            \lim_{n→∞} {n^2 + n - n^2 \over \sqrt{n^2 + n} + n }

            \lim_{n→∞} { n \over \sqrt{n^2 + n} + n }

        Ausklammern der höchsten Potenz:

        .. math::

            \lim_{n→∞} {n \over n \left(\sqrt{1 + 1/n} +1 \right) }

            \lim_{n→∞} {1 \over \sqrt{1 + 1/n} +1  } = {1 \over 2}

        (Da gilt: :math:`\lim_{n→∞} \sqrt{1 + 1/n} = 1`)

.. supplemental::

    Um eine Potenz aus einer Wurzel zu bekommen, hilft ggf. das Wurzelgesetz :math:`\sqrt{a} \cdot \sqrt{b} = \sqrt{a \cdot b}`.

    Beispiel: :math:`\sqrt{x^4 + x^2} = \sqrt{x^4 (1 + 1/x ^2)} = \sqrt{x^4} \cdot \sqrt{(1 + 1/x ^2)} = x^2 \cdot \sqrt{(1 + 1/x ^2)}`.


.. exercise:: Folge mit mehreren Termen

    Berechnen Sie den Grenzwert Folge  :math:`b_n = {n^2 -1 \over n + 3 } - {n^2 + 1 \over n - 1}` falls er existiert.

    .. solution::
        :pwd: ausmultiplizieren_ist_der_Schluessel

        Vorgehen: Auf einem gemeinsamen Nenner bringen und dann die höchste Potenz ausklammern.

        .. math::

            \begin{array}{rl}
                \lim_{n→∞} {n^2 -1 \over n + 3 } - {n^2 + 1 \over n - 1} = & \lim_{n→∞} {n^2 -1 \over n + 3 } \cdot {n - 1 \over n - 1} - {n^2 + 1 \over n - 1} \cdot {n + 3 \over n + 3} \\
                = & \lim_{n→∞} {n^3 - n - n^2 + 1 - n^3 - 3n^2 - n - 3 \over n^2 + 2n - 3} \\
                = & \lim_{n→∞} { -4n^2 - 2n -2 \over n^2 + 2n - 3} \\
                = & \lim_{n→∞} { n^2 (-4 - 2/n -2/n^2) \over n^2 (1 + 2/n - 3/n^2)} \\
                = & {-4 \over 1} \\
                = & -4
            \end{array}


.. exercise:: Zwei Wurzeln

    Bestimmen Sie den Grenzwert :math:`\lim_{n→∞} \sqrt{n^2 + 1} - \sqrt{n^2 + 4n}`.

    .. solution::
        :pwd: Binomische_Teil2

        Auch hier helfen die Binomischen Formeln:

        .. math::

            \begin{array}{rl}
            \lim_{n→∞} \sqrt{n^2 + 1} - \sqrt{n^2 + 4n} = & \lim_{n→∞} {(\sqrt{n^2 + 1} - \sqrt{n^2 + 4n}) \cdot (\sqrt{n^2 + 1} + \sqrt{n^2 + 4n}) \over \sqrt{n^2 + 1} + \sqrt{n^2 + 4n}} \\
            = & \lim_{n→∞} {n^2 + 1 - n^2 - 4n \over \sqrt{n^2 + 1} + \sqrt{n^2 + 4n}} \\
            = & \lim_{n→∞} {1 - 4n \over \sqrt{n^2 + 1} + \sqrt{n^2 + 4n}} \\
            = & \lim_{n→∞} {1 - 4n \over n \cdot (\sqrt{1 + 1/n^2} + \sqrt{1 + 4/n})} \\
            = & \lim_{n→∞} {n(1/n - 4) \over n \cdot (\sqrt{1 + 1/n^2} + \sqrt{1 + 4/n})} \\
            = & \lim_{n→∞} {1/n - 4 \over \sqrt{1 + 1/n^2} + \sqrt{1 + 4/n}} \\
            = & {-4 \over 1 + 1} = -2
            \end{array}

.. class:: new-subsection

Landau-Notation
--------------------------------------------------------


Asymptotische Abschätzung
--------------------------------------------------------

.. admonition:: Definition

    .. rubric:: Landau-Notation

    Folgenden Mengen von Funktionen können asymptotisch von :math:`g(n)`
    ...

    .. class:: incremental-list

    - nach oben abgeschätzt werden, :math:`\mathcal{O}(g) := \{f : \mathbb{N} →\mathbb{R}_{≥0} | \lim_{n→∞} {f(n) \over g(n)} < ∞\}`
    - nach unten abgeschätzt werden, :math:`Ω(g) := \{f : \mathbb{N} →\mathbb{R}_{≥0} | \lim_{n→∞} {f(n) \over g (n)} > 0\}`
    - in gleicher Ordnung abgeschätzt werden, :math:`Θ(g) := \{f : \mathbb{N} →\mathbb{R}_{≥0} | \lim_{n→∞} {f(n) \over g(n)} = C \in \mathbb{R}_{>0}\}`


.. container:: incremental

    Es gilt der folgende Zusammenhang für die Mengen :math:`\mathcal{O}(g)`\ [#]_, :math:`Ω(g)` und :math:`Θ(g)`:

    .. math::

        Θ(g) = \mathcal{O}(g) ∩ Ω(g)

    .. [#] Im Folgenden verwenden wir einfach :math:`O` statt :math:`\mathcal{O}`.


.. supplemental::

    Wenn eine Funktion :math:`f` in der Menge :math:`O(g)` (d. h. :math:`f \in O(g)`) ist, dann wächst die Funktion :math:`g` mindestens genauso schnell wie die Funktion :math:`f`. Wächst :math:`g(n)` asymptotisch schneller, dann ist :math:`f(n)/g(n)` für :math:`n \to \infty` in diesem Falle 0; wachsen beide gleich schnell, dann ist es eine Konstante :math:`c`.

    Die Verwendung der O-Notation zur Beschreibung der Komplexität von Algorithmen wurde von Donald
    E. Knuth eingeführt.



Alternative Schreibweisen
----------------------------------------

.. container:: center-child-elements

    Insbesondere für die obere Abschätzung :math:`O(g)` gibt es eine alternative Schreibweise:

    .. math::

        f(n) ∈ O(g(n)) ⇔ ∃c_0, n_0 ∀n : n > n_0 ⇒ f (n) ≤ c_0· g(n)

    D. h. ab einem Wert :math:`n_0` liegt die Funktion :math:`f` unter dem :math:`c_0`-fachen der Funktion :math:`g`.

    Beispiel: :math:`f(n) = 4n + 7 ∈ O(n)`

    :math:`4n + 7 ≤ c_0· n ⇔ n· (4− c_0) ≤ −7`

    Wähle (exemplarisch): :math:`c_0 = 5` und :math:`n_0 = 7` sowie :math:`g(n) = n`.



Verstehen von Aufwandsklassen
----------------------------------------

.. image:: images/aufwandsklassen.svg
    :align: center

.. remark::
    :class: s-font-size-80

    Häufige Vergleichsfunktionen sind zum Beispiel Monome wie :math:`n^k` für :math:`k ∈ \mathbb{N}_0`.



Achtung bei asymptotischen Abschätzungen
----------------------------------------

Asymptotische Laufzeitabschätzungen können zu Missverständnissen führen:

.. class:: incremental-list

1. Asymptotische Abschätzungen werden nur für steigende Problemgrößen genauer, für kleine Problemstellungen liegt oft eine ganz andere Situation vor.
2. Asymptotisch nach oben abschätzende Aussagen mit :math:`O(g)`-Notation können die tatsächliche Laufzeit beliebig hoch überschätzen, auch wenn möglichst scharfe Abschätzungen erwünscht sein sollten, gibt es diese teilweise nicht in beliebiger Genauigkeit, oder sind nicht praktikabel.
3. Nur Abschätzungen von gleicher Ordnung :math:`Θ(g)` können direkt verglichen werden, oder wenn zusätzlich zu :math:`O(g)` auch :math:`Ω(h)` Abschätzungen vorliegen.



.. class:: exercises  transition-move-to-top

Übung
------------------------------------------

.. exercise:: Gegenseitige asymptotische Abschätzung I

    Bestimmen Sie welche Funktionen sich gegenseitig asymptotisch abschätzen:

    :math:`f_1(x) = \sqrt[3]{x},\; f_2(x) = e^{−1+ln\, x} , f_3(x) = {x \over ln(x) + 1}`.

    D. h. berechnen Sie:

    .. math::

        \lim_{x→∞} {f_1(x) \over f_2(x)}, \lim_{x→∞} {f_2(x) \over f_3(x)},\; \text{und ggf.}\; \lim_{x→∞} {f_1(x) \over f_3(x)}

    .. solution::
        :pwd: viel_zu-berechnen

        1. Aufgabe

        .. math::

            \lim_{x→∞} {f_1(x) \over f_2(x)} = \lim_{x→∞} {\sqrt[3]{x} \over e^{-1+ln\, x}} = \lim_{x→∞} {x^{1/3}  \over {e^{-1} \cdot e^{ln\,x}}}  = \lim_{x→∞} e \cdot x^{-2/3} = \lim_{x→∞} {e \over \sqrt[3]{x^2}} = 0

        2. Aufgabe

        .. math::

            \lim_{x→∞} {f_2(x) \over f_3(x)} = {e^{−1+ln\, x} \over {x \over ln(x) + 1}} = {e^{−1} \cdot e^{ln\, x} \over {x \over ln(x) + 1}} = { x (ln(x) + 1) \over x \cdot e} =  { ln(x) + 1 \over e} = \infty

        3. Aufgabe

        .. math::

            \begin{array}{rl}
                \lim_{x→∞} {f_1(x) \over f_3(x)} = \lim_{x→∞}  {\sqrt[3]{x} \over {x \over ln(x) + 1}} = & \lim_{x→∞} x^{1/3} \cdot x^{-1} \cdot (ln(x) + 1) \\
                = & \lim_{x→∞} x^{-2/3} \cdot (ln(x) + 1)
            \end{array}

        Sowohl Zähler als auch Nenner gehen gegen unendlich. Deswegen ist die Anwendung von L'Hôpital (bzw. L'Hospital) erforderlich! (D. h. getrennte Ableitung von Zähler und Nenner):

        .. math::

            = \lim_{x→∞} {{d \over dx} (ln(x) + 1)  \over {d \over dx}  (x^{2/3}) } = \lim_{x→∞} {{1 \over x} \over 2/3 \cdot x^{-1/3}} = \lim_{x→∞} x^{-1} \cdot x^{1/3} \cdot {3 \over 2} = \lim_{x→∞} {3 \over \sqrt[3]{x^2} \cdot 2} = 0

        Also ist :math:`f_1 \notin Θ(f_2)`, :math:`f_1 ∈ O(f_2)` und :math:`f_2 ∈ Ω(f_1)`.

        Also ist :math:`f_2 \notin Θ(f_3)`, :math:`f_2 ∈ Ω(f_3)` und :math:`f_3 ∈ O(f_2)`.

        Also ist :math:`f_1 \notin Θ(f_3)`, :math:`f_1 ∈ O(f_3)` und :math:`f_3 ∈ Ω(f_1)`.

.. supplemental::

    Denken Sie daran, dass die erste Ableitung von :math:`f(x) = ln(x)` die Funktion :math:`f'(x)= {1 \over x}` ist.



.. class:: exercises

Übung - Asymptotische Abschätzungen
------------------------------------------

.. exercise:: Gegenseitige asymptotische Abschätzung II

    Vergleichen Sie: :math:`f_1(x) = e^{2ln(x)+1}` und :math:`f_2(x) = {x^3+1 \over x}`.

    .. solution::
        :pwd: ganz_und_gar-vergleichbar

        **1. Vereinfachungen**

        .. math::

            e^{2ln(x)+1} = e \cdot x^2

            {x^3+1 \over x} = x^2 + {1 \over x}

        **2. Vergleich**

        .. math::

            \begin{array}{rl}
                \lim_{x→∞} {f_1(x) \over f_2(x)} = & \lim_{x→∞} {e^{2ln(x)+1} \over {x^3+1 \over x}} \\
                =  & \lim_{x→∞} {e^{2ln(x)+1} \cdot x \over x^3+1} \\
                 = & \lim_{x→∞} {e \cdot x^2 \over x^2+1/x} \\
                 = & \lim_{x→∞} {e \cdot x^2 \over x^2\cdot(1+1/x^3)} \\
                 = & \lim_{x→∞} {e \over 1+1/x^3} = e
            \end{array}

        Somit sind die Funktionen :math:`f_1` und :math:`f_2` asymptotisch äquivalent.

.. exercise:: Gegenseitige asymptotische Abschätzung III

    Vergleichen Sie: :math:`f_1(x) = 2^{1+2x}` und :math:`f_2(x) = 4^x + 2^x`.

    .. solution::
        :pwd: auch+ganz_und_gar-vergleichbar

        **1. Umstellung**

        .. math::

            2^{1+2x} = 2 \cdot 2^{2^{x}} = 2 \cdot 4^x

        **1. Vergleich**

        .. math::

            \lim_{x→∞} {2 \cdot 4^x \over  4^x + 2^x} = \lim_{x→∞} {2 \cdot 4^x \over  4^x \cdot (1 + 1/2^x)} = 2


        Somit sind die Funktionen :math:`f_1` und :math:`f_2` asymptotisch äquivalent.



.. class:: new-section

Algorithmische Komplexität
--------------------------------------------------------


Algorithmen
----------------------------------------

Algorithmen sind Verfahren, die gegebene Ausprägungen von Problemen in endlich vielen Schritten lösen können.

.. container:: incremental

    Dabei muss jeder Schritt

    - ausführbar und
    - reproduzierbar sein.

.. container:: incremental

    Es gibt aber oft viele Methoden die Probleme zu lösen:

    - Daher ist es wichtig, Eigenschaften von Algorithmen zu analysieren!
    - Insbesondere z.B.
    - Zeitaufwand und
    - Speicherbedarf
    - in Abhängigkeit von der Problemgröße.


.. supplemental::

    .. rubric:: Problemumfang (Problemgröße) n

    Konkrete Beispiele für Problemgrößen:

    - Konkreter Wert von :math:`n`: :math:`f (n)`
    - Stellenanzahl des Eingabewertes (der Eingabewerte) → :math:`f (z_1z_2 . . . z_n) (z_i ∈ { 0, . . . , 9 })`
    - Anzahl der Eingabewerte: :math:`f(x_1, x_2, . . . , x_n)`



Aufwand - Übersicht
----------------------------------------

.. image:: images/aufwand.svg
    :align: center




Komplexität
----------------------------------------

Wir unterscheiden:

:Komplexität eines Algorithmus:

    Asymptotischer Aufwand (n → ∞) der Implementierung des Algorithmus.

:Komplexität eines Problems:

    Minimale Komplexität (irgend) eines Algorithmus zur Lösung des Problems.



Algorithmen - Zeitaufwand
----------------------------------------


Tatsächlicher Zeitaufwand hängt vom ausführenden Rechnersystem ab.

.. class:: incremental-list

- Beeindruckende Entwicklung der Rechentechnik in den letzten Jahrzehnten
- Größere Probleme können gelöst werden

.. warning::
    :class: incremental

    Langsamere Algorithmen bleiben langsamer auch auf schnellen Systemen.

.. container:: incremental

    Eine möglichst sinnvolle Annahme eines Rechnersystems gesucht:

    .. class:: incremental-list

    - Von-Neumann System
    - *mit einer Recheneinheit*
    - genaue Geschwindigkeit nicht relevant

.. supplemental::

    Die Komplexität eines Problems zu bestimmen ist oft ausgesprochen schwierig, da man hierfür den besten Algorithmus kennen muss. Es stellt sich dann weiterhin die Frage wie man beweist, dass der beste Algorithmus vorliegt.

    **Bei vielen Komplexitätsanalysen steht die Zeitkomplexität im Vordergrund.**

    Die Zeitkomplexität misst nicht konkrete Ausführungszeiten (z. B. 1456 ms), da die Ausführungszeit von sehr vielen Randbedingungen abhängig ist, die direkt nichts mit dem Algorithmus zu tun haben, z. B.:

    - Prozessortyp und Taktfrequenz
    - Größe des Hauptspeichers
    - Zugriﬀszeiten der Peripheriegeräte
    - Betriebssystem → wird z. B. ein virtueller Speicher unterstützt
    - Compiler- oder Interpreter-Version
    - Systemlast zum Zeitpunkt der Ausführung


Wichtige Komplexitätsklassen
----------------------------------------

.. csv-table::
    :header: Klasse, Eigenschaft
    :class: incremental-table-rows

    :math:`O(1)`, Die Rechenzeit ist unabhängig von der Problemgröße
    ":math:`O(\log n)`", Die Rechenzeit wächst logarithmisch mit der Problemgröße
    :math:`O(n)`, Die Rechenzeit wächst linear mit der Problemgröße
    ":math:`O(n \cdot \log n)`", Die Rechenzeit wächst linear logarithmisch mit der Problemgröße
    :math:`O(n^2)`, Die Rechenzeit wächst quadratisch mit der Problemgröße
    :math:`O(n^3)`, Die Rechenzeit wächst kubisch mit der Problemgröße
    :math:`O(2^n)`, Die Rechenzeit wächst exponentiell (hier zur Basis 2) mit der Problemgröße
    :math:`O(n!)`, Die Rechenzeit wächst entsprechend der Fakultätsfunktion mit der Problemgröße


Komplexität und bekannte Algorithmen/Probleme
----------------------------------------------

.. deck::

    .. card::

        :math:`O(1)`

        - Liegt typischerweise dann vor, wenn das Programm nur einmal linear durchlaufen wird.
        - Es liegt keine Abhängigkeit von der Problemgröße vor, d. h. beispielsweise keine Schleifen in Abhängigkeit von :math:`n`.

        .. example::
            :class: incremental

            Die Position eines Datensatzes auf einem Datenträger kann mit konstanten Aufwand berechnet werden.

    .. card::

        :math:`O(\log n)`

        .. example::
            :class: incremental

            Binäre Suche; d. h. in einem sortierten Array mit :math:`n` Zahlen eine Zahl suchen.

    .. card::

        :math:`O(n)`

        .. example::
            :class: incremental

            Invertieren eines Bildes oder sequentielle Suche in einem unsortierten Array.

    .. card::

        :math:`O(n \cdot \log n)`

        .. example::
            :class: incremental

            Bessere Sortierverfahren wie z. B. Quicksort.


    .. card::

        :math:`O(n^2)`

        - Häufig bei zwei ineinander geschachtelten Schleifen.

        .. example::
            :class: incremental

            Einfache Sortierverfahren oder die Matrixaddition.


    .. card::

        :math:`O(n^3)`

        - Häufig bei drei ineinander geschachtelten Schleifen.

        .. example::
            :class: incremental

            .. rubric:: Die (naive) Matrixmultiplikation

            :math:`M(m, t)` ist eine Matrix mit :math:`m` Zeilen und :math:`t` Spalten.

            :math:`C(m, t) = A(m, n)· B(n, t)` mit

            :math:`c_{i,j} = \sum_{k = 1}^n a_{i,k}· b_{k,j}\qquad i = 1, . . . , m \qquad j = 1, . . . , t`


    .. card::

        :math:`O(2^n)`

        - Typischerweise der Fall, wenn für eine Menge mit :math:`n` Elementen alle Teilmengen berechnet und verarbeitet werden müssen.

        .. example::
            :class: incremental

            .. rubric:: Das Rucksackproblem (:eng:`Knapsack Problem`)

            Ein Rucksack besitzt eine maximale Tragfähigkeit und :math:`n` Gegenstände unterschiedlichen Gewichts und Wertes liegen vor, deren Gesamtgewicht über der Tragfähigkeit des Rucksacks liegt. Ziel ist es jetzt eine Teilmenge von Gegenständen zu finden, so dass der Rucksack optimal in Hinblick auf den Gesamtwert gefüllt wird.

    .. card::

        :math:`O(n!)`

        - Typischerweise der Fall, wenn für eine Menge von :math:`n` Elementen alle Permutationen dieser Elemente zu berechnen und zu verarbeiten sind.

        .. example::
            :class: incremental

            .. rubric:: Das Problem des Handlungsreisenden (:eng:`Traveling Salesman Problem (TSP)`)

            Gegeben sind :math:`n` Städte, die alle durch Straßen direkt miteinander verbunden sind und für jede Direktverbindung ist deren Länge bekannt.

            Gesucht ist die kürzeste Rundreise, bei der jede Stadt genau einmal besucht wird.



Approximation von Laufzeiten
----------------------------------------

.. note::
    :class: width-50

    Für die Approximation sei ein Rechner mit 4 GHz Taktrate angenommen und ein Rechenschritt soll einen Takt benötigen.

    .. container:: 

        Verwendete Abkürzungen:

        - :math:`1ns = 10^{-9}s` → Nanosekunde
        - :math:`1µs = 10^{-6}s` → Mikrosekunde
        - :math:`1ms = 10^{-3}s` → Millisekunde
        - :math:`1h = 3 600s` → Stunde
        - :math:`1d = 86 400s` → Tag
        - :math:`1a` → Jahr

Sei die Problemgröße :math:`n = 128`:

.. csv-table::
    :header: Klasse, Laufzeit
    :class: highlight-line-on-hover

    ":math:`O(\log_2\, n)`", ":math:`1,75\,ns`"
    :math:`O(n)`, ":math:`32\,ns`"
    ":math:`O(n \cdot \log_2\, n)`", ":math:`224\,ns`"
    :math:`O(n^2)`, ":math:`4,096\,µs`"
    :math:`O(n^3)`, ":math:`524,288\,µs`"
    :math:`O(2^n)`, ":math:`2,70 \cdot 10^{21}\,a`"
    :math:`O(3^n)`, ":math:`9,35 \cdot 10^{43}\,a`"
    :math:`O(n!)`, ":math:`3,06 \cdot 10^{198}\,a`"

.. container:: incremental

    Dies zeigt, dass Algorithmen mit einer Komplexität von :math:`O(n^3)` oder höher für große bzw. nicht-triviale Problemgrößen nicht praktikabel sind.



.. class:: new-subsection

Iterative Algorithmen
--------------------------------------------------------



Elementare Kosten als Approximation
----------------------------------------

.. deck::

    .. card::

        .. csv-table::
            :header: "Operation", "Anzahl der Rechenschritte"
            :widths: 35, 65
            :class: highlight-line-on-hover

            "elementare Arithmetik: +    ,-    ,    *    , /, <, <=, etc.", 1
            "elementare logische Operationen: &&, ||, !, etc.", 1
            "Ein- und Ausgabe", 1
            "Wertzuweisung", 1
            ":java:`return`, :java:`break`, :java:`continue`", 1

    .. card::

        .. csv-table::
            :header: "Kontrollstrukturen", "Anzahl der Rechenschritte"
            :widths: 35, 65
            :class: highlight-line-on-hover

            Methodenaufruf, 1 + Komplexität der Methode
            "Fallunterscheidung", "Komplexität des logischen Ausdrucks + Maximum der Komplexität der Rechenschritte der Zweige"
            Schleife, "Annahme: :math:`m` Durchläufe:
            Komplexität der Initialisierung + :math:`m` mal die Komplexität des
            Schleifenkörpers + Komplexität aller Schleifenfortschaltungen"



Beispiel Primzahltest: Analyse mit elementaren Kosten
------------------------------------------------------------

.. code:: python
    :number-lines:

    def ist_primzahl(n):
        prim = True                 # Wertzuweisung:            1
        i = 2                       # Wertzuweisung:            1
        if n < 2:                   # Vergleich:                1
            prim = False            # Wertzuweisung:            1
        else:                       # Durchläufe:               n-2 * (
            while prim and i < n:   #   Vergleiche, und:            3
                if n % i == 0:      #   modulo, Vergleich:          2
                    prim = False    #       Wertzuweisung:              1
                i += 1              #   Inkrement:                  1
                                    #                           )
                                    # letzte Bedingungsprüfung  3
        return prim                 # Befehl:                   1

.. container:: incremental

    Im schlechtesten Fall, d. h. :math:`i \geq 2` und es gilt :math:`i==n` nach der while-Schleife, werden :math:`7 + (n− 2)· 7 = 7· n− 7` Rechenschritte benötigt. Die Anzahl der Rechenschritte hängt somit linear vom Eingabewert :math:`n` ab.

.. supplemental::

    Beachte, dass in keinem Falle alle Instruktionen ausgeführt werden.

    .. hint::

        Dies ist kein effizienter Algorithmus zum Feststellen ob eine Zahl Primzahl ist. Dieser Algorithmus ist nur zu Demonstrationszwecken gedacht.



Insertion-Sort: Analyse mit abstrahierten Kosten
-----------------------------------------------------------

.. deck::

    .. card::

        .. rubric:: Sortieralgorithmen

        .. background::

            Sortierung basiert (meist) auf paarweisen Vergleichen.

            - Vergleichsoperatoren für numerische Werte (:java:`int`, :java:`float`,...) klar
            - Andere Datentypen / Klassen benötigen definierten Vergleichsoperator


    .. card::

        Vergleichbar zum Ziehen von Karten: die neue Karte wird an der richtigen Stelle eingeschoben.

    .. card::

        .. raw:: html

            <style>
                #insertion-sort-visualization {
                    iframe {
                        border: none;
                        margin: 0;
                        padding: 0;
                    }
                }
                @container style(--ld-rendering-mode: slide) {

                    #insertion-sort-visualization {
                        width: 912.5px;
                        height: 963.75px;
                        scale: 2.5;
                        transform-origin: 0 0;
                    }
                }
            </style>
            <div id="insertion-sort-visualization">
                <iframe src="vis/insertionsort.html" width="365" height="385.5" frameborder="0"></iframe>
            </div>

    .. card::

        .. class:: columns

            - .. image:: images/insertion_sort.svg
                    :alt: Visualisierung des Insertion-Sort-Algorithmus

            - .. code:: python
                    :number-lines:
                    :class: copy-to-clipboard incremental

                    def insertion_sort(A):
                      for i in range(1, len(A)):
                        key = A[i]
                        j = i - 1
                        while j >= 0 and A[j] > key:
                          A[j + 1] = A[j]
                          j = j - 1
                        A[j + 1] = key



Beispiel Insertion-Sort: Detailanalyse
--------------------------------------------------------

.. story::

    .. csv-table::
        :class: borderless highlight-line-on-hover
        :header: "", "Algorithmus: Insertion-Sort(A, n) [Pseudocode - Index startet bei 1]", "Zeit", "Anzahl"
        :widths: 3, 80, 10, 25

        1:, ``for i = 2...n do``, "c1", :math:`n`
        2:,   ``key = A[i]``, c2, :math:`n-1`
        3:,   ``j = i - 1``,                          c3   ,   :math:`n−1`
        4:,   ``while j > 0 and A[j] > key do``,     c4  ,    :math:`\sum_{i=2}^n t_i`
        5:,     ``A[j + 1] = A[j]``,              c5  ,    :math:`\sum_{i=2}^n (t_i−1)`
        6:,     ``j = j - 1``,                      c6   ,  :math:`\sum_{i=2}^n (t_i−1)`
        7:,   ``A[j + 1] = key``,                   c7 ,     :math:`n−1`

    - :math:`c_x` sind die konstanten Kosten für die jeweilige Operation. Wir abstrahieren diese als :math:`c = max(c_1,...c_7)`.
    - :math:`t_i` ist die Anzahl der Schritte, die für das Einsortieren der :math:`i`-ten Karte benötigt wird. Dies hängt davon ab, wie die Liste vorliegt.

    .. container:: incremental

        Abschätzung der Laufzeit :math:`T(n)` nach oben:

        .. math::

            T(n) ≤ c· \left( n + 3·(n−1) + \sum^n_{i=2} t_i + 2 · \sum^n_{i=2} (t_i−1) \right)

    .. container:: incremental

        .. math::

            T(n) ≤ c· \left( n + 3·(n−1) + \sum^n_{i=2} t_i + 2 · (\sum^n_{i=2} t_i − \sum^n_{i=2} 1 )) \right)

    .. container:: incremental

        .. math::

            = c· \left( 4n - 3 + 3 · \sum^n_{i=2} t_i - 2 · (n-1)  \right)

    .. container:: incremental

        .. math::

            = c· \left( 2n -1 + 3  · \sum^n_{i=2} t_i \right)

    .. container:: incremental

        Jetzt können drei Fälle unterschieden werden:

        .. class:: incremental

        - die Liste ist bereits sortiert, d. h. :math:`t_i = 1`
        - die Liste ist umgekehrt sortiert, d. h. :math:`t_i = i`
        - die Liste ist zufällig sortiert, d. h. :math:`t_i = {i+1 \over 2}`

    .. container:: incremental

        Im schlimmsten Fall, d. h. die Liste ist umgekehrt sortiert, ergibt sich:

        .. math::

            T(n) ≤ c· \left( 2n -1 + 3 · \sum^n_{i=2} i \right)\\

        .. container:: text-align-center

            und nach Anwendung der Summenformel für die natürlichen Zahlen:

        .. math::

            = c· \left( {3 \over 2} n^2 + {7 \over 2} n - 4 \right)


    .. container:: incremental

        Im besten Fall, d. h. die Liste ist bereits sortiert, ergibt sich:

        .. math::

            T(n) ≤ c· \left( 2n -1 + 3 · \sum^n_{i=2} 1 \right)\\

            = c· \left( 5n - 4 \right)

    .. container:: incremental

        .. remark::

            *Insertion-Sort* hat die gleiche Performance auf Arrays und (doppelt) verketteten Listen

.. supplemental::

    .. hint::

        In Zeile 1 ist die Anzahl :math:`n`, da :math:`n-1` mal hochgezählt wird und dann noch ein Test erfolgt, der fehlschlägt und die Schleife beendet.



Beispiel Insertion-Sort: Ergebnisse
--------------------------------------------------------

In Hinblick auf den Zeitaufwand gilt:

.. math::

    \begin{array}{rl}
        T_{worst}(n)  \in & \Theta(n^2)\\
        T_{average}(n)  \in & \Theta(n^2) \\
        T_{best}(n)  \in & \Theta(n)
    \end{array}

.. container:: incremental

    Der Insertion-Sort-Algorithmus hat eine quadratische Komplexität, d. h. die Laufzeit wächst quadratisch mit der Problemgröße. Er hat die Komplexität :math:`O(n^2)`.



.. class:: exercises transition-scale

Übung
------------------------------------------

.. exercise:: Bestimmung der asymptotischen Laufzeit eines Algorithmus

    Die Funktion :math:`p(n)` hat die Laufzeit :math:`T_p(n) = c_p \cdot n^2` und :math:`q(n)` die Laufzeit :math:`T_q(n) = c_q \cdot \log(n)`.

    .. code:: pascal
        :number-lines:

        Algorithmus COMPUTE(n)
            p(n);
            for j = 1...n do
                for k = 1...j do
                    q(n);
                end
            end

    Bestimmen Sie die asymptotische Laufzeit des Algorithmus in Abhängigkeit von :math:`n` durch zeilenweise Analyse.

    .. solution::
        :pwd: \log(n)*n^2

        Die Komplexität ergibt sich zu: :math:`\log(n)\cdot n^2`

        :math:`p(n)` hat die Komplexität :math:`n^2`

        :math:`q(n)` hat die Komplexität :math:`\log(n)`

        Eine Analyse der Schleifen ergibt, dass q(n):

            :math:`1\cdot q(n)+2\cdot q(n)+3\cdot q(n)+…+n\cdot q(n) = q(n) \cdot \sum_{i = 1}^n i = q(n) \cdot {n(n+1)\over 2} = { n^2+n \over 2 } \cdot q(n)`

        aufgerufen wird. Daraus folgt:

            :math:`(n(n+1))/2 \cdot q(n)` bzw. :math:`n^2 \cdot \log(n)`



.. class:: exercises transition-scale

Übung
------------------------------------------

.. exercise:: „Naive“ Power Funktion

    .. container::

        Bestimmen Sie die algorithmische asymptotische Komplexität des folgenden Algorithmus durch Analyse jeder einzelnen Zeile. Jede Zeile kann für sich mit konstantem Zeitaufwand abgeschätzt werden. Die Eingabe ist eine nicht-negative Ganzzahl :math:`n` mit :math:`k` Bits. Bestimmen Sie die Laufzeitkomplexität für den schlimmstmöglichen Fall in Abhängigkeit von :math:`k`!

        .. container::

            (Beispiel: die Zahl :math:`n = 7_d` benötigt drei Bits :math:`n= 111_b`, die Zahl :math:`4_d` benötigt zwar auch drei Bits :math:`100_b` aber dennoch weniger Rechenschritte.).

        .. code:: pascal
            :number-lines:
            :class: margin-top-1em

            Algorithmus Power(x,n)
                r = 1
                for i = 1...n do
                    r = r * x
                return r

        .. solution::
            :pwd: Zaehlen_der_Schritte

            .. code:: pascal
                :number-lines:

                Algorithmus Power(x,n)      # Anzahl der Rechenschritte
                    r = 1                   # 1
                    for i = 1...n do        # n + 1
                        r = r * x           # n
                    return r                # 1

            Sei c ein konstanter Faktor, der gleich dem größten Faktor ist, der von einem Rechenschritt benötigt wird.

            :math:`T(n) \leq c \cdot (1 + (n + 1) + n + 1)`

            :math:`T(n) \leq c \cdot (3 + 2n)`

            Im schlimmsten Fall, d. h. :math:`n_{worst} = 2^k - 1`:

            :math:`T_{worst}(k) \leq c \cdot (3 + 2 \cdot (2^k - 1)) = c \cdot (1 + 2^{k+1})`

            Somit gilt: :math:`T_{worst}(k) \in \Theta(2^{k})`



.. class:: exercises transition-scale

Übung
------------------------------------------

.. exercise:: Effizientere Power Funktion

    Bestimmen Sie die algo. asymptotische Komplexität durch Analyse jeder einzelnen Zeile. Jede Zeile kann mit konstantem Zeitaufwand abgeschätzt werden.
    Bestimmen Sie die Laufzeitkomplexität mit Indikator :math:`t_i` für gesetzte Bits in :math:`n` für den schlimmstmöglichen Fall - in Abhängigkeit von :math:`k` für eine nicht-negative Ganzzahl :math:`n` mit :math:`k` Bits.

    .. supplemental::

        (D. h. :math:`t_i = 1`, wenn der i-te Bit von :math:`n` gesetzt ist, sonst ist :math:`t_i = 0`; sei :math:`n = 5_d = 101_b` dann ist :math:`t_1 = 1, t_2 = 0, t_3 = 1`).

    .. code:: pascal
        :number-lines:

        Algorithmus BinPower(x,n)
            r = 1
            while n > 0 do
                if n mod 2 == 1 then
                    r = r * x
                    n = (n-1)/2
                else
                    n = n/2
                x = x * x
            return r

    .. solution::
        :pwd: Zaehlen_der_Schritte

        Bestimmung der Anzahl Rechenschritte in Abhängigkeit von der Anzahl an Bits von :math:`n`:

        .. code:: pascal
            :number-lines:

            Algorithmus BinPower(x,n)       # Anzahl der Rechenschritte
                r = 1                       # 1
                while n > 0 do              # 1 + (max i für das gilt t_i = 1)
                    if n mod 2 == 1 then    # max i für das gilt t_i = 1 (# der Vergleiche)
                        r = r * x           # Summe aller t_i; d.h. Anzahl der 1-Bits in n
                        n = (n-1)/2         # Summe aller t_i
                    else
                        n = n/2             # Summe aller (1-t_i); d.h. Anzahl der „relevanten“ 0-Bits in n
                    x = x *x                # max i für das gilt t_i = 1
                return r                    # 1

        Sei c ein konstanter Faktor, der gleich dem größten Faktor ist, der von einem Rechenschritt benötigt wird.

        Sei :math:`l = \underset{t_i = 1}{max}\; i` und :math:`m = \sum_{k=1}^l t_i`:

            .. math::

                \begin{array}{rl}
                T(n) & ≤ c· \left( 1+ 1+ l + l + 2 \sum_{k=1}^l t_i + \sum_{k=1}^l (1- t_i) + l +1 \right) \\
                & = c·(3+ 4l + m)
                \end{array}



        Im schlimmsten Fall, d. h. :math:`n_{worst} = 2^k - 1` und :math:`l_{worst} = m_{worst} = k` : :math:`T_{worst}(k) ≤ c · (3+ 4k + k) = c· (5k + 3)`

        Somit gilt: :math:`T_{worst}(k) \in \Theta(k)`




.. class:: exercises

Übung - Bubble-Sort
------------------------------------------

.. deck::

    .. card::

        Der Algorithmus "Bubble-Sort" sortiert eine Liste von Zahlen in aufsteigender Reihenfolge. Der Algorithmus vergleicht benachbarte Elemente und vertauscht sie, wenn sie in der falschen Reihenfolge sind. Dieser Vorgang wird wiederholt, bis die Liste vollständig sortiert ist.

    .. card::

        .. raw:: html

            <style>
                #bubble-sort-visualization {
                    iframe {
                        border: none;
                        margin: 0;
                        padding: 0;
                    }
                }
                @container style(--ld-rendering-mode: slide) {

                    #bubble-sort-visualization {
                        width: 912.5px;
                        height: 963.75px;
                        scale: 2.5;
                        transform-origin: 0 0;
                    }
                }
            </style>
            <div id="bubble-sort-visualization">
                <iframe src="vis/bubblesort.html" width="365" height="385.5" frameborder="0"></iframe>
            </div>

    .. card::

        .. exercise:: Implementieren Sie Bubble-Sort

            1. Implementieren Sie den Bubble-Sort Algorithmus in einer Programmiersprache Ihrer Wahl und analysieren Sie die Laufzeitkomplexität des Algorithmus. Der Algorithmus soll ein Array/Liste von Ganzzahlen als Eingabe nehmen und sortiert zurückgeben. Dabei soll das Array/die Liste *in-place* sortiert werden, d. h. ohne zusätzliche Speicherplatznutzung.

            2. Bestimmen Sie die Komplexität des Algorithmus in Abhängigkeit von der Anzahl der Elemente (n) im Array/der Liste.

            .. solution::
                :pwd: Bubble-bubble-bubble-sort

                .. rubric:: Lösung in Java

                .. code:: java
                    :number-lines:
                    :class: copy-to-clipboard

                    static void bubbleSort(int[] arr) {
                        int n = arr.length;
                        boolean swapped;
                        do {
                            swapped = false;
                            for (int i = 0; i < n - 1; i++) {
                                if (arr[i] > arr[i + 1]) {
                                    // Swap arr[i] and arr[i + 1]
                                    int temp = arr[i];
                                    arr[i] = arr[i + 1];
                                    arr[i + 1] = temp;
                                    swapped = true;
                                }
                            }
                            n--; // Reduce the range of the next iteration
                        } while (swapped);
                    }

                .. rubric:: Lösung in Python

                .. code:: python
                    :number-lines:
                    :class: copy-to-clipboard

                    def bubble_sort(arr):
                        n = len(arr)
                        swapped = True
                        while swapped:
                            swapped = False
                            for i in range(n - 1):
                                if arr[i] > arr[i + 1]:
                                    arr[i], arr[i + 1] = arr[i + 1], arr[i]
                                    swapped = True
                            n -= 1  # Reduce the range of the next iteration
                        return arr





Rucksackproblem (:eng:`Knapsack Problem`)
--------------------------------------------------------

.. deck::

    .. card::

        .. definition::

            .. rubric:: Das Rucksackproblem

            Gegeben seien Wertepaare :math:`\{(g_1,w_1),...,(g_m,w_m)\}` mit
            :math:`g_i ,w_i ∈ \mathbb{N}`, die das Gewicht :math:`g_i` und den Wert :math:`w_i` eines Teils :math:`i` darstellen. Gesucht sind
            die Anzahlen :math:`a_i ∈ \mathbb{N}_0` der jeweiligen Teile, so dass

            .. math::

                \sum_{i=1}^m a_i g_i ≤ n \quad \text{und} \quad \sum_{i=1}^m a_i w_i\quad \text{maximal wird}

            also für gegebene maximale Last n des Rucksacks der aufsummierte Wert maximal wird.

    .. card::

        .. example::

            Verfügbare Objekte (:math:`(Gewicht,Wert)`): :math:`A = \{(1,1),(3,4),(5,8),(2,3)\}`.

            .. class:: incremental

            - Bei einer maximalen Traglast von :math:`5` ist der maximale Wert :math:`8` (:math:`1 \times` Objekt :math:`3`).

            - Gesucht ist die maximale Wertsumme bei einer maximalen Traglast von 13.

              .. class:: incremental-list

              1. Versuch: bei Einhaltung der Traglast (:math:`n =13`):

                 :math:`\overset{\#}{1}·\overset{g}{1}+ \overset{\#}{4}·\overset{g}{3}= 13 ≤13 \quad\Rightarrow\quad \overset{\#}{1}·\overset{w}{1}+ \overset{\#}{4}·\overset{w}{4}= 17` (Wert)

              2. Versuch: bei Einhaltung der Traglast (:math:`n =13`):

                 :math:`1·1+ 2·5+ 1·2= 13 ≤13\quad \Rightarrow\quad 1·1+ 2·8+ 1·3= 20`  (Wert)



Rucksackproblem - rekursive Lösung
----------------------------------------

.. story::

    .. code:: python
        :number-lines:
        :class: copy-to-clipboard

        gW = [ (1, 1), (3, 4), (5, 8), (2, 3) ] # [(Gewicht, Wert)...]

        def bestWertRekursiv(n): # n = max. Traglast
            best = 0
            for i in range(len(gW)):
                (gewt,wert) = gW[i]
                if n >= gewt:
                    test = wert + bestWertRekursiv(n - gewt)
                    if test > best:
                        best = test
            return best

        print(bestWertRekursiv(5)) # max. Traglast ist hier zu Beginn n = 5

    .. container:: incremental

        Für die Bestimmung der Komplexität nehmen wir jetzt die häufigste Aktion her; hier die Additionen.

        Bei der Rekursion ergibt sich (:math:`m` = Anzahl der verschiedenen Objekte):

        - Im schlimmsten Fall sind alle :math:`g_i = 1` (d. h. die Gewichte).
        - Pro Aufruf :math:`m` weitere Aufrufe.

          (D. h. auf erster Ebene haben wir :math:`m` Additionen, auf der zweiten Ebene :math:`m^2` Additionen, usw.)

    .. container:: incremental

        .. math::

            \begin{array}{rl}
            c^{Rek}_{Add}(n) = & m + m^2 +...+ m^n\quad | \text{Anw. der Summenformel für geo. Reihen}\\
            = & m· \dfrac{m^n-1}{m-1}            =  \dfrac{m}{m-1} (m^n-1) \\
            = & \dfrac{4}{3}(4^n-1) \quad \text{hier mit } m = 4\quad \text{(Anzahl der Objekte)}
            \end{array}

.. supplemental::

    .. rubric:: Erklärungen

    *Grobe Idee*: Wir gehen in der Methode :java:`bestWertRekursiv` über alle Elemente und probieren aus ob wir diese einmal in den Rucksack packen können, d. h. die (verbleibende) Traglast ausreicht. Falls ja, dann führen wir einen rekursiven Aufruf durch bei dem wir die Traglast entsprechende reduziert haben.

    *Details*: Für jedes Element entscheiden wir, ob es noch in den Rucksack passt (Zeile 7). Falls ja, dann wird der Wert des Elements addiert und die Traglast um das Gewicht des Elements reduziert (Zeile 8: :java:`n - gewt`). Anschließend wird rekursiv der bester Wert für den  kleineren Rucksacks berechnet.




Rucksackproblem - iterative Lösung
----------------------------------------

.. story::

    .. rubric:: Grundsätzliche Idee der iterativen Lösung

    Gehe über alle Objekte. Berechne in jedem Schleifendurchlauf :java:`i` bei Hinzunahme von Teil :java:`i` das jeweils beste Ergebnis für alle Kapazitäten :java:`j` bis inklusive :java:`n`.

    .. example::
        :class: incremental

        Verfügbare Objekte (:math:`(Gewicht,Wert)`): :math:`A = \{(1,1),(3,4),(5,8),(2,3)\}`. Sei die maximale Traglast :math:`n = 7`:

        .. csv-table::
            :header: ``i\\j``, 0, 1, 2, 3, 4, 5, 6, 7
            :align: center
            :class: fake-header-column highlight-identical-cells-on-hover

            0, 0, 1, 2, 3, 4, 5, 6, 7
            1, 0, 1, 2, 4, 5, 6, 8, 9
            2, 0, 1, 2, 4, 5, 8, 9, 10
            3, 0, 1, 3, 4, 6, 8, 9, 11

    .. container:: incremental

        .. rubric:: Implementierung

        .. code:: python
            :number-lines:
            :class: copy-to-clipboard

            gW = [ (1, 1), (3, 4), (5, 8), (2, 3) ] # (Gewicht, Wert)

            def bestWertIterativ(n):    # n = max. Traglast
                best = [0] * (n + 1)    # best[i] = bester Wert für Traglast i (initial 0)
                for i in range(len(gW)):
                    (gewt, wert) = gW[i]
                    for j in range(gewt, n + 1): # range(gewt, n + 1): [gewt, ..., n]
                        test = best[j - gewt] + wert
                        if test > best[j]:
                            best[j] = test

                return best[n]

            print(bestWertIterativ(5))  # max. Traglast ist hier zu Beginn n = 5

    .. container:: incremental

        .. rubric:: Komplexitätsanalyse

        Bei den Iterationen ergibt sich:

        Zwei Schleifen über :math:`m` und :math:`n`:

        .. math::

            \begin{array}{rl}
                c^{Ite}_{Add}(n)    & = m·n \\
                                    & = 4n \quad \text{hier mit } m = 4
            \end{array}

.. supplemental::

    .. rubric:: Erklärungen

    Grobe Idee: Wir gehen in der Methode :java:`bestWertIterativ` über alle Objekte (Zeile 5). In der inneren Schleife (Zeile 7) iterieren wir über die Traglasten, die das Objekt — ggf. auch mehrfach — aufnehmen könnten (:java:`range(gewt, n + 1)`). Für jede dieser Traglasten prüfen wir ob es vorteilhaft ist das Objekt in den Rucksack zu packen. Falls ja, dann wird der aktuell beste Wert für die Traglast aktualisiert.

    D. h. wir legen zum Beispiel ein Objekt mit dem Gewicht 2 bei einer verbleibenden Traglast von 5 ggf. (implizit) dadurch mehrfach in den Rucksack, dass wir bereits den besten Wert für die kleineren Traglasten kennen.



Rucksackproblem - Vergleich
----------------------------------------

.. story::

    .. class:: columns evenly-spaced

    - .. math::

                \begin{array}{rl}
                c^{Rek}_{Add}(n) = & \dfrac{m}{m-1} (m^n-1) \\
                = & \dfrac{4}{3}(4^n-1)
                \end{array}

    - .. math::

                \begin{array}{rl}
                    c^{Ite}_{Add}(n)    & = m·n \\
                                        & = 4n
                \end{array}

    .. summary::
        :class: incremental

        Die iterative Variante ist wegen der vermiedenen Berechnung gleicher Werte – aufgrund der Verwendung von dynamischer Programmierung – praktisch immer schneller. Dies könnte bei Rekursion ggf. mit Caching erreicht werden.

    .. question::
        :class: incremental

        Wieso ist das Rucksackproblem dann aber als NP-vollständig klassifiziert?

        .. container:: incremental

            Die Analyse erfolgte nicht über die Wortlänge (als Eingabegröße); d. h. :math:`n` (Kapazität bzw. Tragkraft) entspricht nicht der Wortlänge. Ein Binärwort :math:`n` mit :math:`k` Zeichen (Bits) hat bis zu :math:`2^k-1` Werte.

            .. math::

                c^{Rek}_{Add}(2^k-1) =  \dfrac{4}{3}(4^{2^k-1}-1) \in O(4^{2\cdot k})

            .. math::

                c^{Ite}_{Add}(2^k-1) = 4(2^k-1) \in \Theta(2^k)


    .. important::
        :class: margin-top-1em incremental

        Der erste Vergleich der Algorithmen ist valide in Hinblick auf die relative Laufzeit beider Varianten. Für die Komplexitätsklassifizierung ist jedoch die Wortlänge entscheidend.

        Es ist immer genau zu prüfen was die Wortlänge ist!

.. supplemental::

    Die Wortlänge eines Problems bezeichnet hier die Anzahl der Bits, die benötigt werden, um die Eingabe eines Problems darzustellen. Sie ist ein Maß dafür, wie groß oder komplex die Darstellung der Eingabedaten ist.

    Die iterative Variante mit dynamischer Programmierung hat eine Laufzeit von :math:`O(m⋅n)` wobei :math:`n` hier die Kapazität in Gewichtseinheiten ist, nicht die Wortlänge. Wenn :math:`n` exponentiell groß ist, wird der Algorithmus ineffizient, da die Eingabegröße :math:`⌈log_2	N⌉` viel kleiner ist als :math:`N` selbst. (D. h. wenn die Kapazität 10 ist, dann brauchen wir 4 Bits, um die Kapazität darzustellen, wenn die Kapazität jedoch 1000 (100 mal größer) ist, dann brauchen wir 10 Bits (d. h. nur 2,5 mal so viele Bits.)






.. class:: new-subsection transition-scale

Rekursiv teilende Algorithmen
--------------------------------------------------------



Standardvorgehensweise bei der Analyse
----------------------------------------

Standardverfahren zur Analyse rekursiver Algorithmen:

.. class:: incremental

1. Anwendung der Verfahren zur Analyse iterativer Algorithmen um die Rekurrenzgleichung zu bestimmen.
2. Eine Anzahl von Werten ausrechnen und auf sinnvollen Zusammenhang schließen.
3. Beweis des Zusammenhangs mit vollständiger Induktion.

   .. attention::

      Das Finden eines sinnvollen Zusammenhangs und der Beweis ist nicht immer einfach.


.. supplemental::

    Dieses Verfahren haben wir bei den Türmen von Hanoi angewandt.

    Rekurrenzgleichungen sind Gleichungen, die eine Folge :math:`a_n` durch ihre vorherigen Elemente definieren. Sie beschreiben die Entwicklung eines Wertes :math:`a_n` in Abhängigkeit von vorhergehenden Werten.


Beobachtung bzgl. rekursiv teilender Algorithmen
---------------------------------------------------

Teilende Verfahren, *bzw. Divide-and-Conquer-Algorithmen*, sind typischerweise sehr effizient.

.. example::

    Wird beispielsweise das Problem immer halbiert, ist also :math:`a_{2n} = a_n + 1` und ist :math:`a_1 = 1`, dann würde für die Folgenglieder gelten :math:`a_1 = 1, a_2 = 2, a_4 = 3, a_8 = 4, a_{16} = 5, …`.

    Verallgemeinert: :math:`a_n = \log_2(n) +1`.

    .. container:: incremental

        Herleitung:

        :math:`a_1 = \log_2(1) + 1 = 0 + 1`

    .. container:: incremental

        :math:`a_{2n} = a_n + 1 = \log_2(n) + 1 + 1 = \log_2(n) + \log_2(2) + 1 = \log_2(2n) + 1`

    .. container:: incremental margin-top-0.5em

        Ein konkretes Beispiel ist die binäre Suche nach einem Namen im Telefonbuch oder nach einer zu erratenden Zahl.

.. supplemental::

    Bei der Herleitung wurde (wieder) vollständige Induktion angewandt und die Logarithmusgesetze genutzt: :math:`\log(a) + \log(b) = \log(a \cdot b)` sowie :math:`\log_bb= 1`.


Rekurrenzgleichung für rekursiv teilende Algorithmen
-------------------------------------------------------

.. story::

    - In vielen Fällen geben rekursiv teilende Algorithmen Grund zur Hoffnung, dass die Laufzeit einen relevanten logarithmischen Anteil hat.

    .. class:: incremental

    - Häufig können die Rekurrenz-Gleichungen rekursiv teilender Algorithmen in folgende Form gebracht werden:

      .. container::

        Sei:

        - :math:`a`: die Anzahl der rekursiven Aufrufe,
        - :math:`{n \over b}`: die Größe jedes rekursiven Unterproblems wobei  :math:`b` die Anzahl der Teile ist in die das Problem geteilt wird,
        - :math:`f(n)`: der Aufwand während der Ausführung (z. B. der Aufwand für das Teilen der Eingabedaten und das Zusammenführen der Teillösungen).

    
      .. container:: incremental
         
         .. math::

            T(n) = a \cdot T\left({n \over b}\right) + f(n)


    .. container:: incremental 

        In diesem Fall können drei Fälle unterschieden identifiziert werden:

        .. class:: incremental-list

        1. Ist der Aufwand :math:`f(n)` vernachlässigbar gegenüber dem Aufwand der weiteren Aufrufe, so ist ein rein durch die Rekursion bestimmtes Verhalten zu erwarten.
        2. Entspricht der Aufwand :math:`f (n)` genau dem Aufwand der weiteren Aufrufe, so vervielfältigt sich der Aufwand gegenüber dem 1. Fall, bleibt aber in der gleichen Größenordnung.
        3.  Ist der Aufwand :math:`f (n)` größer als der Aufwand der verbleibenden Aufrufe, so wird der Aufwand asymptotisch von :math:`f (n)` dominiert.

.. supplemental::

    .. rubric:: Beispiel für den 1. Fall

    Bei :math:`a = 1` und :math:`b= 2` — wie bei der binären Suche — ist somit logarithmisches Verhalten zu erwarten. Wird hingegen ein :math:`b= 2` halbiertes Feld :math:`a = 4` viermal aufgerufen, so ist ein quadratisches Verhalten zu erwarten.



Lösen von Rekurrenzgleichungen mit dem Master-Theorem
--------------------------------------------------------

.. story::

    Das Master-Theorem ist ein Werkzeug zur Analyse der Zeitkomplexität von rekursiven Algorithmen, die mit Hilfe von Rekurrenzgleichungen der Form :math:`T(n) = a \cdot T\left({n \over b}\right) + f(n)` beschrieben werden können.

    .. container:: incremental

        Anwendungsgebiet sind insbesondere Teile-und-Herrsche Algorithmen.

    .. container:: incremental

        Das Master-Theorem hat drei Fälle, die auf dem Vergleich zwischen :math:`f(n)` und :math:`n^{\log_b a}` basieren und die asymptotische Komplexität von :math:`T(n)` bestimmen. Wobei :math:`n^{\log_b a}` die Laufzeit für die Rekursion selbst beschreibt:

        Seien :math:`a >0` und :math:`b >1` Konstanten und :math:`f : \mathbb{N} → \mathbb{N}`:

    .. container:: incremental

        1. Wenn :math:`f(n) \in O(n^{\log_b a - \epsilon})` für ein :math:`\epsilon > 0` gilt – d. h. wenn :math:`f(n)` langsamer wächst als :math:`n^{\log_b a}` – dann dominiert die Rekursion, und es gilt: :math:`T(n) \in \Theta(n^{\log_b a})`.


    .. container:: incremental

        2. Wenn :math:`f(n) \in \Theta(n^{\log_b a} \cdot (\log n)^k)` für ein :math:`k \geq 0` gilt – d. h. wenn :math:`f(n)` und :math:`n^{\log_b a} \cdot (\log n)^k` gleich schnell wachsen – dann tragen beide Teile zur Gesamtkomplexität bei, und es gilt: :math:`T(n) \in \Theta(n^{\log_b a} \cdot (\log n)^{k+1})`.

    .. container:: incremental

        3. Wenn :math:`f(n) \in \Omega(n^{\log_b a + \epsilon})` für ein :math:`\epsilon > 0` gilt und weiterhin gilt :math:`af(n/b) \leq c f(n)` für eine Konstante :math:`c < 1` und ein hinreichend großes :math:`n` – d. h. wenn also :math:`f(n)` schneller wächst als :math:`n^{\log_b a}` – dann dominiert :math:`f(n)` die Komplexität, und es gilt: :math:`T(n) \in \Theta(f(n))`.


.. supplemental::

    Viele Sortieralgorithmen sind zum Beispiel Teile-und-Herrsche Algorithmen.

    .. hint::

        Nicht immer kann das Master-Theorem angewandt werden, da es nur für spezielle Rekurrenzgleichungen gilt.


    Im Mastertheorem erfolgt der Vergleich ggf. mit :math:`n^{(\log_ba)-\epsilon}` und nicht mit :math:`n^{\log_b (a-\epsilon)}`.


Anwendung des Master-Theorems: 1. Beispiel
--------------------------------------------------------

:Gegeben sei: :math:`T (n) = 2T (n/2) + n \log_2 n`

.. container:: incremental margin-top-1em

    :Somit gilt: :math:`a = 2`, :math:`b = 2` und :math:`n^{\log_2 2} = n`

.. container:: incremental margin-top-1em

    :Analyse: Es liegt Fall 2 vor, da :math:`f(n) = n \cdot (\log_2n)^{k=1} \in \Theta(n^{\log_b a} \cdot (\log n))`.

.. container:: incremental margin-top-1em

    :Ergebnis: Die Laufzeit beträgt somit :math:`T(n) = \Theta(n \cdot (\log_2 n)^2)`.


.. supplemental::

    Der Wechsel der Basis des Logarithmus ist möglich, da sich die Basis nur um einen konstanten Faktor unterscheidet:

    :math:`\log_\textcolor{blue}{a} \textcolor{red}{x} = \frac{ 1 }{ \log_b \textcolor{blue}{a}} \cdot \log_b \textcolor{red}{x}`



Anwendung des Master-Theorems: 2. Beispiel
--------------------------------------------------------

:Gegeben sei: :math:`T (n) = 9T (n/3) + 2n`

.. container:: incremental margin-top-1em

    :Somit gilt: :math:`a = 9`, :math:`b = 3` und :math:`n^{\log_3 9} = n^2`

.. container:: incremental margin-top-1em

    :Analyse: Es liegt Fall 1 vor, da :math:`f(n) = 2n \in O(n^{\log_3 9 - \epsilon})`.

.. container:: incremental margin-top-1em

    :Ergebnis: Die Laufzeit beträgt somit :math:`T(n) = \Theta(n^2)`.



Anwendung des Master-Theorems: 3. Beispiel
--------------------------------------------------------

:Gegeben sei: :math:`T (n) = 2T (n/3) + n`

.. container:: incremental margin-top-1em

    :Somit gilt: :math:`a = 2`, :math:`b = 3` und :math:`n^{\log_3 2}`, :math:`log_32 \approx 0,63 < 1`

.. container:: incremental margin-top-1em

    :Analyse:
        Es liegt Fall 3 vor, da :math:`f(n) = n \in \Omega(n^{\log_3 2 + \epsilon})` und

        :math:`af(n/b) = 2n/3 \leq c \cdot n` für :math:`1 > c \geq 2/3`.

.. container:: incremental margin-top-1em

    :Ergebnis: Die Laufzeit beträgt somit :math:`T(n) = \Theta(n)`.



.. class:: no-title center-content

Master-Theorem: Zusammenfassung
----------------------------------------

.. summary::

    Das Master-Theorem hilft also, die asymptotische Komplexität von Algorithmen schnell zu bestimmen, ohne dass eine detaillierte Analyse der Rekurrenz erforderlich ist.



.. class:: exercises transition-scale

Übung
--------------------------------------------------------

.. exercise:: f(n) ist konstant

    Gegeben sei: :math:`T (n) = 2T (n/4) + 1`

    - Bestimmen Sie die Laufzeit des Algorithmus mit Hilfe des Master-Theorems.

    .. solution::
        :pwd: so_ist_es

        - :math:`a = 2`: Es gibt zwei rekursive Aufrufe,
        - :math:`b = 4`: Jeder Aufruf hat die Größe :math:`n/4`,
        - :math:`f(n) = 1`: Die Kosten für die Rekursion.

        (Hinweis: :math:`\log_4 2 = {\log_{10} 2 \over \log_{10} 4 } = {\ln 2 \over \ln 4 }  = 1/2`)

        :Analyse:

            Hier ergibt sich :math:`n^{\log_b a} = n^{\log_4 2} = n^\frac{1}{2}`. Für :math:`\epsilon \leq ½` gilt :math:`f(n) \in O(n^{\log_4 2 - \epsilon})` d. h. :math:`1 \in O(n^{(1/2)-\epsilon}`.

            Das passt zu Fall 1, da :math:`f(n) \in O(n^{\log_b a - \epsilon})`.

        :Ergebnis: Daher ist die Laufzeit :math:`T(n) \in \Theta(\sqrt{n})`.


.. exercise:: f(n) ist die Quadratwurzel

    Gegeben sei: :math:`T (n) = 3T (n/9) + \sqrt{n}`

    - Bestimmen Sie die Laufzeit des Algorithmus mit Hilfe des Master-Theorems.

    .. solution::
        :pwd: Haus_und_Hof

        - :math:`a = 3`: Es gibt drei rekursive Aufrufe,
        - :math:`b = 9`: Jeder Aufruf hat die Größe :math:`n/9`,
        - :math:`f(n) = \sqrt{n}`: Die Kosten für die Rekursion.

        :Analyse:
            Hier ergibt sich :math:`n^{\log_b a} = n^{\log_9 3} = n^\frac{1}{2}`.

            Das passt zu Fall 2, da :math:`f(n) \in \Theta(\sqrt{n}\cdot (\log n)^0)`.

            Anmerkung: :math:`f(n) =  n^{(1/2)} \notin O(n^{(1/2) - \epsilon})` für :math:`\epsilon > 0`.

        :Ergebnis: Daher ist die Laufzeit :math:`T(n) \in \Theta(\sqrt{n} \cdot \log n)`.


.. exercise:: a=1 und f(n) sind konstant

    Gegeben sei: :math:`T (n) = T (n/2) + 1`

    - Bestimmen Sie die Laufzeit des Algorithmus mit Hilfe des Master-Theorems.

    .. solution::
        :pwd: der zweite Fall

        :Analyse:
            Hier ergibt sich :math:`n^{\log_b a} = n^{\log_2 1} = n ^ 0 = 1`.

            Das passt zu Fall 2, da :math:`f(n) \in \Theta(1)`.

        :Ergebnis: Daher ist die Laufzeit :math:`T(n) \in \Theta(\log n)`.


.. class:: exercises transition-scale

Übung
--------------------------------------------------------

.. deck::

    .. card::

        .. rubric:: Mergesort

        Der Mergesort-Algorithmus ist ein rekursiver Algorithmus, der ein Array solange in zwei Hälften teilt (Teile und Herrsche (:eng:`divide and conquer`)) bis diese trivial sind. Danach werden jeweils zwei sortierte Hälften zusammengeführt bis das ganze Array wieder zusammengeführt wurde. Das Zusammenführen der Hälften hat einen Aufwand von :math:`n` und das Teilen des Arrays hat einen konstanten Aufwand.


    .. card::

        .. rubric:: Mergesort (Visualisierung)

        .. raw:: html

            <style>
                #merge-sort-visualization {
                    iframe {
                        border: none;
                        margin: 0;
                        padding: 0;
                    }
                }
                @container style(--ld-rendering-mode: slide) {

                    #merge-sort-visualization {
                        width: 408px;
                        height: 864px;
                        scale: 3;
                        transform-origin: 0 0;
                    }
                }
            </style>
            <div id="merge-sort-visualization">
                <iframe src="vis/mergesort.html" width="136" height="288" frameborder="0"></iframe>
            </div>


    .. card::

        .. exercise:: Anwendung des Master-Theorems auf Mergesort



            - Bestimmen Sie die Rekurrenzgleichung für den Mergesort-Algorithmus.
            - Bestimmen Sie die Laufzeit des Mergesort-Algorithmus mit Hilfe des Master-Theorems.

            .. solution::
                :pwd: und somit gilt...

                Der Mergesort-Algorithmus kann durch die Rekurrenz :math:`T(n) = 2 \cdot T(n/2) + n` beschrieben werden:

                - :math:`a = 2`: Es gibt zwei rekursive Aufrufe,
                - :math:`b = 2`: Jeder Aufruf hat die Größe :math:`n/2`,
                - :math:`f(n) = n`: Die Kosten für das Mischen.

                Hier ergibt sich :math:`n^{\log_b a} = n^{\log_2 2} = n^1 = n`. Das passt zu Fall 2, da :math:`f(n) = \Theta(n^{\log_b a})` (k = 0). Daher ist die Laufzeit:

                .. math::

                    T(n) = \Theta(n \cdot \log n)




.. class:: new-subsection

Entwurf von Algorithmen: Backtracking bzw. das Backtrack-Prinzip
--------------------------------------------------------------------

.. supplemental::

    Neben der dynamischen Programmierung ist das Backtrack-Prinzip ein weiteres grundlegendes Verfahren zur Lösung von Problemen.


Backtracking
--------------------------------------------------------

- Backtracking ist ein Verfahren, das in vielen Algorithmen zur Anwendung kommt. Insbesondere, wenn kein effizienterer Algorithmus bekannt ist, als *alle möglichen Lösungen auszuprobieren*.

.. class:: incremental-list list-with-explanations

- Backtracking ist eine systematische Methode, um alle möglichen Lösungen eines Problems zu finden. Es ist eine Art von rekursivem Durchsuchen, bei dem Teillösungen zu Gesamtlösungen erweitert werden.
- Backtracking erlaubt ggf. Heuristiken, um die Suche zu beschleunigen.

  Weder die Komplexitätsklasse noch die Korrektheit ändert sich dadurch.
- Viele NP-harte Probleme werden mit Backtracking gelöst.\ [#]_

.. supplemental::

    Backtracking führt eine erschöpfende Suche durch, um eine Lösung zu finden. Kann aber auch direkt genutzt werden, um ggf. alle Lösungen zu finden.

    Backtracking ist in Prolog inherent vorhanden, da Prolog auf dem Prinzip des Backtrackings basiert, weswegen Prolog für die Lösung solcher Probleme gut geeignet ist.

.. [#] NP-harte und NP-vollständige Probleme sind solche Probleme, die schwierig sind zu lösen. Hier reicht es zu wissen, dass es Probleme gibt, die nicht in polynomieller („vernünftiger“) Zeit gelöst werden können.


Beispiel: Das 4-Damen Problem (konzeptuell)
--------------------------------------------------------------------------

.. code:: pascal
    :number-lines:
    :class: copy-to-clipboard

    // i: Spalte; j: Zeile
    procedure findeStellung(i : integer)
      j := 0
      repeat
        { wähle nächste Zeile j }
        if  Dame an Position i / j bedroht
            keine bisher platzierte Dame then
          { platziere Dame in Feld i / j }
          if i = 4 then
            { Lösung gefunden }
            { Ausgabe der Lösung }
          else
            findeStellung(i + 1) // rek. Aufruf
          { entferne Dame aus Spalte i und Zeile j } // zurücksetzen des Zustands
      until { alle Zeilen j getestet }

.. [#] Es gibt eine geschlossene Lösung für das Problem. Backtracking wird hier nur als Beispiel für das Verfahren verwendet.

.. supplemental::

    Ziel ist es vier Damen auf einem Schachbrett so zu platzieren, dass keine Dame eine andere Dame schlagen kann.\ [#]_ Eine Lösung:

    .. csv-table::
        :header: " ", "1", "2", "3", "4"
        :class: align-center background-white

        1, " ", " ", "D", " "
        2, "D", " ", " ", " "
        3, " ", " ", " ", "D"
        4, " ", "D", " ", " "

    Wesentliche Elemente:

    - Die Lösung ist endlich.
    - Die Lösung wird iterativ aufgebaut. Es ist jederzeit möglich zu testen, ob die bisherige Lösung noch gültig ist (Zeile 6, 7).
    - Ist eine Lösung nicht mehr möglich, wird die Teillösung auch nicht weiter verfolgt.
    - Wurde eine Lösung gefunden, wird sie ausgegeben (Zeile 10, 11).
    - Die Methode wird rekursiv aufgerufen, um die Lösung zu vervollständigen (Zeile 13).



Backtracking - Allgemein
--------------------------------------------------------

.. deck::

    .. card::

        .. rubric:: Voraussetzungen für Backtracking

        .. class:: incremental-list list-with-explanations

        1. Die Lösung ist als Vektor :java:`a[1], a[2], ...` endlicher Länge darstellbar.
        2. Jedes Element :java:`a[i]` hat eine endliche Anzahl von möglichen Werten :java:`A[i]`.

           D. h. die Menge der möglichen Werte pro :java:`a[i]` kann unterschiedlich sein.
        3. Es gibt einen effizienten Test, ob eine Teillösung :java:`a[1], a[2], ..., a[k]` zu einer gültigen Lösung führen kann.

    .. card::

        .. rubric:: Verfahren

        :Start: Wähle eine Teillösung :java:`a[1]`.
        :Allgemein:
            Ist eine Teillösung basierend auf :java:`a[1], a[2], ..., a[k-1]` noch keine Gesamtlösung, dann erweitere sie mit dem nächsten nicht ausgeschlossenen Wert :java:`a[k]` aus :java:`A[k]` zur neuen Teillösung :java:`a[1], a[2], ..., a[k]`.

            Falls noch nicht alle Elemente von :java:`A[K]`, die zu keiner inkonsistenten Lösungen führen, ausgeschöpft sind, dann gehe zurück (backtrack) und wähle :java:`a[k]` neu. Ggf. gehe zu  :java:`a[k-1]` usw. zurück.

.. supplemental::

    Es wird hier nicht gefordert, dass alle Element den gleichen Wertebereich haben. Es ist auch möglich, dass die Werte unterschiedlich sind.



.. class:: exercises transition-scale

Übung
------------------------------------------

.. exercise:: Auswerten logischer Ausdrücke mittels Backtracking

    Bestimmen Sie für folgenden Ausdruck ``c`` - mittels Backtracking - Wahrheitswerte für die Variablen, damit der Ausdruck als Ganzes wahr wird:

    ``c = (A ∨ ¬B) ∧ (¬A ∨ B) ∧ (¬A ∨ ¬C) ∧ (C ∨ D) ∧ (¬C ∨ ¬D)``

    Füllen Sie dazu die folgende Tabelle aus, um alle Lösungen zu finden. In der letzten Spalte geben Sie an, ob die Zeile eine Teillösung darstellt (nicht inkonsistent), keine Lösung ist bzw. sein kann, oder eine Gesamtlösung identifiziert wurde. Die Evaluation wie vieler vollständiger Belegungen wurde eingespart, wenn die Lösung gefunden wurde?

    .. csv-table::
        :header: " ", A, B, C, D, "nicht inkonsistent (T), keine Lösung (K), vollständige Lösung (L)"
        :align: center

        1, w, , , , T
        ..., ..., ..., ..., ..., ...
        16, ..., ..., ..., ..., ...

    .. solution::
        :pwd: Backtracking

        Es gibt 16 mögliche Belegungen (:math:`2^4`); nur 6 davon wurden vollständig evaluiert. 10 (vollständige) Belegungen wurden nicht getestet, da bereits Teillösungen als inkonsistent identifiziert wurden.

        .. csv-table::
            :header: A, B, C, D, "nicht inkonsistent (T), keine Lösung (K), vollständige Lösung (L)", "nicht geteste Belegungen"
            :align: center

            w, , , , T
            w, w, , , T
            w, w, w, , :red:`K`, 2
            w, w, f, , T, 2
            w, w, f, w, :green:`L`
            w, w, f, f, :red:`K`
            w, f, , , :red:`K`, 4
            f, , , , T
            f, w, , , :red:`K`, 4
            f, f, , , T
            f, f, w, , T
            f, f, w, w, :red:`K`
            f, f, w, f, :green:`L`
            f, f, f, , T
            f, f, f, w, :green:`L`
            f, f, f, f, :red:`K`



.. class:: exercises

Übung
------------------------------------------

.. exercise:: Das Erfüllbarkeitsproblem

    .. supplemental::

        .. rubric:: Konjunktive Normalform (KNF)

        Ein logischer Ausdruck ist in KNF, wenn der Ausdruck nur als Konjunktion (UND-Verknüpfung) von Disjunktionen (ODER-Verknüpfungen) dargestellt wird. Die Negation darf nur auf Variablen angewendet werden.

        Beispiel: (A ∨ B ∨ C) ∧ (¬C ∨ D)

    Entwickeln Sie ein Programm — in einer Programmiersprache Ihrer Wahl — das in der Lage ist eine Formel in konjunktiver Normalform (KNF) auf Erfüllbarkeit zu prüfen.

    Prüfen Sie Ihr Programm anhand der vorhergehenden Aufgabe.

    .. hint::

        Sollten Sie das Programm in Python oder Java implementieren wollen, dann können sie den entsprechenden Code im Anhang als Grundlage verwenden. Sie müssen dann nur noch die Methode ``solve`` implementieren. Der Code implementiert eine kleine Klassenhierarchie zur Repräsentation von logischen Ausdrücken und ermöglicht die Evaluation (``is_solution`` bzw. ``isSolution``) unter einer gegebenen Belegung.

    .. solution::
        :pwd: Anzahl_der_Belegungen

        .. rubric:: Prolog basierte Lösung

        Eine Lösung in (SWI)-Prolog könnte wie folgt aussehen (Zeile 1 bis 11 ist das vollständige Programm; Zeile 13 bis 30 implementiert die selbe Lösung nur schöner):

        .. include:: code/sat_model.pl
            :code: prolog
            :number-lines:
            :class: copy-to-clipboard

        .. rubric:: Python basierte Lösung

        .. include:: code/sat.py
            :code: python
            :number-lines: 135
            :class: copy-to-clipboard
            :start-after: variable to its current truth value (True or False)."""

        .. rubric:: Java basierte Lösung

        .. include:: code/sat.java
            :code: java
            :number-lines: 141
            :class: copy-to-clipboard
            :start-after: void solve(Expr expr, Stack<Var> vars, Map<Var, Boolean> solution) {

        Die drei Lösungen sind:

        .. code:: prolog
            :number-lines:

            A = B, B = D, D = true, C = false ;
            A = B, B = D, D = false, C = true ;
            A = B, B = C, C = false, D = true ;

.. supplemental::

    .. rubric:: |python-icon| Python Template

    .. include:: code/sat.py
        :code: python
        :number-lines:
        :class: copy-to-clipboard
        :end-before:     var = vars[0]

    .. rubric:: |java-icon| Java Template

    .. include:: code/sat.java
        :code: java
        :number-lines:
        :class: copy-to-clipboard
        :end-before:     final var var = vars.pop();



.. class:: exercises transition-move-to-top

Übung
--------------------------------------------------------

.. exercise:: Gruppenzuteilung

    Finden Sie eine sehr gute Aufteilung von Personen (Studierenden) auf eine feste Anzahl an Gruppen, basierend auf den Präferenzen der Personen zueinander. Nutzen Sie dazu Backtracking.

    Im Template ist eine initiale Aufgabenstellung hinterlegt, die es zu lösen gilt: Verteilung von 16 Studierenden auf 4 Gruppen inkl. Bewertungsmatrix :peripheral:`(jeder Studierende hat jeden anderen mit Werten von 1 bis 10 bewertet)`:

    |python-icon| |group_assignment_template.py|

    |java-icon| |GroupAssignmentTemplate.java|

    .. solution::
        :pwd: ALLE Kombinationen bewerten

        *Ein Lösungsvorschlag*

        .. include:: code/group_assignment.py
            :code: python
            :number-lines:
            :class: copy-to-clipboard
