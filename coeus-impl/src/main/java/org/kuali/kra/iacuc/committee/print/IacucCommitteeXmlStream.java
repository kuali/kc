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
package org.kuali.kra.iacuc.committee.print;

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

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipExpertiseBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipRole;
import org.kuali.coeus.common.committee.impl.bo.CommitteeResearchAreaBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.print.stream.xml.PrintBaseXmlStream;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.iacuc.committee.print.service.IacucPrintXmlUtilService;
import org.kuali.kra.protocol.personnel.ProtocolPersonRolodexBase;

import java.math.BigInteger;
import java.util.*;

/**
 * This class generates XML that confirms with the XSD related to 
 * committee reports. The data for XML is derived from
 * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and {@link Map} of details passed to the class.
 */
public class IacucCommitteeXmlStream extends PrintBaseXmlStream {

    private IacucScheduleXmlStream scheduleXmlStream;
    private IacucPrintXmlUtilService printXmlUtilService;
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
        CommitteeBase committee = (CommitteeBase)printableBusinessObject;
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
       CommitteeDocument committeeDocumentType = CommitteeDocument.Factory.newInstance();
        committeeDocumentType.setCommittee(getCommitteeCompleteDetails(committee));
        xmlObjectList.put("CommitteeBase", committeeDocumentType);
        return xmlObjectList;
    }
    public CommitteeType getCommitteeCompleteDetails(CommitteeBase committee)  {
        CommitteeType committeeType = CommitteeType.Factory.newInstance();
        setCommitteeMasterData(committee,committeeType.addNewCommitteeMasterData()) ;
        setCommitteeMembers(committee,committeeType);
        setScheduleForcommittee(committee,committeeType);
        setCommitteeResearchArea(committee,committeeType);
        return committeeType ;
    }
    private void setCommitteeResearchArea(org.kuali.coeus.common.committee.impl.bo.CommitteeBase committee, CommitteeType committeeType) {
        List<CommitteeResearchAreaBase> committeeResearchAreas = committee.getCommitteeResearchAreas();
        if(committeeResearchAreas.isEmpty()) return;
        for (CommitteeResearchAreaBase committeeResearchArea : committeeResearchAreas) {
           ResearchAreaType researchArea = committeeType.addNewResearchArea();
            researchArea.setResearchAreaCode(committeeResearchArea.getResearchAreaCode()) ;
            researchArea.setResearchAreaDescription(committeeResearchArea.getResearchArea().getDescription()) ;
        }
    }

    private void setScheduleForcommittee(org.kuali.coeus.common.committee.impl.bo.CommitteeBase committee, CommitteeType committeeType) {
        Date currentDate = new Date();
        Boolean isRooster=committee.getPrintRooster();
        List<CommitteeScheduleBase> vecSchedule = committee.getCommitteeSchedules();
        if (vecSchedule.isEmpty()) return;
        for (CommitteeScheduleBase scheduleDetailsBean : vecSchedule) {
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

    public void setCommitteeMembers(org.kuali.coeus.common.committee.impl.bo.CommitteeBase committee, CommitteeType committeeType) {
        List<CommitteeMembershipBase> committeeMemberships = committee.getCommitteeMemberships();
        if(committeeMemberships.isEmpty()) return;
        for (CommitteeMembershipBase membershipBean : committeeMemberships) {
            CommitteeMemberType committeeMember = committeeType.addNewCommitteeMember();
            setCommitteeMembershipType(membershipBean, committeeMember);
        }
        
    }
    public void setCommitteeMembers(org.kuali.coeus.common.committee.impl.bo.CommitteeBase committee, Submissions committeeType) {
        List<CommitteeMembershipBase> committeeMemberships = committee.getCommitteeMemberships();
        if(committeeMemberships.isEmpty()) return;
        for (CommitteeMembershipBase membershipBean : committeeMemberships) {
            CommitteeMemberType committeeMember = committeeType.addNewCommitteeMember();
            setCommitteeMembershipType(membershipBean, committeeMember);
        }
        
    }

    private void setCommitteeMembershipType(CommitteeMembershipBase membershipBean, CommitteeMemberType committeeMember) {
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

    private void setPersonType(CommitteeMembershipBase membershipBean,CommitteeMemberType committeeMember) {
        PersonType person = committeeMember.addNewPerson();
        boolean employeeFlag = membershipBean.getPerson()!=null;
        person.setFacultyFlag(false) ; 
        person.setEmployeeFlag(!employeeFlag) ;
         if (employeeFlag ){
             KcPerson personBean = membershipBean.getPerson();
             getPrintXmlUtilService().setPersonXml(personBean, person);
         }else{ 
            ProtocolPersonRolodexBase rolodexBean =  membershipBean.getRolodex();
            getPrintXmlUtilService().setPersonXml(rolodexBean, person);
         }    
    }

    public void setCommitteeMasterData(CommitteeBase committee,CommitteeMasterDataType committeeMasterDataType){
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
    public void setScheduleXmlStream(IacucScheduleXmlStream scheduleXmlStream) {
        this.scheduleXmlStream = scheduleXmlStream;
    }
    /**
     * Gets the scheduleXmlStream attribute. 
     * @return Returns the scheduleXmlStream.
     */
    public IacucScheduleXmlStream getScheduleXmlStream() {
        return scheduleXmlStream;
    }
    /**
     * Sets the irbPrintXmlUtilService attribute value.
     * @param irbPrintXmlUtilService The irbPrintXmlUtilService to set.
     */
    public void setPrintXmlUtilService(IacucPrintXmlUtilService printXmlUtilService) {
        this.printXmlUtilService = printXmlUtilService;
    }
    /**
     * Gets the irbPrintXmlUtilService attribute. 
     * @return Returns the irbPrintXmlUtilService.
     */
    public IacucPrintXmlUtilService getPrintXmlUtilService() {
        return printXmlUtilService;
    } 



}
