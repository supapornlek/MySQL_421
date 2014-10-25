package com.example.mysql55410421;

import java.nio.channels.AsynchronousCloseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.R.string;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText edt1, edt2, edt3;
	private Button btn1;
	private ProgressDialog pDialog;
	private static String url_create_student = "http://www.sawasdeemall.com/android/create_student.php";
	JSONparser jsonParser = new JSONparser();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		edt1 = (EditText) findViewById(R.id.editText1);
		edt2 = (EditText) findViewById(R.id.editText2);
		edt3 = (EditText) findViewById(R.id.editText3);
		btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new CreateNewStudent().execute();

			}
		});

	}

	class CreateNewStudent extends AsyncTask<String, String, string> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Createing Student.....");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected string doInBackground(String... params) {
			String STU_ID = edt1.getText().toString();
			String NAME = edt2.getText().toString();
			String TEL = edt3.getText().toString();

			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("stu_id", STU_ID));
			list.add(new BasicNameValuePair("name", NAME));
			list.add(new BasicNameValuePair("tel", TEL));

			JSONObject jsonObject = jsonParser.makeHttpRequest(url_create_student, "POST", list);
			return null;
		}

		@Override
		protected void onPostExecute(string result) {
			
			pDialog.dismiss();
		}
	}

}
