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
package org.kuali.coeus.propdev.impl.attachment;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;

public interface NarrativeAuthZService {

    /**
     * Gets the default narrative right for a user.  The default narrative
     * right is the highest right that a user can have based upon his/her
     * permissions.
     * 
     * @param userId the user's unique username
     * @param doc the Proposal Development Document
     * @return the user's default narrative right
     */
    public NarrativeRight getDefaultNarrativeRight(String userId, ProposalDevelopmentDocument doc);
    
    /**
     * Gets the default narrative right for a user.  The default narrative
     * right is the highest right that a user can have based upon his/her
     * role.
     *
     * @param roleName the proposal role for the user
     * @return the user's default narrative right
     */
    public NarrativeRight getDefaultNarrativeRight(String roleName);
}
