.. meta::
   :lang: de
   :author: Michael Eichberg
   :keywords: "Kontrollfragen", "Java", "Eingabe/Ausgabe"
   :description lang=de: Kontrollfragen bzgl. Eingabe und Ausgabe in Java
   :id: lecture-prog-java-ea-kontrollfragen
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!
    
.. include:: ../docutils.defs



Java Eingabe/Ausgabe
===========================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0


.. class:: new-section transition-scale

Klassisches IO 
-------------------------------------


.. class:: exercises

Kontrollfragen 
-------------------------------------

.. deck:: numbered

   .. card:: 

      .. exercise:: Welche Arten von Datenströmen unterscheiden Sie?

         .. solution::
            :pwd: ZeichenUndByteStröme

            Wir können einerseits zwischen Zeichen und Byteströmen unterscheiden. Andererseits können wir zwischen Eingabe- und Ausgabeströmen unterscheiden und auch zwischen Prozessströmen und Datensenkeströmen.

      .. exercise:: Wie ist das allgemeine Muster der Verwendung von Streams?

         .. solution::
            :pwd: OeffnenUndDann

            1. Erstellen eines Streams
            2. Verwenden des Streams
            3. Schließen des Streams (close)

   .. card::

      .. exercise:: Was ist programmatisch bei der Verwendung von Streams auf jeden Fall zu beachten?

         .. solution::
            :pwd: TryWithResources

            Ein Stream muss immer geschlossen werden, um Ressourcen freizugeben. Dies geschieht in der Regel mit einem *try-with-resources* Block.

      .. exercise:: Wenn Sie von einem Stream lesen (oder schreiben) was ist dann immer zu beachten?

         .. solution::
            :pwd: StreamSchliessen

            Es kann sein, dass nicht alle Daten gelesen (oder geschrieben) werden. D. h. es ist explizit zu prüfen wieviele Bytes/Zeichen gelesen/geschrieben wurden. 

      .. exercise:: Warum benötigen wir Pufferströme?

         .. solution::
            :pwd: Pufferstroeme

            Pufferströme sind wichtig, um die Effizienz der Ein- und Ausgabe zu erhöhen. Sie reduzieren die Anzahl der physischen Lese- und Schreiboperationen, indem sie Daten in Blöcken verarbeiten.
      



.. class:: new-section transition-scale

Stream IO 
-------------------------------------


.. class:: exercises

Kontrollfragen 
-------------------------------------

.. exercise:: Welchen Vorteil bietet die Verwendung von NIO?

   .. solution::
      :pwd: AllesNeu

      Es ist zum Beispiel einfach möglich Streams zu verwenden.