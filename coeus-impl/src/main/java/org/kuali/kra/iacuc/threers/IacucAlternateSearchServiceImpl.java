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

import org.kuali.kra.iacuc.IacucProtocol;

import java.util.ArrayList;
import java.util.List;

public class IacucAlternateSearchServiceImpl implements IacucAlternateSearchService {

    @Override
    public void addAlternateSearch(IacucProtocol protocol, IacucAlternateSearch newAlternateSearch, List<String> selectedDatabases) {
               
        List<IacucProtocolAlternateSearchDatabase> databases = new ArrayList<IacucProtocolAlternateSearchDatabase>();
        for (String newDb : selectedDatabases) {
            databases.add(createAltSearchDB(newDb));
        }
        
        newAlternateSearch.setDatabases(databases);
        
        List<IacucAlternateSearch> searches = protocol.getIacucAlternateSearches();
        if (searches == null) {
          searches = new ArrayList<IacucAlternateSearch>(); 
        }
                
        searches.add(newAlternateSearch);
        
        protocol.setIacucAlternateSearches(searches);        
    }

    @Override
    public void deleteAlternateSearch(IacucProtocol protocol, int index) {
        if (index >= 0 && index < protocol.getIacucAlternateSearches().size()) {
            protocol.getIacucAlternateSearches().remove(index);
        }
        
    }

    private IacucProtocolAlternateSearchDatabase createAltSearchDB(String dbName) {
        IacucProtocolAlternateSearchDatabase db = new IacucProtocolAlternateSearchDatabase();
        db.setAlternateSearchDatabaseName(dbName);
        return db;
    }    
}
