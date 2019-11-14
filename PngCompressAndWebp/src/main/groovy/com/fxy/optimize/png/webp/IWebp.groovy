package com.fxy.optimize.png.webp

import org.gradle.api.Project

interface IWebp{
    void convert(Project project, String toolName, File file,List<File> failedList, int quality)
}