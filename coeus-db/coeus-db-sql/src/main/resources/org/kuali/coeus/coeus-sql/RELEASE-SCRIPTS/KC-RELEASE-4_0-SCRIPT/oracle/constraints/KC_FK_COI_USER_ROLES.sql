ALTER TABLE COI_USER_ROLES
ADD CONSTRAINT FK_COI_USER_ROLES
FOREIGN KEY (COI_DISCLOSURE_ID) 
REFERENCES COI_DISCLOSURE (COI_DISCLOSURE_ID)
/
