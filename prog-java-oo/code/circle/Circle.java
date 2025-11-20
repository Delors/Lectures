public class Circle {
    int x, y, r;
    private Circle(int x, int y, int r) { this.x = x; this.y = y; this.r = r; }
    static Circle create(int x, int y, int r) { return new Circle(x, y, r); }
}
