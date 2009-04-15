/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.service;

import java.util.Collection;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

/**
 * A Service for persisted modifications of Key Personnel related business objects
 *
 * @see org.kuali.kra.proposaldevelopment.bo.ProposalPerson
 * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentKeyPersonnelAction
 * @see org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm
 * @author $Author: gmcgrego $
 * @version $Revision: 1.11 $
 */
public interface KeyPersonnelService {
    /**
     * Part of a complete breakfast, it has everything you need to populate Key Personnel into a <code>{@link ProposalDevelopmentDocument}</code>
     * 
     * @param document
     */
    public void populateDocument(ProposalDevelopmentDocument document);

    /**
     * Part of a complete breakfast, it has everything you need to populate Key Personnel
     * 
     * @param person
     * @param document
     */
    public void populateProposalPerson(ProposalPerson person, ProposalDevelopmentDocument document);
    
    /**
     * Create a <code>{@link Collection}</code> from all the ACTIVE <code>{@link InvestigatorCreditTypes}</code>
     * stored persistently.
     * 
     * @return a <code>{@link Collection}</code> of <code>{@link InvestigatorCreditType}</code> instances.
     */
    public Collection<InvestigatorCreditType> getInvestigatorCreditTypes();

    /**
     * Add a <code>{@link ProposalPersonUnit}</code> instance to a <code>{@link ProposalPerson}</code>. 
     * 
     * @param person
     * @param unit
     */
    public void addUnitToPerson(ProposalPerson person, ProposalPersonUnit unit);

    /**
     * Everytime something changes that will effect credit split values, this gets called to generate a graph of the
     * new data.
     *
     * @param document
     * @return Map
     */
    public Map calculateCreditSplitTotals(ProposalDevelopmentDocument document);

    /**
     * Retrieve the injected <code>{@link BusinessObjectService}</code>
     */
    public BusinessObjectService getBusinessObjectService();

    /**
     * assign the <code>{@link BusinessObjectService}</code> to use.
     */
    public void setBusinessObjectService(BusinessObjectService boservice);

    /**
     * Uses a <code>{@link Unit}</code> obtained from the <code>{@link Unit}</code> lookup on the 
     * <code>{@link ProposalDevelopmentForm}</code> to create a <code>{@link ProposalPersonUnit}</code> instance.
     *
     * @param unitId
     * @return ProposalPersonUnit
     */
    public ProposalPersonUnit createProposalPersonUnit(String unitId, ProposalPerson person);

    /**
     * Uses a <code>personId</code> obtained from the <code>{@link Person}</code> lookup on the 
     * <code>{@link ProposalDevelopmentForm}</code> to create a <code>{@link ProposalPerson}</code> instance.
     *
     * @param personId
     * @return ProposalPerson
     */
    public ProposalPerson createProposalPersonFromPersonId(String personId);

    /**
     * Uses a <code>rolodexId</code> obtained from the <code>{@link Person}</code> or <code>{@link Rolodex}</code> lookup on the 
     * <code>{@link ProposalDevelopmentForm}</code> to create a <code>{@link ProposalPerson}</code> instance.
     *
     * @param rolodexId
     * @return ProposalPerson
     */
    public ProposalPerson createProposalPersonFromRolodexId(String rolodexId);

    /**
     * Determines if a given {@link ProposalPerson} instance is considered a PI. This is done by comparing the {@link ProposalPersonRole} of the
     *  {@link ProposalPerson} as a PI role.
     * 
     * @param person {@link ProposalPerson} instance to compare
     * @return true if the {@link ProposalPerson} is a PI
     */
    public boolean isPrincipalInvestigator(ProposalPerson person);

    /**
     * Determines if a given {@link ProposalPerson} instance is considered a COI. This is done by comparing the {@link ProposalPersonRole} of the
     *  {@link ProposalPerson} as a COI role.
     * 
     * @param person {@link ProposalPerson} instance to compare
     * @return true if the {@link ProposalPerson} is a COI
     */
    public boolean isCoInvestigator(ProposalPerson person);
    
    
    /**
     * Determines if a given {@link ProposalPerson} instance is considered a KP. This is done by comparing the {@link ProposalPersonRole} of the
     *  {@link ProposalPerson} as a COI role.
     * 
     * @param person {@link ProposalPerson} instance to compare
     * @return true if the {@link ProposalPerson} is a COI
     */
    public boolean isKeyPerson(ProposalPerson person);
    

    /**
     * Determines if a given {@link ProposalPerson} instance is considered a PI or COI. This is done by comparing the {@link ProposalPersonRole} of the
     *  {@link ProposalPerson} as a PI or COI role.
     * 
     * @param person {@link ProposalPerson} instance to compare
     * @return true if the {@link ProposalPerson} is a PI or COI
     */
    public boolean isInvestigator(ProposalPerson person);
        
    
    /**
     * Determines if a {@link ProposalDevelopmentDocument has a PI composited in it anywhere
     * 
     * @param document
     * @return true if the {@link ProposalDevelopmentDocument} has a PI
     */
    public boolean hasPrincipalInvestigator(ProposalDevelopmentDocument document);

    /**
     * Checks if the application-level configuration parameter for credit splits is enabled. This allows credit splits to be used within the application
     * 
     * 
     * @return true if credit split enabled configuration parameter is on
     * @see org.kuali.kra.proposaldevelopment.bo.CreditSplit
     */
    public boolean isCreditSplitEnabled();
    
    
    /**
     * Used by Spring to inject the {@link KualiConfigurationService}
     * 
     * @param kualiConfigurationService instance to assign
     */
    public void setConfigurationService(KualiConfigurationService kualiConfigurationService);
    
    
    /**
     * Retrieve the injected {@link KualiConfigurationService}
     * 
     * @return {@link KualiConfigurationService}
     */
    public KualiConfigurationService getConfigurationService();
        
    /**
     * Compares the given <code>roleId</code> against the <code>proposaldevelopment.personrole.readonly.roles</code> to see if it is 
     * read only or not.
     * 
     * @param roleId to check
     * @return true if the <code>roleId</code> is a value in the <code>proposaldevelopment.personrole.readonly.roles</code> system parameter, and false
     *         if the <coderoleId</code> is null
     * @see #isRoleReadOnly(ProposalPersonRole)
     */
    public boolean isRoleReadOnly(String roleId);
    
    /**
     * Compares the <code>roleId</code> of the given {@link ProposalPersonRole} against the <code>proposaldevelopment.personrole.readonly.roles</code> to see if it is 
     * read only or not.
     * 
     * @param role to check
     * @return true if the <code>role</code> is a value in the <code>proposaldevelopment.personrole.readonly.roles</code> system parameter, and false
     *         if the <code>role</code> is null
     * @see #isRoleReadOnly(String)
     */
    public boolean isRoleReadOnly(ProposalPersonRole role);

    /**
     * 
     * @param document to get Sponsor status
     * @return Principal Investigator Role Description from the System Parameters
     */
    public String getPrincipalInvestigatorRoleDescription(ProposalDevelopmentDocument document);
    
    /**
     * Assigns the lead unit of the proposal to the given principal investigator
     *
     * @param document
     * @param person Principal 
     */
    
    public void assignLeadUnit(ProposalPerson person, String unitNumber);
    
    
    /**
     * Determines whether the sponsor on the document is NIH
     *
     * @param document
     * @return true or false
     */
    
    public boolean isSponsorNIH(ProposalDevelopmentDocument document);
     
}
