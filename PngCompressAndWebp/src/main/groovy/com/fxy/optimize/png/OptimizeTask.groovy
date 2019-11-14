package com.fxy.optimize.png

import com.fxy.optimize.png.compress.ICompress
import com.fxy.optimize.png.config.Constants
import com.fxy.optimize.png.webp.IWebp
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

class OptimizeTask extends DefaultTask{

    def webpTool
    def pngTool

    def minSdk
    static List<File> imgList = []
    def compressQualityMin = Constants.COMPRESS_QUALITY_LOW
    def compressQualityMax = Constants.COMPRESS_QUALITY_HIGH
    def convertStrategy = Constants.STRATEGY_BOTH
    def webpStrategy = Constants.LOSSY
    def compressStrategy = Constants.LOSSY
    String appIconName
    String appRoundIconName

    IWebp iWebp
    ICompress iCompress

    OptimizeTask(){
        group = "Optimization"
    }


    @TaskAction
    def startOptimize(){
        def data = project["optimizeOption"]
        minSdk = 19
        appIconName = data.appIconName
        appRoundIconName = data.appRoundIconName
        convertStrategy = data.optimizeOption
        compressStrategy = data.compressStrategy
        compressQualityMin = data.compressMinQuality
        compressQualityMax = data.compressMaxQuality
        webpStrategy = data.webpStrategy

        OptimizeUtil.checkParams(minSdk,compressQualityMin,compressQualityMax,
                convertStrategy,webpStrategy,compressStrategy,
                appIconName,appRoundIconName)

        webpTool = OptimizeUtil.copyConvertTool(project,Constants.CWEBP_TOOL_NAME)

        if(compressStrategy == Constants.LOSSY){
            pngTool = OptimizeUtil.copyConvertTool(project,Constants.LOSSY_PNG_TOOL_NAME)
        }else{
            pngTool = OptimizeUtil.copyConvertTool(project,Constants.LOSSYLESS_PNG_TOOL_NAME)
        }

        iWebp = ToolFactory.getWebPTool(webpStrategy)
        iCompress = ToolFactory.getCompressTool(compressStrategy)

        project.logger.error "==========Optimize Option=========="
        project.logger.error "convertStrategy : $convertStrategy"
        project.logger.error "compressQualityMin : $compressQualityMin"
        project.logger.error "compressQualityMax : $compressQualityMax"
        project.logger.error "png tool : $pngTool"
        project.logger.error "webp tool : $webpTool"
        project.logger.error "mini sdk : $minSdk"
        project.logger.error "webpStrategy : $webpStrategy"
        project.logger.error "compressStrategy : $compressStrategy"

        project.logger.error "=============================deep search dirs========================="
        //遍历文件夹
        String rootLocation =  project["optimizeOption"].rootLocation

        OptimizeUtil.searchDir(rootLocation)

        imgList.each { img ->
            println img.name
        }


        List<File> failList = []
        List<File> compress = []
        project.logger.error "============================start optimize png========================"
        if(convertStrategy == Constants.STRATEGY_ONLY_COMPRESS){

            imgList.each { img ->
                compressPng(img)
            }

        }else if(convertStrategy == Constants.STRATEGY_BOTH){
            if(minSdk < 14){ // < 14 只能压缩
                imgList.each { img ->
                    compressPng(img)
                }

            }else if(14 < minSdk && minSdk < 18){ //14 - 18之间的带有透明通道的不能转换，只能压缩
                imgList.each { img ->
                    if(OptimizeUtil.isTransparent(img)){
                        compress << img
                    }else{
                        convertWebp(img, failList)
                    }
                }

                failList.each { img ->
                    compressPng(img)
                }

            }else{ // >= 18 全可以转换

                imgList.each { img ->
                    convertWebp(img, failList)
                }

                failList.each { img ->
                    compressPng(img)
                }
            }
            failList.each {
                project.logger.error "failed convert : ${it.absolutePath}"
            }

            failList.each {
                compressPng(it)
            }

        }


    }

    def compressPng(File file){
        iCompress.convert(pngTool,project,file,compressQualityMin,compressQualityMax)
    }

    def convertWebp(File file,List<File> failedList){
        iWebp.convert(project, webpTool, file, failedList, Constants.WEBP_QUALITY)
    }


}