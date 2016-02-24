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
package org.kuali.kra.irb.actions.print;

import edu.mit.irb.irbnamespace.CommitteeMasterDataDocument.CommitteeMasterData;
import edu.mit.irb.irbnamespace.NextScheduleDateDocument.NextScheduleDate;
import edu.mit.irb.irbnamespace.RenewalReminderDocument;
import edu.mit.irb.irbnamespace.RenewalReminderDocument.RenewalReminder;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.print.CommitteeXmlStream;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.print.RenewalReminderStreamBase;

import java.sql.Date;
import java.util.*;


public class RenewalReminderStream extends RenewalReminderStreamBase {
    private ProtocolXmlStream protocolXmlStream;
    private CommitteeXmlStream committeeXmlStream;

    @Override
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
        Protocol protocol = (Protocol)printableBusinessObject;
        RenewalReminderDocument renewalReminderDocument = RenewalReminderDocument.Factory.newInstance() ;
        RenewalReminder renewalReminder = RenewalReminder.Factory.newInstance() ;
        renewalReminder.setCurrentDate(getDateTimeService().getCurrentCalendar()) ;
        String committeeId = (String)reportParameters.get("committeeId");
        Committee committee = null;
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("committeeId", committeeId);
        Collection<Committee> committees = getBusinessObjectService().findMatching(Committee.class, fieldValues);
        if (committees.size() > 0) {
            /*
             * Return the most recent approved committee (i.e. the committee version with the highest 
             * sequence number that is approved/in the database).
             */
            committee = (Committee) Collections.max(committees);
        }
        CommitteeMasterData committeeMasterData = CommitteeMasterData.Factory.newInstance();
        committeeXmlStream.setCommitteeMasterData(committee,committeeMasterData) ;
        renewalReminder.setCommitteeMasterData(committeeMasterData) ;
        List<CommitteeSchedule> committeSchedules = committee.getCommitteeSchedules();
        int rowNumber = 0;
        for (CommitteeSchedule committeeSchedule : committeSchedules) {
            if(rowNumber<5 ) break;
            if(committeeSchedule.getScheduledDate().after(getDateTimeService().getCurrentDate()) ||
                    committeeSchedule.getScheduledDate().equals(getDateTimeService().getCurrentDate())){
                ++rowNumber;
                NextScheduleDate nextScheduleDateType = renewalReminder.addNewNextScheduleDate();
                nextScheduleDateType.setScheduleDate(getDateTimeService().getCalendar(committeeSchedule.getScheduledDate()));
                nextScheduleDateType.setScheduleNumber(rowNumber);
            }
        }

        if (reportParameters.get("protoCorrespTypeCode") != null &&
                ("23".equals((String)reportParameters.get("protoCorrespTypeCode")) || "24".equals((String)reportParameters.get("protoCorrespTypeCode")))){  
            setActionDate(protocol);
        }

       if (reportParameters.get("submissionNumber") ==null ){    
          renewalReminder.setProtocol(protocolXmlStream.getProtocol(protocol)) ;
       }else{
           renewalReminder.setProtocol(protocolXmlStream.getProtocol(protocol,
                                                   (Integer)reportParameters.get("submissionNumber"))) ;
       }
       Map<String,XmlObject> xmlObjectMap = new HashMap<String,XmlObject>();
       renewalReminderDocument.setRenewalReminder(renewalReminder);
       xmlObjectMap.put("Renewal reminder", renewalReminderDocument);
       return xmlObjectMap;
    }

    private void setActionDate(Protocol protocol) {
       for (ProtocolActionBase action : protocol.getProtocolActions()) {
           if (ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED.equals(action.getProtocolActionTypeCode()) ||
                   ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED.equals(action.getProtocolActionTypeCode())) {
               protocol.setExpirationDate(new Date(action.getActionDate().getTime()));
           }
           
       }
    }
    
    public void setProtocolXmlStream(ProtocolXmlStream protocolXmlStream) {
        this.protocolXmlStream = protocolXmlStream;
    }

    public void setCommitteeXmlStream(CommitteeXmlStream committeeXmlStream) {
        this.committeeXmlStream = committeeXmlStream;
    }
}
