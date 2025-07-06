.. meta::
    :version: renaissance
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
:Version: 1.0

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

            Wie bewerten Sie die Sicherheit (dieses Protokolls/Ansatzes)?

        .. presenter-note::

            Das Problem ist hierbei, dass das Passwort auf dem Server im Klartext gespeichert wird. Dies kann zu Sicherheitslücken führen, wenn der Server kompromittiert wird oder wenn der Serveradministrator unberechtigten Zugriff hat.


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

                .. cell:: width-60

                    .. class:: incremental-list

                    - Peggy wählt einen der Wege zur Tür, während Victor an der Stelle A steht und darauf wartet, dass Sie bei der Tür ist.
                    - Sobald Peggy bescheid gibt, dass Sie an der Tür angekommen ist, geht Victor zu Punkt B und sagt Peggy auf welchem Weg sie zurückkommen soll.
                    - Kommt Sie auf dem falschen Weg zurück, dann kennt sie den Code der Tür (offensichtlich) nicht. Kommt Sie auf dem richtigen Weg zurück, könnte es noch immer Zufall gewesen sein mit Wahrscheinlichkeit :math:`\frac{1}{2}`.

                .. cell:: width-40

                    .. image:: images/zkp-geheimgang.svg
                        :align: center

                    .. container:: incremental

                        Wird das Spiel jedoch :math-i:`n` mal gespielt und Peggy kommt immer auf dem richtigen Weg zurück, dann ist die Wahrscheinlichkeit, dass Peggy immer zufällig den richtigen Weg genommen hat :math:`\frac{1}{2^n}`.

    .. card::

        Viele Zero-Knowledge Protokolle basieren darauf, dass man im Prinzip ein Spiel spielt, das man auch zufällig gewinnen kann. Durch die Wiederholung des Spiels wird die Wahrscheinlichkeit jedoch für permanentes zufälliges gewinnen sehr schnell sehr klein (exponentiell). Somit kann man für praktische Zwecke hinreichend sicher sein, dass der Beweisführende (im Beispiel Peggy) über das Wissen verfügt, dass er vorgibt zu besitzen, wenn er immer gewinnt.

        Nach :math-r:`20` Runden ist die Wahreinschlichkeit nur noch :math:`1/2^{20} = 1/1 048 576`.

        Mit :math-r:`128` Runden erreicht man ein Sicherheitsniveau, dass vergleichbar ist mit anderen kryptographischen Verfahren (AES-128, SHA-256, ...).



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

        5. Peggy sende :math-i:`y` an Victor.

        6. Victor testet:  :math:`x \cdot v^b \bmod n \stackrel{?}{=} y^2 \bmod n`




.. class:: exercises

Übung - klassisches Fiat-Shamir-Protokoll
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

         Der Inverse von 4 modulo 21 ist 16, denn: :math:`4 \cdot 16 = 64 \equiv 1 \mod 21`

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

         - :math:`y^2 = 8^2 = 64 \mod 21 = 1`
         - :math:`x \cdot v^b = 16 \cdot 16 = 256 \mod 21 = 1`

         → Beide Seiten ergeben 1 modulo 21

         Die Gleichung stimmt also: :math:`y^2 \equiv x \cdot v \mod n`

         Somit ist die Verifikation erfolgreich.

         Da in diesem Beispiel nur eine Runde durchgeführt wurde, kann Victor lediglich mit Wahrscheinlichkeit :math:`\frac{1}{2}` sicher sein, dass Peggy tatsächlich die Geheimzahl :math:`s` kennt.

         Erst durch die Wiederholung über viele Runden hinweg sinkt die Wahrscheinlichkeit eines erfolgreichen Betrugs exponentiell.
