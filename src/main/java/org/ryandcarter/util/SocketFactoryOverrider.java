package org.ryandcarter.util;

import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.log4j.Logger;
import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextAware;
import org.mule.api.context.notification.MuleContextNotificationListener;
import org.mule.context.notification.MuleContextNotification;


public class SocketFactoryOverrider implements
		MuleContextNotificationListener<MuleContextNotification>,
		MuleContextAware {

   private MuleContext context;
	
	private final static Logger log = Logger.getLogger(SocketFactoryOverrider.class);

	@Override
	public void onNotification(MuleContextNotification notification) {
		
		if (notification.getAction() == MuleContextNotification.CONTEXT_STARTED) {
		   log.debug("Initializing httpclient protcol with overridden SocketFactory");
		   Protocol.registerProtocol("http", new Protocol("http", new OSProtocolSocketFactory(), 80));
		   Protocol.registerProtocol("https", new Protocol("https", new OSProtocolSocketFactory(), 80));
		}

	}

	@Override
	public void setMuleContext(MuleContext context) {
		this.context = context;

	}

   public MuleContext getContext() {
      return context;
   }

}
