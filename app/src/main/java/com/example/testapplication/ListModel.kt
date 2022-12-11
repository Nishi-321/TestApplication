package com.example.testapplication

/**
 *
 * @property id
 * @property author
 * @property width
 * @property height
 * @property url
 * @property download_url
 * @author Nishikanta
 */
data class ListModel(
    val id: Int,
    val author: String,
    val width: String,
    val height: String,
    val url: String,
    val download_url: String
)
