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


\. ./kc/bootstrap/V1603_003__support_new_pd_attachments.sql
\. ./kc/bootstrap/V1603_004__add_new_form.sql
\. ./kc/bootstrap/V1603_007__fix_obligated_amount_fields.sql
\. ./kc/bootstrap/V1603_008__new_form_support.sql
\. ./kc/bootstrap/V1603_009__fix_new_form_support.sql
\. ./kc/bootstrap/V1603_011__fix_valid_narr_forms_23_44.sql
\. ./kc/bootstrap/V1603_012__new_form_support.sql
\. ./kc/bootstrap/V1603_013__fix_nsf_cover_page_1_6_form.sql
\. ./kc/bootstrap/V1603_014__fix_new_form_support.sql
\. ./kc/bootstrap/V1603_015__fix_nsf_cover_page_narratives.sql
\. ./kc/bootstrap/V1603_016__fix_questionnaire_for_form.sql
commit;
