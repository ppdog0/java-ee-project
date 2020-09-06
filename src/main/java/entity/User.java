package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.util.Collection;
import java.util.Date;

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


    @Column(name = "userid")
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
}