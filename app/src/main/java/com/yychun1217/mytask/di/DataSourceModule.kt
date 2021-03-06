package com.yychun1217.mytask.di

import com.yychun1217.mytask.datasource.local.*
import com.yychun1217.mytask.db.DeliveryAndRouteDao
import com.yychun1217.mytask.db.DeliveryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Inject

@Module
@InstallIn(FragmentComponent::class)
class DataSourceModule {
    @Provides
    @Inject
    fun provideIDeliveryAndRouteLocalDataSource(
        deliveryAndRouteDao: DeliveryAndRouteDao
    ): IDeliveryAndRouteLocalDataSource = DeliveryAndRouteLocalDataSource(deliveryAndRouteDao)

    @Provides
    @Inject
    fun provideIDeliveryLocalDataSource(
        deliveryDao: DeliveryDao
    ): IDeliveryLocalDataSource = DeliveryLocalDataSource(deliveryDao)
}