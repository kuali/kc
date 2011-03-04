CREATE OR REPLACE PROCEDURE  getTotalProjEndDt
              (as_proposal_number IN OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
               cur_generic IN OUT result_sets.cur_generic) is


begin




OPEN cur_generic for

 SELECT FINAL_EXPIRATION_DATE
   FROM  OSP$AWARD A, OSP$AWARD_AMOUNT_INFO AI
   WHERE  A.SPONSOR_AWARD_NUMBER = (SELECT A.SPONSOR_AWARD_NUMBER
                                    FROM  OSP$AWARD A, OSP$EPS_PROPOSAL P
                                  WHERE  P.PROPOSAL_NUMBER = as_proposal_number
                                  AND    P.CURRENT_AWARD_NUMBER = A.MIT_AWARD_NUMBER
                                    AND    A.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER)
                                                                FROM OSP$AWARD
                                                                WHERE MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER)
                                     )
   AND   A.MIT_AWARD_NUMBER = AI.MIT_AWARD_NUMBER
   AND A.SEQUENCE_NUMBER = AI.SEQUENCE_NUMBER
   AND   A.SEQUENCE_NUMBER =  (SELECT MAX(SEQUENCE_NUMBER)
           FROM OSP$AWARD
           WHERE MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER)
   AND   AI.AMOUNT_SEQUENCE_NUMBER = (SELECT MAX(AMOUNT_SEQUENCE_NUMBER)
                                      FROM OSP$AWARD_AMOUNT_INFO
                                      WHERE MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER);

end;
/

