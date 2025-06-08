.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "IT Sicherheit", "Pentesting"
    :description lang=de: Fortgeschrittene Angewandte IT Sicherheit - Pentesting
    :id: 2024_08-w3wi_se411-forschungsseminar_informatik-adv._practical_it_security
    :first-slide: last-viewed

.. include:: ../docutils.defs



Forschungsseminar Informatik / Advanced Practical IT Security
=================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
:Version: 22SEA (2. Semester)
:Modul: *W3WI_SE411*
:Unterlagen: Moodle



Teaser
---------

.. deck::

  .. card::

    In dieser Veranstaltung werden wir uns mit den Grundlagen praktischer Angriffe und Verteidigungsmaßnahmen im Bereich IT Security auseinandersetzen. Wir werden uns der Frage widmen wie, wann und in welcher Form man - auf den ersten Blick abgesicherte Systeme - einerseits angreifen (:eng:`to exploit`) kann und wie man die Sicherheit weiter erhöhen kann.

    Wir werden uns in diesem und dem nächsten Semester ausgewählten Themen widmen, die von der Sicherheit von Passwörtern und Angriffen auf selbige bis hin zu der Sicherheit von Netzwerken und Webanwendungen reichen. Wir werden uns dabei insbesondere an gängigen Angriffsszenarien orientieren.

    :peripheral:`Wir werden uns somit auf praktische Aspekte der IT Sicherheit fokussieren. Das Kennenlernen der theoretischen Grundlagen von Verschlüsselungsalgorithmen, Hashalgorithmen, Zertifikaten und Vergleichbarem ist nicht primärer Fokus dieser Veranstaltung.`

  .. card::

      .. hint::

        Für diese Veranstaltung sind Grundkenntnisse in Linux hilfreich aber nicht notwendig. Notwendig ist aber Interesse an tiefergehenden technischen Details.



Grober Ablauf
--------------

- 1. Semester: 6 Termine; (50% der Note)
- 2. Semester  5 Termine; (50% der Note)

.. container:: incremental margin-top-1em

  .. rubric:: Genereller Ablauf

  1. Einführung in ein für die IT-Sicherheit relevantes Thema in Hinblick auf praktische Anwendung
  2. Aufgaben, die jeder für sich lösen muss/soll
  3. Abgabe, die genau beschreibt wie Sie die Aufgabe gelöst haben. Darüber sammeln Sie Teilnoten, die am Ende verrechnet werden. D. h. es gibt Teilnoten pro Aufgabe. Die Punkte sind zwischen den Aufgaben nicht untereinander vergleichbar.
  4. Jeder muss präsentieren.



Inhalte
------------

.. hint::

  Diese Veranstaltung ist ergänzend zur Veranstaltung SE III IT-Sicherheit zu sehen. D. h. Inhalte die dort vermittelt werden, werden hier nicht noch einmal behandelt, sind aber potentiell relevant.

1. Semester

   1. Passwortwiederherstellung (:eng:`Password Recovery`)
   2. Reverse Engineering 101

2. Semester

   Pentesting von Webanwendungen



.. class:: fade-out

Was passiert wann im 1. Semester...
--------------------------------------

.. story::

  .. container::

    :26. Aug 2024:
      .. rubric:: Einführung & Passwortwiederherstellung (Teil 1)

      (Ggf. Linux Shell und Reguläre Ausdrücke.)

    :9. Sep 2024:

      .. rubric:: Passwortwiederherstellung (Teil 2)

      Ausgabe der Übung - Notenanteil: 15%

  .. container:: line-above incremental

    :23. Sep 2024:

      .. rubric:: Einführung in Reverse Engineering (und Netzwerkanalyse)

      Ausgabe der Übung - Notenanteil: 20%

  .. container:: line-above incremental dhbw-gray

    :7. Oct 2024:

      Online (*Optional* - Unterstützung bei der Bearbeitung der Aufgaben)

      BBB: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd

  .. container:: line-above incremental dhbw-gray

    :21. Oct 2024: Online (*Optional* - Unterstützung bei der Bearbeitung der Aufgaben)

      BBB: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd

  .. container:: line-above  incremental

    :28. Okt. 2024:

      **Abgabe der Lösungen für alle Aufgaben als PDF Dokument (Moodle)**

      *(Ich werde am 29. zuteilen wer welchen Teil präsentiert; bitte schauen Sie in Moodle. Sollten Sie am 30. Okt. bis 22:00 Uhr weder eine Nachricht in Moodle noch eine E-Mail von mir erhalten haben, dann melden Sie sich bitte umgehend bei mir.)*

  .. container:: line-above  incremental

    :4. Nov 2024:

      .. rubric:: Abschlusspräsentationen

      Die Präsentationsdauer ist am Inhalt zu orientieren; darf max. 30 min pro Person jedoch nicht überschreiten. Jeder soll in der Lage sein alle Schritte nachvollziehen zu können. D. h. die Präsentation kann auch eine „Live-Demo“ sein, die
      zeigt wie die Aufgabe gelöst wurde.

      Die Präsentation ist bis zum 3. Nov. 2024 23:59 Uhr in Moodle hochzuladen. Sollten Sie eine Live-Demo machen, dann zeichnen Sie Ihren Probelauf auf und laden Sie diesen als Zip-Datei hoch. Alternativ können Sie Ihre Video auch in Youtube stellen oder per OneDrive, Dropbox, ... zur Verfügung stellen. In diesem Falle laden Sie eine Textdatei mit der URL zum Video hoch! Nutzen Sie nicht Moodle für die Videos, da diese häufig Probleme bereitet!

      `Erste Tips zur Gestaltung von Vorträgen finden Sie hier. <https://delors.github.io/allg-vortraege/folien.de.rst.html>`__

      Notenanteil: 15%



Was passiert wann im 2. Semester...
--------------------------------------

.. story::

  .. compound::

    .. rubric:: 19. Feb 2025

    - Ausgabe der Themen zur Bearbeitung
    - Kurze Einführung in das Thema Pentesting.

  .. compound::
    :class: incremental

    .. rubric:: 24. Feb 2025

    **Bearbeitung der Themen** mit dem Ziel „Hands-on“; bei Bedarf stehe ich für Rückfragen *online* zur Verfügung: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd.


  .. compound::
    :class: incremental

    .. rubric:: 12. Mar 2025

    - Halten der Präsentationen - Notenanteil: 20%
    - Vergabe der Aufgabe für das Pentesting

    .. attention::
      :class: margin-bottom-1em

      Die Vorträge müssen am Abend vorher hochgeladen sein.

  .. compound::
    :class: incremental

    .. rubric:: 31. Mar 2025

    **Durchführung des Pentesting**; bei Bedarf stehe ich für Rückfragen *online* zur Verfügung: https://bbb.dhbw.de/mannheim/eic-mn5-hvh-7qd.


  .. compound::
    :class: incremental

    .. rubric:: 23. Apr 2025

    Vorstellung der mittels Pentesting gefundenen Lücken - Notenanteil: 20%

    .. attention::
      :class: margin-bottom-1em

      Die Vorträge müssen am Abend vorher hochgeladen sein.

  .. compound::
    :class: incremental

    .. rubric:: Ende des Semesters

    Abgabe der Dokumentation der Ergebnisse des Pentesting inkl. Bewertung als PDF Dokument (Moodle) - Notenanteil: 10%



Vortragsthemen
--------------------

.. class:: list-with-explanations smaller

- `Nmap (und ncat) <https://nmap.org/>`__  (1 Person)

  (Network discovery and security auditing.)

- `Zed Attack Proxy (ZAP) <https://www.zaproxy.org/>`__ (1 Person)

  (Wep App Scanner)
- `Burp Suite inkl. Dastardly <https://portswigger.net/burp/communitydownload>`__  (1 Person)

  (Penetration testing toolkit.)
- `Metasploit <https://www.metasploit.com/>`__ (2 Personen)

  (Penetration testing framework.)

**Dauer pro Person: 25 Minuten**

.. weiteres Thema:  https://www.openvas.org/index-de.html
                    https://github.com/GoVanguard/legion
                    https://sqlmap.org
                    `Sonarqube (Community Edition) <https://www.sonarsource.com/products/sonarqube/downloads/>`__ (1 Person)
                    (Code quality tool.)
                    - `Nikto <https://cirt.net/Nikto2>`__ (1 Person)
                   (Web server scanner.)
                    - `Nmap (und ncat) <https://nmap.org/>`__  (1 Person)
                   (Network discovery and security auditing.)
                   - `Scapy <https://scapy.net/>`__ (1 Person)
                  (Interactive packet manipulation library)

Bewertungskriterien
-------------------------------------------

.. deck::

  .. card::

    .. rubric:: Für die Präsentationen

    - Vermittelt die Präsentation einen guten ersten Einblick in das Tool (Fähigkeiten und Grenzen)
    - Qualität der (Live-)Demonstration (und ggf. des Backups)

      (ggf. ist das Aufsetzen einer (kleinen) virtuellen Maschine sinnvoll/notwendig.)
    - Reduktion auf das Wesentliche
    - Qualität der Beantwortung von Fragen
    - Persönliches Auftreten
    - Einhaltung der Dauer der Präsentation

    Notenanteil: 20% (max. 20 Punkte von 100 Punkten)

  .. card::

    .. rubric:: für das Pentesting

    - Anzahl der gefundenen Schwachstellen
    - Qualität der Präsentation der Schwachstellen
    - Beantwortung von Fragen

    Notenanteil: 25% (max. 25 Punkte von 100 Punkten)

  .. card::

    .. rubric:: für die Dokumentation

    - Qualität der Dokumentation (leserlich, strukturiert, frei von Tippfehlern, ...)
    - Ist die Einschätzung der Lücken nachvollziehbar

    Notenanteil: 5% (max 5. Punkte von 100 Punkten)
