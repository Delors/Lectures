.. meta:: 
    :author: Michael Eichberg
    :keywords: "IT Sicherheit", Passwortwiederherstellung
    :description lang=de: Fortgeschrittene Angewandte IT Sicherheit
    :id: 2023_11-w3wi_se403_passwort_wiederherstellung-regexp
    :first-slide: last-viewed

.. |html-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html
.. |pdf-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html.pdf
.. |at| unicode:: 0x40

.. role:: incremental   
.. role:: eng
.. role:: ger
.. role:: red
.. role:: green
.. role:: the-blue
.. role:: minor
.. role:: ger-quote
.. role:: obsolete
.. role:: line-above


.. role:: raw-html(raw)
   :format: html


Reguläre Ausdrücke 
=====================================================

.. container:: line-above padding-bottom-1em

  :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
  :Kontakt: michael.eichberg@dhbw-mannheim.de
  :Version: 1.0

.. supplemental::

  :Folien: 
      :HTML: |html-source|

      :PDF: |pdf-source|
      
  :Fehler auf Folien melden:
      https://github.com/Delors/delors.github.io/issues



Reguläre Ausdrücke (Wiederholung)
----------------------------------

Reguläre Ausdrücke (:eng:`regular expressions`) sind ein Standardwerkzeug. Wir verwenden diese insbesondere um Wörterbücher, Leaks und weitere Kontextinformationen zu verarbeiten.

Reguläre Ausdrücke können insbesondere zum Patternmatching auf Textdaten genutzt werden.

Reguläre Ausdrück beschreiben reguläre Sprachen und können durch einen endlichen Automaten erkannt werden.

Reguläre Ausdrücke - in der Standardeinstellung - nehmen immer einen maximalen Musterabgleich vor (:eng:`greedy matching / eager matching`).


Reguläre Ausdrücke (Grundlagen)
----------------------------------

1. Zeichenklassen:

   Buchstaben und Zahlen können direkt in einem regulären Ausdruck verwenden, um entsprechenden Text zu finden. Zum Beispiel steht ``"a"`` für das Zeichen ``a``.

   .. code:: bash

      echo -n "abc" | grep -E "a"
    
   findet: ``a``
    
   Der Punkt repräsentiert ein beliebiges Zeichen - außer den Zeilenumbruch.

   .. code:: bash

      echo -n "abc" | grep -E "a."

   findet: ``ab``


Reguläre Ausdrücke (Grundlagen)
----------------------------------

1. Zeichenklassen (fortgesetzt):
   
   Eine ganze Klasse von Zeichen kann in eckigen Klammern angegeben werden "[]". 

   .. code:: bash

      echo -n "abcdefg" | grep -E "[acg]"

   findet: ``a``, ``c``, ``g``

   Klassen können auch durch Bereiche beschrieben werden (``a-z``, ``A-Z``, ``0-9``):

   .. code:: bash

      echo -n "ab12xy" | grep -Eo "[a-z]"

   findet: ``a``, ``b``, ``x``, ``y``


Reguläre Ausdrücke (Grundlagen)
----------------------------------

1. Zeichenklassen (fortgesetzt):

   Welche Buchstaben genau durch eine Klasse repräsentiert werden hängt von den Spracheinstellungen ab!

   .. code:: bash

      LANG=de_DE.UTF-8; echo "aä" | grep -Eo "[a-z]"       

   findet: ``a``, ``ä``

   aber
   
   .. code:: bash
 
      LANG=C; echo "aä" | grep -Eo "[a-z]"       

   findet :ger-quote:`nur`: ``a`` 


Reguläre Ausdrücke (Grundlagen)
----------------------------------

1. Zeichenklassen (fortgesetzt):

   Die Negation einer Klasse wird durch an ein ``^`` direkt am Anfang der Klasse erzwungen.

   .. code:: bash
 
     echo "abc123" | grep -Eo "[^a-z]"    

   findet: ``1``, ``2``, ``3``

2. Escapezeichen:
   
   Der Backslash ``\`` dient als Escapezeichen für Sonderzeichen.

   .. code:: bash
 
     echo "abc-123[a-z]" | grep -Eo "\[a-z\]"

   findet: ``[a-z]`` :minor:`(aber nicht "abc")`


Reguläre Ausdrücke (Grundlagen)
----------------------------------

3. Anker

   ``^``: Steht für den Anfang einer Zeile.

   ``$``: steht für das Ende einer Zeile. 

   .. code:: bash

      $ echo "abcabcabc" | grep -Eo "abc"  
      abc
      abc
      abc
      $ echo "abcY_abcZ" | grep -Eo "^abc."
      abcY
      $ echo "XbcYbc" | grep -Eo ".bc$"
      Ybc


Reguläre Ausdrücke (Grundlagen)
----------------------------------

3. Quantifizierung

   ``*``: "null oder mehr" Vorkommen des vorherigen Zeichens oder Musters.

   ``+``: "ein oder mehr" Vorkommen des vorherigen Zeichens oder Musters.

   ``?``: "null oder ein" Vorkommen des vorherigen Zeichens oder Musters.

   .. code:: bash

      $ echo "Sa--aa--aaaE" | grep -Eo "aa*"  
      a, aa, aaa
      $ echo "Sa--aa--aaaE" | grep -Eo "aa+"  
      aa, aaa
      $ echo "Sa--aa--aaaE" | grep -Eo "a?"   
      a, a, a, a, a, a


Reguläre Ausdrücke (Grundlagen)
----------------------------------

3. Quatifizierung (fortgesetzt)

   ``{X,Y}``: zwischen X und Y Vorkommen des vorherigen Zeichens oder Musters. Die obere Grenze ist optional, um zum Beispiel X und mehr Vorkommen zu finden.

   .. code:: bash

      $ echo "Sa--aa--aaaE" | grep -Eo "a{2,2}"
      aa
      aa
      $ echo "Sa--aa--aaaE" | grep -Eo "a{2,3}"
      aa
      aaa


Reguläre Ausdrücke (Grundlagen)
----------------------------------

4. Alternativen: ``|`` trennt  verschiedene Alternativen.

   .. code:: bash

      $ echo "HundMausAffe" | grep -Eo "Hund|Affe"  
      Hund
      Affe

   .. class:: incremental

      Aufgrund des :ger-quote:`gierigem` Musterabgleichs ist bei dem Abgleich von Alternativen generell darauf zu achten, dass zuerst auf den letzten Abgleich geprüft wird.

      .. code:: bash

        $ echo "Schifffahrt" | grep -Eo "Schiff|Schifffahrt"
        Schifffahrt

        # Perl compatible
        echo "Schifffahrt" | grep -Po "Schiff|Schifffahrt"
        Schiff


Reguläre Ausdrücke (Grundlagen)
----------------------------------

5. Klammern: ``()`` dienen der Gruppierung von Teilausdrücken und der Referenzierbarkeit bzw. Rückreferenzen.

   Beispiel: der folgende Ausdruck findet Zeichenketten, die mit dem Zeichen aufhören mit dem sie begonnen haben.

   .. code:: bash

      $ echo "XaaaaX" | grep -Eo "^(.).*\1$" 
      XaaaaX

      $ echo "XaaaaY" | grep -Eo "^(.).*\1$" 


Reguläre Ausdrücke (Fortgeschrittene Grundlagen)
-------------------------------------------------

6. Lookahead

   ``(?=...)``: ist ein positiver Lookahead und stellt sicher, dass ein bestimmtes Muster im Text folgt, ohne es selbst in das Ergebnis aufzunehmen. 

   ``(?!...)``: ist ein negativer Lookahead und stellt sicher, dass ein bestimmtes Muster im Text *nicht* folgt. 

   .. code:: bash

      $ echo "HundKatzeHundMaus" | grep -Po 'Hund(?=Katze).{1,2}'
      HundKa
                                                                                                    
      $ echo "HundKatzeHundMaus" | grep -Po 'Hund(?!Katze).{1,2}'
      HundMa


.. class:: transition-scale integrated-exercise

Fingerübungen
---------------

1. Prüfen Sie ob der Begriff: schmetterling oder Schmetterling in rockyou vorkommt.

..
    grep -E "^[a-zA-Z]+$" /usr/share/wordlists/rockyou.txt | grep -ni "schmetterling"

2. Finden Sie alle Passworte in denen ein Zeichen mind. 3 oder mehrmals wiederholt wird. z. B. "x0000!" oder "aaaabbbb".  

..
    grep -E "(.)\1{3,}" /usr/share/wordlists/rockyou.txt 

3. Finden Sie alle Passworte, in denen eine Sequenz mit mindestens 3 Zeichen wiederholt wird. z. B. "TestTest" oder "1AffeIstAffe#".

..
    └─$ grep -E "(.{3,}).*\1" /usr/share/wordlists/rockyou.txt 
