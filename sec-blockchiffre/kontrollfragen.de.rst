.. meta::
   :version: renaissance
   :lang: de
   :author: Michael Eichberg
   :keywords: Blockchiffren, Kontrollfragen
   :description lang=de: Kontrollfragen bzgl. Einführung in Blockchiffren
   :id: vorlesung-sec-blockchiffren-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Kontrollfragen: Blockchiffren
================================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0.1



.. class:: new-section

Blockchiffren - Allgemein
---------------------------



.. class:: exercises

Kontrollfragen
----------------

.. story::

    .. exercise:: Wie unterscheidet sich eine Blockchiffre von einer Stromchiffere?

        .. solution::
            :pwd: Bloecke...

            Eine Blockchiffre verarbeitet Daten in Blöcken zu (typischerweise) 8 oder 16 Byte (64-128Bit); Stromchiffre Byte für Byte.

    .. exercise:: Erfolgt symmetrische Verschlüsselung immer mit Blockchiffren?
        :class: incremental

        .. solution::
            :pwd: Nein-!

            Nein - es gibt auch Stromchiffren.

    .. exercise:: Sind Blockchiffren Stromchiffren technisch überlegen?
        :class: incremental

        .. solution::
            :pwd: Nicht immer..

            Insbesondere AES genießt umfangreiche Hardwareunterstützung und ist deswegen effizient; im Allgemeinen kann keine solche Aussage getroffen werden.

    .. exercise:: Welche grundlegenden Techniken sollten Blockchiffren immer umsetzen, um welche Ziele zu erreichen?
        :class: incremental

        .. solution::
            :pwd: ts->kf

            Transposition/Permutation und Substitution, um Diffusion und Konfusion zu erreichen.

    .. exercise:: Welchem Zweck soll die Diffusion bzw. Konfusion dienen?
        :class: incremental

        .. solution::
            :pwd: Statistiken...

            Diffusion: statistische Beziehung zwischen Klartext und Chiffretext wird so komplex wie möglich

            Konfusion: die Beziehung zwischen den Statistiken des Chiffriertextes und dem Wert des Chiffrierschlüssels so komplex wie möglich zu gestalten

    .. exercise:: Was ist der Lawineffekt?
        :class: incremental

        .. solution::
            :pwd: er rollt dahin...

            Der Lawineneffekt besagt, dass bei einer Chiffre eine minimale Änderung (d. h. 1 Bit) sich auf alle Bits mit einer Wahrscheinlichkeit von 1/2 auswirken sollten.

    .. exercise:: Warum ist eine doppelte Verschlüsselung nicht effektiv?
        :class: incremental

        .. solution::
            :pwd: kein gEwinn

            Es bringt aus kryptographischer Sicht praktisch keinen Gewinn. (*Meet-in-the-middle attack*)



.. class:: new-section

Blockchiffren - Feistel
---------------------------



.. class:: exercises

Kontrollfragen
----------------

.. story::

    .. exercise:: Warum wird am Ende einer Verschlüsselung (und Entschlüsselung) noch eine SWAP-Operation durchgeführt?
        :class: incremental

        .. solution::
            :pwd: selbeAlgo

            Damit derselbe Algo verwendet werden kann für die Verschlüsselung und Entschlüsselung.


    .. exercise:: Welche Anforderung muss die Rundenfunktion F erfüllen?
        :class: incremental

        .. solution::
            :pwd: nonlinear

            Die Funktion muss nicht-linear sein.

    .. exercise:: Was kann passieren, wenn die Anzahl der Runden zu klein ist?
        :class: incremental

        .. solution::
            :pwd: Angriffsmgl

            Es bietet sich dann die Möglichkeit für bestimmte Angriffstechniken. Zum Beispiel differentielle Kryptoanalyse.

    .. exercise:: Wenn wir nur eine Runde eine Feistelchiffre anwenden, sind dann bereits alle Daten zumindest rudimentär verschlüsselt?
        :class: incremental

        .. solution::
            :pwd: GanzUndGarNicht

            Nein, nach der ersten Runden ist gerade mal die Hälfte der Blöcke überhaupt verarbeitet worden.

    .. exercise:: Welche Block- und Schlüsselgrößen müssen Feistelchiffren haben?
        :class: incremental

        .. solution::
            :pwd: nicht-definiert

            Eine Feistelchiffre ist eine allg. Konstruktionsmethode. Es gibt keine konkreten Vorgaben.

    .. exercise:: Was ist bei der Generierung des Unterschlüssel bei Ver- und Entschlüsseln zu beachten?
        :class: incremental

        .. solution::
            :pwd: genau umgekehrt, sie sein sollten

            Der Algorithmus kann unverändert verwendet werden, aber der "Keyschedule" muss invertiert werden!



.. class:: new-section

Blockchiffren - DES
---------------------------



.. class:: exercises

Kontrollfragen
----------------

.. story::

    .. exercise:: Welche Schlüsselgröße hat DES?
        :class: incremental

        .. solution::
            :pwd: seltsam

            Ein DES Schlüssel hat effektiv nur 56Bit; die anderen Bit sind Paritätsbits.

    .. exercise:: Welche Aussage über DES ist korrekt?
       :class: incremental

       - DES basiert auf einem Feistel-Netzwerk mit 16 Runden.
       - Beim Design von DES wurde das Kerckhoff Prinzip eingehalten.
       - DES ist gegen Brute-Force-Angriffe immun.

       .. solution::
          :pwd: 2 von 3

          DES basiert auf einem Feistel-Netzwerk mit 16 Runden. DES ist eine Blockchiffre und nicht immun gegen Brute-Force-Angriffe. DES wurde zwar nicht öffentlich entwickelt, aber der Algorithmus wurde veröffentlicht und deswegen wird das Kerckhoffs-Prinzip eingehalten.


    .. exercise:: Was versteht man unter Triple-DES (3DES)?
       :class: incremental

       .. solution::
            :pwd: da wird dahingetrippelt

            Triple-DES (3DES) ist eine Methode, die DES dreimal hintereinander mit unterschiedlichen Schlüsseln anwendet, um die Sicherheit zu erhöhen. Es gibt Varianten mit zwei oder drei verschiedenen Schlüsseln.

    .. exercise:: Was ist der Zweck der Initialen Permutation (IP) in DES?
        :class: incremental

        .. solution::
            :pwd: Diffusion...

            Die Initiale Permutation (IP) dient der Umordnung der Bits des Klartexts vor den Rundenoperationen. Sie erhöht die Diffusion, ist jedoch kryptographisch nicht entscheidend.
