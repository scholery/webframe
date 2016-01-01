

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class StartWebApplication {
	public static void main(String[] args) throws Exception {
		
		String jetty_home = System.getProperty("jetty.home",".");
				 
        Server server = new Server(8080);
         
        WebAppContext webapp = new WebAppContext();
        
        webapp.setContextPath("framework"); 
        webapp.setWar(jetty_home+"/WebContent");
        
        webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
        
        server.setHandler(webapp);
        
        server.start();
        server.join();

		System.out.println("Jetty Web Server Started");


	}
}