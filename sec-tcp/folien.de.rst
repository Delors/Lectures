.. meta::
    :author: Michael Eichberg
    :keywords: TCP, DDoS
    :description lang=de: Network Security - Eine Einführung in die Sicherheit von (verteilten) Systemen
    :id: it-security-tcp-vorlesung
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!
    :svg-defs:
        <marker
            id="arrow"
            viewBox="0 0 10 10"
            refX="10"
            refY="5"
            markerWidth="8"
            markerHeight="8"
            orient="auto-start-reverse">
            <path d="M 0 0 L 10 5 L 0 10 z" />
        </marker>

.. include:: ../docutils.defs



Aspekte der Netzwerksicherheit: Transmission Control Protocol (TCP)
========================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.3.7

.. class:: sources

:Quellen: Folien von Prof. Dr. Henning Pagnia bzgl. Netzwerksicherheit

.. supplemental::

  :Folien:
      [HTML] |html-source|

      [PDF] |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues



.. class:: repetition

TCP Grundlagen
-------------------

.. class:: incremental-list

- Protokoll der Schicht 4 (Transport Layer) basiert auf IP
- verbindungsorientierte Kommunikation zweier Rechner im Internet zuverlässig und geordnet:

  .. class:: incremental-list

  - Verwerfen von Duplikaten und fehlerhaft übertragener Pakete
  - automatisches Wiederversenden fehlender Pakete
  - Nachrichtenpuffer: Daten werden in korrekter Reihenfolge an Applikation zugestellt

- Verbindungsaufbau immer zwischen zwei Sockets (Socket-Adresse: IP Adresse und 16 Bit-Port-Nummer)



.. class:: repetition

Aufbau einer TCP Verbindung
-----------------------------

Dreifacher Handshake:

.. supplemental::

    **Terminologie**:

    :SYN: :eng:`synchronize (session establishment)`
    :ACK: :eng:`acknowledge`
    :RST: :eng:`reset`

    **Verbindungsaufbau - Ablauf**:

    1. Client sendet SYN Paket mit initialer Sequenznummer (hier) 1000 an den Server.
    2. Server sendet ein SYN-ACK Paket mit seiner initialen Sequenznummer (hier) 2000 und ein ACK mit der Sequenznummer 1001 (initiale Sequenznummer des Clients +1) an den Client
    3. Client sendet ein ACK Paket mit Sequenznummer 2001 (initiale Sequenznummer des Servers +1) an den Server; danach ist die Verbindung aufgebaut.

    Das Betriebssystem sollte die initialen Sequenznummern zufällig wählen, so dass ein Angreifer diese nicht leicht vorhersagen kann. Beide Seiten haben eigene Sequenznummern, die unabhängig voneinander sind.

    Bei einer laufenden Verbindung werden die Sequenznummern inkrementiert und es ist nicht (mehr) erkennbar wer die Verbindung aufgebaut hat.

.. raw:: html
    :class: center-child-elements

    <div style="width: 76ch; height: 40ch"; container-type:size;">
    <svg viewBox="100 0 1140 600" font-size="36" version="1.1" xmlns="http://www.w3.org/2000/svg">

        <text x="125" y="75" style="font-weight: bolder">Client</text>
        <line x1="200" y1="100" x2="200" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
        <text x="925" y="75" style="font-weight: bolder">Server</text>
        <line x1="1000" y1="100" x2="1000" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
        <line x1="200" y1="400" x2="200" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />
        <line x1="1000" y1="400" x2="1000" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />

        <text x="500" y="65" transform="rotate(6.6)">SYN(1000)</text>
        <line x1="200" y1="110" x2="1000" y2="190" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>

        <g class="incremental">
        <text x="270" y="300" transform="rotate(-6.6)">SYN(2000), ACK(1001)</text>
        <line x1="1000" y1="200" x2="200" y2="290" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
        </g>

        <g class="incremental">
        <text x="555" y="315" transform="rotate(6.6)">ACK(2001)</text>
        <line x1="200" y1="300" x2="1000" y2="390" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
        </g>
    </svg>
    </div>



.. class:: repetition


Ports bei TCP
----------------

.. class:: incremental-list

- Port-Nummern werden für die Kommunikation zwischen zwei Diensten/Prozessen verwendet
- Ports sind 16 Bit Zahlen (0-65535)
- (Unix) Ports < 1024 sind privilegiert (nur root kann diese öffnen)
- einige Port-Nummern sind Standarddiensten zugeordnet



.. class:: repetition

Port-Nummern einiger Standarddienste [#]_
------------------------------------------

**Ungeschützte Dienste** (Kommunikation findet ohne Verschlüsselung statt.)

.. csv-table::
    :header: Protokoll, Dienst, Portnummer
    :class: highlight-row-on-hover
    :widths: 100, 600, 50

    ftp, Dateitransfer, 21
    smtp, Simple Mail Transfer Protocol, 25
    dns, Domain Name System, 53
    http, Hypertext Transfer Protocol, 80
    login, Login auf entfernte Rechner, 513

**Geschützte Dienste** (Die Kommunikation ist verschlüsselt.)

.. csv-table::
    :header: Protokoll, Dienst, Portnummer
    :class: highlight-row-on-hover incremental
    :widths: 100, 600, 50

    ssh, Secure Shell, 22
    https, HTTP über Secure Socket Layer, 443
    smtps, SMTP über Secure Socket Layer, 465
    imaps, IMAP über Secure Socket Layer, 993
    pop3s, POP3 über Secure Socket Layer, 995


.. [#] `Port numbers assigned by IANA <https://www.iana.org/assignments/service-names-port-numbers>`__



Angriffe auf TCP - Motivation
--------------------------------

.. class:: incremental-list list-with-explanations

- Netzwerkprogrammierung mit TCP ist relativ komfortabel.
- Viele Dienste sind mit TCP implementiert.

  Insbesondere in der Anfangszeit hatten viele TCP Dienste sowohl technische als auch konzeptionelle Schwachstellen. Einige dieser Schwachstellen sind bis heute nicht behoben.
- Das Auffinden von angreifbaren Diensten kann mit Hilfe von Port Scans systematisch erfolgen.

  Server haben heutzutage im Allgemeinen alle nicht verwendeten Dienste geschlossen.



Port Scans: TCP Connect Scan
-------------------------------

.. grid::

    .. cell:: width-50 incremental

          .. rubric:: Vorgehen

          Aufbau vollständiger Verbindungen zu allen bzw. zu ausgewählten Ports.

    .. cell:: width-50

        .. raw:: html

            <div style="width: 36ch; height:18ch; container-type:size;">
            <svg viewBox="0 0 1200 600" font-size="48" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <text x="125" y="75" style="font-weight: bolder">Scanner</text>
                <line x1="200" y1="100" x2="200" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
                <text x="925" y="75" style="font-weight: bolder">Server</text>
                <line x1="1000" y1="100" x2="1000" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
                <line x1="200" y1="400" x2="200" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />
                <line x1="1000" y1="400" x2="1000" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />

                <text x="500" y="65" transform="rotate(6.6)">SYN</text>
                <line x1="200" y1="110" x2="1000" y2="190" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>

                <text x="390" y="300" transform="rotate(-6.6)">SYN / ACK</text>
                <line x1="1000" y1="200" x2="200" y2="290" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>

                <text x="555" y="315" transform="rotate(6.6)">ACK</text>
                <line x1="200" y1="300" x2="1000" y2="390" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
            </svg>
            </div>

.. assessment::
    :class: incremental

    - simpelster Port Scan
    - große Entdeckungsgefahr (Scan selbst ist kein Angriff)
    - mögliche Verbesserung: zwischen dem Scannen mehrerer Ports Pausen einstreuen (Wie lange?)



Port Scans: TCP SYN Scan
-----------------------------

.. grid::

    .. cell:: width-50 incremental

        .. rubric:: Vorgehen

        1. Senden eines TCP-Segments mit gesetztem SYN-Flag an einen Port
        2. falls der *Port offen* ist, kommt SYN/ACK zurück danach RST senden
        3. falls der *Port nicht offen* ist, kommt RST (oder nichts) zurück

    .. cell:: widh-50

        .. raw:: html

            <div style="width: 36ch; height:18ch; container-type:size;">
            <svg viewBox="0 0 1200 600" font-size="48" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <text x="125" y="75" style="font-weight: bolder">Scanner</text>
                <line x1="200" y1="100" x2="200" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
                <text x="925" y="75" style="font-weight: bolder">Server</text>
                <line x1="1000" y1="100" x2="1000" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
                <line x1="200" y1="400" x2="200" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />
                <line x1="1000" y1="400" x2="1000" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />

                <text x="500" y="65" transform="rotate(6.6)">SYN</text>
                <line x1="200" y1="110" x2="1000" y2="190" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>

                <text x="390" y="300" transform="rotate(-6.6)">SYN / ACK</text>
                <line x1="1000" y1="200" x2="200" y2="290" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>

                <text x="555" y="315" transform="rotate(6.6)">RST</text>
                <line x1="200" y1="300" x2="1000" y2="390" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
            </svg>
            </div>

.. assessment::
    :class: incremental

    - kein vollständiger Verbindungsaufbau
    - meist nicht protokolliert
    - geringe(re) Entdeckungsgefahr



Port Scans: Stealth Scans
-----------------------------

:Vorgehen: Versenden eines für den Verbindungsaufbau ungültigen TCP-Segments an einen Port:

    .. class:: incremental

    - NULL-Scan (keine Flags)
    - ACK-Scan (ACK-Flag)
    - FIN-Scan (FIN-Flag)
    - XMAS-Scan (alle Flags)

    .. class:: incremental

    Laut RFC kommt RST zurück, falls der Port offen ist. (Reaktion ist de-facto aber abhängig vom Betriebssystem und oft kommt keine Antwort zurück.)

.. assessment::
    :class: incremental

    - Zugriff wird meist nicht protokolliert
    - Scan bleibt unbemerkt

.. supplemental::

    **XMAS-Scan**:

    Bei diesem Scan sind alle Flags gesetzt; ein XMAS-Scan wird auch als Christmas-Tree-Scan bezeichnet, da das Paket erleuchtet ist wie ein Weihnachtsbaum.



Port Scans: Idle Scan [#]_
-----------------------------

Bei allen bisher betrachteten Scans kann der Scanner prinzipiell identifiziert werden. Unter Verwendung eines sog. Zombies geht es auch anders:

.. grid::

    .. cell:: width-50

        Sondiere IP ID des Zombies:

        .. image:: images/idle-scan/idle-scan-step1.svg
            :alt: Idle Scan - Schritte 1-2

    .. cell:: width-50 fade-out

        Starte Scan:

        .. image:: images/idle-scan/idle-scan-step2.svg
            :alt: Idle Scan - Schritte 3-5

.. supplemental::

    :Zombie: ein Rechner (Computer, Drucker oder anderes IoT Gerät) im Internet *möglichst ohne eigenen Netzverkehr* und mit *altem* Betriebssystem, bei dem die IP ID in vorhersehbarer Weise inkrementiert wird. (Bei modernen Betriebssystemen ist die IP ID zufällig, **konstant** oder sogar ``null``.)

    :Grundlegende Idee: Der Zombie sendet ein RST Paket zurück, da er kein SYN gesendet hat und kein SYN/ACK erwarte. Dadurch erfährt der Angreifer die aktuelle IP ID des Zombies. Über diesen Seitenkanal - d. h. die Veränderung der IP ID des Zombies - kann der Angreifer nun den Zustand des Ports auf dem Zielrechner ermitteln.

    .. hint::

        Sollte ein Intrusion Detection System vorhanden sein, so wird dieses den Zombie als Angreifer identifizieren.

    .. rubric:: Hintergrund - IP ID

    Das Feld *IP Identifikation (IP ID)* dient der Identifizierung einer Gruppe von Fragmenten eines einzelnen IP-Datagramms.

    .. image:: images-external/IPv4_Packet-en.svg
            :alt: IPv4 Packet
            :align: left

    .. container:: peripheral

        By Michel Bakni - Postel, J. (September 1981) RFC 791, IP Protocol, DARPA Internet Program Protocol Specification, p. 1 DOI: 10.17487/RFC0791., CC BY-SA 4.0, https://commons.wikimedia.org/w/index.php?curid=79949694


.. [#] `NMap Book <https://nmap.org/book/idlescan.html>`__



Port Scans: Idle Scan
-----------------------------

.. grid::

    .. cell:: width-50

        Starte Scan:

        .. image:: images/idle-scan/idle-scan-step2.svg
            :alt: Idle Scan - Schritte 3-5

    .. cell:: width-50

        Sondiere IP ID des Zombies:

        .. image:: images/idle-scan/idle-scan-step3.svg
            :alt: Idle Scan - Schritt 6



Port Scans: Idle Scan - Zusammenfassung
----------------------------------------

- Angreifer sendet SYN/ACK Paket an Zombie
- der Zombie antwortet mit RST und enthüllt seine IP ID (:eng:`IP Fragment Identification Number`).
- Angreifer sendet SYN („mit IP vom Zombie“) an Port des Servers:

  [**Port offen**] Der Zielrechner antwortet mit SYN/ACK an den Zombie, wenn der Port offen ist. Der Zombie antwortet darauf mit RST an den Server, da er kein SYN gesendet hat und kein SYN/ACK erwartet und *erhöht seine IP ID*.

  [**Port geschlossen**] Der Zielrechner antwortet mit RST an den Zombie, wenn der Port geschlossen ist. Dies wird vom Zombie ignoriert.
- Der Angreifer sendet wieder ein SYN/ACK an den Zombie, um die IP ID zu erfahren.

.. supplemental::

    Mit einem IDLE Scan kann nicht unterschieden werden, ob der Port geschlossen oder gefiltert ist.




Port Scans mit nmap
-----------------------

.. class:: incremental-list

- alle Arten von Port-Scans möglich
- auch OS fingerprinting
- u. U. sogar Ermittlung der Versionsnummern von Diensten

.. code:: bash
    :class: incremental

    $ nmap 192.168.178.121 -Pn
    Starting Nmap 7.94 ( https://nmap.org ) at 2023-12-14 13:16 PST
    Nmap scan report for Michaels-MacBook-Pro (192.168.178.121)
    Host is up (0.0056s latency).
    Not shown: 995 filtered tcp ports (no-response)
    PORT     STATE SERVICE
    53/tcp   open  domain
    88/tcp   open  kerberos-sec
    445/tcp  open  microsoft-ds
    5000/tcp open  upnp
    7000/tcp open  afs3-fileserver

.. supplemental::

    **OS-Fingerprinting**

    Beim OS-Fingerprinting werden Datenpakete analysiert, die aus einem Netzwerk stammen, um Informationen für spätere Angriffe zu gewinnen. Durch die Erkennung des Betriebssystems, mit dem ein Netzwerk arbeitet, haben Hacker es leichter, Schwachstellen zu finden und auszunutzen. OS-Fingerprinting kann auch Konfigurationsattribute von entfernten Geräten sammeln. Diese Art von Aufklärungsangriff ist in der Regel (einer) der erste(n) Schritt(e).

    Es gibt zwei Arten von OS-Fingerprinting: (1) Aktiv und (2) passiv.

        (1) Bei einem aktiven OS-Fingerprinting-Versuch senden die Angreifer ein Paket an das Zielsystem und warten auf eine Antwort, um den Inhalt des TCP-Pakets zu analysieren.

        (2) Bei einem passiven Versuch agieren die Angreifer eher als "Schnüffler", der keine absichtlichen Änderungen oder Aktionen im Netzwerk vornimmt. Passives OS-Fingerprinting ist ein unauffälligerer, aber wesentlich langsamerer Prozess.



Port Knocking
----------------

.. class:: incremental-list list-with-explanations

- Ein Knock-Daemon versteckt offene Ports auf dem Server.
- Zugriffe auf alle Ports werden im Log-File protokolliert.
- Knock-Daemon beobachtet das Log-File.
- Erst nach Erkennen einer vordefinierten (Einmal-)Klopfsequenz öffnet der Knock-Daemon den gewünschten Port für diesen Client.
- Client kann nun die Verbindung aufbauen.
- Weiterentwicklung: TCP Stealth

  In diesem Fall werden offene Ports dadurch versteckt, dass sie nur auf spezielle SYN-Pakete mit bestimmten Sequenznummern reagieren. Die Sequenznummern sind ggf. kryptografisch abgesichert und basieren auf vorher ausgetauschten Schlüsseln.

.. supplemental::

    **Weiterführend**

    Alternativen zu einer Knock-Sequenz ist zum Beispiel, dass der Port nur dann als offen gilt, wenn die IP ID eine bestimmte Sequenznummer aufweist.

    M.\ Krzywinski: Port Knocking: Network Authentication Across Closed Ports in SysAdmin Magazine 12: 12-17. (2003)



Connection Hijacking
-------------------------

Angreifer übernimmt eine bestehende - zum Beispiel eine bereits durch (Einmal-)Passwort authentisierte - Verbindung.

.. image:: images/connection-hijacking.svg
    :alt: Connection Hijacking (einfache Variante)
    :align: center

.. supplemental::

    TCP/IP-Hijacking ist eine Form eines Person-in-the-Middle-Angriffs. Der Angreifer bestimmt erst die IP-Adressen der beiden Sitzungsteilnehmer.

    Danach gibt es mehrere Möglichkeiten:

    - Der Angreifer schickt ("in einer Pause") ein Paket mit der passenden Sequenznummer an den Server.

      *(Dies kann dann in einem ACK-Storm enden, was ggf. unterbunden werden muss (zum Beispiel durch das Senden eines RSTs), oder ignoriert werden kann.)*

    - Der Angreifer macht einen Client mit einem DoS-Angriff unerreichbar, um sich dann mit dem Anderen zu verbinden, indem er die Netzwerk-ID des ausgeschalteten Clients nutzt.



Denial-of-Service (DoS) Angriffe
------------------------------------

Ziel des Angreifers: Lahmlegen eines Dienstes oder des ganzen Systems ...

- durch Ausnutzen von Schwachstellen (:eng:`vulnerabilities`) wie z. B. Buffer Overflows
- durch Generierung von Überlast (Ausschöpfen von RAM, CPU, Netzwerkbandbreite, ...)

.. example::
    :class: incremental

    .. rubric:: Ping-of-Death

    (Historisch: aus dem Jahr 1997)

    Ein ``ping`` (vgl. Internet Control Message Protocol (ICMP)) verwendet üblicherweise kleine Nachrichten, aber die verwendete Länge ist einstellbar.

    Falls die Länge zu groß ist ⇒ Buffer Overflow ⇒ Systemabsturz!

    Variante: mittels Fragmentierung ließen sich generell übergroße IP-Pakete (>65,536 Byte) erstellen.



Denial-of-Service: SYN-flooding Angriff
-----------------------------------------

.. class:: incremental-list

- Angriff auf Design
- Angreifer sendet eine Verbindungsaufbauanforderung (gesetztes SYN-Flag) an Zielmaschine
- Server generiert eine halboffene TCP-Verbindung
- Angreifer wiederholt in schneller Folge dieses erste Paket zum Verbindungsaufbau

  ⇒ vollständiges Füllen der internen Systemtabelle

  ⇒ Anfragen normaler Benutzer werden zurückgewiesen

- Angreifer verwendet i. Allg. IP-Spoofing weswegen Firewalls wirkungslos sind.
- Abwehr: SYN-Cookies



`SYN-Cookies - D J. Bernstein <https://cr.yp.to/syncookies.html>`__
-----------------------------------------------------------------------

SYN-Cookies sind speziell konstruiert initiale Sequenznummern.

.. deck::

    .. card::

        .. raw:: html

            <div style="width:72ch;height:27ch">
            <svg viewBox="100 0 1600 600" font-size="36" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <text x="150" y="75" style="font-weight: bolder">Client</text>
                <line x1="200" y1="100" x2="200" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
                <line x1="200" y1="400" x2="200" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />

                <text x="1450" y="75" style="font-weight: bolder">Server</text>
                <line x1="1500" y1="100" x2="1500" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
                <line x1="1500" y1="400" x2="1500" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />

                <text x="500" y="75" transform="rotate(4.25)">SYN(1000)</text>
                <line x1="200" y1="110" x2="1500" y2="190" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
            </svg>
            </div>

    .. card:: overlay

        .. raw:: html

            <div style="width:72ch;height:27ch">
            <svg viewBox="100 0 1600 600" font-size="36"  version="1.1" xmlns="http://www.w3.org/2000/svg">
                <text x="270" y="290" transform="rotate(-4.25)">SYN(with cookie), ACK(1001)</text>
                <line x1="1500" y1="200" x2="200" y2="290" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
            </svg>
            </div>

        Der Cookie ermöglicht es, dass keine Informationen im Speicher gehalten werden müssen. Der Cookie encodiert die Informationen, die der Server benötigt, um die Verbindung aufzubauen: Client IP, time window, etc.

    .. card:: overlay

        .. raw:: html

            <div style="width:72ch;height:27ch">
            <svg  viewBox="100 0 1600 600" font-size="36"  version="1.1" xmlns="http://www.w3.org/2000/svg">
                <text x="555" y="325" transform="rotate(4.2)">ACK(with cookie(+1))</text>
                <line x1="200" y1="300" x2="1500" y2="390" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>

                <text x="1515" y="340" >Validierung</text>
                <text x="1515" y="390" >des Cookie</text>
                <line x1="1600" y1="400" x2="1600" y2="455" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
                <text x="1515" y="490" >ggf. </text>
                <text x="1515" y="540" >Verbindungs-</text>
                <text x="1515" y="590" >aufbau</text>
            </svg>
            </div>



Distributed Denial-of-Service (DDoS) Angriff
------------------------------------------------

Opfer wird von sehr vielen Angreifern mit Nachrichten überflutet.

.. container:: incremental

    Ein Beispiel: Smurf-Angriff:

    .. image:: images/smurf-angriff.svg
        :alt: Smurf Angriff
        :align: center



Distributed Denial-of-Service (DDoS) Angriff
------------------------------------------------

.. class:: incremental-list

- Bot-Netze (Botnetze) werden verwendet, um DDoS-Angriffe durchzuführen.
- Bot-Netze können viele 10.000 Rechner umfassen.
- IoT Geräte sind besonders beliebt (z. B. IP-Kameras, Smart-TVs, Smart-Home Geräte, ...), da diese oft nicht ausreichend geschützt sind und trotzdem permanent mit dem Internet verbunden sind.
- Beliebte Ziele:

  - Onlinespieleserver
  - Banking-Portale
  - politische Webseiten
- Firewalls und Intrusion Detection Systeme sind meist wirkungslos, da die Angriffe von vielen verschiedenen IP-Adressen kommen.



Distributed-Reflected-Denial-of-Service Angriff
------------------------------------------------------------

.. deck::

    .. card::

        .. grid::

            .. cell:: width-50

                Idee eines (DRDoS) Angriffs

                .. class:: incremental-list list-with-explanations

                - Es wird eine Anfrage an einen Server gesendet, die eine große Antwort auslöst.

                  .. supplemental::

                    Z. B. hat(te) der NTP Monlist Befehl eine Antwort, die ca. 200 Fach größer ist als die Anfrage!

                - Mittels IP-Spoofing wird die IP-Adresse des Opfers als Absenderadresse verwendet.

                - Es werden insbesondere Dienste basierend auf UDP verwendet, da hier keine Verbindung aufgebaut werden muss.

            .. cell:: width-50

                .. image:: images/drdos.svg
                    :alt: DRDoS Angriff
                    :align: center

    .. card::

        - Nehmen einen signifikanten Teil aller DDoS-Angriffe ein.
        - Die Tatsache, dass die Sender legitime Server sind, erschwert die Abwehr.
        - :eng:`Egress Filtering` kann helfen, die Verwendung von IP-Spoofing zu verhindern.

.. supplemental::

    Bereits im Jahr 2018 wurde ein Angriff mit einer Bandbreite von 1,7 TBit/s beobachtet.

    :Egress Filtering: Der Router verwirft alle Pakete, die eine Absenderadresse verwenden, die nicht aus dem eigenen Netzwerk stammt.



Distributed-Denial-of-Service-Angriffe (DDoS)
--------------------------------------------------------------------------------

.. deck::

    .. card::

        .. epigraph::

            [...] Google's DDoS Response Team has observed the trend that distributed denial-of-service (DDoS) attacks are **increasing exponentially in size**. Last year, we blocked the largest DDoS attack recorded at the time. This August [2023], we stopped an even larger DDoS attack — 7½ times larger — that also used new techniques to try to disrupt websites and Internet services.

            This new series of DDoS attacks reached **a peak of 398 million requests per second (rps)**, and relied on a novel HTTP/2 “Rapid Reset” technique based on stream multiplexing that has affected multiple Internet infrastructure companies. By contrast, last year's largest-recorded DDoS attack peaked at 46 million rps.

            -- `Okt. 2023 - DDoS Attack with 398 Million RPS  <https://cloud.google.com/blog/products/identity-security/google-cloud-mitigated-largest-ddos-attack-peaking-above-398-million-rps>`__

    .. card::

        .. epigraph::

            Cloudflare hat Mitte Mai den "größten jemals registrierten" Denial-of-Service-Angriff (DDoS) mit [...] 7,3 Terabit pro Sekunde (TBit/s) blockiert. [...] Diese Attacke war demnach rund 12 Prozent größer als der vorherige Rekord und lieferte ein massives Datenvolumen von 37,4 Terabyte in nur 45 Sekunden. [...]

            Stellen Sie sich vor, Sie könnten mit Ihrem Smartphone 12,5 Millionen hochauflösende Fotos schießen und hätten nie einen vollen Speicherplatz." Und das alles in 45 Sekunden.

            [...] Mitgewirkt hätten über 122.145 Quell-IP-Adressen, die sich über 5433 autonome Netzwerksysteme in 161 Ländern erstreckten.

            -- `22.06.2025 Heise.de - Rekord DDoS Angriff <https://www.heise.de/news/Junk-Traffic-Flut-Rekord-DDoS-Angriff-auf-Provider-mit-7-3-TBit-s-10455216.html>`__

    .. card::

        .. epigraph::

            [...] Der letzte rekordverdächtige Überlastungsangriff ist noch gar nicht so lange her, da vermeldet Cloudflare schon den nächsten beobachteten Spitzenwert. Am Montag erreichte ein Distributed-Denial-of-Service-Angriff (DDoS) in der Spitze eine Last von 11,5 Terabit pro Sekunde. Das entspricht umgerechnet mehr als 1,4 Terabyte je Sekunde oder dem Inhalt von 184 randvollen DVDs.

            [...] Dabei sendeten die Angreifer 5,1 Milliarden Pakete pro Sekunde (Bpps). Bei letzterer handelte es sich demnach um eine UDP-Flood-Attacke, die ihren Ausgangspunkt hauptsächlich in der Google-Cloud hatte. Die Zeitspanne, die der Höchstlast-Angriff einnahm, war etwa 35 Sekunden lang, schreibt Cloudflare weiter.

            -- `03.09.2025 - Heise.de: Überlastungsattacke erreicht 11,5 TBit pro Sekunde <https://www.heise.de/news/Ueberlastungsattacke-erreicht-11-5-TBit-pro-Sekunde-10630141.html>`__



Distributed Denial-of-Service Angriffe - Beispiele
---------------------------------------------------

- **TCP Stack Attacks** SYN, FIN, RST, ACK, SYN-ACK, URG-PSH, other combinations of TCP Flags, slow TCP attacks
- **Application Attacks**:HTTP GET/POST Floods, slow HTTP Attacks, SIP Invite Floods, DNS Attacks, HTTPS Protocol Attacks
- **SSL/TLS Attacks**: Malformed SSL Floods, SSL Renegotiation, SSL Session Floods
- **DNS Cache Poisoning**
- **Reflection Amplification Flood Attacks**: TCP, UDP, ICMP, DNS, mDNS, SSDP, NTP, NetBIOS, RIPv1, rpcbind, SNMP, SQL RS, Chargen, L2TP, Microsoft SQL Resolution Service
- **Fragmentation Attacks**: Teardrop, Targa3, Jolt2, Nestea
- **Vulnerability Attacks**
- **Resource Exhaustion Attacks**: Slowloris, Pyloris, LOIC, etc.
- **Flash Crowd Protection**
- **Attacks on Gaming Protocols**



Schutz vor DDoS-Angriffen: On-Site Maßnahmen
--------------------------------------------------------

.. class:: incremental-list list-with-sublists

- Aufrüsten der Ressourcen (z. B. Bandbreite, CPU, RAM, ...)
- Exemplarische Sofortmaßnahmen bei aktivem Angriff:

  .. class:: incremental-list

  - Whitelisting von IP-Adressen von besonders wichtigen Clients
  - Blacklisting von IP-Adressen aus bestimmten Bereichen
  - Captchas
  - Überprüfung der Browser-Echtheit

- Anti-DDos Appliances

.. attention::
    :class: incremental

    Diese Maßnahmen sind häufig teuer und ggf. begrenzt effektiv; wenn der Angriff die verfügbare Bandbreite übersteigt, sind diese Maßnahmen darüber hinaus wirkungslos.



Schutz vor DDoS-Angriffen: Off-Site Maßnahmen
------------------------------------------------------------

.. class:: incremental-list list-with-explanations

- Einbinden des ISP
- Einbinden spezialisierter Dienstleister

  (Im Angriffsfall wird mittels BGP-Rerouting der Traffic an den Dienstleister umgeleitet, der dann die DDos Attacke filtert.)
- Content-Delivery-Networks (CDNs) für statische Inhalte (z. B. Cloudflare, Akamai, ...)
- Distributed Clouds



.. class:: exercises transition-move-left

Übung
------------------------------

.. exercise:: Port Scans - IDLE Scan

  - Warum kann bei einem IDLE Scan nicht festgestellt werden weshalb ein Port geschlossen oder gefiltert ist?
  - Welchen Wert hat die IP ID des Zombies, der einem IDLE Scan durchführt, wenn der Zielport offen bzw. geschlossen ist, wenn der Scanner diesen wieder abfragt?

  .. solution::
     :pwd: IDLEPort

     - Wenn der Port geschlossen ist, dann sendet der Zielrechner ein RST Paket an den Zombie. Dieses wird vom Zombie ignoriert. Daher erhöht sich die IP ID des Zombies nicht.
     - Wenn der Port offen ist, dann sendet der Zielrechner ein SYN/ACK Paket an den Zombie. Dieser antwortet mit einem RST Paket und erhöht seine IP ID um 1. D. h. der Wert der IP ID des Zombies ist um 2 höher, wenn der Port offen ist und „nur“ eins höher sonst.



.. class:: exercises transition-move-left

Übung
--------------

.. exercise:: DDoS

  1.  Welches Problem entsteht wenn zum Schutze vor Angriffen auf die Verfügbarkeit die Ressourcen von IT-Systemen und deren Internet-Anbindung erhöht werden?
  2. Recherchieren Sie was ein „Low and Slow Angriff“ ist.
  3. Wo kann überall „Egress filtering“ statt finden.

  .. solution::
    :pwd: DDoSVerstehen

    1. Ressourcenverschwendung wenn gerade kein Angriff stattfindet. Wenn der Angriff stattfindet, dann ist es immer noch möglich bzw. sogar wahrscheinlich, dass die Ressourcen nicht ausreichen.
    2. (vgl. https://www.cloudflare.com/de-de/learning/ddos/ddos-low-and-slow-attack/)

       Ein Low-and-Slow-Angriff ist eine Art von DoS- oder DDoS-Angriff, der sich auf einen kleinen Strom sehr langsamen Traffics stützt, der auf Anwendungs- oder Serverressourcen abzielt. Im Gegensatz zu herkömmlichen Brute-Force-Angriffen benötigen Low-and-Slow-Angriffe nur sehr wenig Bandbreite und können schwer bekämpft werden, da sie Traffic erzeugen, der nur sehr schwer von normalem Traffic zu unterscheiden ist. Während groß angelegte DDoS-Angriffe wahrscheinlich schnell bemerkt werden, können Low-and-Slow-Attacken über lange Zeiträume unentdeckt bleiben, während der Dienst für echte Nutzer verweigert oder verlangsamt wird.

       Da sie nicht viele Ressourcen benötigen, können Low-and-Slow-Angriffe von einem einzigen Computer aus erfolgreich durchgeführt werden, im Gegensatz zu verteilten Angriffen, für die ein Botnet erforderlich sein kann. Zwei der beliebtesten Tools für Low-and-Slow-Angriffe heißen Slowloris und R.U.D.Y.

       .. rubric:: R.U.D.Y.

       „R U Dead Yet?“ oder R.U.D.Y. ist ein Denial-of-Service-Angriffstool, das zum Ziel hat, einen Webserver durch Senden von Formulardaten bei unsinnig niedriger Geschwindigkeit zu blockieren. Ein R.U.D.Y.-Exploit wird als Low-and-Slow-Angriff kategorisiert, weil er darauf abzielt, einige wenige langwierige Anfragen zu erzeugen, anstatt einen Server mit einem hohen Volumen schneller Anfragen zu überfluten. Ein erfolgreicher R.U.D.Y.-Angriff bewirkt, dass der Ursprungsserver des Opfers für legitimen Traffic unzugänglich wird.

       .. rubric:: Slowloris

       Slowloris ist ein *low and slow* DDoS-Angriffsvektor. Die Idee des Slowloris-Angriffs besteht darin, den gesamten TCP-Stack für den HTTP/S-Daemon zu sättigen. Dies geschieht, indem langsam Verbindungen geöffnet und dann eine unvollständige Anfrage gesendet wird, um die Verbindung so lange wie möglich am Leben zu erhalten. Das Tool geht dabei langsam vor, so dass es in einigen Fällen möglich ist, dass ein einziger Angreifer einen Webserver zum Absturz bringen kann. Wenn das Limit der gleichzeitigen Verbindungen auf dem angegriffenen Server erreicht ist, kann der Server nicht mehr auf legitime Anfragen von anderen Benutzern reagieren, was zu einer Dienstverweigerung führt.

       Der Slowloris-Angriff zielt darauf ab, die Verbindungstabelle zu füllen, so dass der Server nicht mehr in der Lage ist, neue legitime Anfragen von legitimen Benutzern zu bedienen. Dies wird durch den Einsatz von zwei Hauptfunktionen erreicht: 1. Instabile Öffnungsrate für neue Verbindungen - neue TCP-Verbindungen werden stoßweise angefordert, wobei zwischen jedem Stoß eine gewisse Zeit gewartet wird, was es schwierig macht, von ratenbasierten (:eng:`rate limiting`) Abhilfemaßnahmen entdeckt zu werden. 2. Aufrechterhaltung neu eingerichteter TCP-Verbindungen - neu eingerichtete TCP-Verbindungen werden aufrechterhalten, indem Teildaten über mehrere HTTP-Anforderungen unter Verwendung derselben TCP-Verbindung gesendet werden. Dadurch wird das Ziel gezwungen, die Verbindungen offen zu halten, während gleichzeitig Platz in der Verbindungstabelle und Speicherplatz verbraucht werden.


    3. Dies kann zum Beispiel auf Seiten eines ISPs geschehen oder auch bei Firmennetzwerken




.. TODO EMail Sicherheit DMARC Rules:
    Phishing E-Mails im Umlauf

    Hallo Michael Eichberg,

    wir möchten Sie darüber informieren, dass derzeit betrügerische Phishing E-Mails im Umlauf sind.

    Diese E-Mails behaupten, dass mit der Kamera Ihres Geräts kompromittierende Aufnahmen von Ihnen gemacht wurden und Ihr Gerät durch einen Virus oder Trojaner infiziert ist. Die Absender fordern daraufhin eine Zahlung in Bitcoin und bauen durch Folgemails zusätzlichen Druck auf.

    Oftmals erwecken diese Nachrichten mit einer Absenderadresse unter Ihrer Domain den Eindruck, von STRATO oder sogar von Ihrer eigenen E-Mail-Adresse versendet worden zu sein – das ist jedoch nicht der Fall. Bitte ignorieren Sie diese betrügerischen E-Mails.

    Um Sie vor solchen Angriffen zu schützen, haben wir die DMARC-Einstellungen für Ihre Domains aktiviert. Dies sorgt dafür, dass unautorisierte E-Mails von Ihren Domains abgelehnt werden und somit gar nicht erst zugestellt werden können. Falls Sie das nicht wünschen, können Sie dies unter den DNS Einstellungen Ihrer Domain jederzeit durch einen Klick auf "Eigene DMARC-Regel" wieder rückgängig machen. Eine Anleitung dazu finden Sie in unserem FAQ-Artikel.

    Tipp: Wenn Sie eine E-Mail von STRATO erhalten haben und unsicher sind, ob diese echt ist, nutzen Sie unseren STRATO Validierungsservice, um sofort herauszufinden, ob es sich um einen Phishing-Versuch handelt.

    Bei Fragen stehen wir Ihnen gerne zur Verfügung. Sie erreichen uns wie gewohnt über unseren Hilfe & Kontakt-Bereich.

    Mit freundlichen Grüßen
    Ihr STRATO Team
