.. meta::
   :lang: de
   :author: Michael Eichberg
   :keywords: "Algorithmen", "Datenstrukturen", "Kontrollfragen"
   :description lang=de: Kontrollfragen zu Algorithmen und Datenstrukturen - Einführung
   :id: lecture-theo-algo-ds-einfuehrung-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Algorithmen und Datenstrukturen - Einführung - Kontrollfragen
================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0


.. class:: new-section

Algorithmen
-------------------------------------


.. class:: exercises

Kontrollfragen
-------------------------------------

.. deck::

   .. card::

      .. exercise:: Was bedeutet es, dass ein Algorithmus deterministisch ist?

         .. solution::
            :pwd: +DeterMi

            Ein deterministischer Algorithmus liefert bei gleicher Eingabe immer das gleiche Ergebnis und durchläuft dabei stets den gleichen Berechnungspfad. Es gibt keine zufälligen oder nicht-vorhersagbaren Entscheidungen im Ablauf.

   .. card::

      .. exercise:: Was versteht man unter der Eigenschaft, dass ein Algorithmus eine endliche Ausführung hat?

         .. solution::
            :pwd: NichtUnendlich

            Ein Algorithmus mit endlicher Ausführung terminiert nach einer endlichen Anzahl von Schritten für jede gültige Eingabe. Er gerät also nicht in eine Endlosschleife und liefert nach endlicher Zeit ein Ergebnis.


.. class:: new-section

Datenstrukturen - Grundlagen
-------------------------------------


.. class:: exercises

Kontrollfragen
-------------------------------------

.. deck::

   .. card::

      .. exercise:: Was ist eine Datenstruktur?

         .. solution::
            :pwd: DaTeNsTrUkTuR

            Eine Datenstruktur beschreibt eine Möglichkeit, Daten zu **organisieren**, zu **verwalten** und zu **speichern**, um sie **effizient** zu nutzen.

   .. card::

      .. exercise:: Nennen Sie mindestens vier verschiedene Datenstrukturen.

         .. solution::
            :pwd: Beispiele

            Beispiele für Datenstrukturen sind:

            - Primitive Datentypen
            - Arrays
            - Listen (verkettete Listen, dynamische Arrays)
            - Bäume
            - Graphen
            - Warteschlangen (Queues)
            - Stacks


.. class:: new-section

Arrays
-------------------------------------


.. class:: exercises

Kontrollfragen
-------------------------------------

.. deck::

   .. card::

      .. exercise:: Wie sind bei einem Array die Elemente im Speicher angeordnet?

         .. solution::
            :pwd: Zu_SAMMEN_

            Ein Array ist eine Datenstruktur, die eine Sammlung von Elementen **gleicher Größe** in einem **kontinuierlichen Speicherbereich** speichert. Die Elemente liegen - im virtuellen Speicher - direkt hintereinander.

   .. card::

      .. exercise:: Wie schnell ist der Zugriff auf ein beliebiges Element eines Arrays (z. B. auf das n-te Element)? Begründen Sie Ihre Antwort.

         .. solution::
            :pwd: DasWissenWir

            Der Zugriff auf ein beliebiges Element ist in **konstanter Zeit** (:math:`O(1)`) möglich. Da alle Elemente gleich groß sind und zusammenhängend im Speicher liegen, kann die Adresse des n-ten Elements direkt berechnet werden: :math:`\text{Adresse} = \text{Basisadresse} + n \times \text{Elementgröße}`. Es ist kein Durchlaufen der vorherigen Elemente notwendig.

   .. card::

      .. exercise:: Wie groß ist der Speicherplatz, den ein Array mit N Elementen benötigt?

         .. solution::
            :pwd: NMalElementUndOverhead

            Der Speicherplatz eines Arrays mit :math:`N` Elementen beträgt :math:`N \times \text{Elementgröße}` (zzgl. eines kleinen konstanten Overheads für die Verwaltung, z. B. die Länge des Arrays). Der Speicherbedarf wächst also **linear** mit der Anzahl der Elemente.

   .. card::

      .. exercise:: Was ist ein dynamisches Array und wie unterscheidet es sich von einem statischen Array?

         .. solution::
            :pwd: IstHalt_dynamisch

            Ein dynamisches Array (Liste) ist ein Array, das seine Größe **anpassen** kann und somit **erweiterbar** ist. Im Gegensatz zu einem statischen Array, dessen Größe bei der Erstellung festgelegt wird und nicht mehr geändert werden kann, wird bei einem dynamischen Array typischerweise ein neues Array mit **doppelter Größe** erstellt, wenn der Speicherplatz erschöpft ist. Die Elemente des alten Arrays werden dann in das neue Array kopiert. Dynamische Arrays werden basierend auf statischen Arrays implementiert.

   .. card::

      .. exercise:: Wie schnell ist das Einfügen eines neuen Elements in ein dynamisches Array im Best-Case und im Worst-Case?

         .. solution::
            :pwd: MalSo+MalSo!

            - **Best-Case:** :math:`O(1)` — wenn noch Platz im internen Array vorhanden ist, wird das Element einfach am Ende angefügt.
            - **Worst-Case:** :math:`O(N)` — wenn das interne Array voll ist, muss ein neues, größeres Array erstellt und alle :math:`N` bestehenden Elemente in das neue Array kopiert werden.

   .. card::

      .. exercise:: Wie schnell ist das Löschen eines Elements aus einem dynamischen Array?

         .. solution::
            :pwd: FastImmerAufwendig

            Das Löschen eines Elements aus einem dynamischen Array hat im Worst-Case eine Laufzeit von :math:`O(N)`. Wird ein Element am Anfang oder in der Mitte gelöscht, müssen alle nachfolgenden Elemente um eine Position nach vorne verschoben werden, um den zusammenhängenden Speicherbereich aufrechtzuerhalten. Nur das Löschen des letzten Elements ist in :math:`O(1)` möglich.



.. class:: new-section

Verkettete Listen
-------------------------------------


.. class:: exercises

Kontrollfragen
-------------------------------------

.. deck::

   .. card::

      .. exercise:: Was ist eine verkettete Liste und woraus bestehen ihre Elemente? Welche Typen von verketteten Listen können wir unterscheiden?

         .. solution::
            :pwd: EinfachMalDoppelt

            Eine verkettete Liste ist eine Datenstruktur, die eine Sammlung von Elementen in **Knoten** speichert. Jeder Knoten enthält den **aktuellen Wert** (die Nutzdaten) und einen **Zeiger auf das nächste Element**. Im Falle einer doppelt verketteten Liste enthält jeder Knoten zusätzlich einen **Zeiger auf das vorherige Element**. Verkettete Listen sind dynamisch, können beliebig wachsen und sind nicht auf einen zusammenhängenden Speicherbereich angewiesen.

   .. card::

      .. exercise:: Was ist der Unterschied zwischen einer einfach und einer doppelt verketteten Liste?

         .. solution::
            :pwd: ZweiZeigerSindDoppeltSovielWieEiner

            - **Einfach verkettete Liste:** Jeder Knoten hat nur einen Zeiger auf das **nächste** Element. Man kann die Liste nur in eine Richtung (vorwärts) durchlaufen.
            - **Doppelt verkettete Liste:** Jeder Knoten hat einen Zeiger auf das **nächste** und das **vorherige** Element. Man kann die Liste in **beide Richtungen** durchlaufen, was z. B. das Löschen eines Knotens und das rückwärts Iterieren erleichtert.

   .. card::

      .. exercise:: Welche Laufzeit haben die folgenden Operationen auf einer doppelt verketteten Liste (Worst-Case): Einfügen am Anfang, Einfügen am Ende, Löschen eines bestimmten Wertes, Überprüfen ob ein Wert vorhanden ist?

         .. solution::
            :pwd: LaUfZeItLiStE

            - **Einfügen am Anfang (prepend):** :math:`O(1)` — es muss nur der Head-Zeiger und der Vorgänger-Zeiger des bisherigen ersten Knotens angepasst werden.
            - **Einfügen am Ende (append):** :math:`O(1)` — wenn ein Tail-Zeiger vorhanden ist, muss nur der Tail-Zeiger und der Nachfolger-Zeiger des bisherigen letzten Knotens angepasst werden.
            - **Löschen eines bestimmten Wertes (remove):** :math:`O(N)` — der Wert muss erst durch Durchlaufen der Liste gefunden werden.
            - **Überprüfen ob ein Wert vorhanden ist (contains):** :math:`O(N)` — im schlimmsten Fall muss die gesamte Liste durchlaufen werden.

   .. card::

      .. exercise:: Welche Vor- und Nachteile hat eine verkettete Liste gegenüber einem (dynamischen) Array?

         .. solution::
            :pwd: DAsIstAbzuwaaaeeegen

            **Vorteile der verketteten Liste:**

            - Einfügen und Löschen am Anfang in :math:`O(1)` (bei Arrays :math:`O(N)` wegen Verschieben)
            - Kein Reorganisationsaufwand bei vollem Speicher (kein Kopieren wie bei dynamischen Arrays)
            - Kein zusammenhängender Speicherbereich nötig

            **Nachteile der verketteten Liste:**

            - Kein direkter Zugriff auf ein Element über den Index (:math:`O(N)` statt :math:`O(1)`)
            - Höherer Speicherverbrauch pro Element durch die zusätzlichen Zeiger
            - Schlechtere Cache-Lokalität, da die Knoten verstreut im Speicher liegen können

   .. card::

      .. exercise:: Warum ist der Zugriff auf das n-te Element einer verketteten Liste langsamer als bei einem Array?

         .. solution::
            :pwd: TrippelDiTrap

            Bei einer verketteten Liste sind die Knoten über Zeiger verbunden und ein direkter Zugriff auf das n-te Element erfordert es, dass  man vom Anfang (Head) der Liste ausgehend :math:`n` Zeigern folgt. Bei einem Array kann man die Speicheradresse direkt berechnen.



.. class:: new-section

Warteschlangen
-------------------------------------


.. class:: exercises

Kontrollfragen
-------------------------------------

.. deck::

   .. card::

      .. exercise:: Was ist eine Warteschlange (Queue) und welches Prinzip realisiert sie?

         .. solution::
            :pwd: QuEuEfIfO

            Eine Warteschlange (Queue) ist eine Datenstruktur, die das **FiFo-Prinzip** (First-In, First-Out) realisiert.

   .. card::

      .. exercise:: Welche Datenstruktur eignet sich als Implementierungsbasis für eine Queue und warum?

         .. solution::
            :pwd: HauptsacheEinenZeigerAufHeadUndEinenAufLast

            Eine **verkettete Liste** eignet sich gut als Implementierungsbasis für eine Queue. Neue Elemente werden am Ende der Liste angefügt (enqueue) und Elemente werden vom Anfang der Liste entfernt (dequeue). Beide Operationen sind mit einer (doppelt) verketteten Liste mit Head- und Last-Zeiger in :math:`O(1)` möglich, da kein Verschieben von Elementen notwendig ist.
