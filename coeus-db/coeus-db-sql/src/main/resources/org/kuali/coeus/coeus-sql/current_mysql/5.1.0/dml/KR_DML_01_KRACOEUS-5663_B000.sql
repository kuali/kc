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
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC2024','protocolTypeCode', 'java.lang.String', 'Y', 1, 'Irb Protocol Type Code', 'KC-PROTOCOL')
/
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) 
values ('KC2021',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='protocolTypeCode' and NMSPC_CD='KC-PROTOCOL'), 1, 'Irb Protocol Type Code')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC2024','KC-PROTOCOL-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='protocolTypeCode' and NMSPC_CD='KC-PROTOCOL'), 'N')
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC2025','initialSubmissionDate', 'java.sql.Date', 'Y', 1, 'Initial Submission Date', 'KC-PROTOCOL')
/
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) 
values ('KC2022',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='initialSubmissionDate' and NMSPC_CD='KC-PROTOCOL'), 1, 'Initial Submission Date')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC2025','KC-PROTOCOL-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='initialSubmissionDate' and NMSPC_CD='KC-PROTOCOL'), 'N')
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC2026','expirationDate', 'java.lang.Integer', 'Y', 1, 'Expiration Date', 'KC-PROTOCOL')
/
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) 
values ('KC2023',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='expirationDate' and NMSPC_CD='KC-PROTOCOL'), 1, 'Expiration Date')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC2026','KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='expirationDate' and NMSPC_CD='KC-PROTOCOL'), 'N')
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC2027','leadUnitNumber', 'java.lang.Integer', 'Y', 1, 'Lead Unit NUmber', 'KC-PROTOCOL')
/
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) 
values ('KC2024',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='leadUnitNumber' and NMSPC_CD='KC-PROTOCOL'), 1, 'Lead Unit NUmber')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC2027','KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='leadUnitNumber' and NMSPC_CD='KC-PROTOCOL'), 'N')
/

DELIMITER ;
