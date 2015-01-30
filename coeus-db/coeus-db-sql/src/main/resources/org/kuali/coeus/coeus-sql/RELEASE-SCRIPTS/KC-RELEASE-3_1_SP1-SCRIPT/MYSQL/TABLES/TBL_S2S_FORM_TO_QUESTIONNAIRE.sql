--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

CREATE TABLE S2S_FORM_TO_QUESTIONNAIRE  ( 
    S2S_FORM_TO_QUESTIONNAIRE_ID	DECIMAL(12,0) NOT NULL,
	OPP_NAME_SPACE                  VARCHAR(200) NOT NULL,
	FORM_NAME                       VARCHAR(100) NOT NULL,
    QUESTIONNAIRE_ID                DECIMAL(12,0) NOT NULL,
	UPDATE_TIMESTAMP                DATE NOT NULL,
	UPDATE_USER                     VARCHAR(60) NOT NULL,
    OBJ_ID                          VARCHAR(36) NOT NULL,
    VER_NBR                         DECIMAL(8,0) NOT NULL
);

ALTER TABLE S2S_FORM_TO_QUESTIONNAIRE ADD (
	CONSTRAINT S2S_FORM_TO_QUESTIONNAIRE_PK
	PRIMARY KEY ( S2S_FORM_TO_QUESTIONNAIRE_ID )
);

ALTER TABLE S2S_FORM_TO_QUESTIONNAIRE ADD (
	CONSTRAINT S2S_FORM_TO_QUESTIONNAIRE_UQ
	UNIQUE ( S2S_FORM_TO_QUESTIONNAIRE_ID, OPP_NAME_SPACE )
);
