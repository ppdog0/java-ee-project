package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "store")
@NamedQueries({
        @NamedQuery(
                name = "findAllPurchasingAgent",
                query =
                        "select c FROM Post c " +
                                "ORDER BY c.storeid"
        )
})
public class Store implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "storeid")
    private Integer storeid;
    private String storename;
    private String phonenumber;

    @OneToMany(targetEntity=Order.class,mappedBy = "store")
    private Set<Order> orders;

    public Store() {
    }

    public Store(String storename, String phonenumber
    ) {
        this.storename = storename;
        this.phonenumber = phonenumber;
    }

    public Integer getStoreid() {
        return storeid;
    }
    public void setStoreid(Integer storeid){
        this.storeid=storeid;
    }

    public String getStorename(){
        return this.storename;
    }

    public void setStorename(String storename){
        this.storename =storename;
    }

    public String getPhonenumber(){
        return this.phonenumber;
    }

    public void setPhonenumber(String phonenumber){
        this.phonenumber=phonenumber;
    }
    //    public void setAdmincommuintys(Set admincommuintys) {
//        this.admincommuintys = admincommuintys;
//    }
}