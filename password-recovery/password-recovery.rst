.. meta:: 
    :author: Michael Eichberg
    :keywords: "IT Sicherheit", Passwortwiederherstellung
    :description lang=de: Fortgeschrittene Angewandte IT Sicherheit
    :id: 2023_11-w3wi_se403_passwort_wiederherstellung
    :first-slide: last-viewed

.. |date| date::
.. |at| unicode:: 0x40

.. role:: incremental   
.. role:: eng
.. role:: ger
.. role:: red
.. role:: green
.. role:: blue
.. role:: minor
.. role:: ger-quote
.. role:: obsolete
.. role:: line-above
.. role:: huge
.. role:: xxl

.. role:: raw-html(raw)
   :format: html


Passwortwiederherstellung 
=====================================================

.. container:: line-below 

     a.k.a. Password-Cracking

.. class:: footnotesize margin-above

:Dozent: **Prof. Dr. Michael Eichberg**
:Kontakt: michael.eichberg@dhbw-mannheim.de
:Version: |date|


Vorerfahrungen?
-------------------

.. class:: incremental more-space-between-list-items

- Wer hat schon einmal Passworte wiederhergestellt?
- Wer hat Erfahrung mit Linux?
- Wer hat Erfahrung mit Linux Kommandozeilenwerkzeugen für die Textverarbeitung?
- Wer hat Erfahrung mit regulären Ausdrücken?
- Wer hat Erfahrung mit Python?
- Wer hat Erfahrung mit Java (Reverse Engineering)?


Was ist Passwortwiederherstellung?
----------------------------------

Passwortwiederherstellung ist der Prozess, der dazu dient ein nicht (mehr) vorhandenes Passwort wiederzuerlangen.

.. topic:: Haftungsausschluss
    :class: line-above red incremental

    .. epigraph::
    
        Wer unbefugt sich oder einem anderen Zugang zu Daten, die nicht für ihn bestimmt und die gegen unberechtigten Zugang besonders gesichert sind, unter Überwindung der Zugangssicherung verschafft, wird mit Freiheitsstrafe bis zu drei Jahren oder mit Geldstrafe bestraft.

        -- § 202 a Abs. 1 StGB


Ist Passwortwiederherstellung noch relevant?
----------------------------------------------

.. container:: three-columns 

    .. container:: column no-separator
    
        .. image:: logos/android.webp
            :class: incremental
            :align: center
            :height: 575px
            :alt: Android Smartphone

    .. container:: column no-separator

        .. image:: logos/cryptostick.jpg
            :class: incremental
            :align: center
            :alt: Cryptostick
            :height: 400px

        .. class:: center-text-in-div incremental
        
            :xxl:`🛜`

    .. container:: column 

        .. image:: logos/filevault.webp
            :class: incremental
            :align: center
            :height: 300px
            :alt: Filevault Logo

        .. image:: logos/veracrypt.webp 
            :class: incremental
            :align: center
            :height: 200px
            :alt: Veracrypt Logo

.. container:: incremental margin-above line-above

    Klassische Passwörter werden (noch immer) in zahlreichen Bereichen verwendet. Beispiele sind Smartphones, Cryptosticks, Logins für Computer und Serversysteme, verschlüsselte Dateien und Datenträger.

.. container:: supplemental
    
    **Hintergrund**

    Obwohl an vielen Stellen versucht wird Passwörter aus vielen Gründen zurück zu drängen, so ist die Verwendung noch allgegenwärtig und in machen Bereichen ist auch nicht unmittelbar eine Ablösung zu erkennen.

    Biometrie ist zum Beispiel in machen Bereichen kein Ersatz für Passwörter und wird - wenn überhaupt - nur ergänzend genommen. So ist es zum Beispiel im deutschen Recht erlaubt/möglich einem Beschuldigten sein Smartphone bei Bedarf vor das Gesicht zu halten, um es zu entsperren. Je nach Qualität des Fingerabdrucksensors können ggf. auch genommene Fingerabdrücke verwendet werden. Möchte der Beschuldigte jedoch das Passwort nicht freiwillige nennen, dann besteht keine direkte weitere Handhabe.


Ist Passwortwiederherstellung nicht :ger-quote:`trivial`?
----------------------------------------------------------

.. image:: logos/home_security_heroes.webp 
    :align: center
    :height: 800
    :class: shadow
    :alt: An AI just cracked your password.


:line-above:`An AI just cracked your password.`

`Home Security Heroes <https://www.homesecurityheroes.com/ai-password-cracking/assets/pdf/hsh-an-ai-just-cracked-your-password-infographic.pdf>`__


Wiederherstellung von Passwörtern mit unterschiedlicher Komplexität
--------------------------------------------------------------------

Beurteilen Sie die Qualität der folgenden Passwörter in Hinblick darauf wie aufwändig es ist das Passwort wiederherzustellen:

.. class:: incremental smaller

1. Donaudampfschifffahrt
2. Passwort
3. ME01703138541
4. 2wsx3edc4rfv
5. Haus Maus 
6. iluvu
7. Emily18
8. MuenchenHamburg2023!!!!
9. hjA223dn4fw"üäKßß k`≤-~ajsdk
10. Baum Lampe Haus Steak Eis Berg
11. password123 


.. class:: center-elements-on-slide

\ 
----

.. admonition:: Warnung
    :class: incremental warning 
    
    Es ist nie eine Option Passwörter im Klartext zu speichern.


.. class:: new-section transition-fade

Kryptografische Hashfunktionen und Passwörter
----------------------------------------------


Hashfunktionen (Wiederholung)
-------------------------------

.. class:: incremental

- Eine Hashfunktion :math:`H` akzeptiert eine beliebig lange Nachricht :math:`M` als Eingabe und gibt einen Wert fixer Größe zurück: :math:`h = H(M)`.
- Wird oft zur Gewährleistung der Datenintegrität verwendet. Eine Änderung eines beliebigen Bits in :math:`M` sollte mit hoher Wahrscheinlichkeit zu einer Änderung des Hashwerts :math:`h` führen.
- Kryptographische Hashfunktionen werden für Sicherheitsanwendungen benötigt. Mögliche Anwendungen:
  
  - :minor:`Authentifizierung von Nachrichten`
  - :minor:`Digitale Signaturen`
  - Speicherung von Passwörtern


Beispiel: Berechnung von MD5 Hashwerten
-------------------------------------------------------

.. class:: monospaced

:: 

    md5("Hello") = 8b1a9953c4611296a827abf8c47804d7
    md5("hello") = 5d41402abc4b2a76b9719d911017c592
    md5("Dieses Passwort ist wirklich total sicher 
          und falls Du es mir nicht glaubst, dann
          tippe es zweimal hintereinander blind 
          fehlerfrei ein.") 
                 = 8fcf22b1f8327e3a005f0cba48dd44c8

.. admonition:: Warnung
    :class: warning incremental margin-above

    Die Verwendung von MD5 dient hier lediglich der Illustration. In realen Anwendung sollte MD5 auf keinen Fall mehr verwendet werden.

.. container:: supplemental

    **Kollisionen bei Hashes**

    Wenn ein Passwort :ger-quote:`nur` als Hash gespeichert wird, dann gibt es zwangsläufig Kollisionen und es könnte dann theoretisch passieren, dass ein Angreifer (zufällig) ein völlig anderes Passwort findet, dass bei der Überprüfung des Passworts akzeptiert wird. Die Konstruktion kryptografischer Hashfunktionen stellt jedoch sicher, dass dies in der Praxis nicht auftritt.


Sicherheitsanforderungen an kryptografische Hashfunktion I
-------------------------------------------------------------------------

:Pseudozufälligkeit: Die Ausgabe von :math:`H` erfüllt die Standardtests für Pseudozufälligkeit.

.. class:: incremental 

:Einweg Eigenschaft: 
    
    Es ist rechnerisch/praktisch nicht machbar für einen gegeben Hashwert :math:`h` ein :math:`N` zu finden so dass gilt: :math:`H(N) = h`

    (:eng:`Preimage resistant; one-way property`)


Sicherheitsanforderungen an kryptografische Hashfunktion II
-------------------------------------------------------------------------

:Schwache Kollisionsresistenz: 

    Es ist rechnerisch nicht machbar für eine gegebene Nachricht M eine Nachricht N zu finden so dass gilt: :math:`M \neq N` mit :math:`H(M) = H(N)` 

    (:eng:`Second preimage resistant; weak collision resistant`)

.. class:: incremental

:Starke Kollisionsresistenz: 
    
    Es ist rechnerisch unmöglich ein paar :math:`(N,M)` zu finden so dass gilt: :math:`H(M) = H(N)`. 

    (:eng:`Collision resistant; strong collision resistant`)

.. container:: supplemental

    **Hintergrund**

    Im Deutschen wird auch von Urbild-Angriffen gesprochen. In dem Fall ist *preimage resistance* (d.h. die Einweg Eigenschaft) gleichbedeutend damit, dass man nicht effektiv einen :ger-quote:`Erstes-Urbild-Angriff` durchführen kann. Hierbei ist das Urbild die ursprüngliche Nachricht :math:`M`, die *gehasht* wurde.

    *Second preimage resistance* ist dann gleichbedeutend damit, dass man nicht effektiv einen :ger-quote:`Zweites-Urbild-Angriff` durchführen kann. Es ist nicht möglich zu einer Nachricht M eine zweite Nachricht N (d.h. ein zweites Urbild) zu finden, die für eine gegebene Hashfunktion den gleich Hash aufweist.


Beziehung zwischen den Sicherheitsanforderungen an Hashfunktionen
------------------------------------------------------------------

.. image:: graffles/beziehung_zwischen_eigenschaften_von_hashfunktionen.svg 
    :alt: Beziehung zwischen den Eigenschaften von Hashfunktionen
    :align: center
    :width: 1200px


Anforderungen an die Resistenz von Hashfunktionen
---------------------------------------------------

.. csv-table::
    :header: "", Preimage Resistant, Second Preimage Resistant, Collision Resistant
    :class: smaller highlight-line-on-hover incremental
    
    Hash + Digitale Signaturen, ✓, ✓, ✓
    Einbruchserkennung und Viruserkennung, , ✓ , 
    Hash + Symmetrische Verschlüsselung, , , 
    Passwortspeicherung, ✓, , 
    MAC, ✓, ✓, ✓

.. container:: supplemental
    
    **Hintergrund**

    Ein Kollisionsangriff erfordert weniger Aufwand als ein *preimage* oder ein *second preimage* Angriff.

    Dies wird durch das Geburtstagsparadoxon erklärt. Wählt man Zufallsvariablen aus einer Gleichverteilung im Bereich von :math:`0` bis :math:`N-1`, so übersteigt die Wahrscheinlichkeit, dass ein sich wiederholendes Element gefunden wird, nach :math:`\sqrt{N}` Auswahlen :math:`0,5`. Wenn wir also für einen m-Bit-Hashwert Datenblöcke zufällig auswählen, können wir erwarten, zwei Datenblöcke innerhalb von :math:`\sqrt{2^m} = 2^{m/2}` Versuchen zu finden.

    .. admonition:: Beispiel
        :class: smaller

        Es ist relativ einfach, ähnliche Meldungen zu erstellen. Wenn ein Text 8 Stellen hat, an denen ein Wort mit einem anderen ausgetauscht werden kann, dann hat man bereits :math:`2^{8}` verschiedene Texte.

        Es ist relativ trivial(1), vergleichbare(2) Nachrichten(3) zu schreiben(4). Wenn ein Text 8 Stellen hat, an denen ein Ausdruck(5) mit einem vergleichbaren (6) ausgetauscht werden kann, dann erhält(7) man bereits :math:`2^{8}` verschiedene Dokumente(8).


Effizienzanforderungen an kryptografische Hashfunktionen
------------------------------------------------------------------------

:Effizienz bei der Verwendung für Signaturen und zur Authentifizierung:

  Bei der Verwendung zur Nachrichtenauthentifizierung und für digitale Signaturen ist :math:`H(N)` für jedes beliebige :math:`N` relativ einfach zu berechnen. Dies soll sowohl Hardware- als auch Softwareimplementierungen ermöglichen.

.. container:: incremental

    .. container:: center-text-in-div bold huge
        
        vs.

    :Brute-Force-Angriffe auf Passwörter erschweren:

        Bei der Verwendung für das Hashing von Passwörtern soll es schwierig sein den Hash effizient zu berechnen, selbst auf spezialisierter Hardware (GPUs, ASICs).


Kryptografische Hashfunktionen für Passworte
----------------------------------------------------------------------

.. class:: incremental smaller more-space-between-list-items

- Bekannte kryptografische Hash-Funktionen: :obsolete:`MD4`, :obsolete:`MD5`, SHA-256, SHA-512, RIPE-MD, ...
- Bekannte Funktion zur Schlüsselableitung: PBKDF2, ...
- Beim Hashing von Passwörtern werden die Basisalgorithmen in der Regel mehrfach (ggf. viele hunderttausend Male) angewendet, um die Laufzeit zu verlängern und es für Angreifer schwieriger zu machen.
- Mehrere Hash-Algorithmen/Schlüsselableitungsfunktionen wurden ausdrücklich für das Hashing von Passwörtern entwickelt, um gängigen Angriffen zu widerstehen. Z.B. bcrypt, scrypt, Argon2.
- Einige dieser Algorithmen sind so rechenintensiv, dass sie nicht für Webanwendungen/Situationen geeignet sind, in denen viele Benutzer gleichzeitig autorisiert werden müssen. Diese Algorithmen werden in der Regel zum Schutz von Dateien, Containern oder lokaler Festplatten verwendet.


Vom Salzen (:eng:`Salt`) ...
-----------------------------

.. container:: small
    
    **Beobachtung/Problem**

    Werden Passwörter direkt mit Hilfe einer kryptografischen Hashfunktion gehasht, dann haben zwei Nutzer, die das gleiche Passwort verwenden, den gleichen Hash.

    .. csv-table::
        :class: tiny
        :header: User, Hash

        Alice, "sha256_crypt.hash('\ **DHBWMannheim**\ ',salt='',rounds=1000) =
        ``$5$rounds=1000$$lb/CwYgN/xR9dqYuYxYVtWkxMEh.VK.QOC9IKmy9DP/``"
        Bob, "sha256_crypt.hash('\ **DHBWMannheim**\ ',salt='',rounds=1000) =
        ``$5$rounds=1000$$lb/CwYgN/xR9dqYuYxYVtWkxMEh.VK.QOC9IKmy9DP/``"

.. container:: incremental small 
    
    **Lösung**  

    Passwörter sollten immer mit einem einzigartigen und zufälligen :ger-quote:`Salt` gespeichert werden, um Angriffe mittels Regenbogentabellen zu verhindern. 

    .. csv-table::
        :class: tiny
        :header: User, Hash

        Alice, "sha256_crypt.hash('\ **DHBWMannheim**\ ',\ :red:`salt='0123456'`\ ,rounds=1000)
        ``$5$rounds=1000$0123456$66x8MB.qev25coq9OVrD1lr1ZGJJelAzOVlCDZykrY0``"

        Bob, "sha256_crypt.hash('\ **DHBWMannheim**\ ',\ :red:`salt='1234567'`\ ,rounds=1000)
        ``$5$rounds=1000$1234567$LxD/hg29N9KYpNdFMW69Kk65BLkVLlzlSEJvqhCmFU9``"


.. container:: supplemental
    
    **Regenbogentabellen**

    Eine Regenbogentabelle (:eng:`rainbow table`) bezeichnet eine vorberechnete Tabelle die konzeptionell zum einem Hash ein jeweilig dazugehörendes  Passwort speichert und einen effizienten Lookup ermöglicht. Dies kann ggf. die Angriffsgeschwindigkeit sehr signifikant beschleunigen.

    Aufgrund der allgemeinen Verwendung von Salts sind Angriffe mit Hilfe von Regenbogentabellen heute nur noch von historischer Bedeutung.


Vom Salzen (:eng:`Salt`)...
----------------------------

.. class:: incremental more-space-between-list-items

- Ein *Salt* sollte ausreichend lang sein (zum Beispiel 16 Zeichen oder 16 Byte).
- Ein *Salt* darf nicht wiederverwendet werden.
- Ein *Salt* kann zum Beispiel (am Anfang oder) am Ende an das Passwort angehängt werden bevor selbiges gehasht wird.
- Ein *Salt* unterliegt (eigentlich) keinen Geheimhaltungsanforderungen. 

.. admonition:: Problem 
    :class: incremental small

    Sollte es einem Angreifer gelingen in eine Datenbank einzubrechen und die Tabellen mit den Nutzerdaten abzufragen (zum Beispiel aufgrund einer erfolgreichen SQL Injection), dann ist es Ihm danach direkt möglich zu versuchen Passworte wiederherzustellen.

.. container:: supplemental

    **Speicherung von Salts**

    In Webanwendungen bzw. allgemein datenbankgestützten Anwendungen wird der *Salt* häufig in der selben Tabelle gespeichert in der auch der Hash des Passworts gespeichert wird. Im Falle von verschlüsselten Dateien, wird der Salt (unverschlüsselt) mit in der Datei gespeichert.


... und Pfeffern (:eng:`Pepper`) von Passwörtern
------------------------------------------------

.. container:: small

    (In Normen/Teilen der Literatur wird statt *Pepper* auch *Secret Key*\ s verwendet.)

.. class:: incremental more-space-between-list-items

- Wie ein *Salt* geht auch der *Secret Key* in den Hashvorgang des Passworts ein.
- Der *Secret Key* wird jedoch **nicht** mit den Hashwerten der Passworte gespeichert.
- Ein *Secret key* kann zum Beispiel in einem Hardwaresicherheitsmodul (z.B. Secure Element oder TPM Chip) gespeichert werden. Gel. wird der *Secret Key* bzw. ein Teil davon auch im Code gespeichert.
- Wie ein Salt sollte auch auch *Secret Key* mind. 16 Byte lang sein, um ggf. ein Brute-Force Angriff auf den *Secret Key* zu verhindern sollte dem Angreifer zu einem Hash und Salt auch noch das Klartext Passwort bekannt sein.
- Der *Secret Key* sollte zufällig sein. 
- Der *Secret Key* sollte pro Instanziierung einer Anwendung einmalig sein. 


`PBKDF2 <https://datatracker.ietf.org/doc/html/rfc2898.html#section-5.2>`__ (Password-Based Key Derivation Function 2)
----------------------------------------------------------------------------------------------------------------------------

.. class:: incremental more-space-between-list-items

- Dient der Ableitung eines Schlüssels aus einem Passwort. 
- Das Ergebnis der Anwendung der PBKDF2 wird zusammen mit dem *Salt* und dem Iterationszähler für die anschließende Passwortverifizierung gespeichert.
- die :math:`PBKDF2` Schlüsselableitungsfunktion hat 5 Parameter :math:`DK = PBKDF2(`\ PRF, Password, Salt, c, dkLen\ :math:`)`:

    :PRF: Eine Pseudozufallsfunktion; typischer Weise ein HMAC.
    :Password: Das Masterpasswort.
    :Salt: der zu verwendende Salt.
    :c: Zähler für die Anzahl an Runden.
    :dkLen: Die Bitlänge des abgeleiteten Schlüssels.


.. container:: supplemental
    
    Die PBKDF2 ist nicht für das eigentliche Hashen zuständig sondern :ger-quote:`nur` für das Iterieren der Hashfuntion und das eigentliche Key-stretching.

    Laut OWASP sollten zum Beispiel für PBKDF2-HMAC-SHA512 600.000 Iterationen verwendet werden.



HMAC (Hash-based Message Authentication Code)
----------------------------------------------

.. container:: small

    Auch als *keyed-hash message authentication code* bezeichnet.

    .. math::

        \begin{array}{rcl}
        HMAC(K,m) & = & H( (K' \oplus opad) || H( ( K' \oplus ipad) || m) ) \\
        K' & = &\begin{cases}
                H(K) & \text{falls K größer als die Blockgröße ist}\\
                K & \text{andernfalls}
                \end{cases}
        \end{array}
    
    :math:`H` is eine kryptografische Hashfunktion.

    :math:`m` ist die Nachricht.

    :math:`K` ist der geheime Schlüssel (*Secret Key*).

    :math:`K'` ist vom Schlüssel K abgeleiteter Schlüssel mit Blockgröße (ggf. *padded* oder *gehasht*).

    :math:`||` ist die Konkatenation.

    :math:`\oplus` ist die XOR Operation.

    :math:`opad` ist das äußere Padding bestehend aus Wiederholungen von 0x5c in Blockgröße.

    :math:`ipad` ist das innere Padding bestehend aus Wiederholungen von 0x36 in Blockgröße.


\ 
----------------------------------------------

.. image:: graffles/hmac_i_o_key_derivation.svg
        :alt: Schlüsselableitung für den inneren und äußeren Schlüssel K'
        :align: left
        :width: 1400px

.. image:: graffles/hmac_message_hashing.svg
        :alt: Schlüsselableitung für den inneren und äußeren Schlüssel K'
        :align: right
        :width: 1300px
        :class: incremental margin-above

.. container:: supplemental

    **Padding und Hashing**

    Im Rahmen der Speicherung von Passwörtern und *Secret Keys* ist die Verwendung von Padding Operationen bzw. das Hashing von Passwörtern, um Eingaben in einer wohl-definierten Länge zu bekommen, üblich. Neben dem hier gesehenen Padding, bei dem 0x00 Werte angefügt werden, ist zum Beispiel auch das einfache Wiederholen des ursprünglichen Wertes, bis man auf die notwendige Länge kommt, ein Ansatz. 
    
    Diese Art Padding darf jedoch nicht verwechselt werden mit dem Padding, dass ggf. im Rahmen der Verschlüsselung von Nachrichten notwendig ist, um diese ggf. auf eine bestimmte Blockgröße zu bringen (zum Beispiel bei ECB bzw. CBC Block Mode Operations.)



HMAC Computation in Python
---------------------------
    
**Implementierung**

.. code:: python
    :class: small

    import hashlib
    pwd = b"MyPassword"
    stretched_pwd = pwd + (64-len(pwd)) * b"\x00" 
    ikeypad = bytes(map(lambda x : x ^ 0x36 , stretched_pwd)) # xor with ipad 
    okeypad = bytes(map(lambda x : x ^ 0x5c , stretched_pwd)) # xor with opad 
    hash1 = hashlib.sha256(ikeypad+b"JustASalt"+b"\x00\x00\x00\x01").digest()
    hmac  = hashlib.sha256(okeypad+hash1).digest()


.. container:: incremental small

    **Ausführung**

    .. code:: python

        hmac =
        b'h\x88\xc2\xb6X\xb7\xcb\x9c\x90\xc2R...
          \x16\x87\x87\x0e\xad\xa1\xe1:9\xca'


.. container:: supplemental
    
    HMAC ist auch direkt als Bibliotheksfunktion verfügbar.

    .. code:: python
        :class: black

        import hashlib
        import hmac
        
        hash_hmac = hmac.new(
            b"MyPassword",
            b"JustASalt"+b"\x00\x00\x00\x01",
            hashlib.sha256).digest()

        hash_hmac = 
            b'h\x88\xc2\xb6X\xb7\xcb\x9c\x90\xc2R...
              \x16\x87\x87\x0e\xad\xa1\xe1:9\xca'


PBKDF2-HMAC (Hash-based Message Authentication Code)
-----------------------------------------------------

Im Fall von PBKDF2 ist der Schlüssel :math:`K` also das Passwort und die Nachricht :math:`M` das Salz.

**Beispielcode**

.. code:: python
    :class: black smaller

    from passlib.crypto.digest import pbkdf2_hmac
    pbkdf2_hmac( "sha256",
        secret=b"MyPassword",
        salt=b"JustASalt",
        rounds=1,   # a real value should be >> 500.000
        keylen=32 )

.. class:: incremental footnotesize

    Bei einer Runde und passenden Blockgrößen ist das Ergebnis der PBKDF2 somit gleich mit der Berechnung des HMACs wenn der Salt um die Nummer des Blocks ``\x00\x00\x00\x01`` ergänzt wurde.

.. container:: supplemental

    In der konkreten Anwendung ist es ggf. möglich das *Secret* auch zu Salzen und den *Salt* aus einer anderen Quellen abzuleiten.


.. class:: transition-move-left integrated-exercise

Nachgehakt
-----------

.. container:: conditions 

    Ihnen liegt eine externer Festplatte/SSD mit USB Anschluss vor, die die folgenden Eigenschaften hat:

    - Die Daten auf der SSD/FP sind hardwareverschlüsselte Festplatte
    - Die Verschlüsselung erfolgt mit XTS-AES 256
    - Es gibt eine spezielle Software, die der Kunde installieren muss, um das Passwort zu setzen. Erst danach wird die Festplatte :ger-quote:`freigeschaltet` und kann in das Betriebssystem eingebunden werden. Davor erscheint die SSD/FP wie ein CD Laufwerk auf dem die Software liegt.
    - Die SSD/FP ist FIPS zertifiziert und gegen Hardwaremanipulation geschützt; zum Beispiel eingegossen mit Epox. 
    - Das Passwort wird von der Software gehasht und dann als Hash an den Controller der externen FP/SSD übertragen. 
    - Im Controller wird der übermittelte Hash direkt zur Autorisierung des Nutzers verwendet. Dazu wird der Hash mit dem im EPROM hinterlegten verglichen.

.. container:: task 

    Wie bewerten Sie die Sicherheit des Produkts?

.. container:: supplemental exercise-solution

    **Lösung**

    Wie in (A) dargestellt, ist die Sicherheit hier ausschließlich durch den Hardwareschutz gegeben. Sobald der Hash aus dem EPROM ausgelesen werden konnte, ist keine Sicherheit mehr gegeben. In (B) wird der Hash erst auf dem Gerät berechnet. Die gleiche Attacke würde hier nicht mehr funktionieren, da ein Übertagen des Hashes zu einem Hashen des Hashes führen würde und danach der Test fehlschlagen würde.

    .. image:: graffles/unsichere_passwort_validierung.svg 
        :alt: Unsichere Passwortvalidierung
        :width: 800px
        :align: center



.. class:: new-section transition-fade

Passwortwiederherstellung 101
------------------------------


Passwortwiederherstellung
-------------------------

.. class:: incremental impressive

1. Wissen wo/in welcher Form der Passworthash zu finden ist.
2. Extraktion des Hashes 
3. Wiederherstellung des Passwortes durch das systematische Durchprobieren aller Kandidaten. 


Beispiel - Wiederherstellung eines Linux Login Passwortes
---------------------------------------------------------

.. code:: bash
    :class: incremental

    ~% sudo cat /etc/shadow      
    [...]
    john:$6$zElzjLsMqi36JXWG$FX2Br1/[...]. ↩︎
    RxAHnNCBsqiouWUz751crHodXxs0iqZfBt9j40l3G0:19425:0:99999:7:::
    [...]

.. code:: bash
    :class: incremental line-above
    
    % echo -n '$6$zElzjLsMqi36JXWG$FX2Br1/[...]. ↩︎
    RxAHnNCBsqiouWUz751crHodXxs0iqZfBt9j40l3G0' > hash.txt 

.. code:: bash
    :class: incremental line-above
    
    % hashcat -m 1800 hash.txt -a 3 '?d?d?d?d?d?d'

.. container:: supplemental

    **Finden eines Hashes**

    Im Falle von Linux Login Passworten ist genau spezifiziert wo die Passworte (``/etc/shadow``) und in welcher Form die Passworte gespeichert werden. Nach dem Namen des Nutzers (im Beispiel ``john``) ist der verwendete Hashingalgorithmus vermerkt. Dieser unterscheidet sich zwischen den Distributionen. Aktuell setzen die meisten Distributionen jedoch auf yescrypt. Danach folgen die Parameter. Insbesondere der Salt.

    .. csv-table::
        :header: ID, Mode

        $5$, Sha256crypt (veraltet)
        $6$, SHA512crypt (in Ablösung)
        $y$ (or $7$), yescrypt


Systematisches Testen aller Kandidaten 
---------------------------------------------------------------

konzeptionell führt die Software Hashcat die folgenden Schritte durch:

.. container:: monospaced small

  <extracted_hash> =? SHA512crypt("zElzjLsMqi36JXWG","000000") ✘

  .. class:: incremental

    <extracted_hash> =? SHA512crypt("zElzjLsMqi36JXWG","000001") ✘

  .. class:: incremental

    <extracted_hash> =? SHA512crypt("zElzjLsMqi36JXWG","000002") ✘

  .. class:: incremental

    <extracted_hash> =? SHA512crypt("zElzjLsMqi36JXWG","000003") ✘

  .. class:: incremental

    <extracted_hash> =? SHA512crypt("zElzjLsMqi36JXWG","000004") ✘

  .. class:: incremental

    <extracted_hash> =? SHA512crypt("zElzjLsMqi36JXWG","000005") ✘

  .. class:: incremental

    <extracted_hash> =? SHA512crypt("zElzjLsMqi36JXWG","000006") ✘

  .. class:: incremental

    <extracted_hash> =? SHA512crypt("zElzjLsMqi36JXWG","000007") ✘

  .. class:: incremental

    <extracted_hash> =? SHA512crypt("zElzjLsMqi36JXWG","000008") ✘

  .. class:: incremental

    ...

    <extracted_hash> == SHA512crypt("zElzjLsMqi36JXWG","123456") ✔︎


.. container:: supplemental 

    Der folgende Code könnte als Grundlage genutzt werden, um das Passwort wiederherzustellen.
    (Linux nutzt standardmäßig 5000 Runden.)

    .. code:: python

        from passlib.hash import sha512_crypt

        sha512_crypt.hash("123456",salt="zElzjLsMqi36JXWG",rounds=5000)



.. class:: new-section transition-scale

Passworte Verstehen
-------------------


Aufbau von Passworten
-----------------------

Von Menschen vergebene Passwörter basieren häufig auf Kombinationen von Wörtern aus den folgenden Kategorien:

.. class:: incremental more-space-between-list-items

- Pins: 1111, 1234, 123456, …
- Tastaturwanderungen (:eng:`keyboard walks`): ``asdfg``, ``q2w3e4r5t``, …
- Patterns: aaaaa, ababab, abcabcabc, …
- Reguläre Wörter aus Wörterbüchern: Duden, Webster, …
- Kontextinformationen:
  
  - Szenespezifisch: ``acab``, …
  - Privates Umfeld: Namen von Kindern, Eltern, Hunden, Geburtsort, Adresse, …


Häufige Passworte
--------------------------

Eine gute Quelle für das Studium von Passwörtern sind sogenannte *Leaks* oder auch Listen mit gängigen Passwörtern. Zum Beispiel `Becker's Health IT 2023 <https://www.beckershospitalreview.com/cybersecurity/30-most-common-passwords-of-2023.html>`__:


.. container:: three-columns small 

    .. container:: compact-ps

        123456

        password
        
        123456789
        
        12345
        
        12345678
        
        qwerty
        
        1234567
        
        111111
        
        1234567890
        
        123123

    .. container:: compact-ps
    
        abc123

        1234
        
        password1
        
        iloveyou
        
        1q2w3e4r
        
        000000
        
        qwerty123
        
        zaq12wsx
        
        dragon
        
        sunshine
        
    .. container:: compact-ps

        princess

        letmein

        654321
        
        monkey
        
        27653
        
        1qaz2wsx
        
        123321
        
        qwertyuiop
        
        superman
        
        asdfghjkl

.. container:: supplemental

    **Hinweise**

    - Die Listen ändern sich in der Regel von Jahr zu Jahr nicht wesentlich.
    - Die Methodik ist oft fragwürdig.


Herausforderung: Hashraten auf aktueller Hardware
----------------------------------------------------

.. csv-table::
    :class: incremental scriptsize no-table-borders
    
    **Hashcat Mode (Hashcat 6.2.6)**, **Hash**, "**RTX 1080Ti
    250 W**", "**RTX 2080TI 
    260 W**", "**RTX 3090 
    350 W**", "**RTX 4090 
    450 W**"
    25700, Murmur, , , , "643700.0 (643 GH/s)"
    23, Skype, 21330.1 , 27843.1 , 37300.7 , 84654.8 
    1400, SHA2-256, 4459.7 , 7154.8 , 9713.2 , 21975.5 
    10500, PDF1.4-1.6, 24.9 , 29.8 , 76.8 ,  122.0 
    1800, SHA 512 Unix (5000 Iterations), 0.2 , 0.3  , 0.5 , 1.2 
    13723, Veracrypt SHA2- 512 + XTX 1536Bit, 0.0004 , 0.0006  , 0.0009 , "0.002
    (2000 H/s)"

.. container:: tiny

    **Quellen:**

    :4090: https://gist.github.com/Chick3nman/e4fcee00cb6d82874dace72106d73fef
    :3090: https://gist.github.com/Chick3nman/e4fcee00cb6d82874dace72106d73fef
    :1080Ti: https://www.onlinehashcrack.com/tools-benchmark-hashcat-nvidia-gtx-1080-ti.php
    :2080Ti: https://gist.github.com/binary1985/c8153c8ec44595fdabbf03157562763e

Herausforderung: Unmöglichkeit eines Brute-Force Angriffs auf Luks2
-------------------------------------------------------------------

.. image:: dgraphs/brute_force-luks2.svg
    :width: 900px
    :align: center
    :alt: Zeit die für einen Angriff auf LUKS2 benötigt wird.


Herausforderung: Unmöglichkeit eines Brute-Force Angriffs auf lange Passworte
------------------------------------------------------------------------------

.. image:: dgraphs/brute_force.svg
    :width: 1820px
    :align: center
    :alt: Zeit für für Angriffe auf lange Passwörter benötigt wird.



Herausforderung: stets neue Algorithmen
--------------------------------------------------

.. epigraph:: Angriff auf LUKS2 mit Argon2

    […] The choice of Argon2 as a KDF makes GPU acceleration impossible. As a result, you’ll be restricted to CPU-only attacks, which may be very slow or extremely slow depending on your CPU. To give an idea, you can try 2 (that’s right, two) passwords per second on a single Intel(R) Core(TM) i7-9700K CPU @ 3.60GHz. Modern CPUs will deliver a slightly better performance, but don’t expect a miracle: LUKS2 default KDF is deliberately made to resist attacks. […]

    -- Elcomsoft `Luks2 with Argon2 <https://blog.elcomsoft.com/2022/08/probing-linux-disk-encryption-luks2-argon-2-and-gpu-acceleration>`__



.. class:: transition-move-left integrated-exercise 

Gedankenexperiment
---------------------

.. container:: conditions 

    Sie wollen einen SHA 256 angreifen und sie haben 100 Nvidia 4090 GPUs. Jede GPU hat eine Hash-Rate von ~22GH/s (mit Hashcat 6.2.6) und benötigt ~500 Watt. Der verwendete Zeichensatz besteht aus 84 verschiedenen Zeichen (z.B. a-z, A-Z, 0-9, <einige Sonderzeichen>).

.. container:: task 

    Wie lange dauert es, ein 10-stelliges Passwort zu ermitteln (Worst Case)?

.. container:: task 

    Wie viel Geld wird es Sie kosten, ein 10-stelliges Passwort zu knacken (worst case) (1kW ~ 0,25ct)?

.. container:: task 

    Werden Sie im Laufe Ihres Lebens in der Lage sein, ein Passwort mit 12 Zeichen zu ermitteln?

.. container:: supplemental exercise-solution

    Es dauert ca. 3 Monate (~2200 Stunden), um ein Passwort mit 10 Ziffern zu ermitteln!
        
    Es wird im schlimmsten Fall 27.000€ Stromkosten verursachen.

    Um ein Passwort mit 11 Ziffern zu finden, brauchen Sie 21 Jahre (worst-case).

    Um ein Passwort mit 12 Ziffern zu finden, brauchen Sie 21*84 Jahre (worst case).


.. class:: transition-move-left integrated-exercise 

Gedankenexperiment
---------------------


.. container:: transition-move-left conditions

    Sie haben ganz viele Grafikkarten und einen sehr schnellen Hash. Sie kommen auf eine Hashrate von 1THash/Sekunde (:math:`1 \times 10^{12}`). Sie haben einen Monat Zeit für das Knacken des Passworts.
    Gehen Sie davon aus, dass Ihr Zeichensatz 100 Zeichen umfasst. 


.. container:: task

    Berechnen Sie den Anteil des Suchraums, den Sie abgesucht haben, wenn das Passwort 32 Zeichen lang sein sollte. Drücken Sie den Anteil des abgesuchten Raums in Relation der Sandkörner der Sahara aus. Gehen Sie davon aus, dass die Sahara ca. 70 Trilliarden (:math:`70 \times 10^{21}`) Sankörner hat.

.. admonition:: Lösung
    :class: supplemental exercise-solution

    Suchraum: :math:`100^{32} = 10^{64}` 

    Passworte in einem Monat: 
    
    .. math:: 
        
        (1 \times 10^{12} {H \over s}) \times 60sec \times 60min \times 24h \times 30d = \

        2.592.000.000.000.000.000 = \

        2,592 \times 10^{18} \
    
    da:

    .. math:: 

        2.592.000.000.000.000.000 \times (4 \times 10^{45}) \approx 10^{64}

    haben wir somit ca.
    
    .. math::

        1 \over 4 \times 10^{45}

    des Suchraums berechnet. Da die Sahara wohl nur ca. :math:`7 \times 10^{22}` Sandkörner hat, haben wir somit nicht mal ansatzweise ein Sandkorn berechnet. Sondern lediglich: :math:`1,75 \times 10^{-23}`.

    Oder ganz praktisch ausgedrückt: wir sind - je nach Theroie - frühestens **nach dem Ende des Universums** fertig - `Ende des Universums <https://www.welt.de/kmpkt/article177740494/Weltall-Wie-das-Ende-des-Universums-aussehen-koennte.html#:~:text=Das%20All%20dehnt%20sich%20immer,Gut%20zu%20wissen!>`__.


Herausforderung: Passwortrichtlinien 
---------------------------------------------------------------

Moderne Passwortrichtlinien (:eng:`Password Policies`) machen es unmöglich, ältere Leaks *direkt* zu nutzen.

*Beispiele:*

.. class:: incremental more-space-between-list-items

- Mindestanzahl von Zeichen (maximale Anzahl von Zeichen)
- Anforderungen an die Anzahl der Ziffern, Sonderzeichen, Groß- und Kleinbuchstaben
- Anforderungen an die Vielfalt der verwendeten Zeichen
- einige Passwörter (z. B. aus bekannten Leaks und Wörterbüchern) sind verboten
- ...

.. container:: supplemental

    Passwortrichtlinien extrem: `Password Game <https://neal.fun/password-game/>`__

    Die wichtigsten `NIST-Richtlinien <https://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-63b.pdf>`__ für Passwörter:

    - Mindestlänge von 8 Zeichen.
    - Keine Komplexitätsanforderung. Benutzer sollten auch die Möglichkeit haben, Leerzeichen einzufügen, um die Verwendung von Phrasen zu ermöglichen. Für die Benutzerfreundlichkeit [...] kann es von Vorteil sein, wiederholte Leerzeichen in getippten Passwörtern vor der Überprüfung zu entfernen.


Die Struktur von Passwörtern verstehen
-----------------------------------------------------------

.. container:: scriptsize
    
    Analyse auf Grundlage des :ger-quote:`berühmten` Rockyou-Lecks.

    Hier haben wir alle Kleinbuchstaben auf l, Großbuchstaben auf u, Ziffern auf d und Sonderzeichen auf s abgebildet.

.. csv-table::
    :align: left
    :width: 100%
    :class: compact-cells tiny no-inner-borders
    
    llllllll, "4,8037%", lllllllldd, "1,4869%", dddddddddddd, "0,2683%", ddddddll, "0,1631%"
    llllll, "4,1978%", lllllld, "1,3474%", lllddddd, "0,2625%", lllllls, "0,1615%"
    lllllll, "4,0849%", llllllld, "1,3246%", lllllllllldd, "0,2511%", ddddlll, "0,1613%"
    lllllllll, "3,6086%", llllllllllll, "1,3223%", llllllllllllllll, "0,2340%", dlllllll, "0,1583%"
    ddddddd, "3,4003%", llldddd, "1,2439%", lllldddddd, "0,2322%", dllllll, "0,1575%"
    dddddddddd, "3,3359%", llllldddd, "1,2109%", llddddd, "0,2270%", llllddddd, "0,1560%"
    dddddddd, "2,9878%", lllllldddd, "1,1204%", uuuuuudd, "0,2189%", dddddddl, "0,1557%"
    lllllldd, "2,9326%", lllllllld, "1,1168%", ddddll, "0,2169%", uuuudd, "0,1551%"
    llllllllll, "2,9110%", lllllddd, "1,0633%", lddddddd, "0,2064%", lllllddddd, "0,1395%"
    dddddd, "2,7243%", llllllddd, "0,9225%", ddddddddddddd, "0,2017%", ddllllll, "0,1391%"
    ddddddddd, "2,1453%", llllllllld, "0,9059%", ullllldd, "0,1930%", ulllll, "0,1379%"
    llllldd, "2,0395%", lllll, "0,8793%", ddddllll, "0,1905%", uuuuuuuuuu, "0,1378%"
    llllllldd, "1,9092%", lllllllllllll, "0,8334%", uuuuuuuuu, "0,1886%", llllllls, "0,1374%"
    lllllllllll, "1,8697%", llllld, "0,8005%", uuuuudd, "0,1815%", lllllllllld, "0,1345%"
    lllldddd, "1,6420%", llllddd, "0,7759%", lllllllllddd, "0,1808%", llllllllllldd, "0,1344%"
    lllldd, "1,5009%", ddddddddddd, "0,7524%", llllllllldddd, "0,1725%", …, …


Die Zusammensetzung von Passwörtern verstehen
----------------------------------------------

.. container:: small

    Analyse des *rockyou* Leaks.

    .. csv-table::
        :class: tiny no-table-borders no-inner-borders incremental
        :header: ∑ Passworte, 14.334.851, "100%"

        Pins, 2.346.591, "16,37%"
        Passworte mit Buchstaben, 11.905.977, "83,34%"

    .. container:: incremental

        Analyse der Passworte mit Buchstaben:

        .. csv-table::
            :class: tiny no-table-borders no-inner-borders incremental
            :header: "Kategorie", "Absolut", "Prozentual", "Beispiele"
            
            
            Emails, 26.749, "0,22%", me\ |at|\ me.com, , , 
            Zahlen gerahmt von Buchstaben, 35696, "0,30%", a123456a, , , 
            Leetspeak, 64.672, "0,54%", G3tm0n3y, , , 
            Patterns, 124.347, "1,04%", lalala, , , 
            Reguläre oder Populäre Wörter, 4.911.647, "**41,25%**", princess, iloveu, , 
            Sequenzen, 5.290, "0,04%", abcdefghij, , , 
            keyboard walks (de/en), 14.662, "0,12%", q2w3e4r, , , 
            Einfache Wortkombinationen, 535.037, "4,49%", pinkpink, sexy4u, te amo, 
            Komplexe Wortkombinationen, 5.983.259, "**50,25%**", Inparadise, kelseylovesbarry, , 
            *<Rest>*, 204.618, "1,72%", j4**9c+p, i(L)you, p\ |at|\ 55w0rd, sk8er4life


.. admonition:: Hinweis
    :class: supplemental

    Die Sprachen, die bei der Identifizierung der Wörter berücksichtigt wurden, waren: "de, en, fr, es, pt, nl".
    
    *Populäre Wörter* sind Wörter, die auf Twitter oder Facebook verwendet wurden, z. B. "iloveu", "iluvu", ....


Der Effekt von Passwortrichtlinien auf Passwörter
--------------------------------------------------

Reale Passwortrichtlinie: 

    Nutze 1 Großbuchstabe, 1 Kleinbuchstabe, 2 Symbole, 2 Ziffern, 4 Buchstaben, 4 Nicht-Buchstaben

.. container:: incremental

    Exemplarisch beobachteter Effekt wenn die Passwörter vorher einfacher waren und der Benutzer gezwungen wurde diese zu erweitern:

        Password11##

        Password12!! 
    
    D.h. die Passworte werden mit möglichst geringem Aufwand erweitert.


Aufbau von Passwörtern - Zusammenfassung
------------------------------------------

.. class:: more-space-between-list-items

- Passwörter, die häufig eingegeben werden müssen, basieren in den allermeisten Fällen auf :ger-quote:`echten` Wörtern.
- Echte Wörter werden oft nicht unverändert verwendet, sondern nach einfachen Regeln umgewandelt, z. B. durch Anhängen einer Zahl oder eines Sonderzeichens, Veränderung der Groß-/Kleinschreibung, etc.

.. admonition:: Frage 
    :class: incremental small
    
    Wie können wir gute Passwortkandidaten identifizieren/generieren, wenn ein *Leak* nicht ausreicht oder nur eine kleine Anzahl von Passwörtern getestet werden kann?
    
    .. container:: incremental

        Zum Beispiel dauert das Testen aller Passwörter von Rockyou...:
        
        ~13.000.000 Passworte / 5 Hashes/Sekunde ≈ 1 Monat

        ~13.000.000 Passworte / 5 Hashes/Stunde ≈ ~297 Jahre


Herausforderungen beim Testen/Generieren von Passwörtern
---------------------------------------------------------------

Aufgrund der :ger-quote:`Unmöglichkeit` eines Brute-Force-Angriffs stellen sich folgende Herausforderungen:

.. class:: incremental

- Verfügbare *Kontextinformationen sollten in die Auswahl/Generierung einfließen*.
- Es sollten nur *technisch sinnvolle* Passwörter getestet/generiert werden. 
- Es sollten *keine Duplikate* getestet werden.
- Auswahl/Generierung von *Passwörten in absteigender Wahrscheinlichkeit*.
- :minor:`Die Auswahl/Generierung sollte effizient sein.`

.. container:: supplemental
    
    Technisch sinnvolle Passwörter sind solche, die die zu Grunde liegenden Passwortrichtlinien und auch weiteren technischen Anforderungen erfüllen. Zum Beispiel den von der Software verwendeten Zeichensatz (UTF-8, ASCII, ...) oder im Falle eines Smartphones/Krytosticks die eingebbaren Zeichen.

    Sollte der Algorithmus zum Generieren der Passwörter langsamer sein als die Zeit, die benötigt wird, um ein Passwort zu falsifizieren, dann beschränkt nicht mehr länger nur die Hashrate den Suchraum.


Ansätze und Werkzeuge zum Generieren von Passwortlisten
---------------------------------------------------------- 
    
- Grundlegende Werkzeuge zum :ger-quote:`vermischen von Wörtern` (:eng:`Word-mangling`)
   - Prince
   - Markov-Modelle (OMEN)
   - Hashcat 
   - ...

.. container:: incremental

    Um vorhandene Kontextinformationen zu erweitern, können ggf. (frei) verfügbare Wordembeddings verwendet werden.

    - `RelatedWords.org <https://relatedwords.org/relatedto/Ferrari>`__ setzt (unter anderem) auf ConceptNet und WordEmbeddings.
    - `Reversedictionary.org <https://reversedictionary.org/wordsfor/Manhattan>`__ setzt auf WordNet und liefert ergänzende Ergebnisse.


Markov-Ketten
------------------

.. admonition:: Hintergrund
    :class: note smaller incremental 

    Eine Markov-Kette beschreibt eine Sequenz möglicher Ereignisse in welcher die Wahrscheinlichkeit des Nächsten nur vom Zustand des vorherigen abhängt.

OMEN lernt - zum Beispiel basierend auf Leaks - die Wahrscheinlichkeiten für das Aufeinanderfolgen von Bigrammen und Trigrammen und nutzt diese, um neue Passwortkandidaten zu generieren.

.. admonition:: Grundlegende Idee
    :class: small incremental

    Gegeben: ``lachen, Sachen, Last, Muster``

    Bigramme: ``2*la, 2*ch, 2*en, sa, 2*st, mu, er``
    
    Auf ein ``st`` folgt entweder ein ``er`` oder ``<Wortende>``; demzufolge ist ``laster`` ein Kandidat, aber auch ``must``. 
    

Password Cracking Using Probabilistic Context-Free Grammars [PCFG]_
-----------------------------------------------------------------------

.. class:: incremental smaller

- Lernt die Muster, Worte, Ziffern und verwendeten Sonderzeichen basierend auf der Auswertung von realen Leaks. Die gelernte Grammatik wird als Schablone verwendet und aus :ger-quote:`Wörterbüchern` befüllt. (Zum Beispiel: ``S → D1L3S2 → 1L3!! → 1luv!!`` ) 
- Generiert Passwortkandidaten mit absteigender Wahrscheinlichkeit.
  
- Prozeß:
   1. Vorverarbeitung, um die Basisstrukturen und deren Wahrscheinlichkeiten zu identifizieren (z.B. zwei Ziffern gefolgt von einem Sonderzeichen und 8 Buchstaben.)
   2. Passwortkandidatengenerierung unter Beachtung der Wahrscheinlichkeiten der Basisstrukturen und der Wahrscheinlichkeiten der Worte, Ziffern und Sonderzeichen. 
   
      :minor:`(In der Originalversion wurden die Wahrscheinlichkeiten von Worten nicht beachtet; die auf GitHub verfügbare Version enthält jedoch zahlreiche Verbesserungen.)``


PCFG - Analyse - Beispiel
------------------------------------------------------------------------------------

.. container:: small
    
    Im ersten Schritt werden die Produktionswahrscheinlichkeiten von Basisstrukturen, Ziffernfolgen, Sonderzeichenfolgen und Alpha-Zeichenfolgen ermittelt. (Z. Bsp.:
    ``!cat123`` :math:`\Rightarrow` S\ :sub:`1`\ L\ :sub:`3`\ D\ :sub:`3`)

    .. csv-table::
        :header: Basis Struktur, Häufigkeit, Wahrscheinlichkeit der Produktion
        :widths: 200  200 400
        :class: smaller highlight-line-on-hover 

        L3S1D3, 12788, 0.75
        S1L3D3, 2789, 0.35

    .. csv-table::
        :header: S1, Häufigkeit, Wahrscheinlichkeit der Produktion
        :widths: 200  200 400
        :class: smaller highlight-line-on-hover 
        
        !, 12788, 0.50
        ., 2789, 0.30
        |at|, 1708, 0.20

    .. csv-table::
        :header: L3, Häufigkeit, Wahrscheinlichkeit der Produktion
        :widths: 200  200 400
        :class: smaller highlight-line-on-hover 

        cat, 12298, 0.85
        dog, 2890, 0.15

    .. csv-table::
        :header: D3, Häufigkeit, Wahrscheinlichkeit der Produktion
        :widths: 200  200 400
        :class: smaller highlight-line-on-hover 

        123, 10788, 0.60
        321, 5789, 0.35
        654, 4708, 0.25


PCFG - Generierung - Beispiel
-------------------------------------------------------------------------------------

.. container:: small margin-above

    **Ergebnis der Analyse**:

    .. admonition:: Hinweis 
        :class: note small
        
        Nicht-Terminale = ``[S,T]``

        Terminale = ``[a, b, c, d, e, …, z, 0, …, 9]``

    .. csv-table::
        :header: "Nich-Terminale", "Produktion", "Wahrscheinlichkeit der Produktion"
        :class: small highlight-line-on-hover 

        S, ``password``\ T, 0.7
        S, ``secure``\ T, 0.3
        T, ``123``, 0.6
        T, ``111``, 0.4

    .. container:: incremental 
        
        **Ableitung**:

        1. S :math:`\Rightarrow` ``password``\ T :math:`\Rightarrow` ``password123``
        2. S :math:`\Rightarrow` ``password``\ T :math:`\Rightarrow` ``password111``
        3. S :math:`\Rightarrow` ``secure``\ T :math:`\Rightarrow` ``secure123``
        4. S :math:`\Rightarrow` ``secure``\ T :math:`\Rightarrow` ``secure111``

PCFG+
--------------

:Next Gen PCFG Password Cracking [NGPCFG]_:

    Unterstützt Tastaturwanderungen (zum Beispiel asdf oder qwerty12345), Passworte bestehend aus mehrerern Worten und wiederholten Worten (zum Beispiel qpqpqpq).

.. class:: incremental line-above margin-above

:On Practical Aspects of PCFG Password Cracking [PAofPCFG]_:
    
    Im Wesentlichen Performanceoptimierungen, um PCFG schneller zu machen.

.. class:: incremental line-above margin-above

:Using personal information in targeted grammar-based probabilistic password attacks [PIandPCFG]_:

    Im Wesentlichen werden zwei PCFGs gewichtet zusammengeführt (0 < alpha < 1).


SePass: Semantic Password Guessing Using k-nn Similarity Search in Word Embeddings [SePass]_
---------------------------------------------------------------------------------------------

Zusätzliche Wortkandidaten werden mithilfe von *Worteinbettungen* identifiziert. Ermöglicht es, automatisch verwandte Wörter zu finden.

**Example**

.. container:: two-columns smaller

    .. container:: column

        Gegeben:

            :green:`Ferrari`\ 01
        
            !\ :green:`Audi`\ !
        
            :green:`Mercedes`\ 88
        
            :green:`Bugatti` 666

    .. container:: column

        "Offensichtliche" Kandidaten für Basiswörter:

            .. container:: incremental

                Porsche
        
                Mclaren

                Lamborghini

                Aston Martin



SePass: Semantic Password Guessing Using k-nn Similarity Search in Word Embeddings
-----------------------------------------------------------------------------------

Vermeidet menschliche Voreingenommenheit.

**Example**

.. container:: two-columns smaller

    .. container:: column

        Gegeben:

            :green:`Luke`\ 2017

            :green:`John`\ 1976

            01\ :green:`Mark`\ !

    .. container:: column

        "Offensichtliche" Kandidaten für Basiswörter:

            .. container:: incremental

                Matthew
        
                Bible

                Gospel

SePass: Semantic Password Guessing Using k-nn Similarity Search in Word Embeddings
-----------------------------------------------------------------------------------

Vermeidet menschliche Voreingenommenheit.

**Example**

.. container:: two-columns smaller

    .. container:: column

        Gegeben:

            :green:`Luke`\ 2017

            :green:`John`\ 1976

            01\ :green:`Mark`\ !

    .. container:: column

        "Offensichtliche" Kandidaten für Basiswörter:

            .. container:: incremental

                Leia
        
                Darth Vader

                Palpatine                



Bewertung von Passworten
---------------------------

.. container:: tiny

    :Donaudampfschifffahrt: Ist weder in Rockyou noch im Duden und auch nicht in den Corpora von Twitter und Facebook von 2022 zu finden.
    :Passwort: Nr. 93968 in Rockyou.
    :password123: Nr. 1348 in Rockyou.
    :2wsx3edc4rfv: So nicht in Rockyou, aber 1qaz2wsx3edc4rfv ist Nr. 143611 in Rockyou.
    :Haus Maus: In Rockyou ist lediglich hausmaus zu finden.
    :iluvu: Nr. 1472 in Rockyou.
    :Emily060218: Emily ist Nr. 35567 in Rockyou. Die Zahl ist ganz offensichtlich ein Datum: 6. Feb. 2018 und könnte ein Geburtsdatum, Hochzeitsdatum, oder ein für die Person vergleichbar bedeutends Datum sein.
    :MuenchenHamburg2023!!!!: Das Passwort ist zwar sehr lang aber es handelt sich vermutlich um zwei - für die entsprechende Person - bedeutende Orte. Die Zahl und die Sonderzeichen sind vermutlich auf eine Passwortrichtlinie zurückzuführen. 
    :hjA223dn4fw"üäKßß k`≤-~ajsdk: 28 Stellen basierend auf einem Zeichensatz, der vermutlich ca. 192 Zeichen pro Stelle umfasst. 
    :Baum Lampe Haus Steak Eis Berg: Es handelt sich um ein Passwort mit 30 Stellen, dass vermutlich mit Hilfe von Diceware generiert wurde und 6 Worte umfasst. 
    :ME01703138541: Namenskürzel und Telefonnummer.
    
.. container:: supplemental

    .. container:: foundations the-blue-background
        
        **Diceware**

        Auch wenn dem Angreifer
        (a) bekannt ist, dass das Passwort mit Hilfe von Diceware generiert wurde, 
        (b) die zugrundeliegende Wortliste vorliegt und 
        (c) auch die Länge (hier 6 Worte) bekannt sein sollte, dann umfasst der Suchraum: :math:`(6^5)^6 \approx 2,21\times 10^{23}` Passwortkandidaten. Sollte man also mit einer Geschwindigkeit von 1 Billion Hashes pro Sekunde angreifen können, dann brauch man noch immer über 7000  Jahre für das Absuchen des vollständigen Suchraums.
    
        Beim klassischen Dicewareansatz umfasst das Wörterbuch :math:`6^5` Worte, da man mit einem normalen Würfel fünfmal Würfelt und dann das entsprechende Wort nachschlägt. Würde man zum Beispiel die folgenden Zahlen würfeln: 1,4,2,5,2. Dann würde man das Wort zur Zahl: 14252 nachschlagen.

    .. container:: foundations the-blue-background  
        
        **Zeichensatz**

        Auf einer deutschen Standardtastatur für Macs können in Kombination mit :ger-quote:`Shift`, :ger-quote:`Alt` und :ger-quote:`Alt+Shift` zum Beispiel 192 verschiedene Zeichen eingegeben werden ohne auf Unicode oder Zeichentabellen zurückgreifen zu müssen.


Wörterbuchgenerierung - Evaluation von Werkzeugen
--------------------------------------------------------

.. image:: passwortwiederherstellung-evaluation.png 
    :alt: Evaluation verschiedener Ansätze für die Passwortevaluation
    :align: center
    :width: 1200px


.. class:: new-section transition-scale

Werkzeuge und Methoden zur Wiederherstellung von Passwörtern
--------------------------------------------------------------


.. class:: new-subsection

Grundlegende Werkzeuge
-------------------------

- `Linux Shell <../shell/shell.rst.html>`__

- `Reguläre Ausdrücke <../regexp/regexp.rst.html>`__


.. class:: new-subsection transition-fade

Extraktion von Hashes
----------------------

.. container:: supplemental

    **Hinweis**

    Im Folgenden diskutieren wir nur exemplarisch die Extraktion einiger Hashes, um das grundlegende Vorgehen zu besprechen. Im Allgemeinen gibt es für weit(er) verbreitete Software häufig bereits Lösungen zur Hashextraktion. Falls nicht, dann muss man Googeln und/oder Reverse Engineering betreiben.


Quellen für Werkzeuge & Anleitungen
------------------------------------

- `Hashcat Tools <https://github.com/hashcat/hashcat/tree/master/tools>`__
- `John (the Ripper) <https://github.com/openwall/john>`__
- Googeln


Relevante Linux Kommandozeilenwerkzeuge
----------------------------------------

.. class:: incremental

- ``file`` dient der Ermittlung des Typs einer Datei.
- ``binwalk`` durchsucht Binärdateien in Hinblick auf das Vorkommen bekannter Muster (insbsondere Dateiheader, aber auch Kryptokonstanten etc.) 
  
  ``-E`` kann zur Visualisierung der Entropie verwendet werden.

- ``dd`` kopiert Daten blockweise von einem Startpunkt in einer Datei in eine andere Datei. Wird ggf. zum Extrahieren von Hashes benötigt.
- ``xxd`` und ``hexdump`` erstellen beide einen Hexdump einer Datei.


Verschlüsselte PDF Dateien 
----------------------------

Extraktion erfolgt (zum Beispiel) mit den John Tools:

.. code:: bash
    
    $ pdf2john Document.pdf > Document.pdf.john
    $ cat Document.pdf.john
    Document.pdf:$pdf$4*4*128*-3392*1*16*861da8b9c1672ddc3953dee025
    5d622d*32*301d0810078c5698ab17b286e2123070000000000000000000000
    00000000000*32*c038ddb8fbdaeb67b6e80e2d936108fc851ff40c5b652c71
    97bda4f797939532

Danach kann der Hash entweder direkt mit John angegriffen werden, oder nach dem Entfernen des Headers mit Hashcat.


.. code:: bash

    $ pdf2john Document.pdf \
      | sed -E "s/^[^:]+://"        # Dateiname entfernen
      > Document.pdf.hashcat


Libreoffice Dateien
----------------------

Extraktion des Basishashes erfolgt auch hier (zum Beispiel) mit den John Tools. Danach muss sowohl der Prefix als auch der Suffix, der für die Entschlüsselung nicht relevant ist, abgeschnitten werden, wenn im Folgenden Hashcat verwendet werden soll.

.. code:: bash

    $ libreoffice2john Document.odt 
      | sed -E -e 's/[^:]+://' -e 's/:::::[^:]+$//' 
      > Document.odt.hashcat

.. container:: incremental 

    Um zu verstehen, wie der Hash genau auszusehen hat, ist es im Allgemeinen hilfreich sich die erwartete Struktur für einen Hash anzusehen: `Hashcat - Example Hashes <https://hashcat.net/wiki/doku.php?id=example_hashes>`__


Verschlüsselte Mac Disk Images (.dmg)
---------------------------------------

In diesem Fall hat nur John (the Ripper) Unterstützung für den konkreten Hash.

.. code:: bash

    $ dmg2john Container.dmg > Container.dmg.john   # Extraktion

.. code:: bash
    :class: incremental 
        
    $ john Container.dmg.john \                     # Angriff 
      --wordlist=/usr/share/wordlists/rockyou.txt   



Verschlüsselter USB Stick (APFS Volume)
-----------------------------------------

Es liegt ein normaler USB Stick vor auf dem eine Partition vom Typ ``Apple APFS`` ist.

.. class:: smaller

::

    Disk /dev/sda: 14.45 GiB, 15518924800 bytes, 30310400 sectors
    Disk model: Flash Disk      
    Units: sectors of 1 * 512 = 512 bytes
    Sector size (logical/physical): 512 bytes / 512 bytes
    I/O size (minimum/optimal): 512 bytes / 512 bytes
    Disklabel type: gpt
    Disk identifier: 1D63D8AE-7CBC-47BE-9093-8469B0786EAF

    Device      Start      End  Sectors  Size Type
    /dev/sda1      40   409639   409600  200M EFI System
    /dev/sda2  409640 30310359 29900720 14.3G Apple APFS


Verschlüsselter USB Stick (APFS Volume)
-----------------------------------------

1. Installation von `apfs2hashcat <https://github.com/Banaanhangwagen/apfs2hashcat>`__ (umfasst das Kompilieren der Sourcen)
2. Hash extrahieren durch "Copy-and-Paste" aus dem Logfile/der Konsole.

   .. code:: bash

      $ sudo ./apfs-dump-quick \
        /dev/sda2 \   # /dev/sda2 ist die Ziel APFS Partition
        /tmp/log.txt 


3. Hash angreifen 
   
   .. code:: bash
      :class: incremental

      $ hashcat -m 18300 fv2.hashcat \
        /usr/share/wordlists/rockyou.txt



.. class:: new-subsection  transition-fade

Passwortwiederherstellung mit Hashcat
--------------------------------------


Hashcat - Einführung
--------------------

Hashcat ist - Stand 2023 - das Tool zum Wiederherstellen von Passwörtern.

Liest ein(e Liste von) Hash(es) ein und prüft, ob einer der angegebenen Passwortkandidaten nach dem Hashen mit einem gegeben Hash übereinstimmt.

.. class:: incremental

- unterstützt über 350 Hash-Typen (mit einigen automatischen Erkennungen)
- unterstützt mehrere Angriffsmodi, z.B., 
	- Wörterbuch (ggf. mit Regeln)
	- Masken
	- Kombinationen aus Wörterbüchern und Masken
	- <Lesen von Passwortkandidaten aus stdin>
- Open-Source 
- Kann zum Generieren von neuen Kandidaten verwendet werden.
- ist CUDA/OpenCL basiert und **auf entsprechenden Grafikkarten extrem schnell**.


Hashcat - relevante Parameter
------------------------------

.. container:: two-columns smaller

    .. container:: column 

        Angriffsmodi:

        .. code:: bash

          -a0 Angriff mit Wörterbuch
              (ggf. mit Regeln -r)

          -a1 Kombinationsangriff
              Angriff mit dem Kreuzprodukt
              zweier Wörterbücher.

          -a3 Brute-force Angriff

          -a6 Hybridangriff 
              Wörterbuch und Maske


    .. container:: column 

        Brute-force - Eingebaute Zeichensätze:

        .. class:: monospaced

          ?l = abcdefghijklmnopqrstuvxyz

          ?u = ABCDEFGHIJKLMNOPQRSTUVWXYZ
          
          ?d = 0123456789
          
          ?s = !"$%&'()*+,-./:;<=>?@[]\^_{|}~
          
          ?a = ?l?u?d?s        

        Definition von bis zu 4 eigenen Zeichensätzen ist möglich.


`Hashcat - Ausgewählte Regeln <https://hashcat.net/wiki/doku.php?id=rule_based_attack>`__
-------------------------------------------------------------------------------------------

.. csv-table::
    :header: "Name", "Function", Description, Input, Output
    :class: small incremental
    
    Nothing, :, Do nothing (passthrough), ``p@ssW0rd``, ``p@ssW0rd``
    Lowercase, l, Lowercase all letters, ``p@ssW0rd``, ``p@ssw0rd``
    Uppercase, u, Uppercase all letters, ``p@ssW0rd``, ``P@SSW0RD``
    Capitalize, c, Capitalize the first letter and lower the rest, ``p@ssW0rd``, ``P@ssw0rd``
    Toggle Case, t, Toggle the case of all characters in word., ``p@ssW0rd``, ``P@SSw0RD``
    Reverse, r, Reverse the entire word, ``p@ssW0rd``, ``dr0Wss@p``
    Duplicate, d, Duplicate entire word, ``p@ssW0rd``, ``p@ssW0rdp@ssW0rd``
    Append, $X, Append X to the end, ``p@ssW0rd``, ``p@ssW0rdX``
    Prepend, ^X, Prepend X at the beginning, ``p@ssW0rd``, ``Xp@ssW0rd``
    ..., ..., ..., ..., ...


.. class:: small

Szenario 1: eine Pin Angreifen
-------------------------------

**Ausgangssituation**

Gegeben sein ein mit SHA256 gehashter 5-stelliger Pin in der Datei: ``5_digits_pin.sha256``.

Hashwert:

.. container:: monospaced 

    ``79737ac46dad121166483e084a0727e5d6769fb47fa9b0b627eba4107e696078``

**Angriff mit Maske**

.. code:: bash
    :class: incremental
	
    hashcat -m 1400 5_digits_pin.sha256 -a3 "?d?d?d?d?d"

.. container:: incremental

    :-m 1400: Modus für einen einfachen SHA256 Hash.
    :-a3: bezeichnet einen Maskenangriffe 
    :"?d?d?d?d?d": Beschreibt die Maske. Hier 5 Ziffern (:eng:`digits`).


.. class:: small

Szenario 2: Ein (hoffentlich) einfaches Loginpasswort angreifen
----------------------------------------------------------------

**Ausgangssituation**

Ein mit SHA512crypt gehashtes Passwort in der Datei: ``password.sha512crypt``.

**Angriff mit Wörterbuch**

.. code:: bash
    	
    hashcat password.sha512crypt -a0 /usr/share/wordlists/rockyou.txt

.. container:: incremental

    :-a0: bezeichnet einen Wörterbuchangriff. 
    :/usr/share/wordlists/rockyou.txt: Das zum Angriff verwendete Wörterbuch; der Pfad ist der Standardpfad zum Rockyou Wörterbuch in Kali Linux.


.. class:: small

Szenario 3: ein komplexeres Passwort angreifen 
---------------------------------------------------

**Ausgangssituation**

Ein mit MD5 gehashtes Passwort in der Datei: ``password.md5``. Ein erster Angriff mit Rockyou war nicht erfolgreich.

**Angriff mit Wörterbuch und Regelsatz**

.. code:: bash
    :class: incremental
	
    hashcat -m 0 password.md5 \
		-a0 /usr/share/wordlists/rockyou.txt \
		-r /usr/share/hashcat/rules/best64.rule

.. container:: incremental

    :-a0: bezeichnet einen Wörterbuchangriff. 
    :/usr/share/wordlists/rockyou.txt: Das zum Angriff verwendete Wörterbuch.
    :-r /usr/share/hashcat/rules/best64.rule: 
    
        Der zum Beugen der Passwortkandidaten verwendete Regelsatz. 
    
        Der Regelsatz best64 hat sich in einem Wettbewerb als :ger-quote:`bester` Regelsatz erwiesen. 


.. class:: small

Szenario 4: ein Passwort mit Salt angreifen
----------------------------------------------

**Ausgangssituation**

Ein MD5 Hash ist gegeben: ``c84b5c34c9ff7d3431018d795b5975e5``. Weiterhin ist bekannt, dass der verwendete *Salt* ``SALT`` ist.

**Angriff**

.. class:: incremental

1. Modus für MD5+Salt heraussuchen (``-m10``); ggf. Beispielhash ansehen, um zu verstehen, wie der Hash aufgebaut ist.

2. Erzeugen des Hashes für Hashcat:

   .. code:: bash

     echo -n "c84b5c34c9ff7d3431018d795b5975e5:SALT" > salted.m5.hash

3. Mit Hashcat angreifen:

   .. code:: bash

      hashcat -m10 salted.md5.hash  -a3 '?a?a?a?a'


.. admonition:: Lösung
    :class: supplemental

    Das Passwort ist ``Test``. In diesem Fall wäre es auch möglich gewesen direkt zu Prüfen ob das Passwort ``Test`` ist, indem man Hashcat im Modus -m0 (für reinen MD5) startet und als Kandidaten ``TestSALT`` vorgibt.


.. class:: small

Szenario 5: Kombination von Wörterbuch mit eigenem Regelsatz
-------------------------------------------------------------

**Ausgangssituation**

Wir greifen einen sogennanten langsamen Hash an und können deswegen nur wenige Passworte gezielt testen. 
    
Aufgrund von Social Engineering/Ermittlungen wissen wir, dass die Person häufig kurze Worte (max 4 Buchstaben nimmt) diese aber oft verdoppelt und häufig die Worte mit einem Großbuchstaben anfangen lässt.

**Angriff**

.. class:: incremental

1. Erstellen eines fokussierten Wörterbuchs: ``candidates.txt``.
2. Erstellen des Regelsatzes: ``case.rule``.
3. Angriff mit den erstellten Wörterbuch und dem Regelsatz.


.. class:: small

Szenario 5: Kombination von Wörterbuch mit eigenem Regelsatz
-------------------------------------------------------------

**Angriff**

1. Generierung von ``candidates.txt``

   Um sicherzustellen, dass wir keine Duplikate testen, wandeln wir alle Worte in Kleinschreibung um und filtern entsprechende Duplikate. Die Beachtung aller Varianten in Hinblick auf die Groß- und Kleinschreibung wird durch die Regeln sichergestellt.

   .. code:: bash
 
      $ grep -Po "^[a-zA-Z]{3,4}(?=[^a-zA-Z])" \
             /usr/share/wordlists/rockyou.txt \
        | tr [:upper:] [:lower:] \
        | sort -u \
        > candidates.txt

.. container:: supplemental

    **Zu Bedenken**

    Die gezeigte Operation löst die Ordnung in der Datei auf und sortiert diese alphabetisch. Dies ist aber häufig nicht gewünscht - insbesondere wenn der Leak nach Verwendungshäufigkeit sortiert ist!


.. class:: small

Szenario 5: Kombination von Wörterbuch mit eigenem Regelsatz
-------------------------------------------------------------

**Angriff**

1. Erstellen des Regelsatzes: ``case.rule``
   
   Um sicherzugehen, dass wir alle Varianten abdecken, brauchen wir drei Regeln.

   .. csv-table::
      :class: incremental no-table-borders
      :width: 100%

      cd, :minor:`Erst Groß-Kleinschreibung anpassen und dann duplizieren.`
      dc, :minor:`Erst duplizieren und dann Groß-Kleinschreibung anpassen.`
      d, :minor:`Einfach nur duplizieren.`

.. class:: incremental

2. Angriff mittels Hahcat

   .. code:: bash

      hashcat -m 1700 hash.sha125 candidates.txt -r case.rule

.. container:: supplemental 

    **Tips**

    Das beherrschen von regulären Ausdrücken ist bei der Passwortrekonstruktion sehr hilfreich.

    Der folgende Ausdruck liefert zum Beispiel alle 4stelligen Worte aus Rockyou mit Hilfe eines Lookheads, dass längere Worte filtert.

    .. code:: bash
        :class: black
        
        $ grep -Po "^[a-zA-Z]{3,4}(?=[^a-zA-Z])" \
             /usr/share/wordlists/rockyou.txt

    Das Passwort ``TreeTree`` würde sich damit erfolgreich wiederherstellen lassen.


.. class:: small 

Szenario 6: Kartesiche Produkt von zwei Wörterbüchern
------------------------------------------------------

**Ausgangssituation**

Aufgrund von Social Engineering/Ermittlungen wissen wir, dass die Person sehr gerne zwischen deutschen Großstädten pendelt. Nachdem andere Versuche nicht zum Erfolge geführt habe, wollen wir jetzt Passworte der Art: "BerlinHamburg" testen.

**Angriff**

.. class:: incremental
   
1. Erstellen eines fokussierten Wörterbuchs durch "Googeln" von großen Städten.
2. Angriff durch Kombination des Wörterbuchs mit sich selbst.

   .. code:: bash

      $ hashcat -m 1400 hash.sha256 -a 1 big_cities2.txt big_cities2.txt


.. class:: small

Szenario 7: Wörterbuch mit Maske
----------------------------------

**Ausgangssituation**

Es ist bekannt, dass die Passwörter der Gruppierung häufig mit vier Zahlen und zwei Sonderzeichen aus einer sehr kleinen Mengen von Sonderzeichen (``$!.``) enden. Davor kommt ein Wort mit ca. 4-8 Stellen in den typischerweiser "liebe/love/luv" vorkommt.

**Angriff**

.. class:: incremental

   1. Erstellen eines fokussierten Wörterbuchs: ``candidates.txt``
   2. Angriff mit passendem Maskenangriff


.. class:: small

Szenario 7: Wörterbuch mit Maske
----------------------------------

**Angriff mit Hybridangriff**

``candidates.txt`` enthält alle Begriffe aus rockyou, die die Anforderung erfüllen:

.. code:: bash

    $ grep -oE "[a-zA-Z]*[Ll]((uv)|(ove)|(iebe))[a-zA-Z]*" \
          /usr/share/wordlists/rockyou.txt \
     | sort -u \
     > candidates.txt

.. class:: incremental

    Angriff mit Hashcat:

    .. code:: bash

        $ hashcat -m 1400 hash.sha256 candidates.txt \
            -a 6 -1 '$.!' '?d?d?d?d?1?1'

.. container:: supplemental

    **Beispiel**
    
    In diesem Falle verwenden wir einen Hybridangriff, der eine Wordliste mit einer Maske kombiniert. Hier definieren wir unseren eigenen :ger-quote:`Zeichensatz` mit dem Parameter ``-1 '$.!'`` und referenzieren diesen in unserer Maske später mit ``?1``.

    Ein Beispielpasswort, dass wir mit dem Ansatz ermitteln könnten, wäre:

    .. csv-table::
        :header: SHA256, Passwort
        :class: monospaced

        "b9cace43df57bc694498bf4d7434f45a
         8466c4a924f608d54fd279d24b3dc937", ILuvU2023!!


.. class:: small

Szenario 8: Passwörter mit Muster
--------------------------------------------------------------------------------------------------

**Ausgangssituation**

Wir möchten ein Wörterbuch erstellen mit :ger-quote:`Wörtern`, die Buchstabenvervielfältigungen enthalten, aber nicht länger als 16 Zeichen sind. Zum Beispiel: "aaaaBBBBcccc" oder auch "AFFFFFE". 
Weiterhin soll die Liste nach der Länge der gefundenen Einträge aufsteigend sortiert sein und Zeichen, die keine Buchstaben sind, einfach gelöscht werden.

**Lösung**

.. container:: incremental

   Heraussuchen entsprechender Wörter aus rockyou mittels Linux Kommandozeilenwerkzeugen.

   .. code:: bash

      $ grep -E "([a-zA-Z])\1{3,}" /usr/share/wordlists/rockyou.txt 
        | grep -E "^.{4,16}$" 
        | sed -E 's/[^a-zAZ]//g' 
        | sort -u 
        | awk '{print length " " $1}' 
        | sort -n 
        | sed -E 's/^[0-9]+ //'


.. container:: supplemental
    
    *Alternative Aufgabenstellung*

    Sortierung der finalen Liste nach nach der Häufigkeit der Muster, angefangen mit dem häufigsten Mustern.


.. class:: small

Szenario 9: Passwörter bestehend aus Fragmenten
-----------------------------------------------------------

**Ausgangssituation**

- Einer gegebenen Liste können wir nur entnehmen, dass alle Passwörter zusammengesetzt sind aus den Fragmenten: ``ab``, ``mem``, ``li`` und ``xy``. 
- Darüber hinaus ist immer eine Zahl vorangestellt und am Ende kommt ein Punkt (``.``) oder ein Ausrufezeichen (``!``). 
- Die Länge scheint zwischen 6 und 16 Zeichen zu sein und Fragmente können sich wiederholen. 

Beispiel: ``1ablixyxy.``

**Vorgehen**

.. class:: incremental

1. Erstellen eines Basiswörterbuchs (``base.txt``) mit den Fragmenten als Einträge.
2. Erstellen von Regeln für das Voranstellen und Anhängen der entsprechenden (Sonder)zeichen.
3. Aus Basiswörterbuch das finale Wörterbuch für den Angriff generieren.
4. Mit dem finalen Wörterbuch und entsprechenden Regeln angreifen.


.. class:: small

Szenario 9: Generierung von Wörterbüchern aus Fragmenten
-----------------------------------------------------------

**Lösung**

Zu Generierung aller Kombinationen aus den Fragmenten verwenden wir den Princeprocessor.  Der Princeprocessor ist sehr schnell und ermöglicht es in Fällen die Ausgabe direkt an Hashcat durchzureichen und das Zwischenwörterbuch nicht explizit speichern zu müssen.

**Angriff**

.. code:: bash

   $ princeprocessor --pw-min=6 --pw-max=16 base.txt \
     | hashcat -m 1400 hash.sha256 \
        -r number_prepend.rule \
        -r sc_append.rule

.. container:: supplemental

    *Aufbau von* ``number_prepend.rule``:

    .. code:: bash
        :class: black

        ^0
        ^1
        ...
        ^9

    *Aufbau von* ``sc_append.rule``:

    .. code:: bash
        :class: black

        $.
        $!

    Mit dem obigen Ansatz könnte zum Beispiel das folgende Passwort ermittelt werden:

    .. csv-table::
        :header: SHA256, Passwort

        "8b11f8e8d487266a791d6d723a3e380c
        38f49679735a7f3395ace4302e83dd0e",  8abxylixy.

    In diesem Falle wäre es auch möglich gewesen nur einen Regelsatz zu erstellen mit den passenden Regeln (zum Beispiel: ``^1$.``, ``^1$!``, ...) der Aufwand wäre hier jedoch höher gewesen und hätte keinen Nutzen gehabt. 
    
    Im Allgemeinen ist jedoch bei der Verwendung des Kreuzproduktes von Regeln immer darauf zu achten, dass keine (oder zumindest keine relevante Anzahl von) Regeln dupliziert werden. Ein Beispiel wäre das Kreuprodukt aus einem Regelnsatz für das optionale Anhängen einer Ziffer mit sich selbst. Sei der Regelsatz:

    .. code::
        :class: black

        :
        $1
        $2

    und würde man diesen mit sich selber kombinieren, um alle Fälle des Anhängens von keiner, einer bzw. zwei Zahlen abzudecken, dann würden folgende Regeln entstehen:

    .. code::
        :class: black

        ::
        :$1
        :$2
        $1$1
        $1$2
        $2$1
        $2$2
        $1:
        $2:

    Wie zu erkennen ist, führen zum Beispiel die Regeln ``$1:`` und ``:$1`` jeweils zum gleichen Ergebnis und wären deswegen nicht effektiv.   

.. class:: small

Szenario 10: Hashcat als Werkzeug zur Wörterbuchgenerierung
------------------------------------------------------------

**Ausgangssituation**
Gegeben sein 3 Wörterbücher [#]_: ``base1.txt``, ``base2.txt`` und ``base3.txt``. Gesucht ist ein Wörterbuch, dass alle Kombinationen aus den drei Wörterbüchern enthält und bei dem alle Teilworte immer mit Sonderzeichen (-) voneinander getrennt sind. 


.. container:: incremental 

    **Beispiel**
    Sei ``base1.txt``: "Kuh", "Schwein"; ``base2.txt``: "Haus", "Villa" und ``base3.txt``: "Baum", "Busch". Dann wäre das gesuchte Wörterbuch: "Kuh-Haus-Baum", "Kuh-Haus-Busch", ..., "Schwein-Villa-Busch".

.. container:: incremental 

    **Vorgehen**

    .. container:: incremental 

        1. Erzeugen des Kreuzprodukts der ersten beiden Wörterbücher.

        .. code:: bash

            $ hashcat --stdout base1.txt base2.txt -j '$-' > base1-base2.txt

    .. container:: incremental 


       1. Erzeugen des finalen Wörterbuchs durch Bildung des Kreugprodukts der Ergebnisse aus Schritt 1 mit dem dritten Wörterbuch.
    
       .. code:: bash

            $ hashcat --stdout base1-base2.txt base3.txt -j '$-' > final.txt


.. container:: supplemental 

    Die Hashcat Utilities Bibliothek hat auch noch weitere Werkzeug zum Kombinieren von Wörterbüchern, die viele Fälle sehr effizient abdecken (auch den besprochenen). Jedoch ist es gerade in Fällen, in denen komplexere Regeln zur Anwendung kommen sollen, häufiger sinnvoller/nowendig direkt Hashcat im "stdout" Modus zu verwenden, um die Zwischenwörterbücher zu generieren.


.. [#] Die selbe Vorgehensweise lässt sich auch anwenden, wenn man ein Wörterbuch mit sich selber kombinieren möchte.



Passwörter angreifen - Zusammenfassung
---------------------------------------

.. class:: incremental more-space-between-list-items

- Passwörter können vielfach effizient angegriffen werden.
- (gute bis exzellente) Kenntnisse über die Zielpersonen sind häufig notwendig.
- Viele Werkzeuge sind verfügbar (siehe auch Hashcat Werkzeuge, Princeprocessor, John the Ripper, etc.)
- Kleine etablierte Kommandozeilenwerkzeuge (tr, greb, sed, awk, ...) oder selbstentwickelte Werkzeuge (zum Beispiel in Python) sind häufig ergänzend notwendig und führen oft  schneller zum Ziel als die Suche nach **dem** Tool. 
- Insbesondere wenn es um die semantische Anreicherung von Wörterbüchern geht, dann sind (bisher) keine etablierten Werkzeuge vorhanden.
- Häufig führen nur Kombinationen von etablierten und eigenen Werkzeugen zum gewünschten Ziel.



Literaturverzeichnis
-----------------------

.. container:: tiny

    .. [SePass] 
        SePass: Semantic Password Guessing Using k-nn Similarity Search in Word Embeddings; Maximilian Hünemörder, Levin Schäfer, Nadine-Sarah Schüler, Michael Eichberg & Peer Kröger, ADMA 2022: Advanced Data Mining and ApplicationsSpringer LNAI, volume 13726

    .. [PCFG]
        S.\  Aggarwal, M. Weir, B. Glodek and B. Medeiros, "Password Cracking Using Probabilistic Context-Free Grammars," in 2009 30th IEEE Symposium on Security and Privacy (SP); doi: `10.1109/SP.2009.8 <https://doi.ieeecomputersociety.org/10.1109/SP.2009.8>`__

    .. [NGPCFG]
        S.\ Houshmand, S. Aggarwal and R. Flood, "Next Gen PCFG Password Cracking," in IEEE Transactions on Information Forensics and Security, vol. 10, no. 8, pp. 1776-1791, Aug. 2015, doi: 10.1109/TIFS.2015.2428671.

    .. [PAofPCFG] 
        Hranický, R., Lištiak, F., Mikuš, D., Ryšavý, O. (2019). On Practical Aspects of PCFG Password Cracking. In: Foley, S. (eds) Data and Applications Security and Privacy XXXIII. DBSec 2019. Lecture Notes in Computer Science(), vol 11559. Springer, Cham. https://doi.org/10.1007/978-3-030-22479-0_3

    .. [PIandPCFG]
        Houshmand, S., Aggarwal, S. (2017). Using Personal Information in Targeted Grammar-Based Probabilistic Password Attacks. In: Peterson, G., Shenoi, S. (eds) Advances in Digital Forensics XIII. DigitalForensics 2017. IFIP Advances in Information and Communication Technology, vol 511. Springer, Cham. https://doi.org/10.1007/978-3-319-67208-3_16