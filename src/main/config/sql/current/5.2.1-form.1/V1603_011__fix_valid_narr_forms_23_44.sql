--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

delete from valid_narr_forms where narrative_type_code in ('23', '44');

INSERT INTO VALID_NARR_FORMS (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
VALUES (SEQ_VALID_NARR_FORMS_ID.NEXTVAL,'PHS398_ResearchPlan-V1.0','23','N','admin',SYSDATE,SYS_GUID());

INSERT INTO VALID_NARR_FORMS (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
VALUES (SEQ_VALID_NARR_FORMS_ID.NEXTVAL,'PHS398_ResearchPlan-V1.1','23','N','admin',SYSDATE,SYS_GUID());

INSERT INTO VALID_NARR_FORMS (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
VALUES (SEQ_VALID_NARR_FORMS_ID.NEXTVAL,'PHS398_ResearchPlan_1_2-V1.2','23','N','admin',SYSDATE,SYS_GUID());

INSERT INTO VALID_NARR_FORMS (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
VALUES (SEQ_VALID_NARR_FORMS_ID.NEXTVAL,'PHS398_ResearchPlan_1_3','44','N','admin',SYSDATE,SYS_GUID());

INSERT INTO VALID_NARR_FORMS (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
VALUES (SEQ_VALID_NARR_FORMS_ID.NEXTVAL,'PHS398_ResearchPlan_2_0','44','N','admin',SYSDATE,SYS_GUID());

INSERT INTO VALID_NARR_FORMS (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
VALUES (SEQ_VALID_NARR_FORMS_ID.NEXTVAL,'PHS398_ResearchPlan_3_0','44','N','admin',SYSDATE,SYS_GUID());
