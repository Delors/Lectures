.. meta::
    :author: Michael Eichberg
    :keywords: Nutzerauthentifizierung, Challenge-Response-Authentifizierung, Fiat-Shamir-Transformation
    :description lang=en: User Authentification
    :description lang=de: Nutzerauthentifizierung
    :id: vorlesung-it-sicherheit-nutzer-authentifizierung
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Nutzerauthentifizierung
===============================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.3.7

.. supplemental::

  :Folien:
      [HTML] |html-source|

      [PDF] |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues



.. class:: new-section

Grundlagen
-------------


Challenge-Response Authentifizierung
-----------------------------------------------

.. deck::

    .. card::

        .. observation::

            Die Verwendung einer (kryptographischen) Hashfunktion alleine ist nicht ausreichend zur sicheren *Benutzerauthentifizierung über eine nicht-sichere Verbindung*.

            Eine einfache Replay-Attacke ist möglich.

    .. card::

        Ein einfaches Protokoll basierend auf einer Hashfunktion :math-i:`f` wäre:

        .. supplemental::

            Challenge-Response Protokoll ≘ :ger:`Herausforderung- und Antwortprotokoll`

        .. csv-table::
            :header: "Alice", "unsicherer Kanal", "Bob"
            :class: compact borderless incremental-table-rows highlight-cell-on-hover zebra-columns
            :widths: 20,15,20
            :width: 100%

            Gibt Benutzerkennung :math-i:`ID` ein, → sendet :math-i:`ID`, sucht zu :math-i:`ID` Schlüssel :math-i:`K` in der Datenbank
            , , ↓
            "Gibt Passwort :math-i:`K'` ein", sendet :math-i:`r` ←, wählt zufällige Zahl :math-i:`r`
            , , ↓
            "berechnet: :math:`Res'=f(K',r)`", → sendet :math-i:`Res'`, ":math:`f(K,r) ?= Res'`"


        .. question::
            :class: incremental

            Wie bewerten Sie die Sicherheit genau dieses Protokolls/Ansatzes?

        .. presenter-note::

            Das Problem ist hierbei, dass das Passwort auf dem Server im Klartext gespeichert wird. Dies kann zu Sicherheitslücken führen, wenn der Server kompromittiert wird oder wenn der Serveradministrator unberechtigten Zugriff hat.

            Ggf. gibt es auch die Möglichkeit für Replay-Attacken, wenn ein Angreifer die Kommunikation abhört und die Challenge-Response-Paare aufzeichnet und Bob "später" die gleiche Challenge wählen sollte. 

            Es erfolgt keine Authentisierung von Bob gegenüber Alice. Ein einfacher Person-in-the-Middle-Angriff ist möglich.
            


Zero-Knowledge Protokolle
---------------------------

.. deck::

    .. card::

        .. container:: accentuate

            Die Idee ist, dass man jemanden davon überzeugen möchte, dass man eine bestimmte Information hat, ohne diese Information zu offenbaren.

    .. card::

        .. example::

            .. rubric:: Der geheimnisvolle Geheimgang

            Peggy möchte Victor überzeugen, dass Sie den Code zur Tür kennt, ohne ihn zu offenbaren.

            .. grid::

                .. cell:: width-70

                    .. class:: incremental-list

                    - Peggy wählt einen der Wege zur Tür, während Victor an der Stelle A steht und darauf wartet, dass Sie bei der Tür ist.
                    - Sobald Peggy Bescheid gibt, dass Sie an der Tür angekommen ist, geht Victor zu Punkt B und sagt Peggy auf welchem Weg sie zurückkommen soll.
                    - Kommt sie auf dem falschen Weg zurück, dann kennt sie den Code der Tür (offensichtlich) nicht. Kommt sie auf dem richtigen Weg zurück, könnte es noch immer Zufall gewesen sein mit Wahrscheinlichkeit :math:`\frac{1}{2}`.

                .. cell:: width-30

                    .. image:: images/zkp-geheimgang.svg
                        :align: center

                    .. container:: incremental

                        Bei n Spielen ist die Wahrscheinlichkeit, dass Peggy immer zufällig den korrekt Weg gewählt hat :math:`\frac{1}{2^n}`.

                        .. Wenn wir :math-i:`n` mal spielen und Peggy kommt immer auf dem richtigen Weg zurück, dann ist die Wahrscheinlichkeit dafür, dass sie es immer zufällig korrekt getan hat :math:`\frac{1}{2^n}`.

    .. card::

        Viele Zero-Knowledge Protokolle basieren darauf, dass man im Prinzip ein Spiel spielt, das man auch zufällig gewinnen kann. Durch die Wiederholung des Spiels wird die Wahrscheinlichkeit jedoch für permanentes zufälliges Gewinnen sehr schnell sehr klein (exponentiell). Somit kann man für praktische Zwecke hinreichend sicher sein, dass der Beweisführende (im Beispiel Peggy) über das Wissen verfügt, das er vorgibt zu besitzen, wenn er immer gewinnt.

        Nach :math-r:`20` Runden ist die Wahreinschlichkeit nur noch :math:`1/2^{20} = 1/1 048 576`.

        Mit :math-r:`128` Runden erreicht man ein Sicherheitsniveau, das vergleichbar ist mit anderen kryptographischen Verfahren (AES-128, SHA-256, ...).



Fiat-Shamir Protokoll
----------------------

.. rubric:: Voraussetzungen

- gegeben zwei zufällige Primzahlen :math-i:`p` und :math-i:`q` und :math:`n = p \cdot q`
- Peggys geheimer Schlüssel :math-i:`s` wird dann zufällig bestimmt; :math:`s \in \mathbb{Z}_n^*` und :math-i:`s` coprimal zu :math-i:`n`.
- Der öffentliche Schlüssel wird dann wie folgt berechnet: :math:`v = s^{-2} \bmod n`. Der öffentlichen Schlüssel besteht dann aus den zwei Zahlen :math:`v` und :math:`n`.

.. rubric:: Protokoll

.. story::

    .. class:: incremental-list

        1. Peggy berechnet :math:`x` unter Verwendung einer beliebigen Zufallszahlen :math:`r \in \mathbb{Z}_n^*`:

           :math:`x = r^2 \bmod n`

           .. presenter-note::

                Die Menge :math:`\mathbb{Z}_n^*` (gesprochen: „die multiplikative Gruppe modulo n“) ist definiert als:

                :math:`\mathbb{Z}_n^* = \{a \in \mathbb{Z}_n \mid \gcd(a, n) = 1\}`

                Dies ist notwendig, damit :math:`s^{-2} \bmod n` existiert.

        2. Peggy sendet die Zahl an Victor.

        3. Victor wählt zufällig ein Bit :math:`b \in \{0, 1\}`

        4. Peggy berechnet :math:`y = r \cdot s^b \bmod n`

        5. Peggy sendet :math-i:`y` an Victor.

        6. Victor testet:  :math:`x \cdot v^{-b} \bmod n \stackrel{?}{=} y^2 \bmod n`



.. class:: exercises

Übung: klassisches Fiat-Shamir-Protokoll
-------------------------------------------

.. exercise:: Beispiel: Fiat-Shamir-Protokoll mit kleinen Zahlen

   Zwei Parteien, Peggy (die sich authentifizieren möchte) und Victor (der Prüfer), führen das Fiat-Shamir-Protokoll durch. Verwenden Sie die folgenden Werte:

   - :math:`p = 3`, :math:`q = 7` → :math:`n = p \cdot q = 21`
   - Peggys geheimer Schlüssel ist :math:`s = 2`
   - Peggy wählt die Zufallszahl :math:`r = 4`
   - Victor wählt die Herausforderung :math:`b = 1`

   Beantworten Sie folgende Fragen:

   #. Berechnen Sie den öffentlichen Schlüssel :math-i:`v`.
   #. Berechnen Sie den Wert :math-i:`x`, den Peggy an Victor sendet.
   #. Berechnen Sie die Antwort :math-i:`y`, die Peggy an Victor sendet.
   #. Führen Sie die Verifikation als Victor durch

   .. solution::
      :pwd: 16OderSo

      #. Berechnung des öffentlichen Schlüssels:

         Peggys geheimer Schlüssel ist :math:`s = 2`; dann ist :math:`s^2 = 4`

         → Gesucht ist :math:`v = s^{-2} \bmod 21 = 4^{-1} \bmod 21`

         Der Inverse von 4 modulo 21 ist 16, denn: :math:`4 \cdot 16 = 64 \equiv 1 \bmod 21`

         → Also: :math:`v = 16`

      #. Berechnung von :math:`x`:

         Peggy wählt :math:`r = 4`

         → :math:`x = r^2 \bmod 21 = 4^2 = 16`

         Peggy sendet :math:`x = 16`

      #. Antwortberechnung:

         Victor wählt :math:`b = 1`

         → :math:`y = r \cdot s^b \bmod n = 4 \cdot 2 = 8`

         Peggy sendet :math:`y = 8`

      #. Verifikation durch Victor:

         - :math:`y^2 = 8^2 = 64 \bmod 21 = 1`
         - :math:`x \cdot v^{-b} \bmod 21 = 16 \cdot v^{-1} \bmod 21= 16 \cdot 4 \bmod 21 = 64 \bmod 21 = 1`

         → Beide Seiten ergeben 1 modulo 21

         Die Gleichung stimmt also: :math:`y^2 \equiv x \cdot v^{-b} \mod n`

         Somit ist die Verifikation erfolgreich.

         Da in diesem Beispiel nur eine Runde durchgeführt wurde, kann Victor lediglich mit Wahrscheinlichkeit :math:`\frac{1}{2}` sicher sein, dass Peggy tatsächlich die Geheimzahl :math:`s` kennt.

         Erst durch die Wiederholung über viele Runden hinweg sinkt die Wahrscheinlichkeit eines erfolgreichen Betrugs exponentiell.



.. class:: new-section transition-fade

Einmalpasswortsysteme
-------------------------


Password Sniffing
---------------------

.. class:: incremental-list

:In der Anfangszeit: unverschlüsselte Übertragung von Passwörtern (telnet, ftp, ...)
:In der Übergangszeit: Verwendung von Einmal-Passwörtern (S/Key, ...)
:Heute: Passwörter werden verschlüsselt übertragen (ssh, https, ...)

    Zusätzliche Absicherung durch Zwei-Faktor-Authentifizierung (basierend auf Einmalpassworten: TOTP, ...)

.. supplemental::

    Unverschlüsselte Passworte können leicht mittels eines Sniffers, der den Netzwerkverkehr mitschneidet (z. B. Wireshark), abgefangen werden.



Einmal-Passwörter
----------------------

Die Idee ist, dass Passwörter nur genau einmal gültig sind und nicht wiederverwendbar sind.

- Tokens (z. B. RSA SecurID)
- Codebuch: Liste von Einmal-Passwörtern, die das gemeinsame Geheimnis sind.
- S/Key: Passwort „wird mit einem Zähler kombiniert“ und dann gehasht.


Das S/Key Verfahren
------------------------------

Einmal-Passwort-System nach Codebuch-Verfahren.

.. deck::

    .. card::

        **Initialisierung**

        .. class:: incremental-list list-with-explanations

        1) Der Nutzer gibt sein Passwort :math:`W` ein; dies ist der geheime Schlüssel.

           (Sollte :math:`W` bekannt werden, dann ist die Sicherheit des Verfahrens nicht mehr gewährleistet.)
        2) Eine kryptografische Hash-Funktion :math:`H` wird n-mal auf :math:`W` angewandt, wodurch eine Hash-Kette von n einmaligen Passwörtern entsteht. :math:`H(W), H(H(W)), \dots, H^{n}(W)`
        3) Das initiale Passwort wird verworfen.
        4) Der Benutzer erhält die :math:`n` Passwörter, die in umgekehrter Reihenfolge ausgedruckt werden: :math:`H^n(W), H^{n-1}(W), ..., H(H(W)), H(W)`.
        5) Nur das Passwort :math:`H^n(W)`, das an erster Stelle der Liste des Benutzers steht, der Wert von :math:`n` und ggf. ein Salt, wird auf dem Server gespeichert.

    .. card::

        **Anmeldung**

        Identifiziere das letzte verwendete Passwort :math:`n`.

        .. class:: incremental-list

        - Der Server fragt den Nutzer nach dem Passwort :math:`n-1` (d. h. :math:`H^{n-1}(W)`) und übermittelt ggf. auch den Salt.
        - Der Server hasht das Passwort und vergleicht es dann mit dem gespeicherten Passwort :math:`H^n(W)`.
        - Ist das Passwort korrekt, dann wird der Nutzer angemeldet und der Server speichert das Passwort :math:`H^{n-1}(W)` als neues Passwort :math:`H^n(W)` und dekrementiert n.

.. supplemental::

    Im Original basiert S/Key auf der kryptographischen Hashfunktion MD4. Ein Austausch wäre aber selbstverständlich möglich!

    Intern verwendet S/KEY 64-bit Zahlen. Für die Benutzbarkeit werden diese Zahlen auf sechs kurze Wörter, von ein bis vier Zeichen, aus einem öffentlich zugänglichen 2048-Wörter-Wörterbuch (:math:`2048 = 2^{11}`) abgebildet. Zum Beispiel wird eine 64-Bit-Zahl auf "ROY HURT SKI FAIL GRIM KNEE" abgebildet.



HMAC-based one-time password (HOTP)\ [#]_
--------------------------------------------

- ermöglicht die Erzeugung von Einmal-Passwörtern auf Basis eines geheimen Schlüssels und eines Zählers; Parameter:

  .. class:: incremental-list

  - Ein kryptografisches Hash-Verfahren :math:`H` (Standard ist SHA-1 mit 160 Bit)
  - einen geheimen Schlüssel :math:`K`, der eine beliebige Bytefolge ist
  - Ein Zähler :math:`C`, der die Anzahl der Iterationen zählt
  - Länge des Passworts: :math:`d` (6-10, Standardwert ist 6, empfohlen werden 6-8)

.. class:: incremental

- Zur Authentifizierung berechnen beide das Einmalpasswort (HOTP) und dann vergleicht der Server den Wert mit dem vom Client übermittelten Wert.

  Berechnung aus dem Schlüssel :math:`K` und dem Zähler :math:`C`:

  .. container:: incremental

    :math:`HOTP(K, C) = truncate(HMAC_H(K, C))`

    .. container:: incremental

        :math:`truncate(MAC) = extract31(MAC, MAC[(19 × 8 + 4):(19 × 8 + 7)])`

    .. container:: incremental

        :math:`HOTP\; value = HOTP(K, C)\; mod\; 10^d\qquad` (führende Nullen werden nicht abgeschnitten)

.. [#] https://www.rfc-editor.org/rfc/rfc4226

.. supplemental::

    :math:`truncate` verwendet die 4 niederwertigsten Bits des MAC als Byte-Offset :math:`i` in den MAC.
    Der Wert :math:`19` kommt daher, dass ein SHA-1 :math:`160` Bit hat und :math:`160/8 = 20` Byte.

    :math:`extract31` extrahiert 31 Bit aus dem MAC. Das höchstwertig Bit wird (wenn es nicht 0 ist) entsprechend maskiert.
    Eine Schwäche des Algorithmus ist, dass beide Seiten den Zähler erhöhen müssen und, falls die Zähler aus dem Tritt geraten, ggf. eine Resynchronisation notwendig ist.



Time-based one-time password (TOTP)\ [#]_
--------------------------------------------

- Erzeugung von zeitlich limitierten Einmal-Passwörtern (z. B. 30 Sekunden)

.. class:: incremental-list list-with-explanations

- Basierend auf einem vorher ausgetauschten geheimen Schlüssel und der aktuellen Zeit

  Z. B. Unix-Zeit in Sekunden (ganzzahlig) und danach gerundet auf 30 Sekunden.

- Es wird das HOTP Verfahren mit der Zeit als Zähler verwendet und entweder SHA-256 oder SHA-512 als Hashverfahren, d. h. TOTP :math:`value(K)` = HOTP :math:`value(K, C_T)`, wobei :math:`T` die „aktuelle Zeit“ ist.

  :math:`C_T = \lfloor { T - T_0 \over T_X } \rfloor`

  - :math:`T_X` ist die Länge eines Zeitintervalls (z. B. 30 Sekunden)
  - :math:`T` ist die aktuelle Zeit in Sekunden seit einer bestimmten Epoche
  - :math:`T_0` ist bei Verwendung der Unix-Zeit :math:`0`
  - :math:`C_T` ist somit die Anzahl der Dauern :math:`T_X` zwischen :math:`T_0` und :math:`T`

.. [#] https://www.rfc-editor.org/rfc/rfc6238

.. supplemental::

   Das Verfahren verlangt somit, dass die Uhren von Server und Client (hinreichend) synchronisiert sind.



.. class:: exercises transition-move-left

Übung
--------------

.. exercise:: S/Key

    1. Welche Vorteile bieten Einmalpasswortsysteme gegenüber Systemen mit mehrfach zu verwendenden Passworten?
    2. Welchen Angriffen sind Einmalpasswortsysteme weiterhin ausgesetzt?
    3. Generieren Sie eine Liste von Einmalpassworten mit Initialwert :math:`r = 769`\ . Generieren Sie :math:`H(r)` bis :math:`H^6(r)` wenn die Einwegfunktion hier der Einfachheit halber :math:`H(x) = x^2\; mod\; 1000` ist.
    4. Wie oft kann sich der Benutzer anmelden? Wie sieht seine Liste aus?
    5. Welchen Wert speichert der Server vor dem ersten Anmeldevorgang?
    6. Spielen Sie zwei Anmeldevorgänge durch.
    7. Wenn ein Passwort :math:`H^L(W), 1 < L < N` bekannt ist, welche Auswirkungen hat dies auf die Sicherheit des Verfahrens?

    .. solution::
        :pwd: sKey.!

        1. Schutz gegen Lauscher
        2. Person-in-the-middle
        3. Der Benutzer wählt eine Zufallszahl :math:`r`, hier :math:`r = 769`. Berechnet wird nun:

           :math:`769^2\; mod\; 1000 = 361`

           :math:`361^2\; mod\; 1000 = 321`

           :math:`321^2\; mod\; 1000 = 41`

           :math:`41^2\; mod\; 1000 = 681`

           :math:`681^2\; mod\; 1000 = 761`

           :math:`761^2\; mod\; 1000 = 121`

        4. Fünfmal. Der Benutzer erhält folgende Passwortliste: :math:`761, 681, 41, 321, 361`
        5. Der Server speichert: :math:`121`.
        6. Beim ersten Anmeldevorgang verwendet der Benutzer das erste Passwort auf der Liste, die :math:`761`.

           Der Server berechnet nun :math:`761^2\, mod\, 1000 = 121` und vergleicht dies mit dem gespeicherten Wert. Da diese übereinstimmen, wird der Benutzer angemeldet.

           Der Server speichert jetzt die :math:`761`, und der Benutzer streicht die :math:`761` von der Liste, usw.

        7. Keine



.. class:: exercises transition-move-left

Übung
--------------

.. exercise:: HOTP

    Gegeben sei der folgende MAC:

    .. csv-table::
        :class: compact table-data-monospaced
        :stub-columns: 1
        :width: 100%

        Offset, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19
        Mac, bc, 9f, aa, ae, 1e, 35, d5, 2f, 3d, ea, 96, 51, da, 12, cd, 36, 62, 7b, 84, 03

    Berechnen Sie den HOTP Wert für :math:`d = 6`.

    .. solution::
        :pwd: HelloWorldAlsHOTP

        Der Offset ergibt sich aus den letzten 4 Bits und ist demzufolge :math:`3`.

        Die Bytefolge ist somit: ``ae 1e 35 d5``. Wir müssen jetzt das erste Bit maskieren, bevor wir die Dezimalzahl berechnen. D.h. vor der Anwendung der Modulfunktion wird das erste Bit auf 0 gesetzt und somit ist die relevante Bytefolge: ``2e 1e 35 d5``. Als Dezimalzahl ergibt sich: :math:`773 731 797` und das Token somit zu: :math:`773 731 797\; mod\; 10^6 = 731 797`.



.. class:: exercises transition-move-left

Übung
--------------

.. exercise:: TOTP

  Identifizieren Sie die Vor- und Nachteile von TOTP gegenüber S/Key und fragen Sie sich an welcher Stelle es (aus Sicherheitsperspektive) mögliche Schwächen gibt?

  Die Standardzeitspanne ist 30 Sekunden. Welcher Konsequenzen hätte eine deutliche Verlängerung bzg. Verkürzung der Zeitspanne?

  .. solution::
    :pwd: TOTPandSKey

    - Bei TOTP gibt es keine beschränkte Liste von Passwörtern. Die Passwörter werden dynamisch generiert und es stehen „unendlich“ viele zur Verfügung. Es kann auch keine Verwirrung über das nächste bzw. bereits verbrauchte Passwort geben. Die Synchronisation ist ggf. einfacher.
    - Bei TOTP gibt es ein Shared Secret, das auf dem Server gespeichert wird. Bei S/Key werden keine entsprechenden Informationen auf dem Server gespeichert. D. h. selbst wenn der Server kompromittiert wird, kann nicht auf das ursprüngliche Secret geschlossen werden.
    - SKey verwendet ursprünglich MD4, was heute als unsicher gilt. TOTP verwendet (z. B.) HMAC-SHA-256, was als sicher gilt. Dies ist jedoch kein konzeptioneller Unterschied und eine Einsatz sicherer Hashverfahren ist/wäre auch bei SKey möglich.

    - Kürzer: mehr Sicherheit, aber ggf. auch schlechtere Bedienbarkeit.
    - Länger: ggf. bessere Bedienbarkeit (man muss sich nicht beeilen.)
    - Sehr lange (z. B. >> 10 min): ggf. auch schlechtere Bedienbarkeit, da man sich nach ein Logout nicht direkt wieder anmelden kann.



.. TODO : Web: Basic Authentication, Bearer Tokens, JWT, OAuth2, Passkey, WebAuthn, FIDO2