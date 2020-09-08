package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "purchasingagent")
@NamedQueries({
        @NamedQuery(
                name = "findAllPurchasingAgent",
                query =
                        "select c FROM Post c " +
                                "ORDER BY c.purchasingagentid"
        )
})
public class PurchasingAgent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "purchasingagentid")
    private Integer purchasingagentid;
    private String purchasingagentname;
    private String phonenumber;

    @OneToMany(targetEntity=Order.class,mappedBy = "purchasingagent")
    private Set<Order> orders;

    public PurchasingAgent() {
    }

    public PurchasingAgent(String purchasingagentname, String phonenumber
    ) {
        this.purchasingagentname = purchasingagentname;
        this.phonenumber = phonenumber;
    }

    public Integer getPurchasingagentid() {
        return purchasingagentid;
    }
    public void setPurchasingagentid(Integer purchasingagentid){
        this.purchasingagentid=purchasingagentid;
    }

    public String getPurchasingagentname(){
        return this.purchasingagentname;
    }

    public void setPurchasingagentname(String purchasingagentname){
        this.purchasingagentname=purchasingagentname;
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