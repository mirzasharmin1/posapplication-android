package com.sharmin.posapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sharmin.posapplication.db.converters.DateConverter
import com.sharmin.posapplication.db.converters.ProductTypeConverter
import com.sharmin.posapplication.db.converters.RoleConverter
import com.sharmin.posapplication.db.converters.TransactionStatusConverter
import com.sharmin.posapplication.db.dao.ProductDao
import com.sharmin.posapplication.db.dao.TransactionDao
import com.sharmin.posapplication.db.dao.TransactionItemDao
import com.sharmin.posapplication.db.models.*
import android.content.Context
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sharmin.posapplication.db.dao.PeopleDao
import com.sharmin.posapplication.db.dummy.dummyPeoples
import com.sharmin.posapplication.db.dummy.dummyProducts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        People::class,
        Product::class,
        Transaction::class,
        TransactionItem::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateConverter::class, ProductTypeConverter::class, RoleConverter::class, TransactionStatusConverter::class)
abstract class PosDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun transactionDao(): TransactionDao

    abstract fun transactionItemDao(): TransactionItemDao

    abstract fun peopleDao(): PeopleDao

    companion object {
        @Volatile
        private var INSTANCE: PosDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PosDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PosDatabase::class.java,
                    "pos_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateProducts(database.productDao())
                        populatePeoples(database.peopleDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateProducts(productDao: ProductDao) {
            dummyProducts().map {
                productDao.insert(it)
            }
        }

        suspend fun populatePeoples(peopleDao: PeopleDao) {
            dummyPeoples().map {
                peopleDao.insert(it)
            }
        }
    }
}
