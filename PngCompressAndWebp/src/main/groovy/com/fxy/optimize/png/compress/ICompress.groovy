package com.fxy.optimize.png.compress

import org.gradle.api.Project

interface ICompress{
    void convert(Object pngTool,Project project, Object file, Object compressQualityLow, Object compressQualityHigh)
}
