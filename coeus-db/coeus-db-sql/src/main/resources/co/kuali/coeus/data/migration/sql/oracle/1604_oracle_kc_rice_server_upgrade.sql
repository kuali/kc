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

spool 1604_oracle_kc_rice_server_upgrade.sql.log
@./rice/bootstrap/V1604_002__RemoveSuperuserQualifier.sql
@./rice/bootstrap/V1604_003__Update_Parm_Desc.sql
@./rice/bootstrap/V1604_007__Generic_Unit_Admin_Derived_Role.sql
@./rice/bootstrap/V1604_012__add_citizen_type_params.sql
@./rice/bootstrap/V1604_014__unit_specific_peopleflow_type.sql
@./rice/bootstrap/V1604_015__irb_pi_workflow_param.sql
@./rice/bootstrap/V1604_017__budget_BO_permissions2.sql
@./rice/bootstrap/V1604_019__FYI_params.sql
commit;
