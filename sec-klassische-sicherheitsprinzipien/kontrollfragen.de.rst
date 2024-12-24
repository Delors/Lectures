.. meta::
   :lang: de
   :author: Michael Eichberg
   :keywords: "Sicherheitsprinzipien, Kontrollfragen"
   :description lang=de: Kontrollfragen bzgl. klassischer Sicherheitsprinzipien
   :id: lecture-klassische-Sicherheitsprinzipien-Kontrollfragen
   :first-slide: last-viewed
   :exercises-master-password: WirklichSchwierig!
    
.. |html-source| source::
   :prefix: https://delors.github.io/
   :suffix: .html
.. |pdf-source| source::
   :prefix: https://delors.github.io/
   :suffix: .html.pdf
.. |at| unicode:: 0x40
.. |qm| unicode:: 0x22 

.. role:: incremental
.. role:: appear
.. role:: eng
.. role:: ger
.. role:: dhbw-red
.. role:: green
.. role:: the-blue
.. role:: minor
.. role:: obsolete
.. role:: line-above
.. role:: smaller
.. role:: far-smaller
.. role:: monospaced
.. role:: copy-to-clipboard
.. role:: kbd
.. role:: java(code)
   :language: java



.. class:: animated-symbol 

Kontrollfragen: Klassische Sicherheitsprinzipien
================================================================================

-----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0



Kontrollfragen
----------------

.. container:: scrollable

   Die folgenden Fragen sind exemplarisch für Fragen wie sie in einer Prüfung vorkommen könnten. Der Inhalt der Prüfung kann sich selbstverständlich auf alle Inhalte der Vorlesung beziehen.

   .. class:: incremental

   1. \
   
      .. exercise:: Principle of Complete Mediation
         
         1) Was besagt das Prinzip?
         2) Wie kann/sollte das Prinzip im Rahmen der Entwicklung einer Webanwendung umgesetzt werden?

         .. solution::
            :pwd: kurz-und-KnapP

            Zugriffsanfragen eines Subjekts auf ein Objekt werden jedes Mal vollständig auf ihre Zulässigkeit hin überprüft.

            Umsetzung in einer Webanwendung: Jede Anfrage an den Server sollte durch eine zentrale Komponente (z.B. einen Filter oder Middleware) geleitet werden, die die Berechtigungen des Nutzers überprüft.

   2. 

       .. exercise:: Kerckhoffs Prinzip
         
         Was besagt das Prinzip?

         .. solution::
            :pwd: Fragen-Antworten

            Die Sicherheit des Systems sollte nicht von der Geheimhaltung der Sicherheitsmechanismen abhängen (sondern nur vom Schlüssel).

   3. 

       .. exercise:: FUSE - File System in User Space
         
         Welche Sicherheitsprinzipien werden mittels FUSE umgesetzt?

         .. solution::
            :pwd: Mehrere-Prinzipien-ganz-einfach

            - Principle of Least Privilege (der User Space Prozess hat nur die Rechte, die er wirklich benötigt)
            - Principle of Isolation (der User Space Prozess ist von anderen Prozessen isoliert)
            - Principle of Modularity (zum Beispiel bei der Implementierung von verschlüsselten Dateisystemen)