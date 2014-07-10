package org.kuali.coeus.s2sgen.impl.generate;

public enum FormVersion {
    v1_0("1.0"),
    v1_1("1.1"),
    v1_2("1.2"),
    v1_3("1.3"),
    v1_4("1.4"),
    v2_0("2.0"),
    v2_1("2.1");

    private final String version;

    FormVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
