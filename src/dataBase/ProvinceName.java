package dataBase;

public class ProvinceName {

    private String ZID;//ʡ������ĸ
    private String ZName;
    private String ZSName;//ʡ�����
    private String ZLName;
    private String ZMainCity;//����
    private String ZProvinceType;//0Ϊʡ,1Ϊ�׶�,3Ϊ������

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
