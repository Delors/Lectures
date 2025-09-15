.. meta::
   :version: renaissance
   :lang: de
   :author: Michael Eichberg
   :keywords: Kontrollaufgaben, AES
   :description lang=de: Kontrollaufgaben bzgl. AES
   :id: vorlesung-aes-kontrollaufgaben
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



AES - Kontrollaufgaben
================================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0



.. class:: new-section

AES - Grundlagen
---------------------------------------



.. class:: exercises

Übung
--------

.. exercise:: Grundlagen

  1. Was sind die Blockgröße und die typischen Schlüsselgrößen bei AES?

  2. Welche vier Hauptoperationen werden in einem AES-Rundendurchlauf durchgeführt? Erläutern Sie kurz jede davon.

  3. Was ist die Rolle des "Key Schedule" in AES? Wie viele Rundenschlüssel werden bei AES-128 erzeugt?

  4. Wie unterscheidet sich AES-128 von AES-256 außer durch die Schlüssellänge?

  5. Was versteht man unter "Diffusion" und "Konfusion"? Wie werden diese Konzepte in AES umgesetzt?

  .. solution::
    :pwd: AESAESAES

    1. AES verwendet eine feste Blockgröße von 128 Bit. Die Schlüsselgrößen können 128, 192 oder 256 Bit betragen.

    2. **SubBytes**: Nichtlineare Byte-Ersetzung mittels S-Box.

       **ShiftRows**: Zyklisches Verschieben der Zeilen im State.

       **MixColumns**: Lineare Transformation jeder Spalte zur Diffusion.

       **AddRoundKey**: XOR des States mit dem Rundenschlüssel.

    3. Der Key Schedule erzeugt aus dem ursprünglichen Schlüssel eine Serie von Rundenschlüsseln. Bei AES-128 sind es 11 Rundenschlüssel (1 Initial + 10 Runden).

    4. AES-256 führt mehr Runden durch (14 statt 10) und verwendet einen längeren Key Schedule. Dies erhöht die Sicherheit, aber auch die Rechenkosten.

    5. **Diffusion**: Kleine Änderungen im Klartext verändern viele Bits im Ciphertext → erreicht durch MixColumns und ShiftRows.
       **Konfusion**: Beziehung zwischen Schlüssel und Ciphertext ist komplex → erreicht durch SubBytes und AddRoundKey.



.. class:: new-section

AES - rechnen
---------------------------------------


.. class:: exercises

Rechenübung
------------------

.. exercise:: AES durchführen

    Nehmen wir an, dass der Zustand (*State*) folgendermaßen sei:

    ::

        6A 59 CB BD
        4E 48 12 A0
        98 9E 30 9B
        8B 3D F4 9B

    Führen Sie die *Mix Columns Transformation* durch für das fehlende Feld (:math:`S'_{0,3}`):

    ::

        15 C9 7F ??
        CE 4D 4B CB
        89 71 BE 86
        65 47 97 CA

    .. solution::
        :pwd: Das Ergebnis ist

        Zu rechnen: :math:`S'_{0,3} = 02 × BD ⨁ 03 × A0 ⨁ 9B ⨁ 9B`

        .. math::

            \begin{matrix}
              & 0111\, 1010b\\
            ⨁ & 0001\, 1011b\\
            \\
            ⨁ & 1010\, 0000b \\
            ⨁ & 0100\, 0000b \\
            ⨁ & 0001\, 1011b \\
            \\
            ⨁ & 1001\, 1011b \\
            \\
            ⨁ & 1001\, 1011b \\
            \\
            = & 1001\, 1010b \\
            = & 9A \\
            \end{matrix}
