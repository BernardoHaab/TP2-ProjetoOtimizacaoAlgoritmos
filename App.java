public class App {
  public static void main(String[] args) throws Exception {

    // int l[] = { 10, 5, 5, 10 };
    // int h[] = { 5, 50, 60, 5 };

    int l[] = { 10, 1, 10, 11, 5, 6 };
    int h[] = { 5, 50, 5, 1, 12, 70 };

    // int l[] = { 10, 1, 10, 11 };
    // int h[] = { 5, 50, 5, 2 };

    P1 p1 = new P1();

    p1.solveP1(l, h);
  }
}
