void main() {
    var weight = Double.parseDouble(IO.readln("Bitte geben Sie Ihr Gewicht in kg ein: "));
    var height = Double.parseDouble(IO.readln("Bitte geben Sie Ihre Größe in m ein: "));
    var bmi = weight / (height * height);
    IO.println("Ihr BMI beträgt: " + bmi);
    IO.println("Untergewicht: " + 
        (bmi < 18.5 ? (18.5 * height * height) - weight + 
        " kg bis Normalgewicht" : "nein"));
    IO.println("Normalgewicht: " + (bmi >= 18.5 && bmi < 25 ? "ja" : "nein"));
    IO.println("Übergewicht: " + 
        (bmi >= 25 ? -(weight - (25 * height * height)) + 
        " kg bis Normalgewicht" : "nein"));
}
