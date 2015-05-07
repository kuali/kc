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

UPDATE krms_term_spec_t SET NM = 'sumTotalCostAmountFromPeriods' where NMSPC_CD = "KC-PD" and NM = 'totalCost';
UPDATE krms_term_spec_t SET NM = 'sumDirectCostAmountFromPeriods' where NMSPC_CD = "KC-PD" and NM = 'totalDirectCost';
UPDATE krms_term_spec_t SET NM = 'sumIndirectCostAmountFromPeriods' where NMSPC_CD = "KC-PD" and NM = 'totalIndirectCost';
UPDATE krms_term_spec_t SET NM = 'sumUnderrecoveryAmountFromPeriods' where NMSPC_CD = "KC-PD" and NM = 'underrecoveryAmount';
UPDATE krms_term_spec_t SET NM = 'getSumTotalDirectCostLimitFromPeriods' where NMSPC_CD = "KC-PD" and NM = 'totalDirectCostLimit';


