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
package org.kuali.kra.web.krad;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.arg.ArgValueLookup;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.impl.custom.arg.ArgValueLookupValuesFinder;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.framework.rolodex.RolodexConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.datadictionary.validation.constraint.NumericPatternConstraint;
import org.kuali.rice.krad.uif.control.Control;
import org.kuali.rice.krad.uif.control.TextControlBase;
import org.kuali.rice.krad.uif.field.InputFieldBase;
import org.kuali.rice.krad.uif.util.ComponentFactory;
import org.kuali.rice.krad.uif.util.LifecycleElement;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;

import java.util.HashMap;
import java.util.Map;

public class QuestionField extends InputFieldBase {

    private static final long serialVersionUID = -940684402907403282L;
    private Map<String, String> controlMappings;
    private boolean useSuggest;
    private String bindingInfo;

    public QuestionField() {
        controlMappings = new HashMap<String, String>();
    }
    
    @Override
    public void performApplyModel(Object model, LifecycleElement parent) {
        Object myBo = ObjectPropertyUtils.getPropertyValue(model, KcBindingInfo.getParentBindingInfo(getBindingInfo()));
        Answer answer = (Answer) myBo;
        setControl((Control) ComponentFactory.getNewComponentInstance(getControlMappings().get(answer.getQuestion().getQuestionTypeId().toString())));
        getControl().getCssClasses().add("answer");

        if (getControl() instanceof TextControlBase
                && !answer.getQuestion().getQuestionTypeId().equals(Constants.QUESTION_RESPONSE_TYPE_DATE)) {
            ((TextControlBase)getControl()).setMaxLength(answer.getQuestion().getAnswerMaxLength());
        }
        if (answer.getQuestion().getQuestionTypeId().equals(Constants.QUESTION_RESPONSE_TYPE_NUMBER)) {
            this.setValidCharactersConstraint(new NumericPatternConstraint());
        }
        if (answer.getQuestion().getQuestionTypeId().equals(Constants.QUESTION_RESPONSE_TYPE_LOOKUP) && answer.getQuestion().getLookupClass().equals(ArgValueLookup.class.getName())) {
            setControl((Control) ComponentFactory.getNewComponentInstance("Uif-DropdownControl"));
            ArgValueLookupValuesFinder valuesFinder = new ArgValueLookupValuesFinder();
            valuesFinder.setArgName(answer.getQuestion().getLookupReturn());
            valuesFinder.setAddBlankOption(false);
            setOptionsFinder(valuesFinder);
            getInquiry().setRender(true);
        } else if (StringUtils.isNotBlank(answer.getQuestion().getLookupClass())) {
            ((TextControlBase) getControl()).setMaxLength(null);
            this.getControl().getAdditionalCssClasses().add("questionnaire-widgetInputOnly");
            this.setOnDocumentReadyScript("Kc.Questionnaire.Answer.setWidgetInputOnly()");
            if (useSuggest) {
                getSuggest().setRender(true);
                getSuggest().setValuePropertyName(answer.getQuestion().getLookupReturn());
                getSuggest().getSuggestQuery().setDataObjectClassName(answer.getQuestion().getLookupClass());
            }

            getQuickfinder().setRender(true);
            getQuickfinder().setReturnByScript(true);
            getQuickfinder().setDataObjectClassName(answer.getQuestion().getLookupClass());
            getQuickfinder().getFieldConversions().put(answer.getQuestion().getLookupReturn(), getPropertyName());

            if (answer.getQuestion().getLookupClass().equals(Rolodex.class.getName())) {
                getQuickfinder().setViewName(RolodexConstants.EDITABLE_ROLODEX_QUICKFINDER);
            }
        }
        super.performApplyModel(model, parent);
    }

    @Override
    public QuestionField copy() {
        final QuestionField copy =  super.copy();
        copy.setControlMappings(new HashMap<>(controlMappings));
        copy.setUseSuggest(useSuggest);
        return copy;
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
