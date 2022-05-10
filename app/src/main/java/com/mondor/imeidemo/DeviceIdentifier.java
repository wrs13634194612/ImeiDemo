package com.mondor.imeidemo;


import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

/**
 * 设备标识符工具类。
 * <p>
 * 双检查锁机制实现单例模式缓存一下标识符，解决APP合规性检测机构检测到的频繁读取设备信息问题
 *
 * @author 贵州山野羡民（1032694760@qq.com）
 * @since 2021/9/1 10:06
 */
public final class DeviceIdentifier {
    private static volatile boolean registered = false;
    private static volatile String clientId = null;
    private static volatile String imei = null;
    private static volatile String oaid = null;
    private static volatile String androidId = null;
    private static volatile String widevineId = null;
    private static volatile String pseudoId = null;
    private static volatile String guid = null;

    private DeviceIdentifier() {
        super();
    }

    public static void register(Application application) {
        if (registered) {
            return;
        }
        synchronized (DeviceIdentifier.class) {
            if (!registered) {
                DeviceID.register(application);
                registered = true;
            }
        }
    }

    public static String getClientId() {
        if (TextUtils.isEmpty(clientId)) {
            synchronized (DeviceIdentifier.class) {
                if (TextUtils.isEmpty(clientId)) {
                    clientId = DeviceID.getClientIdMD5();
                }
            }
        }
        if (clientId == null) {
            clientId = "";
        }
        return clientId;
    }

    public static String getIMEI(Context context) {
        if (imei == null) {
            synchronized (DeviceIdentifier.class) {
                if (imei == null) {
                    imei = DeviceID.getUniqueID(context);
                }
            }
        }
        if (imei == null) {
            imei = "";
        }
        return imei;
    }

    public static String getOAID(Context context) {
        if (TextUtils.isEmpty(oaid)) {
            synchronized (DeviceIdentifier.class) {
                if (TextUtils.isEmpty(oaid)) {
                    oaid = DeviceID.getOAID();
                    if (oaid == null || oaid.length() == 0) {
                        DeviceID.getOAID(context, new IGetter() {
                            @Override
                            public void onOAIDGetComplete(String result) {
                                oaid = result;
                            }

                            @Override
                            public void onOAIDGetError(Exception error) {
                                oaid = "";
                            }
                        });
                    }
                }
            }
        }
        if (oaid == null) {
            oaid = "";
        }
        return oaid;
    }

    public static String getAndroidID(Context context) {
        if (androidId == null) {
            synchronized (DeviceIdentifier.class) {
                if (androidId == null) {
                    androidId = DeviceID.getAndroidID(context);
                }
            }
        }
        if (androidId == null) {
            androidId = "";
        }
        return androidId;
    }

    public static String getWidevineID() {
        if (widevineId == null) {
            synchronized (DeviceIdentifier.class) {
                if (widevineId == null) {
                    widevineId = DeviceID.getWidevineID();
                }
            }
        }
        if (widevineId == null) {
            widevineId = "";
        }
        return widevineId;
    }

    public static String getPseudoID() {
        if (pseudoId == null) {
            synchronized (DeviceIdentifier.class) {
                if (pseudoId == null) {
                    pseudoId = DeviceID.getPseudoID();
                }
            }
        }
        if (pseudoId == null) {
            pseudoId = "";
        }
        return pseudoId;
    }

    public static String getGUID(Context context) {
        if (guid == null) {
            synchronized (DeviceIdentifier.class) {
                if (guid == null) {
                    guid = DeviceID.getGUID(context);
                }
            }
        }
        if (guid == null) {
            guid = "";
        }
        return guid;
    }

}
