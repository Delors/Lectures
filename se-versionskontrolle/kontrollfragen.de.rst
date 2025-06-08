.. meta::
   :version: renaissance
   :lang: de
   :author: Michael Eichberg
   :keywords: "Versionskontrolle", "Git"
   :description lang=de: Kontrollfragen bzgl. Versionskontrolle
   :id: lecture-se-versionskontrolle-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!
    
.. include:: ../docutils.defs



Versionskontrolle
===========================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1


.. class:: new-section transition-scale

Versionskontrolle und Git
-------------------------------------


.. class:: exercises

Kontrollfragen 
-------------------------------------

.. deck:: numbered

   .. card:: 
   
      .. exercise:: Welche Fragen kann ich mit Hilfe von Versionskontrollsystemen (einfach(er)) beantworten?

         .. solution::
            :pwd: GanzVieleSindWichtig 
         
            - Wer hat was wann geändert?
            - Was ist der aktuelle Stand?
            - Was ist der Unterschied zwischen zwei Versionen?
            - Wie kann ich eine alte Version wiederherstellen?
            - Wie kann ich Änderungen von mehreren Personen zusammenführen?
            - Wie kann ich Änderungen von mehreren Personen vergleichen?
            - Wie kann ich Änderungen von mehreren Personen zusammenführen?
            - Wie kann ich Änderungen von mehreren Personen vergleichen?

   .. card::

      .. exercise:: Wie unterscheiden sich zentralisierte von dezentralisierten Versionskontrollsystemen?

         .. solution::
            :pwd: ZentralisierteVonDezentralisierten
         
            - Zentralisierte VCS speichern alle Versionen in einer zentralen Datenbank.
            - Dezentrale VCS speichern alle Versionen lokal auf dem Rechner des Benutzers. Es gibt aber meistens auch eine Datenbank, die den "Ground Truth" darstellt.
            - Dezentrale VCS ermöglichen es, **Änderungen offline vorzunehmen und später mit der zentralen Datenbank zu synchronisieren**.

   .. card::

      .. exercise:: In Git: Was ist ein Branch? Was ist ein Merge?

         .. solution::
            :pwd: BranchUndMerge
         
            - Ein Branch ist eine parallele Entwicklungslinie in einem Versionskontrollsystem.
            - Ein Merge ist der Prozess, bei dem zwei Branches zusammengeführt werden.
            - Ein Merge kann Konflikte verursachen, wenn die gleichen Zeilen in beiden Branches geändert wurden.

   .. card::

      .. exercise:: Verwalten von Dateien in GIT
         
         Um eine Datei unter Verwaltung von Git zu stellen müssen welche Befehle ausgeführt werden? Was müssen Sie ggf. noch tun, um die Änderungen zu veröffentlichen?

         .. solution::
            :pwd: AddUndCommit
         
            - git add <datei>
            - git commit -m "<Nachricht>"
            - (ggf.) git push

   .. card::

      .. exercise:: Welche Informationen enthält ein Commit?

         .. solution::
            :pwd: InfosInCommit

            - Commit-Hash
            - Autor
            - Datum
            - Commit-Nachricht
            - Änderung


   .. card::

      .. exercise:: Wozu brauchen Sie die .gitignore-Datei?

         .. solution::
            :pwd: gitignore
         
            Um Dateien und Verzeichnisse zu ignorieren, die nicht in das Repository aufgenommen werden sollen.

            Z. B. temporäre Dateien, Build-Artefakte, etc.