package allen.gong.spring.oauth2.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationListener;
import org.springframework.session.events.AbstractSessionEvent;

public class SessionListener implements HttpSessionListener, ApplicationListener<AbstractSessionEvent>{
	
//	private final int SESSION_TIMEOUT = 1 * 60;
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("***************session created****************" + event.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("***************session destroyed****************" + event.getSession().getId());
	}

	@Override
	public void onApplicationEvent(AbstractSessionEvent event) {
		System.out.println("***************session event****************" + event.getSession().getId());
	}
}
