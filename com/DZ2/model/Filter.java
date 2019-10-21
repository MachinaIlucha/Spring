package com.DZ2.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

@Repository
public class Filter {

    @PersistenceContext
    private static EntityManager entityManager;

    public static void filter() {

//        CriteriaBuilder criteriaBuilderObj = entityManager.getCriteriaBuilder();
//
//        // Making The Query Object From The 'CriteriaBuilder' Instance
//        CriteriaQuery<Object> queryObj = criteriaBuilderObj.createQuery();
//        Root<lesson6.DZ2.model.Flight> from = queryObj.from(lesson6.DZ2.model.Flight.class);
//
//        // Step #1 - Displaying All Records
//        CriteriaQuery<Object> selectQuery = queryObj.select(from);
//        TypedQuery<Object> typedQuery = entityManager.createQuery(selectQuery);
//        List<Object> flightList = typedQuery.getResultList();
//
//        if (flightList != null && flightList.size() > 0) {
//            for (Object obj : flightList) {
//                lesson6.DZ2.model.Flight flight = (lesson6.DZ2.model.Flight) obj;
//                System.out.println(flight.toString());
//            }
//        } else {
//            System.out.println("No Flight In The Flight Table !");
//        }

        CriteriaBuilder criteriaBuilder = entityManager
                .getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder
                .createQuery();
        Root<Flight> from = criteriaQuery.from(Flight.class);

        //select all records
        System.out.println("Select all");
        CriteriaQuery<Object> select = criteriaQuery.select(from);
        TypedQuery<Object> typedQuery = entityManager
                .createQuery(select);
        List<Object> resultlist = typedQuery.getResultList();

        for (Object o : resultlist) {
            Flight f = (Flight) o;
            System.out.println("Flight: " + f.getId());
        }

        //Ordering the records
        System.out.println("Select all records by follow ordering");
        CriteriaQuery<Object> select1 = criteriaQuery.select(from);
        select1.orderBy(criteriaBuilder.asc(from.get("name")));
        TypedQuery<Object> typedQuery1 = entityManager
                .createQuery(select);
        List<Object> resultlist1 = typedQuery1.getResultList();

        for (Object o : resultlist1) {
            Flight e = (Flight) o;
            System.out.println("Flight id: " + e.getId());
        }
    }
}
