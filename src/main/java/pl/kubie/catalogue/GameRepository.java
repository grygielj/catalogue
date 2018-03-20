package pl.kubie.catalogue;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class GameRepository {

    private EntityManager entityManager;

    public GameRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Game save(Game game) {
        long id = game.getId();
        entityManager.getTransaction().begin();

        if(entityManager.find(Game.class,id) == null){
            entityManager.persist(game);
            entityManager.getTransaction().commit();
            return game;
        }else{
            entityManager.getTransaction().commit();
            throw new EntityNotFoundException();
        }
    }

    public void delete(String name) {
        Game game = findByName(name);
        if(game == null){
            throw new EntityNotFoundException();
        }else{
            entityManager.getTransaction().begin();
            entityManager.remove(game);
            entityManager.getTransaction().commit();
        }
    }
    public List<Game> findAll(){
        TypedQuery<Game> query = entityManager.createQuery("select g from Game g",Game.class);
        return query.getResultList();
    }

    public Game findByName(String name) {
        TypedQuery<Game> query = entityManager.createQuery("select g from Game g where g.name = :name",Game.class);
        query.setParameter("name",name);
        return query.getSingleResult();
    }

    public List<Game> findByRateGreaterThan(double rate) {
        TypedQuery<Game> query = entityManager.createQuery("select g from Game g where g.rate > :rate",Game.class);
        query.setParameter("rate",rate);
        return query.getResultList();
    }
    public List<Game> findByDate(LocalDate localDate) {
        TypedQuery<Game> query = entityManager.createQuery("select g from Game g where g.date = :date",Game.class);
        query.setParameter("date",localDate);
        return query.getResultList();
   }
}
