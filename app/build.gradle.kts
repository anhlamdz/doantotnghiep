plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id ("kotlin-kapt")
	id ("kotlin-parcelize")
	id("com.google.gms.google-services")
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
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = "17"
	}
	buildFeatures {
		dataBinding = true
		buildConfig = true
	}
}

dependencies {
	implementation("androidx.core:core-ktx:1.9.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.10.0")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	implementation("com.google.firebase:firebase-database:20.3.0")
	implementation("com.google.firebase:firebase-auth:22.3.0")
	implementation("com.google.firebase:firebase-storage:20.3.0")
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
	val room_version = "2.5.0"
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
	//okhttp
	implementation(platform("com.squareup.okhttp3:okhttp-bom:4.11.0"))
	implementation("com.squareup.okhttp3:okhttp")
	implementation("com.squareup.okhttp3:logging-interceptor")

	implementation ("com.github.dhaval2404:imagepicker:2.1")
	implementation ("de.hdodenhof:circleimageview:3.1.0")
	//viewpager2
	implementation ("androidx.viewpager2:viewpager2:1.0.0")
	//firebase
	//firebase
	implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
	implementation("com.google.firebase:firebase-database")
}