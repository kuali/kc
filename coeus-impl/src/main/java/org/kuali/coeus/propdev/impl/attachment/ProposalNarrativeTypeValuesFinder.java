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
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentConstants;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.uif.field.InputField;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

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
public class ProposalNarrativeTypeValuesFinder  extends UifKeyValuesFinderBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalNarrativeTypeValuesFinder.class);
    public static final String NARRATIVE_TYPE_GROUP = "narrativeTypeGroup";
    public static final String PROPOSAL_NUMBER = "proposalNumber";
    public static final String FORM_NAME = "formName";
    public static final String ALL = "ALL";
    public static final String SELECT = "select";

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Override
    public List<KeyValue> getKeyValues(ViewModel model, InputField field){
        DevelopmentProposal developmentProposal = ((ProposalDevelopmentDocumentForm) model).getDevelopmentProposal();
        List<NarrativeType> allNarrativeTypes = new ArrayList<>(loadAllNarrativeTypes(developmentProposal));
        String narrativeTypeCode = "";

        try {
           narrativeTypeCode = getNarrativeTypeCodeFromModelValue(model, field);
           if (StringUtils.isNotEmpty(narrativeTypeCode)) {
           NarrativeType currentNarrativeType = getNarrativeType(narrativeTypeCode);
                if (currentNarrativeType != null) {
                    if (!allNarrativeTypes.contains(currentNarrativeType)) {
                        allNarrativeTypes.add(currentNarrativeType);
                    }
                }
           }
        } catch (Exception e) {
            throw new RiceRuntimeException("could not retrieve narrative type from the input field", e);
        }
        
        if (shouldAlphabetizeNarrativeTypes()) {
        	allNarrativeTypes.sort(Comparator.comparing(NarrativeType::getDescription, Comparator.nullsFirst(Comparator.naturalOrder())));
        }

        return getFilteredKeyValues(allNarrativeTypes, developmentProposal, narrativeTypeCode);

    }

	protected NarrativeType getNarrativeType(String narrativeTypeCode) {
		return getDataObjectService().find(NarrativeType.class,narrativeTypeCode);
	}

	protected String getNarrativeTypeCodeFromModelValue(ViewModel model, InputField field)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return (String) PropertyUtils.getProperty(model, field.getBindingInfo().getBindingPath());
	}

	protected Boolean shouldAlphabetizeNarrativeTypes() {
		return getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, ProposalDevelopmentConstants.Parameters.ALPHABETIZE_ATTACHMENT_TYPES);
	}

    @Override
    public List<KeyValue> getKeyValues(ViewModel model) {
        DevelopmentProposal developmentProposal = ((ProposalDevelopmentDocumentForm) model).getDevelopmentProposal();
        List<NarrativeType> allNarrativeTypes = new ArrayList<>(loadAllNarrativeTypes(developmentProposal));
        if (shouldAlphabetizeNarrativeTypes()) {
        	allNarrativeTypes.sort(Comparator.comparing(NarrativeType::getDescription, Comparator.nullsFirst(Comparator.naturalOrder())));
        }
        return getFilteredKeyValues(allNarrativeTypes, developmentProposal, "");
    }
    
    public List<KeyValue> getFilteredKeyValues(Collection<NarrativeType> allNarrativeTypes, DevelopmentProposal developmentProposal, String narrativeTypeCode) {
        Collection<NarrativeType> filteredCollection = filterCollection(allNarrativeTypes, developmentProposal, narrativeTypeCode);
        return buildKeyValuesCollection(filteredCollection);
        
    }

    protected Collection<NarrativeType> filterCollection(Collection<NarrativeType> narrativeTypes, DevelopmentProposal developmentProposal, String narrativeTypeCode) {
        Collection<NarrativeType> forRemoval = new ArrayList<>();
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
        KeyValues.add(new ConcreteKeyValue(StringUtils.EMPTY, SELECT));
        for (NarrativeType narrativeType : narrativeTypes) {
            String key = narrativeType.getCode();
            String label = narrativeType.getDescription();
            KeyValues.add(new ConcreteKeyValue(key, label));
        }
        return KeyValues;
    }

    protected Collection<NarrativeType> loadAllNarrativeTypes(DevelopmentProposal developmentProposal) {
        List<NarrativeType> narrativeTypes = new ArrayList<>();
        if(isS2sCandidate(developmentProposal)) {
            narrativeTypes.addAll(populateNarrativeTypesFrom(developmentProposal));
        } else if (developmentProposal.isChild()) {
            narrativeTypes.addAll(populateNarrativeTypesFromParent(developmentProposal.getHierarchyParentProposalNumber()));
        }
        narrativeTypes.addAll(populateDefaultNarrativeForms());
        return narrativeTypes;
    }

    private Collection<NarrativeType> populateNarrativeTypesFromParent(String hierarchyProposalNumber) {
        List<NarrativeType> validS2SFormNarratives = new ArrayList<>();
        DevelopmentProposal parentProposal = getDevelopmentProposal(hierarchyProposalNumber);
        if (isS2sCandidate(parentProposal)) {
            validS2SFormNarratives.addAll(populateNarrativeTypesFrom(parentProposal));
        }
        return validS2SFormNarratives;
    }

    public DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        return getDataObjectService().find(DevelopmentProposal.class,proposalNumber);
    }

    private boolean isS2sCandidate(DevelopmentProposal developmentProposal) {
        return !developmentProposal.getS2sOppForms().isEmpty();
    }

    private List<NarrativeType> populateNarrativeTypesFrom(DevelopmentProposal developmentProposal) {
        List<NarrativeType> validNarrativeTypes = new ArrayList<>();
        validNarrativeTypes.addAll(populateValidFormNarratives(developmentProposal));
        return validNarrativeTypes;
    }

    private List<NarrativeType> populateDefaultNarrativeForms() {
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put(NARRATIVE_TYPE_GROUP, getProposalNarrativeTypeGroup());
        List<ValidNarrForms> validNarrativeForms = getDataObjectService().findAll(ValidNarrForms.class).getResults();
        List<NarrativeType> narrativeTypes = getDataObjectService().findMatching(NarrativeType.class, QueryByCriteria.Builder.andAttributes(queryMap).build()).getResults();
        List<NarrativeType> filteredNarrativeTypes = populateGenericValidNarrativeTypes();
        for (NarrativeType narrativeType : narrativeTypes) {
            if(!validNarrFormAlreadyExists(validNarrativeForms, narrativeType)){
                filteredNarrativeTypes.add(narrativeType);
            }
        }
        return filteredNarrativeTypes;
    }

    private boolean validNarrFormAlreadyExists(List<ValidNarrForms> validNarrativeForms, NarrativeType narrativeType) {
        for (ValidNarrForms validNarrForm : validNarrativeForms) {
            if(validNarrForm.getNarrativeTypeCode().equals(narrativeType.getCode())){
               return true;
            }
        }
        return false;
    }

    private List<NarrativeType> populateValidFormNarratives(DevelopmentProposal developmentProposal) {
        List<NarrativeType> validNarrativeTypes = new ArrayList<>();
        List<S2sOppForms> s2sOpportunityForms = developmentProposal.getS2sOppForms();
        for (S2sOppForms oppForms : s2sOpportunityForms) {
            Map<String, String> queryMap = new HashMap<>();
            queryMap.put(FORM_NAME, oppForms.getFormName());
            List<ValidNarrForms>  validNarrativeForms = getDataObjectService().findMatching(ValidNarrForms.class,QueryByCriteria.Builder.andAttributes(queryMap).build()).getResults();
            for (ValidNarrForms validNarrForms : validNarrativeForms) {
                if(isProposalGroup(validNarrForms.getNarrativeType().getNarrativeTypeGroup())){
                    if(!isNarrativeAlreadyAdded(validNarrativeTypes,validNarrForms.getNarrativeType()) && !validNarrForms.getNarrativeType().isSystemGenerated()){
                        validNarrativeTypes.add(validNarrForms.getNarrativeType());
                    }
                }
            }
        }
        return validNarrativeTypes;
    }

    private boolean isNarrativeAlreadyAdded(List<NarrativeType> validaNarrativeTypes, NarrativeType validNarrativeType) {
        for (NarrativeType narrativeType : validaNarrativeTypes) {
            if(narrativeType.getCode().equals(validNarrativeType.getCode())){
                return true;
            }
        }
        return false;
    }

    private boolean isProposalGroup(String narrativeTypeCode) {
        return StringUtils.equals(narrativeTypeCode, getProposalNarrativeTypeGroup());
    }

    private String getProposalNarrativeTypeGroup() {
        return getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.PROPOSAL_NARRATIVE_TYPE_GROUP);
    }

    private List<NarrativeType> populateGenericValidNarrativeTypes() {
        List<NarrativeType> validNarrativeTypes = new ArrayList<>();
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put(FORM_NAME, ALL);
        List<ValidNarrForms> validNarrativeForms = getDataObjectService().findMatching(ValidNarrForms.class, QueryByCriteria.Builder.andAttributes(queryMap).build()).getResults();
        for (ValidNarrForms validNarrForms : validNarrativeForms) {
            if(isProposalGroup(validNarrForms.getNarrativeType().getNarrativeTypeGroup())){
                validNarrativeTypes.add(validNarrForms.getNarrativeType());
            }
        }
        return validNarrativeTypes;
    }

    protected boolean filterCondition(NarrativeType documentNarrativeType, NarrativeType narrativeType) {
        return documentNarrativeType.getCode().equals(narrativeType.getCode()) && multiplesNotAllowed(narrativeType);
    }

    private boolean multiplesNotAllowed(NarrativeType narrativeType) {
        return !narrativeType.isAllowMultiple();
    }

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
}
