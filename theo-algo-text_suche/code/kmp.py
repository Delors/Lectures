def compute_prefix_function(needle):
    m = len(needle)
    # Initialisierung des Arrays für die Präfix-Funktion
    B = [0] * m  
    j = 0
    B[0] = 0  # Da Python nullbasierte Indizes verwendet, ist B[0] = 0
    
    # Berechnung der Präfix-Funktion
    for i in range(1, m):  # i beginnt bei 1 (entspricht 2 im Pseudocode)
        while j > 0 and needle[j] != needle[i]:
            if j > 1:
                j = B[j - 1]
            else:
                j = 0
            print("B:", B, "i:", i, "j:", j)
        
        if needle[j] == needle[i]:
            j += 1
        
        B[i] = j
    
    return B


def compute_margins(needle):
    """
    Hilfsfunktion zur Berechnung der Präfix-Funktion (Marginalwerte) für das Muster.
    """
    m = len(needle)
    B = [0] * m  # Array für die Längen der Ränder der Teilworte
    j = 0
    for i in range(1, m):  # Startet bei 1 (entspricht i = 2 im Pseudocode)
        while j > 0 and needle[j] != needle[i]:
            j = B[j - 1]
        if needle[j] == needle[i]:
            j += 1
        B[i] = j
    return B


def kmp_text_search(hay, needle):
    """
    Implementiert den Knuth-Morris-Pratt (KMP) Suchalgorithmus.
    
    Parameter:
    - hay: Der Text, in dem gesucht wird.
    - needle: Das Muster, das gesucht wird.
    """
    n = len(hay)
    m = len(needle)
    j = 0  # Zeiger für das Muster/Anzahl der übereinstimmenden Zeichen
    B = compute_margins(needle)  # Berechne Präfix-Funktion (Marginalwerte)
    r = []
    for i in range(n):  # Schleife durch den Text
        print("i:", i, "j:", j, needle[j],end="")
        while j > 0 and hay[i] != needle[j]:
            #if j > 1:
            j = B[j - 1] # j-1 da wir bis j-1 erfolgreich waren beim Matching und jetzt die Länge des Rands von j-1 suchen und dann ab der Stelle im Muster testen ob wir weitere Übereinstimmungen mit dem Text haben!
            #else:
            #    j = 0
        print(" => j:", j,end="")
        if hay[i] == needle[j]:
            j += 1

        print(" => j:", j)
        if j == m:  # Muster vollständig gefunden
            r.append(i - j + 1)
            j = B[j - 1]  # Setze j basierend auf Präfix-Funktion zurück
    return r

# Beispiel
print("Prefix-Funktion:",compute_prefix_function("ababc"))
print("Margins-Funktion:",compute_margins("ababc"))
 
print("Prefix-Funktion:",compute_prefix_function("ananas"))
print("Margins-Funktion:",compute_margins("ananas"))

print("Prefix-Funktion:",compute_prefix_function("okokorok"))
print("Margins-Funktion:",compute_margins("okokorok"))

print("Prefix-Funktion:",compute_prefix_function("test"))
print("Margins-Funktion:",compute_margins("test"))

print("Text-search:",kmp_text_search("abababc","ababc"))

print("Text-search:",kmp_text_search("abcababcab","cababc"))

print("Text-search:",kmp_text_search("saansanananassaansanananas","ananas"))


print("Text-search:",kmp_text_search("abababbbabababab","aba"))