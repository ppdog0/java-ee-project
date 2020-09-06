package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;



import static javax.persistence.TemporalType.TIMESTAMP;


@Entity
@Table(name = "user")
//@NamedQuery(
//        name="findUserByIdUser",
//        query=
//        "SELECT u FROM User u " +
//        "WHERE u.iduser = :iduser "
//)
public class User implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private Integer id;
    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;
    private Set admincommuintys = new HashSet();
    private Set habitantcommunities= new HashSet();

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Community.class)
    @JoinTable(name = "admins",
            joinColumns = {
                    @JoinColumn(name = "userid", referencedColumnName = "userid")},
            inverseJoinColumns = {
                    @JoinColumn(name = "communityid", referencedColumnName = "communityid")})
    public Set getAdmincommuintys() {
        return admincommuintys;
    }

    @ManyToMany(mappedBy = "habitants")
    public Set<Community> getHabitantcommunities() {
        return habitantcommunities;
    }
//    public void setAdmincommuintys(Set admincommuintys) {
//        this.admincommuintys = admincommuintys;
//    }
}