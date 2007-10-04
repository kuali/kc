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
package org.kuali.kra.proposaldevelopment.web.struts.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.web.struts.form.KualiTransactionalDocumentFormBase;
import org.kuali.kra.bo.PersonEditableField;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.PropLocation;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.PropSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalUnitCreditSplit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rules.ProposalDevelopmentKeyPersonsRule;
import org.kuali.kra.service.SponsorService;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentForm extends KualiTransactionalDocumentFormBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentForm.class);
    private String primeSponsorName;
    private PropLocation newPropLocation;
    private PropSpecialReview newPropSpecialReview;
    private ProposalPerson newProposalPerson;
    private ProposalPersonDegree newProposalPersonDegree;
    private Unit newProposalPersonUnit;
    private String newRolodexId;
    private String newPersonId;
    private String addToPerson;
    private Narrative newNarrative;
    private FormFile narrativeFile;
    private Map personEditableFields;
    private List<ProposalPerson> investigators;
    private boolean showMaintenanceLinks;

    /**
     * Used to indicate which result set we're using when refreshing/returning from a multi-value lookup
     */
    private String lookupResultsSequenceNumber;
    /**
     * The type of result returned by the multi-value lookup
     *
     * TODO: to be persisted in the lookup results service instead? See https://test.kuali.org/confluence/display/KULRNE/Using+multiple+value+lookups
     */
    private String lookupResultsBOClassName;

    public ProposalDevelopmentForm() {
        super();
        this.setDocument(new ProposalDevelopmentDocument());
        newPropLocation=new PropLocation();
        newPropSpecialReview=new PropSpecialReview();
        setNewProposalPerson(new ProposalPerson());
        setNewProposalPersonDegree(new ProposalPersonDegree());
        setNewProposalPersonUnit(new Unit());
        setInvestigators(new ArrayList<ProposalPerson>());
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
        this.setHeaderNavigationTabs((dataDictionaryService.getDataDictionary().getDocumentEntry(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument.class.getName())).getHeaderTabNavigation());
        
    }


    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return (ProposalDevelopmentDocument) this.getDocument();
    }

    @Override
    public void populate(HttpServletRequest request) {

        super.populate(request);
        ProposalDevelopmentDocument proposalDevelopmentDocument=getProposalDevelopmentDocument();

        proposalDevelopmentDocument.refreshReferenceObject("sponsor");
    }


    public PropLocation getNewPropLocation() {
        return newPropLocation;
    }


    public void setNewPropLocation(PropLocation newPropLocation) {
        this.newPropLocation = newPropLocation;
    }


    public PropSpecialReview getNewPropSpecialReview() {
        return newPropSpecialReview;
    }


    public void setNewPropSpecialReview(PropSpecialReview newPropSpecialReview) {
        this.newPropSpecialReview = newPropSpecialReview;
    }

    /* Reset method
     * @param mapping
     * @param request
     * reset check box values in keyword panel and properties that much be read on each request.
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.setMethodToCall(null);
        this.setRefreshCaller(null);
        this.setAnchor(null);
        this.setTabStates(new HashMap<String, String>());
        this.setCurrentTabIndex(0);

        
        ProposalDevelopmentDocument proposalDevelopmentDocument = this.getProposalDevelopmentDocument();
        List<PropScienceKeyword> keywords = proposalDevelopmentDocument.getPropScienceKeywords();
        for(int i=0; i<keywords.size(); i++) {
            PropScienceKeyword propScienceKeyword = (PropScienceKeyword)keywords.get(i);
            propScienceKeyword.setSelectKeyword(false);
        }
    }


    /**
     * Sets the primeSponsorName attribute value.
     * @param primeSponsorName The primeSponsorName to set.
     */
    public void setPrimeSponsorName(String primeSponsorName) {
        this.primeSponsorName = primeSponsorName;
    }


    /**
     * Gets the primeSponsorName attribute.
     * @return Returns the primeSponsorName.
     */
    public String getPrimeSponsorName() {
        return primeSponsorName;
    }

    /**
     * Gets the value of newPersonId
     *
     * @return the value of newPersonId
     */
    public String getNewPersonId() {
        return this.newPersonId;
    }

    /**
     * Sets the value of newPersonId
     *
     * @param argNewPersonId Value to assign to this.newPersonId
     */
    public void setNewPersonId(String argNewPersonId) {
        this.newPersonId = argNewPersonId;
    }

    public String getLookupResultsSequenceNumber() {
        return lookupResultsSequenceNumber;
    }

    public void setLookupResultsSequenceNumber(String lookupResultsSequenceNumber) {
        this.lookupResultsSequenceNumber = lookupResultsSequenceNumber;
    }


    /**
     * Gets the value of newProposalPerson
     *
     * @return the value of newProposalPerson
     */
    public ProposalPerson getNewProposalPerson() {
        return this.newProposalPerson;
    }

    /**
     * Sets the value of newProposalPerson
     *
     * @param argNewProposalPerson Value to assign to this.newProposalPerson
     */
    public void setNewProposalPerson(ProposalPerson argNewProposalPerson) {
        this.newProposalPerson = argNewProposalPerson;
    }

    /**
     * Gets the value of newProposalPersonUnit
     *
     * @return the value of newProposalPersonUnit
     */
    public Unit getNewProposalPersonUnit() {
        return this.newProposalPersonUnit;
    }

    /**
     * Sets the value of newProposalPersonUnit
     *
     * @param argUnit Value to assign to this.newProposalPersonUnit
     */
    public void setNewProposalPersonUnit(Unit argUnit) {
        this.newProposalPersonUnit = argUnit;
    }

    /**
     * Gets the value of newProposalPersonDegree
     *
     * @return the value of newProposalPersonDegree
     */
    public ProposalPersonDegree getNewProposalPersonDegree() {
        return this.newProposalPersonDegree;
    }

    /**
     * Sets the value of newProposalPersonDegree
     *
     * @param argDegree Value to assign to this.newProposalPersonDegree
     */
    public void setNewProposalPersonDegree(ProposalPersonDegree argDegree) {
        this.newProposalPersonDegree = argDegree;
    }

    /**
     * Gets the value of newRolodexId
     *
     * @return the value of newRolodexId
     */
    public String getNewRolodexId() {
        return this.newRolodexId;
    }

    /**
     * This is the person to add a unit, certification, or degree to
     *
     * @return Integer
     */
    public String getAddToPerson() {
        return addToPerson;
    }

    /**
     * This is the person to add a unit, certification, or degree to
     *
     * @param name of the ProposalPerson to add to (may look something like </code>document.proposalPerson[0]</code>
     */
    public void setAddToPerson(String name) {
        addToPerson = name;
    }

    /**
     * Sets the value of newRolodexId
     *
     * @param argNewRolodexId Value to assign to this.newRolodexId
     */
    public void setNewRolodexId(String argNewRolodexId) {
        this.newRolodexId = argNewRolodexId;
    }

    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }

    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }

    /**
     * Gets the newNarrative attribute. 
     * @return Returns the newNarrative.
     */
    public Narrative getNewNarrative() {
        return newNarrative;
    }


    /**
     * Sets the newNarrative attribute value.
     * @param newNarrative The newNarrative to set.
     */
    public void setNewNarrative(Narrative newNarrative) {
        this.newNarrative = newNarrative;
    }


    public FormFile getNarrativeFile() {
        return narrativeFile;
    }


    public void setNarrativeFile(FormFile narrativeFile) {
        this.narrativeFile = narrativeFile;
    }

    public boolean isShowMaintenanceLinks(){
        return showMaintenanceLinks;
    }
    
    public void setShowMaintenanceLinks(boolean showMaintenanceLinks) {
        this.showMaintenanceLinks = showMaintenanceLinks;
    }


    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }
    
    /**
     * Creates the list of <code>{@link PersonEditableField}</code> field names.
     */
    public void populatePersonEditableFields() {
        setPersonEditableFields(new HashMap());
        
        Collection<PersonEditableField> fields = getBusinessObjectService().findAll(PersonEditableField.class);
        for (PersonEditableField field : fields) {
            LOG.info("Putting into editable field map " + field);
            getPersonEditableFields().put(field.getFieldName(), new Boolean(true));
        }
    }

    public void setPersonEditableFields(Map fields) {
        personEditableFields = fields;
    }    

    /**
     * Returns a an array of editablefields
     */
    public Map getPersonEditableFields() {
        return personEditableFields;
    }

    public void setInvestigators(List<ProposalPerson> investigators) {
        this.investigators = investigators;
    }

    public List<ProposalPerson> getInvestigators() {
        return investigators;
    }

    public List<InvestigatorCreditType> getInvestigatorCreditTypes() {
        List retval = new ArrayList();
        Collection<InvestigatorCreditType> types = getBusinessObjectService().findAll(InvestigatorCreditType.class);
        for (InvestigatorCreditType type : types) {
            retval.add(type);
        }
        return retval;
    }

    /**
     * Populate investigators
     */
    public void populateInvestigators() {
        // Populate Investigators from a proposal document's persons
        for (ProposalPerson person : getProposalDevelopmentDocument().getProposalPersons()) {
            if (new ProposalDevelopmentKeyPersonsRule().isInvestigator(person) 
                && !getInvestigators().contains(person)) {
                getInvestigators().add(person);
            }
        }
    }
    
    public Map getCreditSplitTotals() {
        Map<String, Map<Integer,KualiDecimal>> retval = new HashMap<String,Map<Integer,KualiDecimal>>();
        List<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        
        for (ProposalPerson investigator : getInvestigators()) {
            LOG.info("Found investigator " + investigator.getFullName());
            Map<Integer,KualiDecimal> creditTypeTotals = retval.get(investigator.getFullName());

            if (creditTypeTotals == null) {
                creditTypeTotals = new HashMap<Integer,KualiDecimal>();
                retval.put(investigator.getFullName(), creditTypeTotals);
            }
            
            // Initialize everything to zero
            for (InvestigatorCreditType creditType : creditTypes) {                
                    KualiDecimal totalCredit = creditTypeTotals.get(creditType.getInvCreditTypeCode());
                    
                    if (totalCredit == null) {
                        totalCredit = new KualiDecimal(0);
                        creditTypeTotals.put(creditType.getInvCreditTypeCode(), totalCredit);
                    }
            }

            for (ProposalPersonUnit unit : investigator.getUnits()) {
                for (ProposalUnitCreditSplit creditSplit : unit.getCreditSplits()) {
                    LOG.info("Found credit split for unit");
                    KualiDecimal totalCredit = creditTypeTotals.get(creditSplit.getInvCreditTypeCode());
                    
                    if (totalCredit == null) {
                        totalCredit = new KualiDecimal(0);
                        creditTypeTotals.put(creditSplit.getInvCreditTypeCode(), totalCredit);
                    }
                    totalCredit.add(creditSplit.getCredit());
                }
            }
        }
        
        return retval;
    }
}
