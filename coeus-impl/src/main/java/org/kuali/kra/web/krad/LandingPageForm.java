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
package org.kuali.kra.web.krad;

import org.kuali.rice.krad.web.bind.RequestAccessible;
import org.kuali.rice.krad.web.form.UifFormBase;

public class LandingPageForm extends UifFormBase {

    private static final long serialVersionUID = 304896781932966963L;
    
    @RequestAccessible
    private String href;
    
    private String selectedMenuItem;

	public LandingPageForm() {
        setViewId("Kc-LandingPage-DefaultView");
    }

	public String getHref() {
		return href;
	}

	/**
	 * Href is used for CustomLinkIframeView. Avoids needing to create custom views for each link
	 * @param href
	 */
	public void setHref(String href) {
		this.href = href;
	}
	
    public String getSelectedMenuItem() {
		return selectedMenuItem;
	}

	public void setSelectedMenuItem(String selectedMenuItem) {
		this.selectedMenuItem = selectedMenuItem;
	}


}
