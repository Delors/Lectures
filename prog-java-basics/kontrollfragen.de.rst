.. meta::
   :version: renaissance
   :lang: de
   :author: Michael Eichberg
   :keywords: "Programmierung", "Java", "Variablen", "Methoden"
   :description lang=de: Kontrollfragen zu Einführung in die Programmierung mit Java
   :id: lecture-prog-java-basics-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Einführung in die Programmierung mit Java - Wiederholung
===========================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.2



.. class:: new-section
   
Grundlagen
------------



.. class:: exercises

Kontrollfragen
----------------

.. deck:: 

   .. card::

      .. exercise:: Welche primitiven Datentypen kennen wir?

         .. solution::
            :pwd: int_und_so

            Wir kennen die primitiven Datentypen :java:`byte`, :java:`short`, :java:`int`, :java:`long`, :java:`float`, :java:`double`, :java:`boolean`, :java:`char`.

   .. card::

      .. exercise:: Was sind Literale?

         .. solution::
            :pwd: lits

            Konstante Werte, die direkt in den Code geschrieben werden.

   .. card::

      .. exercise:: Welche der folgenden Bezeichner sind für Variablen gültig?

         1. :java:`fooBar`
         2. :java:`BarFoo`
         3. :java:`_fooBar`
         4. :java:`1fooBar`
         5. :java:`fooBar1`
         6. :java:`fooBar!`
         7. :java:`$fooBar`
         8. :java:`$_BarFoo`

         .. solution::
            :pwd: BezeichNer

            1. ja
            2. ja
            3. ja
            4. nein
            5. ja
            6. nein
            7. ja
            8. ja

   .. card::

      .. exercise:: Welche der folgenden Bezeichner sollte man verwenden?

         1. :java:`gewinn`
         2. :java:`Gewinn`
         3. :java:`_private_i`
         4. :java:`i`
         5. :java:`$i`
         6. :java:`_i`

         .. solution::
            :pwd: BezeichNer_Teil2


            1. ja
            2. nein
            3. (nein)
            4. ja
            5. nein
            6. (nein)

   .. card::

      .. exercise:: Welchen Namen würden Sie für eine Konstante verwenden?

         1. :java:`ISOLAENDERCODE`
         2. :java:`ISO_LÄNDERCODE`
         3. :java:`ISO_LAENDERCODE`
         4. :java:`ISO_Ländercode`
         5. :java:`ISO_Laendercode`
         6. :java:`iso_Laendercode`

         .. solution::
            :pwd: BezeichNer_Teil2

            Wenn die Konstante einen deutschen bekommen  soll, dann sollte man dennoch auf Umlaute verzichten. Daher ist 3. die beste Wahl.

   .. card::

      .. exercise:: Welchen Typ hat die Variable x in folgendem Code?

         1. :java:`var x = 1;`
         2. :java:`var x = 1.0;`
         3. :java:`var x = '1';`
         4. :java:`var x = 1f;`
         5. :java:`var x = 2F;`
         6. :java:`var x = "x";`

         .. solution::
            :pwd: MeinTypDeinTyp

            1. :java:`int`
            2. :java:`double`
            3. :java:`char`
            4. :java:`float`
            5. :java:`float`
            6. :java:`String`

   .. card::

      .. exercise:: Wie viele Bits hat ein int?

         1. 8
         2. 16
         3. 24
         4. 32
         5. 40
         6. 48

         .. solution::
            :pwd: 3_2_Bits

            32

   .. card::

      .. exercise:: Wie ist der Wertebereich von byte?

         1. 0 bis 255
         2. -128 bis 128
         3. -128 bis 127
         4. -127 bis 127
         5. -127 bis 128

         .. solution::
            :pwd: Es gibt nur eine Antwort

            -128 bis 127

   .. card::

      .. exercise:: Was passiert bei den folgenden Typumwandlungen?

         1. :java:`int i = 42; byte b = (byte) i;`
         2. :java:`int i = 255; byte b = (byte) i;`
         3. :java:`int i = 256; byte b = (byte) i;`

         .. solution::
            :pwd: 3_2_Bits

            1. b = 42
            2. b = -1
            3. b = 0

   .. card::

       .. exercise:: Warum ist der folgende  Ausdruck wahr?

         :java:`(long) ((float) (Long.MAX_VALUE - Integer.MAX_VALUE)) == Long.MAX_VALUE;`

         .. remark::

               Rein mathematisch betrachtet - d. h. ohne Betrachtung von Typen und Typumwandlungen - wäre dieser natürlich falsch.

         .. solution::
            :pwd: VerlusteSindDA!

            Durch die Typkonvertierung wird der Wert von :java:`Long.MAX_VALUE - Integer.MAX_VALUE` in einen :java:`float` umgewandelt. Da ein float nur 24 Bit für die Mantisse hat kommt es zu einem Präzisionsverlust. Der Wert wird also verändert. In (2) wird der Wert als :java:`long` berechnet und ist daher korrekt.

   .. card::

       .. exercise:: Ist die Länge eines Strings gleich der Anzahl sichtbarer Zeichen?
         :formatted-title: Ist die Länge eines Strings gleich der Anzahl *sichtbarer* Zeichen?


         .. solution::
            :pwd: x_plus_plus

            Nein - es gibt Zeichen (zum Beispiel Emojis), die mehrere Zeichen (:java:`char`\ s) benötigen.

   .. card::

       .. exercise:: Wie fügen Sie in einen String ein Anführungszeichen ein?

         .. solution::
            :pwd: backslash

            Sie verwenden ein Backslash: :java:`"\""`.

   .. card::

       .. exercise:: Muss ich bei der Variablendeklaration den Typ explizit angeben?

         .. solution::
            :pwd: nein...

            Nein, in Java kann der Typ auch implizit durch den Compiler ermittelt werden, wenn die Variable auch direkt initialisiert wird.

   .. card::

       .. exercise:: Wie deklariert man eine Konstante?

         Sollte man Werte, die man nicht ändern möchte immer als Konstanten deklarieren?

         .. solution::
            :pwd: final var

            Durch die Verwendung des Schlüsselwortes :java:`final`.

            Ja - es ist eine gute Praxis, Werte, die sich nicht ändern sollen, als Konstanten zu deklarieren.

   .. card::

       .. exercise:: Wie ist der Operator für die Modulorechnung in Java?

         (D. h. wenn Sie eine Restwertberechnung in Java durchführen wollen.)

         .. solution::
            :pwd: _-%-_

            Das Prozentzeichen :java:`%`.

   .. card::

       .. exercise:: Wie sieht der ternäre Operator in Java aus?

         .. solution::
            :pwd: if?then:else

            Es ist das Fragezeichen :java:`?` und der Doppelpunkt :java:`:`. Beispiel :java:`x > 18 ? "alt" : "jung"`.

   .. card::

       .. exercise:: Welchen Wert haben die folgenden Ausdrücke?

         :java:`x` hat vor der jeweiligen Auswertung den Wert 5.

         (1) :java:`x++`
         (2) :java:`++x`
         (3) :java:`x += 1`
         (4) :java:`x = (x = x - 2 ) + 3 * 4`
         (5) :java:`x = x = x - 2 + 3 * 4`
         (6) :java:`(x = (x = x - 2 ) + 3) * 4`
         (7) :java:`x >= 5 || 2 / (x - 5) == 0`
         (8) :java:`x >= 5 | 2 / (x - 5) == 0`
         (9) :java:`x << 1 >> 2`

         .. solution::
            :pwd: mal_so_mal_so

            (1) 5, (2) 6, (3) 6, (4) 15, (5) 15, (6) 24, (7) true, (8) Division durch 0, (9) 2

   .. card::

       .. exercise:: Was stellt ein Block in Hinblick auf eine Variable dar?

         .. solution::
            :pwd: gibts_mich_oder_nicht

            Den Gültigkeitsbereich einer Variable.



.. class:: new-section

Schleifen
------------


.. class:: exercises

Kontrollfragen
----------------

.. deck::

   .. card::

       .. exercise:: Können while- und for-Schleifen ineinander umgewandelt werden?
         :formatted-title: Können :java:`while`- und :java:`for`-Schleifen ineinander umgewandelt werden?

         .. solution::
            :pwd: einfach_ja

            Ja.

   .. card::

       .. exercise:: Wie unterscheidet sich eine do-while- von einer while-Schleife?
         :formatted-title: Wie unterscheidet sich eine :java:`do`-:java:`while`- von einer :java:`while`-Schleife?

         .. solution::
            :pwd: einmal wird die do-while ausgefuehrt

            Eine do-while-Schleife wird mindestens einmal ausgeführt, während eine while-Schleife nur dann ausgeführt wird, wenn die Bedingung wahr ist.

   .. card::

       .. exercise:: Schleifen und Variablen - wie ist die Ausgabe auf der JShell?
         :formatted-title: Schleifen und Variablen - wie ist die Ausgabe auf der *JShell*?

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            int i = 0;
            for (int i = 0; i < 10; i++) {
                if (i == 5) {
                    break;
                }
            }
            IO.println(i);

         .. solution::
            :pwd: i ist 0

            Die Ausgabe ist ``0``. (Die Variable ``i`` in der Schleife ist eine andere Variable als die Variable ``i``, die vor der Schleife deklariert wurde. Achtung: in einem Java Skript würde dies zu einem Fehler führen.)

   .. card::

       .. exercise:: Schleife mit break - wie ist die Ausgabe?

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            int i = 0;
            for (; i < 10; i++) {
                if (i == 5) {
                    break;
                }
            }
            IO.println(i);

         .. solution::
            :pwd: i==5

            Die Ausgabe ist ``5``.

            (Die Update Anweisung wird nicht ausgeführt, wenn die Schleife durch ein :java:`break` beendet wird.)

   .. card::

       .. exercise:: Ganz einfache Schleife - wie ist die Ausgabe?

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            int i = 10;
            for (; i < 10; i++) {
               IO.println(i);
            }

         .. solution::
            :pwd: nix_da

            Die Schleife wird nicht betreten.

   .. card::

       .. exercise:: Schleife mit continue - wie ist die Ausgabe?

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            int i = 0;
            for (; i < 10; i++) {
                if (i % 2 == 0) {
                    continue;
                }
                IO.println(i);
            }

         .. solution::
            :pwd: ungerade

            Es werden die ungeraden Zahlen von 1 bis einschließlich 9 ausgegeben.

   .. card::

       .. exercise:: Verschachteltet Schleifen - wie ist die Ausgabe?

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            int i = 0;
            outer : for (; i < 10; i++) {
                if (i % 2 == 0)
                    continue;
                IO.println(i);
                for (int j = 1; j < 10; j++) {
                    if (j % 3 == 0)
                        continue outer;
                    IO.println(i + " " + j);
                }
            }
            IO.println(i);

         .. solution::
            :pwd: 1__1_1-und_so_weiter

            Ausgabe:

            ::

               1
               1 1
               1 2
               3
               3 1
               3 2
               5
               5 1
               5 2
               7
               7 1
               7 2
               9
               9 1
               9 2

   .. card::

       .. exercise:: Verschachteltet Schleifen - wie ist die Ausgabe?

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            outer : for (int i = 0; i < 10; i++) {
               if (i % 2 == 0) {
                 i = 10;
                 continue outer;
               }
               IO.println(i);
               for (int j = 1; j < 10; j++) {
                  if (j % 3 != i % 5)
                     break;
                  IO.println(i + " " + j);
            }  }

         .. solution::
            :pwd: 1_3_5_oder_so...

            Ausgabe:

            *<keine>*



.. class:: new-section

Funktionen
------------


.. class:: exercises

Kontrollfragen
----------------

.. deck::

   .. card::

       .. exercise:: Rekursive Funktion

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            int f(int n) {
               if (n == 0) return 0; return n + f(n-1);
            }

         .. class:: incremental

         - Was berechnet diese Funktion?
         - Ist diese Funktion effizient?
         - Ist eine Lösung mit for-Schleife besser?

         .. solution::
            :pwd: Summe-rekursiv

            - Die Summe der Zahlen von 1 bis n.
            - Nein, da die Funktion rekursiv ist und daher für große Werte von n - bei einigen Programmiersprachen (insbesondere Java, Python, etc.) - zu einem Stackoverflow führen wird.
            - Jein - sie ist nur um einen Konstanten Faktor schneller, aber sie braucht keinen Stack.

   .. card::

       .. exercise:: Funktion mit „Tail-Call“

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            /* private */ int f(int n, int sum) {
               if (n == 0) return sum; return f(n-1,n+sum);
            }
            int f(int n) { return f(n,0); }

         .. class:: incremental

         - Was berechnet diese Funktion?
         - Ist diese Funktion effizient(er)?

         .. solution::
            :pwd: Summe-optimierbar

            - Die Summe der Zahlen von 1 bis n.

            - Dieser Code könnte in der Programmiersprache Scala verwendet werden, um die Summe der Zahlen von 1 bis n zu berechnen. In Scala wird der rekursive Aufruf so optimiert, dass es keinen Stackoverflow gibt; in Java ist dies nicht möglich.

               .. code:: scala
                  :class: copy-to-clipboard
                  :number-lines:

                  import scala.annotation.tailrec

                  object SumN {

                        @tailrec def f(n: Int,sum: Int): Int = { if(n == 0) sum else f(n-1,n+sum); }

                        @main def main(): Unit = {
                              println(f(100_000,0));
                        }
                  }

   .. card::

       .. exercise:: Wie werden Parameter übergeben?

         .. solution::
            :pwd: Call-by-value

            Call-by-Value (Java)

   .. card::

       .. exercise:: Wie bewerten Sie folgende Kommentierung?

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            /**
             * Testet ob eine Zahl eine Primzahl ist.
             *
             * Die Laufzeit ist O(n/4).
             *
             * @param n Eine positive ganze Zahl.
             * @return true, wenn n eine Primzahl ist, sonst false.
             */
            boolean isPrim(int n) {
               ...
            }

         .. solution::
            :pwd: "Ausreichend"

            Ausreichend - obwohl die Frage ist, was bei negativen Zahlen passiert!

   .. card::

       .. exercise:: Ist der Kommentar ausreichend?

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            /**
             * Computes the absolute value of the argument.
             *
             * @param a - the argument whose absolute value is to be
             *            determined
             * @return the absolute value of the argument.
             */
            double abs(double a) { ... }

         .. solution::
            :pwd: Nicht schlecht, aber ...

            Es fehlt eine Diskussion der besonderen Werte von :java:`a` (NaN, +0.0, -0.0, Infinity).

   .. card::

       .. exercise:: Ist die Kommentierung hier ausreichend?

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            /**
             * Returns the absolute value of an int value.
             * If the argument is not negative, the argument is returned.
             * If the argument is negative, the negation of the argument
             * is returned.
             *
             * @param a - the argument whose absolute value is to be
             *            determined
             * @return the absolute value of the argument.
             */
            long abs(long a) { ... }

         .. solution::
            :pwd: Nicht schlecht, aber ...

            Der Wertebereich von Long ist nicht symmetrisch! Es stellt sich also unmittelbar die Frage was bei Long.MIN_VALUE passiert. (Die Antwort ist Long.MIN_VALUE.)

   .. card::

      .. exercise:: Sind Java Assertions (assert) in Java immer aktiv?
         :formatted-title: Sind Java Assertions (:java:`assert`) in Java immer aktiv?

         .. solution::
            :pwd: Nope

            Sie müssen extra aktiviert werden.

   .. card::

      .. exercise:: Wofür sollten Assertions verwendet werden?

         1. Zur Validierung von Eingabeparametern?
         2. Zur Validierung von Rückgabewerten?
         3. Zur Validierung von internen Invarianten?

         .. solution::
            :pwd: so oder so

            1. Nur dann, wenn die Funktion eine private Funktion ist, die kein Teil der öffentlichen API ist! Sollte die Eingabe auf Nutzereingaben zurückzuführen sein, dann sind Java Assertions der falsche Mechanismus.
            2. Ja
            3. Ja

   .. card::

       .. exercise:: Beschreiben Sie die Ausgabe des Programms

         .. code:: java
            :class:  copy-to-clipboard
            :number-lines:

            int width = 20;
            int height = 10;
            for (int i = 0; i < width; i++) IO.print("-");
            IO.println("");
            for (int i = 0; i < height - 2; i++) {
                  IO.print("|");
                  for (int j = 0; j < width - 2; j++) IO.print(" ");
                  IO.println("|");
            }
            for (int i = 0; i < width; i++) {
                  IO.print("-");
            }
            IO.println("");

         .. solution::
            :pwd: ein Rechteck

            In Abhängigkeit von :java:`width` und :java:`height` wird ein Rechteck gezeichnet.
