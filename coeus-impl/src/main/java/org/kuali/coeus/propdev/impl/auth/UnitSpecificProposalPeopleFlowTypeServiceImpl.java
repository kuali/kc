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
package org.kuali.coeus.propdev.impl.auth;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.workflow.KcAttributeCapablePeopleFlowTypeService;
import org.kuali.coeus.common.impl.workflow.KcPeopleFlowTypeServiceImpl;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.rice.core.api.data.DataType;
import org.kuali.rice.core.api.uif.RemotableAbstractWidget;
import org.kuali.rice.core.api.uif.RemotableAttributeField;
import org.kuali.rice.core.api.uif.RemotableAttributeLookupSettings;
import org.kuali.rice.core.api.uif.RemotableQuickFinder;
import org.kuali.rice.core.api.uif.RemotableTextInput;
import org.kuali.rice.kew.api.document.Document;
import org.kuali.rice.kew.api.document.DocumentContent;
import org.kuali.rice.kew.api.peopleflow.PeopleFlowDefinition;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.lookup.LookupUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("unitSpecificProposalPeopleFlowTypeService")
public class UnitSpecificProposalPeopleFlowTypeServiceImpl extends KcPeopleFlowTypeServiceImpl implements KcAttributeCapablePeopleFlowTypeService {
	
    public static final String UNIT_NUMBER_LOWER = "unit number";
    
	@Autowired
	@Qualifier("dataObjectService")
	private DataObjectService dataObjectService;
	
    @Override
    public Map<String, String> resolveRoleQualifiers(PeopleFlowDefinition peopleflow, String roleId, Document document,
            DocumentContent documentContent) {
    	return Collections.singletonMap(KcKimAttributes.UNIT_NUMBER, peopleflow.getAttributes().get(KcKimAttributes.UNIT_NUMBER));
    }
    
    @Override
    public List<RemotableAttributeField> getAttributeFields(String kewTypeId) {
    	String unitClassName = Unit.class.getName();
    	String baseLookupUrl = LookupUtils.getBaseLookupUrl();
    
        RemotableQuickFinder.Builder quickFinderBuilder = RemotableQuickFinder.Builder.create(baseLookupUrl, unitClassName);
        quickFinderBuilder.setLookupParameters(Collections.singletonMap(KcKimAttributes.UNIT_NUMBER, KcKimAttributes.UNIT_NUMBER));
        quickFinderBuilder.setFieldConversions(Collections.singletonMap(KcKimAttributes.UNIT_NUMBER, KcKimAttributes.UNIT_NUMBER));

        RemotableTextInput.Builder controlBuilder = RemotableTextInput.Builder.create();
        controlBuilder.setSize(30);
        controlBuilder = RemotableTextInput.Builder.create();
        controlBuilder.setSize(Integer.valueOf(40));
        controlBuilder.setWatermark(UNIT_NUMBER_LOWER);
        
        RemotableAttributeLookupSettings.Builder lookupSettingsBuilder = RemotableAttributeLookupSettings.Builder.create();
        lookupSettingsBuilder.setCaseSensitive(Boolean.TRUE);
        lookupSettingsBuilder.setInCriteria(true);
        lookupSettingsBuilder.setInResults(true);
        lookupSettingsBuilder.setRanged(false);

        RemotableAttributeField.Builder builder = RemotableAttributeField.Builder.create(KcKimAttributes.UNIT_NUMBER);
        builder.setAttributeLookupSettings(lookupSettingsBuilder);
        builder.setName(KcKimAttributes.UNIT_NUMBER);
        builder.setRequired(true);
        builder.setDataType(DataType.STRING);
        builder.setControl(controlBuilder);
        builder.setLongLabel(KcKrmsConstants.UNIT_NUMBER);
        builder.setShortLabel(KcKrmsConstants.UNIT_NUMBER);
        builder.setMinLength(Integer.valueOf(1));
        builder.setMaxLength(Integer.valueOf(40));
        builder.setWidgets(Collections.<RemotableAbstractWidget.Builder> singletonList(quickFinderBuilder));

        return Collections.singletonList(builder.build());
    }

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
	
}
