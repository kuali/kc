/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.Map;

import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
import org.kuali.rice.core.api.uif.DataType;
import org.kuali.rice.core.api.uif.RemotableAttributeField;
import org.kuali.rice.core.api.uif.RemotableTextInput;
import org.kuali.rice.krms.api.engine.ExecutionEnvironment;
import org.kuali.rice.krms.api.repository.agenda.AgendaDefinition;
import org.kuali.rice.krms.api.repository.type.KrmsAttributeDefinition;
import org.kuali.rice.krms.api.repository.type.KrmsTypeAttribute;
import org.kuali.rice.krms.framework.engine.Agenda;
import org.kuali.rice.krms.framework.engine.AgendaTree;
import org.kuali.rice.krms.framework.engine.BasicAgenda;
import org.kuali.rice.krms.impl.provider.repository.RepositoryToEngineTranslatorImpl;
import org.kuali.rice.krms.impl.type.AgendaTypeServiceBase;
import org.kuali.rice.krms.impl.util.KRMSServiceLocatorInternal;

public class UnitAgendaTypeService extends AgendaTypeServiceBase  {
    
    private static final String UNIT_FIELD_NAME = "unitNumber";

    @Override
    public RemotableAttributeField translateTypeAttribute(KrmsTypeAttribute inputAttribute,
            KrmsAttributeDefinition attributeDefinition) {

        if (UNIT_FIELD_NAME.equals(attributeDefinition.getName())) {
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

        RemotableAttributeField.Builder builder = RemotableAttributeField.Builder.create(UNIT_FIELD_NAME);
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
        RepositoryToEngineTranslatorImpl repositoryToEngineTranslator = KRMSServiceLocatorInternal.getService(
                "repositoryToEngineTranslator");
        if (repositoryToEngineTranslator == null) {
            return null;
        }
        
        Agenda agenda = repositoryToEngineTranslator.translateAgendaDefinition(agendaDefinition);
        
        return agenda;
    }
    
    
    
    private static class UnitAgenda extends BasicAgenda {
        
        private Map<String, String> qualifiers;
    
        public UnitAgenda(Map<String, String> qualifiers, AgendaTree agendaTree) {
            super(qualifiers, agendaTree);
            this.qualifiers = qualifiers;
        }
        
        @Override
        public boolean appliesTo(ExecutionEnvironment environment) {
            for (Map.Entry<String, String> agendaQualifier : environment.getSelectionCriteria().getAgendaQualifiers().entrySet()) {
                String agendaQualifierValue = qualifiers.get(agendaQualifier.getKey());
                String environmentQualifierValue = agendaQualifier.getValue();
                if (UNIT_FIELD_NAME.equals(agendaQualifier.getKey())) {
                    // environmentQualifierValue will equal the unit number passed in when the engine was invoked.
                    // applies to should return true if:
                    // environmentQualifierValue = agendaQualifier.getValue, OR
                    // agendaQualifier.getValue is a parent of environmentQualifierValue
                    if (!environmentQualifierValue.equals(agendaQualifier.getValue())
                            && !isChildUnit(environmentQualifierValue, agendaQualifier.getValue())) {
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
            return childUnit.isParentUnit(parentUnit);
        }
        
    }

}
