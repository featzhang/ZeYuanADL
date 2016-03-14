package dataBase;

import util.stateClass;

public class DataBaseConnection {

    public DataBaseConnection() {
    }

    public static String getConnectionStr() {
        return "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="
                + new stateClass().getcurrentDir() + System.getProperty("file.separator") + "adll.mdb";
    }

    public String getConnectionStrCity() {
        return "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="
                + new stateClass().getcurrentDir() + System.getProperty("file.separator") + "adll.mdb";
    }
}
