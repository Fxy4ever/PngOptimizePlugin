package com.fxy.optimize.png.config

class Constants{
    def static final PNG = ".png"
    def static final PNG9 = ".9.png"
    def static final JPG = ".jpg"
    def static final JPEG = ".jpeg"
    def static final TOOLSDIR = "image_tools"

    def static final COMPRESS_QUALITY_LOW = 80
    def static final COMPRESS_QUALITY_HIGH = 100

    //转换webp的工具类型
    def static final LOSSY = "lossy"//默认，有损模式，可指定压缩质量
    def static final LOSSLESS = "lossless"//无损模式
    def static final WEBP_QUALITY = 100

    //插件策略
    def static final STRATEGY_ONLY_COMPRESS = "compress"//只进行压缩，不转为webp
    def static final STRATEGY_BOTH = "both"

    //工具名称
    def static final CWEBP_TOOL_NAME = "cwebp"
    def static final LOSSY_PNG_TOOL_NAME = "pngquant"
    def static final LOSSYLESS_PNG_TOOL_NAME = "pngout"
}