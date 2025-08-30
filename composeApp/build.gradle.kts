import org.gradle.kotlin.dsl.implementation
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.google.services)
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.kspCompose)
}

kotlin {
    sourceSets.commonMain {
        kotlin.srcDir("build/generated/ksp/metadata")
    }

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        task("testClasses")
        androidMain.dependencies {
            // Compose Android
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.ui)
            implementation(libs.androidx.ui.graphics)
            implementation(libs.androidx.ui.tooling)
            implementation(libs.androidx.ui.tooling.preview)
            implementation(libs.androidx.material3)
//            implementation(libs.androidx.material.icons.extended)
//            implementation(libs.material)
            implementation(libs.navigation.compose)

            // AndroidX
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.datastore)
            implementation(libs.androidx.appcompat)

            // Koin
            implementation(libs.koin.android)

            // Firebase / Google Play Services
            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.auth)
            implementation(libs.firebase.google)
            implementation(libs.firebase.firestore)
            implementation(libs.firebase.database)
            implementation(libs.firebase.analytics)
            implementation(libs.firebase.storage)
            implementation(libs.firebase.notifications)
            // implementation(libs.firebase.crashlytics) // TODO

            // Ktor Android
            implementation(libs.ktor.client.okhttp)
            implementation(libs.ktor.client.android)
        }
        commonMain.dependencies {
            // Compose multiplatform
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            // Lifecycle
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Navigation
            implementation(libs.navigation.compose)

            // Koin DI
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)

            // Ktor multiplatform
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.negotiation)
            implementation(libs.ktor.serialization)

            // Pagination
//            implementation(libs.paging.common)
//            implementation(libs.paging.compose.common)

            // Coil
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)

            // Others
//            implementation(libs.kotlinx.datetime)
//            implementation(libs.androidx.sqlite.bundled)
//            implementation(libs.room.runtime)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }

    cocoapods {
        version = "1.0.0"
        summary = "Squadfy App"
        homepage = "https://kikepb.com"
        ios.deploymentTarget = "18.2"
        framework {
            baseName = "composeApp"
            isStatic = true
        }

        pod("FirebaseCore") { extraOpts += listOf("-compiler-option", "-fmodules") }
        pod("FirebaseAuth") { extraOpts += listOf("-compiler-option", "-fmodules") }
    }
}

android {
    namespace = "com.kikepb.squadfy"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.kikepb.squadfy"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

ksp {
    arg("room.schemaLocation", "${projectDir}/schemas")
}

dependencies {
    debugImplementation(compose.uiTooling)
    add("kspCommonMainMetadata", libs.room.compiler)
    add("kspAndroid", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
}
