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
package org.kuali.kra.proposaldevelopment.service.impl;

import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.rule.event.DocumentAuditEvent;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.service.KualiRuleService;
import org.kuali.core.util.TypedArrayList;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.bo.ExemptionType;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UserRole;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RightConstants;
import org.kuali.kra.lookup.keyvalue.ExtendedPersistableBusinessObjectValuesFinder;
import org.kuali.kra.lookup.keyvalue.KeyLabelPairComparator;
import org.kuali.kra.proposaldevelopment.bo.ProposalExemptNumber;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.service.UserRoleService;

// TODO : extends PersistenceServiceStructureImplBase is a hack to temporarily resolve get class descriptor.
public class ProposalDevelopmentServiceImpl implements ProposalDevelopmentService {
    private BusinessObjectService businessObjectService;
    private UserRoleService userRoleService;

    /**
     * This method...
     * 
     * @param proposalDevelopmentDocument
     * @param proposalOrganization
     */
    public void initializeUnitOrganzationLocation(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        Organization proposalOrganization = proposalDevelopmentDocument.getOrganization();

        // Unit number chosen, set Organzation, etc...
        if (proposalDevelopmentDocument.getOwnedByUnitNumber() != null && proposalOrganization == null) {
            // get Lead Unit details
            proposalDevelopmentDocument.refreshReferenceObject("ownedByUnit");
            String organizationId = proposalDevelopmentDocument.getOwnedByUnit().getOrganizationId();

            // get Organzation assoc. w/ Lead Unit
            proposalDevelopmentDocument.setOrganizationId(organizationId);
            proposalDevelopmentDocument.refreshReferenceObject("organization");
            proposalOrganization = proposalDevelopmentDocument.getOrganization();
            proposalDevelopmentDocument.setPerformingOrganizationId(organizationId);
            proposalDevelopmentDocument.refreshReferenceObject("performingOrganization");

            // initialize Proposal Locations with Organization details
            if (proposalDevelopmentDocument.getProposalLocations().isEmpty()) {
                ProposalLocation newProposalLocation = new ProposalLocation();
                newProposalLocation.setLocation(proposalOrganization.getOrganizationName());
                newProposalLocation.setRolodexId(proposalOrganization.getContactAddressId());
                newProposalLocation.refreshReferenceObject("rolodex");
                newProposalLocation.setLocationSequenceNumber(proposalDevelopmentDocument
                        .getDocumentNextValue(Constants.PROPOSAL_LOCATION_SEQUENCE_NUMBER));
                proposalDevelopmentDocument.getProposalLocations().add(0, newProposalLocation);
            }

        }
    }

    public List<Unit> getDefaultModifyProposalUnitsForUser(String userName) {
        Map queryMap = new HashMap();
        queryMap.put("userName", userName);


        List<Person> persons = (List<Person>) getBusinessObjectService().findMatching(Person.class, queryMap);

        if (persons.size() > 1) {
            throw new RuntimeException("More than one person retieved for userName: " + userName);
        }

        Person person = persons.get(0);

        List<Unit> units = new ArrayList<Unit>();

        List<UserRole> userRoles = getUserRoleService().getUserRoles(person.getPersonId(), RightConstants.MODIFY_PROPOSAL);
        for (UserRole userRole : userRoles) {
            Unit unit = userRole.getUnit();
            if (!units.contains(unit))
                units.add(unit);
        }

        return units;
    }

    /**
     * Gets units for the given names. Useful when you know what you want.
     * 
     * @param unitNumbers varargs representation of unitNumber array
     * @return Collection<Unit>
     */
    private Collection<Unit> getUnitsWithNumbers(String... unitNumbers) {
        Collection<Unit> retval = new ArrayList<Unit>();

        for (String unitNumber : unitNumbers) {
            Map query_map = new HashMap();
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
     * Gets the userRoleService attribute.
     * 
     * @return Returns the userRoleService.
     */
    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    /**
     * Sets the userRoleService attribute value.
     * 
     * @param userRoleService The userRoleService to set.
     */
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService#validateBudgetAuditRule(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public boolean validateBudgetAuditRule(ProposalDevelopmentDocument proposalDevelopmentDocument) throws Exception {
        boolean valid = true;
        for (BudgetVersionOverview budgetVersion : proposalDevelopmentDocument.getBudgetVersionOverviews()) {
            if (budgetVersion.isFinalVersionFlag()) {
                valid &= applyAuditRuleForBudgetDocument(budgetVersion);
            }
        }

        return valid;
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService#validateBudgetAuditRuleBeforeSaveBudgetVersion(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public boolean validateBudgetAuditRuleBeforeSaveBudgetVersion(ProposalDevelopmentDocument proposalDevelopmentDocument)
            throws Exception {
        boolean valid = true;
        for (BudgetVersionOverview budgetVersion : proposalDevelopmentDocument.getBudgetVersionOverviews()) {
            String budgetStatusCompleteCode = KraServiceLocator.getService(KualiConfigurationService.class).getParameter(
                    Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT,
                    Constants.BUDGET_STATUS_COMPLETE_CODE).getParameterValue();
            // if status is complete and version is not final, then business rule will take care of it
            if (budgetVersion.isFinalVersionFlag() && budgetVersion.getBudgetStatus() != null
                    && budgetVersion.getBudgetStatus().equals(budgetStatusCompleteCode)) {
                valid &= applyAuditRuleForBudgetDocument(budgetVersion);
            }
        }

        return valid;
    }

    private boolean applyAuditRuleForBudgetDocument(BudgetVersionOverview budgetVersion) throws Exception {
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        BudgetDocument budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetVersion.getDocumentNumber());
        return KraServiceLocator.getService(KualiRuleService.class).applyRules(new DocumentAuditEvent(budgetDocument));

    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService#getExemptionTypeKeyValues()
     */
    public List<KeyLabelPair> getExemptionTypeKeyValues() {
        // TODO this is to get the key values pair for exempt numbers - any other options
        // put in service ?
        ExtendedPersistableBusinessObjectValuesFinder finder = new ExtendedPersistableBusinessObjectValuesFinder();
        finder.setBusinessObjectClass(ExemptionType.class);
        finder.setKeyAttributeName("exemptionTypeCode");
        finder.setLabelAttributeName("description");
        List<KeyLabelPair> exemptionTypes = finder.getKeyValues();
        sort(exemptionTypes, new KeyLabelPairComparator());
        return exemptionTypes;
    }


    public void populateExemptNumbersToForm(ProposalDevelopmentForm proposalDevelopmentForm) {
        // initial load
        List<ProposalSpecialReview> proposalSpecialReviews = proposalDevelopmentForm.getProposalDevelopmentDocument()
                .getPropSpecialReviews();
        int i = 0;
        List<String[]> documentExemptNumbers = proposalDevelopmentForm.getDocumentExemptNumbers();
        for (ProposalSpecialReview proposalSpecialReview : proposalSpecialReviews) {
            List<ProposalExemptNumber> proposalExemptNumbers = proposalSpecialReview.getProposalExemptNumbers();
            String[] exemptNumbers = null;
            //if (proposalExemptNumbers != null && proposalExemptNumbers.size() > 0) {
                if (documentExemptNumbers == null  || documentExemptNumbers.size() - 1 < i ) {
                    if (documentExemptNumbers == null) {
                        documentExemptNumbers = new ArrayList<String[]>();
                        proposalDevelopmentForm.setDocumentExemptNumbers(documentExemptNumbers);
                    }
                    documentExemptNumbers.add(exemptNumbers);
                }
                exemptNumbers = documentExemptNumbers.get(i++);
                if ((exemptNumbers == null || exemptNumbers.length == 0) && proposalExemptNumbers != null
                        && proposalExemptNumbers.size() > 0) {
                    int idx = 0;
                    if (exemptNumbers == null || exemptNumbers.length == 0) {
                        exemptNumbers = new String[proposalExemptNumbers.size()];
                        documentExemptNumbers.remove(i-1);
                        documentExemptNumbers.add(i-1,exemptNumbers);
                    }
                    for (Object proposalExemptNumber : proposalExemptNumbers) {
                        exemptNumbers[idx++] = (((ProposalExemptNumber) proposalExemptNumber).getExemptionTypeCode());
                    }
                }
           // }
        }

    }


    public void populateProposalExempNumbers(ProposalDevelopmentForm proposalDevelopmentForm) {
        // initial load
        List<ProposalSpecialReview> proposalSpecialReviews = proposalDevelopmentForm.getProposalDevelopmentDocument()
                .getPropSpecialReviews();
        int i = 0;
        List<String[]> documentExemptNumbers = proposalDevelopmentForm.getDocumentExemptNumbers();
        for (ProposalSpecialReview proposalSpecialReview : proposalSpecialReviews) {
            List newProposalExemptNumbers = new TypedArrayList(ProposalExemptNumber.class);
            
            if (documentExemptNumbers != null && documentExemptNumbers.size() > 0) {
                String[] exemptNumbers = documentExemptNumbers.get(i++);
                if (exemptNumbers !=null) {
                    for (String exemptNumber : exemptNumbers) {
                        if (StringUtils.isNotBlank(exemptNumber)) {
                            ProposalExemptNumber proposalExemptNumber = new ProposalExemptNumber();
                            proposalExemptNumber.setProposalNumber(proposalSpecialReview.getProposalNumber());
                            proposalExemptNumber.setSpecialReviewNumber((proposalSpecialReview.getSpecialReviewNumber()));
                            proposalExemptNumber.setExemptionTypeCode(exemptNumber);
                            // TODO : below is to prevent optimistic locking issue. Tested to see if it is really needed.
                            // if (proposalExemptNumber != null && proposalExemptNumbers.size() > newProposalExemptNumbers.size()) {
                            // proposalExemptNumber.setVersionNumber(((ProposalExemptNumber)proposalExemptNumbers.get(proposalExemptNumbers.size()
                            // - 1)).getVersionNumber());
                            // }
                            newProposalExemptNumbers.add(proposalExemptNumber);
                        }
                    }
                }
            }
            proposalSpecialReview.setProposalExemptNumbers(newProposalExemptNumbers);
        }

    }

}
