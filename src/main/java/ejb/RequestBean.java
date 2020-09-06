package ejb;

import entity.User;

import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateful
public class RequestBean {
    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = Logger.getLogger("java.ejb.RequestBean");

    public void createUser(String password,
                           String username) {
        try {
            User user = new User(password,
                    username);
            logger.log(Level.INFO, "Created user {0}--{1}", new Object[]{id, username});
            em.persist(user);
            logger.log(Level.INFO, "Persisted user {0}--{1}", new Object[]{id, username});
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = (List<User>) em.createNamedQuery("findAllUsers").getResultList();
        return users;
    }
}
