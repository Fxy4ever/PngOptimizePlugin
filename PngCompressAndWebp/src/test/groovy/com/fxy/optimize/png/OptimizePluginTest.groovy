/**
 * @Author fxy
 * @Date 2019-11-14 00:23
 * @Description TODO
 */

package com.fxy.optimize.png

import org.gradle.api.Project
import org.gradle.internal.impldep.org.testng.annotations.Test

class OptimizePluginTest {

    @Test
    OptimizePluginTest(){
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'com.fxy.compress'

//        assertTrue(project.tasks.compressAnd2Wep instanceof )
    }
}