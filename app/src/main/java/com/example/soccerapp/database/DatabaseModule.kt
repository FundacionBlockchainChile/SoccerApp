package com.example.soccerapp.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "soccerapp_database"
        )
            .fallbackToDestructiveMigration() // Añadir aquí para manejar cambios de esquema destruyendo la base de datos anterior
            .build()
    }

    @Provides
    fun provideTeamDao(database: AppDatabase): TeamDao {
        return database.teamDao()
    }
}
