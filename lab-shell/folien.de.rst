.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "Linux", Shell, "Kommandozeile", "Unix", "Bash", "Scripting", "Administration"
    :description lang=de: Einführung in die Linux Shell und Verwendung von Kommandozeilenwerkzeugen auf Linux Systemen
    :id: 2025_05-linux-shell-erste-schritte
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Die Linux Shell
=====================================================

Eine kurze Wiederholung / Einführung.

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.2

.. supplemental::

  :Folien:
      [HTML] |html-source|

      [PDF] |pdf-source|

  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues



.. class:: new-section

Erste Schritte
-----------------



Einloggen auf einem Server
----------------------------

**Ablauf**

.. class:: peripheral

0. Starten Sie ggf. den CISCO Secure Client, wenn Sie auf interne Server der DHBW Mannheim zugreifen wollen. Dies ist ggf. auch aus dem WLan der DHBW heraus notwendig!

.. class:: incremental-list

1. Starten Sie eine Shell (z. B. Terminal oder iTerm auf MacOS bzw. Linux; cmd oder Powershell unter Windows.)
2. Verwenden Sie SSH, um sich auf dem Server einzuloggen. Sollte Ihr Accountname zum Beispiel ``mueller`` sein und der Server z. B. unter der IP ``141.72.12.103`` erreichbar sein, dann können Sie sich wie folgt auf dem Server einloggen *wenn passwort-basierte Authentifizierung möglich ist\ [#]_*:

   .. code:: bash
        :class: copy-to-clipboard

        ssh -l mueller 141.72.12.103

   .. supplemental::

        .. hint::

            Im professionellen Umfeld ist es bei der Verwendung von SSH üblich, sich mittels Public-Key-Authentifizierung zu authentifizieren.

.. [#] Erhalten Sie eine Meldung, dass eine Passwort basierte Authentifizierung nicht erlaubt ist, dannn müssen Sie sich an Ihren Administrator wenden.

.. supplemental::

    SSH ist die Secure Shell und eine allg. etabliertes Werkzeug im Bereich der Administration von (Server-)Systemen, IoT Geräten etc.

    Im professionellen Umfeld würde man zur Authentifizierung mittels SSH auf Zertifikate setzen. Für diese erste Übung verzichten wir darauf.



Dateisysteme auf Unix-ähnlichen Betriebssytemen
-----------------------------------------------

.. story::

    .. class:: incremental-list

    - Die Verzeichnisstruktur in Unix-ähnlichen Systemen ist hierarchisch aufgebaut und beginnt bei der Wurzel (``/``).
    - Alle Dateien und Verzeichnisse sind Unterknoten dieses Wurzelverzeichnisses.

      .. hint::
        :class: incremental

        In Unixoiden Betriebssystemen trennt ``/`` auch Verzeichnisse.

      .. example::
        :class: incremental

        ``/Users/administrator/.zsh_history`` ist der Pfad der Datei .zsh_history im Verzeichnis ``administrator``, welches wiederum im Verzeichnis ``Users`` zu finden ist. ``Users`` ist ein direktes Kindverzeichnis des Wurzelverzeichnisses ``/``.

    - Im Regelfall sind Datei- und Verzeichnisnamen *Case-sensitive*.

      .. example::
        :class: incremental

        Es ist möglich zwei Dateien ``Test.txt`` und ``test.txt`` gleichzeitig zu haben, die sich nur in der Groß-Kleinschreibung unterscheiden.

      .. supplemental::

        Ist ein Dateisystem nur *Case-preserving* (Standard unter Windows und MacOS), dann ist es zwar möglich z. B. eine Datei Test.txt anzulegen, aber nicht gleichzeitig auch eine Datei test.txt, die sich nur in der Kapitalisierung der Buchstaben unterscheidet.

    - Geräte (wie zum Beispiel Festplatten, USB Sticks, Zufallszahlengeneratoren ...) können an *fast beliebiger Stelle* eingehängt werden.

      .. example::
        :class: incremental

        Mittels :console:`mount` kann man sich anzeigen lassen welche Geräte wo eingehängt sind:

        .. code:: systemd
            :number-lines:

            /dev/vda2 on / type ext4 (rw,relatime)
            /dev/vda1 on /boot/efi type vfat (rw,relatime,...)

    - Systeminformationen sind auch direkt über spezielle Verzeichnisse verfügbar

      .. example::
        :class: incremental

        Ausgabe von detaillierten Informationen über die CPU:

        .. code:: console

            more /proc/cpuinfo


        Ausgabe der Log-level Konfiguration des Kernels:

        .. code:: console

            cat /proc/sys/kernel/printk

    - Die Systemkonfiguration kann direkt über das Schreiben in spezialisierte Dateien erfolgen (meist sind Administratorrechte notwendig)

      .. example::
        :class: incremental

        .. code:: console

            echo 7 > /proc/sys/kernel/printk

        Setzt den Logging-level für das Kernel Logging auf den Debug-Level, in dem der Wert 7 in die Datei ``printk`` geschrieben wird.

.. supplemental::

    ``cat``, ``more`` oder auch ``less`` sind ganz generische Programme zum Lesen von jeder Art von Text-basierten Dateien.



Wichtige Verzeichnisse
-----------------------

Die wichtigsten Verzeichnisse - insbesondere unter Linux Betriebssystemen - sind:

.. story::

    .. class:: incremental-table-rows

    +--------------+--------------------------------------------------------------------------+
    | Verzeichnis  | Bedeutung / Inhalt                                                       |
    +==============+==========================================================================+
    | /            | Wurzelverzeichnis – Ausgangspunkt aller Pfade                            |
    +--------------+--------------------------------------------------------------------------+
    | /bin         | Wichtige Systemprogramme (z. B. ``ls``, ``cp``, ``mv``)                  |
    +--------------+--------------------------------------------------------------------------+
    | /dev         | Gerätedateien (z. B. Festplatten: ``/dev/sda``, Terminals: ``/dev/tty``) |
    +--------------+--------------------------------------------------------------------------+
    | /etc         | Systemweite Konfigurationsdateien                                        |
    +--------------+--------------------------------------------------------------------------+
    | /home        | Persönliche Benutzerverzeichnisse (z. B. ``/home/alex``)                 |
    +--------------+--------------------------------------------------------------------------+
    | /lib         | Wichtige Systembibliotheken                                              |
    +--------------+--------------------------------------------------------------------------+
    | /media       | Einhängepunkte für Wechseldatenträger                                    |
    +--------------+--------------------------------------------------------------------------+
    | /mnt         | Temporärer Einhängepunkt für Administratoren                             |
    +--------------+--------------------------------------------------------------------------+
    | /root        | Heimatverzeichnis des Superusers ``root``                                |
    +--------------+--------------------------------------------------------------------------+
    | /sbin        | Systemprogramme für den Administrator                                    |
    +--------------+--------------------------------------------------------------------------+
    | /tmp         | Temporäre Dateien                                                        |
    +--------------+--------------------------------------------------------------------------+
    | /usr         | Sekundäres Hierarchiesystem für Benutzerprogramme                        |
    +--------------+--------------------------------------------------------------------------+
    | /var         | Veränderliche Dateien (z. B. Logs, Mails, Spool-Dateien)                 |
    +--------------+--------------------------------------------------------------------------+
    +--------------+--------------------------------------------------------------------------+
    | ~            | Alias für das Homedirectory des aktuellen Nutzer.                        |
    +--------------+--------------------------------------------------------------------------+




Grundlegendes
-----------------------

.. deck::

    .. card::

        .. rubric:: Verzeichnis Operationen

        .. class:: incremental-list dd-margin-left-4em

        :`ls`:console:: Gibt Informationen über eine Datei/Verzeichnis aus. Ermöglicht es insbesondere den Inhalt eines Verzeichnisses aufzulisten. :console:`ls -al` gibt weitergehende Informationen aus. Insbesondere alle versteckten Dateien und Verzeichnisse sowie die Größe der Dateien und die Rechte.
        :`cd`:console:: (:eng:`change directory`)

            Wechsel des Verzeichnisses. Wechsel zu einem bestimmten Verzeichnis erfolgt durch Angabe des Names (:console:`cd Test`).             Wechsel zum Elternverzeichnis erfolgt über: :console:`cd ..` (:console:`..` steht immer für das Elternverzeichnis).
            Wechsel zum Wurzelverzeichnis erfolgt über: :console:`cd /`

        :`mkdir`:console:: Anlegen eines Verzeichnisses (:console:`mkdir Test`)
        :`rmdir`:console:: Löschen eines *leeren* Verzeichnisses (:console:`rmdir Test`)
        :`rm`:console:: (:eng:`remove`) Löschen einer Datei (:console:`rm Hello.txt`)

    .. card::

        .. rubric:: Allgemeines

        .. class:: incremental-list

        - Wenn man in Linux/MacOS ein Stern benutzt, dann wird dies zu den Namen aller nicht-versteckten Dateien/Verzeichnisse im aktuellen Verzeichnis aufgelöst und diese werden an das Programm übergeben! Zum Beispiel löscht :console:`rm *` alle Dateien im aktuellen Verzeichnis.

        - Verfügbarer Speicher auf der Festplatte: :console:`df -h`

        - Benutzer Speicher (eines Verzeichnisses): :console:`du -h -s ~`

        - Inhalt einer Datei editieren ist (zum Beispiel) mit ``pico`` möglich. Alternative kann mit Hilfe von ``echo`` ein Text in eine Datei umgeleitet werden (:console:`echo "Hello" > Hello.txt`).

        - Inhalt einer Datei ansehen: ``cat``, ``less``, ``more``, ...



Hilfe bekommen
-----------------

In unixoiden Betriebssystemen gibt es für Kommandozeilentools Insbesondere die folgenden *eingebauten* Hilfemöglichkeiten:

- Hilfe des Programms (typischerweise :console:`--help` oder einfach starten).
- **Manpages** (mittels :console:`man <Name des Programms>`)
- Infopages (mittels :console:`info <Name des Programms>`)

.. remark::
    :class: incremental

    Manpages exisitieren für praktisch alle Kommandozeilenwerkzeuge.

    Die schnelle Hilfe ist insbesondere bei „neueren“ Programmen nicht immer über :console:`--help` verfügbar.

    Je nach Anwendung kann das Starten der Anwendung ohne Parameter dazu führen, dass das Programm auf Eingaben wartet (z. B. :console:`cat`).



Kommandozeilenprogrammen beenden
--------------------------------

.. class:: incremental-list

:<ctrl>+c: Programm abbrechen (ggf. unkontrolliert)
:<ctrl>+d: Eingabe abschließen bzw. Ende der Eingabe
:`kill <PID>`:console:: Mittels des Befehls :console:`kill` lässt sich das Progrmm auch beenden. Erfordert ggf. dass man einen zweiten Terminal startet um die PID zu ermitteln (zum Beispiel mittels :console:`top` oder :console:`ps`):


.. class:: exercises

Übung - mit einem Server verbinden
-----------------------------------------

.. exercise:: Unix - Erste Schritte

    .. class:: incremental-list list-with-explanations

    1. Loggen Sie sich auf dem Server unter Verwendung der passenden Zugangsdaten ein.

    2. Ändern Sie Ihr Passwort mit Hilfe von ``passwd``.


.. class:: exercises

Übung - mit einem Server über Keys verbinden\ [#]_
-----------------------------------------------------

.. exercise:: Unix - Einloggen mittels SSHKey

    **Im professionellen Umfeld ist die Authentifizierung über SSH-Keys üblich.** SSH-Keys sind eine sicherere Alternative zu Passwörtern, da keine Passwörter übertragen werden.

    .. class:: incremental-list list-with-explanations

    1. Erstellen Sie **lokal** - auf Ihrem Clientrechner - einen SSH Key-Paar mittels ssh-keygen. Nutzen Sie den ``ed25519`` Algorithmus.

       Sollten Sie bereits ein SSH Key-Paar haben, dann können Sie diesen verwenden. Ansonten erstellen Sie das Schlüsselpaar im lokalen Verzeichnis.

    2. Kopieren Sie den **öffentlichen Schlüssel** auf den Server mittels :console:`ssh-copy-id`.

       Der öffentliche Schlüssel ist in der ``.pub`` Datei. Diese Datei ist das *ìdentity file*, dass der Server benötigt/verwendet.

    3. Testen Sie ob Sie sich beim Server anmelden können ohne Paswort!

    .. solution::
        :pwd: LesenIstWichtig

        Die folgenden Schritte sind auf dem Client auszuführen.

        Die Nutzerkennung und die IP-Adressen sind ggf. an „Ihren“ Server anzupassen.

        1. :console:`ssh-keygen -t ed25519 -f ./userX-ssh-keypair`
        2. :console:`ssh-copy-id -i userx-ssh-keypair.pub  userx@141.72.12.138`
        3. :console:`ssh -i ./userx-ssh-keypair 'userx@141.72.12.138'`

.. [#]  Nutzen Sie Google/LLMs nur, wenn die Manpages und die Hilfe der Programme Ihnen nicht weiterhelfen.


.. class:: exercises

Übung - Starten eines kleinen WebServers
-----------------------------------------

.. exercise:: Unix - Grundlegende Operationen

    .. class:: incremental-list list-with-explanations

    1. Erzeugen Sie ein Verzeichnis mit Ihrem Nachnamen (z.B. "eichberg")
    2. Legen Sie in dem Verzeichnis eine rundimentäre ``index.html`` Datei an.

       (Sie können zum Beispiel ``pico``, ``vim`` oder ``echo`` verwenden!)
    3. Starten Sie einen Webserver mit dem gerade angelegten Verzeichnis als Wurzel. Wählen Sie eine zufälligen Port XXXX > 1024 aus.

       (Zum Beispiel können Sie den bereits installierten Webserver mittels ``http-server`` wie folgt starten. Im Folgenden ist der Port ``XXXX`` und das Wurzelverzeichnis für Ihre Webseiten "``eichberg``":
        :console:`http-server -p XXXX eichberg`).
    4. Wechseln Sie in Ihren Browser und öffnen sie die entsprechende Webseite.

       (Denken Sie daran, dass dieser Server nur http und nicht https unterstützt.)
    5. Beenden Sie den WebServers
    6. Löschen Sie das gerade angelegte Verzeichnis.

    .. solution::
        :pwd: ServerStartenIstNichtSchwer

        Wenn wir davon ausgehen, dass der Name "eichberg" ist und der zugewiesene Port 8888, dann wären die folgenden Befehle eine mögliche Lösung:

        .. code:: console
            :number-lines:

            mkdir eichberg
            cd eichberg
            echo "<b>Hello World</b>" > index.html
            cd ..
            http-server -p 8888 eichberg

        ::

            <Webbrowser öffnen und Seite besuchen.>
            <CTRL+C>

        .. code:: console
            :number-lines: 6

            rm eichberg/index.html
            rmdir eichberg

        Die zu verwendende URL ist:

        http://<IP des Servers>:8888


.. class:: exercises

Übung - Starten des Servers im Hintergrund
-------------------------------------------

.. exercise:: nohup
    :formatted-title: nohup

    1. Starten Sie den Webserver im Hintergrund und leiten Sie die Ausgaben in eine Log-Datei um. Nutzen Sie ``nohup`` als Tool.

       .. example::

         :console:`nohup http-server -p 8888 eichberg > web.log 2>&1 &`

    2. Überprüfen Sie, ob der Server im Hintergrund läuft. D. h. loggen sie sich aus und rufen Sie danach wieder die Webseite auf.
    3. Loggen Sie sich wieder ein und beenden Sie den Server mittels ``kill`` Befehl. Die PID können Sie mittels :console:`ps -aux | grep http-server` herausfinden.



.. class:: exercises

Übung - Installation von Software als normaler Nutzer
-------------------------------------------------------

.. exercise:: NVM installieren

    Installieren Sie nvm (Node Version Manager), um im zweiten Schritt eine aktuelle Version von node.js zu installieren.

    https://github.com/nvm-sh/nvm?tab=readme-ov-file#installing-and-updating

    .. solution::
        :pwd: LesenIstWichtig

        1. Script herunterladen: :console:`curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.3/install.sh | bash` (die Verwendung von wget ist auch möglich!)

        2. Ausloggen und einloggen (oder "Sourcen")

        3. Aktuellste Version von Node installieren: :console:`nvm install node`



.. class:: exercises

Übung - Daten transferieren
-----------------------------------------

.. exercise:: Secure Copy (scp) nutzen zum Kopieren
    :formatted-title: Secure Copy (:console:`scp`) nutzen

    Nutzen Sie das Programm :console:`scp`, um eine HTML Datei von Ihrem lokalen Rechner auf den Zielserver zu kopieren.

    1. Nutzen Sie die Hilfe zu :console:`scp`, um herauszufinden wie Sie eine Datei auf den Server in das richtige Verzeichnis kopieren können.
    2. Öffnen Sie Ihren Browser, um zu prüfen, dass Sie auf die kopierte Webseite zugreifen können.

    .. solution::
        :pwd: scp...verstehen

        .. rubric:: Verwendung von :console:`scp` bei Verwendung eines Identity-Files.

        Kopiert die lokale Datei ``tips.md`` in das Rootverzeichnis auf dem Server.

        .. code:: bash

            scp -i lectures_stack_dhbw_cloud tips.md eichberg@141.72.12.103:~

        .. remark::

            Wird der -i Parameter weggelassen, dann wird nach dem Passwort gefragt.



.. class:: new-section transition-move-to-top

(Text-)Dateien und Ihre Bearbeitung
--------------------------------------




Die grundlegenden Datenströme
------------------------------------------

Jedes Programm hat immer Zugriff auf die drei Standarddatenströme:

- ``stdin`` - Eingabedatenstrom.
- ``stdout`` - Ausgabedatenstrom für „normale“ Nachrichten.
- ``stderr`` - Ausgabedatenstrom für Fehlernachrichten.

.. container:: incremental

    Diese Datenströme sind (auch nur) Dateien, mit einem festgelegten Dateideskriptor (:eng:`file descriptor`):

    :0: ``stdin``
    :1: ``stdout``
    :2: ``stderr``


Umleitung von ``stdin`` in Dateien
---------------------------------------------

``>`` leitet die Ausgabe eines Programmes/Kommandos in eine Datei um; löscht bzw. legt die Zieldatei bei Bedarf an.

.. class:: incremental

``>>`` leitet die Ausgabe eines Programmes/Kommandos in eine Datei um; hängt die Ausgabe an das Ende einer bestehenden Datei an bzw. legt die Zieldatei bei Bedarf an.

.. example::
    :class: incremental

    Erzeugen einer Datei ``tmp/0s.txt``, die 1024 mal den Wert 0 in Base64 Kodierung enthält.

    .. code:: bash
        :number-lines:
        :class: copy-to-clipboard

        dd if=/dev/zero bs=1 count=1024 | base64 \ # 1024 * "0" in base64
        >                                        \ # Umleitung der Ausgabe
        /tmp/0s.txt                                # in /tmp/0s.txt

    .. remark::

        Der ``\`` wird dazu verwendet, lange Shellkommandos über mehrere Zeilen schreiben zu können. Die # leitet ein "End-of-line Comment" ein.



Umleiten von bestimmten Ausgaben an Dateien
---------------------------------------------

Beim Umleiten von Ausgaben an eine Datei, kann der Dateideskriptor angegeben werden: ``[FD]><Ziel>``

.. container:: incremental

    ``2>`` leitet z. B. die Fehlerausgaben (:eng:`stderr`) eines Programmes/Kommandos in eine Datei um; löscht bzw. legt die Zieldatei bei Bedarf an.

.. container:: incremental

    .. example::

        Finden von bestimmten Dateien; aber Fehlerausgaben während des Suchprozesses ignorieren.

        .. code:: bash
            :number-lines:
            :class: copy-to-clipboard

            find / -iname "*txt*" -type f \
            2>/dev/null           # Umleitung aller Fehler nach /dev/null



Grundlegende Prinzipien: Lesen aus einer Datei
----------------------------------------------

``<`` liest den Inhalt einer Datei und leitet diesen an das Programm/Kommando weiter; d. h. stellt den Inhalt über stdin zur Verfügung.

.. example::
    :class: incremental

    Finden aller Städte, die mit "B" beginnen.\ [#]_

    .. code:: zsh
        :number-lines:
        :class: copy-to-clipboard

        grep B \             # filtert alle Zeilen, die ein "B" enthalten
           < Big\ Cities.txt # der Inhalt von Big Cities.txt wird über stdin
                             # zur Verfügung gestellt

.. [#] In diesem Fall könnte die Datei (``Big Cities.txt``) auch direkt als Parameter an ``grep`` übergeben werden. In anderen Fällen ist dies aber nicht möglich.



Linux Shell - Grundlegendes Design-Pattern: **Pipes and Filters**
------------------------------------------------------------------

- Grundlegendes Konzept bzw. Entwurfsmuster (:eng:`Design-Pattern`) in Unix-basierten Betriebssystemen.
- Ermöglicht die effiziente Verkettung von Befehlen. Die „Pipes-and-Filter“ Architektur erlaubt es komplexe Verarbeitungsoperationen mit Hilfe der Kombination von einfachen Befehlen durchzuführen.

.. class:: incremental

``|``: Verbindet den Ausgabestrom (``stdout``) des vorhergehenden Befehls mit dem Eingabestrom (``stdin``) des nachfolgenden Befehls.


.. example::
    :class: incremental

    Konvertierung des Wortes ``Test`` in Base64 Kodierung.

    .. code:: bash
        :number-lines:
        :class: copy-to-clipboard

        echo -n "Test" \
        |              \ # Weiterleitung der Ausgabe von echo an base64.
        base64

.. supplemental::

    „Filter“: Kommandos/Programme, die von ``stdin`` lesen und nach ``stdout`` schreiben.



Wichtige Linux Kommandozeilenwerkzeuge für die Verarbeitung von Passwortkandidaten
-----------------------------------------------------------------------------------

.. story::

    .. class:: incremental-list

    :cat: Dateien verketten.

    :sed: Strom Editor.

    :grep: Mustersuche auf Dateien.

    :tr: Ersetzung und Löschung von Zeichen.

    :uniq: Filtert wiederholte aufeinanderfolgende Zeilen in einer Datei.

    :sort: Sortiert Dateien.

    :echo: Schreibt Argumente auf *Standard Out* (``stdout``).

    :wc: Zählt die Zeichen, Wörter, Zeilen einer Datei.

    :comm: Vergleicht sortierte Listen und filtert entsprechend.

    :find: Auswertung eines Ausdrucks für jede Datei während eines rekusiven Abstiegs über den Verzeichnisbaum.

    :awk: Muster-orientierte Verarbeitung der Zeilen einer Eingabedatei.

    :base64: (De-)Kodierung von Daten in Base64 Kodierung.

    :rev: Dreht die Reihenfolge der Zeichen einer Zeile um.

    :head: Zeigt die ersten (``-n``) Zeilen einer Datei an.

    :tail: Zeigt die letzten (``-n``) Zeilen einer Datei an. :raw-html:`<br>`
           (``-f`` folgt der Datei, d. h. wartet auf weitere Daten, die der Datei hinzugefügt werden.)

.. supplemental::

    **Anwendungsfälle**

    Typischerweise werden diese Werkzeuge bei der Verarbeitung von Leaks/Aufbereitung von Wörterbüchern im Vorfeld gebraucht - vor dem eigentlichen Versuch das Passwort wiederherzustellen.



echo
-------

- Universell eingesetzt, um Inhalte in Dateien zu schreiben bzw. anzuhängen.
- ``-n`` um das automatische Anhängen von Zeilenumbrüchen zu unterdrücken.
  :peripheral:`(Besonders dann wichtig, wenn man Hashes für Testdaten generieren will.)`
- Entweder ein explizites Programm oder ein in die Shell eingebautes Kommando.

.. container:: incremental

    .. note::
        :class: width-30

        Der hier zu sehende Befehl ``shasum -a 256`` ist unter einigen Linuxdistributionen einfach ``sha256sum``.

    **Anwendungsfall**: Programmatisch Daten nach ``stdout`` schreiben.

    .. code:: bash
        :number-lines:

        $ echo -n "TestPasswort"
          | shasum -a 256
          | sed -E 's/  -$//'
        2214db3d6fca761041242b9fc41fdcca
        f0b2c7f556b80c0a91cfe6994437d807



cat
------

- Liest alle Dateien sequentiell ein und schreibt diese auf ``Standard Out`` (stdout).
- "``-``" repräsentiert ``Standard In`` (stdin); dies ermöglicht die Verwendung von cat mitten in einer Verarbeitungskette.
- Liest (ggf.) von ``stdin`` bis zur EOF :eng:`End-of-File` Markierung.

  (Das Einlesen von der Kommandozeile kann mit ``CTR+D`` beendet werden.)

.. container:: incremental

    **Anwendungsfall**: Mehrere Teilwörterbücher sollen zusammengefügt werden.

    Inhalt von Test1.txt: ``Test1``

    Inhalt von Test2.txt: ``Test2``

    .. code:: bash
        :number-lines:

        $ echo "Test3" | cat Test1.txt Test2.txt -
        Test1
        Test2
        Test3



tr
------

- Kopiert die Eingabe von ``stdin`` nach ``stdout`` und führt dabei Substitutionen und Löschungen durch.

.. container:: incremental

    **Anwendungsfall**: bestimmte Buchstaben - zum Beispiel Sonderzeichen - sollen gelöscht werden.

    .. code:: bash
        :number-lines:

        $ echo -n 'ab.cd_12!' | tr -dc '[:alnum:]'  # -dc = delete complement
        abcd12

.. container:: incremental

    **Anwendungsfall**: Groß- in Kleinbuchstaben verwandeln.

    .. code:: bash
        :number-lines:

        $ echo -n 'STARK' | tr '[:upper:]' '[:lower:]'
        stark



uniq
------

- vergleicht nebeneinanderliegende Zeilen und schreibt jede einzigartige Zeile einmal nach ``stdout``. Nicht-nebeneinanderliegende Wiederholungen werden nicht erkannt.
- ``-c`` erlaubt es die Anzahl der Wiederholungen zu zählen.

.. container:: incremental

    **Anwendungsfall**: Wir möchten eine alphabetisch sortierte Liste nach der Häufigkeit des Vorkommens eines Wortes sortieren.

    Mittels ``uniq`` kann die Häufigkeit gezählt werden.

    :peripheral:`Die Sortierung - zum Beispiel angefangen mit den am häufigsten vorkommenden Einträgen - kann danach im Nachgang erfolgen`.

    .. code:: bash
        :number-lines:

        $ echo "Test\nTest\nSchlaraffenland\nTest" | uniq -c
        2 Test
        1 Schlaraffenland
        1 Test



awk
------

- Muster-orientierte Verarbeitung der Zeilen einer Eingabedatei.
- Jede Zeile wird segmentiert (Standardmäßig basierend auf Leerzeichen), die einzelnen Segmente werden mit ``$1``, ``$2``, ... bezeichnet. ``$0`` steht für die ganze Zeile.
- Die Verarbeitung erfolgt durch Muster-Handlungsanweisungen der Form:

  .. code:: awk

     pattern { action }

  ist das Muster (:eng:`pattern`) leer, dann wird die Zeile immer verarbeitet; ist keine Handlungsanweisung (:eng:`action`) angegeben, dann wird die Zeile ausgegeben.

.. container:: incremental

    **Anwendungsfall**: Die Einträge einer Datei sollen nach länge sortiert werden. In diesem Fall, kann mit Hilfe von awk jede Zeile mit der Länge ausgegeben werden. :peripheral:`Danach kann die Liste entsprechend sortiert werden.`

    .. code:: bash
        :number-lines:

        $ echo "Test\nSchlaraffenland" |  awk '{print length " " $1}'
        4 Test
        15 Schlaraffenland



sort
----

- Sortiert eine Liste gemäß der entsprechenden Felder.
- ``-r`` sortiert in absteigender Reihenfolge.
- ``-n`` der Wert des ersten Feldes wird als numerischer Wert interpretiert.
- ``-k`` spezifiziert das Feld, nach dem sortiert werden soll. (z. B. -k 3)
- ``-t`` spezifiziert das Trennzeichen, das die Felder trennt. (z. B. -t ',')

.. container:: incremental

    **Anwendungsfall**: Sortiere eine Liste nach Häufigkeit des Vorkommens eines Wortes.

    .. code:: bash
        :number-lines:

        $ echo "abc\nxyz\nuvw\nxyz" \
          | sort \                  # alphabetische Sortierung
          | uniq -c \               # zähle Häuigkeit des Vorkommens einer Zeile
          | sort -nr \              # absteigende Sortierung
          | sed -E 's/ *[0-9]+ *//' # entferne den Zähler
        xyz
        uvw
        abc

.. supplemental::

    **Komplexes Beispiel**

    Sortierung einer Liste von Worten in absteigender Reihenfolge bzgl. (1) der Häufigkeit und (2) Länge.

    .. code:: bash
        :number-lines:

        $ printf '%s' "abc\nuvw\nxyz\nlmnop\nxyz\nuvw" \
               "\nlmnop\nlmnop\nxyz\ncd\ncd\ncd" \
          | awk '{print length " " $1}'
          | sort
          | uniq -c
          | sort -nr -k 1 -k 2
        3 5 lmnop
        3 3 xyz
        3 2 cd
        2 3 uvw
        1 3 abc

    Sortierung einer Liste von Worten in absteigender Reihenfolge bzgl. (1) der Häufigkeit und (2) aufsteigend bzgl. der Länge.

    .. code:: bash
        :number-lines:

        $ echo "abc\n" "uvw\n" "xyz\n" "lmnop\n" "xyz\n" "uvw\n" \
               "lmnop\n" "lmnop\n" "xyz\n" "cd\n" "cd\n" "cd" \
          | awk '{print length " " $1}' \
          | sort | uniq -c \
          | sort  -k1nr -k2n
        3 3 cd
        3 4 xyz
        3 6 lmnop
        2 4 uvw
        1 3 abc



base64
------

Base64 kodierte Werte bestehen nur noch aus gültigen ASCII Zeichen und können als "Text" gespeichet/übermittelt werden kann.

.. note::
    :class: width-40

    Je nach Betriebssystem/Konfiguration ist der Befehl unter Umständen etwas anders benannt. Grundlegend gibt es den Befehl auf allen Unixoiden.

.. container:: incremental

    **Anwendungsfall**: In vielen Fällen können gehashte Passworte nicht roh (d. h. als Binärdaten) gespeichert werden sondern müssen `Base64 <https://datatracker.ietf.org/doc/html/rfc4648#section-4>`__ (oder vergleichbar) kodiert werden.

    .. code:: bash
        :number-lines:

        # Codierung
        $ echo "Dies_ist_ein_test" | base64
        RGllc19pc3RfZWluX3Rlc3QK
        $ echo 'Dies_ist_ein_test!' | base64
        RGllc19pc3RfZWluX3Rlc3QhCg==

        # Dekodierung
        $ echo REhCVyBNYW5uaGVpbQ== | base64 --decode
        DHBW Mannheim



grep
-----

- Selektiert Zeilen, die einem gegebenen Muster entsprechen.
- ``-o`` gibt nur den Teil einer Zeile aus, der dem Muster entspricht.
- ``-v`` selektiert Zeilen für die kein Teil der Zeile dem Muster entspricht.
- ``-E`` erlaubt die Spezifikation von Mustern mit Hilfe von regulären Ausdrücken.
- ``-i`` ignoriert Groß-/Kleinschreibung (in Verbindung mit -E mgl. verwirrend).
- ``-P`` Perl kompatible Ausdrücke

.. container:: incremental

    **Anwendungsfall**: Alle Textfragmente in einem Leak finden\ :peripheral:`, um danach mit Regeln neue Passwortkandidaten zu bilden`.

    .. code:: bash
        :number-lines:

        $ echo "Test123\nmichael@dhbw.de\n345test@dhbw.de\nEnde__" \
          | grep -Eo "[a-zA-Z]{3,}" | sort -u
        Ende
        Test
        dhbw
        michael
        test



sed - Stromeditor
-------------------

- modifiziert die Eingabe gemäß der spezifizierten Kommandos in der angegebenen Reihenfolge.
- ``-E`` zur Verwendung moderner regulärer Ausdrücke
- Standardform: ``Funktion[Agrumente]``
- Substitutionen: ``s/Regulärer Ausdruck/Ersatz/[Kennzeichen]``; das Kennzeichen "``g``" z. B. bewirkt, dass jedes Vorkommen ersetzt wird; sonst nur das erste Vorkommen.

.. container:: incremental

    **Anwendungsfall**: Löschen des ersten Sonderzeichens in einer Zeile.

    .. code:: bash
        :number-lines:

        $ echo 'ab_cd!_ef?' | sed -E  's/[^a-zA-Z0-9]//'
        abcd!_ef?


.. container:: incremental

    **Anwendungsfall**: Analyse der Struktur eines Leaks durch das Abbilden **aller** Buchstaben auf die Repräsentanten: ``l``\ (lower) ``u``\ (upper) ``d``\ (digits) ``s``\ (special).

    .. code:: bash
        :number-lines:

        $ echo 'aB_c1d!_ef?' |
          sed -E -e's/[a-z]/l/g' -e's/[A-Z]/u/g' -e's/[0-9]/d/g' -e 's/[^lud]/s/g'
        lusldlsslls

.. supplemental::

    .. hint::

        ``sed`` auf dem Mac (BSD) und ``sed`` unter Linux (GNU) unterscheiden sich teilweise deutlich.



find
-------

- durchläuft den Dateibaum ab einer angegebenen Stelle und evaluiert dabei Ausdrücke.
- ``-iname`` Testet ob der Verzeichniseintrag - unabhängig von der Groß- und Kleinschreibung - dem gegebenen Muster entspricht.
- ``-exec ... {} ... \;`` ermöglicht es für jede gefilterte Datei ``{}`` einen Befehl auszuführen.

.. container:: incremental

    **Anwendungsfall**: Feststellen wie lange die Hashes sind.

    .. code:: bash
        :number-lines:

        $ find . -iname "*hash*" -exec wc -c {} \;
        33 ./saltedmd5/hash.md5
        38 ./saltedmd5/saltedhash.md5
        129 ./scenario5/hash.sha125
        65 ./scenario6/hash.sha256
        65 ./scenario7/hash.sha256
        65 ./scenario9/hash.sha256



Software nachinstallieren
---------------------------

- Auf allen Linux und BSD Distributionen können Softwarepakete durch den Paketmanager des Betriebssystems nachinstalliert werden, z. B.:

  - ``apt`` (Debian, Ubuntu, Kali Linux, ...)

  .. class:: peripheral

  - ``yum`` (RedHat, CentOS, ...)
  - ``pacman`` (Arch Linux, ...)
  - ``brew`` oder ``macports`` (MacOS) [*]_

.. [*] Beide sind in diesem Fall nicht Teil des Betriebssystems, sondern müssen erst nachinstalliert werden, bevor damit weitere Software nachinstalliert werden kann.

.. container:: incremental

    **Anwendungsfall**: Installieren von ``ent`` (ein Programm, das die Entropie von Dateien berechnet):

    .. code:: bash
        :class: copy-to-clipboard
        :number-lines:

        sudo apt install ent



Shellprogrammierung
----------------------

- Jede Shell (insbesondere: ``zsh`` (auf Mac und Kali Linux) und ``bash`` (Debian, Ubuntu, ...)) erlaubt es prozedurale Programme zu schreiben.

.. container:: incremental

    **Anwendungsfall**: Berechnung der Entropie für jede Datei in einer Liste.

    .. code:: zsh
        :class: copy-to-clipboard
        :number-lines:

        #!/usr/bin/zsh                   # Shebang zur Spezifikation der Shell
        IFS=$'\n'                        # IFS = Internal Field Separator
                                         # (Nur Zeilenumbrüche sind Trennzeichen)
        rm Files.list.assessed           # Lösche die Ausgabedatei
        for i in $(cat Files.list); do   # Iteriere über die Zeilen in Files.list
            echo "Processing: ""$i"
            ent -t "$i" | \              # Berechne die Entropie
            grep -E "^1" | \             # Selektiere die Zeile mit der Entropie
            tr -d '\n' | \               # Lösche den Zeilenumbruch
            cat - <(echo ",""$i") \      # Füge den Dateinamen hinzu
                >> Files.list.assessed ; # Schreibe das Ergebnis
        done;




.. class:: transition-scale exercises

Fingerübungen
-------------------

.. admonition:: Voraussetzung

    Starten Sie z. B. Kali Linux (oder eine entsprechende VM), loggen Sie sich ein und starten Sie ein Terminal.

.. exercise:: Dateien finden

    Finden Sie die Datei, die die Standardpassworte von Postgres Datenbanken enthält (der Dateiname enthält sowohl ``postgres`` als auch ``pass``).

    .. solution::
        :pwd: find_mit_iname

        .. code:: bash

            $ find /  -iname "*postgres*pass*" -type f 2>/dev/null

.. exercise:: MD5 Hash berechnen

    Konkatenieren sie die Zeichenkette „MySalt“ (ohne Zeilenumbruch!) mit dem Inhalt von rockyou.txt (als Ganzes) und berechnen Sie davon den md5 Hash. Verwenden Sie keine expliziten Zwischenergebnisse.

    .. solution::
        :pwd: ech_cat_md5sum

        .. code:: bash

            $ echo -n "MySalt" | cat - /usr/share/wordlists/rockyou.txt |\
               md5sum

            4e50fd427d675821b68c61a4c6099ea0  -

.. exercise:: Base64

    Erzeugen Sie für eine Datei (z. B. ``/usr/bin/wc``) einen MD5 hash und stellen Sie diesen der Datei selber voran bevor sie alles nach Base64 konvertieren.

    .. solution::
        :pwd: md5sum_base64

        .. code:: bash

            $ md5sum /usr/bin/wc | cat - /usr/bin/wc | base64
