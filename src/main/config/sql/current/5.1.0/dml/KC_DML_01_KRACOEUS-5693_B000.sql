update QUESTION 
set SEQUENCE_STATUS = 'A' 
where QUESTION_REF_ID in (select QUESTION_REF_ID 
    from QUESTION q1 
          join (select QUESTION_ID, max(SEQUENCE_NUMBER) as max_sequence_number 
       from QUESTION group by QUESTION_ID) q2 
     on q1.QUESTION_ID = q2.QUESTION_ID 
  where SEQUENCE_NUMBER < max_sequence_number)
/