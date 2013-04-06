/*
 * Copyright 2005-2013 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.kim.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
import org.kuali.rice.core.api.uif.RemotableAbstractWidget;
import org.kuali.rice.core.api.uif.RemotableAttributeError;
import org.kuali.rice.core.api.uif.RemotableQuickFinder;
import org.kuali.rice.kim.api.type.KimAttributeField;
import org.kuali.rice.kns.kim.role.RoleTypeServiceBase;
import org.kuali.rice.krad.service.KRADServiceLocator;

public class UnitHierarchyRoleTypeServiceImpl extends RoleTypeServiceBase {
    
    private UnitService unitService;
    
    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
    
    @Override
    public boolean performMatch(Map<String,String> qualification, Map<String,String> roleQualifier) {        
        if (roleQualifiedByUnitHierarchy(roleQualifier) && qualification.containsKey(KcKimAttributes.UNIT_NUMBER)) {
            return (performWildCardMatching(qualification, roleQualifier) || unitQualifierMatches(qualification, roleQualifier) || unitQualifierMatchesHierarchy(qualification, roleQualifier));
        }
        
        return false; 
    }
    
    @Override
    public List<RemotableAttributeError> validateAttributes(String kimTypeId, Map<String, String> attributes) { 
        List<RemotableAttributeError> validationErrors = new ArrayList<RemotableAttributeError>();
        if(roleQualifiedByUnitHierarchy(attributes) && attributes.containsKey(KcKimAttributes.UNIT_NUMBER)) {
            validationErrors = super.validateAttributes(kimTypeId, attributes);
        }
        
        return validationErrors;
    }
    
    protected boolean roleQualifiedByUnitHierarchy(Map<String,String> roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.UNIT_NUMBER) && roleQualifier.containsKey(KcKimAttributes.SUBUNITS);
    }
    
    protected boolean unitQualifierMatches(Map<String,String> qualification, Map<String,String> roleQualifier) {
        return StringUtils.equals(qualification.get(KcKimAttributes.UNIT_NUMBER), roleQualifier.get(KcKimAttributes.UNIT_NUMBER));
    }
    
    protected boolean unitQualifierMatchesHierarchy(Map<String,String> qualification, Map<String,String> roleQualifier) {
        boolean qualifierMatches = false;
        String unitNumber = qualification.get(KcKimAttributes.UNIT_NUMBER);
        
        while (!qualifierMatches) {
            final Unit unit = this.unitService.getUnit(unitNumber);
            if (unit == null) {
                break;
            }
            final String parentUnitNumber = unit.getParentUnitNumber();
            if (parentUnitNumber == null) {
                break;
            }
            qualifierMatches = ( StringUtils.equals(parentUnitNumber, roleQualifier.get(KcKimAttributes.UNIT_NUMBER)) && descendsSubunits(roleQualifier));
            unitNumber = parentUnitNumber;
        }
        
        return qualifierMatches;
    }
    
    
    protected boolean descendsSubunits(Map<String,String> roleQualifier) {
        return StringUtils.equalsIgnoreCase("Y", roleQualifier.get(KcKimAttributes.SUBUNITS)) ||
                StringUtils.equalsIgnoreCase("Yes", roleQualifier.get(KcKimAttributes.SUBUNITS));
    }
    
    protected boolean performWildCardMatching(Map<String,String> qualification, Map<String,String> roleQualifier) {
        if(qualification.get(KcKimAttributes.UNIT_NUMBER).equalsIgnoreCase("*")) {
            return true;
        }
        //If necessary, we can include logic for other pattern matching later.
        //Not found necessary at this time.
        return false;
    }
    
    @Override
    public List<String> getUniqueAttributes(String kimTypeId){
        return new ArrayList<String>();
    }    
    
    @Override
    public List<KimAttributeField> getAttributeDefinitions(String kimTypeId) {
        if (StringUtils.isBlank(kimTypeId)) {
            throw new RiceIllegalArgumentException("kimTypeId was null or blank");
        }

        List<KimAttributeField> attributeList = new ArrayList<KimAttributeField>(super.getAttributeDefinitions(kimTypeId));

        for (int i = 0; i < attributeList.size(); i++) {
            final KimAttributeField definition = attributeList.get(i);
            if (KcKimAttributes.UNIT_NUMBER.equals(definition.getAttributeField().getName())) {
                KimAttributeField.Builder b = KimAttributeField.Builder.create(definition);

                String baseUrl = KRADServiceLocator.getKualiConfigurationService().getPropertyValueAsString("application.lookup.url");
                Collection<RemotableAbstractWidget.Builder> widgetsCopy = new ArrayList<RemotableAbstractWidget.Builder>();
                for (RemotableAbstractWidget.Builder widget : b.getAttributeField().getWidgets()) {
                    if(widget instanceof RemotableQuickFinder.Builder) {
                        RemotableQuickFinder.Builder orig = (RemotableQuickFinder.Builder) widget;
                        RemotableQuickFinder.Builder copy = RemotableQuickFinder.Builder.create(baseUrl, orig.getDataObjectClass());
                        copy.setLookupParameters(orig.getLookupParameters());
                        copy.setFieldConversions(orig.getFieldConversions());
                        widgetsCopy.add(copy);
                    } else {
                        widgetsCopy.add(widget);
                    }
                }
                b.getAttributeField().setWidgets(widgetsCopy);
                attributeList.set(i, b.build());
            }
        }
        return Collections.unmodifiableList(attributeList);
    }    

    /*
     * Should override if derivedRoles should not to be cached.  Currently defaults to system-wide default.
     */
    @Override
    public boolean dynamicRoleMembership(String namespaceCode, String roleName) {
        super.dynamicRoleMembership(namespaceCode, roleName);
        return true;
    }


}
