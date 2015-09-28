package org.kuali.coeus.coi.framework;

public enum ProjectTypeCode {
    PROPOSAL(1L), INSTITUTIONAL_PROPOSAL(2L), IRB_PROTOCOL(3L), IACUC_PROTOCOL(4L), AWARD(5L);

    private final Long id;

    ProjectTypeCode(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
