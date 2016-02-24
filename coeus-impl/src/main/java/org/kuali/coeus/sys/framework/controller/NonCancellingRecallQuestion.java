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
package org.kuali.coeus.sys.framework.controller;

import org.kuali.rice.kns.question.QuestionBase;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("nonCancellingRecallQuestion")
public class NonCancellingRecallQuestion extends QuestionBase {

    public static final String YES = "0";
    public static final String NO = "1";

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public NonCancellingRecallQuestion() {
        super("Are you sure you want to recall this document to the action list?", new ArrayList(2));
        this.getButtons().add("Yes");
        this.getButtons().add("returntodocument");
    }
}
