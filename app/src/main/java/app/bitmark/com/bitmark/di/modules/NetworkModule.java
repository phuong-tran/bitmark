package app.bitmark.com.bitmark.di.modules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

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

@Module(includes = {AppModule.class})
public class NetworkModule {
    private static final String BASE_URL = "https://api.test.bitmark.com/v1/";

    @Singleton
    @Provides
    @Named("serverUrl")
    public String provideServerUrl() {
        return BASE_URL;
    }

    @Provides
    @Singleton
    public OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    @Provides
    @Singleton
    public Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    @Singleton
    @Provides
    public BitmarkWebService provideBitmarkWebService() {
        return createRetrofit().create(BitmarkWebService.class);
    }

    static class LoggingInterceptor implements Interceptor {
        private static Logger LOG = LoggerFactory.getLogger(LoggingInterceptor.class);
        @Override public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            LOG.info(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            LOG.info(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }
}
