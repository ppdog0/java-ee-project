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
            Integer id = searchUserId(username);
            User user = new User(password,
                    username);
            logger.log(Level.INFO, "Created user {0}--{1}", new Object[]{username, password});
            em.persist(user);
            logger.log(Level.INFO, "Persisted user {0}--{1}", new Object[]{username, password});
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

//    public List<User> getAllUsers() {
//        List<User> users = (List<User>) em.createNamedQuery("findAllUsers").getResultList();
//        return users;
//    }

    User findUser(Integer userId) {
        try {
            return (User) em.createNamedQuery("findUserById")
                    .setParameter("id", userId)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    Integer searchUserId(String username) {
        try {
            return (Integer) em.createNamedQuery("findUserByName")
                    .setParameter("name", username)
                    .getSingleResult();
        }catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

//    public Integer searchNoticeId(Notice notice) {}
//    public void createNotice(String userId, String title,  String text, String communityId) {}
//    public List<Notice> findAllNotice(String communityId) {}
//    public void updateNotice(Integer noticeid, Integer userId, String title, String text, Integer communityId) {}
//
//    public Integer searchPostId(Post post) {}
//    public void createPost(Integer userId, String title, String text, Integer communityId) {}
//    public void updatePost(Integer postId, Integer userId, Integer communityId, String titile, String text) {}
//    public void deletePost(Integer postId, Integer userId) {}
//    public List<Post> findAllPosts(Integer communityId) {}
//
//    //待定
//    public void createOrders() {}
//    public void getOrderPrice() {}
//
//    Integer searchBillId(Bill bill) {}
//    public void createBill(Integer amdinId, Integer userId, Integer communityId, Integer price, String type, Boolean status) {}
//    public void updateBill(Integer billId, Integer adminId, Boolean status) {}
//    List<Bill> findBills(Integer communityId, Integer userId) {}
//
//    //待定
//    public void createComplaints() {}
//
//    Integer searchHealthId(Health health) {}
//    void createHealth(Integer userId, String status, String curr_position) {}
//    void updateHealth(Integer healthId, Integer userId, String status, String curr_postion) {}
//    Health findUserHealth(Integer userId) {}
//
//    Integer searchCommunityId(Community community) {}
//    void createCommunity(Integer adminId, String communityName) {}
//    Lisr<Community> findAllCommunity() {}
//
//    Integer searchAdminId(Integer userId) {}
//    void createAdmin(Integer userId, Integer communityId) {}
//
//    Integer searchHid(Integer userId) {}
//    void creatHabitant(Integer userId, Integer communityId) {}
}
