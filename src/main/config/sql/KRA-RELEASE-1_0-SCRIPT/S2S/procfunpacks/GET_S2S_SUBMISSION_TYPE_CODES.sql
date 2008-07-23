CREATE OR REPLACE procedure GET_S2S_SUBMISSION_TYPE_CODES
  ( cur_generic IN OUT result_sets.cur_generic) is

begin

open cur_generic for
  SELECT S2S_SUBMISSION_TYPE_CODE,
			DESCRIPTION,
			UPDATE_TIMESTAMP,
			UPDATE_USER
    FROM OSP$S2S_SUBMISSION_TYPE;
end;
/

