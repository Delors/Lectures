.. meta::
   :version: renaissance
   :lang: de
   :author: Michael Eichberg
   :keywords: Hashing
   :description lang=de: Kontrollaufgaben bzgl. Hashfunktionen
   :id: vorlesung-hashfunktionen-kontrollaufgaben
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Hashfunktionen - Kontrollaufgaben
================================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0



.. class:: new-section

Verständnis
---------------------------------------



.. class:: exercises

Hashfunktionen
---------------------------

.. story::

    .. exercise:: Beurteilen Sie folgende Aussagen

        .. class:: incremental-list dhbw

        1. Die Anforderungen an eine Hashfunktion sind vom Einsatzbereich abhängig.
        2. Zur Virenerkennnung werden - unter anderem - auch Hashfunktionen verwendet.
        3. Hashfunktionen sind nicht deterministisch.
        4. Hashfunktionen können zur Absicherung der Integrität verwendet werden.
        5. Die Mindestausgabelänge für eine sichere Hashfunktion beträgt mindestens 512 Bit.
        6. Der Input eine Hashalgorithmuss muss ein vielfaches der Blockgröße des Hashes betragen.
        7. Poly 1305 ist nur deswegen sicher weil die Nonce 96Bit lang ist.
        8. Jeder Hashalgorithmus, der starke Kollisionsresistenz bietet, kann zur Absicherung der Integrität verwendet werden.
        9. Jeder Hashalgorithmus, der schwache Kollisionsresistenz bietet, kann zum Hashing von Passwörtern verwendet werden.
        10. Preimage Resistance ist nur beim Hashen von Passwörtern relevant.
        11. HMAC und Poly 1305 sind Algorithmen, die dem gleichen Zweck dienen.
        12. Bei Poly 1305 und HMAC kann der Schlüssel mehrfach verwendet werden.
        13. Bei HMACs wird der Schlüssel, der als Eingabe dient immer erst gehasht.
        14. Poly 1305 ist eine Merkle-Damgard Konstruktion.
        15. Eine Nonce ist eine Zahl, die einmal generiert wird und möglichst zufällig sein sollte. Danach kann diese für die Absicherungen mehrer Verbindungen mit den selben Partnern wiederverwendet werden.


        .. solution::
            :pwd: HashMesh

            1. Ja
            2. Ja - aber es werden auch weitere Techniken verwendet.
            3. Nein - sie sind deterministisch.
            4. Ja - (Kryptografische) Hashfunktionen können zum Beispiel zur Überprüfung der Integrität von Dateien verwendet werden; allerdings  nur solche, die (noch) sicher sind. Ist es gezielt möglich Kollisionen zu erzeugen, dann gilt dies nicht mehr!
            5. Nein - SHA 256 ist zum Beispiel noch immer sicher.
            6. Nein - es findet ggf. einfach ein internes Padding statt (z.B. SHA-2 Familie). Weiterhin gibt es auch Hash- oder MAC-Verfahren wie Poly1305, bei denen die interne Struktur anders funktioniert und eine klassische Blockgröße keine direkte Rolle spielt.
            7. Nein - die Länge der Nonce bestimmt nur wie viele Nachrichten bei Verwendung des selben Passworts erzeugt werden können.
            8. Ja - in Hinblick auf alle bekannten Algorithmen. Sollte es jedoch einen Algorithmus geben, der zwar starke Kollisionsresistenz bietet, aber die Einwegeigenschaft nicht erfüllen sollte, dann wäre dieser auch zur Integritätssicherung nicht geeignet.
            9. Nein - beim Hashing ist insbesondere (Preimage Resistance) relevant.
            10. Je nach Einsatzbereich (z. B. Integritätssicherung) ist ggf. insbesondere Second Preimage Resistace notwendig. Insbesondere immer dann, wenn bereits eine Nachricht *und* ein Hashwert vorliegt. Die Möglichkeit zu einem Hashwert irgendeine Nachricht zu finden, die ggf. den gleichen Hashwert hat, ist in in diesen Fällen zwar nicht gewünscht aber im Vergleich weniger kritisch.
            11. Beides sind MACs und in dieser Hinsicht dienen Sie dem gleichen Zweck.
            12. Bei HMACs ja - bei Poly1305 ist dies nicht der Fall. Hier ist es ggf. möglich den Schlüssel zu brechen bzw. zu finden, wenn für mehrere Nachrichten der selbe Schlüssel verwendet wird. Poly 1305 dient der *Einmal*\ authentifizierung.
            13. Nein - nur wenn dieser zu lange ist; sonst wird ggf. gepaddet!
            14. Nein - die SHA 2 Familie ist eine Merkle-Damgard Konstruktion.
            15. Nonce steht für “number used once”, also eine einmal verwendete Zahl. Eine Wiederverwendung sollte nicht erfolgen!
