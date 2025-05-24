.. meta::
   :version: renaissance
   :lang: de
   :author: Michael Eichberg
   :keywords: "Sicherheitsprinzipien, Kontrollfragen"
   :description lang=de: Kontrollfragen bzgl. klassischer Sicherheitsprinzipien
   :id: lecture-klassische-Sicherheitsprinzipien-Kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Klassische Sicherheitsprinzipien - Kontrollfragen
================================================================================

-----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0


.. class:: new-section

Prinzipien
------------------



.. class:: exercises

Übung
----------------

Die folgenden Fragen sind exemplarisch für Fragen wie sie in einer Prüfung vorkommen könnten.

.. exercise:: Principle of Complete Mediation
        :class: incremental

        1) Was besagt das Prinzip?
        2) Wie kann/sollte das Prinzip im Rahmen der Entwicklung einer Webanwendung umgesetzt werden?

        .. solution::
            :pwd: kurz-und-KnapP

            Zugriffsanfragen eines Subjekts auf ein Objekt werden jedes Mal vollständig auf ihre Zulässigkeit hin überprüft.

            Umsetzung in einer Webanwendung: Jede Anfrage an den Server sollte durch eine zentrale Komponente (z.B. einen Filter oder Middleware) geleitet werden, die die Berechtigungen des Nutzers überprüft.

.. exercise:: Kerckhoffs Prinzip
        :class: incremental

        Was besagt das Prinzip?

        .. solution::
            :pwd: Fragen-Antworten

            Die Sicherheit des Systems sollte nicht von der Geheimhaltung der Sicherheitsmechanismen abhängen (sondern nur vom Schlüssel).



.. class:: new-section

Umsetzung von Prinzipien
---------------------------------



.. class:: exercises

Übung
----------------

.. exercise:: FUSE - File System in User Space
        :class: incremental

        Welche Sicherheitsprinzipien werden mittels FUSE umgesetzt?

        .. solution::
            :pwd: Mehrere-Prinzipien-ganz-einfach

            - Principle of Least Privilege (der User Space Prozess hat nur die Rechte, die er wirklich benötigt)
            - Principle of Isolation (der User Space Prozess ist von anderen Prozessen isoliert)
            - Principle of Modularity (zum Beispiel bei der Implementierung von verschlüsselten Dateisystemen)
