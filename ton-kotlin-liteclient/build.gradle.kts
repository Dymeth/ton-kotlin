kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.tonKotlinAdnl)
                api(projects.tonKotlinLiteapi)
                api(projects.tonKotlinLogger)
                implementation(libs.serialization.json)
                implementation(libs.atomicfu)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.coroutines.core)
            }
        }
    }
}
