package web;

import ejb.RequestBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

@Named
@RequestScoped
public class Manager {

    @EJB
    private RequestBean request;
    private String userName;
    private String password;
    private String communityname;

    public String addUser() {
        request.createUser(userName, password);
        return "response";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCommunityname() {
        return communityname;
    }

    public void setCommunityname(String password) {
        this.communityname = communityname;
    }
}
