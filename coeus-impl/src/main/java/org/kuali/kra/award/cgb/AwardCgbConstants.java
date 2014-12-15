package org.kuali.kra.award.cgb;

public class AwardCgbConstants {

	public static class InvoicingOptions {
        public enum Types {
            AWARD("1", "Invoice by Award Hierarchy"), ACCOUNT("2", "Invoice by Account"), CONTRACT_CONTROL("3", "Invoice by Contract Control Account");
            private String code;
            private String name;
            Types(String code, String name) {
                this.code = code;
                this.name = name;
            }
            public String getCode() {
                return code;
            }
            public String getName() {
                return name;
            }
            public static String get(String code) {
                for(Types type : Types.values()) {
                    if(type.getCode().equals(code)){
                        return type.getName();
                    }
                }
                return null;
            }
        }
	}
}
