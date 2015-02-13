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
insert into KRMS_TERM_SPEC_T values('KC2000','specifiedGGForm','java.lang.String','Y',1,'Specified GG Form','KC-PD')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T values ('KC2000','KC-PD-CONTEXT','KC2000','Y')
/
insert into KRMS_TERM_RSLVR_T values ('KC2000','KC-PD','Java Function Resolver','KC1001','KC2000','Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
values ('KC2000', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Java Function Resolver' and NMSPC_CD='KC-PD'), 'GrantsGov Form Name', 1)
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM='specifiedGGForm'), 
	(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/
DELIMITER ;
