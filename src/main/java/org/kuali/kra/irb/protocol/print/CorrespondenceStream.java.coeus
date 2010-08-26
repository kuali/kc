/*
 * CorrespondenceStream.java
 *
 * Created on November 24, 2003, 4:28 PM
 */

package edu.mit.coeus.utils.xml.generator;

import java.util.* ;
import java.math.BigInteger;

import edu.mit.coeus.irb.bean.* ;
import edu.mit.coeus.utils.xml.bean.schedule.ObjectFactory;
import edu.mit.coeus.utils.dbengine.DBException;
import edu.mit.coeus.exception.CoeusException;
import edu.mit.coeus.utils.xml.bean.schedule.* ; 
import edu.mit.coeus.utils.DateUtils;
import edu.mit.coeus.utils.UtilFactory;
import java.sql.Timestamp;
import java.util.TimeZone;


public class CorrespondenceStream
{
    ObjectFactory objFactory ;
    
    /** Creates a new instance of CorrespondenceStream */
    public CorrespondenceStream(ObjectFactory objFactory)
    {
        this.objFactory = objFactory ;
    }
    
    
    public CorrespondenceType getCorrespondence(ProtocolActionsBean actionBean) throws CoeusException, DBException, javax.xml.bind.JAXBException
    {
      String protocolId = actionBean.getProtocolNumber() ;
      int submissionNumber = actionBean.getSubmissionNumber() ;
      String committeeId  = actionBean.getCommitteeId() ;
      int sequenceNumber = actionBean.getSequenceNumber() ;
      
      CorrespondenceType correspondence = objFactory.createCorrespondence() ;
        
      // set the current date
      /*
       * Fix for setting the local timezone date
       * 23-Feb-2006
       */
//      java.util.GregorianCalendar currDate = new java.util.GregorianCalendar();
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(UtilFactory.getLocalTimeZoneId()));
      correspondence.setCurrentDate(cal);
          
      // add protocol details
      ProtocolStream protocolStream = new ProtocolStream(objFactory) ;
     if (submissionNumber <= 0)
     {    
        correspondence.setProtocol(protocolStream.getProtocol(protocolId, sequenceNumber)) ;
     }
     else
     {
         correspondence.setProtocol(protocolStream.getProtocol(protocolId, sequenceNumber, submissionNumber)) ;
     }    
           
      return correspondence ;
      
    }
        public CorrespondenceType getCorrespondence(edu.mit.coeus.iacuc.bean.ProtocolActionsBean actionBean) throws CoeusException, DBException, javax.xml.bind.JAXBException
    {
      String protocolId = actionBean.getProtocolNumber() ;
      int submissionNumber = actionBean.getSubmissionNumber() ;
      String committeeId  = actionBean.getCommitteeId() ;
      int sequenceNumber = actionBean.getSequenceNumber() ;
      
      CorrespondenceType correspondence = objFactory.createCorrespondence() ;
        
      // set the current date
      /*
       * Fix for setting the local timezone date
       * 23-Feb-2006
       */
//      java.util.GregorianCalendar currDate = new java.util.GregorianCalendar();
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(UtilFactory.getLocalTimeZoneId()));
      correspondence.setCurrentDate(cal);
          
      // add protocol details
      ProtocolStream protocolStream = new ProtocolStream(objFactory) ;
     if (submissionNumber <= 0)
     {    
        correspondence.setProtocol(protocolStream.getProtocol(protocolId, sequenceNumber)) ;
     }
     else
     {
         correspondence.setProtocol(protocolStream.getProtocol(protocolId, sequenceNumber, submissionNumber)) ;
     }    
           
      return correspondence ;
      
    }  
//      correspondence.setCurrentDate(cal);

    
    
}
