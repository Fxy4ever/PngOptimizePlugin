package com.fxy.optimize.png.webp

public class LosslessWebp extends BaseWebp{

    @Override
    String command(String webpTool, File inFile, File outFile, int quality) {
        return "$webPTool -lossless ${inFile.absolutePath} -o ${outFile.absolutePath}"
    }
}