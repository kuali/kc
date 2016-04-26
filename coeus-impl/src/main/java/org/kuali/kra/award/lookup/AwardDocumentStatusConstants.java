package org.kuali.kra.award.lookup;

public enum AwardDocumentStatusConstants {

    ACTIVE ("ACTIVE", "Final"),
    PENDING("PENDING", "Saved"),
    BOTH("BOTH", "Both");

    private final String code;
    private final String description;

    AwardDocumentStatusConstants(String code, String description) {
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
