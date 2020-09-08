package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "health")
@NamedQueries({
        @NamedQuery(
                name = "findHealthById",
                query = "select c FROM Health c " +
                                "WHERE c.healthid = :id "
        )
})
public class Health implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "healthid")
    private Integer healthid;

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

    public int getHealthid() {
        return this.healthid;
    }

    public void setHealthid(int id) {
        this.healthid = id;
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