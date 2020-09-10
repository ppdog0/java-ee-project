package ejb;

import entity.*;

import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    public User findUser(String username) {
        try {
            Integer userid = searchUserId(username);
            //user = em.find(User.class, userid);
            User user = (User) em.createNamedQuery("findUserByName")
                    .setParameter("name", username)
                    .getSingleResult();
            logger.log(Level.INFO, "find user {0}--{1}", new Object[]{user.getUsername(), user.getPassword()});
            return user;
        } catch (Exception e) {

        }
        return null;
    }
    public String searchUserPassword(String username) {
        try {
            User user = (User) em.createNamedQuery("findUserByName")
                    .setParameter("name", username)
                    .getSingleResult();
            String password = user.getPassword();
            logger.log(Level.INFO, "search user password {0}", new Object[]{password});
            return password;
        } catch (Exception e) {

        }
        return null;
    }
    public String searchUserName(Integer userid) {
        try {
            // User user = em.find(User.class, userid);
            User user = (User) em.createNamedQuery("findUserById")
                    .setParameter("id", userid)
                    .getSingleResult();
            return user.getUsername();
        } catch (Exception e) {

        }
        return null;
    }
    public Integer searchUserId(String username) {
        try {
            //logger.log(Level.INFO, "search user id {0}", new Object[]{username});

            User user = (User) em.createNamedQuery("findUserByName")
                    .setParameter("name", username)
                    .getSingleResult();
//            logger.log(Level.INFO, "search user id {0}--{1}", new Object[]{user.getUsername(), user.getPassword()});
            Integer userId = user.getId();
            return userId;
        }catch (Exception ex) {
            logger.log(Level.WARNING, ex.toString());
            throw new EJBException(ex.getMessage());
        }
    }

    public void userHabitantCommunity(Integer userId, Integer communityId){
        try {

            User user = em.find(User.class, userId);
            Community community = em.find(Community.class, communityId);
            Set<Community> communitys=user.getHabitantcommunities();
            communitys.add(community);
            user.setHabitantcommunities(communitys);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void userAdminCommunity(Integer userId, Integer communityId){
        try {

            User user = em.find(User.class, userId);
            Community community = em.find(Community.class, communityId);
            Set<Community> communitys=user.getAdmincommuintys();
            communitys.add(community);
            user.setHabitantcommunities(communitys);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    public void createNotice(Integer userId, String title, String text, Integer communityId) {
        try {
            //SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            // User user = em.find(User.class, userId);
            User user = (User) em.createNamedQuery("findUserById")
                    .setParameter("id", userId)
                    .getSingleResult();
            // Community community = em.find(Community.class, communityId);
            Community community = (Community) em.createNamedQuery("findCommunityById")
                    .setParameter("id", communityId)
                    .getSingleResult();

            Notice notice = new Notice(user, title, text, date, community);
            em.persist(notice);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    public List<Notice> findAllNotice() {
        try {
            return (List<Notice>) em.createNamedQuery("findAllNotice")
                    .getResultList();
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
    public Notice findNotice(Integer noticeid) {
        try {
            Notice notice = em.find(Notice.class, noticeid);

            return notice;
        } catch (Exception e) {

        }
        return null;
    }

    public void createPost(Integer userId, String title, String text, Integer communityId) {
        try {
//            User user = em.find(User.class, userId);
//            Community community = em.find(Community.class, communityId);
            User user = (User) em.createNamedQuery("findUserById")
                    .setParameter("id", userId)
                    .getSingleResult();

            Community community = (Community) em.createNamedQuery("findCommunityById")
                    .setParameter("id", communityId)
                    .getSingleResult();
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
    public List<Post> findAllPosts() {
        try {
            return (List<Post>) em.createNamedQuery("findAllPost")
                    .getResultList();
        } catch (Exception e) {

        }
        return null;
    }
    public Post findPost(Integer postid) {
        try {
            Post post = em.find(Post.class, postid);
            return post;
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
    public List<Bill> findBills(Integer communityId, Integer userId) {
        try {
            return (List<Bill>) em.createNamedQuery("findBillById")
                    .setParameter("id", userId)
                    .getResultList();
        } catch (Exception e) {

        }
        return null;
    }

    public Integer createHealth(Integer userId, String status, String position, float temperature) {
        try {
            User user = em.find(User.class, userId);
            Date date = new Date();
            Health health = new Health(user, status, position, temperature,date);

            em.persist(health);
            return health.getHealthid();
        } catch (Exception e) {

        }
        return null;
    }
    public void updateHealth(Integer healthId, String status, float temperature, String curr_position) {
        try {
            //Health health = em.find(Health.class, healthId);
            Health health = (Health) em.createNamedQuery("findHealthById")
                    .setParameter("id",healthId)
                    .getSingleResult();
            health.setStatus(status);
            health.setPosition(curr_position);
            health.setTemperature(temperature);

            em.merge(health);
        } catch (Exception e) {

        }
    }
    public Health findUserHealth(Integer healthid) {
        try {
            Health health = (Health) em.createNamedQuery("findHealthById")
                    .setParameter("id", healthid)
                    .getSingleResult();
            return health;
        } catch (Exception e) {

        }
        return null;
    }

//    public void createStore(String storename, String phonenumber) {
//        try {
//            Store store = new Store(storename,phonenumber);
//
//            em.persist(store);
//        } catch (Exception e) {
//
//        }
//    }
//    public void updateStore(Integer storeId, String storename, String phonenumber) {
//        try {
//            Store store = em.find(Store.class, storeId);
//            store.setStorename(storename);
//            store.setPhonenumber(phonenumber);
//
//            em.merge(store);
//        } catch (Exception e) {
//
//        }
//    }
//
//    public void createPurchasingAgent(String purchasingagentname, String phonenumber) {
//        try {
//            PurchasingAgent purchasingAgent = new PurchasingAgent(purchasingagentname,phonenumber);
//
//            em.persist(purchasingAgent);
//        } catch (Exception e) {
//
//        }
//    }
//    public void updatePurchasingAgent(Integer purchasingAgentId, String purchasingagentname, String phonenumber) {
//        try {
//            Store purchasingAgent = em.find(Store.class, purchasingAgentId);
//            purchasingAgent.setStorename(purchasingagentname);
//            purchasingAgent.setPhonenumber(phonenumber);
//
//            em.merge(purchasingAgent);
//        } catch (Exception e) {
//
//        }
//    }

//    public void createOrder(Integer userId,Integer communityId, Integer storeId, Integer purchasingAgentId,String details ,String status) {
//        try {
//            User user = em.find(User.class, userId);
//            Community community = em.find(Community.class, communityId);
//            Store store = em.find(Store.class, storeId);
//            PurchasingAgent purchasingAgent = em.find(PurchasingAgent.class, purchasingAgentId);
//            Date date = new Date();
//            Order order=new Order(date,details,status,community,user,store,purchasingAgent);
//
//            em.persist(order);
//        } catch (Exception e) {
//
//        }
//    }
//    public void updateOrder(Integer orderId,String details ,String status) {
//        try {
//            Order order=em.find(Order.class,orderId);
//            order.setDetails(details);
//            order.setStatus(status);
//
//            em.merge(order);
//        } catch (Exception e) {
//
//        }
//    }
//    public List<Order> findOrders(Integer userId) {
//        try {
//            return (List<Order>) em.createNamedQuery("findAllOrders")
//                    .setParameter("id", userId)
//                    .getSingleResult();
//        } catch (Exception e) {
//
//        }
//        return null;
//    }

    public Integer searchCommunityId(String communityname) {
        try {
            return (Integer) em.createNamedQuery("findCommunityByName")
                    .setParameter("name", communityname)
                    .getSingleResult();
        } catch (Exception e) {

        }
        return null;
    }
    public String findCommunityName(Integer communityid) {
        try {
//            Community community = em.find(Community.class, communityid);
//            Community community = (Community) em.createNamedQuery("findCommunityById")
//                    .setParameter("id", communityid)
//                    .getSingleResult();
           // return community.getCommunityname();
            String str = "Generals' Tomb";
            return str;
        } catch (Exception e) {

        }
        return null;
    }
    public void createCommunity(String communityname) {
        try {
            Community community = new Community(communityname);

            em.persist(community);
        } catch (Exception e) {

        }
    }

    public void createComplaint(Integer userid, Integer communityid, String title, String details) {
        try {
            Date date = new Date();
            User user = em.find(User.class, userid);
            Community community = em.find(Community.class, communityid);

            Complaint complaint = new Complaint(user, community, title, details, date);
            em.persist(complaint);
        } catch (Exception e) {

        }
    }
    public List<Complaint> findComplaints(Integer communityid) {
        try {
            return (List<Complaint>) em.createNamedQuery("findAllComplaint")
                    .getResultList();
        } catch (Exception e) {

        }
        return null;
    }

}
