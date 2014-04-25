package edu.cmpe283.hostCollector.manager;

import com.vmware.vim25.mo.ServiceInstance;
import edu.cmpe283.hostCollector.config.Config;

import java.net.URL;


/**
 * User: vplociennik
 * Date: 11/21/13
 * Time: 2:24 AM
 */
public class LoginManager {

    public ServiceInstance connect(){

        if(valid()){
            try {
                return new ServiceInstance(new URL(Config.URL), Config.LOGIN, Config.PASSWORD, true);
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    private boolean valid() {
        return Config.URL != null && Config.LOGIN != null && Config.PASSWORD != null && !Config.URL.isEmpty() && !Config.LOGIN.isEmpty() && !Config.PASSWORD.isEmpty();
    }

}
