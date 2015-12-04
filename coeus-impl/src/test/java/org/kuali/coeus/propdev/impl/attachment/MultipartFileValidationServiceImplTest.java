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

import org.junit.Assert;
import org.junit.Test;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.springframework.mock.web.MockMultipartFile;

public class MultipartFileValidationServiceImplTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_validate_null_errorPath() {
        final MultipartFileValidationServiceImpl mp = new MultipartFileValidationServiceImpl();
        mp.validateMultipartFile(null, new MockMultipartFile("foo.txt", new byte[] {}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_validate_blank_errorPath() {
        final MultipartFileValidationServiceImpl mp = new MultipartFileValidationServiceImpl();
        mp.validateMultipartFile(" ", new MockMultipartFile("foo.txt", new byte[] {}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_validate_null_file() {
        final MultipartFileValidationServiceImpl mp = new MultipartFileValidationServiceImpl();
        mp.validateMultipartFile("a_prop", null);
    }

    @Test
    public void test_validate_valid_file_size() {
        final MultipartFileValidationServiceImpl mp = new MultipartFileValidationServiceImpl();
        mp.validateMultipartFile("a_prop", new MockMultipartFile("foo.txt", new byte[] {1, 2}));
    }

    @Test
    public void test_validate_invalid_file_size() {
        final MultipartFileValidationServiceImpl mp = new MultipartFileValidationServiceImpl();
        Assert.assertTrue(mp.validateMultipartFile("a_prop", new MockMultipartFile("foo.txt", new byte[] {}))
                .containsMessageKey(RiceKeyConstants.ERROR_UPLOADFILE_EMPTY));
    }
}
