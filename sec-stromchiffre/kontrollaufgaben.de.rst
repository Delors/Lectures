.. meta::
   :version: renaissance
   :lang: de
   :author: Michael Eichberg
   :keywords: Stromchiffre, Zufallszahlen, Kontrollfragen
   :description lang=de: Kontrollaufgaben/-fragen bzgl. Zufallszahlen und Stromchiffre
   :id: vorlesung-stromchiffre-kontrollaufgaben
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Zufallszahlen und Stromchiffre - Kontrollaufgaben
================================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0.1



.. class:: new-section

Zufallszahlen
---------------------------------------



.. class:: exercises

Fragen
------------------

.. exercise:: Zufallszahlen

    .. class:: incremental-list

    1. Wie unterscheidet sich ein TRNG von einem PRNG?
    2. Welche Formen der Unvorhersagbarkeit gibt es?
    3. Beschreiben Sie Maurer's Test auf Zufälligkeit.
    4. Ist ein Lauflängentest besser geeignet als ein Häufigkeitstest, um die Zufälligkeit einer Bitfolge zu überprüfen?
    5. Welche Anforderungen stellen wir an ein *Seed*?
    6. Welche grundsätzlichen Möglichkeiten gibt es Pseudozufallszahlen zu erzeugen?
    7. Welche Betriebsmodi für Blockchiffren sind geeignet für die Erzeugung von Pseudozufallszahlen.
    8. Wenn man einen TRNG hat, der (leicht) verzerrt ist, wie kann dieser konditioniert werden?
    9. Was besagt der Wert, der durch die Shannon-Entropie berechnet wird (nicht normalisiert)?

    .. solution::
        :pwd: WieZufälligIstEs?

        1. Wie unterscheidet sich ein TRNG von einem PRNG?

           - **TRNG (True Random Number Generator)** basiert auf physikalischen Prozessen (z. B. Rauschen) und liefert nicht deterministische, echte Zufallswerte.
           - **PRNG (Pseudo Random Number Generator)** verwendet deterministische Algorithmen und erzeugt aus einem Seed eine scheinbar zufällige, aber reproduzierbare Folge.

        2. Welche Formen der Unvorhersagbarkeit gibt es?

           - vorwärtsgerichtete Unvorhersagbarkeit
           - rückwärtsgerichtete Unvorhersagbarkeit

           D.h. auch wenn einige Zufallszahlen bekannt sind, kann man nicht vorhersagen, welche Zufallszahlen als nächste kommen bzw. welche Zufallszahlen als vorherige waren.

        3. Beschreiben Sie Maurer's Test auf Zufälligkeit.

           - Prüft, wie gut sich eine Bitfolge komprimieren lässt.
           - Idee: Nicht-zufällige Daten enthalten wiederkehrende Muster.
           - Eine schwer komprimierbare Folge weist auf hohe Zufälligkeit hin.

        4. Ist ein Lauflängentest besser geeignet als ein Häufigkeitstest?

           - **Häufigkeitstest** prüft Gleichverteilung von 0 und 1.
           - **Lauflängentest** prüft die Längen von gleichen Bitfolgen.
           - Beide Tests prüfen unterschiedliche Aspekte – sie ergänzen sich.

        5. Welche Anforderungen stellen wir an ein *Seed*?

           - Hohe Entropie (viel Zufälligkeit).
           - Ausreichende Länge (z. B. ≥128 Bit).

        6. Welche grundsätzlichen Möglichkeiten gibt es, Pseudozufallszahlen zu erzeugen?

           - **Klassische Verfahren**: z. B. lineare Kongruenzgeneratoren.
           - **Kryptographische PRNGs**: auf Basis sicherer Verfahren wie AES oder Hashfunktionen.
           - **Kombination mit Entropiequellen**

        7. Geeignete Betriebsmodi für Pseudozufallszahlen aus Blockchiffren

           - **CTR (Counter Mode)**: Verschlüsselung eines Zählers erzeugt Pseudozufallsfolge.
           - **OFB (Output Feedback)** (und **CFB (Cipher Feedback)**): Rückkopplung des Ausgabestroms.

        8. Wie TRNG-Ausgabe konditionieren, wenn leicht verzerrt?

           - Zur Entzerrung bieten sich Hashfunktionen (z. B. SHA-2) oder Verschlüsselungsverfahren (z. B. AES-CTR) an.
           - Ziel: Gleichverteilung und Unabhängigkeit der Bits.

        9. Was bedeutet die (nicht normalisierte) Shannon-Entropie?

           Misst die mittlere Informationsmenge pro Symbol. D. h. die Untergrenze der Anzahl an Bits, die benötigt werden, um ein Symbol - für eine gegebene Nachricht bzw. Häufigkeitsverteilung - zu kodieren.

           D. h. sei :math-i:`S` die Menge der verwendeten Symbole in einer Nachricht, und ist :math-i:`b` die Anzahl an Bits, die benötigt werden für die Kodierung eines Symbol, dann ist die Entropie maximal wenn alle Symbole gleich wahrscheinlich sind und es gilt::math:`b = \log_2(|S|)`.



.. class:: new-section

Stromchiffre
---------------------------------------



.. class:: exercises

Fragen
------------------

.. exercise:: Stromchiffren

    .. class:: incremental-list

    1. Welche Bedeutung hat der Schlüssel bei Stromchiffren?
    2. Welche Bedeutung hat der IV/die Nonce bei Stromchiffren?
    3. Wie unterscheiden sich die Funktionen zur Berechnung des nächsten Zustands und die Schlüsselstromfunktion bei Stromchiffren?

    .. solution::
        :pwd: der Strom der STROM

        1. Welche Bedeutung hat der Schlüssel bei Stromchiffren?

           - Der Schlüssel initialisiert den internen Zustand der Chiffre.
           - Er bestimmt den generierten Schlüsselstrom.
           - Die Sicherheit der Verschlüsselung hängt direkt von der Geheimhaltung und Qualität des Schlüssels ab.
           - Ein schwacher oder wiederverwendeter Schlüssel gefährdet die Sicherheit.

        2. Welche Bedeutung hat der IV/die Nonce bei Stromchiffren?

           - Der IV (Initialisierungsvektor) oder die Nonce sorgt dafür, dass bei gleichem Schlüssel unterschiedliche Schlüsselströme entstehen.
           - Sie schützt vor Wiederverwendungsangriffen und der Wiedererkennung von Klartextmustern.
           - Die Nonce muss einmalig sein – ihre Wiederverwendung bei gleichem Schlüssel ist kritisch.

        3. Unterschied von Zustandsübergangsfunktion und Schlüsselstromfunktion

           - Die **Zustandsübergangsfunktion** berechnet, wie sich der interne Zustand der Chiffre bei jedem Schritt verändert.
           - Die **Schlüsselstromfunktion** berechnet aus dem aktuellen Zustand das nächste Ausgabebit oder -wort.
           - Beide Funktionen arbeiten zusammen: Aus dem Zustand wird der Schlüsselstrom erzeugt, während sich der Zustand fortlaufend ändert.
