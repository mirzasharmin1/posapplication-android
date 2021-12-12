package com.sharmin.posapplication.models.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.sharmin.posapplication.models.Role

@ProvidedTypeConverter
class  RoleConverter {
    @TypeConverter
    fun toRole(value: Int): Role = enumValues<Role>()[value]
    @TypeConverter
    fun fromRole(value: Role): Int = value.ordinal
}