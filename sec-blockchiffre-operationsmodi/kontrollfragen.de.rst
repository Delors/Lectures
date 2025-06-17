.. meta::
   :version: renaissance
   :lang: de
   :author: Michael Eichberg
   :keywords: Kontrollfragen, Operationsmodi, Blockchiffren
   :description lang=de: Kontrollfragen bzgl. Operationsmodi von Blockchiffren
   :id: vorlesung-blockchiffren-operationsmodi-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Betriebsmodi von Blockchiffren - Kontrollfragen
================================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0



.. class:: new-section

Betriebsmodi - Grundlagen
---------------------------------------



.. class:: exercises

Übung
--------

.. exercise:: Grundlagen

  .. class:: incremental-list list-with-explanations

  1. Warum sind Betriebsmodi von Nöten? Begründen Sie mit einem Beispiel.
  2. Eignet sich CBC für die Verwendung einer Blockchiffre als Stromchiffre?
  3. Nennen Sie zwei *allg.* Verfahren für das Padding von Nachrichten und erklären Sie diese kurz.
  4. Welche Kriterien können zur Bewertung eines Operationsmodis herangezogen werden?
  5. Verhalten sich alle Betriebsmodi bei Bitfehlern im Chiffretext - zum Beispiel aufgrund von Übertragungsfehlern - gleich?

     (D.h. wenn einzelne Bits des Chiffretexts gekippt sind.)

  .. solution::
    :pwd: CBSECBundSOWeiTer

    .. rubric:: Lösungen

    1. Zur Anpassung an verschiedene Anwendungsszenarien; zur Verbesserung der Wirkung.

    2. Nein. Bei CBC muss der letzte Block einer Nachricht die Blockgröße der Chiffre haben. Falls dies nicht der Fall ist, muss Padding erfolgen. D.h. CBC generiert keinen Schlüsselstrom wie zum Beispiel CTR, OFB und CFB, sondern verschlüsselt direkt.

    3. - PKCS#7 (Länge des Paddings wird am Ende eingefügt; z.B. 03 03 03 falls es drei Byte waren.)
       - ANSI (0x00 bis auf das letzte Byte, dass die Anzahl an Paddingbytes angibt).
       - ISO/IEC /816-4 (0x80 gefolgt von Nullen)

    4. - Overhead (Parallelisierbarkeit)
       - Fehlerbehebung/Fehlerfortpflanzung
       - Sicherheit

    5. Nein - (siehe Folien)




.. class:: new-section

Spezielle Betriebsmodi
---------------------------------------



.. class:: exercises

Tweakable Blockchiffre
-----------------------

.. exercise:: AES-XTS

    .. class:: incremental-list

    1. Erklären Sie den Aufbau von AES-XTS.
    2. Warum muss der Tweak nicht geheim gehalten werden?

    .. solution::
        :pwd: GeheimGeheimAlleinDaheim

        .. rubric:: Lösungen

        1. Der Aufbau besteht aus:

           •	Zwei *unabhängigen* AES-Schlüsseln K\ :sub:`1` und K\ :sub:`2`
           •	Einer Tweak-Berechnung für jeden Datenblock, meist abhängig von der Sektor- oder Blocknummer
           •	Einem Verfahren, das jede Blockposition individuell durch den Tweak varriert, ohne die Blockgröße zu verändern
           •	Verwendung des Tweak-Werts in einer XOR-Operation (XEX) zur Kombination mit Klartext und Zwischenergebnissen
        2. Der Tweak erfüllt eine ähnliche Rolle wie ein Initialisierungsvektor (IV) in anderen Modi: Er sorgt dafür, dass identische Klartexte nicht zu identischem Ciphertext führen, solange der Tweak sich unterscheidet. Dabei gilt:

           •	Der Tweak muss nicht geheim sein – seine Aufgabe ist Nichtdeterminismus, nicht Vertraulichkeit.
           •	Sicherheit entsteht dadurch, dass der geheime Schlüssel K\ :sub:`1` verwendet wird – nicht durch die Geheimhaltung des Tweaks.
