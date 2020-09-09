package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "Notice")
@NamedQueries({
        @NamedQuery(
                name = "findAllNotice",
                query =
                        "select c FROM Notice c " +
                                "ORDER BY c.noticeid"
        )
})
public class Notice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "noticeid")
    private Integer noticeid;

    private String title;

    private String details;
    private Date date;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userid", nullable=false)
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="communityid", nullable=false)
    private Community community;



    public Notice() {
    }

    public Notice(User user,
                  String title,
                  String details,
                  Date date,
                  Community community) {
        this.user = user;
        this.title = title;
        this.details = details;
        this.date = date;
        this.community = community;
    }

    public int getNoticeid() {
        return this.noticeid;
    }

    public void setNoticeid(int id) {
        this.noticeid = id;
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

    public void setDetails(String details) {
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