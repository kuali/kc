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

insert into KRIM_PERM_ID_S values(null);
insert into KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, VER_NBR, OBJ_ID)
values (concat('KC', (select (MAX(ID)) from KRIM_PERM_ID_S)),
        (select PERM_TMPL_ID from KRIM_PERM_TMPL_T where NM = 'Initiate Document' and NMSPC_CD = 'KR-SYS'),
        'KC-M','Maintain Institute Rates','Allows creating and maintaining of Institute Rates','Y',1,uuid());

insert into KRIM_ATTR_DATA_ID_S values(null);
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, VER_NBR, OBJ_ID)
values (concat('KC', (select (MAX(ID)) from KRIM_ATTR_DATA_ID_S)),
concat('KC', (select max(ID) from KRIM_PERM_ID_S)),
(select KIM_TYP_ID from KRIM_TYP_T where NM = 'Document Type (Permission)' and NMSPC_CD = 'KR-SYS'),
(select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NM = 'documentTypeName'),
'InstituteRateMaintenanceDocument',1,uuid());

insert into KRIM_ROLE_PERM_ID_S values(NULL);
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, VER_NBR, OBJ_ID)
values (CONCAT('KC', (select (MAX(ID)) from KRIM_ROLE_PERM_ID_S)),
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Technical Administrator' and NMSPC_CD = 'KR-SYS'),
concat('KC', (select max(ID) from KRIM_PERM_ID_S)),
'Y', 1, uuid());

insert into KRIM_ROLE_PERM_ID_S values(NULL);
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, VER_NBR, OBJ_ID)
values (CONCAT('KC', (select (MAX(ID)) from KRIM_ROLE_PERM_ID_S)),
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'KC Superuser' and NMSPC_CD = 'KC-SYS'),
concat('KC', (select max(ID) from KRIM_PERM_ID_S)),
'Y', 1, uuid());

insert into KRIM_ROLE_PERM_ID_S values(NULL);
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, VER_NBR, OBJ_ID)
values (CONCAT('KC', (select (MAX(ID)) from KRIM_ROLE_PERM_ID_S)),
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
concat('KC', (select max(ID) from KRIM_PERM_ID_S)),
'Y', 1, uuid());

insert into KRIM_ROLE_PERM_ID_S values(NULL);
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, VER_NBR, OBJ_ID)
values (CONCAT('KC', (select (MAX(ID)) from KRIM_ROLE_PERM_ID_S)),
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Manager' and NMSPC_CD = 'KC-SYS'),
concat('KC', (select max(ID) from KRIM_PERM_ID_S)),
'Y', 1, uuid());