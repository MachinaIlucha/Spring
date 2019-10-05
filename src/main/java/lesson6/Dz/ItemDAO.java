package lesson6.Dz;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ItemDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Item save(Item item) {
        entityManager.persist(item);
        return item;
    }

    public Item read(Long id) {
        Item item = entityManager.find(Item.class, id);
        return item;
    }

    public Item update(Item item) {
        item = entityManager.merge(item);
        return item;
    }

    public void delete(Item item) {
        entityManager.remove(item);
    }

    public void deleteByName(String name) {
        Query query = entityManager.createQuery("delete from Item where name = :name");
        query.setParameter(name, name);
        query.executeUpdate();
    }
}
