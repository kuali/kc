package org.kuali.coeus.propdev.impl.custom;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.arg.ArgValueLookup;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.impl.custom.arg.ArgValueLookupValuesFinder;
import org.kuali.kra.web.krad.KcBindingInfo;
import org.kuali.rice.kim.impl.identity.principal.PrincipalBo;
import org.kuali.rice.krad.uif.control.Control;
import org.kuali.rice.krad.uif.control.TextControlBase;
import org.kuali.rice.krad.uif.field.InputFieldBase;
import org.kuali.rice.krad.uif.util.ComponentFactory;
import org.kuali.rice.krad.uif.util.LifecycleElement;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;


public class ProposalDevelopmentCustomDataField extends InputFieldBase {

    @Override
    public void performApplyModel(Object model, LifecycleElement parent) {
        Object myBo = ObjectPropertyUtils.getPropertyValue(model, KcBindingInfo.getParentBindingInfo(getBindingInfo()));
        CustomAttributeDocValue customData = (CustomAttributeDocValue) myBo;

        if (StringUtils.isNotBlank(customData.getCustomAttribute().getLookupClass())) {
            if (customData.getCustomAttribute().getLookupClass().equals(ArgValueLookup.class.getName())) {
                setControl((Control) ComponentFactory.getNewComponentInstance("Uif-DropdownControl"));
                ArgValueLookupValuesFinder valuesFinder = new ArgValueLookupValuesFinder();
                valuesFinder.setArgName(customData.getCustomAttribute().getLookupReturn());
                valuesFinder.setAddBlankOption(false);
                setOptionsFinder(valuesFinder);
            } else {
                if (customData.getCustomAttribute().getLookupClass().equals(KcPerson.class.getName())) {
                    if (customData.getCustomAttribute().getLookupReturn().equals("userName")) {
                        getSuggest().getSuggestQuery().setDataObjectClassName(PrincipalBo.class.getName());
                        getSuggest().setValuePropertyName("principalName");
                        getSuggest().setRender(true);
                    }
                } else {
                    getSuggest().setValuePropertyName(customData.getCustomAttribute().getLookupReturn());
                    getSuggest().getSuggestQuery().setDataObjectClassName(customData.getCustomAttribute().getLookupClass());
                    getSuggest().setRender(true);
                }

                getQuickfinder().setRender(true);
                getQuickfinder().setDataObjectClassName(customData.getCustomAttribute().getLookupClass());
                getQuickfinder().getFieldConversions().put(customData.getCustomAttribute().getLookupReturn(), getPropertyName());
            }
        } else if (customData.getCustomAttribute().getDataTypeCode().equals("3")) {
            setControl((Control) ComponentFactory.getNewComponentInstance("Uif-DateControlOnFocus"));
            ((TextControlBase)getControl()).setWatermarkText("mm/dd/yyyy");
        }
        super.performApplyModel(model, parent);
    }
}
