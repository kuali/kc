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
            org.kuali.kra.irb.actions.submit.ProtocolSubmissionLite protocolSubmission, ProtocolSubmission protocolSubmissionType);
    public void setProcotolSubmissionMinutes(CommitteeSchedule committeeSchedule,
            org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission, Submissions submissionsType);
    
    public void setProtocolReviewMinutes(CommitteeSchedule committeeSchedule,
            org.kuali.kra.irb.actions.submit.ProtocolSubmission protocolSubmission, Submissions submissionsType);
    
}
