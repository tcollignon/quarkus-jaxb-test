# quarkus-jaxb-test

Try to reproduce a problem with quarkus in dev mode with jaxb.

## It works in this way

### Unit test

Run _TestNewsResource_ (with IDE or with Maven) -> OK

### Package and run
 
` mvn package`
 
` java -jar target/quarkus.jaxb.test-1.0-SNAPSHOT-runner.jar `
 
Then call the rest service :

` curl localhost:8080/getnews`
 
It output : 

`[{"author":"cheval","content":"\nVoici le lien vers la revue de presse front de la semaine :\n","description":"\nVoici le lien vers la revue de presse front de la semaine :\n","title":"veille techno • Revue de presse front du 18/04/2019","url":"http://eforum.uem.lan/viewtopic.php?t=2905&p=11262#p11262","version":0}]
`
## It not works in this way

` ./mvnw compile quarkus:dev`
 
Then call the rest service : 

` curl localhost:8080/getnews`
 
It output nothing, and we have this trace in quarkus console : 

`
17:23 $ ./mvnw compile quarkus:dev
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------< org.tcollignon:quarkus.jaxb.test >------------------
[INFO] Building quarkus.jaxb.test 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ quarkus.jaxb.test ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 3 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ quarkus.jaxb.test ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- quarkus-maven-plugin:0.13.3:dev (default-cli) @ quarkus.jaxb.test ---
Listening for transport dt_socket at address: 5005
2019-04-19 17:24:26,099 INFO  [io.qua.dep.QuarkusAugmentor] (main) Beginning quarkus augmentation
2019-04-19 17:24:27,041 INFO  [io.qua.dep.QuarkusAugmentor] (main) Quarkus augmentation completed in 942ms
2019-04-19 17:24:27,613 INFO  [io.quarkus] (main) Quarkus 0.13.3 started in 1.702s. Listening on: http://[::]:8080
2019-04-19 17:24:27,615 INFO  [io.quarkus] (main) Installed features: [cdi, resteasy, resteasy-jsonb]
javax.xml.bind.UnmarshalException: élément inattendu (URI : "http://www.w3.org/2005/Atom", local : "feed"). Les éléments attendus sont <{}feed>
	at com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallingContext.handleEvent(UnmarshallingContext.java:726)
	at com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader.reportError(Loader.java:247)
	at com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader.reportError(Loader.java:242)
	at com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader.reportUnexpectedChildElement(Loader.java:109)
	at com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallingContext$DefaultRootLoader.childElement(UnmarshallingContext.java:1131)
	at com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallingContext._startElement(UnmarshallingContext.java:556)
	at com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallingContext.startElement(UnmarshallingContext.java:538)
	at com.sun.xml.internal.bind.v2.runtime.unmarshaller.SAXConnector.startElement(SAXConnector.java:153)
	at com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser.startElement(AbstractSAXParser.java:509)
	at com.sun.org.apache.xerces.internal.impl.XMLNSDocumentScannerImpl.scanStartElement(XMLNSDocumentScannerImpl.java:374)
	at com.sun.org.apache.xerces.internal.impl.XMLNSDocumentScannerImpl$NSContentDriver.scanRootElementHook(XMLNSDocumentScannerImpl.java:613)
	at com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl$FragmentContentDriver.next(XMLDocumentFragmentScannerImpl.java:3132)
	at com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl$PrologDriver.next(XMLDocumentScannerImpl.java:852)
	at com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl.next(XMLDocumentScannerImpl.java:602)
	at com.sun.org.apache.xerces.internal.impl.XMLNSDocumentScannerImpl.next(XMLNSDocumentScannerImpl.java:112)
	at com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.scanDocument(XMLDocumentFragmentScannerImpl.java:505)
	at com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:842)
	at com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:771)
	at com.sun.org.apache.xerces.internal.parsers.XMLParser.parse(XMLParser.java:141)
	at com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser.parse(AbstractSAXParser.java:1213)
	at com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl$JAXPSAXParser.parse(SAXParserImpl.java:643)
	at com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallerImpl.unmarshal0(UnmarshallerImpl.java:243)
	at com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallerImpl.unmarshal(UnmarshallerImpl.java:214)
	at javax.xml.bind.helpers.AbstractUnmarshallerImpl.unmarshal(AbstractUnmarshallerImpl.java:157)
	at javax.xml.bind.helpers.AbstractUnmarshallerImpl.unmarshal(AbstractUnmarshallerImpl.java:204)
	at mapper.process.UnmarshalRSSProcess.getFeed(UnmarshalRSSProcess.java:37)
	at mapper.process.UnmarshalRSSProcess.retrieveLastNews(UnmarshalRSSProcess.java:30)
	at mapper.process.UnmarshalRSSProcess_ClientProxy.retrieveLastNews(Unknown Source)
	at rest.NewsResource.getNews(NewsResource.java:27)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.jboss.resteasy.core.MethodInjectorImpl.invoke(MethodInjectorImpl.java:151)
	at org.jboss.resteasy.core.MethodInjectorImpl.lambda$invoke$3(MethodInjectorImpl.java:122)
	at java.util.concurrent.CompletableFuture.uniApply(CompletableFuture.java:602)
	at java.util.concurrent.CompletableFuture.uniApplyStage(CompletableFuture.java:614)
	at java.util.concurrent.CompletableFuture.thenApply(CompletableFuture.java:1983)
	at java.util.concurrent.CompletableFuture.thenApply(CompletableFuture.java:110)
	at org.jboss.resteasy.core.MethodInjectorImpl.invoke(MethodInjectorImpl.java:122)
	at org.jboss.resteasy.core.ResourceMethodInvoker.internalInvokeOnTarget(ResourceMethodInvoker.java:568)
	at org.jboss.resteasy.core.ResourceMethodInvoker.invokeOnTargetAfterFilter(ResourceMethodInvoker.java:442)
	at org.jboss.resteasy.core.ResourceMethodInvoker.lambda$invokeOnTarget$2(ResourceMethodInvoker.java:396)
	at org.jboss.resteasy.core.interception.jaxrs.PreMatchContainerRequestContext.filter(PreMatchContainerRequestContext.java:362)
	at org.jboss.resteasy.core.ResourceMethodInvoker.invokeOnTarget(ResourceMethodInvoker.java:398)
	at org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:367)
	at org.jboss.resteasy.core.ResourceMethodInvoker.lambda$invoke$1(ResourceMethodInvoker.java:341)
	at java.util.concurrent.CompletableFuture.uniComposeStage(CompletableFuture.java:981)
	at java.util.concurrent.CompletableFuture.thenCompose(CompletableFuture.java:2124)
	at java.util.concurrent.CompletableFuture.thenCompose(CompletableFuture.java:110)
	at org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:341)
	at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:477)
	at org.jboss.resteasy.core.SynchronousDispatcher.lambda$invoke$4(SynchronousDispatcher.java:252)
	at org.jboss.resteasy.core.SynchronousDispatcher.lambda$preprocess$0(SynchronousDispatcher.java:153)
	at org.jboss.resteasy.core.interception.jaxrs.PreMatchContainerRequestContext.filter(PreMatchContainerRequestContext.java:362)
	at org.jboss.resteasy.core.SynchronousDispatcher.preprocess(SynchronousDispatcher.java:156)
	at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:238)
	at org.jboss.resteasy.plugins.server.servlet.ServletContainerDispatcher.service(ServletContainerDispatcher.java:234)
	at io.quarkus.resteasy.runtime.ResteasyFilter$ResteasyResponseWrapper.sendError(ResteasyFilter.java:72)
	at io.undertow.servlet.handlers.DefaultServlet.doGet(DefaultServlet.java:175)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:686)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:791)
	at io.undertow.servlet.handlers.ServletHandler.handleRequest(ServletHandler.java:74)
	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:129)
	at io.quarkus.resteasy.runtime.ResteasyFilter.doFilter(ResteasyFilter.java:43)
	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)
	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)
	at io.undertow.servlet.handlers.FilterHandler.handleRequest(FilterHandler.java:84)
	at io.undertow.servlet.handlers.security.ServletSecurityRoleHandler.handleRequest(ServletSecurityRoleHandler.java:62)
	at io.undertow.servlet.handlers.ServletChain$1.handleRequest(ServletChain.java:68)
	at io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36)
	at io.undertow.servlet.handlers.security.SSLInformationAssociationHandler.handleRequest(SSLInformationAssociationHandler.java:132)
	at io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler.handleRequest(ServletAuthenticationCallHandler.java:57)
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at io.undertow.security.handlers.AbstractConfidentialityHandler.handleRequest(AbstractConfidentialityHandler.java:46)
	at io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler.handleRequest(ServletConfidentialityConstraintHandler.java:64)
	at io.undertow.security.handlers.AuthenticationMechanismsHandler.handleRequest(AuthenticationMechanismsHandler.java:60)
	at io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler.handleRequest(CachedAuthenticatedSessionHandler.java:77)
	at io.undertow.security.handlers.AbstractSecurityContextAssociationHandler.handleRequest(AbstractSecurityContextAssociationHandler.java:43)
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:292)
	at io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:81)
	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:138)
	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:135)
	at io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48)
	at io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43)
	at io.quarkus.undertow.runtime.UndertowDeploymentTemplate$7$1$1.call(UndertowDeploymentTemplate.java:416)
	at io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:272)
	at io.undertow.servlet.handlers.ServletInitialHandler.handleRequest(ServletInitialHandler.java:197)
	at io.undertow.server.handlers.HttpContinueReadHandler.handleRequest(HttpContinueReadHandler.java:65)
	at io.quarkus.undertow.runtime.UndertowDeploymentTemplate$1.handleRequest(UndertowDeploymentTemplate.java:89)
	at io.undertow.server.handlers.CanonicalPathHandler.handleRequest(CanonicalPathHandler.java:49)
	at io.quarkus.undertow.deployment.devmode.UndertowHotReplacementSetup.handleHotDeploymentRequest(UndertowHotReplacementSetup.java:66)
	at io.quarkus.undertow.deployment.devmode.UndertowHotReplacementSetup$1$1.handleRequest(UndertowHotReplacementSetup.java:37)
	at io.undertow.server.Connectors.executeRootHandler(Connectors.java:364)
	at io.undertow.server.HttpServerExchange$1.run(HttpServerExchange.java:830)
	at org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)
	at org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:1998)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1525)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1382)
	at java.lang.Thread.run(Thread.java:748)
java.lang.NullPointerException
	at mapper.process.UnmarshalRSSProcess.getEForumNews(UnmarshalRSSProcess.java:46)
	at mapper.process.UnmarshalRSSProcess.retrieveLastNews(UnmarshalRSSProcess.java:30)
	at mapper.process.UnmarshalRSSProcess_ClientProxy.retrieveLastNews(Unknown Source)
	at rest.NewsResource.getNews(NewsResource.java:27)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.jboss.resteasy.core.MethodInjectorImpl.invoke(MethodInjectorImpl.java:151)
	at org.jboss.resteasy.core.MethodInjectorImpl.lambda$invoke$3(MethodInjectorImpl.java:122)
	at java.util.concurrent.CompletableFuture.uniApply(CompletableFuture.java:602)
	at java.util.concurrent.CompletableFuture.uniApplyStage(CompletableFuture.java:614)
	at java.util.concurrent.CompletableFuture.thenApply(CompletableFuture.java:1983)
	at java.util.concurrent.CompletableFuture.thenApply(CompletableFuture.java:110)
	at org.jboss.resteasy.core.MethodInjectorImpl.invoke(MethodInjectorImpl.java:122)
	at org.jboss.resteasy.core.ResourceMethodInvoker.internalInvokeOnTarget(ResourceMethodInvoker.java:568)
	at org.jboss.resteasy.core.ResourceMethodInvoker.invokeOnTargetAfterFilter(ResourceMethodInvoker.java:442)
	at org.jboss.resteasy.core.ResourceMethodInvoker.lambda$invokeOnTarget$2(ResourceMethodInvoker.java:396)
	at org.jboss.resteasy.core.interception.jaxrs.PreMatchContainerRequestContext.filter(PreMatchContainerRequestContext.java:362)
	at org.jboss.resteasy.core.ResourceMethodInvoker.invokeOnTarget(ResourceMethodInvoker.java:398)
	at org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:367)
	at org.jboss.resteasy.core.ResourceMethodInvoker.lambda$invoke$1(ResourceMethodInvoker.java:341)
	at java.util.concurrent.CompletableFuture.uniComposeStage(CompletableFuture.java:981)
	at java.util.concurrent.CompletableFuture.thenCompose(CompletableFuture.java:2124)
	at java.util.concurrent.CompletableFuture.thenCompose(CompletableFuture.java:110)
	at org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:341)
	at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:477)
	at org.jboss.resteasy.core.SynchronousDispatcher.lambda$invoke$4(SynchronousDispatcher.java:252)
	at org.jboss.resteasy.core.SynchronousDispatcher.lambda$preprocess$0(SynchronousDispatcher.java:153)
	at org.jboss.resteasy.core.interception.jaxrs.PreMatchContainerRequestContext.filter(PreMatchContainerRequestContext.java:362)
	at org.jboss.resteasy.core.SynchronousDispatcher.preprocess(SynchronousDispatcher.java:156)
	at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:238)
	at org.jboss.resteasy.plugins.server.servlet.ServletContainerDispatcher.service(ServletContainerDispatcher.java:234)
	at io.quarkus.resteasy.runtime.ResteasyFilter$ResteasyResponseWrapper.sendError(ResteasyFilter.java:72)
	at io.undertow.servlet.handlers.DefaultServlet.doGet(DefaultServlet.java:175)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:686)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:791)
	at io.undertow.servlet.handlers.ServletHandler.handleRequest(ServletHandler.java:74)
	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:129)
	at io.quarkus.resteasy.runtime.ResteasyFilter.doFilter(ResteasyFilter.java:43)
	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)
	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)
	at io.undertow.servlet.handlers.FilterHandler.handleRequest(FilterHandler.java:84)
	at io.undertow.servlet.handlers.security.ServletSecurityRoleHandler.handleRequest(ServletSecurityRoleHandler.java:62)
	at io.undertow.servlet.handlers.ServletChain$1.handleRequest(ServletChain.java:68)
	at io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36)
	at io.undertow.servlet.handlers.security.SSLInformationAssociationHandler.handleRequest(SSLInformationAssociationHandler.java:132)
	at io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler.handleRequest(ServletAuthenticationCallHandler.java:57)
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at io.undertow.security.handlers.AbstractConfidentialityHandler.handleRequest(AbstractConfidentialityHandler.java:46)
	at io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler.handleRequest(ServletConfidentialityConstraintHandler.java:64)
	at io.undertow.security.handlers.AuthenticationMechanismsHandler.handleRequest(AuthenticationMechanismsHandler.java:60)
	at io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler.handleRequest(CachedAuthenticatedSessionHandler.java:77)
	at io.undertow.security.handlers.AbstractSecurityContextAssociationHandler.handleRequest(AbstractSecurityContextAssociationHandler.java:43)
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:292)
	at io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:81)
	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:138)
	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:135)
	at io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48)
	at io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43)
	at io.quarkus.undertow.runtime.UndertowDeploymentTemplate$7$1$1.call(UndertowDeploymentTemplate.java:416)
	at io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:272)
	at io.undertow.servlet.handlers.ServletInitialHandler.handleRequest(ServletInitialHandler.java:197)
	at io.undertow.server.handlers.HttpContinueReadHandler.handleRequest(HttpContinueReadHandler.java:65)
	at io.quarkus.undertow.runtime.UndertowDeploymentTemplate$1.handleRequest(UndertowDeploymentTemplate.java:89)
	at io.undertow.server.handlers.CanonicalPathHandler.handleRequest(CanonicalPathHandler.java:49)
	at io.quarkus.undertow.deployment.devmode.UndertowHotReplacementSetup.handleHotDeploymentRequest(UndertowHotReplacementSetup.java:66)
	at io.quarkus.undertow.deployment.devmode.UndertowHotReplacementSetup$1$1.handleRequest(UndertowHotReplacementSetup.java:37)
	at io.undertow.server.Connectors.executeRootHandler(Connectors.java:364)
	at io.undertow.server.HttpServerExchange$1.run(HttpServerExchange.java:830)
	at org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)
	at org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:1998)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1525)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1382)
	at java.lang.Thread.run(Thread.java:748)
`