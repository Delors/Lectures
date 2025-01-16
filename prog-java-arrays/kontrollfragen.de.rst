.. meta::
   :version: genesis
   :lang: de
   :author: Michael Eichberg
   :keywords: "Programmierung", "Java", "Arrays"
   :description lang=de: Kontrollfragen zu Java Arrays
   :id: lecture-prog-java-arrays-kontrollfragen
   :first-slide: last-viewed
   :exercises-master-password: WirklichSchwierig!
    
.. |html-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html
.. |pdf-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html.pdf
.. |at| unicode:: 0x40
.. |qm| unicode:: 0x22 

.. role:: incremental
.. role:: appear
.. role:: eng
.. role:: ger
.. role:: dhbw-red
.. role:: green
.. role:: the-blue
.. role:: minor
.. role:: obsolete
.. role:: line-above
.. role:: smaller
.. role:: far-smaller
.. role:: monospaced
.. role:: copy-to-clipboard
.. role:: kbd
.. role:: java(code)
   :language: java



.. class:: animated-symbol 

Java Arrays
===================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0



Kontrollfragen
----------------

.. container:: scrollable

   .. class:: incremental long-list

   1. \
   
      .. exercise:: Welche der folgenden Deklarationen sind gültig?

         .. code:: java
            :class: far-smaller copy-to-clipboard

            int[] a;
            int b[];
            int c[] = new int[10];
            var d = new int[10];
            final double[] e = {1.0d, 1, 1l};

         .. solution::
            :pwd: int_und_so

            Wir kennen die primitiven Datentypen :java:`byte`, :java:`short`, :java:`int`, :java:`long`, :java:`float`, :java:`double`, :java:`boolean`, :java:`char`.

   2. 

      .. exercise:: Wie kann man über die Elemente in einem Array iterieren?

         .. solution::
            :pwd: es gibt zwei Wege

            For und For-each Schleifen.   

   3. 

      .. exercise:: Was ist die Ausgabe?

         .. code:: java
            :class: far-smaller copy-to-clipboard

            int[] a = new int[3];
            for (int i = 0; i < a.length; i++) {
                System.out.println(a[i]);
            }



         .. solution::
            :pwd: BezeichNer

            ::

               0
               0
               0


   4. 

      .. exercise:: Was ist die Ausgabe?

         .. code:: java
            :class: far-smaller copy-to-clipboard

            final int[] a = {2,6,12};
            for (int i = 0; i < a.length-1; i++) {
                System.out.println(a[i]);
            }



         .. solution::
            :pwd: BezeichNer

            ::

               2
               6
               

   5. 

      .. exercise:: Welche Werte enthalten die Arrayelemente und was ist die Ausgabe?

         .. code:: java
            :class: far-smaller copy-to-clipboard

            final int[] a = new int[10];
            a[0] = 0;
            a[1] = 1;
            for (int i = 2; i < a.length; i++) {
                a[i] = i + a[i-1];
            }
            System.out.println(a[9]);

         .. solution::
            :pwd: 45_-was-sonst

            45
            
   6. 

      .. exercise:: Welcher Code ist korrekt?

         .. code:: java
            :class: far-smaller copy-to-clipboard
            :number-lines:

            int a[][] = new int[2][2];
            int b[][] = new int[2]{1,2,3,4};
            int b[][] = { new int[2], new int[3], new int[4], new int[6] };

         .. solution::
            :pwd: multidim_array

            1. ja
            2. nein (Syntaxfehler!)
            3. ja
   
   7. 

      .. exercise:: Finden Sie den Fehler:

         .. code:: java
            :class: far-smaller copy-to-clipboard
            :number-lines:

            for (int i = 1; i <= a.length; i++) {
                a[i] = i;
            }

         .. solution::
            :pwd: array_index

            Der Index des Arrays beginnt bei 0 und endet bei a.length-1. Der Code führt zu einer :java:`ArrayIndexOutOfBoundsException`.
            