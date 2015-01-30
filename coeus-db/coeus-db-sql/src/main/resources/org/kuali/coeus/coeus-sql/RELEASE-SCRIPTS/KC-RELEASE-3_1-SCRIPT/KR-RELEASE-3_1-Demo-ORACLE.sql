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
spool KR-RELEASE-3_1-Demo-ORACLE-Install.log
@../../current/4.0/dml/KR_DML_31000_KCINFR-353_0TSD.sql
@../../current/4.0/dml/KR_DML_31001_KREN_PRODCR_T_000D.sql
@../../current/4.0/dml/KR_DML_31001_KRIM_ENTITY_T_0TSD.sql
@../../current/4.0/dml/KR_DML_31001_KRNS_CAMPUS_T_0TSD.sql
@../../current/4.0/dml/KR_DML_31002_KRIM_ENTITY_ENT_TYP_T_0TSD.sql
@../../current/4.0/dml/KR_DML_31002_KRIM_PRNCPL_T_0TSD.sql
@../../current/4.0/dml/KR_DML_31003_KREW_DOC_HDR_T_0TSD.sql
@../../current/4.0/dml/KR_DML_31003_KRIM_ENTITY_ADDR_T_0TSD.sql
@../../current/4.0/dml/KR_DML_31003_KRIM_ENTITY_AFLTN_T_0TSD.sql
@../../current/4.0/dml/KR_DML_31003_KRIM_ENTITY_EMAIL_T_0TSD.sql
@../../current/4.0/dml/KR_DML_31003_KRIM_ENTITY_EMP_INFO_T_0TSD.sql
@../../current/4.0/dml/KR_DML_31003_KRIM_ENTITY_PHONE_T_0TSD.sql
@../../current/4.0/dml/KR_DML_31004_KREW_ACTN_TKN_T_0TSD.sql
@../../current/4.0/dml/KR_DML_31004_KREW_DOC_HDR_CNTNT_T_0TSD.sql
@../../current/4.0/dml/KR_DML_31004_KRIM_GRP_MBR_T_0TSD.sql
@../../current/4.0/dml/KR_DML_31004_KRIM_ROLE_MBR_T_0TSD.sql
@../../current/4.0/dml/KR_DML_31005_KREW_ACTN_RQST_T_0TSD.sql
commit;
exit
