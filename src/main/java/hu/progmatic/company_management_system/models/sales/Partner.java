package hu.progmatic.company_management_system.models.sales;

import javax.persistence.*;

@Entity
public class Partner {
    @Id
    @GeneratedValue
    private Long id;
    private String partnerName;
    @Enumerated(EnumType.STRING)
    private PartnerType partnerType;
    private String address;
    private String contactName;
    private String email;
    private String phoneNumber;


    public Partner() {}

    public Partner(String partnerName, PartnerType partnerType, String address, String contactName, String email, String phoneNumber) {
        this.partnerName = partnerName;
        this.partnerType = partnerType;
        this.address = address;
        this.contactName = contactName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public PartnerType getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(PartnerType partnerType) {
        this.partnerType = partnerType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
