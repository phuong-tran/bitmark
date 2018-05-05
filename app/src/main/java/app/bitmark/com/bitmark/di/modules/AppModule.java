package app.bitmark.com.bitmark.di.modules;

import android.content.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import app.bitmark.com.bitmark.App;
import app.bitmark.com.bitmark.di.scopes.ApplicationContext;
import app.bitmark.com.bitmark.network.BitmarkWebService;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class AppModule {

   @ApplicationContext
   @Provides
   public Context provideApplication(App app) {
      return app;
   }

}
