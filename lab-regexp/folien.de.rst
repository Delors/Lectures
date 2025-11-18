.. meta::
   :author: Michael Eichberg
   :keywords: "Reguläre Ausdrücke"
   :description lang=de: Eine erste Einführung in reguläre Ausdrücke
   :id: lab-regexp
   :first-slide: last-viewed
   :exercises-master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Reguläre Ausdrücke
=====================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.3

.. supplemental::

  :Folien:
      [HTML] |html-source|

      [PDF] |pdf-source|

  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues



.. class:: repetition

Reguläre Ausdrücke
----------------------------------

.. class:: incremental-list list-with-explanations

- Reguläre Ausdrücke (:eng:`regular expressions`) sind ein Standardwerkzeug zum Patternmatching auf Textdaten. 

  - Wir verwenden diese z. B. zum Suchen und Ersetzen in Textdateien,
  - im Rahmen der Entwicklung von Lexern und Parsern,
  - um Eingaben zu überprüfen (Sanitizing) oder 
  - um Wörterbücher, Leaks und weitere Kontextinformationen zu Passwortkandidaten zu verarbeiten.

- Reguläre Ausdrücke beschreiben reguläre Sprachen und können durch einen endlichen Automaten erkannt werden.

- Reguläre Ausdrücke nehmen - im Normalfall - immer einen maximalen Musterabgleich vor (:eng:`greedy matching / eager matching`).


.. class:: new-section

Schreiben von regulären Ausdrücken
-----------------------------------

.. container:: section-subtitle

   Fokus ist hier auf grundlegende reguläre Ausdrücke.

.. supplemental::

   :peripheral:`Es gibt verschiedene Dialekte von regulären Ausdrücken. Hier wird der Dialekt verwendet, der von GNU grep mit der Option -E (Extended Regular Expressions) unterstützt wird. Einige Beispiele verwenden Perl-kompatible reguläre Ausdrücke (PCRE), die mit grep -P genutzt werden können.`




Reguläre Ausdrücke - Zeichenklassen
------------------------------------

.. deck::

   .. card::

      Buchstaben und Zahlen können direkt in einem regulären Ausdruck verwenden, um entsprechenden Text zu finden. Zum Beispiel steht ``"a"`` für das Zeichen ``a``.

      .. code:: bash
         :class: copy-to-clipboard
         :number-lines:

         echo -n "abc" | grep -E "a"

      findet: ``a``

   .. card::

      Der Punkt repräsentiert ein beliebiges Zeichen - außer den Zeilenumbruch.

      .. code:: bash
         :class: copy-to-clipboard
         :number-lines:


         echo -n "abc" | grep -E "a."

      findet: ``ab``

   .. card::

      Klassen von Zeichen können in eckigen Klammern angegeben werden "[]".

      .. code:: bash
         :class: copy-to-clipboard
         :number-lines:


         echo -n "abcdefg" | grep -E "[acg]"

      findet: ``a``, ``c``, ``g``

      Klassen können auch durch Bereiche beschrieben werden (``a-z``, ``A-Z``, ``0-9``):

      .. code:: bash
         :class: copy-to-clipboard
         :number-lines:

         echo -n "ab12xy" | grep -Eo "[a-z]"

      findet: ``a``, ``b``, ``x``, ``y``

   .. card::

      Welche Buchstaben genau durch eine Klasse repräsentiert werden hängt von den Spracheinstellungen ab!

      .. code:: bash
         :class: copy-to-clipboard
         :number-lines:


         LANG=de_DE.UTF-8; echo "aä" | grep -Eo "[a-z]"

      findet: ``a``, ``ä``

      aber

      .. code:: bash
         :class: copy-to-clipboard
         :number-lines:

         LANG=C; echo "aä" | grep -Eo "[a-z]"

      findet „nur“: ``a``

   .. card::

      Die Negation einer Klasse wird durch an ein ``^`` direkt am Anfang der Klasse erzwungen.

      .. code:: bash
         :class: copy-to-clipboard
         :number-lines:

         echo "abc123" | grep -Eo "[^a-z]"

      findet: ``1``, ``2``, ``3``



Reguläre Ausdrücke - Escapezeichen
----------------------------------

Der Backslash ``\`` dient als Escapezeichen für Sonderzeichen.

.. code:: bash
   :class: copy-to-clipboard
   :number-lines:

   echo "abc-123[a-z]" | grep -Eo "\[a-z\]"

findet: ``[a-z]`` :peripheral:`(aber nicht "abc")`



Reguläre Ausdrücke - Anker
----------------------------------

``^``: Steht für den Anfang einer Zeile.

``$``: steht für das Ende einer Zeile.

.. code:: bash
   :number-lines:

   $ echo "abcabcabc" | grep -Eo "abc"
   abc
   abc
   abc
   $ echo "abcY_abcZ" | grep -Eo "^abc."
   abcY
   $ echo "XbcYbc" | grep -Eo ".bc$"
   Ybc



Reguläre Ausdrücke - Quantifizierung
-------------------------------------

.. story::

   ``*``: "null oder mehr" Vorkommen des vorherigen Zeichens oder Musters.

   ``+``: "ein oder mehr" Vorkommen des vorherigen Zeichens oder Musters.

   ``?``: "null oder ein" Vorkommen des vorherigen Zeichens oder Musters.

   .. code:: bash
      :number-lines:

      $ echo "Sa--aa--aaaE" | grep -Eo "aa*"
      a, aa, aaa
      $ echo "Sa--aa--aaaE" | grep -Eo "aa+"
      aa, aaa
      $ echo "Sa--aa--aaaE" | grep -Eo "a?"
      a, a, a, a, a, a

   .. container:: incremental line-above

      ``{X,Y}``: zwischen ``X`` und ``Y`` Vorkommen des vorherigen Zeichens oder Musters. Die obere Grenze ist optional, um zum Beispiel ``X`` und mehr Vorkommen zu finden.

      .. code:: bash
         :number-lines:

         $ echo "Sa--aa--aaaE" | grep -Eo "a{2,2}"
         aa
         aa
         $ echo "Sa--aa--aaaE" | grep -Eo "a{2,3}"
         aa
         aaa



Reguläre Ausdrücke - Alternativen
----------------------------------

``|`` trennt  verschiedene Alternativen.

.. code:: bash
   :number-lines:

   $ echo "HundMausAffe" | grep -Eo "Hund|Affe"
   Hund
   Affe

.. class:: incremental

   Aufgrund des „gierigem“ Musterabgleichs ist bei dem Abgleich von Alternativen im Allgemeinen darauf zu achten, dass zuerst auf längere bzw. spezifischere Muster geprüft wird. Die Regeln sind jedoch 

   .. code:: bash
      :number-lines:

      $ echo "Schifffahrt" | grep -Eo "Schiff|Schifffahrt"
      Schifffahrt

      # Perl compatible (requires GNU grep)
      echo "Schifffahrt" | grep -Po "Schiff|Schifffahrt"
      Schiff



Reguläre Ausdrücke - Klammern
----------------------------------

``()`` dienen der Gruppierung von Teilausdrücken und der Referenzierbarkeit bzw. Rückreferenzen mittel ``$``\ <NR> wobei NR die Nummer der Gruppe ist.

Beispiel: der folgende Ausdruck findet Zeichenketten, die mit dem Zeichen aufhören mit dem sie begonnen haben.

.. code:: bash
   :number-lines:

   $ echo "XaaaaX" | grep -Eo "^(.).*\1$"
   XaaaaX

   $ echo "XaaaaY" | grep -Eo "^(.).*\1$"



Reguläre Ausdrücke - Lookahead
-------------------------------------------------

``(?=...)``: ist ein positiver Lookahead und stellt sicher, dass ein bestimmtes Muster im Text folgt, ohne es selbst in das Ergebnis aufzunehmen.

``(?!...)``: ist ein negativer Lookahead und stellt sicher, dass ein bestimmtes Muster im Text *nicht* folgt.

.. code:: bash
   :number-lines:

   $ echo "HundKatzeHundMaus" | grep -Po 'Hund(?=Katze).{1,2}'
   HundKa

   $ echo "HundKatzeHundMaus" | grep -Po 'Hund(?!Katze).{1,2}'
   HundMa



Reguläre Ausdrücke - *Non-capturing Groups*
-------------------------------------------------

``(?:...)``: definiert eine Gruppe, die nicht für Rückreferenzen verwendet wird. (Primär für Effizienz.)

.. code:: bash
   :number-lines:

   $ echo "abcabc" | grep -Po '(?:abc){2}'
   abcabc




Reguläre Ausdrücke - *Eager vs. Lazy Matching*
-------------------------------------------------

``*?``: match 0 oder mehr Vorkommen des vorherigen Zeichens oder Musters, aber so wenige wie möglich/nötig

``+?``: match 1 oder mehr Vorkommen des vorherigen Zeichens oder Musters, aber so wenige wie möglich/nötig

``(??)``: match 0 oder 1 Vorkommen des vorherigen Zeichens oder Musters, aber so wenige wie möglich/nötig

.. code:: bash
   :number-lines:

   $ echo '<div>Hallo</div><div>Welt</div>' | grep -Eo '<.*?>'
   <div>
   </div>
   <div>
   </div>
   $ echo '<div>Hallo</div><div>Welt</div>' | grep -Eo '<.*>'
   <div>Hallo</div><div>Welt</div>



.. class:: transition-scale exercises

Fingerübungen
---------------

.. exercise:: Schmetterling in Rockyou

   Prüfen Sie ob der Begriff: schmetterling oder Schmetterling in der Datei ``rockyou.txt`` vorkommt.

   .. solution::
      :pwd: mal_sehen

      .. code:: bash

         $ grep -E "^[a-zA-Z]+$" /usr/share/wordlists/rockyou.txt |\
           grep -ni "schmetterling"

.. exercise:: Wiederholungen von Zeichen in Passwörtern

   Finden Sie alle Passworte in denen ein Zeichen mind. 3 oder mehrmals wiederholt wird. z. B. "x0000!" oder "aaaabbbb".

   .. solution::
      :pwd: ganz_schoen_viele

      .. code:: bash

         $ grep -E "(.)\1{3,}" /usr/share/wordlists/rockyou.txt

.. exercise:: Wiederholungen von Sequenzen in Passwörtern

   Finden Sie alle Passworte, in denen eine Sequenz mit mindestens 3 Zeichen wiederholt wird, z. B. „TestTest“ oder „1AffeIstAffe#“.

   .. solution::
      :pwd: Wiederholungen

      .. code:: bash

         $ grep -E "(.{3,}).*\1" /usr/share/wordlists/rockyou.txt

.. exercise:: Kein Sonderzeichen danach

   Finden Sie alle Buchstabensequenzen mit ein bis drei Zeichen, bei denen das letzte Zeichen nicht gefolgt wird von einem Sonderzeichen (z. B. ``ab`` und ``de`` in „``abc_def_``“).

   .. solution::
      :pwd: kein_sonderzeichen_danach

      .. code:: bash

         $ echo "abc_def_ ghi!" | grep -Po '[a-z]{1,3}(?![^a-zA-Z0-9])'
         ab
         de
         gh