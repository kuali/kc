/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.web.struts.action;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.kra.award.home.keywords.AwardScienceKeyword;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.summary.BudgetSummaryService;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModular;
import org.kuali.kra.service.KeywordsService;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.ObjectUtils;


/**
 * 
 * This class represents the Struts Action for Award page(AwardHome.jsp) 
 */
public class AwardHomeAction extends AwardAction { 
    
    private static final String AWARD_VERSION_EDITPENDING_PROMPT_KEY = "message.award.version.editpending.prompt";
    private static final String DOC_HANDLER_URL_PATTERN = "%s/DocHandler.do?command=displayDocSearchView&docId=%s";
    private ApprovedSubawardActionHelper approvedSubawardActionHelper;
    
    public AwardHomeAction(){
        approvedSubawardActionHelper = new ApprovedSubawardActionHelper();
    }

    public ActionForward addFundingProposal(ActionMapping mapping, ActionForm form, 
                                            HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {
        ((AwardForm) form).getFundingProposalBean().addFundingProposal();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to add a new Award Cost Share
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward addApprovedSubaward(ActionMapping mapping, ActionForm form, 
                                                HttpServletRequest request,
                                                    HttpServletResponse response) throws Exception {
        approvedSubawardActionHelper.addApprovedSubaward(((AwardForm) form).getApprovedSubawardFormHelper());        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is a convenience method for adding an <code>AwardApprovedSubaward</code> to
     * <code>Award</code> business object.This way the add functionality can be tested
     * independently using a JUnit Test.
     * @param award
     * @param awardApprovedSubaward
     * @return
     */
    boolean addApprovedSubawardToAward(Award award, AwardApprovedSubaward awardApprovedSubaward){
        return award.getAwardApprovedSubawards().add(awardApprovedSubaward);
    }
    
    /**
     * This method is used to delete an Award Cost Share
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward deleteApprovedSubaward(ActionMapping mapping, ActionForm form, 
                                                    HttpServletRequest request,
                                                         HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        int delApprovedSubaward = getLineToDelete(request);
        awardDocument.getAward().getAwardApprovedSubawards().remove(delApprovedSubaward);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
     
    }
    
    /**
     * 
     * This method is a convenience method for deleting an <code>AwardApprovedSubaward</code> from
     * <code>Award</code> business object. This way the delete functionality can be tested
     * independently using a JUnit Test.
     * @param award
     * @param lineToDelete
     * @return
     */
    boolean deleteApprovedSubawardFromAward(Award award, int lineToDelete){
        award.getAwardApprovedSubawards().remove(lineToDelete);
        return true;
    }
    
    /**
     * 
     * This method opens a document in read-only mode
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward open(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        AwardForm awardForm = (AwardForm) form;
        String commandParam = request.getParameter(KNSConstants.PARAMETER_COMMAND);
        if (StringUtils.isNotBlank(commandParam) && commandParam.equals("initiate")
            && StringUtils.isNotBlank(request.getParameter(AWARD_ID_PARAMETER_NAME))) {
            Award award = findSelectedAward(request.getParameter(AWARD_ID_PARAMETER_NAME));
            initializeFormWithAward(awardForm, award);
        }
        
        return actionForward;
    }
    
    /**
     * This method is used to recalculate the Total Subaward amount in the Subaward panel.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward recalculateSubawardTotal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        approvedSubawardActionHelper.recalculateSubawardTotal(((AwardForm) form).getApprovedSubawardFormHelper());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
   
    /**
     * 
     * @see org.kuali.kra.award.web.struts.action.AwardAction#execute(org.apache.struts.action.ActionMapping, 
     *                                                                 org.apache.struts.action.ActionForm, 
     *                                                                 javax.servlet.http.HttpServletRequest, 
     *                                                                 javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        AwardForm awardForm = (AwardForm) form; 
        String commandParam = request.getParameter(KNSConstants.PARAMETER_COMMAND);
        if (StringUtils.isNotBlank(commandParam) && commandParam.equals("initiate")
            && StringUtils.isNotBlank(request.getParameter(AWARD_ID_PARAMETER_NAME))) {
            Award award = findSelectedAward(request.getParameter(AWARD_ID_PARAMETER_NAME));
            initializeFormWithAward(awardForm, award);
        }
        
        return actionForward;
    }
    
    /**
     * This takes care of populating the ScienceKeywords in keywords list after the selected Keywords returns from <code>multilookup</code>
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#refresh(org.apache.struts.action.ActionMapping, 
     *                                                                          org.apache.struts.action.ActionForm, 
     *                                                                          javax.servlet.http.HttpServletRequest, 
     *                                                                          javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("unchecked")
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);
        GlobalVariables.getUserSession().removeObject(Constants.LINKED_FUNDING_PROPOSALS_KEY);
        AwardForm awardMultiLookupForm = (AwardForm) form;
        String lookupResultsBOClassName = request.getParameter(KNSConstants.LOOKUP_RESULTS_BO_CLASS_NAME);
        String lookupResultsSequenceNumber = request.getParameter(KNSConstants.LOOKUP_RESULTS_SEQUENCE_NUMBER);
        awardMultiLookupForm.setLookupResultsBOClassName(lookupResultsBOClassName);
        awardMultiLookupForm.setLookupResultsSequenceNumber(lookupResultsSequenceNumber);
        Award awardDocument = awardMultiLookupForm.getAwardDocument().getAward();
        getKeywordService().addKeywords(awardDocument, awardMultiLookupForm);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Override to update funding proposal status as needed
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.save(mapping, form, request, response);
//        if(GlobalVariables.getMessageMap().getErrorCount() == 0) {
//            ((AwardForm) form).getFundingProposalBean().updateProposalStatuses();   // TODO: This save isn't in same transaction as document save
//        }

        return forward;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // The user is prompted to save when the document is closed.  If they say yes, it will save the document but won't call back to the Action.save.
        // Therefore we need to ensure the Award number is populated or else it will just save the default Award number.
        AwardForm awardForm = (AwardForm) form;
        checkAwardNumber(awardForm.getAwardDocument().getAward());
        return super.close(mapping, awardForm, request, response);
    }
    
    /**
     * Override to add lookup helper params to global variables
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public ActionForward performLookup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName) && parameterName.indexOf(".performLookup") != -1 && parameterName.contains("InstitutionalProposal")) {
            GlobalVariables.getUserSession().addObject(Constants.LINKED_FUNDING_PROPOSALS_KEY, ((AwardForm) form).getLinkedProposals());
        }

        return super.performLookup(mapping, form, request, response);
    }
    
    

    /**
     * 
     * This method...
     * @return
     */
    @SuppressWarnings("unchecked")
    protected KeywordsService getKeywordService(){
        return KraServiceLocator.getService(KeywordsService.class);
    }
    /**
     * 
     * This method is for selecting all keywords if javascript is disabled on a browser. 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return Basic ActionForward
     * @throws Exception
     */
    public ActionForward selectAllScienceKeyword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        List<AwardScienceKeyword> keywords = awardDocument.getAward().getKeywords();
        for (AwardScienceKeyword awardScienceKeyword : keywords) {
            awardScienceKeyword.setSelectKeyword(true);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is to delete selected keywords from the keywords list. 
     * It uses {@link KeywordsService} to process the request 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward deleteSelectedScienceKeyword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        KeywordsService keywordsService = KraServiceLocator.getService(KeywordsService.class);
        keywordsService.deleteKeyword(awardDocument.getAward()); 
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to handle the edit button action on an ACTIVE Award. If no Pending version exists for the same
     * awardNumber, a new Award version is created. If a Pending version exists, the user is prompted as to whether she would
     * like to edit the Pending version. Answering Yes results in that Pending version AwardDocument to be opened. Answering No 
     * simply returns the user to the ACTIVE document screen 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editOrVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        
        AwardForm awardForm = ((AwardForm)form);
        AwardDocument awardDocument = awardForm.getAwardDocument();
        Award award = awardDocument.getAward();
        ActionForward forward;
        
        if(getTimeAndMoneyExistenceService().validateTimeAndMoneyRule(award, awardForm.getAwardHierarchyNodes())){
            VersionHistory foundPending = findPendingVersion(award);
            cleanUpUserSession();
            if(foundPending != null) {
                Object question = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
                forward = question == null ? showPromptForEditingPendingVersion(mapping, form, request, response) :
                                             processPromptForEditingPendingVersionResponse(mapping, request, response, awardForm, foundPending);
            } else {
                forward = createAndSaveNewAwardVersion(response, awardForm, awardDocument, award);
            }    
        }else{
            getTimeAndMoneyExistenceService().addAwardVersionErrorMessage();
            forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        return forward;
    }

    private ActionForward createAndSaveNewAwardVersion(HttpServletResponse response, AwardForm awardForm,
                                                        AwardDocument awardDocument, Award award) throws Exception {
        Award newVersion = getVersioningService().createNewVersion(award);
        newVersion.getFundingProposals().clear();
        copyTimeAndMoneyData(award, newVersion);
        AwardDocument newAwardDocument = (AwardDocument) getDocumentService().getNewDocument(AwardDocument.class);
        newAwardDocument.getDocumentHeader().setDocumentDescription(awardDocument.getDocumentHeader().getDocumentDescription());
        newAwardDocument.setAward(newVersion);
        newVersion.setAwardTransactionTypeCode(0);
        getDocumentService().saveDocument(newAwardDocument);
        copyBudgetData(awardDocument, newAwardDocument);
        getVersionHistoryService().createVersionHistory(newVersion, VersionStatus.PENDING, GlobalVariables.getUserSession().getPrincipalName());
        reinitializeAwardForm(awardForm, newAwardDocument);
        return new ActionForward(makeDocumentOpenUrl(newAwardDocument), true);
    }

    /*
     * This method copies over the time and money data that is data in AwardAmountInfo list from old Award to new Award.
     * @param award
     * @param newVersion
     */
    private void copyTimeAndMoneyData(Award award, Award newVersion) {
        for(AwardAmountInfo awardAmountInfo : award.getAwardAmountInfos()){
            AwardAmountInfo newAwardAmountInfo = new AwardAmountInfo();
            newAwardAmountInfo.setAwardNumber(awardAmountInfo.getAwardNumber());
            newAwardAmountInfo.setSequenceNumber(awardAmountInfo.getSequenceNumber());
            newAwardAmountInfo.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
            newAwardAmountInfo.setCurrentFundEffectiveDate(awardAmountInfo.getCurrentFundEffectiveDate());
            newAwardAmountInfo.setObligationExpirationDate(awardAmountInfo.getObligationExpirationDate());
            newAwardAmountInfo.setTimeAndMoneyDocumentNumber(awardAmountInfo.getTimeAndMoneyDocumentNumber());
            newAwardAmountInfo.setTransactionId(awardAmountInfo.getTransactionId());
            
            newAwardAmountInfo.setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount());
            newAwardAmountInfo.setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate()); 
            newVersion.getAwardAmountInfos().add(newAwardAmountInfo);
        }
    }
    
    private void copyBudgetData(AwardDocument award, AwardDocument newVersion) throws Exception {
       List<String> budgetDocNumbers = new ArrayList<String>(award.getBudgetDocumentVersions().size());
        for (BudgetDocumentVersion budgetDocumentVersion : award.getBudgetDocumentVersions()) {
            budgetDocNumbers.add(budgetDocumentVersion.getDocumentNumber());
        }
        int i = 1;
        for (String docNumber : budgetDocNumbers) {
            BudgetDocument budgetDoc = copyAwardBudgetVersion(docNumber, newVersion, i++);
        }
        award.refreshReferenceObject("budgetDocumentVersions");
    } 
    
    private BudgetDocument copyAwardBudgetVersion(String documentNumber, BudgetParentDocument dest, int budgetVersionNumber) throws Exception {
        BudgetDocument budgetDocument = (BudgetDocument) getDocumentService().getByDocumentHeaderId(documentNumber);
        
        budgetDocument.toCopy();
        budgetDocument.setVersionNumber(null);
        if(budgetDocument.getBudgets().isEmpty()) {
            return null;
        }
        budgetDocument.getBudget().setBudgetVersionNumber(budgetVersionNumber);
        Map<String, Object> objectMap = new HashMap<String, Object>();
        fixNumericProperty(budgetDocument, "setBudgetId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetPeriodId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetLineItemId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetLineItemCalculatedAmountId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetPersonnelLineItemId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetPersonnelCalculatedAmountId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetPersonnelRateAndBaseId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetRateAndBaseId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setVersionNumber", Integer.class, null, objectMap);
        objectMap.clear();
        
        ObjectUtils.materializeAllSubObjects(budgetDocument.getBudget());

        Budget budget = budgetDocument.getBudget();
        AwardBudgetExt awardBudget = (AwardBudgetExt)budget;
        awardBudget.setBudgetStatus(getParameterService().getParameterValue(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_STATUS_IN_PROGRESS));

        budget.setFinalVersionFlag(false);
        budgetDocument.setParentDocumentKey(dest.getDocumentNumber());
        budgetDocument.setParentDocument(dest);
        
        //Work around for 1-to-1 Relationship between BudgetPeriod & BudgetModular
        Map<BudgetPeriod, BudgetModular> tmpBudgetModulars = new HashMap<BudgetPeriod, BudgetModular>(); 
        for(BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
            BudgetModular tmpObject = null;
            if(budgetPeriod.getBudgetModular() != null) {
                tmpObject = (BudgetModular) ObjectUtils.deepCopy(budgetPeriod.getBudgetModular());
            }
            tmpBudgetModulars.put(budgetPeriod, tmpObject);
            budgetPeriod.setBudgetModular(null);
        }
        
        List<BudgetProjectIncome> srcProjectIncomeList = budget.getBudgetProjectIncomes();
        budget.setBudgetProjectIncomes(new ArrayList<BudgetProjectIncome>());
        budget.setBudgetDocument(budgetDocument);
        budget.setDocumentNumber(budgetDocument.getDocumentNumber());
        getDocumentService().saveDocument(budgetDocument);
        
        for(BudgetPeriod tmpBudgetPeriod: budget.getBudgetPeriods()) {
            BudgetModular tmpBudgetModular = tmpBudgetModulars.get(tmpBudgetPeriod);
            if(tmpBudgetModular != null) {
                tmpBudgetModular.setBudgetPeriodId(tmpBudgetPeriod.getBudgetPeriodId());
                tmpBudgetPeriod.setBudgetModular(tmpBudgetModular);
            }
            
            for(BudgetProjectIncome budgetProjectIncome : srcProjectIncomeList) {
                if(budgetProjectIncome.getBudgetPeriodNumber().intValue() == tmpBudgetPeriod.getBudgetPeriod().intValue()) {
                    budgetProjectIncome.setBudgetPeriodId(tmpBudgetPeriod.getBudgetPeriodId());
                    budgetProjectIncome.setBudgetId(tmpBudgetPeriod.getBudget().getBudgetId());
                    budgetProjectIncome.setVersionNumber(new Long(0));
                }
            }
        }
        
        budget.setBudgetProjectIncomes(srcProjectIncomeList);
        getBudgetSummaryService().calculateBudget(budgetDocument.getBudget());
        getDocumentService().saveDocument(budgetDocument);
        budgetDocument.getParentDocument().refreshReferenceObject("budgetDocumentVersions");
        return budgetDocument;
  }
  
  /**
   * Recurse through all of the BOs and if a BO has a specific property,
   * set its value to the new value.
   * @param object the object
   * @param propertyValue 
   */
  private void fixNumericProperty(Object object, String methodName, Class clazz, Object propertyValue, Map<String, Object> objectMap) throws Exception {
      if(ObjectUtils.isNotNull(object) && object instanceof PersistableBusinessObject) {
          PersistableBusinessObject objectWId = (PersistableBusinessObject) object;
          if (objectMap.get(objectWId.getObjectId()) != null) return;
          objectMap.put(((PersistableBusinessObject) object).getObjectId(), object);
          
          Method[] methods = object.getClass().getMethods();
          for (Method method : methods) {
              if (method.getName().equals(methodName)) {
                    try {
                      if(clazz.equals(Long.class))
                          method.invoke(object, (Long) propertyValue);  
                      else 
                          method.invoke(object, (Integer) propertyValue);
                     } catch (Throwable e) { }  
              } else if (isPropertyGetterMethod(method, methods)) {
                  Object value = null;
                  try {
                      value = method.invoke(object);
                  } catch (Throwable e) {
                      //We don't need to propagate this exception
                  }
                  
                  if(value != null) {
                      if (value instanceof Collection) {
                          Collection c = (Collection) value;
                          Iterator iter = c.iterator();
                          while (iter.hasNext()) {
                              Object entry = iter.next();
                              fixNumericProperty(entry, methodName, clazz, propertyValue, objectMap);
                          }
                      } else {
                          fixNumericProperty(value, methodName, clazz, propertyValue, objectMap);
                      }   
                  }
              }
          }
      }
  }
  
      /**
       * Is the given method a getter method for a property?  Must conform to
       * the following:
       * <ol>
       * <li>Must start with the <b>get</b></li>
       * <li>Must have a corresponding setter method</li>
       * <li>Must have zero arguments.</li>
       * </ol>
       * @param method the method to check
       * @param methods the other methods in the object
       * @return true if it is property getter method; otherwise false
       */
    private boolean isPropertyGetterMethod(Method method, Method methods[]) {
        if (method.getName().startsWith("get") && method.getParameterTypes().length == 0) {
            String setterName = method.getName().replaceFirst("get", "set");
            for (Method m : methods) {
                if (m.getName().equals(setterName)) {
                    return true;
                }
            }
        }
        return false;
    }    
    
    /**
     * 
     * This method adds a new AwardTransferringSponsor to the list. 
     * It uses {@link KeywordsService} to process the request 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addAwardTransferringSponsor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ((AwardForm) form).getDetailsAndDatesFormHelper().addAwardTransferringSponsor();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /** 
     * This method removes an AwardTransferringSponsor from the list. 
     * It uses {@link KeywordsService} to process the request 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteAwardTransferringSponsor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ((AwardForm) form).getDetailsAndDatesFormHelper().deleteAwardTransferringSponsor(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method deletes a Funding proposal
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteAwardFundingProposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((AwardForm) form).getFundingProposalBean().deleteAwardFundingProposal(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    /**
     * @return
     */
    protected VersioningService getVersioningService() {
        return KraServiceLocator.getService(VersioningService.class);
    }
    
    protected BudgetSummaryService getBudgetSummaryService() {
        return KraServiceLocator.getService(BudgetSummaryService.class);
    }
    
    /**
     * This method locates an award for the specified awardId
     * @param awardId
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Award findSelectedAward(String awardId) {
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put(AWARD_ID_PARAMETER_NAME, awardId);
        List<Award> awards = (List<Award>) getBusinessObjectService().findMatching(Award.class, fieldMap);
        return (Award) awards.get(0);
    }

    private void initializeFormWithAward(AwardForm awardForm, Award award) throws WorkflowException {
        reinitializeAwardForm(awardForm, findDocumentForAward(award));
    }

    private AwardDocument findDocumentForAward(Award award) throws WorkflowException {
        AwardDocument document = (AwardDocument) getDocumentService().getByDocumentHeaderId(award.getAwardDocument().getDocumentNumber());
        document.setAward(award);
        return document;
    }
    
    /**
     * This method prepares the AwardForm with the document found via the Award lookup
     * Because the helper beans may have preserved a different AwardForm, we need to reset these too
     * @param awardForm
     * @param document
     */
    private void reinitializeAwardForm(AwardForm awardForm, AwardDocument document) throws WorkflowException {
        awardForm.populateHeaderFields(document.getDocumentHeader().getWorkflowDocument());
        awardForm.setDocument(document);
        document.setDocumentSaveAfterAwardLookupEditOrVersion(true);
        awardForm.initialize();
    }
    
    private String makeDocumentOpenUrl(AwardDocument newAwardDocument) {
        String workflowUrl = getKualiConfigurationService().getPropertyString(KNSConstants.WORKFLOW_URL_KEY);
        return String.format(DOC_HANDLER_URL_PATTERN, workflowUrl, newAwardDocument.getDocumentNumber());
    }
    
    private VersionHistory findPendingVersion(Award award) {
        List<VersionHistory> histories = getVersionHistoryService().loadVersionHistory(Award.class, award.getAwardNumber());
        VersionHistory foundPending = null;
        for(VersionHistory history: histories) {
            if (history.getStatus() == VersionStatus.PENDING && award.getSequenceNumber() < history.getSequenceOwnerSequenceNumber()) {
                foundPending = history;
                break;
            }
        }
        return foundPending;
    }

    private ActionForward showPromptForEditingPendingVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                                                HttpServletResponse response) throws Exception {
        return this.performQuestionWithoutInput(mapping, form, request, response, "EDIT_OR_VERSION_QUESTION_ID",
                                                getResources(request).getMessage(AWARD_VERSION_EDITPENDING_PROMPT_KEY),
                                                KNSConstants.CONFIRMATION_QUESTION,
                                                KNSConstants.MAPPING_CANCEL, "");
    }
    
    private ActionForward processPromptForEditingPendingVersionResponse(ActionMapping mapping, HttpServletRequest request,
            HttpServletResponse response, AwardForm awardForm, 
            VersionHistory foundPending) throws WorkflowException, 
                                                IOException {
        ActionForward forward;
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        if (ConfirmationQuestion.NO.equals(buttonClicked)) {
            forward = mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            initializeFormWithAward(awardForm, (Award) foundPending.getSequenceOwner());
            response.sendRedirect(makeDocumentOpenUrl(awardForm.getAwardDocument()));
            forward = null;
        }
        return forward;
    }
}
