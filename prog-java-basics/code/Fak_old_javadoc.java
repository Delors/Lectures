/** 
 * Berechnet die Fakultät von n.
 * 
 * @param n die Zahl, von der die Fakultät berechnet werden soll; (0 <= n <= 20).
 * @return die Fakultät von n. 
 */
long fak(long n){ // TODO mögliche Fehlerfälle abfangen
    /* Die Verwendung von long als Datentyp limitiert uns auf n <= 20;
        durch den wechsel von long auf double könnten wir bis n <= 170 rechnen; 
        sind aber unpräziser. */
    if (n == 0) return 1;
    else return n * fak(n-1);
}
