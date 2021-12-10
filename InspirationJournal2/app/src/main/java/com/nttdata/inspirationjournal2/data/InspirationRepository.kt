package com.nttdata.inspirationjournal2.data

import com.nttdata.inspirationjournal2.data.local.dao.InspirationDao
import com.nttdata.inspirationjournal2.data.remote.InspirationService
import com.nttdata.inspirationjournal2.data.remote.request.InspirationRequest
import com.nttdata.inspirationjournal2.data.remote.toInspirationItem
import com.nttdata.inspirationjournal2.model.InspirationItem
import java.lang.Exception

class InspirationRepository(
    private val inspirationDao: InspirationDao,
    private val inspirationService: InspirationService
) {

    suspend fun getInspirationList(): List<InspirationItem>{
        val response = inspirationService.getInspirationList()

        if(response.isSuccessful){
            val inspirationList = response.body()
            if(inspirationList != null){
                val inspirationResults = inspirationList.inspirations
                return inspirationResults.map{ inspirationItemResponse ->
                    inspirationItemResponse.toInspirationItem()
                }
            }
            throw Exception("Inspiration list response is null")
        }
        else{
            throw Exception("Somenthig went wrong")
        }
    }

    suspend fun getInspirationDetail(inspirationId: String): InspirationItem? {
        val response = inspirationService.getInspirationDetail(inspirationId)
        if (response.isSuccessful){
            val inspirationDetailResponse = response.body()
            if(inspirationDetailResponse != null){
                val inspirationDetailResult = inspirationDetailResponse.inspiration
                return inspirationDetailResult.toInspirationItem()
            }
            throw Exception("Inspiration list response is null")
        }
        else {
            throw Exception("Somenthig went wrong")
        }
    }

    suspend fun getFavoriteList(): List<InspirationItem>{
        val response = inspirationService.getFavoriteList()

        if(response.isSuccessful){
            val inspirationList = response.body()
            if(inspirationList != null){
                val inspirationResults = inspirationList.inspirations
                return inspirationResults.map{ inspirationItemResponse ->
                    inspirationItemResponse.toInspirationItem()
                }
            }
            throw Exception("Inspiration list response is null")
        }
        else{
            throw Exception("Somenthig went wrong")
        }
    }

    suspend fun addInspiration(title: String, description: String, category: String): Boolean {
        val request = InspirationRequest(title, description, category)
        val response = inspirationService.addInspiration(request)
        if (response.isSuccessful) {
            val inspirationResponse = response.body()
            if (inspirationResponse != null) {
                return inspirationResponse.status
            } else {
                throw Exception("Inspiration response is null")
            }
        } else {
            throw Exception("Somenthig went wrong")
        }
    }

}