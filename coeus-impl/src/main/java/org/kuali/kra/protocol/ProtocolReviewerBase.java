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
package org.kuali.kra.protocol;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

public class ProtocolReviewerBase extends KcPersistableBusinessObjectBase {

    private Long protocolIdFk;

    private Long submissionIdFk;

    private boolean nonEmployeeFlag;

    private String personId;

    private Integer rolodexId;

    private Rolodex rolodex;

    private ProtocolBase protocol;

    private ProtocolSubmissionBase protocolSubmission;

    //transient fields for the services, and the  
    //kcPerson.  
    private transient KcPersonService kcPersonService;

    private transient KcPerson kcPerson;

    public Long getProtocolIdFk() {
        return protocolIdFk;
    }

    public void setProtocolIdFk(Long protocolIdFk) {
        this.protocolIdFk = protocolIdFk;
    }

    public Long getSubmissionIdFk() {
        return submissionIdFk;
    }

    public void setSubmissionIdFk(Long submissionIdFk) {
        this.submissionIdFk = submissionIdFk;
    }

    public boolean getNonEmployeeFlag() {
        return nonEmployeeFlag;
    }

    public void setNonEmployeeFlag(boolean nonEmployeeFlag) {
        this.nonEmployeeFlag = nonEmployeeFlag;
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

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    public ProtocolBase getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolBase protocol) {
        this.protocol = protocol;
    }

    public ProtocolSubmissionBase getProtocolSubmission() {
        return protocolSubmission;
    }

    public void setProtocolSubmission(ProtocolSubmissionBase protocolSubmission) {
        this.protocolSubmission = protocolSubmission;
    }

    public KcPerson getPerson() {
        if (kcPerson == null) {
                kcPerson = getKcPersonService().getKcPersonByPersonId(this.personId);
        }
        return kcPerson;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    /**
     * Convenience method - is the CommitteeMembershipBase and this ProtocolReviewerBase the
     * same person?  Does the check properly against the person or rolodex as required.
     * 
     * @param member
     * @return
     */
    public boolean isProtocolReviewerFromCommitteeMembership(CommitteeMembershipBase member) {
        boolean isMatched = false;
        if (!getNonEmployeeFlag() && StringUtils.equals(member.getPersonId(), getPersonId())) {
            isMatched = true;
        } else if (getNonEmployeeFlag() && ObjectUtils.equals(member.getRolodexId(), getRolodexId())) {
            isMatched = true;
        }
        return isMatched;
    }

    /**
     * Convenience method - compares the provided personId with the rolodex id or the personId
     * of this record appropriately.
     * @param personId
     * @return
     */
    public boolean isPersonIdProtocolReviewer(String personId, boolean nonEmployeeFlag) {
        boolean result = false;
        if ((nonEmployeeFlag == getNonEmployeeFlag()) && ((getNonEmployeeFlag() && StringUtils.equals(getRolodexId() == null ? null : getRolodexId().toString(), personId) && (getNonEmployeeFlag() == nonEmployeeFlag)) || (!getNonEmployeeFlag() && StringUtils.equals(personId, this.personId)))) {
            result = true;
        }
        return result;
    }

    public String getFullName() {
        if (nonEmployeeFlag && rolodexId != null) {
            refreshReferenceObject("rolodex");
            return getRolodex().getFullName();
        } else if (getPerson() != null) {
            return getPerson().getFullName();
        } else {
            return null;
        }
    }
}
