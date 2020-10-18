package com.yychun1217.mychallenge.datasource.local

import android.database.sqlite.SQLiteConstraintException
import com.yychun1217.mychallenge.db.DeliveryDao
import com.yychun1217.mychallenge.model.Delivery
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.pagination.datasource.ILocalPagingSource
import timber.log.Timber

class DeliveryLocalPagingSource(
    private val deliveryDao: DeliveryDao
) : ILocalPagingSource<GetDeliveryRequest, Delivery.Db> {
    override suspend fun insert(
        key: GetDeliveryRequest,
        page: List<Delivery.Db>
    ): List<Delivery.Db> {
        return try {
            deliveryDao.insertAll(*page.toTypedArray())
            page
        } catch (e: SQLiteConstraintException) {
            Timber.e(e)
            page.filter {
                try {
                    deliveryDao.insert(it)
                    true
                } catch (e: SQLiteConstraintException) {
                    Timber.e(e)
                    false
                }
            }
        }
    }

    override suspend fun loadPage(key: GetDeliveryRequest): List<Delivery.Db> =
        deliveryDao.getAll(key.offset, key.limit)
}