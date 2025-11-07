.. meta::
   :lang: de
   :author: Michael Eichberg
   :keywords: "Programmierung", "Java", "Arrays", "Kontrollfragen"
   :description lang=de: Kontrollfragen zu Java Arrays
   :id: lecture-prog-java-arrays-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Java Arrays - Kontrollfragen
===================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.1.2



.. class:: exercises

Kontrollfragen
----------------

.. deck:: 

   .. card::

      .. exercise:: Welche der folgenden Deklarationen sind gültig?

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            int[] a;
            int b[];
            int[] b[];
            int c[] = new int[10];
            var f = new float[][]{null,null};
            var d = new int[10];
            final double[] e = {1.0d, 1, 1l};
            var e = new int[][10];

         .. solution::
            :pwd: int_und_so

            1. ja
            2. ja
            3. ja (die Syntax ist ungewöhnlich, aber korrekt)
            4. ja            
            5. ja
            6. ja
            7. ja
            8. nein (Syntaxfehler!)
            

   .. card::

      .. exercise:: Wie kann man über die Elemente in einem Array iterieren?

         .. solution::
            :pwd: es gibt mehrere Wege

            Im Wesentlichen mit Schleifen: z.B: For und For-each Schleifen, aber auch (do-)While Schleifen. 

            Weiterhin ist es möglich mit einer (rekursiven) Methode zu iterieren.

   .. card::

      .. exercise:: Was ist die Ausgabe?

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            int[] a = new int[3];
            for (int i = 0; i < a.length; i++) {
                IO.println(a[i]);
            }

         .. solution::
            :pwd: BezeichNer

            ::

               0
               0
               0

   .. card::

      .. exercise:: Was ist die Ausgabe?

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            final int[] a = {2,6,12};
            for (int i = 0; i < a.length-1; i++) {
                IO.println(a[i]);
            }

         .. solution::
            :pwd: BezeichneR

            ::

               2
               6


   .. card::

      .. exercise:: Welche Werte enthalten die Arrayelemente und was ist die Ausgabe?

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            final int[] a = new int[10];
            a[0] = 0;
            a[1] = 1;
            for (int i = 2; i < a.length; i++) {
                a[i] = i + a[i-1];
            }
            IO.println(a[9]);

         .. solution::
            :pwd: 45_-was-sonst

            ::

               45

   .. card::

      .. exercise:: Welche Zeile ist korrekt?

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            int a[][] = new int[2][2];
            int b[][] = new int[2]{1,2,3,4};
            int b[][] = { new int[2], new int[3], new int[4], new int[6] };

         .. solution::
            :pwd: multidim_array

            1. ja
            2. nein (Syntaxfehler!)
            3. ja

   .. card::

      .. exercise:: Finden Sie den Fehler:

         .. code:: java
            :class: copy-to-clipboard
            :number-lines:

            int[] a = new int[Integer.parseInt(IO.readln("Dimension: "))];
            for (int i = 0; i < a.length; i++) {
                a[i] = i;
            }

         .. solution::
            :pwd: array_index

            Der Index des Arrays beginnt bei 0 und endet bei a.length-1. Der Code führt zu einer :java:`ArrayIndexOutOfBoundsException`.
