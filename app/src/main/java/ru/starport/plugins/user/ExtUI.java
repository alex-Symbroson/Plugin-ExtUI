/*
	Created by Alexandr Strashko

	This plugin extends the native capabilities UI DroidScript.
	It allows work with API Android: to create new objects and to call
	new methods for them or existing DS objects.

	Modified by Symbroson

	DroidScript Plugin class.
	(This is where you put your plugin code)
*/

package ru.starport.plugins.user;

import android.os.*;
import android.util.*;
import android.content.*;

import java.lang.reflect.*;

import android.app.*;
import android.view.*;

import java.lang.*;
import java.util.*;

import android.widget.*;
import android.view.ViewGroup.*;
import android.widget.DatePicker.*;

public class ExtUI {
	private static final String TAG = "ExtUI";
	private static final float VERSION = 1.02f;
	private Method m_callscript;
	private Object m_parent;
	private Context m_ctx;

	private String methodResult = "";
	//private String objClassName = "";
	private Object[] objParam = {null, null, null, null};

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
			//...
		} catch (Exception e) {
			Log.e(TAG, "Failed to Initialise plugin!", e);
		}
	}

	// Release plugin resources.
	public void Release() {
		// Your tidy up code goes here.
		//...
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

		// Choose command
		try {
			switch (cmd) {
				case "GetVersion":
					return GetVersion();

				case "GetResult":
					return methodResult;

				case "RunMethod":
					methodResult = "";
					methodResult = RunMethod(b, obj);
					//methodResult = objClassName;
					break;

				case "SetOnEvent":
					SetOnEvent(b, obj);
					break;

				case "GetMethods":
					return getMethods(b);

				case "SetParamObject":
					setParamObject(b, obj);
					break;
			}
		} catch (Exception e) {
			Log.e(TAG, "Plugin command failed!", e);
		}
		return "";
	}

	// Handle the CreateObject from DroidScript
	public Object CreateObject(Bundle b) {
		String type = b.getString("type");
		if (type == null) return null;

		try {
			if (type.equals("ExtObject")) {
				return CreateExtObject(b);
			}
		} catch (Exception e) {
			Log.e(TAG, "Plugin command failed!", e);
		}
		return null;
	}

	private void clearParamsObjects() {
		for (byte _i = 0; _i < objParam.length; _i++) {
			objParam[_i] = null;
		}
	}

	private void setParamObject(Bundle b, Object obj) {
		Log.d(TAG, "Set object");
		int _index = (int) Math.floor(b.getFloat("p1"));
		clearParamsObjects();
		if ((_index >= 0) && (_index <= 4)) {
			objParam[_index] = obj;
		}
	}

	// Handle the GetVersion command.
	private String GetVersion() {
		//d TAG, "Got GetVersion
		return Float.toString(VERSION);
	}

	// Handle events
	private void SetOnEvent(Bundle b, Object obj) {
		String _eventName = b.getString("p1");
		if (_eventName == null) return;

		final String _command = b.getString("p2");
		if (_command == null) return;

		switch (_eventName) {
			case "SetOnTouch": {
				// for object allowing touch
				final Button _btn = (Button) obj;
				_btn.setOnClickListener(new View.OnClickListener() {

					//@Override
					public void onClick(View v) {
						if (!_command.equals("")) {
							Bundle b = new Bundle();
							b.putString("cmd", _command);
							CallScript(b);
						}
					}
				});

				break;
			}

			case "SetOnDateChanged": {
				// for DatePicker

				final DatePicker _obj = (DatePicker) obj;

				_obj.init(_obj.getYear(), _obj.getMonth(), _obj.getDayOfMonth(), new OnDateChangedListener() {
					//@Override
					public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfYear) {
						Bundle b = new Bundle();
						b.putString("cmd", _command);
						b.putInt("p1", view.getYear());
						b.putInt("p2", view.getMonth());
						b.putInt("p3", view.getDayOfMonth());
						CallScript(b);
					}
				});
				break;
			}
		}
	}

	// Handle RunMethod command
	private String RunMethod(Bundle b, Object obj) {
		return ExecMethod(
				obj,
				b.getString("p1"), //method name
				b.getString("p2"), //parameter types
				b.get("p3"),       //1st parameter
				b.get("p4"),       //2nd parameter
				b.get("p5"),       //3rd parameter
				b.get("p6")        //4th parameter
		);
	}

	// Dynamic creation of object
	private Object CreateExtObject(Bundle b) {
		String _className = b.getString("p1");
		if (_className == null) return null;

		Object _obj = null;

		switch (_className) {
			case "AlertDialog":
				AlertDialog.Builder builder = new AlertDialog.Builder(m_ctx);
				return builder.create(); //returns dialog object

			case "Toast": // = simple popup
				return Toast.makeText(m_ctx, null, Toast.LENGTH_LONG);

			default:
				try {
					Class _class = Class.forName("android.widget." + _className);
					Constructor _con = _class.getConstructors()[0];
					try {
						_obj = _con.newInstance(m_ctx);
					} catch (InvocationTargetException | InstantiationException | IllegalAccessException | IllegalArgumentException e) {
						methodResult = "error: " + e;
					}
				} catch (ClassNotFoundException e) {
					methodResult = "error: " + e;
				}
				break;
		}

		View _view = (View) _obj;
		if (_view != null) {
			_view.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}

		return _view;
	}

	// Matching arguments and parameters
	private Boolean getEqualArgsParamsCount(Class[] p_argsTypes, String[] p_paramsTypes) {
		Byte _i = 0;
		Byte _countMatches = 0;

		for (Class argType : p_argsTypes) {
			if (argType.getSimpleName().compareTo(p_paramsTypes[_i]) == 0) {
				_countMatches++;
			}
			_i++;
		}

		//i TAG, "find method with passes args
		return p_argsTypes.length == _countMatches;
	}

	// Find and execute method
	// It is disabled reserved for future use
	private String ExecMethod(Object p_obj, String p_methodName, String p_paramsTypes, Object... p_params) {

		Object _result = "method id done";

		Class _cls;
		//Button _btn = (Button)p_obj;

		try {
			_cls = p_obj.getClass();
			//i class found

			Method[] methods = _cls.getMethods();
			ArrayList<Method> _findMethods = new ArrayList<>();

			String[] _paramsTypes = new String[0];

			byte _paramsCount = 0;
			for (Object _i : p_params) {
				if (_i != null) {
					_paramsCount++;
				}
			}

			if (p_paramsTypes.length() > 0) {
				_paramsTypes = p_paramsTypes.split(",");

				if (_paramsTypes.length != _paramsCount) {

					return "error: number of specified types and submitted parameters do not match " +
							"types - " + _paramsTypes.length + ", parameters - " + _paramsCount;
				}
			}

			Class[] _argsTypes = new Class[0];

			for (Method method : methods) {
				if (method.getName().equals(p_methodName)) {
					// method found
					_findMethods.add(method);
				}
			}

			if (_findMethods.size() == 0) {
				return "error: method " + p_methodName + " not found";
			}

			Boolean _methodFound = false;

			if (_findMethods.size() == 1) {
				//i find one method
				_argsTypes = _findMethods.get(0).getParameterTypes();

				if (_argsTypes.length == 0) {
					_methodFound = true;
				}

				if (_argsTypes.length == _paramsCount) {
					_methodFound = getEqualArgsParamsCount(_argsTypes, _paramsTypes);
				}

			} else if (_findMethods.size() > 1) {
				//i find several methods

				for (Method method : _findMethods) {
					_argsTypes = method.getParameterTypes();

					if (_argsTypes.length == 0) {
						_methodFound = true;
						break;
					}

					if (_argsTypes.length == _paramsCount) {
						_methodFound = getEqualArgsParamsCount(_argsTypes, _paramsTypes);
					}

					if (_methodFound) break;

				}
			}

			if (_methodFound) {

				//i execution method

				try {
					Method m = _cls.getMethod(p_methodName, _argsTypes);
					for (int _i = 0; _i < _argsTypes.length; _i++) {
						if (_paramsTypes[_i].equals("int")) {
							p_params[_i] = (int) Math.floor((Float) p_params[_i]);
						}
					}

					for (byte _i = 0; _i < objParam.length; _i++) {
						if (objParam[_i] != null) {
							p_params[_i] = objParam[_i];
						}
					}

					// selects and run method depending on the number of parameters
					switch (_argsTypes.length) {
						case 0:
							_result = m.invoke(p_obj);
							break;

						case 1:
							_result = m.invoke(p_obj, p_params[0]);
							break;

						case 2:
							_result = m.invoke(p_obj, p_params[0], p_params[1]);
							break;

						case 3:
							_result = m.invoke(p_obj, p_params[0], p_params[1], p_params[2]);
							break;

						case 4:
							_result = m.invoke(p_obj, p_params[0], p_params[1], p_params[2], p_params[3]);
							break;
					}

				} catch (Exception e) {
					_result = "error: " + e;
				}
			}
		} catch (Exception ignored) {

		}
		//_btn.setText(_info);
		//_btn.setText(_result.toString());
		if (_result != null) {
			return _result.toString();
		} else {
			return "null";
		}
	}

	// return list of methods for specified className
	private String getMethods(Bundle b) throws ClassNotFoundException {

		String _className = "android.widget." + b.getString("p1");
		ArrayList<String> _paramsList = new ArrayList<>();
		// ArrayList<String> _methodsList = new ArrayList<>();
		StringBuilder _method = new StringBuilder();

		Class c = Class.forName(_className);
		Method[] methods = c.getMethods();
		for (Method method : methods) {

			Class[] paramTypes = method.getParameterTypes();
			_paramsList.clear();
			for (Class paramType : paramTypes) {

				_paramsList.add(paramType.getSimpleName());
			}
			_method.append(method.getName());
			_method.append("(");
			_method.append(_paramsList.toString());
			_method.append("):");
			_method.append(method.getReturnType().getName());
			_method.append("|");
		}
		return _method.toString();
	}

}


