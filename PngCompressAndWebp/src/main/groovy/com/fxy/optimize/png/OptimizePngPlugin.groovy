package com.fxy.optimize.png


import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

class OptimizePngPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        startApply(project)
    }

    static void startApply(Project project){
        project.extensions.create("optimizeOption",OptimizeOptionExtension)

        project.task("compressAndWebp",type : OptimizeTask){
            doFirst{
                if(project["optimizeOption"].rootLocation == null){
                    throw new GradleException("You should write necessary option extension in your build.gradle")
                }
            }
        }
    }
}