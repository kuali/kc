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
package org.kuali.kra.document;

import java.util.List;

import org.kuali.kra.bo.AbstractSpecialReview;

/**
 * This interface declares all methods required to process SpecialReview.
 * T represents type of concrete class of SpecialReview
 */
public interface SpecialReviewHandler<T extends AbstractSpecialReview> {
    /**
     * 
     * This method is for adding special review
     * @param specialReview
     */
    public void addSpecialReview(T specialReview);
    /**
     * 
     * Gets the list of SpecialReview BO objects
     * @return
     */
    public List<T> getSpecialReviews();
    /**
     * 
     * Gets the SpecialReview BO at the selected index from the list
     * @param index
     * @return SpecialReview
     */
    public T getSpecialReview(int index);
}
