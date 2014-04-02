/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.sponsor.SponsorService;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.sys.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.sys.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.sys.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncServiceImpl;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.distributionincome.BudgetCostShare;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.proposaldevelopment.bo.*;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetColumnsToAlter;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.s2s.S2sAppSubmission;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.*;

import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;

public class ProposalDevelopmentServiceImpl implements ProposalDevelopmentService {

    protected final Log LOG = LogFactory.getLog(AwardSyncServiceImpl.class);
    public static final String VALUE_UNKNOWN = "Unknown";
    private static final String FEDERAL_ID_COMES_FROM_CURRENT_AWARD = "FEDERAL_ID_COMES_FROM_CURRENT_AWARD";

    private BusinessObjectService businessObjectService;
    private DataObjectService dataObjectService;
    private UnitAuthorizationService unitAuthService;
    private KcPersistenceStructureService kraPersistenceStructureService;
    private BudgetService budgetService;
    private ParameterService parameterService;
    private DocumentService documentService;
    private VersionHistoryService versionHistoryService;  
    private RoleService roleService;
    private S2SService s2sService;
    private SponsorService sponsorService;
    private KeyPersonnelService keyPersonnelService;
    private UnitService unitService;
    private SystemAuthorizationService systemAuthorizationService;


    /**
     * Sets the ParameterService.
     * 
     * @param parameterService the parameter service.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    protected ParameterService getParameterService() {
        return parameterService;
    }

    /**
     * This method gets called from the "save" action. It initializes the applicant org. on the first save; it also sets the
     * performing org. if the user didn't make a selection.
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
        if (StringUtils.isEmpty(developmentProposal.getProposalNumber()) && performingOrganization.getOrganization() == null
                && developmentProposal.getOwnedByUnitNumber() != null) {
            String performingOrganizationId = developmentProposal.getOwnedByUnit().getOrganizationId();
            performingOrganization = createProposalSite(performingOrganizationId, getNextSiteNumber(proposalDevelopmentDocument));
            developmentProposal.setPerformingOrganization(performingOrganization);
        }
    }

    /**
     * Constructs a ProposalSite; initializes the organization, and locationName fields, and sets the default district if there is
     * one defined for the Organization.
     * 
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
        for (ProposalSite proposalSite : proposalDevelopmentDocument.getDevelopmentProposal().getProposalSites())
            if (proposalSite.getSiteNumber() == null) {
                proposalSite.setSiteNumber(getNextSiteNumber(proposalDevelopmentDocument));
            }
    }

    public List<Unit> getDefaultModifyProposalUnitsForUser(String userId) {
        return getUnitsForCreateProposal(userId);
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
     * Set the Unit Authorization Service. Injected by Spring.
     * 
     * @param unitAuthService
     */
    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthService) {
        this.unitAuthService = unitAuthService;
    }

    public String populateProposalEditableFieldMetaDataForAjaxCall(String proposalNumber, String editableFieldDBColumn) {
        if (isAuthorizedToAccess(proposalNumber)) {
            if (StringUtils.isNotBlank(proposalNumber) && proposalNumber.contains(Constants.COLON)) {
                proposalNumber = StringUtils.split(proposalNumber, Constants.COLON)[0];
            }
            return populateProposalEditableFieldMetaData(proposalNumber, editableFieldDBColumn);
        }
        return StringUtils.EMPTY;
    }
    public String populateBudgetEditableFieldMetaDataForAjaxCall(String proposalNumber, String documentNumber, String editableFieldDBColumn) {
        if (isAuthorizedToAccess(proposalNumber) && StringUtils.isNotBlank(documentNumber) && StringUtils.isNotBlank(editableFieldDBColumn)) {
            return populateBudgetEditableFieldMetaData(documentNumber, editableFieldDBColumn);
        }
        return StringUtils.EMPTY;
        
    }

    protected ProposalOverview getProposalOverview(String proposalNumber) {
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("proposalNumber", proposalNumber);
        ProposalOverview currentProposal = (ProposalOverview) businessObjectService.findByPrimaryKey(ProposalOverview.class,
                primaryKeys);
        return currentProposal;
    }
    
    protected BudgetVersionOverview getBudgetVersionOverview(String documentNumber) {
        BudgetVersionOverview currentBudget=null;
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("documentNumber", documentNumber);
        Collection<BudgetVersionOverview> currentBudgets = businessObjectService.findMatching(BudgetVersionOverview.class,
                primaryKeys);
        for (BudgetVersionOverview budgetVersionOverview:currentBudgets) {
            if (budgetVersionOverview.isFinalVersionFlag()) {
                currentBudget = budgetVersionOverview;
                break;
            }
        }
        return currentBudget;
    }

    public String getDataOverrideLookupDisplayReturnValue(String lookupClassName) {
        List<String> lookupClassPkFields = null;
        String returnValue = "";
        Class lookupClass = null;

        if (StringUtils.isNotEmpty(lookupClassName)) {
            try {
                lookupClass = Class.forName(lookupClassName);
                lookupClassPkFields = (List<String>) kraPersistenceStructureService.getPrimaryKeys(lookupClass);
            }
            catch (ClassNotFoundException e) {
            }

            if (CollectionUtils.isNotEmpty(lookupClassPkFields)) {
                returnValue = StringUtils.isNotEmpty(lookupClassPkFields.get(0)) ? lookupClassPkFields.get(0) : "";

            }

        }
        return returnValue;
    }

    protected String getDataOverrideLookupDisplayDisplayValue(String lookupClassName, String value, String displayAttributeName) {
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        List<String> lookupClassPkFields = null;
        Class lookupClass = null;
        String displayValue = "";
        PersistableBusinessObject businessObject = null;

        if (StringUtils.isNotEmpty(lookupClassName)) {
            try {
                lookupClass = Class.forName(lookupClassName);
                lookupClassPkFields = (List<String>) kraPersistenceStructureService.getPrimaryKeys(lookupClass);
            }
            catch (ClassNotFoundException e) {
            }

            if (CollectionUtils.isNotEmpty(lookupClassPkFields)) {

                if (StringUtils.isNotEmpty(value)) {
                    primaryKeys.put(lookupClassPkFields.get(0), value);
                    businessObject = (PersistableBusinessObject) businessObjectService.findByPrimaryKey(lookupClass, primaryKeys);
                    if (businessObject != null) {
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
        // Might happen due to Unknown Property Exception
        catch (RuntimeException e) {
        }
        return displayValue;
    }

    public Object getProposalFieldValueFromDBColumnName(String proposalNumber, String dbColumnName) {
        Object fieldValue = null;
        Map<String, String> fieldMap = kraPersistenceStructureService.getDBColumnToObjectAttributeMap(ProposalOverview.class);
        String proposalAttributeName = fieldMap.get(dbColumnName);
        if (StringUtils.isNotEmpty(proposalAttributeName)) {
            ProposalOverview currentProposal = getProposalOverview(proposalNumber);
            if (currentProposal != null) {
                fieldValue = ObjectUtils.getPropertyValue(currentProposal, proposalAttributeName);
            }
        }
        return fieldValue;
    }
    
    public Object getBudgetFieldValueFromDBColumnName(String documentNumber, String dbColumnName) {
        Object fieldValue = null;        
        Map<String, String> fieldMap = kraPersistenceStructureService.getDBColumnToObjectAttributeMap(BudgetVersionOverview.class);
        String budgetAttributeName = fieldMap.get(dbColumnName);
        if (StringUtils.isNotEmpty(budgetAttributeName)) {
            BudgetVersionOverview currentBudget = getBudgetVersionOverview(documentNumber);            
            if (currentBudget != null) {
                fieldValue = ObjectUtils.getPropertyValue(currentBudget, budgetAttributeName);
            }
        }            
        return fieldValue;    
             
    }

    protected String populateProposalEditableFieldMetaData(String proposalNumber, String editableFieldDBColumn) {
        String returnValue = "";
        if (GlobalVariables.getMessageMap() != null) {
            GlobalVariables.getMessageMap().clearErrorMessages();
        }

        Object fieldValue = getProposalFieldValueFromDBColumnName(proposalNumber, editableFieldDBColumn);
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("columnName", editableFieldDBColumn);
        ProposalColumnsToAlter editableColumn = (ProposalColumnsToAlter) businessObjectService.findByPrimaryKey(
                ProposalColumnsToAlter.class, primaryKeys);

        if (editableColumn.getHasLookup()) {
            returnValue = getDataOverrideLookupDisplayReturnValue(editableColumn.getLookupClass())
                    + ","
                    + editableColumn.getLookupReturn()
                    + ","
                    + getDataOverrideLookupDisplayDisplayValue(editableColumn.getLookupClass(),
                            (fieldValue != null ? fieldValue.toString() : ""), editableColumn.getLookupReturn());
        }
        else if (fieldValue != null && editableColumn.getDataType().equalsIgnoreCase("DATE")) {
            returnValue = ",," + CoreApiServiceLocator.getDateTimeService().toString((Date) fieldValue, "MM/dd/yyyy");
        }
        else if (fieldValue != null) {
            returnValue = ",," + fieldValue.toString();
        }
        else {
            returnValue = ",,";
        }

        returnValue += "," + editableColumn.getDataType();
        returnValue += "," + editableColumn.getHasLookup();
        returnValue += "," + editableColumn.getLookupClass();

        return returnValue;
    }
 
    @SuppressWarnings("unchecked")
    public Award getProposalCurrentAwardVersion(ProposalDevelopmentDocument proposal) {
        String awardNumber = proposal.getDevelopmentProposal().getCurrentAwardNumber();
        VersionHistory vh = versionHistoryService.findActiveVersion(Award.class, awardNumber);
        Award award = null;

        if (vh != null) {
            award = (Award) vh.getSequenceOwner();
        }
        else {
            HashMap<String, String> valueMap = new HashMap<String, String>();
            valueMap.put("awardNumber", awardNumber);
            List<Award> awards = (List<Award>) businessObjectService.findMatching(Award.class, valueMap);
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

        if (vh != null) {
            ip = (InstitutionalProposal) vh.getSequenceOwner();
        }
        else if (StringUtils.isNotEmpty(proposalNumber)) {
            HashMap<String, String> valueMap = new HashMap<String, String>();
            valueMap.put("proposalNumber", proposalNumber);
            List<InstitutionalProposal> proposals = (List<InstitutionalProposal>) businessObjectService.findMatching(
                    InstitutionalProposal.class, valueMap);
            if (proposals != null && !proposals.isEmpty()) {
                ip = proposals.get(0);
            }
        }
        return ip;
    }

    public KcPersistenceStructureService getKraPersistenceStructureService() {
        return kraPersistenceStructureService;
    }

    public void setKraPersistenceStructureService(KcPersistenceStructureService kraPersistenceStructureService) {
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
        String federalSponsorTypeCode = parameterService.getParameterValueAsString(AwardDocument.class, Constants.FEDERAL_SPONSOR_TYPE_CODE);
        return !devProposal.isChild() && devProposal.getSponsor() != null
                && StringUtils.equals(devProposal.getSponsor().getSponsorTypeCode(), federalSponsorTypeCode);
    }

    public boolean isGrantsGovEnabledOnSponsorChange(String proposalNumber, String sponsorCode) {
        String federalSponsorTypeCode = parameterService.getParameterValueAsString(AwardDocument.class, Constants.FEDERAL_SPONSOR_TYPE_CODE);
        DevelopmentProposal proposal = (DevelopmentProposal) getBusinessObjectService().findBySinglePrimaryKey(
                DevelopmentProposal.class, proposalNumber);
        Sponsor sponsor = (Sponsor) getBusinessObjectService().findBySinglePrimaryKey(Sponsor.class, sponsorCode);
        boolean enableGrantsGov = proposal == null || !proposal.isChild();
        enableGrantsGov &= sponsor != null && StringUtils.equals(sponsor.getSponsorTypeCode(), federalSponsorTypeCode);
        return enableGrantsGov;
    }

    @Override
    public ProposalDevelopmentDocument deleteProposal(ProposalDevelopmentDocument proposalDocument) throws WorkflowException {
        ListIterator<BudgetDocumentVersion> iter = proposalDocument.getBudgetDocumentVersions().listIterator();
        while (iter.hasNext()) {
            BudgetDocumentVersion budgetVersion = iter.next();
            deleteProposalBudget(budgetVersion.getDocumentNumber(), proposalDocument);
            iter.remove();
        }
        // remove budget statuses as they are not referenced via ojb, but there is a
        // database constraint that requires removing these first
        Map<String, Object> keyValues = new HashMap<String, Object>();
        keyValues.put("proposalNumber", proposalDocument.getDevelopmentProposal().getProposalNumber());
        dataObjectService.deleteMatching(ProposalBudgetStatus.class, QueryByCriteria.Builder.andAttributes(keyValues).build());
        proposalDocument.setDevelopmentProposal(null);
        proposalDocument.setBudgetDocumentVersions(null);
        proposalDocument.setProposalDeleted(true);

        // because the devproplist was cleared above the dev prop and associated BOs will be
        // deleted upon save
        proposalDocument = dataObjectService.save(proposalDocument);
        return (ProposalDevelopmentDocument) getDocumentService().cancelDocument(proposalDocument, "Delete Proposal");
    }

    protected void deleteProposalBudget(String budgetDocumentNumber, ProposalDevelopmentDocument parentDocument) {
        try {
            BudgetDocument<DevelopmentProposal> document = (BudgetDocument<DevelopmentProposal>) getDocumentService().getByDocumentHeaderId(budgetDocumentNumber);
            document.getBudgets().clear();
            // make sure the budget points to this instance of the pdd as other deleted budgets
            // must be removed so they don't fail document validation.
            document.setParentDocument(parentDocument);
            document.setBudgetDeleted(true);
            document = (BudgetDocument<DevelopmentProposal>) getDocumentService().saveDocument(document);
        }
        catch (WorkflowException e) {
            throw new RuntimeException(e);
        }

    }

    protected DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    /*
     * a utility method to check if dwr/ajax call really has authorization 'updateProtocolFundingSource' also accessed by non ajax
     * call
     */

    private boolean isAuthorizedToAccess(String proposalNumber) {
        boolean isAuthorized = true;
        if (proposalNumber.contains(Constants.COLON)) {
            if (GlobalVariables.getUserSession() != null) {
                // TODO : this is a quick hack for KC 3.1.1 to provide authorization check for dwr/ajax call. dwr/ajax will be
                // replaced by
                // jquery/ajax in rice 2.0
                String[] invalues = StringUtils.split(proposalNumber, Constants.COLON);
                String docFormKey = invalues[1];
                if (StringUtils.isBlank(docFormKey)) {
                    isAuthorized = false;
                }
                else {
                    Object formObj = GlobalVariables.getUserSession().retrieveObject(docFormKey);
                    if (formObj == null || !(formObj instanceof ProposalDevelopmentForm)) {
                        isAuthorized = false;
                    }
                    else {
                        Map<String, String> editModes = ((ProposalDevelopmentForm) formObj).getEditingMode();
                        isAuthorized = BooleanUtils.toBoolean(editModes.get(AuthorizationConstants.EditMode.FULL_ENTRY))
                                || BooleanUtils.toBoolean(editModes.get(AuthorizationConstants.EditMode.VIEW_ONLY))
                                || BooleanUtils.toBoolean(editModes.get("modifyProposal"));
                    }
                }

            }
            else {
                // TODO : it seemed that tomcat has this issue intermittently ?
                LOG.info("dwr/ajax does not have session ");
            }
        }
        return isAuthorized;
    }

    public Budget getFinalBudget(DevelopmentProposal proposal) {
        List<BudgetDocumentVersion> budgetDocuments = proposal.getProposalDocument().getBudgetDocumentVersions();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        Budget budget = null;

        if (budgetDocuments != null && budgetDocuments.size() > 0) {
            for (BudgetDocumentVersion budgetDocument : budgetDocuments) {
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

    public List<CoPiInfoDO> getCoPiPiInfo(DevelopmentProposal proposal) {

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

    public List<CostShareInfoDO> getCostShareInfo(Budget budget) {
        List<BudgetCostShare> costShares = budget.getBudgetCostShares();
        List<CostShareInfoDO> costShareInfos = new ArrayList<CostShareInfoDO>();

        if (costShares != null && costShares.size() > 0) {
            for (BudgetCostShare costShare : costShares) {
                if (!Constants.THIRD_PARTY_UNIT_NO.equals(costShare.getSourceUnit())) {
                    CostShareInfoDO costShareInfo = new CostShareInfoDO();
                    costShareInfo.setCostShareUnit(costShare.getSourceUnit());
                    costShareInfo.setCostShareAmount(costShare.getShareAmount());
                    costShareInfos.add(costShareInfo);
                }
            }
        }

        return costShareInfos;
    }

    /**
     * Return the institutional proposal linked to the development proposal.
     */
    public InstitutionalProposal getInstitutionalProposal(String devProposalNumber) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("devProposalNumber", devProposalNumber);
        Collection<ProposalAdminDetails> proposalAdminDetails = businessObjectService.findMatching(ProposalAdminDetails.class,
                values);

        for (Iterator iter = proposalAdminDetails.iterator(); iter.hasNext();) {
            ProposalAdminDetails pad = (ProposalAdminDetails) iter.next();
            pad.refreshReferenceObject("institutionalProposal");
            return pad.getInstitutionalProposal();
        }
        return null;
    }
    protected String populateBudgetEditableFieldMetaData(
            String documentNumber, String editableFieldDBColumn) {
        String returnValue  = "";
      
        //BudgetDocument budgetDocument = null;
        if (GlobalVariables.getMessageMap() != null) {
            GlobalVariables.getMessageMap().clearErrorMessages();
        }      
        Object fieldValue = getBudgetFieldValueFromDBColumnName(documentNumber, editableFieldDBColumn);
        
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("columnName", editableFieldDBColumn);
        BudgetColumnsToAlter editableColumn = (BudgetColumnsToAlter) businessObjectService.findByPrimaryKey(
                BudgetColumnsToAlter.class, primaryKeys);            
        if (editableColumn.getHasLookup()) {
            returnValue = getDataOverrideLookupDisplayReturnValue(editableColumn.getLookupClass())
                    + ","
                    + editableColumn.getLookupReturn()
                    + ","
                    + getDataOverrideLookupDisplayDisplayValue(editableColumn.getLookupClass(),
                            (fieldValue != null ? fieldValue.toString() : ""), editableColumn.getLookupReturn());
        }
        else if (fieldValue != null && editableColumn.getDataType().equalsIgnoreCase("DATE")) {
            returnValue = ",," + CoreApiServiceLocator.getDateTimeService().toString((Date) fieldValue, "MM/dd/yyyy");
        }
        else if (fieldValue != null) {
            returnValue = ",," + fieldValue.toString();
        }
        else {
            returnValue = ",,";
        }
        if (fieldValue instanceof Boolean) {
            editableColumn.setDataType("boolean");
        }
      
        returnValue += "," + editableColumn.getDataType();
        returnValue += "," + editableColumn.getHasLookup();
        returnValue += "," + editableColumn.getLookupClass();

        return returnValue;
    }
    
    public boolean canSaveProposalXml(ProposalDevelopmentDocument document) {
        String principalId = GlobalVariables.getUserSession().getPerson().getPrincipalId();
        Role roleInfo  = getRoleService().getRoleByNamespaceCodeAndName(RoleConstants.OSP_ROLE_TYPE, RoleConstants.OSP_ADMINISTRATOR);
        List<String> roleIds = new ArrayList<String>();
        roleIds.add(roleInfo.getId());
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(KcKimAttributes.UNIT_NUMBER, "*");
        Map<String,String> qualifications = new HashMap<String,String>(qualifiedRoleAttributes);
        if(getRoleService().principalHasRole(principalId, roleIds, qualifications)){
            return true;
        }
        KcAuthorizationService proposalAuthService = KcServiceLocator.getService(KcAuthorizationService.class);
        List<String> users = proposalAuthService.getPrincipalsInRole(document, RoleConstants.AGGREGATOR);

        for (String user : users) {
            if(GlobalVariables.getUserSession().getPrincipalId().equals(user)){
                return true;
            }
        }
        
        return false;
    }
    
    public Long createS2sOpportunityDetails(DevelopmentProposal proposal, S2sOpportunity s2sOpportunity, Long versionNumberForS2sOpportunity) {
        Long result = versionNumberForS2sOpportunity;
        
        Boolean mandatoryFormNotAvailable = false;
        if(s2sOpportunity.getCfdaNumber()!=null){
            proposal.setCfdaNumber(s2sOpportunity.getCfdaNumber());
        }
        if(s2sOpportunity.getOpportunityId()!=null){
            proposal.setProgramAnnouncementNumber(s2sOpportunity.getOpportunityId());
        }
        if(s2sOpportunity.getOpportunityTitle()!=null){
            proposal.setProgramAnnouncementTitle(s2sOpportunity.getOpportunityTitle());
        }
        List<S2sOppForms> s2sOppForms = new ArrayList<S2sOppForms>();
        if(s2sOpportunity.getSchemaUrl()!=null){
            s2sOppForms = getS2sService().parseOpportunityForms(s2sOpportunity);
            if(s2sOppForms!=null){
                for(S2sOppForms s2sOppForm:s2sOppForms){
                    if(s2sOppForm.getMandatory() && !s2sOppForm.getAvailable()){
                        mandatoryFormNotAvailable = true;
                        break;
                    }
                }
            }
            if(!mandatoryFormNotAvailable){
                s2sOpportunity.setS2sOppForms(s2sOppForms);
                s2sOpportunity.setVersionNumber(versionNumberForS2sOpportunity);
                proposal.setS2sOpportunity(s2sOpportunity);
                result = null;
            }else{
                GlobalVariables.getMessageMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID,
                        proposal.getS2sOpportunity().getOpportunityId());
                proposal.setS2sOpportunity(new S2sOpportunity());
            }
        }
        return result;
    }
        
    public Map<String, Parameter> getProposalDevelopmentParameters() {
        Map<String, Parameter> result = new HashMap<String, Parameter>();
        result.put(DELIVERY_INFO_DISPLAY_INDICATOR, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, DELIVERY_INFO_DISPLAY_INDICATOR));
        result.put(PROPOSAL_NARRATIVE_TYPE_GROUP, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, PROPOSAL_NARRATIVE_TYPE_GROUP));
        result.put(PROPOSAL_SUMMARY_INDICATOR, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, PROPOSAL_SUMMARY_INDICATOR));
        result.put(BUDGET_SUMMARY_INDICATOR, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, BUDGET_SUMMARY_INDICATOR));
        result.put(KEY_PERSONNEL_INDICATOR, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, KEY_PERSONNEL_INDICATOR));
        result.put(SPECIAL_REVIEW_INDICATOR, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, SPECIAL_REVIEW_INDICATOR));
        result.put(SUMMARY_PRINT_FORMS_INDICATOR, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, SUMMARY_PRINT_FORMS_INDICATOR));
        result.put(CUSTOM_DATA_INFO_INDICATOR, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, CUSTOM_DATA_INFO_INDICATOR));
        result.put(SUMMARY_QUESTIONS_INDICATOR, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, SUMMARY_QUESTIONS_INDICATOR));
        result.put(SUMMARY_ATTACHMENTS_INDICATOR, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, SUMMARY_ATTACHMENTS_INDICATOR));
        result.put(SUMMARY_KEYWORDS_INDICATOR, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, SUMMARY_KEYWORDS_INDICATOR));
        result.put(SUMMARY_DATA_VALIDATION_INDICATOR, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, SUMMARY_DATA_VALIDATION_INDICATOR));       
        result.put(PROPOSAL_SUMMARY_DISCLAIMER_INDICATOR, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, PROPOSAL_SUMMARY_DISCLAIMER_INDICATOR));
        result.put(SUMMARY_SPECIAL_REVIEW_LIST, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, SUMMARY_SPECIAL_REVIEW_LIST));
        result.put(PROPOSAL_NARRATIVE_TYPE_GROUP2,getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, PROPOSAL_NARRATIVE_TYPE_GROUP2));
        return result;
    }
    
    public void updateNIHDescriptions(DevelopmentProposal proposal) {
        SponsorService sponsorService = getSponsorService();
        // Update the NIH related properties since this information is not persisted with the document
        // (isSponsorNih sets the nih property as a side effect)
        if (sponsorService.isSponsorNihMultiplePi(proposal)) {
            proposal.setNihDescription(getKeyPersonnelService().loadKeyPersonnelRoleDescriptions(true));
        }
        boolean multiPIFlag = getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class,
                ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
        if (multiPIFlag) {
            proposal.setSponsorNihMultiplePi(true);
            proposal.setSponsorNihOsc(true);
        } else {
            proposal.setSponsorNihMultiplePi(sponsorService.isSponsorNihMultiplePi(proposal));
            proposal.setSponsorNihOsc(sponsorService.isSponsorNihOsc(proposal));
        }
    }
    
    /**
     * 
     * This method attempts to deal with the multiple pessimistic locks that can be on the proposal development document
     * Proposal, Narratives, and Budget must all be treated separately and therefore the other portions of the document,
     * outside of the one currently being saved, must be updated from the database to make sure the sessions do not stomp
     * changes already persisted by another session.
     * @throws Exception
     */
    public ProposalDevelopmentDocument updateProposalDocument(ProposalDevelopmentDocument pdDocument, ProposalLockingRegion region) throws Exception {
        ProposalDevelopmentDocument updatedDocCopy = getProposalDoc(pdDocument.getDocumentNumber());
        
        if (updatedDocCopy != null) {
            
            //For Budget and Narrative Lock regions, this is the only way in which a Proposal Document might get updated
            if (region != null && updatedDocCopy != null) {
                if (region != ProposalLockingRegion.BUDGET) {
                    pdDocument.setBudgetDocumentVersions(updatedDocCopy.getBudgetDocumentVersions());
                    pdDocument.getDevelopmentProposal().setBudgetStatus(updatedDocCopy.getDevelopmentProposal().getBudgetStatus());
                } else {
                    //in case other parts of the document have been saved since we have saved,
                    //we save off possibly changed parts and reload the rest of the document
                    List<BudgetDocumentVersion> newVersions = pdDocument.getBudgetDocumentVersions();
                    String budgetStatus = pdDocument.getDevelopmentProposal().getBudgetStatus();
                    
                    pdDocument = updatedDocCopy;
                    loadDocument(pdDocument);
                    
                    pdDocument.setBudgetDocumentVersions(newVersions);
                    pdDocument.getDevelopmentProposal().setBudgetStatus(budgetStatus);                  
                }
                if (region != ProposalLockingRegion.ATTACHMENTS) {
                    pdDocument.getDevelopmentProposal().setNarratives(updatedDocCopy.getDevelopmentProposal().getNarratives());
                    pdDocument.getDevelopmentProposal().setInstituteAttachments(updatedDocCopy.getDevelopmentProposal().getInstituteAttachments());
                    pdDocument.getDevelopmentProposal().setProposalAbstracts(updatedDocCopy.getDevelopmentProposal().getProposalAbstracts());
                    pdDocument.getDevelopmentProposal().setPropPersonBios(updatedDocCopy.getDevelopmentProposal().getPropPersonBios());
                    removePersonnelAttachmentForDeletedPerson(pdDocument);
               } else {
                   //in case other parts of the document have been saved since we have saved,
                   //we save off possibly changed parts and reload the rest of the document
                   List<Narrative> newNarratives = pdDocument.getDevelopmentProposal().getNarratives();
                   List<Narrative> instituteAttachments = pdDocument.getDevelopmentProposal().getInstituteAttachments();
                   List<ProposalAbstract> newAbstracts = pdDocument.getDevelopmentProposal().getProposalAbstracts();
                   List<ProposalPersonBiography> newBiographies = pdDocument.getDevelopmentProposal().getPropPersonBios();
    
                   pdDocument = updatedDocCopy;
                   loadDocument(pdDocument);
                   
                    List<Narrative> newNarrativesCopy = new ArrayList<Narrative>();
                    if (newNarratives != null) {
                        for (Narrative refreshNarrativesList : newNarratives) {
                            if (refreshNarrativesList.getNarrativeType().getNarrativeTypeGroup()
                                    .equalsIgnoreCase(PROPOSAL_ATTACHMENT_TYPE_GROUP_CODE)) {
                                newNarrativesCopy.add(refreshNarrativesList);
                            }
                        }
                    }
                   //now re-add narratives that could include changes and can't be modified otherwise
                   pdDocument.getDevelopmentProposal().setNarratives(newNarrativesCopy);
                   pdDocument.getDevelopmentProposal().setInstituteAttachments(instituteAttachments);
                   pdDocument.getDevelopmentProposal().setProposalAbstracts(newAbstracts);
                   pdDocument.getDevelopmentProposal().setPropPersonBios(newBiographies);
    
               }
            }
            
            //rice objects are still using optimistic locking so update rice BO versions
            //if no other session has saved this document we should be updating with same version number, but no easy way to know if it has been
            //I don't think anyway? So do it every time.
            pdDocument.getDocumentHeader().setVersionNumber(updatedDocCopy.getDocumentHeader().getVersionNumber());
            int noteIndex = 0;
            for(Object note: pdDocument.getNotes()) {
                Note updatedNote = updatedDocCopy.getNote(noteIndex);
                ((Note) note).setVersionNumber(updatedNote.getVersionNumber());
                noteIndex++;
            }
            for(DocumentNextvalue documentNextValue : pdDocument.getDocumentNextvalues()) {
                DocumentNextvalue updatedDocumentNextvalue = updatedDocCopy.getDocumentNextvalueBo(documentNextValue.getPropertyName());
                if(updatedDocumentNextvalue != null) {
                    documentNextValue.setVersionNumber(updatedDocumentNextvalue.getVersionNumber());
                }
            }   
            //fix budget document version's document headers
            for (int i = 0; i < pdDocument.getBudgetDocumentVersions().size(); i++) {
                BudgetDocumentVersion curVersion = pdDocument.getBudgetDocumentVersion(i);
                BudgetDocumentVersion otherVersion = updatedDocCopy.getBudgetDocumentVersion(i);
                otherVersion.refreshReferenceObject("documentHeader");
                if (curVersion != null && otherVersion != null) {
                    curVersion.getDocumentHeader().setVersionNumber(otherVersion.getDocumentHeader().getVersionNumber());
                }
            }
        }
        return pdDocument;
    }
    
    /*
     * The updatePD has some issue, such as if person is deleted, and the person attachment is also deleted.
     * However, the updateProposalDocument recover everything from DB.  so, add this method to delete the deleted, but not saved personnel attachment.
     */
    protected void removePersonnelAttachmentForDeletedPerson(ProposalDevelopmentDocument proposaldevelopmentDocument) {

        List<ProposalPersonBiography> personAttachments = new ArrayList();
        for (ProposalPersonBiography proposalPersonBiography : proposaldevelopmentDocument.getDevelopmentProposal()
                .getPropPersonBios()) {
            boolean personFound = false;
            for (ProposalPerson person : proposaldevelopmentDocument.getDevelopmentProposal().getProposalPersons()) {
                if (proposalPersonBiography.getProposalPersonNumber().equals(person.getProposalPersonNumber())) {
                    personFound = true;
                    break;
                }
            }
            if (!personFound) {
                personAttachments.add(proposalPersonBiography);
            }

        }
        if (!personAttachments.isEmpty()) {
            proposaldevelopmentDocument.getDevelopmentProposal().getPropPersonBios().removeAll(personAttachments);
        }
    }

    protected ProposalDevelopmentDocument getProposalDoc(String pdDocumentNumber) throws Exception {
        ProposalDevelopmentDocument newCopy;
        DocumentService docService = KcServiceLocator.getService(DocumentService.class);
        newCopy = (ProposalDevelopmentDocument)docService.getByDocumentHeaderId(pdDocumentNumber);        
        return newCopy;
    }
    
    @Override
    public void loadDocument(ProposalDevelopmentDocument document) {
        getKeyPersonnelService().populateDocument(document);
        updateNIHDescriptions(document.getDevelopmentProposal());
        getBudgetService().setBudgetStatuses(document);
    }
    
    /** 
     * @see org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService#constructColumnsToAlterLookupMTCs(java.lang.String)
     */
    public List<String> constructColumnsToAlterLookupMTCs(String proposalNumber) {
        Map<String,Object> filterMap = new HashMap<String,Object>();
        ProposalDevelopmentService proposalDevelopmentService = KcServiceLocator.getService(ProposalDevelopmentService.class);
        Collection<ProposalColumnsToAlter> proposalColumnsToAlterCollection = (KcServiceLocator.getService(BusinessObjectService.class).findMatching(ProposalColumnsToAlter.class, filterMap));
        
        List<String> mtcReturn = new ArrayList<String>();
        
        for( ProposalColumnsToAlter pcta : proposalColumnsToAlterCollection ) {
            if( pcta.getHasLookup() ) {
                Map<String, Object> primaryKeys = new HashMap<String, Object>();
                primaryKeys.put("columnName", pcta.getColumnName());
                Object fieldValue = proposalDevelopmentService.getProposalFieldValueFromDBColumnName(proposalNumber, pcta.getColumnName());
                String displayAttributeName = pcta.getLookupReturn();
                String displayLookupReturnValue = proposalDevelopmentService.getDataOverrideLookupDisplayReturnValue(pcta.getLookupClass());
                mtcReturn.add("methodToCall.performLookup.(!!"+pcta.getLookupClass()+"!!).((("+displayLookupReturnValue+":newProposalChangedData.changedValue,"+displayAttributeName+":newProposalChangedData.displayValue))).((``)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~)).anchorProposalDataOverride");
            }
        }
        return mtcReturn;
    }  

    private class S2sOppFormsComparator implements Comparator<S2sOppForms> {
        public int compare(S2sOppForms s2sOppForms1, S2sOppForms s2sOppForms2) {
            int result = s2sOppForms2.getMandatory().compareTo(s2sOppForms1.getMandatory());
            if (result != 0) {
                return result;
            } else {
                return s2sOppForms2.getAvailable().compareTo(s2sOppForms1.getAvailable());
            }
        }
    }
    
    public void sortS2sForms(DevelopmentProposal proposal) {
        if (proposal.getS2sOpportunity() != null && proposal.getS2sOpportunity().getS2sOppForms() != null) {
            Collections.sort(proposal.getS2sOpportunity().getS2sOppForms(), new S2sOppFormsComparator());
        }
    }

    @Override
    public List<Unit> getUnitsForCreateProposal(String userId) {
        final String namespaceCode = Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT;
        final String permissionName = PermissionConstants.CREATE_PROPOSAL;

        Set<Unit> units = new LinkedHashSet<Unit>();
        // Start by getting all of the Qualified Roles that the person is in.  For each
        // qualified role that has the UNIT_NUMBER qualification, check to see if the role
        // has the required permission.  If so, add that unit to the list.  Also, if the
        // qualified role has the SUBUNITS qualification set to YES, then also add all of the
        // subunits the to the list.
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(KcKimAttributes.UNIT_NUMBER, "*");
        Map<String,String> qualification =new HashMap<String,String>(qualifiedRoleAttributes);
        List<String> roleIds = systemAuthorizationService.getRoleIdsForPermission(permissionName, namespaceCode);

        List<Map<String,String>> qualifiers = roleService.getNestedRoleQualifiersForPrincipalByRoleIds(userId, roleIds, qualification);
        for (Map<String,String> qualifier : qualifiers) {
            Unit unit = unitService.getUnit(qualifier.get(KcKimAttributes.UNIT_NUMBER));
            if (unit != null) {
                units.add(unit);
                if (qualifier.containsKey(KcKimAttributes.SUBUNITS) && StringUtils.equalsIgnoreCase("Y", qualifier.get(KcKimAttributes.SUBUNITS))) {
                    addDescendantUnits(unit, units);
                }
            }
        }
        //the above line could potentially be a performance problem - need to revisit
        return new ArrayList<Unit>(units);
    }

    /**
     *
     * This method gets the Federal Agency for the given {@link ProposalDevelopmentDocument}
     *
     * @param developmentProposal Proposal Development Document.
     * @return {@link String} Federal Agency
     */
    @Override
    public String getCognizantFedAgency(DevelopmentProposal developmentProposal) {
        StringBuilder fedAgency = new StringBuilder();
        ProposalSite applicantOrganization = developmentProposal.getApplicantOrganization();
        if (applicantOrganization != null && applicantOrganization.getOrganization() != null
                && applicantOrganization.getOrganization().getCognizantAuditor() != null) {
            applicantOrganization.getOrganization().refreshReferenceObject("cognizantAuditorRolodex");
            Rolodex rolodex = applicantOrganization.getOrganization().getCognizantAuditorRolodex();
            fedAgency.append(rolodex.getOrganization());
            fedAgency.append(", ");
            fedAgency.append(StringUtils.trimToEmpty(rolodex.getFirstName()));
            fedAgency.append(" ");
            fedAgency.append(StringUtils.trimToEmpty(rolodex.getLastName()));
            fedAgency.append(" ");
            if (rolodex.getPhoneNumber() != null) {
                if (rolodex.getPhoneNumber().length() < 180) {
                    fedAgency.append(rolodex.getPhoneNumber());
                }
                else {
                    fedAgency.append(rolodex.getPhoneNumber().substring(0, 180));
                }
            }
        }
        if (fedAgency.toString().length() == 0) {
            fedAgency.append(VALUE_UNKNOWN);
        }
        return fedAgency.toString();
    }

    /**
     * This method returns the Federal ID for a given proposal
     *
     * @param proposalDevelopmentDocument Proposal Development Document.
     * @return Federal ID for a given proposal.
     */
    @Override
    public String getFederalId(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        String federalIdComesFromAwardStr = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class,
                FEDERAL_ID_COMES_FROM_CURRENT_AWARD);
        Boolean federalIdComesFromAward = federalIdComesFromAwardStr != null && federalIdComesFromAwardStr.equalsIgnoreCase("Y");
        DevelopmentProposal proposal = proposalDevelopmentDocument.getDevelopmentProposal();
        Award currentAward = null;
        String federalId = null;
        if (StringUtils.isNotBlank(proposal.getCurrentAwardNumber())) {
            currentAward = getProposalCurrentAwardVersion(proposalDevelopmentDocument);
        }
        InstitutionalProposal institutionalProposal = null;
        if (StringUtils.isNotBlank(proposal.getContinuedFrom())) {
            institutionalProposal = getProposalContinuedFromVersion(proposalDevelopmentDocument);
        }
        if (isProposalTypeRenewalRevisionContinuation(proposal.getProposalTypeCode())) {
            if (!StringUtils.isBlank(proposal.getSponsorProposalNumber())) {
                federalId = proposal.getSponsorProposalNumber();
            }
            else if (currentAward != null && !StringUtils.isBlank(currentAward.getSponsorAwardNumber()) && federalIdComesFromAward) {
                federalId = currentAward.getSponsorAwardNumber();
            }
            else {
                return null;
            }
        }
        else if (isProposalTypeNew(proposal.getProposalTypeCode())
                && (proposal.getS2sOpportunity() != null && isSubmissionTypeChangeCorrected(proposal.getS2sOpportunity()
                .getS2sSubmissionTypeCode()))) {
            if (!StringUtils.isBlank(proposal.getSponsorProposalNumber())) {
                federalId = proposal.getSponsorProposalNumber();
            }
            else if (institutionalProposal != null) {
                federalId = getGgTrackingIdFromProposal(institutionalProposal);
            }
        }
        else if (isProposalTypeResubmission(proposal.getProposalTypeCode())) {
            if (!StringUtils.isBlank(proposal.getSponsorProposalNumber())) {
                federalId = proposal.getSponsorProposalNumber();
            }
            else if (institutionalProposal != null && !StringUtils.isBlank(institutionalProposal.getSponsorProposalNumber())) {
                federalId = institutionalProposal.getSponsorProposalNumber();
            }
            if (isProposalTypeResubmission(proposal.getProposalTypeCode())) {
                if (proposal.getSponsorCode().equals(
                        this.parameterService.getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE,
                                ParameterConstants.ALL_COMPONENT, KeyConstants.NSF_SPONSOR_CODE))) {
                    return null;
                }
            }
        }
        if (federalId != null && sponsorService.isSponsorNihMultiplePi(proposal)) {
            return fromatFederalId(federalId);
        }
        return federalId;
    }

    /**
     *
     * This method is to format sponsor award number assume sponsor award number format is like this : 5-P01-ES05622-09, it should
     * be formatted to ES05622
     *
     * @param federalId
     * @return
     */
    protected String fromatFederalId(String federalId) {
        if (federalId.length() > 7) {
            int in = federalId.indexOf('-', 8);
            if (in != -1)
                federalId = federalId.substring(6, in);
        }
        return federalId;
    }

    protected boolean isSubmissionTypeChangeCorrected(String submissionTypeCode) {
        return StringUtils
                .equalsIgnoreCase(submissionTypeCode, this.parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.S2S_SUBMISSIONTYPE_CHANGEDCORRECTED));
    }

    protected boolean isProposalTypeRenewalRevisionContinuation(String proposalTypeCode) {
        String proposalTypeCodeRenewal = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class,
                KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_RENEWAL);
        String proposalTypeCodeRevision = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class,
                KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION);
        String proposalTypeCodeContinuation = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class,
                KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_CONTINUATION);

        return !StringUtils.isEmpty(proposalTypeCode)
                && (proposalTypeCode.equals(proposalTypeCodeRenewal) || proposalTypeCode.equals(proposalTypeCodeRevision) || proposalTypeCode
                .equals(proposalTypeCodeContinuation));
    }

    /**
     * Is the Proposal Type set to Resubmission?
     *
     * @param proposalTypeCode proposal type code
     * @return true or false
     */
    protected boolean isProposalTypeResubmission(String proposalTypeCode) {
        String proposalTypeCodeResubmission = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class,
                KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_RESUBMISSION);

        return !StringUtils.isEmpty(proposalTypeCode) && (proposalTypeCode.equals(proposalTypeCodeResubmission));
    }

    /**
     * Is the Proposal Type set to New?
     *
     * @param proposalTypeCode proposal type code
     * @return true or false
     */
    protected boolean isProposalTypeNew(String proposalTypeCode) {
        String proposalTypeCodeNew = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class,
                KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_NEW);

        return !StringUtils.isEmpty(proposalTypeCode) && (proposalTypeCode.equals(proposalTypeCodeNew));
    }

    @Override
    public String getGgTrackingIdFromProposal(InstitutionalProposal proposal) {
        DevelopmentProposal newestDevProp = getNewestDevPropFromInstProp(proposal);
        if (newestDevProp != null && newestDevProp.getS2sOpportunity() != null) {
            S2sAppSubmission appSubmission = null;
            int submissionNo = 0;
            for (S2sAppSubmission s2AppSubmission : newestDevProp.getS2sAppSubmission()) {
                if (s2AppSubmission.getSubmissionNumber() > submissionNo
                        && StringUtils.isNotBlank(s2AppSubmission.getGgTrackingId())) {
                    appSubmission = s2AppSubmission;
                    submissionNo = s2AppSubmission.getSubmissionNumber();
                }
            }
            if (appSubmission != null) {
                return appSubmission.getGgTrackingId();
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    protected DevelopmentProposal getNewestDevPropFromInstProp(InstitutionalProposal instProp) {
        Integer listDetailSize = 0;
        ProposalAdminDetails curDetail = new ProposalAdminDetails();
        Map<String, Object> detailFieldValues = new HashMap<String, Object>();
        detailFieldValues.put("instProposalId", instProp.getProposalId());
        List<ProposalAdminDetails> details = (List<ProposalAdminDetails>) businessObjectService.findMatchingOrderBy(ProposalAdminDetails.class,
                detailFieldValues, "devProposalNumber", true);
        listDetailSize = details.size();
        if (listDetailSize > 1) {
            curDetail = details.get(listDetailSize - 2);
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("proposalNumber", curDetail.getDevelopmentProposal().getProposalNumber());
            List<S2sAppSubmission> s2sSubmissionDetails = (List<S2sAppSubmission>) businessObjectService.findMatchingOrderBy(S2sAppSubmission.class,
                    fieldValues, "proposalNumber", true);
            curDetail.getDevelopmentProposal().setS2sAppSubmission(s2sSubmissionDetails);
            return curDetail.getDevelopmentProposal();
        }
        if (listDetailSize == 1) {
            curDetail = details.get(0);
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("proposalNumber", curDetail.getDevelopmentProposal().getProposalNumber());
            List<S2sAppSubmission> s2sSubmissionDetails = (List<S2sAppSubmission>) businessObjectService.findMatchingOrderBy(S2sAppSubmission.class,
                    fieldValues, "proposalNumber", true);
            curDetail.getDevelopmentProposal().setS2sAppSubmission(s2sSubmissionDetails);
            return curDetail.getDevelopmentProposal();

        }
        return null;
    }

    protected void addDescendantUnits(Unit parentUnit, Set<Unit> units) {
        List<Unit> subunits = unitService.getSubUnits(parentUnit.getUnitNumber());
        if (CollectionUtils.isNotEmpty(subunits)) {
            units.addAll(subunits);
            for (Unit subunit : subunits) {
                addDescendantUnits(subunit, units);
            }
        }
    }
    
    protected RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    protected S2SService getS2sService() {
        return s2sService;
    }

    public void setS2sService(S2SService s2sService) {
        this.s2sService = s2sService;
    }
    public SponsorService getSponsorService() {
        return sponsorService;
    }
    public void setSponsorService(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }
    public KeyPersonnelService getKeyPersonnelService() {
        return keyPersonnelService;
    }
    public void setKeyPersonnelService(KeyPersonnelService keyPersonnelService) {
        this.keyPersonnelService = keyPersonnelService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public SystemAuthorizationService getSystemAuthorizationService() {
        return systemAuthorizationService;
    }

    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }

    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

    public VersionHistoryService getVersionHistoryService() {
        return versionHistoryService;
    }

    public UnitAuthorizationService getUnitAuthService() {
        return unitAuthService;
    }

    public void setUnitAuthService(UnitAuthorizationService unitAuthService) {
        this.unitAuthService = unitAuthService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }
}
