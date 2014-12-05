DELIMITER /
update proposal_state set description='Cancelled' where state_type_code=10;
/
DELIMITER ;