package org.kuali.kra.subaward.lookup;

public enum SubAwardDocumentStatusConstants {

    Active ("ACTIVE", "Final"),
    Pending("PENDING", "Saved"),
    Both("BOTH", "Both");

    private final String code;
    private final String description;

    SubAwardDocumentStatusConstants(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String code() {
        return code;
    }

    public String description() {
        return description;
    }

}
