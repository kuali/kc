/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.common.impl.unit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
import org.kuali.rice.core.api.uif.RemotableAbstractWidget;
import org.kuali.rice.core.api.uif.RemotableAttributeError;
import org.kuali.rice.core.api.uif.RemotableQuickFinder;
import org.kuali.rice.kim.api.type.KimAttributeField;
import org.kuali.rice.kns.kim.role.RoleTypeServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("unitRoleTypeService")
public class UnitRoleTypeServiceImpl extends RoleTypeServiceBase {

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService kualiConfigurationService;

    @Override
    public List<RemotableAttributeError> validateAttributes(String kimTypeId, Map<String, String> attributes) { 
        List<RemotableAttributeError> validationErrors = new ArrayList<RemotableAttributeError>();
        if(roleQualifiedByProposalKey(attributes) || 
                roleQualifiedByProtocolKey(attributes) || roleQualifiedByCommitteeKey(attributes) || 
                roleQualifiedByAwardKey(attributes) || roleQualifiedByAwardKey(attributes)) {
            return validationErrors;
        } else if(roleQualifiedByUnitOnly(attributes)) {
            validationErrors = super.validateAttributes(kimTypeId, attributes);
        }
        
        return validationErrors;
    }
    
    @Override
    public boolean performMatch(Map<String,String> qualification, Map<String,String> roleQualifier) {
        if(roleQualifiedByProposalKey(roleQualifier)) {
            return qualification.containsKey(KcKimAttributes.PROPOSAL) && StringUtils.equals(qualification.get(KcKimAttributes.PROPOSAL), 
                    roleQualifier.get(KcKimAttributes.PROPOSAL));
        } else if(roleQualifiedByProtocolKey(roleQualifier)) {
            return qualification.containsKey(KcKimAttributes.PROTOCOL) && StringUtils.equals(qualification.get(KcKimAttributes.PROTOCOL), 
                    roleQualifier.get(KcKimAttributes.PROTOCOL));
        } else if(roleQualifiedByCommitteeKey(roleQualifier)) {
            return qualification.containsKey(KcKimAttributes.COMMITTEE) && StringUtils.equals(qualification.get(KcKimAttributes.COMMITTEE), 
                    roleQualifier.get(KcKimAttributes.COMMITTEE));
        } else if(roleQualifiedByAwardKey(roleQualifier)) {
            return qualification.containsKey(KcKimAttributes.AWARD) && StringUtils.equals(qualification.get(KcKimAttributes.AWARD), 
                    roleQualifier.get(KcKimAttributes.AWARD));
        } else if(roleQualifiedByTimeAndMoneyKey(roleQualifier)) {
            return qualification.containsKey(KcKimAttributes.TIMEANDMONEY) && StringUtils.equals(qualification.get(KcKimAttributes.TIMEANDMONEY), 
                    roleQualifier.get(KcKimAttributes.TIMEANDMONEY));
        } else if(roleQualifiedByUnitOnly(roleQualifier)) {
            return (qualification.containsKey(KcKimAttributes.UNIT_NUMBER) &&  StringUtils.equals(qualification.get(KcKimAttributes.UNIT_NUMBER), 
                    roleQualifier.get(KcKimAttributes.UNIT_NUMBER)) || performWildCardMatching(qualification, roleQualifier));
        } 
        
        return false; 
    }

    protected boolean roleQualifiedByProposalKey(Map<String,String> roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.PROPOSAL);
    }
    
    protected boolean roleQualifiedByProtocolKey(Map<String,String> roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.PROTOCOL);
    }
    
    protected boolean roleQualifiedByCommitteeKey(Map<String,String> roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.COMMITTEE);
    }
    
    protected boolean roleQualifiedByAwardKey(Map<String,String> roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.AWARD);
    }
    
    protected boolean roleQualifiedByTimeAndMoneyKey(Map<String,String> roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.TIMEANDMONEY);
    }
    
    protected boolean roleQualifiedByUnitOnly(Map<String,String> roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.UNIT_NUMBER) && !roleQualifier.containsKey(KcKimAttributes.SUBUNITS);
    }
    
    protected boolean performWildCardMatching(Map<String,String> qualification, Map<String,String> roleQualifier) {
        if(qualification.containsKey(KcKimAttributes.UNIT_NUMBER) && qualification.get(KcKimAttributes.UNIT_NUMBER).equalsIgnoreCase("*") && roleQualifier.containsKey(KcKimAttributes.UNIT_NUMBER) ) {
            return true;
        }
        //If necessary, we can include logic for other pattern matching later.
        //Not found necessary at this time.
        return false;
    }

    
    @Override
    public List<String> getQualifiersForExactMatch() {
        List<String> attributes = new ArrayList<String>();
        attributes.add(KcKimAttributes.PROPOSAL);
        attributes.add(KcKimAttributes.PROTOCOL);
        attributes.add(KcKimAttributes.COMMITTEE);
        attributes.add(KcKimAttributes.AWARD);
        attributes.add(KcKimAttributes.TIMEANDMONEY);
        attributes.add(KcKimAttributes.UNIT_NUMBER);
        return attributes; 
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

                String baseUrl = kualiConfigurationService.getPropertyValueAsString("application.lookup.url");
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

    protected ConfigurationService getKualiConfigurationService() {
        return kualiConfigurationService;
    }

    public void setKualiConfigurationService(ConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }
}
