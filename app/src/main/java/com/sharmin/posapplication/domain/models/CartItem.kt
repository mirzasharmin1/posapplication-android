package com.sharmin.posapplication.domain.models

import com.sharmin.posapplication.db.models.Product

class CartItem(val product: Product, var quantity: Int)