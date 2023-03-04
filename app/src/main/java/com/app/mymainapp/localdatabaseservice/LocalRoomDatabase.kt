package com.app.mymainapp.localdatabaseservice

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.mymainapp.localdatabaseservice.entities.StudentEntity


@Database(entities = [StudentEntity::class], version = 1, exportSchema = false)
abstract class LocalRoomDatabase : RoomDatabase() {
    /**
     * Connects the database to the DAO.
     */
    abstract fun appLocalRoomDatabaseDao(): AppLocalRoomDatabaseDao
}