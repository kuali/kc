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

update krcr_parm_t set VAL =
  'Approval signifies that the proposed project fits within the academic framework and resources of the unit, requirements for new or renovated facilities/space have been discussed with the appropriate people, contributions listed will be met by the department/college unless otherwise approved, that Conflict of Interest requirements have been addressed, and that Sponsored Programs may process the proposal.'
  where NMSPC_CD = 'KC-PD' and CMPNT_CD = 'Document' and PARM_NM = 'propSummaryDisclaimerText' and APPL_ID = 'KC';
