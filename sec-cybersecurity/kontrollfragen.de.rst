.. meta::
   :lang: de
   :author: Michael Eichberg
   :keywords: "Sicherheitsprinzipien, Kontrollfragen"
   :description lang=de: Kontrollfragen bzgl. klassischer Sicherheitsprinzipien
   :id: lecture-klassische-Sicherheitsprinzipien-Kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Cybersecurity - Kontrollfragen
================================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1


.. class:: new-section

Allgemeines
------------------



.. class:: exercises

Klassenraumübung
------------------

.. story::

    Die folgenden Fragen sind *exemplarisch* für Fragen wie sie in einer Prüfung vorkommen könnten.

    .. exercise:: Welches sind die drei primären Schutzziele der IT Sicherheit

        .. solution::
            :pwd: CIA-kurz-und-KnapP

            CIA - Confidentiality, Integrity and Availability


    .. exercise:: Wovor müssen IT Systeme geschützt werden?
        :class: incremental

        .. solution::
            :pwd: Fragen-Antworten

            Zum Beispiel:

            - Ausfall

            - Missbrauch

            - Sabotage

            - Spionage

            - Betrug und Diebstahl

    .. exercise:: Beschreiben Sie kurz was die Mean Time to Contain bei Sicherheitsvorfällen besagt?
        :formatted-title: Beschreiben Sie kurz was die *Mean Time to Contain* bei Sicherheitsvorfällen besagt?
        :class: incremental

        .. solution::
            :pwd: MTTC

            Die mittlere Zeit, die benötigt wird, um einen Cyberangriff einzudämmen. D. h. die Zeit, die benötigt wird, um zu verhindern, dass sich der Angriff weiter ausbreitet.
            MTTC = MTTD (Detect) + MTTI (Identify) + MTTR (Respond)

    .. exercise:: Benennen Sie Arten/Kategorien von Angriffen?
        :class: incremental

        .. solution::
            :pwd: DaGibtEsGanzViele

            - Backdoors (Hintertüren)
            - (Distributed-)Denial-of-service Angriffe
            - Direct-access Angriffe (d. h. physischer Angriff auf das System)
            - Eavesdropping (Abhören)
            - Malware
            - Person-in-the-middle Angriffe
            - Privilege escalation (unterschieden werden: horizontale und vertikale)
            - Side-Channel attacks (Seitenkanalangriffe)
            - Spoofing (z. B. IP-Spoofing) (Vortäuschen)
            - Social-Engineering (z. B. Phishing)
            - Advanced Persistent Threats (APT)
            - Store-now, Decrypt-later (Speichere jetzt, Entschlüssele später)

    .. exercise:: Beschreiben Sie was ein Seitenkanalangriff ist und geben Sie ein Beispiel.
        :class: incremental

        .. solution::
            :pwd: VonHintenDurchsKnie

            Es werden Informationen aus dem Verhalten des Systems (Stromverbrauch, Zeit, ...) abgeleitet.

    .. exercise:: Was ist OSInt?
        :class: incremental

        .. solution::
            :pwd: Open-Source-Intelligence

            Open-Source-Intelligence

    .. exercise:: Bennen Sie Gefahren für die IT Sicherheit, die vom Einsatz von KI ausgehen können.
        :class: incremental

        .. solution::
            :pwd: Vielfaeltig!!!

            Das geht von KI generiertem Code mit Sicherheitslücken, über Code der Angriffe durchführt bis hin zu verbesserten Phishing Tools etc.

    .. exercise:: Beschreiben Sie ein realistisches Szenario für Quishing/QR phising?
        :class: incremental

        .. solution::
            :pwd: Parkautomaten

            Überkleben oder anbringen an Parkautomaten für das Bezahlen mit dem Handy.

    .. exercise:: Welchem Zweck dienen Bug-Bounty Programme?
        :class: incremental

        .. solution::
            :pwd: WhiteHatHacker

            Sie sollen Sicherheitsspezialisten einen Anreiz geben Lücken (zuerst) dem Hersteller zu melden.

    .. exercise:: Ist immer ein Timing-Angriff möglich bei nicht-konstantem Zeitverhalten?
        :class: incremental
        
        Sie haben folgende Situation: Ein Client übermittelt das Passwort in gehashter Form (:math:`H_1`) an den Server. Der Server hasht den Hash noch einmal in Verbindung mit einem Salt :math:`H_2 = \text{hash}(H_1 \parallel SALT)`. Danach vergleicht er den berechneten Wert :math:`H_2` mit dem äquivalent konstruierten, gespeicherten Hash :math:`H_{2_{\text{gespeichert}}}`. Ist in diesem Szenario eine Timing-Angriff möglich?

        .. solution::
            :pwd: NeinKeinTimingAngriff

            **Nein.**

            Unter diesen Annahmen kann der Angreifer nicht systematisch :math:`H_1` so verändern, dass H2 sich schrittweise einem Ziel‑:math:`H_2` nähert. Weil :math:`H_{\text{server}}(H_1\!\parallel\!\text{salt})` sich (für den Angreifer ohne Salt) wie eine zufällige Abbildung verhält, haben kleine, gezielte Änderungen an :math:`H_1` keine kontrollierbare, deterministische Wirkung auf Präfixe von :math:`H_2`. Ein Versuch, über Timing‑Signale ein immer längeres gemeinsames Präfix von :math:`H_2` zu erzwingen, erfordert im Erwartungsfall exponentiell viele Versuche (die Erfolgswahrscheinlichkeit für ein zusätzliches korrekte Byte ist :math:`2^{-8}` pro Byte), also praktisch nicht machbar.

            .. attention::

                Praktische Umsetzung sollten jedoch immer gegen Timing-Angriffe gehärtet werden!


.. class:: new-section

NIS 2
------------------



.. class:: exercises

Klassenraumübung
------------------

.. exercise:: Wer ist betroffen von NIS2?

    .. solution::
        :pwd: viele...hier-undDa

        Von NIS2 betroffene öff. und priv. Einrichtungen sind Organisationen mit mehr als 50 Mitarbeitern und einem Umsatz von mehr als 10 Millionen Euro(obligatorisch), die einer der "kritischen" Kategorien zugehörig sind.
