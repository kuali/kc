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

-- delete existing permissions of Application Administrator
DELETE FROM KRIM_ROLE_PERM_T
WHERE ROLE_ID = (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-AWARD')
/
-- change the name space of Application Administrator to KC-SYS
UPDATE KRIM_ROLE_T
SET NMSPC_CD = 'KC-SYS'
WHERE ROLE_NM = 'Application Administrator' AND NMSPC_CD = 'KC-AWARD'
/

-- assign Blanket Approve Document permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),(select PERM_ID from KRIM_PERM_T where NM='Blanket Approve Document' and nmspc_cd = 'KC-SYS'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
-- assign Save Document to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),(select PERM_ID from KRIM_PERM_T where NM='Save Document' and nmspc_cd = 'KC-SYS'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
-- assign Delete Note / Attachment to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),(select PERM_ID from KRIM_PERM_T where NM='Delete Note / Attachment' and nmspc_cd = 'KC-SYS'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
-- assign Initiate Document to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Initiate Simple Document' and nmspc_cd = 'KC-SYS' and desc_txt = 'Authorizes the initiation of KC Simple Maintenance documents.'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
-- assign Assign Role to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Assign Role' and nmspc_cd = 'KC-SYS'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
-- assign Grant Permission to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Grant Permission' and nmspc_cd = 'KC-SYS'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
-- assign Grant Responsibility to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Grant Responsibility' and nmspc_cd = 'KC-SYS'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
-- assign Maintain System Parameter to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Maintain System Parameter' and nmspc_cd = 'KC-SYS'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
-- assign Maintain System Parameter to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Maintain System Parameter' and nmspc_cd = 'KR-SYS'), 'Y')
/
-- assign Full Unmask Field to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Full Unmask Tax Identification Number Payee ACH Document' and nmspc_cd = 'KR-SYS' and desc_txt = 'Authorizes users to view the entire Tax Identification Number on the Payee ACH document and Inquiry.'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
-- assign View Other Action List to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='View Other Action List' and nmspc_cd = 'KR-WKFLW' and desc_txt = 'Authorizes users to access other users action lists via the Help Desk Action List Login.'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
-- assign Unrestricted Document Search to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Unrestricted Document Search' and nmspc_cd = 'KR-WKFLW'), 'Y')
/
-- assign Full Unmask Field to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Full Unmask Tax Identification Number Person Document' and nmspc_cd = 'KR-SYS' and desc_txt = 'Authorizes users to view the entire Tax Identification Number on the Person document and inquiry.'), 'Y')
/
-- assign Initiate Document to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Initiate RICE Document' and nmspc_cd = 'KR-SYS'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
-- assign Blanket Approve ProposalDevelopmentDocument to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Blanket Approve ProposalDevelopmentDocument' and nmspc_cd = 'KC-PD'), 'Y')
/
-- assign Blanket Approve ProtocolDocument to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Blanket Approve ProtocolDocument' and nmspc_cd = 'KC-PROTOCOL'), 'Y')
/
-- assign Blanket Approve CommitteeDocument to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Blanket Approve CommitteeDocument' and nmspc_cd = 'KC-PROTOCOL'), 'Y')
/
-- assign Blanket Approve AwardDocument to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Blanket Approve AwardDocument' and nmspc_cd = 'KC-AWARD'), 'Y')
/
-- assign Blanket Approve TimeAndMoneyDocument to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Blanket Approve TimeAndMoneyDocument' and nmspc_cd = 'KC-AWARD'), 'Y')
/
-- assign administer routing for document permission to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Administer Routing for Document' and nmspc_cd = 'KC-SYS'), 'Y')
/
-- assign modify entity permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Modify Entity' and nmspc_cd = 'KR-IDM'), 'Y')
/
-- assign populate group permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Populate Group' and nmspc_cd = 'KR-SYS' and desc_txt = 'Authorizes users to modify the information on the Assignees Tab of the Group Document and the Group section of the Membership Tab on the Person Document for groups with namespaces beginning with KR.'), 'Y')
/
-- assign cancel document permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Cancel Document' and nmspc_cd = 'KUALI'), 'Y')
/
-- assign copy document permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Copy RICE Document' and nmspc_cd = 'KR-SYS'), 'Y')
/
-- assign edit document permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select MIN(PERM_ID) from KRIM_PERM_T where NM='Edit Kuali ENROUTE Document Route Status Code S' and nmspc_cd = 'KUALI'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select MIN(PERM_ID) from KRIM_PERM_T where NM='Edit Kuali ENROUTE Document Route Status Code I' and nmspc_cd = 'KUALI'), 'Y')
/
-- assign log in permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Log In Kuali Portal' and nmspc_cd = 'KUALI'), 'Y')
/
-- assign route document permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Route Document' and nmspc_cd = 'KUALI'), 'Y')
/
-- assign Populate KC Groups permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
(select PERM_ID from KRIM_PERM_T where NM='Populate KC Groups' and nmspc_cd = 'KC-SYS'), 'Y')
/

DELIMITER ;
