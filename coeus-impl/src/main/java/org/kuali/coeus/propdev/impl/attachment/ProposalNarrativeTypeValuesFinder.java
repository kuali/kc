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
package org.kuali.coeus.propdev.impl.attachment;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

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
public class ProposalNarrativeTypeValuesFinder  extends FormViewAwareUifKeyValuesFinderBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalNarrativeTypeValuesFinder.class);
    private ParameterService parameterService;
    private  BusinessObjectService businessObjectService;

    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }

    protected BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null)
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        return businessObjectService;
    }
    
    @Override
    public List<KeyValue> getKeyValues(ViewModel model) {
        DevelopmentProposal developmentProposal = ((ProposalDevelopmentDocumentForm) model).getDevelopmentProposal();
        Narrative selectedNarrative = ((ProposalDevelopmentDocumentForm) model).getProposalDevelopmentAttachmentHelper().getNarrative();
        Collection<NarrativeType> allNarrativeTypes = loadAllNarrativeTypes(developmentProposal);
        return getFilteredKeyValues(allNarrativeTypes,developmentProposal,selectedNarrative);
    }
    
    public List<KeyValue> getFilteredKeyValues(Collection<NarrativeType> allNarrativeTypes, DevelopmentProposal developmentProposal, Narrative selectedNarrative) {
        Collection<NarrativeType> filteredCollection = filterCollection(allNarrativeTypes,developmentProposal,selectedNarrative);
        return buildKeyValuesCollection(filteredCollection);
        
    }

    protected Collection<NarrativeType> filterCollection(Collection<NarrativeType> narrativeTypes, DevelopmentProposal developmentProposal, Narrative selectedNarrative) {
        Collection<NarrativeType> forRemoval = new ArrayList<NarrativeType>();        
        for (NarrativeType narrativeType : narrativeTypes) {
            for (Narrative narrative : developmentProposal.getNarratives()) {
                if (narrative.getNarrativeType() != null && filterCondition(narrative.getNarrativeType(), narrativeType) &&
                        !isSelectedNarrative(narrative, selectedNarrative)) {
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
        KeyValues.add(new ConcreteKeyValue("", "select"));
        for (NarrativeType narrativeType : narrativeTypes) {
            String key = narrativeType.getCode();
            String label = narrativeType.getDescription();
            KeyValues.add(new ConcreteKeyValue(key, label));
        }
        return KeyValues;
    }
    
    @SuppressWarnings("unchecked")
    protected Collection<NarrativeType> loadAllNarrativeTypes(DevelopmentProposal developmentProposal) {
        List<NarrativeType> validS2SFormNarratives = new ArrayList<NarrativeType>();
        if(isS2sCandidate(developmentProposal)){
            populateValidFormNarratives(developmentProposal,validS2SFormNarratives);
            populateGenericValidNarrativeTypes(validS2SFormNarratives);
            populateValidNarrativeTypeFromParentProposal(developmentProposal,validS2SFormNarratives);
        }else{
            String proposalNarrativeTypeGroup = this.getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.PROPOSAL_NARRATIVE_TYPE_GROUP);
            Map<String,String> queryMap = new HashMap<String,String>();
            queryMap.put("narrativeTypeGroup", proposalNarrativeTypeGroup);
            List<ValidNarrForms> validNarrativeForms = (List<ValidNarrForms>)getBusinessObjectService().findAll(ValidNarrForms.class);
            List<NarrativeType> allNarrativeTypes = (List)getBusinessObjectService().findMatching(NarrativeType.class, queryMap);
            validS2SFormNarratives = removeValidNarrativeForms(allNarrativeTypes,validNarrativeForms);
        }
        return validS2SFormNarratives;
    }


    private List<NarrativeType> removeValidNarrativeForms(List<NarrativeType> narrativeTypes,List<ValidNarrForms> validNarrativeForms) {
        List<NarrativeType> filteredNarrativeTypes = new ArrayList<NarrativeType>();
        populateGenericValidNarrativeTypes(filteredNarrativeTypes);
        for (NarrativeType narrativeType : narrativeTypes) {
            boolean exists = false;
            for (ValidNarrForms validNarrForm : validNarrativeForms) {
                if(validNarrForm.getNarrativeTypeCode().equals(narrativeType.getCode())){
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
                    if(!isNarrativeAlreadyAdded(validaNarrativeTypes,validNarrForms.getNarrativeType()) && !validNarrForms.getNarrativeType().isSystemGenerated()){
                        validaNarrativeTypes.add(validNarrForms.getNarrativeType());
                    }
                }
            }
        }
        return validaNarrativeTypes;
    }
    private boolean isNarrativeAlreadyAdded(List<NarrativeType> validaNarrativeTypes, NarrativeType validNarrativeType) {
        for (NarrativeType narrativeType : validaNarrativeTypes) {
            if(narrativeType.getCode().equals(validNarrativeType.getCode())){
                return true;
            }
        }
        return false;
    }
    String proposalNarrativeTypeGroup = this.getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.PROPOSAL_NARRATIVE_TYPE_GROUP);

    private boolean isProposalGroup(ValidNarrForms validNarrForms) {
        return proposalNarrativeTypeGroup.equals(validNarrForms.getNarrativeType().getNarrativeTypeGroup());
    }


    private void populateValidNarrativeTypeFromParentProposal(DevelopmentProposal developmentProposal, List<NarrativeType> validaNarrativeTypes) {
        if(developmentProposal.isInHierarchy()){
            String hierarchyProposalNumber = developmentProposal.getHierarchyParentProposalNumber();
            DevelopmentProposal parentProposal = getDevelopmentProposal(hierarchyProposalNumber);
            populateValidFormNarratives(parentProposal, validaNarrativeTypes);
        }
    }


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

    protected boolean filterCondition(NarrativeType documentNarrativeType, NarrativeType narrativeType) {
        return documentNarrativeType.getCode().equals(narrativeType.getCode()) && multiplesNotAllowed(narrativeType);
    }

    private boolean multiplesNotAllowed(NarrativeType narrativeType) {
        return !narrativeType.isAllowMultiple();
    } 
    private boolean isSelectedNarrative(Narrative narrative, Narrative selectedNarrative) {
        if (selectedNarrative.getNarrativeTypeCode() != null && selectedNarrative.getNarrativeTypeCode().equals(narrative.getNarrativeTypeCode())) {
            return true;
        }
        return false;
    }
}
