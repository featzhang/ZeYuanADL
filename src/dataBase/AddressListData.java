package dataBase;

public class AddressListData {

    private String XAddress = null;
    private String XClass = null;
    private String XEmail = null;
    private String XHometown = null;
    private String XID = null;
    private String XName = null;
    private String XNo1 = null;
    private String XNo1Class = null;
    private String XNo2 = null;
    private String XNo2Class = null;
    private String XNo3 = null;
    private String XNo3Class = null;
    private String XQQ = null;
    private String XRemarks = null;
    private String XSex = null;
    private String XCalendarBirthday = null;
    private String XLunarBirthday = null;

    public AddressListData() {
    }

    public AddressListData(String XID, String XName, String XSex, String XClass, String XNo1, String XNo1Class, String XNo2, String XNo2Class, String XNo3, String XNo3Class, String XAddress, String XHometown, String XQQ, String XEmail, String XRemarks, String XCalendarBirthday, String XLunarBirthday) {
        this.XAddress = XAddress;
        this.XClass = XClass;
        this.XEmail = XEmail;
        this.XHometown = XHometown;
        this.XID = XID;
        this.XName = XName;
        this.XNo1 = XNo1;
        this.XNo1Class = XNo1Class;
        this.XNo2 = XNo2;
        this.XNo2Class = XNo2Class;
        this.XNo3 = XNo3;
        this.XNo3Class = XNo3Class;
        this.XQQ = XQQ;
        this.XRemarks = XRemarks;
        this.XSex = XSex;
        this.XCalendarBirthday = XCalendarBirthday;
        this.XLunarBirthday = XLunarBirthday;
    }

    public String getAddress() {
        return XAddress;
    }

    public String getXClass() {
        return XClass;
    }

    public String getEmail() {
        return XEmail;
    }

    public String getHometown() {
        return XHometown;
    }

    public String getID() {
        return XID;
    }

    public String getName() {
        return XName;
    }

    public String getNo1() {
        return XNo1;
    }

    public String getNo1Class() {
        return XNo1Class;
    }

    public String getNo2() {
        return XNo2;
    }

    public String getNo2Class() {
        return XNo2Class;
    }

    public String getNo3() {
        return XNo3;
    }

    public String getNo3Class() {
        return XNo3Class;
    }

    public String getQQ() {
        return XQQ;
    }

    public String getRemarks() {
        return XRemarks;
    }

    public String getSex() {
        return XSex;
    }

    public void setAddress(String s) {
        XAddress = s;
    }

    public void setXClass(String s) {
        XClass = s;
    }

    public void setEmail(String s) {
        XEmail = s;
    }

    public void setHometown(String s) {
        XHometown = s;
    }

    public void setID(String s) {
        XID = s;
    }

    public void setName(String s) {
        XName = s;
    }

    public void setNo1(String s) {
        XNo1 = s;
    }

    public void setNo1Class(String s) {
        XNo1Class = s;
    }

    public void setNo2(String s) {
        XNo2 = s;
    }

    public void setNo2Class(String s) {
        XNo2Class = s;
    }

    public void setNo3(String s) {
        XNo3 = s;
    }

    public void setNo3Class(String s) {
        XNo3Class = s;
    }

    public void setQQ(String s) {
        XQQ = s;
    }

    public void setRemarks(String s) {
        XRemarks = s;
    }

    public void setSex(String s) {
        XSex = s;
    }

    public String getCalendarBirthday() {
        return XCalendarBirthday;
    }

    public void setXCalendarBirthday(String XCalendarBirthday) {
        this.XCalendarBirthday = XCalendarBirthday;
    }

    public String getLunarBirthday() {
        return XLunarBirthday;
    }

    public void setXLunarBirthday(String XLunarBirthday) {
        this.XLunarBirthday = XLunarBirthday;
    }

    @Override
    public String toString() {
        String s = "";
        s += "ID:" + XID;
        s += " Name:" + XName;
        s += " NO1:" + XNo1;
        s += " NO2:" + XNo2;
        s += " NO3:" + XNo3;
        s += " ADDRESS:" + XAddress;
        s += " Hometown:" + XHometown;
        s += " QQ:" + XQQ;
        s += " Email:" + XEmail;
        s += " Remarks:" + XRemarks;
        s += " Birthday:" + XCalendarBirthday;
        s += " BirthdayLunar:" + XLunarBirthday;
        return s;
    }
}
