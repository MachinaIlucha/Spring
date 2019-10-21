package com.DZ2.DAO;

import com.DZ2.model.Passenger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PassengerDAO {

    private static final String regularPassengersSql =
            "SELECT * FROM passenger WHERE exist" +
                    "(SELECT * FROM flight_passenger fp JOIN FLIGHT f ON fp.FLIGHT_ID = f.FLIGHT_ID" +
                    "WHERE fp.PASSENGER_ID = PASSENGER.PASSENGER_ID" +
                    "GROUP BY fp.PASSENGER_ID, EXTRACT(YEAR FROM f.DATE_FLIGHT)" +
                    "HAVING COUNT (DISTINCT f.FLIGHT_ID) >= 25)";

    @PersistenceContext
    private EntityManager entityManager;

    public Passenger save(Passenger passenger) {
        entityManager.persist(passenger);
        return passenger;
    }

    public Passenger read(Long id) {
        Passenger passenger = entityManager.find(Passenger.class, id);
        return passenger;
    }

    public Passenger update(Passenger passenger) {
        passenger = entityManager.merge(passenger);
        return passenger;
    }

    public void delete(Passenger passenger) {
        entityManager.remove(passenger);
    }

    public List<Passenger> regularPassengers(int year) {
        return entityManager.createNativeQuery(regularPassengersSql).getResultList();
    }
}
