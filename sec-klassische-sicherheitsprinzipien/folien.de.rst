.. meta::
    :author: Michael Eichberg
    :keywords: "Sicherheitsprinzipien"
    :description lang=de: Einführung in klassische Sicherheitsprinzipien
    :id: lecture-security-einfuehrung-in-sicherheitsprinzipien
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Klassische Sicherheitsprinzipien
===================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.2.4

.. supplemental::

    :Folien:
        [HTML] |html-source|

        [PDF] |pdf-source|

    :Kontrollfragen:

        .. source:: kontrollfragen.de.rst
            :path: relative
            :prefix: https://delors.github.io/
            :suffix: .html

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



Klassische Sicherheitsprinzipien
-----------------------------------------------

(Jerome Saltzer and Michael Schroeder, 1975)

.. story::

    .. class:: incremental-list dd-margin-left-4em

    :Principle of Economy of Mechanism (aka Principle of Simplicity): Die Sicherheitsmechanismen sollten so einfach wie möglich sein.

        .. supplemental::

            :Principle of Economy of Mechanism (aka Principle of Simplicity): Dies fördert zum Beispiel die Korrektheit der Implementierung/Anwendung, da diese schneller verstanden wird und auch einfacher getestet werden kann. Weiterhin reduziert es die Angriffsfläche.

    :Principle of Fail-Safe Defaults: Standardmäßig sollte der Zugriff auf Ressourcen verweigert werden.

    :Principle of Complete Mediation: Zugriffsanfragen eines Subjekts auf ein Objekt werden *jedes Mal* vollständig auf ihre Zulässigkeit hin überprüft.

        .. remark::

            Bei modernen Zero-Trust-Ansätzen gilt, dass jeder Zugriff erneut authentifiziert und autorisiert wird. Dies ist eine Umsetzung des *Principle of Complete Mediation*.

        .. supplemental::

            :Principle of Complete Mediation: Bei der Entwicklung einer Serveranwendung besagt das *Principle of Complete Mediation* somit, dass bei jeder Anfrage zu überprüfen ist, ob der Nutzer die entsprechenden Rechte besitzt.



    :Principle of Least Authority (aka POLA)/ Principle of Least Privilege: Jedes Programm und jeder Benutzer sollte nur die für seine Aufgabe unbedingt notwendigen Rechte besitzen.

    :Principle of Separation of Privilege: Ein System sollte in mehrere POLA konforme Komponenten unterteilt sein. Sollte eine Komponente kompromittiert sein, dann sind die Möglichkeiten des Angreifers dennoch begrenzt. D. h. kritische Operationen sollten nur ausgeführt werden, wenn mehrere Bedingungen erfüllt sind.

        (Eng verwandt mit dem POLA.)

        .. supplemental::

            :*Principle of Separation of Privilege*: Dieses Prinzip wird dann eingehalten, wenn ein Angreifer, der eine Komponente kompromittiert hat, nicht die Rechte erhält, die notwendig sind, um das gesamte System zu kompromittieren. Zum Beispiel ist es für Überweisungen notwendig, diese auf zwei verschiedene Arten zu autorisieren.

                .. attention::

                    *Privilege Separation* (für Programme) sollte nicht mit dem hier beschriebenen Prinzip verwechselt werden. *Privilege Separation* liegt zum Beispiel dann vor, wenn ein Programm in zwei Teile aufgeteilt ist und ein Teil - zum Beispiel zum Zugriff auf Betriebssystemressourcen wie Sockets oder bestimmte Dateien - erhöhte Rechte benötigt als der Rest vom Programm. In diesem Fall erfolgt dann der Austausch zwischen den beiden Teilen über eine wohldefinierte, minimale Schnittstelle, die die Rechte des ersten Teils auf das notwendige Minimum beschränkt.

    :Principle of Least Common Mechanism: Die Sicherheitsmechanismen sollten über Nutzer (
         insbesondere aber auch Programme, die andere Programme nutzen) hinweg möglichst wenig gemeinsam haben.

        .. supplemental::

            **Beispiel - Principle of Least Common Mechanism**
            (~ :ger:`Grundsatz des kleinsten gemeinsamen Mechanismus``)

            Das Prinzip besagt zum Beispiel, dass die Mechanismen, die von mehreren Benutzern verwendet werden oder von dem mehrere Nutzer abhängen, minimiert werden sollten.

            Das Prinzip kann/sollte auf ganz verschiedenen Ebenen angewendet werden:

            - Z. B. sollten keine gemeinsamen Speicherbereiche verwendet werden in denen möglicherweise sicherheitsrelevantes Material vorgehalten wird. Es ist deswegen z. B. sinnvoll - wenn möglich - auf Implementierungen im Kernel zu verzichten und statt dessen auf User-Space-Implementierungen zu setzen.

            TCP Connection Hijacking Angriffe werden bzw. wurden z. B. durch die Implementierung des TCP Stacks im Kernel ermöglicht (:math:`\Leftrightarrow` „Principle of Least Common Mechanism“).

            - Z. B. sollten keine geteilten Passworte verwendet werden, um sich gegenüber einem System zu authentifizieren. (Dies bezieht sich sowohl auf die Passwörter einer Person als auch auf Passwörter über Personen und Systemgrenzen hinweg!)

    :Principle of Open Design: Das Prinzip besagt, dass die Sicherheit eines Mechanismus nicht von der Geheimhaltung seines Designs oder seiner Implementierung abhängen sollte.

    (Vgl. Kerckhoffs Prinzip: Die Sicherheit eines Kryptosystems sollte nur von der Geheimhaltung des Schlüssels abhängen.)
    
    :Principle of Psychological Acceptability: Die Sicherheitsmechanismen sollten einfach zu verstehen und zu benutzen sein.

    :Principle of Isolation: Die Sicherheitsmechanismen sollten so entworfen sein, dass Fehler in einem Teil des Systems nicht die Sicherheit des gesamten Systems gefährden; d. h. die einzelnen Komponenten sollten möglichst unabhängig voneinander sein und nur über wohldefinierte  Schnittstellen miteinander kommunizieren und entsprechende Sicherheitsüberprüfungen durchführen.

        .. supplemental::

            **Beispiel - Principle of Isolation:**

            1. Virtuelle Maschinen können genutzt werden, um Anwendungen in einer isolierten Umgebung auszuführen. Ein Angreifer, der eine Anwendung kompromittiert hat, kann somit nicht auf andere Anwendungen oder das Betriebssystem zugreifen.

            2. Typischerweise kommuniziert zum Beispiel ein Basebandchip (WIFI, LTE, 5G, ...) mit dem Betriebssystem über eine minimale Schnittstelle über die nur Nachrichten übermittelt werden können, die leicht auf ihre Korrektheit überprüft werden können. Insbesondere erfolgt kein direkter Zugriff auf den Speicher des Betriebssystems.

            Einen Angreifer ist es somit ggf. möglich den Basebandchip anzugreifen und ggf. zu kompromittieren, aber er kann nicht direkt auf das Betriebssystem zugreifen und Nachrichten, die bereits auf Betriebssystem oder Anwendungsebene verschlüsselt werden, sind weiterhin sicher.

    :Principle of Modularity: Die Sicherheitsmechanismen sollten so entworfen sein, dass sie unabhängig voneinander implementiert und geprüft werden können.

    :Principle of Layering: Die Sicherheitsmechanismen sollten in Schichten organisiert sein.

        .. supplemental::

            Beispiel für ein Schutzsystem für Netzwerke, dass mehrere Schichten verwendet:

            - einfache (und effiziente) Paketfilter auf unterster Ebene
            - zustandsbehaftete Paketfilter auf der nächsten bzw. der Anwendungsebene

    :Principle of Least Astonishment: Die Sicherheitsmechanismen sollten so entworfen sein, dass sie keine Überraschungen für die Benutzer bereithalten.



.. class:: exercises

Übung
-----------------------------------------------

.. story::

    .. exercise:: Principle of Open Design
        :class: incremental

        Benennen Sie ein historisches Verschlüsselungsverfahren, das gegen das *Principle of Open Design* verstoßen hat.

        .. solution::
            :pwd: Caesar

            Das Verschlüsselungsverfahren von Caesar verletzt das *Principle of Open Design*, da die Sicherheit des Verfahrens von der Geheimhaltung des Verfahrens abhängt. Selbst zu Zeiten Caesars wäre ein Brute-Force Angriff trivial möglich gewesen.

    .. exercise:: Verletzung
        :class: incremental

        Stellen Sie sich vor, dass Sie als Pin (z. B. für ein Tablet) folgende Zahl verwenden wollen, diese aber abgelehnt wird (Leerzeichen dienen nur der Lesbarkeit):

        ``3671 1197 4769``

        Während als Pin das folgende Passwort akzeptiert wird:

        ``1364 7964 1364``

        Wie bewerten Sie dies?

        .. hint::

            Schauen Sie sich ggf. ein Pinpad an.

        .. solution::
            :pwd: PrincipleOfLeastAstonishment

            Bei der zweiten Pin handelt es sich um einen einfachen *Keypad Walk*. Während die erste Pin eine (scheinbar ?) zufällige Zahlenfolge ist. Das *Principle of Least Astonishment* wird hier verletzt, da der Benutzer davon ausgehen würde, dass, wenn die erste Pin nicht akzeptiert wird, die zweite Pin erst recht nicht akzeptiert wird.



.. class:: exercises

Übung
-----------------------------------------------

.. exercise:: Browser

    Der Chrome-Browser (zum Beispiel) unterstützt die so genannten `Isolierung von besuchten Webseiten <https://support.google.com/chrome/a/answer/7581529?hl=en>`__.
    Bei dieser werden Seiten von verschiedenen Websites in unterschiedliche Prozesse aufgeteilt.

    Welches Prinzip bzw. welche Prinzipien wird/werden hier umgesetzt?

    .. solution::
        :pwd: ChromeWasFirst

        Das *Principle of Isolation* wird hier umgesetzt. Ist die Sicherheit einer Webseite kompromittiert, so betrifft dies (hoffentlich) nicht sie Sicherheit der anderen Webseiten.

        Weiterhin wird auch das *Principle of Least Common Mechanism* umgesetzt, da die Webseiten in unterschiedlichen Prozessen laufen und somit viele Angriffsvektoren unterbunden werden, da es nur minimalen bzw. keinen geteilten Speicher gibt.

.. exercise:: Quantum Algorithmen für die Verschlüsselung

    Zukünftige Verschlüsselungsalgorithmen, z. B. solche die auch im Zeitalter der Quantencomputer noch sicher sein sollen, werden häufig im Rahmen von offenen Wettbewerben entwickelt bzw. ausgesucht. Wie bewerten Sie dieses Vorgehen?

    .. solution::
        :pwd: OpenDesignIsTheKey

        Das *Principle of Open Design* wird hier umgesetzt und dient letztlich dazu Sicherheitslücken möglichst früh zu entdecken. Dies hat auch dazu geführt, dass kein Algorithmus, der in den letzten Jahren ausgewählt wurde (insbesondere zum Beispiel AES), von bekannten Sicherheitsproblemen betroffen ist.


.. class:: exercises

Übung
-----------------------------------------------

.. exercise:: Rechte von im Hintergrund laufenden Prozessen auf Servern

    Es ist üblich, dass für Prozesse, die auf Servern im Hintergrund laufen, extra Nutzerkonten eingerichtet werden.

    - Warum ist dies so?
    - Welche Rechte sollten diese „Nutzer“ bekommen?
    - Was sollte weiterhin beachtet werden?

    .. solution::
        :pwd: LeastPrivilege

        Das *Principle of Least Privilege* wird hier umgesetzt. Ein Prozess sollte nur die Rechte bekommen, die er wirklich benötigt. D. h. die Nutzerkonten sollten auf keinen Fall über Administrationsrechte verfügen. Normalerweise sollte auch kein Einloggen möglich sein. Ggf. notwendige Rechte (zum Beispiel für die Verwendung von privilegierten Ports) sollten explizit vergeben werden. Zugriff nur auf die Verzeichnisse/Dateien sollte möglich sein, die wirklich benötigt werden.
