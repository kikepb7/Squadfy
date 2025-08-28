package com.kikepb.squadfy

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform