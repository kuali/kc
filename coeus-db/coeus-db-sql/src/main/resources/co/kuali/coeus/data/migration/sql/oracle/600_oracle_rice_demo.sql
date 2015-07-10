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

spool 600_oracle_rice_demo.sql.log
@./rice/demo/V600_159__KR_DML_31000_KCINFR-353_0TSD.sql
@./rice/demo/V600_160__KR_DML_31001_KREN_PRODCR_T_000D.sql
@./rice/demo/V600_161__KR_DML_31001_KRIM_ENTITY_T_0TSD.sql
@./rice/demo/V600_162__KR_DML_31001_KRNS_CAMPUS_T_0TSD.sql
@./rice/demo/V600_163__KR_DML_31002_KRIM_ENTITY_ENT_TYP_T_0TSD.sql
@./rice/demo/V600_164__KR_DML_31002_KRIM_PRNCPL_T_0TSD.sql
@./rice/demo/V600_165__KR_DML_31003_KREW_DOC_HDR_T_0TSD.sql
@./rice/demo/V600_166__KR_DML_31003_KRIM_ENTITY_ADDR_T_0TSD.sql
@./rice/demo/V600_167__KR_DML_31003_KRIM_ENTITY_AFLTN_T_0TSD.sql
@./rice/demo/V600_168__KR_DML_31003_KRIM_ENTITY_EMAIL_T_0TSD.sql
@./rice/demo/V600_169__KR_DML_31003_KRIM_ENTITY_EMP_INFO_T_0TSD.sql
@./rice/demo/V600_170__KR_DML_31003_KRIM_ENTITY_PHONE_T_0TSD.sql
@./rice/demo/V600_171__KR_DML_31004_KREW_ACTN_TKN_T_0TSD.sql
@./rice/demo/V600_172__KR_DML_31004_KREW_DOC_HDR_CNTNT_T_0TSD.sql
@./rice/demo/V600_173__KR_DML_31004_KRIM_GRP_MBR_T_0TSD.sql
@./rice/demo/V600_174__KR_DML_31004_KRIM_ROLE_MBR_T_0TSD.sql
@./rice/demo/V600_175__KR_DML_31005_KREW_ACTN_RQST_T_0TSD.sql
@./rice/demo/V600_182__KR_DML_32001_KRACOEUS-4976_0TSD.sql
@./rice/demo/V600_183__KR_DML_32001_KRACOEUS-5091_0TSD.sql
@./rice/demo/V600_184__KR_DML_32001_KRIM_ROLE_MBR_T_0TSD.sql
@./rice/demo/V600_191__KR_DML_40001_KCCOI-142_0TSD.sql
@./rice/demo/V600_192__KR_DML_40002_KCINFR-483_0TSD.sql
@./rice/demo/V600_193__KR_DML_40002_KCIRB-1664_0TSD.sql
@./rice/demo/V600_194__KR_DML_40002_KRACOEUS-5100_0TSD.sql
@./rice/demo/V600_195__KR_DML_40002_KRACOEUS-5257_0TSD.sql
@./rice/demo/V600_203__KR_DML_00_KCINFR-353_0TSD.sql
@./rice/demo/V600_204__KR_DML_01_KCCOI-280_0TSD.sql
@./rice/demo/V600_205__KR_DML_01_KCIAC-181_0TSD.sql
@./rice/demo/V600_210__KR_DML_01_KCIAC-209_0TSD.sql
@./rice/demo/V600_211__KR_DML_02_KCCOI-281_0TSD.sql
@./rice/demo/V600_212__KR_DML_02_KCIAC-236_0TSD.sql
@./rice/demo/V600_218__KR_DML_01_KRACOEUS-5886_0TSD.sql
@./rice/demo/V600_219__KR_DML_01_KRACOEUS-5892_00SD.sql
@./rice/demo/V600_220__KR_DML_01_KRACOEUS-6267_0TSD.sql
@./rice/demo/V600_221__KR_DML_02_KRACOEUS-6059_0TSD.sql
@./rice/demo/V600_222__KR_DML_02_KRACOEUS-6078_00SD.sql
@./rice/demo/V600_223__KR_DML_02_KRACOEUS-6201_0TSD.sql
@./rice/demo/V600_224__KR_DML_03_KRACOEUS-6232_0TSD.sql
@./rice/demo/V600_225__KR_DML_02_KRACOEUS-6458_BTSD.sql
@./rice/demo/V600_226__KR_DML_02_KRACOEUS-6566_0TSD.sql
@./rice/demo/V600_227__KR_DML_02_KRACOEUS-6775_0TSD.sql
commit;
