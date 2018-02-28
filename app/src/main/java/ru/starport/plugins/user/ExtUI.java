/*
	Created by Alexandr Strashko
	Extended by Symbroson

	This plugin extends the native capabilities UI DroidScript.
	It allows work with API Android: to create new objects and to call
	new methods for them or existing DS objects.

	DroidScript Plugin class.
	(This is where you put your plugin code)
*/

package ru.starport.plugins.user;

//import android.annotation.TargetApi;
import android.os.*;
import android.util.*;
import android.content.*;

import java.lang.reflect.*;

//import android.app.*;
import android.view.*;

import java.lang.*;
//import java.util.*;

import android.widget.*;
import android.view.ViewGroup.*;
//import android.widget.DatePicker.*;


public class ExtUI {
	private static final String TAG = "ExtUI";
	private static final float VERSION = 1.03f;
	private Method m_callscript;
	private Object m_parent;
	private Context m_ctx;

	// Construct plugin.
	public ExtUI() {
		Log.d(TAG, "Creating plugin object");
	}

	// Initialise plugin.
	public void Init(Context ctx, Object parent) {
		try {
			Log.d(TAG, "Initialising plugin object");
			m_ctx = ctx;
			m_parent = parent;
			m_callscript = parent.getClass().getMethod("CallScript", Bundle.class);

			// Your initialisation code goes here.
		} catch (Exception e) {
			Log.e(TAG, "Failed to Initialise plugin!", e);
		}
	}

	// Release plugin resources.
	public void Release() {
	}

	// Use this method to call a function in the user's script.
	private void CallScript(Bundle b) {
		try {
			m_callscript.invoke(m_parent, b);
		} catch (Exception e) {
			Log.e(TAG, "Failed to call script function!", e);
		}
	}

	// Handle commands from DroidScript
	public String CallPlugin(Bundle b, Object obj) {

		// Extract command
		String cmd = b.getString("cmd");
		if (cmd == null) return "";

		Log.d(TAG, "CallPlugin -> " + cmd);

		String[] func = cmd.split("\\.", 2);
		Log.d(TAG, "CallPlugin -> '" + func[0] + "', '" + func[1] + "'");


		// Choose command
		try {
			switch (func[0]) {
				case "Plg":
					return handlePlg(func[1], b);
				case "Obj":
					return handleObj(func[1], (View)obj, b);
				case "Swt":
					return handleSwt(func[1], (Switch)obj, b);
			}
		} catch (Exception e) {
			Log.e(TAG, "Plugin command '" + cmd + "' failed!", e);
		}
		return "";
	}


	private String handlePlg(String cmd, Bundle b) {
		Log.d(TAG, "handlePlg -> " + cmd);

		switch (cmd) {
			case "GetVersion":
				return Float.toString(VERSION);
		}
		return "";
	}

	private String handleObj(String cmd, View obj, Bundle b) {
		if (obj == null) return "";
		Log.d(TAG, "handleObj -> " + cmd);

		switch (cmd) {
			case "SetEnabled":
				obj.setEnabled(b.getBoolean("p1", false));
				return "1";

			case "IsEnabled":
				if(obj.isEnabled()) return "1";
				else return "0";

			case "SetOnTouch":
				final String callb = b.getString("p1");

				if(callb != null && !callb.equals("")) {
					obj.setOnClickListener(new View.OnClickListener() {

						//@Override
						public void onClick(View v) {
							if (!callb.equals("")) {
								Bundle b = new Bundle();
								b.putString("cmd", callb);
								CallScript(b);
							}
						}
					});
				}
		}
		return "";
	}


	private String handleSwt(String cmd, Switch obj, Bundle b) {
		if (obj == null) return "";
		Log.d(TAG, "handleSwt -> " + cmd);

		switch (cmd) {
			case "SetChecked":
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
					obj.setChecked(b.getBoolean("p1", false));
					return "1";
				} else return "0";

			case "GetChecked":
				if(obj.isChecked()) return "1";
				else return "0";
		}
		return "0";
	}

	// Handle the CreateObject from DroidScript
	public Object CreateObject(Bundle b) {

		String type = b.getString("type");
		if (type == null) return null;
		Log.d(TAG, "Create Object '" + type + "'");

		View obj = null;

		try {
			switch (type) {
				case "Switch":
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
						obj = new Switch(m_ctx);
					}
					break;
			}

			if (obj != null) {
				obj.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				return obj;
			}

		} catch (Exception e) {
			Log.e(TAG, "Plugin command failed!", e);
		}

		return null;
	}
}
