package com.fxy.optimize.png

class OptimizeOptionExtension{
    String rootLocation = null
    boolean isConvertToWebp = false
    boolean isCompress = false
    class CompressOption{
        boolean isForce = false//是否覆盖导出文件
        boolean skipIfLarger = true//当转换的文件比源文件更小时保存文件
        int minQuality = 100//图片转换加一个品质限制，如果转换后的图片比最低品质还低，就不保存，并返回错误码99.取值范围 0-100
        int maxQuality = 100
        int speed = 3 // 转换速度与品质的比例。1(最佳品质)，10(速度最快)，默认是3
    }

    class WebpOption{
        boolean lossless = true // 无损编码
        int mode = 6 // 在0-9之间切换无损耗压缩模式，0级最快，9级最慢。快速模式产生的文件大小比较慢的文件要大。
    }
}