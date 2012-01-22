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
package org.kuali.kra.proposaldevelopment.rules;

import static java.util.Collections.sort;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_ADD_EXISTING_UNIT;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_DELETE_LEAD_UNIT;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_INVALID_UNIT;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_INVALID_YEAR;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_INVESTIGATOR_UPBOUND;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_MISSING_CITIZENSHIP_TYPE;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_MISSING_PERSON_ROLE;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_ONE_UNIT;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_PERCENTAGE;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_PROPOSAL_PERSON_EXISTS_WITH_ROLE;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_SELECT_UNIT;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.kra.logging.FormattedLogger.debug;
import static org.kuali.kra.logging.FormattedLogger.info;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.DegreeType;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonComparator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonCreditSplit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalUnitCreditSplit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddKeyPersonRule;
import org.kuali.kra.proposaldevelopment.rule.CalculateCreditSplitRule;
import org.kuali.kra.proposaldevelopment.rule.ChangeKeyPersonRule;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Implementation of business rules required for the Key Persons Page of the 
 * <code>{@link org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument}</code>.
 *
 * @see org.kuali.rice.kns.rules.BusinessRule
 * @author $Author: cdenne $
 * @version $Revision: 1.46 $
 */
public class ProposalDevelopmentKeyPersonsRule extends ResearchDocumentRuleBase implements AddKeyPersonRule, ChangeKeyPersonRule,CalculateCreditSplitRule  {
    private static final String PERSON_HAS_UNIT_MSG = "Person %s has unit %s";
    private static final int FIELD_ERA_COMMONS_USERNAME_MIN_LENGTH = 6;
    private KcPersonService kcPersonService;
    
    /**
     * @see ResearchDocumentRuleBase#processCustomSaveDocumentBusinessRules(Document)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        return processSaveKeyPersonBusinessRules((ProposalDevelopmentDocument) document);
    }

    /**
     * Rule invoked upon saving persons to a 
     * <code>{@link org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument}</code>
     *
     * @param document ProposalDevelopmentDocument being saved
     * @return boolean
     */
    public boolean processSaveKeyPersonBusinessRules(ProposalDevelopmentDocument document) {
        info("Processing Key Personnel Save Document Rule");
        boolean retval = true;
        int pi_cnt = 0;
        int personIndex = 0;
        List<ProposalPerson> investigators = document.getDevelopmentProposal().getInvestigators();
       
               
        for (ProposalPerson person : document.getDevelopmentProposal().getProposalPersons()) {
            if (isPrincipalInvestigator(person)) {
                pi_cnt++;
                 
            }
            
            if (person.getProposalPersonExtendedAttributes() != null 
                    && person.getProposalPersonExtendedAttributes().getCitizenshipTypeCode() == null) {
                debug("error.noCitizenshipType");
                //document.developmentProposalList[0].proposalPersons[0].proposalPersonExtendedAttributes.citizenshipTypeCode
                reportError("document.developmentProposalList[0].proposalPersons[" + personIndex + "].proposalPersonExtendedAttributes.citizenshipTypeCode", ERROR_MISSING_CITIZENSHIP_TYPE);
                retval = false;
            }
            
            if (isBlank(person.getProposalPersonRoleId()) && person.getRole() == null) { 
                debug("error.missingPersonRole");
                reportError("document.developmentProposalList[0].proposalPersons[" + personIndex + "]", ERROR_MISSING_PERSON_ROLE);
            }
            personIndex++;
        }

        if (pi_cnt > 1) {
            retval = false;
            reportError("newProposalPerson", ERROR_INVESTIGATOR_UPBOUND, getKeyPersonnelService().getPrincipalInvestigatorRoleDescription(document));            
        }        
        personIndex=0;
        for (ProposalPerson person : document.getDevelopmentProposal().getProposalPersons()) {

            if(isCoInvestigator(person) && (person.getUnits() != null) && (person.getUnits().size()==0)){
                reportError("newProposalPersonUnit[" + personIndex + "].unitNumber",
                            ERROR_ONE_UNIT, person.getFullName());            
            }
            if(isKeyPerson(person) && (person.getOptInUnitStatus().equals("Y")) && (person.getUnits()!= null) && (person.getUnits().size() ==0)){
                reportError("newProposalPersonUnit[" + personIndex + "].unitNumber",
                            ERROR_ONE_UNIT, person.getFullName());  
            }
            if(isKeyPerson(person) && StringUtils.isBlank(person.getProjectRole())){
                reportError("document.developmentProposalList[0].proposalPersons[" + personIndex + "].projectRole",
                            RiceKeyConstants.ERROR_REQUIRED,"Key Person Role");
            }
            
            if(person.getPercentageEffort()!= null && (person.getPercentageEffort().isLessThan(new KualiDecimal(0)) 
                    || person.getPercentageEffort().isGreaterThan(new KualiDecimal(100)))){
                GlobalVariables.getMessageMap().putError("document.developmentProposalList[0].proposalPersons[" + personIndex + "].percentageEffort", ERROR_PERCENTAGE,
                        new String[] {"Percentage Effort" });
            }
            
            if(StringUtils.isNotBlank(person.getEraCommonsUserName()) && person.getEraCommonsUserName().length() < FIELD_ERA_COMMONS_USERNAME_MIN_LENGTH){
                GlobalVariables.getMessageMap().putError("document.developmentProposalList[0].proposalPersons[" + personIndex + "].eraCommonsUserName", KeyConstants.ERROR_MINLENGTH,
                        new String[] {"eRA Commons User Name" , ""+ FIELD_ERA_COMMONS_USERNAME_MIN_LENGTH});
            }
            
            personIndex++;
        }
        
        retval &= this.processCalculateCreditSplitBusinessRules(document);
        
        if(retval){
            boolean leadunit=false;
            ProposalPersonUnit unit=null;
            for (ProposalPerson person : document.getDevelopmentProposal().getProposalPersons()) {
                if (person.getProposalPersonRoleId().equals(PRINCIPAL_INVESTIGATOR_ROLE)) {
                      for(ProposalPersonUnit personunit:person.getUnits()){
                        if(personunit.isLeadUnit())
                            leadunit=true;
                         }
                    if(!leadunit){
                        getKeyPersonnelService().assignLeadUnit(person, document.getDevelopmentProposal().getOwnedByUnitNumber());
                    }
                    leadunit=false;
                }else if (person.getProposalPersonRoleId().equals(CO_INVESTIGATOR_ROLE)) {
                    for(ProposalPersonUnit personunit:person.getUnits()){
						if (personunit.isLeadUnit() && StringUtils.isNotBlank(person.getHomeUnit()) && !(person.getHomeUnit().equals(document.getDevelopmentProposal().getOwnedByUnitNumber()))) {
                            leadunit=true;
                           personunit.setLeadUnit(false);
                            unit= person.getUnit(personunit.getUnitNumber());
                        }
                    }
                    if(leadunit && unit!=null){
                        person.getUnits().remove(unit);
                     }
               }
           }
            sort(document.getDevelopmentProposal().getProposalPersons(), new ProposalPersonComparator());
        }
     return retval;
    }

    /**
     * Validate the following
     * 
     * <ul>
     *   <li>One principal investigator at a time</li>
     *   <li>0 or more Key Persons or Co-Investigators are allowed</li>
     *   <li>A person cannot have multiple roles, ie. a person can only be added as a key person once.</li>
     * </ul>
     * @see org.kuali.kra.proposaldevelopment.rule.AddKeyPersonRule#processAddKeyPersonBusinessRules(ProposalDevelopmentDocument,ProposalPerson)
     */
    public boolean processAddKeyPersonBusinessRules(ProposalDevelopmentDocument document, ProposalPerson person) {
        boolean retval = true;
        
        debug("validating " + person);
        info("Person role is " + person.getRole());

        if (isPrincipalInvestigator(person) && hasPrincipalInvestigator(document)) {
            debug("error.principalInvestigator.limit");
            reportError("newProposalPerson", ERROR_INVESTIGATOR_UPBOUND, getKeyPersonnelService().getPrincipalInvestigatorRoleDescription(document));
            retval = false;
        }
        info("roleid is %s", person.getProposalPersonRoleId());
        info("role is %s", person.getRole());
        if (isBlank(person.getProposalPersonRoleId()) && person.getRole() == null) {
            debug("Tried to add person without role");
            reportError("newProposalPerson", ERROR_MISSING_PERSON_ROLE);
            retval = false;
        }
        
        debug("Does document contain a proposal person with PERSON_ID " + person.getPersonId() + "?");
        debug(document.getDevelopmentProposal().getProposalPersons().contains(person) + "");
        int firstIndex = document.getDevelopmentProposal().getProposalPersons().indexOf(person);
        int lastIndex = document.getDevelopmentProposal().getProposalPersons().lastIndexOf(person);
        if (firstIndex != -1) {
            if (firstIndex == lastIndex) {
                if (isKeyPerson(person) && isKeyPerson(document.getDevelopmentProposal().getProposalPersons().get(firstIndex))) {
                    reportError("newProposalPerson", ERROR_PROPOSAL_PERSON_EXISTS_WITH_ROLE, person.getFullName(), "Key Person");
                    retval = false;
                }
                else if (isInvestigator(person) && isInvestigator(document.getDevelopmentProposal().getProposalPersons().get(firstIndex))) {
                    reportError("newProposalPerson", ERROR_PROPOSAL_PERSON_EXISTS_WITH_ROLE, person.getFullName(), "Investigator");
                    retval = false;                    
                }      
            }
            else {
                reportError("newProposalPerson", ERROR_PROPOSAL_PERSON_EXISTS_WITH_ROLE, person.getFullName(), "both Investigator and Key Person");
                retval = false;                
            }
        }
        
        if (isNotBlank(person.getProposalPersonRoleId())) {
            if ((StringUtils.isNotBlank(person.getPersonId())
                    //FIXME should be calling a count method rather than returning the entire BO
                    && this.getKcPersonService().getKcPersonByPersonId(person.getPersonId()) == null)
                    ||(person.getRolodexId() != null
                            && isInvalid(Rolodex.class, keyValue("rolodexId", person.getRolodexId())))
                    || (StringUtils.isBlank(person.getPersonId()) && person.getRolodexId() == null)) {
                reportError("newProposalPerson", ERROR_MISSING_PERSON_ROLE, person.getFullName());
                retval = false;
            }
        }
        
        if(isKeyPerson(person) && isBlank(person.getProjectRole())) {
            reportError("newProposalPerson", RiceKeyConstants.ERROR_REQUIRED,"Key Person Role");
            retval = false;
        }
        
        if (document.getDevelopmentProposal().isParent()) {
            reportError("newProposalPerson", "error.hierarchy.unexpected", "Cannot add Personnel to the Parent of a Hierarchy");
            retval = false;
        }
        
        return retval;
    }
            
    /**
     * @see org.kuali.kra.rules.ResearchDocumentRuleBase#reportError(String, String, String...)
     */
    protected void reportErrorWithPrefix(String errorPathPrefix, String propertyName, String errorKey, String... errorParams) {
        GlobalVariables.getMessageMap().addToErrorPath(errorPathPrefix);
        super.reportError(propertyName, errorKey, errorParams);
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPathPrefix);        
    }

    /**
     * @see KeyPersonnelService#isPrincipalInvestigator(ProposalPerson)
     */
    private boolean isPrincipalInvestigator(ProposalPerson person) {
        return getKeyPersonnelService().isPrincipalInvestigator(person);
    }

    /**
     * @see KeyPersonnelService#isPrincipalInvestigator(ProposalPerson)
     */
    private boolean hasPrincipalInvestigator(ProposalDevelopmentDocument document) {
        return getKeyPersonnelService().hasPrincipalInvestigator(document);
    }

    /**
     * @see KeyPersonnelService#isCoInvestigator(ProposalPerson)
     */
    private boolean isCoInvestigator(ProposalPerson person){
        return getKeyPersonnelService().isCoInvestigator(person);
    }
    
    private boolean isInvestigator(ProposalPerson person) {
        return isCoInvestigator(person) || isPrincipalInvestigator(person);
    }
    
    /**
     * @see KeyPersonnelService#isCoInvestigator(ProposalPerson)
     */
    private boolean isKeyPerson(ProposalPerson person){
        return getKeyPersonnelService().isKeyPerson(person);
    }
    /**
     * Locate in Spring <code>{@link KeyPersonnelService}</code> singleton  
     * 
     * @return KeyPersonnelService
     */
    private KeyPersonnelService getKeyPersonnelService() {
        return getService(KeyPersonnelService.class);
    }
    
    /**
     * Gets the KcPersonSevice.
     * @return the service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = getService(KcPersonService.class);    
        }
        
        return this.kcPersonService;
    }

    /**
     * Either adding a degree or unit can trigger this rule to be validated
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.ChangeKeyPersonRule#processChangeKeyPersonBusinessRules(org.kuali.kra.proposaldevelopment.bo.ProposalPerson, org.kuali.rice.krad.bo.BusinessObject)
     * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentKeyPersonnelAction#insertDegree(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentKeyPersonnelAction#insertUnit(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public boolean processChangeKeyPersonBusinessRules(ProposalPerson proposalPerson, BusinessObject source,int index) {
        boolean retval = true;
        
        if (source instanceof ProposalPersonDegree) {
            retval &= validateDegree((ProposalPersonDegree) source,index);
        }
        else if (source instanceof ProposalPersonUnit) {
            retval &= validateUnit((ProposalPersonUnit) source, proposalPerson,index);
        }
        
        return retval;
    }

    /**
     * Checks to makes sure that the unit is valid. Usually called as a result of a <code>{@link ProposalPersonUnit}</code> being added to a <code>{@link ProposalPerson}</code>.
     * 
     * @param source
     * @return boolean pass or fail
     */
    private boolean validateUnit(ProposalPersonUnit source, ProposalPerson person,int index) {
        boolean retval = true;
        
        if (source == null) {
            debug("validated null unit");
            return false;
        }
        
        debug("Validating unit %s",  source);
       
        if (source.getUnit() == null && isBlank(source.getUnitNumber()) && (GlobalVariables.getMessageMap().getMessages("newProposalPersonUnit*")== null)) {
            GlobalVariables.getMessageMap().putError("newProposalPersonUnit[" + index + "].unitNumber", ERROR_SELECT_UNIT);
            retval = false;
        }
        
        if (isNotBlank(source.getUnitNumber()) && isInvalid(Unit.class, keyValue("unitNumber", source.getUnitNumber())) && (GlobalVariables.getMessageMap().getMessages("newProposalPersonUnit*")== null)) {
            GlobalVariables.getMessageMap().putError("newProposalPersonUnit[" + index + "].unitNumber", ERROR_INVALID_UNIT,
                    source.getUnitNumber(), person.getFullName());
            retval = false;
        }

        debug("isLeadUnit %s", source.isLeadUnit());
        if(source.isDelete()){
            if(person.getProposalPersonRoleId().equals(PRINCIPAL_INVESTIGATOR_ROLE)){
               if (isDeletingUnitFromPrincipalInvestigator(source, person)&& (GlobalVariables.getMessageMap().getMessages("newProposalPersonUnit*")== null)) {
                   GlobalVariables.getMessageMap().putError("newProposalPersonUnit[" + index + "].unitNumber", ERROR_DELETE_LEAD_UNIT,
                           source.getUnitNumber(), person.getFullName());
                retval = false;
               }
            }
        }else
        {
        if((unitExists(source , person)) && (GlobalVariables.getMessageMap().getMessages("newProposalPersonUnit*")== null)){
            GlobalVariables.getMessageMap().putError("newProposalPersonUnit[" + index + "].unitNumber", ERROR_ADD_EXISTING_UNIT,
                     source.getUnitNumber(), person.getFullName());
            retval=false;
        }
        }
         debug("validateUnit = %s", retval);
        
        return retval;
    }
    
    /**
     * Determine whether the <code>{@link ProposalPersonUnit}</code> already exists by Unit Number in the 
     * given <code>{@link ProposalPerson}</code>
     * 
     * @param source
     * @param person
     * @return true or false
     */
    private boolean unitExists(ProposalPersonUnit source, ProposalPerson person) {
        for (ProposalPersonUnit unit : person.getUnits()) {
            if (unit.getUnitNumber().equals(source.getUnitNumber())) { 
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Determine if we are deleting a Lead Unit from a PI
     * 
     * @param unit intending to be deleted
     * @param person possible PI
     * @return boolean
     */
    private boolean isDeletingUnitFromPrincipalInvestigator(ProposalPersonUnit unit, ProposalPerson person) {
        boolean retval = false;
        
        info(PERSON_HAS_UNIT_MSG, person.getProposalPersonNumber(), unit.getUnitNumber());
        
        return retval && unit.isDelete() && unit.isLeadUnit() && getKeyPersonnelService().isPrincipalInvestigator(person);
    }

    /**
     * Checks to makes sure that the degree is valid. Usually called as a result of a <code>{@link ProposalPersonDegree}</code> being added to a <code>{@link ProposalPerson}</code>.
     * 
     * @param source
     * @return boolean
     */
    private boolean validateDegree(ProposalPersonDegree source,int index) {
        boolean retval = true;
     
        String regExpr = "^(16|17|18|19|20)[0-9]{2}$";
        if(source.getGraduationYear()!=null && !(source.getGraduationYear().matches(regExpr)) && GlobalVariables.getMessageMap().getMessages("document.newProposalPersonDegree") == null)
        {            
            GlobalVariables.getMessageMap().putError("newProposalPersonDegree[" + index + "].graduationYear", ERROR_INVALID_YEAR,
                    new String[] { source.getGraduationYear(), "Graduation Year"});
            retval = false;
        }

        if (source == null) {
            return false;
        }
        
        if (isNotBlank(source.getDegreeCode()) && isInvalid(DegreeType.class, keyValue("degreeCode", source.getDegreeCode()))) {
            retval = false;
        }
        if(StringUtils.isBlank(source.getDegreeCode())){
            GlobalVariables.getMessageMap().putError("newProposalPersonDegree[" + index + "].degreeCode", RiceKeyConstants.ERROR_REQUIRED,
                    new String[] {"Degree Type" });
            retval= false;
        }

        if(StringUtils.isBlank(source.getDegree())){

            GlobalVariables.getMessageMap().putError("newProposalPersonDegree[" + index + "].degree", RiceKeyConstants.ERROR_REQUIRED,
                    new String[] {"Degree Description" });
            retval= false;
        }
        
        
        if(StringUtils.isBlank(source.getGraduationYear())){

            GlobalVariables.getMessageMap().putError("newProposalPersonDegree[" + index + "].graduationYear", RiceKeyConstants.ERROR_REQUIRED,
                    new String[] {"Graduation year" });
            retval= false;
        }
        
        
        return retval;
    }
    
    public boolean processCalculateCreditSplitBusinessRules(ProposalDevelopmentDocument document) {

        List<ProposalPerson> person = document.getDevelopmentProposal().getInvestigators(); 
        boolean retval=true;
   

        for (int i = 0; i < person.size(); i++) {
            ProposalPerson propPerson = (ProposalPerson)person.get(i);
            List<ProposalPersonCreditSplit> personCreditSplit=propPerson.getCreditSplits();
            List<ProposalPersonUnit> propUnitCreditSplit=propPerson.getUnits();
            for (int j = 0; j < personCreditSplit.size(); j++) {
                ProposalPersonCreditSplit creditSplit = personCreditSplit.get(j);
                if(creditSplit.getCredit() !=null){
                    if(creditSplit.getCredit().doubleValue() > 100.00 || creditSplit.getCredit().doubleValue() < 0.00){
                        GlobalVariables.getMessageMap().putError("document.developmentProposalList[0].investigator["+i+"].creditSplits["+j+"].credit", ERROR_PERCENTAGE,
                                new String[] {"Credit Split" });
                        retval=false;
                    }
                }
                
           }
            for(int j = 0; j < propUnitCreditSplit.size(); j++){
                List<ProposalUnitCreditSplit> unitCreditSplit = propUnitCreditSplit.get(j).getCreditSplits();
                for(int k = 0; k < unitCreditSplit.size(); k++){
                    ProposalUnitCreditSplit unitSplit = unitCreditSplit.get(k);
                    if(unitSplit.getCredit()!= null){
                        if(unitSplit.getCredit().doubleValue() > 100.00 || unitSplit.getCredit().doubleValue() < 0.00){
                            GlobalVariables.getMessageMap().putError("document.developmentProposalList[0].investigator["+i+"].units["+j+"].creditSplits["+k+"].credit", ERROR_PERCENTAGE,
                                    new String[] {"Credit Split" });
                            retval=false; 
                        }
                        
                    }
                }
                
                
                
            }
            
            i++;
        }
        return retval;
    }
}

