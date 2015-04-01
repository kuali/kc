package org.kuali.coeus.org.kuali.rice.krad.uif.control;

import org.kuali.rice.krad.datadictionary.parse.BeanTag;
import org.kuali.rice.krad.datadictionary.parse.BeanTagAttribute;
import org.kuali.rice.krad.datadictionary.parse.BeanTags;
import org.kuali.rice.krad.uif.control.RadioGroupControl;

import java.util.List;

/**
 * Created by Jeff on 3/24/15.
 */
@BeanTags({@BeanTag(name = "questionRadioControl", parent = "Uif-QuestionRadioControl")})
public class QuestionRadioGroupControl extends RadioGroupControl {

    List<String> optionDescriptions;

    public QuestionRadioGroupControl() {
        super();
    }

    @BeanTagAttribute
    public List<String> getOptionDescriptions() {
        return this.optionDescriptions;
    }

    public void setOptionDescriptions(List<String> optionDescriptions) {
        this.optionDescriptions = optionDescriptions;
    }
}
