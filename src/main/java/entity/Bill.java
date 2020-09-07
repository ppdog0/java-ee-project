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
@Table(name = "bills")
@NamedQueries({
        @NamedQuery(
                name = "findUserByIdUser",
                query =
                        "select c FROM Bills c " +
                                "WHERE c.id = :id "
        ),
        @NamedQuery(
                name = "findAllUsers",
                query =
                        "select c FROM Bills c " +
                                "ORDER BY c.id"
        )
})
public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "billsid")
    private Integer id;
    private float price;
    private String type;
    private Boolean status;
    private String title;
    private String details;
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "communityid", nullable = false)
    private Community community;


    public Bill() {
    }

    public Bill(User user,
                Community community,
                String title,
                String details,
                float price,
                Boolean status,
                Date date) {
        this.user = user;
        this.community = community;
        this.title = title;
        this.details = details;
        this.price = price;
        this.status = status;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Community getCommunity() {
        return this.community;
    }

    public void setUser(Community community) {
        this.community = community;
    }
//    public void setAdmincommuintys(Set admincommuintys) {
//        this.admincommuintys = admincommuintys;
//    }
}