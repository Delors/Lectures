.. meta::
    :version: genesis
    :author: Michael Eichberg
    :keywords: "Web Programmierung", "HTML"
    :description lang=de: HTML
    :id: lecture-web-programming-html
    :first-slide: last-viewed
    :exercises-master-password: WirklichSchwierig!
    
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

HTML(5) - HyperText Markup Language
================================================

*Eine Einführung in die grundlegenden Konzepte von HTML*

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0.2

.. supplemental::

    :Folien: 
        
        |html-source|

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues


.. class:: new-section transition-fade

Einführung
------------------------------------------------


HyperText Markup Language (HTML)
------------------------------------------------

.. stack::

    .. layer::

        - Sprache zur Beschreibung der Darstellung von Inhalten (Markup Language), zwischen denen „navigiert“ werden kann (Hypertext).
        - Auszeichnungssprache abgeleitet aus SGML (Standard Generalized Markup Language).
    
    .. layer:: incremental
    
        Verwendungszweck:
  
        - Webseiten
        - Progressive Web-Apps
        - Desktop Apps (z. B. mit Electron)
  

HTML - Historie
------------------------------------------------

.. module:: timeline 
    :class: align-center far-far-smaller

    {
    "class" : "HTML-Timeline",
    "spread" : "0.9",
    "data": [
        {"d":"...","t":"HTML 1.0"},
        {"d":"1995","t":"HTML 2.0"},
        {"d":"1997","t":"HTML 3.2 (3.0 wurde nie veröffentlicht)"},
        {"d":"1998","t":"HTML 4.0/CSS"},
        {"d":"2000","t":"XHTML (HTML 4 in XML)"},	
        {"d":"2001","t":"XHTML 1.1"},	
        {"d":"seit 2004","t":"HTML5 in Entwicklung"},
        {"d":"2018","t":"XHTML 1.0 und 1.1 - obsolet"},	
        {"d":"seit 2019","t":"HTML(5) (W3C und WHATWG)"}
    ]
    }     

.. container:: far-smaller

    WHATWG ≘ Web Hypertext Application Technology Working Group



.. container:: footer-left far-far-smaller 

    Im folgenden bezeichnet HTML die HTML(5) Spezifikation (Living Standard).




HTML vs. XML Syntax vs. DOM
------------------------------------------------

Die *HTML Spezifikation* definiert eine abstrakte Sprache zur Beschreibung von Dokumenten. XML und HTML sind konkrete Syntaxbeschreibungen dieser abstrakten Sprache. 

.. class:: list-with-explanations incremental

- HTML ist eine Beschreibungssprache für entsprechende Dokumente.
- XML ist eine Beschreibungssprache, die auch für HTML verwendet werden kann. (MIME Type: ``application/xhtml+xml``) (nicht mehr empfohlen)

- Das DOM (:eng:`Document Object Model`) ist die In-Memory Darstellung eines Dokuments. 
  
  Das DOM ist ein API, um HTML Dokumente zu manipulieren.

.. admonition:: Hinweis
    :class: warning incremental far-smaller

    Das DOM, die HTML-Syntax und die XML-Syntax können nicht alle denselben Inhalt darstellen. 
    
.. supplemental::

    **Beispiele für Unterschiede**

    - Namespaces werden nicht von der HTML-Syntax unterstützt, aber sowohl vom DOM als auch der  XML-Syntax unterstützt. 
    - ``noscript`` wird nur in HTML Dokumenten unterstützt.
    - Kommentare, die ``-->`` enthalten, werden nur vom DOM unterstützt.



*HTML in a Nutshell*
------------------------------------------------

HTML-Dokumente bestehen aus einem Baum von Elementen und Text. 

.. container:: two-columns far-far-smaller

    .. container:: column

        .. rubric:: HTML Dokument

        .. code:: HTML
            :class: line-height-1-25em    
            
            <!DOCTYPE html>
            <html lang="de">
            <head><title>Eine Webseite</title></head>
            <body>
                <h1>Informationen</h1>
                <p><!-- Ein Kommentar.. -->
                    Ein einfacher link auf 
                    <a href="
                       http://www.michael-eichberg.de
                    ">
                        Michael Eichberg's Homepage
                    </a>.
                </p>
            </body>
            </html>

    .. container:: column

        .. rubric:: DOM

        .. code:: html 
            :class: line-height-1-25em    

            ├─DOCTYPE: html
            └─html lang="de"
                ├─head                                                                
                │ └─title
                │   └─#text: Eine Webseite
                ├─ #text: ⏎
                └─body
                  ├─ #text: ⏎␣␣␣␣
                  ├─ h1
                  ...

.. supplemental::

    Mehrere HTML Dokumente bilden ggf. auf den selben DOM ab. Zum Beispiel werden die Tags als solches gar nicht abgebildet und wenn im HTML Code ein optionales (schließendes) Tag fehlt, dann ist dies im DOM nicht mehr ersichtlich.


HTML - Verarbeitung
------------------------------------------------

.. image:: images/html.svg
    :alt: HTML Verarbeitung
    :width: 100%
    :align: center


.. class:: new-section transition-fade

Aufbau von HTML Dokumenten
------------------------------------------------


HTML Dokumente
------------------------------------------------

Die Dokumente müssen aus den folgenden Teilen in der angegebenen Reihenfolge bestehen:

  - Optional ein einzelnes U+FEFF BYTE ORDER MARK (BOM) Zeichen.
  - Eine beliebige Anzahl von Kommentaren und ASCII-Whitespace.
  - Eine DOCTYPE Deklaration: `<!DOCTYPE html>`.
  - Eine beliebige Anzahl von Kommentaren und ASCII-Whitespace.
  - Das **Dokumentenelement** in Form eines ``html``-Elements\ [#]_.
  - Eine beliebige Anzahl von Kommentaren und ASCII-Leerzeichen.

  -- HTML Spezifikation
  
.. [#] HTML ist nicht case-sensitive, d. h. ``html`` und ``HTML`` sind gleichwertig. Wir verwenden jedoch immer die Kleinschreibung.


Allgemeiner Aufbau von HTML Elementen
-------------------------------------

.. table:: 
    :class:  no-table-borders 
    :align: center

    +-------------+--------------------------+-----------+
    | *Start Tag* | *Inhalt des Elements*    | *End Tag* |
    +-------------+--------------------------+-----------+
    |                                                    |
    | .. code:: html                                     |
    |                                                    |
    |  <b>    Sehr Wichtig..  </b>                       |
    +-------------+--------------------------+-----------+
    | Element                                            |
    +-------------+--------------------------+-----------+

.. admonition:: Warnung
    :class: warning incremental

    Die Spezifikation verlangt nicht in allen Fällen ein Start und Endtag. Es ist jedoch eine gute Praxis, diese immer zu verwenden, wenn ein Endtag möglich ist.

.. incremental:: 

    Im Fall von Elementen ohne Endtag (z. B. ``<wbr>``) darf auch keines hinzugefügt werden!

.. supplemental::

    **Beispiel**

    .. code:: html

        <!DOCTYPE HTML><head>
                <title>Hello</title>
            </head>
            <body>
                <p>Welcome to this example.</p>
            </body>
        </html>

    Ist ein gültiges Dokument. Es ist jedoch **keine** gute Praxis (hier wurde das *Start Tag* des ``html`` Elements weggelassen.



Typen von HTML Elementen
---------------------------

Sechs Typen von HTML-Elementen werden unterschieden:

:Void elements: ``area``, ``base``, ``br``, ``col``, ``embed``, ``hr``, ``img``, ``input``, ``link``, ``meta``, ``source``, ``track``, ``wbr``
:Raw text elements: ``script``, ``style``
:Escapable raw text elements: ``textarea``, ``title``
:Das template Element: ``template``
:Foreign elements: Elemente aus dem MathML- und SVG-Namensraum.

.. container:: line-above margin-top-1em

    :Normal elements: **Alle weiteren HTML Elemente** sind *normale Elemente*.



Attribute in HTML
---------------------------------------

Attribute liefern Informationen über das Element. 

.. table:: 
    :class:  no-table-borders 
    :align: center

    +--+------------------------+-----------+
    | *Start Tag*                           |
    +--+------------------------+-----------+
    |                                       |
    | .. code:: html                        |
    |                                       |
    |  <a  class="obsolete" href="#top" >.. |
    +--+------------------------+-----------+
    |  | Attribute              |           |
    +--+------------------------+-----------+

.. class:: incremental list-with-explanations

- Attribute kommen nur beim Start Tag vor.
- Attribute (in HTML) können, müssen aber kein Wert haben (Boolsche Attribute).
- Attributwerte sollten in Anführungszeichen (:eng:`quoted`) (entweder: ``"`` oder ``'``) stehen, müssen aber nicht.  

  Werte ohne Anführungszeichen dürfen keine Leerzeichen oder Anführungszeichen  enthalten.
- Konkrete Attributwerte aus der HTML Spezifikation sind case-insensitive; andere Werte sind es nicht. 
 
.. supplemental::

  Im Allgemeinen sollten Attributwerte klein geschrieben werden. Selektoren in CSS und JavaScript sind case-sensitive.

  Z. B. ist ``<input type="text">`` und ``<input type="TEXT">`` gleichwertig, aber ``<div id="text">`` und ``<div id="Text">`` nicht!



HTML Grundgerüst
------------------------------------------------

.. code:: HTML

    <!DOCTYPE HTML">

    <html lang="de">
        <head>
        ... Meta-Daten, Scripte, Stylesheets, etc. ...
        </head>

        <body>
        ... das Dokument ...
        </body>
    </html>



HTML ``<head>`` Element - „obligatorische“ Elemente
-----------------------------------------------------------------

Im Head sollten immer die folgenden Informationen deklariert werden:

.. container:: smaller

  :Titel: Der Titel des Dokuments mit Hilfe des ``title`` elements
  :Zeichensatz: Der verwendete Zeichensatz mit Hilfe des passenden meta elements: ``<meta charset="utf-8">``
  :Viewport: Konfiguration des *Viewports*\ [#]_ (insbesondere für mobile Geräte relevant): ``<meta name="viewport" content="width=device-width, initial-scale=1.0">``

.. [#] Der *Viewport* (:ger:`Ansichtsbereich`) des Browsers ist der Bereich des Fensters, in dem der Webinhalt zu sehen ist. 
  
.. supplemental::

    Insbesondere Mobilgeräte haben oft entweder eine geringere Auflösung als Desktop-Computer oder verwenden HiDPI Screens. Beides führt dazu, dass die Webseiten nicht wie gewünscht aussehen. In diesem Fall verwenden die Browser für die Webseiten einen virtuellen Viewport mit (z. B.) 960px und skalieren dann die Seite (z. B.) auf 390px herunter. Wenn dieses Verhalten nicht gewünscht ist — z. B. weil die Seite :eng:`Responsive` ist oder von vorneherein auf mobile Endgeräte ausgerichtet ist — dann ist auf jeden Fall eine *Viewport* Konfiguration notwendig.

    .. csv-table::
        :header: "Device", "Viewport Size (width x height)", "Device Resolution (width x height)"
        
        iPhone 12, 390 x 844, 1170 x 2532
        iPhone 12 Mini, 360 x 780, 1080 x 2340
        iPhone 12 Pro, 390 x 844, 1170 x 2532
        iPhone 12 Pro Max, 428 x 926, 1248 x 2778

    Siehe: https://experienceleague.adobe.com/en/docs/target/using/experiences/vec/mobile-viewports.html?lang=de für weitere Details.


HTML ``<head>`` Element - weitere Elemente
-----------------------------------------------------------------

Im Head können weitere Informationen und Pragmas deklariert werden bzw. sollten dort deklariert werden, wenn sie benötigt werden:

.. container:: scrollable
    
    .. class:: incremental

    :Skripte: ``<script [src="script.js" [defer|async]]></script>``

    .. class:: incremental

    :CSS: Mittels ``<link rel="stylesheet" href="style.css">`` oder  ``<style>...</style>``

    .. class:: incremental
    
    :Favorite Icon: ``<link rel="icon" type="image/png" href="/img/icon.png" />``

    .. class:: incremental

    :Pragmas:

        .. container:: minor far-smaller

            ``http-equiv`` ≘ *HTML equivalent*; d. h. die Informationen könnte auch im HTTP Header stehen.

        - ``<meta charset="utf-8">`` (alt)
        - ``<meta http-equiv="Content-Security-Policy" content="default-src https:">``

          .. incremental:: far-smaller 

              Äquivalente HTTP Header Definition: 
                    
              .. code:: http
                        
                Content-Security-Policy: default-src https:

    .. class:: incremental

    :Benannte Meta-Daten: ``<meta name="author" content="Michael Eichberg">``


.. supplemental::

    `Content Security Policies <https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Security-Policy>`__



Semantisches HTML vs. „generisches“ HTML
-----------------------------------------------------------

.. container:: two-columns

    .. incremental:: column 

        .. rubric:: Semantisches HTML

        - Verwendung von HTML Elementen, die die Bedeutung des Inhalts klar machen.
        - Bessere Zugänglichkeit
        - Bessere Suchmaschinen-Optimierung

        .. incremental:: 

            **Beispiel Elemente**

            ``<header>``, ``<footer>``, ``<nav>``, ``<article>``, ``<section>``, ``<aside>``, ``<main>``, ``<figure>``,  ``<address>``, ``<em>``, ``<s>``, ...

    .. incremental:: column

        .. rubric:: Nicht-Semantic HTML

        - Verwendung von ``<div>`` und ``<span>`` Elementen, um den Inhalt zu strukturieren.
        - Keine klare Bedeutung des Inhalts.

        


Semantisches HTML vs. „generisches“ HTML - Beispiel
---------------------------------------------------------------------

.. container:: two-columns smallest

    .. container:: column

        .. code:: html

            <div>
                <span>Zwei Wörter</span>
                <div>
                    <a>Ein Wort</a>
                    <a>Ein Wort</a>
                </div>
            </div>
            <div>
                <div>
                    <div>Viele Wörter</div>
                </div>
                <div>
                    <div>Erste Worte</div>
                    <div>DaDaDa</div>
                    <div>BlaBlaBla</div>
                </div>
            </div>
            <div>
                <span>Alle Worte</span>
            </div>

    .. container:: column incremental

        .. code:: html

            <header>
                <h1>Zwei Wörter</h1>
                <nav>
                    <a>Ein Wort</a>
                    <a>Ein Wort</a>
                </nav>
            </header>
            <main>
                <header>
                    <h1>Viele Wörter</h1>
                </header>
                <section>
                    <h2>Erste Worte</h2>
                    <p>DaDaDa</p>
                    <p>BlaBlaBla</p>
                </section>
            </main>
            <footer>
                <p>Alle Worte</p>
            </footer>

.. supplemental::

    Semantische Informationen im DOM zu haben, ist insbesondere für die Barrierefreiheit notwendig.

    Alternativ zur Verwendung von semantischen Elementen können auch generische Attribute mit dem ``role`` Attribute versehen werden, um die Bedeutung des Elements zu spezifizieren: ``<div role="navigation">...</div>``



.. class:: center-child-elements no-title

HTML dient der Strukturierung von Inhalten
------------------------------------------------

    Verwenden Sie HTML zur Strukturierung von Inhalten, und nicht, um das Aussehen der Inhalte zu definieren. 
    
    Das Aussehen ist Sache von CSS. 



Strukturierung von Dokumenten
------------------------------------------------

.. class:: incremental list-with-explanations

- ``header``, ``footer``, ``nav``, ``article``, ``section``, ``aside``, ``main``, ``figure``, ``address``, ...
  
  In Hinblick auf die konkrete Semantik eines Elements gibt es Unterschiede wo und wie oft diese verwendet werden. 
  
  Ein ``footer`` Element innerhalb eines ``article`` Elements hat eine andere Bedeutung als ein ``footer`` Element auf oberster Ebene.

  Ein ``main`` Element sollte nur einmal pro Dokument verwendet werden.
- Überschriften: ``h1``, ``h2``, ``h3``, ``h4``, ``h5``, ``h6`` 
  
  Überschriften sollten in der richtigen Reihenfolge verwendet werden.
- Überschriften gruppiert mit zugehörigem Inhalt: ``hgroup``.

.. supplemental::


  Das ``hgroup``-Element stellt eine Überschrift und den zugehörigen Inhalt dar. Dient dazu  eine Überschrift mit einem oder mehreren p-Elementen zu gruppieren. Zum Beispiel für eine Unterüberschrift oder einen alternativen Titel.


Attribute
------------------------------------------------

.. class:: incremental

:Boolsche Attribute: sind wahr, wenn diese angegeben sind und falsch andernfalls.
 

  .. container:: far-smaller
    
    Z. B. ``<input id="the-checkbox" type="checkbox" checked>``. 

.. class:: incremental

:Aufgezählte Attribute (`enumerated values`:eng:): definieren eine begrenzte Anzahl von gültigen Werten sowie einen Default, der verwendet wird, wenn kein Wert angegeben ist, aber das Attribut verwendet wird.

.. class:: incremental

:Globale Attribute: 

    können für jedes Element verwendet werden; sind aber nicht immer sinnvoll.\ [#]_

    Globale HTML Attribute sind Z. B. ``id``, ``class``, ``data-*``, ``autofocus``, ``role``, ``lang``, ``style``, ``popover``, ``tabindex``.

    .. container:: minor
    
        Event Handler Attribute: ``onclick``, ``onclose``, ...
   

.. [#] `Globale Attribute <https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes#list_of_global_attributes>`__

.. supplemental::

    Boolsche Attribute sollten in JavaScript durch hinzufügen bzw. löschen gesetzt werden (und nicht durch die Manipulation des Wertes eines Attributs).

    .. code:: JavaScript
    
        const checkbox = document.getElementById("the-checkbox");
        checkbox.removeAttribute("checked");
        checkbox.setAttribute("checked");


.. supplemental::

    Der Wert eines Attributs kann über mehrere Zeilen gehen solange diese keine Anführungszeichen enthalten. Zeilenumbrüche und Einrückungen (mit Tabulatoren (⇥)) werden dabei automatisch gefiltert.
    
    Zum Beispiel kann der ``content``-Wert des ``meta``-Elements wie folgt geschrieben werden:

    .. code:: html
        :class: far-smaller copy-to-clipboard

        <meta name="author" content="
        ⇥ ⇥Michael Eichberg
        ⇥ ⇥ Professor
        ">

    Dies ist äquivalent zu:
    
    .. code:: html
        :class: far-smaller copy-to-clipboard

        <meta name="author" content="Michael Eichberg Professor">



Ausgewählte globale Attribute
------------------------------------------------

.. container:: scrollable

    :``id``: 

        - verwendet, um ein Element eindeutig zu identifizieren
        
          (Welches man in CSS oder JavaScript per Selektor referenzieren kann.)
        - als Ziel von Hyperlinks (``<a href="#id">``)
        - im Rahmen der Unterstützung von Barrierefreiheit
        - der Wert ist case-sensitive 

        Best Practice: Kleinbuchstaben und Bindestriche verwenden (Unterstriche sind erlaubt aber im Zusammenhang mit CSS nicht optimal).

    .. class:: incremental

    :``class``:

        - das ``class``\ -Attribut ermöglicht es Elemente mit CSS und JavaScript anzusprechen
        - dient keinem anderen Zweck in HTML 
        - wird sehr häufig von Frameworks und Bibliotheken verwendet

    .. class:: incremental

    :``style``: Das ``style``-Attribut ermöglicht die (ad-hoc) Anwendung von Inline-Styles auf das entsprechende Element (nicht empfohlen).

    .. class:: incremental

    :``data-*``: Das ``data-*``-Attribut ermöglicht es, benutzerdefinierte Daten an das Element zu binden, die von JavaScript verwendet werden können. ``*`` kann ein beliebiger Name sein, aber nicht ``xml`` oder ``:``  enthalten.



HTML - logische Gruppierung von Text
------------------------------------------------

.. container:: scrollable

    :Paragraphen: ``<p>Inhalt</p>``

    .. class:: incremental

    :Zitate: ``<blockquote>`` und ``<q>`` (für kurze Zitate innerhalb eines Absatzes)

        Das Inline-Zitat-Element ``<q>`` fügt der Sprache entsprechende Anführungszeichen hinzu.

        **Beispiel**

        .. container:: two-columns incremental far-smaller

            .. container:: column

                .. raw:: html

                    <q lang="de">Ein Zitat</q> (deutsch)

                    <q lang="en">A quote</q> (englisch)  

            .. container:: column

                .. code:: html

                    <q lang="de">Ein Zitat</q> 

                    <q lang="en">A quote</q> 

    .. class:: incremental

    :Betonung: ``<em>`` (:eng:`emphasized`) und ``<strong>`` 

    .. class:: incremental

    :Randbemerkungen: ``<small>`` - für Randbemerkungen und Kleingedrucktes (d. h. ``small`` steht nicht für unwichtige(re)n Text oder die Schriftgröße) 

    .. class:: incremental

    :Veraltet bzw. nicht mehr korrekt: ``<s>``

    .. class:: incremental

    :Zitierung: ``<cite>`` - für den **Titel** eines Werkes oder einer Publikation

    .. class:: incremental

    :Definitionen: ``<dfn [title="der definierte Begriff"]>`` - für die Definition eines Begriffs

    .. class:: incremental

    :Abkürzungen: ``<abbr title="HyperText Markup Language">HTML</abbr>`` - für Abkürzungen

    .. class:: incremental

    :Zeitangaben: ``<time datetime="2021-10-01">1. Oktober 2021</time>`` - für Zeitangaben

    .. class:: incremental

    :Code: ``<code>`` - für Code; für das Darstellen von Code-Beispielen wird ``code`` häufig mit ``<pre>`` kombiniert; die Sprache des Codes wird dann über ein ``class`` Attribute spezifiziert (z. B. ``<pre><code class="language-java">...</code></pre>``)

    .. class:: incremental

    :Variablen:    ``<var>`` - für Variablen in mathematischen oder Programmierkontexten


    .. class:: incremental

    :(Tastatur-)Eingaben: ``<kbd>`` - für Tastatureingaben oder andere Benutzereingaben

        .. code:: html
            :class: far-smaller

            Drücken Sie <kbd>cmd</kbd> + <kbd>c</kbd> zum Kopieren.

    .. class:: incremental
    
    :Hoch-/Tiefstellung: ``<sup>`` und ``<sub>`` - für Hoch- und Tiefstellung, die nicht typographisch Zwecken dient, sondern inhaltlichen Zwecken. 

        .. code:: html
            :class: far-smaller
            
            H<sub>2</sub>O steht für Wasser.

    .. class:: incremental

    :Text mit abweichender Bedeutung: ``<i>`` - Text, der von normaler Prosa abweicht wie z. B. eine taxonomische Bezeichnung, ein technischer Begriff, ...

        .. code:: html
            :class: far-smaller

            Brot besteht aus <i>Mehl</i>.

    .. class:: incremental

    :Text mit erhöhter Aufmerksamkeit: ``<b>`` - Text, der erhöhte Aufmerksamkeit erfordert, aber nicht unbedingt betont werden muss; z. B. Schlüsselwörter in einem Artikel.

        .. code:: html
            :class: far-smaller
                
            <p>Das <b>Wetter</b> ist heute schön.</p>

    .. class:: incremental

    :Text mit erhöhter Bedeutung: ``<mark>`` - Text, der hervorgehoben werden soll, z. B. Suchergebnisse.


.. supplemental::

    Es gibt weitere Elemente, die für spezielle Anwendungsfälle verwendet werden können. Siehe `WHATWG <https://html.spec.whatwg.org/multipage/text-level-semantics.html>`__.



HTML - physische Auszeichnung von Text
------------------------------------------------

.. container:: scrollable

    :Vorformatierter text: ``<pre>...</pre>`` - für Text, der so angezeigt werden soll, wie er geschrieben wurde)

    .. class:: incremental

    :Zeilenumbrüche: ``<br>`` - für Zeilenumbrüche, die inhärenter Teil der Daten sind wie zum Beispiel bei Adressen. D. h. sollte nicht innerhalb von Text verwendet werden!

    .. class:: incremental

    :Optionale Zeilenumbrüche: ``<wbr>`` (:eng:`word break opportunity``) - ein optionaler Zeilenumbruch 

        (Beispiel: ``<p>Er schrie: <q lang="de">Lasst<wbr>Mich<br>In<wbr>Ruhe!</q></p>``) 



HTML - ``<span>`` und ``div``
------------------------------------------------

- ``<span>`` und ``<div>`` sind generische Container-Elemente, die verwendet werden, um Text oder andere Elemente zu gruppieren.
- ``<span>`` ist ein Inline-Element
- ``<div>`` ist ein Block-Element
- beide werden häufig verwendet, um CSS-Klassen zuzuweisen, um den Inhalt zu gruppieren oder um den Inhalt zu manipulieren.



HTML - ``data``
------------------------------------------------

- Das ``data``-Element ermöglicht es, maschinenlesbare Date an ein Element zu binden, die dann von JavaScript verwendet werden können. 
- Die maschinenlesbaren Daten werden im ``value`` Attribut gespeichert.

  .. code:: html

    <data value="8">Acht</data>




HTML - Links
------------------------------------------------

.. container:: scrollable

  .. class:: incremental

  - Hyperlinks werden mit dem ``<a>`` Element erstellt.
  - Der ``href``-Attribut enthält die Adresse des Ziels (innerhalb des gleichen Dokuments, auf einer anderen Webseite, per E-Mail, ...)

    .. code:: html
      :number-lines:
      :class: far-smaller
   
      <a href="https://www.dhbw-mannheim.de">DHBW Mannheim</a>
      <a href="#teachers">Unsere Lehrenden</a>
      <a href="mailto:michael.eichberg@dhbw.de">Email: Michael Eichberg</a>

    .. container:: far-smaller

      1. Externer Link
      2. Interner Link (:eng:`link fragment identifier`) auf ein Element mit der ID ``teachers``
      3. E-Mail Link - kann ergänzt werden durch ``subject`` und ``body`` Parameter innerhalb des ``href`` Attributs.
  - Das ``target``-Attribut ermöglicht die Definition des Browsing-Kontextes für die Link-Navigation (und die Formularübermittlung).
    
    .. code:: html
      :number-lines:
      :class: far-smaller
   
      <a target="_blank"  href="https://www.dhbw-mannheim.de">DHBW Mannheim</a>
      <a target="_self"  href="https://www.dhbw-mannheim.de">DHBW Mannheim</a>
      <a target="_top"  href="https://www.dhbw-mannheim.de">DHBW Mannheim</a>
      <a target="_parent"  href="https://www.dhbw-mannheim.de">DHBW Mannheim</a>
      <a target="dhbw"  href="https://www.dhbw-mannheim.de">DHBW Mannheim</a>
      
    .. container:: far-smaller

      1. Öffnet den Link in einem neuen Fenster oder Tab
      2. Öffnet den Link im gleichen Browsing-Kontext
      3. Öffnet den Link im obersten Browsing-Kontext
      4. Öffnet den Link im übergeordneten Browsing-Kontext 
      5. Öffnet den Link im Browsing-Kontext mit dem Namen `dhbw` (Beispiel: :raw-html:`<a target="dhbw" href="https://www.dhbw-mannheim.de">DHBW Mannheim</a>`)
       
      ``_self``, ``_top`` und ``_parent`` sind relativ zum aktuellen Browsing-Kontext und unterscheiden sich nur, wenn die Seite in einem Frame oder einem iframe angezeigt wird.

  - Das ``rel``-Attribut legt die Art des Links fest und definiert die Beziehung zwischen dem aktuellen Dokument und der Ressource, auf die der Hyperlink verweist. (Z. B. ``rel="license"``, ``rel="author"`` oder ``rel="noopener"``; siehe `MDN rel attribute <https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/rel>`__)


.. supplemental::

    Durch die Zuweisung zu einem Browsing-Kontext kann verhindert werden, dass die selbe Seite X mal geöffnet wird, wenn ein Nutzer auf den Link klickt.


HTML - Lists
------------------------------------------------

Drei Arten von Listen werden unterstützt, die beliebig verschachtelt werden können:

.. container:: scrollable

    .. class:: incremental

    - Definitionslisten: ``<dl>``

      .. container:: two-columns

          .. container:: column

            .. code:: html
                :class: far-smaller

                <dl>
                    <dt>Erster Begriff</dt>
                    <dd>Erklärung des 1. Begriffs</dd>
                    <dt>Zweiter Begriff</dt>
                    <dd>Erklärung des 2. Begriffs</dd>
                </dl>

          .. container:: column far-smaller

            .. raw:: html                    

                <dl>
                    <dt style="font-weight:bold">Erster Begriff</dt>
                    <dd style="margin-left:3em">Erklärung des 1. Begriffs</dd>
                    <dt style="font-weight:bold">Zweiter Begriff</dt>
                    <dd style="margin-left:3em">Erklärung des 2. Begriffs</dd>
                </dl>

    - geordnete Listen: ``<ol [reversed] [start=<NO>]>``

      .. container:: two-columns

          .. container:: column

             .. code:: html
                :class: far-smaller

                <ol start="0">
                    <li>Erster Punkt</li>
                    <li>Zweiter Punkt</li>
                    <li value="10">Dritter Punkt</li>
                </ol>

          .. container:: column far-smaller 

             .. raw:: html                 
                :class: margin-left-2em   

                <ol start="0">
                    <li>Erster Punkt</li>
                    <li>Zweiter Punkt</li>
                    <li value="10">Dritter Punkt</li>
                </ol>


    - ungeordnete Listen: ``<ul>``

      .. container:: two-columns

          .. container:: column

             .. code:: html
                :class: far-smaller

                <ul>
                    <li>Erster Punkt</li>
                    <li>Zweiter Punkt</li>
                </ul> 

          .. container:: column far-smaller

             .. raw:: html                    

                <ul>
                    <li>Erster Punkt</li>
                    <li>Zweiter Punkt</li>
                </ul> 






HTML - Navigation
------------------------------------------------

- Das ``<nav>`` Element wird verwendet, um Navigationslinks zu gruppieren.
- Insbesondere für Screenreader und die Suchmaschine relevant.

.. container:: incremental

    .. rubric:: Beispiel

    .. code:: HTML
        :class: far-smaller

        <nav id="ld-menu">
            <button type="button" 
                    id="ld-slides-button" 
                    aria-label="show slides"></button>
            <button type="button" 
                    id="ld-slides-with-nr-button" 
                    aria-label="show slides with numbers"></button>
            <button type="button" 
                    id="ld-help-button" 
                    aria-label="show help"></button>
        </nav>


HTML - Tabellen
------------------------------------------------

.. container:: scrollable

    Verwendet für die Darstellung von tabellarischen Daten mit Zeilen und Spalten. 
    
    .. admonition:: Hinweis
            :class: warning incremental

            Die Verwendung von <table> sollte sich nach dem Inhalt richten!

            Tabellen sollten nicht zum Layout von Webseiten verwendet werden.

    .. incremental:: far-smaller

        Aufbau von Tabellen:

        .. container:: two-columns

            .. container:: column no-separator

                .. code:: html
                    :class: far-smaller

                    <table>
                        <caption>Logische Operation</caption>
                        <thead>
                            <tr><th>not xor</th><th>1</th><th>0</th></tr>
                        </thead>
                        <tbody>
                            <tr><th>1</th><td>1</td><td>0</td></tr>
                            <tr><th>0</th><td>0</td><td>1</td></tr>
                        </tbody>
                        <tfoot></tfoot>
                    </table> 


            .. container:: column padding-left-1em

                .. raw:: html
                
                    <table>
                        <caption>Logische Operation</caption>
                        <thead>
                            <tr>
                                <th>not xor</th>
                                <th>1</th>
                                <th>0</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th>1</th>
                                <td>1</td>
                                <td>0</td>
                            </tr>
                            <tr>
                                <th>0</th>
                                <td>0</td>
                                <td>1</td>
                            </tr>
                        </tbody>
                        <tfoot></tfoot>
                    </table>         

    .. class:: incremental

    - Zellen, die über mehrere Spalten oder Zeilen gehen können mit Hilfe von ``colspan`` und ``rowspan`` Attributen definiert werden.

    - Spalten und Zeilen können mit Hilfe von ``<col>`` und ``<colgroup>`` Elementen definiert werden.


HTML - Images
------------------------------------------------

.. container:: scrollable

    .. class:: incremental

    - Bilder werden mit dem ``<img>`` Element eingebunden.

      .. code:: html
         :class: far-smaller

            <img src="path/filename" alt="descriptive text" />

    - Das ``src``-Attribut enthält die Adresse des Bildes.
    - Das ``alt``-Attribut enthält eine Beschreibung des Bildes, die angezeigt wird, wenn das Bild nicht geladen werden kann.
    - Das ``width`` und ``height``-Attribut können und sollten verwendet werden, um die Größe des Bildes festzulegen.
    - Lazy loading ist durch die Verwendung des ``loading`` Attributs möglich (:raw-html:`loading="lazy"`).

    - Folgende Bildformate werden breit unterstützt: ``jpg``, ``png``, ``gif``, ``svg`` und ``webp``. 
    - Responsive Images werden über das ``srcset`` Attribut unterstützt:
    
      .. code:: html
        :class: far-smaller
    
        <img src="images/dhbw.png" alt="Logo der DHBW"
            srcset="images/dhbw.png 400w, images/dhbw-xl.jpg 800w"
            sizes="(max-width: 800px) 400px, 800px" />

    .. incremental::

        Weitere `Responsive Features <https://web.dev/learn/design/responsive-images>`__) werden mittels CSS ermöglicht. Um zum Beispiel zu verhindern, dass ein Bild größer als eine Textzeile wird, kann folgendes CSS definiert werden:

        .. code:: css
            :class: far-smaller

            img {
                max-inline-size: 100%;
                block-size: auto;
            }


HTML - Formulare
------------------------------------------------

.. container:: scrollable

    .. incremental::

        Formulare werden mit dem ``<form>`` Element erstellt.

        .. class:: incremental

        - ``action`` enthält die Adresse, an die die Formulardaten gesendet werden.
        - ``method`` definiert die Methode, die zum Senden der Daten verwendet wird (``GET`` oder ``POST``).
        - ``name`` setzt den Namen des Formulars.
        - ``target`` enthält den Namen des Browsing-Kontexts, in dem die Antwort angezeigt wird.
        - ``autocomplete`` ermöglicht das automatische Ausfüllen von Formularen.
        - ``novalidate`` verhindert die Validierung der Formulardaten durch den Browser.
        - ``accept-charset`` definiert die Zeichencodierung, die zum Senden der Formulardaten verwendet wird.

    
    .. incremental:: 

        Formularelemente werden mit dem ``<input>`` Element erstellt.

        .. class:: incremental

        - ``type`` definiert den Typ des Formularelements.
        - ``name`` definiert den Namen des Formularelements.
        - ``value`` definiert den Wert des Formularelements.
        - ``placeholder`` definiert den Platzhaltertext des Formularelements.
        - ``required`` definiert, ob das Formularelement erforderlich ist.
        - ``disabled`` definiert, ob das Formularelement deaktiviert ist.
        - ``autofocus`` definiert, ob das Formularelement den Fokus erhält.
    
    .. incremental:: far-smaller
            
        .. rubric:: Beispiel

        .. container:: two-columns 
        
            .. container:: column
        
                .. code:: html

                    <form method="GET" 
                          name="Folienauswahl">
                      <label for="slide">Folie:</label>
                      <select name="ld-slide-no" id="slide">
                        <option value="8">Elemente</option>
                        <option value="10">Attribute</option>
                        <option value="29">Formulare</option>
                      </select>
                      <input type="submit" value="Submit">
                    </form>
        
            .. container:: column

                .. raw:: html

                    <form method="GET" name="Folienauswahl">
                      <label for="slide">Folie:</label>
                      <select name="ld-slide-no" id="slide">
                         <option value="8">Elemente</option>
                         <option value="10">Attribute</option>
                         <option value="29">Formulare</option>
                      </select>
                      <input type="submit" value="Submit">
                    </form>

.. supplemental::

    Für weitere Informationen bzgl. Formulare siehe `MDN Web Docs <https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form>`__ oder `Web.dev <https://web.dev/learn/html/forms>`__.



HTML - Zusammenfassungen und Details
------------------------------------------------

HTML unterstützt verschiedene interaktive Elemente:

- Anzeigen von optionalen Details mit Hilfe des ``<details>`` Elements.

  .. container:: two-columns
  
    .. container:: column

        .. code:: html
            :class: far-smaller

            <details [open]>
                <summary>Abstract</summary>
                <p>Password guessing ...</p>
            </details>

    .. container:: column

        .. raw:: html
            :class: far-smaller

            <p>
                Geschlossen - Details werden erst nach einem Klick angezeigt:

                <details>
                    <summary>Abstract</summary>
                    <p>Password guessing ...</p>
                </details>
            </p>

            <p>
                Offen - Details werden direkt angezeigt:

                <details open>
                    <summary>Abstract</summary>
                    <p>Password guessing ...</p>
                </details>
            </p>



HTML - Dialoge
------------------------------------------------

- Dialoge werden mit Hilfe des ``<dialog>`` Elements erstellt. Dialoge sind spezielle Fenster, die den Fokus auf sich ziehen und die Interaktion mit dem Rest der Seite unterbrechen - falls diese modal sind.

  .. container:: two-columns
  
    .. container:: column no-separator

        Beispiel\ [#]_:

        .. code:: html
            :class: far-smaller

            <dialog>
              <h1>Dialog</h1>
              <p>Dialog Inhalt</p>
              <button formmethod="dialog">
                OK
              </button>
              <button autofocus>Abbrechen</button>
            </dialog>

    .. container:: column center-child-elements

        .. raw:: html
            :class: smaller

            <button onclick="document.querySelector('#html-dialog-example').showModal()">Open Dialog</button>

            <dialog id="html-dialog-example" class="ld-dialog" style="color:white;padding:1em">
                <header>Dialog</header>
                <p>Dialog Inhalt</p>
                <button formmethod="dialog" tabindex=2 onclick="document.querySelector('#html-dialog-example').close()">OK</button>
                <button autofocus tabindex=1 onclick="document.querySelector('#html-dialog-example').close()">Abbrechen</button>
            </dialog>

.. [#]  :minor:`JavaScript Code zum Öffnen des Dialogs wird hier nicht gezeigt.`



HTML Entities
------------------------------------------------

Ausgewählte Zeichen können (in manchen Kontexten) nur durch HTML Entities dargestellt werden:

- ``<`` durch &lt; oder &#60; (:raw-html:`&#60;`)

- ``>`` durch: &gt; oder &#62;  (:raw-html:`&#62;`)

- ``&`` durch: &amp; oder &#38;  (:raw-html:`&#38;`)

- ``"`` durch: &quot; oder &#34;  (:raw-html:`&#34;`)

- ``␣`` durch: &nbsp;  (:raw-html:`&nbsp;`)

  
.. container:: footer-left  far-smaller

    `Benannte Zeichen <https://html.spec.whatwg.org/multipage/named-characters.html#named-character-references>`__

    Die numerischen Werte sind `Unicode Code Points <https://en.wikipedia.org/wiki/List_of_Unicode_characters>`__ (d.h. ``#60`` ist  der Unicode Code Point von ``<``).



Eingebettet Webseiten
------------------------------------------------

Das ``<iframe>`` Element ermöglicht das Einbetten von Webseiten in Webseiten:

.. container:: two-columns 

    .. container:: column no-separator

        .. code:: html
            :class: far-far-smaller

            <iframe src="https://www.dhbw-mannheim.de" 
                    width="600" 
                    height="400">
                iframes are not supported</iframe>

            <iframe srcdoc="
                        <h1>HTML</h1>
                        <p>HTML is a markup language.</p>" 
                    width="600" 
                    height="400">
                iframes are not supported
            </iframe>

    .. container:: column

        .. raw:: html

            <iframe srcdoc="<html style='font-size:32px'>
                        <h1>HTML</h1>
                        <p>HTML is a markup language.</p>" 
                    width="900" 
                    height="600" style="border:1px solid black; box-shadow: 2px 2px gray;">
                iframes are not supported
            </iframe>


        


HTML Erweiterbarkeit
------------------------------------------------

.. class:: incremental

- Hinzufügen von Meta-daten (``<meta name="" content="">``)
- ``class`` Attribute
- „Custom Elements“ (z. B. ``<my-element>``)
- Autoren können APIs mit Hilfe des JavaScript-Prototyping-Mechanismus erweitern


Veraltetes - aber noch unterstütztes - HTML
------------------------------------------------

.. class:: incremental

- keine ``border`` Attribute auf ``img`` Elementen
- keine ``charset`` Attribute auf ``script`` Elementen (utf-8 ist gefordert)
- keine ``language`` Attribute auf ``script`` Elementen (JavaScript ist der Standard)
- kein ``type`` Attribute auf ``style`` Elementen (``text/css`` ist der Standard)


HTML - „nicht mehr unterstützt - April Stand 2024“
--------------------------------------------------------------

Nicht mehr unterstützte Elemente (Auswahl):

.. class:: far-far-smaller

- ``big``
- ``blink``
- ``center``
- ``font``
- ``marquee``
- ``nobr``
- ``tt``
- ``menuitem``
- ...
  
Nicht mehr unterstützte Attribute (Auswahl):

.. class:: far-far-smaller

- ``align`` bei ``h1`` bis ``h6`` Elementen
- ``bgcolor`` bei ``body`` Elementen
- ``charset`` bei ``a`` und ``link`` Elementen
- ``name`` bei ``img``, ``option``, ... Elementen



Barrierefreiheit ist verpflichtend
--------------------------------------


.. epigraph::

    [...] Webseiten, Onlineshops, Apps, Onlinebuchungs- und Ticketdienste, Fahrkartenautomaten, Selbstbedienungs- und Zahlungsterminals, Telefon- und Messenger-Dienstleistungen sowie E-Book-Reader ab Juni 2025 barrierefrei sein müssen. Denn am 28. Juni 2025 tritt das Barrierefreiheitsstärkungsgesetz (BFSG)\ [#]_ in Kraft. [...]

    .. container:: incremental

        [...] Diese [die Barrierefreie-Informationstechnik-Verordnung (BITV 2.0)] regelt, wie Behörden und Ministerien Webseiten gestalten müssen. In Paragraf 3 Absatz 2 ist festgelegt, dass diese dann als barrierefrei anzusehen sind, wenn sie Normen erfüllen, die im Amtsblatt der EU genannt werden – beispielsweise die EN 301 549 oder die Web Content Accessibility Guidelines 2.1 (WCAG) des World Wide Web Consortiums.

    .. container:: incremental

        [...] Verstöße gegen das Barrierefreiheitsstärkungsgesetz [...] muss mit einem Bußgeld bis zu 100.000 Euro rechnen. [...]

    -- Sept. 2024; `Golem.de - Deutsche Webseiten sind versetzungsgefährdet <https://www.golem.de/news/barrierefreiheit-deutsche-webseiten-sind-versetzungsgefaehrdet-2409-188655.html>`__

.. [#] https://bfsg-gesetz.de/



Web Content Accessibility Guidelines 2.1 (WCAG) 
--------------------------------------------------------------- 

Einige Anforderungen\ [#]_:

.. container:: smaller

    .. rubric:: „Harte“ Kriterien

    .. class:: incremental smaller

    - die Schrift auf einer Website wenigstens 16 Pixel groß und 
    - Zeilen nicht mehr als 80 Zeichen lang sein sollen. 
    - Der Abstand dazwischen soll das 1,5-Fache ihrer Höhe betragen. 
    - Um einen ausreichenden Kontrast zu erzeugen, muss die Schrift wenigstens 4,5-mal dunkler sein als der Hintergrund. 
    - Klickflächen schließlich müssen wenigstens 44 x 44 Pixel groß sein.

    .. rubric:: „Weiche“ Kriterien

    .. class:: incremental smaller

    - Starke Animationen sind auf barrierefreien Seiten tabu.
    - das Layout der Seite außerdem einfach, logisch und auf jeder Unterseite einheitlich aufgebaut sein.

.. [#] Sept. 2024; `Golem.de - So setzen gute Webdesigner Barrierefreiheit um
 <https://www.golem.de/news/barrierefreiheit-deutsche-webseiten-sind-versetzungsgefaehrdet-2409-188655-2.html>`__



.. class:: transition-fade

Referenzen
------------------------------------------------

- `MDN Web Docs <https://developer.mozilla.org/en-US/docs/Web/HTML>`__
- `caniuse.com: Unterstützung von HTML, CSS etc. Features <https://caniuse.com>`__ 
- `HTML (Living Standard) <https://html.spec.whatwg.org>`__ (aka HTML5)
- `HTML DOM <https://developer.mozilla.org/en-US/docs/Web/API/Document_Object_Model>`__
- `Web Content Accessibility Guidelines <https://www.w3.org/TR/WCAG21/>`__


.. class:: integrated-exercise transition-scale

Übung
--------    

.. container:: two-columns

    .. container:: column smaller no-separator

        Erzeugen Sie ein HTML Dokument, das wie das Dokument auf der rechten Seite aussieht.

        Nutzen Sie den `HTML Validator <https://validator.w3.org/nu/#textarea>`__, um zu verifizieren, dass Ihr Dokument valide ist. 

        Achten Sie auf eine korrekte Strukturierung des Dokuments und verwenden Sie semantische Elemente, wo immer dies sinnvoll ist. Denken Sie auch grundlegend an die Barrierefreiheit.

    .. container:: column

        .. raw:: html
            :class: center-child-elements 

            <iframe width=850px height=900px srcdoc='
                <!DOCTYPE html>
                <html lang="de">
                <head>
                <title>Lebenslauf von X Y</title>
                <meta name="author" content="X Y zu W">
                <meta charset="utf-8">
                </head> 
                <body style="font-size:36px"> 
                    <header>
                    <nav>
                    <a href="#Ausbildung">Ausbildung</a> <a href="#ehrenamt">Ehrenamt</a>
                    </nav>
                    <hr>
                    </header>                    
                    <main>
                        <section>
                            <strong>Lebenslauf</strong>
                            
                            <address>
                                Musterstraße 1<br>
                                12345 Musterstadt
                            </address>
                            <p>
                                <a href="mailto:x.y@nirgendwo.de">x.y@nirgendwo.de</a>
                            <p>
                        </section>
                        <section>
                        <h1 id="Ausbildung">Ausbildung</h1>
                            <table>
                                <thead>
                                    <tr><th>Datum</th><th>Ort</th></tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1990</td><td><details>
                                        <summary>Theodor Gymnasium</summary>
                                        Gegründet von Theodor von und zu Frankenburg im Jahr 1818</details>
                                        </summary></td>
                                    </tr>
                                    <tr>
                                        <td>2000</td><td><a target="_blank" href="https://www.dhbw-mannheim.de">Duale Hochschule Baden-Württemberg Mannheim (<abbr>DHBW</abbr>)</a></td>
                                    </tr>
                                </tbody>
                            </table>
                        </section>
                        <section>
                        <h1 id="Ehrenamt">Ehrenamtliche Tätigkeiten</h1>
                            <ul>
                                <li>DRK</li>
                                <li>Messdiener</li>
                            </ul>
                        </section>
                    </main>
                    <footer>
                    <hr>
                    <span>Seite 1 von 1</span><span>Version: <time datetime="2024">2024</time></span>
                    </footer>
                </body>
                </html>
            ' style="border: 1px solid black;">iframe is not supported</iframe>

.. exercise:: 

  \ 

  .. solution::
    :pwd: HTML5Webseite

    .. code:: html

        <!DOCTYPE html>
        <html lang="de">
        <head>
            <title>Lebenslauf von X Y</title>
            <meta name="author" content="X Y zu W">
            <meta charset="utf-8">
        </head> 
        <body style="font-size:36px"> 
            <header>
            <nav>
               <a href="#Ausbildung">Ausbildung</a> <a href="#ehrenamt">Ehrenamt</a>
            </nav>
            </header>
            <hr>
            <main>
                <section>
                    <strong>Lebenslauf</strong>
                    
                    <address>
                        Musterstraße 1<br>
                        12345 Musterstadt
                    </address>
                    <p>
                        <a href="mailto:x.y@nirgendwo.de">x.y@nirgendwo.de</a>
                    <p>
                </section>
                <section>
                <h1 id="Ausbildung">Ausbildung</h1>
                    <table>
                        <thead>
                            <tr><th>Datum</th><th>Ort</th></tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1990</td><td><details>
                                <summary>Theodor Gymnasium</summary>
                                Gegründet von Theodor von und zu Frankenburg 
                                im Jahr 1818</details>
                                </summary></td>
                            </tr>
                            <tr>
                                <td>2000</td>
                                <td><a target="_blank" 
                                       href="https://www.dhbw-mannheim.de">
                                       Duale Hochschule 
                                       Baden-Württemberg 
                                       Mannheim (<abbr>DHBW</abbr>)
                                </a></td>
                            </tr>
                        </tbody>
                    </table>
                </section>
                <section>
                <h1 id="Ehrenamt">Ehrenamtliche Tätigkeiten</h1>
                    <ul>
                        <li>DRK</li>
                        <li>Messdiener</li>
                    </ul>
                </section>
            </main>
            <hr>
            <footer>
                <span>Seite 1 von 1</span>
                <span>Version: <time datetime="2024">2024</time></span>
            </footer>
        </body>
        </html>


