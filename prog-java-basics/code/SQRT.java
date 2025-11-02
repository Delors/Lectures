// Beispielaufruf: java SQRT.java 10000 0.00000000001

double sqrt(double x, double xn, double epsilon) {
    var xnNew = 0.5 * (xn + x / xn);
    // IO.println(xn + " => " + xnNew);
    if (Math.abs(xn - xnNew) < epsilon)
        return xnNew;
    else
        return sqrt(x, xnNew, epsilon);
}

double sqrt(double x, double epsilon) {
    return sqrt(x, x, epsilon);
}

double sqrt_it(double x, double epsilon) {
    var yn = x;
    var yn1 = yn;
    do {
        yn = yn1;
        yn1 = 0.5*(yn + x / yn);
    } while (Math.abs(yn - yn1) > epsilon);

    return yn1;
}

void main(String[] args) {
    var x = Double.parseDouble(IO.readln("Enter number to compute SQRT for: "));
    var epsilon = Double.parseDouble(IO.readln("Enter epsilon: "));
    var result = sqrt_it(x, epsilon);
    IO.println("The SQRT of " + x + " is " + result);

}