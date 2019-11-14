package com.fxy.optimize.png

import com.fxy.optimize.png.config.Constants

class OptimizeOptionExtension{
    //necessary option
    String rootLocation = null
    String appIconName = "ic_launcher.png"//启动图标的图片名字
    String appRoundIconName = "ic_launcher_round.png" //圆形启动图标的图片名字

    //normal option
    String optimizeOption = Constants.STRATEGY_BOTH

    //compress
    String compressStrategy = Constants.LOSSY//默认有损压缩
    int compressMinQuality = Constants.COMPRESS_QUALITY_LOW//图片转换加一个品质限制，如果转换后的图片比最低品质还低，就不保存，并返回错误码99.取值范围 0-100
    int compressMaxQuality = Constants.COMPRESS_QUALITY_HIGH

    //webp
    String webpStrategy = Constants.LOSSY//默认有损压缩
}