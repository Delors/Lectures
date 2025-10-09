int x = 5;
int y = 7;
int n = 0;

IO.println(x == 5 && y == 7);
IO.println(n != 0 && y/n == 1);
IO.println(n != 0 & y/n == 1); // ?!
IO.println(x == 5 && y/x >= 1);

IO.println(x == 5 || y/x >= 0);
IO.println(x == 5 || y/n >= 0);
IO.println(y/n >= 0 || x == 5); // ?!