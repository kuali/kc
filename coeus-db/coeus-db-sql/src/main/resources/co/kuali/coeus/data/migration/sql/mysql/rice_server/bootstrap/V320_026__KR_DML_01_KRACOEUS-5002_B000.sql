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

DELIMITER /
INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, APPL_NMSPC_CD)
VALUES ('KC-AWARD', 'Document', 'AwardPaymentScheduleActiveLinks', UUID(), 1, 'CONFG', 'Y', 'Award Payment Schedule: Determines how users enter the Preparer and Status values. The object field is followed by the database field in parentheses.  N = Enter text for Preparer: submittedBy (SUBMITTED_BY) and Status: status (STATUS).  Y = Active links to Person table for Preparer: submittedByPersonId (SUBMITTED_BY_PERSON_ID) and Report Status table for Status: reportStatusCode (REPORT_STATUS_CODE).', 'A', 'KC')
/
DELIMITER ;
