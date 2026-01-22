package test;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.Configuration;
import java.io.File;

public class SimpleServer {
    public static void main(String[] args) {
        System.out.println("--- Hanmatsu Server Starting ---");
        
        // Set system properties for optimization
        System.setProperty("org.apache.tomcat.util.descriptor.tld.TldParser.skipValidation", "true");
        System.setProperty("org.apache.jasper.compiler.TldLocationsCache.noTldNextTime", "true");
        System.setProperty("org.eclipse.jetty.annotations.AnnotationConfiguration.maxScanWait", "1");
        System.setProperty("tomcat.util.scan.StandardJarScanner.jarsToSkip", "*.jar");
        System.setProperty("tomcat.util.scan.DefaultJarScanner.jarsToSkip", "*.jar");
        
        try {
            int port = 8895;
            Server server = new Server(port);

            WebAppContext webAppContext = new WebAppContext();
            webAppContext.setContextPath("/hanmatsu");
            
            // Use absolute path for resource base
            File webappDir = new File("src/main/webapp");
            String resourceBase = webappDir.getAbsolutePath();
            webAppContext.setResourceBase(resourceBase);
            System.out.println("Resource Base: " + resourceBase);

            // TLD scanning and Annotation scanning configuration
            // We need to include JSTL and other jars for the app to work correctly
            String jarPattern = ".*/.*\\.jar$|.*/classes/.*";
            webAppContext.setAttribute("org.eclipse.jetty.server.webapp.WebInfIncludeJarPattern", jarPattern);
            webAppContext.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", jarPattern);
            
            // Enable standard webapp features
            Configuration.ClassList classlist = Configuration.ClassList.setServerDefault(server);
            classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", 
                                "org.eclipse.jetty.annotations.AnnotationConfiguration");
            
            System.out.println("Configurations set up. Adding context listeners...");
            webAppContext.addEventListener(new javax.servlet.ServletContextListener() {
                @Override
                public void contextInitialized(javax.servlet.ServletContextEvent sce) {
                    System.out.println("✅ Context initialized: " + sce.getServletContext().getContextPath());
                }
                @Override
                public void contextDestroyed(javax.servlet.ServletContextEvent sce) {
                    System.out.println("❌ Context destroyed");
                }
            });

            // TLD 스캔을 위해 WEB-INF/lib의 jar 파일들을 포함시킵니다.
            webAppContext.setAttribute("org.eclipse.jetty.server.webapp.WebInfIncludeJarPattern", ".*/.*\\.jar$|.*/classes/.*");
             
            server.setHandler(webAppContext);

            server.addLifeCycleListener(new org.eclipse.jetty.util.component.AbstractLifeCycle.AbstractLifeCycleListener() {
                @Override
                public void lifeCycleStarted(org.eclipse.jetty.util.component.LifeCycle event) {
                    System.out.println("✨✨✨ SERVER IS READY AND LISTENING ON PORT " + port + " ✨✨✨");
                }
            });

            // Add a life cycle listener to see progress
            server.addLifeCycleListener(new org.eclipse.jetty.util.component.AbstractLifeCycle.AbstractLifeCycleListener() {
                @Override
                public void lifeCycleStarting(org.eclipse.jetty.util.component.LifeCycle event) {
                    System.out.println("🚀 Server starting...");
                }
                @Override
                public void lifeCycleStarted(org.eclipse.jetty.util.component.LifeCycle event) {
                    System.out.println("✨ Server started!");
                }
                @Override
                public void lifeCycleFailure(org.eclipse.jetty.util.component.LifeCycle event, Throwable cause) {
                    System.err.println("💥 Server failure!");
                    cause.printStackTrace();
                }
            });

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("🔔 Shutdown hook triggered!");
                try {
                    server.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
            
            System.out.println("Starting Jetty on port " + port + "...");
            try {
                server.start();
                System.out.println("✨ Server started successfully!");
                
                if (webAppContext.isFailed()) {
                    System.err.println("❌ WebAppContext failed to start!");
                    if (webAppContext.getUnavailableException() != null) {
                        webAppContext.getUnavailableException().printStackTrace();
                    }
                } else {
                    System.out.println("✅ WebAppContext started!");
                    System.out.println("URL: http://localhost:" + port + "/hanmatsu/main.do");
                }
                
                System.out.println("Entering server.join()...");
                server.join();
                System.out.println("🔔 server.join() returned!");
                
                // Absolute fallback to keep JVM alive
                while (server.isRunning()) {
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                System.err.println("💥 Server failed to start!");
                e.printStackTrace();
            }
        } catch (Throwable t) {
            System.err.println("💥 Fatal error in main!");
            t.printStackTrace();
        }
    }
}
