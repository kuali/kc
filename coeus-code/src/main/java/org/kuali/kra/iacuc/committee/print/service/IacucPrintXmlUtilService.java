/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.committee.print.service;

import edu.mit.coeus.xml.iacuc.PersonType;
import edu.mit.coeus.xml.iacuc.ProtocolSubmissionType;
import edu.mit.coeus.xml.iacuc.ProtocolType.Submissions;
import edu.mit.coeus.xml.iacuc.ScheduleType;
import edu.mit.coeus.xml.iacuc.SubmissionDetailsType;

import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonRolodexBase;

/**
 * This class has different helper methods to populate data for Person XML data.
 */
public interface IacucPrintXmlUtilService {
    
    public void setPersonXml(KcPerson person, PersonType personType);
    public void setPersonXml(ProtocolPersonRolodexBase rolodex, PersonType personType);
    public void setPersonRolodexType(ProtocolPersonBase protocolPerson, PersonType personType);   
    
    public void setProtocolSubmissionAction(IacucProtocolSubmission protocolSubmission,
            SubmissionDetailsType protocolSubmissionDetail);
    public void setSubmissionCheckListinfo(org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase protocolSubmission,
            SubmissionDetailsType protocolSubmissionDetail);
    public void setMinutes(CommitteeScheduleBase scheduleDetailsBean, ScheduleType schedule);
    public void setProcotolMinutes(CommitteeScheduleBase committeeSchedule, 
            org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase protocolSubmission, ProtocolSubmissionType protocolSubmissionType);
    public void setProcotolSubmissionMinutes(CommitteeScheduleBase committeeSchedule,
            ProtocolSubmissionBase protocolSubmission, Submissions submissionsType);
    
    public void setProtocolReviewMinutes(CommitteeScheduleBase committeeSchedule,
            org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase protocolSubmission, Submissions submissionsType);
    
}
