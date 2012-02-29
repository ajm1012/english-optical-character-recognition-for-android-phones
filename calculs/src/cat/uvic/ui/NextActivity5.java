package cat.uvic.ui;

import java.io.IOException;
import jjil.core.Error;
import cat.uvic.android.ResultBitmap;
import cat.uvic.android.ImageException;
import cat.uvic.calculs.Transformation;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity responsible for color analysis
 * 
 * @author ANNA
 */
public class NextActivity5 extends Activity {
	long state = 0;
	String result;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		state = this.getIntent().getLongExtra("STATE", 0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.image);
		final MyView mm = (MyView) findViewById(R.id.myView);
		final Button buttonNext = (Button) findViewById(R.id.ButtonNext);
		final TextView textViewLog = (TextView) findViewById(R.id.TextViewLog);
		// buttonNext.setText(" FINISH ");
		textViewLog.setTextColor(Color.WHITE);
		mm.invalidate();
		buttonNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Intent in = new Intent(NextActivity5.this,
							cat.uvic.ui.FinalActivity.class);
					startActivity(in);
					finish();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		if (state == 5) {
			this.state = 6;
			Transformation trans;
			try {
				trans = Transformation.getInstance(null);
				ResultBitmap res = new ResultBitmap();
				if (trans.getRecognition() != 5 && trans.getRecognition() != 10
						&& trans.getRecognition() != 1010
						&& trans.getRecognition() != 101) {
					trans.setRecognition(trans.analiseColor());
				}
				res.setRegion(trans.getRegion());
				res.setBitmap(trans.getCurrentImg());
				mm.setRect(res.getRegion());
				mm.setBtm(res.getBitmap());
				mm.setResult(trans.getRecognition() + "");
				mm.invalidate();
			} catch (IOException e) {
				state = 0;
				e.printStackTrace();
			} catch (Error e) {
				state = 0;
				e.printStackTrace();
			} catch (ImageException e) {
				state = 0;
				e.printStackTrace();
			}
		}
	}
}