package org.kuali.kra.protocol;

public enum ProtocolSpecialVersion {
    AMENDMENT("A", "Amendment"),
    RENEWAL("R", "Renewal"),
    CONTINUATION ("C", "Continuation"),
    FYI ("F", "FYI");

    private final String code;
    private final String description;

    ProtocolSpecialVersion(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
