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

@./rice_server/bootstrap/V300_002__schema.sql
@./rice_server/bootstrap/V300_003__KR_COUNTRY_T.sql
@./rice_server/bootstrap/V300_004__KR_STATE_T.sql
@./rice_server/bootstrap/V300_005__KREN_CHNL_T.sql
@./rice_server/bootstrap/V300_006__KREN_CNTNT_TYP_T.sql
@./rice_server/bootstrap/V300_007__KREN_PRIO_T.sql
@./rice_server/bootstrap/V300_008__KREW_DOC_TYP_ATTR_T.sql
@./rice_server/bootstrap/V300_009__KREW_DOC_TYP_PLCY_RELN_T.sql
@./rice_server/bootstrap/V300_010__KREW_DOC_TYP_PROC_T.sql
@./rice_server/bootstrap/V300_011__KREW_DOC_TYP_T.sql
@./rice_server/bootstrap/V300_012__KREW_RTE_NODE_CFG_PARM_T.sql
@./rice_server/bootstrap/V300_013__KREW_RTE_NODE_LNK_T.sql
@./rice_server/bootstrap/V300_014__KREW_RTE_NODE_T.sql
@./rice_server/bootstrap/V300_015__KREW_RULE_ATTR_T.sql
@./rice_server/bootstrap/V300_016__KREW_RULE_EXT_T.sql
@./rice_server/bootstrap/V300_017__KREW_RULE_EXT_VAL_T.sql
@./rice_server/bootstrap/V300_018__KREW_RULE_RSP_T.sql
@./rice_server/bootstrap/V300_019__KREW_RULE_T.sql
@./rice_server/bootstrap/V300_020__KREW_RULE_TMPL_ATTR_T.sql
@./rice_server/bootstrap/V300_021__KREW_RULE_TMPL_T.sql
@./rice_server/bootstrap/V300_022__KRIM_ADDR_TYP_T.sql
@./rice_server/bootstrap/V300_023__KRIM_AFLTN_TYP_T.sql
@./rice_server/bootstrap/V300_024__KRIM_ATTR_DEFN_T.sql
@./rice_server/bootstrap/V300_025__KRIM_EMAIL_TYP_T.sql
@./rice_server/bootstrap/V300_026__KRIM_EMP_STAT_T.sql
@./rice_server/bootstrap/V300_027__KRIM_EMP_TYP_T.sql
@./rice_server/bootstrap/V300_028__KRIM_ENT_NM_TYP_T.sql
@./rice_server/bootstrap/V300_029__KRIM_ENT_TYP_T.sql
@./rice_server/bootstrap/V300_030__KRIM_ENTITY_EMAIL_T.sql
@./rice_server/bootstrap/V300_031__KRIM_ENTITY_ENT_TYP_T.sql
@./rice_server/bootstrap/V300_032__KRIM_ENTITY_NM_T.sql
@./rice_server/bootstrap/V300_033__KRIM_ENTITY_T.sql
@./rice_server/bootstrap/V300_034__KRIM_EXT_ID_TYP_T.sql
@./rice_server/bootstrap/V300_035__KRIM_GRP_MBR_T.sql
@./rice_server/bootstrap/V300_036__KRIM_GRP_T.sql
@./rice_server/bootstrap/V300_037__KRIM_PERM_ATTR_DATA_T.sql
@./rice_server/bootstrap/V300_038__KRIM_PERM_T.sql
@./rice_server/bootstrap/V300_039__KRIM_PERM_TMPL_T.sql
@./rice_server/bootstrap/V300_040__KRIM_PHONE_TYP_T.sql
@./rice_server/bootstrap/V300_041__KRIM_PRNCPL_T.sql
@./rice_server/bootstrap/V300_042__KRIM_ROLE_MBR_T.sql
@./rice_server/bootstrap/V300_043__KRIM_ROLE_PERM_T.sql
@./rice_server/bootstrap/V300_044__KRIM_ROLE_T.sql
@./rice_server/bootstrap/V300_045__KRIM_RSP_TMPL_T.sql
@./rice_server/bootstrap/V300_046__KRIM_TYP_ATTR_T.sql
@./rice_server/bootstrap/V300_047__KRIM_TYP_T.sql
@./rice_server/bootstrap/V300_048__KRNS_CAMPUS_T.sql
@./rice_server/bootstrap/V300_049__KRNS_CMP_TYP_T.sql
@./rice_server/bootstrap/V300_050__KRNS_NMSPC_T.sql
@./rice_server/bootstrap/V300_051__KRNS_NTE_TYP_T.sql
@./rice_server/bootstrap/V300_052__KRNS_PARM_DTL_TYP_T.sql
@./rice_server/bootstrap/V300_053__KRNS_PARM_T.sql
@./rice_server/bootstrap/V300_054__KRNS_PARM_TYP_T.sql
@./rice_server/bootstrap/V300_055__KRSB_MSG_PYLD_T.sql
@./rice_server/bootstrap/V300_056__KRSB_MSG_QUE_T.sql
@./rice_server/bootstrap/V300_057__KRSB_QRTZ_LOCKS.sql
@./rice_server/bootstrap/V300_058__schema-constraints.sql
@./rice_server/bootstrap/V300_059__KR_01_KREN_CHNL_T.sql
@./rice_server/bootstrap/V300_060__KR_01_KREN_PRODCR_T.sql
@./rice_server/bootstrap/V300_061__KR_01_KRIM_AFLTN_TYP_T.sql
@./rice_server/bootstrap/V300_062__KR_01_KRIM_ATTR_DEFN_T.sql
@./rice_server/bootstrap/V300_063__KR_01_KRIM_RSP_T.sql
@./rice_server/bootstrap/V300_064__KR_01_KRIM_TYP_T.sql
@./rice_server/bootstrap/V300_065__KR_01_KRNS_NMSPC_T.sql
@./rice_server/bootstrap/V300_066__KR_01_KRNS_PARM_DTL_TYP_T.sql
@./rice_server/bootstrap/V300_067__KR_01_KRNS_PARM_T.sql
@./rice_server/bootstrap/V300_068__KR_02_KREN_CHNL_PRODCR_T.sql
@./rice_server/bootstrap/V300_069__KR_02_KRIM_GRP_T.sql
@./rice_server/bootstrap/V300_070__KR_02_KRIM_PERM_TMPL_T.sql
@./rice_server/bootstrap/V300_071__KR_02_KRIM_ROLE_T.sql
@./rice_server/bootstrap/V300_072__KR_02_KRIM_TYP_ATTR_T.sql
@./rice_server/bootstrap/V300_073__KR_03_KRIM_GRP_MBR_T.sql
@./rice_server/bootstrap/V300_074__KR_03_KRIM_PERM_T.sql
@./rice_server/bootstrap/V300_075__KR_03_KRIM_ROLE_MBR_T.sql
@./rice_server/bootstrap/V300_076__KR_03_KRIM_ROLE_RSP_T.sql
@./rice_server/bootstrap/V300_077__KR_04_KRIM_ROLE_PERM_T.sql
@./rice_server/bootstrap/V300_078__KR_05_KREW_DOC_TYP_T.sql
@./rice_server/bootstrap/V300_079__KR_06_KREW_DOC_TYP_PLCY_RELN_T.sql
@./rice_server/bootstrap/V300_080__KR_06_KREW_RTE_NODE_T.sql
@./rice_server/bootstrap/V300_081__KR_07_KREW_DOC_TYP_PROC_T.sql
@./rice_server/bootstrap/V300_082__KR_07_KREW_RTE_NODE_CFG_PARM_T.sql
commit;
