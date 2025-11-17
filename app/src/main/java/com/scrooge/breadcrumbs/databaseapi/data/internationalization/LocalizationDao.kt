package com.scrooge.breadcrumbs.databaseapi.data.internationalization

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

data class LocalizedLanguageDbState(
    @Embedded
    val language: LanguageEntity,
    val label: String,
)

private const val LOCALIZED_LANGUAGE_BASE_QUERY = """
    SELECT lang.*, loc.value as label FROM languages lang
    INNER JOIN language_localizations lang_loc ON lang.id = lang_loc.language_id
    INNER JOIN localizations loc ON lang_loc.localization_id = loc.localization_id
"""

@Dao
interface LocalizationDao { // todo validate usage
    @Query("""
        ${LOCALIZED_LANGUAGE_BASE_QUERY} AND loc.language_id = :localizationLanguageId
    """)
    fun getAllLocalizedLanguages(localizationLanguageId: Short): Flow<List<LocalizedLanguageDbState>>

    @Query("""
        ${LOCALIZED_LANGUAGE_BASE_QUERY} AND loc.language_id = lang.id
        WHERE lang.language_code = :languageCode
    """)
    fun getLocalizedLanguage(languageCode: String): Flow<LocalizedLanguageDbState>

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
