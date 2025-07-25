.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "Verteilte Anwendungen", "Modelle", "Architekturen", "Architekturelle Stile"
    :description lang=de: Architekturen von verteilten Anwendungen
    :id: lecture-ds-architekturen
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Architekturen verteilter Anwendungen
==========================================================================

Ein erster Überblick.

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0

.. supplemental::

  :Folien: 
          |html-source|

          |pdf-source|
          
  :Fehler melden:

          https://github.com/Delors/delors.github.io/issues

.. container:: footer-left 

    Ausgewählte Folien basieren auf Folien von Maarten van Steen ( *Distributed Systems*)

    Alle Fehler sind meine eigenen.



.. class:: transition-fade new-section

Grundlegende Architekturen
-------------------------------------



Architekturstile (:eng:`Architectural Styles`)
----------------------------------------------

Ein Architekturstil wird formuliert in Form von

.. class:: incremental-list

- (austauschbare) Komponenten mit klar definierten Schnittstellen
- der Art und Weise, wie die Komponenten miteinander verbunden sind
- die zwischen den Komponenten ausgetauschten Daten
- der Art und Weise, wie diese Komponenten und Verbindungen gemeinsam zu einem System konfiguriert werden System.
  

.. supplemental::

  .. rubric:: Konnektor

  Ein Mechanismus, der die Kommunikation, Koordination oder Kooperation zwischen Komponenten vermittelt. Beispiel: Einrichtungen für (entfernte) Prozeduraufrufe (RPC), Nachrichtenübermittlung oder Streaming.


Schichtenarchitekturen
----------------------

.. class:: columns incremental-list

- \
  
  .. image:: images/common_application_architectures/n-layered_architectures.svg
       
- \ 
  
  .. image:: images/common_application_architectures/n-layered_architectures-jump_over_layers.svg
       
- \
 
  .. image:: images/common_application_architectures/n-layered_architectures-and-callbacks.svg
       
       


Beispiel einer 3-Schichtenarchitektur
--------------------------------------

.. image:: images/common_application_architectures/3-layered-example.svg
   :align: center



Klassische Architekturen
-------------------------

.. image:: images/common_application_architectures/common_architectures.svg
   :align: center


.. supplemental::

  .. rubric:: Traditionelle Dreischichtenarchitektur

  Diese Architektur findet sich in vielen verteilten Informationssystemen mit traditioneller Datenbanktechnologie und zugehörigen Anwendungen.

  - Die Präsentationsschicht stellt die Schnittstelle zu Benutzern oder externen Anwendungen dar.
  - Die Verarbeitungsschicht implementiert die Geschäftslogik.
  - Die Persistenz-/Datenschicht ist für die Datenhaltung verantwortlich.


.. class:: smaller

*Publish and Subscribe* Architekturen
-------------------------------------

Abhängigkeiten zwischen den Komponenten werden durch das *Publish and Subscribe* Paradigma realisiert mit dem Ziel der loosen Kopplung.


.. deck:: incremental 
 
  .. card::

    **Taxonomie der Koordinierungsansätze in Hinblick auf Kommunikation und Koordination:**

    .. csv-table::
      :class: highlight-on-hover fake-header-column fake-header-row smaller
      :widths: 12 40 40
      
      "", "Zeitlich gekoppelt", "Zeitlich entkoppelt"
      :light-gray:`Referentiell gekoppelt`, :light-gray:`Direkt Koordination`, :light-gray:`Mailboxkoordination`
      "Referentiell entkoppelt", "ereignisbasierte Koordination 
      
      (:eng:`Event-based Coordination`)", "gemeinsam genutzter Datenspeicher 
      
      (:eng:`Shared Data Space`)"

  .. card:: 
        
    .. rubric:: Ereignisbasierte Koordination

    .. image:: images/pubsub/event-based.svg
       :align: center


  .. card::
        
    .. rubric:: *Shared Data Space*

    .. image:: images/pubsub/shared-data-space.svg
       :align: center

.. container:: incremental 

  Häufig wird die *ereignisbasierte Koordination* in Kombination mit *Shared Data Space* zur Realisierung von *Publish and Subscribe* Architekturen.


.. supplemental::

  .. rubric:: Direkte Koordination

  Ein Prozess interagiert unmittelbar (⇒ zeitliche Kopplung) mit genau einem anderen wohl-definierten Prozess (⇒ referentielle Kopplung).

  .. rubric:: Mailboxkoordination

  Die miteinander kommunizierenden Prozesse interagieren nicht direkt miteinander, sondern über eine eindeutige Mailbox (⇒ referentielle Kopplung). Dies ermöglicht es, dass die Prozesse nicht zeitgleich verfügbar sein müssen.

  .. rubric:: Ereignisbasierte Koordination

  Ein Prozess löst Ereignisse aus, auf die *irgendein* anderer Prozesse direkt reagiert. Ein Prozess, der zum Zeitpunkt des Auftretens des Ereignisses nicht verfügbar ist, sieht das Ereignis nicht.

  .. rubric:: Gemeinsam genutzter Datenspeicher

  Prozesse kommunizieren über Tuples, die in einem gemeinsam genutzten Datenspeicher hinterlegt werden. Ein Prozess, der zum Zeitpunkt des Schreibens nicht verfügbar ist, kann das Tuple später lesen. Prozesse definieren Muster in Hinblick auf die Tuples, die sie lesen wollen.

.. TODO Baue die Diskussion vo PubSub Architekturen weiter aus.


Aufbau von Cloud Computing Anwendungen
---------------------------------------------

.. image:: images/cloud.svg
   :align: center 


.. supplemental:: 

  Es können vier Schichten unterschieden werden:

  .. class:: list-with-explanations

  - Hardware: Prozessoren, Router, Stromversorgungs- und Kühlsysteme. 
   
    Für Kunden normalerweise vollkommen transparent.
  - Infrastruktur: Einsatz von Virtualisierungstechniken zum Zwecke der  Zuweisung und Verwaltung virtueller Speichere und virtueller Server.
  - Plattformen: Bietet Abstraktionen auf höherer Ebene für Speicher und dergleichen. 
   
    Beispiel: Das Amazon S3-Speichersystem bietet eine API für (lokal erstellte) Dateien, die in sogenannten Buckets organisiert und gespeichert werden können.
  - Anwendung: Tatsächliche Anwendungen, wie z. B. Office-Suiten (Textverarbeitungsprogramme, Tabellenkalkulationsprogramme, Präsentationsanwendungen). 
   
    Vergleichbar mit der Suite von Anwendungen, die mit Betriebssystemen ausgeliefert werden.




.. class:: new-section transition-fade

Microservices [Newman2021]_
---------------------------



.. class:: no-title 

Microservice mit REST Schnittstelle
-------------------------------------

.. class:: columns

- \  

  .. rubric:: Microservices

  Ein einfacher Microservice, der eine REST Schnittstelle anbietet und Ereignisse auslöst.

  .. container:: incremental question smaller

    Wo liegen hier die Herausforderungen?

- \

  .. image:: images/microservices/basisbeispiel.svg
  
.. supplemental::

  Eine große Herausforderung ist das Design der Schnittstellen. Um wirkliche Unabhängigkeit zu erreichen, müssen die Schnittstellen sehr gut definiert sein. Sind die Schnittstellen nicht klar definiert oder unzureichend, dann kann das zu viel Arbeit und Koordination zwischen den Teams führen, die eigentlich unerwünscht ist!



Schlüsselkonzepte von Microservices
-------------------------------------

.. class:: incremental-list list-with-explanations

- können unabhängig bereitgestellt werden (:eng:`independently deployable`) 
  
  und werden unabhängig entwickelt
- modellieren eine Geschäftsdomäne
  
  Häufig entlang einer Kontextgrenze (eng. Bounded Context) oder eines Aggregats aus DDD
- verwalten Ihren eigenen Zustand
  
  d. h. keine geteilten Datenbanken
- sind klein
  
  Klein genug, um durch (max.) ein Team entwickelt werden zu können

- flexibel bzgl. Skalierbarkeit, Robustheit, eingesetzter Technik
- erlauben das Ausrichten der Architektur an der Organisation (vgl. Conway's Law)



Microservices und Conway's Law
-------------------------------------

.. class:: columns incremental-list

- .. rubric:: Traditionelle Schichtenarchitektur 
       
  .. image:: images/microservices/aenderungen-bei-klassischer-architektur.svg
       
- .. rubric:: Microservices Architektur

  .. image:: images/microservices/aenderungen-bei-microservices-architektur.svg
       


Microservices und Technologieeinsatz
-------------------------------------

Microservices sind flexibel bzgl. des Technologieeinsatzes und ermöglichen den Einsatz „der geeignetsten“ Technologie.

.. image:: images/microservices/technologische-flexibilitaet.svg
   :align: center



.. class:: vertical-title

Aktuelle Standardtechnologien
-------------------------------------

.. deck::

  .. card::
  
    .. image:: screenshots/tiobe_2012-04.png
       :align: center

    Quelle: TIOBE Programming Community Index - April 2012

  .. card::

    .. image:: screenshots/tiobe_2024-02.png
       :align: center

    Quelle: `TIOBE Programming Community Index - Feb. 2024 <https://www.tiobe.com/tiobe-index/>`__


Microservices und Skalierbarkeit
-------------------------------------

Sauber entworfene Microservices können sehr gut skaliert werden.

.. image:: images/microservices/skalierbarkeit.svg
   :align: center



Implementierung einer langlebigen Transaktion?
-------------------------------------------------

.. image:: images/sagas/transaktion.svg

.. supplemental::

  Die Implementierung von Transaktionen ist eine der größten Herausforderungen bei der Entwicklung von Microservices. 




Aufteilung einer langlebigen Transaktion mit Sagas
---------------------------------------------------------------

.. image:: images/sagas/transaktion-mit-saga.svg

.. supplemental::

  Eine *Saga* ist eine Sequenz von Aktionen, die ausgeführt werden, um eine langlebige Transaktion zu implementieren. 
  
  Sagas können keine Atomizität garantieren. Jedes System für sich kann jedoch ggf. Atomizität garantieren (z. B. durch die Verwendung traditioneller Datenbanktransaktionen).

  Sollte ein Abbruch der Transaktion notwendig sein, dann kann kein traditioneller *Rollback* erfolgen. Die Saga muss dann entsprechende kompensierende Transaktionen durchführen, die alle bisher erfolgreich durchgeführten Aktionen rückgängig machen.



.. class:: smaller-slide-title vertical-title

Minimierung von mgl. *Rollbacks*
--------------------------------------------------------------------------------

.. image:: images/sagas/transaktion-mit-saga-mit-weniger-rollbacks.svg

.. supplemental::

  Die Abarbeitungsreihenfolge der Aktionen kann so optimiert werden, dass die Wahrscheinlichkeit von *Rollbacks* minimiert wird. In diesem Falle ist die Wahrscheinlichkeit, dass es zu einem *Rollback* während des Schritts „Versand der Bestellung“ kommt, wesentlich höher als beim Schritt „Kundenbonus gutschreiben“.



Langlebige Transaktionen mit orchestrierten Sagas
--------------------------------------------------------

.. image:: images/sagas/orchestrierte-saga.svg

.. supplemental::

  Die orchestrierte Saga ist eine Möglichkeit, um langlebige Transaktionen zu implementieren. 

  .. class:: positive-list
  
  - Mental einfach

  .. class:: negative-list list-with-explanations 

  - Hoher Grad an *Domain Coupling* 
  
    Da es sich im Wesentlichen um fachlich getriebene Kopplung handelt, ist diese Kopplung häufig akzeptabel. Die Kopplung erzeugt keine technischen Schulden (:eng:`technical debt`).
  - Hoher Grad an *Request-Response* Interaktionen
  - Gefahr, dass Funktionalität, die besser in den einzelnen Services (oder ggf. neuen Services) unterzubringen wäre, in den Bestellung Service wandert.



Langlebige Transaktionen mit choreografierten Sagas
----------------------------------------------------------

.. image:: images/sagas/choreographierte-saga.svg
   :align: center

.. supplemental::

  Ein großes Problem bei choreografierten Sagas ist es den Überblick über den aktuellen Stand zu behalten. Durch die Verwendung einer "Korrelations-ID" kann diese Problem gemindert werden.


*Dual-write Problem*
---------------------

.. deck::

  .. card:: 

    .. grid::

      .. cell::
          
        .. image:: images/dual-write/no-crash-no-problem.svg
       
      .. cell::

        An welcher Stelle könnte es zu einem Problem kommen?

        .. warning::
          :class: incremental
          
          Das „Schreiben“ auf zwei unterschiedliche Systeme (hier: Datenbank und Event-processing Middleware) erfordert immer einen transaktionalen Kontext. 
          
          Kann dieser nicht hergestellt werden, dann kann es zu Inkonsistenzen kommen (:eng:`Dual-write Problem`).

  .. card:: 

    .. grid:: 

      .. cell::
 
        .. image:: images/dual-write/crash.svg
          :align: center

      .. cell::

        .. rubric:: Lösungsideen

        .. class:: incremental-list negative-list
        
        - 2PC ist im Kontext von Microservices keine Option (zu langsam, zu komplex)
        - Änderung der Reihenfolge der Aktionen (1. *publish* dann 2. *update*) führt noch immer zu Inkonsistenzen
        - die Event Processing Middleware (synchron) zu notifizieren - d. h. als Teil des Datenbankupdates - ist auch keine Option:
        
          .. class:: negative-list

          - Was passiert, wenn die Middleware nicht erreichbar ist?
          - Was passiert, wenn das Event nicht verarbeitet werden kann? 
  
        .. container:: incremental assessment
          
         Strikte Konsistenz ist nicht erreichbar.



*Dual-write Problem* - Outbox Pattern
-----------------------------------------

.. image:: images/dual-write/outbox-pattern.svg

.. supplemental:: 
  
  .. rubric:: *(eine) Lösung: Outbox Pattern*

  - Die Aktionen werden (zusätzlich) in einer Outbox-Tabelle gespeichert und dann **asynchron** verarbeitet.

  - Damit kann *Eventual Consistency* erreicht werden.



.. class:: no-title center-content transition-fade

Die Wahl der richtigen Architektur ist ein Tradeoff!
-----------------------------------------------------

.. container:: accentuate

  Die Wahl der Softwarearchitektur ist immer eine Abwägung von vielen Tradeoffs!

.. supplemental::

  Weitere Aspekte, die berücksichtigt werden können/müssen:

  - Cloud (und ggf. Serverless)
  - Mechanical Sympathy
  - Testen und Deployment von Mircoservices (Stichwort: *Canary Releases*)
  - Monitoring und Logging
  - Service Meshes
  - ...



Literatur
-------------------------------------

.. [Newman2021] Sam Newman, **Building Microservices: Designing Fine-Grained Systems**, O'Reilly, 2021.
