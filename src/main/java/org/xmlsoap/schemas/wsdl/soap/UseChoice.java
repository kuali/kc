
package org.xmlsoap.schemas.wsdl.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for useChoice.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="useChoice">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="literal"/>
 *     &lt;enumeration value="encoded"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "useChoice")
@XmlEnum
public enum UseChoice {

    @XmlEnumValue("literal")
    LITERAL("literal"),
    @XmlEnumValue("encoded")
    ENCODED("encoded");
    private final String value;

    UseChoice(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UseChoice fromValue(String v) {
        for (UseChoice c: UseChoice.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
