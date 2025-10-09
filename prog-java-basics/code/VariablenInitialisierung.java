int foo(){
    int x; // a local variable must be initialized before it is used; but not immediately!
    x = 5;
    return x;
}


void main(){
    IO.println(foo());
}