package com.DZ2.DAO;

import com.DZ2.model.Plane;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PlaneDAO {

    private static final String oldPlanesSql = "SELECT * FROM PLANE WHERE EXTRACT(YEAR FROM current_date)-YEAR_PRODUCED >= 20";

    private final static String regularPlanesSql =
            "SELECT * FROM plane" +
                    "WHERE " +
                    "GROUP BY fp.Plane_ID, EXTRACT(YEAR FROM f.DATE_FLIGHT)" +
                    "HAVING COUNT (DISTINCT f.FLIGHT_ID) >= 300)";

    @PersistenceContext
    private EntityManager entityManager;

    public Plane save(Plane plane) {
        entityManager.persist(plane);
        return plane;
    }

    public Plane read(Long id) {
        Plane plane = entityManager.find(Plane.class, id);
        return plane;
    }

    public Plane update(Plane plane) {
        plane = entityManager.merge(plane);
        return plane;
    }

    public void delete(Plane plane) {
        entityManager.remove(plane);
    }

    public List<Plane> oldPlanes() {
        List<Plane> oldPlanes = entityManager.createQuery(oldPlanesSql).getResultList();
        return oldPlanes;
    }

    public List<Plane> regularPlanes(int year) {

        return entityManager.createQuery(regularPlanesSql).getResultList();
    }
}
