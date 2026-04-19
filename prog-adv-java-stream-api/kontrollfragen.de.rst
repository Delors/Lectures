.. meta::
   :lang: de
   :author: Michael Eichberg
   :keywords: "Kontrollfragen", "Java", "Streams"
   :description lang=de: Kontrollfragen bzgl. Java Streams
   :id: lecture-prog-java-streams-kontrollfragen
   :first-slide: last-viewed
   :master-password: SehrSchwierig?

.. include:: ../docutils.defs



Java Streams
===========================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.2 [Themed]


.. class:: exercises

Streams
-------------------------------------

.. exercise:: Was ist ein Stream?

   .. solution::
      :pwd: Stream

      Ein Stream ist eine Abstraktion, die eine Sequenz von Elementen beschreibt, die verarbeitet werden können. Streams unterstützen eine Vielzahl von Operationen wie Filterung, Mapping und Reduzierung.

.. exercise:: Welche Typen von Streams gibt es?
   :class: incremental

   .. solution::
      :pwd: StreamTypen

      - :java:`IntStream`
      - :java:`LongStream`
      - :java:`DoubleStream`
      - :java:`Stream<T>`

.. exercise:: Wie sieht die grundlegende Verwendung von Streams aus?
   :class: incremental

   .. solution::
      :pwd: StreamVerwendung

      1. Erstellen eines Streams
      2. Anwenden von Zwischenoperationen (Verarbeitungs-/Transformationsschritte) (z.B. :java:`filter`, :java:`map`)
      3. Anwenden einer Terminaloperation (z.B. :java:`collect`, :java:`forEach`)
