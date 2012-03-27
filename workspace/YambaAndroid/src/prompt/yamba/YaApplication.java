package prompt.yamba;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.Log;

public class YaApplication extends Application implements OnSharedPreferenceChangeListener {

	public volatile String TAG; // static final
	private volatile Twitter _twitter;
	private volatile SharedPreferences _prefs; // volatile: Activ sbmtTweet AsncTsk 
	public Resources res;

	private void init(){
		res = getResources();
		TAG = res.getString(R.string.app_tag);
		_twitter = null;
	}
	
	public SharedPreferences getPrefs(){ // metodo p/: Activ submitTweet AsyncTask
		return _prefs;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		init();
		_prefs = PreferenceManager.getDefaultSharedPreferences(this);
		_prefs.registerOnSharedPreferenceChangeListener(this);
		Log.d(TAG, "YaApplication.onCreate()");
	}

	public synchronized Twitter getTwitter(){
		if (_twitter == null)
		{
			_twitter = new Twitter(res.getString(R.string.yamba_user, ""),
									res.getString(R.string.yamba_password, ""));
			_twitter.setAPIRootUrl(res.getString(R.string.yamba_api_url, ""));
		}
		return _twitter;
	}
	
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		_twitter = null;
	}

	public String getAppTag() {
		return res.getString(R.string.app_name, "");
	}

}
