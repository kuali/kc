/*
 * RenewalReminderStream.java
 *
 * Created on March 26, 2004, 2:33 PM
 */

package edu.mit.coeus.utils.xml.generator;


import java.util.* ;
import java.math.BigInteger;

import edu.mit.coeus.irb.bean.* ;
import edu.mit.coeus.utils.xml.bean.schedule.ObjectFactory;
import edu.mit.coeus.utils.dbengine.DBException;
import edu.mit.coeus.exception.CoeusException;
import edu.mit.coeus.utils.CoeusVector;
import edu.mit.coeus.utils.xml.bean.schedule.* ; 
import edu.mit.coeus.utils.DateUtils;
import edu.mit.coeus.utils.xml.generator.XMLGeneratorTxnBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RenewalReminderStream 
{
    ObjectFactory objFactory ;
    
    /** Creates a new instance of CorrespondenceStream */
    public RenewalReminderStream(ObjectFactory objFactory)
    {
        this.objFactory = objFactory ;
    }
    
    
    public RenewalReminderType getReminder(ProtocolActionsBean actionBean) throws CoeusException, DBException, javax.xml.bind.JAXBException
    {
      String protocolId = actionBean.getProtocolNumber() ;
      int submissionNumber = actionBean.getSubmissionNumber() ;
      String committeeId  = actionBean.getCommitteeId() ;
      int sequenceNumber = actionBean.getSequenceNumber() ;
      
      RenewalReminderType renewalReminder = objFactory.createRenewalReminder() ;
         
      // set the current date
      java.util.GregorianCalendar currDate = new java.util.GregorianCalendar();
      renewalReminder.setCurrentDate(currDate) ;
     
      
      //add Committee details
      CommitteeStream committeeStream = new CommitteeStream(objFactory) ;
      CommitteeMasterDataType committeeMasterData = committeeStream.getCommitteeMasterData(committeeId) ;
    
      renewalReminder.setCommitteeMasterData(committeeMasterData) ;
      
      //add next schedules
     
      XMLGeneratorTxnBean xMLGeneratorTxnBean = new XMLGeneratorTxnBean();
      
      Vector vDates = xMLGeneratorTxnBean.getNextScheduleDates(committeeId);
      //vDates is a vector of hashMaps containing nextScheduleNumbers and Dates
      HashMap hashDates = new HashMap();
      NextScheduleDateType nextScheduleDateType ;
      
     //try simple date formatter 
       
      java.sql.Date schDate = null ;
      String strDate;
      
      for (int index = 0; index < vDates.size() ; index++) {
         hashDates = (HashMap) vDates.get(index);
         
         schDate = (java.sql.Date) hashDates.get("SCHEDULE_DATE");
         strDate = schDate.toString();
         nextScheduleDateType = objFactory.createNextScheduleDateType();
         nextScheduleDateType.setScheduleDate(
           convertDateStringToCalendar(strDate)); 
         nextScheduleDateType.setScheduleNumber(Integer.parseInt(hashDates.get("SCHEDULE_NUMBER").toString()));
      
         renewalReminder.getNextScheduleDate().add(nextScheduleDateType);
       }

      // add protocol details
     ProtocolStream protocolStream = new ProtocolStream(objFactory) ;
     if (submissionNumber <= 0)
     {    
        renewalReminder.setProtocol(protocolStream.getProtocol(protocolId, sequenceNumber)) ;
     }
     else
     {
         renewalReminder.setProtocol(protocolStream.getProtocol(protocolId, sequenceNumber, submissionNumber)) ;
     }    
           
      return renewalReminder ;
      
    }
    
       private Calendar convertDateStringToCalendar(String dateStr)
    {
        java.util.GregorianCalendar calDate = new java.util.GregorianCalendar();
        DateUtils dtUtils = new DateUtils();
        if (dateStr != null)
        {    
          
            if (dateStr.indexOf('-')!= -1)
            { // if the format obtd is YYYY-MM-DD
              dateStr = dtUtils.formatDate(dateStr,"MM/dd/yyyy");
            }    
            calDate.set(Integer.parseInt(dateStr.substring(6,10)),
                        Integer.parseInt(dateStr.substring(0,2)) - 1,
                        Integer.parseInt(dateStr.substring(3,5))) ;
            
            return calDate ;
        }
        return null ;
     }
    public RenewalReminderType getReminder(edu.mit.coeus.iacuc.bean.ProtocolActionsBean actionBean) throws CoeusException, DBException, javax.xml.bind.JAXBException
    {
      String protocolId = actionBean.getProtocolNumber() ;
      int submissionNumber = actionBean.getSubmissionNumber() ;
      String committeeId  = actionBean.getCommitteeId() ;
      int sequenceNumber = actionBean.getSequenceNumber() ;
      
      RenewalReminderType renewalReminder = objFactory.createRenewalReminder() ;
         
      // set the current date
      java.util.GregorianCalendar currDate = new java.util.GregorianCalendar();
      renewalReminder.setCurrentDate(currDate) ;
     
      
      //add Committee details
      CommitteeStream committeeStream = new CommitteeStream(objFactory) ;
      CommitteeMasterDataType committeeMasterData = committeeStream.getCommitteeMasterData(committeeId) ;
    
      renewalReminder.setCommitteeMasterData(committeeMasterData) ;
      
      //add next schedules
     
      XMLGeneratorTxnBean xMLGeneratorTxnBean = new XMLGeneratorTxnBean();
      
      Vector vDates = xMLGeneratorTxnBean.getNextScheduleDates(committeeId);
      //vDates is a vector of hashMaps containing nextScheduleNumbers and Dates
      HashMap hashDates = new HashMap();
      NextScheduleDateType nextScheduleDateType ;
      
     //try simple date formatter 
       
      java.sql.Date schDate = null ;
      String strDate;
      
      for (int index = 0; index < vDates.size() ; index++) {
         hashDates = (HashMap) vDates.get(index);
         
         schDate = (java.sql.Date) hashDates.get("SCHEDULE_DATE");
         strDate = schDate.toString();
         nextScheduleDateType = objFactory.createNextScheduleDateType();
         nextScheduleDateType.setScheduleDate(
           convertDateStringToCalendar(strDate)); 
         nextScheduleDateType.setScheduleNumber(Integer.parseInt(hashDates.get("SCHEDULE_NUMBER").toString()));
      
         renewalReminder.getNextScheduleDate().add(nextScheduleDateType);
       }

      // add protocol details
     ProtocolStream protocolStream = new ProtocolStream(objFactory) ;
     if (submissionNumber <= 0)
     {    
        renewalReminder.setProtocol(protocolStream.getProtocol(protocolId, sequenceNumber)) ;
     }
     else
     {
         renewalReminder.setProtocol(protocolStream.getProtocol(protocolId, sequenceNumber, submissionNumber)) ;
     }    
           
      return renewalReminder ;
      
    }
}
