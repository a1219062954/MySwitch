package com.example.myswitch;


import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;


public class MainActivity extends Activity implements OnCheckedChangeListener {

	private TabHost tabHost;
	private Intent oftenIntent;
	private Intent deviceIntent;
	private Intent sceneIntent;
	private Intent settingIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		tabHost = (TabHost) findViewById(R.id.my_tabhost);
		@SuppressWarnings("deprecation")
		LocalActivityManager groupManager = new LocalActivityManager(this,
				false);
		groupManager.dispatchCreate(savedInstanceState);
		tabHost.setup(groupManager);
		initIntent();
		addSpec();
		((RadioGroup) findViewById(R.id.tab_radiogroup))
				.setOnCheckedChangeListener(this);
	}

	private void addSpec() {
		tabHost.addTab(this.buildTagSpec("tab_often", R.string.tab_often,
				R.drawable.tab_often, oftenIntent));
		tabHost.addTab(this.buildTagSpec("tab_device", R.string.tab_device,
				R.drawable.tab_device, deviceIntent));
		tabHost.addTab(this.buildTagSpec("tab_scene", R.string.tab_scene,
				R.drawable.tab_scene, sceneIntent));
		tabHost.addTab(this.buildTagSpec("tab_setting", R.string.tab_setting,
				R.drawable.tab_setting, settingIntent));
	}

	private void initIntent() {
		oftenIntent = new Intent(this, OftenActivity.class);
		deviceIntent = new Intent(this, DeviceActivity.class);
		sceneIntent = new Intent(this, SceneActivity.class);
		settingIntent = new Intent(this, SettingActivity.class);
	}

	private TabHost.TabSpec buildTagSpec(String tagName, int tagLable,
			int icon, Intent content) {
		return tabHost
				.newTabSpec(tagName)
				.setIndicator(getResources().getString(tagLable),
						getResources().getDrawable(icon)).setContent(content);

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.radio_btn_often:
			tabHost.setCurrentTabByTag("tab_often");
			break;
		case R.id.radio_btn_device:
			tabHost.setCurrentTabByTag("tab_device");
			break;
		case R.id.radio_btn_scene:
			tabHost.setCurrentTabByTag("tab_scene");
			break;
		case R.id.radio_btn_setting:
			tabHost.setCurrentTabByTag("tab_setting");
			break;
		}
	}

}
