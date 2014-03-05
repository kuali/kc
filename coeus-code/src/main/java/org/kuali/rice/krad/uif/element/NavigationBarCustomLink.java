package org.kuali.rice.krad.uif.element;

import org.kuali.rice.krad.uif.element.NavigationBar;
import org.kuali.rice.krad.uif.util.UrlInfo;

public class NavigationBarCustomLink extends NavigationBar {

	private static final long serialVersionUID = -3341001364063327193L;
	
	private UrlInfo brandImageUrl;

	public UrlInfo getBrandImageUrl() {
		return brandImageUrl;
	}

	public void setBrandImageUrl(UrlInfo brandImageUrl) {
		this.brandImageUrl = brandImageUrl;
	}
}
