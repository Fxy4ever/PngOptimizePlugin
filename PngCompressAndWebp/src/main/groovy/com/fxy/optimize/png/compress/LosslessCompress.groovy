package com.fxy.optimize.png.compress

public class LosslessCompress extends BaseCompress{

    @Override
    String commandLine(Object pngTool, Object file, Object compressQualityLow, Object compressQualityHigh) {
        return  "${pngTool} ${file.absolutePath}"
    }
}