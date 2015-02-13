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
INSERT INTO KRIM_TYP_ATTR_ID_S VALUES(NULL)
/
INSERT INTO KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, SORT_CD, ACTV_IND, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM KRIM_TYP_ATTR_ID_S), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName'), 'b', 'Y', UUID())
/
INSERT INTO KRIM_TYP_ATTR_ID_S VALUES(NULL)
/

INSERT INTO KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, SORT_CD, ACTV_IND, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM KRIM_TYP_ATTR_ID_S), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName'), 'b', 'Y', UUID())
/
DELIMITER ;
