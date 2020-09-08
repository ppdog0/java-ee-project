package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "complaint")
@NamedQueries({
        @NamedQuery(
                name = "findUserByIdUser",
                query =
                        "select c FROM Complaint c " +
                                "WHERE c.complaintid = :id "
        ),
        @NamedQuery(
                name = "findAllUsers",
                query =
                        "select c FROM Complaint c " +
                                "ORDER BY c.complaintid"
        )
})
public class Complaint implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "complaintid")
    private Integer complaintid;

    private String title;

    private String details;
    private Date date;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userid", nullable=false)
    private User user;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="communityid", nullable=false)
    private Community community;



    public Complaint() {
    }

    public Complaint(String title,
                     String details,
                     Date date) {
        this.title = title;
        this.details = details;
        this.date = date;
    }

    public int getComplaintid() {
        return this.complaintid;
    }

    public void setComplaintid(int id) {
        this.complaintid = id;
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