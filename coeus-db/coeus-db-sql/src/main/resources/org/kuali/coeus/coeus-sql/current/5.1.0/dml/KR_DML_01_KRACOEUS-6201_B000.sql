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

-- create the coi maintainer role with a default kim type
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT) 
	VALUES (KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'COI Maintainer', 'KC-COIDISCLOSURE', 'Grants the ability to maintain all sorts of COI-related artifacts', 
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Default'), 'Y', SYSDATE)
/



-- add the 'initiate document' template-based permission for COI attachment type maintenance documents
-- and confer this permission on the coi maintainer role

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) 
	VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD ='KR-SYS' AND NM ='Initiate Document'), 
			'KC-COIDISCLOSURE', 'Add COI Attachment Type', 'Permission to create new COI attachment types', 'Y', SYS_GUID())
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
	VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, 
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
			'CoiAttachmentTypeMaintenanceDocument', SYS_GUID())
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, KRIM_PERM_ID_BS_S.CURRVAL, 'Y')
/



-- add the 'initiate document' template-based permission for COI disclosure status maintenance documents
-- and confer this permission on the coi maintainer role

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) 
	VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD ='KR-SYS' AND NM ='Initiate Document'), 
			'KC-COIDISCLOSURE', 'Add COI Disclosure Status', 'Permission to create new COI disclosure statuses', 'Y', SYS_GUID())
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
	VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, 
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
			'CoiDisclosureStatusMaintenanceDocument', SYS_GUID())
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, KRIM_PERM_ID_BS_S.CURRVAL, 'Y')
/



-- add the 'initiate document' template-based permission for COI disposition status maintenance documents
-- and confer this permission on the coi maintainer role

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) 
	VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD ='KR-SYS' AND NM ='Initiate Document'), 
			'KC-COIDISCLOSURE', 'Add COI Disposition Status', 'Permission to create new COI disposition statuses', 'Y', SYS_GUID())
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
	VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, 
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
			'CoiDispositionStatusMaintenanceDocument', SYS_GUID())
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, KRIM_PERM_ID_BS_S.CURRVAL, 'Y')
/



-- add the 'initiate document' template-based permission for COI committee role type maintenance documents
-- and confer this permission on the coi maintainer role

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) 
	VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD ='KR-SYS' AND NM ='Initiate Document'), 
			'KC-COIDISCLOSURE', 'Add COI Committee Role Type', 'Permission to create new COI committee role types', 'Y', SYS_GUID())
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
	VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, 
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
			'CoiCommitteeRoleTypeMaintenanceDocument', SYS_GUID())
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, KRIM_PERM_ID_BS_S.CURRVAL, 'Y')
/



-- add the 'initiate document' template-based permission for COI disclosure event type maintenance documents
-- and confer this permission on the coi maintainer role

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) 
	VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD ='KR-SYS' AND NM ='Initiate Document'), 
			'KC-COIDISCLOSURE', 'Add COI Disclosure Event Type', 'Permission to create new COI disclosure event types', 'Y', SYS_GUID())
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
	VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, 
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
			'CoiDisclosureEventTypeMaintenanceDocument', SYS_GUID())
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, KRIM_PERM_ID_BS_S.CURRVAL, 'Y')
/



-- add the 'initiate document' template-based permission for COI review type maintenance documents
-- and confer this permission on the coi maintainer role

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) 
	VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD ='KR-SYS' AND NM ='Initiate Document'), 
			'KC-COIDISCLOSURE', 'Add COI Review Type', 'Permission to create new COI review types', 'Y', SYS_GUID())
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
	VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, 
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
			'CoiReviewTypeMaintenanceDocument', SYS_GUID())
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, KRIM_PERM_ID_BS_S.CURRVAL, 'Y')
/



-- add the 'initiate document' template-based permission for COI reviewer maintenance documents
-- and confer this permission on the coi maintainer role

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) 
	VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD ='KR-SYS' AND NM ='Initiate Document'), 
			'KC-COIDISCLOSURE', 'Add COI Reviewer', 'Permission to create new COI reviewers', 'Y', SYS_GUID())
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
	VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, 
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
			'CoiReviewerMaintenanceDocument', SYS_GUID())
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, KRIM_PERM_ID_BS_S.CURRVAL, 'Y')
/


-- add the 'initiate document' template-based permission for COI status maintenance documents
-- and confer this permission on the coi maintainer role

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) 
	VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD ='KR-SYS' AND NM ='Initiate Document'), 
			'KC-COIDISCLOSURE', 'Add COI Status', 'Permission to create new COI statuses', 'Y', SYS_GUID())
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
	VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, 
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
			'CoiStatusMaintenanceDocument', SYS_GUID())
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, KRIM_PERM_ID_BS_S.CURRVAL, 'Y')
/


-- add the 'initiate document' template-based permission for financial interest entity relationship type maintenance documents
-- and confer this permission on the coi maintainer role

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) 
	VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD ='KR-SYS' AND NM ='Initiate Document'), 
			'KC-COIDISCLOSURE', 'Add Financial Interest Entity Relationship Type', 'Permission to create new financial interest entity relationship types', 'Y', SYS_GUID())
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
	VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, 
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
			'FinIntEntityRelTypeMaintenanceDocument', SYS_GUID())
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, KRIM_PERM_ID_BS_S.CURRVAL, 'Y')
/


-- add the 'initiate document' template-based permission for financial interest entity status maintenance documents
-- and confer this permission on the coi maintainer role

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) 
	VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD ='KR-SYS' AND NM ='Initiate Document'), 
			'KC-COIDISCLOSURE', 'Add Financial Interest Entity Status', 'Permission to create new financial interest entity statuses', 'Y', SYS_GUID())
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
	VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, 
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
			'FinIntEntityStatusMaintenanceDocument', SYS_GUID())
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, KRIM_PERM_ID_BS_S.CURRVAL, 'Y')
/


-- add the 'initiate document' template-based permission for financial entity data group maintenance documents
-- and confer this permission on the coi maintainer role

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) 
	VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD ='KR-SYS' AND NM ='Initiate Document'), 
			'KC-COIDISCLOSURE', 'Add Financial Entity Data Group', 'Permission to create new financial entity data groups', 'Y', SYS_GUID())
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
	VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, 
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
			'FinEntitiesDataGroupMaintenanceDocument', SYS_GUID())
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, KRIM_PERM_ID_BS_S.CURRVAL, 'Y')
/


-- add the 'initiate document' template-based permission for financial entity data matrix maintenance documents
-- and confer this permission on the coi maintainer role

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) 
	VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD ='KR-SYS' AND NM ='Initiate Document'), 
			'KC-COIDISCLOSURE', 'Add Financial Entity Data Matrix', 'Permission to create new financial entity data matrices', 'Y', SYS_GUID())
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
	VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_PERM_ID_BS_S.CURRVAL, 
			(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
			(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
			'FinEntitiesDataMatrixMaintenanceDocument', SYS_GUID())
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, KRIM_PERM_ID_BS_S.CURRVAL, 'Y')
/

