package com.example.news.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

)

data class DataItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("category_id")
	val categoryId: String,

	@field:SerializedName("thumb")
	val thumb: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("news_category")
	val newsCategory: NewsCategory,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("place_id")
	val placeId: Any,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class NewsCategory(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("deletedAt")
	val deletedAt: Any,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
