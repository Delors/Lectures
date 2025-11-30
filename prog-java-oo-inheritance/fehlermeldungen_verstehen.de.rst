.. meta::
   :lang: de
   :author: Michael Eichberg
   :keywords: "Programmierung", "Java", "Compiler", "Fehlermeldungen"
   :description lang=de: Kontrollfragen zu Objektorientierter Programmierung in Java
   :id: lecture-prog-java-oo-fehlermeldungen-verstehen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Fehlermeldungen des Java Compilers (Temurin Java 25) verstehen 
================================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0


.. class:: new-section

Einleitung
---------------------------------



Compiler und fehlermeldungen
-------------------------------

- Fehlermeldungen des Compilers zu verstehen ist eine essentielle Fähigkeit und oft auch sehr einfach, da die Compiler sehr präzise geworden sind. 

- In diesem Kapitel werden ausgewählte Fehlermeldungen des Java Compilers (Temurin Java 25) vorgestellt. 

- Mit ein bisschen Übung werden Sie in der Lage sein die Fehlermeldungen schnell zu verstehen und die Fehler in Ihrem Code zu beheben. (Schneller als das Kopieren in den Chat Ihres AI-Assistenten.)



.. class:: new-section

Fehlermeldungen des Java 25 Compilers 
---------------------------------------------



.. class:: exercises

Fehlerhafte Programme und ihre Meldungen
-------------------------------------------------------

.. deck::

   .. card::

      .. exercise:: Falscher Typ

         .. code:: java
            :number-lines:
            
            int i = 0;

            void main() {
                byte b = i;
                IO.println(b);
            }
         
         .. code:: bash
            :number-lines:
            :class: incremental

            Bugs.java:4: error: incompatible types: possible lossy conversion from int to byte
               byte b = i;
                        ^

         .. solution::
            :pwd: explicit


            Die Variable :java:`i` ist vom Typ :java:`int`. Der Typ byte kann nur Werte von -128 bis 127 speichern. Der Typ :java:`int` kann jedoch viel größere Werte speichern. Daher ist es nicht möglich einen Wert vom Typ :java:`int` *implizit* in eine Variable vom Typ :java:`byte` zu speichern.
   
   .. card::

      .. exercise:: Array Indizes

         .. code:: java
            :number-lines:
            
            int[] a = new int[10];

            void main(String[] args) {
               long b = Integer.parseInt(args[0]);
               IO.println(a[b]);
            }
         
         .. code:: bash
            :number-lines:
            :class: incremental

            Bugs.java:5: error: incompatible types: possible lossy conversion from long to int
               IO.println(a[b]);
                            ^

         .. solution::
            :pwd: index_has_to_be_int

            Der Index für Arrays in Java muss vom Typ :java:`int` sein. In der Methode :java:`main` wird jedoch eine Variable vom Typ :java:`long` als Index verwendet. Daher kann der Wert nicht als Index verwendet werden.

   .. card::

      .. exercise:: Syntaxfehler

         .. code:: java
            :number-lines:
 
            void printElement(int[] a, long b) throws IOException {
               IO.println(a[(int) b]);
               threw new IOException("Simulierte Ausnahme");
            }

            void main(String[] args) {
               long b = Integer.parseInt(args[0]);
               int[] a = { 10, 20, 30 };
               printElement(a, b);
            }

         .. code:: bash
            :number-lines:
            :class: incremental

            Bugs.java:3: error: not a statement
               threw new IOException("Simulierte Ausnahme");
               ^
            Bugs.java:3: error: ';' expected
               threw new IOException("Simulierte Ausnahme");
                           ^

         .. solution::
            :pwd: doppeltes_thrown

            In Java wird das Schlüsselwort :java:`throw` verwendet, um eine Ausnahme zu werfen. In der Methode :java:`printElement` wurde jedoch fälschlicherweise :java:`threw` verwendet. 
            
            Die zweite Fehlermeldung ist bogus. Da :java:`threw` kein gültiges Schlüsselwort ist, interpretiert der Compiler die Zeile als unvollständige Anweisung.

   .. card::

      .. exercise:: Evolution von Java

         .. code:: java
            :number-lines:
            
            void printElement(int[] a, long b) throws IOException {
               IO.println(a[(int) b]);
               throw new IOException("Simulierte Ausnahme");
            }

            void main(String[] args) throws IOException {
               int[] a = { 10, 20, 30 };
               printElement(a, Integer.parseInt(args[0]););
            }
         
         .. code:: bash
            :number-lines:

            Bugs.java:1: error: class, interface, enum, or record expected
            void printElement(int[] a, long b) throws IOException {
            ^
            Bugs.java:3: error: class, interface, enum, or record expected
               throw new IOException("Simulierte Ausnahme");
               ^
            [... more errors ...]   

         .. solution::
            :pwd: declared_Exception

            Die Fehlermeldung ist auf eine veraltet Version von Java (zum Beispiel Java 18 oder älter) zurückzuführen, die keine Methoden außerhalb von Klassen erlaubt. Erst in neueren Versionen von Java ist dies erlaubt (sogenannter Skript-Modus). 


   .. card::

      .. exercise:: static Kontext
         :formatted-title: :java:`static` Kontext

         .. code:: java
            :number-lines:

            public class Bugs {

               void printElement(int[] a, long b) {
                  System.out.println(a[(int) b]);
               }

               public static void main(String[] args) {
                  int[] a = { 10, 20, 30 };
                  printElement(a, Integer.parseInt(args[0]));
               }
            }

         .. code:: bash
            :number-lines:
            :class: incremental

            Bugs.java:10: error: non-static method printElement(int[], long) cannot 
                                 be referenced from a static context
               printElement(a, Integer.parseInt(args[0]));
               ^
         .. solution::
            :pwd: static_context

            Die Methode :java:`main` ist statisch, d. h. sie gehört zur Klasse und nicht zu einer Instanz der Klasse. Die Methode :java:`printElement` ist jedoch nicht statisch und gehört daher zu einer Instanz der Klasse. Daher kann die Methode :java:`printElement` nicht direkt aus der statischen Methode :java:`main` aufgerufen werden.

   .. card::

      .. exercise:: Definition von Klassen

         .. code:: java
            :number-lines:

            public Class Util {
               void printElement(int[] a, long b) {
                  System.out.println(a[(int) b]);
            }  }
            public class Bugs {
               public static void main(String[] args) {
                  printElement(new int[] { 10, 20, 30 }, Integer.parseInt(args[0]));
            }  }

         .. code:: bash
            :number-lines:
            :class: incremental

            Bugs.java:1: error: class, interface, annotation type, enum, 
                                record, method or field expected
            public Class Util {
                   ^
            Bugs.java:3: error: class, interface, annotation type, enum, 
                                record, method or field expected
               }  }
                  ^

         .. solution::
            :pwd: class_not_lowercase

            Das Schlüsselwort :java:`class` wird in Java immer mit einem Kleinbuchstaben geschrieben.
            
            Die zweite Fehlermeldung ist bogus. Da der Compiler die Klasse nicht erkennen kann, interpretiert er die schließende geschweifte Klammer als fehlerhaft.


   .. card::

      .. exercise:: No Symbol
         :formatted-title: *No Symbol*

         .. code:: java
            :number-lines:

            public class Util {
               static void printElement(int[] a, long b) {
                  System.out.println(a[(int) b]);
               }
            }
            public class Bugs {
               public static void main(String[] args) {
                  int[] a = { 10, 20, 30 };
                  printElement(a, Integer.parseInt(args[0]));
               }
            }

         .. code:: bash
            :number-lines:
            :class: incremental

            Bugs.java:101
            : error: cannot find symbol
                  printElement(a, Integer.parseInt(args[0]));
                  ^
            symbol:   method printElement(int[],int)
            location: class Bugs

         .. solution::
            :pwd: method_in_Util

            Die Methode :java:`printElement` ist eine Methode der Klasse :java:`Util`. Daher muss sie mit dem Klassennamen aufgerufen werden, also :java:`Util.printElement(...)`.

   .. card:: overlay center-content

      .. attention::
         :class: width-50 backdrop-blur float-right

         Die Definition von mehreren Klassen in einer Datei ist nur in Java Skripten erlaubt (Java 19 und neuer).
