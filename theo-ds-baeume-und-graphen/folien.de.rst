.. meta::
    :version: renaissance
    :lang: de
    :author: Michael Eichberg
    :keywords: "Datenstrukturen"
    :description lang=de: Datenstrukturen - Bäume und Graphen
    :id: lecture-theo-ds-baeume-und-graphen
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Datenstrukturen - Bäume und Graphen
====================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1

.. container:: peripheral footer-left

    :Quelle:
        Die Folien sind teilweise inspiriert von oder basierend auf:

        Lehrmaterial von Prof. Dr. Scherer, Prof. Dr. Baumgart

.. supplemental::

    :Folien:

        |html-source|

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-move-to-top

Graphen
--------------------------------------------------------



Gerichteter Graph
---------------------

.. definition::

    Ein gerichteter Graph :math:`G = (V, E)` wird durch eine Knotenmenge :math:`V` und eine Kantenmenge :math:`E` beschrieben.

    :math:`V= \{ v_1, v_2, . . . , v_n \}`

    :math:`E= \{ (v_i, v_j ) | v_i, v_j ∈ V \}`

.. remark::
    :class: incremental

    .. class:: incremental-list

    • Es ist erlaubt, dass ein Knoten (Vertex) eine Kante (Edge) zu sich selbst besitzt
    • Da :math:`E` eine Menge ist, kann es keine doppelten Elemente geben; folglich gilt:

      - Es gibt maximal zwei Kanten zwischen zwei Knoten vi und vj (i ̸= j)
      - Ein Knoten kann maximal eine Kante zu sich selbst haben
      - Statt :math:`v_1, . . . v_n` werden häufig (Groß-) Buchstaben A, B, C etc. verwendet



Gerichteter Graph - Beispiel
-------------------------------

.. raw:: html
    :class: center-content

    <div    style="width: 48ch; height: 28ch; container-type:size;">
    <svg    viewBox="0 0 24 14"
            font-size="2"
            version="1.1" xmlns="http://www.w3.org/2000/svg">
        <style>
            g.graph {
                circle{
                    fill: black;
                    stroke-width: 0.2;

                    &.red {
                        fill: red;
                    }

                    &.green {
                        fill: green;
                    }

                    &.blue {
                        fill: blue;
                    }
                }
                path, 
                line {
                    fill: none;
                    stroke:darkgray;
                    stroke-width:0.2;
                }
                text {
                    fill: black;
                    font-family: var(--theme-code-font-family);
                    font-weight: bold;

                    &.edge-label {
                        fill: darkorange;
                        font-weight: normal;
                        font-size: 60%;
                    }
                }                
            }
        </style>
        <defs>
            <marker
            id="arrow"
            viewBox="0 0 2 2"
            refX="2"
            refY="1"
            markerUnits="strokeWidth"
            markerWidth="6"
            markerHeight="6"
            orient="auto-start-reverse">
            <path class="arrow-head" d="M 0 0 L 2 1 L 0 2 z" />
            </marker>
        </defs>
        <g class="graph">
            <g>
            <circle cx="8" cy="1" r="0.5" />
            <text x="7.5" y="3.25">A</text>
            </g>

            <g class="incremental">
            <circle cx="1" cy="6" r="0.5" />
            <line x1="1.35" y1="5.65" x2="7.65" y2="1.35" marker-end="url(#arrow)" />
            <path d="M 7.5 1 C 4 0.5, 1 3, 1 5.5" marker-end="url(#arrow)" />
            <text x="0.5" y="8.25">B</text>
            </g>

            <g class="incremental">
            <circle cx="15" cy="6" r="0.5" />
            <line x1="14.65" y1="5.65" x2="8.35" y2="1.35" marker-end="url(#arrow)"/>
            <text x="15.75" y="6.75">C</text>
            </g>

            <g class="incremental">
            <circle cx="8" cy="11" r="0.5" />
            <line x1="8.35" y1="10.65" x2="14.5" y2="6" marker-end="url(#arrow)" />
            <path d="M 8 10.5 C 5 6.5, 2.5 12, 7.5 11" marker-end="url(#arrow)" />
            <text x="7.5" y="13.25">D</text>
            </g>

            <g class="incremental">
            <circle cx="22" cy="11" r="0.5" />
            <line x1="21.5" y1="11" x2="8.5" y2="11" marker-end="url(#arrow)" />
            <path d="M 22 10.5 C 22 5.5, 16 1, 8.5 1" marker-end="url(#arrow)" />
            <text x="21.5" y="13.25">E</text>
            </g>
        </g>
    </svg>
    </div>



Ungerichteter Graph
---------------------

.. definition::

    Ein ungerichteter Graph :math:`G = (V, E)` ist ein gerichteter Graph, in dem die Relation :math:`E` symmetrisch ist:

    :math:`(v_i, v_j ) ∈ E ⇒ (v_j , v_i) ∈ E`

    Die Kanten :math:`\{(v_i, v_j ), (v_j, v_i)\}` werden dann als eine Kante gezählt.



Ungerichteter Graph - Beispiel
-------------------------------

.. raw:: html
    :class: center-content

    <div    style="width: 48ch; height: 28ch;">
    <svg    viewBox="0 0 24 14"
            font-size="2"
            version="1.1" xmlns="http://www.w3.org/2000/svg">
        <!-- the previous style is reused!-->
        <g class="graph">
            <g>
            <circle cx="8" cy="1" r="0.5" />
            <text x="7.5" y="3.25">A</text>
            </g>

            <g class="incremental">
            <circle cx="1" cy="6" r="0.5" />
            <line x1="1.35" y1="5.65" x2="7.65" y2="1.35" />
            <text x="0.5" y="8.25">B</text>
            </g>

            <g class="incremental">
            <circle cx="15" cy="6" r="0.5" />
            <line x1="14.65" y1="5.65" x2="8.35" y2="1.35"/>
            <text x="15.75" y="6.75">C</text>
            </g>

            <g class="incremental">
            <circle cx="8" cy="11" r="0.5" />
            <line x1="8.35" y1="10.65" x2="14.5" y2="6" />
            <path d="M 8 10.5 C 5 6.5, 2.5 12, 7.5 11" />
            <text x="7.5" y="13.25">D</text>
            </g>

            <g class="incremental">
            <circle cx="22" cy="11" r="0.5" />
            <line x1="21.5" y1="11" x2="8.5" y2="11" />
            <path d="M 22 10.5 C 22 5.5, 16 1, 8.5 1" />
            <text x="21.5" y="13.25">E</text>
            </g>
        </g>
    </svg>
    </div>



Gewichteter Graph
-------------------

.. definition::

    Bei einem gewichteten Graphen :math:`G = (V, E)` besitzt jede Kante :math:`(vi, vj)` eine Attributierung :math:`w(v_i, v_j)`.

.. remark::

    • Sowohl gerichtete als auch ungerichtete Graphen können gewichtet sein
    • Die Funktion :math:`w` hat als Wertebereich oft :math:`\mathbb{N}, \mathbb{Z}` oder :math:`\mathbb{R}`
    • Die Attributierung kann aber auch aus mehreren Attributen bestehen, z. B. aus einer Zahl und einer (Kanten-) Farbe; für dieses Beispiel sind die Ergebnisse von :math:`w` dann Zweitupel



Gewichteter Graph - Beispiel
-------------------------------

.. raw:: html
    :class: center-content

    <div    style="width: 48ch; height: 28ch; container-type:size;">
    <svg    viewBox="0 0 24 14"
            font-size="2"
            version="1.1" xmlns="http://www.w3.org/2000/svg">
        <!-- the previous style is reused!-->
        <g class="graph">
            <g>
            <circle cx="8" cy="1" r="0.5" />
            <text x="7.5" y="3.25">A</text>
            </g>

            <g>
            <circle cx="1" cy="6" r="0.5" />
            <line x1="1.35" y1="5.65" x2="7.65" y2="1.35" />
            <text x="0.5" y="8.25">B</text>
            </g>

            <g>
            <circle cx="15" cy="6" r="0.5" />
            <line x1="14.65" y1="5.65" x2="8.35" y2="1.35"/>
            <text x="15.75" y="6.75">C</text>
            </g>

            <g>
            <circle cx="8" cy="11" r="0.5" />
            <line x1="8.35" y1="10.65" x2="14.5" y2="6" />
            <path d="M 8 10.5 C 5 6.5, 2.5 12, 7.5 11" />
            <text x="7.5" y="13.25">D</text>
            </g>

            <g>
            <circle cx="22" cy="11" r="0.5" />
            <line x1="21.5" y1="11" x2="8.5" y2="11" />
            <path d="M 22 10.5 C 22 5.5, 16 1, 8.5 1" />
            <text x="21.5" y="13.25">E</text>
            </g>

            <g class="incremental" >
                <text class="edge-label" x="5.5" y="4">8</text>
                <text class="edge-label" x="12.5" y="4">2</text>
                <text class="edge-label" x="12.5" y="8.5">12</text>
                <text class="edge-label" x="15" y="12.25">7</text>
                <text class="edge-label" x="3.5" y="11">7</text>
                <text class="edge-label" x="20" y="5">9</text>
            </g>
        </g>
    </svg>
    </div>



Markierte Graphen
--------------------

.. definition::

    In einem markierter Graphen :math:`G = (V, E)` besitzt jeder Knoten :math:`v_i` eine Markierung :math:`m(v_i)`.

.. remark::

    • Die Markierung stellt eine Attributierung des Knotens dar
    • Die Markierung ist häufig ein Farbwert
    • Alle Typen von Graphen (gerichtet, ungerichtet, gewichtet) können markiert werden
    • Die Markierung spielt in vielen Graphenalgorithmen eine Rolle (z. B. bei der Breite-zuerst-Suche)



Markierter (gewichteter) Graph - Beispiel
-----------------------------------------

.. raw:: html
    :class: text-align-center

    <div    style="width: 48ch; height: 28ch; container-type:size;">
    <svg    viewBox="0 0 24 14"
            font-size="2"
            version="1.1" xmlns="http://www.w3.org/2000/svg">
        <!-- the previous style is reused!-->
        <g class="graph">
            <g>
            <circle class="red" cx="8" cy="1" r="0.5"/>
            <text x="7.5" y="3.25">A</text>
            </g>

            <g>
            <circle class="green" cx="1" cy="6" r="0.5" />
            <line x1="1.35" y1="5.65" x2="7.65" y2="1.35" />
            <text x="0.5" y="8.25">B</text>
            </g>

            <g>
            <circle class="blue" cx="15" cy="6" r="0.5" />
            <line x1="14.65" y1="5.65" x2="8.35" y2="1.35"/>
            <text x="15.75" y="6.75">C</text>
            </g>

            <g>
            <circle class="green" cx="8" cy="11" r="0.5" />
            <line x1="8.35" y1="10.65" x2="14.5" y2="6" />
            <path d="M 8 10.5 C 5 6.5, 2.5 12, 7.5 11" />
            <text x="7.5" y="13.25">D</text>
            </g>

            <g>
            <circle cx="22" cy="11" r="0.5" class="blue" />
            <line x1="21.5" y1="11" x2="8.5" y2="11" />
            <path d="M 22 10.5 C 22 5.5, 16 1, 8.5 1" />
            <text x="21.5" y="13.25">E</text>
            </g>

            <g>
                <text class="edge-label" x="5.5" y="4">8</text>
                <text class="edge-label" x="12.5" y="4">2</text>
                <text class="edge-label" x="12.5" y="8.5">12</text>
                <text class="edge-label" x="15" y="12.25">7</text>
                <text class="edge-label" x="3.5" y="11">7</text>
                <text class="edge-label" x="20" y="5">9</text>
            </g>
        </g>
    </svg>
    </div>



Pfad
-----

.. definition::

    Ein Pfad in einem Graphen :math:`G = (V, E)` mit :math:`V= \{ v_1, . . . , v_n \}` ist eine Folge von Knoten :math:`v_{a_1}, v_{a_2}, . . . , v_{a_k}` mit folgender Eigenschaft: :math:`(v_{a_i}, v_{a_{i+1}}) ∈ E` für :math:`i ∈ \{ 1, . . . , k− 1 \}`.

    Die Länge des Pfades ist die Anzahl der Kanten auf dem Pfad, d. h. :math:`k− 1`.

.. remark::

    • In einem Pfad dürfen Knoten mehrfach auftreten
    • Für Pfade wird häufig eine Listenschreibweise verwendet: :math:`< v3, v1, v2, v3, v4 >`



Fragestellungen auf Graphen
-----------------------------

.. class:: incremental-list

• Welche Knoten sind von einem (Start-) Knoten aus erreichbar? → Erreichbarkeitsbaum
• Existiert zwischen 2 Knoten A und B ein Pfad, d. h. ist B von A aus erreichbar?
• Wenn mehrere Pfade zwischen 2 Knoten existieren, welcher ist der kürzeste?
• Existieren in einem Graphen Zyklen?



Implementierung von Graphen
---------------------------

.. grid::

    .. cell:: width-40

        .. raw:: html
            :class: text-align-center

            <div    style="width: 24ch; height: 14ch; container-type:size;">
            <svg    viewBox="0 0 24 14"
                    font-size="2"
                    version="1.1" xmlns="http://www.w3.org/2000/svg">
                <defs>
                    <marker
                    id="arrow"
                    viewBox="0 0 2 2"
                    refX="2"
                    refY="1"
                    markerUnits="strokeWidth"
                    markerWidth="6"
                    markerHeight="6"
                    orient="auto-start-reverse">
                    <path class="arrow-head" d="M 0 0 L 2 1 L 0 2 z" />
                    </marker>
                </defs>
                <g class="graph">
                    <g>
                    <circle cx="8" cy="1" r="0.5" />
                    <text x="7.5" y="3.25">A</text>
                    </g>

                    <g>
                    <circle cx="1" cy="6" r="0.5" />
                    <line x1="1.35" y1="5.65" x2="7.65" y2="1.35" marker-end="url(#arrow)" />
                    <path d="M 7.5 1 C 4 0.5, 1 3, 1 5.5" marker-end="url(#arrow)" />
                    <text x="0.5" y="8.25">B</text>
                    </g>

                    <g>
                    <circle cx="15" cy="6" r="0.5" />
                    <line x1="14.65" y1="5.65" x2="8.35" y2="1.35" marker-end="url(#arrow)"/>
                    <text x="15.75" y="6.75">C</text>
                    </g>

                    <g>
                    <circle cx="8" cy="11" r="0.5" />
                    <line x1="8.35" y1="10.65" x2="14.5" y2="6" marker-end="url(#arrow)" />
                    <path d="M 8 10.5 C 5 6.5, 2.5 12, 7.5 11" marker-end="url(#arrow)" />
                    <text x="7.5" y="13.25">D</text>
                    </g>

                    <g>
                    <circle cx="22" cy="11" r="0.5" />
                    <line x1="21.5" y1="11" x2="8.5" y2="11" marker-end="url(#arrow)" />
                    <path d="M 22 10.5 C 22 5.5, 16 1, 8.5 1" marker-end="url(#arrow)" />
                    <text x="21.5" y="13.25">E</text>
                    </g>
                </g>
            </svg>
            </div>

    .. cell:: width-60

        .. deck::

            .. card::

                .. rubric:: Adjazenzliste

                .. deck::

                    .. card::

                        Struktur der Adjazenzlisten für einen Graphen :math:`G = (V, E)`:

                        Es gibt eine Knotenliste :math:`L_0`, die alle Knoten aus :math:`V` enthält.

                        Jedem Element :math:`v_i (i ∈ { v_1, . . . , v_n })` der Knotenliste :math:`L_0` ist eine weitere Knotenliste :math:`L_i` zugeordnet, die die Endknoten der Kanten aus :math:`E` enthält, die von :math:`v_i` ausgehen

                          Wir speichern einen Graphen somit als Liste von Listen.

                    .. card::

                        .. example::

                            Seien :java:`A` bis :java:`E` Instanzen der Klasse :java:`Node`, die einen Knoten im Graphen repräsentieren.

                            ::

                                // Konzeptionell
                                // L0 = List<Tuple<Node,List<Node>>>(
                                L0 = Map<Node,List<Node>>.of(
                                    A, List.of(B),
                                    B, List.of(A),
                                    C, List.of(A),
                                    D, List.of(D,C),
                                    E, List.of(D,A),
                                )

                    .. card::

                        **Bewertung**

                        .. class:: incremental-list  positive-list

                        - Kompaktere Speicherung
                        - Erweiterbarkeit

                        .. class:: incremental-list  negative-list

                        - Aufwändigerer Zugriﬀ z. B. bei der Adressierung der Kanten
                        - Höhere Komplexität für die Beantwortung von Fragen wie

                          - Existiert eine Kante von x nach y?
                          - Welche Kanten enden an einem Knoten x?

            .. card::

                .. rubric:: Adjazenzmatrix

                .. deck::

                    .. card::

                        Struktur der Adjazenzmatrix für einen Graphen :math:`G = (V, E)`:

                        - Die Zeilen und Spalten der Adjazenzmatrix sind mit der Knotenmenge V beschriftet
                        - Die Matrixelemente dienen zur Darstellung der Kantenrelation E eines Graphen G = (V, E)

                        AJ[x, y] = 1 ⇔ es existiert eine gerichtete Kante von x nach y
                        - Bei einem gewichteten Graphen enthält die Adjazenzmatrix die Kantengewichte w(x, y)

                    .. card::

                        .. example::

                            .. csv-table::
                                :stub-columns: 1
                                :class: highlight-cell-on-hover
                                :header: " ", "A", "B", "C", "D", "E"

                                "A", 0, 1, 0, 0, 0
                                "B", 1, 0, 0, 0, 0
                                "C", 1, 0, 0, 0, 0
                                "D", 0, 0, 1, 1, 0
                                "E", 1, 0, 0, 1, 0

                    .. card::

                        **Bewertung**

                        .. class:: positive-list incremental-list

                        - Extrem schnelle und einfache Adressierbarkeit der Kanten
                        - Semantik einer Zeile: Welche Kanten gehen von dem zugehörigen Knoten weg
                        - Semantik einer Spalte: Welche Kanten enden an dem zugehörigen Knoten

                        .. class:: negative-list incremental-list

                        - Bei wenigen Kante ist die Matrix nur dünn besetzt → Speicherplatzverschwendung
                        - Arrays als typische Implementierung für Matrizen erfordern Reorganisationsaufwand, wenn dem Graphen neue Knoten hinzugefügt werden



.. class:: new-subsection

Suchalgorithmen auf Graphen
---------------------------



Suchalgorithmen auf Graphen - Grundlegendes
--------------------------------------------

.. deck::

    .. card::

        **Eingaben**

        - (Gerichteter) Graph :math:`G = (V, E)`
        - Startknoten :math:`v_s`

        **Ablauf**

        - S1: Markiere Startknoten :math:`v_s`
        - S2: Wähle Kante :math:`(v_i, v_j)` mit folgenden Eigenschaften:

          :math:`v_i` ist markiert und :math:`v_j` ist nicht markiert
        - S3: Markiere :math:`v_j` und setze mit Schritt S2 fort

        **Endebedingung**

        Es existiert in Schritt S2 kein unmarkierter Knoten mehr

        **Ergebnis**

        Genau ein Erreichbarkeitsbaum wird markiert.

    .. card::

        **Wichtige Varianten**

        - Breite-zuerst-Suche
        - Tiefe-zuerst-Suche

        (Die Unterscheidung erfolgt im Schritt S2 in Hinblick auf die auszuwählende Kante.)

    .. card::

        **Benötigte Konzepte**

        • Aktiver und passiver Knoten

          Kennzeichnung eines Knotens als aktiv, wenn von ihm aus die Suche (gegebenenfalls) später fortgesetzt werden kann.
        • allg. Vorgehensweise

          - Kennzeichnung eines neu markierten Knotens als aktiv

          - Ein aktiver Knoten :math:`v` wird passiv, wenn gilt:

            Alle von :math:`v` ausgehenden Kanten wurden dahingehend untersucht, ob sie zu einem noch nichtmarkierten Knoten führen.

        **Ergebnis**

        - Weitersuche ist nur von aktiven Knoten aus sinnvoll
        - Nur markierte Knoten können aktiv sein



Breite-zuerst-Suche (:eng:`Breadth-first-Search`)
-------------------------------------------------

.. deck::

    .. card::

        **Im Schritt S2**

        .. class:: incremental-list list-with-explanations

        - Durchsuchung aller vom Suchknoten ausgehenden Kanten dahingehend, ob sie zu einem (bisher) unmarkierten Knoten führen.
        - Markierung eines derartigen Knotens.
        - Aufnahme des Knotens in die Liste aktiver Knoten.
        - Gibt es keine derartige Kante mehr, wähle als neuen Suchknoten den ältesten noch aktiven Knoten.

          (Als Datenstruktur dient die Verwendung einer FIFO-Warteschlange)

        .. container:: incremental

            **Endekriterium**

            Vom Suchknoten aus wird kein unmarkierter Nachfolgerknoten mehr gefunden und die Liste aktiver Knoten ist leer.

    .. card::

        .. important::

            Unter allen möglichen Erreichbarkeitsbäumen wird derjenige mit den kürzesten Pfaden erzeugt.

            Dies liegt an der Struktur der Suchsteuerung in S2:

            - Inkrementeller Aufbau der Pfade
            - Weiterhin werden die Pfade nicht sequentiell, sondern simultan aufgebaut

            ⇒ Knoten werden markiert, bevor lange Pfade entstehen können



Breite-zuerst-Suche - Beispiel
-------------------------------

.. raw:: html
    :class: text-align-center

    <div    style="width: 48ch; height: 28ch; container-type:size;">
    <svg    viewBox="0 0 24 14"
            font-size="2"
            version="1.1" xmlns="http://www.w3.org/2000/svg">
            <!-- the previous style is (also) used!-->
        <style>
            g.graph {
                circle{
                    &.start-node {
                        fill: none;
                        stroke: red;
                        stroke-width: 0.3;
                    }
                    &.visited-node {
                        fill: lightgray;
                        stroke: blue;
                        stroke-width: 0.3;
                    }
                    &.unreached-node {
                        fill: lightgray;
                        stroke: none;
                        stroke-width: 0.3;
                    }
                }

                line, path { 
                    &.irrelevant-edge {
                        stroke: red;
                        stroke-width: 0.2;
                    }
                }
            }
        </style>
        <defs>
            <marker
            id="arrow"
            viewBox="0 0 2 2"
            refX="2"
            refY="1"
            markerUnits="strokeWidth"
            markerWidth="6"
            markerHeight="6"
            orient="auto-start-reverse">
            <path class="arrow-head" d="M 0 0 L 2 1 L 0 2 z" />
            </marker>
        </defs>

        <g class="graph">
            <g>
            <circle cx="8" cy="1" r="0.5" />
            <text x="7.5" y="3.25">A</text>
            </g>

            <g>
            <circle cx="1" cy="6" r="0.5" />
            <line x1="1.35" y1="5.65" x2="7.65" y2="1.35" marker-end="url(#arrow)" />
            <path d="M 7.5 1 C 4 0.5, 1 3, 1 5.5"  marker-end="url(#arrow)"/>
            <text x="0.5" y="8.25">B</text>
            </g>

            <g>
            <circle cx="15" cy="6" r="0.5" />
            <line x1="14.65" y1="5.65" x2="8.35" y2="1.35" marker-end="url(#arrow)"/>
            <text x="15.75" y="6.75">C</text>
            </g>

            <g>
            <circle cx="8" cy="11" r="0.5" />
            <line x1="8.35" y1="10.65" x2="14.5" y2="6" marker-end="url(#arrow)" />
            <path d="M 8 10.35 C 5 6.5, 2.5 12, 7.5 11" marker-end="url(#arrow)" />
            <text x="7.5" y="13.25">D</text>
            </g>

            <g>
            <circle cx="22" cy="11" r="0.5" />
            <line x1="21.5" y1="11" x2="8.5" y2="11"  marker-end="url(#arrow)"/>
            <path d="M 22 10.5 C 22 5.5, 16 1, 8.5 1" marker-end="url(#arrow)" />
            <text x="21.5" y="13.25">E</text>
            </g>

            <g class="incremental" >
                <circle class="start-node" cx="22" cy="11" r="0.5" />
            </g>

            <g class="incremental" >
                <circle class="visited-node" cx="8" cy="11" r="0.5" />
                <text class="edge-label" x="15.5" y="10.5">1</text>
            </g>

            <g class="incremental" >
                <circle class="visited-node" cx="8" cy="1" r="0.5" />
                <text class="edge-label" x="17" y="4.5">2</text>
            </g>

            <g class="incremental" >
                <text class="edge-label" x="3.5" y="10.5">3</text>
                <path class="irrelevant-edge" stroke-dasharray="0.2,0.2" d="M 8 10.35 C 5 6.5, 2.5 12, 7.5 11" marker-end="url(#arrow)" />
            </g>

            <g class="incremental" >
                <circle class="visited-node" cx="15" cy="6" r="0.5" />
                <text class="edge-label" x="11.5" y="9.25">4</text>
            </g>

            <g class="incremental" >
                <circle class="visited-node" cx="1" cy="6" r="0.5" />
                <text class="edge-label" x="1.5" y="2.5">5</text>
            </g>

            <g class="incremental" >
                <text class="edge-label" x="12.5" y="4">6</text>
                <line class="irrelevant-edge" stroke-dasharray="0.2,0.2" x1="14.65" y1="5.65" x2="8.35" y2="1.35" marker-end="url(#arrow)"/>
            </g>

            <g class="incremental" >
                <line class="irrelevant-edge" stroke-dasharray="0.2,0.2" x1="1.35" y1="5.65" x2="7.65" y2="1.35" marker-end="url(#arrow)" />
                <text class="edge-label" x="4.5" y="5">7</text>
            </g>
        </g>
    </svg>
    </div>



Tiefe-zuerst-Suche
-------------------

**Im Schritt S2**

.. class:: incremental-list list-with-explanations

- Suche vom aktuellen Suchknoten :math:`nd` eine Kante zu einem (bisher) unmarkierten Knoten :math:`nd_{neu}`
- Markierung von :math:`nd_{neu}`
- Aufnahme des Knotens :math:`nd` (nicht :math:`nd_{neu}`!) in die Liste aktiver Knoten
- :math:`nd_{neu}` wird zum neuen Suchknoten und die Suche wird mit :math:`nd_{neu}` fortgesetzt
- Gibt es keine derartige Kante mehr, wähle als neuen Suchknoten den jüngsten noch aktiven Knoten

  (Zu verwendende Datenstruktur: Stack (LiFO))

.. container:: incremental

    **Endekriterium**

    Vom Suchknoten aus wird kein unmarkierter Nachfolgerknoten mehr gefunden und die Liste aktiver Knoten ist leer.



Tiefe-zuerst-Suche - Beispiel
-------------------------------

.. raw:: html
    :class: text-align-center

    <div    style="width: 48ch; height: 28ch; container-type:size;">
    <svg    viewBox="0 0 24 14"
            font-size="2"
            version="1.1" xmlns="http://www.w3.org/2000/svg">
            <!-- the previous style is (also) used!-->
        <style>
            g.graph {
                circle{
                    &.start-node {
                        fill: none;
                        stroke: red;
                        stroke-width: 0.3;
                    }
                    &.visited-node {
                        fill: lightgray;
                        stroke: blue;
                        stroke-width: 0.3;
                    }
                    &.unreached-node {
                        fill: lightgray;
                        stroke: none;
                        stroke-width: 0.3;
                    }
                }

                line, path {
                    &.irrelevant-edge {
                        stroke: red;
                        stroke-width: 0.2;
                    }
                }
            }
        </style>
        <defs>
            <marker
            id="arrow"
            viewBox="0 0 2 2"
            refX="2"
            refY="1"
            markerUnits="strokeWidth"
            markerWidth="6"
            markerHeight="6"
            orient="auto-start-reverse">
            <path class="arrow-head" d="M 0 0 L 2 1 L 0 2 z" />
            </marker>
        </defs>

        <g class="graph">
            <g>
            <circle cx="8" cy="1" r="0.5" />
            <text x="7.5" y="3.25">A</text>
            </g>

            <g>
            <circle cx="1" cy="6" r="0.5" />
            <line x1="1.35" y1="5.65" x2="7.65" y2="1.35" marker-end="url(#arrow)" />
            <path d="M 7.5 1 C 4 0.5, 1 3, 1 5.5"  marker-end="url(#arrow)"/>
            <text x="0.5" y="8.25">B</text>
            </g>

            <g>
            <circle cx="15" cy="6" r="0.5" />
            <line x1="14.65" y1="5.65" x2="8.35" y2="1.35" marker-end="url(#arrow)"/>
            <text x="15.75" y="6.75">C</text>
            </g>

            <g>
            <circle cx="8" cy="11" r="0.5" />
            <line x1="8.35" y1="10.65" x2="14.5" y2="6" marker-end="url(#arrow)" />
            <path d="M 8 10.5 C 5 6.5, 2.5 12, 7.5 11" marker-end="url(#arrow)" />
            <text x="7.5" y="13.25">D</text>
            </g>

            <g>
            <circle cx="22" cy="11" r="0.5" />
            <line x1="21.5" y1="11" x2="8.5" y2="11"  marker-end="url(#arrow)"/>
            <path d="M 22 10.5 C 22 5.5, 16 1, 8.5 1" marker-end="url(#arrow)" />
            <text x="21.5" y="13.25">E</text>
            </g>

            <g class="incremental" >
                <circle class="start-node" cx="22" cy="11" r="0.5" />
            </g>
            <g class="incremental" >
                <circle class="visited-node" cx="8" cy="11" r="0.5" />
                <text class="edge-label" x="15.5" y="10.5">1</text>
            </g>
            <g class="incremental" >
                <circle class="visited-node" cx="15" cy="6" r="0.5" />
                <text class="edge-label" x="11.5" y="9.5">2</text>
            </g>
            <g class="incremental" >
                <circle class="visited-node" cx="8" cy="1" r="0.5" />
                <text class="edge-label" x="12.5" y="4">3</text>
            </g>
            <g class="incremental" >
                <circle class="visited-node" cx="1" cy="6" r="0.5" />
                <text class="edge-label" x="1.5" y="2.5">4</text>
            </g>

            <g class="incremental" >
                <text class="edge-label" x="4.5" y="5">5</text>
                <line class="irrelevant-edge" stroke-dasharray="0.2,0.2" x1="1.35" y1="5.65" x2="7.65" y2="1.35" marker-end="url(#arrow)"  />
            </g>

            <g class="incremental" >
                <text class="edge-label" x="3.5" y="10.5">6</text>
                <path class="irrelevant-edge" d="M 8 10.5 C 5 6.5, 2.5 12, 7.5 11" marker-end="url(#arrow)" stroke-dasharray="0.2,0.2" />
            </g>

            <g class="incremental" >
                <text class="edge-label" x="17" y="4.5">7</text>
                <path class="irrelevant-edge" d="M 22 10.5 C 22 5.5, 16 1, 8.5 1" marker-end="url(#arrow)" stroke-dasharray="0.2,0.2"/>
            </g>
        </g>
    </svg>
    </div>



.. class:: exercises

Übung
---------

.. exercise:: Tiefe-/Breite-zuerst-Suche

    Gegeben sei der folgenden Graph:

    .. raw:: html
        :class: text-align-center

        <div    style="width: 36ch; height: 21ch; container-type:size;">
        <svg    viewBox="0 0 24 14"
                font-size="2"
                version="1.1" xmlns="http://www.w3.org/2000/svg">
                <!-- the previous style is (also) used!-->
            <defs>
                <marker
                id="arrow"
                viewBox="0 0 2 2"
                refX="2"
                refY="1"
                markerUnits="strokeWidth"
                markerWidth="6"
                markerHeight="6"
                orient="auto-start-reverse">
                <path class="arrow-head" d="M 0 0 L 2 1 L 0 2 z" />
                </marker>
            </defs>

            <g class="graph">
                <g>
                <circle cx="8" cy="1" r="0.5" />
                <text x="8.25" y="4.25">1</text>
                <line x2="14.65" y2="5.65" x1="8.35" y1="1.35" marker-end="url(#arrow)"/>
                <path d="M 8.5 1 C 16 1, 22 5.5, 22 10.5" marker-end="url(#arrow)" />
                </g>

                <g>
                <circle cx="1" cy="6" r="0.5" />
                <line x1="1.35" y1="5.65" x2="7.65" y2="1.35" marker-end="url(#arrow)" />
                <path d="M 7.5 1 C 4 0.5, 1 3, 1 5.5"  marker-end="url(#arrow)"/>

                <text x="0.5" y="8.25">2</text>
                </g>

                <g>
                <circle cx="15" cy="6" r="0.5" />
                <text x="15.75" y="6.75">3</text>
                </g>

                <g>
                <circle cx="8" cy="11" r="0.5" />
                <line x1="8.35" y1="10.65" x2="14.5" y2="6" marker-end="url(#arrow)" />
                <line x1="8.35" y1="10.65" x2="8" y2="1.5" marker-end="url(#arrow)" />
                <path d="M 8 10.5 C 5 6.5, 2.5 12, 7.5 11" marker-end="url(#arrow)" />
                <text x="7.5" y="13.25">4</text>
                </g>

                <g>
                <circle cx="22" cy="11" r="0.5" />
                <line x1="21.5" y1="11" x2="8.5" y2="11"  marker-end="url(#arrow)"/>
                <text x="21.5" y="13.25">5</text>
                </g>
            </g>
        </svg>
        </div>

    Führen Sie sowohl eine Tiefe- als auch Breite-zuerst-Suche ausgehend von dem Knoten 4 als auch Knoten 5 durch. Sollte es mehrere Möglichkeiten geben, dann wählen Sie zuerst den Knoten mit der kleineren Nummer!

    .. solution::
        :pwd: Graphen!

        .. rubric:: Tiefe-zuerst-Suche von 4 aus.

        ::

            4 -> 1 -> 2 (Ende)
                   -> 5 (Ende)
                -> 3 (Ende)

        .. rubric:: Breite-zuerst-Suche von 5 aus.

        ::

            4 -> 1
              -> 3
                    (Fortsetzung von 1) -> 2 (Ende)
                                        -> 5 (Ende)

        .. rubric:: Tiefe-zuerst-Suche von 5 aus.

        ::

            5 -> 4 -> 1 -> 2 (Ende)
                   -> 3 (Ende)

        .. rubric:: Breite-zuerst-Suche von 5 aus.

        ::

            5 -> 4 -> 1
                   -> 3
                        (Fortsetzung von 1) -> 2 (Ende)



.. class:: new-section transition-move-to-top

Bäume
--------------------------------------------------------



Bäume - Einführung
------------------

- Bäume sind eine Datenstruktur für hierarchische Daten

  .. rubric:: Beispiele

  .. class:: incremental-list

  - Stammbaum
  - Dateisystem
  - Organisationsstruktur

.. class:: incremental-list

- relevante Operationen

  - Schnelles Abändern
  - Schnelles Suchen



Bäume - Schlüsselbegriffe
-------------------------

.. grid::

    .. cell:: width-30

        .. rubric:: Beispielbaum

        .. container:: text-align-center

            .. raw:: html

                <div    style="width: 21ch; height: 22ch; container-type:size;">
                <svg    viewBox="0 0 21 22"
                        version="1.1" xmlns="http://www.w3.org/2000/svg"
                        font-size="2">
                    <style>
                        g.tree {
                            circle {
                                fill: darkgray;
                                filter: drop-shadow( 2 2 4 rgb(0, 0, 0));
                            }
                            line {
                                stroke:darkgray;
                                stroke-width:0.2;
                            }
                            text {
                                fill: black;
                                font-family: var(--theme-code-font-family);
                                font-weight: bold;
                            }
                        }
                    </style>
                    <g class="tree">
                        <g>
                        <circle cx="8" cy="1" r="0.5" />
                        <text x="8.75" y="1.5">2</text>
                        </g>

                        <g class="incremental">
                        <circle cx="3" cy="6" r="0.5" />
                        <line x1="3" y1="6" x2="8" y2="1" />
                        <text x="1.5" y="6.5">1</text>
                        </g>

                        <g class="incremental">
                        <circle cx="13" cy="6" r="0.5" />
                        <line x1="13" y1="6" x2="8" y2="1" />
                        <text x="13.75" y="6.5">7</text>
                        </g>

                        <g class="incremental">
                        <circle cx="8" cy="11" r="0.5" />
                        <line x1="8" y1="11" x2="13" y2="6" />
                        <text x="8.75" y="11.5">4</text>
                        </g>

                        <g class="incremental">
                        <circle cx="18" cy="11" r="0.5" />
                        <line x1="18" y1="11" x2="13" y2="6" />
                        <text x="18.75" y="11.5">8</text>
                        </g>

                        <g class="incremental">
                        <circle cx="3" cy="16" r="0.5" />
                        <line x1="3" y1="16" x2="8" y2="11" />
                        <text x="1.5" y="16.5">3</text>
                        </g>

                        <g class="incremental">
                        <circle cx="13" cy="16" r="0.5" />
                        <line x1="13" y1="16" x2="8" y2="11" />
                        <text x="13.5" y="16.5">6</text>
                        </g>

                        <g class="incremental">
                            <circle cx="8" cy="21" r="0.5" />
                            <line x1="8" y1="21" x2="13" y2="16" />
                            <text x="8.75" y="21.5">5</text>
                        </g>
                    </g>
                </svg>
                </div>

    .. cell:: width-70 incremental

        .. deck::

            .. card::

                Elternknoten (:eng:`Parent`)

                Ein Elternknoten ist ein Knoten, der einen oder mehrere Kinder hat.

                .. example::

                    2 ist der Elternknoten von 7

            .. card::

                Kindknoten (:eng:`Child(ren)`)

                Kindknoten sind Knoten, die einen Elternknoten haben.

                .. example::

                    1 und 7 sind Kindknoten von 2

            .. card::

                Wurzel (:eng:`Root`)

                Der Wurzelknoten ist der einzige Knoten, der keinen Elternknoten hat.

                .. example::

                    2 ist die Wurzel/der Wurzelknoten

            .. card::

                Blattknoten (:eng:`Leaf`)

                Ein Blattknoten ist ein Knoten, der keine Kinder hat.

                .. example::

                    8, 3, 5 sind Blattknoten

            .. card::

                Innerer Knoten (:eng:`Inner Node`)

                Ein Knoten, der kein Blattknoten ist, ist ein innerer Knoten.

                .. example::

                    4, 7 und 6 sind innere Knoten. Auch der Wurzelknoten ist ein innerer Knoten, wenn der Baum nicht degeniert ist.


            .. card::

                Die Höhe eines Baumes ist die Länge des längsten Pfades vom Wurzelknoten zu einem Blattknoten.

            .. card::

                Die Tiefe eines Knotens :math:`p` ist die Länge des Pfades vom Wurzelknoten zu :math:`p`.

            .. card::

                Binärbaum

                .. definition::

                    Ein Binärbaum ist ein Baum, bei dem jeder Knoten höchstens zwei Kinder hat.

            .. card::

                Binärer Suchbaum

                .. definition::

                    Ein binärer Suchbaum ist ein Binärbaum, bei dem für jeden Knoten alle Schlüssel in den linken Unterbäumen kleiner sind und alle Schlüssel in den rechten Unterbäumen größer sind.

                .. example::

                    (siehe Darstellung)

            .. card::

                .. attention::

                    Da die Kanten in einem Baum grundsätzlich von einem Eltern- zu einem Kindknoten gehen (d. h. es sind gerichtete Kanten), wird die Kantenrichtung typischerweise nicht angegeben (d. h. es wird keine Pfeilspitze gezeichnet).



Traversierung von Bäumen
--------------------------

.. TODO Pfeile den Grafiken hinzufügen, um klar zu machen wann die Knoten besucht werden.

.. deck::

    .. card::

        Traversierung von Bäumen beschreibt die Reihenfolge, in der Knoten eines Baumes besucht werden.

        Wir können drei Formen von Traversierung unterscheiden:

        1. Präorder
        2. Inorder und
        3. Postorder

        Traversierung.

    .. card::

        .. rubric:: Präorder Traversierung (:eng:`Preorder Traversal`)

        .. grid::

            .. cell:: width-70

                .. example::

                    .. raw:: html

                        <div    style="width: 48ch; height: 22ch; container-type:size;">
                        <svg    font-size="2"
                                viewBox="0 0 48 22" version="1.1" xmlns="http://www.w3.org/2000/svg">
                            <style>
                                g.tree {
                                    circle.visited-node {
                                        fill: lightgray;
                                        stroke: blue;
                                        stroke-width: 0.3;
                                    }
                                }
                            </style>
                            <g class="tree">
                                <g>
                                <circle cx="8" cy="1" r="0.5" />
                                <text x="8.75" y="1.55">2</text>
                                </g>

                                <g>
                                <circle cx="3" cy="6" r="0.5" />
                                <line x1="3" y1="6" x2="8" y2="1" />
                                <text x="1.5" y="6.5">1</text>
                                </g>

                                <g>
                                <circle cx="13" cy="6" r="0.5" />
                                <line x1="13" y1="6" x2="8" y2="1" />
                                <text x="13.5" y="6.5">7</text>
                                </g>

                                <g>
                                <circle cx="8" cy="11" r="0.5" />
                                <line x1="8" y1="11" x2="13" y2="6" />
                                <text x="8.75" y="11.5">4</text>
                                </g>

                                <g>
                                <circle cx="18" cy="11" r="0.5" />
                                <line x1="18" y1="11" x2="13" y2="6" />
                                <text x="18.75" y="11.5">8</text>
                                </g>

                                <g>
                                <circle cx="3" cy="16" r="0.5" />
                                <line x1="3" y1="16" x2="8" y2="11" />
                                <text x="1.5" y="16.5">3</text>
                                </g>

                                <g>
                                <circle cx="13" cy="16" r="0.5" />
                                <line x1="13" y1="16" x2="8" y2="11" />
                                <text x="13.75" y="16.5">6</text>
                                </g>

                                <g>
                                    <circle cx="8" cy="21" r="0.5" />
                                    <line x1="8" y1="21" x2="13" y2="16" />
                                    <text x="8.75" y="21.5">5</text>
                                </g>

                                <g class="incremental">
                                    <text x="13.75" y="1.75">Reihenfolge = [ </text>
                                    <circle class="visited-node" cx="8" cy="1" r="0.5" />
                                    <text x="30" y="1.75">2,</text>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="3" cy="6" r="0.5" />
                                    <text x="32" y="1.75">1,</text>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="13" cy="6" r="0.5" />
                                    <text x="34" y="1.75">7,</text>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="8" cy="11" r="0.5" />
                                    <text x="36" y="1.75">4,</text>
                                </g>


                                <g class="incremental">
                                    <circle class="visited-node" cx="3" cy="16" r="0.5" />
                                    <text x="38" y="1.75">3,</text>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="13" cy="16" r="0.5" />
                                    <text x="40" y="1.75">6,</text>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="8" cy="21" r="0.5" />
                                    <text x="42" y="1.75">5,</text>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="18" cy="11" r="0.5" />
                                    <text x="44" y="1.75">8]</text>
                                </g>

                            </g>
                        </svg>
                        </div>

            .. cell:: width-30

                .. definition::

                    Eine Präorder Traversierung eines (Teil-)Baumes beginnt mit der Verarbeitung des aktuellen (Wurzel-)Knotens, danach besuchen wir rekursiv den linken Unterbaum, bevor der rechte Unterbaum besucht wird.

    .. card::

        .. rubric:: Inorder Traversierung (:eng:`Inorder Traversal`)

        .. grid::

            .. cell:: width-70

                .. example::

                    .. raw:: html

                        <div    style="width: 48ch; height: 22ch; container-type:size;">
                        <svg    font-size="2" viewBox="0 0 48 22" 
                                version="1.1" xmlns="http://www.w3.org/2000/svg">
                            <g class="tree">
                                <g>
                                    <circle cx="8" cy="1" r="0.5" />
                                    <text x="8.75" y="1.75">2</text>
                                </g>

                                <g>
                                    <circle cx="3" cy="6" r="0.5" />
                                    <line x1="3" y1="6" x2="8" y2="1" />
                                    <text x="1.5" y="6.5">1</text>
                                </g>

                                <g>
                                    <circle cx="13" cy="6" r="0.5" />
                                    <line x1="13" y1="6" x2="8" y2="1" />
                                    <text x="13.5" y="6.5">7</text>
                                </g>

                                <g>
                                    <circle cx="8" cy="11" r="0.5" />
                                    <line x1="8" y1="11" x2="13" y2="6" />
                                    <text x="8.75" y="11.5">4</text>
                                </g>

                                <g>
                                    <circle cx="18" cy="11" r="0.5" />
                                    <line x1="18" y1="11" x2="13" y2="6" />
                                    <text x="18.75" y="11.5">8</text>
                                </g>

                                <g>
                                    <circle cx="3" cy="16" r="0.5" />
                                    <line x1="3" y1="16" x2="8" y2="11" />
                                    <text x="1.5" y="16.5">3</text>
                                </g>

                                <g>
                                    <circle cx="13" cy="16" r="0.5" />
                                    <line x1="13" y1="16" x2="8" y2="11" />
                                    <text x="13.75" y="16.5">6</text>
                                </g>

                                <g>
                                    <circle cx="8" cy="21" r="0.5" />
                                    <line x1="8" y1="21" x2="13" y2="16" />
                                    <text x="8.75" y="21.5">5</text>
                                </g>


                                <g class="incremental">
                                    <text x="13.75" y="1.75">Reihenfolge = [ </text>
                                    <circle class="visited-node" cx="3" cy="6" r="0.5" />
                                    <text x="30" y="1.75">1,</text>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="8" cy="1" r="0.5" />
                                    <text x="32" y="1.75">2,</text>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="3" cy="16" r="0.5" />
                                    <text x="34" y="1.75">3,</text>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="8" cy="11" r="0.5" />
                                    <text x="36" y="1.75">4,</text>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="8" cy="21" r="0.5" />
                                    <text x="38" y="1.75">5,</text>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="13" cy="16" r="0.5" />
                                    <text x="40" y="1.75">6,</text>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="13" cy="6" r="0.5" />
                                    <text x="42" y="1.75">7,</text>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="18" cy="11" r="0.5" />
                                    <text x="44" y="1.75">8]</text>
                                </g>

                            </g>
                        </svg>
                        </div>

            .. cell:: width-30

                .. definition::

                    Eine Inorder-Traversierung eines Baumes beginnt mit den linken Unterbaum, besucht dann den (Wurzel-)knoten, bevor der rechte Unterbaum besucht wird.

        .. remark::
            :class: incremental

            Eine Inorder-Traversierung eines (binären) Suchbaums führt zu einer sortierten Liste der Knotenwerte. Es handelt sich somit um ein weiteres Beispiel für ein Sortierverfahren.

    .. card::

        .. rubric:: Postorder Traversierung (:eng:`Postorder Traversal`)

        .. grid::

            .. cell:: width-70

                .. example::

                    .. raw:: html

                        <div    style="width: 48ch; height: 22ch; container-type:size;">
                        <svg    font-size="2"
                                viewBox="0 0 48 22" version="1.1" xmlns="http://www.w3.org/2000/svg">
                            <style>
                                g.tree {
                                    line, path {
                                        &.traversal-edge {
                                            fill: none;
                                            stroke: red;
                                            stroke-width: 0.2;
                                        }
                                    }
                                }
                            </style>
                            <defs>
                                <marker
                                id="small-arrow"
                                viewBox="0 0 2 2"
                                refX="2"
                                refY="1"
                                markerUnits="strokeWidth"
                                markerWidth="4"
                                markerHeight="4"
                                orient="auto-start-reverse">
                                <path class="arrow-head" d="M 0 0 L 2 1 L 0 2 z" />
                                </marker>
                            </defs>
                            <g class="tree">
                                <g>
                                <circle cx="8" cy="1" r="0.5" />
                                <text x="8.75" y="1.55">2</text>
                                </g>

                                <g>
                                <circle cx="3" cy="6" r="0.5" />
                                <line x1="3" y1="6" x2="8" y2="1" />
                                <text x="1.5" y="6.5">1</text>
                                </g>

                                <g>
                                <circle cx="13" cy="6" r="0.5" />
                                <line x1="13" y1="6" x2="8" y2="1" />
                                <text x="13.5" y="6.5">7</text>
                                </g>

                                <g>
                                <circle cx="8" cy="11" r="0.5" />
                                <line x1="8" y1="11" x2="13" y2="6" />
                                <text x="8.75" y="11.5">4</text>
                                </g>

                                <g>
                                <circle cx="18" cy="11" r="0.5" />
                                <line x1="18" y1="11" x2="13" y2="6" />
                                <text x="18.75" y="11.5">8</text>
                                </g>

                                <g>
                                <circle cx="3" cy="16" r="0.5" />
                                <line x1="3" y1="16" x2="8" y2="11" />
                                <text x="1.5" y="16.5">3</text>
                                </g>

                                <g>
                                <circle cx="13" cy="16" r="0.5" />
                                <line x1="13" y1="16" x2="8" y2="11" />
                                <text x="13.75" y="16.5">6</text>
                                </g>

                                <g>
                                    <circle cx="8" cy="21" r="0.5" />
                                    <line x1="8" y1="21" x2="13" y2="16" />
                                    <text x="8.75" y="21.5">5</text>
                                </g>

                                <text x="13.75" y="1.75">Reihenfolge = [ </text>

                                <g class="incremental">
                                    <line   class="traversal-edge"
                                            x1="7" y1="1" x2="3" y2="5"
                                            marker-end="url(#small-arrow)"
                                            stroke-dasharray="0.2, 0.2"/>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="3" cy="6" r="0.5" />
                                    <text x="30" y="1.75">1,</text>
                                </g>

                                <g class="incremental">
                                    <path   class="traversal-edge"
                                            d="M 4 6 L 8 2 L 12 6 L 3 15"
                                            marker-end="url(#small-arrow)"
                                            stroke-dasharray="0.2, 0.2"/>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="3" cy="16" r="0.5" />
                                    <text x="32" y="1.75">3,</text>
                                </g>

                                <g class="incremental">
                                    <path   class="traversal-edge"
                                            d="M 4 16 L 8 12 L 12 16 L 8 20"
                                            marker-end="url(#small-arrow)"
                                            stroke-dasharray="0.2, 0.2"/>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="8" cy="21" r="0.5" />
                                    <text x="34" y="1.75">5,</text>
                                </g>

                                <g class="incremental">
                                    <path   class="traversal-edge"
                                            d="M 9 21 L 13 17"
                                            marker-end="url(#small-arrow)"
                                            stroke-dasharray="0.2, 0.2"/>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="13" cy="16" r="0.5" />
                                    <text x="36" y="1.75">6,</text>
                                </g>

                                <g class="incremental">
                                    <path   class="traversal-edge"
                                            d="M 13 15 L 9 11"
                                            marker-end="url(#small-arrow)"
                                            stroke-dasharray="0.2, 0.2"/>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="8" cy="11" r="0.5" />
                                    <text x="38" y="1.75">4,</text>
                                </g>

                                <g class="incremental">
                                    <path   class="traversal-edge"
                                            d="M 9 11 L 13 7 L 17 11"
                                            marker-end="url(#small-arrow)"
                                            stroke-dasharray="0.2, 0.2"/>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="18" cy="11" r="0.5" />
                                    <text x="40" y="1.75">8,</text>
                                </g>

                                <g class="incremental">
                                    <path   class="traversal-edge"
                                            d="M 18 10 L 14 6"
                                            marker-end="url(#small-arrow)"
                                            stroke-dasharray="0.2, 0.2"/>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="13" cy="6" r="0.5" />
                                    <text x="42" y="1.75">7,</text>
                                </g>

                                <g class="incremental">
                                    <path   class="traversal-edge"
                                            d="M 13 5 L 9 1"
                                            marker-end="url(#small-arrow)"
                                            stroke-dasharray="0.2, 0.2"/>
                                </g>

                                <g class="incremental">
                                    <circle class="visited-node" cx="8" cy="1" r="0.5" />
                                    <text x="44" y="1.75">2]</text>
                                </g>

                            </g>
                        </svg>
                        </div>

            .. cell:: width-30

                .. definition::

                    Eine Postorder-Traversierung eines Baumes beginnt mit den linken Unterbaum und besucht dann den rechten Unterbaum, bevor der (Wurzel-)knoten besucht wird.



Implementierung eines binären Suchbaums (BST)
---------------------------------------------------

.. scrollable::

    .. code:: java
        :number-lines:
        :class: copy-to-clipboard

        // BST.java

        import java.util.Deque;
        import java.util.LinkedList;
        import java.util.Queue;

        public class BST<Key extends Comparable<Key>, Value> {

            private class Node {
                private Key key;
                private Value val;
                private Node left, right;
                private int count;

                public Node(Key key, Value val) {
                        this.key = key;
                        this.val = val;
                        this.count = 1;
                }
            }

            private Node root;

            public void put(Key key, Value val) {
                root = put(root, key, val);
            }

            private Node put(Node x, Key key, Value val) {
                if (x == null)
                        return new Node(key, val);
                int cmp = key.compareTo(x.key);
                if (cmp < 0)
                        x.left = put(x.left, key, val);
                else if (cmp > 0)
                        x.right = put(x.right, key, val);
                else if (cmp == 0)
                        x.val = val;
                x.count = 1 + size(x.left) + size(x.right);

                return x;
            }

            public Value get(Key key) {
                Node x = root;
                while (x != null) {
                    int cmp = key.compareTo(x.key);
                    if (cmp < 0)
                        x = x.left;
                    else if (cmp > 0)
                        x = x.right;
                    else if (cmp == 0)
                        return x.val;
                }
                return null;
            }

            public int size() {
                return size(root);
            }

            private int size(Node x) {
               if (x == null)
                     return 0;
               return x.count;
            }

            public Key select(int k) { // returns the nth-largest key
                if (k < 0)         return null;
                if (k >= size())   return null;
                Node x = select(root, k);
                return x.key;
            }

            private Node select(Node x, int k) {
                if (x == null) return (Node) null;
                int t = size(x.left);
                if (t > k)
                    return select(x.left, k);
                else if (t < k)
                    return select(x.right, k - t - 1);
                else // if (t == k)
                    return x;
            }

            public void delete(Key key) {
                /* root = */ delete(root, key);
            }

            private Node delete(Node x, Key key) {
                if (x == null)     return null;
                int cmp = key.compareTo(x.key);
                if (cmp < 0)       x.left = delete(x.left, key);
                else if (cmp > 0)  x.right = delete(x.right, key);
                else {
                        if (x.right == null)     return x.left;
                        if (x.left == null)      return x.right;

                        Node t = x;
                        x = min(t.right);
                        x.right = deleteMin(t.right);
                        x.left = t.left;
                }
                x.count = size(x.left) + size(x.right) + 1;
                return x;
            }
        }



.. class:: exercises

Übung
----------------

.. exercise:: Optimale Einfügereihenfolge

   Nehmen wir an, dass wir im Voraus schätzen können, wie oft auf Suchschlüssel in einem binären Baum zugegriffen wird. Sollten die Schlüssel in wachsender oder fallender Reihenfolge der zu erwartenden Zugriffshäufigkeit in den Baum eingefügt werden? Warum?

   .. solution::
      :pwd: 1-2-3

      Zuerst sollten die Schlüssel eingefügt werden, die später häufig benötigt werden, da diese Schlüssel nahe der Wurzel einsortiert werden und somit die Anzahl der Vergleiche geringer sein wird.

.. exercise:: Minimum bestimmen

    Implementieren Sie die Methode :java:`min` (:java:`public Node min() {...}`) als rekursive Methode.

    .. solution::
        :pwd: rek-min!

        .. code:: Java

            public Node min() {
                if (root == null)
                    return null;
                else
                    return min(root);
            }

            private Node min(Node x) {
                if (x.left == null)
                    return x;
                else
                    return min(x.left);
            }

            /* Min nicht-rekursiv
            public Node minNonRecursive() {
                Node x = root;
                if (x == null)
                    return null;
                while (x.left != null)
                    x = x.left;
                return x;
            }
            */



.. exercise:: Rückgabe in absteigender Reihenfolge

    Implementieren Sie eine Methode :java:`public Iterable<Key> descending(){... }`, die die Schlüssel in umgekehrter Reihenfolge zurückgibt (d. h. der größte Schlüssel zuerst, der kleinste Schlüssel zuletzt). Implementieren Sie diese Methode rekursiv.

    .. remark::
        :class: peripheral

        Es ist eine gute Hausübung die Methode auch einmal nicht-rekursiv zu implementieren.

    .. solution::
        :pwd: so-geht-es-auch

        .. rubric:: Rekursiv

        .. code:: Java

            public Iterable<Key> descending() {
                Deque<Key> q = new LinkedList<Key>();
                descending(root, q);
                return q;
            }

            private void descending(Node x, Deque<Key> q) {
                if (x == null)
                    return;
                descending(x.right, q);
                q.add(x.key);
                descending(x.left, q);
            }
