.. meta:: 
    :author: Michael Eichberg
    :keywords: "IT Sicherheit", "Reverse Engineering"
    :description lang=de: Fortgeschrittene Angewandte IT Sicherheit
    :id: lecture-security-java_reverse_engineering
    :first-slide: last-viewed
    :exercises-master-password: WirklichSchwierig!    

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
.. role:: red
.. role:: green

.. role:: raw-html(raw)
   :format: html



.. class:: animated-symbol organic-red

Cybersecurity 
=====================================================

.. container::

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
    :Kontakt: michael.eichberg@dhbw.de
    :Version: 2.5

.. supplemental::

  :Folien: 
      |html-source|

      |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues



Was ist Cybersecurity?
-----------------------

.. stack::

    .. layer:: incremental

        .. epigraph::

            **Cybersecurity is the practice of protecting systems, networks, and programs from digital attacks**. These cyberattacks are usually aimed at accessing, changing, or destroying sensitive information; extorting money from users via ransomware; or interrupting normal business processes.

            -- July 4th, 2024 - `Cisco <https://www.cisco.com/c/en/us/products/security/what-is-cybersecurity.html>`__

    .. layer:: incremental

        .. epigraph::

            [...] The security precautions related to computer information and access address four major threats: **(1) theft of data**, such as that of military secrets from government computers; **(2) vandalism**, including the destruction of data by a computer virus; **(3) fraud**, such as employees at a bank channeling funds into their own accounts; and **(4) invasion of privacy**, such as the illegal accessing of protected personal financial or medical data from a large database. [...]

            -- July 4th, 2024 - `Britannica <https://www.britannica.com/technology/computer-security>`__

    .. layer:: incremental

        .. epigraph::
            
            **VERORDNUNG (EU) 2019/881 DES EUROPÄISCHEN PARLAMENTS UND DES RATES vom 17. April 2019 über die ENISA (Agentur der Europäischen Union für Cybersicherheit**

            *Artikel 2 Nummer 1* 

            „Cybersicherheit“ bezeichnet alle Tätigkeiten, die notwendig sind, um Netz- und Informationssysteme, die Nutzer solcher Systeme und andere von Cyberbedrohungen betroffene Personen zu schützen [...]

            -- `Verordnung (EU) 2019/881 <https://eur-lex.europa.eu/legal-content/DE/TXT/PDF/?uri=CELEX:32019R0881>`__

    .. layer:: incremental

        Das Ziel der IT-Sicherheit ist es Systeme vor:

        - Ausfall
        - Missbrauch
        - Sabotage
        - Spionage
        - Betrug und Diebstahl zu schützen



.. class:: new-section

Von praktischen und theoretischen Angriffen
-------------------------------------------------------------



.. class:: no-title center-child-elements

Indonesia - Ransomware Angriff
-------------------------------------------------------------

.. epigraph::

    **Indonesia won’t pay an $8 million ransom after a cyberattack compromised its national data center**

    [...] The attackers have held data hostage and offered a key for access in return for the $8 million ransom, said PT Telkom Indonesia’s director of network & IT solutions, Herlan Wijanarko, without giving further details. Wijanarko said the company, in collaboration with authorities at home and abroad, is investigating and trying to break the encryption that made data inaccessible. [...]

    -- June 25th, 2024 - `AP News <https://apnews.com/article/indonesia-ransomware-attack-national-data-center-213c14c6cc69d7b66815e58478f64cee>`__



.. class:: no-title center-child-elements

Crypto-Hackers Steal $2.2bn as North Koreans Dominate
-------------------------------------------------------------

.. epigraph:: 

    **Crypto-Hackers Steal $2.2bn as North Koreans Dominate**

    Threat actors stole $2.2bn from cryptocurrency platforms in 2024, with the majority (61%) of illicit funds attributed to North Korean hackers, according to Chainalysis. [...]

    Notably, attacks between $50 and $100m, and those above $100m, occurred far more frequently in 2024 than they did in 2023, suggesting that the DPRK is getting better and faster at massive exploits[...].

    This increase is unfortunately also being matched by “a growing density” of hacks which yielded lower amounts of around $10,000 in value.[...]

    Some of these events appear to be linked to North Korean IT workers, who have been increasingly infiltrating crypto and Web3 companies, and compromising their networks, operations, and integrity.

    -- 19.12.2024 - `Infosecurity Magazine <https://www.infosecurity-magazine.com/news/cryptohackers-steal-22bn-north/>`__



.. class:: no-title center-child-elements

Cyberangriff auf KritIs - Zentralheizung ausgefallen
----------------------------------------------------

.. epigraph::

    **Hackers shut down heating in Ukrainian city with malware**

    For two days in mid-January, some Ukrainians in the city of Lviv had to live without central heating and suffer freezing temperatures because of a cyberattack against a municipal energy company [...]
    
    [...], the cybersecurity company Dragos published a report with details about a new malware dubbed FrostyGoop, which the company says is designed to target industrial control systems [...]

    -- Juli 2024 - `Techcrunch <https://techcrunch.com/2024/07/23/hackers-shut-down-heating-in-ukrainian-city-with-malware-researchers-say/?guccounter=1>`__



.. class:: no-title center-child-elements

Cyberangriff auf KritIs - Telefonnetzwerke
----------------------------------------------------

.. epigraph::

    **US government tells officials, politicians to ditch regular calls and texts**

    The U.S. government [CISA] is urging senior government officials and politicians to ditch phone calls and text messages following intrusions at major American telecommunications companies blamed on Chinese hackers. [...]

    The first recommendation: "Use only end-to-end encrypted communications." [...]


    --18.12.2024 - `Reuters <https://www.reuters.com/world/us/us-cyber-watchdog-tells-senior-officials-immediately-adopt-end-to-end-encryption-2024-12-18/>`__    




.. class:: no-title center-child-elements

Cyberangriff auf Fahrräder
----------------------------

.. epigraph::

    **Want to Win a Bike Race? Hack Your Rival’s Wireless Shifters**

    Relatively inexpensive hardware can be used to hack the Shimano Di2 wireless gear-shifting systems used by cyclists [...]. They tested the eavesdrop-and-replay attack with a $1,500 USRP software-defined radio, an antenna, and a laptop but said the setup could be miniaturized. Attackers could spoof signals from up to 30 feet away, causing the target bike to shift gears unexpectedly or lock into the wrong gear. 

    --August 2024 - `summary provided by ACM <https://technews.acm.org/archives.cfm?fo=2024-08-aug/aug-16-2024.html>`__; `full article: Wired <https://www.wired.com/story/shimano-wireless-bicycle-shifter-jamming-replay-attacks/>`__



.. class:: no-title center-child-elements

Auslesen von Daten über die Luftschnittstelle
----------------------------------------------------------------


.. epigraph::

    **New RAMBO attack steals data using RAM in air-gapped computers**

    [...] A novel side-channel attack dubbed  "RAMBO" (Radiation of Air-gapped Memory Bus for Offense) generates electromagnetic radiation from a device's RAM to send data from air-gapped computers.

    .. container:: incremental

        [...] To conduct the Rambo attack, an attacker plants malware on the air-gapped computer to collect sensitive data and prepare it for transmission. It transmits the data by manipulating memory access patterns to generate controlled electromagnetic emissions from the device's RAM. 

    .. container:: incremental

        [...] The RAMBO attack achieves data transfer rates of up to 1,000 bits per second (bps) [at a distance of up to 7 meters], equating to 128 bytes per second, or 0.125 KB/s.

    --September 2024 - `Bleepingcomputer  <https://www.bleepingcomputer.com/news/security/new-rambo-attack-steals-data-using-ram-in-air-gapped-computers/>`__


.. supplemental::

    .. rubric:: Weitere Details

    .. epigraph::
    
        The emitted data is encoded into "1" and "0," represented in the radio signals as "on" and "off." The researchers opted for using Manchester code to enhance error detection and ensure signal synchronization, reducing the chances for incorrect interpretations at the receiver's end.
        
        The attacker may use a relatively inexpensive Software-Defined Radio (SDR) with an antenna to intercept the modulated electromagnetic emissions and convert them back into binary information.


.. class:: no-title center-child-elements

Snailload
-------------------------------------------------------------

.. epigraph::

    **SnailLoad: Exploiting Remote Network Latency Measurements without JavaScript**

    [Side-Channel Attack to circumvent privacy.]
    
    [...] The attack setup for SnailLoad. A victim downloads data from an attacker's HTTP server while it watches a video on a video-sharing platform, e.g., YouTube. Due to the network bottleneck on the victim's side, the attacker can infer the transmitted amount of data by measuring the packet round trip time. The round trip time traces are unique per video and can be used to classify the video watched by the victim. [...]

    -- 28.6.2024 Snailload: `Paper <https://www.snailload.com/snailload.pdf>`__, `Web <https://www.snailload.com>`__



.. class:: no-title center-child-elements

Side-Channel Attack
--------------------------

.. epigraph::

    **New PIXHELL Attack Exploits LCD Screen Noise to Exfiltrate Data from Air-Gapped Computers**

    A new side-channel attack dubbed PIXHELL could be abused to target air-gapped computers by breaching the "audio gap" and exfiltrating sensitive information by taking advantage of the noise generated by pixels on an LCD screen.

    Malware in the air-gap and audio-gap computers generates crafted pixel patterns that produce noise in the frequency range of 0 - 22 kHz," Dr. Mordechai Guri, the head of the Offensive Cyber Research Lab in the Department of Software and Information Systems Engineering at the Ben Gurion University of the Negev in Israel, said in a newly published paper. [...]

    -- 10. Sept. 2024 - `The Hacker News <https://thehackernews.com/2024/09/new-pixhell-attack-exploits-screen.html>`__



.. class:: no-title center-child-elements

Online-Betrug
-----------------

.. epigraph::

    **FAST 4.000 VERHAFTUNGEN: Interpol gelingt großer Schlag gegen Onlinebetrug**

    Die Einsatzkräfte haben nicht nur weltweit Tausende von Verdächtigen verhaftet, sondern auch Vermögenswerte im Umfang von 257 Millionen US-Dollar beschlagnahmt.

    [...] Mit einem Gesamtwert von 135 Millionen US-Dollar besteht laut Interpol mehr als die Hälfte davon aus beschlagnahmten Fiat-Währungen wie US-Dollar, Euro oder Yen. Weitere zwei Millionen Dollar liegen in Form von Kryptowährungen vor. Hinzu kommen andere Vermögenswerte wie etwa Immobilien, Luxusfahrzeuge, teurer Schmuck und andere hochwertige Gegenstände und Sammlungen im Gesamtwert von 120 Millionen US-Dollar. [...]

    --29. Juni 2024 - `Golem.de <https://www.golem.de/news/fast-4-000-verhaftungen-interpol-gelingt-grosser-schlag-gegen-onlinebetrug-2406-186568.html>`__



.. class:: no-title center-child-elements

Receiver mit DDoS Funktion
-------------------------------------------------------------

.. epigraph::

    *CEO VERHAFTET*

    Der Hersteller soll insgesamt 240.000 Geräte mit der DDoS-Funktion ausgestattet haben – teils ab Werk, teils erst nachträglich per Firmwareupdate.

    [...] In Südkorea sind fünf Mitarbeiter sowie der CEO eines Unternehmens verhaftet worden. Dieses soll Satellitenreceiver hergestellt und Hunderttausende davon auf Wunsch eines Kunden mit einer DDoS-Funktion ausgestattet haben. Wie [...] unter Verweis auf Angaben der südkoreanischen Polizei berichtet, lieferte der Hersteller 98.000 Geräte ab Werk mit dieser Funktion aus. [...] 

    Dass Geräte ab Werk mit Schadsoftware ausgeliefert werden, ist gerade im unteren Preissegment keine Seltenheit. Sicherheitsforscher deckten erst im vergangenen Jahr eine Malware-Kampagne auf, bei der vor allem billige Android-Geräte wie Smartphones, Tablets und TV-Boxen aus China vor ihrer Auslieferung an Endkunden mit einer Schadsoftware ausgestattet worden waren. 

    -- 3.12.2024 - `Golem.de <https://www.golem.de/news/ceo-verhaftet-satellitenreceiver-jahrelang-mit-ddos-funktion-ausgeliefert-2412-191354.html>`__




Cybersicherheit ist das Geschäftsrisiko Nr. 1
-------------------------------------------------------------


.. epigraph::

    **Cybervorfälle wie Ransomware-Angriffe, Datenschutzverletzungen und IT-Unterbrechungen sind laut dem Allianz Risk Barometer im Jahr 2024 die größte Sorge für Unternehmen weltweit.** An zweiter Stelle steht die eng miteinander verknüpfte Gefahr der Betriebsunterbrechung. [...]

    Cybervorfälle (36% der Gesamteinsätze) sind zum dritten Mal in Folge das weltweit gefürchtetste Risiko [...]. Eine Datenschutzverletzung wird von den Befragten des Allianz Risk Barometers (59%) als die besorgniserregendste Cyberbedrohung angesehen, gefolgt von Angriffen auf kritische Infrastrukturen und physische Vermögenswerte (53%). [...]

    Cyberkriminelle suchen vermehrt nach Möglichkeiten, neue Technologien wie generative künstliche Intelligenz (KI) zu nutzen, um Angriffe zu automatisieren und zu beschleunigen und so effektivere Malware und Phishing zu schaffen. [...]

    -- Jan. 24 - `Allianz Risk Barometer 2024 <https://www.allianz-trade.de/wissen/wirtschafts-news/allianz-risk-barometer-2024-geschaeftsrisiken.html?utm_source=google&utm_medium=cpc&utm_campaign=[generic][mm][de][de][leadgen][trade_credit_insurance][e][p][b][p-max]-eh_wkv_p-max&_bt=&_bk=&_bm=&_bn=x&_bg=&gad_source=1&gbraid=0AAAAAC_uaZo7sk9ZvZrwUYEuUPFQbJoY1&gclid=Cj0KCQiAvP-6BhDyARIsAJ3uv7aL2d0IlFz61--XDPzi50sJUl5JjeneJlk3ehYu3pTYgkyDz0C0_cMaAtzKEALw_wcB>`__




.. class:: new-section transition-fade

Angriffe auf die Schutzziele der IT-Sicherheit
-------------------------------------------------------------



Ausgewählte Angriffe, Angriffsmethoden und Bedrohungsszenarien
----------------------------------------------------------------

.. class:: incremental

- Backdoors (:ger:`Hintertüren`)
- (Distributed-)Denial-of-service Angriffe
- Direct-access Angriffe (d. h. physischer Angriff auf das System)
- Eavesdropping (:ger:`Abhören`)
- Malware
- Man-in-the-middle (MITM) Angriffe
- Privilege escalation (unterschieden werden: horizontale und vertikale)
- Side-Channel attacks/\ :ger:`Seitenkanalangriffe`
- Spoofing (z. B. IP-Spoofing)
- Social engineering (z. B. Phishing)
  
.. class:: incremental
    
- Advanced Persistent Threats (APT)
- *Store-now, Decrypt-later* (:ger:`Speichere jetzt, Entschlüssele später`)


.. supplemental::

    :Vertikale Privilege Escalation: Der Angreifer erhält Zugriff auf höhere Rechte, die er vorher nicht hatte.
    :Horizontale Privilege Escalation: Der Angreifer erhält Zugriff auf die Rechte einer anderen Person, die er vorher nicht hatte.
    :APT: Der Begriff *Advanced Persistent Threat* (≘ „fortgeschrittene, andauernde Bedrohung“) bezeichnet gezielte Cyberangriffe durch professionelle Gruppen (häufig *state sponsored*). Es werden in der Regel langfristige Ziele verfolgt. Diese dienen zum Beispiel der Spionage oder der Vorbereitung auf einen Cyberkrieg. Häufige Ziele sind Regierungen und Unternehmen sowie Organisationen, die über kritische Daten verfügen. Insbesondere in der Anfangsphase gehen die Angreifer sehr vorsichtig vor, um nicht entdeckt zu werden. Danach unterscheidet sich das Vorgehen je nach Zielsetzung. Häufig wird versucht den Zugriff auf das Zielsystem langfristig zu erhalten, um so an weitere Informationen zu gelangen.



Schutzziele der IT-Sicherheit: CIA-Triade
--------------------------------------------

.. raw:: html
    :class: center-child-elements

    <style>
    .cia-triangle {
        position: relative;
        width: 400px;
        height: 400px;
        overflow: visible;
        scale: 1.5;
        transform: translate(0, 50px);

        * {
            position: absolute;
        }

        .bottom-left {
            left: 0;
           background: linear-gradient(145.98deg, rgba(255,255,255) 50%, var(--dhbw-dark-red) 50%, var(--dhbw-dark-red) 100%);
        }
        .bottom-right {
            right: 0;
            background: linear-gradient(213.98deg, rgba(255,255,255) 50%, var(--dhbw-dark-red) 50%, var(--dhbw-dark-red) 100%);
        }
        .bottom-left,
        .bottom-right {
            width: 200px;
            height: 135px;
            z-index: 1;
            bottom: 0;
            right: 0;            
        }

        .left,
        .right {
            width: 200px;         
            height: 400px;
            z-index: 2;  
            mix-blend-mode: multiply;
        }

        .left {
            background: linear-gradient(116.57deg, rgba(255,255,255,1) 0%, rgba(255,255,255,1) 50%, var(--dhbw-light-red) 50%, var(--dhbw-light-red) 100%);
        }
    
        .right {
            right: 0;
            background: linear-gradient(243.43deg, rgba(255,255,255,1) 0%, rgba(255,255,255,1) 50%, var(--dhbw-red) 50%, var(--dhbw-red) 100%);
        }

        p {
            font-size: 0.75em;
        
            &.availability {
                bottom: -20%;
                left: 50%;
                transform: translate(-50%);
            }

            &.integrity {
                top: 50%;
                right: -20%;
                transform: translate(0, -50%);
            }

            &.confidentiality {
                top: 50%;
                left: -70%;
                transform: translate(0, -50%);
            }
        }
    }
    </style>

    <div class="cia-triangle incremental">
        <div class="bottom-left"></div>
        <div class="bottom-right"></div>
        <p class="availability">Availability</p>
        <div class="left incremental"><p class="confidentiality">Confidentiality</p></div>
        <div class="right incremental"><p class="integrity">Integrity</p></div>
    </div>

.. supplemental::

    Confidentiality ≘dt. Vertraulichkeit
    
    Integrity ≘dt. Integrität

    Availability ≘dt. Verfügbarkeit



Erweiterte Schutzziele
--------------------------------------------

Neben den primären Schutzzielen, gibt es eine Reihe weiterer kontextabhängiger Schutzziele:

.. class:: incremental

:Verbindlichkeit/Nichtabstreitbarkeit (`Accountability/Non-repudiation`:eng:):
    Ein Akteur kann seine Handlungen nicht abstreiten.

.. class:: incremental

:Pseudo-/Anonymisierung: Eine Person kann nicht (mehr) identifiziert werden.

.. class:: incremental

:Authentizität (`Authenticity`:eng:): Ist eine Information echt bzw. vertrauenswürdig?




.. class:: new-section transition-fade

Social-Engineering Angriffe
-------------------------------------------------------------



Eigenschaften von Social-Engineering Angriffe
-------------------------------------------------------------

.. class:: incremental with-explanations

- **sind häufig die Ursache für erfolgreiche Angriffe**

  (Der Hacker Kevin Mitnick war praktisch immer aufgrund von Social Engineering erfolgreich.)
- stellen die größte Bedrohung für die Sicherheit von IT-Systemen dar
- es wird angenommen, dass die betroffenen Personen es in vielen Fällen nicht merken :incremental:`(Beispiel: Fake Bewerbungsgespräch)`
- mittels OSINT kann die Vorbereitung von Social-Engineering Angriffen vereinfacht werden
- neue technische Möglichkeiten (z. B. KI generierte Stimmen) erweitern die Angriffsmöglichkeiten

.. supplemental::

    .. rubric:: Beispiel eines fortgeschrittenen Social-Engineering Angriffs

    Ein vom Angreifer bewusst eingefädeltes Bewerbungsgespräch für eine Position als Administrator könnte zum Beispiel dazu genutzt werden, um Informationen über das Zielsystem zu erhalten, die für einen Angriff nützlich sind (z. B. welche Software wird eingesetzt, wie sieht die Architektur aus, ...). In diesem Fall ist davon auszugehen, dass ein Bewerber zum Beispiel durch ein Headhunter eine gutes Angebot gemacht wird und er dann im Rahmen des Gesprächs gebeten wird eine Sicherheitsarchitektur darzustellen, die er einführen würde. Es ist dann davon auszugehen, dass er auf seine bisherige Erfahrung zurückgreift und diese darstellt und er somit die Architektur des Zielsystems offenlegt.

    .. rubric:: Neue Gefahren 

    Durch KI generierte Stimmen kann es Angreifern gelingen, z. B. durch das Vortäuschen einer Notlage einer nahestehenden Person, an Informationen zu gelangen.


**One Question Saved Ferrari from a Deepfake Scam**
-------------------------------------------------------------

.. epigraph::

    With one question, an executive at Ferrari stopped an effort to use deepfake technology to scam the company. CEO Benedetto Vigna (pictured) was impersonated on a call by deepfake software that, using a convincing imitation of Vigna's southern Italian accent, said he needed to discuss something confidential that required an unspecified currency-hedge transaction to be carried out. The executive started to have suspicions and asked, for identification purposes, the title of the book Vigna had recently recommended to him. With that, the call ended.

    -- Juli, 2024 - Zusammenfassung: `ACM <https://technews.acm.org/archives.cfm?fo=2024-07-jul/jul-29-2024.html>`__\ ; Original: `‘I Need to Identify You': How One Question Saved Ferrari From a Deepfake Scam - Bloomberg <https://www.bloomberg.com/news/articles/2024-07-26/ferrari-narrowly-dodges-deepfake-scam-simulating-deal-hungry-ceo>`__





Ausgewählte Social-Engineering Angriffe 
-------------------------------------------------------------

.. container:: scrollable

    .. class:: incremental

    :Phishing and Spear Phishing: 

        *Phishing* nutzt elektr. Kommunikationswege um an Informationen zu gelangen (z. B. E-Mail oder SMS). 
        
        *Spear phishing* ist Phishing, bei der der Angreifer auf eine bestimmte Zielgruppe oder sogar eine einzelne Person abzielt.

    .. class:: incremental

    :Smishing: 
    
        Phishing mit Hilfe von SMS.

    .. class:: incremental

    :Vishing:

        Phishing mit Hilfe von Telefonanrufen.
        
        .. incremental::

            (Z. B. `Anrufe von Europol <https://www.europol.europa.eu/publications-events/publications/vishing-calls>`__)

    .. class:: incremental

    :Quishing/QR phishing: 

        Phishing mit Hilfe von QR Codes. 

    .. class:: incremental

    :Whaling:

        Phishing, dass sich gegen hochrangige und sehr ausgewählte Personen richtet (z. B. den CEO eines Unternehmens).

    .. class:: incremental

    :Pharming:

        Manipulation des DNS-Servers, um den Nutzer auf eine gefälschte Webseite zu leiten, um dann sensitive Informationen zu erlangen.

    .. class:: incremental

    :Spam / Spam over Internet messaging (SPIM):

        Unerwünschte und nicht angeforderte E-Mail-Nachrichten oder Nachrichten in sozialen Medien bzw. Instant Messaging-Diensten.

    .. class:: incremental

    :Dumpster Diving:

        Durchsuchen von „Müllcontainern“ nach Informationen, die für einen Angriff nützlich sein könnten.

    .. class:: incremental

    :Shoulder Surfing:

        Beobachten von Personen, die sich an einem Computer anmelden, um das Passwort zu erfahren oder die sensitive Informationen auf dem Schreibtisch liegen haben.   

    .. class:: incremental

    :Tailgating:

        Ein Angreifer nutzt die Zugangsberechtigung einer Person, um sich Zugang zu einem Gebäude zu verschaffen ohne dass die Person dies bemerkt oder gar zustimmt. 
        
        Dies kann z. B. durch Zugangsschleusen verhindert werden, die immer nur einer Person den Zugang gewähren. 

    .. class:: incremental

    :Identity Fraud:

        Identitätsdiebstahl. Der Angreifer gibt sich als jemand anderes aus, um an Informationen zu gelangen oder um eine Straftat zu begehen.

    .. class:: incremental

    :Invoice Scams:

        Versenden von Rechnungen, für Dienstleistungen und Produkte die man nicht gekauft hat (z. B. Rechnungen für Postzustellung.)

    .. class:: incremental

    :Credential Harvesting:
    
        Sammlung von Zugangsdaten, die durch Sicherheitslücken in Systemen oder durch Phishing erlangt wurden. 

    .. class:: incremental

    :Hoax:
        Eine bewusste Falschmeldung, die Menschen dazu veranlasst etwas falsches zu glauben. 

    .. class:: incremental

    :Impersonation oder Pretexting: 
        Vorgabe einer falschen Identität (z. B. als Mitarbeiter des IT-Supports); d. h. der Angreifer gibt sich persönlich als jemand anderes aus, um an Informationen zu gelangen und nutzt dafür keine elektronischen Hilfsmittel.

    .. class:: incremental

    :Eavesdropping:
        Abhören von Gesprächen, um an relevante Informationen zu gelangen.   

    .. class:: incremental

    :Eliciting Information:
        Der Angreifer versucht durch geschicktes Fragen an Informationen zu gelangen, die für einen Angriff nützlich sein könnten.

    .. class:: incremental

    :Baiting (`Ködern`:ger:):
        Der Angreifer bietet etwas an, um an Informationen zu gelangen (z. B. ein USB-Stick mit einem Virus, der sich beim Einstecken des USB-Sticks auf dem Rechner installiert.)

    .. class:: incremental

    :Watering Hole Attack:
        Der Angreifer infiziert eine Webseite, die von der Zielgruppe häufig besucht wird, um dann die Besucher der Webseite anzugreifen.
    
    .. class:: incremental

    :Typo Squatting:
        Ausnutzen von Tippfehlern durch das Registrieren einer Domain, die der Domain eines Zielunternehmens ähnelt, um dann Besucher der Webseite auf eine gefälschte Webseite zu leiten. (z. B. `www.gooogle.com`)


.. supplemental::

    .. rubric:: Quishing/QR phishing: 

    D. h. der Angreifer erstellt einen QR Code, der auf eine gefälschte Webseite führt. Der QR Code wird dann z. B. auf einem Plakat angebracht oder zum Beispiel an einer Säule zum Kaufen von Fahrkarten, um möglichst viele Personen glaubhaft zu erreichen.

    .. rubric:: HOAX

    Ein Beispiel eines nicht-harmlosen Streichs (Hoax) ist die Falschmeldung vom 1. April 2003, dass Bill Gates gestorben sei. Diese Falschmeldung wurde von vielen Menschen geglaubt und hatte relevanten Einfluss auf den Aktienmarkt.

    .. rubric:: Credential harvesting

    In der Anfangszeit von Github und Bitbucket wurden häufig Zugangsdaten und Zertifikate in öffentlichen Repositories gefunden, da die Nutzer diese im Quellcode hinterlegt hatten oder sogar als Ressourcen direkt eingebunden hatten.

    .. rubric:: Impersonation

    .. epigraph::
    
        **U.S. charges 14 North Koreans in $88 million identity theft and extortion case**

        The Department of Justice accused 14 North Koreans of conspiring to use false identities to get IT jobs with U.S. companies and siphon money back to their home country.

        The indictment in Missouri federal court alleged that the conspiracy generated at least $88 million.

        The State Department said Thursday it is offering an up to $5 million reward for information about the conspirators and others associated with the two “North Korean front companies.”

        -- 12.12.2024 - `CNBC <https://www.cnbc.com/2024/12/12/north-koreans-charged-identity-theft-extortion-conspiracy-doj.html>`__


    .. rubric:: Typische Phishing E-Mail

    .. image:: images/phishing-mail-fake-fedex.png 
        :align: center
        :width: 80%



„Motivationstechniken“ von Angreifern
-------------------------------------------------------------

.. class:: incremental

- Autorität: Der Angreifer gibt sich z. B. als Mitarbeiter des IT-Supports aus.
- Einschüchterung (:eng:`Intimidation`)
- Dringlichkeit (*„In 10 Minuten verschlüssele ich den Rechner.“*)
- Konsens (*„Alle machen das so.“*)
- Knappheit (*„Es sind nur noch drei Rechner nicht infiziert.“*)
- Vertrautheit 
- Vertrauen



.. class:: new-section transition-fade

Cybersicherheit stärken 
-------------------------------------------------------------



.. class:: no-title center-child-elements

Bug-Bounty-Programme
-------------------------------------------------------------


.. epigraph::

    **Microsoft to offer hackers millions in Zero Day Quest event**

    Microsoft on Tuesday unveiled Zero Day Quest, a bug bounty event offering up to $4 million in rewards to security researchers.

    "At the end of the day, we recognize that when it comes to security, it's fundamentally a team sport," Microsoft CEO Satya Nadella said during his Tuesday keynote. "And that's why we want to partner, and we're partnering broadly with the security community." 

    [...] Zero Day Quest is the "largest of its kind" and will offer a potential $4 million in awards for research into cloud and AI, which he described as "high-impact areas."

    -- 19.11.2024 `Techtarget <https://www.techtarget.com/searchsecurity/news/366616078/Microsoft-to-offer-hackers-millions-in-Zero-Day-Quest-event>`__


.. supplemental::

    Bug-Bounty-Programme sind Initiativen, die Einzelpersonen oder Forschergruppen für das Finden und Melden von Softwarefehlern belohnen. Diese Programme werden häufig von Softwareanbietern initiiert, um die Sicherheit ihrer Produkte zu verbessern.


.. class:: no-title center-child-elements

BSI: Post-Quantum Cryptography
-------------------------------------------------------------

.. epigraph::

    **A joint statement from partners from 18 EU member states[...]**

    This threat to cryptography [i. e. established public-key cryptography is no longer secure] is posed by the development of a [...] quantum computer, which can break traditional public-key cryptographic schemes, [...] due to Shor’s algorithm. While there are currently no such cryptographically relevant quantum computers (CRQC) available, their development is progressing rapidly [...] :incremental:`preparing for the quantum threat should be considered an integral aspect of cyber security risk management.`

    :incremental:`[...] we currently strongly recommend to deploy PQC in hybrid solutions for most use-cases, i.e. combining a deployed cryptographic scheme with PQC in such a way that the combination remains secure even if one of its components is broken.`

    :incremental:`[...] The transition should also consider cryptoagility, allowing to ensure a more resilient transition to PQC[...]`

    -- 27.11.2024 `Securing Tomorrow, Today: Transitioning to Post-Quantum Cryptography (PQC) <https://www.bsi.bund.de/SharedDocs/Downloads/EN/BSI/Crypto/PQC-joint-statement.pdf?__blob=publicationFile&v=3>`__



Quantencomputer - Bedrohungsbewertung
-------------------------------------------------------------

.. rubric:: [Bewertung der Bedrohung durch Quantencomputer]

.. epigraph::

    [...] preparing for the quantum threat should be considered an integral aspect of cybersecurity risk management. In an attempt to quantify the risk, the 2023 issue of the Quantum Threat Timeline conducted a survey among 37 international leading experts from academia and industry. Out of these, 17 estimated the risk that a CRQC appears within a 10-year timeframe higher than 5%. Moreover, 10 of these respondents even indicated a likelihood of about 50% or more.

    :incremental:`[...] To ensure an acceptable level of readiness, we recommend that these should be protected against 'store now, decrypt later' attacks as soon as possible, latest by the end of 2030.`

    -- 27.11.2024 `Securing Tomorrow, Today: Transitioning to Post-Quantum Cryptography (PQC) <https://www.bsi.bund.de/SharedDocs/Downloads/EN/BSI/Crypto/PQC-joint-statement.pdf?__blob=publicationFile&v=3>`__


.. class:: new-subsection transition-scale

Die NIS 2 Richtlinie
-------------------------------------------------------------

.. container:: far-far-smaller minor margin-left-1em margin-right-1em margin-top-1em

    `Directive (EU) 2022/2555 of the European Parliament and of the Council of 14 December 2022 on measures for a high common level of cybersecurity across the Union, amending Regulation (EU) No 910/2014 and Directive (EU) 2018/1972, and repealing Directive (EU) 2016/1148 (NIS 2 Directive) <https://eur-lex.europa.eu/legal-content/DE/TXT/HTML/?uri=CELEX:32022L2555#d1e40-80-1>`__



NIS 2 Richtlinie (:eng:`NIS 2 Directive`)
-------------------------------------------------------------

.. container:: scrollable

    .. class:: incremental list-with-explanations

    - Die NIS2-Richtlinie ist die zweite EU-Richtlinie zur Netz- und Informationssicherheit (NIS) in der EU.
    - seit 17. Oktober 2024 müssen alle nationalstaaten entsprechende Regelungen in nationales Recht umgesetzt haben und ab 18. Oktober 2024 anwenden
    - Das Hauptziel ist die Verbesserung der Widerstandsfähigkeit gegen Cyberkriminalität und die Verbesserung des europäischen und nationalen Cybersecurity-Managements.

      Die neue NIS-2-Richtlinie zielt darauf ab, die Widerstandsfähigkeit und Reaktionsfähigkeit des öffentlichen und privaten Sektors zu verbessern. Der Schwerpunkt der Richtlinie liegt auf der Bekämpfung der Cyberkriminalität.

    -  Die NIS-2-Richtlinie gilt für Organisationen, inkl. Unternehmen und Zulieferer, die durch Erbringung wesentlicher oder wichtiger Dienstleistungen eine entscheidende Rolle für die Aufrechterhaltung der europäischen Wirtschaft und Gesellschaft spielen. 

    - Die Führungskräfte von betroffenen Einrichtungen sind für die Überwachung der Umsetzung der NIS-2-Richtlinie verantwortlich und können für Verstöße gegen die NIS-2-Richtlinie haftbar gemacht werden (Artikel 20).



.. supplemental::

    .. epigraph::

        **Artikel 20, Governance**

        (1)   Die Mitgliedstaaten stellen sicher, dass die Leitungsorgane wesentlicher und wichtiger Einrichtungen die von diesen Einrichtungen zur Einhaltung von Artikel 21 ergriffenen Risikomanagementmaßnahmen im Bereich der Cybersicherheit billigen, ihre Umsetzung überwachen und für Verstöße gegen diesen Artikel durch die betreffenden Einrichtungen verantwortlich gemacht werden können. [...]

        (2)   Die Mitgliedstaaten stellen sicher, dass die Mitglieder der Leitungsorgane wesentlicher und wichtiger Einrichtungen an Schulungen teilnehmen müssen, und fordern wesentliche und wichtige Einrichtungen auf, allen Mitarbeitern regelmäßig entsprechende Schulungen anzubieten, um ausreichende Kenntnisse und Fähigkeiten zur Erkennung und Bewertung von Risiken sowie Managementpraktiken im Bereich der Cybersicherheit und deren Auswirkungen auf die von der Einrichtung erbrachten Dienste zu erwerben.
   
        -- NIS 2 - KAPITEL IV `RISIKOMANAGEMENTMAẞNAHMEN UND BERICHTSPFLICHTEN IM BEREICH DER CYBERSICHERHEIT <https://eur-lex.europa.eu/legal-content/DE/TXT/HTML/?uri=CELEX:32022L2555#d1e3310-80-1>`__



NIS 2 - Berichtspflichten
----------------------------

- Wesentliche und wichtige Einrichtungen müssen unverzüglich (*in jeden Fall aber innerhalb von 24 Stunden*) über jeden Sicherheitsvorfall unterrichten, der erhebliche Auswirkungen auf die Erbringung ihrer Dienste hat
-   Ein Sicherheitsvorfall gilt als erheblich, wenn

    .. class:: incremental

    a) er schwerwiegende Betriebsstörungen der Dienste oder finanzielle Verluste für die betreffende Einrichtung verursacht hat oder verursachen kann;

    b) er andere natürliche oder juristische Personen durch erhebliche materielle oder immaterielle Schäden beeinträchtigt hat oder beeinträchtigen kann.



Von NIS2 betroffene Öffentliche und private Einrichtungen\ [#]_
-----------------------------------------------------------------

.. container:: far-smaller

    Folgende Organisation mit mehr als 50 Mitarbeitern und einem Umsatz von mehr als 10 Millionen Euro müssen die NIS-2-Richtlinie einhalten (obligatorisch).


.. container:: two-columns far-smaller
    
    .. container:: column no-separator

        - Post- und Kurierdienste
        - Abfallwirtschaft
        - Chemie
        - Lebensmittel
        - Herstellung medizinischer Geräten
        - Computer und Elektronik
        - Maschinen
        - Kraftfahrzeuge
        - Energie

    .. container:: column

        - Verkehrswesen
        - Bankwesen
        - Finanzmarkt-Infrastrukturen
        - Gesundheitswesen
        - Trinkwasserversorgung und -verteilung
        - Digitale Infrastrukturen
        - Online-Marktplätze
        - Online-Suchmaschinen
        - Cloud Computing-Dienste

.. container:: incremental far-smaller
    
    Bis zum 17. April 2025 erstellen die Mitgliedstaaten eine Liste von wesentlichen und wichtigen Einrichtungen und von Einrichtungen, die Domänennamen-Registrierungsdienste erbringen und aktualisieren sie gegebenenfalls regelmäßig — spätestens alle 2 Jahre.
  
.. [#] `Details siehe Anhang I und II der NIS 2 Richtlinie <https://eur-lex.europa.eu/legal-content/DE/TXT/HTML/?uri=CELEX:32022L2555#d1e32-143-1>`__



.. class:: no-title center-child-elements

NIS 2 - Nationale Cybersicherheitsstrategie
-------------------------------------------------------------

.. attention::

    Jeder Mitgliedstaat erlässt eine *nationale Cybersicherheitsstrategie*, die die strategischen Ziele, die zur Erreichung dieser Ziele erforderlichen Ressourcen sowie angemessene politische und regulatorische Maßnahmen zur Erreichung und Aufrechterhaltung eines hohen Cybersicherheitsniveaus enthält.



NIS 2 - zentrale Einrichtungen
-------------------------------------------------------------

.. image:: images/nis_2.svg
    :align: center
    :width: 95%

.. container:: legende far-far-smaller padding-1em

   :CSIRT: Computer Security Incident Response Team
   :Behörden für das Krisenmanagement: Sollte es mehr als eine geben, so wird eine explizit benannt, die für die Koordination und das  Management von *Cybersicherheitsvorfällen großen Ausmaßes und Krisen* zuständig ist
 

.. supplemental::

    Ein zentraler Gedanke ist die Vernetzung der zuständigen Behörden sowohl auf nationaler als auch auf europäischer Ebene sicherzustellen.