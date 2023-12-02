import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class P1 {
  private HashMap<Integer, ReturnDTO> M = new HashMap<>();

  public class ReturnDTO {
    public int val;
    public ArrayList<Integer> dp;

    public ReturnDTO(int val, ArrayList<Integer> dp) {
      this.val = val;
      this.dp = dp;
    }
  }

  public void solveP1(int l[], int h[]) {
    ArrayList<Integer> dp = new ArrayList<>();

    ReturnDTO maxValue = opt(l, h, 0);
    System.out.println(maxValue.val);
    Collections.reverse(maxValue.dp);
    System.out.println(maxValue.dp);
  }

  public ReturnDTO opt(int l[], int h[], int i) {
    ArrayList<Integer> solution = new ArrayList<>();

    String indent = "";

    for (int j = 0; j < i; j++) {
      indent += "\t";
    }

    try {
      System.out.println();
      System.out.println(indent +
          "l[i]: " + l[i] + " h[i]: " + h[i] + " i: " + i + " val: " + 0 + " dp: "
          + Arrays.toString(solution.toArray()));
    } catch (Exception e) {
      System.out.println(indent + "Chegou no último pelo H");
    }
    ReturnDTO r = new ReturnDTO(0, solution);

    if (i == l.length - 2) {
      System.out.println(indent + "i == l.length - 1");
      if (l[i] + l[i + 1] > h[i + 1]) {
        r.val = l[i] + l[i + 1];
        r.dp = addDp(l[i], addDp(l[i + 1], solution));

      } else {
        r.val = h[i + 1];
        r.dp = addDp(0, addDp(h[i + 1], solution));
      }

      System.out.println(indent + "Base recursão");
      System.out.println(indent + "l[" + i + "]: " + l[i]);
      System.out.println(indent + "l[" + (i + 1) + "]: " + l[i + 1]);
      System.out.println(indent + "h[" + (i + 1) + "]: " + h[i + 1]);
      System.out.println(indent + "r.val: " + r.val + " r.dp: " + Arrays.toString(r.dp.toArray()));
      System.out.println();

      M.put(i, r);
      return r;
    } else if (i >= l.length - 1) {
      System.out.println(indent + "i == l.length");
      System.out.println(indent + "i: " + i + " val: " + 0 + " dp: " + Arrays.toString(solution.toArray()));
      System.out.println(indent + l[i]);
      r.val = l[i];

      r.dp = addDp(l[i], solution);
      System.out.println(indent + "Base recursão");
      System.out.println(indent + "r.val: " + r.val + " r.dp: " + Arrays.toString(r.dp.toArray()));
      System.out.println();
      M.put(i, r);
      return r;
    }

    if (M.get(i) != null) {
      System.out.println(indent + "M.get(" + i + ") != null");
      System.out.println(indent + M.get(i).val + " " + Arrays.toString(M.get(i).dp.toArray()));
      System.out.println();
      return M.get(i);
    }

    System.out.println(indent + "Vai calcular l[" + i + "]: " + l[i]);
    ReturnDTO r1 = opt(l, h, i + 1);
    System.out.println(indent + r1.val);
    System.out.println(indent + l[i]);
    System.out.println(indent + "(r1.val+l[" + i + "]): " + (r1.val + l[i]));
    System.out.println(indent + "Vai calcular h[" + (i) + "]: " + 0 + "+" + h[i + 1]);
    ReturnDTO r2 = opt(l, h, i + 2);
    System.out.println(indent + r2.val);
    System.out.println(indent + " (r2.val + h[" + (i + 1) + "]): " + (r2.val + h[i + 1]));

    System.out.println(indent + "r1: " + r1.val + " r2: " + (r2.val + h[i + 1]));
    System.out.println(indent + "(r1.val+l[i]): " + (r1.val + l[i]) + " (r2.val): " + (r2.val + h[i + 1]));

    if ((r1.val + l[i]) > (r2.val + h[i + 1])) {
      r.val = r1.val + l[i];
      r.dp = addDp(l[i], r1.dp);
      System.out.println(indent + "Adicionou r1");
      System.out.println(indent + "r1.val: " + r.val + " r1.dp: " + Arrays.toString(r.dp.toArray()));
      System.out.println();
    } else {
      r.val = r2.val + h[i + 1];
      r.dp = addDp(0, addDp(h[i + 1], r2.dp));
      System.out.println(indent + "Adicionou r2");
      System.out.println(indent + "r1.val: " + r2.val + " r2.dp: " + Arrays.toString(r2.dp.toArray()));
      System.out.println();
    }
    M.put(i, r);

    return r;
  }

  private ArrayList<Integer> addDp(int val, ArrayList<Integer> dp) {
    ArrayList<Integer> newDp = (ArrayList<Integer>) dp.clone();
    newDp.add(val);
    return newDp;
  }

}
