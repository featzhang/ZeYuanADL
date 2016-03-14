package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class networkLink {

    public static boolean linkable() {
        String ip = "192.168.1.1";
        try {
            Process p = Runtime.getRuntime().exec("cmd /c ping -n 1 " + ip);  //此处1变大可以增加精确度，但影响测试速度
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String temp = null;
            StringBuffer strBuffer = new StringBuffer();
            while ((temp = (in.readLine())) != null) {
                strBuffer.append(temp);
            }
            if (strBuffer.toString().matches(".*\\(\\d?\\d% loss\\).*")) {
                System.out.println("正常");
                return true;
            } else {
                System.out.println("不正常");
                return false;
            }
        } catch (IOException e) {
        }
        return false;
    }
}
