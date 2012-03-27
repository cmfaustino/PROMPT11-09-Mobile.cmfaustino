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
import android.widget.Toast;

public class UpdateStatusService extends Service {

	private TsncApplication _application;
	private Handler _worker;
	private static final String PAYLOAD_KEY = "tweetMsg";
	
	protected TsncApplication getApp(){ // substituir private, p/ visivel subclasses
		return (TsncApplication) getApplication();
	}
	
	private void init(){
		_application = getApp();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		init();
		Log.i(_application.getAppTag(), "UpdateStatusService.onCreate()");
		
		HandlerThread workerThread = new HandlerThread("Updater service worker");
		workerThread.start();
		Looper looper = workerThread.getLooper();
		_worker = new Handler(looper){

			@Override
			public void handleMessage(Message msg) {
				//super.handleMessage(msg);
				Bundle payload = msg.getData(); 
				String tweet = payload.getString(PAYLOAD_KEY);
				
				//try..catch adaptado do livro Learning Android - paginas
				//impressas 68 Chapter6 e 105 Chapter8 (2a versao reenvia excepcao)
				try {
					_application.getTwitter().updateStatus(tweet);
				} catch (Exception e) {
					Log.e(getApp().getAppTag(), e.toString()); // nao se reenvia exc
					e.printStackTrace();
				}
				
				//adaptado do livro Learning Android - pagina impressa 68 Chapter6
				Toast.makeText(UpdateStatusService.this,
						"UpdateStatusService - "
								+ "Fim da Operacao de Envio de Post/Twitter.",
						Toast.LENGTH_SHORT).show();
				
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
		
		Log.i(TAG, "YambaUpdateService.onStartCommand() : thread id = "
				+ Thread.currentThread().getId());
		
		final String messageToPayload = intent.getStringExtra(
				_application.res.getString(R.string.upload_intent_extra_text)
				);
		
		Log.i(TAG, "YambaUpdateService.onStartCommand() : intent payload = "
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
