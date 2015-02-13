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
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='X: Other (specify)' WHERE ORGANIZATION_TYPE_CODE='3'
/
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='M: Nonprofit with 501C3 IRS status (Other than Institution of Higher Education)' WHERE ORGANIZATION_TYPE_CODE='4'
/
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='N: Nonprofit without 501C3 IRS status (Other than Institution of Higher Education)' WHERE ORGANIZATION_TYPE_CODE='5'
/
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='Q: For-profit Organization (Other than small business)' WHERE ORGANIZATION_TYPE_CODE='6'
/
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='X: Other (specify)' WHERE ORGANIZATION_TYPE_CODE='7'
/
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='I: Native American Tribal Government (Federally Recognized)' WHERE ORGANIZATION_TYPE_CODE='8'
/
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='P: Individual' WHERE ORGANIZATION_TYPE_CODE='9'
/
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='O: Private Institution of Higher Education' WHERE ORGANIZATION_TYPE_CODE='10'
/
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='R: Small Business' WHERE ORGANIZATION_TYPE_CODE='11'
/
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='X: Other (specify) - socially and economically disadvantaged' WHERE ORGANIZATION_TYPE_CODE='14'
/
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='X: Other (specify) - women owned' WHERE ORGANIZATION_TYPE_CODE='15'
/
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='H: State-Controlled Institution of Higher Education' WHERE ORGANIZATION_TYPE_CODE='21'
/
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='G: Independent School District' WHERE ORGANIZATION_TYPE_CODE='24'
/
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='L: Public/Indian Housing Authority' WHERE ORGANIZATION_TYPE_CODE='25'
/
UPDATE ORGANIZATION_TYPE_LIST SET DESCRIPTION='J: Native American Tribal Organization (other than Federally recognized)' WHERE ORGANIZATION_TYPE_CODE='26'
/

DELIMITER ;
