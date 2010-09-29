/*
 * ProtocolSummaryStream.java
 *
 * Created on March 11, 2009, 1:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.mit.coeus.utils.xml.bean.protocol.generator;

import edu.mit.coeus.award.bean.AwardTxnBean;
import edu.mit.coeus.bean.UserRolesInfoBean;

import edu.mit.coeus.exception.CoeusException;
import edu.mit.coeus.instprop.bean.InstituteProposalTxnBean;
import edu.mit.coeus.irb.bean.ProtocolActionsBean;
import edu.mit.coeus.irb.bean.ProtocolAmendRenewalBean;
import edu.mit.coeus.irb.bean.ProtocolCorrespondentsBean;
import edu.mit.coeus.irb.bean.ProtocolCustomElementsInfoBean;
import edu.mit.coeus.irb.bean.ProtocolDataTxnBean;
import edu.mit.coeus.irb.bean.ProtocolFundingSourceBean;
import edu.mit.coeus.irb.bean.ProtocolInfoBean;
import edu.mit.coeus.irb.bean.ProtocolInvestigatorUnitsBean;
import edu.mit.coeus.irb.bean.ProtocolInvestigatorsBean;
import edu.mit.coeus.irb.bean.ProtocolKeyPersonnelBean;
import edu.mit.coeus.irb.bean.ProtocolLocationListBean;
import edu.mit.coeus.irb.bean.ProtocolModuleBean;
import edu.mit.coeus.irb.bean.ProtocolNotepadBean;
import edu.mit.coeus.irb.bean.ProtocolReasearchAreasBean;
import edu.mit.coeus.irb.bean.ProtocolReferencesBean;
import edu.mit.coeus.irb.bean.ProtocolRiskLevelBean;
import edu.mit.coeus.irb.bean.ProtocolSpecialReviewFormBean;
import edu.mit.coeus.irb.bean.ProtocolVulnerableSubListsBean;
import edu.mit.coeus.irb.bean.UploadDocumentBean;
import edu.mit.coeus.propdev.bean.ProposalDevelopmentUpdateTxnBean;
import edu.mit.coeus.sponsormaint.bean.SponsorMaintenanceDataTxnBean;
import edu.mit.coeus.sponsormaint.bean.SponsorMaintenanceFormBean;
import edu.mit.coeus.unit.bean.UnitDataTxnBean;
import edu.mit.coeus.unit.bean.UnitDetailFormBean;
import edu.mit.coeus.utils.CoeusProperties;
import edu.mit.coeus.utils.CoeusPropertyKeys;
import edu.mit.coeus.utils.ComboBoxBean;
import edu.mit.coeus.utils.UtilFactory;
import edu.mit.coeus.utils.dbengine.DBException;
import edu.mit.coeus.utils.xml.bean.protocol.ProtoAmendRenewalType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolActionsType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolCorrespondentType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolDetailsType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolDocumentType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolDocumentsType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolFundingSourceType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolInvestigatorType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolKeyPersonsType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolLocationType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolModulesType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolNotesType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolOtherDataType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolOtherDocumentsType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolReferencesType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolResearchAreasType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolRiskLevelsType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolRolesType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolSpecialReviewType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolSubjectsType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolSummary;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolSummaryType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolUnitsType;
import edu.mit.coeus.utils.xml.bean.protocol.ProtocolUserRolesType;
import edu.mit.coeus.utils.xml.bean.protocol.SchoolInfoType;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtoAmendRenewalTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolActionsTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolCorrespondentTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolDetailsTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolDocumentTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolDocumentsTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolFundingSourceTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolInvestigatorTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolKeyPersonsTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolLocationTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolModulesTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolNotesTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolOtherDataTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolOtherDocumentsTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolReferencesTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolResearchAreasTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolRiskLevelsTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolRolesTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolSpecialReviewTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolSubjectsTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolSummaryImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolSummaryTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolUnitsTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.ProtocolUserRolesTypeImpl;
import edu.mit.coeus.utils.xml.bean.protocol.impl.SchoolInfoTypeImpl;
import edu.mit.coeus.xml.generator.ReportBaseStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author mohann
 */
public class ProtocolSummaryStream extends ReportBaseStream {
    
    private static final String ACTIONS = "ACTIONS";
    private static final String AMENDMENT_RENEWAL_SUMMARY ="AMENDMENT_RENEWAL_SUMMARY";
    private static final String AMENDMENT_RENEWAL_HISTORY ="AMENDMENT_RENEWAL_HISTORY";
    private static final String ATTACHMENTS = "ATTACHMENTS";
    private static final String AREA_OF_RESEARCH = "AREA_OF_RESEARCH";
    private static final String CORRESPONDENTS = "CORRESPONDENTS";
    private static final String FUNDING_SOURCE = "FUNDING_SOURCE";
    private static final String INVESTIGATOR = "INVESTIGATOR";
    private static final String NOTES = "NOTES";
    private static final String ORGANIZATION = "ORGANIZATION";
    private static final String OTHER_DATA = "OTHER_DATA";
    private static final String PROTOCOL_DETAIL = "PROTOCOL_DETAIL";
    private static final String REFERENCES = "REFERENCES";
    private static final String RISK_LEVELS = "RISK_LEVELS";
    private static final String ROLES = "ROLES";
    private static final String SPECIAL_REVIEW = "SPECIAL_REVIEW";
    private static final String STUDY_PERSONNEL = "STUDY_PERSONNEL";
    private static final String SUBJECTS = "SUBJECTS";
    
    
    
    /** Creates a new instance of ProtocolSummaryStream */
    public ProtocolSummaryStream() {
    }
    
    public Object getObjectStream(Hashtable htData) throws DBException,CoeusException{
        try{
            
            String loggedInUser = (String)htData.get("LOGGED_IN_USER");
            String reportType = (String)htData.get("REPORT_TYPE");
            
            if(loggedInUser == null || reportType == null) {
                CoeusException coeusException = new CoeusException();
                coeusException.setMessage("No data found");
                throw coeusException;
            }
            
            Object object = null;
            
//            if(reportType.equals("ProtocolSummary")) {
            ProtocolInfoBean protocolInfoBean = (ProtocolInfoBean)htData.get("PROTOCOL_INFO_BEAN");
            object = getProtocolSummaryReportStream(protocolInfoBean, htData, loggedInUser);
//            }
            
            return object;
            
        }catch (Exception exception) {
            throw new CoeusException(exception.getMessage());
        }
    }
    
    private Object getProtocolSummaryReportStream(ProtocolInfoBean protocolInfoBean, Hashtable htData, String loggedInUser) throws CoeusException {
        
        try{
            
            ProtocolSummary protocolSummary = new ProtocolSummaryImpl();
            
            ProtocolSummaryType.PrintRequirementType  printRequirementType = new ProtocolSummaryTypeImpl.PrintRequirementTypeImpl();
            protocolSummary.setPrintRequirement(printRequirementType);
            if (((Boolean)htData.get(ACTIONS)).booleanValue()) {
                protocolSummary.getPrintRequirement().setActionsRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setActionsRequired("0");
            }
            if (((Boolean)htData.get(AMENDMENT_RENEWAL_SUMMARY)).booleanValue()) {
                protocolSummary.getPrintRequirement().setAmendRenewModulesRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setAmendRenewModulesRequired("0");
            }
            if (((Boolean)htData.get(AMENDMENT_RENEWAL_HISTORY)).booleanValue()) {
                protocolSummary.getPrintRequirement().setAmendRenewSRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setAmendRenewSRequired("0");
            }
            if (((Boolean)htData.get(ATTACHMENTS)).booleanValue()) {
                protocolSummary.getPrintRequirement().setDocumentsRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setDocumentsRequired("0");
            }
            if (((Boolean)htData.get(AREA_OF_RESEARCH)).booleanValue()) {
                protocolSummary.getPrintRequirement().setResearchAreasRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setResearchAreasRequired("0");
            }
            if (((Boolean)htData.get(CORRESPONDENTS)).booleanValue()) {
                protocolSummary.getPrintRequirement().setCorrespondentsRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setCorrespondentsRequired("0");
            }
            if (((Boolean)htData.get(FUNDING_SOURCE)).booleanValue()) {
                protocolSummary.getPrintRequirement().setFundingSourcesRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setFundingSourcesRequired("0");
            }
            if (((Boolean)htData.get(INVESTIGATOR)).booleanValue()) {
                protocolSummary.getPrintRequirement().setInvestigatorsRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setInvestigatorsRequired("0");
            }
            if (((Boolean)htData.get(NOTES)).booleanValue()) {
                protocolSummary.getPrintRequirement().setNotesRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setNotesRequired("0");
            }
            if (((Boolean)htData.get(ORGANIZATION)).booleanValue()) {
                protocolSummary.getPrintRequirement().setOrganizationRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setOrganizationRequired("0");
            }
            if (((Boolean)htData.get(OTHER_DATA)).booleanValue()) {
                protocolSummary.getPrintRequirement().setOtherDataRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setOtherDataRequired("0");
            }
            if (((Boolean)htData.get(PROTOCOL_DETAIL)).booleanValue()) {
                protocolSummary.getPrintRequirement().setProtocolDetailsRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setProtocolDetailsRequired("0");
            }
            if (((Boolean)htData.get(REFERENCES)).booleanValue()) {
                protocolSummary.getPrintRequirement().setReferencesRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setReferencesRequired("0");
            }
            if (((Boolean)htData.get(RISK_LEVELS)).booleanValue()) {
                protocolSummary.getPrintRequirement().setRiskLevelsRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setRiskLevelsRequired("0");
            }
            if (((Boolean)htData.get(ROLES)).booleanValue()) {
                protocolSummary.getPrintRequirement().setUserRolesRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setUserRolesRequired("0");
            }
            if (((Boolean)htData.get(SPECIAL_REVIEW)).booleanValue()) {
                protocolSummary.getPrintRequirement().setSpecialReviewRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setSpecialReviewRequired("0");
            }
            if (((Boolean)htData.get(STUDY_PERSONNEL)).booleanValue()) {
                protocolSummary.getPrintRequirement().setKeyPersonsRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setKeyPersonsRequired("0");
            }
            if (((Boolean)htData.get(SUBJECTS)).booleanValue()) {
                protocolSummary.getPrintRequirement().setSubjectsRequired("1");
            } else {
                protocolSummary.getPrintRequirement().setSubjectsRequired("0");
            }
             //Added for the case#3091 - IRB - generate a protocol summary pdf -start
//            if (((Boolean)htData.get(QUESTIONNAIRE)).booleanValue()) {
//                protocolSummary.getPrintRequirement().setQuestionnaireDetailsRequired("1");
//            } else {
//                protocolSummary.getPrintRequirement().setQuestionnaireDetailsRequired("0");
//            }
            //Added for the case#3091 - IRB - generate a protocol summary pdf -end   
            Calendar currentDate = Calendar.getInstance();
            currentDate.setTime(new Date());
            protocolSummary.getPrintRequirement().setCurrentDate(currentDate);
            
            // Get protocol details
            ProtocolDataTxnBean protocolDataTxnBean = new ProtocolDataTxnBean();
            
            // All all protocol details
            ProtocolInfoBean protoInfoBean =  protocolDataTxnBean.getProtocolInfo(protocolInfoBean.getProtocolNumber());
            protocolSummary.getProtocolDetails().addAll(getProtocolDetailsInfo(protoInfoBean));
            
            // Add all the Investigators and units.
            Vector vecInvestigators = protoInfoBean.getInvestigators();
            protocolSummary.getProtocolInvestigators().addAll(getProtocolInvestigatorsDetail(vecInvestigators));
            
            //Add Key Persons
            Vector vecKeyPersons = protoInfoBean.getKeyStudyPersonnel();
            protocolSummary.getProtocolKeyPersons().addAll(getProtocolKeypersonsDetail(vecKeyPersons));
            
            // Add Locations / Organizations
            Vector vecLocations = protoInfoBean.getLocationLists();
            protocolSummary.getProtocolOrganization().addAll(getProtocolOrganizationInfo(vecLocations));
            
            // Add Correspondents
            Vector vecCorrespondents = protoInfoBean.getCorrespondetns();
            protocolSummary.getProtocolCorrespondents().addAll(getProtocolCorrespondentsDetail(vecCorrespondents));
            
            // Add ProtocolResearchAreas
            Vector VecResearchAreas = protoInfoBean.getAreaOfResearch();
            protocolSummary.getProtocolResearchAreas().addAll(getProtocolResearchAreasDetail(VecResearchAreas));
            
            // Add ProtocolFundingSources
            Vector VecFundingSources = protoInfoBean.getFundingSources();
            protocolSummary.getProtocolFundingSources().addAll(getProtocolFundingSourcesDetail(VecFundingSources));
            
            // Add ProtocolActions
            
            Vector vecProtocolActions = protoInfoBean.getActions();
            protocolSummary.getProtocolActions().addAll(getProtocolActionsDetail(vecProtocolActions));
            
            // Add ProtocolSubjects
            Vector vecProtoclSubjects = protoInfoBean.getVulnerableSubjectLists();
            protocolSummary.getProtocolSubjects().addAll(getProtocolSubjectsDetail(vecProtoclSubjects));
            
            //Add Special Review
            Vector vecSpecialReview = protoInfoBean.getSpecialReviews();
            protocolSummary.getProtocolSpecialReview().addAll(getProtocolSpecialReviewDetail(vecSpecialReview));
            
            //Add ProtocolRiskLevels
            Vector vecRiskLevels =protocolDataTxnBean.getProtocolRiskLevels(protoInfoBean.getProtocolNumber());
            protocolSummary.getProtocolRiskLevels().addAll(getProtocolRiskLevelsInfo(vecRiskLevels));
            
            //Add Protocol Notes
            Vector vecNotes = protoInfoBean.getVecNotepad();
            protocolSummary.getProtocolNotes().addAll(getProtocolNotesInfo(vecNotes));
            
            // Add ProtocolAmenRenewal this is for normal protocol
            Vector vecProtoAmentRenewal = protoInfoBean.getAmendmentRenewal();
            Vector vecEditableModules = protoInfoBean.getAmendRenewEditableModules();
            protocolSummary.getProtocolAmenRenewal().addAll(getProtocolAmenRenewalInfo(vecProtoAmentRenewal, vecEditableModules));
            
            // Add ProtoAmendRenewSummary this is for Amendment / Renewal protocol
//            Vector protoAmentRenewal = protoInfoBean.  setVecModuleData;
//            protocolSummary.getProtocolAmenRenewal().addAll(getProtocolAmenRenewalInfo(protoAmentRenewal));
            
            // Add Protocol Other Data
            Vector vecProtocolOtherData = protoInfoBean.getCustomElements();
            protocolSummary.getProtocolOthersData().addAll(getProtocolOthersDataInfo(vecProtocolOtherData));
            
            // Add Protocol References
            Vector vecReferences = protoInfoBean.getReferences();
            protocolSummary.getProtocolReferences().addAll(getProtocolReferencesInfo(vecReferences));
            
            // Add Protocol Roles
            Vector vecRoles = protoInfoBean.getUserRolesInfoBean();
            protocolSummary.getProtocolUserRoles().addAll(getProtocolUserRolesInfo(vecRoles));
            
            // Add Documents and Other Documents
            protocolSummary.getProtocolDocuments().addAll(getProtocolDocumentsInfo(protoInfoBean.getProtocolNumber(), protoInfoBean.getSequenceNumber()));
            
            //Added for the case#3091 - IRB - generate a protocol summary pdf -start
            String schoolName = CoeusProperties.getProperty(CoeusPropertyKeys.SCHOOL_NAME);
            String schoolAcronym = CoeusProperties.getProperty(CoeusPropertyKeys.SCHOOL_ACRONYM);
            // SchoolInfoType
            SchoolInfoType schoolInfoType = new SchoolInfoTypeImpl();
            schoolInfoType.setSchoolName(schoolName);
            schoolInfoType.setAcronym(schoolAcronym);
            protocolSummary.setSchoolInfo(schoolInfoType);
            //Added for the case#3091 - IRB - generate a protocol summary pdf -
            return protocolSummary;
            
        } catch (Exception e) {
            UtilFactory.log( e.getMessage(), e,"ProtocolSummaryStream", "getProtocolSummaryReportStream");
            throw new CoeusException(e.getMessage());
        }
        
        
    }
    
    
    /**
     * This method is used to get all Protocol details information.
     * @param collection of all the key persons
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolDetailsInfo(ProtocolInfoBean protocolInfoBean) throws DBException, CoeusException {
        Vector vecProtocolDetailsInfo = new Vector();
        //Added for the case#3091 - IRB - generate a protocol summary pdf -start
        Vector typeDescVect = new Vector();
        Vector investigatorListVect = new Vector();
        HashMap typeDescMap=new HashMap();
        ProtocolDataTxnBean protocolDataTxnBean = new ProtocolDataTxnBean();
        ProtocolInvestigatorsBean protocolInvestigatorsBean = new ProtocolInvestigatorsBean();
        //Added for the case#3091 - IRB - generate a protocol summary pdf -end
        ProtocolDetailsType protocolDetailsType = null;
        if( protocolInfoBean !=null ){
            protocolDetailsType = new ProtocolDetailsTypeImpl();
            
            Calendar applicationDate =null;
            if (protocolInfoBean.getApplicationDate()!=null) {
                applicationDate = Calendar.getInstance();
                applicationDate.setTime(protocolInfoBean.getApplicationDate());
                protocolDetailsType.setApplicationDate(applicationDate);
            }
            Calendar approvalDate =null;
            if (protocolInfoBean.getApprovalDate()!=null) {
                approvalDate = Calendar.getInstance();
                approvalDate.setTime(protocolInfoBean.getApprovalDate());
                protocolDetailsType.setApprovalDate(approvalDate);
            }
            Calendar creationDate =null;
            if (protocolInfoBean.getCreateTimestamp()!=null) {
                creationDate = Calendar.getInstance();
                creationDate.setTime(protocolInfoBean.getCreateTimestamp());
                protocolDetailsType.setCreateTimestamp(creationDate);
            }
            
            protocolDetailsType.setCreateUser(protocolInfoBean.getCreateUser());
            protocolDetailsType.setDescription(protocolInfoBean.getDescription());
            Calendar exprirationDate =null;
            if (protocolInfoBean.getExpirationDate()!=null) {
                exprirationDate = Calendar.getInstance();
                exprirationDate.setTime(protocolInfoBean.getExpirationDate());
                protocolDetailsType.setExpirationDate(exprirationDate);
            }
            
            protocolDetailsType.setFdaApplicationNumber(protocolInfoBean.getFDAApplicationNumber());
            protocolDetailsType.setIsBillable((protocolInfoBean.isBillableFlag()== true  ? "Y" : "N" ) );
            Calendar lastApprovalDate =null;
            if (protocolInfoBean.getLastApprovalDate()!=null) {
                lastApprovalDate = Calendar.getInstance();
                lastApprovalDate.setTime(protocolInfoBean.getLastApprovalDate());
                protocolDetailsType.setLastApprovalDate(lastApprovalDate);
            }
            
            protocolDetailsType.setProtocolNumber(protocolInfoBean.getProtocolNumber());
            protocolDetailsType.setProtocolStatusCode(protocolInfoBean.getProtocolStatusCode());
            protocolDetailsType.setProtocolStatusDesc(protocolInfoBean.getProtocolStatusDesc());
            protocolDetailsType.setProtocolTypeCode(protocolInfoBean.getProtocolTypeCode());
            //Added for the case#3091 - IRB - generate a protocol summary pdf -start
            typeDescVect=protocolDataTxnBean.getProtocolTypes();
            if(typeDescVect!=null && typeDescVect.size()>0){
                for(int i=0;i<typeDescVect.size();i++){
                    ComboBoxBean comboBean = (ComboBoxBean)typeDescVect.get(i);
                    typeDescMap.put(comboBean.getCode(),comboBean.getDescription());
                }
            }
            investigatorListVect=protocolDataTxnBean.getProtocolInvestigators(protocolInfoBean.getProtocolNumber().toString(),protocolInfoBean.getSequenceNumber());
            if(investigatorListVect!=null && investigatorListVect.size()>0){
                for(int index=0;index<investigatorListVect.size();index++){
                    protocolInvestigatorsBean=(ProtocolInvestigatorsBean)investigatorListVect.get(index);
                    if(protocolInvestigatorsBean.isPrincipalInvestigatorFlag()){
                        protocolDetailsType.setInvestigator(protocolInvestigatorsBean.getPersonName());
                        
                    }
                }
            }
            //Added for the case#3091 - IRB - generate a protocol summary pdf -end
            protocolDetailsType.setProtocolTypeDesc((String) typeDescMap.get(Integer.toString(protocolInfoBean.getProtocolTypeCode())));
            protocolDetailsType.setReferenceNumber1(protocolInfoBean.getRefNum_1());
            protocolDetailsType.setReferenceNumber2(protocolInfoBean.getRefNum_2());
            protocolDetailsType.setSequenceNumber(protocolInfoBean.getSequenceNumber());
            protocolDetailsType.setTitle(protocolInfoBean.getTitle());
            protocolDetailsType.setUpdateUser(protocolInfoBean.getUpdateUser());
            vecProtocolDetailsInfo.addElement(protocolDetailsType);
        }
        return vecProtocolDetailsInfo;
    }
    
    
    
    /**
     * This method is used to get all the investigators and units information for Protocol.
     * @param collection of all the investigators
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolInvestigatorsDetail(Vector vecInvestigators) throws DBException, CoeusException {
        Vector vecAllInvestigator = new Vector();
        ProtocolInvestigatorType protocolInvestigatorType = null;
        if( vecInvestigators !=null && vecInvestigators.size() > 0){
            for(int index = 0; index < vecInvestigators.size(); index++) {
                protocolInvestigatorType = new ProtocolInvestigatorTypeImpl();
                ProtocolInvestigatorsBean investigatorBean = (ProtocolInvestigatorsBean) vecInvestigators.get(index);
                protocolInvestigatorType.setPersonId(investigatorBean.getPersonId());
                protocolInvestigatorType.setPersonName(investigatorBean.getPersonName());
                protocolInvestigatorType.setAffiliationTypeCode(investigatorBean.getAffiliationTypeCode());
                protocolInvestigatorType.setNonEmployeeFlag( ( investigatorBean.isNonEmployeeFlag() == true  ? "Y" : "N" ) );
                protocolInvestigatorType.setPrincipalInvestigatorFlag((investigatorBean.isPrincipalInvestigatorFlag() == true ? "Y" : "N"));
                protocolInvestigatorType.setProtocolNumber(investigatorBean.getProtocolNumber());
                protocolInvestigatorType.setSequenceNumber(investigatorBean.getSequenceNumber());
                protocolInvestigatorType.setUpdateUser(investigatorBean.getUpdateUser());
                protocolInvestigatorType.setTrainingFlag( ( investigatorBean.isTrainingFlag() == true  ? "Y" : "N" ) );
                protocolInvestigatorType.setAffiliationTypeCode(investigatorBean.getAffiliationTypeCode());
                protocolInvestigatorType.setAffiliationTypeDesc(investigatorBean.getAffiliationTypeDescription());
                // Get all the Investigator Units.
                Vector vecInvUnits =  investigatorBean.getInvestigatorUnits();
                if(vecInvUnits != null && vecInvUnits.size() > 0 ){
                    ProtocolInvestigatorUnitsBean protocolInvestigatorUnitsBean = null;
                    ProtocolUnitsType protocolUnitsType = null;
                    for(int invIndex = 0; invIndex < vecInvUnits.size(); invIndex++){
                        protocolUnitsType = new ProtocolUnitsTypeImpl();
                        protocolInvestigatorUnitsBean = (ProtocolInvestigatorUnitsBean) vecInvUnits.get(invIndex);
                        protocolUnitsType.setLeadUnitFlag((protocolInvestigatorUnitsBean.isLeadUnitFlag() == true ? "Y" : "N"));
                        protocolUnitsType.setPersonId(protocolInvestigatorUnitsBean.getPersonId());
                        protocolUnitsType.setUnitNumber(protocolInvestigatorUnitsBean.getUnitNumber());
                        protocolUnitsType.setUnitName(protocolInvestigatorUnitsBean.getUnitName());
                        protocolUnitsType.setProtocolNumber(protocolInvestigatorUnitsBean.getProtocolNumber());
                        protocolUnitsType.setSequenceNumber(protocolInvestigatorUnitsBean.getSequenceNumber());
                        protocolInvestigatorType.getProtocolUnits().add(protocolUnitsType);
                    }
                }
                vecAllInvestigator.addElement(protocolInvestigatorType);
            }
        }
        
        return vecAllInvestigator;
    }
    
    /**
     * This method is used to get all the key persons information for Protocol.
     * @param collection of all the key persons
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolKeypersonsDetail(Vector vecInvestigators) throws DBException, CoeusException {
        Vector vecAllInvestigator = new Vector();
        ProtocolKeyPersonsType protocolKeyPersonsType = null;
        if( vecInvestigators !=null && vecInvestigators.size() > 0){
            for(int index = 0; index < vecInvestigators.size(); index++) {
                protocolKeyPersonsType = new ProtocolKeyPersonsTypeImpl();
                ProtocolKeyPersonnelBean investigatorBean = (ProtocolKeyPersonnelBean) vecInvestigators.get(index);
                protocolKeyPersonsType.setPersonId(investigatorBean.getPersonId());
                protocolKeyPersonsType.setPersonName(investigatorBean.getPersonName());
                protocolKeyPersonsType.setAffiliationTypeCode(investigatorBean.getAffiliationTypeCode());
                protocolKeyPersonsType.setNonEmployeeFlag( ( investigatorBean.isNonEmployeeFlag() == true  ? "Y" : "N" ) );
                protocolKeyPersonsType.setProtocolNumber(investigatorBean.getProtocolNumber());
                protocolKeyPersonsType.setSequenceNumber(investigatorBean.getSequenceNumber());
                protocolKeyPersonsType.setUpdateUser(investigatorBean.getUpdateUser());
                protocolKeyPersonsType.setPersonRole(investigatorBean.getPersonRole());
                protocolKeyPersonsType.setTrainingFlag( ( investigatorBean.isTrainingFlag() == true  ? "Y" : "N" ) );
                protocolKeyPersonsType.setAffiliationTypeCode(investigatorBean.getAffiliationTypeCode());
                protocolKeyPersonsType.setAffiliationTypeDesc(investigatorBean.getAffiliationTypeDescription());
                vecAllInvestigator.addElement(protocolKeyPersonsType);
            }
        }
        
        return vecAllInvestigator;
    }
    
    
    
    
    /**
     * This method is used to get all the Organization information for Protocol.
     * @param vector
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolOrganizationInfo(Vector vecOrganizations) throws DBException, CoeusException {
        Vector vecAllOrganizations = new Vector();
        ProtocolLocationType protocolLocationType = null;
        if( vecOrganizations !=null && vecOrganizations.size() > 0){
            for(int index = 0; index < vecOrganizations.size(); index++) {
                protocolLocationType = new ProtocolLocationTypeImpl();
                ProtocolLocationListBean protocolLocationListBean = (ProtocolLocationListBean) vecOrganizations.get(index);
                protocolLocationType.setOrganizationId(protocolLocationListBean.getOrganizationId());
                protocolLocationType.setProtocolOrgTypeCode(protocolLocationListBean.getOrganizationTypeId());
                protocolLocationType.setProtocolOrgTypeDesc(protocolLocationListBean.getOrganizationTypeName());
                protocolLocationType.setOrganizationId(protocolLocationListBean.getOrganizationId());
                protocolLocationType.setOrgName(protocolLocationListBean.getOrganizationName());
                String address = "";
                if(protocolLocationListBean.getAddress() !=null){
                    address = protocolLocationListBean.getAddress();
                    address =  address.replaceAll("[$#]", " ");
                }
                protocolLocationType.setAddress(address);
                protocolLocationType.setRolodexId(protocolLocationListBean.getRolodexId());
                vecAllOrganizations.addElement(protocolLocationType);
            }
        }
        return vecAllOrganizations;
    }
    
    /**
     * This method is used to get all the Correspondents information for Protocol.
     * @param vector
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolCorrespondentsDetail(Vector vecCorrespondents) throws DBException, CoeusException {
        Vector vecAllCorrespondents = new Vector();
        ProtocolCorrespondentType protocolCorrespondentType = null;
        if( vecCorrespondents !=null && vecCorrespondents.size() > 0){
            for(int index = 0; index < vecCorrespondents.size(); index++) {
                protocolCorrespondentType = new ProtocolCorrespondentTypeImpl();
                ProtocolCorrespondentsBean correspondentsBean = (ProtocolCorrespondentsBean) vecCorrespondents.get(index);
                protocolCorrespondentType.setCorrespondentTypeCode(correspondentsBean.getCorrespondentTypeCode());
                protocolCorrespondentType.setCorrespondentTypeDesc(correspondentsBean.getCorrespondentTypeDesc());
                protocolCorrespondentType.setComments(correspondentsBean.getComments());
                protocolCorrespondentType.setNonEmployeeFlag(( correspondentsBean.isNonEmployeeFlag() == true  ? "Y" : "N" ));
                protocolCorrespondentType.setPersonId(correspondentsBean.getPersonId());
                protocolCorrespondentType.setPersonName(correspondentsBean.getPersonName());
                protocolCorrespondentType.setProtocolNumber(correspondentsBean.getProtocolNumber());
                protocolCorrespondentType.setSequenceNumber(correspondentsBean.getSequenceNumber());
                protocolCorrespondentType.setUpdateUser(correspondentsBean.getUpdateUser());
                vecAllCorrespondents.addElement(protocolCorrespondentType);
            }
        }
        
        return vecAllCorrespondents;
    }
    
    /**
     * This method is used to get all Research Areas information for Protocol.
     * @param vector
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolResearchAreasDetail(Vector vecResearchAreas) throws DBException, CoeusException {
        Vector vecAllResearchAreas = new Vector();
        ProtocolResearchAreasType protocolResearchAreasType = null;
        if( vecResearchAreas !=null && vecResearchAreas.size() > 0){
            for(int index = 0; index < vecResearchAreas.size(); index++) {
                protocolResearchAreasType = new ProtocolResearchAreasTypeImpl();
                ProtocolReasearchAreasBean reasearchAreasBean  = (ProtocolReasearchAreasBean) vecResearchAreas.get(index);
                protocolResearchAreasType.setProtocolNumber(reasearchAreasBean.getProtocolNumber());
                protocolResearchAreasType.setResearchAreaCode(reasearchAreasBean.getResearchAreaCode());
                protocolResearchAreasType.setResearchAreaDesc(reasearchAreasBean.getResearchAreaDescription());
                protocolResearchAreasType.setSequenceNumber(reasearchAreasBean.getSequenceNumber());
                protocolResearchAreasType.setUpdateUser(reasearchAreasBean.getUpdateUser());
                vecAllResearchAreas.addElement(protocolResearchAreasType);
            }
        }
        
        return vecAllResearchAreas;
    }
    
    
    
    /**
     * This method is used to get all Research Areas information for Protocol.
     * @param vector
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolFundingSourcesDetail(Vector vecFundingSources) throws DBException, CoeusException {
        Vector vecAllFundingSources = new Vector();
        ProtocolFundingSourceType protocolFundingSourceType = null;
        //Added for the case#3091 - IRB - generate a protocol summary pdf -start
        String title=null;
        // //Added for the case#3091 - IRB - generate a protocol summary pdf -end
        
        if( vecFundingSources !=null && vecFundingSources.size() > 0){
            for(int index = 0; index < vecFundingSources.size(); index++) {
                protocolFundingSourceType = new ProtocolFundingSourceTypeImpl();
                ProtocolFundingSourceBean fundingSourceBean  = (ProtocolFundingSourceBean) vecFundingSources.get(index);
                protocolFundingSourceType.setFundingSource(fundingSourceBean.getFundingSource());
                protocolFundingSourceType.setFundingSourceTypeCode(fundingSourceBean.getFundingSourceTypeCode());
                // //Added for the case#3091 - IRB - generate a protocol summary pdf -start
                title= getFundingSourceNameOrTitle(fundingSourceBean.getFundingSourceTypeCode(),fundingSourceBean.getFundingSource());
                if(title!=null){
                    protocolFundingSourceType.setTitle(title);
                }
                // //Added for the case#3091 - IRB - generate a protocol summary pdf -end
                
                protocolFundingSourceType.setFundingSourceTypeDesc(fundingSourceBean.getFundingSourceTypeDesc());
                protocolFundingSourceType.setProtocolNumber(fundingSourceBean.getProtocolNumber());
                protocolFundingSourceType.setSequenceNumber(fundingSourceBean.getSequenceNumber());
                protocolFundingSourceType.setUpdateUser(fundingSourceBean.getUpdateUser());
                //protocolFundingSourceType.setTitle(fundingSourceBean.getFundingSourceName());
                vecAllFundingSources.addElement(protocolFundingSourceType);
            }
        }
        
        return vecAllFundingSources;
    }
    
    
    
    
    /**
     * This method is used to get all Actions information for Protocol.
     * @param vector
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolActionsDetail(Vector vecActions) throws DBException, CoeusException {
        Vector vecAllActions = new Vector();
        ProtocolActionsType protocolActionsType = null;
        
        if( vecActions !=null && vecActions.size() > 0){
            for(int index = 0; index < vecActions.size(); index++) {
                protocolActionsType = new ProtocolActionsTypeImpl();
                ProtocolActionsBean protocolActionsBean = (ProtocolActionsBean)vecActions.get(index);
                protocolActionsType.setActionId(protocolActionsBean.getActionId());
                Calendar actionDate =null;
                if (protocolActionsBean.getActionDate()!=null) {
                    actionDate = Calendar.getInstance();
                    actionDate.setTime(protocolActionsBean.getActionDate());
                    protocolActionsType.setActionDate(actionDate);
                }
                protocolActionsType.setComments(protocolActionsBean.getComments());
                protocolActionsType.setProtocolActionTypeCode(protocolActionsBean.getActionTypeCode());
                protocolActionsType.setProtocolNumber(protocolActionsBean.getProtocolNumber());
                protocolActionsType.setSequenceNumber(protocolActionsBean.getSequenceNumber());
                protocolActionsType.setSubmissionNumber(protocolActionsBean.getSubmissionNumber());
                protocolActionsType.setUpdateUser(protocolActionsBean.getUpdateUser());
                Calendar approvalDate =null;
                if (protocolActionsBean.getApprovalDate()!=null) {
                    approvalDate = Calendar.getInstance();
                    approvalDate.setTime(protocolActionsBean.getApprovalDate());
                    protocolActionsType.setApprovalDate(approvalDate);
                }
//                protocolActionsType.setActionDesc(protocolActionsBean.get)
                protocolActionsType.setProtocolActionTypeDesc(protocolActionsBean.getActionTypeDescription());
                
                vecAllActions.addElement(protocolActionsType);
            }
        }
        
        return vecAllActions;
    }
    
    
    /**
     * This method is used to get all Subjects information for Protocol.
     * @param vector
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolSubjectsDetail(Vector vecSubjects) throws DBException, CoeusException {
        Vector vecAllSubjects = new Vector();
        ProtocolSubjectsType protocolSubjectsType = null;
        if( vecSubjects !=null && vecSubjects.size() > 0){
            for(int index = 0; index < vecSubjects.size(); index++) {
                protocolSubjectsType = new ProtocolSubjectsTypeImpl();
                ProtocolVulnerableSubListsBean vulnerableSubListsBean = (ProtocolVulnerableSubListsBean)vecSubjects.get(index);
                protocolSubjectsType.setProtocolNumber(vulnerableSubListsBean.getProtocolNumber());
                protocolSubjectsType.setSequenceNumber(vulnerableSubListsBean.getSequenceNumber());
                //protocolSubjectsType.setSubjectCount(vulnerableSubListsBean.getSubjectCount().intValue());
                 //Added for the case#3091 - IRB - generate a protocol summary pdf -start
                if(vulnerableSubListsBean.getSubjectCount()!=null){
                    protocolSubjectsType.setSubjectCount(vulnerableSubListsBean.getSubjectCount().intValue());
                }
                 //Added for the case#3091 - IRB - generate a protocol summary pdf -end
                protocolSubjectsType.setVulnerableSubjectTypeCode(vulnerableSubListsBean.getVulnerableSubjectTypeCode());
                protocolSubjectsType.setVulnerableSubjectTypeDesc(vulnerableSubListsBean.getVulnerableSubjectTypeDesc());
                protocolSubjectsType.setUpdateUser(vulnerableSubListsBean.getUpdateUser());
                vecAllSubjects.addElement(protocolSubjectsType);
            }
        }
        
        return vecAllSubjects;
    }
    
    
    /**
     * This method is used to get all Special Review information for Protocol.
     * @param vector
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolSpecialReviewDetail(Vector vecSpecialReviews) throws DBException, CoeusException {
        Vector vecAllSpecialReviews = new Vector();
        ProtocolSpecialReviewType protocolSpecialReviewType = null;
        if( vecSpecialReviews !=null && vecSpecialReviews.size() > 0){
            for(int index = 0; index < vecSpecialReviews.size(); index++) {
                
                protocolSpecialReviewType = new ProtocolSpecialReviewTypeImpl();
                ProtocolSpecialReviewFormBean specialReviewFormBean = (ProtocolSpecialReviewFormBean)vecSpecialReviews.get(index);
                // Set Application Date
                Calendar applicationDate =null;
                if (specialReviewFormBean.getApplicationDate()!=null) {
                    applicationDate = Calendar.getInstance();
                    applicationDate.setTime(specialReviewFormBean.getApplicationDate());
                    protocolSpecialReviewType.setApplicationDate(applicationDate);
                }
                // Set Approval Date
                Calendar approvalDate =null;
                if (specialReviewFormBean.getApprovalDate()!=null) {
                    approvalDate = Calendar.getInstance();
                    approvalDate.setTime(specialReviewFormBean.getApprovalDate());
                    protocolSpecialReviewType.setApprovalDate(approvalDate);
                }
                
                protocolSpecialReviewType.setApprovalTypeCode(specialReviewFormBean.getApprovalCode());
                protocolSpecialReviewType.setApprovalTypeDesc(specialReviewFormBean.getApprovalDescription());
                protocolSpecialReviewType.setComments(specialReviewFormBean.getComments());
                protocolSpecialReviewType.setProtocolNumber(specialReviewFormBean.getProtocolNumber());
                protocolSpecialReviewType.setSequenceNumber(specialReviewFormBean.getSequenceNumber());
                protocolSpecialReviewType.setSpRevProtocolNumber(specialReviewFormBean.getProtocolSPRevNumber());
                protocolSpecialReviewType.setSpecialReviewCode(specialReviewFormBean.getSpecialReviewCode());
                protocolSpecialReviewType.setSpecialReviewDesc(specialReviewFormBean.getSpecialReviewDescription());
                protocolSpecialReviewType.setSpecialReviewNumber(specialReviewFormBean.getSpecialReviewNumber());
                protocolSpecialReviewType.setUpdateUser(specialReviewFormBean.getUpdateUser());
                
                vecAllSpecialReviews.addElement(protocolSpecialReviewType);
            }
        }
        
        return vecAllSpecialReviews;
    }
    
    /**
     * This method is used to get all Risk level information for Protocol.
     * @param vector
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolRiskLevelsInfo(Vector vecRiskLevels) throws DBException, CoeusException {
        Vector vecAllRiskLevels = new Vector();
        ProtocolRiskLevelsType protocolRiskLevelsType = null;
        //Added for the case#3091 - IRB - generate a protocol summary pdf -start
        HashMap riskLevelsMap = new HashMap();
        Vector riskLevelVect = new Vector();
        //Added for the case#3091 - IRB - generate a protocol summary pdf -end
        if( vecRiskLevels !=null && vecRiskLevels.size() > 0){
            //Added for the case#3091 - IRB - generate a protocol summary pdf -start
            ProtocolDataTxnBean protocolDataTxnBean = new ProtocolDataTxnBean();
            riskLevelVect=protocolDataTxnBean.getRiskLevels();
            for(int i=0;i<riskLevelVect.size();i++){
                ComboBoxBean comboBean = (ComboBoxBean)riskLevelVect.get(i);
                riskLevelsMap.put(comboBean.getCode(),comboBean.getDescription());
            }
            //Added for the case#3091 - IRB - generate a protocol summary pdf -end
            for(int index = 0; index < vecRiskLevels.size(); index++) {
                protocolRiskLevelsType = new ProtocolRiskLevelsTypeImpl();
                ProtocolRiskLevelBean protocolRiskLevelBean = (ProtocolRiskLevelBean) vecRiskLevels.get(index);
                protocolRiskLevelsType.setComments(protocolRiskLevelBean.getComments());
                Calendar assignedDate =null;
                if (protocolRiskLevelBean.getDateAssigned()!=null) {
                    assignedDate = Calendar.getInstance();
                    assignedDate.setTime(protocolRiskLevelBean.getDateAssigned());
                    protocolRiskLevelsType.setDateAssigned(assignedDate);
                }
                Calendar updatedDate =null;
                if (protocolRiskLevelBean.getDateUpdated()!=null) {
                    updatedDate = Calendar.getInstance();
                    updatedDate.setTime(protocolRiskLevelBean.getDateUpdated());
                    protocolRiskLevelsType.setDateUpdated(updatedDate);
                }
                protocolRiskLevelsType.setProtocolNumber(protocolRiskLevelBean.getProtocolNumber());
                protocolRiskLevelsType.setRiskLevelCode(Integer.parseInt(protocolRiskLevelBean.getRiskLevelCode()));
                //protocolRiskLevelsType.setRiskLevelDesc((String) riskLevelsMap.get);
                //Added for the case#3091 - IRB - generate a protocol summary pdf -start
                protocolRiskLevelsType.setRiskLevelDesc((String) riskLevelsMap.get(protocolRiskLevelBean.getRiskLevelCode()));
                //Added for the case#3091 - IRB - generate a protocol summary pdf -end
                protocolRiskLevelsType.setSequenceNumber(protocolRiskLevelBean.getSequenceNumber());
                protocolRiskLevelsType.setStatus(protocolRiskLevelBean.getStatus());
                protocolRiskLevelsType.setUpdateUser(protocolRiskLevelBean.getUpdateUser());
                vecAllRiskLevels.addElement(protocolRiskLevelsType);
            }
        }
        
        return vecAllRiskLevels;
    }
    
    /**
     * This method is used to get all Notes information for Protocol.
     * @param vector
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolNotesInfo(Vector vecNotes) throws DBException, CoeusException {
        Vector vecNotesInfo = new Vector();
        ProtocolNotesType protocolNotesType = null;
        if( vecNotes !=null && vecNotes.size() > 0){
            for(int index = 0; index < vecNotes.size(); index++) {
                protocolNotesType = new ProtocolNotesTypeImpl();
                ProtocolNotepadBean protocolNotepadBean = (ProtocolNotepadBean) vecNotes.get(index);
                protocolNotesType.setComments(protocolNotepadBean.getComments());
                protocolNotesType.setEntryNumber(protocolNotepadBean.getEntryNumber());
                protocolNotesType.setProtocolNumber(protocolNotepadBean.getProtocolNumber());
                protocolNotesType.setSequenceNumber(protocolNotepadBean.getSequenceNumber());
                protocolNotesType.setUpdateUser(protocolNotepadBean.getUpdateUser());
                Calendar updatedDate =null;
                if (protocolNotepadBean.getUpdateTimestamp()!=null) {
                    updatedDate = Calendar.getInstance();
                    updatedDate.setTime(protocolNotepadBean.getUpdateTimestamp());
                    protocolNotesType.setUpdateTimestamp(updatedDate);
                }
                
                vecNotesInfo.addElement(protocolNotesType);
            }
        }
        
        return vecNotesInfo;
    }
    
    
    
    /**
     * This method is used to get all Amendment/Renewal information for Protocol.
     * @param vector
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolAmenRenewalInfo(Vector vecAmendRenewal, Vector vecEditableModules) throws DBException, CoeusException {
        //Added for the case#3091 - IRB - generate a protocol summary pdf -start
        String versionNo = "",moduleType = "";
        String amendType = "Amendment";
        String renewalType = "Renewal";
        String protocolNo = null;
        String protocolId = null;
        //Added for the case#3091 - IRB - generate a protocol summary pdf -end
        Vector vecAmendRenewalInfo = new Vector();
        ProtoAmendRenewalType  protoAmendRenewalType = null;
        ProtocolModulesType protocolModulesType = null;
        if( vecAmendRenewal !=null && vecAmendRenewal.size() > 0){
            for(int index = 0; index < vecAmendRenewal.size(); index++) {
                protoAmendRenewalType = new ProtoAmendRenewalTypeImpl();
                ProtocolAmendRenewalBean protocolAmendRenewalBean = (ProtocolAmendRenewalBean) vecAmendRenewal.get(index);
                
                Calendar createdDate =null;
                if (protocolAmendRenewalBean.getDateCreated() !=null) {
                    createdDate = Calendar.getInstance();
                    createdDate.setTime(protocolAmendRenewalBean.getDateCreated());
                    protoAmendRenewalType.setDateCreated(createdDate);
                }
                protoAmendRenewalType.setProtoAmendRenNumber(protocolAmendRenewalBean.getProtocolAmendRenewalNumber());
                protoAmendRenewalType.setProtocolNumber(protocolAmendRenewalBean.getProtocolNumber());
                protoAmendRenewalType.setSequenceNumber(protocolAmendRenewalBean.getSequenceNumber());
                protoAmendRenewalType.setSummary(protocolAmendRenewalBean.getSummary());
                protoAmendRenewalType.setUpdateUser(protocolAmendRenewalBean.getUpdateUser());
                  //Added for the case#3091 - IRB - generate a protocol summary pdf -start
                protocolNo=protocolAmendRenewalBean.getProtocolAmendRenewalNumber();
                if(protocolNo!=null && protocolNo.length() >= 14 ) {
                    versionNo = protocolNo.substring(11);
                    protocolId = protocolNo.substring(0,10);
                    if( protocolNo.indexOf( 'A' ) != -1 ) {
                        moduleType = amendType;
                    }else if( protocolNo.indexOf( 'R' ) != -1 ) {
                        moduleType = renewalType;
                    }
                    //moduleType = ""+protocolNo.charAt(10);
                }
                if(moduleType!=null && moduleType.length()>0){
                    protoAmendRenewalType.setType(moduleType);
                }
                if(versionNo!=null && versionNo.length()>0){
                    protoAmendRenewalType.setVersion(versionNo);
                }
                //Added for the case#3091 - IRB - generate a protocol summary pdf -end
                
                protoAmendRenewalType.setProtocolStatusDesc(protocolAmendRenewalBean.getProtocolStatusDescription());
                protoAmendRenewalType.setProtocolStatusCode(protocolAmendRenewalBean.getProtocolStatus());
                // Get Module Data
                Vector vecModuleData  = protocolAmendRenewalBean.getVecModuleData();
                
                if(vecModuleData != null && vecModuleData.size() > 0){
                    HashMap hmModules  = new HashMap();
                    if (vecEditableModules !=null && vecEditableModules.size() > 0){
                        hmModules = (HashMap) vecEditableModules.get(0);
                    }
                    for(int index1= 0 ; index1 < vecModuleData.size() ; index1++){
                        protocolModulesType =  new ProtocolModulesTypeImpl();
                        ProtocolModuleBean amendRenewModuleBean = (ProtocolModuleBean)vecModuleData.get(index1);
                        protocolModulesType.setProtocolModuleCode(amendRenewModuleBean.getProtocolModuleCode());
                        protocolModulesType.setDescription( ( hmModules.get(amendRenewModuleBean.getProtocolModuleCode()) != null ?
                            hmModules.get(amendRenewModuleBean.getProtocolModuleCode()) : " " ).toString());
                        protocolModulesType.setUpdateUser(amendRenewModuleBean.getUpdateUser());
                        protoAmendRenewalType.getProtocolModules().add(protocolModulesType);
                    }
                }
                
                vecAmendRenewalInfo.addElement(protoAmendRenewalType);
            }
        }
        
        return vecAmendRenewalInfo;
    }
    
    /**
     * This method is used to get all Custom information for Protocol.
     * @param vector
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolOthersDataInfo(Vector vecOtherData) throws DBException, CoeusException {
        Vector vecOtherDataInfo = new Vector();
        ProtocolOtherDataType protocolOtherDataType = null;
        if( vecOtherData !=null && vecOtherData.size() > 0){
            for(int index = 0; index < vecOtherData.size(); index++) {
                protocolOtherDataType = new ProtocolOtherDataTypeImpl();
                ProtocolCustomElementsInfoBean customElementsInfoBean = (ProtocolCustomElementsInfoBean) vecOtherData.get(index);
                protocolOtherDataType.setColumnName(customElementsInfoBean.getColumnName());
                protocolOtherDataType.setColumnValue(customElementsInfoBean.getColumnValue());
                protocolOtherDataType.setProtocolNumber(customElementsInfoBean.getProtocolNumber());
                protocolOtherDataType.setSequenceNumber(customElementsInfoBean.getSequenceNumber());
                protocolOtherDataType.setUpdateUser(customElementsInfoBean.getUpdateUser());
                vecOtherDataInfo.addElement(protocolOtherDataType);
            }
        }
        
        return vecOtherDataInfo;
    }
    /**
     * This method is used to get all Refereces information for Protocol.
     * @param vector
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolReferencesInfo(Vector vecReferences) throws DBException, CoeusException {
        Vector vecRefInfo = new Vector();
        ProtocolReferencesType protocolReferencesType = null;
        if( vecReferences !=null && vecReferences.size() > 0){
            for(int index = 0; index < vecReferences.size(); index++) {
                protocolReferencesType = new ProtocolReferencesTypeImpl();
                ProtocolReferencesBean protocolReferencesBean = (ProtocolReferencesBean)vecReferences.get(index);
                
                Calendar applicationDate =null;
                if (protocolReferencesBean.getApplicationDate() !=null) {
                    applicationDate = Calendar.getInstance();
                    applicationDate.setTime(protocolReferencesBean.getApplicationDate());
                    protocolReferencesType.setApplicationDate(applicationDate);
                }
                Calendar approvalDate =null;
                if (protocolReferencesBean.getApprovalDate() !=null) {
                    approvalDate = Calendar.getInstance();
                    approvalDate.setTime(protocolReferencesBean.getApprovalDate());
                    protocolReferencesType.setApprovalDate(approvalDate);
                }
                
                protocolReferencesType.setComments(protocolReferencesBean.getComments());
                protocolReferencesType.setProtocolNumber(protocolReferencesBean.getProtocolNumber());
                protocolReferencesType.setProtocolReferenceNumber(protocolReferencesBean.getReferenceNumber());
                protocolReferencesType.setProtocolReferenceTypeCode(protocolReferencesBean.getReferenceTypeCode());
                protocolReferencesType.setProtocolReferenceTypeDesc(protocolReferencesBean.getReferenceTypeDescription());
                protocolReferencesType.setReferenceKey(protocolReferencesBean.getReferenceKey());
                protocolReferencesType.setSequenceNumber(protocolReferencesBean.getSequenceNumber());
                
                vecRefInfo.addElement(protocolReferencesType);
            }
        }
        
        return vecRefInfo;
    }
    
    
    
    /**
     * This method is used to get all Custom information for Protocol.
     * @param vector
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolUserRolesInfo(Vector vecRoles) throws DBException, CoeusException {
        Vector vecRolesInfo = new Vector();
        ProtocolUserRolesType protocolUserRolesType = null;
        ProtocolRolesType protocolRolesType = null;
        
        if( vecRoles !=null && vecRoles.size() > 0){
            for(int index = 0; index < vecRoles.size(); index++) {
                protocolRolesType = new ProtocolRolesTypeImpl();
                UserRolesInfoBean userRolesInfoBean = (UserRolesInfoBean) vecRoles.get(index);
                
                protocolRolesType.setRole(userRolesInfoBean.getRoleBean().getRoleId());
                protocolRolesType.setRoleName(userRolesInfoBean.getRoleBean().getRoleName());
                
                
                Vector vecUsers = userRolesInfoBean.getUsers();
                if(vecUsers != null && vecUsers.size() > 0 ){
                    for(int index1= 0 ; index1 < vecUsers.size(); index1++ ){
                        protocolUserRolesType = new ProtocolUserRolesTypeImpl();
                        UserRolesInfoBean rolesInfoBean = (UserRolesInfoBean) vecUsers.get(index1);
//                        UserInfoBean userInfoBean = (UserInfoBean) vecUsers.get(index1);
                        protocolUserRolesType.setPersonId(rolesInfoBean.getUserBean().getPersonId());
                        protocolUserRolesType.setPersonName(rolesInfoBean.getUserBean().getPersonName());
//                        protocolUserRolesType.setRoleDesc(userInfoBean.get)
//                        protocolUserRolesType.setRoleId()
                        protocolUserRolesType.setUnitName(rolesInfoBean.getUserBean().getUnitName());
                        protocolUserRolesType.setUnitNumber(rolesInfoBean.getUserBean().getUnitNumber());
                        protocolUserRolesType.setUserId(rolesInfoBean.getUserBean().getUserId());
                        //Added for the case#3091 - IRB - generate a protocol summary pdf -start
                        protocolUserRolesType.setUserName(rolesInfoBean.getUserBean().getUserName());
                        //Added for the case#3091 - IRB - generate a protocol summary pdf -end
                        protocolRolesType.getUserRoles().add(protocolUserRolesType);
                    }
                    
                }
                vecRolesInfo.addElement(protocolRolesType);
            }
        }
        
        
        return vecRolesInfo;
    }
    
    
    /**
     * This method is used to get all Document information for Protocol.
     * @param vector
     * @throws DBException
     * @throws CoeusException
     */
    private Vector getProtocolDocumentsInfo(String protocolNumber, int SequenceNumber) throws DBException, CoeusException {
        Vector vecAllDocuments = new Vector();
        ProtocolDocumentsType protocolDocumentsType = null;
        ProtocolDocumentType  protocolDocumentType = null;
        ProtocolOtherDocumentsType protocolOtherDocumentsType = null;
        // Get protocol details
        ProtocolDataTxnBean protocolDataTxnBean = new ProtocolDataTxnBean();
        Vector vectorDocuments =   protocolDataTxnBean.getUploadDocumentForProtocol(protocolNumber);
        protocolDocumentsType = new ProtocolDocumentsTypeImpl();
        protocolDocumentsType.setProtocolNumber(protocolNumber);
        protocolDocumentsType.setSequenceNumber(SequenceNumber);
        
        if( vectorDocuments !=null && vectorDocuments.size() > 0){
            for(int index = 0; index < vectorDocuments.size(); index++) {
                protocolDocumentType = new ProtocolDocumentTypeImpl();
                UploadDocumentBean uploadDocumentBean = (UploadDocumentBean) vectorDocuments.get(index);
                protocolDocumentType.setDescription(uploadDocumentBean.getDescription());
                protocolDocumentType.setDocumentTypeCode(uploadDocumentBean.getDocCode());
                protocolDocumentType.setDocumentTypeGroup(uploadDocumentBean.getDocType());
                Calendar updateDate =null;
                if (uploadDocumentBean.getUpdateTimestamp() !=null) {
                    updateDate = Calendar.getInstance();
                    updateDate.setTime(uploadDocumentBean.getUpdateTimestamp());
                    protocolDocumentType.setUpdateTimestamp(updateDate);
                }
                protocolDocumentType.setUpdateUser(uploadDocumentBean.getUpdateUser());
                protocolDocumentsType.getProtocolDocument().add(protocolDocumentType);
//                vecAllDocuments.addElement(protocolDocumentsType);
            }
        }
        
        // Get Other Document Details
        Vector vectorOtherDocuments =   protocolDataTxnBean.getProtoOtherAttachments(protocolNumber);
        if( vectorOtherDocuments !=null && vectorOtherDocuments.size() > 0){
            for(int index = 0; index < vectorOtherDocuments.size(); index++) {
                protocolOtherDocumentsType = new ProtocolOtherDocumentsTypeImpl();
                UploadDocumentBean otherDocumentBean = (UploadDocumentBean) vectorOtherDocuments.get(index);
                protocolOtherDocumentsType.setDescription(otherDocumentBean.getDescription());
                protocolOtherDocumentsType.setDocumentId(otherDocumentBean.getDocumentId());
                protocolOtherDocumentsType.setDocumentTypeCode(otherDocumentBean.getDocCode());
                protocolOtherDocumentsType.setDocumentTypeDesc(otherDocumentBean.getDocType());
                protocolOtherDocumentsType.setFileName(otherDocumentBean.getFileName());
                Calendar updateDate =null;
                if (otherDocumentBean.getUpdateTimestamp() !=null) {
                    updateDate = Calendar.getInstance();
                    updateDate.setTime(otherDocumentBean.getUpdateTimestamp());
                    protocolOtherDocumentsType.setUpdateTimestamp(updateDate);
                }
                protocolOtherDocumentsType.setUpdateUser(otherDocumentBean.getUpdateUser());
                protocolDocumentsType.getProtocolOtherDocuments().add(protocolOtherDocumentsType);
//                vecAllDocuments.addElement(protocolDocumentsType);
            }
        }
        vecAllDocuments.addElement(protocolDocumentsType);
        
        return vecAllDocuments;
    }
    
     //Added for the case#3091 - IRB - generate a protocol summary pdf -start
     private String getFundingSourceNameOrTitle(int fundingSourceTypeCode,String fundingSource) throws DBException, CoeusException {
         String title=null;
         if(fundingSourceTypeCode==1){
             SponsorMaintenanceDataTxnBean sponsorTxnBean = new SponsorMaintenanceDataTxnBean();
             SponsorMaintenanceFormBean sponsorMaintenanceFormBean=(SponsorMaintenanceFormBean)sponsorTxnBean.getSponsorMaintenanceDetails(fundingSource);
             title= sponsorMaintenanceFormBean.getName();
         }else if(fundingSourceTypeCode==2){
             UnitDataTxnBean unitDataTxnBean=new UnitDataTxnBean();
             UnitDetailFormBean unitDetailFormBean=(UnitDetailFormBean)unitDataTxnBean.getUnitDetails(fundingSource);
             title=unitDetailFormBean.getUnitName();
         } else if(fundingSourceTypeCode==4){
             ProposalDevelopmentUpdateTxnBean proposalDevelopmentUpdateTxnBean = new ProposalDevelopmentUpdateTxnBean();
             title=proposalDevelopmentUpdateTxnBean.getProposalTitle(fundingSource);
         } else if(fundingSourceTypeCode==5){
             InstituteProposalTxnBean instituteProposalTxnBean = new InstituteProposalTxnBean();
             title=instituteProposalTxnBean.getProposalTitle(fundingSource);
         } else if(fundingSourceTypeCode==6){
             AwardTxnBean awardTxnBean = new AwardTxnBean();
             title=awardTxnBean.getAwardTitle(fundingSource);
         }
         return title;
     }
    
     //Added for the case#3091 - IRB - generate a protocol summary pdf -end
}
