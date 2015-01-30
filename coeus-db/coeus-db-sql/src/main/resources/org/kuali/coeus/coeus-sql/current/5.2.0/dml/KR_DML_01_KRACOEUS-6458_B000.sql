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

-- Add new namespace
INSERT INTO KRCR_NMSPC_T (NMSPC_CD,OBJ_ID,VER_NBR, NM, ACTV_IND, APPL_ID )
VALUES ('KC-PROTOCOL-DOC',SYS_GUID(),1, 'IRB Protocol Doc Level Nmspc' , 'Y', 'KC')
/

-- Add new (permissionless) Aggregator role for Protocol Doc namespace
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(CONCAT('KC',KRIM_ROLE_ID_S.NEXTVAL), SYS_GUID(), 1, 'Protocol Aggregator', 'KC-PROTOCOL-DOC', 'Added to Document Qualified Role memberships for corresponding Role in KC-PROTOCOL namespace', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'), 'Y', sysdate)
/

-- Add new (permissionless) Viewer role for Protocol Doc namespace
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(CONCAT('KC',KRIM_ROLE_ID_S.NEXTVAL), SYS_GUID(), 1, 'Protocol Viewer', 'KC-PROTOCOL-DOC', 'Added to Document Qualified Role memberships for corresponding Role in KC-PROTOCOL namespace', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'), 'Y', sysdate)
/

-- add all permissions from Protocol Aggregator to protocol PI derived role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
 (SELECT CONCAT('KC',KRIM_ROLE_PERM_ID_S.NEXTVAL), SYS_GUID(), 1, R.ROLE_ID, P.PERM_ID, 'Y' FROM KRIM_ROLE_T R, (SELECT RP.PERM_ID FROM KRIM_ROLE_PERM_T RP JOIN KRIM_ROLE_T R ON RP.ROLE_ID = R.ROLE_ID where R.ROLE_NM='Protocol Aggregator' and R.NMSPC_CD='KC-PROTOCOL') P WHERE R.ROLE_NM = 'PI' and NMSPC_CD='KC-PROTOCOL')
/
