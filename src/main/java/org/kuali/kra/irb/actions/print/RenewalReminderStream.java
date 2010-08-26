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

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.print.CommitteeXmlStream;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.printing.xmlstream.PrintBaseXmlStream;
import org.kuali.rice.core.util.CollectionUtils;

import edu.mit.irb.irbnamespace.CommitteeMasterDataDocument.CommitteeMasterData;
import edu.mit.irb.irbnamespace.NextScheduleDateDocument.NextScheduleDate;
import edu.mit.irb.irbnamespace.RenewalReminderDocument.RenewalReminder;

/**
 * This class...
 */
public class RenewalReminderStream extends PrintBaseXmlStream {

    /**
     * @see org.kuali.kra.printing.xmlstream.XmlStream#generateXmlStream(KraPersistableBusinessObjectBase, java.util.Map)
     */
    public Map<String, XmlObject> generateXmlStream(KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
        Protocol protocol = (Protocol)printableBusinessObject;
        RenewalReminder renewalReminder = RenewalReminder.Factory.newInstance() ;
        renewalReminder.setCurrentDate(getDateTimeService().getCurrentCalendar()) ;
        String committeeId = (String)reportParameters.get("committeeId");
        Committee committee = getBusinessObjectService().findBySinglePrimaryKey(Committee.class, committeeId);
        CommitteeXmlStream committeeStream = new CommitteeXmlStream() ;
        CommitteeMasterData committeeMasterData = CommitteeMasterData.Factory.newInstance();
        committeeStream.setCommitteeMasterData(committee,committeeMasterData) ;
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

       ProtocolXmlStream protocolStream = new ProtocolXmlStream();
       if (reportParameters.get("submissionNumber") ==null ){    
          renewalReminder.setProtocol(protocolStream.getProtocol(protocol)) ;
       }else{
           renewalReminder.setProtocol(protocolStream.getProtocol(protocol,
                                                   (Integer)reportParameters.get("submissionNumber"))) ;
       }
       Map<String,XmlObject> xmlObjectMap = new HashMap<String,XmlObject>();
       xmlObjectMap.put("Renewal reminder", renewalReminder);
       return xmlObjectMap;
    }
}
