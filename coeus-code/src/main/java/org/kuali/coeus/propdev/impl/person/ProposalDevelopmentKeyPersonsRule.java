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
package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalPersonCreditSplit;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalUnitCreditSplit;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.common.framework.person.attr.DegreeType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.person.creditsplit.CalculateCreditSplitRule;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;

import static java.util.Collections.sort;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.KeyConstants.*;

/**
 * Implementation of business rules required for the Key Persons Page of the 
 * <code>{@link org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument}</code>.
 *
 * @author $Author: cdenne $
 * @version $Revision: 1.46 $
 */
public class ProposalDevelopmentKeyPersonsRule extends KcTransactionalDocumentRuleBase implements AddKeyPersonRule, ChangeKeyPersonRule,CalculateCreditSplitRule  {

    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentKeyPersonsRule.class);

    private static final int FIELD_ERA_COMMONS_USERNAME_MIN_LENGTH = 6;
    private KcPersonService kcPersonService;
    
    @Override
    public boolean processCustomSaveDocumentBusinessRules(Document document) {
        return processSaveKeyPersonBusinessRules((ProposalDevelopmentDocument) document);
    }

    /**
     * Rule invoked upon saving persons to a 
     * <code>{@link org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument}</code>
     *
     * @param document ProposalDevelopmentDocument being saved
     * @return boolean
     */
    public boolean processSaveKeyPersonBusinessRules(ProposalDevelopmentDocument document) {
        LOG.info("Processing Key Personnel Save Document Rule");
        boolean retval = true;
        int pi_cnt = 0;
        int personIndex = 0;
        List<ProposalPerson> investigators = document.getDevelopmentProposal().getInvestigators();
       
               
        for (ProposalPerson person : document.getDevelopmentProposal().getProposalPersons()) {
            if (person.isPrincipalInvestigator()) {
                pi_cnt++;
                 
            }
            
            if (person.getCitizenshipTypeCode() == null) {
                LOG.debug("error.noCitizenshipType");
                reportError("document.developmentProposalList[0].proposalPersons[" + personIndex + "].citizenshipTypeCode", ERROR_MISSING_CITIZENSHIP_TYPE);
                retval = false;
            }
            
            if (isBlank(person.getProposalPersonRoleId()) && person.getRole() == null) {
                LOG.debug("error.missingPersonRole");
                reportError("document.developmentProposalList[0].proposalPersons[" + personIndex + "]", ERROR_MISSING_PERSON_ROLE);
            }
            personIndex++;
        }

        if (pi_cnt > 1) {
            retval = false;
            reportError("newProposalPerson", ERROR_INVESTIGATOR_UPBOUND, document.getDevelopmentProposal().getPrincipalInvestigator().getRole().getDescription());            
        }        
        personIndex=0;
        for (ProposalPerson person : document.getDevelopmentProposal().getProposalPersons()) {

            if(person.isCoInvestigator() && (person.getUnits() != null) && (person.getUnits().size()==0)){
                reportError("newProposalPersonUnit[" + personIndex + "].unitNumber",
                            ERROR_ONE_UNIT, person.getFullName());            
                retval = false;
            }
            if(person.isKeyPerson() && person.getOptInUnitStatus() && (person.getUnits()!= null) && (person.getUnits().size() ==0)){
                reportError("newProposalPersonUnit[" + personIndex + "].unitNumber",
                            ERROR_ONE_UNIT, person.getFullName());  
                retval = false;
            }
            if(person.isKeyPerson() && StringUtils.isBlank(person.getProjectRole())){
                reportError("document.developmentProposalList[0].proposalPersons[" + personIndex + "].projectRole",
                            RiceKeyConstants.ERROR_REQUIRED,"Key Person Role");

            }
            
            if(person.getPercentageEffort()!= null && (person.getPercentageEffort().isLessThan(new ScaleTwoDecimal(0))
                    || person.getPercentageEffort().isGreaterThan(new ScaleTwoDecimal(100)))){
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
     * @see org.kuali.coeus.propdev.impl.person.AddKeyPersonRule#processAddKeyPersonBusinessRules(ProposalDevelopmentDocument,ProposalPerson)
     */
    public boolean processAddKeyPersonBusinessRules(ProposalDevelopmentDocument document, ProposalPerson person) {
        boolean retval = true;

        LOG.debug("validating " + person);
        LOG.info("Person role is " + person.getRole());

        if (person.isPrincipalInvestigator() && document.getDevelopmentProposal().getPrincipalInvestigator() != null) {
            LOG.debug("error.principalInvestigator.limit");
            reportError("newProposalPerson", ERROR_INVESTIGATOR_UPBOUND, document.getDevelopmentProposal().getPrincipalInvestigator().getRole().getDescription());
            retval = false;
        }
        LOG.info("roleid is " + person.getProposalPersonRoleId());
        LOG.info("role is " + person.getRole());
        if (isBlank(person.getProposalPersonRoleId()) && person.getRole() == null) {
            LOG.debug("Tried to add person without role");
            reportError("newProposalPerson", ERROR_MISSING_PERSON_ROLE);
            retval = false;
        }

        LOG.debug("Does document contain a proposal person with PERSON_ID " + person.getPersonId() + "?");
        LOG.debug(document.getDevelopmentProposal().getProposalPersons().contains(person) + "");
        int firstIndex = document.getDevelopmentProposal().getProposalPersons().indexOf(person);
        int lastIndex = document.getDevelopmentProposal().getProposalPersons().lastIndexOf(person);
        if (firstIndex != -1) {
            if (firstIndex == lastIndex) {
                if (person.isKeyPerson() && document.getDevelopmentProposal().getProposalPersons().get(firstIndex).isKeyPerson()) {
                    reportError("newProposalPerson", ERROR_PROPOSAL_PERSON_EXISTS_WITH_ROLE, person.getFullName(), "Key Person");
                    retval = false;
                }
                else if (person.isInvestigator() && document.getDevelopmentProposal().getProposalPersons().get(firstIndex).isInvestigator()) {
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
                    && this.getKcPersonService().getKcPersonByPersonId(person.getPersonId()) == null)
                    ||(person.getRolodexId() != null
                            && isInvalid(Rolodex.class, keyValue("rolodexId", person.getRolodexId())))
                    || (StringUtils.isBlank(person.getPersonId()) && person.getRolodexId() == null)) {
                reportError("newProposalPerson", ERROR_MISSING_PERSON_ROLE, person.getFullName());
                retval = false;
            }
        }
        
        if(person.isKeyPerson() && isBlank(person.getProjectRole())) {
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
            LOG.debug("validated null unit");
            return false;
        }

        LOG.debug("Validating unit " + source);
       
        if (source.getUnit() == null && isBlank(source.getUnitNumber()) && (GlobalVariables.getMessageMap().getMessages("newProposalPersonUnit*")== null)) {
            GlobalVariables.getMessageMap().putError("newProposalPersonUnit[" + index + "].unitNumber", ERROR_SELECT_UNIT);
            retval = false;
        }
        
        if (isNotBlank(source.getUnitNumber()) && isInvalid(Unit.class, keyValue("unitNumber", source.getUnitNumber())) && (GlobalVariables.getMessageMap().getMessages("newProposalPersonUnit*")== null)) {
            GlobalVariables.getMessageMap().putError("newProposalPersonUnit[" + index + "].unitNumber", ERROR_INVALID_UNIT,
                    source.getUnitNumber(), person.getFullName());
            retval = false;
        }

        LOG.debug("isLeadUnit " + source.isLeadUnit());
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
        LOG.debug("validateUnit = " + retval);
        
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
        
        LOG.info("Person " + person.getProposalPersonNumber() + " has unit " + unit.getUnitNumber());
        
        return retval && unit.isDelete() && unit.isLeadUnit() && person.isPrincipalInvestigator();
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
        
        if (isNotBlank(source.getDegreeCode()) && isInvalid(DegreeType.class, keyValue("code", source.getDegreeCode()))) {
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

