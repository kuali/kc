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
package org.kuali.coeus.common.impl.unit;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.rice.core.api.data.DataType;
import org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
import org.kuali.rice.core.api.uif.RemotableAbstractWidget;
import org.kuali.rice.core.api.uif.RemotableAttributeField;
import org.kuali.rice.core.api.uif.RemotableAttributeLookupSettings;
import org.kuali.rice.core.api.uif.RemotableQuickFinder;
import org.kuali.rice.core.api.uif.RemotableTextInput;
import org.kuali.rice.krad.lookup.LookupUtils;
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

import java.util.Collections;
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
    	
    	String unitClassName = Unit.class.getName();
    	String baseLookupUrl = LookupUtils.getBaseLookupUrl();
    
        RemotableQuickFinder.Builder quickFinderBuilder = RemotableQuickFinder.Builder.create(baseLookupUrl, unitClassName);
        quickFinderBuilder.setLookupParameters(Collections.singletonMap(KcKrmsConstants.UNIT_NUMBER, "unitNumber"));
        quickFinderBuilder.setFieldConversions(Collections.singletonMap("unitNumber",KcKrmsConstants.UNIT_NUMBER));

        RemotableTextInput.Builder controlBuilder = RemotableTextInput.Builder.create();
        controlBuilder.setSize(30);
        controlBuilder = RemotableTextInput.Builder.create();
        controlBuilder.setSize(Integer.valueOf(40));
        controlBuilder.setWatermark("unit number");
        
        RemotableAttributeLookupSettings.Builder lookupSettingsBuilder = RemotableAttributeLookupSettings.Builder.create();
        lookupSettingsBuilder.setCaseSensitive(Boolean.TRUE);
        lookupSettingsBuilder.setInCriteria(true);
        lookupSettingsBuilder.setInResults(true);
        lookupSettingsBuilder.setRanged(false);

        RemotableAttributeField.Builder builder = RemotableAttributeField.Builder.create(KcKrmsConstants.UNIT_NUMBER);
        builder.setAttributeLookupSettings(lookupSettingsBuilder);
        builder.setName(KcKrmsConstants.UNIT_NUMBER);
        builder.setRequired(true);
        builder.setDataType(DataType.STRING);
        builder.setControl(controlBuilder);
        builder.setLongLabel("Unit Number");
        builder.setShortLabel("Unit Number");
        builder.setMinLength(Integer.valueOf(1));
        builder.setMaxLength(Integer.valueOf(40));
        builder.setWidgets(Collections.<RemotableAbstractWidget.Builder> singletonList(quickFinderBuilder));

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
                            if (enviornmentUnitNumber.equals(agendaQualifierValue)){ 
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
