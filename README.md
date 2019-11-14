# PngOptimizePlugin
用于学习用途的Gradle插件，压缩png并转化为webp，减少包体积大小。依赖自己建立的本地repo来运行插件。

## 使用
### 1、建立本地仓库
```shell
gradle uploadArchives
```

### 2、在子模块的build.gradle里添加
```groovy
apply plugin: 'com.fxy.optimize'

optimizeOption {
    rootLocation  './OptimizePhotoPlugin' //文件夹的根目录
    appIconName  'ic_launcher.png' 
    appRoundIconName = 'ic_launcher_round.png'
    optimizeOption 'both' //optimize策略
    compressStrategy 'lossy' //压缩策略 无损和有损
    webpStrategy 'lossy'//转webp策略
    compressMinQuality 90 //压缩最低质量
    compressMaxQuality 100//最高质量
//    filterName = ["ic_launcher.png","ic_launcher_background.png"] //过滤文件的名称
}
```

### 3、运行
```
gradle compressAndWebp
```
## PS：练手项目，请勿用于商业用途
