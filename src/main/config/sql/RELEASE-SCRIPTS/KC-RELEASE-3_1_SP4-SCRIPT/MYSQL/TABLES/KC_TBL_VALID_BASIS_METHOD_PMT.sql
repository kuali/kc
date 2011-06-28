-- following command removes not null constraint from column FREQUENCY_INDICATOR since column is not used
ALTER TABLE valid_basis_method_pmt MODIFY frequency_indicator CHAR(1) NULL;