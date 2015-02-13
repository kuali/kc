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

UPDATE IACUC_PROTOCOL_CORRESP_TYPE SET MODULE_ID = 'Y' WHERE PROTO_CORRESP_TYPE_CODE IN ('1','11','12','13','14','15','16','17','18','19','20','21','22','25','26','27','28','29','6','7','8','9')
/

UPDATE PROTO_CORRESP_TYPE SET MODULE_ID = 'P' WHERE PROTO_CORRESP_TYPE_CODE IN ('13','14')
/

UPDATE PROTO_CORRESP_TYPE SET MODULE_ID = 'C' WHERE PROTO_CORRESP_TYPE_CODE = '15'
/

UPDATE PROTO_CORRESP_TYPE SET MODULE_ID = 'S' WHERE PROTO_CORRESP_TYPE_CODE IN ('11','12')
/

DELIMITER ;
