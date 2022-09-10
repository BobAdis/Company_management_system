package hu.progmatic.company_management_system.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Partner {
    @Id
    @GeneratedValue
    private Long id;
    private String partnerName;
    private PartnerType partnerType;
    private String address;
    private String contactName;
    private String email;
    private String phoneNumber;

    public Partner() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
