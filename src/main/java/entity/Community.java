package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Community")
@NamedQuery(
        name="findCommunityByName",
        query=
        "SELECT u.id FROM Community u " +
        "WHERE u.communityname = :name "
)
public class Community implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "communityid")
    private Integer id;

    @Column(name = "communityname")
    private String communityname;

    private Set habitantusers = new HashSet();
    private Set adminusers = new HashSet();

    @OneToMany(targetEntity=Complaint.class,mappedBy = "community")
    private Set<Complaint> complaints;

    @OneToMany(targetEntity=Notice.class,mappedBy = "community")
    private Set<Notice> notices;

    @OneToMany(targetEntity=Post.class,mappedBy = "community")
    private Set<Post> posts;

    @OneToMany(targetEntity=Health.class,mappedBy = "community")
    private Set<Health> healths;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommunityname() {
        return this.communityname;
    }

    public void setCommunityname(String communityname) {
        this.communityname = communityname;
    }

    @ManyToMany(mappedBy = "admins")
    public Set<User> getAdminusers() {
        return this.adminusers;
    }

    public void setAdminusers(Set<User> adminusers){
        this.adminusers=adminusers;
    }

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinTable(name = "habitants",
            joinColumns = {
                    @JoinColumn(name = "communityid", referencedColumnName = "communityid")},
            inverseJoinColumns = {
                    @JoinColumn(name = "userid", referencedColumnName = "userid")})
    public Set<User> getHabitantusers() {
        return this.habitantusers;
    }

    public void setHabitantusers(Set<User> habitantusers){
        this.habitantusers=habitantusers;
    }

    public boolean checkAdminCommunity(User user){
        if(this.adminusers.contains(user))
            return true;
        else
            return false;
    }

    public boolean checkHabitantCommunity(User user){
        if(this.habitantusers.contains(user))
            return true;
        else
            return false;
    }
}