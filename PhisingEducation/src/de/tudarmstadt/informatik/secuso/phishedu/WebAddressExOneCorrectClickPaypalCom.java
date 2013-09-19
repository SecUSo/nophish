package de.tudarmstadt.informatik.secuso.phishedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class WebAddressExOneCorrectClickPaypalCom extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_address_ex_one_correct_click_paypal_com);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(
				R.menu.web_address_ex_one_correct_click_paypal_com, menu);
		return true;
	}

	public void goToWebAddressExampleTwo(View view){
		Intent intent = new Intent(this, WebAddressExTwoActivity.class);
		startActivity(intent);
	}

}
