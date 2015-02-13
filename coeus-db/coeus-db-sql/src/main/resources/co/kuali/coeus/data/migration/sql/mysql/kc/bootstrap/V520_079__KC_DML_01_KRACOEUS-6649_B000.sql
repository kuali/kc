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

DELIMITER /
INSERT INTO SEQ_VALID_NARR_FORMS_ID VALUES(NULL)
/
insert into VALID_NARR_FORMS (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID) 
values ((SELECT (MAX(ID)) FROM SEQ_VALID_NARR_FORMS_ID),'RR_SF424_2_0-V2.0',86,'N',NOW(),'admin',UUID())
/
INSERT INTO SEQ_VALID_NARR_FORMS_ID VALUES(NULL)
/
insert into VALID_NARR_FORMS (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID) 
values ((SELECT (MAX(ID)) FROM SEQ_VALID_NARR_FORMS_ID),'RR_SF424_2_0-V2.0',6,'N',NOW(),'admin',UUID())
/
insert into NARRATIVE_TYPE (NARRATIVE_TYPE_CODE,DESCRIPTION,NARRATIVE_TYPE_GROUP,ALLOW_MULTIPLE,SYSTEM_GENERATED,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
values ('139','RRSF424_Cover_Letter','P','N','N','admin',NOW(),UUID(),1)
/
INSERT INTO SEQ_VALID_NARR_FORMS_ID VALUES(NULL)
/
insert into VALID_NARR_FORMS (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID) 
values ((SELECT (MAX(ID)) FROM SEQ_VALID_NARR_FORMS_ID),'RR_SF424_2_0-V2.0',139,'N',NOW(),'admin',UUID())
/

DELIMITER ;
