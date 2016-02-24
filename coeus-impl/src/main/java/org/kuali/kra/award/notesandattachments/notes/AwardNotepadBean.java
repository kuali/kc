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
package org.kuali.kra.award.notesandattachments.notes;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;


public class AwardNotepadBean implements Serializable {

    private static final long serialVersionUID = -2409602626444019766L;

    private AwardForm parent;
    
    private AwardNotepad newAwardNotepad;
    
    /**
     * Constructs a AwardNotepadBean
     * @param parent
     */
    public AwardNotepadBean() {
        super();
    }
    /**
     * Constructs a CostShareFormHelper
     * @param parent
     */
    public AwardNotepadBean(AwardForm parent) {
        this.parent = parent;
        init();
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newAwardNotepad = new AwardNotepad(); 
    }


    /**
     * Gets the newAwardNotepad attribute. 
     * @return Returns the newAwardNotepad.
     */
    public AwardNotepad getNewAwardNotepad() {
        return newAwardNotepad;
    }

    /**
     * Sets the newAwardNotepad attribute value.
     * @param newAwardNotepad The newAwardNotepad to set.
     */
    public void setNewAwardNotepad(AwardNotepad newAwardNotepad) {
        this.newAwardNotepad = newAwardNotepad;
    }

    public AwardDocument getAwardDocument() {
        return parent.getAwardDocument();
    }

    public Object getData() {
        return getNewAwardNotepad();
    }
    
    /**
     * This method is called when adding a new AwardCostShare
     * @param formHelper
     * @return
     * @throws Exception
     */
    public boolean addNote(AwardNotepadBean awardNotepadBean) throws Exception {
        AwardNotepad note = awardNotepadBean.getNewAwardNotepad();
               
        if(StringUtils.isBlank(note.getComments())){
            note.setComments("&nbsp");
        }
        
        Calendar cl = Calendar.getInstance();
        note.setCreateTimestamp(new Timestamp(cl.getTime().getTime()));
        note.setUpdateTimestamp(KcServiceLocator.getService(DateTimeService.class).getCurrentTimestamp());
        note.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
        note.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        note.setCreateUserFullName(GlobalVariables.getUserSession().getPerson().getName());
        note.setUpdateUserFullName(GlobalVariables.getUserSession().getPerson().getName());
        awardNotepadBean.getAwardDocument().getAward().add(note);
        awardNotepadBean.init();
        return true;
    }
}
