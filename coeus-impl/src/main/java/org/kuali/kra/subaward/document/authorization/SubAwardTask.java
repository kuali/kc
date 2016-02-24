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

package org.kuali.kra.subaward.document.authorization;

import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.subaward.document.SubAwardDocument;


public class SubAwardTask extends Task {

    private SubAwardDocument subAwardDocument;

    /**
     * Constructs a SubAwardTask.java.
     * @param taskName the taskName
     * @param subAwardDocument the SubAwardDocument
     */
    public SubAwardTask(String taskName, SubAwardDocument subAwardDocument) {
        super(TaskGroupName.SUBAWARD, taskName);
        this.setSubAwardDocument(subAwardDocument);
    }

	/**.
	 * This is the Getter Method for subAwardDocument
	 * @return Returns the subAwardDocument.
	 */
	public SubAwardDocument getSubAwardDocument() {
		return subAwardDocument;
	}

	/**.
	 * This is the Setter Method for subAwardDocument
	 * @param subAwardDocument The subAwardDocument to set.
	 */
	public void setSubAwardDocument(SubAwardDocument subAwardDocument) {
		this.subAwardDocument = subAwardDocument;
	}

}
