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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

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
    @Qualifier("kcPersistenceStructureService")
    private KcPersistenceStructureService kcPersistenceStructureService;

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
    @Qualifier("unitService")
    private UnitService unitService;

    @Autowired
    @Qualifier("systemAuthorizationService")
    private SystemAuthorizationService systemAuthorizationService;

    @Autowired
    @Qualifier("proposalTypeService")
    private ProposalTypeService proposalTypeService;

    /**
     * This method gets called from the "save" action. It initializes the applicant org. on the first save; it also sets the
     * performing org. if the user didn't make a selection.
     */
    public void initializeUnitOrganizationLocation(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        ProposalSite applicantOrganization = proposalDevelopmentDocument.getDevelopmentProposal().getApplicantOrganization();
        DevelopmentProposal developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();

        // Unit number chosen, set Applicant Organization
        if (developmentProposal.getOwnedByUnitNumber() != null &&
                applicantOrganization == null || applicantOrganization.getOrganization() == null) {
            // get Lead Unit details
            developmentProposal.refreshReferenceObject("ownedByUnit");
            String applicantOrganizationId = developmentProposal.getOwnedByUnit().getOrganizationId();

            // get Organzation assoc. w/ Lead Unit, set applicant org
            applicantOrganization = createProposalSite(applicantOrganizationId);
            applicantOrganization.setDevelopmentProposal(developmentProposal);
            developmentProposal.setApplicantOrganization(applicantOrganization);
        }

        // On first save, set Performing Organization if not selected
        ProposalSite performingOrganization = developmentProposal.getPerformingOrganization();
        if (StringUtils.isEmpty(developmentProposal.getProposalNumber()) && performingOrganization.getOrganization() == null
                && developmentProposal.getOwnedByUnitNumber() != null) {
            String performingOrganizationId = developmentProposal.getOwnedByUnit().getOrganizationId();
            performingOrganization = createProposalSite(performingOrganizationId);
            performingOrganization.setDevelopmentProposal(developmentProposal);
            developmentProposal.setPerformingOrganization(performingOrganization);
        }
    }

    /**
     * Constructs a ProposalSite; initializes the organization, and locationName fields, and sets the default district if there is
     * one defined for the Organization.
     *
     */
    protected ProposalSite createProposalSite(String organizationId) {
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

    protected ProposalDevelopmentBudgetExt getBudgetVersionOverview(String proposalNumber) {
        DevelopmentProposal proposal = getDataObjectService().findUnique(DevelopmentProposal.class,
                QueryByCriteria.Builder.forAttribute("PROPOSAL_NUMBER", proposalNumber).build());
        if (proposal != null) {
        	return proposal.getFinalBudget();
        }
        return null;
    }

    protected String getPropertyValue(BusinessObject businessObject, String fieldName) {
        String displayValue = "";
        try {
            displayValue = (String) ObjectUtils.getPropertyValue(businessObject, fieldName);
        }
        // Might happen due to Unknown Property Exception
        catch (RuntimeException e) {
            LOG.warn(e.getMessage(), e);
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

    public boolean isGrantsGovEnabledForProposal(DevelopmentProposal devProposal) {
        String federalSponsorTypeCode = getParameterService().getParameterValueAsString(AwardDocument.class, Constants.FEDERAL_SPONSOR_TYPE_CODE);
        return !devProposal.isChild() && devProposal.getSponsor() != null
                && StringUtils.equals(devProposal.getSponsor().getSponsorTypeCode(), federalSponsorTypeCode);
    }

    @Override
    public ProposalDevelopmentDocument deleteProposal(ProposalDevelopmentDocument proposalDocument) throws WorkflowException {

        dataObjectService.delete(proposalDocument.getDevelopmentProposal());
        proposalDocument.setDevelopmentProposal(null);
        proposalDocument.setProposalDeleted(true);

        proposalDocument = (ProposalDevelopmentDocument)getDocumentService().saveDocument(proposalDocument);
        return (ProposalDevelopmentDocument) getDocumentService().cancelDocument(proposalDocument, "Delete Proposal");
    }

    protected DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public Budget getFinalBudget(DevelopmentProposal proposal) {
    	return proposal.getFinalBudget();
    }


    /**
     * Return the institutional proposal linked to the development proposal.
     */
    public InstitutionalProposal getInstitutionalProposal(String devProposalNumber) {
        Map<String, Object> values = new HashMap<>();
        values.put("devProposalNumber", devProposalNumber);
        Collection<ProposalAdminDetails> proposalAdminDetails = getBusinessObjectService().findMatching(ProposalAdminDetails.class,values);

        for (ProposalAdminDetails pad : proposalAdminDetails) {
        	if (pad.getInstProposalId() != null) {
        		return getBusinessObjectService().findBySinglePrimaryKey(InstitutionalProposal.class, pad.getInstProposalId());
        	}
        }
        return null;
    }

    @Override
    public List<Unit> getUnitsForCreateProposal(String userId) {
        final String namespaceCode = Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT;
        final String permissionName = PermissionConstants.CREATE_PROPOSAL;

        Set<Unit> units = new LinkedHashSet<>();
        // Start by getting all of the Qualified Roles that the person is in.  For each
        // qualified role that has the UNIT_NUMBER qualification, check to see if the role
        // has the required permission.  If so, add that unit to the list.  Also, if the
        // qualified role has the SUBUNITS qualification set to YES, then also add all of the
        // subunits the to the list.
        Map<String, String> qualifiedRoleAttributes = new HashMap<>();
        qualifiedRoleAttributes.put(KcKimAttributes.UNIT_NUMBER, "*");
        Map<String,String> qualification =new HashMap<>(qualifiedRoleAttributes);
        final Set<String> roleIds = new HashSet<>(systemAuthorizationService.getRoleIdsForPermission(permissionName, namespaceCode));

        Set<Map<String,String>> qualifiers = new HashSet<>(roleService.getNestedRoleQualifiersForPrincipalByRoleIds(userId, new ArrayList<>(roleIds), qualification));

        for (Map<String,String> qualifier : qualifiers) {
            Unit unit = getUnitService().getUnit(qualifier.get(KcKimAttributes.UNIT_NUMBER));
            if (unit != null && unit.isActive()) {
                units.add(unit);
                if (qualifier.containsKey(KcKimAttributes.SUBUNITS) && (StringUtils.equalsIgnoreCase("Y", qualifier.get(KcKimAttributes.SUBUNITS)) || StringUtils.equalsIgnoreCase("Yes", qualifier.get(KcKimAttributes.SUBUNITS)))) {
                    addDescendantUnits(unit, units);
                }
            }
        }
        //the above line could potentially be a performance problem - need to revisit
        return new ArrayList<>(units);
    }
    
    public boolean autogenerateInstitutionalProposal() {
    	return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, 
                ParameterConstants.DOCUMENT_COMPONENT, KeyConstants.AUTOGENERATE_INSTITUTIONAL_PROPOSAL_PARAM);
    }

    @Override
    public String getIPGenerateOption(DevelopmentProposal developmentProposal) {
        if( isProposalTypeChangeCorrected(developmentProposal.getProposalTypeCode())) {
            return ProposalDevelopmentConstants.ResubmissionOptions.GENERATE_NEW_VERSION_OF_ORIGINAL_IP;
        }else{
            return ProposalDevelopmentConstants.ResubmissionOptions.GENERATE_NEW_IP;
        }
    }

    @Override
    public boolean isProposalReniewedOrChangeCorrected(DevelopmentProposal developmentProposal) {
       return (getProposalTypeService().getContinuationProposalTypeCode().equals(developmentProposal.getProposalTypeCode())
           || getProposalTypeService().getRenewProposalTypeCode().equals(developmentProposal.getProposalTypeCode())
           || getProposalTypeService().getResubmissionProposalTypeCode().equals(developmentProposal.getProposalTypeCode())
           || getProposalTypeService().getRevisionProposalTypeCode().equals(developmentProposal.getProposalTypeCode())
           || isProposalTypeChangeCorrected(developmentProposal.getProposalTypeCode())
           || isSubmissionChangeCorrected(developmentProposal));
    }

    private boolean isSubmissionChangeCorrected(DevelopmentProposal developmentProposal) {
        return developmentProposal.getS2sOpportunity() != null && getProposalTypeService().getS2SSubmissionChangeCorrectedCode().equals(developmentProposal.getS2sOpportunity().getS2sSubmissionTypeCode());
    }

    private boolean isProposalTypeChangeCorrected(String proposalTypeCode) {
        return (getProposalTypeService().getNewChangedOrCorrectedProposalTypeCode().equals(proposalTypeCode)
            || getProposalTypeService().getRenewalChangedOrCorrectedProposalTypeCode().equals(proposalTypeCode)
            || getProposalTypeService().getResubmissionChangedOrCorrectedProposalTypeCode().equals(proposalTypeCode)
            || getProposalTypeService().getSupplementChangedOrCorrectedProposalTypeCode().equals(proposalTypeCode)
            || getProposalTypeService().getBudgetSowUpdateProposalTypeCode().equals(proposalTypeCode));
    }

    protected void addDescendantUnits(Unit parentUnit, Set<Unit> units) {
        List<Unit> subunits = getUnitService().getActiveSubUnits(parentUnit.getUnitNumber());
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

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    protected ParameterService getParameterService() {
        return parameterService;
    }

    public void setBusinessObjectService(BusinessObjectService bos) {
        businessObjectService = bos;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public KcPersistenceStructureService getKcPersistenceStructureService() {
        return kcPersistenceStructureService;
    }

    public void setKcPersistenceStructureService(KcPersistenceStructureService kcPersistenceStructureService) {
        this.kcPersistenceStructureService = kcPersistenceStructureService;
    }

    public ProposalTypeService getProposalTypeService() {
        return proposalTypeService;
    }

    public void setProposalTypeService(ProposalTypeService proposalTypeService) {
        this.proposalTypeService = proposalTypeService;
    }
}
