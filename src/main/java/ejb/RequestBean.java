package ejb;

import entity.User;

import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Stateful
public class RequestBean {
    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = Logger.getLogger("java.ejb.RequestBean");

    public void createUser(Integer id,
                           String password,
                           String username) {
        try {
//            User user = new User(id,
//                    password,
//                    username);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
}
