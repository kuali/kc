/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.service.KcPersonService;

public class ProtocolReviewerBase extends KraPersistableBusinessObjectBase {

    private Long protocolIdFk;

    private Long submissionIdFk;

    private boolean nonEmployeeFlag;

    private String personId;

    private Integer rolodexId;

    private Rolodex rolodex;

    private Protocol protocol;

    private ProtocolSubmission protocolSubmission;

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

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public ProtocolSubmission getProtocolSubmission() {
        return protocolSubmission;
    }

    public void setProtocolSubmission(ProtocolSubmission protocolSubmission) {
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
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    /**
     * Convenience method - is the CommitteeMembership and this ProtocolReviewerBase the
     * same person?  Does the check properly against the person or rolodex as required.
     * 
     * @param member
     * @return
     */
    public boolean isProtocolReviewerFromCommitteeMembership(CommitteeMembership member) {
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
        if (nonEmployeeFlag && getRolodex() != null) {
            return getRolodex().getFullName();
        } else if (getPerson() != null) {
            return getPerson().getFullName();
        } else {
            return null;
        }
    }
}
