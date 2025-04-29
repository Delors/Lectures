.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "Git", "Versionskontrolle"
    :description lang=de: Software Engineering - Versionskontrolle und Git
    :id: lecture-se-versionskontrolle
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs

.. |RPN.zip| source:: exercise/RPN.zip
    :prefix: https://delors.github.io/
    :path: relative



Software Engineering - Versionskontrolle (mit Git)
===================================================================

Eine allererste Einführung 

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.0

.. container:: footer-left tiny
    
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


Versionsmanagement
-------------------

.. deck::

    .. card::

        .. observation::

            .. deck:: numbered

                .. card::

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

        .. compound::

            .. rubric:: Versionsmanagement 

            .. class:: list-with-explanations

            - Wo ist die aktuelle Version?
            - Was ist die zuletzt lauffähige Version?
            - Wo ist die Implementierungsversion vom 01. April 2016? 

                Und welche Dokumente beziehen sich auf diese Version?
            - Welche Version wurde dem Kunden präsentiert?

        .. compound::
            :class: incremental

            .. rubric:: Änderungsmanagement

            - Was hat sich seit letzter Woche geändert? 
            - Wer hat diese Änderung gemacht?
            - Warum wurde diese Änderung gemacht?


    .. card::

        Einfache Lösungen, die oft verwendet werden:

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
        
        :Zentralisierte VCS: 
            synchronisieren alle Änderungen in einem zentralen Repository (Subversion, . . . )

            .. class:: negative-list
            
            - Keine Offline-Nutzung möglich.
             

        :Dezentralisierte VCS: 
        
            können mehrere, unabhängige Repositories haben (Git, Mercurial, . . . )
        
            .. remark::
                :class: incremental
        
                Dezentralisierte VCS (insbesondere Git) sind heute am weitesten verbreitet.

    .. card:: dd-margin-left-6em

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

        - Mergen kann in seltenen Fällen komplex werden und zu Fehlern führen  



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

    <svg width="57ch" height="13lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
        <style>
        </style>
        <defs>
            <marker 
            id="arrow"
            viewBox="0 0 10 10"
            refX="10"
            refY="5"
            markerUnits="strokeWidth"
            markerWidth="8"
            markerHeight="8"
            orient="auto-start-reverse">
            <path d="M 0 0 L 10 5 L 0 10 z" />
            </marker>
        </defs>

        <g style="fill:white">
            <rect width="10ch" height="3lh" x="0ch" y="0" rx="1ch" ry="1ch" style="fill:rgb(71, 65, 254)" />
            <text x="1ch" y="1.5lh">Working</text>
            <text x="1ch" y="2.5lh">Directory</text>
            <line x1="5ch" y1="3lh" x2="5ch" y2="13lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch;" stroke-dasharray="1ch 1ch"/>
        </g>
        <g class="incremental" style="fill:white">
            <rect width="10ch" height="3lh" x="15ch" y="0" rx="1ch" ry="1ch" style="fill:rgb(71, 65, 254)" />
            <text x="16ch" y="2lh">Index</text>
            <line x1="20ch" y1="3lh" x2="20ch" y2="13lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch;" stroke-dasharray="1ch 1ch"/>
            <line x1="5ch" y1="4.5lh" x2="20ch" y2="4.5lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch" marker-end="url(#arrow)"/>
            <text x="6ch" y="4.25lh" style="fill:black">add</text>
        </g>
        <g class="incremental" style="fill:white">
            <rect width="10ch" height="3lh" x="30ch" y="0" rx="1ch" ry="1ch" style="fill:rgb(71, 65, 254)" />
            <text x="31ch" y="1.5lh">Local</text>
            <text x="31ch" y="2.5lh">Repo</text>
            <line x1="35ch" y1="3lh" x2="35ch" y2="13lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch;" stroke-dasharray="1ch 1ch"/>
            <line x1="20ch" y1="6lh" x2="35ch" y2="6lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch" marker-end="url(#arrow)"/>
            <text x="21ch" y="5.75lh" style="fill:black">commit</text>
        </g>
        <g class="incremental" style="fill:white">
            <rect width="10ch" height="3lh" x="45ch" y="0" rx="1ch" ry="1ch" style="fill:rgb(55,155,55)" />
            <text x="46ch" y="1.5lh">Remote</text>
            <text x="46ch" y="2.5lh">Repo</text>
            <line x1="50ch" y1="3lh" x2="50ch" y2="13lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch;" stroke-dasharray="1ch 1ch"/>
            <line x1="35ch" y1="7.5lh" x2="50ch" y2="7.5lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch" marker-end="url(#arrow)"/>
            <text x="36ch" y="7.25lh" style="fill:black">push</text>
        </g>
        <g class="incremental" style="fill:black">
            <line x2="35ch" y1="10.5lh" x1="50ch" y2="10.5lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch" marker-end="url(#arrow)"/>
            <text x="36ch" y="10.25lh" style="fill:black">fetch</text>
            <!-- To get a stable animation: --><circle cx="57ch" cy="13lh" r="0.1ch" fill="white"/>
        </g>
        <g class="incremental" style="fill:black">
            <line x1="35ch" y1="10.5lh" x2="5ch" y2="10.5lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch" marker-end="url(#arrow)"/>
            <text x="15ch" y="10.25lh" style="fill:black">merge/checkout</text>
            <!-- To get a stable animation: --><circle cx="57ch" cy="13lh" r="0.1ch" fill="white"/>
        </g>
        <g class="incremental" style="fill:black">
            <line x1="50ch" y1="12lh" x2="5ch" y2="12lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch" marker-end="url(#arrow)"/>
            <text x="25ch" y="11.75lh" style="fill:black">pull</text>
            <!-- To get a stable animation: --><circle cx="57ch" cy="13lh" r="0.1ch" fill="white"/>
        </g>
    </svg>




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

            <svg width="48ch" height="8lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <rect width="4ch" height="1lh" x="0ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="1.5ch" y="3.75lh" style="fill:white">A</text>

                <rect width="6ch" height="1lh" x="6ch" y="0lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="7ch" y="0.75lh" style="fill:white">main</text>       

                <line x1="2ch" y1="3lh" x2="6ch" y2="0.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     
            </svg>
    
    .. card:: 
        
        .. rubric:: *main* ist der aktuell ausgecheckte Branch

        .. raw:: html

            <svg width="48ch" height="8lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <style>
                    polygon.star {
                        transform: scale(calc(var(--unitless-current-base-font-size) / 20));
                    }
                </style>
                <defs>
                    <g id="star">
                        <polygon class="star" points="12,2 15,9 22,9 16,14 18,21 12,17 6,21 8,14 2,9 9,9" fill="gold" stroke="black" stroke-width="1"/>
                    </g>
                </defs>

                    <rect width="4ch" height="1lh" x="0ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                    <text x="1.5ch" y="3.75lh" style="fill:white">A</text>

                    <rect width="6ch" height="1lh" x="6ch" y="0lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                    <text x="7ch" y="0.75lh" style="fill:white">main</text>       

                    <line x1="2ch" y1="3lh" x2="6ch" y2="0.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     

                    <use href="#star" x="11ch" y="0"/>
            </svg>

    .. card:: 
        
        .. rubric:: :console:`git branch develop`

        .. raw:: html

            <svg width="48ch" height="8lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <style>
                    polygon.star {
                        transform: scale(calc(var(--unitless-current-base-font-size) / 20));
                    }
                </style>
                <defs>
                    <g id="star">
                        <polygon class="star" points="12,2 15,9 22,9 16,14 18,21 12,17 6,21 8,14 2,9 9,9" fill="gold" stroke="black" stroke-width="1"/>
                    </g>
                </defs>

                <rect width="4ch" height="1lh" x="0ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="1.5ch" y="3.75lh" style="fill:white">A</text>

                <rect width="6ch" height="1lh" x="6ch" y="0lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="7ch" y="0.75lh" style="fill:white">main</text>       
                <line x1="2ch" y1="3lh" x2="6ch" y2="0.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     
                <use href="#star" x="11ch" y="0"/>

                <rect width="8ch" height="1lh" x="6ch" y="6lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="7ch" y="6.75lh" style="fill:white">develop</text>       
                <line x1="2ch" y1="4lh" x2="6ch" y2="6.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     
            </svg>

    .. card:: 
        
        .. rubric:: :console:`git checkout develop`

        .. raw:: html

            <svg width="48ch" height="8lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <style>
                    polygon.star {
                        transform: scale(calc(var(--unitless-current-base-font-size) / 20));
                    }
                </style>
                <defs>
                    <g id="star">
                        <polygon class="star" points="12,2 15,9 22,9 16,14 18,21 12,17 6,21 8,14 2,9 9,9" fill="gold" stroke="black" stroke-width="1"/>
                    </g>
                </defs>

                <rect width="4ch" height="1lh" x="0ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="1.5ch" y="3.75lh" style="fill:white">A</text>

                <rect width="6ch" height="1lh" x="6ch" y="0lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="7ch" y="0.75lh" style="fill:white">main</text>       
                <line x1="2ch" y1="3lh" x2="6ch" y2="0.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     

                <rect width="8ch" height="1lh" x="6ch" y="6lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="7ch" y="6.75lh" style="fill:white">develop</text>       
                <line x1="2ch" y1="4lh" x2="6ch" y2="6.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     
                <use href="#star" class="star" x="13ch" y="6lh" />
            </svg>


    .. card:: 
        
        .. rubric:: :console:`git commit` von B

        .. raw:: html

            <svg width="48ch" height="8lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <style>
                    polygon.star {
                        transform: scale(calc(var(--unitless-current-base-font-size) / 20));
                    }
                </style>
                <defs>
                    <g id="star">
                        <polygon class="star" points="12,2 15,9 22,9 16,14 18,21 12,17 6,21 8,14 2,9 9,9" fill="gold" stroke="black" stroke-width="1"/>
                    </g>
                </defs>

                <rect width="4ch" height="1lh" x="0ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="1.5ch" y="3.75lh" style="fill:white">A</text>

                <rect width="4ch" height="1lh" x="8ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="9.5ch" y="3.75lh" style="fill:white">B</text>
                <line x1="4ch" y1="3.5lh" x2="8ch" y2="3.5lh" style="stroke:blue;stroke-width:0.2ch"/>     

                <rect width="6ch" height="1lh" x="6ch" y="0lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="7ch" y="0.75lh" style="fill:white">main</text>       
                <line x1="2ch" y1="3lh" x2="6ch" y2="0.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     

                <rect width="8ch" height="1lh" x="6ch" y="6lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="7ch" y="6.75lh" style="fill:white">develop</text>       
                <line x1="2ch" y1="4lh" x2="6ch" y2="6.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     
                <use href="#star" class="star" x="13ch" y="6lh" />
            </svg>

    .. card:: 
        
        .. rubric:: :console:`git commit` von B setzt den *aktuellen* Branch weiter

        .. raw:: html

            <svg width="48ch" height="8lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <style>
                    polygon.star {
                        transform: scale(calc(var(--unitless-current-base-font-size) / 20));
                    } 
                </style>
                <defs>
                    <marker 
                    id="arrow"
                    viewBox="0 0 10 10"
                    refX="10"
                    refY="5"
                    markerUnits="strokeWidth"
                    markerWidth="6"
                    markerHeight="6"
                    orient="auto-start-reverse">
                    <path class="arrow-head" d="M 0 0 L 10 5 L 0 10 z" />
                    </marker>
                    <g id="star">
                        <polygon class="star" points="12,2 15,9 22,9 16,14 18,21 12,17 6,21 8,14 2,9 9,9" fill="gold" stroke="black" stroke-width="1"/>
                    </g>
                </defs>

                <rect width="4ch" height="1lh" x="0ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="1.5ch" y="3.75lh" style="fill:white">A</text>

                <rect width="4ch" height="1lh" x="8ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="9.5ch" y="3.75lh" style="fill:white">B</text>
                <line x1="4ch" y1="3.5lh" x2="8ch" y2="3.5lh" style="stroke:blue;stroke-width:0.2ch" marker-end="url(#arrow)"/>     

                <rect width="6ch" height="1lh" x="6ch" y="0lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="7ch" y="0.75lh" style="fill:white">main</text>       
                <line x1="2ch" y1="3lh" x2="6ch" y2="0.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     

                <rect width="8ch" height="1lh" x="14ch" y="6lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="15ch" y="6.75lh" style="fill:white">develop</text>       
                <line x1="10ch" y1="4lh" x2="14ch" y2="6.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     
                <use href="#star" class="star" x="21ch" y="6lh" />
            </svg>

    .. card:: 
        
        .. rubric:: :console:`git checkout -b cveXXX-hotfix` 

        .. note::

            ``git checkout -b cveXXX-hotfix``
        
            ist lediglich eine Abkürzung für:

            ``git branch cveXXX-hotfix``

            ``git checkout cveXXX-hotfix``

        .. raw:: html

            <svg width="32ch" height="8lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <style>
                    polygon.star {
                        transform: scale(calc(var(--unitless-current-base-font-size) / 20));
                    }
                </style>
                <defs>
                    <marker 
                    id="arrow"
                    viewBox="0 0 10 10"
                    refX="10"
                    refY="5"
                    markerUnits="strokeWidth"
                    markerWidth="6"
                    markerHeight="6"
                    orient="auto-start-reverse">
                    <path class="arrow-head" d="M 0 0 L 10 5 L 0 10 z" />
                    </marker>
                    <g id="star">
                        <polygon class="star" points="12,2 15,9 22,9 16,14 18,21 12,17 6,21 8,14 2,9 9,9" fill="gold" stroke="black" stroke-width="1"/>
                    </g>
                </defs>

                <rect width="4ch" height="1lh" x="0ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="1.5ch" y="3.75lh" style="fill:white">A</text>

                <rect width="4ch" height="1lh" x="8ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="9.5ch" y="3.75lh" style="fill:white">B</text>
                <line x1="4ch" y1="3.5lh" x2="8ch" y2="3.5lh" style="stroke:blue;stroke-width:0.2ch" marker-end="url(#arrow)"/>     

                <rect width="6ch" height="1lh" x="6ch" y="0lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="7ch" y="0.75lh" style="fill:white">main</text>       
                <line x1="2ch" y1="3lh" x2="6ch" y2="0.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     

                <rect width="8ch" height="1lh" x="14ch" y="5lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="15ch" y="5.75lh" style="fill:white">develop</text>       
                <line x1="10ch" y1="4lh" x2="14ch" y2="5.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     

                <rect width="12.5ch" height="1lh" x="14ch" y="7lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="15ch" y="7.75lh" style="fill:white">cveXXX-hotfix</text>       
                <line x1="10ch" y1="4lh" x2="14ch" y2="7.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     
                <use href="#star" class="star" x="25.5ch" y="7lh" />
            </svg>    

    .. card:: 
        
        .. rubric:: :console:`git commit` von C

        .. raw:: html

            <svg width="40ch" height="8lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <style>
                    polygon.star {
                        transform: scale(calc(var(--unitless-current-base-font-size) / 20));
                    }
                </style>
                <defs>
                    <marker 
                    id="arrow"
                    viewBox="0 0 10 10"
                    refX="10"
                    refY="5"
                    markerUnits="strokeWidth"
                    markerWidth="6"
                    markerHeight="6"
                    orient="auto-start-reverse">
                    <path class="arrow-head" d="M 0 0 L 10 5 L 0 10 z" />
                    </marker>
                    <g id="star">
                        <polygon class="star" points="12,2 15,9 22,9 16,14 18,21 12,17 6,21 8,14 2,9 9,9" fill="gold" stroke="black" stroke-width="1"/>
                    </g>
                </defs>

                <rect width="4ch" height="1lh" x="0ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="1.5ch" y="3.75lh" style="fill:white">A</text>

                <rect width="4ch" height="1lh" x="8ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="9.5ch" y="3.75lh" style="fill:white">B</text>
                <line x1="4ch" y1="3.5lh" x2="8ch" y2="3.5lh" style="stroke:blue;stroke-width:0.2ch" marker-end="url(#arrow)"/>     

                <rect width="4ch" height="1lh" x="16ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="17.5ch" y="3.75lh" style="fill:white">C</text>
                <line x1="12ch" y1="3.5lh" x2="16ch" y2="3.5lh" style="stroke:blue;stroke-width:0.2ch" marker-end="url(#arrow)"/>     

                <rect width="6ch" height="1lh" x="6ch" y="0lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="7ch" y="0.75lh" style="fill:white">main</text>       
                <line x1="2ch" y1="3lh" x2="6ch" y2="0.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     

                <rect width="8ch" height="1lh" x="14ch" y="5lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="15ch" y="5.75lh" style="fill:white">develop</text>       
                <line x1="10ch" y1="4lh" x2="14ch" y2="5.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     

                <rect width="12.5ch" height="1lh" x="22ch" y="0lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="23ch" y="0.75lh" style="fill:white">cveXXX-hotfix</text>       
                <line x1="18ch" y1="3lh" x2="22ch" y2="0.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     
                <use href="#star" class="star" x="33.5ch" y="0lh" />
            </svg>
        
    .. card:: 
        
        .. rubric:: :console:`git checkout develop`

        .. raw:: html

            <svg width="40ch" height="8lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <style>
                    polygon.star {
                        transform: scale(calc(var(--unitless-current-base-font-size) / 20));
                    }
                </style>
                <defs>
                    <marker 
                    id="arrow"
                    viewBox="0 0 10 10"
                    refX="10"
                    refY="5"
                    markerUnits="strokeWidth"
                    markerWidth="6"
                    markerHeight="6"
                    orient="auto-start-reverse">
                    <path class="arrow-head" d="M 0 0 L 10 5 L 0 10 z" />
                    </marker>
                    <g id="star">
                        <polygon class="star" points="12,2 15,9 22,9 16,14 18,21 12,17 6,21 8,14 2,9 9,9" fill="gold" stroke="black" stroke-width="1"/>
                    </g>
                </defs>

                <rect width="4ch" height="1lh" x="0ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="1.5ch" y="3.75lh" style="fill:white">A</text>

                <rect width="4ch" height="1lh" x="8ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="9.5ch" y="3.75lh" style="fill:white">B</text>
                <line x1="4ch" y1="3.5lh" x2="8ch" y2="3.5lh" style="stroke:blue;stroke-width:0.2ch" marker-end="url(#arrow)"/>     

                <rect width="4ch" height="1lh" x="16ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="17.5ch" y="3.75lh" style="fill:white">C</text>
                <line x1="12ch" y1="3.5lh" x2="16ch" y2="3.5lh" style="stroke:blue;stroke-width:0.2ch" marker-end="url(#arrow)"/>     

                <rect width="6ch" height="1lh" x="6ch" y="0lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="7ch" y="0.75lh" style="fill:white">main</text>       
                <line x1="2ch" y1="3lh" x2="6ch" y2="0.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     

                <rect width="8ch" height="1lh" x="14ch" y="6lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="15ch" y="6.75lh" style="fill:white">develop</text>       
                <line x1="10ch" y1="4lh" x2="14ch" y2="6.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     
                <use href="#star" class="star" x="21ch" y="6lh" />

                <rect width="12.5ch" height="1lh" x="22ch" y="0lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="23ch" y="0.75lh" style="fill:white">cveXXX-hotfix</text>       
                <line x1="18ch" y1="3lh" x2="22ch" y2="0.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     
            </svg>


    .. card:: 
        
        .. rubric:: Fast-forward Merge :console:`git merge cveXXX-hotfix`

        .. raw:: html

            <svg width="40ch" height="8lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <style>
                    polygon.star {
                        transform: scale(calc(var(--unitless-current-base-font-size) / 20));
                    }  
                </style>
                <defs>
                    <marker 
                    id="arrow"
                    viewBox="0 0 10 10"
                    refX="10"
                    refY="5"
                    markerUnits="strokeWidth"
                    markerWidth="6"
                    markerHeight="6"
                    orient="auto-start-reverse">
                    <path class="arrow-head" d="M 0 0 L 10 5 L 0 10 z" />
                    </marker>
                    <g id="star">
                        <polygon class="star" points="12,2 15,9 22,9 16,14 18,21 12,17 6,21 8,14 2,9 9,9" fill="gold" stroke="black" stroke-width="1"/>
                    </g>
                </defs>

                <rect width="4ch" height="1lh" x="0ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="1.5ch" y="3.75lh" style="fill:white">A</text>

                <rect width="4ch" height="1lh" x="8ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="9.5ch" y="3.75lh" style="fill:white">B</text>
                <line x1="4ch" y1="3.5lh" x2="8ch" y2="3.5lh" style="stroke:blue;stroke-width:0.2ch" marker-end="url(#arrow)"/>     

                <rect width="4ch" height="1lh" x="16ch" y="3lh" rx="1ch" ry="1ch" style="fill:darkblue" />
                <text x="17.5ch" y="3.75lh" style="fill:white">C</text>
                <line x1="12ch" y1="3.5lh" x2="16ch" y2="3.5lh" style="stroke:blue;stroke-width:0.2ch" marker-end="url(#arrow)"/>     

                <rect width="6ch" height="1lh" x="6ch" y="0lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="7ch" y="0.75lh" style="fill:white">main</text>       
                <line x1="2ch" y1="3lh" x2="6ch" y2="0.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     

                <rect width="8ch" height="1lh" x="22ch" y="6lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="23ch" y="6.75lh" style="fill:white">develop</text>       
                <line x1="18ch" y1="4lh" x2="22ch" y2="6.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     
                <use href="#star" class="star" x="29ch" y="6lh" />

                <rect width="12.5ch" height="1lh" x="22ch" y="0lh" rx="1ch" ry="1ch" style="fill:darkorange" />
                <text x="23ch" y="0.75lh" style="fill:white">cveXXX-hotfix</text>       
                <line x1="18ch" y1="3lh" x2="22ch" y2="0.5lh" style="stroke:darkorange;stroke-width:0.2ch"/>     
            </svg>

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

    <svg width="48ch" height="9lh" version="1.1" xmlns="http://www.w3.org/2000/svg">
        <style>
        </style>
        <defs>
            <marker 
            id="arrow"
            viewBox="0 0 10 10"
            refX="10"
            refY="5"
            marker
            markerUnits="strokeWidth"
            markerWidth="8"
            markerHeight="8"
            orient="auto-start-reverse">
            <path d="M 0 0 L 10 5 L 0 10 z" />
            </marker>
        </defs>

        <g class="incremental" style="fill:white">
            <rect width="12ch" height="3lh" x="0ch" y="0" rx="1ch" ry="1ch" style="fill:rgb(55,155,55)" />
            <text x="1ch" y="1.5lh">Main</text>
            <text x="1ch" y="2.5lh">Repository</text>
        </g>
        <g class="incremental" >
            <rect width="12ch" height="3lh" x="18ch" y="6lh" rx="1ch" ry="1ch" style="fill:rgb(194, 191, 246)" />
            <text x="19ch" y="7.5lh">Developer A</text>
            <text x="19ch" y="8.5lh">Private</text>
            <line x1="9ch" y1="3lh" x2="21ch" y2="6lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch" marker-end="url(#arrow)"/>
        </g>
        <g class="incremental" >
            <rect width="12ch" height="3lh" x="36ch" y="6lh" rx="1ch" ry="1ch" style="fill:rgb(194, 191, 246)" />
            <text x="37ch" y="7.5lh">Developer B</text>
            <text x="37ch" y="8.5lh">Private</text>
            <line x1="9ch" y1="3lh" x2="39ch" y2="6lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch" marker-end="url(#arrow)"/>
        </g>
        <g class="incremental" style="fill:white">
            <rect width="12ch" height="3lh" x="18ch" y="0" rx="1ch" ry="1ch" style="fill:rgb(112, 105, 238)" />
            <text x="19ch" y="1.5lh">Developer A</text>
            <text x="19ch" y="2.5lh">Public</text>
            <line x1="24ch" y1="6lh" x2="24ch" y2="3lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch" marker-end="url(#arrow)"/>
        </g>
        <g class="incremental" style="fill:white">
            <rect width="12ch" height="3lh" x="36ch" y="0" rx="1ch" ry="1ch" style="fill:rgb(112, 105, 238)" />
            <text x="37ch" y="1.5lh">Developer B</text>
            <text x="37ch" y="2.5lh">Public</text>
            <line x1="42ch" y1="6lh" x2="42ch" y2="3lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch" marker-end="url(#arrow)"/>
        </g>
        <g class="incremental" style="fill:white">
            <rect width="12ch" height="3lh" x="0ch" y="6lh" rx="1ch" ry="1ch" style="fill:rgb(247, 173, 0)" />
            <text x="1ch" y="7.5lh">Integration</text>
            <text x="1ch" y="8.5lh">Repository</text>
            <line x1="6ch" y1="6lh" x2="6ch" y2="3lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch" marker-end="url(#arrow)"/>
            <line x1="21ch" y1="3lh" x2="9ch" y2="6lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch" marker-end="url(#arrow)"/>
            <line x1="39ch" y1="3lh" x2="9ch" y2="6lh" style="stroke:rgb(0,0,0);stroke-width:0.2ch" marker-end="url(#arrow)"/>
        </g>
    </svg>

.. deck:: margin-top-1em


    .. card::
        

        (Verteilte) Workflows beschreiben, wie Personen Änderungen zwischen verteilten Repositories synchronisieren.

        .. class:: incremental-list

        • Hängt von Projekt und Organisationsstruktur ab
        • Workflows unterscheiden öffentliche und private Repositories
        • In den meisten Workflows gibt es ein ausgezeichnetes Repository als *ground truth*

    .. card::

        .. attention::

            Die Commit-Historie des blessed repository niemals verändern!



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

        8. Erstellen Sie einen neuen Branch mit dem Namen ``feature/bugfix`` und wechseln Sie auf den neuen Branch. 
        
           (Nutzen Sie git status, um zu verifizieren, dass Sie auf dem neuen Branch sind)
        9. Ändern Sie die Datei ``RPN.java``, um den Bug im Switch statement (:java:`case "* "` => :java:`case "*"`) zu beheben.
        10. Committen Sie die Änderungen.
        11. Wechseln Sie zurück auf den ``main`` Branch.
        12. Mergen Sie den Branch ``feature/bugfix`` in den ``main`` Branch mit Hilfe von :console:`git merge`.
        13. Löschen Sie den Branch ``feature/bugfix`` mit Hilfe von :console:`git branch -d`.
        14. Erstellen Sie einen neuen Branch develop und wechseln Sie auf diesen Branch.
        15. Ändern Sie die Reihenfolge der Methoden :java:`pop` und :java:`peek` in der Klasse :java:`Stack`.
        16. Committen Sie die Änderungen.
        17. Wechseln Sie zurück auf den ``main`` Branch.
            
            *(Führen Sie noch keinen Merge durch!)*
        18. Entfernen Sie die ``{}`` Klammern um die :java:`throw new NoSuchElementException()` Anweisungen.
        19. Committen Sie die Änderungen.  
        20. Führen Sie einen Merge von ``develop`` in ``main`` durch.
        21. Öffnen Sie die Datei ``ds/Stack.java`` und beheben Sie den Merge-Konflikt.
        22. Committen Sie die Änderungen.
        23. Nutzen Sie :console:`git log --oneline --graph --all` um sich die Commit-Historie anzusehen.
        24. Wechseln Sie zurück zum ``develop`` Branch.
        25. Führen Sie einen Merge von ``main`` in ``develop`` durch.

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
                8. git checkout -b feature/bugfix
                10. git add rpn/RPN.java
                    git commit -m "Fix bug in RPN.java"
                11. git checkout main
                12. git merge feature/bugfix#
                13. git branch -d feature/bugfix
                14. git checkout -b develop
                16. git add ds/Stack.java
                    git commit -m "Change order of pop and peek"
                17. git checkout main
                19. git add ds/Stack.java
                    git commit -m "Remove {}"
                20. git merge develop
                22. git add ds/Stack.java
                    git commit -m "Resolve merge conflict in Stack.java"
                23. git log --oneline --graph --all
                24. git checkout develop
                25. git merge main

