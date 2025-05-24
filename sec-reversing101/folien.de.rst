.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "IT Sicherheit", "Reverse Engineering"
    :description lang=de: Fortgeschrittene Angewandte IT Sicherheit
    :id: lecture-security-java_reverse_engineering
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Reverse Engineering 101
=====================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.0.1

.. supplemental::

  :Folien:

    |html-source|

    |pdf-source|

  :Fehler melden:

    https://github.com/Delors/delors.github.io/issues



Vorerfahrungen?
-------------------

.. class:: incremental-list

- Wer hat schon einmal Software or Hardware Reverse Engineering betrieben?
- Wer kennt Java Bytecode?
- Wer hat Erfahrung mit Python?



Reverse Engineering
----------------------

Reverse Engineering ist die Analyse von Systemen mit dem Ziel, ihren Aufbau und ihre Funktionsweise zu verstehen.

.. container:: incremental

    Typische Anwendungsfälle:

    .. class:: incremental

    - die Rekonstruktion (von Teilen) des Quellcodes von Programmen, die nur als Binärabbild vorliegen.
    - die Analyse von Kommunikationsprotokollen proprietärer Software

.. supplemental::

    Vom Reverse Engineering ist das **Reengineering** zu unterscheiden. Im Fall von letzteren geht es „nur“ darum die Funktionalität eines bestehenden Systems mit neuen Techniken wiederherzustellen.



Zweck von Reverse Engineering
--------------------------------

.. class:: incremental-list

- Herstellung von Interoperabilität
- Untersuchung auf Schwachstellen
- Untersuchung auf Copyrightverletzungen
- Untersuchung auf Backdoors
- Analyse von Viren, Würmern etc.
- Umgehung von ungerechtfertigten(?) Schutzmaßnahmen (z. B. bei Malware)



Bootrom Lücke
---------------

.. epigraph::

    **CPU-Lücke macht Malware-Infektionen nahezu unumkehrbar**

    Die Schwachstelle verschafft Angreifern Zugang zu einer der höchsten Privilegienstufen heutiger PC-Systeme. Schadsoftware entzieht sich damit jeglicher Erkennung.

    [...] Gegenüber Wired erklärten die Forscher, per Sinkclose ließen sich etwa Bootkits installieren, die für das Betriebssystem und gängige Antivirensoftware unsichtbar seien, während Angreifer einen Vollzugriff auf das Zielsystem erhielten.

    -- August, 2024 - `Golem.de <https://www.golem.de/news/sinkclose-trifft-amd-cpu-luecke-macht-malware-infektionen-nahezu-unumkehrbar-2408-187913.html>`__ (`AMD CVE <https://www.amd.com/en/resources/product-security/bulletin/amd-sb-7014.html>`__)




.. class:: smaller-slide-title

`CVE-2024-3094 <https://nvd.nist.gov/vuln/detail/CVE-2024-3094>`__ - ``liblzma`` Backdoor in OpenSSH\ [#]_\ [#]_
-------------------------------------------------------------------------------------------------------------------------------------------------

.. class:: incremental-list column-list dhbw list-with-details

- Ziel

  Das Verhalten von SSH bei der Authentifikation so zu verändern, dass es dem Angreifer Zugang zum System erlaubt.

  Zur Absicherung der Backdoor ist diese über ein Zertifikat abgesichert.

- Wie verbreitet?

  Die Bibliothek ``liblzma`` wurde so angepasst, dass diese eine Backdoor in SSH einbaut.

  Der Schadcode ist nur in den Tarballs zu finden - nicht im SourceCode im GIT. Der eigentliche Schadcode wurde versteckt in *Testfixtures*.

  Der Code wurde so entworfen, dass bekannte Werkzeuge (*Valgrind*) keine Probleme erkennen.

  Die Bibliothek wurde nur in bestimmten Situationen von OpenSSH verwendet.
- Bewertung

  *CVSS Base Score*: 10.0 (kritisch)

  *Entstandener Schaden*: vermutlich gering, da (gerade noch) keine offiziellen Releases (von Debian, Ubuntu, etc.) betroffen waren.

  Dem Angriff ging ein sehr langer Social Engineering Angriff voraus, weswegen mit höherer Wahrscheinlichkeit ein „State-sponsored Actor“ dahintersteckt.


.. [#] `InnoQ  Podcast <https://www.innoq.com/de/podcast/030-xz-open-ssh-backdoor/transcript/>`__
.. [#] `SSH Blob <https://www.ssh.com/blog/a-recap-of-the-openssh-and-xz-liblzma-incident#:~:text=The%20harsh%20fact%20is%20that,by%20one%20of%20its%20maintainers>`__



Backdoor in 16 D-Link Routern\ [#]_
--------------------------------------

- Angreifer können aus dem lokalen Netzwerk heraus den Telnet-Dienst betroffener D-Link-Router durch Angabe einer bestimmten Ziel URL aktivieren.
- Die Admin-Zugangsdaten sind in der Firmware hinterlegt.
- Vermutlich ursprünglich für werksseitige Tests.
- *CVSS Base Score*: 8.8 (hoch)


.. [#] `Golem.de <https://www.golem.de/news/d-link-versteckte-backdoor-in-16-routermodellen-entdeckt-2406-186277.html>`__



Reverse Engineering - grundlegende Schritte
---------------------------------------------

.. class:: incremental-list dhbw

1. Informationsgewinnung zur Gewinnung aller relevanten Informationen über das Produkt.
2. Modellierung mit dem Ziel der (Wieder-)Gewinnung eines (abstrakten) Modells der relevanten Funktionalität.
3. Überprüfung (:eng:`review`) des Modells auf seine Richtigkeit und Vollständigkeit.



Informationsgewinnung - Beispiel
----------------------------------

Gegeben sei eine App zum Ver- und Entschlüsseln von Dateien sowie ein paar verschlüsselte Dateien. Mögliche erste Schritte vor der Analyse von Binärcode:

.. deck::

    .. card::

       - Die ausführbare Datei ggf. mit ``file`` (oder sogar mit ``binwalk``) überprüfen (z. B. wie wurde die Datei kompiliert und für welches Betriebssystem und Architektur)

        Beispiel:

        .. code:: bash

            $ file /usr/bin/openssl
            /usr/bin/openssl: Mach-O universal binary with 2 archi...
            /usr/bin/openssl (for architecture x86_64):	Mach-O 64-bit
            /usr/bin/openssl (for architecture arm64e):	Mach-O 64-bit

    .. card::

       - Die Dateien mit einem (guten) Hexeditor auf Auffälligkeiten untersuchen.

         .. image:: pictures/hexeditor.png
            :alt: Hexeditor mit Dateninterpretation
            :align: center
            :class: highdpi

    .. card::

        .. caution::

            Die Datei ggf. auf (bekannte) Viren und Malware überprüfen.

    .. card::

      - Eine Datei mit einem bekannten Inhalt verschlüsseln und danach vergleichen.

        Ist die Datei gleich groß?

           Falls ja, dann werden keine Metainformationen gespeichert und das Passwort kann (ggf.) nicht (leicht) verifiziert werden.

           (Es kann zumindest nicht *direkt* in der Datei gespeichert sein.)

    .. card::

      - Eine Datei mit verschiedenen Passworten verschlüsseln.

        Sind die Dateien gleich?

           Falls ja, dann wäre die Verschlüsselung komplett nutzlos und es gilt nur noch den konstanten Schlüssel zu finden.

        Gibt es Gemeinsamkeiten?

           Falls ja, dann wäre es möglich, dass das Passwort (gehasht) in der Datei gespeichert wird.

    .. card::

       - Eine Datei mit einem wohldefinierten Muster verschlüsseln, um ggf. den „Mode of Operation“ (insbesondere ECB) zu identifizieren.

    .. card::

       - Mehrere verschiedene Dateien mit dem gleichen Passwort verschlüsseln

         Gibt es Gemeinsamkeiten?

           Falls ja, dann wäre es möglich, dass die entsprechenden Teile direkt vom Passwort abgeleitet werden/damit verschlüsselt werden.

    .. card::

       - ...

    .. card::

       - Reverse Engineering der App durchführen.


Rechtliche Aspekte des Reverse Engineering
-------------------------------------------

.. caution::

    Die Gesetzgebungen unterscheiden sich von Land zu Land teils signifikant.

.. class:: incremental-list

- Die Rechtslage hat sich in Deutschland mehrfach geändert.
- Umgehung von Kopierschutzmechanismen ist im Allgemeinen verboten.
- Lizenzen verbieten das Reverse Engineering häufig; es stellt sich aber die Frage nach der Rechtmäßigkeit der Klauseln.

.. warning::
    :class: incremental

    Bevor Sie Reverse Engineering von Systemen betreiben, erkundigen sie sich erst über mögliche rechtliche Konsequenzen.


.. class:: new-section transition-scale

Software Reverse Engineering
--------------------------------

Ansätze
-----------

.. story::

    .. class:: incremental-list

    :statische Analyse: Studieren des Programms ohne es auszuführen; typischerweise mittels eines Disassemblers oder eines Decompilers.

    :dynamische Analyse: Ausführen des Programms; typischerweise unter Verwendung eines Debuggers oder eines instrumentations Frameworks (z. B. `Frida <https://frida.re>`__).

    :hybride Analyse: Kombination aus statischer und dynamischer Analyse.

        Ansätze wie `Unicorn <https://www.unicorn-engine.org>`__, welches auf `QEmu <https://www.qemu.org>`__ aufbaut, erlaubt zum Beispiel die Ausführung von (Teilen von) Binärcode auf einer anderen Architektur als der des Hosts.

        Ein Beispiel wäre die Ausführung einer Methode, die im Code verschlüsselte hinterlegte Strings entschlüsselt (:eng:`deobfuscation`), um die Analyse zu vereinfachen.

    .. compound::
        :class: incremental

        Ggf. müssen für Teile des Codes, die die Hostfunktionalität nutzen, Stubs/Mocks bereitgestellt werden.



Disassembler
-------------

Überführt (maschinenlesbaren) Binärcode in Assemblercode

Kommandozeilenwerkzeuge (exemplarisch):

- ``objdump -d``
- ``gdb``
- ``radare``
- ``javap (für Java)``

.. hint::
    :class: incremental

    Für einfache Programme ist es häufig möglich direkt den gesamten Assemblercode mittels der entsprechenden Werkzeuge zu erhalten. Im Falle komplexer Binärdateien (z. B. im ELF (Linux) und PE (Windows) Format) gilt dies nicht und erfordert ggf. manuelle Unterstützung zum Beispiel durch das Markieren von Methodenanfängen.

    Im Fall von Java ``.class`` ist die Disassemblierung immer möglich.



Decompiler
-------------

Überführt (maschinenlesbarem) Binärcode *bestmöglich* in Hochsprache (meist C ähnlich oder Java). Eine *kleine* Auswahl von verfügbaren Werkzeugen:

- Hex-Rays IDAPro (kommerziell)
- `Ghidra <https://ghidra-sre.org/>`__ (unterstützt fast jede Platform; die Ergebnisse sind sehr unterschiedlich.)
- JadX (Androids ``.dex`` Format)
- CFR (Java ``.class`` Dateien)
- IntelliJ

.. supplemental::

    Mittels Decompiler ist es ggf. möglich Code, der zum Beispiel ursprünglich in Kotlin oder Scala geschrieben und für die JVM kompiliert wurde, als Java Code zurückzubekommen.

    Die Ergebnisse sind für Analysezwecke zwar häufig ausreichend gut – von funktionierendem Code jedoch ggf. (sehr) weit entfernt.

    `decompiler.com <https://decompiler.com>`__ unterstützt eine große Anzahl ausführbaren Dateien.

.. hint::
    :class: incremental

    Decompiler sind generell sehr hilfreich, aber gleichzeitig auch sehr fehlerbehaftet. Vieles, dass im Binärcode möglich ist, hat auf der Ebene des Sourcecodes keine Entsprechung.

    Zum Beispiel unterstützt Java Bytecode beliebige Sprünge. Solche Code wird aber durch normale Programme, die z. B. in Java, Kotlin, Scala oder Clojure geschrieben wurden, nicht erzeugt. Decompiler kommen mit solchem Code in der Regel nicht (gut) zurecht.



cfr Decompiler
---------------

.. image:: pictures/cfr.png
    :alt: The CFR Decompiler (Java)
    :align: center
    :class: highdpi



JD Decompiler
---------------

.. grid::

    .. cell:: width-30

        .. image:: pictures/jd.png
            :class: highdpi
            :alt: The JD Decompiler (Java)

    .. cell:: width-55 incremental framed trbl-shadow-dark

        .. figure:: pictures/jd-excerpt.png
            :class: highdpi

            Beispiel fehlgeschlagener Dekompilierung


JDec Decompiler
---------------

.. image:: pictures/jdec.png
    :alt: The JDec Decompiler (Java)
    :align: center
    :class: highdpi



Debugger
-----------

Dient der schrittweisen Ausführung des zu analysierenden Codes oder Hardware; ermöglichen zum Beispiel Speicherinspektion und Manipulation.

- gdb
- lldb
- x64dbg (Windows, Open-Source)
- jdb (Java Debugger)

.. supplemental::

    .. rubric:: Hardware Debugger

    Für das Debuggen von Hardware gibt es entsprechende Werkzeuge, z. B.
    `Lauterbach Hardware Debugger <https://www.lauterbach.com>`__ (kommerziell und sehr teuer).

    Mittels solcher Werkzeuge ist es möglich die Ausführung von Hardware Schritt für Schritt (:eng:`single step mode``) zu verfolgen und den Zustand der Hardware (Speicher und Register) zu inspizieren. Dies erfordert jedoch häufig eine JTAG Schnittstelle oder etwas vergleichbares.



.. class:: new-section transition-fade

Erschwerung des Reverse Engineering
------------------------------------



.. class:: new-subsection

Obfuscation (:ger:`Verschleierung`)
------------------------------------



Obfuscation → für Menschen unverständlich Code
----------------------------------------------------------

.. story::

    .. class:: incremental-list

    - Techniken, die dazu dienen das Reverse Engineering zu erschweren.
    - Häufig eingesetzt ...

    .. class:: incremental-list

    -  von Malware
    -  Adware (im Kontext von Android ein häufig beobachtetes Phänomen)
    -  zum Schutz geistigen Eigentums
    -  für DRM / Durchsetzung von Kopierrechten
    -  zur Prävention von „Cheating“ (insbesondere im Umfeld von Online Games)
    -  Wenn das Programm als Source Code vertrieben wird bzw. vertrieben werden muss (JavaScript)

    - Arbeiten auf Quellcode oder Maschinencode Ebene
    - Grenze zwischen *Code Minimization*, *Code Optimization* und *Code Obfuscation* ist fließend.
    - Mögliche Werkzeuge (ohne Wertung der Qualität/Effektivität):

    - [Java] Proguard / Dexguard
    - [C/C++] `Star Force <https://www.star-force.com/products/starforce-crypto/>`__

.. supplemental::

    Gerade im Umfeld von klassischen *Binaries* für Windows, Mac und Linux erhöhen Compiler Optimierungen, z. B. von C/C++ und Rust Compilern (``-O2 / -O3``), bereits den Aufwand, der notwendig ist den Code zu verstehen, erheblich.

    .. hint::

        Einen ambitionierten und entsprechend ausgestatteten Angreifer wird **Code Obfuscation** bremsen, aber sicher nicht vollständig ausbremsen und das Vorhaben verteilen.



Obfuscation - Techniken (Auszug)
------------------------------------

.. story::


    .. class:: incremental-list

    - :peripheral:`entfernen aller Debug-Informationen`
    - Das Kürzen aller möglichen Namen (insbesondere Methoden und Klassennamen).
    - Das Verschleiern von Konstanten durch den Einsatz vermeintlich komplexer Berechnungen zu deren Initialisierung.

      .. code:: java
            :class: copy-to-clipboard

            ~(((int)Math.PI) ^ Integer.MAX_VALUE >> 16)+Short.MAX_VALUE

      .. code:: java
            :class: incremental

            = 2

    - Die Verwendung von Unicode Codepoints für Strings oder die Verschleierung von Strings mittels `rot13 <https://cryptii.com/pipes/rot13-decoder>`__ Verschlüsselung.

      .. code:: C
        :class: copy-to-clipboard

        /* ??? */ printf("\x48""e\154l\x6F"" \127o\x72""l\144!");


      .. code:: C
        :class: incremental

        /*  =  */ printf("Hello World!");

    - Das Umstellen von Instruktionen, um das Dekompilieren zu erschweren.
    - Das Hinzufügen von totem Code.

    - Den relevanten Teil der Anwendung komprimieren und verschlüsseln und erst bei Verwendung entpacken und entschlüsseln.
    - ...

.. supplemental::

   Obfuscation auf Source Code Ebene:
   `International Obfuscated C Code Contest <https://www.ioccc.org/>`__

   **Umstellen von Instruktionen**

   Das Umstellen von Instruktionen erschwert die Analyse, da viele Werkzeuge zum Dekompilieren auf die Erkennung von bestimmten Mustern im Code angewiesen sind und ansonsten nur sehr generischen (Spagetti Code) oder gar unsinnigen Code zurückgeben.

   **Verschleierung von Strings**

   Das Verschleiern von Strings kann insbesondere das Reversen von Binärcode erschweren, da ein Angreifer häufig „nur“ an einer ganz bestimmten Funktionalität interessiert ist und dann Strings ggf. einen sehr guten Einstiegspunkt für die weitergehende Analyse bieten.

   Stellen Sie sich eine komplexe Java Anwendung vor, in der alle Namen von Klassen, Methoden und Attributen durch einzelne oder kurze Sequenzen von Buchstaben ersetzt wurden und sie suchen danach wie von der Anwendung Passworte verarbeitet werden. Handelt es sich um eine GUI Anwendung, dann wäre zum Beispiel die Suche nach Text, der in den Dialogen vorkommt (z. B. ``"Password"``) z. B. ein sehr guter Einstiegspunkt.


.. class:: new-subsection

Verschlüsselung von Bytecode und Java Class Loader
---------------------------------------------------


``ClassLoader``
----------------

.. class:: incremental-list list-with-explanations

- ``ClassLoader`` dienen dazu Klassen dynamisch zu laden. D. h. eine Klasse wird erst dann von der JVM geladen, wenn sie benötigt bzw. angefordert wird.
- Jeder ``ClassLoader`` spannt seinen eigenen Namensraum auf.

  Zwei Instanzen der gleichen Klasse (d. h. mit dem selben Bytecode) sind nicht gleich (Referenzgleichheit), wenn zwei verschiedene ``ClassLoader`` genutzt wurden.
- ``ClassLoader`` stehen in einer Hierarchie.
- ``ClassLoader`` können genutzt werden, um:

  - ein Programm dynamisch zu erweitern (Plug-ins
  - um Klassen zu laden, die zur Laufzeit generiert wurden
  - um den Bytecode zu manipulieren, bevor er von der JVM ausgeführt wird.





Ein eigener ClassLoader
--------------------------

.. code:: Java
    :class: copy-to-clipboard
    :number-lines:

    static class MyClassLoader extends ClassLoader {
      public MyClassLoader(ClassLoader parent) { super(parent); }

      @Override
      protected Class<?> findClass(final String name) throws ClassNotFoundException {
        try (final var in = super.getResourceAsStream(name)) {
          final var classBytes = new byte[in.available()];
          final var readBytes = in.read(classBytes);
          if (readBytes != classBytes.length) {
             throw new IOException("failed reading class file: " + name);
          }
          return defineClass(name,classBytes, 0, classBytes.length);
        } catch (IOException ioe) {
           throw new ClassNotFoundException("failed loading " + name, ioe);
    } } }



ClassLoading - Example
------------------------

.. story::

    .. compound::

        .. rubric:: Ein Singleton

        .. code:: Java
            :class: copy-to-clipboard
            :number-lines:

            public class MySingleton {

                private static MySingleton instance = null;
                private MySingleton() {}

                public static synchronized MySingleton instance() {
                    if (instance == null) instance = new MySingleton();
                    return instance;
                }
            }

    .. compound::
        :class: incremental

        .. rubric:: Gleichheit von Instanzen

        .. code:: Java
            :class: copy-to-clipboard
            :number-lines:

            Object a = MySingleton.instance();
            Object b = MySingleton.instance();
            System.out.println(a == b);

        .. code:: Text

            Ergebnis: true

    .. container:: incremental

        .. rubric:: Verwendung des SystemClassLoader

        .. code:: Java
            :class: copy-to-clipboard
            :number-lines:

            ClassLoader cl1 = ClassLoader.getSystemClassLoader();
            Class<?> clazz1 = cl1.loadClass("demo.MySingleton");
            Object a = clazz1
                .getDeclaredMethod("instance", new Class<?>[] {})
                .invoke(null);
            ClassLoader cl2 = ClassLoader.getSystemClassLoader();
            Class<?> clazz2 = cl2.loadClass("demo.MySingleton");
            Object b = clazz2
                .getDeclaredMethod("instance", new Class<?>[] {})
                .invoke(null);

            System.out.println(a == b);

        .. code:: Text

            Ergebnis: true

    .. compound::
        :class: incremental

        .. rubric:: Verwendung von zwei Instanzen von MyClassLoader

        .. code:: Java
            :class: copy-to-clipboard
            :number-lines:

            ClassLoader cl1 = new MyClassLoader();
            Class<?> clazz1 = cl1.loadClass("demo.MySingleton");
            Object a = clazz1
                .getDeclaredMethod("instance", new Class<?>[] {})
                    .invoke(null);
            ClassLoaderacl2 = new MyClassLoader();
            Class<?> clazz2 = cl2.loadClass("demo.MySingleton");
            Object b = clazz2
                .getDeclaredMethod("instance", new Class<?>[] {})
                    .invoke(null);

            System.out.println(a == b);

        .. code:: Text
            :class: incremental

            Ergebnis: false



.. class:: new-section transition-fade

Eine sehr kurz Einführung in Java Bytecode
-----------------------------------------------



Die Java Virtual Machine
-------------------------------------------------

.. class:: incremental-list

- **Java Bytecode** ist die Sprache, in der Java (oder Scala, Kotlin, ...) Programme auf der Java Virtual Machine (JVM) [#]_ ausgeführt werden.
- :peripheral:`In den meisten Fällen arbeiten Java Decompiler so gut, dass ein tiefgehendes Verständnis von Java Bytecode selten notwendig ist.`
- Java Bytecode kann — muss aber nicht — interpretiert werden. (Z. B. können „virtuelle Methodenaufrufe“ in Java schneller sein als in C++.)

.. [#] `Java Bytecode Spezifikation <https://docs.oracle.com/javase/specs/jvms/se21/html/index.html>`__



Java Bytecode - stackbasierte virtuelle Maschine
-------------------------------------------------

.. container:: smaller

   Die JVM ist eine stackbasierte virtuelle Maschine.

   Die getypten Operanden eines Befehls werden auf einem Stack abgelegt und die Operationen arbeiten auf den obersten Elementen des Stacks. Jeder Thread hat seinen eigenen Stack.

        .. grid:: incremental

            .. cell:: width-50

                .. rubric:: Instruktionen

                .. code:: java

                    nop
                    bipush 100               → int

                    bipush  50               → int


                    iadd        ← 2 ⨉ int    → int



            .. cell:: width-50 incremental

                .. rubric:: Veränderung des Stacks

                .. code:: java

                    └─────┘
                    │ 100 │
                    └─────┘
                    │  50 │
                    │ 100 │
                    └─────┘
                    │ 150 │
                    └─────┘

.. supplemental::

    Eine Methode muss einen Stack begrenzter Höhe aufweisen. Code, für den die Stackhöhe nicht berechenbar ist, wird vom Compiler abgelehnt. (Zum Beispiel ein ``bipush`` in einer Endlosschleife.)
    Die benötigte Höhe des Stacks wird vom Compiler berechnet und von der JVM überprüft.



Java Bytecode - Methodenaufrufe und lokale Variablen
---------------------------------------------------------

.. class:: incremental-list

- Die Java Virtual Machine verwendet lokale Variablen zur Übergabe von Parametern beim Methodenaufruf.
- Beim Aufruf von *Klassenmethoden* (:java:`static`) werden alle Parameter in aufeinanderfolgenden lokalen Variablen übergeben, beginnend mit der lokalen Variable 0.
  D. h. in der aufrufenden Methode werden die Parameter vom Stack geholt und in lokalen Variablen gespeichert.
- Beim Aufruf von *Instanzmethoden* wird die lokale Variable 0 dazu verwendet, um die Referenz (:java:`this`) auf das Objekt zu übergeben, auf dem die Instanzmethode aufgerufen wird.
  Anschließend werden alle Parameter in aufeinanderfolgenden lokalen Variablen übergeben, beginnend mit der lokalen Variable 1.

.. supplemental::

    Die Anzahl der benötigten lokalen Variablen wird vom Compiler berechnet und von der JVM überprüft.



Beispiel: *Default Constructor* In Java Bytecode
-------------------------------------------------

Ein *Constructor* welcher keine expliziten Parameter hat und nur den super Konstruktor aufruft.

.. code:: java
    :number-lines:

    // Method descriptor ()V
    // Stack: 1, Locals: 1
    public Main();
        0  aload_0 [this]
        1  invokespecial java.lang.Object()
        4  return

Die Zeilennummern und die Informationen über die lokalen Variablen sind optional und werden nur für Debugging Zwecke benötigt.

.. code:: java

      Line numbers:         [pc: 0, line: 9]
      Local variable table: [pc: 0, pc: 5]  local: this
                                            index: 0
                                            type:  de.dhbw.simplesecurepp.Main

.. supplemental::

    Es gibt weitere Metainformationen, die „nur“ für Debugging-Zwecke benötigt werden, z. B. Informationen über die ursprünglich Quelle des Codes oder die sogenannte "Local Variable Type Table" in Hinblick auf generische Typinformationen. Solche Informationen werden häufig vor Auslieferung entfernt bzw. nicht hineinkompiliert.


Beispiel: Aufruf einer komplexeren Methode
-------------------------------------------

.. code:: java
    :number-lines:

    // Method descriptor ([Ljava/lang/String;)V
    // Stack: 5, Locals: 8
    public static void main(java.lang.String[] args) throws ...;
        0  aload_0 [args]
        1  arraylength
        2  iconst_2
        3  if_icmpeq 74                // integer comparison for equality
        6  getstatic java.lang.System.err : java.io.PrintStream
        9  ldc <String "SimpleSecure++">
        11  invokevirtual java.io.PrintStream.println(java.lang.String) : void
        ...






.. class:: new-section transition-scale

Verschlüsselung von Daten
----------------------------------------------


Alternativen zur Speicherung von Passwörtern
---------------------------------------------

In einigen Anwendungsgebieten ist es möglich auf das explizite Speichern von Passwörtern ganz zu verzichten\ [*]_.

.. container:: incremental

    Stattdessen wird z. B. einfach versucht das Ziel zu entschlüsseln und danach evaluiert ob das Passwort (vermutlich) das Richtige war.

.. container:: incremental

    Kann darauf verzichtet werden zu überprüfen ob das Passwort korrekt war, dann sind keine Metainformationen notwendig und die verschlüsselte Datei kann genau so groß sein wie die unverschlüsselte Datei.

.. [*] Bei einer Verschlüsselung mit OpenSSL wird das Passwort nicht gespeichert.



Schematische Darstellung der Verschlüsselung von Containern (z. B. Veracrypt)
-------------------------------------------------------------------------------

.. image:: drawings/password-recovery/verschluesselung_von_veracrypt.svg
    :alt: Schematische Darstellung von Containern.
    :align: center



Generische Dateiverschlüsselung ohne explizite Speicherung des Passworts
-------------------------------------------------------------------------

.. image:: drawings/password-recovery/generische_dateiverschluesselung.svg
    :alt: Beispiehafte Verschlüsselung von Containern.
    :align: center



.. class:: center-content no-title

Fokussiert bleiben!
--------------------------------------

.. important::
    :class: width-100

    Bleibe fokussiert!

    Analysiere nur was notwendig ist.



.. class:: exercises

Live Exercise
--------------------------------------

.. rubric:: Gegeben

:Programm: `Simplesecure++ <./exercise/simplesecurepp/simplesecurepp-0.0.1.jar>`__
:Datei: `42.enc <./exercise/simplesecurepp/42.enc>`__
:Hinweise: `Hints.pdf <./exercise/reversing_aufgaben.pdf>`__

.. exercise:: Reversing SimpleSecure++

    .. container
        .. rubric:: Programm
        .. source::
            :path: dir
            :prefix: https://delors.github.io/
            :suffix: /exercise/simplesecurepp/simplesecurepp-0.0.1.jar
        .. rubric:: Verschlüsselte Datei
        .. source::
            :path: dir
            :prefix: https://delors.github.io/
            :suffix: /exercise/simplesecurepp/42.enc

    .. solution::
        :pwd: This passworD is wirklich impossible TO crack

        Beobachtungen:

        - die Datei ist größer als die unverschlüsselte Datei
        - wenn ich die selbe Datei zweimal hintereinander mit dem selben Passwort verschlüssele, dann sind die Dateien identisch (es scheint keinen "Zufall/Seed" zu geben)
        - wenn ich das falsche Passwort verwende, dann wird dies erkannt (und keine Entschlüsselung durchgeführt)
        - wenn ich zwei verschiedene Dateien mit dem selben Passwort verschlüssele, dann sind die Anfänge gleich; die Anfänge unterscheiden sich aber, wenn ich das Passwort ändere
        - es scheint keine Blockchiffre zu sein

        - [Nach der Analyse des Codes]

        .. code:: java
            :class: copy-to-clipboard smaller

                /*
                * INTENDED TO BE RUN AS A JAVA SCRIPT.
                * (I.e. use: "java FindSeed.java".)
                */

                import java.math.BigInteger;
                import java.nio.charset.Charset;
                import java.security.NoSuchAlgorithmException;
                import java.security.SecureRandom;
                import java.util.Arrays;

                public class FindSeed {

                    private final static byte[] CC = "simplesecurepp"
                            .getBytes(Charset.forName("ascii"));
                    private final static long ADLER32_MAX = ((long) Integer.MAX_VALUE) * 2;

                    /**
                    * Reads in an encrypted file from stdin and tries to find the seed that
                    * was used for the SecureRandom generator. Given the seed, it is then
                    * possible to easily decrypt the stream.
                    *
                    * If we are not lucky, it may take several hours on a multi-core machine.
                    */
                    public static void main(String[] args) throws Exception {
                        /*
                        * We don't need the password, because as soon as we have identified the
                        * seed everything is Ok and we can efficiently check if the seed is ok.
                        */
                        var encryptedCC = System.in.readNBytes(CC.length);
                        var availableProcessors = Runtime.getRuntime().availableProcessors();
                        System.out.println(
                            "Trying to find the seed for decryption using " +
                            availableProcessors +
                            " processors.");

                        for (int p = 0; p < availableProcessors; p++) {
                            final var fp = p;
                            var t = new Thread() {

                                final byte[] decryptedHeader = new byte[CC.length];

                                public void run() {
                                    final var START_TIME = System.currentTimeMillis();
                                    final long SEG_SIZE = ADLER32_MAX / availableProcessors;
                                    final long START = fp * SEG_SIZE;
                                    final long END = (fp + 1) * SEG_SIZE;
                                    for (long s = START; s < END; s++) {

                                        SecureRandom secureRandom;
                                        try {
                                            secureRandom = SecureRandom.getInstance("SHA1PRNG");
                                        } catch (NoSuchAlgorithmException e) {
                                            throw new RuntimeException(e);
                                        }
                                        secureRandom.setSeed(BigInteger.valueOf(s).toByteArray());

                                        for (int i = 0; i < CC.length; i++) {
                                            decryptedHeader[i] = (byte) (
                                                encryptedCC[i] ^ secureRandom.nextInt()
                                            );
                                        }
                                        if (Arrays.equals(CC, decryptedHeader)) {
                                            System.out.println("The seed: " + s);
                                            System.exit(1);
                                        }

                                        if (s % 5000000 == 0) {
                                            var speed =
                                                (s - START) /
                                                    (System.currentTimeMillis() - START_TIME);
                                            System.out.println(
                                                    "[" + fp + "] Tested " +
                                                    100l * (s - START) / SEG_SIZE +
                                                    "% (" + speed + "H/ms): "
                                                            + START + "=>" + s);
                                        }
                                    }
                                }
                            };
                            t.start();
                        }
                    }
                }



.. class:: exercises

Reverse Engineering Übung
--------------------------------------

.. rubric:: Gegeben

:Programm: `Secure++ <./exercise/securepp/securepp-0.0.1.jar>`__

    **Exemplarische Verwendung zum Verschlüsseln**

    .. code:: bash
        :class: copy-to-clipboard

        java -jar securepp-0.0.1.jar de.dhbw.securepp.Main \
            -p 'VielleichtIstEsRichtig-vielleichtAuchNICHT...' \
            -in Poem.txt -out Poem.enc

:Datei: `Poem.enc <./exercise/securepp/Poem.enc>`__
:Hinweise: `Hints.pdf <./exercise/reversing_aufgaben.pdf>`__

.. exercise:: Reversing Secure++

    Entschlüsseln Sie die Datei Poem.enc, die mit Secure++ verschlüsselt wurde.

    .. solution::
        :pwd: 5ZeilenInPython;

        Das Problem von Secure++ ist, dass der DEK unabhängig vom Passwort ist. Wir benötigen nur den Nonce aus der Datei und die Konstante "DEK", um die Datei zu entschlüsseln.

        Der folgende Python-Code entschlüsselt Dateien, die mit Secure++ verschlüsselt wurden.

        .. code:: python
            :class: copy-to-clipboard
            :number-lines:

            #!/usr/local/bin/python3

            # Format:
            # [32] Salt (for KEK)
            # [44] Encrypted and Encoded DEK
            # [16] Checksum
            # [16] Nonce for CTR
            # [...] Encrypted Data

            from Crypto.Cipher import AES
            from Crypto.Util import Counter
            from binascii import hexlify

            dek = bytes([ 0x43, 0xE7, 0x14, 0x67, 0xF9, 0x86, 0xDE, 0xEA, 0xAA,
                          0x4E, 0x5F, 0x88, 0xDE, 0x89, 0x15, 0xD7, 0x91, 0x00,
                          0x3D, 0x32, 0x0A, 0xE1, 0x2D, 0x19, 0x25, 0x20, 0x5B,
                          0x92, 0xA9, 0xB1, 0x84, 0xED ])

            with open("demo/Poem.enc","rb") as f:
                f.seek(0x5c)
                nonce = f.read(16)[:8]
                encryptedData = f.read()
                aes = AES.new(dek,AES.MODE_CTR,nonce=nonce)
                print(aes.decrypt(encryptedData))


.. TODO add discussion how to use FRIDA to hook into the Java program SecureRandom class and extract the seed.
