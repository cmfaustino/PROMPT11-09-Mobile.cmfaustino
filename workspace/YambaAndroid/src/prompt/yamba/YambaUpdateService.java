package prompt.yamba;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


public final class YambaUpdateService extends Service {

	private YaApplication _application;
	private Handler _worker;
	private static final String PAYLOAD_KEY = "tweetMsg";
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	private void init(){
		_application = (YaApplication) getApplication();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		init();
		Log.d(_application.getAppTag(), "YambaUpdateService.onCreate()");
		HandlerThread workerThread = new HandlerThread("Updater service worker");
		workerThread.start();
		Looper looper = workerThread.getLooper();
		_worker = new Handler(looper){

			@Override
			public void handleMessage(Message msg) {
				//super.handleMessage(msg);
				Bundle payload = msg.getData(); 
				String tweet = payload.getString(PAYLOAD_KEY);
				_application.getTwitter().updateStatus(tweet);
				stopSelf();
			}
		};
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//return super.onStartCommand(intent, flags, startId);
		final String TAG = _application.getAppTag();
		Log.d(TAG, "YambaUpdateService.onStartCommand() : thread id = "
				+ Thread.currentThread().getId());
		final String messageToPayload = intent.getStringExtra(getResources().getString(R.string.upload_intent_extra_text));
		Log.d(TAG, "YambaUpdateService.onStartCommand() : intent payload = "
				+ messageToPayload);
		
/*		_worker.post(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
*/		
		Message msg =  new Message();
		msg.setTarget(_worker);
		Bundle payload = new Bundle();
		payload.putString(PAYLOAD_KEY, messageToPayload);
		
		msg.setData(payload);
		_worker.sendMessage(msg);

		return START_STICKY;
	}

}
