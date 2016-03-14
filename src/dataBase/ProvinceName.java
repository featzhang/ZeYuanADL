package dataBase;

public class ProvinceName {

    private String ZID;//省名首字母
    private String ZName;
    private String ZSName;//省名简称
    private String ZLName;
    private String ZMainCity;//区号
    private String ZProvinceType;//0为省,1为首都,3为自治区

    public void setZID(String ZID) {
        this.ZID = ZID;
    }

    public void setZLName(String ZLName) {
        this.ZLName = ZLName;
    }

    public void setZMainCity(String ZMainCity) {
        this.ZMainCity = ZMainCity;
    }

    public void setZName(String ZName) {
        this.ZName = ZName;
    }

    public void setZProvinceType(String ZProvinceType) {
        this.ZProvinceType = ZProvinceType;
    }

    public void setZSName(String ZSName) {
        this.ZSName = ZSName;
    }

    public String getZID() {
        return ZID;
    }

    public String getZLName() {
        return ZLName;
    }

    public String getZMainCity() {
        return ZMainCity;
    }

    public String getZName() {
        return ZName;
    }

    public String getZProvinceType() {
        return ZProvinceType;
    }

    public String getZSName() {
        return ZSName;
    }
}
