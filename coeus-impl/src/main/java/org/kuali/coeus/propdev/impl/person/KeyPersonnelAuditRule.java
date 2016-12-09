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
package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.collections4.keyvalue.DefaultMapEntry;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplitConstants;
import org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplitValidator;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.*;
import static org.kuali.kra.infrastructure.KeyConstants.*;




/**
 * Rules that invoke audit mode for KeyPersonnel
 */
public class KeyPersonnelAuditRule extends KcTransactionalDocumentRuleBase implements DocumentAuditRule {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KeyPersonnelAuditRule.class);
    public static final String SPONSOR_GROUPS = "Sponsor Groups";

    private SponsorHierarchyService sponsorHierarchyService;
    private KeyPersonnelService keyPersonnelService;
    private ParameterService parameterService;

    protected SponsorHierarchyService getSponsorHierarchyService(){
        if (sponsorHierarchyService == null)
            sponsorHierarchyService = KcServiceLocator.getService(SponsorHierarchyService.class);
        return sponsorHierarchyService;
    }
    protected KeyPersonnelService getKeyPersonnelService (){
        if (keyPersonnelService == null)
            keyPersonnelService = KcServiceLocator.getService(KeyPersonnelService.class);
        return keyPersonnelService;
    }

    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }

    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument) document;
        boolean retval = true;

        if (!hasPrincipalInvestigator(pd)) {
            retval = false;
            getAuditErrors(StringUtils.EMPTY, AUDIT_ERRORS).add(new AuditError(PERSONNEL_PAGE_ID, ERROR_INVESTIGATOR_LOWBOUND, PERSONNEL_PAGE_ID));
        }
        // Include normal save document business rules
        retval &= new ProposalDevelopmentKeyPersonsRule().processCustomSaveDocumentBusinessRules(pd);
        
        boolean hasInvestigator = false;
        boolean hasOptInPerson = false;
        int  personCount = 0;
        for (ProposalPerson person : pd.getDevelopmentProposal().getProposalPersons()) {
            retval &= validateInvestigator(person,personCount);
            retval = doPIsHaveCorrectEraCommonsNames(retval, personCount, person, pd);

            if (!hasInvestigator && isInvestigator(person)) {
                hasInvestigator = true;
            }

            if (BooleanUtils.isTrue(person.getIncludeInCreditAllocation())) {
                hasOptInPerson = true;
            }
            personCount++;
        }

        if (hasInvestigator || (hasOptInPerson && getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants
                .KC_ALL_PARAMETER_DETAIL_TYPE_CODE, CreditSplitConstants.ENABLE_OPT_IN_PERSONNEL_CREDIT_SPLIT_FUNCTIONALITY))) {
            retval &= validateCreditSplit((ProposalDevelopmentDocument) document);
        }
        return retval;

    }

    protected boolean doPIsHaveCorrectEraCommonsNames(boolean retval, int personCount, ProposalPerson person, ProposalDevelopmentDocument pd) {
        if (pd.getDevelopmentProposal().getS2sOpportunity() != null &&
                !pd.getDevelopmentProposal().getS2sOpportunity().getOpportunityId().isEmpty() &&
                getSponsorHierarchyService().isSponsorInHierarchy(pd.getDevelopmentProposal().getSponsorCode(), SPONSOR_GROUPS, 1, Constants.NIH_SPONSOR_ACRONYM)) {
            if (person.isMultiplePi() || person.isPrincipalInvestigator()) {
                retval &= validateEraCommonsUserName(person, personCount);
            }
        }

        return retval;
    }

    protected boolean validateEraCommonsUserName(ProposalPerson person, int personCount) {
        boolean retval = true;
        if (person.getEraCommonsUserName() == null) {
            retval = false;
            getAuditErrors(PERSONNEL_DETAIL_SECTION_NAME, AUDIT_ERRORS).add(new AuditError("document.developmentProposal.proposalPersons[" + personCount + "].eraCommonsUserName",
                    ERROR_ERA_COMMON_USER_NAME,PERSONNEL_PAGE_ID,new String[]{person.getFullName()}));

        } else {
            if(person.getEraCommonsUserName().length() < Constants.ERA_COMMONS_USERNAME_MIN_LENGTH) {
                getAuditErrors(PERSONNEL_DETAIL_SECTION_NAME, AUDIT_WARNINGS).add(new AuditError("document.developmentProposal.proposalPersons[" + personCount + "].eraCommonsUserName",
                        KeyConstants.ERROR_MINLENGTH, PERSONNEL_PAGE_ID, new String[] {"eRA Commons User Name for user " + person.getFullName() + " ", ""+ Constants.ERA_COMMONS_USERNAME_MIN_LENGTH}));

            }
        }
        return retval;
    }

   /**
    *
    * @param person <code>{@link ProposalPerson}</code> instance that is also an investigator to validate
    * @boolean investigator is valid
    * @Wsee #validateInvestigatorUnits(ProposalPerson)
    */
   protected boolean validateInvestigator(ProposalPerson person,int personCount) {
       boolean retval = true;
       
       if (!isInvestigator(person)) {
           return retval;
       }

       retval &= validateInvestigatorUnits(person, personCount);
       
       return retval;
   }
   
   /**
    *
    * @param person <code>{@link ProposalPerson}</code> instance who's units we want to validate
    * @return boolean Investigator Units are valid
    */
   protected boolean validateInvestigatorUnits(ProposalPerson person,int personCount) {
       boolean retval = true;
       
       List<AuditError> auditErrors = new ArrayList<AuditError>();
       LOG.info("validating units for " + person.getPersonId() + " " + person.getFullName());
       
       if (person.getUnits().size() < 1) {
           LOG.info("error.investigatorUnits.limit");
           getAuditErrors(PERSONNEL_UNIT_SECTION_NAME, AUDIT_ERRORS).add(new AuditError("document.developmentProposal.proposalPersons[" + personCount + "].units", ERROR_INVESTIGATOR_UNITS_UPBOUND,PERSONNEL_PAGE_ID));
       }
       
       for (ProposalPersonUnit unit : person.getUnits()) {
           if (isBlank(unit.getUnitNumber())) {
               LOG.trace("error.investigatorUnits.limit");
               getAuditErrors(PERSONNEL_UNIT_SECTION_NAME, AUDIT_ERRORS).add(new AuditError("document.developmentProposal.proposalPersons[" + personCount + "].units", ERROR_INVESTIGATOR_UNITS_UPBOUND,PERSONNEL_PAGE_ID));
           }
           
           retval &= validateUnit(unit);
       }

       return retval;
   }

   /**
    * Checks to makes sure that the unit is valid. Usually called as a result of a <code>{@link ProposalPersonUnit}</code> being added to a <code>{@link ProposalPerson}</code>.
    * 
    * @param source
    * @return boolean pass or fail
    */
   private boolean validateUnit(ProposalPersonUnit source) {
       boolean retval = true;
       
       if (source == null) {
           LOG.info("validated null unit");
           return false;
       }
       
       if (source.getUnit() == null && isBlank(source.getUnitNumber())) {
           retval = false;
       }
       
       if (isNotBlank(source.getUnitNumber()) && isInvalid(Unit.class, keyValue("unitNumber", source.getUnitNumber()))) {
           retval = false;
       }

       LOG.debug("Validating " + source);
       LOG.debug("validateUnit = " + retval);
       
       return retval;
   }

   /**
    * Convenience method for creating a <code>{@link SimpleEntry}</code> out of a key/value pair
    * 
    * @param key
    * @param value
    * @return SimpleImmutableEntry
    */
   private Entry<String, String> keyValue(String key, String value) {
       return new DefaultMapEntry(key, value);
   }

    private boolean isInvestigator(ProposalPerson person) {
        return person.isInvestigator();
    }
        
    private boolean hasPrincipalInvestigator(ProposalDevelopmentDocument document) {
        return document.getDevelopmentProposal().getPrincipalInvestigator() != null;
    }

    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List&lt;AuditError&gt;}</code>
     * to the auditErrorMap.
     *
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditErrors(String sectionName, String severity) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String clusterKey = PERSONNEL_PAGE_NAME + "." + sectionName;
        if (!GlobalVariables.getAuditErrorMap().containsKey(clusterKey)) {
           GlobalVariables.getAuditErrorMap().put(clusterKey, new AuditCluster(clusterKey, auditErrors, severity));
        }
        else {
            auditErrors = ((AuditCluster)GlobalVariables.getAuditErrorMap().get(clusterKey)).getAuditErrorList();
        }

        return auditErrors;
    }

    /**
     * Check if credit split totals validate
     *
     * @param document <code>{@link ProposalDevelopmentDocument}</code> instance to validate
     * credit splits of
     * @boolean is the credit split valid?
     * @see org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplitValidator#validate(ProposalDevelopmentDocument)
     */
    protected boolean validateCreditSplit(ProposalDevelopmentDocument document) {
        boolean retval = true;
        
        if (getKeyPersonnelService().isCreditSplitEnabled()) {
            CreditSplitValidator validator = new CreditSplitValidator();
            retval &= validator.validate(document);
        }
        
        return retval;
    }

}
