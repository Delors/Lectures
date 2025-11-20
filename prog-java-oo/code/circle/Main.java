
String toString(Circle c) { return "Circle(" + c.x + "," + c.y + "," + c.r + ")"; }

void main(String[] args) { IO.println(toString(Circle.create(1, 2, 3))); }