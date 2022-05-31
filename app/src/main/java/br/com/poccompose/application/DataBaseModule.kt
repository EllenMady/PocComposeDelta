package br.com.poccompose.application

import android.content.Context
import br.com.poccompose.model.dao.ClientDao
import br.com.poccompose.model.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.create(context)

    @Provides
    fun provideClientDao(database: AppDatabase): ClientDao{
        return database.clientDao()
    }
}