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
update NOTIFICATION_TYPE_RECIPIENT t1 set t1.ROLE_NAME = 'KC-PD:Aggregator Document Level'
where t1.NOTIFICATION_TYPE_ID = (
 select t3.NOTIFICATION_TYPE_ID from NOTIFICATION_TYPE t3 where t3.MODULE_CODE=
    (select t4.MODULE_CODE from COEUS_MODULE t4 where upper( t4.DESCRIPTION )= upper('Development Proposal'))
    and t3.ACTION_CODE='103'
 );
 /
DELIMITER ;
