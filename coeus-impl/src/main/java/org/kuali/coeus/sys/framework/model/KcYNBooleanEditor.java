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
package org.kuali.coeus.sys.framework.model;

import org.kuali.rice.krad.web.bind.UifBooleanEditor;

public class KcYNBooleanEditor extends UifBooleanEditor {
    @Override
    public String getAsText() {
        if(this.getValue() == null) {
            return "";
        }
        if (this.getValue() instanceof String){
            setAsText(this.getValue().toString().trim());
        }
        if(((Boolean)this.getValue()).booleanValue()) {
            return "Yes";
        }
        else {
            return "No";
        }
    }
}
