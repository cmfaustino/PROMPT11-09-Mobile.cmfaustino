package prompt.yamba;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.Log;

public class TsncApplication extends Application
								implements OnSharedPreferenceChangeListener {

	public Resources res;
	private volatile String TAG; // static final // substituir public
	private volatile Twitter _twitter;
	private volatile SharedPreferences _prefs; // volatile: Activ sbmtTweet AsncTsk 

	public String getAppTag() {
		return res.getString(R.string.app_tag, "");
	}

	public synchronized Twitter getTwitter(){
		if (_twitter == null)
		{
			// criar twitter com preferencias, caso contrario, utilizar resources
			String user = _prefs.getString("username",
					res.getString(R.string.yamba_user, ""));
			String password = _prefs.getString("password",
					res.getString(R.string.yamba_password, ""));
			String url = _prefs.getString("yamba_url",
					res.getString(R.string.yamba_api_url, ""));

			_twitter = new Twitter(user, password);
			_twitter.setAPIRootUrl(url);
		}
		return _twitter;
	}
	
	public SharedPreferences getPrefs(){
		// metodo p/: Activ submitTweet AsyncTask
		return _prefs;
	}

	private void init(){
		res = getResources();
		TAG = res.getString(R.string.app_tag);
		_twitter = null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		init();
		
		_prefs = PreferenceManager.getDefaultSharedPreferences(this);
		_prefs.registerOnSharedPreferenceChangeListener(this);
		
		Log.i(TAG, "TsncApplication.onCreate()");
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// invalidar objecto twitter, para ser recriado com as novas preferencias 
		_twitter = null;
	}

}
