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

drop procedure if exists duplicate_ip_sequence_numbers;

DELIMITER /
CREATE PROCEDURE duplicate_ip_sequence_numbers()
  BEGIN
    DECLARE DONE INT DEFAULT FALSE;
    DECLARE tmp_proposal_number varchar(8);

    DECLARE CUR CURSOR FOR SELECT proposal_number FROM proposal GROUP BY proposal_number, sequence_number HAVING COUNT(*) > 1;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET DONE = TRUE;

    OPEN CUR;

    read_loop: LOOP
      FETCH CUR INTO tmp_proposal_number;
      IF DONE THEN
        LEAVE read_loop;
      END IF;

    SET @correct_seq=0;

    UPDATE proposal
    SET sequence_number = (
      SELECT c.correct_seq
      FROM (
        SELECT @correct_seq:=@correct_seq+1 AS correct_seq, proposal_id, proposal_number, sequence_number
        FROM proposal
        WHERE proposal_number = tmp_proposal_number
        ORDER BY proposal_id
      ) AS c WHERE proposal.proposal_id = c.proposal_id
    )
    WHERE proposal_number = tmp_proposal_number;

    END LOOP;

    CLOSE CUR;
  END
/
DELIMITER ;
CALL duplicate_ip_sequence_numbers();

drop procedure if exists duplicate_ip_sequence_numbers;