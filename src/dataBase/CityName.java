package dataBase;

public class CityName {
    private String ZID;
    private String ZUP;
    private String ZName;
    private String ZPostCode;

    public void setID(String ZIDl) {
        this.ZID = ZIDl;
    }

    public void setName(String ZName) {
        this.ZName = ZName;
    }

    public void setPostCode(String ZPostCode) {
        this.ZPostCode = ZPostCode;
    }

    public void setUP(String ZUP) {
        this.ZUP = ZUP;
    }

    public String getID() {
        return ZID;
    }

    public String getName() {
        return ZName;
    }

    public String getPostCode() {
        return ZPostCode;
    }

    public String getUP() {
        return ZUP;
    }
    
}
