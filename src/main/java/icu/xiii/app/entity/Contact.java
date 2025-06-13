package icu.xiii.app.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;

@Entity
@Table(name = "contacts",
        uniqueConstraints = @UniqueConstraint(
                name = "contacts_full_name_IDX",
                columnNames = { "first_name", "last_name" }
        ))
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "UNSIGNED BIGINT")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 128)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 128)
    private String lastName;

    @Column(name = "phone", nullable = false, length = 32)
    private String phone;

    public Contact() {

    }

    public Contact(String firstName, String lastName, String phone) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Contact(Long id, String firstName, String lastName, String phone) {
        this(firstName, lastName, phone);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonGetter("full_name")
    public String getFullName() {
        return "%s %s".formatted(firstName, lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;
        return id.equals(contact.id) && firstName.equals(contact.firstName) && lastName.equals(contact.lastName) && phone.equals(contact.phone);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
