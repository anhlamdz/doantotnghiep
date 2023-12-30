plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id ("kotlin-kapt")
}

android {
	namespace = "kids.preschool.doantotnghiep"
	compileSdk = 34

	defaultConfig {
		applicationId = "kids.preschool.doantotnghiep"
		minSdk = 28
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		dataBinding = true
		buildConfig = true
	}
}

dependencies {
	implementation("androidx.core:core-ktx:1.12.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.11.0")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

	// coroutine
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

	implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

	implementation ("androidx.activity:activity-ktx:1.8.0")
	//implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1 "
	implementation ("androidx.fragment:fragment-ktx:1.6.1")
	implementation ("androidx.room:room-common:2.5.2")

	val paging_version = "3.1.1"

	//noinspection GradleDependency
	//noinspection GradleDependency
	implementation ("androidx.paging:paging-runtime-ktx:$paging_version")
	// For Kotlin use paging-runtime-ktx
	//noinspection GradleDependency
	implementation ("androidx.paging:paging-runtime-ktx:$paging_version")

	testImplementation ("androidx.paging:paging-common-ktx:$paging_version")

	// navigation
	implementation ("androidx.navigation:navigation-fragment-ktx:2.7.4")
	implementation ("androidx.navigation:navigation-ui-ktx:2.7.4")
	// Feature module Support
	implementation("androidx.navigation:navigation-dynamic-features-fragment:2.7.4")

	// Testing Navigation
	androidTestImplementation("androidx.navigation:navigation-testing:2.7.4")

	// Jetpack Compose Integration
	implementation("androidx.navigation:navigation-compose:2.7.4")
	//room
	val room_version = "2.5.2"
	implementation("androidx.room:room-runtime:$room_version")
	annotationProcessor("androidx.room:room-compiler:$room_version")
	// To use Kotlin annotation processing tool (kapt)
	//noinspection KaptUsageInsteadOfKsp
	kapt ("androidx.room:room-compiler:$room_version")
	// optional - Kotlin Extensions and Coroutines support for Room
	implementation("androidx.room:room-ktx:$room_version")
	// optional - RxJava2 support for Room
	implementation("androidx.room:room-rxjava2:$room_version")
	implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
	implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
	implementation ("androidx.lifecycle:lifecycle-common-java8:2.6.2")

	// glide
	implementation ("com.github.bumptech.glide:glide:4.16.0")
}