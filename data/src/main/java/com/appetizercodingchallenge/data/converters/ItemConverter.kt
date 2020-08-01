package com.appetizercodingchallenge.data.converters

import androidx.room.TypeConverter
import com.appetizercodingchallenge.data.types.ListItemType
import com.appetizercodingchallenge.data.types.toTrackType

class ItemConverter {

    @TypeConverter
    fun convertToTrackType(value: String): ListItemType? {
        return toTrackType(value)
    }

    @TypeConverter
    fun convertFromTrackType(value: ListItemType): String {
        return value.type
    }
}
