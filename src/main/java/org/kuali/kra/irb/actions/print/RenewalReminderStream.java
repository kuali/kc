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
package org.kuali.kra.irb.actions.print;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.print.CommitteeXmlStream;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.printing.xmlstream.PrintBaseXmlStream;

import edu.mit.irb.irbnamespace.RenewalReminderDocument;
import edu.mit.irb.irbnamespace.CommitteeMasterDataDocument.CommitteeMasterData;
import edu.mit.irb.irbnamespace.NextScheduleDateDocument.NextScheduleDate;
import edu.mit.irb.irbnamespace.RenewalReminderDocument.RenewalReminder;

/**
 * This class...
 */
public class RenewalReminderStream extends PrintBaseXmlStream {
    private ProtocolXmlStream protocolXmlStream;
    private CommitteeXmlStream committeeXmlStream;

    /**
     * @see org.kuali.kra.printing.xmlstream.XmlStream#generateXmlStream(KraPersistableBusinessObjectBase, java.util.Map)
     */
    public Map<String, XmlObject> generateXmlStream(KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
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
       for (ProtocolAction action : protocol.getProtocolActions()) {
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
