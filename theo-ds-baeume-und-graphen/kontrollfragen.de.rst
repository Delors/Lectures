.. meta::
   :lang: de
   :author: Michael Eichberg
   :keywords: "Datenstrukturen", "Bäume", "Graphen", "Heaps", "Kontrollfragen"
   :description lang=de: Kontrollfragen zu Bäumen, Graphen und Heaps
   :id: lecture-theo-ds-baeume-und-graphen-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Datenstrukturen: Bäume, Graphen und Heaps - Kontrollfragen
===========================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0


.. class:: new-section

Graphen
-------------------------------------


.. class:: exercises

Kontrollfragen
-------------------------------------

.. deck::

   .. card::

      .. exercise:: Woraus besteht ein gerichteter Graph?

         .. solution::
            :pwd: GrApHdEf

            Ein gerichteter Graph :math:`G = (V, E)` wird durch eine Knotenmenge :math:`V = \{v_1, v_2, \ldots, v_n\}` und eine Kantenmenge :math:`E = \{(v_i, v_j) \mid v_i, v_j \in V\}` beschrieben. Die Kanten sind geordnete Paare, d. h. :math:`(v_i, v_j)` ist eine Kante von :math:`v_i` nach :math:`v_j`.

   .. card::

      .. exercise:: Was unterscheidet einen ungerichteten von einem gerichteten Graphen?

         .. solution::
            :pwd: UnGeRiChTeT

            Bei einem ungerichteten Graphen ist die Kantenrelation :math:`E` symmetrisch: :math:`(v_i, v_j) \in E \Rightarrow (v_j, v_i) \in E`. Die Kanten :math:`\{(v_i, v_j), (v_j, v_i)\}` werden als eine einzige Kante gezählt. Im gerichteten Graphen haben Kanten dagegen eine Richtung, und :math:`(v_i, v_j)` und :math:`(v_j, v_i)` sind zwei verschiedene Kanten.

   .. card::

      .. exercise:: Was ist ein gewichteter Graph?

         .. solution::
            :pwd: GeWiChTe

            Bei einem gewichteten Graphen :math:`G = (V, E)` besitzt jede Kante :math:`(v_i, v_j)` eine Attributierung :math:`w(v_i, v_j)`. Der Wertebereich von :math:`w` ist häufig :math:`\mathbb{N}`, :math:`\mathbb{Z}` oder :math:`\mathbb{R}`. Sowohl gerichtete als auch ungerichtete Graphen können gewichtet sein.

   .. card::

      .. exercise:: Was ist ein markierter Graph und welche Rolle spielen Markierungen?

         .. solution::
            :pwd: MaRkIeRt

            In einem markierten Graphen besitzt jeder Knoten :math:`v_i` eine Markierung :math:`m(v_i)`, die eine Attributierung des Knotens darstellt (häufig ein Farbwert). Markierungen spielen in vielen Graphenalgorithmen eine Rolle, z. B. bei der Breite-zuerst-Suche, um bereits besuchte Knoten zu kennzeichnen.

   .. card::

      .. exercise:: Wie viele Kanten kann es maximal zwischen zwei verschiedenen Knoten in einem gerichteten Graphen geben? Kann ein Knoten eine Kante zu sich selbst haben?

         .. solution::
            :pwd: KaNtEnMaX

            Da :math:`E` eine Menge ist und somit keine doppelten Elemente enthalten kann, gibt es maximal zwei Kanten zwischen zwei verschiedenen Knoten :math:`v_i` und :math:`v_j` (nämlich :math:`(v_i, v_j)` und :math:`(v_j, v_i)`). Ein Knoten kann maximal eine Kante zu sich selbst haben (eine sogenannte Schleife).

   .. card::

      .. exercise:: Was ist ein Pfad in einem Graphen und wie wird seine Länge definiert?

         .. solution::
            :pwd: PfAdLaEnGe

            Ein Pfad in einem Graphen :math:`G = (V, E)` ist eine Folge von Knoten :math:`v_{a_1}, v_{a_2}, \ldots, v_{a_k}`, bei der für alle :math:`i \in \{1, \ldots, k-1\}` gilt: :math:`(v_{a_i}, v_{a_{i+1}}) \in E`. Die Länge des Pfades ist die Anzahl der Kanten auf dem Pfad, also :math:`k - 1`. In einem Pfad dürfen Knoten mehrfach auftreten.


.. class:: new-section

Implementierung von Graphen
-------------------------------------


.. class:: exercises

Kontrollfragen
-------------------------------------

.. deck::

   .. card::

      .. exercise:: Welche zwei grundlegenden Datenstrukturen können zur Implementierung von Graphen verwendet werden?

         .. solution::
            :pwd: AdjAzEnZ

            Graphen können durch **Adjazenzlisten** oder **Adjazenzmatrizen** implementiert werden.

   .. card::

      .. exercise:: Beschreiben Sie die Struktur einer Adjazenzliste.

         .. solution::
            :pwd: AdjLiStE

            Bei einer Adjazenzliste gibt es eine Knotenliste :math:`L_0`, die alle Knoten aus :math:`V` enthält. Jedem Element :math:`v_i` der Knotenliste ist eine weitere Knotenliste :math:`L_i` zugeordnet, die die Endknoten der Kanten enthält, die von :math:`v_i` ausgehen. Konzeptionell wird ein Graph somit als Liste von Listen (bzw. als Map von Knoten auf Listen von Knoten) gespeichert.

   .. card::

      .. exercise:: Nennen Sie je zwei Vor- und Nachteile der Adjazenzliste gegenüber der Adjazenzmatrix.

         .. solution::
            :pwd: VoRnAcH

            **Vorteile der Adjazenzliste:**

            - Kompaktere Speicherung, insbesondere bei dünn besetzten Graphen
            - Einfache Erweiterbarkeit (Hinzufügen neuer Knoten)

            **Nachteile der Adjazenzliste:**

            - Aufwändigerer Zugriff bei der Adressierung von Kanten
            - Höhere Komplexität für Fragen wie: Existiert eine Kante von x nach y? Welche Kanten enden an einem Knoten x?

   .. card::

      .. exercise:: Wie ist eine Adjazenzmatrix aufgebaut und was bedeuten ihre Einträge?

         .. solution::
            :pwd: MaTrIxAuFbAu

            Die Zeilen und Spalten der Adjazenzmatrix :math:`M` sind mit der Knotenmenge :math:`V` beschriftet. Ist ein Element :math:`M_{x,y}` der Matrix :math-i:`1` bedeutet dies, dass eine gerichtete Kante von :math:`x` nach :math:`y` existiert. Bei gewichteten Graphen enthält die Matrix die Kantengewichte :math:`w(x, y)`. Eine Zeile beschreibt die ausgehenden Kanten, eine Spalte die eingehenden Kanten eines Knotens.

   .. card::

      .. exercise:: Wann ist die Adjazenzmatrix besonders ineffizient und warum?

         .. solution::
            :pwd: DuEnNbEsEtZt

            Die Adjazenzmatrix ist besonders ineffizient bei Graphen mit wenigen Kanten relativ zur Anzahl der Knoten (dünn besetzte Graphen), da die Matrix :math:`|V| \times |V|` Einträge hat, aber nur wenige davon von Null verschieden sind. Dies führt zu Speicherplatzverschwendung. Zudem erfordern Arrays als typische Implementierung Reorganisationsaufwand, wenn dem Graphen neue Knoten hinzugefügt werden.


.. class:: new-section

Suchalgorithmen auf Graphen
-------------------------------------


.. class:: exercises

Kontrollfragen
-------------------------------------

.. deck::

   .. card::

      .. exercise:: Was ist der Unterschied zwischen einem aktiven und einem passiven Knoten bei der Graphensuche?

         .. solution::
            :pwd: AkTiVpAsSiV

            Ein aktiver Knoten ist ein markierter Knoten, von dem aus die Suche gegebenenfalls später fortgesetzt werden kann. Ein neu markierter Knoten wird zunächst als aktiv gekennzeichnet. Ein aktiver Knoten :math:`v` wird passiv, wenn alle von :math:`v` ausgehenden Kanten dahingehend untersucht wurden, ob sie zu einem noch nicht markierten Knoten führen. Die Weitersuche ist nur von aktiven Knoten aus sinnvoll.

   .. card::

      .. exercise:: Welche Datenstruktur wird bei der Breite-zuerst-Suche zur Verwaltung der aktiven Knoten verwendet und warum?

         .. solution::
            :pwd: FiFoBfS

            Bei der Breite-zuerst-Suche wird eine **FIFO-Warteschlange** (Queue) verwendet. Gibt es keine Kante mehr zu einem unmarkierten Knoten, wird als neuer Suchknoten der **älteste** noch aktive Knoten gewählt. Dadurch werden Knoten ebenenweise (nach zunehmender Entfernung vom Startknoten) besucht.

   .. card::

      .. exercise:: Welche Datenstruktur wird bei der Tiefe-zuerst-Suche zur Verwaltung der aktiven Knoten verwendet und warum?

         .. solution::
            :pwd: LiFoDfS

            Bei der Tiefe-zuerst-Suche wird ein **Stack** (LiFO) verwendet. Gibt es keine Kante mehr zu einem unmarkierten Knoten, wird als neuer Suchknoten der **jüngste** noch aktive Knoten gewählt. Dadurch wird zuerst ein Pfad so weit wie möglich verfolgt, bevor Alternativen betrachtet werden.

   .. card::

      .. exercise:: Welche besondere Eigenschaft hat der Erreichbarkeitsbaum, der durch die Breite-zuerst-Suche erzeugt wird?

         .. solution::
            :pwd: KuErZeStEpFaDe

            Die Breite-zuerst-Suche erzeugt unter allen möglichen Erreichbarkeitsbäumen denjenigen mit den **kürzesten Pfaden** vom Startknoten zu allen erreichbaren Knoten. Dies liegt daran, dass die Pfade inkrementell und simultan (nicht sequentiell) aufgebaut werden. Knoten werden markiert, bevor lange Pfade entstehen können.

   .. card::

      .. exercise:: Beschreiben Sie den grundlegenden Ablauf einer Graphensuche (unabhängig von BFS oder DFS).

         .. solution::
            :pwd: GrUnDaBlAuF

            1. Markiere den Startknoten :math:`v_s`.
            2. Wähle eine Kante :math:`(v_i, v_j)`, bei der :math:`v_i` markiert und :math:`v_j` nicht markiert ist.
            3. Markiere :math:`v_j` und setze mit Schritt 2 fort.
            4. **Endekriterium:** Es existiert keine Kante mehr von einem markierten zu einem unmarkierten Knoten. Das Ergebnis ist genau ein Erreichbarkeitsbaum.

   .. card::

      .. exercise:: Wie unterscheidet sich der Schritt bzgl. der Kantenwahl bei der Tiefe-zuerst-Suche von der Breite-zuerst-Suche?

         .. solution::
            :pwd: S2UnTeRsChIeD

            **Breite-zuerst-Suche:** Vom aktuellen Suchknoten werden **alle** ausgehenden Kanten zu unmarkierten Knoten untersucht und diese markiert. Erst wenn keine solche Kante mehr existiert, wird der **älteste** aktive Knoten (FIFO) zum neuen Suchknoten.

            **Tiefe-zuerst-Suche:** Vom aktuellen Suchknoten wird **eine** Kante zu einem unmarkierten Knoten gesucht. Der bisherige Suchknoten wird als aktiv gespeichert, und der neu gefundene Knoten wird zum neuen Suchknoten. Erst wenn keine Kante mehr existiert, wird der **jüngste** aktive Knoten (Stack/LiFO) zum neuen Suchknoten.


.. class:: new-section

Bäume
-------------------------------------


.. class:: exercises

Kontrollfragen
-------------------------------------

.. deck::

   .. card::

      .. exercise:: Erklären Sie die folgenden Begriffe im Zusammenhang mit Bäumen: Wurzel, Blattknoten, innerer Knoten, Elternknoten, Kindknoten.

         .. solution::
            :pwd: BaUmBeGrIfFe

            - **Wurzel (Root):** Der einzige Knoten, der keinen Elternknoten hat.
            - **Blattknoten (Leaf):** Ein Knoten, der keine Kinder hat.
            - **Innerer Knoten (Inner Node):** Ein Knoten, der kein Blattknoten ist (also mindestens ein Kind hat).
            - **Elternknoten (Parent):** Ein Knoten, der einen oder mehrere Kinder hat.
            - **Kindknoten (Child):** Ein Knoten, der einen Elternknoten hat.

   .. card::

      .. exercise:: Was ist der Unterschied zwischen der Höhe eines Baumes und der Tiefe eines Knotens?

         .. solution::
            :pwd: HoEhEtIeFe

            Die **Höhe** eines Baumes ist die Länge des längsten Pfades vom Wurzelknoten zu einem Blattknoten. Die **Tiefe** eines Knotens :math:`p` ist die Länge des Pfades vom Wurzelknoten zu :math:`p`. Die Wurzel hat die Tiefe 0 und die Höhe des Baumes entspricht der maximalen Tiefe aller Blattknoten.

   .. card::

      .. exercise:: Was ist ein binärer Suchbaum und welche Eigenschaft muss er erfüllen?

         .. solution::
            :pwd: BiNsUcHbAuM

            Ein binärer Suchbaum ist ein Binärbaum (jeder Knoten hat höchstens zwei Kinder), bei dem für jeden Knoten gilt: Alle Schlüssel im **linken** Unterbaum sind **kleiner** und alle Schlüssel im **rechten** Unterbaum sind **größer** als der Schlüssel des aktuellen Knotens. Beide Unterbäume sind wiederum binäre Suchbäume (rekursive Definition). Optional kann jeder Knoten neben dem Schlüssel auch weitere Nutzdaten speichern.

   .. card::

      .. exercise:: In welcher Reihenfolge werden die Knoten bei einer Präorder-, Inorder- und Postorder-Traversierung besucht?

         .. solution::
            :pwd: TrAvErSiErUnG

            - **Präorder:** Erst wird der aktuelle (Wurzel-)Knoten verarbeitet, dann rekursiv der linke Unterbaum, dann der rechte Unterbaum. (Wurzel → Links → Rechts)
            - **Inorder:** Erst wird rekursiv der linke Unterbaum besucht, dann der aktuelle Knoten, dann der rechte Unterbaum. (Links → Wurzel → Rechts)
            - **Postorder:** Erst wird rekursiv der linke Unterbaum besucht, dann der rechte Unterbaum, dann der aktuelle Knoten. (Links → Rechts → Wurzel)

   .. card::

      .. exercise:: Welche besondere Eigenschaft hat die Inorder-Traversierung eines binären Suchbaums?

         .. solution::
            :pwd: InOrDeRsOrT

            Eine Inorder-Traversierung eines binären Suchbaums liefert die Knotenwerte in **aufsteigend sortierter** Reihenfolge. Dies ergibt sich direkt aus der Suchbaum-Eigenschaft: Alle Schlüssel im linken Unterbaum sind kleiner, alle im rechten Unterbaum sind größer. Somit stellt die Inorder-Traversierung ein weiteres Sortierverfahren dar.

   .. card::

      .. exercise:: Gegeben sei ein binärer Suchbaum mit den Werten 2, 1, 7, 4, 8, 3, 6, 5 (eingefügt in dieser Reihenfolge). Geben Sie die Reihenfolge der Knoten bei einer Postorder-Traversierung an.

         .. solution::
            :pwd: PoStOrDeR

            Der Baum hat die Struktur: 2 ist die Wurzel, 1 ist links, 7 ist rechts, darunter 4 (links von 7), 8 (rechts von 7), 3 (links von 4), 6 (rechts von 4), 5 (links von 6).

            Die Postorder-Traversierung ergibt: **1, 3, 5, 6, 4, 8, 7, 2**.

   .. card::

      .. exercise:: Welche Laufzeit hat die Suche in einem binären Suchbaum und wovon hängt sie ab?

         .. solution::
            :pwd: LaUfZeItBsT

            Die Laufzeit der Suche in einem binären Suchbaum ist **O(h)**, wobei :math:`h` die Höhe des Baumes ist. Im besten Fall (balancierter Baum) ist :math:`h = O(\log n)`, im schlimmsten Fall (degenerierter Baum, der einer linearen Liste entspricht) ist :math:`h = O(n)`.

   .. card::

      .. exercise:: Warum sollten häufig benötigte Schlüssel zuerst in einen binären Suchbaum eingefügt werden?

         .. solution::
            :pwd: HaEuFiGzUeRsT

            Schlüssel, die zuerst eingefügt werden, befinden sich näher an der Wurzel des Baumes. Da die Suche an der Wurzel beginnt, werden Schlüssel, die nahe der Wurzel liegen, mit weniger Vergleichen gefunden. Häufig benötigte Schlüssel sollten daher zuerst eingefügt werden, um die durchschnittliche Anzahl der Vergleiche (und damit die durchschnittliche Suchzeit) zu minimieren.


.. class:: new-section

Prioritätswarteschlangen und Heaps
-------------------------------------


.. class:: exercises

Kontrollfragen
-------------------------------------

.. deck::

   .. card::

      .. exercise:: Was ist eine Prioritätswarteschlange und welche Standardoperationen bietet sie?

         .. solution::
            :pwd: PrIoQuEuE

            Eine Prioritätswarteschlange ist eine Datenstruktur, bei der Elemente mit einer Priorität eingefügt werden und das Element mit der höchsten (oder niedrigsten) Priorität zuerst entnommen wird. Die Standardoperationen sind:

            - **Erzeugen** einer leeren Prioritätswarteschlange
            - **Einfügen** eines neuen Elements mit einer bestimmten Priorität
            - **Abfragen** des Elements mit der höchsten/niedrigsten Priorität
            - **Entfernen** des Elements mit der höchsten/niedrigsten Priorität

            Eine Prioritätswarteschlange ist eine Verallgemeinerung von Stacks und Queues.

   .. card::

      .. exercise:: Was ist ein Heap und welche Eigenschaft muss er erfüllen?

         .. solution::
            :pwd: HeApEiGeNsChAfT

            Ein Heap ist ein fast vollständiger Binärbaum, bei dem die sogenannte Heap-Eigenschaft gilt:

            - **Max-Heap:** Das Elternelement ist immer **größer oder gleich** seinen Kindern.
            - **Min-Heap:** Das Elternelement ist immer **kleiner oder gleich** seinen Kindern.

            „Fast vollständig" bedeutet, dass alle Ebenen bis auf die letzte vollständig besetzt sind. Die letzte Ebene wird von links nach rechts aufgefüllt.

   .. card::

      .. exercise:: Inwiefern ist eine Prioritätswarteschlange eine Verallgemeinerung von Stacks und Queues? Welchen Typ von Heap verwendet man ggf. zur Implementierung eines Stacks/eine Queue?

         .. solution::
            :pwd: VeRaLlGeMeInErUnG

            Wenn man als Schlüssel (Priorität) den Einfügezeitpunkt verwendet, kann man mit einer Prioritätswarteschlange sowohl Stacks als auch Queues simulieren:

            - **Stack (LiFO):** Das zuletzt eingefügte Element hat die höchste Priorität und wird zuerst entnommen. (D. h. wir verwenden ein Max-Heap)
            - **Queue (FiFO):** Das zuerst eingefügte Element hat die höchste Priorität und wird zuerst entnommen. (D. h. wir verwenden ein Min-Heap)

   .. card::

      .. exercise:: Wie wird ein Heap effizient in einem Array gespeichert? Geben Sie die Indexformeln für Eltern und Kinder an (Wurzel bei Index 0).

         .. solution::
            :pwd: HeaP->ArraY

            Ein Heap wird in einem Array gespeichert, indem die Knoten ebenenweise von links nach rechts abgelegt werden. Die Wurzel befindet sich an Index 0. Für ein Element an Index :math:`i` gilt:

            - **Elternknoten:** :math:`\lfloor(i - 1) / 2\rfloor`
            - **Linkes Kind:** :math:`2i + 1`
            - **Rechtes Kind:** :math:`2i + 2`

   .. card::

      .. exercise:: Beschreiben Sie den Ablauf beim Einfügen eines neuen Elements in einen Min-Heap.

         .. solution::
            :pwd: InSeRtHeAp

            1. Das neue Element wird am Ende des Arrays angefügt (an Position :math:`n`).
            2. Die Anzahl der Elemente wird um 1 erhöht (:math:`n \leftarrow n + 1`).
            3. **Up-Heap:** Solange das eingefügte Element kleiner als sein Elternelement ist, werden die beiden vertauscht. Das Element „wandert" so nach oben, bis die Heap-Eigenschaft wiederhergestellt ist oder die Wurzel erreicht wurde.

            Die Laufzeit ist :math:`O(\log n)`, da das Element maximal von einem Blatt bis zur Wurzel wandern muss.

   .. card::

      .. exercise:: Beschreiben Sie den Ablauf beim Entfernen des kleinsten Elements aus einem Min-Heap.

         .. solution::
            :pwd: ReMoVeHeAp

            1. Das Wurzelelement (kleinstes Element) wird entfernt.
            2. Das letzte Element des Arrays wird an die Wurzelposition verschoben.
            3. Die Anzahl der Elemente wird um 1 verringert.
            4. **Down-Heap:** Das neue Wurzelelement wird mit dem **kleineren** seiner beiden Kinder verglichen. Ist es größer, werden die beiden vertauscht. Dieser Vorgang wird wiederholt, bis die Heap-Eigenschaft wiederhergestellt ist oder ein Blatt erreicht wurde.

            **Wichtig:** Beim Down-Heap muss immer mit dem **Minimum** der beiden Kinder getauscht werden, da sonst die Heap-Eigenschaft verletzt werden könnte.

   .. card::

      .. exercise:: Welche Laufzeiten haben die Standardoperationen auf einem Heap?

         .. solution::
            :pwd: HeApLaUfZeIt

            - **Einfügen:** :math:`O(\log n)` (Up-Heap, maximal Höhe des Baumes)
            - **Entfernen des höchsten/niedrigsten Elements:** :math:`O(\log n)` (Down-Heap)
            - **Abfragen des höchsten/niedrigsten Elements:** :math:`O(1)` (Wurzel des Heaps)

   .. card::

      .. exercise:: Gegeben sei ein Min-Heap als Array: [1, 3, 2, 5, 4, 8, 6]. Zeichnen Sie den zugehörigen Binärbaum. Was passiert, wenn das Minimum entfernt wird?

         .. solution::
            :pwd: HeApBeIsPiEl

            Der Binärbaum sieht wie folgt aus:

            ::

                       1
                      / \
                     3   2
                    / \ / \
                   5  4 8  6

            Beim Entfernen des Minimums (1):

            1. Die 6 (letztes Element) wird zur neuen Wurzel: [6, 3, 2, 5, 4, 8]
            2. Down-Heap: 6 > 2 (kleineres Kind), also Tausch 6↔2: [2, 3, 6, 5, 4, 8]
            3. 6 > 8 ist falsch, kein weiterer Tausch nötig.

            Ergebnis:

            ::

                       2
                      / \
                     3   6
                    / \ /
                   5  4 8
