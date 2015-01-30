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
INSERT INTO SEQ_KC_KRMS_TERM_FUNCTION_ID VALUES(NULL)
/
insert into KC_KRMS_TERM_FUNCTION(KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM,DESCRIPTION,RETURN_TYPE,FUNCTION_TYPE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID)
values ((SELECT (MAX(ID)) FROM SEQ_KC_KRMS_TERM_FUNCTION_ID),'IS_SPONSOR_FEDERAL','Is Sponsor Federal','java.lang.String',1,NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_KC_KRMS_TERM_FUN_PARM_ID VALUES(NULL)
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID)
values ((SELECT (MAX(ID)) FROM SEQ_KC_KRMS_TERM_FUN_PARM_ID),(SELECT (MAX(ID)) FROM SEQ_KC_KRMS_TERM_FUNCTION_ID),'sponsorCode','java.lang.Object',1,NOW(),'admin',1,UUID())
/
DELIMITER ;
