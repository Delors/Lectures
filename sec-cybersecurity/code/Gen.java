void main(){
    // the dot is added to have a reliable end-of-line marker.
    IO.println("\u200c.");  // ZERO WIDTH NON-JOINER
    IO.println(" \uFE00."); 
    IO.println(" \uFE01."); 
    IO.println("\uFE01\uFE02.");
    IO.println("\uFE01\uFE02\uFE03\uFE04\uFE05\uFE06\uFE07\uFE08.");
    IO.println("l\uFE01(\uFE02\"\uFE03!\uFE04\"\uFE05)\uFE06;\uFE07.");
}
