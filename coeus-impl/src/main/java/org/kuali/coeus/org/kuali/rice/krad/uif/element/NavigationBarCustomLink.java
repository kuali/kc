package org.kuali.coeus.org.kuali.rice.krad.uif.element;

import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.element.NavigationBar;

public class NavigationBarCustomLink extends NavigationBar {

	private static final long serialVersionUID = -3341001364063327193L;

	private Action brandImageLink;

	public Action getBrandImageLink() {
		return brandImageLink;
	}

	public void setBrandImageLink(Action brandImageLink) {
		this.brandImageLink = brandImageLink;
	}
}
