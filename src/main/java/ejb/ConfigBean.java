package ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ConfigBean {
    @EJB
    private RequestBean request;

    @PostConstruct
    public void createData() {}

    @PreDestroy
    public void deleteData() {}
}
