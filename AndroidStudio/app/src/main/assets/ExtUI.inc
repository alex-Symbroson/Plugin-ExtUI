
app.CreateExtUI = function() {
    return new ExtUI();
}

//Plugin wrapper class.
function ExtUI() {

    var width = app.GetScreenWidth();
    var height = app.GetScreenHeight();

    this.GetScreenWidth = function() { return width; }
    this.GetScreenHeight = function() { return height; }

    //this.GetClasses = function() { return _classes; }
    this.GetType = function() { return "Plugin"; };
    this.GetVersion = function() { return _flot(plg.Send("Plg.GetVersion", _, _, _, _, _, _, _, _)); }
    //this.GetMethods = function(classSimpleName) { return plg.Send("Plg.GetMethods", classSimpleName, _, _, _, _, _, _, _).split("|"); }
    this.GetControls = function() { var lst = []; for(var i in this) if(i.startsWith("Create")) lst.push(i.slice(6)); return lst; }

/*
    this.CreateActionMenuView               = function() { var id = plg.CreateObj("ActionMenuView"               , _, _, _, _, _, _, _, _); return id? new Amv(id) : null; }
    this.CreateActivityChooserView          = function() { var id = plg.CreateObj("ActivityChooserView"          , _, _, _, _, _, _, _, _); return id? new Acv(id) : null; }
    this.CreateAdapterViewFlipper           = function() { var id = plg.CreateObj("AdapterViewFlipper"           , _, _, _, _, _, _, _, _); return id? new Avf(id) : null; }
    this.CreateAnalogClock                  = function() { var id = plg.CreateObj("AnalogClock"                  , _, _, _, _, _, _, _, _); return id? new Anc(id) : null; }
    this.CreateAutoCompleteTextView         = function() { var id = plg.CreateObj("AutoCompleteTextView"         , _, _, _, _, _, _, _, _); return id? new Act(id) : null; }
    this.CreateCalendarView                 = function() { var id = plg.CreateObj("CalendarView"                 , _, _, _, _, _, _, _, _); return id? new Clv(id) : null; }
    this.CreateCheckBox                     = function() { var id = plg.CreateObj("CheckBox"                     , _, _, _, _, _, _, _, _); return id? new Chb(id) : null; }
    this.CreateChronometer                  = function() { var id = plg.CreateObj("Chronometer"                  , _, _, _, _, _, _, _, _); return id? new Chr(id) : null; }
    this.CreateDatePicker                   = function() { var id = plg.CreateObj("DatePicker"                   , _, _, _, _, _, _, _, _); return id? new Dtp(id) : null; }
    this.CreateDateTimeView                 = function() { var id = plg.CreateObj("DateTimeView"                 , _, _, _, _, _, _, _, _); return id? new Dtv(id) : null; }
    this.CreateDayPickerView                = function() { var id = plg.CreateObj("DayPickerView"                , _, _, _, _, _, _, _, _); return id? new Dpv(id) : null; }
    this.CreateDialerFilter                 = function() { var id = plg.CreateObj("DialerFilter"                 , _, _, _, _, _, _, _, _); return id? new Dlf(id) : null; }
    this.CreateDigitalClock                 = function() { var id = plg.CreateObj("DigitalClock"                 , _, _, _, _, _, _, _, _); return id? new Dgc(id) : null; }
    this.CreateExpandableListView           = function() { var id = plg.CreateObj("ExpandableListView"           , _, _, _, _, _, _, _, _); return id? new Elv(id) : null; }
    this.CreateGallery                      = function() { var id = plg.CreateObj("Gallery"                      , _, _, _, _, _, _, _, _); return id? new Gal(id) : null; }
    this.CreateGridLayout                   = function() { var id = plg.CreateObj("GridLayout"                   , _, _, _, _, _, _, _, _); return id? new Grl(id) : null; }
    this.CreateGridView                     = function() { var id = plg.CreateObj("GridView"                     , _, _, _, _, _, _, _, _); return id? new Grv(id) : null; }
    this.CreateHorizontalScrollView         = function() { var id = plg.CreateObj("HorizontalScrollView"         , _, _, _, _, _, _, _, _); return id? new Hsv(id) : null; }
    this.CreateImageSwitcher                = function() { var id = plg.CreateObj("ImageSwitcher"                , _, _, _, _, _, _, _, _); return id? new Isw(id) : null; }
    this.CreateMediaController              = function() { var id = plg.CreateObj("MediaController"              , _, _, _, _, _, _, _, _); return id? new Mdc(id) : null; }
    this.CreateMultiAutoCompleteTextView    = function() { var id = plg.CreateObj("MultiAutoCompleteTextView"    , _, _, _, _, _, _, _, _); return id? new Mat(id) : null; }
    this.CreateNumberPicker                 = function() { var id = plg.CreateObj("NumberPicker"                 , _, _, _, _, _, _, _, _); return id? new Nbp(id) : null; }
    this.CreateQuickContactBadge            = function() { var id = plg.CreateObj("QuickContactBadge"            , _, _, _, _, _, _, _, _); return id? new Qcb(id) : null; }
    this.CreateRadialTimePickerView         = function() { var id = plg.CreateObj("RadialTimePickerView"         , _, _, _, _, _, _, _, _); return id? new Rtp(id) : null; }
    this.CreateRadioButton                  = function() { var id = plg.CreateObj("RadioButton"                  , _, _, _, _, _, _, _, _); return id? new Rdb(id) : null; }
    this.CreateRadioGroup                   = function() { var id = plg.CreateObj("RadioGroup"                   , _, _, _, _, _, _, _, _); return id? new Rgr(id) : null; }
    this.CreateRatingBar                    = function() { var id = plg.CreateObj("RatingBar"                    , _, _, _, _, _, _, _, _); return id? new Rtb(id) : null; }
    this.CreateRelativeLayout               = function() { var id = plg.CreateObj("RelativeLayout"               , _, _, _, _, _, _, _, _); return id? new Rla(id) : null; }
    this.CreateSearchView                   = function() { var id = plg.CreateObj("SearchView"                   , _, _, _, _, _, _, _, _); return id? new Srv(id) : null; }
    this.CreateSimpleMonthView              = function() { var id = plg.CreateObj("SimpleMonthView"              , _, _, _, _, _, _, _, _); return id? new Smv(id) : null; }
    this.CreateSlidingDrawer                = function() { var id = plg.CreateObj("SlidingDrawer"                , _, _, _, _, _, _, _, _); return id? new Sld(id) : null; }
    this.CreateSpace                        = function() { var id = plg.CreateObj("Space"                        , _, _, _, _, _, _, _, _); return id? new Spc(id) : null; }
    this.CreateStackView                    = function() { var id = plg.CreateObj("StackView"                    , _, _, _, _, _, _, _, _); return id? new Stv(id) : null; }
*/
    this.CreateSwitch                       = function() { var id = plg.CreateObj("Switch"                       , _, _, _, _, _, _, _, _); return id? new Swt(id) : null; }
/*
    this.CreateTabWidget                    = function() { var id = plg.CreateObj("TabWidget"                    , _, _, _, _, _, _, _, _); return id? new Twd(id) : null; }
    this.CreateTableLayout                  = function() { var id = plg.CreateObj("TableLayout"                  , _, _, _, _, _, _, _, _); return id? new Tbl(id) : null; }
    this.CreateTableRow                     = function() { var id = plg.CreateObj("TableRow"                     , _, _, _, _, _, _, _, _); return id? new Tbr(id) : null; }
    this.CreateTextClock                    = function() { var id = plg.CreateObj("TextClock"                    , _, _, _, _, _, _, _, _); return id? new Tcl(id) : null; }
    this.CreateTextSwitcher                 = function() { var id = plg.CreateObj("TextSwitcher"                 , _, _, _, _, _, _, _, _); return id? new Tsw(id) : null; }
    this.CreateTimePicker                   = function() { var id = plg.CreateObj("TimePicker"                   , _, _, _, _, _, _, _, _); return id? new Tmp(id) : null; }
    this.CreateToolbar                      = function() { var id = plg.CreateObj("Toolbar"                      , _, _, _, _, _, _, _, _); return id? new Tlb(id) : null; }
    this.CreateTwoLineListItem              = function() { var id = plg.CreateObj("TwoLineListItem"              , _, _, _, _, _, _, _, _); return id? new Tlv(id) : null; }
    this.CreateViewAnimator                 = function() { var id = plg.CreateObj("ViewAnimator"                 , _, _, _, _, _, _, _, _); return id? new Vam(id) : null; }
    this.CreateViewFlipper                  = function() { var id = plg.CreateObj("ViewFlipper"                  , _, _, _, _, _, _, _, _); return id? new Vfl(id) : null; }
    this.CreateViewSwitcher                 = function() { var id = plg.CreateObj("ViewSwitcher"                 , _, _, _, _, _, _, _, _); return id? new Vsw(id) : null; }
    this.CreateYearPickerView               = function() { var id = plg.CreateObj("YearPickerView"               , _, _, _, _, _, _, _, _); return id? new Ypv(id) : null; }
    this.CreateZoomButton                   = function() { var id = plg.CreateObj("ZoomButton"                   , _, _, _, _, _, _, _, _); return id? new Zmb(id) : null; }
    this.CreateZoomControls                 = function() { var id = plg.CreateObj("ZoomControls"                 , _, _, _, _, _, _, _, _); return id? new Zct(id) : null; }
*/

    function _ExtObj(id) {
        this.id = id;

        this.BringToFront = function() { return _void(plg.SendObj("Obj.BringToFront", this, _, _, _, _, _, _, _, _)); }
        /*this.ClearAnimation = function() { return _void(plg.SendObj("Obj.ClearAnimation", this, _, _, _, _, _, _, _, _)); }*/
        this.ClearFocus = function() { return _void(plg.SendObj("Obj.ClearFocus", this, _, _, _, _, _, _, _, _)); }
        /*this.DispatchTouchEvent = function(MotionEvent) { return _bool(plg.SendObj("Obj.DispatchTouchEvent", this, MotionEvent, _, _, _, _, _, _, _)); }*/
        this.GetAlpha = function() { return _flot(plg.SendObj("Obj.GetAlpha", this, _, _, _, _, _, _, _, _)); }
        this.GetBottom = function() { return _heig(plg.SendObj("Obj.GetBottom", this, _, _, _, _, _, _, _, _)); }
        /*this.GetDefaultSize = function(intg, intg) { return _intg(plg.SendObj("Obj.GetDefaultSize", this, intg, intg, _, _, _, _, _, _)); }*/
        this.GetHeight = function() { return _heig(plg.SendObj("Obj.GetHeight", this, _, _, _, _, _, _, _, _)); }
        this.GetId = function() { return _intg(plg.SendObj("Obj.GetId", this, _, _, _, _, _, _, _, _)); }
        /*this.GetLayerType = function() { return _intg(plg.SendObj("Obj.GetLayerType", this, _, _, _, _, _, _, _, _)); }*/
        this.GetLeft = function() { return _widh(plg.SendObj("Obj.GetLeft", this, _, _, _, _, _, _, _, _)); }
        /*this.GetMeasuredHeight = function() { return _intg(plg.SendObj("Obj.GetMeasuredHeight", this, _, _, _, _, _, _, _, _)); }*/
        /*this.GetMeasuredWidth = function() { return _intg(plg.SendObj("Obj.GetMeasuredWidth", this, _, _, _, _, _, _, _, _)); }*/
        this.GetRight = function() { return _widh(plg.SendObj("Obj.GetRight", this, _, _, _, _, _, _, _, _)); }
        this.GetRotation = function() { return _flot(plg.SendObj("Obj.GetRotation", this, _, _, _, _, _, _, _, _)); }
        this.GetScaleX = function() { return _flot(plg.SendObj("Obj.GetScaleX", this, _, _, _, _, _, _, _, _)); }
        this.GetScaleY = function() { return _flot(plg.SendObj("Obj.GetScaleY", this, _, _, _, _, _, _, _, _)); }
        this.GetScrollX = function() { return _intg(plg.SendObj("Obj.GetScrollX", this, _, _, _, _, _, _, _, _)); }
        this.GetScrollY = function() { return _intg(plg.SendObj("Obj.GetScrollY", this, _, _, _, _, _, _, _, _)); }
        this.GetTop = function() { return _heig(plg.SendObj("Obj.GetTop", this, _, _, _, _, _, _, _, _)); }
        this.GetVisibility = function() { return _intg(plg.SendObj("Obj.GetVisibility", this, _, _, _, _, _, _, _, _)); }
        this.GetWidth = function() { return _widh(plg.SendObj("Obj.GetWidth", this, _, _, _, _, _, _, _, _)); }
        this.IsEnabled = function() { return _bool(plg.SendObj("Obj.IsEnabled", this, _, _, _, _, _, _, _, _)); }
        this.IsFocusable = function() { return _bool(plg.SendObj("Obj.IsFocusable", this, _, _, _, _, _, _, _, _)); }
        this.IsFocused = function() { return _bool(plg.SendObj("Obj.IsFocused", this, _, _, _, _, _, _, _, _)); }
        this.IsPressed = function() { return _bool(plg.SendObj("Obj.IsPressed", this, _, _, _, _, _, _, _, _)); }
        this.IsSelected = function() { return _bool(plg.SendObj("Obj.IsSelected", this, _, _, _, _, _, _, _, _)); }
        this.IsShown = function() { return _bool(plg.SendObj("Obj.IsShown", this, _, _, _, _, _, _, _, _)); }
        /*this.OnKeyDown = function(intg, KeyEvent) { return _bool(plg.SendObj("Obj.OnKeyDown", this, intg, KeyEvent, _, _, _, _, _, _)); }*/
        /*this.OnKeyLongPress = function(intg, KeyEvent) { return _bool(plg.SendObj("Obj.OnKeyLongPress", this, intg, KeyEvent, _, _, _, _, _, _)); }*/
        /*this.OnKeyUp = function(intg, KeyEvent) { return _bool(plg.SendObj("Obj.OnKeyUp", this, intg, KeyEvent, _, _, _, _, _, _)); }*/
        /*this.OnTouchEvent = function(MotionEvent) { return _bool(plg.SendObj("Obj.OnTouchEvent", this, MotionEvent, _, _, _, _, _, _, _)); }*/
        this.PerformClick = function() { return _bool(plg.SendObj("Obj.PerformClick", this, _, _, _, _, _, _, _, _)); }
        this.PerformHapticFeedback = function(intg) { return _bool(plg.SendObj("Obj.PerformHapticFeedback", this, intg, _, _, _, _, _, _, _)); }
        this.PlaySoundEffect = function(/*intg*/) { return _void(plg.SendObj("Obj.PlaySoundEffect", this, /*intg*/_, _, _, _, _, _, _, _)); }
        this.ScrollBy = function(intg, intg) { return _void(plg.SendObj("Obj.ScrollBy", this, intg, intg, _, _, _, _, _, _)); }
        this.ScrollTo = function(intg, intg) { return _void(plg.SendObj("Obj.ScrollTo", this, intg, intg, _, _, _, _, _, _)); }
        this.SetAlpha = function(flot) { return _void(plg.SendObj("Obj.SetAlpha", this, flot, _, _, _, _, _, _, _)); }
        /*this.SetAnimation = function(Animation) { return _void(plg.SendObj("Obj.SetAnimation", this, Animation, _, _, _, _, _, _, _)); }*/
        /*this.SetBackground = function(Drawable) { return _void(plg.SendObj("Obj.SetBackground", this, Drawable, _, _, _, _, _, _, _)); }*/
        this.SetBackgroundColor = function(intg) { return _void(plg.SendObj("Obj.SetBackgroundColor", this, intg, _, _, _, _, _, _, _)); }
        this.SetBottom = function(flot) { return _void(plg.SendObj("Obj.SetBottom", this, _toheig(flot), _, _, _, _, _, _, _)); }
        this.SetClickable = function(bool) { return _void(plg.SendObj("Obj.SetClickable", this, bool, _, _, _, _, _, _, _)); }
        this.SetEnabled = function(bool) { return _void(plg.SendObj("Obj.SetEnabled", this, bool, _, _, _, _, _, _, _)); }
        this.SetFocusable = function(bool) { return _void(plg.SendObj("Obj.SetFocusable", this, bool, _, _, _, _, _, _, _)); }
        this.SetHorizontalScrollBarEnabled = function(bool) { return _void(plg.SendObj("Obj.SetHorizontalScrollBarEnabled", this, bool, _, _, _, _, _, _, _)); }
        this.SetLeft = function(flot) { return _void(plg.SendObj("Obj.SetLeft", this, _towidh(flot), _, _, _, _, _, _, _)); }
        //this.SetLeftTopRightBottom = function(intg, intg, intg, intg) { return _void(plg.SendObj("Obj.SetLeftTopRightBottom", this, intg, intg, intg, intg, _, _, _, _)); }
        /*this.SetMinimumHeight = function(flot) { return _void(plg.SendObj("Obj.SetMinimumHeight", this, _toheig(flot), _, _, _, _, _, _, _)); }*/
        /*this.SetMinimumWidth = function(flot) { return _void(plg.SendObj("Obj.SetMinimumWidth", this, _towidh(flot), _, _, _, _, _, _, _)); }*/
        /*this.SetOnKey = function(OnKey) { return _void(plg.SendObj("Obj.SetOnKey", this, _Cbm(this, OnKey), _, _, _, _, _, _, _)); }*/
        this.SetOnClick = function(OnClick) { return _void(plg.SendObj("Obj.SetOnClick", this, _Cbm(this, OnClick), _, _, _, _, _, _, _)); }
        this.SetOnLongClick = function(OnLongClick) { return _void(plg.SendObj("Obj.SetOnLongClick", this, _Cbm(this, OnLongClick), _, _, _, _, _, _, _)); }
        /*this.SetOnScrollChange = function(OnScrollChange) { return _void(plg.SendObj("Obj.SetOnScrollChange", this, _Cbm(this, OnScrollChange), _, _, _, _, _, _, _)); }*/
        /*this.SetOnTouch = function(OnTouch) { return _void(plg.SendObj("Obj.SetOnTouch", this, _Cbm(this, OnTouch), _, _, _, _, _, _, _)); }*/
        this.SetPadding = function(flot, flot, flot, flot) { return _void(plg.SendObj("Obj.SetPadding", this, _towidh(flot), _toheig(flot), _towidh(flot), _toheig(flot), _, _, _, _)); }
        this.SetRight = function(flot) { return _void(plg.SendObj("Obj.SetRight", this, _towidh(flot), _, _, _, _, _, _, _)); }
        this.SetRotation = function(flot) { return _void(plg.SendObj("Obj.SetRotation", this, flot, _, _, _, _, _, _, _)); }
        this.SetScaleX = function(flot) { return _void(plg.SendObj("Obj.SetScaleX", this, flot, _, _, _, _, _, _, _)); }
        this.SetScaleY = function(flot) { return _void(plg.SendObj("Obj.SetScaleY", this, flot, _, _, _, _, _, _, _)); }
        this.SetScrollBarSize = function(intg) { return _void(plg.SendObj("Obj.SetScrollBarSize", this, intg, _, _, _, _, _, _, _)); }
        this.SetScrollBarStyle = function(style) { return _void(plg.SendObj("Obj.SetScrollBarStyle", this, style, _, _, _, _, _, _, _)); }
        this.SetScrollX = function(flot) { return _void(plg.SendObj("Obj.SetScrollX", this, _towidh(flot), _, _, _, _, _, _, _)); }
        this.SetScrollY = function(flot) { return _void(plg.SendObj("Obj.SetScrollY", this, _toheig(flot), _, _, _, _, _, _, _)); }
        this.SetSelected = function(bool) { return _void(plg.SendObj("Obj.SetSelected", this, bool, _, _, _, _, _, _, _)); }
        this.SetTop = function(flot) { return _void(plg.SendObj("Obj.SetTop", this, _toheig(flot), _, _, _, _, _, _, _)); }
        this.SetVerticalScrollBarEnabled = function(bool) { return _void(plg.SendObj("Obj.SetVerticalScrollBarEnabled", this, bool, _, _, _, _, _, _, _)); }
        this.SetVerticalScrollbarPosition = function(intg) { return _void(plg.SendObj("Obj.SetVerticalScrollbarPosition", this, intg, _, _, _, _, _, _, _)); }
        this.SetVisibility = function(state) { return _void(plg.SendObj("Obj.SetVisibility", this, state, _, _, _, _, _, _, _)); }
        /*this.StartAnimation = function(Animation) { return _void(plg.SendObj("Obj.StartAnimation", this, Animation, _, _, _, _, _, _, _)); }*/
    }

    function Swt(id) {
        var obj = new _ExtObj(id);
        obj.GetType = function() { return "Switch"; };
        obj.SetChecked = function(bool) { return _bool(plg.SendObj("Swt.SetChecked", this, bool, _, _, _, _, _, _, _)); };
        obj.GetChecked = function()     { return _bool(plg.SendObj("Swt.GetChecked", this, _, _, _, _, _, _, _, _)); };
        return obj;
    }


    function _Cbm(obj, cb) {
        var i = _extCallb.indexOf(cb);
        if(i == -1) {
            i = _extCallb.length;
            _extCallb.push(cb);
        }
        return "_extCallb[" + i.toString() + "]";
    }

    function _void(v) { return; }
    function _intg(v) { return v? parseInt(v) : 0; }
    function _bool(v) { return v? v == "1" : false; }
    function _flot(v) { return v? parseFloat(v) : 0; }
    function _list(v) { return v? JSON.parse(v) : []; }
    function _objc(v) { return v? JSON.parse(v) : {}; }
    function _widh(v) { return _intg(v)/width; }
    function _heig(v) { return _intg(v)/height; }

    function _towidh(v) { return Math.floor(v*width); }
    function _toheig(v) { return Math.floor(v*height); }
    function _tolist(v) { return v? JSON.stringify(v) : []; }
    function _toobjc(v) { return v? JSON.stringify(v) : {}; }



    const _ = "";

    var options = ""
        /*, _classes = [
        "AbsListView","AbsoluteLayout","AbsSeekBar","AbsSpinner","AccessibilityIterators",
        "ActionMenuPresenter","ActionMenuView","ActivityChooserModel","ActivityChooserView",
        "Adapter","AdapterView","AdapterViewAnimator","AdapterViewFlipper","Advanceable",
        "AlphabetIndexer","AnalogClock","AppSecurityPermissions","ArrayAdapter","AutoCompleteTextView",
        "AutoCompleteTextViewCallbacks","AutoCompleteTextViewPopup","AutoCompleteTextViewSimple",
        "BaseAdapter","BaseExpandableListAdapter","Button","CalendarView","CalendarViewLegacyDelegate",
        "CalendarViewMaterialDelegate","Checkable","CheckBox","CheckedTextView","Chronometer",
        "CompoundButton","CursorAdapter","CursorFilter","CursorTreeAdapter","DatePicker",
        "DatePickerCalendarDelegate","DatePickerController","DateTimeView","DayPickerPagerAdapter",
        "DayPickerView","DayPickerViewPager","DialerFilter","DigitalClock","DoubleDigitManager",
        "EdgeEffect","Editor","EditText","ExpandableListAdapter","ExpandableListConnector",
        "ExpandableListPosition","ExpandableListView","FastScroller","Filter","Filterable",
        "FilterQueryProvider","FrameLayout","Gallery","GridLayout","GridView","HeaderViewListAdapter",
        "HeterogeneousExpandableList","HorizontalScrollView","ImageButton","ImageSwitcher","ImageView",
        "LabelView","LinearLayout","ListAdapter","ListPopupWindow","ListView","ListViewTest",
        "MediaController","MultiAutoCompleteTextView","NumberPicker","OnDateChangedListener",
        "OverScroller","PopupMenu","PopupWindow","ProgressBar","QuickContactBadge","RadialTimePickerView",
        "RadioButton","RadioGroup","RadioGroupActivity","RadioGroupPreCheckedTest","RatingBar",
        "RelativeLayout","RemoteViews","RemoteViewsAdapter","RemoteViewsListAdapter","RemoteViewsService",
        "ResourceCursorAdapter","ResourceCursorTreeAdapter","RtlSpacingHelper","ScrollBarDrawable",
        "Scroller","ScrollView","SearchView","SectionIndexer","SeekBar","ShareActionProvider",
        "SimpleAdapter","SimpleCursorAdapter","SimpleCursorAdapterTest","SimpleCursorTreeAdapter",
        "SimpleExpandableListAdapter","SimpleMonthView","SimpleMonthView_Delegate","SlidingDrawer",
        "Space","SpellChecker","Spinner","SpinnerAdapter","StackView","SuggestionsAdapter","Switch",
        "TabHost","TableLayout","TableRow","TabWidget","TextClock","TextSwitcher","TextView",
        "TextViewPerformanceTest","TextViewTest","TextViewWordLimitsTest","ThemedSpinnerAdapter",
        "TimePicker","TimePickerClockDelegate","TimePickerClockDelegate_Delegate",
        "TimePickerSpinnerDelegate","Toast","ToggleButton","Toolbar","Toolbar_Accessor","TwoLineListItem",
        "VideoView","ViewAnimator","ViewFlipper","ViewSwitcher","WrapperListAdapter","YearPickerView",
        "ZoomButton","ZoomButtonsController","ZoomControls"
    ]*/;

    var plg = _CreatePlugin("ru.starport.plugins.user.ExtUI", options);
}

var _extCallb = [];



/*
    // handle events
    this.SetOnEvent = function(obj, objTypeName, callback) {
        plg.SendObj("SetOnEvent", obj, objTypeName, callback.name);
    }

    // creating object
    this.CreateExtObject = function(classSimpleName) {
        var ret = plg.CreateObj("ExtObject", classSimpleName);

        if(ret) return new Obj(ret);
        else return null;
    }

    //  execute method
    this.RunMethod = function(obj, methodName, paramsTypes, par1, par2, par3, par4) {
        plg.SendObj("RunMethod", obj, methodName, paramsTypes, par1, par2, par3, par4);
        return plg.Send("GetResult");
    }

    this.SetParamObject = function(obj, no) {
        if(obj != null) plg.SendObj("SetParamObject", obj, no);
    }
*/