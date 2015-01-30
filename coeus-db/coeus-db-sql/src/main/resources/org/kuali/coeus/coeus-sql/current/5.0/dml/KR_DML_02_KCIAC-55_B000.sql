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

INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
  VALUES( KRIM_TYP_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Derived Role: Active Committee Member On Iacuc Protocol', 'activeCommitteeMemberOnIacucProtocolDerivedRoleTypeService', 'Y', 'KC-IACUC')
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Active Committee Member On Iacuc Protocol', 'KC-IACUC', 'Role members are derived from active committee members on the protocol.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
  VALUES(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IACUC' AND NM = 'View IACUC Protocol'),'Y')
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
  VALUES( KRIM_TYP_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Derived Role: Protocol Personnel','iacucProtocolPersonnelDerivedRoleTypeService','Y','KC-IACUC')
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'PI', 'KC-IACUC', 'The Protocol Principal Investigator role.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'COI', 'KC-IACUC', 'The Protocol Co-Investigator role.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'SP', 'KC-IACUC', 'The Protocol Study Personnel role.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'CRC', 'KC-IACUC', 'The Protocol Correspondent CRC role.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'CA', 'KC-IACUC', 'The Protocol Correspondent Administrator role.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,OBJ_ID,VER_NBR,NM,SRVC_NM,ACTV_IND,NMSPC_CD)
  VALUES( KRIM_TYP_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Derived Role: Protocol Affiliate Type','iacucProtocolAffiliateTypeDerivedRoleTypeService','Y','KC-IACUC')
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Faculty', 'KC-IACUC', 'The Faculty Affiliate role.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Non-Faculty', 'KC-IACUC', 'The Non-Faculty Affiliate role.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Affiliate', 'KC-IACUC',	'The Protocol Affiliate role.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Student Investigator', 'KC-IACUC', 'The Protocol Student Investigator role.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Faculty Supervisor', 'KC-IACUC', 'The Protocol Faculty Supervisor role.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/
