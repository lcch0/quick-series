package com.example.quickseries.network.repositories

import com.example.quickseries.intefaces.data.IRepository
import com.example.quickseries.intefaces.views.IAppContext
import com.example.quickseries.models.*
import com.example.quickseries.network.json.Categories
import com.example.quickseries.network.json.restorants.Restorant
import com.example.quickseries.network.json.vacationspots.VacationSpot
import com.example.quickseries.network.queries.CategoryResourceQuery
import com.example.quickseries.network.queries.RestaurantListQuery
import com.example.quickseries.network.queries.VacationSpotsListQuery
import java.util.*

class CategoryRepository(private val categoryId: String) : IRepository<Category>
{
    override var onModelLoaded: ((Category) -> Unit)? = null
    override var onError: ((Category) -> Exception)? = null
    override fun loadModel(app: IAppContext)
    {
        val categoryQuery = CategoryResourceQuery(categoryId)

        categoryQuery.onSuccess =
            {
                when (it.slug)
                {
                    RestaurantListQuery.ID    ->
                    {
                        val query = RestaurantListQuery()
                        query.onSuccess =
                            {
                                lr -> transformFromRestos(it, lr).let { c -> onModelLoaded?.invoke(c) }
                            }
                        query.request(app)
                    }
                    VacationSpotsListQuery.ID ->
                    {
                        val query = VacationSpotsListQuery()
                        query.onSuccess =
                            {
                                lvs -> transformFromVacations(it, lvs).let { c -> onModelLoaded?.invoke(c) }
                            }
                        query.request(app)
                    }
                }
            }

        categoryQuery.request(app)
    }

    private fun transformFromRestos(category: Categories, restoList: List<Restorant>): Category
    {
        val obj = Category()
        obj.id = UUID.fromString(category.eid)
        obj.name = category.title

        for (c in restoList.filter { it.category_eid == category.eid })
        {
            val model = Resource()
            model.id = UUID.fromString(c.eid)
            model.name = c.title
            model.htmlDesc = c.description

            val details = model.resourceDetails

            if(c.addresses?.isNotEmpty() == true)
            {
                val address = Address()
                val theirAddress = c.addresses[0]
                address.addressLine1 = theirAddress.address1
                address.city = theirAddress.city
                address.country = theirAddress.country
                address.label = theirAddress.label
                address.state = theirAddress.state
                address.zipCode = theirAddress.zipCode
                if(theirAddress.gps != null)
                {
                    address.geoCoordinates =
                        Pair(theirAddress.gps.latitude.toDouble(), theirAddress.gps.longitude.toDouble())
                }

                details.address = mutableListOf()
                details.address?.add(address)
            }

            if(c.contactInfo != null)
            {
                details.contactInfo = ContactInfo()
                details.contactInfo?.website = assignNullable(c.contactInfo.website, 0)
                details.contactInfo?.email = assignNullable(c.contactInfo.email, 0)
                details.contactInfo?.phoneNumber = assignNullable(c.contactInfo.phoneNumber, 0)
            }

            if (c.socialMedia != null)
            {
                details.socialMedia = SocialMedia()
                details.socialMedia?.facebook = assignNullable(c.socialMedia.facebook)
                details.socialMedia?.twitter = assignNullable(c.socialMedia.twitter)
                details.socialMedia?.youtubeChannel = assignNullable(c.socialMedia.youtubeChannel)
            }

            //and so on

            obj.resources.add(model)
        }
        return obj
    }

    private fun assignNullable(list: List<String?>?, pos: Int): String
    {
        return if(list.isNullOrEmpty()) "" else if(pos > -1 && pos < list.size) list[pos] ?: "" else ""
    }

    private fun assignNullable(list: List<String?>?): MutableList<String>
    {
        return  if(list.isNullOrEmpty())
                    mutableListOf()
                else
                    list.map { l -> l ?: "" }.toMutableList()
    }

    private fun transformFromVacations(category: Categories, vacationList: List<VacationSpot>): Category
    {
        val obj = Category()
        obj.id = UUID.fromString(category.eid)
        obj.name = category.title
        for (c in vacationList.filter { it.category_eid == category.eid })
        {
            val model = Resource()
            model.id = UUID.fromString(c.eid)
            model.name = c.title
            model.htmlDesc = c.description
            obj.resources.add(model)
        }
        return obj
    }
}