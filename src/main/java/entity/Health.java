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
                name = "findHealthById",
                query = "select c FROM Health c " +
                                "WHERE c.user.id = :id "
        )
})
public class Health implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "healthid")
    private Integer id;

    private String status;
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
                String status,
                String currposition,
                Date date) {
        this.user = user;
        this.status = status;
        this.prevposition = this.currposition;
        this.currposition = currposition;
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
        this.prevposition = this.currposition;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //    public void setAdmincommuintys(Set admincommuintys) {
//        this.admincommuintys = admincommuintys;
//    }
}