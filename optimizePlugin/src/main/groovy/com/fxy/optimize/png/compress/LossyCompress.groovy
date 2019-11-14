package com.fxy.optimize.png.compress

public class LossyCompress extends BaseCompress{

    @Override
    String commandLine(Object pngTool, Object file, Object compressQualityLow, Object compressQualityHigh) {
        def suffix = file.name.substring(file.name.lastIndexOf("."), file.name.length())
        return  "${pngTool} -f --skip-if-larger --ext $suffix --quality ${compressQualityLow}-${compressQualityHigh} --speed 1 ${file.absolutePath}"
    }

}