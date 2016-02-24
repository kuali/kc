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

import edu.mit.irb.irbnamespace.CommitteeDocument.Committee;
import edu.mit.irb.irbnamespace.CommitteeMasterDataDocument.CommitteeMasterData;
import edu.mit.irb.irbnamespace.CommitteeMemberDocument.CommitteeMember;
import edu.mit.irb.irbnamespace.CommitteeMemberRoleDocument.CommitteeMemberRole;
import edu.mit.irb.irbnamespace.PersonDocument.Person;
import edu.mit.irb.irbnamespace.ProtocolDocument.Protocol.Submissions;
import edu.mit.irb.irbnamespace.ResearchAreaDocument.ResearchArea;
import edu.mit.irb.irbnamespace.ScheduleDocument.Schedule;
import edu.mit.irb.irbnamespace.ScheduleDocument.Schedule.NextSchedule;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipExpertiseBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeResearchAreaBase;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.print.stream.xml.PrintBaseXmlStream;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.irb.personnel.ProtocolPersonRolodex;

import java.math.BigInteger;
import java.util.*;

/**
 * This class generates XML that confirms with the XSD related to 
 * committee reports. The data for XML is derived from
 * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and {@link Map} of details passed to the class.
 */
public class CommitteeXmlStream extends PrintBaseXmlStream {

    private ScheduleXmlStream scheduleXmlStream;
    private IrbPrintXmlUtilService irbPrintXmlUtilService;
    /**
     * This method generates XML committee report. It uses data passed in
     * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML nodes. The XMl once
     * generated is returned as {@link XmlObject}
     * 
     * @param printableBusinessObject
     *            using which XML is generated
     * @param reportParameters
     *            parameters related to XML generation
     * @return {@link XmlObject} representing the XML
     */
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
        org.kuali.kra.committee.bo.Committee committee = (org.kuali.kra.committee.bo.Committee)printableBusinessObject;
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
        edu.mit.irb.irbnamespace.CommitteeDocument committeeDocumentType = edu.mit.irb.irbnamespace.CommitteeDocument.Factory.newInstance();
        committeeDocumentType.setCommittee(getCommitteeCompleteDetails(committee));
        xmlObjectList.put("Committee", committeeDocumentType);
        return xmlObjectList;
    }
    public Committee getCommitteeCompleteDetails(org.kuali.kra.committee.bo.Committee committee)  {
        Committee committeeType = Committee.Factory.newInstance();
        setCommitteeMasterData(committee,committeeType.addNewCommitteeMasterData()) ;
        setCommitteeMembers(committee,committeeType);
        setScheduleForcommittee(committee,committeeType) ;
        setCommitteeResearchArea(committee,committeeType) ;
        return committeeType ;
    }
    private void setCommitteeResearchArea(org.kuali.kra.committee.bo.Committee committee, Committee committeeType) {
        List<CommitteeResearchAreaBase> committeeResearchAreas = committee.getCommitteeResearchAreas();
        if(committeeResearchAreas.isEmpty()) return;
        for (CommitteeResearchAreaBase committeeResearchArea : committeeResearchAreas) {
            edu.mit.irb.irbnamespace.ResearchAreaDocument.ResearchArea researchArea = committeeType.addNewResearchArea();
            researchArea.setResearchAreaCode(committeeResearchArea.getResearchAreaCode()) ;
            researchArea.setResearchAreaDescription(committeeResearchArea.getResearchArea().getDescription()) ;
        }
    }

    private void setScheduleForcommittee(org.kuali.kra.committee.bo.Committee committee, Committee committeeType) {
        Date currentDate = new Date();
        Boolean isRooster=committee.getPrintRooster();
        List<CommitteeSchedule> vecSchedule = committee.getCommitteeSchedules();
        if (vecSchedule.isEmpty()) return;
        for (CommitteeSchedule scheduleDetailsBean : vecSchedule) {
           Date scheduleDate =  scheduleDetailsBean.getScheduledDate();
           int dateCount = scheduleDate.compareTo(currentDate);
           if(isRooster){
             Schedule scheduleType = committeeType.addNewSchedule();
            getScheduleXmlStream().setScheduleMasterData(scheduleDetailsBean,scheduleType.addNewScheduleMasterData()) ;
            NextSchedule nextSchedule = scheduleType.addNewNextSchedule();
            getScheduleXmlStream().setNextSchedule(scheduleDetailsBean,nextSchedule.addNewScheduleMasterData());
        }
           if(!isRooster){
               if(dateCount>0){
                   Schedule scheduleType = committeeType.addNewSchedule();
                   getScheduleXmlStream().setScheduleMasterData(scheduleDetailsBean,scheduleType.addNewScheduleMasterData()) ;
                   NextSchedule nextSchedule = scheduleType.addNewNextSchedule();
                   getScheduleXmlStream().setNextSchedule(scheduleDetailsBean,nextSchedule.addNewScheduleMasterData());   
           }}}
    }

    public void setCommitteeMembers(org.kuali.kra.committee.bo.Committee committee, Committee committeeType) {
        List<CommitteeMembership> committeeMemberships = (List)committee.getCommitteeMemberships();
        if(committeeMemberships.isEmpty()) return;
        for (CommitteeMembership membershipBean : committeeMemberships) {
            CommitteeMember committeeMember = committeeType.addNewCommitteeMember();
            setCommitteeMembershipType(membershipBean, committeeMember);
        }
        
    }
    public void setCommitteeMembers(org.kuali.kra.committee.bo.Committee committee, Submissions committeeType) {
        List<CommitteeMembership> committeeMemberships = (List)committee.getCommitteeMemberships();
        if(committeeMemberships.isEmpty()) return;
        for (CommitteeMembership membershipBean : committeeMemberships) {
            CommitteeMember committeeMember = committeeType.addNewCommitteeMember();
            setCommitteeMembershipType(membershipBean, committeeMember);
        }
        
    }

    private void setCommitteeMembershipType(CommitteeMembership membershipBean, CommitteeMember committeeMember) {
        membershipBean.refreshNonUpdateableReferences();
        setPersonType(membershipBean,committeeMember);
        committeeMember.setMemberStatus(membershipBean.isActive()?"active":"inactive") ;
        committeeMember.setMemberStatusStartDt(Calendar.getInstance()); 
        committeeMember.setMemberStatusEndDt(Calendar.getInstance()) ; 
        if (membershipBean.getTermEndDate() != null){     
            committeeMember.setTermEnd(getDateTimeService().getCalendar(membershipBean.getTermEndDate()));  
        }
        if (membershipBean.getTermStartDate() != null){     
            committeeMember.setTermStart(getDateTimeService().getCalendar(membershipBean.getTermStartDate())) ;  
        }    
        if (membershipBean.getMembershipType() != null){
            committeeMember.setMemberType(membershipBean.getMembershipType().getDescription()) ;
        }
        committeeMember.setPaidMemberFlag(membershipBean.getPaidMember());
        List<CommitteeMembershipExpertiseBase> committeeMemResearchArea =  membershipBean.getMembershipExpertise();
        if (committeeMemResearchArea != null){
            for (CommitteeMembershipExpertiseBase committeeMemberExpertise : committeeMemResearchArea) {
                ResearchArea researchArea = committeeMember.addNewResearchArea();
                researchArea.setResearchAreaCode(committeeMemberExpertise.getResearchAreaCode()) ;
                if (committeeMemberExpertise.getResearchArea()!=null){
                    researchArea.setResearchAreaDescription(committeeMemberExpertise.getResearchArea().getDescription()) ;
                }
            }    
        }
        List<org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipRole> vecMemRoles = membershipBean.getMembershipRoles();
        if ( vecMemRoles != null){
            for (org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipRole committeeMembershipRole : vecMemRoles) {
                CommitteeMemberRole committeeMemRole = committeeMember.addNewCommitteeMemberRole();
                committeeMemRole.setMemberRoleCode(new BigInteger(String.valueOf(committeeMembershipRole.getMembershipRoleCode()))) ;
                if (committeeMembershipRole.getMembershipRole()!= null){
                    committeeMemRole.setMemberRoleDesc(committeeMembershipRole.getMembershipRole().getDescription()) ;
                }
                
                if (committeeMembershipRole.getStartDate() != null){    
                    committeeMemRole.setMemberRoleStartDt(getDateTimeService().getCalendar(committeeMembershipRole.getStartDate())) ;
                }    

                if (committeeMembershipRole.getEndDate() != null){     
                    committeeMemRole.setMemberRoleEndDt(getDateTimeService().getCalendar(committeeMembershipRole.getEndDate())) ;
                }    
            }
        }
    }

    private void setPersonType(CommitteeMembership membershipBean,CommitteeMember committeeMember) {
        Person person = committeeMember.addNewPerson();
        boolean employeeFlag = membershipBean.getPerson()!=null;
        person.setFacultyFlag(false) ; 
        person.setEmployeeFlag(!employeeFlag) ;
         if (employeeFlag ){
             KcPerson personBean = membershipBean.getPerson();
             getIrbPrintXmlUtilService().setPersonXml(personBean, person);
         }else{ 
            ProtocolPersonRolodex rolodexBean =  (ProtocolPersonRolodex) membershipBean.getRolodex();
            getIrbPrintXmlUtilService().setPersonXml(rolodexBean, person);
         }    
    }

    public void setCommitteeMasterData(org.kuali.kra.committee.bo.Committee committee,CommitteeMasterData committeeMasterData){
//      committee.refreshNonUpdateableReferences();
      committeeMasterData.setCommitteeId(committee.getCommitteeId()) ;
      committeeMasterData.setCommitteeName(committee.getCommitteeName()) ;
      committeeMasterData.setHomeUnitNumber(committee.getHomeUnitNumber()) ;
      committeeMasterData.setHomeUnitName(committee.getUnitName()) ;
      committeeMasterData.setCommitteeTypeCode(new BigInteger(String.valueOf(committee.getCommitteeTypeCode()))) ;
      committeeMasterData.setCommitteeTypeDesc(committee.getCommitteeType().getDescription()) ;
      committeeMasterData.setScheduleDescription(committee.getScheduleDescription()) ;
      committeeMasterData.setMinimumMembersRequired(new BigInteger(String.valueOf(committee.getMinimumMembersRequired()))) ;
      committeeMasterData.setMaxProtocols(new BigInteger(String.valueOf(committee.getMaxProtocols()))) ;
      committeeMasterData.setAdvSubmissionDays(new BigInteger(String.valueOf(committee.getAdvancedSubmissionDaysRequired()))) ;
        if(committee.getReviewType()!=null){
        committeeMasterData.setDefaultReviewTypeCode(new BigInteger(String.valueOf(committee.getReviewTypeCode()))) ;
          committeeMasterData.setDefaultReviewTypeDesc(committee.getReviewType().getDescription()) ;
      }
    }
    /**
     * Sets the scheduleXmlStream attribute value.
     * @param scheduleXmlStream The scheduleXmlStream to set.
     */
    public void setScheduleXmlStream(ScheduleXmlStream scheduleXmlStream) {
        this.scheduleXmlStream = scheduleXmlStream;
    }
    /**
     * Gets the scheduleXmlStream attribute. 
     * @return Returns the scheduleXmlStream.
     */
    public ScheduleXmlStream getScheduleXmlStream() {
        return scheduleXmlStream;
    }
    /**
     * Sets the irbPrintXmlUtilService attribute value.
     * @param irbPrintXmlUtilService The irbPrintXmlUtilService to set.
     */
    public void setIrbPrintXmlUtilService(IrbPrintXmlUtilService irbPrintXmlUtilService) {
        this.irbPrintXmlUtilService = irbPrintXmlUtilService;
    }
    /**
     * Gets the irbPrintXmlUtilService attribute. 
     * @return Returns the irbPrintXmlUtilService.
     */
    public IrbPrintXmlUtilService getIrbPrintXmlUtilService() {
        return irbPrintXmlUtilService;
    } 



}
