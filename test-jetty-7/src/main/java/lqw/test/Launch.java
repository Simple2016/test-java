package lqw.test;

import lqw.test.servlet.AsyncEchoServlet;
import lqw.test.servlet.HelloServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.xml.XmlConfiguration;


/**
 * Hello world!
 */
public class Launch {
    public static void main(String[] args) throws Exception {

        Resource fileserver_xml = Resource.newSystemResource("fileserver.xml");
        XmlConfiguration xmlConfiguration = new XmlConfiguration(fileserver_xml.getInputStream());
        Server server = (Server) xmlConfiguration.configure();
        server.start();
        server.join();
    }

//    public static void main(String[] args) throws Exception {
//        Server server = new Server();
//
//        SelectChannelConnector connector =new SelectChannelConnector();
//        connector.setPort(8080);
//        server.setConnectors(new Connector[]{connector});
//
//        ServletContextHandler context = new ServletContextHandler();
//        context.setContextPath("/");
//        context.addServlet(HelloServlet.class, "/hello/*");
//        context.addServlet(AsyncEchoServlet.class, "/echo/*");
//        HandlerCollection handlers = new HandlerCollection();
//        handlers.setHandlers(new Handler[]{context, new DefaultHandler()});
//        server.setHandler(handlers);
//
//        server.start();
//        server.join();
//    }


}
