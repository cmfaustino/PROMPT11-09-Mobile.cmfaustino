package prompt.yamba;

//import prompt.yamba.R.string;
//import winterwell.jtwitter.Twitter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
//import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class YambaAndroidActivity extends Activity {
	
	public int intTamanhoMaximo = 140;
	public int intTamanhoLivre = 140;
	
	//private Twitter.Status _tweetText;

	private YaApplication getYa(){
		return (YaApplication) getApplication();
	}
	
	public void getValorCor(TextView tv, Button tv2){
		int cor2set;
		if (intTamanhoLivre == intTamanhoMaximo)
			cor2set = Color.GREEN;
		else if ((intTamanhoLivre > 0) && (intTamanhoLivre < intTamanhoMaximo))
			cor2set = Color.YELLOW;
		else if (intTamanhoLivre == 0)
			cor2set = Color.RED;
		else cor2set = Color.MAGENTA;
		tv.setText(Integer.toString(intTamanhoLivre));
    	tv.setTextColor(cor2set);
		tv2.setText(Integer.toString(intTamanhoLivre));
    	tv2.setTextColor(cor2set);
	}
	
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		startActivity(new Intent(this,UserPreferencesActivity.class)); //this.
		return true;
	}

	/**
	 * Helper method
	 */
	//private void submitTweet(){
	private void submitTweet(String msgtxt){

/*		(new AsyncTask<String, Void, Void>() {
			@Override
			protected void onPreExecute() {

			}

			@Override
			protected Void doInBackground(String... params) {
				Twitter _twitter = getYa().getTwitter();
				if (_twitter == null)
				{
					// _prefs nao estava na YaApplication, nem era volatile,
					//  mas estava nesta Activity, e tudo relacionado tambem
					_twitter = new Twitter(getYa().getPrefs().getString("username", ""), getYa().getPrefs().getString("password", ""));
					_twitter.setAPIRootUrl(getYa().getPrefs().getString("site_url", ""));
				}

				_twitter.updateStatus(params[0]);

				return null;
			}

		}).execute(msgtxt);
*/		
		//Intent msg = new Intent(this,YambaUpdateService.class);
		Intent msg = new Intent(this,YambaIntentService.class);
		msg.putExtra(
				getResources().getString(R.string.upload_intent_extra_text),
				//_tweetText.getText().toString()
				msgtxt
				);
		startService(msg);
	}
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // set up 1st behavior - ini
    	final TextView intText0 = (TextView) findViewById(R.id.txtvwNumero);
    	final Button intButton0 = (Button) findViewById(R.id.edtbtnButton);
    	intTamanhoLivre = intTamanhoMaximo;
    	getValorCor(intText0 , intButton0);
        // set up 1st behavior - end

    	// set up behavior

		Log.i(getYa().getAppTag(), "main called: " + Thread.currentThread().getId());
        
		// cores : verde - inicial ; amarelo - positivo ; vermelho nulo
		
        EditText msgEditText = (EditText) findViewById(R.id.edtxtMultlinText);
        msgEditText.addTextChangedListener(new TextWatcher(){

			public void afterTextChanged(Editable arg0s) {
				// TODO Auto-generated method stub
				
			}

			public void beforeTextChanged(CharSequence arg0s, int arg1start,
					int arg2count, int arg3after) {
				// TODO Auto-generated method stub
				
			}

			public void onTextChanged(CharSequence arg0s, int arg1start, int arg2before,
					int arg3count) {
				intTamanhoLivre = intTamanhoLivre - (arg3count - arg2before);
		        final TextView intText = (TextView) findViewById(R.id.txtvwNumero);
		    	final Button intButton = (Button) findViewById(R.id.edtbtnButton);
		    	getValorCor(intText , intButton);

				Log.i(getYa().getAppTag(), "onClick edt called: " + Thread.currentThread().getId());
			}
        	
        });
        
        Button submitButton = (Button) findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Log.i(getYa().getAppTag(), "onClick btn called: " + Thread.currentThread().getId());
				EditText msgEditText = (EditText) findViewById(R.id.edtxtMultlinText);
				Editable msg = msgEditText.getText(); 
				//_tweetText = getYa().getTwitter().updateStatus(msg.toString());
				submitTweet(msg.toString());
			}
		});
    }
}