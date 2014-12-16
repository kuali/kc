package org.kuali.kra.external.customercreation;

public class CustomerConstants {

    public static class CustomerOptions {
        public enum Types {
            EXISTING("Y", "Use Existing Customer"), NEW("N", "Create New Customer"), NO("NA", "No Customer");
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
