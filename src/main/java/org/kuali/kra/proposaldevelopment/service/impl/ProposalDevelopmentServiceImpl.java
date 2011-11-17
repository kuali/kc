/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncServiceImpl;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.distributionincome.BudgetCostShare;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.proposaldevelopment.bo.CoPiInfoDO;
import org.kuali.kra.proposaldevelopment.bo.CostShareInfoDO;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalBudgetStatus;
import org.kuali.kra.proposaldevelopment.bo.ProposalColumnsToAlter;
import org.kuali.kra.proposaldevelopment.bo.ProposalOverview;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.service.KraPersistenceStructureService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;
import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;

// TODO : extends PersistenceServiceStructureImplBase is a hack to temporarily resolve get class descriptor.
public class ProposalDevelopmentServiceImpl implements ProposalDevelopmentService {
    
    protected final Log LOG = LogFactory.getLog(AwardSyncServiceImpl.class);
    private BusinessObjectService businessObjectService;
    private UnitAuthorizationService unitAuthService;
    private KraPersistenceStructureService kraPersistenceStructureService;
    private BudgetService budgetService;
    private ParameterService parameterService;
    private DocumentService documentService;
    private VersionHistoryService versionHistoryService;
    

    
    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    /**
     * This method gets called from the "save" action. It initializes the applicant org. on the first save;
     * it also sets the performing org. if the user didn't make a selection.
     * 
     * @param proposalDevelopmentDocument
     */
    public void initializeUnitOrganizationLocation(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        ProposalSite applicantOrganization = proposalDevelopmentDocument.getDevelopmentProposal().getApplicantOrganization();
        DevelopmentProposal developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();

        // Unit number chosen, set Applicant Organization
        if (developmentProposal.getOwnedByUnitNumber() != null && applicantOrganization.getOrganization() == null) {
            // get Lead Unit details
            developmentProposal.refreshReferenceObject("ownedByUnit");
            String applicantOrganizationId = developmentProposal.getOwnedByUnit().getOrganizationId();

            // get Organzation assoc. w/ Lead Unit, set applicant org
            applicantOrganization = createProposalSite(applicantOrganizationId, getNextSiteNumber(proposalDevelopmentDocument));
            developmentProposal.setApplicantOrganization(applicantOrganization);
        }

        // On first save, set Performing Organization if not selected
        ProposalSite performingOrganization = developmentProposal.getPerformingOrganization();
        if (StringUtils.isEmpty(developmentProposal.getProposalNumber()) && performingOrganization.getOrganization() == null && developmentProposal.getOwnedByUnitNumber() != null) {
            String performingOrganizationId = developmentProposal.getOwnedByUnit().getOrganizationId();
            performingOrganization = createProposalSite(performingOrganizationId, getNextSiteNumber(proposalDevelopmentDocument));
            developmentProposal.setPerformingOrganization(performingOrganization);
        }
    }

    /**
     * Constructs a ProposalSite; initializes the organization, and locationName fields,
     * and sets the default district if there is one defined for the Organization.
     * @param organizationId
     */
    protected ProposalSite createProposalSite(String organizationId, int siteNumber) {
        ProposalSite proposalSite = new ProposalSite();
        proposalSite.setOrganizationId(organizationId);
        proposalSite.refreshReferenceObject("organization");
        proposalSite.setLocationName(proposalSite.getOrganization().getOrganizationName());
        proposalSite.initializeDefaultCongressionalDistrict();
        return proposalSite;
    }
      
    protected int getNextSiteNumber(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        return proposalDevelopmentDocument.getDocumentNextValue(Constants.PROPOSAL_LOCATION_SEQUENCE_NUMBER);
    }
    
    // see interface for Javadoc
    public void initializeProposalSiteNumbers(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        for (ProposalSite proposalSite: proposalDevelopmentDocument.getDevelopmentProposal().getProposalSites())
            proposalSite.setSiteNumber(getNextSiteNumber(proposalDevelopmentDocument));
    }

    public List<Unit> getDefaultModifyProposalUnitsForUser(String userId) {
        return unitAuthService.getUnits(userId, Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, PermissionConstants.CREATE_PROPOSAL);
    }

    /**
     * Gets units for the given names. Useful when you know what you want.
     * 
     * @param unitNumbers varargs representation of unitNumber array
     * @return Collection<Unit>
     */
    protected Collection<Unit> getUnitsWithNumbers(String... unitNumbers) {
        Collection<Unit> retval = new ArrayList<Unit>();

        for (String unitNumber : unitNumbers) {
            Map<String, String> query_map = new HashMap<String, String>();
            query_map.put("unitNumber", unitNumber);
            retval.add((Unit) getBusinessObjectService().findByPrimaryKey(Unit.class, query_map));
        }

        return retval;
    }

    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     * 
     * @param bos BusinessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService bos) {
        businessObjectService = bos;
    }

    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     * 
     * @return BusinessObjectService
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    /**
     * Set the Unit Authorization Service.  Injected by Spring.
     * @param unitAuthService
     */
    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthService) {
        this.unitAuthService = unitAuthService;
    }

    public String populateProposalEditableFieldMetaDataForAjaxCall(String proposalNumber, String editableFieldDBColumn) {
        if(isAuthorizedToAccess(proposalNumber)){
            if (StringUtils.isNotBlank(proposalNumber) && proposalNumber.contains(Constants.COLON)) {
                proposalNumber = StringUtils.split(proposalNumber, Constants.COLON)[0];
            }
            return populateProposalEditableFieldMetaData(proposalNumber, editableFieldDBColumn);
        }
        return StringUtils.EMPTY;
    }
    
    protected ProposalOverview getProposalOverview(String proposalNumber) {
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("proposalNumber", proposalNumber);
        ProposalOverview currentProposal = (ProposalOverview) businessObjectService.findByPrimaryKey(ProposalOverview.class, primaryKeys);
        return currentProposal;
    }

    protected String getLookupDisplayValue(String lookupClassName, String value, String displayAttributeName) {
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        List<String> lookupClassPkFields = null;
        Class lookupClass = null;
        String displayValue = "";
        String returnValue = "";
        PersistableBusinessObject businessObject = null;
        
        if(StringUtils.isNotEmpty(lookupClassName)) {
            try {
                lookupClass = Class.forName(lookupClassName);
                lookupClassPkFields = (List<String>) kraPersistenceStructureService.getPrimaryKeys(lookupClass);
            }
            catch (ClassNotFoundException e) {}
            
            if(CollectionUtils.isNotEmpty(lookupClassPkFields)){
                returnValue = StringUtils.isNotEmpty(lookupClassPkFields.get(0)) ? lookupClassPkFields.get(0) : "";
                
                if(StringUtils.isNotEmpty(value)) {
                    primaryKeys.put(lookupClassPkFields.get(0), value);
                    businessObject = (PersistableBusinessObject) businessObjectService.findByPrimaryKey(lookupClass, primaryKeys);
                    if(businessObject != null) {
                        displayValue = getPropertyValue(businessObject, displayAttributeName);
                        displayValue = StringUtils.isNotEmpty(displayValue) ? displayValue : "";
                    }
                } 
            }
        } 
        
        return returnValue + "," + displayAttributeName + "," + displayValue;
    }
    
    
    public String getDataOverrideLookupDisplayReturnValue( String lookupClassName ) {
        List<String> lookupClassPkFields = null;
        String returnValue = "";
        Class lookupClass = null;
        
        if(StringUtils.isNotEmpty(lookupClassName)) {
            try {
                lookupClass = Class.forName(lookupClassName);
                lookupClassPkFields = (List<String>) kraPersistenceStructureService.getPrimaryKeys(lookupClass);
            }
            catch (ClassNotFoundException e) {}
      
            if(CollectionUtils.isNotEmpty(lookupClassPkFields)){
                returnValue = StringUtils.isNotEmpty(lookupClassPkFields.get(0)) ? lookupClassPkFields.get(0) : "";
      
            }
        
        }
        return returnValue;
    }
    
    public String getDataOverrideLookupDisplayDisplayValue( String lookupClassName, String value, String displayAttributeName ) {
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        List<String> lookupClassPkFields = null;
        Class lookupClass = null;
        String displayValue = "";
        PersistableBusinessObject businessObject = null;
        
        if(StringUtils.isNotEmpty(lookupClassName)) {
            try {
                lookupClass = Class.forName(lookupClassName);
                lookupClassPkFields = (List<String>) kraPersistenceStructureService.getPrimaryKeys(lookupClass);
            }
            catch (ClassNotFoundException e) {}
            
            if(CollectionUtils.isNotEmpty(lookupClassPkFields)){
                
                if(StringUtils.isNotEmpty(value)) {
                    primaryKeys.put(lookupClassPkFields.get(0), value);
                    businessObject = (PersistableBusinessObject) businessObjectService.findByPrimaryKey(lookupClass, primaryKeys);
                    if(businessObject != null) {
                        displayValue = getPropertyValue(businessObject, displayAttributeName);
                        displayValue = StringUtils.isNotEmpty(displayValue) ? displayValue : "";
                    }
                } 
            }
        } 
        
        return displayValue;
    }


    
    
    
    protected String getPropertyValue(BusinessObject businessObject, String fieldName) {
        String displayValue = "";
        try {
            displayValue = (String) ObjectUtils.getPropertyValue(businessObject, fieldName);
        }
        //Might happen due to Unknown Property Exception 
        catch (RuntimeException e) { }
        return displayValue;  
    }
    
    public Object getProposalFieldValueFromDBColumnName(String proposalNumber, String dbColumnName) {
        Object fieldValue = null;
        Map<String, String> fieldMap = kraPersistenceStructureService.getDBColumnToObjectAttributeMap(ProposalOverview.class);
        String proposalAttributeName = fieldMap.get(dbColumnName);
        if(StringUtils.isNotEmpty(proposalAttributeName)) {
            ProposalOverview currentProposal =  getProposalOverview(proposalNumber);
            if(currentProposal != null) {
                fieldValue = ObjectUtils.getPropertyValue(currentProposal, proposalAttributeName);
            }
        }
        return fieldValue;
    }
    
    protected String populateProposalEditableFieldMetaData(String proposalNumber, String editableFieldDBColumn) { 
        String returnValue = "";
        if(GlobalVariables.getErrorMap() != null) {
            GlobalVariables.getErrorMap().clear();
        }
        
        Object fieldValue = getProposalFieldValueFromDBColumnName(proposalNumber, editableFieldDBColumn);
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("columnName", editableFieldDBColumn);
        ProposalColumnsToAlter editableColumn = (ProposalColumnsToAlter) businessObjectService.findByPrimaryKey(ProposalColumnsToAlter.class, primaryKeys);
        
        if(editableColumn.getHasLookup()) {
            returnValue = getDataOverrideLookupDisplayReturnValue(editableColumn.getLookupClass()) + "," + editableColumn.getLookupReturn() + "," + getDataOverrideLookupDisplayDisplayValue( editableColumn.getLookupClass(), (fieldValue != null ? fieldValue.toString() : ""),editableColumn.getLookupReturn() );
        } else if (fieldValue != null && editableColumn.getDataType().equalsIgnoreCase("DATE")){
            returnValue = ",," + KNSServiceLocator.getDateTimeService().toString((Date) fieldValue, "MM/dd/yyyy");
        } else if (fieldValue != null) {
            returnValue = ",," + fieldValue.toString();
        } else {
            returnValue = ",,";
        }

        returnValue+=","+editableColumn.getDataType();
        returnValue+=","+editableColumn.getHasLookup();
        returnValue+=","+editableColumn.getLookupClass();
        
        return returnValue; 
    }
    
    @SuppressWarnings("unchecked")
    public Award getProposalCurrentAwardVersion(ProposalDevelopmentDocument proposal) {
        String awardNumber = proposal.getDevelopmentProposal().getCurrentAwardNumber();
        VersionHistory vh = versionHistoryService.findActiveVersion(Award.class, awardNumber);
        Award award = null;
        
        if(vh!=null){
            award = (Award) vh.getSequenceOwner();
        }else{
            HashMap<String, String> valueMap = new HashMap<String, String>();
            valueMap.put("awardNumber", awardNumber);
            List<Award> awards = (List<Award>)businessObjectService.findMatching(Award.class, valueMap);
            if (awards != null && !awards.isEmpty()) {
                award = awards.get(0);
            }
        }
        return award;
    }
    
    public InstitutionalProposal getProposalContinuedFromVersion(ProposalDevelopmentDocument proposal) {
        String proposalNumber = proposal.getDevelopmentProposal().getContinuedFrom();
        VersionHistory vh = versionHistoryService.findActiveVersion(InstitutionalProposal.class, proposalNumber);
        InstitutionalProposal ip = null;
        
        if (vh!=null) {
            ip = (InstitutionalProposal) vh.getSequenceOwner();
        } else if (StringUtils.isNotEmpty(proposalNumber)){
            HashMap<String, String> valueMap = new HashMap<String, String>();
            valueMap.put("proposalNumber", proposalNumber);
            List<InstitutionalProposal> proposals = (List<InstitutionalProposal>)businessObjectService.findMatching(InstitutionalProposal.class, valueMap);
            if (proposals != null && !proposals.isEmpty()) {
                ip = proposals.get(0);
            }
        }
        return ip;
    }

    public KraPersistenceStructureService getKraPersistenceStructureService() {
        return kraPersistenceStructureService;
    }

    public void setKraPersistenceStructureService(KraPersistenceStructureService kraPersistenceStructureService) {
        this.kraPersistenceStructureService = kraPersistenceStructureService;
    }

    /**
     * Retrieve injected <code>{@link BudgetService}</code> singleton
     * 
     * @return BudgetService
     */
    public BudgetService getBudgetService() {
        return budgetService;
    }

    /**
     * Inject <code>{@link BudgetService}</code> singleton
     * 
     * @return budgetService to assign
     */
    public void setBudgetService(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }

    public boolean isGrantsGovEnabledForProposal(DevelopmentProposal devProposal) {
        return !devProposal.isChild() && devProposal.getSponsor() != null && StringUtils.equals(devProposal.getSponsor().getSponsorTypeCode(), "0");
    }

    public boolean isGrantsGovEnabledOnSponsorChange(String proposalNumber, String sponsorCode) {
        DevelopmentProposal proposal = (DevelopmentProposal) getBusinessObjectService().findBySinglePrimaryKey(DevelopmentProposal.class, proposalNumber);
        Sponsor sponsor = (Sponsor) getBusinessObjectService().findBySinglePrimaryKey(Sponsor.class, sponsorCode);
        boolean enableGrantsGov = proposal == null || !proposal.isChild();
        enableGrantsGov &= sponsor != null && StringUtils.equals(sponsor.getSponsorTypeCode(), "0");
        return enableGrantsGov;
    }
    
    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService#deleteProposal(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public void deleteProposal(ProposalDevelopmentDocument proposalDocument) throws WorkflowException {
        ListIterator<BudgetDocumentVersion> iter = proposalDocument.getBudgetDocumentVersions().listIterator();
        while (iter.hasNext()) {
            BudgetDocumentVersion budgetVersion = iter.next();
            deleteProposalBudget(budgetVersion.getDocumentNumber(), proposalDocument);
            iter.remove();
        }
        //remove budget statuses as they are not referenced via ojb, but there is a
        //database constraint that requires removing these first
        Map<String, Object> keyValues = new HashMap<String, Object>();
        keyValues.put("proposalNumber", proposalDocument.getDevelopmentProposal().getProposalNumber());
        getBusinessObjectService().deleteMatching(ProposalBudgetStatus.class, keyValues);
        proposalDocument.getDevelopmentProposalList().clear();
        proposalDocument.getBudgetDocumentVersions().clear();
        proposalDocument.setProposalDeleted(true);
        
        //because the devproplist was cleared above the dev prop and associated BOs will be
        //deleted upon save
        getBusinessObjectService().save(proposalDocument); 
        getDocumentService().cancelDocument(proposalDocument, "Delete Proposal");
    }
    
    protected void deleteProposalBudget(String budgetDocumentNumber, ProposalDevelopmentDocument parentDocument) {
        try {
            BudgetDocument document = 
                (BudgetDocument) getDocumentService().getByDocumentHeaderId(budgetDocumentNumber);
            document.getBudgets().clear();
            //make sure the budget points to this instance of the pdd as other deleted budgets
            //must be removed so they don't fail document validation.
            document.setParentDocument(parentDocument);
            document.setBudgetDeleted(true);
            getDocumentService().saveDocument(document);
        }
        catch (WorkflowException e) {
            LOG.warn("Error getting budget document to delete", e);
        }
        
    }

    protected DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    /*
     * a utility method to check if dwr/ajax call really has authorization
     * 'updateProtocolFundingSource' also accessed by non ajax call
     */
    
    private boolean isAuthorizedToAccess(String proposalNumber) {
        boolean isAuthorized = true;
        if(proposalNumber.contains(Constants.COLON)){
            if (GlobalVariables.getUserSession() != null) {
                // TODO : this is a quick hack for KC 3.1.1 to provide authorization check for dwr/ajax call. dwr/ajax will be replaced by
                // jquery/ajax in rice 2.0
                String[] invalues = StringUtils.split(proposalNumber, Constants.COLON);
                String docFormKey = invalues[1];
                if (StringUtils.isBlank(docFormKey)) {
                    isAuthorized = false;
                } else {
                    Object formObj = GlobalVariables.getUserSession().retrieveObject(docFormKey);
                    if (formObj == null || !(formObj instanceof ProposalDevelopmentForm)) {
                        isAuthorized = false;
                    } else {
                        Map<String, String> editModes = ((ProposalDevelopmentForm)formObj).getEditingMode();
                        isAuthorized = BooleanUtils.toBoolean(editModes.get(AuthorizationConstants.EditMode.FULL_ENTRY))
                        || BooleanUtils.toBoolean(editModes.get(AuthorizationConstants.EditMode.VIEW_ONLY))
                        || BooleanUtils.toBoolean(editModes.get("modifyProposal"));
                    }
                }

            } else {
                // TODO : it seemed that tomcat has this issue intermittently ?
                LOG.info("dwr/ajax does not have session ");
            }
        }
        return isAuthorized;
    }

    public Budget getFinalBudget (DevelopmentProposal proposal) {
        List<BudgetDocumentVersion> budgetDocuments = proposal.getProposalDocument().getBudgetDocumentVersions();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        Budget budget = null;
        
        if (budgetDocuments != null && budgetDocuments.size() > 0) {            
            for(BudgetDocumentVersion budgetDocument : budgetDocuments) {
                fieldValues.clear();
                fieldValues.put("document_number", budgetDocument.getDocumentNumber());
                List<Budget> budgets = (List<Budget>) getBusinessObjectService().findMatching(Budget.class, fieldValues);
                budget = budgets.get(0);               
                // break out if we find the final budget
                if (budget.getFinalVersionFlag()) {
                    break;
                }
            }                         
        }   
        
        return budget;
    }
    
public List<CoPiInfoDO> getCoPiPiInfo (DevelopmentProposal proposal) {
        
        List<ProposalPerson> proposalPersons = proposal.getProposalPersons();
        List<CoPiInfoDO> coPiInfos = new ArrayList<CoPiInfoDO>();
        
        for (ProposalPerson proposalPerson : proposalPersons) {
            if (proposalPerson.getProposalPersonRoleId().equals(CO_INVESTIGATOR_ROLE)) {
                CoPiInfoDO coPiInfo = new CoPiInfoDO();
                coPiInfo.setCoPiUnit(proposalPerson.getHomeUnit());
                coPiInfo.setCoPiName(proposalPerson.getFullName());   
                coPiInfos.add(coPiInfo);
            } 
        }
        
        return coPiInfos;
    }

public List<CostShareInfoDO> getCostShareInfo (Budget budget) {       
    List<BudgetCostShare> costShares = budget.getBudgetCostShares();
    List<CostShareInfoDO> costShareInfos = new ArrayList<CostShareInfoDO>();
    
    if (costShares != null && costShares.size() > 0) {
        for (BudgetCostShare costShare : costShares) {
            if (!costShare.getSourceUnit().equals(Constants.THIRD_PARTY_UNIT_NO)) {
                CostShareInfoDO costShareInfo = new CostShareInfoDO();
                costShareInfo.setCostShareUnit(costShare.getSourceUnit());
                costShareInfo.setCostShareAmount(costShare.getShareAmount());
                costShareInfos.add(costShareInfo);
            }                            
        }
    }
    
    return costShareInfos;
}
    
}
