
double bmi(double weight, double height) {
    return weight / (height * height);
}

double bmi(String weight,String height) {
    return bmi(Double.parseDouble(weight), Double.parseDouble(height));
}

void main(String []args) {
    if (args.length != 2) {
        IO.println("java BMI.java <Gewicht in kg> <Größe in Meter>");
        return;
    }
    final var bmi = bmi(args[0],args[1]);
    print("Ihr BMI beträgt: " + bmi);
    if (bmi < 18.5) {
        IO.println(" - Untergewicht: ");
    } else if (bmi >= 18.5 && bmi < 25) {
        IO.println(" - Normalgewicht");
    } else {
        IO.println(" - Übergewicht");
    }
}
