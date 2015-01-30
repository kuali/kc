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

set sqlblanklines on
set define off
spool KC-Release-3_0-Clean-Server-Oracle-Install.log
@krrelease/datasql/KR_01_KREN_CHNL_T.sql
@krrelease/datasql/KR_01_KREN_PRODCR_T.sql
@krrelease/datasql/KR_01_KRIM_AFLTN_TYP_T.sql
@krrelease/datasql/KR_01_KRIM_ATTR_DEFN_T.sql
@krrelease/datasql/KR_01_KRIM_RSP_T.sql
@krrelease/datasql/KR_01_KRIM_TYP_T.sql
@krrelease/datasql/KR_01_KRNS_NMSPC_T.sql
@krrelease/datasql/KR_01_KRNS_PARM_DTL_TYP_T.sql
@krrelease/datasql/KR_01_KRNS_PARM_T.sql
@krrelease/datasql/KR_02_KREN_CHNL_PRODCR_T.sql
@krrelease/datasql/KR_02_KRIM_GRP_T.sql
@krrelease/datasql/KR_02_KRIM_PERM_TMPL_T.sql
@krrelease/datasql/KR_02_KRIM_ROLE_T.sql
@krrelease/datasql/KR_02_KRIM_TYP_ATTR_T.sql
@krrelease/datasql/KR_03_KRIM_GRP_MBR_T.sql
@krrelease/datasql/KR_03_KRIM_PERM_T.sql
@krrelease/datasql/KR_03_KRIM_ROLE_MBR_T.sql
@krrelease/datasql/KR_03_KRIM_ROLE_RSP_T.sql
@krrelease/datasql/KR_04_KRIM_ROLE_PERM_T.sql
@krrelease/datasql/KR_05_KREW_DOC_TYP_T.sql
@krrelease/datasql/KR_06_KREW_DOC_TYP_PLCY_RELN_T.sql
@krrelease/datasql/KR_06_KREW_RTE_NODE_T.sql
@krrelease/datasql/KR_07_KREW_DOC_TYP_PROC_T.sql
@krrelease/datasql/KR_07_KREW_RTE_NODE_CFG_PARM_T.sql
