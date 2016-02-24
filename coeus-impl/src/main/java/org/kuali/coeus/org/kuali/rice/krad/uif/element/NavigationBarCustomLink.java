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
