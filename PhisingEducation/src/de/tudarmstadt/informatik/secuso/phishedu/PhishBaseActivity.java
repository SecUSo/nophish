package de.tudarmstadt.informatik.secuso.phishedu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import de.tudarmstadt.informatik.secuso.phishedu.backend.BackendController;

public class PhishBaseActivity extends ActionBarActivity {
	

    @Override
    protected void onStart() {
        super.onStart();
        BackendController.getInstance().getGameHelper().onStart(this);
        StartMenuActivity.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //disabled because this is called after onstart
        //BackendController.getInstance().getGameHelper().onStop();
    }
    
    @Override
    protected void onActivityResult(int request, int response, Intent data) {
        super.onActivityResult(request, response, data);
        BackendController.getInstance().getGameHelper().onActivityResult(request, response, data);
    }
		
	protected void updateScore(){
    	updateScore(findViewById(R.id.score_relative));
    }
		
	protected void updateScore(View view){
		if(view == null){
			return;
		}
		RelativeLayout scores = (RelativeLayout) view.findViewById(R.id.score_relative);
		if(scores != null){
			TextView urlsText = (TextView) scores.findViewById(R.id.urls);
			TextView urlsGoalText = (TextView) scores.findViewById(R.id.urls_goal);
			ImageView lifeOne = (ImageView) scores.findViewById(R.id.life_1);
			ImageView lifeTwo = (ImageView) scores.findViewById(R.id.life_2);
			ImageView lifeThree = (ImageView) scores.findViewById(R.id.life_3);
			TextView LevelScoreText = (TextView) scores.findViewById(R.id.level_score);

			urlsText.setText(Integer.toString(BackendController.getInstance().getCorrectlyFoundURLs()));
			urlsGoalText.setText(Integer.toString(BackendController.getInstance().levelCorrectURLs()));
			LevelScoreText.setText(Integer.toString(BackendController.getInstance().getLevelPoints()));
			
			int remaininLives = BackendController.getInstance().getLifes();
			
			//now hide hearts if required
			switch (remaininLives) {
			case 0:
				//hide all hearts
				lifeOne.setVisibility(View.INVISIBLE);
				lifeTwo.setVisibility(View.INVISIBLE);
				lifeThree.setVisibility(View.INVISIBLE);
				break;
			case 1:
				//hide heart 1 and 2
				lifeOne.setVisibility(View.INVISIBLE);
				lifeTwo.setVisibility(View.INVISIBLE);
				break;
			case 2:
				//hide only heart 1
				lifeOne.setVisibility(View.INVISIBLE);
			default:
				break;
			}
		}
	}
	
	
	protected void levelRestartWarning() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

		// Setting Dialog Title
		alertDialog.setTitle(getString(R.string.level_restart_title));

		// Setting Dialog Message
		alertDialog.setMessage(getString(R.string.level_restart_text));

		alertDialog.setPositiveButton(R.string.level_restart_positive_button, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				BackendController.getInstance().restartLevel();
			}
		});

		alertDialog.setNegativeButton(R.string.level_restart_negative_button,
				new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
	
	protected void levelCanceldWarning() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

		// Setting Dialog Title
		alertDialog.setTitle(getString(R.string.level_cancel_title));

		// Setting Dialog Message
		alertDialog.setMessage(getString(R.string.level_cancel_text));

		alertDialog.setPositiveButton(R.string.level_cancel_positive_button, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				NavUtils.navigateUpFromSameTask(PhishBaseActivity.this);
			}
		});

		alertDialog.setNegativeButton(R.string.level_cancel_negative_button,
				new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
}
