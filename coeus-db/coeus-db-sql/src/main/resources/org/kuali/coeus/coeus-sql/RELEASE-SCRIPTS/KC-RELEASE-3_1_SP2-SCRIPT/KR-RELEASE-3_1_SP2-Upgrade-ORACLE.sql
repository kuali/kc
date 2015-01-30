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
spool KR-RELEASE-3_1_SP2-Upgrade-ORACLE-Install.log
@ORACLE/DML/KR_DML_BS1_KRIM_ID_CLEAN.sql
@ORACLE/DML/KR_DML_BS2_KRIM_ID_CLEAN.sql
@ORACLE/DML/KR_DML_BS3_KRIM_PERM_T.sql
@ORACLE/DML/KR_DML_BS4_KRIM_PERM_ATTR_DATA_T.sql
@ORACLE/DML/KR_DML_BS4_KRIM_ROLE_T.sql
@ORACLE/DML/KR_DML_BS5_KRIM_ROLE_PERM_T.sql
@ORACLE/DML/KR_DML_BS7_KREW_DOC_HDR_T.sql
@ORACLE/DML/KR_DML_BS7_KRNS_PARM_T.sql
@ORACLE/DML/KR_DML_BS8_KREW_ACTN_TKN_T.sql
@ORACLE/DML/KR_DML_BS8_KREW_DOC_HDR_CNTNT_T.sql
@ORACLE/DML/KR_DML_BS8_KREW_RTE_NODE_INSTN_T.sql
@ORACLE/DML/KR_DML_BS9_KREW_ACTN_RQST_T.sql
commit;
exit;
