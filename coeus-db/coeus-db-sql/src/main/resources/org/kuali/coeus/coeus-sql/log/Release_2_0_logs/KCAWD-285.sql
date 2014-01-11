alter table award_amount_info
drop column amount_sequence_number;

alter table award_amount_info
modify transaction_id number(10);

COMMIT;

