package org.kuali.kra.s2s.backport;

public enum YnqConstant {
    YES ("Y", "Yes"),
    NO("N", "No"),
    NA("X", "N/A"),
    YES_NO("YN", "Yes,No"),
    YES_NO_NA("YNX", "Yes,No,N/A"),
    YES_NA("YX", "Yes,N/A"),
    NO_NA("NX", "No,N/A"),
    NONE("", "None");

    private final String code;   
    private final String description; 
    YnqConstant(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String code()   
    { 
        return code; 
    }

    public String description() 
    { 
        return description; 
    }

}