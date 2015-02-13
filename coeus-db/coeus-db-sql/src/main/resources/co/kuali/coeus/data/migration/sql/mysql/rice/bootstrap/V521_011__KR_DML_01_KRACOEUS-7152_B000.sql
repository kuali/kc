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

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES (
(SELECT NMSPC_CD FROM  KRCR_NMSPC_T WHERE NM = 'Time And Money' AND APPL_ID = 'KC'), 
(SELECT CMPNT_CD FROM KRCR_CMPNT_T WHERE NMSPC_CD = (SELECT NMSPC_CD FROM  KRCR_NMSPC_T WHERE NM = 'Time And Money' AND APPL_ID = 'KC') AND NM = 'Document'),
'TXN_TYPE_DEF_COPIED_AWARD',
UUID(),
1,
(SELECT PARM_TYP_CD FROM KRCR_PARM_TYP_T WHERE NM = 'Config'),
9,
'New Transaction',
'A',
'KC')
/

DELIMITER ;
