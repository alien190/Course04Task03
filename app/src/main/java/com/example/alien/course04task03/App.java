package com.example.alien.course04task03;

import android.app.Application;

import timber.log.Timber;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.configuration.Configuration;
import toothpick.registries.FactoryRegistryLocator;
import toothpick.registries.MemberInjectorRegistryLocator;

public class App extends Application {

    private static Scope sAppScope;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        Toothpick.setConfiguration(Configuration.forProduction().disableReflection());
        // Toothpick.setConfiguration(Configuration.forDevelopment().enableReflection());

       // MemberInjectorRegistryLocator.setRootRegistry(new com.example.alien.course04task03.MemberInjectorRegistry());
       // FactoryRegistryLocator.setRootRegistry(new com.example.alien.course04task03.FactoryRegistry());

       // sAppScope = Toothpick.openScope("Application");
       // sAppScope.installModules(new AppModule(this), new ContextModule(getApplicationContext()), new NetworkModule(), new RepositoryModule(), new ServiceModule());
        //, new ServiceModule()
    }
}
