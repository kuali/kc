CREATE OR REPLACE FUNCTION FN_GET_PROP_EXEMPT_NUMBERS (as_proposal_number in EPS_PROP_SPECIAL_REVIEW.PROPOSAL_NUMBER%TYPE,
                                                     as_SPECIAL_REVIEW_NUMBER in EPS_PROP_SPECIAL_REVIEW.SPECIAL_REVIEW_NUMBER%TYPE)
RETURN VARCHAR2 IS
    exempt_number varchar2(3);
	exempt_numbers   VARCHAR2(50) :='';
BEGIN
	DECLARE CURSOR c_exempt_number is
	select exemption_type_code
	from eps_prop_exempt_number
	where  eps_prop_exempt_number.proposal_number = as_proposal_number and
            eps_prop_exempt_number.SPECIAL_REVIEW_NUMBER = as_SPECIAL_REVIEW_NUMBER;
	BEGIN
		OPEN c_exempt_number;
		LOOP
				FETCH c_exempt_number INTO exempt_number;
			   EXIT WHEN c_exempt_number%NOTFOUND;
				if (exempt_numbers is NULL) then
					exempt_numbers := 'E' || exempt_number;
				else
					exempt_numbers := exempt_numbers || ',' || 'E' || exempt_number;
				end if;
		END LOOP;
		CLOSE c_exempt_number;
	END;
      if (exempt_numbers is NULL) then
        select comments into exempt_numbers from eps_prop_special_review
	    where  eps_prop_special_review.proposal_number = as_proposal_number and
            eps_prop_special_review.SPECIAL_REVIEW_NUMBER = as_SPECIAL_REVIEW_NUMBER;
      end if;

	return exempt_numbers;
end;
/