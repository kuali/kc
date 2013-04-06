/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.actions.table;

import java.io.Serializable;
import java.sql.Date;

import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.actions.IacucProtocolActionBean;

/**
 * This class is really just a "form" containing the comments and action date 
 * for tabling an IACUC protocol.
 */
public class IacucProtocolTableBean extends IacucProtocolActionBean implements Serializable {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
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
