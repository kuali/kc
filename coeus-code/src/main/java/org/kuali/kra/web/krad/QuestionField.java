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
package org.kuali.kra.web.krad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.component.DataBinding;
import org.kuali.rice.krad.uif.control.Control;
import org.kuali.rice.krad.uif.control.TextControl;
import org.kuali.rice.krad.uif.field.FieldGroup;
import org.kuali.rice.krad.uif.field.InputField;
import org.kuali.rice.krad.uif.util.ComponentFactory;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.uif.widget.QuickFinder;
import org.kuali.rice.krad.uif.widget.Suggest;

public class QuestionField extends InputField {

    private static final long serialVersionUID = -940684402907403282L;
    private Map<String, String> controlMappings;
    private boolean useSuggest;
    private String bindingInfo;

    public QuestionField() {
        controlMappings = new HashMap<String, String>();
    }
    
    @Override
    public void performApplyModel(Object model, Component parent) {
        Object myBo = ObjectPropertyUtils.getPropertyValue(model, KcBindingInfo.getParentBindingInfo(getBindingInfo()));
        Answer answer = (Answer) myBo;
        setControl((Control) ComponentFactory.getNewComponentInstance(getControlMappings().get(answer.getQuestion().getQuestionTypeId().toString())));
        getControl().getCssClasses().add("answer");
        if (StringUtils.isNotBlank(answer.getQuestion().getLookupClass())) {
            if (useSuggest) {
                getSuggest().setRender(true);
                getSuggest().setValuePropertyName(answer.getQuestion().getLookupReturn());
                getSuggest().getSuggestQuery().setDataObjectClassName(answer.getQuestion().getLookupClass());
            }
            
            getQuickfinder().setRender(true);
            getQuickfinder().setDataObjectClassName(answer.getQuestion().getLookupClass());
            getQuickfinder().getFieldConversions().put(answer.getQuestion().getLookupReturn(), getPropertyName());
        }
        super.performApplyModel(model, parent);
    }
    
    protected <T> void copyProperties(T component) {
        super.copyProperties(component);
        QuestionField question = (QuestionField) component;
        question.setControlMappings(controlMappings);
        question.setUseSuggest(useSuggest);
    }
    
    public Map<String, String> getControlMappings() {
        return controlMappings;
    }

    public void setControlMappings(Map<String, String> controlMappings) {
        this.controlMappings = controlMappings;
    }

    public boolean isUseSuggest() {
        return useSuggest;
    }

    public void setUseSuggest(boolean useSuggest) {
        this.useSuggest = useSuggest;
    }
}
