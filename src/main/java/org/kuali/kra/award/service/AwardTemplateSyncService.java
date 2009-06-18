/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.service;

import org.kuali.kra.award.document.AwardDocument;

/**
 * This class is used for sync AwardSponsorTemplate details to different Award Details Objects.
 */
public interface AwardTemplateSyncService {
    /**
     * 
     * This method is to sync entire AwardTemplate details to Award object. 
     * @param award
     * @return true if successful
     */
    public boolean syncToAward(AwardDocument awardDocument);
    /**
     * 
     * This method is to sync a particular syncable list from award template
     * @param award
     * @param syncPropertyName is the name of member variable to represent syncable list
     * @return true if successful
     */
    public boolean syncToAward(AwardDocument awardDocument,String syncPropertyName);
}
