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

\. ./mysql/sequences/KC_SEQ_COI_DISCLOSURE_HISTORY_ID.sql
\. ./mysql/sequences/KC_SEQ_COI_USER_ROLES_ID.sql
\. ./mysql/sequences/KC_SEQ_RATE_CLASS_BASE_EXCL_ID.sql
\. ./mysql/sequences/KC_SEQ_RATE_CLASS_BASE_INCL_ID.sql
\. ./mysql/sequences/KC_SEQ_SEQ_PERSON_CUSTOM_DATA_ID.sql
\. ./mysql/sequences/KC_SEQ_SEQ_PERSON_MASS_CHANGE_ID.sql
\. ./mysql/tables/KC_TBL_AWARD_PERSONS.sql
\. ./mysql/tables/KC_TBL_BUDGET_PERSONS.sql
\. ./mysql/tables/KC_TBL_COI_DISCLOSURE.sql
\. ./mysql/tables/KC_TBL_COI_DISCLOSURE_ATTACHMENT.sql
\. ./mysql/tables/KC_TBL_COI_DISCLOSURE_EVENT_TYPE.sql
\. ./mysql/tables/KC_TBL_COI_DISCLOSURE_HISTORY.sql
\. ./mysql/tables/KC_TBL_COI_DISCLOSURE_NOTEPAD.sql
\. ./mysql/tables/KC_TBL_COI_DISCL_PROJECTS.sql
\. ./mysql/tables/KC_TBL_COI_DISC_DETAILS.sql
\. ./mysql/tables/KC_TBL_COI_DISPOSITION_STATUS.sql
\. ./mysql/tables/KC_TBL_COI_USER_ROLES.sql
\. ./mysql/tables/KC_TBL_EPS_PROPOSAL.sql
\. ./mysql/tables/KC_TBL_EPS_PROP_PERSON.sql
\. ./mysql/tables/KC_TBL_EPS_PROP_SPECIAL_REVIEW.sql
\. ./mysql/tables/KC_TBL_FINANCIAL_ENTITY_ATTACHMENT.sql
\. ./mysql/tables/KC_TBL_IACUC_PROTOCOL.sql
\. ./mysql/tables/KC_TBL_IACUC_PROTOCOL_DOCUMENT.sql
\. ./mysql/tables/KC_TBL_NOTIFICATION_TYPE_RECIPIENT.sql
\. ./mysql/tables/KC_TBL_PERSON_CUSTOM_DATA.sql
\. ./mysql/tables/KC_TBL_PERSON_EXT_T.sql
\. ./mysql/tables/KC_TBL_PERSON_MASS_CHANGE.sql
\. ./mysql/tables/KC_TBL_PERSON_MASS_CHANGE_DOCUMENT.sql
\. ./mysql/tables/KC_TBL_PMC_AWARD.sql
\. ./mysql/tables/KC_TBL_PMC_EPS_PROPOSAL.sql
\. ./mysql/tables/KC_TBL_PMC_IACUC_PROTOCOL.sql
\. ./mysql/tables/KC_TBL_PMC_NEGOTIATION.sql
\. ./mysql/tables/KC_TBL_PMC_PROPOSAL.sql
\. ./mysql/tables/KC_TBL_PMC_PROPOSAL_LOG.sql
\. ./mysql/tables/KC_TBL_PMC_PROTOCOL.sql
\. ./mysql/tables/KC_TBL_PMC_SUBAWARD.sql
\. ./mysql/tables/KC_TBL_PMC_UNIT_ADMINISTRATOR.sql
\. ./mysql/tables/KC_TBL_PROTOCOL_CORRESPONDENCE.sql
\. ./mysql/tables/KC_TBL_QUESTIONNAIRE_ANSWER_HEADER.sql
\. ./mysql/tables/KC_TBL_RATE_CLASS_BASE_EXCLUSION.sql
\. ./mysql/tables/KC_TBL_RATE_CLASS_BASE_INCLUSION.sql
\. ./mysql/tables/KC_TBL_REVIEWER_ATTACHMENTS.sql
\. ./mysql/tables/KC_TBL_UNIT.sql
\. ./mysql/tables/KC_TBL_WATERMARK.sql
\. ./mysql/dml/KC_DML_01_KCCOI-118_B000.sql
\. ./mysql/dml/KC_DML_01_KCCOI-178_B000.sql
\. ./mysql/dml/KC_DML_01_KCCOI-23_B000.sql
\. ./mysql/dml/KC_DML_01_KCCOI-60_B000.sql
\. ./mysql/dml/KC_DML_01_KCCOI-68_B000.sql
\. ./mysql/dml/KC_DML_01_KCCOI-93_B000.sql
\. ./mysql/dml/KC_DML_01_KCCOI-96_B000.sql
\. ./mysql/dml/KC_DML_01_KCCOI-98_B000.sql
\. ./mysql/dml/KC_DML_01_KCINFR-431_B000.sql
\. ./mysql/dml/KC_DML_01_KCINFR-439_B000.sql
\. ./mysql/dml/KC_DML_01_KCINFR-476_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1637_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1664_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1707_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1751_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1752_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1753_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1754_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1755_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1757_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1760_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1761_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1762_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1763_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1764_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1765_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1766_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1767_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1768_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1769_B000.sql
\. ./mysql/dml/KC_DML_01_KCIRB-1770_B000.sql
\. ./mysql/dml/KC_DML_01_KRACOEUS-5221_B000.sql
\. ./mysql/dml/KC_DML_01_KRACOEUS-5222_B000.sql
\. ./mysql/dml/KC_DML_01_KRACOEUS-5233_B000.sql
\. ./mysql/dml/KC_DML_02_KCCOI-50_B000.sql
\. ./mysql/dml/KC_DML_02_KCCOI-86_B000.sql
\. ./mysql/dml/KC_DML_02_KCCOI-87_B000.sql
\. ./mysql/dml/KC_DML_02_KCIRB-1638_B000.sql
\. ./mysql/dml/KC_DML_02_KCIRB-1694_B000.sql
\. ./mysql/dml/KC_DML_02_KRACOEUS-5401_B000.sql
\. ./mysql/dml/KC_DML_03_KCIRB-1695_B000.sql
\. ./mysql/dml/KC_DML_04_KCCOI-195_B000.sql
\. ./mysql/constraints/KC_FK_COI_DISCLOSURE_ATTACHMENT.sql
\. ./mysql/constraints/KC_FK_COI_DISCLOSURE_HISTORY.sql
\. ./mysql/constraints/KC_FK_COI_DISCLOSURE_NOTEPAD.sql
\. ./mysql/constraints/KC_FK_COI_DISPOSITION_STATUS.sql
\. ./mysql/constraints/KC_FK_COI_USER_ROLES.sql
\. ./mysql/constraints/KC_FK_PMC_AWARD.sql
\. ./mysql/constraints/KC_FK_PMC_EPS_PROPOSAL.sql
\. ./mysql/constraints/KC_FK_PMC_IACUC_PROTOCOL.sql
\. ./mysql/constraints/KC_FK_PMC_NEGOTIATION.sql
\. ./mysql/constraints/KC_FK_PMC_PROPOSAL.sql
\. ./mysql/constraints/KC_FK_PMC_PROPOSAL_LOG.sql
\. ./mysql/constraints/KC_FK_PMC_PROTOCOL.sql
\. ./mysql/constraints/KC_FK_PMC_SUBAWARD.sql
\. ./mysql/constraints/KC_FK_PMC_UNIT_ADMINISTRATOR.sql
\. ./mysql/constraints/KC_FK_RATE_CLASS_BASE_EXCL.sql
\. ./mysql/constraints/KC_FK_RATE_CLASS_BASE_INCL.sql
\. ./mysql/constraints/KC_PK_COI_DISCLOSURE_ATTACHMENT.sql
\. ./mysql/constraints/KC_PK_COI_DISCLOSURE_NOTEPAD.sql
\. ./mysql/constraints/KC_PK_COI_DISPOSITION_STATUS.sql
\. ./mysql/constraints/KC_PK_FINANCIAL_ENTITY_ATTACHMENT.sql
commit;
exit
