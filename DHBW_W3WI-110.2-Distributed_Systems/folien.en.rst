.. meta::
    :author: Michael Eichberg
    :keywords: Distributed Systems, Lecture, Planning, Organization
    :description lang=de: Distributed Systems
    :description lang=de: Verteilte Systeme
    :id: lecture-w3wi_110.2-distributed-systems_wirtschaftsinformatik
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



W3WI_110.2 - Distributed Systems
================================================

:Lecturer: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Contact: michael.eichberg@dhbw.de, Raum 149B
:Version: 2.0 (24SCA und 24EG/EH)



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
- Who knows what a RESTful API is?
- Who has ever logged in to a server via SSH?
- Who has ever done any administration of a Linux server?
- Who is familiar with Unix/Linux/Mac OS command line tools?
- Who has done any development outside of university projects?



Examination - Portfolio
------------------------------------------

.. background::

    .. class:: list-with-explanations

    - the module *Developing Distributed Systems* has 55 lecture hours
    - the lecture *Distributed Systems* has 22 lecture hours

      ⇒ Distributed Systems will contribute up to **50** points (out of 120) to the final grade for the module. (Please, don't do the math.)

.. rubric:: 2 Parts

.. class:: dhbw

1. Presentations
2. Programming Exercise 



Presentations - General Conditions
------------------------------------------

.. class:: incremental-list list-with-explanations

- Each person should present for 10 Minutes sharp!
- The presentations should deal in particular with the core content of the lecture and *be of a conceptual nature*.

  This means, after briefly presenting the overall purpose of the framework/technology/protocol, the architecture/the details must be presented. I. e., how errors are dealt with, which services are offered, which guarantees/security aspects are implemented, how scalability is achieved, etc.

  No promotional presentations!
- Presentations have to be in English.
- The speakers should not rotate several times during the presentation. I. e. the first speaker presents first, then the second, and so on. This is necessary for the grading.



Presentations - Submissions
------------------------------------------

.. attention::

  You have to upload your presentation to Moodle 36 hours before you give the presentation.

.. attention::
  :class: incremental

  `Further Requirements and Checklist (in Ger.) <https://delors.github.io/allg-vortraege/folien.de.rst.html#slide-3>`__

  The checklist hast to be signed and uploaded together with the presentation. (As a second PDF file.)



Presentations - Available Topics
------------------------------------------

.. deck:: 

  .. card::

    .. hint::

        Students giving presentations belonging to the same block have to coordinate with each other to avoid any overlap. If you need a specific topic to be covered by another student but are not sure whether it will be presented sufficiently, create a backup slide for your presentation that covers this topic as well and mark it as a backup slide. This backup slide will not be counted towards the time limit.

  .. card:: 

        .. rubric:: Virtualization and Virtualization Platforms

        .. class:: dhbw list-with-sublists show-list-item-content-on-hover font-size-90

        1. Introduction to Virtualization & Use Cases

           - What is virtualization? Historical context and motivation
           - Different types/levels of virtualization
           - Key use cases: server consolidation, cloud computing, development/testing, isolation
           - Benefits and trade-offs

        2. Hypervisors - Architecture & Types

           - What is a hypervisor?
           - Type 1 (bare-metal) vs Type 2 (hosted) - architectural differences
           - Full virtualization vs paravirtualization approaches
           - Examples and when to use each type

        3. Virtual Machines - Implementation & Management

           - VM structure and components (virtual hardware, guest OS)
           - VM lifecycle: creation, running, pause/resume, snapshots
           - VM migration (live migration concepts)
           - Resource allocation and isolation

        4. Containers & OS-level Virtualization

           - Container concept and how it differs from VMs
           - Namespaces and cgroups (conceptual)
           - Container images and layering
           - Use cases and comparison with VMs

        5. Memory Virtualization

           - The address translation problem (guest virtual → guest physical → host physical)
           - Shadow page tables approach
           - Hardware-assisted virtualization (EPT/NPT)
           - Memory management techniques (overcommitment, ballooning)

        6. Network & I/O Virtualization

           - Challenges of virtualizing network and I/O devices
           - Emulated vs paravirtualized devices
           - SR-IOV (Single Root I/O Virtualization) and device passthrough
           - Virtual NICs and network bridges
           - Virtual switches and network isolation

  .. card:: 

        .. rubric:: Network Protocols

        .. class:: dhbw font-size-90

        1. QUIC :peripheral:`(only available when we have ≥ 21 students)`
        2. HTTP/3
        3. BitTorrent Protocol :peripheral:`(only available when we have ≥ 25 students)`

  .. card:: 

        .. rubric:: Modern RPC

        .. class:: dhbw font-size-90

        1.  Protobuf
        2.  Google RPC

  .. card:: 

        .. rubric:: Web-App Security

        .. class:: dhbw font-size-90

        1.  SOP (Same-Origin Policy), CORS (Cross-Origin Resource Sharing) (Foundations)
        2.  CORP / COOP / COEP (Cross-Origin Resource/Opener/Embedder Policies) :peripheral:`(only available when we have ≥ 23 students)`
        3.  CSP (Content Security Policy) and SRI (Subresource Integrity)

        Introduction and concrete examples how they are used/specified and help prevent attacks.

  .. card:: 

        .. rubric:: Monitoring & Debugging Distributed Systems

        .. class:: dhbw font-size-90

        1.  Log Aggregation with a particular focus on correlation of log entries

  .. card:: 

        .. rubric:: Leader Election

        .. class:: dhbw font-size-90

        1.  Bully Algorithm and/or Ring Algorithm

  .. card::

        .. rubric:: Quorum Systems
        
        .. class:: dhbw font-size-90       

        1.  Majority voting (i. e., quorum-distributed computing)        

  .. card::

        .. rubric:: Consensus Algorithms and Fault Tolerance

        .. class:: dhbw list-with-sublists show-list-item-content-on-hover font-size-90       

        1.  Consensus Fundamentals & Problem Definition

            - What is consensus and why is it hard in distributed systems?
            - The FLP impossibility result (conceptual understanding)
            - Fault models: crash faults vs Byzantine faults
            - Safety vs liveness properties
            - Real-world motivation: replicated state machines, distributed databases

        2.  (Practical) Byzantine Fault Tolerance

            - When do we need BFT?
            - Modern developments
          
              .. presenter-note::
                
                Tendermint, HotStuff

            - Real-world usage

        3.  Paxos Family

            - Basic Paxos algorithm (conceptual overview, roles: proposers, acceptors, learners)
            - Why Paxos is correct but complex
            - Multi-Paxos for practical systems
            - Real-world usage
          
              .. presenter-note::
          
                [VERIFY!:] Google Chubby, Apache ZooKeeper foundations

        4.  Raft - Understandable Consensus

            - Motivation
            - Leader election, log replication, safety
            - How Raft differs from Paxos (design philosophy)
            - Real-world usage
          
              .. presenter-note::
                
                etcd, Consul, CockroachDB
  
  .. card:: 

        .. rubric:: Eventual Consistency
    
        .. class:: dhbw font-size-90       

        1.  Eventual Consistency and Gossip Protocol
        2.  CRDTs (Conflict-free Replicated Data Types) :peripheral:`(only available when we have ≥ 22 students)`

  .. card:: 

        .. rubric:: Distributed File Systems

        .. class:: dhbw font-size-90       

        1.  Ceph
        2.  HDFS :peripheral:`(only available when we have ≥ 24 students)`



Topic Assignment
------------------------------------------

.. scrollable:: 

  The presentations need to be given in the order listed below.

  .. list-table::
    :header-rows: 1
    :widths: 57 18 25
    :class: dhbw compact sticky-header

    * - Topic
      - Constraint
      - Student Name
    * - \01. Introduction to Virtualization & Use Cases
      - 
      - 
    * - \02. Hypervisors - Architecture & Types
      - 
      - 
    * - \03. Virtual Machines 
      - 
      - 
    * - \04. Containers & OS-level Virtualization
      - 
      - 
    * - \05. Memory Virtualization
      - 
      - 
    * - \06. Network & I/O Virtualization
      - 
      - 
    * - \07. QUIC
      - ≥ 21 students
      - 
    * - \08. HTTP/3
      - 
      - 
    * - \09. BitTorrent Protocol
      - ≥ 25 students
      - 
    * - \10. Protobuf
      - 
      - 
    * - \11. Google RPC
      - 
      - 
    * - \12. SOP, CORS (Foundations)
      - 
      - 
    * - \13. CORP / COOP / COEP
      - ≥ 23 students
      - 
    * - \14. CSP and SRI
      - 
      - 
    * - \15. Log Aggregation
      - 
      - 
    * - \16. Leader Election (Bully/Ring Algorithm)
      - 
      - 
    * - \17. Quorum Systems (Majority Voting)
      - 
      - 
    * - \18. Consensus Fundamentals & Problem Definition
      - 
      - 
    * - \19. Byzantine Fault Tolerance (PBFT)
      - 
      - 
    * - \20. Paxos Family
      - 
      - 
    * - \21. Raft - Understandable Consensus
      - 
      - 
    * - \22. Eventual Consistency and Gossip Protocol
      - 
      - 
    * - \23. CRDTs
      - ≥ 22 students
      - 
    * - \24. Ceph
      - 
      - 
    * - \25. HDFS
      - ≥ 24 students
      -



Moderator Assignment
------------------------------------------

.. grid::
    
    .. cell:: width-50

      .. csv-table::
          :header: Topic Id, Moderator Id
          :width: 100%
          :widths: 50 50
          :class: compact dhbw
          :stub-columns: 1

          1,
          2,
          3,
          4,
          5,
          6,
          7,
          8,  
          9,
          10, 
          11,
          12,
          13,


    .. cell:: width-50

      .. csv-table::
          :header: Topic Id, Moderator Id
          :width: 100%
          :widths: 50 50
          :class: compact dhbw
          :stub-columns: 1

          14,
          15,
          16,
          17,
          18,  
          19,
          20, 
          21,
          22,
          23,
          24,
          25,
          26,



Presentations - Grading (max 25 Points)
------------------------------------------

.. class:: list-with-explanations incremental-list

- Presentation (max 20 Points)
- Checklist (max 1 Point)
- Moderation (max 4 Points)

  A Moderator has three main tasks:

  1. :peripheral:`(Introduce the speaker) and` *motivate the topic*.
  2. Keep track of time and *give time warnings to the presenter* (2 minutes left, time is up); abort the presentation if necessary (-1 minute).
  3. Lead a short Q&A session (2-3 questions) after the presentation; if there are no questions from the audience, *the moderator should have two to three questions*.




Programming Task (max 25 Points)
----------------------------------

.. container:: accentuate

    See Moodle April 7th for task description and grading details.




Lecture - Schedule
------------------------------------------

:3. Mar 2026: Lecture

:17. Mar 2026: Lecture
    Assignment of moderators to presentation topics

:7. Apr 2026: 
    Presentations (~8 students)

    Programming Task Explanation 

    Lecture

:20. Apr 2026 (5VL): 
    Presentations (~8 students) 

    Lecture

:24. Apr 2026 (5VL): 
    Presentations (~8 students) 

    Lecture

:8. May 2026 (Event): 
    Programming Task Submission Deadline (see Moodle for details)