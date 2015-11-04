package com.verox.smartscreenlock;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	private DevicePolicyManager mDevicePolicyManager;
	private ComponentName mComponentName; 
	private static final int ADMIN_INTENT = 15;
	private static final String description = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDevicePolicyManager = (DevicePolicyManager)getSystemService(  
				Context.DEVICE_POLICY_SERVICE);  

		mComponentName = new ComponentName(this, MyAdminReceiver.class); 
		boolean isAdmin = mDevicePolicyManager.isAdminActive(mComponentName);  

		if (isAdmin) {  
			mDevicePolicyManager.lockNow(); 
			finish();
		}else{
			//            Toast.makeText(getApplicationContext(), "Not Registered as admin", Toast.LENGTH_SHORT).show();
		}

		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,description);
		startActivityForResult(intent, ADMIN_INTENT);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == ADMIN_INTENT) {
			if (resultCode == RESULT_OK) {
				Toast.makeText(getApplicationContext(), "'Smart Screen Lock' Activated!", Toast.LENGTH_LONG).show();
				finish();
			}else{
				//                Toast.makeText(getApplicationContext(), "Failed to register as Admin", Toast.LENGTH_SHORT).show();
				finish();
			}
		}
	}

}
