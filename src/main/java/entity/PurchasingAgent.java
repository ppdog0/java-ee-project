package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "purchasingagent")
@NamedQueries({
        @NamedQuery(
                name = "findAllPurchasingAgent",
                query =
                        "select c FROM PurchasingAgent c " +
                                "ORDER BY c.agentid"
        )
})
public class PurchasingAgent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "agentid")
    private Integer agentid;
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

    public Integer getAgentid() {
        return agentid;
    }
    public void setAgentid(Integer purchasingagentid){
        this.agentid =purchasingagentid;
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
//
}