.. meta::
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
:Version: 0.3

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

            Code Reviews helfen dabei Fehler frühzeitig zu finden, die Qualität des Codes zu verbessern und sicherzustellen, dass alle Teammitglieder mit dem Code vertraut sind und verstehen wie der Code funktioniert.

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

    .. rubric:: für die Autoren des Codes

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

.. rubric:: Exemplarische Kriterien

.. story::

    .. class:: incremental-list list-with-explanations

    - keine Redundanzen, keine unnötigen Style Definitionen.
    - Konsistente Schreibweise von:

      .. class:: incremental-list

      - CSS Variablen
      - Klassennamen
      - IDs

    - Sind die Namen (CSS Variablen, Klassennamen und IDs) verständlich und sprechend?

      :red:`(Korrektes Deutsch oder Englisch!)`
    - Werden CSS Variablen sinnvoll verwendet? (Z.B. um ein leicht anpassbares Design zu ermöglichen?)
    - Werden CSS Layer verwendet, um die Styles zu organisieren und die Wartbarkeit zu erhöhen (Stichwort: Modularisierung)?
    - Wird CSS Nesting verwendet, um die Struktur der Styles zu verdeutlichen?
    - *Einfachheit und Korrektheit von* Selektoren

      (Z. B. keine Verwendung von :css:`!important`.)
    - Sind die Selektoren effizient?
    - Sind komplizierte Selektoren - falls notwendig - dokumentiert/begründet/nachhvollziehbar?
    - Konsistente Vorgehensweise in Hinblick auf (z. B.) Responsiveness (z. B. konsistente Verwendung von Größen (em, rem,...) oder das Farbschema.
    - Einfachheit des Layouts?
    - Gibt es ein klares Vorgehen (Mobile-first or Desktop-first)?
    - Werden Tools eingesetzt, um bei der Formatierung bzw. dem Linting zu helfen.
    - Wird modernes CSS verwendet?

      (Dies gilt immer in Hinblick auf die Zielplattformen/Browserversionen.)




Code Reviews von HTML
-------------------------

.. rubric:: Exemplarische Kriterien

.. story::

    .. class:: incremental-list

    - Ist der HTML Code korrekt?

      .. attention::

        Folgende Aspekte (Auszug) sollten automatisiert geprüft werden:

        Sind IDs einmalig, sind alle Element korrekt geschlossen, ist die Baumstruktur korrekt.

    - Benennung von Klassen und IDs:

      Sind die Namen konsistent vergeben und verständlich und korrekt geschrieben.

    - Ist die Struktur einfach nachvollziehbar oder übermäßig komplex?

      (Z. B. gibt es eine hohe Anzahl an verschachtelten DIVs oder gibt es eine sinnvolle Strukturierung der Überschriften?).

    - Accessibility?

      wird primär semantisches HTML genutzt? (D. h. beschränkt sich die Nutzung von :html:`<span>` und :html:`<div>` auf die optische Gestaltung?)

      Werden ARIA Tags sinnvoll verwendet?

      Haben Bilder ein :html:`alt` Attribut?

    - Keine Verwendung von inline Styles?

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

.. rubric:: Exemplarische Kriterien

.. story::

    .. class:: incremental-list

    - Macht der Code was er vorgibt zu tun?

      (D. h. sind Variablen, Klassen, Methoden, ... -namen korrekt und sinnvoll?)
    - Werden Sonderfälle und Fehlerzustände behandelt?
    - Ist die Fehlerbehandlung konsistent und für den Endnutzer nachvollziehbar?
    - Werden keine Fehler „verschluckt“?
    - Wird null/undefined korrekt behandelt?
    - Wird modernes JavaScript verwendet (z. B. Klassen, :javascript:`const` und :javascript:`let` anstatt von `var`, Destrukturierung, Spread und Rest Operator etc.)?
    - Wird :javascript:`eval()` nicht verwendet?
    - Werden asynchrone Funktionen richtig verwendet?
    - Werden Promises korrekt behandelt (keine fehlenden .catch() oder try-catch bei async/await)?
    - Gibt es potenzielle Nebenläufigkeitsprobleme bei asynchronen Operationen?   
    - Werden Event Listener korrekt registriert und wieder entfernt (Memory Leaks)?
    - Ist der Kontrollfluss verständlich?

      (Nachfragen deuten auf Probleme bzgl. der Verständlichkeit hin.)
    - Ist der Datenfluss nachvollziehbar?

      (Insbesondere bei der Verwendung von globalem Zustand oder über Modulgrenzen hinweg.)
    - Wird die Architektur eingehalten?
    - Ist der Code modularisiert?
    - Wird auf tief verschachtelte Logik verzichtet?
    - Werden Funktionen und Methoden klein und fokussiert gehalten (Single Responsibility)?
    - Werden teure Manipulationen (des DOMs) auf das notwendige Minimum beschränkt?
    - Werden Eingaben validiert (auf Client und **Server** Seite, falls es sich um Serverseitigen JavaScript Code handelt)?
    - Werden sensible Daten (Passwörter, API-Keys) nicht im Client-Code hartcodiert?
    - Werden keine sensiblen Daten geloggt?
    - Ist das Logging von Fehlern (Error) sinnvoll und enthält genug Kontextinformationen?
    - Gibt es Testfälle? Falls ja, sind diese ausreichend?

      (Codeabdeckung ist hier *nur* ein erster Indikator.)
    - Wird nur minimaler globaler Zustand verwendet?
    - Ist die Verwendung von Bibliotheken/Dependencies gerechtfertigt und aktuell?
    - Gibt es überflüssige oder ungenutzte Abhängigkeiten?
    - Ist die (Inline-)Dokumentation ausreichend und korrekt?
    - Sind die TODOs und FIXMEs verständlich und umsetzbar?
    - Werden Browser-Kompatibilitätsanforderungen erfüllt?
    - Bei verteilten Anwendungen: ist die Aufteilung der Logik nachvollziehbar und sinnvoll (zum Beispiel auch aus Sicht von Cheatingmöglichkeiten)
