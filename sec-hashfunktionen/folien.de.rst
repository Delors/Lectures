.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: hash functions
    :description lang=en: Cryptographic Hash Functions
    :description lang=de: Kryptografische Hashfunktionen
    :id: lecture-security-hash_functions
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Kryptografische Hash Funktionen
===============================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Basierend auf: *Cryptography and Network Security - Principles and Practice, 8th Edition, William Stallings*
:Version: 2.2.1

.. supplemental::

  :Folien:
      [HTML] |html-source|

      [PDF] |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues



.. class:: new-section

Hashfunktionen - Grundlagen
------------------------------------------------


Hashfunktionen
-------------------------------

.. class:: incremental-list

- Eine Hashfunktion :math:`H` akzeptiert eine beliebig lange Nachricht :math:`M` als Eingabe und gibt einen Wert fixer Größe zurück: :math:`h = H(M)`.
- Wird oft zur Gewährleistung der Datenintegrität verwendet. Eine Änderung eines beliebigen Bits in :math:`M` sollte mit hoher Wahrscheinlichkeit zu einer Änderung des Hashwerts :math:`h` führen.
- Kryptographische Hashfunktionen werden für Sicherheitsanwendungen benötigt. Mögliche Anwendungen:

  - Authentifizierung von Nachrichten
  - Digitale Signaturen
  - Speicherung von Passwörtern


Beispiel: Berechnung von Hashwerten mittels MD5
-------------------------------------------------------

::

    md5("Hello") = 8b1a9953c4611296a827abf8c47804d7
    md5("hello") = 5d41402abc4b2a76b9719d911017c592
    md5("Dieses Passwort ist wirklich total sicher
         und falls Du es mir nicht glaubst, dann
         tippe es zweimal hintereinander blind
         fehlerfrei ein.")
                 = 8fcf22b1f8327e3a005f0cba48dd44c8

.. warning::
    :class: incremental

    Die Verwendung von MD5 dient hier lediglich der Illustration. In realen Anwendung sollte MD5 nicht mehr verwendet werden.



Sicherheitsanforderungen an kryptografische Hashfunktion I
----------------------------------------------------------

:Variable Eingabegröße: H kann auf einen Block beliebiger Größe angewendet werden.
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

.. supplemental::

    **Hintergrund**

    Im Deutschen wird auch von Urbild-Angriffen gesprochen. In dem Fall ist *preimage resistance* (d. h. die Einweg Eigenschaft) gleichbedeutend damit, dass man nicht effektiv einen „Erstes-Urbild-Angriff“ durchführen kann. Hierbei ist das Urbild die ursprüngliche Nachricht :math:`M`, die *gehasht* wurde.

    *Second preimage resistance* ist dann gleichbedeutend damit, dass man nicht effektiv einen „Zweites-Urbild-Angriff“ durchführen kann. Es ist nicht möglich zu einer Nachricht M eine zweite Nachricht N (d. h. ein zweites Urbild) zu finden, die für eine gegebene Hashfunktion den gleich Hash aufweist.



Beziehung zwischen den Sicherheitsanforderungen an Hashfunktionen
------------------------------------------------------------------

.. image:: drawings/hash_functions/properties.svg
    :alt: Beziehung zwischen den Eigenschaften von Hashfunktionen
    :align: center



Nachrichtenauthentifizierung - vereinfacht
-------------------------------------------------------

Nachrichten können auf verschiedene Weisen authentifiziert werden, so dass *Man-in-the-Middle-Angriffe* (MitM)\ [#]_ verhindert werden können.

.. deck::

    .. card::

        .. image:: drawings/digests/all_encrypted.svg
            :align: center

    .. card::

        .. image:: drawings/digests/hash_encrypted.svg
            :align: center

    .. card::

        .. image:: drawings/digests/secret_appended.svg
            :align: center

    .. card::

        .. image:: drawings/digests/secret_encrypted.svg
            :align: center

.. [#] :eng:`Man` ist hier geschlechtsneutral zu verstehen.

.. supplemental::

    **Szenarien**

    Im ersten Szenario wird der Hash an die Nachricht angehängt und als ganzes verschlüsselt. Wir erhalten Vertraulichkeit und Authentizität.

    Im zweiten Szenario wird der Hash der Nachricht berechnet und dann verschlüsselt. Der Empfänger kann den Hash berechnen und mit dem entschlüsselten Hash vergleichen. Wir erhalten Authentizität, aber keine Vertraulichkeit.

    Im dritten Szenario wird an die Nachricht ein geteiltes Secret angehängt und  alles zusammen gehasht. Die Nachricht wird dann mit dem Ergebnis der vorhergehenden Operation zusammen verschickt.

    Im letzten Szenario werden alle Ansätze kombiniert.

    **Legende**

    :M: die Nachricht
    :H: die Hashfunktion
    :E: der Verschlüsselungsalgorithmus
    :D: der Entschlüsselungsalgorithmus
    :K: ein geheimer Schlüssel
    :S: eine geheime Zeichenkette
    :||: die Konkatenation von zwei Werten (d. h. das Aneinanderhängen von zwei Werten)

    .. hint::

        Bei *Man-in-the-Middle-Angriffen* handelt es sich um einen Fachbegriff und häufig wird zum Beispiel Eve oder Mallory verwendet, um die Person zu bezeichnen, die den Angriff durchführt. Gelegentlich wird auch *Adversary-in-the-Middle* oder *Person-in-the-Middle* verwendet.

    .. rubric:: Message-Digests

    Im allgemeinen Sprachgebrauch wird auch von :eng:`Message Digests` gesprochen.



Digitale Signaturen - vereinfacht
-------------------------------------------------------

Digitale Signaturen dienen dem Nachweis der Authentizität einer Nachricht und der Integrität der Nachricht.  Jeder, der einen öffentlichen Schlüssel hat, kann die Signatur überprüfen, aber nur der Besitzer des privaten Schlüssels kann die Signatur erstellen.

.. deck::

    .. card::

        .. image:: drawings/signatures/just_authentication.svg
            :align: center

    .. card::

        .. image:: drawings/signatures/authentication_and_encryption.svg
            :align: center

.. supplemental::

    **Legende**

    :M: die Nachricht
    :H: die Hashfunktion
    :E: der Verschlüsselungsalgorithmus
    :D: der Entschlüsselungsalgorithmus
    :`PR_a`:math:: der private Schlüssel von a
    :`PU_a`:math:: der öffentliche Schlüssel von a
    :||: die Konkatenation von zwei Werten (d. h. das Aneinanderhängen von zwei Werten)



Anforderungen an die Resistenz von Hashfunktionen
---------------------------------------------------

.. csv-table::
    :header: "", Preimage Resistant, Second Preimage Resistant, Collision Resistant
    :class: incremental-table-rows highlight-line-on-hover
    :widths: 28, 10, 10, 10

    Hash + Digitale Signaturen, ✓, ✓, ✓
    Einbruchserkennung und Viruserkennung, , ✓ ,
    Hash + Symmetrische Verschlüsselung, , ,
    Passwortspeicherung, ✓, ,
    MAC, ✓, ✓, ✓

.. supplemental::

    .. rubric:: Einbruchserkennung und Viruserkennung - Hintergrund

    Bei der Einbruchserkennung und Viruserkennung ist *second preimage* Resistenz erforderlich. Andernfalls könnte ein Angreifer seine Malware so schreiben, ass diese einen Hash wie eine vorhandene gutartige Software hat und so verhindern, dass die Malware auf eine schwarze Liste gesetzt werde kann, ohne den Kollateralschaden, dass auch die gutartige Software fälschlicherweise als Malware erkannt wird.

    .. rubric:: Aufwand eines Kollisionsangriffs

    Ein Kollisionsangriff erfordert weniger Aufwand als ein *preimage* oder ein *second preimage* Angriff.

    Dies wird durch das Geburtstagsparadoxon erklärt. Wählt man Zufallsvariablen aus einer Gleichverteilung im Bereich von :math:`0` bis :math:`N-1`, so übersteigt die Wahrscheinlichkeit, dass ein sich wiederholendes Element gefunden wird, nach :math:`\sqrt{N}` Auswahlen :math:`0,5`. Wenn wir also für einen m-Bit-Hashwert Datenblöcke zufällig auswählen, können wir erwarten, zwei Datenblöcke innerhalb von :math:`\sqrt{2^m} = 2^{m/2}` Versuchen zu finden.

    .. example::

        Es ist relativ einfach, ähnliche Meldungen zu erstellen. Wenn ein Text 8 Stellen hat, an denen ein Wort mit einem anderen ausgetauscht werden kann, dann hat man bereits :math:`2^{8}` verschiedene Texte.

        Es ist relativ trivial(1), vergleichbare(2) Nachrichten(3) zu schreiben(4). Wenn ein Text 8 Stellen hat, an denen ein Ausdruck(5) mit einem vergleichbaren (6) ausgetauscht werden kann, dann erhält(7) man bereits :math:`2^{8}` verschiedene Dokumente(8).



Effizienzanforderungen an kryptografische Hashfunktionen
------------------------------------------------------------------------

:Effizienz bei der Verwendung für Signaturen und zur Authentifizierung:

  Bei der Verwendung zur Nachrichtenauthentifizierung und für digitale Signaturen ist :math:`H(N)` für jedes beliebige :math:`N` relativ einfach zu berechnen. Dies soll sowohl Hardware- als auch Softwareimplementierungen ermöglichen.

.. container:: incremental

    .. container:: text-align-center bold huge

        vs.

    :Brute-Force-Angriffe auf Passwörter erschweren:

        Bei der Verwendung für das Hashing von Passwörtern soll es schwierig sein den Hash effizient zu berechnen, selbst auf spezialisierter Hardware (GPUs, ASICs).



Struktur eines sicheren Hash-Codes
------------------------------------------------------------------------

(Vorgeschlagen von Merkle.)

.. image:: drawings/hash_functions/structure_of_secure_hash_codes.svg
    :align: center

.. class:: columns

-       :math:`IV` = Initialer Wert (Algorithmus-abhängig)

        :math:`CV_i` = Verkettungsvariable

        :math:`Y_i` = i-er Eingabeblock

        :math:`f` = Kompressionsfunktion

-       :math:`n` = Länge des Blocks

        :math:`L` = Anzahl der Eingabeblöcke

        :math:`b` = Länge des Eingabeblocks

.. supplemental::

    Diese Struktur liegt insbesondere den Hashfunktionen der SHA-2 Familie zugrunde.



.. class:: exercises

Übung
-------

.. exercise:: XOR als Hashfunktion


    Warum ist eine einfache „Hash-Funktion“, die einen 256-Bit-Hash-Wert berechnet, indem sie ein XOR über alle 256-Bit Blöcke einer Nachricht durchführt, im Allgemeinen ungeeignet?

    .. container:: peripheral

        Wir nehmen hier an, dass die Nachricht ein Vielfaches von 256 Bit lang ist. Falls nicht, dann wenden wir Padding an.

    .. solution::
        :pwd: alles nichts

        Je nach Beschaffenheit der zugrunde liegenden Daten können wir die ursprüngliche Nachricht ggf. wiederherstellen bzw. diese liegt direkt vor. Stellen Sie sich z. B. vor, dass nur ein Block sinnvolle Daten enthält und alle anderen Blöcke einfach "0" sind.

        Darüber hinaus wäre es ggf. sehr einfach eine Nachricht zu finden, die denselben Hashwert hat wie die ursprüngliche Nachricht. Somit wäre die Kollisionsresistenz nicht gegeben und eine Integritätssicherung wäre nicht möglich.

        ::

            Nachricht     A:                 B:
            1. Block      10101010101010     01010101010101
            2. Block      10101111101011     01010000010100

            "Hash" (XOR): 00000101000001  =  00000101000001



.. class:: exercises

Übung
---------

.. exercise:: Bewertung der Sicherheit

    .. class:: list-with-explanations

    - Eine Nachricht :math:`M` bestehe aus :math:`N` 64-bit Blöcken: :math:`X_1, \ldots, X_n`.
    - Der Hashcode H(M) ist ein simpler XOR über alle Blöcke: :math:`H(M) = h = X_1 \oplus X_2 \oplus \ldots \oplus X_n`.
    - :math:`h` wird als der :math:`X_{N+1}` Block an die Nachricht angehängt und danach wird unter Verwendung des CBC Modus die Nachricht inkl. des Hashcodes verschlüsselt (:math:`C = Y_1, \ldots, Y_{N+1}`).
    - Gegen welche Art von Manipulation ist diese Konstruktion *nicht* sicher?

      Studieren Sie ggf. noch einmal den CBC Modus.

    .. solution::
        :pwd: umsortiert

        Die Konstruktion ist nicht sicher gegenüber Vertauschungen der Blöcke!

        Da :math:`X_1 = IV \oplus D(K,Y_1)`, ... ,\ :math:`X_{N+1} = Y_N \oplus D(K,Y_{N+1})` ist und :math:`X_{N+1} = X_1 \oplus X_2 \oplus \ldots \oplus X_n`. Gilt:

        .. math::

            X_1 \oplus X_2 \oplus \ldots \oplus X_n = [IV \oplus D(K,Y_{1})] \oplus \ldots \oplus [ Y_{N-1} \oplus D(K,Y_{N})]

        Somit kann ein Angreifer die Blöcke vertauschen (:math:`\oplus` ist kommutativ), ohne dass dies erkannt werden könnte.



.. class:: exercises

Übung
-------

.. exercise:: Irrelevanz von Second-Preimage-Resistenz und Kollisionssicherheit

    Warum sind *Second-Preimage-Resistenz* und Kollisionssicherheit von nachgeordneter Relevanz, wenn der Hash-Algorithmus zum Hashing von Passwörtern verwendet wird?

    .. solution::
        :pwd: kein Startpunkt

        Wir haben keinen Block der Nachricht, mit dem wir arbeiten können, und wir haben keinen Vorteil davon, zwei beliebige aber verschiedene Nachrichten zu finden, die denselben Hash haben. Bei der Passwortwiederherstellung liegt uns immer ein Hashwert vor, und wir versuchen, *eine* Nachricht zu finden, die diesen Hashwert erzeugt hat.


`SHA 512 - Übersicht <https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.180-4.pdf>`__
--------------------------------------------------------------------------------------

.. deck::

    .. card::

        .. image:: drawings/sha512/sha512-main.svg
            :alt: Nachricht
            :align: center

    .. card:: overlay

        .. image:: drawings/sha512/sha512-blocks.svg
            :alt: Nachricht
            :align: center


    .. card:: overlay

        .. image:: drawings/sha512/sha512-nth-block.svg
            :alt: Nachricht
            :align: center

    .. card:: overlay

        .. image:: drawings/sha512/sha512-padding.svg
            :alt: Nachricht
            :align: center

.. supplemental::

    - SHA-512 nimmt eine Nachricht beliebiger Größe und gibt einen 512-Bit-Hashwert zurück.
    - Der IV von SHA-512 besteht aus den folgenden acht 64-Bit-Zahlen:

      ::

        6a09e667f3bcc908
        bb67ae8584caa73b
        3c6ef372fe94f82b
        a54ff53a5f1d36f1
        510e527fade682d1
        9b05688c2b3e6c1f
        1f83d9abfb41bd6b
        5be0cd19137e2179

    - Die Addition erfolgt Wortweise modulo :math:`2^{64}`.

    - Die Nachricht wird in 1024-Bit-Blöcke unterteilt. Die Nachricht wird - unabhängig von der tatsächlichen Länge - *immer* aufgefüllt (:eng:`padded`) und auf eine Länge  :math:`l \equiv 896 (mod\, 1024)` Bits gebracht.
    - Das Padding besteht aus einem Bit mit Wert 1, gefolgt von der notwendigen Anzahl Nullen.
    - Am Ende wird die Länge der Nachricht als 128-Bit-Wert angehängt, um ein Vielfaches von 1024 zu erhalten.



SHA-512 Verarbeitung eines 1024-Bit-Blocks
------------------------------------------------------------

.. deck::

    .. card::

        .. image:: drawings/sha512-processing_a_block/main.svg
            :align: center

    .. card:: overlay

        .. image:: drawings/sha512-processing_a_block/main-pass-through.svg
            :align: center

    .. card:: overlay

        .. image:: drawings/sha512-processing_a_block/block.svg
            :align: center

    .. card:: overlay

        .. image:: drawings/sha512-processing_a_block/wi.svg
            :align: center

    .. card:: overlay

        .. image:: drawings/sha512-processing_a_block/constants.svg
            :align: center

.. supplemental::

    Die Additionen erfolgen Modulo :math:`2^{64}`.

    .. rubric:: Berechnung der :math:`W_i`

    :math:`W_0` bis :math:`W_{15}` sind die ersten 16 Wörter des 1024-Bit-Blocks. Die restlichen 64 Wörter werden wie folgt berechnet.

    :math:`w_t = \sigma_1(w_{t-2}) + w_{t-7} + \sigma_0(w_{t-15}) + w_{t-16}`

    Mit:

    :math:`\sigma_0(x) = (x \ggg 1) \oplus (x \ggg 8) \oplus (x \gg 7)`

    :math:`\sigma_1(x) = (x \ggg 19) \oplus (x \ggg 61) \oplus (x \gg 6)`

    .. legend::

        :math:`\gg` ist die Rechtsverschiebung bei der die linke Seite mit Nullen aufgefüllt wird.

        :math:`\ggg` ist die zyklische Rechtsverschiebung.

        :math:`\oplus` steht für die bitweise XOR-Operation.


    .. rubric:: Rundenfunktion

    .. math::

        T_1 = h + Ch(e,f,g) + ( \sum\nolimits_{1}^{512} e ) + K_t + W_t

        T_2 = (\sum\nolimits_{0}^{512} a ) + Maj(a,b,c)

        h = g

        g = f

        f = e

        e = d + T_1

        d = c

        c = b

        b = a

        a = T_1 + T_2

    Weiterhin gilt:

    :math:`t` ist die Schrittnummer

    :math:`Ch(e,f,g) = (e \land f) \oplus (\neg e \land g)`

    :math:`Maj(a,b,c) = (a \land b) \oplus (a \land c) \oplus (b \land c)`

    :math:`\sum\nolimits_{0}^{512} a = a \ggg 28 \oplus a \ggg 34 \oplus a \ggg 39`

    :math:`\sum\nolimits_{1}^{512} e = e \ggg 14 \oplus e \ggg 18 \oplus e \ggg 41`

    .. rubric:: Berechnung der Konstanten

    Die Konstanten :math:`K` sind die ersten 64 Bits der Bruchteile der Kubikwurzeln der ersten 80 Primzahlen.

    Konzeptionell: :math:`((\sqrt[3]{n_{te}\, Primzahl} - \lfloor \sqrt[3]{n_{te}\, Primzahl} \rfloor) << 64)`\ :code:`.toInt().toString(16)`

    **Die SHA-512 Konstanten**

    ::

        428a2f98d728ae22 7137449123ef65cd b5c0fbcfec4d3b2f e9b5dba58189dbbc
        3956c25bf348b538 59f111f1b605d019 923f82a4af194f9b ab1c5ed5da6d8118
        d807aa98a3030242 12835b0145706fbe 243185be4ee4b28c 550c7dc3d5ffb4e2
        72be5d74f27b896f 80deb1fe3b1696b1 9bdc06a725c71235 c19bf174cf692694
        e49b69c19ef14ad2 efbe4786384f25e3 0fc19dc68b8cd5b5 240ca1cc77ac9c65
        2de92c6f592b0275 4a7484aa6ea6e483 5cb0a9dcbd41fbd4 76f988da831153b5
        983e5152ee66dfab a831c66d2db43210 b00327c898fb213f bf597fc7beef0ee4
        c6e00bf33da88fc2 d5a79147930aa725 06ca6351e003826f 142929670a0e6e70
        27b70a8546d22ffc 2e1b21385c26c926 4d2c6dfc5ac42aed 53380d139d95b3df
        650a73548baf63de 766a0abb3c77b2a8 81c2c92e47edaee6 92722c851482353b
        a2bfe8a14cf10364 a81a664bbc423001 c24b8b70d0f89791 c76c51a30654be30
        d192e819d6ef5218 d69906245565a910 f40e35855771202a 106aa07032bbd1b8
        19a4c116b8d2d0c8 1e376c085141ab53 2748774cdf8eeb99 34b0bcb5e19b48a8
        391c0cb3c5c95a63 4ed8aa4ae3418acb 5b9cca4f7763e373 682e6ff3d6b2b8a3
        748f82ee5defb2fc 78a5636f43172f60 84c87814a1f0ab72 8cc702081a6439ec
        90befffa23631e28 a4506cebde82bde9 bef9a3f7b2c67915 c67178f2e372532b
        ca273eceea26619c d186b8c721c0c207 eada7dd6cde0eb1e f57d4f7fee6ed178
        06f067aa72176fba 0a637dc5a2c898a6 113f9804bef90dae 1b710b35131c471b
        28db77f523047d84 32caab7b40c72493 3c9ebe0a15c9bebc 431d67c49c100d4c
        4cc5d4becb3e42b6 597f299cfc657e2a 5fcb6fab3ad6faec 6c44198c4a475817


    **Exemplarischer Code zur Berechnung der Konstanten**

    Der folgende JavaScript Code demonstriert, wie die Konstanten für SHA-512 berechnet werden bzw. wurden. Die Präzision von Standard JavaScript Gleitkommazahlen (64-Bit Double) ist jedoch nicht ganz ausreichend, um die Konstanten vollständig zu berechnen.

    .. code:: javascript
        :class: copy-to-clipboard
        :number-lines:

        const primes = [
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59,
            61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127,
            131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193,
            197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269,
            271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349,
            353, 359, 367, 373, 379, 383, 389, 397, 401, 409 ];

        function* genSHA512Constants() {
            let i = 0;
            while(i < 80) {
                const p = primes[i];
                const cubeRootP = Math.cbrt(p); // == p ** (1/3);
                yield (cubeRootP - Math.floor(cubeRootP));
                ++i;
            }
        }
        for(const c of genSHA512Constants()) {
            console.log(c.toString(16));
        }

    Der folgenden Java Code demonstriert, wie die Konstanten für SHA-512 berechnet werden können. Wir verwenden hier die Klasse `BigDecimal`, um die Konstanten zu berechnen, da Java keinen ``long double`` Typ (mit 128 Bit) kennt.

    .. code:: java
        :class: copy-to-clipboard
        :number-lines:

        /**
         * Compute the cube root using BigDecimals and the Newton-Raphson
         * algorithm.
         *
         * @param n the number for which the cube root should be computed.
         * @param guess the current/initial guess. Can be BigDecimal.ONE.
         * @param the number of steps to be executed. The algorithm is
         *        iterative and the number of steps determines the
         *        precision of the result.
         */
        BigDecimal cbrt(BigDecimal n, BigDecimal guess,  int steps) {
            if (steps == 0) return guess;
            final var newGuess =
                guess.add(
                    guess.pow(3).add(n.negate()).divide(
                        guess.pow(2).multiply(new BigDecimal(3)),
                        MathContext.DECIMAL128
                    ).negate()
                );
            return cbrt(n,newGuess,steps -1);
        }
        /**
         * Given a prime number get the first 64 bits of the fractional
         * part of the cube root.
         */
        String shaConstant(int prime) {
            final var cubeRoot = cbrt(new BigDecimal(prime),BigDecimal.ONE,16);
            // "extract" the fractional by computing modulo 1
            final var fractionalPart = cubeRoot.remainder(BigDecimal.ONE);
            // To extract the first 64 bits we effectively do a shift-left
            // by 64 which we simulate by multiplying with 2^64
            final var bits = fractionalPart.multiply(BigDecimal.TWO.pow(64));
            // to get the HEX representation we use BigInteger's toString
            // method as a convenience method
            return bits.toBigInteger().toString(16);
        }



.. class:: new-section transition-move-left

*Message Authentication Codes* (MACs)
----------------------------------------------

.. supplemental::

    .. hint::

        *Message Authentication Codes* könnte ins Deutsche mit
        Nachrichtenauthentifizierungscodes übersetzt werden, dies ist aber nicht üblich.

        Im allgemeinen (IT-)Sprachgebrauch wird von *MAC*\ s gesprochen.



HMAC (Hash-based Message Authentication Code)
----------------------------------------------

.. container::

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



.. class:: no-title

HMAC Berechnung visualisiert
----------------------------------------------

.. image:: drawings/hmac/hmac_i_o_key_derivation.svg
        :alt: Schlüsselableitung für den inneren und äußeren Schlüssel K'
        :align: left

.. image:: drawings/hmac/hmac_message_hashing.svg
        :alt: Schlüsselableitung für den inneren und äußeren Schlüssel K'
        :align: right
        :class: incremental

.. supplemental::

    **Padding und Hashing**

    Im Rahmen der Speicherung von Passwörtern und *Secret Keys* ist die Verwendung von Padding Operationen bzw. das Hashing von Passwörtern, um Eingaben in einer wohl-definierten Länge zu bekommen, üblich. Neben dem hier gesehenen Padding, bei dem 0x00 Werte angefügt werden, ist zum Beispiel auch das einfache Wiederholen des ursprünglichen Wertes, bis man auf die notwendige Länge kommt, ein Ansatz.

    Diese Art Padding darf jedoch nicht verwechselt werden mit dem Padding, dass ggf. im Rahmen der Verschlüsselung von Nachrichten notwendig ist, um diese ggf. auf eine bestimmte Blockgröße zu bringen (zum Beispiel bei ECB bzw. CBC Block Mode Operations.)



HMAC Berechnung in Python
---------------------------

**Implementierung**

.. code:: python
    :class: copy-to-clipboard
    :number-lines:

    import hashlib
    pwd = b"MyPassword"
    stretched_pwd = pwd + (64-len(pwd)) * b"\x00"

    ikeypad = bytes(map(lambda x : x ^ 0x36 , stretched_pwd)) # xor with ipad
    okeypad = bytes(map(lambda x : x ^ 0x5c , stretched_pwd)) # xor with opad

    hash1 = hashlib.sha256(ikeypad+b"JustAMessage").digest()
    hmac  = hashlib.sha256(okeypad+hash1).digest()

    # hmac =
    #        b'\xab\xa0\xd9\xe2\x8ar\xc8\x081\x8e\x1b\x1d,
    #        \x8f\xa6\xd6L\x94\xab\x89\x9a\x89*\xc7\x0f_no\xc1\xdc6\xfc'

.. supplemental::

    HMAC ist auch direkt als Bibliotheksfunktion verfügbar.

    .. code:: python
        :class: copy-to-clipboard
        :number-lines:

        import hashlib
        import hmac

        hash_hmac = hmac.new(
            b"MyPassword",
            b"JustAMessage",
            hashlib.sha256).digest()

        hash_hmac =
            b'\xab\xa0\xd9\xe2\x8ar\xc8\x081\x8e\x1b\x1d,
            \x8f\xa6\xd6L\x94\xab\x89\x9a\x89*\xc7\x0f_no\xc1\xdc6\xfc'



MAC: `Poly 1305 <https://datatracker.ietf.org/doc/html/rfc8439#section-2.5>`__
--------------------------------------------------------------------------------

.. deck::

    .. card::

        - Ein MAC Algorithmus für die Einmalauthentifizierung von Nachrichten.
        - Entwickelt von Daniel J. Bernstein.
        - Basierend auf einem 256-Bit-Schlüssel und einer Nachricht wird ein 128-Bit-Tag berechnet.
        - In Verbindung mit *ChaCha20* in einer Reihe von Protokollen verwendet.

    .. card::

        .. image:: drawings/poly1305.svg
           :alt: Poly 1305 - Verwendung des Schlüssels
           :align: center

        .. container:: align-center

            .. rubric:: Aufteilung des Schlüssels

    .. card::

        .. rubric:: Verarbeitung der Nachricht

        .. class:: incremental-list list-with-explanations

        - initialisiere den Akkumulator :math:`a` mit 0
        - die Nachricht wird in Blöcke von 16 Byte aufgeteilt und als *little-endian* Zahl verarbeitet; d. h. ein Block hat 16 Oktette (:math:`16 \times 8` Bit)
        - Füge dem Block :math:`n` ein Bit jenseits der Anzahl der Oktette des aktuellen Blocks hinzu :math:`= n'`; d. h. im Falle eines 16-Byte-Blocks wird die Zahl :math:`2^{128}` addiert

          (Danach haben wir ggf. eine 17-Byte-Zahl.)
        - Addiere :math:`n'` aus dem letzten Schritt zum Akkumulator :math:`a` und multipliziere mit :math:`\text{clamped r}`
        - Aktualisiere den Akkumulator mit dem Ergebnis :math:`modulo\, P` mit :math:`P = 2^{130} - 5`:
          :math:`a = ((a + n') \times \text{clamped r})\mod P`


    .. card::

        Beispiel

        .. code:: text

            000  43 72 79 70 74 6f 67 72 61 70 68 69 63 20 46 6f  Cryptographic Fo
            016  72 75 6d 20 52 65 73 65 61 72 63 68 20 47 72 6f  rum Research Gro
            032  75 70                                            up

        .. container::

            Sei :math:`\text{clamped r} = 806d5400e52447c036d555408bed685`

            Sei :math:`s = 1bf54941aff6bf4afdb20dfb8a800301`

        .. deck:: incremental

            .. card::

                .. rubric:: Verarbeitung des ersten Blocks

                .. math::

                    \begin{array}{rr}
                        a      & = & 00 \\
                        n      & = &   6f4620636968706172676f7470797243 \\
                        n'    & = & 016f4620636968706172676f7470797243 \\
                        a + n' & = & 016f4620636968706172676f7470797243 \\
                        (a + n') \times \text{clamped r} & = &
                                b83fe991ca66800489155dcd69e8426ba2779453994ac90ed284034da565ecf \\
                        a = ((a + n') \times \text{clamped r})\, mod\, P & = &
                                2c88c77849d64ae9147ddeb88e69c83fc \\
                    \end{array}

            .. card::

                .. rubric:: Verarbeitung des letzten Blocks

                .. math::

                    \begin{array}{rr}
                        a      & = & 2d8adaf23b0337fa7cccfb4ea344b30de \\
                        n      & = &   7075 \\
                        n'    & = & 017075 \\
                        a + n' & = & 2d8adaf23b0337fa7cccfb4ea344ca153 \\
                        (a + n') \times \text{clamped r} & = &
                                16d8e08a0f3fe1de4fe4a15486aca7a270a29f1e6c849221e4a6798b8e45321f \\
                        a = ((a + n') \times \text{clamped r})\, mod\, P & = &
                                28d31b7caff946c77c8844335369d03a7 \\
                    \end{array}

            .. card::

                .. rubric:: Abschuss

                Addiere auf den Wert des Akkumulators :math:`a` den Wert :math:`s`.

                Und somit ist der Tag :math:`2a927010caf8b2bc2c6365130c11d06a8` (als Zahl im little-endian Format).


.. supplemental::

    .. rubric:: Hinweise

    In dieser Diskussion betrachten wir jeden Block der Nachricht als „große Zahl“.

    :math:`P = 2^{130} - 5 = 3fffffffffffffffffffffffffffffffb`

    Dadurch, dass wir den Block als Zahl in *little-endian* Reihenfolge interpretieren, ist das hinzufügen des Bits jenseits der Anzahl der Oktette gleichbedeutend damit, dass wir den Wert 0x01 am Ende des Blocks hinzufügen.


.. TODO discuss CBC-MAC


Zusammenfassung
-------------------

.. class:: incremental-list list-with-explanations

- Ein Hashwert dient der Integritätssicherung von Nachrichten.
- Ein Mac dient der Authentifizierung von Nachrichten.
- Ein Mac sichert auch immer die Integrität der Nachricht.

  Es ist somit möglich die Integrität einer Nachricht zu sichern ohne Authentizität zu gewährleisten, aber nicht umgekehrt.
- Ein Mac erlaubt es dem Empfänger eine gefälschte Nachricht zu erkennen aber ggf. auch zu erstellen (:eng:`to forge a message`).
- Eine Signatur basiert auf einem Hashwert und einem privaten Schlüssel.
- Der Empfänger kann bei einer signierten Nachricht, diese nicht verändern und als eine Nachricht des Senders ausgeben.
- Nur für Nachrichten, die signiert sind, gilt somit die Nichtabstreitbarkeit (:eng:`non-repudation`).
