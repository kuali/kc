--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
VALUES ('Y', 'org.kuali.kra.kim.bo.KcKimAttributes', 'KC' || KRIM_ATTR_DEFN_ID_S.nextval, 'boClass', 'KC-SYS', sys_guid(), 1);

INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES('KC' || KRIM_TYP_ID_S.nextval, sys_guid(), 1, 'Class Name', 'classNameTypeService', 'Y', 'KC-SYS');

INSERT INTO krim_perm_tmpl_t (perm_tmpl_id, obj_id, ver_nbr, nmspc_cd, nm, desc_txt, kim_typ_id, actv_ind)
VALUES ('KC' || krim_perm_tmpl_id_s.nextval, sys_guid(), '1', 'KC-SYS', 'Read Class', 'Ability to read the data mapped to a class.', (SELECT kim_typ_id FROM krim_typ_t WHERE nmspc_cd = 'KC-SYS' AND nm = 'Class Name'), 'Y');

INSERT INTO krim_perm_tmpl_t (perm_tmpl_id, obj_id, ver_nbr, nmspc_cd, nm, desc_txt, kim_typ_id, actv_ind)
VALUES ('KC' || krim_perm_tmpl_id_s.nextval, sys_guid(), '1', 'KC-SYS', 'Write Class', 'Ability to write the data mapped to a class.', (SELECT kim_typ_id FROM krim_typ_t WHERE nmspc_cd = 'KC-SYS' AND nm = 'Class Name'), 'Y');

INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ('KC' || KRIM_PERM_ID_S.nextval,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Read Class'),'KC-SYS','Read All','Read all data in the KC system','Y',sys_guid(),1);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR)
VALUES ('KC' || KRIM_ATTR_DATA_ID_S.nextval,(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Read All'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Class Name'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'boClass'),'*',sys_guid(),1);

INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ('KC' || KRIM_PERM_ID_S.nextval,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Write Class'),'KC-SYS','Write All','Write all data in the KC system','Y',sys_guid(),1);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR)
VALUES ('KC' || KRIM_ATTR_DATA_ID_S.nextval,(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Write All'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Class Name'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'boClass'),'*',sys_guid(),1);

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ('KC' || KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'KC Superuser' AND NMSPC_CD='KC-SYS'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Read All' AND NMSPC_CD='KC-SYS'), 'Y');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ('KC' || KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'KC Superuser' AND NMSPC_CD='KC-SYS'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Write All' AND NMSPC_CD='KC-SYS'), 'Y');
