package entity;

import entity.Community;
import entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;


import static javax.persistence.TemporalType.TIMESTAMP;


@Entity
@Table(name = "Post")
@NamedQueries({
        @NamedQuery(
                name = "findAllPost",
                query =
                        "select c FROM Post c " +
                                "ORDER BY c.id"
        )
})
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "postid")
    private Integer id;

    private String title;

    private String details;
    private Date date;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userid", nullable=false)
    private User user;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="communityid", nullable=false)
    private Community community;



    public Post() {
    }

    public Post(User user,
                Community community,
                String title,
                String details,
                Date date) {
        this.user = user;
        this.community = community;
        this.title = title;
        this.details = details;
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String username) {
        this.title = title;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String password) {
        this.details = details;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser(){return this.user;}

    public void setUser(User user){this.user=user; }

    public Community getCommunity(){return this.community;}

    public void setUser(Community community){this.community=community; }
//    public void setAdmincommuintys(Set admincommuintys) {
//        this.admincommuintys = admincommuintys;
//    }
}