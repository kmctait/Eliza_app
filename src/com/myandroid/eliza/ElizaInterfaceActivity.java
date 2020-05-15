package com.myandroid.eliza;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ElizaInterfaceActivity extends Activity {
	
	ElizaParse eliza = new ElizaParse();
	EditText input;
	TextView responses;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliza_interface);
    	input = (EditText)findViewById(R.id.inputTextField);
    	responses = (TextView)findViewById(R.id.elizaResponses);
    	outputBlurb();
    	responses.append(" > " + eliza.introMessage + "\n");
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	outState.putString("responses", responses.getText().toString());
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
    	responses.append(savedInstanceState.getString("responses"));
    }
    
    public void handleInput(View clickedButton) {
		if (input.getText() == null)
			return;
		
		String s = input.getText().toString().trim();
		
		if (s.length() < 1)
			return;

		responses.append(s + "\n");
		responses.append(" > " + eliza.handleLine(s) + "\n");

    	input.setText("");
    }
    
	private void outputBlurb() {
		String[] messages = eliza.getBlurb();
		for(String line: messages) {
			responses.append(line + "\n");
		}
	}
    
}
