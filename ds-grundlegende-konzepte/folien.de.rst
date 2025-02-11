.. meta::
    :version: genesis
    :author: Michael Eichberg
    :keywords: "Lamport Clock", "2PC"
    :description lang=de: Grundlegende Konzepte verteilter Systeme: Lamport-Uhren und 2PC
    :description lang=en: Basic concepts of distributed systems: Lamport Clocks and 2PC
    :id: lecture-ds-2pc-und-zeit
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. |html-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html
.. |pdf-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html.pdf
.. |at| unicode:: 0x40

.. role:: incremental
.. role:: eng
.. role:: ger
.. role:: minor
.. role:: obsolete
.. role:: dhbw-red
.. role:: dhbw-gray
.. role:: dhbw-light-gray
.. role:: the-blue
.. role:: the-green
.. role:: the-orange
.. role:: shiny-green
.. role:: shiny-red
.. role:: black
.. role:: dark-red
.. role:: huge
.. role:: smaller  

.. role:: raw-html(raw)
   :format: html



Grundlegende Konzepte verteilter Systeme
===============================================================================

.. container:: line-above 

  :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
  :Kontakt: michael.eichberg@dhbw.de
  :Version: 1.0.1
 
.. supplemental::  

  :Folien: 
    
      |html-source|
      
      |pdf-source|
  :Fehler melden:

      https://github.com/Delors/delors.github.io/issues


.. class:: center-child-elements

\ 
----

Die folgenden Konzepte sind für die Entwicklung verteilter Systeme von zentraler Bedeutung und sind in vielen aktuellen Middlewareprodukten umgesetzt.



.. class:: new-section transition-fade

Zeit in verteilten Systemen
--------------------------------------------------------------------------------


Von der Bedeutung der Zeit in verteilten Systemen
--------------------------------------------------------------------------------

- Updates, die über mehrere Systeme hinweg erfolgen, müssen in korrekter Reihenfolge erfolgen.
- Log-Einträge sollen in korrekter Reihenfolge erfolgen.
- Gültigkeit von Berechtigungen (z. B. Zertifikate)
- geographische Positionsbestimmung (z. B. GPS)



Probleme, wenn die Zeit nicht korrekt ist
--------------------------------------------------------------------------------

.. epigraph::

  A recent surge in GPS “spoofing”, a form of digital attack which can send commercial airliners off course, has entered an intriguing new dimension, according to cybersecurity researchers: The ability to hack time. [...]

  “We think too much about GPS being a source of position, but it's actually a source of time,” [...]  “We're starting to see reports of the clocks on board airplanes during spoofing events start to do weird things."
  In an interview with Reuters, Munro [at Defcon] cited a recent incident in which an aircraft operated by a major Western airline had its onboard clocks suddenly sent forward by years, causing the plane to lose access to its digitally-encrypted communication systems.

  -- `11. August, 2024 - GPS spoofers 'hack time' on commercial airlines <https://www.reuters.com/technology/cybersecurity/gps-spoofers-hack-time-commercial-airlines-researchers-say-2024-08-10/>`__



.. class:: center-child-elements transition-scale no-title

Reale vs. logische Zeit
--------------------------------------------------------------------------------

.. container:: dhbw-light-gray-background rounded-corners padding-1em margin-top-2em

  In verteilten System unterscheiden wir **reale** und **logische Zeit**.

  .. container:: dhbw-gray

    Die logische Zeit ermöglicht es uns, eine wohldefinierte Reihenfolge zwischen Ereignissen (vgl. :eng:`happened before` Relation) zu bestimmen. *Häufig* ist dies für verteilte Systeme ausreichend.



Reale Zeit
--------------------------------------------------------------------------------

:Sonnensekunde: bezieht sich auf die Zeitspanne zwischen aufeinanderfolgenden Sonnenhöchstständen.

.. class:: incremental

:Atomzeitsekunde: 
   Bezugspunkt ist die Schwingungsdauer eines Cäsium-133-Atoms.

   TAI (Temps Atomique International): Durchschnittszeit der Atomuhren von über 60 Instituten weltweit (z. B. Braunschweig), ermittelt vom BIH (Bureau International de l’Heure) in Paris

.. class:: incremental

:UTC (Universal Coordinated Time):
  Basiert auf TAI; aktuell ist noch das Einfügen gelegentlicher Schaltsekunden zur Anpassung an den Sonnentag erforderlich. Ab 2035 wird die Schaltsekunde voraussichtlich abgeschafft.


Computeruhrzeit
--------------------------------------------------------------------------------

.. class:: incremental list-with-explanations

- Real-time Clock (RTC): interne batteriegepufferte Uhr.
  
  (Die Genauigkeit und Auflösung sind teilweise sehr grob.)
- Funkuhr (DCF77 aus Mainflingen, ca. 2000 km Reichweite)
- GPS-Signal (Global Positioning System) mit einer  Auflösung von ca. 100 ns
- mittels Nachrichtenaustausch mit einem Zeitserver 


Uhrensynchronisation nach Christian
--------------------------------------------------------------------------------

:minor:`(Probabilistic Clock Synchronisation, 1989)`

- Voraussetzung: zentraler Zeitserver mit UTC.
- Clients fragen periodisch nach und korrigieren um halbe Antwortzeit
- Client-Uhren werden niemals zurückgesetzt sondern ggf. nur verlangsamt bzw. beschleunigt.



Network Time Protocol (NTP, RFC 5905)
--------------------------------------------------------------------------------

.. class:: incremental list-with-explanations

- Synchronisierung auf UTC
  
  - im lokalen Netz mit einer Genauigkeit von bis zu 200 Mikrosekunden
  - im Internet mit einer Genauigkeit von 1-10 Millisekunden

- Hierarchie von Zeitservern

  Stratum 0: Quelle - z. B. DCF77-Zeitzeichensender

  Stratum 1: Primärserver
  
  Stratum 2,...: Sekundär-/...server 
  
  Clients

- Wechselseitiger Austausch von Zeitstempeln zwischen den Server-Rechnern wird unterstützt (NTP ist symmetrisch).

.. supplemental::

  Aktualisierung der Zeit eines NTP Servers erfolgt aber nur wenn der anfragende Server einen höheren *Stratum*\ wert hat (d. h. potentiell unpräziser ist) als der angefragte Server. Der anfragende Server erhält danach den Stratumwert des abgefragten Servers :math:`+1`. 


.. class:: smaller-slide-title

Zeit: Berechung der Round-Trip-Time und der Zeitdifferenz/des Gangunterschieds
--------------------------------------------------------------------------------

.. csv-table::
  :width: 100%

  Origin :math:`T_1`, Systemzeit des Clients beim Absenden der Anfrage
  Receive :math:`T_2`, Systemzeit des Servers beim Empfang der Anfrage
  Transmit :math:`T_3`, Systemzeit des Servers beim Absenden der Antwort
  Destination :math:`T_4`, Systemzeit des Clients beim Empfang der Antwort

.. math::

  RTT: r = (T_4 - T_1) - (T_3 - T_2)

.. math::

  Gangunterschied: x = \frac{(T_2 - T_1) - (T_4 - T_3)}{2}


.. admonition:: Achtung
  :class: warning margin-top-1em incremental

  Eine exakte Uhrensynchronisation ist in einem asynchronem System nicht realisierbar! 

.. supplemental::

  Es wird die Annahme getroffen, dass die Zeit auf beiden Rechnern quasi gleichschnell vergeht. Die Zeitdifferenz zwischen den beiden Rechnern ist also konstant. 

  :math:`(T3 - T2)` ist die Zeit, die der Server zum Bearbeiten benötigt.
  
  Die Round-Trip-Time (RTT) ist die Zeit, die ein Signal benötigt, um von einem Rechner zum anderen und zurückzugelangen. 
  
  Der Gangunterschied ist die Differenz zwischen der Zeit auf dem Server und der Zeit auf dem Client. 

  Probleme bei der Uhrensynchronisation entstehen aufgrund ungewisser Latenzen:

  - Nachrichtenübertragungszeit (abhängig von Entfernung und Medium)
  - Zeitverzögerung in Routern bei Weitervermittling (lastabhängig)
  - Zeit bis zur Interrupt-Annahme im Betriebssystem (kontextabhängig)
  - Zeit zum Kopieren von Puffern (lastabhängig)
  
  Aufgrund der Probleme ist ein konsistenter, realistischer globaler Schnappschuss nicht realisierbar.


Beispiel zur Berechnung des Gangunterschieds
--------------------------------------------------------------------------------

.. container:: incremental

  Sei die Latenz 5 ms und die Bearbeitungszeit 2 ms. 
  
  Weiterhin sei :math:`T_1 = 110` und :math:`T_2 = 100`. D. h. der Client geht vor. 

.. container:: incremental margin-top-2em

  Da die Bearbeitungszeit des Servers 2 ms beträgt, gilt für :math:`T_3` und :math:`T_4`:
    
  :math:`T_3 = 102` und 
  
  :math:`T_4 = 110+(2 \times 5) +2 =  122`.

.. container:: incremental margin-top-2em

  Somit ergibt sich der Gangunterschied zu:
  
  :math:`x = \frac{(100-110) - (122-102)}{2} = \frac{(-10 - 20)}{2} = -15` ms.


.. class:: center-child-elements 

Logische Zeit
--------------------------------------------------------------------------------

.. container:: assessment
   
  Für die konsistente Sicht von Ereignissen in einem verteilten System ist die reale Zeit in vielen Fällen nicht wichtig! 
  
  Wir benötigen nur eine global eindeutige Reihenfolge der Ereignisse; d. h. wir benötigten Zeitstempel.
 
  Jedoch beeinflussen sich nicht alle Ereignisse untereinander; d. h. sind kausal unabhängig.

.. supplemental::

  Es ist wichtig zu wissen, was vorher und was nachher passiert ist, aber es ist nicht wichtig, dass wir wissen wann genau (Uhrzeit) etwas passiert ist.


Lamport-Uhren (*logical clocks*)
--------------------------------------------------------------------------------

.. admonition:: Ereignis
  :class: definition
  
  Ein Ereignis (*write*, *send*, *receive*) ist eine Zustandsänderung in einem Prozess.

.. container:: smaller
    
  .. rubric:: Vorgehensweise

  - vor *write* und *send*: erhöhen der lokalen Zeit :math:`T_{local} = T_{local} + 1`
  - *send* immer inklusive Zeitstempel: :math:`T_{msg} = T_{local}`
  - vor *receive*: :math:`T_{local} = max(T_{msg}, T_{local}) + 1`
      
.. container:: assessment smaller incremental
  
   Ereignis *receive* ist zeitlich immer nach *send*.

   Ereignisse werden eingeordnet nach der „happened-before“ Relation: 
   
   a → b

   (a happened-before b) 
   
.. container:: smaller incremental
  
  Resultat: es ergibt sich eine partielle Ordnung (partial ordering) der Ereignisse.

  Ein konsistenter Schnappschuss enthält zu jedem Empfangs- das entsprechende Sendeereignis. 


.. supplemental::

  Lamport Uhren sind eine Möglichkeit, um Totally-ordered Multicast zu unterstützen, was insbesondere im Zusammenhang mit Replication von Nöten ist.


.. class:: integrated-exercise transition-scale

Übung
------------------------

.. exercise:: Lamport-Uhren
  :class: smaller

  Gegeben sei die nachfolgend dargestellte Situation mit drei Prozessen in einem verteilten System. Die Zeitstempel der Ereignisse werden mittels der Lamport'schen Uhren vergeben.

  (Die Werte c ganz links geben den Stand der jeweiligen Uhren zu Beginn an.)

  (a) Versehen Sie alle Ereignisse mit den korrekten Zeitstempeln.
  (b) Geben Sie einen konsistenten Sicherungspunkt an, der Ereignis r enthält.

  .. image:: images/lamport-exercise/task.svg
     :width: 60%
     :align: center
     :class: box-shadow rounded-corners

  .. solution::
    :pwd: ReplikationVoraus.

    (a)

    .. image:: images/lamport-exercise/solution.svg
        :width: 60%
        :align: center
        :class: box-shadow rounded-corners

    (b)

    Der konsistente Sicherungspunkt muss ebenfalls die Ereignisse i und q enthalten.
    (i *happend before* r, aber l und n stehen in keinem kausalen Zusammenhang zu r.)


.. class:: new-section

Verteilte Transaktionen
--------------------------------------------------------------------------------


„Atomic Commit Protocol“
--------------------------------------------------------------------------------

.. class:: incremental smaller

- Verteilte Transaktion erstrecken sich über mehrere Prozesse und meist auch über mehrere Knoten in einem verteilten System.
- Mehr Fehlerfälle müssen berücksichtigt werden.

  Ein Beispiel wäre die Überweisung eines Geldbetrags (konzeptionelles Beispiel):

  .. code:: Pseudocode
    :class: tiny
      
    send_money(A, B, amount) { 
      Begin_Transaction();
      if (A.balance - amount >= 0) {
        A.balance = A.balance - amount; 
        B.balance = B.balance + amount; 
        Commit_Transaction();
      } else { 
        Abort_Transaction();
    } }

.. container:: assessment incremental

  Wir brauchen ein *Atomic Commit Protocol*.


.. supplemental::

  .. rubric:: Wiederholung Transaktionen

  Eine Transaktion stellt die zuverlässige Bearbeitung persistenter Daten sicher – auch in Fehlersituationen. Zentrales Merkmal ist die Garantie der ACID-Eigenschaften (Atomicity, Consistency, Isolation, Durability).
  
  Am Ende einer Transaktion findet entweder ein commit oder abort / rollback statt.

  Nach einem commit sind alle Änderungen dauerhaft.

  .. rubric:: Fehlertoleranz
  
  Das Ziel ist es zu ermöglichen, ein zuverlässiges System aus unzuverlässigen Komponenten aufzubauen.

  Drei grundsätzliche Schritte:

  1. Erkennung von Fehlern: Erkennen des Vorhandenseins eines Fehlers in einem Datenwert oder einem Steuersignal
  2. Fehlereingrenzung: Begrenzung der Fehlerausbreitung
  3. Maskierung von Fehlern: Entwicklung von Mechanismen, die sicherstellen, dass ein System trotz eines Fehlers korrekt funktioniert (und möglicherweise einen Fehler korrigiert)


.. class:: smaller

Two-Phase Commit Protocol - 2PC
--------------------------------------------------------------------------------

Teilnehmer sind (1) die Partizipanten (:math:`P_i`), welche die verteilten Daten verwalten, und (2) ein Koordinator, (:math:`K`) der die Steuerung des Protokolls übernimmt. (:math:`K` darf selbst einer der :math:`P_i` sein)

.. class:: incremental

1. **Abstimmungsphase**\ :

   .. class:: incremental

   - K sendet eine PREPARE-Nachricht an alle :math:`P_i`.
   - Jeder :math:`P_i` prüft für sich, ob die Transaktion lokal korrekt abgeschlossen werden kann.
   - Falls ja, sendet er READY, anderenfalls ABORT an :math:`K`
  
2. **Entscheidungsphase**\ :

   .. class:: incremental

   - Falls alle :math:`P_i` mit READY geantwortet haben, sendet :math:`K` COMMIT an alle :math:`P_i`; anderenfalls sendet :math:`K` eine ABORT-Nachricht an alle :math:`P_i`
   - Falls die Entscheidung COMMIT war, machen alle :math:`P_i` die Transaktion *stabil*
   - Falls die Entscheidung ABORT war, setzen alle :math:`P_i` die Transaktion zurück.
   - Alle :math:`P_i` senden schließlich eine OK-Nachricht an :math:`K`

.. supplemental::

  Das 2-PC Protokoll ist nicht Fehlerresistent. d. h. es kann Fehler erkennen, aber nicht zwangsläufig korrigieren. Um einige Fehlerszenarien zu behandeln, müssen Ergebnisse (insbesondere READY und COMMIT) in einem persistenten *write-ahead* Log-File festgehalten werden.



CAP Theorem\ [#]_ 
--------------------------------------------------------------------------------

In **verteilten** (*Datenbank-*)\ *Systemen* können nur zwei der drei folgenden Eigenschaften gleichzeitig garantiert werden:

.. container:: two-columns

  .. container:: column

    .. image:: images/cap.svg
      :height: 750
      :align: center

  .. container:: column incremental smaller

    .. class:: list-with-explanations

    - Konsistenz (:eng:`Consistency`)

      (Nach Abschluss einer Transaktion ist der Rückgabewert der nächsten Leseoperation das Ergebnis der letzten Schreiboperation oder ein Fehler.)
    - Verfügbarkeit (:eng:`Availability`)
      
      (Jede Anfrage erhält eine Antwort in akzeptabler Zeit.)
    - Partitionstoleranz (:eng:`Partition Tolerance`)
        
      (Das System funktioniert auch bei Netzwerkpartitionierungen; d.h. Knoten können nicht mehr miteinander kommunizieren.)


.. [#] 2000 Brewer(Vermutung), 2002 Gilbert und Lynch(Beweis)

.. supplemental::

  Das CAP Theorem bezieht sich „nur“ auf verteilte Systeme. In solchen Systemen kann es immer zu Netzwerkpartitionierungen kommen. Deswegen ist Partitionstoleranz eine natürliche Eigenschaft und man kann häufig „nur“ zwischen Konsistenz und Verfügbarkeit wählen.

  Welche Eigenschaften sind in welchen Szenarien wichtig?

  :DNS: Verfügbarkeit und Partitionstoleranz
  :Banking: Konsistenz und Partitionstoleranz
  



.. ideas: Leader Election Algo., Gossip Protocol, RAFT Protocol, Paxos, AMQP



.. class:: integrated-exercise

Übung
----------

.. exercise:: Two-Phase-Commit

  Analysieren Sie, wie das Two-Phase-Commit-Protokoll mit Fehlersituationen umgeht.

  Welche Fehler können zu welchen Zeitpunkten auftreten und welche kann das Protokoll beheben?

  .. solution::
    :pwd: 2PC kann alles?

    Szenarien: Es können Nachrichten verloren gehen, es können Knoten ausfallen und es kann zu einer Netzpartitionierung kommen.

    Verlorengegangene Nachrichten können mittels Timeouts erkannt und nochmals gesendet werden.
    
    Eine andauernde Netzpartitionierung während der ersten Phase, die dazu führt, dass ein oder mehrere Teilnehmer des Protokollablaufs nicht mehr mit dem Koordinator kommunizieren können, wird dazu führen, dass der Koordinator ABORT entscheidet.

    Fällt ein Teilnehmer in der ersten Phase aus, so antwortet er nicht. Der Koordinator wertet dies als ABORT und entscheidet ABORT.

    Fällt ein Teilnehmer in der zweiten Phase aus, so bekommt er die Entscheidung des Koordinators nicht mit. Es gilt jedoch:
    
    - Der Koordinator hat die Entscheidung im persistenten Log-File (stable storage) festgehalten.
    - Der Teilnehmer hat in seinem persistenten Log-File notiert, dass die Transaktion begonnen, aber noch nicht abgeschlossen wurde. Nach dem Booten erfragt der Teilnehmer beim Koordinator den Ausgang der Transaktion. Wenn alle Teilnehmer den Ausgang der Transaktion kennen, kann der Koordinator den Log-Eintrag löschen.
     
    Fällt der Koordinator aus, nachdem er die Entscheidung getroffen und diese im Log-File notiert hat, oder kommt es zu diesem Zeitpunkt zu einer Netzpartitionierung, so kann das Protokoll erst nach dem Reboot des Koordinators fortgesetzt werden. Das Protokoll ist solange blockiert.
    - Kennt einer der Teilnehmer die Entscheidung des Koordinators bereits, kann er diese auf Nachfrage an die anderen Teilnehmer weiterleiten.
    - Hat ein Teilnehmer mit ABORT geantwortet, dann kann er die Transaktion auch ohne den Koordinator zurücksetzen und dies auf Nachfrage auch den anderen Teilnehmern mitteilen.



