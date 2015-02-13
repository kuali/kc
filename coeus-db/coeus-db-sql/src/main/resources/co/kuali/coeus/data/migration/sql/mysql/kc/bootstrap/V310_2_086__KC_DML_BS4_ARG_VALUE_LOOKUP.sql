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

-- I have not been able to find any instances in KC where deleting these entries will cause a problem. In
-- fact, I cannot find anywhere that they are yet used. However, with that in mind, you should receive
-- a referential integrity error if you have existing data that will cause an issue here. In that case,
-- skip this script because it is not necessary for proper operation of KC.
delete from arg_value_lookup where argument_name='PeriodTypes' and description='Cycle';
delete from arg_value_lookup where argument_name='AppointmentTypes' and description='REG EMPLOYEE';
commit;
