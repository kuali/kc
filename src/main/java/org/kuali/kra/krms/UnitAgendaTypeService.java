/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.krms;

import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
import org.kuali.rice.core.api.uif.DataType;
import org.kuali.rice.core.api.uif.RemotableAttributeField;
import org.kuali.rice.core.api.uif.RemotableTextInput;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krms.api.engine.ExecutionEnvironment;
import org.kuali.rice.krms.api.repository.agenda.AgendaDefinition;
import org.kuali.rice.krms.api.repository.type.KrmsAttributeDefinition;
import org.kuali.rice.krms.api.repository.type.KrmsTypeAttribute;
import org.kuali.rice.krms.framework.engine.Agenda;
import org.kuali.rice.krms.framework.engine.AgendaTree;
import org.kuali.rice.krms.framework.engine.BasicAgenda;
import org.kuali.rice.krms.impl.provider.repository.LazyAgendaTree;
import org.kuali.rice.krms.impl.provider.repository.RepositoryToEngineTranslator;
import org.kuali.rice.krms.impl.type.AgendaTypeServiceBase;
import org.kuali.rice.krms.impl.util.KrmsServiceLocatorInternal;

import java.util.HashMap;
import java.util.Map;

public class UnitAgendaTypeService extends AgendaTypeServiceBase  {

    public static final String UNIT_AGENDA_TYPE_ID = "KC1000";
    public static final String QUESTIONNAIRE_AGENDA_TYPE_ID = "KC1004";
    
    @Override
    public RemotableAttributeField translateTypeAttribute(KrmsTypeAttribute inputAttribute,
            KrmsAttributeDefinition attributeDefinition) {

        if (KcKrmsConstants.UNIT_NUMBER.equals(attributeDefinition.getName())) {
            return createUnitField();
        } else {
            return super.translateTypeAttribute(inputAttribute,
                attributeDefinition);
        }
    }

    private RemotableAttributeField createUnitField() {

        //String campusBoClassName = CampusBo.class.getName();

        //String baseLookupUrl = LookupInquiryUtils.getBaseLookupUrl();

//        RemotableQuickFinder.Builder quickFinderBuilder =
//                RemotableQuickFinder.Builder.create(baseLookupUrl, campusBoClassName);
//
//        quickFinderBuilder.setLookupParameters(Collections.singletonMap("Campus", "code"));
//        quickFinderBuilder.setFieldConversions(Collections.singletonMap("code","Campus"));

        RemotableTextInput.Builder controlBuilder = RemotableTextInput.Builder.create();
        controlBuilder.setSize(30);
        controlBuilder = RemotableTextInput.Builder.create();
        controlBuilder.setSize(Integer.valueOf(40));
        controlBuilder.setWatermark("unit number");

//        RemotableAttributeLookupSettings.Builder lookupSettingsBuilder = RemotableAttributeLookupSettings.Builder.create();
//        lookupSettingsBuilder.setCaseSensitive(Boolean.TRUE);
//        lookupSettingsBuilder.setInCriteria(true);
//        lookupSettingsBuilder.setInResults(true);
//        lookupSettingsBuilder.setRanged(false);

        RemotableAttributeField.Builder builder = RemotableAttributeField.Builder.create(KcKrmsConstants.UNIT_NUMBER);
        //builder.setAttributeLookupSettings(lookupSettingsBuilder);
        builder.setRequired(true);
        builder.setDataType(DataType.STRING);
        builder.setControl(controlBuilder);
        builder.setLongLabel("Unit Number");
        builder.setShortLabel("Unit Number");
        builder.setMinLength(Integer.valueOf(1));
        builder.setMaxLength(Integer.valueOf(40));
        //builder.setWidgets(Collections.<RemotableAbstractWidget.Builder>singletonList(quickFinderBuilder));

        return builder.build();
    }

//    @Override
//    public List<RemotableAttributeError> validateAttributes(@WebParam(name = "krmsTypeId") String krmsTypeId,
//            @WebParam(name = "attributes") @XmlJavaTypeAdapter(
//                    value = MapStringStringAdapter.class) Map<String, String> attributes) throws RiceIllegalArgumentException {
//
//        List<RemotableAttributeError> errors = new ArrayList<RemotableAttributeError>(super.validateAttributes(krmsTypeId, attributes));
//
//        RemotableAttributeError.Builder campusErrorBuilder = RemotableAttributeError.Builder.create(UNIT_FIELD_NAME);
//
//        String campusValue = attributes.get(UNIT_FIELD_NAME);
//        
//        // Replace with a service to verify the unit number is valid
//
//        if (StringUtils.isEmpty(campusValue)) {
//            campusErrorBuilder.addErrors(configurationService.getPropertyValueAsString("error.agenda.invalidAttributeValue"));
//        } else {
//            Campus campus = LocationApiServiceLocator.getCampusService().getCampus(campusValue);
//
//            if (campus == null) {
//                campusErrorBuilder.addErrors(configurationService.getPropertyValueAsString("error.agenda.invalidAttributeValue"));
//            }
//        }
//
//        if (campusErrorBuilder.getErrors().size() > 0) {
//            errors.add(campusErrorBuilder.build());
//        }
//
//        return errors;
//    }
    
    @Override
    public Agenda loadAgenda(AgendaDefinition agendaDefinition) {

        if (agendaDefinition == null) { throw new RiceIllegalArgumentException("agendaDefinition must not be null"); }
        RepositoryToEngineTranslator repositoryToEngineTranslator = KrmsServiceLocatorInternal.getRepositoryToEngineTranslator();
        if (repositoryToEngineTranslator == null) {
            return null;
        }
        
        return new UnitAgenda(agendaDefinition.getAttributes(), new LazyAgendaTree(agendaDefinition, repositoryToEngineTranslator), 
                                            agendaDefinition.getTypeId(),agendaDefinition.isActive());
    }
    
    private static class UnitAgenda extends BasicAgenda {
        
        private Map<String, String> qualifiers;
        private boolean isActive;
        
        public UnitAgenda(Map<String, String> qualifiers, AgendaTree agendaTree, String agendaTypeId,boolean isActive) {
            super(qualifiers, agendaTree);
            this.isActive = isActive;
            this.qualifiers = new HashMap<String, String>(qualifiers);
            this.qualifiers.put("typeId", agendaTypeId);
        }
        
        @Override
        public boolean appliesTo(ExecutionEnvironment environment) {
            if(!isActive){
                return false;
            }
            for (Map.Entry<String, String> agendaQualifier : environment.getSelectionCriteria().getAgendaQualifiers().entrySet()) {
                String agendaQualifierValue = qualifiers.get(agendaQualifier.getKey());
                String environmentQualifierValue = agendaQualifier.getValue();
                
               if (KcKrmsConstants.UNIT_NUMBER.equals(agendaQualifier.getKey())) {
                    if (!(environmentQualifierValue.equals(agendaQualifierValue) || isChildUnit(environmentQualifierValue, agendaQualifierValue))) {
                        return false;
                    }
                } else if (!environmentQualifierValue.equals(agendaQualifierValue)) {
                    return false;
                }
            }
            return true;
        }
        
        private boolean isChildUnit(String childNumber, String parentNumber) {
            UnitService unitService = KraServiceLocator.getService(UnitService.class);
            Unit childUnit = unitService.getUnit(childNumber);
            Unit parentUnit = unitService.getUnit(parentNumber);
            return childUnit == null || parentUnit == null ? false : childUnit.isParentUnit(parentUnit);
        }

    }
}
