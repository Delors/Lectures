.. meta:: 
    :author: Michael Eichberg
    :keywords: "TCP"
    :description lang=de: Verteilte Systeme
    :id: lecture-tcp
    :first-slide: last-viewed

.. |date| date::
.. |at| unicode:: 0x40

.. role:: incremental   
.. role:: eng
.. role:: ger
.. role:: red
.. role:: green
.. role:: blue
.. role:: minor
.. role:: ger-quote
.. role:: obsolete
.. role:: line-above
.. role:: huge
.. role:: xxl

.. role:: raw-html(raw)
   :format: html


CVSS, CVE und OWASP
=====================================================

:Dozent: **Prof. Dr. Michael Eichberg**
:Kontakt: michael.eichberg@dhbw-mannheim.de
:Version: |date|


.. class:: new-section transition-fade

CVSS 
----------------


.. class:: center-elements-on-slide

\
---------

.. container:: foundations

    Das `Common Vulnerability Scoring System (CVSS 4.0) <https://www.first.org/cvss/v4.0/specification-document>`__ stellt einen Rahmen bereit für die Beschreibung und Bewertung des Schweregrads von Software-/Hardware-/Firmwareschwachstellen.

    Die Bewertung der Basiskennzahlen ergibt eine Punktzahl zwischen 0,0 und 10,0. Wobei 0 bedeuted, dass die Schwachstelle (bisher) harmlos ist und 10,0 bedeutet, dass die Schwachstelle sehr gefährlich ist.


CVSS umfasst vier Gruppen von Metriken
----------------------------------------

.. class:: incremental li-margin-top-0-75em

1) Basis-Metriken (:eng:`Base Metrics`) erfassen die inhärenten Eigenschaften einer Schwachstelle, die sich nicht ändern, wenn sich die Umgebung ändert.
2) Bedrohungs-Metriken (:eng:`Threat Metric Group`) spiegelt die Merkmale einer Schwachstelle wieder, die sich im Laufe der Zeit verändern.
3) Umgebungs-Metriken (:eng:`Environmental Metric Group`) erfassen die Eigenschaften einer Schwachstelle, die sich ändern, wenn sich die Umgebung ändert.
4) Ergänzende-Metriken (:eng:`Supplemental`) liefern zusätzliche Informationen, die für die Bewertung einer Schwachstelle nützlich sein können, aber den Schweregrad nicht direkt beeinflussen.


CVSS - Basis-Metriken (:eng:`Base Metric Group`)
------------------------------------------------------------

.. container:: two-columns scriptsize

    .. container:: column

        **Bewertung der Ausnutzbarkeit** (:eng:`Exploitability Metrics`)

        .. class:: incremental impressive

        - Angriffsvektor (:eng:`Attack Vector`)
        - Angriffskomplexität (:eng:`Attack Complexity`)
        - Angriffsanforderungen (:eng:`Attack Requirements`)
        - Benötigte Privilegien (:eng:`Privileges Required`)
        - Erforderliche Benutzerinteraktion (:eng:`User Interaction`)

    .. container:: column incrementalr

        **Bewertung der Auswirkungen** (:eng:`Impact Metrics`)

        .. container:: incremental

            *bzgl. des betroffenen Systems* (:eng:`Vulnerable System`)

            .. class:: incremental impressive

            - Vertraulichkeit  (:eng:`Confidentiality Impact`)
            - Integrität (:eng:`Integrity Impact`)
            - Verfügbarkeit (:eng:`Availability Impact`)
        
        .. container:: incremental 

            *bzgl. nachgelagerter Systeme* (:eng:`Subsequent System`)

            .. class:: incremental impressive
                
            - Vertraulichkeit (:eng:`Confidentiality Impact`)
            - Integrität (:eng:`Integrity Impact`)
            - Verfügbarkeit (:eng:`Availability Impact`)



CVSS Bedrohungs-Metriken (:eng:`Threat Metric Group`) [#]_
--------------------------------------------------------------

.. container::  scriptsize
    
        .. class:: impressive

        - Reifegrad des Exploits (:eng:`Exploit Maturity`)
        
.. [#] Die Namen und der Gruppenzuschnitt (hier: :eng:`Temporal Metric Group`) waren unter CVSS 3.0 anders: `CVSS 3.0 <https://www.first.org/cvss/v3-0/specification-document>`__


.. container:: supplemental

    Gibt es bisher nur die Beschreibung der Schwachstelle oder gibt es bereits einen Proof-of-Concept (PoC) Exploit?


CVSS Umgebungs-Metriken 
---------------------------------------------------------------

.. container:: scriptsize two-columns

    .. container:: column tiny

        **Angepasste Basis-Metriken** (:eng:`Modified Base Metrics`)

            .. class:: impressive

            - Angriffsvektor (:eng:`Attack Vector`)
    
              Angriffskomplexität (:eng:`Attack Complexity`)

              Angriffsanforderungen (:eng:`Attack Requirements`)

              Benötigte Privilegien (:eng:`Privileges Required`)

              Erforderliche Benutzerinteraktion (:eng:`User Interaction`)

            bzgl. des betroffenen Systems **und** auch der nachgelagerten Systeme:

            .. class:: impressive

            - Vertraulichkeitsverlust   (:eng:`Confidentiality Impact`)
            
              Integritätsverlust (:eng:`Integrity Impact`)

              Verfügbarkeitsverlust (:eng:`Availability Impact`)


    .. container:: column
    
        .. class:: impressive

            - Vertraulichkeitsanforderungen (:eng:`Confidentiality Requirement`)
            
            - Integritätsanforderungen (:eng:`Integrity Requirement`)

            - Verfügbarkeitsanforderungen (:eng:`Availability Requirement`)



CVSS - Bewertung der Ausnutzbarkeit/Exploitability Metrics
------------------------------------------------------------

:Attack Vector (AV): Network, Adjacent, Local, Physical

:Attack Complexity (AC): Low, High

:Attack Requirements (AT): None, Present

:Privileges Required (PR): None, Low, High

:User Interaction (UI): None, Passive, Active


.. container:: supplemental

    **Attack Vector**

    *Network*

    Schwachstellen, die häufig "aus der Ferne ausnutzbar" sind und als ein Angriff betrachtet werden können, der auf Protokollebene über einen oder mehrere Netzknoten hinweg (z. B. über einen oder mehrere Router) ausgenutzt werden kann.

    *Adjacent*

    Der Angriff ist auf eine logisch benachbarte Topologie beschränkt. Dies kann z.B.  bedeuten, dass ein Angriff aus demselben gemeinsamen Nahbereich (z. B. Bluetooth, NFC oder IEEE 802.11) oder logischen Netz (z. B. lokales IP-Subnetz) gestartet werden muss.

    *Local*

    Der Angreifer nutzt die Schwachstelle aus, indem er lokal auf das Zielsystem zugreift (z. B. Tastatur, Konsole) oder über eine Terminalemulation (z. B. SSH); oder der Angreifer verlässt sich auf die Interaktion des Benutzers, um die zum Ausnutzen der Schwachstelle erforderlichen Aktionen durchzuführen (z. B. mithilfe von Social-Engineering-Techniken, um einen legitimen Benutzer zum Öffnen eines bösartigen Dokuments zu verleiten).

    *Physical*

    Der Angreifer muss physisch Zugriff auf das Zielsystem haben, um die Schwachstelle auszunutzen.

    **Attack Complexity**

    Wie aufwendig ist es explizite Schutzmaßnahmen ((K)ASLR, Stack Canaries, ...) zu umgehen. Wie wahrscheinlich ist es, dass ein Angriff erfolgreich ist. Im Falle von :eng:`Race Conditions` können ggf. sehr viele Ausführungen notwendig sein bevor die Race Condition erfüllt ist.

    **Attack Requirements**

    Welcher Vorbedingungen (unabhängig von den expliziten Sicherungsmaßnahmen) müssen erfüllt sein, damit die Schwachstelle ausgenutzt werden kann. (z.B. der Nutzer muss sich an seinem Smartphone mindestens einmal seit dem Boot angemeldet haben (After-First-Use vs. Before-First-Use.))

    **Privileges Required**

    Welche Privilegien muss der Angreifer mindestens haben, um die Schwachstelle auszunutzen (Sind Adminstratorrechte erforderlich oder reichen normale Benutzerrechte).

    **User Interaction**
    
    Passiv bedeuted hier, dass der Nutzer unfreiwillig die Schwachstelle ausnutzt ohne bewusst Schutzmechanismen zu unterlaufen. Aktiv bedeuted, dass der Nutzer aktiv Interaktionen unternimmt, um die Schutzmechanismen des Systems ausnutzen (z.B. durch das Installieren einer nicht-signierten Anwendung aus dem Internet).



CVSS - Bewertung der Auswirkung auf das betroffene System/Vulnerable System Impact Metrics
--------------------------------------------------------------------------------------------

:Confidentiality Impact (C): None, Low, High
:Integrity Impact (I): None, Low, High
:Availability Impact (A): None, Low, High



CVSS - Bewertung der Auswirkung auf das nachgelagerte System/Vulnerable System Impact Metrics
-----------------------------------------------------------------------------------------------

:Confidentiality Impact (C): None, Low, High
:Integrity Impact (I): None, Low, High
:Availability Impact (A): None, Low, High









.. class:: integrated-exercise transition-move-left smaller

Übung: Schwachstellen und Ihre Bewertung (1)
---------------------------------------------------------------

**Szenario** 

Ihnen liegt eine externe Festplatte vor, die Hardwareverschlüsselung unterstützt. D.h. wenn diese Festplatte an einen Computer angeschlossen wird, dann muss ein Passwort eingegeben werden, bevor auf die Daten zugegriffen werden kann. Dieses entsperren der Festplatte geschieht mit Hilfe eines speziellen Programms, dass ggf. vorher installiert werden muss. Die Festplatte ist mit AES-256-XTX verschlüsselt. 
  
Das Clientprogramm hasht erst das Passwort bevor es den Hash an den Controller der Festplatte überträgt. Die Firmware des Controller validiert das Passwort in dem es den gesendeten Hash direkt mit dem bei der Einrichtung übermittelten Hash vergleicht; d.h. es finden keine weiteren sicherheitsrelevanten Operationen außer dem direkten Vergleich statt. Zum Entsperren der Festplatte ist es demzufolge ausreichend den Hash aus der Hardware auszulesen und diesen an den Controller zu senden, um diese zu entsperren. Danach kann auf die Daten frei zugegriffen werden. 

1. Ermitteln Sie den `CVSS 4.0 Score <https://www.first.org/cvss/v4-0/>`__ für diese Schwachstelle. (`CVSS Rechner <https://www.first.org/cvss/calculator/4.0>`__)
2. Welche Anwendungsfälle sind für diese Schwachstelle denkbar?


.. Lösung:
   (ACHTUNG: Diskussionsbedarf!!!)
   CVSS:4.0/AV:P/AC:H/AT:N/PR:N/UI:N/VC:H/VI:H/VA:N/SC:N/SI:N/SA:N
   CVSS v4.0 Score: 5.3 / Medium

.. class:: integrated-exercise transition-move-left  smaller

Übung: Schwachstellen und Ihre Bewertung (2)
---------------------------------------------------------------

**Szenario** 

Durch die Analyse der Firmware eines Basebands haben Sie folgende Erkenntnisse erhalten: Wenn es Ihnen gelingt ein speziell manipuliertes Paket - welches außerhalb der Spezifikation liegt -  an das Baseband zu senden, dann kommt es zu einem Buffer-Overflow. Mit Hilfe dieses Buffer-Overflows ist es dann möglich das Baseband zum Absturz zu bringen, welches daraufhin direkt selbständig neu startet. Aufgrund des Neustarts muss der Nutzer dann jedoch seine SIM-Pin neu eingeben, um sich wieder gegenüber dem Mobilfunknetz zu authentifizieren. 

Intensive weitere Untersuchungen haben ergeben, dass es nicht möglich ist den Buffer-Overflow weitergehend auszunutzen, um zum Beispiel Daten des Smartphones abzugreifen, da die Validierung der Kommunikation mit dem Hauptprozessor effektiv ist. In einem Labortest wurden die Erkenntnisse validiert. Es war möglich ein entsprechendes Paket erfolgreich an ein Baseband zu senden und dadurch ein Neustart des Basebands zu erzwingen.

1. Ermitteln Sie den `CVSS 4.0 Score <https://www.first.org/cvss/v4-0/>`__ für diese Schwachstelle. (`CVSS Rechner <https://www.first.org/cvss/calculator/4.0>`__)
2. Welche Anwendungsfälle sind für diese Schwachstelle denkbar?

.. container:: supplemental 

    .. container:: black

        **Baseband**

        Der Baseband Chip Ihres Smartphones ist für die Kommunikation mit dem Mobilfunknetz zuständig. Als solcher hat das Baseband eine eigene Firmware, die von dem Hersteller des Basebands stammt. Die Kommunikation zwischen dem Baseband und dem Hauptprozessor erfolgt über eine wohl definierte, minimal gehaltene Schnittstelle, um die Auswirkungen von Sicherheitsproblemen ggf. eindämmen zu können.

.. Lösung:
   (ACHTUNG: Diskussionsbedarf!!!)
   CVSS:4.0/AV:A/AC:L/AT:N/PR:N/UI:N/VC:N/VI:N/VA:H/SC:N/SI:N/SA:N
   CVSS v4.0 Score: 7.1 / High ⊕





Common Vulnerabilities and Exposures (`CVE <https://cve.org/>`__)
------------------------------------------------------------------

.. epigraph:: CVE definiert eine Sicherheitslücke als:

    "Eine Schwachstelle in der Berechnungslogik (z. B. Code), die in Software- und Hardwarekomponenten gefunden wird und die, wenn sie ausgenutzt wird, zu einer negativen Auswirkung auf die Vertraulichkeit, Integrität oder Verfügbarkeit führt. Die Behebung der Schwachstellen in diesem Zusammenhang umfasst in der Regel Änderungen am Code, kann aber auch Änderungen an der Spezifikation oder sogar die Ablehnung der Spezifikation (z. B. die vollständige Entfernung der betroffenen Protokolle oder Funktionen) beinhalten."

    -- https://nvd.nist.gov/vuln (Übersetzt mit DeepL)


Zweck von CVEs
------------------

.. class:: incremental

- Schwachstellen eindeutig identifizieren und bestimmten Versionen eines Codes (z. B. Software und gemeinsam genutzte Bibliotheken) mit diesen Schwachstellen verknüpfen. 
- Kommunikationsgrundlage bilden, damit mehrere Parteien über eine eindeutig identifizierte Sicherheitslücke diskutieren können.
- Es können generell n-Day und 0-Day Schwachstellen unterschieden werden.


.. class:: smaller

Beschreibung eines `CVEs <https://github.com/CVEProject/cvelistV5>`__
----------------------------------------------------------------------

Jeder CVE ist mit Hilfe eines wohldefinierten JSON-Formats beschrieben. Gekürztes Beispiel

.. code:: json
    :class: footnotesize

    { "dataVersion": "5.0",
      "cveMetadata": {
          "cveId": "CVE-2023-51034",
          "assignerOrgId": "8254265b-2729-46b6-b9e3-3dfca2d5bfca",
          "assignerShortName": "mitre",
          "datePublished": "2023-12-22T00:00:00"
      },
      "containers": { "cna": { ...,
            "descriptions": [ {
               "value": "TOTOlink [...] vulnerable to command execution [...]"
            } ], ...,
            "references": [{
               "url": "815yang.github.io/[...]totolink_UploadFirmwareFile/"
              } ], ...
    } } }




Common Weakness Enumeration (`CWE <https://cwe.mitre.org/>`__)
----------------------------------------------------------------

- eine kollaborativ entwickelte, vollständig durchsuchbare, kategorisierte Liste von Typen von Software- und Hardware-Schwachstellen und deren Beschreibung, dient als:
  
  .. class:: incremental

  - gemeinsame Sprache, 
  - Messlatte für Sicherheitstools,
  - als Grundlage für die Identifizierung von Schwachstellen sowie für Maßnahmen zur Abschwächung und Prävention.


CWE - Schwachstellenkatalog `TOP 8 in 2023 <https://cwe.mitre.org/top25/archive/2023/2023_top25_list.html#tableView>`__
--------------------------------------------------------------------------------------------------------------------------------------

.. csv-table::
    :class: small highlight-line-on-hover
    :header:     Rank , ID , Name, Rank Change vs. 2022
    :widths: 7, 12, 63, 18
    
    1 , CWE-787 , Out-of-bounds Write  , 0
    2 , CWE-79 , Improper Neutralization of Input During Web Page Generation ('Cross-site Scripting') , 0
    3 , CWE-89 , Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection') , 0
    4 , CWE-416 , Use After Free , +3
    5 , CWE-78 , Improper Neutralization of Special Elements used in an OS Command ('OS Command Injection') , +1
    6 , CWE-20 , Improper Input Validation , -2
    7 , CWE-125 , Out-of-bounds Read , -2
    8 , CWE-22 , Improper Limitation of a Pathname to a Restricted Directory ('Path Traversal') , 0

CWE - Schwachstellenkatalog `TOP 9-16 in 2023 <https://cwe.mitre.org/top25/archive/2023/2023_top25_list.html#tableView>`__
--------------------------------------------------------------------------------------------------------------------------------------

.. csv-table::
    :class: small highlight-line-on-hover
    :header:     Rank , ID , Name, Rank Change vs. 2022
    :widths: 7, 12, 63, 18
    
    9 , CWE-352 , Cross-Site Request Forgery (CSRF) , 0
    10 , CWE-434 , Unrestricted Upload of File with Dangerous Type , 0
    11 , CWE-862 , Missing Authorization ,  +5
    12 , CWE-476 , NULL Pointer Dereference , -1
    13 , CWE-287 , Improper Authentication , +1
    14 , CWE-190 , Integer Overflow or Wraparound , -1
    15 , CWE-502 , Deserialization of Untrusted Data , -3
    16 , CWE-77 , Improper Neutralization of Special Elements used in a Command ('Command Injection') , +1


CWE - Schwachstellenkatalog `TOP 17-25 in 2023 <https://cwe.mitre.org/top25/archive/2023/2023_top25_list.html#tableView>`__
--------------------------------------------------------------------------------------------------------------------------------------

.. csv-table::
    :class: small highlight-line-on-hover
    :header:     Rank , ID , Name, Rank Change vs. 2022
    :widths: 7, 12, 63, 18

    17 , CWE-119 , Improper Restriction of Operations within the Bounds of a Memory Buffer , +2
    18 , CWE-798 , Use of Hard-coded Credentials , -3
    19 , CWE-918 , Server-Side Request Forgery (SSRF) , +2
    20 , CWE-306 , Missing Authentication for Critical Function , -2
    21 , CWE-362 , Concurrent Execution using Shared Resource with Improper Synchronization ('Race Condition') , +1
    22 , CWE-269 , Improper Privilege Management , +7
    23 , CWE-94 , Improper Control of Generation of Code ('Code Injection') , +2
    24 , CWE-863 , Incorrect Authorization ,  +4
    25 , CWE-276 , Incorrect Default Permissions , -5




National Vulnerability Database (`NVDs <https://nvd.nist.gov/>`__)
---------------------------------------------------------------------

.. class:: incremental

- Auflistung aller CVEs und deren Bewertung
- Alle Schwachstellen in der NVD wurden sind einer CVE-Kennung versehen 
- Die NVD ist ein Produkt der NIST Computer Security Division, Information Technology Laboratory
- Verlinkt häufig weiterführend Seiten, die Lösungshinweise und Tools bereitstellen, um die Schwachstelle zu beheben;
- Verweist auf entsprechende `CWEs <https://cwe.mitre.org/>`__
- Verlinkt gelegentlich *PoC* Exploits (:eng:`Proof-of-Concept Exploits`)


Beispiel eines CVEs für eine *XSS Schwachstelle*
------------------------------------------------

.. epigraph:: CVE-2023-50712

   Iris is a web collaborative platform aiming to help incident responders sharing technical details during investigations. A stored Cross-Site Scripting (XSS) vulnerability has been identified in iris-web, affecting multiple locations in versions prior to v2.3.7. The vulnerability may allow an attacker to inject malicious scripts into the application, which could then be executed when a user visits the affected locations. This could lead to unauthorized access, data theft, or other related malicious activities. An attacker need to be authenticated on the application to exploit this vulnerability. The issue is fixed in version v2.3.7 of iris-web. No known workarounds are available.

   -- Published: December 22, 2023

.. container:: footnotesize
    
    **Bewertung**: CVSS V3.1: 5.4 MEDIUM


Beispiel eines CVEs für eine *Arbitrary Code Execution Schwachstelle*
----------------------------------------------------------------------

.. epigraph:: CVE-2023-51034

   TOTOlink EX1200L V9.3.5u.6146_B20201023 is vulnerable to arbitrary command execution via the cstecgi.cgi UploadFirmwareFile interface.

   -- Published: December 22, 2023; Last modified: January 2, 2024

.. container:: footnotesize
    
    :Bewertung: CVSS V3.1: 9.8 Critical
    :PoC Exploit: https://815yang.github.io/2023/12/12/ex1200l/totolink_ex1200L_UploadFirmwareFile/
    :Weakness Enumeration: CWE-434 Unrestricted Upload of File with Dangerous Type

.. container:: supplemental

    Bei TOTOlink EX1200L handelt es sich um einen Wifi Range Expander.



.. class:: smaller

CWE-434 Unrestricted Upload of File with Dangerous Type
--------------------------------------------------------

.. class:: footnotesize

.. epigraph::
    
    Beschreibung:

        Das Produkt ermöglicht es dem Angreifer, Dateien gefährlicher Typen hochzuladen oder zu übertragen, die in der Produktumgebung automatisch verarbeitet werden können.

    Arten der Einführung:

        Diese Schwäche wird durch das Fehlen einer Sicherheitstaktik während der Architektur- und Entwurfsphase verursacht. 

    Scope: Integrität, Vertraulichkeit, Verfügbarkeit

        Willkürliche Codeausführung ist möglich, wenn eine hochgeladene Datei vom Empfänger als Code interpretiert und ausgeführt wird. [...] 

    -- https://cwe.mitre.org/data/definitions/434.html (Übersetzt mit DeepL) 



.. class:: smaller

CVE-2023-51034 - PoC (gekürzt)
-------------------------------------------------

Initiale Anfrage:

.. code:: http

    POST /cgi-bin/cstecgi.cgi HTTP/1.1
    [...]    
    {
        "FileName":";ls../>/www/yf.txt;",
        "topicurl":"UploadFirmwareFile"

    }

.. container:: incremental

    Abfrage der Datei (hier: :code:`yf.txt`):

    .. code:: http

        GET /yf.txt HTTP/1.1
        [...]
        Connection: close

    Das Ergebnis ist die Auflistung der Dateien im Verzeichnis.


.. class:: smaller

CVE-2023-51034 - zugrundeliegende Schwachstelle
-------------------------------------------------

.. code:: C
    :class: scriptsize

    Var = (const char *)websGetVar(a1, "FileName", &byte_42FE28);
    v3 = (const char *)websGetVar(a1, "FullName", &byte_42FE28);
    v4 = (const char *)websGetVar(a1, "ContentLength", &word_42DD4C);
    v5 = websGetVar(a1, "flags", &word_42DD4C);
    v6 = atoi(v5);
    Object = cJSON_CreateObject();
    v8 = fopen("/dev/console", "a");
    v9 = v8;
    if ( v8 )
    {
        fprintf(v8, "[%s:%d] FileName=%s,FullName=%s,ContentLength=%s\n", 
                    "UploadFirmwareFile", 751, Var, v3, v4);
        fclose(v9);
    }
    v10 = strtol(v4, 0, 10) + 1;
    strcpy(v52, "/tmp/myImage.img");
    doSystem("mv %s %s", Var, v52);

.. container:: supplemental

    Die Lücke ist auf die folgenden Zeilen zurückzuführen:

    .. code:: c

        Var = (const char *)websGetVar(a1, “FileName”, &byte_42FE28);
        doSystem(“mv %s %s”, Var, v52);

    Der Aufruf von :code:`doSystem` ermöglicht die Ausführung von beliebigem Code. Der Angreifer kann den Wert von :code:`Var` so manipulieren, dass er quasi beliebigen Code ausführen kann.



.. class:: new-section transition-fade

Diskussion der relevantesten Schwachstellen (CWEs)
-------------------------------------------------------------


.. No 1 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-787: Out-of-bounds Write (Memory Corruption)
--------------------------------------------------------

CWE-787: Out-of-bounds Write
----------------------------

:Description: The product writes data past the end, or before the beginning, of the intended buffer. 
:Languages: C /C++
:Likelihood Of Exploit: High
:Technical Impact: Modify Memory; DoS: Crash, Exit, or Restart; Execute Unauthorized Code or Commands


.. class:: scriptsize

CWE-787: Out-of-bounds Write - Beispiel 1
--------------------------------------------------------


.. code:: c

    int id_sequence[3];

    /* Populate the id array. */

    id_sequence[0] = 123;
    id_sequence[1] = 234;
    id_sequence[2] = 345;
    id_sequence[3] = 456;


.. class:: scriptsize

CWE-787: Out-of-bounds Write - Beispiel 2
--------------------------------------------------------

.. code:: C

    int returnChunkSize(void *) {

        /* if chunk info is valid, return the size of usable memory,

        * else, return -1 to indicate an error

        */
        ...
    }

    int main() {
        ...
        memcpy(destBuf, srcBuf, (returnChunkSize(destBuf)-1));
        ...
    }

.. container:: post-lecture-exercise-solution

    `memcpy` erwartet als dritten Parameter einen :code:`unsigned int`. Wenn :code:`returnChunkSize -1 zurückgibt, dann wird :code:`MAX_INT-1` verwendet.


.. class:: scriptsize

CWE-787: Out-of-bounds Write - Beispiel 3
--------------------------------------------------------

.. code:: C

    void host_lookup(char *user_supplied_addr){
        struct hostent *hp;
        in_addr_t *addr;
        char hostname[64];
        in_addr_t inet_addr(const char *cp);

        /* routine that ensures user_supplied_addr is in the right format for 
           conversion */

        validate_addr_form(user_supplied_addr);
        addr = inet_addr(user_supplied_addr);
        hp = gethostbyaddr( addr, sizeof(struct in_addr), AF_INET);
        strcpy(hostname, hp->h_name);
    }

.. container:: post-lecture-exercise-solution

    - Problem 1: hostname hat nur 64 Bytes, aber der Name des Hosts kann länger sein.
    - Problem 2: `gethostbyaddr` kann NULL zurückgeben, wenn der Host nicht gefunden werden kann. (Null pointer dereference)


.. class:: scriptsize

CWE-787: Out-of-bounds Write - Beispiel 4
--------------------------------------------------------

.. code:: C

    char * copy_input(char *user_supplied_string){
      int i, dst_index;
      char *dst_buf = (char*)malloc(4*sizeof(char) * MAX_SIZE);
      if ( MAX_SIZE <= strlen(user_supplied_string) ) die("string too long");
      dst_index = 0;
      for ( i = 0; i < strlen(user_supplied_string); i++ ){
        if( '&' == user_supplied_string[i] ){
          dst_buf[dst_index++] = '&';
          dst_buf[dst_index++] = 'a';
          dst_buf[dst_index++] = 'm';
          dst_buf[dst_index++] = 'p';
          dst_buf[dst_index++] = ';';
        }
        else if ('<' == user_supplied_string[i] ){ /* encode to &lt; */ }
        else dst_buf[dst_index++] = user_supplied_string[i];
      }
      return dst_buf;
    }

.. container:: post-lecture-exercise-solution

    - Problem: :code:`dst_buf` hat nur :code:`4*sizeof(char) * MAX_SIZE`` Bytes. Wenn der Nutzer einen sehr langen String mit (fast) nur `&` übermittelt, dann wird der Puffer überlaufen, da das Encoding 5 Zeichen benötigt.


.. class:: scriptsize

CWE-787: Out-of-bounds Write - Beispiel 5
--------------------------------------------------------

.. code:: C

    char* trimTrailingWhitespace(char *strMessage, int length) {
      char *retMessage;
      char message[length+1];                    // copy input string to a 
      int index;                                 //      temporary string
      for (index = 0; index < length; index++) { //
        message[index] = strMessage[index];      //
      }                                          //
      message[index] = '\0';                     //
      int len = index-1;                         // trim trailing whitespace
      while (isspace(message[len])) {            //
        message[len] = '\0';                     //
        len--;                                   //
      }                                          //
      
      retMessage = message;
      return retMessage;                         // return trimmed string
    }

.. container:: supplemental

    :isspace: If an argument (character) passed to the isspace() function is a white-space character, it returns non-zero integer. If not, it returns 0.

.. container:: post-lecture-exercise-solution

    - Problem: Zeichenketten, die nur aus Whitespace bestehen, werden nicht korrekt behandelt. In diesem Fall kommt es zu einem Buffer-Underflow (d.h. es wird auf den Speicherbereich vor dem Puffer zugegriffen).
    


.. class:: scriptsize

CWE-787: Out-of-bounds Write - Beispiel 6
--------------------------------------------------------

.. code:: C

    int i;
    unsigned int numWidgets;
    Widget **WidgetList;

    numWidgets = GetUntrustedSizeValue();
    if ((numWidgets == 0) || (numWidgets > MAX_NUM_WIDGETS)) {
      ExitError("Incorrect number of widgets requested!");
    }
    WidgetList = (Widget **)malloc(numWidgets * sizeof(Widget *));
    printf("WidgetList ptr=%p\n", WidgetList);
    for(i=0; i<numWidgets; i++) {
      WidgetList[i] = InitializeWidget();
    }
    WidgetList[numWidgets] = NULL;
    showWidgets(WidgetList);


.. container:: post-lecture-exercise-solution

    - Problem 1: Der Rückgabewert von :code:`malloc` wird nicht überprüft.
    - Problem 2: :code:`WidgetList[numWidgets] = NULL;` schreibt außerhalb des Puffers. (Buffer-Overflow)
    

CWE-787: Out-of-bounds Write - Potential Mitigations
--------------------------------------------------------

.. class:: incremental

- Verwendung einer sicheren Programmiersprache (Java, ...)
- Verwendung von Bibliotheken, die sicherer sind (z.B. :code:`strncpy` statt :code:`strcpy`)
- Kompilierung mit entsprechenden Flags, die entsprechende Prüfung aktivieren (z.B. :code:`-D_FORTIFY_SOURCE=2`)
- Kompilierung als Position-Independent-Code 

  :minor:`Dies löst nicht das Problem, aber es macht es schwerer eine Schwachstelle auszunutzen.`
- Statische Analyse Werkzeuge
- Dynamische Analyse Werkzeuge (z.B. *Fuzzing*, *Fault Injection*, ...)



.. No 2 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-79: Improper Neutralization of Input During Web Page Generation (*Cross-site Scripting* or *XSS*)
----------------------------------------------------------------------------------------------------------

CWE-79: Improper Neutralization of Input During Web Page Generation
---------------------------------------------------------------------

:Short Description: The product does not neutralize or incorrectly neutralizes user-controllable input before it is placed in output that is used as a web page that is served to other users.

:Likelihood Of Exploit: High
:Technical Impact: Modify Memory; DoS: Crash, Exit, or Restart; Execute Unauthorized Code or Commands
:Scope: Access Control, Confidentiality
:Main Kinds: Stored XSS (Typ 2), Reflected XSS (Typ 1), DOM-based XSS (Typ 0)

.. container:: supplemental

    Durch eine XSS Lücke werden häufig Informationen abgegriffen (z.B. Session Cookies). Allerdings ist es ggf. auch möglich, dass der Angreifer die Session des Nutzers übernimmt und sich als dieser ausgibt. 

Stored XSS (Typ 2)
-------------------

.. image:: xss/stored-xss.svg
   :alt: Stored XSS
   :width: 1700px
   :align: center


Reflected XSS (Typ 1)
----------------------

.. image:: xss/reflected-xss.svg
   :alt: Reflected XSS
   :width: 1650px
   :align: center

.. container:: supplemental

    Reflected XSS ist häufig schwerer auszunutzen, da der Angreifer den Nutzer dazu bringen muss, einen Link zu klicken, der den Angriffsvektor enthält. Bei Stored XSS ist dies nicht notwendig, da der Angriffsvektor bereits auf dem Server gespeichert ist.


Dom-based XSS (Typ 0)
----------------------

.. image:: xss/dom-based-xss.svg
   :alt: Dom-based XSS
   :width: 1500px
   :align: center

.. container:: supplemental

    Dom-based-xss ist am schwersten Auszunutzen, da der Angreifer den Nutzer dazu bringen muss den Schadcode in die Informationen einzubringen, die von dem Script verarbeitet werden (z.B. durch das Eingeben in ein Formular).




.. class:: scriptsize

CWE-79: XSS - Beispiel 1 - XSS Typ 1 (Php)
--------------------------------------------------------


.. code:: php

    # Rückgabe einer Willkommensnachricht basierend auf dem 
    # HTTP Get username Parameter
    $username = $_GET['username'];
    echo '<div class="header"> Welcome, ' . $username . '</div>';



.. container:: post-lecture-exercise-solution

    - Problem: der Nutzername kann "beliebig lange" sein und insbesondere beliebigen JavaScript Code enthalten. Beispiel :code:`http://trustedSite.example.com/welcome.php?username=<Script Language="Javascript">alert("You've been attacked!");</Script>`. Komplexerer Code könnte zum Beispiel ein Fakelogin nachbauen und so die Zugangsdaten des Nutzers abgreifen. Entsprechende Links könnten mit Hilfe von Verschleierungstechniken so verschleiert werden, dass der Nutzer nicht bemerkt, dass er auf einen Link mit Schadfunktion klickt.


.. class:: scriptsize

CWE-79: XSS - Beispiel 2 - XSS Typ 2 (JSP)
--------------------------------------------------------

.. code:: jsp

    <%  String eid = request.getParameter("eid");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from emp where id="+eid);
        if (rs != null) {
          rs.next();
          String name = rs.getString("name");
        }
    %>

    Employee Name: <%= name %>

.. container:: post-lecture-exercise-solution

    - Problem: Falls der Nutzer in der Lage war seinen Namen selber zu wählen und beim Anlegen keine ausreichenden Prüfungen stattgefunden haben, ist ggf. ein XSS Angriff möglich. 
    - Weiteres Problem : In dem Beispiel wird der Parameter :code:`eid` nicht validiert. Der Angreifer kann beliebige SQL-Statements ausführen. 


.. class:: scriptsize

CWE-79: XSS - Beispiel 3 - XSS Typ 2 (PHP)
--------------------------------------------------------

.. code:: php

    $username = mysql_real_escape_string($username);
    $fullName = mysql_real_escape_string($fullName);
    $query = sprintf('Insert Into users (username,password) Values ("%s","%s","%s")', 
                     $username, 
                     crypt($password),
                     $fullName) ;
    mysql_query($query);
    ...

.. container:: post-lecture-exercise-solution

    - Problem: Hier wird zwar die Eingabe validiert (mysql_real_escape_string) aber *nur* in Hinblick auf SQL Injections! Der Angreifer kann so einen Nutzer anlegen, der HTML code enthält.



CWE-79: Improper Neutralization of Input During Web Page Generation - Abhilfemaßnahmen und Erkennung
-------------------------------------------------------------------------------------------------------------

.. class:: incremental

- Verwendung von geprüften/sicheren APIs
- Verringerung der Angriffsfläche mit dem Ziel möglichst wenig Daten in Cookies etc. zu speichern.
- Prüfung dass alle Client-seitigen Prüfungen auch Server-seitig vorgenommen werden.
- Prüfe jeden Input.
- Verwendung von HttpOnly Cookies (d.h. Cookies, die nicht über JavaScript ausgelesen werden können)
- Statische Analyse Werkzeuge
- Beherzigen von Best Practices (`XSS Prevention Cheat Sheet <https://cheatsheetseries.owasp.org/cheatsheets/Cross_Site_Scripting_Prevention_Cheat_Sheet.html>`__)



.. No 3 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-89: Improper Neutralization of Special Elements used in an SQL Command (*SQL Injection*)
----------------------------------------------------------------------------------------------

CWE-89: Improper Neutralization of Special Elements used in an SQL Command 
----------------------------------------------------------------------------

:Short Description: The product constructs all or part of an SQL command using externally-influenced input from an upstream component, but it does not neutralize or incorrectly neutralizes special elements that could modify the intended SQL command when it is sent to a downstream component. 

:Likelihood Of Exploit: High
:Technology: Database Server
:Scope: Access Control, Confidentiality, Integrity



.. class:: scriptsize

CWE-89: SQL Injection - Beispiel 1 (MS SQl)
--------------------------------------------------------

.. code:: sql

    SELECT ITEM,PRICE FROM PRODUCT WHERE ITEM_CATEGORY='$user_input' ORDER BY PRICE

.. admonition:: Hintergrund
    :class: margin-top-2em

    MS SQL hat eine eingebaute Funktion, die es erlaubt Shell Befehle auszuführen. Diese Funktion kann auch in einem SQL Statement verwendet werden.


.. container:: post-lecture-exercise-solution   

    - Problem: Sollte der Nutzername :code:`'; exec master..xp_cmdshell 'dir' --` sein, dann wird das entsprechende Kommando ausgeführt.


.. class:: scriptsize

CWE-89: SQL Injection - Beispiel 2 (PHP)
--------------------------------------------------------

.. code:: php

    $id = $_COOKIE["mid"];
    mysql_query("SELECT MessageID, Subject FROM messages WHERE MessageID = '$id'");


.. container:: post-lecture-exercise-solution   

    - Problem: Der Wert von :code:`$id`, welcher aus einem Cookie ausgelesen wird, wird nicht validiert. Auch wenn Cookies nicht trivial von einem Nutzer bzw. Angreifer manipuliert werden können, so ist es doch möglich. Der Angreifer kann so beliebige SQL Statements ausführen. Deswegen gilt: *Alle* Eingaben müssen validiert werden.
    - 

CWE-89: Improper Neutralization of Special Elements used in an SQL Command - Abhilfemaßnahmen und Erkennung
--------------------------------------------------------------------------------------------------------------

.. class:: incremental

- Verwendung von geprüften/sicheren APIs.
- Verwendung von Prepared Statements.
- Datenbank nur mit den notwendigen Rechten betreiben (Principle of Least Privilege)
- Sollte es notwendig sein einen dynamischen SQL Befehl zu erstellen, dann sollten geprüfte Escapefunktionen verwendet werden.
- Statische Analyse Werkzeuge
- ggf. Application-level Firewall einsetzen




.. No 4 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-416: Use After Free (UAF)
----------------------------------------------------------------------------------------------

CWE-416: Use After Free 
----------------------------------------------------------------------------

:Short Description: Referencing memory after it has been freed can cause a program to crash, use unexpected values, or execute code.  

:Likelihood Of Exploit: High
:Languages: C, C++
:Scope: Availability, Confidentiality, Integrity



.. class:: scriptsize

CWE-416: Use After Free - Triviales Beispiel
----------------------------------------------------------------------------

.. code:: C

    char* ptr = (char*)malloc (SIZE);
    if (err) {
      abrt = 1;
      free(ptr);
    }
    ...
    if (abrt) {
      logError("operation aborted before commit", ptr); // Use of ptr after free
    }

.. admonition:: Hinweis
    :class: margin-top-2em

    Ziel ist es im Allgemeinen eine Referenz auf einen interessanten Speicherbereich zu erhalten, der bereits freigegeben wurde und dann den Inhalt dieses Speicherbereichs auszulesen bzw. zu manipulieren, um die nächste Verwendung zu kontrollieren.


.. class:: scriptsize

CWE-416: Use After Free - Beispiel
----------------------------------------------------------------------------

.. container:: two-columns

    .. container:: column

        .. code:: C

            #include <stdlib.h>
            #include <stdio.h>
            #include <string.h>
            #define BUFSIZER1 512
            int main(int argc, char **argv) {
              char *buf1R1, *buf2R1, *buf2R2;
              buf1R1 = (char *) malloc(BUFSIZER1);
              buf2R1 = (char *) malloc(BUFSIZER1);
              printf("buf2R1 -> %p\n",buf2R1); 
              free(buf2R1);
              buf2R2 = (char *) malloc(BUFSIZER1);
              strncpy(buf2R1, argv[1], BUFSIZER1-1);
              printf("[FREED]   %p\n",buf2R1);
              printf("buf2R2 -> %p\n",buf2R2);
              printf("buf2R2  = %s\n",buf2R2);
              free(buf1R1);
              free(buf2R2);
            }

    .. container:: column

        Wird dieses Program bis zum Ende laufen oder abstürzen? 
        
        Welche Ausgabe erzeugt das Programm?

        Ist die Ausgabe bei jedem Lauf gleich?

.. container:: post-lecture-exercise-solution   

    Das Programm wird (immer) bis zum Ende laufen!

    Ausgabe - 1. Lauf:

    .. code:: text

        buf2R1 -> 0xaaaabc1fc4b0
        [FREED]   0xaaaabc1fc4b0
        buf2R2 -> 0xaaaabc1fc4b0
        buf2R2  = Test

    Ausgabe - 2. Lauf:

    .. code:: text

        buf2R1 -> 0xaaaad5de54b0
        [FREED]   0xaaaad5de54b0
        buf2R2 -> 0xaaaad5de54b0
        buf2R2  = Test


    Der Inhalt von :code:`buf2R2` ist :code:`Test`, obwohl dort nie explizit etwas hinkopiert wurde. Die Ausgabe ist bei jedem Lauf anders, da wir Position-Independent-Code haben und der Kernel ASLR verwendet.

    Die Ausgabe wird bei jedem Lauf gleich, wenn man beides explizit unterbindet.

    .. code:: bash
    
        gcc uaf.c -fno-stack-protector -D_FORTIFY_SOURCE=0 -no-pie -fno-pic
        echo 0 | sudo tee /proc/sys/kernel/randomize_va_space
    
        $ ./a.out Test
        buf2R1 -> 0x4214b0
        [FREED]   0x4214b0
        buf2R2 -> 0x4214b0
        buf2R2  = Test
        $ ./a.out Test
        buf2R1 -> 0x4214b0
        [FREED]   0x4214b0
        buf2R2 -> 0x4214b0
        buf2R2  = Test



.. class:: scriptsize

CWE-416: CVE-2006-4997 IP over ATM clip_mkip dereference freed pointer (Linux Kernel)
---------------------------------------------------------------------------------------


.. code:: c

   // clip_mkip (clip.c):
      198 static void clip_push(struct atm_vcc *vcc,struct sk_buff *skb) {
      ...
      234         memset(ATM_SKB(skb), 0, sizeof(struct atm_skb_data));
      235         netif_rx(skb);
      236 }
      ...
      510         clip_push(vcc,skb);
      511         PRIV(skb->dev)->stats.rx_packets--;
      512         PRIV(skb->dev)->stats.rx_bytes -= len;

   // netif_rx (dev.c):
      1392 int netif_rx(struct sk_buff *skb) {
      ...
      1428        kfree_skb(skb);	//drop skb
      1429        return NET_RX_DROP;

.. container:: post-lecture-exercise-solution   

    - Problem: In Zeile 511 wird auf den Speicherbereich von :code:`skb->dev` zugegriffen, obwohl dieser bereits freigegeben wurde in netif_rx in Zeile 1428.


CWE-416: Use After Free - Abhilfemaßnahmen und Erkennung
----------------------------------------------------------------------------

.. class:: incremental

- Wahl einer sicheren Programmiersprache (z.B. RUST)
- explizites :code:`NULL` setzen, nachdem der Speicherbereich freigegeben wurde. 
- Fuzzing
- Statische Analyse Werkzeuge

.. container:: supplemental

    Empfohlene Lektüre: `One day short of a full chain: Real world exploit chains explained <https://github.blog/2021-03-24-real-world-exploit-chains-explained/>`__ (In Teil 1 wird eine UAF Schwachstelle genutzt.)



.. No 5 in CWE Top 2023

.. class:: new-subsection transition-move-to-top
    
CWE-78: Improper Neutralization of Special Elements used in an OS Command (*OS Command Injection*)
----------------------------------------------------------------------------------------------------------


CWE-78: Improper Neutralization of Special Elements used in an OS Command
----------------------------------------------------------------------------

:Short Description: The product constructs all or part of an OS command using externally-influenced input from an upstream component, but it does not neutralize or incorrectly neutralizes special elements that could modify the intended OS command when it is sent to a downstream component.  

:Likelihood Of Exploit: High
:Scope: Availability, Confidentiality, Integrity
:Main Kinds:
    1. Die Anwendung führt ein bestimmtes Program aus und die Daten werden als Parameter übergeben.
    2. Die Anwendung bestimmt basierend auf den Nutzerdaten welches Program mit welchen Parameter ausgeführt werden soll.


.. class:: scriptsize

CWE-78: Improper Neutralization of Special Elements used in an OS Command - Beispiel (Java)
-------------------------------------------------------------------------------------------

.. code:: java

    ...
    String btype = request.getParameter("backuptype");
    String cmd = new String("cmd.exe /K \"
    c:\\util\\rmanDB.bat "
    +btype+
    "&&c:\\utl\\cleanup.bat\"")

    System.Runtime.getRuntime().exec(cmd);
    ...


.. container:: post-lecture-exercise-solution   

    - Problem: Der Wert von :code:`btype` wird nicht validiert und dewegen kann der Angreifer  beliebige Befehle ausführen, da die Shell (:code:`cmd.exe``) mehrere Befehle, die mit :code:`&&` verknüpft sind hintereinander ausführt.


CWE-78: Improper Neutralization of Special Elements used in an OS Command - Abhilfemaßnahmen und Erkennung
--------------------------------------------------------------------------------------------------------------

.. class:: incremental

- Verwendung von geprüften/sicheren APIs.
- Anwendung bzw. Befehl nur mit den notwendigen Rechten betreiben (Principle of Least Privilege) bzw. in einer Sandbox ausführen.
- Statische Analyse Werkzeuge
- Dynammische Analyse in Kombination mit Fuzzing
- Manuelle Code Reviews/Statische Analyse
- ggf. Application-level Firewall einsetzen





.. No 6 in CWE Top 2023

.. class:: new-subsection transition-move-to-top
    
CWE-20: Improper Input Validation
-------------------------------------------


CWE-20: Improper Input Validation
-------------------------------------------


:Short Description: The product receives input or data, but it does not validate or incorrectly validates that the input has the properties that are required to process the data safely and correctly.   

:Likelihood Of Exploit: High
:Scope: Availability, Confidentiality, Integrity
:Application Areas:
    - raw data - strings, numbers, parameters, file contents, etc.
    - metadata - information about the raw data, such as headers or size 


CWE-20: Improper Input Validation - zu verifizierende Werte und Eigenschaften
-------------------------------------------------------------------------------

.. class:: incremental

- Größen wie Größe, Länge, Häufigkeit, Preis, Rate, Anzahl der Vorgänge, Zeit usw.
- implizite oder abgeleitete Größen, wie z. B. die tatsächliche Größe einer Datei anstelle einer angegebenen Größe
- Indizes, Offsets oder Positionen in komplexeren Datenstrukturen
- Schlüssel von Hashtabellen, assoziativen Feldern usw.
- syntaktische Korrektheit - Übereinstimmung mit der erwarteten Syntax
- Bestimmung des tatsächlichen Typs der Eingabe (oder das, was die Eingabe zu sein scheint)
- Konsistenz zwischen den Rohdaten und Metadaten, zwischen Referenzen usw.
- semantische Korrektheit Konformität mit domänenspezifischen Regeln, z. B. Geschäftslogik
- Authentizität von z. B. kryptografischen Signaturen 



.. class:: center-elements-on-slide

\ 
-------------------------------------------------------------------------------

.. admonition:: Improper Input Validation vs. Injection
    
    Ein Name wie ``O'Reily`` stellt ein Problem dar, wenn er in ein SQL Statement eingefügt wird, sollte jedoch von der Anwendung verarbeitet werden können und die Eingabevalidierung passieren.

    Die Validierung muss immer in Hinblick auf den Kontext erfolgen.


.. class:: scriptsize

CWE-20: Improper Input Validation - Beispiel (C)
--------------------------------------------------------


.. code:: c

    #define MAX_DIM 100   
    int m,n, error; /* m,n = board dimensions */
    board_square_t *board;
    printf("Please specify the board height: \n");
    error = scanf("%d", &m);
    if ( EOF == error ) die("No integer passed!\n");
    printf("Please specify the board width: \n");
    error = scanf("%d", &n);
    if ( EOF == error ) die("No integer passed!\n");
    if ( m > MAX_DIM || n > MAX_DIM ) {
      die("Value too large!\n");
    }
    board = (board_square_t*) malloc( m * n * sizeof(board_square_t));
    ...

.. admonition:: Warnung
    :class: incremental

    Ein vergleichbares Problem ist auch in sicheren Programmiersprachen möglich.

.. container:: post-lecture-exercise-solution   

    - Problem: n und m werden nicht vollständig validiert. Sind die Werte negative, dann wird ggf. sehr viel Speicher alloziiert oder das Programm stürzt ab. 

    


CWE-20: Improper Input Validation - Abhilfemaßnahmen und Erkennung
----------------------------------------------------------------------

.. class:: incremental

- (begrenzt) Statische Analyse Werkzeuge
- Manuelle statische Analyse insbesondere in Hinblick auf die zugrundeliegende Semantik
- Dynamische Analyse mit Fuzzing





.. No 7 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-125: Out-of-bounds Read
-------------------------------------------



CWE-125: Out-of-bounds Read
-------------------------------------------


:Short Description: The product reads data past the end, or before the beginning, of the intended buffer. 

:Likelihood Of Exploit: High
:Languages: C, C++
:Scope: Confidentiality
:Impact: Bypass Protection Mechanism; Read Memory

.. container:: supplemental

    Die Ausnutzung dieser Schwachstelle ist häufig schwierig, da nicht immer bekannt ist welche und wieviele Daten gelesen werden können. Es kann allerdings möglich sein Speicheradressen auszulesen. Dies kann ggf. genutzt werden, um Mechanismen wie ASLR zu umgehen.


.. class:: scriptsize

CWE-125: Out-of-bounds Read - Beispiel (C)
--------------------------------------------------------

.. code:: C

    int getValueFromArray(int *array, int len, int index) {

      int value;

      // check that the array index is less than the maximum
      // length of the array
      if (index < len) {
        // get the value at the specified index of the array
        value = array[index];
      }
      // if array index is invalid then output error message
      // and return value indicating error
      else {
        printf("Value is: %d\n", array[index]);
        value = -1;
      }
      return value;
    }


.. container:: post-lecture-exercise-solution   

    - Problem: Der Wert von :code:`index` wird nicht gegen zu kleine Werte validiert. Der Angreifer kann so beliebige Speicherbereiche auslesen.


CWE-125: Out-of-bounds Read - Abhilfemaßnahmen und Erkennung
----------------------------------------------------------------------

.. class:: incremental

- eine sichere Programmiersprache verwenden
- Fuzzing
- Statische Analyse Werkzeuge welche Kontroll- und Datenflussanalyse durchführen