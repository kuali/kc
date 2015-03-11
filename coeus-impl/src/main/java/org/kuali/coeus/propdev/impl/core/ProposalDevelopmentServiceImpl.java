/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.rate.BudgetLaRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.propdev.impl.budget.CostShareInfoDO;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetStatus;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.CoPiInfoDO;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.coeus.propdev.impl.budget.editable.BudgetColumnsToAlter;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.datetime.DateTimeService;
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
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.kuali.rice.core.api.criteria.PredicateFactory.*;
import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;

@Component("proposalDevelopmentService")
public class ProposalDevelopmentServiceImpl implements ProposalDevelopmentService {

    protected final Log LOG = LogFactory.getLog(ProposalDevelopmentServiceImpl.class);

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
    @Autowired
    @Qualifier("unitAuthorizationService")
    private UnitAuthorizationService unitAuthorizationService;
    @Autowired
    @Qualifier("kcPersistenceStructureService")
    private KcPersistenceStructureService kcPersistenceStructureService;
    @Autowired
    @Qualifier("proposalBudgetService")
    private BudgetService budgetService;
    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;
    @Autowired
    @Qualifier("sponsorHierarchyService")
    private SponsorHierarchyService sponsorHierarchyService;
    @Autowired
    @Qualifier("keyPersonnelService")
    private KeyPersonnelService keyPersonnelService;
    @Autowired
    @Qualifier("unitService")
    private UnitService unitService;
    @Autowired
    @Qualifier("systemAuthorizationService")
    private SystemAuthorizationService systemAuthorizationService;

    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kcAuthorizationService;

    @Autowired
    @Qualifier("dateTimeService")
    private DateTimeService dateTimeService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    public void setKcAuthorizationService (KcAuthorizationService kcAuthorizationService){
        this.kcAuthorizationService = kcAuthorizationService;
    }
    protected KcAuthorizationService getKcAuthorizationService (){return kcAuthorizationService;}

    public void setDateTimeService (DateTimeService dateTimeService){
        this.dateTimeService = dateTimeService;
    }
    protected DateTimeService getDateTimeService (){return dateTimeService;}

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
            applicantOrganization.setDevelopmentProposal(developmentProposal);
            developmentProposal.setApplicantOrganization(applicantOrganization);
        }

        // On first save, set Performing Organization if not selected
        ProposalSite performingOrganization = developmentProposal.getPerformingOrganization();
        if (StringUtils.isEmpty(developmentProposal.getProposalNumber()) && performingOrganization.getOrganization() == null
                && developmentProposal.getOwnedByUnitNumber() != null) {
            String performingOrganizationId = developmentProposal.getOwnedByUnit().getOrganizationId();
            performingOrganization = createProposalSite(performingOrganizationId, getNextSiteNumber(proposalDevelopmentDocument));
            performingOrganization.setDevelopmentProposal(developmentProposal);
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
        for (ProposalSite proposalSite : proposalDevelopmentDocument.getDevelopmentProposal().getProposalSites()){
            if (proposalSite.getSiteNumber() == null) {
                proposalSite.setSiteNumber(getNextSiteNumber(proposalDevelopmentDocument));
            }
        	if (proposalSite.getDevelopmentProposal() == null) {
        		proposalSite.setDevelopmentProposal(proposalDevelopmentDocument.getDevelopmentProposal());
        	}
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
        this.unitAuthorizationService = unitAuthService;
    }

    public String populateBudgetEditableFieldMetaDataForAjaxCall(String proposalNumber, String documentNumber, String editableFieldDBColumn) {
        if (isAuthorizedToAccess(proposalNumber) && StringUtils.isNotBlank(documentNumber) && StringUtils.isNotBlank(editableFieldDBColumn)) {
            return populateBudgetEditableFieldMetaData(proposalNumber, editableFieldDBColumn);
        }
        return StringUtils.EMPTY;
        
    }
    
    protected ProposalDevelopmentBudgetExt getBudgetVersionOverview(String proposalNumber) {
        DevelopmentProposal proposal = getDataObjectService().findUnique(DevelopmentProposal.class,
                QueryByCriteria.Builder.forAttribute("PROPOSAL_NUMBER", proposalNumber).build());
        if (proposal != null) {
        	return proposal.getFinalBudget();
        }
        return null;
    }

    public String getDataOverrideLookupDisplayReturnValue(String lookupClassName) {
        List<String> lookupClassPkFields = null;
        String returnValue = "";
        Class lookupClass = null;

        if (StringUtils.isNotEmpty(lookupClassName)) {
            try {
                lookupClass = Class.forName(lookupClassName);
                lookupClassPkFields = (List<String>) getKcPersistenceStructureService().getPrimaryKeys(lookupClass);
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
       // Map<String, Object> primaryKeys = new HashMap<String, Object>();
        List<String> lookupClassPkFields = null;
        Class lookupClass = null;
        String displayValue = "";
        PersistableBusinessObject businessObject = null;

        if (StringUtils.isNotEmpty(lookupClassName)) {
            try {
                lookupClass = Class.forName(lookupClassName);
                lookupClassPkFields = (List<String>) getKcPersistenceStructureService().getPrimaryKeys(lookupClass);
            }
            catch (ClassNotFoundException e) {
            }

            if (CollectionUtils.isNotEmpty(lookupClassPkFields)) {

                if (StringUtils.isNotEmpty(value)) {
                   // primaryKeys.put(lookupClassPkFields.get(0), value);
                    businessObject = (PersistableBusinessObject) getDataObjectService().find (lookupClass, value);
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
    
    public Object getBudgetFieldValueFromDBColumnName(String proposalNumber, String dbColumnName) {
        Object fieldValue = null;        
        Map<String, String> fieldMap = getKcPersistenceStructureService().getDBColumnToObjectAttributeMap(Budget.class);
        String budgetAttributeName = fieldMap.get(dbColumnName);
        if (StringUtils.isNotEmpty(budgetAttributeName)) {
            Budget currentBudget = getBudgetVersionOverview(proposalNumber);            
            if (currentBudget != null) {
                fieldValue = ObjectUtils.getPropertyValue(currentBudget, budgetAttributeName);
            }
        }            
        return fieldValue;    
             
    }

    @SuppressWarnings("unchecked")

    public KcPersistenceStructureService getKcPersistenceStructureService() {
        return kcPersistenceStructureService;
    }

    public void setKcPersistenceStructureService(KcPersistenceStructureService kcPersistenceStructureService) {
        this.kcPersistenceStructureService = kcPersistenceStructureService;
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

    public boolean isGrantsGovEnabledForProposal(DevelopmentProposal devProposal) {
        String federalSponsorTypeCode = getParameterService().getParameterValueAsString(AwardDocument.class, Constants.FEDERAL_SPONSOR_TYPE_CODE);
        return !devProposal.isChild() && devProposal.getSponsor() != null
                && StringUtils.equals(devProposal.getSponsor().getSponsorTypeCode(), federalSponsorTypeCode);
    }

    public boolean isGrantsGovEnabledOnSponsorChange(String proposalNumber, String sponsorCode) {
        String federalSponsorTypeCode = getParameterService().getParameterValueAsString(AwardDocument.class, Constants.FEDERAL_SPONSOR_TYPE_CODE);
        DevelopmentProposal proposal =  getDataObjectService().find(DevelopmentProposal.class, proposalNumber);
        Sponsor sponsor = (Sponsor) getDataObjectService().find(Sponsor.class, sponsorCode);
        boolean enableGrantsGov = proposal == null || !proposal.isChild();
        enableGrantsGov &= sponsor != null && StringUtils.equals(sponsor.getSponsorTypeCode(), federalSponsorTypeCode);
        return enableGrantsGov;
    }

    @Override
    public ProposalDevelopmentDocument deleteProposal(ProposalDevelopmentDocument proposalDocument) throws WorkflowException {

        final DevelopmentProposal developmentProposal = proposalDocument.getDevelopmentProposal();
        final String proposalNumber = developmentProposal.getProposalNumber();

        cleanupBudgetObjects(developmentProposal);
        getDataObjectService().deleteMatching(ProposalDevelopmentBudgetExt.class, QueryByCriteria.Builder.andAttributes(Collections.singletonMap("developmentProposal.proposalNumber", proposalNumber)).build());
        developmentProposal.setBudgets(new ArrayList<ProposalDevelopmentBudgetExt>());
        developmentProposal.setFinalBudget(null);

        getDataObjectService().deleteMatching(ProposalBudgetStatus.class, QueryByCriteria.Builder.andAttributes(Collections.singletonMap("proposalNumber", proposalNumber)).build());
        proposalDocument.setDevelopmentProposal(null);
        proposalDocument.setProposalDeleted(true);

        proposalDocument = (ProposalDevelopmentDocument)getDocumentService().saveDocument(proposalDocument);
        return (ProposalDevelopmentDocument) getDocumentService().cancelDocument(proposalDocument, "Delete Proposal");
    }

    /**
     * BudgetRate, BudgetLaRate, BudgetPeriods will not cascade delete for some reason.  Manually cleaning them up here to avoid
     * a constraint violation normally JPA's orphanRemoval should automatically take care of these deletes
     */
    protected void cleanupBudgetObjects(DevelopmentProposal developmentProposal) {
        final Collection<Long> budgetIds = CollectionUtils.collect(developmentProposal.getBudgets(), new Transformer<ProposalDevelopmentBudgetExt, Long>() {
            @Override
            public Long transform(ProposalDevelopmentBudgetExt input) {
                return input.getBudgetId();
            }
        });
        //this should be in the budgets list but including it just to be safe
        if (developmentProposal.getFinalBudget() != null) {
            budgetIds.add(developmentProposal.getFinalBudget().getBudgetId());
        }

        if (!budgetIds.isEmpty()) {
            getDataObjectService().deleteMatching(BudgetRate.class, QueryByCriteria.Builder.fromPredicates(in("budgetId", budgetIds)));
            getDataObjectService().deleteMatching(BudgetLaRate.class, QueryByCriteria.Builder.fromPredicates(in("budgetId", budgetIds)));
            getDataObjectService().deleteMatching(BudgetPeriod.class, QueryByCriteria.Builder.fromPredicates(in("budget.budgetId", budgetIds)));
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
            if (globalVariableService.getUserSession() != null) {
                String[] invalues = StringUtils.split(proposalNumber, Constants.COLON);
                String docFormKey = invalues[1];
                if (StringUtils.isBlank(docFormKey)) {
                    isAuthorized = false;
                }
                else {
                    Object formObj = globalVariableService.getUserSession().retrieveObject(docFormKey);
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
                LOG.info("dwr/ajax does not have session ");
            }
        }
        return isAuthorized;
    }

    public Budget getFinalBudget(DevelopmentProposal proposal) {
    	return proposal.getFinalBudget();
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
        Collection<ProposalAdminDetails> proposalAdminDetails = getBusinessObjectService().findMatching(ProposalAdminDetails.class,values);

        for (Iterator iter = proposalAdminDetails.iterator(); iter.hasNext();) {
            ProposalAdminDetails pad = (ProposalAdminDetails) iter.next();
            return getBusinessObjectService().findBySinglePrimaryKey(InstitutionalProposal.class, pad.getInstProposalId());
        }
        return null;
    }
    protected String populateBudgetEditableFieldMetaData(
            String proposalNumber, String editableFieldDBColumn) {
        String returnValue  = "";

        if (globalVariableService.getMessageMap() != null) {
            globalVariableService.getMessageMap().clearErrorMessages();
        }      
        Object fieldValue = getBudgetFieldValueFromDBColumnName(proposalNumber, editableFieldDBColumn);
        
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("columnName", editableFieldDBColumn);
        BudgetColumnsToAlter editableColumn = getDataObjectService().find(BudgetColumnsToAlter.class, primaryKeys);
        if (editableColumn.getHasLookup()) {
            returnValue = getDataOverrideLookupDisplayReturnValue(editableColumn.getLookupClass())
                    + ","
                    + editableColumn.getLookupReturn()
                    + ","
                    + getDataOverrideLookupDisplayDisplayValue(editableColumn.getLookupClass(),
                            (fieldValue != null ? fieldValue.toString() : ""), editableColumn.getLookupReturn());
        }
        else if (fieldValue != null && editableColumn.getDataType().equalsIgnoreCase("DATE")) {
            returnValue = ",," + getDateTimeService().toString((Date) fieldValue, "MM/dd/yyyy");
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
        String principalId = globalVariableService.getUserSession().getPerson().getPrincipalId();
        Role roleInfo  = getRoleService().getRoleByNamespaceCodeAndName(RoleConstants.OSP_ROLE_TYPE, RoleConstants.OSP_ADMINISTRATOR);
        List<String> roleIds = new ArrayList<String>();
        roleIds.add(roleInfo.getId());
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(KcKimAttributes.UNIT_NUMBER, "*");
        Map<String,String> qualifications = new HashMap<String,String>(qualifiedRoleAttributes);
        if(getRoleService().principalHasRole(principalId, roleIds, qualifications)){
            return true;
        }
        KcAuthorizationService proposalAuthService = getKcAuthorizationService();
        List<String> users = proposalAuthService.getPrincipalsInRole(RoleConstants.AGGREGATOR, document);

        for (String user : users) {
            if(globalVariableService.getUserSession().getPrincipalId().equals(user)){
                return true;
            }
        }
        
        return false;
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
        result.put(PROPOSAL_SUMMARY_DISCLAIMER_INDICATOR, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, PROPOSAL_SUMMARY_DISCLAIMER_INDICATOR));
        result.put(SUMMARY_SPECIAL_REVIEW_LIST, getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, SUMMARY_SPECIAL_REVIEW_LIST));
        result.put(PROPOSAL_NARRATIVE_TYPE_GROUP2,getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, PROPOSAL_NARRATIVE_TYPE_GROUP2));
        return result;
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
                DevelopmentProposal developmentProposal = pdDocument.getDevelopmentProposal();
				if (region != ProposalLockingRegion.BUDGET) {
					developmentProposal.setBudgets(updatedDocCopy.getDevelopmentProposal().getBudgets());
                } else {
                    //in case other parts of the document have been saved since we have saved,
                    //we save off possibly changed parts and reload the rest of the document
                    List<ProposalDevelopmentBudgetExt> newVersions = developmentProposal.getBudgets();
                    
                    pdDocument = updatedDocCopy;
                    loadDocument(pdDocument);
                    
                    developmentProposal.setBudgets(newVersions);
                }
                if (region != ProposalLockingRegion.ATTACHMENTS) {
                    developmentProposal.setNarratives(updatedDocCopy.getDevelopmentProposal().getNarratives());
                    developmentProposal.setInstituteAttachments(updatedDocCopy.getDevelopmentProposal().getInstituteAttachments());
                    developmentProposal.setProposalAbstracts(updatedDocCopy.getDevelopmentProposal().getProposalAbstracts());
                    developmentProposal.setPropPersonBios(updatedDocCopy.getDevelopmentProposal().getPropPersonBios());
                    removePersonnelAttachmentForDeletedPerson(pdDocument);
               } else {
                   //in case other parts of the document have been saved since we have saved,
                   //we save off possibly changed parts and reload the rest of the document
                   List<Narrative> newNarratives = developmentProposal.getNarratives();
                   List<Narrative> instituteAttachments = developmentProposal.getInstituteAttachments();
                   List<ProposalAbstract> newAbstracts = developmentProposal.getProposalAbstracts();
                   List<ProposalPersonBiography> newBiographies = developmentProposal.getPropPersonBios();
    
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
                   developmentProposal.setNarratives(newNarrativesCopy);
                   developmentProposal.setInstituteAttachments(instituteAttachments);
                   developmentProposal.setProposalAbstracts(newAbstracts);
                   developmentProposal.setPropPersonBios(newBiographies);
    
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
        DocumentService docService = getDocumentService();
        newCopy = (ProposalDevelopmentDocument)docService.getByDocumentHeaderId(pdDocumentNumber);        
        return newCopy;
    }
    
    @Override
    public void loadDocument(ProposalDevelopmentDocument document) {
        getKeyPersonnelService().populateDocument(document);
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
        final Set<String> roleIds = new HashSet<>(systemAuthorizationService.getRoleIdsForPermission(permissionName, namespaceCode));

        Set<Map<String,String>> qualifiers = new HashSet<>(roleService.getNestedRoleQualifiersForPrincipalByRoleIds(userId, new ArrayList<>(roleIds), qualification));

        for (Map<String,String> qualifier : qualifiers) {
            Unit unit = getUnitService().getUnit(qualifier.get(KcKimAttributes.UNIT_NUMBER));
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
    
    public boolean autogenerateInstitutionalProposal() {
    	return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, 
                ParameterConstants.DOCUMENT_COMPONENT, KeyConstants.AUTOGENERATE_INSTITUTIONAL_PROPOSAL_PARAM);
    }
    
    protected void addDescendantUnits(Unit parentUnit, Set<Unit> units) {
        List<Unit> subunits = getUnitService().getSubUnits(parentUnit.getUnitNumber());
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


    public UnitAuthorizationService getUnitAuthService() {
        return unitAuthorizationService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public SponsorHierarchyService getSponsorHierarchyService() {
        return sponsorHierarchyService;
    }

    public void setSponsorHierarchyService(SponsorHierarchyService sponsorHierarchyService) {
        this.sponsorHierarchyService = sponsorHierarchyService;
    }

    public UnitAuthorizationService getUnitAuthorizationService() {
        return unitAuthorizationService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
