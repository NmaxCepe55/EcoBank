package com.example.ecobank.data.remote

import com.example.ecobank.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClientProvider {
    // Supabase URL butuh base origin (cth: https://xxx.supabase.co).
    // Menghapus suffix /rest/v1/ jika ada di local.properties
    private val rawUrl = BuildConfig.SUPABASE_URL.trim()
    private val baseUrl = when {
        rawUrl.contains("/rest/v1") -> rawUrl.substringBefore("/rest/v1")
        else -> rawUrl
    }.removeSuffix("/")

    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = baseUrl,
        supabaseKey = BuildConfig.SUPABASE_ANON_KEY.trim(),
    ) {
        install(Postgrest)
    }
}
