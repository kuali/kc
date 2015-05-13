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

UPDATE SPECIAL_REVIEW SET SORT_ID = 1 WHERE DESCRIPTION = "Animal Usage";
UPDATE SPECIAL_REVIEW SET SORT_ID = 2 WHERE DESCRIPTION = "Biohazard Materials";
UPDATE SPECIAL_REVIEW SET SORT_ID = 3 WHERE DESCRIPTION = "Foundation Relations";
UPDATE SPECIAL_REVIEW SET SORT_ID = 4 WHERE DESCRIPTION = "Human Subjects";
UPDATE SPECIAL_REVIEW SET SORT_ID = 5 WHERE DESCRIPTION = "International Programs";
UPDATE SPECIAL_REVIEW SET SORT_ID = 6 WHERE DESCRIPTION = "Radioactive Isotopes";
UPDATE SPECIAL_REVIEW SET SORT_ID = 7 WHERE DESCRIPTION = "Recombinant DNA";
UPDATE SPECIAL_REVIEW SET SORT_ID = 8 WHERE DESCRIPTION = "Space Change";
UPDATE SPECIAL_REVIEW SET SORT_ID = 9 WHERE DESCRIPTION = "TLO PR-Previously Reviewed";
UPDATE SPECIAL_REVIEW SET SORT_ID = 10 WHERE DESCRIPTION = "TLO Review - No conflict (A)";
UPDATE SPECIAL_REVIEW SET SORT_ID = 11 WHERE DESCRIPTION = "TLO Review - Potential Conflict (B2)";
UPDATE SPECIAL_REVIEW SET SORT_ID = 12 WHERE DESCRIPTION = "TLO review - Reviewed, no conflict (B1)";
