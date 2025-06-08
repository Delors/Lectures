.. meta::
   :version: renaissance
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
:Version: 1.0


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

            Die mittlere Zeit, die benötigt wird, um einen Cyberangriff einzudämmen. D. h. die Zeit, die benötigt wird, um zu verhindern, dass sich der Angriff weiter ausbreitet.
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
            - Man-in-the-middle (MITM) Angriffe
            - Privilege escalation (unterschieden werden: horizontale und vertikale)
            - Side-Channel attacks (Seitenkanalangriffe)
            - Spoofing (z. B. IP-Spoofing) (Vortäuschen)
            - Social engineering (z. B. Phishing)
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
