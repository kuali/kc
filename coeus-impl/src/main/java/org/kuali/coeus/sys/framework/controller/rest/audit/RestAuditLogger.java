package org.kuali.coeus.sys.framework.controller.rest.audit;

public interface RestAuditLogger {

	void addModifiedItem(Object currentItem, Object updatedItem);
	
	void addNewItem(Object newItem);
	
	void addDeletedItem(Object deletedItem);
	
	void saveAuditLog();
}
