package com.dcu

interface PropertyDB {
    val all: List<Property>

    operator fun get(id: Int): Property

    fun update(prop: Property, id: Int)

    fun delete(id: Int)

    fun add(prop: Property): Int
}
