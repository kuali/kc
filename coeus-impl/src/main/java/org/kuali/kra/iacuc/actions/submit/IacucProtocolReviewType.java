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
package org.kuali.kra.iacuc.actions.submit;

import org.kuali.kra.protocol.actions.submit.ProtocolReviewTypeBase;

/**
 * A Protocol Review Type refers to the type of review that an
 * IACUC Committee will perform
 */
@SuppressWarnings("serial")
public class IacucProtocolReviewType extends ProtocolReviewTypeBase {

    public static final String ADMINISTRATIVE_REVIEW = "1";
    
    public static final String DESIGNATED_MEMBER_REVIEW = "2";
    
    public static final String FULL_COMMITTEE_REVIEW = "3";
    
    public static final String FYI = "4";

    public static final String RESPONSE = "5";

    public static final String IACUC_REVIEW_NOT_REQUIRED = "6";


    public IacucProtocolReviewType() {
    }

}
