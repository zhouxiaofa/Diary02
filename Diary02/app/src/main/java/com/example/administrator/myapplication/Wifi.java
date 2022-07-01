package com.example.administrator.myapplication;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2021/10/15.
 */

public class Wifi extends Activity {
    private List<ScanResult> wifiList;
    private WifiManager wifiManager;
    private List<String> passableHotsPot;
    private WifiReceiver wifiReceiver;
    private boolean isConnected=false;
    private Button connect;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    /* 初始化参数 */
    public void init() {
        setContentView(R.layout.admin_book_manage);
        connect=(Button)findViewById(R.id.but1);
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiReceiver = new WifiReceiver();
        //通过按钮事件搜索热点
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifiManager.startScan();
            }
        });
    }
    /* 监听热点变化 */
    private final class WifiReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            wifiList = wifiManager.getScanResults();
            if (wifiList == null || wifiList.size() == 0 || isConnected)
                return;
            onReceiveNewNetworks(wifiList);
        }
    }
    /*当搜索到新的wifi热点时判断该热点是否符合规格*/
    public void onReceiveNewNetworks(List<ScanResult> wifiList){
        passableHotsPot=new ArrayList<String>();
        for(ScanResult result:wifiList){
            System.out.println(result.SSID);
            if((result.SSID).contains("YRCCONNECTION"))
                passableHotsPot.add(result.SSID);
        }
        synchronized (this) {
            connectToHotpot();
        }
    }
    /*连接到热点*/
    public void connectToHotpot(){
        if(passableHotsPot==null || passableHotsPot.size()==0)
            return;
        WifiConfiguration wifiConfig=this.setWifiParams(passableHotsPot.get(0));
        int wcgID = wifiManager.addNetwork(wifiConfig);
        boolean flag=wifiManager.enableNetwork(wcgID, true);
        isConnected=flag;
        System.out.println("connect success? "+flag);
    }
    /*设置要连接的热点的参数*/
    public WifiConfiguration setWifiParams(String ssid){
        WifiConfiguration apConfig=new WifiConfiguration();
        apConfig.SSID="\""+ssid+"\"";
        apConfig.preSharedKey="\"12122112\"";
        apConfig.hiddenSSID = true;
        apConfig.status = WifiConfiguration.Status.ENABLED;
        apConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        apConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        apConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        apConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        apConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        apConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        return apConfig;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    /*销毁时注销广播*/
        unregisterReceiver(wifiReceiver);
    }
}
