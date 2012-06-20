
package com.targetprocess.integration.bug;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bugID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userEmailOrLogin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bugID",
    "userEmailOrLogin",
    "actorName"
})
@XmlRootElement(name = "AssignUserByEmailOrLoginAsActor")
public class AssignUserByEmailOrLoginAsActor {

    protected int bugID;
    protected String userEmailOrLogin;
    protected String actorName;

    /**
     * Gets the value of the bugID property.
     * 
     */
    public int getBugID() {
        return bugID;
    }

    /**
     * Sets the value of the bugID property.
     * 
     */
    public void setBugID(int value) {
        this.bugID = value;
    }

    /**
     * Gets the value of the userEmailOrLogin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserEmailOrLogin() {
        return userEmailOrLogin;
    }

    /**
     * Sets the value of the userEmailOrLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserEmailOrLogin(String value) {
        this.userEmailOrLogin = value;
    }

    /**
     * Gets the value of the actorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActorName() {
        return actorName;
    }

    /**
     * Sets the value of the actorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActorName(String value) {
        this.actorName = value;
    }

}