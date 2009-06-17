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

import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.bo.AbstractSpecialReviewExemption;
import org.kuali.kra.document.SpecialReviewHandler;
import org.kuali.kra.web.struts.form.SpecialReviewFormBase;

/**
 * This interface should take care of all tasks needed for Special Review functionality for different module 
 */
public interface SpecialReviewService<T extends AbstractSpecialReview,E extends AbstractSpecialReviewExemption> {
    /**
     * 
     * This method is for adding SpecialReview BO
     * @param processSpecialReview
     * @param specialReviewForm
     * @return true if business rules passed; otherwise false
     */
    public boolean addSpecialReview(SpecialReviewHandler<T> processSpecialReview,SpecialReviewFormBase<E> specialReviewForm);
    /**
     * 
     * This method is for deleting special review from SpecialReview list
     * @param processSpecialReview
     * @param selectedIndex
     */
    public void deleteSpecialReview(SpecialReviewHandler<T> processSpecialReview,int selectedIndex);
    /**
     * 
     * This method process all required validations and actions needed before saving the special review
     * @param processSpecialReview
     */
    public void processBeforeSaveSpecialReview(SpecialReviewHandler<T> processSpecialReview);
    
    /**
     * Delete exemptions.  Kuali Rice and OJB has difficulty properly deleting lists that
     * are lists.  Due to that, this method will correctly delete any exemptions that are
     * in the database that shouldn't be there.  
     * @param specialReviewHandler the owner of the list of special reviews
     * @param specialReviewIdFieldName the name of the property for the primary key id
     * @param specialReviewExemptionClass the exemption classes to access in the database
     */
    public void deleteExemptions(SpecialReviewHandler<T> specialReviewHandler, String specialReviewIdFieldName, Class specialReviewExemptionClass);
}
