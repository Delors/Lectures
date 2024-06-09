.. meta:: 
    :author: Michael Eichberg
    :keywords: "Dokumenten Management"
    :description lang=de: "Einführung in das Dokumenten Management"
    :id: lecture-dm-einfuehrung
    :first-slide: last-viewed

.. |html-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html
.. |pdf-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html.pdf
.. |date| date::
.. |at| unicode:: 0x40

.. role:: incremental   
.. role:: eng
.. role:: ger
.. role:: the-blue
.. role:: minor
.. role:: ger-quote
.. role:: obsolete
.. role:: line-above

   

Dokumentenmanagement, Archivierungs- und Verschlüsselungsverfahren
===================================================================

.. container:: smaller line-above

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.rst.html>`__
    :Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
    :Version: |date|

.. supplemental::

  :Folien: 
      :HTML: |html-source|

      :PDF: |pdf-source|
  :Fehler auf Folien melden:
      https://github.com/Delors/delors.github.io/issues


.. container:: footer-left smallest incremental

    Dieser Foliensatz basiert auf Folien von: Klaus Götzer.
    
    Dokumenten-Management von *Klaus Götzer, Patrick Maué, und Ulrich Emmert*, dpunkt.verlag, 2023.

    Alle Fehler sind meine eigenen.

    



Dokumentmanagement, Archivierungs- und Verschlüsselungsverfahren - W3WI_EH304.1
----------------------------------------------------------------------------------

:Verortung: Drittes Studienjahr (5. oder 6. Semester)
:Umfang: 25 Vorlesungsstunden und 50 Stunden Selbststudium (2,5 ECTS) 
:Übergeordnetes Modul: Fortgeschrittene Konzepte des Informationsmanagements im Gesundheitswesen (W3WI_EH304)
:Prüfungsleistung: :obsolete:`Klausur (60 min/60 Punkte)` oder 

    **Portfolio** (hier mündl. Prüfungsgespräche mit 10min pro Person.)


.. container:: supplemental

    .. rubric:: Kerninhalte gem. MHB

    - Definitionen, Grundsätze, Aufgaben, Funktionen und Prozesse von Dokumentenmanagement 
    - Archivierungssysteme 
    - Struktur elektronischer Verwaltungsunterlagen, Archivierungsvarianten der Software- und Hardware-Lösungen 
    - Rechtliche, technische, organisatorische und wirtschaftliche Anforderungen 
    - Schnittstellen 
    - Aktuelle Standards 
    - Verfügbarkeit elektronischer Verwaltungsakten 
    - IT-Sicherheit von digital erzeugten und gescannten Dokumenten 
    - Elektronische Signaturen
    - Archivierungsverfahren 
    - Verschlüsselungsverfahren


Literatur
-------------------------------------------------------------------

- Bzgl. Dokumentenmanagement:

  .. image:: images/dokumentenmanagement.jpg
        :class: box-shadow

- weitere bzgl. der IT sicherheitsrelevanten Aspekte: Verschlüsselungsverfahren und Signaturen


.. class:: new-section

Warum Dokumentenmanagement?
-----------------------------------------------------------------



Motivation
----------------------------------------------------------------------------------

.. class:: incremental list-with-explanations

- Die meisten strukturierten Daten liegen elektronisch vor (ERP, CRM, etc.). 
- Dokumente liegen aber (noch immer) in Papierform vor (insbesondere in Deutschland) und es gilt dieses Rationalisierungspotential zu heben.
- Alle reden von elektronischen Geschäftsprozessen und Digitalisierung - Dokumente sind ein wesentlicher Bestandteil!
- Wichtige gesetzliche und technische Grundlagen sind geschaffen.
- Experten schätzen, dass 95% der Papierdokumente nach Ablage nicht mehr genutzt werden, da sie zu schwer zu finden sind.
- Ähnlich hohe Werte werden für digitale Dokumente genannt, die ohne weitere Strukturierungshilfen in Dateisystemen oder Datenbanken liegen. 
- Die Menge an Dokumenten, die heute entstehen kann durch eine einzelne Person nicht mehr bewältigt werden.

.. supplemental::

  Zum  Beispiel fallen für ein einzelnes konventionelles Kraftwerk bereits ca. 500  000 Dokumente.
   

.. class:: smaller

Digitalisierung - ein langwieriger Prozess...
-------------------------------------------------

.. figure:: images/floppydisk.com.2024-01.png
    :width: 70%
    :align: center
    
    Jan. 2024


.. class:: smaller

Digitalisierung - ein langwieriger nicht-aufzuhaltender Prozess...
----------------------------------------------------------------------------------

  .. rubric::  `Japanese government finally bids sayonara to the 3.5" floppy disk <https://www.theregister.com/2024/01/29/japan_government_floppy_disks/>`__

  **Businesses can at long last submit digital docs to government agencies**

.. epigraph:: 

   Japan is saying sayonara to the floppy disk, which until now was a required medium for submitting some 1,900 official documents to the government.

   The announcement (Japanese, machine translated) last week from the Ministry of Economy, Trade and Industry brings decades of physical media submission requirements in Japan to an end. [...]

   Despite being a world leader in cutting-edge technology, Japan has an odd relationship with legacy tech. It's still a land of **cash-only payments and fax machines** that has moved slowly to embrace the modern digital economy.

   -- The Register, Mon 29 Jan 2024 // 19:00 UTC


.. class:: vertical-title

Digitalisierung - ein Prozess mit Stilblüten...
-------------------------------------------------

.. image:: images/sparkasse-verschickt-usb-sticks.2024-03-03.png
    :height: 1175px
    :align: center



.. class:: center-child-elements transition-scale

\ 
---

.. container:: 

    .. epigraph:: 

        Wie die zahlreichste Bibliothek, wenn ungeordnet, nicht so viel Nutzen schafft, als eine sehr mäßige, sorgfältig geordnete; eben so ist die größte Menge von Kenntnissen, die nicht gehörig durch eigenes Denken bearbeitet werden, viel weniger Wert als eine viel geringere Menge von Kenntnissen, die gehörig durchdacht werden.

        -- Arthur Schopenhauer (mutmaßlich)



.. class:: smaller

Was kann ein Dokumentenmanagement System (DMS) leisten?
-------------------------------------------------------------------------------

.. class:: incremental

• Beschleunigung der Prozesse

    - Unabhängigkeit des Zugriffes von Ort und Zeit
    - Schnelle Verfügbarkeit der Dokumente
    - Gleichzeitiger Zugriff auf die Dokumente durch mehrere Mitarbeiter

• Revisionssicherheit der Ablage
 
    - Strukturierte Ablage und Suche von Dokumenten
    - Sicherheit der Dokumente vor Verfälschung und Verlust 
    - Transparenz der Prozesse
• Redundanzfreie Archivierung
• Kostenreduktion (Bearbeitungszeiten, Archivkosten.... )

.. container:: supplemental

    Revisionssichere Archivsysteme stellen sicher, dass Informationen wieder auffindbar, nachvollziehbar, unveränderbar und verfälschungssicher archiviert sind.



Gegenüberstellung konventionelles Archiv zu DMS
-------------------------------------------------------------------------------

.. csv-table::
    :class: highlight-line-on-hover slightly-more-smaller incremental
    :header: " ", "Konventionell","DMS"

    Ablagestruktur, "hierarchisch, meist nach Dokumententypen getrennt", "datenbankgestützt, Suchbeginn nach jedem Suchkriterium möglich, Suche wahlweise z. B. je Kunde, Vorgang oder Beleg"
    Auskunftsbereitschaft,"zeitverzögert, nach Sichtung/ Entnahme aller Dokumente", "unmittelbar durch vorgangsbezogene bzw. dokumentenübergreifende Suche"
    Auskunftsaufwand, "für Belegsuche, Entnahme und wieder einordnen, evtl. kopieren und Versand", "nur für Recherche am Bildschirm, gegebenenfalls direkter Versand per E-Mail"
    Redundanz, "Abteilungsablagen = mehrfacher Aufwand", Einmalablage
    Vollständigkeit, "leidet unter jeder Entnahme", bleibt immer gewahrt
    Sicherheit, "Ordner u. Dokumente lassen sich leicht entfernen oder kopieren", "klare Regelung der Zugriffsrechte, keine ungewollte Entnahme möglich"



.. class:: vertical-title

Zyklus von Dokumenten
---------------------------------------

.. image:: drawings/dokumente/lebenszyklus.svg
    :height: 1150px
    :align: center



Grundlegende Voraussetzungen für Dokumentenmanagementorganisation
-------------------------------------------------------------------------------

In einer Organisation ist zu regeln:

.. class:: incremental

• Kennzeichnung und Beschreibung von Dokumenten
• Fortschreibung und Historienverwaltung von Dokumenten
• Ablage und Archivierung von Dokumenten
• Verteilung und Umlauf von Dokumenten
• Suche nach Dokumenten bzw. Dokumenteninhalten
• Vernichtung von Dokumenten
• Regelung von Verantwortlichkeiten für Inhalt und Verwaltung von Dokumenten

.. container:: assessment incremental
    
    Ein Dokumentenmanagementsystem ist ein IT System zur Unterstützung der Dokumentenmanagementorganisation.


.. class:: new-section

Dokument und Dokumentenmanagement
----------------------------------

Dokumente - Beispiele
----------------------

.. image:: drawings/dokumente/dokumente.svg
    :height: 950px
    :align: center
    :class: incremental



Was ist ein Dokument aus logischer Sicht?
-------------------------------------------

.. admonition:: Definition

    Ein Dokument fasst inhaltlich zusammengehörende Informationen strukturiert zusammen, die nicht ohne erheblichen Bedeutungsverlust weiter unterteilt werden könnten.

    • Die Information ist für einen gewissen Zeitraum zu erhalten.
    • Dokumente dienen dem Nachweis von Tatsachen.
    
.. container:: assessment incremental
    
    Das Dokument ist somit eigentlich der Träger, der die Informationen speichert, unabhängig davon ob das Dokument ein Stück Papier, eine Datei auf einem Rechner, ein Videoband oder eine Tontafel ist etc.



Was ist ein Dokument aus technischer Sicht?
-------------------------------------------

.. admonition:: Definition

    Ein Dokument ist ein Objekt, das in einer Datenbank beschrieben wird.

    Das beschriebene Objekt kann selbst elektronisch gespeichert werden.


.. container:: supplemental

    Es ist somit eine reine Deklarationsfrage, was ein Dokument ist!



.. class:: vertical-title

Elektronische Dokumente
-------------------------


.. image:: drawings/dokumente/elektronische_dokumente.svg
    :height: 1150px
    :align: center

.. container:: supplemental

    :NCI: *Non-Coded-Information (NCI)-Dokumente* sind eingescannte Unterlagen, die als Bild vorliegen, also keine direkte Bearbeitung/Verarbeitung ermöglichen. 

    :CI: Ein *CI-Dokument* ist ein digital erstelltes Dokument, das durch Zeichensätze kodiert ist und von Programmen direkt ausgewertet werden kann.

    :OCR (Optical Character Recognition):  Text einer gedruckten Vorlage wird durch einfachen Mustervergleich automatisch in maschinenlesbare Zeichen transformiert. 

    :ICR (Intelligent Character Recognition): Die Qualität der Texterkennung wird durch Kontextanalyse verbessert. Typische Fehler von OCR-Systemen, wie zum Beispiel Fehlerkennungen von optisch nahe beieinanderliegenden Zeichen (z. B. „8 und B“ oder „0 und O“), werden vermieden. (Wie nahe Zeichen beieinander liegen, ist stark vom verwendeten Schrifttyp abhängig.)

    :OMR (Optical Mark Recognition): Liest mit großer Sicherheit spezielle Markierungen in vordefinierten Feldern aus - zum Beispiel in Multiple-Choice-Tests und Vordrucken.


.. TODO Folie bzgl. strukturierter und unstrukturierter Daten (Dokumentenmanagement S. 103)



Bestandteile eines Dokumentes
--------------------------------

.. the following is necessary, because we can't have local svgs that reference local pngs... (browser security)
.. container:: stack

    .. container:: layer

        .. image:: images/eheurkunde.png
            :height: 950px
            :align: center

    .. container:: layer overlay incremental

        .. image:: drawings/dokumente/mit_stempel.svg
            :height: 950px
            :align: center

    

Struktur eines Dokumentes
--------------------------------

.. container:: stack

    .. container:: layer

        .. image:: images/berufung.png
            :height: 950px
            :align: center


    .. container:: layer overlay

        .. image:: drawings/dokumente/struktur.svg
            :height: 950px
            :align: center



Dokumente annotieren
--------------------------------

.. image:: images/dokument_mit_anmerkungen.png
    :height: 1050px
    :align: center



*Renditions* eines Dokumentes
--------------------------------

.. class:: incremental

- Man unterscheidet zwischen dem Originalformat des Dokuments (z. B. von MS-Word oder LibreOffice) und Renditions (wie PDF/a und TIFF).
- Formate wie PDF/a und TIFF sind in der Regel langlebiger. Für beide Formate gibt es weitverbreitete Viewer und sie erhalten besser den ursprünglichen optischen Zustand.
- Preview Images sind oft zusätzlich eingebettet.



Hashwerte und Signaturen [#]_
--------------------------------

.. class:: incremental

- Hashwert ist wie ein mathematischer Fingerabdruck des Dokumentes.
  
- Dieser Hashwert – verknüpft mit einer persönlichen Signatur – zeigt, dass dieses Dokument von dem Absender der Signatur stammt und das Dokument nicht verändert wurde.



.. [#] Hashwerte und Signaturen werden wir ausführlich im Zusammenhang mit Verschlüsselungsverfahren betrachten.



Volltext(-indizierung)
--------------------------------

• Für Volltextindizierung wird oft der Volltext - insbesondere wenn er durch OCR/ICR gewonnen wurde - mit dem Dokument abgespeichert.
• Die indizierten Begriffe werden oft mit der Angabe der Fundstelle im Dokument abgespeichert, um innerhalb des Dokuments das Suchergebnis anzeigen zu können.



Versionierung von Dokumenten
--------------------------------

.. class:: incremental

• Was ist die aktuelle gültige Version?
• Was hat sich gegenüber den Vorgängern geändert?
• Was ist für die nächste in Bearbeitung? 

  - Vorgängerversion(en)
  - Freigegebene Version
  - Bearbeitungsversionen
  
• Versionen des Dokumentes
• Versionen der Metadaten des Dokumentes



Meta-Daten
--------------------------------


• Strukturierte Daten, die das Dokument klassifizieren und beschreiben

  Beispiele:

  .. class:: incremental

  - Eindeutige Schlüssel wie Personalnr., Produktnr., ...
  - Stichwörter zum Klassifizieren des Textes
  - Datum der Erstellung, Änderung, ...
  - Autor
  - Kategorien wie Mahnung, Anfrage, Branche, Land, ... 
  - Quelle des Dokuments (Zeitschrift...)

.. container:: supplemental

    Dies ist insbesondere ein Thema der Datenmodellierung, d. h. welche Daten möchte man wie erfassen.



Arten von Meta-Daten
--------------------------------


• Eindeutiger Schlüssel im DMS
• Fremdschlüssel (z. B. Buchungsnummern)

.. container:: incremental margin-top-2em

  • Statische Metadaten (unveränderlich)
  • Dynamische Metadaten (wie Status oder Version der Dokumente)



Beispiele von Meta-Daten einer Verwaltungssoftware für grafische Zeichnungen
----------------------------------------------------------------------------------------------

.. csv-table::
    :class: highlight-line-on-hover far-smaller incremental
    :header: Nr., Attribut, Muss, Funktion, Quelle, Bemerkung

    1, Zeichnungsnummer, M, Eindeutiger Schlüssel, Manuelle Vergabe durch Benutzer, Identifiziert Zeichnung
    2,"Zeichnungsmappen- nummer", M, Fremdschlüssel, , 
    3a, Version, M, Version der Zeichnung verwalten, Automatische Vergabe durch DMS bei Check-in, Benutzer entscheidet, ob *minor* oder *major*
    3b, Check-In-Datum, M, Datum des Check-in der Version, Automatische Vergabe durch DMS, Check-in Datum
    3c, Dokumenten-Owner, M, Gruppe aus letzten Bearbeitern, Aus USER-ID abgeleitet
    3d, Letzter Bearbeiter, M, Identifikation, USER-ID, Beim Check-in



Zusammenfassung: Dokumente in einem DMS
-----------------------------------------------

Ein Dokument in einem DMS ist ein komplexes Objekt, das aus verschiedenen Komponenten bestehen kann:

.. class:: incremental

• Das Dokument im Originalformat (z. B. odt, docx, xlsx, txt, ...)
• Verschiedene Renditions (pdf, tiff, xml, ....)
• Vorschaubild
• Volltext
• Annotationen (Layer für Anmerkungen, Stempel, ...)
• Hashwert, um elektronische Signaturen zu erzeugen und/oder zu prüfen
• Elektronische Signaturen
• Versionen des Dokumentes
• Metadaten des Dokumentes bzw. der Komponenten des Dokumentes



Dokumentenstrukturen
-----------------------------------------------

.. class:: incremental

• Welche Dokumente bilden eine logische Einheit („Mappen“, „Ordner“, „Vorgang“)?
• Metadaten zu diesen Mappen definieren.
• Ein Dokument kann in mehreren Mappen sein.
• Der Inhalt einer Mappe unterteilt sich in:

  1. Dokumente, die immer da sein müssen, 
  2. solche, die optional da sind und
  3. in nicht vorhersehbare Exoten.
   
.. supplemental::
     
    Ein Beispiel einer Mappe wäre eine Vorgangsakte mit einem Antrag, (am Ende) einem Gutachten, ggf. E-Mails aber auch handschriftlichen Notizen ...



Zusammengesetzte Dokumente 
-------------------------------------------------------

Komplexes Objekt aus mehreren Dokumenten mit eigener Verwaltungsstruktur:

- Metadaten
- Versionen 
- Rechte

.. supplemental::

    Zusammengesetzte Dokumente ≘ :eng:`Compound Documents`


.. class:: new-section

Dokumentenlebenszyklus
-----------------------------------------------



Dokumentenlebenszyklus - Überblick
-----------------------------------------------

.. class:: center-child-elements 

    .. image:: drawings/dokumente/lebenszyklus_a_bis_z.svg
        :width: 100%
        :align: center



Dokumentenlebenszyklus
-----------------------------------------------

Dokumente ...

.. class:: incremental

• entstehen
• verändern sich
• werden festgeschrieben
• dienen als Nachweis / Infoquelle
• müssen bestimmte Zeit aufbewahrt werden
• können bzw. müssen gelöscht werden.



Erstellen von Dokumenten
-----------------------------------------------

.. class:: incremental

• Scannen analoger Dokumente (Papier, Mikrofilm, ..)
• Neuerstellung von Dokumenten (Vorlagen im DMS,..)
• Vorhandene Dokumente einstellen (*drag and drop*)
• Dokumente aus Applikationen übernehmen (SAP-Archive-Link, Mail-Archivierung, ...)
• Spezielle Verfahren bei Migration und Massenimporten
• Indizieren der Dokumente entweder automatisch oder manuell



Nutzen und Bearbeiten von Dokumenten
-----------------------------------------------

.. class:: incremental

- Suchen und Retrieval:

  - Volltext
  - Indizes
  - Verknüpfungen (z. B. in Applikationen)
  
- Ausgabe der Dokumente auf Bildschirm, Drucker, Mail
  
- Check-out / bearbeiten / Check-in



Rahmenbedingungen für die Lebensdauer von Dokumenten
------------------------------------------------------

.. class:: incremental

• Betriebliche Notwendigkeiten
• Gesetzliche Aufbewahrungspflichten 
• Datenschutzbestimmungen


.. container:: supplemental

    - Konzept zur intelligenten Verwaltung, Bewertung und Nutzung von Daten bei möglichst geringen Kosten (Geschäftsregeln, Servicelevel, ..)
    - Betrachtung der Daten und Dokumente über ihren gesamten Lebenslauf, aber nicht in Abhängigkeit vom Alter sondern von der Wichtigkeit
    - Entwicklung optimaler Verwaltungsstrategien in Abhängigkeit von der aktuellen Wichtigkeit und Nutzung 
    - Enge Verzahnung von Speicherhardware, Archivierung und Daten-, Dokumenten- und Content-Management



Löschen von Dokumenten
-----------------------------------------------

• Falsche Dokumente (z. B. Fehler beim Indizieren)
• Nicht mehr benötigte Dokumente

.. class:: incremental

• Logisches Löschen
• Physikalisches Löschen



.. class:: center-child-elements

Archivierung (von Dokumenten)
-----------------------------------------------

.. admonition:: Definition
    
    **Archiv**
    
    Ein Archiv ist ein realer oder elektronischer Ort, in dem Dokumente, die zur laufenden Aufgabenerfüllung nicht mehr benötigt werden, erfasst, ausgewertet und zugänglich gemacht werden.



Kerneigenschaften elektronischer Archive
-----------------------------------------------

Archiv System müssen die folgenden Eigenschaften bei langjähriger Aufbewahrung unterstützen:

.. class:: incremental

:Integrität: Nachweis, dass die Informationen während der Aufbewahrung nicht verändert oder gelöscht wurden.

.. class:: incremental

:Authenzität: Herkunft lässt sich zweifelsfrei nachweisen.

.. class:: incremental

:Lesbarkeit: Informationen müssen auch in Zukunft lesbar sein.

.. container:: assessment incremental

    Eigenschaften wie die Unterstützung von Volltextindizierung sind nicht in allen Fällen notwendig. 

.. supplemental::

    Z. B. muss ein Insolvenzverwalter nach dem Abschluss des Verfahrens die Dokumente 10 Jahre aufbewahren obwohl er in dieser Zeit die Dokumente höchstwahrscheinlich nicht mehr benötigt.

    Informationen über die Ergebnisse von arbeitsmedizinischen Untersuchungen (dies sind ggf. sehr vielfältige und umfangreiche Daten (z. B. Röntenbilder, EKGs)) müssen 30 Jahre bis nach der letzten Untersuchung aufbewahrt werden, da sich daraus ggf. Rentenansprüche ableiten. Daraus leiten sich ggf. Fristen für Dokumente von 50 Jahren und mehr ab.



Revisionssichere elektronische Archivierung
-----------------------------------------------

.. stack::

    .. layer::

        Merkmale revisionssicherer Archivsysteme sind:

        .. class:: incremental

        - Informationen sind wieder auffindbar, 
        - Änderungen sind nachvollziehbar, 
        - Informationen sind unveränderbar und 
        - die Informationen sind verfälschungssicher archiviert 

    .. layer:: incremental

        Aufgrund von HGB-Vorschriften gilt für die Revisionssicherheit bei der Archivierung:

        .. class:: incremental

        - Richtigkeit
        - Vollständigkeit
        - Sicherheit des Gesamtverfahrens
        - Schutz vor Veränderung und Verfälschung
        - Sicherung vor Verlust
        - Nutzung nur durch Berechtigte
        - Einhaltung der Aufbewahrungsfristen
        - Dokumentation des Verfahrens
        - Nachvollziehbarkeit
        - Prüfbarkeit




Strategien für die langfristige Aufbewahrung von Dokumenten
------------------------------------------------------------

.. class:: incremental

- *Technikmuseum* (Variante: Alte Umgebung emulieren)
- Dauerhafte Formate nutzen
- Migration der Dokumente auf neue Umgebung




Bewertung von Dateiformaten in Hinblick auf die Dauerhaftigkeit
-----------------------------------------------------------------

.. class:: incremental

- vollständige und offene Dokumentation (am besten mit Standardisierung)
- Plattformunabhängigkeit
- nicht-proprietär (herstellerunabhängig)
- keine „verlustbehaftete“ oder proprietäre Komprimierung
- keine eingebetteten Dateien, Programme oder Skripte
- keine vollständige oder teilweise Verschlüsselung
- kein Passwortschutz
- relevante Nutzerbasis




Langfristige Aufbewahrung von Dokumenten
--------------------------------------------

.. container:: stack far-far-smaller

    .. container:: layer

        .. csv-table::
            :class:  overflow-y-scroll
            :header: "Content Type", High probability for long-term preservation, Medium probability for long-term preservation, Low probability for long-term preservation

            
            Text, "
            .. class:: incremental 
            
            • Plain text (encoding: USASCII, UTF-8, UTF-16 with BOM) 
            • XML (includes XSD/XSL/XHTML, etc.; with included or accessible schema)
            • PDF/A-1 (ISO 19005-1) (\*.pdf)", "
            .. class:: incremental 
            
            • Cascading Style Sheets (\*.css)
            • DTD (\*.dtd)
            • Plain text (ISO 8859-1 encoding)
            • PDF (\*.pdf) (embedded fonts)
            • Rich Text Format (\*.rtf)
            • HTML (include a DOCTYPE declaration)
            • SGML (\*.sgml)
            • Open Office (\*.sxw/\*.odt)
            • OOXML (ISO/IEC DIS 29500) (\*.docx)", "
            .. class:: incremental 
            
            • PDF (\*.pdf) (encrypted)
            • Microsoft Word (\*.doc)
            • WordPerfect (\*.wpd)
            • All other text formats not listed here
            
            "


    .. container:: layer incremental

        .. csv-table::
            :class:  overflow-y-scroll
            :header: "Content Type", High probability for long-term preservation, Medium probability for long-term preservation, Low probability for long-term preservation

            Raster Image, "
            .. class:: incremental 
            
            • TIFF (uncompressed)
            • JPEG2000 (lossless) (\*.jp2)
            • PNG (\*.png)", "
            .. class:: incremental 
            
            • BMP (\*.bmp)
            • JPEG/JFIF (\*.jpg)
            • JPEG2000 (lossy) (\*.jp2)
            • TIFF (compressed)
            • GIF (\*.gif)
            • Digital Negative DNG (\*.dng)", "
            .. class:: incremental 
            
            • MrSID (\*.sid)
            • TIFF (in Planar format)
            • FlashPix (\*.fpx)
            • PhotoShop (\*.psd)
            • RAW
            • JPEG 2000 Part 2 (\*.jpf, \*.jpx)
            • All other raster image formats not listed here"

    .. container:: layer incremental

        .. csv-table::
            :class:  overflow-y-scroll
            :header: "Content Type", High probability for long-term preservation, Medium probability for long-term preservation, Low probability for long-term preservation

            Vector Graphics, "
            .. class:: incremental 
            
            • SVG (no Java script binding) (\*.svg)", "
            .. class:: incremental 
            
            • Computer Graphic Metafile (CGM, WebCGM) (\*.cgm)", "
            .. class:: incremental 
            
            • Encapsulated Postscript (EPS)
            • Macromedia Flash (\*.swf)
            • All other vector image formats not listed here"

    .. container:: layer incremental

        .. csv-table::
            :class:  overflow-y-scroll
            :header: "Content Type", High probability for long-term preservation, Medium probability for long-term preservation, Low probability for long-term preservation

            Audio, "
            .. class:: incremental 
            
            • AIFF (PCM) (\*.aif, \*.aiff)
            • WAV (PCM) (\*.wav)", "
            .. class:: incremental 
            
            • SUN Audio (uncompressed) (\*.au)
            • Standard MIDI (\*.mid, \*.midi)
            • Ogg Vorbis (\*.ogg)
            • Free Lossless Audio Codec (\*.flac)
            • Advance Audio Coding (\*.mp4, \*.m4a, \*.aac)
            • MP3 (MPEG-1/2, Layer 3) (\*.mp3)", "
            .. class:: incremental 
            
            • AIFC (compressed) (\*.aifc)
            • NeXT SND (\*.snd)
            • RealNetworks 'Real Audio' (\*.ra, \*.rm, \*.ram)
            • Windows Media Audio (\*.wma)
            • Protected AAC (\*.m4p)
            • WAV (compressed) (\*.wav)
            • All other audio formats not listed here"

    .. container:: layer incremental

        .. csv-table::
            :class:  overflow-y-scroll
            :header: "Content Type", High probability for long-term preservation, Medium probability for long-term preservation, Low probability for long-term preservation

            Video, "
            .. class:: incremental 
            
            • Motion JPEG 2000 (ISO/IEC 15444-4)(\*.mj2)
            • AVI (uncompressed, motion JPEG) (\*.avi)
            • QuickTime Movie (uncompressed, motion JPEG) (\*.mov)", "
            .. class:: incremental 
            
            • Ogg Theora (\*.ogg)
            • MPEG-1, MPEG-2 (\*.mpg, \*.mpeg, wrapped in AVI, MOV)
            • MPEG-4 (H.263, H.264) (\*.mp4, wrapped in AVI, MOV)", "
            .. class:: incremental 
            
            • AVI (others) (\*.avi)
            • QuickTime Movie (others) (\*.mov)
            • RealNetworks 'Real Video' (\*.rv)
            • Windows Media Video (\*.wmv)
            • All other video formats not listed here"

    .. container:: layer incremental

        .. csv-table::
            :class:  overflow-y-scroll
            :header: "Content Type", High probability for long-term preservation, Medium probability for long-term preservation, Low probability for long-term preservation

            Spreadsheet/ Database, "
            .. class:: incremental 
            
            • Comma Separated Values (\*.csv)
            • Delimited Text (\*.txt)
            • SQL DDL", "
            .. class:: incremental 
            
            • DBF (\*.dbf)
            • OpenOffice (\*.sxc/\*.ods)
            • OOXML (ISO/IEC DIS 29500) (\*.xlsx)", "
            .. class:: incremental 
            
            • Excel (\*.xls)
            • All other spreadsheet/ database formats not listed here"

    .. container:: layer incremental

        .. csv-table::
            :class:  overflow-y-scroll
            :header: "Content Type", High probability for long-term preservation, Medium probability for long-term preservation, Low probability for long-term preservation

            Virtual Reality, "
            .. class:: incremental 
            
            • X3D (\*.x3d)", "
            .. class:: incremental 
            
            • VRML (\*.wrl, \*.vrml)
            • U3D (Universal 3D file format)", "
            .. class:: incremental 
            
            • All other virtual reality formats not listed here"

    .. container:: layer incremental

        .. csv-table::
            :class:  overflow-y-scroll
            :header: "Content Type", High probability for long-term preservation, Medium probability for long-term preservation, Low probability for long-term preservation

            Computer Programs,"
            .. class:: incremental 
            
            • Computer program source code, uncompiled (\*.c, \*.c++, \*.java, \*.js, \*.jsp, \*.php, \*.pl, etc.)", ,"
            .. class:: incremental 
            
            • Compiled / Executable files (EXE, \*.class, COM, DLL, BIN, DRV, OVL, SYS, PIF)"

    .. container:: layer incremental

        .. csv-table::
            :class:  overflow-y-scroll
            :header: "Content Type", High probability for long-term preservation, Medium probability for long-term preservation, Low probability for long-term preservation

            Presentation, , "
            .. class:: incremental 
            
            • OpenOffice (\*.sxi/\*.odp)
            • OOXML (ISO/IEC DIS 29500) (\*.pptx)", "
            .. class:: incremental 
            
            • PowerPoint (\*.ppt)
            • All other presentation formats not listed here"

.. container:: minor far-smaller margin-top-1em
    
    `Recommended File Formats for Long-Term Data Curation - Georgia Southern University | University Libraries <https://georgiasouthern.libguides.com/c.php?g=833713&p=5953146>`__




Dateiformate und ihre Eignung für die Langzeitarchivierung
----------------------------------------------------------------

.. epigraph::

    .. container:: larger

        **SPASSPROJEKT: Entwicklerin erstellt PDF-Dokument in der Größe der Welt**
        
        Sind PDFs in ihren Ausmaßen in der Größe limitiert? Eine Frau wollte es genau wissen und erstellte ein Dokument, das größer ist als Deutschland.

    PDF-Enthusiastin Alex Chan hat ein Experiment durchgeführt, um ein extrem großes PDF-Dokument zu erstellen – lediglich, um zu sehen, ob es möglich ist. Mit ihrem Wissen über das PDF-Dateiformat machte sich Chan daran, ein PDF-Dokument zu erstellen, das größer ist als die Bundesrepublik Deutschland. [...]

    Sie lädt andere Dateiformat-Enthusiasten ein, mit ihr die Möglichkeiten jenseits der dokumentierten Spezifikationen zu erforschen.

    -- `Andreas Donath 3. Februar 2024, 14:21 Uhr <https://www.golem.de/news/spassprojekt-mann-erstellt-pdf-dokument-in-der-groesse-der-welt-2402-181844.html>`__



E-Mail-Archivierung - eine besondere Herausforderung
------------------------------------------------------------

.. container::
    
    Geschäftliche E-Mails sind Dokumente und müssen mit der gleichen Sorgfalt (revisionssicher) archiviert werden wie andere Dokumente.

.. container:: incremental

    Herausforderungen:

    .. class:: incremental list-with-explanations

    - nicht alle E-Mails sind (geschäftlich) relevant (z. B. private E-Mails oder Spam)
  
      (Wer entscheidet, was relevant ist? Dezentral die Mitarbeiter oder zentral die IT?)
    - manche E-Mails sind sehr komplex (z. B. Anhänge)
    - E-Mails enthalten links auf flüchtige Dokumente (z. B. Webseiten)
    - E-Mails enthalten Schadsoftware 
      
      (Diesbezüglich gilt das Fernmeldegeheimnis seit dem 1.12.2021 nicht mehr.)


.. supplemental::

    Aufgrund rechtlicher Rahmenbedingungen wird im Allgemeinen die zentrale Archivierung von E-Mails bevorzugt.



Pflege des Systems
-----------------------------------------------

Regelmäßiges Umkopieren der Bestände ist häufig notwendig:

• Datensätze müssen gelöscht werden (Datenschutz)
• Dokumente und Mappen sollen zusammengefasst werden (Performance)
• Datenträger altern (Sicherheit)

.. admonition:: Hinweis
  :class: warning incremental margin-top-2em

  Kann mit einer Migration auf andere Formate bzw. Formatversionen verbunden sein! 



Sichere Speicherung
-----------------------------------------------

.. class:: incremental

- Dokumente und Metadaten können grundsätzlich folgendermaßen gespeichert werden:

  - zentrale Speicherung
  - dezentrale, verteilte Speicherung
  - dezentrale Speicherung mit Replikation
  
  Daraus ergeben sich Unterschiede in Hinblick auf:

  - Administration
  - Transferzeiten (Latenz)
  - Speicherplatz
- Dokumente und die Metadaten können, müssen aber nicht gemeinsam gespeichert werden.

.. supplemental::

    Die Frage ob die Metadaten oder Dokumente zentral oder dezentral gespeichert werden, stellt sich insbesondere bei Behörden und großen Unternehmen, die über mehrere Standorte verfügen.



.. class:: center-child-elements no-title transition-scale

Sichere Speicherung kann nicht lokal erfolgen
-----------------------------------------------

.. admonition:: Hinweis
    :class: warning

    Das Speichern auf lokalen Netzlaufwerken oder gar auf dem Arbeitsplatzrechner ist sowohl aus betrieblicher (und ggf. auch rechtlicher Sicht) nicht ausreichend.



Drucken von Dokumenten
-----------------------------------------------

DM-Systeme müssen das Drucken von Dokumenten unterstützen, da Nutzer oft Ausdrucke benötigen (z. B. auf Baustellen)

Relevante Anforderungen an den Ausdruck:

.. class:: incremental

- eindeutige Dokumentenkennungen müssen mit ausgedruckt werden
- relevante Dokumentenmerkmale (z. B. Autoren, Version, Status) sollten auf den Ausdrucken erscheinen
- ggf. automatisches Paginieren bei mehrseitigen Dokumenten; insbesondere wenn Signaturnachweise gefordert und mitgedruckt werden sollen



.. class:: new-section  

Integration
-----------------------------------------------


DMS als Infrastruktur
-----------------------------------------------

- DMS kann nie sinnvoll für sich allein stehen

.. class:: incremental

- DMS bietet Dienste für andere Applikationen an, um Dokumente zu verwalten.
- Integration in:
 
  .. class:: incremental

  - Standardsysteme (wie MS-Office)
  - Mail-Systeme
  - ERP- / CRM- / PLM-Systeme und andere 
  - Suchmaschinen
  - Workflow
  - Scanning
  - ...
  
- Referenzen auf Dokumente außerhalb des DMS


Typische (Web-) Dienste eines DMS
-----------------------------------------------

.. container:: two-columns

    .. container:: column no-separator
                
        • Create document 
        • Move document
        • Copy document
        • Delete document 
        • Search document 
        • Search full text
        • Retrieve full document info 
        • ...

    .. container:: column

        • Create folder 
        • Move folder
        • Copy folder
        • Delete folder 
        • Search folder 
        • ...



Beispiel: Anlagendokumentation
-----------------------------------------------

.. image:: drawings/dokumente/anlagendokumentation.svg
    :width: 100%
    :align: center

.. container:: supplemental

    Redlining bzw. die Redlining-Funktion, oder auch Rotstiftfunktion, bezeichnet das Markieren und Ändern von elektronischen Dokumenten oder Zeichnungen zu Feedback-Zwecken. Anmerkungen und Änderungen sind ersichtlich, ohne dass die Originaldatei verändert wird. 
    
    (Vgl. https://www.fme.de/blog/redlining-spezielle-anwendungsfaelle-auf-basis-von-opentext-documentum-for-life-sciences-teil-5/)



Beispiel: Anlagendokumentation
-----------------------------------------------

.. class:: incremental

• Integrierte und konsistente Gesamtsicht auf alle Anlagen und ihre Bestandteile (Pläne und sonstige Dokumente)
• SAP als führendes System für Schlüssel (Datenqualität)
• Aktuelle Version der Dokumente überall und sofort verfügbar (Web)
• Revisionssichere Prozesse für Bereitstellung und Freigabe der Prozesse
• Eine verbindliche Quelle für alle Dokumente und alle Nutzer
• DMS wird für weitere Bereiche genutzt (z. B. SAP-Eingangs- und Ausgangsrechnungen, Magazin, E-Mail-Archivierung).



Typische Fragestellungen bei Integration
--------------------------------------------

.. class:: incremental

• Welches System ist bezüglich der Schlüssel das führende System?
• Einheitliche Nutzer- und Zugriffsrechte
• Wie werden die Systeme synchronisiert (permanent-online oder zyklisch im Batch)?
• Schnittstellenrealisierung
• …


.. class:: no-title

Zusammenfassung: Dokumenten-Management-Lösung
-----------------------------------------------

.. image:: drawings/dokumente/dokumenten-management-loesung.svg
    :height: 1150px
    :align: center



.. class:: new-section transition-move-left

Produkte 
----------


Kategorisierung von DMS Produkten
-----------------------------------------------

.. class:: incremental list-with-explanations

- nach Funktionsbreite
- nach Funktionstiefe 
  
  Zum Beispiel in Hinblick auf unterstützte Dateiformate, unterstützte Barcode-Typen, ...
- nach Skalierung

  In Hinblick auf die Anzahl der Dokumente, die Anzahl der gleichzeitigen Nutzer, die Anzahl der Standorte, ...

- nach Unterstützung spezifischer Kontextabhängiger Anforderungen

  Bei internationalen Unternehmen ist ggf. die Unterstützung von mehreren Sprachen notwendig bzw. die Einhaltung verschiedener rechtlicher Rahmenbedingungen. Weiterhin können besondere Schnittstellen zu anderen Systemen erforderlich sein.



Enterprise Content Management (ECM)
-----------------------------------------------

- Lösungen, die über das reine Dokumentenmanagement hinausgehen und auch Content-Management oder Records-Management (:ger:`Schriftgutverwaltung`) unterstützen.
- Häufig aus Content-Management-Systemen (CMS) hervorgegangen.
- unterstützt auch schwach- oder unstrukturierte Daten (z. B. E-Mails, Webseiten, ...)


.. supplemental::

    Beim Records-Management (Schriftgutverwaltung) ist die Betrachtung des gesamten Lebenszyklus von Akten in Hinblick auf einen sachlichen Kontext bzw. Geschäftsvorfall (Transaktion) und die Dokumentation desselbigen im Fokus.
    
    Records-Management erstreckt sich ggf. auch auf Papierdokumente.

    Records-Management bedingen den Einsatz von DMS und Workflow-Systemen.



Enterprise Content Management (ECM) im AIIM Modell\ [#]_
-----------------------------------------------------------

.. image:: drawings/ecm.svg
    :height: 820px
    :align: center


.. container:: footer-left far-smaller

    .. [#] `Association for Intelligent Information Management (AIIM) <https://www.aiim.org>`_ 


Open-Source Lösungen (Stand 2024)
----------------------------------

- `Agorum Core Open <https://www.agorum.com/agorum-core-open-und-pro-im-vergleich/>`__
- `LogicalDOC Open Source DMS <https://www.logicaldoc.com/download-logicaldoc-community>`__
- `Bitfarm Archiv <https://www.bitfarm-archiv.de/dokumentenmanagement/kosten-lizenzierung.html>`__



Open-Source Lösungen bieten meist grundlegende Funktionalität 
---------------------------------------------------------------

.. epigraph::

    Für bitfarm-Archiv Enterprise sind zumindest die Serverinstallation und eine Schulung des / der IT-Betreuer durch uns oder einen unserer  Dokumentenmagementsystem Partner notwendig. **So können wir dafür garantieren, dass das System funktioniert und den technischen Teil der zum Nachweis der Revisionssicherheit erforderlichen Verfahrensdokumentation übernehmen.**

    Gerade in kleineren Unternehmen mit übersichtlichen Anforderungen und knapper Kostenkalkulation kann das DMS anschließend in Eigenleistung konfiguriert werden. Tauchen dabei Fragen oder Probleme auf, stehen Ihnen unsere Supportmitarbeiter zur Seite. [...]Inklusive professionellem Support für ein Jahr sowie Installation, aller Module & Plugins, ist die Enterprise Version schon ab etwa 2500,- € erhältlich.