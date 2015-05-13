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
package org.kuali.coeus.org.kuali.rice.krad.uif.control;

import org.kuali.rice.krad.datadictionary.parse.BeanTag;
import org.kuali.rice.krad.datadictionary.parse.BeanTagAttribute;
import org.kuali.rice.krad.datadictionary.parse.BeanTags;
import org.kuali.rice.krad.uif.control.RadioGroupControl;

import java.util.List;

@BeanTags({@BeanTag(name = "questionRadioControl", parent = "Uif-QuestionRadioControl")})
public class QuestionRadioGroupControl extends RadioGroupControl {

    private List<String> optionDescriptions;

    @BeanTagAttribute
    public List<String> getOptionDescriptions() {
        return this.optionDescriptions;
    }

    public void setOptionDescriptions(List<String> optionDescriptions) {
        this.optionDescriptions = optionDescriptions;
    }
}
