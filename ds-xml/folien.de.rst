.. meta::
    :version: genesis
    :author: Michael Eichberg
    :keywords: "Web Programmierung", "XML", "XPath"
    :description lang=de: XML, XPath
    :id: lecture-distributed-systems-xml-xpath
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!
    
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
.. role:: obsolete
.. role:: line-above
.. role:: smaller
.. role:: far-smaller
.. role:: monospaced

.. role:: raw-html(raw)
   :format: html


.. class:: animated-symbol
    
XML (eXtensible Markup Language) und XPath
================================================

.. container:: line-above tiny

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
    :Kontakt: michael.eichberg@dhbw.de, Raum 149B
    :Version: 1.0.1

.. supplemental::

    :Folien: 

        |html-source|

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues

    


.. class:: new-section transition-fade

XML (eXtensible Markup Language)
------------------------------------------------


*Markup Languages*
---------------------   

- Sprachen, die verwendete werden, um Texte zu strukturieren und zu formatieren

.. class:: incremental list-with-explanations

  - maschinenlesbar
  - Beispiele:

    - HTML
    - XML
    - LaTeX
    - Markdown
    - reStructuredText
    - ...

.. supplemental::
   
   Auch wenn Markup-Sprachen für Menschen lesbar sind, sind sie in erster Linie für Maschinen gedacht. Darüber hinaus sollte im Allgemeinen vermieden werden, dass der Markup dem Formatieren dient/zum formatieren verwendet wird. 

   YAML hat keinen Dokumentenfokus und ist nicht (mehr) als Markup-Sprache klassifiziert.


XML - Hintergrund
-----------------

.. class:: incremental

- Aufbauend auf **Standard Generalized Markup Language (SGML)**

  .. class:: list-with-explanations

  - SGML ist Standardisiert als ISO 8879:1986
  - In SGML ist die Basis für jedes Dokument eine Formatbeschreibung mit Hilfe einer *Document type definition* (DTD)
  
    Beschreibt welche Elemente es gibt und wie diese ineinander geschachtelt werden können
   
    .. code:: DTD
        :class: far-smaller
   
        <!ELEMENT note (head,body)>
        <!ELEMENT head (#PCDATA)>
        <!ELEMENT body (#PCDATA)>
- XML ist eine vereinfachte Version von SGML und wurde 1998 standardisiert.
- XML dient der Kodierung und Strukturierung einzelner Instanzen von Dokumenten.


XML\ [#]_
-----------------

.. class:: incremental list-with-explanations

- Ein XML Dokument kann man sich als einen Baum von Elementen vorstellen, die Informationen enthalten.
- Dokumentenstruktur kann durch DTDs oder XML-Schemas beschrieben werden.
- Eine explizite Beschreibung der Dokumentenstruktur ist nicht zwingend erforderlich (aber häufig sinnvoll).
- XML Dokumente müssen stringente Anforderungen an die Syntax erfüllen (:eng:`Well-formed XML Dokumente`).
- XML bildet die Basis für viele weitere Sprachen wie MathML, GraphML, SVG, …
- Abfragen auf XML basierenden Dokumenten können mittels XPath oder XQuery durchgeführt werden.
- Auf XML basierende Dokumenten können durch XSLT transformiert werden.

.. [#] XML 1.0: eXtensible Markup Language, https://www.w3.org/TR/xml/ (Aktuell)

       XML 1.1: https://www.w3.org/TR/2006/REC-xml11-20060816/ (nur für Spezialfälle)


.. supplemental::

    In Hinblick auf XML betrachten wir Dokumente als Instanzen von Informationen, die eine Struktur haben. Unter dieser Perspektive ist vieles ein Dokument:

    - Artikel, Bücher, Notizen, Gedichte, Romane
    - Technische Handbücher, Beiblätter, Produktverpackungen
    - Mails, Nachrichten
    - Rechnungen, Bestellungen, Lieferscheine
    - Log Dateien, Protokolle, Konfigurationsdateien
  
    Wesentliche Anforderungen bzgl. der Syntax eines XML Dokuments (*Well-formed* XML Dokumente):

    - es gibt nur ein Wurzelelement
    - Element überlappen sich nicht; d. h. für alle Elemente (außer dem Wurzelelement) gilt: Befindet sich das Start-Tag im Inhalt eines anderen Elements, so befindet sich das End-Tag im Inhalt desselben Elements. Es ergibt sich somit ein Baum.


Was bietet XML?
-------------------

- Internationalisierung durch die Verwendung von Unicode.
- Validierung von Instanzen (d. h. von Dokumenten).
- Lokalisierung von Namen über Namensräume (z. B. *Mein* Haus ist nicht dein *Haus*).
- Ein *menschenlesbares* Format.
- Hierarchische Struktur.
- Erweiterbarkeit.

.. supplemental::

    Wie auch in HTML (HyperText Markup Language) kann auch in XML jedes Zeichen als Referenz auf ein Unicode-Zeichen kodiert werden. 

    Beispiel:
    
    .. code:: xml

        &#x2200;&#x03b1;&#x2208;&#x0393;

    entspricht:

        .. raw:: html

           &#x2200;&#x03b1;&#x2208;&#x0393;
        


XML Dokument - Beispiel
------------------------

.. code:: xml
    :class: far-smaller

    <?xml version="1.0" encoding="UTF-8" standalone="yes" ?>
    <lehrveranstaltungen status="akkreditiert">
        <!-- Modul muss überarbeitet werden... -->
        <modul>
            <vorlesung>Web Entwicklung</vorlesung>
            <vorlesung>Verteilte Systeme</vorlesung>
        </modul>
    </lehrveranstaltungen>

.. container:: incremental

    :XML-Deklaration: ``<?xml version="1.0" encoding="UTF-8" standalone="yes"?>``
    :Start-Tags: ``<lehrveranstaltungen>``, ``<modul>``, ``<vorlesung>``
    :End-Tags: ``</lehrveranstaltungen>``, ``</modul>``, ``</vorlesung>``
    :Attribute: ``status``
    :#Text Nodes: ``Web Entwicklung``, ``Verteilte Systeme``


.. supplemental::

    Die Spezifikationen bzgl. ``encoding`` (Kodierung des Dokuments) und ``standalone`` (Ist das Dokument von anderen Dokumenten abhängig) sind *nur* Pseudoattribute, da sie zum Prolog des Dokuments gehören.



XML Dokument - allgemeine Struktur
-----------------------------------

.. stack::

    .. layer:: no-box-shadow

        .. image:: drawings/xml/struktur-prolog.svg
            :height: 700px
            :align: center

    .. layer:: incremental overlay no-box-shadow

        .. image:: drawings/xml/struktur-element.svg
            :height: 700px
            :align: center

    .. layer:: incremental overlay no-box-shadow

        .. image:: drawings/xml/struktur-epilog.svg
            :height: 700px
            :align: center


Formale Beschreibung der XML Syntax
---------------------------------------

- die Syntax von XML Dokumenten wird durch eine *formale Grammatik* (hier: EBNF) beschrieben.

  Beispiel - Beschreibung des Prologs von XML Dokumenten in EBNF:
  
  .. code:: ebnf
    :class: far-smaller

    prolog      ::= XMLDecl? Misc* (doctypedecl Misc*)?
    XMLDecl     ::= "<?xml" VersionInfo EncodingDecl? SDDecl? S? "?>""
    VersionInfo ::= S "version" Eq ("'" VersionNum "'" | '"' VersionNum '"')
    Eq          ::= S? "=" S?
    VersionNum  ::= "1." [0-9]+
    Misc        ::= Comment | PI | S

  .. container:: incremental minor rounded-corners dhbw-light-gray-background padding-1em far-far-smaller margin-top-2em
  
    Wir werden uns auf eine informelle Beschreibung der Syntax der wichtigsten Konstrukte beschränken.

.. supplemental::

    EBNF (*Extended Backus-Naur Form*) 101:

    - '+' bedeutet 'eins oder mehr', 
    - '?' bedeutet 'optional'
    - '*' bedeutet 'null oder mehr'.
    - Klammerkonstrukte werden gruppiert.
    - '|' (Pipe-Zeichen) bedeutet 'oder'.
    - 'S' steht für Leerzeichen (hier).
    - 'string' bedeutet das Vorkommen der wörtlichen Zeichenkette.
    - [c-c] ist eine Zeichenklasse und steht für ein einzelnes Zeichen im angegebenen Bereich.
  
    EBNFs sind eng mit regulären Ausdrücke verwandt. EBNFs können jedoch auch rekursive Strukturen beschreiben und werden häufig für die Beschreibung von Programmiersprachen verwendet.
 


Elemente
-------------------

- Im Allgemeinen bestehen Elemente aus einem Start-Tag (z. B. ``<start>``), seinem Inhalt und einem End-Tag (z. B. ``</start>``).
- Der Inhalt eines Elements ist geordnet.
- Start-Tags können Attribute haben - Name/Wert-Paare (z. B. ``<start kind="slow"/>``).
- Die Elemente müssen wohlgeformt sein: balanciert, konforme Syntax, gültige Attribute, keine Duplikate, usw.
- Elemente können leer sein (z. B. ``<empty/>``); d. h. sie haben keinen Inhalt, können aber Attribute haben.


Attribute
-------------------

- Attribute sind *ungeordnete* Name/Wert-Paare, die in einem Start-Tag eines Elements enthalten sind.
- Jedes Attribut darf nur einmal in einem Element vorkommen.
- Ausgewählte Zeichen müssen maskiert werden, wenn sie im Wert vorkommen sollen.
- Die Werte von Attributen werden normalisiert (z. B. werden Zeilenumbrüche entfernt).
   

Vordefinierte *Entity References*
----------------------------------

.. csv-table::
    :header: *Entity Reference*, "Zeichen"
    :align: center

    ``&lt;``, ``<``
    ``&gt;``, ``>``
    ``&amp;``, ``&``
    ``&quot;``, ``"``
    ``&apos;``, ``'``



*Whitespace* in XML
--------------------    

- Oft wird Leerraum (Leerzeichen, Zeilenumbrüche, Tabulatoren usw.) hinzugefügt, um das XML "lesbarer" zu machen.
- Leerzeichen können als nicht signifikant gekennzeichnet werden; dies erfordert jedoch einen validierenden XML Prozessor.



XML für Anwendungen - *Infosets*
-----------------------------------------------------------

:eng:`Infosets (Information Sets)`

- Ein *Infoset* ist eine (abstrakte) Darstellung eines XML Dokuments; losgelöst von der konkreten Syntax (z. B. ob der Wert eines Attributs in ``""`` oder ``''`` gefasst wurde; oder ob *Entity References* verwendet wurden, etc.).

.. class:: incremental

- Ein *Infoset* enthält alle Informationen, die in einem XML Dokument enthalten sind.

  .. image:: drawings/xml/infoset.svg
      :width: 100%
      :align: center

  Ein Infoset ist eine Hierarchie (oder ein Baum) von Elementen mit benannten Eigenschaften.



Ausgewählte *Info Items*
-------------------------

Die verschiedenen *Info Items* eines *Infosets* stellen z. B. die folgenden Informationen bereit:

:*Document Info Item*:  Kinder, Wurzelelement, Basis-URI.

.. class:: incremental

:*Element Info Item*: lokaler Name, Kinder, Attribute, Vorgänger 


.. class:: incremental

:*Attribute Info Item*: lokaler Name, normalisierter Wert, deklarierendes Element


.. container:: incremental

    Es gibt weitere *Info Items* für Kommentare, Verarbeitungsanweisungen, Text, etc.



.. class:: new-section transition-fade

XML Namensräume 
------------------------------------------------

.. container:: block-footer margin-bottom-1em
    
    :eng:`XML Namespaces`



Namensräume in XML - Motivation
--------------------------------

.. stack:: 

    .. layer:: 

        Wenn wir nur einen Namen(sraum) haben sollten...

        .. class:: incremental

        - Was würde passieren, wenn wir Markup von zwei verschiedenen Autoritäten nutzen wollten?
        - Wie assoziiere ich Semantik mit gemischtem Markup?
        - Wie verbinde ich ein Schema (oder Regeln) mit dem gemischten Markup?

    .. layer:: incremental

        *Variante 1*:

        .. code:: xml
            :class: far-smaller

            <date>1/27</date>

        *Variante 2*:
        
        .. code:: xml
            :class: far-smaller

            <date><year>2004</year><day>1</day><month>27</month></date>

        .. incremental:: margin-top-1em

            Wie kann ich beide unterscheiden?



XML - Namen und Namensräume
--------------------------------

Namen werden in zwei Teile unterteilt:

:``Präfix``: Ein Bezeichner für einen Namensraum.

:``lokaler Name``: Ein Bezeichner für einen Namen in diesem Namensraum

.. incremental:: margin-top-1em

  Diese Teile werden durch einen Doppelpunkt getrennt und **QNames** (:eng:`Qualified Names`) genannt.

.. incremental:: margin-top-1em

    Beispiel:

    .. code:: xml
        :class: far-smaller

        <c:pseudocode>
          <c:comment xlink:href="http://somewhere..."/>
        </c:pseudocode>

    Dies gilt nur für Element- und Attributnamen.


.. supplemental:: 

    Jedes Präfix, das "xml" enthält, ist für das W3C reserviert.



XML Präfixe und Namensräume
--------------------------------

- Präfixe müssen durch assoziierte Präfixe mit Namensräumen deklariert werden, *bevor* sie verwendet werden.
- Diese Assoziation kann nur für Elemente deklariert werden.

.. class:: incremental

- Die Syntax lautet: ``xmlns:prefix="some:uri"``.
 
  Beispiel:

  .. code:: xml
      :class: far-smaller

      <c:pseudocode xmlns:c="urn:publicid:IDN+mathdoc.org">
        <c:comment xlink:href="http://somewhere..." 
                   xmlns:xlink="http://www.w3.org/..."/>
      </c:pseudocode>

- *Bevor* bedeutet, dass der Präfix auf dem Element, in dem das Präfix vorkommt - oder auf einem Vorgängerelement - deklariert werden muss.


.. supplemental::

  Das Präfix ``xml`` ist vordefiniert und die URI ist: ``http://www.w3.org/XML/1998/namespace``.

  Mit Hilfe einer URI (Uniform Resource Identifier) wird ein Namensraum identifiziert. Die URI muss nicht aufgelöst werden können.

  URI-Werte können Webadressen sein (z. B. ``http://youdomain.com``), aber auch andere Werte wie URNs (Namen): ``urn:...`` oder andere Schemata: ``scheme:scheme-specific-part``.



*Default Namespace*
-------------------

- Der Standardnamensraum kann vorgegeben werden.

.. class:: incremental

- Dies gilt nur für Elementnamen ohne Präfixe.
- Die Syntax lautet: ``xmlns="some:uri"``.

  Beispiel:

  .. code:: xml
    :class: far-smaller

    <c:pseudocode xmlns:c="urn:publicid:IDN+mathdoc.org">
       <c:comment xmlns="http://www.w3.org/1999/xhtml">
          <p>Dieser Code macht folgendes:</p>
          ...
       </c:comment>
    </c:pseudocode>


.. supplemental::

    Mit ``xmlns=""`` kann der gesetzte Standardnamensraum aufgehoben werden.

    
    .. admonition:: Hinweis
        :class: warning

        Attribute ohne Präfix befinden sich immer im leeren Namensraum, d. h. sie haben keinen Namensraum



Geltungsbereich von Namensräumen\ [#]_
---------------------------------------

- Der Geltungsbereich einer Deklaration eines Namensraums ist das Element, in dem sie vorkommt.

.. class:: incremental

- Es gibt keinen Unterschied zwischen Deklarationen auf dem Wurzelelement und anderswo.
- Das Element, seine Attribute und seine Kinder können dieses Präfix in ihren Namen verwenden.
- Namespaces können redefiniert werden.

.. [#] :eng: *Namespace Scoping*



Der Name des Namensraums  
--------------------------------

- Das Präfix ist nur eine Abkürzung des eigentlichen Namens des Namensraumes (d. h. des Wertes der Deklaration).

- Ein Name besteht nun aus zwei Teilen:

  1. der Name des Namensraum, der mit dem Präfix verbunden ist.
  2. der lokale Name; d. h. der Teil des Namens nach dem Doppelpunkt.



Namensräume und das XML Information Set (Infoset)
--------------------------------------------------

.. stack::

    .. layer:: 

        .. rubric:: Elemente

        :Name des Namensraums: der Name des Namensraums oder ``no value``, wenn es keinen gibt.

        :Lokaler Name: der lokale Teil des Namens (d. h. nach dem Doppelpunkt).

        :Präfix: der für das Element verwendete Namensraumpräfix oder ``no value``, wenn es keinen gibt.

        :Im Geltungsbereich definierte Namensräume: Eine ungeordnete Liste von *Namespace Info Items*.

        :Deklarationen von Namensräumen: Eine ungeordnete Liste aller Attribute des Elements, die Namensräume deklarieren.

    .. layer:: incremental

        .. rubric:: Attribute

        :Name des Namensraums: der Name des Namensraums oder ``no value``, wenn es keinen gibt.

        :Lokaler Name: der lokale Teil des Namens (d. h. nach dem Doppelpunkt).

        :Präfix: der für das Attribut verwendete Namensraumpräfix oder ``no value``, wenn es keinen gibt.


Namensräume 
------------------------------------------------

.. rubric:: Setzen des Standardnamensraums

.. code:: xml
    :class: far-smaller

    <pseudocode xmlns="urn:publicid:IDN+mathdoc.org">
        <comment>e = mc^2</comment>
    </pseudocode>

.. rubric:: Definition eines Präfixes (hier: „m“)

.. code:: xml
    :class: far-smaller

    <m:pseudocode xmlns:m="urn:publicid:IDN+mathdoc.org">
        <m:comment>e = mc^2</m:comment>
    </m:pseudocode>


.. rubric:: Redefinition eines Präfixes (hier: „m“)

.. code:: xml
    :class: far-smaller

    <m:pseudocode xmlns:m="urn:publicid:IDN+mathdoc.org">
        <m:comment xmlns:m="urn:comment">e = mc^2</m:comment>
    </m:pseudocode>



Beispiel: OpenOffice Dokumentenformat
---------------------------------------

.. code:: xml
    :class: far-far-smaller

    <?xml version="1.0" encoding="UTF-8"?>
    <office:document-content 
        [...]
        xmlns:style="urn:oasis:names:tc:opendocument:xmlns:style:1.0" 
        xmlns:text="urn:oasis:names:tc:opendocument:xmlns:text:1.0" 
        xmlns:office="urn:oasis:names:tc:opendocument:xmlns:office:1.0" office:version="1.3">
        <office:scripts/>
        <office:font-face-decls>[...]
        </office:font-face-decls>
        <office:automatic-styles>
            <style:style style:name="P1" [...]>[...]
            </style:style>
        </office:automatic-styles>
        <office:body>
            <office:text> [...]
                <text:p text:style-name="P1">Test</text:p>
            </office:text>
        </office:body>
    </office:document-content>


.. class:: integrated-exercise transition-scale

Übung: XML Dokument mit Namensräumen
------------------------------------------------

.. exercise::

    Erstellen Sie ein XML Dokument nach folgenden Vorgaben:

    - Das Wurzelelement ist ``document``.
    - Das Dokument fasst mehrere  Bestellungen (``order``-Elemente) zusammen.
    - Es gibt vier Bestellungen (d.h. vier ``order``-Elemente). 
    - Jede Bestellung enthält mehrere Produkte (d. h. ``product``-Elemente).
    - Pro Produkt soll angegeben werden um welches Produkt es sich handelt und wie viele davon bestellt wurden. Fügen Sie den Bestellungen zwischen einem und drei Produkte hinzu.
    - Die Bestellungen gehen an verschiedenen Partnersysteme und sollen deswegen durch entsprechende Namensräume voneinander getrennt sein.    

    .. solution:: 
        :pwd: !xml_and_NameSpaces

        Im folgenden ist **eine** mögliche Lösung dargestellt:

        .. code:: xml
            :class: far-smaller

            <document>
                <orders>
                    <order xmlns="http://fruits.com">
                        <product quantity="1">Bananen</product>
                        <product quantity="3">Orangen</product>
                        <product quantity="4">Zitronen</product>
                        <product quantity="2">Äpfel</product>
                    </order>
                    <order xmlns="http://electronics.com">
                        <product id="65'' TV" quantity="1" />
                        <product id="Refrigator" quantity="3"/>
                    </order>
                    <m:order xmlns:m="http://meat.com">
                        <m:product m:id="Ripeye Steak" m:quantity="1" />
                        <m:product m:id="T-bone Steak" m:quantity="3"/>
                    </m:order>
                    <m:order xmlns:m="http://meat.com">
                        <m:product m:id="Hind Leg" m:quantity="101" />
                    </m:order>
                </orders>
            </document>



.. class:: new-section transition-fade

XPath 
------------------------------------------------


XPath - Übersicht
-------------------

.. class:: incremental 

- XPath ist eine Syntax/Sprache zur Adressierung von Knoten in einem Dokument.
- XPath-Ausdrücke sind *Pfadausdrücke* (:eng:`path expressions`).
- Erlaubt es folgende Dinge auszudrücken:
  
  .. class:: incremental

  - Selektiere alle ``vorlesung``-Kinderelemente des ``lehrveranstaltungselements``-Elements.

  - Finde die Geschwisterknoten des Elements ``vorlesung``.

  - Finde das Element ``lehrveranstaltung``, bei dem das Attribut ``status`` den Wert ``aufgekündigt`` hat.

- Es handelt sich um einen eigenen Mini-Standard, der von vielen Spezifikationen verwendet wird (XSLT, XQuery, ...).
- Implementationen sind in vielen Programmiersprachen verfügbar (z. B. Java, JavaScript, Python, ...) und alle Browser unterstützen XPath-Ausdrücke für die Selektion von Elementen.


XPath - Pfadausdrücke
-----------------------

- Ein Pfadausdruck besteht aus einer Folge von Schritten, die durch Schrägstriche getrennt sind. (Ähnlich wie bei Dateipfaden.)

.. class:: incremental

- Ein einzelner Schrägstrich ("``/``") steht für das Wurzelelement.
- Nachfolgende benannte Schritte im Pfad stellen Kinder dar:

  .. code:: xslt
    :class: far-smaller

    /lehrveranstaltungen/modul
  
  Wählt das untergeordnete Element ``modul`` des Dokumentenelements ``lehrveranstaltungen`` aus.

- XPath-Ausdrücke müssen nicht bei der Wurzel starten:

  .. code:: xslt
    :class: far-smaller

    modul/vorlesung
    
  Wählt das ``vorlesung``-Kinderelement des ``modul``-Elements aus.



Resultat eines XPath-Ausdrucks
--------------------------------

- Das Ergebnis der Auswertung eines XPath-Ausdrucks ist ein *Node Set* oder ein einzelner Wert (ein String, eine Zahl oder ein Boolean).\ [#]_

- Ein ``Node`` ist nur ein anderer Begriff für *Info Item*.

.. class:: incremental

  - Beispiel

    Sei das folgende XML-Dokument gegeben:  

    .. code:: xml
        :class: far-smaller

        <modul>
            <vorlesung>Eins</vorlesung>
            <vorlesung>Zwei</vorlesung>
        </modul>

    Dann gibt der folgende Ausdruck zwei ``vorlesung``-Elemente zurück:

    .. code:: xslt
        :class: far-smaller

        /modul/vorlesung

    


.. [#] Die Reihenfolge der Ergebnisse muss nicht über alle Implementierungen (z. B. Browser) hinweg konsistent sein. (vgl. `XPathResult <https://developer.mozilla.org/en-US/docs/Web/API/XPathResult>`__)


Attribute Selektieren
-----------------------

- Attribute können über den entsprechenden Schritt: ``@Name`` ausgewählt werden.

.. class:: incremental

  - Beispiel

    Sei das folgende XML-Dokument gegeben: 

    .. code:: xml
        :class: far-smaller

        <modul>
            <vorlesung mhb="123">Eins</vorlesung>
            <vorlesung mhb="456">Zwei</vorlesung>
        </modul>

    Dann würde der Ausdruck:

    .. code:: xslt
        :class: far-smaller

        /modul/vorlesung/@mhb
    
    Die beiden ``mhb`` Attribute als Menge zurückgeben.



Namen und Namensräume
-----------------------

- Jeder Schritt eines XPath-Ausdrucks kann einen *QName* verwenden: ``<Präfix>:<Lokaler Name>``
- Das Matching basiert auf dem lokalen Namen und dem Namen des Namespaces und nicht auf dem Präfix.

.. class:: incremental

  - Beispiele für XPath-Ausdrücke mit Namensraum:

    .. code:: xslt
        :class: far-smaller

        /dhbw:modul/dhbw:vorlesung
        /dhbw:modul/dhbw:vorlesung/@mhb
        /dhbw:modul/dhbw:vorlesung/@i:mhb


.. admonition:: Hinweis
    :class: warning incremental margin-top-1em

    Die Präfixbindung wird außerhalb des Ausdrucks definiert (i. d. R. anwendungsspezifisch). 


.. supplemental::

    In dem gezeigten Beispiel müsste die Anwendung die Präfixe (``dhbw`` und ``i``) mit den entsprechenden Namensräumen verknüpfen.

    .. rubric:: kein Präfix = kein Namensraum

    Ein Namenstest innerhalb eines Pfadausdrucks, der kein Präfix spezifiziert ist nur für  Namen ohne Namensraum erfolgreich!

    Zum Beispiel:

    .. code:: xslt
        :class: far-smaller

        m:section/title

    selektiert das Element ``title`` im folgenden Beispiel, da es keinen Namensraum hat:

    .. code:: xml
        :class: far-smaller

        <m:section xmlns:m='urn:...'>
          <title>Kein Namespace</title>
        </m:section>

    in folgendem Beispiel jedoch nicht:

    .. code:: xml  
        :class: far-smaller 

        <m:section xmlns:m='urn:...' 
                   xmlns='urn:something-else...'>
          <title>Ich habe einen Namensraum...</title>
        </m:section>
    
    *Der Namensabgleich basiert auf dem lokalen Namen und dem Namen des Namensraums.*



*Wildcards* in xPath
-----------------------

- ``*`` wird als Platzhalter für Namen verwendet werden.

.. class:: incremental

- Beispiele:

  .. class:: incremental

  - Alle Elemente, die in einem ``modul``-Element enthalten sind:

    .. code:: xslt
        :class: far-smaller

        /modul/*
    
  - Alle Attribute eines ``vorlesung``-Elements:

    .. code:: xslt
        :class: far-smaller

        /modul/vorlesung/@*

  - Verwendung von Namensräumen:

    .. code:: xslt
        :class: far-smaller

        /dhbw:modul/dhbw:*
        /dhbw:modul/dhbw:vorlesung/@i:*

.. class:: footer-left

    Der Namensraum Präfix kann nicht durch ein *Wildcard* ersetzt werden.



Kontextknoten   
-----------------------

- Die Auswertung erfolgt immer in Bezug auf einen Kontextknoten.
- Der Kontextknoten wird mit ``.`` (Punkt) referenziert.

.. class:: incremental

- Beispiel - Selektion der Attribute des Kontextknotens:

  .. code:: xslt
    :class: far-smaller

    ./@*

  Der Kontextknoten ist implizit.

- Der Kontextknoten muss nicht zwingend ein Element sein.



Bedingtes Matching
-----------------------

- Prädikate erlauben die Angaben von Bedingungen und folgen der Deklaration des *Schrittes*.
- Prädikate sind in eckigen Klammern (``[`` und ``]``) eingeschlossen.
- Verschachtelte Prädikate sind möglich.

.. class:: incremental 

- Beispiel

  .. code:: xslt
        :class: far-smaller
    
        /modul/vorlesung[@mhb='123']
    
  Wählt das ``vorlesung``-Element aus, das das Attribut ``mhb`` mit dem Wert ``123`` hat.

- Die Verwendung von (komplexen) Pfadausdrücken in Bedingungen ist ebenfalls möglich.

  Beispiel

  .. code:: xslt
    :class: far-smaller

    lehrveranstaltungen/modul[vorlesung/@mhb='123']

Bedingtes Matching - Operatoren und Funktionen
----------------------------------------------------------

.. class:: incremental

- boolesche Operatoren: (``or`` und ``and``)
- boolesche Funktionen: ``not ( boolean )``, ``lang ( string )``, ``true()``, ``false()``, ...
- Mathematische Funktionen: ``sum( node-set )``, ``number( object )``, ...
- Zeichenketten: ``string( object )``, ``concat( string, string, string* )``, ``starts-with( string, string )``, ``contains( string, string )``, ``substring( string, number, number )``, ``string-length( string )``, ``normalize-space( string )``, ...
- Node-set Funktionen: ``last()``, ``position()``, ``count( node-set )``, ``id( object )``, ``local-name( node-set )``, ``namespace-uri( node-set )``, ...


.. container:: incremental

    Beispiel - alle Element, die den lokalen Namen ``modul`` haben:
    
    .. code:: xslt
        :class: far-smaller

        //*[local-name()='modul']


.. container:: footer-left

    vgl. `XPath 1.0 Funktionen <https://www.edankert.com/xpathfunctions.html>`__



Selektion von Elternknoten und Vorgängerknoten
------------------------------------------------

- Über den Kontextknoten kann  auf  übergeordnete und vorgelagerte Elemente zugegriffen werden.
- ``..`` steht für das übergeordnete Element; wie bei Verzeichnissen.

.. class:: incremental

  - Beispiel
   
    .. code:: xslt
        :class: far-smaller

        /modul/vorlesung[@mhb='123']/..
    
    Wählt das ``modul``-Element aus, das das ``vorlesung``-Element mit dem Attribut ``mhb`` und dem Wert ``123`` enthält.


Selektion von Kindknoten
-------------------------

.. class:: list-with-explanations

- mit dem ``//`` können Elemente, die keine direkten Kinder sind abgeglichen werden
  
  Es werden somit die Nachkommen des *aktuellen Kontexts* durchsucht.

.. class:: incremental

  - Beispiel
   
    .. code:: xslt
        :class: far-smaller

        lehrveranstaltungen//vorlesung[@mhb='123']/..
    
    Wählt alle ``vorlesung``-Elemente mit dem Attribut ``mhb`` und dem Wert ``123``, die Nachkommen des ``lehrveranstaltungen``-Elements sind aus.



Auswahl von Knoten, die keine Elemente oder Attribute sind
-----------------------------------------------------------

.. csv-table:: 
    :class: incremental
    :header: "Funktion", "Beschreibung"
    :align: center

    ``text()``, "Wählt den Textinhalt eines Elements aus."
    ``comment()``, "Wählt Kommentare aus."
    ``processing-instruction()``, "Wählt Verarbeitungsanweisungen aus."
    ``node()``, "Wählt alle Knoten aus."

.. container:: smaller

    .. container:: incremental

        Beispiel - alle Kommentare, die Kinder des ``document``-Elements sind:
        
        .. code:: xml
            :class: far-smaller

            /document/comment()

        




Beziehungen zwischen Knoten
----------------------------

.. container:: two-columns

    .. container:: column no-separator

        .. rubric:: Baumstruktur

        .. image:: drawings/xml/xpath-axis.svg
            :height: 900px
            :align: center

    .. container:: column incremental

        .. rubric:: Weitere Beziehungen

        :Attribute: Jedes Element kann Attribute haben :minor:`(welche keine Kinder im Baum sind)`.

        :Namensraum: Jedes Element kann Namensräume haben :minor:`(welche keine Kinder bzgl. des Baums sind)`.


Axen in XPath beschreiben die Richtungen von Beziehungen zwischen Knoten.
---------------------------------------------------------------------------

.. container:: two-columns

    .. incremental:: column

      - Baumbeziehungen:

        - ``ancestor``, ``ancestor-or-self``

        - ``parent``, ``child``, ``self``

        - ``descendant``, ``descendant-or-self``

        - ``following``, ``following-sibling``

        - ``preceding``, ``preceding-sibling``


      - Weitere Beziehungen:

        - *Attribute*
        - *Namensräume*

    .. incremental:: column

        - Beispiel:
    
            .. code:: xslt
                :class: far-smaller
    
                //modul/ancestor::lehrveranstaltungen
    
            Wählt das ``lehrveranstaltungen``-Element aus, das das ``modul``-Element enthält.
    
        - Beispiel:
    
            .. code:: xslt
                :class: far-smaller
    
                //modul/child::vorlesung
    
            Wählt das ``vorlesung``-Element aus, das ein Kind des ``modul``-Elements ist.



.. class:: no-title center-child-elements

XPath Support
-------------

.. container:: box-shadow padding-1em  dhbw-red-background white rounded-corners

    Alle gängigen Browser unterstützen XPath 1.0. 
    
    Gängige Bibliotheken (z. B. Saxon) unterstützen XPath 3.1.


.. container:: block-footer margin-bottom-1em
    
     https://www.saxonica.com/welcome/welcome.xml



.. class:: integrated-exercise transition-scale far-smaller

Übung: XPath
------------------------------------------------

Schreiben Sie XPath-Ausdrücke, um die folgenden Anfragen zu beantworten:

- Wählen Sie alle ``orders``-Elemente aus.
- Wählen Sie alle ``product``-Elemente aus, die im Namensraum ``http://fruits.com`` sind.
- Berechnen Sie die Summe der Werte der ``quantity``-Attribute, die zu Bestellungen aus dem Namensraum von ``http://fruits.com`` gehören.
- Berechnen Sie die Summe der Werte der ``quantity``-Attribute, die im Namensraum ``http://meat.com`` sind.
- Berechnen Sie die Summe der Werte *aller* ``quantity``-Attribute; unabhängig von dem konkreten Ziel der Bestellung.
- Wählen Sie alle ``order``-Elemente aus.
- Wählen Sie das erste ``product``-Element jeder Bestellung aus, die ``http://meat.com`` zugeordnet ist.
- Wählen Sie alle ``order``-Elemente aus, bei denen mehr als fünf Produkte bestellt wurden.
- Bestimmen Sie wie viele Bestellungen es gibt.
- Selektieren Sie alle Produkte der Bestellungen, die genau vier Produkte umfassen.


.. admonition:: Hinweis
    :class: warning incremental smaller

    Verwenden Sie das XML Dokument aus der Musterlösung zur letzten Aufgabe als Grundlage.



.. exercise::

    .. solution:: 
        :pwd: xpath-rauf-und-runter

        .. code:: json
            :class: smaller

            {
                "source": "orders.xml",
                "namespaces": {
                "f": "http://fruits.com",
                "e": "http://electronics.com",
                "m" : "http://meat.com"
                },
                "xpaths" : [
                {
                    "expr": "//orders"
                },
                {
                    "expr": "//f:order/f:product"
                },
                {
                    "expr": "sum(//f:order//@quantity)"
                },
                {
                    "expr": "sum(//@m:quantity)"
                },
                { 
                    "expr": "sum(//@*[local-name()='quantity'])"
                },
                {
                    "expr": "//*[local-name()='order']"
                },
                {
                    "expr" : "//m:product[1]"
                },
                {
                    "expr": "//*[local-name()='order' and sum(.//@*[local-name()='quantity']) > 5]"
                },
                {
                    "expr": "count(//orders/*)"
                },
                {
                    "expr": "//orders/*/*[last()=4]"
                }
                ]
            }


.. supplemental:: 
    
    .. rubric:: Voraussetzungen
    
    **Installation von node.js**

    Installieren Sie node.js von https://nodejs.org/en (Version 21 und 22 sind getestet). Benutzen Sie bitte *eine getestet Version*!

    **Installieren Sie die benötigten node.js Pakete**

    Am Besten einfach im "aktuellen Verzeichnis" in dem die Übungsdateien liegen ausführen:

    .. code:: shell
        :class: copy-to-clipboard
    
        npm install jsonschema@~1.4.1
        npm install xpath@~0.0.34
        npm install @xmldom/xmldom@~0.8.10 #ACHTUNG: 0.9.0 funktioniert nicht!

    **Ausführen der XPath Ausdrücke**

    
    Nutzen Sie den XPath Evaluator, um die XPath-Ausdrücke auf dem XML-Dokument auszuführen: 
    
    https://gist.github.com/Delors/189629b86265463e4a625924a9f705c8

    (Speichern Sie das Script in der Datei ``xpaths_evaluator.js`` und führen Sie es mit ``node xpaths_evaluator.js <xpath specifications>`` aus.)

    In der Datei finden Sie am Anfang eine Beschreibung wie die Dateien auszusehen haben. Alternativ können Sie auch die XML Datei `demo.xml <https://gist.github.com/Delors/89e57bd0665f66376cac0cbdf359fa83>`__ und die Datei `demo.xpaths.json <https://gist.github.com/Delors/aee6eb48450657a5ae22e530580548b2>`__ herunterladen und als Grundlage nutzen. 