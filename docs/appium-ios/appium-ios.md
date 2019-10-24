## Hướng dẫn setup môi trường test với appium cho IOS (Real Device)



#### Bước 1: Tải và cài đặt [Xcode](https://itunes.apple.com/vn/app/xcode/id497799835?mt=12) phiên bản mới nhất. 
>> Xcode khá nặng, nên ưu tiên tải trước, sau đó thực hiện các bước tiếp theo. Khi cài đặt xong Xcode, tiến hành cài đặt Xcode command line.

##### Cài đặt Xcode Command line (Thực hiện sau khi đã cài đặt xong Xcode) 
- Mở Terminal, nhập vào:

```bash
$ xcode-select --install
```  
#### Bước 2: Cài đặt [homebrew:](https://brew.sh/)
- Mở Terminal, nhập vào:

```bash
$ /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

#### Bước 3: Cài đặt java JDK:
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

#### Bước 4: Download [IntelliJ IDEA:](https://www.jetbrains.com/idea/download/#section=mac) (`Chọn phiên bản Community Edition, free và đủ dùng`)


#### Bước 5: Cài đặt [nodeJS](https://nodejs.org/en/download/)

- Tải tại trang: [https://nodejs.org/en/download/](https://nodejs.org/en/download/) sau đó cài đặt.

#### Bước 6: Cài đặt npm
- Mở Terminal, nhập vào:

```bash
$ npm install npm -g
```

#### Bước 7: Setup môi trường.

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
export JAVA_HOME=$(/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk)
export PATH=${JAVA_HOME}/Contents/Home/bin:$PATH
export PATH=/usr/local/bin:/usr/local/sbin:~/bin:$PATH
export PATH=$PATH:/opt/bin:$PATH
```

>> Lưu ý:
thay thế *minhtuong* bằng tên user trên máy của bạn.
thay thế *jdk1.8.0_141.jdk* bằng bản jdk đã cài đặt 

#### Bước 8: Cài đặt Carthage 
- Mở Terminal, nhập vào:

```bash
$ brew install carthage
$ npm i -g webpack
```

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


#### Bước 10: Cài đặt Appium Doctor 
- Mở Terminal, nhập vào:

```bash
$ npm install -g appium-doctor 
```
 

#### Bước 11 : Setup Webdriver Agent Server
##### Lưu ý rằng: Chúng ta có 2 bản Appium (cài bằng terminal và bản Desktop)
>> Appium cài trên terminal, mỗi khi khởi động chỉ cần gõ `appium` vào terminal là xong, tiện cho việc test sau này.<br>
Appium bản desktop để chúng ta bắt element, phục vụ cho việc viết script.
>>> Vậy nên cần thiết phải cài 2 bản appium, và phải setup WebDriverAgent 2 lần.

##### Đối với bản appium bản Desktop:
- Mở Terminal, nhập vào:

```bash
$ cd /Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent
$ mkdir -p Resources/WebDriverAgent.bundle
$ ./Scripts/bootstrap.sh -d 
```

##### Đối với bản appium cài bằng Terminal:
- Mở Terminal, nhập vào:

```bash
$ cd /usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent
$ mkdir -p Resources/WebDriverAgent.bundle
$ ./Scripts/bootstrap.sh -d 
```

#### Bước 12 : Setup để chạy được trên real device  (Lúc này chắc chắn Xcode và Xcode Command line đã cài xong rồi nha, nếu chưa cài thì chờ cài xong rồi làm tiếp)
- Mở Terminal, nhập vào:

```bash
$ brew install libimobiledevice --HEAD
$ brew install ideviceinstaller
$ npm install -g ios-deploy
$ gem install xcpretty
```

#### Bước 13 : Cấu hình WebDriverAgent (Bước rất quan trọng)

- Mở thư mục WebDriverAgent (thực hiện 2 lần trên 2 thư mục, đường dẫn đến thư mục thì xem lại ở `Bước 11`)
- Mở file `WebDriverAgent.xcodeproj` bằng Xcode
- Chọn `Automatically manage signing` cho cả `WebDriverAgentLib` và `WebDriverAgentRunner` ở tab `General`.
- Chọn `Development Team`. (Chưa có tài khoản thì tạo tự tạo nha)
- Chọn `WebDriverAgentRunner`, chọn tab `Build Settings`. Đổi value của `Product Bundle Identifier` từ `com.facebook.WebDriverAgentRunner` thành 1 cái gì khác bất kỳ, ví dụ: `com.facebook.WebDriverAgentRunner.minhtuong`
- Chọn thư mục `Configurations` trên Xcode, tạo 1 file `Config.xcconfig`, chèn vào 2 dòng sau:

```
DEVELOPMENT_TEAM = DEVELOPMENT_TEAM 
CODE_SIGN_IDENTITY = iPhone Developer
```
- Để lấy DEVELOPMENT_TEAM, mở `Keychain Access` trên Mac, ở mục category, chọn My Certificates, chọn development team, ví dụ:
`iPhone Developer: minhtuongitc@gmail.com(9T8xxx)`
- Copy đoạn trong ngoặc, pass vào DEVELOPMENT_TEAM ở bên trên.

<img src="https://github.com/hominhtuong/appium/blob/master/resources/keychain-access.png" alt="Key Chain" width="720" height ="478"/>
 

##### Vậy là đã hoàn tất các bước cài đặt.

### Bonus: set capabilities để test trên IOS


```java

public class Test {
    
    // Class Constants:
    
    public static final String PLATFORM_VERSION = "12.2";
    public static final String DEVICE_NAME = "Nokia 1280";
    public static final String UDID = "749bc67ddbb8397319f8c55f9b43258c6e523d4e";
    public static final String bundleId = "com.minhtuong.xxx";
    public static final String xcodeOrgId = "9T8xxx";
    public static final String xcodeSigningId = "iPhone Developer";
    public static final String xcodeConfigFile = "/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/Configurations/Config.xcconfig"; //Optional
    public static final Boolean NO_RESET = true;
    //
    
    
    // Class Setup Driver
    private IOSDriver driver;
    private void setupDriver() throws IOException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Constants.PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Constants.DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.UDID, Constants.UDID);
        capabilities.setCapability(MobileCapabilityType.NO_RESET,Constants.NO_RESET);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability("bundleId", Constants.bundleId);
        capabilities.setCapability("xcodeOrgId", Constants.xcodeOrgId);
        capabilities.setCapability("xcodeSigningId", Constants.xcodeSigningId);
        capabilities.setCapability("xcodeConfigFile",Constants.xcodeConfigFile);  //Optional

        URL remoteAddress = new URL("http://0.0.0.0:4723/wd/hub");

        driver = new IOSDriver(remoteAddress,capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    
}

```

- Để lấy UDID, Mở Xcode > Window > Devices and Simulators, copy dòng `identifier`.
##### Chú ý...Vui lòng thực hiện theo step-by-step. 
Mọi thắc mắc xin liên hệ: 
_...mà thôi khỏi liên hệ đi_
