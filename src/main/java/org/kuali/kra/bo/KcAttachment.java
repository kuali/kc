/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.bo;

/**
 * Simple interface to provide common attachment methods.
 */
public interface KcAttachment {

    /**
     * Returns file name of the attached file.
     * @return
     */
    String getName();
    /**
     * Returns the mime type of the attachment.
     * @return
     */
    String getType();
    /**
     * Returns the data of this attachment
     * @return
     */
    byte[] getData();
    /**
     * Return the image file path based on mime type or other criteria.
     * @return
     */
    String getIconPath();
}
