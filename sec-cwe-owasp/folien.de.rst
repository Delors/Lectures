.. meta::
    :version: genesis
    :author: Michael Eichberg
    :keywords: "CWE", "OWASP"
    :description lang=de: Verteilte Systeme
    :id: lecture-security-cwe-owasp
    :first-slide: last-viewed
    :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



CWE/OWASP
=====================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de
:Version: 1.1.1

.. supplemental::

  :Folien: 
      [HTML] |html-source|

      [PDF] |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues




.. class:: new-section transition-fade

Relevante Schwachstellen (`CWEs <https://cwe.mitre.org>`__)
-------------------------------------------------------------


.. No 1 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-787: Out-of-bounds Write (Memory Corruption)
--------------------------------------------------------



CWE-787: Out-of-bounds Write
----------------------------

:Beschreibung: Es werden Daten hinter oder vor den Bereich des Puffers geschrieben.
:Programmiersprachen: C /C++
:Wahrscheinlichkeit des Missbrauchs: Hoch
:Technische Auswirkungen: Speichermodifikation; DoS: Crash, Beendigung oder Neustart; Ausführen von nicht autorisiertem Code oder Befehlen



.. class:: far-far-smaller

CWE-787: Out-of-bounds Write - Beispiel 1
--------------------------------------------------------


.. code:: c
    :number-lines:

    int id_sequence[3];

    /* Populate the id array. */

    id_sequence[0] = 123;
    id_sequence[1] = 234;
    id_sequence[2] = 345;
    id_sequence[3] = 456;



.. class:: far-far-smaller

CWE-787: Out-of-bounds Write - Beispiel 2
--------------------------------------------------------

.. exercise::

    .. code:: C
        :number-lines:

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

    .. solution:: Solution
        :pwd: memcpy...

        `memcpy` erwartet als dritten Parameter effektiv einen :code:`unsigned int`. Wenn :code:`returnChunkSize` -1 zurückgibt, dann wird :code:`MAX_INT-1` verwendet.

.. supplemental::

    Signatur der Methode :code:`memcpy`:

    .. code:: C
    
        void *memcpy(void *dest_str, const void * src_str, size_t n)



.. class:: far-far-smaller

CWE-787: Out-of-bounds Write - Beispiel 3
--------------------------------------------------------

.. exercise::

    .. code:: C
        :number-lines:

        void host_lookup(char *user_supplied_addr){
            struct hostent *hp;
            in_addr_t *addr;
            char hostname[64];
            in_addr_t inet_addr(const char *cp); // function prototype

            /* routine that ensures user_supplied_addr is in the right format for 
            conversion */

            validate_addr_form(user_supplied_addr);
            addr = inet_addr(user_supplied_addr);
            hp = gethostbyaddr( addr, sizeof(struct in_addr), AF_INET);
            strcpy(hostname, hp->h_name);
        }

    .. solution:: 
        :pwd: gethostbyaddr

        - Problem 1: ``hostname`` hat nur 64 Bytes, aber der Name des Hosts kann länger sein.
        - Problem 2: ``gethostbyaddr`` kann ``NULL`` zurückgeben, wenn der Host nicht gefunden werden kann. (:eng:`Null-pointer Dereference`)

.. supplemental::

    .. rubric:: Auszug aus der Dokumentation von gethostbyaddr

    .. epigraph::  
    
        **Return Value**

        The :c:`gethostbyaddr()` function return the :c:`hostent` structure or a NULL pointer if an error occurs. 

        -- https://linux.die.net/man/3/gethostbyaddr



.. class:: far-far-smaller

CWE-787: Out-of-bounds Write - Beispiel 4
--------------------------------------------------------

.. exercise::

    .. code:: C
        :number-lines:

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
                else if ( '<' == user_supplied_string[i] ){ /* encode to &lt; */ }
                else dst_buf[dst_index++] = user_supplied_string[i];
            }
            return dst_buf;
        }

    .. solution:: 
        :pwd: dst_buf

        Das Problem ist, dass :code:`dst_buf` nur :code:`4*sizeof(char) * MAX_SIZE` Bytes hat. Wenn der Nutzer einen sehr langen String mit (fast) nur `&` enkodierten Zeichen übermittelt, dann wird der Puffer überlaufen, da das Encoding 5 Zeichen benötigt.



.. class:: far-far-smaller

CWE-787: Out-of-bounds Write - Beispiel 5
--------------------------------------------------------

.. exercise:: 

    .. code:: C
        :number-lines:

        char* trimTrailingWhitespace(char *strMessage, int length) {
            char *retMessage;
            char message[length+1];                    // copy input string to a 
            int index;                                 //      temporary string
            for (index = 0; index < length; index++) { //
                message[index] = strMessage[index];    //
            }                                          //
            message[index] = '\0';                     //

            int len = index-1;                         // trim trailing whitespace
            while (isspace(message[len])) {            //
                message[len] = '\0';                   //
                len--;                                 //
            }                                          //
            
            retMessage = message;
            return retMessage;                         // return trimmed string
        }

    .. solution:: 
        :pwd: Whitespace

        Das Problem ist, dass Zeichenketten, die nur aus Whitespace bestehen, nicht korrekt behandelt werden. In diesem Fall kommt es zu einem Buffer-Underflow (d. h. es wird auf den Speicherbereich vor dem Puffer zugegriffen).


.. supplemental::

    .. rubric:: Auszug aus der Dokumentation von isspace

    .. epigraph::
        
        If an argument (character) passed to the isspace() function is a white-space character, it returns non-zero integer. If not, it returns 0.


.. class:: far-far-smaller

CWE-787: Out-of-bounds Write - Beispiel 6
--------------------------------------------------------

.. exercise::

    .. code:: C
        :number-lines:

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

    .. solution::
        :pwd: malloc!!

        - Problem 1: Der Rückgabewert von :code:`malloc` wird nicht überprüft.
        - Problem 2: :code:`WidgetList[numWidgets] = NULL;` schreibt außerhalb des Puffers. (Buffer-Overflow)
    

CWE-787: Out-of-bounds Write - Mögliche Abhilfemaßnahmen
----------------------------------------------------------

.. class:: incremental

- Verwendung einer sicheren Programmiersprache (Java, Rust ...)
- Verwendung von Bibliotheken, die sicher(er) sind (z. B. :code:`strncpy` statt :code:`strcpy`)
- Kompilierung mit entsprechenden Flags, die entsprechende Prüfung aktivieren (z. B. :code:`-D_FORTIFY_SOURCE=2`)
- Kompilierung als Position-Independent-Code 

  :peripheral:`Dies löst nicht das Problem, aber es macht es schwerer eine Schwachstelle auszunutzen.`
- Statische Analyse Werkzeuge
- Dynamische Analyse Werkzeuge (z. B. *Fuzzing*, *Fault Injection*, ...)



.. No 2 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-79: Improper Neutralization of Input During Web Page Generation (*Cross-site Scripting* or *XSS*)
------------------------------------------------------------------------------------------------------



CWE-79: Improper Neutralization of Input During Web Page Generation
---------------------------------------------------------------------

:Kurzbeschreibung: Nutzereingaben werden nicht oder falsch bereinigt, bevor sie in die Ausgabe eingefügt werden, die als Webseite für andere Benutzer verwendet wird.

.. The product does not neutralize or incorrectly neutralizes user-controllable input before it is placed in output that is used as a web page that is served to other users.

:Wahrscheinlichkeit des Missbrauchs: Hoch
:Technische Auswirkungen: Speichermodifikation; DoS: Crash, Beendigung oder Neustart; Ausführen von nicht autorisiertem Code oder Befehlen
:Betrifft: Zugriffskontrolle, Vertraulichkeit
:Typen: Stored XSS (Typ 2), Reflected XSS (Typ 1), DOM-based XSS (Typ 0)

.. supplemental::

    Durch eine XSS Lücke werden häufig Informationen abgegriffen (z. B. Session Cookies). Allerdings ist es ggf. auch möglich, dass der Angreifer die Session des Nutzers übernimmt und sich als dieser ausgibt. 



Stored XSS (Typ 2)
-------------------

.. image:: images/xss/stored-xss.svg
    :alt: Stored XSS
    :width: 1650px
    :class: icon
    :align: center



Reflected XSS (Typ 1)
----------------------

.. image:: images/xss/reflected-xss.svg
   :alt: Reflected XSS
   :width: 1650px
   :class: icon
   :align: center

.. supplemental::

    Reflected XSS ist häufig schwerer auszunutzen, da der Angreifer den Nutzer dazu bringen muss, einen Link zu klicken, der den Angriffsvektor enthält. Bei Stored XSS ist dies nicht notwendig, da der Angriffsvektor bereits auf dem Server gespeichert ist.



Dom-based XSS (Typ 0)
----------------------

.. image:: images/xss/dom-based-xss.svg
   :alt: Dom-based XSS
   :width: 1500px
   :class: icon
   :align: center

.. supplemental::

    Dom-based XSS ist am schwersten Auszunutzen, da der Angreifer den Nutzer dazu bringen muss den Schadcode in die Informationen einzubringen, die von dem Script verarbeitet werden (z. B. durch das Eingeben in ein Formular).



.. class:: far-far-smaller

CWE-79: XSS - Beispiel 1 - XSS Typ 1 (Php)
--------------------------------------------------------

.. exercise::

    .. code:: php
        :number-lines:

        # Rückgabe einer Willkommensnachricht basierend auf dem 
        # HTTP Get username Parameter
        $username = $_GET['username'];
        echo '<div class="header"> Welcome, ' . $username . '</div>';

    .. solution:: 
        :pwd: beliebig_lange

        Das Problem ist, dass der Nutzername „beliebig lange“` sein kann und insbesondere beliebigen JavaScript Code enthalten. Beispiel :code:`http://trustedSite.example.com/welcome.php?username=<Script Language="Javascript">alert("You've been attacked!");</Script>`. Komplexerer Code könnte zum Beispiel ein Fakelogin nachbauen und so die Zugangsdaten des Nutzers abgreifen. Entsprechende Links könnten mit Hilfe von Werkzeugen so verschleiert werden, dass der Nutzer nicht bemerkt, dass er auf einen Link mit Schadfunktion klickt.



.. class:: far-far-smaller

CWE-79: XSS - Beispiel 2 - XSS Typ 2 (JSP)
--------------------------------------------------------

.. exercise::

    .. code:: jsp
        :number-lines:

        <%  String eid = request.getParameter("eid");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from emp where id="+eid);
            if (rs != null) {
                rs.next();
                String name = rs.getString("name");
            }
        %>

        Employee Name: <%= name %>

    .. solution:: 
        :pwd: Mein Name

        - Problem: Falls der Nutzer in der Lage war seinen Namen selber zu wählen und beim Anlegen keine ausreichenden Prüfungen stattgefunden haben, ist ggf. ein XSS Angriff möglich. Zum Beispiel wenn in einem Forum angezeigt wird, wer gerade online ist und der Nutzer seinen Namen auf z. B. :code:`<script>alert('Mein Name')</script>` setzt.
        - Weiteres Problem : In dem Beispiel wird der Parameter :code:`eid` nicht validiert. Der Angreifer kann beliebige SQL-Statements ausführen. (SQL-Injection)


.. class:: far-far-smaller

CWE-79: XSS - Beispiel 3 - XSS Typ 2 (PHP)
--------------------------------------------------------

.. exercise:: 

    .. code:: php
        :number-lines:

        $username = mysql_real_escape_string($username);
        $fullName = mysql_real_escape_string($fullName);
        $query = sprintf('Insert Into users (uname,pwd,fname) Values ("%s","%s","%s")', 
                        $username, 
                        crypt($password),
                        $fullName) ;
        mysql_query($query);
        ...

    .. solution::
        :pwd: HTML code

        Hier wird zwar die Eingabe validiert (``mysql_real_escape_string``) aber *nur* in Hinblick auf SQL Injections! Der Angreifer kann so einen Nutzer anlegen, der HTML/JavaScript code enthält. Auch in diesem Fall ist es erforderlich, dass der Name an einer anderen Stelle angezeigt wird, wenn ein anderer (ggf. authentifizierter) Nutzer die Seite besucht. 



CWE-79: Improper Neutralization of Input During Web Page Generation - Abhilfemaßnahmen und Erkennung
-------------------------------------------------------------------------------------------------------------

.. class:: incremental list-with-explanations

- **Prüfe jeden Input**
- Prüfung, dass alle auf dem Client durchgeführten Prüfungen auch auf dem Server vorgenommen werden
- Verwendung von geprüften/sicheren APIs
- Verringerung der Angriffsfläche mit dem Ziel möglichst wenig Daten in Cookies etc. zu speichern
- Verwendung von HttpOnly Cookies (d. h. Cookies, die nicht über JavaScript ausgelesen werden können)
- Statische Analyse Werkzeuge
- Beherzigen von Best Practices (`XSS Prevention Cheat Sheet <https://cheatsheetseries.owasp.org/cheatsheets/Cross_Site_Scripting_Prevention_Cheat_Sheet.html>`__)
- Setzen der CSP (Content Security Policy) 

  (Dies löst nicht das Problem, minimiert aber ggf. die Auswirkungen!)


.. No 3 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-89: Improper Neutralization of Special Elements used in an SQL Command (*SQL Injection*)
----------------------------------------------------------------------------------------------

CWE-89: Improper Neutralization of Special Elements used in an SQL Command 
----------------------------------------------------------------------------

:Kurzbeschreibung: Ein SQL-Befehl wird ganz oder teilweise unter Verwendung extern beeinflusster Eingaben von einer vorgelagerten Komponente erzeugt. Dabei werden aber spezielle Elemente nicht oder falsch bereinigt, die den beabsichtigten SQL-Befehl verändern könnten, wenn er an eine nachgelagerte Komponente gesendet wird.

:Wahrscheinlichkeit des Missbrauchs: Hoch
:Technologie: Datenbanken
:Betrifft: Zugriffskontrolle, Vertraulichkeit, Integrität



.. class:: far-far-smaller

CWE-89: SQL Injection - Beispiel 1 (MS SQL)
--------------------------------------------------------

.. exercise:: 

    .. code:: sql
        :number-lines:

        SELECT ITEM,PRICE 
            FROM PRODUCT 
            WHERE ITEM_CATEGORY='$user_input' 
            ORDER BY PRICE

    .. warning::
        :class: incremental margin-top-2em larger

        MS SQL hat eine eingebaute Funktion, die es erlaubt Shell Befehle auszuführen. Diese Funktion kann auch in einem SQL Statement verwendet werden.

    .. solution:: 
        :pwd: Kommando_frei   

        Sollte der Nutzername :code:`'; exec master..xp_cmdshell 'dir' --` sein, dann wird das entsprechende Kommando ausgeführt.



.. class:: far-far-smaller

CWE-89: SQL Injection - Beispiel 2 (PHP)
--------------------------------------------------------

.. exercise::

    .. code:: php
        :number-lines:

        $id = $_COOKIE["mid"];
        mysql_query(
            "SELECT MessageID, Subject FROM messages WHERE MessageID = '$id'"
        );


    .. solution::
        :pwd: Cookies

        Das Problem ist, dass der Wert von :code:`$id`, welcher aus einem Cookie ausgelesen wird,  nicht validiert wird. Auch wenn Cookies nicht trivial von einem Nutzer bzw. Angreifer manipuliert werden können, so ist es dennoch möglich. Der Angreifer kann so beliebige SQL Statements ausführen. Deswegen gilt: *Alle* Eingaben müssen validiert werden.
  


CWE-89: Improper Neutralization of Special Elements used in an SQL Command - Abhilfemaßnahmen und Erkennung
--------------------------------------------------------------------------------------------------------------

.. class:: incremental list-with-explanations

- Verwendung von geprüften/sicheren APIs
- Verwendung von *Prepared Statements*
- Datenbank nur mit den notwendigen Rechten betreiben 
  
  (*Principle of Least Privilege*)
- Sollte es notwendig sein einen dynamischen SQL Befehl zu erstellen, dann sollten geprüfte Escapefunktionen verwendet werden
- Statische Analyse Werkzeuge
- ggf. Application-level Firewall einsetzen



.. No 4 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-416: Use After Free (UAF)
----------------------------------------------------------------------------------------------

CWE-416: Use After Free 
----------------------------------------------------------------------------

:Kurzbeschreibung: Referenzierung von Speicher nach der Freigabe kann dazu führen, dass ein Programm abstürzt, unerwartete Werte verwendet oder Code ausführt.

:Wahrscheinlichkeit des Missbrauchs: Hoch
:Programmiersprachen: C, C++
:Betrifft: Verfügbarkeit, Vertraulichkeit, Integrität



.. class:: far-far-smaller

CWE-416: Use After Free - Triviales Beispiel
----------------------------------------------------------------------------

.. code:: C
    :number-lines:

    char* ptr = (char*)malloc (SIZE);
    if (err) {
      abrt = 1;
      free(ptr);
    }

.. code:: C
    :number-lines: 6   
    :class: incremental

    // ... somewhere else in the code
    //    char* otherPtr = (char*)malloc (SIZE);
    //    otherPtr* = <HACKER CONTROLLED VALUE>;
    // ...

.. code:: C
    :number-lines: 10
    :class: incremental

    if (abrt) {
      // Next: use of ptr after free which uses the hacker controlled value
      logError("operation aborted before commit", ptr); 
    }

.. admonition:: Hinweis
    :class: margin-top-1em

    Ziel ist es im Allgemeinen eine Referenz auf einen interessanten Speicherbereich zu erhalten, der bereits freigegeben wurde und dann den Inhalt dieses Speicherbereichs auszulesen bzw. zu manipulieren, um die nächste Verwendung zu kontrollieren.



.. class:: far-far-smaller

CWE-416: Use After Free - Beispiel
----------------------------------------------------------------------------

.. exercise::

    .. container:: two-columns

        .. container:: column no-separator

            .. code:: C
                :number-lines: 

                #include <stdlib.h>
                #include <stdio.h>
                #include <string.h>
                #define BUFSIZER1 512
                int main(int argc, char **argv) {
                    char *buf1R1, *buf2R1, *buf2R2;
                    buf2R1 = (char *) malloc(BUFSIZER1);
                    printf("buf2R1     -> %p\n",buf2R1); 
                    free(buf2R1);
                    printf("[FREED]       %p\n",buf2R1);
                    
                    buf2R2 = (char *) malloc(BUFSIZER1);
                    strncpy(buf2R2, argv[1], BUFSIZER1-1);
                    printf("buf2R2     -> %p\n",buf2R2);
                    printf("buf2R2     =  %s\n",buf2R2);
                    printf("!!! buf2R1 =  %s\n",buf2R1);
                    free(buf2R2);
                }


        .. container:: column margin-left-1em

            **Fragen**:

            Wird dieses Program bis zum Ende laufen oder abstürzen? 
            
            Welche Ausgabe erzeugt das Programm?

            Ist die Ausgabe bei jedem Lauf gleich?

    .. solution::
        :pwd: Das Ende wir kommen.   

        Das Programm wird (immer) bis zum Ende laufen!

        Ausgabe - 1. Lauf:

        .. code:: text

            buf2R1     -> 0xaaaafd8982a0
            [FREED]       0xaaaafd8982a0
            buf2R2     -> 0xaaaafd8982a0
            buf2R2     =  Test
            !!! buf2R1 =  Test

        Ausgabe - 2. Lauf:

        .. code:: text

            buf2R1     -> 0xaaab070812a0
            [FREED]       0xaaab070812a0
            buf2R2     -> 0xaaab070812a0
            buf2R2     =  Test
            !!! buf2R1 =  Test

        Der Inhalt von :code:`buf2R2` ist :code:`Test`, obwohl dort nie explizit etwas hineinkopiert wurde. Die Ausgabe (Adressen) ist bei jedem Lauf anders, da wir Position-Independent-Code haben und der Kernel ASLR verwendet.

        Die Ausgabe wird bei jedem Lauf gleich sein, wenn man beides explizit unterbindet.

        .. code:: bash
        
            # Kompiliert 2023 mit GCC
            gcc uaf.c -fno-stack-protector -D_FORTIFY_SOURCE=0 -no-pie -fno-pic -o uaf
            echo 0 | sudo tee /proc/sys/kernel/randomize_va_space
        
            $ ./uaf Test
            buf2R1     -> 0x294562a0
            [FREED]       0x294562a0
            buf2R2     -> 0x294562a0
            buf2R2     =  Test
            !!! buf2R1 =  Test

            $ ./uaf Test
            buf2R1     -> 0x294562a0
            [FREED]       0x294562a0
            buf2R2     -> 0x294562a0
            buf2R2     =  Test
            !!! buf2R1 =  Test



.. class:: far-far-smaller

CWE-416: CVE-2006-4997 IP over ATM clip_mkip dereference freed pointer (Linux Kernel)
---------------------------------------------------------------------------------------

.. exercise::

    .. code:: c

        // clip_mkip (clip.c):
            198 static void clip_push(struct atm_vcc *vcc,struct sk_buff *skb) {
            ...
            234         memset(ATM_SKB(skb), 0, sizeof(struct atm_skb_data));
            235         netif_rx(skb);
            236 }
            ...         // PROBLEMATIC CODE STARTS HERE: 
            510         clip_push(vcc,skb);
            511         PRIV(skb->dev)->stats.rx_packets--;
            512         PRIV(skb->dev)->stats.rx_bytes -= len;

        // netif_rx (dev.c):
            1392 int netif_rx(struct sk_buff *skb) {
            ...
            1428        kfree_skb(skb);	//drop skb
            1429        return NET_RX_DROP;

    .. solution:: 
        :pwd: 511_1428   

        In Zeile 511 wird auf den Speicherbereich von :code:`skb->dev` zugegriffen, obwohl dieser bereits freigegeben wurde in ``netif_rx`` in Zeile 1428.


CWE-416: Use After Free - Abhilfemaßnahmen und Erkennung
----------------------------------------------------------------------------

.. class:: incremental

- Wahl einer sicheren Programmiersprache (z. B. RUST)
- explizites :code:`NULL` setzen, nachdem der Speicherbereich freigegeben wurde 
- Fuzzing
- Statische Analyse Werkzeuge

.. supplemental::

    Empfohlene Lektüre: `One day short of a full chain: Real world exploit chains explained <https://github.blog/2021-03-24-real-world-exploit-chains-explained/>`__ (In Teil 1 wird eine UAF Schwachstelle genutzt.)



.. No 5 in CWE Top 2023

.. class:: new-subsection transition-move-to-top
    
CWE-78: Improper Neutralization of Special Elements used in an OS Command (*OS Command Injection*)
----------------------------------------------------------------------------------------------------------


CWE-78: Improper Neutralization of Special Elements used in an OS Command
----------------------------------------------------------------------------

:Kurzbeschreibung: Alles oder zumindest ein Teil eines Betriebssystembefehls hängt von extern beeinflussten Eingaben ab. Es erfolgt jedoch keine Bereinigung spezieller Elemente, die den beabsichtigten Betriebssystembefehl verändern könnten.

.. The product constructs all or part of an OS command using externally-influenced input from an upstream component, but it does not neutralize or incorrectly neutralizes special elements that could modify the intended OS command when it is sent to a downstream component.  

:Wahrscheinlichkeit des Missbrauchs: Hoch
:Betrifft: Verfügbarkeit, Vertraulichkeit, Integrität
:Arten:
    1. Ein bestimmtes Program wird ausgeführt und die Nutzerdaten werden als Parameter übergeben.
    2. Die Anwendung bestimmt basierend auf den Nutzerdaten welches Program mit welchen Parametern ausgeführt wird.


.. class:: far-far-smaller

CWE-78: Improper Neutralization of Special Elements used in an OS Command - Beispiel (Java)
-------------------------------------------------------------------------------------------

.. exercise:: 

    .. code:: java
        :number-lines:

        ...
        String btype = request.getParameter("backuptype");
        String cmd = new String(
            "cmd.exe /K \"c:\\util\\rmanDB.bat "
            +btype+
            "&&c:\\utl\\cleanup.bat\"")

        System.Runtime.getRuntime().exec(cmd);
        ...


    .. solution:: 
        :pwd: Improper

        Der Wert von :code:`btype` wird nicht validiert und dewegen kann der Angreifer  beliebige Befehle ausführen, da die Shell (:code:`cmd.exe``) mehrere Befehle, die mit :code:`&&` verknüpft sind hintereinander ausführt.


CWE-78: Improper Neutralization of Special Elements used in an OS Command - Abhilfemaßnahmen und Erkennung
--------------------------------------------------------------------------------------------------------------

.. class:: incremental

- Verwendung von geprüften/sicheren APIs.
- Anwendung bzw. Befehl nur mit den notwendigen Rechten betreiben (*Principle of Least Privilege*) bzw. in einer Sandbox ausführen.
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


:Kurzbeschreibung:  Empfangene Eingaben oder Daten werden nicht nicht oder falsch validiert in Hinblick darauf, dass die Eingaben die Eigenschaften haben, die für eine sichere und korrekte Verarbeitung der Daten erforderlich sind.   

.. The product receives input or data, but it does not validate or incorrectly validates that the input has the properties that are required to process the data safely and correctly.   

:Wahrscheinlichkeit des Missbrauchs: Hoch
:Betrifft: Verfügbarkeit, Vertraulichkeit, Integrität
:Anwendungsbereiche:
    - Rohdaten - Strings, Zahlen, Parameter, Dateiinhalte, etc.
    - Metadaten - Information über die Rohdaten, wie zum Beispiel *Header* oder Größe


CWE-20: Improper Input Validation - zu verifizierende Werte und Eigenschaften
-------------------------------------------------------------------------------

.. class:: incremental smaller

- **Größen** wie Größe, Länge, Häufigkeit, Preis, Rate, Anzahl der Vorgänge, Zeit usw.
- **implizite oder abgeleitete Größen**, wie z. B. die tatsächliche Größe einer Datei anstelle einer angegebenen Größe
- **Indizes**, Offsets oder Positionen in komplexeren Datenstrukturen
- **Schlüssel** von Hashtabellen, assoziativen Feldern usw.
- **syntaktische Korrektheit** - Übereinstimmung mit der erwarteten Syntax
- Bestimmung des **tatsächlichen Typs der Eingabe** (oder das, was die Eingabe zu sein scheint)
- **Konsistenz** zwischen den Rohdaten und Metadaten, zwischen Referenzen usw.
- **semantische Korrektheit** bzw. Konformität mit domänenspezifischen Regeln, z. B. Geschäftslogik
- **Authentizität** von z. B. kryptografischen Signaturen 



.. class:: no-title center-child-elements

O'Reilly ist keine SQL Injection
-------------------------------------------------------------------------------

.. admonition:: Beobachtung
    :class: hint

    Ein Name wie ``O'Reily`` stellt ein Problem dar, wenn er in ein SQL Statement eingefügt wird, sollte jedoch von der Anwendung verarbeitet werden können und die Eingabevalidierung passieren.


.. admonition:: Improper Input Validation vs. Injection
    :class: incremental

    Die Validierung muss immer in Hinblick auf den Kontext erfolgen.



.. class:: far-far-smaller

CWE-20: Improper Input Validation - Beispiel partielle Validierung
---------------------------------------------------------------------

.. exercise::

    C:

    .. code:: c
        :number-lines:

        #define MAX_DIM 100   
        int m,n, error; /* m,n = board dimensions */
        board_square_t *board;
        printf("Please specify the board height: \n");
        error = scanf("%d", &m);
        if ( EOF == error ) die("No integer passed!\n");
        printf("Please specify the board width: \n");
        error = scanf("%d", &n);
        if ( EOF == error ) die("No integer passed!\n");
        if ( m > MAX_DIM || n > MAX_DIM ) die("Value too large!\n");

        board = (board_square_t*) malloc( m * n * sizeof(board_square_t));
        ...

    .. admonition:: Warnung
        :class: incremental margin-top-1em

        Ein vergleichbares Problem ist auch in sicheren Programmiersprachen möglich.

    .. solution::
        :pwd: Allokation

        Das Problem ist, dass n und m nicht vollständig validiert werden. Sind die Werte negativ, dann wird ggf. sehr viel Speicher alloziert oder das Programm stürzt ab. 



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


:Kurzbeschreibung: Daten vor oder nach einem Puffer werden gelesen.

.. The product reads data past the end, or before the beginning, of the intended buffer. 

:Wahrscheinlichkeit des Missbrauchs: Hoch
:Programmiersprachen: C, C++
:Betrifft: Vertraulichkeit
:Auswirkungen: Umgehung von Schutzmaßnahmen; Lesen von Speicher

.. supplemental::

    Die Ausnutzung dieser Schwachstelle ist häufig schwierig, da nicht immer bekannt ist welche und wie viele Daten gelesen werden können. Es kann allerdings möglich sein Speicheradressen auszulesen. Dies kann ggf. genutzt werden, um Mechanismen wie ASLR zu umgehen.


.. class:: far-far-smaller

CWE-125: Out-of-bounds Read - Beispiel: partielle Validierung
-------------------------------------------------------------

.. exercise::

    C:

    .. code:: C
        :number-lines:

        int getValueFromArray(int *array, int len, int index) {
            int value;

            // check that the array index is less than the maximum length of the array
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


    .. solution::
        :pwd: index   

        Der Wert von :code:`index` wird nicht gegen zu kleine Werte validiert. Der Angreifer kann so beliebige Speicherbereiche auslesen.



CWE-125: Out-of-bounds Read - Abhilfemaßnahmen und Erkennung
----------------------------------------------------------------------

.. class:: incremental

- eine sichere Programmiersprache verwenden
- Fuzzing
- Statische Analyse Werkzeuge welche Kontroll- und Datenflussanalyse durchführen



.. No 8 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-22: Improper Limitation of a Pathname to a Restricted Directory (*Path Traversal*)
-------------------------------------------------------------------------------------------


CWE-22: Improper Limitation of a Pathname to a Restricted Directory
----------------------------------------------------------------------------


:Kurzbeschreibung:  Externe Eingaben werden für die Konstruktion eines Pfadnamens verwendet, der eine Datei oder ein Verzeichnis identifizieren soll, das sich unterhalb eines eingeschränkten übergeordneten Verzeichnisses befindet. Eine Bereinigung spezieller Elemente innerhalb des Pfadnamens erfolgt jedoch nicht ordnungsgemäß, was dazu führen kann, dass der Pfadname zu einem Ort außerhalb des eingeschränkten Verzeichnisses aufgelöst wird. 

.. The product uses external input to construct a pathname that is intended to identify a file or directory that is located underneath a restricted parent directory, but the product does not properly neutralize special elements within the pathname that can cause the pathname to resolve to a location that is outside of the restricted directory. 

:Wahrscheinlichkeit des Missbrauchs: Hoch
:Betrifft: Vertraulichkeit, Integrität, Verfügbarkeit


.. class:: far-far-smaller

CWE-22: Path Traversal - Beispiel: fehlende Validierung
--------------------------------------------------------

.. exercise::

    PHP:

    .. code:: php
        :number-lines:

        <?php
        $file = $_GET['file'];
        include("/home/www-data/$file");
        ?>

    .. solution:: 
        :pwd: no_validation_of_file

        Das Problem ist, dass der Wert von :code:`file` nicht validiert wird. Der Angreifer kann so beliebige Dateien auslesen.


.. class:: far-far-smaller

CWE-22: Path Traversal - Beispiel: partielle Validierung
--------------------------------------------------------

.. exercise::

    Perl:

    .. code:: Perl
        :number-lines:

        my $Username = GetUntrustedInput();
        $Username =~ s/\.\.\///;                # Remove ../
        my $filename = "/home/user/" . $Username;
        ReadAndSendFile($filename);

    .. container:: incremental margin-top-2em

        Java: 

        .. code:: java
            :number-lines:

            String path = getInputPath();
            if (path.startsWith("/safe_dir/")) {
                File f = new File(path);
                f.delete()
            }

    .. solution::
        :pwd: Perl-oh-Perl!

        - Problem im Perl Beispiel: :code:`Username` wird nur bzgl. ../ am Anfang der Zeichenkette gesäubert. Beginnt der Nutzername mit :code:`../../` dann kann der Angreifer dennoch zum darüber liegenden Verzeichnis wechseln. Es fehlt im Wesentlichen das :code:`g` Flag (vgl. Reguläre Ausdrücke in ``sed``)

        - Problem im Java Beispiel: Auch in diesem Falle wird zwar der Anfang geprüft, d. h. ob der Pfad mit :code:`/safe_dir/` beginnt, aber dies verhindert nicht, dass der Pfad im Weiteren :code:`../` verwendet und der Angreifer darüber zu einem höherliegenden Verzeichnis wechseln kann.



.. class:: far-far-smaller

CWE-22: Path Traversal - Beispiel: verwirrende Python API\ [#]_
----------------------------------------------------------------

.. container:: two-columns

    .. container:: column

        .. code:: Python
            :number-lines:

            import os
            import sys
            def main():
            filename = sys.argv[1]
            path = os.path.join(os.getcwd(), 
                                filename)
            try:
                with open(path, 'r') as f:
                file_data = f.read()
            except FileNotFoundError as e:
                print("Error - file not found")
    
            # do something with file_data

    .. container:: column incremental larger

        .. epigraph:: 

            **Dokumentation os.path.join**

            Join one or more path components intelligently. The return value is the concatenation of path and any members of \*paths with exactly one directory separator following each non-empty part except the last, meaning that the result will only end in a separator if the last part is empty. 
            
            If a component is an absolute path [...], all previous components are thrown away and joining continues from the absolute path component.
            
            -- `Python 3.11.7 <https://docs.python.org/3.11/library/os.path.html#os.path.join>`__

.. [#] Verwirrende APIs gibt es in praktischen allen Sprachen!



CWE-22: Path Traversal - Abhilfemaßnahmen und Erkennung
----------------------------------------------------------------------

.. class:: incremental

- Eingabe vollständig validieren; zum Beispiel über kanonische Pfade
- Sandboxen
- Umgebung härten
- Bei Fehlerausgaben darauf achten, dass keine Informationen über das Dateisystem preisgegeben werden
- den Code mit minimalen Rechten ausführen


.. No 9 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-352: Cross-Site Request Forgery (*CSRF*)
-------------------------------------------------------------------------------------------


CWE-352: Cross-Site Request Forgery (CSRF)
----------------------------------------------------------------------------


:Kurze Beschreibung: 

    Die Webanwendung prüft nicht bzw. kann nicht prüfen, ob eine Anfrage absichtlich von dem Benutzer gestellt wurde, von dessen Browser sie übermittelt wurde.

    D. h. eine CSRF Schwachstelle nutzt das Vertrauen aus, das eine Webseite in den Browser eines Nutzers hat. Bei einem CSRF-Angriff wird ein legitimer Nutzer von einem Angreifer dazu gebracht, ohne sein Wissen eine Anfrage zu übermitteln, die er nicht beabsichtigt hat und auch nicht bemerkt.

:Missbrauchswahrscheinlichkeit: Mittel
:Auswirkung: Hängt von den Nutzerrechten ab
:Ausmaß: Vertraulichkeit, Integrität, Verfügbarkeit


.. class:: far-far-smaller

CWE-352: Cross-Site Request Forgery (CSRF) - ursprüngliche Form
------------------------------------------------------------------


.. image:: images/csrf.svg
    :alt: Cross-Site Request Forgery (CSRF) - ursprüngliche Form
    :height: 1050px



CWE-352: Cross-Site Request Forgery (CSRF) in 2023
----------------------------------------------------------

.. epigraph::   

    Fiber ist ein von Express inspiriertes Web-Framework, das in Go geschrieben wurde. In der Anwendung wurde eine Cross-Site Request Forgery (CSRF)-Schwachstelle entdeckt, die es einem Angreifer ermöglicht, beliebige Werte zu injizieren und bösartige Anfragen im Namen eines Benutzers zu fälschen. Diese Schwachstelle kann es einem Angreifer ermöglichen, beliebige Werte ohne Authentifizierung einzuschleusen oder verschiedene böswillige Aktionen im Namen eines authentifizierten Benutzers durchzuführen, wodurch die Sicherheit und Integrität der Anwendung gefährdet werden kann. Die Schwachstelle wird durch eine unsachgemäße Validierung und Durchsetzung von CSRF-Tokens innerhalb der Anwendung verursacht.

    -- `CVE-2023-45128 <https://nvd.nist.gov/vuln/detail/CVE-2023-45128>`__ (übersetzt mit DeepL)

.. container:: small margin-top-1em

    Identifizierte Schwachstellen: *CWE-20* Improper Input Validation, *CWE-807* Reliance on Untrusted Inputs in a Security Decision, *CWE-565* Reliance on Cookies without Validation and Integrity Checking, **CWE-352** Cross-Site Request Forgery


CWE-352: Cross-Site Request Forgery (CSRF) in 2023
----------------------------------------------------------

Standardtechniken, die CSRF verhindern *sollen*:

.. class:: incremental

- Same-site Cookies (für Authentifizierung)
- CSRF-Tokens, wenn diese die folgenden Eigenschaften haben:
  
  - Einmalig pro Nutzersession
  - Geheim
  - nicht vorhersagbar (z. B. eine sehr große, sicher erzeugte Zufallszahl)
 
- Validierung des Referer-Header 
- Custom Request Header, da diese nur vom JavaScript Code gesetzt werden können, der den gleichen Ursprung hat (siehe *Same Origin Policy* (SOP)).

.. container:: incremental small foundations

    Auch diese Techniken lassen sich ggf. (alle zusammen) aushebeln, `wenn die Anwendung weitere Schwachstellen aufweist <https://portswigger.net/web-security/csrf>`__. So gibt/gab es Anwendungen, die Anfragen, die nur über ein POST request gestellt werden sollten, auch bei einem GET akzeptiert haben. 


.. supplemental::

    In allen Browsern wird in der Zwischenzeit für Cookies die Same-site Policy angewandt mit dem Wert :code:`Lax`. Dieser Wert hat zur Folge, dass Cookies nur dann gesendet werden, wenn der Nutzer explizit auf einen Link klickt oder sich innerhalb der selben Seite befindet.
    


.. No 10 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-434: Unrestricted Upload of File with Dangerous Type
-------------------------------------------------------------------------------------------



CWE-434: Unrestricted Upload of File with Dangerous Type
----------------------------------------------------------------------------

:Kurze Beschreibung: 

    Es ist möglich potentiell gefährliche Dateien hochzuladen bzw. zu transferieren, die von der Anwendung automatisch im Kontext der Anwendung verarbeitet werden.

:Missbrauchswahrscheinlichkeit: Mittel
:Auswirkung: Bis hin zur Ausführung von beliebigen Befehlen
:Ausmaß: Vertraulichkeit, Integrität, Verfügbarkeit



.. class:: far-far-smaller

CWE-434: Unrestricted Upload of File with Dangerous Type - Beispiel
----------------------------------------------------------------------------

.. exercise::

    HTML:

    .. code:: HTML
        :number-lines:

        <form action="upload_picture.php" method="post" enctype="multipart/form-data">
            Choose a file to upload:
            <input type="file" name="filename"/>
            <br/>
            <input type="submit" name="submit" value="Submit"/>
        </form>


    PHP:

    .. code:: PHP
        :number-lines:

        // Define the target location where the picture being
        // uploaded is going to be saved.
        $target = "pictures/" . basename($_FILES['uploadedfile']['name']);

        // Move the uploaded file to the new location.
        move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target)


    .. solution:: 
        :pwd: upload

        Problem: Die Datei :code:`$_FILES['uploadedfile']['name']` wird nicht validiert. Sollte der Nutzer statt einem Bild eine PHP Datei hochladen, dann wird diese beim einem späteren Aufruf im Kontext der Anwendung ausgeführt.
    
        Eine einfache Möglichkeit die Schwachstelle auszunutzen wäre die Datei:

            .. code:: PHP

                // malicious.php
        
                <?php
                system($_GET['cmd']);
                ?>

            Mit einer Anfrage wie:

                ``...malicious.php?cmd=ls%20-l``    



CWE-434: Unrestricted Upload of File with Dangerous Type - Abhilfemaßnahmen und Erkennung
-------------------------------------------------------------------------------------------

- Beim Speichern von Dateien niemals den ursprünglichen Dateinamen verwenden sondern einen vom Server generierten.
- Speicher die Daten nicht im Kontext der Webanwendung sondern außerhalb des Webroots.
- Prüfe die Dateiendung. Prüfe den Inhalt der Datei gegen die Erwartung.
- Ausführen der Webanwendung mit minimalen Rechten.
- Sandbox.



.. No 2 in 2023 CWE Top 10 KEV Weaknesses

.. class:: new-subsection transition-move-to-top

CWE-122: Heap-based Buffer Overflow
-------------------------------------------------------------------------------------------


CWE-122: Heap-based Buffer Overflow
------------------------------------------------------


:Kurze Beschreibung: 

    Ein Pufferüberlauf, bei dem der Puffer, der überschrieben wird, auf dem Heap alloziiert wurde, was im Allgemeinen bedeutet, dass der Puffer mit einer Routine wie malloc() allloziiert wurde.

:Missbrauchswahrscheinlichkeit: Hoch
:Sprachen: C/C++
:Auswirkung: Bis hin zur Ausführung von beliebigen Befehlen
:Ausmaß: Vertraulichkeit, Integrität, Verfügbarkeit, Zugriffskontrolle



.. class:: far-far-smaller

CWE-122: Heap-based Buffer Overflow
-------------------------------------------------------------------

.. exercise::

    „Basisbeispiel“ in C:

    .. code:: C
        :number-lines:

        #define BUFSIZE 256
        int main(int argc, char **argv) {
            char *buf;
            buf = (char *)malloc(sizeof(char)*BUFSIZE);
            strcpy(buf, argv[1]);
        }


    .. solution:: 
        :pwd: buf-to-small

        Problem: Die Größe von ``buf`` ist unabhängig von der Größe von :code:`argv[1]`. 



CWE-122: Heap-based Buffer Overflow - Abhilfemaßnahmen und Erkennung
-----------------------------------------------------------------------

- Verwendung einer sicheren Programmiersprache
- Verwendung von sicheren APIs
- Kompilierung unter Verwendung entsprechender Schutzmechanismen (Position-Independent Executables (PIE), Canaries, ...)
- Härtung der Umgebung (z. B. ASLR)
- Statische Analyse Werkzeuge
- Fuzzing




.. No 6 in 2023 CWE Top 10 KEV Weaknesses https://cwe.mitre.org/top25/archive/2023/2023_kev_list.html

.. class:: new-subsection transition-move-to-top


CWE-502: Deserialization of Untrusted Data
--------------------------------------------------------------------------------


CWE-502: Deserialization of Untrusted Data
------------------------------------------------------


:Kurze Beschreibung: 

    Nicht vertrauenswürdige Daten werden deserialisiert ohne - *je nach Bibliothek notwendige vorhergehende* - Prüfung, dass die Daten die erwarteten Eigenschaften haben.

:Missbrauchswahrscheinlichkeit: Mittel
:Sprachen: Java, Ruby, Python, PHP, JavaScript, ...
:Ausmaß: Insbesondere: Integrität und Verfügbarkeit (DoS); weitere Effekte sind vom Kontext abhängig.

:Alternative Begriffe: (Un-)Marshalling, (Un-)Pickling


.. supplemental::

    Bei der Serialisierung werden programminterne Objekte so verpackt, dass die Daten extern gespeichert und/oder übertragen werden können. Die Deserialisierung kehrt diesen Prozess um.




.. class:: far-far-smaller

CWE-502: Deserialization of Untrusted Data - Beispiel
-------------------------------------------------------------------

Java

.. code:: java
    :number-lines:

    File file = new File("object.obj");
    try ( FileInputStream fin = new FileInputStream(file);
          ObjectInputStream oin = new ObjectInputStream(fin)
        ) {
        javax.swing.JButton button = (javax.swing.JButton) oin.readObject();
        ...
    } 

.. supplemental::

    In diesem Beispiel wird ein Objekt aus einer Datei gelesen und in eine Variable vom Typ :code:`javax.swing.JButton` geschrieben. Der Typ des Objekts wird nicht geprüft. Es ist möglich, dass die Datei ein Objekt enthält, welches vom Typ :code:`javax.swing.JButton` ist, aber nicht die Eigenschaften hat, die ein Button haben sollte. In diesem Fall wird keine Exception geworfen, aber das Objekt kann nicht wie erwartet verwendet werden bzw. es kommt zur Ausführung von beliebigem Code.



.. class:: far-far-smaller

CWE-502: Deserialization of Untrusted Data - Beispiel
-------------------------------------------------------------------

.. exercise:: 

    Python

    .. code:: Python
        :number-lines:
        
        class ExampleProtocol(protocol.Protocol):

            def dataReceived(self, data):
                # ... parse the incoming data and 
                # after receiving headers, call confirmAuth() to authenticate

            def confirmAuth(self, headers):
                try:
                    token = cPickle.loads(base64.b64decode(headers['AuthToken']))
                    if not check_hmac(token['signature'], token['data'], getSecretKey()):
                        raise AuthFail
                    self.secure_data = token['data']
                except:
                    raise AuthFail

    .. solution::
        :pwd: PicklingAtItsBest

        In diesem Fall könnte man der Funktion ein Objekt unterschieben, dass bei der Deserialisierung beliebigen Code ausführt (zum Beispiel, um einen weitere Prozess zu starten).

        Dieses Problem wird in der Dokumentation auch explizit erwähnt:

        .. epigraph::

            **Warning The pickle module is not secure**. Only unpickle data you trust.
            It is possible to construct malicious pickle data which will execute arbitrary code during unpickling. Never unpickle data that could have come from an untrusted source, or that could have been tampered with.

            -- `Python 3.12 <https://docs.python.org/3/library/pickle.html>`__

    

CWE-502: Deserialization of Untrusted Data - Abhilfemaßnahmen und Erkennung
-----------------------------------------------------------------------------


- ggf. Einsatz von Signaturen, um sicherzustellen, dass der serialisierte Code nicht manipuliert wurde 
- Serialisiere nur Daten, die auch wirklich serialisiert werden müssen
- Verwendung von sicheren Formaten (z. B. JSON)
- statische Analyse

.. class:: supplemental

    Empfohlene Lektüre: `Deserialization Vulnerabilities <https://portswigger.net/web-security/deserialization>`__




.. No 7 in 2023 CWE Top 10 KEV Weaknesses https://cwe.mitre.org/top25/archive/2023/2023_kev_list.html

.. class:: new-subsection transition-move-to-top



CWE-918: Server-Side Request Forgery (SSRF)\ [#]_
--------------------------------------------------------------------------------

.. [#] ≈ :ger:`Serverseitige Anfragefälschung`


CWE-918: Server-Side Request Forgery 
------------------------------------------------------


:Kurze Beschreibung: 
    Der Webserver erhält eine URL oder eine ähnliche Anfrage und ruft den Inhalt dieser URL ab, stellt aber nicht sicher, dass die Anfrage an das erwartete Ziel gesendet wird.

:Technologien: Webserver
:Ausmaß: Vetraulichkeit, Integrität 



CWE-918: Server-Side Request Forgery 
------------------------------------------------------

.. image:: images/ssrf.svg
    :alt: Server-Side Request Forgery (SSRF)
    :width: 1800px



CWE-918: Server-Side Request Forgery 
-----------------------------------------------------------------

**Beispiel: CVE-2002-1484**

:Beschreibung:  
    Wenn der DB4Web-Server so konfiguriert ist, dass er ausführliche Debug-Meldungen verwendet, können entfernte Angreifer DB4Web als Proxy verwenden und über eine Anfrage an eine URL, die die Ziel-IP-Adresse und den Port angibt, TCP-Verbindungen zu anderen Systemen (Port-Scan) versuchen, was einen Verbindungsstatus in der resultierenden Fehlermeldung erzeugt.
    
.. class:: incremental

:PoC: http://127.0.0.1/DB4Web/172.31.93.30:22/foo

.. class:: incremental

:Workaround:
    Um die Ausnutzung dieses Features zu verhindern, muss die Standardfehlerseite durch eine benutzerdefinierte ersetzt werden.

    .. container:: far-smaller minor

        Der Hersteller betrachtet die Funktionalität nicht als Fehler, sondern als Feature für Entwickler.



.. class:: far-far-smaller

CWE-918: Server-Side Request Forgery - Beispiel: NodeJS Unicode Handling Fehler [#]_
---------------------------------------------------------------------------------------

JavaScript:

.. code:: JavaScript
    :number-lines:

    var base = "http://orange.tw/sandbox/";
    var path = req.query.path;
    if (path.indexOf("..") == -1) { // check for no directory traversal
        http.get(base + path, callback);
    }

.. container:: incremental

    Beispiel URL (*U+FF2E Full width Latin capital letter N*):

    .. code:: restructuredtext
        :class: incremental

          http://orange.tw/sandbox/ＮＮ/passwd

    .. code:: restructuredtext
        :class: incremental

        ≙ http://orange.tw/sandbox/\xFF\x2E\xFF\x2E/passwd

    .. code:: restructuredtext
        :class: incremental

        ≙ http://orange.tw/sandbox/\x2E\x2E/passwd

    .. code:: restructuredtext
        :class: incremental

        ≙ http://orange.tw/sandbox/../passwd

    
.. [#] `Exploiting URL Parsers <https://www.blackhat.com/docs/us-17/thursday/us-17-Tsai-A-New-Era-Of-SSRF-Exploiting-URL-Parser-In-Trending-Programming-Languages.pdf>`__



.. class:: far-far-smaller

CWE-918: Server-Side Request Forgery - Beispiel: URL Parser vs. Abfrage der URL
---------------------------------------------------------------------------------

PHP (> 7.0.13):

.. code:: php
    :number-lines:

    $url = 'http://foo@127.0.0.1⬜@google.com:11211/'; // ⬜ is "just" a space
    $parsed = parse_url($url);
    var_dump($parsed[host]); // string(10) "google.com"
    var_dump($parsed[port]); // int(11211)
    curl($url);

Ergebnis:

.. container:: incremental

    ``curl`` fragt die URL ``127.0.0.1:11211`` ab.

    D. h. ``curl`` und php interpretieren die URL unterschiedlich.



CWE-918: Server-Side Request Forgery
-----------------------------------------------------------------------------

.. rubric:: Variante: Blind SSRF

Bei *Blind SSRF*-Schwachstellen werden auch Back-End-HTTP-Anfragen an eine bereitgestellte URL gestellt, die Antwort der Back-End-Anfrage jedoch nicht an die Front-End-Antwort der Anwendung zurückgegeben.

.. supplemental::

    Empfohlene Lektüre: `Blind Server-Side Request Forgery (SSRF) <https://portswigger.net/web-security/ssrf/blind>`__



CWE-918: Server-Side Request Forgery - Abhilfemaßnahmen und Erkennung
-----------------------------------------------------------------------------

- keine (Wieder-)Verwendung der Eingabe URL
- sichere APIs
- statische Analyse (insbesondere Datenflußanalysen)
- Behandlung von Zugriffen von lokalen Maschinen sollte mit der gleichen sorgfalt überprüft werden wie Zugriffe von externen Maschinen; andernfalls können kritische SSRF Angriffe durchgeführt werden
- Firewall/Network Policy, um Zugriff auf interne Systeme zu verhindern



.. No 8 in 2023 CWE Top 10 KEV Weaknesses https://cwe.mitre.org/top25/archive/2023/2023_kev_list.html

.. class:: new-subsection transition-move-to-top


CWE-843: Access of Resource Using Incompatible Type (Type Confusion)
------------------------------------------------------------------------------


CWE-843: Access of Resource Using Incompatible Type (Type Confusion)
----------------------------------------------------------------------

:Beschreibung: 

        Eine Anwendung initialisiert eine Ressource mit einem bestimmten Typ (z. B. Zeiger (:eng:`Pointer`), Objekt, etc.). Später wird auf die Ressource (Variable) dann mit einem anderen Typ zugegriffen. 

:Sprachen: insbesondere (aber nicht ausschließlich) C/C++; im Prinzip in jeder Sprache, die automatische Typkonvertierungen durchführt. 
:Ausmaß: Integrität, Verfügbarkeit, Vertraulichkeit


.. class:: far-far-smaller

CWE-843: Access of Resource Using Incompatible Type - Beispiel in C
----------------------------------------------------------------------

.. exercise::

    .. code:: c
        :number-lines:

        #define NAME_TYPE 1
        #define ID_TYPE 2
        
        struct MessageBuffer {
            int msgType;
            union {
                char *name;
                int nameID;
        };  };

        int main (int argc, char **argv) {
            struct MessageBuffer buf;
            char *defaultMessage = "Hello World";
            buf.msgType = NAME_TYPE;
            buf.name = defaultMessage;              // printf("*buf.name %p", buf.name);
            buf.nameID = (int)(defaultMessage + 1); // printf("*buf.name %p", buf.name);
            if (buf.msgType == NAME_TYPE) printf("%s\n", buf.name);
            else                          printf("ID %d\n", buf.nameID);
        }

    Welche Ausgabe erzeugt das Programm?

    .. solution:: 
        :pwd: bufbuf

        Der Zugriff auf ``buf.nameId`` manipuliert den Zeiger auf ``buf.name``. Dieser zeigt nun auf die Speicherstelle ``defaultMessage + 1`` weswegen der nachfolgende Zugriff ``buf.name`` „nur“ noch ``ello World`` ausgibt und nicht mehr ``Hello World``.



.. class:: far-far-smaller

CWE-843: Access of Resource Using Incompatible Type - Beispiel in Perl
------------------------------------------------------------------------

.. exercise::

    .. code:: perl
        :number-lines:

        my $UserPrivilegeArray = ["user", "user", "admin", "user"];
        my $userID = get_current_user_ID();
        if ($UserPrivilegeArray eq "user") {
            print "Regular user!\n";
        }
        else {
            print "Admin!\n";
        }

        print "\$UserPrivilegeArray = $UserPrivilegeArray\n";


    .. solution:: 
        :pwd: Zuviel ist zuviel

        In der Zeile: :code:`if ($UserPrivilegeArray eq "user")` wurde vergesen die Indizierung (:code:`$userID`) zu verwenden (:code:`$UserPrivilegeArray->{$userID}`). Es wird also das Array als Ganzes mit dem String ``user`` verglichen und der Vergleich ist immer ``falsch (:eng:`false`)``.



.. No 10 in 2023 CWE Top 10 KEV Weaknesses https://cwe.mitre.org/top25/archive/2023/2023_kev_list.html

.. class:: new-subsection transition-move-to-top

CWE-306: Missing Authentication for Critical Function
--------------------------------------------------------------------------------




CWE-306: Missing Authentication for Critical Function
----------------------------------------------------------------------

:Beschreibung: 

    Eine Anwendung führt eine kritische Funktion aus, ohne die Identität des Nutzers zu überprüfen. Kritischer Funktionen sind solche, die entweder signifikante Ressourcen verbrauchen oder nur von privilegierten Nutzern ausgeführt werden sollten.

:Sprachen: "alle"



CWE-306: Missing Authentication for Critical Function - Abhilfemaßnahmen und Erkennung
-----------------------------------------------------------------------------------------

.. class:: incremental

- manuelle Code Reviews 
- statische Analyse (Binärcode und/oder Quellcode)



.. class:: no-title

Dump C /  C++
---------------------

.. image:: screenshots/dump_c_c++_2024_02_27.png
    :alt: Dump C /  C++
    :height: 1150px
    :align: center
    :class: picture


.. class:: no-title

C++ is missjudged by the White House
---------------------------------------

.. image:: screenshots/c++_creator_rebuts_white_house_warning-infoworld_2024_03_26.png
    :alt: C++ is missjudged
    :height: 1150px
    :align: center


.. class:: new-section

Open Worldwide Application Security Project (OWASP)
----------------------------------------------------------------------


OWASP
-----------------------------------------------------------------------

.. class:: incremental
    
- gemeinnützige Stiftung, die sich für die Verbesserung der Sicherheit von Software einsetzt
- 2001 gegründet
- weltweit tätig
- Stellt insbesondere Foren, Dokumente und Werkzeuge bereit
- Dokumente, die bei der Entwicklung sicherer Anwendungen unterstützen:

  - `OWASP Web Security Testing Guide <https://owasp.org/www-project-web-security-testing-guide/>`__
  - `OWASP Code Review Guide <https://owasp.org/www-project-code-review-guide/>`__
- Ausgewählte Projekte:
  
  .. class:: incremental

  - `OWASP Top 10 (die relevantesten Sicherheitsprobleme bei Webanwendungen) <https://owasp.org/www-project-top-ten/>`__
  - `Cheat Sheets <https://owasp.org/www-project-cheat-sheets/>`__
  - `OWASP Dependency-Track <https://owasp.org/www-project-dependency-track/>`__
  - `OWASP Web Security Testing Guide <https://owasp.org/www-project-web-security-testing-guide/>`__
  


.. class:: integrated-exercise 

Übung: Schwachstelle(n) (1)
-----------------------------------------------------------------------

.. container:: far-smaller

  .. exercise:: malloc verstehen
    
    1. Benenne die Schwachstelle(n) entsprechend der CWEs (ohne ID).
    2. Identifiziere die für die Schwachstelle(n) relevanten Zeilen im Code.
    3. Gebe - falls möglich - einen Angriffsvektor an.
    4. Skizziere mögliche Auswirkung der Schwachstelle(n) (z. B. Verlust der Vertraulichkeit, Integrität oder Verfügbarkeit; Umgehung der Zugriffskontrolle; beliebige Codeausführung, ...) 

    .. code:: C
        :class: smaller
        :number-lines:
        
        #include <stdio.h>
        #include <string.h>
        void process(char *str) {
            char *buffer = malloc(16);
            strcpy(buffer, str);
            ...
            // ... definitively executed in the future: free(buffer);
        }
        int main(int argc, char *argv[]) {
            if (argc < 2) { printf("Usage: %s <string>\n", argv[0]); return 1; }
            process(argv[1]);
            return 0;
        }

    .. solution:: 
        :pwd: AVuln

        Die Länge von :code:`str` wird nicht validiert. Es kommt somit potentiel zu einem "Out-of-bounds Write" (:code:`strcpy(buffer,str)`). Ein String wäre jeder String, der länger als 16 Zeichen ist. Ein Angriffsvektor wäre z. B. ein String, der 17 Zeichen lang ist und am Ende ein :code:`\0` enthält. Die Auswirkung wäre ein Pufferüberlauf, der ggf. zur Ausführung von beliebigem Code führt.



.. class:: integrated-exercise 

Übung: Schwachstelle(n) (2)
-------------------------------------------------

.. container:: far-smaller

  .. exercise:: REST API

    Sie analysieren eine REST API die folgendes Verhalten aufweist, wenn man einem Blog einen Kommentar hinzufügen möchte:

    .. code:: HTTP
        :class: smaller
        :number-lines:

        POST /post/comment HTTP/1.1
        Host: important-website.com
        Content-Length: 100

        postId=3&comment=This+<post>+was+helpful.&name=Karl+Gustav

    Fragt man danach den Webservice nach dem Kommentar, dann erhält man folgendes zurück:

    .. code:: HTML
        :class: smaller
        :number-lines:

        <div class="comment">
            <div class="name">Karl Gustav</div>
            <div class="comment">This <post> was helpful.</div>
        </div>

    Bewerten Sie die Schwachstelle: CWE Name, problematische Codestelle(n), möglicher Angriffsvektor und mögliche Auswirkung.

    .. solution::
        :pwd: StoredXXS

        Es handelt sich um eine *Stored Cross-Site Scripting* Schwachstelle. Der Angreifer kann beliebigen Code ausführen, wenn er es schafft der angegriffenen Person den richtigen Link unterzuschieben. In diesem Fall wird der Code in der Variable :code:`comment` ausgeführt. Der Angreifer könnte also z. B. folgende Anfrage stellen:

        :code:`POST /post/comment HTTP/1.1 Host: important-website.com Content-Length: 100 postId=3&comment=<script>/*+Bad+stuff+here...+*/</script>&name=Karl+Gustav`



.. class:: integrated-exercise 

Übung: Schwachstelle(n) (3)
-----------------------------------------------------------------------

.. container:: far-smaller

  .. exercise:: SQL Abfrage mit Java

    Java:

    .. code:: java
        :class: smaller
        :number-lines:

        String query = 
            "SELECT account_balance FROM user_data WHERE user_name = "
                + request.getParameter("customerName");
        try {
            Statement statement = connection.createStatement( ... );
            ResultSet results = statement.executeQuery( query );
        }

    Bewerten Sie die Schwachstelle: CWE Name, problematische Codestelle(n), möglicher Angriffsvektor und mögliche Auswirkung.



.. class:: integrated-exercise 

Übung: Schwachstelle(n) (4)
-----------------------------------------------------------------------

.. container:: far-smaller

  .. note::  
    :class: blue-background

    **URL Encoding**

    :%20: Leerzeichen

    :%22: "

    :%3C: <

    :%3E: >

    :%2F: /


  .. exercise:: Verwendung von HTTP Query Parametern

    Sie beobachten folgendes Verhalten einer Webseite:

    **Anfrage**

    .. code:: http
        :number-lines:

        https://my-website.com/search?
              term=This%20is%20a%20%3C%22%3Egift%3C%2F%22%3E

    **Antwort**

    .. code:: HTML
        :number-lines:

        <div class="search-result">
            <div class="title">This is a <">gift</"></div>
        </div>   

    Bewerten Sie die Schwachstelle: CWE Name, problematische Codestelle(n), möglicher Angriffsvektor und mögliche Auswirkung.

    .. solution::
        :pwd: reflectedXXS

        Es handelt sich um eine *Reflected Cross-Site Scripting* Schwachstelle. Der Angreifer kann beliebigen Code ausführen, wenn er es schafft der angegriffenen Person den richtigen Link unterzuschieben. In diesem Fall wird der Code in der Variable :code:`term` ausgeführt. Der Angreifer könnte also z. B. folgende Anfrage stellen:

        :code:`https://my-website.com/search?term=<script>/*+Bad+stuff+here...+*/</script>``
