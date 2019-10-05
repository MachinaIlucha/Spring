package lesson6.DZ2.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "PASSENGER")
public class Passenger {

    private Long id;
    private String lastName;
    private String nationality;
    private Date dateOfBirth;
    private String passportCode;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "FLIGHT_PASSENGERS",
            joinColumns = @JoinColumn(name = "PASSENGER_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "FLIGHT_ID", referencedColumnName = "id"))
    private Set<Flight> flights;

    @Id
    @SequenceGenerator(name = "PR_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PR_SEQ")
    @Column(name = "PASSENGER_ID")
    public Long getId() {
        return id;
    }

    @Column(name = "LASTNAME")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "PASSENGER_ID")
    public String getNationality() {
        return nationality;
    }

    @Column(name = "DATEOFBIRTH")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Column(name = "PASSPORTCODE")
    public String getPassportCode() {
        return passportCode;
    }

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Flight> getFlights() {
        return flights;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPassportCode(String passportCode) {
        this.passportCode = passportCode;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }
}
