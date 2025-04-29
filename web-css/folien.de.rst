.. meta:: 
    :lang: de
    :author: Michael Eichberg
    :keywords: "Web Programmierung", "CSS"
    :description lang=de: CSS
    :id: lecture-web-programming-css
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!
    
.. include:: ../docutils.defs



CSS
================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1

.. supplemental::

    :Folien: 
        
        |html-source| 

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues


.. class:: new-section transition-flip

Einführung
------------------------------------------------


CSS - Cascading Style Sheets
------------------------------------------------


CSS (Cascading Style Sheets) ist eine Stylesheet-Sprache, die verwendet wird, um das Aussehen von Dokumenten zu gestalten.

.. container:: two-columns

    .. container:: column no-separator incremental

        .. rubric:: HTML

        .. code:: html
            :class: far-far-smaller

            <section>
                <p>1. Absatz</p>
                <p>2. Absatz</p>   
                <p>3. Absatz</p>
            </section>

    .. container:: column incremental
                
        .. rubric:: CSS und Resultat
    
        .. raw:: html
            :class: css-iframe

            <iframe srcdoc="<html style='font-size:36px'>
                <head><style>
                    style {
                        font-family: monospace; 
                        white-space: pre; 
                        display: block; 
                        background-color: whitesmoke;
                    } 
                    h1 {margin:0; padding:0;} p {margin: 0; padding:0;} </style>
                </head>
                <body>
                    <style spellcheck='false' contenteditable='true'>
            p { color: red; }
            p ~ p { color: blue; }
            p:nth-child(3) { color: green; }
                    </style><hr>
                    <section>
                    <p>1. Absatz</p>
                    <p>2. Absatz</p>   
                    <p>3. Absatz</p>
                    </section>
                </body></html>"
                    width="var(--ld-slide-width)" 
                    height="550"
                    style="border-radius: calc(var(--s-border-radius) * 0.5)" >
                iframes are not supported
            </iframe>


CSS - Historie
----------------

.. class:: incremental

- Entwicklung begann 1994; CSS 1 wurde 1996 veröffentlicht und war erst einmal ein Fehlschlag
- CSS 2 wurde 1998 veröffentlicht 
- CSS 3 wurde modularisiert, um die Entwicklung zu beschleunigen

  .. class:: incremental just-a-bit-smaller

  - CSS Color Level 3 (2012)
  - CSS Namespaces Level 3 (2012)
  - CSS Selectors Level 3 (2012)
  - ...
  - CSS Flexbox Level 1 (2018) (`nach 9 Jahren Entwicklungszeit <https://www.w3.org/standards/history/css-flexbox-1/>`_)
  - CSS Selectors Level 4 (`2024 noch Draft Status <https://www.w3.org/TR/selectors-4/>`__; insbesondere ``:has()`` hat `breite Unterstützung <https://caniuse.com/css-has>`__)
  - CSS Nesting (`2024 noch Draft Status <https://drafts.csswg.org/css-nesting/>`__; `dennoch bereits seit 2024 weit verfügbar <https://caniuse.com/css-nesting>`__)




Grundlagen
------------------------------------------------

Eine CSS-Datei besteht aus Regeln, die aus einem Selektor und einer oder mehreren Deklarationen bestehen:

.. image:: drawings/css.svg
    :width: 1000px
    :align: center
    :alt: Aufbau von CSS-Regeln
    :class: margin-bottom-1em

.. container:: two-columns incremental

    .. container:: column

        .. rubric:: CSS

        .. code:: css
            :class: far-far-smaller

            h1 {
              color: blue;
              font-size: larger;
            }
            body { /* the boss said so... */
              background-color    : 
                lightblue;
            }

    .. container:: column incremental

        .. rubric:: Resultat

        .. raw:: html
            :class: css-iframe 

            <iframe srcdoc="<html style='font-size:32px'><head></head><body>
                            <style> 
            html { font: 32px Helvetica, sans-serif; }
            h1 {
                color: blue;
                font-size: larger;
            }
            body {
                background-color: lightblue;
            }
                            </style>
                        <h1>Überschrift</h1>
                        <p contenteditable='true'>Paragraph<strong> in sehr wichtig!</strong>.</p>
                        </body>"
                    height="410">
                iframes are not supported
            </iframe>

.. supplemental::
    
    CSS ist im wesentlichen *Whitespace insensitive*, d. h. Leerzeichen, Zeilenumbrüche und Tabulatoren werden ignoriert.

    Kommentare werden in ``/* ... */`` geschrieben.


Verknüpfung von CSS und HTML
------------------------------------------------

.. class:: incremental

- Inline CSS: ``<p style="color: red;">``
- Externe CSS-Datei:

  - über Link: ``<link rel="stylesheet" media="screen, print" href="style.css">``
    
    (Normalerweise im ``<head>`` deklariert.) 
  - mittels ``import`` Direktive\ [#]_\ : ``<style>@import url(style2.css);</style>``
- im ``<style>`` Element: ``<style> h1 { color: blue; } </style>``
    
  (Normalerweise im ``<head>`` deklariert.) 
- Das Verwenden beliebig vieler CSS-Dateien und ``style`` Elemente ist möglich.

.. [#] `@import <https://developer.mozilla.org/en-US/docs/Web/CSS/@import>`__


.. class:: new-section transition-fade

Selektoren
------------------------------------------------


Übersicht über Selektoren
------------------------------------------------

.. container:: scrollable smaller

    :*Typ*: Selektoren basierend auf dem Typ des auszuwählenden Elements (z. B. ``h1``, ``div``, ``span``, ...); meistens von HTML Elementen.

    .. class:: incremental

    :*IDs*: Selektoren basierend auf den Werten der (einmaligen) ``id`` Attribute (z. B. ``#core``, ``#impressum``, ...).
    
    .. class:: incremental

    :*Klassen*: Selektoren, die auf den Werten der ``class`` Attribute basieren (z. B. ``.important``, ``.highlight``, ...).
   
    .. class:: incremental

    :*Attributwerte*: Selektoren, die auf einem Attribut bzw. dem Wert eines Attributs als solches basieren (z. B. ``[href]``, ``[type='text']``, ...).

    .. class:: incremental

    :*Pseudoklassen*: Selektoren in Hinblick auf den Zustand eines Elements (z. B. ``:hover``, ``:active``, ...).    

    .. class:: incremental

    :*Pseudoelemente*: Selektoren eines Teils eines Elements (z. B. ``::first-line``, ``::first-letter``, ...).

    .. class:: incremental

    :*Gruppierung*: Gruppierungen von durch Kommas getrennten Selektoren für die die selben Regeln angewandt werden sollen (z. B. ``h1, h2, h3 { ... }``).

    .. class:: incremental

    :*Kombinatoren*: Selektoren, die auf der Beziehung zwischen zwei Elementen basieren (z. B. ``div p { ... }``).



Klassen (:eng:`class-Selector`) - Beispiel\ [#]_
--------------------------------------------------


.. container:: two-columns smaller

    .. container:: column

        *HTML*

        .. code:: html
            :class: far-far-smaller

            <h1>Die Bedeutung des Seins</h1>
            <h1 class="wip">
                Die Bedeutung des Nicht-Seins
            </h1>
            <h1 class="todo future">
                Das Sein und das Nicht-Sein
            </h1>

        *CSS*

        .. code:: css
            :class: far-far-smaller

            h1 { color: black }
            h1.wip { color: green; }
            *.todo { color: red; }
            .future { text-decoration: underline;}

    .. container:: column incremental

        *Resultat*

        .. raw:: html
            :class: css-iframe 

            <iframe srcdoc="<html style='font-size:26px'><head></head><body>
                            <style> 
            h1 { color: black }
            h1.wip { color: green; }
            *.todo { color: red; }
            .future { text-decoration: underline;}
                            </style>
                                   <h1>Die Bedeutung des Seins</h1>
                <h1 class='wip'>Die Bedeutung des Nicht-Seins</h1>
                <h1 class='todo future'>Das Sein und das Nicht-Sein</h1>
                        </body>"
                    height="300">
                iframes are not supported.
            </iframe>


.. [#] ID basierte Selektoren funktionieren vergleichbar, jedoch wird ein ``#`` anstatt eines ``.`` verwendet. (In CSS müssen IDs nicht eindeutig sein; dies ist aber eine Verletzung von HTML und eindeutige IDs sind eine *Best Practices*.) 



Attribute (:eng:`Attribute-Selector`) \ [#]_
--------------------------------------------------------

.. class:: incremental

- basierend auf der Existenz eines Attributs: ``h1[lang] { color: red; }``
- basierend auf dem *exakten* Wert eines Attributs: ``h1[lang="de-DE"] { color: red; }``
- basierend auf einem partiellen Match: 

  - enthält als eigenständiges ``de``: ``h1[lang~="de"] { color: red; }``
  - beginnt mit ``de``: ``h1[lang^="de"] { color: red; }``
  - substring ``de``: ``h1[lang*="de"] { color: red; }``
  - endet mit ``de`` : ``h1[lang$="de"] { color: red; }``
  - beginnt mit ``de`` und wird dann gefolgt von einem Bindestrich oder steht alleine: ``h1[lang|="de"] { color: red; }``
- durch ein i am Ende wird der **Selektor für den Wert** *case-insensitive*: ``h1[lang="de-de" i] { color: red; }``
  
.. [#] Im Allgemeinen sind Attribut-basierte Selektoren vergleichsweise fragil und werden deswegen nur spärlich eingesetzt. Im Zusammenhang mit ``data-*`` Attributen ist dies jedoch eine sehr mächtige Technik.



Attribute (:eng:`Attribute-Selector`) - Beispiel
--------------------------------------------------


.. container:: two-columns smaller

    .. container:: column

        *HTML*

        .. code:: html
            :class: far-far-smaller

            <h1 lang="de-DE">Die Bedeutung des Seins.</h1>
            <h1 lang="en-GB">To Be Or Not To Be</h1>
            <h1 lang="en-US">Play to win!</h1>
            <h1 lang="de-AT">Ich brauch ne Jause</h1>

        *CSS*

        .. code:: css
            :class: far-far-smaller

            [lang] { text-decoration: underline; }            
            [lang$='US'] { color: orange; }
            [lang|='en'] { font-style: italic; }
            [lang="de-at" i] { text-transform: uppercase; }

    .. container:: column incremental

        *Resultat*

        .. raw:: html
            :class: css-iframe 

            <iframe srcdoc="<html style='font-size:26px'><head></head><body>
                            <style> 
            [lang] { text-decoration: underline; }            
            [lang$='US'] { color: orange; }
            [lang|='en'] { font-style: italic; }
            [lang='de-at' i] { text-transform: uppercase; }
                            </style>
            <h1 lang='de-DE'>Die Bedeutung des Seins.</h1>
            <h1 lang='en-GB'>To Be Or Not To Be</h1>
            <h1 lang='en-US'>Play to win!</h1>
            <h1 lang='de-AT'>Ich brauch ne Jause</h1>
                        </body>"
                    height="400">
                iframes are not supported.
            </iframe>


Kombinatoren
--------------------------------------------------------------------

.. container:: scrollable

    .. class:: incremental

    - Nachfahren (bzgl. Dokumentenstruktur) (:eng:`Descendant Selector`):
    
      :``div p``: alle ``<p>`` Nachfahren von ``<div>`` Elementen

      :``.important[lang='de-de' i] p``: alle ``<p>`` Nachfahren von ``.important`` Elementen, die ein ``lang`` Attribut mit dem Wert ``de-DE`` haben.

    - Alle direkten Kinder (:eng:`Child Selector`):

      :``div > p``: alle ``<p>`` Kinder von ``<div>`` Elementen.
    - Benachbarte Geschwister (:eng:`Adjacent Sibling Selector`):

      :``h1 + p``: alle ``<p>`` Elemente, die *direkt* auf ein ``<h1>`` Element folgen und sich gleiche Eltern-Element teilen.

    - Allgemeiner Geschwister Selektor (:eng:`General Sibling Selector`):

      :``h1 ~ p``: alle ``<p>`` Elemente, die auf ein ``<h1>`` Element folgen und sich das Gleiche Eltern-Element teilen.    


Kombinatoren - Beispiele
--------------------------------------------------------------------


.. container:: two-columns far-smaller

    .. container:: column

        *HTML*

        .. code:: html
            :class: far-smaller

            <h1>Ü1</h1>            
            Text
            <p>P1</p>
            <p>P2</p>
            <div>D0</div>
            <p>P3</p>

            <h1>Ü2</h1>
            <div>
                D1
                <div>D1.1</div>
                <div>D1.2</div>
            </div>
            <div>D2</div>
            <div>D3</div>


    .. container:: column incremental

        *Spielwiese*

        .. raw:: html
            :class: one-column-iframe with-editable-content

            <iframe srcdoc="<html style='font-size:36px'><head><style>style {
                font-family: monospace; white-space: pre; display: block; background-color: whitesmoke;
            } h1 {margin:0; padding:0;} p {margin: 0; padding:0;} </style></head><body>
                            <style spellcheck='false' contenteditable='true'>/* h1 + p { color: blue; } */   
            /* p + p { color: red; } */   
            /* h1 ~ p { color: green; } */
            /* div ~ div { color: yellow } */
            /* div + div { color: purple; } */ 
            /* h1 ~ div { color: orange; } */
                            </style><hr>
                        <h1>Ü1</h1>
                        Text
                        <p>P1</p>
                        <p>P2</p>
                        <div>D0</div>
                        <p>P3</p>
                        <h1>Ü2</h1>
                        <div>D1
                            <div>D1.1</div>
                            <div>D1.2</div>
                        </div>
                        <div>D2</div>
                        <div>D3</div>
                        </body>"
                    width="var(--ld-slide-width)" 
                    height="930"
                    style="border-radius: calc(var(--s-border-radius) * 0.5)" >
                iframes are not supported
            </iframe>



*Pseudo-class Selektors*
------------------------------------------------


.. class:: incremental 
    
- erlauben das Selektieren von Elementen basierend auf ihrem Zustand
- können beliebig kombiniert werden: ``a:link:hover { color: red; }`` selektiert alle nicht-besuchten Links über denen sich die Maus befindet
- Ausgewählte Beispiele: 

    .. class:: incremental smaller

    - Bzgl. der Struktur: ``:first-child``, ``:last-child``, ``:nth-child(n)``, ``:nth-of-type(n)``, ``:root``, ``:only-child``, ``:only-of-type``, ``:link``, ``:visited``

    - Basierend auf Nutzerinteraktionen: ``:hover``, ``:active``, ``:focus``
    - Zustand des Elements: ``:enabled``, ``:disabled``, ``:checked``, ``:valid``, ``:invalid``
    - Sprache und Lokalisierung: ``:lang(de)``, ``:dir(ltr)``
    - Logische Selektoren: ``:not(selector)``, ``:is(selector)``, ``:where(selector)``, ``:has(selector)``

- Pseudo-class Selektoren beziehen sich immer auf das aktuelle Element.

.. supplemental::
    
    - Bei ``nth-child(n)`` und ``nth-of-type(n)`` ist n eine Zahl oder ein Ausdruck (:math:`\alpha\,n+b`), der eine Zahl ergibt (z. B. ``2n+1`` oder aber ``even``). Das Zählen der Elemente beginnt bei 1.
    - ``:root`` selektiert das Wurzelelement des Dokuments, also das ``<html>`` Element.
    - ``:only-child`` und ``:only-of-type`` selektiert ein Element, das das einzige entsprechende Kind seines Eltern-Elements ist.
  


*Pseudo-class Selektors* - Beispiel
------------------------------------------------

.. container:: two-columns

    .. container:: column

        **HTML**

        .. code:: html
            :class: far-far-smaller copy-to-clipboard

            <div class="oma" id="Maria">
                <div class="papa" id="Fritz">
                    Vater 1
                    <div class="kind" id="Elias">
                        Kind 1
                    </div>
                </div>
                <div class="papa" id="Hans">
                    Vater 2
                    <div class="kind" id="Tobias">
                        Kind 2
                    </div>
                </div>
            </div>

    .. container:: column

        **CSS**

        .. code:: css
            :class: far-far-smaller copy-to-clipboard

            .papa:first-child { color: red; }
            .kind:first-child { color: green; }
        
        Selektiert welches Element?

        .. container:: far-far-smaller
        
            Zur Erinnerung: 
            Pseudo-class Selektoren beziehen sich immer auf das Element, auf das sie sich beziehen.

        .. raw:: html
            :class: one-column-iframe incremental margin-top-1em

            <iframe srcdoc="<html style='font-size:36px'>
                <head>
                <style>
                h1 {margin:0; padding:0;} p {margin: 0; padding:0;} 
                </style>
                </head>
                <body>
                    <style>.papa:first-child { color: red; } .kind:first-child { color: green; }</style>
                    <div class=,oma' id='Maria'>
                        <div class='papa' id='Fritz'>
                            Vater 1
                            <div class='kind' id='Elias'>
                                Kind 1
                            </div>
                        </div>
                        <div class='papa' id='Hans'>
                            Vater 2
                            <div class='kind' id='Tobias'>
                                Kind 2
                            </div>
                        </div>
                    </div>"                        
                    height="100"
                    style="border-radius: calc(var(--s-border-radius) * 0.5)" >
                iframes are not supported
            </iframe>

.. incremental:: smaller

    Selektiert wird ein Element mit der Klasse ``papa``, wenn es das erste Kind seines Eltern-Elements ist. Es wird *nicht das erste Kind des Elements selektiert*.





*Pseudo-class Selektors* bzgl. Inputvalidierung
--------------------------------------------------------------------


.. container:: two-columns smaller

    .. container:: column

        *HTML*

        .. code:: html
            :class: far-far-smaller

            <input type="email" 
                   placeholder="your email"  
                   required>
            <input type="email" 
                   placeholder="your friend's email">


    .. container:: column incremental

        *Spielwiese*

        .. raw:: html
            :class: one-column-iframe with-editable-content

            <iframe srcdoc="<html style='font-size:32px'><head><style>style {
                font-family: monospace; white-space: pre; display: block; background-color: whitesmoke;
            } h1 {margin:0; padding:0;} p {margin: 0; padding:0;} input {font-size: 30px; padding: 0.5em; display: block; margin: 0.5em} </style></head><body>
                            <style spellcheck='false' contenteditable='true'> 
            input[type='email']:valid { 
                color: green; 
                border: 2px solid green; 
            }
            /*input[type='email']:invalid { 
                color: red; 
                border: 2px solid red; 
            }*/
                            </style><hr>
                        <input type='email' placeholder='your email'  required>
                        <input type='email' placeholder='your friend`s email'>
                        </body>"
                    width="var(--ld-slide-width)" 
                    height="900"
                    style="border-radius: calc(var(--s-border-radius) * 0.5)" >
                iframes are not supported
            </iframe>

.. supplemental::

  Da das zweite Eingabefeld nicht als ``required`` markiert ist, wird es auch dann als ``:valid`` betrachtet, wenn es leer ist.


Spezifität von Selektoren
-----------------------------

.. stack::


    .. layer::

      - Die Spezifität eines Selektors bestimmt, welcher Stil auf ein Element angewendet wird, wenn mehrere Regeln auf ein Element zutreffen und diese bzgl. der gleichen Eigenschaften in Konflikt stehen.
        
        Die Spezifität wird durch einen Vektor ``(a, b, c)`` dargestellt:

        - ``a``: Anzahl der ID Selektoren
        - ``b``: Anzahl der Klassen-, Attribut- und Pseudo-Klassen Selektoren
        - ``c``: Anzahl der Element- und Pseudo-Element Selektoren

        Die Spezifität wird in der Reihenfolge ``a``, ``b``, ``c`` verglichen.

      .. class:: incremental

      - Konzeptionell wird die Spezifität pro Deklaration betrachtet.

    .. layer:: incremental

      - Beispiele:
        
        .. csv-table::
            :header: "Selektor", "Spezifität"
            :class: incremental no-table-borders
            :width: 100%

            p { color: black; }, "0, 0, 1"
            section p { color: orange; }, "0, 0, 2"
            section > p { color: orange; }, "0, 0, 2"
            p.warning { color: red; }, "0, 1, 1"
            p[id*='this'] {color: green; }, "0, 1, 1"
            #main { color: yellow; }, "1, 0, 0"
            \* { color: yellow !important; }, "0, 0, 0 (Important)"

    .. layer:: incremental

        .. container:: two-columns smaller

            .. container:: column

                *HTML*

                .. code:: html
                    :class: far-smaller

                    <section>
                        <p id='this-is-it'>
                            Der erste Abschnitt!
                        </p>
                        <p class='obsolete'>
                            Ein alter Abschnitt.
                        </p>
                    </section>
                    <p>Der letzte Abschnitt.</p>

            .. container:: column incremental

                *Spielwiese*

                .. raw:: html
                    :class: one-column-iframe with-editable-content

                    <iframe srcdoc="<html style='font-size:36px'><head><style>style {
                        font-family: monospace; white-space: pre; display: block; background-color: whitesmoke;
                    } h1 {margin:0; padding:0;} p {margin: 0; padding:0;} input {font-size: 30px; padding: 0.5em; display: block; margin: 0.5em} </style></head><body>
                                    <style spellcheck='false' contenteditable='true'>/*p[id*='this'] {color: green; }*/
                    /*section p { color: red; }*/
                    /*p { color: orange; }*/
                    /*p ~ p { color: aliceblue; }*/
                                    </style><hr>
                    <section>
                        <p id='this-is-it'>Der erste Abschnitt!</p>
                        <p class='obsolete'>Ein alter Abschnitt.</p>
                    </section>
                    <p>Der letzte Abschnitt.</p>
                                </body>"
                            height="600px"
                            style="border-radius: calc(var(--s-border-radius) * 0.5); width: 100% !important;" >
                        iframes are not supported
                    </iframe>



.. supplemental::

    - Kombinatoren haben keine Spezifität.
    - ``*`` hat die Spezifität (0,0,0)
    - eine Deklaration mit ``!important`` hat eine höhere Spezifität alls jede Deklaration ohne ``!important``. Alle als ``!important`` markierten Deklarationen werden nach den beschriebenen Regeln ausgewertet.



Elemente
------------------------------------------------

- Wir unterscheiden zwischen *replaced elements* bei denen der Inhalt nicht Teil des Dokumentes ist (zum Beispiel ``<img>``) und *non-replaced elements* (zum Beispiel ``<p>`` und ``<div>``; d. h. die meisten HTML Elemente).

.. class:: incremental

- Grundlegende Formatierungskontexte\ [#]_\ : *block* (z. B. der Standard von ``h1``, ``p``, ``div``, ...) und *inline* (z. B. der Standard von ``strong``, ``span``,...).

  .. class:: list-with-explanations

  - Block-Elemente generieren eine Box, welche den Inhaltsbereich des *Parent-Elements* ausfüllt. 

    (*Replaced elements* können, müssen aber nicht Block-Elemente sein.)
  - Inline-Elemente generieren eine Box innerhalb einer Zeile und unterbrechen den Fluss der Zeile nicht.
  - Mittels CSS kann der Formatierungskontext geändert werden.

  .. [#] Es gibt noch „viel mehr“ Kontexte für spezielle Anwendungsfälle.


Block und Inline Elemente - Beispiel
------------------------------------------------

.. container:: two-columns incremental

    .. container:: column

        .. rubric:: Code

        .. code:: css
            :class: far-far-smaller

            h1 {
                display: inline;
            }
            strong { 
                display: block;
            }

        Folgendes Beispiel dient nur der Veranschaulichung:

        .. code:: html
            :class: far-far-smaller

            Dies ist eine <strong><h1>Überschrift</h1> 
            in sehr wichtig</strong>; wirklich!

    .. container:: column incremental

        .. rubric:: Visualisierung

        .. raw:: html
            :class: css-iframe 

            <iframe srcdoc="<html style='font-size:32px'><head></head><body>
                            <style> 
             h1 {
                display: inline;
            }
            strong { 
                display: block;
            }
                            </style>
                        Dies ist eine <strong><h1>Überschrift</h1> in sehr wichtig</strong>; wirklich!
                        </body>"
                    height="410">
                iframes are not supported.
            </iframe>


.. admonition:: Warnung
    :class: warning far-smaller incremental

    Dies ist kein gültiges HTML5!




Vererbung   
------------------------------------------------

- die meisten Eigenschaften (wie zum Beispiel ``color``) werden vererbt

.. class:: incremental list-with-explanations

- Eigenschaften, die nicht vererbt werden sind zum Beispiel: ``border``, ``margin``, ``padding`` und ``background`` 
- vererbte Eigenschaften haben **keine Spezifität** 

  (D. h. ein :where() Selektor oder der Universal-Selektor ``*`` gewinnen.)


Kaskadierung
------------------------------------------------
Die Entscheidung welche Regeln bzw. Deklarationen Anwendung finden, wird durch die Kaskadierung bestimmt:

.. class:: incremental

1. Bestimme alle Regeln, die auf ein Element zutreffen.
2. Sortiere die Regeln nach Gewicht des Selektors (d.h. ``!important`` oder *normal*)
3. Sortiere alle Deklarationen basierend auf der Quelle: 

   - Autor (höchste Priorität)
   - Benutzer (mittlere Priorität; z. B. *User-Stylesheets*)
   - *User Agent* (niedrigste Priorität; z. B. Browser Standard Styles)
4. Sortiere nach *Encapsulation Context* (cf. Shadow-DOM)
5. Sortiere danach ob die Deklarationen *Element Attached* sind (d. h. mittels ``style`` Attribut)
6. Sortiere nach *Cascade Layer*
7. Sortiere nach Spezifität
8. Sortiere nach Reihenfolge der Deklarationen

.. supplemental::

    Der Shadow-Dom kapselt CSS und JavaScript bgzl. eines Elements. Dies ist insbesondere für Web-Komponenten relevant.


.. class:: no-title transition-fade center-child-elements

CSS - Trick - nicht-unterstützte Eigenschaften
------------------------------------------------

.. container:: trick

    Sollte eine Deklaration möglicherweise nicht unterstützt werden, es jedoch einen vernünftigen Fallback geben, dann ist es möglich, die Deklarationen untereinander zu schreiben. Der Browser wird die unterstützte Deklaration verwenden und die anderen ignorieren.

    .. incremental::
    
        Beispiel:

        .. code:: css
            :class: smaller

            div {
                height: 100vh;
                height: 100svh;
            }



``:not()`` - Beispiel
--------------------------------------------------------------------

.. container:: two-columns smaller

    .. container:: column

        *HTML*

        .. code:: html
            :class: far-smaller

            <hr>
            <p class="new">
                Neuer Absatz
            </p>
            <p class="new">
                Noch ein neuer Absatz
            </p>   
            <p>Alter text.</p>


    .. container:: column incremental

        *Spielwiese*

        .. raw:: html
            :class: one-column-iframe with-editable-content

            <iframe srcdoc="<html style='font-size:36px'><head><style>style {
                font-family: monospace; white-space: pre; display: block; background-color: whitesmoke;
            } h1 {margin:0; padding:0;} p {margin: 0; padding:0;} </style></head><body>
                            <style spellcheck='false' contenteditable='true'>p:not(.new) {
                text-decoration: line-through;
            }
            /*hr ~ *:not([class]) {
                font-size: smaller;
                color: red;
            }*/
                            </style><hr>
            <p class='new'>Neuer Absatz</p>
            <p class='new'>Noch ein neuer Absatz</p>   
            <p>Alter text.</p>
                        </body>"
                    width="var(--ld-slide-width)" 
                    height="550"
                    style="border-radius: calc(var(--s-border-radius) * 0.5)" >
                iframes are not supported
            </iframe>

.. incremental:: smaller margin-top-1em

    - ``:not(<list of selectors>)`` erlaubt die logische Und-Verknüpfung: 
    
      ``:not(<selector_a>, <selector_b>)`` ≘ ``nicht selector_a und nicht selector_b``.
    - die Spezifität ergibt sich aus der Spezifität des spezifischsten Selektors


``:is()`` und ``:where()`` - Beispiel
--------------------------------------------------------------------

Erlauben das Gruppieren von Selektoren innerhalb eines (komplexen) Selektors.

.. container:: two-columns smaller

    .. container:: column

        *HTML*

        .. code:: html
            :class: far-smaller

            <hr>
            <ol>
                <li>Aufgezählt</li>
            </ol>
            <ul>
                <li>Ein Punkt</li>
            </ul>


    .. container:: column incremental

        *Spielwiese*

        .. raw:: html
            :class: one-column-iframe with-editable-content

            <iframe srcdoc="<html style='font-size:36px'><head><style>style {
                font-family: monospace; white-space: pre; display: block; background-color: whitesmoke;
            } h1 {margin:0; padding:0;} p {margin: 0; padding:0;} input {font-size: 30px; padding: 0.5em; display: block; margin: 0.5em} </style></head><body>
                            <style spellcheck='false' contenteditable='true'>:is(ol, ul) li { 
                font-style: italic; 
            }
            /* :where(ol, ul) li {
                font-weight: bold;
                font-style: normal;
            }*/
                            </style>
            <hr>
            <ol>
                <li>Aufgezählt</li>
            </ol>
            <ul>
                <li>Ein Punkt</li>
            </ul>
                        </body>"
                    width="var(--ld-slide-width)" 
                    height="600"
                    style="border-radius: calc(var(--s-border-radius) * 0.5)" >
                iframes are not supported
            </iframe>


.. incremental:: smaller margin-top-1em

    - ``:is()`` und ``:where()`` unterscheiden sich nur in der Spezifität. Die Spezifität ist bei ``:where()`` immer 0 und bei ``:is()`` gleich der die Spezifität des spezifischsten Selektors.

    


``:has()`` - Beispiel
--------------------------------------------------------------------

.. container:: two-columns smaller

    .. container:: column

        *HTML*

        .. code:: html
            :class: far-smaller

            <ol>
                <li class="important">Aufgezählt</li>
                <li>Aufgezählt</li>
            </ol>
            <ul>
                <li>Ein 
                    <span class='important'>Punkt</span>
                </li>
                <li>Semikolon</li>
            </ul>


    .. container:: column incremental

        *Spielwiese*

        .. raw:: html
            :class: one-column-iframe with-editable-content

            <iframe srcdoc="<html style='font-size:36px'><head><style>style {
                font-family: monospace; white-space: pre; display: block; background-color: whitesmoke;
            } h1 {margin:0; padding:0;} p {margin: 0; padding:0;} input {font-size: 30px; padding: 0.5em; display: block; margin: 0.5em} </style></head><body>
                            <style spellcheck='false' contenteditable='true'>:is(ol, ul):has(>.important) li { 
                font-style: italic; 
                color: red;
            }
                            </style>
            <ol>
                <li class='important'>Aufgezählt</li>
                <li>Aufgezählt</li>
            </ol>
            <ul>
                <li>Ein 
                    <span class='important'>Punkt</span>
                </li>
                <li>Semikolon</li>
            </ul>
                        </body>"
                    width="var(--ld-slide-width)" 
                    height="500"
                    style="border-radius: calc(var(--s-border-radius) * 0.5)" >
                iframes are not supported
            </iframe>


.. incremental:: smaller margin-top-1em

   - bei ``:has()`` werden die Selektoren relativ zum Element ausgewählt, welche den Anker für ``:has()`` bilden

   - ``:has(<list of selectors>)`` verknüpft die Selektoren mittels logischem Oder.
    
     ``:has(<selector_a>, <selector_b>)`` ≘ ``selector_a oder selector_b passt``.

   - die Spezifität ergibt sich aus der Spezifität des spezifischsten Selektors


.. supplemental::

    Mittels ``:has`` können wir (hier) eine Liste als ganzes selektieren, wenn ein Element in der Liste eine bestimmte Klasse hat (z. B. ``important``).


.. class:: no-title transition-fade center-child-elements

JavaScript und CSS Selektoren
------------------------------------------------

CSS Selektoren werden auch von der JavaScript API für HTML Dokumente verwendet, um Elemente zu selektieren.


Nesting
------------------------------------------------


.. container:: two-columns smaller

    .. container:: column

        *HTML*

        .. code:: html
            :class: far-far-smaller
            
            <h1 class="obsolete">1. Überschrift</h1>
                <p>Ein alter Absatz</p>
            <h2>2. Überschrift</h2>
                <p>Ein neuer, besserer Absatz</p>


    .. container:: column incremental

        *Spielwiese*

        .. raw:: html
            :class: one-column-iframe with-editable-content

            <iframe srcdoc="<html style='font-size:32px'><head><style>style {
                font-family: monospace; white-space: pre; display: block; background-color: whitesmoke;
            } h1 {margin:0; padding:0;} p {margin: 0; padding:0;} input {font-size: 30px; padding: 0.5em; display: block; margin: 0.5em} </style></head><body>
                            <style spellcheck='false' contenteditable='true'> 
            h1.obsolete { 
                color: red;
                text-decoration: line-through;
                background-color: lightgray;

                & + p {
                    color: green;
                }
            }
                            </style><hr>
                                <h1 class='obsolete'>1. Überschrift</h1>
                                    <p>Ein alter Absatz</p>
                                <h2>2. Überschrift</h2>
                                    <p>Ein neuer, besserer Absatz</p>
                        </body>"
                    width="var(--ld-slide-width)" 
                    height="900"
                    style="border-radius: calc(var(--s-border-radius) * 0.5)" >
                iframes are not supported
            </iframe>

.. supplemental::

    CSS Nesting ist erst seit 2024 in CSS verfügbar. Nesting findet bzgl. der Selektoren statt.  Häufig(er) in Kombination mit *At-Regeln* (:eng:`at-rules`; z. B. :code:`@media`) verwendet.


Nesting - ``&`` Operator
------------------------------------------------

Der ``&`` Operator kann immer verwendet werden, ist aber oft optional.

.. container:: two-columns smaller

    .. container:: column

        .. code:: css
            :class: smaller copy-to-clipboard

            p  {
                .obsolete {
                    text-decoration: line-through;
                } 
            }

        ist äquivalent zu:

        .. code:: css
            :class: smaller copy-to-clipboard

            p .obsolete {
                text-decoration: line-through;
            }

    .. container:: column incremental   

        .. code:: css
            :class: smaller copy-to-clipboard

            p  {
                &.obsolete {
                    text-decoration: line-through;
                } 
            }

        ist äquivalent zu:

        .. code:: css
            :class: smaller copy-to-clipboard

            p.obsolete {
                text-decoration: line-through;
            }

.. supplemental::

    D. h. sollten nur solche Paragraphen durchgestrichen werden, die als *obsolete* markiert sind (d. h. ``<p class='obsolete'>``) und nicht alle darunter liegenden Elemente, dann muss der ``&`` Operator verwendet werden (``&`` ist dann nicht optional).



.. class:: integrated-exercise

Übung - Einbinden von CSS in HTML
------------------------------------------------

.. container:: scrollable smaller

    Gegen sei die folgende (unformatierte) Webseite:

    .. image:: code/1st-exercise/screenshot-ausgangssituation.png
        :width: 1024px
        :align: center
        :class: box-shadow rounded-corners

    .. container:: incremental

        :Code (HTML): `exercise-template.html <code/1st-exercise/exercise-template.html>`__ 
        :Hintergrundbild: `image.png <code/1st-exercise/image.png>`__ 

    .. container:: incremental
                    
        1. Binden Sie den CSS Code (siehe Anhang) ein, um grundlegend das folgende Layout zu erhalten:

           .. image:: code/1st-exercise/screenshot-final.png 
              :width: 1024px
              :align: center 
              :class: box-shadow rounded-corners  
        
    .. container:: incremental

        2. Erweitern Sie den CSS Code, um das finale Layout zu erhalten. Dazu müssen sie die folgenden CSS Eigenschaften passend „einfügen“.
   
           .. code:: css
             :class: far-far-smaller copy-to-clipboard
            
             text-align: center;
             text-align: right;
            
             font-family: sans-serif;
             font-size: smaller;
             font-size: 0.5em;
             font-size: 25px;
             text-shadow: 2px 2px 4px white;            
            
             color: #999; /* defines the font color */
             color: #ccc;

             background-color: rgba(0, 0, 0, 0.3);
             background-color: rgba(0, 0, 0, 0.6);
             background-color: rgba(255, 255, 255, 0.4);

             /* Corners: top-left; top-right; bottom-right; bottom-left */
             border-radius: 0.5em 0.5em 0 0; 
             border-radius: 0 0 0.5em 0.5em ;

.. exercise:: Einbinden von CSS
    
    .. solution::
        :pwd: DasWarNICHTsoSchwer

        Die Lösung ist hier zu finden: `exercise-solution.html <code/1st-exercise/exercise-solution.html>`__

.. supplemental::

    Grundlegender CSS Code

    .. code:: css
        :class: copy-to-clipboard far-far-smaller

        :root {
            background-size: cover;
            background-image: url('image.png');
        }
        body {
            max-width: 60ch;
            padding: 0;
            margin: 0;
            margin-right:auto;
            margin-left:auto;
        }
        h1 {
            padding:0.5rem;
            margin-bottom: 0;
            backdrop-filter: blur(5px);
            -webkit-backdrop-filter: blur(10px);
            
        }
        p {
            position: relative;
            margin-top:0;
            margin-bottom:0;
            padding: 0.5rem;            
            font-weight: 100;
            text-wrap: pretty;
            -webkit-backdrop-filter: blur(10px);
            backdrop-filter: blur(10px);
        }
        cite {
            display: block;
            padding: 0.5rem;
        }
        footer {
            position: fixed;
            bottom: 0;
            left: 0;
            right: 0;
            padding: 0.5rem;
        }


.. class:: integrated-exercise

Übung - CSS Selektoren
------------------------------------------------

.. container:: two-columns smaller margin-bottom-2em

    .. container:: column

        Gegeben sei folgendes HTML Dokument:

        .. code:: html
            :class: copy-to-clipboard far-far-smaller

            <body>
                <h1>Country Information</h1>
                <ul>
                    <li>Germany
                        <ul>
                            <li>Berlin</li>
                            <li>Hamburg</li>
                            <li>Munich</li>
                        </ul>
                    </li>
                    <li>France</li>
                    <li>Spain</li>
                    <li>Sweden</li>
                    <li>Finland</li>
                    <li>Norway</li>
                    <li>Italy</li>
                    <li>Albania</li>
                    <li>Portugal</li>
                </ul>
            </body>

    .. container:: column incremental

        Schreiben Sie CSS Code, um folgende Formatierung zu erreichen:

        .. raw:: html
            :class: margin-top-1em margin-bottom-1em

            <div style="margin-left: auto; margin-right:auto; width: fit-content">
                <video 
                    width="800px" 
                    height="568px"
                    controlslist="nofullscreen nodownload"
                    controls
                    playsinline
                    autoplay 
                    loop
                    muted
                    preload="metadata"
                    style="box-shadow: var(--trbl-shadow);">
                    <source src="code/2nd-exercise/index.mov" type="video/mp4">
                    Your browser does not support the video tag.
                </video>
            </div>

        .. container::  far-smaller

            Sie benötigten folgende Selektoren:

            ``:nth-child(2n-1)``, ``:hover``, ``+``, ``:has``, ``h1``, ``ul``, ``li``

            Verwenden Sie CSS Nesting, wenn möglich.

.. exercise:: CSS Selektoren

    .. solution::
        :pwd: SelektorenGanzEinfach

        Die Lösung ist hier zu finden: `exercise-solution.html <code/2nd-exercise/index.html>`__



.. class:: new-section transition-fade

Werte und Einheiten
------------------------------------------------


Grundlagen
------------------------------------------------

.. container:: scrollable
        
    .. class:: incremental 

    - Einige Eigenschaften haben Schlüsselworte, die spezielle Werte repräsentieren (z. B. ``none`` bei ``text-decoration``)
    - Das gleiche Schlüsselwort kann verschiedene Bedeutungen haben (z. B. ``normal`` bei ``letter-spacing`` und ``font-style``)
    - Es gibt fünf globale Schlüsselworte, die immer verwendet werden können: ``inherit``, ``initial``, ``unset``, ``revert``, und ``revert-layer``.
    - Strings können in ``'`` oder ``"`` eingeschlossen werden
    - Identifikatoren (z. B. ``checked``)
    - URLs werden mittels ``url(...)`` angegeben
    - Ganzzahlen, Fließkommazahlen und Prozente
    - Ausgewählte Distanzen:
    
      .. container:: smaller

        - Absolute Längen: ``cm``, ``mm``, ``in``, ``pt``, ``pc``, ``px``
        - Relative Längen: 
        
          - Charakter bezogene Längen: ``em``, ``ex``, ``lh``, ``ch`` 
          - Root bezogene Längen: ``rem`` (*root-em*)
          - Relation: ``fr`` (Anteil vom Leerraum)
        - Viewport bezogene Längen: ``vw`` (viewport width), ``vh`` (viewport height), ``dvh`` (dynamic viewport height), ``dvw`` (dynamic viewport width), ``svh`` (small viewport height), ``svw`` (small viewport width)
    - Funktionswerte: ``calc()``, ``min()``, ``max()``, ``clamp(<min_value>,<preferred_value>,<max_value>)``, ``attr`` und über 90 weitere Funktionen
    - Farben werden spezifiziert mittels Schlüsselworte: (``red``, ``green``, etc.), RGB-Werte: ``rgb(<red>,<green>,<blue>)`` oder ``rgb(<red> <green> <blue> [/ <alpha>])``; oder ...
    - Zeitangaben: ``s`` und ``ms``
    - Verhältnisse: ``<number> / <number>`` (z. B. ``16/9``)
    - Benutzerdefinierte Eigenschaften (*CSS Variables*): 

      Beispiel: 
        
      1. Deklaration

         ``html { --main-color: red;}``
         
         (Häufig ``:root {...}`` statt ``html``.)

      2. Verwendung inkl. Fallback-Wert:

         ``p {color: var(--main-color, black)}``

      Der Scope ergibt sich aus dem Element, in dem die Variable definiert wurde. 
      
      .. container:: warning
        
        Bei Verwendung findet einfaches (textuelles) Ersetzen statt.


.. supplemental::

    ``px`` ist ein Pixel ist die Größe, die man benötigt, wenn man 96 Pixel pro Zoll hat; ``px`` ist die Einzige absolute Längeneinheit, die von Webseiten typischerweise verwendet wird. Ein Pixel ist somit unabhängig von der Größe eines Pixels auf dem Bildschirm!

    ``em`` der Wert der Font-Größe des aktuellen Fonts.

    ``ex`` ist die größe eines kleinen x im aktuellen Font

    ``lh`` computed line-height

    ``ch`` Breite des Zeichens „0“ (ZERO, U+0030) (Ein Wert von 60ch entspricht bei vielen Fonts einer effektiven Breite von ca. 80 Zeichen im Durchschnitt.)

    ``calc`` erlaubt verschiedenste Berechnungen ist aber an einigen Stellen *Whitespace-sensitive* und unterliegt bestimmten Einschränkungen welche Arten von Werten verrechnet werden können. (+ und - müssen immer mit Leerraum umgeben sein.)


 
CSS - Berechnung von Werten
------------------------------------------------

Der Wert einer CSS Eigenschaft wird wie folgt bestimmt:

.. class:: incremental list-with-explanations

1. der spezifizierte Wert wird basierend auf der Auswertung der Kaskadierung bestimmt
2. der berechnete Wert (:eng:`computed value`) wird bestimmt basierend auf der CSS Spezifikation
   
   (Dieser Wert lässt sich mittels JavaScript abfragen.)
3. der verwendete Wert (:eng:`used value`) wird bestimmt basierend auf dem berechneten Wert und den Eigenschaften des Ausgabemediums

   (Größen sind zum Beispiel in Pixel.)
4. der tatsächliche Wert (:eng:`actual value`) wird bestimmt basierend auf dem verwendeten Wert (z. B. durch Rundung auf ganze Zahlen)


.. class:: new-section transition-fade

Grundlegende Formatierung
------------------------------------------------


Box-Modell - Einführung
------------------------------------------------

- jedes Element erzeugt eine Box (*Element Box*): 

  - entweder eine *Block Box* 
  - oder eine *Inline Box*

.. container:: incremental scrollable

  - Es ist möglich den Typ der Box zu ändern. 
  - Es ist möglich die Größe der Box zu ändern.
  
    .. class:: incremental list-with-explanations

    - Basierend auf der Größe des Inhalts: max-content, min-content, fit-content 

      (Insbesondere - aber nicht ausschließlich - genutzt bei Grid-Layouts.)
    - Explizite Angabe der Größe: ``width``, ``height``, ``min-width``, ``max-width``, ``min-height``, ``max-height`` 
  
      - absolute Werte: insbesondere ``px``
      - relative Werte: ``width: x%`` setzt die Breite auf ``x%`` der Größe des *Containing Block*. ``height: y%`` setzt die Höhe auf ``y%`` der Größe des *Containing Block* - wenn dieser eine explizite Höhe hat!
      - ``auto`` ist der Standardwert 
    - Die Größe wird bei *Inline-Replaced Elements* ignoriert.

      .. container : : hint
        ``content`` ist die einzige Eigenschaft, die nicht verändert werden kann.

  - Die Größe der Box berechnet sich „nur“ aus der Größe des Inhalts (d. h. der ``content`` Bereich); dies kann geändert werden durch: ``box-sizing: border-box;``

      ``box-sizing: border-box;`` setzt die Größe der Box auf die Größe des Inhalts plus Padding und Border. (Der Standardwert ist ``content-box``.)




Darstellung des Box-Modells
------------------------------------------------

Im Zentrum ist der Content-Bereich (*Content Area*)

    .. raw:: html

        <style>
            div.web-css-box-model {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 100%;
                height: 100%;
                color: white;
                font-size: 36px;
                margin:0;
                border:0;
                padding:1em;
            }
        </style>
        <div style="scale: 0.75">
        <div class="web-css-box-model" style="width:1800px; height:800px; background:white; color: white; border: 1px solid black ;position:relative;">
            <span style="position:absolute; top:15px;left:25px;color: gray;">Margin</span>
            <div class="web-css-box-model" style="width:1500px; height:600px; background:darkgray;">
                <span style="position:absolute; top:115px;left:175px">Border</span>
                <div class="web-css-box-model" style="width:1200px; height:400px; background:lightblue;">
                    <span style="position:absolute; top:215px;left:325px; color:black;">Padding</span>
                    <div class="web-css-box-model" style="width:900px; height:200px; background:blue;">
                        Content Area
                    </div>
                </div>
            </div>
        </div>
        </div>

- Das Layout erfolgt relativ zum *Containing Block*.

.. supplemental::

    Eine Block Box generiert vor und nach ihrer Box einen Leerraum entlang des normalen Flusses des Dokuments. Eine Inline Box, die länger als eine Zeile ist, wird in mehrere Zeilen umgebrochen - außer bei *Replaced Elements*.

    Padding und Border können nicht negativ sein. Margin kann negativ sein.

    .. container:: hint

        ``outlines`` belegen keinen Platz und sind nicht Teil des Box-Modells. 



Inhalt, der nicht in die umgebende Box passt
------------------------------------------------


.. container:: two-columns smaller

    .. container:: column

        *HTML*

        .. code:: html
            :class: far-smaller

            <div class="container">
                <div style='width:1000px;     
                     text-align:center;'>
                    1
                </div>
                <div>2</div>
                <div>3</div>
            </div>
            <p>Der Test ist zu lang.</p>


    .. container:: column incremental

        *Spielwiese*

        .. raw:: html
            :class: one-column-iframe with-editable-content

            <iframe srcdoc="<html style='font-size:36px'><head><style>style {
                font-family: monospace; white-space: pre; display: block; background-color: whitesmoke;
            } h1 {margin:0; padding:0;} p {margin: 0; padding:0;} input {font-size: 30px; padding: 0.5em; display: block; margin: 0.5em} div > div { background-color: lightblue; opacity: 50%;  margin: 5px; padding: 15px; } </style></head><body>
                            <style spellcheck='false' contenteditable='true'>div.container {
                height: 160px;
                overflow: scroll; /*visible, hidden*/   
                /* overflow-x: hidden; */
            }
            div > div {
                width: 100%
                height: 40px;
            }
                            </style><hr>
                <div class='container'>
                    <div style='width:1500px; text-align:center;'>1</div>
                    <div>2</div>
                    <div>3</div>
                </div>
                <p>Der Test ist zu lang.</p>

                        </body>"
                    width='var(--ld-slide-width)' 
                    height='750'
                    style='border-radius: calc(var(--s-border-radius) * 0.5)' >
                iframes are not supported
            </iframe>



*Collapsing Block-Axis Margins*
------------------------------------------------

.. container:: two-columns smaller

    .. container:: column

        *HTML*

        .. code:: html
            :class: far-smaller

            <div class="container">
                <div>1</div>
                <div>2</div>
                <div>3</div>
                <p>Text</p>
            </div>



    .. container:: column incremental

        *Spielwiese*

        .. raw:: html
            :class: one-column-iframe with-editable-content

            <iframe srcdoc="<html style='font-size:36px'><head><style>style {
                font-family: monospace; white-space: pre; display: block; background-color: whitesmoke;
            } h1 {margin:0; padding:0;} p {margin: 0; padding:0px;} input {font-size: 30px; padding: 0.5em; display: block; margin: 0.5em} div > div { background-color: lightblue; opacity: 50%;  margin: 5px; padding: 15px; } </style></head><body>
                            <style spellcheck='false' contenteditable='true'>div.container {
                padding: 0;
            }
            div > div {
                width: 100%
                height: 1.2em;
                margin: 1.2em;
                /*margin-bottom: 0;*/
            }
                            </style><hr>
                    <div class='container'>
                        <div>1</div>
                        <div>2</div>
                        <div>3</div>
                        <p>Text</p>
                    </div>
                        </body>"
                    width='var(--ld-slide-width)' 
                    height='900'
                    style='border-radius: calc(var(--s-border-radius) * 0.5)' >
                iframes are not supported
            </iframe>


Floating
------------------------------------------------

Elemente können mit ``float`` aus dem normalen Fluss genommen werden: 

.. container:: two-columns smaller

    .. container:: column

        *HTML*

        .. code:: html
            :class: far-far-smaller

            <div>
                <aside style='
                    height: 5lh; padding: 1em;
                    background-color: black; color: white'>
                    Rechtspopulismus
                </aside> 
                [...] Dabei verhält sich der Rechtspopulismus
                durchaus ambivalent: Während er in einigen 
                Bereichen der Politik, wie der Kriminalitäts-
                bekämpfung, einen starken Staat fordert, lehnt
                er ihn in anderen Bereichen ab und fordert 
                stattdessen Volksabstimmungen, weil er dem 
                repräsentativen Charakter von Parlamenten 
                misstraut und durch sie den Volkswillen 
                verfälscht sieht. [...] 

                <cite> Wikipedia - Rechtspopulismus </cite>    
            </div>


    .. container:: column incremental

        *Spielwiese*

        .. raw:: html
            :class: one-column-iframe with-editable-content

            <iframe srcdoc="<html style='font-size:36px'><head><style>style {
                font-family: monospace; white-space: pre; display: block; background-color: whitesmoke;
            } h1 {margin:0; padding:0;} p {margin: 0; padding:0;} input {font-size: 30px; padding: 0.5em; display: block; margin: 0.5em} aside { padding: 1em; margin-left: 1em; } </style></head><body>
                            <style spellcheck='false' contenteditable='true'>aside {
                /*display: inline;*/
                float: right;
                box-sizing: border-box;
            }
            cite { display: block;}
                            </style><hr>
            <div>
                
                <aside style='height: 5lh; background-color: black; color: white'>Rechtspopulismus</aside> 
                
                [...] Dabei verhält sich der Rechtspopulismus durchaus ambivalent: Während er in einigen Bereichen der Politik, wie der Kriminalitätsbekämpfung, einen starken Staat fordert, lehnt er ihn in anderen Bereichen ab und fordert stattdessen Volksabstimmungen, weil er dem repräsentativen Charakter von Parlamenten misstraut und durch sie den Volkswillen verfälscht sieht. [...] 

                <cite>https://de.wikipedia.org/wiki/Rechtspopulismus</cite>                
            </div>
                        </body>"
                    width='var(--ld-slide-width)' 
                    height='800'
                    style='border-radius: calc(var(--s-border-radius) * 0.5)' >
                iframes are not supported
            </iframe>


.. supplemental::

    - Varianten:

      - ``left``: Element wird links ausgerichtet
      - ``right``: Element wird rechts ausgerichtet
      - ``none``: Element wird nicht ausgerichtet
    - Standardansatz für das Erstellen von Layouts in den Anfangstagen (totaler Hack!)
    - Um zu verhindern, dass ein Float in ein anderes Element hineinragt, kann ``clear`` verwendet werden.



Positioning - ``relative`` und ``absolute``
------------------------------------------------

.. container:: two-columns smaller

    .. container:: column

        *HTML*

        .. code:: html
            :class: far-smaller

            <div class="page">
                Ein erster Text.
            </div>
            <div class="page">
                Hier kommt mehr text.
            </div>

    .. container:: column incremental

        *Spielwiese*

        .. raw:: html
            :class: one-column-iframe with-editable-content

            <iframe srcdoc="<html style='font-size:32px'><head><style>style {
                font-family: monospace; white-space: pre; display: block; background-color: whitesmoke;
            } h1 {margin:0; padding:0;} p {margin: 0; padding:0;} input {font-size: 30px; padding: 0.5em; display: block; margin: 0.5em} aside { padding: 1em; margin-left: 1em; } </style></head><body>
                            <style spellcheck='false' contenteditable='true'>.page { 
                width: calc(100% - 20px); height: 100px;
                background-color: yellow;
                position: relative;
                padding: 10px;
                margin: 10px;
                box-sizing: border-box;
            }
            .page::after{
                content: '<Page>';
                font-size: 0.8em;
                position: absolute;
                bottom: 10px;
                right: 10px;
            }
                            </style><hr>
            <div class='page'>
                Ein erster Text.
            </div>
            <div class='page'>
                Hier kommt mehr text.
            </div>
                        </body>"
                    width='var(--ld-slide-width)' 
                    height='900'
                    style='border-radius: calc(var(--s-border-radius) * 0.5)' >
                iframes are not supported
            </iframe>


.. supplemental::

    Die Positionierung erfolgt dann über die *Offset Eigenschaften*:

    :top: Abstand zum oberen Rand des *Containing Block*
    :right: Abstand zum rechten Rand des *Containing Block*
    :bottom: Abstand zum unteren Rand des *Containing Block*
    :left: Abstand zum linken Rand des *Containing Block*

    ``relative`` positionierte Elemente verhalten sich wie ``static`` positionierte Elemente; bilden jedoch den *Containing Block* für ``absolute`` positionierte Elemente.

    ``absolute`` positionierte Elemente werden relativ zum nächsten *positionierten* Elternelement positioniert. Sollte ein solches Element nicht existieren, dann wird das Element relativ zum *Initial Containing Block* positioniert.



Positioning - ``fixed`` und ``sticky``
------------------------------------------------

:fixed: Das Element wird relativ zum Viewport positioniert.
:sticky: Das Element bleibt im normalen Fluss, bis der Zeitpunkt erreicht ist, an dem es fixiert wird (d. h. absolut positioniert wird).



Flexbox 
--------------------------------------------------------------

.. container:: smaller

    Layout-Modell, das es ermöglicht Elemente einfach innerhalb eines Containers anzuordnen.

    (Aktiviert mit ``display: flex;`` oder ``display: inline-flex``)

.. container:: two-columns smaller

    .. container:: column

        *HTML*

        .. code:: html
            :class: far-smaller

            <div id="main">
                <div class="flex-container">
                    <div class="flex-item">1</div>
                    <div class="flex-item">2</div>
                    <div class="flex-item">3</div>
                </div>
            </div>


    .. container:: column incremental

        *Spielwiese*

        .. raw:: html
            :class: one-column-iframe with-editable-content

            <iframe srcdoc="<html style='font-size:36px'><head><style>style {
                font-family: monospace; white-space: pre; display: block; background-color: whitesmoke;
            } h1 {margin:0; padding:0;} p {margin: 0; padding:0;} input {font-size: 30px; padding: 0.5em; display: block; margin: 0.5em} #main{ background-color: yellow; padding: 20px; margin-left:auto; margin-right: auto;} .flex-item { background-color: lightblue; 
                margin: 5px; padding: 15px; } </style></head><body>
                            <style spellcheck='false' contenteditable='true'>#main {width: 850px;}
            div.flex-container {
                display: flex;
                flex-direction: row; /* column */
                flex-wrap: wrap;
                justify-content: space-evenly;
            }
            div.flex-item {
                flex-basis: 150px;
                flex-grow: 1;
                height: 30px;
            }</style><hr>
            <div id='main'>
            <div class='flex-container'>
                <div class='flex-item'>1</div>
                <div class='flex-item'>2</div>
                <div class='flex-item'>3</div>
            </div>
            </div>
                        </body>"
                    width='var(--ld-slide-width)' 
                    height='750'
                    style='border-radius: calc(var(--s-border-radius) * 0.5)' >
                iframes are not supported
            </iframe>

.. supplemental::

    - Flexbox ist ein „ganzes CSS-Modul“, dass aus mehreren Eigenschaften besteht.
    - Eigenschaften des Container: ``flex-direction``, ``flex-wrap``, ``justify-content``, ``align-items``, ``align-content``, (``row-``\|\ ``column-``)\ ``gap``
    - Eigenschaften der Elemente des Containers: ``align-self``, ``flex-grow``, ``flex-shrink``, ``flex-basis``, ``order``
    - Flexbox unterscheidet zwischen der *Main Axis* und *Cross Axis*. ``flex-direction`` legt die Hauptachse fest.


.. class:: new-section transition-fade

Responsive Design
------------------------------------------------


Responsive Design - Grundlagen
------------------------------------------------


- Ziel ist es sicherzustellen, dass eine Webseite auf verschiedenen Geräten mit (sehr) unterschiedlichen Auflösungen gut aussieht.
- Durch unterschiedliche Techniken umsetzbar
  
  - Media-Queries
  - Container Queries
  - Flexbox
  - Grid-Layout


Media-Queries - Beispielhaft
------------------------------------------------

.. container:: two-columns no-default-width

    .. container:: column 

        .. code:: html
            :class: far-smaller 

            <h1>Überschrift</h1>
            <p>
                Ein Absatz.
            </p>  

    .. container:: column

        .. code:: html
            :class: far-smaller 

            <style>
                @media screen and (600px <= width < 1200px) {
                    body { background-color: lightblue; }
                    html { font-size: 16px; }
                }
                @media screen and (width < 600px) {
                    body { background-color: red; }
                    html { font-size: 12px; }
                }
                @media screen and (width >= 1200px) {
                    body {
                        background-color: whitesmoke;
                        transition: all 2.5s;
                    }
                    html { font-size: 24px; }
                }
            </style>

  
.. supplemental::

    Der Type kann für referenzierte Stylesheets direkt angegeben werden:    
    
    .. code:: html

        <link rel="stylesheet" media="screen and (max-width: 600px)" href="small.css">
        <link rel="stylesheet" media="print" href="print.css">


Media-Queries und CSS Nesting - Beispielhaft
------------------------------------------------

Kombination von Media-Queries und CSS Nesting, um *Drop Caps* nur auf großen Bildschirmen anzuzeigen.

.. code:: css
    :class: far-smaller 

    p {
        font-size: 0.9rem;
        font-style: italic;
        min-height: 3lh;

        @media (width >= 1200px) {
            &::first-letter {
                float: left;
                font-size: 2lh;
                line-height: 2lh;
                font-weight: bold;
            }
        }
    }


Flexbox - Beispielhaft
--------------------------

.. container:: two-columns no-default-width

    .. container:: column 

        .. code:: html
            :class: far-smaller 

            <section>
                <p>
                    D-Day bezeichnet im Englischen
                    den Stichtag militärischer 
                    Operationen. 
                </p>
                <p>
                    Die Europawahl 2024 ist die 
                    zehnte Direktwahl zum 
                    Europäischen Parlament.
                </p>
                <p>
                    Demokratie ist ein Begriff für
                    Formen der Herrschaftsorgani-
                    sation auf der Grundlage der 
                    Partizipation aller.
                </p>
            </section>

    .. container:: column

        .. code:: html
            :class: far-smaller 

            <style>
                section {
                    display: flex;
                    flex-direction: row;
                    flex-wrap: wrap;
                    gap: 1em;
                }

                section p {
                    flex-basis: 
                        calc(900px * 999 - 100% * 999); 
                    flex-grow: 1;
                    flex-shrink: 1;
                    background-color: whitesmoke;
                    padding: 1em;
                    margin: 0;
                }
            </style>

  
.. supplemental::

    Der „Trick“ ist, dass die Berechnung für ``flex-basis`` so gewählt ist, dass ab einer bestimmten Größe der Wert für flex-basis entweder sehr groß ist (und damit nur noch ein Element in die Zeile passt oder eben sehr klein ist und damit alle Elemente in eine Zeile passen.)



Dark and Light Mode
------------------------------------------------

.. stack::

    .. layer::

      - Die Unterstützung sowohl von Dark und Light-Mode ist mittlerweile Standard.
      - Der aktuelle Modus kann mittels ``prefers-color-scheme`` abgefragt werden:

        - ``@media ( prefers-color-scheme: dark ) { ... }``
        - ``@media ( prefers-color-scheme: light ) { ... }``

    .. layer:: incremental

        (Eine) Vorgehensweise: Definition des Farbschemas über *Custom Properties*

        .. code:: css
            :class: far-far-smaller

            :root {
                /* Here, the default theme is the "light theme" */        
                --background-color: white;
                --text-color: black;
            }

            @media ( prefers-color-scheme: dark ) {
                :root {
                    --background-color: black;
                    --text-color: white;
                }
                a:link {
                    color: lightcoral;
                }
            }


.. class:: not-covered-topics transition-fade

Nicht Behandelte Themen
------------------------------------------------

- Cascade Layers
- Counter

- Transformation (skalieren, drehen, ...)
- Animation
 
  .. scaling using ``scale`` vs. using ``transform: scale``
- (bisher nur grob) Flexbox  (`A guide to flex-box <https://css-tricks.com/snippets/css/a-guide-to-flexbox/>`__)
- Grid-Layout (`A complete guide to CSS Grid <https://css-tricks.com/snippets/css/complete-guide-grid/>`__)

- CSS Tricks

  
- Shadow-DOM (und HTML Custom Elements)
- Dokumente mit alternativen Flussrichtungen (rechts nach links / oben nach unten)
- CSS bzgl. Printing


.. supplemental::

    Es gibt sehr, sehr viele CSS Tricks die Dinge ermöglichen, die nicht unmittelbar zu erwarten gewesen wären. Z. B. kann man einem Element einen Index zuordnen basierend auf dem ":nth-child()" Selektor. Dieser Index kann dann für „die Berechnung“ von weiteren Werten verwendet werden.

.. class:: integrated-exercise transition-move-up

Übung - Wo Licht ist, ist auch Schatten
-----------------------------------------

Bauen Sie Unterstützung für den Dark und Light Mode nach. Den Rumpf der HTML-Datei finden Sie im Anhang.

.. raw:: html

    <div style="margin-left: auto; margin-right:auto; width: fit-content">
        <video 
            width="1260px" 
            height="820px"
            controls
            autoplay
            loop
            muted
            preload="metadata"
            style="box-shadow: var(--trbl-shadow)">
            <source src="code/3rd-exercise/dark-light.mov" type="video/mp4">
            Your browser does not support the video tag.
        </video>
    </div>

.. supplemental::

    **HTML-Datei**

    .. code::  html
        :class: copy-to-clipboard far-far-smaller

        <!DOCTYPE html>
        <html lang="de">
        <body>
          <main>
            <h1>Naturalismus (Philosophie)</h1>
            <p>
            Der Naturalismus ist die Auffassung, dass die Welt als ein 
            rein von der Natur gegebenes Geschehen zu begreifen ist. 
            Er geht davon aus, dass alles natürliche Ursachen hat und 
            dass es nichts Übernatürliches gibt.[...]
            </p>
            <cite>
              Quelle: 
              <a href="https://de.wikipedia.org/wiki/Naturalismus_(Philosophie)">
              Wikipedia
              </a>
            </cite>
          </main>
        </body>
        </html>

    **Grundlegendes CSS Gerüst**

    .. code:: css
        :class: copy-to-clipboard far-far-smaller

        /* The following CSS does not define any colors/color scheme. */ 
        :root {
            --font-siz
            --font-family: sans-serif;
        }

        body {
            max-width: 60ch;
            padding: 20px;
            font-size: var(--font-size);
            font-family: var(--font-family);
            padding: 0;
            margin: 0;
            margin-right:auto;
            margin-left:auto;
        }
        h1 {
            padding:0.75rem;
            margin-bottom: 0;
            border-radius: 0.5em 0.5em 0 0;
            backdrop-filter: blur(5px);
            -webkit-backdrop-filter: blur(10px);
        }
        p {
            position: relative;
            margin-top:0;
            margin-bottom:0;
            padding: 0.75rem;
            border-radius: 0 0 0.5em 0.5em ;
            font-weight: 100;
            text-wrap: pretty;
        }
        cite {
            display: block;
            padding: 0.5rem;
            text-align: right;
            font-size: smaller;
        }



.. class:: integrated-exercise transition-move-up

Übung - Komplexeres Layout
---------------------------

.. exercise:: 
    
    Versuchen Sie das Layout der folgenden HTML-Datei mittels CSS nachzubauen. Der HTML Code darf nicht verändert werden. JavaScript darf auch nicht verwendet werden. Den Rumpf der HTML-Datei finden Sie im Anhang.

    .. solution::
        :pwd: web-css.

        .. code:: css
            :class: smaller copy-to-clipboard

            body {
                height: 100dvh;
                margin: 0;
                display: flex;
                flex-direction: column;
                background-color: black;
                color: whitesmoke;
                font-family: system-ui, Ubuntu, Cantarell, 
                            'Open Sans', 'Helvetica Neue', sans-serif;
            }

            main {
                flex-grow: 1;
                padding: 0.5rem;
                margin: 0;
                height:70%;
                overflow-y: scroll;
            }
            
            blockquote {
                margin-left: 1em;
                margin-right: 1em;
                border-left: 2px solid lightgray;
                padding: 0em 1em 0em 1em;
                font-style: italic;

                h1 {
                    color: lightgray;
                    background-color: rgb(25%, 25%, 25%);
                    padding: 0.35rem 0.35rem 0.75rem 0.35rem;
                    margin: 0;
                    border-radius: 0.5rem 0.5rem 0 0;
                }

                p {
                    margin-top: 0;
                    padding: 0 0.35rem 0.35rem 0.35rem;
                    border-radius: 0 0 0.5rem 0.5rem;
                    background-color: rgb(30%, 30%, 30%);
                    min-height: 3lh;
                    font-family: Georgia, 'Times New Roman', Times, serif;
                    line-height: 1.5em;

                    @media (width >=1200px) {
                        &::first-letter {
                            float: left;
                            margin-right: 0.15em;
                            font-size: 2lh;
                            line-height: 2lh;
                            font-weight: bold;
                        }
                    }
                }

                h1:has(+ p:hover), p:hover {
                    color: rgb(255, 201, 154);
                    box-shadow: 0.1rem 0.1rem 0.1rem white;
                    transition: all 0.6s;
                }
            }

            blockquote::after {
                content: attr(cite)" - June 2024";
                display: block;
                padding-bottom: 1em;
                text-align: right;
                font-size: 0.8em;
            }

            nav {
                flex-grow: 0;
                display: flex;
                flex-wrap: wrap;
                justify-content: space-around;
                margin: 0;
                padding: 0.3rem;
                font-size: 0.75em;
                color: gray;
                background-color: whitesmoke;

                & a {
                    /* Erklärung ist auf den Folien zu finden. */
                    flex-basis: 
                    calc(900px * 999 - 100% * 999); 
                    flex-grow: 1;
                    text-align: center;
                    margin: 0.2rem;
                    padding: 0.5em;

                    text-decoration: none;
                    color: whitesmoke;
                    background-color: gray;

                    border-radius: 0.2em;
                }

                & a:visited {
                    color: whitesmoke;
                }

                & a:hover {
                    box-shadow: 4px 4px 4px black;
                    transition: all 0.3s;
                }
            } 


.. raw:: html

    <div style="margin-left: auto; margin-right:auto; width: fit-content">
        <video 
            width="1150px" 
            height="755px"
            controls 
            autoplay 
            loop
            muted
            preload="metadata"
            style="box-shadow: var(--trbl-shadow);">
            <source src="code/simple-layout-480p.mov" type="video/mp4">
            Your browser does not support the video tag.
        </video>
    </div>




.. supplemental::

    .. rubric:: Hinweise

    .. container:: smaller

        Mit Hilfe der folgenden CSS Eigenschaften können Sie das Layout nachbauen. Es gibt aber viele Wege, die zum Ziel führen!

        **Verhalten (zum Beispiel mit Flexbox)**

        - ``display: flex``, ``flex-direction``, ``flex-wrap``, ``flex-basis``, ``flex-grow``, ``gap``, ``height``, ``overflow-y``

        **Größen und Abstände**

        - margin(-right|-left), border, padding, font-size, line-height

        **Optik**

        - box-shadow, font-style, font-family, color, background-color, border-radius, text-decoration

        **Animation**

        - transition: all 0.6s; 


    .. container:: trick

        Nutzen Sie ggf. die Tricks aus dem Foliensatz!

    .. rubric:: Rumpf der HTML-Datei

    .. code:: html
        :class: far-far-smaller copy-to-clipboard
            
        <!DOCTYPE html>
        <html lang="de">

        <head>
            <style>
                html {
                    margin: 0;
                    border: 0;
                    padding: 0;
                    font-size: 24px;
                }

                /* TODO */
            </style>
        </head>

        <body>
            <header>
                <nav>
                    <a href="#einfuehrung">Die Demokratie</a>
                    <a href="#lib_demokratie">Liberale Demokratie</a>
                    <a href="#rep_demokratie">Repräsentative Demokratie</a>
                    <a href="#dir_demokratie">Direkte Demokratie</a>
                </nav>
            </header>
            <main>
                Anlässlich der Gefahren, die unserer Demokratie drohen, sollte
                man sich mit den verschiedenen Formen der Demokratie 
                auseinandersetzen.

                <blockquote cite="https://de.wikipedia.org/wiki/Demokratie">
                    <h1 id="einfuehrung">Demokratie</h1>
                    <p>
                        Demokratie (von altgriechisch δημοκρατία dēmokratía 
                        Volksherrschaft) ist ein Begriff für Formen der
                        Herrschaftsorganisation auf der Grundlage der 
                        Partizipation bzw. Teilhabe aller an der politischen
                        Willensbildung. Es handelt sich um einen zentralen 
                        Begriff der Politikwissenschaft, der ursprünglich aus
                        der Staatsformenlehre stammt und in der 
                        Demokratietheorie erörtert wird. Die erste begriffliche 
                        Erwähnung findet sich bezogen auf die Attische 
                        Demokratie bei Herodot. Ideengeschichtlich wegweisend 
                        für den Begriff war
                        die
                        Definition der Politie bei Aristoteles. Eine 
                        schlagwortartige Beschreibung aus der Moderne liefert
                        Abraham
                        Lincolns Gettysburg-Formel von 1863: „Regierung des 
                        Volkes, durch das Volk, für das Volk“.
                    </p>

                    <h1 id="lib_demokratie">Liberale Demokratie</h1>
                    <p>
                        Zur liberalen Demokratie, wie sie sich nach westlichen 
                        Mustern herausgebildet hat, gehören allgemeine,
                        freie
                        und geheime Wahlen, die Aufteilung der Staatsgewalt bei 
                        Gesetzgebung, Regierung und Rechtsprechung auf
                        voneinander unabhängige Organe (Gewaltenteilung) sowie 
                        die Garantie der Grundrechte.
                    </p>

                    <h1 id="rep_demokratie">Repräsentative Demokratie</h1>
                    <p>
                        In einer repräsentativen Demokratie, in der gewählte 
                        Repräsentanten zentrale politische Entscheidungen
                        treffen, haben oft Parteien maßgeblichen Anteil an der 
                        politischen Willensbildung und an der durch
                        Wahlen
                        legitimierten Regierung. Die Opposition ist fester 
                        Bestandteil eines solchen demokratischen Systems, zu
                        dem
                        auch die freie Meinungsäußerung samt Pressefreiheit, die 
                        Möglichkeit friedlicher Regierungswechsel und
                        der
                        Minderheitenschutz gehören.
                    </p>
                    <h1 id="dir_demokratie">Direkte Demokratie</h1>
                    <p>
                        In einer direkten Demokratie trifft das Stimmvolk 
                        politische Entscheidungen direkt.
                    </p>
                </blockquote>
            </main>

        </body>

        </html>


