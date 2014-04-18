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
package org.kuali.coeus.common.api.rolodex;


/**
 * This service provides methods for working with Rolodex objects.
 *
 * @author Kuali Research Administration Team
 *
 */
public interface RolodexService {

    /**
     * This method will retrieves a {@link RolodexContract} by rolodexId.  The rolodexId cannot be null.
     * @param rolodexId the rolodexId.  Cannot be null.
     * @return the {@link RolodexContract} or null if not found.
     * @throws java.lang.IllegalArgumentException if the rolodexId is null
     */
    RolodexContract getRolodex(Integer rolodexId);
}
