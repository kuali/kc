/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2016 Kuali, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.coi.framework;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Project implements Serializable, ProjectMetadata {

    @Size(min = 1, max = 2000)
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

    private List<ProjectPerson> persons;

    private List<ProjectSponsor> sponsors;

    private Map<String, String> metadata;

    /**
     * @deprecated use {@link #sponsors}
     */
    @Deprecated
    @Size(min = 1, max = 6)
    private String sponsorCode;

    /**
     * @deprecated use {@link #sponsors}
     */
    @Deprecated
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

    public List<ProjectSponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(List<ProjectSponsor> sponsors) {
        this.sponsors = sponsors;
    }

    @Deprecated
    public String getSponsorCode() {
        return sponsorCode;
    }

    @Deprecated
    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    @Deprecated
    public String getSponsorName() {
        return sponsorName;
    }

    @Deprecated
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

    @Override
    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", typeCode=" + typeCode +
                ", sourceSystem='" + sourceSystem + '\'' +
                ", sourceIdentifier='" + sourceIdentifier + '\'' +
                ", sourceStatus='" + sourceStatus + '\'' +
                ", persons=" + persons +
                ", sponsors=" + sponsors +
                ", sponsorCode='" + sponsorCode + '\'' +
                ", sponsorName='" + sponsorName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", metadata=" + metadata +
                '}';
    }
}
