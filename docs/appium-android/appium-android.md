## Hướng dẫn setup môi trường test với appium cho Android



#### Bước 1: Cài đặt [homebrew:](https://brew.sh/)
- Mở Terminal, nhập vào:

```bash
$ /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```


#### Bước 2: Cài đặt java JDK:
- Download và cài đặt: [Java SE Development Kit 8](https://www.oracle.com/technetwork/java/javaee/downloads/jdk8-downloads-2133151.html) (_Khuyến khích_)
- Hoặc thông qua Terminal: 

```bash
$ brew cask install java 
$ java -version
``` 

- Nếu cài đặt thành công sẽ có kết quả giống vầy:
```
java version "1.8.0_211"
Java(TM) SE Runtime Environment (build 1.8.0_211-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.211-b12, mixed mode)
```

#### Bước 3: Cài đặt MAVEN
- Mở Terminal, nhập vào:
 
```bash
$ brew install maven 
```


#### Bước 4: Download [IntelliJ IDEA:](https://www.jetbrains.com/idea/download/#section=mac) (`Chọn phiên bản Community Edition, free và đủ dùng`)

#### Bước 5: Download [Android studio](https://developer.android.com/studio)
- Cài đặt và khởi động Android studio
- Mở Android `SDK Manager`
- Download SDK Platforms (Android 6,7,8,9,..), SDK Tools

<img src="https://github.com/hominhtuong/appium/blob/master/resources/sdk-manager.png" alt="Example" width="720" height ="478"/>


#### Bước 6: Cài đặt [nodeJS](https://nodejs.org/en/download/)

- Tải tại trang: [https://nodejs.org/en/download/](https://nodejs.org/en/download/) sau đó cài đặt.

#### Bước 7: Cài đặt npm
- Mở Terminal, nhập vào:

```bash
$ npm install npm -g
```

#### Bước 8: Setup môi trường.

>> Nếu như máy tính chưa có có file .bash_profile, thì mình tạo file .bash_profile trước:

- Mở Terminal, nhập vào:

```bash
$ cd ~/
$ touch .bash_profile
```

- Khi tạo `.bash_profile` rồi, mở Terminal, nhập vào:
```bash
$ nano ~/.bash_profile
hoặc
$ open -e .bash_profile
```

- Nhập các dòng sau vào .bash_profile, sau đó lưu lại:
 
```
export ANDROID_HOME=/Users/minhtuong/Library/Android/sdk
export PATH=$ANDROID_HOME/platform-tools:$PATH
export PATH=$ANDROID_HOME/tools:$PATH
export JAVA_HOME=$(/Library/Java/JavaVirtualMachines/jdk1.8.0_141.jdk)
export PATH=${JAVA_HOME}/Contents/Home/bin:$PATH
export PATH=/usr/local/bin:/usr/local/sbin:~/bin:$PATH
export PATH=$PATH:/opt/bin:$PATH
```

>> Lưu ý:
thay thế *minhtuong* bằng tên user trên máy của bạn.
thay thế *jdk1.8.0_141.jdk* bằng bản jdk đã cài đặt 


#### Bước 9: Cài đặt Appium
- Mở Terminal, nhập vào:

```bash
$ npm install -g appium
$ npm install wd
```
Sau đó tiếp tục tải và cài đặt [Appium Desktop](https://github.com/appium/appium-desktop/releases)

- Kiểm tra appium đã cài đặt thành công hay chưa:
```bash
$ appium -v
```
- Nếu trả về version của appium (ví dụ: `1.13.0`) là đã cài đặt thành công.
- Để mở appium bằng terminal:

```bash
$ appium
```

- Nếu mọi thứ suôn sẽ thì kết quả sẽ giống vầy, hổng giống thì xem lại cách ăn ở nha.  
```
[Appium] Welcome to Appium v1.13.0
[Appium] Appium REST http interface listener started on 0.0.0.0:4723
```

#### Bước 10: Setup adb:
- Mở Terminal, nhập vào:
```bash
$ brew cask install android-platform-tools
$ adb devices
```

##### Vậy là đã hoàn tất các bước cài đặt.
### Bonus: set capabilities để test trên Android

```java
public class AndroidSetup {
    
    // Class Constants:
    
    public static final Boolean isEmulator = true;
    public static final String PLATFORM_VERSION = "8.0";
    public static final String DEVICE_NAME = "Nokia";
    public static final Boolean NO_RESET = true;
    public static final String UDID = "emulator-5554"; // Only for use emulator (isEmulator = true)
    public static final String appPackage = "com.minhtuong.xxx";
    public static final String appActivity = "com.minhtuong.xxx.SplashActivity";


    // Class Setup Driver
    public AndroidDriver setupDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Constants.PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Constants.DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, Constants.NO_RESET);

        if (Constants.isEmulator) {

            capabilities.setCapability(MobileCapabilityType.UDID,Constants.UDID);
        }
        capabilities.setCapability("appPackage", Constants.appPackage);
        capabilities.setCapability("appActivity", Constants.appActivity);

        URL remoteAddress = new URL("http://0.0.0.0:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(remoteAddress,capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        return driver;
    }
}
```
##### Để lấy Appackage và App activity, follow [Tại đây](http://www.automationtestinghub.com/apppackage-and-appactivity-name/)

##### Chú ý...Vui lòng thực hiện theo step-by-step. 
