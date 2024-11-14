#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_movielist_BaseApplication_getBaseUrl(
        JNIEnv* env,
        jobject /* this */) {
    std::string baseUrl = "https://api.themoviedb.org/";
    return env->NewStringUTF(baseUrl.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_movielist_BaseApplication_getImageUrl(
        JNIEnv* env,
        jobject /* this */) {
    std::string imgUrl = "https://image.tmdb.org/t/p/w500";
    return env->NewStringUTF(imgUrl.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_movielist_BaseApplication_getApiKey(
        JNIEnv* env,
        jobject /* this */) {
    std::string apiKey = "ead818942396c644f8ee13eeab662cef";
    return env->NewStringUTF(apiKey.c_str());
}