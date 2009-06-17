/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.service;

import org.kuali.kra.bo.Rolodex;

/**
 * The RolodexService provides methods for obtaining Rolodex business objects.
 *
 * @author Kuali Research Administration Team 
 * 
 */
public interface RolodexService {

    
    /**
     * 
     * This method returns a Rolodex BO for a particular RolodexId or null if none is found.
     * 
     * @param rolodexId
     * @return the Rolodex if found (@see org.kuali.kra.bo.Rolodex)
     * 
     */
    public Rolodex getRolodex(int rolodexId);
}
