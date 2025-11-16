package com.scrooge.breadcrumbs.databaseapi.data.internationalization

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalizationDao { // todo validate usage
    @Query("SELECT * FROM languages")
    fun getAllLanguages(): Flow<List<LanguageEntity>>

    @Query("""
        SELECT id FROM languages
        WHERE language_code = :languageCode
    """)
    suspend fun getLanguageId(languageCode: String): Int

    @Query("""
        SELECT value FROM localizations
        WHERE (localization_id, language_id) = (:localizationId, :languageId)
    """)
    suspend fun getLocalization(localizationId: Long, languageId: Int): String

    @Query("""
        SELECT value FROM localizations loc
        INNER JOIN languages lang ON loc.language_id = lang.id
        WHERE (loc.localization_id, lang.language_code) = (:localizationId, :languageCode)
    """)
    suspend fun getLocalization(localizationId: Long, languageCode: String): String
}
