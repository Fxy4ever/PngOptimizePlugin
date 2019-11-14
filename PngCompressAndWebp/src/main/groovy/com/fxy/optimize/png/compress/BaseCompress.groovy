package com.fxy.optimize.png.compress

import org.gradle.api.Project

abstract class BaseCompress implements ICompress {


    @Override
    void convert(Object pngTool,Project project, Object file, Object compressQualityLow, Object compressQualityHigh) {
        long len = file.length()
        def result = commandLine(pngTool, file, compressQualityLow, compressQualityHigh).execute()
        result.waitForProcessOutput()
        if (result.exitValue() == 0) {
            long afterLen = new File(file.absolutePath).length()
            float compressRate = 1.0f * (len - afterLen) / len * 100
            project.logger.error "compress success from ${len} to ${afterLen}, $compressRate !"
        } else if (result.exitValue() == 98) {
            project.logger.error "Lossy type, ${file.absolutePath} has been compressed, skip!"
        } else if (result.exitValue() == 2) {
            project.logger.error "Lossless type, ${file.absolutePath} has been compressed, skip!"
        } else {
            project.logger.error "compress error : ${file.absolutePath}"
        }
    }

    abstract String commandLine(Object pngTool, Object file, Object compressQualityLow, Object compressQualityHigh)
}