package org.kuali.coeus.sys.framework.model;

import org.kuali.rice.krad.web.bind.UifBooleanEditor;

public class KcYNBooleanEditor extends UifBooleanEditor {
    @Override
    public String getAsText() {
        if(this.getValue() == null) {
            return "";
        }
        if (this.getValue() instanceof String){
            setAsText(this.getValue().toString().trim());
        }
        if(((Boolean)this.getValue()).booleanValue()) {
            return "Yes";
        }
        else {
            return "No";
        }
    }
}
