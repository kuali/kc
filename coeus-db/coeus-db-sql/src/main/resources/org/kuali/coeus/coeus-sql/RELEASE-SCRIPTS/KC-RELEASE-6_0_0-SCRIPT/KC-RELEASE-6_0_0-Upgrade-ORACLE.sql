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
spool KC-RELEASE-6_0_0-Upgrade-ORACLE-Install.log
@../../current/6.0.0/sequences/KC_SEQ_COMMITTEE_SCHEDULE_ATTACHMENT_ID.sql
@../../current/6.0.0/sequences/KC_SEQ_DOCUMENT_ACCESS_ID.sql
@../../current/6.0.0/sequences/KC_SEQ_EPS_PROP_PERSON_ROLE.sql
@../../current/6.0.0/sequences/KC_SEQ_S2S_ERROR_ID.sql
@../../current/6.0.0/tables/KC_TBL_AWARD_BUDGET_EXT.sql
@../../current/6.0.0/tables/KC_TBL_AWARD_CGB.sql
@../../current/6.0.0/tables/KC_TBL_AWARD_PERSONS.sql
@../../current/6.0.0/tables/KC_TBL_BUDGET.sql
@../../current/6.0.0/tables/KC_TBL_BUDGET_SUB_AWARDS.sql
@../../current/6.0.0/tables/KC_TBL_DOCUMENT_ACCESS.sql
@../../current/6.0.0/tables/KC_TBL_DOCUMENT_NEXTVALUE.sql
@../../current/6.0.0/tables/KC_TBL_EPS_PROP_CONG_DISTRICT.sql
@../../current/6.0.0/tables/KC_TBL_EPS_PROPOSAL_BUDGET_EXT.sql
@../../current/6.0.0/tables/KC_TBL_EPS_PROPOSAL.sql
@../../current/6.0.0/tables/KC_TBL_EPS_PROP_PERSON_EXT.sql
@../../current/6.0.0/tables/KC_TBL_EPS_PROP_PERSON_ROLE.sql
@../../current/6.0.0/tables/KC_TBL_EPS_PROP_PERSON.sql
@../../current/6.0.0/tables/KC_TBL_PROPOSAL_PERSONS.sql
@../../current/6.0.0/tables/KC_TBL_PROPOSAL_RESPONSE.sql
@../../current/6.0.0/tables/KC_TBL_S2S_ERROR.sql
@../../current/6.0.0/tables/KC_TBL_SPONSOR.sql
@../../current/6.0.0/dml/KC_DML_01_KFSMI-11381_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KFSMI-11382_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KFSMI-11384_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-6956_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7014_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7089_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7109_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7516_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7722_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7889_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-7922_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-8027_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-8128_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-8129_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-8411_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-8417_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-8442_B000.sql
@../../current/6.0.0/dml/KC_DML_01_KRACOEUS-8454_B000.sql
@../../current/6.0.0/dml/KC_DML_02_KCINFR-979_B000.sql
@../../current/6.0.0/dml/KC_DML_02_KRACOEUS-8602_B000.sql
@../../current/6.0.0/constraints/KC_FK1_BUDGET_SUB_AWARD_ATT.sql
commit;
exit
