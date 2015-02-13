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
-- All Protocols function
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1030', 'allProtocols', 'java.lang.String', 'Y', 1, 'All Protocols', 'KC-PROTOCOL')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) 
values ('KC1030', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='allProtocols'), 1, 'All Protocols')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1030', 'KC-PROTOCOL-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='allProtocols' and NMSPC_CD='KC-PROTOCOL'), 'N')
/

insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and NM='allProtocols'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
DELIMITER ;
