package util;

public class stateClass {

    private String currentDir;
    private String defaultPath;
    private Boolean sortbyAddressUp = true;
    private Boolean sortbyClassUp = true;
    private Boolean sortbyEmailUp = true;
    private Boolean sortbyHomeTownUp = true;
    private Boolean sortbyIDUp = false;
    private Boolean sortbyNameUp = true;
    private Boolean sortbyNO1ClassUp = true;
    private Boolean sortbyNO1Up = true;
    private Boolean sortbyNO2ClassUp = true;
    private Boolean sortbyNO2Up = true;
    private Boolean sortbyNO3ClassUp = true;
    private Boolean sortbyNO3Up = true;
    private Boolean sortbyQqUp = true;
    private Boolean sortbyRankUp = true;
    private Boolean sortbyRemarksUp = true;
    private Boolean sortbySexUp = true;
    // private String SQL;
    //SQLÓï¾ä
    private Boolean stateChange = false;// ×´Ì¬ÊÇ·ñ¸Ä±ä

    public stateClass() {
//        currentDir = System.getProperty("java.class.path");
//        if (currentDir.indexOf(";") != -1) {
//            currentDir = currentDir.substring(0, currentDir.indexOf(";"));
//        }
//        currentDir = currentDir.substring(0, currentDir.lastIndexOf("\\"));
        currentDir=System.getProperty("user.dir");
//        System.out.println(currentDir);
        defaultPath = currentDir;
    }

    public String getcurrentDir() {
        return currentDir;
    }

    public String getDefaultPath() {
        return defaultPath;
    }

    public Boolean getSortbyAddress() {
        return sortbyAddressUp;
    }

    public Boolean getSortbyClass() {
        return sortbyClassUp;
    }

    public Boolean getSortbyEmail() {
        return sortbyEmailUp;
    }

    public Boolean getSortbyHomeTown() {
        return sortbyHomeTownUp;
    }

    public Boolean getSortbyID() {
        return sortbyIDUp;
    }

    public Boolean getSortbyName() {
        return sortbyNameUp;
    }

    public Boolean getSortbyQq() {
        return sortbyQqUp;
    }

    public Boolean getSortbyRank() {
        return sortbyRankUp;
    }

    public Boolean getSortbyRemarks() {
        return sortbyRemarksUp;
    }

    public Boolean getSortbySex() {
        return sortbySexUp;
    }

    public Boolean getSortNO1() {
        return sortbyNO1Up;
    }

    public Boolean getSortNO1Class() {
        return sortbyNO1ClassUp;
    }

    public Boolean getSortNO2() {
        return sortbyNO2Up;
    }

    public Boolean getSortNO2Class() {
        return sortbyNO2ClassUp;
    }

    public Boolean getSortNO3() {
        return sortbyNO3Up;
    }

    public Boolean getSortNO3Class() {
        return sortbyNO3ClassUp;
    }

    public Boolean getStateChange() {
        return stateChange;
    }
    public void setSortbyAddress(Boolean bl) {
        sortbyAddressUp = bl;
    }

    public void setSortbyClass(Boolean bl) {
        sortbyClassUp = bl;
    }

    public void setSortbyEmail(Boolean bl) {
        sortbyEmailUp = bl;
    }

    public void setSortbyHomeTown(Boolean bl) {
        sortbyHomeTownUp = bl;
    }

    public void setSortbyID(Boolean bl) {
        sortbyIDUp = bl;
    }

    public void setSortbyName(Boolean bl) {
        sortbyNameUp = bl;
    }

    public void setSortbyQq(Boolean bl) {
        sortbyQqUp = bl;
    }

    public void setSortbyRank(Boolean bl) {
        sortbyRankUp = bl;
    }

    public void setSortbyRemarks(Boolean bl) {
        sortbyRemarksUp = bl;
    }

    public void setSortbySex(Boolean bl) {
        sortbySexUp = bl;
    }

    public void setSortNO1(Boolean bl) {
        sortbyNO1Up = bl;
    }

    public void setSortNO1Class(Boolean bl) {
        sortbyNO1ClassUp = bl;
    }

    public void setSortNO2(Boolean bl) {
        sortbyNO2Up = bl;
    }

    public void setSortNO2Class(Boolean bl) {
        sortbyNO2ClassUp = bl;
    }

    public void setSortNO3(Boolean bl) {
        sortbyNO3Up = bl;
    }

    public void setSortNO3Class(Boolean bl) {
        sortbyNO3ClassUp = bl;
    }

    public void setStateChange() {
        stateChange = true;
    }
}
