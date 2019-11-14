package com.fxy.optimize.png.webp

import org.gradle.api.Project

abstract class BaseWebp implements IWebp {

    @Override
    void convert(Project project, String toolName, File file,List<File> failedList, int quality) {
        def name = file.name.substring(0,file.name.lastIndexOf("."))
        def outputFile = new File(file.parent, "${name}.webp")

        def result = command(toolName, file, outputFile, quality).execute()
        result.waitForProcessOutput()

        if(result.exitValue() == 0){
            def beforeLen = file.length()
            def afterLen = outputFile.length()

            if(beforeLen > afterLen){
                file.delete()
                failedList << file
                project.logger.error "convert webp success: ${file.absolutePath}"
            }else{
                outputFile.delete()
                failedList << file
                project.logger.error "converted webp is bigger than origin: ${file.absolutePath}"
            }

        }else{
            project.logger.error("convert webp failed : ${file.absolutePath}")
        }
    }

    abstract String command(String webpTool,File inFile,File outFile, int quality)
}