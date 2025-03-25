.. meta::
    :version: renaissance
    :author: Michael Eichberg
    :keywords: "IT Sicherheit", 
    :description lang=de: Grundlagen IT Security - IT Schulung
    :id: schulung-it-security-grundlagen
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

.. role:: raw-html(raw)
   :format: html



.. class:: animated-symbol organic-red

IT Sicherheit - Grundlagen an einem Tag
=====================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.0

.. supplemental::

  :Folien: 
      |html-source|

      |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues



Nachrichten aus der Welt der IT-Sicherheit
---------------------------------------------

.. deck::

    .. card::

        .. epigraph::

            .. rubric:: Paragon Spyware Tool Linked to Canadian Police

            Researchers at the University of Toronto's Citizen Lab in Canada said Ontario Provincial Police appear to have deployed spyware from Israel's Paragon on computers under its control. Spyware victims were Android phone users who were added to a WhatsApp group, where a malicious PDF file was sent to compromise devices via "zero click" intrusion. The researchers said Paragon's Graphite spyware has been linked to users in Australia, Canada, Cyprus, Denmark, Israel, and Singapore.

            -- `19.3.2025 - Bloomberg,  <https://www.bloomberg.com/news/articles/2025-03-19/paragon-spyware-tool-linked-to-canadian-police-watchdog-says?embedded-checkout=true>`__

    .. card::

        .. epigraph::

            .. rubric:: Paragon Spyware Tool mit kanadischer Polizei in Verbindung gebracht

            Forscher des *Citizen Lab* der Universität Toronto in Kanada haben festgestellt, dass die Polizei der Provinz Ontario offenbar Spyware des israelischen Unternehmens Paragon auf den von ihr kontrollierten Computern eingesetzt hat. Bei den Spyware-Opfern handelte es sich um Nutzer von Android-Telefonen, die zu einer WhatsApp-Gruppe hinzugefügt wurden, in der eine bösartige PDF-Datei per „Zero-Click“-Einbruch an kompromittierte Geräte gesendet wurde. Den Forschern zufolge wurde die Graphite-Spyware von Paragon mit Nutzern in Australien, Kanada, Zypern, Dänemark, Israel und Singapur in Verbindung gebracht.

            -- `19.3.2025 - Bloomberg (Übersetzt mit DeepL) <https://www.bloomberg.com/news/articles/2025-03-19/paragon-spyware-tool-linked-to-canadian-police-watchdog-says?embedded-checkout=true>`__

    .. card::

        .. epigraph::

            .. rubric:: CISA Warns of Active Exploitation in GitHub Action Supply Chain Compromise

            The U.S. Cybersecurity and Infrastructure Security Agency (CISA) on Tuesday added a vulnerability linked to the supply chain compromise of the GitHub Action, tj-actions/changed-files, to its Known Exploited Vulnerabilities (KEV) catalog.

            The high-severity flaw, tracked as CVE-2025-30066 (CVSS score: 8.6), involves the breach of the GitHub Action to inject malicious code that enables a remote attacker to access sensitive data via actions logs.

            -- `19.3.2025 - The Hacker News <https://thehackernews.com/2025/03/cisa-warns-of-active-exploitation-in.html>`__


    .. card::

        .. epigraph::

            .. rubric:: CISA warnt vor aktiver Ausnutzung einer Schwachstelle in der Lieferkette von GitHub-Aktion

            Die US-Behörde für Cybersicherheit und Infrastruktursicherheit (CISA) hat am Dienstag eine Schwachstelle im Zusammenhang mit der Kompromittierung der Lieferkette der GitHub-Aktion tj-actions/changed-files in ihren Katalog der bekannten ausgenutzten Schwachstellen (KEV) aufgenommen.

            Die hochgradig gefährliche Schwachstelle, die als CVE-2025-30066 (CVSS-Score: 8.6) verfolgt wird, beinhaltet die Verletzung der GitHub-Action, um bösartigen Code einzuschleusen, der es einem entfernten Angreifer ermöglicht, über Aktionsprotokolle auf sensible Daten zuzugreifen.

            -- `19.3.2025 - The Hacker News (Übersetzt mit DeepL) <https://thehackernews.com/2025/03/cisa-warns-of-active-exploitation-in.html>`__


    .. card::

        .. epigraph::

            .. rubric:: UK cybersecurity agency warns over risk of quantum hackers

            **Organisations including energy and transport firms told to guard systems against powerful new computers**

            Guidance from the U.K.'s National Cyber Security Centre calls on large organizations, critical national infrastructure operators, and companies with bespoke IT systems to implement "post-quantum cryptography" to guard against future quantum hackers. These entities were urged to identify services in need of an upgrade by 2028. The guidance indicated that the most important upgrades should be completed by 2031, with migration to a new encryption system by 2035.

            -- `20.3.2025 - ACM Technews based on a report by The Guardian <https://www.theguardian.com/technology/2025/mar/20/uk-cybersecurity-agency-quantum-hackers>`__

    .. card::

        .. epigraph::
                
            .. rubric:: Britische Cybersicherheitsbehörde warnt vor der Gefahr von Quanten-Hackern

            **Organisationen, darunter Energie- und Transportunternehmen, sollen ihre Systeme gegen leistungsstarke neue Computer schützen**

            In einem Leitfaden des britischen *National Cyber Security Centre* werden große Organisationen, Betreiber kritischer nationaler Infrastrukturen und Unternehmen mit maßgeschneiderten IT-Systemen aufgefordert, „Post-Quantum-Kryptografie“ zu implementieren, um sich gegen künftige Quanten-Hacker zu schützen. Diese Einrichtungen wurden aufgefordert, die Dienste zu identifizieren, die bis 2028 aufgerüstet werden müssen. Der Leitfaden besagt, dass die wichtigsten Upgrades bis 2031 abgeschlossen sein sollten und die Migration auf ein neues Verschlüsselungssystem bis 2035 erfolgen sollte.

            -- `20.3.2025 - ACM Technews based on a report by The Guardian (Übersetzt mit DeepL) <https://www.theguardian.com/technology/2025/mar/20/uk-cybersecurity-agency-quantum-hackers>`__



