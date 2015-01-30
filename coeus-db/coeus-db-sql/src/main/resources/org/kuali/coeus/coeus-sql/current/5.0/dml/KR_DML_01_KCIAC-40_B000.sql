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

-- View permission 
INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES (KRIM_PERM_ID_BS_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'),'KC-IACUC','View IACUCCommittee','View all IACUC committees in a unit','Y',SYS_GUID(),1)
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Section'), (SELECT KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T WHERE NM = 'sectionName'), 'committee', SYS_GUID())
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Section'), (SELECT KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName'), 'CommitteeDocument', SYS_GUID())
/
-- Modify permission    
INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'), 'KC-IACUC', 'Modify IACUCCommittee', 'Modify existing committees in a unit', 'Y', SYS_GUID())
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Section'), (SELECT KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T WHERE NM = 'sectionName'), 'committee', SYS_GUID())
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Section'), (SELECT KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName'), 'CommitteeDocument', SYS_GUID())
/
-- Perform actions
INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'), 'KC-IACUC', 'Perform IACUCCommittee Actions', 'Perform IACUC committee actions', 'Y', SYS_GUID())
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName'), 'CommitteeDocument', SYS_GUID())
/
-- ROLES 
-- Active IACUC Committee Member 
INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
  VALUES( KRIM_TYP_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Derived Role: Active IACUC Committee Member', 'activeIacucCommitteeMemberDerivedRoleTypeService', 'Y', 'KC-IACUC')
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Active IACUC Committee Member', 'KC-IACUC', 'Role members are derived from active committee members.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/
-- Active IACUC Committee Member on Scheduled Date 
INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
  VALUES( KRIM_TYP_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Derived Role: Active IACUC Committee Member on Scheduled Date', 'activeIacucCommitteeMemberOnScheduledDateDerivedRoleTypeService', 'Y', 'KC-IACUC')
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Active IACUC Committee Member on Scheduled Date', 'KC-IACUC', 'Role members are derived from active committee members on scheduled date.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/
-- Active IACUC Committee Member On Protocol
INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
  VALUES( KRIM_TYP_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Derived Role: Active IACUC Committee Member on Protocol', 'activeIacucCommitteeMemberOnProtocolDerivedRoleTypeService', 'Y', 'KC-IACUC')
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Active IACUC Committee Member on Protocol', 'KC-IACUC', 'Role members are derived from active committee members on protocol.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/
-- Assinging all the permissions to IACUC Admin
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, OBJ_ID, VER_NBR, ACTV_IND) 
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Administrator'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View IACUCCommittee'), SYS_GUID(), '1', 'Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, OBJ_ID, VER_NBR, ACTV_IND) 
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Administrator'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify IACUCCommittee'), SYS_GUID(), '1', 'Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, OBJ_ID, VER_NBR, ACTV_IND) 
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Administrator'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Perform IACUCCommittee Actions'), SYS_GUID(), '1', 'Y')
/



 
