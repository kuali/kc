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
