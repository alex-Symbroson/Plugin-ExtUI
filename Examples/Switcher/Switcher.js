
app.LoadPlugin( "ExtUI" );

function OnStart() {
	var plg = app.CreateExtUI();
	var lay = app.CreateLayout( "linear", "VCenter,FillXY" );	
	
	swt = plg.CreateSwitch();
	swt.SetOnTouch(swt_OnTouch);
	lay.AddChild( swt );
	
	app.AddLayout( lay );
	
	alert("Version: " + plg.GetVersion());
	alert("SetEnabled: " + (swt.SetEnabled(true)? "" : "un") + "successful");
	alert("SetChecked: " + (swt.SetChecked(true)? "" : "un") + "successful");
	alert("GetChecked: " + swt.GetChecked());
}

function swt_OnTouch() {
    app.ShowPopup("switch touched!\nEnabled: " + swt.GetChecked());
}