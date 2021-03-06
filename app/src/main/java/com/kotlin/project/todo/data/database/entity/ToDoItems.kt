package com.kotlin.project.todo.data.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.kotlin.project.todo.data.database.STATUS
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class ToDoItems (
    @PrimaryKey(autoGenerate = true) var id:Int,
    var taskName:String,
    var tag:String,
    var desc:String,
    var isRemainder:Boolean,
    var dateTime:Long,
    var status: STATUS
) : Parcelable


class EnumTypeConverter {

    @TypeConverter
    fun restoreEnum(enumName: String): STATUS = STATUS.valueOf(enumName)

    @TypeConverter
    fun saveEnumToString(enumType: STATUS) = enumType.name
}