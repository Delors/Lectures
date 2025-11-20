.. meta::
    :author: Michael Eichberg
    :keywords: IDS, Firewalls
    :description lang=de: Network Security - Eine Einführung in die Sicherheit von (verteilten) Systemen
    :id: it-security-firewalls-vorlesung
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



Aspect der Sicherheit von verteilten Systemen: Firewalls
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



.. class:: new-section transition-fade

Firewalls
------------



Unabhängiges Netz - „Ideale Situation“
---------------------------------------------------

.. image:: images/firewalls/all-save.svg
    :alt: Ideale Situation
    :align: center

.. class:: incremental-list

:Vorteile:

    - keinerlei Angriffsmöglichkeiten von außen

:Nachteile:

    - kein Schutz gegen Insider
    - kein Zugang zum Internet

.. supplemental::

    Wie bereits diskutiert gibt es auch Angriffsmuster gegen Air-Gapped-Systeme. Ein Beispiel ist der Stuxnet-Wurm, der sich initial über USB-Sticks verbreitet.

    Wenn man kein Zugang zum Internet hat, dann hat man zum Beispiel kein Zugriff auf externe Dienste wie NTP und das Einspielen von Updates ist nur über Umwege möglich.




Von der Notwendigkeit des Schutzes von Rechnern
--------------------------------------------------------

.. epigraph::

    [...] Züger und sein Team hätten [...] erst kürzlich ein Experiment durchgeführt, [...]. Sie hätten einen Computer "ohne jeglichen Schutz" mit dem Internet verbunden, um zu sehen, wie lange es dauere, bis er befallen sei. Konkrete Details zur Konfiguration dieses Systems werden zwar nicht genannt, angeblich war der Rechner aber schon nach 20 Minuten infiltriert.

    -- `Golem.de 6.2.2024 <https://www.golem.de/news/iot-hacker-missbrauchen-zahnbuersten-fuer-ddos-angriffe-2402-181921.html>`__



Schutzschicht zwischen internem und externem Netz
------------------------------------------------------

.. image:: images/firewalls/firewall.svg
    :alt: Schutzschicht zwischen internem und externem Netz
    :align: center

- Kontrolle des Nachrichtenverkehrs durch Filterung
- begrenzte Isolation mit begrenztem Schutz

.. supplemental::

    Eine Firewall schafft zwischen verbundenen Netzen Sicherheitsdomänen mit unterschiedlichem Schutzbedarf. Eine wichtige Teilaufgabe ist das Ausarbeiten von Sicherheitsrichtlinien.



Realisierung von Virtual Private Networks (VPN)
------------------------------------------------------

.. image:: images/firewalls/vpn.svg
    :alt: Realisierung von Virtual Private Networks (VPN)
    :align: center

- Aufbau einer scheinbar privaten Verbindung von Firmenteilnetzen über das (öffentliche) Internet.
- Zusätzliche Verbindungsverschlüsselung zwischen den Firewalls.

.. supplemental::

    Ziel ist es aktive und passive Angriffe zu unterbinden.
    Selbst bei verschlüsselten Verbindungen kann die Verkehrsflussanalyse noch Informationen liefern über die Verbindungen liefern.


Kommerzielle VPNs für Endnutzer
---------------------------------

.. image:: images/firewalls/vpn-commercial.svg
    :alt: Einsatz von Virtual Private Networks (VPN) für Privatnutzer
    :align: center

.. supplemental::

    **Motivation**

    - Schutz der Privatsphäre; der ISP kennt nicht mehr die Webseiten, die man aufruft.
    - Die IP-Adresse des Nutzers ist den aufgerufenen Webseiten nicht mehr bekannt und kann deswegen der Umgehung von Geo-Blocking dienen.

    **Nachteile?**

    - Vertrauen in den VPN-Anbieter muss vorhanden sein. Insbesondere, beim Einsatz zum Stärken der Privatsphäre, muss der VPN-Anbieter vertrauenswürdig sein und sollte ein so genannter „no-log“ Anbieter sein.
    - Es gibt auch (scheinbar kostenlose) VPN-Anbieter, die die Daten der Nutzer dann aber verkaufen (ehemals: `Facebook Onavo <https://techcrunch.com/2019/02/21/facebook-removes-onavo/?guccounter=1&guce_referrer=aHR0cHM6Ly93d3cuZ29vZ2xlLmNvbS8&guce_referrer_sig=AQAAAGVIppEgEOd9Z0FoMbmk2TCleRmD9wCMWDmIzGYEjIo1c7Cmz8NpiSoibthFG5IZQzmZ-kiJq-5Wj1bj21byh7YUrC_aSJJk1Bapwz80GSgzLFS-LHCF2OOetUYLSKwEG7W75znuqJJBJcNTTbtJ1UGB95Yu90saK9aIIkEywcRq>`__).



Schutz auf den Schichten des TCP/IP Stacks
-----------------------------------------------------------

Zentraler Schutz des gesamten internen Netzwerks durch:

.. class:: incremental-list

- Paket Filter (:eng:`Packet Filtering`)

  .. class:: list-with-explanations

  - Blockieren bestimmter IP-Empfänger-Adressen (extern / intern)
  - Blockieren bestimmter IP-Absender-Adressen (extern / intern)

    (z. B. aus dem Internet mit internen IP-Absender-Adressen)

  - Blockieren bestimmter Dienste; ggf. nur für bestimmte IP-Adressen

- Filter auf Anwendungsebene (:eng:`Application-level Filtering`)

  .. class:: list-with-explanations

  - inhaltsbezogene Filterung der Verkehrsdaten eines Dienstes

    (z. B. Virenfilter oder Spamfilter)
  - wirkungslos bei verschlüsselten Verkehrsdaten

- Protokollierungsmöglichkeit der Kommunikation von / nach extern

.. supplemental::

    Firewalls (alleine) können die Struktur des Netzwerks nicht verbergen.



DoS Attacke auf Anwendungsebene
------------------------------------------------

.. epigraph::

    [...] Angriff auf die Kleinen

    Waren bei früheren Spamangriffen massenhaft Accounts auf der größten Mastodon-Instanz ``mastodon.social`` angelegt worden, die dann von dort ihre Inhalte verbreiteten, trifft es nun nicht die größte, sondern die kleinsten. Automatisiert werden dabei Instanzen ausgesucht, auf denen eine Registrierung ohne Überprüfung und sogar ohne ein Captcha möglich ist. Das können etwa solche mit wenigen Accounts sein, die von Enthusiasten etwa für eine Gemeinde betrieben werden. Waren die Verantwortlichen in den vergangenen Tagen nicht aufmerksam, wurden diese Instanzen dann regelrecht überrannt. Die Spam-Accounts verschickten massenhaft Nachrichten mit einem Bild des namensgebenden Frühstücksfleischs und Links zu Discord-Servern, die wohl lahmgelegt werden sollten.

    -- `Mastodon: Spamwelle zeigt Schwächen auf [...] <https://www.heise.de/news/Mastodon-Spamwelle-zeigt-Schwaechen-auf-und-weckt-Sorge-vor-schlimmerer-Methode-9632055.html>`__



Realisierungsmöglichkeiten von Firewalls
------------------------------------------------

.. class:: incremental-list

- Hardware-Firewall

  - Screening Router
  - Application Gateway (auch Bastion Host)

    - (Reverse-)Proxy-Server für bestimmte Dienste
    - Endpunkt aus Sicht von Client-Software (HTTP-Browser, EMail, ...)
    - spezialisierte Server-Software

- Software-Firewall (*Personal Firewall*)


.. supplemental::

    Im Falle eines :eng:`Bastion Host`, ist dies der einzige unmittelbar aus dem Internet erreichbare Rechner.


.. .. class:: s-vertical-title

Dual-Homed Host
----------------

.. compound::
    :class: encapsulate-floats

    .. image:: images/firewalls/dual-homed-host.svg
        :alt: Dual-Homed Host
        :align: right

    **Aufbau**

    - zwei Netzwerkkarten: ggf. private interne Adressen
    - Screening Router & Gate: Packet Filter und Application-Level Filter
    - Proxy-Dienste installieren
    - Benutzer-Logins von extern

.. important::
    :class: incremental

    Bei der Konfiguration der Netzwerkkarten gilt:

    *IP-Pakete nicht automat. weiterleiten*



Screening Router
----------------------------------------------------

.. grid::

    .. cell:: width-60

        **Aufbau**

        Programmierbarer Hardwarerouter mit simplen Filterfunktionen:

        - nur Paket-Header prüfen
        -  schnelle Auswertung ermöglicht hohen Durchsatz

        - Realisierung eines Packet Filters

        .. assessment::
            :class: incremental

            .. class:: columns

            - .. class:: positive-list incremental

                    - einfach und billig
                    - flexibel

            - .. class:: negative-list incremental

                    - schwer zu testen
                    - Protokollierung
                    - Fernwartung
                    - keine Inhaltsfilterung

    .. cell:: width-40

        .. image:: images/firewalls/screening-router.svg
            :alt: Screening Router
            :align: center




Screened Host
-----------------

.. image:: images/firewalls/screened-host.svg
    :alt: Screened Host
    :align: right

**Aufbau**

- Screening Router blockiert:

  - Pakete von / an interne Rechner (nicht Gate)
  - Source-Routed Pakete

- von extern nur Gate sichtbar
- Pakete von intern nur via Gate
- Gate bietet Proxy-Server (z. B. für E-Mail)

.. supplemental::

    *Source-Routed Pakete* sind Pakete, die den Weg durch das Netzwerk explizit angeben. (*Source-routing* wird auch als *Path Addressing* bezeichnet und wird im Allgemeinen als Sicherheitsproblem angesehen.)

    Gibt es für eine bestimmte Anwendung kein Application-level Proxy, dann kann auf einen für TCP/UDP generischen Proxy zurückgegriffen werden. Dieser arbeitet auf dem Session Layer und kann nur die Header-Informationen auswerten. Es handelt sich dann um ein :eng:`Circuit-level Proxy/Gateway`. Im Vergleich zu einem Application-level Proxy ist die Sicherheit geringer, da der Circuit-level Proxy nicht in der Lage ist, die Daten zu interpretieren.

    Ein allgemeines Problem ist, dass viele Anwendungen auf generische Protokolle wie HTTP aufsetzen. Weiterhin betreiben einige Anwendungen „Port Hopping“, d. h. sie wechseln den Port wenn der Standardport nicht offen ist.

    Eine Anforderung an „Next-generation Firewalls“ ist, dass diese die Analyse von den Daten einer Anwendung unabhängig vom Port und Protokoll ermöglichen.



Konfiguration eines Gateways
---------------------------------

Das Ziel der Konfiguration muss eine minimale angreifbare Oberfläche sein.

.. class:: incremental-list

- Abschalten aller nicht-benötigten Netzdienste
- Löschen aller nicht benötigter Programme
- Rechte von ``/bin/sh`` auf 500 setzen
- Rechte aller Systemverzeichnisse auf 711 setzen
- keine regulären Benutzerkennungen
- root-Login mit Einmal-Passwortsystem bzw. 2-Faktor Authentifizierung
- setzen von Platten- und Prozess-Quotas
- volle Protokollierung, möglichst auf Hardcopy-Gerät
- möglichst sichere, stabile und regelmäßig aktualisierte Betriebssystemversion einsetzen

.. supplemental::

    Die Rechte von ``/bin/sh`` auf 500 setzen bedeutet, dass nur der Eigentümer (root) es ausführen kann.

    Default:

    .. code:: sh

        $ ls -al /bin/sh
        -rwxr-xr-x  1 root  wheel  101232 Oct  1 06:10 /bin/sh



Screened Subnet
----------------

.. image:: images/firewalls/screened-subnet.svg
    :alt: Screened Subnet
    :align: right

**Aufbau**

.. class:: incremental-list

- interner Screening Router als weiterer Schutzwall

  - blockiert Dienste, die nicht einmal bis zum Gate gelangen sollen
  - lässt nur Pakete zum / vom Gate durch

- äußeres Netz realisiert Demilitarisierte Zone (DMZ) für HTTP-Server, Mail-Server, ...




.. class:: new-section transition-fade

Intrusion Detection Systeme (IDS)
----------------------------------




Intrusion Detection Systeme (IDS)
--------------------------------------

.. definition::

    Ein IDS ist ein Gerät (meist ein speziell konfigurierter Rechner), das vielfältige Techniken zur Erkennung von Angriffen anwendet und Angriffe meldet und ggf. abwehrt, in dem (z. B.) die Firewall automatisch umkonfiguriert wird.

.. compound::
    :class: incremental

    **Motivation**

    .. class:: incremental-list

    - Firewalls alleine sind zu statisch und deswegen häufig nicht ausreichend
    - bessere Aufzeichnung und flexiblere Erkennung notwendig
    - angepasste Reaktion notwendig

.. compound::
    :class: incremental

    **Umsetzung**

    An verschiedenen, neuralgischen Stellen werden spezielle Sensoren platziert, die (hier) den Netzwerkverkehr überwachen und verdächtige Aktivitäten melden.

.. supplemental::

    Miteinander verwandt bzw. typischerweise in einem Produkt zu finden:

    - Intrusion Detection (IDS)
    - Intrusion Response (IRS)
    - Intrusion Prevention (IPS)



IDS-Erkennungstechniken
----------------------------

.. class:: incremental-list

- Signaturerkennung
- statistische Analyse
- Anomalieerkennung


.. admonition:: Probleme
    :class: incremental

    - Fälschlicherweise gemeldete Angriffe (false positives)
    - nicht gemeldete Angriffe (false negatives) (insbesondere bei neuartigen Angriffen)
    - Echtzeitanforderung, insbesondere bei Hochgeschwindigkeitsnetzen
    - Aufzeichnung bei Netzwerken mit Switches ( ⇒ spez. SPAN Port)
    - Sensoren sollen unbeobachtbar sein (*stealth*)

.. supplemental::

    SPAN (:eng:`Switched Port Analyzer`) Ports sind spezielle Ports auf Switches, die bestimmten Verkehr (z. B. bestimmte Pakete) die über ein Switch gehen, an einen definierten Port weiterleiten können. An diesem Port kann dann eine Analyse des Verkehrs durchgeführt werden / ein Sensor angeschlossen werden.



.. class:: exercises transition-move-left

Übung
------------------

.. exercise:: Firewalls

    1. Was sind Vorteile eines Dual Homed Host gegenüber einem Paketfilter? Was sind die Nachteile?

    2. Benennen Sie zwei konzeptionelle Grenzen von Firewalls. D. h. zwei Szenarien gegen die Firewalls nicht schützen können.

    3. Für welche der folgenden Cybersicherheitsstrategien können Firewalls eingesetzt werden:

       1. Angriffe vermeiden
       2. Angriffe erkennen
       3. Angriffe abwehren/Angriffen entgegenwirken
       4. Reaktion auf Angriffe

    4. Sie werden beauftragt die Firewall so einzurichten, dass Mails mit Schadsoftware nicht durchgelassen werden. Wie reagieren Sie?

    .. solution::
        :pwd: fIREwall

        1. Ein Dual Homed Host ist ein Computer mit zwei Netzwerkschnittstellen. Zur Verwendung als Firewall wird das Routing, also die Weiterleitung von IP-Paketen zwischen den Schnittstellen, abgeschaltet. Damit können keine Pakete direkt zwischen den Netzen ausgetauscht werden und alle Verbindungen enden am Dual Homed Host. Um Daten weiterzuleiten, muss auf dem Dual Homed Host ein Proxy laufen, der eine Verbindung annimmt und eine neue Verbindung in das andere Netz aufbaut (gesteuert über Regel- und Berechtigungstabellen). Man kann über diese Application Level Gateways eine gute inhaltliche Kontrolle der übertragenen Daten durchführen, bei E-Mail beispielsweise eine Längenbegrenzung oder eine Erkennung von mitgeschickten ausführbaren Programmen, die dann automatisch geprüft oder entfernt werden könnten. Für jeden freigeschalteten Dienst benötigt man einen speziellen Proxy.

           Ein Risiko bei Dual Homed Hosts ist die Übernahme des Hosts durch einen Angreifer. Dieser hat dann über die entsprechende Netzwerkschnittstelle des Dual Homed Hosts vollständigen Zugriff auf das interne Netz.

        2. \

           - Hintertüren - sollte es Kommunikationsübergänge an der Firewall vorbei geben,  so können diese von Angreifern genutzt werden.
           - Interne Angriffe - diesbezüglich gibt es keine Unterschiede zu einem Netzwerk ohne Firewall.
           - Vertrauenswürdigkeit der Kommunikationspartner.

        3. Die Hauptaufgabe von Firewalls ist es Angriffen entgegenzuwirken (3.). Eine Reaktion auf Angriffe ist für klassische Firewalls nicht möglich. Eine Reaktion auf Angriffe ist Aufgabe von Intrusion Detection Systemen. Moderne Firewalls integrieren jedoch häufig auch Funktionen von *Intrusion Detection Systemen*. (Angriffe können nicht vermieden werden, da dies nicht in der Macht der Firewall liegt. Klassische/Einfache Firewalls können keine Angriffe erkennen.)
        4. ... die Mails sollen ja den Mailserver erreichen; eine inhaltsbasierte Beurteilung des Inhalts einer Mail ist nicht Aufgabe einer Firewall.

