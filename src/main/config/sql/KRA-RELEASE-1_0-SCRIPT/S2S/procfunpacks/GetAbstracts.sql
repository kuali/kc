create or replace procedure GetAbstracts 
   ( AW_PROPOSAL_NUMBER IN OSP$EPS_PROP_ABSTRACT.PROPOSAL_NUMBER%TYPE, 
     cur_generic IN OUT result_sets.cur_generic) is 

begin 
	open cur_generic for 
	SELECT P.PROPOSAL_NUMBER, 
	       P.ABSTRACT_TYPE_CODE, 
   	    A.DESCRIPTION,
	       P.ABSTRACT 
	FROM 	 OSP$EPS_PROP_ABSTRACT P, OSP$ABSTRACT_TYPE A 
   WHERE  P.PROPOSAL_NUMBER = AW_PROPOSAL_NUMBER
   AND    P.ABSTRACT_TYPE_CODE = A.ABSTRACT_TYPE_CODE ;
end;

/




