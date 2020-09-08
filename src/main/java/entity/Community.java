package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "community")
//@NamedQuery(
//        name="findUserByIdUser",
//        query=
//        "SELECT u FROM User u " +
//        "WHERE u.iduser = :iduser "
//)
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

    @OneToMany(mappedBy="complaintid")
    private Set<Complaint> complaints;

    @OneToMany(mappedBy="noticeid")
    private Set<Notice> notices;

    @OneToMany(mappedBy="postid")
    private Set<Post> posts;

    @OneToMany(mappedBy = "healthid")
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
    public Set<Community> getAdminusers() {
        return adminusers;
    }

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Community.class)
    @JoinTable(name = "habitants",
            joinColumns = {
                    @JoinColumn(name = "communityid", referencedColumnName = "communityid")},
            inverseJoinColumns = {
                    @JoinColumn(name = "userid", referencedColumnName = "userid")})
    public Set getHabitants() {
        return habitantusers;
    }

    public boolean checkAdminCommunity(User user){
        if(this.habitantusers.contains(user))
            return true;
        else
            return false;
    }
}