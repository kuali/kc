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

spool 1606_oracle_kc_upgrade.sql.log
@./kc/bootstrap/V1606_002__CITI.sql
@./kc/bootstrap/V1606_004__AwardAttachments2.sql
@./kc/bootstrap/V1606_005__add_configure_narr_for_form.sql
@./kc/bootstrap/V1606_006__phs.sql
@./kc/bootstrap/V1606_007__field_of_training.sql
@./kc/bootstrap/V1606_008__CORE_IMPERSONATE.sql
@./kc/bootstrap/V1606_010__CITI_Update.sql
@./kc/bootstrap/V1606_012__arg_value_desc_fix.sql
@./kc/bootstrap/V1606_013__CITI_Update.sql
@./kc/bootstrap/V1606_014__s2s_errors.sql
@./kc/bootstrap/V1606_015__FOT_desc_fix.sql
@./kc/bootstrap/V1606_016__award_post.sql
commit;
