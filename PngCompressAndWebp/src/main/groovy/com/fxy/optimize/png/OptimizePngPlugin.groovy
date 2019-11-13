package com.fxy.optimize.png

import org.gradle.api.Plugin
import org.gradle.api.Project

class OptimizePngPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.extensions.create("optimizeOption",OptimizeOptionExtension)

        project.task("compressAndWebp"){
            doLast {
                String rootLocation =  project["optimizeOption"].rootLocation
                println rootLocation
                if(rootLocation != null){
                    searchDir(rootLocation)
                }else{
                    println "root null"
                }
            }
        }
    }

    static void searchDir(String rootLocation){
        File root = new File(rootLocation)
        if(root.isDirectory()){
            root.eachFile { file ->
                searchDir(file.absolutePath)
            }
        }else{
            if(root.name.endsWith(".png")){
                println root.absolutePath
            }
        }
    }
}