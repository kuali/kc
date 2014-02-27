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
package org.kuali.kra.committee.print;

import edu.mit.irb.irbnamespace.PersonDocument.Person;
import edu.mit.irb.irbnamespace.ProtocolDocument.Protocol.Submissions;
import edu.mit.irb.irbnamespace.ProtocolSubmissionDocument.ProtocolSubmission;
import edu.mit.irb.irbnamespace.ScheduleDocument.Schedule;
import edu.mit.irb.irbnamespace.SubmissionDetailsDocument.SubmissionDetails;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolPersonRolodex;

/**
 * This class has different helper methods to populate data for Person XML data.
 */
public interface IrbPrintXmlUtilService {
    public void setPersonXml(KcPerson person, Person personType);
    public void setPersonXml(ProtocolPersonRolodex rolodex, Person personType);
    public void setPersonRolodexType(ProtocolPerson protocolPerson, Person personType);
    public void setProtocolSubmissionAction(org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission,
            SubmissionDetails protocolSubmissionDetail);
    public void setSubmissionCheckListinfo(org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission,
            SubmissionDetails protocolSubmissionDetail);
    public void setMinutes(CommitteeSchedule scheduleDetailsBean, Schedule schedule);
    public void setProcotolMinutes(CommitteeSchedule committeeSchedule, 
            org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission, ProtocolSubmission protocolSubmissionType);
    public void setProcotolSubmissionMinutes(CommitteeSchedule committeeSchedule,
            org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission, Submissions submissionsType);
    
    public void setProtocolReviewMinutes(CommitteeSchedule committeeSchedule,
            org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission, Submissions submissionsType);
    
}
