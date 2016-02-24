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
package org.kuali.kra.coi.actions;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiUserRole;

/**
 * 
 * This class represents the event fired when a coi reviewer is added to 
 * a disclosure.
 */
public class AddCoiReviewerEvent extends KcDocumentEventBaseExtension {

    private String propertyName;
    private CoiDisclosure coiDisclosure;
    private CoiUserRole coiUserRole;
    
    
    public AddCoiReviewerEvent(String propertyName, CoiDisclosure coiDisclosure, CoiUserRole coiUserRole) {
        super("Add Coi Reviewer", "", null);       
        this.propertyName = propertyName;
        this.coiDisclosure = coiDisclosure;
        this.coiUserRole = coiUserRole;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public KcBusinessRule getRule() {
        return new AddCoiReviewerRule();
    }

    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public CoiUserRole getCoiUserRole() {
        return coiUserRole;
    }

    public void setCoiUserRole(CoiUserRole coiUserRole) {
        this.coiUserRole = coiUserRole;
    }
}
