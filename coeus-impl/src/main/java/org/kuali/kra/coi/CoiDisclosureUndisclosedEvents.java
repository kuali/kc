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
package org.kuali.kra.coi;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.bo.BusinessObject;

import java.sql.Date;

/**
 * This class exists only so you can do lookups on undisclosed events.
 */
public class CoiDisclosureUndisclosedEvents implements BusinessObject {

    

    private static final long serialVersionUID = 636845900013278880L;
    private String personId;
    private String disclosureEventType;
    private Date createDate;
    private String sponsorCode;
    
    private Date createDateFrom;
    private Date createDateTo;
    
    private transient KcPersonService kcPersonService;

    private String projectTitle;
    private String projectId;
    private String personName;
    private String formattedCreateDate;
    private String projectStatus;
    
    private Sponsor sponsor;
    
    public static final String SEARCH_CRITERIA_REPORTER = "personId";
    public static final String SEARCH_CRITERIA_EVENT_TYPE = "disclosureEventType";
    public static final String SEARCH_CRITERIA_CREATE_DATE = "createDate";
    public static final String SEARCH_CRITERIA_CREATE_DATE_FROM = "createDateFrom";
    public static final String SEARCH_CRITERIA_CREATE_DATE_TO = "createDateTo";
    public static final String SEARCH_CRITERIA_SPONSOR = "sponsorCode";
    
    public CoiDisclosureUndisclosedEvents() {
    }
    

    @Override
    public void refresh() {
    }


    public String getDisclosureEventType() {
        return disclosureEventType;
    }


    public void setDisclosureEventType(String disclosureEventType) {
        this.disclosureEventType = disclosureEventType;
    }


    public Date getCreateDate() {
        return createDate;
    }


    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public String getProjectTitle() {
        return projectTitle;
    }


    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }


    public String getProjectId() {
        return projectId;
    }


    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


    public KcPerson getPerson() {
        if (this.personId != null) {
            return this.getKcPersonService().getKcPersonByPersonId(this.personId);
        }
        return new KcPerson();
    }
    
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }


    public Date getCreateDateFrom() {
        return createDateFrom;
    }


    public void setCreateDateFrom(Date createDateFrom) {
        this.createDateFrom = createDateFrom;
    }


    public Date getCreateDateTo() {
        return createDateTo;
    }


    public void setCreateDateTo(Date createDateTo) {
        this.createDateTo = createDateTo;
    }


    public String getSponsorCode() {
        return sponsorCode;
    }


    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }


    public Sponsor getSponsor() {
        return sponsor;
    }


    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }


    public String getPersonName() {
        return personName;
    }


    public void setPersonName(String personName) {
        this.personName = personName;
    }


    public String getFormattedCreateDate() {
        return formattedCreateDate;
    }


    public void setFormattedCreateDate(String formattedCreateDate) {
        this.formattedCreateDate = formattedCreateDate;
    }


    public String getProjectStatus() {
        return projectStatus;
    }


    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

}
