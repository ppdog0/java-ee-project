package ejb;

import entity.*;

import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
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
            //Integer id = searchUserId(username);
            User user = new User(password,
                    username);
            logger.log(Level.INFO, "Created user {0}--{1}", new Object[]{username, password});
            em.persist(user);
            logger.log(Level.INFO, "Persisted user {0}--{1}", new Object[]{username, password});
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    public User findUser(String username) {
        User user;
        try {
            Integer userid = searchUserId(username);
            user = em.find(User.class, userid);

            return user;
        } catch (Exception e) {

        }
        return null;
    }
    public Integer searchUserId(String username) {
        try {
            return (Integer) em.createNamedQuery("findUserByName")
                    .setParameter("name", username)
                    .getSingleResult();
        }catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    public void createNotice(Integer userId, String title, String text, Integer communityId) {
        try {
            //SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");


            Date date = new Date();

            User user = em.find(User.class, userId);
            Community community = em.find(Community.class, communityId);

            Notice notice = new Notice(user, title, text, date, community);
            em.persist(notice);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    public List<Notice> findAllNotice(String communityId) {
        try {
            return (List<Notice>) em.createNamedQuery("findAllNotice")
                    .getSingleResult();
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    public void updateNotice(Integer noticeid, Integer userId, String title, String text, Integer communityId) {
        try {
            Date date = new Date();

            User user = em.find(User.class, userId);
            Community community = em.find(Community.class, communityId);

            if(user.checkAdminCommunity(community)) {
                Notice notice = new Notice(user, title, text, date, community);
                notice.setNoticeid(noticeid);

                em.merge(notice);
            }
        } catch (Exception e) {

        }
    }

    public void createPost(Integer userId, String title, String text, Integer communityId) {
        try {
            User user = em.find(User.class, userId);
            Community community = em.find(Community.class, communityId);
            Date date = new Date();

            Post post = new Post(user, community, title, text, date);
            em.persist(post);
        } catch (Exception e) {

        }
    }
    public void updatePost(Integer postId, Integer userId, Integer communityId, String titile, String text) {
        try {
            User user = em.find(User.class, userId);
            Community community = em.find(Community.class, communityId);
            Date date = new Date();

            Post post = new Post(user, community, titile, text, date);
            post.setPostid(postId);

            em.merge(post);
        } catch (Exception e) {

        }
    }
    public void deletePost(Integer postId, Integer userId) {
        try {
            Post post = em.find(Post.class, postId);
            User user = em.find(User.class, userId);
            if(post.getUser().getId() == userId || user.checkAdminCommunity(post.getCommunity())) {
                em.remove(em.merge(post));
            }
        } catch (Exception e) {
        }
    }
    public List<Post> findAllPosts(Integer communityId) {
        try {
            return (List<Post>) em.createNamedQuery("findAllPost")
                    .getSingleResult();
        } catch (Exception e) {

        }
        return null;
    }

    public void createBill(Integer userId, Integer communityId, Integer price, String title, String type, Boolean status) {
        try {
            User user = em.find(User.class, userId);
            Community community = em.find(Community.class, communityId);
            Date date = new Date();

            Bill bill = new Bill(user, community, title, type, price, status, date);
            em.persist(bill);
        } catch (Exception e) {

        }
    }
    public void updateBill(Integer billId, Integer userId, Boolean status) {
        try {
            Bill bill = em.find(Bill.class, billId);
            User user = em.find(User.class, userId);
            if(user.checkAdminCommunity(bill.getCommunity())) {
                Date date = new Date();
                bill.setDate(date);
                bill.setStatus(status);
                em.merge(bill);
            }
        } catch (Exception e) {

        }
    }
    List<Bill> findBills(Integer communityId, Integer userId) {
        try {
            return (List<Bill>) em.createNamedQuery("findBillById")
                    .setParameter("id", userId).setParameter("cid", communityId)
                    .getResultList();
        } catch (Exception e) {

        }
        return null;
    }

    void createHealth(Integer userId, String status, String curr_position) {
        try {
            User user = em.find(User.class, userId);
            Date date = new Date();
            Health health = new Health(user, status, curr_position, date);

            em.persist(health);
        } catch (Exception e) {

        }
    }
    void updateHealth(Integer healthId, Integer userId, String status, String curr_position) {
        try {
            Health health = em.find(Health.class, healthId);
            health.setStatus(status);
            health.setCurrposition(curr_position);

            em.persist(health);
        } catch (Exception e) {

        }
    }
    Health findUserHealth(Integer userId) {
        try {
            return (Health) em.createNamedQuery("findHealthById")
                    .setParameter("id", userId)
                    .getSingleResult();
        } catch (Exception e) {

        }
        return null;
    }

    Integer searchCommunityId(String communityname) {
        try {
            return (Integer) em.createNamedQuery("findCommunityByName")
                    .setParameter("name", communityname)
                    .getSingleResult();
        } catch (Exception e) {

        }
        return null;
    }

//待定
//    public void createComplaints() {}
//
//待定
//    public void createOrders() {}
//    public void getOrderPrice() {}
//
//
}
