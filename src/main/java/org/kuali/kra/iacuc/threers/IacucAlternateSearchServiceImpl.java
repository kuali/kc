/*
 * Copyright 2005-2014 The Kuali Foundation
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
