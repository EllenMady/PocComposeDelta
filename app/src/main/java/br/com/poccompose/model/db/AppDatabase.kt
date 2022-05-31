package br.com.poccompose.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.poccompose.model.dao.ClientDao
import br.com.poccompose.model.entities.ClientEntity

private const val DB_NAME = "poc_compose_app"
@Database(entities = [ClientEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clientDao(): ClientDao
    companion object {
        fun create(context: Context) : AppDatabase{
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            ).build()
        }
    }
}