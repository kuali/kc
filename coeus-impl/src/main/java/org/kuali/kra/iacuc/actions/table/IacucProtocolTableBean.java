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
package org.kuali.kra.iacuc.actions.table;

import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.actions.IacucProtocolActionBean;

import java.io.Serializable;
import java.sql.Date;

/**
 * This class is really just a "form" containing the comments and action date 
 * for tabling an IACUC protocol.
 */
public class IacucProtocolTableBean extends IacucProtocolActionBean implements Serializable {
    

    private static final long serialVersionUID = 6076002106217543225L;
    
    private String comments = "";
    private Date actionDate = new Date(System.currentTimeMillis());


    /**
     * Constructs a IacucProtocolTableBean
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolTableBean(IacucActionHelper actionHelper) {
        super(actionHelper);
    }
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public Date getActionDate() {
        return actionDate;
    }
    
    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

}
