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

set define off 
set sqlblanklines on 
spool KR-Release-3_0_1-3_1_S1-Upgrade-Oracle-Install.log
@ORACLE/DML/DML_BS_OBJ_ID_CLEAN.sql
@ORACLE/DML/DML_BS1_KREN_PRODCR_T.sql
@ORACLE/DML/DML_BS2_KRNS_PARM_DTL_TYP_T.sql
@ORACLE/DML/DML_BS2_KRNS_PARM_T.sql
@ORACLE/DML/DML_BS3_KRIM_PERM_T.sql
@ORACLE/DML/DML_BS4_KRIM_PERM_ATTR_DATA_T.sql
@ORACLE/DML/DML_BS4_KRIM_ROLE_PERM_T.sql
@ORACLE/DML/DML_BS5_KREW_RULE_RSP_T.sql
@ORACLE/DML/DML_BS5_KREW_RULE_T.SQL.sql
@ORACLE/DML/DML_BS5_KRIM_APP_ADMIN.sql
@ORACLE/DML/DML_BS5_KRIM_GROUP_MBR_T.sql
@ORACLE/DML/DML_BS7_KRNS_PARM_T.sql
commit;
exit;
