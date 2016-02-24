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
package org.kuali.coeus.s2sgen.impl.generate.support;

public class NSFCoverPageV1_2GeneratorTest extends NSFCoverPageLegacyBaseGeneratorTest {

    private static final String NAMESPACE = "http://apply.grants.gov/forms/NSF_CoverPage_1_2-V1.2";
    private static final String FORM_NAME = "NSF_CoverPage_1_2-V1.2";

    @Override
    protected String getFormName() {
        return FORM_NAME;
    }

    @Override
    protected String getNamespace() {
        return NAMESPACE;
    }

    @Override
    protected String getFormGeneratorName() {
        return NSFCoverPageV1_2Generator.class.getSimpleName();
    }
}
