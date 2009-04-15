/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.dao;

/**
 * Person DAO.  The DAO is necessary for fast SQL queries.  When
 * we don't want to read an entire BO, the DAO is used to retrieve
 * the raw data that we need.
 */
public interface PersonDao {
    
    /**
     * Get the username of a person based upon the person's ID.
     * @param userId the person's ID
     * @return the person's username
     */
    public String getUserName(String userId);
}
