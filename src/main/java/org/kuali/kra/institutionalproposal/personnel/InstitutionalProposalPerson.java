/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.institutionalproposal.personnel;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;

public class InstitutionalProposalPerson extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer proposalPersonId; 
    private String proposalNumber; 
    private Integer sequenceNumber; 
    private String personId; 
    private Integer rolodexId;
    private String  proposalPersonRoleId;
    private String personName; 
    private boolean principalInvestigatorFlag; 
    private boolean facultyFlag; 
    private boolean conflictOfInterestFlag; 
    private Long percentageEffort; 
    private boolean fedrDebrFlag; 
    private boolean fedrDelqFlag; 
    private boolean multiPiFlag; 
    private Long academicYearEffort; 
    private Long summerYearEffort; 
    private Long calendarYearEffort; 
    private ProposalPersonRole proposalPersonRole;
    
    
    public InstitutionalProposalPerson() { 

    } 
    
    public Integer getProposalPersonId() {
        return proposalPersonId;
    }

    public void setProposalPersonId(Integer proposalPersonId) {
        this.proposalPersonId = proposalPersonId;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public String getProposalPersonRoleId() {
        return proposalPersonRoleId;
    }

    public void setProposalPersonRoleId(String proposalPersonRoleId) {
        this.proposalPersonRoleId = proposalPersonRoleId;
    }
    
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public boolean getPrincipalInvestigatorFlag() {
        return principalInvestigatorFlag;
    }

    public void setPrincipalInvestigatorFlag(boolean principalInvestigatorFlag) {
        this.principalInvestigatorFlag = principalInvestigatorFlag;
    }

    public boolean getFacultyFlag() {
        return facultyFlag;
    }

    public void setFacultyFlag(boolean facultyFlag) {
        this.facultyFlag = facultyFlag;
    }

    public boolean getConflictOfInterestFlag() {
        return conflictOfInterestFlag;
    }

    public void setConflictOfInterestFlag(boolean conflictOfInterestFlag) {
        this.conflictOfInterestFlag = conflictOfInterestFlag;
    }

    public Long getPercentageEffort() {
        return percentageEffort;
    }

    public void setPercentageEffort(Long percentageEffort) {
        this.percentageEffort = percentageEffort;
    }

    public boolean getFedrDebrFlag() {
        return fedrDebrFlag;
    }

    public void setFedrDebrFlag(boolean fedrDebrFlag) {
        this.fedrDebrFlag = fedrDebrFlag;
    }

    public boolean getFedrDelqFlag() {
        return fedrDelqFlag;
    }

    public void setFedrDelqFlag(boolean fedrDelqFlag) {
        this.fedrDelqFlag = fedrDelqFlag;
    }

    public boolean getMultiPiFlag() {
        return multiPiFlag;
    }

    public void setMultiPiFlag(boolean multiPiFlag) {
        this.multiPiFlag = multiPiFlag;
    }

    public Long getAcademicYearEffort() {
        return academicYearEffort;
    }

    public void setAcademicYearEffort(Long academicYearEffort) {
        this.academicYearEffort = academicYearEffort;
    }

    public Long getSummerYearEffort() {
        return summerYearEffort;
    }

    public void setSummerYearEffort(Long summerYearEffort) {
        this.summerYearEffort = summerYearEffort;
    }

    public Long getCalendarYearEffort() {
        return calendarYearEffort;
    }

    public void setCalendarYearEffort(Long calendarYearEffort) {
        this.calendarYearEffort = calendarYearEffort;
    }

    public ProposalPersonRole getProposalPersonRole() {
        return proposalPersonRole;
    }

    public void setProposalPersonRole(ProposalPersonRole proposalPersonRole) {
        this.proposalPersonRole = proposalPersonRole;
    }
    
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalPersonId", this.getProposalPersonId());
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("personId", this.getPersonId());
        hashMap.put("rolodexId", getRolodexId());
        hashMap.put("proposalPersonRoleId", getProposalPersonRoleId());
        hashMap.put("personName", this.getPersonName());
        hashMap.put("principalInvestigatorFlag", this.getPrincipalInvestigatorFlag());
        hashMap.put("facultyFlag", this.getFacultyFlag());
        hashMap.put("conflictOfInterestFlag", this.getConflictOfInterestFlag());
        hashMap.put("percentageEffort", this.getPercentageEffort());
        hashMap.put("fedrDebrFlag", this.getFedrDebrFlag());
        hashMap.put("fedrDelqFlag", this.getFedrDelqFlag());
        hashMap.put("multiPiFlag", this.getMultiPiFlag());
        hashMap.put("academicYearEffort", this.getAcademicYearEffort());
        hashMap.put("summerYearEffort", this.getSummerYearEffort());
        hashMap.put("calendarYearEffort", this.getCalendarYearEffort());
        return hashMap;
    }
    
}