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

@./rice_server/bootstrap/V310_1_002__update_final_oracle.sql
@./rice_server/bootstrap/V310_1_003__DML_BS_OBJ_ID_CLEAN.sql
@./rice_server/bootstrap/V310_1_004__DML_BS1_KREN_PRODCR_T.sql
@./rice_server/bootstrap/V310_1_005__DML_BS2_KRNS_PARM_DTL_TYP_T.sql
@./rice_server/bootstrap/V310_1_006__DML_BS2_KRNS_PARM_T.sql
@./rice_server/bootstrap/V310_1_007__DML_BS3_KRIM_PERM_T.sql
@./rice_server/bootstrap/V310_1_008__DML_BS4_KRIM_PERM_ATTR_DATA_T.sql
@./rice_server/bootstrap/V310_1_009__DML_BS4_KRIM_ROLE_PERM_T.sql
@./rice_server/bootstrap/V310_1_010__DML_BS5_KREW_RULE_RSP_T.sql
@./rice_server/bootstrap/V310_1_011__DML_BS5_KREW_RULE_T.SQL.sql
@./rice_server/bootstrap/V310_1_012__DML_BS5_KRIM_APP_ADMIN.sql
@./rice_server/bootstrap/V310_1_013__DML_BS5_KRIM_GROUP_MBR_T.sql
@./rice_server/bootstrap/V310_1_014__DML_BS7_KRNS_PARM_T.sql
@./rice_server/bootstrap/V310_2_002__KR_DML_BS1_KRIM_ID_CLEAN.sql
@./rice_server/bootstrap/V310_2_003__KR_DML_BS2_KRIM_ID_CLEAN.sql
@./rice_server/bootstrap/V310_2_004__KR_DML_BS3_KRIM_PERM_T.sql
@./rice_server/bootstrap/V310_2_005__KR_DML_BS4_KRIM_PERM_ATTR_DATA_T.sql
@./rice_server/bootstrap/V310_2_006__KR_DML_BS4_KRIM_ROLE_T.sql
@./rice_server/bootstrap/V310_2_007__KR_DML_BS5_KRIM_ROLE_PERM_T.sql
@./rice_server/bootstrap/V310_2_008__KR_DML_BS7_KREW_DOC_HDR_T.sql
@./rice_server/bootstrap/V310_2_009__KR_DML_BS7_KRNS_PARM_T.sql
@./rice_server/bootstrap/V310_2_010__KR_DML_BS8_KREW_ACTN_TKN_T.sql
@./rice_server/bootstrap/V310_2_011__KR_DML_BS8_KREW_DOC_HDR_CNTNT_T.sql
@./rice_server/bootstrap/V310_2_012__KR_DML_BS8_KREW_RTE_NODE_INSTN_T.sql
@./rice_server/bootstrap/V310_2_013__KR_DML_BS9_KREW_ACTN_RQST_T.sql
@./rice_server/bootstrap/V310_3_002__KR_DML_BS1_KRIM_PERM_T.sql
@./rice_server/bootstrap/V310_3_003__KR_DML_BS1_KRNS_PARM_T.sql
@./rice_server/bootstrap/V310_3_004__KR_DML_BS2_KRIM_PERM_ATTR_DATA_T.sql
@./rice_server/bootstrap/V310_3_005__KR_DML_BS2_KRIM_ROLE_T.sql
@./rice_server/bootstrap/V310_3_006__KR_DML_BS3_KRIM_ROLE_PERM_T.sql
@./rice_server/bootstrap/V310_3_007__KR_DML_BS4_KRIM_GRP_T.sql
@./rice_server/bootstrap/V310_3_008__KR_DML_BS5_KREW_DOC_HDR_T.sql
@./rice_server/bootstrap/V310_3_009__KR_DML_BS5_KRNS_PARM_T.sql
@./rice_server/bootstrap/V310_3_010__KR_DML_BS6_KREW_ACTN_TKN_T.sql
@./rice_server/bootstrap/V310_3_011__KR_DML_BS6_KREW_DOC_HDR_CNTNT_T.sql
@./rice_server/bootstrap/V310_3_012__KR_DML_BS6_KREW_RTE_NODE_INSTN_T.sql
@./rice_server/bootstrap/V310_3_013__KR_DML_BS7_KREN_CHNL_PRODCR_T.sql
@./rice_server/bootstrap/V310_3_014__KR_DML_BS7_KREW_ACTN_RQST_T.sql
@./rice_server/bootstrap/V310_3_015__KR_DML_BS7_KRIM_AFLTN_TYP_T.sql
@./rice_server/bootstrap/V310_3_016__KR_DML_BS7_KRIM_TYP_T.sql
@./rice_server/bootstrap/V310_4_002__update_final_oracle.sql
@./rice_server/bootstrap/V310_4_003__KR_DML_BS1_KRIM_ENTITY_T.sql
@./rice_server/bootstrap/V310_4_004__KR_DML_BS1_KRIM_PERM_T.sql
@./rice_server/bootstrap/V310_4_005__KR_DML_BS1_KRNS_PARM_T.sql
@./rice_server/bootstrap/V310_4_006__KR_DML_BS2_KRIM_ENTITY_ENT_TYP_T.sql
@./rice_server/bootstrap/V310_4_007__KR_DML_BS2_KRIM_ROLE_PERM_T.sql
@./rice_server/bootstrap/V310_4_008__KR_DML_BS2_KRIM_ROLE_RSP_ACTN_T.sql
@./rice_server/bootstrap/V310_4_009__KR_DML_BS3_KRIM_PERM_T.sql
@./rice_server/bootstrap/V310_4_010__KR_DML_BS3_KRIM_PRNCPL_T.sql
@./rice_server/bootstrap/V310_4_011__KR_DML_BS3_KRIM_ROLE_T.sql
@./rice_server/bootstrap/V310_4_012__KR_DML_BS4_KRIM_GRP_MBR_T.sql
@./rice_server/bootstrap/V310_4_013__KR_DML_BS4_KRIM_PERM_ATTR_DATA_T.sql
@./rice_server/bootstrap/V310_4_014__KR_DML_BS4_KRIM_ROLE_PERM_T.sql
commit;
