void main(){
    // Test ob alles funktioniert
    
    var bib = new Bibliothek("Coblitzallee","DHBW Mannheim");
    
    var ben1 = new Benutzer("Max","Mustermann",1);
    var ben2 = new Benutzer("Erika","Musterfrau",2);
    bib.addBenutzer(ben1);
    bib.addBenutzer(ben2);

    var buch1 = new Buch("Java","J. Gosling",1995,"100-12323-3323");
    var buch2 = new Buch("Python","G. van Rossum",1991,"100-12323-3BFFF3");
    bib.addBuch(buch1);
    bib.addBuch(buch2);

    var ex1 = new Exemplar(1,1,1);
    var ex2 = new Exemplar(2,1,2);
    var ex3 = new Exemplar(3,1,1);
    var ex4 = new Exemplar(4,1,2);
    buch1.addExemplar(ex1);
    buch1.addExemplar(ex2);
    buch2.addExemplar(ex3);
    buch2.addExemplar(ex4);
    ex1.verleihe(ben1);
    ex2.verleihe(ben2);
    ex3.verleihe(ben1);
    
    bib.print();

}