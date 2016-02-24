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
package org.kuali.coeus.common.questionnaire.framework.core;

public final class QuestionnaireConstants {
	
	/**
	 * field names
	 */
	public static final String QUESTIONNAIRE_ID_PARAMETER_NAME = "id";
	public static final String QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME = "questionnaireSeqId";
	public static final String QUESTIONNAIRE_SEQUENCE_NUMBER_PARAMETER_NAME = "sequenceNumber";

	public static final String QUESTION_SEQEQUENCE_ID = "questionSeqId";
	public static final String QUESTION_ID = "id";
	
	/**
	 * Sequence name
	 */
	public static final String DB_SEQUENCE_NAME_QUESTION_SEQ_ID = "SEQ_QUESTION_ID";
	public static final String MODULE_CODE = "moduleCode";
	public static final String MODULE_ITEM_KEY = "moduleItemKey";
	public static final String MODULE_SUB_ITEM_KEY = "moduleSubItemKey";
	public static final String MODULE_ITEM_CODE = "moduleItemCode";

	private QuestionnaireConstants() {
		throw new UnsupportedOperationException("do not instantiate");
	}

}
