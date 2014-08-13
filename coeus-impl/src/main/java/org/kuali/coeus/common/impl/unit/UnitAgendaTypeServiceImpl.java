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
package org.kuali.coeus.common.impl.unit;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.rice.core.api.data.DataType;
import org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
import org.kuali.rice.core.api.uif.RemotableAttributeField;
import org.kuali.rice.core.api.uif.RemotableTextInput;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("unitAgendaTypeService")
public class UnitAgendaTypeServiceImpl extends AgendaTypeServiceBase  {

    @Autowired
    @Qualifier("unitService")
    private UnitService unitService;

    @Autowired
    @Qualifier("repositoryToEngineTranslator")
    private RepositoryToEngineTranslator repositoryToEngineTranslator;

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

        RemotableTextInput.Builder controlBuilder = RemotableTextInput.Builder.create();
        controlBuilder.setSize(30);
        controlBuilder = RemotableTextInput.Builder.create();
        controlBuilder.setSize(Integer.valueOf(40));
        controlBuilder.setWatermark("unit number");

        RemotableAttributeField.Builder builder = RemotableAttributeField.Builder.create(KcKrmsConstants.UNIT_NUMBER);
        builder.setRequired(true);
        builder.setDataType(DataType.STRING);
        builder.setControl(controlBuilder);
        builder.setLongLabel("Unit Number");
        builder.setShortLabel("Unit Number");
        builder.setMinLength(Integer.valueOf(1));
        builder.setMaxLength(Integer.valueOf(40));

        return builder.build();
    }
    
    @Override
    public Agenda loadAgenda(AgendaDefinition agendaDefinition) {

        if (agendaDefinition == null) { throw new RiceIllegalArgumentException("agendaDefinition must not be null"); }
        if (repositoryToEngineTranslator == null) {
            return null;
        }
        
        return new UnitAgenda(agendaDefinition.getAttributes(), new LazyAgendaTree(agendaDefinition, repositoryToEngineTranslator), 
                                            agendaDefinition.getTypeId(),agendaDefinition.isActive());
    }
    
    private class UnitAgenda extends BasicAgenda {
        
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
            boolean canApply = false;
            for (Map.Entry<String, String> agendaQualifier : environment.getSelectionCriteria().getAgendaQualifiers().entrySet()) {
                if(!canApply){
                    String agendaQualifierValue = qualifiers.get(agendaQualifier.getKey());
                    String environmentQualifierValue = agendaQualifier.getValue();
                    
                   if (KcKrmsConstants.UNIT_NUMBER.equals(agendaQualifier.getKey())) {
                        String[] unitNumbers = environmentQualifierValue.split(",");
                        for (int i = 0; i < unitNumbers.length; i++) {
                            String enviornmentUnitNumber = unitNumbers[i];
                            if ((enviornmentUnitNumber.equals(agendaQualifierValue) || isChildUnit(environmentQualifierValue, agendaQualifierValue))) {
                                canApply = true;
                                break;
                            }
                        }
                    } else if (!environmentQualifierValue.equals(agendaQualifierValue)) {
                        canApply = false;
                    }
                }
            }
            return canApply;
        }
        
        private boolean isChildUnit(String childNumber, String parentNumber) {
            Unit childUnit = unitService.getUnit(childNumber);
            Unit parentUnit = unitService.getUnit(parentNumber);
            return childUnit == null || parentUnit == null ? false : childUnit.isParentUnit(parentUnit);
        }

    }

    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

    public RepositoryToEngineTranslator getRepositoryToEngineTranslator() {
        return repositoryToEngineTranslator;
    }

    public void setRepositoryToEngineTranslator(RepositoryToEngineTranslator repositoryToEngineTranslator) {
        this.repositoryToEngineTranslator = repositoryToEngineTranslator;
    }
}
