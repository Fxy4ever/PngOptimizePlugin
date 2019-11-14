package com.fxy.optimize.png.webp


public class LossyWebp extends BaseWebp{

    @Override
    String command(String webpTool, File inFile, File outFile, int quality) {
        return "$webpTool -q ${quality} ${inFile.absolutePath} -o ${outFile.absolutePath}"
    }
}
