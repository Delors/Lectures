.. meta::
    :author: Michael Eichberg
    :keywords: "IT Sicherheit", "Pentesting"
    :description lang=de: Fortgeschrittene Angewandte IT Sicherheit - Pentesting
    :id: 2024_08-w3wi_se411-forschungsseminar_informatik-adv._practical_it_security
    :first-slide: last-viewed

.. include:: ../docutils.defs



*W3WI_SE411* - Forschungsseminar Informatik / Advanced Practical IT Security
==============================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
:Unterlagen: Moodle
:Version: 23SEB (Rev 2026-01-30)



Teaser
---------

.. deck::

  .. card::

    In dieser Veranstaltung werden wir uns mit den Grundlagen praktischer Angriffe und Verteidigungsmaßnahmen im Bereich IT Security auseinandersetzen. Wir werden uns der Frage widmen wie, wann und in welcher Form man - auf den ersten Blick abgesicherte Systeme/geschützte Daten - einerseits angreifen (:eng:`to exploit`) kann und wie man die Sicherheit weiter erhöhen kann.

    Wir werden uns in diesem und dem nächsten Semester ausgewählten Themen widmen, die von der Sicherheit von Passwörtern und Angriffen auf selbige bis hin zu der Sicherheit von Netzwerken und Webanwendungen reichen. 

    :peripheral:`Wir werden uns somit auf praktische Aspekte der IT Sicherheit fokussieren. Theoretische Grundlagen sind nur in so weit relevant, dass sie in der Praxis eine Relevanz haben.`

  .. card::

      .. hint::

        Für diese Veranstaltung sind Grundkenntnisse in Linux/Unix/MacOS hilfreich aber nicht notwendig. Notwendig ist aber Interesse an tiefer gehenden technischen Details. 
        
        Es wird  empfohlen sich eine virtuelle Maschine (z. B. mit VirtualBox) aufzusetzen, auf der Sie Linux (z. B. Kali Linux) installieren. Ggf. können Sie auch WSL2 unter Windows verwenden. Mac User können viele der Tools direkt installieren oder über Homebrew bzw. MacPorts beziehen.



Grober Ablauf
--------------

- 1. Semester: 6 Termine; (50% der Endnote)
- 2. Semester: 5 Termine; (50% der Endnote)

.. container:: incremental

  .. rubric:: Genereller Ablauf

  1. Einführung in ein für die IT-Sicherheit relevantes Thema mit praktischer Ausrichtung.
  2. Studentische `Präsentationen <../allg-vortraege/folien.de.rst.html>`__ zur Vertiefung bzw. Betrachtung wichtiger Aspekte.
  3. Aufgaben für die entsprechende Fragestellung.
  4. Abgaben, die genau beschreiben wie Sie die Aufgaben gelöst haben. Darüber sammeln Sie Teilnoten, die am Ende verrechnet werden. D. h. es gibt Teilnoten pro Aufgabe. Die Punkte sind zwischen den Aufgaben nicht untereinander vergleichbar.



Inhalte
------------

.. hint::

  Diese Veranstaltung ist ergänzend zur Veranstaltung SE III IT-Sicherheit zu sehen. D. h. Inhalte, die dort vermittelt werden, werden hier nicht noch einmal behandelt, sind aber potentiell relevant.

1. Semester

   1. Passwortwiederherstellung (:eng:`Password Recovery`)
   2. Reverse Engineering 101

2. Semester

   Pentesting von Webanwendungen



.. class:: new-section fade-out

Erstes Semester: Passwortwiederherstellung und Reverse Engineering
----------------------------------------------------------------------


.. class:: fade-out

Was passiert wann im 1. Semester...
--------------------------------------

.. story::

    .. class:: incremental-list

    :19. Nov. 2025:

      - Einführung 
      - Themenvergabe für die Fachpräsentationen - Notenanteil: 15%

      Die folgenden Themen werden ggf. am 01.12.2025 bzw. 15.12.2025 weiter vorgestellt:

      - Einführung in Passwortwiederherstellung (Linux Shell und Reguläre Ausdrücke)
      - Einführung in Reverse Engineering 

    :24. November 2025 (Online):

        Unterstützung bei der Erstellung der Präsentationen (*Optional*)

        BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa

    :01. Dezember 2025:

      - **Präsentationen (Schwerpunkt: Themen mit Bezug Passwortwiederherstellung)** 
       
      - Ausgabe der Übung bzgl. Passwortwiederherstellung (:eng:`Password Recovery`) - Notenanteil: 15%

    :15. Dezember 2025:

      - **Präsentationen (Schwerpunkt: Themen mit Bezug Reverse Engineering)** 

      - Ausgabe der Übung bzg. Reverse Engineering - Notenanteil: 15%

    :12. Januar 2026 (Online):

      Unterstützung bei der Bearbeitung der Aufgaben (*Optional*)

      BBB: https://bbb.dhbw.de/rooms/eic-dx8-r7g-ioa

    :21. Januar 2026 (Ereignis):

      **Abgabe der Lösungen für alle Aufgaben als PDF Dokument über Moodle - Deadline 21.1.2026, 12:00 Uhr**

    :22. Januar 2026 (Ereignis):

      Zuteilung wer welchen Teil präsentiert über Moodle/Mail. Sollten Sie am 22.01. bis 19:00 Uhr weder eine Nachricht in Moodle noch eine E-Mail von mir erhalten haben, dann *melden Sie sich bitte umgehend* bei mir.

    :26. Januar 2026:

      **Abschlusspräsentationen - Notenanteil: 5%**

      Die Präsentationsdauer pro Person ist 5 Minuten und soll die Vorgehensweise zur Lösung der entsprechenden Aufgabe präsentieren. D. h. die Präsentation kann auch eine „Live-Demo“ sein, die zeigt wie die Aufgabe gelöst wurde.

      `Erste Tips zur Gestaltung von Vorträgen finden Sie hier. <https://delors.github.io/allg-vortraege/folien.de.rst.html>`__

      Sollten Sie eine Live-Demo machen, dann zeichnen Sie Ihren Probelauf auf (5 Minuten) und laden Sie diesen als Zip-Datei hoch. Alternativ können Sie Ihr Video auch in Youtube stellen oder per OneDrive, Dropbox, ... zur Verfügung stellen. In diesem Falle laden Sie eine Textdatei mit der URL zum Video hoch! Das Video soll mindestens 3 Monate verfügbar sein.



.. class:: fade-out

Notenzusammenstellung - 1. Semester 
-------------------------------------

.. csv-table::
   :class: incremental-table-rows booktabs table-header-align-left
   :width: 100%
   :header: "Leistungsnachweis", "Notenanteil"

   "Fachpräsentation (10 Minuten pro Person)", "15%"
   "Lösungen der Aufgaben bzg. Passwortwiederherstellung", "15%"
   "Lösungen der Aufgaben bzgl. Reverse Engineering", "15%"
   "Lösungspräsentationen (5 Minuten pro Person)", "5%"



.. class:: fade-out

Fachpräsentationen
--------------------

.. story::

  .. rubric:: Vortragsthemen 1.12.2025

  .. class:: incremental-list list-with-explanations

  - scrpyt, bcrypt und Argon2: moderne Passwort Hashing Algorithmen - Vorstellung und Shout-out

    (3 Personen)
  - John the Ripper: Passwort Cracking Tool - Einführung und Demonstration

    (1 Person)
  - WIFI Passwortsicherheit: Hacking WEP und WPA2 mit Aircrack-ng - Einführung und Demonstration; WPA3: `Besprechung von Dragonblood <https://wpa3.mathyvanhoef.com>`__

    (2 Personen)
  - Wireshark: Einführung in die Konzepte und praktische Anwendung (Kali Linux, Mac OS, Windows) - Anwendung bei verschlüsselten Protokollen (TLS, HTTPS, ...)

    (3 Personen)

  - YSoSerial: Java Deserialization Exploits (aka Serialization gadgets) - Einführung in das Thema und Demonstration/Besprechung von Beispielen

    (2 Person)

  - Fuzzy Hashing und Yara: Malware Detection/Description

    (1 Person)
    
  .. rubric:: Vortragsthemen 15.12.2025

  .. class:: incremental-list list-with-explanations

  - Aufbau von APKs (Android Apps) und der grobe Aufbau des Dex File Formats

    (2 Person)
  - DexGuard und Proguard: Obfuscator für Android und Java Anwendungen - Einführung und Demonstration (z.B. mit Hilfe von JadX, CFR/JD-GUI)

    (1 Personen)

  - Einsatz von FRIDA zur dynamischen Instrumentierung von Android und iOS Anwendungen

    (1 Personen)

  - xdbg64 (Windows): Hacking Applications mit dem x64 Debugger - Einführung und Demonstration

    (1 Person)
  
  .. class:: incremental-list

  - Native Binary Reverse Engineering

    (Bitte Gegenseitig absprechen, um Überschneidungen zu vermeiden!)

    .. class:: list-with-explanations

    - Assembler: x86 oder ARM Assembler Grundlagen für Reverse Engineering

      (2 Personen)
    - *Function Call Semantics* in Binaries und *Syscalls*

      (1 Person)
    - Virtuelle Methodenaufrufe und deren Umsetzung in Assembler

      (1 Person)
    - Sicherheitsmechanismen in Native-Binaries: insbesondere Stack canaries, Position-independent Code & Address Space Layout Randomization

      (1 Person)
    - Ghidra: Binary-Reverse-Engineering - kurze Einführung und Demonstration

      (2 Personen)



.. class:: fade-out

Themenvergabe
--------------------

.. csv-table::
    :class:  booktabs table-header-align-left s-font-size-90
    :width: 100%
    :widths: 50,50
    :header: "Thema", "Personen"
  
    "scrypt, bcrypt und Argon2", "3"
    John the Ripper, "1"
    WIFI Passwortsicherheit, "2"
    Wireshark, "3"
    YSoSerial, "2"
    Fuzzy Hashing und Yara, "1"
    Aufbau von APKs und Dex, "2"
    DexGuard und Proguard, "1"
    FRIDA, "1"
    xdbg64, "1"
    Assembler Einführung, "2"
    *Function Call Semantics*, "1"
    Virtuelle Methodenaufrufe, "1"
    Sicherheitsmechanismen in Native-Binaries, "1"
    Ghidra, "2"



.. class:: fade-out

Bewertete Abgaben
--------------------

#. `Passwortwiederherstellung - Aufgaben <https://delors.github.io/DHBW-W3WI_SE411-Forschungsseminar_Informatik-Adv._Practical_IT_Security/passwort_wiederherstellung.de.rst.html>`__

#. `Reverse Engineering - Aufgaben <https://delors.github.io/DHBW-W3WI_SE411-Forschungsseminar_Informatik-Adv._Practical_IT_Security/reverse_engineering.de.rst.html>`__



.. class:: new-section

Zweites Semester: Pentesting von Webanwendungen
--------------------------------------------------



.. class:: no-title

Bewertungskriterien
-------------------------

.. attention:: 

  Geänderte Bewertungskriterien für alle Formen von Abgaben (Präsentationen, Dokumentationen):

  - Jede Aussage, die faktisch falsch ist, führt unmittelbar zu Punktabzug am Ende (und wird nicht ignoriert werden).
  - Jede Aussage, die blödsinnig ist, führt zu Punktabzug.
  - Jede Aussage, die überflüssig ist (*„Blah Blah“*) und nicht dem Thema der Aufgabe/Präsentation dient, führt zu Punktabzug.
  - Unnötige Aussagen, die bekannte Inhalte (zum Beispiel aus dieser oder anderen Vorlesungen) wiederholen, führen zu Punktabzug.
  - Jede Aussage, die nicht durch eine Quelle oder durch eigene Experimente belegt werden kann, führt zu Punktabzug.
  - Qualifizierende Attribute („schneller“, „besser“, „schwerer“ ...) müssen durch konkrete Metriken belegt bzw. definiert werden, ansonsten führen sie zu Punktabzug.


  
Ablauf
--------------------------------------

.. story::

  .. compound::

    .. rubric:: 20.05.2026

    - Ausgabe der Themen zur Bearbeitung
    - Kurze Einführung in das Thema Pentesting

  .. compound::
    :class: incremental

    .. rubric:: 01.06.2026

    - Bearbeitung der Themen mit dem Ziel „Hands-on“
    - Ich stehe für Rückfragen *online* (https://bbb.dhbw.de/rooms/eic-uue-f52-6xb/join) zur Verfügung

  .. compound::
    :class: incremental

    .. rubric:: 05.06.2026 (Ereignis)

    - Abgabe der Vorträge (Details siehe Moodle)

  .. compound::
    :class: incremental

    .. rubric:: 08.06.2026

    - Halten der Präsentationen
    - Vergabe der Aufgabe für das Pentesting
      
  .. compound::
    :class: incremental

    .. rubric:: 15.06.2026

    - Durchführung des Pentesting
    - Ich stehe für Rückfragen *online* (https://bbb.dhbw.de/rooms/eic-uue-f52-6xb/join) zur Verfügung

  .. compound::
    :class: incremental

    .. rubric:: 30.06.2026 (Ereignis)

    - Abgabe der Vorträge (Details siehe Moodle)

  .. compound::
    :class: incremental

    .. rubric:: 02.07.2026

    - Vorstellung der mittels Pentesting gefundenen Lücken

  .. compound::
    :class: incremental

    .. rubric:: 24.07.2026 (Ereignis)

    - Abgabe der Dokumentation der Ergebnisse des Pentesting (Moodle)



Vortragsthemen - TODO 
-----------------------

**Dauer pro Person: 10 Minuten**



.. class:: list-with-explanations smaller

- `Nmap (und ncat) <https://nmap.org/>`__  

  (Network discovery and security auditing.)

- `Zed Attack Proxy (ZAP) <https://www.zaproxy.org/>`__

  (Wep App Scanner)
- `Burp Suite inkl. Dastardly <https://portswigger.net/burp/communitydownload>`__  

  (Penetration testing toolkit.)
- `Metasploit <https://www.metasploit.com/>`__ 

  (Penetration testing framework.)

- Shodan und recon ng




.. weiteres Thema:  https://www.openvas.org/index-de.html
                    https://sqlmap.org
                    - `Nikto <https://cirt.net/Nikto2>`__ (1 Person)
                   (Web server scanner.)
                   - `Scapy <https://scapy.net/>`__ (1 Person)
                  (Interactive packet manipulation library)



Bewertungskriterien
-------------------------------------------

.. deck::

  .. card::

    .. rubric:: Für die Präsentation Ihres Fachvortrags

    - `Hinweise für Präsentationen <../allg-vortraege/folien.de.rst.html>`__
    - Notenanteil: 20% von der Gesamtnote

  .. card::

    .. rubric:: Für die Präsentation der Pentesting-Ergenbnisse

    - `Hinweise für Präsentationen <../allg-vortraege/folien.de.rst.html>`__
    - Anzahl der gefundenen Schwachstellen
    - Beantwortung von Fragen

    Notenanteil: 20% von der Gesamtnote

  .. card::

    .. rubric:: für die Dokumentation

    - `Hinweise für Ausarbeitungen <../allg-ausarbeitungen/folien.de.rst.html>`__
    - Ist die Bewertung der Lücken nachvollziehbar

    Notenanteil: 10% von der Gesamtnote
