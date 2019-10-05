package lesson6.DZ2.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "FLIGHT")
public class Flight {

    private Long id;
    private Plane plane;

    @ManyToMany(mappedBy = "flights")
    private Set<Passenger> passenger;
    private Date dateFlight;
    private String cityFrom;
    private String cityTo;

    @Id
    @SequenceGenerator(name = "PR_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PR_SEQ")
    @Column(name = "FLIGHT_ID")
    public Long getId() {
        return id;
    }

    @Column(name = "PLANE_ID")
    public Plane getPlane() {
        return plane;
    }

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Passenger> getPassenger() {
        return passenger;
    }

    @Column(name = "DATEFLIGHT")
    public Date getDateFlight() {
        return dateFlight;
    }

    @Column(name = "CITYFROM")
    public String getCityFrom() {
        return cityFrom;
    }

    @Column(name = "CITYTO")
    public String getCityTo() {
        return cityTo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public void setPassenger(Set<Passenger> passenger) {
        this.passenger = passenger;
    }

    public void setDateFlight(Date dateFlight) {
        this.dateFlight = dateFlight;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }
}
