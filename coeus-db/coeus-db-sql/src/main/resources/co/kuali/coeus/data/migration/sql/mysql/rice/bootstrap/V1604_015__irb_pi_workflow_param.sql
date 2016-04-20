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

insert into KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
values ('KC-PROTOCOL','Document','ASSIGN_PRINCIPAL_INVESTIGATOR_TO_WORKFLOW',UUID(),1,'CONFG','Y',
  'Determines whether the PI of the IRB Protocol is assigned to the IRB Approvers role for the individual document to accomodate appropriate KEW actions, such as delete, cancel and disapprove. Disabling may cause the PI to receive errors when performing these actions.','A','KC');

insert into KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
values ('KC-IACUC','Document','ASSIGN_PRINCIPAL_INVESTIGATOR_TO_WORKFLOW',UUID(),1,'CONFG','Y',
  'Determines whether the PI of the IACUC Protocol is assigned to the IACUC Protocol Approvers role for the individual document to accomodate appropriate KEW actions, such as delete, cancel and disapprove. Disabling may cause the PI to receive errors when performing these actions.','A','KC');
