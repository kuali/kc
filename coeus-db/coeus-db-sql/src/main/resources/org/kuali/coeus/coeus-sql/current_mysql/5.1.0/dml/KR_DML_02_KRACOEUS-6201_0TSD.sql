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

-- create the coimaintainer principal
INSERT INTO KRIM_ENTITY_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ENTITY_T (ENTITY_ID, ACTV_IND, LAST_UPDT_DT,OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ENTITY_ID_S), 'Y', NOW(), UUID(), 1)
/

INSERT INTO KRIM_ENTITY_NM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ENTITY_NM_T (ENTITY_NM_ID, ENTITY_ID, FIRST_NM, LAST_NM, NM_TYP_CD, DFLT_IND, ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ENTITY_NM_ID_S), (SELECT (MAX(ID)) FROM KRIM_ENTITY_ID_S), 'COI', 'Maintainer', 'PRFR', 'Y', 'Y', NOW(), UUID(), 1)
/

INSERT INTO KRIM_PRNCPL_ID_S VALUES(NULL)
/
INSERT INTO KRIM_PRNCPL_T (PRNCPL_ID, ENTITY_ID, PRNCPL_NM, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_PRNCPL_ID_S), (SELECT (MAX(ID)) FROM KRIM_ENTITY_ID_S), 'coimaintainer', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', NOW(), UUID(), 1)
/

INSERT INTO KRIM_ENTITY_ENT_TYP_T (ENTITY_ID, ENT_TYP_CD, ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ENTITY_ID_S), 'PERSON', 'Y', NOW(), UUID(), 1)
/

INSERT INTO KRIM_ENTITY_EMAIL_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ENTITY_EMAIL_T (ENTITY_EMAIL_ID, ENTITY_ID, ENT_TYP_CD, EMAIL_TYP_CD, EMAIL_ADDR, DFLT_IND, ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ENTITY_EMAIL_ID_S), (SELECT (MAX(ID)) FROM KRIM_ENTITY_ID_S), 'PERSON', 'WRK', 'coiMaintainer@kuali.org', 'Y', 'Y', NOW(), UUID(), 1)
/

INSERT INTO KRIM_ENTITY_EMP_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ENTITY_EMP_INFO_T (ENTITY_EMP_ID, ENTITY_ID, EMP_REC_ID, EMP_ID, EMP_STAT_CD, EMP_TYP_CD, BASE_SLRY_AMT, PRMRY_DEPT_CD, PRMRY_IND, ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ENTITY_EMP_ID_S), (SELECT (MAX(ID)) FROM KRIM_ENTITY_ID_S), '1', 'coimaintainer', 'A', 'P', 100000, '000001', 'Y','Y', NOW(), UUID(), 1)
/



-- add the principal to the kc admin group so the principal is in the blanket approve workgroup for coi maint docs
INSERT INTO KRIM_GRP_MBR_ID_S VALUES(NULL)
/
INSERT INTO KRIM_GRP_MBR_T (GRP_MBR_ID, GRP_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
       VALUES ((SELECT (MAX(ID)) FROM KRIM_GRP_MBR_ID_S), (SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KC-WKFLW' AND GRP_NM = 'KcAdmin'), (SELECT (MAX(ID)) FROM KRIM_PRNCPL_ID_S), 'P', NOW(), UUID(), 1)
/

-- assign the Coi Maintainer role to the principal
INSERT INTO KRIM_ROLE_MBR_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_MBR_ID_S), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'COI Maintainer' AND NMSPC_CD='KC-COIDISCLOSURE'), (SELECT (MAX(ID)) FROM KRIM_PRNCPL_ID_S), 'P', NOW(), UUID(), 1)
/

-- assign the Coi Maintainer role to coiadmin
INSERT INTO KRIM_ROLE_MBR_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID,ROLE_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_MBR_ID_S), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'COI Maintainer' AND NMSPC_CD='KC-COIDISCLOSURE'), 
            (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'coiadmin'), 'P', NOW(), UUID(), 1)
/

-- assign the Coi Maintainer role to quickstart
INSERT INTO KRIM_ROLE_MBR_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID,ROLE_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_MBR_ID_S), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'COI Maintainer' AND NMSPC_CD='KC-COIDISCLOSURE'), 
            (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', NOW(), UUID(), 1)
/

DELIMITER ;
