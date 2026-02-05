.. meta::
    :lang: de
    :author: Michael Eichberg
    :keywords: "Komplexität", "Algorithmen", "Backtracking"
    :description lang=de: Theoretische Informatik - Backtracking
    :id: lecture-theo-algo-backtracking
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs

.. |group_assignment_template.py| source:: code/group_assignment_template.py
            :path: relative
            :prefix: https://delors.github.io/



Entwurf von Algorithmen: Backtracking bzw. das Backtrack-Prinzip
==================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 2.0.3

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



Backtracking
--------------------------------------------------------

.. supplemental::

    Neben der dynamischen Programmierung ist das Backtrack-Prinzip ein weiteres grundlegendes Verfahren zur Lösung von Problemen.

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

    .. solution::
        :pwd: ALLE Kombinationen bewerten

        *Ein Lösungsvorschlag*

        .. include:: code/group_assignment.py
            :code: python
            :number-lines:
            :class: copy-to-clipboard
