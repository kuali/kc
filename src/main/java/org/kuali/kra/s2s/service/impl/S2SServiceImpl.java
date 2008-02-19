/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.log4j.Logger;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.budget.bo.BudgetSubAwards;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.service.S2SService;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import edu.mit.coeus.bean.CoeusMessageResourcesBean;
import edu.mit.coeus.exception.CoeusException;
import edu.mit.coeus.s2s.GetAppStatusDetails;
import edu.mit.coeus.s2s.GetApplication;
import edu.mit.coeus.s2s.GetOpportunity;
import edu.mit.coeus.s2s.SubmissionEngine;
import edu.mit.coeus.s2s.bean.ApplicationInfoBean;
import edu.mit.coeus.s2s.bean.FormInfoBean;
import edu.mit.coeus.s2s.bean.OpportunityInfoBean;
import edu.mit.coeus.s2s.bean.S2SHeader;
import edu.mit.coeus.s2s.bean.S2SSubmissionDataTxnBean;
import edu.mit.coeus.s2s.bean.SubmissionDetailInfoBean;
import edu.mit.coeus.s2s.validator.OpportunitySchemaParser;
import edu.mit.coeus.s2s.validator.S2SValidationException;
import edu.mit.coeus.user.bean.UserMaintDataTxnBean;
import edu.mit.coeus.utils.CoeusFunctions;
import edu.mit.coeus.utils.CoeusProperties;
import edu.mit.coeus.utils.CoeusPropertyKeys;
import edu.mit.coeus.utils.S2SConstants;
import edu.mit.coeus.utils.dbengine.DBException;

/**
 * This class...
 */
@Transactional
public class S2SServiceImpl implements S2SService, S2SConstants {
    private S2SSubmissionDataTxnBean s2sSubmissionTxnBean;
    private CoeusMessageResourcesBean coeusMessageResourcesBean;
    private String loggedinUser;
    private UserMaintDataTxnBean userTxn;
    private String reportFolder;
    private String debugMode;
    private String reportPath;
    private static final Logger LOG = Logger.getLogger(S2SServiceImpl.class);
    private BusinessObjectService businessObjectService;
    public S2SServiceImpl() {
        s2sSubmissionTxnBean = new S2SSubmissionDataTxnBean();
        coeusMessageResourcesBean = new CoeusMessageResourcesBean();
//        loggedinUser = GlobalVariables.getUserSession().getLoggedInUserNetworkId();
        userTxn = new UserMaintDataTxnBean();
        try {
            reportFolder = CoeusProperties.getProperty(CoeusPropertyKeys.REPORT_GENERATED_PATH, "Reports");
            debugMode = CoeusProperties.getProperty(CoeusPropertyKeys.GENERATE_XML_FOR_DEBUGGING);
            reportPath = reportFolder + "/";
        }
        catch (IOException e) {
            LOG.error("Cannot initialize S2SService", e);
        }
    }

    /**
     * @see org.kuali.kra.s2s.service.S2SService#isS2SCandidate(String proposalNumber)
     */
    public boolean isS2SCandidate(String proposalNumber) {
        try {
            return s2sSubmissionTxnBean.isS2SCandidate(proposalNumber.toString());
        }
        catch (DBException e) {
            LOG.error(e.getMessage(), e);
        }
        catch (CoeusException e) {
            LOG.error(e.getMessage(), e);
        }
        return false;
    }
    private S2SHeader getS2SHeader(String proposalNumber){
        return null;
    }
    /**
     * @see org.kuali.kra.s2s.service.S2SService#validateApplication(edu.mit.coeus.s2s.bean.S2SHeader)
     */
    public boolean validateApplication(String proposalNumber) {
        S2SHeader header = getS2SHeader(proposalNumber);
        SubmissionEngine subEngine = new SubmissionEngine();
        subEngine.setLoggedInUser(loggedinUser);
        subEngine.setLogDir(reportPath);
        try {
            subEngine.validateData(header);
        }
        catch (S2SValidationException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
        catch (CoeusException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
    public boolean submitApplication(String proposalNumber) {
        S2SHeader header = getS2SHeader(proposalNumber);
        try{
            SubmissionEngine subEngine = new SubmissionEngine();
            subEngine.setLoggedInUser(loggedinUser);
            subEngine.setLogDir(reportPath);
            subEngine.submitApplication(header);
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
    /**
     * @see org.kuali.kra.s2s.service.S2SService#getStatusDetails(edu.mit.coeus.s2s.bean.S2SHeader)
     */
    public Object getStatusDetails(String ggTrackingId, String proposalNumber) {
        try {
            return new GetAppStatusDetails().getStatusDetails(ggTrackingId, proposalNumber);
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @see org.kuali.kra.s2s.service.S2SService#refreshGrantsGov(String proposalNumber)
     */
    public void refreshGrantsGov(String proposalNumber) {
        S2SHeader header = getS2SHeader(proposalNumber);
        try {
            SubmissionDetailInfoBean localSubInfo = (SubmissionDetailInfoBean) s2sSubmissionTxnBean.getSubmissionDetails(header);
            GetApplication appReq = new GetApplication();
            ApplicationInfoBean[] appList = appReq.getApplicationList(header);
            localSubInfo.setAcType('U');
            localSubInfo.setUpdateUser(loggedinUser);
            if (appList == null || appList.length == 0) {// Need to check whether there is any error during the submission
                // by calling getApplicationStatusDetail web service
                Object statusDetail = new GetAppStatusDetails().getStatusDetails(localSubInfo.getGrantsGovTrackingNumber(), header
                        .getSubmissionTitle());
                if (statusDetail == null)
                    throw new CoeusException(coeusMessageResourcesBean.parseMessageKey("exceptionCode.90001"));
                String comments = statusDetail.toString();
                localSubInfo.setComments(comments);
                String status = comments.toUpperCase().indexOf("ERROR") == -1 ? comments : coeusMessageResourcesBean
                        .parseMessageKey("exceptionCode.90011");
                localSubInfo.setStatus(status);
            }
            else {
                ApplicationInfoBean latestInfo = appList[0];
                localSubInfo.setGrantsGovTrackingNumber(latestInfo.getGrantsGovTrackingNumber());
                localSubInfo.setReceivedDateTime(latestInfo.getReceivedDateTime());
                localSubInfo.setStatus(latestInfo.getStatus());
                localSubInfo.setStatusDate(latestInfo.getStatusDate());
                localSubInfo.setAgencyTrackingNumber(latestInfo.getAgencyTrackingNumber());
                if (latestInfo.getAgencyTrackingNumber() != null && latestInfo.getAgencyTrackingNumber().length() > 0) {
                    localSubInfo.setComments(coeusMessageResourcesBean.parseMessageKey("exceptionCode.90003"));
                }
                else {
                    localSubInfo.setComments(latestInfo.getStatus());
                }
            }
            s2sSubmissionTxnBean.addUpdDeleteSubmissionDetails(localSubInfo);
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
    public List<S2sOpportunity> searchOpportunity(String cfdaNumber,String opportunityId,String competitionId) {
        
        S2SHeader header = new S2SHeader();
        header.setCfdaNumber(cfdaNumber);
        header.setOpportunityId(opportunityId);
        header.setCompetitionId(competitionId);
        
        try {
            List<OpportunityInfoBean> oppInfList =  new GetOpportunity().searchOpportunity(header);
            if(oppInfList!=null){
                return convert(oppInfList,S2sOpportunity.class);
            }
            return null;
        }
        catch (S2SValidationException e) {
            LOG.error(e.getMessage(), e);

        }
        catch (CoeusException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
    
    private Object convert(Object origObject, Class destClazz) {
        Object destObject = null;
        try {
            destObject = destClazz.newInstance();
            ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
            BeanUtils.copyProperties(destObject, origObject);
            if(origObject instanceof FormInfoBean && destClazz.equals(S2sOppForms.class)){
                BeanUtils.setProperty(destObject, "oppNameSpace", BeanUtils.getProperty(origObject, "ns"));
                BeanUtils.setProperty(destObject, "schemaUrl", BeanUtils.getProperty(origObject, "schUrl"));
            }
        }
        catch (InstantiationException e) {
            LOG.error(e.getMessage(), e);
        }
        catch (IllegalAccessException e) {
            LOG.error(e.getMessage(), e);
        }
        catch (InvocationTargetException e) {
            LOG.error(e.getMessage(), e);
        }
        catch (NoSuchMethodException e) {
            LOG.error(e.getMessage(), e);
        }
        return destObject;
    }
    private List convert(List oppInfList, Class clazz) {
        List destObjectList = new ArrayList(oppInfList.size());
        for (Object origObject : oppInfList) {
            destObjectList.add(convert(origObject,clazz));
        }
        return destObjectList;
    }

    public List<S2sOppForms> parseOpportunityForms(S2sOpportunity s2sOpportunity) {
        OpportunityInfoBean opportunityInfoBean = (OpportunityInfoBean)convert(s2sOpportunity,OpportunityInfoBean.class);
        try {
            return convert(new OpportunitySchemaParser().getFormsList(opportunityInfoBean.getSchemaUrl()),S2sOppForms.class);
        }
        catch (ParserConfigurationException e) {
            LOG.error(e.getMessage(), e);
        }
        catch (SAXException e) {
            LOG.error(e.getMessage(), e);
        }
        catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        catch (CoeusException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
    public Map<String,Boolean> getS2SAuthZRights(String proposalNumber) {
        S2SHeader header = getS2SHeader(proposalNumber);
        Map<String,Boolean> rightFlags = new HashMap<String,Boolean>();
        try {
            rightFlags.put(SUBMIT_TO_SPONSOR, new Boolean(userTxn.getUserHasOSPRight(loggedinUser, SUBMIT_TO_SPONSOR)));
            rightFlags.put(IS_READY_TO_SUBMIT, new Boolean(s2sSubmissionTxnBean.isProposalReadyForS2S(header.getSubmissionTitle())));
            rightFlags.put(IS_ATTR_MATCH, new Boolean(s2sSubmissionTxnBean.isS2SAttrMatch(header.getSubmissionTitle())));
            rightFlags.put(ALTER_PROPOSAL_DATA, new Boolean(userTxn.getUserHasOSPRight(loggedinUser, ALTER_PROPOSAL_DATA)));
            return rightFlags;
        }
        catch (DBException e) {
            LOG.error(e.getMessage(), e);
        }
        catch (CoeusException e) {
            LOG.error(e.getMessage(), e);
        }
        catch (java.lang.Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public String getDefaultS2SSubmissionType() {
        try {
            return new CoeusFunctions().getParameterValue("DEFAULT_S2S_SUBMISSION_TYPE");
        }
        catch (CoeusException e) {
            LOG.error(e.getMessage(), e); 
        }
        catch (DBException e) {
            LOG.error(e.getMessage(), e); 
        }
        return null;
    }



    /**
     * @see org.kuali.kra.s2s.service.S2SService#deleteOpportunity(edu.mit.coeus.s2s.bean.OpportunityInfoBean)
     */
    public boolean deleteOpportunity(S2sOpportunity s2sOpportunity) {
        try {
            s2sSubmissionTxnBean.updateDelOpportunity((OpportunityInfoBean)convert(s2sOpportunity,OpportunityInfoBean.class));
        }
        catch (DBException ex) {
            LOG.error("Cannot initialize S2SService", ex);
            // put errors to GlobalErrorMap
            return false;
        }
        return true;
    }


    /**
     * @see org.kuali.kra.s2s.service.S2SService#getData(edu.mit.coeus.s2s.bean.S2SHeader)
     */
    public List getData(String proposalNumber) {
        S2SHeader header = getS2SHeader(proposalNumber);
        List s2sDetails = new ArrayList();

        Object[] tmpArray = null;
        try {
            tmpArray = s2sSubmissionTxnBean.getS2SDetails(header);
        }
        catch (DBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++)
            s2sDetails.add(tmpArray[i]);
        if (tmpArray[0] == null) {// opportunity is null, try to submit and
            ArrayList oppList = null;
            try {
                oppList = new GetOpportunity().searchOpportunity(header);
                s2sDetails.set(0, oppList);
            }
            catch (S2SValidationException e) {
                LOG.error(e.getMessage(), e);
            }
            catch (CoeusException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        else if (tmpArray[1] == null) {
            OpportunityInfoBean oppInfo = (OpportunityInfoBean) tmpArray[0];
            try {
                s2sDetails.set(1, new OpportunitySchemaParser().getFormsList(oppInfo.getSchemaUrl()));
            }
            catch (ParserConfigurationException e) {
                LOG.error(e.getMessage(), e);
            }
            catch (SAXException e) {
                LOG.error(e.getMessage(), e);
            }
            catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
            catch (CoeusException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        Map rightFlags = new HashMap();
        try {
            rightFlags.put(SUBMIT_TO_SPONSOR, new Boolean(userTxn.getUserHasOSPRight(loggedinUser, SUBMIT_TO_SPONSOR)));
            rightFlags
                    .put(IS_READY_TO_SUBMIT, new Boolean(s2sSubmissionTxnBean.isProposalReadyForS2S(header.getSubmissionTitle())));
            rightFlags.put(IS_ATTR_MATCH, new Boolean(s2sSubmissionTxnBean.isS2SAttrMatch(header.getSubmissionTitle())));
            rightFlags.put(ALTER_PROPOSAL_DATA, new Boolean(userTxn.getUserHasOSPRight(loggedinUser, ALTER_PROPOSAL_DATA)));
            s2sDetails.add(rightFlags);
            // modification for new columns in OSP$S2S_OPPORTUNITY, S2S_SUBMISSION_TYPE_CODE, REVISION_CODE,
            // REVISION_OTHER_DESCRIPTION - START
            List submissionTypes;
            submissionTypes = s2sSubmissionTxnBean.getSubmissionTypes();
            s2sDetails.add(submissionTypes);
            CoeusFunctions coeusFunctions = new CoeusFunctions();
            String defaultSelect;
            defaultSelect = coeusFunctions.getParameterValue("DEFAULT_S2S_SUBMISSION_TYPE");
            s2sDetails.add(defaultSelect);
        }
        catch (DBException e) {
            LOG.error(e.getMessage(), e);
        }
        catch (CoeusException e) {
            LOG.error(e.getMessage(), e);
        }
        catch (java.lang.Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return s2sDetails;
    }

    /**
     * @see org.kuali.kra.s2s.service.S2SService#getOpportunityList(edu.mit.coeus.s2s.bean.S2SHeader)
     */
    public S2sOpportunity getOpportunity(String proposalNumber) {
        try {
            return (S2sOpportunity)convert(s2sSubmissionTxnBean.getLocalOpportunity(proposalNumber),S2sOpportunity.class);
        }
        catch (DBException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @see org.kuali.kra.s2s.service.S2SService#getXmlFromPureEdge(java.util.List)
     */
    public List<BudgetSubAwards> getXmlFromPureEdge(List<BudgetSubAwards> subAwardList) {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * @see org.kuali.kra.s2s.service.S2SService#printForm(java.util.List)
     */
    public void printForm(List<S2sOppForms> formsList) {

    }


    /**
     * @see org.kuali.kra.s2s.service.S2SService#saveFormsNSubmitApplication(edu.mit.coeus.s2s.bean.S2SHeader)
     */
    public boolean saveFormsNSubmitApplication(List formsList, OpportunityInfoBean oppInfoBean, S2SHeader header) {
        try{
            Vector saveList = new Vector();
            saveList.add(formsList);
            saveList.add(oppInfoBean);
            s2sSubmissionTxnBean.addUpdDelOppForms(saveList);
            SubmissionEngine subEngine = new SubmissionEngine();
            subEngine.setLoggedInUser(loggedinUser);
            subEngine.setLogDir(reportPath);
            subEngine.submitApplication(header);
        }catch(Exception ex){
            LOG.error(ex.getMessage(), ex);
        }
        return true;
    }

    /**
     * @see org.kuali.kra.s2s.service.S2SService#saveGrantsGovData(java.util.List)
     */
    public boolean saveGrantsGovData(List objects) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see org.kuali.kra.s2s.service.S2SService#saveOpportunity(edu.mit.coeus.s2s.bean.OpportunityInfoBean)
     */
    public boolean saveOpportunity(OpportunityInfoBean opportunityInfoBean) {
        // TODO Auto-generated method stub
        return false;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
