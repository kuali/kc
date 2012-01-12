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
package org.kuali.kra.committee.print;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.irb.personnel.ProtocolPersonRolodex;
import org.kuali.kra.printing.xmlstream.PrintBaseXmlStream;

import edu.mit.irb.irbnamespace.CommitteeDocument.Committee;
import edu.mit.irb.irbnamespace.CommitteeMasterDataDocument.CommitteeMasterData;
import edu.mit.irb.irbnamespace.CommitteeMemberDocument.CommitteeMember;
import edu.mit.irb.irbnamespace.CommitteeMemberRoleDocument.CommitteeMemberRole;
import edu.mit.irb.irbnamespace.PersonDocument.Person;
import edu.mit.irb.irbnamespace.ProtocolDocument.Protocol.Submissions;
import edu.mit.irb.irbnamespace.ResearchAreaDocument.ResearchArea;
import edu.mit.irb.irbnamespace.ScheduleDocument.Schedule;
import edu.mit.irb.irbnamespace.ScheduleDocument.Schedule.NextSchedule;

/**
 * This class generates XML that confirms with the XSD related to 
 * committee reports. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 */
public class CommitteeXmlStream extends PrintBaseXmlStream {

    private ScheduleXmlStream scheduleXmlStream;
    private IrbPrintXmlUtilService irbPrintXmlUtilService;
    /**
     * This method generates XML committee report. It uses data passed in
     * {@link ResearchDocumentBase} for populating the XML nodes. The XMl once
     * generated is returned as {@link XmlObject}
     * 
     * @param printableBusinessObject
     *            using which XML is generated
     * @param reportParameters
     *            parameters related to XML generation
     * @return {@link XmlObject} representing the XML
     */
    public Map<String, XmlObject> generateXmlStream(KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
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
        List<CommitteeResearchArea> committeeResearchAreas = committee.getCommitteeResearchAreas();
        if(committeeResearchAreas.isEmpty()) return;
        for (CommitteeResearchArea committeeResearchArea : committeeResearchAreas) {
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
        List<CommitteeMembership> committeeMemberships = committee.getCommitteeMemberships();
        if(committeeMemberships.isEmpty()) return;
        for (CommitteeMembership membershipBean : committeeMemberships) {
            CommitteeMember committeeMember = committeeType.addNewCommitteeMember();
            setCommitteeMembershipType(membershipBean, committeeMember);
        }
        
    }
    public void setCommitteeMembers(org.kuali.kra.committee.bo.Committee committee, Submissions committeeType) {
        List<CommitteeMembership> committeeMemberships = committee.getCommitteeMemberships();
        if(committeeMemberships.isEmpty()) return;
        for (CommitteeMembership membershipBean : committeeMemberships) {
            CommitteeMember committeeMember = committeeType.addNewCommitteeMember();
            setCommitteeMembershipType(membershipBean, committeeMember);
        }
        
    }
    /**
     * This method...
     * @param membershipBean
     * @param committeeMember
     */
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
        List<CommitteeMembershipExpertise> committeeMemResearchArea =  membershipBean.getMembershipExpertise();
        if (committeeMemResearchArea != null){
            for (CommitteeMembershipExpertise committeeMemberExpertise : committeeMemResearchArea) {
                ResearchArea researchArea = committeeMember.addNewResearchArea();
                researchArea.setResearchAreaCode(committeeMemberExpertise.getResearchAreaCode()) ;
                if (committeeMemberExpertise.getResearchArea()!=null){
                    researchArea.setResearchAreaDescription(committeeMemberExpertise.getResearchArea().getDescription()) ;
                }
            }    
        }
        List<CommitteeMembershipRole> vecMemRoles = membershipBean.getMembershipRoles();
        if ( vecMemRoles != null){
            for (CommitteeMembershipRole committeeMembershipRole : vecMemRoles) {
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
            ProtocolPersonRolodex rolodexBean =  membershipBean.getRolodex();
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
      committeeMasterData.setDefaultReviewTypeCode(new BigInteger(String.valueOf(committee.getReviewTypeCode()))) ;
      if(committee.getReviewType()!=null){
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
