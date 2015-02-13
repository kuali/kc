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

-- Copied from rice-1.0.3.1\scripts\upgrades\1.0.3 to 1.0.4\db-updates\mysql\2010-12-23.sql
UPDATE krew_rule_t SET RULE_TMPL_ID='1030' WHERE RULE_ID='1049';
UPDATE krew_rule_t SET RULE_TMPL_ID='1032' WHERE RULE_ID='1051';
DELETE FROM krew_rule_t WHERE RULE_ID='1052';
UPDATE krew_rule_rsp_t SET RULE_ID='1050' WHERE RULE_RSP_ID='2029';
UPDATE krew_rule_rsp_t SET RULE_ID='1051' WHERE RULE_RSP_ID='2031';
