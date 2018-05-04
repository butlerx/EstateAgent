package com.dcu

import org.springframework.stereotype.Component
import java.util.*

@Component
class PropertyDBImpl : PropertyDB {
    private val properties = ArrayList<Property>()

    override val all: List<Property>
        get() = properties

    init {
        val rand = Random()
        val types = arrayOf("apartment", "house")
        for (i in 0..99) {
            this.properties.add(
                    Property(
                            types[rand.nextInt(types.size)],
                            rand.nextInt(24) + 1,
                            rand.nextInt(4) + 1,
                            100000 + (rand.nextDouble() * (1000000 - 100000 + 1)).toInt(),
                            rand.nextInt(14) + 1,
                            i))
        }
    }

    override fun get(id: Int): Property {
        if (properties.size < id) throw NotFound()
        return properties[id]
    }

    override fun update(prop: Property, id: Int) {
        if (properties.size < id) throw NotFound()
        properties[id] = prop
    }

    override fun delete(id: Int) {
        properties.removeAt(id)
    }

    override fun add(prop: Property): Int {
        properties.add(
                Property(
                        prop.type,
                        prop.district,
                        prop.bedrooms,
                        prop.price,
                        prop.dayLeft,
                        properties.size))
        return properties.size - 1
    }
}