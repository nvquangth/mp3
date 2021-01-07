package com.bt.base.model

import com.bt.mp3.entity.Model

interface ItemMapper<M : Model, MI : ModelItem> {

    /**
     * convert data from the format of use cases and entities layer, to the format of presentation
     * layer
     */
    fun mapToPresentation(model: M): MI

    /**
     * convert data from the format of presentation layer, to the format of use cases and entities
     * layer
     */
    fun mapToDomain(item: MI): M
}
