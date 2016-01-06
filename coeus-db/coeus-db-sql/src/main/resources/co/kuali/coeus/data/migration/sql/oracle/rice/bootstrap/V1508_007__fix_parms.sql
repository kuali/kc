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

DECLARE
  parmcnt number;
BEGIN
  select count(*) into parmcnt from krcr_parm_t where APPL_ID = 'KC' and NMSPC_CD = 'KC-GEN' and PARM_NM = 'POST_DOCTORAL_COSTELEMENT' and CMPNT_CD = 'All';
  if parmcnt = 0 THEN
    update krcr_parm_t set CMPNT_CD = 'All' where APPL_ID = 'KC' and NMSPC_CD = 'KC-GEN' and PARM_NM = 'POST_DOCTORAL_COSTELEMENT' and CMPNT_CD = 'A';
  ELSE
    delete from krcr_parm_t where APPL_ID = 'KC' and NMSPC_CD = 'KC-GEN' and PARM_NM = 'POST_DOCTORAL_COSTELEMENT' and CMPNT_CD = 'A';
  END IF;
END;
/

insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KC-AB', 'All', 'All', 'Y', SYS_GUID(), 1);
insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KC-IP', 'All', 'All', 'Y', SYS_GUID(), 1);
insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KC-T', 'All', 'All', 'Y', SYS_GUID(), 1);
insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KC-AWARD', 'All', 'All', 'Y', SYS_GUID(), 1);
insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KC-SYS', 'All', 'All', 'Y', SYS_GUID(), 1);
insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KC-WKFLW', 'All', 'All', 'Y', SYS_GUID(), 1);
insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KR-LOC', 'All', 'All', 'Y', SYS_GUID(), 1);
insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KC-ADM', 'All', 'All', 'Y', SYS_GUID(), 1);
insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KC-PROTOCOL', 'All', 'All', 'Y', SYS_GUID(), 1);
insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KC-UNT', 'All', 'All', 'Y', SYS_GUID(), 1);
insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KR-KRAD', 'All', 'All', 'Y', SYS_GUID(), 1);
insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KC-COMMITTEE', 'Document', 'Document', 'Y', SYS_GUID(), 1);
insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KC-UNT', 'Document', 'Document', 'Y', SYS_GUID(), 1);
insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KC-QUESTIONNAIRE', 'Document', 'Document', 'Y', SYS_GUID(), 1);
insert into krcr_cmpnt_t (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KR-IDM', 'PersonDocumentName', 'PersonDocumentName', 'Y', SYS_GUID(), 1);
