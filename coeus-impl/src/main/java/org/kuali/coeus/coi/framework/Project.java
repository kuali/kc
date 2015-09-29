package org.kuali.coeus.coi.framework;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Project implements Serializable {

    @Size(min = 1, max = 200)
    @NotNull
    private String title;

    @Digits(integer = 11, fraction = 0)
    @NotNull
    private Long typeCode;

    @Size(min = 1, max = 20)
    @NotNull
    private String sourceSystem;

    @Size(min = 1, max = 50)
    @NotNull
    private String sourceIdentifier;

    @Size(min = 1, max = 75)
    private String sourceStatus;

    List<ProjectPerson> persons;

    @Size(min = 1, max = 6)
    private String sponsorCode;

    @Size(min = 1, max = 200)
    private String sponsorName;

    private Date startDate;

    private Date endDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Long typeCode) {
        this.typeCode = typeCode;
    }

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

    public String getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(String sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    public List<ProjectPerson> getPersons() {
        return persons;
    }

    public void setPersons(List<ProjectPerson> persons) {
        this.persons = persons;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
