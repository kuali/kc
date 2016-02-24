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
package org.kuali.coeus.common.impl.krms;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.rice.core.api.data.DataType;
import org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
import org.kuali.rice.core.api.uif.RemotableAttributeField;
import org.kuali.rice.core.api.uif.RemotableRadioButtonGroup;
import org.kuali.rice.core.api.uif.RemotableTextInput;
import org.kuali.rice.core.api.uif.RemotableTextarea;
import org.kuali.rice.krms.api.repository.action.ActionDefinition;
import org.kuali.rice.krms.api.repository.type.KrmsAttributeDefinition;
import org.kuali.rice.krms.api.repository.type.KrmsTypeAttribute;
import org.kuali.rice.krms.api.repository.type.KrmsTypeDefinition;
import org.kuali.rice.krms.api.repository.type.KrmsTypeRepositoryService;
import org.kuali.rice.krms.framework.engine.Action;
import org.kuali.rice.krms.framework.type.ActionTypeService;
import org.kuali.rice.krms.framework.type.ValidationActionType;
import org.kuali.rice.krms.framework.type.ValidationActionTypeService;
import org.kuali.rice.krms.impl.type.KrmsTypeServiceBase;
import org.kuali.rice.krms.impl.validation.RadioButtonTypeServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component("kcValidationActionTypeService")
@Lazy
public class KcValidationActionTypeServiceImpl extends KrmsTypeServiceBase implements ActionTypeService {

    @Autowired
    @Qualifier("krmsTypeRepositoryService")
    private KrmsTypeRepositoryService krmsTypeRepositoryService;

    @Override
    public Action loadAction(ActionDefinition validationActionDefinition) {

        if (validationActionDefinition == null) { throw new RiceIllegalArgumentException("validationActionDefinition must not be null"); }
        if (validationActionDefinition.getAttributes() == null) { throw new RiceIllegalArgumentException("validationActionDefinition must not be null");}

        // TypeCode
        if (!validationActionDefinition.getAttributes().containsKey(ValidationActionTypeService.VALIDATIONS_ACTION_TYPE_CODE_ATTRIBUTE)) {
            throw new RiceIllegalArgumentException("validationActionDefinition does not contain an " +
                    ValidationActionTypeService.VALIDATIONS_ACTION_TYPE_CODE_ATTRIBUTE + " attribute");
        }
        String validationActionTypeCode = validationActionDefinition.getAttributes().get(ValidationActionTypeService.VALIDATIONS_ACTION_TYPE_CODE_ATTRIBUTE);
        if (StringUtils.isBlank(validationActionTypeCode)) {
            throw new RiceIllegalArgumentException(ValidationActionTypeService.VALIDATIONS_ACTION_TYPE_CODE_ATTRIBUTE + " attribute must not be null or blank");
        }

        // Message
        if (!validationActionDefinition.getAttributes().containsKey(ValidationActionTypeService.VALIDATIONS_ACTION_MESSAGE_ATTRIBUTE)) {
            throw new RiceIllegalArgumentException("validationActionDefinition does not contain an " +
                    ValidationActionTypeService.VALIDATIONS_ACTION_MESSAGE_ATTRIBUTE + " attribute");
        }
        String validationMessage = validationActionDefinition.getAttributes().get(ValidationActionTypeService.VALIDATIONS_ACTION_MESSAGE_ATTRIBUTE);
        if (StringUtils.isBlank(validationMessage)) {
            throw new RiceIllegalArgumentException(ValidationActionTypeService.VALIDATIONS_ACTION_MESSAGE_ATTRIBUTE + " attribute must not be null or blank");
        }

        //AreaName
        if (!validationActionDefinition.getAttributes().containsKey(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_AREA_ATTRIBUTE)) {
            throw new RiceIllegalArgumentException("validationActionDefinition does not contain an " +
                    KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_AREA_ATTRIBUTE + " attribute");
        }
        String validationActionArea = validationActionDefinition.getAttributes().get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_AREA_ATTRIBUTE);
        if (StringUtils.isBlank(validationActionArea)) {
            throw new RiceIllegalArgumentException(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_AREA_ATTRIBUTE + " attribute must not be null or blank");
        }
        //SectionName
        if (!validationActionDefinition.getAttributes().containsKey(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_SECTION_ATTRIBUTE)) {
            throw new RiceIllegalArgumentException("validationActionDefinition does not contain an " +
                    KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_SECTION_ATTRIBUTE + " attribute");
        }
        String validationActionSection = validationActionDefinition.getAttributes().get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_SECTION_ATTRIBUTE);
        //NavigateToPageId
        if (!validationActionDefinition.getAttributes().containsKey(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_PAGE_ID_ATTRIBUTE)) {
            throw new RiceIllegalArgumentException("validationActionDefinition does not contain an " +
                    KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_PAGE_ID_ATTRIBUTE + " attribute");
        }
        String validationActionNavigateToPageId = validationActionDefinition.getAttributes().get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_PAGE_ID_ATTRIBUTE);
        if (StringUtils.isBlank(validationActionNavigateToPageId)) {
            throw new RiceIllegalArgumentException(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_PAGE_ID_ATTRIBUTE + " attribute must not be null or blank");
        }
        //NavigateToSectionId
        if (!validationActionDefinition.getAttributes().containsKey(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_SECTION_ID_ATTRIBUTE)) {
            throw new RiceIllegalArgumentException("validationActionDefinition does not contain an " +
                    KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_SECTION_ID_ATTRIBUTE + " attribute");
        }
        String validationActionNavigateToSectionId = validationActionDefinition.getAttributes().get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_SECTION_ID_ATTRIBUTE);
        if (StringUtils.isBlank(validationActionNavigateToSectionId)) {
            throw new RiceIllegalArgumentException(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_SECTION_ID_ATTRIBUTE + " attribute must not be null or blank");
        }

        if (ValidationActionType.WARNING.getCode().equals(validationActionTypeCode)) {
            return new KcValidationAction(ValidationActionType.WARNING,validationActionArea,validationActionSection,validationActionNavigateToPageId,validationActionNavigateToSectionId, validationMessage);
        }
        if (ValidationActionType.ERROR.getCode().equals(validationActionTypeCode)) {
            return new KcValidationAction(ValidationActionType.ERROR, validationActionArea,validationActionSection,validationActionNavigateToPageId,validationActionNavigateToSectionId, validationMessage);
        }
        return null;
    }

    @Override
    public List<RemotableAttributeField> getAttributeFields(@WebParam(name = "krmsTypeId") String krmsTypeId) throws RiceIllegalArgumentException {

        List<RemotableAttributeField> results = new ArrayList<RemotableAttributeField>();

        KrmsTypeDefinition krmsType = getKrmsTypeRepositoryService().getTypeById(krmsTypeId);

        if (krmsType == null) {
            throw new RiceIllegalArgumentException("krmsTypeId must be a valid id of a KRMS type");
        } else {
            List<KrmsTypeAttribute> typeAttributes = krmsType.getAttributes();
            Map<String, Integer> attribDefIdSequenceNumbers = new TreeMap<String, Integer>();
            Map<String, String> unsortedIdLables = new TreeMap<String, String>();
            if (!CollectionUtils.isEmpty(typeAttributes)) {
                // translate the attribute and store the sort code in our map
                Map<String, String> keyLabels = new TreeMap<String, String>();
                keyLabels.put(ValidationActionType.WARNING.getCode(), "Warning Action");
                keyLabels.put(ValidationActionType.ERROR.getCode(), "Error Action");

                KrmsAttributeDefinition attributeDefinition = null;
                RadioButtonTypeServiceUtil util = new RadioButtonTypeServiceUtil();

                for (KrmsTypeAttribute typeAttribute : typeAttributes) {

                    attributeDefinition = getKrmsTypeRepositoryService().getAttributeDefinitionById(typeAttribute.getAttributeDefinitionId());

                    if (ValidationActionTypeService.VALIDATIONS_ACTION_TYPE_CODE_ATTRIBUTE.equals(attributeDefinition.getName())) {
                        RemotableAttributeField attributeField = translateTypeAttribute(attributeDefinition, keyLabels);
                        results.add(attributeField);
                    }

                    if (ValidationActionTypeService.VALIDATIONS_ACTION_MESSAGE_ATTRIBUTE.equals(attributeDefinition.getName())) {
                        RemotableAttributeField attributeField = createMessageField(attributeDefinition);
                        results.add(attributeField);
                    }

                    if (KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_AREA_ATTRIBUTE.equals(attributeDefinition.getName())
                            || KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_SECTION_ATTRIBUTE.equals(attributeDefinition.getName())
                            || KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_PAGE_ID_ATTRIBUTE.equals(attributeDefinition.getName())
                            || KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_SECTION_ID_ATTRIBUTE.equals(attributeDefinition.getName()))    {
                        RemotableAttributeField attributeField = createTextField(attributeDefinition);
                        results.add(attributeField);
                    }
                }
            }
        }
        return results;
    }

    private RemotableAttributeField createTextField(KrmsAttributeDefinition krmsAttributeDefinition) {

        RemotableTextInput.Builder controlBuilder = RemotableTextInput.Builder.create();
        controlBuilder.setSize(100);
        controlBuilder.setWatermark("");

        RemotableAttributeField.Builder builder = RemotableAttributeField.Builder.create(krmsAttributeDefinition.getName());
        if (KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_SECTION_ATTRIBUTE.equals(krmsAttributeDefinition.getName())) {
            builder.setRequired(false);
        } else {
            builder.setRequired(true);
        }
        builder.setDataType(DataType.STRING);
        builder.setControl(controlBuilder);
        builder.setLongLabel(krmsAttributeDefinition.getLabel());
        builder.setShortLabel(krmsAttributeDefinition.getLabel());

        return builder.build();
    }

    private RemotableAttributeField createMessageField(KrmsAttributeDefinition krmsAttributeDefinition) {

        RemotableTextarea.Builder controlBuilder = RemotableTextarea.Builder.create();
        controlBuilder = RemotableTextarea.Builder.create();
        controlBuilder.setRows(Integer.valueOf(2));
        controlBuilder.setCols(Integer.valueOf(30));
        controlBuilder.setWatermark("Enter a Validation Action Message");

        RemotableAttributeField.Builder builder = RemotableAttributeField.Builder.create(krmsAttributeDefinition.getName());
        builder.setRequired(true);
        builder.setDataType(DataType.STRING);
        builder.setControl(controlBuilder);
        builder.setLongLabel(krmsAttributeDefinition.getLabel());
        builder.setShortLabel("Message");
        builder.setMinLength(Integer.valueOf(1));
        builder.setMaxLength(Integer.valueOf(400));

        return builder.build();
    }

    RemotableAttributeField translateTypeAttribute(KrmsAttributeDefinition krmsAttributeDefinition, Map<String, String> valueLabels) {

        RemotableAttributeField.Builder builder = RemotableAttributeField.Builder.create(krmsAttributeDefinition.getName());

        RemotableRadioButtonGroup.Builder controlBuilder = RemotableRadioButtonGroup.Builder.create(valueLabels);

        builder.setLongLabel(krmsAttributeDefinition.getLabel());
        builder.setName(krmsAttributeDefinition.getName());
        builder.setRequired(true);
        List<String> defaultValue = new ArrayList<String>();
        defaultValue.add((String) valueLabels.keySet().toArray()[0]);
        builder.setDefaultValues(defaultValue);
        builder.setControl(controlBuilder);

        return builder.build();
    }

    public KrmsTypeRepositoryService getKrmsTypeRepositoryService() {
        return krmsTypeRepositoryService;
    }

    public void setKrmsTypeRepositoryService(KrmsTypeRepositoryService krmsTypeRepositoryService) {
        this.krmsTypeRepositoryService = krmsTypeRepositoryService;
    }
}
