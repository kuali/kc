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
package org.kuali.kra.iacuc.threers;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.protocol.ProtocolDocumentBase;

import java.util.List;

public class AddAlternateSearchEvent extends KcDocumentEventBaseExtension {

    private IacucAlternateSearch alternateSearch;
    private List<String> selectedDatabases;
    
    protected AddAlternateSearchEvent(ProtocolDocumentBase document, IacucAlternateSearch alternateSearch, List<String> selectedDatabases) {
        super("Add Alternate Search", "", document);
        
        this.alternateSearch = alternateSearch;
        this.selectedDatabases = selectedDatabases;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public KcBusinessRule getRule() {
        return new AddAlternateSearchRule();
    }

    public IacucAlternateSearch getAlternateSearch() {
        return alternateSearch;
    }

    public void setAlternateSearch(IacucAlternateSearch alternateSearch) {
        this.alternateSearch = alternateSearch;
    }

    public List<String> getSelectedDatabases() {
        return selectedDatabases;
    }

    public void setSelectedDatabases(List<String> selectedDatabases) {
        this.selectedDatabases = selectedDatabases;
    }

}
