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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.rice.krad.service.SequenceAccessorService;

public class CoiDiscDetail extends KcPersistableBusinessObjectBase implements Comparable<CoiDiscDetail> {
    

    private static final long serialVersionUID = 779054686380799255L;
    private Long coiDiscDetailId; 
    private Long coiDisclosureId; 
    private Long coiDisclProjectId;
    private String coiDisclosureNumber; 
    private Integer sequenceNumber; 
    private Integer coiDiscDetailNumber; 
    private String moduleCode; 
    private String moduleItemKey; 
    // TODO : since the synthetic key 'personFinIntDisclosureId' is added.  ok to remove entitynumber/entitysequence ?
    private String entityNumber; 
    private Integer entitySequenceNumber; 
    private Integer entityDispositionCode; 
    // Future: keeping track of old entity status, for when we track who changes it during reviews
    private Integer oldEntityDispositionCode; 
    private String description; 
    private String comments; 
    private Long personFinIntDisclosureId;
    // for master disclosure.  if this is not null, then it is copied from previous master disclosure
    private Long copiedCoiDiscDetailId; 
    private PersonFinIntDisclosure personFinIntDisclosure;
    private CoiDispositionStatus coiEntityDispositionStatus; 
    private CoiDisclosure coiDisclosure; 
    // originalxxxx is for master disclosure ui bean
    private Long originalCoiDisclosureId;
    @SkipVersioning
    private CoiDisclosure originalCoiDisclosure; 
    private CoiDisclProject coiDisclProject;
    // TODO : in general this projectType is same as disclosure eventtypecode. Can this be replaced by modulecode ?
    // the only exception is annual event which may have several project type.
    // this is also used for sorting, so annual discl cSan be in proper order, then they can be moved to 
    // eventbo.
    private String projectType; 
    // fk to projects.  easire to retrieve project, especially projects are versioned.  moduleitemkey is not enough
    private String projectIdFk; 
    // transient data for UI
    // TODO : investigate to come up with super class/interface for disclose projects (PD/PROTOCOL/AWD) purpose ?
    
    public CoiDiscDetail() { 

    } 
    
    public CoiDiscDetail(PersonFinIntDisclosure personFinIntDisclosure) { 
        this.setPersonFinIntDisclosureId(personFinIntDisclosure.getPersonFinIntDisclosureId());
        this.setPersonFinIntDisclosure(personFinIntDisclosure);
        this.setEntityNumber(personFinIntDisclosure.getEntityNumber());
        this.setEntitySequenceNumber(personFinIntDisclosure.getSequenceNumber());
        // TODO : not sure about disclosuredetailnumber & expirationdate
        Long nextNumber = KcServiceLocator.getService(SequenceAccessorService.class).getNextAvailableSequenceNumber("SEQ_COI_DISC_DETAILS_ID", getClass());
        this.setCoiDiscDetailNumber(nextNumber.intValue());

    } 

    public Long getCoiDiscDetailId() {
        return coiDiscDetailId;
    }

    public void setCoiDiscDetailId(Long coiDiscDetailId) {
        this.coiDiscDetailId = coiDiscDetailId;
    }

    public Long getCoiDisclosureId() {
        return coiDisclosureId;
    }

    public void setCoiDisclosureId(Long coiDisclosureId) {
        this.coiDisclosureId = coiDisclosureId;
    }

    public Long getCoiDisclProjectId() {
        return coiDisclProjectId;
    }

    public void setCoiDisclProjectId(Long coiDisclProjectId) {
        this.coiDisclProjectId = coiDisclProjectId;
    }

    public String getCoiDisclosureNumber() {
        return coiDisclosureNumber;
    }

    public void setCoiDisclosureNumber(String coiDisclosureNumber) {
        this.coiDisclosureNumber = coiDisclosureNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getCoiDiscDetailNumber() {
        return coiDiscDetailNumber;
    }

    public void setCoiDiscDetailNumber(Integer coiDiscDetailNumber) {
        this.coiDiscDetailNumber = coiDiscDetailNumber;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleItemKey() {
        return moduleItemKey;
    }

    public void setModuleItemKey(String moduleItemKey) {
        this.moduleItemKey = moduleItemKey;
    }

    public String getEntityNumber() {
        return entityNumber;
    }

    public void setEntityNumber(String entityNumber) {
        this.entityNumber = entityNumber;
    }

    public Integer getEntitySequenceNumber() {
        return entitySequenceNumber;
    }

    public void setEntitySequenceNumber(Integer entitySequenceNumber) {
        this.entitySequenceNumber = entitySequenceNumber;
    }

    public Integer getEntityDispositionCode() {
        return entityDispositionCode;
    }

    public void setEntityDispositionCode(Integer entityDispositionCode) {
        this.entityDispositionCode = entityDispositionCode;
    }

    public Integer getOldEntityDispositionCode() {
        return oldEntityDispositionCode;
    }

    public void setOldEntityDispositionCode() {
        oldEntityDispositionCode = entityDispositionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public CoiDispositionStatus getCoiEntityDispositionStatus() {
        return coiEntityDispositionStatus;
    }

    public void setCoiEntityDispositionStatus(CoiDispositionStatus coiEntityDispositionStatus) {
        this.coiEntityDispositionStatus = coiEntityDispositionStatus;
    }

    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }

    public Long getPersonFinIntDisclosureId() {
        return personFinIntDisclosureId;
    }

    public void setPersonFinIntDisclosureId(Long personFinIntDisclosureId) {
        this.personFinIntDisclosureId = personFinIntDisclosureId;
    }

    public PersonFinIntDisclosure getPersonFinIntDisclosure() {
        return personFinIntDisclosure;
    }

    public void setPersonFinIntDisclosure(PersonFinIntDisclosure personFinIntDisclosure) {
        this.personFinIntDisclosure = personFinIntDisclosure;
    }

    public int compareTo(CoiDiscDetail other) {
        int result = 0;
        if (other != null) {
            if (StringUtils.equals(projectType, other.getProjectType())) {
                if (StringUtils.equals(moduleItemKey, other.getModuleItemKey())) {
                    result = personFinIntDisclosureId.compareTo(other.getPersonFinIntDisclosureId());
                }
                else {
                    result = moduleItemKey.compareTo(other.getModuleItemKey());
                }
            }
            else {
                result = projectType.compareTo(other.getProjectType());
            }
        }
        return result;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public boolean isProposalEvent() {
        return StringUtils.equals(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, this.projectType);
    }
    
    public boolean isInstitutionalProposalEvent() {
        return StringUtils.equals(CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL, this.projectType);
    }
    
    public boolean isAwardEvent() {
        return StringUtils.equals(CoiDisclosureEventType.AWARD, this.projectType);
    }
    
    public boolean isProtocolEvent() {
        return StringUtils.equals(CoiDisclosureEventType.IRB_PROTOCOL, this.projectType);
    }

    public boolean isIacucProtocolEvent() {
        return StringUtils.equals(CoiDisclosureEventType.IACUC_PROTOCOL, this.projectType);
    }

    public boolean isManualAwardEvent() {
        return StringUtils.equals(CoiDisclosureEventType.MANUAL_AWARD, this.projectType);
    }

    public boolean isManualProposalEvent() {
        return StringUtils.equals(CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL, this.projectType);
    }

    public boolean isManualProtocolEvent() {
        return StringUtils.equals(CoiDisclosureEventType.MANUAL_IRB_PROTOCOL, this.projectType);
    }

    public boolean isManualIacucProtocolEvent() {
        return StringUtils.equals(CoiDisclosureEventType.MANUAL_IACUC_PROTOCOL, this.projectType);
    }


    public boolean isManualEvent() {
        return isManualAwardEvent() || isManualProposalEvent() || isManualProtocolEvent();
    }

    public String getProjectIdFk() {
        return projectIdFk;
    }

    public void setProjectIdFk(String projectIdFk) {
        this.projectIdFk = projectIdFk;
    }

    public Long getCopiedCoiDiscDetailId() {
        return copiedCoiDiscDetailId;
    }

    public void setCopiedCoiDiscDetailId(Long copiedCoiDiscDetailId) {
        this.copiedCoiDiscDetailId = copiedCoiDiscDetailId;
    }

    public Long getOriginalCoiDisclosureId() {
        return originalCoiDisclosureId;
    }

    public void setOriginalCoiDisclosureId(Long originalCoiDisclosureId) {
        this.originalCoiDisclosureId = originalCoiDisclosureId;
    }

    public CoiDisclosure getOriginalCoiDisclosure() {
        if (originalCoiDisclosureId != null && originalCoiDisclosure ==null) {
            this.refreshReferenceObject("originalCoiDisclosure");
        }
        return originalCoiDisclosure;
    }

    public void setOriginalCoiDisclosure(CoiDisclosure originalCoiDisclosure) {
        this.originalCoiDisclosure = originalCoiDisclosure;
    }

    public CoiDisclProject getCoiDisclProject() {
        return coiDisclProject;
    }

    public void setCoiDisclProject(CoiDisclProject coiDisclProject) {
        this.coiDisclProject = coiDisclProject;
    }


}
