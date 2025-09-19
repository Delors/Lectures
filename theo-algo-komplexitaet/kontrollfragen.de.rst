.. meta:: 
   :version: renaissance
   :lang: de
   :author: Michael Eichberg
   :keywords: "Komplexität", "Algorithmen", "Kontrollfragen"
   :description lang=de: Theoretische Informatik - Komplexität und Algorithmen - Kontrollfragen
   :id: lecture-theo-algo-komplexitaet-kontrollfragen
   :first-slide: last-viewed
   :exercises-master-password: WirklichSchwierig!
   
.. include:: ../docutils.defs



Komplexität und Algorithmen - Kontrollfragen
====================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0.1



.. class:: exercises

Dynamische Programmierung
--------------------------

.. exercise:: Einsatz von dynamischer Programmierung

   Wann ist es sinnvoll, dynamische Programmierung einzusetzen?

   .. solution::
      :pwd: kommt-drauf-an

      Dynamische Programmierung ist sinnvoll, wenn ein Problem ...

      - in Teilprobleme zerlegt werden kann, 
      - die sich überlappen (d. h. die gleichen Teilprobleme mehrfach auftreten und ggf. mehrfache berechnet werden würden.)
  
.. exercise:: Minimale Anzahl an Münzen
   :class: incremental

   Gegeben sei ein Betrag ``n`` und eine Liste von Münzen ``coins``. Implementieren Sie eine naive rekursive Funktion ``minCoins(n: int, coins: list[int]) -> int``, die die minimale Anzahl an Münzen zurückgibt, die benötigt wird, um den Betrag `n` zu erreichen. 

   .. solution::
      :pwd: NichtsIstEinfacher

      .. include:: code/min_coins_rek.py
         :code: python
         :number-lines:

.. exercise:: Minimale Anzahl an Münzen mit dynamischer Programmierung
   :class: incremental

   Stellen Sie die Funktion ``minCoins(n: int, coins: list[int]) -> int`` so um, dass sie dynamische Programmierung einsetzt.
    
   .. solution::
      :pwd: AuchNicht+Schwer

      .. include:: code/min_coins_dynamic_programming.py
         :code: python
         :number-lines:



.. class:: exercises

Folgen
------

.. exercise:: Wichtige Grenzwerte

   Wie sind die Grenzwerte der folgenden Folgen:

   .. math::

      \lim_{{n \to \infty}} {q^n \over n!}\; \text{für} \; \ q \in \mathbb{C} \qquad \text{und} \qquad \lim_{{n \to \infty}} \sqrt[n]{n}  

   .. solution::
      :pwd: 42_ist_nicht_immer_die_Lösung

      Die erste Folge konvergiert gegen 0, wenn ``q`` ein beliebiger Wert ist. Die zweite Folge konvergiert gegen 1.

.. exercise:: Konvergenz einer Folge

   Gegen welchen Wert konvergiert die Folge: :math:`a_n = {n^3+n^2+1\over n^4}`

   Wie gehen Sie vor, um den Grenzwert einer Folge zu bestimmen?

   .. solution::
      :pwd: nullllllll

      Die Folge konvergiert gegen 0.

      :math:`a_n = {n^3+n^2+1\over n^4 } ={n^4(1/n + 1/n^2 + 1/n^4) \over n^4 } = 1/n + 1/n^2 + 1/n^4 = 0`

      Vorgehensweise: Termumformung mit dem Ziel, dass der Nenner und/oder der Zähler ein Konstanter Wert ist, um den Grenzwert zu bestimmen.



.. class:: exercises

Analyse des asymptotischen Verhaltens
--------------------------------------

.. exercise:: Asymptotisches Verhalten

   Bestimmen Sie das asymptotische Verhalten der folgenden Funktionen:

   .. math::

      f(x) = \frac{\ln x}{\log_2 x} \quad \text{für} \; x \rightarrow \infty

   .. solution::
      :pwd: 42_ist_nicht_immer_die_Lösung

      Anwendung der Logarithmusregel zum Basiswechsel (hier: :math:`\log_a b = \frac{\ln b}{\ln a}`) ergibt:

      :math:`f(x) = \frac{\ln x}{\log_2 x} = \frac{\ln x}{\ln x / \ln 2} = \ln 2`



.. class:: exercises

Landau-Notation
----------------

.. exercise:: Landau-Notation -  Prüfen Sie die folgenden Aussagen

   - Sei :math:`f \in O(g)`. Ist dann auch :math:`f \in \Omega(g)`?
   - :math:`\Theta(g) \subseteq O(g)`
   - Sei :math:`\lim_{x→∞} {f_1(x) \over f_2(x)} = \infty`. Ist dann :math:`f_1(x) \in \Omega(f_2(x))`?
   - Sei :math:`\lim_{x→∞} {f_1(x) \over f_2(x)} = 5`. Ist dann :math:`f_1(x) \in \Omega(f_2(x))` oder :math:`f_1(x) \in O(f_2(x))` oder :math:`f_1(x) \in \Theta(f_2(x))`?

   .. solution::
      :pwd: natuerlich_nicht

      - Nein, die Aussage gilt nicht. :math:`f \in O(g)` bedeutet, dass :math:`f` asymptotisch höchstens so schnell wächst wie :math:`g`. :math:`f \in \Omega(g)` bedeutet, dass :math:`f` asymptotisch mindestens so schnell wächst wie :math:`g`. Es gibt Funktionen, die in :math:`O(g)` sind, aber nicht in :math:`\Omega(g)` und umgekehrt.
      - Ja; :math:`\Theta(g)` ist eine Menge von Funktionen, die sowohl in :math:`O(g)` als auch in :math:`\Omega(g)` sind. Daher ist :math:`\Theta(g) \subseteq O(g)`.
      - Ja, die Aussage gilt. :math:`\lim_{x→∞} {f_1(x) \over f_2(x)} = \infty` bedeutet, dass :math:`f_1(x)` asymptotisch schneller wächst als :math:`f_2(x)`. Daher ist :math:`f_1(x) \in \Omega(f_2(x))`.
      - In diesem Fall ist :math:`f_1(x)` sowohl in :math:`\Omega(f_2(x))` als auch :math:`\Theta(f_2(x))` und auch in :math:`O(f_2(x))`.
  
  

.. class:: exercises

Rekurrenz-Gleichungen und das Master Theorem
----------------------------------------------

.. exercise:: Anwendung des Master-Theorems

   Analysieren Sie die folgenden Rekurrenz-Gleichungen mit Hilfe des Master-Theorems:  

   1) Gegeben sei: :math:`T(n) = 9 \cdot T(n/3) + 3n^2\log_2n`.
  
   2) Gegeben sei: :math:`T(n) = 1 \cdot T(n/4) + \frac{1}{3}n^2`.
  
   .. solution:: 
      :pwd: es_ist_nicht_so_schwer   

      .. rubric:: 1. Lösung
      
      :Analyse: 
            
            - :math:`a = 9`, :math:`b = 3`, :math:`f(n) = 3n^2\log_2n`.

            - :math:`n^{\log_b a} = n^{\log_3 9} = n^2`. 

            - Durchtesten:
            
              :math:`f(n) \notin O(n^{2-\epsilon})` (D. h. :math:`f(n)` wächst schneller als :math:`n^{2-\epsilon}` für ein beliebiges :math:`\epsilon > 0`.)

              :math:`f(n) \in \Theta(n^{2}\cdot(\log n)^k)` für :math:`k=1`.

      :Ergebnis: Daher ist die Laufzeit :math:`T(n) \in \Theta(n^2\cdot (\log_2 n)^2)`. (Beachte, dass der zweite Faktor :math:`(\log n)^{k+1}` ist.)

      .. rubric:: 2. Lösung

      :Analyse:

         - :math:`a = 1`, :math:`b = 4`, :math:`f(n) = \frac{1}{3}n^2`.
         - :math:`n^{\log_b a} = n^{\log_4 1} = n^0 = 1`.
         - Durchtesten:
            
           :math:`f(n) \notin O(n^{0-\epsilon})` für ein :math:`\epsilon > 0`.

           :math:`f(n) \notin \Theta(1 \cdot(\log n)^k)` für ein beliebiges :math:`k`.

           :math:`f(n) \in \Omega(n^{0+\epsilon})` für ein :math:`0 < \epsilon \leq 2`. Weiterhin gilt :math:`a \cdot f(n/b) \leq c \cdot f(n)` da :math:`1 \cdot \frac{1}{3}(n/4)^2 = \frac{1}{3} \frac{1}{16}n^2 \leq c \cdot \frac{1}{3} \cdot n^2` für :math:`c = 1/16`.

      :Ergebnis: Daher ist die Laufzeit :math:`T(n) \in \Theta(n^2)`.
           
        