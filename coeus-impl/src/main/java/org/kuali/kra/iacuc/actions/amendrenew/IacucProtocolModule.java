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
package org.kuali.kra.iacuc.actions.amendrenew;

import org.kuali.kra.protocol.actions.amendrenew.ProtocolModuleBase;

@SuppressWarnings("serial")
public class IacucProtocolModule extends ProtocolModuleBase {

    public static final String THREE_RS = "036";
    public static final String SPECIES_GROUPS = "032";
    public static final String EXCEPTIONS = "033";
    public static final String PROCEDURES = "031";
    

    public static final String GENERAL_INFO = "001";

    public static final String PROTOCOL_PERSONNEL = "002";

    public static final String AREAS_OF_RESEARCH = "004";

    public static final String SUBJECTS = "006";

    public static final String SPECIAL_REVIEW = "007";

    public static final String ADD_MODIFY_ATTACHMENTS = "008";

    public static final String PROTOCOL_REFERENCES = "016";

    public static final String PROTOCOL_ORGANIZATIONS = "017";

    public static final String OTHERS = "023";

    public static final String FUNDING_SOURCE = "024";

    public static final String PROTOCOL_PERMISSIONS = "025";

    public static final String QUESTIONNAIRE = "026";
    
    public IacucProtocolModule() {
        super();
    }


}
