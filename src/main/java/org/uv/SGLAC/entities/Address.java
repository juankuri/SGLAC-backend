package org.uv.SGLAC.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name = "street")
    private String street;

    @Column(name = "ext_number")
    private String externalNumber;

    @Column(name = "int_number")
    private String internalNumber; // opcional

    @Column(name = "colony")
    private String colony;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "municipality")
    private String municipality; // municipio / alcaldía

    @Column(name = "state")
    private String state; // Puedes poner Enum si quieres

    @Column(name = "country")
    private String country = "México";

    public Address() {}

    public Address(String street, String externalNumber, String internalNumber,
                   String colony, String postalCode, String municipality,
                   String state) {
        this.street = street;
        this.externalNumber = externalNumber;
        this.internalNumber = internalNumber;
        this.colony = colony;
        this.postalCode = postalCode;
        this.municipality = municipality;
        this.state = state;
    }

    // Getters & setters
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getExternalNumber() { return externalNumber; }
    public void setExternalNumber(String externalNumber) { this.externalNumber = externalNumber; }

    public String getInternalNumber() { return internalNumber; }
    public void setInternalNumber(String internalNumber) { this.internalNumber = internalNumber; }

    public String getColony() { return colony; }
    public void setColony(String colony) { this.colony = colony; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getMunicipality() { return municipality; }
    public void setMunicipality(String municipality) { this.municipality = municipality; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
