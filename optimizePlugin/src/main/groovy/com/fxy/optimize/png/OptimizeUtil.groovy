package com.fxy.optimize.png

import com.fxy.optimize.png.config.Constants
import org.apache.tools.ant.taskdefs.condition.Os
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.internal.impldep.org.apache.maven.plugin.descriptor.InvalidParameterException

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class OptimizeUtil {

    static void searchDir(String rootLocation){
        File root = new File(rootLocation)
        if(root.isDirectory()){
            root.eachFile { file ->
                searchDir(file.absolutePath)
            }
        }else{
            if(root.name.endsWith(".png") && !root.name.contains(".9.png")){
                OptimizeTask.imgList << root
            }
        }
    }

    def static isTransparent(File file) {
        BufferedImage image = ImageIO.read(file)
        return image.colorModel.hasAlpha()
    }

    def static copyConvertTool(Project project,String name){
        String toolName

        if (Os.isFamily(Os.FAMILY_MAC)) {
            toolName = "${name}_darwin"
        } else if (Os.isFamily(Os.FAMILY_WINDOWS)) {
            toolName = "${name}_win.exe"
        } else {
            toolName = "${name}_linux"
        }

        def tool = "${project.buildDir}/${Constants.TOOLSDIR}/$toolName"
        def file = new File(tool)

        if(!file.exists()){
            file.parentFile.mkdirs()
            file.withOutputStream {
                def inputStream = OptimizeUtil.class.getResourceAsStream("/$name/$toolName")
                it << inputStream.bytes
                inputStream.close()
            }
        }
        if(file.exists() && file.setExecutable(true))
            return file.absolutePath

        throw new GradleException("$tool : not execute or not exist")
    }

    /*
    sdk<14:不支持webp格式图片
    14<= sdk <18:支持webp格式图片，但是不支持有透明通道的 webp格式图片
    sdk >=18:有透明通道的webp图片也支持

    此外如果想以无损方式将png/jpg图片转为webp，需要的sdk版本最小为18。
     */
    static void checkParams(int minSdk,int compressQualityMin,int compressQualityMax,
                            String convertStrategy,String webpStrategy,String compressStrategy,
                            String appIconName,String appIconRoundName){
        if(webpStrategy == Constants.LOSSLESS && minSdk < 18){
            throw new InvalidParameterException("If you want to use lossless strategy,your sdk must >= 18")
        }

        if((compressQualityMin > compressQualityMax)
                || compressQualityMin < 0 || compressQualityMin > 100
                || compressQualityMax < 0 || compressQualityMax > 100){
            throw new InvalidParameterException("illegal quality params, please check!")
        }

        if(convertStrategy != Constants.STRATEGY_BOTH
                && convertStrategy != Constants.STRATEGY_ONLY_COMPRESS){
            throw new InvalidParameterException("illegal convert strategy params, please check!")
        }

        if(compressStrategy != Constants.LOSSLESS && compressStrategy != Constants.LOSSY){
            throw new InvalidParameterException("illegal compress strategy params, please check!")
        }

        if(webpStrategy != Constants.LOSSLESS && webpStrategy != Constants.LOSSY){
            throw new InvalidParameterException("illegal webp strategy params, please check!")
        }

        if (appIconName == null || appIconName.isEmpty()) {
            throw new InvalidParameterException("appIconName should not be empty")
        }

        if (appIconRoundName == null || appIconRoundName.isEmpty()) {
            throw new InvalidParameterException("appIconRoundName should not be empty")
        }
    }

}