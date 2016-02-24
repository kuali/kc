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
package org.kuali.kra.committee.web.struts.form;


import org.kuali.coeus.common.committee.impl.web.struts.form.CommitteeFormBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.CommitteeHelperBase;

/**
 * The Committee Form contains the fields necessary for all POST
 * operations coming from a web browser pertaining to the editing
 * of a Committee.
 * 
 * The form contains a reference to the original committee document.
 * User requests will result in the document fields being set.  It
 * is the responsibility of the business rules to validate the new
 * values of those fields.
 * 
 * The Committee Form has Helpers for some tabs (web pages), if necessary.  
 * For extra properties, e.g. fields for adding a new entry to a table, those
 * properties reside in the Helper corresponding to the tab (web page)
 * they are associated with.
 */
@SuppressWarnings("serial")
public class CommitteeForm extends CommitteeFormBase {
    
    @Override
    protected String getDefaultDocumentTypeName() {
        return "CommitteeDocument";
    }


    @Override
    protected CommitteeHelperBase getNewCommitteeHelperInstanceHook(CommitteeFormBase committeeForm) {
        return new CommitteeHelper((CommitteeForm)committeeForm);
    }
}

