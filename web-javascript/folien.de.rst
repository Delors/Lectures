.. meta:: 
    :author: Michael Eichberg
    :keywords: "Web Programmierung", "JavaScript"
    :description lang=de: Webprogrammierung mit JavaScript
    :id: lecture-web-programming-javascript
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
.. role:: ger-quote
.. role:: obsolete
.. role:: line-above



.. class:: animated-symbol

Webprogrammierung mit JavaScript
================================================

Eine kurze Einführung/eine kurze Übersicht über JavaScript für erfahrene Programmierer.

.. container:: line-above tiny

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
    :Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
    :Version: 2.0 (alpha)

.. supplemental::

    :Folien: 
        
        |html-source|

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



Historie
------------------------------------------------

.. module:: timeline
   :class: far-smaller align-center

   {
    "class" : "JavaScript-Timeline",
    "spread" : "1.1",
    "data": [
        {"d":"03/1996","t":"1.0"},
        {"d":"08/1996","t":"1.1"},
        {"d":"06/1997","t":"1.2"},
        {"d":"10/1998","t":"1.3 - ECMA-262 1st + 2nd edition"},
        {"d":"11/2000","t":"1.5 - ECMA-262 3rd edition"},						
        {"d":"11/2005","t":"1.6"},
        {"d":"10/2006","t":"1.7"},
        {"d":"06/2008","t":"1.8"},
        {"d":"07/2010","t":"1.8.5 - ECMAScript 5"},
        {"d":"06/2015","t":"ECMAScript 6"}
    ]
   }
    
Seit 2016 gibt es jährliche Updates (ECMAScript 2016, 2017, 2018, 2019, 2020, 2021, 2022, ...)



Grundlagen
--------------

.. container:: scrollable
   
  .. class:: incremental

  - Objektorientiert
  
      .. class:: list-with-explanations 

      - Protoypische Vererbung
      - Objekte *erben* von anderen Objekten
      - Objekte als allgemeine Container
      
         (Im Grunde eine Vereinheitlichung von Objekten und Hashtabellen.)
      - seit ES6 werden auch Klassen unterstützt; diese sind aber nur syntaktischer Zucker
  - Skriptsprache

      - *Loose Typing*/*Dynamische Typisierung*
      - *Load and go-delivery* (Lieferung als Text/Quellcode)
      - Garbage Collected
      - Single-Threaded

  - Funktionen sind Objekte erster Klasse
  - Ein (globaler) Namespace
  - Syntaktisch eine Sprache der "C"-Familie (viele Ähnlichkeiten zu Java)
  - Standardisiert durch die ECMA (ECMAScript)
  - Verwendet ganz insbesondere in Browsern, aber auch Serverseitig (`Node.js <http://nodejs.org/>`__) oder in Desktop-Anwendungen (Electron)



Reservierte Schlüsselworte
-----------------------------

Schlüsselworte:

.. class:: incremental

  - ``function, async, await, return, yield``
  - ``break, continue, case, default, do, else, for, if, instanceof, of, typeof, switch, while``
  - ``throw, try, finally, catch``
  - ``class, delete, extends, in, new, static, super, this``
  - ``const, let, var``
  - ``export, import``

.. container:: incremental

   Nicht genutzte Schlüsselworte:

   ``enum, implements, interface, package, private, protected, public, void, with`` (no longer)



Bezeichner (*Identifier*)
---------------------------
 
.. container:: minor

   (Sehr vergleichbar mit Java.)

.. class:: incremental

- Buchstaben (Unicode), Ziffern, Unterstriche, Dollarzeichen
- Ein Identifier darf nicht mit einer Ziffer beginnen
  
- Nameskonventionen: 
  
  - Klassen beginnen mit einem Großbuchstaben (*UpperCamelCase*)
  - Variablen und Funktionen beginnen mit einem Kleinbuchstaben (*lowerCamelCase*)
  - Konstanten sind komplett in Großbuchstaben



Global Verfügbare Objekte
--------------------------------

Standard
_________

.. class:: smaller

- ``console``
- ``Number``, ``Boolean``,  ``Date``, ``BigInt``, ``Math``, ...



`Von Browsern zur Verfügung gestellte Objekte <https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects>`__ (Ein Auszug) 
________________________________________________________________________________________________________________________________________________________

.. class:: smaller

.. container:: two-columns

   .. container:: column

     - ``window`` 
     - ``document`` (bzw. ``window.document``)

   .. container:: column margin-left-1em

     - ``alert`` 
     - ``navigator``
     - ``location``



`Von Node.js zur Verfügung gestellte Objekte <https://nodejs.org/api/globals.html>`__ (Ein Auszug)
_____________________________________________________________________________________________________

.. class:: smaller

.. container:: two-columns

   .. container:: column

     - ``module``
     - ``exports``
     - ``require``
  
   .. container:: column  margin-left-1em

     - ``process``
     - ``crypto``



`Deklaration von Variablen <./code/Variables_const_let.mjs>`__ (``const`` und ``let``)
---------------------------------------------------------------------------------------

.. container:: scrollable

   .. include:: code/Variables_const_let.mjs
      :code: javascript
      :start-line: 2
      :end-before: done();
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 2

.. supplemental::

   Um diesen und den Code auf den folgenden Folien ggf. mit Hilfe von Node.js auszuführen, muss am Anfang der Datei:

      ``import { ilog, log, done } from "./log.mjs";``

   und am Ende der Datei:

     ``done();``
   
   hinzugefügt werden.

   Den entsprechenden Code der Module (log.mjs und später Queue.mjs) finden Sie auf:

   `https://github.com/Delors/delors.github.io/tree/main/web-javascript/code <https://github.com/Delors/delors.github.io/tree/main/web-javascript/code>`__



`Datentypen und Operatoren <./code/Datatypes.js>`__
-----------------------------------------------------

.. container:: scrollable
      
   .. include:: code/Datatypes.js
      :code: javascript
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 2



`Funktionsdefinitionen <./code/Functions_basics.js>`__
-------------------------------------------------------

.. container:: scrollable

   .. include:: code/Functions_basics.js
      :code: javascript
      :start-line: 3
      :end-before: ////////////////////////////////////////////////////////////////////////////////
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 2



.. class:: exercise

Übung - vertraut machen mit JavaScript und Node.js
----------------------------------------------------

**Voraussetzung: Installieren Sie Node.js (http://nodejs.org/).**

.. exercise:: Hello World in Node.js

   Starten Sie die Konsole/Terminal und schreiben Sie ein einfaches JavaScript Programm, das "Hello World" ausgibt.

   .. solution:: 
      :pwd: HelloWorld

      .. code:: javascript
            
         console.log("Hello World");

.. exercise:: Hello World auf der JavaScript Console 

   Starten Sie einen Browser und aktivieren Sie die JavaScript Console in den Entwicklerwerkzeugen. Schreiben Sie ein einfaches JavaScript Programm, das "Hello World" ausgibt.

   .. solution:: 
      :pwd: HelloWorld

      .. code:: javascript
            
         console.log("Hello World");


.. class:: exercise

Übung - die JavaScript Konsole
------------------------------------

.. exercise:: Prototyping mit der JavaScript Konsole

   Schreiben Sie ein kurzes JavaScript Programm, das programmatisch zum Ende des Dokuments scrollt.

   .. container:: smaller
   
      Hinweise: 

      - das von :code:`document.body` referenziert HTML Element enthält den gesamten Inhalt des Dokuments
      - die aktuellen Abmaße des Dokuments können Sie mit der Funktion :code`window.getComputedStyle(<HTML Element>).height` ermitteln; geben Sie den Wert auf der Konsole aus bevor Sie das Dokument scrollen; was fällt Ihnen auf?
      - um zu scrollen, können Sie window.scrollTo(x,y) verwenden
      - um den Integer Wert eines Wertes in Pixeln zu bestimmen, können Sie ``parseInt`` verwenden

   .. solution:: 
      :pwd: scrollTo(x,y)

      .. code:: javascript

         const h = window.getComputedStyle(document.body).height
         window.scrollTo(0,parseInt(h));



`Vergleich von Werten und implizite Typumwandlung <./code/ComparingValues.mjs>`__
------------------------------------------------------------------------------------------

.. container:: scrollable

   .. include:: code/ComparingValues.mjs
      :code: javascript
      :start-line: 2
      :end-before: done();
      :number-lines:
      :class: far-far-smaller copy-to-clipboard
      :tab-width: 4

.. supplemental::

   NaN (Not a Number) repräsentiert das Ergebnis einer Operation die keinen sinnvollen Wert hat. Ein Vergleich mit NaN ist *immer* :code:`false`. Um zu überprüfen, ob ein Wert NaN ist muss :code:`isNaN(<Value>)` verwendet werden.


`Bedingungen und Schleifen <./code/LoopsAndConditions.mjs>`__
--------------------------------------------------------------

.. container:: scrollable

   .. include:: code/LoopsAndConditions.mjs
      :code: javascript
      :start-line: 2
      :end-before: done();
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 4


.. supplemental::

  Die Tatsache, dass insbesondere null als auch undefined falsy sind, wird of in Bedingungen ausgenutzt (z. B., :code:`if (!x)...`).



`Fehlerbehandlung <./code/Errors.js>`__
--------------------------------------------------------------

.. container:: scrollable

   .. include:: code/Errors.js
      :code: javascript
      :start-line: 2
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 4


.. supplemental::

   In JavaScript können während der Laufzeit Fehler auftreten, die (z. B.) in Java während des kompilierens erkannt werden. 



Übung - Bedingungen und Schleifen
------------------------------------

.. exercise:: removeNthElement

   Implementieren Sie eine Funktion, die ein Array übergeben bekommt und ein neues Array zurückgibt in dem jedes n-te Element nicht vorkommt.

   Beispiel: ``removeNthElement([1,2,3,4,5,6,7], 2)`` :math:`\Rightarrow` ``[1,3,5,7]``

   - Schreiben Sie Ihren Code in eine JavaScript Datei und führen Sie diese mit Hilfe von Node.js aus. 
   
   - Testen Sie Ihre Funktion mit verschiedenen Eingaben und lassen Sie sich das Ergebnis ausgeben (z. B. :code:`console.log(removeNthElement([1,2,3,4,5,6,7],2))`)!

   .. solution::
      :pwd: _RemoveNthElement

      .. code:: javascript

         function removeNthElement(arr, n) {
            const a = [];
            for (i = 0; i < arr.length ; i ++) {
               if ((i+1) % n === 0) continue;
               a.push(arr[i]);
            }
            return a;
         }

         console.log(removeNthElement([1,2,3,4,5,6,7],2))
         console.log(removeNthElement([1],2))
         console.log(removeNthElement([1,2,3],1))
         console.log(removeNthElement([1,2,3,4,5,6],4))



Übung - Fehlerbehandlung
------------------------------------

.. exercise:: removeNthElement mit Fehlerbehandlung

   - Erweitern Sie die Implementierung von ``removeNthElement`` so, dass die Funktion einen Fehler wirft, wenn das übergebene Array kein Array ist oder wenn der zweite Parameter kein positiver Integer ist.
  
   - Testen Sie alle Fehlerzustände und fangen Sie die entsprechenden Fehler ab (``catch``) und geben Sie die Nachrichten aus.

   .. solution:: 
      :pwd: removeNthElementWithErrorHandling

      .. code:: javascript

         function removeNthElement(arr, n) {
            if (!Array.isArray(arr)) 
               throw new Error("arr must be an array.");
            if (!Number.isInteger(n)) {
               throw new Error("n must be an integer.");
            }
            if (n <= 0) {
               throw new RangeError("n must be a positive integer.");
            }

            const a = [];
            for (i = 0; i < arr.length ; i ++) {
               if ((i+1) % n === 0) continue;
               a.push(arr[i]);
            }
            return a;
         }

         try {
            console.log(removeNthElement(undefined,2))
         } catch (e) {
            console.error(e.message);
         }
         try {
            console.log(removeNthElement([1],{}))
         } catch (e) {
            console.error(e.message);
         }
         try {
            console.log(removeNthElement([1],-2))
         } catch (e) {
            console.error(e.message);
         }
         


.. class:: exercise

Übung - Funktionen 
---------------------

.. exercise:: Einfacher RPN Calculator

   Implementieren Sie einen einfachen RPN (Reverse Polish Notation) Calculator, der eine Liste von Zahlen und Operatoren (``+``, ``-``, ``*``, ``/``) als Array entgegennimmt und das Ergebnis berechnet.

   Nutzen Sie keine ``if`` oder ``switch`` Anweisung, um die Operatoren zu unterscheiden. Nutzen Sie stattdessen ein Objekt. Sollte der Operator unbekannt sein, dann geben Sie eine entsprechende Fehlermeldung aus.

   Beispiel: ``eval([2,3,"+",4,"*"])`` :math:`\Rightarrow` ``20``

   .. solution::
      :pwd: _RPNCalculator+

      .. code:: javascript

         const ops = {
            "+": (a,b) => a+b,
            "-": (a,b) => a-b,
            "*": (a,b) => a*b,
            "/": (a,b) => a/b
         }

         function eval(expr) {
            const stack = [];
            for (const e of expr) {
               if (Number.isInteger(e)) {
                  stack.push(e);
               } else {
                  const b = stack.pop();
                  const a = stack.pop();
                  const op = ops[e];
                  if (!op) {
                     throw new Error("Unknown operator: "+e);
                  } 
                  stack.push(op(a,b));
               }
            }
            return stack.pop();
         }

         eval([2,3,"+",4,"*"]) 
         eval([2,3,"+",4,"%"]) 



.. TODO ALLES ÜBERARBEITEN!!!!!!!!!!!!

.. TODO: 
   - adding JavaScript to HTML files (in particular "defer" and "async" and type="module" )
   - adding major events DOMContentLoaded, onload, etc.
   - 



`Variables (var) <./code/Variables_var.mjs>`__ 
-----------------------------------------------------------------------------------------------

**(Neuer Code sollte var nicht mehr verwenden!)**

.. container:: scrollable

   .. include:: code/Variables_var.mjs
      :code: javascript
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 2
      :start-line: 2
      :end-before: done();



Destrukturierung (:eng:`Destructuring`)
------------------------------------------------

.. container:: scrollable
      
   .. include:: code/Destructuring.mjs
      :code: javascript
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 2
      :start-line: 2
      :end-before: done();



JSON (JavaScript Object Notation)
------------------------------------------------

.. container:: scrollable
      
   .. include:: code/JSON.js
      :code: javascript
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 4


.. supplemental::

   JSON requires that keys must be strings and strings must be enclosed in double quotes.



`Reguläre Ausdrücke <./code/RegularExpressions.js>`__
-------------------------------------------------------

- Built-in support by means of regular expression literals and an API
- Use Perl syntax
- Methods on regular expression objects: test (e.g., RegExp.test(String)).
- Methods on strings that take RegExps: search, match, replace, split,...

.. container:: scrollable
      
   .. include:: code/RegularExpressions.js
      :code: javascript
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 2



``this`` `in Funktionen und partielle Funktionsaufrufe <./code/Functions_this.js>`__
------------------------------------------------------------------------------------------

.. container:: scrollable

   .. include:: code/Functions_this.js
      :code: javascript
      :start-line: 3
      :end-before: ////////////////////////////////////////////////////////////////////////////////
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 2



Alles ist ein Objekt
------------------------------------------------

- ``this`` ist ein "zusätzlicher" Parameter, dessen Wert von der aufrufenden Form abhängt
- ``this`` ermöglicht den Methoden den Zugriff auf ihr Objekt
- ``this`` wird zum Zeitpunkt des Aufrufs gebunden (außer bei Arrow-Funktionen)

.. container:: scrollable

   .. include:: code/EverythingIsAnObject.js
      :code: javascript
      :number-lines:
      :class: far-far-smaller copy-to-clipboard 
      :tab-width: 4



Prototype basierte Vererbung
------------------------------------------------

.. stack::

   .. layer::

      .. code:: javascript
         :number-lines:
         :class: copy-to-clipboard far-smaller

         const p = { s : "p" };
         const c = Object.create(p);
         const gc = Object.create(c);

      .. image:: images/prototype_chain/object_literals_and_the_prototype_chain.svg
         :width: 1400px
         :align: center

   .. layer:: incremental
      
      .. code:: javascript
         :number-lines:
         :class: copy-to-clipboard far-smaller

         const p = { s : "p" };
         const c = Object.create(p);
         const gc = Object.create(c);
         gc.t = "q";

      .. image:: images/prototype_chain/object_literals_and_the_prototype_chain_with_update.svg
         :width: 1400px
         :align: center

   .. layer:: incremental
      
      .. rubric:: *Pseudoclassical Inheritance*

      .. code:: javascript
         :number-lines:
         :class: copy-to-clipboard far-far-smaller


         function Person(name, title){ this.name = name; this.title = title; } // constructor
         Person.prototype.formOfAddress = function (){
            const foa = "Dear ";
            if(this.title){ foa += this.title+" "; }
            return foa + this.name; 
         }
         function Student(name, title, id, email) { // constructor
            Person.call(this, name, title);
            this.id = id;
            this.email = email;
         }
         Student.prototype = Object.create(Person.prototype);
         Student.prototype.constructor = Student;
         
         const aStudent = new Student("Emilia Galotti", "Mrs.", 1224441, 'emilia@galotti.com'); 

   .. layer:: incremental
      
      .. rubric:: Objektabhängigkeiten

      .. code:: javascript
         :number-lines:
         :class: copy-to-clipboard far-far-smaller

         function Person(name, title){ … }
         Person.prototype.formOfAddress = function (){ … }

         function Student(name, title, id, email) { … }
         Student.prototype = Object.create(Person.prototype);
         Student.prototype.constructor = Student;
 
         const p = new Person(…); const s = new Student(…);

      .. image:: images/prototype_chain/pseudoclassical_Inheritance.svg
         :width: 1000px
         :align: center




Prototype basierte Vererbung
------------------------------------------------

.. include:: code/Prototypes.js
   :code: javascript
   :number-lines:
   :class: far-far-smaller scrollable copy-to-clipboard
   :tab-width: 4


Classes
------------------------------------------------

.. include:: code/Classes.js
   :code: javascript
   :number-lines:
   :class: far-far-smaller scrollable copy-to-clipboard
   :tab-width: 4



DOM Manipulation
------------------------------------------------

.. include:: code/DOM.html
   :code: html
   :number-lines:
   :class: far-far-smaller scrollable copy-to-clipboard
   :tab-width: 4


.. TODO: 
   Promises,
   async-await, 

Interaktion mit Server
--------------------------

.. include:: code/EventHandling.html
   :code: html
   :number-lines:
   :class: far-far-smaller scrollable copy-to-clipboard
   :tab-width: 4


.. TODO: ECMAScript Modules (Plug-in Architektur mit Modules)


Referenzen
------------------------------------------------

- `HTML DOM API  <https://developer.mozilla.org/en-US/docs/Web/API/HTML_DOM_API>`__