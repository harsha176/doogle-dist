//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.12.05 at 02:27:20 PM EST 
//


package edu.ncsu.csc573.project.common.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TableParamType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TableParamType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="direction" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="peerid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nexthop" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="entry" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableParamType", propOrder = {
    "direction",
    "peerid",
    "nexthop"
})
public class TableParamType {

    protected int direction;
    @XmlElement(required = true)
    protected String peerid;
    @XmlElement(required = true)
    protected String nexthop;
    @XmlAttribute(required = true)
    protected int entry;

    /**
     * Gets the value of the direction property.
     * 
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Sets the value of the direction property.
     * 
     */
    public void setDirection(int value) {
        this.direction = value;
    }

    /**
     * Gets the value of the peerid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeerid() {
        return peerid;
    }

    /**
     * Sets the value of the peerid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeerid(String value) {
        this.peerid = value;
    }

    /**
     * Gets the value of the nexthop property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNexthop() {
        return nexthop;
    }

    /**
     * Sets the value of the nexthop property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNexthop(String value) {
        this.nexthop = value;
    }

    /**
     * Gets the value of the entry property.
     * 
     */
    public int getEntry() {
        return entry;
    }

    /**
     * Sets the value of the entry property.
     * 
     */
    public void setEntry(int value) {
        this.entry = value;
    }

}
