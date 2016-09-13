--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

update KRCR_PARM_T set PARM_DESC_TXT = 'Select how the FDP Pass-Through Entity Contact Information is completed. N completes with only top organization information. Y requires requisitioner unit and completes with top organization name but also the contact information from requisitioner unit.' where APPL_ID = 'KUALI' and NMSPC_CD = 'KC-SUBAWARD' and CMPNT_CD = 'All' and PARM_NM = 'FDP_ORG_FROM_REQUISITIONER_UNIT';
update KRCR_PARM_T set PARM_DESC_TXT = 'Determines how the FDP FAIN, Anticipated and Obligated amount fields are populated. N uses from award linked. Y always uses the Parent Award from award linked.' where APPL_ID = 'KUALI' and NMSPC_CD = 'KC-SUBAWARD' and CMPNT_CD = 'All' and PARM_NM = 'FDP_FROM_PARENT_AWARD';