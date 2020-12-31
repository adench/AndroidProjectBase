# AndroidProjectBase
[![](https://jitpack.io/v/adench/AndroidProjectBase.svg)](https://jitpack.io/#adench/AndroidProjectBase)

android项目所需工具类，activity基础类，加载等待弹窗，json封装，okgo网络框架封装，数据处理工具，沉浸式状态栏，后续工具还在继续更新中

How to
To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.adench:AndroidProjectBase:1.0.2'
	}

##启动页

创建WellComeHelper辅助类集成SplashScreenHelper，在initData中设置通知数据

    public class WellComeHelper extends SplashScreenHelper {

        public WellComeHelper(Context context) {
            super(context);
            //设置跳过时间 默认5s
            setCoutTime(8);
        }

        @Override
        protected void initData() {
            super.initData();
            //更新广告图片信息  此通知为异步，可进行网络请求
            notifyShowImage("http://pic346.nipic.com/file/20201126/32357757_184530234082_2.jpg",
                    "https://www.baidu.com/","百度官网");
        }
    }

##网络框架

post请求调用post(url),get请求调用get(url).如果上传参数复杂,可调用postJson(url,map),map为Map<String,Object>,可满足复杂json拼接
setClazz(cls)是请求后需要解析的对象，如果请求结果为集合，传item对象即可，在success(Object object)中，object直接强转为cls,cls=object,
如果是集合，需要强制转化为List<cls>

    HttpRequest.init(this).post(url).setShowDialog(true).setMap(map).setClazz(cls).excute(new HttpCallBack() {

        @Override
        public void success(Object object) {
            cls = object;
        }

        @Override
        public void failed(String code, String info) {

        }
    });

##WebView

advertTitle 是web页标题，advertUrl是web访问的url

    WebHelper.show(this, advertTitle, advertUrl);


##支付

##分享

##版本更新




##项目贡献

郑州小千科技有限公司