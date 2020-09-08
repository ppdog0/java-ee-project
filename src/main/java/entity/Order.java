package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "order")
@NamedQueries({
        @NamedQuery(
                name = "findOrderById",
                query = "select c FROM Order c " +
                        "WHERE c.orderid = :id "
        )
})
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderid")
    private Integer orderid;
    private String details;
    private String status;
    private Date date;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userid", nullable=false)
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="communityid", nullable=false)
    private Community community;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="purchasingagentid", nullable=false)
    private PurchasingAgent purchasingagent;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="storeid", nullable=false)
    private Store store;

    public Order() {
    }

    public Order(Date date,String details, String status, Community community, User user, Store store, PurchasingAgent purchasingAgent
    ) {
        this.date=date;
        this.details = details;
        this.status = status;
        this.community=community;
        this.user=user;
        this.store=store;
        this.purchasingagent=purchasingAgent;
    }

    public Integer getOrderid() {
        return orderid;
    }
    public void setOrderid(Integer storeid){
        this.orderid =storeid;
    }

    public String getDetails(){
        return this.details;
    }

    public void setDetails(String details){
        this.details =details;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status =status;
    }

    public User getUser(){return this.user;}

    public void setUser(User user){this.user=user; }

    public Community getCommunity(){return this.community;}

    public void setCommunity(Community community){this.community=community; }

    public Store getStore(){return this.store;}

    public void setStore(Store store){this.store=store; }
    //    public void setAdmincommuintys(Set admincommuintys) {
//        this.admincommuintys = admincommuintys;
//    }
}