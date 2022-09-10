package uz.ataboyev.debtbook.enums;

public enum DebtorStatus {
    RED("QIZIL"),
    YELLOW("SARIQ"),
    GREEN("YASHIL"),
    BLACK("QORA RO'YHAT");

    private final String  nameUz;

    DebtorStatus(String nameUz) {
        this.nameUz = nameUz;
    }

    public String getNameUz() {
        return nameUz;
    }
}
