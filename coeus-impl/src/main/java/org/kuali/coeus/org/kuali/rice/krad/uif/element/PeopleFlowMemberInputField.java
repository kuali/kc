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
package org.kuali.coeus.org.kuali.rice.krad.uif.element;


import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.web.krad.KcBindingInfo;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kew.impl.peopleflow.PeopleFlowDelegateBo;
import org.kuali.rice.kew.impl.peopleflow.PeopleFlowMemberBo;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.impl.group.GroupBo;
import org.kuali.rice.kim.impl.role.RoleBo;
import org.kuali.rice.krad.uif.CssConstants;
import org.kuali.rice.krad.uif.control.TextControl;
import org.kuali.rice.krad.uif.control.UserControl;
import org.kuali.rice.krad.uif.field.InputFieldBase;
import org.kuali.rice.krad.uif.util.ComponentFactory;
import org.kuali.rice.krad.uif.util.LifecycleElement;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.uif.widget.QuickFinder;

import java.util.ArrayList;
import java.util.Collections;

public class PeopleFlowMemberInputField extends InputFieldBase {

    public static final String PERSON_NAME = "person.name";
    public static final String NAME = "Name";

    @Override
    public void performApplyModel(Object model, LifecycleElement parent) {
        String memberTypeCode = getMemberTypeCodeFromModel(model);
        if (StringUtils.equals(memberTypeCode, MemberType.PRINCIPAL.getCode())) {
            setUserControl();
        } else {
            setTextControl();
            setQuickFinder(memberTypeCode);
        }
        super.performApplyModel(model, parent);
    }

    protected String getMemberTypeCodeFromModel(Object model) {
        Object bo = ObjectPropertyUtils.getPropertyValue(model, KcBindingInfo.getParentBindingInfo(getBindingInfo()));
        if (bo instanceof PeopleFlowMemberBo) {
           return ((PeopleFlowMemberBo)bo).getMemberTypeCode();
        } else if (bo instanceof PeopleFlowDelegateBo) {
            return ((PeopleFlowDelegateBo)bo).getMemberTypeCode();
        }
        return null;
    }

    protected void setQuickFinder(String memberTypeCode) {
        QuickFinder quickFinder = ComponentFactory.getQuickFinder();
        quickFinder.setFieldConversions(Collections.singletonMap(KimConstants.PrimaryKeyConstants.ID, KimConstants.PrimaryKeyConstants.MEMBER_ID));
        setQuickfinder(quickFinder);
        if (StringUtils.equals(memberTypeCode, MemberType.ROLE.getCode())) {
            getQuickfinder().setDataObjectClassName(RoleBo.class.getName());
        } else if (StringUtils.equals(memberTypeCode, MemberType.GROUP.getCode())) {
            getQuickfinder().setDataObjectClassName(GroupBo.class.getName());
        }
    }

    protected void setTextControl() {
        TextControl textControl = ComponentFactory.getTextControl();
        textControl.setSize(40);
        textControl.setAdditionalCssClasses(new ArrayList<String>(){{add(CssConstants.Classes.IGNORE_VALID);}});
        setControl(textControl);
    }

    protected void setUserControl() {
        UserControl userControl = ComponentFactory.getUserControl();
        userControl.setPrincipalIdPropertyName(KimConstants.PrimaryKeyConstants.MEMBER_ID);
        userControl.setPersonNamePropertyName(PERSON_NAME);
        userControl.setWatermarkText(NAME);
        userControl.setAdditionalCssClasses(new ArrayList<String>(){{add(CssConstants.Classes.IGNORE_VALID);}});
        setControl(userControl);
    }
}
