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
package org.kuali.kra.common.committee.print;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.common.committee.bo.CommonCommittee;
import org.kuali.kra.common.committee.bo.CommitteeMembership;
import org.kuali.kra.common.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.common.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.common.committee.bo.CommitteeResearchArea;
import org.kuali.kra.common.committee.bo.CommonCommitteeSchedule;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonRolodex;
import org.kuali.kra.printing.xmlstream.PrintBaseXmlStream;

import edu.mit.coeus.xml.iacuc.CommitteeDocument;
import edu.mit.coeus.xml.iacuc.CommitteeMasterDataType;
import edu.mit.coeus.xml.iacuc.CommitteeMemberRoleType;
import edu.mit.coeus.xml.iacuc.CommitteeMemberType;
import edu.mit.coeus.xml.iacuc.CommitteeType;
import edu.mit.coeus.xml.iacuc.PersonType;
import edu.mit.coeus.xml.iacuc.ProtocolType.Submissions;
import edu.mit.coeus.xml.iacuc.ResearchAreaType;
import edu.mit.coeus.xml.iacuc.ScheduleSummaryType;
import edu.mit.coeus.xml.iacuc.ScheduleType;

/**
 * This class generates XML that confirms with the XSD related to 
 * committee reports. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 */
public class CommitteeXmlStream extends PrintBaseXmlStream {

    private ScheduleXmlStream scheduleXmlStream;
    private PrintXmlUtilService printXmlUtilService;
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
        CommonCommittee committee = (CommonCommittee)printableBusinessObject;
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
       CommitteeDocument committeeDocumentType = CommitteeDocument.Factory.newInstance();
        committeeDocumentType.setCommittee(getCommitteeCompleteDetails(committee));
        xmlObjectList.put("Committee", committeeDocumentType);
        return xmlObjectList;
    }
    public CommitteeType getCommitteeCompleteDetails(CommonCommittee committee)  {
        CommitteeType committeeType = CommitteeType.Factory.newInstance();
        setCommitteeMasterData(committee,committeeType.addNewCommitteeMasterData()) ;
        setCommitteeMembers(committee,committeeType);
        setScheduleForcommittee(committee,committeeType) ;
        setCommitteeResearchArea(committee,committeeType) ;
        return committeeType ;
    }
    private void setCommitteeResearchArea(org.kuali.kra.common.committee.bo.CommonCommittee committee, CommitteeType committeeType) {
        List<CommitteeResearchArea> committeeResearchAreas = committee.getCommitteeResearchAreas();
        if(committeeResearchAreas.isEmpty()) return;
        for (CommitteeResearchArea committeeResearchArea : committeeResearchAreas) {
           ResearchAreaType researchArea = committeeType.addNewResearchArea();
            researchArea.setResearchAreaCode(committeeResearchArea.getResearchAreaCode()) ;
            researchArea.setResearchAreaDescription(committeeResearchArea.getResearchArea().getDescription()) ;
        }
    }

    private void setScheduleForcommittee(org.kuali.kra.common.committee.bo.CommonCommittee committee, CommitteeType committeeType) {
        Date currentDate = new Date();
        Boolean isRooster=committee.getPrintRooster();
        List<CommonCommitteeSchedule> vecSchedule = committee.getCommitteeSchedules();
        if (vecSchedule.isEmpty()) return;
        for (CommonCommitteeSchedule scheduleDetailsBean : vecSchedule) {
           Date scheduleDate =  scheduleDetailsBean.getScheduledDate();
           int dateCount = scheduleDate.compareTo(currentDate);
           if(isRooster){
             ScheduleType scheduleType = committeeType.addNewSchedule();
            getScheduleXmlStream().setScheduleMasterData(scheduleDetailsBean,scheduleType.addNewScheduleMasterData()) ;
            ScheduleSummaryType nextSchedule = scheduleType.addNewNextSchedule();
            getScheduleXmlStream().setNextSchedule(scheduleDetailsBean,nextSchedule.addNewScheduleMasterData());
        }
           if(!isRooster){
               if(dateCount>0){
                   ScheduleType scheduleType = committeeType.addNewSchedule();
                   getScheduleXmlStream().setScheduleMasterData(scheduleDetailsBean,scheduleType.addNewScheduleMasterData()) ;
                   ScheduleSummaryType nextSchedule = scheduleType.addNewNextSchedule();
                   getScheduleXmlStream().setNextSchedule(scheduleDetailsBean,nextSchedule.addNewScheduleMasterData());   
           }}}
    }

    public void setCommitteeMembers(org.kuali.kra.common.committee.bo.CommonCommittee committee, CommitteeType committeeType) {
        List<CommitteeMembership> committeeMemberships = committee.getCommitteeMemberships();
        if(committeeMemberships.isEmpty()) return;
        for (CommitteeMembership membershipBean : committeeMemberships) {
            CommitteeMemberType committeeMember = committeeType.addNewCommitteeMember();
            setCommitteeMembershipType(membershipBean, committeeMember);
        }
        
    }
    public void setCommitteeMembers(org.kuali.kra.common.committee.bo.CommonCommittee committee, Submissions committeeType) {
        List<CommitteeMembership> committeeMemberships = committee.getCommitteeMemberships();
        if(committeeMemberships.isEmpty()) return;
        for (CommitteeMembership membershipBean : committeeMemberships) {
            CommitteeMemberType committeeMember = committeeType.addNewCommitteeMember();
            setCommitteeMembershipType(membershipBean, committeeMember);
        }
        
    }
    /**
     * This method...
     * @param membershipBean
     * @param committeeMember
     */
    private void setCommitteeMembershipType(CommitteeMembership membershipBean, CommitteeMemberType committeeMember) {
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
                ResearchAreaType researchArea = committeeMember.addNewResearchArea();
                researchArea.setResearchAreaCode(committeeMemberExpertise.getResearchAreaCode()) ;
                if (committeeMemberExpertise.getResearchArea()!=null){
                    researchArea.setResearchAreaDescription(committeeMemberExpertise.getResearchArea().getDescription()) ;
                }
            }    
        }
        List<CommitteeMembershipRole> vecMemRoles = membershipBean.getMembershipRoles();
        if ( vecMemRoles != null){
            for (CommitteeMembershipRole committeeMembershipRole : vecMemRoles) {
                CommitteeMemberRoleType committeeMemRole = committeeMember.addNewCommitteeMemberRole();
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

    private void setPersonType(CommitteeMembership membershipBean,CommitteeMemberType committeeMember) {
        PersonType person = committeeMember.addNewPerson();
        boolean employeeFlag = membershipBean.getPerson()!=null;
        person.setFacultyFlag(false) ; 
        person.setEmployeeFlag(!employeeFlag) ;
         if (employeeFlag ){
             KcPerson personBean = membershipBean.getPerson();
             getPrintXmlUtilService().setPersonXml(personBean, person);
         }else{ 
            ProtocolPersonRolodex rolodexBean =  membershipBean.getRolodex();
            getPrintXmlUtilService().setPersonXml(rolodexBean, person);
         }    
    }

    public void setCommitteeMasterData(CommonCommittee committee,CommitteeMasterDataType committeeMasterDataType){
//      committee.refreshNonUpdateableReferences();
      committeeMasterDataType.setCommitteeId(committee.getCommitteeId()) ;
      committeeMasterDataType.setCommitteeName(committee.getCommitteeName()) ;
      committeeMasterDataType.setHomeUnitNumber(committee.getHomeUnitNumber()) ;
      committeeMasterDataType.setHomeUnitName(committee.getUnitName()) ;
      committeeMasterDataType.setCommitteeTypeCode(new BigInteger(String.valueOf(committee.getCommitteeTypeCode()))) ;
      committeeMasterDataType.setCommitteeTypeDesc(committee.getCommitteeType().getDescription()) ;
      committeeMasterDataType.setScheduleDescription(committee.getScheduleDescription()) ;
      committeeMasterDataType.setMinimumMembersRequired(new BigInteger(String.valueOf(committee.getMinimumMembersRequired()))) ;
      committeeMasterDataType.setMaxProtocols(new BigInteger(String.valueOf(committee.getMaxProtocols()))) ;
      committeeMasterDataType.setAdvSubmissionDays(new BigInteger(String.valueOf(committee.getAdvancedSubmissionDaysRequired()))) ;
        if(committee.getReviewType()!=null){
        committeeMasterDataType.setDefaultReviewTypeCode(new BigInteger(String.valueOf(committee.getReviewTypeCode()))) ;
          committeeMasterDataType.setDefaultReviewTypeDesc(committee.getReviewType().getDescription()) ;
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
    public void setPrintXmlUtilService(PrintXmlUtilService printXmlUtilService) {
        this.printXmlUtilService = printXmlUtilService;
    }
    /**
     * Gets the irbPrintXmlUtilService attribute. 
     * @return Returns the irbPrintXmlUtilService.
     */
    public PrintXmlUtilService getPrintXmlUtilService() {
        return printXmlUtilService;
    } 



}
