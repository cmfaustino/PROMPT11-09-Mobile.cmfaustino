package prompt.yamba;

import java.util.concurrent.ConcurrentHashMap;
import winterwell.jtwitter.Twitter;

public final class TimelineStoreBasica {
	private ConcurrentHashMap<Integer, Twitter.Status> _store;
	
	public TimelineStoreBasica(){
		_store = new ConcurrentHashMap<Integer, Twitter.Status>();
	}
	
	public synchronized ConcurrentHashMap<Integer, Twitter.Status> GetStore(){
		return _store;
	}

}
