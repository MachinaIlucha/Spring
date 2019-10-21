package com.DZ2.DAO;

import com.DZ2.model.Flight;
import com.DZ2.model.Plane;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class FlightDAO {

    private static final String regularPlanesSql =
            "SELECT plane_id" +
                    "FROM flight" +
                    "WHERE exists" +
                    "(SELECT plane_id FROM flight" +
                    "where EXTRACT(YEAR FROM CURRENT_TIMESTAMP) = EXTRACT(YEAR FROM flight.dateflight)" +
                    "GROUP BY plane_id" +
                    "HAVING COUNT (DISTINCT plane_id) >= 300);";

    private static final String SQL_MOST_POPULAR_TO =
            "SELECT FLIGHT.*" +
                    "FROM FLIGHT\n" +
                    "LEFT JOIN (\n" +
                    "    SELECT CITY_TO, COUNT(FLIGHT_ID) rnk\n" +
                    "    FROM FLIGHT\n" +
                    "    GROUP BY CITY_TO\n" +
                    ") city_rnk ON FLIGHT.CITY_TO = city_rnk.CITY_TO\n" +
                    "ORDER BY city_rnk.rnk DESC";

    private static final String SQL_MOST_POPULAR_FROM =
            "SELECT FLIGHT.*" +
                    "FROM FLIGHT\n" +
                    "LEFT JOIN (\n" +
                    "    SELECT CITY_FROM, COUNT(FLIGHT_ID) rnk\n" +
                    "    FROM FLIGHT\n" +
                    "    GROUP BY CITY_FROM\n" +
                    ") city_rnk ON FLIGHT.CITY_FROM = city_rnk.CITY_FROM\n" +
                    "ORDER BY city_rnk.rnk DESC";

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

    public List<Plane> regularPlanes(int year) {
        return entityManager.createNativeQuery(regularPlanesSql).getResultList();
    }

    public List<Flight> SQL_MOST_POPULAR_TO(int year) {
        return entityManager.createNativeQuery(SQL_MOST_POPULAR_TO).getResultList();
    }

    public List<Flight> SQL_MOST_POPULAR_FROM(int year) {
        return entityManager.createNativeQuery(SQL_MOST_POPULAR_FROM).getResultList();
    }
}
