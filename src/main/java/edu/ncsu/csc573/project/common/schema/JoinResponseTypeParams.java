//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.12.02 at 05:51:02 PM EST 
//


package edu.ncsu.csc573.project.common.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for JoinResponseTypeParams complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JoinResponseTypeParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statuscode" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="myipaddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="peerid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firsthash" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lasthash" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="File" type="{http://www.doogle.project.csc573.csc.ncsu.edu}FileParamType" maxOccurs="100"/>
 *         &lt;element name="Table" type="{http://www.doogle.project.csc573.csc.ncsu.edu}TableParamType" maxOccurs="100"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JoinResponseTypeParams", propOrder = {
    "statuscode",
    "message",
    "myipaddress",
    "peerid",
    "firsthash",
    "lasthash",
    "file",
    "table"
})
public class JoinResponseTypeParams {

    @XmlElement(required = true)
    protected BigInteger statuscode;
    @XmlElement(required = true)
    protected String message;
    @XmlElement(required = true)
    protected String myipaddress;
    @XmlElement(required = true)
    protected String peerid;
    @XmlElement(required = true)
    protected String firsthash;
    @XmlElement(required = true)
    protected String lasthash;
    @XmlElement(name = "File", required = true)
    protected List<FileParamType> file;
    @XmlElement(name = "Table", required = true)
    protected List<TableParamType> table;

    /**
     * Gets the value of the statuscode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStatuscode() {
        return statuscode;
    }

    /**
     * Sets the value of the statuscode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStatuscode(BigInteger value) {
        this.statuscode = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the myipaddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMyipaddress() {
        return myipaddress;
    }

    /**
     * Sets the value of the myipaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMyipaddress(String value) {
        this.myipaddress = value;
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
     * Gets the value of the firsthash property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirsthash() {
        return firsthash;
    }

    /**
     * Sets the value of the firsthash property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirsthash(String value) {
        this.firsthash = value;
    }

    /**
     * Gets the value of the lasthash property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLasthash() {
        return lasthash;
    }

    /**
     * Sets the value of the lasthash property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLasthash(String value) {
        this.lasthash = value;
    }

    /**
     * Gets the value of the file property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the file property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFile().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FileParamType }
     * 
     * 
     */
    public List<FileParamType> getFile() {
        if (file == null) {
            file = new ArrayList<FileParamType>();
        }
        return this.file;
    }

    /**
     * Gets the value of the table property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the table property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTable().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TableParamType }
     * 
     * 
     */
    public List<TableParamType> getTable() {
        if (table == null) {
            table = new ArrayList<TableParamType>();
        }
        return this.table;
    }

}
