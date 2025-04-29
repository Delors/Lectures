.. meta::
    :version: renaissance
    :lang: de
    :author: Michael Eichberg
    :keywords: "Compiler", "Interpreter", "VMs", "Java"
    :description lang=de: Von Compilern, Interpretern und virtuellen Maschinen
    :id: lecture-prog-compiler-interpreter-vms
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!
    
.. include:: ../docutils.defs  



Von Compilern, Interpretern und virtuellen Maschinen
=====================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0.1

.. supplemental::

    :Folien: 
        
        |html-source| 

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues

    :Kontrollfragen:

        .. source:: kontrollfragen.de.rst 
            :path: relative
            :prefix: https://delors.github.io/
            :suffix: .html



.. class:: new-section transition-move-to-top

Einführung
------------------------------------------------

Interpreter
------------------------------------------------

.. definition::

    Ein Interpreter ist ein Programm, das ein Programm einer bestimmten Programmiersprache direkt ausführt.

Arbeitsweise eines Interpreters:

.. container:: peripheral

    (:java:`inpExpr` sei der aktueller Ausdruck des auszuführenden Programms)

1. Syntaktische Analyse von :java:`inpExpr`
2. Überführung von :java:`inpExpr` in eine Befehlsfolge der Maschinensprache, oder der Sprache, in der das Interpreterprogramm selbst geschrieben ist (:java:`outExpr`) 
3. Ausführung von :java:`outExpr`
4. Wiederholung der Schritte (1) bis (3) für die nächste Anweisung.



Compiler
------------------------------------------------

.. deck::

    .. card::
                

        .. definition::

            Ein Übersetzer (Compiler) ist ein Programm, das Programme aus einer Programmiersprache A in eine Programmiersprache B übersetzt


        .. raw:: html

            <svg width="70ch" height="5lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <style>
                    rect {
                        fill: white;
                        stroke: blue;
                        stroke-width: 0.2ch;
                        filter: drop-shadow( 2px 2px 4px rgba(0, 0, 0, .7));
                    } 
                    line { 
                        stroke:darkorange;
                        stroke-width:0.2ch;
                    }
                    text {
                        fill: black;
                        font-family: var(--theme-code-font-family);
                        font-size: 0.75lh;
                    }
                    .subtext {
                        fill: var(--dark-gray);
                    }
                </style>
                <defs>
                    <marker 
                    id="arrow"
                    viewBox="0 0 2 2"
                    refX="2"
                    refY="1"
                    markerUnits="strokeWidth"
                    markerWidth="7"
                    markerHeight="7"
                    orient="auto-start-reverse">
                    <path class="arrow-head" d="M 0 0 L 2 1 L 0 2 z" />
                    </marker>
                </defs>

                    <!-- Quellprogramm P1 -->
                    <rect x="0.1ch" y="0.1ch" rx="1ch" ry="1ch" width="16ch" height="2.5lh" />
                    <text x="1ch" y="1lh">Programm in</text>
                    <text x="1ch" y="2lh">der Sprache A</text>
                    <text class="subtext" x="1ch" y="4lh">Quellprogramm P1</text>
                    
                    <g class="incremental">
                    <!-- Übersetzer -->
                    <line x1="16ch" y1="1.25lh" x2="20ch" y2="1.25lh" marker-end="url(#arrow)" />
                    <rect x="20ch" y="0.5lh" rx="1ch" ry="1ch"  width="12ch" height="1.5lh" />
                    <text x="21.35ch" y="1.6lh">Übersetzer</text>
                    </g>

                    <g class="incremental">
                    <!-- Zielprogramm P2 -->
                    <line x1="32ch" y1="1.25lh" x2="36ch" y2="1.25lh" marker-end="url(#arrow)" />
                    <rect x="36ch" y="0.1ch" width="16ch" rx="1ch" ry="1ch" height="2.5lh" />
                    <text x="37ch" y="1lh">Programm in</text>
                    <text x="37ch" y="2lh">der Sprache B</text>
                    <text class="subtext" x="37ch" y="4lh">Zielprogramm P2</text>
                    </g>

                    <g class="incremental">
                        <!-- Ausführung -->
                        <line x1="52ch" y1="1.25lh" x2="56ch" y2="1.25lh" marker-end="url(#arrow)" />
                        <text x="57ch" y="1.6lh" style="font-style:italic">Ausführung</text> 
                    </g>
            </svg>

        .. legend::

            A ist die Ausgangssprache und B die Zielsprache.

        .. container:: incremental

            **Wesentlicher Aspekt ist die semantische Korrektheit:** Jedem Quellprogramm P1 in A wird genau ein Zielprogramm P2 in B zugeordnet. Das dem Quellprogramm P1 zugeordnete Zielprogramm P2 muss die gleiche Bedeutung (Semantik) wie P1 besitzen.


    .. card::

        Mutmaßlich erfunden von Konteradmiral Grace Murray Hopper (1906–1992) 

        Nach eigener Aussage hat sie den Compiler aus Faulheit erfunden, und weil sie hoffte, dass "Programmierer wieder Mathematiker werden" könnten.


Compiler - Übersetzungsphasen
------------------------------------------------


.. class:: incremental-list

:Lexikalische Analyse:

     Quellprogramm wird in eine Folge von Worten zerlegt

:Syntaktische Analyse:

    Testet, ob das Quellprogramm den Syntaxregeln der Quellsprache entspricht. Strukturiert Worte in gültige Sätze.

:Semantische Analyse:

    Testet, ob alle im Quellprogramm benutzten Namen deklariert wurden und ihrem Typ entsprechend verwendet werden, usw. 

:Code-Generierung:

    Zielprogramm wird erzeugt.




Compiler - Übersetzung (Traditionell)
------------------------------------------------


.. raw:: html
    :class: center-content

    <svg width="55ch" height="9lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
        <style>
            line { 
                stroke:darkorange;
                stroke-width:0.2ch;
            }
            text {
                fill: black;
                font-family: var(--theme-code-font-family);
                font-size: 0.75lh;
            }
            .subtext {
                fill: var(--dark-gray);
            }
        </style>
        <defs>
            <marker 
            id="arrow"
            viewBox="0 0 2 2"
            refX="2"
            refY="1"
            markerUnits="strokeWidth"
            markerWidth="7"
            markerHeight="7"
            orient="auto-start-reverse">
            <path class="arrow-head" d="M 0 0 L 2 1 L 0 2 z" />
            </marker>
        </defs>

        <text x="1ch" y="1lh" style="font-style: italic">N Sprachen</text>
        <text x="40ch" y="1lh" style="font-style: italic">M Plattformen</text>

        <text x="1ch" y= 2.5lh>Pascal</text>
        <g class="incremental">
        <line x1="7ch" y1="2.15lh" x2="39ch" y2="2.25lh" marker-end="url(#arrow)" />
        <line x1="7ch" y1="2.15lh" x2="39ch" y2="3.75lh" marker-end="url(#arrow)" />
        <line x1="7ch" y1="2.15lh" x2="39ch" y2="5.25lh" marker-end="url(#arrow)" />
        <line x1="7ch" y1="2.15lh" x2="39ch" y2="6.75lh" marker-end="url(#arrow)" />
        <line x1="7ch" y1="2.15lh" x2="39ch" y2="8.25lh" marker-end="url(#arrow)" />
        </g>

        <text x="1ch" y= 4lh>C</text>
        <g class="incremental">
        <line x1="2.5ch" y1="3.65lh" x2="39ch" y2="2.25lh" marker-end="url(#arrow)" />
        <line x1="2.5ch" y1="3.65lh" x2="39ch" y2="3.75lh" marker-end="url(#arrow)" />
        <line x1="2.5ch" y1="3.65lh" x2="39ch" y2="5.25lh" marker-end="url(#arrow)" />
        <line x1="2.5ch" y1="3.65lh" x2="39ch" y2="6.75lh" marker-end="url(#arrow)" />
        <line x1="2.5ch" y1="3.65lh" x2="39ch" y2="8.25lh" marker-end="url(#arrow)" />
        </g>

        <text x="1ch" y= 5.5lh>C++</text>
        <g class="incremental">
        <line x1="5ch" y1="5.15lh" x2="39ch" y2="2.25lh" marker-end="url(#arrow)" />
        <line x1="5ch" y1="5.15lh" x2="39ch" y2="3.75lh" marker-end="url(#arrow)" />
        <line x1="5ch" y1="5.15lh" x2="39ch" y2="5.25lh" marker-end="url(#arrow)" />
        <line x1="5ch" y1="5.15lh" x2="39ch" y2="6.75lh" marker-end="url(#arrow)" />
        <line x1="5ch" y1="5.15lh" x2="39ch" y2="8.25lh" marker-end="url(#arrow)" />
        </g>

        <text x="1ch" y= 7lh>Smalltalk</text>
        <g class="incremental">
        <line x1="10ch" y1="6.65lh" x2="39ch" y2="2.25lh" marker-end="url(#arrow)" />
        <line x1="10ch" y1="6.65lh" x2="39ch" y2="3.75lh" marker-end="url(#arrow)" />
        <line x1="10ch" y1="6.65lh" x2="39ch" y2="5.25lh" marker-end="url(#arrow)" />
        <line x1="10ch" y1="6.65lh" x2="39ch" y2="6.75lh" marker-end="url(#arrow)" />
        <line x1="10ch" y1="6.65lh" x2="39ch" y2="8.25lh" marker-end="url(#arrow)" />
        </g>

        <text x="1ch" y= 8.5lh>...</text>
        <g class="incremental">
        <line x1="4ch" y1="8.25lh" x2="39ch" y2="2.25lh" marker-end="url(#arrow)" />
        <line x1="4ch" y1="8.25lh" x2="39ch" y2="3.75lh" marker-end="url(#arrow)" />
        <line x1="4ch" y1="8.25lh" x2="39ch" y2="5.25lh" marker-end="url(#arrow)" />
        <line x1="4ch" y1="8.25lh" x2="39ch" y2="6.75lh" marker-end="url(#arrow)" />
        <line x1="4ch" y1="8.25lh" x2="39ch" y2="8.25lh" marker-end="url(#arrow)" />
        </g>

        <text x="40ch" y= 2.5lh>Linux (ARM)</text>
        <text x="40ch" y= 4lh>Apple (ARM)</text>
        <text x="40ch" y= 5.5lh>Apple (Intel)</text>
        <text x="40ch" y= 7lh>Windows (Intel)</text>
        <text x="40ch" y= 8.5lh>...</text>

    </svg>

.. container:: incremental

   D. h. der Quelltext in Sprache A wird meinem spezialisierten Compiler für Sprache A und Zielplattform X in ein ausführbares Programm für X übersetzt.

.. class:: incremental-list positive-list

    - Performance/Effizienz: Optimale Ausnutzung der jeweiligen Prozessoreigenschaften und hohe Abarbeitungsgeschwindigkeit der übersetzten Programme.

.. class:: incremental-list negative-list

    - Plattformabhängigkeit: Ein Programm, das in einer höheren Programmiersprache geschrieben ist, kann - bei Verfügbarkeit eines Compilers - auf jeder Maschine laufen.



Interpreter - Vor- und Nachteile
------------------------------------------------

.. class:: incremental-list positive-list

- es lassen sich relativ schnell lauffähige Programmversionen erstellen (Prototyping)
- Schnelle Änderbarkeit: geänderte Anweisungen / Deklarationen des Quellprogramms sind sofort ausführbar
- Neuübersetzung nicht notwendig

.. class:: incremental-list negative-list

- Längere Ausführungszeit
- Werden Anweisungen des Quellprogramms k-mal verwendet (z.B. bei Schleifen), werden sie k-mal analysiert und überführt
- Bei Zugriffen auf Variablen müssen die zugeordneten Adressen immer wieder bestimmt werden.

.. remark::
    :class: incremental

    Auch heute gibt es noch *reine* Interpreter, aber Programmiersprachen wie Python und Java etc. setzen schon lange auf hybride Ansätze, die versuchen das Beste aus allen Welten vereinen.



Virtuelle Maschinen
------------------------------------------------

.. deck::

    .. card::


        .. definition::

            Eine virtuelle Maschine ist ein Programm, das die Arbeit eines Prozessors in Software simuliert 


        .. class:: incremental-list

        - Programme einer höheren Sprache werden in eine Assembler-ähnliche Zwischensprache übersetzt.

        - Der simulierte Hardware-Prozessor nutzt diese Zwischensprache und besitzt einige Software-Register
        - Die Anweisungen der Zwischensprache nennt man auch Byte-Code.

        - Die Zwischensprache wird von der Virtuellen Maschine interpretiert.
        - Eine virtuelle Maschine versteckt die spezifischen Eigenschaften eines konkreten Prozessors. Wir haben somit eine neue Abstraktionsschicht auf der Hardware-Ebene!


    .. card::
                
        .. raw:: html
            :class: center-content

            <svg width="55ch" height="9lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <style>
                    line { 
                    stroke:darkorange;
                    stroke-width:0.2ch;
                    }
                    text {
                    fill: black;
                    font-family: var(--theme-code-font-family);
                    font-size: 0.75lh;
                    }
                    .subtext {
                    fill: var(--dark-gray);
                    }
                </style>
                <defs>
                    <marker 
                        id="arrow"
                        viewBox="0 0 2 2"
                        refX="2"
                        refY="1"
                        markerUnits="strokeWidth"
                        markerWidth="7"
                        markerHeight="7"
                        orient="auto-start-reverse">
                        <path class="arrow-head" d="M 0 0 L 2 1 L 0 2 z" />
                    </marker>
                </defs>
                
                <text x="1ch" y="1lh" style="font-style: italic">N Sprachen</text>
                <text x="40ch" y="1lh" style="font-style: italic">M Plattformen</text>
                
                <text x="20ch" y="4.5lh" style="font-style: italic">Virtuelle</text>
                <text x="20ch" y="5.5lh" style="font-style: italic">Machine</text>
                
                <text x="1ch" y= 2.5lh>Pascal</text>
                <g class="incremental">
                    <line x1="7ch" y1="2.15lh" x2="19ch" y2="4.15lh" marker-end="url(#arrow)" />
                    
                </g>
                
                <text x="1ch" y= 4lh>C</text>
                <g class="incremental">
                    <line x1="2.5ch" y1="3.65lh" x2="19ch" y2="4.35lh" marker-end="url(#arrow)" />
                </g>
                
                <text x="1ch" y= 5.5lh>C++</text>
                <g class="incremental">
                    <line x1="4.5ch" y1="5.15lh" x2="19ch" y2="4.55lh" marker-end="url(#arrow)" />
                </g>
                
                <text x="1ch" y= 7lh>Smalltalk</text>
                <g class="incremental">
                    <line x1="10.5ch" y1="6.65lh" x2="19ch" y2="4.75lh" marker-end="url(#arrow)" />
                </g>
                
                <text x="1ch" y= 8.5lh>...</text>
                <g class="incremental">
                    <line x1="5ch" y1="8.25lh" x2="19ch" y2="4.95lh" marker-end="url(#arrow)" />
                </g>
                
                <g class="incremental">
                    <line x1="29ch" y1="4.95lh" x2="39ch" y2="8.25lh" marker-end="url(#arrow)" />
                    <line x1="29ch" y1="4.15lh" x2="39ch" y2="2.15lh" marker-end="url(#arrow)" />
                    <line x1="29ch" y1="4.35lh" x2="39ch" y2="3.65lh" marker-end="url(#arrow)" />
                    <line x1="29ch" y1="4.55lh" x2="39ch" y2="5.15lh" marker-end="url(#arrow)" />
                    <line x1="29ch" y1="4.75lh" x2="39ch" y2="6.65lh" marker-end="url(#arrow)" />
                </g>
                
                <text x="40ch" y= 2.5lh>Linux (ARM)</text>
                <text x="40ch" y= 4lh>Apple (ARM)</text>
                <text x="40ch" y= 5.5lh>Apple (Intel)</text>
                <text x="40ch" y= 7lh>Windows (Intel)</text>
                <text x="40ch" y= 8.5lh>...</text>
                
            </svg>
        
    .. card::


        Eine VM verdeckt die speziellen Eigenschaften des jeweiligen Prozessortyps und dient somit als Abstraktionsschicht!

        .. class:: incremental-list positive-list

        - Übersetzte Programme einer Sprache laufen auf allen Prozessortypen, für die es einen Byte-Code Interpreter(VM) gibt. 
        - Es wird nur ein Compiler benötigt und die Sprache wird plattformunabhängig.

        .. supplemental::

            Natürlich braucht man eine VM pro Prozessortyp und Platform. Aber das ist ein geringerer Aufwand als für jede Sprache einen eigenen Compiler zu schreiben.

        .. class:: incremental-list negative-list

        - Byte-Code Programme sind langsamer als Maschinenprogramme

          .. container:: incremental peripheral

            Just-in-time-compiler (JIT) versuchen diesen Nachteil aufzulösen. Sie Übersetzen den Byte-Code in ein Objekt-Programm für einen speziellen Prozessortyp sobald es geladen wird, oder nach einer gewissen Anzahl an Ausführungen.




Struktur eines Java-Programms
------------------------------------------------

Ein Java-Programm kann aus beliebig vielen Klassen bestehen, von denen mindestens eine die :java:`main`-Operation besitzen muss (Hauptprogrammklasse).

.. compound::
    :class: incremental

    .. rubric:: Aufgaben von :java:`main`

    .. class:: incremental-list list-with-explanations

    - Objekterzeugung; d. h. der Aufbau einer anfangs minimalen Welt
    - Aufruf der ersten Operation

    - Sollte in der Regel keinen weitergehenden Kontrollfluss des Java-Programms enthalten

      Der Kontrollfluss wird innerhalb der Objektoperationen realisiert.

    - :java:`main` wird mit Hilfe des Java-Interpreters gestartet und ausgeführt



Java-Laufzeitumgebung
------------------------------------------------

.. story::

    .. class:: incremental-list dd-margin-left-4em

    :Java Interpreter: Programm zur Ausführung des Java-Bytecodes auf dem konkreten Rechner.
    :Just-In-Time-Compiler (JIT-Compiler):
        Klassen bzw. Methoden werden bei Bedarf in  Code der jeweiligen Maschine übersetzt. Ggf. auf verschiedenen Optimierungsstufen.

    :Runtime-System: Stellt einem Java-Programm wichtige Ressourcen zur Verfügung.
    :Bytecode Verifier: Überprüft, ob die geladenen Bytecodes der JVM-Spezifikation entsprechen.
        Klassen können über das Netz oder aus dem lokalen Dateisystem zur Laufzeit einer Java-Anwendung in das Laufzeitsystem nachgeladen werden
        
        Ein Teil der Sicherheitsmaßnahmen wird durch den Bytecode Verifier realisiert. 

    .. attention:: 
        :class: incremental

        Die Sicherheit, die der Bytecode-Verifier und Java als solches bietet - ist nicht ausreichend, um die Sicherheit des Systems zu gewährleisten. Führen Sie Java Programme, denen Sie nicht vertrauen, niemals aus.



Java-Übersetzung
------------------------------------------------

- Die Eingabe für :console:`javac` sind ein oder mehrere Java-Dateien, die jeweils die eine oder mehrere Klassendefinitionen enthalten. 
- Eine derartige Datei nennt man eine Übersetzungseinheit
- Die Ausgabe ist pro Klasse X genau eine Datei X.class, die den Bytecode der Klasse enthält.

.. example::

    .. deck::

        .. card::

            .. rubric:: HelloWorld.java (im Default Package)

            .. include:: code/HelloWorld.java
                :code: java
                :number-lines:

        .. card::

            .. rubric:: Kompilieren mit javac und Ausführen mit java

            .. code:: console
                :number-lines:

                $ javac HelloWorld.java                                  
                $ ls
                HelloWorld.class HelloWorld.java
                $ java HelloWorld                                        # ACHTUNG kein ".java"!
                Hello, World!

        .. card::

            .. rubric:: HelloWorld.java (im Package de.dhbw)

            :peripheral:`Die Datei HelloWorld.java muss im Verzeichnis de/dhbw liegen!`

            .. include:: code/de/dhbw/HelloWorld.java
                :code: java
                :number-lines:

        .. card::

            .. rubric:: Kompilieren mit javac und Ausführen mit java

            .. code:: console
                :number-lines:

                $ javac de/dhbw/HelloWorld.java                                  
                $ ls de/dhbw
                HelloWorld.class HelloWorld.java
                $ java de.dhbw.HelloWorld                # ACHTUNG vollständiger Name der Klasse!
                Hello, World!

.. class:: exercises

Übung
--------

.. exercise:: RPN Taschenrechner kompilieren/Java Code übersetzen

    Stellen Sie sicher, dass Ihr Programm für den RPN Taschenrechner durch die Klasse RPN implementiert wird und diese Klasse in der entsprechenden Java Datei gespeichert ist. Die Klasse RPN soll im Package :java:`rpn` sein! Die Klassen für den Stack und die Liste sollen im Package :java:`ds` liegen.

    Compilieren Sie nur Ihr Program und die benötigten Hilfsklassen in einem Schritt mit Hilfe von :console:`javac`. Starten Sie danach Ihr Programm mit Hilfe von :console:`java`. Vergessen Sie nicht den vollqualifizierten Namen der RPN Klasse zu verwenden. Stellen Sie auch sicher, dass Sie sich im passen Root-Verzeichnis befinden.

    .. supplemental::

        Falls Sie den Code nicht haben, dann können Sie den Code von hier verwenden. Achten Sie darauf die Dateien in den entsprechenden Verzeichnissen zu speichern!

        .. rubric:: Datei: rpn/RPN.java

        .. include:: code/rpn/RPN.java
            :code: java
            :number-lines:
            :class: copy-to-clipboard

        .. rubric:: Datei: ds/Stack.java

        .. include:: code/ds/Stack.java
            :code: java
            :number-lines:
            :class: copy-to-clipboard

        .. rubric:: Datei: ds/List.java

        .. include:: code/ds/List.java
            :code: java
            :number-lines:
            :class: copy-to-clipboard            

    .. solution::
        :pwd: JetztMalRichtig:javac

        .. code:: console
            :number-lines:

            $ javac rpn/RPN.java ds/Stack.java ds/List.java
            $ ls rpn ds
            rpn:
            RPN.class RPN.java

            ds:
            List.class List.java Stack.class Stack.java
            $java rpn.RPN 1 2 +
            (2 + 1) = 3.0
            (2 + 1) 
    
