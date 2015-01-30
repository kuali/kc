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

UPDATE KRIM_PERM_T set NM = 'Submit IACUC Protocol' WHERE NM = 'Submit IACUC  protocol for Review'
/
UPDATE KRIM_PERM_T SET DESC_TXT='Submit an IACUC Protocol to committee for review' WHERE NM = 'Submit IACUC Protocol'
/
UPDATE KRIM_PERM_T set NM = 'Add IACUC Protocol Notes' WHERE NM = 'Add IACUC  Protocol Notes'
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','IACUC Administrator',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy'),SYSDATE,'KC-UNT',SYS_GUID(),KRIM_ROLE_ID_BS_S.NEXTVAL,'IACUC Administrator',1)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
  VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,KRIM_ROLE_ID_BS_S.CURRVAL,(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify IACUC Protocol'),'Y',SYS_GUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
  VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,KRIM_ROLE_ID_BS_S.CURRVAL,(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Submit IACUC Protocol'),'Y',SYS_GUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
  VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,KRIM_ROLE_ID_BS_S.CURRVAL,(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create IACUC Protocol'),'Y',SYS_GUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
  VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,KRIM_ROLE_ID_BS_S.CURRVAL,(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Add IACUC Protocol Notes'),'Y',SYS_GUID(),1)
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','IACUC Reviewer',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy'),SYSDATE,'KC-UNT',SYS_GUID(),KRIM_ROLE_ID_BS_S.NEXTVAL,'IACUC Reviewer',1)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
  VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Protocol Viewer' AND NMSPC_CD='KC-IACUC'),
    (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View IACUC Protocol'),'Y',SYS_GUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
  VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Administrator' AND NMSPC_CD='KC-UNT'),
    (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View IACUC Protocol'),'Y',SYS_GUID(),1)
/
