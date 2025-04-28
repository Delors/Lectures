.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "Lamport Clock", "2PC"
    :description lang=de: Grundlegende Konzepte verteilter Systeme: Lamport-Uhren und 2PC
    :description lang=en: Basic concepts of distributed systems: Lamport Clocks and 2PC
    :id: lecture-ds-2pc-and-time
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Basic Concepts of Distributed Systems
===============================================================================

----

:Lecturer: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Contact: michael.eichberg@dhbw.de
:Version: 1.0.1

.. supplemental::  

  :Folien: 
    
      |html-source|
      
      |pdf-source|
  :Fehler melden:

      https://github.com/Delors/delors.github.io/issues



.. class:: center-content

\ 
----

The following concepts are of central importance for the development of distributed systems and are implemented in many current middleware products.



.. class:: new-section transition-fade

Time in distributed systems
--------------------------------------------------------------------------------


The importance of time in distributed systems
--------------------------------------------------------------------------------

.. class:: incremental-list

- Updates that are carried out across several systems must be carried out in the correct order.
- Log entries should be made in the correct order.
- Validity of authorizations (e.g. certificates)
- Geographical positioning (e.g. GPS)



Problems when the time is not correct
--------------------------------------------------------------------------------

.. epigraph::

  A recent surge in GPS “spoofing”, a form of digital attack which can send commercial airliners off course, has entered an intriguing new dimension, according to cybersecurity researchers: The ability to hack time. [...]

  “We think too much about GPS being a source of position, but it's actually a source of time,” [...]  “We're starting to see reports of the clocks on board airplanes during spoofing events start to do weird things."
  In an interview with Reuters, Munro [at Defcon] cited a recent incident in which an aircraft operated by a major Western airline had its onboard clocks suddenly sent forward by years, causing the plane to lose access to its digitally-encrypted communication systems.

  -- `11. August, 2024 - GPS spoofers 'hack time' on commercial airlines <https://www.reuters.com/technology/cybersecurity/gps-spoofers-hack-time-commercial-airlines-researchers-say-2024-08-10/>`__



.. class:: transition-scale 

Real vs. logical time in distributed systems
--------------------------------------------------------------------------------

.. deck::

  .. card::

    .. rubric:: Logical Time

    Logical time allows us to determine a well-defined sequence between events (cf. :eng:`happened before` relation). This is *often sufficient* for distributed systems.

  .. card::

    .. rubric:: Real Time

    :Solar second: refers to the period of time between successive solar equinoxes.

    .. class:: incremental

    :Atomic Second: 
      The reference point is the period of oscillation of a caesium-133 atom.

      TAI (Temps Atomique International): Average time of the atomic clocks of over 60 institutes worldwide (e.g. Braunschweig), determined by the BIH (Bureau International de l'Heure) in Paris.

    .. class:: incremental

    :UTC (Universal Coordinated Time):
      Based on TAI; it is currently still necessary to insert occasional leap seconds to adjust to the solar day. The leap second is expected to be abolished from 2035.



Computer Clock Time
--------------------------------------------------------------------------------

.. class:: incremental-list list-with-explanations

- Real-time Clock (RTC): internal battery-buffered clock.
  
  (The accuracy and resolution are sometimes very coarse).
- Radio-controlled clock (DCF77 from Mainflingen, approx. 2000 km range)
- GPS signal (Global Positioning System) with a resolution of approx. 100 ns
- by means of message exchange with a time server 



Clock synchronization according to Christian
--------------------------------------------------------------------------------

:peripheral:`(Probabilistic Clock Synchronisation, 1989)`

- Prerequisite: central time server with UTC.
- Clients ask periodically and correct by half the response time
- Client clocks are never reset but only slowed down or accelerated if necessary.



Network Time Protocol (NTP, RFC 5905)
--------------------------------------------------------------------------------

.. class:: incremental-list list-with-explanations

- Synchronisation to UTC
  
  - in the local network with an accuracy of up to 200 microseconds
  - on the Internet with an accuracy of 1-10 milliseconds

- Hierarchy of time servers

  Stratum 0: Quelle - z. B. DCF77-Zeitzeichensender

  Stratum 1: Primary server
  
  Stratum 2,...: Secondary-/...server 
  
  Clients

- Mutual exchange of time stamps between the server computers is supported (NTP is symmetrical).

.. supplemental::

  However, the time of an NTP server is only updated if the requesting server has a higher *stratum*\ value (i.e. is potentially less precise) than the requested server. The requesting server then receives the stratum value of the queried server :math-r:`+1`. 



Time: Calculation of the round trip time and the time difference
--------------------------------------------------------------------------------

.. csv-table::
  :width: 100%

  Origin :math:`T_1`, System time of the client when sending the request
  Receive :math:`T_2`, System time of the server when the request is received
  Transmit :math:`T_3`, System time of the server when sending the response
  Destination :math:`T_4`, System time of the client when receiving the response

.. math::

  \text{RTT:}\quad r = (T_4 - T_1) - (T_3 - T_2)

.. math::

  \text{Time difference:}\quad x = \frac{(T_2 - T_1) - (T_4 - T_3)}{2}

.. attention:: 
  :class: incremental

  Exact clock synchronization cannot be achieved in an asynchronous system! 

.. supplemental::

  It is assumed that time passes at virtually the same speed on both computers. The time difference between the two computers is therefore constant. 

  :math:`(T3 - T2)` is the time required by the server for processing.
  
  The round trip time (RTT) is the time it takes for a signal to travel from one computer to another and back. 
  
  The time difference is the difference between the time on the server and the time on the client. 

  Problems with clock synchronisation arise due to uncertain latencies:

  - Message transmission time (depending on distance and medium)
  - Time delay in routers during relaying (load-dependent)
  - Time until interrupt acceptance in the operating system (context-dependent)
  - Time for copying buffers (load-dependent)
  
  Due to these problems, a consistent, realistic global snapshot cannot be realised.



Example of Calculating the Time Difference
--------------------------------------------------------------------------------

.. container:: incremental

  Let the latency be 5 ms and the processing time 2 ms. 
  
  Furthermore, let :math:`T_1 = 110` and :math:`T_2 = 100`. I.e. the client is ahead.

.. container:: incremental 

  Since the processing time of the server is 2 ms, the following applies for :math:`T_3` and :math:`T_4`:
    
  :math:`T_3 = 102` and 
  
  :math:`T_4 = 110+(2 \times 5) +2 = 122`.

.. container:: incremental 

  This results in the time difference:
  
  :math:`x = \frac{(100-110) - (122-102)}{2} = \frac{(-10 - 20)}{2} = -15` ms.



Logical Time
--------------------------------------------------------------------------------

.. observation::
   
  For the consistent view of events in a distributed system, the real time is not important in many cases! 
  
  We only need a globally unique sequence of events, i.e. we need timestamps.
 
  However, not all events influence each other, i.e. they are causally independent.

.. supplemental::

  It is important to know what happened before and what happened after, but it is not important that we know exactly when (time) something happened.



Lamport-Uhren (*logical clocks*)
--------------------------------------------------------------------------------

.. definition::
  
  An event (*write*, *send*, *receive*) is a change in a process.

.. rubric:: Procedure

- before *write* and *send*: increment the local time :math:`T_{local} = T_{local} + 1`
- *send* always include the timestamp: :math:`T_{msg} = T_{local}`
- before *receive*: :math:`T_{local} = max(T_{msg}, T_{local}) + 1`
      
.. container:: incremental
  
  The *receive* event is always after *send*.

  Events are categorised according to the happened-before relation: **a → b**
   
  (a happened-before b) 
   
.. container:: incremental
  
  The result is a partial ordering of the events.

  A consistent snapshot contains the corresponding send event for each receive event. 

.. supplemental::

  Lamport clocks are one way of supporting *totally-ordered multicasts*, which is particularly necessary in combination with replication.



.. class:: exercises transition-scale

Exercise
------------------------

.. scrollable::

  .. exercise:: Lamport-Clocks

    Consider the following situation with three processes in a distributed system. The timestamps of the events are assigned using Lamport's clocks. 
    (The values :math-r:`c` on the far left indicate the state of the respective clocks at the beginning).

    (a) Provide all events with the correct timestamps.
    (b) Specify a consistent save point containing event r.

    .. image:: images/lamport-exercise/task.svg
      :align: center

    .. solution::
      :pwd: ReplicationIncoming.

      (a)

      .. image:: images/lamport-exercise/solution.svg
          :align: center
          :class: box-shadow rounded-corners

      (b)

      The consistent backup point must also contain the events i and q.
      (i *happened before* r, but l and n are not causally related to r).



.. class:: new-section

Distributed transactions
--------------------------------------------------------------------------------


„Atomic Commit Protocol“
--------------------------------------------------------------------------------

.. class:: incremental-list

- Distributed transactions extend over several processes and usually also over several nodes in a distributed system.
- More error cases must be taken into account.

  One example would be the transfer of a sum of money (conceptual example):

  .. code:: java
    :number-lines:
      
    send_money(A, B, amount) { 
      Begin_Transaction();
      if (A.balance - amount >= 0) {
        A.balance = A.balance - amount; 
        B.balance = B.balance + amount; 
        Commit_Transaction();
      } else { 
        Abort_Transaction();
    } }

.. container:: framed incremental

  We need an *Atomic Commit Protocol*.

.. supplemental::

  .. rubric:: Repetition: Transactions

  A transaction ensures the reliable processing of persistent data - even in error situations. The central feature is the guarantee of the ACID properties (Atomicity, Consistency, Isolation, Durability).
  
  At the end of a transaction, either a commit or abort / rollback takes place.

  After a commit, all changes are permanent.

  .. rubric:: Fault tolerance
  
  The aim is to make it possible to build a reliable system from unreliable components.

  Three basic steps:

  1. error detection: recognising the presence of an error in a data value or a control signal
  2. fault localization: limiting the propagation of faults
  3. masking errors: developing mechanisms that ensure that a system functions correctly despite an error (and possibly corrects an error)



Two-Phase Commit Protocol - 2PC
--------------------------------------------------------------------------------

Participants are (1) those (:math:`P_i`), who manage the distributed data, and (2) a coordinator (:math:`K`), who controls the protocol. (:math:`K` may itself be one of the :math:`P_i`)

.. class:: incremental

1. **Coordination Phase**\ :

   .. class:: incremental-list

   - K sends a PREPARE message to all :math:`P_i`.
   - Each :math:`P_i` checks for itself whether the transaction can be completed correctly locally.
   - If so, it sends READY, otherwise ABORT to :math:`K`
  
2. **Decision Phase**\ :

   .. class:: incremental-list

   - If all :math:`P_i` have responded with READY, :math:`K` sends COMMIT to all :math:`P_i`; otherwise :math:`K` sends an ABORT message to all :math:`P_i`
   - If the decision was COMMIT, all :math:`P_i` make the transaction *stable*
   - If the decision was ABORT, all :math:`P_i` roll back the transaction.
   - Finally, all :math:`P_i` send an OK message to :math:`K`

.. supplemental::

    The 2-PC protocol is not error-resistant, i.e. it can recognise errors but cannot necessarily correct them. To handle some error scenarios, results (especially READY and COMMIT) must be recorded in a persistent *write-ahead* log file.



CAP Theorem\ [#]_ 
--------------------------------------------------------------------------------

In **distributed** (*database*)\ *systems*, only two of the following three properties can be guaranteed at the same time:

.. grid:: 

  .. grid:: 

    .. image:: images/cap.svg
      :align: center

  .. grid::  incremental 

    .. class:: list-with-explanations

    - *Consistency*

      After completion of a transaction, the return value of the next read operation is the result of the last write operation or an error.
    - *Availability*
      
      Each request receives a response in an acceptable time.
    - *Partition tolerance*
        
      The system also works with network partitioning, i.e. nodes can no longer communicate with each other.

.. [#] 2000 Brewer(Conjecture), 2002 Gilbert and Lynch(Proof)

.. supplemental::

  The CAP theorem *only* refers to distributed systems. Network partitioning can always occur in such systems. Therefore, partition tolerance is a natural property and you can often only choose between consistency and availability.

  Which properties are important in which scenarios?

  :DNS: Availability and partition tolerance
  :Banking: Consistency and partition tolerance
  
  

.. ideas: Leader Election Algo., Gossip Protocol, RAFT Protocol, Paxos, AMQP



.. class:: exercises transition-move-to-top

Exercise
----------

.. exercise:: Two-Phase-Commit

  Analyse how the two-phase commit protocol deals with error situations.

  Which errors can occur at which points in time and which can the protocol rectify?

  .. solution::
    :pwd: 2PC con do everything?

    Scenarios: Messages can be lost, nodes can fail and network partitioning can occur.

    Lost messages can be recognized by means of timeouts and sent again.
    
    Persistent network partitioning during the first phase, which means that one or more participants in the protocol flow can no longer communicate with the coordinator, will result in the coordinator deciding ABORT.

    If a participant drops out in the second phase, they will not be informed of the coordinator's decision. However, the following applies:
    
    - The coordinator has recorded the decision in the persistent log file (stable storage).
    - The participant has noted in its persistent log file that the transaction has been started but not yet completed. After booting, the participant asks the coordinator for the outcome of the transaction. If all participants know the outcome of the transaction, the coordinator can delete the log entry.
     
    If the coordinator fails after it has made the decision and noted it in the log file, or if a network partition occurs at this point, the log can only be continued after the coordinator has rebooted. The log is blocked until then.
    - If one of the participants already knows the coordinator's decision, it can forward it to the other participants on request.
    - If a participant has responded with ABORT, they can also reset the transaction without the coordinator and inform the other participants of this on request.




