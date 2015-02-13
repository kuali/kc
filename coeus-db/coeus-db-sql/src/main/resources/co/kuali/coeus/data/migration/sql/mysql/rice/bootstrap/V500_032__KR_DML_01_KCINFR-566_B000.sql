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
insert into KRMS_TYP_T values ('KC1001','Function Term Resolver Type Service','KC-PD','functionTermResolverTypeService','Y',1)
/
insert into KRMS_TERM_SPEC_T values('KC1009','IS_SPONSOR_FEDERAL','java.lang.String','Y',1,'Is Sponsor Federal','KC-PD')
/
insert into KRMS_TERM_T values ('KC1009','KC1009',1,'Is Sponsor Federal')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T values ('KC1009','KC-PD-CONTEXT','KC1009','Y')
/
insert into KRMS_TERM_RSLVR_T values ('KC1000','KC-PD','storedFunctionResolver','KC1001','KC1009','Y',1)
/
DELIMITER ;
