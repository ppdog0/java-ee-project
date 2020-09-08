package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;


import static javax.persistence.TemporalType.TIMESTAMP;


@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(
                name = "findUserById",
                query =
                        "SELECT u FROM User u " +
                                "WHERE u.id = :id "
        ),
        @NamedQuery(
                name = "findUserByName",
                query =
                        "select u.id FROM User u " +
                                "WHERE u.username = :name"
        )
})
public class User implements Serializable {
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
    private Set habitantcommunities = new HashSet();

    @OneToMany(targetEntity=Complaint.class,mappedBy = "user")
    private Set<Complaint> complaints;

    @OneToMany(targetEntity=Notice.class,mappedBy = "user")
    private Set<Notice> notices;

    @OneToMany(targetEntity=Post.class,mappedBy = "user")
    private Set<Post> posts;

    @OneToOne(targetEntity=Health.class,mappedBy = "user")
    private Health health;

    @OneToMany(targetEntity=Order.class,mappedBy = "user")
    private Set<Order> orders;

    public User() {
    }

    public User(String password,
                String username) {
        this.password = password;
        this.username = username;
    }

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
    public Set<Community> getAdmincommuintys() {
        return this.admincommuintys;
    }

    public void setAdmincommuintys(Set<Community> admincommuintys){
        this.admincommuintys=admincommuintys;
    }

    @ManyToMany(mappedBy = "habitants")
    public Set<Community> getHabitantcommunities() {
        return this.habitantcommunities;
    }

    public void setHabitantcommunities(Set<Community> habitantcommunities){
        this.habitantcommunities=habitantcommunities;
    }

    public boolean checkAdminCommunity(Community community){
        return this.admincommuintys.contains(community);
    }

    public boolean checkHabitantCommunity(Community community){
        return this.habitantcommunities.contains(community);
    }

//    public void setAdmincommuintys(Set admincommuintys) {
//        this.admincommuintys = admincommuintys;
//    }
}