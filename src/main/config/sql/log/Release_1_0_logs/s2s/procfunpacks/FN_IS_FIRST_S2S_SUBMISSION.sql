CREATE OR REPLACE function FN_IS_FIRST_S2S_SUBMISSION
	   (as_proposal_number in osp$eps_proposal.proposal_number%type)
	RETURN smallint
is
 ret_code      number := NULL;
 row_count     number;
begin
  SELECT count(*)
  INTO row_count
    FROM OSP$S2S_APPLICATION
   WHERE PROPOSAl_NUMBER = as_proposal_number;
 IF (row_count > 0) THEN
  ret_code := 0;
 ELSE
  ret_code := 1;
 END IF;
 RETURN (ret_code);
end;
/

