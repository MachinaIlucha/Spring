package lesson6.DZ2.DAO;

import lesson6.DZ2.model.Flight;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class FlightDAO {

    @PersistenceContext
    private static EntityManager entityManager;

    public Flight save(Flight flight) {
        entityManager.persist(flight);
        return flight;
    }

    public static Flight read(Long id) {
        Flight flight = entityManager.find(Flight.class, id);
        return flight;
    }

    public Flight update(Flight flight) {
        flight = entityManager.merge(flight);
        return flight;
    }

    public void delete(Flight flight) {
        entityManager.remove(flight);
    }
}
