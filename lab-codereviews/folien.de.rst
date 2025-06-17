.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: Code Review, HTML, CSS
    :description lang=de: Einführung in Code Reviews mit dem besonderen Schwerpunkt Webtechnologien (HTML, CSS und JavaScript)
    :id: lab-code-reviews
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Code Reviews
=====================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 0.1

.. supplemental::

  :Folien:
      [HTML] |html-source|

      [PDF] |pdf-source|

  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues



.. class:: new-section

Einführung
-----------------



Was sind Code Reviews
----------------------------

.. deck::

    .. card::

        .. definition::

            Bei einem Code Review geben Entwickler Feedback zu (fremden) Code; typischerweise vor einem Merge.

            Code Reviews helfen dabei Fehler frühzeitig zu finden, die Qualtiät des Codes zu verbessern und sicherzustellen, dass alle Teammitglieder mit dem Code vertraut sind und verstehen wie der Code funktioniert.

    .. card::

        .. rubric:: Zweck von Code Reviews

        - Qualitätssicherung
        - Frühzeitiger Fehlererkennung
        - Wissensverbreitung (im Team)


Best Practices
-----------------------------

.. compound::

    .. rubric:: für Reviewer

    * konstruktiv und respektvoll bleiben
    * (kleine) fokussierte Reviews bevorzugen
    * Review mit Kontext (Anforderungen, Ziel) durchführen
    * eigene Annahmen hinterfragen
    * auf Verständlichkeit und Stil achten (Semantik von Bezeichnern etc.)

.. compound::
    :class: incremental

    .. rubric:: für Autoren

    * Reviews nicht persönlich nehmen
    * Nachfragen ggf. als Hinweis verstehen für Dokumentationsmängel



Voraussetzung für Code Reviews
--------------------------------

.. class:: incremental-list

- der Code ist konsistent formatiert (Code Prettifier)
- triviale Fehler wurden bereits behoben (Linting)
- der Code kompiliert
- (Unit-/Integrations-) Tests laufen durch
- Architektur und zentrale Designentscheidungen sind dokumentiert

.. attention::
    :class: incremental

    Es macht nur Sinn Code Reviews für Code durchzuführen, der grundlegenden Qualitätsstandards genügt.



Code Review von CSS
---------------------

.. story::

    .. class:: incremental-list

    - Konsistente Schreibweise von ....

      .. class:: incremental-list

      - CSS Variablen
      - Klassennamen
      - IDs

    - Sind die Namen (CSS Variablen, Klassennamen und IDs) verständlich und sprechend?

      :red:`(Korrektes Deutsch oder Englisch!)`
    - *Einfachheit und Korrektheit von* Selektoren

      (Z. B. keine Verwendung von :css:`!important`.)
    - Sind die Selektoren effizient?
    - Konsistente Vorgehensweise in Hinblick auf (z. B.) Responsiveness (z. B. konsistente Verwendung von Größen (rem, rem,...) oder das Farbschema.
    - keine Redundanzen, keine unnötigen Style Definitionen.
    - Modularisiert
    - Einfachheit des Layouts?
    - Gibt es ein klares Vorgehen (Mobile-first or Desktop-first)?
    - Werden Tools eingesetzt, um bei der Formatierung dem Linting zu helfen.
    - Sind komplizierte Selektoren - falls notwendig - dokumentiert/begründet/nachhvollziehbar?



Code Reviews von HTML
-------------------------

.. story::

    .. class:: incremental-list

    - Ist der HTML Code korrekt?

      .. attention::

        Folgende Aspekte (Auszug) sollten automatisiert geprüpft werden:

        Sind IDs einmalig, sind alle Element korrekt geschlossen, ist die Baumstruktur korrekt.

    - Benennung von Klassen und IDs:

      Sind die Namen konsistent vergeben und verständlich und korrekt geschrieben.

    - Ist die Struktur einfach nachvollziehbar oder übermäßig komplex?

      (Z. B. gibt es eine hohe Anzahl and verschachtelten DIVs oder gibt es eine sinnvolle Strukturierung der Überschriften?).

    - Accessibility?

      Nutzt der Code nur :html:`<span>` und :html:`<div>`, oder semantisches HTML?

      Werden ARIA Tags sinnvoll verwendet?

      Haben Bilder ein :html:`alt` Attribut?

    - Keine Verwendung von inlines styles?

    - Keine Verwendung von inline JavaScript?

    - Keine Verwendung von :html:`<br>` für Formatierungszwecke.

    - Konsistente Verwendung von tags?

      (Z. B. ist Code immer in :html:`<pre><code>...</code></pre>`?)

    - Werden Eingaben in Eingabefelder geprüft?

    - Werden :html:`meta` Tags für die Spezifikation des Viewports sowie weiterer Metainformationen verwendet?

    - Werden übermäßig große Bilder/Icons vermieden?

    - Sind Links inhaltlich nachvollziehbar?


Code Reviews von JavaScript
----------------------------

.. story::

    .. class:: incremental-list

    - Macht der Code was er vorgibt zu tun?

      (D. h. sind Variablen, Klassen, Methoden, ... -namen korrekt und sinnvoll?)
    - Werden Sonderfälle und Fehlerzustände behandelt?
    - Ist die Fehlerbehandlung konsisten und für den Endnutzer nachvollziehbar?
    - Werden keine Fehler „verschluckt“?
    - Wird null/undefined korrekt behandelt?
    - Wird modernes JavaScript verwendet (z. B. Klassen, :javascript:`const` und :javascript:`let` anstatt von `var`, Destrukturierung, Spread und Rest Operator etc.)?
    - Wird :javascript:`eval()` nicht verwendet?
    - Werden asynchrone Funktionen richtige verwendet?
    - Ist der Kontrollfluss verständlich?

      (Nachfragen deuten auf Probleme bzgl. der Verständlichkeit hin.)
    - Ist der Code modularisiert?
    - Wird auf tief verschachteltete Logik verzichtet?
    - Werden teure Manipulationen (des DOMs) auf das notwendige Minimum beschränkt?
    - Werden Eingaben validiert (auf Client und **Server** Seite)?
    - Ist das Logging von Fehlern (Error) sinnvoll und enthält genug Kontextinformationen?
    - Gibt es Testfälle? Falls ja, sind diese ausreichend?

      (Codeabdeckung ist hier *nur* ein erster Indikator.)
    - Wird nur minimaler globaler Zustand verwendet?
    - Ist die (Inline-)Dokumentation ausreichend und korrekt.
    - Sind die TODOs, FIXMEs verständlich und umsetzbar?
