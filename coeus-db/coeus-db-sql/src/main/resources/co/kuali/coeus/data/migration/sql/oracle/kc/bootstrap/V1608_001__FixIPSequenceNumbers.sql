--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

DECLARE
  tmp_proposal_number varchar2(8);

  CURSOR CUR IS SELECT proposal_number FROM proposal GROUP BY proposal_number, sequence_number HAVING COUNT(*) > 1;

BEGIN

  OPEN CUR;

  LOOP
    FETCH CUR INTO tmp_proposal_number;
    EXIT WHEN CUR%NOTFOUND;

    UPDATE proposal
    SET sequence_number = (
      SELECT c.correct_seq
      FROM (
       SELECT ROWNUM AS correct_seq, proposal_id, proposal_number, sequence_number
        FROM proposal
        WHERE proposal_number = tmp_proposal_number
        ORDER BY proposal_id
      ) c
      WHERE proposal.proposal_id = c.proposal_id
    )
    WHERE proposal_number = tmp_proposal_number;

  END LOOP;

  CLOSE CUR;
END;
