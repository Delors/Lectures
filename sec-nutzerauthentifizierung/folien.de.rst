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

            Die Verwendung einer (kryptographischen) Hashfunktion alleine ist nicht ausreichend zur sicheren Benutzerauthentifizierung über eine nicht-sichere Verbindung. Eine einfache Replay-Attacke ist möglich.

    .. card::

        Ein einfaches Challenge-Response Protokoll (:ger:`Herausforderung- und Antwortprotokoll`) basierende auf einer Hashfunktion :math-i:`f` wäre:

        .. csv-table::
            :header: "Alice", "unsicherer Kanal", "Bob"
            :class: borderless incremental-table-rows highlight-cell-on-hover
            :widths: 20,15,20
            :width: 100%

            Gibt Benutzerkennung ein: :math-i:`ID`, → sendet :math-i:`ID`, sucht zu :math-i:`ID` Schlüssel :math-i:`K` in der Datenbank
            , , ↓
            "Gibt Passwort :math-i:`K'` ein", sendet :math-i:`r` ←, wählt zufällige Zahl :math-i:`r`
            , , ↓
            "berechnet: :math:`Res'=f(K',r)`", → sendet :math-i:`Res'`, ":math:`f(K,r) \stackrel{?}{=} Res'`"


        .. question::
            :class: incremental

            Wie bewerten Sie die Sicherheit (dieses Protokolls)?

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
