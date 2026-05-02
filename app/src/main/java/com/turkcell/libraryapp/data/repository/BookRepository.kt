package com.turkcell.libraryapp.data.repository

import com.turkcell.libraryapp.data.model.Book
import com.turkcell.libraryapp.data.supabase.supabase
import io.github.jan.supabase.postgrest.postgrest

class BookRepository {
    suspend fun getAllBooks(): Result<List<Book>> = runCatching {
        supabase.postgrest["books"]
                .select()
                .decodeList<Book>()
    }

    suspend fun getBookById(id:String): Result<Book> = runCatching {
        supabase.postgrest["books"]
            .select { filter { eq("id",id) } }
            .decodeSingle<Book>()
    }

    suspend fun addBook(book: Book): Result<Unit> = runCatching {
        if(book.title.length < 3)
            return@runCatching
        supabase.postgrest["books"].insert(book)
    }

    // ÖDEV 2: BookRepository Güncelleme, silme, arama fonksiyonlarını tanımla.
}


//  ÖDEV:
// - Kitapların kiralanma işi -> Kartta availableCopies>0 ise ÖDÜNÇ AL butonu <=0 ise STOKTA YOK göstergesi
// - BorrowRecord tablosunu ve policylerini oluştur..
// - ÖDÜNÇ AL butonu ilgili bilgileri isteyip-hesaplayarak (maks 5 gün) BorrowRecord tablosuna atalım.
// - Kiralamalar sayfası -> Giriş yapmış öğrencinin önceki-aktif kiralamalarını listeleyen bir sayfa.