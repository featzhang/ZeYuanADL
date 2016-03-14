package util;

public class passwordHandle {

    protected char ntw(int n) {
        int nn = n % 62;
        char a = 0;
        switch (nn) {
            case 0:
                a = '0';
                break;
            case 1:
                a = '1';
                break;
            case 2:
                a = '2';
                break;
            case 3:
                a = '3';
                break;
            case 4:
                a = '4';
                break;
            case 5:
                a = '5';
                break;
            case 6:
                a = '6';
                break;
            case 7:
                a = '7';
                break;
            case 8:
                a = '8';
                break;
            case 9:
                a = '9';
                break;
            case 10:
                a = 'a';
                break;
            case 11:
                a = 'b';
                break;
            case 12:
                a = 'c';
                break;
            case 13:
                a = 'd';
                break;
            case 14:
                a = 'e';
                break;
            case 15:
                a = 'f';
                break;
            case 16:
                a = 'g';
                break;
            case 17:
                a = 'h';
                break;
            case 18:
                a = 'i';
                break;
            case 19:
                a = 'j';
                break;
            case 20:
                a = 'k';
                break;
            case 21:
                a = 'l';
                break;
            case 22:
                a = 'm';
                break;
            case 23:
                a = 'n';
                break;
            case 24:
                a = 'o';
                break;
            case 25:
                a = 'p';
                break;
            case 26:
                a = 'q';
                break;
            case 27:
                a = 'r';
                break;
            case 28:
                a = 's';
                break;
            case 29:
                a = 't';
                break;
            case 30:
                a = 'u';
                break;
            case 31:
                a = 'v';
                break;
            case 32:
                a = 'w';
                break;
            case 33:
                a = 'x';
                break;
            case 34:
                a = 'y';
                break;
            case 35:
                a = 'z';
                break;
            case 36:
                a = 'u';
                break;
            case 37:
                a = 'A';
                break;
            case 38:
                a = 'B';
                break;
            case 39:
                a = 'C';
                break;
            case 40:
                a = 'D';
                break;
            case 41:
                a = 'E';
                break;
            case 42:
                a = 'F';
                break;
            case 43:
                a = 'G';
                break;
            case 44:
                a = 'H';
                break;
            case 45:
                a = 'I';
                break;
            case 46:
                a = 'J';
                break;
            case 47:
                a = 'K';
                break;
            case 48:
                a = 'L';
                break;
            case 49:
                a = 'M';
                break;
            case 50:
                a = 'N';
                break;
            case 51:
                a = 'O';
                break;
            case 52:
                a = 'P';
                break;
            case 53:
                a = 'Q';
                break;
            case 54:
                a = 'R';
                break;
            case 55:
                a = 'S';
                break;
            case 56:
                a = 'T';
                break;
            case 57:
                a = 'U';
                break;
            case 58:
                a = 'V';
                break;
            case 59:
                a = 'W';
                break;
            case 60:
                a = 'X';
                break;
            case 61:
                a = 'Y';
                break;
            case 62:
                a = 'Z';
                break;

        }
        return a;
    }

    public StringBuffer numberparse(StringBuffer s) {
        StringBuffer sb = new StringBuffer(s.toString().toLowerCase());
        StringBuffer sb1 = new StringBuffer();
        int length = sb.length();
        for (int i = 0; i < length; i++) {
            sb1.append(wtn(sb.charAt(i)));
        }
        sb1 = overturn(sb1);
        sb1 = verify(sb1);
        StringBuffer sb2 = new StringBuffer();
        for (int i = 0; i < length - 1; i++) {
            sb2.append(ntw(Integer.parseInt(sb1.charAt(i) + "")
                    * Integer.parseInt(sb1.charAt(i + 1) + "")));
        }
        sb2.append(ntw(Integer.parseInt(sb1.charAt(length - 1) + "")
                * Integer.parseInt(sb1.charAt(0) + "")));
        return sb2;
    }

    protected StringBuffer overturn(StringBuffer sbf) {
        StringBuffer s = new StringBuffer(sbf);
        int length = s.length();
        int a = Integer.parseInt(s.charAt(0) + "") % length;
        char ac = s.charAt(a % length);
        int b = Integer.parseInt(s.charAt(1) + "") % length;
        int c = Integer.parseInt(s.charAt(2) + "") % length;
        char cc = s.charAt(c % length);
        int i = 0;
        int m = (wtn(s.charAt(length - 1)) + 10)
                * (wtn(s.charAt(length - 1)) + 4) * 2;
        while (m-- > 0) {
            ac = s.charAt(a);
            cc = s.charAt(c);
            s.setCharAt(a, cc);
            s.setCharAt(c, ac);
            b = (a * c / (a + c) + 1) % length;
            if (a == c) {
                a = (c + 1) % length;
            } else {
                a = c;
            }
            c = (a + b) % length;
            i++;
        }
        return s;
    }

    protected StringBuffer verify(StringBuffer sbf) {
        int length = sbf.length();
        int sum = 1;
        while (length-- > 0) {
            sum *= Integer.parseInt(sbf.charAt(length) + "") + 1;
            sum %= 10;
            sum++;
        }
        sbf.append(sum);
        if (sbf.charAt(0) == '0') {
            sbf.setCharAt(0, '3');
        }
        return sbf;
    }

    protected int wtn(char c) {
        int a = 9;
        switch (c) {
            case 'w':
            case 'g':
            case 'f':
            case '8':
                a = 7;
                break;
            case 'k':
            case 'j':
            case 'b':
            case '9':
                a = 2;
                break;
            case 't':
            case 'c':
            case 's':
            case '0':
                a = 3;
                break;
            case 'y':
            case 'z':
            case 'v':
            case '2':
                a = 0;
                break;
            case 'o':
            case 'x':
            case 'l':
            case '7':
                a = 5;
                break;
            case 'n':
            case 'q':
            case 'e':
            case '6':
                a = 6;
                break;
            case 'p':
            case 'r':
            case '1':
                a = 1;
                break;
            case 'i':
            case 'd':
            case '3':
                a = 8;
                break;
            case 'u':
            case 'm':
            case '4':
                a = 9;
                break;
            case 'h':
            case 'a':
            case '5':
                a = 4;
                break;
        }
        return a;
    }
}
