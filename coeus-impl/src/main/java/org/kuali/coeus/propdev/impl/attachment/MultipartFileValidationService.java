/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2015 Kuali, Inc.
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

import org.kuali.rice.krad.util.MessageMap;
import org.springframework.web.multipart.MultipartFile;

public interface MultipartFileValidationService {

    /**
     * Validates a MultipartFile and returns error messages.
     * @param errorPath the error path.  cannot be blank
     * @param file the multipart file. cannot be null.
     * @return empty MessageMap if valid
     * @throws IllegalArgumentException if the errorPath is blank or the file is null.
     */
    MessageMap validateMultipartFile(String errorPath, MultipartFile file);
}
