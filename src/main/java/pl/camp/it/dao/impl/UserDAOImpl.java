package pl.camp.it.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.dao.IUserDAO;
import pl.camp.it.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDAOImpl implements IUserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.openSession();
        User user = session
                .createQuery("FROM pl.camp.it.model.User WHERE id = " + id, User.class)
                .uniqueResult();
        session.close();

        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        // pobieramy sesję
        Session session = sessionFactory.openSession();
        //tworzymy narzędzie pomagające w budowie zapytania (sesja tworzy to narzędzie)
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        //tworzymy zapytanie kryteriowe (tworzy nam je criteriaBuilder, jest ono typowane na Usera)
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        //struktura tabelki z bazy danych, wyciągamy ją z criteriaQuery
        Root<User> root = criteriaQuery.from(User.class);
        /*tworzymy zapytanie, mówimy że robimy select * czyli wyciągamy całego roota
        (wszystkie kolumny tabelki), oraz dodajemy warunek WHERE podając predykat

        predykat - prosimy criteriaBuilder żeby stworzył nam predykat równości
        podajemy co czemu ma się równać, w naszym przypadku ma to być kolumna login
        ze struktury tabeli oraz login podany w parametrze funkcji
         */

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("login"), login));
        //tworzymy zapytanie SQL na podstawie zapytania kryteriowego
        Query<User> query = session.createQuery(criteriaQuery);
        //odpalamy zapytanie i pobieramy pojedyńczy rezultat
        return query.getSingleResult();

    }
}
