.. meta:: 
    :author: Michael Eichberg
    :keywords: "TOR"
    :description lang=de: TOR 
    :id: lecture-security-tor
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
.. role:: the-blue
.. role:: minor
.. role:: ger-quote
.. role:: obsolete
.. role:: line-above

.. role:: raw-html(raw)
   :format: html


TOR - The Onion Router
===================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.rst.html>`__
:Kontakt: michael.eichberg@dhbw-mannheim.de
:Version: 1.0

.. supplemental::

  :Folien: 
      :HTML: |html-source|

      :PDF: |pdf-source|
  :Fehler auf Folien melden:
      https://github.com/Delors/delors.github.io/issues



Tor (The Onion Router)
---------------------------

.. class:: incremental

- Anwendungsunabhängiger **low-latency Anonymisierungsdienst für TCP-Verbindungen**, der den Standort und die IP des Nutzers verschleiert
- Typische Anwendung: anonymes Surfen im Internet und Instant Messaging (z. B. Briar)
- Frei und Open Source
- gegründet 2002, öffentlich nutzbar seit 2003, Code seit 2004 frei verfügbar
- Baut ein *Overlay-Netzwerk* auf
- Grundlegendes Prinzip: Onion Routing

.. container:: supplemental

    :low-latency: Die Verzögerung durch die Anonymisierung ist so gering, dass Tor für Instant Messaging und das Surfen im Internet verwendet werden kann.

    :Overlay-Netzwerk: Tor baut ein eigenes Netzwerk auf, welches auf dem Internet aufsetzt. Die Verbindungen zwischen den Tor-Knoten werden von Tor zusätzlich verschlüsselt. 



Tor - Verwendung und Sicherheit
--------------------------------

.. class:: incremental

- legale/intendierte Nutzungen: Nutzer mit allg. Datenschutzbedürfnissen, *Whistleblowers*, Dissidenten, Journalisten, ...
- illegale Zwecke (Darknet). 

  .. container:: smaller minor

    Es wird geschätzt, dass etwa 80% des Datenverkehrs im Zusammenhang mit dem Zugriff auf Kinderpornografie steht. Solche Schätzungen sind allerdings mit Vorsicht zu genießen!

- Mehrere Sicherheitslücken wurden in der Vergangenheit gefunden und geschlossen. Die Angriffe [#]_ waren:
  
  - :minor:`DoS Attacken`
  - Deanonymisierungsattacken
  - Identifikation von *Onion Services* (aka *Hidden Services*)

.. [#] `Aufstellung von Angriffen auf Tor <https://github.com/Attacks-on-Tor/Attacks-on-Tor#correlation-attacks>`__.


.. container:: supplemental

    .. epigraph::

        [Sicherheitslücke gefunden in 2013] Wenn ein einzelner Nutzer Tor über einen längeren Zeitraum [3 bis 6-Monate, abhängig von einigen Faktoren] regelmäßig nutzt, ist es fast sicher, dass er *de-anonymisiert* werden kann.
        
        [Übersetzt mit DeepL.]

        -- https://www.infosecurity-magazine.com/news/tor-is-not-as-safe-as-you-may-think/


    Surface web vs. Deep web vs. Dark web


Tor - Hintergrund
------------------

.. class:: incremental

- Die grundlegende Idee ist es eine Trennung zwischen der Quelle und dem Ziel des Datenverkehrs zu schaffen.
- Der Datenverkehr wird über *mehrere Knoten* (*Relays*) umgeleitet, die jeweils nur den vorherigen und den nächsten Knoten kennen. Der Weg den ein Datenpaket nimmt, wird als *Circuit* oder *Path* bezeichnet.
- Der Pfad wird dazu vorher ausgewählt und der gesamte Datenverkehr entsprechend des Pfades verschlüsselt.
- Tor bietet Anonymität auch für die Serverseite durch *Onion Services* (auch *Hidden Services*), die nur über eine von Tor vergebene Onion-Adresse erreicht werden können.


Tor - Bedrohungsmodel
----------------------

Tor bietet Schutz for folgenden Angreifern: Einem Angreifer dem es gelingt ...

- einen Teil der Kommunikation zu beobachten und 
- nur einen Teil der Tor-Knoten zu kontrollieren, indem er entweder einen eigenen Tor-Knoten (*Relay*\ ; früher *Onion-Router*) betreibt oder einen bereits laufenden Knoten kompromittiert.

.. admonition:: Warnung
   :class: incremental margin-top-2em

   Folgendes Szenario ist nicht abgedeckt: Ein Angreifer, der beide Enden der Kommunikation, den ``Entry Guard`` und den ``Exit Node`` überwachen kann.
   
   Gegen solche Angreifer bietet Tor keine Anonymität.

.. class:: vertical-title tiny

Tor - Aufbau
----------------

.. container:: stack

    .. container:: layer

        .. image:: images/tor/base-network.svg
            :alt: Tor - Network
            :align: center
            :height: 1120px

    .. container:: layer incremental overlay

        .. image:: images/tor/directory-authority.svg
            :alt: Tor - Directory Authority
            :align: center
            :height: 1120px

    .. container:: layer incremental overlay

        .. image:: images/tor/bridge-nodes.svg
            :alt: TOR - Bridges and Bridge Nodes
            :align: center
            :height: 1120px

    .. container:: layer incremental overlay

        .. image:: images/tor/msg.svg
            :alt: TOR - Onion Routing
            :align: center
            :height: 1120px


.. container:: supplemental

    `Spezifikation <https://spec.torproject.org>`__

    :Tor-Knoten: Rechner, die das Tor-Netzwerk bilden. Es gibt drei Arten von Tor-Knoten:

      - *Entry Nodes* (auch *Guard Nodes*): Diese Knoten sind die ersten Knoten in der Kette. Sie kennen die IP-Adresse des Clients. Sie können den Datenverkehr nicht entschlüsseln. Sie können aber sehen, dass der Datenverkehr von einem bestimmten Client kommt. 
      - *Middle Nodes*: Diese Knoten sind die mittleren Knoten in der Kette. Sie kennen weder die IP-Adresse des Clients noch die IP-Adresse des Ziels. Sie können den Datenverkehr nicht entschlüsseln. Sie können aber sehen, dass der Datenverkehr von einem bestimmten Entry Node kommt und an einen bestimmten Exit Node geht. 
      - *Exit Nodes*: Diese Knoten sind die letzten Knoten in der Kette. Sie kennen die IP-Adresse des Ziels. Sie können den Datenverkehr entschlüsseln. Sie können aber nicht sehen, von welchem Entry Node der Datenverkehr kommt. 
      - *Bridge Nodes*: Diese Knoten sind *Entry Nodes*, die nicht bzw. nicht vollständig öffentlich bekannt. Diese dienen ggf. dazu in Ländern, in denen Tor blockiert wird, den Zugang zu Tor zu ermöglichen. Sollte eine Verbindung zu einer Bridge nicht hergestellt werden können, aufgrund der Struktur der Nachrichten - zum Beispiel aufgrund der Verwendung von *Deep Packet Inspection* - dann ist es möglich diese mit Hilfe von *Pluggable Transports* zu verschleiern. 

    :Tor-Netzwerk: besteht aus mehreren tausend Tor-Knoten. Viele Knoten sind freiwillig betriebene Knoten. 

    :Circuit/Path: Ein Circuit besteht typischerweise aus drei Knoten: *Entry Node*, *Middle Node* und *Exit Node*. Mehr Knoten sind möglich, haben jedoch nur einen geringen Einfluss auf die Sicherheit. Die Übertragung der Daten zwischen diesen Knoten erfolgt verschlüsselt. In welcher Form die Daten vom *Exit Node* zum Ziel übertragen werden, ist nicht Teil von Tor. Hat der Client eine verschlüsselte Verbindung initiiert (HTTPS), dann ist auch der Datenverkehr zwischen dem Exit Node und dem Ziel (noch) verschlüsselt ansonsten nicht und der Exit Node kann den Datenverkehr lesen.

    :Directory Authority: 
    
        Knoten, die die Liste der aktiven Tor-Knoten verwalten. Diese Liste wird von allen Tor-Knoten regelmäßig in Hinblick auf das *Consensus Document* bzgl. der Knoten und deren Eigenschaften sowie Zustand abgefragt. Das *Consensus Document* wird von den *Directory Authorities* einmal pro Stunde gemeinsam erstellt und beschreibt die relevanten Eigenschaften jedes Tor-Knotens. Die Authentizität des *Consensus Document* wird durch die Signaturen der *Directory Authorities* nachgewiesen.
    
        Es gibt (Stand 2023) 9 *Directory Authorities*. 

    :`Onion Routing`:eng:: bedeutet, dass die Datenpakete mehrfach verschlüsselt werden. Jeder Tor-Knoten kann nur die Verschlüsselungsschicht entfernen, für die er den Schlüssel hat. Die Schlüssel werden mit dem Client während des Aufbaus des Circuits ausgehandelt. Es gibt für jeden Tor-Knoten einen eigenen Schlüssel und die Nachrichten werden in umgekehrter Reihenfolge der Tor-Knoten entlang des Pfades verschlüsselt. d. h. die Verschlüsselung für den Entry Node wird als letztes angewendet, da diese als erstes entfernt wird.

    :Cells: sind die Datenpakete, die zwischen den Tor-Knoten ausgetauscht werden. Cells sind immer 512Byte groß, um es unmöglich zu machen anhand der Größe der Datenpakete Rückschlüsse auf die Daten zu ziehen.

    .. admonition:: Hinweis
    
        In älteren Dokumenten wird der *Client* auch als *Onion Proxy (OP)* bezeichnet und die Tor-Knoten als *Onion Router (OR)*. Die Tor-Knoten (:eng:`Nodes`) werden auch als *Onion Relay* bezeichnet.


.. class:: vertical-title tiny

Initiierung eines Circuits (konzeptionell)
--------------------------------------------

.. image:: images/tor/circuit-creation.svg
    :alt: Initiierung eines Circuits
    :align: center
    :width: 1800px

.. container:: supplemental

    Jeder Tor-Knoten verfügt über mehrere Keys. Für den Aufbau der Verbindung werden die *Onion Keys* verwendet. Mit Hilfe dieser werden die initialen Datenpakete mittels Public-Key Kryptografie verschlüsselt. Dies wird benötigt, um den AES Key - einer pro Knoten - der für den eigentlichen Versand benötigt wird, auszuhandeln und sicher zu übertragen.

    In der Grafik wird der Aufbau eines Circuits mit zwei Tor-Knoten dargestellt. Der Client kennt die Onion Keys der Tor-Knoten (``OR1`` und ``OR2``). Die Onion Keys werden verwendet, um die *Create* Zelle zu verschlüsseln. Der Entry Node verwendet diese Onion Keys um die *Create* Zelle zu entschlüsseln und den gemeinsamen Schlüssel zu erzeugen. Um einen längeren Pfad aufzubauen, muss der Client ggf. einfach mehrere ``Extend`` Nachrichten versenden. Erhält ein Knoten eine Relay Nachricht, dann kann der Knoten diese mit dem mit ihm ausgehandelten AES Key entschlüsseln und die Nachricht weiterleiten. Er kann den Inhalt (zum Beispiel eine weitere Relay Nachricht oder eine Extend Nachricht) nicht lesen.



Tor Relays in Deutschland
----------------------------

.. image:: images/tor-metrics-relays.png
   :alt: Tor Relays gelistet von Tor Metrics Jan. 2024
   :align: center
   :height: 1050px

.. container:: supplemental

    **Flags**

    Beschreibung jedes Tor-Knotens in Hinblick auf die Rolle des Knotens im Tor-Netzwerk. Zum Beispiel: kann der Knoten als Entry Node verwendet werden? Ist der Knoten schnell genug um als Exit Node verwendet zu werden? 

    Auszug wichtiger *Flags*:

    :HSDir: Ein Router ist ein *v2 Hidden Service Directory*
    :Running: Eine Authority konnte sich innerhalb der letzten 45 Minuten mit dem Router verbinden.
    :Stable: die gewichtet Zeit zwischen zwei Fehlern (*weighted MTBF*) ist größer als 7 Tage oder größer als der Median aller aktiven Router. 
    :Valid: eine Version von Tor wird ausgeführt, die von den Authorities als aktuell angesehen wird und keine bekannten Schwachstellen aufweist.



Informationen über Tor Relays
-------------------------------

.. image:: images/tor-relay-snorlax.png
        :alt: Tor Relay Snorlax
        :align: right
        :width: 50%
        :class: picture

.. container:: smaller width-40 float-left

    .. stack::

        .. layer:: incremental

            - Viele Tor Relays werden von Freiwilligen betrieben 
            - In Deutschland gibt es viele Relays
            - Hetzner ist diesbezüglich beliebt...
            
            .. container:: incremental

                ... und deswegen steht Hetzner auf der Liste der zu `vermeidenden Hoster <https://community.torproject.org/relay/community-resources/good-bad-isps/>`__ (Stand Jan. 2024).


        .. layer:: incremental

            Ein Tor-Knoten wird as ``schnell`` (*fast*) eingestuft, wenn er aktiv ist und eine Bandbreite von mindestens 100KB/s hat oder unter den Top 7/8tel aller bekannten aktiven Router ist.
            
            Zum Vergleich: Die durchschnittliche Bandbreite in Deutschland ist 80Mbit/s (cf. `Statista <https://www.statista.com/statistics/1338657/average-internet-speed-germany/>`__).

            (Stand Jan. 2024)


.. container:: supplemental

    Pfade, die über die ganze Welt gehen verhindern, dass der ``Entry-`` und ``Exit-node`` beim gleichen Anbieter liegen.

    .. image:: images/tor-circuit.png
        :alt: Tor Circuit
        :align: center
        :class: picture

    **Jan. 2024 - zu vermeidende Hoster**:

    .. code:: text

        Frantech / Ponynet / BuyVM (AS53667)
        OVH SAS / OVHcloud (AS16276)
        Online S.A.S. / Scaleway (AS12876)
        Hetzner Online GmbH (AS24940)
        IONOS SE (AS8560)
        netcup GmbH (AS197540)
        Psychz Networks (AS40676)
        1337 Services GmbH / RDP.sh (AS210558)


Tor Exit Nodes
-----------------

Die Anzahl der Exit nodes ist deutlich kleiner (2. Jan. 2024 - 1314 Einträge) als die Anzahl der Knoten. Dies liegt daran, dass die technischen Anforderungen höher sind (z. B. stabile IP Adressen) und insbesondere daran, dass die Betreiber der ``Exit nodes`` darauf vorbereitet sein müssen ggf. (zahlreiche) Anfragen von den Behörden zu bekommen. [#]_

.. image:: images/tor-german-exit-node.png
    :alt: Deutscher Tor Exit Node von der TU Berlin
    :align: center
    :class: picture

.. container:: supplemental

    Reverse IP Lookup für 130.149.80.199 durchgeführt mit `IP Location Service <https://www.iplocation.net/ip-lookup>`__.

.. [#] `Tor Exit Node Guidelines <https://community.torproject.org/relay/community-resources/tor-exit-guidelines/>`__




Tor Relays: ``Exit Policy``
-----------------------------

Jeder ``Node`` legt in seiner ``Exit Policy`` genau fest welchen Datenverkehr weiterleiten möchte:

- Es gibt offene Exit Nodes, die alle Anfragen weiterleiten.
- Es gibt Knoten, die die Daten nur an weitere Tor-Knoten weiterleiten.
- Es gibt Knoten, die nur bestimmte Dienste (z. B. HTTPs) weiterleiten.
- Es gibt :ger-quote:`private Exit Nodes`, die nur zu einem bestimmten Netz Verbindungen aufbauen.






Onion Services/Hidden Services
-----------------------------------

- Server, die Anfragen nur aus dem Tor-Netzwerk annehmen, werden als *Onion Services* (bzw. *Hidden Services*) bezeichnet. 
- ``.onion`` ist eine *Pseudo*-Top-Level-Domain, die für Onion Services verwendet wird.
- Onion Services können nur über das Tor-Netzwerk erreicht werden. 
  
  :minor:`Onion-Adresse der New-York-Times im Tor Netzwerk: https://nytimesn7cgmftshazwhfgzm37qxb44r64ytbb2dj3x62d2lljsciiyd.onion (Aus Deutschland faktisch nicht nutzbar.)` 



Tor Browser
---------------

Standardanwendung für den Zugriff auf das Tor-Netzwerk.

.. container:: stack

    .. container:: layer clearfix
        
        .. image:: images/tor-onion-service-nyt.png
            :alt: Tor Browser mit Ney-York-Times - 01.01.2024
            :height: 848px
            :align: left

        .. container:: scriptsize

            Ergebnis nach mehreren Minuten Wartezeit und zwei Versuchen überhaupt eine Verbindung aufzubauen.

    .. container:: layer incremental

        **Sicherheitseinstellungen des Tor Browsers**       
        
        :Standard: alle Browserfunktionen sind aktiviert.
        :Sicherer: JavaScript ist auf Nicht-HTTPS-Seiten deaktiviert. Wenn JavaScript aktiviert ist, dann sind die Leistungsoptimierungen deaktiviert. Audio und Video (HTML5-Medien) sowie WebGL werden nur nach Mausklick abgespielt.
        :Sicher: (zusätzlich) JavaScript ist immer deaktiviert. Einige Schriftarten, Symbole, mathematische Symbole und Bilder sind deaktiviert.


.. container:: supplemental

    Das Tor-Netzwerk erlaubt ggf. das Setzen des ``Exit Nodes``, um zum Beispiel geografische Sperren zu umgehen. Entsprechende Dienstanbieter können dies jedoch leicht erkennen, da die Knoten des Tor Netzwerkes bekannt sind (https://check.torproject.org/torbulkexitlist) und verweigern dann den Zugriff.




Tor
----

.. class:: positive-list incremental

- Schützt vor der Analyse des Datenverkehrs. 

  Von `SecureDrop <https://securedrop.org/>`__ wird zum Beispiel für Whistleblower empfohlen sich mit dem SecureDrop Service über Tor zu verbinden und erst dann Dokumente hochzuladen.

- Tor Browser schützt relativ effektiv vor Website-Fingerprinting.

.. class:: negative-list incremental

- Teilweise sehr langsam (insbesondere bei Onion Services).
- Monitoring des Netzwerks ist an den Grenzen möglich.
- Ende-zu-Ende Korrelation von Datenverkehr ist möglich.
- Die Anonymität hängt auch von der Anzahl der Nutzer ab.


.. container:: supplemental

    *Website Fingerprinting*

    Website Fingerprinting ermöglicht es die besuchten Websites anhand des Datenverkehrs zu identifizieren. Dabei wird nicht der Inhalt der Datenpakete analysiert, sondern die statistischen Eigenschaften des Datenverkehrs. Wie groß sind die Datenpakete (d. h. die ausgelieferten Dateien)? Wie viele Datenpakete werden wann verschickt? Wie lange dauert es bis ein Datenpaket verschickt wird (d. h. Geschwindigkeit der Webseite)? Wie lange dauert es bis ein Datenpaket ankommt?

    *(Cross-)Browser Fingerprinting*

    Durch das Sammeln vieler (auch kleiner) Informationen über den/die Browser und das Betriebssystem kann ein für praktische Zwecke hinreichend eindeutiger Fingerabdruck erstellt werden. Dieser kann dann zur Identifikation des Nutzers verwendet werden.

    Kleiner Auszug aus den möglichen Informationen:

    - System Fonts
    - Werden Cookies unterstützt?
    - Betriebssystem
    - Betriebssystem Sprache 
    - Keyboard layout
    - Art/Version des Browsers
    - verfügbare Sensoren: Beschleunigungssensor, Näherungssensor, Gyroskop
    - verfügbare Browser Plugins
    - HTTP-Header Eigenschaften
    - CPU Klasse
    - HTML 5 Canvas Fingerprinting 
    - Unterstützung von Multitouch

    *Monitoring des Netzwerks an den Grenzen*

    Hat in der Vergangenheit dazu geführt, dass Nutzer von Tor-Netzwerken identifiziert werden konnten.

    *Ende-zu-Ende Korrelation von Datenverkehr* 

    Auch als *Traffic Confirmation* bekannt. Diese Art von Attacke ist möglich, wenn *Relays* am Anfang und am Ende der Verbindung kontrolliert werden. Die Angreifer können dann den Datenverkehr an beiden Enden beobachten und die Datenpakete korrelieren z. B. basierend auf statistischen Informationen über die Zeitpunkte und Volumen von Datenflüssen. 



.. class:: integrated-exercise transition-move-left

Übung: Tor
-----------

- \ 

  .. exercise:: Onion Services

    Ist es für *Onion Services* (.onion) notwendig auf HTTPS zu setzen oder reicht HTTP für eine sichere Kommunikation? Ist die Verwendung von HTTPS ggf. sogar problematisch?

    .. solution::
        :pwd: nurHTTP

        Im Allgemeinen ist es ausreichend wenn Onion Service "nur" HTTP anbieten, da der gesamte Verkehr zwischen Client und Server durch Tor verschlüsselt ist.
    
        Für Onion Services ist es sinnvoller über HTTP zu kommunizieren. HTTPS bietet keinen relevanten zusätzlichen Schutz. Auf der anderen Seite gefährdet HTTPS die Anonymität des Servers, da die TLS Zertifikate öffentlich sind und damit die Existenz des Servers preisgeben.

        https://support.torproject.org/https/https-1/

- \ 

  .. exercise:: TOR und DNS Lookups

    Warum führt der Tor Browser keine DNS Lookups durch? Warum ist dies wichtig und wer kann/muss es stattdessen machen?

    .. solution::
        :pwd: DNSLookups

        Der DNS Lookup wird nicht durch den Tor Browser durchgeführt. Der DNS Lookup wird durch den Exit Node durchgeführt.

        Ein standardmäßiger DNS Lookup würde die Anonymität des Nutzers gefährden. Der Exit Node könnte den DNS Lookup mit dem Datenverkehr des Nutzers korrelieren und damit die Identität des Nutzers ermitteln.

- \ 

  .. exercise:: TOR abschalten?
    
    Warum hätte das Abschalten von TOR auf kriminelle Aktivitäten im Internet vermutlich nur einen geringeren Einfluss?
  
    .. solution::
        :pwd: BringtNichts

        Es gibt zahlreicher weitere Dienste, die ähnliche Funktionalität bieten. Darüber hinaus haben kriminelle Organisationen ggf. die Mittel sich alternative Lösungen zu schaffen.



.. class:: integrated-exercise transition-move-left

Übung: Tor
-----------

- \ 

  .. exercise:: Wie vergleichen sich Proxies und Tor-Knoten?

    .. solution:: 
        :pwd: ProxyVsTor

        Ein Proxy ist ein Server, der als Vermittler zwischen einem Client und einem Server fungiert. Ein Tor-Knoten ist ein Server, der als Vermittler zwischen einem Client und einem Server fungiert. Ein Tor-Knoten ist also ein Proxy, aber ein Proxy ist nicht unbedingt ein Tor-Knoten.

- \ 

  .. exercise:: Wie unterscheidet sich Tor von einem VPN?
  
    .. solution:: 
        :pwd: VPNsundTOR

        Ein VPN ist ein Tunnel zwischen zwei Netzwerken. Tor ist ein Tunnel zwischen einem Client und einem Server. 
        - In beiden Fällen kennt der Ziel(webs)erver nicht die IP-Adresse des Clients.
        - Tor ist dezentralisiert und anonym. VPNs sind zentralisiert und nicht anonym; der VPN Anbieter kennt die IP-Adresse des Clients. 
        - Tor ist sehr langsam; VPNs sind schnell(er).
        - Bei Tor ist dem *Exit Node* nicht bekannt wer der Client ist; bei VPNs ist dem VPN Anbieter bekannt wer der Client ist.
        - Tor erlaubt den Zugriff auf .onion Adressen; VPNs nicht.

- \ 

  .. exercise:: Tor über VPN oder VPN über TOR?

    Macht es Sinn ein VPN über Tor oder anders herum zu betreiben?

    .. solution::
        :pwd: VPN-ueber-Tor

        Es macht nur selten Sinn ein VPN über Tor (d. h. erst TOR, dann VPN) zu betreiben. In diesem Fall ist zum Beispiel kein Zugriff auf .onion Adressen möglich. Weiterhin kennt der VPN Anbieter seine Kunden. Jedoch wird der Standort des Clients vor dem VPN Anbieter verborgen. Die Einrichtung ist jedoch kompliziert und wird ggf. vom VPN Anbieter nicht unterstützt. Möglicherweise sinnvoll wenn der Einsatz eines VPN verboten ist.

        Es macht meistens mehr Sinn Tor über ein VPN zu betreiben. In diesem Fall sieht kein TOR-Knoten die IP Adresse des Clients. Insbesondere garantiert diese Lösung die Annonymisierung, die das TOR Netzwerk bietet. Weiterhin weiss der VPN Anbieter nicht, dass der Client TOR benutzt.  

- \ 

  .. exercise:: Kontrolle über TOR Netzwerk?

    Was passiert, wenn eine Angreifer in der Lage ist :math:`50\% + 1` der ``Directory Authority`` Server zu kontrollieren?

    .. solution:: 
        :pwd: Hell!

        "Hell breaks loose." Er kann zum Beispiel auf die Entry und Exitnodes verweisen, die er kontrolliert. Damit kann er den Datenverkehr entschlüsseln. Er kann auf die Onion Services verweisen, die er kontrolliert. Damit kann er die Identität der Nutzer der Onion Services ermitteln.


.. IDEAs/TODOs/FIXMEs
   add a discussion about alle the keys used by tor and how they are initialized!