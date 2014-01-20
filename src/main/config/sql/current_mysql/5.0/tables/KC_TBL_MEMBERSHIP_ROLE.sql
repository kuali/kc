DELIMITER /
alter table membership_role add committee_type_code VARCHAR(3)
/
alter table membership_role add constraint fk_committee_type foreign key (COMMITTEE_TYPE_CODE) REFERENCES committee_type (committee_type_code)
/

DELIMITER ;
