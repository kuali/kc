-- Bootstrap data for Valid Special Review Approval ID update --

ALTER TABLE VALID_SP_REV_APPROVAL 
DROP CONSTRAINT VALID_SP_REV_APPROVALP1;

ALTER TABLE VALID_SP_REV_APPROVAL 
ADD (VALID_SP_REV_APPROVAL_ID NUMBER(12, 0));

UPDATE VALID_SP_REV_APPROVAL sra1 
SET VALID_SP_REV_APPROVAL_ID = (select VALID_SP_REV_APPROVAL_ID from (select format(@num := if(@num is null,1,@num + 1),0) as VALID_SP_REV_APPROVAL_ID, special_review_code, approval_type_code
from valid_sp_rev_approval group by special_review_code, approval_type_code) sra2 
where sra2.approval_type_code = sra1.approval_type_code and sra2.special_review_code = sra1.special_review_code)

COMMIT;

ALTER TABLE VALID_SP_REV_APPROVAL 
MODIFY (VALID_SP_REV_APPROVAL_ID DECIMAL(12, 0) NOT NULL);

ALTER TABLE VALID_SP_REV_APPROVAL
ADD CONSTRAINT VALID_SP_REV_APPROVALP1 PRIMARY KEY (VALID_SP_REV_APPROVAL_ID);