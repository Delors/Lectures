.. meta::
   :lang: de
   :author: Michael Eichberg
   :keywords: Klassische Verschlüsselungsverfahren, Kontrollfragen
   :description lang=de: Kontrollfragen bzgl. Einführung in klassische Verschlüsselungsverfahren
   :id: vorlesung-it-sicherheit-klassische-verschluesselungsverfahren-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Kontrollfragen: Klassische Verschlüsselungsverfahren
================================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1.0



.. class:: new-section

Allgemeine Fragen
---------------------------



.. class:: exercises

Kontrollfragen
----------------

.. story::

  .. exercise:: Was ist der Unterschied zwischen Kryptoanalyse und Kryptologie?
    :class: incremental

    .. solution::
        :pwd: KryptoLogik

        Kryptologie ist der Oberbegriff und umfasst zwei Teilgebiete: 
        
        1. die Kryptographie: Entwicklung von Verschlüsselungsverfahren und 
        2. die Kryptoanalyse: Methoden zum Brechen bzw. Analysieren von Verschlüsselungsverfahren



  .. exercise:: Welche Aussage über den Brute-Force-Angriff ist korrekt?
    :class: incremental

    - Ein Brute-Force-Angriff erfordert keinerlei Wissen über den Klartext.
    - Im Durchschnitt muss beim Brute-Force-Angriff die Hälfte aller möglichen Schlüssel ausprobiert werden.
  
    .. solution::
        :pwd: HalfTheWork

        .. class:: positive-list

        - die zweite Aussage ist korrekt
        
        .. class:: negative-list

        - die erste Aussage ist falsch: Man benötigt ein Mechanismus um zu erkennen, ob das Resultat der Dechiffrierung gültiger/sinnvolle Klartext ist (z. B. durch die Berechnung der Entropie, Suche nach bekannten Klartextfragmenten, oder durch die Spracheigenschaften des Klartextes).

  .. exercise:: Was unterscheidet einen „Chosen Plaintext"-Angriff von einem „Known Plaintext"-Angriff?
    :class: incremental

    .. solution::
        :pwd: ChoiceMatters

        - Beim **Known-Plaintext-Angriff** kennt der Angreifer zufällig vorliegende Klartext-Geheimtext-Paare, die mit dem geheimen Schlüssel erzeugt wurden 
        - Beim **Chosen-Plaintext-Angriff** hingegen kann der Angreifer selbst beliebige Klartexte zur Verschlüsselung einreichen und erhält die zugehörigen Geheimtexte.

  .. exercise:: Warum ist ein großer Schlüsselraum allein keine hinreichende Bedingung für ein sicheres Verschlüsselungsverfahren?
    :class: incremental

    .. solution::
        :pwd: GroßAberNichtSicher

        .. class:: list-with-explanations

        - Ein großer Schlüsselraum schützt „nur“ gegen Brute-Force-Angriffe. 
        - Wenn das Verfahren selbst strukturelle Schwächen aufweist, können analytische Angriffe den Schlüssel oder den Klartext ermitteln, ohne den gesamten Schlüsselraum zu durchsuchen. 
  
          Ein Beispiel ist die monoalphabetische Substitutions-Chiffre: Obwohl es 26! ≈ 4·10²⁶ mögliche Schlüssel gibt (mehr als bei DES), kann sie durch Häufigkeitsanalyse leicht gebrochen werden.

  .. exercise:: Was ist der wesentliche Unterschied zwischen einer Substitutions- und einer Transpositions-Chiffre?
    :class: incremental

    .. solution::
        :pwd: TauschenVsVerschieben

        - Bei einer Substitutions-Chiffre werden die Buchstaben des Klartextes durch andere Zeichen ersetzt.
        - Bei einer Transpositions-Chiffre dagegen bleiben die Buchstaben unverändert, aber ihre Positionen im Text werden permutiert.
  
        .. remark:: 
          
          Beide Prinzipien können bzw. müssen kombiniert werden, um stärkere Chiffren zu erzeugen.

  .. exercise:: Welchen grundlegenden Nachteil haben alle klassischen Transpositions-Chiffren gemeinsam?
    :class: incremental

    .. solution::
        :pwd: StatistikVerrät

        Da die Buchstaben selbst unverändert bleiben gilt:

        Die Buchstabenhäufigkeitsverteilung des Klartextes bleibt im Geheimtext vollständig erhalten. Deswegen kann allein durch Zählen der Buchstabenhäufigkeiten im Geheimtext festgestellt werden, dass es sich um eine Transpositions-Chiffre handelt. Dies schränkt die Menge der möglichen Verfahren ein.

  .. exercise:: Worin besteht der Vorteil der Steganografie gegenüber der Verschlüsselung – und wo liegt ihre größte Schwäche?
    :class: incremental

    .. solution::
        :pwd: VerstecktAberZerbrechlich

        .. class:: positive-list

        - die (geheime) Kommunikation selbst bleibt verborgen. 
        
        .. class:: negative-list 

        - sobald das steganografische Verfahren entdeckt wird, ist es sofort wertlos. 
        - Außerdem ist der Overhead hoch – man benötigt viel Trägerinhalt, um wenige Bits zu verbergen.


  .. exercise:: Was sind die zwei grundlegenden Voraussetzungen für den sicheren Einsatz eines symmetrischen Verschlüsselungsverfahrens?
    :class: incremental

    .. solution::
        :pwd: AlgorithmusUndSchlüssel

        #. der Verschlüsselungsalgorithmus muss stark sein
        #. das Schlüsselmanagement muss sicher sein: Sender und Empfänger müssen den geheimen Schlüssel auf sicherem Wege ausgetauscht haben und ihn sicher aufbewahren. 



.. class:: new-section

Chiffren
---------------------------



.. class:: exercises

Kontrollfragen
----------------

.. story::

  .. exercise:: Ist die Cäsar-Chiffre bedingungslos sicher oder „nur“ rechnerisch sicher?
    :class: incremental

    .. solution::
        :pwd: WederNoch

        Weder noch! Es gibt nur 25 mögliche Schlüssel (Verschiebungen 1–25), sodass ein Brute-Force-Angriff von Hand  möglich ist. 
        
        Zur Erinnerung: „Rechnerisch sicher" würde bedeuten, dass der Aufwand zum Brechen den Wert der Information oder deren Lebensdauer übersteigt – das trifft hier nicht zu.

  .. exercise:: Was ist das Grundprinzip der Playfair-Chiffre, und warum ist sie schwerer zu brechen als eine einfache monoalphabetische Substitution?
    :class: incremental

    .. solution::
        :pwd: DigrammIstTrumpf

        - Die Playfair-Chiffre verschlüsselt nicht einzelne Buchstaben, sondern immer Buchstabenpaare (Digramme). Das macht eine einfache Häufigkeitsanalyse einzelner Buchstaben wirkungslos, da 26² = 676 mögliche Digramme existieren und deren Häufigkeitsverteilung viel weniger ausgeprägt ist. 
        - Dennoch bleibt sie angreifbar, da auch Digramme charakteristische Häufigkeiten aufweisen (z. B. „th" im Englischen).

  .. exercise:: Welche der folgenden Eigenschaften treffen auf das One-Time-Pad zu?
    :class: incremental

    - Es bietet nachweislich perfekte Geheimhaltung.
    - Der Schlüssel darf kürzer als die Nachricht sein, wenn er wiederholt wird.
    - Es ist praktisch für den Einsatz in stark genutzten Kommunikationssystemen geeignet.
    - Der Schlüssel muss wirklich zufällig sein.

    .. solution::
        :pwd: 1und4

        - Die erste und die vierte Aussage sind korrekt. 
        - Die zweite Aussage ist falsch – Schlüsselwiederholung reduziert die Sicherheit erheblich!
        - Die dritte Aussage ist falsch – der enorme Aufwand der Schlüsselverteilung macht es für stark genutzte Systeme unpraktisch.

  .. exercise:: Warum ist die Vigenère-Chiffre stärker als eine monoalphabetische Substitutions-Chiffre?
    :class: incremental

    .. solution::
        :pwd: VielAlphabete

        - Die Vigenère-Chiffre verwendet mehrere verschiedene Cäsar-Alphabete nacheinander – gesteuert durch ein Schlüsselwort. 
        - Derselbe Klartextbuchstabe wird je nach seiner Position im Text durch unterschiedliche Geheimtextbuchstaben repräsentiert. Dadurch wird die charakteristische Häufigkeitsverteilung der Klartextbuchstaben verschleiert, was eine einfache Häufigkeitsanalyse erschwert. 
        - Die Stärke hängt dabei von der Länge des Schlüsselworts ab: Je länger, desto schwieriger die Kryptoanalyse.

  .. exercise:: Welchen Vorteil bietet das Vigenère-Autokey-System gegenüber der klassischen Vigenère-Chiffre – und welche Schwäche bleibt?
    :class: incremental

    .. solution::
        :pwd: AutoKeyProblem

        Der Vorteil: Das Schlüsselwort wird nicht wiederholt, sondern mit dem Klartext selbst verkettet. Damit entfällt die periodische Struktur des Schlüssels, die beim klassischen Vigenère ausgenutzt werden kann. Die verbleibende Schwäche: Schlüssel (welcher größtenteils auf dem Klartext basiert) und Klartext stammen aus derselben Sprache und haben daher dieselbe Buchstabenhäufigkeitsverteilung. Das ermöglicht weiterhin statistische Angriffe.

  .. exercise:: Warum ist die Rail-Fence-Chiffre keine Substitutions-Chiffre?
    :class: incremental

    .. solution::
        :pwd: NurUmgestellt

        - Bei der Rail-Fence-Chiffre werden die Positionen der Buchstaben des Klartextes verändert! D. h. der Inhalt bleibt unverändert, nur die Reihenfolge der Zeichen wird permutiert.
        - Der Klartext wird diagonal auf mehrere „Schienen" verteilt und dann zeilenweise abgelesen. Es handelt sich also um eine Transpositions-Chiffre.

  .. exercise:: Ein Angreifer kennt nur den Geheimtext einer mit der Cäsar-Chiffre verschlüsselten Nachricht. Um welche Art von Angriff handelt es sich, und wie viele Versuche sind im schlechtesten Fall nötig?
    :class: incremental

    .. solution::
        :pwd: 25imSchlimmsten

        - Es handelt sich um einen Ciphertext-Only-Angriff kombiniert mit Brute Force. 
        - Da die Cäsar-Chiffre nur 25 sinnvolle Schlüssel hat (Verschiebungen 1 bis 25; Verschiebung 0 ist trivial), sind im schlechtesten Fall 25 Versuche nötig. 
        

