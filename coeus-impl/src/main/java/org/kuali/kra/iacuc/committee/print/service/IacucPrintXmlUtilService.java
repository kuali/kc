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
    
    void setPersonXml(KcPerson person, PersonType personType);
    void setPersonXml(ProtocolPersonRolodexBase rolodex, PersonType personType);
    void setPersonRolodexType(ProtocolPersonBase protocolPerson, PersonType personType);
    
    void setProtocolSubmissionAction(IacucProtocolSubmission protocolSubmission,
            SubmissionDetailsType protocolSubmissionDetail);
    void setSubmissionCheckListinfo(org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase protocolSubmission,
            SubmissionDetailsType protocolSubmissionDetail);
    void setMinutes(CommitteeScheduleBase scheduleDetailsBean, ScheduleType schedule);
    void setProcotolMinutes(CommitteeScheduleBase committeeSchedule,
            org.kuali.kra.protocol.actions.submit.ProtocolSubmissionLiteBase protocolSubmission, ProtocolSubmissionType protocolSubmissionType);
    
    void setProtocolReviewMinutes(CommitteeScheduleBase committeeSchedule,
            org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase protocolSubmission, Submissions submissionsType);
    
}
