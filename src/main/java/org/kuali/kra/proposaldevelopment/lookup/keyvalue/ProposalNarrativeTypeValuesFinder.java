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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.bo.ValidNarrForms;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KRADServiceLocator;

/**
 * Finds the available set of supported Narrative Types.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author KRADEV team
 * 
 * Rewrite of class to implement filterable KeyValue list.
 * 
 * A complete rewrite had to be done because super class was not editable, but the patterns used 
 * here should be incorporated into super class. This class would then be refactored to again rely
 * more on super class behavior. Super class rewrite should include isolating behaviors relying on 
 * infrastructure into protected methods to allow unit tests to override with mocks and stub out 
 * database and service lookups. 
 */
public class ProposalNarrativeTypeValuesFinder extends PersistableBusinessObjectValuesFinder {
    private static final String NO = "N";
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalNarrativeTypeValuesFinder.class);
    private ParameterService parameterService;
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
    
    @Override
    public List<KeyValue> getKeyValues() {
        Collection<NarrativeType> allNarrativeTypes = loadAllNarrativeTypes();
        return getFilteredKeyValues(allNarrativeTypes);
    }
    
    public List<KeyValue> getFilteredKeyValues(Collection<NarrativeType> allNarrativeTypes) {
        Collection<NarrativeType> filteredCollection = filterCollection(allNarrativeTypes);
        return buildKeyValuesCollection(filteredCollection);
        
    }

    protected Collection<NarrativeType> filterCollection(Collection<NarrativeType> narrativeTypes) {
        ProposalDevelopmentDocument document = getDocumentFromForm();
        Collection<NarrativeType> forRemoval = new ArrayList<NarrativeType>();        
        for (NarrativeType narrativeType : narrativeTypes) {
            for (Narrative narrative : document.getDevelopmentProposal().getNarratives()) {
                if (filterCondition(narrative.getNarrativeType(), narrativeType)) {
                    forRemoval.add(narrativeType);
                } else {
                    LOG.debug("Not removing narrative type " + narrativeType.getDescription());
                }
            }            
        }
        
        // Remove any instances for with these type codes already in the document
        narrativeTypes.removeAll(forRemoval);
        
        return narrativeTypes;
    }

    private List<KeyValue> buildKeyValuesCollection(Collection<NarrativeType> narrativeTypes) {
        List<KeyValue> KeyValues = new ArrayList<KeyValue>();
        for (NarrativeType narrativeType : narrativeTypes) {
            String key = narrativeType.getNarrativeTypeCode();
            String label = narrativeType.getDescription();
            KeyValues.add(new ConcreteKeyValue(key, label));
        }
        return KeyValues;
    }
    
    @SuppressWarnings("unchecked")
    protected Collection<NarrativeType> loadAllNarrativeTypes() {
        ProposalDevelopmentDocument document = getDocumentFromForm();
        DevelopmentProposal developmentProposal = document.getDevelopmentProposal();
        List<NarrativeType> validS2SFormNarratives = new ArrayList<NarrativeType>();
        if(isS2sCandidate(developmentProposal)){
            populateValidFormNarratives(developmentProposal,validS2SFormNarratives);
            populateGenericValidNarrativeTypes(validS2SFormNarratives);
            populateValidNarrativeTypeFromParentProposal(developmentProposal,validS2SFormNarratives);
        }else{
            String proposalNarrativeTypeGroup = this.getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.PROPOSAL_NARRATIVE_TYPE_GROUP);
            Map<String,String> queryMap = new HashMap<String,String>();
            queryMap.put("narrativeTypeGroup", proposalNarrativeTypeGroup);
//            queryMap.put("systemGenerated", "N");
            List<ValidNarrForms> validNarrativeForms = (List<ValidNarrForms>)getBusinessObjectService().findAll(ValidNarrForms.class);
            List<NarrativeType> allNarrativeTypes = (List)getBusinessObjectService().findMatching(getBusinessObjectClass(), queryMap);
            validS2SFormNarratives = removeValidNarrativeForms(allNarrativeTypes,validNarrativeForms);
            //return boService.findMatchingOrderBy(getBusinessObjectClass(), queryMap, "narrativeTypeCode", true);
        }
        return validS2SFormNarratives;
    }


    private List<NarrativeType> removeValidNarrativeForms(List<NarrativeType> narrativeTypes,List<ValidNarrForms> validNarrativeForms) {
        List<NarrativeType> filteredNarrativeTypes = new ArrayList<NarrativeType>();
        populateGenericValidNarrativeTypes(filteredNarrativeTypes);
        for (NarrativeType narrativeType : narrativeTypes) {
            boolean exists = false;
            for (ValidNarrForms validNarrForm : validNarrativeForms) {
                if(validNarrForm.getNarrativeTypeCode().equals(narrativeType.getNarrativeTypeCode())){
                    exists = true;
                    break;
                }
            }
            if(!exists){
                filteredNarrativeTypes.add(narrativeType);
            }
        }
        return filteredNarrativeTypes;
    }

    private boolean isS2sCandidate(DevelopmentProposal developmentProposal) {
        return !developmentProposal.getS2sOppForms().isEmpty();
    }

    /**
     * This method...
     * @return
     */
    private BusinessObjectService getBusinessObjectService() {
        return KRADServiceLocator.getBusinessObjectService();
    }
    public DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        Map<String, String> pk = new HashMap<String, String>();
        pk.put("proposalNumber", proposalNumber);
        return (DevelopmentProposal) (getBusinessObjectService().findByPrimaryKey(DevelopmentProposal.class, pk));
    }
    private List<NarrativeType> populateValidFormNarratives(DevelopmentProposal developmentProposal, List<NarrativeType> validaNarrativeTypes) {
        List<S2sOppForms> s2sOpportunityForms = developmentProposal.getS2sOppForms();
        for (S2sOppForms oppForms : s2sOpportunityForms) {
            Map<String, String> queryMap = new HashMap<String, String>();
            queryMap.put("formName", oppForms.getFormName());
            List<ValidNarrForms>  validNarrativeForms = (List)getBusinessObjectService().findMatching(ValidNarrForms.class, queryMap);
            for (ValidNarrForms validNarrForms : validNarrativeForms) {
                if(isProposalGroup(validNarrForms)){
                    if(!isNarrativeAlreadyAdded(validaNarrativeTypes,validNarrForms.getNarrativeType()) && validNarrForms.getNarrativeType().getSystemGenerated().equals("N")){
                        validaNarrativeTypes.add(validNarrForms.getNarrativeType());
                    }
                }
            }
        }
        return validaNarrativeTypes;
    }
    private boolean isNarrativeAlreadyAdded(List<NarrativeType> validaNarrativeTypes, NarrativeType validNarrativeType) {
        for (NarrativeType narrativeType : validaNarrativeTypes) {
            if(narrativeType.getNarrativeTypeCode().equals(validNarrativeType.getNarrativeTypeCode())){
                return true;
            }
        }
        return false;
    }
    String proposalNarrativeTypeGroup = this.getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.PROPOSAL_NARRATIVE_TYPE_GROUP);
    /**
     * This method...
     * @param validNarrForms
     * @param proposalNarrativeTypeGroup
     * @return
     */
    private boolean isProposalGroup(ValidNarrForms validNarrForms) {
        return proposalNarrativeTypeGroup.equals(validNarrForms.getNarrativeType().getNarrativeTypeGroup());
    }

    /**
     * This method...
     * @param developmentProposal
     * @param validS2SFormNarratives 
     */
    private void populateValidNarrativeTypeFromParentProposal(DevelopmentProposal developmentProposal, List<NarrativeType> validaNarrativeTypes) {
        if(developmentProposal.isInHierarchy()){
            String hierarchyProposalNumber = developmentProposal.getHierarchyParentProposalNumber();
            DevelopmentProposal parentProposal = getDevelopmentProposal(hierarchyProposalNumber);
            populateValidFormNarratives(parentProposal, validaNarrativeTypes);
        }
    }

    /**
     * This method...
     * @param developmentProposal 
     * @param validaNarrativeTypes
     * @return
     */
    private Map<String, String> populateGenericValidNarrativeTypes(List<NarrativeType> validaNarrativeTypes) {
        Map<String,String> queryMap = new HashMap<String,String>();
        queryMap.put("formName", "ALL");
        List<ValidNarrForms> validNarrativeForms = (List)getBusinessObjectService().findMatching(ValidNarrForms.class, queryMap);
        for (ValidNarrForms validNarrForms : validNarrativeForms) {
            if(isProposalGroup(validNarrForms)){
                validaNarrativeTypes.add(validNarrForms.getNarrativeType());
            }
        }
        return queryMap;
    }

    protected ProposalDevelopmentDocument getDocumentFromForm() {
        return ((ProposalDevelopmentForm) KNSGlobalVariables.getKualiForm()).getDocument();
    }
    
    /**
     * Used to compare which Narrative Types already exist in the document.
     * 
     * @param document
     * @param narrativeTypeCode <code>{@link NarrativeType}</code> code
     * @return true if the narrative type is in the document and false otherwise
     */
    protected boolean filterCondition(NarrativeType documentNarrativeType, NarrativeType narrativeType) {
        return documentNarrativeType.getNarrativeTypeCode().equals(narrativeType.getNarrativeTypeCode()) && multiplesNotAllowed(narrativeType);
    }

    private boolean multiplesNotAllowed(NarrativeType narrativeType) {
        return narrativeType.getAllowMultiple().equals(NO);
    }
}
