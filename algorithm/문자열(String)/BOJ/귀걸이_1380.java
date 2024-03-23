import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int scene_num = 1;

        while (true) {
            String AB = " ";
            String n = sc.nextLine();
            int n1 = Integer.parseInt(n);
            if (n1 == 0) break;

            String[] name = new String[n1];
            for (int i = 0; i < n1; i++) {
                name[i] = sc.nextLine();
            }

            ArrayList<String> k = new ArrayList<>();

            for (int i = 0; i < 2 * n1 - 1; i++) {
                String num = sc.next();
                if (k.contains(num))
                    k.remove(num);
                else
                    k.add(num);

                AB = sc.nextLine();
            }
            Integer tmp = Integer.parseInt(k.get(0)) - 1;
            System.out.println(scene_num + " " + name[tmp]);
            scene_num++;

        }
        sc.close();

    }

}
