.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "Git", "Versionskontrolle"
    :description lang=de: Software Engineering - Versionskontrolle und Git
    :id: lecture-se-versionskontrolle
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!
    :svg-defs:
        <marker
            id="arrow"
            viewBox="0 0 10 10"
            refX="7.5"
            refY="5"
            markerUnits="strokeWidth"
            markerWidth="4"
            markerHeight="4"
            orient="auto-start-reverse">
            <path d="M 0 0 L 10 5 L 0 10 z" />
        </marker>
        <g id="star">
            <polygon
                class="star"
                points="12,2 15,9 22,9 16,14 18,21 12,17 6,21 8,14 2,9 9,9"
                fill="gold"
                stroke="black"
                stroke-width="1"
                style="transform: scale(0.05)"/>
        </g>
    :svg-style:
        .svg-life-line {
            stroke:rgb(0,0,0);
            stroke-width:0.2ch;
            stroke-dasharray: 1 1;
        }
        .svg-action {
            stroke:rgb(0,0,0);
            stroke-width:0.2;
            marker-end:url("#arrow");
        }

.. include:: ../docutils.defs

.. |RPN.zip| source:: exercise/RPN.zip
    :prefix: https://delors.github.io/
    :path: relative



Software Engineering - Versionskontrolle (mit Git)\ [#]_
===================================================================

Eine allererste Einführung

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.0

.. [#]

    Die Folien basieren in Teilen auf Folien von Dr. Helm und Prof. Dr. Hermann.

    Alle Fehler sind meine eigenen.

.. supplemental::

    :Folien:
        [HTML] |html-source|

        [PDF] |pdf-source|
    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues

    :Kontrollfragen:

        .. source:: kontrollfragen.de.rst
            :path: relative
            :prefix: https://delors.github.io/
            :suffix: .html



.. class:: new-section  transition-fade

Versionsmanagement und Versionskontrollsysteme
--------------------------------------------------



Aspekte der Softwareentwicklung
---------------------------------

.. deck::

    .. card::

        .. observation::

            .. deck:: numbered

                .. card::

                    .. class:: incremental-list

                    - Softwareentwicklung ist Teamarbeit
                    - Viel (indirekte) Kommunikation nötig
                    - Entwicklungswissen muss dokumentiert werden
                    - Software besteht aus vielen Dokumenten:

                      - Lastenheft
                      - Pflichtenheft
                      - Analyse- und Designdokument
                      - Programmcode (Quellcode, Skripte, Konfigurationsdateien, ...)
                      - Testdokumentation
                      - Codedokumentation

                .. card::

                    - Verschiedene Personen greifen (gleichzeitig) auf Dokumente zu
                    - Oft bearbeiten verschiedene Personen gleichzeitig (unabhängig voneinander) das selbe Dokument

    .. card::

        .. grid::

            .. cell:: width-50

                .. rubric:: Versionsmanagement

                .. class:: incremental-list list-with-explanations

                - Wo ist die aktuelle Version?
                - Was ist die zuletzt lauffähige Version?
                - Wo ist die Implementierungsversion vom 01. April 2016?

                  Und welche Dokumente beziehen sich auf diese Version?
                - Welche Version wurde dem Kunden präsentiert?

            .. cell:: incremental

                .. rubric:: Änderungsmanagement

                .. class:: incremental-list

                - Was hat sich seit letzter Woche geändert?
                - Wer hat diese Änderung gemacht?
                - Warum wurde diese Änderung gemacht?

    .. card::

        Einfache „*ad-hoc Lösungen*“ bei der Entwicklung von Softwareprojekten

        - Austausch der Dokumente via USB-Stick / Festplatte
        - Austausch der Dokumente via Mail
        - Austausch über Netzwerkfestplatte

        Zusätzlich müssen dann noch Konventionen und Regeln im Team definiert werden.

        .. warning::
            :class: incremental

            Just, don't do it!




Unterstütztes Versionsmanagement - Motivation
----------------------------------------------

.. observation::

    - „Einfache Lösungen“ um Versionen zu verwalten erzeugen neue Probleme
    - Konventionen und Regeln werden nicht eingehalten
    - Koordination ist aufwendig und führt zu Verzögerungen
    - Varianten und Konfigurationen werden von Hand verwaltet
    - Versions- und Änderungsfragen nicht bzw. nur schwer beantwortbar
    - Geistige Kapazität wird mit „Kleinkram“ verschwendet

.. conclusion::
    :class: incremental

    Konventionen müssen technisch erzwungen werden!




Versionskontrollsysteme (VCS) - Überblick
-----------------------------------------------

.. deck::

    .. card::

        .. rubric:: Zweck

        Versionskontrollsysteme verwalten mehrere Versionen des Codes.

        • Erlauben es mehreren Personen gleichzeitig am selben Projekt zu arbeiten
        • Änderungen unterschiedlicher Personen werden teil-automatisch integriert
        • Erhält Historie von Änderungen

    .. card:: dd-margin-left-6em

        .. rubric:: Arten

        .. class:: incremental-list

        :Zentralisierte VCS:
            synchronisieren alle Änderungen in einem zentralen Repository (Subversion, ... )

            .. attention::
                :class: incremental

                Es ist keine Offline-Nutzung möglich.


        :Dezentralisierte VCS:

            können mehrere, unabhängige Repositories haben (Git, Mercurial, ... )

            .. remark::
                :class: incremental

                Dezentralisierte VCS (insbesondere Git) sind heute am weitesten verbreitet.

    .. card:: dd-margin-left-2em

        .. rubric:: Konsistenzmechanismen

        :Optimistische Mechanismen:
            - System erlaubt gleichzeitiges Bearbeiten des Dokuments durch verschiedene Personen
            - System erkennt und integriert die Änderungen (Merging)
            - Evtl. funktioniert das nicht automatisch; dann muss der Konflikt manuell beseitigt werden
        :Pessimistische Mechanismen:
            - System verbietet gleichzeitiges Bearbeiten des Dokuments durch verschiedene Personen (Sperrprotokolle)

        Beide Mechanismen haben Vor- und Nachteile

        .. class:: positive-list

        - Sperren serialisiert die Arbeit

        .. class:: negative-list

        - Mergen kann *in seltenen Fällen* komplex werden und zu Fehlern führen



.. class:: new-section  transition-fade

Versionskontrolle mit Git
----------------------------------



VCS - Git - einfache Verwendung
----------------------------------

.. story::

    .. compound::
        :class: framed

        .. rubric:: Repository auf Server einrichten

        1. Git repository einrichten (Beispielsweise über Web-Frontend wie https://github.com)

        2. Lokale Kopie des Remote-repositories „Klonen”: :console:`git clone <repo-URL> [lokales Verzeichnis]`

        .. rubric:: Repository lokal anlegen

        In einem beliebigen Verzeichnis: :console:`git init`

    .. compound::
        :class: framed incremental

        .. rubric:: Datei neu versionieren

        1. Dateien dem Repository hinzufügen

           :console:`git add <Dateipfade>`

           Dateien landen dann in der sogenannten „Staging Area“.

           .. supplemental::

                Die Staging Area (oder Index) hält alle Änderungen, Hinzufügungen und Löschungen von Dateien, die Teil des nächsten Commits werden sollen.

        2. gestagete Änderungen committen

           :console:`git ci`

           Dies fügt die Änderungen dem lokalen Repository hinzu.



VCS - Git - Prozess
----------------------------------

.. raw:: html
    :class: align-center

    <div style="width: 70ch; height: 18.15ch; container-type:size;">
    <svg viewBox="0 0 54 14" font-size="1" version="1.1" xmlns="http://www.w3.org/2000/svg">
        <g style="fill:white">
            <rect width="6" height="3" x="2" y="0" rx="1" ry="1" style="fill:rgb(71, 65, 254)" />
            <text x="3" y="1.5">Working</text>
            <text x="3" y="2.5">Directory</text>
            <line x1="5" y1="3" x2="5" y2="15" class="svg-life-line"/>
        </g>
        <g class="incremental" style="fill:white">
            <rect width="6" height="3" x="17" y="0" rx="1" ry="1" style="fill:rgb(71, 65, 254)" />
            <text x="18" y="2">Index</text>
            <line x1="20" y1="3" x2="20" y2="15" class="svg-life-line"/>
            <line x1="5" y1="4.5" x2="20" y2="4.5" class="svg-action" />
            <text x="6" y="4.25" style="fill:black">add</text>
        </g>
        <g class="incremental" style="fill:white">
            <rect width="6" height="3" x="32" y="0" rx="1" ry="1" style="fill:rgb(71, 65, 254)" />
            <text x="33" y="1.5">Local</text>
            <text x="33" y="2.5">Repo</text>
            <line x1="35" y1="3" x2="35" y2="15" class="svg-life-line"/>
            <line x1="20" y1="6" x2="35" y2="6" class="svg-action" />
            <text x="21" y="5.75" style="fill:black">commit</text>
        </g>
        <g class="incremental" style="fill:white">
            <rect width="6" height="3" x="47" y="0" rx="1" ry="1" style="fill:rgb(55,155,55)" />
            <text x="48" y="1.5">Remote</text>
            <text x="48" y="2.5">Repo</text>
            <line x1="50" y1="3" x2="50" y2="15" class="svg-life-line"/>
            <line x1="35" y1="7.5" x2="50" y2="7.5" class="svg-action" />
            <text x="36" y="7.25" style="fill:black">push</text>
        </g>
        <g class="incremental" style="fill:black">
            <line x2="35" y1="10.5" x1="50" y2="10.5" class="svg-action" />
            <text x="36" y="10.25" style="fill:black">fetch</text>
            <!-- To get a stable animation: --><circle cx="57" cy="13" r="0.1" fill="white"/>
        </g>
        <g class="incremental" style="fill:black">
            <line x1="35" y1="10.5" x2="5" y2="10.5" class="svg-action" />
            <text x="15" y="10.25" style="fill:black">merge/checkout</text>
            <!-- To get a stable animation: --><circle cx="57" cy="13" r="0.1" fill="white"/>
        </g>
        <g class="incremental" style="fill:black">
            <line x1="50" y1="13" x2="5" y2="13" class="svg-action" />
            <text x="25" y="12.75" style="fill:black">pull</text>
            <!-- To get a stable animation: --><circle cx="57" cy="13" r="0.1" fill="white"/>
        </g>
    </svg>
    </div>




VCS - Git - Commits - Beispiel
---------------------------------

.. scrollable::

    .. code:: diff
        :number-lines:

        commit ace47c68a2deaa6290344a5f9c2d7749d01f0efc
        Author: Michael Eichberg <mail@michael-eichberg.de>
        Date:   Wed Jan 22 17:43:28 2025 +0100

            encrypted presenter notes

        diff --git a/renaissance/css/core/slide-view.css b/renaissance/css/core/slide-view.css
        index 21d433b..03f010a 100644
        --- a/renaissance/css/core/slide-view.css
        +++ b/renaissance/css/core/slide-view.css
        @@ -47,6 +47,12 @@
                /* The height will be computed by JavaScript depending on the mode. */
            }

        +
        +
        +    /* Presenter Notes */
        +    ld-presenter-note-marker[data-encrypted="true"] {
        +        display: none;
        +    }
        }

        ...


VCS - Git - Commits
--------------------

.. rubric:: Commits beschreiben eine atomare Änderung des Codes

- Hashcode, um den Commit zu identifizieren
- Autor und Zeit des Commits
- Beschreibung der Änderung
- Änderung als Diff: Hinzugefügte und entferne Zeilen je Datei



VCS - Git - Hilfreiches
--------------------------------------------

.. deck::

    .. card::

        .. rubric:: Zwischenspeichern von Änderungen

        Aktuelle Änderungen zwischenspeichern und Working Copy resetten:

        :console:`git stash`

        Hilfreich z. B. wenn man vergessen hat, Änderungen von *Remote* zu pullen.
        Ein pull könnte lokale Änderungen überschreiben, mit :console:`git stash` werden diese Änderungen aber zunächst sicher beiseite gelegt.

        Änderungen vom *Stash* in *Working Copy* zurückspielen:

        :console:`git stash pop`

    .. card::

        .. rubric:: Änderungen in der Working Copy zurücksetzen

        :console:`git reset --hard`

        Setzt alle Änderungen in der Working Copy auf den letzten Commit zurück (z. B. nach einem „Fehlversuch“).

    .. card::

        .. rubric:: Metadaten setzen

        Username und Emailadresse als Metadaten für Commits setzen:

        :console:`git config user.name <name>`

        :console:`git config user.email <e-mail>`


Git Branches
------------

Git verwaltet Versionen von Dokumenten mittels Commits in Branches.


.. deck::

    .. card::

        .. rubric:: Initiales Setup - main ist aktuell auf dieser Version

        .. raw:: html

            <div style="width: 90ch; height:16ch">
            <svg viewBox="0 0 48 8" font-size="0.75" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <rect width="4" height="1" x="0" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="1.5" y="3.75" style="fill:white">A</text>

                <rect width="6" height="1" x="6" y="0" rx="1" ry="1" style="fill:darkorange" />
                <text x="7" y="0.75" style="fill:white">main</text>

                <line x1="2" y1="3" x2="6" y2="0.5" style="stroke:darkorange;stroke-width:0.2"/>
            </svg>
            </div>

    .. card::

        .. rubric:: *main* ist der aktuell ausgecheckte Branch

        .. raw:: html

            <div style="width: 90ch; height:16ch">
            <svg viewBox="0 0 48 8" font-size="0.75"  version="1.1" xmlns="http://www.w3.org/2000/svg">
                <rect width="4" height="1" x="0" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="1.5" y="3.75" style="fill:white">A</text>

                <rect width="6" height="1" x="6" y="0" rx="1" ry="1" style="fill:darkorange" />
                <text x="7" y="0.75" style="fill:white">main</text>

                <line x1="2" y1="3" x2="6" y2="0.5" style="stroke:darkorange;stroke-width:0.2"/>

                <use href="#star" x="11" y="0"/>
            </svg>
            </div>

    .. card::

        .. rubric:: :console:`git branch develop`

        .. raw:: html

            <div style="width: 90ch; height:16ch">
            <svg viewBox="0 0 48 8" font-size="0.75"  version="1.1" xmlns="http://www.w3.org/2000/svg">
                <rect width="4" height="1" x="0" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="1.5" y="3.75" style="fill:white">A</text>

                <rect width="6" height="1" x="6" y="0" rx="1" ry="1" style="fill:darkorange" />
                <text x="7" y="0.75" style="fill:white">main</text>
                <line x1="2" y1="3" x2="6" y2="0.5" style="stroke:darkorange;stroke-width:0.2"/>
                <use href="#star" x="11" y="0"/>

                <rect width="8" height="1" x="6" y="6" rx="1" ry="1" style="fill:darkorange" />
                <text x="7" y="6.75" style="fill:white">develop</text>
                <line x1="2" y1="4" x2="6" y2="6.5" style="stroke:darkorange;stroke-width:0.2"/>
            </svg>
            </div>

    .. card::

        .. rubric:: :console:`git checkout develop`

        .. raw:: html

            <div style="width: 90ch; height:16ch">
            <svg viewBox="0 0 48 8" font-size="0.75"  version="1.1" xmlns="http://www.w3.org/2000/svg">

                <rect width="4" height="1" x="0" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="1.5" y="3.75" style="fill:white">A</text>

                <rect width="6" height="1" x="6" y="0" rx="1" ry="1" style="fill:darkorange" />
                <text x="7" y="0.75" style="fill:white">main</text>
                <line x1="2" y1="3" x2="6" y2="0.5" style="stroke:darkorange;stroke-width:0.2"/>

                <rect width="8" height="1" x="6" y="6" rx="1" ry="1" style="fill:darkorange" />
                <text x="7" y="6.75" style="fill:white">develop</text>
                <line x1="2" y1="4" x2="6" y2="6.5" style="stroke:darkorange;stroke-width:0.2"/>
                <use href="#star" class="star" x="13" y="6" />
            </svg>
            </div>


    .. card::

        .. rubric:: :console:`git commit` von B

        .. raw:: html

            <div style="width: 90ch; height:16ch">
            <svg viewBox="0 0 48 8" font-size="0.75"  version="1.1" xmlns="http://www.w3.org/2000/svg">

                <rect width="4" height="1" x="0" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="1.5" y="3.75" style="fill:white">A</text>

                <rect width="4" height="1" x="8" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="9.5" y="3.75" style="fill:white">B</text>
                <line x1="4" y1="3.5" x2="8" y2="3.5" style="stroke:blue;stroke-width:0.2"/>

                <rect width="6" height="1" x="6" y="0" rx="1" ry="1" style="fill:darkorange" />
                <text x="7" y="0.75" style="fill:white">main</text>
                <line x1="2" y1="3" x2="6" y2="0.5" style="stroke:darkorange;stroke-width:0.2"/>

                <rect width="8" height="1" x="6" y="6" rx="1" ry="1" style="fill:darkorange" />
                <text x="7" y="6.75" style="fill:white">develop</text>
                <line x1="2" y1="4" x2="6" y2="6.5" style="stroke:darkorange;stroke-width:0.2"/>
                <use href="#star" class="star" x="13" y="6" />
            </svg>
            </div>

    .. card::

        .. rubric:: :console:`git commit` von B setzt den *aktuellen* Branch weiter

        .. raw:: html

            <div style="width: 90ch; height:16ch">
            <svg viewBox="0 0 48 8" font-size="0.75"  version="1.1" xmlns="http://www.w3.org/2000/svg">

                <rect width="4" height="1" x="0" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="1.5" y="3.75" style="fill:white">A</text>

                <rect width="4" height="1" x="8" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="9.5" y="3.75" style="fill:white">B</text>
                <line x1="4" y1="3.5" x2="8" y2="3.5" style="stroke:blue;stroke-width:0.2" marker-end="url(#arrow)"/>

                <rect width="6" height="1" x="6" y="0" rx="1" ry="1" style="fill:darkorange" />
                <text x="7" y="0.75" style="fill:white">main</text>
                <line x1="2" y1="3" x2="6" y2="0.5" style="stroke:darkorange;stroke-width:0.2"/>

                <rect width="8" height="1" x="14" y="6" rx="1" ry="1" style="fill:darkorange" />
                <text x="15" y="6.75" style="fill:white">develop</text>
                <line x1="10" y1="4" x2="14" y2="6.5" style="stroke:darkorange;stroke-width:0.2"/>
                <use href="#star" class="star" x="21" y="6" />
            </svg>
            </div>

    .. card::

        .. rubric:: :console:`git checkout -b cveXXX-hotfix`

        .. raw:: html

            <div style="width: 90ch; height:16ch">
            <svg viewBox="0 0 48 8" font-size="0.75"  version="1.1" xmlns="http://www.w3.org/2000/svg">

                <rect width="4" height="1" x="0" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="1.5" y="3.75" style="fill:white">A</text>

                <rect width="4" height="1" x="8" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="9.5" y="3.75" style="fill:white">B</text>
                <line x1="4" y1="3.5" x2="8" y2="3.5" style="stroke:blue;stroke-width:0.2" marker-end="url(#arrow)"/>

                <rect width="6" height="1" x="6" y="0" rx="1" ry="1" style="fill:darkorange" />
                <text x="7" y="0.75" style="fill:white">main</text>
                <line x1="2" y1="3" x2="6" y2="0.5" style="stroke:darkorange;stroke-width:0.2"/>

                <rect width="8" height="1" x="14" y="5" rx="1" ry="1" style="fill:darkorange" />
                <text x="15" y="5.75" style="fill:white">develop</text>
                <line x1="10" y1="4" x2="14" y2="5.5" style="stroke:darkorange;stroke-width:0.2"/>

                <rect width="8" height="1" x="14" y="7" rx="1" ry="1" style="fill:darkorange" />
                <text x="15" y="7.75" style="fill:white">cveXXX-hotfix</text>
                <line x1="10" y1="4" x2="14" y2="7.5" style="stroke:darkorange;stroke-width:0.2"/>
                <use href="#star" class="star" x="21" y="7" />
            </svg>
            </div>

        .. remark::

            ``git checkout -b cveXXX-hotfix``

            ist lediglich eine Abkürzung für:

            ``git branch cveXXX-hotfix``

            ``git checkout cveXXX-hotfix``

    .. card::

        .. rubric:: :console:`git commit` von C

        .. raw:: html

            <div style="width: 90ch; height:16ch">
            <svg viewBox="0 0 48 8" font-size="0.75"  version="1.1" xmlns="http://www.w3.org/2000/svg">

                <rect width="4" height="1" x="0" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="1.5" y="3.75" style="fill:white">A</text>

                <rect width="4" height="1" x="8" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="9.5" y="3.75" style="fill:white">B</text>
                <line x1="4" y1="3.5" x2="8" y2="3.5" style="stroke:blue;stroke-width:0.2" marker-end="url(#arrow)"/>

                <rect width="4" height="1" x="16" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="17.5" y="3.75" style="fill:white">C</text>
                <line x1="12" y1="3.5" x2="16" y2="3.5" style="stroke:blue;stroke-width:0.2" marker-end="url(#arrow)"/>

                <rect width="6" height="1" x="6" y="0" rx="1" ry="1" style="fill:darkorange" />
                <text x="7" y="0.75" style="fill:white">main</text>
                <line x1="2" y1="3" x2="6" y2="0.5" style="stroke:darkorange;stroke-width:0.2"/>

                <rect width="8" height="1" x="14" y="5" rx="1" ry="1" style="fill:darkorange" />
                <text x="15" y="5.75" style="fill:white">develop</text>
                <line x1="10" y1="4" x2="14" y2="5.5" style="stroke:darkorange;stroke-width:0.2"/>

                <rect width="8" height="1" x="22" y="0" rx="1" ry="1" style="fill:darkorange" />
                <text x="23" y="0.75" style="fill:white">cveXXX-hotfix</text>
                <line x1="18" y1="3" x2="22" y2="0.5" style="stroke:darkorange;stroke-width:0.2"/>
                <use href="#star" class="star" x="29" y="0" />
            </svg>
            </div>

    .. card::

        .. rubric:: :console:`git checkout develop`

        .. raw:: html

            <div style="width: 90ch; height:16ch">
            <svg viewBox="0 0 48 8" font-size="0.75"  version="1.1" xmlns="http://www.w3.org/2000/svg">

                <rect width="4" height="1" x="0" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="1.5" y="3.75" style="fill:white">A</text>

                <rect width="4" height="1" x="8" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="9.5" y="3.75" style="fill:white">B</text>
                <line x1="4" y1="3.5" x2="8" y2="3.5" style="stroke:blue;stroke-width:0.2" marker-end="url(#arrow)"/>

                <rect width="4" height="1" x="16" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="17.5" y="3.75" style="fill:white">C</text>
                <line x1="12" y1="3.5" x2="16" y2="3.5" style="stroke:blue;stroke-width:0.2" marker-end="url(#arrow)"/>

                <rect width="6" height="1" x="6" y="0" rx="1" ry="1" style="fill:darkorange" />
                <text x="7" y="0.75" style="fill:white">main</text>
                <line x1="2" y1="3" x2="6" y2="0.5" style="stroke:darkorange;stroke-width:0.2"/>

                <rect width="8" height="1" x="14" y="6" rx="1" ry="1" style="fill:darkorange" />
                <text x="15" y="6.75" style="fill:white">develop</text>
                <line x1="10" y1="4" x2="14" y2="6.5" style="stroke:darkorange;stroke-width:0.2"/>
                <use href="#star" class="star" x="21" y="6" />

                <rect width="8" height="1" x="22" y="0" rx="1" ry="1" style="fill:darkorange" />
                <text x="23" y="0.75" style="fill:white">cveXXX-hotfix</text>
                <line x1="18" y1="3" x2="22" y2="0.5" style="stroke:darkorange;stroke-width:0.2"/>
            </svg>
            </div>


    .. card::

        .. rubric:: Fast-forward Merge: :console:`git merge cveXXX-hotfix`

        .. raw:: html

            <div style="width: 90ch; height:16ch">
            <svg viewBox="0 0 48 8" font-size="0.75"  version="1.1" xmlns="http://www.w3.org/2000/svg">

                <rect width="4" height="1" x="0" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="1.5" y="3.75" style="fill:white">A</text>

                <rect width="4" height="1" x="8" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="9.5" y="3.75" style="fill:white">B</text>
                <line x1="4" y1="3.5" x2="8" y2="3.5" style="stroke:blue;stroke-width:0.2" marker-end="url(#arrow)"/>

                <rect width="4" height="1" x="16" y="3" rx="1" ry="1" style="fill:darkblue" />
                <text x="17.5" y="3.75" style="fill:white">C</text>
                <line x1="12" y1="3.5" x2="16" y2="3.5" style="stroke:blue;stroke-width:0.2" marker-end="url(#arrow)"/>

                <rect width="6" height="1" x="6" y="0" rx="1" ry="1" style="fill:darkorange" />
                <text x="7" y="0.75" style="fill:white">main</text>
                <line x1="2" y1="3" x2="6" y2="0.5" style="stroke:darkorange;stroke-width:0.2"/>

                <rect width="8" height="1" x="22" y="6" rx="1" ry="1" style="fill:darkorange" />
                <text x="23" y="6.75" style="fill:white">develop</text>
                <line x1="18" y1="4" x2="22" y2="6.5" style="stroke:darkorange;stroke-width:0.2"/>
                <use href="#star" class="star" x="29" y="6" />

                <rect width="8" height="1" x="22" y="0" rx="1" ry="1" style="fill:darkorange" />
                <text x="23" y="0.75" style="fill:white">cveXXX-hotfix</text>
                <line x1="18" y1="3" x2="22" y2="0.5" style="stroke:darkorange;stroke-width:0.2"/>
            </svg>
            </div>

    .. card::

        .. ggf. Visualisierungen einbauen (Folien von Ben - X - Git-basierte Versionskontrolle.key ab ca. Folie 32)

        .. rubric:: Sonderfälle

        - wenn es auf beiden Branches Änderungen gab, dann kann ein Merge ggf. fehlschlagen und muss manuell gemerged werden.
        - Um Änderungen auf ein remote Repository zu schieben bzw. davon zu holen muss man git push und git pull verwenden. Dabei kann es auch zu Konflikten kommen, die manuell gelöst werden müssen.


Git-Flow
---------

Git-Flow ist eine Konvention zur Nutzung von Branches in einer sinnvollen Art und Weise.\ [#]_

Mindestens fünf Arten von Branches:

.. class:: incremental-list

:main: enthält stets die zuletzt veröffentlichte Version
:develop: enthält aktuelle Entwicklungsversion
:feature/topic branches: zur Entwicklung individueller Features
:hotfix branches: zur Implementierung dringender Bugfixes
:release branches: zum Vorbereiten eines Releases

.. [#] Erstmals dokumentiert durch Vincent Driessen http://nvie.com/posts/a-successful-git-branching-model/



Dezentralisierte VCS - Verteiltes Arbeiten
--------------------------------------------

.. raw:: html
    :class: align-center

    <div style="width: 70ch; height: 14ch; container-type:size;">
    <svg viewBox="0 0 48 9" font-size="1" version="1.1" xmlns="http://www.w3.org/2000/svg">

        <g class="incremental" style="fill:white">
            <rect width="12" height="3" x="0" y="0" rx="1" ry="1" style="fill:rgb(55,155,55)" />
            <text x="1" y="1.5">Main</text>
            <text x="1" y="2.5">Repository</text>
        </g>
        <g class="incremental" >
            <rect width="12" height="3" x="18" y="6" rx="1" ry="1" style="fill:rgb(194, 191, 246)" />
            <text x="19" y="7.5">Developer A</text>
            <text x="19" y="8.5">Private</text>
            <line x1="9" y1="3" x2="21" y2="6" style="stroke:rgb(0,0,0);stroke-width:0.2" marker-end="url(#arrow)"/>
        </g>
        <g class="incremental" >
            <rect width="12" height="3" x="36" y="6" rx="1" ry="1" style="fill:rgb(194, 191, 246)" />
            <text x="37" y="7.5">Developer B</text>
            <text x="37" y="8.5">Private</text>
            <line x1="9" y1="3" x2="39" y2="6" style="stroke:rgb(0,0,0);stroke-width:0.2" marker-end="url(#arrow)"/>
        </g>
        <g class="incremental" style="fill:white">
            <rect width="12" height="3" x="18" y="0" rx="1" ry="1" style="fill:rgb(112, 105, 238)" />
            <text x="19" y="1.5">Developer A</text>
            <text x="19" y="2.5">Public</text>
            <line x1="24" y1="6" x2="24" y2="3" style="stroke:rgb(0,0,0);stroke-width:0.2" marker-end="url(#arrow)"/>
        </g>
        <g class="incremental" style="fill:white">
            <rect width="12" height="3" x="36" y="0" rx="1" ry="1" style="fill:rgb(112, 105, 238)" />
            <text x="37" y="1.5">Developer B</text>
            <text x="37" y="2.5">Public</text>
            <line x1="42" y1="6" x2="42" y2="3" style="stroke:rgb(0,0,0);stroke-width:0.2" marker-end="url(#arrow)"/>
        </g>
        <g class="incremental" style="fill:white">
            <rect width="12" height="3" x="0" y="6" rx="1" ry="1" style="fill:rgb(247, 173, 0)" />
            <text x="1" y="7.5">Integration</text>
            <text x="1" y="8.5">Repository</text>
            <line x1="6" y1="6" x2="6" y2="3" style="stroke:rgb(0,0,0);stroke-width:0.2" marker-end="url(#arrow)"/>
            <line x1="21" y1="3" x2="9" y2="6" style="stroke:rgb(0,0,0);stroke-width:0.2" marker-end="url(#arrow)"/>
            <line x1="39" y1="3" x2="9" y2="6" style="stroke:rgb(0,0,0);stroke-width:0.2" marker-end="url(#arrow)"/>
        </g>
    </svg>
    </div>

.. deck::

    .. card::

        (Verteilte) Workflows beschreiben, wie Personen Änderungen zwischen verteilten Repositories synchronisieren.

        .. class:: incremental-list

        • Hängt von Projekt und Organisationsstruktur ab
        • Workflows unterscheiden öffentliche und private Repositories
        • In den meisten Workflows gibt es ein ausgezeichnetes Repository als *ground truth*

    .. card::

        .. attention::

            Die Commit-Historie des *blessed repository* niemals verändern!



GIT Befehle
------------

.. class:: incremental-list

• Erstellen eines neuen lokalen Repositories: :console:`git init`
• Lokalen Klon von entferntem Repositories anlegen: :console:`git clone <Repository-URL>`
• Geänderte Dateien anzeigen: :console:`git status`
• Zeilenweise Änderungen anzeigen: :console:`git diff (<Datei-Pfad>)`
• Änderungshistorie ansehen: :console:`git log`
• Commit ansehen: :console:`git show <Commit-Hash>`
• Dateien dem nächsten Commit hinzufügen: :console:`git add (--all|<Datei-Pfad>)`
• Commit anlegen: :console:`git commit (-m " <Beschreibung>")`
• Neuen Branch anlegen: :console:`git checkout -b <Branch-Name>`
• Aktuellen Branch wechseln: :console:`git checkout <Branch-Name>`
• Commits eines anderen in den aktuellen ziehen: :console:`git merge <Branch-Name>`
• Commits vom entfernten zum lokalen Repository holen: :console:`git fetch`
• Commits vom lokalen zum entfernten Repository schieben: :console:`git push`
• Kombination von :console:`git fetch` und :console:`git merge`: :console:`git pull`


`.gitignore <https://git-scm.com/docs/gitignore>`__
------------------------------------------------------------

Die Datei .gitignore listet alle Arten von Dateien und Verzeichnissen auf, die von Git ignoriert werden sollen. Dies sind typischerweise alle Artefakte, die automatische generiert werden als Teil des Entwicklungsprozesses.


.. grid::

    .. cell::

        .. class:: incremental-list

        - Kommentare beginnen mit #
        - Leerzeilen sind erlaubt
        - jede nicht-leere Zeile, die kein Kommentar ist, beschreibt ein Muster
        - Wildcards (*) sind erlaubt
        - ! am Anfang negiert ein Muster
        - "/" separiert Verzeichnisse

    .. cell:: incremental

        .. example::

            .. code:: unixconfig
                :number-lines:

                *.bak
                *.class
                *.jar
                target/

                # "Editors"
                .vscode/
                .zed/
                .idea/






.. class:: exercises

Übung
------

.. story::

    .. exercise:: Eine erste Übung mit GIT

        Installieren Sie Git auf Ihrem System, falls es nicht verfügbar sein sollte.

        .. class:: incremental-list list-with-explanations

        1. Erstellen Sie ein neues Verzeichnis und legen Sie darin ein neues lokales Repository mit Hilfe von :console:`git init` an.
        2. Entpacken Sie die Datei |RPN.zip| in dem Verzeichnis.
        3. Führen Sie einen initialen Commit durch mit Hilfe von :console:`git add` und :console:`git commit`.
        4. Compilieren Sie die Sourcen mit :console:`javac`.
        5. Legen Sie eine ``.gitignore`` Datei an, um sicherzustellen, dass die Binärdateien nicht in das Repository gelangen.
        6. Nutzen Sie :console:`git status`, um sich zu vergewissern, dass die Binärdateien ignoriert werden.
        7. fügen Sie die ``.gitignore`` Datei Ihrem Repository hinzu.

        .. solution::
            :pwd: GITgitGit

            ::

                1.  git init
                3.  git add ds/*.java
                    git add rpn/*.java
                    git commit -m "Initial commit"
                4. javac ds/*.java rpn/*.java
                5. echo "*.class" > .gitignore
                6. git status
                7. git add .gitignore
                   git commit -m "Add .gitignore"


.. class:: exercises

Übung
------

.. story::

    .. exercise:: Branching mit GIT

        1. Erstellen Sie einen neuen Branch mit dem Namen ``feature/bugfix`` und wechseln Sie auf den neuen Branch.

           (Nutzen Sie ``git status``, um zu verifizieren, dass Sie auf dem neuen Branch sind)
        2. Ändern Sie die Datei ``RPN.java``, um den Bug im Switch statement (:java:`case "* "` => :java:`case "*"`) zu beheben.
        3. Committen Sie die Änderungen.
        4. Wechseln Sie zurück auf den ``main`` Branch.
        5. Mergen Sie den Branch ``feature/bugfix`` in den ``main`` Branch mit Hilfe von :console:`git merge`.
        6. Löschen Sie den Branch ``feature/bugfix`` mit Hilfe von :console:`git branch -d`.

        .. solution::
            :pwd: GITgitGit

            ::

                1. git checkout -b feature/bugfix
                2. git add rpn/RPN.java
                3. git commit -m "Fix bug in RPN.java"
                4. git checkout main
                5. git merge feature/bugfix
                6. git branch -d feature/bugfix



.. class:: exercises

Übung
------

.. story::

    .. exercise:: Merging mit GIT

        1. Erstellen Sie einen neuen Branch develop und wechseln Sie auf diesen Branch.
        2. Ändern Sie die Reihenfolge der Methoden :java:`pop` und :java:`peek` in der Klasse :java:`Stack`.
        3. Committen Sie die Änderungen.

        4. Wechseln Sie zurück auf den ``main`` Branch.

           *(Führen Sie noch keinen Merge durch!)*
        5. Entfernen Sie die ``{}`` Klammern um die :java:`throw new NoSuchElementException()` Anweisungen.
        6. Committen Sie die Änderungen.
        7. Führen Sie einen Merge von ``develop`` in ``main`` durch.
        8. Öffnen Sie die Datei ``ds/Stack.java`` und beheben Sie den Merge-Konflikt.
        9. Committen Sie die Änderungen.

        .. solution::
            :pwd: GITgitGit

            ::

                1. git checkout -b develop
                3. git add ds/Stack.java
                   git commit -m "Change order of pop and peek"
                4. git checkout main
                6. git add ds/Stack.java
                   git commit -m "Remove {}"
                7. git merge develop
                9. git add ds/Stack.java
                   git commit -m "Resolve merge conflict in Stack.java"



.. class:: exercises

Übung
------

.. story::

    .. exercise:: Weiterentwicklung vorbereiten

        1. Nutzen Sie :console:`git log --oneline --graph --all` um sich die Commit-Historie anzusehen.
        2. Wechseln Sie zurück zum ``develop`` Branch.
        3. Führen Sie einen Merge von ``main`` in ``develop`` durch.

        .. solution::
            :pwd: GITgitGit

            ::

                1. git log --oneline --graph --all
                2. git checkout develop
                3. git merge main
