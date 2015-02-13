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
INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_PERM_T(PERM_ID,OBJ_ID,VER_NBR,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND) VALUES((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),UUID(),'1',(SELECT PERM_TMPL_ID from KRIM_PERM_TMPL_T WHERE NMSPC_CD='KR-NS' AND NM='Full Unmask Field'),'KC-SYS','View Active Protocol Types','Can view all active protocol types','Y')
/
commit
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID,OBJ_ID,VER_NBR,ROLE_ID,PERM_ID,ACTV_IND) VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),UUID(),'1',(SELECT ROLE_ID FROM KRIM_ROLE_T RT WHERE RT.ROLE_NM = 'IRB Administrator'),(SELECT PERM_ID FROM KRIM_PERM_T PT WHERE PT.NM = 'View Active Protocol Types' AND PT.NMSPC_CD = 'KC-SYS'),'Y')
/
commit
/
DELIMITER ;
