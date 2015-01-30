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
spool KC-RELEASE-5_1_1-Upgrade-ORACLE-Install.log
@../../current/5.1.1/tables/KC_TBL_COI_DISCLOSURE.sql
@../../current/5.1.1/tables/KC_TBL_COI_DISCLOSURE_NOTEPAD.sql
@../../current/5.1.1/tables/KC_TBL_COI_DISC_DETAILS.sql
@../../current/5.1.1/tables/KC_TBL_COI_DISPOSITION_STATUS.sql
@../../current/5.1.1/tables/KC_TBL_COI_NOTE_TYPE.sql
@../../current/5.1.1/tables/KC_TBL_COI_NOTIFICATION.sql
@../../current/5.1.1/tables/KC_TBL_COI_RECOMENDED_ACTION_TYPE.sql
@../../current/5.1.1/tables/KC_TBL_COI_REVIEW_STATUS.sql
@../../current/5.1.1/tables/KC_TBL_COI_USER_ROLES.sql
@../../current/5.1.1/tables/KC_TBL_COMM_BATCH_CORRESP_DETAIL.sql
@../../current/5.1.1/tables/KC_TBL_IACUC_PROTOCOL_NOTIFICATION.sql
@../../current/5.1.1/tables/KC_TBL_PERSON_BIOSKETCH.sql
@../../current/5.1.1/tables/KC_TBL_PERSON_FIN_INT_DISCLOSURE.sql
@../../current/5.1.1/tables/KC_TBL_PROTOCOL_NOTIFICATION.sql
@../../current/5.1.1/dml/KC_DML_01_KRACOEUS-6113_B000.sql
@../../current/5.1.1/dml/KC_DML_01_KRACOEUS-6332_B000.sql
@../../current/5.1.1/dml/KC_DML_01_KRACOEUS-6381_B000.sql
@../../current/5.1.1/dml/KC_DML_01_KRACOEUS-6383_B000.sql
@../../current/5.1.1/dml/KC_DML_01_KRACOEUS-6389_B000.sql
@../../current/5.1.1/dml/KC_DML_01_KRACOEUS-6414_B000.sql
@../../current/5.1.1/dml/KC_DML_01_KRACOEUS-6422_B000.sql
@../../current/5.1.1/dml/KC_DML_01_KRACOEUS-6444_B000.sql
@../../current/5.1.1/dml/KC_DML_01_KRACOEUS-6530_B000.sql
@../../current/5.1.1/constraints/KC_FK_COI_DISCLOSURE.sql
@../../current/5.1.1/constraints/KC_FK_COI_DISCLOSURE_NOTEPAD.sql
@../../current/5.1.1/constraints/KC_FK_COI_DISC_DETAILS.sql
@../../current/5.1.1/constraints/KC_FK_USER_ROLES.sql
commit;
exit
