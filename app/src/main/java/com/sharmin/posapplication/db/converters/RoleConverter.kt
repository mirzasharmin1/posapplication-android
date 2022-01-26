package com.sharmin.posapplication.db.converters

import androidx.room.TypeConverter
import com.sharmin.posapplication.db.models.Role

class  RoleConverter {
    @TypeConverter
    fun toRole(value: Int): Role = enumValues<Role>()[value]
    @TypeConverter
    fun fromRole(value: Role): Int = value.ordinal
}