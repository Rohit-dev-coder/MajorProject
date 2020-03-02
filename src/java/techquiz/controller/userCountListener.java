
package techquiz.controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class userCountListener implements HttpSessionListener {

    public static int usercount;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ++usercount;
        System.out.println("++usercount: "+usercount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        --usercount;
        System.out.println("--usercount: "+usercount);
    }
}
