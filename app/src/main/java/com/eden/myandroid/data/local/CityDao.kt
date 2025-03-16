package com.eden.myandroid.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityDao {
    @Query("SELECT * FROM cities WHERE name LIKE :query || '%' LIMIT 5")
    suspend fun getCitiesStartingWith(query: String): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCities(cities: List<CityEntity>)
}
