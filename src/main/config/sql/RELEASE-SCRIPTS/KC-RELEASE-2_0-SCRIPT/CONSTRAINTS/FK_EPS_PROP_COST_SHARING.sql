ALTER TABLE EPS_PROP_COST_SHARING 
    ADD CONSTRAINT FK_EPS_PROP_COST_SHARING FOREIGN KEY (BUDGET_ID) 
                REFERENCES BUDGET;
