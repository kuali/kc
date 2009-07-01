
package org.xmlsoap.schemas.wsdl.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tStyleChoice.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tStyleChoice">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="rpc"/>
 *     &lt;enumeration value="document"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tStyleChoice")
@XmlEnum
public enum TStyleChoice {

    @XmlEnumValue("rpc")
    RPC("rpc"),
    @XmlEnumValue("document")
    DOCUMENT("document");
    private final String value;

    TStyleChoice(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TStyleChoice fromValue(String v) {
        for (TStyleChoice c: TStyleChoice.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
