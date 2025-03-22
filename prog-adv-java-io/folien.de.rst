.. meta::
    :version: renaissance
    :lang: de
    :author: Michael Eichberg
    :keywords: "Programmierung", "Java", "IO"
    :description lang=de: Lesen und Schreiben von Dateien in Java - Grundlagen
    :id: lecture-prog-java-io
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
.. role:: obsolete
.. role:: peripheral
.. role:: monospaced
.. role:: java(code)
   :language: java
.. role:: console(code)
   :language: console



Java - Dateien lesen und schreiben
===========================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0


.. supplemental::

    :Folien:

        |html-source|

        |pdf-source|

    ..
        :Kontrollfragen:

            .. source:: kontrollfragen.de.rst
                :path: relative
                :prefix: https://delors.github.io/
                :suffix: .html

        :Klausurvorbereitung:

            .. source:: klausurvorbereitung.de.rst
                :path: relative
                :prefix: https://delors.github.io/
                :suffix: .html

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section

Einführung in I/O in Java
------------------------------------------------




.. class:: new-section

Streams und Java I/O
------------------------------------------------



:java:`java.nio.file.Files`
----------------------------

Neben den traditionellen I/O-Klassen (seit Java 1.X) gibt es auch die Möglichkeit Dateien als Streams zu lesen und zu schreiben (:java:`java.nio.file.Files`).

.. code:: java
    :number-lines:

    package java.nio.file;

    public class Files {
        /** Read all lines from a file as a Stream. */
        static Stream<String> lines(Path path)

        /** Read all lines from a file as a Stream. */
        static Stream<String> lines(Path path, Charset cs)

        // ...
    }

.. attention::

    Diese Streams müssen explizit geschlossen werden (:java:`close()`), da sie Ressourcen verbrauchen.



.. class:: exercise

Übung
---------

.. exercise:: Streamverarbeitung von Dateien

    Schreiben Sie ein Programm, das eine Textdatei liest und die Zeilen in der Konsole ausgibt. Jeder Zeile soll weiterhin die Zeilennummer vorangestellt werden. Verwenden Sie dazu die Klasse `Files` und die Methode `lines`.

    .. example::

        Für folgende Datei (Autor: ChatGPT):

        :: 

            In Java springt der Code so leicht,
            Klammern tanzen, Ziel erreicht,
            Fehler? Nur ein Abenteuer vielleicht! 


        sollte folgende Ausgabe erzeugt werden:

        :: 

            1: In Java springt der Code so leicht,
            2: Klammern tanzen, Ziel erreicht,
            3: Fehler? Nur ein Abenteuer vielleicht!

    .. solution::
        :pwd: JavaIO-Streams

        .. rubric:: Lösung bei der Verwendung der JShell

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            var c = 0; 
            Files.lines(path).map(l -> (++c) + ": " + l).forEach(System.out::println);

        .. rubric:: Allgemeine Lösung

        .. code:: java
            :number-lines:
            :class: copy-to-clipboard

            void printLinesWithLineNumber(Path path) throws IOException {
                final int[] c = {0}; 
                try (var s = Files.lines(path)) {
                    s.map(l -> (++c[0]) + ": " + l).forEach(System.out::println);
                }
            }

            printLinesWithLineNumber(new File("docutils.conf").toPath());


.. class:: exercise

Übung
---------

.. exercise:: Durchsuchen von Dateien

    Schreiben Sie ein Programm (Sie können die JShell benutzen), dass alle Textdateien (z. B. \*.rxt, \*.md oder \*.java) eines Verzeichnisses in Hinblick auf das Vorkommen eines bestimmten Wortes (z. B. Java) durchsucht. Geben Sie den Namen der Datei und eine Zeilennummer aus, in der das Wort vorkommt. Parallelisieren Sie die Suche wenn möglich.

    Relevante API: :java:`Files.walk`, :java:`Files.isRegularFile`, :java:`Files.lines`, :java:`Stream.filter`, :java:`Stream.map`, :java:`Stream.findAny`, :java:`Optional.isPresent`, :java:`Optional.get`, :java:`Optional.empty`

    .. hint::

        Sie müssen ggf. :java:`IOException`\ s explizit behandeln und in solchen Fällen zum Beispiel :java:`Optional.empty()` zurückgeben.

    .. solution::
        :pwd: JavaIO--DasWars

        .. rubric:: Lösung bei der Verwendung der JShell (Java 24)

        .. code::
            :number-lines:
            :class: copy-to-clipboard

            Files
                .walk(new File(".").toPath())
                .parallel()
                .filter(p -> Files.isRegularFile(p) && p.toString().endsWith(".rst"))
                .map(p -> {
                        try {
                            int[] c = {0};
                            return Files.lines(p)// DON'T USE .parallel() HERE!
                                    .map(l -> (++c[0]) + ": " + l)
                                    .filter(x -> x.contains("Java"))
                                    .map(x -> p + ":" + x)
                                    .findAny();
                        }
                        catch(Exception e){ 
                            System.out.println("Ignoring: "+p);
                            return Optional.empty();
                        }
                    })
                .filter(x -> x.isPresent())
                .map(x -> x.get())
                .forEach(System.out::println);