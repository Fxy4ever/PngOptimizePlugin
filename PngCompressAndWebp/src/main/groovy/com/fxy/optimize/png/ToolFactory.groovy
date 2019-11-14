package com.fxy.optimize.png

import com.fxy.optimize.png.compress.ICompress
import com.fxy.optimize.png.compress.LosslessCompress
import com.fxy.optimize.png.compress.LossyCompress
import com.fxy.optimize.png.config.Constants
import com.fxy.optimize.png.webp.IWebp
import com.fxy.optimize.png.webp.LosslessWebp
import com.fxy.optimize.png.webp.LossyWebp
import org.gradle.api.GradleException

class ToolFactory {

    private ToolFactory() {}

    static IWebp getWebPTool(String strategy) {
        if (strategy == Constants.LOSSY) {
            return new LossyWebp()
        } else if (strategy == Constants.LOSSLESS) {
            return new LosslessWebp()
        } else {
            throw new GradleException("convertWebp strategy illegal")
        }
    }

    static ICompress getCompressTool(String strategy){
        if (strategy == Constants.LOSSY) {
            return new LossyCompress()
        } else if (strategy == Constants.LOSSLESS) {
            return new LosslessCompress()
        } else {
            throw new GradleException("compress strategy illegal")
        }
    }
}