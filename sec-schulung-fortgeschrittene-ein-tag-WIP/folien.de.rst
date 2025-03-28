.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "IT Sicherheit", 
    :description lang=de: Vertiefung IT Security - IT Schulung
    :id: schulung-it-security-vertiefung
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!    

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
.. role:: peripheral
.. role:: obsolete

.. role:: raw-html(raw)
   :format: html



.. class:: animated-symbol 

IT Sicherheit - Vertiefung
=====================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.0

.. supplemental::

  :Folien: 
      |html-source|

      |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues





.. class:: new-section transition-scale

Netzwerksicherheit
-------------------



Denial-of-Service (DoS) Angriffe
------------------------------------

Ziel des Angreifers: Lahmlegen eines Dienstes oder des ganzen Systems ...

- durch Ausnutzen von Schwachstellen (:eng:`vulnerabilities`) wie z. B. Buffer Overflows
- durch Generierung von Überlast (Ausschöpfen von RAM, CPU, Netzwerkbandbreite, ...)

.. admonition:: Beispiel: Ping-of-Death
    :class: incremental smaller

    (Historisch: aus dem Jahr 1997)

    Ein ``ping`` (vgl. Internet Control Message Protocol (ICMP)) verwendet üblicherweise kleine Nachrichten, aber die verwendete Länge ist einstellbar.

    Falls die Länge zu groß ist ⇒ Buffer Overflow ⇒ Systemabsturz!
    
    Variante: mittels Fragmentierung ließen sich generell übergroße IP-Pakete (>65,536 Byte) erstellen.



Distributed Denial-of-Service (DDoS) Angriff
------------------------------------------------

Opfer wird von sehr vielen Angreifern mit Nachrichten überflutet.

.. container:: incremental

    Ein Beispiel: Smurf-Angriff:

    .. image:: images/smurf-angriff.svg 
        :alt: Smurf Angriff
        :align: center




Distributed-Reflected-Denial-of-Service Angriff
------------------------------------------------------------

.. grid:: 

    .. cell:: width-40

      - Idee eines (DRDoS) Angriffs:

        .. class:: list-with-explanations

        - Es wird eine Anfrage an einen Server gesendet, die eine große Antwort auslöst. 
      
          (Z. B. hat(te) der NTP Monlist Befehl eine Antwort, die ca. 200 Fach größer ist als die Anfrage!)
        - Mittels IP-Spoofing wird die IP-Adresse des Opfers als Absenderadresse verwendet.
        
        .. class:: incremental

        - Es werden insbesondere Dienste basierend auf UDP verwendet, da hier keine Verbindung aufgebaut werden muss.

    .. cell::

        .. image:: images/drdos.svg 
            :alt: DRDoS Angriff
            :align: center

.. class:: incremental-list

- Nehmen einen signifikanten Teil aller DDoS-Angriffe ein. 
- Die Tatsache, dass die Sender legitime Server sind, erschwert die Abwehr.
- :eng:`Egress Filtering` kann helfen, die Verwendung von IP-Spoofing zu verhindern.     


.. supplemental::
    
    Bereits im Jahr 2018 wurde ein Angriff mit einer Bandbreite von 1,7 TBit/s beobachtet.

    :Egress Filtering: Der Router verwirft alle Pakete, die eine Absenderadresse verwenden, die nicht aus dem eigenen Netzwerk stammt. 


`Distributed Denial-of-Service (DDoS) Angriffe - Beispiel <https://cloud.google.com/blog/products/identity-security/google-cloud-mitigated-largest-ddos-attack-peaking-above-398-million-rps>`__
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

.. epigraph::

    [...] Google's DDoS Response Team has observed the trend that distributed denial-of-service (DDoS) attacks are **increasing exponentially in size**. Last year, we blocked the largest DDoS attack recorded at the time. This August [2023], we stopped an even larger DDoS attack — 7½ times larger — that also used new techniques to try to disrupt websites and Internet services.

    This new series of DDoS attacks reached **a peak of 398 million requests per second (rps)**, and relied on a novel HTTP/2 “Rapid Reset” technique based on stream multiplexing that has affected multiple Internet infrastructure companies. By contrast, last year's largest-recorded DDoS attack peaked at 46 million rps.



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
  
  .. class:: incremental

  - Whitelisting von IP-Adressen von besonders wichtigen Clients
  - Blacklisting von IP-Adressen aus bestimmten Bereichen
  - Captchas
  - Überprüfung der Browser-Echtheit
  
- Anti-DDos Appliances 

.. admonition:: Achtung
    :class: warning incremental

    Diese Maßnahmen sind häufig teuer und ggf. begrenzt effektiv; wenn der Angriff die verfügbare Bandbreite übersteigt, sind diese Maßnahmen darüber hinaus wirkungslos.



Schutz vor DDoS-Angriffen: Off-Site Maßnahmen
------------------------------------------------------------

.. class:: incremental list-with-explanations
  
- Einbinden des ISP
- Einbinden spezialisierter Dienstleister 

  (Im Angriffsfall wird mittels BGP-Rerouting der Traffic an den Dienstleister umgeleitet, der dann die DDos Attacke filtert.)
- Content-Delivery-Networks (CDNs) für statische Inhalte (z. B. Cloudflare, Akamai, ...)
- Distributed Clouds


