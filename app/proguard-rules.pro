# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/sunshine/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-ignorewarnings
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
    public void get*(...);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}
-keepclassmembers class **.R$* {
    public static <fields>;
}
-keepclasseswithmembernames class * {
    native <methods>;
}
#-keepnames class * implements java.io.Serializable
-keep public class * implements java.io.Serializable {
    public *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-dontwarn android.support.**
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**
#greendao3.2.0,此是针对3.2.0，如果是之前的，可能需要更换下包名
-keep class org.greenrobot.greendao.**{*;}
### greenDAO 3
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use RxJava:
-dontwarn rx.**
#个推start
-dontwarn com.igexin.**
-keep class com.igexin.** { *; }
-keep class org.json.** { *; }
#个推end
-keep class org.apache.**{*;}

#支付宝start
#-libraryjars libs/alipaySDK-20161222.jar

-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
#支付宝end

#微信start
#-libraryjars libs/wechat-sdk-android-with-mta-1.0.2.jar
-keep class com.tencent.mm.opensdk.** {*;}
-keep class com.tencent.wxop.** {*;}
-keep class com.tencent.mm.sdk.** {*;}
#微信end

#model
-keep class com.coolu.nokelock.bike.bean.** {*;}
-keep class com.sunshine.notelockbike.dao.db.** {*;}

#Gson
##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# Gson specific classes
-keep class com.google.** { *; }
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
##---------------End: proguard configuration for Gson  ----------
#End:Gson
#3D 地图
-keep class com.amap.api.mapcore.**{*;}
-keep class com.amap.api.maps.**{*;}
-keep class com.autonavi.amap.mapcore.*{*;}
#定位
-keep class com.amap.api.location.**{*;}
-keep class com.loc.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}
# 搜索
-keep class com.amap.api.services.**{*;}
#保留crash日志的行号
-keepattributes SourceFile,LineNumberTable

#有赞
# Youzan SDK
-dontwarn com.youzan.androidsdk.***
-keep class com.youzan.androidsdk.**{*;}

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-keep class okio.**{*;}
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }

-dontwarn java.nio.file.*
-dontwarn javax.annotation.**
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Image Loader
-keep class com.squareup.picasso.Picasso
-keep class com.android.volley.toolbox.Volley
-keep class com.bumptech.glide.Glide
-keep class com.nostra13.universalimageloader.core.ImageLoader
-keep class com.facebook.drawee.backends.pipeline.Fresco
-keepattributes EnclosingMethod

#讯飞
-keep class com.iflytek.**{*;}
-keepattributes Signature

#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#去除警告
-dontwarn freemarker.**
-keep class freemarker.** { *;}
-dontwarn com.alipay.**
-keep class com.alipay.** { *;}
-dontwarn com.handmark.**
-keep class com.handmark.** { *;}
-dontwarn com.loc.**
-keep class com.loc.** { *;}

#闪验
-dontwarn com.cmic.sso.sdk.**
-keep class com.cmic.sso.sdk.** { *; }
-keep class com.sdk.base.api.* {*;}
-keep class com.sdk.mobile.manager.UiOauthManager {*;}
-keep class com.sdk.mobile.handler.UiOauthHandler {*;}
-keep class com.sdk.mobile.manager.* {*;}
-keep class com.sdk.base.framework.bean.* {*;}
-keep class com.sdk.base.module.config.* {*;}
-keep class com.sdk.mobile.config.* {*;}
-keep class cn.com.chinatelecom.account.api.**{*;}
-keep class com.sdk.mobile.ui.CucWebView {public *;}
-keep class **.R$* {  *;  }