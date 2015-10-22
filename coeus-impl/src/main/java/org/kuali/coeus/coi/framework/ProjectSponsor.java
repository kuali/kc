package org.kuali.coeus.coi.framework;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ProjectSponsor implements Serializable {

    public ProjectSponsor() {
        super();
    }

    public ProjectSponsor(String sourceSystem, String sourceIdentifier, String sponsorCode, String sponsorName) {
        this.sourceSystem = sourceSystem;
        this.sourceIdentifier = sourceIdentifier;
        this.sponsorCode = sponsorCode;
        this.sponsorName = sponsorName;
    }

    @Size(min = 1, max = 20)
    @NotNull
    private String sourceSystem;

    @Size(min = 1, max = 50)
    @NotNull
    private String sourceIdentifier;

    @Size(min = 1, max = 6)
    private String sponsorCode;

    @Size(min = 1, max = 200)
    private String sponsorName;


    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getSourceIdentifier() {
        return sourceIdentifier;
    }

    public void setSourceIdentifier(String sourceIdentifier) {
        this.sourceIdentifier = sourceIdentifier;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    @Override
    public String toString() {
        return "ProjectSponsor{" +
                "sourceSystem='" + sourceSystem + '\'' +
                ", sourceIdentifier='" + sourceIdentifier + '\'' +
                ", sponsorCode='" + sponsorCode + '\'' +
                ", sponsorName='" + sponsorName + '\'' +
                '}';
    }
}
