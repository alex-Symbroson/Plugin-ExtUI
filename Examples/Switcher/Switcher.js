
app.LoadPlugin( "ExtUI" );

function OnStart() {
	var plg = app.CreateExtUI();
	var lay = app.CreateLayout( "linear", "VCenter,FillXY" );	
	
	swt = plg.CreateSwitch();
	lay.AddChild( swt );
	
	app.AddLayout( lay );
	
	var res = "";
	function test(o, f, p) { 
	    p = p || [];
	    res += (o && o.GetType? o.GetType() + "." : "") + f + "(" + p.join(", ") + "):\n";
	    try { res += JSON.stringify(o[f].apply(o,p)) + "\n\n"; }
	    catch(e) { res += e; }
	}
	
	test(plg, "GetVersion" );
	
	test(swt, "BringToFront", [] );
	test(swt, "ClearFocus", [] );
	test(swt, "GetAlpha", [] );
	test(swt, "GetBottom", [] );
	test(swt, "GetHeight", [] );
	test(swt, "GetId", [] );
	test(swt, "GetLeft", [] );
	test(swt, "GetRight", [] );
	test(swt, "GetRotation", [] );
	test(swt, "GetScaleX", [] );
	test(swt, "GetScaleY", [] );
	test(swt, "GetScrollX", [] );
	test(swt, "GetScrollY", [] );
	test(swt, "GetTop", [] );
	test(swt, "GetVisibility", [] );
	test(swt, "GetWidth", [] );
	test(swt, "IsEnabled", [] );
	test(swt, "IsFocusable", [] );
	test(swt, "IsFocused", [] );
	test(swt, "IsPressed", [] );
	test(swt, "IsSelected", [] );
	test(swt, "IsShown", [] );
	test(swt, "PerformClick", [] );
    //test(swt, "PerformHapticFeedback", [1] ); //ne
    //test(swt, "PlaySoundEffect", [] ); //ne
    	//test(swt, "ScrollBy", [intg, intg] );
    	//test(swt, "ScrollTo", [intg, intg] );
	test(swt, "SetAlpha", [0.6] );
	    //test(swt, "SetBackgroundColor", [3] ); //?
	//test(swt, "SetBottom", [.9] ); //ne
	test(swt, "SetClickable", [true] );
	test(swt, "SetEnabled", [true] );
	test(swt, "SetFocusable", [true] );
	test(swt, "SetHorizontalScrollBarEnabled", [true] );
	//test(swt, "SetLeft", [0.3] ); //ne
	test(swt, "SetOnClick", [swt_OnClick] );
	test(swt, "SetOnLongClick", [swt_OnLongClick] );
	//test(swt, "SetPadding", [flot, flot, flot, flot] );
	//test(swt, "SetRight", [0.3] ); //ne
	test(swt, "SetRotation", [45] );
	test(swt, "SetScaleX", [1.4] );
	test(swt, "SetScaleY", [2] );
        //test(swt, "SetScrollBarSize", [500] ); //ne
    	//test(swt, "SetScrollBarStyle", [style] ); //ne
        //test(swt, "SetScrollX", [0.5] ); //ne
    	//test(swt, "SetScrollY", [flot] ); //ne
	test(swt, "SetSelected", [true] );
    //test(swt, "SetTop", [0.2] ); //ne
	    //test(swt, "SetVerticalScrollBarEnabled", [true] ); //ne
    	//test(swt, "SetVerticalScrollbarPosition", [intg] ); //ne
	test(swt, "SetVisibility", ["Show"] );
	
	alert(res);
	
	setInterval("swt.PerformClick()", 3000);
	
	rotation = 45;
	setInterval("swt.SetRotation(rotation+=45)", 1000);
}

function swt_OnClick() {
    app.ShowPopup("switch touched!\nEnabled: " + swt.GetChecked());
}

function swt_OnLongClick() {
    app.ShowPopup("switch long touched!\nEnabled: " + swt.GetChecked());
}
