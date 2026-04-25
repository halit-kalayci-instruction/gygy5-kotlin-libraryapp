package com.turkcell.libraryapp.data.supabase

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

val supabase = createSupabaseClient(
    supabaseKey = "",
    supabaseUrl = ""
) {
    install(Postgrest)
    install(Auth)
}