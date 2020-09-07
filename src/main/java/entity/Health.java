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
@Table(name = "Health")
@NamedQueries({
        @NamedQuery(
                name = "findUserByIdUser",
                query =
                        "select c FROM Health c " +
                                "WHERE c.id = :id "
        ),
        @NamedQuery(
                name = "findAllUsers",
                query =
                        "select c FROM Health c " +
                                "ORDER BY c.id"
        )
})
public class Health implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "healthid")
    private Integer id;

    private String currposition;
    private String prevposition;


    private Date date;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userid", nullable=false)
    private User user;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="communityid", nullable=false)
    private Community community;



    public Health() {
    }

    public Health(User user,
                String currposition,
                String prevposition,
                Date date) {
        this.currposition = currposition;
        this.prevposition = prevposition;
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrposition() {
        return this.currposition;
    }

    public void setCurrposition(String currposition) {
        this.currposition = currposition;
    }

    public String getPrevposition() {
        return this.prevposition;
    }

    public void setPrevposition(String prevposition) {
        this.prevposition = prevposition;
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