CREATE OR REPLACE package s2sAttachmentsV11Pkg as

 

procedure get_count_attachments (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           				   cur_type IN OUT result_sets.cur_generic) ;

 


END;
/

CREATE OR REPLACE package body s2sAttachmentsV11Pkg as

-------------------------------
-- procedure get_count_attachments 
--  GETS COUNT OF OTHER ATTACHMENTS
-------------------------------

procedure get_count_attachments (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           				   cur_type IN OUT result_sets.cur_generic)

 IS

li_count			number;

BEGIN

      li_count :=0;
	SELECT COUNT(*)
	INTO   li_count
	FROM	 OSP$NARRATIVE
	WHERE  PROPOSAL_NUMBER = as_proposal_number
	AND    NARRATIVE_TYPE_CODE = 61;

 open cur_type for

 	SELECT 	li_count as COUNT_ATTACHMENTS
 	FROM		DUAL;

end;
 End;
/



