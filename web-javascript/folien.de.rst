.. meta::
   :author: Michael Eichberg
   :keywords: Web Programmierung, JavaScript, ECMAScript
   :description lang=de: Webprogrammierung mit JavaScript
   :id: vorlesung-web-programmierung-javascript
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Webprogrammierung mit JavaScript
================================================

Eine kurze Einführung/eine kurze Übersicht über JavaScript für erfahrene Programmierer.

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 2.1.2

.. supplemental::

    :Folien:

        |html-source|

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



Historie
------------------------------------------------

.. module:: timeline
   :class: s-font-size-90 align-center

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



.. class:: new-section

Grundlegende Sprachkonstrukte
------------------------------------------------



Grundlagen
--------------

.. story::

  .. class:: incremental-list

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
  - (im Wesentlichen) ein (globaler) Namespace
  - Syntaktisch eine Sprache der "C"-Familie (viele Ähnlichkeiten zu Java)
  - Standardisiert durch die ECMA (ECMAScript)
  - Verwendet ganz insbesondere in Browsern, aber auch Serverseitig (z. B. `Node.js <http://nodejs.org/>`__) oder in Desktop-Anwendungen (z. B. Electron)



Reservierte Schlüsselworte
-----------------------------

.. rubric:: Genutzte Schlüsselworte

.. class:: incremental-list

  - :javascript:`function`, :javascript:`async`, :javascript:`await`, :javascript:`return`, :javascript:`yield`, :javascript:`void`
  - :javascript:`break`, :javascript:`continue`, :javascript:`case`, :javascript:`default`, :javascript:`do`, :javascript:`else`, :javascript:`for`, :javascript:`if`, :javascript:`instanceof`, :javascript:`of`, :javascript:`typeof`, :javascript:`switch`, :javascript:`while`
  - :javascript:`throw`, :javascript:`try`, :javascript:`finally`, :javascript:`catch`
  - :javascript:`class`, :javascript:`delete`, :javascript:`extends`, :javascript:`in`, :javascript:`new`, :javascript:`static`, :javascript:`super`, :javascript:`this`
  - :javascript:`const`, :javascript:`let`, :javascript:`var`
  - :javascript:`export`, :javascript:`import`

.. remark::
   :class: incremental

   Nicht (mehr) genutzte Schlüsselworte:

   :javascript:`enum`, :javascript:`implements`, :javascript:`interface`, :javascript:`package`, :javascript:`private`, :javascript:`protected`, :javascript:`public`, :javascript:`with` (nicht mehr verwendet)



Bezeichner (*Identifier*)
---------------------------

.. container:: peripheral

   (Sehr vergleichbar mit Java.)

.. class:: incremental-list

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

- ``console``
- ``Number``, ``Boolean``,  ``Date``, ``BigInt``, ``Math``, ...

`Von Browsern zur Verfügung gestellte Objekte <https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects>`__ (Ein Auszug)
________________________________________________________________________________________________________________________________________________________

.. class:: horizontal

- ``window``
- ``document`` (bzw. ``window.document``)
- ``alert``
- ``navigator``
- ``location``

`Von Node.js zur Verfügung gestellte Objekte <https://nodejs.org/api/globals.html>`__ (Ein Auszug)
____________________________________________________________________________________________________

.. class:: horizontal

- ``module``
- ``exports``
- ``require``
- ``process``
- ``crypto``



`Deklaration von Variablen <./code/Variables_const_let.mjs>`__ (:js:`const` und :js:`let`)
--------------------------------------------------------------------------------------------

.. scrollable::

   .. include:: code/Variables_const_let.mjs
      :code: javascript
      :start-line: 2
      :end-before: done();
      :number-lines:
      :tab-width: 2

.. supplemental::

   Um diesen und den Code auf den folgenden Folien ggf. mit Hilfe von Node.js auszuführen, muss am Anfang der Datei:

      :js:`import { ilog, log, done } from "./log.mjs";`

   und am Ende der Datei:

     :js:`done();`

   hinzugefügt werden.

   Den entsprechenden Code der Module (log.mjs und später Queue.mjs) finden Sie auf:

   `https://github.com/Delors/delors.github.io/tree/main/web-javascript/code <https://github.com/Delors/delors.github.io/tree/main/web-javascript/code>`__




.. class:: outdated

`Variables (var) <./code/Variables_var.mjs>`__
-----------------------------------------------------------------------------------------------

.. attention::

    Neuer Code sollte var nicht mehr verwenden!

.. scrollable::

   .. include:: code/Variables_var.mjs
      :code: javascript
      :number-lines:
      :tab-width: 2
      :start-line: 2
      :end-before: done();



`Datentypen und Operatoren <./code/Datatypes.js>`__
-----------------------------------------------------

.. scrollable::

   .. include:: code/Datatypes.js
      :code: javascript
      :number-lines:
      :tab-width: 2



`Funktionsdefinitionen <./code/Functions_basics.js>`__
-------------------------------------------------------

.. scrollable::

   .. include:: code/Functions_basics.js
      :code: javascript
      :start-line: 3
      :end-before: ////////////////////////////////////////////////////////////////////////////////
      :number-lines:
      :tab-width: 2



.. class:: exercises

Übung - JavaScript und Node.js erste Schritte
----------------------------------------------------

*Voraussetzung: Installieren Sie Node.js (http://nodejs.org/).*

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



.. class:: exercises

Übung - die JavaScript Konsole
------------------------------------

.. exercise:: Prototyping mit der JavaScript Konsole

   Schreiben Sie ein kurzes JavaScript Snippet (in der Konsole des Browsers), das programmatisch zum Ende des Dokuments scrollt.

   .. hint:: 

      - :js:`document.body` referenziert das HTML Body Element.
      - Die aktuellen Abmaße des Dokuments können Sie mit der Funktion :js:`window.getComputedStyle(<HTML Element>).height` ermitteln; geben Sie den Wert auf der Konsole aus bevor Sie das Dokument scrollen; was fällt Ihnen auf?
      - Um zu scrollen, können Sie :js:`window.scrollTo(x,y)` verwenden.
      - Um den Integer Wert eines Wertes in Pixeln zu bestimmen, können Sie :js:`parseInt` verwenden.

        (Sei der String: :js:`"100px"`, dann liefert :js:`parseInt`, den Wert :js:`100`).

   .. solution::
      :pwd: scrollTo(x,y)

      .. code:: javascript

         const h = window.getComputedStyle(document.body).height
         window.scrollTo(0,parseInt(h));



`Vergleich von Werten und implizite Typumwandlung <./code/ComparingValues.mjs>`__
------------------------------------------------------------------------------------------

.. scrollable::

   .. include:: code/ComparingValues.mjs
      :code: javascript
      :start-line: 2
      :end-before: done();
      :number-lines:
      :tab-width: 4

.. supplemental::

   NaN (Not a Number) repräsentiert das Ergebnis einer Operation die keinen sinnvollen Wert hat. Ein Vergleich mit NaN ist *immer* :js:`false`. Um zu überprüfen, ob ein Wert NaN ist muss :js:`isNaN(<Value>)` verwendet werden.



`Bedingungen und Schleifen <./code/LoopsAndConditions.mjs>`__
--------------------------------------------------------------

.. scrollable::

   .. include:: code/LoopsAndConditions.mjs
      :code: javascript
      :start-line: 2
      :end-before: done();
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 4


.. supplemental::

  Die Tatsache, dass insbesondere :js:`null` als auch :js:`undefined` falsy sind, wird of in Bedingungen ausgenutzt (z. B., :js:`if (!x)...`).



`Fehlerbehandlung <./code/Errors.js>`__
--------------------------------------------------------------

.. scrollable::

   .. include:: code/Errors.js
      :code: javascript
      :start-line: 2
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 4


.. supplemental::

   In JavaScript können während der Laufzeit Fehler auftreten, die (z. B.) in Java während des kompilierens erkannt werden.



.. class:: exercises

Übung - Bedingungen und Schleifen
------------------------------------

.. exercise:: removeNthElement

   Implementieren Sie eine Funktion, die ein Array übergeben bekommt und ein neues Array zurückgibt in dem jedes n-te Element nicht vorkommt.

   Beispiel: :js:`removeNthElement([1,2,3,4,5,6,7], 2)` :math:`\Rightarrow` :js:`[1,3,5,7]`

   - Schreiben Sie Ihren Code in eine JavaScript Datei und führen Sie diese mit Hilfe von Node.js aus.

   - Testen Sie Ihre Funktion mit verschiedenen Eingaben und lassen Sie sich das Ergebnis ausgeben (z. B. :js:`console.log(removeNthElement([1,2,3,4,5,6,7],2))`)!

   .. hint::

        Einem Array können Sie einen neuen Wert mittels :js:`push` hinzufügen:
        :js:`const a = [].push(1);`.


   .. solution::
        :pwd: _RemoveNthElement

        .. code:: javascript
            :number-lines:
            :class: copy-to-clipboard

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



.. class:: exercises

Übung - Fehlerbehandlung
------------------------------------

.. exercise:: removeNthElement mit Fehlerbehandlung

   - Erweitern Sie die Implementierung von :js:`removeNthElement` so, dass die Funktion einen Fehler wirft, wenn das übergebene Array kein Array ist oder wenn der zweite Parameter kein positiver Integer ist.

   - Testen Sie alle Fehlerzustände und fangen Sie die entsprechenden Fehler ab (:js:`catch`) und geben Sie die Nachrichten aus.

   .. solution::
      :pwd: removeNthElementWithErrorHandling

      .. code:: javascript
        :number-lines:
        :class: copy-to-clipboard

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



.. class:: exercises

Übung - Funktionen
---------------------

.. exercise:: Einfacher RPN Calculator

   Implementieren Sie einen einfachen RPN (Reverse Polish Notation) Calculator, der eine Liste von Zahlen und Operatoren (:js:`+`, :js:`-`, :js:`*`, :js:`/`) als Array entgegennimmt und das Ergebnis berechnet.

   Nutzen Sie keine :js:`if` oder :js:`switch` Anweisung, um die Operatoren zu unterscheiden. Nutzen Sie stattdessen ein Objekt. Sollte der Operator unbekannt sein, dann geben Sie eine entsprechende Fehlermeldung aus.

   Nutzen Sie node.js als Test-/Ausführungsumgebung.

   Beispiel: :js:`eval([2,3,"+",4,"*"])` :math:`\Rightarrow` :js:`20`

   .. solution::
        :pwd: _RPNCalculator+

        .. code:: javascript
            :number-lines:
            :class: copy-to-clipboard

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



`Destrukturierung <./code/Destructuring.mjs>`__ (:eng:`Destructuring`)
-------------------------------------------------------------------------

.. scrollable::

   .. include:: code/Destructuring.mjs
      :code: javascript
      :number-lines:
      :tab-width: 2
      :start-line: 2
      :end-before: done();



`JSON <./code/JSON.js>`__ (JavaScript Object Notation)
----------------------------------------------------------

.. scrollable::

   .. include:: code/JSON.js
      :code: javascript
      :number-lines:
      :tab-width: 2


.. supplemental::

   JSON requires that keys must be strings and strings must be enclosed in double quotes.



`Reguläre Ausdrücke <./code/RegularExpressions.js>`__
-------------------------------------------------------

- Eingebaute Unterstützung basierend auf entsprechenden Literalen (Strings in "/") und einer API
- inspiriert von der Perl Syntax
- Methoden auf regulären RegExps: :js:`test` (e.g., :js:`<RegExp>.test(String)`).
- Methoden auf Strings, die reguläre Ausdrücke verarbeiten: :js:`search`, :js:`match`, :js:`replace`, :js:`split`, ...

.. scrollable::

   .. include:: code/RegularExpressions.js
      :code: javascript
      :number-lines:
      :tab-width: 2



`Klassen und Vererbung <./code/Classes.js>`__
------------------------------------------------

.. scrollable::

    .. include:: code/Classes.js
        :code: javascript
        :number-lines:
        :tab-width: 2
        :end-before: class Queue {




Alles ist ein Objekt
------------------------------------------------

- :js:`this` ist ein "zusätzlicher" Parameter, dessen Wert von der aufrufenden Form abhängt
- :js:`this` ermöglicht den Methoden den Zugriff auf ihr Objekt
- :js:`this` wird zum Zeitpunkt des Aufrufs gebunden (außer bei Arrow-Funktionen, da erfolgt die Bindung zum Zeitpunkt der Definition und es wird das this aus dem umgebenden Context geerbt.)


.. scrollable::

    .. include:: code/EverythingIsAnObject.js
        :code: javascript
        :number-lines:
        :tab-width: 2



`Partial Function Application <./code/Functions_partiell.js>`__
----------------------------------------------------------------------------

.. scrollable::

   .. include:: code/Functions_partiell.js
      :code: javascript
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 2



`Prototype basierte Vererbung <./code/ObjectCreate.js>`__
-------------------------------------------------------------

.. deck::

   .. card::

      Verwendung von :js:`Object.create` zur Initialisierung der *Prototype Chain*:

      .. code:: javascript
         :number-lines:
         :class: copy-to-clipboard

         const p = { s : "p" };
         const c = Object.create(p);
         const gc = Object.create(c);

      .. image:: images/prototype_chain/object_literals_and_the_prototype_chain.svg
         :align: center
         :class: light-image

   .. card::

      Verwendung der Eigenschaften von Prototypen:

      .. code:: javascript
         :number-lines:
         :class: copy-to-clipboard

         const p = { s : "p" };
         const c = Object.create(p);
         const gc = Object.create(c);
         gc.t = "q";

      .. image:: images/prototype_chain/object_literals_and_the_prototype_chain_with_update.svg
         :align: center
         :class: light-image

      .. code:: javascript
         :number-lines: 5
         :class: copy-to-clipboard incremental

         gc.s = "gc"
         console.log(gc.s); // gc
         delete gc.s;
         console.log(gc.s); // p

   .. card::

      .. rubric:: *Pseudoclassical Inheritance*

      .. code:: javascript
         :number-lines:
         :class: copy-to-clipboard

         // constructor for Person objects:
         function Person(name, title){ this.name = name; this.title = title; }
         Person.prototype.formOfAddress = function (){
            const foa = "Dear ";
            if(this.title){ foa += this.title+" "; }
            return foa + this.name;
         }
         function Student(name, title, id, email) {
            Person.call(this, name, title); // super constructor call
            this.id = id;
            this.email = email;
         }
         Student.prototype = Object.create(Person.prototype);
         Student.prototype.constructor = Student;

         const aStudent = new Student("Emily Xi", "Mrs.", 12441, 'emily@xi.de');

   .. card::

      .. container:: scale-on-hover 

            .. image:: images/prototype_chain/pseudoclassical_Inheritance.svg
               :align: center
               :class: light-image

      .. rubric:: Objektabhängigkeiten

      .. code:: javascript
         :number-lines:
         :class: copy-to-clipboard

         function Person(name, title){ … }
         Person.prototype.formOfAddress = function (){ … }

         function Student(name, title, id, email) { … }
         Student.prototype = Object.create(Person.prototype);
         Student.prototype.constructor = Student;

         const p = new Person(…);
         const s = new Student(…);



.. supplemental::

   Die Eigenschaft :js:`prototype` einer Funktion (:js:`F`) verweist auf das Objekt, dass als Prototype (:js:`__proto__`) verwendet wird, wenn die Funktion als Konstruktor verwendet wird. D. h. im Falle einer Instantiierung von :js:`F` (d. h. :js:`const newF = new F()`) wird das Objekt, das durch :js:`F.prototype` referenziert wird, als Prototype (:js:`newF.__proto__`) des neu erstellten Objekts (:js:`newF`) verwendet.

   .. include:: code/Prototypes.js
      :code: javascript
      :number-lines:
      :class: copy-to-clipboard
      :tab-width: 4



Praktische Verwendung von Prototypen basierter Vererbung
-------------------------------------------------------------

.. scrollable::

   .. include:: code/Array.prototype.js
      :code: javascript
      :number-lines:
      :class: copy-to-clipboard
      :tab-width: 4



Grundlagen von ECMAScript Modulen
------------------------------------------------

.. scrollable::

   `Queue.mjs <./code/Queue.mjs>`__ exportiert die Klasse Queue

   .. include:: code/Queue.mjs
      :code: javascript
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 4

   `log.mjs <./code/log.mjs>`__ verwendet (:js:`import`) die Klasse Queue und exportiert Funktionen zum Loggen

   .. include:: code/log.mjs
      :code: javascript
      :number-lines:
      :class: code far-far-smaller copy-to-clipboard
      :tab-width: 4
      :end-before: /**


.. supplemental::

   ECMAScript Module verwenden immer den *strict mode*.

   Import Statements erlauben das selektierte importieren als auch das Umbenennen von importierten Elementen (z. B., :js:`import { Queue as Q } from "./Queue.mjs";`).




`DOM Manipulation <./code/DOM.html>`__
------------------------------------------------

.. scrollable::

   .. include:: code/DOM.html
      :code: html
      :number-lines:
      :class: copy-to-clipboard
      :tab-width: 4



`Minimaler Server mit Express JS <./code/UsersServer.mjs>`__
--------------------------------------------------------------

.. scrollable::

   .. include:: code/UsersServer.mjs
      :code: javascript
      :number-lines:
      :class: copy-to-clipboard
      :tab-width: 4

.. supplemental::

   `Express <https://expressjs.com>`__ ist ein minimalistisches Web-Framework für Node.js, das die Entwicklung von Webanwendungen vereinfacht. Die Installation kann über einen Packagemanager erfolgen.

   Installieren Sie (z. B.) pnpm (https://pnpm.io/) und nutzen Sie danach pnpm, um die benötigten Module zu installieren:

   .. code:: bash

      $ pnpm init
      $ pnpm install express

   Danach starten Sie Ihren Server mit:

   .. code:: bash

      node --watch UsersServer.mjs



`Interaktion mit Server mit Hilfe von Fetch <./code/Users.html>`__
--------------------------------------------------------------------

.. scrollable::

   .. include:: code/Users.html
      :code: html
      :number-lines:
      :class: copy-to-clipboard
      :tab-width: 4

.. TODO add (more :-)) explanations related to Promises, async-await, etc.



Beispiel - Rumpf einer einfachen Webanwendung ("Quizzy")
------------------------------------------------------------

Im Folgenden verwenden wir zur Client-/Server-Kommunikation insbesondere Websockets.

.. scrollable::

   **Server**

   .. include:: code/quizzy/game.js
      :code: javascript
      :number-lines:
      :class: copy-to-clipboard
      :tab-width: 4

   **Client - Players**

   .. include:: code/quizzy/player.html
      :code: html
      :number-lines:
      :class: copy-to-clipboard
      :tab-width: 4

   **Client - Admin**

   .. include:: code/quizzy/admin.html
      :code: html
      :number-lines:
      :class: copy-to-clipboard
      :tab-width: 4


.. supplemental::

   Die Implementierung dient nur dazu die grundlegenden Konzepte zu verdeutlichen. Es fehlen **viele** Aspekte wie z. B., Sicherheit.



.. TODO basic authentication and JSON Web Tokens (JWT)

Authentifizierung mit JWT (und Express)
------------------------------------------------

.. scrollable::

   Im Folgenden wird primär die Verwendung eines JWTs zur Authentifizierung von Benutzern demonstriert.

   .. class:: peripheral

      Die initiale Authentifizierung, die im folgenden Beispiel über ein per get-Request übermittelten Benutzernamen und Passwort erfolgt, ist **nicht sicher**. In einer realen Anwendung sollte für die initiale Authentifizierung ein sicherer Mechanismus verwendet werden. Eine Möglichkeit wäre z. B. die Verwendung von DIGEST Authentication (nicht empfohlen bzw. nur für einfachste Fälle). Sinnvoll wäre Basic Authentication *in Verbindung mit HTTPS* oder zum Beispiel der Einsatz von OAuth.

   .. warning::

      *Basic Authentication* ohne HTTPS ist nicht sicher!

      D.h. *Basic Authentication* ist genauso unsicher wie die hier gezeigte Lösung für die initiale Authentifizierung.

   **Server**

   .. include:: code/authentication/server.mjs
      :code: javascript
      :number-lines:
      :class: copy-to-clipboard
      :tab-width: 4

   **Client (JavaScript)**

   .. include:: code/authentication/admin.js
      :code: javascript
      :number-lines:
      :class: copy-to-clipboard
      :tab-width: 4

.. supplemental::

   Alle Quellen:

   `admin.js <./code/authentication/admin.js>`__

   `admin.html <./code/authentication/admin.html>`__

   `admin.css <./code/authentication/admin.css>`__

   `server.mjs <./code/authentication/server.mjs>`__

   `start_server.sh <./code/authentication/start_server.sh>`__

   `Users.json <./code/authentication/users.json>`__



.. TODO add a discussion of "ADDCSSClassOnHover"

Referenzen
------------------------------------------------

- `HTML DOM API  <https://developer.mozilla.org/en-US/docs/Web/API/HTML_DOM_API>`__




.. class:: new-section transition-scale

Web Komponenten
-------------------

.. class:: subtitle

    Work in progress!



Quizzy
------------------------------------------------

Bei der Quizzy Komponenten handelt es sich um eine (ganz) einfache Client-Server basierte Komponente für Quizzes. Die Komponente besteht aus der Definition der Web Komponente und einem Server. Die Komponente und der Server kommunizieren über Web Sockets. Der Server führt in Hinblick auf Cross-origin requests keine besonderen Prüfungen durch!

.. warning::

    Es handelt sich nur um einen minimalen Prototyp, der lediglich der Demonstration von Webkomponenten und der Kommunikation selbiger mit Servern dient. Es existiert keinerlei Sicherheit!



Quizzy - Server Code (server.js)
---------------------------------

.. scrollable::

    .. include:: code/quizzy-component/server.js
        :code: JavaScript
        :number-lines:
        :class: copy-to-clipboard



Quizzy - Client Code (quizzy.js)
---------------------------------


.. scrollable::

    .. include:: code/quizzy-component/quizzy.js
        :code: JavaScript
        :number-lines:
        :class: copy-to-clipboard




Verwendung der Quizzy Komponente
----------------------------------------------------------

.. code:: html

   <script src="code/quizzy-component/quizzy.js"></script>
   <div onclick="event.stopPropagation();">
        <ld-quizzy ws-url="ws://localhost:5557">
            <!-- When used as a real component, we have to either specify the
            questions here (somehow encrypted) and send them to the server when
            required, or refer to a set of questions stored on the server by
            means of a parameter/attribute... -->
       </ld-quizzy>
   </div>

.. raw : : html

    <script src="code/quizzy-component/quizzy.js"></script>
    <div onclick="event.stopPropagation();">
        <ld-quizzy ws-url="ws://localhost:5557" set="capitals"></ld-quizzy>
    </div>
