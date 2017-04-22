package main.listeners;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Kuznetsov on 20/04/2017.
 */

public class AppStartListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        PropertyConfigurator.configure(AppStartListener.class.getClassLoader().getResource("log4j.properties"));
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}