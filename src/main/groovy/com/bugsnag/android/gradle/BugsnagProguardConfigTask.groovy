package com.bugsnag.android.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.android.build.gradle.api.ApplicationVariant

class BugsnagProguardConfigTask extends DefaultTask {
    static final String PROGUARD_CONFIG_PATH = "build/intermediates/bugsnag/bugsnag.pro"
    static final String PROGUARD_CONFIG_SETTINGS = "-keepattributes LineNumberTable,SourceFile"

    ApplicationVariant applicationVariant

    BugsnagProguardConfigTask() {
        super()
        this.description = "Adds the Bugsnag recommended proguard settings to your project"
    }

    @TaskAction
    def createProguardConfig() {
        // Create a file handle for the Bugsnag proguard config file
        def file = project.file(PROGUARD_CONFIG_PATH)

        // Create the directory if it doesnt exist already
        file.getParentFile().mkdirs()

        // Write our recommended proguard settings to this file
        FileWriter fr = new FileWriter(file.path)
        fr.write(PROGUARD_CONFIG_SETTINGS)
        fr.write("\n")
        fr.close()

        // Add this proguard settings file to the list
        applicationVariant.getBuildType().buildType.proguardFiles(file)
    }
}
