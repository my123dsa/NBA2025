package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScanUtil {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine()  {
        while (true) {
            try {
                return br.readLine().strip();
            }catch (IOException e) {
                System.out.println("문자를 제대로 입력해주세요");
            }
        }
    }

    public static int readInt()  {
        while (true) {
            try {
                String s = br.readLine().strip();
                return Integer.parseInt(s);
            }catch (IOException |NumberFormatException e) {
                System.out.println("숫자로 제대로 입력해주세요");
            }
        }
    }
}
