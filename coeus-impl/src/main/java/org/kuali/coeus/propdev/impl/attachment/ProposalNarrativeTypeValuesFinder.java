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
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.uif.field.InputField;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    public ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public DataObjectService getDataObjectService() {
        if (dataObjectService == null) {
            dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    @Override
    public List<KeyValue> getKeyValues(ViewModel model, InputField field){
        DevelopmentProposal developmentProposal = ((ProposalDevelopmentDocumentForm) model).getDevelopmentProposal();
        Collection<NarrativeType> allNarrativeTypes = loadAllNarrativeTypes(developmentProposal);
        String narrativeTypeCode = "";

        try {
           narrativeTypeCode = (String) PropertyUtils.getProperty(model, field.getBindingInfo().getBindingPath());
           if (StringUtils.isNotEmpty(narrativeTypeCode)) {
           NarrativeType currentNarrativeType = getDataObjectService().find(NarrativeType.class,narrativeTypeCode);
                if (currentNarrativeType != null) {
                    if (!allNarrativeTypes.contains(currentNarrativeType)) {
                        allNarrativeTypes.add(currentNarrativeType);
                    }
                }
           }
        } catch (Exception e) {
            throw new RiceRuntimeException("could not retrieve narrative type from the input field", e);
        }

        return getFilteredKeyValues(allNarrativeTypes,developmentProposal,narrativeTypeCode);

    }

    @Override
    public List<KeyValue> getKeyValues(ViewModel model) {
        DevelopmentProposal developmentProposal = ((ProposalDevelopmentDocumentForm) model).getDevelopmentProposal();
        Collection<NarrativeType> allNarrativeTypes = loadAllNarrativeTypes(developmentProposal);
        return getFilteredKeyValues(allNarrativeTypes,developmentProposal,"");
    }
    
    public List<KeyValue> getFilteredKeyValues(Collection<NarrativeType> allNarrativeTypes, DevelopmentProposal developmentProposal, String narrativeTypeCode) {
        Collection<NarrativeType> filteredCollection = filterCollection(allNarrativeTypes,developmentProposal,narrativeTypeCode);
        return buildKeyValuesCollection(filteredCollection);
        
    }

    protected Collection<NarrativeType> filterCollection(Collection<NarrativeType> narrativeTypes, DevelopmentProposal developmentProposal, String narrativeTypeCode) {
        Collection<NarrativeType> forRemoval = new ArrayList<NarrativeType>();        
        for (NarrativeType narrativeType : narrativeTypes) {
            for (Narrative narrative : developmentProposal.getNarratives()) {
                if (narrative.getNarrativeType() != null && filterCondition(narrative.getNarrativeType(), narrativeType) &&
                        !narrative.getNarrativeTypeCode().equals(narrativeTypeCode)) {
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
            if (developmentProposal.isChild()) {
                populateValidNarrativeTypeFromParentProposal(developmentProposal, validS2SFormNarratives);
            }
        }else{
            String proposalNarrativeTypeGroup = this.getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.PROPOSAL_NARRATIVE_TYPE_GROUP);
            Map<String,String> queryMap = new HashMap<String,String>();
            queryMap.put("narrativeTypeGroup", proposalNarrativeTypeGroup);
            List<ValidNarrForms> validNarrativeForms = (List<ValidNarrForms>)getDataObjectService().findAll(ValidNarrForms.class).getResults();
            List<NarrativeType> allNarrativeTypes = (List)getDataObjectService().findMatching(NarrativeType.class, QueryByCriteria.Builder.andAttributes(queryMap).build()).getResults();
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
        return (DevelopmentProposal) (getDataObjectService().find(DevelopmentProposal.class, proposalNumber));
    }
    private List<NarrativeType> populateValidFormNarratives(DevelopmentProposal developmentProposal, List<NarrativeType> validaNarrativeTypes) {
        List<S2sOppForms> s2sOpportunityForms = developmentProposal.getS2sOppForms();
        for (S2sOppForms oppForms : s2sOpportunityForms) {
            Map<String, String> queryMap = new HashMap<String, String>();
            queryMap.put("formName", oppForms.getFormName());
            List<ValidNarrForms>  validNarrativeForms = (List)getDataObjectService().findMatching(ValidNarrForms.class,QueryByCriteria.Builder.andAttributes(queryMap).build()).getResults();
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
            String hierarchyProposalNumber = developmentProposal.getHierarchyParentProposalNumber();
            DevelopmentProposal parentProposal = getDevelopmentProposal(hierarchyProposalNumber);
            populateValidFormNarratives(parentProposal, validaNarrativeTypes);
    }


    private Map<String, String> populateGenericValidNarrativeTypes(List<NarrativeType> validaNarrativeTypes) {
        Map<String,String> queryMap = new HashMap<String,String>();
        queryMap.put("formName", "ALL");
        List<ValidNarrForms> validNarrativeForms = (List)getDataObjectService().findMatching(ValidNarrForms.class, QueryByCriteria.Builder.andAttributes(queryMap).build()).getResults();
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
}
