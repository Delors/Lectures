.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "Distributed Systems", "Lecture", "Planning"
    :description lang=de: Distributed Systems
    :description lang=de: Verteilte Systeme
    :id: lecture-w3wi_110.2-distributed-systems_wirtschaftsinformatik
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. |at| unicode:: 0x40

.. role:: incremental   
.. role:: eng
.. role:: ger
.. role:: red
.. role:: green
.. role:: obsolete
.. role:: peripheral

.. role:: raw-html(raw)
   :format: html



W3WI_110.2 - Distributed Systems 
================================================

----

:Lecturer: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Contact: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0 (WWI23SCA)



Core Topics
---------------------------

.. class:: incremental-list 

- Terminology, concepts, architectures, requirements profiles and architecture models for distributed systems
- Design and implementation approaches
- Comparison of different middleware concepts
- Synchronous and asynchronous communication, remote method calls
- Asynchronous communication and messaging systems
- Security aspects in distributed systems



Previous Knowledge
---------------------------

.. class:: incremental-list

- Who is familiar with Java?
- Who is familiar with Python?
- Who is familiar with JavaScript/Typescript?
- Who has done any development outside of university projects?
- Who knows what a RESTful API is?



Examination - Portfolio
------------------------------------------

.. background:: 

    .. class:: list-with-explanations

    - the module has 55 lecture hours
    - Distributed Systems has 22 lecture hours
      
      ⇒ Distributed Systems will contribute up to **50** points to the final grade for the module. (Please, don't do the math.)

.. rubric:: 2 Parts

.. class:: dhbw

1. Presentations (in teams of 2 to 3 students) - max. 15 points
2. Programming Exercise (in small teams) - max. 35 points



Presentations - General Conditions
------------------------------------------

.. class:: list-with-explanations

- The presentations should deal in particular with the core content of the lecture and be of a conceptual nature.  

  This means that, after briefly presenting the overall purpose of the framework/technology/protocol, the architecture/the details must be presented. That is, how errors are dealt with, which services are offered, which guarantees/security aspects are implemented, how scalability is achieved, etc. 
  
  No promotional presentations!
- The presentations are to be uploaded to Moodle the evening before the agreed date.
- Presentations should be in English.
- The speakers should not rotate several times during the presentation. I.e. the first speaker presents first, then the second, and so on. This is necessary for the grading.



Presentations - Available Topics
------------------------------------------

.. presenter-note:: 

            Backend Subsetting and connection churn is about the challenges that arise when a backend service is split into multiple services and how the connection churn can be managed.

.. story:: 
    
  .. rubric:: Must-have topics

  .. list-table:: 
    :width: 100%
    :widths: 70 30
    :class: booktabs incremental-table-rows
    :header-rows: 0

    * - **Byzantine faults** `🔗 <https://en.wikipedia.org/wiki/Byzantine_fault>`__ 
      - 1 Person
    * - **LDAP** `🔗 <https://www.rfc-editor.org/rfc/rfc4511.txt>`__ 
      - 2 - 3 Persons
    * - **Backend Subsetting and connection churn** `🔗 <https://queue.acm.org/detail.cfm?id=3570937>`__ 
      - 1 Person
    * - **Virtualization**: The goal of this presentation is to give an overview of the different virtualization technologies and to explain the commonalities and differences between them. It is also possible to demonstrate some of the differences using concrete products. 

        :peripheral:`Some keywords: Containers (e. g., Docker, Linux), Firecracker, Hypervisors (KVM, Xen, Hyper-V)`
      - 3 - 5 Persons
    * - **Real time system monitoring with ebpf** `🔗 <https://queue.acm.org/detail.cfm?id=3178371>`__
      - 2 Persons (One with interest in Linux)
    * - **HTTP/3 and QUIC or HTTP over QUIC** `🔗 <https://en.wikipedia.org/wiki/HTTP/3>`_
      - 2 Persons

  .. class:: incremental

  .. rubric:: Optional topics

  .. list-table:: 
    :width: 100%
    :widths: 70 30
    :class: booktabs incremental-table-rows
    :header-rows: 0

    * - **Paxos** `🔗 <https://en.wikipedia.org/wiki/Paxos_(computer_science)>`__
      - 2 Persons
    * - **Raft Consensus Algorithm** `🔗 <https://raft.github.io>`__ 
      - 2 Persons
    * - **Gossip Protokoll** `🔗 <https://highscalability.com/gossip-protocol-explained/>`__
      - 2 Persons
    * - **gRPC** `🔗 <https://grpc.io>`__
      - 2 Persons
    * - **Web and Distributed Application Testing**

        Client and Server Side testing should be discussed.
      - 4 Persons 
    * - **Neo4J** `🔗 <https://neo4j.com>`__
      - 2 Persons

.. supplemental::

  :eng:`connection churn` ≘ :ger:`Verbindungsabbruch`
