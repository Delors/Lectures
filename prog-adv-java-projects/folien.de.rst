.. meta::
    :version: renaissance
    :lang: de
    :author: Michael Eichberg
    :keywords: "Projekt", "Java"
    :description lang=de: Aufsetzen von Java Projekten
    :id: lecture-prog-adv-java-projekte
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!
    
.. |html-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html
.. |pdf-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html.pdf

.. |newton-code.zip| source:: code/newton-code.zip
    :path: relative

.. |at| unicode:: 0x40

.. role:: incremental
.. role:: eng
.. role:: ger
.. role:: red
.. role:: green
.. role:: peripheral
.. role:: obsolete
.. role:: java(code)
   :language: java
.. role:: console(code)
   :language: console



Java Projekte bauen mit Maven
===========================================================

Eine kurze Einführung

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
     
    :Kontrollfragen:

        .. source:: kontrollfragen.de.rst 
            :path: relative
            :prefix: https://delors.github.io/
            :suffix: .html


.. class:: new-section 

Java Projekte bauen
------------------------------------------------



Ziele
------------------------------------------------


0. **Management der Projektabhängigkeiten** (z. B. JUnit, Log4J, Hibernate, Spring, ....)

.. class:: incremental-list

1. **Kompilieren** des Quellcodes
2. **Testen** des Quellcodes
3. **Paketieren** des Quellcodes (.jar oder .war Datei erzeugen inkl. *aller* Abhängigkeiten)
4. **Dokumentation** erstellen
5. **Reports** erstellen (z.B. Testabdeckung, Code-Qualität)
6. ... und vieles mehr, dass dann aber häufig Projektabhängig ist.   


.. attention::
    :class: incremental

    Ein wichtiges Meta-Ziel ist es, das Bauen der Software zu automatisieren und zu vereinfachen und *stabile Builds zu gewährleisten*. 
    
    .. container:: peripheral

        D. h. zwei Entwickler, die das selbe Projekt auf unterschiedlichen Rechnern mit initial ggf. unterschiedlichen Versionen installierter Werkzeuge und Bibliotheken bauen, sollten dennoch das selbe Ergebnis erhalten.



Etablierte Build-Tools
------------------------------------------------

.. class:: incremental-list

- :obsolete:`Ant`\ [#]_  
- **Maven**
- Gradle
- :peripheral:`sbt`
- :peripheral:`make` (nicht spezifisch für Java)

.. warning::
    :class: incremental

    IDEs wie IntelliJ IDEA, Eclipse, Visual Studio Code oder NetBeans bieten ebenfalls „Build-Unterstützung“. Diese ist aber bestenfalls für kleine Ein-Entwickler-Projekte geeignet.


.. [#] Wurde in der Anfangsphase häufig verwendet. Heute nicht mehr.


Projektstruktur
------------------------------------------------

Konvention, die praktisch über alle Build-Tools und IDEs hinweg gilt\ [#]_:

.. class:: incremental-list

- Quellcode im Verzeichnis ``src/main/java``
- Testcode im Verzeichnis ``src/test/java``
- Ressourcen im Verzeichnis ``src/main/resources``
- Testressourcen im Verzeichnis ``src/test/resources``
- Konfigurationen und andere Ressourcen im Verzeichnis ``src/main/resources``
- gebaute Artefakte im Verzeichnis ``target`` 

.. [#] Andere Sprachen verwenden häufig ähnliche Strukturen. (Selbstverständlich, wird ``java``  dann durch den Namen der entsprechenden Sprache ersetzt.)


.. class:: new-section transition-move-to-top

Maven 
-----------------------------------------------

.. class:: section-subtitle

    https://maven.apache.org



Aufsetzen eines Projekts mittels *Scaffolding*
------------------------------------------------------

Maven ermöglicht es, den Rumpf für ein Java-Projekt mit einer einfachen Befehlszeile zu erstellen:

.. code:: console
    :class: copy-to-clipboard

    mvn archetype:generate \    
        -DgroupId=com.mycompany.app \
        -DartifactId=my-app \
        -DarchetypeArtifactId=maven-archetype-quickstart \
        -DarchetypeVersion=1.5 \
        -DinteractiveMode=false

Dies erzeugt eine initiale Build-Konfiguration für ein einfaches Java-Projekt und erzeugt die Projektstruktur.\ [#]_

.. supplemental::

    Die ``GroupId`` folgt dabei den selben Konventionen wie Java-Packages. Die ``ArtifactId`` ist der Name des Projekts.


.. [#] Es gibt eine Vielzahl von Archetypen, die unterschiedliche Projektstrukturen erzeugen und für unterschiedliche Anwendungsfälle optimiert sind.


Maven - Build Phasen
------------------------------------------------

.. deck::

    .. card::

        .. class:: incremental-list list-with-sublists

        - Eine Phase ist ein Schritt im Build-Lebenszyklus. Die *ersten* Phasen des Standardlebenszyklus sind:

          .. class:: incremental-list

          1. ``validate``
          2. ``generate-sources``
          3. ``process-sources``
          4. ``generate-resources``
          5. ``process-resources``
          6. ``compile``

        - Wenn eine Phase angegeben wird, dann werden alle vorherigen Phasen ausgeführt. Zum Beispiel führt ``mvn compile`` alle genannten Phasen in obiger Reihenfolge aus.

    .. card::

        .. rubric:: die wichtigsten Phasen des Standardlebenszyklus

        :validate: überprüfen, ob das Projekt korrekt konfiguriert ist
        :compile: kompilieren des Quellcodes des Projekts
        :test: testet den kompilierten Quellcode mit einem geeigneten Unit-Testing-Framework. 
        :package: den kompilierten Code in ein verteilbares Format, z. B. ein JAR, verpacken.
        :integration-test: Verarbeitet  das Paket und stellt es, wenn nötig, in einer Umgebung bereit, in der Integrationstests ausgeführt werden können.
        :deploy: bereitstellen in einer Integrations- oder Release-Umgebung

    .. card::

        .. rubric:: Spezialisierte Lebenszyklen (mit eigenen Phasen)

        :clean: bereinigt Artefakte, die von früheren Builds erzeugt wurden.

            Phasen: ``pre-clean``, ``clean``, ``post-clean``
        :site: generiert eine Site-Dokumentation für dieses Projekt

            Phasen: ``pre-site``, ``site``, ``post-site``, ``site-deploy``


Beispiel Build-Konfiguration für ein Java Projekt
----------------------------------------------------

.. story::

    .. rubric:: Code der Anwendung (in ``src/main/java/<package>/<class>.java``)

    .. include:: code/hello/src/main/java/de/dhbw/HelloYou.java
        :code: java
        :class: copy-to-clipboard
        :number-lines:

    .. class:: incremental

    .. rubric:: TestCode (in ``src/test/java/<package>/<class>.java``) 

    .. container:: incremental

        *(Herausforderung: Testing System.out)*

        **Header**

        .. include:: code/hello/src/test/java/de/dhbw/HelloYouTest.java
            :code: java
            :number-lines: 10
            :class: copy-to-clipboard 
            :start-after: import java.io.PrintStream;
            :end-before: @BeforeEach

    .. container:: incremental

        **Setup**

        .. include:: code/hello/src/test/java/de/dhbw/HelloYouTest.java
            :code: java
            :number-lines: 15
            :class: copy-to-clipboard
            :start-after: new ByteArrayOutputStream();
            :end-before: // TESTS

    .. container:: incremental

        **Eigentliche Tests**

        .. include:: code/hello/src/test/java/de/dhbw/HelloYouTest.java
            :code: java
            :class: copy-to-clipboard
            :number-lines: 28
            :start-after: // TESTS

    .. container:: incremental

        **Benötigte Imports**

        .. include:: code/hello/src/test/java/de/dhbw/HelloYouTest.java
            :code: java
            :number-lines:
            :class: copy-to-clipboard
            :end-before: public class HelloYouTest

    .. class:: incremental

    .. rubric:: Maven - Build-Konfiguration (``pom.xml`` im Root Verzeichnis des Projekts)

    .. container:: incremental

        **Header der Konfigurationsdatei**

        .. include:: code/hello/pom.xml
            :code: xml
            :class: copy-to-clipboard
            :number-lines: 1
            :end-before: <groupId>

    .. container:: incremental

        **Allg. projektspezifische Metainformationen** *(Achtung: Anpassung erforderlich!)*

        .. include:: code/hello/pom.xml
            :code: xml
            :class: copy-to-clipboard
            :number-lines: 8
            :start-after: </modelVersion>
            :end-before: <properties>

    .. container:: incremental

        **Buildumgebung**

        .. include:: code/hello/pom.xml
            :code: xml
            :class: copy-to-clipboard
            :number-lines: 14
            :start-after: </url>
            :end-before: <dependencies>

    .. container:: incremental

        **Abhängigkeiten**

        .. include:: code/hello/pom.xml
            :code: xml
            :class: copy-to-clipboard
            :number-lines: 20
            :start-after: </properties>
            :end-before: <build>

    .. container:: incremental

        **Konfiguration des Builds**

    .. include:: code/hello/pom.xml
        :code: xml
        :class: copy-to-clipboard incremental
        :number-lines: 29
        :start-after: </dependencies>
        :end-before: <!--Code Coverage-->

    .. include:: code/hello/pom.xml
        :code: xml
        :class: copy-to-clipboard incremental
        :number-lines: 46
        :start-after: <!--Code Coverage-->
        :end-before: <!--Testing-->

    .. include:: code/hello/pom.xml
        :code: xml
        :class: copy-to-clipboard incremental
        :number-lines: 59
        :start-after: <!--Testing-->
        :end-before: <!--Packaging-->

    .. include:: code/hello/pom.xml
        :code: xml
        :class: copy-to-clipboard incremental
        :number-lines: 63
        :start-after: <!--Packaging-->
        :end-before: <!-- Additional configuration

    .. include:: code/hello/pom.xml
        :code: xml
        :class: copy-to-clipboard incremental
        :number-lines: 77
        :start-after: </reporting>


Projekt bauen und ausführen
------------------------------------------------

.. rubric:: Projekt bauen

.. code:: console
    :class: copy-to-clipboard

    mvn package

.. rubric:: Projekt ausführen

:peripheral:`Die gebauten Artefakte befinden sich im Verzeichnis target.`

.. code:: console
    :class: copy-to-clipboard

    java -jar target/hello-1.0.jar <Name>



.. class:: exercises 

Übung 
------------------------------------------------

.. scrollable::

    .. exercise:: Build-Konfiguration eines Java Projekts
        
        (Falls Maven (``mvn``) noch nicht installiert ist, installieren Sie es.)

        - entpacken Sie das Projekt |newton-code.zip|.
        - legen Sie eine ``pom.xml`` Datei an, um das Projekt zu bauen.
        - Konfigurieren Sie eine Abhängigkeit zu JUnit 5.12 und konfigurieren Sie das ``surefire`` Plugin, um die Tests auszuführen.
        - Nutzen Sie :console:`mvn test`, um die Tests auszuführen.
        - Konfigurieren Sie das ``maven-jar-plugin``, um ein ausführbares JAR zu erzeugen. Vergessen sie nicht die ``mainClass`` zu konfigurieren.
        - Nutzen Sie :console:`mvn package`, um das Projekt zu bauen.
        - Nutzen Sie :console:`mvn site`, um eine Dokumentation des Projekts zu erstellen.
        - Schauen Sie sich die erzeugten Artefakte an.
        - Testen Sie ob Sie die Anwendung mit ``java -jar target/newton-1.0-SNAPSHOT.jar`` starten können.

        .. rubric:: Weiterführende Aufgaben 

        (In diesem Fall ist es Ihrer Aufgabe zu recherchieren wie die Einbindung/Konfiguration zu erfolgen hat.)

        - Binden Sie Checkstyle in Ihre Projekt ein. D. h. wenn Sie die :console:`mvn site` ausführen, dann soll automatisch ein Report in Hinblick auf die Einhaltung der Checkstyle-Regeln erstellt werden.

          Schauen Sie sich den Report an und versuchen Sie für die Klasse Liste eine besser Einhaltung der Checkstyle Regeln zu erreichen.
        - Binden Sie das Maven-Plugin JaCoCo ein, dass automatisch die Testabdeckung berechnet und in einem Report darstellt. Führen Sie danach :console:`mvn test` aus (und ggf. mvn site) und schauen Sie sich den Report an.

          Wie hoch ist bereits die Testabdeckung für die Klasse :java:`List` obwohl diese gar nicht explizit getestet wurde?
        - Schreiben Sie sinnvolle Tests für die Klasse :java:`List` und erhöhen Sie die Anweisungsüberdeckung auf 100% - abgesehen von den Zeilen, die nur Exceptions werfen. D. h. Sie brauchen sich in den Tests nicht um den Code kümmern, der Exceptions wirft; ignorieren Sie diesen Aspekt für den Moment.
        - Binden Sie ein Maven-Plugin ein, dass automatisch die JavaDoc erstellt und in einem Report darstellt.

        .. solution::
            :pwd: MAVENFORJAVA

            .. include:: code/newton/pom.xml
                :code: xml
                :number-lines:



        


