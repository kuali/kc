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

delete from krim_entity_emp_info_t where entity_id = (select ENTITY_ID from krim_prncpl_t where prncpl_nm = 'admin');
delete from krim_entity_afltn_t where entity_id = (select ENTITY_ID from krim_prncpl_t where prncpl_nm = 'admin');
insert into krim_entity_afltn_t (ENTITY_AFLTN_ID, ENTITY_ID, AFLTN_TYP_CD, CAMPUS_CD, DFLT_IND, ACTV_IND, OBJ_ID, VER_NBR, LAST_UPDT_DT)
	values ('RES-GRMONLY1000', (select ENTITY_ID from krim_prncpl_t where prncpl_nm = 'admin'), 'STAFF', 'UN', 'Y', 'Y', uuid(), 1, now());

insert into krim_entity_emp_info_t (ENTITY_EMP_ID, ENTITY_ID, ENTITY_AFLTN_ID, EMP_STAT_CD, EMP_TYP_CD, BASE_SLRY_AMT, PRMRY_IND, ACTV_IND, LAST_UPDT_DT, PRMRY_DEPT_CD, EMP_ID, EMP_REC_ID, OBJ_ID, VER_NBR)
	values ('RES-GRMONLY1000', (select ENTITY_ID from krim_prncpl_t where prncpl_nm = 'admin'), 'RES-GRMONLY1000', 'A', 'P', 0, 'Y', 'Y', now(), '000001', 'RES-GRMONLY1', 'RES-GRMONLY1', uuid(), 1);