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
package org.kuali.kra.rice.shim;

import org.kuali.rice.kew.user.UserId;

/** This class is a shim until we replace calls to UniversalUserService with the 
 * new KIM IdentityManagementService.
 */
public interface UniversalUserService {
    
    /**
     * Return the UniversalUser according to the universal user ID supplied.
     * 
     * @param personUniversalIdentifier
     * @return Returns a populated UniversalUser object if the User is found, otherwise throws a UserNotFoundException
     * @throws UserNotFoundException
     */
    public UniversalUser getUniversalUser(UserId userId) throws Exception;

    public UniversalUser getUniversalUserByAuthenticationUserId( String authenticationUserId ) throws Exception;

}
