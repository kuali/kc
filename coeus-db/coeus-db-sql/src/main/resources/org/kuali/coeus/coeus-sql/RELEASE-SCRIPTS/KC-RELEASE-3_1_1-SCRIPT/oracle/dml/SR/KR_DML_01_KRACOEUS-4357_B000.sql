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

INSERT INTO KRCR_PARM_T(APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)
	VALUES('KC', 'KC-AWARD', 'Document', 'closeoutReportTypeInvoice', 1, 'CONFG', '6', 'This system parameter maps the CloseoutReportType Invoice(closeoutReoprtTypeCode=6) with ReportClass Payment/Invoice(reportClassCode=6). If this system parameter is changed - the corresponding values in CloseoutReportType and ReportClass tables should be updated as well.', 'A', SYS_GUID())
/
