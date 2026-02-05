.. meta:: 
   :lang: de
   :author: Michael Eichberg
   :keywords: "Komplexität", "Algorithmen", "Kontrollfragen", "Backtracking"
   :description lang=de: Theoretische Informatik - Backtracking - Kontrollfragen
   :id: lecture-theo-algo-backtracking-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!
   
.. include:: ../docutils.defs



Entwurf von Algorithmen: Backtracking - Kontrollfragen
=====================================================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0.0



.. class:: exercises

Backtracking - Grundlagen
--------------------------

.. exercise:: Backtracking vs. vollständige Enumeration

   Erklären Sie - z. B. anhand des Beispiels der logischen Ausdrücke (SAT-Problem) - wie Backtracking die Anzahl der zu evaluierenden vollständigen Belegungen reduziert. 

  .. solution::
   :pwd: DerAbbruch
   
   Durch Abbruch von Teillösungen, die zu keiner gültigen Gesamtlösung führen (können).

.. exercise:: Komplexität von Backtracking-Algorithmen

   Warum verändern Backtracking-Algorithmen die theoretische Komplexitätsklasse des Problems nicht?

  .. solution::
   :pwd: KomplexitätsklasseBleibtGleich
   
   Weil im *schlimmsten Fall* immer noch alle möglichen Lösungen ausprobiert werden müssen. 
