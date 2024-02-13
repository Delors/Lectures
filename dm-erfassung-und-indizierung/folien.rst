.. meta:: 
    :author: Michael Eichberg
    :keywords: "Dokumenten Management"
    :description lang=de: "Dokumentenerfassung und -indizierung"
    :id: lecture-dm-erfassung-und-indizierung
    :first-slide: last-viewed

.. |date| date::
.. |at| unicode:: 0x40

.. role:: incremental   
.. role:: eng
.. role:: ger
.. role:: red
.. role:: green
.. role:: the-blue
.. role:: minor
.. role:: ger-quote
.. role:: obsolete
.. role:: line-above
.. role:: huge
.. role:: xxl
.. role:: monospaced

.. role:: raw-html(raw)
   :format: html



Dokumentenerfassung und -indizierung
===================================================================

.. container:: smaller line-above

    :Dozent: **Prof. Dr. Michael Eichberg**
    :Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
    :Version: |date|


.. container:: footer-left x-tiny 

    Dieser Foliensatz basiert auf Folien von: Klaus Götzer.
    
    Alle Fehler sind meine eigenen.

    Dokumenten-Management von *Klaus Götzer, Patrick Maué, und Ulrich Emmert*, dpunkt.verlag, 2023.




.. class:: new-section

Quellen von Dokumenten
-------------------------------------------------------------------------------



Quellen von Dokumenten - Dimensionen
------------------------------------

• Eigenerstellte und fremderstellte Dokumente
• Papierdokumente und elektronische Dokumente 
• Einmalige Übernahme und laufende Übernahme



Eigenerstellte Dokumente
------------------------------------

• Editoren für Texte, Graphiken, Mails,... (Office, Outlook, AutoCAD, ....)
• Dokumentenerzeugende Systeme (z.B. Rechnungen aus ERP-Systemen) (COLD)
• Übernahme von Bildern aus speziellen Verfahren wie Röntgen.

.. container:: assessment

    Anzustreben ist, dass beim Speichern automatisch Dokumente und Metadaten der Dokumente in das DMS übernommen werden.



Speichern von Dokumenten aus Anwendungen
------------------------------------------

.. container:: stack

    .. container:: layer

        .. image:: screenshots/elo-office-ms-word-integration-2024-02-05.png
            :width: 95%
            :align: center


    .. container:: layer incremental

        .. image:: screenshots/elo-office-ms-word-speichern-in-archiv-2024-02-05.png
            :width: 95%
            :align: center


Fremderstellte Dokumente
------------------------------------

.. container:: 

    .. rubric:: Herkunft der Dokumente 

    • Posteingang (Papier)
    • Übersendete Dateien
    • E-Mail-Eingang

.. container:: incremental

    .. rubric:: Typische Problemstellungen

    • Unterschiedliche Formate
    • Ermittlung und Erfassung der Metadaten

.. container:: incremental

    .. rubric:: Probleme beim Eingang als Papier 
        
    • Aufbereitung des Eingangs
    • Qualitätsunterschiede
    • Umsetzung in ein CI-Format

.. container:: supplemental

    :NCI: *Non Coded Information* (z.B. Texte in Bildern)
    :CI: *Coded Information*




Analoge (NCI) oder elektronische(CI) Dokumente
-------------------------------------------------

.. container:: 

    .. rubric:: Elektronische Dokumente

    .. class:: list-with-explanations

    • Welches Dateiformat liegt vor? Konvertieren?
    • Automatisch auswertbar?
    
      Strukturiertes Dokument oder Fließtext?

.. container:: incremental

    .. rubric:: Papierdokument

    • S/W oder farbig?
    • Automatisch auszuwerten?
    • Aufwand für manuelle Vorbereitung (Entheften, Glätten, ..)



.. class:: vertical-title smaller-slide-title 

Beispiel für Eingangspostbearbeitung
---------------------------------------

.. container:: two-columns margin-left-1em

    .. container:: column

        • Workflow zur strukturierten Abarbeitung
        • Ausnahmebehandlungen vorsehen
        • Möglichst automatische Klassifikation und Indizierung

    .. container:: column

        .. figure:: drawings/eingangspostbearbeitung.svg
            :height: 1175px
            :align: center



.. class:: smaller-slide-title

Unterstützung für Workflowdefinitionen in ECM Systemen
-------------------------------------------------------

.. figure:: screenshots/docuware-workflow-manager-2024-02.webp
    :height: 900px
    :align: center
    :class: box-shadow

    https://start.docuware.com


.. container:: supplemental
    
        :ECM: *Enterprise Content Management*


Erstmalige Übernahme von Dokumenten
---------------------------------------

.. container:: 

    .. rubric:: Quellen

    • Altsystem (Archiv, DMS) 
    • Filesystem
    • Mikrofilm, Mikrofish etc. 
    • Papierbeständen

.. container:: incremental

    .. rubric:: Zu Klären

    • Was ist wirklich sinnvoll zu übernehmen?
    • Automatisierbare Übernahme möglich? (Zeitaufwand!) 
    • Outsourcing prüfen


Laufende Übernahme
------------------------

• Eingehende Papierpost 
• Eingehende E-Mails
• Ausgehende Dokumente 
• Ausgehende E-Mails
• Fortschreibungen von Dokumentationen, Akten etc.

.. container:: assessment incremental

    **Zentrale Aspekte**

    • Etablierter „revisionssicherer“ Prozess 
    • Möglichst „Vollautomatik“



Automatisierung des Posteinganges (Papier)
--------------------------------------------

.. class:: incremental list-with-explanations

• **Sichere Übernahme des Dokuments in das DMS/Archiv**

  - Protokollieren des Eingangs
  - Zählen (Scanprozess) und paginieren
  - Zeitsignatur / Bearbeitersignatur

• **Klassifikation des Dokuments und Indizierung**

  - Manuell durch Bearbeiter
  - Automatisch (Formularerkennung, OCR - Volltext, Barcode) 
  - Gemischte Verfahren

• **Zuordnung zu einem Geschäftsvorfall**
 
  - Abgeleitet aus Metadaten
  - Durch Bearbeiter

• **Weitere Bearbeitung veranlassen**

  - Weiterleitung (E-Mail)
  - Workflow




.. class:: new-section

Scanning von Dokumenten
-------------------------------------------------------------------------------



Scannen der Eingangspost
--------------------------

• Scanner: meist verbreitetes Erfassungsgerät für Dokumente auf Papier oder Film

  .. admonition:: Prozess
  
    Papierdokument → Scannen → Elektronisches Dokument

• Scanning ist ein komplexer mehrstufiger Prozess zur Erfassung von Dokumenten
• Scanning ist meist mit weiteren Verarbeitungsschritten eng verknüpft.
• Zum Scannen und der Folgebearbeitung werden oft Speziallösungen eingesetzt.



Scanprofile (hier in Elo Office)
---------------------------------

.. container:: stack

    .. container:: layer

        .. image:: screenshots/elo-office-vordefiniertes-scanprofil-2024-02-05.png
            :width: 1600px
            :align: center

    .. container:: layer incremental

        .. image:: screenshots/elo-office-scanprofil-2024-02-05.png
            :width: 1600px
            :align: center



.. container::  supplemental

    Festgelegt wird: 

    • Auflösung
    • Farbe oder S/W 
    • Trennseiten
    • Barcodes
    • Duplex
    • Zielformat
    • ...


Scanner
---------------------------------

.. container:: two-columns
    
        .. container:: column no-separator
    
            Scanner unterscheiden sich in:

            - Zufuhr von Seiten 
            - Vorlagengröße (z.B. A4, A3)
            - Geschwindigkeit (bis zu mehrere hundert Seiten pro Minute)
            - Farbtiefe
            - Umschlagerkennung
            - Heftklammererkennung
            - Preis
            - ... 
    
        .. container:: column
    
            .. figure:: screenshots/hochleistungsscanner-140blatt_pro_min-canon-scanmachine-10000eur-2024.webp
                :height: 800px

            .. class:: text-align-center
            
                `Scanmachine <https://www.scanmachine.de>`__



Weiterverarbeitung gescannter Dokumente
---------------------------------------

• Umwandlung von Images (NCI) im CI-Dokumente (wie Texte)
• Klassifikation und Indizierung der Dokumente 

  - manuell
  - automatisch
  
• Automatisches Auslesen von Formulardaten
• Automatisches Auslesen von Rechnungen oder ähnlichem (z.B. wenn Dokumentenklasse bekannt ist)



Umwandlung von NCI zu CI
---------------------------------

:Optical Charakter Recognition (OCR):

    Primär auf Basis der Form der Zeichen der Maschinenschrift werden Pixelmuster in Zeichen umgesetzt

.. class:: incremental

:Handprint Charakter Recognition (HCR):

    Erkennen von handschriftlichen Texten.

.. class:: incremental

:Intelligent Charakter Recognition (ICR):

    Weiterentwicklung von OCR und HCR: Das Ergebnis wird verbessert durch modernste Algorithmen und KI-Verfahren.

.. class:: incremental

:Optical Mark Recognition (OMR):

    Es werden Markierungen in vordefinierten Feldern/Bereichen ausgelesen (wie z.B. Selektionsfelder aus Fragebögen oder geprüft ob :ger-quote:`eine Unterschrift` in dem vorgesehenen Feld erfolgt ist. )



Arbeitsablauf beim Scannen
------------------------------

.. image:: drawings/scannen.svg
    :height: 900px
    :alt: Arbeitsabläufe beim Scannen unterschiedlicher Mengen von Dokumenten
    :align: center



Sicherstellung der Qualität
---------------------------------

.. class:: incremental

.. container:: stack

    .. container:: layer

      **Fehleranzahl** hängt stark ab von...

      - Vorlagenqualität (Knicke, Schmutz, ...)
      - Schriftgröße
      - Sonderzeichen
      - Schriftart (mit/ohne Serifen...) und Qualität des Ausdrucks
      - Qualität der Software
      - Vorinformationen (welche Schriftarten werden verwendet...)
  
  
    .. container:: layer incremental

      **Problemfälle**

      - Ligaturen (z.B. :monospaced:`ﬃ statt ffi oder ﬁ statt fi`)
      - Bestimmte Zeichenkombinationen z.B. rn: ‚r‘ gefolgt von ‚n‘ oder ‚m‘
      - Großes I (wie Ida) und kleines l (wie lieb) bei serifenlosen Zeichensätzen
      - Fremdsprachige Zeichen (z.B. $)
      - Optisch beschädigte Zeichen

    .. container:: layer incremental

      Es muss **unterschieden werden** zwischen

      - nicht erkannten Zeichen → werden von OCR-Software i.d.R. entsprechend markiert
      - falsch erkannten Zeichen → müssen im konvertierten Text mühsam gesucht werden


.. container:: supplemental

    Serifenlose Zeichensätze sind solche, bei denen die Zeichensätze keine Endstriche an Zeichen haben. Z.B. Arial oder Helvetica.



Barcode/ QR-Code
-----------------

• Wird im DMS-Umfeld zur Identifizierung von Dokumenten eingesetzt
• 2 Einsatzgebiete

  - Selbst erzeugte Dokumente (z.B. Anträge) mit Barcode-Aufdruck: Beim Rücklauf automatisch erkennbar
  - Für Fremddokumente: Barcode-Etiketten (Szenario „Spätes Archivieren“)

• Sehr robust und etabliert
• Bar-/QR-Codes weisen sehr hohe Erkennungsraten auf

.. image:: screenshots/lohnsteuer-mit-qr-code.jpeg
    :width: 1000px
    :align: center
    :class: box-shadow


Szenarien: Zeitpunkt des Scannens
---------------------------------

Drei typische Erfassungsszenarien für Eingangspost:

- Scannen im Posteingang (frühes Archivieren)
- Scannen zum Zeitpunkt der Bearbeitung
- Scannen nach der Bearbeitung (Spätes Archivieren)



Szenario 1: Erfassen beim Posteingang (*Frühes Archivieren*)
--------------------------------------------------------------

- Eingehende Dokumente werden vor der eigentlichen Bearbeitung gescannt

  - Scannen erfolgt meist im Posteingang
  - Weiterleitung an Sachbearbeiter auf elektronischem Weg
  
- Vor elektronischer Weiterleitung: evlt. Klassifikation + evtl. Attributierung

.. container:: two-columns  small incremental

    .. container:: column

        Vorteil: Elektronische Weiterleitung

        .. class:: positive-list

        - Kurze Transportzeiten, geringe Transportkosten
        - Weiterleitung an mehrere Personen
        - Evlt. automatisierte Adressermittlung
        - Steuerung und Verfolgen der Bearbeitung (Workflow)
     
    .. container:: column margin-left-1em

      Nachteil:

      .. class:: negative-list

      - Sachbearbeiter benötigen Arbeitsplatz mit DMS-Zugang 
      - ggf. Neuausrichtung des Geschäftsprozesses
      - ggf. aufwändiger Einstieg


Szenario 2: Erfassung bei der Bearbeitung
------------------------------------------

- Dokumente gelangen in Papierform zum Sachbearbeiter
- Dort werden sie direkt vor oder gleich nach der Bearbeitung eingescannt, attributiert und abgelegt

.. container:: small incremental
        
    **Einsatzgebiet**

    .. class:: list-with-explanations

    - Erfassung, Nachbearbeitung oder Attributierung ist aufwendig oder erfordert spezielle Sachkenntnis
    - Fehlgeleitete Belege werden in das DMS eingebracht 

      (ggf. in Ergänzung zum „Frühen Archivieren“)

    - kleine Dokumentenmengen, nicht für Massenbearbeitung geeignet


    **Nachteile**

    .. class:: negative-list

    - Bearbeitungsplätze müssen mit Scanner ausgestattet sein.
    - Ständiger Wechsel zw. Dokumentenerfassung und Dokumentenbearbeitung stört Arbeitsfluss
    - Einsatz teurer Personalressourcen (Sachbearbeiter) für einfache Tätigkeiten (Scannen, Attributieren)



Szenario 3: Spätes Archivieren
-------------------------------

• Papierdokumente werden nach ihrer Bearbeitung an zentrale Erfassungsstelle geschickt und dort eingescannt.
• Zusätzlich wird ein Identifikator für das Papierdokument benötigt.

  .. class:: small

  - für Zuordnung des Papierdokuments zu Vorgang während Bearbeitung 
  - Bar-/QR-Code oder Referenznummer/Belegnummer
  
.. class:: small

• Bar-/QR-Code:

  - Registrierung: Dokument erhält eindeutigen Barcode z.B. im Posteingang oder durch Sachbearbeiter
  - Barcode-Erfassung mit Barcodestift oder Lesepistole
  - Erfassung des Papierdokuments
  
    - Erfassungssoftware erkennt Code automatisch
    - Code auf der ersten Seite kann gleichzeitig für Dokumententrennung genutzt werden
    - Die Zuordnungstabelle zw. Code und Dokument ist regelmäßig zu prüfen, ob alle registrierten Dokumente zwischenzeitlich gescannt wurden.

  - Code wird nach Erfassung des Dokuments nicht mehr benötigt; Wiederverwendung ist ca. nach 1 Jahr


Szenario 3: Spätes Archivieren - Bewertung
---------------------------------------------

**Vorteile**

.. class:: positive-list

- Arbeits- und Papierflüsse können weitgehend wie bisher abgewickelt werden
- Papierdokumente (z.B. Rechnungen) können vor ihrer Erfassung noch geprüft und abgezeichnet werden: Stempel, Unterschrift, Korrekturen werden beim Scannen erfasst
- Arbeitsplätze der Sachbearbeiter erfordern keine spezielle Ausstattung

**Nachteile**

.. class:: negative-list

- Eigentliches Potenzial elekt. Dokumente wird nicht genutzt
- Gefahr des Verlusts oder der Beschädigung des Papierdoks höher



Zusammenfassung
------------------ 

• Frühes Scannen vs. Spätes Scannen oder Scannen bei der Sachbearbeitung
• Zentrales Scannen vs. dezentrales Scannen
• Scannen und indizieren gleichzeitig oder zeitlich versetzt
• Selbst scannen oder Outsourcing (externer Dienstleister)



.. class:: new-section

COLD-Verfahren (Computer Output on Laser Disk)
-------------------------------------------------------------------------------


COLD
---------------------------------

Begriff stammt aus der Zeit Mitte der 80er Jahre, hatte sich aber bereits zu Beginn/Mitte der 90er  technologieunabhängig verallgemeinert.

Beschreibt **die direkte digitale Speicherung von  von Druck- und Listenausgaben  betrieblicher Softwaresysteme** (z.B. direkt von ERP Systemen oder von Office Anwendungen über spezielle Druckertreiber).

• Die Recherche kann danach wie bei jedem anderen Dokument im DMS erfolgen.
• COLD bei größeren Unternehmen bzw. DMS-Lösungen sehr verbreitet.
• COLD-Verarbeitung ist typische Batch-Verarbeitung.

.. container:: supplemental

    D.h. bei COLD werden die Daten nicht mehr - bzw. nur optional - auf Papier ausgegeben, sondern stattdessen direkt in ein DMS übernommen. Da kein OCR notwendig ist, sondern die Daten direkt :ger-quote:`beim Drucken` abgegriffen werden, ist die Qualität der Daten sehr hoch.


COLD-Verfahren (historisch)
---------------------------------

.. container:: two-columns

    .. container:: column

        .. image:: drawings/cold.svg
            :height: 900px
            :align: center

    .. container:: column

        **Verarbeitung COLD-Server**

        1. Zerlegung des Datenstrom in einzelne Dokumente
        2. Extrahiert die für die Ablage bzw. spätere Recherche der Dokumente notwendigen Index-Daten automatisch + evtl. Bezug zu Overlays (Trennung zwischen fachlichen und layout Daten)
        3. Konvertierung bringt die Dokumente in eine für die Ablage geeignete Form


.. class:: new-section

Metadaten für Dokumente
-------------------------------------------------------------------------------

Metadaten
---------------------------------


• Beschreibende Merkmale für Dokumente
• Ziel ist das möglichst exakte Wiederfinden der richtigen 
  Dokumente (strukturierte Suche!)
• Metadaten sind strukturiert und möglichst exakt vordefiniert (z.B. Wertebereiche)
• Quellen für Metadaten:

  - Manuelles Erfassen
  - Aus dem Dokument automatisch ermitteln
  - Aus anderen Anwendungen / Quellen übernehmen



Manuelles Indizieren
---------------------------------

- Freitexteingabe (z.B. Zusammenfassung, Notizen)
- Unterstützung durch Auswahlmenüs, Formatvorgaben oder Defaultwerte, z.B

  - Schlagwortindizierung (definierter Wortschatz) 
  - Formalisierte Eingabe (z.B. Datum)
  
- **Probleme**:

  .. class:: negative-list

  - Fehleranfällig
  - Aufwändig
  - Ergebnis vom Bearbeiter abhängig



Suche und Retrieval von Dokumenten
------------------------------------

**Strukturierte Suche**

Unter Nutzung der Metadaten werden gezielte Anfragen an das DMS
gestellt.

.. class:: positive-list

- Suche per Daten über Dokumente, die nicht unbedingt direkt in den Dokumenten zu finden sind.

.. class:: negative-list

- Suchraster ist vorgegeben (d.h. Metadatenschema ist fest) 

**Volltextsuche**

Wenn die Dokumente als CI-Dateien vorliegen, dann kann man auch mittels Volltext suchen.
Evtl. ergänzt um semantische Hilfsmittel (Thesaurus, etc. ).

.. class:: positive-list

- Vorteil: Man kann jedes Wort wiederfinden.

.. class:: negative-list

- Unstrukturiert, :ger-quote:`langsam`, Ressourcenbedarf, keine semantisch zusammenfassenden Informationen abfragbar
