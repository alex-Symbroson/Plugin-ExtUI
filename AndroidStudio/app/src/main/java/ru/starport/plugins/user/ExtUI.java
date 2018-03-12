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
					return handleObj(func[1], (View) obj, b);
				case "Swt":
					return handleSwt(func[1], (Switch) obj, b);
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

			case "BringToFront":
				obj.bringToFront();
				break;
/*
			case "ClearAnimation":
				obj.clearAnimation();
				break;
*/
			case "ClearFocus":
				obj.clearFocus();
				break;
/*
			case "DispatchTouchEvent":
				return obj.dispatchTouchEvent(<MotionEvent>)? "1" : "0";
*/
			case "GetAlpha":
				return Float.toString(obj.getAlpha());

			case "GetBottom":
				return Integer.toString(obj.getBottom());
/*
			case "GetDefaultSize":
				return Integer.toString(View.getDefaultSize((int) Math.floor(b.getFloat("p1", 0.0f)), (int) Math.floor(b.getFloat("p2", 0.0f))));
*/
			case "GetHeight":
				return Integer.toString(obj.getHeight());

			case "GetId":
				return Integer.toString(obj.getId());
/*
			case "GetLayerType":
				return Integer.toString(obj.getLayerType());
*/
			case "GetLeft":
				return Integer.toString(obj.getLeft());
/*
			case "GetMeasuredHeight":
				return Integer.toString(obj.getMeasuredHeight());

			case "GetMeasuredWidth":
				return Integer.toString(obj.getMeasuredWidth());
*/
			case "GetRight":
				return Integer.toString(obj.getRight());

			case "GetRotation":
				return Float.toString(obj.getRotation());

			case "GetScaleX":
				return Float.toString(obj.getScaleX());

			case "GetScaleY":
				return Float.toString(obj.getScaleY());

			case "GetScrollX":
				return Integer.toString(obj.getScrollX());

			case "GetScrollY":
				return Integer.toString(obj.getScrollY());

			case "GetTop":
				return Integer.toString(obj.getTop());

			case "GetVisibility":
				return Integer.toString(obj.getVisibility());

			case "GetWidth":
				return Integer.toString(obj.getWidth());

			case "IsEnabled":
				return obj.isEnabled() ? "1" : "0";

			case "IsFocusable":
				return obj.isFocusable() ? "1" : "0";

			case "IsFocused":
				return obj.isFocused() ? "1" : "0";

			case "IsPressed":
				return obj.isPressed() ? "1" : "0";

			case "IsSelected":
				return obj.isSelected() ? "1" : "0";

			case "IsShown":
				return obj.isShown() ? "1" : "0";
/*
			case "OnKeyDown":
				return obj.onKeyDown((int) Math.floor(b.getFloat("p1", 0.0f)), <KeyEvent>)? "1" : "0";

			case "OnKeyLongPress":
				return obj.onKeyLongPress((int) Math.floor(b.getFloat("p1", 0.0f)), <KeyEvent>)? "1" : "0";

			case "OnKeyUp":
				return obj.onKeyUp((int) Math.floor(b.getFloat("p1", 0.0f)), <KeyEvent>)? "1" : "0";

			case "OnTouchEvent":
				return obj.onTouchEvent(<MotionEvent>)? "1" : "0";
*/
			case "PerformClick":
				return obj.performClick() ? "1" : "0";

			case "PerformHapticFeedback":
				return obj.performHapticFeedback((int) Math.floor(b.getFloat("p1", 0.0f))) ? "1" : "0";

			case "PlaySoundEffect":
				obj.playSoundEffect(SoundEffectConstants.CLICK);
				break;

			case "ScrollBy":
				obj.scrollBy((int) Math.floor(b.getFloat("p1", 0.0f)), (int) Math.floor(b.getFloat("p2", 0.0f)));
				break;

			case "ScrollTo":
				obj.scrollTo((int) Math.floor(b.getFloat("p1", 0.0f)), (int) Math.floor(b.getFloat("p2", 0.0f)));
				break;

			case "SetAlpha":
				obj.setAlpha(b.getFloat("p1", 0.0f));
				break;
/*
		    case "SetAnimation":
		        obj.setAnimation(<Animation>);
		        break;

		    case "SetBackground":
		        obj.setBackground(<Drawable>);
		        break;
*/
			case "SetBackgroundColor":
				obj.setBackgroundColor((int) Math.floor(b.getFloat("p1", 0.0f)));
				break;

			case "SetBottom":
				obj.setBottom((int) Math.floor(b.getFloat("p1", 0.0f)));
				break;

			case "SetClickable":
				obj.setClickable(b.getBoolean("p1", false));
				break;

			case "SetEnabled":
				obj.setEnabled(b.getBoolean("p1", false));
				break;

			case "SetFocusable":
				obj.setFocusable(b.getBoolean("p1", false));
				break;

			case "SetHorizontalScrollBarEnabled":
				obj.setHorizontalScrollBarEnabled(b.getBoolean("p1", false));
				break;

			case "SetLeft":
				obj.setLeft((int) Math.floor(b.getFloat("p1", 0.0f)));
				break;
/*
			case "SetLeftTopRightBottom":
				obj.setLeftTopRightBottom((int) Math.floor(b.getFloat("p1", 0.0f)), (int) Math.floor(b.getFloat("p2", 0.0f)), (int) Math.floor(b.getFloat("p3", 0.0f)), (int) Math.floor(b.getFloat("p4", 0.0f))); break;

			case "SetMinimumHeight":
				obj.setMinimumHeight((int) Math.floor(b.getFloat("p1", 0.0f)));
				break;

			case "SetMinimumWidth":
				obj.setMinimumWidth((int) Math.floor(b.getFloat("p1", 0.0f)));
				break;

			case "SetOnKey": {
				final String callb = b.getString("p1");
				if (callb != null && !callb.equals("")) {
					obj.setOnKeyListener(new View.OnKeyListener() {
						public void onKey(View v) {
							if (!callb.equals("")) {
								Bundle b = new Bundle();
								b.putString("cmd", callb);
								CallScript(b);
							}
						}
					});
				}
			}
			break;
*/
			case "SetOnClick": {
				final String callb = b.getString("p1");
				if (callb != null && !callb.equals("")) {
					obj.setOnClickListener(new View.OnClickListener() {
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
			break;

			case "SetOnLongClick": {
				final String callb = b.getString("p1");
				if (callb != null && !callb.equals("")) {
					obj.setOnLongClickListener(new View.OnLongClickListener() {
						public boolean onLongClick(View v) {
							if (!callb.equals("")) {
								Bundle b = new Bundle();
								b.putString("cmd", callb);
								CallScript(b);
							}
							return false;
						}
					});
				}
			}
			break;
/*
			case "SetOnScrollChange": {
				final String callb = b.getString("p1");
				if (callb != null && !callb.equals("")) {
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
						obj.setOnScrollChangeListener(new View.OnScrollChangeListener() {
							public void onScrollChange(View v) {
								if (!callb.equals("")) {
									Bundle b = new Bundle();
									b.putString("cmd", callb);
									CallScript(b);
								}
							}
						});
					}
				}
			}
			break;
*/
			case "SetPadding":
				obj.setPadding((int) Math.floor(b.getFloat("p1", 0.0f)), (int) Math.floor(b.getFloat("p2", 0.0f)), (int) Math.floor(b.getFloat("p3", 0.0f)), (int) Math.floor(b.getFloat("p4", 0.0f)));
				break;

			case "SetRight":
				obj.setRight((int) Math.floor(b.getFloat("p1", 0.0f)));
				break;

			case "SetRotation":
				obj.setRotation(b.getFloat("p1", 0.0f));
				break;

			case "SetScaleX":
				obj.setScaleX(b.getFloat("p1", 0.0f));
				break;

			case "SetScaleY":
				obj.setScaleY(b.getFloat("p1", 0.0f));
				break;

			case "SetScrollBarSize":
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
					obj.setScrollBarSize((int) Math.floor(b.getFloat("p1", 0.0f)));
				break;

			case "SetScrollBarStyle":

				switch (b.getString("p1", "Hide").toLowerCase()) {

					case "insideinset":
						obj.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
						break;

					case "insideoverlay":
						obj.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
						break;

					case "outsideinset":
						obj.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_INSET);
						break;

					case "outsideoverlay":
						obj.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
						break;
				}
				break;

			case "SetScrollX":
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
					obj.setScrollX((int) Math.floor(b.getFloat("p1", 0.0f)));
				break;

			case "SetScrollY":
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
					obj.setScrollY((int) Math.floor(b.getFloat("p1", 0.0f)));
				break;

			case "SetSelected":
				obj.setSelected(b.getBoolean("p1", false));
				break;

			case "SetTop":
				obj.setTop((int) Math.floor(b.getFloat("p1", 0.0f)));
				break;

			case "SetVerticalScrollBarEnabled":
				obj.setVerticalScrollBarEnabled(b.getBoolean("p1", false));
				break;

			case "SetVerticalScrollbarPosition":
				obj.setVerticalScrollbarPosition((int) Math.floor(b.getFloat("p1", 0.0f)));
				break;

			case "SetVisibility":

				switch (b.getString("p1", "Hide").toLowerCase()) {

					case "show":
						obj.setVisibility(View.VISIBLE);
						break;

					case "hide":
						obj.setVisibility(View.INVISIBLE);
						break;

					case "gone":
						obj.setVisibility(View.GONE);
						break;
				}
				break;
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
				if (obj.isChecked()) return "1";
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
