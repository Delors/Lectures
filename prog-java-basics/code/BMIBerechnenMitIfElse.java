void main() {
    var weight = Double.parseDouble(IO.readln("Bitte geben Sie Ihr Gewicht in kg ein: "));
    var height = Double.parseDouble(IO.readln("Bitte geben Sie Ihre Größe in m ein: "));
    var bmi = weight / (height * height);
    // ACHTUNG: ES GIBT VIELE VARIANTEN!
    IO.println("Ihr BMI beträgt: " + bmi);
    if (bmi < 18.5) {
        IO.println((18.5 * height * height) - weight + 
        " kg bis Normalgewicht");
    } else if (bmi >= 18.5 && bmi < 25) {
        IO.println("Normalgewicht");
    } else if (bmi >= 25) {
        IO.println(-(weight - (25 * height * height)) + 
        " kg bis Normalgewicht");
    } 
}
