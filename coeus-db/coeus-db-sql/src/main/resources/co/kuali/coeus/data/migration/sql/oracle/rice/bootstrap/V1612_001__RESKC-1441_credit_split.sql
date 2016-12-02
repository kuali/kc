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

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VAL, PARM_DESC_TXT, PARM_TYP_CD, EVAL_OPRTR_CD, OBJ_ID, VER_NBR)
VALUES ('KC', 'KC-PD', 'All', 'ENABLE_OPT_IN_PERSONNEL_CREDIT_SPLIT_FUNCTIONALITY', 'N', 'This flag when enabled will give the proposal creator the ability to choose which persons to add credit split for.', 'CONFG', 'A', SYS_GUID(), 1);
