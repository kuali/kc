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


import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("multipartFileValidationService")
public class MultipartFileValidationServiceImpl implements MultipartFileValidationService {

    @Override
    public MessageMap validateMultipartFile(String errorPath, MultipartFile file) {
        if (StringUtils.isBlank(errorPath)) {
            throw new IllegalArgumentException("errorPath cannot be blank");
        }

        if (file == null) {
            throw new IllegalArgumentException("file cannot be null");
        }
        final MessageMap messages = new MessageMap();

        if (file.getSize() == 0) {
            messages.putError(errorPath, RiceKeyConstants.ERROR_UPLOADFILE_EMPTY, file.getOriginalFilename());
        }
        return messages;
    }
}
