create or replace procedure GetNIHAward ( as_proposal_number IN OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
														cur_generic IN OUT result_sets.cur_generic) IS

BEGIN

	open cur_generic for
	SELECT	A.SPONSOR_AWARD_NUMBER AWARD_NUMBER,
			   SUBSTR(A.sponsor_award_number,3,3) ACTIVITY_CODE,
         	SUBSTR(A.sponsor_award_number, 1, 1) AWARD_TYPE
	FROM		OSP$AWARD A, OSP$EPS_PROPOSAL P
	WHERE		P.PROPOSAL_NUMBER = AS_PROPOSAL_NUMBER
	AND      P.CURRENT_AWARD_NUMBER = A.MIT_AWARD_NUMBER 
   AND      A.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD WHERE
													MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER);

end;
/


