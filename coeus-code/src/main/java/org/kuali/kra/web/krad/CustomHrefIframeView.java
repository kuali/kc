package org.kuali.kra.web.krad;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.krad.uif.view.IframeView;

public class CustomHrefIframeView extends IframeView {

    @Override
    public void performInitialization(Object model) {
        super.performInitialization(model);
        if (model instanceof LandingPageForm
        		&& StringUtils.isNotBlank(((LandingPageForm) model).getHref())) {
        	setHref(((LandingPageForm) model).getHref());
        }
    }
}
