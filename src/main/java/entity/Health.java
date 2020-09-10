package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "health")
@NamedQueries({
        @NamedQuery(
                name = "findHealthByUserId",
                query = "select c FROM Health c " +
                        "WHERE c.user.id = :id "
        )
})
public class Health implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "healthid")
    private Integer healthid;

    private String status;
    private String position;
    private float temperature;
    private Date date;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private User user;




    public Health() {
    }

    public Health(User user,
                  String status,
                  String position,
                  float temperature,
                  Date date) {
        this.user = user;
        this.status = status;
        this.temperature = temperature;
        this.position = position;
        this.date = date;
    }

    public int getHealthid() {
        return this.healthid;
    }

    public void setHealthid(int id) {
        this.healthid = id;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getTemperature() {
        return this.temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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