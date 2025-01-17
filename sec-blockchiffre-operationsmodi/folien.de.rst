.. meta:: 
    :version: genesis
    :author: Michael Eichberg
    :keywords: Blockchiffren, Operationsmodi, ECB, CBC, CFB, OFB, CTR, XTS-AES
    :description lang=en: Block Cipher Operations
    :description lang=de: Betriebsmodi bei Blockchiffren
    :id: lecture-security-blockchiffre-operationsmodi
    :first-slide: last-viewed
    :exercises-master-password: WirklichSchwierig!

.. |html-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html
.. |pdf-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html.pdf

.. role:: incremental
.. role:: ger
.. role:: red
.. role:: green 
.. role:: blue 
.. role:: smaller
.. role:: eng
.. role:: raw-html(raw)
    :format: html
    
    

Betriebsmodi bei Blockchiffren
===============================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.1
:Basierend auf: *Cryptography and Network Security - Principles and Practice, 8th Edition, William Stallings*

.. supplemental::

  :Folien: 
      :HTML: |html-source|

      :PDF: |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues


Betriebsmodi
--------------------

- Eine Technik zur Verbesserung der Wirkung eines kryptografischen Algorithmus oder zur Anpassung des Algorithmus an ein Anwendungsszenario. Insbesondere in Abhängigkeit von der Länge des Klartexts.

.. class:: incremental

- Um eine Blockchiffre in einer Vielzahl von Anwendungen einsetzen zu können, hat das NIST fünf Betriebsmodi definiert.

  - Die fünf Modi decken eine breite Palette von Verschlüsselungsanwendungen ab, für die eine Blockchiffre verwendet werden kann.
  - Diese Modi sind für die Verwendung mit jeder symmetrischen Blockchiffre vorgesehen, einschließlich 3DES und AES.



Betriebsmodi - Übersicht
------------------------------

.. container:: scrollable

    .. csv-table::
        :class: smaller highlight-line-on-hover 
        :width: 100%
        :header: Modus, Beschreibung, Typische Anwendung

        **Electronic Codebook (ECB)**, Jeder Block von Klartextbits wird unabhängig voneinander mit demselben Schlüssel verschlüsselt., "
        • Sichere Übertragung einzelner Werte (z. B. eines Verschlüsselungsschlüssels)
        "
        **Cipher Block Chaining (CBC)**, Die Eingabe für den Verschlüsselungsalgorithmus ist die XOR-Verknüpfung des nächsten Klartextblocks mit dem vorangegangenen Chiffretextblock., " 
        - Universelle blockorientierte Übertragung 
        - Authentifizierung
        "
        **Cipher Feedback (CFB)**, "Die Eingabe wird Bit für Bit verarbeitet.
        Der vorhergehende Chiffretext wird als Eingabe für den Verschlüsselungsalgorithmus verwendet, um eine pseudozufällige Ausgabe zu erzeugen, die mit dem Klartext XOR-verknüpft wird, um die nächste Einheit des Chiffretextes zu erzeugen.", " 
        - Allgemeine stromorientierte Übertragung
        - Authentifizierung
        " 
        **Output Feedback (OFB)**, "Ähnlich wie CFB, mit dem Unterschied, dass die Eingabe für den Verschlüsselungsalgorithmus die vorangegangene Verschlüsselungsausgabe ist, und volle Blöcke verwendet werden.", " 
        • Stromorientierte Übertragung über verrauschte Kanäle (z. B. Satellitenkommunikation) 
        "
        "**Counter (CTR**)", "Jeder Klartextblock wird mit einem verschlüsselten Zähler XOR-verknüpft. Der Zähler wird für jeden nachfolgenden Block erhöht.", " 
        - Blockorientierte Übertragung für allgemeine Zwecke
        - Nützlich für Hochgeschwindigkeitsanforderungen
        "



.. class:: new-subsection transition-fade

Grundlegende Blockchiffren
--------------------------------



Electronic Codebook
--------------------

.. image:: opensource-drawings/ecb_encryption.svg
    :width: 1200px
    :align: center 

.. image:: opensource-drawings/ecb_decryption.svg
    :width: 1200px
    :align: center 

.. container:: far-far-smaller
    
    Autor: https://commons.wikimedia.org/wiki/User:WhiteTimberwolf



Probleme bei der Verwendung der Verschlüsselung im ECB-Modus
----------------------------------------------------------------

.. container:: two-columns

    .. container:: column no-separator

        *ECB-Tux* - der Linux-Pinguin verschlüsselt im ECB-Modus:

        Quelle: https://github.com/robertdavidgraham/ecb-penguin

    .. image:: opensource-drawings/tux.ecb.from_robert_david_graham.png
        :align: center
  
Kriterien und Eigenschaften für die Bewertung und Konstruktion von Blockchiffre-Betriebsarten, die ECB überlegen sind.

- Overhead
- Fehlerbehebung 
- Fehlerfortpflanzung
- Streuung
- Sicherheit



Cipher Block Chaining
----------------------

.. image:: opensource-drawings/cbc_encryption.svg
    :width: 1200px
    :align: center 

.. container:: incremental

    .. image:: opensource-drawings/cbc_decryption.svg
        :width: 1200px
        :align: center 

    .. container:: far-far-smaller
        
        Autor: https://commons.wikimedia.org/wiki/User:WhiteTimberwolf



.. class:: new-subsection transition-fade

Blockchiffren, die als Stromchiffren verwendet werden können.
---------------------------------------------------------------




Konvertierung von Blockchiffren in Stromchiffre
------------------------------------------------

.. admonition:: Hinweis
    :class: note smaller

    Es gibt drei Modi, die es ermöglichen, eine Blockchiffre in eine zeichenorientierte Stromchiffre umzuwandeln:

    - Cipher Feedback Mode (CFB)
    - Output Feedback Mode (OFB)
    - Counter Mode (CTR)

    D. h., es ist kein Auffüllen (:eng:`Padding`) erforderlich, wenn die Nachricht nicht ein Vielfaches der Blockgröße ist.

Bei AES, DES oder jeder anderen Blockchiffre erfolgt die Verschlüsselung immer Block-für-Block mit Blockgrößen von b Bits:

- Im Fall von (3)DES: :math:`b=64` 
- Im Fall von AES: :math:`b=128`




*Cipher Feedback Mode*
-----------------------

.. image:: opensource-drawings/cfb_encryption.svg
    :width: 1200px
    :align: center 

.. container:: incremental

    .. image:: opensource-drawings/cfb_decryption.svg
        :width: 1200px
        :align: center 

    .. container:: far-far-smaller
        
        Autor: https://commons.wikimedia.org/wiki/User:WhiteTimberwolf



*Cipher Feedback Mode* als Stromchiffre
--------------------------------------------

.. image:: drawings/operationsmodi/cfb_s_bits.svg
    :width: 100%
    :align: center 



*Output Feedback Mode*
------------------------

.. image:: opensource-drawings/ofb_encryption.svg
    :width: 1200px
    :align: center 

.. container:: incremental
        
    .. image:: opensource-drawings/ofb_decryption.svg
        :width: 1200px
        :align: center 

    .. container:: far-far-smaller
        
        Autor: https://commons.wikimedia.org/wiki/User:WhiteTimberwolf

.. When used as a Stream Cipher we can simply discard the last bytes of the encrypted block cipher.



*Counter Mode*
-----------------

.. image:: opensource-drawings/ctr_encryption.svg
    :width: 1200px
    :align: center 

.. container:: incremental
        
    .. image:: opensource-drawings/ctr_decryption.svg
        :width: 1200px
        :align: center 

    .. container:: far-far-smaller
        
        Autor: https://commons.wikimedia.org/wiki/User:WhiteTimberwolf



*Counter Mode* - Vorteile
-------------------------

:Hardware-Effizienz: kann von der Parallelisierung der Hardware profitieren
:Software-Effizienz: leicht parallelisierbar in Software
:Vorverarbeitung: die Verschlüsselung der Zähler
:Zufälliger Zugriff: der i-te Block des Klartextes/des Chiffretextes kann im Zufallszugriff verarbeitet werden
:Nachweisbare Sicherheit: genauso sicher wie die anderen Verfahren
:Einfachheit: es wird nur der Verschlüsselungsalgorithmus benötigt



Rückkopplungseigenschaften\ [#]_  der Betriebsmodi
-------------------------------------------------------------------------------

.. image:: drawings/operationsmodi/feedback_characteristics.svg
    :width: 1750px
    :align: center 

.. [#] (:eng:`Feedback Characteristics`)



.. class:: new-subsection transition-fade

Spezielle Betriebsmodi
--------------------------------



XTS-AES Modus für block-orientierte Speichergeräte
---------------------------------------------------

2010 vom NIST als zusätzlicher Blockchiffre-Betriebsmodus genehmigt.

Modus ist auch ein IEEE-Standard, IEEE Std 1619-2007
 
.. admonition:: Frage
    :class: note

    Welche potenziellen Bedrohungen sind relevant?

    .. many similar blocks
    .. data is freely accessible

- Die Norm beschreibt eine Verschlüsselungsmethode für Daten, die in sektor-basierten Geräten gespeichert sind, wobei das Bedrohungsmodell einen möglichen Zugriff des Gegners auf die gespeicherten Daten beinhaltet.
  
- Hat breite Unterstützung der Industrie erhalten.



*Tweakable* Blockchiffren - Bestandteile
-----------------------------------------------

- Der XTS-AES-Modus basiert auf dem Konzept einer veränderbaren (:eng:`tweakable`) Blockchiffre.
- Um den Chiffriertext zu berechnen, wird benötigt:

  - **Klartext**
  - **Symmetrischer Schlüssel**
  - **Tweak**

- Der *Tweak* muss nicht geheim gehalten werden; der Zweck ist, Variabilität zu bieten. 

.. supplemental::

    Ein Tweak ist insbesondere bei der Verschlüsselung von Daten auf Speichergeräten wichtig, da der gleiche Klartext an verschiedenen Stellen in verschiedene Chiffretexte verschlüsselt wird, aber immer in denselben Chiffretext, wenn er wieder an dieselbe Stelle geschrieben wird.



*Tweakable* Blockchiffren - grundlegende Struktur
-----------------------------------------------------

.. image:: drawings/operationsmodi/tweakable_block_cipher.svg
    :width: 1750px
    :align: center 



Anforderungen an die Speicherverschlüsselung
-----------------------------------------------

Die Anforderungen an die Verschlüsselung gespeicherter Daten, die auch als *data at rest* bezeichnet werden, unterscheiden sich von denen für übertragene Daten.

Die IEEE Norm P1619 wurde in Hinblick auf folgende Eigenschaften entwickelt:

.. class:: incremental smaller

- Der Chiffretext ist für einen Angreifer frei verfügbar.
- Das Datenlayout wird auf dem Speichermedium und beim Transport nicht verändert.
- Der Zugriff auf die Daten erfolgt in Blöcken fester Größe und unabhängig voneinander.
- Die Verschlüsselung erfolgt in 16-Byte-Blöcken, die unabhängig voneinander sind.
- Es werden keine weiteren Metadaten verwendet, außer der Position der Datenblöcke innerhalb des gesamten Datensatzes.
- Derselbe Klartext wird an verschiedenen Stellen in verschiedene Chiffretexte verschlüsselt, aber immer in denselben Chiffretext, wenn er wieder an dieselbe Stelle geschrieben wird.
- Ein standardkonformes Gerät kann für die Entschlüsselung von Daten konstruiert werden, die von einem anderen standardkonformen Gerät verschlüsselt wurden.


XTS-AES Operation auf einem Block
------------------------------------

.. image:: drawings/operationsmodi/xts_aes.svg
    :width: 1750px
    :align: center 

.. container:: far-far-smaller two-columns margin-top-1em
    
    .. container:: column no-separator

      - Schlüssel: es gilt: :math:`Schlüssel = Schlüssel_1\, ||\, Schlüssel_2` 
      - :math:`P_j`: Der j-te Block des Klartexts. Alle Blöcke haben eine Länge von 128 bits. Eine (Klartext)dateneinheit – in der Regel ein Festplattensektor – besteht aus einer Folge von Klartextblöcken.
      - :math:`C_j`: Der j-te Block des Chiffretextes.
      - :math:`j`: Die fortlaufende Nummer des 128-Bit-Blocks innerhalb der Dateneinheit.
    
    
    .. container:: column

      - :math:`i`: Der Wert des 128-Bit-Tweaks.
      - :math:`\alpha`: Ein primitives Element des :math:`GF(2^{128})` welches dem Polynom :math:`x` (d. h. 0000...0010) entspricht.
      - :math:`\alpha^j`: :math:`\alpha` :math:`j` mal mit sich selbst multipliziert im Körper :math:`GF(2^{128})`  
      - :math:`\oplus` Bitwise XOR
      - :math:`\otimes` Modulare Multiplikation mit Binärkoeffizienten modulo :math:`x^{128}+x^7+x^2+x+1`.  


.. class:: integrated-exercise transition-scale

Übung
---------------------

- \
  
  .. exercise:: Der Initialisierungsvektor (IV) bei CBC

     Warum ist es bei CBC wichtig, den Initialisierungsvektor (IV) zu schützen?

     .. solution::
        :pwd: IV und CBC
    
        Wenn der IV im Klartext gesendet wird, können wir in bestimmten Szenarien einige Bits des Klartextes (des ersten Blocks) im Rahmen einer MITM Attacke umdrehen, wenn wir den IV ändern (`Bit Flipping Attack <https://en.wikipedia.org/wiki/Bit-flipping_attack>`__). D. h. wir fangen die Nachricht ab, ändern den IV und senden die Nachricht weiter. Wenn der Empfänger die Nachricht entschlüsselt, dann ist der erste Block des Klartextes gezielt verändert. Wenn man Kenntnisse über den Aufbau/Inhalt des ersten Blocks hat, dann kann dies dazu führen, dass man die Daten gezielt verändert.


- \
  
  .. exercise:: Padding
    
     In welchen Betriebsarten ist eine Auffüllung (:eng:`Padding`) notwendig?

     .. solution::
     
        ECB und CBC (die Eingabe für die Verschlüsselung ist ein vollständiger Klartextblock).

- \
  
  .. exercise:: Auswirkungen eines Bitflips

     Was geschieht im Falle eines Übertragungsfehlers (einzelner Bitflip im Chiffretext) bei ECB, CBC, CFB, OFB, CTR?
   
     .. solution::
        :pwd: bitFlip

        :ECB: ein Block ist betroffen (im Falle von DES und AES ca. 50% der Bits).
        :CBC: im nächsten Block haben wir ein gespiegeltes Bit im Klartext und ca. 50% im aktuellen Block.
        :CFB: Das umgedrehte Bit beeinflusst das entsprechende Klartextbit und alle nachfolgenden Bits mit einer Wahrscheinlichkeit von ca. 50%, solange das umgedrehte Bit als Eingabe für die Verschlüsselung verwendet wird.
        :OFB, CTR: Im Klartext wird ein Bit umgedreht.

- \
  
  .. exercise:: Nonce bei OFB
 
     Warum muss der IV bei OFB eine Nonce sein?

     .. solution::
        :pwd: nOnce
 
        Die O_i hängen nur vom Schlüssel und dem Initialisierungsvektor ab. Wenn der IV mit demselben Schlüssel wiederverwendet wird und wir zufällig einen bestimmten Klartext kennen, können wir möglicherweise einen entsprechenden Chiffretext in einer anderen Nachricht entschlüsseln.


.. supplemental::

    Eine Nonce (:eng:`Number used ONCE`) ist eine Zahl, die nur einmal für die Ausführung des Verschlüsselungsalgorithmus verwendet wird.



.. class:: integrated-exercise transition-scale

Übung
---------------------

- \
  
  .. exercise:: ECB?

     Sie möchten feststellen, ob ein Programm zur Verschlüsselung von Dateien den ECB-Modus verwendet. Was müssen Sie tun?

     .. solution::
        :pwd: ecb_Erkennung

        Verwenden Sie ein Dokument, das aus mehreren Blöcken besteht, wobei jeder Block die Größe der zugrunde liegenden Chiffre hat und jeder Block den gleichen Inhalt hat. Bei Verwendung des ECB-Modus werden alle Blöcke auf die gleiche Weise verschlüsselt.

- \

  .. exercise:: XTS-AES

    Wie viele Blöcke hat eine Dateneinheiten, wenn ein Festplattensektor 4 KiB groß ist?

    Welchen praktischen Vorteil hat es, das der Hash T vor und nach der Verschlüsselung des Klartextes mit dem aktuellen Wert XOR-verknüpft wird?

    .. solution:: 
        :pwd: XTS-AES

        Wenn ein Festplattensektor 4 KiB groß ist und ein Block eine Größe von 128 Bit (16 Byte) hat, dann gilt, dass ein Sektor 4096/16 = 256 Blöcke hat.

        Dadurch kann der Selbe Algorithmus für die Verschlüsselung und die Entschlüsselung verwendet werden. 





.. class:: integrated-exercise

Übung 
---------------------

.. container:: far-far-smaller

    .. exercise:: OFB-Modus
        
        Verwenden Sie den OFB-Modus in Kombination mit einer Caesar-Chiffre, bei der die Blockgröße ein Zeichen sei. Der Schlüssel ist die Anzahl der Zeichen, um die Sie ein Zeichen verschieben wollen - wie zuvor. Der IV ist ein Zeichen. Damit sie ein XOR durchführen können, ordnen wir jedem Zeichen einen Wert zu und erweitern das Alphabet um die Ziffern 1 bis 3, "!", "?" und das "_". Auf diese Weise ist es immer möglich, ein sinnvolles Zeichen auszugeben. 

        Daraus ergibt sich die folgende Kodierung:

        .. container:: three-columns far-smaller

            .. container:: column  no-separator
                        
                .. csv-table::
                    :header: Index, Zeichen, Binärdarstellung

                    0, A, 00000 
                    1, B, 00001 
                    2, C, 00010 
                    3, D, 00011 
                    4, E, 00100 
                    5, F, 00101 
                    6, G, 00110 
                    7, H, 00111 
                    8, I, 01000 
                    9, J, 01001 
                    10, K, 01010

            .. container:: column no-separator
                        
                .. csv-table::
                    :header: Index, Zeichen, Binärdarstellung
 
                    11, L, 01011 
                    12, M, 01100 
                    13, N, 01101 
                    14, O, 01110 
                    15, P, 01111 
                    16, Q, 10000 
                    17, R, 10001 
                    18, S, 10010 
                    19, T, 10011 
                    20, U, 10100 
                    21, V, 10101 

            .. container:: column
                        
                .. csv-table::
                    :header: Index, Zeichen, Binärdarstellung
                    
                    22, W, 10110 
                    23, X, 10111 
                    24, Y, 11000 
                    25, Z, 11001 
                    26, 1, 11010
                    27, 2, 11011
                    28, 3, 11100
                    29, !, 11101
                    30, ?, 11110
                    31, "_", 11111

        Verschlüsseln Sie nun einige Nachrichten mit dieser Chiffre. Welchen Effekt hat die Anwendung des OFB-Modus auf die Nachrichten?

        .. solution::
            :pwd: Caesar_ofb

            Das gleiche Klartextzeichen wird nicht mehr (notwendigerweise) dem gleichen Chiffretextzeichen zugeordnet, wenn es in der ursprünglichen Nachricht wieder auftaucht, d. h. es liegt eine gewisse Diffusion vor.

            .. admonition:: Beispiel - Verschlüsselung
            
                .. math::
            
                    IV = A, k = 3, M = AA

                    1. I_1 = IV = A; E(I_1) = D; C_1 = A \oplus D = D

                    2. I_2 = D; E(I_2) = G, C_2 = A \oplus G = G
        
            .. admonition:: Beispiel - Entschlüsselung
            
                .. math:: 
                    
                    IV = Z, k = 3, C = T
                
                    E(IV) = 3, M = T \oplus 3 = P\qquad (10011_b \oplus 11100_b = 01111_b = P) 

