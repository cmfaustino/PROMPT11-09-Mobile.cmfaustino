package prompt.yamba;

import android.app.IntentService;
import android.content.Intent;
//import android.os.Handler;
import android.util.Log;

public final class YambaIntentService extends IntentService {

	private YaApplication _application;
	
	public YambaIntentService(String name) {
		super(name);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		_application = ((YaApplication) getApplication());
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		final String TAG = _application.getAppTag();
		Log.d(TAG, "YambaIntentService.onHandleIntent() : thread id = "
				+ Thread.currentThread().getId());
		final String message = intent.getStringExtra(getResources().getString(R.string.upload_intent_extra_text));
		Log.d(TAG, "YambaIntentService.onHandleIntent() : intent payload = "
				+ message);
		
		_application.getTwitter().updateStatus(message);
	}

}
