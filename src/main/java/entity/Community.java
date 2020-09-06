package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;

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



    @Column(name = "userid")
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
}