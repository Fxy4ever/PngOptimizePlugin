# PngOptimizePlugin
简单插件
执行gradle compressAndWebp命令一键转换项目中的png到webp
```
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
PS：练手项目，请勿用于商业用途
