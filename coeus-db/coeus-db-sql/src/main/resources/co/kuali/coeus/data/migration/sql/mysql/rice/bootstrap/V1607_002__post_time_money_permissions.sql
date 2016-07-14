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

INSERT INTO KRCR_PARM_T(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
values ('KC-T','All','TM_POST_ENABLED',UUID(),1,'CONFG','false',
'This parameter is used to enable / disable the T&M post feature.','A','KC');

update KRCR_PARM_T set PARM_NM = 'AWARD_POST_ENABLED' where PARM_NM = 'ENABLE_FINANCIAL_REST_API' and nmspc_cd = 'KC-AWARD';

update KRCR_PARM_T set  PARM_DESC_TXT = 'True will enable the Award Post History tab in Award. In conjunction with the API and the permission Post an Award, it will allow user to create account or post after account has already been created.'
where PARM_NM = 'AWARD_POST_ENABLED';

INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
    VALUES ('RES-BOOT1002',
    (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
    'KC-SYS','Post Time and Money','Post Time and Money information to the account table.','Y',UUID(),1);

insert into KRIM_ROLE_PERM_T values ('RES-BOOT1003', UUID(), 1,
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'KC Superuser'),
'RES-BOOT1002', 'Y');

insert into KRIM_ROLE_PERM_T values ('RES-BOOT1004', UUID(), 1,
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'OSP Administrator'),
'RES-BOOT1002', 'Y');
