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
package org.kuali.coeus.org.kuali.rice.krad.uif.container;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.container.Group;
import org.kuali.rice.krad.uif.container.GroupBase;
import org.kuali.rice.krad.uif.element.Action;

public class DropdownButton extends GroupBase {

	private Action buttonPrototype;
	private Group listPrototype;
	
	public void performInitialization(Object model) {
		List<Component> newItems = new ArrayList<Component>();
		newItems.add(buttonPrototype);
		newItems.add(listPrototype);
		setItems(newItems);
		super.performInitialization(model);
	}
	
	public Action getButtonPrototype() {
		return buttonPrototype;
	}
	public void setButtonPrototype(Action buttonPrototype) {
		this.buttonPrototype = buttonPrototype;
	}
	public Group getListPrototype() {
		return listPrototype;
	}
	public void setListPrototype(Group listPrototype) {
		this.listPrototype = listPrototype;
	}
	
}
