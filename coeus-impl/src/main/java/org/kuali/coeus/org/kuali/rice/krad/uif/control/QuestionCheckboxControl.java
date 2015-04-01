package org.kuali.coeus.org.kuali.rice.krad.uif.control;

import org.kuali.rice.krad.uif.control.CheckboxControl;

/**
 * Created by Jeff Largent on 3/24/15.
 */
public class QuestionCheckboxControl extends CheckboxControl {

    protected String description;

    public QuestionCheckboxControl() {
        super();
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
